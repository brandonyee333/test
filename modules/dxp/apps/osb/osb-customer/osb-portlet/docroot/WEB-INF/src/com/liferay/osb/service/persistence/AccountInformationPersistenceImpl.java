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

import com.liferay.osb.NoSuchAccountInformationException;
import com.liferay.osb.model.AccountInformation;
import com.liferay.osb.model.impl.AccountInformationImpl;
import com.liferay.osb.model.impl.AccountInformationModelImpl;

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
 * The persistence implementation for the account information service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountInformationPersistence
 * @see AccountInformationUtil
 * @generated
 */
public class AccountInformationPersistenceImpl extends BasePersistenceImpl<AccountInformation>
	implements AccountInformationPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AccountInformationUtil} to access the account information persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AccountInformationImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_AEI_API = new FinderPath(AccountInformationModelImpl.ENTITY_CACHE_ENABLED,
			AccountInformationModelImpl.FINDER_CACHE_ENABLED,
			AccountInformationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAEI_API",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_API =
		new FinderPath(AccountInformationModelImpl.ENTITY_CACHE_ENABLED,
			AccountInformationModelImpl.FINDER_CACHE_ENABLED,
			AccountInformationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAEI_API",
			new String[] { Long.class.getName(), Long.class.getName() },
			AccountInformationModelImpl.ACCOUNTENTRYID_COLUMN_BITMASK |
			AccountInformationModelImpl.ACCOUNTPROJECTID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_AEI_API = new FinderPath(AccountInformationModelImpl.ENTITY_CACHE_ENABLED,
			AccountInformationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAEI_API",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_AEI_NOTAPI =
		new FinderPath(AccountInformationModelImpl.ENTITY_CACHE_ENABLED,
			AccountInformationModelImpl.FINDER_CACHE_ENABLED,
			AccountInformationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAEI_NotAPI",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_AEI_NOTAPI =
		new FinderPath(AccountInformationModelImpl.ENTITY_CACHE_ENABLED,
			AccountInformationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByAEI_NotAPI",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_AEI_API_FI = new FinderPath(AccountInformationModelImpl.ENTITY_CACHE_ENABLED,
			AccountInformationModelImpl.FINDER_CACHE_ENABLED,
			AccountInformationImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByAEI_API_FI",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			},
			AccountInformationModelImpl.ACCOUNTENTRYID_COLUMN_BITMASK |
			AccountInformationModelImpl.ACCOUNTPROJECTID_COLUMN_BITMASK |
			AccountInformationModelImpl.FIELDID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_AEI_API_FI = new FinderPath(AccountInformationModelImpl.ENTITY_CACHE_ENABLED,
			AccountInformationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAEI_API_FI",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AccountInformationModelImpl.ENTITY_CACHE_ENABLED,
			AccountInformationModelImpl.FINDER_CACHE_ENABLED,
			AccountInformationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AccountInformationModelImpl.ENTITY_CACHE_ENABLED,
			AccountInformationModelImpl.FINDER_CACHE_ENABLED,
			AccountInformationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AccountInformationModelImpl.ENTITY_CACHE_ENABLED,
			AccountInformationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the account information in the entity cache if it is enabled.
	 *
	 * @param accountInformation the account information
	 */
	public void cacheResult(AccountInformation accountInformation) {
		EntityCacheUtil.putResult(AccountInformationModelImpl.ENTITY_CACHE_ENABLED,
			AccountInformationImpl.class, accountInformation.getPrimaryKey(),
			accountInformation);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_AEI_API_FI,
			new Object[] {
				Long.valueOf(accountInformation.getAccountEntryId()),
				Long.valueOf(accountInformation.getAccountProjectId()),
				Integer.valueOf(accountInformation.getFieldId())
			}, accountInformation);

		accountInformation.resetOriginalValues();
	}

	/**
	 * Caches the account informations in the entity cache if it is enabled.
	 *
	 * @param accountInformations the account informations
	 */
	public void cacheResult(List<AccountInformation> accountInformations) {
		for (AccountInformation accountInformation : accountInformations) {
			if (EntityCacheUtil.getResult(
						AccountInformationModelImpl.ENTITY_CACHE_ENABLED,
						AccountInformationImpl.class,
						accountInformation.getPrimaryKey()) == null) {
				cacheResult(accountInformation);
			}
			else {
				accountInformation.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all account informations.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(AccountInformationImpl.class.getName());
		}

		EntityCacheUtil.clearCache(AccountInformationImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the account information.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AccountInformation accountInformation) {
		EntityCacheUtil.removeResult(AccountInformationModelImpl.ENTITY_CACHE_ENABLED,
			AccountInformationImpl.class, accountInformation.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(accountInformation);
	}

	@Override
	public void clearCache(List<AccountInformation> accountInformations) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AccountInformation accountInformation : accountInformations) {
			EntityCacheUtil.removeResult(AccountInformationModelImpl.ENTITY_CACHE_ENABLED,
				AccountInformationImpl.class, accountInformation.getPrimaryKey());

			clearUniqueFindersCache(accountInformation);
		}
	}

	protected void cacheUniqueFindersCache(
		AccountInformation accountInformation) {
		if (accountInformation.isNew()) {
			Object[] args = new Object[] {
					Long.valueOf(accountInformation.getAccountEntryId()),
					Long.valueOf(accountInformation.getAccountProjectId()),
					Integer.valueOf(accountInformation.getFieldId())
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_AEI_API_FI, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_AEI_API_FI, args,
				accountInformation);
		}
		else {
			AccountInformationModelImpl accountInformationModelImpl = (AccountInformationModelImpl)accountInformation;

			if ((accountInformationModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_AEI_API_FI.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(accountInformation.getAccountEntryId()),
						Long.valueOf(accountInformation.getAccountProjectId()),
						Integer.valueOf(accountInformation.getFieldId())
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_AEI_API_FI,
					args, Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_AEI_API_FI,
					args, accountInformation);
			}
		}
	}

	protected void clearUniqueFindersCache(
		AccountInformation accountInformation) {
		AccountInformationModelImpl accountInformationModelImpl = (AccountInformationModelImpl)accountInformation;

		Object[] args = new Object[] {
				Long.valueOf(accountInformation.getAccountEntryId()),
				Long.valueOf(accountInformation.getAccountProjectId()),
				Integer.valueOf(accountInformation.getFieldId())
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_AEI_API_FI, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_AEI_API_FI, args);

		if ((accountInformationModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_AEI_API_FI.getColumnBitmask()) != 0) {
			args = new Object[] {
					Long.valueOf(accountInformationModelImpl.getOriginalAccountEntryId()),
					Long.valueOf(accountInformationModelImpl.getOriginalAccountProjectId()),
					Integer.valueOf(accountInformationModelImpl.getOriginalFieldId())
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_AEI_API_FI, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_AEI_API_FI, args);
		}
	}

	/**
	 * Creates a new account information with the primary key. Does not add the account information to the database.
	 *
	 * @param accountInformationId the primary key for the new account information
	 * @return the new account information
	 */
	public AccountInformation create(long accountInformationId) {
		AccountInformation accountInformation = new AccountInformationImpl();

		accountInformation.setNew(true);
		accountInformation.setPrimaryKey(accountInformationId);

		return accountInformation;
	}

	/**
	 * Removes the account information with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param accountInformationId the primary key of the account information
	 * @return the account information that was removed
	 * @throws com.liferay.osb.NoSuchAccountInformationException if a account information with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountInformation remove(long accountInformationId)
		throws NoSuchAccountInformationException, SystemException {
		return remove(Long.valueOf(accountInformationId));
	}

	/**
	 * Removes the account information with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the account information
	 * @return the account information that was removed
	 * @throws com.liferay.osb.NoSuchAccountInformationException if a account information with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AccountInformation remove(Serializable primaryKey)
		throws NoSuchAccountInformationException, SystemException {
		Session session = null;

		try {
			session = openSession();

			AccountInformation accountInformation = (AccountInformation)session.get(AccountInformationImpl.class,
					primaryKey);

			if (accountInformation == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAccountInformationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(accountInformation);
		}
		catch (NoSuchAccountInformationException nsee) {
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
	protected AccountInformation removeImpl(
		AccountInformation accountInformation) throws SystemException {
		accountInformation = toUnwrappedModel(accountInformation);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, accountInformation);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(accountInformation);

		return accountInformation;
	}

	@Override
	public AccountInformation updateImpl(
		com.liferay.osb.model.AccountInformation accountInformation,
		boolean merge) throws SystemException {
		accountInformation = toUnwrappedModel(accountInformation);

		boolean isNew = accountInformation.isNew();

		AccountInformationModelImpl accountInformationModelImpl = (AccountInformationModelImpl)accountInformation;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, accountInformation, merge);

			accountInformation.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !AccountInformationModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((accountInformationModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_API.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(accountInformationModelImpl.getOriginalAccountEntryId()),
						Long.valueOf(accountInformationModelImpl.getOriginalAccountProjectId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_AEI_API, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_API,
					args);

				args = new Object[] {
						Long.valueOf(accountInformationModelImpl.getAccountEntryId()),
						Long.valueOf(accountInformationModelImpl.getAccountProjectId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_AEI_API, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_API,
					args);
			}
		}

		EntityCacheUtil.putResult(AccountInformationModelImpl.ENTITY_CACHE_ENABLED,
			AccountInformationImpl.class, accountInformation.getPrimaryKey(),
			accountInformation);

		clearUniqueFindersCache(accountInformation);
		cacheUniqueFindersCache(accountInformation);

		return accountInformation;
	}

	protected AccountInformation toUnwrappedModel(
		AccountInformation accountInformation) {
		if (accountInformation instanceof AccountInformationImpl) {
			return accountInformation;
		}

		AccountInformationImpl accountInformationImpl = new AccountInformationImpl();

		accountInformationImpl.setNew(accountInformation.isNew());
		accountInformationImpl.setPrimaryKey(accountInformation.getPrimaryKey());

		accountInformationImpl.setAccountInformationId(accountInformation.getAccountInformationId());
		accountInformationImpl.setModifiedUserId(accountInformation.getModifiedUserId());
		accountInformationImpl.setModifiedUserName(accountInformation.getModifiedUserName());
		accountInformationImpl.setModifiedDate(accountInformation.getModifiedDate());
		accountInformationImpl.setAccountEntryId(accountInformation.getAccountEntryId());
		accountInformationImpl.setAccountProjectId(accountInformation.getAccountProjectId());
		accountInformationImpl.setFieldId(accountInformation.getFieldId());
		accountInformationImpl.setData(accountInformation.getData());

		return accountInformationImpl;
	}

	/**
	 * Returns the account information with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the account information
	 * @return the account information
	 * @throws com.liferay.portal.NoSuchModelException if a account information with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AccountInformation findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the account information with the primary key or throws a {@link com.liferay.osb.NoSuchAccountInformationException} if it could not be found.
	 *
	 * @param accountInformationId the primary key of the account information
	 * @return the account information
	 * @throws com.liferay.osb.NoSuchAccountInformationException if a account information with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountInformation findByPrimaryKey(long accountInformationId)
		throws NoSuchAccountInformationException, SystemException {
		AccountInformation accountInformation = fetchByPrimaryKey(accountInformationId);

		if (accountInformation == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					accountInformationId);
			}

			throw new NoSuchAccountInformationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				accountInformationId);
		}

		return accountInformation;
	}

	/**
	 * Returns the account information with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the account information
	 * @return the account information, or <code>null</code> if a account information with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AccountInformation fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the account information with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param accountInformationId the primary key of the account information
	 * @return the account information, or <code>null</code> if a account information with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountInformation fetchByPrimaryKey(long accountInformationId)
		throws SystemException {
		AccountInformation accountInformation = (AccountInformation)EntityCacheUtil.getResult(AccountInformationModelImpl.ENTITY_CACHE_ENABLED,
				AccountInformationImpl.class, accountInformationId);

		if (accountInformation == _nullAccountInformation) {
			return null;
		}

		if (accountInformation == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				accountInformation = (AccountInformation)session.get(AccountInformationImpl.class,
						Long.valueOf(accountInformationId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (accountInformation != null) {
					cacheResult(accountInformation);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(AccountInformationModelImpl.ENTITY_CACHE_ENABLED,
						AccountInformationImpl.class, accountInformationId,
						_nullAccountInformation);
				}

				closeSession(session);
			}
		}

		return accountInformation;
	}

	/**
	 * Returns all the account informations where accountEntryId = &#63; and accountProjectId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @return the matching account informations
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountInformation> findByAEI_API(long accountEntryId,
		long accountProjectId) throws SystemException {
		return findByAEI_API(accountEntryId, accountProjectId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account informations where accountEntryId = &#63; and accountProjectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param start the lower bound of the range of account informations
	 * @param end the upper bound of the range of account informations (not inclusive)
	 * @return the range of matching account informations
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountInformation> findByAEI_API(long accountEntryId,
		long accountProjectId, int start, int end) throws SystemException {
		return findByAEI_API(accountEntryId, accountProjectId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the account informations where accountEntryId = &#63; and accountProjectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param start the lower bound of the range of account informations
	 * @param end the upper bound of the range of account informations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account informations
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountInformation> findByAEI_API(long accountEntryId,
		long accountProjectId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_API;
			finderArgs = new Object[] { accountEntryId, accountProjectId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_AEI_API;
			finderArgs = new Object[] {
					accountEntryId, accountProjectId,
					
					start, end, orderByComparator
				};
		}

		List<AccountInformation> list = (List<AccountInformation>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AccountInformation accountInformation : list) {
				if ((accountEntryId != accountInformation.getAccountEntryId()) ||
						(accountProjectId != accountInformation.getAccountProjectId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_ACCOUNTINFORMATION_WHERE);

			query.append(_FINDER_COLUMN_AEI_API_ACCOUNTENTRYID_2);

			query.append(_FINDER_COLUMN_AEI_API_ACCOUNTPROJECTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEntryId);

				qPos.add(accountProjectId);

				list = (List<AccountInformation>)QueryUtil.list(q,
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
	 * Returns the first account information in the ordered set where accountEntryId = &#63; and accountProjectId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account information
	 * @throws com.liferay.osb.NoSuchAccountInformationException if a matching account information could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountInformation findByAEI_API_First(long accountEntryId,
		long accountProjectId, OrderByComparator orderByComparator)
		throws NoSuchAccountInformationException, SystemException {
		AccountInformation accountInformation = fetchByAEI_API_First(accountEntryId,
				accountProjectId, orderByComparator);

		if (accountInformation != null) {
			return accountInformation;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(", accountProjectId=");
		msg.append(accountProjectId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountInformationException(msg.toString());
	}

	/**
	 * Returns the first account information in the ordered set where accountEntryId = &#63; and accountProjectId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account information, or <code>null</code> if a matching account information could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountInformation fetchByAEI_API_First(long accountEntryId,
		long accountProjectId, OrderByComparator orderByComparator)
		throws SystemException {
		List<AccountInformation> list = findByAEI_API(accountEntryId,
				accountProjectId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last account information in the ordered set where accountEntryId = &#63; and accountProjectId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account information
	 * @throws com.liferay.osb.NoSuchAccountInformationException if a matching account information could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountInformation findByAEI_API_Last(long accountEntryId,
		long accountProjectId, OrderByComparator orderByComparator)
		throws NoSuchAccountInformationException, SystemException {
		AccountInformation accountInformation = fetchByAEI_API_Last(accountEntryId,
				accountProjectId, orderByComparator);

		if (accountInformation != null) {
			return accountInformation;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(", accountProjectId=");
		msg.append(accountProjectId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountInformationException(msg.toString());
	}

	/**
	 * Returns the last account information in the ordered set where accountEntryId = &#63; and accountProjectId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account information, or <code>null</code> if a matching account information could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountInformation fetchByAEI_API_Last(long accountEntryId,
		long accountProjectId, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByAEI_API(accountEntryId, accountProjectId);

		List<AccountInformation> list = findByAEI_API(accountEntryId,
				accountProjectId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the account informations before and after the current account information in the ordered set where accountEntryId = &#63; and accountProjectId = &#63;.
	 *
	 * @param accountInformationId the primary key of the current account information
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account information
	 * @throws com.liferay.osb.NoSuchAccountInformationException if a account information with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountInformation[] findByAEI_API_PrevAndNext(
		long accountInformationId, long accountEntryId, long accountProjectId,
		OrderByComparator orderByComparator)
		throws NoSuchAccountInformationException, SystemException {
		AccountInformation accountInformation = findByPrimaryKey(accountInformationId);

		Session session = null;

		try {
			session = openSession();

			AccountInformation[] array = new AccountInformationImpl[3];

			array[0] = getByAEI_API_PrevAndNext(session, accountInformation,
					accountEntryId, accountProjectId, orderByComparator, true);

			array[1] = accountInformation;

			array[2] = getByAEI_API_PrevAndNext(session, accountInformation,
					accountEntryId, accountProjectId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AccountInformation getByAEI_API_PrevAndNext(Session session,
		AccountInformation accountInformation, long accountEntryId,
		long accountProjectId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ACCOUNTINFORMATION_WHERE);

		query.append(_FINDER_COLUMN_AEI_API_ACCOUNTENTRYID_2);

		query.append(_FINDER_COLUMN_AEI_API_ACCOUNTPROJECTID_2);

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

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(accountEntryId);

		qPos.add(accountProjectId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(accountInformation);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AccountInformation> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the account informations where accountEntryId = &#63; and accountProjectId &ne; &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @return the matching account informations
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountInformation> findByAEI_NotAPI(long accountEntryId,
		long accountProjectId) throws SystemException {
		return findByAEI_NotAPI(accountEntryId, accountProjectId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account informations where accountEntryId = &#63; and accountProjectId &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param start the lower bound of the range of account informations
	 * @param end the upper bound of the range of account informations (not inclusive)
	 * @return the range of matching account informations
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountInformation> findByAEI_NotAPI(long accountEntryId,
		long accountProjectId, int start, int end) throws SystemException {
		return findByAEI_NotAPI(accountEntryId, accountProjectId, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the account informations where accountEntryId = &#63; and accountProjectId &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param start the lower bound of the range of account informations
	 * @param end the upper bound of the range of account informations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account informations
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountInformation> findByAEI_NotAPI(long accountEntryId,
		long accountProjectId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_AEI_NOTAPI;
		finderArgs = new Object[] {
				accountEntryId, accountProjectId,
				
				start, end, orderByComparator
			};

		List<AccountInformation> list = (List<AccountInformation>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AccountInformation accountInformation : list) {
				if ((accountEntryId != accountInformation.getAccountEntryId()) ||
						(accountProjectId != accountInformation.getAccountProjectId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_ACCOUNTINFORMATION_WHERE);

			query.append(_FINDER_COLUMN_AEI_NOTAPI_ACCOUNTENTRYID_2);

			query.append(_FINDER_COLUMN_AEI_NOTAPI_ACCOUNTPROJECTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEntryId);

				qPos.add(accountProjectId);

				list = (List<AccountInformation>)QueryUtil.list(q,
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
	 * Returns the first account information in the ordered set where accountEntryId = &#63; and accountProjectId &ne; &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account information
	 * @throws com.liferay.osb.NoSuchAccountInformationException if a matching account information could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountInformation findByAEI_NotAPI_First(long accountEntryId,
		long accountProjectId, OrderByComparator orderByComparator)
		throws NoSuchAccountInformationException, SystemException {
		AccountInformation accountInformation = fetchByAEI_NotAPI_First(accountEntryId,
				accountProjectId, orderByComparator);

		if (accountInformation != null) {
			return accountInformation;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(", accountProjectId=");
		msg.append(accountProjectId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountInformationException(msg.toString());
	}

	/**
	 * Returns the first account information in the ordered set where accountEntryId = &#63; and accountProjectId &ne; &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account information, or <code>null</code> if a matching account information could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountInformation fetchByAEI_NotAPI_First(long accountEntryId,
		long accountProjectId, OrderByComparator orderByComparator)
		throws SystemException {
		List<AccountInformation> list = findByAEI_NotAPI(accountEntryId,
				accountProjectId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last account information in the ordered set where accountEntryId = &#63; and accountProjectId &ne; &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account information
	 * @throws com.liferay.osb.NoSuchAccountInformationException if a matching account information could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountInformation findByAEI_NotAPI_Last(long accountEntryId,
		long accountProjectId, OrderByComparator orderByComparator)
		throws NoSuchAccountInformationException, SystemException {
		AccountInformation accountInformation = fetchByAEI_NotAPI_Last(accountEntryId,
				accountProjectId, orderByComparator);

		if (accountInformation != null) {
			return accountInformation;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(", accountProjectId=");
		msg.append(accountProjectId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountInformationException(msg.toString());
	}

	/**
	 * Returns the last account information in the ordered set where accountEntryId = &#63; and accountProjectId &ne; &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account information, or <code>null</code> if a matching account information could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountInformation fetchByAEI_NotAPI_Last(long accountEntryId,
		long accountProjectId, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByAEI_NotAPI(accountEntryId, accountProjectId);

		List<AccountInformation> list = findByAEI_NotAPI(accountEntryId,
				accountProjectId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the account informations before and after the current account information in the ordered set where accountEntryId = &#63; and accountProjectId &ne; &#63;.
	 *
	 * @param accountInformationId the primary key of the current account information
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account information
	 * @throws com.liferay.osb.NoSuchAccountInformationException if a account information with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountInformation[] findByAEI_NotAPI_PrevAndNext(
		long accountInformationId, long accountEntryId, long accountProjectId,
		OrderByComparator orderByComparator)
		throws NoSuchAccountInformationException, SystemException {
		AccountInformation accountInformation = findByPrimaryKey(accountInformationId);

		Session session = null;

		try {
			session = openSession();

			AccountInformation[] array = new AccountInformationImpl[3];

			array[0] = getByAEI_NotAPI_PrevAndNext(session, accountInformation,
					accountEntryId, accountProjectId, orderByComparator, true);

			array[1] = accountInformation;

			array[2] = getByAEI_NotAPI_PrevAndNext(session, accountInformation,
					accountEntryId, accountProjectId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AccountInformation getByAEI_NotAPI_PrevAndNext(Session session,
		AccountInformation accountInformation, long accountEntryId,
		long accountProjectId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ACCOUNTINFORMATION_WHERE);

		query.append(_FINDER_COLUMN_AEI_NOTAPI_ACCOUNTENTRYID_2);

		query.append(_FINDER_COLUMN_AEI_NOTAPI_ACCOUNTPROJECTID_2);

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

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(accountEntryId);

		qPos.add(accountProjectId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(accountInformation);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AccountInformation> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the account information where accountEntryId = &#63; and accountProjectId = &#63; and fieldId = &#63; or throws a {@link com.liferay.osb.NoSuchAccountInformationException} if it could not be found.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param fieldId the field ID
	 * @return the matching account information
	 * @throws com.liferay.osb.NoSuchAccountInformationException if a matching account information could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountInformation findByAEI_API_FI(long accountEntryId,
		long accountProjectId, int fieldId)
		throws NoSuchAccountInformationException, SystemException {
		AccountInformation accountInformation = fetchByAEI_API_FI(accountEntryId,
				accountProjectId, fieldId);

		if (accountInformation == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("accountEntryId=");
			msg.append(accountEntryId);

			msg.append(", accountProjectId=");
			msg.append(accountProjectId);

			msg.append(", fieldId=");
			msg.append(fieldId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchAccountInformationException(msg.toString());
		}

		return accountInformation;
	}

	/**
	 * Returns the account information where accountEntryId = &#63; and accountProjectId = &#63; and fieldId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param fieldId the field ID
	 * @return the matching account information, or <code>null</code> if a matching account information could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountInformation fetchByAEI_API_FI(long accountEntryId,
		long accountProjectId, int fieldId) throws SystemException {
		return fetchByAEI_API_FI(accountEntryId, accountProjectId, fieldId, true);
	}

	/**
	 * Returns the account information where accountEntryId = &#63; and accountProjectId = &#63; and fieldId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param fieldId the field ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching account information, or <code>null</code> if a matching account information could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountInformation fetchByAEI_API_FI(long accountEntryId,
		long accountProjectId, int fieldId, boolean retrieveFromCache)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				accountEntryId, accountProjectId, fieldId
			};

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_AEI_API_FI,
					finderArgs, this);
		}

		if (result instanceof AccountInformation) {
			AccountInformation accountInformation = (AccountInformation)result;

			if ((accountEntryId != accountInformation.getAccountEntryId()) ||
					(accountProjectId != accountInformation.getAccountProjectId()) ||
					(fieldId != accountInformation.getFieldId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_ACCOUNTINFORMATION_WHERE);

			query.append(_FINDER_COLUMN_AEI_API_FI_ACCOUNTENTRYID_2);

			query.append(_FINDER_COLUMN_AEI_API_FI_ACCOUNTPROJECTID_2);

			query.append(_FINDER_COLUMN_AEI_API_FI_FIELDID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEntryId);

				qPos.add(accountProjectId);

				qPos.add(fieldId);

				List<AccountInformation> list = q.list();

				result = list;

				AccountInformation accountInformation = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_AEI_API_FI,
						finderArgs, list);
				}
				else {
					accountInformation = list.get(0);

					cacheResult(accountInformation);

					if ((accountInformation.getAccountEntryId() != accountEntryId) ||
							(accountInformation.getAccountProjectId() != accountProjectId) ||
							(accountInformation.getFieldId() != fieldId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_AEI_API_FI,
							finderArgs, accountInformation);
					}
				}

				return accountInformation;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_AEI_API_FI,
						finderArgs);
				}

				closeSession(session);
			}
		}
		else {
			if (result instanceof List<?>) {
				return null;
			}
			else {
				return (AccountInformation)result;
			}
		}
	}

	/**
	 * Returns all the account informations.
	 *
	 * @return the account informations
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountInformation> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account informations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of account informations
	 * @param end the upper bound of the range of account informations (not inclusive)
	 * @return the range of account informations
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountInformation> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the account informations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of account informations
	 * @param end the upper bound of the range of account informations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of account informations
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountInformation> findAll(int start, int end,
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

		List<AccountInformation> list = (List<AccountInformation>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_ACCOUNTINFORMATION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ACCOUNTINFORMATION;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<AccountInformation>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<AccountInformation>)QueryUtil.list(q,
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
	 * Removes all the account informations where accountEntryId = &#63; and accountProjectId = &#63; from the database.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByAEI_API(long accountEntryId, long accountProjectId)
		throws SystemException {
		for (AccountInformation accountInformation : findByAEI_API(
				accountEntryId, accountProjectId)) {
			remove(accountInformation);
		}
	}

	/**
	 * Removes all the account informations where accountEntryId = &#63; and accountProjectId &ne; &#63; from the database.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByAEI_NotAPI(long accountEntryId, long accountProjectId)
		throws SystemException {
		for (AccountInformation accountInformation : findByAEI_NotAPI(
				accountEntryId, accountProjectId)) {
			remove(accountInformation);
		}
	}

	/**
	 * Removes the account information where accountEntryId = &#63; and accountProjectId = &#63; and fieldId = &#63; from the database.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param fieldId the field ID
	 * @return the account information that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public AccountInformation removeByAEI_API_FI(long accountEntryId,
		long accountProjectId, int fieldId)
		throws NoSuchAccountInformationException, SystemException {
		AccountInformation accountInformation = findByAEI_API_FI(accountEntryId,
				accountProjectId, fieldId);

		return remove(accountInformation);
	}

	/**
	 * Removes all the account informations from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (AccountInformation accountInformation : findAll()) {
			remove(accountInformation);
		}
	}

	/**
	 * Returns the number of account informations where accountEntryId = &#63; and accountProjectId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @return the number of matching account informations
	 * @throws SystemException if a system exception occurred
	 */
	public int countByAEI_API(long accountEntryId, long accountProjectId)
		throws SystemException {
		Object[] finderArgs = new Object[] { accountEntryId, accountProjectId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_AEI_API,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ACCOUNTINFORMATION_WHERE);

			query.append(_FINDER_COLUMN_AEI_API_ACCOUNTENTRYID_2);

			query.append(_FINDER_COLUMN_AEI_API_ACCOUNTPROJECTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEntryId);

				qPos.add(accountProjectId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_AEI_API,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of account informations where accountEntryId = &#63; and accountProjectId &ne; &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @return the number of matching account informations
	 * @throws SystemException if a system exception occurred
	 */
	public int countByAEI_NotAPI(long accountEntryId, long accountProjectId)
		throws SystemException {
		Object[] finderArgs = new Object[] { accountEntryId, accountProjectId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_AEI_NOTAPI,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ACCOUNTINFORMATION_WHERE);

			query.append(_FINDER_COLUMN_AEI_NOTAPI_ACCOUNTENTRYID_2);

			query.append(_FINDER_COLUMN_AEI_NOTAPI_ACCOUNTPROJECTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEntryId);

				qPos.add(accountProjectId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_AEI_NOTAPI,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of account informations where accountEntryId = &#63; and accountProjectId = &#63; and fieldId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param fieldId the field ID
	 * @return the number of matching account informations
	 * @throws SystemException if a system exception occurred
	 */
	public int countByAEI_API_FI(long accountEntryId, long accountProjectId,
		int fieldId) throws SystemException {
		Object[] finderArgs = new Object[] {
				accountEntryId, accountProjectId, fieldId
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_AEI_API_FI,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_ACCOUNTINFORMATION_WHERE);

			query.append(_FINDER_COLUMN_AEI_API_FI_ACCOUNTENTRYID_2);

			query.append(_FINDER_COLUMN_AEI_API_FI_ACCOUNTPROJECTID_2);

			query.append(_FINDER_COLUMN_AEI_API_FI_FIELDID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEntryId);

				qPos.add(accountProjectId);

				qPos.add(fieldId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_AEI_API_FI,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of account informations.
	 *
	 * @return the number of account informations
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ACCOUNTINFORMATION);

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
	 * Initializes the account information persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.osb.model.AccountInformation")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<AccountInformation>> listenersList = new ArrayList<ModelListener<AccountInformation>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<AccountInformation>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(AccountInformationImpl.class.getName());
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
	private static final String _SQL_SELECT_ACCOUNTINFORMATION = "SELECT accountInformation FROM AccountInformation accountInformation";
	private static final String _SQL_SELECT_ACCOUNTINFORMATION_WHERE = "SELECT accountInformation FROM AccountInformation accountInformation WHERE ";
	private static final String _SQL_COUNT_ACCOUNTINFORMATION = "SELECT COUNT(accountInformation) FROM AccountInformation accountInformation";
	private static final String _SQL_COUNT_ACCOUNTINFORMATION_WHERE = "SELECT COUNT(accountInformation) FROM AccountInformation accountInformation WHERE ";
	private static final String _FINDER_COLUMN_AEI_API_ACCOUNTENTRYID_2 = "accountInformation.accountEntryId = ? AND ";
	private static final String _FINDER_COLUMN_AEI_API_ACCOUNTPROJECTID_2 = "accountInformation.accountProjectId = ?";
	private static final String _FINDER_COLUMN_AEI_NOTAPI_ACCOUNTENTRYID_2 = "accountInformation.accountEntryId = ? AND ";
	private static final String _FINDER_COLUMN_AEI_NOTAPI_ACCOUNTPROJECTID_2 = "accountInformation.accountProjectId != ?";
	private static final String _FINDER_COLUMN_AEI_API_FI_ACCOUNTENTRYID_2 = "accountInformation.accountEntryId = ? AND ";
	private static final String _FINDER_COLUMN_AEI_API_FI_ACCOUNTPROJECTID_2 = "accountInformation.accountProjectId = ? AND ";
	private static final String _FINDER_COLUMN_AEI_API_FI_FIELDID_2 = "accountInformation.fieldId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "accountInformation.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AccountInformation exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No AccountInformation exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(AccountInformationPersistenceImpl.class);
	private static AccountInformation _nullAccountInformation = new AccountInformationImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<AccountInformation> toCacheModel() {
				return _nullAccountInformationCacheModel;
			}
		};

	private static CacheModel<AccountInformation> _nullAccountInformationCacheModel =
		new CacheModel<AccountInformation>() {
			public AccountInformation toEntityModel() {
				return _nullAccountInformation;
			}
		};
}