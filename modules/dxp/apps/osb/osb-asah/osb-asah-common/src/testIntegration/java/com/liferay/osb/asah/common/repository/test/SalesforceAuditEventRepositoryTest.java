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
import com.liferay.osb.asah.common.entity.SalesforceAuditEvent;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.common.repository.SalesforceAuditEventRepository;
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
public class SalesforceAuditEventRepositoryTest
	implements OSBAsahCommonSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@BeforeEach
	public void setUp() {
		DataSource dataSource = new DataSource();

		dataSource.setId(123L);
		dataSource.setIsNew(Boolean.TRUE);

		_dataSourceRepository.save(dataSource);

		SalesforceAuditEvent salesforceAuditEvent1 = new SalesforceAuditEvent();

		salesforceAuditEvent1.setDataSourceId(123L);
		salesforceAuditEvent1.setEntityTypeName("Account");
		salesforceAuditEvent1.setIsNew(Boolean.TRUE);

		_salesforceAuditEventRepository.save(salesforceAuditEvent1);

		SalesforceAuditEvent salesforceAuditEvent2 = new SalesforceAuditEvent();

		salesforceAuditEvent2.setDataSourceId(134L);
		salesforceAuditEvent2.setEntityTypeName("Contact");
		salesforceAuditEvent2.setIsNew(Boolean.TRUE);

		_salesforceAuditEventRepository.save(salesforceAuditEvent2);
	}

	@AfterEach
	public void tearDown() {
		_salesforceAuditEventRepository.deleteAll();
	}

	@Test
	public void testCountByDataSourceIdAndEntityTypeNameIn() {
		Assertions.assertEquals(
			1,
			_salesforceAuditEventRepository.
				countByDataSourceIdAndEntityTypeNameIn(
					123L, Arrays.asList("Account", "Contact")));
	}

	@Test
	public void testFindByDataSourceIdAndEntityTypeName() {
		List<SalesforceAuditEvent> salesforceAuditEvents =
			_salesforceAuditEventRepository.findByDataSourceIdAndEntityTypeName(
				123L, "Account", PageRequest.of(0, 1));

		Assertions.assertEquals(1, salesforceAuditEvents.size());
	}

	@Autowired
	private DataSourceRepository _dataSourceRepository;

	@Autowired
	private SalesforceAuditEventRepository _salesforceAuditEventRepository;

}