/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.pulpo.connector.de.service.persistence.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;

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

import com.liferay.pulpo.connector.de.exception.NoSuchConnectorTransactionException;
import com.liferay.pulpo.connector.de.model.ConnectorTransaction;
import com.liferay.pulpo.connector.de.service.ConnectorTransactionLocalServiceUtil;
import com.liferay.pulpo.connector.de.service.persistence.ConnectorTransactionPersistence;
import com.liferay.pulpo.connector.de.service.persistence.ConnectorTransactionUtil;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.junit.runner.RunWith;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @generated
 */
@RunWith(Arquillian.class)
public class ConnectorTransactionPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.liferay.pulpo.connector.de.service"));

	@Before
	public void setUp() {
		_persistence = ConnectorTransactionUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<ConnectorTransaction> iterator = _connectorTransactions.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		ConnectorTransaction connectorTransaction = _persistence.create(pk);

		Assert.assertNotNull(connectorTransaction);

		Assert.assertEquals(connectorTransaction.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		ConnectorTransaction newConnectorTransaction = addConnectorTransaction();

		_persistence.remove(newConnectorTransaction);

		ConnectorTransaction existingConnectorTransaction = _persistence.fetchByPrimaryKey(newConnectorTransaction.getPrimaryKey());

		Assert.assertNull(existingConnectorTransaction);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addConnectorTransaction();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		ConnectorTransaction newConnectorTransaction = _persistence.create(pk);

		newConnectorTransaction.setCompanyId(RandomTestUtil.nextLong());

		newConnectorTransaction.setUserId(RandomTestUtil.nextLong());

		newConnectorTransaction.setUserName(RandomTestUtil.randomString());

		newConnectorTransaction.setCreateDate(RandomTestUtil.nextDate());

		newConnectorTransaction.setModifiedDate(RandomTestUtil.nextDate());

		newConnectorTransaction.setClassNameId(RandomTestUtil.nextLong());

		newConnectorTransaction.setClassPK(RandomTestUtil.nextLong());

		newConnectorTransaction.setConnectorTransactionUuid(RandomTestUtil.randomString());

		newConnectorTransaction.setOperation(RandomTestUtil.randomString());

		newConnectorTransaction.setStatus(RandomTestUtil.randomString());

		_connectorTransactions.add(_persistence.update(newConnectorTransaction));

		ConnectorTransaction existingConnectorTransaction = _persistence.findByPrimaryKey(newConnectorTransaction.getPrimaryKey());

		Assert.assertEquals(existingConnectorTransaction.getConnectorTransactionId(),
			newConnectorTransaction.getConnectorTransactionId());
		Assert.assertEquals(existingConnectorTransaction.getCompanyId(),
			newConnectorTransaction.getCompanyId());
		Assert.assertEquals(existingConnectorTransaction.getUserId(),
			newConnectorTransaction.getUserId());
		Assert.assertEquals(existingConnectorTransaction.getUserName(),
			newConnectorTransaction.getUserName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingConnectorTransaction.getCreateDate()),
			Time.getShortTimestamp(newConnectorTransaction.getCreateDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingConnectorTransaction.getModifiedDate()),
			Time.getShortTimestamp(newConnectorTransaction.getModifiedDate()));
		Assert.assertEquals(existingConnectorTransaction.getClassNameId(),
			newConnectorTransaction.getClassNameId());
		Assert.assertEquals(existingConnectorTransaction.getClassPK(),
			newConnectorTransaction.getClassPK());
		Assert.assertEquals(existingConnectorTransaction.getConnectorTransactionUuid(),
			newConnectorTransaction.getConnectorTransactionUuid());
		Assert.assertEquals(existingConnectorTransaction.getOperation(),
			newConnectorTransaction.getOperation());
		Assert.assertEquals(existingConnectorTransaction.getStatus(),
			newConnectorTransaction.getStatus());
	}

	@Test
	public void testCountByConnectorTransactionUUID() throws Exception {
		_persistence.countByConnectorTransactionUUID("");

		_persistence.countByConnectorTransactionUUID("null");

		_persistence.countByConnectorTransactionUUID((String)null);
	}

	@Test
	public void testCountByC_C() throws Exception {
		_persistence.countByC_C(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong());

		_persistence.countByC_C(0L, 0L);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		ConnectorTransaction newConnectorTransaction = addConnectorTransaction();

		ConnectorTransaction existingConnectorTransaction = _persistence.findByPrimaryKey(newConnectorTransaction.getPrimaryKey());

		Assert.assertEquals(existingConnectorTransaction,
			newConnectorTransaction);
	}

	@Test(expected = NoSuchConnectorTransactionException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<ConnectorTransaction> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("PULPO_ConnectorTransaction",
			"connectorTransactionId", true, "companyId", true, "userId", true,
			"userName", true, "createDate", true, "modifiedDate", true,
			"classNameId", true, "classPK", true, "connectorTransactionUuid",
			true, "operation", true, "status", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		ConnectorTransaction newConnectorTransaction = addConnectorTransaction();

		ConnectorTransaction existingConnectorTransaction = _persistence.fetchByPrimaryKey(newConnectorTransaction.getPrimaryKey());

		Assert.assertEquals(existingConnectorTransaction,
			newConnectorTransaction);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		ConnectorTransaction missingConnectorTransaction = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingConnectorTransaction);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		ConnectorTransaction newConnectorTransaction1 = addConnectorTransaction();
		ConnectorTransaction newConnectorTransaction2 = addConnectorTransaction();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newConnectorTransaction1.getPrimaryKey());
		primaryKeys.add(newConnectorTransaction2.getPrimaryKey());

		Map<Serializable, ConnectorTransaction> connectorTransactions = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, connectorTransactions.size());
		Assert.assertEquals(newConnectorTransaction1,
			connectorTransactions.get(newConnectorTransaction1.getPrimaryKey()));
		Assert.assertEquals(newConnectorTransaction2,
			connectorTransactions.get(newConnectorTransaction2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, ConnectorTransaction> connectorTransactions = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(connectorTransactions.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		ConnectorTransaction newConnectorTransaction = addConnectorTransaction();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newConnectorTransaction.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, ConnectorTransaction> connectorTransactions = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, connectorTransactions.size());
		Assert.assertEquals(newConnectorTransaction,
			connectorTransactions.get(newConnectorTransaction.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, ConnectorTransaction> connectorTransactions = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(connectorTransactions.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		ConnectorTransaction newConnectorTransaction = addConnectorTransaction();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newConnectorTransaction.getPrimaryKey());

		Map<Serializable, ConnectorTransaction> connectorTransactions = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, connectorTransactions.size());
		Assert.assertEquals(newConnectorTransaction,
			connectorTransactions.get(newConnectorTransaction.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = ConnectorTransactionLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<ConnectorTransaction>() {
				@Override
				public void performAction(
					ConnectorTransaction connectorTransaction) {
					Assert.assertNotNull(connectorTransaction);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		ConnectorTransaction newConnectorTransaction = addConnectorTransaction();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ConnectorTransaction.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("connectorTransactionId",
				newConnectorTransaction.getConnectorTransactionId()));

		List<ConnectorTransaction> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		ConnectorTransaction existingConnectorTransaction = result.get(0);

		Assert.assertEquals(existingConnectorTransaction,
			newConnectorTransaction);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ConnectorTransaction.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("connectorTransactionId",
				RandomTestUtil.nextLong()));

		List<ConnectorTransaction> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		ConnectorTransaction newConnectorTransaction = addConnectorTransaction();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ConnectorTransaction.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"connectorTransactionId"));

		Object newConnectorTransactionId = newConnectorTransaction.getConnectorTransactionId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("connectorTransactionId",
				new Object[] { newConnectorTransactionId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingConnectorTransactionId = result.get(0);

		Assert.assertEquals(existingConnectorTransactionId,
			newConnectorTransactionId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ConnectorTransaction.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"connectorTransactionId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("connectorTransactionId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		ConnectorTransaction newConnectorTransaction = addConnectorTransaction();

		_persistence.clearCache();

		ConnectorTransaction existingConnectorTransaction = _persistence.findByPrimaryKey(newConnectorTransaction.getPrimaryKey());

		Assert.assertTrue(Objects.equals(
				existingConnectorTransaction.getConnectorTransactionUuid(),
				ReflectionTestUtil.invoke(existingConnectorTransaction,
					"getOriginalConnectorTransactionUuid", new Class<?>[0])));

		Assert.assertEquals(Long.valueOf(
				existingConnectorTransaction.getClassNameId()),
			ReflectionTestUtil.<Long>invoke(existingConnectorTransaction,
				"getOriginalClassNameId", new Class<?>[0]));
		Assert.assertEquals(Long.valueOf(
				existingConnectorTransaction.getClassPK()),
			ReflectionTestUtil.<Long>invoke(existingConnectorTransaction,
				"getOriginalClassPK", new Class<?>[0]));
	}

	protected ConnectorTransaction addConnectorTransaction()
		throws Exception {
		long pk = RandomTestUtil.nextLong();

		ConnectorTransaction connectorTransaction = _persistence.create(pk);

		connectorTransaction.setCompanyId(RandomTestUtil.nextLong());

		connectorTransaction.setUserId(RandomTestUtil.nextLong());

		connectorTransaction.setUserName(RandomTestUtil.randomString());

		connectorTransaction.setCreateDate(RandomTestUtil.nextDate());

		connectorTransaction.setModifiedDate(RandomTestUtil.nextDate());

		connectorTransaction.setClassNameId(RandomTestUtil.nextLong());

		connectorTransaction.setClassPK(RandomTestUtil.nextLong());

		connectorTransaction.setConnectorTransactionUuid(RandomTestUtil.randomString());

		connectorTransaction.setOperation(RandomTestUtil.randomString());

		connectorTransaction.setStatus(RandomTestUtil.randomString());

		_connectorTransactions.add(_persistence.update(connectorTransaction));

		return connectorTransaction;
	}

	private List<ConnectorTransaction> _connectorTransactions = new ArrayList<ConnectorTransaction>();
	private ConnectorTransactionPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}