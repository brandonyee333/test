/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.asah.batch.curator.bot.nanite;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchBulkRequestBuilder;
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoActivityDog;
import com.liferay.osb.asah.common.http.QueueHttp;
import com.liferay.osb.asah.common.json.JSONArrayIterator;
import com.liferay.osb.asah.common.json.JSONUtil;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortOrder;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Michael Bowerman
 */
@Component
public class IndividualActivityFieldsNanite extends BaseActivitiesNanite {

	@Override
	public long getInterval() {
		return DateUtil.SECOND * 5;
	}

	@Override
	public void run() throws Exception {
		while (true) {
			int messagesCount = _queueHttp.getMessagesCount(
				QueueHttp.QUEUE_NAME_ACTIVE_INDIVIDUAL_IDS);

			if (messagesCount <= 0) {
				break;
			}

			JSONObject responseJSONObject = new JSONObject(
				_queueHttp.getMessages(
					QueueHttp.QUEUE_NAME_ACTIVE_INDIVIDUAL_IDS, 100));

			List<String> messages = JSONUtil.toStringList(
				responseJSONObject.getJSONArray("messages"), "message");

			Stream<String> stream = messages.stream();

			Map<String, Map<String, Long>> ownerIdCounts = stream.map(
				JSONObject::new
			).collect(
				Collectors.groupingBy(
					jsonObject -> jsonObject.getString("ownerId"),
					Collectors.groupingBy(
						jsonObject -> jsonObject.getString("channelId"),
						Collectors.counting()))
			);

			ElasticsearchBulkRequestBuilder elasticsearchBulkRequestBuilder =
				faroInfoElasticsearchInvoker.
					createElasticsearchBulkRequestBuilder();

			for (Map.Entry<String, Map<String, Long>> ownerIdEntry :
					ownerIdCounts.entrySet()) {

				String ownerId = ownerIdEntry.getKey();

				JSONObject individualJSONObject =
					faroInfoElasticsearchInvoker.fetch("individuals", ownerId);

				if (individualJSONObject == null) {
					continue;
				}

				elasticsearchBulkRequestBuilder.update(
					"individuals",
					JSONUtil.put(
						"activitiesCounts",
						_getActivitiesCountsJSONArray(
							ownerIdEntry.getValue(), individualJSONObject)
					).put(
						"id", ownerId
					).put(
						"lastActivityDates",
						_getLastActivityDatesJSONArray(
							ownerIdEntry.getValue(), individualJSONObject)
					));
			}

			if (elasticsearchBulkRequestBuilder.hasActions()) {
				elasticsearchBulkRequestBuilder.get();
			}
		}
	}

	@Override
	protected void cleanUp() throws Exception {
		try {
			JSONArrayIterator.of(
				"individuals", faroInfoElasticsearchInvoker,
				individualJSONObject -> {
					individualJSONObject.remove("lastActivityDates");

					faroInfoElasticsearchInvoker.update(
						"individuals", individualJSONObject.getString("id"),
						JSONUtil.put(
							"activitiesCounts", Collections.emptyList()));

					return null;
				}
			).setMonitoringConsumers(
				this::monitorProcessedCount, this::monitorQueueSize
			).iterate();
		}
		finally {
			_active = false;
		}
	}

	@Override
	protected Log getLog() {
		return LogFactory.getLog(IndividualActivityFieldsNanite.class);
	}

	@Override
	protected boolean isActive() {
		return _active;
	}

	@Override
	protected void setActive(boolean active) {
		_active = active;
	}

	private JSONArray _getActivitiesCountsJSONArray(
		Map<String, Long> channelIdsCounts, JSONObject individualJSONObject) {

		JSONArray activitiesCountsJSONArray = individualJSONObject.optJSONArray(
			"activitiesCounts");

		if (activitiesCountsJSONArray == null) {
			activitiesCountsJSONArray = new JSONArray();
		}

		for (Map.Entry<String, Long> channelIdEntry :
				channelIdsCounts.entrySet()) {

			String channelId = channelIdEntry.getKey();
			long count = channelIdEntry.getValue();

			JSONObject channelCountJSONObject = JSONUtil.find(
				activitiesCountsJSONArray, "channelId", channelId);

			if (channelCountJSONObject == null) {
				channelCountJSONObject = JSONUtil.put("channelId", channelId);

				activitiesCountsJSONArray.put(channelCountJSONObject);
			}

			count += channelCountJSONObject.optLong("activitiesCount", 0);

			channelCountJSONObject.put("activitiesCount", count);
		}

		return activitiesCountsJSONArray;
	}

	private JSONArray _getLastActivityDatesJSONArray(
		Map<String, Long> channelIdsCounts, JSONObject individualJSONObject) {

		JSONArray lastActivityDatesJSONArray =
			individualJSONObject.optJSONArray("lastActivityDates");

		if (lastActivityDatesJSONArray == null) {
			lastActivityDatesJSONArray = new JSONArray();
		}

		for (String channelId : channelIdsCounts.keySet()) {
			JSONArray activitiesJSONArray = new JSONArray(
				faroInfoElasticsearchInvoker.get(
					"activities",
					searchSourceBuilder -> {
						searchSourceBuilder.query(
							BoolQueryBuilderUtil.filter(
								_faroInfoActivityDog.getEventsQueryBuilder(
									individualJSONObject.getString("id"))
							).filter(
								QueryBuilders.termQuery("channelId", channelId)
							));
						searchSourceBuilder.size(1);
						searchSourceBuilder.sort(
							SortBuilderUtil.fieldSort(
								"endTime", SortOrder.DESC));
					}));

			if (activitiesJSONArray.length() == 0) {
				continue;
			}

			JSONObject activityJSONObject = activitiesJSONArray.getJSONObject(
				0);

			JSONObject channelLastActivityDateJSONObject = JSONUtil.find(
				lastActivityDatesJSONArray, "channelId", channelId);

			if (channelLastActivityDateJSONObject == null) {
				channelLastActivityDateJSONObject = JSONUtil.put(
					"channelId", channelId);

				lastActivityDatesJSONArray.put(
					channelLastActivityDateJSONObject);
			}

			channelLastActivityDateJSONObject.put(
				"lastActivityDate", activityJSONObject.getString("endTime"));
		}

		return lastActivityDatesJSONArray;
	}

	private boolean _active;

	@Autowired
	private FaroInfoActivityDog _faroInfoActivityDog;

	@Autowired
	private QueueHttp _queueHttp;

}