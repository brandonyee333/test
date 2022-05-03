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
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.entity.Field;
import com.liferay.osb.asah.common.entity.Individual;
import com.liferay.osb.asah.common.entity.Membership;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.faro.info.dog.test.BaseFaroInfoDogTestCase;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.common.repository.FieldMappingRepository;
import com.liferay.osb.asah.common.repository.FieldRepository;
import com.liferay.osb.asah.common.repository.SegmentRepository;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.annotation.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.elasticsearch.index.query.QueryBuilders;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Michael Bowerman
 * @author Vishal Reddy
 */
public class MembershipDogTest
	extends BaseFaroInfoDogTestCase
	implements OSBAsahTestExecutionListenersContext {

	@Test
	public void testAddMembershipWithActiveStatusAndAnonymousIndividual() {
		Segment segment1 = new Segment();

		segment1.setId(234L);
		segment1.setIncludeAnonymousUsers(Boolean.TRUE);
		segment1.setStatus("ACTIVE");

		_segmentRepository.save(segment1);

		Segment segment2 = new Segment();

		segment2.setId(456L);
		segment2.setIncludeAnonymousUsers(Boolean.TRUE);
		segment2.setStatus("ACTIVE");

		_segmentRepository.save(segment2);

		Individual individual = new Individual();

		individual.setId(123L);
		individual.setSegmentIds(Collections.singleton(456L));

		individual = _individualDog.addIndividual(individual, false);

		Membership membership = new Membership();

		membership.setCreateDate(
			DateUtil.toUTCDate("2019-02-11T20:27:36.603Z"));
		membership.setIndividualId(123L);
		membership.setIndividualSegmentId(234L);
		membership.setModifiedDate(
			DateUtil.toUTCDate("2019-02-11T20:27:36.603Z"));
		membership.setStatus("ACTIVE");

		membership = _membershipDog.addMembership(membership);

		Assertions.assertNotNull(membership);

		JSONArray membershipsJSONArray = faroInfoElasticsearchInvoker.get(
			"memberships");

		Assertions.assertEquals(1, membershipsJSONArray.length());

		JSONAssert.assertEquals(
			_objectMapper.convertValue(membership, JSONObject.class),
			membershipsJSONArray.getJSONObject(0), false);

		Assertions.assertNotNull(membership.getId());

		individual = _individualDog.fetchIndividual(individual.getId());

		Set<Long> segmentIds = individual.getSegmentIds();

		MatcherAssert.assertThat(
			new Long[] {234L, 456L},
			Matchers.arrayContainingInAnyOrder(
				segmentIds.toArray(new Long[0])));

		JSONArray membershipChangesJSONArray = faroInfoElasticsearchInvoker.get(
			"membership-changes");

		Assertions.assertEquals(1, membershipChangesJSONArray.length());

		JSONAssert.assertEquals(
			JSONUtil.put(
				"dateChanged", "2019-02-11T20:27:36.603Z"
			).put(
				"dateFirst", "2019-02-11T20:27:36.603Z"
			).put(
				"individualDeleted", false
			).put(
				"individualId", "123"
			).put(
				"individualsCount", 1
			).put(
				"individualSegmentId", "234"
			).put(
				"knownIndividualsCount", 0
			).put(
				"operation", "ADDED"
			),
			membershipChangesJSONArray.getJSONObject(0), false);
	}

	@Test
	public void testAddMembershipWithInactiveStatus() {
		Membership membership = _membershipDog.addMembership(
			_objectMapper.convertValue(
				JSONUtil.put("status", "INACTIVE"), Membership.class));

		Assertions.assertNotNull(membership);

		JSONArray membershipsJSONArray = faroInfoElasticsearchInvoker.get(
			"memberships");

		Assertions.assertEquals(1, membershipsJSONArray.length());

		JSONAssert.assertEquals(
			JSONUtil.put("status", "INACTIVE"),
			membershipsJSONArray.getJSONObject(0), false);

		Assertions.assertNotNull(membership.getId());
	}

	@ElasticsearchIndex(
		name = "membership-changes", resourcePath = "membership_changes.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "memberships", resourcePath = "memberships.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testDeactivateMembershipWithIndividuals() {
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

		DataSource dataSource = FaroInfoTestUtil.buildLiferayDataSource();

		dataSource.setId(338486009405470846L);

		dataSource = _dataSourceRepository.save(dataSource);

		_fieldMappingRepository.save(
			FaroInfoTestUtil.buildIndividualFieldMapping(
				dataSource.getId(), "email", "email", "Text"));

		Individual individual1 = new Individual();

		individual1.setId(338486041327913341L);
		individual1.setSegmentIds(Collections.singleton(338511398116723458L));

		_individualDog.addIndividual(individual1, false);

		Field field1 = new Field();

		field1.setContext("demographics");
		field1.setDataSourceId(dataSource.getId());
		field1.setDataSourceName("Test Data Source");
		field1.setFieldType("Text");
		field1.setModifiedDate(DateUtil.toUTCDate("2019-02-11T17:05:06.814Z"));
		field1.setName("email");
		field1.setOwnerId(338486041327913341L);
		field1.setOwnerType("individual");
		field1.setSourceName("email");
		field1.setValue("test2@liferay.com");

		_fieldRepository.save(field1);

		individual1.setFields(Collections.singleton(field1));

		_individualDog.updateIndividual(individual1);

		Individual individual2 = new Individual();

		individual2.setId(338486037253283140L);
		individual2.setSegmentIds(Collections.singleton(338511398116723458L));

		_individualDog.addIndividual(individual1, false);

		Field field2 = new Field();

		field2.setContext("demographics");
		field2.setDataSourceId(dataSource.getId());
		field2.setDataSourceName("Test Data Source");
		field2.setFieldType("Text");
		field2.setModifiedDate(DateUtil.toUTCDate("2019-02-11T17:05:06.814Z"));
		field2.setName("email");
		field2.setOwnerId(338486037253283140L);
		field2.setOwnerType("individual");
		field2.setSourceName("email");
		field2.setValue("test1@liferay.com");

		_fieldRepository.save(field2);

		individual2.setFields(Collections.singleton(field2));

		_individualDog.updateIndividual(individual2);

		Date deletionDate = DateUtil.toUTCDate("2019-02-11T20:26:53.218Z");

		_membershipDog.deactivateMembership(
			deletionDate, 338486041327913341L, 338511398116723458L);

		JSONAssert.assertEquals(
			JSONUtil.put(
				"dateCreated", "2019-02-11T20:26:53.218Z"
			).put(
				"dateModified", "2019-02-11T20:26:53.218Z"
			).put(
				"dateRemoved", "2019-02-11T20:26:53.218Z"
			).put(
				"id", "338511398389279308"
			).put(
				"individualId", "338486041327913341"
			).put(
				"individualSegmentId", "338511398116723458"
			).put(
				"status", "INACTIVE"
			),
			faroInfoElasticsearchInvoker.fetch(
				"memberships",
				QueryBuilders.termsQuery("individualId", "338486041327913341")),
			false);

		JSONAssert.assertEquals(
			JSONUtil.put(
				"dateChanged", "2019-02-11T20:26:53.218Z"
			).put(
				"dateFirst", "2019-02-11T20:26:53.218Z"
			).put(
				"individualEmail", "test2@liferay.com"
			).put(
				"individualId", "338486041327913341"
			).put(
				"individualsCount", 1
			).put(
				"individualSegmentId", "338511398116723458"
			).put(
				"knownIndividualsCount", 1
			).put(
				"operation", "REMOVED"
			),
			faroInfoElasticsearchInvoker.fetch(
				"membership-changes",
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery(
						"individualSegmentId", "338511398116723458")
				).filter(
					QueryBuilders.termQuery(
						"individualId", "338486041327913341")
				).filter(
					QueryBuilders.termQuery("operation", "REMOVED")
				)),
			false);

		JSONObject individualJSONObject = faroInfoElasticsearchInvoker.fetch(
			"individuals", "338486041327913341");

		JSONArray individualSegmentIdsJSONArray =
			individualJSONObject.getJSONArray("individualSegmentIds");

		Assertions.assertEquals(0, individualSegmentIdsJSONArray.length());
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
	public void testDeactivateMembershipWithoutKnownIndividuals() {
		Date deletionDate = DateUtil.toUTCDate("2019-02-11T20:26:53.218Z");

		_membershipDog.deactivateMembership(
			deletionDate, 338486041327913339L, 338511398116723457L);

		JSONAssert.assertEquals(
			JSONUtil.put(
				"dateCreated", "2019-02-11T20:26:53.218Z"
			).put(
				"dateModified", "2019-02-11T20:26:53.218Z"
			).put(
				"dateRemoved", "2019-02-11T20:26:53.218Z"
			).put(
				"id", "338511398389279306"
			).put(
				"individualId", "338486041327913339"
			).put(
				"individualSegmentId", "338511398116723457"
			).put(
				"status", "INACTIVE"
			),
			faroInfoElasticsearchInvoker.fetch(
				"memberships",
				QueryBuilders.termsQuery("individualId", "338486041327913339")),
			false);

		JSONAssert.assertEquals(
			JSONUtil.put(
				"dateChanged", "2019-02-11T20:26:53.218Z"
			).put(
				"dateFirst", "2019-02-11T20:26:53.218Z"
			).put(
				"individualId", "338486041327913339"
			).put(
				"individualsCount", 0
			).put(
				"individualSegmentId", "338511398116723457"
			).put(
				"knownIndividualsCount", 0
			).put(
				"operation", "REMOVED"
			),
			faroInfoElasticsearchInvoker.fetch(
				"membership-changes",
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery(
						"individualId", "338486041327913339")
				).filter(
					QueryBuilders.termQuery(
						"individualSegmentId", "338511398116723457")
				).filter(
					QueryBuilders.termQuery("operation", "REMOVED")
				)),
			false);
	}

	@ElasticsearchIndex(
		name = "individual-segments", resourcePath = "individual_segments.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals.json",
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
	public void testGetIndividualSegmentIndividualIds() {
		List<Long> individualIds = _membershipDog.getActiveIndividualIds(
			338511398116723458L);

		Assertions.assertEquals(
			2, individualIds.size(), individualIds.toString());
		Assertions.assertTrue(individualIds.contains(338486037253283140L));
		Assertions.assertTrue(individualIds.contains(338486041327913341L));
	}

	@ElasticsearchIndex(
		name = "memberships", resourcePath = "memberships.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testIsMember() {
		Assertions.assertFalse(_membershipDog.isMember(0L, 0L));
		Assertions.assertTrue(
			_membershipDog.isMember(338486041327913341L, 338511398116723458L));
	}

	@Autowired
	private DataSourceRepository _dataSourceRepository;

	@Autowired
	private FieldMappingRepository _fieldMappingRepository;

	@Autowired
	private FieldRepository _fieldRepository;

	@Autowired
	private IndividualDog _individualDog;

	@Autowired
	private MembershipDog _membershipDog;

	@Autowired
	private ObjectMapper _objectMapper;

	@Autowired
	private SegmentDog _segmentDog;

	@Autowired
	private SegmentRepository _segmentRepository;

}