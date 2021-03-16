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
import com.liferay.osb.asah.common.dog.MembershipDog;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.faro.info.dog.test.BaseFaroInfoDogTestCase;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.Membership;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Michael Bowerman
 * @author Vishal Reddy
 */
@ContextConfiguration(classes = OSBAsahSpringBootApplication.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class MembershipDogTest extends BaseFaroInfoDogTestCase {

	@Test
	public void testAddMembershipWithActiveStatusAndAnonymousIndividual()
		throws Exception {

		faroInfoElasticsearchInvoker.add(
			"individuals",
			JSONUtil.put(
				"id", "123"
			).put(
				"individualSegmentIds", JSONUtil.put("456")
			));

		faroInfoElasticsearchInvoker.add(
			"individual-segments",
			JSONUtil.put(
				"id", "234"
			).put(
				"includeAnonymousUsers", true
			).put(
				"individualSegmentIds", JSONUtil.put("456")
			).put(
				"status", "ACTIVE"
			));

		JSONObject newMembershipJSONObject = JSONUtil.put(
			"dateCreated", "2019-02-11T20:27:36.603Z"
		).put(
			"dateModified", "2019-02-11T20:27:36.603Z"
		).put(
			"individualId", "123"
		).put(
			"individualSegmentId", "234"
		).put(
			"status", "ACTIVE"
		);

		Membership membership = _membershipDog.addMembership(
			_objectMapper.convertValue(
				newMembershipJSONObject, Membership.class));

		Assert.assertNotNull(membership);

		JSONArray membershipsJSONArray = faroInfoElasticsearchInvoker.get(
			"memberships");

		Assert.assertEquals(1, membershipsJSONArray.length());

		JSONAssert.assertEquals(
			newMembershipJSONObject, membershipsJSONArray.getJSONObject(0),
			false);

		Assert.assertNotNull(membership.getId());

		JSONAssert.assertEquals(
			JSONUtil.put(
				"id", "123"
			).put(
				"individualSegmentIds", JSONUtil.putAll("234", "456")
			),
			faroInfoElasticsearchInvoker.get("individuals", "123"), false);

		JSONArray membershipChangesJSONArray = faroInfoElasticsearchInvoker.get(
			"membership-changes");

		Assert.assertEquals(1, membershipChangesJSONArray.length());

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
	public void testAddMembershipWithInactiveStatus() throws Exception {
		Membership membership = _membershipDog.addMembership(
			_objectMapper.convertValue(
				JSONUtil.put("status", "INACTIVE"), Membership.class));

		Assert.assertNotNull(membership);

		JSONArray membershipsJSONArray = faroInfoElasticsearchInvoker.get(
			"memberships");

		Assert.assertEquals(1, membershipsJSONArray.length());

		JSONAssert.assertEquals(
			JSONUtil.put("status", "INACTIVE"),
			membershipsJSONArray.getJSONObject(0), false);

		Assert.assertNotNull(membership.getId());
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
	public void testDeactivateMembershipWithIndividuals() throws Exception {
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

		Assert.assertEquals(0, individualSegmentIdsJSONArray.length());
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
	public void testDeactivateMembershipWithoutKnownIndividuals()
		throws Exception {

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

		Assert.assertEquals(individualIds.toString(), 2, individualIds.size());
		Assert.assertTrue(individualIds.contains(338486037253283140L));
		Assert.assertTrue(individualIds.contains(338486041327913341L));
	}

	@ElasticsearchIndex(
		name = "memberships", resourcePath = "memberships.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testIsMember() {
		Assert.assertFalse(_membershipDog.isMember(0L, 0L));
		Assert.assertTrue(
			_membershipDog.isMember(338486041327913341L, 338511398116723458L));
	}

	@Autowired
	private MembershipDog _membershipDog;

	@Autowired
	private ObjectMapper _objectMapper;

}