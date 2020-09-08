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

package com.liferay.osb.asah.upgrade.v2_8_0.test;

import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;
import com.liferay.osb.asah.upgrade.spring.OSBAsahUpgradeSpringBootApplication;
import com.liferay.osb.asah.upgrade.v2_8_0.IndividualsUpgradeStep;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Leslie Wong
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OSBAsahUpgradeSpringBootApplication.class)
public class IndividualsUpgradeStepTest {

	@Before
	public void setUp() {
		_elasticsearchInvoker = _elasticsearchInvokerFactory.forFaroInfo();
	}

	@ElasticsearchIndex(
		name = "activities", resourcePath = "activities.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "activity-groups", resourcePath = "activity_groups.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "engagements", resourcePath = "engagements.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "fields", resourcePath = "fields.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "individual-segments", resourcePath = "individual_segments.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals_1.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "interests", resourcePath = "interests.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "memberships", resourcePath = "memberships.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "membership-changes", resourcePath = "membership_changes.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testUpgrade() throws Exception {
		_individualsUpgradeStep.upgrade(null);

		JSONArray jsonArray = _elasticsearchInvoker.get("individuals");

		Assert.assertEquals(1, jsonArray.length());

		JSONObject individualJSONObject = jsonArray.getJSONObject(0);

		String individualId = individualJSONObject.getString("id");

		Assert.assertEquals(
			2,
			_elasticsearchInvoker.count(
				"activities",
				QueryBuilders.termQuery("ownerId", individualId)));

		Assert.assertEquals(
			2,
			_elasticsearchInvoker.count(
				"activity-groups",
				QueryBuilders.termQuery("ownerId", individualId)));

		Assert.assertEquals(
			2,
			_elasticsearchInvoker.count(
				"engagements",
				QueryBuilders.termQuery("ownerId", individualId)));

		Assert.assertEquals(
			2,
			_elasticsearchInvoker.count(
				"fields", QueryBuilders.termQuery("ownerId", individualId)));

		Assert.assertEquals(
			2,
			_elasticsearchInvoker.count(
				"interests", QueryBuilders.termQuery("ownerId", individualId)));

		Assert.assertEquals(
			1,
			_elasticsearchInvoker.count(
				"memberships",
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery("individualId", individualId)
				).filter(
					QueryBuilders.termQuery("status", "ACTIVE")
				)));

		JSONObject membershipChangeJSONObject = _elasticsearchInvoker.fetch(
			"membership-changes",
			QueryBuilders.termQuery("operation", "REMOVED"));

		Assert.assertNotNull(membershipChangeJSONObject);

		Assert.assertTrue(
			membershipChangeJSONObject.optBoolean("individualDeleted"));
	}

	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

	@Autowired
	private IndividualsUpgradeStep _individualsUpgradeStep;

}