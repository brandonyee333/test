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

package com.liferay.osb.asah.upgrade.v2_7_0;

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchIndexManager;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.upgrade.UpgradeStep;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Gabriel Ibson
 */
@Component
public class CanonicalUrlMappingUpgradeStep implements UpgradeStep {

	@Override
	public void upgrade(String version) {
		_cerebroInfoElasticsearchInvoker =
			_elasticsearchInvokerFactory.forCerebroInfo();
		_faroInfoElasticsearchInvoker =
			_elasticsearchInvokerFactory.forFaroInfo();

		for (String collectionName : _ASSET_COLLECTION_NAMES) {
			_addMappingField(collectionName, "canonicalUrls");
			_addMappingField(collectionName, "tempUrls");
		}

		_addMappingField(
			"activities",
			JSONUtil.put(
				"properties",
				JSONUtil.put(
					"object",
					JSONUtil.put(
						"properties",
						JSONUtil.put(
							"canonicalUrl",
							JSONUtil.put(
								"normalizer", "case_insensitive_sort"
							).put(
								"type", "keyword"
							))))),
			WeDeployDataService.OSB_ASAH_FARO_INFO);
		_addMappingField("page-referrers", "canonicalUrl");
		_addMappingField(
			"visited-pages",
			JSONUtil.put(
				"properties",
				JSONUtil.put(
					"canonicalUrl",
					JSONUtil.put(
						"normalizer", "case_insensitive_sort"
					).put(
						"type", "keyword"
					))),
			WeDeployDataService.OSB_ASAH_FARO_INFO);
	}

	private void _addMappingField(
		String collectionName, JSONObject mappingJSONObject,
		WeDeployDataService weDeployDataService) {

		_elasticsearchIndexManager.updateMapping(
			collectionName, mappingJSONObject.toString(), collectionName,
			weDeployDataService);
	}

	private void _addMappingField(String collectionName, String propertyName) {
		_addMappingField(
			collectionName,
			JSONUtil.put(
				"properties",
				JSONUtil.put(propertyName, JSONUtil.put("type", "keyword"))),
			WeDeployDataService.OSB_ASAH_CEREBRO_INFO);
	}

	private static final String[] _ASSET_COLLECTION_NAMES = {
		"blog-clicks", "blog-social-shares", "blog-traffic-sources", "blogs",
		"custom-assets", "document-libraries", "forms", "journal-clicks",
		"journals"
	};

	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;

	@Autowired
	private ElasticsearchIndexManager _elasticsearchIndexManager;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

}