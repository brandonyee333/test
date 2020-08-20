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

package com.liferay.osb.community.doc.project.service.persistence.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.osb.community.doc.project.exception.NoSuchDocProjectException;
import com.liferay.osb.community.doc.project.model.DocProject;
import com.liferay.osb.community.doc.project.service.DocProjectLocalServiceUtil;
import com.liferay.osb.community.doc.project.service.persistence.DocProjectPersistence;
import com.liferay.osb.community.doc.project.service.persistence.DocProjectUtil;
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
public class DocProjectPersistenceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(
				Propagation.REQUIRED,
				"com.liferay.osb.community.doc.project.service"));

	@Before
	public void setUp() {
		_persistence = DocProjectUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<DocProject> iterator = _docProjects.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		DocProject docProject = _persistence.create(pk);

		Assert.assertNotNull(docProject);

		Assert.assertEquals(docProject.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		DocProject newDocProject = addDocProject();

		_persistence.remove(newDocProject);

		DocProject existingDocProject = _persistence.fetchByPrimaryKey(
			newDocProject.getPrimaryKey());

		Assert.assertNull(existingDocProject);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addDocProject();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		DocProject newDocProject = _persistence.create(pk);

		newDocProject.setUuid(RandomTestUtil.randomString());

		newDocProject.setGroupId(RandomTestUtil.nextLong());

		newDocProject.setCompanyId(RandomTestUtil.nextLong());

		newDocProject.setUserId(RandomTestUtil.nextLong());

		newDocProject.setUserName(RandomTestUtil.randomString());

		newDocProject.setCreateDate(RandomTestUtil.nextDate());

		newDocProject.setModifiedDate(RandomTestUtil.nextDate());

		newDocProject.setName(RandomTestUtil.randomString());

		newDocProject.setDescription(RandomTestUtil.randomString());

		newDocProject.setIconFileName(RandomTestUtil.randomString());

		newDocProject.setUnlisted(RandomTestUtil.randomBoolean());

		newDocProject.setType(RandomTestUtil.randomString());

		newDocProject.setTypeSettings(RandomTestUtil.randomString());

		newDocProject.setStatus(RandomTestUtil.nextInt());

		_docProjects.add(_persistence.update(newDocProject));

		DocProject existingDocProject = _persistence.findByPrimaryKey(
			newDocProject.getPrimaryKey());

		Assert.assertEquals(
			existingDocProject.getUuid(), newDocProject.getUuid());
		Assert.assertEquals(
			existingDocProject.getDocProjectId(),
			newDocProject.getDocProjectId());
		Assert.assertEquals(
			existingDocProject.getGroupId(), newDocProject.getGroupId());
		Assert.assertEquals(
			existingDocProject.getCompanyId(), newDocProject.getCompanyId());
		Assert.assertEquals(
			existingDocProject.getUserId(), newDocProject.getUserId());
		Assert.assertEquals(
			existingDocProject.getUserName(), newDocProject.getUserName());
		Assert.assertEquals(
			Time.getShortTimestamp(existingDocProject.getCreateDate()),
			Time.getShortTimestamp(newDocProject.getCreateDate()));
		Assert.assertEquals(
			Time.getShortTimestamp(existingDocProject.getModifiedDate()),
			Time.getShortTimestamp(newDocProject.getModifiedDate()));
		Assert.assertEquals(
			existingDocProject.getName(), newDocProject.getName());
		Assert.assertEquals(
			existingDocProject.getDescription(),
			newDocProject.getDescription());
		Assert.assertEquals(
			existingDocProject.getIconFileName(),
			newDocProject.getIconFileName());
		Assert.assertEquals(
			existingDocProject.isUnlisted(), newDocProject.isUnlisted());
		Assert.assertEquals(
			existingDocProject.getType(), newDocProject.getType());
		Assert.assertEquals(
			existingDocProject.getTypeSettings(),
			newDocProject.getTypeSettings());
		Assert.assertEquals(
			existingDocProject.getStatus(), newDocProject.getStatus());
	}

	@Test
	public void testCountByUuid() throws Exception {
		_persistence.countByUuid("");

		_persistence.countByUuid("null");

		_persistence.countByUuid((String)null);
	}

	@Test
	public void testCountByUUID_G() throws Exception {
		_persistence.countByUUID_G("", RandomTestUtil.nextLong());

		_persistence.countByUUID_G("null", 0L);

		_persistence.countByUUID_G((String)null, 0L);
	}

	@Test
	public void testCountByUuid_C() throws Exception {
		_persistence.countByUuid_C("", RandomTestUtil.nextLong());

		_persistence.countByUuid_C("null", 0L);

		_persistence.countByUuid_C((String)null, 0L);
	}

	@Test
	public void testCountByGroupId() throws Exception {
		_persistence.countByGroupId(RandomTestUtil.nextLong());

		_persistence.countByGroupId(0L);
	}

	@Test
	public void testCountByName() throws Exception {
		_persistence.countByName("");

		_persistence.countByName("null");

		_persistence.countByName((String)null);
	}

	@Test
	public void testCountByU_S() throws Exception {
		_persistence.countByU_S(
			RandomTestUtil.randomBoolean(), RandomTestUtil.nextInt());

		_persistence.countByU_S(RandomTestUtil.randomBoolean(), 0);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		DocProject newDocProject = addDocProject();

		DocProject existingDocProject = _persistence.findByPrimaryKey(
			newDocProject.getPrimaryKey());

		Assert.assertEquals(existingDocProject, newDocProject);
	}

	@Test(expected = NoSuchDocProjectException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, getOrderByComparator());
	}

	protected OrderByComparator<DocProject> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create(
			"OSBCommunity_DocProject", "uuid", true, "docProjectId", true,
			"groupId", true, "companyId", true, "userId", true, "userName",
			true, "createDate", true, "modifiedDate", true, "name", true,
			"description", true, "iconFileName", true, "unlisted", true, "type",
			true, "status", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		DocProject newDocProject = addDocProject();

		DocProject existingDocProject = _persistence.fetchByPrimaryKey(
			newDocProject.getPrimaryKey());

		Assert.assertEquals(existingDocProject, newDocProject);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		DocProject missingDocProject = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingDocProject);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {

		DocProject newDocProject1 = addDocProject();
		DocProject newDocProject2 = addDocProject();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newDocProject1.getPrimaryKey());
		primaryKeys.add(newDocProject2.getPrimaryKey());

		Map<Serializable, DocProject> docProjects =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, docProjects.size());
		Assert.assertEquals(
			newDocProject1, docProjects.get(newDocProject1.getPrimaryKey()));
		Assert.assertEquals(
			newDocProject2, docProjects.get(newDocProject2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {

		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, DocProject> docProjects =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(docProjects.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {

		DocProject newDocProject = addDocProject();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newDocProject.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, DocProject> docProjects =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, docProjects.size());
		Assert.assertEquals(
			newDocProject, docProjects.get(newDocProject.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys() throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, DocProject> docProjects =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(docProjects.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey() throws Exception {
		DocProject newDocProject = addDocProject();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newDocProject.getPrimaryKey());

		Map<Serializable, DocProject> docProjects =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, docProjects.size());
		Assert.assertEquals(
			newDocProject, docProjects.get(newDocProject.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery =
			DocProjectLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<DocProject>() {

				@Override
				public void performAction(DocProject docProject) {
					Assert.assertNotNull(docProject);

					count.increment();
				}

			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting() throws Exception {
		DocProject newDocProject = addDocProject();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			DocProject.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"docProjectId", newDocProject.getDocProjectId()));

		List<DocProject> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(1, result.size());

		DocProject existingDocProject = result.get(0);

		Assert.assertEquals(existingDocProject, newDocProject);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			DocProject.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"docProjectId", RandomTestUtil.nextLong()));

		List<DocProject> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting() throws Exception {
		DocProject newDocProject = addDocProject();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			DocProject.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("docProjectId"));

		Object newDocProjectId = newDocProject.getDocProjectId();

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"docProjectId", new Object[] {newDocProjectId}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingDocProjectId = result.get(0);

		Assert.assertEquals(existingDocProjectId, newDocProjectId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			DocProject.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("docProjectId"));

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"docProjectId", new Object[] {RandomTestUtil.nextLong()}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		DocProject newDocProject = addDocProject();

		_persistence.clearCache();

		DocProject existingDocProject = _persistence.findByPrimaryKey(
			newDocProject.getPrimaryKey());

		Assert.assertEquals(
			existingDocProject.getUuid(),
			ReflectionTestUtil.invoke(
				existingDocProject, "getOriginalUuid", new Class<?>[0]));
		Assert.assertEquals(
			Long.valueOf(existingDocProject.getGroupId()),
			ReflectionTestUtil.<Long>invoke(
				existingDocProject, "getOriginalGroupId", new Class<?>[0]));

		Assert.assertEquals(
			Long.valueOf(existingDocProject.getGroupId()),
			ReflectionTestUtil.<Long>invoke(
				existingDocProject, "getOriginalGroupId", new Class<?>[0]));

		Assert.assertEquals(
			existingDocProject.getName(),
			ReflectionTestUtil.invoke(
				existingDocProject, "getOriginalName", new Class<?>[0]));
	}

	protected DocProject addDocProject() throws Exception {
		long pk = RandomTestUtil.nextLong();

		DocProject docProject = _persistence.create(pk);

		docProject.setUuid(RandomTestUtil.randomString());

		docProject.setGroupId(RandomTestUtil.nextLong());

		docProject.setCompanyId(RandomTestUtil.nextLong());

		docProject.setUserId(RandomTestUtil.nextLong());

		docProject.setUserName(RandomTestUtil.randomString());

		docProject.setCreateDate(RandomTestUtil.nextDate());

		docProject.setModifiedDate(RandomTestUtil.nextDate());

		docProject.setName(RandomTestUtil.randomString());

		docProject.setDescription(RandomTestUtil.randomString());

		docProject.setIconFileName(RandomTestUtil.randomString());

		docProject.setUnlisted(RandomTestUtil.randomBoolean());

		docProject.setType(RandomTestUtil.randomString());

		docProject.setTypeSettings(RandomTestUtil.randomString());

		docProject.setStatus(RandomTestUtil.nextInt());

		_docProjects.add(_persistence.update(docProject));

		return docProject;
	}

	private List<DocProject> _docProjects = new ArrayList<DocProject>();
	private DocProjectPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;

}