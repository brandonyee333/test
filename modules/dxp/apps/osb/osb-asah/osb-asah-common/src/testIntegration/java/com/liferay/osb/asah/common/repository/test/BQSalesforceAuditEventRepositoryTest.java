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
import com.liferay.osb.asah.common.entity.BQSalesforceAuditEvent;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.repository.BQSalesforceAuditEventRepository;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

/**
 * @author Leilany Ulisses
 */
public class BQSalesforceAuditEventRepositoryTest
	implements OSBAsahCommonSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@BeforeEach
	public void setUp() {
		DataSource dataSource = new DataSource();

		dataSource.setId(123L);
		dataSource.setIsNew(Boolean.TRUE);

		_dataSourceRepository.save(dataSource);

		BQSalesforceAuditEvent bqSalesforceAuditEvent1 =
			new BQSalesforceAuditEvent();

		bqSalesforceAuditEvent1.setDataSourceId(123L);
		bqSalesforceAuditEvent1.setEntityTypeName("Account");
		bqSalesforceAuditEvent1.setIsNew(Boolean.TRUE);

		_bqSalesforceAuditEventRepository.save(bqSalesforceAuditEvent1);

		BQSalesforceAuditEvent bqSalesforceAuditEvent2 =
			new BQSalesforceAuditEvent();

		bqSalesforceAuditEvent2.setDataSourceId(134L);
		bqSalesforceAuditEvent2.setEntityTypeName("Contact");
		bqSalesforceAuditEvent2.setIsNew(Boolean.TRUE);

		_bqSalesforceAuditEventRepository.save(bqSalesforceAuditEvent2);
	}

	@AfterEach
	public void tearDown() {
		_bqSalesforceAuditEventRepository.deleteAll();
	}

	@Test
	public void testCountByDataSourceIdAndEntityTypeNameIn() {
		Assertions.assertEquals(
			1,
			_bqSalesforceAuditEventRepository.
				countByDataSourceIdAndEntityTypeNameIn(
					123L, Arrays.asList("Account", "Contact")));
	}

	@Test
	public void testFindByDataSourceIdAndEntityTypeName() {
		List<BQSalesforceAuditEvent> bqSalesforceAuditEvents =
			_bqSalesforceAuditEventRepository.
				findByDataSourceIdAndEntityTypeName(
					123L, "Account", PageRequest.of(0, 1));

		Assertions.assertEquals(1, bqSalesforceAuditEvents.size());
	}

	@Autowired
	private BQSalesforceAuditEventRepository _bqSalesforceAuditEventRepository;

	@Autowired
	private DataSourceRepository _dataSourceRepository;

}