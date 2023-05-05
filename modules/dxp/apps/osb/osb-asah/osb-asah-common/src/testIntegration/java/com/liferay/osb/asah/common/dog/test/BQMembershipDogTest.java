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

import com.liferay.osb.asah.common.dog.BQMembershipDog;
import com.liferay.osb.asah.common.entity.BQMembership;
import com.liferay.osb.asah.common.faro.info.dog.test.BaseFaroInfoDogTestCase;
import com.liferay.osb.asah.common.repository.BQIndividualRepository;
import com.liferay.osb.asah.common.repository.BQMembershipChangeRepository;
import com.liferay.osb.asah.common.repository.BQMembershipRepository;
import com.liferay.osb.asah.common.repository.SegmentRepository;
import com.liferay.osb.asah.test.util.annotation.BQSQLResource;
import com.liferay.osb.asah.test.util.annotation.RepositoryResource;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

/**
 * @author Michael Bowerman
 * @author Vishal Reddy
 */
public class BQMembershipDogTest
	extends BaseFaroInfoDogTestCase
	implements OSBAsahTestExecutionListenersContext {

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
			2, _bqMembershipDog.getActiveBQMembershipsCount(Boolean.TRUE, 2L));
	}

	@BQSQLResource(resourcePath = "test_bq_active_memberships_count.sql")
	@Test
	public void testGetActiveStaticMembershipsCount() {
		Assertions.assertEquals(
			1, _bqMembershipDog.getActiveBQMembershipsCount(Boolean.FALSE, 1L));
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
	public void testUpdateBQMembershipsWithActivities() {
		String assetId =
			"da70dfa4d9f95ac979f921e8e623358236313f334afcd06cddf8a5621cf6a1e9";

		_bqMembershipDog.updateBQMemberships(
			11L,
			"(activities.filterByCount(filter='(activityKey eq " +
				"''WebContent#webContentViewed#" + assetId + "'' and day eq " +
					"''2022-12-16'')', operator='ge', value=1))",
			Boolean.TRUE, 1L);

		Assertions.assertEquals(2L, _bqMembershipDog.getBQMembershipsCount(1L));

		_bqMembershipDog.updateBQMemberships(
			11L,
			"(not(activities.filterByCount(filter='(activityKey eq " +
				"''WebContent#webContentViewed#" + assetId + "'' and day eq " +
					"''2022-12-16'')', operator='ge', value=1)))",
			Boolean.TRUE, 1L);

		Assertions.assertEquals(2L, _bqMembershipDog.getBQMembershipsCount(1L));

		_bqMembershipDog.updateBQMemberships(
			11L,
			"(activities.filterByCount(filter='(activityKey eq " +
				"''WebContent#webContentViewed#" + assetId + "'' and day gt " +
					"''2022-12-17'')', operator='ge', value=1))",
			Boolean.TRUE, 1L);

		Assertions.assertEquals(0L, _bqMembershipDog.getBQMembershipsCount(1L));

		_bqMembershipDog.updateBQMemberships(
			11L,
			"(not(activities.filterByCount(filter='(activityKey eq " +
				"''WebContent#webContentViewed#" + assetId + "'' and day gt " +
					"''2022-12-17'')', operator='ge', value=1)))",
			Boolean.TRUE, 1L);

		Assertions.assertEquals(4L, _bqMembershipDog.getBQMembershipsCount(1L));

		_bqMembershipDog.updateBQMemberships(
			11L,
			"(activities.filterByCount(filter='(activityKey eq " +
				"''WebContent#webContentViewed#" + assetId + "'' and day lt " +
					"''2022-12-17'')', operator='ge', value=1))",
			Boolean.TRUE, 1L);

		Assertions.assertEquals(2L, _bqMembershipDog.getBQMembershipsCount(1L));

		_bqMembershipDog.updateBQMemberships(
			11L,
			"(not(activities.filterByCount(filter='(activityKey eq " +
				"''WebContent#webContentViewed#" + assetId + "'' and day lt " +
					"''2022-12-17'')', operator='ge', value=1)))",
			Boolean.TRUE, 1L);

		Assertions.assertEquals(2L, _bqMembershipDog.getBQMembershipsCount(1L));

		assetId =
			"0e12831a7047f759733b21f028525039607350b1b1b4fe904595427e72ea0d9b";

		_bqMembershipDog.updateBQMemberships(
			11L,
			"(activities.filterByCount(filter='(activityKey eq ''Blog#" +
				"commentPosted#" + assetId + "'' and day lt ''2022-12-17'')'" +
					", operator='ge', value=1))",
			Boolean.TRUE, 1L);

		Assertions.assertEquals(2L, _bqMembershipDog.getBQMembershipsCount(1L));
	}

	@BQSQLResource(resourcePath = "test_bq_memberships.sql")
	@Test
	public void testUpdateBQMembershipsWithCustomFields() {
		_bqMembershipDog.updateBQMemberships(
			11L, "contains(custom/Favorite_Food/value, 'Rice')", Boolean.TRUE,
			1L);

		Assertions.assertEquals(3L, _bqMembershipDog.getBQMembershipsCount(1L));

		_bqMembershipDog.updateBQMemberships(
			11L, "not contains(custom/Favorite_Food/value, 'Rice')",
			Boolean.TRUE, 1L);

		Assertions.assertEquals(1L, _bqMembershipDog.getBQMembershipsCount(1L));

		_bqMembershipDog.updateBQMemberships(
			11L, "contains(custom/Favorite_Number/value, 3)", Boolean.TRUE, 1L);

		Assertions.assertEquals(1L, _bqMembershipDog.getBQMembershipsCount(1L));

		_bqMembershipDog.updateBQMemberships(
			11L, "not contains(custom/Favorite_Number/value, 5)", Boolean.TRUE,
			1L);

		Assertions.assertEquals(1L, _bqMembershipDog.getBQMembershipsCount(1L));

		_bqMembershipDog.updateBQMemberships(
			11L, "custom/Favorite_Number/value ge 3", Boolean.TRUE, 1L);

		Assertions.assertEquals(3L, _bqMembershipDog.getBQMembershipsCount(1L));

		_bqMembershipDog.updateBQMemberships(
			11L,
			"custom/Favorite_Number/value gt 2 and " +
				"custom/Favorite_Number/value lt 3",
			Boolean.TRUE, 1L);

		Assertions.assertEquals(1L, _bqMembershipDog.getBQMembershipsCount(1L));

		_bqMembershipDog.updateBQMemberships(
			11L, "custom/Favorite_Number/value gt 3", Boolean.TRUE, 1L);

		Assertions.assertEquals(2L, _bqMembershipDog.getBQMembershipsCount(1L));

		_bqMembershipDog.updateBQMemberships(
			11L, "custom/Favorite_Number/value le 4", Boolean.TRUE, 1L);

		Assertions.assertEquals(3L, _bqMembershipDog.getBQMembershipsCount(1L));

		_bqMembershipDog.updateBQMemberships(
			11L, "custom/Favorite_Number/value lt 2", Boolean.TRUE, 1L);

		Assertions.assertEquals(1L, _bqMembershipDog.getBQMembershipsCount(1L));

		_bqMembershipDog.updateBQMemberships(
			11L, "custom/Hobbies/value eq 'ing'", Boolean.TRUE, 1L);

		Assertions.assertEquals(0L, _bqMembershipDog.getBQMembershipsCount(1L));

		_bqMembershipDog.updateBQMemberships(
			11L, "custom/Hobbies/value eq 'Exercise'", Boolean.TRUE, 1L);

		Assertions.assertEquals(3L, _bqMembershipDog.getBQMembershipsCount(1L));

		_bqMembershipDog.updateBQMemberships(
			11L, "custom/Hobbies/value ne 'Exercise'", Boolean.TRUE, 1L);

		Assertions.assertEquals(1L, _bqMembershipDog.getBQMembershipsCount(1L));

		_bqMembershipDog.updateBQMemberships(
			11L, "custom/Hobbies/value ne 'ing'", Boolean.TRUE, 1L);

		Assertions.assertEquals(4L, _bqMembershipDog.getBQMembershipsCount(1L));

		_bqMembershipDog.updateBQMemberships(
			11L, "custom/Salary/value ge 120000.30", Boolean.TRUE, 1L);

		Assertions.assertEquals(1L, _bqMembershipDog.getBQMembershipsCount(1L));

		_bqMembershipDog.updateBQMemberships(
			11L, "custom/Salary/value gt 100000", Boolean.TRUE, 1L);

		Assertions.assertEquals(3L, _bqMembershipDog.getBQMembershipsCount(1L));

		_bqMembershipDog.updateBQMemberships(
			11L, "custom/Salary/value le 100000.00", Boolean.TRUE, 1L);

		Assertions.assertEquals(0L, _bqMembershipDog.getBQMembershipsCount(1L));

		_bqMembershipDog.updateBQMemberships(
			11L, "custom/Salary/value lt 100001", Boolean.TRUE, 1L);

		Assertions.assertEquals(2L, _bqMembershipDog.getBQMembershipsCount(1L));
	}

	@BQSQLResource(resourcePath = "test_bq_memberships.sql")
	@Test
	public void testUpdateBQMembershipsWithDateCustomFields() {
		_bqMembershipDog.updateBQMemberships(
			11L, "custom/Joined_Date/value eq '2022-04-30'", Boolean.TRUE, 1L);

		Assertions.assertEquals(2L, _bqMembershipDog.getBQMembershipsCount(1L));

		_bqMembershipDog.updateBQMemberships(
			11L, "custom/Joined_Date/value ge '2022-04-30'", Boolean.TRUE, 1L);

		Assertions.assertEquals(3L, _bqMembershipDog.getBQMembershipsCount(1L));

		_bqMembershipDog.updateBQMemberships(
			11L, "custom/Joined_Date/value gt '2022-01-01'", Boolean.TRUE, 1L);

		Assertions.assertEquals(4L, _bqMembershipDog.getBQMembershipsCount(1L));

		_bqMembershipDog.updateBQMemberships(
			11L, "custom/Joined_Date/value le '2022-06-01'", Boolean.TRUE, 1L);

		Assertions.assertEquals(4L, _bqMembershipDog.getBQMembershipsCount(1L));

		_bqMembershipDog.updateBQMemberships(
			11L, "custom/Joined_Date/value lt '2022-05-03'", Boolean.TRUE, 1L);

		Assertions.assertEquals(3L, _bqMembershipDog.getBQMembershipsCount(1L));
	}

	@BQSQLResource(resourcePath = "test_bq_memberships.sql")
	@Test
	public void testUpdateBQMembershipsWithDemographics() {
		_bqMembershipDog.updateBQMemberships(
			11L, "contains(demographics/email/value, 'delta.com')",
			Boolean.TRUE, 1L);

		Assertions.assertEquals(1L, _bqMembershipDog.getBQMembershipsCount(1L));

		_bqMembershipDog.updateBQMemberships(
			11L, "not(contains(demographics/email/value, 'delta.com'))",
			Boolean.TRUE, 1L);

		Assertions.assertEquals(3L, _bqMembershipDog.getBQMembershipsCount(1L));

		_bqMembershipDog.updateBQMemberships(
			11L, "(demographics/firstName/value eq 'Marcus')", Boolean.TRUE,
			1L);

		Assertions.assertEquals(2L, _bqMembershipDog.getBQMembershipsCount(1L));

		_bqMembershipDog.updateBQMemberships(
			11L, "(demographics/jobTitle/value eq 'Engineer')", Boolean.TRUE,
			1L);

		Assertions.assertEquals(2L, _bqMembershipDog.getBQMembershipsCount(1L));
	}

	@BQSQLResource(resourcePath = "test_bq_memberships_2.sql")
	@Test
	public void testUpdateBQMembershipsWithDifferentChannelIds() {
		_bqMembershipDog.updateBQMemberships(
			1L,
			"(organizations.filter(filter='(id eq ''23k92323l923lf0as'')'))",
			Boolean.TRUE, 1L);

		Assertions.assertEquals(1L, _bqMembershipDog.getBQMembershipsCount(1L));

		_bqMembershipDog.updateBQMemberships(
			1L, "custom/Organization/value eq 'Developer'", Boolean.TRUE, 1L);

		Assertions.assertEquals(1L, _bqMembershipDog.getBQMembershipsCount(1L));

		_bqMembershipDog.updateBQMemberships(
			2L, "custom/Organization/value eq 'Developer'", Boolean.TRUE, 2L);

		Assertions.assertEquals(1L, _bqMembershipDog.getBQMembershipsCount(2L));
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

	@BQSQLResource(resourcePath = "test_bq_memberships.sql")
	@Test
	public void testUpdateBQMembershipsWithMemberships() {
		_bqMembershipDog.updateBQMemberships(
			11L, "groupIds ne '9823423jh23908234'", Boolean.TRUE, 1L);

		Assertions.assertEquals(2L, _bqMembershipDog.getBQMembershipsCount(1L));

		_bqMembershipDog.updateBQMemberships(
			11L, "roleIds eq '32oiaejf8e32433wr'", Boolean.TRUE, 1L);

		Assertions.assertEquals(1L, _bqMembershipDog.getBQMembershipsCount(1L));

		_bqMembershipDog.updateBQMemberships(
			11L, "roleIds ne '32oiaejf8e32433wr'", Boolean.TRUE, 1L);

		Assertions.assertEquals(3L, _bqMembershipDog.getBQMembershipsCount(1L));

		_bqMembershipDog.updateBQMemberships(
			11L, "userGroupIds eq 'newr87232kjhdsf89'", Boolean.TRUE, 1L);

		Assertions.assertEquals(1L, _bqMembershipDog.getBQMembershipsCount(1L));
	}

	@BQSQLResource(resourcePath = "test_bq_memberships.sql")
	@Test
	public void testUpdateBQMembershipsWithOrganization() {

		// Custom fields

		_bqMembershipDog.updateBQMemberships(
			11L,
			"organizations.filter(filter='(custom/Organization_Type/value eq " +
				"''test'')')",
			Boolean.TRUE, 1L);

		Assertions.assertEquals(1L, _bqMembershipDog.getBQMembershipsCount(1L));

		_bqMembershipDog.updateBQMemberships(
			11L,
			"organizations.filter(filter='(custom/Divisions/value ge 35)')",
			Boolean.TRUE, 1L);

		Assertions.assertEquals(1L, _bqMembershipDog.getBQMembershipsCount(1L));

		_bqMembershipDog.updateBQMemberships(
			11L,
			"organizations.filter(filter='(custom/Divisions/value gt 35)')",
			Boolean.TRUE, 1L);

		Assertions.assertEquals(0L, _bqMembershipDog.getBQMembershipsCount(1L));

		_bqMembershipDog.updateBQMemberships(
			11L, "organizations.filter(filter='(custom/Year/value le 2023)')",
			Boolean.TRUE, 1L);

		Assertions.assertEquals(1L, _bqMembershipDog.getBQMembershipsCount(1L));

		_bqMembershipDog.updateBQMemberships(
			11L, "organizations.filter(filter='(custom/Year/value lt 2023)')",
			Boolean.TRUE, 1L);

		Assertions.assertEquals(0L, _bqMembershipDog.getBQMembershipsCount(1L));

		// Hierarchy path known/unknown

		_bqMembershipDog.updateBQMemberships(
			11L, "(organizations.filter(filter='(hierarchyPath ne null)'))",
			Boolean.TRUE, 1L);

		Assertions.assertEquals(1L, _bqMembershipDog.getBQMembershipsCount(1L));

		_bqMembershipDog.updateBQMemberships(
			11L, "(organizations.filter(filter='(hierarchyPath eq null)'))",
			Boolean.TRUE, 1L);

		Assertions.assertEquals(3L, _bqMembershipDog.getBQMembershipsCount(1L));

		// Id

		_bqMembershipDog.updateBQMemberships(
			11L,
			"(organizations.filter(filter='(id eq ''23k92323l923lf0as'')'))",
			Boolean.TRUE, 1L);

		Assertions.assertEquals(1L, _bqMembershipDog.getBQMembershipsCount(1L));

		// Modified date

		_bqMembershipDog.updateBQMemberships(
			11L,
			"organizations.filter(filter='(modifiedDate eq ''2022-12-18'')')",
			Boolean.TRUE, 1L);

		Assertions.assertEquals(1L, _bqMembershipDog.getBQMembershipsCount(1L));

		_bqMembershipDog.updateBQMemberships(
			11L,
			"organizations.filter(filter='(modifiedDate gt ''2022-12-17'')')",
			Boolean.TRUE, 1L);

		Assertions.assertEquals(1L, _bqMembershipDog.getBQMembershipsCount(1L));

		_bqMembershipDog.updateBQMemberships(
			11L,
			"organizations.filter(filter='(modifiedDate lt ''2022-12-17'')')",
			Boolean.TRUE, 1L);

		Assertions.assertEquals(0L, _bqMembershipDog.getBQMembershipsCount(1L));

		// Name

		_bqMembershipDog.updateBQMemberships(
			11L, "organizations.filter(filter='(name eq ''Organization 1'')')",
			Boolean.TRUE, 1L);

		Assertions.assertEquals(1L, _bqMembershipDog.getBQMembershipsCount(1L));

		_bqMembershipDog.updateBQMemberships(
			11L, "organizations.filter(filter='(name ne ''Organization 1'')')",
			Boolean.TRUE, 1L);

		Assertions.assertEquals(0L, _bqMembershipDog.getBQMembershipsCount(1L));
	}

	@BQSQLResource(resourcePath = "test_bq_memberships_sessions.sql")
	@Test
	public void testUpdateBQMembershipsWithSessions() {
		_bqMembershipDog.updateBQMemberships(
			1L,
			"(sessions.filter(filter='(contains(context/referrer, " +
				"''facebook.com'') and between(completeDate, ''2022-08-30'', " +
					"''2050-09-02''))'))",
			Boolean.FALSE, 1L);

		Assertions.assertEquals(2L, _bqMembershipDog.getBQMembershipsCount(1L));

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

}