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

package com.liferay.osb.asah.batch.curator.bot.nanite.test;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.batch.curator.bot.nanite.UpdateDynamicMembershipsNanite;
import com.liferay.osb.asah.batch.curator.spring.OSBAsahBatchCuratorSpringBootApplication;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.MembershipDog;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoIndividualDog;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.Membership;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import java.util.List;

import org.apache.commons.lang3.RandomUtils;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Vishal Reddy
 * @author Rachael Koestartyo
 * @author Michael Bowerman
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OSBAsahBatchCuratorSpringBootApplication.class)
public class UpdateDynamicMembershipsNaniteTest extends BaseNaniteTestCase {

	@Test
	public void test() throws Exception {
		JSONObject newIndividualSegmentJSONObject =
			faroInfoElasticsearchInvoker.add(
				"individual-segments",
				FaroInfoTestUtil.buildDynamicIndividualSegmentJSONObject(
					"1", "id gt '0'"));

		_updateDynamicMembershipsNanite.run(newIndividualSegmentJSONObject);

		JSONObject individualSegmentJSONObject =
			faroInfoElasticsearchInvoker.fetch(
				"individual-segments",
				newIndividualSegmentJSONObject.getString("id"));

		Assert.assertEquals(
			"READY", individualSegmentJSONObject.getString("state"));
	}

	@Test
	public void testBehavioralCriteria() throws Exception {
		JSONObject dataSourceJSONObject = faroInfoElasticsearchInvoker.add(
			"data-sources",
			FaroInfoTestUtil.buildLiferayDataSourceJSONObject());

		JSONObject individual1JSONObject = faroInfoElasticsearchInvoker.add(
			"individuals",
			FaroInfoTestUtil.buildIndividualJSONObject(
				"1", dataSourceJSONObject));
		JSONObject individual2JSONObject = faroInfoElasticsearchInvoker.add(
			"individuals",
			FaroInfoTestUtil.buildIndividualJSONObject(
				"1", dataSourceJSONObject));

		String dataSourceId = dataSourceJSONObject.getString("id");

		JSONObject assetJSONObject = faroInfoElasticsearchInvoker.add(
			"assets", FaroInfoTestUtil.buildPageAssetJSONObject(dataSourceId));

		JSONObject activityGroup1JSONObject = faroInfoElasticsearchInvoker.add(
			"activity-groups",
			FaroInfoTestUtil.buildActivityGroupJSONObject(
				dataSourceId, individual1JSONObject));
		JSONObject activityGroup2JSONObject = faroInfoElasticsearchInvoker.add(
			"activity-groups",
			FaroInfoTestUtil.buildActivityGroupJSONObject(
				dataSourceId, individual2JSONObject));

		faroInfoElasticsearchInvoker.add(
			"activities",
			FaroInfoTestUtil.buildActivityJSONObject(
				activityGroup1JSONObject, assetJSONObject, "pageViewed",
				new String[0]));
		faroInfoElasticsearchInvoker.add(
			"activities",
			FaroInfoTestUtil.buildActivityJSONObject(
				activityGroup2JSONObject, assetJSONObject, "pageUnloaded",
				new String[] {"viewDuration", "1000"}));

		Long individualSegmentId = _updateDynamicMemberships(
			"(((activities/ever eq 'Page#pageViewed#" +
				assetJSONObject.getString("id") + "')))");

		Long individual1Id = individual1JSONObject.getLong("id");
		Long individual2Id = individual2JSONObject.getLong("id");

		Assert.assertTrue(
			_membershipDog.isMember(individual1Id, individualSegmentId));
		Assert.assertFalse(
			_membershipDog.isMember(individual2Id, individualSegmentId));

		individualSegmentId = _updateDynamicMemberships(
			"(((activities/ever ne 'Page#pageViewed#" +
				assetJSONObject.getString("id") + "')))");

		Assert.assertFalse(
			_membershipDog.isMember(individual1Id, individualSegmentId));
		Assert.assertTrue(
			_membershipDog.isMember(individual2Id, individualSegmentId));
	}

	@Test
	public void testIndividualSegmentState() throws Exception {
		JSONObject individualSegmentJSONObject =
			faroInfoElasticsearchInvoker.add(
				"individual-segments",
				FaroInfoTestUtil.buildDynamicIndividualSegmentJSONObject(""));

		_updateDynamicMembershipsNanite.run(
			_getContextJSONObject(individualSegmentJSONObject));

		individualSegmentJSONObject = faroInfoElasticsearchInvoker.fetch(
			"individual-segments", individualSegmentJSONObject.getString("id"));

		Assert.assertEquals(
			"READY", individualSegmentJSONObject.getString("state"));
	}

	@Test
	public void testInterestCriteria() throws Exception {
		JSONObject dataSourceJSONObject = faroInfoElasticsearchInvoker.add(
			"data-sources",
			FaroInfoTestUtil.buildLiferayDataSourceJSONObject());

		JSONObject individual1JSONObject = faroInfoElasticsearchInvoker.add(
			"individuals",
			FaroInfoTestUtil.buildIndividualJSONObject(
				"1", dataSourceJSONObject));
		JSONObject individual2JSONObject = faroInfoElasticsearchInvoker.add(
			"individuals",
			FaroInfoTestUtil.buildIndividualJSONObject(
				"1", dataSourceJSONObject));
		JSONObject individual3JSONObject = faroInfoElasticsearchInvoker.add(
			"individuals",
			FaroInfoTestUtil.buildIndividualJSONObject(
				"1", dataSourceJSONObject));

		String dataSourceId = dataSourceJSONObject.getString("id");

		JSONObject assetJSONObject = faroInfoElasticsearchInvoker.add(
			"assets", FaroInfoTestUtil.buildPageAssetJSONObject(dataSourceId));

		String dayDateString = DateUtil.newDayDateString();

		Long individual1Id = individual1JSONObject.getLong("id");
		Long individual2Id = individual2JSONObject.getLong("id");

		_addIndividualInterests(
			dayDateString,
			FaroInfoTestUtil.buildIndividualInterestsJSONArray(
				assetJSONObject, dayDateString, String.valueOf(individual1Id),
				0.5, 20),
			FaroInfoTestUtil.buildIndividualInterestsJSONArray(
				assetJSONObject, dayDateString, String.valueOf(individual2Id),
				0.1, 4));

		JSONArray keywordsJSONArray = assetJSONObject.getJSONArray("keywords");

		JSONObject keywordJSONObject = keywordsJSONArray.getJSONObject(
			RandomUtils.nextInt(0, keywordsJSONArray.length()));

		String keyword = keywordJSONObject.getString("keyword");

		faroInfoElasticsearchInvoker.add(
			"OSBAsahMarkers",
			JSONUtil.put(
				"id", "InterestThresholdScoreNanite"
			).put(
				"lastSuccessfulDay", dayDateString
			).put(
				"score", 0.3
			)
		).put(
			"type", "nanite"
		);

		Long individualSegmentId = _updateDynamicMemberships(
			"interests.filter(filter='(name eq ''" + keyword + "'') and " +
				"(score eq ''true'')')");

		Long individual3Id = individual3JSONObject.getLong("id");

		Assert.assertTrue(
			_membershipDog.isMember(individual1Id, individualSegmentId));
		Assert.assertFalse(
			_membershipDog.isMember(individual2Id, individualSegmentId));
		Assert.assertFalse(
			_membershipDog.isMember(individual3Id, individualSegmentId));

		individualSegmentId = _updateDynamicMemberships(
			"interests.filter(filter='(name eq ''" + keyword + "'') and " +
				"(score eq ''false'')')");

		Assert.assertFalse(
			_membershipDog.isMember(individual1Id, individualSegmentId));
		Assert.assertTrue(
			_membershipDog.isMember(individual2Id, individualSegmentId));
		Assert.assertTrue(
			_membershipDog.isMember(individual3Id, individualSegmentId));
	}

	@Test
	public void testKeywordInterestMembership() throws Exception {
		JSONObject dataSourceJSONObject = faroInfoElasticsearchInvoker.add(
			"data-sources",
			FaroInfoTestUtil.buildLiferayDataSourceJSONObject());

		JSONObject assetJSONObject = faroInfoElasticsearchInvoker.add(
			"assets",
			FaroInfoTestUtil.buildPageAssetJSONObject(
				dataSourceJSONObject.getString("id"),
				JSONUtil.put(
					JSONUtil.put(
						"keyword", "test"
					).put(
						"type", "keyword"
					))));

		String dayDateString = DateUtil.newDayDateString();

		JSONObject individual1JSONObject = faroInfoElasticsearchInvoker.add(
			"individuals",
			FaroInfoTestUtil.buildIndividualJSONObject(
				"1", dataSourceJSONObject));
		JSONObject individual2JSONObject = faroInfoElasticsearchInvoker.add(
			"individuals",
			FaroInfoTestUtil.buildIndividualJSONObject(
				"1", dataSourceJSONObject));

		Long individual1Id = individual1JSONObject.getLong("id");
		Long individual2Id = individual2JSONObject.getLong("id");

		_addIndividualInterests(
			dayDateString,
			FaroInfoTestUtil.buildIndividualInterestsJSONArray(
				assetJSONObject, dayDateString, String.valueOf(individual1Id),
				0.5, 20),
			FaroInfoTestUtil.buildIndividualInterestsJSONArray(
				assetJSONObject, dayDateString, String.valueOf(individual2Id),
				0.1, 4));

		faroInfoElasticsearchInvoker.add(
			"OSBAsahMarkers",
			JSONUtil.put(
				"id", "InterestThresholdScoreNanite"
			).put(
				"lastSuccessfulDay", dayDateString
			).put(
				"score", 0.3
			)
		).put(
			"type", "nanite"
		);

		Long individualSegmentId = _updateDynamicMemberships(
			"interests.filter(filter='(name eq ''test'') and (score eq " +
				"''false'')')");

		Assert.assertFalse(
			_membershipDog.isMember(individual1Id, individualSegmentId));
		Assert.assertTrue(
			_membershipDog.isMember(individual2Id, individualSegmentId));

		individualSegmentId = _updateDynamicMemberships(
			"interests.filter(filter='(name eq ''test'') and (score eq " +
				"''true'')')");

		Assert.assertTrue(
			_membershipDog.isMember(individual1Id, individualSegmentId));
		Assert.assertFalse(
			_membershipDog.isMember(individual2Id, individualSegmentId));
	}

	@Test
	public void testKeywordInterestMembershipNoThreshold() throws Exception {
		JSONObject dataSourceJSONObject = faroInfoElasticsearchInvoker.add(
			"data-sources",
			FaroInfoTestUtil.buildLiferayDataSourceJSONObject());

		JSONObject individualJSONObject = faroInfoElasticsearchInvoker.add(
			"individuals",
			FaroInfoTestUtil.buildIndividualJSONObject(dataSourceJSONObject));

		JSONObject assetJSONObject = faroInfoElasticsearchInvoker.add(
			"assets",
			FaroInfoTestUtil.buildPageAssetJSONObject(
				dataSourceJSONObject.getString("id"),
				JSONUtil.put(
					JSONUtil.put(
						"keyword", "test"
					).put(
						"type", "keyword"
					))));

		String dayDateString = DateUtil.newDayDateString();

		_addIndividualInterests(
			dayDateString,
			FaroInfoTestUtil.buildIndividualInterestsJSONArray(
				assetJSONObject, dayDateString,
				individualJSONObject.getString("id"), 0.5, 20));

		Long individualSegmentId = _updateDynamicMemberships(
			"interests.filter(filter='(name eq ''test'') and (score eq " +
				"''true'')')");

		Assert.assertNull(
			"No memberships should be added if no interest threshold exists",
			faroInfoElasticsearchInvoker.fetch(
				"memberships",
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery(
						"individualSegmentId",
						String.valueOf(individualSegmentId)))));
	}

	@Test
	public void testOrganizationAssociationMembership() throws Exception {
		JSONObject dataSourceJSONObject = faroInfoElasticsearchInvoker.add(
			"data-sources",
			FaroInfoTestUtil.buildLiferayDataSourceJSONObject());

		JSONObject organizationJSONObject = faroInfoElasticsearchInvoker.add(
			"organizations",
			JSONUtil.put(
				"dataSourceId", dataSourceJSONObject.getString("id")
			).put(
				"name", "engineering"
			).put(
				"organizationPK", "33120"
			));

		JSONObject individualJSONObject = faroInfoElasticsearchInvoker.add(
			"individuals",
			FaroInfoTestUtil.buildIndividualJSONObject(
				"1", dataSourceJSONObject
			).put(
				"organizationIds",
				JSONUtil.put(organizationJSONObject.getString("id"))
			));

		JSONObject individualSegmentJSONObject =
			faroInfoElasticsearchInvoker.add(
				"individual-segments",
				FaroInfoTestUtil.buildDynamicIndividualSegmentJSONObject(
					"1",
					"((organizations.filter(filter='(id eq ''" +
						organizationJSONObject.getString("id") + "'')')))"
				).put(
					"referencedOrganizationIds",
					JSONUtil.put(organizationJSONObject.getString("id"))
				));

		Assert.assertFalse(
			_membershipDog.isMember(
				individualJSONObject.getLong("id"),
				individualSegmentJSONObject.getLong("id")));

		JSONObject contextJSONObject = JSONUtil.put(
			"addQueryBuilder",
			QueryBuilders.termsQuery(
				"referencedOrganizationIds",
				organizationJSONObject.getString("id")
			).toString()
		).put(
			"dateModified", DateUtil.newDateString()
		);

		_updateDynamicMembershipsNanite.run(contextJSONObject);

		Assert.assertTrue(
			_membershipDog.isMember(
				individualJSONObject.getLong("id"),
				individualSegmentJSONObject.getLong("id")));

		individualJSONObject = faroInfoElasticsearchInvoker.update(
			"individuals",
			individualJSONObject.put("organizationIds", new JSONArray()));

		contextJSONObject = JSONUtil.put(
			"dateModified", DateUtil.newDateString()
		).put(
			"removeQueryBuilder",
			QueryBuilders.termsQuery(
				"referencedOrganizationIds",
				organizationJSONObject.getString("id")
			).toString()
		);

		_updateDynamicMembershipsNanite.run(contextJSONObject);

		Assert.assertFalse(
			_membershipDog.isMember(
				individualJSONObject.getLong("id"),
				individualSegmentJSONObject.getLong("id")));
	}

	@Test
	public void testRemoveMembershipsWithDifferentChannel() throws Exception {
		JSONObject dataSourceJSONObject = faroInfoElasticsearchInvoker.add(
			"data-sources",
			FaroInfoTestUtil.buildLiferayDataSourceJSONObject());

		JSONObject individualSegmentJSONObject =
			faroInfoElasticsearchInvoker.add(
				"individual-segments",
				FaroInfoTestUtil.buildDynamicIndividualSegmentJSONObject(
					"1", "id gt '0'"));

		JSONObject individual1JSONObject = _faroInfoIndividualDog.addIndividual(
			FaroInfoTestUtil.buildIndividualJSONObject(
				"1", dataSourceJSONObject),
			false);
		JSONObject individual2JSONObject = _faroInfoIndividualDog.addIndividual(
			FaroInfoTestUtil.buildIndividualJSONObject(
				"2", dataSourceJSONObject),
			false);

		_membershipDog.addMembership(
			_objectMapper.convertValue(
				FaroInfoTestUtil.buildMembershipJSONObject(
					individual1JSONObject.getString("id"),
					individualSegmentJSONObject.getString("id")),
				Membership.class));
		_membershipDog.addMembership(
			_objectMapper.convertValue(
				FaroInfoTestUtil.buildMembershipJSONObject(
					individual2JSONObject.getString("id"),
					individualSegmentJSONObject.getString("id")),
				Membership.class));

		_updateDynamicMembershipsNanite.run(individualSegmentJSONObject);

		List<Long> individualIds = _membershipDog.getActiveIndividualIds(
			individualSegmentJSONObject.getLong("id"));

		Assert.assertEquals(individualIds.toString(), 1, individualIds.size());
	}

	@Test
	public void testRoleAssociationMembership() throws Exception {
		JSONObject dataSourceJSONObject = faroInfoElasticsearchInvoker.add(
			"data-sources",
			FaroInfoTestUtil.buildLiferayDataSourceJSONObject());

		JSONObject roleJSONObject = dxpRawElasticsearchInvoker.add(
			"roles",
			JSONUtil.put(
				"name", "Administrator"
			).put(
				"osbAsahDataSourceId", dataSourceJSONObject.getString("id")
			).put(
				"roleId", "33120"
			));

		JSONObject individualJSONObject = faroInfoElasticsearchInvoker.add(
			"individuals",
			FaroInfoTestUtil.buildIndividualJSONObject(
				"1", dataSourceJSONObject
			).put(
				"roleIds", JSONUtil.put(roleJSONObject.getString("id"))
			));

		JSONObject individualSegmentJSONObject =
			faroInfoElasticsearchInvoker.add(
				"individual-segments",
				FaroInfoTestUtil.buildDynamicIndividualSegmentJSONObject(
					"1",
					"((roleIds eq '" + roleJSONObject.getString("id") + "'))"
				).put(
					"referencedRoleIds",
					JSONUtil.put(roleJSONObject.getString("id"))
				));

		Assert.assertFalse(
			_membershipDog.isMember(
				individualJSONObject.getLong("id"),
				individualSegmentJSONObject.getLong("id")));

		JSONObject contextJSONObject = JSONUtil.put(
			"addQueryBuilder",
			QueryBuilders.termsQuery(
				"referencedRoleIds", roleJSONObject.getString("id")
			).toString()
		).put(
			"dateModified", DateUtil.newDateString()
		);

		_updateDynamicMembershipsNanite.run(contextJSONObject);

		Assert.assertTrue(
			_membershipDog.isMember(
				individualJSONObject.getLong("id"),
				individualSegmentJSONObject.getLong("id")));

		individualJSONObject = faroInfoElasticsearchInvoker.update(
			"individuals",
			individualJSONObject.put("roleIds", new JSONArray()));

		contextJSONObject = JSONUtil.put(
			"dateModified", DateUtil.newDateString()
		).put(
			"removeQueryBuilder",
			QueryBuilders.termsQuery(
				"referencedRoleIds", roleJSONObject.getString("id")
			).toString()
		);

		_updateDynamicMembershipsNanite.run(contextJSONObject);

		Assert.assertFalse(
			_membershipDog.isMember(
				individualJSONObject.getLong("id"),
				individualSegmentJSONObject.getLong("id")));
	}

	@Test
	public void testSalesforceAccountMemberships() throws Exception {
		faroInfoElasticsearchInvoker.add(
			"accounts",
			JSONUtil.put(
				"accountPK", "345"
			).put(
				"dataSourceId", "123"
			).put(
				"id", "234"
			));

		faroInfoElasticsearchInvoker.add(
			"individuals",
			JSONUtil.put(
				JSONUtil.put(
					"channelIds", JSONUtil.put("1")
				).put(
					"dataSourceAccountPKs",
					JSONUtil.put(
						"accountPKs", JSONUtil.put("345")
					).put(
						"dataSourceId", "123"
					)
				).put(
					"demographics",
					JSONUtil.put(
						"email",
						JSONUtil.put(
							JSONUtil.put(
								"context", "demographics"
							).put(
								"dataSourceId", "123"
							).put(
								"dataSourceName", "test"
							).put(
								"dateModified", DateUtil.newDateString()
							).put(
								"fieldType", "Text"
							).put(
								"name", "email"
							).put(
								"ownerId", "123"
							).put(
								"ownerType", "individual"
							).put(
								"sourceName", "email"
							).put(
								"value", RandomTestUtil.randomEmailAddress()
							)))
				).put(
					"individualSegmentIds", new JSONArray()
				)));

		_updateDynamicMemberships(
			"(((dataSourceAccountPKs/accountPKs eq '345')))");

		JSONArray membershipsJSONArray = faroInfoElasticsearchInvoker.get(
			"memberships");

		Assert.assertEquals(1, membershipsJSONArray.length());

		JSONObject membershipJSONObject = membershipsJSONArray.getJSONObject(0);

		JSONObject individualJSONObject = faroInfoElasticsearchInvoker.fetch(
			"individuals", membershipJSONObject.getString("individualId"));

		JSONAssert.assertEquals(
			JSONUtil.put(
				"accountPKs", JSONUtil.put("345")
			).put(
				"dataSourceId", "123"
			),
			individualJSONObject.getJSONObject("dataSourceAccountPKs"), true);
	}

	private void _addIndividualInterests(
		String dayDateString, JSONArray... individualInterestsJSONArrays) {

		JSONArray jsonArray = new JSONArray();

		for (JSONArray individualInterestsJSONArray :
				individualInterestsJSONArrays) {

			for (Object value : individualInterestsJSONArray) {
				jsonArray.put(value);
			}
		}

		faroInfoElasticsearchInvoker.add("interests", jsonArray);

		faroInfoElasticsearchInvoker.add(
			"OSBAsahMarkers",
			JSONUtil.put(
				"id", "IndividualInterestScoresNanite"
			).put(
				"lastSuccessfulDay", dayDateString
			)
		).put(
			"type", "nanite"
		);
	}

	private JSONObject _getContextJSONObject(
		JSONObject individualSegmentJSONObject) {

		return JSONUtil.put(
			"dateModified",
			individualSegmentJSONObject.getString("dateModified")
		).put(
			"individualSegmentJSONObject", individualSegmentJSONObject
		);
	}

	private Long _updateDynamicMemberships(String filter) throws Exception {
		JSONObject individualSegmentJSONObject =
			faroInfoElasticsearchInvoker.add(
				"individual-segments",
				FaroInfoTestUtil.buildDynamicIndividualSegmentJSONObject(
					"1", filter));

		_updateDynamicMembershipsNanite.run(
			_getContextJSONObject(individualSegmentJSONObject));

		return individualSegmentJSONObject.getLong("id");
	}

	@Autowired
	private FaroInfoIndividualDog _faroInfoIndividualDog;

	@Autowired
	private MembershipDog _membershipDog;

	@Autowired
	private ObjectMapper _objectMapper;

	@Autowired
	private UpdateDynamicMembershipsNanite _updateDynamicMembershipsNanite;

}