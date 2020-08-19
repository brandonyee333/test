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

package com.liferay.osb.asah.upgrade.v2_7_3;

import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchBulkRequestBuilder;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.json.JSONArrayIterator;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.upgrade.UpgradeStep;

import javax.annotation.PostConstruct;

import org.apache.lucene.search.join.ScoreMode;

import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Shinn Lok
 */
@Component
public class ActivitiesUpgradeStep implements UpgradeStep {

	@Override
	public void upgrade(String version) throws Exception {
		ElasticsearchBulkRequestBuilder elasticsearchBulkRequestBuilder =
			_elasticsearchInvoker.createElasticsearchBulkRequestBuilder();

		elasticsearchBulkRequestBuilder.refreshPolicy(
			WriteRequest.RefreshPolicy.IMMEDIATE);

		JSONArrayIterator.of(
			"activity-groups", _elasticsearchInvoker,
			activityGroupJSONObject -> {
				String individualId = _getIndividualId(
					activityGroupJSONObject.getString("dataSourceId"),
					activityGroupJSONObject.getString("userId"));

				if (individualId == null) {
					return elasticsearchBulkRequestBuilder;
				}

				elasticsearchBulkRequestBuilder.update(
					"activity-groups",
					JSONUtil.put(
						"id", activityGroupJSONObject.getString("id")
					).put(
						"ownerId", individualId
					));

				_upgradeActivities(
					activityGroupJSONObject.getString("id"), individualId);

				return elasticsearchBulkRequestBuilder;
			}
		).setQueryBuilder(
			BoolQueryBuilderUtil.mustNot(QueryBuilders.existsQuery("ownerId"))
		).iterate();
	}

	private String _getIndividualId(String dataSourceId, String userId) {
		JSONObject individualJSONObject = _elasticsearchInvoker.fetch(
			"individuals",
			QueryBuilders.nestedQuery(
				"dataSourceIndividualPKs",
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery(
						"dataSourceIndividualPKs.dataSourceId", dataSourceId)
				).filter(
					QueryBuilders.termQuery(
						"dataSourceIndividualPKs.individualPKs", userId)
				),
				ScoreMode.None));

		if (individualJSONObject == null) {
			return null;
		}

		return individualJSONObject.getString("id");
	}

	@PostConstruct
	private void _init() {
		_elasticsearchInvoker = _elasticsearchInvokerFactory.forFaroInfo();
	}

	private void _upgradeActivities(String groupId, String ownerId)
		throws Exception {

		ElasticsearchBulkRequestBuilder elasticsearchBulkRequestBuilder =
			_elasticsearchInvoker.createElasticsearchBulkRequestBuilder();

		elasticsearchBulkRequestBuilder.refreshPolicy(
			WriteRequest.RefreshPolicy.IMMEDIATE);

		JSONArrayIterator.of(
			"activities", _elasticsearchInvoker,
			activityJSONObject -> elasticsearchBulkRequestBuilder.update(
				"activities",
				JSONUtil.put(
					"id", activityJSONObject.getString("id")
				).put(
					"ownerId", ownerId
				))
		).setQueryBuilder(
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("groupId", groupId)
			).filter(
				QueryBuilders.termQuery("ownerId", "")
			)
		).iterate();
	}

	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

}