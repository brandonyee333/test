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
import com.liferay.osb.asah.test.util.annotation.BQSQLResource;
import com.liferay.osb.asah.test.util.annotation.RepositoryResource;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;

import org.json.JSONObject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

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

		List<BQMembership> bqMemberships = _bqMembershipRepository.findAll();

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

	@Disabled
	@Test
	public void testAddMembershipWithInactiveStatus() {
		BQMembership bqMembership = _bqMembershipDog.addBQMembership(
			_objectMapper.convertValue(
				JSONUtil.put("status", "INACTIVE"), BQMembership.class));

		Assertions.assertNotNull(bqMembership);

		List<BQMembership> bqMemberships = _bqMembershipRepository.findAll();

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

	@BQSQLResource(resourcePath = "test_bq_active_memberships_count.sql")
	@Test
	public void testGetActiveDynamicMembershipsCountWithAnonymous() {
		Assertions.assertEquals(
			3, _bqMembershipDog.getActiveBQMembershipsCount(Boolean.TRUE, 2L));
	}

	@BQSQLResource(resourcePath = "test_bq_active_memberships_count.sql")
	@Test
	public void testGetActiveStaticMembershipsCount() {
		Assertions.assertEquals(
			2, _bqMembershipDog.getActiveBQMembershipsCount(Boolean.FALSE, 1L));
	}

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

	@BQSQLResource(resourcePath = "test_bq_memberships.sql")
	@Test
	public void testUpdateBQMemberships() {
		_bqMembershipDog.updateBQMemberships(
			1L, "contains(demographics/email/value, 'delta.com')", Boolean.TRUE,
			1L);

		Assertions.assertEquals(1L, _bqMembershipDog.getBQMembershipsCount(1L));

		_bqMembershipDog.updateBQMemberships(
			1L,
			"(organizations.filter(filter='(id eq ''23k92323l923lf0as'')'))",
			Boolean.TRUE, 1L);

		Assertions.assertEquals(1L, _bqMembershipDog.getBQMembershipsCount(1L));

		_bqMembershipDog.updateBQMemberships(
			1L, "userGroupIds eq 'newr87232kjhdsf89'", Boolean.TRUE, 1L);

		Assertions.assertEquals(1L, _bqMembershipDog.getBQMembershipsCount(1L));

		_bqMembershipDog.updateBQMemberships(
			1L, "groupIds ne '9823423jh23908234'", Boolean.TRUE, 1L);

		Assertions.assertEquals(2L, _bqMembershipDog.getBQMembershipsCount(1L));
	}

	@BQSQLResource(resourcePath = "test_bq_memberships_activities.sql")
	@Test
	public void testUpdateBQMembershipsWithActivities() {
		_bqMembershipDog.updateBQMemberships(
			1L,
			"(demographics/firstName/value eq 'Test1' and " +
				"(activities.filterByCount(filter='(applicationId eq " +
					"''Blog'' and eventId = ''blogClicked'' and id = " +
						"''1'')', operator='ge', value=1)))",
			Boolean.TRUE, 1L);

		Assertions.assertEquals(1L, _bqMembershipDog.getBQMembershipsCount(1L));

		_bqMembershipDog.updateBQMemberships(
			1L,
			"(demographics/firstName/value eq 'Test1' and " +
				"(activities.filterByCount(filter='(applicationId eq " +
					"''Blog'' and eventId eq ''blogClicked'' and id eq " +
						"''2'')', operator='ge', value=1)))",
			Boolean.TRUE, 1L);

		Assertions.assertEquals(0L, _bqMembershipDog.getBQMembershipsCount(1L));
	}

	@BQSQLResource(resourcePath = "test_bq_memberships_custom_fields.sql")
	@Test
	public void testUpdateBQMembershipsWithCustomFields() {
		_bqMembershipDog.updateBQMemberships(
			1L, "(custom/Organization/value eq 'Engineer')", Boolean.TRUE, 1L);

		Assertions.assertEquals(1L, _bqMembershipDog.getBQMembershipsCount(1L));
	}

	@BQSQLResource(resourcePath = "test_bq_memberships_2.sql")
	@Test
	public void testUpdateBQMembershipsWithDifferentChannelIds() {
		_bqMembershipDog.updateBQMemberships(
			1L,
			"(organizations.filter(filter='(id eq ''23k92323l923lf0as'')'))",
			Boolean.TRUE, 1L);

		Assertions.assertEquals(1L, _bqMembershipDog.getBQMembershipsCount(1L));
	}

	@BQSQLResource(resourcePath = "test_bq_memberships_with_interest.sql")
	@Test
	public void testUpdateBQMembershipsWithInterest1() {
		_bqMembershipDog.updateBQMemberships(
			1L,
			"(interests.filter(filter='(name eq ''analytics'' and score eq " +
				"''true'')'))",
			Boolean.TRUE, 2L);

		Assertions.assertEquals(1L, _bqMembershipDog.getBQMembershipsCount(2L));

		Page<BQMembership> bqMembershipPage =
			_bqMembershipDog.getBQMembershipPage(
				2L, null, 0, 20, new String[0]);

		List<BQMembership> bqMemberships = bqMembershipPage.getContent();

		Map<String, String> expectedIndividuals = new HashMap<>();

		expectedIndividuals.put(
			"abc-123",
			"761319ac0d9f6e0f3467ad26bc8c63989d06c5f491849d6aa12fabdbd6c6b7bb");

		_assertBQMemberships(bqMemberships, expectedIndividuals);

		_bqMembershipDog.updateBQMemberships(
			1L,
			"(interests.filter(filter='(name eq ''analytics'' and score eq " +
				"''false'')'))",
			Boolean.TRUE, 2L);

		Assertions.assertEquals(3L, _bqMembershipDog.getBQMembershipsCount(2L));

		bqMembershipPage = _bqMembershipDog.getBQMembershipPage(
			2L, null, 0, 20, new String[0]);

		bqMemberships = bqMembershipPage.getContent();

		expectedIndividuals = new HashMap<>();

		expectedIndividuals.put(
			"bcd-456",
			"5970d88ec4ed505177361de1b17a3f2debf7c4f630c14f075a823ec97942692a");
		expectedIndividuals.put(
			"efg-789",
			"5f20f61b2cfaa86c4f3cb3557751a702776af029deabed8e943fb55cfa604e34");
		expectedIndividuals.put(
			"ghi-101",
			"def73c7b1d2934d8bcdc8080a221c39df40e7ccfa499ad49d862138f5bc055f9");

		_assertBQMemberships(bqMemberships, expectedIndividuals);
	}

	@BQSQLResource(resourcePath = "test_bq_memberships_with_interest.sql")
	@Test
	public void testUpdateBQMembershipsWithInterest2() {
		_bqMembershipDog.updateBQMemberships(
			1L,
			"(interests.filter(filter='(name eq ''analytics'' and score eq " +
				"''true'')') and interests.filter(filter='(name eq ''cloud'' " +
					"and score eq ''true'')'))",
			Boolean.TRUE, 2L);

		Assertions.assertEquals(0L, _bqMembershipDog.getBQMembershipsCount(2L));

		_bqMembershipDog.updateBQMemberships(
			1L,
			"(interests.filter(filter='(name eq ''metrics'' and score eq " +
				"''true'')') and interests.filter(filter='(name eq " +
					"''quality'' and score eq ''true'')'))",
			Boolean.TRUE, 3L);

		Assertions.assertEquals(1L, _bqMembershipDog.getBQMembershipsCount(3L));

		Page<BQMembership> bqMembershipPage =
			_bqMembershipDog.getBQMembershipPage(
				3L, null, 0, 20, new String[0]);

		List<BQMembership> bqMemberships = bqMembershipPage.getContent();

		Map<String, String> expectedIndividuals = new HashMap<>();

		expectedIndividuals.put(
			"efg-789",
			"5f20f61b2cfaa86c4f3cb3557751a702776af029deabed8e943fb55cfa604e34");

		_assertBQMemberships(bqMemberships, expectedIndividuals);

		_bqMembershipDog.updateBQMemberships(
			1L,
			"(interests.filter(filter='(name eq ''analytics'' and score eq " +
				"''true'')') and interests.filter(filter='(name eq ''cloud'' " +
					"and score eq ''false'')'))",
			Boolean.TRUE, 4L);

		Assertions.assertEquals(1L, _bqMembershipDog.getBQMembershipsCount(4L));

		bqMembershipPage = _bqMembershipDog.getBQMembershipPage(
			4L, null, 0, 20, new String[0]);

		bqMemberships = bqMembershipPage.getContent();

		expectedIndividuals = new HashMap<>();

		expectedIndividuals.put(
			"abc-123",
			"761319ac0d9f6e0f3467ad26bc8c63989d06c5f491849d6aa12fabdbd6c6b7bb");

		_assertBQMemberships(bqMemberships, expectedIndividuals);
	}

	@BQSQLResource(resourcePath = "test_bq_memberships_with_interest.sql")
	@Test
	public void testUpdateBQMembershipsWithInterest3() {
		_bqMembershipDog.updateBQMemberships(
			1L,
			"(interests.filter(filter='(name eq ''analytics'' and score eq " +
				"''true'')') or interests.filter(filter='(name eq ''cloud'' " +
					"and score eq ''true'')'))",
			Boolean.TRUE, 2L);

		Assertions.assertEquals(2L, _bqMembershipDog.getBQMembershipsCount(2L));

		Page<BQMembership> bqMembershipPage =
			_bqMembershipDog.getBQMembershipPage(
				2L, null, 0, 20, new String[0]);

		List<BQMembership> bqMemberships = bqMembershipPage.getContent();

		Map<String, String> expectedIndividuals = new HashMap<>();

		expectedIndividuals.put(
			"abc-123",
			"761319ac0d9f6e0f3467ad26bc8c63989d06c5f491849d6aa12fabdbd6c6b7bb");
		expectedIndividuals.put(
			"bcd-456",
			"5970d88ec4ed505177361de1b17a3f2debf7c4f630c14f075a823ec97942692a");

		_assertBQMemberships(bqMemberships, expectedIndividuals);

		_bqMembershipDog.updateBQMemberships(
			1L,
			"(interests.filter(filter='(name eq ''analytics'' and score eq " +
				"''true'')') or interests.filter(filter='(name eq ''cloud'' " +
					"and score eq ''false'')'))",
			Boolean.TRUE, 3L);

		Assertions.assertEquals(3L, _bqMembershipDog.getBQMembershipsCount(3L));

		bqMembershipPage = _bqMembershipDog.getBQMembershipPage(
			3L, null, 0, 20, new String[0]);

		bqMemberships = bqMembershipPage.getContent();

		expectedIndividuals = new HashMap<>();

		expectedIndividuals.put(
			"abc-123",
			"761319ac0d9f6e0f3467ad26bc8c63989d06c5f491849d6aa12fabdbd6c6b7bb");
		expectedIndividuals.put(
			"efg-789",
			"5f20f61b2cfaa86c4f3cb3557751a702776af029deabed8e943fb55cfa604e34");
		expectedIndividuals.put(
			"ghi-101",
			"def73c7b1d2934d8bcdc8080a221c39df40e7ccfa499ad49d862138f5bc055f9");

		_assertBQMemberships(bqMemberships, expectedIndividuals);
	}

	@BQSQLResource(resourcePath = "test_bq_memberships_with_interest.sql")
	@Test
	public void testUpdateBQMembershipsWithInterest4() {
		_bqMembershipDog.updateBQMemberships(
			1L,
			"(not contains(demographics/email/value, 'gamma.com') and " +
				"(interests.filter(filter='(name eq ''analytics'' and score " +
					"eq ''true'')') or interests.filter(filter='(name eq " +
						"''cloud'' and score eq ''false'')')))",
			Boolean.TRUE, 2L);

		Assertions.assertEquals(2L, _bqMembershipDog.getBQMembershipsCount(2L));

		Page<BQMembership> bqMembershipPage =
			_bqMembershipDog.getBQMembershipPage(
				2L, null, 0, 20, new String[0]);

		List<BQMembership> bqMemberships = bqMembershipPage.getContent();

		Map<String, String> expectedIndividuals = new HashMap<>();

		expectedIndividuals.put(
			"abc-123",
			"761319ac0d9f6e0f3467ad26bc8c63989d06c5f491849d6aa12fabdbd6c6b7bb");
		expectedIndividuals.put(
			"efg-789",
			"5f20f61b2cfaa86c4f3cb3557751a702776af029deabed8e943fb55cfa604e34");

		_assertBQMemberships(bqMemberships, expectedIndividuals);
	}

	@BQSQLResource(resourcePath = "test_bq_memberships_sessions.sql")
	@Test
	public void testUpdateBQMembershipsWithSessions() {
		_bqMembershipDog.updateBQMemberships(
			1L,
			"(sessions.filter(filter='(contains(context/referrer, " +
				"''facebook.com'') and completeDate gt ''2022-09-01'')'))",
			Boolean.FALSE, 1L);

		Assertions.assertEquals(2L, _bqMembershipDog.getBQMembershipsCount(1L));

		_bqMembershipDog.updateBQMemberships(
			1L,
			"(sessions.filter(filter='(contains(context/referrer, " +
				"''facebook.com'') and completeDate gt ''2022-09-01'')'))",
			Boolean.TRUE, 1L);

		Assertions.assertEquals(3L, _bqMembershipDog.getBQMembershipsCount(1L));

		_bqMembershipDog.updateBQMemberships(
			1L,
			"(sessions.filter(filter='(context/referrer eq " +
				"''https://facebook.com'' and completeDate gt " +
					"''2022-09-01'')'))",
			Boolean.FALSE, 1L);

		Assertions.assertEquals(1L, _bqMembershipDog.getBQMembershipsCount(1L));

		_bqMembershipDog.updateBQMemberships(
			1L,
			"(sessions.filter(filter='(context/referrer eq " +
				"''https://facebook.com'' and completeDate gt " +
					"''2022-09-01'')'))",
			Boolean.TRUE, 2L);

		Assertions.assertEquals(1L, _bqMembershipDog.getBQMembershipsCount(1L));

		_bqMembershipDog.updateBQMemberships(
			1L,
			"(sessions.filter(filter='(contains(context/url, ''url2.com'') " +
				"and completeDate gt ''2022-09-01'')'))",
			Boolean.TRUE, 1L);

		Assertions.assertEquals(5L, _bqMembershipDog.getBQMembershipsCount(1L));

		_bqMembershipDog.updateBQMemberships(
			1L,
			"(sessions.filter(filter='(context/url eq ''https://url2.com'' " +
				"and completeDate gt ''2022-09-01'')'))",
			Boolean.FALSE, 1L);

		Assertions.assertEquals(2L, _bqMembershipDog.getBQMembershipsCount(1L));

		_bqMembershipDog.updateBQMemberships(
			1L,
			"(sessions.filter(filter='(context/browserName eq ''Chrome'')'))",
			Boolean.FALSE, 1L);

		Assertions.assertEquals(3L, _bqMembershipDog.getBQMembershipsCount(1L));

		_bqMembershipDog.updateBQMemberships(
			1L, "(sessions.filter(filter='(context/deviceType ne ''Phone'')'))",
			Boolean.TRUE, 1L);

		Assertions.assertEquals(3L, _bqMembershipDog.getBQMembershipsCount(1L));

		_bqMembershipDog.updateBQMemberships(
			1L,
			"(sessions.filter(filter='(context/platformName eq ''Linux'')'))",
			Boolean.FALSE, 1L);

		Assertions.assertEquals(2L, _bqMembershipDog.getBQMembershipsCount(1L));
	}

	private void _assertBQMemberships(
		List<BQMembership> bqMemberships,
		Map<String, String> expectedIndividuals) {

		for (BQMembership bqMembership : bqMemberships) {
			Assertions.assertEquals(
				expectedIndividuals.get(bqMembership.getIdentityId()),
				bqMembership.getIndividualId());

			expectedIndividuals.remove(bqMembership.getIdentityId());
		}

		Assertions.assertEquals(0, expectedIndividuals.size());
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