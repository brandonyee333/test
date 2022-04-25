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

import com.liferay.osb.asah.backend.OSBAsahBackendSpringTestContext;
import com.liferay.osb.asah.common.dog.DXPEntityDog;
import com.liferay.osb.asah.common.entity.DXPEntity;
import com.liferay.osb.asah.common.model.DXPUser;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.repository.ChannelRepository;
import com.liferay.osb.asah.common.repository.DXPEntityRepository;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.common.util.ListUtil;
import com.liferay.osb.asah.test.util.annotation.RepositoryResource;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

/**
 * @author Matthew Kong
 */
public class DXPEntityDogTest
	implements OSBAsahBackendSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@AfterEach
	public void tearDown() {
		_dxpEntityDog.deleteByType(DXPEntity.Type.GROUP);
		_dxpEntityDog.deleteByType(DXPEntity.Type.ORGANIZATION);
		_dxpEntityDog.deleteByType(DXPEntity.Type.ROLE);
		_dxpEntityDog.deleteByType(DXPEntity.Type.TEAM);
		_dxpEntityDog.deleteByType(DXPEntity.Type.USER);
		_dxpEntityDog.deleteByType(DXPEntity.Type.USER_GROUP);
	}

	@RepositoryResource(
		repositoryClass = DataSourceRepository.class,
		resourcePath = "osbasahfaroinfo/data_sources.json"
	)
	@RepositoryResource(
		repositoryClass = DXPEntityRepository.class,
		resourcePath = "osbasahdxpraw/users.json"
	)
	@Test
	public void testFindByAfterAndFieldsAndType() {
		List<? extends DXPEntity> dxpEntities =
			_dxpEntityDog.findByAfterAndFieldsAndType(
				null,
				Collections.singletonMap("fields.screenName", "bruno.admin"),
				10, DXPEntity.Type.USER);

		Stream<? extends DXPEntity> stream = dxpEntities.stream();

		stream.map(
			dxpEntity -> (DXPUser)dxpEntity
		).forEach(
			dxpUser -> Assertions.assertNotNull(dxpUser.getName())
		);
	}

	@RepositoryResource(
		repositoryClass = DataSourceRepository.class,
		resourcePath = "osbasahfaroinfo/data_sources.json"
	)
	@RepositoryResource(
		repositoryClass = DXPEntityRepository.class,
		resourcePath = "osbasahdxpraw/users.json"
	)
	@Test
	public void testFindByFieldsAndType() {
		List<? extends DXPEntity> dxpEntities =
			_dxpEntityDog.findByFieldsAndType(
				Collections.singletonMap("fields.screenName", "bruno.admin"),
				DXPEntity.Type.USER);

		DXPEntity dxpEntity = dxpEntities.get(0);

		Assertions.assertEquals("Bruno Admin", dxpEntity.getName());
	}

	@RepositoryResource(
		repositoryClass = DataSourceRepository.class,
		resourcePath = "osbasahfaroinfo/data_sources.json"
	)
	@RepositoryResource(
		repositoryClass = DXPEntityRepository.class,
		resourcePath = "osbasahdxpraw/groups.json"
	)
	@RepositoryResource(
		repositoryClass = ChannelRepository.class,
		resourcePath = "osbasahfaroinfo/channels.json"
	)
	@Test
	public void testGetGroups() {
		_testGetDXPEntityPage(
			null, "groups", Arrays.asList("Global", "Guest"), 2, null,
			Sort.asc("fields.name"));
		_testGetDXPEntityPage(
			414686271857066676L, "groups", Arrays.asList("Global", "Guest"), 2,
			null, Sort.asc("fields.name"));
		_testGetDXPEntityPage(
			414686271857066677L, "groups", Collections.emptyList(), 0, null,
			Sort.asc("fields.name"));
	}

	@RepositoryResource(
		repositoryClass = DataSourceRepository.class,
		resourcePath = "osbasahfaroinfo/data_sources.json"
	)
	@RepositoryResource(
		repositoryClass = DXPEntityRepository.class,
		resourcePath = "osbasahdxpraw/organizations.json"
	)
	@Test
	public void testGetOrganizations() {
		_testGetDXPEntityPage(
			null, "organizations",
			Arrays.asList("engineering", "marketing", "sales engineering"), 3,
			null, Sort.asc("fields.name"));
	}

	@RepositoryResource(
		repositoryClass = DataSourceRepository.class,
		resourcePath = "osbasahfaroinfo/data_sources.json"
	)
	@RepositoryResource(
		repositoryClass = DXPEntityRepository.class,
		resourcePath = "osbasahdxpraw/organizations.json"
	)
	@Test
	public void testGetOrganizationsSearch() {
		_testGetDXPEntityPage(
			null, "organizations",
			Arrays.asList("engineering", "sales engineering"), 2, "engine",
			Sort.asc("fields.name"));
	}

	@RepositoryResource(
		repositoryClass = DataSourceRepository.class,
		resourcePath = "osbasahfaroinfo/data_sources.json"
	)
	@RepositoryResource(
		repositoryClass = DXPEntityRepository.class,
		resourcePath = "osbasahdxpraw/roles.json"
	)
	@Test
	public void testGetRoles() {
		_testGetDXPEntityPage(
			null, "roles",
			Arrays.asList("Administrator", "Guest", "Owner", "Power User"), 4,
			null, Sort.asc("fields.name"));
	}

	@RepositoryResource(
		repositoryClass = DataSourceRepository.class,
		resourcePath = "osbasahfaroinfo/data_sources.json"
	)
	@RepositoryResource(
		repositoryClass = DXPEntityRepository.class,
		resourcePath = "osbasahdxpraw/teams.json"
	)
	@RepositoryResource(
		repositoryClass = ChannelRepository.class,
		resourcePath = "osbasahfaroinfo/channels.json"
	)
	@Test
	public void testGetTeams() {
		_testGetDXPEntityPage(
			414686271857066676L, "teams",
			Arrays.asList("teamA", "teamB", "teamC", "teamD"), 4, null,
			Sort.asc("fields.name"));
		_testGetDXPEntityPage(
			414686271857066677L, "teams", Arrays.asList("teamE"), 1, null,
			Sort.asc("fields.name"));
	}

	@RepositoryResource(
		repositoryClass = DataSourceRepository.class,
		resourcePath = "osbasahfaroinfo/data_sources.json"
	)
	@RepositoryResource(
		repositoryClass = DXPEntityRepository.class,
		resourcePath = "osbasahdxpraw/user_groups.json"
	)
	@Test
	public void testGetUserGroups() {
		_testGetDXPEntityPage(
			null, "user-groups", Arrays.asList("Mac Users"), 1, null,
			Sort.asc("fields.name"));
	}

	@RepositoryResource(
		repositoryClass = DataSourceRepository.class,
		resourcePath = "osbasahfaroinfo/data_sources.json"
	)
	@RepositoryResource(
		repositoryClass = DXPEntityRepository.class,
		resourcePath = "osbasahdxpraw/users.json"
	)
	@RepositoryResource(
		repositoryClass = ChannelRepository.class,
		resourcePath = "osbasahfaroinfo/channels.json"
	)
	@Test
	public void testGetUsers() {
		_testGetDXPEntityPage(
			null, "users",
			Arrays.asList(
				"Bruno Admin", "Bruno Badmin", "Test1 Test1", "Test2 Test2",
				"Test3 Test3"),
			5, null, Sort.asc("fields.name"));
		_testGetDXPEntityPage(
			414686271857066676L, "users",
			Arrays.asList(
				"Bruno Admin", "Bruno Badmin", "Test1 Test1", "Test2 Test2"),
			4, null, Sort.asc("fields.name"));
		_testGetDXPEntityPage(
			414686271857066677L, "users", Arrays.asList("Test3 Test3"), 1, null,
			Sort.asc("fields.name"));
	}

	@RepositoryResource(
		repositoryClass = DataSourceRepository.class,
		resourcePath = "osbasahfaroinfo/data_sources.json"
	)
	@RepositoryResource(
		repositoryClass = DXPEntityRepository.class,
		resourcePath = "osbasahdxpraw/users.json"
	)
	@Test
	public void testGetUsersCaseInsensitiveSearch() {
		_testGetDXPEntityPage(
			null, "users",
			Arrays.asList("Test1 Test1", "Test2 Test2", "Test3 Test3"), 3,
			"test", Sort.asc("fields.name"));
	}

	@RepositoryResource(
		repositoryClass = DataSourceRepository.class,
		resourcePath = "osbasahfaroinfo/data_sources.json"
	)
	@RepositoryResource(
		repositoryClass = DXPEntityRepository.class,
		resourcePath = "osbasahdxpraw/users.json"
	)
	@RepositoryResource(
		repositoryClass = ChannelRepository.class,
		resourcePath = "osbasahfaroinfo/channels.json"
	)
	@Test
	public void testGetUsersSearch() {
		_testGetDXPEntityPage(
			null, "users",
			Arrays.asList("Test1 Test1", "Test2 Test2", "Test3 Test3"), 3,
			"Test", Sort.asc("fields.name"));
		_testGetDXPEntityPage(
			414686271857066676L, "users",
			Arrays.asList("Test1 Test1", "Test2 Test2"), 2, "Test",
			Sort.asc("fields.name"));
	}

	@RepositoryResource(
		repositoryClass = DataSourceRepository.class,
		resourcePath = "osbasahfaroinfo/data_sources.json"
	)
	@RepositoryResource(
		repositoryClass = DXPEntityRepository.class,
		resourcePath = "osbasahdxpraw/users.json"
	)
	@Test
	public void testGetUsersSearchAndSort() {
		_testGetDXPEntityPage(
			null, "users", Arrays.asList("Bruno Badmin", "Bruno Admin"), 2,
			"Bruno", Sort.desc("name"));
	}

	private void _testGetDXPEntityPage(
		Long channelId, String collectionName, List<String> expectedNames,
		int expectedTotal, String keywords, Sort sort) {

		Page<? extends DXPEntity> dxpEntityPage =
			_dxpEntityDog.getDXPEntityPage(
				channelId, keywords, 10, sort, 0,
				DXPEntity.Type.ofCollectionName(collectionName));

		Assertions.assertEquals(
			expectedTotal, dxpEntityPage.getTotalElements(),
			dxpEntityPage.toString());

		Assertions.assertEquals(
			expectedNames,
			ListUtil.map(dxpEntityPage.getContent(), DXPEntity::getName));
	}

	@Autowired
	private DXPEntityDog _dxpEntityDog;

}