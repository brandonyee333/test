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
import com.liferay.osb.asah.common.dog.DataSourceDog;
import com.liferay.osb.asah.common.dog.SegmentDog;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoActivityDog;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoIndividualDog;
import com.liferay.osb.asah.common.faro.info.dog.test.BaseFaroInfoDogTestCase;
import com.liferay.osb.asah.common.http.ChannelHttp;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.DataSource;
import com.liferay.osb.asah.common.salesforce.extractor.dog.SalesforceExtractorConfigurationDog;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import java.util.HashMap;

import org.apache.lucene.search.join.ScoreMode;

import org.elasticsearch.index.query.QueryBuilders;

import org.hamcrest.CoreMatchers;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

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
	public void setUp() throws Exception {
		_mock();
	}

	@Test
	public void testAddDataSource() throws Exception {
		_dataSourceDog.addDataSource(
			_objectMapper.convertValue(
				FaroInfoTestUtil.buildLiferayDataSourceJSONObject(),
				DataSource.class));
	}

	@Test
	public void testAddDataSourceUpdatesExistingIndividual() throws Exception {
		JSONObject dataSourceJSONObject1 = _objectMapper.convertValue(
			_dataSourceDog.addDataSource(
				_objectMapper.convertValue(
					FaroInfoTestUtil.buildLiferayDataSourceJSONObject(),
					DataSource.class)),
			JSONObject.class);
		JSONObject dataSourceJSONObject2 = _objectMapper.convertValue(
			_dataSourceDog.addDataSource(
				_objectMapper.convertValue(
					FaroInfoTestUtil.buildLiferayDataSourceJSONObject(),
					DataSource.class)),
			JSONObject.class);

		String dataSourceId1 = dataSourceJSONObject1.getString("id");
		String dataSourceId2 = dataSourceJSONObject2.getString("id");

		faroInfoElasticsearchInvoker.add(
			"field-mappings",
			FaroInfoTestUtil.buildIndividualFieldMappingJSONObject(
				JSONUtil.put(
					dataSourceId1, "email"
				).put(
					dataSourceId2, "emailAddress"
				),
				"email", "http://schema.org/email"));

		faroInfoElasticsearchInvoker.add(
			"field-mappings",
			FaroInfoTestUtil.buildIndividualFieldMappingJSONObject(
				dataSourceId2, "givenName", "givenName", "Text"));

		JSONObject individualJSONObject = _faroInfoIndividualDog.addIndividual(
			FaroInfoTestUtil.buildIndividualJSONObject(dataSourceJSONObject1),
			false);

		faroInfoElasticsearchInvoker.add(
			"fields",
			FaroInfoTestUtil.buildIndividualFieldJSONObject(
				dataSourceJSONObject1, "email", "http://schema.org/email",
				RandomTestUtil.randomString(), individualJSONObject, "email"));

		individualJSONObject = _faroInfoIndividualDog.updateIndividual(
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
			_objectMapper.convertValue(dataSourceJSONObject2, DataSource.class),
			individualJSONObject);

		JSONObject demographicsJSONObject = individualJSONObject.getJSONObject(
			"demographics");

		Assert.assertTrue(
			"Adding data source with information on existing individual " +
				"should update fields of existing individual",
			demographicsJSONObject.has("givenName"));

		Assert.assertTrue(
			"Updating individual from new data source should add an " +
				"enrichment date",
			individualJSONObject.has("lastEnrichmentDate"));
	}

	@Test
	public void testAddDataSourceWithDuplicateName() throws Exception {
		for (int i = 0; i < 4; i++) {
			_dataSourceDog.addDataSource(
				_objectMapper.convertValue(
					FaroInfoTestUtil.buildLiferayDataSourceJSONObject(
						"Liferay", RandomTestUtil.randomURL()),
					DataSource.class));
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
			_objectMapper.convertValue(dataSourceJSONObject1, DataSource.class),
			null, null);

		Assert.assertFalse(
			"Found entry in csv-individuals collection with data source ID " +
				dataSourceId1,
			faroInfoElasticsearchInvoker.exists(
				"csv-individuals",
				QueryBuilders.termQuery("dataSourceId", dataSourceId1)));
		Assert.assertTrue(
			"Unable to find entry in csv-individuals collection with data " +
				"source ID " + dataSourceId2,
			faroInfoElasticsearchInvoker.exists(
				"csv-individuals",
				QueryBuilders.termQuery("dataSourceId", dataSourceId2)));
	}

	@Test
	public void testDeleteDataSourceDeletesEmptyFieldMappings()
		throws Exception {

		JSONObject dataSourceJSONObject = _objectMapper.convertValue(
			_dataSourceDog.addDataSource(
				_objectMapper.convertValue(
					FaroInfoTestUtil.buildLiferayDataSourceJSONObject(),
					DataSource.class)),
			JSONObject.class);

		JSONObject fieldMappingJSONObject = faroInfoElasticsearchInvoker.add(
			"field-mappings",
			FaroInfoTestUtil.buildIndividualFieldMappingJSONObject(
				dataSourceJSONObject.getString("id"), "givenName", "givenName",
				"Text"));

		dataSourceJSONObject.put("deletionDate", DateUtil.newDayDateString());

		_dataSourceDog.deleteDataSource(
			_objectMapper.convertValue(dataSourceJSONObject, DataSource.class),
			null, null);

		Assert.assertFalse(
			"Field mapping should have been deleted on data source deletion",
			faroInfoElasticsearchInvoker.exists(
				"field-mappings", fieldMappingJSONObject.getString("id")));
	}

	@Test
	public void testDeleteDataSourceDeletesIndividual() throws Exception {
		JSONObject dataSourceJSONObject = _objectMapper.convertValue(
			_dataSourceDog.addDataSource(
				_objectMapper.convertValue(
					FaroInfoTestUtil.buildLiferayDataSourceJSONObject(),
					DataSource.class)),
			JSONObject.class);

		JSONObject individualJSONObject = _faroInfoIndividualDog.addIndividual(
			FaroInfoTestUtil.buildIndividualJSONObject(dataSourceJSONObject),
			false);

		dataSourceJSONObject.put("deletionDate", DateUtil.newDayDateString());

		_dataSourceDog.deleteDataSource(
			_objectMapper.convertValue(dataSourceJSONObject, DataSource.class),
			null, null);

		Assert.assertFalse(
			"Individual was not deleted on data source deletion despite only " +
				"containing fields from the deleted data source",
			faroInfoElasticsearchInvoker.exists(
				"individuals", individualJSONObject.getString("id")));
	}

	@Test
	public void testDeleteDataSourceDeletesReferenceInFieldMapping()
		throws Exception {

		JSONObject dataSourceJSONObject1 = _objectMapper.convertValue(
			_dataSourceDog.addDataSource(
				_objectMapper.convertValue(
					FaroInfoTestUtil.buildLiferayDataSourceJSONObject(),
					DataSource.class)),
			JSONObject.class);

		JSONObject dataSourceJSONObject2 = _objectMapper.convertValue(
			_dataSourceDog.addDataSource(
				_objectMapper.convertValue(
					FaroInfoTestUtil.buildLiferayDataSourceJSONObject(),
					DataSource.class)),
			JSONObject.class);

		String dataSourceId1 = dataSourceJSONObject1.getString("id");
		String dataSourceId2 = dataSourceJSONObject2.getString("id");

		faroInfoElasticsearchInvoker.add(
			"field-mappings",
			FaroInfoTestUtil.buildIndividualFieldMappingJSONObject(
				JSONUtil.put(
					dataSourceId1, "givenName"
				).put(
					dataSourceId2, "givenName"
				),
				"givenName", "Text"));

		dataSourceJSONObject1.put("deletionDate", DateUtil.newDayDateString());

		_dataSourceDog.deleteDataSource(
			_objectMapper.convertValue(dataSourceJSONObject1, DataSource.class),
			null, null);

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

		JSONObject dataSourceJSONObject = _objectMapper.convertValue(
			_dataSourceDog.addDataSource(
				_objectMapper.convertValue(
					FaroInfoTestUtil.buildLiferayDataSourceJSONObject(),
					DataSource.class)),
			JSONObject.class);

		faroInfoElasticsearchInvoker.add(
			"field-mappings",
			FaroInfoTestUtil.buildIndividualFieldMappingJSONObject(
				dataSourceJSONObject.getString("id"), "givenName", "givenName",
				"Text"));

		JSONObject individualSegmentJSONObject =
			_segmentDog.addIndividualSegment(
				FaroInfoTestUtil.buildDynamicIndividualSegmentJSONObject(
					"(((demographics/givenName/value ne null)))"));

		dataSourceJSONObject.put("deletionDate", DateUtil.newDayDateString());

		_dataSourceDog.deleteDataSource(
			_objectMapper.convertValue(dataSourceJSONObject, DataSource.class),
			null, null);

		individualSegmentJSONObject = faroInfoElasticsearchInvoker.get(
			"individual-segments", individualSegmentJSONObject.getString("id"));

		Assert.assertEquals(
			"Individual dynamic segment with properties was not disabled " +
				"when data source was deleted",
			"DISABLED", individualSegmentJSONObject.getString("state"));
	}

	@Test
	public void testDeleteDataSourceDisablesIndividualDynamicSegment2()
		throws Exception {

		JSONObject dataSourceJSONObject = _objectMapper.convertValue(
			_dataSourceDog.addDataSource(
				_objectMapper.convertValue(
					FaroInfoTestUtil.buildLiferayDataSourceJSONObject(),
					DataSource.class)),
			JSONObject.class);

		JSONObject assetJSONObject = faroInfoElasticsearchInvoker.add(
			"assets",
			FaroInfoTestUtil.buildPageAssetJSONObject(
				dataSourceJSONObject.getString("id")));

		JSONObject individualSegmentJSONObject =
			_segmentDog.addIndividualSegment(
				FaroInfoTestUtil.buildDynamicIndividualSegmentJSONObject(
					"activities/ever eq 'page#pageViewed#" +
						assetJSONObject.getString("id") + "'"));

		dataSourceJSONObject.put("deletionDate", DateUtil.newDayDateString());

		_dataSourceDog.deleteDataSource(
			_objectMapper.convertValue(dataSourceJSONObject, DataSource.class),
			null, null);

		individualSegmentJSONObject = faroInfoElasticsearchInvoker.get(
			"individual-segments", individualSegmentJSONObject.getString("id"));

		Assert.assertEquals(
			"Individual dynamic segment with assets was not disabled when " +
				"data source was deleted",
			"DISABLED", individualSegmentJSONObject.getString("state"));
	}

	@Test
	public void testDeleteDataSourceDoesNotDeleteDefaultFieldMappings()
		throws Exception {

		JSONObject dataSourceJSONObject = _objectMapper.convertValue(
			_dataSourceDog.addDataSource(
				_objectMapper.convertValue(
					FaroInfoTestUtil.buildLiferayDataSourceJSONObject(),
					DataSource.class)),
			JSONObject.class);

		JSONObject fieldMappingJSONObject = faroInfoElasticsearchInvoker.add(
			"field-mappings",
			FaroInfoTestUtil.buildIndividualFieldMappingJSONObject(
				JSONUtil.put(
					"id", "FARO_SYSTEM"
				).put(
					"name", "FARO_SYSTEM"
				),
				dataSourceJSONObject.getString("id"), "givenName", "givenName",
				"Text"));

		dataSourceJSONObject.put("deletionDate", DateUtil.newDayDateString());

		_dataSourceDog.deleteDataSource(
			_objectMapper.convertValue(dataSourceJSONObject, DataSource.class),
			null, null);

		Assert.assertTrue(
			"Field mappings created by the default user should not be " +
				"deleted on data source deletion",
			faroInfoElasticsearchInvoker.exists(
				"field-mappings", fieldMappingJSONObject.getString("id")));
	}

	@Test
	public void testDeleteDataSourceUpdatesIndividualFields() throws Exception {
		JSONObject dataSourceJSONObject1 = _objectMapper.convertValue(
			_dataSourceDog.addDataSource(
				_objectMapper.convertValue(
					FaroInfoTestUtil.buildLiferayDataSourceJSONObject(),
					DataSource.class)),
			JSONObject.class);

		JSONObject dataSourceJSONObject2 = _objectMapper.convertValue(
			_dataSourceDog.addDataSource(
				_objectMapper.convertValue(
					FaroInfoTestUtil.buildLiferayDataSourceJSONObject(),
					DataSource.class)),
			JSONObject.class);

		String dataSourceId1 = dataSourceJSONObject1.getString("id");
		String dataSourceId2 = dataSourceJSONObject2.getString("id");

		faroInfoElasticsearchInvoker.add(
			"field-mappings",
			FaroInfoTestUtil.buildIndividualFieldMappingJSONObject(
				JSONUtil.put(
					dataSourceId1, "email"
				).put(
					dataSourceId2, "emailAddress"
				),
				"email", "http://schema.org/email"));

		faroInfoElasticsearchInvoker.add(
			"field-mappings",
			FaroInfoTestUtil.buildIndividualFieldMappingJSONObject(
				dataSourceId2, "givenName", "givenName", "Text"));

		JSONObject individualJSONObject = _faroInfoIndividualDog.addIndividual(
			FaroInfoTestUtil.buildIndividualJSONObject(dataSourceJSONObject1),
			false);

		faroInfoElasticsearchInvoker.add(
			"fields",
			FaroInfoTestUtil.buildIndividualFieldJSONObject(
				dataSourceJSONObject1, "email", "http://schema.org/email",
				RandomTestUtil.randomString(), individualJSONObject, "email"));

		individualJSONObject = _faroInfoIndividualDog.updateIndividual(
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
			_objectMapper.convertValue(dataSourceJSONObject2, DataSource.class),
			individualJSONObject);

		String lastEnrichmentDate = individualJSONObject.getString(
			"lastEnrichmentDate");

		dataSourceJSONObject2.put("deletionDate", DateUtil.newDayDateString());

		_dataSourceDog.deleteDataSource(
			_objectMapper.convertValue(dataSourceJSONObject2, DataSource.class),
			null, null);

		Assert.assertTrue(
			"Individual was deleted even though another data source with " +
				"data on the individual exists",
			faroInfoElasticsearchInvoker.exists(
				"individuals", individualJSONObject.getString("id")));

		individualJSONObject = faroInfoElasticsearchInvoker.get(
			"individuals", individualJSONObject.getString("id"));

		Assert.assertFalse(
			"Data source individual PK was not deleted on data source deletion",
			faroInfoElasticsearchInvoker.exists(
				"individuals",
				QueryBuilders.nestedQuery(
					"dataSourceIndividualPKs",
					QueryBuilders.termQuery(
						"dataSourceIndividualPKs.dataSourceId", dataSourceId2),
					ScoreMode.None)));

		JSONObject demographicsJSONObject = individualJSONObject.getJSONObject(
			"demographics");

		Assert.assertFalse(
			"Individual field 'givenName' was not deleted",
			demographicsJSONObject.has("givenName"));

		JSONArray emailJSONArray = demographicsJSONObject.getJSONArray("email");

		JSONObject emailJSONObject = emailJSONArray.getJSONObject(0);

		Assert.assertEquals(
			dataSourceId1, String.valueOf(emailJSONObject.get("dataSourceId")));

		Assert.assertEquals(
			"Data source deletion should not count towards individual " +
				"enrichment",
			lastEnrichmentDate,
			individualJSONObject.getString("lastEnrichmentDate"));
	}

	@Test
	public void testDeleteLiferayDataSource() throws Exception {
		JSONObject dataSourceJSONObject1 = _objectMapper.convertValue(
			_dataSourceDog.addDataSource(
				_objectMapper.convertValue(
					FaroInfoTestUtil.buildLiferayDataSourceJSONObject(),
					DataSource.class)),
			JSONObject.class);

		_addActivityAndUserToLiferayDataSource(dataSourceJSONObject1);

		JSONObject dataSourceJSONObject2 = _objectMapper.convertValue(
			_dataSourceDog.addDataSource(
				_objectMapper.convertValue(
					FaroInfoTestUtil.buildLiferayDataSourceJSONObject(),
					DataSource.class)),
			JSONObject.class);

		_addActivityAndUserToLiferayDataSource(dataSourceJSONObject2);

		dataSourceJSONObject1.put(
			"deletionDate", DateUtil.addDays(DateUtil.newDayDateString(), -1));

		_dataSourceDog.deleteDataSource(
			_objectMapper.convertValue(dataSourceJSONObject1, DataSource.class),
			null, null);

		String dataSourceId1 = dataSourceJSONObject1.getString("id");
		String dataSourceId2 = dataSourceJSONObject2.getString("id");

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
		JSONObject dataSourceJSONObject1 = _objectMapper.convertValue(
			_dataSourceDog.addDataSource(
				FaroInfoTestUtil.buildSalesforceDataSource()),
			JSONObject.class);

		String dataSourceId1 = dataSourceJSONObject1.getString("id");

		_addDataToSalesforceDataSource(
			dataSourceId1, dataSourceJSONObject1.getString("name"));

		JSONObject dataSourceJSONObject2 = _objectMapper.convertValue(
			_dataSourceDog.addDataSource(
				FaroInfoTestUtil.buildSalesforceDataSource()),
			JSONObject.class);

		String dataSourceId2 = dataSourceJSONObject2.getString("id");

		_addDataToSalesforceDataSource(
			dataSourceId2, dataSourceJSONObject2.getString("name"));

		dataSourceJSONObject1.put("deletionDate", DateUtil.newDayDateString());

		_dataSourceDog.deleteDataSource(
			_objectMapper.convertValue(dataSourceJSONObject1, DataSource.class),
			null, null);

		Assert.assertFalse(
			"Found entry in accounts collection with data source ID " +
				dataSourceId1,
			faroInfoElasticsearchInvoker.exists(
				"accounts",
				QueryBuilders.termQuery("dataSourceId", dataSourceId1)));
		Assert.assertTrue(
			"Unable to find entry in accounts collection with data source ID " +
				dataSourceId2,
			faroInfoElasticsearchInvoker.exists(
				"accounts",
				QueryBuilders.termQuery("dataSourceId", dataSourceId2)));
		Assert.assertFalse(
			"Found entry in fields collection with data source ID " +
				dataSourceId1,
			faroInfoElasticsearchInvoker.exists(
				"fields",
				QueryBuilders.termQuery("dataSourceId", dataSourceId1)));
		Assert.assertTrue(
			"Unable to find entry in fields collection with data source ID " +
				dataSourceId2,
			faroInfoElasticsearchInvoker.exists(
				"fields",
				QueryBuilders.termQuery("dataSourceId", dataSourceId2)));
		Assert.assertFalse(
			"Found entry in individuals collection with data source ID " +
				dataSourceId1,
			_salesforceRawElasticsearchInvoker.exists(
				"individuals",
				QueryBuilders.termQuery("osbAsahDataSourceId", dataSourceId1)));
		Assert.assertTrue(
			"Unable to find entry in individuals collection with data source " +
				"ID " + dataSourceId2,
			_salesforceRawElasticsearchInvoker.exists(
				"individuals",
				QueryBuilders.termQuery("osbAsahDataSourceId", dataSourceId2)));
	}

	@Test
	public void testUpdateDataSourceModifiesDataSourceName() throws Exception {
		JSONObject dataSourceJSONObject = _objectMapper.convertValue(
			_dataSourceDog.addDataSource(
				_objectMapper.convertValue(
					FaroInfoTestUtil.buildLiferayDataSourceJSONObject(
						"foo", RandomTestUtil.randomURL()),
					DataSource.class)),
			JSONObject.class);

		JSONObject liferayDataSourceJSONObject =
			FaroInfoTestUtil.buildLiferayDataSourceJSONObject(
				"bar", RandomTestUtil.randomURL());

		liferayDataSourceJSONObject.put(
			"id", dataSourceJSONObject.getString("id"));

		dataSourceJSONObject = _objectMapper.convertValue(
			_dataSourceDog.updateDataSourceConfiguration(
				_objectMapper.convertValue(
					liferayDataSourceJSONObject, DataSource.class)),
			JSONObject.class);

		Assert.assertEquals("bar", dataSourceJSONObject.getString("name"));
	}

	@Test(expected = Exception.class)
	public void testUpdateDataSourceModifyingToDuplicateDataSourceName()
		throws Exception {

		_dataSourceDog.addDataSource(
			_objectMapper.convertValue(
				FaroInfoTestUtil.buildLiferayDataSourceJSONObject(
					"bar", RandomTestUtil.randomURL()),
				DataSource.class));

		JSONObject dataSourceJSONObject = _objectMapper.convertValue(
			_dataSourceDog.addDataSource(
				_objectMapper.convertValue(
					FaroInfoTestUtil.buildLiferayDataSourceJSONObject(
						"foo", RandomTestUtil.randomURL()),
					DataSource.class)),
			JSONObject.class);

		try {
			JSONObject liferayDataSourceJSONObject =
				FaroInfoTestUtil.buildLiferayDataSourceJSONObject(
					"bar", RandomTestUtil.randomURL());

			liferayDataSourceJSONObject.put(
				"id", dataSourceJSONObject.getString("id"));

			_dataSourceDog.updateDataSourceConfiguration(
				_objectMapper.convertValue(
					liferayDataSourceJSONObject, DataSource.class));
		}
		catch (Exception e) {
			Assert.assertThat(
				e.getMessage(),
				CoreMatchers.containsString("Duplicate data source name"));

			throw e;
		}
	}

	@Test
	public void testUpdateDataSourceWithoutModifyingDataSourceName()
		throws Exception {

		String dataSourceName = "test";

		JSONObject dataSourceJSONObject = _objectMapper.convertValue(
			_dataSourceDog.addDataSource(
				_objectMapper.convertValue(
					FaroInfoTestUtil.buildLiferayDataSourceJSONObject(
						dataSourceName, RandomTestUtil.randomURL()),
					DataSource.class)),
			JSONObject.class);

		String updatedURL = "https://foo.bar";

		JSONObject liferayDataSourceJSONObject =
			FaroInfoTestUtil.buildLiferayDataSourceJSONObject(
				dataSourceName, updatedURL);

		liferayDataSourceJSONObject.put(
			"id", dataSourceJSONObject.getString("id"));

		dataSourceJSONObject = _objectMapper.convertValue(
			_dataSourceDog.updateDataSourceConfiguration(
				_objectMapper.convertValue(
					liferayDataSourceJSONObject, DataSource.class)),
			JSONObject.class);

		Assert.assertEquals(updatedURL, dataSourceJSONObject.getString("url"));
	}

	private void _addActivityAndUserToLiferayDataSource(
			JSONObject dataSourceJSONObject)
		throws Exception {

		String dataSourceId = dataSourceJSONObject.getString("id");

		_faroInfoActivityDog.addActivity(
			FaroInfoTestUtil.buildActivityJSONObject(
				faroInfoElasticsearchInvoker.add(
					"activity-groups",
					FaroInfoTestUtil.buildActivityGroupJSONObject(
						dataSourceId,
						_faroInfoIndividualDog.addIndividual(
							FaroInfoTestUtil.buildIndividualJSONObject(
								dataSourceJSONObject),
							false))),
				faroInfoElasticsearchInvoker.add(
					"assets",
					FaroInfoTestUtil.buildPageAssetJSONObject(dataSourceId)),
				"pageViewed", new String[0]));

		_dxpRawElasticsearchInvoker.add(
			"users",
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
				"osbAsahDataSourceId", dataSourceId
			).put(
				"userId", RandomTestUtil.randomNumber()
			).put(
				"uuid", RandomTestUtil.randomUUID()
			));
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
				"osbAsahDataSourceId", dataSourceId
			).put(
				"subscription", RandomTestUtil.randomString()
			));
	}

	private void _mock() {
		Mockito.when(
			_salesforceExtractorConfigurationDog.getState(
				Mockito.any(DataSource.class))
		).thenReturn(
			"CREDENTIALS_VALID"
		);
	}

	@MockBean
	private ChannelHttp _channelHttp;

	@Autowired
	private DataSourceDog _dataSourceDog;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_DXP_RAW)
	private ElasticsearchInvoker _dxpRawElasticsearchInvoker;

	@Autowired
	private FaroInfoActivityDog _faroInfoActivityDog;

	@Autowired
	private FaroInfoIndividualDog _faroInfoIndividualDog;

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