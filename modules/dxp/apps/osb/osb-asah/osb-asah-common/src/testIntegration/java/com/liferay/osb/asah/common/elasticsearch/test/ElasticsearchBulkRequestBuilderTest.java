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

package com.liferay.osb.asah.common.elasticsearch.test;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.OSBAsahCommonSpringTestContext;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchBulkRequestBuilder;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import java.util.Arrays;

import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.support.WriteRequest;

import org.json.JSONObject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Rachael Koestartyo
 */
@Disabled
public class ElasticsearchBulkRequestBuilderTest
	implements OSBAsahCommonSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@Test
	public void test() {
		DataSource liferayDataSource = _dataSourceRepository.save(
			FaroInfoTestUtil.buildLiferayDataSource());

		JSONObject individualJSONObject1 = _objectMapper.convertValue(
			FaroInfoTestUtil.buildIndividual(liferayDataSource),
			JSONObject.class);
		JSONObject individualJSONObject2 = _objectMapper.convertValue(
			FaroInfoTestUtil.buildIndividual(liferayDataSource),
			JSONObject.class);

		ElasticsearchBulkRequestBuilder elasticsearchBulkRequestBuilder =
			_elasticsearchInvoker.createElasticsearchBulkRequestBuilder();

		BulkResponse bulkResponse = elasticsearchBulkRequestBuilder.add(
			"individuals", individualJSONObject1.put("id", "1")
		).add(
			"individuals", individualJSONObject2.put("id", "2")
		).get();

		Assertions.assertFalse(bulkResponse.hasFailures());

		Assertions.assertFalse(elasticsearchBulkRequestBuilder.hasActions());

		Assertions.assertTrue(_elasticsearchInvoker.exists("individuals", "1"));
		Assertions.assertTrue(_elasticsearchInvoker.exists("individuals", "2"));

		individualJSONObject1 = _elasticsearchInvoker.get("individuals", "1");
		individualJSONObject2 = _elasticsearchInvoker.get("individuals", "2");

		bulkResponse = elasticsearchBulkRequestBuilder.update(
			"individuals", individualJSONObject1.put("test", "test1")
		).update(
			"individuals", individualJSONObject2.put("test", "test2")
		).get();

		individualJSONObject1 = _elasticsearchInvoker.get("individuals", "1");
		individualJSONObject2 = _elasticsearchInvoker.get("individuals", "2");

		Assertions.assertFalse(bulkResponse.hasFailures());
		Assertions.assertFalse(elasticsearchBulkRequestBuilder.hasActions());

		Assertions.assertEquals(
			"test1", individualJSONObject1.getString("test"));
		Assertions.assertEquals(
			"test2", individualJSONObject2.getString("test"));

		bulkResponse = elasticsearchBulkRequestBuilder.delete(
			"individuals", individualJSONObject1.getString("id")
		).delete(
			"individuals", individualJSONObject2.getString("id")
		).get();

		Assertions.assertFalse(bulkResponse.hasFailures());

		Assertions.assertFalse(elasticsearchBulkRequestBuilder.hasActions());

		Assertions.assertFalse(
			_elasticsearchInvoker.exists("individuals", "1"));
		Assertions.assertFalse(
			_elasticsearchInvoker.exists("individuals", "2"));
	}

	@Test
	public void testUpsertInsert() {
		ElasticsearchBulkRequestBuilder elasticsearchBulkRequestBuilder =
			_elasticsearchInvoker.createElasticsearchBulkRequestBuilder();

		elasticsearchBulkRequestBuilder.refreshPolicy(
			WriteRequest.RefreshPolicy.IMMEDIATE);

		JSONObject assetJSONObject = FaroInfoTestUtil.buildAssetJSONObject(
			"Page", Long.valueOf(RandomTestUtil.randomId()));

		BulkResponse bulkResponse = elasticsearchBulkRequestBuilder.upsert(
			"assets", assetJSONObject
		).get();

		BulkItemResponse[] bulkItemResponses = bulkResponse.getItems();

		Assertions.assertEquals(
			1, bulkItemResponses.length, Arrays.toString(bulkItemResponses));

		BulkItemResponse bulkItemResponse = bulkItemResponses[0];

		Assertions.assertFalse(bulkItemResponse.isFailed());
	}

	@Test
	public void testUpsertUpdate() {
		JSONObject assetJSONObject = _elasticsearchInvoker.add(
			"assets",
			FaroInfoTestUtil.buildAssetJSONObject(
				"Page", Long.valueOf(RandomTestUtil.randomId())));

		ElasticsearchBulkRequestBuilder elasticsearchBulkRequestBuilder =
			_elasticsearchInvoker.createElasticsearchBulkRequestBuilder();

		elasticsearchBulkRequestBuilder.refreshPolicy(
			WriteRequest.RefreshPolicy.IMMEDIATE);

		String assetName = RandomTestUtil.randomMultipleWordString(5, 20);

		assetJSONObject.put("name", assetName);

		BulkResponse bulkResponse = elasticsearchBulkRequestBuilder.upsert(
			"assets", assetJSONObject
		).get();

		Assertions.assertFalse(bulkResponse.hasFailures());
	}

	@Autowired
	private DataSourceRepository _dataSourceRepository;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private ObjectMapper _objectMapper;

}