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

package com.liferay.osb.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.exception.NoSuchSupportTeamLanguageException;
import com.liferay.osb.model.SupportTeamLanguage;
import com.liferay.osb.model.impl.SupportTeamLanguageImpl;
import com.liferay.osb.model.impl.SupportTeamLanguageModelImpl;
import com.liferay.osb.service.persistence.SupportTeamLanguagePersistence;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the support team language service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SupportTeamLanguagePersistence
 * @see com.liferay.osb.service.persistence.SupportTeamLanguageUtil
 * @generated
 */
@ProviderType
public class SupportTeamLanguagePersistenceImpl extends BasePersistenceImpl<SupportTeamLanguage>
	implements SupportTeamLanguagePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link SupportTeamLanguageUtil} to access the support team language persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = SupportTeamLanguageImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(SupportTeamLanguageModelImpl.ENTITY_CACHE_ENABLED,
			SupportTeamLanguageModelImpl.FINDER_CACHE_ENABLED,
			SupportTeamLanguageImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(SupportTeamLanguageModelImpl.ENTITY_CACHE_ENABLED,
			SupportTeamLanguageModelImpl.FINDER_CACHE_ENABLED,
			SupportTeamLanguageImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SupportTeamLanguageModelImpl.ENTITY_CACHE_ENABLED,
			SupportTeamLanguageModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SUPPORTTEAMID =
		new FinderPath(SupportTeamLanguageModelImpl.ENTITY_CACHE_ENABLED,
			SupportTeamLanguageModelImpl.FINDER_CACHE_ENABLED,
			SupportTeamLanguageImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBySupportTeamId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTTEAMID =
		new FinderPath(SupportTeamLanguageModelImpl.ENTITY_CACHE_ENABLED,
			SupportTeamLanguageModelImpl.FINDER_CACHE_ENABLED,
			SupportTeamLanguageImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBySupportTeamId",
			new String[] { Long.class.getName() },
			SupportTeamLanguageModelImpl.SUPPORTTEAMID_COLUMN_BITMASK |
			SupportTeamLanguageModelImpl.LANGUAGEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SUPPORTTEAMID = new FinderPath(SupportTeamLanguageModelImpl.ENTITY_CACHE_ENABLED,
			SupportTeamLanguageModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBySupportTeamId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the support team languages where supportTeamId = &#63;.
	 *
	 * @param supportTeamId the support team ID
	 * @return the matching support team languages
	 */
	@Override
	public List<SupportTeamLanguage> findBySupportTeamId(long supportTeamId) {
		return findBySupportTeamId(supportTeamId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the support team languages where supportTeamId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportTeamLanguageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param supportTeamId the support team ID
	 * @param start the lower bound of the range of support team languages
	 * @param end the upper bound of the range of support team languages (not inclusive)
	 * @return the range of matching support team languages
	 */
	@Override
	public List<SupportTeamLanguage> findBySupportTeamId(long supportTeamId,
		int start, int end) {
		return findBySupportTeamId(supportTeamId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the support team languages where supportTeamId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportTeamLanguageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param supportTeamId the support team ID
	 * @param start the lower bound of the range of support team languages
	 * @param end the upper bound of the range of support team languages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching support team languages
	 */
	@Override
	public List<SupportTeamLanguage> findBySupportTeamId(long supportTeamId,
		int start, int end,
		OrderByComparator<SupportTeamLanguage> orderByComparator) {
		return findBySupportTeamId(supportTeamId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the support team languages where supportTeamId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportTeamLanguageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param supportTeamId the support team ID
	 * @param start the lower bound of the range of support team languages
	 * @param end the upper bound of the range of support team languages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching support team languages
	 */
	@Override
	public List<SupportTeamLanguage> findBySupportTeamId(long supportTeamId,
		int start, int end,
		OrderByComparator<SupportTeamLanguage> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTTEAMID;
			finderArgs = new Object[] { supportTeamId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SUPPORTTEAMID;
			finderArgs = new Object[] {
					supportTeamId,
					
					start, end, orderByComparator
				};
		}

		List<SupportTeamLanguage> list = null;

		if (retrieveFromCache) {
			list = (List<SupportTeamLanguage>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SupportTeamLanguage supportTeamLanguage : list) {
					if ((supportTeamId != supportTeamLanguage.getSupportTeamId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_SUPPORTTEAMLANGUAGE_WHERE);

			query.append(_FINDER_COLUMN_SUPPORTTEAMID_SUPPORTTEAMID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SupportTeamLanguageModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(supportTeamId);

				if (!pagination) {
					list = (List<SupportTeamLanguage>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SupportTeamLanguage>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Returns the first support team language in the ordered set where supportTeamId = &#63;.
	 *
	 * @param supportTeamId the support team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support team language
	 * @throws NoSuchSupportTeamLanguageException if a matching support team language could not be found
	 */
	@Override
	public SupportTeamLanguage findBySupportTeamId_First(long supportTeamId,
		OrderByComparator<SupportTeamLanguage> orderByComparator)
		throws NoSuchSupportTeamLanguageException {
		SupportTeamLanguage supportTeamLanguage = fetchBySupportTeamId_First(supportTeamId,
				orderByComparator);

		if (supportTeamLanguage != null) {
			return supportTeamLanguage;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("supportTeamId=");
		msg.append(supportTeamId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSupportTeamLanguageException(msg.toString());
	}

	/**
	 * Returns the first support team language in the ordered set where supportTeamId = &#63;.
	 *
	 * @param supportTeamId the support team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support team language, or <code>null</code> if a matching support team language could not be found
	 */
	@Override
	public SupportTeamLanguage fetchBySupportTeamId_First(long supportTeamId,
		OrderByComparator<SupportTeamLanguage> orderByComparator) {
		List<SupportTeamLanguage> list = findBySupportTeamId(supportTeamId, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last support team language in the ordered set where supportTeamId = &#63;.
	 *
	 * @param supportTeamId the support team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support team language
	 * @throws NoSuchSupportTeamLanguageException if a matching support team language could not be found
	 */
	@Override
	public SupportTeamLanguage findBySupportTeamId_Last(long supportTeamId,
		OrderByComparator<SupportTeamLanguage> orderByComparator)
		throws NoSuchSupportTeamLanguageException {
		SupportTeamLanguage supportTeamLanguage = fetchBySupportTeamId_Last(supportTeamId,
				orderByComparator);

		if (supportTeamLanguage != null) {
			return supportTeamLanguage;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("supportTeamId=");
		msg.append(supportTeamId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSupportTeamLanguageException(msg.toString());
	}

	/**
	 * Returns the last support team language in the ordered set where supportTeamId = &#63;.
	 *
	 * @param supportTeamId the support team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support team language, or <code>null</code> if a matching support team language could not be found
	 */
	@Override
	public SupportTeamLanguage fetchBySupportTeamId_Last(long supportTeamId,
		OrderByComparator<SupportTeamLanguage> orderByComparator) {
		int count = countBySupportTeamId(supportTeamId);

		if (count == 0) {
			return null;
		}

		List<SupportTeamLanguage> list = findBySupportTeamId(supportTeamId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the support team languages before and after the current support team language in the ordered set where supportTeamId = &#63;.
	 *
	 * @param supportTeamLanguageId the primary key of the current support team language
	 * @param supportTeamId the support team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next support team language
	 * @throws NoSuchSupportTeamLanguageException if a support team language with the primary key could not be found
	 */
	@Override
	public SupportTeamLanguage[] findBySupportTeamId_PrevAndNext(
		long supportTeamLanguageId, long supportTeamId,
		OrderByComparator<SupportTeamLanguage> orderByComparator)
		throws NoSuchSupportTeamLanguageException {
		SupportTeamLanguage supportTeamLanguage = findByPrimaryKey(supportTeamLanguageId);

		Session session = null;

		try {
			session = openSession();

			SupportTeamLanguage[] array = new SupportTeamLanguageImpl[3];

			array[0] = getBySupportTeamId_PrevAndNext(session,
					supportTeamLanguage, supportTeamId, orderByComparator, true);

			array[1] = supportTeamLanguage;

			array[2] = getBySupportTeamId_PrevAndNext(session,
					supportTeamLanguage, supportTeamId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SupportTeamLanguage getBySupportTeamId_PrevAndNext(
		Session session, SupportTeamLanguage supportTeamLanguage,
		long supportTeamId,
		OrderByComparator<SupportTeamLanguage> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SUPPORTTEAMLANGUAGE_WHERE);

		query.append(_FINDER_COLUMN_SUPPORTTEAMID_SUPPORTTEAMID_2);

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
			query.append(SupportTeamLanguageModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(supportTeamId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(supportTeamLanguage);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SupportTeamLanguage> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the support team languages where supportTeamId = &#63; from the database.
	 *
	 * @param supportTeamId the support team ID
	 */
	@Override
	public void removeBySupportTeamId(long supportTeamId) {
		for (SupportTeamLanguage supportTeamLanguage : findBySupportTeamId(
				supportTeamId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(supportTeamLanguage);
		}
	}

	/**
	 * Returns the number of support team languages where supportTeamId = &#63;.
	 *
	 * @param supportTeamId the support team ID
	 * @return the number of matching support team languages
	 */
	@Override
	public int countBySupportTeamId(long supportTeamId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SUPPORTTEAMID;

		Object[] finderArgs = new Object[] { supportTeamId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SUPPORTTEAMLANGUAGE_WHERE);

			query.append(_FINDER_COLUMN_SUPPORTTEAMID_SUPPORTTEAMID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(supportTeamId);

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

	private static final String _FINDER_COLUMN_SUPPORTTEAMID_SUPPORTTEAMID_2 = "supportTeamLanguage.supportTeamId = ?";

	public SupportTeamLanguagePersistenceImpl() {
		setModelClass(SupportTeamLanguage.class);
	}

	/**
	 * Caches the support team language in the entity cache if it is enabled.
	 *
	 * @param supportTeamLanguage the support team language
	 */
	@Override
	public void cacheResult(SupportTeamLanguage supportTeamLanguage) {
		entityCache.putResult(SupportTeamLanguageModelImpl.ENTITY_CACHE_ENABLED,
			SupportTeamLanguageImpl.class, supportTeamLanguage.getPrimaryKey(),
			supportTeamLanguage);

		supportTeamLanguage.resetOriginalValues();
	}

	/**
	 * Caches the support team languages in the entity cache if it is enabled.
	 *
	 * @param supportTeamLanguages the support team languages
	 */
	@Override
	public void cacheResult(List<SupportTeamLanguage> supportTeamLanguages) {
		for (SupportTeamLanguage supportTeamLanguage : supportTeamLanguages) {
			if (entityCache.getResult(
						SupportTeamLanguageModelImpl.ENTITY_CACHE_ENABLED,
						SupportTeamLanguageImpl.class,
						supportTeamLanguage.getPrimaryKey()) == null) {
				cacheResult(supportTeamLanguage);
			}
			else {
				supportTeamLanguage.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all support team languages.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(SupportTeamLanguageImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the support team language.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SupportTeamLanguage supportTeamLanguage) {
		entityCache.removeResult(SupportTeamLanguageModelImpl.ENTITY_CACHE_ENABLED,
			SupportTeamLanguageImpl.class, supportTeamLanguage.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<SupportTeamLanguage> supportTeamLanguages) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (SupportTeamLanguage supportTeamLanguage : supportTeamLanguages) {
			entityCache.removeResult(SupportTeamLanguageModelImpl.ENTITY_CACHE_ENABLED,
				SupportTeamLanguageImpl.class,
				supportTeamLanguage.getPrimaryKey());
		}
	}

	/**
	 * Creates a new support team language with the primary key. Does not add the support team language to the database.
	 *
	 * @param supportTeamLanguageId the primary key for the new support team language
	 * @return the new support team language
	 */
	@Override
	public SupportTeamLanguage create(long supportTeamLanguageId) {
		SupportTeamLanguage supportTeamLanguage = new SupportTeamLanguageImpl();

		supportTeamLanguage.setNew(true);
		supportTeamLanguage.setPrimaryKey(supportTeamLanguageId);

		return supportTeamLanguage;
	}

	/**
	 * Removes the support team language with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param supportTeamLanguageId the primary key of the support team language
	 * @return the support team language that was removed
	 * @throws NoSuchSupportTeamLanguageException if a support team language with the primary key could not be found
	 */
	@Override
	public SupportTeamLanguage remove(long supportTeamLanguageId)
		throws NoSuchSupportTeamLanguageException {
		return remove((Serializable)supportTeamLanguageId);
	}

	/**
	 * Removes the support team language with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the support team language
	 * @return the support team language that was removed
	 * @throws NoSuchSupportTeamLanguageException if a support team language with the primary key could not be found
	 */
	@Override
	public SupportTeamLanguage remove(Serializable primaryKey)
		throws NoSuchSupportTeamLanguageException {
		Session session = null;

		try {
			session = openSession();

			SupportTeamLanguage supportTeamLanguage = (SupportTeamLanguage)session.get(SupportTeamLanguageImpl.class,
					primaryKey);

			if (supportTeamLanguage == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSupportTeamLanguageException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(supportTeamLanguage);
		}
		catch (NoSuchSupportTeamLanguageException nsee) {
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
	protected SupportTeamLanguage removeImpl(
		SupportTeamLanguage supportTeamLanguage) {
		supportTeamLanguage = toUnwrappedModel(supportTeamLanguage);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(supportTeamLanguage)) {
				supportTeamLanguage = (SupportTeamLanguage)session.get(SupportTeamLanguageImpl.class,
						supportTeamLanguage.getPrimaryKeyObj());
			}

			if (supportTeamLanguage != null) {
				session.delete(supportTeamLanguage);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (supportTeamLanguage != null) {
			clearCache(supportTeamLanguage);
		}

		return supportTeamLanguage;
	}

	@Override
	public SupportTeamLanguage updateImpl(
		SupportTeamLanguage supportTeamLanguage) {
		supportTeamLanguage = toUnwrappedModel(supportTeamLanguage);

		boolean isNew = supportTeamLanguage.isNew();

		SupportTeamLanguageModelImpl supportTeamLanguageModelImpl = (SupportTeamLanguageModelImpl)supportTeamLanguage;

		Session session = null;

		try {
			session = openSession();

			if (supportTeamLanguage.isNew()) {
				session.save(supportTeamLanguage);

				supportTeamLanguage.setNew(false);
			}
			else {
				supportTeamLanguage = (SupportTeamLanguage)session.merge(supportTeamLanguage);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!SupportTeamLanguageModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					supportTeamLanguageModelImpl.getSupportTeamId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_SUPPORTTEAMID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTTEAMID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((supportTeamLanguageModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTTEAMID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						supportTeamLanguageModelImpl.getOriginalSupportTeamId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_SUPPORTTEAMID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTTEAMID,
					args);

				args = new Object[] {
						supportTeamLanguageModelImpl.getSupportTeamId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_SUPPORTTEAMID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTTEAMID,
					args);
			}
		}

		entityCache.putResult(SupportTeamLanguageModelImpl.ENTITY_CACHE_ENABLED,
			SupportTeamLanguageImpl.class, supportTeamLanguage.getPrimaryKey(),
			supportTeamLanguage, false);

		supportTeamLanguage.resetOriginalValues();

		return supportTeamLanguage;
	}

	protected SupportTeamLanguage toUnwrappedModel(
		SupportTeamLanguage supportTeamLanguage) {
		if (supportTeamLanguage instanceof SupportTeamLanguageImpl) {
			return supportTeamLanguage;
		}

		SupportTeamLanguageImpl supportTeamLanguageImpl = new SupportTeamLanguageImpl();

		supportTeamLanguageImpl.setNew(supportTeamLanguage.isNew());
		supportTeamLanguageImpl.setPrimaryKey(supportTeamLanguage.getPrimaryKey());

		supportTeamLanguageImpl.setSupportTeamLanguageId(supportTeamLanguage.getSupportTeamLanguageId());
		supportTeamLanguageImpl.setSupportTeamId(supportTeamLanguage.getSupportTeamId());
		supportTeamLanguageImpl.setLanguageId(supportTeamLanguage.getLanguageId());

		return supportTeamLanguageImpl;
	}

	/**
	 * Returns the support team language with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the support team language
	 * @return the support team language
	 * @throws NoSuchSupportTeamLanguageException if a support team language with the primary key could not be found
	 */
	@Override
	public SupportTeamLanguage findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSupportTeamLanguageException {
		SupportTeamLanguage supportTeamLanguage = fetchByPrimaryKey(primaryKey);

		if (supportTeamLanguage == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSupportTeamLanguageException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return supportTeamLanguage;
	}

	/**
	 * Returns the support team language with the primary key or throws a {@link NoSuchSupportTeamLanguageException} if it could not be found.
	 *
	 * @param supportTeamLanguageId the primary key of the support team language
	 * @return the support team language
	 * @throws NoSuchSupportTeamLanguageException if a support team language with the primary key could not be found
	 */
	@Override
	public SupportTeamLanguage findByPrimaryKey(long supportTeamLanguageId)
		throws NoSuchSupportTeamLanguageException {
		return findByPrimaryKey((Serializable)supportTeamLanguageId);
	}

	/**
	 * Returns the support team language with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the support team language
	 * @return the support team language, or <code>null</code> if a support team language with the primary key could not be found
	 */
	@Override
	public SupportTeamLanguage fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(SupportTeamLanguageModelImpl.ENTITY_CACHE_ENABLED,
				SupportTeamLanguageImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		SupportTeamLanguage supportTeamLanguage = (SupportTeamLanguage)serializable;

		if (supportTeamLanguage == null) {
			Session session = null;

			try {
				session = openSession();

				supportTeamLanguage = (SupportTeamLanguage)session.get(SupportTeamLanguageImpl.class,
						primaryKey);

				if (supportTeamLanguage != null) {
					cacheResult(supportTeamLanguage);
				}
				else {
					entityCache.putResult(SupportTeamLanguageModelImpl.ENTITY_CACHE_ENABLED,
						SupportTeamLanguageImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(SupportTeamLanguageModelImpl.ENTITY_CACHE_ENABLED,
					SupportTeamLanguageImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return supportTeamLanguage;
	}

	/**
	 * Returns the support team language with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param supportTeamLanguageId the primary key of the support team language
	 * @return the support team language, or <code>null</code> if a support team language with the primary key could not be found
	 */
	@Override
	public SupportTeamLanguage fetchByPrimaryKey(long supportTeamLanguageId) {
		return fetchByPrimaryKey((Serializable)supportTeamLanguageId);
	}

	@Override
	public Map<Serializable, SupportTeamLanguage> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, SupportTeamLanguage> map = new HashMap<Serializable, SupportTeamLanguage>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			SupportTeamLanguage supportTeamLanguage = fetchByPrimaryKey(primaryKey);

			if (supportTeamLanguage != null) {
				map.put(primaryKey, supportTeamLanguage);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(SupportTeamLanguageModelImpl.ENTITY_CACHE_ENABLED,
					SupportTeamLanguageImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (SupportTeamLanguage)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_SUPPORTTEAMLANGUAGE_WHERE_PKS_IN);

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

			for (SupportTeamLanguage supportTeamLanguage : (List<SupportTeamLanguage>)q.list()) {
				map.put(supportTeamLanguage.getPrimaryKeyObj(),
					supportTeamLanguage);

				cacheResult(supportTeamLanguage);

				uncachedPrimaryKeys.remove(supportTeamLanguage.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(SupportTeamLanguageModelImpl.ENTITY_CACHE_ENABLED,
					SupportTeamLanguageImpl.class, primaryKey, nullModel);
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
	 * Returns all the support team languages.
	 *
	 * @return the support team languages
	 */
	@Override
	public List<SupportTeamLanguage> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the support team languages.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportTeamLanguageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of support team languages
	 * @param end the upper bound of the range of support team languages (not inclusive)
	 * @return the range of support team languages
	 */
	@Override
	public List<SupportTeamLanguage> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the support team languages.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportTeamLanguageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of support team languages
	 * @param end the upper bound of the range of support team languages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of support team languages
	 */
	@Override
	public List<SupportTeamLanguage> findAll(int start, int end,
		OrderByComparator<SupportTeamLanguage> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the support team languages.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportTeamLanguageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of support team languages
	 * @param end the upper bound of the range of support team languages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of support team languages
	 */
	@Override
	public List<SupportTeamLanguage> findAll(int start, int end,
		OrderByComparator<SupportTeamLanguage> orderByComparator,
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

		List<SupportTeamLanguage> list = null;

		if (retrieveFromCache) {
			list = (List<SupportTeamLanguage>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_SUPPORTTEAMLANGUAGE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SUPPORTTEAMLANGUAGE;

				if (pagination) {
					sql = sql.concat(SupportTeamLanguageModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<SupportTeamLanguage>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SupportTeamLanguage>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Removes all the support team languages from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (SupportTeamLanguage supportTeamLanguage : findAll()) {
			remove(supportTeamLanguage);
		}
	}

	/**
	 * Returns the number of support team languages.
	 *
	 * @return the number of support team languages
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_SUPPORTTEAMLANGUAGE);

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
		return SupportTeamLanguageModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the support team language persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(SupportTeamLanguageImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_SUPPORTTEAMLANGUAGE = "SELECT supportTeamLanguage FROM SupportTeamLanguage supportTeamLanguage";
	private static final String _SQL_SELECT_SUPPORTTEAMLANGUAGE_WHERE_PKS_IN = "SELECT supportTeamLanguage FROM SupportTeamLanguage supportTeamLanguage WHERE supportTeamLanguageId IN (";
	private static final String _SQL_SELECT_SUPPORTTEAMLANGUAGE_WHERE = "SELECT supportTeamLanguage FROM SupportTeamLanguage supportTeamLanguage WHERE ";
	private static final String _SQL_COUNT_SUPPORTTEAMLANGUAGE = "SELECT COUNT(supportTeamLanguage) FROM SupportTeamLanguage supportTeamLanguage";
	private static final String _SQL_COUNT_SUPPORTTEAMLANGUAGE_WHERE = "SELECT COUNT(supportTeamLanguage) FROM SupportTeamLanguage supportTeamLanguage WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "supportTeamLanguage.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No SupportTeamLanguage exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No SupportTeamLanguage exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(SupportTeamLanguagePersistenceImpl.class);
}