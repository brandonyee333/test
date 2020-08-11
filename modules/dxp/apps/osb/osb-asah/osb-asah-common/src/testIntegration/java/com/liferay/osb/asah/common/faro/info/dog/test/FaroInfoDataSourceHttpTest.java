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

package com.liferay.osb.asah.common.faro.info.dog.test;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dxp.extractor.dog.DXPExtractorConfigurationDog;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoActivityDog;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoDataSourceDog;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoIndividualDog;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoIndividualSegmentDog;
import com.liferay.osb.asah.common.http.ChannelHttp;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.salesforce.extractor.dog.SalesforceExtractorConfigurationDog;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;
import com.liferay.osb.asah.test.util.spring.cache.OSBAsahRedisDisabledTestConfiguration;
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

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Leslie Wong
 */
@ContextConfiguration(
	classes = {
		OSBAsahSpringBootApplication.class,
		OSBAsahRedisDisabledTestConfiguration.class
	}
)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class FaroInfoDataSourceHttpTest extends BaseFaroInfoDogTestCase {

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		_mock();

		_dxpRawElasticsearchInvoker = _elasticsearchInvokerFactory.forDXPRaw();
		_salesforceRawElasticsearchInvoker =
			_elasticsearchInvokerFactory.forSalesforceRaw();
	}

	@Test
	public void testAddDataSource() throws Exception {
		_faroInfoDataSourceDog.addDataSource(
			FaroInfoTestUtil.buildLiferayDataSourceJSONObject());
	}

	@Test
	public void testAddDataSourceUpdatesExistingIndividual() throws Exception {
		JSONObject dataSourceJSONObject1 = _faroInfoDataSourceDog.addDataSource(
			FaroInfoTestUtil.buildLiferayDataSourceJSONObject());
		JSONObject dataSourceJSONObject2 = _faroInfoDataSourceDog.addDataSource(
			FaroInfoTestUtil.buildLiferayDataSourceJSONObject());

		String dataSourceId1 = dataSourceJSONObject1.getString("id");
		String dataSourceId2 = dataSourceJSONObject2.getString("id");

		elasticsearchInvoker.add(
			"field-mappings",
			FaroInfoTestUtil.buildIndividualFieldMappingJSONObject(
				JSONUtil.put(
					dataSourceId1, "email"
				).put(
					dataSourceId2, "emailAddress"
				),
				"email", "http://schema.org/email"));

		elasticsearchInvoker.add(
			"field-mappings",
			FaroInfoTestUtil.buildIndividualFieldMappingJSONObject(
				dataSourceId2, "givenName", "givenName", "Text"));

		JSONObject individualJSONObject = _faroInfoIndividualDog.addIndividual(
			FaroInfoTestUtil.buildIndividualJSONObject(dataSourceJSONObject1),
			false);

		elasticsearchInvoker.add(
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
			dataSourceJSONObject2, individualJSONObject);

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
	public void testAddDataSourceWithDuplicateName() {
		for (int i = 0; i < 4; i++) {
			_faroInfoDataSourceDog.addDataSource(
				FaroInfoTestUtil.buildLiferayDataSourceJSONObject(
					"Liferay", RandomTestUtil.randomURL()));
		}

		Assert.assertEquals(
			4L,
			elasticsearchInvoker.count(
				"data-sources", QueryBuilders.matchAllQuery()));
		Assert.assertTrue(
			elasticsearchInvoker.exists(
				"data-sources", QueryBuilders.termQuery("name", "Liferay")));
		Assert.assertTrue(
			elasticsearchInvoker.exists(
				"data-sources",
				QueryBuilders.termQuery("name", "Liferay (1)")));
		Assert.assertTrue(
			elasticsearchInvoker.exists(
				"data-sources",
				QueryBuilders.termQuery("name", "Liferay (2)")));
		Assert.assertTrue(
			elasticsearchInvoker.exists(
				"data-sources",
				QueryBuilders.termQuery("name", "Liferay (3)")));
	}

	@Test
	public void testDeleteCSVDataSource() throws Exception {
		JSONObject dataSourceJSONObject1 = _faroInfoDataSourceDog.addDataSource(
			FaroInfoTestUtil.buildCSVDataSourceJSONObject());
		JSONObject dataSourceJSONObject2 = _faroInfoDataSourceDog.addDataSource(
			FaroInfoTestUtil.buildCSVDataSourceJSONObject());

		String dataSourceId1 = dataSourceJSONObject1.getString("id");
		String dataSourceId2 = dataSourceJSONObject2.getString("id");

		elasticsearchInvoker.add(
			"csv-individuals",
			JSONUtil.putAll(
				FaroInfoTestUtil.buildCSVIndividualJSONObject(
					dataSourceId1, RandomTestUtil.randomUUID(),
					new HashMap<>()),
				FaroInfoTestUtil.buildCSVIndividualJSONObject(
					dataSourceId2, RandomTestUtil.randomUUID(),
					new HashMap<>())));

		dataSourceJSONObject1.put("deletionDate", DateUtil.newDayDateString());

		_faroInfoDataSourceDog.deleteDataSource(
			dataSourceJSONObject1, null, null);

		Assert.assertFalse(
			"Found entry in csv-individuals collection with data source ID " +
				dataSourceId1,
			elasticsearchInvoker.exists(
				"csv-individuals",
				QueryBuilders.termQuery("dataSourceId", dataSourceId1)));
		Assert.assertTrue(
			"Unable to find entry in csv-individuals collection with data " +
				"source ID " + dataSourceId2,
			elasticsearchInvoker.exists(
				"csv-individuals",
				QueryBuilders.termQuery("dataSourceId", dataSourceId2)));
	}

	@Test
	public void testDeleteDataSourceDeletesEmptyFieldMappings()
		throws Exception {

		JSONObject dataSourceJSONObject = _faroInfoDataSourceDog.addDataSource(
			FaroInfoTestUtil.buildLiferayDataSourceJSONObject());

		JSONObject fieldMappingJSONObject = elasticsearchInvoker.add(
			"field-mappings",
			FaroInfoTestUtil.buildIndividualFieldMappingJSONObject(
				dataSourceJSONObject.getString("id"), "givenName", "givenName",
				"Text"));

		dataSourceJSONObject.put("deletionDate", DateUtil.newDayDateString());

		_faroInfoDataSourceDog.deleteDataSource(
			dataSourceJSONObject, null, null);

		Assert.assertFalse(
			"Field mapping should have been deleted on data source deletion",
			elasticsearchInvoker.exists(
				"field-mappings", fieldMappingJSONObject.getString("id")));
	}

	@Test
	public void testDeleteDataSourceDeletesIndividual() throws Exception {
		JSONObject dataSourceJSONObject = _faroInfoDataSourceDog.addDataSource(
			FaroInfoTestUtil.buildLiferayDataSourceJSONObject());

		JSONObject individualJSONObject = _faroInfoIndividualDog.addIndividual(
			FaroInfoTestUtil.buildIndividualJSONObject(dataSourceJSONObject),
			false);

		dataSourceJSONObject.put("deletionDate", DateUtil.newDayDateString());

		_faroInfoDataSourceDog.deleteDataSource(
			dataSourceJSONObject, null, null);

		Assert.assertFalse(
			"Individual was not deleted on data source deletion despite only " +
				"containing fields from the deleted data source",
			elasticsearchInvoker.exists(
				"individuals", individualJSONObject.getString("id")));
	}

	@Test
	public void testDeleteDataSourceDeletesReferenceInFieldMapping()
		throws Exception {

		JSONObject dataSourceJSONObject1 = _faroInfoDataSourceDog.addDataSource(
			FaroInfoTestUtil.buildLiferayDataSourceJSONObject());
		JSONObject dataSourceJSONObject2 = _faroInfoDataSourceDog.addDataSource(
			FaroInfoTestUtil.buildLiferayDataSourceJSONObject());

		String dataSourceId1 = dataSourceJSONObject1.getString("id");
		String dataSourceId2 = dataSourceJSONObject2.getString("id");

		elasticsearchInvoker.add(
			"field-mappings",
			FaroInfoTestUtil.buildIndividualFieldMappingJSONObject(
				JSONUtil.put(
					dataSourceId1, "givenName"
				).put(
					dataSourceId2, "givenName"
				),
				"givenName", "Text"));

		dataSourceJSONObject1.put("deletionDate", DateUtil.newDayDateString());

		_faroInfoDataSourceDog.deleteDataSource(
			dataSourceJSONObject1, null, null);

		Assert.assertFalse(
			"Field mapping reference to deleted data source was not removed",
			elasticsearchInvoker.exists(
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
			elasticsearchInvoker.exists(
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

		JSONObject dataSourceJSONObject = _faroInfoDataSourceDog.addDataSource(
			FaroInfoTestUtil.buildLiferayDataSourceJSONObject());

		elasticsearchInvoker.add(
			"field-mappings",
			FaroInfoTestUtil.buildIndividualFieldMappingJSONObject(
				dataSourceJSONObject.getString("id"), "givenName", "givenName",
				"Text"));

		JSONObject individualSegmentJSONObject =
			_faroInfoIndividualSegmentDog.addIndividualSegment(
				FaroInfoTestUtil.buildDynamicIndividualSegmentJSONObject(
					"(((demographics/givenName/value ne null)))"));

		dataSourceJSONObject.put("deletionDate", DateUtil.newDayDateString());

		_faroInfoDataSourceDog.deleteDataSource(
			dataSourceJSONObject, null, null);

		individualSegmentJSONObject = elasticsearchInvoker.get(
			"individual-segments", individualSegmentJSONObject.getString("id"));

		Assert.assertEquals(
			"Individual dynamic segment with properties was not disabled " +
				"when data source was deleted",
			"DISABLED", individualSegmentJSONObject.getString("state"));
	}

	@Test
	public void testDeleteDataSourceDisablesIndividualDynamicSegment2()
		throws Exception {

		JSONObject dataSourceJSONObject = _faroInfoDataSourceDog.addDataSource(
			FaroInfoTestUtil.buildLiferayDataSourceJSONObject());

		JSONObject assetJSONObject = elasticsearchInvoker.add(
			"assets",
			FaroInfoTestUtil.buildPageAssetJSONObject(
				dataSourceJSONObject.getString("id")));

		JSONObject individualSegmentJSONObject =
			_faroInfoIndividualSegmentDog.addIndividualSegment(
				FaroInfoTestUtil.buildDynamicIndividualSegmentJSONObject(
					"activities/ever eq 'page#pageViewed#" +
						assetJSONObject.getString("id") + "'"));

		dataSourceJSONObject.put("deletionDate", DateUtil.newDayDateString());

		_faroInfoDataSourceDog.deleteDataSource(
			dataSourceJSONObject, null, null);

		individualSegmentJSONObject = elasticsearchInvoker.get(
			"individual-segments", individualSegmentJSONObject.getString("id"));

		Assert.assertEquals(
			"Individual dynamic segment with assets was not disabled when " +
				"data source was deleted",
			"DISABLED", individualSegmentJSONObject.getString("state"));
	}

	@Test
	public void testDeleteDataSourceDoesNotDeleteDefaultFieldMappings()
		throws Exception {

		JSONObject dataSourceJSONObject = _faroInfoDataSourceDog.addDataSource(
			FaroInfoTestUtil.buildLiferayDataSourceJSONObject());

		JSONObject fieldMappingJSONObject = elasticsearchInvoker.add(
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

		_faroInfoDataSourceDog.deleteDataSource(
			dataSourceJSONObject, null, null);

		Assert.assertTrue(
			"Field mappings created by the default user should not be " +
				"deleted on data source deletion",
			elasticsearchInvoker.exists(
				"field-mappings", fieldMappingJSONObject.getString("id")));
	}

	@Test
	public void testDeleteDataSourceUpdatesIndividualFields() throws Exception {
		JSONObject dataSourceJSONObject1 = _faroInfoDataSourceDog.addDataSource(
			FaroInfoTestUtil.buildLiferayDataSourceJSONObject());
		JSONObject dataSourceJSONObject2 = _faroInfoDataSourceDog.addDataSource(
			FaroInfoTestUtil.buildLiferayDataSourceJSONObject());

		String dataSourceId1 = dataSourceJSONObject1.getString("id");
		String dataSourceId2 = dataSourceJSONObject2.getString("id");

		elasticsearchInvoker.add(
			"field-mappings",
			FaroInfoTestUtil.buildIndividualFieldMappingJSONObject(
				JSONUtil.put(
					dataSourceId1, "email"
				).put(
					dataSourceId2, "emailAddress"
				),
				"email", "http://schema.org/email"));

		elasticsearchInvoker.add(
			"field-mappings",
			FaroInfoTestUtil.buildIndividualFieldMappingJSONObject(
				dataSourceId2, "givenName", "givenName", "Text"));

		JSONObject individualJSONObject = _faroInfoIndividualDog.addIndividual(
			FaroInfoTestUtil.buildIndividualJSONObject(dataSourceJSONObject1),
			false);

		elasticsearchInvoker.add(
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
			dataSourceJSONObject2, individualJSONObject);

		String lastEnrichmentDate = individualJSONObject.getString(
			"lastEnrichmentDate");

		dataSourceJSONObject2.put("deletionDate", DateUtil.newDayDateString());

		_faroInfoDataSourceDog.deleteDataSource(
			dataSourceJSONObject2, null, null);

		Assert.assertTrue(
			"Individual was deleted even though another data source with " +
				"data on the individual exists",
			elasticsearchInvoker.exists(
				"individuals", individualJSONObject.getString("id")));

		individualJSONObject = elasticsearchInvoker.get(
			"individuals", individualJSONObject.getString("id"));

		Assert.assertFalse(
			"Data source individual PK was not deleted on data source deletion",
			elasticsearchInvoker.exists(
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
			dataSourceId1, emailJSONObject.getString("dataSourceId"));

		Assert.assertEquals(
			"Data source deletion should not count towards individual " +
				"enrichment",
			lastEnrichmentDate,
			individualJSONObject.getString("lastEnrichmentDate"));
	}

	@Test
	public void testDeleteLiferayDataSource() throws Exception {
		JSONObject dataSourceJSONObject1 = _faroInfoDataSourceDog.addDataSource(
			FaroInfoTestUtil.buildLiferayDataSourceJSONObject());

		_addActivityAndUserToLiferayDataSource(dataSourceJSONObject1);

		JSONObject dataSourceJSONObject2 = _faroInfoDataSourceDog.addDataSource(
			FaroInfoTestUtil.buildLiferayDataSourceJSONObject());

		_addActivityAndUserToLiferayDataSource(dataSourceJSONObject2);

		dataSourceJSONObject1.put(
			"deletionDate", DateUtil.addDays(DateUtil.newDayDateString(), -1));

		_faroInfoDataSourceDog.deleteDataSource(
			dataSourceJSONObject1, null, null);

		String dataSourceId1 = dataSourceJSONObject1.getString("id");
		String dataSourceId2 = dataSourceJSONObject2.getString("id");

		for (String index :
				new String[] {"activities", "activity-groups", "assets"}) {

			Assert.assertTrue(
				"Unable to find entry in " + index + " collection with data " +
					"source ID " + dataSourceId1,
				elasticsearchInvoker.exists(
					index,
					QueryBuilders.termQuery("dataSourceId", dataSourceId1)));
			Assert.assertTrue(
				"Unable to find entry in " + index + " collection with data " +
					"source ID " + dataSourceId2,
				elasticsearchInvoker.exists(
					index,
					QueryBuilders.termQuery("dataSourceId", dataSourceId2)));
		}

		Assert.assertFalse(
			"Individuals from deleted data source still exist",
			elasticsearchInvoker.exists(
				"individuals",
				QueryBuilders.nestedQuery(
					"dataSourceIndividualPKs",
					QueryBuilders.termQuery(
						"dataSourceIndividualPKs.dataSourceId", dataSourceId1),
					ScoreMode.None)));
		Assert.assertTrue(
			"Individuals from data source " + dataSourceId2 + " were deleted " +
				"when deleting data source " + dataSourceId1,
			elasticsearchInvoker.exists(
				"individuals",
				QueryBuilders.nestedQuery(
					"dataSourceIndividualPKs",
					QueryBuilders.termQuery(
						"dataSourceIndividualPKs.dataSourceId", dataSourceId2),
					ScoreMode.None)));
	}

	@Test
	public void testDeleteSalesforceDataSource() throws Exception {
		JSONObject dataSourceJSONObject1 = _faroInfoDataSourceDog.addDataSource(
			FaroInfoTestUtil.buildSalesforceDataSourceJSONObject());

		String dataSourceId1 = dataSourceJSONObject1.getString("id");

		_addDataToSalesforceDataSource(
			dataSourceId1, dataSourceJSONObject1.getString("name"));

		JSONObject dataSourceJSONObject2 = _faroInfoDataSourceDog.addDataSource(
			FaroInfoTestUtil.buildSalesforceDataSourceJSONObject());

		String dataSourceId2 = dataSourceJSONObject2.getString("id");

		_addDataToSalesforceDataSource(
			dataSourceId2, dataSourceJSONObject2.getString("name"));

		dataSourceJSONObject1.put("deletionDate", DateUtil.newDayDateString());

		_faroInfoDataSourceDog.deleteDataSource(
			dataSourceJSONObject1, null, null);

		Assert.assertFalse(
			"Found entry in accounts collection with data source ID " +
				dataSourceId1,
			elasticsearchInvoker.exists(
				"accounts",
				QueryBuilders.termQuery("dataSourceId", dataSourceId1)));
		Assert.assertTrue(
			"Unable to find entry in accounts collection with data source ID " +
				dataSourceId2,
			elasticsearchInvoker.exists(
				"accounts",
				QueryBuilders.termQuery("dataSourceId", dataSourceId2)));
		Assert.assertFalse(
			"Found entry in fields collection with data source ID " +
				dataSourceId1,
			elasticsearchInvoker.exists(
				"fields",
				QueryBuilders.termQuery("dataSourceId", dataSourceId1)));
		Assert.assertTrue(
			"Unable to find entry in fields collection with data source ID " +
				dataSourceId2,
			elasticsearchInvoker.exists(
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
		JSONObject dataSourceJSONObject = _faroInfoDataSourceDog.addDataSource(
			FaroInfoTestUtil.buildLiferayDataSourceJSONObject(
				"foo", RandomTestUtil.randomURL()));

		dataSourceJSONObject = _faroInfoDataSourceDog.updateDataSource(
			dataSourceJSONObject.getString("id"),
			FaroInfoTestUtil.buildLiferayDataSourceJSONObject(
				"bar", RandomTestUtil.randomURL()));

		Assert.assertEquals("bar", dataSourceJSONObject.getString("name"));
	}

	@Test(expected = Exception.class)
	public void testUpdateDataSourceModifyingToDuplicateDataSourceName()
		throws Exception {

		_faroInfoDataSourceDog.addDataSource(
			FaroInfoTestUtil.buildLiferayDataSourceJSONObject(
				"bar", RandomTestUtil.randomURL()));

		JSONObject dataSourceJSONObject = _faroInfoDataSourceDog.addDataSource(
			FaroInfoTestUtil.buildLiferayDataSourceJSONObject(
				"foo", RandomTestUtil.randomURL()));

		try {
			_faroInfoDataSourceDog.updateDataSource(
				dataSourceJSONObject.getString("id"),
				FaroInfoTestUtil.buildLiferayDataSourceJSONObject(
					"bar", RandomTestUtil.randomURL()));
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

		JSONObject dataSourceJSONObject = _faroInfoDataSourceDog.addDataSource(
			FaroInfoTestUtil.buildLiferayDataSourceJSONObject(
				dataSourceName, RandomTestUtil.randomURL()));

		String updatedURL = "https://foo.bar";

		dataSourceJSONObject = _faroInfoDataSourceDog.updateDataSource(
			dataSourceJSONObject.getString("id"),
			FaroInfoTestUtil.buildLiferayDataSourceJSONObject(
				dataSourceName, updatedURL));

		Assert.assertEquals(updatedURL, dataSourceJSONObject.getString("url"));
	}

	private void _addActivityAndUserToLiferayDataSource(
			JSONObject dataSourceJSONObject)
		throws Exception {

		String dataSourceId = dataSourceJSONObject.getString("id");

		_faroInfoActivityDog.addActivity(
			FaroInfoTestUtil.buildActivityJSONObject(
				elasticsearchInvoker.add(
					"activity-groups",
					FaroInfoTestUtil.buildActivityGroupJSONObject(
						dataSourceId,
						_faroInfoIndividualDog.addIndividual(
							FaroInfoTestUtil.buildIndividualJSONObject(
								dataSourceJSONObject),
							false))),
				elasticsearchInvoker.add(
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

		elasticsearchInvoker.add(
			"accounts",
			JSONUtil.put(
				"accountPK", RandomTestUtil.randomId()
			).put(
				"dataSourceId", dataSourceId
			));

		elasticsearchInvoker.add(
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
				Mockito.any(JSONObject.class))
		).thenReturn(
			"CREDENTIALS_VALID"
		);
	}

	@MockBean
	private ChannelHttp _channelHttp;

	@MockBean
	private DXPExtractorConfigurationDog _dxpExtractorConfigurationDog;

	private ElasticsearchInvoker _dxpRawElasticsearchInvoker;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

	@Autowired
	private FaroInfoActivityDog _faroInfoActivityDog;

	@Autowired
	@InjectMocks
	private FaroInfoDataSourceDog _faroInfoDataSourceDog;

	@Autowired
	private FaroInfoIndividualDog _faroInfoIndividualDog;

	@Autowired
	private FaroInfoIndividualSegmentDog _faroInfoIndividualSegmentDog;

	@Mock
	private SalesforceExtractorConfigurationDog
		_salesforceExtractorConfigurationDog;

	private ElasticsearchInvoker _salesforceRawElasticsearchInvoker;

}