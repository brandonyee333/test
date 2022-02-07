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
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.salesforce.extractor.dog.SalesforceExtractorConfigurationDog;
import com.liferay.osb.asah.common.util.TimeOrderedUuidGenerator;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

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

		_salesforceRawElasticsearchInvoker.add(
			"individuals",
			JSONUtil.putAll(
				_buildSalesforceIndividualJSONObject(
					String.valueOf(getDataSourceId()),
					getIndividual1FieldsMap(), getIndividual1PK()),
				_buildSalesforceIndividualJSONObject(
					String.valueOf(getDataSourceId()),
					getIndividual2FieldsMap(), getIndividual2PK())));

		_salesforceRawElasticsearchInvoker.add(
			"audit-events",
			JSONUtil.putAll(
				_buildAuditEventJSONObject(
					String.valueOf(getDataSourceId()),
					_salesforceRawElasticsearchInvoker.get(
						"individuals", getIndividual1PK())),
				_buildAuditEventJSONObject(
					String.valueOf(getDataSourceId()),
					_salesforceRawElasticsearchInvoker.get(
						"individuals", getIndividual2PK()))));

		_salesforceRawElasticsearchInvoker.add(
			"run-logs",
			JSONUtil.put(
				"dataSourceId", getDataSourceId()
			).put(
				"dateLogged", DateUtil.newDateString()
			).put(
				"naniteClassName", "SalesforceExtractorIndividualsNanite"
			).put(
				"status", "COMPLETED"
			));

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

	private JSONObject _buildAuditEventJSONObject(
		String dataSourceId, JSONObject individualJSONObject) {

		return JSONUtil.put(
			"additionalInfo", individualJSONObject
		).put(
			"dataSourceId", dataSourceId
		).put(
			"dateCreated", DateUtil.newDateString()
		).put(
			"eventType", "UPDATE"
		).put(
			"recordId", individualJSONObject.getString("id")
		).put(
			"typeName", "individuals"
		);
	}

	private JSONObject _buildSalesforceIndividualJSONObject(
		String dataSourceId, Map<String, Object> fieldsMap, String id) {

		JSONObject individualFieldsJSONObject = new JSONObject(fieldsMap);

		individualFieldsJSONObject.put("dataSourceId", dataSourceId);
		individualFieldsJSONObject.put("id", id);
		individualFieldsJSONObject.put(
			"modifiedDate", DateUtil.newDateString());

		return JSONUtil.put(
			"dataSourceId", dataSourceId
		).put(
			"fields", individualFieldsJSONObject
		).put(
			"id", id
		);
	}

	@Autowired
	private SalesforceIndividualsNanite _salesforceIndividualsNanite;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_SALESFORCE_RAW)
	private ElasticsearchInvoker _salesforceRawElasticsearchInvoker;

	private final TimeOrderedUuidGenerator _timeOrderedUuidGenerator =
		new TimeOrderedUuidGenerator();

}