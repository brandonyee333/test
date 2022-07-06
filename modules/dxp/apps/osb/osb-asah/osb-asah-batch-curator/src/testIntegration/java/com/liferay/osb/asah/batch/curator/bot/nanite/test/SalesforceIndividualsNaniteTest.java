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
import com.liferay.osb.asah.common.dog.BQSalesforceEntityDog;
import com.liferay.osb.asah.common.entity.BQSalesforceAuditEvent;
import com.liferay.osb.asah.common.entity.BQSalesforceEntity;
import com.liferay.osb.asah.common.entity.RunLog;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.repository.BQSalesforceAuditEventRepository;
import com.liferay.osb.asah.common.repository.RunLogRepository;
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

		BQSalesforceEntity bqSalesforceEntity1 =
			_buildIndividualBQSalesforceEntity(
				getDataSourceId(), getIndividual1FieldsMap(), getUser1PK());

		bqSalesforceEntity1.setIsNew(Boolean.TRUE);

		_bqSalesforceEntityDog.saveBQSalesforceEntity(bqSalesforceEntity1);

		BQSalesforceEntity salesforceEntity2 =
			_buildIndividualBQSalesforceEntity(
				getDataSourceId(), getIndividual2FieldsMap(), getUser2PK());

		salesforceEntity2.setIsNew(Boolean.TRUE);

		_bqSalesforceEntityDog.saveBQSalesforceEntity(salesforceEntity2);

		BQSalesforceAuditEvent bqSalesforceAuditEvent1 =
			_buildBQSalesforceAuditEvent(
				_bqSalesforceEntityDog.getBQSalesforceEntity(
					getDataSourceId(), getUser1PK(),
					BQSalesforceEntity.Type.INDIVIDUAL));

		bqSalesforceAuditEvent1.setIsNew(Boolean.TRUE);

		_bqSalesforceAuditEventRepository.save(bqSalesforceAuditEvent1);

		BQSalesforceAuditEvent bqSalesforceAuditEvent2 =
			_buildBQSalesforceAuditEvent(
				_bqSalesforceEntityDog.getBQSalesforceEntity(
					getDataSourceId(), getUser2PK(),
					BQSalesforceEntity.Type.INDIVIDUAL));

		bqSalesforceAuditEvent2.setIsNew(Boolean.TRUE);

		_bqSalesforceAuditEventRepository.save(bqSalesforceAuditEvent2);

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
	protected String generateUserPK() {
		return _timeOrderedUuidGenerator.generateId();
	}

	@Override
	protected String getEmailDataSourceFieldName() {
		return "email";
	}

	private BQSalesforceAuditEvent _buildBQSalesforceAuditEvent(
		BQSalesforceEntity bqSalesforceEntity) {

		BQSalesforceAuditEvent bqSalesforceAuditEvent =
			new BQSalesforceAuditEvent();

		bqSalesforceAuditEvent.setAdditionalInfoJSONObject(
			bqSalesforceEntity.getFieldsJSONObject());
		bqSalesforceAuditEvent.setCreateDate(DateUtil.newDate());
		bqSalesforceAuditEvent.setDataSourceId(
			bqSalesforceEntity.getDataSourceId());
		bqSalesforceAuditEvent.setEntityTypeName("individuals");
		bqSalesforceAuditEvent.setRecordId(bqSalesforceEntity.getId());
		bqSalesforceAuditEvent.setType(BQSalesforceAuditEvent.Type.UPDATE);

		return bqSalesforceAuditEvent;
	}

	private BQSalesforceEntity _buildIndividualBQSalesforceEntity(
		Long dataSourceId, Map<String, Object> fieldsMap, String id) {

		JSONObject userFieldsJSONObject = new JSONObject(fieldsMap);

		userFieldsJSONObject.put("dataSourceId", dataSourceId);
		userFieldsJSONObject.put("id", id);
		userFieldsJSONObject.put("modifiedDate", DateUtil.newDateString());

		return new BQSalesforceEntity(
			id, dataSourceId, userFieldsJSONObject,
			BQSalesforceEntity.Type.INDIVIDUAL);
	}

	@Autowired
	private BQSalesforceAuditEventRepository _bqSalesforceAuditEventRepository;

	@Autowired
	private BQSalesforceEntityDog _bqSalesforceEntityDog;

	@Autowired
	private RunLogRepository _runLogRepository;

	@Autowired
	private SalesforceIndividualsNanite _salesforceIndividualsNanite;

	private final TimeOrderedUuidGenerator _timeOrderedUuidGenerator =
		new TimeOrderedUuidGenerator();

}