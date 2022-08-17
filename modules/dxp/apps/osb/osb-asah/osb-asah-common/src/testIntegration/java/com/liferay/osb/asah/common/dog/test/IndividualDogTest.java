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
import com.liferay.osb.asah.common.dog.BQMembershipDog;
import com.liferay.osb.asah.common.dog.IndividualDog;
import com.liferay.osb.asah.common.dog.SegmentDog;
import com.liferay.osb.asah.common.entity.BQDataSourceUser;
import com.liferay.osb.asah.common.entity.BQMembership;
import com.liferay.osb.asah.common.entity.BQOrganization;
import com.liferay.osb.asah.common.entity.DXPEntity;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.entity.Individual;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.faro.info.dog.test.BaseFaroInfoDogTestCase;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.DXPEntityType;
import com.liferay.osb.asah.common.model.Field;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.repository.BQMembershipChangeRepository;
import com.liferay.osb.asah.common.repository.BQMembershipRepository;
import com.liferay.osb.asah.common.repository.BQOrganizationRepository;
import com.liferay.osb.asah.common.repository.DXPEntityRepository;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.common.repository.IndividualRepository;
import com.liferay.osb.asah.common.repository.SegmentRepository;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.common.util.SetUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.annotation.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.annotation.RepositoryResource;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.RandomStringUtils;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;

import org.json.JSONObject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.yaml.snakeyaml.util.ArrayUtils;

/**
 * @author Rachael Koestartyo
 */
@Disabled
public class IndividualDogTest
	extends BaseFaroInfoDogTestCase
	implements OSBAsahTestExecutionListenersContext {

	@BeforeEach
	public void setUp() {
		_liferayDataSource = FaroInfoTestUtil.buildLiferayDataSource();

		_liferayDataSource.setId(
			Long.parseLong(RandomStringUtils.randomNumeric(4)));

		// TODO Save BQFieldMapping with _FIELD_NAMES

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

		// TODO Add BQFieldMapping "address", "Text"
		// TODO Add BQFieldMapping "spokenLanguages", "Text"
		// TODO Add BQFieldMapping "favoriteNumber", "Number"

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
		_assertCustomFields(customFields, "[english]", "spokenLanguages");

		userJSONObject.remove("expando");

		individual = _individualDog.updateIndividual(
			"1", userJSONObject, _liferayDataSource, individual);

		_assertCustomFields(customFields, "1400 Montefino Ave", "address");
		_assertCustomFields(customFields, 42, "favoriteNumber");
		_assertCustomFields(customFields, "[english]", "spokenLanguages");

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
		_assertCustomFields(customFields, "[german]", "spokenLanguages");
	}

	@RepositoryResource(
		repositoryClass = IndividualRepository.class,
		resourcePath = "osbasahfaroinfo/individuals_associations.json"
	)
	@RepositoryResource(
		repositoryClass = DataSourceRepository.class,
		resourcePath = "osbasahfaroinfo/data_sources.json"
	)
	@RepositoryResource(
		repositoryClass = DXPEntityRepository.class,
		resourcePath = "osbasahdxpraw/users.json"
	)
	@RepositoryResource(
		repositoryClass = BQOrganizationRepository.class,
		resourcePath = "osbasahfaroinfo/organizations.json"
	)
	@Test
	public void testAddIndividualAssociation() {
		Individual individual = _individualDog.addIndividualAssociation(
			33134, 405201047787757795L,
			DXPEntity.Type.of(DXPEntityType.CLASS_NAME_ORGANIZATION),
			_individualDog.fetchIndividualByEmailAddress("test1@liferay.com"));

		Set<Long> organizationIds = individual.getOrganizationIds();

		MatcherAssert.assertThat(
			new Long[] {402139267512234420L, 402139268847589064L},
			Matchers.arrayContainingInAnyOrder(
				organizationIds.toArray(new Long[0])));
	}

	@RepositoryResource(
		repositoryClass = IndividualRepository.class,
		resourcePath = "osbasahfaroinfo/individuals_2.json"
	)
	@Test
	public void testCountActiveIndividualsFromLast30DaysBySegment() {
		Segment segment = new Segment();

		segment.setChannelId(100L);
		segment.setId(123L);

		Assertions.assertEquals(
			3,
			_individualDog.countActiveIndividualsFromLast30DaysBySegment(
				segment));
	}

	@ElasticsearchIndex(
		name = "organizations", resourcePath = "organizations.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@RepositoryResource(
		repositoryClass = IndividualRepository.class,
		resourcePath = "osbasahfaroinfo/individuals_associations.json"
	)
	@RepositoryResource(
		repositoryClass = DataSourceRepository.class,
		resourcePath = "osbasahfaroinfo/data_sources.json"
	)
	@RepositoryResource(
		repositoryClass = DXPEntityRepository.class,
		resourcePath = "osbasahdxpraw/users.json"
	)
	@RepositoryResource(
		repositoryClass = BQOrganizationRepository.class,
		resourcePath = "osbasahfaroinfo/organizations.json"
	)
	@Test
	public void testDeleteIndividualAssociation() {
		Individual individual = _individualDog.deleteIndividualAssociation(
			33134, 402139209179557944L,
			DXPEntity.Type.of(DXPEntityType.CLASS_NAME_ORGANIZATION),
			_individualDog.fetchIndividualByEmailAddress("test1@liferay.com"));

		Set<Long> organizationIds = individual.getOrganizationIds();

		MatcherAssert.assertThat(
			new Long[] {402139267512234420L},
			Matchers.arrayContainingInAnyOrder(
				organizationIds.toArray(new Long[0])));
	}

	@ElasticsearchIndex(
		name = "fields", resourcePath = "individuals_fields_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@RepositoryResource(
		repositoryClass = IndividualRepository.class,
		resourcePath = "osbasahfaroinfo/individuals_info.json"
	)
	@Test
	public void testGetIndividualPage1() {

		// TODO Add BQFieldMapping "client_id", "Text"
		// TODO Add BQFieldMapping "favoritePokemon", "Text"

		Page<Individual> individualPage = _individualDog.getIndividualPage(
			"", null, 0, 10);

		Assertions.assertEquals(5, individualPage.getTotalElements());

		Assertions.assertEquals(
			SetUtil.of("123", "124", "125", "126", "127"),
			Collections.emptySet());
		Assertions.assertEquals(
			SetUtil.of("Bulbasaur", "Ivysaur", "Venusaur", "Charmander", "Mew"),
			Collections.emptySet());
	}

	@ElasticsearchIndex(
		name = "fields", resourcePath = "individuals_fields_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@RepositoryResource(
		repositoryClass = IndividualRepository.class,
		resourcePath = "osbasahfaroinfo/individuals_info.json"
	)
	@Test
	public void testGetIndividualPage2() {

		// TODO Add BQFieldMapping "favoritePokemon", "Text"

		Page<Individual> individualPage = _individualDog.getIndividualPage(
			"mander", null, 0, 10);

		Assertions.assertEquals(1, individualPage.getTotalElements());

		Assertions.assertEquals(
			SetUtil.of("Charmander"), Collections.emptySet());
	}

	@RepositoryResource(
		repositoryClass = IndividualRepository.class,
		resourcePath = "osbasahfaroinfo/individuals_4.json"
	)
	@Test
	public void testGetIndividualPage3() {
		Page<Individual> individualPage = _individualDog.getIndividualPage(
			DateUtil.toUTCDate("2019-02-10T10:00:00.000Z"), -1L, 2,
			Sort.by(Sort.Order.asc("id")),
			DateUtil.toUTCDate("2019-02-12T14:00:00.000Z"));

		Assertions.assertEquals(3, individualPage.getTotalElements());

		List<Individual> individuals = individualPage.getContent();

		Assertions.assertEquals(2, individuals.size());

		Individual individual = individuals.get(0);

		Assertions.assertEquals(4L, individual.getId());

		individual = individuals.get(1);

		Assertions.assertEquals(6L, individual.getId());
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

		_assertDataSourceUserPKs(new String[] {uuid1}, individual);

		String uuid2 = RandomTestUtil.randomUUID();

		individual = _individualDog.updateIndividual(
			uuid2, dataJSONObject.put("uuid", uuid2), _liferayDataSource,
			individual);

		_assertDataSourceUserPKs(new Object[] {uuid1, uuid2}, individual);

		individual = _individualDog.updateIndividual(
			uuid2, dataJSONObject.put("test", "test"), _liferayDataSource,
			individual);

		_assertDataSourceUserPKs(new Object[] {uuid1, uuid2}, individual);
	}

	@RepositoryResource(
		repositoryClass = IndividualRepository.class,
		resourcePath = "osbasahfaroinfo/individuals.json"
	)
	@Test
	public void testRemoveIndividualSegmentId() {
		Individual individual = _individualDog.fetchIndividual(
			338486041327913341L);

		Set<Long> segmentIds = individual.getSegmentIds();

		Assertions.assertEquals(1, segmentIds.size(), segmentIds.toString());

		_individualDog.removeSegmentId(individual.getId(), 338511398116723458L);

		individual = _individualDog.fetchIndividual(338486041327913341L);

		segmentIds = individual.getSegmentIds();

		Assertions.assertEquals(0, segmentIds.size(), segmentIds.toString());
	}

	@ElasticsearchIndex(
		name = "fields", resourcePath = "fields.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@RepositoryResource(
		repositoryClass = IndividualRepository.class,
		resourcePath = "osbasahfaroinfo/individuals.json"
	)
	@Test
	public void testSearchIndividuals1() {

		// TODO Add BQFieldMapping "givenName", "Text"

		List<Individual> individuals = _individualDog.searchIndividuals(
			100L, null, false, 0, 10,
			new String[] {"demographics/givenName/value,asc"});

		Assertions.assertEquals(
			ArrayUtils.toUnmodifiableList(
				new String[] {"alpha", "beta", "gamma", "omega", "theta"}),
			_getGivenNames(individuals));

		individuals = _individualDog.searchIndividuals(
			100L, null, false, 0, 10,
			new String[] {"demographics/givenName/value,desc"});

		Assertions.assertEquals(
			ArrayUtils.toUnmodifiableList(
				new String[] {"theta", "omega", "gamma", "beta", "alpha"}),
			_getGivenNames(individuals));

		individuals = _individualDog.searchIndividuals(
			null, null, false, 0, 10,
			new String[] {"demographics/givenName/value,desc"});

		Assertions.assertEquals(
			ArrayUtils.toUnmodifiableList(
				new String[] {"theta", "omega", "gamma", "beta", "alpha"}),
			_getGivenNames(individuals));
	}

	@ElasticsearchIndex(
		name = "activities", resourcePath = "activities.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@RepositoryResource(
		repositoryClass = IndividualRepository.class,
		resourcePath = "osbasahfaroinfo/individuals.json"
	)
	@Test
	public void testSearchIndividuals2() {
		StringBuilder sb = new StringBuilder();

		sb.append("(channelIds eq '100' and (activities.filterByCount(");
		sb.append("filter='(activityKey eq ");
		sb.append("''Form#formViewed#511687236573013375'' and day gt ");
		sb.append("''last24Hours'')',operator='%s',value=%s)))");

		List<Individual> individuals = _individualDog.searchIndividuals(
			100L, String.format(sb.toString(), "eq", 2), true, 0, 10, null);

		Assertions.assertEquals(1, individuals.size(), individuals.toString());

		individuals = _individualDog.searchIndividuals(
			100L, String.format(sb.toString(), "ge", 2), true, 0, 10, null);

		Assertions.assertEquals(1, individuals.size(), individuals.toString());

		individuals = _individualDog.searchIndividuals(
			100L, String.format(sb.toString(), "ge", 3), true, 0, 10, null);

		Assertions.assertEquals(0, individuals.size(), individuals.toString());

		individuals = _individualDog.searchIndividuals(
			100L, String.format(sb.toString(), "gt", 0), true, 0, 10, null);

		Assertions.assertEquals(1, individuals.size(), individuals.toString());

		individuals = _individualDog.searchIndividuals(
			100L, String.format(sb.toString(), "le", 3), true, 0, 10, null);

		Assertions.assertEquals(5, individuals.size(), individuals.toString());

		individuals = _individualDog.searchIndividuals(
			100L, String.format(sb.toString(), "lt", 2), true, 0, 10, null);

		Assertions.assertEquals(4, individuals.size(), individuals.toString());
	}

	@Disabled
	@ElasticsearchIndex(
		name = "user-sessions", resourcePath = "user_sessions.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testSearchIndividuals3() {
		Individual individual = new Individual();

		individual.setChannelIds(Collections.singleton(100L));
		individual.setId(1L);
		individual.setSegmentIds(Collections.emptySet());

		_individualDog.addIndividual(individual, false);

		StringBuilder sb = new StringBuilder();

		sb.append("(channelIds eq '100' and (sessions.filter(filter=");
		sb.append(
			"'(context/country %s ''%s'' and context/city %s ''%s'')')))");

		List<Individual> individuals = _individualDog.searchIndividuals(
			100L, String.format(sb.toString(), "eq", "Japan", "eq", "Tokyo"),
			true, 0, 10, null);

		Assertions.assertEquals(1, individuals.size(), individuals.toString());

		individuals = _individualDog.searchIndividuals(
			100L, String.format(sb.toString(), "ne", "Japan", "ne", "Tokyo"),
			true, 0, 10, null);

		Assertions.assertEquals(0, individuals.size(), individuals.toString());

		individuals = _individualDog.searchIndividuals(
			100L, String.format(sb.toString(), "eq", "japan", "eq", "tokyo"),
			true, 0, 10, null);

		Assertions.assertEquals(1, individuals.size(), individuals.toString());

		individuals = _individualDog.searchIndividuals(
			100L, String.format(sb.toString(), "ne", "japan", "ne", "tokyo"),
			true, 0, 10, null);

		Assertions.assertEquals(0, individuals.size(), individuals.toString());
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
		segment.setIsNew(Boolean.TRUE);
		segment.setStatus("ACTIVE");

		_segmentDog.addSegment(segment);

		_individualDog.updateDynamicMemberships(
			DateUtil.toUTCDate("2019-02-11T20:26:53.218Z"), segment);

		individual = _individualDog.fetchIndividual(individual.getId());

		Set<Long> segmentIds = individual.getSegmentIds();

		Assertions.assertArrayEquals(
			new Long[] {234L}, segmentIds.toArray(new Long[0]));
	}

	@Test
	public void testUpdateDynamicMemberships() {
		Segment segment1 = new Segment();

		segment1.setFilter("");
		segment1.setFilterMetadata("0");
		segment1.setId(338511398116723458L);
		segment1.setIsNew(Boolean.TRUE);
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
		segment2.setIsNew(Boolean.TRUE);
		segment2.setModifiedDate(
			DateUtil.toUTCDate("2019-02-11T20:27:47.622Z"));
		segment2.setName("Test Segment 2");
		segment2.setScope("PROJECT");
		segment2.setType(Segment.Type.STATIC);
		segment2.setState("READY");
		segment2.setStatus("ACTIVE");

		_segmentDog.addSegment(segment2);

		Segment segment3 = new Segment();

		segment3.setFilter("");
		segment3.setFilterMetadata("0");
		segment3.setId(338511451975440190L);
		segment3.setIsNew(Boolean.TRUE);
		segment3.setModifiedDate(
			DateUtil.toUTCDate("2022-01-06T20:27:47.622Z"));
		segment3.setName("Test Segment 3");
		segment3.setScope("PROJECT");
		segment3.setType(Segment.Type.DYNAMIC);
		segment3.setFilter(
			"(sessions.filter(filter='(context/country eq ''Germany'' and " +
				"completeDate gt ''last24Hours'')'))");
		segment3.setState("READY");
		segment3.setStatus("ACTIVE");

		_segmentDog.addSegment(segment3);

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

		individual.setFields(Collections.singleton(field));

		_individualDog.updateIndividual(individual);

		BQMembership bqMembership = new BQMembership();

		bqMembership.setCreateDate(
			DateUtil.toUTCDate("2019-02-11T20:26:53.218Z"));
		bqMembership.setIdentityId("338486037253283140");
		bqMembership.setSegmentId(338511451975440187L);
		bqMembership.setStatus("ACTIVE");

		_bqMembershipDog.addBQMembership(bqMembership);

		_individualDog.updateDynamicMemberships(
			DateUtil.toUTCDate("2019-02-11T20:26:53.218Z"),
			_segmentDog.getSegment(338511451975440187L));

		individual = _individualDog.fetchIndividual(338486037253283140L);

		Set<Long> segmentIds = individual.getSegmentIds();

		MatcherAssert.assertThat(
			new Long[] {338511398116723458L, 338511451975440187L},
			Matchers.arrayContainingInAnyOrder(
				segmentIds.toArray(new Long[0])));

		_individualDog.deleteIndividual(new Date(), individual.getId());

		_individualDog.updateDynamicMemberships(
			DateUtil.toUTCDate("2019-02-11T20:26:53.218Z"),
			_segmentDog.getSegment(338511398116723458L));

		Assertions.assertEquals(
			0,
			_bqMembershipRepository.countByIdentityIdAndSegmentId(
				"338486037253283140", 338511398116723458L));

		_individualDog.updateDynamicMemberships(
			DateUtil.toUTCDate("2022-01-06T20:26:53.218Z"),
			_segmentDog.getSegment(338511451975440190L));

		Assertions.assertEquals(
			0, _bqMembershipRepository.countBySegmentId(338511451975440190L));
	}

	@RepositoryResource(
		repositoryClass = BQMembershipChangeRepository.class,
		resourcePath = "osbasahfaroinfo/bq_membership_changes.json"
	)
	@RepositoryResource(
		repositoryClass = BQMembershipRepository.class,
		resourcePath = "osbasahfaroinfo/bq_memberships.json"
	)
	@RepositoryResource(
		repositoryClass = SegmentRepository.class,
		resourcePath = "osbasahfaroinfo/individual_segments.json"
	)
	@Test
	public void testUpdateDynamicRemoveMemberships() {
		_individualDog.updateDynamicRemoveMemberships(
			DateUtil.toUTCDate("2019-02-11T20:26:53.218Z"),
			_segmentDog.getSegment(338511398116723458L));

		Assertions.assertEquals(
			3,
			_bqMembershipRepository.countBySegmentIdAndStatus(
				338511398116723458L, "INACTIVE"));
	}

	@RepositoryResource(
		repositoryClass = DataSourceRepository.class,
		resourcePath = "osbasahfaroinfo/data_sources.json"
	)
	@RepositoryResource(
		repositoryClass = DXPEntityRepository.class,
		resourcePath = "osbasahdxpraw/users.json"
	)
	@Test
	public void testUpdateIndividualAssociation() throws Exception {
		DataSource dataSource = FaroInfoTestUtil.buildLiferayDataSource();

		dataSource.setId(402139209179557944L);

		_dataSourceRepository.save(dataSource);

		BQOrganization bqOrganization1 = new BQOrganization();

		bqOrganization1.setDataSourceId(402139209179557944L);
		bqOrganization1.setId("402139267512234420");
		bqOrganization1.setIsNew(true);
		bqOrganization1.setName("engineering");
		bqOrganization1.setOrganizationId(33120L);

		_bqOrganizationRepository.save(bqOrganization1);

		BQOrganization bqOrganization2 = new BQOrganization();

		bqOrganization2.setDataSourceId(402139209179557944L);
		bqOrganization2.setId("402139268847589064");
		bqOrganization2.setIsNew(true);
		bqOrganization2.setName("marketing");
		bqOrganization2.setOrganizationId(33134L);

		_bqOrganizationRepository.save(bqOrganization2);

		Individual individual = new Individual();

		individual.setBQDataSourceUsers(
			Collections.singleton(
				new BQDataSourceUser(
					Collections.emptySet(), 402139209179557944L,
					402139280465582637L,
					Collections.singleton(
						"86ada3db-d8f9-c59f-7985-5c8fbdebb169"))));
		individual.setId(402139280465582637L);
		individual.setOrganizationIds(
			Collections.singleton(402139267512234420L));

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

		individual.setFields(Collections.singleton(field));

		_individualDog.updateIndividual(individual);

		// TODO Add BQFieldMapping "email", "Text"

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

		MatcherAssert.assertThat(
			new Long[] {402139267512234420L, 402139268847589064L},
			Matchers.arrayContainingInAnyOrder(
				organizationIds.toArray(new Long[0])));
	}

	@RepositoryResource(
		repositoryClass = IndividualRepository.class,
		resourcePath = "osbasahfaroinfo/individuals_3.json"
	)
	@Test
	public void testUpdateIndividualFirstEnrichmentDate() throws Exception {
		Individual individual = _individualDog.updateIndividual(
			null,
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
			_liferayDataSource,
			_individualDog.getIndividual(523130384134494521L));

		Assertions.assertNotNull(individual.getFirstEnrichmentDate());
	}

	@Test
	public void testUpdateIndividualFromDifferentDataSourceIgnoresNullValue()
		throws Exception {

		Individual individual = _individualDog.addIndividual(
			"2",
			JSONUtil.put(
				"country", "United States"
			).put(
				"email", "dummy.test@test.com"
			).put(
				"modifiedDate", DateUtil.newDateString()
			),
			null);

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

		Assertions.assertEquals("United States", countryField.getValue());
	}

	@Test
	public void testUpdateIndividualUpdatesPagesAndAssets() throws Exception {
		Date date = DateUtil.toUTCDate(DateUtil.newDayDateString());

		Individual individual = new Individual();

		individual.setActivitiesCounts(Collections.emptySet());

		BQDataSourceUser bqDataSourceUser = new BQDataSourceUser();

		bqDataSourceUser.setUserPKs(Collections.singleton("12345"));
		bqDataSourceUser.setDataSourceId(_liferayDataSource.getId());

		individual.setBQDataSourceUsers(
			Collections.singleton(bqDataSourceUser));

		individual.setChannelIds(Collections.singleton(1L));
		individual.setCreateDate(date);

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

		_updateIndividualAsync(
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
			individual);

		page1JSONObject = cerebroInfoElasticsearchInvoker.fetch(
			"pages", page1JSONObject.getString("id"));

		Assertions.assertTrue(page1JSONObject.getBoolean("knownIndividual"));

		page2JSONObject = cerebroInfoElasticsearchInvoker.fetch(
			"pages", page2JSONObject.getString("id"));

		Assertions.assertTrue(page2JSONObject.getBoolean("knownIndividual"));
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
			Assertions.assertNull(expectedValue);
		}
		else if (values.size() == 1) {
			Assertions.assertEquals(
				String.valueOf(expectedValue), String.valueOf(values.get(0)));
		}
		else {
			MatcherAssert.assertThat(
				(Object[])expectedValue,
				Matchers.arrayContainingInAnyOrder(
					values.toArray(new Object[0])));
		}
	}

	private void _assertDataSourceUserPKs(
		Object[] expectedValue, Individual individual) {

		Set<Individual.DataSourceUserPK> dataSourceUserPKs =
			individual.getDataSourceUserPKs();

		Iterator<Individual.DataSourceUserPK> iterator =
			dataSourceUserPKs.iterator();

		Individual.DataSourceUserPK dataSourceUserPK = iterator.next();

		Set<String> userPKs = dataSourceUserPK.getUserPKs();

		MatcherAssert.assertThat(
			expectedValue,
			Matchers.arrayContainingInAnyOrder(userPKs.toArray(new Object[0])));
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

		Assertions.assertEquals(expectedMiddleName, middleNameField.getValue());
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

	private void _updateIndividualAsync(
			JSONObject dataJSONObject, Individual individual)
		throws Exception {

		String projectId = ProjectIdThreadLocal.getProjectId();

		ExecutorService executorService = Executors.newSingleThreadExecutor();

		Future<?> future = executorService.submit(
			() -> {
				try {
					ProjectIdThreadLocal.setProjectId(projectId);

					_individualDog.updateIndividual(
						String.valueOf(individual.getId()), dataJSONObject,
						_liferayDataSource, individual);
				}
				catch (Exception exception) {
				}
			});

		try {
			future.get(3, TimeUnit.SECONDS);
		}
		catch (TimeoutException timeoutException) {
			future.cancel(true);
		}
		finally {
			executorService.shutdownNow();
		}
	}

	private static final String[] _FIELD_NAMES = {
		"country", "email", "middleName"
	};

	@Autowired
	private BQMembershipDog _bqMembershipDog;

	@Autowired
	private BQMembershipRepository _bqMembershipRepository;

	@Autowired
	private BQOrganizationRepository _bqOrganizationRepository;

	@Autowired
	private DataSourceRepository _dataSourceRepository;

	@Autowired
	private IndividualDog _individualDog;

	private DataSource _liferayDataSource;

	@Autowired
	private ObjectMapper _objectMapper;

	@Autowired
	private SegmentDog _segmentDog;

}