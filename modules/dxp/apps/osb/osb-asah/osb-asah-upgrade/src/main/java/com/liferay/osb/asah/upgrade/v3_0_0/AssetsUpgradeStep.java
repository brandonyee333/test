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

package com.liferay.osb.asah.upgrade.v3_0_0;

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchIndexManager;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.upgrade.UpgradeStep;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Inácio Nery
 */
@Component
public class AssetsUpgradeStep implements UpgradeStep {

	@Override
	public void upgrade(String version) {
		_elasticsearchIndexManager.updateMapping(
			"assets",
			JSONUtil.put(
				"properties",
				JSONUtil.put(
					"keywords",
					JSONUtil.put(
						"properties",
						JSONUtil.put(
							"keyword",
							JSONUtil.put(
								"fields",
								JSONUtil.put(
									"keyword", JSONUtil.put("type", "keyword"))
							).put(
								"normalizer", "case_insensitive_sort"
							).put(
								"type", "keyword"
							)
						).put(
							"type", JSONUtil.put("type", "keyword")
						)))
			).toString(),
			"assets", WeDeployDataService.OSB_ASAH_FARO_INFO);

		_faroInfoElasticsearchInvoker.updateByQuery(null, true, null, "assets");
	}

	@Autowired
	private ElasticsearchIndexManager _elasticsearchIndexManager;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

}