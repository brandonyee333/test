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

package com.liferay.osb.asah.common.repository.test;

import com.liferay.osb.asah.common.entity.Account;
import com.liferay.osb.asah.common.repository.AccountRepository;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.annotation.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Rachael Koestartyo
 */
@ContextConfiguration(classes = OSBAsahSpringBootApplication.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class ElasticsearchAccountRepositoryTest
	extends BaseAccountRepositoryTestCase {

	@ElasticsearchIndex(
		name = "accounts", resourcePath = "accounts.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Override
	@Test
	public void testCountAccounts() {
		super.testCountAccounts();
	}

	@ElasticsearchIndex(
		name = "accounts", resourcePath = "accounts_2.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "field-mappings", resourcePath = "field_mappings.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "individual-segments", resourcePath = "individual_segments.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Override
	@Test
	public void testGetDistributionAccounts() throws Exception {
		setUpDataSources();

		super.testGetDistributionAccounts();
	}

	@ElasticsearchIndex(
		name = "accounts", resourcePath = "accounts.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Override
	@Test
	public void testSearchAccounts() {
		AccountRepository accountRepository =
			(AccountRepository)getCrudRepository();

		PageRequest pageRequest = PageRequest.of(
			0, 20, Sort.by(Sort.Order.asc("organization/field1/value")));

		List<Account> accounts = accountRepository.searchAccounts(
			null, null, "organization/field1/value eq 'field two'", pageRequest,
			Sort.by(Sort.Order.asc("individualCount")));

		Assert.assertEquals(accounts.toString(), 1, accounts.size());

		Optional<Account> accountOptional = accountRepository.findById(10L);

		Assert.assertEquals(accountOptional.orElse(null), accounts.get(0));
	}

}