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

import com.liferay.osb.NoSuchAccountWorkerException;
import com.liferay.osb.model.AccountWorker;
import com.liferay.osb.model.impl.AccountWorkerImpl;
import com.liferay.osb.model.impl.AccountWorkerModelImpl;

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
 * The persistence implementation for the account worker service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountWorkerPersistence
 * @see AccountWorkerUtil
 * @generated
 */
public class AccountWorkerPersistenceImpl extends BasePersistenceImpl<AccountWorker>
	implements AccountWorkerPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AccountWorkerUtil} to access the account worker persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AccountWorkerImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID = new FinderPath(AccountWorkerModelImpl.ENTITY_CACHE_ENABLED,
			AccountWorkerModelImpl.FINDER_CACHE_ENABLED,
			AccountWorkerImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUserId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID =
		new FinderPath(AccountWorkerModelImpl.ENTITY_CACHE_ENABLED,
			AccountWorkerModelImpl.FINDER_CACHE_ENABLED,
			AccountWorkerImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByUserId", new String[] { Long.class.getName() },
			AccountWorkerModelImpl.USERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(AccountWorkerModelImpl.ENTITY_CACHE_ENABLED,
			AccountWorkerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ACCOUNTENTRYID =
		new FinderPath(AccountWorkerModelImpl.ENTITY_CACHE_ENABLED,
			AccountWorkerModelImpl.FINDER_CACHE_ENABLED,
			AccountWorkerImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByAccountEntryId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID =
		new FinderPath(AccountWorkerModelImpl.ENTITY_CACHE_ENABLED,
			AccountWorkerModelImpl.FINDER_CACHE_ENABLED,
			AccountWorkerImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByAccountEntryId", new String[] { Long.class.getName() },
			AccountWorkerModelImpl.ACCOUNTENTRYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ACCOUNTENTRYID = new FinderPath(AccountWorkerModelImpl.ENTITY_CACHE_ENABLED,
			AccountWorkerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAccountEntryId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_U_AEI = new FinderPath(AccountWorkerModelImpl.ENTITY_CACHE_ENABLED,
			AccountWorkerModelImpl.FINDER_CACHE_ENABLED,
			AccountWorkerImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByU_AEI",
			new String[] { Long.class.getName(), Long.class.getName() },
			AccountWorkerModelImpl.USERID_COLUMN_BITMASK |
			AccountWorkerModelImpl.ACCOUNTENTRYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_U_AEI = new FinderPath(AccountWorkerModelImpl.ENTITY_CACHE_ENABLED,
			AccountWorkerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_AEI",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_U_R = new FinderPath(AccountWorkerModelImpl.ENTITY_CACHE_ENABLED,
			AccountWorkerModelImpl.FINDER_CACHE_ENABLED,
			AccountWorkerImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByU_R",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_R = new FinderPath(AccountWorkerModelImpl.ENTITY_CACHE_ENABLED,
			AccountWorkerModelImpl.FINDER_CACHE_ENABLED,
			AccountWorkerImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByU_R",
			new String[] { Long.class.getName(), Integer.class.getName() },
			AccountWorkerModelImpl.USERID_COLUMN_BITMASK |
			AccountWorkerModelImpl.ROLE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_U_R = new FinderPath(AccountWorkerModelImpl.ENTITY_CACHE_ENABLED,
			AccountWorkerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_R",
			new String[] { Long.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_AEI_R = new FinderPath(AccountWorkerModelImpl.ENTITY_CACHE_ENABLED,
			AccountWorkerModelImpl.FINDER_CACHE_ENABLED,
			AccountWorkerImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByAEI_R",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_R = new FinderPath(AccountWorkerModelImpl.ENTITY_CACHE_ENABLED,
			AccountWorkerModelImpl.FINDER_CACHE_ENABLED,
			AccountWorkerImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByAEI_R",
			new String[] { Long.class.getName(), Integer.class.getName() },
			AccountWorkerModelImpl.ACCOUNTENTRYID_COLUMN_BITMASK |
			AccountWorkerModelImpl.ROLE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_AEI_R = new FinderPath(AccountWorkerModelImpl.ENTITY_CACHE_ENABLED,
			AccountWorkerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAEI_R",
			new String[] { Long.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_AEI_NOTN = new FinderPath(AccountWorkerModelImpl.ENTITY_CACHE_ENABLED,
			AccountWorkerModelImpl.FINDER_CACHE_ENABLED,
			AccountWorkerImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByAEI_NotN",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_AEI_NOTN =
		new FinderPath(AccountWorkerModelImpl.ENTITY_CACHE_ENABLED,
			AccountWorkerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByAEI_NotN",
			new String[] { Long.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AccountWorkerModelImpl.ENTITY_CACHE_ENABLED,
			AccountWorkerModelImpl.FINDER_CACHE_ENABLED,
			AccountWorkerImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AccountWorkerModelImpl.ENTITY_CACHE_ENABLED,
			AccountWorkerModelImpl.FINDER_CACHE_ENABLED,
			AccountWorkerImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AccountWorkerModelImpl.ENTITY_CACHE_ENABLED,
			AccountWorkerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the account worker in the entity cache if it is enabled.
	 *
	 * @param accountWorker the account worker
	 */
	public void cacheResult(AccountWorker accountWorker) {
		EntityCacheUtil.putResult(AccountWorkerModelImpl.ENTITY_CACHE_ENABLED,
			AccountWorkerImpl.class, accountWorker.getPrimaryKey(),
			accountWorker);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_AEI,
			new Object[] {
				Long.valueOf(accountWorker.getUserId()),
				Long.valueOf(accountWorker.getAccountEntryId())
			}, accountWorker);

		accountWorker.resetOriginalValues();
	}

	/**
	 * Caches the account workers in the entity cache if it is enabled.
	 *
	 * @param accountWorkers the account workers
	 */
	public void cacheResult(List<AccountWorker> accountWorkers) {
		for (AccountWorker accountWorker : accountWorkers) {
			if (EntityCacheUtil.getResult(
						AccountWorkerModelImpl.ENTITY_CACHE_ENABLED,
						AccountWorkerImpl.class, accountWorker.getPrimaryKey()) == null) {
				cacheResult(accountWorker);
			}
			else {
				accountWorker.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all account workers.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(AccountWorkerImpl.class.getName());
		}

		EntityCacheUtil.clearCache(AccountWorkerImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the account worker.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AccountWorker accountWorker) {
		EntityCacheUtil.removeResult(AccountWorkerModelImpl.ENTITY_CACHE_ENABLED,
			AccountWorkerImpl.class, accountWorker.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(accountWorker);
	}

	@Override
	public void clearCache(List<AccountWorker> accountWorkers) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AccountWorker accountWorker : accountWorkers) {
			EntityCacheUtil.removeResult(AccountWorkerModelImpl.ENTITY_CACHE_ENABLED,
				AccountWorkerImpl.class, accountWorker.getPrimaryKey());

			clearUniqueFindersCache(accountWorker);
		}
	}

	protected void cacheUniqueFindersCache(AccountWorker accountWorker) {
		if (accountWorker.isNew()) {
			Object[] args = new Object[] {
					Long.valueOf(accountWorker.getUserId()),
					Long.valueOf(accountWorker.getAccountEntryId())
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_U_AEI, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_AEI, args,
				accountWorker);
		}
		else {
			AccountWorkerModelImpl accountWorkerModelImpl = (AccountWorkerModelImpl)accountWorker;

			if ((accountWorkerModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_U_AEI.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(accountWorker.getUserId()),
						Long.valueOf(accountWorker.getAccountEntryId())
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_U_AEI, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_AEI, args,
					accountWorker);
			}
		}
	}

	protected void clearUniqueFindersCache(AccountWorker accountWorker) {
		AccountWorkerModelImpl accountWorkerModelImpl = (AccountWorkerModelImpl)accountWorker;

		Object[] args = new Object[] {
				Long.valueOf(accountWorker.getUserId()),
				Long.valueOf(accountWorker.getAccountEntryId())
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_U_AEI, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_U_AEI, args);

		if ((accountWorkerModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_U_AEI.getColumnBitmask()) != 0) {
			args = new Object[] {
					Long.valueOf(accountWorkerModelImpl.getOriginalUserId()),
					Long.valueOf(accountWorkerModelImpl.getOriginalAccountEntryId())
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_U_AEI, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_U_AEI, args);
		}
	}

	/**
	 * Creates a new account worker with the primary key. Does not add the account worker to the database.
	 *
	 * @param accountWorkerId the primary key for the new account worker
	 * @return the new account worker
	 */
	public AccountWorker create(long accountWorkerId) {
		AccountWorker accountWorker = new AccountWorkerImpl();

		accountWorker.setNew(true);
		accountWorker.setPrimaryKey(accountWorkerId);

		return accountWorker;
	}

	/**
	 * Removes the account worker with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param accountWorkerId the primary key of the account worker
	 * @return the account worker that was removed
	 * @throws com.liferay.osb.NoSuchAccountWorkerException if a account worker with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountWorker remove(long accountWorkerId)
		throws NoSuchAccountWorkerException, SystemException {
		return remove(Long.valueOf(accountWorkerId));
	}

	/**
	 * Removes the account worker with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the account worker
	 * @return the account worker that was removed
	 * @throws com.liferay.osb.NoSuchAccountWorkerException if a account worker with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AccountWorker remove(Serializable primaryKey)
		throws NoSuchAccountWorkerException, SystemException {
		Session session = null;

		try {
			session = openSession();

			AccountWorker accountWorker = (AccountWorker)session.get(AccountWorkerImpl.class,
					primaryKey);

			if (accountWorker == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAccountWorkerException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(accountWorker);
		}
		catch (NoSuchAccountWorkerException nsee) {
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
	protected AccountWorker removeImpl(AccountWorker accountWorker)
		throws SystemException {
		accountWorker = toUnwrappedModel(accountWorker);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, accountWorker);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(accountWorker);

		return accountWorker;
	}

	@Override
	public AccountWorker updateImpl(
		com.liferay.osb.model.AccountWorker accountWorker, boolean merge)
		throws SystemException {
		accountWorker = toUnwrappedModel(accountWorker);

		boolean isNew = accountWorker.isNew();

		AccountWorkerModelImpl accountWorkerModelImpl = (AccountWorkerModelImpl)accountWorker;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, accountWorker, merge);

			accountWorker.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !AccountWorkerModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((accountWorkerModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(accountWorkerModelImpl.getOriginalUserId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);

				args = new Object[] {
						Long.valueOf(accountWorkerModelImpl.getUserId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);
			}

			if ((accountWorkerModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(accountWorkerModelImpl.getOriginalAccountEntryId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTENTRYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID,
					args);

				args = new Object[] {
						Long.valueOf(accountWorkerModelImpl.getAccountEntryId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTENTRYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID,
					args);
			}

			if ((accountWorkerModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_R.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(accountWorkerModelImpl.getOriginalUserId()),
						Integer.valueOf(accountWorkerModelImpl.getOriginalRole())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_U_R, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_R,
					args);

				args = new Object[] {
						Long.valueOf(accountWorkerModelImpl.getUserId()),
						Integer.valueOf(accountWorkerModelImpl.getRole())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_U_R, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_R,
					args);
			}

			if ((accountWorkerModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_R.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(accountWorkerModelImpl.getOriginalAccountEntryId()),
						Integer.valueOf(accountWorkerModelImpl.getOriginalRole())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_AEI_R, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_R,
					args);

				args = new Object[] {
						Long.valueOf(accountWorkerModelImpl.getAccountEntryId()),
						Integer.valueOf(accountWorkerModelImpl.getRole())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_AEI_R, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_R,
					args);
			}
		}

		EntityCacheUtil.putResult(AccountWorkerModelImpl.ENTITY_CACHE_ENABLED,
			AccountWorkerImpl.class, accountWorker.getPrimaryKey(),
			accountWorker);

		clearUniqueFindersCache(accountWorker);
		cacheUniqueFindersCache(accountWorker);

		return accountWorker;
	}

	protected AccountWorker toUnwrappedModel(AccountWorker accountWorker) {
		if (accountWorker instanceof AccountWorkerImpl) {
			return accountWorker;
		}

		AccountWorkerImpl accountWorkerImpl = new AccountWorkerImpl();

		accountWorkerImpl.setNew(accountWorker.isNew());
		accountWorkerImpl.setPrimaryKey(accountWorker.getPrimaryKey());

		accountWorkerImpl.setAccountWorkerId(accountWorker.getAccountWorkerId());
		accountWorkerImpl.setUserId(accountWorker.getUserId());
		accountWorkerImpl.setAccountEntryId(accountWorker.getAccountEntryId());
		accountWorkerImpl.setRole(accountWorker.getRole());
		accountWorkerImpl.setNotifications(accountWorker.getNotifications());

		return accountWorkerImpl;
	}

	/**
	 * Returns the account worker with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the account worker
	 * @return the account worker
	 * @throws com.liferay.portal.NoSuchModelException if a account worker with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AccountWorker findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the account worker with the primary key or throws a {@link com.liferay.osb.NoSuchAccountWorkerException} if it could not be found.
	 *
	 * @param accountWorkerId the primary key of the account worker
	 * @return the account worker
	 * @throws com.liferay.osb.NoSuchAccountWorkerException if a account worker with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountWorker findByPrimaryKey(long accountWorkerId)
		throws NoSuchAccountWorkerException, SystemException {
		AccountWorker accountWorker = fetchByPrimaryKey(accountWorkerId);

		if (accountWorker == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + accountWorkerId);
			}

			throw new NoSuchAccountWorkerException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				accountWorkerId);
		}

		return accountWorker;
	}

	/**
	 * Returns the account worker with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the account worker
	 * @return the account worker, or <code>null</code> if a account worker with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AccountWorker fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the account worker with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param accountWorkerId the primary key of the account worker
	 * @return the account worker, or <code>null</code> if a account worker with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountWorker fetchByPrimaryKey(long accountWorkerId)
		throws SystemException {
		AccountWorker accountWorker = (AccountWorker)EntityCacheUtil.getResult(AccountWorkerModelImpl.ENTITY_CACHE_ENABLED,
				AccountWorkerImpl.class, accountWorkerId);

		if (accountWorker == _nullAccountWorker) {
			return null;
		}

		if (accountWorker == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				accountWorker = (AccountWorker)session.get(AccountWorkerImpl.class,
						Long.valueOf(accountWorkerId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (accountWorker != null) {
					cacheResult(accountWorker);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(AccountWorkerModelImpl.ENTITY_CACHE_ENABLED,
						AccountWorkerImpl.class, accountWorkerId,
						_nullAccountWorker);
				}

				closeSession(session);
			}
		}

		return accountWorker;
	}

	/**
	 * Returns all the account workers where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching account workers
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountWorker> findByUserId(long userId)
		throws SystemException {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account workers where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of account workers
	 * @param end the upper bound of the range of account workers (not inclusive)
	 * @return the range of matching account workers
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountWorker> findByUserId(long userId, int start, int end)
		throws SystemException {
		return findByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the account workers where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of account workers
	 * @param end the upper bound of the range of account workers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account workers
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountWorker> findByUserId(long userId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID;
			finderArgs = new Object[] { userId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID;
			finderArgs = new Object[] { userId, start, end, orderByComparator };
		}

		List<AccountWorker> list = (List<AccountWorker>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AccountWorker accountWorker : list) {
				if ((userId != accountWorker.getUserId())) {
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

			query.append(_SQL_SELECT_ACCOUNTWORKER_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

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

				qPos.add(userId);

				list = (List<AccountWorker>)QueryUtil.list(q, getDialect(),
						start, end);
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
	 * Returns the first account worker in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account worker
	 * @throws com.liferay.osb.NoSuchAccountWorkerException if a matching account worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountWorker findByUserId_First(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchAccountWorkerException, SystemException {
		AccountWorker accountWorker = fetchByUserId_First(userId,
				orderByComparator);

		if (accountWorker != null) {
			return accountWorker;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountWorkerException(msg.toString());
	}

	/**
	 * Returns the first account worker in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account worker, or <code>null</code> if a matching account worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountWorker fetchByUserId_First(long userId,
		OrderByComparator orderByComparator) throws SystemException {
		List<AccountWorker> list = findByUserId(userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last account worker in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account worker
	 * @throws com.liferay.osb.NoSuchAccountWorkerException if a matching account worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountWorker findByUserId_Last(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchAccountWorkerException, SystemException {
		AccountWorker accountWorker = fetchByUserId_Last(userId,
				orderByComparator);

		if (accountWorker != null) {
			return accountWorker;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountWorkerException(msg.toString());
	}

	/**
	 * Returns the last account worker in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account worker, or <code>null</code> if a matching account worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountWorker fetchByUserId_Last(long userId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUserId(userId);

		List<AccountWorker> list = findByUserId(userId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the account workers before and after the current account worker in the ordered set where userId = &#63;.
	 *
	 * @param accountWorkerId the primary key of the current account worker
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account worker
	 * @throws com.liferay.osb.NoSuchAccountWorkerException if a account worker with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountWorker[] findByUserId_PrevAndNext(long accountWorkerId,
		long userId, OrderByComparator orderByComparator)
		throws NoSuchAccountWorkerException, SystemException {
		AccountWorker accountWorker = findByPrimaryKey(accountWorkerId);

		Session session = null;

		try {
			session = openSession();

			AccountWorker[] array = new AccountWorkerImpl[3];

			array[0] = getByUserId_PrevAndNext(session, accountWorker, userId,
					orderByComparator, true);

			array[1] = accountWorker;

			array[2] = getByUserId_PrevAndNext(session, accountWorker, userId,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AccountWorker getByUserId_PrevAndNext(Session session,
		AccountWorker accountWorker, long userId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ACCOUNTWORKER_WHERE);

		query.append(_FINDER_COLUMN_USERID_USERID_2);

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

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(accountWorker);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AccountWorker> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the account workers where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @return the matching account workers
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountWorker> findByAccountEntryId(long accountEntryId)
		throws SystemException {
		return findByAccountEntryId(accountEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account workers where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of account workers
	 * @param end the upper bound of the range of account workers (not inclusive)
	 * @return the range of matching account workers
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountWorker> findByAccountEntryId(long accountEntryId,
		int start, int end) throws SystemException {
		return findByAccountEntryId(accountEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the account workers where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of account workers
	 * @param end the upper bound of the range of account workers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account workers
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountWorker> findByAccountEntryId(long accountEntryId,
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

		List<AccountWorker> list = (List<AccountWorker>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AccountWorker accountWorker : list) {
				if ((accountEntryId != accountWorker.getAccountEntryId())) {
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

			query.append(_SQL_SELECT_ACCOUNTWORKER_WHERE);

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

				list = (List<AccountWorker>)QueryUtil.list(q, getDialect(),
						start, end);
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
	 * Returns the first account worker in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account worker
	 * @throws com.liferay.osb.NoSuchAccountWorkerException if a matching account worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountWorker findByAccountEntryId_First(long accountEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchAccountWorkerException, SystemException {
		AccountWorker accountWorker = fetchByAccountEntryId_First(accountEntryId,
				orderByComparator);

		if (accountWorker != null) {
			return accountWorker;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountWorkerException(msg.toString());
	}

	/**
	 * Returns the first account worker in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account worker, or <code>null</code> if a matching account worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountWorker fetchByAccountEntryId_First(long accountEntryId,
		OrderByComparator orderByComparator) throws SystemException {
		List<AccountWorker> list = findByAccountEntryId(accountEntryId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last account worker in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account worker
	 * @throws com.liferay.osb.NoSuchAccountWorkerException if a matching account worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountWorker findByAccountEntryId_Last(long accountEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchAccountWorkerException, SystemException {
		AccountWorker accountWorker = fetchByAccountEntryId_Last(accountEntryId,
				orderByComparator);

		if (accountWorker != null) {
			return accountWorker;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountWorkerException(msg.toString());
	}

	/**
	 * Returns the last account worker in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account worker, or <code>null</code> if a matching account worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountWorker fetchByAccountEntryId_Last(long accountEntryId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByAccountEntryId(accountEntryId);

		List<AccountWorker> list = findByAccountEntryId(accountEntryId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the account workers before and after the current account worker in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountWorkerId the primary key of the current account worker
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account worker
	 * @throws com.liferay.osb.NoSuchAccountWorkerException if a account worker with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountWorker[] findByAccountEntryId_PrevAndNext(
		long accountWorkerId, long accountEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchAccountWorkerException, SystemException {
		AccountWorker accountWorker = findByPrimaryKey(accountWorkerId);

		Session session = null;

		try {
			session = openSession();

			AccountWorker[] array = new AccountWorkerImpl[3];

			array[0] = getByAccountEntryId_PrevAndNext(session, accountWorker,
					accountEntryId, orderByComparator, true);

			array[1] = accountWorker;

			array[2] = getByAccountEntryId_PrevAndNext(session, accountWorker,
					accountEntryId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AccountWorker getByAccountEntryId_PrevAndNext(Session session,
		AccountWorker accountWorker, long accountEntryId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ACCOUNTWORKER_WHERE);

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
			Object[] values = orderByComparator.getOrderByConditionValues(accountWorker);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AccountWorker> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the account worker where userId = &#63; and accountEntryId = &#63; or throws a {@link com.liferay.osb.NoSuchAccountWorkerException} if it could not be found.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @return the matching account worker
	 * @throws com.liferay.osb.NoSuchAccountWorkerException if a matching account worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountWorker findByU_AEI(long userId, long accountEntryId)
		throws NoSuchAccountWorkerException, SystemException {
		AccountWorker accountWorker = fetchByU_AEI(userId, accountEntryId);

		if (accountWorker == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("userId=");
			msg.append(userId);

			msg.append(", accountEntryId=");
			msg.append(accountEntryId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchAccountWorkerException(msg.toString());
		}

		return accountWorker;
	}

	/**
	 * Returns the account worker where userId = &#63; and accountEntryId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @return the matching account worker, or <code>null</code> if a matching account worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountWorker fetchByU_AEI(long userId, long accountEntryId)
		throws SystemException {
		return fetchByU_AEI(userId, accountEntryId, true);
	}

	/**
	 * Returns the account worker where userId = &#63; and accountEntryId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching account worker, or <code>null</code> if a matching account worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountWorker fetchByU_AEI(long userId, long accountEntryId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { userId, accountEntryId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_U_AEI,
					finderArgs, this);
		}

		if (result instanceof AccountWorker) {
			AccountWorker accountWorker = (AccountWorker)result;

			if ((userId != accountWorker.getUserId()) ||
					(accountEntryId != accountWorker.getAccountEntryId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_ACCOUNTWORKER_WHERE);

			query.append(_FINDER_COLUMN_U_AEI_USERID_2);

			query.append(_FINDER_COLUMN_U_AEI_ACCOUNTENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(accountEntryId);

				List<AccountWorker> list = q.list();

				result = list;

				AccountWorker accountWorker = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_AEI,
						finderArgs, list);
				}
				else {
					accountWorker = list.get(0);

					cacheResult(accountWorker);

					if ((accountWorker.getUserId() != userId) ||
							(accountWorker.getAccountEntryId() != accountEntryId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_AEI,
							finderArgs, accountWorker);
					}
				}

				return accountWorker;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_U_AEI,
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
				return (AccountWorker)result;
			}
		}
	}

	/**
	 * Returns all the account workers where userId = &#63; and role = &#63;.
	 *
	 * @param userId the user ID
	 * @param role the role
	 * @return the matching account workers
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountWorker> findByU_R(long userId, int role)
		throws SystemException {
		return findByU_R(userId, role, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the account workers where userId = &#63; and role = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param role the role
	 * @param start the lower bound of the range of account workers
	 * @param end the upper bound of the range of account workers (not inclusive)
	 * @return the range of matching account workers
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountWorker> findByU_R(long userId, int role, int start,
		int end) throws SystemException {
		return findByU_R(userId, role, start, end, null);
	}

	/**
	 * Returns an ordered range of all the account workers where userId = &#63; and role = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param role the role
	 * @param start the lower bound of the range of account workers
	 * @param end the upper bound of the range of account workers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account workers
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountWorker> findByU_R(long userId, int role, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_R;
			finderArgs = new Object[] { userId, role };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_U_R;
			finderArgs = new Object[] {
					userId, role,
					
					start, end, orderByComparator
				};
		}

		List<AccountWorker> list = (List<AccountWorker>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AccountWorker accountWorker : list) {
				if ((userId != accountWorker.getUserId()) ||
						(role != accountWorker.getRole())) {
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

			query.append(_SQL_SELECT_ACCOUNTWORKER_WHERE);

			query.append(_FINDER_COLUMN_U_R_USERID_2);

			query.append(_FINDER_COLUMN_U_R_ROLE_2);

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

				qPos.add(userId);

				qPos.add(role);

				list = (List<AccountWorker>)QueryUtil.list(q, getDialect(),
						start, end);
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
	 * Returns the first account worker in the ordered set where userId = &#63; and role = &#63;.
	 *
	 * @param userId the user ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account worker
	 * @throws com.liferay.osb.NoSuchAccountWorkerException if a matching account worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountWorker findByU_R_First(long userId, int role,
		OrderByComparator orderByComparator)
		throws NoSuchAccountWorkerException, SystemException {
		AccountWorker accountWorker = fetchByU_R_First(userId, role,
				orderByComparator);

		if (accountWorker != null) {
			return accountWorker;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", role=");
		msg.append(role);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountWorkerException(msg.toString());
	}

	/**
	 * Returns the first account worker in the ordered set where userId = &#63; and role = &#63;.
	 *
	 * @param userId the user ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account worker, or <code>null</code> if a matching account worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountWorker fetchByU_R_First(long userId, int role,
		OrderByComparator orderByComparator) throws SystemException {
		List<AccountWorker> list = findByU_R(userId, role, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last account worker in the ordered set where userId = &#63; and role = &#63;.
	 *
	 * @param userId the user ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account worker
	 * @throws com.liferay.osb.NoSuchAccountWorkerException if a matching account worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountWorker findByU_R_Last(long userId, int role,
		OrderByComparator orderByComparator)
		throws NoSuchAccountWorkerException, SystemException {
		AccountWorker accountWorker = fetchByU_R_Last(userId, role,
				orderByComparator);

		if (accountWorker != null) {
			return accountWorker;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", role=");
		msg.append(role);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountWorkerException(msg.toString());
	}

	/**
	 * Returns the last account worker in the ordered set where userId = &#63; and role = &#63;.
	 *
	 * @param userId the user ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account worker, or <code>null</code> if a matching account worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountWorker fetchByU_R_Last(long userId, int role,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByU_R(userId, role);

		List<AccountWorker> list = findByU_R(userId, role, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the account workers before and after the current account worker in the ordered set where userId = &#63; and role = &#63;.
	 *
	 * @param accountWorkerId the primary key of the current account worker
	 * @param userId the user ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account worker
	 * @throws com.liferay.osb.NoSuchAccountWorkerException if a account worker with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountWorker[] findByU_R_PrevAndNext(long accountWorkerId,
		long userId, int role, OrderByComparator orderByComparator)
		throws NoSuchAccountWorkerException, SystemException {
		AccountWorker accountWorker = findByPrimaryKey(accountWorkerId);

		Session session = null;

		try {
			session = openSession();

			AccountWorker[] array = new AccountWorkerImpl[3];

			array[0] = getByU_R_PrevAndNext(session, accountWorker, userId,
					role, orderByComparator, true);

			array[1] = accountWorker;

			array[2] = getByU_R_PrevAndNext(session, accountWorker, userId,
					role, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AccountWorker getByU_R_PrevAndNext(Session session,
		AccountWorker accountWorker, long userId, int role,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ACCOUNTWORKER_WHERE);

		query.append(_FINDER_COLUMN_U_R_USERID_2);

		query.append(_FINDER_COLUMN_U_R_ROLE_2);

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

		qPos.add(userId);

		qPos.add(role);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(accountWorker);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AccountWorker> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the account workers where accountEntryId = &#63; and role = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param role the role
	 * @return the matching account workers
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountWorker> findByAEI_R(long accountEntryId, int role)
		throws SystemException {
		return findByAEI_R(accountEntryId, role, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account workers where accountEntryId = &#63; and role = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param role the role
	 * @param start the lower bound of the range of account workers
	 * @param end the upper bound of the range of account workers (not inclusive)
	 * @return the range of matching account workers
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountWorker> findByAEI_R(long accountEntryId, int role,
		int start, int end) throws SystemException {
		return findByAEI_R(accountEntryId, role, start, end, null);
	}

	/**
	 * Returns an ordered range of all the account workers where accountEntryId = &#63; and role = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param role the role
	 * @param start the lower bound of the range of account workers
	 * @param end the upper bound of the range of account workers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account workers
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountWorker> findByAEI_R(long accountEntryId, int role,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_R;
			finderArgs = new Object[] { accountEntryId, role };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_AEI_R;
			finderArgs = new Object[] {
					accountEntryId, role,
					
					start, end, orderByComparator
				};
		}

		List<AccountWorker> list = (List<AccountWorker>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AccountWorker accountWorker : list) {
				if ((accountEntryId != accountWorker.getAccountEntryId()) ||
						(role != accountWorker.getRole())) {
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

			query.append(_SQL_SELECT_ACCOUNTWORKER_WHERE);

			query.append(_FINDER_COLUMN_AEI_R_ACCOUNTENTRYID_2);

			query.append(_FINDER_COLUMN_AEI_R_ROLE_2);

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

				qPos.add(role);

				list = (List<AccountWorker>)QueryUtil.list(q, getDialect(),
						start, end);
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
	 * Returns the first account worker in the ordered set where accountEntryId = &#63; and role = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account worker
	 * @throws com.liferay.osb.NoSuchAccountWorkerException if a matching account worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountWorker findByAEI_R_First(long accountEntryId, int role,
		OrderByComparator orderByComparator)
		throws NoSuchAccountWorkerException, SystemException {
		AccountWorker accountWorker = fetchByAEI_R_First(accountEntryId, role,
				orderByComparator);

		if (accountWorker != null) {
			return accountWorker;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(", role=");
		msg.append(role);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountWorkerException(msg.toString());
	}

	/**
	 * Returns the first account worker in the ordered set where accountEntryId = &#63; and role = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account worker, or <code>null</code> if a matching account worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountWorker fetchByAEI_R_First(long accountEntryId, int role,
		OrderByComparator orderByComparator) throws SystemException {
		List<AccountWorker> list = findByAEI_R(accountEntryId, role, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last account worker in the ordered set where accountEntryId = &#63; and role = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account worker
	 * @throws com.liferay.osb.NoSuchAccountWorkerException if a matching account worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountWorker findByAEI_R_Last(long accountEntryId, int role,
		OrderByComparator orderByComparator)
		throws NoSuchAccountWorkerException, SystemException {
		AccountWorker accountWorker = fetchByAEI_R_Last(accountEntryId, role,
				orderByComparator);

		if (accountWorker != null) {
			return accountWorker;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(", role=");
		msg.append(role);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountWorkerException(msg.toString());
	}

	/**
	 * Returns the last account worker in the ordered set where accountEntryId = &#63; and role = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account worker, or <code>null</code> if a matching account worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountWorker fetchByAEI_R_Last(long accountEntryId, int role,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByAEI_R(accountEntryId, role);

		List<AccountWorker> list = findByAEI_R(accountEntryId, role, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the account workers before and after the current account worker in the ordered set where accountEntryId = &#63; and role = &#63;.
	 *
	 * @param accountWorkerId the primary key of the current account worker
	 * @param accountEntryId the account entry ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account worker
	 * @throws com.liferay.osb.NoSuchAccountWorkerException if a account worker with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountWorker[] findByAEI_R_PrevAndNext(long accountWorkerId,
		long accountEntryId, int role, OrderByComparator orderByComparator)
		throws NoSuchAccountWorkerException, SystemException {
		AccountWorker accountWorker = findByPrimaryKey(accountWorkerId);

		Session session = null;

		try {
			session = openSession();

			AccountWorker[] array = new AccountWorkerImpl[3];

			array[0] = getByAEI_R_PrevAndNext(session, accountWorker,
					accountEntryId, role, orderByComparator, true);

			array[1] = accountWorker;

			array[2] = getByAEI_R_PrevAndNext(session, accountWorker,
					accountEntryId, role, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AccountWorker getByAEI_R_PrevAndNext(Session session,
		AccountWorker accountWorker, long accountEntryId, int role,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ACCOUNTWORKER_WHERE);

		query.append(_FINDER_COLUMN_AEI_R_ACCOUNTENTRYID_2);

		query.append(_FINDER_COLUMN_AEI_R_ROLE_2);

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

		qPos.add(role);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(accountWorker);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AccountWorker> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the account workers where accountEntryId = &#63; and notifications &ne; &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param notifications the notifications
	 * @return the matching account workers
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountWorker> findByAEI_NotN(long accountEntryId,
		int notifications) throws SystemException {
		return findByAEI_NotN(accountEntryId, notifications, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account workers where accountEntryId = &#63; and notifications &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param notifications the notifications
	 * @param start the lower bound of the range of account workers
	 * @param end the upper bound of the range of account workers (not inclusive)
	 * @return the range of matching account workers
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountWorker> findByAEI_NotN(long accountEntryId,
		int notifications, int start, int end) throws SystemException {
		return findByAEI_NotN(accountEntryId, notifications, start, end, null);
	}

	/**
	 * Returns an ordered range of all the account workers where accountEntryId = &#63; and notifications &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param notifications the notifications
	 * @param start the lower bound of the range of account workers
	 * @param end the upper bound of the range of account workers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account workers
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountWorker> findByAEI_NotN(long accountEntryId,
		int notifications, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_AEI_NOTN;
		finderArgs = new Object[] {
				accountEntryId, notifications,
				
				start, end, orderByComparator
			};

		List<AccountWorker> list = (List<AccountWorker>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AccountWorker accountWorker : list) {
				if ((accountEntryId != accountWorker.getAccountEntryId()) ||
						(notifications != accountWorker.getNotifications())) {
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

			query.append(_SQL_SELECT_ACCOUNTWORKER_WHERE);

			query.append(_FINDER_COLUMN_AEI_NOTN_ACCOUNTENTRYID_2);

			query.append(_FINDER_COLUMN_AEI_NOTN_NOTIFICATIONS_2);

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

				qPos.add(notifications);

				list = (List<AccountWorker>)QueryUtil.list(q, getDialect(),
						start, end);
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
	 * Returns the first account worker in the ordered set where accountEntryId = &#63; and notifications &ne; &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param notifications the notifications
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account worker
	 * @throws com.liferay.osb.NoSuchAccountWorkerException if a matching account worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountWorker findByAEI_NotN_First(long accountEntryId,
		int notifications, OrderByComparator orderByComparator)
		throws NoSuchAccountWorkerException, SystemException {
		AccountWorker accountWorker = fetchByAEI_NotN_First(accountEntryId,
				notifications, orderByComparator);

		if (accountWorker != null) {
			return accountWorker;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(", notifications=");
		msg.append(notifications);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountWorkerException(msg.toString());
	}

	/**
	 * Returns the first account worker in the ordered set where accountEntryId = &#63; and notifications &ne; &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param notifications the notifications
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account worker, or <code>null</code> if a matching account worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountWorker fetchByAEI_NotN_First(long accountEntryId,
		int notifications, OrderByComparator orderByComparator)
		throws SystemException {
		List<AccountWorker> list = findByAEI_NotN(accountEntryId,
				notifications, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last account worker in the ordered set where accountEntryId = &#63; and notifications &ne; &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param notifications the notifications
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account worker
	 * @throws com.liferay.osb.NoSuchAccountWorkerException if a matching account worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountWorker findByAEI_NotN_Last(long accountEntryId,
		int notifications, OrderByComparator orderByComparator)
		throws NoSuchAccountWorkerException, SystemException {
		AccountWorker accountWorker = fetchByAEI_NotN_Last(accountEntryId,
				notifications, orderByComparator);

		if (accountWorker != null) {
			return accountWorker;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(", notifications=");
		msg.append(notifications);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountWorkerException(msg.toString());
	}

	/**
	 * Returns the last account worker in the ordered set where accountEntryId = &#63; and notifications &ne; &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param notifications the notifications
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account worker, or <code>null</code> if a matching account worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountWorker fetchByAEI_NotN_Last(long accountEntryId,
		int notifications, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByAEI_NotN(accountEntryId, notifications);

		List<AccountWorker> list = findByAEI_NotN(accountEntryId,
				notifications, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the account workers before and after the current account worker in the ordered set where accountEntryId = &#63; and notifications &ne; &#63;.
	 *
	 * @param accountWorkerId the primary key of the current account worker
	 * @param accountEntryId the account entry ID
	 * @param notifications the notifications
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account worker
	 * @throws com.liferay.osb.NoSuchAccountWorkerException if a account worker with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountWorker[] findByAEI_NotN_PrevAndNext(long accountWorkerId,
		long accountEntryId, int notifications,
		OrderByComparator orderByComparator)
		throws NoSuchAccountWorkerException, SystemException {
		AccountWorker accountWorker = findByPrimaryKey(accountWorkerId);

		Session session = null;

		try {
			session = openSession();

			AccountWorker[] array = new AccountWorkerImpl[3];

			array[0] = getByAEI_NotN_PrevAndNext(session, accountWorker,
					accountEntryId, notifications, orderByComparator, true);

			array[1] = accountWorker;

			array[2] = getByAEI_NotN_PrevAndNext(session, accountWorker,
					accountEntryId, notifications, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AccountWorker getByAEI_NotN_PrevAndNext(Session session,
		AccountWorker accountWorker, long accountEntryId, int notifications,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ACCOUNTWORKER_WHERE);

		query.append(_FINDER_COLUMN_AEI_NOTN_ACCOUNTENTRYID_2);

		query.append(_FINDER_COLUMN_AEI_NOTN_NOTIFICATIONS_2);

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

		qPos.add(notifications);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(accountWorker);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AccountWorker> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the account workers.
	 *
	 * @return the account workers
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountWorker> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account workers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of account workers
	 * @param end the upper bound of the range of account workers (not inclusive)
	 * @return the range of account workers
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountWorker> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the account workers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of account workers
	 * @param end the upper bound of the range of account workers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of account workers
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountWorker> findAll(int start, int end,
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

		List<AccountWorker> list = (List<AccountWorker>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_ACCOUNTWORKER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ACCOUNTWORKER;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<AccountWorker>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<AccountWorker>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Removes all the account workers where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUserId(long userId) throws SystemException {
		for (AccountWorker accountWorker : findByUserId(userId)) {
			remove(accountWorker);
		}
	}

	/**
	 * Removes all the account workers where accountEntryId = &#63; from the database.
	 *
	 * @param accountEntryId the account entry ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByAccountEntryId(long accountEntryId)
		throws SystemException {
		for (AccountWorker accountWorker : findByAccountEntryId(accountEntryId)) {
			remove(accountWorker);
		}
	}

	/**
	 * Removes the account worker where userId = &#63; and accountEntryId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @return the account worker that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public AccountWorker removeByU_AEI(long userId, long accountEntryId)
		throws NoSuchAccountWorkerException, SystemException {
		AccountWorker accountWorker = findByU_AEI(userId, accountEntryId);

		return remove(accountWorker);
	}

	/**
	 * Removes all the account workers where userId = &#63; and role = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param role the role
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByU_R(long userId, int role) throws SystemException {
		for (AccountWorker accountWorker : findByU_R(userId, role)) {
			remove(accountWorker);
		}
	}

	/**
	 * Removes all the account workers where accountEntryId = &#63; and role = &#63; from the database.
	 *
	 * @param accountEntryId the account entry ID
	 * @param role the role
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByAEI_R(long accountEntryId, int role)
		throws SystemException {
		for (AccountWorker accountWorker : findByAEI_R(accountEntryId, role)) {
			remove(accountWorker);
		}
	}

	/**
	 * Removes all the account workers where accountEntryId = &#63; and notifications &ne; &#63; from the database.
	 *
	 * @param accountEntryId the account entry ID
	 * @param notifications the notifications
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByAEI_NotN(long accountEntryId, int notifications)
		throws SystemException {
		for (AccountWorker accountWorker : findByAEI_NotN(accountEntryId,
				notifications)) {
			remove(accountWorker);
		}
	}

	/**
	 * Removes all the account workers from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (AccountWorker accountWorker : findAll()) {
			remove(accountWorker);
		}
	}

	/**
	 * Returns the number of account workers where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching account workers
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUserId(long userId) throws SystemException {
		Object[] finderArgs = new Object[] { userId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_USERID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ACCOUNTWORKER_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_USERID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of account workers where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @return the number of matching account workers
	 * @throws SystemException if a system exception occurred
	 */
	public int countByAccountEntryId(long accountEntryId)
		throws SystemException {
		Object[] finderArgs = new Object[] { accountEntryId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_ACCOUNTENTRYID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ACCOUNTWORKER_WHERE);

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
	 * Returns the number of account workers where userId = &#63; and accountEntryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @return the number of matching account workers
	 * @throws SystemException if a system exception occurred
	 */
	public int countByU_AEI(long userId, long accountEntryId)
		throws SystemException {
		Object[] finderArgs = new Object[] { userId, accountEntryId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_U_AEI,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ACCOUNTWORKER_WHERE);

			query.append(_FINDER_COLUMN_U_AEI_USERID_2);

			query.append(_FINDER_COLUMN_U_AEI_ACCOUNTENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_U_AEI,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of account workers where userId = &#63; and role = &#63;.
	 *
	 * @param userId the user ID
	 * @param role the role
	 * @return the number of matching account workers
	 * @throws SystemException if a system exception occurred
	 */
	public int countByU_R(long userId, int role) throws SystemException {
		Object[] finderArgs = new Object[] { userId, role };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_U_R,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ACCOUNTWORKER_WHERE);

			query.append(_FINDER_COLUMN_U_R_USERID_2);

			query.append(_FINDER_COLUMN_U_R_ROLE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(role);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_U_R, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of account workers where accountEntryId = &#63; and role = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param role the role
	 * @return the number of matching account workers
	 * @throws SystemException if a system exception occurred
	 */
	public int countByAEI_R(long accountEntryId, int role)
		throws SystemException {
		Object[] finderArgs = new Object[] { accountEntryId, role };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_AEI_R,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ACCOUNTWORKER_WHERE);

			query.append(_FINDER_COLUMN_AEI_R_ACCOUNTENTRYID_2);

			query.append(_FINDER_COLUMN_AEI_R_ROLE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEntryId);

				qPos.add(role);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_AEI_R,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of account workers where accountEntryId = &#63; and notifications &ne; &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param notifications the notifications
	 * @return the number of matching account workers
	 * @throws SystemException if a system exception occurred
	 */
	public int countByAEI_NotN(long accountEntryId, int notifications)
		throws SystemException {
		Object[] finderArgs = new Object[] { accountEntryId, notifications };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_AEI_NOTN,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ACCOUNTWORKER_WHERE);

			query.append(_FINDER_COLUMN_AEI_NOTN_ACCOUNTENTRYID_2);

			query.append(_FINDER_COLUMN_AEI_NOTN_NOTIFICATIONS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEntryId);

				qPos.add(notifications);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_AEI_NOTN,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of account workers.
	 *
	 * @return the number of account workers
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ACCOUNTWORKER);

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
	 * Initializes the account worker persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.osb.model.AccountWorker")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<AccountWorker>> listenersList = new ArrayList<ModelListener<AccountWorker>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<AccountWorker>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(AccountWorkerImpl.class.getName());
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
	private static final String _SQL_SELECT_ACCOUNTWORKER = "SELECT accountWorker FROM AccountWorker accountWorker";
	private static final String _SQL_SELECT_ACCOUNTWORKER_WHERE = "SELECT accountWorker FROM AccountWorker accountWorker WHERE ";
	private static final String _SQL_COUNT_ACCOUNTWORKER = "SELECT COUNT(accountWorker) FROM AccountWorker accountWorker";
	private static final String _SQL_COUNT_ACCOUNTWORKER_WHERE = "SELECT COUNT(accountWorker) FROM AccountWorker accountWorker WHERE ";
	private static final String _FINDER_COLUMN_USERID_USERID_2 = "accountWorker.userId = ?";
	private static final String _FINDER_COLUMN_ACCOUNTENTRYID_ACCOUNTENTRYID_2 = "accountWorker.accountEntryId = ?";
	private static final String _FINDER_COLUMN_U_AEI_USERID_2 = "accountWorker.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_AEI_ACCOUNTENTRYID_2 = "accountWorker.accountEntryId = ?";
	private static final String _FINDER_COLUMN_U_R_USERID_2 = "accountWorker.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_R_ROLE_2 = "accountWorker.role = ?";
	private static final String _FINDER_COLUMN_AEI_R_ACCOUNTENTRYID_2 = "accountWorker.accountEntryId = ? AND ";
	private static final String _FINDER_COLUMN_AEI_R_ROLE_2 = "accountWorker.role = ?";
	private static final String _FINDER_COLUMN_AEI_NOTN_ACCOUNTENTRYID_2 = "accountWorker.accountEntryId = ? AND ";
	private static final String _FINDER_COLUMN_AEI_NOTN_NOTIFICATIONS_2 = "accountWorker.notifications != ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "accountWorker.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AccountWorker exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No AccountWorker exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(AccountWorkerPersistenceImpl.class);
	private static AccountWorker _nullAccountWorker = new AccountWorkerImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<AccountWorker> toCacheModel() {
				return _nullAccountWorkerCacheModel;
			}
		};

	private static CacheModel<AccountWorker> _nullAccountWorkerCacheModel = new CacheModel<AccountWorker>() {
			public AccountWorker toEntityModel() {
				return _nullAccountWorker;
			}
		};
}