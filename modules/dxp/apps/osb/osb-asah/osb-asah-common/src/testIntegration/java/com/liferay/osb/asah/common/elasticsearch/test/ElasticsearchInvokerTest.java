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

import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchBulkRequestBuilder;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchConnection;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchIndexManager;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchIndexUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.impl.ElasticsearchInvokerImpl;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import java.util.Collections;

import org.apache.commons.lang3.RandomUtils;
import org.apache.lucene.search.TotalHits;

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

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Marcellus Tavares
 * @author Rachael Koestartyo
 */
@ContextConfiguration(classes = OSBAsahSpringBootApplication.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class ElasticsearchInvokerTest {

	@Before
	public void setUp() {
		_elasticsearchInvoker = new ElasticsearchInvokerImpl(
			Collections.emptyMap(), _elasticsearchConnection.getClient(),
			"test");

		_collectionName = RandomTestUtil.randomString();

		_indexName = ElasticsearchIndexUtil.getIndexName(
			_collectionName, "test");

		_elasticsearchIndexManager.create(
			true, _createIndexConfiguration(), _indexName);
	}

	@After
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

		Assert.assertNotNull(actualJSONObject.getString("id"));
	}

	@Test
	public void testCount() {
		String testString = RandomTestUtil.randomHexString(4096);

		_elasticsearchInvoker.add(
			_collectionName, JSONUtil.put("string", testString));

		Assert.assertEquals(
			1,
			_elasticsearchInvoker.count(
				_collectionName,
				QueryBuilders.termQuery("string", testString)));
	}

	@Test
	public void testDelete() {
		JSONObject jsonObject = _elasticsearchInvoker.add(
			_collectionName,
			JSONUtil.put("string", RandomTestUtil.randomHexString(4096)));

		Assert.assertEquals(
			1,
			_elasticsearchInvoker.count(
				_collectionName,
				QueryBuilders.termQuery("id", jsonObject.getString("id"))));

		boolean deleted = _elasticsearchInvoker.delete(
			_collectionName, jsonObject);

		Assert.assertTrue(deleted);

		Assert.assertEquals(
			0,
			_elasticsearchInvoker.count(
				_collectionName,
				QueryBuilders.termQuery("id", jsonObject.getString("id"))));
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

		Assert.assertEquals(
			15000,
			_elasticsearchInvoker.count(
				_collectionName, QueryBuilders.matchAllQuery()));

		_elasticsearchInvoker.deleteByQuery(
			QueryBuilders.matchAllQuery(), true, _collectionName);

		Assert.assertEquals(
			0,
			_elasticsearchInvoker.count(
				_collectionName, QueryBuilders.matchAllQuery()));
	}

	@Test
	public void testExistsDocument() {
		JSONObject jsonObject = _elasticsearchInvoker.add(
			_collectionName,
			JSONUtil.put("string", RandomTestUtil.randomHexString(4096)));

		Assert.assertTrue(
			_elasticsearchInvoker.exists(
				_collectionName,
				QueryBuilders.termQuery("id", jsonObject.getString("id"))));
	}

	@Test
	public void testFetchWithExistentDocument() {
		JSONObject jsonObject = _elasticsearchInvoker.add(
			_collectionName,
			JSONUtil.put("string", RandomTestUtil.randomHexString(4096)));

		Assert.assertNotNull(
			_elasticsearchInvoker.fetch(
				_collectionName,
				QueryBuilders.termQuery("id", jsonObject.getString("id"))));
	}

	@Test
	public void testFetchWithNonexistentDocument() {
		_elasticsearchInvoker.add(
			_collectionName,
			JSONUtil.put("string", RandomTestUtil.randomHexString(4096)));

		Assert.assertNull(
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

	@Test(expected = IllegalArgumentException.class)
	public void testFieldIndexLimitExceeded() {
		JSONObject expectedJSONObject = new JSONObject();

		for (int i = 0; i < 2500; i++) {
			expectedJSONObject.put(
				String.valueOf(i), RandomTestUtil.randomHexString(4096));
		}

		_elasticsearchInvoker.add(_collectionName, expectedJSONObject);
	}

	@Test
	public void testGetAll() {
		JSONArray expectedJSONArray = JSONUtil.put(
			JSONUtil.put("string", RandomTestUtil.randomString()));

		_elasticsearchInvoker.add(_collectionName, expectedJSONArray);

		JSONArray actualJSONArray = _elasticsearchInvoker.get(_collectionName);

		Assert.assertEquals(1, actualJSONArray.length());

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

		Assert.assertEquals(1, actualJSONArray.length());

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

		Assert.assertEquals(
			newTestString, actualJSONObject.getString("string"));
		Assert.assertEquals(-1, actualJSONObject.optLong("value", -1));
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

		Assert.assertEquals(
			newTestString, actualJSONObject.getString("string"));
		Assert.assertEquals(-1, actualJSONObject.optLong("value", -1));
	}

	@Test
	public void testSaveWithNonexistentDocument() {
		String testString = RandomTestUtil.randomHexString(4096);

		JSONObject jsonObject = _elasticsearchInvoker.save(
			_collectionName, JSONUtil.put("string", testString));

		JSONObject actualJSONObject = _elasticsearchInvoker.get(
			_collectionName, jsonObject.getString("id"));

		Assert.assertEquals(testString, actualJSONObject.getString("string"));
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

		TotalHits totalHits = searchHits.getTotalHits();

		Assert.assertEquals(3, totalHits.value);

		SearchHit[] hits = searchHits.getHits();

		int hitsLength = hits.length;

		Assert.assertEquals(0, hitsLength);

		Aggregations aggregations = searchResponse.getAggregations();

		Sum sum = aggregations.get("sum");

		Assert.assertEquals(testNumberSum, (int)sum.getValue());
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

		Assert.assertEquals(testString, actualJSONObject.getString("string"));
		Assert.assertEquals(testNumber, actualJSONObject.getLong("value"));
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

		Assert.assertEquals(1, bulkByScrollResponse.getUpdated());
		Assert.assertEquals(0, bulkByScrollResponse.getVersionConflicts());

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

		Assert.assertEquals(
			(int)Math.ceil(expectedCount / 10000.0),
			bulkByScrollResponse.getBatches());
		Assert.assertEquals(expectedCount, bulkByScrollResponse.getUpdated());
	}

	@Ignore
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

		Assert.assertEquals(testNumber, actualJSONObject.getLong("value"));
		Assert.assertEquals(testString, actualJSONObject.getString("string"));

		thread.interrupt();
	}

	@Test
	public void testUpsertExisting() {
		String testString1 = RandomTestUtil.randomHexString(64);
		String testString2 = RandomTestUtil.randomHexString(64);

		JSONObject jsonObject = _elasticsearchInvoker.add(
			_collectionName,
			JSONUtil.put(
				"string1", testString1
			).put(
				"string2", testString2
			));

		String id = jsonObject.getString("id");

		String testString3 = RandomTestUtil.randomHexString(64);
		String testString4 = RandomTestUtil.randomHexString(64);

		JSONObject actualJSONObject = _elasticsearchInvoker.upsert(
			_collectionName, id,
			JSONUtil.put(
				"string1", testString3
			).put(
				"string2", testString4
			),
			new Script("ctx._source.string1 = 'foo'"));

		Assert.assertEquals("foo", actualJSONObject.getString("string1"));
		Assert.assertEquals(testString2, actualJSONObject.getString("string2"));
	}

	@Test
	public void testUpsertNew() {
		String testId = RandomTestUtil.randomHexString(64);
		String testString1 = RandomTestUtil.randomHexString(64);
		String testString2 = RandomTestUtil.randomHexString(64);

		JSONObject actualJSONObject = _elasticsearchInvoker.upsert(
			_collectionName, testId,
			JSONUtil.put(
				"string1", testString1
			).put(
				"string2", testString2
			),
			new Script("ctx._source.string1 = 'foo'"));

		Assert.assertEquals(testString1, actualJSONObject.getString("string1"));
		Assert.assertEquals(testString2, actualJSONObject.getString("string2"));
	}

	private String _createIndexConfiguration() {
		JSONObject jsonObject = JSONUtil.put(
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
		);

		return jsonObject.toString();
	}

	private String _collectionName;

	@Autowired
	private ElasticsearchConnection _elasticsearchConnection;

	@Autowired
	private ElasticsearchIndexManager _elasticsearchIndexManager;

	private ElasticsearchInvoker _elasticsearchInvoker;
	private String _indexName;

}