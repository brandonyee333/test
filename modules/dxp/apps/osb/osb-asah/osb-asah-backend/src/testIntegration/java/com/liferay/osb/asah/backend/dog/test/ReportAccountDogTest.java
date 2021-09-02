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

package com.liferay.osb.asah.backend.dog.test;

import com.liferay.osb.asah.backend.dog.ReportAccountDog;
import com.liferay.osb.asah.backend.model.Account;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.model.ResultBag;
import com.liferay.osb.asah.common.util.SetUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.annotation.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Marcellus Tavares
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OSBAsahBackendSpringBootApplication.class)
public class ReportAccountDogTest {

	@ElasticsearchIndex(
		name = "field-mappings", resourcePath = "field_mappings_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "accounts", resourcePath = "accounts_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetAccount() {
		Account account = _reportAccountDog.getAccount(379649798552539340L);

		Assert.assertEquals(12, account.getActiveIndividualsCount());
		Assert.assertEquals(
			"2019-10-16T21:25:31.053Z",
			DateUtil.toUTCString(account.getDateCreated()));
		Assert.assertEquals(
			"2019-10-16T21:26:31.053Z",
			DateUtil.toUTCString(account.getDateModified()));
		Assert.assertEquals("379649798552539340", account.getId());
		Assert.assertEquals(13, account.getIndividualsCount());
	}

	@ElasticsearchIndex(
		name = "field-mappings", resourcePath = "field_mappings_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "fields", resourcePath = "fields_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "accounts", resourcePath = "accounts_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetAccountsResultBag() {
		ResultBag<Account> accountResultBag =
			_reportAccountDog.getAccountResultBag(6, 0);

		Assert.assertEquals(6, accountResultBag.getTotal());
		Assert.assertEquals(
			Collections.emptySet(),
			_getAccountPropertiesValues(
				accountResultBag.getResults(), "accountType"));
		Assert.assertEquals(
			SetUtil.of(
				"Heard Island and McDonald Islands", "Maldives", "Swaziland",
				"Uzbekistan", "Virgin Islands"),
			_getAccountPropertiesValues(
				accountResultBag.getResults(), "billingCountry"));
	}

	private Set<String> _getAccountPropertiesValues(
		List<Account> accounts, String fieldName) {

		Stream<Account> stream = accounts.stream();

		return stream.map(
			Account::getProperties
		).map(
			properties -> properties.get(fieldName)
		).filter(
			propertyValue -> !Objects.isNull(propertyValue)
		).map(
			String::valueOf
		).collect(
			Collectors.toSet()
		);
	}

	@Autowired
	private ReportAccountDog _reportAccountDog;

}