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
import com.liferay.osb.asah.batch.curator.spring.OSBAsahBatchCuratorSpringBootApplication;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.elasticsearch.impl.TimeOrderedUuidGenerator;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.salesforce.extractor.dog.SalesforceExtractorConfigurationDog;
import com.liferay.osb.asah.test.util.queue.http.CerebroQueueHttpTestConfiguration;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;
import com.liferay.osb.asah.test.util.spring.cache.OSBAsahRedisDisabledTestConfiguration;

import java.util.Map;

import org.json.JSONObject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * @author Michael Bowerman
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(
	classes = {
		CerebroQueueHttpTestConfiguration.class,
		OSBAsahBatchCuratorSpringBootApplication.class,
		OSBAsahRedisDisabledTestConfiguration.class
	}
)
public class SalesforceIndividualsNaniteTest
	extends BaseIndividualsNaniteTestCase {

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		_salesforceRawElasticsearchInvoker =
			_elasticsearchInvokerFactory.forSalesforceRaw();
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
					getDataSourceId(), getIndividual1FieldsMap(),
					getIndividual1PK()),
				_buildSalesforceIndividualJSONObject(
					getDataSourceId(), getIndividual2FieldsMap(),
					getIndividual2PK())));

		_salesforceRawElasticsearchInvoker.add(
			"audit-events",
			JSONUtil.putAll(
				_buildAuditEventJSONObject(
					getDataSourceId(),
					_salesforceRawElasticsearchInvoker.get(
						"individuals", getIndividual1PK())),
				_buildAuditEventJSONObject(
					getDataSourceId(),
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
				"dataSourceId", getDataSourceId()
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

	private static JSONObject _buildAuditEventJSONObject(
		String dataSourceId, JSONObject individualJSONObject) {

		return JSONUtil.put(
			"additionalInfo", individualJSONObject
		).put(
			"dateCreated", DateUtil.newDateString()
		).put(
			"eventType", "UPDATE"
		).put(
			"osbAsahDataSourceId", dataSourceId
		).put(
			"recordId", individualJSONObject.getString("id")
		).put(
			"typeName", "individuals"
		);
	}

	private static JSONObject _buildSalesforceIndividualJSONObject(
		String dataSourceId, Map<String, Object> fieldsMap, String id) {

		JSONObject individualJSONObject = new JSONObject(fieldsMap);

		return individualJSONObject.put(
			"id", id
		).put(
			"modifiedDate", DateUtil.newDateString()
		).put(
			"osbAsahDataSourceId", dataSourceId
		);
	}

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

	@Autowired
	private SalesforceIndividualsNanite _salesforceIndividualsNanite;

	private ElasticsearchInvoker _salesforceRawElasticsearchInvoker;
	private final TimeOrderedUuidGenerator _timeOrderedUuidGenerator =
		new TimeOrderedUuidGenerator();

}