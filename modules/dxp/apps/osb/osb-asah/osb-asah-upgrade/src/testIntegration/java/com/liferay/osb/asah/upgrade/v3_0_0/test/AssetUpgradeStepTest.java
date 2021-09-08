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

package com.liferay.osb.asah.upgrade.v3_0_0.test;

import com.liferay.osb.asah.common.elasticsearch.ClientUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchConnection;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchIndexManager;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchIndexUtil;
import com.liferay.osb.asah.common.entity.Asset;
import com.liferay.osb.asah.common.entity.AssetKeyword;
import com.liferay.osb.asah.common.repository.AssetRepository;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.util.ReleaseInfo;
import com.liferay.osb.asah.common.util.SetUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;
import com.liferay.osb.asah.upgrade.spring.OSBAsahUpgradeSpringBootApplication;
import com.liferay.osb.asah.upgrade.v3_0_0.AssetsUpgradeStep;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.elasticsearch.action.admin.indices.alias.Alias;
import org.elasticsearch.action.admin.indices.template.delete.DeleteIndexTemplateRequestBuilder;
import org.elasticsearch.action.admin.indices.template.put.PutIndexTemplateRequestBuilder;
import org.elasticsearch.client.AdminClient;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.IndicesAdminClient;

import org.json.JSONObject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

/**
 * @author Inácio Nery
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OSBAsahUpgradeSpringBootApplication.class)
public class AssetUpgradeStepTest {

	@Test
	public void testUpgrade() throws Exception {
		_elasticsearchIndexManager.delete(
			ElasticsearchIndexUtil.getIndexName(
				"assets", WeDeployDataService.OSB_ASAH_FARO_INFO));

		_deleteAssetsTemplate();

		_addAssetsTemplate();

		_elasticsearchIndexManager.create(
			true,
			ResourceUtil.readResourceToString(
				"dependencies/old_assets_index_configuration.json", this),
			ElasticsearchIndexUtil.getIndexName(
				"assets", WeDeployDataService.OSB_ASAH_FARO_INFO));

		Asset asset = new Asset();

		asset.setAssetType("Page");
		asset.setAssetKeywords(
			SetUtil.of(new AssetKeyword("holistic ROI", "title")));

		_assetRepository.save(asset);

		List<String> keywords = _assetRepository.findKeywordByAssetType("Page");

		Assert.assertEquals(keywords.toString(), 0, keywords.size());

		_assetsUpgradeStep.upgrade("");

		for (String cacheName : _cacheManager.getCacheNames()) {
			Cache cache = _cacheManager.getCache(cacheName);

			if (cache != null) {
				cache.invalidate();
			}
		}

		keywords = _assetRepository.findKeywordByAssetType("Page");

		Assert.assertEquals(keywords.toString(), 1, keywords.size());

		Assert.assertEquals(Arrays.asList("holistic ROI"), keywords);
	}

	private void _addAssetsTemplate() throws Exception {
		Client client = _elasticsearchConnection.getTransportClient();

		AdminClient adminClient = client.admin();

		IndicesAdminClient indicesAdminClient = adminClient.indices();

		PutIndexTemplateRequestBuilder putIndexTemplateRequestBuilder =
			indicesAdminClient.preparePutTemplate("osbasahfaroinfo_assets");

		putIndexTemplateRequestBuilder.addAlias(new Alias("{index}_alias"));
		putIndexTemplateRequestBuilder.setPatterns(
			Collections.singletonList("*_assets"));

		JSONObject indexConfigurationJSONObject =
			ResourceUtil.readResourceToJSONObject(
				"dependencies/old_assets_index_configuration.json", this);

		putIndexTemplateRequestBuilder.setSource(
			indexConfigurationJSONObject.toMap());

		putIndexTemplateRequestBuilder.setVersion(
			ReleaseInfo.getSchemaVersion());

		ClientUtil.waitForConnection(client);

		putIndexTemplateRequestBuilder.get();
	}

	private void _deleteAssetsTemplate() {
		Client client = _elasticsearchConnection.getTransportClient();

		AdminClient adminClient = client.admin();

		IndicesAdminClient indicesAdminClient = adminClient.indices();

		DeleteIndexTemplateRequestBuilder deleteIndexTemplateRequestBuilder =
			indicesAdminClient.prepareDeleteTemplate("osbasahfaroinfo_assets");

		ClientUtil.waitForConnection(client);

		deleteIndexTemplateRequestBuilder.get();
	}

	@Autowired
	private AssetRepository _assetRepository;

	@Autowired
	private AssetsUpgradeStep _assetsUpgradeStep;

	@Autowired
	private CacheManager _cacheManager;

	@Autowired
	private ElasticsearchConnection _elasticsearchConnection;

	@Autowired
	private ElasticsearchIndexManager _elasticsearchIndexManager;

}