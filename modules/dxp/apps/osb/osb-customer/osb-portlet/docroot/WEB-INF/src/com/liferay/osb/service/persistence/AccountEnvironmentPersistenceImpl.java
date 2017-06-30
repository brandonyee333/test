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

import com.liferay.osb.NoSuchAccountEnvironmentException;
import com.liferay.osb.model.AccountEnvironment;
import com.liferay.osb.model.impl.AccountEnvironmentImpl;
import com.liferay.osb.model.impl.AccountEnvironmentModelImpl;

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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ClassNamePersistence;
import com.liferay.portal.service.persistence.ListTypePersistence;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the account environment service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountEnvironmentPersistence
 * @see AccountEnvironmentUtil
 * @generated
 */
public class AccountEnvironmentPersistenceImpl extends BasePersistenceImpl<AccountEnvironment>
	implements AccountEnvironmentPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AccountEnvironmentUtil} to access the account environment persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AccountEnvironmentImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ACCOUNTENTRYID =
		new FinderPath(AccountEnvironmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountEnvironmentModelImpl.FINDER_CACHE_ENABLED,
			AccountEnvironmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAccountEntryId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID =
		new FinderPath(AccountEnvironmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountEnvironmentModelImpl.FINDER_CACHE_ENABLED,
			AccountEnvironmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAccountEntryId",
			new String[] { Long.class.getName() },
			AccountEnvironmentModelImpl.ACCOUNTENTRYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ACCOUNTENTRYID = new FinderPath(AccountEnvironmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountEnvironmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAccountEntryId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_AEI_PEI = new FinderPath(AccountEnvironmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountEnvironmentModelImpl.FINDER_CACHE_ENABLED,
			AccountEnvironmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAEI_PEI",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_PEI =
		new FinderPath(AccountEnvironmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountEnvironmentModelImpl.FINDER_CACHE_ENABLED,
			AccountEnvironmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAEI_PEI",
			new String[] { Long.class.getName(), Long.class.getName() },
			AccountEnvironmentModelImpl.ACCOUNTENTRYID_COLUMN_BITMASK |
			AccountEnvironmentModelImpl.PRODUCTENTRYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_AEI_PEI = new FinderPath(AccountEnvironmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountEnvironmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAEI_PEI",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_AEI_PEI_N = new FinderPath(AccountEnvironmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountEnvironmentModelImpl.FINDER_CACHE_ENABLED,
			AccountEnvironmentImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByAEI_PEI_N",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			},
			AccountEnvironmentModelImpl.ACCOUNTENTRYID_COLUMN_BITMASK |
			AccountEnvironmentModelImpl.PRODUCTENTRYID_COLUMN_BITMASK |
			AccountEnvironmentModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_AEI_PEI_N = new FinderPath(AccountEnvironmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountEnvironmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAEI_PEI_N",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AccountEnvironmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountEnvironmentModelImpl.FINDER_CACHE_ENABLED,
			AccountEnvironmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AccountEnvironmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountEnvironmentModelImpl.FINDER_CACHE_ENABLED,
			AccountEnvironmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AccountEnvironmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountEnvironmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the account environment in the entity cache if it is enabled.
	 *
	 * @param accountEnvironment the account environment
	 */
	public void cacheResult(AccountEnvironment accountEnvironment) {
		EntityCacheUtil.putResult(AccountEnvironmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountEnvironmentImpl.class, accountEnvironment.getPrimaryKey(),
			accountEnvironment);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_AEI_PEI_N,
			new Object[] {
				Long.valueOf(accountEnvironment.getAccountEntryId()),
				Long.valueOf(accountEnvironment.getProductEntryId()),
				
			accountEnvironment.getName()
			}, accountEnvironment);

		accountEnvironment.resetOriginalValues();
	}

	/**
	 * Caches the account environments in the entity cache if it is enabled.
	 *
	 * @param accountEnvironments the account environments
	 */
	public void cacheResult(List<AccountEnvironment> accountEnvironments) {
		for (AccountEnvironment accountEnvironment : accountEnvironments) {
			if (EntityCacheUtil.getResult(
						AccountEnvironmentModelImpl.ENTITY_CACHE_ENABLED,
						AccountEnvironmentImpl.class,
						accountEnvironment.getPrimaryKey()) == null) {
				cacheResult(accountEnvironment);
			}
			else {
				accountEnvironment.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all account environments.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(AccountEnvironmentImpl.class.getName());
		}

		EntityCacheUtil.clearCache(AccountEnvironmentImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the account environment.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AccountEnvironment accountEnvironment) {
		EntityCacheUtil.removeResult(AccountEnvironmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountEnvironmentImpl.class, accountEnvironment.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(accountEnvironment);
	}

	@Override
	public void clearCache(List<AccountEnvironment> accountEnvironments) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AccountEnvironment accountEnvironment : accountEnvironments) {
			EntityCacheUtil.removeResult(AccountEnvironmentModelImpl.ENTITY_CACHE_ENABLED,
				AccountEnvironmentImpl.class, accountEnvironment.getPrimaryKey());

			clearUniqueFindersCache(accountEnvironment);
		}
	}

	protected void cacheUniqueFindersCache(
		AccountEnvironment accountEnvironment) {
		if (accountEnvironment.isNew()) {
			Object[] args = new Object[] {
					Long.valueOf(accountEnvironment.getAccountEntryId()),
					Long.valueOf(accountEnvironment.getProductEntryId()),
					
					accountEnvironment.getName()
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_AEI_PEI_N, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_AEI_PEI_N, args,
				accountEnvironment);
		}
		else {
			AccountEnvironmentModelImpl accountEnvironmentModelImpl = (AccountEnvironmentModelImpl)accountEnvironment;

			if ((accountEnvironmentModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_AEI_PEI_N.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(accountEnvironment.getAccountEntryId()),
						Long.valueOf(accountEnvironment.getProductEntryId()),
						
						accountEnvironment.getName()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_AEI_PEI_N, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_AEI_PEI_N, args,
					accountEnvironment);
			}
		}
	}

	protected void clearUniqueFindersCache(
		AccountEnvironment accountEnvironment) {
		AccountEnvironmentModelImpl accountEnvironmentModelImpl = (AccountEnvironmentModelImpl)accountEnvironment;

		Object[] args = new Object[] {
				Long.valueOf(accountEnvironment.getAccountEntryId()),
				Long.valueOf(accountEnvironment.getProductEntryId()),
				
				accountEnvironment.getName()
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_AEI_PEI_N, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_AEI_PEI_N, args);

		if ((accountEnvironmentModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_AEI_PEI_N.getColumnBitmask()) != 0) {
			args = new Object[] {
					Long.valueOf(accountEnvironmentModelImpl.getOriginalAccountEntryId()),
					Long.valueOf(accountEnvironmentModelImpl.getOriginalProductEntryId()),
					
					accountEnvironmentModelImpl.getOriginalName()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_AEI_PEI_N, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_AEI_PEI_N, args);
		}
	}

	/**
	 * Creates a new account environment with the primary key. Does not add the account environment to the database.
	 *
	 * @param accountEnvironmentId the primary key for the new account environment
	 * @return the new account environment
	 */
	public AccountEnvironment create(long accountEnvironmentId) {
		AccountEnvironment accountEnvironment = new AccountEnvironmentImpl();

		accountEnvironment.setNew(true);
		accountEnvironment.setPrimaryKey(accountEnvironmentId);

		return accountEnvironment;
	}

	/**
	 * Removes the account environment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param accountEnvironmentId the primary key of the account environment
	 * @return the account environment that was removed
	 * @throws com.liferay.osb.NoSuchAccountEnvironmentException if a account environment with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEnvironment remove(long accountEnvironmentId)
		throws NoSuchAccountEnvironmentException, SystemException {
		return remove(Long.valueOf(accountEnvironmentId));
	}

	/**
	 * Removes the account environment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the account environment
	 * @return the account environment that was removed
	 * @throws com.liferay.osb.NoSuchAccountEnvironmentException if a account environment with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AccountEnvironment remove(Serializable primaryKey)
		throws NoSuchAccountEnvironmentException, SystemException {
		Session session = null;

		try {
			session = openSession();

			AccountEnvironment accountEnvironment = (AccountEnvironment)session.get(AccountEnvironmentImpl.class,
					primaryKey);

			if (accountEnvironment == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAccountEnvironmentException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(accountEnvironment);
		}
		catch (NoSuchAccountEnvironmentException nsee) {
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
	protected AccountEnvironment removeImpl(
		AccountEnvironment accountEnvironment) throws SystemException {
		accountEnvironment = toUnwrappedModel(accountEnvironment);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, accountEnvironment);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(accountEnvironment);

		return accountEnvironment;
	}

	@Override
	public AccountEnvironment updateImpl(
		com.liferay.osb.model.AccountEnvironment accountEnvironment,
		boolean merge) throws SystemException {
		accountEnvironment = toUnwrappedModel(accountEnvironment);

		boolean isNew = accountEnvironment.isNew();

		AccountEnvironmentModelImpl accountEnvironmentModelImpl = (AccountEnvironmentModelImpl)accountEnvironment;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, accountEnvironment, merge);

			accountEnvironment.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !AccountEnvironmentModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((accountEnvironmentModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(accountEnvironmentModelImpl.getOriginalAccountEntryId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTENTRYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID,
					args);

				args = new Object[] {
						Long.valueOf(accountEnvironmentModelImpl.getAccountEntryId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTENTRYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID,
					args);
			}

			if ((accountEnvironmentModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_PEI.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(accountEnvironmentModelImpl.getOriginalAccountEntryId()),
						Long.valueOf(accountEnvironmentModelImpl.getOriginalProductEntryId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_AEI_PEI, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_PEI,
					args);

				args = new Object[] {
						Long.valueOf(accountEnvironmentModelImpl.getAccountEntryId()),
						Long.valueOf(accountEnvironmentModelImpl.getProductEntryId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_AEI_PEI, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_PEI,
					args);
			}
		}

		EntityCacheUtil.putResult(AccountEnvironmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountEnvironmentImpl.class, accountEnvironment.getPrimaryKey(),
			accountEnvironment);

		clearUniqueFindersCache(accountEnvironment);
		cacheUniqueFindersCache(accountEnvironment);

		return accountEnvironment;
	}

	protected AccountEnvironment toUnwrappedModel(
		AccountEnvironment accountEnvironment) {
		if (accountEnvironment instanceof AccountEnvironmentImpl) {
			return accountEnvironment;
		}

		AccountEnvironmentImpl accountEnvironmentImpl = new AccountEnvironmentImpl();

		accountEnvironmentImpl.setNew(accountEnvironment.isNew());
		accountEnvironmentImpl.setPrimaryKey(accountEnvironment.getPrimaryKey());

		accountEnvironmentImpl.setAccountEnvironmentId(accountEnvironment.getAccountEnvironmentId());
		accountEnvironmentImpl.setUserId(accountEnvironment.getUserId());
		accountEnvironmentImpl.setUserName(accountEnvironment.getUserName());
		accountEnvironmentImpl.setCreateDate(accountEnvironment.getCreateDate());
		accountEnvironmentImpl.setModifiedDate(accountEnvironment.getModifiedDate());
		accountEnvironmentImpl.setAccountEntryId(accountEnvironment.getAccountEntryId());
		accountEnvironmentImpl.setProductEntryId(accountEnvironment.getProductEntryId());
		accountEnvironmentImpl.setName(accountEnvironment.getName());
		accountEnvironmentImpl.setEnvOS(accountEnvironment.getEnvOS());
		accountEnvironmentImpl.setEnvOSCustom(accountEnvironment.getEnvOSCustom());
		accountEnvironmentImpl.setEnvDB(accountEnvironment.getEnvDB());
		accountEnvironmentImpl.setEnvJVM(accountEnvironment.getEnvJVM());
		accountEnvironmentImpl.setEnvAS(accountEnvironment.getEnvAS());
		accountEnvironmentImpl.setEnvLFR(accountEnvironment.getEnvLFR());

		return accountEnvironmentImpl;
	}

	/**
	 * Returns the account environment with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the account environment
	 * @return the account environment
	 * @throws com.liferay.portal.NoSuchModelException if a account environment with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AccountEnvironment findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the account environment with the primary key or throws a {@link com.liferay.osb.NoSuchAccountEnvironmentException} if it could not be found.
	 *
	 * @param accountEnvironmentId the primary key of the account environment
	 * @return the account environment
	 * @throws com.liferay.osb.NoSuchAccountEnvironmentException if a account environment with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEnvironment findByPrimaryKey(long accountEnvironmentId)
		throws NoSuchAccountEnvironmentException, SystemException {
		AccountEnvironment accountEnvironment = fetchByPrimaryKey(accountEnvironmentId);

		if (accountEnvironment == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					accountEnvironmentId);
			}

			throw new NoSuchAccountEnvironmentException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				accountEnvironmentId);
		}

		return accountEnvironment;
	}

	/**
	 * Returns the account environment with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the account environment
	 * @return the account environment, or <code>null</code> if a account environment with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AccountEnvironment fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the account environment with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param accountEnvironmentId the primary key of the account environment
	 * @return the account environment, or <code>null</code> if a account environment with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEnvironment fetchByPrimaryKey(long accountEnvironmentId)
		throws SystemException {
		AccountEnvironment accountEnvironment = (AccountEnvironment)EntityCacheUtil.getResult(AccountEnvironmentModelImpl.ENTITY_CACHE_ENABLED,
				AccountEnvironmentImpl.class, accountEnvironmentId);

		if (accountEnvironment == _nullAccountEnvironment) {
			return null;
		}

		if (accountEnvironment == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				accountEnvironment = (AccountEnvironment)session.get(AccountEnvironmentImpl.class,
						Long.valueOf(accountEnvironmentId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (accountEnvironment != null) {
					cacheResult(accountEnvironment);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(AccountEnvironmentModelImpl.ENTITY_CACHE_ENABLED,
						AccountEnvironmentImpl.class, accountEnvironmentId,
						_nullAccountEnvironment);
				}

				closeSession(session);
			}
		}

		return accountEnvironment;
	}

	/**
	 * Returns all the account environments where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @return the matching account environments
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountEnvironment> findByAccountEntryId(long accountEntryId)
		throws SystemException {
		return findByAccountEntryId(accountEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account environments where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of account environments
	 * @param end the upper bound of the range of account environments (not inclusive)
	 * @return the range of matching account environments
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountEnvironment> findByAccountEntryId(long accountEntryId,
		int start, int end) throws SystemException {
		return findByAccountEntryId(accountEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the account environments where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of account environments
	 * @param end the upper bound of the range of account environments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account environments
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountEnvironment> findByAccountEntryId(long accountEntryId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
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

		List<AccountEnvironment> list = (List<AccountEnvironment>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AccountEnvironment accountEnvironment : list) {
				if ((accountEntryId != accountEnvironment.getAccountEntryId())) {
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
				query = new StringBundler(2);
			}

			query.append(_SQL_SELECT_ACCOUNTENVIRONMENT_WHERE);

			query.append(_FINDER_COLUMN_ACCOUNTENTRYID_ACCOUNTENTRYID_2);

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

				list = (List<AccountEnvironment>)QueryUtil.list(q,
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
	 * Returns the first account environment in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account environment
	 * @throws com.liferay.osb.NoSuchAccountEnvironmentException if a matching account environment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEnvironment findByAccountEntryId_First(long accountEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchAccountEnvironmentException, SystemException {
		AccountEnvironment accountEnvironment = fetchByAccountEntryId_First(accountEntryId,
				orderByComparator);

		if (accountEnvironment != null) {
			return accountEnvironment;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountEnvironmentException(msg.toString());
	}

	/**
	 * Returns the first account environment in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account environment, or <code>null</code> if a matching account environment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEnvironment fetchByAccountEntryId_First(long accountEntryId,
		OrderByComparator orderByComparator) throws SystemException {
		List<AccountEnvironment> list = findByAccountEntryId(accountEntryId, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last account environment in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account environment
	 * @throws com.liferay.osb.NoSuchAccountEnvironmentException if a matching account environment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEnvironment findByAccountEntryId_Last(long accountEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchAccountEnvironmentException, SystemException {
		AccountEnvironment accountEnvironment = fetchByAccountEntryId_Last(accountEntryId,
				orderByComparator);

		if (accountEnvironment != null) {
			return accountEnvironment;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountEnvironmentException(msg.toString());
	}

	/**
	 * Returns the last account environment in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account environment, or <code>null</code> if a matching account environment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEnvironment fetchByAccountEntryId_Last(long accountEntryId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByAccountEntryId(accountEntryId);

		List<AccountEnvironment> list = findByAccountEntryId(accountEntryId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the account environments before and after the current account environment in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEnvironmentId the primary key of the current account environment
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account environment
	 * @throws com.liferay.osb.NoSuchAccountEnvironmentException if a account environment with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEnvironment[] findByAccountEntryId_PrevAndNext(
		long accountEnvironmentId, long accountEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchAccountEnvironmentException, SystemException {
		AccountEnvironment accountEnvironment = findByPrimaryKey(accountEnvironmentId);

		Session session = null;

		try {
			session = openSession();

			AccountEnvironment[] array = new AccountEnvironmentImpl[3];

			array[0] = getByAccountEntryId_PrevAndNext(session,
					accountEnvironment, accountEntryId, orderByComparator, true);

			array[1] = accountEnvironment;

			array[2] = getByAccountEntryId_PrevAndNext(session,
					accountEnvironment, accountEntryId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AccountEnvironment getByAccountEntryId_PrevAndNext(
		Session session, AccountEnvironment accountEnvironment,
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

		query.append(_SQL_SELECT_ACCOUNTENVIRONMENT_WHERE);

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

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(accountEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(accountEnvironment);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AccountEnvironment> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the account environments where accountEntryId = &#63; and productEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param productEntryId the product entry ID
	 * @return the matching account environments
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountEnvironment> findByAEI_PEI(long accountEntryId,
		long productEntryId) throws SystemException {
		return findByAEI_PEI(accountEntryId, productEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account environments where accountEntryId = &#63; and productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of account environments
	 * @param end the upper bound of the range of account environments (not inclusive)
	 * @return the range of matching account environments
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountEnvironment> findByAEI_PEI(long accountEntryId,
		long productEntryId, int start, int end) throws SystemException {
		return findByAEI_PEI(accountEntryId, productEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the account environments where accountEntryId = &#63; and productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of account environments
	 * @param end the upper bound of the range of account environments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account environments
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountEnvironment> findByAEI_PEI(long accountEntryId,
		long productEntryId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_PEI;
			finderArgs = new Object[] { accountEntryId, productEntryId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_AEI_PEI;
			finderArgs = new Object[] {
					accountEntryId, productEntryId,
					
					start, end, orderByComparator
				};
		}

		List<AccountEnvironment> list = (List<AccountEnvironment>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AccountEnvironment accountEnvironment : list) {
				if ((accountEntryId != accountEnvironment.getAccountEntryId()) ||
						(productEntryId != accountEnvironment.getProductEntryId())) {
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

			query.append(_SQL_SELECT_ACCOUNTENVIRONMENT_WHERE);

			query.append(_FINDER_COLUMN_AEI_PEI_ACCOUNTENTRYID_2);

			query.append(_FINDER_COLUMN_AEI_PEI_PRODUCTENTRYID_2);

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

				qPos.add(productEntryId);

				list = (List<AccountEnvironment>)QueryUtil.list(q,
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
	 * Returns the first account environment in the ordered set where accountEntryId = &#63; and productEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account environment
	 * @throws com.liferay.osb.NoSuchAccountEnvironmentException if a matching account environment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEnvironment findByAEI_PEI_First(long accountEntryId,
		long productEntryId, OrderByComparator orderByComparator)
		throws NoSuchAccountEnvironmentException, SystemException {
		AccountEnvironment accountEnvironment = fetchByAEI_PEI_First(accountEntryId,
				productEntryId, orderByComparator);

		if (accountEnvironment != null) {
			return accountEnvironment;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(", productEntryId=");
		msg.append(productEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountEnvironmentException(msg.toString());
	}

	/**
	 * Returns the first account environment in the ordered set where accountEntryId = &#63; and productEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account environment, or <code>null</code> if a matching account environment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEnvironment fetchByAEI_PEI_First(long accountEntryId,
		long productEntryId, OrderByComparator orderByComparator)
		throws SystemException {
		List<AccountEnvironment> list = findByAEI_PEI(accountEntryId,
				productEntryId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last account environment in the ordered set where accountEntryId = &#63; and productEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account environment
	 * @throws com.liferay.osb.NoSuchAccountEnvironmentException if a matching account environment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEnvironment findByAEI_PEI_Last(long accountEntryId,
		long productEntryId, OrderByComparator orderByComparator)
		throws NoSuchAccountEnvironmentException, SystemException {
		AccountEnvironment accountEnvironment = fetchByAEI_PEI_Last(accountEntryId,
				productEntryId, orderByComparator);

		if (accountEnvironment != null) {
			return accountEnvironment;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(", productEntryId=");
		msg.append(productEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountEnvironmentException(msg.toString());
	}

	/**
	 * Returns the last account environment in the ordered set where accountEntryId = &#63; and productEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account environment, or <code>null</code> if a matching account environment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEnvironment fetchByAEI_PEI_Last(long accountEntryId,
		long productEntryId, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByAEI_PEI(accountEntryId, productEntryId);

		List<AccountEnvironment> list = findByAEI_PEI(accountEntryId,
				productEntryId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the account environments before and after the current account environment in the ordered set where accountEntryId = &#63; and productEntryId = &#63;.
	 *
	 * @param accountEnvironmentId the primary key of the current account environment
	 * @param accountEntryId the account entry ID
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account environment
	 * @throws com.liferay.osb.NoSuchAccountEnvironmentException if a account environment with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEnvironment[] findByAEI_PEI_PrevAndNext(
		long accountEnvironmentId, long accountEntryId, long productEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchAccountEnvironmentException, SystemException {
		AccountEnvironment accountEnvironment = findByPrimaryKey(accountEnvironmentId);

		Session session = null;

		try {
			session = openSession();

			AccountEnvironment[] array = new AccountEnvironmentImpl[3];

			array[0] = getByAEI_PEI_PrevAndNext(session, accountEnvironment,
					accountEntryId, productEntryId, orderByComparator, true);

			array[1] = accountEnvironment;

			array[2] = getByAEI_PEI_PrevAndNext(session, accountEnvironment,
					accountEntryId, productEntryId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AccountEnvironment getByAEI_PEI_PrevAndNext(Session session,
		AccountEnvironment accountEnvironment, long accountEntryId,
		long productEntryId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ACCOUNTENVIRONMENT_WHERE);

		query.append(_FINDER_COLUMN_AEI_PEI_ACCOUNTENTRYID_2);

		query.append(_FINDER_COLUMN_AEI_PEI_PRODUCTENTRYID_2);

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

		qPos.add(productEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(accountEnvironment);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AccountEnvironment> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the account environment where accountEntryId = &#63; and productEntryId = &#63; and name = &#63; or throws a {@link com.liferay.osb.NoSuchAccountEnvironmentException} if it could not be found.
	 *
	 * @param accountEntryId the account entry ID
	 * @param productEntryId the product entry ID
	 * @param name the name
	 * @return the matching account environment
	 * @throws com.liferay.osb.NoSuchAccountEnvironmentException if a matching account environment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEnvironment findByAEI_PEI_N(long accountEntryId,
		long productEntryId, String name)
		throws NoSuchAccountEnvironmentException, SystemException {
		AccountEnvironment accountEnvironment = fetchByAEI_PEI_N(accountEntryId,
				productEntryId, name);

		if (accountEnvironment == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("accountEntryId=");
			msg.append(accountEntryId);

			msg.append(", productEntryId=");
			msg.append(productEntryId);

			msg.append(", name=");
			msg.append(name);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchAccountEnvironmentException(msg.toString());
		}

		return accountEnvironment;
	}

	/**
	 * Returns the account environment where accountEntryId = &#63; and productEntryId = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param accountEntryId the account entry ID
	 * @param productEntryId the product entry ID
	 * @param name the name
	 * @return the matching account environment, or <code>null</code> if a matching account environment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEnvironment fetchByAEI_PEI_N(long accountEntryId,
		long productEntryId, String name) throws SystemException {
		return fetchByAEI_PEI_N(accountEntryId, productEntryId, name, true);
	}

	/**
	 * Returns the account environment where accountEntryId = &#63; and productEntryId = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param accountEntryId the account entry ID
	 * @param productEntryId the product entry ID
	 * @param name the name
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching account environment, or <code>null</code> if a matching account environment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEnvironment fetchByAEI_PEI_N(long accountEntryId,
		long productEntryId, String name, boolean retrieveFromCache)
		throws SystemException {
		Object[] finderArgs = new Object[] { accountEntryId, productEntryId, name };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_AEI_PEI_N,
					finderArgs, this);
		}

		if (result instanceof AccountEnvironment) {
			AccountEnvironment accountEnvironment = (AccountEnvironment)result;

			if ((accountEntryId != accountEnvironment.getAccountEntryId()) ||
					(productEntryId != accountEnvironment.getProductEntryId()) ||
					!Validator.equals(name, accountEnvironment.getName())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_ACCOUNTENVIRONMENT_WHERE);

			query.append(_FINDER_COLUMN_AEI_PEI_N_ACCOUNTENTRYID_2);

			query.append(_FINDER_COLUMN_AEI_PEI_N_PRODUCTENTRYID_2);

			if (name == null) {
				query.append(_FINDER_COLUMN_AEI_PEI_N_NAME_1);
			}
			else {
				if (name.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_AEI_PEI_N_NAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_AEI_PEI_N_NAME_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEntryId);

				qPos.add(productEntryId);

				if (name != null) {
					qPos.add(name);
				}

				List<AccountEnvironment> list = q.list();

				result = list;

				AccountEnvironment accountEnvironment = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_AEI_PEI_N,
						finderArgs, list);
				}
				else {
					accountEnvironment = list.get(0);

					cacheResult(accountEnvironment);

					if ((accountEnvironment.getAccountEntryId() != accountEntryId) ||
							(accountEnvironment.getProductEntryId() != productEntryId) ||
							(accountEnvironment.getName() == null) ||
							!accountEnvironment.getName().equals(name)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_AEI_PEI_N,
							finderArgs, accountEnvironment);
					}
				}

				return accountEnvironment;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_AEI_PEI_N,
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
				return (AccountEnvironment)result;
			}
		}
	}

	/**
	 * Returns all the account environments.
	 *
	 * @return the account environments
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountEnvironment> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account environments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of account environments
	 * @param end the upper bound of the range of account environments (not inclusive)
	 * @return the range of account environments
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountEnvironment> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the account environments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of account environments
	 * @param end the upper bound of the range of account environments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of account environments
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountEnvironment> findAll(int start, int end,
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

		List<AccountEnvironment> list = (List<AccountEnvironment>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_ACCOUNTENVIRONMENT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ACCOUNTENVIRONMENT;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<AccountEnvironment>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<AccountEnvironment>)QueryUtil.list(q,
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
	 * Removes all the account environments where accountEntryId = &#63; from the database.
	 *
	 * @param accountEntryId the account entry ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByAccountEntryId(long accountEntryId)
		throws SystemException {
		for (AccountEnvironment accountEnvironment : findByAccountEntryId(
				accountEntryId)) {
			remove(accountEnvironment);
		}
	}

	/**
	 * Removes all the account environments where accountEntryId = &#63; and productEntryId = &#63; from the database.
	 *
	 * @param accountEntryId the account entry ID
	 * @param productEntryId the product entry ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByAEI_PEI(long accountEntryId, long productEntryId)
		throws SystemException {
		for (AccountEnvironment accountEnvironment : findByAEI_PEI(
				accountEntryId, productEntryId)) {
			remove(accountEnvironment);
		}
	}

	/**
	 * Removes the account environment where accountEntryId = &#63; and productEntryId = &#63; and name = &#63; from the database.
	 *
	 * @param accountEntryId the account entry ID
	 * @param productEntryId the product entry ID
	 * @param name the name
	 * @return the account environment that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEnvironment removeByAEI_PEI_N(long accountEntryId,
		long productEntryId, String name)
		throws NoSuchAccountEnvironmentException, SystemException {
		AccountEnvironment accountEnvironment = findByAEI_PEI_N(accountEntryId,
				productEntryId, name);

		return remove(accountEnvironment);
	}

	/**
	 * Removes all the account environments from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (AccountEnvironment accountEnvironment : findAll()) {
			remove(accountEnvironment);
		}
	}

	/**
	 * Returns the number of account environments where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @return the number of matching account environments
	 * @throws SystemException if a system exception occurred
	 */
	public int countByAccountEntryId(long accountEntryId)
		throws SystemException {
		Object[] finderArgs = new Object[] { accountEntryId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_ACCOUNTENTRYID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ACCOUNTENVIRONMENT_WHERE);

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
	 * Returns the number of account environments where accountEntryId = &#63; and productEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param productEntryId the product entry ID
	 * @return the number of matching account environments
	 * @throws SystemException if a system exception occurred
	 */
	public int countByAEI_PEI(long accountEntryId, long productEntryId)
		throws SystemException {
		Object[] finderArgs = new Object[] { accountEntryId, productEntryId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_AEI_PEI,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ACCOUNTENVIRONMENT_WHERE);

			query.append(_FINDER_COLUMN_AEI_PEI_ACCOUNTENTRYID_2);

			query.append(_FINDER_COLUMN_AEI_PEI_PRODUCTENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEntryId);

				qPos.add(productEntryId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_AEI_PEI,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of account environments where accountEntryId = &#63; and productEntryId = &#63; and name = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param productEntryId the product entry ID
	 * @param name the name
	 * @return the number of matching account environments
	 * @throws SystemException if a system exception occurred
	 */
	public int countByAEI_PEI_N(long accountEntryId, long productEntryId,
		String name) throws SystemException {
		Object[] finderArgs = new Object[] { accountEntryId, productEntryId, name };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_AEI_PEI_N,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_ACCOUNTENVIRONMENT_WHERE);

			query.append(_FINDER_COLUMN_AEI_PEI_N_ACCOUNTENTRYID_2);

			query.append(_FINDER_COLUMN_AEI_PEI_N_PRODUCTENTRYID_2);

			if (name == null) {
				query.append(_FINDER_COLUMN_AEI_PEI_N_NAME_1);
			}
			else {
				if (name.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_AEI_PEI_N_NAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_AEI_PEI_N_NAME_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEntryId);

				qPos.add(productEntryId);

				if (name != null) {
					qPos.add(name);
				}

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_AEI_PEI_N,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of account environments.
	 *
	 * @return the number of account environments
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ACCOUNTENVIRONMENT);

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
	 * Initializes the account environment persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.osb.model.AccountEnvironment")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<AccountEnvironment>> listenersList = new ArrayList<ModelListener<AccountEnvironment>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<AccountEnvironment>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(AccountEnvironmentImpl.class.getName());
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
	@BeanReference(type = ListTypePersistence.class)
	protected ListTypePersistence listTypePersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_ACCOUNTENVIRONMENT = "SELECT accountEnvironment FROM AccountEnvironment accountEnvironment";
	private static final String _SQL_SELECT_ACCOUNTENVIRONMENT_WHERE = "SELECT accountEnvironment FROM AccountEnvironment accountEnvironment WHERE ";
	private static final String _SQL_COUNT_ACCOUNTENVIRONMENT = "SELECT COUNT(accountEnvironment) FROM AccountEnvironment accountEnvironment";
	private static final String _SQL_COUNT_ACCOUNTENVIRONMENT_WHERE = "SELECT COUNT(accountEnvironment) FROM AccountEnvironment accountEnvironment WHERE ";
	private static final String _FINDER_COLUMN_ACCOUNTENTRYID_ACCOUNTENTRYID_2 = "accountEnvironment.accountEntryId = ?";
	private static final String _FINDER_COLUMN_AEI_PEI_ACCOUNTENTRYID_2 = "accountEnvironment.accountEntryId = ? AND ";
	private static final String _FINDER_COLUMN_AEI_PEI_PRODUCTENTRYID_2 = "accountEnvironment.productEntryId = ?";
	private static final String _FINDER_COLUMN_AEI_PEI_N_ACCOUNTENTRYID_2 = "accountEnvironment.accountEntryId = ? AND ";
	private static final String _FINDER_COLUMN_AEI_PEI_N_PRODUCTENTRYID_2 = "accountEnvironment.productEntryId = ? AND ";
	private static final String _FINDER_COLUMN_AEI_PEI_N_NAME_1 = "accountEnvironment.name IS NULL";
	private static final String _FINDER_COLUMN_AEI_PEI_N_NAME_2 = "accountEnvironment.name = ?";
	private static final String _FINDER_COLUMN_AEI_PEI_N_NAME_3 = "(accountEnvironment.name IS NULL OR accountEnvironment.name = ?)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "accountEnvironment.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AccountEnvironment exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No AccountEnvironment exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(AccountEnvironmentPersistenceImpl.class);
	private static AccountEnvironment _nullAccountEnvironment = new AccountEnvironmentImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<AccountEnvironment> toCacheModel() {
				return _nullAccountEnvironmentCacheModel;
			}
		};

	private static CacheModel<AccountEnvironment> _nullAccountEnvironmentCacheModel =
		new CacheModel<AccountEnvironment>() {
			public AccountEnvironment toEntityModel() {
				return _nullAccountEnvironment;
			}
		};
}