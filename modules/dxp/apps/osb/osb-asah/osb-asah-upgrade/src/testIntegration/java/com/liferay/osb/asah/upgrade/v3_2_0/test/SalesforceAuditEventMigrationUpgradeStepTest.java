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

package com.liferay.osb.asah.upgrade.v3_2_0.test;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchIndexManager;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.entity.SalesforceAuditEvent;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.common.repository.SalesforceAuditEventRepository;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.upgrade.OSBAsahUpgradeSpringTestContext;
import com.liferay.osb.asah.upgrade.v3_2_0.SalesforceAuditEventMigrationUpgradeStep;

import org.json.JSONArray;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Leilany Ulisses
 */
public class SalesforceAuditEventMigrationUpgradeStepTest
	implements OSBAsahUpgradeSpringTestContext {

	@BeforeEach
	public void setUp() throws Exception {
		ProjectIdThreadLocal.setProjectId("test");

		_salesforceAuditEventRepository.deleteAll();
		_dataSourceRepository.deleteAll();

		DataSource dataSource = new DataSource();

		dataSource.setId(342837044336786733L);
		dataSource.setIsNew(Boolean.TRUE);

		_dataSourceRepository.save(dataSource);

		_elasticsearchIndexManager.delete(
			"test_osbasahsalesforceraw_audit-events");

		_elasticsearchIndexManager.create(
			ResourceUtil.readResourceToString(
				"dependencies/audit_events_index_configuration.json", this),
			"test_osbasahsalesforceraw_audit-events");

		_elasticsearchIndexManager.addAlias(
			"test_osbasahsalesforceraw_audit-events_alias",
			"test_osbasahsalesforceraw_audit-events");
	}

	@AfterEach
	public void tearDown() throws Exception {
		_elasticsearchIndexManager.delete(
			"test_osbasahsalesforceraw_audit-events");

		_salesforceAuditEventRepository.deleteAll();
		_dataSourceRepository.deleteAll();
	}

	@Test
	public void testUpgrade() throws Exception {
		JSONArray jsonArray = new JSONArray(
			ResourceUtil.readResourceToString(
				"dependencies/audit_events.json", this));

		_salesforceRawElasticsearchInvoker.add("audit-events", jsonArray);

		_salesforceAuditEventMigrationUpgradeStep.upgrade("");

		Assertions.assertEquals(
			JSONUtil.toList(
				jsonArray,
				jsonObject -> _objectMapper.convertValue(
					jsonObject, SalesforceAuditEvent.class)),
			_salesforceAuditEventRepository.findAll());

		Assertions.assertTrue(
			_salesforceAuditEventMigrationUpgradeStep.isSequenceSync());
	}

	@Autowired
	private DataSourceRepository _dataSourceRepository;

	@Autowired
	private ElasticsearchIndexManager _elasticsearchIndexManager;

	@Autowired
	private ObjectMapper _objectMapper;

	@Autowired
	private SalesforceAuditEventMigrationUpgradeStep
		_salesforceAuditEventMigrationUpgradeStep;

	@Autowired
	private SalesforceAuditEventRepository _salesforceAuditEventRepository;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_SALESFORCE_RAW)
	private ElasticsearchInvoker _salesforceRawElasticsearchInvoker;

}