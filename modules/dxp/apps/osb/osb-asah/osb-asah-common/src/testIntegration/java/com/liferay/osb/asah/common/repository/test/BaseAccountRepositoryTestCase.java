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
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.entity.Account;
import com.liferay.osb.asah.common.entity.Channel;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.entity.Field;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.model.Distribution;
import com.liferay.osb.asah.common.postgresql.converter.helper.AccountsFilterStringConverterHelper;
import com.liferay.osb.asah.common.repository.AccountRepository;
import com.liferay.osb.asah.common.repository.ChannelRepository;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.common.repository.FieldMappingRepository;
import com.liferay.osb.asah.common.repository.FieldRepository;
import com.liferay.osb.asah.common.repository.IndividualRepository;
import com.liferay.osb.asah.common.repository.SegmentRepository;
import com.liferay.osb.asah.common.repository.helper.FilterHelper;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

import org.yaml.snakeyaml.util.ArrayUtils;

/**
 * @author Rachael Koestartyo
 */
public abstract class BaseAccountRepositoryTestCase
	extends BaseRepositoryTestCase<Account, Long> {

	@BeforeEach
	public void setUp() {
		DataSource dataSource1 = new DataSource("Liferay Brazil");

		dataSource1.setCredentialType("Token Authentication");

		Channel channel1 = new Channel("channel1");

		channel1.setId(11L);
		channel1.setIsNew(true);

		_channelRepository.save(channel1);

		dataSource1.setFaroBackendSecuritySignature(
			"faroBackendSecuritySignature");
		dataSource1.setId(1L);
		dataSource1.setIsNew(true);
		dataSource1.setProviderType("LIFERAY");
		dataSource1.setState("READY");
		dataSource1.setStatus("STARTED");
		dataSource1.setURL("");

		_dataSourceRepository.save(dataSource1);

		Account account = new Account();

		account.setAccountPK("testAccount");
		account.setCreateDate(new Date());
		account.setDataSourceId(dataSource1.getId());
		account.setModifiedDate(new Date());

		setUpRepository(account);

		account = entityModels.get(0);

		_accountId = account.getId();

		Field field1 = new Field();

		field1.setContext("organization");
		field1.setDataSourceId(account.getDataSourceId());
		field1.setDataSourceName("Source 1");
		field1.setFieldType("Text");
		field1.setModifiedDate(new Date(System.currentTimeMillis() - 10000));
		field1.setName("field1");
		field1.setOwnerId(_accountId);
		field1.setOwnerType("account");
		field1.setSourceName("Field 1");
		field1.setValue("field one");

		fieldRepository.save(field1);

		Field field2 = new Field();

		field2.setContext("organization");
		field2.setDataSourceId(account.getDataSourceId());
		field2.setDataSourceName("Source 1");
		field2.setFieldType("Text");
		field2.setModifiedDate(new Date(System.currentTimeMillis()));
		field2.setName("field1");
		field2.setOwnerId(_accountId);
		field2.setOwnerType("account");
		field2.setSourceName("Field 1");
		field2.setValue("field two");

		fieldRepository.save(field2);

		Field field3 = new Field();

		field3.setContext("organization");
		field3.setDataSourceId(account.getDataSourceId());
		field3.setDataSourceName("Source 1");
		field3.setFieldType("Text");
		field3.setModifiedDate(new Date());
		field3.setName("field3");
		field3.setOwnerId(_accountId);
		field3.setOwnerType("account");
		field3.setSourceName("Field 3");
		field3.setValue("field three");

		fieldRepository.save(field3);

		Segment segment = new Segment();

		segment.setActiveIndividualsCount(2L);
		segment.setActivitiesCount(3L);
		segment.setAnonymousIndividualsCount(1L);
		segment.setCreateDate(new Date());
		segment.setFilter(
			"((dataSourceAccountPKs/accountPKs eq 'testAccount'))");
		segment.setIndividualsCount(2L);
		segment.setLastActivityDate(new Date());
		segment.setModifiedDate(new Date());
		segment.setName("Account: " + _accountId);
		segment.setState("READY");
		segment.setStatus("INACTIVE");
		segment.setType(Segment.Type.DYNAMIC);

		segmentRepository.save(segment);
	}

	@Override
	public void tearDown() {
		super.tearDown();

		segmentRepository.deleteAll();

		_fieldMappingRepository.deleteAll();

		_channelRepository.deleteAll();
		_dataSourceRepository.deleteAll();

		fieldRepository.deleteAll();
		individualRepository.deleteAll();
	}

	@Test
	public void testCountAccounts() {
		Assertions.assertEquals(
			1,
			accountRepository.countAccounts(
				null,
				new FilterHelper(
					_defaultFilterStringConverterHelper,
					"organization/field1/value eq 'field two'",
					_accountsFilterStringConverterHelper)));
	}

	@Test
	public void testCountByCreateDateBetweenAndIdAfter() {
		Date todayDate = new Date();
		Date yesterdayDate = DateUtil.addDays(new Date(), -1);

		Assertions.assertEquals(
			1,
			accountRepository.countByCreateDateBetweenAndIdAfter(
				yesterdayDate, todayDate, 0L));
		Assertions.assertEquals(
			0,
			accountRepository.countByCreateDateBetweenAndIdAfter(
				yesterdayDate, todayDate, _accountId));
	}

	@Test
	public void testFindByCreateDateBetweenAndIdAfter() {
		List<Account> accounts =
			accountRepository.findByCreateDateBetweenAndIdAfter(
				DateUtil.addDays(new Date(), -1), new Date(), _accountId - 1L,
				PageRequest.of(0, 1));

		Assertions.assertEquals(1, accounts.size(), accounts.toString());

		Account account = accounts.get(0);

		Assertions.assertEquals(_accountId, account.getId());
	}

	@Test
	public void testGetAccountDistributions() throws Exception {
		List<Distribution> distributions =
			accountRepository.getAccountDistributions(
				individualRepository.findAccountPKsByChannelIdAndSegmentId(
					1L, 366637689379787789L),
				"numberOfEmployees", "Number", FilterHelper.EMPTY,
				PageRequest.of(0, 10, Sort.by(Sort.Order.asc("count"))));

		_assertEquals(
			distributions.get(0), 1,
			ArrayUtils.toUnmodifiableList(new Integer[] {20000, 20000}));

		distributions = accountRepository.getAccountDistributions(
			individualRepository.findAccountPKsByChannelIdAndSegmentId(
				1L, null),
			"numberOfEmployees", "Number",
			new FilterHelper(
				_defaultFilterStringConverterHelper,
				"organization/billingState/value eq 'New York'",
				_accountsFilterStringConverterHelper),
			PageRequest.of(0, 10, Sort.by(Sort.Order.asc("count"))));

		_assertEquals(
			distributions.get(0), 1,
			ArrayUtils.toUnmodifiableList(new Integer[] {20000, 20000}));

		distributions = accountRepository.getAccountDistributions(
			individualRepository.findAccountPKsByChannelIdAndSegmentId(
				1L, null),
			"shippingPostalCode", "Text", FilterHelper.EMPTY,
			PageRequest.of(0, 100, Sort.by(Sort.Order.desc("name"))));

		_assertEquals(
			distributions.get(0), 1,
			ArrayUtils.toUnmodifiableList(new String[] {"91789"}));
		_assertEquals(
			distributions.get(1), 1,
			ArrayUtils.toUnmodifiableList(new String[] {"91765"}));

		distributions = accountRepository.getAccountDistributions(
			individualRepository.findAccountPKsByChannelIdAndSegmentId(
				1L, null),
			"shippingPostalCode", "Text", FilterHelper.EMPTY,
			PageRequest.of(0, 100, Sort.by(Sort.Order.asc("count"))));

		_assertEquals(
			distributions.get(0), 1,
			ArrayUtils.toUnmodifiableList(new String[] {"91765"}));
		_assertEquals(
			distributions.get(1), 1,
			ArrayUtils.toUnmodifiableList(new String[] {"91789"}));

		distributions = accountRepository.getAccountDistributions(
			individualRepository.findAccountPKsByChannelIdAndSegmentId(
				1L, null),
			"shippingPostalCode", "Text", FilterHelper.EMPTY,
			PageRequest.of(0, 1, Sort.by(Sort.Order.asc("count"))));

		Assertions.assertEquals(
			1, distributions.size(), distributions.toString());

		_assertEquals(
			distributions.get(0), 1,
			ArrayUtils.toUnmodifiableList(new String[] {"91765"}));

		distributions = accountRepository.getAccountDistributions(
			individualRepository.findAccountPKsByChannelIdAndSegmentId(
				1L, null),
			"numberOfEmployees", "Number", FilterHelper.EMPTY,
			PageRequest.of(0, 10, Sort.by(Sort.Order.asc("values"))));

		Assertions.assertEquals(
			10, distributions.size(), distributions.toString());

		_assertEquals(
			distributions.get(0), 1,
			ArrayUtils.toUnmodifiableList(new Integer[] {2000, 3800}));
		_assertEquals(
			distributions.get(1), 0,
			ArrayUtils.toUnmodifiableList(new Integer[] {3800, 5600}));
		_assertEquals(
			distributions.get(2), 0,
			ArrayUtils.toUnmodifiableList(new Integer[] {5600, 7400}));
		_assertEquals(
			distributions.get(3), 0,
			ArrayUtils.toUnmodifiableList(new Integer[] {7400, 9200}));
		_assertEquals(
			distributions.get(4), 0,
			ArrayUtils.toUnmodifiableList(new Integer[] {9200, 11000}));
		_assertEquals(
			distributions.get(5), 0,
			ArrayUtils.toUnmodifiableList(new Integer[] {11000, 12800}));
		_assertEquals(
			distributions.get(6), 0,
			ArrayUtils.toUnmodifiableList(new Integer[] {12800, 14600}));
		_assertEquals(
			distributions.get(7), 0,
			ArrayUtils.toUnmodifiableList(new Integer[] {14600, 16400}));
		_assertEquals(
			distributions.get(8), 0,
			ArrayUtils.toUnmodifiableList(new Integer[] {16400, 18200}));
		_assertEquals(
			distributions.get(9), 1,
			ArrayUtils.toUnmodifiableList(new Integer[] {18200, 20000}));
	}

	@Test
	public void testSearchAccounts() {
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

		Optional<Account> accountOptional = accountRepository.findById(
			_accountId);

		Assertions.assertEquals(accountOptional.orElse(null), accounts.get(0));
	}

	@Override
	protected PagingAndSortingRepository<Account, Long>
		getPagingAndSortingRepository() {

		return accountRepository;
	}

	protected void setUpDataSources() {
		Channel channel = new Channel();

		channel.setId(1L);
		channel.setIsNew(true);

		_channelRepository.save(channel);

		DataSource dataSource = FaroInfoTestUtil.buildSalesforceDataSource();

		dataSource.setId(337984445922213329L);
		dataSource.setIsNew(true);

		_dataSourceRepository.save(dataSource);

		dataSource = FaroInfoTestUtil.buildSalesforceDataSource();

		dataSource.setId(342312716287315687L);
		dataSource.setIsNew(true);

		_dataSourceRepository.save(dataSource);

		dataSource = FaroInfoTestUtil.buildSalesforceDataSource();

		dataSource.setId(342312716287315688L);
		dataSource.setIsNew(true);

		_dataSourceRepository.save(dataSource);

		dataSource = FaroInfoTestUtil.buildSalesforceDataSource();

		dataSource.setId(414985450066315739L);
		dataSource.setIsNew(true);

		_dataSourceRepository.save(dataSource);

		dataSource = FaroInfoTestUtil.buildLiferayDataSource();

		dataSource.setId(331238757269547423L);
		dataSource.setIsNew(true);

		_dataSourceRepository.save(dataSource);

		dataSource = FaroInfoTestUtil.buildLiferayDataSource();

		dataSource.setId(351238757269547424L);
		dataSource.setIsNew(true);

		_dataSourceRepository.save(dataSource);

		dataSource = FaroInfoTestUtil.buildLiferayDataSource();

		dataSource.setId(351238757269547425L);
		dataSource.setIsNew(true);

		_dataSourceRepository.save(dataSource);

		dataSource = FaroInfoTestUtil.buildLiferayDataSource();

		dataSource.setId(366588399828298918L);
		dataSource.setIsNew(true);

		_dataSourceRepository.save(dataSource);

		dataSource = FaroInfoTestUtil.buildLiferayDataSource();

		dataSource.setId(366588441118531472L);
		dataSource.setIsNew(true);

		_dataSourceRepository.save(dataSource);

		dataSource = FaroInfoTestUtil.buildLiferayDataSource();

		dataSource.setId(366588489711802687L);
		dataSource.setIsNew(true);

		_dataSourceRepository.save(dataSource);

		dataSource = FaroInfoTestUtil.buildLiferayDataSource();

		dataSource.setId(366573382114568984L);
		dataSource.setIsNew(true);

		_dataSourceRepository.save(dataSource);
	}

	@Autowired
	protected AccountRepository accountRepository;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	protected ElasticsearchInvoker elasticsearchInvoker;

	@Autowired
	protected FieldMappingRepository fieldMappingRepository;

	@Autowired
	protected FieldRepository fieldRepository;

	@Autowired
	protected IndividualRepository individualRepository;

	@Autowired
	protected SegmentRepository segmentRepository;

	private void _assertEquals(
		Distribution distribution, int expectedCount,
		List<Object> expectedValues) {

		Assertions.assertEquals(expectedCount, (int)distribution.getCount());
		Assertions.assertEquals(expectedValues, distribution.getValues());
	}

	private Long _accountId;
	private final AccountsFilterStringConverterHelper
		_accountsFilterStringConverterHelper =
			new AccountsFilterStringConverterHelper();

	@Autowired
	private ChannelRepository _channelRepository;

	@Autowired
	private DataSourceRepository _dataSourceRepository;

	private final DefaultFilterStringConverterHelper
		_defaultFilterStringConverterHelper =
			new DefaultFilterStringConverterHelper();

	@Autowired
	private FieldMappingRepository _fieldMappingRepository;

}