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

package com.liferay.osb.asah.common.http.test;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.DXPEntityDog;
import com.liferay.osb.asah.common.dog.DataSourceDog;
import com.liferay.osb.asah.common.dog.FieldMappingDog;
import com.liferay.osb.asah.common.dog.IndividualDog;
import com.liferay.osb.asah.common.dog.SegmentDog;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.entity.ActivityGroup;
import com.liferay.osb.asah.common.entity.DXPEntity;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.entity.Field;
import com.liferay.osb.asah.common.entity.FieldMapping;
import com.liferay.osb.asah.common.entity.Individual;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoActivityDog;
import com.liferay.osb.asah.common.faro.info.dog.test.BaseFaroInfoDogTestCase;
import com.liferay.osb.asah.common.http.ChannelHttp;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.repository.ActivityGroupRepository;
import com.liferay.osb.asah.common.repository.FieldMappingRepository;
import com.liferay.osb.asah.common.repository.FieldRepository;
import com.liferay.osb.asah.common.salesforce.extractor.dog.SalesforceExtractorConfigurationDog;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

import org.apache.lucene.search.join.ScoreMode;

import org.elasticsearch.index.query.QueryBuilders;

import org.hamcrest.CoreMatchers;

import org.json.JSONObject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Leslie Wong
 */
@ContextConfiguration(classes = OSBAsahSpringBootApplication.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class DataSourceHttpTest extends BaseFaroInfoDogTestCase {

	@Before
	public void setUp() {
		_mock();
	}

	@Test
	public void testAddDataSource() {
		_dataSourceDog.addDataSource(FaroInfoTestUtil.buildLiferayDataSource());
	}

	@Test
	public void testAddDataSourceUpdatesExistingIndividual() throws Exception {
		DataSource dataSource1 = _dataSourceDog.addDataSource(
			FaroInfoTestUtil.buildLiferayDataSource());
		DataSource dataSource2 = _dataSourceDog.addDataSource(
			FaroInfoTestUtil.buildLiferayDataSource());

		Long dataSourceId1 = dataSource1.getId();
		Long dataSourceId2 = dataSource2.getId();

		_fieldMappingRepository.save(
			FaroInfoTestUtil.buildIndividualFieldMapping(
				new HashMap<String, String>() {
					{
						put(String.valueOf(dataSourceId1), "email");
						put(String.valueOf(dataSourceId2), "emailAddress");
					}
				},
				"email", "http://schema.org/email"));

		_fieldMappingRepository.save(
			FaroInfoTestUtil.buildIndividualFieldMapping(
				dataSourceId2, "givenName", "givenName", "Text"));

		Individual individual = _individualDog.addIndividual(
			FaroInfoTestUtil.buildIndividual(dataSource1), false);

		_fieldRepository.save(
			FaroInfoTestUtil.buildIndividualField(
				dataSource1, "email", "http://schema.org/email",
				RandomTestUtil.randomString(), individual, "email"));

		individual = _individualDog.updateIndividual(
			RandomTestUtil.randomUUID(),
			JSONUtil.put(
				"contact",
				JSONUtil.put(
					"emailAddress", RandomTestUtil.randomEmailAddress()
				).put(
					"givenName", RandomTestUtil.randomString()
				)
			).put(
				"modifiedDate", System.currentTimeMillis()
			),
			dataSource2, individual);

		individual = _individualDog.fetchIndividual(individual.getId());

		Set<Field> fields = individual.getFields();

		Stream<Field> stream = fields.stream();

		Assert.assertTrue(
			"Adding data source with information on existing individual " +
				"should update fields of existing individual",
			stream.anyMatch(
				field -> Objects.equals(field.getName(), "givenName")));

		Assert.assertTrue(
			"Updating individual from new data source should add an " +
				"enrichment date",
			!Objects.isNull(individual.getLastEnrichmentDate()));
	}

	@Test
	public void testAddDataSourceWithDuplicateName() {
		for (int i = 0; i < 4; i++) {
			_dataSourceDog.addDataSource(
				FaroInfoTestUtil.buildLiferayDataSource(
					"Liferay", RandomTestUtil.randomURL()));
		}

		Assert.assertEquals(
			4L,
			faroInfoElasticsearchInvoker.count(
				"data-sources", QueryBuilders.matchAllQuery()));
		Assert.assertTrue(
			faroInfoElasticsearchInvoker.exists(
				"data-sources", QueryBuilders.termQuery("name", "Liferay")));
		Assert.assertTrue(
			faroInfoElasticsearchInvoker.exists(
				"data-sources",
				QueryBuilders.termQuery("name", "Liferay (1)")));
		Assert.assertTrue(
			faroInfoElasticsearchInvoker.exists(
				"data-sources",
				QueryBuilders.termQuery("name", "Liferay (2)")));
		Assert.assertTrue(
			faroInfoElasticsearchInvoker.exists(
				"data-sources",
				QueryBuilders.termQuery("name", "Liferay (3)")));
	}

	@Test
	public void testDeleteCSVDataSource() throws Exception {
		JSONObject dataSourceJSONObject1 = _objectMapper.convertValue(
			_dataSourceDog.addDataSource(
				_objectMapper.convertValue(
					FaroInfoTestUtil.buildCSVDataSourceJSONObject(),
					DataSource.class)),
			JSONObject.class);
		JSONObject dataSourceJSONObject2 = _objectMapper.convertValue(
			_dataSourceDog.addDataSource(
				_objectMapper.convertValue(
					FaroInfoTestUtil.buildCSVDataSourceJSONObject(),
					DataSource.class)),
			JSONObject.class);

		String dataSourceId1 = dataSourceJSONObject1.getString("id");
		String dataSourceId2 = dataSourceJSONObject2.getString("id");

		faroInfoElasticsearchInvoker.add(
			"csv-individuals",
			JSONUtil.putAll(
				FaroInfoTestUtil.buildCSVIndividualJSONObject(
					dataSourceId1, RandomTestUtil.randomUUID(),
					new HashMap<>()),
				FaroInfoTestUtil.buildCSVIndividualJSONObject(
					dataSourceId2, RandomTestUtil.randomUUID(),
					new HashMap<>())));

		dataSourceJSONObject1.put("deletionDate", DateUtil.newDayDateString());

		_dataSourceDog.deleteDataSource(
			_objectMapper.convertValue(
				dataSourceJSONObject1, DataSource.class));

		Assert.assertFalse(
			"Found entry in csv-individuals collection with data source ID " +
				dataSourceId1,
			faroInfoElasticsearchInvoker.exists(
				"csv-individuals",
				QueryBuilders.termQuery(
					"dataSourceId", Long.valueOf(dataSourceId1))));
		Assert.assertTrue(
			"Unable to find entry in csv-individuals collection with data " +
				"source ID " + dataSourceId2,
			faroInfoElasticsearchInvoker.exists(
				"csv-individuals",
				QueryBuilders.termQuery(
					"dataSourceId", Long.valueOf(dataSourceId2))));
	}

	@Test
	public void testDeleteDataSourceDeletesEmptyFieldMappings()
		throws Exception {

		DataSource dataSource = _dataSourceDog.addDataSource(
			FaroInfoTestUtil.buildLiferayDataSource());

		FieldMapping fieldMapping = _fieldMappingRepository.save(
			FaroInfoTestUtil.buildIndividualFieldMapping(
				dataSource.getId(), "givenName", "givenName", "Text"));

		dataSource.setDeletionDate(new Date());

		_dataSourceDog.deleteDataSource(dataSource);

		Assert.assertFalse(
			"Field mapping should have been deleted on data source deletion",
			_fieldMappingDog.existsById(fieldMapping.getId()));
	}

	@Test
	public void testDeleteDataSourceDeletesIndividual() throws Exception {
		DataSource dataSource = _dataSourceDog.addDataSource(
			FaroInfoTestUtil.buildLiferayDataSource());

		Individual individual = _individualDog.addIndividual(
			FaroInfoTestUtil.buildIndividual(dataSource), false);

		dataSource.setDeletionDate(new Date());

		_dataSourceDog.deleteDataSource(dataSource);

		Long individualId = individual.getId();

		if (individualId == null) {
			individualId = 0L;
		}

		Assert.assertFalse(
			"Individual was not deleted on data source deletion despite only " +
				"containing fields from the deleted data source",
			_individualDog.existsById(individualId));
	}

	@Test
	public void testDeleteDataSourceDeletesReferenceInFieldMapping()
		throws Exception {

		DataSource dataSource1 = _dataSourceDog.addDataSource(
			FaroInfoTestUtil.buildLiferayDataSource());
		DataSource dataSource2 = _dataSourceDog.addDataSource(
			FaroInfoTestUtil.buildLiferayDataSource());

		Long dataSourceId1 = dataSource1.getId();
		Long dataSourceId2 = dataSource2.getId();

		_fieldMappingRepository.save(
			FaroInfoTestUtil.buildIndividualFieldMapping(
				new HashMap<String, String>() {
					{
						put(String.valueOf(dataSourceId1), "givenName");
						put(String.valueOf(dataSourceId2), "givenName");
					}
				},
				"givenName", "Text"));

		dataSource1.setDeletionDate(new Date());

		_dataSourceDog.deleteDataSource(dataSource1);

		Assert.assertFalse(
			"Field mapping reference to deleted data source was not removed",
			faroInfoElasticsearchInvoker.exists(
				"field-mappings",
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery("fieldName", "givenName")
				).filter(
					QueryBuilders.existsQuery(
						"dataSourceFieldNames." + dataSourceId1)
				)));
		Assert.assertTrue(
			"Field mapping reference to existing data source was removed on " +
				"deletion of another data source",
			faroInfoElasticsearchInvoker.exists(
				"field-mappings",
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery("fieldName", "givenName")
				).filter(
					QueryBuilders.existsQuery(
						"dataSourceFieldNames." + dataSourceId2)
				)));
	}

	@Test
	public void testDeleteDataSourceDisablesIndividualDynamicSegment1()
		throws Exception {

		DataSource dataSource = _dataSourceDog.addDataSource(
			FaroInfoTestUtil.buildLiferayDataSource());

		_fieldMappingRepository.save(
			FaroInfoTestUtil.buildIndividualFieldMapping(
				dataSource.getId(), "givenName", "givenName", "Text"));

		Segment segment = _segmentDog.addSegment(
			FaroInfoTestUtil.buildDynamicSegment(
				"(((demographics/givenName/value ne null)))"));

		dataSource.setDeletionDate(new Date());

		_dataSourceDog.deleteDataSource(dataSource);

		segment = _segmentDog.getSegment(segment.getId());

		Assert.assertEquals(
			"Individual dynamic segment with properties was not disabled " +
				"when data source was deleted",
			"DISABLED", segment.getState());
	}

	@Test
	public void testDeleteDataSourceDisablesIndividualDynamicSegment2()
		throws Exception {

		DataSource dataSource = _dataSourceDog.addDataSource(
			FaroInfoTestUtil.buildLiferayDataSource());

		JSONObject assetJSONObject = faroInfoElasticsearchInvoker.add(
			"assets",
			FaroInfoTestUtil.buildPageAssetJSONObject(dataSource.getId()));

		Segment segment = _segmentDog.addSegment(
			FaroInfoTestUtil.buildDynamicSegment(
				"activities/ever eq 'page#pageViewed#" +
					assetJSONObject.getString("id") + "'"));

		dataSource.setDeletionDate(new Date());

		_dataSourceDog.deleteDataSource(dataSource);

		segment = _segmentDog.getSegment(segment.getId());

		Assert.assertEquals(
			"Individual dynamic segment with assets was not disabled when " +
				"data source was deleted",
			"DISABLED", segment.getState());
	}

	@Test
	public void testDeleteDataSourceDoesNotDeleteDefaultFieldMappings()
		throws Exception {

		DataSource dataSource = _dataSourceDog.addDataSource(
			FaroInfoTestUtil.buildLiferayDataSource());

		FieldMapping fieldMapping = _fieldMappingRepository.save(
			FaroInfoTestUtil.buildIndividualFieldMapping(
				new FieldMapping.Author("FARO_SYSTEM", "FARO_SYSTEM"),
				dataSource.getId(), "givenName", "givenName", "Text"));

		dataSource.setDeletionDate(new Date());

		_dataSourceDog.deleteDataSource(dataSource);

		Assert.assertTrue(
			"Field mappings created by the default user should not be " +
				"deleted on data source deletion",
			_fieldMappingDog.existsById(fieldMapping.getId()));
	}

	@Test
	public void testDeleteDataSourceUpdatesIndividualFields() throws Exception {
		DataSource dataSource1 = _dataSourceDog.addDataSource(
			FaroInfoTestUtil.buildLiferayDataSource());
		DataSource dataSource2 = _dataSourceDog.addDataSource(
			FaroInfoTestUtil.buildLiferayDataSource());

		Long dataSourceId1 = dataSource1.getId();
		Long dataSourceId2 = dataSource2.getId();

		_fieldMappingRepository.save(
			FaroInfoTestUtil.buildIndividualFieldMapping(
				new HashMap<String, String>() {
					{
						put(String.valueOf(dataSourceId1), "email");
						put(String.valueOf(dataSourceId2), "emailAddress");
					}
				},
				"email", "http://schema.org/email"));

		_fieldMappingRepository.save(
			FaroInfoTestUtil.buildIndividualFieldMapping(
				dataSourceId2, "givenName", "givenName", "Text"));

		Individual individual = _individualDog.addIndividual(
			FaroInfoTestUtil.buildIndividual(dataSource1), false);

		_fieldRepository.save(
			FaroInfoTestUtil.buildIndividualField(
				dataSource1, "email", "http://schema.org/email",
				RandomTestUtil.randomString(), individual, "email"));

		individual = _individualDog.updateIndividual(
			RandomTestUtil.randomUUID(),
			JSONUtil.put(
				"contact",
				JSONUtil.put(
					"emailAddress", RandomTestUtil.randomEmailAddress()
				).put(
					"givenName", RandomTestUtil.randomString()
				)
			).put(
				"modifiedDate", System.currentTimeMillis()
			),
			dataSource2, individual);

		Date lastEnrichmentDate = individual.getLastEnrichmentDate();

		dataSource2.setDeletionDate(new Date());

		_dataSourceDog.deleteDataSource(dataSource2);

		Long individualId = individual.getId();

		if (individualId == null) {
			individualId = 0L;
		}

		Assert.assertTrue(
			"Individual was deleted even though another data source with " +
				"data on the individual exists",
			_individualDog.existsById(individualId));

		individual = _individualDog.fetchIndividual(individualId);

		Assert.assertFalse(
			"Data source individual PK was not deleted on data source deletion",
			_individualDog.existsByDataSourceIndividualPK(
				dataSourceId2, individualId));

		Set<Field> fields = individual.getFields();

		Stream<Field> stream = fields.stream();

		Assert.assertFalse(
			"Individual field 'givenName' was not deleted",
			stream.anyMatch(
				field -> Objects.equals(field.getName(), "givenName")));

		stream = fields.stream();

		Field emailField = stream.filter(
			field -> Objects.equals(field.getName(), "email")
		).findFirst(
		).orElse(
			null
		);

		Assert.assertNotNull(emailField);

		Assert.assertEquals(dataSourceId1, emailField.getDataSourceId());

		Assert.assertEquals(
			"Data source deletion should not count towards individual " +
				"enrichment",
			lastEnrichmentDate, individual.getLastEnrichmentDate());
	}

	@Test
	public void testDeleteLiferayDataSource() throws Exception {
		DataSource dataSource1 = _dataSourceDog.addDataSource(
			FaroInfoTestUtil.buildLiferayDataSource());

		_addActivityAndUserToLiferayDataSource(dataSource1);

		DataSource dataSource2 = _dataSourceDog.addDataSource(
			FaroInfoTestUtil.buildLiferayDataSource());

		_addActivityAndUserToLiferayDataSource(dataSource2);

		dataSource1.setDeletionDate(
			DateUtil.toUTCDate(
				DateUtil.addDays(DateUtil.newDayDateString(), -1)));

		_dataSourceDog.deleteDataSource(dataSource1);

		Long dataSourceId1 = dataSource1.getId();
		Long dataSourceId2 = dataSource2.getId();

		for (String index :
				new String[] {"activities", "activity-groups", "assets"}) {

			Assert.assertTrue(
				"Unable to find entry in " + index + " collection with data " +
					"source ID " + dataSourceId1,
				faroInfoElasticsearchInvoker.exists(
					index,
					QueryBuilders.termQuery("dataSourceId", dataSourceId1)));
			Assert.assertTrue(
				"Unable to find entry in " + index + " collection with data " +
					"source ID " + dataSourceId2,
				faroInfoElasticsearchInvoker.exists(
					index,
					QueryBuilders.termQuery("dataSourceId", dataSourceId2)));
		}

		Assert.assertFalse(
			"Individuals from deleted data source still exist",
			faroInfoElasticsearchInvoker.exists(
				"individuals",
				QueryBuilders.nestedQuery(
					"dataSourceIndividualPKs",
					QueryBuilders.termQuery(
						"dataSourceIndividualPKs.dataSourceId", dataSourceId1),
					ScoreMode.None)));
		Assert.assertTrue(
			"Individuals from data source " + dataSourceId2 + " were deleted " +
				"when deleting data source " + dataSourceId1,
			faroInfoElasticsearchInvoker.exists(
				"individuals",
				QueryBuilders.nestedQuery(
					"dataSourceIndividualPKs",
					QueryBuilders.termQuery(
						"dataSourceIndividualPKs.dataSourceId", dataSourceId2),
					ScoreMode.None)));
	}

	@Test
	public void testDeleteSalesforceDataSource() throws Exception {
		DataSource dataSource1 = _dataSourceDog.addDataSource(
			FaroInfoTestUtil.buildSalesforceDataSource());

		Long dataSourceId1 = dataSource1.getId();

		_addDataToSalesforceDataSource(
			String.valueOf(dataSourceId1), dataSource1.getName());

		DataSource dataSource2 = _dataSourceDog.addDataSource(
			FaroInfoTestUtil.buildSalesforceDataSource());

		Long dataSourceId2 = dataSource2.getId();

		_addDataToSalesforceDataSource(
			String.valueOf(dataSourceId2), dataSource2.getName());

		dataSource1.setDeletionDate(new Date());

		_dataSourceDog.deleteDataSource(dataSource1);

		Assert.assertFalse(
			"Found entry in accounts collection with data source ID " +
				dataSourceId1,
			faroInfoElasticsearchInvoker.exists(
				"accounts",
				QueryBuilders.termQuery(
					"dataSourceId", String.valueOf(dataSourceId1))));
		Assert.assertTrue(
			"Unable to find entry in accounts collection with data source ID " +
				dataSourceId2,
			faroInfoElasticsearchInvoker.exists(
				"accounts",
				QueryBuilders.termQuery(
					"dataSourceId", String.valueOf(dataSourceId2))));
		Assert.assertFalse(
			"Found entry in fields collection with data source ID " +
				dataSourceId1,
			faroInfoElasticsearchInvoker.exists(
				"fields",
				QueryBuilders.termQuery(
					"dataSourceId", String.valueOf(dataSourceId1))));
		Assert.assertTrue(
			"Unable to find entry in fields collection with data source ID " +
				dataSourceId2,
			faroInfoElasticsearchInvoker.exists(
				"fields",
				QueryBuilders.termQuery(
					"dataSourceId", String.valueOf(dataSourceId2))));
		Assert.assertFalse(
			"Found entry in individuals collection with data source ID " +
				dataSourceId1,
			_salesforceRawElasticsearchInvoker.exists(
				"individuals",
				QueryBuilders.termQuery(
					"dataSourceId", String.valueOf(dataSourceId1))));
		Assert.assertTrue(
			"Unable to find entry in individuals collection with data source " +
				"ID " + dataSourceId2,
			_salesforceRawElasticsearchInvoker.exists(
				"individuals",
				QueryBuilders.termQuery(
					"dataSourceId", String.valueOf(dataSourceId2))));
	}

	@Test
	public void testUpdateDataSourceModifiesDataSourceName() {
		DataSource dataSource = FaroInfoTestUtil.buildLiferayDataSource(
			"foo", RandomTestUtil.randomURL());

		DataSource liferayDataSource = FaroInfoTestUtil.buildLiferayDataSource(
			"bar", RandomTestUtil.randomURL());

		liferayDataSource.setId(dataSource.getId());

		dataSource = _dataSourceDog.updateDataSourceConfiguration(
			liferayDataSource);

		Assert.assertEquals("bar", dataSource.getName());
	}

	@Test(expected = Exception.class)
	public void testUpdateDataSourceModifyingToDuplicateDataSourceName() {
		_dataSourceDog.addDataSource(
			FaroInfoTestUtil.buildLiferayDataSource(
				"bar", RandomTestUtil.randomURL()));

		DataSource dataSource = _dataSourceDog.addDataSource(
			FaroInfoTestUtil.buildLiferayDataSource(
				"foo", RandomTestUtil.randomURL()));

		try {
			DataSource liferayDataSource =
				FaroInfoTestUtil.buildLiferayDataSource(
					"bar", RandomTestUtil.randomURL());

			liferayDataSource.setId(dataSource.getId());

			_dataSourceDog.updateDataSourceConfiguration(liferayDataSource);
		}
		catch (Exception exception) {
			Assert.assertThat(
				exception.getMessage(),
				CoreMatchers.containsString("Duplicate data source name"));

			throw exception;
		}
	}

	@Test
	public void testUpdateDataSourceWithoutModifyingDataSourceName() {
		String dataSourceName = "test";

		DataSource dataSource = _dataSourceDog.addDataSource(
			FaroInfoTestUtil.buildLiferayDataSource(
				dataSourceName, RandomTestUtil.randomURL()));

		String updatedURL = "https://foo.bar";

		DataSource liferayDataSource = FaroInfoTestUtil.buildLiferayDataSource(
			dataSourceName, updatedURL);

		liferayDataSource.setId(dataSource.getId());

		dataSource = _dataSourceDog.updateDataSourceConfiguration(
			liferayDataSource);

		Assert.assertEquals(updatedURL, dataSource.getURL());
	}

	private void _addActivityAndUserToLiferayDataSource(DataSource dataSource) {
		if (dataSource == null) {
			return;
		}

		Long dataSourceId = dataSource.getId();

		if (dataSourceId == null) {
			return;
		}

		ActivityGroup activityGroup = FaroInfoTestUtil.buildActivityGroup(
			dataSourceId,
			_individualDog.addIndividual(
				FaroInfoTestUtil.buildIndividual(dataSource), false));

		activityGroup = _activityGroupRepository.save(activityGroup);

		_faroInfoActivityDog.addActivity(
			FaroInfoTestUtil.buildActivityJSONObject(
				_objectMapper.convertValue(activityGroup, JSONObject.class),
				faroInfoElasticsearchInvoker.add(
					"assets",
					FaroInfoTestUtil.buildPageAssetJSONObject(dataSourceId)),
				"pageViewed", new String[0]));

		DXPEntity dxpEntity = new DXPEntity();

		dxpEntity.setDataSourceId(dataSourceId);
		dxpEntity.setFieldsJSONObject(
			JSONUtil.put(
				"contact",
				JSONUtil.put(
					"email", RandomTestUtil.randomEmailAddress()
				).put(
					"firstName", RandomTestUtil.randomString()
				).put(
					"jobTitle", RandomTestUtil.randomString()
				).put(
					"lastName", RandomTestUtil.randomString()
				).put(
					"subscription", RandomTestUtil.randomString()
				)
			).put(
				"modifiedDate", System.currentTimeMillis()
			).put(
				"userId", RandomTestUtil.randomNumber()
			).put(
				"uuid", RandomTestUtil.randomUUID()
			));

		_dxpEntityDog.addDXPEntity(dxpEntity, DXPEntity.Type.USER);
	}

	private void _addDataToSalesforceDataSource(
		String dataSourceId, String dataSourceName) {

		faroInfoElasticsearchInvoker.add(
			"accounts",
			JSONUtil.put(
				"accountPK", RandomTestUtil.randomId()
			).put(
				"dataSourceId", dataSourceId
			));

		faroInfoElasticsearchInvoker.add(
			"fields",
			FaroInfoTestUtil.buildFieldJSONObject(
				dataSourceId, dataSourceName));

		_salesforceRawElasticsearchInvoker.add(
			"individuals",
			JSONUtil.put(
				"dataSourceId", dataSourceId
			).put(
				"email", RandomTestUtil.randomEmailAddress()
			).put(
				"firstName", RandomTestUtil.randomString()
			).put(
				"id", RandomTestUtil.randomUUID()
			).put(
				"jobTitle", RandomTestUtil.randomString()
			).put(
				"lastName", RandomTestUtil.randomString()
			).put(
				"modifiedDate", DateUtil.newDayDateString()
			).put(
				"subscription", RandomTestUtil.randomString()
			));
	}

	private void _mock() {
		Mockito.when(
			_salesforceExtractorConfigurationDog.getState(
				ArgumentMatchers.any(DataSource.class))
		).thenReturn(
			"CREDENTIALS_VALID"
		);
	}

	@Autowired
	private ActivityGroupRepository _activityGroupRepository;

	@MockBean
	private ChannelHttp _channelHttp;

	@Autowired
	private DataSourceDog _dataSourceDog;

	@Autowired
	private DXPEntityDog _dxpEntityDog;

	@Autowired
	private FaroInfoActivityDog _faroInfoActivityDog;

	@Autowired
	private FieldMappingDog _fieldMappingDog;

	@Autowired
	private FieldMappingRepository _fieldMappingRepository;

	@Autowired
	private FieldRepository _fieldRepository;

	@Autowired
	private IndividualDog _individualDog;

	@Autowired
	private ObjectMapper _objectMapper;

	@MockBean
	private SalesforceExtractorConfigurationDog
		_salesforceExtractorConfigurationDog;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_SALESFORCE_RAW)
	private ElasticsearchInvoker _salesforceRawElasticsearchInvoker;

	@Autowired
	private SegmentDog _segmentDog;

}