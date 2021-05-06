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

package com.liferay.osb.asah.salesforce.extractor.bot.nanite.test;

import com.liferay.osb.asah.common.dog.SalesforceAuditEventDog;
import com.liferay.osb.asah.common.dog.SalesforceEntityDog;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchIndexManager;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.entity.SalesforceAuditEvent;
import com.liferay.osb.asah.common.entity.SalesforceEntity;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.salesforce.extractor.bot.nanite.SalesforceExtractorIndividualsNanite;
import com.liferay.osb.asah.salesforce.extractor.bot.nanite.test.util.SalesforceExtractorTestUtil;
import com.liferay.osb.asah.salesforce.extractor.spring.OSBAsahSalesforceExtractorSpringBootApplication;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.Date;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * @author Pedro Queiroz
 * @author Rachael Koestartyo
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(
	classes = {
		OSBAsahSalesforceExtractorSpringBootApplication.class,
		SalesforceExtractorIndividualsNaniteTest.
			SalesforceExtractorIndividualsNaniteTestConfiguration.class
	}
)
public class SalesforceExtractorIndividualsNaniteTest {

	@Before
	public void setUp() {
		_elasticsearchIndexManager.clearIndices();

		_elasticsearchIndexManager.checkIndices();

		SalesforceEntity accountSalesforceEntity = new SalesforceEntity(
			"1", 0L, JSONUtil.put("Name", "Liferay, Inc."),
			SalesforceEntity.Type.ACCOUNT);

		_contactSalesforceEntity = new SalesforceEntity(
			"2", 0L,
			JSONUtil.put(
				"AccountId", "1"
			).put(
				"Birthdate", "-1176968214861"
			).put(
				"Department", "IT"
			).put(
				"Description", "Test"
			).put(
				"DoNotCall", "true"
			).put(
				"Email", "test@liferay.com"
			).put(
				"FirstName", "Test"
			).put(
				"Industry", "Agriculture"
			).put(
				"LastName", "Test"
			).put(
				"MailingCity", "Diamond Bar"
			).put(
				"MailingCountry", "United States"
			).put(
				"MailingPostalCode", "91765"
			).put(
				"MailingState", "California"
			).put(
				"MailingStreet", "1400 Montefino Ave."
			).put(
				"Name", "Test Test"
			).put(
				"Phone", "(000) 000-0000"
			).put(
				"Title", "Administrator"
			),
			SalesforceEntity.Type.CONTACT);

		_leadSalesforceEntity = new SalesforceEntity(
			"3", 0L,
			JSONUtil.put(
				"City", "Diamond Bar"
			).put(
				"Company", "Liferay, Inc."
			).put(
				"Country", "United States"
			).put(
				"Description", "Test"
			).put(
				"Email", "test@liferay.com"
			).put(
				"FirstName", "Test"
			).put(
				"id", "3"
			).put(
				"Industry", "Agriculture"
			).put(
				"LastName", "Test"
			).put(
				"Name", "Test Test"
			).put(
				"Phone", "(000) 000-0000"
			).put(
				"PostalCode", "91765"
			).put(
				"State", "California"
			).put(
				"Street", "1400 Montefino Ave."
			).put(
				"Title", "Administrator"
			),
			SalesforceEntity.Type.LEAD);

		_salesforceEntityDog.saveSalesforceEntities(
			Arrays.asList(
				accountSalesforceEntity, _contactSalesforceEntity,
				_leadSalesforceEntity));

		_salesforceAuditEventDog.addSalesforceAuditEvents(
			Arrays.asList(
				_buildSalesforceAuditEvent(
					_contactSalesforceEntity, SalesforceAuditEvent.Type.UPDATE),
				_buildSalesforceAuditEvent(
					_leadSalesforceEntity, SalesforceAuditEvent.Type.UPDATE)));
	}

	@After
	public void tearDown() {
		_elasticsearchInvoker.delete("Account", QueryBuilders.matchAllQuery());
		_elasticsearchInvoker.delete("Contact", QueryBuilders.matchAllQuery());
		_elasticsearchInvoker.delete("Lead", QueryBuilders.matchAllQuery());
		_elasticsearchInvoker.delete(
			"audit-events", QueryBuilders.matchAllQuery());
		_elasticsearchInvoker.delete(
			"individuals", QueryBuilders.matchAllQuery());

		_elasticsearchIndexManager.clearIndices();
	}

	@Test
	public void testIndividuals() throws Exception {
		_testMergeIndividual();

		_testDeleteContact();
		_testDeleteLead();
	}

	@TestConfiguration
	public static class SalesforceExtractorIndividualsNaniteTestConfiguration {

		@Bean
		public SalesforceExtractorIndividualsNanite
			salesforceExtractorIndividualsNanite() {

			return new SalesforceExtractorIndividualsNanite(
				SalesforceExtractorTestUtil.
					getSalesforceExtractorConfiguration());
		}

	}

	private SalesforceAuditEvent _buildSalesforceAuditEvent(
		SalesforceEntity salesforceEntity, SalesforceAuditEvent.Type type) {

		SalesforceAuditEvent salesforceAuditEvent = new SalesforceAuditEvent();

		salesforceAuditEvent.setAdditionalInfoJSONObject(
			salesforceEntity.getFieldsJSONObject());
		salesforceAuditEvent.setCreateDate(new Date());
		salesforceAuditEvent.setDataSourceId(
			salesforceEntity.getDataSourceId());
		salesforceAuditEvent.setEntityTypeName(
			String.valueOf(salesforceEntity.getType()));
		salesforceAuditEvent.setRecordId(salesforceEntity.getId());
		salesforceAuditEvent.setType(type);

		return salesforceAuditEvent;
	}

	private void _delete(SalesforceEntity salesforceEntity) {
		_salesforceEntityDog.deleteSalesforceEntity(salesforceEntity);

		_salesforceAuditEventDog.addSalesforceAuditEvent(
			_buildSalesforceAuditEvent(
				salesforceEntity, SalesforceAuditEvent.Type.DELETE));
	}

	private void _testDeleteContact() throws Exception {
		_delete(_contactSalesforceEntity);

		_salesforceExtractorIndividualsNanite.run();

		JSONArray individualsJSONArray = _elasticsearchInvoker.get(
			"individuals");

		Assert.assertEquals(1, individualsJSONArray.length());

		JSONObject individualJSONObject = individualsJSONArray.getJSONObject(0);

		Assert.assertNull(individualJSONObject.optJSONArray("accountPKs"));
		Assert.assertNull(individualJSONObject.optString("contactId", null));
	}

	private void _testDeleteLead() throws Exception {
		_delete(_leadSalesforceEntity);

		_salesforceExtractorIndividualsNanite.run();

		JSONArray individualsJSONArray = _elasticsearchInvoker.get(
			"individuals");

		Assert.assertEquals(0, individualsJSONArray.length());
	}

	private void _testMergeIndividual() throws Exception {
		_salesforceExtractorIndividualsNanite.run();

		JSONArray individualsJSONArray = _elasticsearchInvoker.get(
			"individuals");

		Assert.assertEquals(1, individualsJSONArray.length());

		JSONObject individualJSONObject = individualsJSONArray.getJSONObject(0);

		JSONObject individualFieldsJSONObject =
			individualJSONObject.getJSONObject("fields");

		JSONArray accountPKsJSONArray = individualFieldsJSONObject.getJSONArray(
			"accountPKs");

		Assert.assertEquals("1", accountPKsJSONArray.get(0));

		Assert.assertEquals(
			"2", individualFieldsJSONObject.getString("contactId"));
		Assert.assertEquals(
			"true", individualFieldsJSONObject.getString("doNotCall"));
		Assert.assertEquals(
			"3", individualFieldsJSONObject.getString("leadId"));
		Assert.assertNotNull(
			individualFieldsJSONObject.optString("modifiedDate"));
		Assert.assertNotNull(
			individualFieldsJSONObject.optString("osbAsahDataSourceId"));
	}

	private SalesforceEntity _contactSalesforceEntity;

	@Autowired
	private ElasticsearchIndexManager _elasticsearchIndexManager;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_SALESFORCE_RAW)
	private ElasticsearchInvoker _elasticsearchInvoker;

	private SalesforceEntity _leadSalesforceEntity;

	@Autowired
	private SalesforceAuditEventDog _salesforceAuditEventDog;

	@Autowired
	private SalesforceEntityDog _salesforceEntityDog;

	@Autowired
	private SalesforceExtractorIndividualsNanite
		_salesforceExtractorIndividualsNanite;

}