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

import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.entity.SalesforceEntity;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.common.repository.SalesforceEntityRepository;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Marcellus Tavares
 */
@ContextConfiguration(classes = OSBAsahSpringBootApplication.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class SalesforceEntityRepositoryTest {

	@Before
	public void setUp() {
		_dataSource = _addDataSource();

		_salesforceEntityRepository.saveAll(
			Arrays.asList(
				new SalesforceEntity(
					"1", _dataSource.getId(), JSONUtil.put("Name", "Liferay"),
					SalesforceEntity.Type.ACCOUNT),
				new SalesforceEntity(
					"2", _dataSource.getId(),
					JSONUtil.put("Name", "Test Company"),
					SalesforceEntity.Type.ACCOUNT),
				new SalesforceEntity(
					"1", _dataSource.getId(),
					JSONUtil.put(
						"AccountId", "1"
					).put(
						"Email", "test@liferay.com"
					),
					SalesforceEntity.Type.CONTACT),
				new SalesforceEntity(
					"2", _dataSource.getId(),
					JSONUtil.put(
						"AccountId", "1"
					).put(
						"Email", "test@liferay.com"
					),
					SalesforceEntity.Type.CONTACT),
				new SalesforceEntity(
					"3", _dataSource.getId(),
					JSONUtil.put(
						"AccountId", "2"
					).put(
						"Email", "test@liferay.com"
					),
					SalesforceEntity.Type.CONTACT)));
	}

	@Test
	public void testFindByDataSourceIdAndFieldKeyEqualsAndType() {
		List<SalesforceEntity> salesforceEntities =
			_salesforceEntityRepository.
				findByDataSourceIdAndFieldKeyEqualsAndType(
					_dataSource.getId(), "Name", "Liferay",
					SalesforceEntity.Type.ACCOUNT);

		Assert.assertEquals(
			salesforceEntities.toString(), 1, salesforceEntities.size());

		SalesforceEntity salesforceEntity = salesforceEntities.get(0);

		Assert.assertEquals(
			new SalesforceEntity(
				"1", _dataSource.getId(), JSONUtil.put("Name", "Liferay"),
				SalesforceEntity.Type.ACCOUNT),
			salesforceEntity);
	}

	@Test
	public void testFindByDataSourceIdAndFieldKeyEqualsAndTypeGroupByFieldKey() {
		List<String> accountIds =
			_salesforceEntityRepository.
				findByDataSourceIdAndFieldKeyEqualsAndTypeGroupByFieldKey(
					_dataSource.getId(), "Email", "test@liferay.com",
					SalesforceEntity.Type.CONTACT, "AccountId");

		Assert.assertEquals(accountIds.toString(), 2, accountIds.size());
		Assert.assertEquals(Arrays.asList("1", "2"), accountIds);
	}

	private DataSource _addDataSource() {
		DataSource dataSource = new DataSource("Liferay Brazil");

		dataSource.setFaroBackendSecuritySignature(
			"faroBackendSecuritySignature");
		dataSource.setId(1L);
		dataSource.setIsNew(true);
		dataSource.setProviderType("LIFERAY");
		dataSource.setState("READY");
		dataSource.setStatus("STARTED");
		dataSource.setURL("");

		return _dataSourceRepository.save(dataSource);
	}

	private DataSource _dataSource;

	@Autowired
	private DataSourceRepository _dataSourceRepository;

	@Autowired
	private SalesforceEntityRepository _salesforceEntityRepository;

}