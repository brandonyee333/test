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

import com.liferay.osb.asah.common.dog.BQSalesforceAuditEventDog;
import com.liferay.osb.asah.common.dog.BQSalesforceEntityDog;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchIndexManager;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.entity.BQSalesforceAuditEvent;
import com.liferay.osb.asah.common.entity.BQSalesforceEntity;
import com.liferay.osb.asah.common.entity.DataSource;
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
		DataSource dataSource = new DataSource("Salesforce");

		dataSource.setId(0L);
		dataSource.setIsNew(Boolean.TRUE);

		_dataSource = _dataSourceRepository.save(dataSource);

		BQSalesforceEntity accountBQSalesforceEntity = new BQSalesforceEntity(
			"1", _dataSource.getId(), JSONUtil.put("Name", "Liferay, Inc."),
			BQSalesforceEntity.Type.ACCOUNT);

		_contactBQSalesforceEntity = new BQSalesforceEntity(
			"2", _dataSource.getId(),
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
			BQSalesforceEntity.Type.CONTACT);

		_leadBQSalesforceEntity = new BQSalesforceEntity(
			"3", _dataSource.getId(),
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
			BQSalesforceEntity.Type.LEAD);

		_bqSalesforceEntityDog.saveSalesforceEntities(
			Arrays.asList(
				accountBQSalesforceEntity, _contactBQSalesforceEntity,
				_leadBQSalesforceEntity));

		_bqSalesforceAuditEventDog.addBQSalesforceAuditEvents(
			Arrays.asList(
				_buildBQSalesforceAuditEvent(
					_contactBQSalesforceEntity,
					BQSalesforceAuditEvent.Type.UPDATE),
				_buildBQSalesforceAuditEvent(
					_leadBQSalesforceEntity,
					BQSalesforceAuditEvent.Type.UPDATE)));
	}

	@AfterEach
	public void tearDown() {
		_bqSalesforceEntityDog.deleteBQSalesforceEntities(_dataSource.getId());

		_dataSourceRepository.delete(_dataSource);

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

	private BQSalesforceAuditEvent _buildBQSalesforceAuditEvent(
		BQSalesforceEntity bqSalesforceEntity,
		BQSalesforceAuditEvent.Type type) {

		BQSalesforceAuditEvent bqSalesforceAuditEvent =
			new BQSalesforceAuditEvent();

		bqSalesforceAuditEvent.setAdditionalInfoJSONObject(
			bqSalesforceEntity.getFieldsJSONObject());
		bqSalesforceAuditEvent.setCreateDate(new Date());
		bqSalesforceAuditEvent.setDataSourceId(
			bqSalesforceEntity.getDataSourceId());

		BQSalesforceEntity.Type bqSalesforceEntityType =
			bqSalesforceEntity.getType();

		bqSalesforceAuditEvent.setEntityTypeName(
			bqSalesforceEntityType.getValue());

		bqSalesforceAuditEvent.setRecordId(bqSalesforceEntity.getId());
		bqSalesforceAuditEvent.setType(type);

		return bqSalesforceAuditEvent;
	}

	private void _delete(BQSalesforceEntity bqSalesforceEntity) {
		_bqSalesforceEntityDog.deleteBQSalesforceEntity(bqSalesforceEntity);

		_bqSalesforceAuditEventDog.addBQSalesforceAuditEvent(
			_buildBQSalesforceAuditEvent(
				bqSalesforceEntity, BQSalesforceAuditEvent.Type.DELETE));
	}

	private void _testDeleteContact() {
		_delete(_contactBQSalesforceEntity);

		_salesforceExtractorIndividualsNanite.run();

		List<BQSalesforceEntity> bqSalesforceEntities =
			_bqSalesforceEntityDog.getBQSalesforceEntities(
				_dataSource.getId(), 0, 100,
				BQSalesforceEntity.Type.INDIVIDUAL);

		Assertions.assertEquals(1, bqSalesforceEntities.size());

		BQSalesforceEntity bqSalesforceEntity = bqSalesforceEntities.get(0);

		JSONObject jsonObject = bqSalesforceEntity.getFieldsJSONObject();

		Assertions.assertNull(jsonObject.optJSONArray("accountPKs"));
		Assertions.assertNull(jsonObject.optString("contactId", null));
	}

	private void _testDeleteLead() {
		_delete(_leadBQSalesforceEntity);

		_salesforceExtractorIndividualsNanite.run();

		Assertions.assertEquals(
			0,
			_bqSalesforceEntityDog.getBQSalesforceEntitiesCount(
				1L, BQSalesforceEntity.Type.INDIVIDUAL));
	}

	private void _testMergeIndividual() {
		_salesforceExtractorIndividualsNanite.run();

		List<BQSalesforceEntity> bqSalesforceEntities =
			_bqSalesforceEntityDog.getBQSalesforceEntities(
				_dataSource.getId(), 0, 100,
				BQSalesforceEntity.Type.INDIVIDUAL);

		Assertions.assertEquals(
			1,
			_bqSalesforceEntityDog.getBQSalesforceEntitiesCount(
				_dataSource.getId(), BQSalesforceEntity.Type.INDIVIDUAL));

		BQSalesforceEntity bqSalesforceEntity = bqSalesforceEntities.get(0);

		JSONObject fieldsJSONObject = bqSalesforceEntity.getFieldsJSONObject();

		JSONArray accountPKsJSONArray = fieldsJSONObject.getJSONArray(
			"accountPKs");

		Assertions.assertEquals("1", accountPKsJSONArray.get(0));

		Assertions.assertEquals("2", fieldsJSONObject.getString("contactId"));
		Assertions.assertNotNull(fieldsJSONObject.optString("dataSourceId"));
		Assertions.assertEquals(
			"true", fieldsJSONObject.getString("doNotCall"));
		Assertions.assertEquals("3", fieldsJSONObject.getString("leadId"));
		Assertions.assertNotNull(fieldsJSONObject.optString("modifiedDate"));
	}

	@Autowired
	private BQSalesforceAuditEventDog _bqSalesforceAuditEventDog;

	@Autowired
	private BQSalesforceEntityDog _bqSalesforceEntityDog;

	private BQSalesforceEntity _contactBQSalesforceEntity;
	private DataSource _dataSource;

	@Autowired
	private DataSourceRepository _dataSourceRepository;

	@Autowired
	private ElasticsearchIndexManager _elasticsearchIndexManager;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_SALESFORCE_RAW)
	private ElasticsearchInvoker _elasticsearchInvoker;

	private BQSalesforceEntity _leadBQSalesforceEntity;

	@Autowired
	private SalesforceExtractorIndividualsNanite
		_salesforceExtractorIndividualsNanite;

}