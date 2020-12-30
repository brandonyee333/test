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

package com.liferay.osb.asah.stream.curator.bot.nanite.activity;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchBulkRequestBuilder;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoActivityDog;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.messaging.Channel;
import com.liferay.osb.asah.common.messaging.MessageSubscriber;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.stream.curator.bot.nanite.Nanite;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
public class IndividualActivityFieldsNanite implements Nanite {

	@Override
	public String getCollectionName() {
		return "individual-activity-fields";
	}

	@Override
	public long getInterval() {
		return DateUtil.SECOND * 5;
	}

	@Override
	public void run() {
		while (true) {
			List<String> messages = _messageSubscriber.pullMessages(100);

			if (messages.isEmpty()) {
				break;
			}

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
				_faroInfoElasticsearchInvoker.
					createElasticsearchBulkRequestBuilder();

			for (Map.Entry<String, Map<String, Long>> ownerIdEntry :
					ownerIdCounts.entrySet()) {

				String ownerId = ownerIdEntry.getKey();

				JSONObject individualJSONObject =
					_faroInfoElasticsearchInvoker.fetch("individuals", ownerId);

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
				_faroInfoElasticsearchInvoker.get(
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

	@Autowired
	private FaroInfoActivityDog _faroInfoActivityDog;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@MessageSubscriber.Autowired(channel = Channel.ACTIVE_INDIVIDUAL_IDS)
	private MessageSubscriber _messageSubscriber;

}