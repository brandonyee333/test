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

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchBulkRequestBuilder;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import org.elasticsearch.action.bulk.BulkResponse;

import org.json.JSONObject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Rachael Koestartyo
 */
@ContextConfiguration(classes = OSBAsahSpringBootApplication.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class ElasticsearchBulkRequestBuilderTest {

	@Before
	public void setUp() {
		_elasticsearchInvoker = _elasticsearchInvokerFactory.forFaroInfo();
	}

	@Test
	public void test() {
		JSONObject liferayDataSourceJSONObject =
			FaroInfoTestUtil.buildLiferayDataSourceJSONObject();

		JSONObject individualJSONObject1 =
			FaroInfoTestUtil.buildIndividualJSONObject(
				liferayDataSourceJSONObject.put("id", "dataSource1"));
		JSONObject individualJSONObject2 =
			FaroInfoTestUtil.buildIndividualJSONObject(
				liferayDataSourceJSONObject.put("id", "dataSource1"));

		ElasticsearchBulkRequestBuilder elasticsearchBulkRequestBuilder =
			_elasticsearchInvoker.createElasticsearchBulkRequestBuilder();

		BulkResponse bulkResponse = elasticsearchBulkRequestBuilder.add(
			"individuals", individualJSONObject1.put("id", "1")
		).add(
			"individuals", individualJSONObject2.put("id", "2")
		).get();

		Assert.assertFalse(elasticsearchBulkRequestBuilder.hasActions());

		Assert.assertFalse(bulkResponse.hasFailures());

		Assert.assertTrue(_elasticsearchInvoker.exists("individuals", "1"));
		Assert.assertTrue(_elasticsearchInvoker.exists("individuals", "2"));

		individualJSONObject1 = _elasticsearchInvoker.get("individuals", "1");
		individualJSONObject2 = _elasticsearchInvoker.get("individuals", "2");

		bulkResponse = elasticsearchBulkRequestBuilder.update(
			"individuals", individualJSONObject1.put("test", "test1")
		).update(
			"individuals", individualJSONObject2.put("test", "test2")
		).get();

		individualJSONObject1 = _elasticsearchInvoker.get("individuals", "1");
		individualJSONObject2 = _elasticsearchInvoker.get("individuals", "2");

		Assert.assertFalse(elasticsearchBulkRequestBuilder.hasActions());

		Assert.assertFalse(bulkResponse.hasFailures());

		Assert.assertEquals("test1", individualJSONObject1.getString("test"));
		Assert.assertEquals("test2", individualJSONObject2.getString("test"));

		bulkResponse = elasticsearchBulkRequestBuilder.delete(
			"individuals", individualJSONObject1.getString("id")
		).delete(
			"individuals", individualJSONObject2.getString("id")
		).get();

		Assert.assertFalse(elasticsearchBulkRequestBuilder.hasActions());

		Assert.assertFalse(bulkResponse.hasFailures());

		Assert.assertFalse(_elasticsearchInvoker.exists("individuals", "1"));
		Assert.assertFalse(_elasticsearchInvoker.exists("individuals", "2"));
	}

	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

}