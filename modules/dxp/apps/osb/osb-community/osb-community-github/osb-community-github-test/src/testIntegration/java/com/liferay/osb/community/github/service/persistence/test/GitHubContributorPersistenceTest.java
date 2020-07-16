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
import com.liferay.osb.community.github.exception.NoSuchGitHubContributorException;
import com.liferay.osb.community.github.model.GitHubContributor;
import com.liferay.osb.community.github.service.GitHubContributorLocalServiceUtil;
import com.liferay.osb.community.github.service.persistence.GitHubContributorPersistence;
import com.liferay.osb.community.github.service.persistence.GitHubContributorUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
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
public class GitHubContributorPersistenceTest {

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
		_persistence = GitHubContributorUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<GitHubContributor> iterator = _gitHubContributors.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		GitHubContributor gitHubContributor = _persistence.create(pk);

		Assert.assertNotNull(gitHubContributor);

		Assert.assertEquals(gitHubContributor.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		GitHubContributor newGitHubContributor = addGitHubContributor();

		_persistence.remove(newGitHubContributor);

		GitHubContributor existingGitHubContributor =
			_persistence.fetchByPrimaryKey(
				newGitHubContributor.getPrimaryKey());

		Assert.assertNull(existingGitHubContributor);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addGitHubContributor();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		GitHubContributor newGitHubContributor = _persistence.create(pk);

		newGitHubContributor.setCompanyId(RandomTestUtil.nextLong());

		newGitHubContributor.setUserId(RandomTestUtil.nextLong());

		newGitHubContributor.setUserName(RandomTestUtil.randomString());

		newGitHubContributor.setCreateDate(RandomTestUtil.nextDate());

		newGitHubContributor.setModifiedDate(RandomTestUtil.nextDate());

		newGitHubContributor.setGitHubRepositoryId(RandomTestUtil.nextLong());

		newGitHubContributor.setName(RandomTestUtil.randomString());

		newGitHubContributor.setAvatarURL(RandomTestUtil.randomString());

		newGitHubContributor.setContributions(RandomTestUtil.nextInt());

		newGitHubContributor.setProfileURL(RandomTestUtil.randomString());

		_gitHubContributors.add(_persistence.update(newGitHubContributor));

		GitHubContributor existingGitHubContributor =
			_persistence.findByPrimaryKey(newGitHubContributor.getPrimaryKey());

		Assert.assertEquals(
			existingGitHubContributor.getGitHubContributorId(),
			newGitHubContributor.getGitHubContributorId());
		Assert.assertEquals(
			existingGitHubContributor.getCompanyId(),
			newGitHubContributor.getCompanyId());
		Assert.assertEquals(
			existingGitHubContributor.getUserId(),
			newGitHubContributor.getUserId());
		Assert.assertEquals(
			existingGitHubContributor.getUserName(),
			newGitHubContributor.getUserName());
		Assert.assertEquals(
			Time.getShortTimestamp(existingGitHubContributor.getCreateDate()),
			Time.getShortTimestamp(newGitHubContributor.getCreateDate()));
		Assert.assertEquals(
			Time.getShortTimestamp(existingGitHubContributor.getModifiedDate()),
			Time.getShortTimestamp(newGitHubContributor.getModifiedDate()));
		Assert.assertEquals(
			existingGitHubContributor.getGitHubRepositoryId(),
			newGitHubContributor.getGitHubRepositoryId());
		Assert.assertEquals(
			existingGitHubContributor.getName(),
			newGitHubContributor.getName());
		Assert.assertEquals(
			existingGitHubContributor.getAvatarURL(),
			newGitHubContributor.getAvatarURL());
		Assert.assertEquals(
			existingGitHubContributor.getContributions(),
			newGitHubContributor.getContributions());
		Assert.assertEquals(
			existingGitHubContributor.getProfileURL(),
			newGitHubContributor.getProfileURL());
	}

	@Test
	public void testCountByGitHubRepositoryId() throws Exception {
		_persistence.countByGitHubRepositoryId(RandomTestUtil.nextLong());

		_persistence.countByGitHubRepositoryId(0L);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		GitHubContributor newGitHubContributor = addGitHubContributor();

		GitHubContributor existingGitHubContributor =
			_persistence.findByPrimaryKey(newGitHubContributor.getPrimaryKey());

		Assert.assertEquals(existingGitHubContributor, newGitHubContributor);
	}

	@Test(expected = NoSuchGitHubContributorException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, getOrderByComparator());
	}

	protected OrderByComparator<GitHubContributor> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create(
			"OSBCommunity_GitHubContributor", "gitHubContributorId", true,
			"companyId", true, "userId", true, "userName", true, "createDate",
			true, "modifiedDate", true, "gitHubRepositoryId", true, "name",
			true, "avatarURL", true, "contributions", true, "profileURL", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		GitHubContributor newGitHubContributor = addGitHubContributor();

		GitHubContributor existingGitHubContributor =
			_persistence.fetchByPrimaryKey(
				newGitHubContributor.getPrimaryKey());

		Assert.assertEquals(existingGitHubContributor, newGitHubContributor);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		GitHubContributor missingGitHubContributor =
			_persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingGitHubContributor);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {

		GitHubContributor newGitHubContributor1 = addGitHubContributor();
		GitHubContributor newGitHubContributor2 = addGitHubContributor();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newGitHubContributor1.getPrimaryKey());
		primaryKeys.add(newGitHubContributor2.getPrimaryKey());

		Map<Serializable, GitHubContributor> gitHubContributors =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, gitHubContributors.size());
		Assert.assertEquals(
			newGitHubContributor1,
			gitHubContributors.get(newGitHubContributor1.getPrimaryKey()));
		Assert.assertEquals(
			newGitHubContributor2,
			gitHubContributors.get(newGitHubContributor2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {

		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, GitHubContributor> gitHubContributors =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(gitHubContributors.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {

		GitHubContributor newGitHubContributor = addGitHubContributor();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newGitHubContributor.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, GitHubContributor> gitHubContributors =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, gitHubContributors.size());
		Assert.assertEquals(
			newGitHubContributor,
			gitHubContributors.get(newGitHubContributor.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys() throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, GitHubContributor> gitHubContributors =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(gitHubContributors.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey() throws Exception {
		GitHubContributor newGitHubContributor = addGitHubContributor();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newGitHubContributor.getPrimaryKey());

		Map<Serializable, GitHubContributor> gitHubContributors =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, gitHubContributors.size());
		Assert.assertEquals(
			newGitHubContributor,
			gitHubContributors.get(newGitHubContributor.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery =
			GitHubContributorLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod
				<GitHubContributor>() {

				@Override
				public void performAction(GitHubContributor gitHubContributor) {
					Assert.assertNotNull(gitHubContributor);

					count.increment();
				}

			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting() throws Exception {
		GitHubContributor newGitHubContributor = addGitHubContributor();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			GitHubContributor.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"gitHubContributorId",
				newGitHubContributor.getGitHubContributorId()));

		List<GitHubContributor> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(1, result.size());

		GitHubContributor existingGitHubContributor = result.get(0);

		Assert.assertEquals(existingGitHubContributor, newGitHubContributor);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			GitHubContributor.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"gitHubContributorId", RandomTestUtil.nextLong()));

		List<GitHubContributor> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting() throws Exception {
		GitHubContributor newGitHubContributor = addGitHubContributor();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			GitHubContributor.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("gitHubContributorId"));

		Object newGitHubContributorId =
			newGitHubContributor.getGitHubContributorId();

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"gitHubContributorId", new Object[] {newGitHubContributorId}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingGitHubContributorId = result.get(0);

		Assert.assertEquals(
			existingGitHubContributorId, newGitHubContributorId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			GitHubContributor.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("gitHubContributorId"));

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"gitHubContributorId",
				new Object[] {RandomTestUtil.nextLong()}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected GitHubContributor addGitHubContributor() throws Exception {
		long pk = RandomTestUtil.nextLong();

		GitHubContributor gitHubContributor = _persistence.create(pk);

		gitHubContributor.setCompanyId(RandomTestUtil.nextLong());

		gitHubContributor.setUserId(RandomTestUtil.nextLong());

		gitHubContributor.setUserName(RandomTestUtil.randomString());

		gitHubContributor.setCreateDate(RandomTestUtil.nextDate());

		gitHubContributor.setModifiedDate(RandomTestUtil.nextDate());

		gitHubContributor.setGitHubRepositoryId(RandomTestUtil.nextLong());

		gitHubContributor.setName(RandomTestUtil.randomString());

		gitHubContributor.setAvatarURL(RandomTestUtil.randomString());

		gitHubContributor.setContributions(RandomTestUtil.nextInt());

		gitHubContributor.setProfileURL(RandomTestUtil.randomString());

		_gitHubContributors.add(_persistence.update(gitHubContributor));

		return gitHubContributor;
	}

	private List<GitHubContributor> _gitHubContributors =
		new ArrayList<GitHubContributor>();
	private GitHubContributorPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;

}