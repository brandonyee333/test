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
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.entity.SalesforceAuditEvent;
import com.liferay.osb.asah.common.entity.SalesforceEntity;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.salesforce.extractor.bot.nanite.SalesforceExtractorIndividualsNanite;
import com.liferay.osb.asah.salesforce.extractor.bot.nanite.test.util.SalesforceExtractorTestUtil;
import com.liferay.osb.asah.salesforce.extractor.spring.OSBAsahSalesforceExtractorSpringBootApplication;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringExtension;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * @author Pedro Queiroz
 * @author Rachael Koestartyo
 */
@ExtendWith(OSBAsahSpringExtension.class)
@SpringBootTest(
	classes = {
		OSBAsahSalesforceExtractorSpringBootApplication.class,
		SalesforceExtractorNaniteTest.
			SalesforceExtractorNaniteTestConfiguration.class
	}
)
public class SalesforceExtractorIndividualsNaniteTest
	implements OSBAsahTestExecutionListenersContext {

	@BeforeEach
	public void setUp() {
		_elasticsearchIndexManager.clearIndices();

		_elasticsearchIndexManager.checkIndices();
		_salesforceEntityDog.deleteSalesforceEntities(0L);
		_dataSourceRepository.deleteAll();

		DataSource dataSource = new DataSource("Salesforce");

		dataSource.setId(0L);
		dataSource.setIsNew(Boolean.TRUE);

		_dataSourceRepository.save(dataSource);

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

	@AfterEach
	public void tearDown() {
		_salesforceEntityDog.deleteSalesforceEntities(0L);
		_dataSourceRepository.deleteAll();
		_elasticsearchInvoker.delete(
			"audit-events", QueryBuilders.matchAllQuery());
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

		SalesforceEntity.Type salesforceEntityType = salesforceEntity.getType();

		salesforceAuditEvent.setEntityTypeName(salesforceEntityType.getValue());

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

	private void _testDeleteContact() {
		_delete(_contactSalesforceEntity);

		_salesforceExtractorIndividualsNanite.run();

		List<SalesforceEntity> salesforceEntities =
			_salesforceEntityDog.getSalesforceEntities(
				0L, 0, 100, SalesforceEntity.Type.INDIVIDUAL);

		Assertions.assertEquals(1, salesforceEntities.size());

		SalesforceEntity salesforceEntity = salesforceEntities.get(0);

		JSONObject jsonObject = salesforceEntity.getFieldsJSONObject();

		Assertions.assertNull(jsonObject.optJSONArray("accountPKs"));
		Assertions.assertNull(jsonObject.optString("contactId", null));
	}

	private void _testDeleteLead() {
		_delete(_leadSalesforceEntity);

		_salesforceExtractorIndividualsNanite.run();

		Assertions.assertEquals(
			0,
			_salesforceEntityDog.getSalesforceEntitiesCount(
				1L, SalesforceEntity.Type.INDIVIDUAL));
	}

	private void _testMergeIndividual() {
		_salesforceExtractorIndividualsNanite.run();

		List<SalesforceEntity> salesforceEntities =
			_salesforceEntityDog.getSalesforceEntities(
				0L, 0, 100, SalesforceEntity.Type.INDIVIDUAL);

		Assertions.assertEquals(
			1,
			_salesforceEntityDog.getSalesforceEntitiesCount(
				0L, SalesforceEntity.Type.INDIVIDUAL));

		SalesforceEntity salesforceEntity = salesforceEntities.get(0);

		JSONObject individualFieldsJSONObject =
			salesforceEntity.getFieldsJSONObject();

		JSONArray accountPKsJSONArray = individualFieldsJSONObject.getJSONArray(
			"accountPKs");

		Assertions.assertEquals("1", accountPKsJSONArray.get(0));

		Assertions.assertEquals(
			"2", individualFieldsJSONObject.getString("contactId"));
		Assertions.assertNotNull(
			individualFieldsJSONObject.optString("dataSourceId"));
		Assertions.assertEquals(
			"true", individualFieldsJSONObject.getString("doNotCall"));
		Assertions.assertEquals(
			"3", individualFieldsJSONObject.getString("leadId"));
		Assertions.assertNotNull(
			individualFieldsJSONObject.optString("modifiedDate"));
	}

	private SalesforceEntity _contactSalesforceEntity;

	@Autowired
	private DataSourceRepository _dataSourceRepository;

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