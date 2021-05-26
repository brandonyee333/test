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

package com.liferay.osb.asah.backend.dog.test;

import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.dog.DXPEntityDog;
import com.liferay.osb.asah.common.entity.DXPEntity;
import com.liferay.osb.asah.common.model.ResultBag;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.util.ListUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.annotation.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Matthew Kong
 */
@ElasticsearchIndex(
	name = "data-sources", resourcePath = "data_sources.json",
	weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OSBAsahBackendSpringBootApplication.class)
public class DXPEntityDogTest {

	@ElasticsearchIndex(
		name = "groups", resourcePath = "groups.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_DXP_RAW
	)
	@Test
	public void testGetGroupResultBag() {
		_testGetResultBag(
			null, "groups", Arrays.asList("Global", "Guest"), 2, null,
			Sort.asc("name"));
		_testGetResultBag(
			"414686271857066676", "groups", Arrays.asList("Global", "Guest"), 2,
			null, Sort.asc("name"));
		_testGetResultBag(
			"414686271857066677", "groups", Collections.emptyList(), 0, null,
			Sort.asc("name"));
	}

	@ElasticsearchIndex(
		name = "organizations", resourcePath = "organizations.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetOrganizationResultBag() {
		_testGetResultBag(
			null, "organizations",
			Arrays.asList("engineering", "marketing", "sales engineering"), 3,
			null, Sort.asc("name"));
	}

	@ElasticsearchIndex(
		name = "organizations", resourcePath = "organizations.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetOrganizationResultBagSearch() {
		_testGetResultBag(
			null, "organizations",
			Arrays.asList("engineering", "sales engineering"), 2, "engine",
			Sort.asc("name"));
	}

	@ElasticsearchIndex(
		name = "roles", resourcePath = "roles.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_DXP_RAW
	)
	@Test
	public void testGetRoleResultBag() {
		_testGetResultBag(
			null, "roles",
			Arrays.asList("Administrator", "Guest", "Owner", "Power User"), 4,
			null, Sort.asc("name"));
	}

	@ElasticsearchIndex(
		name = "channels", resourcePath = "channels.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "teams", resourcePath = "teams.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_DXP_RAW
	)
	@Test
	public void testGetTeamResultBag() {
		_testGetResultBag(
			"414686271857066676", "teams",
			Arrays.asList("teamA", "teamB", "teamC", "teamD"), 4, null,
			Sort.asc("name"));
		_testGetResultBag(
			"414686271857066677", "teams", Arrays.asList("teamE"), 1, null,
			Sort.asc("name"));
	}

	@ElasticsearchIndex(
		name = "user-groups", resourcePath = "user_groups.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_DXP_RAW
	)
	@Test
	public void testGetUserGroupResultBag() {
		_testGetResultBag(
			null, "user-groups", Arrays.asList("Mac Users"), 1, null,
			Sort.asc("name"));
	}

	@ElasticsearchIndex(
		name = "channels", resourcePath = "channels.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "users", resourcePath = "users.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_DXP_RAW
	)
	@Test
	public void testGetUserResultBag() {
		_testGetResultBag(
			null, "users",
			Arrays.asList(
				"Bruno Admin", "Bruno Badmin", "Test1 Test1", "Test2 Test2",
				"Test3 Test3"),
			5, null, Sort.asc("name"));
		_testGetResultBag(
			"414686271857066676", "users",
			Arrays.asList(
				"Bruno Admin", "Bruno Badmin", "Test1 Test1", "Test2 Test2"),
			4, null, Sort.asc("name"));
		_testGetResultBag(
			"414686271857066677", "users", Arrays.asList("Test3 Test3"), 1,
			null, Sort.asc("name"));
	}

	@ElasticsearchIndex(
		name = "users", resourcePath = "users.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_DXP_RAW
	)
	@Test
	public void testGetUserResultBagCaseInsensitiveSearch() {
		_testGetResultBag(
			null, "users",
			Arrays.asList("Test1 Test1", "Test2 Test2", "Test3 Test3"), 3,
			"test", Sort.asc("name"));
	}

	@ElasticsearchIndex(
		name = "channels", resourcePath = "channels.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "users", resourcePath = "users.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_DXP_RAW
	)
	@Test
	public void testGetUserResultBagSearch() {
		_testGetResultBag(
			null, "users",
			Arrays.asList("Test1 Test1", "Test2 Test2", "Test3 Test3"), 3,
			"Test", Sort.asc("name"));
		_testGetResultBag(
			"414686271857066676", "users",
			Arrays.asList("Test1 Test1", "Test2 Test2"), 2, "Test",
			Sort.asc("name"));
	}

	@ElasticsearchIndex(
		name = "users", resourcePath = "users.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_DXP_RAW
	)
	@Test
	public void testGetUserResultBagSearchAndSort() {
		_testGetResultBag(
			null, "users", Arrays.asList("Bruno Badmin", "Bruno Admin"), 2,
			"Bruno", Sort.desc("name"));
	}

	private void _testGetResultBag(
		String channelId, String collectionName, List<String> expectedNames,
		int expectedTotal, String keywords, Sort sort) {

		ResultBag<? extends DXPEntity> dxpEntityResultBag =
			_dxpEntityDog.getDXPEntityResultBag(
				channelId, collectionName, keywords, 10, sort, 0);

		Assert.assertEquals(expectedTotal, dxpEntityResultBag.getTotal());

		Assert.assertEquals(
			expectedNames,
			ListUtil.map(dxpEntityResultBag.getResults(), DXPEntity::getName));
	}

	@Autowired
	private DXPEntityDog _dxpEntityDog;

}