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

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.date.dog.TimeZoneDog;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchBulkRequestBuilder;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.osb.asah.common.json.JSONArrayIterator;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.upgrade.UpgradeStep;

import java.time.LocalDate;
import java.time.LocalTime;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortOrder;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Leslie Wong
 */
@Component
public class ActivityGroupsUpgradeStep implements UpgradeStep {

	@Override
	public void upgrade(String version) throws Exception {
		LocalDate localDate = LocalDate.parse("2021-06-28");

		ElasticsearchBulkRequestBuilder elasticsearchBulkRequestBuilder =
			_elasticsearchInvoker.createElasticsearchBulkRequestBuilder();

		JSONArrayIterator.of(
			"activity-groups", _elasticsearchInvoker,
			activityGroupJSONObject -> {
				String activityGroupEndTime = _getActivityGroupEndTime(
					activityGroupJSONObject.getString("channelId"),
					activityGroupJSONObject.getString("dataSourceId"),
					activityGroupJSONObject.getString("ownerId"),
					activityGroupJSONObject.getString("userId"));

				if (activityGroupEndTime == null) {
					return elasticsearchBulkRequestBuilder;
				}

				return elasticsearchBulkRequestBuilder.update(
					"activity-groups",
					JSONUtil.put(
						"endTime", activityGroupEndTime
					).put(
						"endTimeLocal",
						DateUtil.toLocalDateTime(
							DateUtil.toUTCDate(activityGroupEndTime),
							_timeZoneDog.getZoneId())
					).put(
						"id", activityGroupJSONObject.getString("id")
					));
			}
		).setQueryBuilder(
			QueryBuilders.rangeQuery(
				"day"
			).gte(
				DateUtil.toUTCString(localDate.atTime(LocalTime.MIDNIGHT))
			)
		).iterate();
	}

	private String _getActivityGroupEndTime(
		String channelId, String dataSourceId, String ownerId, String userId) {

		JSONObject activityJSONObject = _elasticsearchInvoker.fetch(
			"activities",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("channelId", channelId)
			).filter(
				QueryBuilders.termQuery("dataSourceId", dataSourceId)
			).filter(
				QueryBuilders.termQuery("ownerId", ownerId)
			).filter(
				QueryBuilders.termQuery("userId", userId)
			),
			SortBuilderUtil.fieldSort("startTime", SortOrder.DESC), null, null);

		if (activityJSONObject == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					String.format(
						"Unable to find activities for individual %s with " +
							"userId %s at channel %s and data source %s",
						ownerId, userId, channelId, dataSourceId));
			}

			return null;
		}

		return activityJSONObject.getString("startTime");
	}

	private static final Log _log = LogFactory.getLog(
		ActivityGroupsUpgradeStep.class);

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private TimeZoneDog _timeZoneDog;

}