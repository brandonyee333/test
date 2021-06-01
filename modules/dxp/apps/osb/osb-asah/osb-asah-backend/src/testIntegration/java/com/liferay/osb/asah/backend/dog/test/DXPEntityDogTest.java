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
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.entity.DXPEntity;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.util.ListUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.annotation.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.elasticsearch.index.query.QueryBuilders;

import org.junit.After;
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

	@After
	public void tearDown() {
		_faroInfoElasticsearchInvoker.delete(
			"channels", QueryBuilders.matchAllQuery());

		_dxpEntityDog.deleteByType(DXPEntity.Type.GROUP);
		_dxpEntityDog.deleteByType(DXPEntity.Type.ORGANIZATION);
		_dxpEntityDog.deleteByType(DXPEntity.Type.ROLE);
		_dxpEntityDog.deleteByType(DXPEntity.Type.TEAM);
		_dxpEntityDog.deleteByType(DXPEntity.Type.USER);
		_dxpEntityDog.deleteByType(DXPEntity.Type.USER_GROUP);
	}

	@ElasticsearchIndex(
		name = "groups", resourcePath = "groups.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_DXP_RAW
	)
	@ElasticsearchIndex(
		name = "channels", resourcePath = "channels.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetGroups() {
		_testGetDXPEntities(
			null, "groups", Arrays.asList("Global", "Guest"), 2, null,
			Sort.asc("fields.name"));
		_testGetDXPEntities(
			414686271857066676L, "groups", Arrays.asList("Global", "Guest"), 2,
			null, Sort.asc("fields.name"));
		_testGetDXPEntities(
			414686271857066677L, "groups", Collections.emptyList(), 0, null,
			Sort.asc("fields.name"));
	}

	@ElasticsearchIndex(
		name = "organizations", resourcePath = "organizations.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_DXP_RAW
	)
	@Test
	public void testGetOrganizations() {
		_testGetDXPEntities(
			null, "organizations",
			Arrays.asList("engineering", "marketing", "sales engineering"), 3,
			null, Sort.asc("fields.name"));
	}

	@ElasticsearchIndex(
		name = "organizations", resourcePath = "organizations.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_DXP_RAW
	)
	@Test
	public void testGetOrganizationsSearch() {
		_testGetDXPEntities(
			null, "organizations",
			Arrays.asList("engineering", "sales engineering"), 2, "engine",
			Sort.asc("fields.name"));
	}

	@ElasticsearchIndex(
		name = "roles", resourcePath = "roles.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_DXP_RAW
	)
	@Test
	public void testGetRoles() {
		_testGetDXPEntities(
			null, "roles",
			Arrays.asList("Administrator", "Guest", "Owner", "Power User"), 4,
			null, Sort.asc("fields.name"));
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
	public void testGetTeams() {
		_testGetDXPEntities(
			414686271857066676L, "teams",
			Arrays.asList("teamA", "teamB", "teamC", "teamD"), 4, null,
			Sort.asc("fields.name"));
		_testGetDXPEntities(
			414686271857066677L, "teams", Arrays.asList("teamE"), 1, null,
			Sort.asc("fields.name"));
	}

	@ElasticsearchIndex(
		name = "user-groups", resourcePath = "user_groups.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_DXP_RAW
	)
	@Test
	public void testGetUserGroups() {
		_testGetDXPEntities(
			null, "user-groups", Arrays.asList("Mac Users"), 1, null,
			Sort.asc("fields.name"));
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
	public void testGetUsers() {
		_testGetDXPEntities(
			null, "users",
			Arrays.asList(
				"Bruno Admin", "Bruno Badmin", "Test1 Test1", "Test2 Test2",
				"Test3 Test3"),
			5, null, Sort.asc("fields.name"));
		_testGetDXPEntities(
			414686271857066676L, "users",
			Arrays.asList(
				"Bruno Admin", "Bruno Badmin", "Test1 Test1", "Test2 Test2"),
			4, null, Sort.asc("fields.name"));
		_testGetDXPEntities(
			414686271857066677L, "users", Arrays.asList("Test3 Test3"), 1, null,
			Sort.asc("fields.name"));
	}

	@ElasticsearchIndex(
		name = "users", resourcePath = "users.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_DXP_RAW
	)
	@Test
	public void testGetUsersCaseInsensitiveSearch() {
		_testGetDXPEntities(
			null, "users",
			Arrays.asList("Test1 Test1", "Test2 Test2", "Test3 Test3"), 3,
			"test", Sort.asc("fields.name"));
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
	public void testGetUsersSearch() {
		_testGetDXPEntities(
			null, "users",
			Arrays.asList("Test1 Test1", "Test2 Test2", "Test3 Test3"), 3,
			"Test", Sort.asc("fields.name"));
		_testGetDXPEntities(
			414686271857066676L, "users",
			Arrays.asList("Test1 Test1", "Test2 Test2"), 2, "Test",
			Sort.asc("fields.name"));
	}

	@ElasticsearchIndex(
		name = "users", resourcePath = "users.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_DXP_RAW
	)
	@Test
	public void testGetUsersSearchAndSort() {
		_testGetDXPEntities(
			null, "users", Arrays.asList("Bruno Badmin", "Bruno Admin"), 2,
			"Bruno", Sort.desc("fields.name"));
	}

	private void _testGetDXPEntities(
		Long channelId, String collectionName, List<String> expectedNames,
		int expectedTotal, String keywords, Sort sort) {

		List<? extends DXPEntity> dxpEntities = _dxpEntityDog.getDXPEntities(
			channelId, keywords, 10, sort, 0,
			DXPEntity.Type.ofCollectionName(collectionName));

		Assert.assertEquals(
			dxpEntities.toString(), expectedTotal, dxpEntities.size());

		Assert.assertEquals(
			expectedNames, ListUtil.map(dxpEntities, DXPEntity::getName));
	}

	@Autowired
	private DXPEntityDog _dxpEntityDog;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

}