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
import com.liferay.osb.asah.common.json.JSONArrayIterator;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.upgrade.UpgradeStep;

import java.util.Collections;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.Script;

import org.springframework.stereotype.Component;

/**
 * @author Leslie Wong
 */
@Component
public class AssetsUpgradeStep implements UpgradeStep {

	@Override
	public void upgrade(String version) throws Exception {
		ElasticsearchBulkRequestBuilder elasticsearchBulkRequestBuilder =
			_elasticsearchInvoker.createElasticsearchBulkRequestBuilder();

		JSONArrayIterator.of(
			"assets", _elasticsearchInvoker,
			assetJSONObject -> elasticsearchBulkRequestBuilder.update(
				"assets", assetJSONObject.getString("id"),
				new Script(
					Script.DEFAULT_SCRIPT_TYPE, Script.DEFAULT_SCRIPT_LANG,
					"ctx._source.canonicalUrl = params.canonicalURL",
					Collections.singletonMap(
						"canonicalURL",
						assetJSONObject.getString("canonicalURL"))))
		).setQueryBuilder(
			BoolQueryBuilderUtil.filter(
				QueryBuilders.existsQuery("canonicalURL")
			).mustNot(
				QueryBuilders.existsQuery("canonicalUrl")
			)
		).iterate();
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

}