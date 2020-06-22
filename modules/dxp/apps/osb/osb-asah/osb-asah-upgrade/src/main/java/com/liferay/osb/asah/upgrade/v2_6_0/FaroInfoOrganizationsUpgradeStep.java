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

package com.liferay.osb.asah.upgrade.v2_6_0;

import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchBulkRequestBuilder;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.json.JSONArrayIterator;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.upgrade.UpgradeStep;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Rachael Koestartyo
 */
@Component
public class FaroInfoOrganizationsUpgradeStep implements UpgradeStep {

	@Override
	public void upgrade(String version) throws Exception {
		ElasticsearchInvoker faroInfoElasticsearchInvoker =
			_elasticsearchInvokerFactory.forFaroInfo();

		JSONArray dataSourcesJSONArray = faroInfoElasticsearchInvoker.get(
			"data-sources",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("provider.type", "LIFERAY")
			).filter(
				QueryBuilders.termQuery("status", "ACTIVE")
			));

		List<String> dataSourceIds = JSONUtil.toStringList(
			dataSourcesJSONArray, "id");

		ElasticsearchBulkRequestBuilder elasticsearchBulkRequestBuilder =
			faroInfoElasticsearchInvoker.
				createElasticsearchBulkRequestBuilder();

		elasticsearchBulkRequestBuilder.refreshPolicy(
			WriteRequest.RefreshPolicy.IMMEDIATE);

		JSONArrayIterator.of(
			"organizations", faroInfoElasticsearchInvoker,
			jsonObject -> {
				_updateIndividuals(
					faroInfoElasticsearchInvoker, jsonObject.getString("id"));

				elasticsearchBulkRequestBuilder.delete(
					"organizations", jsonObject.getString("id"));

				return elasticsearchBulkRequestBuilder;
			}
		).setQueryBuilder(
			BoolQueryBuilderUtil.mustNot(
				QueryBuilders.termsQuery("dataSourceId", dataSourceIds))
		).setStopOnExceptions(
			false
		).iterate();
	}

	private void _updateIndividuals(
			ElasticsearchInvoker elasticsearchInvoker, String organizationId)
		throws Exception {

		JSONArrayIterator.of(
			"individuals", elasticsearchInvoker,
			individualJSONObject -> {
				JSONObject modifiedJSONObject = new JSONObject();

				JSONArray idsJSONArray = individualJSONObject.getJSONArray(
					"organizationIds");

				Iterator<Object> iterator = idsJSONArray.iterator();

				while (iterator.hasNext()) {
					String id = (String)iterator.next();

					if (StringUtils.equals(id, organizationId)) {
						iterator.remove();
					}
				}

				modifiedJSONObject.put("organizationIds", idsJSONArray);

				elasticsearchInvoker.update(
					"individuals", individualJSONObject.getString("id"),
					modifiedJSONObject);

				return null;
			}
		).setQueryBuilder(
			QueryBuilders.termQuery("organizationIds", organizationId)
		).iterate();
	}

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

}