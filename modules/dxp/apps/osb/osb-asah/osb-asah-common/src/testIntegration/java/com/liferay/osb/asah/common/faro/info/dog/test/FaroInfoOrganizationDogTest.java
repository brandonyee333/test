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

import com.liferay.osb.asah.common.faro.info.dog.FaroInfoOrganizationDog;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.test.util.faro.DXPRawTestUtil;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONObject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Matthew Kong
 */
@ContextConfiguration(classes = OSBAsahSpringBootApplication.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class FaroInfoOrganizationDogTest extends BaseFaroInfoDogTestCase {

	@Before
	public void setUp() throws Exception {
		_liferayDataSourceJSONObject =
			FaroInfoTestUtil.buildLiferayDataSourceJSONObject();

		_liferayDataSourceJSONObject.put("id", RandomTestUtil.randomId());

		faroInfoElasticsearchInvoker.add(
			"field-mappings",
			FaroInfoTestUtil.buildFieldMappingJSONObject(
				null, "custom",
				JSONUtil.put(
					_liferayDataSourceJSONObject.getString("id"), "address"),
				"address", "Text", "organization"));
		faroInfoElasticsearchInvoker.add(
			"field-mappings",
			FaroInfoTestUtil.buildFieldMappingJSONObject(
				null, "custom",
				JSONUtil.put(
					_liferayDataSourceJSONObject.getString("id"),
					"dateFounded"),
				"dateFounded", "Date", "organization"));
	}

	@Test
	public void testAddOrganization() throws Exception {
		JSONObject dxpRawOrganizationJSONObject =
			DXPRawTestUtil.buildOrganizationJSONObject(
				_liferayDataSourceJSONObject.getString("id")
			).put(
				"expando", JSONUtil.put("address", "1400 Montefino Ave")
			);

		JSONObject organizationJSONObject =
			_faroInfoOrganizationDog.addOrganization(
				dxpRawOrganizationJSONObject, _liferayDataSourceJSONObject);

		Assert.assertEquals(
			"1400 Montefino Ave",
			JSONUtil.getValue(
				organizationJSONObject.getJSONObject("custom"),
				"JSONArray/address", "Object/0", "Object/value"));
		Assert.assertNotNull(organizationJSONObject.getString("dateCreated"));
		Assert.assertEquals(
			_liferayDataSourceJSONObject.getString("id"),
			organizationJSONObject.getString("dataSourceId"));
		Assert.assertEquals(
			dxpRawOrganizationJSONObject.getString("name"),
			organizationJSONObject.getString("name"));
		Assert.assertEquals(
			dxpRawOrganizationJSONObject.getString("nameTreePath"),
			organizationJSONObject.getString("nameTreePath"));
		Assert.assertEquals(
			dxpRawOrganizationJSONObject.getLong("organizationId"),
			organizationJSONObject.getLong("organizationPK"));
		Assert.assertEquals(
			dxpRawOrganizationJSONObject.getLong("parentOrganizationId"),
			organizationJSONObject.getLong("parentOrganizationPK"));
		Assert.assertEquals(
			dxpRawOrganizationJSONObject.getString("type"),
			organizationJSONObject.getString("type"));
	}

	@Test
	public void testDeleteOrganization() throws Exception {
		JSONObject organizationJSONObject =
			_faroInfoOrganizationDog.addOrganization(
				DXPRawTestUtil.buildOrganizationJSONObject(
					_liferayDataSourceJSONObject.getString("id")),
				_liferayDataSourceJSONObject);

		_faroInfoOrganizationDog.deleteOrganization(organizationJSONObject);

		Assert.assertEquals(
			0,
			faroInfoElasticsearchInvoker.count(
				"organizations", QueryBuilders.matchAllQuery()));
		Assert.assertEquals(
			1,
			faroInfoElasticsearchInvoker.count(
				"OSBAsahTasks",
				QueryBuilders.termQuery(
					"className", "UpdateDynamicMembershipsNanite")));
	}

	@Test
	public void testUpdateOrganization() throws Exception {
		JSONObject dxpRawOrganizationJSONObject =
			DXPRawTestUtil.buildOrganizationJSONObject(
				_liferayDataSourceJSONObject.getString("id"));

		JSONObject organizationJSONObject =
			_faroInfoOrganizationDog.addOrganization(
				dxpRawOrganizationJSONObject, _liferayDataSourceJSONObject);

		organizationJSONObject = _faroInfoOrganizationDog.updateOrganization(
			dxpRawOrganizationJSONObject.put(
				"expando",
				JSONUtil.put(
					"dateFounded", String.valueOf(System.currentTimeMillis()))
			).put(
				"name", "marketing"
			),
			_liferayDataSourceJSONObject, organizationJSONObject);

		Assert.assertEquals(
			"marketing", organizationJSONObject.getString("name"));

		JSONObject customContextJSONObject =
			organizationJSONObject.getJSONObject("custom");

		Assert.assertNotNull(
			JSONUtil.getValue(
				customContextJSONObject, "JSONArray/dateFounded", "Object/0",
				"Object/value"));

		Assert.assertEquals(
			1,
			faroInfoElasticsearchInvoker.count(
				"OSBAsahTasks",
				QueryBuilders.termQuery(
					"className", "UpdateDynamicMembershipsNanite")));
	}

	@Autowired
	private FaroInfoOrganizationDog _faroInfoOrganizationDog;

	private JSONObject _liferayDataSourceJSONObject;

}