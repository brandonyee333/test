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
import com.liferay.osb.asah.common.entity.BQSalesforceEntity;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.repository.BQSalesforceEntityRepository;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
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
public class BQSalesforceEntityRepositoryTest
	implements OSBAsahCommonSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@BeforeEach
	public void setUp() {
		_dataSource = _addDataSource();

		_bqSalesforceEntityRepository.saveAll(
			Arrays.asList(
				new BQSalesforceEntity(
					"1", _dataSource.getId(), JSONUtil.put("Name", "Liferay"),
					BQSalesforceEntity.Type.ACCOUNT),
				new BQSalesforceEntity(
					"2", _dataSource.getId(),
					JSONUtil.put("Name", "Test Company"),
					BQSalesforceEntity.Type.ACCOUNT),
				new BQSalesforceEntity(
					"1", _dataSource.getId(),
					JSONUtil.put(
						"AccountId", "1"
					).put(
						"Email", "test@liferay.com"
					),
					BQSalesforceEntity.Type.CONTACT),
				new BQSalesforceEntity(
					"2", _dataSource.getId(),
					JSONUtil.put(
						"AccountId", "1"
					).put(
						"Email", "test@liferay.com"
					),
					BQSalesforceEntity.Type.CONTACT),
				new BQSalesforceEntity(
					"3", _dataSource.getId(),
					JSONUtil.put(
						"AccountId", "2"
					).put(
						"Email", "test@liferay.com"
					),
					BQSalesforceEntity.Type.CONTACT)));
	}

	@Test
	public void testDeleteByDataSourceId() {
		_bqSalesforceEntityRepository.deleteByDataSourceId(1L);

		Assertions.assertEquals(0, _bqSalesforceEntityRepository.count());
	}

	@Test
	public void testDeleteByFieldKeyAndFieldValueAndType() {
		_bqSalesforceEntityRepository.deleteByFieldKeyAndFieldValueAndType(
			"AccountId", "1", BQSalesforceEntity.Type.CONTACT);

		Assertions.assertEquals(3, _bqSalesforceEntityRepository.count());
	}

	@Test
	public void testFindByAfterAndFieldKeyAndFieldValueAndType() {
		List<BQSalesforceEntity> bqSalesforceEntities =
			_bqSalesforceEntityRepository.
				findByAfterAndFieldKeyAndFieldValueAndType(
					null, "AccountId", "1", 10,
					BQSalesforceEntity.Type.CONTACT);

		Assertions.assertEquals(2, bqSalesforceEntities.size());
	}

	@Test
	public void testFindByDataSourceIdAndFieldKeyEqualsAndType() {
		List<BQSalesforceEntity> bqSalesforceEntities =
			_bqSalesforceEntityRepository.
				findByDataSourceIdAndFieldKeyEqualsAndType(
					_dataSource.getId(), "Name", "Liferay",
					BQSalesforceEntity.Type.ACCOUNT);

		Assertions.assertEquals(
			1, bqSalesforceEntities.size(), bqSalesforceEntities.toString());

		BQSalesforceEntity bqSalesforceEntity = bqSalesforceEntities.get(0);

		Assertions.assertEquals(
			new BQSalesforceEntity(
				"1", _dataSource.getId(), JSONUtil.put("Name", "Liferay"),
				BQSalesforceEntity.Type.ACCOUNT),
			bqSalesforceEntity);
	}

	@Test
	public void testFindByDataSourceIdAndFieldKeyEqualsAndTypeGroupByFieldKey() {
		List<String> accountIds =
			_bqSalesforceEntityRepository.
				findByDataSourceIdAndFieldKeyEqualsAndTypeGroupByFieldKey(
					_dataSource.getId(), "Email", "test@liferay.com",
					BQSalesforceEntity.Type.CONTACT, "AccountId");

		Assertions.assertEquals(2, accountIds.size(), accountIds.toString());
		Assertions.assertEquals(Arrays.asList("1", "2"), accountIds);
	}

	@Test
	public void testFindByDataSourceIdAndType() {
		List<BQSalesforceEntity> bqSalesforceEntities =
			_bqSalesforceEntityRepository.findByDataSourceIdAndType(
				1L, BQSalesforceEntity.Type.CONTACT, PageRequest.of(0, 100));

		Assertions.assertEquals(3, bqSalesforceEntities.size());
	}

	@Test
	public void testUpdateBQSalesforceEntityFields() {
		_bqSalesforceEntityRepository.updateBQSalesforceEntityFields(
			1L,
			JSONUtil.put(
				"AccountId", "321"
			).put(
				"Email", "john.doe@liferay.com"
			),
			"1", BQSalesforceEntity.Type.CONTACT);

		List<BQSalesforceEntity> bqSalesforceEntities =
			_bqSalesforceEntityRepository.
				findByAfterAndFieldKeyAndFieldValueAndType(
					null, "AccountId", "321", 10,
					BQSalesforceEntity.Type.CONTACT);

		Assertions.assertEquals(1, bqSalesforceEntities.size());
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

	@Autowired
	private BQSalesforceEntityRepository _bqSalesforceEntityRepository;

	private DataSource _dataSource;

	@Autowired
	private DataSourceRepository _dataSourceRepository;

}