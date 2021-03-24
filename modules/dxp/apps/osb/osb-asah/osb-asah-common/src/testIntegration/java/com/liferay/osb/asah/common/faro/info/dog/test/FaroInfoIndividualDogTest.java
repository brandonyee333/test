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

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoIndividualDog;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.DXPEntityType;
import com.liferay.osb.asah.common.model.DataSource;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import org.hamcrest.Matchers;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Rachael Koestartyo
 */
@ContextConfiguration(classes = OSBAsahSpringBootApplication.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class FaroInfoIndividualDogTest extends BaseFaroInfoDogTestCase {

	@Before
	public void setUp() throws Exception {
		_liferayDataSourceJSONObject =
			FaroInfoTestUtil.buildLiferayDataSourceJSONObject();

		_liferayDataSourceJSONObject.put("id", RandomTestUtil.randomId());

		_salesforceDataSource = FaroInfoTestUtil.buildSalesforceDataSource();

		for (String fieldName : _FIELD_NAMES) {
			faroInfoElasticsearchInvoker.add(
				"field-mappings",
				FaroInfoTestUtil.buildIndividualFieldMappingJSONObject(
					_liferayDataSourceJSONObject.getString("id"), fieldName,
					fieldName, "Text"));
			faroInfoElasticsearchInvoker.add(
				"field-mappings",
				FaroInfoTestUtil.buildIndividualFieldMappingJSONObject(
					String.valueOf(_salesforceDataSource.getId()), fieldName,
					fieldName, "Text"));
		}
	}

	@Test
	public void testAddAndUpdateLiferayIndividual() throws Exception {
		String userId = RandomTestUtil.randomId();

		JSONObject individualJSONObject = _faroInfoIndividualDog.addIndividual(
			"3",
			JSONUtil.put(
				"contact",
				JSONUtil.put(
					"email", "john.doe@liferay.com"
				).put(
					"firstName", "John"
				).put(
					"lastName", "Doe"
				).put(
					"middleName", ""
				).put(
					"modifiedDate", System.currentTimeMillis()
				).put(
					"userId", userId
				)
			).put(
				"email", "john.doe@liferay.com"
			).put(
				"id", RandomTestUtil.randomId()
			).put(
				"middleName", "James"
			).put(
				"modifiedDate", System.currentTimeMillis()
			).put(
				"osbAsahDataSourceId",
				_liferayDataSourceJSONObject.getString("id")
			).put(
				"userId", userId
			).put(
				"uuid", RandomTestUtil.randomUUID()
			),
			_objectMapper.convertValue(
				_liferayDataSourceJSONObject, DataSource.class));

		_assertIndividualMiddleName("James", individualJSONObject);

		individualJSONObject = _faroInfoIndividualDog.updateIndividual(
			individualJSONObject.getString("id"),
			JSONUtil.put("contact", JSONUtil.put("middleName", "Joseph")),
			false);

		_assertIndividualMiddleName("James", individualJSONObject);
	}

	@Test
	public void testAddAndUpdateLiferayIndividualCustomFields()
		throws Exception {

		faroInfoElasticsearchInvoker.add(
			"field-mappings",
			FaroInfoTestUtil.buildIndividualFieldMappingJSONObject(
				_liferayDataSourceJSONObject.getString("id"), "address",
				"address", "Text"
			).put(
				"context", "custom"
			).put(
				"displayType", "text-box"
			));
		faroInfoElasticsearchInvoker.add(
			"field-mappings",
			FaroInfoTestUtil.buildIndividualFieldMappingJSONObject(
				_liferayDataSourceJSONObject.getString("id"), "spokenLanguages",
				"spokenLanguages", "Text"
			).put(
				"context", "custom"
			).put(
				"displayType", "checkbox"
			));
		faroInfoElasticsearchInvoker.add(
			"field-mappings",
			FaroInfoTestUtil.buildIndividualFieldMappingJSONObject(
				_liferayDataSourceJSONObject.getString("id"), "favoriteNumber",
				"favoriteNumber", "Number"
			).put(
				"context", "custom"
			).put(
				"displayType", "input-field"
			));

		JSONObject userJSONObject = JSONUtil.put(
			"contact",
			JSONUtil.put(
				"email", "john.doe@liferay.com"
			).put(
				"userId", 12345
			)
		).put(
			"email", "john.doe@liferay.com"
		).put(
			"expando",
			JSONUtil.put(
				"address", "1400 Montefino Ave"
			).put(
				"favoriteNumber", 42
			).put(
				"spokenLanguages",
				JSONUtil.putAll("chinese", "english", "spanish")
			)
		).put(
			"modifiedDate", System.currentTimeMillis()
		).put(
			"osbAsahDataSourceId", _liferayDataSourceJSONObject.getString("id")
		).put(
			"userId", 12345
		);

		JSONObject individualJSONObject = _faroInfoIndividualDog.addIndividual(
			"1", userJSONObject,
			_objectMapper.convertValue(
				_liferayDataSourceJSONObject, DataSource.class));

		JSONObject customJSONObject = individualJSONObject.getJSONObject(
			"custom");

		Assert.assertEquals(
			"1400 Montefino Ave",
			JSONUtil.getValue(
				customJSONObject, "JSONArray/address", "Object/0",
				"Object/value"));
		Assert.assertEquals(
			42,
			JSONUtil.getValue(
				customJSONObject, "JSONArray/favoriteNumber", "Object/0",
				"Object/value"));
		Assert.assertThat(
			new String[] {"chinese", "english", "spanish"},
			Matchers.arrayContainingInAnyOrder(
				JSONUtil.toStringArray(
					customJSONObject.getJSONArray("spokenLanguages"),
					"value")));

		_assertFields(
			1, new String[] {"1400 Montefino Ave"}, "address",
			individualJSONObject.getString("id"));
		_assertFields(
			1, new String[] {"42"}, "favoriteNumber",
			individualJSONObject.getString("id"));
		_assertFields(
			3, new String[] {"chinese", "english", "spanish"},
			"spokenLanguages", individualJSONObject.getString("id"));

		individualJSONObject = _faroInfoIndividualDog.updateIndividual(
			"1",
			userJSONObject.put(
				"expando",
				JSONUtil.put(
					"favoriteNumber", 8
				).put(
					"spokenLanguages", JSONUtil.put("german")
				)),
			_objectMapper.convertValue(
				_liferayDataSourceJSONObject, DataSource.class),
			individualJSONObject);

		customJSONObject = individualJSONObject.getJSONObject("custom");

		Assert.assertNull(customJSONObject.opt("address"));
		Assert.assertEquals(
			8,
			JSONUtil.getValue(
				customJSONObject, "JSONArray/favoriteNumber", "Object/0",
				"Object/value"));
		Assert.assertThat(
			new String[] {"german"},
			Matchers.arrayContainingInAnyOrder(
				JSONUtil.toStringArray(
					customJSONObject.getJSONArray("spokenLanguages"),
					"value")));

		_assertFields(
			0, new String[0], "address", individualJSONObject.getString("id"));
		_assertFields(
			1, new String[] {"8"}, "favoriteNumber",
			individualJSONObject.getString("id"));
		_assertFields(
			1, new String[] {"german"}, "spokenLanguages",
			individualJSONObject.getString("id"));
	}

	@Test
	public void testAddAndUpdateSalesforceIndividual() throws Exception {
		String dataSourceId = String.valueOf(_salesforceDataSource.getId());

		JSONObject individualJSONObject = _faroInfoIndividualDog.addIndividual(
			"1",
			JSONUtil.put(
				"accountPKs", JSONUtil.put("123")
			).put(
				"country", "United States"
			).put(
				"email", "dummy.test@test.com"
			).put(
				"modifiedDate", DateUtil.newDateString()
			),
			_salesforceDataSource);

		JSONAssert.assertEquals(
			JSONUtil.put(
				JSONUtil.put(
					"accountPKs", JSONUtil.put("123")
				).put(
					"dataSourceId", dataSourceId
				)),
			individualJSONObject.getJSONArray("dataSourceAccountPKs"), false);

		individualJSONObject = _faroInfoIndividualDog.updateIndividual(
			individualJSONObject.getString("id"),
			JSONUtil.put(
				"accountPKs", JSONUtil.putAll("123", "456", "789")
			).put(
				"country", "United States"
			).put(
				"email", "dummy.test@test.com"
			).put(
				"modifiedDate", DateUtil.newDateString()
			),
			_salesforceDataSource, individualJSONObject);

		JSONAssert.assertEquals(
			JSONUtil.put(
				JSONUtil.put(
					"accountPKs", JSONUtil.putAll("123", "456", "789")
				).put(
					"dataSourceId", dataSourceId
				)),
			individualJSONObject.getJSONArray("dataSourceAccountPKs"), false);

		_salesforceDataSource.setId(1L);

		individualJSONObject = _faroInfoIndividualDog.updateIndividual(
			individualJSONObject.getString("id"),
			JSONUtil.put(
				"accountPKs", JSONUtil.put("321")
			).put(
				"country", "United States"
			).put(
				"email", "dummy.test@test.com"
			).put(
				"modifiedDate", DateUtil.newDateString()
			),
			_salesforceDataSource, individualJSONObject);

		JSONAssert.assertEquals(
			JSONUtil.putAll(
				JSONUtil.put(
					"accountPKs", JSONUtil.putAll("123", "456", "789")
				).put(
					"dataSourceId", dataSourceId
				),
				JSONUtil.put(
					"accountPKs", JSONUtil.put("321")
				).put(
					"dataSourceId", "1"
				)),
			individualJSONObject.getJSONArray("dataSourceAccountPKs"), false);
	}

	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals_associations.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "organizations", resourcePath = "organizations.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "users", resourcePath = "users.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_DXP_RAW
	)
	@Test
	public void testAddIndividualAssociation() {
		JSONObject individualJSONObject =
			_faroInfoIndividualDog.addIndividualAssociation(
				33134, 402139209179557944L,
				DXPEntityType.of(DXPEntityType.CLASS_NAME_ORGANIZATION),
				faroInfoElasticsearchInvoker.fetch(
					"individuals",
					QueryBuilders.termQuery(
						"demographics.email.value", "test1@liferay.com")));

		Assert.assertThat(
			new String[] {"402139267512234420", "402139268847589064"},
			Matchers.arrayContainingInAnyOrder(
				JSONUtil.toStringArray(
					individualJSONObject.getJSONArray("organizationIds"))));
	}

	@Test
	public void testBuildIndividualsQueryBuilder() throws Exception {
		QueryBuilder queryBuilder =
			_faroInfoIndividualDog.buildIndividualsQueryBuilder(
				null, "(((demographics/age/value gt '50')))", false);

		JSONAssert.assertEquals(
			JSONUtil.put(
				"bool",
				JSONUtil.put(
					"adjust_pure_negative", true
				).put(
					"boost", 1.0
				).put(
					"filter",
					JSONUtil.putAll(
						JSONUtil.put(
							"exists",
							JSONUtil.put(
								"boost", 1.0
							).put(
								"field", "demographics.email"
							)),
						JSONUtil.put(
							"range",
							JSONUtil.put(
								"demographics.age.value",
								JSONUtil.put(
									"boost", 1.0
								).put(
									"from", "50"
								).put(
									"include_lower", false
								).put(
									"include_upper", true
								))))
				)),
			new JSONObject(queryBuilder.toString()), false);

		queryBuilder = _faroInfoIndividualDog.buildIndividualsQueryBuilder(
			null, "(((demographics/age/value gt '50')))", true);

		JSONAssert.assertEquals(
			JSONUtil.put(
				"bool",
				JSONUtil.put(
					"filter",
					JSONUtil.put(
						JSONUtil.put(
							"range",
							JSONUtil.put(
								"demographics.age.value",
								JSONUtil.put(
									"boost", 1.0
								).put(
									"from", "50"
								).put(
									"include_lower", false
								).put(
									"include_upper", true
								)))))),
			new JSONObject(queryBuilder.toString()), false);

		queryBuilder = _faroInfoIndividualDog.buildIndividualsQueryBuilder(
			"1234", "(((demographics/age/value gt '50')))", true);

		JSONAssert.assertEquals(
			JSONUtil.put(
				"bool",
				JSONUtil.put(
					"filter",
					JSONUtil.putAll(
						JSONUtil.put(
							"range",
							JSONUtil.put(
								"demographics.age.value",
								JSONUtil.put(
									"boost", 1.0
								).put(
									"from", "50"
								).put(
									"include_lower", false
								).put(
									"include_upper", true
								))),
						JSONUtil.put(
							"term",
							JSONUtil.put(
								"channelIds",
								JSONUtil.put(
									"boost", 1.0
								).put(
									"value", "1234"
								)))))),
			new JSONObject(queryBuilder.toString()), false);
	}

	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals_associations.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "organizations", resourcePath = "organizations.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "users", resourcePath = "users.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_DXP_RAW
	)
	@Test
	public void testDeleteIndividualAssociation() {
		JSONObject individualJSONObject =
			_faroInfoIndividualDog.deleteIndividualAssociation(
				33134, 402139209179557944L,
				DXPEntityType.of(DXPEntityType.CLASS_NAME_ORGANIZATION),
				faroInfoElasticsearchInvoker.fetch(
					"individuals",
					QueryBuilders.termQuery(
						"demographics.email.value", "test1@liferay.com")));

		Assert.assertThat(
			new String[] {"402139267512234420"},
			Matchers.arrayContainingInAnyOrder(
				JSONUtil.toStringArray(
					individualJSONObject.getJSONArray("organizationIds"))));
	}

	@Test
	public void testNoDuplicateIndividualPKs() throws Exception {
		String userId = RandomTestUtil.randomId();
		String uuid1 = RandomTestUtil.randomUUID();

		JSONObject dataJSONObject = JSONUtil.put(
			"contact",
			JSONUtil.put(
				"email", "dummy.test@test.com"
			).put(
				"userId", userId
			)
		).put(
			"email", "dummy.test@test.com"
		).put(
			"id", RandomTestUtil.randomId()
		).put(
			"modifiedDate", System.currentTimeMillis()
		).put(
			"osbAsahDataSourceId", _liferayDataSourceJSONObject.getString("id")
		).put(
			"userId", userId
		).put(
			"uuid", uuid1
		);

		JSONObject individualJSONObject = _faroInfoIndividualDog.addIndividual(
			uuid1, dataJSONObject,
			_objectMapper.convertValue(
				_liferayDataSourceJSONObject, DataSource.class));

		_assertDataSourceIndividualPKs(
			JSONUtil.put(uuid1), individualJSONObject);

		String uuid2 = RandomTestUtil.randomUUID();

		individualJSONObject = _faroInfoIndividualDog.updateIndividual(
			uuid2, dataJSONObject.put("uuid", uuid2),
			_objectMapper.convertValue(
				_liferayDataSourceJSONObject, DataSource.class),
			individualJSONObject);

		_assertDataSourceIndividualPKs(
			JSONUtil.putAll(new Object[] {uuid1, uuid2}), individualJSONObject);

		individualJSONObject = _faroInfoIndividualDog.updateIndividual(
			uuid2, dataJSONObject.put("test", "test"),
			_objectMapper.convertValue(
				_liferayDataSourceJSONObject, DataSource.class),
			individualJSONObject);

		_assertDataSourceIndividualPKs(
			JSONUtil.putAll(new Object[] {uuid1, uuid2}), individualJSONObject);
	}

	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testRemoveIndividualSegmentId() {
		JSONObject individualJSONObject = faroInfoElasticsearchInvoker.fetch(
			"individuals", "338486041327913341");

		JSONArray individualSegmentIdsJSONArray =
			individualJSONObject.getJSONArray("individualSegmentIds");

		Assert.assertEquals(1, individualSegmentIdsJSONArray.length());

		_faroInfoIndividualDog.removeIndividualSegmentId(
			individualJSONObject, 338511398116723458L);

		individualJSONObject = faroInfoElasticsearchInvoker.fetch(
			"individuals", "338486041327913341");

		individualSegmentIdsJSONArray = individualJSONObject.getJSONArray(
			"individualSegmentIds");

		Assert.assertEquals(0, individualSegmentIdsJSONArray.length());
	}

	@Test
	public void testUpdateDynamicAddMemberships() throws Exception {
		faroInfoElasticsearchInvoker.add(
			"individuals",
			JSONUtil.put(
				"id", "123"
			).put(
				"individualSegmentIds", new JSONArray()
			));

		_faroInfoIndividualDog.updateDynamicAddMemberships(
			true,
			faroInfoElasticsearchInvoker.add(
				"individual-segments",
				JSONUtil.put(
					"filter", ""
				).put(
					"id", "234"
				).put(
					"includeAnonymousUsers", true
				).put(
					"status", "ACTIVE"
				)),
			DateUtil.toUTCDate("2019-02-11T20:26:53.218Z"));

		JSONObject individualJSONObject = faroInfoElasticsearchInvoker.fetch(
			"individuals", "123");

		JSONAssert.assertEquals(
			JSONUtil.putAll("234"),
			individualJSONObject.getJSONArray("individualSegmentIds"), false);
	}

	@ElasticsearchIndex(
		name = "individual-segments", resourcePath = "individual_segments.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testUpdateDynamicMemberships() throws Exception {
		faroInfoElasticsearchInvoker.add(
			"memberships",
			JSONUtil.put(
				"dateCreated", "2019-02-11T20:26:53.218Z"
			).put(
				"individualId", "338486040159673751"
			).put(
				"individualSegmentId", "338511451975440187"
			).put(
				"status", "ACTIVE"
			));

		_faroInfoIndividualDog.updateDynamicMemberships(
			faroInfoElasticsearchInvoker.fetch(
				"individual-segments", "338511451975440187"),
			DateUtil.toUTCDate("2019-02-11T20:26:53.218Z"));

		JSONObject individualJSONObject = faroInfoElasticsearchInvoker.fetch(
			"individuals", "338486037253283140");

		JSONAssert.assertEquals(
			JSONUtil.putAll("338511398116723458", "338511451975440187"),
			individualJSONObject.getJSONArray("individualSegmentIds"), false);

		faroInfoElasticsearchInvoker.delete(
			"individuals", individualJSONObject);

		_faroInfoIndividualDog.updateDynamicMemberships(
			faroInfoElasticsearchInvoker.fetch(
				"individual-segments", "338511398116723458"),
			DateUtil.toUTCDate("2019-02-11T20:26:53.218Z"));

		Assert.assertFalse(
			faroInfoElasticsearchInvoker.exists(
				"memberships",
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery(
						"individualSegmentId", "338511398116723458")
				).filter(
					QueryBuilders.termQuery(
						"individualId", "338486037253283140")
				)));
	}

	@ElasticsearchIndex(
		name = "individual-segments", resourcePath = "individual_segments.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "membership-changes", resourcePath = "membership_changes.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "memberships", resourcePath = "memberships.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testUpdateDynamicRemoveMemberships() throws Exception {
		_faroInfoIndividualDog.updateDynamicRemoveMemberships(
			faroInfoElasticsearchInvoker.fetch(
				"individual-segments", "338511398116723458"),
			DateUtil.toUTCDate("2019-02-11T20:26:53.218Z"));

		JSONArray membershipJSONArray = faroInfoElasticsearchInvoker.get(
			"memberships",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery(
					"individualSegmentId", "338511398116723458")
			).filter(
				QueryBuilders.termQuery("status", "INACTIVE")
			));

		Assert.assertEquals(3, membershipJSONArray.length());
	}

	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals_associations.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "organizations", resourcePath = "organizations.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "users", resourcePath = "users.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_DXP_RAW
	)
	@Test
	public void testUpdateIndividualAssociation() throws Exception {
		JSONObject dataSourceJSONObject =
			FaroInfoTestUtil.buildLiferayDataSourceJSONObject();

		dataSourceJSONObject.put("id", "402139209179557944");

		faroInfoElasticsearchInvoker.add(
			"field-mappings",
			FaroInfoTestUtil.buildIndividualFieldMappingJSONObject(
				dataSourceJSONObject.getString("id"), "emailAddress", "email",
				"Text"));

		JSONObject individualJSONObject =
			_faroInfoIndividualDog.updateIndividual(
				"402139280465582637",
				JSONUtil.put(
					"createDate", System.currentTimeMillis()
				).put(
					"emailAddress", "test1@liferay.com"
				).put(
					"memberships",
					JSONUtil.put(
						DXPEntityType.CLASS_NAME_ORGANIZATION,
						JSONUtil.putAll(33120, 33134))
				).put(
					"osbAsahDataSourceId", dataSourceJSONObject.getString("id")
				).put(
					"userId", 36016
				),
				_objectMapper.convertValue(
					dataSourceJSONObject, DataSource.class),
				faroInfoElasticsearchInvoker.fetch(
					"individuals",
					QueryBuilders.termQuery(
						"demographics.email.value", "test1@liferay.com")));

		Assert.assertThat(
			new String[] {"402139267512234420", "402139268847589064"},
			Matchers.arrayContainingInAnyOrder(
				JSONUtil.toStringArray(
					individualJSONObject.getJSONArray("organizationIds"))));
	}

	@Test
	public void testUpdateIndividualFromDifferentDataSourceIgnoresNullValue()
		throws Exception {

		JSONObject individualJSONObject = _faroInfoIndividualDog.addIndividual(
			"2",
			JSONUtil.put(
				"accountPKs", JSONUtil.put("123")
			).put(
				"country", "United States"
			).put(
				"email", "dummy.test@test.com"
			).put(
				"modifiedDate", DateUtil.newDateString()
			),
			_salesforceDataSource);

		individualJSONObject = _faroInfoIndividualDog.updateIndividual(
			individualJSONObject.getString("id"),
			JSONUtil.put(
				"contact",
				JSONUtil.put(
					"country", ""
				).put(
					"email", "dummy.test@test.com"
				)
			).put(
				"modifiedDate", System.currentTimeMillis()
			),
			_objectMapper.convertValue(
				_liferayDataSourceJSONObject, DataSource.class),
			individualJSONObject);

		JSONObject demographicsJSONObject = individualJSONObject.getJSONObject(
			"demographics");

		JSONArray countryJSONArray = demographicsJSONObject.getJSONArray(
			"country");

		JSONObject countryJSONObject = countryJSONArray.getJSONObject(0);

		Assert.assertEquals(
			"United States", countryJSONObject.getString("value"));
	}

	@Test
	public void testUpdateIndividualUpdatesPagesAndAssets() throws Exception {
		String dayDateString = DateUtil.newDayDateString();

		JSONObject individualJSONObject = faroInfoElasticsearchInvoker.add(
			"individuals",
			JSONUtil.put(
				"activitiesCounts", new JSONArray()
			).put(
				"analyticsData", new JSONObject()
			).put(
				"channelIds", JSONUtil.put("1")
			).put(
				"dataSourceAccountPKs", new JSONArray()
			).put(
				"dataSourceIndividualPKs",
				JSONUtil.put(
					JSONUtil.put(
						"dataSourceId",
						_liferayDataSourceJSONObject.getString("id")
					).put(
						"dataSourceType", "LIFERAY"
					).put(
						"individualPKs", JSONUtil.put("12345")
					))
			).put(
				"dateCreated", dayDateString
			).put(
				"dateModified", dayDateString
			).put(
				"emailAddressHashed",
				"47ff64395860b1d498241d907069f649b98c198a95b3ba5303b87094058590"
			).put(
				"individualSegmentIds", new JSONArray()
			));

		JSONObject page1JSONObject = cerebroInfoElasticsearchInvoker.add(
			"pages",
			JSONUtil.put(
				"individualId", individualJSONObject.getString("id")
			).put(
				"knownIndividual", false
			));

		JSONObject page2JSONObject = cerebroInfoElasticsearchInvoker.add(
			"pages",
			JSONUtil.put(
				"individualId", individualJSONObject.getString("id")
			).put(
				"knownIndividual", true
			));

		_faroInfoIndividualDog.updateIndividual(
			individualJSONObject.getString("id"),
			JSONUtil.put(
				"contact",
				JSONUtil.put(
					"country", ""
				).put(
					"email", "dummy.test@test.com"
				)
			).put(
				"modifiedDate", System.currentTimeMillis()
			),
			_objectMapper.convertValue(
				_liferayDataSourceJSONObject, DataSource.class),
			individualJSONObject);

		page1JSONObject = cerebroInfoElasticsearchInvoker.fetch(
			"pages", page1JSONObject.getString("id"));

		Assert.assertTrue(page1JSONObject.getBoolean("knownIndividual"));

		page2JSONObject = cerebroInfoElasticsearchInvoker.fetch(
			"pages", page2JSONObject.getString("id"));

		Assert.assertTrue(page2JSONObject.getBoolean("knownIndividual"));
	}

	private void _assertDataSourceIndividualPKs(
		JSONArray expectedJSONArray, JSONObject individualJSONObject) {

		JSONArray dataSourceIndividualPKsJSONArray =
			individualJSONObject.getJSONArray("dataSourceIndividualPKs");

		JSONObject dataSourceIndividualPKsJSONObject =
			dataSourceIndividualPKsJSONArray.getJSONObject(0);

		JSONArray individualPKsJSONArray =
			dataSourceIndividualPKsJSONObject.getJSONArray("individualPKs");

		JSONAssert.assertEquals(
			expectedJSONArray, individualPKsJSONArray, false);
	}

	private void _assertFields(
		int expectedSize, Object[] expectedValues, String fieldName,
		String ownerId) {

		JSONArray fieldsJSONArray = faroInfoElasticsearchInvoker.get(
			"fields",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("context", "custom")
			).filter(
				QueryBuilders.termQuery(
					"dataSourceId",
					_liferayDataSourceJSONObject.getString("id"))
			).filter(
				QueryBuilders.termQuery("name", fieldName)
			).filter(
				QueryBuilders.termQuery("ownerId", ownerId)
			).filter(
				QueryBuilders.termQuery("ownerType", "individual")
			));

		Assert.assertEquals(expectedSize, fieldsJSONArray.length());
		Assert.assertThat(
			expectedValues,
			Matchers.arrayContainingInAnyOrder(
				JSONUtil.toObjectArray(fieldsJSONArray, "value")));
	}

	private void _assertIndividualMiddleName(
		String expectedMiddleName, JSONObject individualJSONObject) {

		JSONObject demographicsJSONObject = individualJSONObject.getJSONObject(
			"demographics");

		JSONArray middleNameJSONArray = demographicsJSONObject.getJSONArray(
			"middleName");

		JSONObject middleNameJSONObject = middleNameJSONArray.getJSONObject(0);

		Assert.assertEquals(
			expectedMiddleName, middleNameJSONObject.getString("value"));
	}

	private static final String[] _FIELD_NAMES = {
		"country", "email", "middleName"
	};

	@Autowired
	private FaroInfoIndividualDog _faroInfoIndividualDog;

	private JSONObject _liferayDataSourceJSONObject;

	@Autowired
	private ObjectMapper _objectMapper;

	private DataSource _salesforceDataSource;

}