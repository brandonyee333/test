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

package com.liferay.osb.asah.batch.curator.bot.nanite.arm;

import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchBulkRequestBuilder;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;

import com.liferay.osb.asah.common.json.JSONArrayIterator;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.util.StringUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.QueryBuilders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Leslie Wong
 */
@Component
public class AssignSessionActivitiesArm {

	public void execute() throws Exception {
		JSONArrayIterator.of(
			"user-sessions", _elasticsearchInvokerFactory.forCerebroInfo(),
			userSessionJSONObject -> {
				String sessionId = userSessionJSONObject.getString("id");

				String firstEventDate = userSessionJSONObject.optString(
					"firstEventDate");

				if (StringUtil.isNull(firstEventDate)) {
					_log.warn(
						String.format(
							"Skipping session %s due to missing firstEventDate",
							sessionId));

					return null;
				}

				String individualId = userSessionJSONObject.optString(
					"individualId");

				if (StringUtil.isNull(individualId)) {
					_log.warn(
						String.format(
							"Skipping session %s due to missing individualId",
							sessionId));

					return null;
				}

				String lastEventDate = userSessionJSONObject.optString(
					"lastEventDate");

				if (StringUtil.isNull(lastEventDate)) {
					_log.warn(
						String.format(
							"Skipping session %s due to missing startTime",
							sessionId));

					return null;
				}

				String userId = userSessionJSONObject.optString("userId");

				if (StringUtil.isNull(userId)) {
					_log.warn(
						String.format(
							"Skipping session %s due to missing userId",
							sessionId));

					return null;
				}

				_upgradeActivities(
					firstEventDate, individualId, lastEventDate, sessionId,
					userId);

				return null;
			}
		).setSourceIncludes(
			new String[] {
				"firstEventDate", "lastEventDate", "id", "individualId",
				"userId"
			}
		).iterate();
	}

	private void _upgradeActivities(
		String firstEventDate, String individualId, String lastEventDate,
		String sessionId, String userId)
		throws Exception {

		ElasticsearchInvoker faroInfoElasticsearchInvoker =
			_elasticsearchInvokerFactory.forFaroInfo();

		ElasticsearchBulkRequestBuilder elasticsearchBulkRequestBuilder =
			faroInfoElasticsearchInvoker.
				createElasticsearchBulkRequestBuilder();

		JSONArrayIterator.of(
			"activities", faroInfoElasticsearchInvoker,
			activityJSONObject -> elasticsearchBulkRequestBuilder.update(
				"activities",
				JSONUtil.put(
					"id", activityJSONObject.getString("id")
				).put(
					"sessionId", sessionId
				)
			)
		).setQueryBuilder(
			BoolQueryBuilderUtil.filter(
				QueryBuilders.rangeQuery(
					"endTime"
				).gte(
					firstEventDate
				).lte(
					lastEventDate
				)
			).filter(
				QueryBuilders.termQuery("ownerId", individualId)
			).mustNot(
				QueryBuilders.existsQuery("sessionId")
			).filter(
				QueryBuilders.termQuery("userId", userId)
			)
		).iterate();
	}

	private static final Log _log = LogFactory.getLog(
		AssignSessionActivitiesArm.class);

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

}
