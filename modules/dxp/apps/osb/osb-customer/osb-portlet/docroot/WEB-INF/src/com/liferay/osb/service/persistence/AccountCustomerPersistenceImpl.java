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

import com.liferay.osb.NoSuchAccountCustomerException;
import com.liferay.osb.model.AccountCustomer;
import com.liferay.osb.model.impl.AccountCustomerImpl;
import com.liferay.osb.model.impl.AccountCustomerModelImpl;

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
import com.liferay.portal.kernel.util.ArrayUtil;
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
import com.liferay.portal.service.persistence.OrganizationPersistence;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the account customer service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountCustomerPersistence
 * @see AccountCustomerUtil
 * @generated
 */
public class AccountCustomerPersistenceImpl extends BasePersistenceImpl<AccountCustomer>
	implements AccountCustomerPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AccountCustomerUtil} to access the account customer persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AccountCustomerImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID = new FinderPath(AccountCustomerModelImpl.ENTITY_CACHE_ENABLED,
			AccountCustomerModelImpl.FINDER_CACHE_ENABLED,
			AccountCustomerImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUserId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID =
		new FinderPath(AccountCustomerModelImpl.ENTITY_CACHE_ENABLED,
			AccountCustomerModelImpl.FINDER_CACHE_ENABLED,
			AccountCustomerImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUserId",
			new String[] { Long.class.getName() },
			AccountCustomerModelImpl.USERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(AccountCustomerModelImpl.ENTITY_CACHE_ENABLED,
			AccountCustomerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ACCOUNTENTRYID =
		new FinderPath(AccountCustomerModelImpl.ENTITY_CACHE_ENABLED,
			AccountCustomerModelImpl.FINDER_CACHE_ENABLED,
			AccountCustomerImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByAccountEntryId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID =
		new FinderPath(AccountCustomerModelImpl.ENTITY_CACHE_ENABLED,
			AccountCustomerModelImpl.FINDER_CACHE_ENABLED,
			AccountCustomerImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAccountEntryId",
			new String[] { Long.class.getName() },
			AccountCustomerModelImpl.ACCOUNTENTRYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ACCOUNTENTRYID = new FinderPath(AccountCustomerModelImpl.ENTITY_CACHE_ENABLED,
			AccountCustomerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAccountEntryId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_U_AEI = new FinderPath(AccountCustomerModelImpl.ENTITY_CACHE_ENABLED,
			AccountCustomerModelImpl.FINDER_CACHE_ENABLED,
			AccountCustomerImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByU_AEI",
			new String[] { Long.class.getName(), Long.class.getName() },
			AccountCustomerModelImpl.USERID_COLUMN_BITMASK |
			AccountCustomerModelImpl.ACCOUNTENTRYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_U_AEI = new FinderPath(AccountCustomerModelImpl.ENTITY_CACHE_ENABLED,
			AccountCustomerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_AEI",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_U_R = new FinderPath(AccountCustomerModelImpl.ENTITY_CACHE_ENABLED,
			AccountCustomerModelImpl.FINDER_CACHE_ENABLED,
			AccountCustomerImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByU_R",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_R = new FinderPath(AccountCustomerModelImpl.ENTITY_CACHE_ENABLED,
			AccountCustomerModelImpl.FINDER_CACHE_ENABLED,
			AccountCustomerImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByU_R",
			new String[] { Long.class.getName(), Integer.class.getName() },
			AccountCustomerModelImpl.USERID_COLUMN_BITMASK |
			AccountCustomerModelImpl.ROLE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_U_R = new FinderPath(AccountCustomerModelImpl.ENTITY_CACHE_ENABLED,
			AccountCustomerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_R",
			new String[] { Long.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_U_R = new FinderPath(AccountCustomerModelImpl.ENTITY_CACHE_ENABLED,
			AccountCustomerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByU_R",
			new String[] { Long.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_AEI_NOTR = new FinderPath(AccountCustomerModelImpl.ENTITY_CACHE_ENABLED,
			AccountCustomerModelImpl.FINDER_CACHE_ENABLED,
			AccountCustomerImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByAEI_NotR",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_AEI_NOTR =
		new FinderPath(AccountCustomerModelImpl.ENTITY_CACHE_ENABLED,
			AccountCustomerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByAEI_NotR",
			new String[] { Long.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_AEI_NOTN = new FinderPath(AccountCustomerModelImpl.ENTITY_CACHE_ENABLED,
			AccountCustomerModelImpl.FINDER_CACHE_ENABLED,
			AccountCustomerImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByAEI_NotN",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_AEI_NOTN =
		new FinderPath(AccountCustomerModelImpl.ENTITY_CACHE_ENABLED,
			AccountCustomerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByAEI_NotN",
			new String[] { Long.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AccountCustomerModelImpl.ENTITY_CACHE_ENABLED,
			AccountCustomerModelImpl.FINDER_CACHE_ENABLED,
			AccountCustomerImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AccountCustomerModelImpl.ENTITY_CACHE_ENABLED,
			AccountCustomerModelImpl.FINDER_CACHE_ENABLED,
			AccountCustomerImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AccountCustomerModelImpl.ENTITY_CACHE_ENABLED,
			AccountCustomerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the account customer in the entity cache if it is enabled.
	 *
	 * @param accountCustomer the account customer
	 */
	public void cacheResult(AccountCustomer accountCustomer) {
		EntityCacheUtil.putResult(AccountCustomerModelImpl.ENTITY_CACHE_ENABLED,
			AccountCustomerImpl.class, accountCustomer.getPrimaryKey(),
			accountCustomer);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_AEI,
			new Object[] {
				Long.valueOf(accountCustomer.getUserId()),
				Long.valueOf(accountCustomer.getAccountEntryId())
			}, accountCustomer);

		accountCustomer.resetOriginalValues();
	}

	/**
	 * Caches the account customers in the entity cache if it is enabled.
	 *
	 * @param accountCustomers the account customers
	 */
	public void cacheResult(List<AccountCustomer> accountCustomers) {
		for (AccountCustomer accountCustomer : accountCustomers) {
			if (EntityCacheUtil.getResult(
						AccountCustomerModelImpl.ENTITY_CACHE_ENABLED,
						AccountCustomerImpl.class,
						accountCustomer.getPrimaryKey()) == null) {
				cacheResult(accountCustomer);
			}
			else {
				accountCustomer.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all account customers.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(AccountCustomerImpl.class.getName());
		}

		EntityCacheUtil.clearCache(AccountCustomerImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the account customer.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AccountCustomer accountCustomer) {
		EntityCacheUtil.removeResult(AccountCustomerModelImpl.ENTITY_CACHE_ENABLED,
			AccountCustomerImpl.class, accountCustomer.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(accountCustomer);
	}

	@Override
	public void clearCache(List<AccountCustomer> accountCustomers) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AccountCustomer accountCustomer : accountCustomers) {
			EntityCacheUtil.removeResult(AccountCustomerModelImpl.ENTITY_CACHE_ENABLED,
				AccountCustomerImpl.class, accountCustomer.getPrimaryKey());

			clearUniqueFindersCache(accountCustomer);
		}
	}

	protected void cacheUniqueFindersCache(AccountCustomer accountCustomer) {
		if (accountCustomer.isNew()) {
			Object[] args = new Object[] {
					Long.valueOf(accountCustomer.getUserId()),
					Long.valueOf(accountCustomer.getAccountEntryId())
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_U_AEI, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_AEI, args,
				accountCustomer);
		}
		else {
			AccountCustomerModelImpl accountCustomerModelImpl = (AccountCustomerModelImpl)accountCustomer;

			if ((accountCustomerModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_U_AEI.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(accountCustomer.getUserId()),
						Long.valueOf(accountCustomer.getAccountEntryId())
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_U_AEI, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_AEI, args,
					accountCustomer);
			}
		}
	}

	protected void clearUniqueFindersCache(AccountCustomer accountCustomer) {
		AccountCustomerModelImpl accountCustomerModelImpl = (AccountCustomerModelImpl)accountCustomer;

		Object[] args = new Object[] {
				Long.valueOf(accountCustomer.getUserId()),
				Long.valueOf(accountCustomer.getAccountEntryId())
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_U_AEI, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_U_AEI, args);

		if ((accountCustomerModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_U_AEI.getColumnBitmask()) != 0) {
			args = new Object[] {
					Long.valueOf(accountCustomerModelImpl.getOriginalUserId()),
					Long.valueOf(accountCustomerModelImpl.getOriginalAccountEntryId())
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_U_AEI, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_U_AEI, args);
		}
	}

	/**
	 * Creates a new account customer with the primary key. Does not add the account customer to the database.
	 *
	 * @param accountCustomerId the primary key for the new account customer
	 * @return the new account customer
	 */
	public AccountCustomer create(long accountCustomerId) {
		AccountCustomer accountCustomer = new AccountCustomerImpl();

		accountCustomer.setNew(true);
		accountCustomer.setPrimaryKey(accountCustomerId);

		return accountCustomer;
	}

	/**
	 * Removes the account customer with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param accountCustomerId the primary key of the account customer
	 * @return the account customer that was removed
	 * @throws com.liferay.osb.NoSuchAccountCustomerException if a account customer with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountCustomer remove(long accountCustomerId)
		throws NoSuchAccountCustomerException, SystemException {
		return remove(Long.valueOf(accountCustomerId));
	}

	/**
	 * Removes the account customer with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the account customer
	 * @return the account customer that was removed
	 * @throws com.liferay.osb.NoSuchAccountCustomerException if a account customer with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AccountCustomer remove(Serializable primaryKey)
		throws NoSuchAccountCustomerException, SystemException {
		Session session = null;

		try {
			session = openSession();

			AccountCustomer accountCustomer = (AccountCustomer)session.get(AccountCustomerImpl.class,
					primaryKey);

			if (accountCustomer == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAccountCustomerException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(accountCustomer);
		}
		catch (NoSuchAccountCustomerException nsee) {
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
	protected AccountCustomer removeImpl(AccountCustomer accountCustomer)
		throws SystemException {
		accountCustomer = toUnwrappedModel(accountCustomer);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, accountCustomer);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(accountCustomer);

		return accountCustomer;
	}

	@Override
	public AccountCustomer updateImpl(
		com.liferay.osb.model.AccountCustomer accountCustomer, boolean merge)
		throws SystemException {
		accountCustomer = toUnwrappedModel(accountCustomer);

		boolean isNew = accountCustomer.isNew();

		AccountCustomerModelImpl accountCustomerModelImpl = (AccountCustomerModelImpl)accountCustomer;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, accountCustomer, merge);

			accountCustomer.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !AccountCustomerModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((accountCustomerModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(accountCustomerModelImpl.getOriginalUserId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);

				args = new Object[] {
						Long.valueOf(accountCustomerModelImpl.getUserId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);
			}

			if ((accountCustomerModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(accountCustomerModelImpl.getOriginalAccountEntryId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTENTRYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID,
					args);

				args = new Object[] {
						Long.valueOf(accountCustomerModelImpl.getAccountEntryId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTENTRYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID,
					args);
			}

			if ((accountCustomerModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_R.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(accountCustomerModelImpl.getOriginalUserId()),
						Integer.valueOf(accountCustomerModelImpl.getOriginalRole())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_U_R, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_R,
					args);

				args = new Object[] {
						Long.valueOf(accountCustomerModelImpl.getUserId()),
						Integer.valueOf(accountCustomerModelImpl.getRole())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_U_R, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_R,
					args);
			}
		}

		EntityCacheUtil.putResult(AccountCustomerModelImpl.ENTITY_CACHE_ENABLED,
			AccountCustomerImpl.class, accountCustomer.getPrimaryKey(),
			accountCustomer);

		clearUniqueFindersCache(accountCustomer);
		cacheUniqueFindersCache(accountCustomer);

		return accountCustomer;
	}

	protected AccountCustomer toUnwrappedModel(AccountCustomer accountCustomer) {
		if (accountCustomer instanceof AccountCustomerImpl) {
			return accountCustomer;
		}

		AccountCustomerImpl accountCustomerImpl = new AccountCustomerImpl();

		accountCustomerImpl.setNew(accountCustomer.isNew());
		accountCustomerImpl.setPrimaryKey(accountCustomer.getPrimaryKey());

		accountCustomerImpl.setAccountCustomerId(accountCustomer.getAccountCustomerId());
		accountCustomerImpl.setUserId(accountCustomer.getUserId());
		accountCustomerImpl.setAccountEntryId(accountCustomer.getAccountEntryId());
		accountCustomerImpl.setRole(accountCustomer.getRole());
		accountCustomerImpl.setNotifications(accountCustomer.getNotifications());

		return accountCustomerImpl;
	}

	/**
	 * Returns the account customer with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the account customer
	 * @return the account customer
	 * @throws com.liferay.portal.NoSuchModelException if a account customer with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AccountCustomer findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the account customer with the primary key or throws a {@link com.liferay.osb.NoSuchAccountCustomerException} if it could not be found.
	 *
	 * @param accountCustomerId the primary key of the account customer
	 * @return the account customer
	 * @throws com.liferay.osb.NoSuchAccountCustomerException if a account customer with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountCustomer findByPrimaryKey(long accountCustomerId)
		throws NoSuchAccountCustomerException, SystemException {
		AccountCustomer accountCustomer = fetchByPrimaryKey(accountCustomerId);

		if (accountCustomer == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + accountCustomerId);
			}

			throw new NoSuchAccountCustomerException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				accountCustomerId);
		}

		return accountCustomer;
	}

	/**
	 * Returns the account customer with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the account customer
	 * @return the account customer, or <code>null</code> if a account customer with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AccountCustomer fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the account customer with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param accountCustomerId the primary key of the account customer
	 * @return the account customer, or <code>null</code> if a account customer with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountCustomer fetchByPrimaryKey(long accountCustomerId)
		throws SystemException {
		AccountCustomer accountCustomer = (AccountCustomer)EntityCacheUtil.getResult(AccountCustomerModelImpl.ENTITY_CACHE_ENABLED,
				AccountCustomerImpl.class, accountCustomerId);

		if (accountCustomer == _nullAccountCustomer) {
			return null;
		}

		if (accountCustomer == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				accountCustomer = (AccountCustomer)session.get(AccountCustomerImpl.class,
						Long.valueOf(accountCustomerId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (accountCustomer != null) {
					cacheResult(accountCustomer);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(AccountCustomerModelImpl.ENTITY_CACHE_ENABLED,
						AccountCustomerImpl.class, accountCustomerId,
						_nullAccountCustomer);
				}

				closeSession(session);
			}
		}

		return accountCustomer;
	}

	/**
	 * Returns all the account customers where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching account customers
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountCustomer> findByUserId(long userId)
		throws SystemException {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account customers where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of account customers
	 * @param end the upper bound of the range of account customers (not inclusive)
	 * @return the range of matching account customers
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountCustomer> findByUserId(long userId, int start, int end)
		throws SystemException {
		return findByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the account customers where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of account customers
	 * @param end the upper bound of the range of account customers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account customers
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountCustomer> findByUserId(long userId, int start, int end,
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

		List<AccountCustomer> list = (List<AccountCustomer>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AccountCustomer accountCustomer : list) {
				if ((userId != accountCustomer.getUserId())) {
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

			query.append(_SQL_SELECT_ACCOUNTCUSTOMER_WHERE);

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

				list = (List<AccountCustomer>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first account customer in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account customer
	 * @throws com.liferay.osb.NoSuchAccountCustomerException if a matching account customer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountCustomer findByUserId_First(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchAccountCustomerException, SystemException {
		AccountCustomer accountCustomer = fetchByUserId_First(userId,
				orderByComparator);

		if (accountCustomer != null) {
			return accountCustomer;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountCustomerException(msg.toString());
	}

	/**
	 * Returns the first account customer in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account customer, or <code>null</code> if a matching account customer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountCustomer fetchByUserId_First(long userId,
		OrderByComparator orderByComparator) throws SystemException {
		List<AccountCustomer> list = findByUserId(userId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last account customer in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account customer
	 * @throws com.liferay.osb.NoSuchAccountCustomerException if a matching account customer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountCustomer findByUserId_Last(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchAccountCustomerException, SystemException {
		AccountCustomer accountCustomer = fetchByUserId_Last(userId,
				orderByComparator);

		if (accountCustomer != null) {
			return accountCustomer;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountCustomerException(msg.toString());
	}

	/**
	 * Returns the last account customer in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account customer, or <code>null</code> if a matching account customer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountCustomer fetchByUserId_Last(long userId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUserId(userId);

		List<AccountCustomer> list = findByUserId(userId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the account customers before and after the current account customer in the ordered set where userId = &#63;.
	 *
	 * @param accountCustomerId the primary key of the current account customer
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account customer
	 * @throws com.liferay.osb.NoSuchAccountCustomerException if a account customer with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountCustomer[] findByUserId_PrevAndNext(long accountCustomerId,
		long userId, OrderByComparator orderByComparator)
		throws NoSuchAccountCustomerException, SystemException {
		AccountCustomer accountCustomer = findByPrimaryKey(accountCustomerId);

		Session session = null;

		try {
			session = openSession();

			AccountCustomer[] array = new AccountCustomerImpl[3];

			array[0] = getByUserId_PrevAndNext(session, accountCustomer,
					userId, orderByComparator, true);

			array[1] = accountCustomer;

			array[2] = getByUserId_PrevAndNext(session, accountCustomer,
					userId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AccountCustomer getByUserId_PrevAndNext(Session session,
		AccountCustomer accountCustomer, long userId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ACCOUNTCUSTOMER_WHERE);

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
			Object[] values = orderByComparator.getOrderByConditionValues(accountCustomer);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AccountCustomer> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the account customers where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @return the matching account customers
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountCustomer> findByAccountEntryId(long accountEntryId)
		throws SystemException {
		return findByAccountEntryId(accountEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account customers where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of account customers
	 * @param end the upper bound of the range of account customers (not inclusive)
	 * @return the range of matching account customers
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountCustomer> findByAccountEntryId(long accountEntryId,
		int start, int end) throws SystemException {
		return findByAccountEntryId(accountEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the account customers where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of account customers
	 * @param end the upper bound of the range of account customers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account customers
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountCustomer> findByAccountEntryId(long accountEntryId,
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

		List<AccountCustomer> list = (List<AccountCustomer>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AccountCustomer accountCustomer : list) {
				if ((accountEntryId != accountCustomer.getAccountEntryId())) {
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

			query.append(_SQL_SELECT_ACCOUNTCUSTOMER_WHERE);

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

				list = (List<AccountCustomer>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first account customer in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account customer
	 * @throws com.liferay.osb.NoSuchAccountCustomerException if a matching account customer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountCustomer findByAccountEntryId_First(long accountEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchAccountCustomerException, SystemException {
		AccountCustomer accountCustomer = fetchByAccountEntryId_First(accountEntryId,
				orderByComparator);

		if (accountCustomer != null) {
			return accountCustomer;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountCustomerException(msg.toString());
	}

	/**
	 * Returns the first account customer in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account customer, or <code>null</code> if a matching account customer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountCustomer fetchByAccountEntryId_First(long accountEntryId,
		OrderByComparator orderByComparator) throws SystemException {
		List<AccountCustomer> list = findByAccountEntryId(accountEntryId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last account customer in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account customer
	 * @throws com.liferay.osb.NoSuchAccountCustomerException if a matching account customer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountCustomer findByAccountEntryId_Last(long accountEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchAccountCustomerException, SystemException {
		AccountCustomer accountCustomer = fetchByAccountEntryId_Last(accountEntryId,
				orderByComparator);

		if (accountCustomer != null) {
			return accountCustomer;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountCustomerException(msg.toString());
	}

	/**
	 * Returns the last account customer in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account customer, or <code>null</code> if a matching account customer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountCustomer fetchByAccountEntryId_Last(long accountEntryId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByAccountEntryId(accountEntryId);

		List<AccountCustomer> list = findByAccountEntryId(accountEntryId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the account customers before and after the current account customer in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountCustomerId the primary key of the current account customer
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account customer
	 * @throws com.liferay.osb.NoSuchAccountCustomerException if a account customer with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountCustomer[] findByAccountEntryId_PrevAndNext(
		long accountCustomerId, long accountEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchAccountCustomerException, SystemException {
		AccountCustomer accountCustomer = findByPrimaryKey(accountCustomerId);

		Session session = null;

		try {
			session = openSession();

			AccountCustomer[] array = new AccountCustomerImpl[3];

			array[0] = getByAccountEntryId_PrevAndNext(session,
					accountCustomer, accountEntryId, orderByComparator, true);

			array[1] = accountCustomer;

			array[2] = getByAccountEntryId_PrevAndNext(session,
					accountCustomer, accountEntryId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AccountCustomer getByAccountEntryId_PrevAndNext(Session session,
		AccountCustomer accountCustomer, long accountEntryId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ACCOUNTCUSTOMER_WHERE);

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
			Object[] values = orderByComparator.getOrderByConditionValues(accountCustomer);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AccountCustomer> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the account customer where userId = &#63; and accountEntryId = &#63; or throws a {@link com.liferay.osb.NoSuchAccountCustomerException} if it could not be found.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @return the matching account customer
	 * @throws com.liferay.osb.NoSuchAccountCustomerException if a matching account customer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountCustomer findByU_AEI(long userId, long accountEntryId)
		throws NoSuchAccountCustomerException, SystemException {
		AccountCustomer accountCustomer = fetchByU_AEI(userId, accountEntryId);

		if (accountCustomer == null) {
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

			throw new NoSuchAccountCustomerException(msg.toString());
		}

		return accountCustomer;
	}

	/**
	 * Returns the account customer where userId = &#63; and accountEntryId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @return the matching account customer, or <code>null</code> if a matching account customer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountCustomer fetchByU_AEI(long userId, long accountEntryId)
		throws SystemException {
		return fetchByU_AEI(userId, accountEntryId, true);
	}

	/**
	 * Returns the account customer where userId = &#63; and accountEntryId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching account customer, or <code>null</code> if a matching account customer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountCustomer fetchByU_AEI(long userId, long accountEntryId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { userId, accountEntryId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_U_AEI,
					finderArgs, this);
		}

		if (result instanceof AccountCustomer) {
			AccountCustomer accountCustomer = (AccountCustomer)result;

			if ((userId != accountCustomer.getUserId()) ||
					(accountEntryId != accountCustomer.getAccountEntryId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_ACCOUNTCUSTOMER_WHERE);

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

				List<AccountCustomer> list = q.list();

				result = list;

				AccountCustomer accountCustomer = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_AEI,
						finderArgs, list);
				}
				else {
					accountCustomer = list.get(0);

					cacheResult(accountCustomer);

					if ((accountCustomer.getUserId() != userId) ||
							(accountCustomer.getAccountEntryId() != accountEntryId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_AEI,
							finderArgs, accountCustomer);
					}
				}

				return accountCustomer;
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
				return (AccountCustomer)result;
			}
		}
	}

	/**
	 * Returns all the account customers where userId = &#63; and role = &#63;.
	 *
	 * @param userId the user ID
	 * @param role the role
	 * @return the matching account customers
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountCustomer> findByU_R(long userId, int role)
		throws SystemException {
		return findByU_R(userId, role, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the account customers where userId = &#63; and role = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param role the role
	 * @param start the lower bound of the range of account customers
	 * @param end the upper bound of the range of account customers (not inclusive)
	 * @return the range of matching account customers
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountCustomer> findByU_R(long userId, int role, int start,
		int end) throws SystemException {
		return findByU_R(userId, role, start, end, null);
	}

	/**
	 * Returns an ordered range of all the account customers where userId = &#63; and role = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param role the role
	 * @param start the lower bound of the range of account customers
	 * @param end the upper bound of the range of account customers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account customers
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountCustomer> findByU_R(long userId, int role, int start,
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

		List<AccountCustomer> list = (List<AccountCustomer>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AccountCustomer accountCustomer : list) {
				if ((userId != accountCustomer.getUserId()) ||
						(role != accountCustomer.getRole())) {
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

			query.append(_SQL_SELECT_ACCOUNTCUSTOMER_WHERE);

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

				list = (List<AccountCustomer>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first account customer in the ordered set where userId = &#63; and role = &#63;.
	 *
	 * @param userId the user ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account customer
	 * @throws com.liferay.osb.NoSuchAccountCustomerException if a matching account customer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountCustomer findByU_R_First(long userId, int role,
		OrderByComparator orderByComparator)
		throws NoSuchAccountCustomerException, SystemException {
		AccountCustomer accountCustomer = fetchByU_R_First(userId, role,
				orderByComparator);

		if (accountCustomer != null) {
			return accountCustomer;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", role=");
		msg.append(role);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountCustomerException(msg.toString());
	}

	/**
	 * Returns the first account customer in the ordered set where userId = &#63; and role = &#63;.
	 *
	 * @param userId the user ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account customer, or <code>null</code> if a matching account customer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountCustomer fetchByU_R_First(long userId, int role,
		OrderByComparator orderByComparator) throws SystemException {
		List<AccountCustomer> list = findByU_R(userId, role, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last account customer in the ordered set where userId = &#63; and role = &#63;.
	 *
	 * @param userId the user ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account customer
	 * @throws com.liferay.osb.NoSuchAccountCustomerException if a matching account customer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountCustomer findByU_R_Last(long userId, int role,
		OrderByComparator orderByComparator)
		throws NoSuchAccountCustomerException, SystemException {
		AccountCustomer accountCustomer = fetchByU_R_Last(userId, role,
				orderByComparator);

		if (accountCustomer != null) {
			return accountCustomer;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", role=");
		msg.append(role);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountCustomerException(msg.toString());
	}

	/**
	 * Returns the last account customer in the ordered set where userId = &#63; and role = &#63;.
	 *
	 * @param userId the user ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account customer, or <code>null</code> if a matching account customer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountCustomer fetchByU_R_Last(long userId, int role,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByU_R(userId, role);

		List<AccountCustomer> list = findByU_R(userId, role, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the account customers before and after the current account customer in the ordered set where userId = &#63; and role = &#63;.
	 *
	 * @param accountCustomerId the primary key of the current account customer
	 * @param userId the user ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account customer
	 * @throws com.liferay.osb.NoSuchAccountCustomerException if a account customer with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountCustomer[] findByU_R_PrevAndNext(long accountCustomerId,
		long userId, int role, OrderByComparator orderByComparator)
		throws NoSuchAccountCustomerException, SystemException {
		AccountCustomer accountCustomer = findByPrimaryKey(accountCustomerId);

		Session session = null;

		try {
			session = openSession();

			AccountCustomer[] array = new AccountCustomerImpl[3];

			array[0] = getByU_R_PrevAndNext(session, accountCustomer, userId,
					role, orderByComparator, true);

			array[1] = accountCustomer;

			array[2] = getByU_R_PrevAndNext(session, accountCustomer, userId,
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

	protected AccountCustomer getByU_R_PrevAndNext(Session session,
		AccountCustomer accountCustomer, long userId, int role,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ACCOUNTCUSTOMER_WHERE);

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
			Object[] values = orderByComparator.getOrderByConditionValues(accountCustomer);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AccountCustomer> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the account customers where userId = &#63; and role = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param roles the roles
	 * @return the matching account customers
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountCustomer> findByU_R(long userId, int[] roles)
		throws SystemException {
		return findByU_R(userId, roles, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the account customers where userId = &#63; and role = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param roles the roles
	 * @param start the lower bound of the range of account customers
	 * @param end the upper bound of the range of account customers (not inclusive)
	 * @return the range of matching account customers
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountCustomer> findByU_R(long userId, int[] roles, int start,
		int end) throws SystemException {
		return findByU_R(userId, roles, start, end, null);
	}

	/**
	 * Returns an ordered range of all the account customers where userId = &#63; and role = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param roles the roles
	 * @param start the lower bound of the range of account customers
	 * @param end the upper bound of the range of account customers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account customers
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountCustomer> findByU_R(long userId, int[] roles, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_U_R;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderArgs = new Object[] { userId, StringUtil.merge(roles) };
		}
		else {
			finderArgs = new Object[] {
					userId, StringUtil.merge(roles),
					
					start, end, orderByComparator
				};
		}

		List<AccountCustomer> list = (List<AccountCustomer>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AccountCustomer accountCustomer : list) {
				if ((userId != accountCustomer.getUserId()) ||
						!ArrayUtil.contains(roles, accountCustomer.getRole())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_ACCOUNTCUSTOMER_WHERE);

			boolean conjunctionable = false;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_U_R_USERID_5);

			conjunctionable = true;

			if ((roles == null) || (roles.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < roles.length; i++) {
					query.append(_FINDER_COLUMN_U_R_ROLE_5);

					if ((i + 1) < roles.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

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

				if (roles != null) {
					qPos.add(roles);
				}

				list = (List<AccountCustomer>)QueryUtil.list(q, getDialect(),
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
	 * Returns all the account customers where accountEntryId = &#63; and role &ne; &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param role the role
	 * @return the matching account customers
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountCustomer> findByAEI_NotR(long accountEntryId, int role)
		throws SystemException {
		return findByAEI_NotR(accountEntryId, role, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account customers where accountEntryId = &#63; and role &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param role the role
	 * @param start the lower bound of the range of account customers
	 * @param end the upper bound of the range of account customers (not inclusive)
	 * @return the range of matching account customers
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountCustomer> findByAEI_NotR(long accountEntryId, int role,
		int start, int end) throws SystemException {
		return findByAEI_NotR(accountEntryId, role, start, end, null);
	}

	/**
	 * Returns an ordered range of all the account customers where accountEntryId = &#63; and role &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param role the role
	 * @param start the lower bound of the range of account customers
	 * @param end the upper bound of the range of account customers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account customers
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountCustomer> findByAEI_NotR(long accountEntryId, int role,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_AEI_NOTR;
		finderArgs = new Object[] {
				accountEntryId, role,
				
				start, end, orderByComparator
			};

		List<AccountCustomer> list = (List<AccountCustomer>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AccountCustomer accountCustomer : list) {
				if ((accountEntryId != accountCustomer.getAccountEntryId()) ||
						(role != accountCustomer.getRole())) {
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

			query.append(_SQL_SELECT_ACCOUNTCUSTOMER_WHERE);

			query.append(_FINDER_COLUMN_AEI_NOTR_ACCOUNTENTRYID_2);

			query.append(_FINDER_COLUMN_AEI_NOTR_ROLE_2);

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

				list = (List<AccountCustomer>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first account customer in the ordered set where accountEntryId = &#63; and role &ne; &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account customer
	 * @throws com.liferay.osb.NoSuchAccountCustomerException if a matching account customer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountCustomer findByAEI_NotR_First(long accountEntryId, int role,
		OrderByComparator orderByComparator)
		throws NoSuchAccountCustomerException, SystemException {
		AccountCustomer accountCustomer = fetchByAEI_NotR_First(accountEntryId,
				role, orderByComparator);

		if (accountCustomer != null) {
			return accountCustomer;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(", role=");
		msg.append(role);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountCustomerException(msg.toString());
	}

	/**
	 * Returns the first account customer in the ordered set where accountEntryId = &#63; and role &ne; &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account customer, or <code>null</code> if a matching account customer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountCustomer fetchByAEI_NotR_First(long accountEntryId, int role,
		OrderByComparator orderByComparator) throws SystemException {
		List<AccountCustomer> list = findByAEI_NotR(accountEntryId, role, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last account customer in the ordered set where accountEntryId = &#63; and role &ne; &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account customer
	 * @throws com.liferay.osb.NoSuchAccountCustomerException if a matching account customer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountCustomer findByAEI_NotR_Last(long accountEntryId, int role,
		OrderByComparator orderByComparator)
		throws NoSuchAccountCustomerException, SystemException {
		AccountCustomer accountCustomer = fetchByAEI_NotR_Last(accountEntryId,
				role, orderByComparator);

		if (accountCustomer != null) {
			return accountCustomer;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(", role=");
		msg.append(role);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountCustomerException(msg.toString());
	}

	/**
	 * Returns the last account customer in the ordered set where accountEntryId = &#63; and role &ne; &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account customer, or <code>null</code> if a matching account customer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountCustomer fetchByAEI_NotR_Last(long accountEntryId, int role,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByAEI_NotR(accountEntryId, role);

		List<AccountCustomer> list = findByAEI_NotR(accountEntryId, role,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the account customers before and after the current account customer in the ordered set where accountEntryId = &#63; and role &ne; &#63;.
	 *
	 * @param accountCustomerId the primary key of the current account customer
	 * @param accountEntryId the account entry ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account customer
	 * @throws com.liferay.osb.NoSuchAccountCustomerException if a account customer with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountCustomer[] findByAEI_NotR_PrevAndNext(
		long accountCustomerId, long accountEntryId, int role,
		OrderByComparator orderByComparator)
		throws NoSuchAccountCustomerException, SystemException {
		AccountCustomer accountCustomer = findByPrimaryKey(accountCustomerId);

		Session session = null;

		try {
			session = openSession();

			AccountCustomer[] array = new AccountCustomerImpl[3];

			array[0] = getByAEI_NotR_PrevAndNext(session, accountCustomer,
					accountEntryId, role, orderByComparator, true);

			array[1] = accountCustomer;

			array[2] = getByAEI_NotR_PrevAndNext(session, accountCustomer,
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

	protected AccountCustomer getByAEI_NotR_PrevAndNext(Session session,
		AccountCustomer accountCustomer, long accountEntryId, int role,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ACCOUNTCUSTOMER_WHERE);

		query.append(_FINDER_COLUMN_AEI_NOTR_ACCOUNTENTRYID_2);

		query.append(_FINDER_COLUMN_AEI_NOTR_ROLE_2);

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
			Object[] values = orderByComparator.getOrderByConditionValues(accountCustomer);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AccountCustomer> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the account customers where accountEntryId = &#63; and notifications &ne; &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param notifications the notifications
	 * @return the matching account customers
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountCustomer> findByAEI_NotN(long accountEntryId,
		int notifications) throws SystemException {
		return findByAEI_NotN(accountEntryId, notifications, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account customers where accountEntryId = &#63; and notifications &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param notifications the notifications
	 * @param start the lower bound of the range of account customers
	 * @param end the upper bound of the range of account customers (not inclusive)
	 * @return the range of matching account customers
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountCustomer> findByAEI_NotN(long accountEntryId,
		int notifications, int start, int end) throws SystemException {
		return findByAEI_NotN(accountEntryId, notifications, start, end, null);
	}

	/**
	 * Returns an ordered range of all the account customers where accountEntryId = &#63; and notifications &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param notifications the notifications
	 * @param start the lower bound of the range of account customers
	 * @param end the upper bound of the range of account customers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account customers
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountCustomer> findByAEI_NotN(long accountEntryId,
		int notifications, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_AEI_NOTN;
		finderArgs = new Object[] {
				accountEntryId, notifications,
				
				start, end, orderByComparator
			};

		List<AccountCustomer> list = (List<AccountCustomer>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AccountCustomer accountCustomer : list) {
				if ((accountEntryId != accountCustomer.getAccountEntryId()) ||
						(notifications != accountCustomer.getNotifications())) {
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

			query.append(_SQL_SELECT_ACCOUNTCUSTOMER_WHERE);

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

				list = (List<AccountCustomer>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first account customer in the ordered set where accountEntryId = &#63; and notifications &ne; &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param notifications the notifications
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account customer
	 * @throws com.liferay.osb.NoSuchAccountCustomerException if a matching account customer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountCustomer findByAEI_NotN_First(long accountEntryId,
		int notifications, OrderByComparator orderByComparator)
		throws NoSuchAccountCustomerException, SystemException {
		AccountCustomer accountCustomer = fetchByAEI_NotN_First(accountEntryId,
				notifications, orderByComparator);

		if (accountCustomer != null) {
			return accountCustomer;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(", notifications=");
		msg.append(notifications);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountCustomerException(msg.toString());
	}

	/**
	 * Returns the first account customer in the ordered set where accountEntryId = &#63; and notifications &ne; &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param notifications the notifications
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account customer, or <code>null</code> if a matching account customer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountCustomer fetchByAEI_NotN_First(long accountEntryId,
		int notifications, OrderByComparator orderByComparator)
		throws SystemException {
		List<AccountCustomer> list = findByAEI_NotN(accountEntryId,
				notifications, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last account customer in the ordered set where accountEntryId = &#63; and notifications &ne; &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param notifications the notifications
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account customer
	 * @throws com.liferay.osb.NoSuchAccountCustomerException if a matching account customer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountCustomer findByAEI_NotN_Last(long accountEntryId,
		int notifications, OrderByComparator orderByComparator)
		throws NoSuchAccountCustomerException, SystemException {
		AccountCustomer accountCustomer = fetchByAEI_NotN_Last(accountEntryId,
				notifications, orderByComparator);

		if (accountCustomer != null) {
			return accountCustomer;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(", notifications=");
		msg.append(notifications);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountCustomerException(msg.toString());
	}

	/**
	 * Returns the last account customer in the ordered set where accountEntryId = &#63; and notifications &ne; &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param notifications the notifications
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account customer, or <code>null</code> if a matching account customer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountCustomer fetchByAEI_NotN_Last(long accountEntryId,
		int notifications, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByAEI_NotN(accountEntryId, notifications);

		List<AccountCustomer> list = findByAEI_NotN(accountEntryId,
				notifications, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the account customers before and after the current account customer in the ordered set where accountEntryId = &#63; and notifications &ne; &#63;.
	 *
	 * @param accountCustomerId the primary key of the current account customer
	 * @param accountEntryId the account entry ID
	 * @param notifications the notifications
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account customer
	 * @throws com.liferay.osb.NoSuchAccountCustomerException if a account customer with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountCustomer[] findByAEI_NotN_PrevAndNext(
		long accountCustomerId, long accountEntryId, int notifications,
		OrderByComparator orderByComparator)
		throws NoSuchAccountCustomerException, SystemException {
		AccountCustomer accountCustomer = findByPrimaryKey(accountCustomerId);

		Session session = null;

		try {
			session = openSession();

			AccountCustomer[] array = new AccountCustomerImpl[3];

			array[0] = getByAEI_NotN_PrevAndNext(session, accountCustomer,
					accountEntryId, notifications, orderByComparator, true);

			array[1] = accountCustomer;

			array[2] = getByAEI_NotN_PrevAndNext(session, accountCustomer,
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

	protected AccountCustomer getByAEI_NotN_PrevAndNext(Session session,
		AccountCustomer accountCustomer, long accountEntryId,
		int notifications, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ACCOUNTCUSTOMER_WHERE);

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
			Object[] values = orderByComparator.getOrderByConditionValues(accountCustomer);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AccountCustomer> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the account customers.
	 *
	 * @return the account customers
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountCustomer> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account customers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of account customers
	 * @param end the upper bound of the range of account customers (not inclusive)
	 * @return the range of account customers
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountCustomer> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the account customers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of account customers
	 * @param end the upper bound of the range of account customers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of account customers
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountCustomer> findAll(int start, int end,
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

		List<AccountCustomer> list = (List<AccountCustomer>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_ACCOUNTCUSTOMER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ACCOUNTCUSTOMER;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<AccountCustomer>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<AccountCustomer>)QueryUtil.list(q,
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
	 * Removes all the account customers where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUserId(long userId) throws SystemException {
		for (AccountCustomer accountCustomer : findByUserId(userId)) {
			remove(accountCustomer);
		}
	}

	/**
	 * Removes all the account customers where accountEntryId = &#63; from the database.
	 *
	 * @param accountEntryId the account entry ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByAccountEntryId(long accountEntryId)
		throws SystemException {
		for (AccountCustomer accountCustomer : findByAccountEntryId(
				accountEntryId)) {
			remove(accountCustomer);
		}
	}

	/**
	 * Removes the account customer where userId = &#63; and accountEntryId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @return the account customer that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public AccountCustomer removeByU_AEI(long userId, long accountEntryId)
		throws NoSuchAccountCustomerException, SystemException {
		AccountCustomer accountCustomer = findByU_AEI(userId, accountEntryId);

		return remove(accountCustomer);
	}

	/**
	 * Removes all the account customers where userId = &#63; and role = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param role the role
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByU_R(long userId, int role) throws SystemException {
		for (AccountCustomer accountCustomer : findByU_R(userId, role)) {
			remove(accountCustomer);
		}
	}

	/**
	 * Removes all the account customers where accountEntryId = &#63; and role &ne; &#63; from the database.
	 *
	 * @param accountEntryId the account entry ID
	 * @param role the role
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByAEI_NotR(long accountEntryId, int role)
		throws SystemException {
		for (AccountCustomer accountCustomer : findByAEI_NotR(accountEntryId,
				role)) {
			remove(accountCustomer);
		}
	}

	/**
	 * Removes all the account customers where accountEntryId = &#63; and notifications &ne; &#63; from the database.
	 *
	 * @param accountEntryId the account entry ID
	 * @param notifications the notifications
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByAEI_NotN(long accountEntryId, int notifications)
		throws SystemException {
		for (AccountCustomer accountCustomer : findByAEI_NotN(accountEntryId,
				notifications)) {
			remove(accountCustomer);
		}
	}

	/**
	 * Removes all the account customers from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (AccountCustomer accountCustomer : findAll()) {
			remove(accountCustomer);
		}
	}

	/**
	 * Returns the number of account customers where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching account customers
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUserId(long userId) throws SystemException {
		Object[] finderArgs = new Object[] { userId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_USERID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ACCOUNTCUSTOMER_WHERE);

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
	 * Returns the number of account customers where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @return the number of matching account customers
	 * @throws SystemException if a system exception occurred
	 */
	public int countByAccountEntryId(long accountEntryId)
		throws SystemException {
		Object[] finderArgs = new Object[] { accountEntryId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_ACCOUNTENTRYID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ACCOUNTCUSTOMER_WHERE);

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
	 * Returns the number of account customers where userId = &#63; and accountEntryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @return the number of matching account customers
	 * @throws SystemException if a system exception occurred
	 */
	public int countByU_AEI(long userId, long accountEntryId)
		throws SystemException {
		Object[] finderArgs = new Object[] { userId, accountEntryId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_U_AEI,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ACCOUNTCUSTOMER_WHERE);

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
	 * Returns the number of account customers where userId = &#63; and role = &#63;.
	 *
	 * @param userId the user ID
	 * @param role the role
	 * @return the number of matching account customers
	 * @throws SystemException if a system exception occurred
	 */
	public int countByU_R(long userId, int role) throws SystemException {
		Object[] finderArgs = new Object[] { userId, role };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_U_R,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ACCOUNTCUSTOMER_WHERE);

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
	 * Returns the number of account customers where userId = &#63; and role = any &#63;.
	 *
	 * @param userId the user ID
	 * @param roles the roles
	 * @return the number of matching account customers
	 * @throws SystemException if a system exception occurred
	 */
	public int countByU_R(long userId, int[] roles) throws SystemException {
		Object[] finderArgs = new Object[] { userId, StringUtil.merge(roles) };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_U_R,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_ACCOUNTCUSTOMER_WHERE);

			boolean conjunctionable = false;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_U_R_USERID_5);

			conjunctionable = true;

			if ((roles == null) || (roles.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < roles.length; i++) {
					query.append(_FINDER_COLUMN_U_R_ROLE_5);

					if ((i + 1) < roles.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (roles != null) {
					qPos.add(roles);
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

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_U_R,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of account customers where accountEntryId = &#63; and role &ne; &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param role the role
	 * @return the number of matching account customers
	 * @throws SystemException if a system exception occurred
	 */
	public int countByAEI_NotR(long accountEntryId, int role)
		throws SystemException {
		Object[] finderArgs = new Object[] { accountEntryId, role };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_AEI_NOTR,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ACCOUNTCUSTOMER_WHERE);

			query.append(_FINDER_COLUMN_AEI_NOTR_ACCOUNTENTRYID_2);

			query.append(_FINDER_COLUMN_AEI_NOTR_ROLE_2);

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

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_AEI_NOTR,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of account customers where accountEntryId = &#63; and notifications &ne; &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param notifications the notifications
	 * @return the number of matching account customers
	 * @throws SystemException if a system exception occurred
	 */
	public int countByAEI_NotN(long accountEntryId, int notifications)
		throws SystemException {
		Object[] finderArgs = new Object[] { accountEntryId, notifications };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_AEI_NOTN,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ACCOUNTCUSTOMER_WHERE);

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
	 * Returns the number of account customers.
	 *
	 * @return the number of account customers
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ACCOUNTCUSTOMER);

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
	 * Initializes the account customer persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.osb.model.AccountCustomer")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<AccountCustomer>> listenersList = new ArrayList<ModelListener<AccountCustomer>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<AccountCustomer>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(AccountCustomerImpl.class.getName());
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
	@BeanReference(type = OrganizationPersistence.class)
	protected OrganizationPersistence organizationPersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_ACCOUNTCUSTOMER = "SELECT accountCustomer FROM AccountCustomer accountCustomer";
	private static final String _SQL_SELECT_ACCOUNTCUSTOMER_WHERE = "SELECT accountCustomer FROM AccountCustomer accountCustomer WHERE ";
	private static final String _SQL_COUNT_ACCOUNTCUSTOMER = "SELECT COUNT(accountCustomer) FROM AccountCustomer accountCustomer";
	private static final String _SQL_COUNT_ACCOUNTCUSTOMER_WHERE = "SELECT COUNT(accountCustomer) FROM AccountCustomer accountCustomer WHERE ";
	private static final String _FINDER_COLUMN_USERID_USERID_2 = "accountCustomer.userId = ?";
	private static final String _FINDER_COLUMN_ACCOUNTENTRYID_ACCOUNTENTRYID_2 = "accountCustomer.accountEntryId = ?";
	private static final String _FINDER_COLUMN_U_AEI_USERID_2 = "accountCustomer.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_AEI_ACCOUNTENTRYID_2 = "accountCustomer.accountEntryId = ?";
	private static final String _FINDER_COLUMN_U_R_USERID_2 = "accountCustomer.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_R_USERID_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_U_R_USERID_2) + ")";
	private static final String _FINDER_COLUMN_U_R_ROLE_2 = "accountCustomer.role = ?";
	private static final String _FINDER_COLUMN_U_R_ROLE_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_U_R_ROLE_2) + ")";
	private static final String _FINDER_COLUMN_AEI_NOTR_ACCOUNTENTRYID_2 = "accountCustomer.accountEntryId = ? AND ";
	private static final String _FINDER_COLUMN_AEI_NOTR_ROLE_2 = "accountCustomer.role != ?";
	private static final String _FINDER_COLUMN_AEI_NOTN_ACCOUNTENTRYID_2 = "accountCustomer.accountEntryId = ? AND ";
	private static final String _FINDER_COLUMN_AEI_NOTN_NOTIFICATIONS_2 = "accountCustomer.notifications != ?";

	private static String _removeConjunction(String sql) {
		int pos = sql.indexOf(" AND ");

		if (pos != -1) {
			sql = sql.substring(0, pos);
		}

		return sql;
	}

	private static final String _ORDER_BY_ENTITY_ALIAS = "accountCustomer.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AccountCustomer exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No AccountCustomer exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(AccountCustomerPersistenceImpl.class);
	private static AccountCustomer _nullAccountCustomer = new AccountCustomerImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<AccountCustomer> toCacheModel() {
				return _nullAccountCustomerCacheModel;
			}
		};

	private static CacheModel<AccountCustomer> _nullAccountCustomerCacheModel = new CacheModel<AccountCustomer>() {
			public AccountCustomer toEntityModel() {
				return _nullAccountCustomer;
			}
		};
}