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

package com.liferay.osb.testray.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.testray.exception.NoSuchTestrayFactorException;
import com.liferay.osb.testray.model.TestrayFactor;
import com.liferay.osb.testray.model.impl.TestrayFactorImpl;
import com.liferay.osb.testray.model.impl.TestrayFactorModelImpl;
import com.liferay.osb.testray.service.persistence.TestrayFactorPersistence;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.CompanyProvider;
import com.liferay.portal.kernel.service.persistence.CompanyProviderWrapper;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the testray factor service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see TestrayFactorPersistence
 * @see com.liferay.osb.testray.service.persistence.TestrayFactorUtil
 * @generated
 */
@ProviderType
public class TestrayFactorPersistenceImpl extends BasePersistenceImpl<TestrayFactor>
	implements TestrayFactorPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link TestrayFactorUtil} to access the testray factor persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = TestrayFactorImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(TestrayFactorModelImpl.ENTITY_CACHE_ENABLED,
			TestrayFactorModelImpl.FINDER_CACHE_ENABLED,
			TestrayFactorImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(TestrayFactorModelImpl.ENTITY_CACHE_ENABLED,
			TestrayFactorModelImpl.FINDER_CACHE_ENABLED,
			TestrayFactorImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(TestrayFactorModelImpl.ENTITY_CACHE_ENABLED,
			TestrayFactorModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CNI_CP = new FinderPath(TestrayFactorModelImpl.ENTITY_CACHE_ENABLED,
			TestrayFactorModelImpl.FINDER_CACHE_ENABLED,
			TestrayFactorImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCNI_CP",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CNI_CP =
		new FinderPath(TestrayFactorModelImpl.ENTITY_CACHE_ENABLED,
			TestrayFactorModelImpl.FINDER_CACHE_ENABLED,
			TestrayFactorImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByCNI_CP",
			new String[] { Long.class.getName(), Long.class.getName() },
			TestrayFactorModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			TestrayFactorModelImpl.CLASSPK_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CNI_CP = new FinderPath(TestrayFactorModelImpl.ENTITY_CACHE_ENABLED,
			TestrayFactorModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCNI_CP",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the testray factors where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching testray factors
	 */
	@Override
	public List<TestrayFactor> findByCNI_CP(long classNameId, long classPK) {
		return findByCNI_CP(classNameId, classPK, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the testray factors where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayFactorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of testray factors
	 * @param end the upper bound of the range of testray factors (not inclusive)
	 * @return the range of matching testray factors
	 */
	@Override
	public List<TestrayFactor> findByCNI_CP(long classNameId, long classPK,
		int start, int end) {
		return findByCNI_CP(classNameId, classPK, start, end, null);
	}

	/**
	 * Returns an ordered range of all the testray factors where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayFactorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of testray factors
	 * @param end the upper bound of the range of testray factors (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching testray factors
	 */
	@Override
	public List<TestrayFactor> findByCNI_CP(long classNameId, long classPK,
		int start, int end, OrderByComparator<TestrayFactor> orderByComparator) {
		return findByCNI_CP(classNameId, classPK, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the testray factors where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayFactorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of testray factors
	 * @param end the upper bound of the range of testray factors (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching testray factors
	 */
	@Override
	public List<TestrayFactor> findByCNI_CP(long classNameId, long classPK,
		int start, int end, OrderByComparator<TestrayFactor> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CNI_CP;
			finderArgs = new Object[] { classNameId, classPK };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CNI_CP;
			finderArgs = new Object[] {
					classNameId, classPK,
					
					start, end, orderByComparator
				};
		}

		List<TestrayFactor> list = null;

		if (retrieveFromCache) {
			list = (List<TestrayFactor>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TestrayFactor testrayFactor : list) {
					if ((classNameId != testrayFactor.getClassNameId()) ||
							(classPK != testrayFactor.getClassPK())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_TESTRAYFACTOR_WHERE);

			query.append(_FINDER_COLUMN_CNI_CP_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_CNI_CP_CLASSPK_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(TestrayFactorModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				if (!pagination) {
					list = (List<TestrayFactor>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TestrayFactor>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first testray factor in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching testray factor
	 * @throws NoSuchTestrayFactorException if a matching testray factor could not be found
	 */
	@Override
	public TestrayFactor findByCNI_CP_First(long classNameId, long classPK,
		OrderByComparator<TestrayFactor> orderByComparator)
		throws NoSuchTestrayFactorException {
		TestrayFactor testrayFactor = fetchByCNI_CP_First(classNameId, classPK,
				orderByComparator);

		if (testrayFactor != null) {
			return testrayFactor;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTestrayFactorException(msg.toString());
	}

	/**
	 * Returns the first testray factor in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching testray factor, or <code>null</code> if a matching testray factor could not be found
	 */
	@Override
	public TestrayFactor fetchByCNI_CP_First(long classNameId, long classPK,
		OrderByComparator<TestrayFactor> orderByComparator) {
		List<TestrayFactor> list = findByCNI_CP(classNameId, classPK, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last testray factor in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching testray factor
	 * @throws NoSuchTestrayFactorException if a matching testray factor could not be found
	 */
	@Override
	public TestrayFactor findByCNI_CP_Last(long classNameId, long classPK,
		OrderByComparator<TestrayFactor> orderByComparator)
		throws NoSuchTestrayFactorException {
		TestrayFactor testrayFactor = fetchByCNI_CP_Last(classNameId, classPK,
				orderByComparator);

		if (testrayFactor != null) {
			return testrayFactor;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTestrayFactorException(msg.toString());
	}

	/**
	 * Returns the last testray factor in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching testray factor, or <code>null</code> if a matching testray factor could not be found
	 */
	@Override
	public TestrayFactor fetchByCNI_CP_Last(long classNameId, long classPK,
		OrderByComparator<TestrayFactor> orderByComparator) {
		int count = countByCNI_CP(classNameId, classPK);

		if (count == 0) {
			return null;
		}

		List<TestrayFactor> list = findByCNI_CP(classNameId, classPK,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the testray factors before and after the current testray factor in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param testrayFactorId the primary key of the current testray factor
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next testray factor
	 * @throws NoSuchTestrayFactorException if a testray factor with the primary key could not be found
	 */
	@Override
	public TestrayFactor[] findByCNI_CP_PrevAndNext(long testrayFactorId,
		long classNameId, long classPK,
		OrderByComparator<TestrayFactor> orderByComparator)
		throws NoSuchTestrayFactorException {
		TestrayFactor testrayFactor = findByPrimaryKey(testrayFactorId);

		Session session = null;

		try {
			session = openSession();

			TestrayFactor[] array = new TestrayFactorImpl[3];

			array[0] = getByCNI_CP_PrevAndNext(session, testrayFactor,
					classNameId, classPK, orderByComparator, true);

			array[1] = testrayFactor;

			array[2] = getByCNI_CP_PrevAndNext(session, testrayFactor,
					classNameId, classPK, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TestrayFactor getByCNI_CP_PrevAndNext(Session session,
		TestrayFactor testrayFactor, long classNameId, long classPK,
		OrderByComparator<TestrayFactor> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_TESTRAYFACTOR_WHERE);

		query.append(_FINDER_COLUMN_CNI_CP_CLASSNAMEID_2);

		query.append(_FINDER_COLUMN_CNI_CP_CLASSPK_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(TestrayFactorModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(classNameId);

		qPos.add(classPK);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(testrayFactor);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TestrayFactor> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the testray factors where classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 */
	@Override
	public void removeByCNI_CP(long classNameId, long classPK) {
		for (TestrayFactor testrayFactor : findByCNI_CP(classNameId, classPK,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(testrayFactor);
		}
	}

	/**
	 * Returns the number of testray factors where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the number of matching testray factors
	 */
	@Override
	public int countByCNI_CP(long classNameId, long classPK) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CNI_CP;

		Object[] finderArgs = new Object[] { classNameId, classPK };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TESTRAYFACTOR_WHERE);

			query.append(_FINDER_COLUMN_CNI_CP_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_CNI_CP_CLASSPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_CNI_CP_CLASSNAMEID_2 = "testrayFactor.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_CNI_CP_CLASSPK_2 = "testrayFactor.classPK = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_C_C_T = new FinderPath(TestrayFactorModelImpl.ENTITY_CACHE_ENABLED,
			TestrayFactorModelImpl.FINDER_CACHE_ENABLED,
			TestrayFactorImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByC_C_T",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			},
			TestrayFactorModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			TestrayFactorModelImpl.CLASSPK_COLUMN_BITMASK |
			TestrayFactorModelImpl.TESTRAYFACTOROPTIONID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_C_T = new FinderPath(TestrayFactorModelImpl.ENTITY_CACHE_ENABLED,
			TestrayFactorModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_C_T",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			});

	/**
	 * Returns the testray factor where classNameId = &#63; and classPK = &#63; and testrayFactorOptionId = &#63; or throws a {@link NoSuchTestrayFactorException} if it could not be found.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param testrayFactorOptionId the testray factor option ID
	 * @return the matching testray factor
	 * @throws NoSuchTestrayFactorException if a matching testray factor could not be found
	 */
	@Override
	public TestrayFactor findByC_C_T(long classNameId, long classPK,
		long testrayFactorOptionId) throws NoSuchTestrayFactorException {
		TestrayFactor testrayFactor = fetchByC_C_T(classNameId, classPK,
				testrayFactorOptionId);

		if (testrayFactor == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("classNameId=");
			msg.append(classNameId);

			msg.append(", classPK=");
			msg.append(classPK);

			msg.append(", testrayFactorOptionId=");
			msg.append(testrayFactorOptionId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchTestrayFactorException(msg.toString());
		}

		return testrayFactor;
	}

	/**
	 * Returns the testray factor where classNameId = &#63; and classPK = &#63; and testrayFactorOptionId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param testrayFactorOptionId the testray factor option ID
	 * @return the matching testray factor, or <code>null</code> if a matching testray factor could not be found
	 */
	@Override
	public TestrayFactor fetchByC_C_T(long classNameId, long classPK,
		long testrayFactorOptionId) {
		return fetchByC_C_T(classNameId, classPK, testrayFactorOptionId, true);
	}

	/**
	 * Returns the testray factor where classNameId = &#63; and classPK = &#63; and testrayFactorOptionId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param testrayFactorOptionId the testray factor option ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching testray factor, or <code>null</code> if a matching testray factor could not be found
	 */
	@Override
	public TestrayFactor fetchByC_C_T(long classNameId, long classPK,
		long testrayFactorOptionId, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] {
				classNameId, classPK, testrayFactorOptionId
			};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_C_C_T,
					finderArgs, this);
		}

		if (result instanceof TestrayFactor) {
			TestrayFactor testrayFactor = (TestrayFactor)result;

			if ((classNameId != testrayFactor.getClassNameId()) ||
					(classPK != testrayFactor.getClassPK()) ||
					(testrayFactorOptionId != testrayFactor.getTestrayFactorOptionId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_TESTRAYFACTOR_WHERE);

			query.append(_FINDER_COLUMN_C_C_T_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_T_CLASSPK_2);

			query.append(_FINDER_COLUMN_C_C_T_TESTRAYFACTOROPTIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				qPos.add(testrayFactorOptionId);

				List<TestrayFactor> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_C_C_T,
						finderArgs, list);
				}
				else {
					TestrayFactor testrayFactor = list.get(0);

					result = testrayFactor;

					cacheResult(testrayFactor);

					if ((testrayFactor.getClassNameId() != classNameId) ||
							(testrayFactor.getClassPK() != classPK) ||
							(testrayFactor.getTestrayFactorOptionId() != testrayFactorOptionId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_C_C_T,
							finderArgs, testrayFactor);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_C_C_T, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (TestrayFactor)result;
		}
	}

	/**
	 * Removes the testray factor where classNameId = &#63; and classPK = &#63; and testrayFactorOptionId = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param testrayFactorOptionId the testray factor option ID
	 * @return the testray factor that was removed
	 */
	@Override
	public TestrayFactor removeByC_C_T(long classNameId, long classPK,
		long testrayFactorOptionId) throws NoSuchTestrayFactorException {
		TestrayFactor testrayFactor = findByC_C_T(classNameId, classPK,
				testrayFactorOptionId);

		return remove(testrayFactor);
	}

	/**
	 * Returns the number of testray factors where classNameId = &#63; and classPK = &#63; and testrayFactorOptionId = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param testrayFactorOptionId the testray factor option ID
	 * @return the number of matching testray factors
	 */
	@Override
	public int countByC_C_T(long classNameId, long classPK,
		long testrayFactorOptionId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_C_T;

		Object[] finderArgs = new Object[] {
				classNameId, classPK, testrayFactorOptionId
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_TESTRAYFACTOR_WHERE);

			query.append(_FINDER_COLUMN_C_C_T_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_T_CLASSPK_2);

			query.append(_FINDER_COLUMN_C_C_T_TESTRAYFACTOROPTIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				qPos.add(testrayFactorOptionId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_C_C_T_CLASSNAMEID_2 = "testrayFactor.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_C_C_T_CLASSPK_2 = "testrayFactor.classPK = ? AND ";
	private static final String _FINDER_COLUMN_C_C_T_TESTRAYFACTOROPTIONID_2 = "testrayFactor.testrayFactorOptionId = ?";

	public TestrayFactorPersistenceImpl() {
		setModelClass(TestrayFactor.class);
	}

	/**
	 * Caches the testray factor in the entity cache if it is enabled.
	 *
	 * @param testrayFactor the testray factor
	 */
	@Override
	public void cacheResult(TestrayFactor testrayFactor) {
		entityCache.putResult(TestrayFactorModelImpl.ENTITY_CACHE_ENABLED,
			TestrayFactorImpl.class, testrayFactor.getPrimaryKey(),
			testrayFactor);

		finderCache.putResult(FINDER_PATH_FETCH_BY_C_C_T,
			new Object[] {
				testrayFactor.getClassNameId(), testrayFactor.getClassPK(),
				testrayFactor.getTestrayFactorOptionId()
			}, testrayFactor);

		testrayFactor.resetOriginalValues();
	}

	/**
	 * Caches the testray factors in the entity cache if it is enabled.
	 *
	 * @param testrayFactors the testray factors
	 */
	@Override
	public void cacheResult(List<TestrayFactor> testrayFactors) {
		for (TestrayFactor testrayFactor : testrayFactors) {
			if (entityCache.getResult(
						TestrayFactorModelImpl.ENTITY_CACHE_ENABLED,
						TestrayFactorImpl.class, testrayFactor.getPrimaryKey()) == null) {
				cacheResult(testrayFactor);
			}
			else {
				testrayFactor.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all testray factors.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(TestrayFactorImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the testray factor.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(TestrayFactor testrayFactor) {
		entityCache.removeResult(TestrayFactorModelImpl.ENTITY_CACHE_ENABLED,
			TestrayFactorImpl.class, testrayFactor.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((TestrayFactorModelImpl)testrayFactor, true);
	}

	@Override
	public void clearCache(List<TestrayFactor> testrayFactors) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (TestrayFactor testrayFactor : testrayFactors) {
			entityCache.removeResult(TestrayFactorModelImpl.ENTITY_CACHE_ENABLED,
				TestrayFactorImpl.class, testrayFactor.getPrimaryKey());

			clearUniqueFindersCache((TestrayFactorModelImpl)testrayFactor, true);
		}
	}

	protected void cacheUniqueFindersCache(
		TestrayFactorModelImpl testrayFactorModelImpl) {
		Object[] args = new Object[] {
				testrayFactorModelImpl.getClassNameId(),
				testrayFactorModelImpl.getClassPK(),
				testrayFactorModelImpl.getTestrayFactorOptionId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_C_C_T, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_C_C_T, args,
			testrayFactorModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		TestrayFactorModelImpl testrayFactorModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					testrayFactorModelImpl.getClassNameId(),
					testrayFactorModelImpl.getClassPK(),
					testrayFactorModelImpl.getTestrayFactorOptionId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_C_C_T, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_C_C_T, args);
		}

		if ((testrayFactorModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_C_C_T.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					testrayFactorModelImpl.getOriginalClassNameId(),
					testrayFactorModelImpl.getOriginalClassPK(),
					testrayFactorModelImpl.getOriginalTestrayFactorOptionId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_C_C_T, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_C_C_T, args);
		}
	}

	/**
	 * Creates a new testray factor with the primary key. Does not add the testray factor to the database.
	 *
	 * @param testrayFactorId the primary key for the new testray factor
	 * @return the new testray factor
	 */
	@Override
	public TestrayFactor create(long testrayFactorId) {
		TestrayFactor testrayFactor = new TestrayFactorImpl();

		testrayFactor.setNew(true);
		testrayFactor.setPrimaryKey(testrayFactorId);

		testrayFactor.setCompanyId(companyProvider.getCompanyId());

		return testrayFactor;
	}

	/**
	 * Removes the testray factor with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayFactorId the primary key of the testray factor
	 * @return the testray factor that was removed
	 * @throws NoSuchTestrayFactorException if a testray factor with the primary key could not be found
	 */
	@Override
	public TestrayFactor remove(long testrayFactorId)
		throws NoSuchTestrayFactorException {
		return remove((Serializable)testrayFactorId);
	}

	/**
	 * Removes the testray factor with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the testray factor
	 * @return the testray factor that was removed
	 * @throws NoSuchTestrayFactorException if a testray factor with the primary key could not be found
	 */
	@Override
	public TestrayFactor remove(Serializable primaryKey)
		throws NoSuchTestrayFactorException {
		Session session = null;

		try {
			session = openSession();

			TestrayFactor testrayFactor = (TestrayFactor)session.get(TestrayFactorImpl.class,
					primaryKey);

			if (testrayFactor == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTestrayFactorException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(testrayFactor);
		}
		catch (NoSuchTestrayFactorException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected TestrayFactor removeImpl(TestrayFactor testrayFactor) {
		testrayFactor = toUnwrappedModel(testrayFactor);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(testrayFactor)) {
				testrayFactor = (TestrayFactor)session.get(TestrayFactorImpl.class,
						testrayFactor.getPrimaryKeyObj());
			}

			if (testrayFactor != null) {
				session.delete(testrayFactor);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (testrayFactor != null) {
			clearCache(testrayFactor);
		}

		return testrayFactor;
	}

	@Override
	public TestrayFactor updateImpl(TestrayFactor testrayFactor) {
		testrayFactor = toUnwrappedModel(testrayFactor);

		boolean isNew = testrayFactor.isNew();

		TestrayFactorModelImpl testrayFactorModelImpl = (TestrayFactorModelImpl)testrayFactor;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (testrayFactor.getCreateDate() == null)) {
			if (serviceContext == null) {
				testrayFactor.setCreateDate(now);
			}
			else {
				testrayFactor.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!testrayFactorModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				testrayFactor.setModifiedDate(now);
			}
			else {
				testrayFactor.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (testrayFactor.isNew()) {
				session.save(testrayFactor);

				testrayFactor.setNew(false);
			}
			else {
				testrayFactor = (TestrayFactor)session.merge(testrayFactor);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!TestrayFactorModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					testrayFactorModelImpl.getClassNameId(),
					testrayFactorModelImpl.getClassPK()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_CNI_CP, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CNI_CP,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((testrayFactorModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CNI_CP.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						testrayFactorModelImpl.getOriginalClassNameId(),
						testrayFactorModelImpl.getOriginalClassPK()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_CNI_CP, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CNI_CP,
					args);

				args = new Object[] {
						testrayFactorModelImpl.getClassNameId(),
						testrayFactorModelImpl.getClassPK()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_CNI_CP, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CNI_CP,
					args);
			}
		}

		entityCache.putResult(TestrayFactorModelImpl.ENTITY_CACHE_ENABLED,
			TestrayFactorImpl.class, testrayFactor.getPrimaryKey(),
			testrayFactor, false);

		clearUniqueFindersCache(testrayFactorModelImpl, false);
		cacheUniqueFindersCache(testrayFactorModelImpl);

		testrayFactor.resetOriginalValues();

		return testrayFactor;
	}

	protected TestrayFactor toUnwrappedModel(TestrayFactor testrayFactor) {
		if (testrayFactor instanceof TestrayFactorImpl) {
			return testrayFactor;
		}

		TestrayFactorImpl testrayFactorImpl = new TestrayFactorImpl();

		testrayFactorImpl.setNew(testrayFactor.isNew());
		testrayFactorImpl.setPrimaryKey(testrayFactor.getPrimaryKey());

		testrayFactorImpl.setTestrayFactorId(testrayFactor.getTestrayFactorId());
		testrayFactorImpl.setGroupId(testrayFactor.getGroupId());
		testrayFactorImpl.setCompanyId(testrayFactor.getCompanyId());
		testrayFactorImpl.setUserId(testrayFactor.getUserId());
		testrayFactorImpl.setUserName(testrayFactor.getUserName());
		testrayFactorImpl.setCreateDate(testrayFactor.getCreateDate());
		testrayFactorImpl.setModifiedDate(testrayFactor.getModifiedDate());
		testrayFactorImpl.setClassNameId(testrayFactor.getClassNameId());
		testrayFactorImpl.setClassPK(testrayFactor.getClassPK());
		testrayFactorImpl.setTestrayFactorCategoryId(testrayFactor.getTestrayFactorCategoryId());
		testrayFactorImpl.setTestrayFactorCategoryName(testrayFactor.getTestrayFactorCategoryName());
		testrayFactorImpl.setTestrayFactorOptionId(testrayFactor.getTestrayFactorOptionId());
		testrayFactorImpl.setTestrayFactorOptionName(testrayFactor.getTestrayFactorOptionName());

		return testrayFactorImpl;
	}

	/**
	 * Returns the testray factor with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the testray factor
	 * @return the testray factor
	 * @throws NoSuchTestrayFactorException if a testray factor with the primary key could not be found
	 */
	@Override
	public TestrayFactor findByPrimaryKey(Serializable primaryKey)
		throws NoSuchTestrayFactorException {
		TestrayFactor testrayFactor = fetchByPrimaryKey(primaryKey);

		if (testrayFactor == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchTestrayFactorException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return testrayFactor;
	}

	/**
	 * Returns the testray factor with the primary key or throws a {@link NoSuchTestrayFactorException} if it could not be found.
	 *
	 * @param testrayFactorId the primary key of the testray factor
	 * @return the testray factor
	 * @throws NoSuchTestrayFactorException if a testray factor with the primary key could not be found
	 */
	@Override
	public TestrayFactor findByPrimaryKey(long testrayFactorId)
		throws NoSuchTestrayFactorException {
		return findByPrimaryKey((Serializable)testrayFactorId);
	}

	/**
	 * Returns the testray factor with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the testray factor
	 * @return the testray factor, or <code>null</code> if a testray factor with the primary key could not be found
	 */
	@Override
	public TestrayFactor fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(TestrayFactorModelImpl.ENTITY_CACHE_ENABLED,
				TestrayFactorImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		TestrayFactor testrayFactor = (TestrayFactor)serializable;

		if (testrayFactor == null) {
			Session session = null;

			try {
				session = openSession();

				testrayFactor = (TestrayFactor)session.get(TestrayFactorImpl.class,
						primaryKey);

				if (testrayFactor != null) {
					cacheResult(testrayFactor);
				}
				else {
					entityCache.putResult(TestrayFactorModelImpl.ENTITY_CACHE_ENABLED,
						TestrayFactorImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(TestrayFactorModelImpl.ENTITY_CACHE_ENABLED,
					TestrayFactorImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return testrayFactor;
	}

	/**
	 * Returns the testray factor with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param testrayFactorId the primary key of the testray factor
	 * @return the testray factor, or <code>null</code> if a testray factor with the primary key could not be found
	 */
	@Override
	public TestrayFactor fetchByPrimaryKey(long testrayFactorId) {
		return fetchByPrimaryKey((Serializable)testrayFactorId);
	}

	@Override
	public Map<Serializable, TestrayFactor> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, TestrayFactor> map = new HashMap<Serializable, TestrayFactor>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			TestrayFactor testrayFactor = fetchByPrimaryKey(primaryKey);

			if (testrayFactor != null) {
				map.put(primaryKey, testrayFactor);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(TestrayFactorModelImpl.ENTITY_CACHE_ENABLED,
					TestrayFactorImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (TestrayFactor)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_TESTRAYFACTOR_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append((long)primaryKey);

			query.append(StringPool.COMMA);
		}

		query.setIndex(query.index() - 1);

		query.append(StringPool.CLOSE_PARENTHESIS);

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (TestrayFactor testrayFactor : (List<TestrayFactor>)q.list()) {
				map.put(testrayFactor.getPrimaryKeyObj(), testrayFactor);

				cacheResult(testrayFactor);

				uncachedPrimaryKeys.remove(testrayFactor.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(TestrayFactorModelImpl.ENTITY_CACHE_ENABLED,
					TestrayFactorImpl.class, primaryKey, nullModel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the testray factors.
	 *
	 * @return the testray factors
	 */
	@Override
	public List<TestrayFactor> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the testray factors.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayFactorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray factors
	 * @param end the upper bound of the range of testray factors (not inclusive)
	 * @return the range of testray factors
	 */
	@Override
	public List<TestrayFactor> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the testray factors.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayFactorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray factors
	 * @param end the upper bound of the range of testray factors (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of testray factors
	 */
	@Override
	public List<TestrayFactor> findAll(int start, int end,
		OrderByComparator<TestrayFactor> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the testray factors.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayFactorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray factors
	 * @param end the upper bound of the range of testray factors (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of testray factors
	 */
	@Override
	public List<TestrayFactor> findAll(int start, int end,
		OrderByComparator<TestrayFactor> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<TestrayFactor> list = null;

		if (retrieveFromCache) {
			list = (List<TestrayFactor>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_TESTRAYFACTOR);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_TESTRAYFACTOR;

				if (pagination) {
					sql = sql.concat(TestrayFactorModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<TestrayFactor>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TestrayFactor>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the testray factors from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (TestrayFactor testrayFactor : findAll()) {
			remove(testrayFactor);
		}
	}

	/**
	 * Returns the number of testray factors.
	 *
	 * @return the number of testray factors
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_TESTRAYFACTOR);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY,
					count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return TestrayFactorModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the testray factor persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(TestrayFactorImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;
	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_TESTRAYFACTOR = "SELECT testrayFactor FROM TestrayFactor testrayFactor";
	private static final String _SQL_SELECT_TESTRAYFACTOR_WHERE_PKS_IN = "SELECT testrayFactor FROM TestrayFactor testrayFactor WHERE testrayFactorId IN (";
	private static final String _SQL_SELECT_TESTRAYFACTOR_WHERE = "SELECT testrayFactor FROM TestrayFactor testrayFactor WHERE ";
	private static final String _SQL_COUNT_TESTRAYFACTOR = "SELECT COUNT(testrayFactor) FROM TestrayFactor testrayFactor";
	private static final String _SQL_COUNT_TESTRAYFACTOR_WHERE = "SELECT COUNT(testrayFactor) FROM TestrayFactor testrayFactor WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "testrayFactor.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No TestrayFactor exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No TestrayFactor exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(TestrayFactorPersistenceImpl.class);
}