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

import com.liferay.osb.asah.common.OSBAsahCommonSpringTestContext;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchAliases;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchBulkRequestBuilder;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchConnection;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchIndexManager;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchIndexUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.HitsUtil;
import com.liferay.osb.asah.common.elasticsearch.impl.ElasticsearchInvokerImpl;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import java.util.Collections;

import org.apache.commons.lang3.RandomUtils;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.metrics.Sum;
import org.elasticsearch.search.aggregations.metrics.SumAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Marcellus Tavares
 * @author Rachael Koestartyo
 */
public class ElasticsearchInvokerTest
	implements OSBAsahCommonSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@BeforeEach
	public void setUp() {
		_elasticsearchInvoker = new ElasticsearchInvokerImpl(
			_elasticsearchConnection.getTransportClient(),
			new ElasticsearchAliases(
				_elasticsearchIndexManager,
				WeDeployDataService.OSB_ASAH_FARO_INFO),
			WeDeployDataService.OSB_ASAH_FARO_INFO);

		_collectionName = RandomTestUtil.randomString();

		_indexName = ElasticsearchIndexUtil.getIndexName(
			_collectionName, WeDeployDataService.OSB_ASAH_FARO_INFO);

		_elasticsearchIndexManager.create(
			true, _createIndexConfiguration(), _indexName);
	}

	@AfterEach
	public void tearDown() {
		_elasticsearchIndexManager.delete(_indexName);
	}

	@Test
	public void testAddJSONArray() {
		JSONArray expectedJSONArray = JSONUtil.putAll(
			JSONUtil.put("string", RandomTestUtil.randomHexString(4096)),
			JSONUtil.put("string", RandomTestUtil.randomHexString(4096)));

		_elasticsearchInvoker.add(_collectionName, expectedJSONArray);

		JSONArray actualJSONArray = _elasticsearchInvoker.get(_collectionName);

		JSONAssert.assertEquals(expectedJSONArray, actualJSONArray, false);
	}

	@Test
	public void testAddJSONArrayWithDelta() {
		JSONArray expectedJSONArray = JSONUtil.putAll(
			JSONUtil.put("string", RandomTestUtil.randomHexString(4096)),
			JSONUtil.put("string", RandomTestUtil.randomHexString(4096)));

		_elasticsearchInvoker.add(_collectionName, expectedJSONArray, 1);

		JSONArray actualJSONArray = _elasticsearchInvoker.get(_collectionName);

		JSONAssert.assertEquals(expectedJSONArray, actualJSONArray, false);
	}

	@Test
	public void testAddJSONObject() {
		JSONObject expectedJSONObject = JSONUtil.put(
			"string", RandomTestUtil.randomHexString(4096));

		expectedJSONObject = _elasticsearchInvoker.add(
			_collectionName, expectedJSONObject);

		JSONObject actualJSONObject = _elasticsearchInvoker.get(
			_collectionName, expectedJSONObject.getString("id"));

		JSONAssert.assertEquals(expectedJSONObject, actualJSONObject, false);
	}

	@Test
	public void testAddMustGenerateId() {
		JSONObject actualJSONObject = _elasticsearchInvoker.add(
			_collectionName,
			JSONUtil.put("string", RandomTestUtil.randomHexString(4096)));

		Assertions.assertNotNull(actualJSONObject.getString("id"));
	}

	@Test
	public void testCount() {
		String testString = RandomTestUtil.randomHexString(4096);

		_elasticsearchInvoker.add(
			_collectionName, JSONUtil.put("string", testString));

		Assertions.assertEquals(
			1,
			_elasticsearchInvoker.count(
				_collectionName,
				QueryBuilders.termQuery("string", testString)));
	}

	@Test
	public void testDeleteByQuery() {
		ElasticsearchBulkRequestBuilder elasticsearchBulkRequestBuilder =
			_elasticsearchInvoker.createElasticsearchBulkRequestBuilder();

		elasticsearchBulkRequestBuilder.refreshPolicy(
			WriteRequest.RefreshPolicy.IMMEDIATE);

		for (int i = 0; i < 15000; i++) {
			elasticsearchBulkRequestBuilder.add(
				_collectionName,
				JSONUtil.put("string", RandomTestUtil.randomHexString(4096)));
		}

		elasticsearchBulkRequestBuilder.get();

		Assertions.assertEquals(
			15000,
			_elasticsearchInvoker.count(
				_collectionName, QueryBuilders.matchAllQuery()));

		_elasticsearchInvoker.deleteByQuery(
			QueryBuilders.matchAllQuery(), true, _collectionName);

		Assertions.assertEquals(
			0,
			_elasticsearchInvoker.count(
				_collectionName, QueryBuilders.matchAllQuery()));
	}

	@Test
	public void testExistsDocument() {
		JSONObject jsonObject = _elasticsearchInvoker.add(
			_collectionName,
			JSONUtil.put("string", RandomTestUtil.randomHexString(4096)));

		Assertions.assertTrue(
			_elasticsearchInvoker.exists(
				_collectionName,
				QueryBuilders.termQuery("id", jsonObject.getString("id"))));
	}

	@Test
	public void testFetchWithExistentDocument() {
		JSONObject jsonObject = _elasticsearchInvoker.add(
			_collectionName,
			JSONUtil.put("string", RandomTestUtil.randomHexString(4096)));

		Assertions.assertNotNull(
			_elasticsearchInvoker.fetch(
				_collectionName,
				QueryBuilders.termQuery("id", jsonObject.getString("id"))));
	}

	@Test
	public void testFetchWithNonexistentDocument() {
		_elasticsearchInvoker.add(
			_collectionName,
			JSONUtil.put("string", RandomTestUtil.randomHexString(4096)));

		Assertions.assertNull(
			_elasticsearchInvoker.fetch(
				_collectionName,
				QueryBuilders.termQuery(
					"string", RandomTestUtil.randomString())));
	}

	@Test
	public void testFieldIndexLimit() {
		JSONObject expectedJSONObject = new JSONObject();

		for (int i = 0; i < 2490; i++) {
			expectedJSONObject.put(
				String.valueOf(i), RandomTestUtil.randomHexString(4096));
		}

		expectedJSONObject = _elasticsearchInvoker.add(
			_collectionName, expectedJSONObject);

		JSONObject actualJSONObject = _elasticsearchInvoker.get(
			_collectionName, expectedJSONObject.getString("id"));

		JSONAssert.assertEquals(expectedJSONObject, actualJSONObject, false);
	}

	@Test
	public void testFieldIndexLimitExceeded() {
		JSONObject expectedJSONObject = new JSONObject();

		for (int i = 0; i < 2500; i++) {
			expectedJSONObject.put(
				String.valueOf(i), RandomTestUtil.randomHexString(4096));
		}

		Assertions.assertThrows(
			IllegalArgumentException.class,
			() -> _elasticsearchInvoker.add(
				_collectionName, expectedJSONObject));
	}

	@Test
	public void testGetAll() {
		JSONArray expectedJSONArray = JSONUtil.put(
			JSONUtil.put("string", RandomTestUtil.randomString()));

		_elasticsearchInvoker.add(_collectionName, expectedJSONArray);

		JSONArray actualJSONArray = _elasticsearchInvoker.get(_collectionName);

		Assertions.assertEquals(1, actualJSONArray.length());

		JSONAssert.assertEquals(expectedJSONArray, actualJSONArray, false);
	}

	@Test
	public void testGetById() {
		JSONObject expectedJSONObject = _elasticsearchInvoker.add(
			_collectionName,
			JSONUtil.put("string", RandomTestUtil.randomHexString(4096)));

		JSONObject actualJSONObject = _elasticsearchInvoker.get(
			_collectionName, expectedJSONObject.getString("id"));

		JSONAssert.assertEquals(expectedJSONObject, actualJSONObject, false);
	}

	@Test
	public void testGetByQuery() {
		String testString = RandomTestUtil.randomHexString(4096);

		JSONArray expectedJSONArray = JSONUtil.put(
			JSONUtil.put("string", testString));

		_elasticsearchInvoker.add(_collectionName, expectedJSONArray);

		JSONArray actualJSONArray = _elasticsearchInvoker.get(
			_collectionName, QueryBuilders.termQuery("string", testString));

		Assertions.assertEquals(1, actualJSONArray.length());

		JSONAssert.assertEquals(expectedJSONArray, actualJSONArray, false);
	}

	@Test
	public void testReplace() {
		JSONObject jsonObject = _elasticsearchInvoker.add(
			_collectionName,
			JSONUtil.put(
				"string", RandomTestUtil.randomHexString(4096)
			).put(
				"value", RandomTestUtil.randomNumber()
			));

		String id = jsonObject.getString("id");

		String newTestString = RandomTestUtil.randomHexString(4096);

		_elasticsearchInvoker.replace(
			_collectionName,
			JSONUtil.put(
				"id", id
			).put(
				"string", newTestString
			));

		JSONObject actualJSONObject = _elasticsearchInvoker.get(
			_collectionName, id);

		Assertions.assertEquals(
			newTestString, actualJSONObject.getString("string"));
		Assertions.assertEquals(-1, actualJSONObject.optLong("value", -1));
	}

	@Test
	public void testSaveWithExistentDocument() {
		JSONObject jsonObject = _elasticsearchInvoker.add(
			_collectionName,
			JSONUtil.put(
				"string", RandomTestUtil.randomHexString(4096)
			).put(
				"value", RandomTestUtil.randomNumber()
			));

		String id = jsonObject.getString("id");

		String newTestString = RandomTestUtil.randomHexString(4096);

		_elasticsearchInvoker.save(
			_collectionName,
			JSONUtil.put(
				"id", id
			).put(
				"string", newTestString
			));

		JSONObject actualJSONObject = _elasticsearchInvoker.get(
			_collectionName, id);

		Assertions.assertEquals(
			newTestString, actualJSONObject.getString("string"));
		Assertions.assertEquals(-1, actualJSONObject.optLong("value", -1));
	}

	@Test
	public void testSaveWithNonexistentDocument() {
		String testString = RandomTestUtil.randomHexString(4096);

		JSONObject jsonObject = _elasticsearchInvoker.save(
			_collectionName, JSONUtil.put("string", testString));

		JSONObject actualJSONObject = _elasticsearchInvoker.get(
			_collectionName, jsonObject.getString("id"));

		Assertions.assertEquals(
			testString, actualJSONObject.getString("string"));
	}

	@Test
	public void testSearch() {
		int testNumber1 = RandomUtils.nextInt(0, 100);
		int testNumber2 = RandomUtils.nextInt(0, 100);
		int testNumber3 = RandomUtils.nextInt(0, 100);

		int testNumberSum = testNumber1 + testNumber2 + testNumber3;

		_elasticsearchInvoker.add(
			_collectionName,
			JSONUtil.putAll(
				JSONUtil.put("value", testNumber1),
				JSONUtil.put("value", testNumber2),
				JSONUtil.put("value", testNumber3)));

		SearchSourceBuilder searchSourceBuilder =
			SearchSourceBuilder.searchSource();

		SumAggregationBuilder sumAggregationBuilder = AggregationBuilders.sum(
			"sum");

		sumAggregationBuilder.field("value");

		searchSourceBuilder.aggregation(sumAggregationBuilder);

		searchSourceBuilder.size(0);

		SearchResponse searchResponse = _elasticsearchInvoker.search(
			_collectionName, searchSourceBuilder);

		SearchHits searchHits = searchResponse.getHits();

		Assertions.assertEquals(3, HitsUtil.getTotalHitsCount(searchHits));

		SearchHit[] searchHitsArray = searchHits.getHits();

		int searchHitsArrayLength = searchHitsArray.length;

		Assertions.assertEquals(0, searchHitsArrayLength);

		Aggregations aggregations = searchResponse.getAggregations();

		Sum sum = aggregations.get("sum");

		Assertions.assertEquals(testNumberSum, (int)sum.getValue());
	}

	@Test
	public void testUpdate() {
		String testString = RandomTestUtil.randomHexString(4096);

		JSONObject jsonObject = _elasticsearchInvoker.add(
			_collectionName, JSONUtil.put("string", testString));

		String id = jsonObject.getString("id");

		long testNumber = RandomTestUtil.randomNumber();

		_elasticsearchInvoker.update(
			_collectionName, id, JSONUtil.put("value", testNumber));

		JSONObject actualJSONObject = _elasticsearchInvoker.get(
			_collectionName, id);

		Assertions.assertEquals(
			testString, actualJSONObject.getString("string"));
		Assertions.assertEquals(testNumber, actualJSONObject.getLong("value"));
	}

	@Test
	public void testUpdateByQuery() {
		_elasticsearchInvoker.add(
			_collectionName,
			JSONUtil.putAll(
				JSONUtil.put("string", "string1"),
				JSONUtil.put("string", "string2"),
				JSONUtil.put("string", "string3")));

		BulkByScrollResponse bulkByScrollResponse =
			_elasticsearchInvoker.updateByQuery(
				QueryBuilders.termQuery("string", "string2"), true,
				new Script(
					Script.DEFAULT_SCRIPT_TYPE, Script.DEFAULT_SCRIPT_LANG,
					"ctx._source.string = params.string",
					Collections.singletonMap("string", "string0")),
				_collectionName);

		Assertions.assertEquals(1, bulkByScrollResponse.getUpdated());
		Assertions.assertEquals(0, bulkByScrollResponse.getVersionConflicts());

		JSONArray actualJSONArray = _elasticsearchInvoker.get(_collectionName);

		JSONAssert.assertEquals(
			JSONUtil.putAll(
				JSONUtil.put("string", "string1"),
				JSONUtil.put("string", "string0"),
				JSONUtil.put("string", "string3")),
			actualJSONArray, false);
	}

	@Test
	public void testUpdateByQueryBatchesUpdate() {
		ElasticsearchBulkRequestBuilder elasticsearchBulkRequestBuilder =
			_elasticsearchInvoker.createElasticsearchBulkRequestBuilder();

		for (int i = 0; i < 15000; i++) {
			elasticsearchBulkRequestBuilder.add(
				_collectionName, JSONUtil.put("string", "string" + i));
		}

		elasticsearchBulkRequestBuilder.get();

		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.mustNot(
			QueryBuilders.existsQuery("test"));

		long expectedCount = _elasticsearchInvoker.count(
			_collectionName, boolQueryBuilder);

		BulkByScrollResponse bulkByScrollResponse =
			_elasticsearchInvoker.updateByQuery(
				boolQueryBuilder, true,
				new Script(
					Script.DEFAULT_SCRIPT_TYPE, Script.DEFAULT_SCRIPT_LANG,
					"ctx._source.test = params.test",
					Collections.singletonMap("test", "test")),
				_collectionName);

		Assertions.assertEquals(
			(int)Math.ceil(expectedCount / 10000.0),
			bulkByScrollResponse.getBatches());
		Assertions.assertEquals(
			expectedCount, bulkByScrollResponse.getUpdated());
	}

	@Disabled
	@Test
	public void testUpdateWithConflict() {
		JSONObject jsonObject = _elasticsearchInvoker.add(
			_collectionName,
			JSONUtil.put("string", RandomTestUtil.randomHexString(4096)));

		String id = jsonObject.getString("id");

		long testNumber = RandomTestUtil.randomNumber();

		Thread thread = new Thread(
			() -> {
				Thread currentThread = Thread.currentThread();

				while (!currentThread.isInterrupted()) {
					_elasticsearchInvoker.update(
						_collectionName, id, JSONUtil.put("value", testNumber));
				}
			});

		thread.start();

		String testString = RandomTestUtil.randomHexString(4096);

		_elasticsearchInvoker.update(
			_collectionName, id, JSONUtil.put("string", testString));

		JSONObject actualJSONObject = _elasticsearchInvoker.fetch(
			_collectionName, id);

		Assertions.assertEquals(testNumber, actualJSONObject.getLong("value"));
		Assertions.assertEquals(
			testString, actualJSONObject.getString("string"));

		thread.interrupt();
	}

	private String _createIndexConfiguration() {
		return JSONUtil.put(
			"mappings",
			JSONUtil.put(
				_collectionName,
				JSONUtil.put(
					"properties",
					JSONUtil.put(
						"id", JSONUtil.put("type", "keyword")
					).put(
						"string", JSONUtil.put("type", "keyword")
					).put(
						"value", JSONUtil.put("type", "long")
					)))
		).put(
			"settings", JSONUtil.put("index.mapping.total_fields.limit", 5000)
		).toString();
	}

	private String _collectionName;

	@Autowired
	private ElasticsearchConnection _elasticsearchConnection;

	@Autowired
	private ElasticsearchIndexManager _elasticsearchIndexManager;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

	private String _indexName;

}