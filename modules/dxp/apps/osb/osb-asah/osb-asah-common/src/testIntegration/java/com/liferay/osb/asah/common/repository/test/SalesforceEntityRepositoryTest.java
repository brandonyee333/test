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

import com.liferay.osb.asah.common.OSBAsahCommonSpringTestContext;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.entity.SalesforceEntity;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.common.repository.SalesforceEntityRepository;
import com.liferay.osb.asah.test.util.configuration.JDBCTestConfiguration;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;

/**
 * @author Marcellus Tavares
 */
@Import(JDBCTestConfiguration.class)
public class SalesforceEntityRepositoryTest
	implements OSBAsahCommonSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@BeforeEach
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
	public void testDeleteByDataSourceId() {
		_salesforceEntityRepository.deleteByDataSourceId(1L);

		Assertions.assertEquals(0, _salesforceEntityRepository.count());
	}

	@Test
	public void testDeleteByFieldKeyAndFieldValueAndType() {
		_salesforceEntityRepository.deleteByFieldKeyAndFieldValueAndType(
			"AccountId", "1", SalesforceEntity.Type.CONTACT);

		Assertions.assertEquals(3, _salesforceEntityRepository.count());
	}

	@Test
	public void testFindByAfterAndFieldKeyAndFieldValueAndType() {
		List<SalesforceEntity> salesforceEntities =
			_salesforceEntityRepository.
				findByAfterAndFieldKeyAndFieldValueAndType(
					null, "AccountId", "1", 10, SalesforceEntity.Type.CONTACT);

		Assertions.assertEquals(2, salesforceEntities.size());
	}

	@Test
	public void testFindByDataSourceIdAndFieldKeyEqualsAndType() {
		List<SalesforceEntity> salesforceEntities =
			_salesforceEntityRepository.
				findByDataSourceIdAndFieldKeyEqualsAndType(
					_dataSource.getId(), "Name", "Liferay",
					SalesforceEntity.Type.ACCOUNT);

		Assertions.assertEquals(
			1, salesforceEntities.size(), salesforceEntities.toString());

		SalesforceEntity salesforceEntity = salesforceEntities.get(0);

		Assertions.assertEquals(
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

		Assertions.assertEquals(2, accountIds.size(), accountIds.toString());
		Assertions.assertEquals(Arrays.asList("1", "2"), accountIds);
	}

	@Test
	public void testFindByDataSourceIdAndType() {
		List<SalesforceEntity> salesforceEntities =
			_salesforceEntityRepository.findByDataSourceIdAndType(
				1L, SalesforceEntity.Type.CONTACT, PageRequest.of(0, 100));

		Assertions.assertEquals(3, salesforceEntities.size());
	}

	@Test
	public void testUpdateSalesforceEntityFields() {
		_salesforceEntityRepository.updateSalesforceEntityFields(
			1L,
			JSONUtil.put(
				"AccountId", "321"
			).put(
				"Email", "john.doe@liferay.com"
			),
			"1", SalesforceEntity.Type.CONTACT);

		List<SalesforceEntity> salesforceEntities =
			_salesforceEntityRepository.
				findByAfterAndFieldKeyAndFieldValueAndType(
					null, "AccountId", "321", 10,
					SalesforceEntity.Type.CONTACT);

		Assertions.assertEquals(1, salesforceEntities.size());
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