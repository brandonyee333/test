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

package com.liferay.osb.asah.upgrade.v2_5_0;

import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchBulkRequestBuilder;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchIndexManager;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.json.JSONArrayIterator;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.upgrade.UpgradeStep;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.QueryBuilders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Matthew Kong
 */
@Component
public class FieldMappingsUpgradeStep implements UpgradeStep {

	@Override
	public void upgrade(String version) throws Exception {
		if (_log.isInfoEnabled()) {
			_log.info("Updating field mappings");
		}

		_elasticsearchIndexManager.updateMapping(
			"field-mappings",
			JSONUtil.put(
				"properties",
				JSONUtil.put(
					"displayName", JSONUtil.put("type", "keyword")
				).put(
					"displayType", JSONUtil.put("type", "keyword")
				)
			).toString(),
			"field-mappings", WeDeployDataService.OSB_ASAH_FARO_INFO);

		ElasticsearchInvoker faroInfoElasticsearchInvoker =
			_elasticsearchInvokerFactory.forFaroInfo();

		ElasticsearchBulkRequestBuilder elasticsearchBulkRequestBuilder =
			faroInfoElasticsearchInvoker.
				createElasticsearchBulkRequestBuilder();

		JSONArrayIterator.of(
			"field-mappings", faroInfoElasticsearchInvoker,
			fieldMappingJSONObject -> elasticsearchBulkRequestBuilder.update(
				"field-mappings",
				fieldMappingJSONObject.put(
					"displayName",
					fieldMappingJSONObject.getString("fieldName")))
		).setQueryBuilder(
			BoolQueryBuilderUtil.mustNot(
				QueryBuilders.existsQuery("displayName"))
		).setStopOnExceptions(
			false
		).iterate();
	}

	private static final Log _log = LogFactory.getLog(
		FieldMappingsUpgradeStep.class);

	@Autowired
	private ElasticsearchIndexManager _elasticsearchIndexManager;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

}