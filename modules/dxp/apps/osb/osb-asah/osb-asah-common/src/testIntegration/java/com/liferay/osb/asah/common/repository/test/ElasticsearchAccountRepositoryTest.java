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

import com.liferay.osb.asah.common.converter.helper.DefaultFilterStringConverterHelper;
import com.liferay.osb.asah.common.entity.Account;
import com.liferay.osb.asah.common.postgresql.converter.helper.AccountsFilterStringConverterHelper;
import com.liferay.osb.asah.common.repository.AccountRepository;
import com.liferay.osb.asah.common.repository.ChannelRepository;
import com.liferay.osb.asah.common.repository.SegmentRepository;
import com.liferay.osb.asah.common.repository.helper.FilterHelper;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.annotation.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.annotation.RepositoryResource;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

/**
 * @author Rachael Koestartyo
 */
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
		name = "individuals", resourcePath = "individuals.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Override
	@RepositoryResource(
		repositoryClass = ChannelRepository.class,
		resourcePath = "osbasahfaroinfo/channels.json"
	)
	@RepositoryResource(
		repositoryClass = SegmentRepository.class,
		resourcePath = "osbasahfaroinfo/individual_segments.json"
	)
	@Test
	public void testGetAccountDistributions() throws Exception {
		setUpDataSources();

		super.testGetAccountDistributions();
	}

	@ElasticsearchIndex(
		name = "accounts", resourcePath = "accounts.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Override
	@Test
	public void testSearchAccounts() {
		AccountRepository accountRepository =
			(AccountRepository)getPagingAndSortingRepository();

		PageRequest pageRequest = PageRequest.of(
			0, 20, Sort.by(Sort.Order.asc("organization/field1/value")));

		List<Account> accounts = accountRepository.searchAccounts(
			null, null,
			new FilterHelper(
				_defaultFilterStringConverterHelper,
				"organization/field1/value eq 'field two'",
				_accountsFilterStringConverterHelper),
			pageRequest, Sort.by(Sort.Order.asc("individualCount")));

		Assertions.assertEquals(1, accounts.size(), accounts.toString());

		Optional<Account> accountOptional = accountRepository.findById(10L);

		Assertions.assertEquals(accountOptional.orElse(null), accounts.get(0));
	}

	private final AccountsFilterStringConverterHelper
		_accountsFilterStringConverterHelper =
			new AccountsFilterStringConverterHelper();
	private final DefaultFilterStringConverterHelper
		_defaultFilterStringConverterHelper =
			new DefaultFilterStringConverterHelper();

}