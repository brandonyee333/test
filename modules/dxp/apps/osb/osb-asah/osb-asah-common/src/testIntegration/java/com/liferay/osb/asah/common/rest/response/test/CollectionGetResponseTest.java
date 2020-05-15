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

package com.liferay.osb.asah.common.rest.response.test;

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.rest.response.CollectionGetResponse;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Vishal Reddy
 * @author Leslie Wong
 */
@ContextConfiguration(classes = OSBAsahSpringBootApplication.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class CollectionGetResponseTest {

	@Before
	public void setUp() throws Exception {
		_elasticsearchInvoker = _elasticsearchInvokerFactory.forFaroInfo();

		_elasticsearchInvoker.add(
			"individuals",
			new JSONArray(
				ResourceUtil.readResourceToString(
					"dependencies/osbasahfaroinfo" +
						"/collection-get-response-individuals.json",
					this)));
	}

	@Test
	public void testRespondWithCustomPageNumber() throws Exception {
		JSONObject responseJSONObject = _getResponseJSONObject(
			2, null, 20, null);

		_assertEmbeddedSize(responseJSONObject, 1);
		_assertPage(responseJSONObject, 2, 20, 41, 3);
	}

	@Test
	public void testRespondWithCustomPageNumberAndPageSize() throws Exception {
		JSONObject responseJSONObject = _getResponseJSONObject(
			2, null, 10, null);

		_assertEmbeddedSize(responseJSONObject, 10);
		_assertPage(responseJSONObject, 2, 10, 41, 5);
	}

	@Test
	public void testRespondWithCustomPageSize() throws Exception {
		JSONObject responseJSONObject = _getResponseJSONObject(
			0, null, 4, null);

		_assertEmbeddedSize(responseJSONObject, 4);
		_assertPage(responseJSONObject, 0, 4, 41, 11);
	}

	@Test
	public void testRespondWithDefaultProperties() throws Exception {
		JSONObject responseJSONObject = _getResponseJSONObject(
			0, null, 20, null);

		_assertEmbeddedSize(responseJSONObject, 20);
		_assertPage(responseJSONObject, 0, 20, 41, 3);
	}

	@Test
	public void testRespondWithNestedSort() throws Exception {
		JSONObject responseJSONObject = _getResponseJSONObject(
			0, null, 20, new String[] {"demographics/email/value", "asc"});

		_assertEmbeddedSize(responseJSONObject, 20);
		_assertOrder(responseJSONObject, "demographics/email/value", "asc");
		_assertPage(responseJSONObject, 0, 20, 41, 3);

		responseJSONObject = _getResponseJSONObject(
			0, null, 20, new String[] {"demographics/email/value", "desc"});

		_assertEmbeddedSize(responseJSONObject, 20);
		_assertOrder(responseJSONObject, "demographics/email/value", "desc");
		_assertPage(responseJSONObject, 0, 20, 41, 3);
	}

	@Test
	public void testRespondWithQueryBuilder() throws Exception {
		JSONObject responseJSONObject = _getResponseJSONObject(
			0,
			QueryBuilders.termQuery(
				"demographics.email.value", "test2@liferay.com"),
			20, null);

		_assertEmbeddedSize(responseJSONObject, 13);
		_assertPage(responseJSONObject, 0, 20, 13, 1);
	}

	@Test
	public void testRespondWithSort() throws Exception {
		JSONObject responseJSONObject = _getResponseJSONObject(
			0, null, 20, new String[] {"dateModified", "asc"});

		_assertEmbeddedSize(responseJSONObject, 20);
		_assertOrder(responseJSONObject, "dateModified", "asc");
		_assertPage(responseJSONObject, 0, 20, 41, 3);

		responseJSONObject = _getResponseJSONObject(
			0, null, 20, new String[] {"dateModified", "desc"});

		_assertEmbeddedSize(responseJSONObject, 20);
		_assertOrder(responseJSONObject, "dateModified", "desc");
		_assertPage(responseJSONObject, 0, 20, 41, 3);
	}

	private void _assertEmbeddedSize(JSONObject jsonObject, long size) {
		JSONObject embeddedJSONObject = jsonObject.getJSONObject("_embedded");

		JSONArray individualsJSONArray = embeddedJSONObject.getJSONArray(
			"individuals");

		Assert.assertEquals(size, individualsJSONArray.length());
	}

	private void _assertOrder(
			JSONObject jsonObject, String path, String sortOrder)
		throws Exception {

		JSONObject embeddedJSONObject = jsonObject.getJSONObject("_embedded");

		List<String> expectedValues = _getValues(
			ResourceUtil.readResourceToJSONArray(
				"dependencies/osbasahfaroinfo" +
					"/collection-get-response-individuals.json",
				this),
			path);

		if (Objects.equals(sortOrder, "asc")) {
			Collections.sort(expectedValues);
		}
		else if (Objects.equals(sortOrder, "desc")) {
			Collections.sort(expectedValues, Collections.reverseOrder());
		}

		Assert.assertEquals(
			expectedValues.subList(0, 20),
			_getValues(embeddedJSONObject.getJSONArray("individuals"), path));
	}

	private void _assertPage(
		JSONObject jsonObject, int number, int size, int totalElements,
		int totalPages) {

		JSONObject pageJSONObject = jsonObject.getJSONObject("page");

		Assert.assertEquals(number, pageJSONObject.getInt("number"));
		Assert.assertEquals(size, pageJSONObject.getInt("size"));
		Assert.assertEquals(
			totalElements, pageJSONObject.getInt("totalElements"));
		Assert.assertEquals(totalPages, pageJSONObject.getInt("totalPages"));
	}

	private JSONObject _getResponseJSONObject(
			int page, QueryBuilder queryBuilder, int size, String[] sorts)
		throws Exception {

		CollectionGetResponse collectionGetResponse =
			new CollectionGetResponse();

		collectionGetResponse.setCollectionName("individuals");
		collectionGetResponse.setElasticsearchInvoker(_elasticsearchInvoker);
		collectionGetResponse.setPage(page);
		collectionGetResponse.setQueryBuilder(queryBuilder);
		collectionGetResponse.setSize(size);
		collectionGetResponse.setSorts(sorts);

		return new JSONObject(collectionGetResponse.respond());
	}

	private List<String> _getValues(JSONArray jsonArray, String path) {
		List<String> values = new ArrayList<>();

		String[] parts = new String[0];

		if (path.contains("/")) {
			int index = path.lastIndexOf("/");

			String part = path.substring(0, index);

			parts = part.split("/");

			path = path.substring(index + 1);
		}

		for (int i = 0; i < jsonArray.length(); i++) {
			Object value = jsonArray.get(i);

			for (String part : parts) {
				if (value instanceof JSONArray) {
					JSONArray curJSONArray = (JSONArray)value;

					value = curJSONArray.get(0);
				}
				else if (value instanceof JSONObject) {
					JSONObject curJSONObject = (JSONObject)value;

					value = curJSONObject.get(part);
				}
			}

			if (value instanceof JSONArray) {
				JSONArray curJSONArray = (JSONArray)value;

				value = curJSONArray.get(0);
			}

			JSONObject jsonObject = (JSONObject)value;

			values.add(jsonObject.getString(path));
		}

		return values;
	}

	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

}