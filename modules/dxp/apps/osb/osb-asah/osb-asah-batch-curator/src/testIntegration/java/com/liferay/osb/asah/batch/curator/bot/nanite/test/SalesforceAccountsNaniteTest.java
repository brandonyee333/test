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

package com.liferay.osb.asah.batch.curator.bot.nanite.test;

import com.liferay.osb.asah.batch.curator.bot.nanite.SalesforceAccountsNanite;
import com.liferay.osb.asah.common.dog.AccountDog;
import com.liferay.osb.asah.common.entity.Account;
import com.liferay.osb.asah.common.entity.Field;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.repository.AccountRepository;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.common.repository.FieldRepository;
import com.liferay.osb.asah.common.repository.RunLogRepository;
import com.liferay.osb.asah.common.repository.SalesforceAuditEventRepository;
import com.liferay.osb.asah.common.repository.SalesforceEntityRepository;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.annotation.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.annotation.RepositoryResource;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Michael Bowerman
 */
public class SalesforceAccountsNaniteTest
	extends BaseNaniteTestCase implements OSBAsahTestExecutionListenersContext {

	@ElasticsearchIndex(
		name = "field-mappings", resourcePath = "field_mappings.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@RepositoryResource(
		repositoryClass = DataSourceRepository.class,
		resourcePath = "osbasahfaroinfo/data_sources.json"
	)
	@RepositoryResource(
		repositoryClass = RunLogRepository.class,
		resourcePath = "osbasahsalesforceraw/run_logs.json"
	)
	@RepositoryResource(
		repositoryClass = SalesforceAuditEventRepository.class,
		resourcePath = "osbasahsalesforceraw/audit_events.json"
	)
	@RepositoryResource(
		repositoryClass = SalesforceEntityRepository.class,
		resourcePath = "osbasahsalesforceraw/account.json"
	)
	@Test
	public void testPopulate() throws Exception {
		_salesforceAccountsNanite.run(
			JSONUtil.put("dataSourceId", "342837044336786766"));

		Optional<Account> accountOptional = _accountRepository.findByAccountPK(
			"0016C00000BKWWLQA5");

		Account account = _accountDog.populateAccount(
			accountOptional.get(), null);

		Assertions.assertNotNull(account);

		_assertField(
			account, "342837044336786766", "accountPK", "Text", "id",
			"0016C00000BKWWLQA5");
		_assertField(
			account, "342837044336786766", "accountName", "Text", "Name",
			"Liferay");
		_assertField(
			account, "342837044336786766", "accountType", "Text", "Type",
			"Other");
		_assertField(
			account, "342837044336786766", "annualRevenue", "Number",
			"AnnualRevenue", "51000000");
		_assertField(
			account, "342837044336786766", "billingCountry", "Text",
			"BillingCountry", "United States");
		_assertField(
			account, "342837044336786766", "billingState", "Text",
			"BillingState", "California");
		_assertField(
			account, "342837044336786766", "currencyIsoCode", "Text",
			"CurrencyIsoCode", "USD");
		_assertField(
			account, "342837044336786766", "industry", "Text", "Industry",
			"Technology");
		_assertField(
			account, "342837044336786766", "ownership", "Text", "Ownership",
			"Private");
		_assertField(
			account, "342837044336786766", "phone", "Text", "Phone",
			"877-543-3729");
		_assertField(
			account, "342837044336786766", "photoUrl", "Text", "PhotoUrl",
			"/services/images/photo/0016C00000BKWWLQA5");
		_assertField(
			account, "342837044336786766", "website", "Text", "Website",
			"http://www.liferay.com");

		accountOptional = _accountRepository.findByAccountPK(
			"0016C00000BKWWLQA6");

		account = accountOptional.get();

		_assertField(
			account, "342837044336786766", "accountPK", "Text", "id",
			"0016C00000BKWWLQA6");
		_assertField(
			account, "342837044336786766", "accountName", "Text", "Name",
			"Test Company");
		_assertField(
			account, "342837044336786766", "accountType", "Text", "Type",
			"Customer");
		_assertField(
			account, "342837044336786766", "annualRevenue", "Number",
			"AnnualRevenue", "11000000");
		_assertField(
			account, "342837044336786766", "billingCountry", "Text",
			"BillingCountry", "United States");
		_assertField(
			account, "342837044336786766", "billingState", "Text",
			"BillingState", "New York");
		_assertField(
			account, "342837044336786766", "currencyIsoCode", "Text",
			"CurrencyIsoCode", "USD");
		_assertField(
			account, "342837044336786766", "industry", "Text", "Industry",
			"Education");
		_assertField(
			account, "342837044336786766", "ownership", "Text", "Ownership",
			"Public");
		_assertField(
			account, "342837044336786766", "phone", "Text", "Phone",
			"888-555-1212");
		_assertField(
			account, "342837044336786766", "photoUrl", "Text", "PhotoUrl",
			"/services/images/photo/0016C00000BKWWLQA6");
		_assertField(
			account, "342837044336786766", "website", "Text", "Website",
			"http://www.testcompany.org");
	}

	private void _assertField(
		Account account, String dataSourceId, String fieldName,
		String fieldType, String sourceName, Object value) {

		List<Field> fields =
			_fieldRepository.
				findByContextAndDataSourceIdAndNameAndOwnerIdAndOwnerType(
					"organization", Long.valueOf(dataSourceId), fieldName,
					account.getId(), "account");

		Field field = fields.get(0);

		Assertions.assertEquals(
			Long.valueOf(dataSourceId), field.getDataSourceId());
		Assertions.assertEquals(fieldType, field.getFieldType());
		Assertions.assertEquals(fieldName, field.getName());
		Assertions.assertEquals(sourceName, field.getSourceName());
		Assertions.assertEquals(value, field.getValue());
	}

	@Autowired
	private AccountDog _accountDog;

	@Autowired
	private AccountRepository _accountRepository;

	@Autowired
	private FieldRepository _fieldRepository;

	@Autowired
	private SalesforceAccountsNanite _salesforceAccountsNanite;

}