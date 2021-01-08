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

import com.liferay.osb.asah.batch.curator.bot.nanite.DXPIndividualsNanite;
import com.liferay.osb.asah.batch.curator.spring.OSBAsahBatchCuratorSpringBootApplication;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dxp.extractor.dog.DXPExtractorConfigurationDog;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.http.ChannelHttp;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.queue.http.CerebroQueueHttpTestConfiguration;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;
import com.liferay.osb.asah.test.util.spring.cache.OSBAsahRedisDisabledTestConfiguration;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import java.util.Map;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Vishal Reddy
 */
@ContextConfiguration(classes = OSBAsahBatchCuratorSpringBootApplication.class)
@Import(
	{
		CerebroQueueHttpTestConfiguration.class,
		OSBAsahRedisDisabledTestConfiguration.class
	}
)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class DXPIndividualsNaniteTest extends BaseIndividualsNaniteTestCase {

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		_dxpRawElasticsearchInvoker = _elasticsearchInvokerFactory.forDXPRaw();

		addDataSource("LIFERAY");
		addEmailFieldMapping();
		addStandardFieldMappings();

		_dxpRawElasticsearchInvoker.add(
			"users",
			JSONUtil.putAll(
				_buildUserJSONObject(
					getDataSourceId(), getIndividual1PK(),
					getIndividual1FieldsMap(), getIndividual1UserId()),
				_buildUserJSONObject(
					getDataSourceId(), getIndividual2PK(),
					getIndividual2FieldsMap(), getIndividual2UserId())));

		_dxpRawElasticsearchInvoker.add(
			"faro-audit-events",
			JSONUtil.putAll(
				_buildFaroAuditEventJSONObject(
					getDataSourceId(), getIndividual1Email(), "ADD",
					getIndividual1UserId()),
				_buildFaroAuditEventJSONObject(
					getDataSourceId(), getIndividual2Email(), "ADD",
					getIndividual2UserId())));

		_dxpRawElasticsearchInvoker.add(
			"run-logs",
			JSONUtil.put(
				"dataSourceId", getDataSourceId()
			).put(
				"dateLogged", DateUtil.newDateString()
			).put(
				"naniteClassName", "DXPExtractorNanite"
			).put(
				"status", "COMPLETED"
			));

		_dxpIndividualsNanite.run(
			JSONUtil.put(
				"dataSourceId", getDataSourceId()
			).put(
				"type", "audit-events"
			));
	}

	@Test
	public void testDeleteIndividualFromAuditEvent() throws Exception {
		_dxpRawElasticsearchInvoker.add(
			"faro-audit-events",
			JSONUtil.putAll(
				_buildFaroAuditEventJSONObject(
					getDataSourceId(), getIndividual1Email(), "DELETE",
					getIndividual1UserId()),
				_buildFaroAuditEventJSONObject(
					getDataSourceId(), getIndividual2Email(), "DELETE",
					getIndividual2UserId())));

		_dxpIndividualsNanite.run(
			JSONUtil.put(
				"dataSourceId", getDataSourceId()
			).put(
				"type", "audit-events"
			));

		Assert.assertFalse(
			"Individuals should be removed on processing DELETE audit event",
			faroInfoElasticsearchInvoker.exists(
				"individuals",
				QueryBuilders.termsQuery(
					"demographics.email.value", getIndividual1Email(),
					getIndividual2Email())));
	}

	@Test
	public void testFieldMappingAdded() throws Exception {
		faroInfoElasticsearchInvoker.add(
			"field-mappings",
			FaroInfoTestUtil.buildIndividualFieldMappingJSONObject(
				getDataSourceId(), "subscription", "subscription", "Text"));

		_dxpIndividualsNanite.run(_addReprocessOSBAsahTaskAndReturnContext());

		assertIndividuals();

		JSONArray individualsJSONArray = faroInfoElasticsearchInvoker.get(
			"individuals");

		for (int i = 0; i < individualsJSONArray.length(); i++) {
			JSONObject individualJSONObject =
				individualsJSONArray.getJSONObject(i);

			assertDemographicsJSONObject(
				getDataSourceId(), "subscription", "Text", individualJSONObject,
				"subscription", getSubscription());
		}
	}

	@Test
	public void testFieldMappingDeleted() throws Exception {
		testFieldMappingAdded();

		faroInfoElasticsearchInvoker.delete(
			"field-mappings",
			faroInfoElasticsearchInvoker.fetch(
				"field-mappings",
				QueryBuilders.termQuery("fieldName", "subscription")));

		_dxpIndividualsNanite.run(_addReprocessOSBAsahTaskAndReturnContext());

		assertIndividuals();

		JSONArray individualsJSONArray = faroInfoElasticsearchInvoker.get(
			"individuals");

		for (int i = 0; i < individualsJSONArray.length(); i++) {
			JSONObject individualJSONObject =
				individualsJSONArray.getJSONObject(i);

			JSONObject demographicsJSONObject =
				individualJSONObject.getJSONObject("demographics");

			Assert.assertFalse(demographicsJSONObject.has("subscription"));
		}
	}

	@Test
	public void testFieldMappingModified() throws Exception {
		testFieldMappingAdded();

		JSONObject fieldMappingJSONObject = faroInfoElasticsearchInvoker.fetch(
			"field-mappings",
			QueryBuilders.termQuery("fieldName", "subscription"));

		fieldMappingJSONObject.put("fieldName", "historicSubscription");

		faroInfoElasticsearchInvoker.replace(
			"field-mappings", fieldMappingJSONObject);

		_dxpIndividualsNanite.run(_addReprocessOSBAsahTaskAndReturnContext());

		assertIndividuals();

		JSONArray individualsJSONArray = faroInfoElasticsearchInvoker.get(
			"individuals");

		for (int i = 0; i < individualsJSONArray.length(); i++) {
			JSONObject individualJSONObject =
				individualsJSONArray.getJSONObject(i);

			assertDemographicsJSONObject(
				getDataSourceId(), "historicSubscription", "Text",
				individualJSONObject, "subscription", getSubscription());

			JSONObject demographicsJSONObject =
				individualJSONObject.getJSONObject("demographics");

			Assert.assertFalse(demographicsJSONObject.has("subscription"));
		}
	}

	@Test
	public void testPopulate() {
		assertIndividuals();
	}

	@TestConfiguration
	public static class DXPIndividualsNaniteTestConfiguration {

		@Bean
		@Primary
		public DXPExtractorConfigurationDog dxpExtractorConfigurationDog() {
			return Mockito.mock(DXPExtractorConfigurationDog.class);
		}

	}

	@Override
	protected String generateIndividualPK() {
		return RandomTestUtil.randomUUID();
	}

	@Override
	protected String getEmailDataSourceFieldName() {
		return "emailAddress";
	}

	private static JSONObject _buildFaroAuditEventJSONObject(
		String dataSourceId, String email, String eventType, long userId) {

		return JSONUtil.put(
			"additionalInfo", String.valueOf(JSONUtil.put("email", email))
		).put(
			"dateCreated", DateUtil.newDateString()
		).put(
			"eventType", eventType
		).put(
			"id", RandomTestUtil.randomWeDeployId()
		).put(
			"osbAsahDataSourceId", dataSourceId
		).put(
			"userId", userId
		);
	}

	private static JSONObject _buildUserJSONObject(
		String dataSourceId, String uuid, Map<String, Object> fieldsMap,
		long userId) {

		JSONObject fieldsJSONObject = new JSONObject(fieldsMap);

		JSONObject userJSONObject = new JSONObject(fieldsJSONObject.toString());

		return userJSONObject.put(
			"contact", fieldsJSONObject
		).put(
			"modifiedDate", System.currentTimeMillis()
		).put(
			"osbAsahDataSourceId", dataSourceId
		).put(
			"userId", userId
		).put(
			"uuid", uuid
		);
	}

	private JSONObject _addReprocessOSBAsahTaskAndReturnContext() {
		JSONObject osbAsahTaskJSONObject = faroInfoElasticsearchInvoker.add(
			"OSBAsahTasks",
			JSONUtil.put(
				"className", "DXPIndividualsNanite"
			).put(
				"context",
				JSONUtil.put(
					"dataSourceId", getDataSourceId()
				).put(
					"type", "reprocess"
				)
			));

		return osbAsahTaskJSONObject.getJSONObject("context");
	}

	@MockBean
	private ChannelHttp _channelHttp;

	@Autowired
	private DXPIndividualsNanite _dxpIndividualsNanite;

	private ElasticsearchInvoker _dxpRawElasticsearchInvoker;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

}