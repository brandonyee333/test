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
import com.liferay.osb.asah.common.entity.Account;
import com.liferay.osb.asah.common.entity.AsahTask;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.faro.info.dog.test.BaseFaroInfoDogTestCase;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.repository.AccountRepository;
import com.liferay.osb.asah.common.repository.FieldMappingRepository;
import com.liferay.osb.asah.common.repository.SegmentRepository;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.IterableUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

/**
 * @author Vishal Reddy
 */
public class AccountDogTest
	extends BaseFaroInfoDogTestCase
	implements OSBAsahTestExecutionListenersContext {

	@BeforeEach
	public void setUp() {
		_salesforceDataSource = FaroInfoTestUtil.buildSalesforceDataSource();

		_fieldMappingRepository.save(
			FaroInfoTestUtil.buildAccountFieldMapping(
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

		Assertions.assertEquals(0, individualSegmentsJSONArray.length());

		List<AsahTask> asahTasks = _asahTaskDog.getAsahTasks(
			"DeleteIndividualSegmentTasksNanite");

		Assertions.assertEquals(1, asahTasks.size(), asahTasks.toString());

		AsahTask asahTask = asahTasks.get(0);

		JSONObject contextJSONObject = asahTask.getContextJSONObject();

		Assertions.assertNotNull(
			contextJSONObject.getJSONArray("individualSegmentIds"));

		Optional<Account> accountOptional = _accountRepository.findById(
			accountId);

		Assertions.assertNull(accountOptional.orElse(null));

		_assertOSBTasksContextAfterUpdate(
			"contains(filter, 'accounts.filterByCount(')");
	}

	@Test
	public void testGetAccountPage() throws Exception {
		for (int i = 1; i <= 5; i++) {
			Account account = _accountDog.addAccount(
				String.valueOf(i), new JSONObject(), _salesforceDataSource);

			_asahTaskDog.deleteAsahTasks();

			Date date = DateUtil.toUTCDate(
				String.format("2022-04-0%dT12:00:00.000Z", i));

			account.setCreateDate(date);
			account.setModifiedDate(date);

			_accountRepository.save(account);
		}

		Page<Account> accountPage = _accountDog.getAccountPage(
			-1L, DateUtil.toUTCDate("2022-04-02T11:00:00.000Z"), 2,
			Sort.by(Sort.Order.asc("id")),
			DateUtil.toUTCDate("2022-04-04T13:00:00.000Z"));

		Assertions.assertEquals(3, accountPage.getTotalElements());

		List<Account> accounts = accountPage.getContent();

		Assertions.assertEquals(2, accounts.size());
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
		List<Segment> segments = IterableUtils.toList(
			_segmentRepository.findAll());

		Assertions.assertEquals(1, segments.size(), segments.toString());

		Segment segment = segments.get(0);

		_assertIndividualSegment(segment);
	}

	private void _assertAsahTaskIndividualSegmentContext() {
		List<AsahTask> asahTasks = _asahTaskDog.getAsahTasks();

		for (AsahTask asahTask : asahTasks) {
			JSONObject contextJSONObject = asahTask.getContextJSONObject();

			Assertions.assertNotNull(
				contextJSONObject.getString("dateModified"));

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

		Assertions.assertTrue(segment.getActivitiesCount() == 0);
		Assertions.assertNotNull(segment.getCreateDate());
		Assertions.assertNotNull(segment.getModifiedDate());
		Assertions.assertEquals(
			"((dataSourceAccountPKs/accountPKs eq '123'))",
			segment.getFilter());
		Assertions.assertEquals(
			"Account: " + account.getId(), segment.getName());
		Assertions.assertEquals("PROJECT", segment.getScope());
		Assertions.assertEquals(Segment.Type.DYNAMIC, segment.getType());
		Assertions.assertEquals("INACTIVE", segment.getStatus());
	}

	private void _assertOSBTasksContextAfterUpdate(String addFilter) {
		List<AsahTask> asahTasks = _asahTaskDog.getAsahTasks(
			"UpdateDynamicMembershipsNanite");

		Assertions.assertEquals(1, asahTasks.size(), asahTasks.toString());

		AsahTask asahTask = asahTasks.get(0);

		JSONObject contextJSONObject = asahTask.getContextJSONObject();

		Assertions.assertEquals(
			addFilter, contextJSONObject.getString("addFilter"));
		Assertions.assertNotNull(contextJSONObject.getString("dateModified"));
		Assertions.assertEquals(
			"contains(filter, 'accounts.filter(') or contains(filter, " +
				"'accounts.filterByCount(')",
			contextJSONObject.getString("removeFilter"));
	}

	private void _assertStandardFields(Account account) {
		Assertions.assertEquals("123", account.getAccountPK());
		Assertions.assertNotNull(account.getCreateDate());
		Assertions.assertNotNull(account.getDataSourceId());
		Assertions.assertNotNull(account.getId());
		Assertions.assertNotNull(account.getModifiedDate());
	}

	@Autowired
	private AccountDog _accountDog;

	@Autowired
	private AccountRepository _accountRepository;

	@Autowired
	private AsahTaskDog _asahTaskDog;

	@Autowired
	private FieldMappingRepository _fieldMappingRepository;

	@Autowired
	private ObjectMapper _objectMapper;

	private DataSource _salesforceDataSource;

	@Autowired
	private SegmentRepository _segmentRepository;

}