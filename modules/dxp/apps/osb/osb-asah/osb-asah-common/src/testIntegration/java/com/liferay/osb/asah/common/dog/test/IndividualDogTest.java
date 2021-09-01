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

package com.liferay.osb.asah.common.dog.test;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.IndividualDog;
import com.liferay.osb.asah.common.dog.MembershipDog;
import com.liferay.osb.asah.common.dog.SegmentDog;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.entity.DXPEntity;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.entity.DataSourceIndividual;
import com.liferay.osb.asah.common.entity.Field;
import com.liferay.osb.asah.common.entity.FieldMapping;
import com.liferay.osb.asah.common.entity.Individual;
import com.liferay.osb.asah.common.entity.Membership;
import com.liferay.osb.asah.common.entity.Organization;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.faro.info.dog.test.BaseFaroInfoDogTestCase;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.DXPEntityType;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.common.repository.FieldMappingRepository;
import com.liferay.osb.asah.common.repository.FieldRepository;
import com.liferay.osb.asah.common.repository.OrganizationRepository;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.annotation.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.RandomStringUtils;

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

import org.yaml.snakeyaml.util.ArrayUtils;

/**
 * @author Rachael Koestartyo
 */
@ContextConfiguration(classes = OSBAsahSpringBootApplication.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class IndividualDogTest extends BaseFaroInfoDogTestCase {

	@Before
	public void setUp() {
		_liferayDataSource = FaroInfoTestUtil.buildLiferayDataSource();

		_liferayDataSource.setId(
			Long.parseLong(RandomStringUtils.randomNumeric(4)));

		_salesforceDataSource = FaroInfoTestUtil.buildSalesforceDataSource();

		for (String fieldName : _FIELD_NAMES) {
			_fieldMappingRepository.save(
				FaroInfoTestUtil.buildIndividualFieldMapping(
					_liferayDataSource.getId(), fieldName, fieldName, "Text"));
			_fieldMappingRepository.save(
				FaroInfoTestUtil.buildIndividualFieldMapping(
					_salesforceDataSource.getId(), fieldName, fieldName,
					"Text"));
		}
	}

	@Test
	public void testAddAndUpdateLiferayIndividual() throws Exception {
		String userId = RandomTestUtil.randomId();

		Individual individual = _individualDog.addIndividual(
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
				String.valueOf(_liferayDataSource.getId())
			).put(
				"userId", userId
			).put(
				"uuid", RandomTestUtil.randomUUID()
			),
			_liferayDataSource);

		_assertIndividualMiddleName("James", individual);

		individual = _individualDog.updateIndividual(
			"3",
			JSONUtil.put(
				"contact",
				JSONUtil.put(
					"email", "john.doe@liferay.com"
				).put(
					"middleName", "Joseph"
				)
			).put(
				"modifiedDate", System.currentTimeMillis()
			),
			_liferayDataSource, individual);

		_assertIndividualMiddleName("Joseph", individual);
	}

	@Test
	public void testAddAndUpdateLiferayIndividualCustomFields()
		throws Exception {

		FieldMapping fieldMapping =
			FaroInfoTestUtil.buildIndividualFieldMapping(
				_liferayDataSource.getId(), "address", "address", "Text");

		fieldMapping.setContext("custom");
		fieldMapping.setDisplayType("text-box");

		_fieldMappingRepository.save(fieldMapping);

		fieldMapping = FaroInfoTestUtil.buildIndividualFieldMapping(
			_liferayDataSource.getId(), "spokenLanguages", "spokenLanguages",
			"Text");

		fieldMapping.setContext("custom");
		fieldMapping.setDisplayType("checkbox");

		_fieldMappingRepository.save(fieldMapping);

		fieldMapping = FaroInfoTestUtil.buildIndividualFieldMapping(
			_liferayDataSource.getId(), "favoriteNumber", "favoriteNumber",
			"Number");

		fieldMapping.setContext("custom");
		fieldMapping.setDisplayType("input-field");

		_fieldMappingRepository.save(fieldMapping);

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
				"spokenLanguages", JSONUtil.put("english")
			)
		).put(
			"modifiedDate", System.currentTimeMillis()
		).put(
			"osbAsahDataSourceId", String.valueOf(_liferayDataSource.getId())
		).put(
			"userId", 12345
		);

		Individual individual = _individualDog.addIndividual(
			"1", userJSONObject, _liferayDataSource);

		Set<Field> customFields = individual.getCustomFields();

		_assertCustomFields(customFields, "1400 Montefino Ave", "address");
		_assertCustomFields(customFields, 42, "favoriteNumber");
		_assertCustomFields(customFields, "english", "spokenLanguages");

		individual = _individualDog.updateIndividual(
			"1",
			userJSONObject.put(
				"expando",
				JSONUtil.put(
					"favoriteNumber", 8
				).put(
					"spokenLanguages", JSONUtil.put("german")
				)),
			_liferayDataSource, individual);

		customFields = individual.getCustomFields();

		_assertCustomFields(customFields, null, "address");
		_assertCustomFields(customFields, 8, "favoriteNumber");
		_assertCustomFields(customFields, "german", "spokenLanguages");
	}

	@Test
	public void testAddAndUpdateSalesforceIndividual() throws Exception {
		Long dataSourceId = _salesforceDataSource.getId();

		Individual individual = _individualDog.addIndividual(
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

		Set<Individual.DataSourceAccountPK> dataSourceAccountPKs =
			individual.getDataSourceAccountPKs();

		Stream<Individual.DataSourceAccountPK> stream =
			dataSourceAccountPKs.stream();

		Set<String> accountPKs = new HashSet<>();

		Set<String> finalAccountPKs1 = accountPKs;

		stream.filter(
			dataSourceAccountPK -> Objects.equals(
				dataSourceId, dataSourceAccountPK.getDataSourceId())
		).forEach(
			dataSourceAccountPK -> finalAccountPKs1.addAll(
				dataSourceAccountPK.getAccountPKs())
		);

		Assert.assertThat(
			new String[] {"123"},
			Matchers.arrayContainingInAnyOrder(
				accountPKs.toArray(new String[0])));

		individual = _individualDog.updateIndividual(
			String.valueOf(individual.getId()),
			JSONUtil.put(
				"accountPKs", JSONUtil.putAll("123", "456", "789")
			).put(
				"country", "United States"
			).put(
				"email", "dummy.test@test.com"
			).put(
				"modifiedDate", DateUtil.newDateString()
			),
			_salesforceDataSource, individual);

		dataSourceAccountPKs = individual.getDataSourceAccountPKs();

		stream = dataSourceAccountPKs.stream();

		accountPKs = new HashSet<>();

		Set<String> finalAccountPKs2 = accountPKs;

		stream.filter(
			dataSourceAccountPK -> Objects.equals(
				dataSourceId, dataSourceAccountPK.getDataSourceId())
		).forEach(
			dataSourceAccountPK -> finalAccountPKs2.addAll(
				dataSourceAccountPK.getAccountPKs())
		);

		Assert.assertThat(
			new String[] {"123", "456", "789"},
			Matchers.arrayContainingInAnyOrder(
				finalAccountPKs2.toArray(new String[0])));

		_salesforceDataSource.setId(1L);

		individual = _individualDog.updateIndividual(
			String.valueOf(individual.getId()),
			JSONUtil.put(
				"accountPKs", JSONUtil.put("321")
			).put(
				"country", "United States"
			).put(
				"email", "dummy.test@test.com"
			).put(
				"modifiedDate", DateUtil.newDateString()
			),
			_salesforceDataSource, individual);

		JSONAssert.assertEquals(
			JSONUtil.putAll(
				JSONUtil.put(
					"accountPKs", JSONUtil.putAll("123", "456", "789")
				).put(
					"dataSourceId", String.valueOf(dataSourceId)
				),
				JSONUtil.put(
					"accountPKs", JSONUtil.put("321")
				).put(
					"dataSourceId", "1"
				)),
			_objectMapper.convertValue(
				individual.getDataSourceAccountPKs(), JSONArray.class),
			false);
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
		Individual individual = _individualDog.addIndividualAssociation(
			33134, 402139209179557944L,
			DXPEntity.Type.of(DXPEntityType.CLASS_NAME_ORGANIZATION),
			_individualDog.fetchIndividualByEmailAddress("test1@liferay.com"));

		Set<Long> organizationIds = individual.getOrganizationIds();

		Assert.assertThat(
			new Long[] {402139267512234420L, 402139268847589064L},
			Matchers.arrayContainingInAnyOrder(
				organizationIds.toArray(new Long[0])));
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
		Individual individual = _individualDog.deleteIndividualAssociation(
			33134, 402139209179557944L,
			DXPEntity.Type.of(DXPEntityType.CLASS_NAME_ORGANIZATION),
			_individualDog.fetchIndividualByEmailAddress("test1@liferay.com"));

		Set<Long> organizationIds = individual.getOrganizationIds();

		Assert.assertThat(
			new Long[] {402139267512234420L},
			Matchers.arrayContainingInAnyOrder(
				organizationIds.toArray(new Long[0])));
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
			"osbAsahDataSourceId", String.valueOf(_liferayDataSource.getId())
		).put(
			"userId", userId
		).put(
			"uuid", uuid1
		);

		Individual individual = _individualDog.addIndividual(
			uuid1, dataJSONObject, _liferayDataSource);

		_assertDataSourceIndividualPKs(new String[] {uuid1}, individual);

		String uuid2 = RandomTestUtil.randomUUID();

		individual = _individualDog.updateIndividual(
			uuid2, dataJSONObject.put("uuid", uuid2), _liferayDataSource,
			individual);

		_assertDataSourceIndividualPKs(new Object[] {uuid1, uuid2}, individual);

		individual = _individualDog.updateIndividual(
			uuid2, dataJSONObject.put("test", "test"), _liferayDataSource,
			individual);

		_assertDataSourceIndividualPKs(new Object[] {uuid1, uuid2}, individual);
	}

	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testRemoveIndividualSegmentId() {
		Individual individual = _individualDog.fetchIndividual(
			338486041327913341L);

		Set<Long> segmentIds = individual.getSegmentIds();

		Assert.assertEquals(segmentIds.toString(), 1, segmentIds.size());

		_individualDog.removeSegmentId(individual, 338511398116723458L);

		individual = _individualDog.fetchIndividual(338486041327913341L);

		segmentIds = individual.getSegmentIds();

		Assert.assertEquals(segmentIds.toString(), 0, segmentIds.size());
	}

	@ElasticsearchIndex(
		name = "fields", resourcePath = "fields.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testSearchIndividuals() {
		List<Individual> individuals = _individualDog.searchIndividuals(
			100L, null, false, 0, 10,
			new String[] {"demographics/givenName/value,asc"});

		Assert.assertEquals(
			ArrayUtils.toUnmodifiableList(
				new String[] {"alpha", "beta", "gamma", "omega", "theta"}),
			_getGivenNames(individuals));

		individuals = _individualDog.searchIndividuals(
			100L, null, false, 0, 10,
			new String[] {"demographics/givenName/value,desc"});

		Assert.assertEquals(
			ArrayUtils.toUnmodifiableList(
				new String[] {"theta", "omega", "gamma", "beta", "alpha"}),
			_getGivenNames(individuals));
	}

	@Test
	public void testUpdateDynamicAddMemberships() {
		Individual individual = new Individual();

		individual.setId(123L);
		individual.setSegmentIds(Collections.emptySet());

		individual = _individualDog.addIndividual(individual, false);

		Segment segment = new Segment();

		segment.setFilter("");
		segment.setId(234L);
		segment.setIncludeAnonymousUsers(Boolean.TRUE);
		segment.setStatus("ACTIVE");

		_segmentDog.addSegment(segment);

		_individualDog.updateDynamicMemberships(
			DateUtil.toUTCDate("2019-02-11T20:26:53.218Z"), segment);

		individual = _individualDog.fetchIndividual(individual.getId());

		Set<Long> segmentIds = individual.getSegmentIds();

		Assert.assertArrayEquals(
			new Long[] {234L}, segmentIds.toArray(new Long[0]));
	}

	@Test
	public void testUpdateDynamicMemberships() {
		Segment segment1 = new Segment();

		segment1.setFilter("");
		segment1.setFilterMetadata("0");
		segment1.setId(338511398116723458L);
		segment1.setModifiedDate(
			DateUtil.toUTCDate("2019-02-11T20:27:36.603Z"));
		segment1.setName("Test Segment 1");
		segment1.setScope("PROJECT");
		segment1.setType(Segment.Type.STATIC);
		segment1.setState("READY");
		segment1.setStatus("ACTIVE");

		_segmentDog.addSegment(segment1);

		Segment segment2 = new Segment();

		segment2.setFilter("");
		segment2.setFilterMetadata("0");
		segment2.setId(338511451975440187L);
		segment2.setModifiedDate(
			DateUtil.toUTCDate("2019-02-11T20:27:47.622Z"));
		segment2.setName("Test Segment 2");
		segment2.setScope("PROJECT");
		segment2.setType(Segment.Type.STATIC);
		segment2.setState("READY");
		segment2.setStatus("ACTIVE");

		_segmentDog.addSegment(segment2);

		Individual individual = new Individual();

		individual.setId(338486037253283140L);
		individual.setSegmentIds(Collections.singleton(338511398116723458L));

		_individualDog.addIndividual(individual, false);

		Field field = new Field();

		field.setContext("demographics");
		field.setDataSourceId(338486009405470846L);
		field.setDataSourceName("Test Data Source");
		field.setFieldType("Text");
		field.setModifiedDate(DateUtil.toUTCDate("2019-02-11T17:05:06.814Z"));
		field.setName("email");
		field.setOwnerId(338486037253283140L);
		field.setOwnerType("individual");
		field.setSourceName("email");
		field.setValue("test1@liferay.com");

		_fieldRepository.save(field);

		individual.setFields(Collections.singleton(field));

		_individualDog.updateIndividual(individual);

		Membership membership = new Membership();

		membership.setCreateDate(
			DateUtil.toUTCDate("2019-02-11T20:26:53.218Z"));
		membership.setIndividualId(338486037253283140L);
		membership.setIndividualSegmentId(338511451975440187L);
		membership.setStatus("ACTIVE");

		_membershipDog.addMembership(membership);

		_individualDog.updateDynamicMemberships(
			DateUtil.toUTCDate("2019-02-11T20:26:53.218Z"),
			_segmentDog.getSegment(338511451975440187L));

		individual = _individualDog.fetchIndividual(338486037253283140L);

		Set<Long> segmentIds = individual.getSegmentIds();

		Assert.assertThat(
			new Long[] {338511398116723458L, 338511451975440187L},
			Matchers.arrayContainingInAnyOrder(
				segmentIds.toArray(new Long[0])));

		_individualDog.deleteIndividual(new Date(), individual.getId());

		_individualDog.updateDynamicMemberships(
			DateUtil.toUTCDate("2019-02-11T20:26:53.218Z"),
			_segmentDog.getSegment(338511398116723458L));

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
	public void testUpdateDynamicRemoveMemberships() {
		_individualDog.updateDynamicRemoveMemberships(
			DateUtil.toUTCDate("2019-02-11T20:26:53.218Z"),
			_segmentDog.getSegment(338511398116723458L));

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
		name = "users", resourcePath = "users.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_DXP_RAW
	)
	@Test
	public void testUpdateIndividualAssociation() throws Exception {
		DataSource dataSource = FaroInfoTestUtil.buildLiferayDataSource();

		dataSource.setId(402139209179557944L);

		_dataSourceRepository.save(dataSource);

		Organization organization1 = new Organization();

		organization1.setDataSourceId(402139209179557944L);
		organization1.setId(402139267512234420L);
		organization1.setName("engineering");
		organization1.setOrganizationPK(33120L);

		_organizationRepository.save(organization1);

		Organization organization2 = new Organization();

		organization2.setDataSourceId(402139209179557944L);
		organization2.setId(402139268847589064L);
		organization2.setName("marketing");
		organization2.setOrganizationPK(33134L);

		_organizationRepository.save(organization2);

		Individual individual = new Individual();

		individual.setId(402139280465582637L);
		individual.setOrganizationIds(
			Collections.singleton(402139267512234420L));
		individual.setDataSourceIndividuals(
			Collections.singleton(
				new DataSourceIndividual(
					Collections.emptySet(), 402139209179557944L,
					402139280465582637L,
					Collections.singleton(
						"86ada3db-d8f9-c59f-7985-5c8fbdebb169"))));

		individual = _individualDog.addIndividual(individual, false);

		Field field = new Field();

		field.setContext("demographics");
		field.setDataSourceId(402139209179557944L);
		field.setDataSourceName("Test Data Source");
		field.setFieldType("Text");
		field.setModifiedDate(DateUtil.toUTCDate("2020-01-30T17:59:38.729Z"));
		field.setName("email");
		field.setOwnerId(402139280465582637L);
		field.setOwnerType("individual");
		field.setSourceName("email");
		field.setValue("test1@liferay.com");

		_fieldRepository.save(field);

		individual.setFields(Collections.singleton(field));

		_individualDog.updateIndividual(individual);

		_fieldMappingRepository.save(
			FaroInfoTestUtil.buildIndividualFieldMapping(
				dataSource.getId(), "emailAddress", "email", "Text"));

		individual = _individualDog.updateIndividual(
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
				"osbAsahDataSourceId", String.valueOf(dataSource.getId())
			).put(
				"userId", 36016
			),
			dataSource, individual);

		Set<Long> organizationIds = individual.getOrganizationIds();

		Assert.assertThat(
			new Long[] {402139267512234420L, 402139268847589064L},
			Matchers.arrayContainingInAnyOrder(
				organizationIds.toArray(new Long[0])));
	}

	@Test
	public void testUpdateIndividualFromDifferentDataSourceIgnoresNullValue()
		throws Exception {

		Individual individual = _individualDog.addIndividual(
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

		individual = _individualDog.updateIndividual(
			String.valueOf(individual.getId()),
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
			_liferayDataSource, individual);

		Set<Field> fields = individual.getFields();

		Stream<Field> stream = fields.stream();

		Field countryField = stream.filter(
			field -> Objects.equals("country", field.getName())
		).findFirst(
		).orElse(
			null
		);

		Assert.assertEquals("United States", countryField.getValue());
	}

	@Test
	public void testUpdateIndividualUpdatesPagesAndAssets() throws Exception {
		Date date = DateUtil.toUTCDate(DateUtil.newDayDateString());

		Individual individual = new Individual();

		individual.setActivitiesCounts(Collections.emptySet());
		individual.setChannelIds(Collections.singleton(1L));
		individual.setCreateDate(date);

		DataSourceIndividual dataSourceIndividual = new DataSourceIndividual();

		dataSourceIndividual.setIndividualPKs(Collections.singleton("12345"));
		dataSourceIndividual.setDataSourceId(_liferayDataSource.getId());

		individual.setDataSourceIndividuals(
			Collections.singleton(dataSourceIndividual));

		individual.setEmailAddressHashed(
			"47ff64395860b1d498241d907069f649b98c198a95b3ba5303b87094058590");
		individual.setModifiedDate(date);
		individual.setSegmentIds(Collections.emptySet());

		individual = _individualDog.addIndividual(individual, false);

		JSONObject page1JSONObject = cerebroInfoElasticsearchInvoker.add(
			"pages",
			JSONUtil.put(
				"individualId", String.valueOf(individual.getId())
			).put(
				"knownIndividual", false
			));

		JSONObject page2JSONObject = cerebroInfoElasticsearchInvoker.add(
			"pages",
			JSONUtil.put(
				"individualId", String.valueOf(individual.getId())
			).put(
				"knownIndividual", true
			));

		_individualDog.updateIndividual(
			String.valueOf(individual.getId()),
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
			_liferayDataSource, individual);

		page1JSONObject = cerebroInfoElasticsearchInvoker.fetch(
			"pages", page1JSONObject.getString("id"));

		Assert.assertTrue(page1JSONObject.getBoolean("knownIndividual"));

		page2JSONObject = cerebroInfoElasticsearchInvoker.fetch(
			"pages", page2JSONObject.getString("id"));

		Assert.assertTrue(page2JSONObject.getBoolean("knownIndividual"));
	}

	private void _assertCustomFields(
		Set<Field> customFields, Object expectedValue, String fieldName) {

		Stream<Field> stream = customFields.stream();

		List<Object> values = stream.filter(
			field -> Objects.equals(fieldName, field.getName())
		).map(
			Field::getValue
		).collect(
			Collectors.toList()
		);

		if (values.isEmpty()) {
			Assert.assertNull(expectedValue);
		}
		else if (values.size() == 1) {
			Assert.assertEquals(
				String.valueOf(expectedValue), String.valueOf(values.get(0)));
		}
		else {
			Assert.assertThat(
				(Object[])expectedValue,
				Matchers.arrayContainingInAnyOrder(
					values.toArray(new Object[0])));
		}
	}

	private void _assertDataSourceIndividualPKs(
		Object[] expectedValue, Individual individual) {

		Set<Individual.DataSourceIndividualPK> dataSourceIndividualPKs =
			individual.getDataSourceIndividualPKs();

		Iterator<Individual.DataSourceIndividualPK> iterator =
			dataSourceIndividualPKs.iterator();

		Individual.DataSourceIndividualPK dataSourceIndividualPK =
			iterator.next();

		Set<String> individualPKs = dataSourceIndividualPK.getIndividualPKs();

		Assert.assertThat(
			expectedValue,
			Matchers.arrayContainingInAnyOrder(
				individualPKs.toArray(new Object[0])));
	}

	private void _assertIndividualMiddleName(
		String expectedMiddleName, Individual individual) {

		Set<Field> fields = individual.getFields();

		Stream<Field> stream = fields.stream();

		Field middleNameField = stream.filter(
			field -> Objects.equals(field.getName(), "middleName")
		).findFirst(
		).orElse(
			null
		);

		Assert.assertEquals(expectedMiddleName, middleNameField.getValue());
	}

	private List<String> _getGivenNames(List<Individual> individuals) {
		List<String> givenNames = new LinkedList<>();

		Stream<Individual> stream1 = individuals.stream();

		stream1.forEachOrdered(
			individual -> {
				Set<Field> fields = individual.getFields();

				Stream<Field> stream2 = fields.stream();

				Optional<Field> fieldOptional = stream2.filter(
					field -> Objects.equals(field.getName(), "givenName")
				).findFirst();

				fieldOptional.ifPresent(
					field -> givenNames.add(String.valueOf(field.getValue())));
			});

		return givenNames;
	}

	private static final String[] _FIELD_NAMES = {
		"country", "email", "middleName"
	};

	@Autowired
	private DataSourceRepository _dataSourceRepository;

	@Autowired
	private FieldMappingRepository _fieldMappingRepository;

	@Autowired
	private FieldRepository _fieldRepository;

	@Autowired
	private IndividualDog _individualDog;

	private DataSource _liferayDataSource;

	@Autowired
	private MembershipDog _membershipDog;

	@Autowired
	private ObjectMapper _objectMapper;

	@Autowired
	private OrganizationRepository _organizationRepository;

	private DataSource _salesforceDataSource;

	@Autowired
	private SegmentDog _segmentDog;

}