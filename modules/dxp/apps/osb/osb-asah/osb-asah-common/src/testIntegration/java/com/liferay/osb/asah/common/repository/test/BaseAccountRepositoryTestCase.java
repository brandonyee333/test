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

import com.liferay.osb.asah.common.model.Account;
import com.liferay.osb.asah.common.model.Channel;
import com.liferay.osb.asah.common.model.DataSource;
import com.liferay.osb.asah.common.model.Field;
import com.liferay.osb.asah.common.model.Segment;
import com.liferay.osb.asah.common.repository.AccountRepository;
import com.liferay.osb.asah.common.repository.ChannelRepository;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.common.repository.FieldRepository;
import com.liferay.osb.asah.common.repository.SegmentRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Rachael Koestartyo
 */
public abstract class BaseAccountRepositoryTestCase
	extends BaseRepositoryTestCase<Account> {

	@Before
	public void setUp() {
		DataSource dataSource1 = new DataSource("Liferay Brazil");

		dataSource1.setCredentialType("Token Authentication");

		Channel channel1 = new Channel("channel1");

		channel1.setId(11L);
		channel1.setIsNew(true);

		_channelRepository.save(channel1);

		dataSource1.setChannelId(channel1.getId());

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

		_fieldRepository.save(field1);

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

		_fieldRepository.save(field2);

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

		_fieldRepository.save(field3);

		Segment segment = new Segment();

		segment.setActiveIndividualCount(2L);
		segment.setActivitiesCount(3L);
		segment.setAnonymousIndividualCount(1L);
		segment.setCreateDate(new Date());
		segment.setFilter(
			"((dataSourceAccountPKs/accountPKs eq 'testAccount'))");
		segment.setIndividualCount(2L);
		segment.setLastActivityDate(new Date());
		segment.setModifiedDate(new Date());
		segment.setName("Account: " + _accountId);
		segment.setState("READY");
		segment.setStatus("INACTIVE");
		segment.setType(Segment.Type.DYNAMIC);

		_segmentRepository.save(segment);
	}

	@Test
	public void testCountAccounts() {
		Assert.assertEquals(
			1,
			_accountRepository.countAccounts(
				"organization/field1/value eq 'field two'"));
	}

	@Test
	public void testSearchAccounts() {
		PageRequest pageRequest = PageRequest.of(
			0, 20, Sort.by(Sort.Order.asc("organization/field1/value")));

		List<Account> accounts = _accountRepository.searchAccounts(
			null, "organization/field1/value eq 'field two'", pageRequest,
			Sort.by(Sort.Order.asc("individualCount")));

		Assert.assertEquals(accounts.toString(), 1, accounts.size());

		Optional<Account> accountOptional = _accountRepository.findById(
			_accountId);

		Assert.assertEquals(accountOptional.orElse(null), accounts.get(0));
	}

	@Override
	protected CrudRepository<Account, Long> getCrudRepository() {
		return _accountRepository;
	}

	private Long _accountId;

	@Autowired
	private AccountRepository _accountRepository;

	@Autowired
	private ChannelRepository _channelRepository;

	@Autowired
	private DataSourceRepository _dataSourceRepository;

	@Autowired
	private FieldRepository _fieldRepository;

	@Autowired
	private SegmentRepository _segmentRepository;

}