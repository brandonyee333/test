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

package com.liferay.osb.service.persistence;

import com.liferay.osb.NoSuchSupportTeamLanguageException;
import com.liferay.osb.model.SupportTeamLanguage;
import com.liferay.osb.model.impl.SupportTeamLanguageImpl;
import com.liferay.osb.model.impl.SupportTeamLanguageModelImpl;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ClassNamePersistence;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the support team language service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SupportTeamLanguagePersistence
 * @see SupportTeamLanguageUtil
 * @generated
 */
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
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SUPPORTTEAMID =
		new FinderPath(SupportTeamLanguageModelImpl.ENTITY_CACHE_ENABLED,
			SupportTeamLanguageModelImpl.FINDER_CACHE_ENABLED,
			SupportTeamLanguageImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBySupportTeamId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTTEAMID =
		new FinderPath(SupportTeamLanguageModelImpl.ENTITY_CACHE_ENABLED,
			SupportTeamLanguageModelImpl.FINDER_CACHE_ENABLED,
			SupportTeamLanguageImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBySupportTeamId",
			new String[] { Long.class.getName() },
			SupportTeamLanguageModelImpl.SUPPORTTEAMID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SUPPORTTEAMID = new FinderPath(SupportTeamLanguageModelImpl.ENTITY_CACHE_ENABLED,
			SupportTeamLanguageModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBySupportTeamId",
			new String[] { Long.class.getName() });
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

	/**
	 * Caches the support team language in the entity cache if it is enabled.
	 *
	 * @param supportTeamLanguage the support team language
	 */
	public void cacheResult(SupportTeamLanguage supportTeamLanguage) {
		EntityCacheUtil.putResult(SupportTeamLanguageModelImpl.ENTITY_CACHE_ENABLED,
			SupportTeamLanguageImpl.class, supportTeamLanguage.getPrimaryKey(),
			supportTeamLanguage);

		supportTeamLanguage.resetOriginalValues();
	}

	/**
	 * Caches the support team languages in the entity cache if it is enabled.
	 *
	 * @param supportTeamLanguages the support team languages
	 */
	public void cacheResult(List<SupportTeamLanguage> supportTeamLanguages) {
		for (SupportTeamLanguage supportTeamLanguage : supportTeamLanguages) {
			if (EntityCacheUtil.getResult(
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
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(SupportTeamLanguageImpl.class.getName());
		}

		EntityCacheUtil.clearCache(SupportTeamLanguageImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the support team language.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SupportTeamLanguage supportTeamLanguage) {
		EntityCacheUtil.removeResult(SupportTeamLanguageModelImpl.ENTITY_CACHE_ENABLED,
			SupportTeamLanguageImpl.class, supportTeamLanguage.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<SupportTeamLanguage> supportTeamLanguages) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (SupportTeamLanguage supportTeamLanguage : supportTeamLanguages) {
			EntityCacheUtil.removeResult(SupportTeamLanguageModelImpl.ENTITY_CACHE_ENABLED,
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
	 * @throws com.liferay.osb.NoSuchSupportTeamLanguageException if a support team language with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SupportTeamLanguage remove(long supportTeamLanguageId)
		throws NoSuchSupportTeamLanguageException, SystemException {
		return remove(Long.valueOf(supportTeamLanguageId));
	}

	/**
	 * Removes the support team language with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the support team language
	 * @return the support team language that was removed
	 * @throws com.liferay.osb.NoSuchSupportTeamLanguageException if a support team language with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SupportTeamLanguage remove(Serializable primaryKey)
		throws NoSuchSupportTeamLanguageException, SystemException {
		Session session = null;

		try {
			session = openSession();

			SupportTeamLanguage supportTeamLanguage = (SupportTeamLanguage)session.get(SupportTeamLanguageImpl.class,
					primaryKey);

			if (supportTeamLanguage == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
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
		SupportTeamLanguage supportTeamLanguage) throws SystemException {
		supportTeamLanguage = toUnwrappedModel(supportTeamLanguage);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, supportTeamLanguage);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(supportTeamLanguage);

		return supportTeamLanguage;
	}

	@Override
	public SupportTeamLanguage updateImpl(
		com.liferay.osb.model.SupportTeamLanguage supportTeamLanguage,
		boolean merge) throws SystemException {
		supportTeamLanguage = toUnwrappedModel(supportTeamLanguage);

		boolean isNew = supportTeamLanguage.isNew();

		SupportTeamLanguageModelImpl supportTeamLanguageModelImpl = (SupportTeamLanguageModelImpl)supportTeamLanguage;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, supportTeamLanguage, merge);

			supportTeamLanguage.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !SupportTeamLanguageModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((supportTeamLanguageModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTTEAMID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(supportTeamLanguageModelImpl.getOriginalSupportTeamId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SUPPORTTEAMID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTTEAMID,
					args);

				args = new Object[] {
						Long.valueOf(supportTeamLanguageModelImpl.getSupportTeamId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SUPPORTTEAMID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTTEAMID,
					args);
			}
		}

		EntityCacheUtil.putResult(SupportTeamLanguageModelImpl.ENTITY_CACHE_ENABLED,
			SupportTeamLanguageImpl.class, supportTeamLanguage.getPrimaryKey(),
			supportTeamLanguage);

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
	 * Returns the support team language with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the support team language
	 * @return the support team language
	 * @throws com.liferay.portal.NoSuchModelException if a support team language with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SupportTeamLanguage findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the support team language with the primary key or throws a {@link com.liferay.osb.NoSuchSupportTeamLanguageException} if it could not be found.
	 *
	 * @param supportTeamLanguageId the primary key of the support team language
	 * @return the support team language
	 * @throws com.liferay.osb.NoSuchSupportTeamLanguageException if a support team language with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SupportTeamLanguage findByPrimaryKey(long supportTeamLanguageId)
		throws NoSuchSupportTeamLanguageException, SystemException {
		SupportTeamLanguage supportTeamLanguage = fetchByPrimaryKey(supportTeamLanguageId);

		if (supportTeamLanguage == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					supportTeamLanguageId);
			}

			throw new NoSuchSupportTeamLanguageException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				supportTeamLanguageId);
		}

		return supportTeamLanguage;
	}

	/**
	 * Returns the support team language with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the support team language
	 * @return the support team language, or <code>null</code> if a support team language with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SupportTeamLanguage fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the support team language with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param supportTeamLanguageId the primary key of the support team language
	 * @return the support team language, or <code>null</code> if a support team language with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SupportTeamLanguage fetchByPrimaryKey(long supportTeamLanguageId)
		throws SystemException {
		SupportTeamLanguage supportTeamLanguage = (SupportTeamLanguage)EntityCacheUtil.getResult(SupportTeamLanguageModelImpl.ENTITY_CACHE_ENABLED,
				SupportTeamLanguageImpl.class, supportTeamLanguageId);

		if (supportTeamLanguage == _nullSupportTeamLanguage) {
			return null;
		}

		if (supportTeamLanguage == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				supportTeamLanguage = (SupportTeamLanguage)session.get(SupportTeamLanguageImpl.class,
						Long.valueOf(supportTeamLanguageId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (supportTeamLanguage != null) {
					cacheResult(supportTeamLanguage);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(SupportTeamLanguageModelImpl.ENTITY_CACHE_ENABLED,
						SupportTeamLanguageImpl.class, supportTeamLanguageId,
						_nullSupportTeamLanguage);
				}

				closeSession(session);
			}
		}

		return supportTeamLanguage;
	}

	/**
	 * Returns all the support team languages where supportTeamId = &#63;.
	 *
	 * @param supportTeamId the support team ID
	 * @return the matching support team languages
	 * @throws SystemException if a system exception occurred
	 */
	public List<SupportTeamLanguage> findBySupportTeamId(long supportTeamId)
		throws SystemException {
		return findBySupportTeamId(supportTeamId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the support team languages where supportTeamId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param supportTeamId the support team ID
	 * @param start the lower bound of the range of support team languages
	 * @param end the upper bound of the range of support team languages (not inclusive)
	 * @return the range of matching support team languages
	 * @throws SystemException if a system exception occurred
	 */
	public List<SupportTeamLanguage> findBySupportTeamId(long supportTeamId,
		int start, int end) throws SystemException {
		return findBySupportTeamId(supportTeamId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the support team languages where supportTeamId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param supportTeamId the support team ID
	 * @param start the lower bound of the range of support team languages
	 * @param end the upper bound of the range of support team languages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching support team languages
	 * @throws SystemException if a system exception occurred
	 */
	public List<SupportTeamLanguage> findBySupportTeamId(long supportTeamId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
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

		List<SupportTeamLanguage> list = (List<SupportTeamLanguage>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (SupportTeamLanguage supportTeamLanguage : list) {
				if ((supportTeamId != supportTeamLanguage.getSupportTeamId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
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

			else {
				query.append(SupportTeamLanguageModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(supportTeamId);

				list = (List<SupportTeamLanguage>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

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
	 * @throws com.liferay.osb.NoSuchSupportTeamLanguageException if a matching support team language could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SupportTeamLanguage findBySupportTeamId_First(long supportTeamId,
		OrderByComparator orderByComparator)
		throws NoSuchSupportTeamLanguageException, SystemException {
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
	 * @throws SystemException if a system exception occurred
	 */
	public SupportTeamLanguage fetchBySupportTeamId_First(long supportTeamId,
		OrderByComparator orderByComparator) throws SystemException {
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
	 * @throws com.liferay.osb.NoSuchSupportTeamLanguageException if a matching support team language could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SupportTeamLanguage findBySupportTeamId_Last(long supportTeamId,
		OrderByComparator orderByComparator)
		throws NoSuchSupportTeamLanguageException, SystemException {
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
	 * @throws SystemException if a system exception occurred
	 */
	public SupportTeamLanguage fetchBySupportTeamId_Last(long supportTeamId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countBySupportTeamId(supportTeamId);

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
	 * @throws com.liferay.osb.NoSuchSupportTeamLanguageException if a support team language with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SupportTeamLanguage[] findBySupportTeamId_PrevAndNext(
		long supportTeamLanguageId, long supportTeamId,
		OrderByComparator orderByComparator)
		throws NoSuchSupportTeamLanguageException, SystemException {
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
		long supportTeamId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
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
	 * Returns all the support team languages.
	 *
	 * @return the support team languages
	 * @throws SystemException if a system exception occurred
	 */
	public List<SupportTeamLanguage> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the support team languages.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of support team languages
	 * @param end the upper bound of the range of support team languages (not inclusive)
	 * @return the range of support team languages
	 * @throws SystemException if a system exception occurred
	 */
	public List<SupportTeamLanguage> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the support team languages.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of support team languages
	 * @param end the upper bound of the range of support team languages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of support team languages
	 * @throws SystemException if a system exception occurred
	 */
	public List<SupportTeamLanguage> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = new Object[] { start, end, orderByComparator };

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<SupportTeamLanguage> list = (List<SupportTeamLanguage>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_SUPPORTTEAMLANGUAGE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SUPPORTTEAMLANGUAGE.concat(SupportTeamLanguageModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<SupportTeamLanguage>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<SupportTeamLanguage>)QueryUtil.list(q,
							getDialect(), start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the support team languages where supportTeamId = &#63; from the database.
	 *
	 * @param supportTeamId the support team ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeBySupportTeamId(long supportTeamId)
		throws SystemException {
		for (SupportTeamLanguage supportTeamLanguage : findBySupportTeamId(
				supportTeamId)) {
			remove(supportTeamLanguage);
		}
	}

	/**
	 * Removes all the support team languages from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (SupportTeamLanguage supportTeamLanguage : findAll()) {
			remove(supportTeamLanguage);
		}
	}

	/**
	 * Returns the number of support team languages where supportTeamId = &#63;.
	 *
	 * @param supportTeamId the support team ID
	 * @return the number of matching support team languages
	 * @throws SystemException if a system exception occurred
	 */
	public int countBySupportTeamId(long supportTeamId)
		throws SystemException {
		Object[] finderArgs = new Object[] { supportTeamId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_SUPPORTTEAMID,
				finderArgs, this);

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
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_SUPPORTTEAMID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of support team languages.
	 *
	 * @return the number of support team languages
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_SUPPORTTEAMLANGUAGE);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Initializes the support team language persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.osb.model.SupportTeamLanguage")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<SupportTeamLanguage>> listenersList = new ArrayList<ModelListener<SupportTeamLanguage>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<SupportTeamLanguage>)InstanceFactory.newInstance(
							clazz.getClassLoader(), listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(SupportTeamLanguageImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@BeanReference(type = AccountAttachmentPersistence.class)
	protected AccountAttachmentPersistence accountAttachmentPersistence;
	@BeanReference(type = AccountCallPersistence.class)
	protected AccountCallPersistence accountCallPersistence;
	@BeanReference(type = AccountCustomerPersistence.class)
	protected AccountCustomerPersistence accountCustomerPersistence;
	@BeanReference(type = AccountEntryPersistence.class)
	protected AccountEntryPersistence accountEntryPersistence;
	@BeanReference(type = AccountEntryLanguagePersistence.class)
	protected AccountEntryLanguagePersistence accountEntryLanguagePersistence;
	@BeanReference(type = AccountEnvironmentPersistence.class)
	protected AccountEnvironmentPersistence accountEnvironmentPersistence;
	@BeanReference(type = AccountEnvironmentAttachmentPersistence.class)
	protected AccountEnvironmentAttachmentPersistence accountEnvironmentAttachmentPersistence;
	@BeanReference(type = AccountInformationPersistence.class)
	protected AccountInformationPersistence accountInformationPersistence;
	@BeanReference(type = AccountLinkPersistence.class)
	protected AccountLinkPersistence accountLinkPersistence;
	@BeanReference(type = AccountProjectPersistence.class)
	protected AccountProjectPersistence accountProjectPersistence;
	@BeanReference(type = AccountWorkerPersistence.class)
	protected AccountWorkerPersistence accountWorkerPersistence;
	@BeanReference(type = AppAuditPersistence.class)
	protected AppAuditPersistence appAuditPersistence;
	@BeanReference(type = AppEntryPersistence.class)
	protected AppEntryPersistence appEntryPersistence;
	@BeanReference(type = AppEntryRelPersistence.class)
	protected AppEntryRelPersistence appEntryRelPersistence;
	@BeanReference(type = AppFlagPersistence.class)
	protected AppFlagPersistence appFlagPersistence;
	@BeanReference(type = AppPackagePersistence.class)
	protected AppPackagePersistence appPackagePersistence;
	@BeanReference(type = AppPackagePluginPersistence.class)
	protected AppPackagePluginPersistence appPackagePluginPersistence;
	@BeanReference(type = AppPricingPersistence.class)
	protected AppPricingPersistence appPricingPersistence;
	@BeanReference(type = AppPricingItemPersistence.class)
	protected AppPricingItemPersistence appPricingItemPersistence;
	@BeanReference(type = AppVersionPersistence.class)
	protected AppVersionPersistence appVersionPersistence;
	@BeanReference(type = AssetAttachmentPersistence.class)
	protected AssetAttachmentPersistence assetAttachmentPersistence;
	@BeanReference(type = AssetAuditPersistence.class)
	protected AssetAuditPersistence assetAuditPersistence;
	@BeanReference(type = AssetLicensePersistence.class)
	protected AssetLicensePersistence assetLicensePersistence;
	@BeanReference(type = AssetListPersistence.class)
	protected AssetListPersistence assetListPersistence;
	@BeanReference(type = AssetListAssetEntryPersistence.class)
	protected AssetListAssetEntryPersistence assetListAssetEntryPersistence;
	@BeanReference(type = AssetReceiptPersistence.class)
	protected AssetReceiptPersistence assetReceiptPersistence;
	@BeanReference(type = AssetReceiptLicensePersistence.class)
	protected AssetReceiptLicensePersistence assetReceiptLicensePersistence;
	@BeanReference(type = AssetReceiptRedeemTokenPersistence.class)
	protected AssetReceiptRedeemTokenPersistence assetReceiptRedeemTokenPersistence;
	@BeanReference(type = AssetReceiptSupportPersistence.class)
	protected AssetReceiptSupportPersistence assetReceiptSupportPersistence;
	@BeanReference(type = AssetRecommendationEntryPersistence.class)
	protected AssetRecommendationEntryPersistence assetRecommendationEntryPersistence;
	@BeanReference(type = AssetRecommendationSetPersistence.class)
	protected AssetRecommendationSetPersistence assetRecommendationSetPersistence;
	@BeanReference(type = AssetStatsDayPersistence.class)
	protected AssetStatsDayPersistence assetStatsDayPersistence;
	@BeanReference(type = AssetStatsMonthPersistence.class)
	protected AssetStatsMonthPersistence assetStatsMonthPersistence;
	@BeanReference(type = AssetStatsWeekPersistence.class)
	protected AssetStatsWeekPersistence assetStatsWeekPersistence;
	@BeanReference(type = AuditActionPersistence.class)
	protected AuditActionPersistence auditActionPersistence;
	@BeanReference(type = AuditEntryPersistence.class)
	protected AuditEntryPersistence auditEntryPersistence;
	@BeanReference(type = ContractAuditPersistence.class)
	protected ContractAuditPersistence contractAuditPersistence;
	@BeanReference(type = ContractEntryPersistence.class)
	protected ContractEntryPersistence contractEntryPersistence;
	@BeanReference(type = CorpEntryPersistence.class)
	protected CorpEntryPersistence corpEntryPersistence;
	@BeanReference(type = CorpGroupPersistence.class)
	protected CorpGroupPersistence corpGroupPersistence;
	@BeanReference(type = CorpMembershipRequestPersistence.class)
	protected CorpMembershipRequestPersistence corpMembershipRequestPersistence;
	@BeanReference(type = CorpProjectPersistence.class)
	protected CorpProjectPersistence corpProjectPersistence;
	@BeanReference(type = CorpProjectMessagePersistence.class)
	protected CorpProjectMessagePersistence corpProjectMessagePersistence;
	@BeanReference(type = CountryAppPricingPersistence.class)
	protected CountryAppPricingPersistence countryAppPricingPersistence;
	@BeanReference(type = CurrencyEntryPersistence.class)
	protected CurrencyEntryPersistence currencyEntryPersistence;
	@BeanReference(type = DeveloperEntryPersistence.class)
	protected DeveloperEntryPersistence developerEntryPersistence;
	@BeanReference(type = ExternalIdMapperPersistence.class)
	protected ExternalIdMapperPersistence externalIdMapperPersistence;
	@BeanReference(type = FeedbackEntryPersistence.class)
	protected FeedbackEntryPersistence feedbackEntryPersistence;
	@BeanReference(type = HolidayCalendarPersistence.class)
	protected HolidayCalendarPersistence holidayCalendarPersistence;
	@BeanReference(type = HolidayCalendarRelPersistence.class)
	protected HolidayCalendarRelPersistence holidayCalendarRelPersistence;
	@BeanReference(type = HolidayEntryPersistence.class)
	protected HolidayEntryPersistence holidayEntryPersistence;
	@BeanReference(type = LCSSubscriptionEntryPersistence.class)
	protected LCSSubscriptionEntryPersistence lcsSubscriptionEntryPersistence;
	@BeanReference(type = LicenseEntryPersistence.class)
	protected LicenseEntryPersistence licenseEntryPersistence;
	@BeanReference(type = LicenseKeyPersistence.class)
	protected LicenseKeyPersistence licenseKeyPersistence;
	@BeanReference(type = LicenseKeySetPersistence.class)
	protected LicenseKeySetPersistence licenseKeySetPersistence;
	@BeanReference(type = MarketingEventPersistence.class)
	protected MarketingEventPersistence marketingEventPersistence;
	@BeanReference(type = OfferingBundlePersistence.class)
	protected OfferingBundlePersistence offeringBundlePersistence;
	@BeanReference(type = OfferingDefinitionPersistence.class)
	protected OfferingDefinitionPersistence offeringDefinitionPersistence;
	@BeanReference(type = OfferingEntryPersistence.class)
	protected OfferingEntryPersistence offeringEntryPersistence;
	@BeanReference(type = OrderEntryPersistence.class)
	protected OrderEntryPersistence orderEntryPersistence;
	@BeanReference(type = PartnerEntryPersistence.class)
	protected PartnerEntryPersistence partnerEntryPersistence;
	@BeanReference(type = PartnerWorkerPersistence.class)
	protected PartnerWorkerPersistence partnerWorkerPersistence;
	@BeanReference(type = PortalReleasePersistence.class)
	protected PortalReleasePersistence portalReleasePersistence;
	@BeanReference(type = ProductEntryPersistence.class)
	protected ProductEntryPersistence productEntryPersistence;
	@BeanReference(type = SearchFilterPersistence.class)
	protected SearchFilterPersistence searchFilterPersistence;
	@BeanReference(type = SecurityPatchPersistence.class)
	protected SecurityPatchPersistence securityPatchPersistence;
	@BeanReference(type = SupportLaborPersistence.class)
	protected SupportLaborPersistence supportLaborPersistence;
	@BeanReference(type = SupportRegionPersistence.class)
	protected SupportRegionPersistence supportRegionPersistence;
	@BeanReference(type = SupportResponsePersistence.class)
	protected SupportResponsePersistence supportResponsePersistence;
	@BeanReference(type = SupportTeamPersistence.class)
	protected SupportTeamPersistence supportTeamPersistence;
	@BeanReference(type = SupportTeamLanguagePersistence.class)
	protected SupportTeamLanguagePersistence supportTeamLanguagePersistence;
	@BeanReference(type = SupportWorkerPersistence.class)
	protected SupportWorkerPersistence supportWorkerPersistence;
	@BeanReference(type = SupportWorkerAccountTierPersistence.class)
	protected SupportWorkerAccountTierPersistence supportWorkerAccountTierPersistence;
	@BeanReference(type = SupportWorkerComponentPersistence.class)
	protected SupportWorkerComponentPersistence supportWorkerComponentPersistence;
	@BeanReference(type = SupportWorkerSeverityPersistence.class)
	protected SupportWorkerSeverityPersistence supportWorkerSeverityPersistence;
	@BeanReference(type = TicketAttachmentPersistence.class)
	protected TicketAttachmentPersistence ticketAttachmentPersistence;
	@BeanReference(type = TicketCallPersistence.class)
	protected TicketCallPersistence ticketCallPersistence;
	@BeanReference(type = TicketCannedResponsePersistence.class)
	protected TicketCannedResponsePersistence ticketCannedResponsePersistence;
	@BeanReference(type = TicketCommentPersistence.class)
	protected TicketCommentPersistence ticketCommentPersistence;
	@BeanReference(type = TicketEntryPersistence.class)
	protected TicketEntryPersistence ticketEntryPersistence;
	@BeanReference(type = TicketFeedbackPersistence.class)
	protected TicketFeedbackPersistence ticketFeedbackPersistence;
	@BeanReference(type = TicketFlagPersistence.class)
	protected TicketFlagPersistence ticketFlagPersistence;
	@BeanReference(type = TicketInformationPersistence.class)
	protected TicketInformationPersistence ticketInformationPersistence;
	@BeanReference(type = TicketLinkPersistence.class)
	protected TicketLinkPersistence ticketLinkPersistence;
	@BeanReference(type = TicketSolutionPersistence.class)
	protected TicketSolutionPersistence ticketSolutionPersistence;
	@BeanReference(type = TicketWorkerPersistence.class)
	protected TicketWorkerPersistence ticketWorkerPersistence;
	@BeanReference(type = TrainingCertificatePersistence.class)
	protected TrainingCertificatePersistence trainingCertificatePersistence;
	@BeanReference(type = TrainingCertificateTemplatePersistence.class)
	protected TrainingCertificateTemplatePersistence trainingCertificateTemplatePersistence;
	@BeanReference(type = TrainingCoursePersistence.class)
	protected TrainingCoursePersistence trainingCoursePersistence;
	@BeanReference(type = TrainingCustomerPersistence.class)
	protected TrainingCustomerPersistence trainingCustomerPersistence;
	@BeanReference(type = TrainingEventPersistence.class)
	protected TrainingEventPersistence trainingEventPersistence;
	@BeanReference(type = TrainingExamPersistence.class)
	protected TrainingExamPersistence trainingExamPersistence;
	@BeanReference(type = TrainingExamResultPersistence.class)
	protected TrainingExamResultPersistence trainingExamResultPersistence;
	@BeanReference(type = TrainingExamResultItemPersistence.class)
	protected TrainingExamResultItemPersistence trainingExamResultItemPersistence;
	@BeanReference(type = TrainingExamResultSectionPersistence.class)
	protected TrainingExamResultSectionPersistence trainingExamResultSectionPersistence;
	@BeanReference(type = TrainingImportLogPersistence.class)
	protected TrainingImportLogPersistence trainingImportLogPersistence;
	@BeanReference(type = TrainingLinkedUserPersistence.class)
	protected TrainingLinkedUserPersistence trainingLinkedUserPersistence;
	@BeanReference(type = TrainingLocationPersistence.class)
	protected TrainingLocationPersistence trainingLocationPersistence;
	@BeanReference(type = TrainingWorkerPersistence.class)
	protected TrainingWorkerPersistence trainingWorkerPersistence;
	@BeanReference(type = UserProfilePersistence.class)
	protected UserProfilePersistence userProfilePersistence;
	@BeanReference(type = UserProfileHistoryPersistence.class)
	protected UserProfileHistoryPersistence userProfileHistoryPersistence;
	@BeanReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_SUPPORTTEAMLANGUAGE = "SELECT supportTeamLanguage FROM SupportTeamLanguage supportTeamLanguage";
	private static final String _SQL_SELECT_SUPPORTTEAMLANGUAGE_WHERE = "SELECT supportTeamLanguage FROM SupportTeamLanguage supportTeamLanguage WHERE ";
	private static final String _SQL_COUNT_SUPPORTTEAMLANGUAGE = "SELECT COUNT(supportTeamLanguage) FROM SupportTeamLanguage supportTeamLanguage";
	private static final String _SQL_COUNT_SUPPORTTEAMLANGUAGE_WHERE = "SELECT COUNT(supportTeamLanguage) FROM SupportTeamLanguage supportTeamLanguage WHERE ";
	private static final String _FINDER_COLUMN_SUPPORTTEAMID_SUPPORTTEAMID_2 = "supportTeamLanguage.supportTeamId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "supportTeamLanguage.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No SupportTeamLanguage exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No SupportTeamLanguage exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(SupportTeamLanguagePersistenceImpl.class);
	private static SupportTeamLanguage _nullSupportTeamLanguage = new SupportTeamLanguageImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<SupportTeamLanguage> toCacheModel() {
				return _nullSupportTeamLanguageCacheModel;
			}
		};

	private static CacheModel<SupportTeamLanguage> _nullSupportTeamLanguageCacheModel =
		new CacheModel<SupportTeamLanguage>() {
			public SupportTeamLanguage toEntityModel() {
				return _nullSupportTeamLanguage;
			}
		};
}