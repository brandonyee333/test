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
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.util.ReleaseInfo;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;
import com.liferay.osb.asah.upgrade.spring.OSBAsahUpgradeSpringBootApplication;
import com.liferay.osb.asah.upgrade.v3_0_0.IndividualsUpgradeStep;

import java.util.Collections;
import java.util.Map;

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

import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Marcos Martins
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OSBAsahUpgradeSpringBootApplication.class)
public class IndividualsUpgradeStepTest {

	@Test
	public void testUpgrade() throws Exception {
		_elasticsearchIndexManager.delete(
			ElasticsearchIndexUtil.getIndexName(
				"individuals", WeDeployDataService.OSB_ASAH_FARO_INFO));

		_deleteIndividualsTemplate();

		_addIndividualsTemplate();

		_elasticsearchIndexManager.create(
			true,
			ResourceUtil.readResourceToString(
				"dependencies/old_individuals_index_configuration.json", this),
			ElasticsearchIndexUtil.getIndexName(
				"individuals", WeDeployDataService.OSB_ASAH_FARO_INFO));

		Map<String, Object> indexMappings =
			_elasticsearchIndexManager.getIndexMappings(
				"individuals", WeDeployDataService.OSB_ASAH_FARO_INFO);

		Map<String, Object> properties = (Map<String, Object>)indexMappings.get(
			"properties");

		Assert.assertFalse(properties.containsKey("previousActivityDates"));

		_individualsUpgradeStep.upgrade("");

		indexMappings = _elasticsearchIndexManager.getIndexMappings(
			"individuals", WeDeployDataService.OSB_ASAH_FARO_INFO);

		properties = (Map<String, Object>)indexMappings.get("properties");

		Assert.assertTrue(properties.containsKey("previousActivityDates"));

		JSONAssert.assertEquals(
			JSONUtil.put(
				"properties",
				JSONUtil.put(
					"channelId", JSONUtil.put("type", "keyword")
				).put(
					"lastActivityDate", JSONUtil.put("type", "date")
				)
			).put(
				"type", "nested"
			),
			new JSONObject(
				(Map<String, Object>)properties.get("previousActivityDates")),
			false);
	}

	private void _addIndividualsTemplate() throws Exception {
		Client client = _elasticsearchConnection.getTransportClient();

		AdminClient adminClient = client.admin();

		IndicesAdminClient indicesAdminClient = adminClient.indices();

		PutIndexTemplateRequestBuilder putIndexTemplateRequestBuilder =
			indicesAdminClient.preparePutTemplate(
				"osbasahfaroinfo_individuals");

		putIndexTemplateRequestBuilder.addAlias(new Alias("{index}_alias"));
		putIndexTemplateRequestBuilder.setPatterns(
			Collections.singletonList("*_osbasahfaroinfo_individuals"));

		JSONObject indexConfigurationJSONObject =
			ResourceUtil.readResourceToJSONObject(
				"dependencies/old_individuals_index_configuration.json", this);

		putIndexTemplateRequestBuilder.setSource(
			indexConfigurationJSONObject.toMap());

		putIndexTemplateRequestBuilder.setVersion(
			ReleaseInfo.getSchemaVersion());

		ClientUtil.waitForConnection(client);

		putIndexTemplateRequestBuilder.get();
	}

	private void _deleteIndividualsTemplate() {
		Client client = _elasticsearchConnection.getTransportClient();

		AdminClient adminClient = client.admin();

		IndicesAdminClient indicesAdminClient = adminClient.indices();

		DeleteIndexTemplateRequestBuilder deleteIndexTemplateRequestBuilder =
			indicesAdminClient.prepareDeleteTemplate(
				"osbasahfaroinfo_individuals");

		ClientUtil.waitForConnection(client);

		deleteIndexTemplateRequestBuilder.get();
	}

	@Autowired
	private ElasticsearchConnection _elasticsearchConnection;

	@Autowired
	private ElasticsearchIndexManager _elasticsearchIndexManager;

	@Autowired
	private IndividualsUpgradeStep _individualsUpgradeStep;

}