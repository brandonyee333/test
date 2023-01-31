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
import com.liferay.osb.asah.common.entity.BQMembership;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.faro.info.dog.test.BaseFaroInfoDogTestCase;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.Individual;
import com.liferay.osb.asah.common.repository.BQIndividualRepository;
import com.liferay.osb.asah.common.repository.BQMembershipChangeRepository;
import com.liferay.osb.asah.common.repository.BQMembershipRepository;
import com.liferay.osb.asah.common.repository.SegmentRepository;
import com.liferay.osb.asah.test.util.annotation.RepositoryResource;
import com.liferay.osb.asah.test.util.annotation.SQLResource;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;

import org.json.JSONObject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Michael Bowerman
 * @author Vishal Reddy
 */
public class BQMembershipDogTest
	extends BaseFaroInfoDogTestCase
	implements OSBAsahTestExecutionListenersContext {

	@Disabled
	@Test
	public void testAddMembershipWithActiveStatusAndAnonymousIndividual() {
		Segment segment1 = new Segment();

		segment1.setId(234L);
		segment1.setIncludeAnonymousUsers(Boolean.TRUE);
		segment1.setIsNew(Boolean.TRUE);
		segment1.setStatus("ACTIVE");

		_segmentRepository.save(segment1);

		Segment segment2 = new Segment();

		segment2.setId(456L);
		segment2.setIncludeAnonymousUsers(Boolean.TRUE);
		segment2.setIsNew(Boolean.TRUE);
		segment2.setStatus("ACTIVE");

		_segmentRepository.save(segment2);

		Individual individual = new Individual();

		individual.setId("123");
		individual.setSegmentIds(Collections.singleton(456L));

		// TODO Add individual

		BQMembership bqMembership = new BQMembership();

		bqMembership.setCreateDate(
			DateUtil.toUTCDate("2019-02-11T20:27:36.603Z"));
		bqMembership.setIdentityId("123");
		bqMembership.setModifiedDate(
			DateUtil.toUTCDate("2019-02-11T20:27:36.603Z"));
		bqMembership.setSegmentId(234L);
		bqMembership.setStatus("ACTIVE");

		bqMembership = _bqMembershipDog.addBQMembership(bqMembership);

		Assertions.assertNotNull(bqMembership);

		List<BQMembership> bqMemberships =
			(List<BQMembership>)_bqMembershipRepository.findAll();

		Assertions.assertEquals(1, bqMemberships.size());

		JSONAssert.assertEquals(
			_objectMapper.convertValue(bqMembership, JSONObject.class),
			_objectMapper.convertValue(bqMemberships.get(0), JSONObject.class),
			false);

		Assertions.assertNotNull(bqMembership.getId());

		Set<Long> segmentIds = individual.getSegmentIds();

		MatcherAssert.assertThat(
			new Long[] {234L, 456L},
			Matchers.arrayContainingInAnyOrder(
				segmentIds.toArray(new Long[0])));
	}

	@Test
	public void testAddMembershipWithInactiveStatus() {
		BQMembership bqMembership = _bqMembershipDog.addBQMembership(
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
	@Test
	public void testDeleteBQMembership() {
		Assertions.assertEquals(
			3, _bqMembershipRepository.countBySegmentId(338511398116723458L));

		_bqMembershipRepository.countBySegmentId(338511398116723458L);
		_bqMembershipDog.deleteBQMembership(
			"abc338511398389279307", 338511398116723458L);
		Assertions.assertEquals(
			2, _bqMembershipRepository.countBySegmentId(338511398116723458L));
	}

	@Disabled
	@RepositoryResource(
		repositoryClass = BQIndividualRepository.class,
		resourcePath = "osbasahfaroinfo/individuals.json"
	)
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
	public void testGetIndividualSegmentIndividualIds() {
		List<String> identityIds = _bqMembershipDog.getActiveIdentityIds(
			338511398116723458L);

		Assertions.assertEquals(2, identityIds.size(), identityIds.toString());
		Assertions.assertTrue(identityIds.contains("338486037253283140"));
		Assertions.assertTrue(identityIds.contains("338486041327913341"));
	}

	@RepositoryResource(
		repositoryClass = BQMembershipRepository.class,
		resourcePath = "osbasahfaroinfo/bq_memberships.json"
	)
	@Test
	public void testIsMember() {
		Assertions.assertFalse(_bqMembershipDog.isMember("0", 0L));
		Assertions.assertTrue(
			_bqMembershipDog.isMember(
				"338486041327913341", 338511398116723458L));
	}

	@SQLResource(resourcePath = "test_bq_memberships.sql")
	@Test
	public void testUpdateBQMemberships() {
		_bqMembershipDog.updateBQMemberships(
			"contains(demographics/email/value, 'delta.com')", 1L);

		Assertions.assertEquals(1L, _bqMembershipDog.getBQMembershipsCount(1L));
	}

	@Autowired
	private BQMembershipDog _bqMembershipDog;

	@Autowired
	private BQMembershipRepository _bqMembershipRepository;

	@Autowired
	private ObjectMapper _objectMapper;

	@Autowired
	private SegmentRepository _segmentRepository;

}