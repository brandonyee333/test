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

package com.liferay.osb.community.github.service.persistence.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.osb.community.github.exception.NoSuchGitHubRepositoryException;
import com.liferay.osb.community.github.model.GitHubRepository;
import com.liferay.osb.community.github.service.GitHubRepositoryLocalServiceUtil;
import com.liferay.osb.community.github.service.persistence.GitHubRepositoryPersistence;
import com.liferay.osb.community.github.service.persistence.GitHubRepositoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @generated
 */
@RunWith(Arquillian.class)
public class GitHubRepositoryPersistenceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(
				Propagation.REQUIRED,
				"com.liferay.osb.community.github.service"));

	@Before
	public void setUp() {
		_persistence = GitHubRepositoryUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<GitHubRepository> iterator = _gitHubRepositories.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		GitHubRepository gitHubRepository = _persistence.create(pk);

		Assert.assertNotNull(gitHubRepository);

		Assert.assertEquals(gitHubRepository.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		GitHubRepository newGitHubRepository = addGitHubRepository();

		_persistence.remove(newGitHubRepository);

		GitHubRepository existingGitHubRepository =
			_persistence.fetchByPrimaryKey(newGitHubRepository.getPrimaryKey());

		Assert.assertNull(existingGitHubRepository);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addGitHubRepository();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		GitHubRepository newGitHubRepository = _persistence.create(pk);

		newGitHubRepository.setCompanyId(RandomTestUtil.nextLong());

		newGitHubRepository.setUserId(RandomTestUtil.nextLong());

		newGitHubRepository.setUserName(RandomTestUtil.randomString());

		newGitHubRepository.setCreateDate(RandomTestUtil.nextDate());

		newGitHubRepository.setModifiedDate(RandomTestUtil.nextDate());

		newGitHubRepository.setOwner(RandomTestUtil.randomString());

		newGitHubRepository.setName(RandomTestUtil.randomString());

		newGitHubRepository.setCommits(RandomTestUtil.nextInt());

		newGitHubRepository.setOpenIssues(RandomTestUtil.nextInt());

		newGitHubRepository.setStars(RandomTestUtil.nextInt());

		newGitHubRepository.setUrl(RandomTestUtil.randomString());

		newGitHubRepository.setRepositoryCreateDate(RandomTestUtil.nextDate());

		_gitHubRepositories.add(_persistence.update(newGitHubRepository));

		GitHubRepository existingGitHubRepository =
			_persistence.findByPrimaryKey(newGitHubRepository.getPrimaryKey());

		Assert.assertEquals(
			existingGitHubRepository.getGitHubRepositoryId(),
			newGitHubRepository.getGitHubRepositoryId());
		Assert.assertEquals(
			existingGitHubRepository.getCompanyId(),
			newGitHubRepository.getCompanyId());
		Assert.assertEquals(
			existingGitHubRepository.getUserId(),
			newGitHubRepository.getUserId());
		Assert.assertEquals(
			existingGitHubRepository.getUserName(),
			newGitHubRepository.getUserName());
		Assert.assertEquals(
			Time.getShortTimestamp(existingGitHubRepository.getCreateDate()),
			Time.getShortTimestamp(newGitHubRepository.getCreateDate()));
		Assert.assertEquals(
			Time.getShortTimestamp(existingGitHubRepository.getModifiedDate()),
			Time.getShortTimestamp(newGitHubRepository.getModifiedDate()));
		Assert.assertEquals(
			existingGitHubRepository.getOwner(),
			newGitHubRepository.getOwner());
		Assert.assertEquals(
			existingGitHubRepository.getName(), newGitHubRepository.getName());
		Assert.assertEquals(
			existingGitHubRepository.getCommits(),
			newGitHubRepository.getCommits());
		Assert.assertEquals(
			existingGitHubRepository.getOpenIssues(),
			newGitHubRepository.getOpenIssues());
		Assert.assertEquals(
			existingGitHubRepository.getStars(),
			newGitHubRepository.getStars());
		Assert.assertEquals(
			existingGitHubRepository.getUrl(), newGitHubRepository.getUrl());
		Assert.assertEquals(
			Time.getShortTimestamp(
				existingGitHubRepository.getRepositoryCreateDate()),
			Time.getShortTimestamp(
				newGitHubRepository.getRepositoryCreateDate()));
	}

	@Test
	public void testCountByO_N() throws Exception {
		_persistence.countByO_N("", "");

		_persistence.countByO_N("null", "null");

		_persistence.countByO_N((String)null, (String)null);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		GitHubRepository newGitHubRepository = addGitHubRepository();

		GitHubRepository existingGitHubRepository =
			_persistence.findByPrimaryKey(newGitHubRepository.getPrimaryKey());

		Assert.assertEquals(existingGitHubRepository, newGitHubRepository);
	}

	@Test(expected = NoSuchGitHubRepositoryException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, getOrderByComparator());
	}

	protected OrderByComparator<GitHubRepository> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create(
			"OSBCommunity_GitHubRepository", "gitHubRepositoryId", true,
			"companyId", true, "userId", true, "userName", true, "createDate",
			true, "modifiedDate", true, "owner", true, "name", true, "commits",
			true, "openIssues", true, "stars", true, "url", true,
			"repositoryCreateDate", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		GitHubRepository newGitHubRepository = addGitHubRepository();

		GitHubRepository existingGitHubRepository =
			_persistence.fetchByPrimaryKey(newGitHubRepository.getPrimaryKey());

		Assert.assertEquals(existingGitHubRepository, newGitHubRepository);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		GitHubRepository missingGitHubRepository =
			_persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingGitHubRepository);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {

		GitHubRepository newGitHubRepository1 = addGitHubRepository();
		GitHubRepository newGitHubRepository2 = addGitHubRepository();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newGitHubRepository1.getPrimaryKey());
		primaryKeys.add(newGitHubRepository2.getPrimaryKey());

		Map<Serializable, GitHubRepository> gitHubRepositories =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, gitHubRepositories.size());
		Assert.assertEquals(
			newGitHubRepository1,
			gitHubRepositories.get(newGitHubRepository1.getPrimaryKey()));
		Assert.assertEquals(
			newGitHubRepository2,
			gitHubRepositories.get(newGitHubRepository2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {

		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, GitHubRepository> gitHubRepositories =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(gitHubRepositories.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {

		GitHubRepository newGitHubRepository = addGitHubRepository();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newGitHubRepository.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, GitHubRepository> gitHubRepositories =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, gitHubRepositories.size());
		Assert.assertEquals(
			newGitHubRepository,
			gitHubRepositories.get(newGitHubRepository.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys() throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, GitHubRepository> gitHubRepositories =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(gitHubRepositories.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey() throws Exception {
		GitHubRepository newGitHubRepository = addGitHubRepository();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newGitHubRepository.getPrimaryKey());

		Map<Serializable, GitHubRepository> gitHubRepositories =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, gitHubRepositories.size());
		Assert.assertEquals(
			newGitHubRepository,
			gitHubRepositories.get(newGitHubRepository.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery =
			GitHubRepositoryLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<GitHubRepository>() {

				@Override
				public void performAction(GitHubRepository gitHubRepository) {
					Assert.assertNotNull(gitHubRepository);

					count.increment();
				}

			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting() throws Exception {
		GitHubRepository newGitHubRepository = addGitHubRepository();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			GitHubRepository.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"gitHubRepositoryId",
				newGitHubRepository.getGitHubRepositoryId()));

		List<GitHubRepository> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(1, result.size());

		GitHubRepository existingGitHubRepository = result.get(0);

		Assert.assertEquals(existingGitHubRepository, newGitHubRepository);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			GitHubRepository.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"gitHubRepositoryId", RandomTestUtil.nextLong()));

		List<GitHubRepository> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting() throws Exception {
		GitHubRepository newGitHubRepository = addGitHubRepository();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			GitHubRepository.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("gitHubRepositoryId"));

		Object newGitHubRepositoryId =
			newGitHubRepository.getGitHubRepositoryId();

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"gitHubRepositoryId", new Object[] {newGitHubRepositoryId}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingGitHubRepositoryId = result.get(0);

		Assert.assertEquals(existingGitHubRepositoryId, newGitHubRepositoryId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			GitHubRepository.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("gitHubRepositoryId"));

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"gitHubRepositoryId",
				new Object[] {RandomTestUtil.nextLong()}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		GitHubRepository newGitHubRepository = addGitHubRepository();

		_persistence.clearCache();

		GitHubRepository existingGitHubRepository =
			_persistence.findByPrimaryKey(newGitHubRepository.getPrimaryKey());

		Assert.assertEquals(
			existingGitHubRepository.getOwner(),
			ReflectionTestUtil.invoke(
				existingGitHubRepository, "getOriginalOwner", new Class<?>[0]));
		Assert.assertEquals(
			existingGitHubRepository.getName(),
			ReflectionTestUtil.invoke(
				existingGitHubRepository, "getOriginalName", new Class<?>[0]));
	}

	protected GitHubRepository addGitHubRepository() throws Exception {
		long pk = RandomTestUtil.nextLong();

		GitHubRepository gitHubRepository = _persistence.create(pk);

		gitHubRepository.setCompanyId(RandomTestUtil.nextLong());

		gitHubRepository.setUserId(RandomTestUtil.nextLong());

		gitHubRepository.setUserName(RandomTestUtil.randomString());

		gitHubRepository.setCreateDate(RandomTestUtil.nextDate());

		gitHubRepository.setModifiedDate(RandomTestUtil.nextDate());

		gitHubRepository.setOwner(RandomTestUtil.randomString());

		gitHubRepository.setName(RandomTestUtil.randomString());

		gitHubRepository.setCommits(RandomTestUtil.nextInt());

		gitHubRepository.setOpenIssues(RandomTestUtil.nextInt());

		gitHubRepository.setStars(RandomTestUtil.nextInt());

		gitHubRepository.setUrl(RandomTestUtil.randomString());

		gitHubRepository.setRepositoryCreateDate(RandomTestUtil.nextDate());

		_gitHubRepositories.add(_persistence.update(gitHubRepository));

		return gitHubRepository;
	}

	private List<GitHubRepository> _gitHubRepositories =
		new ArrayList<GitHubRepository>();
	private GitHubRepositoryPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;

}