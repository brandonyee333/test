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
import com.liferay.osb.asah.common.dog.AsahTaskDog;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoAccountDog;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.AsahTask;
import com.liferay.osb.asah.common.model.DataSource;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.util.List;

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
	public void setUp() throws Exception {
		_salesforceDataSource = FaroInfoTestUtil.buildSalesforceDataSource();

		faroInfoElasticsearchInvoker.add(
			"field-mappings",
			FaroInfoTestUtil.buildAccountFieldMappingJSONObject(
				String.valueOf(_salesforceDataSource.getId()), "country",
				"country", "Text"));
	}

	@Test
	public void testAddAccount() throws Exception {
		JSONObject accountJSONObject = _faroInfoAccountDog.addAccount(
			JSONUtil.put("id", "123"), _salesforceDataSource);

		_assertAccountIndividualSegment();
		_assertAsahTaskIndividualSegmentContext();
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
		_assertAsahTaskIndividualSegmentContext();
	}

	@Test
	public void testDeleteAccount() throws Exception {
		JSONObject accountJSONObject = _faroInfoAccountDog.addAccount(
			JSONUtil.put("id", "123"), _salesforceDataSource);

		_assertAccountIndividualSegment();
		_assertAsahTaskIndividualSegmentContext();
		_assertStandardFields(accountJSONObject);

		_asahTaskDog.deleteAsahTasks();

		_faroInfoAccountDog.deleteAccount(accountJSONObject);

		JSONArray individualSegmentsJSONArray =
			faroInfoElasticsearchInvoker.get("individual-segments");

		Assert.assertEquals(0, individualSegmentsJSONArray.length());

		List<AsahTask> asahTasks = _asahTaskDog.getAsahTasks(
			"DeleteIndividualSegmentTasksNanite");

		Assert.assertEquals(asahTasks.toString(), 1, asahTasks.size());

		AsahTask asahTask = asahTasks.get(0);

		JSONObject contextJSONObject = asahTask.getContextJSONObject();

		Assert.assertNotNull(
			contextJSONObject.getString("individualSegmentId"));

		Assert.assertFalse(
			faroInfoElasticsearchInvoker.exists(
				"accounts", accountJSONObject.getString("id")));

		_assertOSBTasksContextAfterUpdate(
			"contains(filter, 'accounts.filterByCount(')");
	}

	@Test
	public void testReplaceAccount() throws Exception {
		JSONObject accountJSONObject = _faroInfoAccountDog.addAccount(
			JSONUtil.put("id", "123"), _salesforceDataSource);

		_assertAccountIndividualSegment();
		_assertAsahTaskIndividualSegmentContext();
		_assertStandardFields(accountJSONObject);

		_asahTaskDog.deleteAsahTasks();

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
			JSONUtil.put("id", "123"), _salesforceDataSource);

		_assertAccountIndividualSegment();
		_assertAsahTaskIndividualSegmentContext();
		_assertStandardFields(accountJSONObject);

		_asahTaskDog.deleteAsahTasks();

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
			JSONUtil.put("id", "123"), _salesforceDataSource);

		_assertAccountIndividualSegment();
		_assertAsahTaskIndividualSegmentContext();
		_assertStandardFields(accountJSONObject);

		_asahTaskDog.deleteAsahTasks();

		_faroInfoAccountDog.updateAccount(
			accountJSONObject,
			JSONUtil.put(
				"country", "United States"
			).put(
				"LastModifiedDate", DateUtil.newDateString()
			),
			_salesforceDataSource);

		_assertAccountIndividualSegment();
		_assertOSBTasksContextAfterUpdate(
			"contains(filter, 'accounts.filter(') or contains(filter, " +
				"'accounts.filterByCount(')");
		_assertStandardFields(accountJSONObject);
	}

	private void _assertAccountIndividualSegment() {
		JSONArray individualSegmentsJSONArray =
			faroInfoElasticsearchInvoker.get("individual-segments");

		Assert.assertEquals(1, individualSegmentsJSONArray.length());

		JSONObject individualSegmentJSONObject =
			individualSegmentsJSONArray.getJSONObject(0);

		_assertIndividualSegmentJSONObject(individualSegmentJSONObject);
	}

	private void _assertAsahTaskIndividualSegmentContext() {
		List<AsahTask> asahTasks = _asahTaskDog.getAsahTasks();

		for (AsahTask asahTask : asahTasks) {
			JSONObject contextJSONObject = asahTask.getContextJSONObject();

			Assert.assertNotNull(contextJSONObject.getString("dateModified"));

			if (contextJSONObject.has("individualSegmentJSONObject")) {
				_assertIndividualSegmentJSONObject(
					contextJSONObject.optJSONObject(
						"individualSegmentJSONObject"));
			}
		}
	}

	private void _assertIndividualSegmentJSONObject(
		JSONObject individualSegmentJSONObject) {

		JSONArray accountsJSONArray = faroInfoElasticsearchInvoker.get(
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

	private void _assertOSBTasksContextAfterUpdate(String addFilter) {
		List<AsahTask> asahTasks = _asahTaskDog.getAsahTasks(
			"UpdateDynamicMembershipsNanite");

		Assert.assertEquals(asahTasks.toString(), 1, asahTasks.size());

		AsahTask asahTask = asahTasks.get(0);

		JSONObject contextJSONObject = asahTask.getContextJSONObject();

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
		Assert.assertNotNull(accountJSONObject.getString("id"));
		Assert.assertEquals(0, accountJSONObject.getInt("individualCount"));
	}

	@Autowired
	private AsahTaskDog _asahTaskDog;

	@Autowired
	private FaroInfoAccountDog _faroInfoAccountDog;

	private DataSource _salesforceDataSource;

}