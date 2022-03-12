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
import com.liferay.osb.asah.common.entity.Asset;
import com.liferay.osb.asah.common.entity.DXPEntity;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.entity.Field;
import com.liferay.osb.asah.common.entity.FieldMapping;
import com.liferay.osb.asah.common.entity.Individual;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoActivityDog;
import com.liferay.osb.asah.common.faro.info.dog.test.BaseFaroInfoDogTestCase;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.repository.ActivityGroupRepository;
import com.liferay.osb.asah.common.repository.AssetRepository;
import com.liferay.osb.asah.common.repository.CSVIndividualRepository;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.common.repository.FieldMappingRepository;
import com.liferay.osb.asah.common.repository.FieldRepository;
import com.liferay.osb.asah.common.repository.helper.FilterHelper;
import com.liferay.osb.asah.common.salesforce.extractor.dog.SalesforceExtractorConfigurationDog;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.spring.OSBAsahElasticsearchTestExecutionListener;
import com.liferay.osb.asah.test.util.spring.OSBAsahRepositoryTestExecutionListener;
import com.liferay.osb.asah.test.util.spring.OSBAsahSQLTestExecutionListener;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

import org.apache.lucene.search.join.ScoreMode;

import org.elasticsearch.index.query.QueryBuilders;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;

import org.json.JSONObject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.boot.test.mock.mockito.ResetMocksTestExecutionListener;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

/**
 * @author Leslie Wong
 */
@TestExecutionListeners(
	mergeMode = TestExecutionListeners.MergeMode.REPLACE_DEFAULTS,
	value = {
		DependencyInjectionTestExecutionListener.class,
		MockitoTestExecutionListener.class,
		OSBAsahElasticsearchTestExecutionListener.class,
		OSBAsahRepositoryTestExecutionListener.class,
		OSBAsahSQLTestExecutionListener.class,
		ResetMocksTestExecutionListener.class
	}
)
public class DataSourceHttpTest extends BaseFaroInfoDogTestCase {

	@BeforeEach
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

		Assertions.assertTrue(
			stream.anyMatch(
				field -> Objects.equals(field.getName(), "givenName")),
			"Adding data source with information on existing individual " +
				"should update fields of existing individual");

		Assertions.assertTrue(
			!Objects.isNull(individual.getLastEnrichmentDate()),
			"Updating individual from new data source should add an " +
				"enrichment date");
	}

	@Test
	public void testAddDataSourceWithDuplicateName() {
		for (int i = 0; i < 4; i++) {
			_dataSourceDog.addDataSource(
				FaroInfoTestUtil.buildLiferayDataSource(
					"Liferay", RandomTestUtil.randomURL()));
		}

		List<DataSource> dataSources = _dataSourceDog.getDataSources();

		Assertions.assertEquals(4L, dataSources.size());

		Assertions.assertTrue(_dataSourceRepository.existsByName("Liferay"));
		Assertions.assertTrue(
			_dataSourceRepository.existsByName("Liferay (1)"));
		Assertions.assertTrue(
			_dataSourceRepository.existsByName("Liferay (2)"));
		Assertions.assertTrue(
			_dataSourceRepository.existsByName("Liferay (3)"));
	}

	@Test
	public void testDeleteCSVDataSource() throws Exception {
		DataSource dataSource1 = _dataSourceDog.addDataSource(
			FaroInfoTestUtil.buildCSVDataSource());

		dataSource1.setName("DataSource1");

		_dataSourceRepository.save(dataSource1);

		DataSource dataSource2 = _dataSourceDog.addDataSource(
			FaroInfoTestUtil.buildCSVDataSource());

		dataSource2.setName("DataSource2");

		_dataSourceRepository.save(dataSource2);

		_csvIndividualRepository.save(
			FaroInfoTestUtil.buildCSVIndividual(
				dataSource1.getId(), RandomTestUtil.randomUUID(),
				_objectMapper.convertValue(new HashMap<>(), JSONObject.class)));

		_csvIndividualRepository.save(
			FaroInfoTestUtil.buildCSVIndividual(
				dataSource2.getId(), RandomTestUtil.randomUUID(),
				_objectMapper.convertValue(new HashMap<>(), JSONObject.class)));

		dataSource1.setDeletionDate(new Date());

		_dataSourceRepository.delete(dataSource1);

		Assertions.assertFalse(
			_dataSourceRepository.existsByName("DataSource1"));
		Assertions.assertTrue(
			_dataSourceRepository.existsByName("DataSource2"));
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

		Assertions.assertFalse(
			_fieldMappingDog.existsById(fieldMapping.getId()),
			"Field mapping should have been deleted on data source deletion");
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

		Assertions.assertFalse(
			_individualDog.existsById(individualId),
			"Individual was not deleted on data source deletion despite only " +
				"containing fields from the deleted data source");
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

		Assertions.assertFalse(
			faroInfoElasticsearchInvoker.exists(
				"field-mappings",
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery("fieldName", "givenName")
				).filter(
					QueryBuilders.existsQuery(
						"dataSourceFieldNames." + dataSourceId1)
				)),
			"Field mapping reference to deleted data source was not removed");
		Assertions.assertTrue(
			faroInfoElasticsearchInvoker.exists(
				"field-mappings",
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery("fieldName", "givenName")
				).filter(
					QueryBuilders.existsQuery(
						"dataSourceFieldNames." + dataSourceId2)
				)),
			"Field mapping reference to existing data source was removed on " +
				"deletion of another data source");
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

		Assertions.assertEquals(
			"DISABLED", segment.getState(),
			"Individual dynamic segment with properties was not disabled " +
				"when data source was deleted");
	}

	@Test
	public void testDeleteDataSourceDisablesIndividualDynamicSegment2()
		throws Exception {

		DataSource dataSource = _dataSourceDog.addDataSource(
			FaroInfoTestUtil.buildLiferayDataSource());

		Asset asset = _assetRepository.save(
			_objectMapper.convertValue(
				FaroInfoTestUtil.buildPageAssetJSONObject(dataSource.getId()),
				Asset.class));

		Segment segment = _segmentDog.addSegment(
			FaroInfoTestUtil.buildDynamicSegment(
				"activities/ever eq 'page#pageViewed#" + asset.getId() + "'"));

		dataSource.setDeletionDate(new Date());

		_dataSourceDog.deleteDataSource(dataSource);

		segment = _segmentDog.getSegment(segment.getId());

		Assertions.assertEquals(
			"DISABLED", segment.getState(),
			"Individual dynamic segment with assets was not disabled when " +
				"data source was deleted");
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

		Assertions.assertTrue(
			_fieldMappingDog.existsById(fieldMapping.getId()),
			"Field mappings created by the default user should not be " +
				"deleted on data source deletion");
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

		Assertions.assertTrue(
			_individualDog.existsById(individualId),
			"Individual was deleted even though another data source with " +
				"data on the individual exists");

		individual = _individualDog.fetchIndividual(individualId);

		Assertions.assertFalse(
			_individualDog.existsByDataSourceIndividualPK(
				dataSourceId2, individualId),
			"Data source individual PK was not deleted on data source " +
				"deletion");

		Set<Field> fields = individual.getFields();

		Stream<Field> stream = fields.stream();

		Assertions.assertFalse(
			stream.anyMatch(
				field -> Objects.equals(field.getName(), "givenName")),
			"Individual field 'givenName' was not deleted");

		stream = fields.stream();

		Field emailField = stream.filter(
			field -> Objects.equals(field.getName(), "email")
		).findFirst(
		).orElse(
			null
		);

		Assertions.assertNotNull(emailField);

		Assertions.assertEquals(dataSourceId1, emailField.getDataSourceId());

		Assertions.assertEquals(
			lastEnrichmentDate, individual.getLastEnrichmentDate(),
			"Data source deletion should not count towards individual " +
				"enrichment");
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

		List<Asset> assets = _assetRepository.findByFilterString(
			new FilterHelper("dataSourceId eq '" + dataSourceId1 + "'"),
			PageRequest.of(0, 10));

		Assertions.assertFalse(assets.isEmpty());

		assets = _assetRepository.findByFilterString(
			new FilterHelper("dataSourceId eq '" + dataSourceId2 + "'"),
			PageRequest.of(0, 10));

		Assertions.assertFalse(assets.isEmpty());

		for (String index : new String[] {"activities", "activity-groups"}) {
			Assertions.assertTrue(
				faroInfoElasticsearchInvoker.exists(
					index,
					QueryBuilders.termQuery("dataSourceId", dataSourceId1)),
				"Unable to find entry in " + index + " collection with data " +
					"source ID " + dataSourceId1);
			Assertions.assertTrue(
				faroInfoElasticsearchInvoker.exists(
					index,
					QueryBuilders.termQuery("dataSourceId", dataSourceId2)),
				"Unable to find entry in " + index + " collection with data " +
					"source ID " + dataSourceId2);
		}

		Assertions.assertFalse(
			faroInfoElasticsearchInvoker.exists(
				"individuals",
				QueryBuilders.nestedQuery(
					"dataSourceIndividualPKs",
					QueryBuilders.termQuery(
						"dataSourceIndividualPKs.dataSourceId", dataSourceId1),
					ScoreMode.None)),
			"Individuals from deleted data source still exist");
		Assertions.assertTrue(
			faroInfoElasticsearchInvoker.exists(
				"individuals",
				QueryBuilders.nestedQuery(
					"dataSourceIndividualPKs",
					QueryBuilders.termQuery(
						"dataSourceIndividualPKs.dataSourceId", dataSourceId2),
					ScoreMode.None)),
			"Individuals from data source " + dataSourceId2 + " were deleted " +
				"when deleting data source " + dataSourceId1);
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

		Assertions.assertFalse(
			faroInfoElasticsearchInvoker.exists(
				"accounts",
				QueryBuilders.termQuery(
					"dataSourceId", String.valueOf(dataSourceId1))),
			"Found entry in accounts collection with data source ID " +
				dataSourceId1);
		Assertions.assertTrue(
			faroInfoElasticsearchInvoker.exists(
				"accounts",
				QueryBuilders.termQuery(
					"dataSourceId", String.valueOf(dataSourceId2))),
			"Unable to find entry in accounts collection with data source ID " +
				dataSourceId2);
		Assertions.assertFalse(
			faroInfoElasticsearchInvoker.exists(
				"fields",
				QueryBuilders.termQuery(
					"dataSourceId", String.valueOf(dataSourceId1))),
			"Found entry in fields collection with data source ID " +
				dataSourceId1);
		Assertions.assertTrue(
			faroInfoElasticsearchInvoker.exists(
				"fields",
				QueryBuilders.termQuery(
					"dataSourceId", String.valueOf(dataSourceId2))),
			"Unable to find entry in fields collection with data source ID " +
				dataSourceId2);
		Assertions.assertFalse(
			_salesforceRawElasticsearchInvoker.exists(
				"individuals",
				QueryBuilders.termQuery(
					"dataSourceId", String.valueOf(dataSourceId1))),
			"Found entry in individuals collection with data source ID " +
				dataSourceId1);
		Assertions.assertTrue(
			_salesforceRawElasticsearchInvoker.exists(
				"individuals",
				QueryBuilders.termQuery(
					"dataSourceId", String.valueOf(dataSourceId2))),
			"Unable to find entry in individuals collection with data source " +
				"ID " + dataSourceId2);
	}

	@Test
	public void testUpdateDataSourceModifiesDataSourceName() {
		DataSource dataSource = _dataSourceDog.addDataSource(
			FaroInfoTestUtil.buildLiferayDataSource(
				"foo", RandomTestUtil.randomURL()));

		DataSource liferayDataSource = FaroInfoTestUtil.buildLiferayDataSource(
			"bar", RandomTestUtil.randomURL());

		liferayDataSource.setId(dataSource.getId());
		liferayDataSource.setIsNew(Boolean.FALSE);

		dataSource = _dataSourceDog.updateDataSourceConfiguration(
			liferayDataSource);

		Assertions.assertEquals("bar", dataSource.getName());
	}

	@Test
	public void testUpdateDataSourceModifyingToDuplicateDataSourceName() {
		_dataSourceDog.addDataSource(
			FaroInfoTestUtil.buildLiferayDataSource(
				"bar", RandomTestUtil.randomURL()));

		DataSource dataSource = _dataSourceDog.addDataSource(
			FaroInfoTestUtil.buildLiferayDataSource(
				"foo", RandomTestUtil.randomURL()));

		DataSource liferayDataSource = FaroInfoTestUtil.buildLiferayDataSource(
			"bar", RandomTestUtil.randomURL());

		liferayDataSource.setId(dataSource.getId());

		Exception exception = Assertions.assertThrows(
			Exception.class,
			() -> _dataSourceDog.updateDataSourceConfiguration(
				liferayDataSource));

		MatcherAssert.assertThat(
			exception.getMessage(),
			CoreMatchers.containsString("Duplicate data source name"));
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
		liferayDataSource.setIsNew(Boolean.FALSE);

		dataSource = _dataSourceDog.updateDataSourceConfiguration(
			liferayDataSource);

		Assertions.assertEquals(updatedURL, dataSource.getURL());
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
				_objectMapper.convertValue(
					_assetRepository.save(
						_objectMapper.convertValue(
							FaroInfoTestUtil.buildPageAssetJSONObject(
								dataSourceId),
							Asset.class)),
					JSONObject.class),
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

	@Autowired
	private AssetRepository _assetRepository;

	@Autowired
	private CSVIndividualRepository _csvIndividualRepository;

	@Autowired
	private DataSourceDog _dataSourceDog;

	@Autowired
	private DataSourceRepository _dataSourceRepository;

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