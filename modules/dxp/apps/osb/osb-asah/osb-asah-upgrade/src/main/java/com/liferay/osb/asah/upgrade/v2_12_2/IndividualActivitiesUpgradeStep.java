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

package com.liferay.osb.asah.upgrade.v2_12_2;

import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchBulkRequestBuilder;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoActivityDog;
import com.liferay.osb.asah.common.json.JSONArrayIterator;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.upgrade.UpgradeStep;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortOrder;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Leslie Wong
 */
@Component
public class IndividualActivitiesUpgradeStep implements UpgradeStep {

	@Override
	public void upgrade(String version) throws Exception {
		ElasticsearchBulkRequestBuilder elasticsearchBulkRequestBuilder =
			_elasticsearchInvoker.createElasticsearchBulkRequestBuilder();

		JSONArrayIterator.of(
			"individuals", _elasticsearchInvoker,
			individualJSONObject -> elasticsearchBulkRequestBuilder.update(
				"individuals",
				JSONUtil.put(
					"activitiesCounts",
					_getActivitiesCountsJSONArray(individualJSONObject)
				).put(
					"id", individualJSONObject.getString("id")
				).put(
					"lastActivityDates",
					_getLastActivityDatesJSONArray(individualJSONObject)
				))
		).iterate();
	}

	private long _getActivitiesCount(String channelId, String individualId) {
		return _elasticsearchInvoker.count(
			"activities",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("channelId", channelId)
			).filter(
				_faroInfoActivityDog.getEventsQueryBuilder(individualId)
			));
	}

	private JSONArray _getActivitiesCountsJSONArray(
		JSONObject individualJSONObject) {

		JSONArray activitiesCountsJSONArray = new JSONArray();

		List<String> channelIds = JSONUtil.toStringList(
			individualJSONObject.optJSONArray("channelIds"));

		for (String channelId : channelIds) {
			long activitiesCount = _getActivitiesCount(
				channelId, individualJSONObject.getString("id"));

			if (activitiesCount == 0) {
				continue;
			}

			activitiesCountsJSONArray.put(
				JSONUtil.put(
					"activitiesCount", activitiesCount
				).put(
					"channelId", channelId
				));
		}

		return activitiesCountsJSONArray;
	}

	private String _getLastActivityDate(String channelId, String individualId) {
		JSONObject activityJSONObject = _elasticsearchInvoker.fetch(
			"activities",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("channelId", channelId)
			).filter(
				_faroInfoActivityDog.getEventsQueryBuilder(individualId)
			),
			SortBuilderUtil.fieldSort("startTime", SortOrder.DESC), null, null);

		if (activityJSONObject == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					String.format(
						"Unable to find activities for individual %s in " +
							"channel %s",
						individualId, channelId));
			}

			return null;
		}

		return activityJSONObject.getString("startTime");
	}

	private JSONArray _getLastActivityDatesJSONArray(
		JSONObject individualJSONObject) {

		JSONArray lastActivityDatesJSONArray = new JSONArray();

		List<String> channelIds = JSONUtil.toStringList(
			individualJSONObject.optJSONArray("channelIds"));

		for (String channelId : channelIds) {
			String lastActivityDate = _getLastActivityDate(
				channelId, individualJSONObject.getString("id"));

			if (lastActivityDate == null) {
				continue;
			}

			lastActivityDatesJSONArray.put(
				JSONUtil.put(
					"channelId", channelId
				).put(
					"lastActivityDate", lastActivityDate
				));
		}

		return lastActivityDatesJSONArray;
	}

	private static final Log _log = LogFactory.getLog(
		IndividualActivitiesUpgradeStep.class);

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private FaroInfoActivityDog _faroInfoActivityDog;

}