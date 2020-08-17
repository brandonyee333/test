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

import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoMembershipDog;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import org.elasticsearch.index.query.QueryBuilder;
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
public class FaroInfoMembershipDogTest extends BaseFaroInfoDogTestCase {

	@Test
	public void testAddMembershipWithActiveStatusAndAnonymousIndividual()
		throws Exception {

		elasticsearchInvoker.add(
			"individuals",
			JSONUtil.put(
				"id", "123"
			).put(
				"individualSegmentIds", JSONUtil.put("456")
			));

		elasticsearchInvoker.add(
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

		JSONObject membershipJSONObject = _faroInfoMembershipDog.addMembership(
			newMembershipJSONObject);

		Assert.assertNotNull(membershipJSONObject);

		JSONArray membershipsJSONArray = elasticsearchInvoker.get(
			"memberships");

		Assert.assertEquals(1, membershipsJSONArray.length());

		JSONAssert.assertEquals(
			newMembershipJSONObject, membershipsJSONArray.getJSONObject(0),
			false);

		_assertIds(membershipJSONObject);

		JSONAssert.assertEquals(
			JSONUtil.put(
				"id", "123"
			).put(
				"individualSegmentIds", JSONUtil.putAll("234", "456")
			),
			elasticsearchInvoker.get("individuals", "123"), false);

		JSONArray membershipChangesJSONArray = elasticsearchInvoker.get(
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
		JSONObject membershipJSONObject = _faroInfoMembershipDog.addMembership(
			JSONUtil.put("status", "INACTIVE"));

		Assert.assertNotNull(membershipJSONObject);

		JSONArray membershipsJSONArray = elasticsearchInvoker.get(
			"memberships");

		Assert.assertEquals(1, membershipsJSONArray.length());

		JSONAssert.assertEquals(
			JSONUtil.put("status", "INACTIVE"),
			membershipsJSONArray.getJSONObject(0), false);

		_assertIds(membershipJSONObject);
	}

	@Test
	public void testBuildIndividualsQueryBuilder() throws Exception {
		QueryBuilder queryBuilder =
			_faroInfoMembershipDog.buildIndividualsQueryBuilder(
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

		queryBuilder = _faroInfoMembershipDog.buildIndividualsQueryBuilder(
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

		queryBuilder = _faroInfoMembershipDog.buildIndividualsQueryBuilder(
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
		Assert.assertNotNull(
			_faroInfoMembershipDog.deactivateMembership(
				"2019-02-11T20:26:53.218Z", "338486041327913341",
				"338511398116723458"));

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
			elasticsearchInvoker.fetch(
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
			elasticsearchInvoker.fetch(
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

		JSONObject individualJSONObject = elasticsearchInvoker.fetch(
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

		Assert.assertNotNull(
			_faroInfoMembershipDog.deactivateMembership(
				"2019-02-11T20:26:53.218Z", "338486041327913339",
				"338511398116723457"));

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
			elasticsearchInvoker.fetch(
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
			elasticsearchInvoker.fetch(
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
		List<String> individualSegmentIndividualIds =
			_faroInfoMembershipDog.getIndividualSegmentIndividualIds(
				elasticsearchInvoker.get(
					"individual-segments", "338511398116723458"));

		Assert.assertEquals(
			individualSegmentIndividualIds.toString(), 2,
			individualSegmentIndividualIds.size());
		Assert.assertTrue(
			individualSegmentIndividualIds.contains("338486037253283140"));
		Assert.assertTrue(
			individualSegmentIndividualIds.contains("338486041327913341"));
	}

	@ElasticsearchIndex(
		name = "memberships", resourcePath = "memberships.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testIsMember() {
		Assert.assertFalse(
			_faroInfoMembershipDog.isMember(
				"noIndividualId", "noIndividualSegmentId"));
		Assert.assertTrue(
			_faroInfoMembershipDog.isMember(
				"338486041327913341", "338511398116723458"));
	}

	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testRemoveIndividualSegmentId() {
		JSONObject individualJSONObject = elasticsearchInvoker.fetch(
			"individuals", "338486041327913341");

		JSONArray individualSegmentIdsJSONArray =
			individualJSONObject.getJSONArray("individualSegmentIds");

		Assert.assertEquals(1, individualSegmentIdsJSONArray.length());

		_faroInfoMembershipDog.removeIndividualSegmentId(
			individualJSONObject, "338511398116723458");

		individualJSONObject = elasticsearchInvoker.fetch(
			"individuals", "338486041327913341");

		individualSegmentIdsJSONArray = individualJSONObject.getJSONArray(
			"individualSegmentIds");

		Assert.assertEquals(0, individualSegmentIdsJSONArray.length());
	}

	@Test
	public void testUpdateDynamicAddMemberships() throws Exception {
		elasticsearchInvoker.add(
			"individuals",
			JSONUtil.put(
				"id", "123"
			).put(
				"individualSegmentIds", new JSONArray()
			));

		_faroInfoMembershipDog.updateDynamicAddMemberships(
			true,
			elasticsearchInvoker.add(
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
			"2019-02-11T20:26:53.218Z");

		JSONObject individualJSONObject = elasticsearchInvoker.fetch(
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
		elasticsearchInvoker.add(
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

		_faroInfoMembershipDog.updateDynamicMemberships(
			elasticsearchInvoker.fetch(
				"individual-segments", "338511451975440187"),
			"2019-02-11T20:26:53.218Z");

		JSONObject individualJSONObject = elasticsearchInvoker.fetch(
			"individuals", "338486037253283140");

		JSONAssert.assertEquals(
			JSONUtil.putAll("338511398116723458", "338511451975440187"),
			individualJSONObject.getJSONArray("individualSegmentIds"), false);

		elasticsearchInvoker.delete("individuals", individualJSONObject);

		_faroInfoMembershipDog.updateDynamicMemberships(
			elasticsearchInvoker.fetch(
				"individual-segments", "338511398116723458"),
			"2019-02-11T20:26:53.218Z");

		Assert.assertFalse(
			elasticsearchInvoker.exists(
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
		_faroInfoMembershipDog.updateDynamicRemoveMemberships(
			elasticsearchInvoker.fetch(
				"individual-segments", "338511398116723458"),
			"2019-02-11T20:26:53.218Z");

		JSONArray membershipJSONArray = elasticsearchInvoker.get(
			"memberships",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery(
					"individualSegmentId", "338511398116723458")
			).filter(
				QueryBuilders.termQuery("status", "INACTIVE")
			));

		Assert.assertEquals(3, membershipJSONArray.length());
	}

	private void _assertIds(JSONObject jsonObject) {
		Assert.assertTrue(StringUtils.isNotBlank(jsonObject.getString("id")));
	}

	@Autowired
	private FaroInfoMembershipDog _faroInfoMembershipDog;

}