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

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoAccountDog;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

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
 */
@ContextConfiguration(classes = OSBAsahSpringBootApplication.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class FaroInfoAccountDogTest extends BaseFaroInfoDogTestCase {

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		_salesforceDataSourceJSONObject =
			FaroInfoTestUtil.buildSalesforceDataSourceJSONObject();

		elasticsearchInvoker.add(
			"field-mappings",
			FaroInfoTestUtil.buildAccountFieldMappingJSONObject(
				_salesforceDataSourceJSONObject.getString("id"), "country",
				"country", "Text"));
	}

	@Test
	public void testAddAccount() throws Exception {
		JSONObject accountJSONObject = _faroInfoAccountDog.addAccount(
			JSONUtil.put("id", "123"), _salesforceDataSourceJSONObject);

		_assertAccountIndividualSegment();
		_assertOSBAsahTaskIndividualSegmentContext();
		_assertStandardFields(accountJSONObject);
	}

	@Test
	public void testAddAccountWithAccountJSONObject() throws Exception {
		_faroInfoAccountDog.addAccount(
			JSONUtil.put(
				"accountPK", "123"
			).put(
				"dateCreated", DateUtil.newDateString()
			));

		_assertAccountIndividualSegment();
		_assertOSBAsahTaskIndividualSegmentContext();
	}

	@Test
	public void testDeleteAccount() throws Exception {
		JSONObject accountJSONObject = _faroInfoAccountDog.addAccount(
			JSONUtil.put("id", "123"), _salesforceDataSourceJSONObject);

		_assertAccountIndividualSegment();
		_assertOSBAsahTaskIndividualSegmentContext();
		_assertStandardFields(accountJSONObject);

		elasticsearchInvoker.delete(
			"OSBAsahTasks", QueryBuilders.matchAllQuery());

		_faroInfoAccountDog.deleteAccount(accountJSONObject);

		JSONArray individualSegmentsJSONArray = elasticsearchInvoker.get(
			"individual-segments");

		Assert.assertEquals(0, individualSegmentsJSONArray.length());

		JSONArray osbAsahTasksJSONArray = elasticsearchInvoker.get(
			"OSBAsahTasks",
			QueryBuilders.termQuery(
				"className", "DeleteIndividualSegmentTasksNanite"));

		Assert.assertEquals(1, osbAsahTasksJSONArray.length());

		JSONObject osbAsahTaskJSONObject = osbAsahTasksJSONArray.getJSONObject(
			0);

		JSONObject contextJSONObject = osbAsahTaskJSONObject.getJSONObject(
			"context");

		Assert.assertNotNull(
			contextJSONObject.getString("individualSegmentId"));

		Assert.assertFalse(
			elasticsearchInvoker.exists(
				"accounts", accountJSONObject.getString("id")));

		_assertOSBTasksContextAfterUpdate(
			"contains(filter, 'accounts.filterByCount(')");
	}

	@Test
	public void testReplaceAccount() throws Exception {
		JSONObject accountJSONObject = _faroInfoAccountDog.addAccount(
			JSONUtil.put("id", "123"), _salesforceDataSourceJSONObject);

		_assertAccountIndividualSegment();
		_assertOSBAsahTaskIndividualSegmentContext();
		_assertStandardFields(accountJSONObject);

		elasticsearchInvoker.delete(
			"OSBAsahTasks", QueryBuilders.matchAllQuery());

		_faroInfoAccountDog.replaceAccount(
			accountJSONObject.put("country", "United States"));

		_assertAccountIndividualSegment();
		_assertOSBTasksContextAfterUpdate(
			"contains(filter, 'accounts.filter(') or contains(filter, " +
				"'accounts.filterByCount(')");
		_assertStandardFields(accountJSONObject);
	}

	@Test
	public void testUpdateAccountByAccountIdAndPartialAccountJSONObject()
		throws Exception {

		JSONObject accountJSONObject = _faroInfoAccountDog.addAccount(
			JSONUtil.put("id", "123"), _salesforceDataSourceJSONObject);

		_assertAccountIndividualSegment();
		_assertOSBAsahTaskIndividualSegmentContext();
		_assertStandardFields(accountJSONObject);

		elasticsearchInvoker.delete(
			"OSBAsahTasks", QueryBuilders.matchAllQuery());

		_faroInfoAccountDog.updateAccount(
			accountJSONObject.getString("id"),
			JSONUtil.put("country", "United States"));

		_assertAccountIndividualSegment();
		_assertOSBTasksContextAfterUpdate(
			"contains(filter, 'accounts.filter(') or contains(filter, " +
				"'accounts.filterByCount(')");
		_assertStandardFields(accountJSONObject);
	}

	@Test
	public void testUpdateAccountByAccountJSONObjectDataJSONObjectDataSourceJSONObject()
		throws Exception {

		JSONObject accountJSONObject = _faroInfoAccountDog.addAccount(
			JSONUtil.put("id", "123"), _salesforceDataSourceJSONObject);

		_assertAccountIndividualSegment();
		_assertOSBAsahTaskIndividualSegmentContext();
		_assertStandardFields(accountJSONObject);

		elasticsearchInvoker.delete(
			"OSBAsahTasks", QueryBuilders.matchAllQuery());

		_faroInfoAccountDog.updateAccount(
			accountJSONObject,
			JSONUtil.put(
				"country", "United States"
			).put(
				"LastModifiedDate", DateUtil.newDateString()
			),
			_salesforceDataSourceJSONObject);

		_assertAccountIndividualSegment();
		_assertOSBTasksContextAfterUpdate(
			"contains(filter, 'accounts.filter(') or contains(filter, " +
				"'accounts.filterByCount(')");
		_assertStandardFields(accountJSONObject);
	}

	private void _assertAccountIndividualSegment() {
		JSONArray individualSegmentsJSONArray = elasticsearchInvoker.get(
			"individual-segments");

		Assert.assertEquals(1, individualSegmentsJSONArray.length());

		JSONObject individualSegmentJSONObject =
			individualSegmentsJSONArray.getJSONObject(0);

		_assertIndividualSegmentJSONObject(individualSegmentJSONObject);
	}

	private void _assertIndividualSegmentJSONObject(
		JSONObject individualSegmentJSONObject) {

		JSONArray accountsJSONArray = elasticsearchInvoker.get(
			"accounts", QueryBuilders.termQuery("accountPK", "123"));

		JSONObject accountJSONObject = accountsJSONArray.getJSONObject(0);

		Assert.assertEquals(
			0, individualSegmentJSONObject.getInt("activitiesCount"));
		Assert.assertNotNull(
			individualSegmentJSONObject.getString("dateCreated"));
		Assert.assertNotNull(
			individualSegmentJSONObject.getString("dateModified"));
		Assert.assertEquals(
			"((dataSourceAccountPKs/accountPKs eq '123'))",
			individualSegmentJSONObject.getString("filter"));
		Assert.assertEquals(
			"Account: " + accountJSONObject.getString("id"),
			individualSegmentJSONObject.getString("name"));
		Assert.assertEquals(
			"PROJECT", individualSegmentJSONObject.getString("scope"));
		Assert.assertEquals(
			"DYNAMIC", individualSegmentJSONObject.getString("segmentType"));
		Assert.assertEquals(
			"INACTIVE", individualSegmentJSONObject.getString("status"));
	}

	private void _assertOSBAsahTaskIndividualSegmentContext() {
		JSONArray osbAsahTasksJSONArray = elasticsearchInvoker.get(
			"OSBAsahTasks");

		for (int i = 0; i < osbAsahTasksJSONArray.length(); i++) {
			JSONObject osbAsahTaskJSONObject =
				osbAsahTasksJSONArray.getJSONObject(i);

			JSONObject contextJSONObject = osbAsahTaskJSONObject.getJSONObject(
				"context");

			Assert.assertNotNull(contextJSONObject.getString("dateModified"));

			if (contextJSONObject.has("individualSegmentJSONObject")) {
				_assertIndividualSegmentJSONObject(
					contextJSONObject.optJSONObject(
						"individualSegmentJSONObject"));
			}
		}
	}

	private void _assertOSBTasksContextAfterUpdate(String addFilter) {
		JSONArray osbAsahTasksJSONArray = elasticsearchInvoker.get(
			"OSBAsahTasks",
			QueryBuilders.termQuery(
				"className", "UpdateDynamicMembershipsNanite"));

		Assert.assertEquals(1, osbAsahTasksJSONArray.length());

		JSONObject osbAsahTaskJSONObject = osbAsahTasksJSONArray.getJSONObject(
			0);

		JSONObject contextJSONObject = osbAsahTaskJSONObject.getJSONObject(
			"context");

		Assert.assertEquals(
			addFilter, contextJSONObject.getString("addFilter"));
		Assert.assertNotNull(contextJSONObject.getString("dateModified"));
		Assert.assertEquals(
			"contains(filter, 'accounts.filter(') or contains(filter, " +
				"'accounts.filterByCount(')",
			contextJSONObject.getString("removeFilter"));
	}

	private void _assertStandardFields(JSONObject accountJSONObject) {
		Assert.assertEquals("123", accountJSONObject.getString("accountPK"));
		Assert.assertEquals(0, accountJSONObject.getInt("activitiesCount"));
		Assert.assertNotNull(accountJSONObject.getString("dataSourceId"));
		Assert.assertNotNull(accountJSONObject.getString("dateCreated"));
		Assert.assertNotNull(accountJSONObject.getString("dateModified"));
		Assert.assertEquals(0, accountJSONObject.getInt("engagementScore"));
		Assert.assertNotNull(accountJSONObject.getString("id"));
		Assert.assertEquals(0, accountJSONObject.getInt("individualCount"));
	}

	@Autowired
	private FaroInfoAccountDog _faroInfoAccountDog;

	private JSONObject _salesforceDataSourceJSONObject;

}