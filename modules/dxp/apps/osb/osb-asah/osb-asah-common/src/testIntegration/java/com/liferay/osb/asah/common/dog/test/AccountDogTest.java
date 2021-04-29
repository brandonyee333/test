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

package com.liferay.osb.asah.common.dog.test;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.AccountDog;
import com.liferay.osb.asah.common.dog.AsahTaskDog;
import com.liferay.osb.asah.common.dog.SegmentDog;
import com.liferay.osb.asah.common.entity.Account;
import com.liferay.osb.asah.common.entity.AsahTask;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.faro.info.dog.test.BaseFaroInfoDogTestCase;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.repository.AccountRepository;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;

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
public class AccountDogTest extends BaseFaroInfoDogTestCase {

	@Before
	public void setUp() {
		_salesforceDataSource = FaroInfoTestUtil.buildSalesforceDataSource();

		faroInfoElasticsearchInvoker.add(
			"field-mappings",
			FaroInfoTestUtil.buildAccountFieldMappingJSONObject(
				String.valueOf(_salesforceDataSource.getId()), "country",
				"country", "Text"));
	}

	@Test
	public void testAddAccount() throws Exception {
		Account account = _accountDog.addAccount(
			"123", new JSONObject(), _salesforceDataSource);

		_assertAccountIndividualSegment();
		_assertAsahTaskIndividualSegmentContext();
		_assertStandardFields(account);
	}

	@Test
	public void testDeleteAccount() throws Exception {
		Account account = _accountDog.addAccount(
			"123", new JSONObject(), _salesforceDataSource);

		Long accountId = null;

		if (account != null) {
			accountId = account.getId();
		}

		_assertAccountIndividualSegment();
		_assertAsahTaskIndividualSegmentContext();
		_assertStandardFields(account);

		_asahTaskDog.deleteAsahTasks();

		_accountDog.deleteAccount(account);

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

		Optional<Account> accountOptional = _accountRepository.findById(
			accountId);

		Assert.assertNull(accountOptional.orElse(null));

		_assertOSBTasksContextAfterUpdate(
			"contains(filter, 'accounts.filterByCount(')");
	}

	@Test
	public void testUpdateAccountByAccountDataJSONObjectDataSource()
		throws Exception {

		Account account = _accountDog.addAccount(
			"123", new JSONObject(), _salesforceDataSource);

		_assertAccountIndividualSegment();
		_assertAsahTaskIndividualSegmentContext();
		_assertStandardFields(account);

		_asahTaskDog.deleteAsahTasks();

		_accountDog.updateAccount(
			account,
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
		_assertStandardFields(account);
	}

	private void _assertAccountIndividualSegment() {
		List<Segment> segments = _segmentDog.getSegments(0, 1);

		Assert.assertEquals(segments.toString(), 1, segments.size());

		Segment segment = segments.get(0);

		_assertIndividualSegment(segment);
	}

	private void _assertAsahTaskIndividualSegmentContext() {
		List<AsahTask> asahTasks = _asahTaskDog.getAsahTasks();

		for (AsahTask asahTask : asahTasks) {
			JSONObject contextJSONObject = asahTask.getContextJSONObject();

			Assert.assertNotNull(contextJSONObject.getString("dateModified"));

			if (contextJSONObject.has("individualSegmentJSONObject")) {
				_assertIndividualSegment(
					_objectMapper.convertValue(
						contextJSONObject.optJSONObject(
							"individualSegmentJSONObject"),
						Segment.class));
			}
		}
	}

	private void _assertIndividualSegment(Segment segment) {
		Optional<Account> accountOptional =
			_accountRepository.findByAccountPKAndDataSourceId(
				"123", _salesforceDataSource.getId());

		Account account = accountOptional.get();

		Assert.assertTrue(segment.getActivitiesCount() == 0);
		Assert.assertNotNull(segment.getCreateDate());
		Assert.assertNotNull(segment.getModifiedDate());
		Assert.assertEquals(
			"((dataSourceAccountPKs/accountPKs eq '123'))",
			segment.getFilter());
		Assert.assertEquals("Account: " + account.getId(), segment.getName());
		Assert.assertEquals("PROJECT", segment.getScope());
		Assert.assertEquals(Segment.Type.DYNAMIC, segment.getType());
		Assert.assertEquals("INACTIVE", segment.getStatus());
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

	private void _assertStandardFields(Account account) {
		Assert.assertEquals("123", account.getAccountPK());
		Assert.assertNotNull(account.getCreateDate());
		Assert.assertNotNull(account.getDataSourceId());
		Assert.assertNotNull(account.getId());
		Assert.assertNotNull(account.getModifiedDate());
	}

	@Autowired
	private AccountDog _accountDog;

	@Autowired
	private AccountRepository _accountRepository;

	@Autowired
	private AsahTaskDog _asahTaskDog;

	@Autowired
	private ObjectMapper _objectMapper;

	private DataSource _salesforceDataSource;

	@Autowired
	private SegmentDog _segmentDog;

}