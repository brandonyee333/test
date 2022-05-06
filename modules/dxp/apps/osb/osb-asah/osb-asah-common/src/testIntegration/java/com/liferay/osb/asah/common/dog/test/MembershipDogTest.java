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
import com.liferay.osb.asah.common.entity.BQMembership;
import com.liferay.osb.asah.common.entity.BQMembershipChange;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.entity.Field;
import com.liferay.osb.asah.common.entity.Individual;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.faro.info.dog.test.BaseFaroInfoDogTestCase;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.repository.BQMembershipChangeRepository;
import com.liferay.osb.asah.common.repository.BQMembershipRepository;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.common.repository.FieldMappingRepository;
import com.liferay.osb.asah.common.repository.FieldRepository;
import com.liferay.osb.asah.common.repository.SegmentRepository;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.annotation.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.annotation.RepositoryResource;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

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

		BQMembership bqMembership = new BQMembership();

		bqMembership.setCreateDate(
			DateUtil.toUTCDate("2019-02-11T20:27:36.603Z"));
		bqMembership.setIndividualId(123L);
		bqMembership.setIndividualSegmentId(234L);
		bqMembership.setModifiedDate(
			DateUtil.toUTCDate("2019-02-11T20:27:36.603Z"));
		bqMembership.setStatus("ACTIVE");

		bqMembership = _membershipDog.addBQMembership(bqMembership);

		Assertions.assertNotNull(bqMembership);

		List<BQMembership> bqMemberships =
			(List<BQMembership>)_bqMembershipRepository.findAll();

		Assertions.assertEquals(1, bqMemberships.size());

		JSONAssert.assertEquals(
			_objectMapper.convertValue(bqMembership, JSONObject.class),
			_objectMapper.convertValue(bqMemberships.get(0), JSONObject.class),
			false);

		Assertions.assertNotNull(bqMembership.getId());

		individual = _individualDog.fetchIndividual(individual.getId());

		Set<Long> segmentIds = individual.getSegmentIds();

		MatcherAssert.assertThat(
			new Long[] {234L, 456L},
			Matchers.arrayContainingInAnyOrder(
				segmentIds.toArray(new Long[0])));

		List<BQMembershipChange> bqMembershipChanges =
			(List<BQMembershipChange>)_bqMembershipChangeRepository.findAll();

		Assertions.assertEquals(1, bqMembershipChanges.size());

		BQMembershipChange bqMembershipChange = bqMembershipChanges.get(0);

		Assertions.assertEquals(
			DateUtil.toUTCDate("2019-02-11T20:27:36.603Z"),
			bqMembershipChange.getModifiedDate());
		Assertions.assertEquals(
			DateUtil.toUTCDate("2019-02-11T20:27:36.603Z"),
			bqMembershipChange.getJoinedDate());
		Assertions.assertEquals(
			false, bqMembershipChange.getIndividualDeleted());
		Assertions.assertEquals(123L, bqMembershipChange.getIndividualId());
		Assertions.assertEquals(1, bqMembershipChange.getIndividualsCount());
		Assertions.assertEquals(
			234L, bqMembershipChange.getIndividualSegmentId());
		Assertions.assertEquals(
			0, bqMembershipChange.getKnownIndividualsCount());
		Assertions.assertEquals("ADDED", bqMembershipChange.getOperation());
	}

	@Test
	public void testAddMembershipWithInactiveStatus() {
		BQMembership bqMembership = _membershipDog.addBQMembership(
			_objectMapper.convertValue(
				JSONUtil.put("status", "INACTIVE"), BQMembership.class));

		Assertions.assertNotNull(bqMembership);

		List<BQMembership> bqMemberships =
			(List<BQMembership>)_bqMembershipRepository.findAll();

		Assertions.assertEquals(1, bqMemberships.size());

		bqMembership = bqMemberships.get(0);

		Assertions.assertEquals("INACTIVE", bqMembership.getStatus());

		Assertions.assertNotNull(bqMembership.getId());
	}

	@RepositoryResource(
		repositoryClass = BQMembershipRepository.class,
		resourcePath = "osbasahfaroinfo/bq_memberships.json"
	)
	@RepositoryResource(
		repositoryClass = BQMembershipChangeRepository.class,
		resourcePath = "osbasahfaroinfo/bq_membership_changes.json"
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

		_membershipDog.deactivateBQMembership(
			deletionDate, 338486041327913341L, 338511398116723458L);

		BQMembership bqMembership =
			_bqMembershipRepository.
				findByIndividualIdAndIndividualSegmentIdAndStatus(
					338486041327913341L, 338511398116723458L, "INACTIVE");

		Assertions.assertEquals(
			DateUtil.toUTCDate("2019-02-11T20:26:53.218Z"),
			bqMembership.getCreateDate());
		Assertions.assertEquals(
			DateUtil.toUTCDate("2019-02-11T20:26:53.218Z"),
			bqMembership.getModifiedDate());
		Assertions.assertEquals(
			DateUtil.toUTCDate("2019-02-11T20:26:53.218Z"),
			bqMembership.getRemovedDate());
		Assertions.assertEquals(338511398389279308L, bqMembership.getId());
		Assertions.assertEquals(
			338486041327913341L, bqMembership.getIndividualId());
		Assertions.assertEquals(
			338511398116723458L, bqMembership.getIndividualSegmentId());
		Assertions.assertEquals("INACTIVE", bqMembership.getStatus());

		BQMembershipChange bqMembershipChange =
			_bqMembershipChangeRepository.
				findByIndividualIdAndIndividualSegmentIdAndOperation(
					338486041327913341L, 338511398116723458L, "REMOVED");

		Assertions.assertEquals(
			DateUtil.toUTCDate("2019-02-11T20:26:53.218Z"),
			bqMembershipChange.getModifiedDate());
		Assertions.assertEquals(
			DateUtil.toUTCDate("2019-02-11T20:26:53.218Z"),
			bqMembershipChange.getJoinedDate());
		Assertions.assertEquals(
			"test2@liferay.com", bqMembershipChange.getIndividualEmail());
		Assertions.assertEquals(
			338486041327913341L, bqMembershipChange.getIndividualId());
		Assertions.assertEquals(1, bqMembershipChange.getIndividualsCount());
		Assertions.assertEquals(
			338511398116723458L, bqMembershipChange.getIndividualSegmentId());
		Assertions.assertEquals(
			1, bqMembershipChange.getKnownIndividualsCount());
		Assertions.assertEquals("REMOVED", bqMembershipChange.getOperation());

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
	@RepositoryResource(
		repositoryClass = BQMembershipRepository.class,
		resourcePath = "osbasahfaroinfo/bq_memberships.json"
	)
	@RepositoryResource(
		repositoryClass = BQMembershipChangeRepository.class,
		resourcePath = "osbasahfaroinfo/bq_membership_changes.json"
	)
	@Test
	public void testDeactivateMembershipWithoutKnownIndividuals() {
		Date deletionDate = DateUtil.toUTCDate("2019-02-11T20:26:53.215Z");

		_membershipDog.deactivateBQMembership(
			deletionDate, 338486037253283140L, 338511398116723458L);

		BQMembership bqMembership =
			_bqMembershipRepository.
				findByIndividualIdAndIndividualSegmentIdAndStatus(
					338486037253283140L, 338511398116723458L, "INACTIVE");

		Assertions.assertEquals(
			DateUtil.toUTCDate("2019-02-11T20:26:53.215Z"),
			bqMembership.getCreateDate());
		Assertions.assertEquals(
			DateUtil.toUTCDate("2019-02-11T20:26:53.215Z"),
			bqMembership.getModifiedDate());
		Assertions.assertEquals(
			DateUtil.toUTCDate("2019-02-11T20:26:53.215Z"),
			bqMembership.getRemovedDate());
		Assertions.assertEquals(338511398389279307L, bqMembership.getId());
		Assertions.assertEquals(
			338486037253283140L, bqMembership.getIndividualId());
		Assertions.assertEquals(
			338511398116723458L, bqMembership.getIndividualSegmentId());
		Assertions.assertEquals("INACTIVE", bqMembership.getStatus());
	}

	@ElasticsearchIndex(
		name = "individual-segments", resourcePath = "individual_segments.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@RepositoryResource(
		repositoryClass = BQMembershipRepository.class,
		resourcePath = "osbasahfaroinfo/bq_memberships.json"
	)
	@RepositoryResource(
		repositoryClass = BQMembershipChangeRepository.class,
		resourcePath = "osbasahfaroinfo/bq_membership_changes.json"
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

	@RepositoryResource(
		repositoryClass = BQMembershipRepository.class,
		resourcePath = "osbasahfaroinfo/bq_memberships.json"
	)
	@Test
	public void testIsMember() {
		Assertions.assertFalse(_membershipDog.isMember(0L, 0L));
		Assertions.assertTrue(
			_membershipDog.isMember(338486041327913341L, 338511398116723458L));
	}

	@Autowired
	private BQMembershipChangeRepository _bqMembershipChangeRepository;

	@Autowired
	private BQMembershipRepository _bqMembershipRepository;

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