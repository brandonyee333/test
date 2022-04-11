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

package com.liferay.osb.asah.batch.curator.bot.nanite.test;

import com.liferay.osb.asah.batch.curator.bot.nanite.SalesforceIndividualsNanite;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.SalesforceEntityDog;
import com.liferay.osb.asah.common.entity.RunLog;
import com.liferay.osb.asah.common.entity.SalesforceAuditEvent;
import com.liferay.osb.asah.common.entity.SalesforceEntity;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.repository.RunLogRepository;
import com.liferay.osb.asah.common.repository.SalesforceAuditEventRepository;
import com.liferay.osb.asah.common.salesforce.extractor.dog.SalesforceExtractorConfigurationDog;
import com.liferay.osb.asah.common.util.TimeOrderedUuidGenerator;

import java.util.Date;
import java.util.Map;

import org.json.JSONObject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * @author Michael Bowerman
 */
public class SalesforceIndividualsNaniteTest
	extends BaseIndividualsNaniteTestCase {

	@BeforeEach
	@Override
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void testPopulate() throws Exception {
		addDataSource("SALESFORCE");
		addEmailFieldMapping();
		addStandardFieldMappings();

		SalesforceEntity salesforceEntity1 = _buildIndividualSalesforceEntity(
			getDataSourceId(), getIndividual1FieldsMap(), getIndividual1PK());

		salesforceEntity1.setIsNew(Boolean.TRUE);

		_salesforceEntityDog.saveSalesforceEntity(salesforceEntity1);

		SalesforceEntity salesforceEntity2 = _buildIndividualSalesforceEntity(
			getDataSourceId(), getIndividual2FieldsMap(), getIndividual2PK());

		salesforceEntity2.setIsNew(Boolean.TRUE);

		_salesforceEntityDog.saveSalesforceEntity(salesforceEntity2);

		SalesforceAuditEvent salesforceAuditEvent1 = _buildSalesforceAuditEvent(
			_salesforceEntityDog.getSalesforceEntity(
				getDataSourceId(), getIndividual1PK(),
				SalesforceEntity.Type.INDIVIDUAL));

		salesforceAuditEvent1.setIsNew(Boolean.TRUE);

		_salesforceAuditEventRepository.save(salesforceAuditEvent1);

		SalesforceAuditEvent salesforceAuditEvent2 = _buildSalesforceAuditEvent(
			_salesforceEntityDog.getSalesforceEntity(
				getDataSourceId(), getIndividual2PK(),
				SalesforceEntity.Type.INDIVIDUAL));

		salesforceAuditEvent2.setIsNew(Boolean.TRUE);

		_salesforceAuditEventRepository.save(salesforceAuditEvent2);

		RunLog runLog = new RunLog();

		runLog.setContextJSONObject(
			JSONUtil.put("dataSourceId", getDataSourceId()));
		runLog.setDataSourceId(getDataSourceId());
		runLog.setDateLogged(new Date());
		runLog.setNaniteClassName("SalesforceExtractorIndividualsNanite");
		runLog.setStatus("COMPLETED");

		_runLogRepository.save(runLog);

		_salesforceIndividualsNanite.run(
			JSONUtil.put(
				"dataSourceId", String.valueOf(getDataSourceId())
			).put(
				"type", "audit-events"
			));

		assertIndividuals();
	}

	@TestConfiguration
	public static class SalesforceIndividualsNaniteTestConfiguration {

		@Bean
		@Primary
		public SalesforceExtractorConfigurationDog
			salesforceExtractorConfigurationDog() {

			return Mockito.mock(SalesforceExtractorConfigurationDog.class);
		}

	}

	@Override
	protected String generateIndividualPK() {
		return _timeOrderedUuidGenerator.generateId();
	}

	@Override
	protected String getEmailDataSourceFieldName() {
		return "email";
	}

	private SalesforceAuditEvent _buildSalesforceAuditEvent(
		SalesforceEntity salesforceEntity) {

		SalesforceAuditEvent salesforceAuditEvent = new SalesforceAuditEvent();

		salesforceAuditEvent.setAdditionalInfoJSONObject(
			salesforceEntity.getFieldsJSONObject());
		salesforceAuditEvent.setCreateDate(DateUtil.newDate());
		salesforceAuditEvent.setDataSourceId(
			salesforceEntity.getDataSourceId());
		salesforceAuditEvent.setEntityTypeName("individuals");
		salesforceAuditEvent.setRecordId(salesforceEntity.getId());
		salesforceAuditEvent.setType(SalesforceAuditEvent.Type.UPDATE);

		return salesforceAuditEvent;
	}

	private SalesforceEntity _buildIndividualSalesforceEntity(
		Long dataSourceId, Map<String, Object> fieldsMap, String id) {

		JSONObject individualFieldsJSONObject = new JSONObject(fieldsMap);

		individualFieldsJSONObject.put("dataSourceId", dataSourceId);
		individualFieldsJSONObject.put("id", id);
		individualFieldsJSONObject.put(
			"modifiedDate", DateUtil.newDateString());

		return new SalesforceEntity(
			id, dataSourceId, individualFieldsJSONObject,
			SalesforceEntity.Type.INDIVIDUAL);
	}

	@Autowired
	private RunLogRepository _runLogRepository;

	@Autowired
	private SalesforceAuditEventRepository _salesforceAuditEventRepository;

	@Autowired
	private SalesforceEntityDog _salesforceEntityDog;

	@Autowired
	private SalesforceIndividualsNanite _salesforceIndividualsNanite;

	private final TimeOrderedUuidGenerator _timeOrderedUuidGenerator =
		new TimeOrderedUuidGenerator();

}