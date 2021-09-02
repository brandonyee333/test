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

package com.liferay.osb.asah.backend.dog;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.backend.model.Account;
import com.liferay.osb.asah.common.dog.AccountDog;
import com.liferay.osb.asah.common.model.ResultBag;
import com.liferay.osb.asah.common.repository.AccountRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
public class ReportAccountDog {

	public Account getAccount(Long id) {
		return _mapAccount(_accountDog.getAccount(id, null));
	}

	public Page<Account> getAccountPage(Long accountId, int size, Sort sort) {
		List<com.liferay.osb.asah.common.entity.Account> accounts =
			_accountRepository.findByIdAfter(
				accountId, PageRequest.of(0, size, sort));

		Stream<com.liferay.osb.asah.common.entity.Account> stream =
			accounts.stream();

		List<Account> reportAccounts = new LinkedList<>();

		stream.forEachOrdered(
			account -> reportAccounts.add(_mapAccount(account)));

		return PageableExecutionUtils.getPage(
			reportAccounts, PageRequest.of(0, size, sort),
			() -> _accountRepository.countByIdAfter(accountId));
	}

	public ResultBag<Account> getAccountResultBag(int size, int start) {
		List<com.liferay.osb.asah.common.entity.Account> accounts =
			_accountDog.getAccounts(size, start);

		List<Account> models = new ArrayList<>();

		for (com.liferay.osb.asah.common.entity.Account account : accounts) {
			models.add(_mapAccount(account));
		}

		ResultBag<Account> resultBag = new ResultBag<>();

		resultBag.setResults(models);
		resultBag.setTotal(accounts.size());

		return resultBag;
	}

	private Map<String, Object> _getAccountOrganizationProperties(
		JSONObject organizationJSONObject) {

		if (organizationJSONObject == null) {
			return Collections.emptyMap();
		}

		Map<String, Object> properties = new HashMap<>();

		for (String propertyName : organizationJSONObject.keySet()) {
			Object propertyValue = _getPropertyValue(
				organizationJSONObject, propertyName);

			if (propertyValue == null) {
				continue;
			}

			properties.put(propertyName, propertyValue);
		}

		return properties;
	}

	private Object _getPropertyValue(
		JSONObject organizationJSONObject, String fieldName) {

		JSONArray propertyJSONArray = organizationJSONObject.optJSONArray(
			fieldName);

		if (propertyJSONArray == null) {
			return null;
		}

		JSONObject propertyJSONObject = propertyJSONArray.optJSONObject(0);

		if (propertyJSONObject == null) {
			return null;
		}

		return propertyJSONObject.get("value");
	}

	private Account _mapAccount(
		com.liferay.osb.asah.common.entity.Account account) {

		Account newAccount = new Account();

		newAccount.setActiveIndividualsCount(
			account.getActiveIndividualsCount());
		newAccount.setDateCreated(account.getCreateDate());
		newAccount.setDateModified(account.getModifiedDate());
		newAccount.setId(String.valueOf(account.getId()));
		newAccount.setIndividualsCount(account.getIndividualCount());
		newAccount.setProperties(
			_getAccountOrganizationProperties(
				_objectMapper.convertValue(
					account.getOrganization(), JSONObject.class)));

		return newAccount;
	}

	@Autowired
	private AccountDog _accountDog;

	@Autowired
	private AccountRepository _accountRepository;

	@Autowired
	private ObjectMapper _objectMapper;

}