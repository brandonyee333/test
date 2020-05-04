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

package com.liferay.osb.asah.common.faro.info.dog.test;

import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoFieldMappingDog;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Vishal Reddy
 */
@ContextConfiguration(classes = OSBAsahSpringBootApplication.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class FaroInfoFieldMappingDogTest extends BaseFaroInfoDogTestCase {

	@Test
	public void testAddDuplicateWithDifferentTypeFieldMappings() {
		JSONObject dataSourceJSONObject = elasticsearchInvoker.add(
			"data-sources",
			FaroInfoTestUtil.buildLiferayDataSourceJSONObject());

		String name = "What is your income?";

		_faroInfoFieldMappingDog.addFieldMapping(
			"custom", name, dataSourceJSONObject.getString("id"), "input-field",
			name, "Number", "individuals");

		JSONArray fieldMappingsJSONArray = elasticsearchInvoker.get(
			"field-mappings");

		Assert.assertEquals(1, fieldMappingsJSONArray.length());

		JSONObject fieldMappingJSONObject =
			fieldMappingsJSONArray.getJSONObject(0);

		Assert.assertEquals(
			name, fieldMappingJSONObject.getString("displayName"));
		Assert.assertEquals(
			"What_is_your_income_",
			fieldMappingJSONObject.getString("fieldName"));

		_faroInfoFieldMappingDog.addFieldMapping(
			"custom", name, dataSourceJSONObject.getString("id"), "input-field",
			name, "Text", "individuals");

		Assert.assertEquals(
			2,
			elasticsearchInvoker.count(
				"field-mappings", QueryBuilders.matchAllQuery()));

		fieldMappingJSONObject = elasticsearchInvoker.fetch(
			"field-mappings",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("displayName", name)
			).filter(
				QueryBuilders.termQuery("fieldType", "Text")
			));

		Assert.assertEquals(
			"What_is_your_income__1",
			fieldMappingJSONObject.getString("fieldName"));
	}

	@Test
	public void testAddEmailFieldMappingCSVDataSource() {
		_testAddEmailFieldMapping(
			elasticsearchInvoker.add(
				"data-sources",
				FaroInfoTestUtil.buildCSVDataSourceJSONObject()),
			"email");
	}

	@Test
	public void testAddEmailFieldMappingLiferayDataSource() {
		_testAddEmailFieldMapping(
			elasticsearchInvoker.add(
				"data-sources",
				FaroInfoTestUtil.buildLiferayDataSourceJSONObject()),
			"emailAddress");
	}

	@Test
	public void testAddEmailFieldMappingSalesforceDataSource() {
		_testAddEmailFieldMapping(
			elasticsearchInvoker.add(
				"data-sources",
				FaroInfoTestUtil.buildSalesforceDataSourceJSONObject()),
			"email");
	}

	@Test
	public void testAddInvalidCharactersFieldMapping() {
		JSONObject dataSourceJSONObject = elasticsearchInvoker.add(
			"data-sources",
			FaroInfoTestUtil.buildLiferayDataSourceJSONObject());

		String name = "test!@#$%^Invalid_Characters^&*()";

		_faroInfoFieldMappingDog.addFieldMapping(
			"custom", name, dataSourceJSONObject.getString("id"), "input-field",
			name, "Text", "individuals");

		JSONArray fieldMappingsJSONArray = elasticsearchInvoker.get(
			"field-mappings");

		Assert.assertEquals(1, fieldMappingsJSONArray.length());

		JSONObject fieldMappingJSONObject =
			fieldMappingsJSONArray.getJSONObject(0);

		Assert.assertEquals(
			name, fieldMappingJSONObject.getString("displayName"));
		Assert.assertEquals(
			"test______Invalid_Characters_____",
			fieldMappingJSONObject.getString("fieldName"));
	}

	@Test
	public void testAddSanitizedDuplicateFieldMappings() {
		JSONObject dataSourceJSONObject = elasticsearchInvoker.add(
			"data-sources",
			FaroInfoTestUtil.buildLiferayDataSourceJSONObject());

		String name = "What is your income?";

		_faroInfoFieldMappingDog.addFieldMapping(
			"custom", name, dataSourceJSONObject.getString("id"), "input-field",
			name, "Number", "individuals");

		JSONArray fieldMappingsJSONArray = elasticsearchInvoker.get(
			"field-mappings");

		Assert.assertEquals(1, fieldMappingsJSONArray.length());

		JSONObject fieldMappingJSONObject =
			fieldMappingsJSONArray.getJSONObject(0);

		Assert.assertEquals(
			name, fieldMappingJSONObject.getString("displayName"));
		Assert.assertEquals(
			"What_is_your_income_",
			fieldMappingJSONObject.getString("fieldName"));

		name = "What-is-your-income?";

		_faroInfoFieldMappingDog.addFieldMapping(
			"custom", name, dataSourceJSONObject.getString("id"), "input-field",
			name, "Number", "individuals");

		Assert.assertEquals(
			2,
			elasticsearchInvoker.count(
				"field-mappings", QueryBuilders.matchAllQuery()));

		fieldMappingJSONObject = elasticsearchInvoker.fetch(
			"field-mappings", QueryBuilders.termQuery("displayName", name));

		Assert.assertEquals(
			"What_is_your_income__1",
			fieldMappingJSONObject.getString("fieldName"));
	}

	@Test
	public void testDeleteFieldMapping() {
		Assert.assertTrue(
			_faroInfoFieldMappingDog.deleteFieldMapping(
				elasticsearchInvoker.add(
					"field-mappings",
					JSONUtil.put(
						"author",
						JSONUtil.put(
							"id", "123"
						).put(
							"name", "test"
						)))));

		Assert.assertFalse(
			_faroInfoFieldMappingDog.deleteFieldMapping(
				elasticsearchInvoker.add(
					"field-mappings",
					JSONUtil.put(
						"author",
						JSONUtil.put(
							"id", "FARO_SYSTEM"
						).put(
							"name", "FARO_SYSTEM"
						)))));

		JSONArray fieldMappingsJSONArray = elasticsearchInvoker.get(
			"field-mappings");

		Assert.assertEquals(1, fieldMappingsJSONArray.length());
	}

	@Test
	public void testFetchFieldMappingJSONObject() {
		elasticsearchInvoker.add(
			"field-mappings",
			JSONUtil.put(
				"context", "demographics"
			).put(
				"fieldName", "email"
			).put(
				"ownerType", "individual"
			));

		Assert.assertNotNull(
			_faroInfoFieldMappingDog.fetchFieldMappingJSONObject(
				"demographics", "email", "individual"));
	}

	@Test
	public void testRemoveDataSourceFieldName() {
		JSONObject fieldMappingJSONObject =
			_faroInfoFieldMappingDog.removeDataSourceFieldName(
				elasticsearchInvoker.add(
					"field-mappings",
					JSONUtil.put(
						"dataSourceFieldNames",
						JSONUtil.put(
							"123", "testFieldName1"
						).put(
							"234", "testFieldName2"
						))),
				"123");

		JSONObject dataSourceFieldNamesJSONObject =
			fieldMappingJSONObject.getJSONObject("dataSourceFieldNames");

		Assert.assertFalse(dataSourceFieldNamesJSONObject.has("123"));
		Assert.assertTrue(dataSourceFieldNamesJSONObject.has("234"));
	}

	@Test
	public void testUpdateEmailFieldMapping() {
		_testAddEmailFieldMapping(
			elasticsearchInvoker.add(
				"data-sources",
				FaroInfoTestUtil.buildCSVDataSourceJSONObject()),
			"email");

		_testAddEmailFieldMapping(
			elasticsearchInvoker.add(
				"data-sources",
				FaroInfoTestUtil.buildLiferayDataSourceJSONObject()),
			"emailAddress");

		_testAddEmailFieldMapping(
			elasticsearchInvoker.add(
				"data-sources",
				FaroInfoTestUtil.buildSalesforceDataSourceJSONObject()),
			"email");
	}

	private void _testAddEmailFieldMapping(
		JSONObject dataSourceJSONObject, String emailFieldName) {

		_faroInfoFieldMappingDog.addEmailFieldMapping(
			dataSourceJSONObject.getString("id"));

		JSONArray fieldMappingsJSONArray = elasticsearchInvoker.get(
			"field-mappings");

		Assert.assertEquals(1, fieldMappingsJSONArray.length());

		JSONObject fieldMappingJSONObject =
			fieldMappingsJSONArray.getJSONObject(0);

		Assert.assertEquals(
			"demographics", fieldMappingJSONObject.getString("context"));
		Assert.assertNotNull(fieldMappingJSONObject.getString("dateCreated"));
		Assert.assertNotNull(fieldMappingJSONObject.getString("dateModified"));
		Assert.assertEquals(
			"email", fieldMappingJSONObject.getString("fieldName"));
		Assert.assertEquals(
			"http://schema.org/email",
			fieldMappingJSONObject.getString("fieldType"));
		Assert.assertEquals(
			"individual", fieldMappingJSONObject.getString("ownerType"));

		JSONObject dataSourceFieldNamesJSONObject =
			fieldMappingJSONObject.getJSONObject("dataSourceFieldNames");

		Assert.assertEquals(
			emailFieldName,
			dataSourceFieldNamesJSONObject.getString(
				dataSourceJSONObject.getString("id")));

		JSONObject strategyJSONObject = fieldMappingJSONObject.getJSONObject(
			"strategy");

		Assert.assertEquals("MOST_RECENT", strategyJSONObject.getString("key"));
	}

	@Autowired
	private FaroInfoFieldMappingDog _faroInfoFieldMappingDog;

}