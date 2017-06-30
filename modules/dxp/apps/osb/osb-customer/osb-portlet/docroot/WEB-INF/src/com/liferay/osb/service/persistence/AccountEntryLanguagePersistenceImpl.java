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

import com.liferay.osb.NoSuchAccountEntryLanguageException;
import com.liferay.osb.model.AccountEntryLanguage;
import com.liferay.osb.model.impl.AccountEntryLanguageImpl;
import com.liferay.osb.model.impl.AccountEntryLanguageModelImpl;

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
 * The persistence implementation for the account entry language service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountEntryLanguagePersistence
 * @see AccountEntryLanguageUtil
 * @generated
 */
public class AccountEntryLanguagePersistenceImpl extends BasePersistenceImpl<AccountEntryLanguage>
	implements AccountEntryLanguagePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AccountEntryLanguageUtil} to access the account entry language persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AccountEntryLanguageImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ACCOUNTENTRYID =
		new FinderPath(AccountEntryLanguageModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryLanguageModelImpl.FINDER_CACHE_ENABLED,
			AccountEntryLanguageImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAccountEntryId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID =
		new FinderPath(AccountEntryLanguageModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryLanguageModelImpl.FINDER_CACHE_ENABLED,
			AccountEntryLanguageImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAccountEntryId",
			new String[] { Long.class.getName() },
			AccountEntryLanguageModelImpl.ACCOUNTENTRYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ACCOUNTENTRYID = new FinderPath(AccountEntryLanguageModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryLanguageModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAccountEntryId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AccountEntryLanguageModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryLanguageModelImpl.FINDER_CACHE_ENABLED,
			AccountEntryLanguageImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AccountEntryLanguageModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryLanguageModelImpl.FINDER_CACHE_ENABLED,
			AccountEntryLanguageImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AccountEntryLanguageModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryLanguageModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the account entry language in the entity cache if it is enabled.
	 *
	 * @param accountEntryLanguage the account entry language
	 */
	public void cacheResult(AccountEntryLanguage accountEntryLanguage) {
		EntityCacheUtil.putResult(AccountEntryLanguageModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryLanguageImpl.class,
			accountEntryLanguage.getPrimaryKey(), accountEntryLanguage);

		accountEntryLanguage.resetOriginalValues();
	}

	/**
	 * Caches the account entry languages in the entity cache if it is enabled.
	 *
	 * @param accountEntryLanguages the account entry languages
	 */
	public void cacheResult(List<AccountEntryLanguage> accountEntryLanguages) {
		for (AccountEntryLanguage accountEntryLanguage : accountEntryLanguages) {
			if (EntityCacheUtil.getResult(
						AccountEntryLanguageModelImpl.ENTITY_CACHE_ENABLED,
						AccountEntryLanguageImpl.class,
						accountEntryLanguage.getPrimaryKey()) == null) {
				cacheResult(accountEntryLanguage);
			}
			else {
				accountEntryLanguage.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all account entry languages.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(AccountEntryLanguageImpl.class.getName());
		}

		EntityCacheUtil.clearCache(AccountEntryLanguageImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the account entry language.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AccountEntryLanguage accountEntryLanguage) {
		EntityCacheUtil.removeResult(AccountEntryLanguageModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryLanguageImpl.class, accountEntryLanguage.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<AccountEntryLanguage> accountEntryLanguages) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AccountEntryLanguage accountEntryLanguage : accountEntryLanguages) {
			EntityCacheUtil.removeResult(AccountEntryLanguageModelImpl.ENTITY_CACHE_ENABLED,
				AccountEntryLanguageImpl.class,
				accountEntryLanguage.getPrimaryKey());
		}
	}

	/**
	 * Creates a new account entry language with the primary key. Does not add the account entry language to the database.
	 *
	 * @param accountEntryLanguageId the primary key for the new account entry language
	 * @return the new account entry language
	 */
	public AccountEntryLanguage create(long accountEntryLanguageId) {
		AccountEntryLanguage accountEntryLanguage = new AccountEntryLanguageImpl();

		accountEntryLanguage.setNew(true);
		accountEntryLanguage.setPrimaryKey(accountEntryLanguageId);

		return accountEntryLanguage;
	}

	/**
	 * Removes the account entry language with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param accountEntryLanguageId the primary key of the account entry language
	 * @return the account entry language that was removed
	 * @throws com.liferay.osb.NoSuchAccountEntryLanguageException if a account entry language with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEntryLanguage remove(long accountEntryLanguageId)
		throws NoSuchAccountEntryLanguageException, SystemException {
		return remove(Long.valueOf(accountEntryLanguageId));
	}

	/**
	 * Removes the account entry language with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the account entry language
	 * @return the account entry language that was removed
	 * @throws com.liferay.osb.NoSuchAccountEntryLanguageException if a account entry language with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AccountEntryLanguage remove(Serializable primaryKey)
		throws NoSuchAccountEntryLanguageException, SystemException {
		Session session = null;

		try {
			session = openSession();

			AccountEntryLanguage accountEntryLanguage = (AccountEntryLanguage)session.get(AccountEntryLanguageImpl.class,
					primaryKey);

			if (accountEntryLanguage == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAccountEntryLanguageException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(accountEntryLanguage);
		}
		catch (NoSuchAccountEntryLanguageException nsee) {
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
	protected AccountEntryLanguage removeImpl(
		AccountEntryLanguage accountEntryLanguage) throws SystemException {
		accountEntryLanguage = toUnwrappedModel(accountEntryLanguage);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, accountEntryLanguage);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(accountEntryLanguage);

		return accountEntryLanguage;
	}

	@Override
	public AccountEntryLanguage updateImpl(
		com.liferay.osb.model.AccountEntryLanguage accountEntryLanguage,
		boolean merge) throws SystemException {
		accountEntryLanguage = toUnwrappedModel(accountEntryLanguage);

		boolean isNew = accountEntryLanguage.isNew();

		AccountEntryLanguageModelImpl accountEntryLanguageModelImpl = (AccountEntryLanguageModelImpl)accountEntryLanguage;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, accountEntryLanguage, merge);

			accountEntryLanguage.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !AccountEntryLanguageModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((accountEntryLanguageModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(accountEntryLanguageModelImpl.getOriginalAccountEntryId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTENTRYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID,
					args);

				args = new Object[] {
						Long.valueOf(accountEntryLanguageModelImpl.getAccountEntryId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTENTRYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID,
					args);
			}
		}

		EntityCacheUtil.putResult(AccountEntryLanguageModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryLanguageImpl.class,
			accountEntryLanguage.getPrimaryKey(), accountEntryLanguage);

		return accountEntryLanguage;
	}

	protected AccountEntryLanguage toUnwrappedModel(
		AccountEntryLanguage accountEntryLanguage) {
		if (accountEntryLanguage instanceof AccountEntryLanguageImpl) {
			return accountEntryLanguage;
		}

		AccountEntryLanguageImpl accountEntryLanguageImpl = new AccountEntryLanguageImpl();

		accountEntryLanguageImpl.setNew(accountEntryLanguage.isNew());
		accountEntryLanguageImpl.setPrimaryKey(accountEntryLanguage.getPrimaryKey());

		accountEntryLanguageImpl.setAccountEntryLanguageId(accountEntryLanguage.getAccountEntryLanguageId());
		accountEntryLanguageImpl.setAccountEntryId(accountEntryLanguage.getAccountEntryId());
		accountEntryLanguageImpl.setLanguageId(accountEntryLanguage.getLanguageId());

		return accountEntryLanguageImpl;
	}

	/**
	 * Returns the account entry language with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the account entry language
	 * @return the account entry language
	 * @throws com.liferay.portal.NoSuchModelException if a account entry language with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AccountEntryLanguage findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the account entry language with the primary key or throws a {@link com.liferay.osb.NoSuchAccountEntryLanguageException} if it could not be found.
	 *
	 * @param accountEntryLanguageId the primary key of the account entry language
	 * @return the account entry language
	 * @throws com.liferay.osb.NoSuchAccountEntryLanguageException if a account entry language with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEntryLanguage findByPrimaryKey(long accountEntryLanguageId)
		throws NoSuchAccountEntryLanguageException, SystemException {
		AccountEntryLanguage accountEntryLanguage = fetchByPrimaryKey(accountEntryLanguageId);

		if (accountEntryLanguage == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					accountEntryLanguageId);
			}

			throw new NoSuchAccountEntryLanguageException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				accountEntryLanguageId);
		}

		return accountEntryLanguage;
	}

	/**
	 * Returns the account entry language with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the account entry language
	 * @return the account entry language, or <code>null</code> if a account entry language with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AccountEntryLanguage fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the account entry language with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param accountEntryLanguageId the primary key of the account entry language
	 * @return the account entry language, or <code>null</code> if a account entry language with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEntryLanguage fetchByPrimaryKey(long accountEntryLanguageId)
		throws SystemException {
		AccountEntryLanguage accountEntryLanguage = (AccountEntryLanguage)EntityCacheUtil.getResult(AccountEntryLanguageModelImpl.ENTITY_CACHE_ENABLED,
				AccountEntryLanguageImpl.class, accountEntryLanguageId);

		if (accountEntryLanguage == _nullAccountEntryLanguage) {
			return null;
		}

		if (accountEntryLanguage == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				accountEntryLanguage = (AccountEntryLanguage)session.get(AccountEntryLanguageImpl.class,
						Long.valueOf(accountEntryLanguageId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (accountEntryLanguage != null) {
					cacheResult(accountEntryLanguage);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(AccountEntryLanguageModelImpl.ENTITY_CACHE_ENABLED,
						AccountEntryLanguageImpl.class, accountEntryLanguageId,
						_nullAccountEntryLanguage);
				}

				closeSession(session);
			}
		}

		return accountEntryLanguage;
	}

	/**
	 * Returns all the account entry languages where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @return the matching account entry languages
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountEntryLanguage> findByAccountEntryId(long accountEntryId)
		throws SystemException {
		return findByAccountEntryId(accountEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account entry languages where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of account entry languages
	 * @param end the upper bound of the range of account entry languages (not inclusive)
	 * @return the range of matching account entry languages
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountEntryLanguage> findByAccountEntryId(
		long accountEntryId, int start, int end) throws SystemException {
		return findByAccountEntryId(accountEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the account entry languages where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of account entry languages
	 * @param end the upper bound of the range of account entry languages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account entry languages
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountEntryLanguage> findByAccountEntryId(
		long accountEntryId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID;
			finderArgs = new Object[] { accountEntryId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ACCOUNTENTRYID;
			finderArgs = new Object[] {
					accountEntryId,
					
					start, end, orderByComparator
				};
		}

		List<AccountEntryLanguage> list = (List<AccountEntryLanguage>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AccountEntryLanguage accountEntryLanguage : list) {
				if ((accountEntryId != accountEntryLanguage.getAccountEntryId())) {
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

			query.append(_SQL_SELECT_ACCOUNTENTRYLANGUAGE_WHERE);

			query.append(_FINDER_COLUMN_ACCOUNTENTRYID_ACCOUNTENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(AccountEntryLanguageModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEntryId);

				list = (List<AccountEntryLanguage>)QueryUtil.list(q,
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
	 * Returns the first account entry language in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account entry language
	 * @throws com.liferay.osb.NoSuchAccountEntryLanguageException if a matching account entry language could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEntryLanguage findByAccountEntryId_First(
		long accountEntryId, OrderByComparator orderByComparator)
		throws NoSuchAccountEntryLanguageException, SystemException {
		AccountEntryLanguage accountEntryLanguage = fetchByAccountEntryId_First(accountEntryId,
				orderByComparator);

		if (accountEntryLanguage != null) {
			return accountEntryLanguage;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountEntryLanguageException(msg.toString());
	}

	/**
	 * Returns the first account entry language in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account entry language, or <code>null</code> if a matching account entry language could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEntryLanguage fetchByAccountEntryId_First(
		long accountEntryId, OrderByComparator orderByComparator)
		throws SystemException {
		List<AccountEntryLanguage> list = findByAccountEntryId(accountEntryId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last account entry language in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account entry language
	 * @throws com.liferay.osb.NoSuchAccountEntryLanguageException if a matching account entry language could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEntryLanguage findByAccountEntryId_Last(long accountEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchAccountEntryLanguageException, SystemException {
		AccountEntryLanguage accountEntryLanguage = fetchByAccountEntryId_Last(accountEntryId,
				orderByComparator);

		if (accountEntryLanguage != null) {
			return accountEntryLanguage;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountEntryLanguageException(msg.toString());
	}

	/**
	 * Returns the last account entry language in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account entry language, or <code>null</code> if a matching account entry language could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEntryLanguage fetchByAccountEntryId_Last(
		long accountEntryId, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByAccountEntryId(accountEntryId);

		List<AccountEntryLanguage> list = findByAccountEntryId(accountEntryId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the account entry languages before and after the current account entry language in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryLanguageId the primary key of the current account entry language
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account entry language
	 * @throws com.liferay.osb.NoSuchAccountEntryLanguageException if a account entry language with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEntryLanguage[] findByAccountEntryId_PrevAndNext(
		long accountEntryLanguageId, long accountEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchAccountEntryLanguageException, SystemException {
		AccountEntryLanguage accountEntryLanguage = findByPrimaryKey(accountEntryLanguageId);

		Session session = null;

		try {
			session = openSession();

			AccountEntryLanguage[] array = new AccountEntryLanguageImpl[3];

			array[0] = getByAccountEntryId_PrevAndNext(session,
					accountEntryLanguage, accountEntryId, orderByComparator,
					true);

			array[1] = accountEntryLanguage;

			array[2] = getByAccountEntryId_PrevAndNext(session,
					accountEntryLanguage, accountEntryId, orderByComparator,
					false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AccountEntryLanguage getByAccountEntryId_PrevAndNext(
		Session session, AccountEntryLanguage accountEntryLanguage,
		long accountEntryId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ACCOUNTENTRYLANGUAGE_WHERE);

		query.append(_FINDER_COLUMN_ACCOUNTENTRYID_ACCOUNTENTRYID_2);

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
			query.append(AccountEntryLanguageModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(accountEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(accountEntryLanguage);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AccountEntryLanguage> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the account entry languages.
	 *
	 * @return the account entry languages
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountEntryLanguage> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account entry languages.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of account entry languages
	 * @param end the upper bound of the range of account entry languages (not inclusive)
	 * @return the range of account entry languages
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountEntryLanguage> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the account entry languages.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of account entry languages
	 * @param end the upper bound of the range of account entry languages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of account entry languages
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountEntryLanguage> findAll(int start, int end,
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

		List<AccountEntryLanguage> list = (List<AccountEntryLanguage>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_ACCOUNTENTRYLANGUAGE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ACCOUNTENTRYLANGUAGE.concat(AccountEntryLanguageModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<AccountEntryLanguage>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<AccountEntryLanguage>)QueryUtil.list(q,
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
	 * Removes all the account entry languages where accountEntryId = &#63; from the database.
	 *
	 * @param accountEntryId the account entry ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByAccountEntryId(long accountEntryId)
		throws SystemException {
		for (AccountEntryLanguage accountEntryLanguage : findByAccountEntryId(
				accountEntryId)) {
			remove(accountEntryLanguage);
		}
	}

	/**
	 * Removes all the account entry languages from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (AccountEntryLanguage accountEntryLanguage : findAll()) {
			remove(accountEntryLanguage);
		}
	}

	/**
	 * Returns the number of account entry languages where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @return the number of matching account entry languages
	 * @throws SystemException if a system exception occurred
	 */
	public int countByAccountEntryId(long accountEntryId)
		throws SystemException {
		Object[] finderArgs = new Object[] { accountEntryId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_ACCOUNTENTRYID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ACCOUNTENTRYLANGUAGE_WHERE);

			query.append(_FINDER_COLUMN_ACCOUNTENTRYID_ACCOUNTENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEntryId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_ACCOUNTENTRYID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of account entry languages.
	 *
	 * @return the number of account entry languages
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ACCOUNTENTRYLANGUAGE);

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
	 * Initializes the account entry language persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.osb.model.AccountEntryLanguage")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<AccountEntryLanguage>> listenersList = new ArrayList<ModelListener<AccountEntryLanguage>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<AccountEntryLanguage>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(AccountEntryLanguageImpl.class.getName());
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
	private static final String _SQL_SELECT_ACCOUNTENTRYLANGUAGE = "SELECT accountEntryLanguage FROM AccountEntryLanguage accountEntryLanguage";
	private static final String _SQL_SELECT_ACCOUNTENTRYLANGUAGE_WHERE = "SELECT accountEntryLanguage FROM AccountEntryLanguage accountEntryLanguage WHERE ";
	private static final String _SQL_COUNT_ACCOUNTENTRYLANGUAGE = "SELECT COUNT(accountEntryLanguage) FROM AccountEntryLanguage accountEntryLanguage";
	private static final String _SQL_COUNT_ACCOUNTENTRYLANGUAGE_WHERE = "SELECT COUNT(accountEntryLanguage) FROM AccountEntryLanguage accountEntryLanguage WHERE ";
	private static final String _FINDER_COLUMN_ACCOUNTENTRYID_ACCOUNTENTRYID_2 = "accountEntryLanguage.accountEntryId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "accountEntryLanguage.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AccountEntryLanguage exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No AccountEntryLanguage exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(AccountEntryLanguagePersistenceImpl.class);
	private static AccountEntryLanguage _nullAccountEntryLanguage = new AccountEntryLanguageImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<AccountEntryLanguage> toCacheModel() {
				return _nullAccountEntryLanguageCacheModel;
			}
		};

	private static CacheModel<AccountEntryLanguage> _nullAccountEntryLanguageCacheModel =
		new CacheModel<AccountEntryLanguage>() {
			public AccountEntryLanguage toEntityModel() {
				return _nullAccountEntryLanguage;
			}
		};
}