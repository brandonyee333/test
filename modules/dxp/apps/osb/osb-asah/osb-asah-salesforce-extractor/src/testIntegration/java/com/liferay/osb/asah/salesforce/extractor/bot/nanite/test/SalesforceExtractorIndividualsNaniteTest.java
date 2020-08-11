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

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchIndexManager;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.salesforce.extractor.bot.nanite.SalesforceExtractorIndividualsNanite;
import com.liferay.osb.asah.salesforce.extractor.configuration.impl.SalesforceExtractorFileConfigurationImpl;
import com.liferay.osb.asah.salesforce.extractor.spring.OSBAsahSalesforceExtractorSpringBootApplication;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import org.apache.commons.lang.RandomStringUtils;

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

		_elasticsearchInvoker = _elasticsearchInvokerFactory.forSalesforceRaw();

		JSONObject accountJSONObject = JSONUtil.put(
			"id", "1"
		).put(
			"Name", "Liferay, Inc."
		).put(
			"osbAsahDataSourceId", "0"
		);

		_elasticsearchInvoker.add(
			"Account",
			new JSONArray() {
				{
					put(accountJSONObject);
				}
			});

		JSONObject contactJSONObject = JSONUtil.put(
			"AccountId", "1"
		).put(
			"Birthdate", DateUtil.newDateString()
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
			"id", "2"
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
			"osbAsahDataSourceId", "0"
		).put(
			"Phone", "(000) 000-0000"
		).put(
			"Title", "Administrator"
		);

		_elasticsearchInvoker.add(
			"Contact",
			new JSONArray() {
				{
					put(contactJSONObject);
				}
			});

		JSONObject leadJSONObject = JSONUtil.put(
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
			"osbAsahDataSourceId", "0"
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
		);

		_elasticsearchInvoker.add(
			"Lead",
			new JSONArray() {
				{
					put(leadJSONObject);
				}
			});

		_elasticsearchInvoker.add(
			"audit-events",
			new JSONArray() {
				{
					put(
						_buildAuditEventJSONObject(
							"UPDATE", contactJSONObject,
							contactJSONObject.getString("id"), "Contact"));
					put(
						_buildAuditEventJSONObject(
							"UPDATE", leadJSONObject,
							leadJSONObject.getString("id"), "Lead"));
				}
			});
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
				new SalesforceExtractorFileConfigurationImpl());
		}

	}

	private JSONObject _buildAuditEventJSONObject(
		String eventType, JSONObject jsonObject, String recordId,
		String typeName) {

		return JSONUtil.put(
			"additionalInfo", jsonObject
		).put(
			"eventType", eventType
		).put(
			"id", RandomStringUtils.randomNumeric(8)
		).put(
			"osbAsahDataSourceId", "0"
		).put(
			"recordId", recordId
		).put(
			"typeName", typeName
		);
	}

	private void _delete(String typeName) {
		JSONArray jsonArray = _elasticsearchInvoker.get(typeName);

		JSONObject jsonObject = jsonArray.getJSONObject(0);

		_elasticsearchInvoker.add(
			"audit-events",
			new JSONArray() {
				{
					put(
						_buildAuditEventJSONObject(
							"DELETE", jsonObject, jsonObject.getString("id"),
							typeName));
				}
			});

		_elasticsearchInvoker.delete(typeName, QueryBuilders.matchAllQuery());
	}

	private void _testDeleteContact() throws Exception {
		_delete("Contact");

		_salesforceExtractorIndividualsNanite.run();

		JSONArray individualsJSONArray = _elasticsearchInvoker.get(
			"individuals");

		Assert.assertEquals(1, individualsJSONArray.length());

		JSONObject individualJSONObject = individualsJSONArray.getJSONObject(0);

		Assert.assertNull(individualJSONObject.optJSONArray("accountPKs"));
		Assert.assertNull(individualJSONObject.optString("contactId", null));
	}

	private void _testDeleteLead() throws Exception {
		_delete("Lead");

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

		JSONArray accountPKsJSONArray = individualJSONObject.getJSONArray(
			"accountPKs");

		Assert.assertEquals("1", accountPKsJSONArray.get(0));

		Assert.assertEquals("2", individualJSONObject.getString("contactId"));
		Assert.assertEquals(
			"true", individualJSONObject.getString("doNotCall"));
		Assert.assertEquals("3", individualJSONObject.getString("leadId"));
		Assert.assertNotNull(individualJSONObject.optString("modifiedDate"));
		Assert.assertNotNull(
			individualJSONObject.optString("osbAsahDataSourceId"));
	}

	@Autowired
	private ElasticsearchIndexManager _elasticsearchIndexManager;

	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

	@Autowired
	private SalesforceExtractorIndividualsNanite
		_salesforceExtractorIndividualsNanite;

}