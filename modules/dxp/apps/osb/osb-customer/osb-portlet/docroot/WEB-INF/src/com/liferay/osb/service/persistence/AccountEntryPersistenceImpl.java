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

import com.liferay.osb.NoSuchAccountEntryException;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.impl.AccountEntryImpl;
import com.liferay.osb.model.impl.AccountEntryModelImpl;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.jdbc.MappingSqlQuery;
import com.liferay.portal.kernel.dao.jdbc.MappingSqlQueryFactoryUtil;
import com.liferay.portal.kernel.dao.jdbc.RowMapper;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
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
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.AddressPersistence;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ClassNamePersistence;
import com.liferay.portal.service.persistence.CountryPersistence;
import com.liferay.portal.service.persistence.ListTypePersistence;
import com.liferay.portal.service.persistence.OrganizationPersistence;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.WorkflowInstanceLinkPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the account entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountEntryPersistence
 * @see AccountEntryUtil
 * @generated
 */
public class AccountEntryPersistenceImpl extends BasePersistenceImpl<AccountEntry>
	implements AccountEntryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AccountEntryUtil} to access the account entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AccountEntryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_FETCH_BY_CORPPROJECTID = new FinderPath(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryModelImpl.FINDER_CACHE_ENABLED, AccountEntryImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByCorpProjectId",
			new String[] { Long.class.getName() },
			AccountEntryModelImpl.CORPPROJECTID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CORPPROJECTID = new FinderPath(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCorpProjectId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_NAME = new FinderPath(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryModelImpl.FINDER_CACHE_ENABLED, AccountEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByName",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAME = new FinderPath(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryModelImpl.FINDER_CACHE_ENABLED, AccountEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByName",
			new String[] { String.class.getName() },
			AccountEntryModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_NAME = new FinderPath(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByName",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_CODE = new FinderPath(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryModelImpl.FINDER_CACHE_ENABLED, AccountEntryImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByCode",
			new String[] { String.class.getName() },
			AccountEntryModelImpl.CODE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CODE = new FinderPath(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCode",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_REDIRECTACCOUNTENTRYID =
		new FinderPath(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryModelImpl.FINDER_CACHE_ENABLED, AccountEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByRedirectAccountEntryId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REDIRECTACCOUNTENTRYID =
		new FinderPath(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryModelImpl.FINDER_CACHE_ENABLED, AccountEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByRedirectAccountEntryId",
			new String[] { Long.class.getName() },
			AccountEntryModelImpl.REDIRECTACCOUNTENTRYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_REDIRECTACCOUNTENTRYID = new FinderPath(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByRedirectAccountEntryId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PARTNERENTRYID =
		new FinderPath(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryModelImpl.FINDER_CACHE_ENABLED, AccountEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByPartnerEntryId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARTNERENTRYID =
		new FinderPath(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryModelImpl.FINDER_CACHE_ENABLED, AccountEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByPartnerEntryId",
			new String[] { Long.class.getName() },
			AccountEntryModelImpl.PARTNERENTRYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PARTNERENTRYID = new FinderPath(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPartnerEntryId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_HIGHESTSUPPORTRESPONSEID =
		new FinderPath(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryModelImpl.FINDER_CACHE_ENABLED, AccountEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByHighestSupportResponseId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_HIGHESTSUPPORTRESPONSEID =
		new FinderPath(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryModelImpl.FINDER_CACHE_ENABLED, AccountEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByHighestSupportResponseId",
			new String[] { Long.class.getName() },
			AccountEntryModelImpl.HIGHESTSUPPORTRESPONSEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_HIGHESTSUPPORTRESPONSEID =
		new FinderPath(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByHighestSupportResponseId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_U_T = new FinderPath(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryModelImpl.FINDER_CACHE_ENABLED, AccountEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByU_T",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_T = new FinderPath(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryModelImpl.FINDER_CACHE_ENABLED, AccountEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByU_T",
			new String[] { Long.class.getName(), Integer.class.getName() },
			AccountEntryModelImpl.USERID_COLUMN_BITMASK |
			AccountEntryModelImpl.TYPE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_U_T = new FinderPath(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_T",
			new String[] { Long.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_RAEI_S = new FinderPath(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryModelImpl.FINDER_CACHE_ENABLED, AccountEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByRAEI_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RAEI_S =
		new FinderPath(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryModelImpl.FINDER_CACHE_ENABLED, AccountEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByRAEI_S",
			new String[] { Long.class.getName(), Integer.class.getName() },
			AccountEntryModelImpl.REDIRECTACCOUNTENTRYID_COLUMN_BITMASK |
			AccountEntryModelImpl.STATUS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_RAEI_S = new FinderPath(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByRAEI_S",
			new String[] { Long.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_RAEI_S = new FinderPath(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByRAEI_S",
			new String[] { Long.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_RAEI_NOTT_S =
		new FinderPath(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryModelImpl.FINDER_CACHE_ENABLED, AccountEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByRAEI_NotT_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_RAEI_NOTT_S =
		new FinderPath(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByRAEI_NotT_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_N_C_RAEI = new FinderPath(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryModelImpl.FINDER_CACHE_ENABLED, AccountEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByN_C_RAEI",
			new String[] {
				String.class.getName(), String.class.getName(),
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_N_C_RAEI =
		new FinderPath(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByN_C_RAEI",
			new String[] {
				String.class.getName(), String.class.getName(),
				Long.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryModelImpl.FINDER_CACHE_ENABLED, AccountEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryModelImpl.FINDER_CACHE_ENABLED, AccountEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the account entry in the entity cache if it is enabled.
	 *
	 * @param accountEntry the account entry
	 */
	public void cacheResult(AccountEntry accountEntry) {
		EntityCacheUtil.putResult(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryImpl.class, accountEntry.getPrimaryKey(), accountEntry);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CORPPROJECTID,
			new Object[] { Long.valueOf(accountEntry.getCorpProjectId()) },
			accountEntry);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CODE,
			new Object[] { accountEntry.getCode() }, accountEntry);

		accountEntry.resetOriginalValues();
	}

	/**
	 * Caches the account entries in the entity cache if it is enabled.
	 *
	 * @param accountEntries the account entries
	 */
	public void cacheResult(List<AccountEntry> accountEntries) {
		for (AccountEntry accountEntry : accountEntries) {
			if (EntityCacheUtil.getResult(
						AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
						AccountEntryImpl.class, accountEntry.getPrimaryKey()) == null) {
				cacheResult(accountEntry);
			}
			else {
				accountEntry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all account entries.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(AccountEntryImpl.class.getName());
		}

		EntityCacheUtil.clearCache(AccountEntryImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the account entry.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AccountEntry accountEntry) {
		EntityCacheUtil.removeResult(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryImpl.class, accountEntry.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(accountEntry);
	}

	@Override
	public void clearCache(List<AccountEntry> accountEntries) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AccountEntry accountEntry : accountEntries) {
			EntityCacheUtil.removeResult(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
				AccountEntryImpl.class, accountEntry.getPrimaryKey());

			clearUniqueFindersCache(accountEntry);
		}
	}

	protected void cacheUniqueFindersCache(AccountEntry accountEntry) {
		if (accountEntry.isNew()) {
			Object[] args = new Object[] {
					Long.valueOf(accountEntry.getCorpProjectId())
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_CORPPROJECTID, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CORPPROJECTID, args,
				accountEntry);

			args = new Object[] { accountEntry.getCode() };

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_CODE, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CODE, args,
				accountEntry);
		}
		else {
			AccountEntryModelImpl accountEntryModelImpl = (AccountEntryModelImpl)accountEntry;

			if ((accountEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_CORPPROJECTID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(accountEntry.getCorpProjectId())
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_CORPPROJECTID,
					args, Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CORPPROJECTID,
					args, accountEntry);
			}

			if ((accountEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_CODE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { accountEntry.getCode() };

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_CODE, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CODE, args,
					accountEntry);
			}
		}
	}

	protected void clearUniqueFindersCache(AccountEntry accountEntry) {
		AccountEntryModelImpl accountEntryModelImpl = (AccountEntryModelImpl)accountEntry;

		Object[] args = new Object[] {
				Long.valueOf(accountEntry.getCorpProjectId())
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CORPPROJECTID, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CORPPROJECTID, args);

		if ((accountEntryModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_CORPPROJECTID.getColumnBitmask()) != 0) {
			args = new Object[] {
					Long.valueOf(accountEntryModelImpl.getOriginalCorpProjectId())
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CORPPROJECTID,
				args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CORPPROJECTID,
				args);
		}

		args = new Object[] { accountEntry.getCode() };

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CODE, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CODE, args);

		if ((accountEntryModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_CODE.getColumnBitmask()) != 0) {
			args = new Object[] { accountEntryModelImpl.getOriginalCode() };

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CODE, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CODE, args);
		}
	}

	/**
	 * Creates a new account entry with the primary key. Does not add the account entry to the database.
	 *
	 * @param accountEntryId the primary key for the new account entry
	 * @return the new account entry
	 */
	public AccountEntry create(long accountEntryId) {
		AccountEntry accountEntry = new AccountEntryImpl();

		accountEntry.setNew(true);
		accountEntry.setPrimaryKey(accountEntryId);

		return accountEntry;
	}

	/**
	 * Removes the account entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param accountEntryId the primary key of the account entry
	 * @return the account entry that was removed
	 * @throws com.liferay.osb.NoSuchAccountEntryException if a account entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEntry remove(long accountEntryId)
		throws NoSuchAccountEntryException, SystemException {
		return remove(Long.valueOf(accountEntryId));
	}

	/**
	 * Removes the account entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the account entry
	 * @return the account entry that was removed
	 * @throws com.liferay.osb.NoSuchAccountEntryException if a account entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AccountEntry remove(Serializable primaryKey)
		throws NoSuchAccountEntryException, SystemException {
		Session session = null;

		try {
			session = openSession();

			AccountEntry accountEntry = (AccountEntry)session.get(AccountEntryImpl.class,
					primaryKey);

			if (accountEntry == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAccountEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(accountEntry);
		}
		catch (NoSuchAccountEntryException nsee) {
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
	protected AccountEntry removeImpl(AccountEntry accountEntry)
		throws SystemException {
		accountEntry = toUnwrappedModel(accountEntry);

		try {
			clearSupportRegions.clear(accountEntry.getPrimaryKey());
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(AccountEntryModelImpl.MAPPING_TABLE_OSB_ACCOUNTENTRIES_SUPPORTREGIONS_NAME);
		}

		try {
			clearSupportTeams.clear(accountEntry.getPrimaryKey());
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(AccountEntryModelImpl.MAPPING_TABLE_OSB_ACCOUNTENTRIES_SUPPORTTEAMS_NAME);
		}

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, accountEntry);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(accountEntry);

		return accountEntry;
	}

	@Override
	public AccountEntry updateImpl(
		com.liferay.osb.model.AccountEntry accountEntry, boolean merge)
		throws SystemException {
		accountEntry = toUnwrappedModel(accountEntry);

		boolean isNew = accountEntry.isNew();

		AccountEntryModelImpl accountEntryModelImpl = (AccountEntryModelImpl)accountEntry;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, accountEntry, merge);

			accountEntry.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !AccountEntryModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((accountEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAME.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						accountEntryModelImpl.getOriginalName()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_NAME, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAME,
					args);

				args = new Object[] { accountEntryModelImpl.getName() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_NAME, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAME,
					args);
			}

			if ((accountEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REDIRECTACCOUNTENTRYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(accountEntryModelImpl.getOriginalRedirectAccountEntryId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_REDIRECTACCOUNTENTRYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REDIRECTACCOUNTENTRYID,
					args);

				args = new Object[] {
						Long.valueOf(accountEntryModelImpl.getRedirectAccountEntryId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_REDIRECTACCOUNTENTRYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REDIRECTACCOUNTENTRYID,
					args);
			}

			if ((accountEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARTNERENTRYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(accountEntryModelImpl.getOriginalPartnerEntryId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PARTNERENTRYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARTNERENTRYID,
					args);

				args = new Object[] {
						Long.valueOf(accountEntryModelImpl.getPartnerEntryId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PARTNERENTRYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARTNERENTRYID,
					args);
			}

			if ((accountEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_HIGHESTSUPPORTRESPONSEID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(accountEntryModelImpl.getOriginalHighestSupportResponseId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_HIGHESTSUPPORTRESPONSEID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_HIGHESTSUPPORTRESPONSEID,
					args);

				args = new Object[] {
						Long.valueOf(accountEntryModelImpl.getHighestSupportResponseId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_HIGHESTSUPPORTRESPONSEID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_HIGHESTSUPPORTRESPONSEID,
					args);
			}

			if ((accountEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_T.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(accountEntryModelImpl.getOriginalUserId()),
						Integer.valueOf(accountEntryModelImpl.getOriginalType())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_U_T, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_T,
					args);

				args = new Object[] {
						Long.valueOf(accountEntryModelImpl.getUserId()),
						Integer.valueOf(accountEntryModelImpl.getType())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_U_T, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_T,
					args);
			}

			if ((accountEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RAEI_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(accountEntryModelImpl.getOriginalRedirectAccountEntryId()),
						Integer.valueOf(accountEntryModelImpl.getOriginalStatus())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_RAEI_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RAEI_S,
					args);

				args = new Object[] {
						Long.valueOf(accountEntryModelImpl.getRedirectAccountEntryId()),
						Integer.valueOf(accountEntryModelImpl.getStatus())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_RAEI_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RAEI_S,
					args);
			}
		}

		EntityCacheUtil.putResult(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryImpl.class, accountEntry.getPrimaryKey(), accountEntry);

		clearUniqueFindersCache(accountEntry);
		cacheUniqueFindersCache(accountEntry);

		return accountEntry;
	}

	protected AccountEntry toUnwrappedModel(AccountEntry accountEntry) {
		if (accountEntry instanceof AccountEntryImpl) {
			return accountEntry;
		}

		AccountEntryImpl accountEntryImpl = new AccountEntryImpl();

		accountEntryImpl.setNew(accountEntry.isNew());
		accountEntryImpl.setPrimaryKey(accountEntry.getPrimaryKey());

		accountEntryImpl.setAccountEntryId(accountEntry.getAccountEntryId());
		accountEntryImpl.setUserId(accountEntry.getUserId());
		accountEntryImpl.setUserName(accountEntry.getUserName());
		accountEntryImpl.setCreateDate(accountEntry.getCreateDate());
		accountEntryImpl.setModifiedUserId(accountEntry.getModifiedUserId());
		accountEntryImpl.setModifiedUserName(accountEntry.getModifiedUserName());
		accountEntryImpl.setModifiedDate(accountEntry.getModifiedDate());
		accountEntryImpl.setCorpProjectId(accountEntry.getCorpProjectId());
		accountEntryImpl.setCorpEntryName(accountEntry.getCorpEntryName());
		accountEntryImpl.setName(accountEntry.getName());
		accountEntryImpl.setCode(accountEntry.getCode());
		accountEntryImpl.setRedirectAccountEntryId(accountEntry.getRedirectAccountEntryId());
		accountEntryImpl.setType(accountEntry.getType());
		accountEntryImpl.setIndustry(accountEntry.getIndustry());
		accountEntryImpl.setCountryId(accountEntry.getCountryId());
		accountEntryImpl.setPartnerEntryId(accountEntry.getPartnerEntryId());
		accountEntryImpl.setPartnerManagedSupport(accountEntry.isPartnerManagedSupport());
		accountEntryImpl.setTier(accountEntry.getTier());
		accountEntryImpl.setMaxCustomers(accountEntry.getMaxCustomers());
		accountEntryImpl.setInstructions(accountEntry.getInstructions());
		accountEntryImpl.setNotes(accountEntry.getNotes());
		accountEntryImpl.setHighestSupportResponseId(accountEntry.getHighestSupportResponseId());
		accountEntryImpl.setLastAuditDate(accountEntry.getLastAuditDate());
		accountEntryImpl.setStatus(accountEntry.getStatus());
		accountEntryImpl.setStatusByUserId(accountEntry.getStatusByUserId());
		accountEntryImpl.setStatusByUserName(accountEntry.getStatusByUserName());
		accountEntryImpl.setStatusDate(accountEntry.getStatusDate());
		accountEntryImpl.setStatusMessage(accountEntry.getStatusMessage());

		return accountEntryImpl;
	}

	/**
	 * Returns the account entry with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the account entry
	 * @return the account entry
	 * @throws com.liferay.portal.NoSuchModelException if a account entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AccountEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the account entry with the primary key or throws a {@link com.liferay.osb.NoSuchAccountEntryException} if it could not be found.
	 *
	 * @param accountEntryId the primary key of the account entry
	 * @return the account entry
	 * @throws com.liferay.osb.NoSuchAccountEntryException if a account entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEntry findByPrimaryKey(long accountEntryId)
		throws NoSuchAccountEntryException, SystemException {
		AccountEntry accountEntry = fetchByPrimaryKey(accountEntryId);

		if (accountEntry == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + accountEntryId);
			}

			throw new NoSuchAccountEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				accountEntryId);
		}

		return accountEntry;
	}

	/**
	 * Returns the account entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the account entry
	 * @return the account entry, or <code>null</code> if a account entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AccountEntry fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the account entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param accountEntryId the primary key of the account entry
	 * @return the account entry, or <code>null</code> if a account entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEntry fetchByPrimaryKey(long accountEntryId)
		throws SystemException {
		AccountEntry accountEntry = (AccountEntry)EntityCacheUtil.getResult(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
				AccountEntryImpl.class, accountEntryId);

		if (accountEntry == _nullAccountEntry) {
			return null;
		}

		if (accountEntry == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				accountEntry = (AccountEntry)session.get(AccountEntryImpl.class,
						Long.valueOf(accountEntryId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (accountEntry != null) {
					cacheResult(accountEntry);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
						AccountEntryImpl.class, accountEntryId,
						_nullAccountEntry);
				}

				closeSession(session);
			}
		}

		return accountEntry;
	}

	/**
	 * Returns the account entry where corpProjectId = &#63; or throws a {@link com.liferay.osb.NoSuchAccountEntryException} if it could not be found.
	 *
	 * @param corpProjectId the corp project ID
	 * @return the matching account entry
	 * @throws com.liferay.osb.NoSuchAccountEntryException if a matching account entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEntry findByCorpProjectId(long corpProjectId)
		throws NoSuchAccountEntryException, SystemException {
		AccountEntry accountEntry = fetchByCorpProjectId(corpProjectId);

		if (accountEntry == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("corpProjectId=");
			msg.append(corpProjectId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchAccountEntryException(msg.toString());
		}

		return accountEntry;
	}

	/**
	 * Returns the account entry where corpProjectId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param corpProjectId the corp project ID
	 * @return the matching account entry, or <code>null</code> if a matching account entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEntry fetchByCorpProjectId(long corpProjectId)
		throws SystemException {
		return fetchByCorpProjectId(corpProjectId, true);
	}

	/**
	 * Returns the account entry where corpProjectId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param corpProjectId the corp project ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching account entry, or <code>null</code> if a matching account entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEntry fetchByCorpProjectId(long corpProjectId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { corpProjectId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_CORPPROJECTID,
					finderArgs, this);
		}

		if (result instanceof AccountEntry) {
			AccountEntry accountEntry = (AccountEntry)result;

			if ((corpProjectId != accountEntry.getCorpProjectId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_ACCOUNTENTRY_WHERE);

			query.append(_FINDER_COLUMN_CORPPROJECTID_CORPPROJECTID_2);

			query.append(AccountEntryModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(corpProjectId);

				List<AccountEntry> list = q.list();

				result = list;

				AccountEntry accountEntry = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CORPPROJECTID,
						finderArgs, list);
				}
				else {
					accountEntry = list.get(0);

					cacheResult(accountEntry);

					if ((accountEntry.getCorpProjectId() != corpProjectId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CORPPROJECTID,
							finderArgs, accountEntry);
					}
				}

				return accountEntry;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CORPPROJECTID,
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
				return (AccountEntry)result;
			}
		}
	}

	/**
	 * Returns all the account entries where name = &#63;.
	 *
	 * @param name the name
	 * @return the matching account entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountEntry> findByName(String name) throws SystemException {
		return findByName(name, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account entries where name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param name the name
	 * @param start the lower bound of the range of account entries
	 * @param end the upper bound of the range of account entries (not inclusive)
	 * @return the range of matching account entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountEntry> findByName(String name, int start, int end)
		throws SystemException {
		return findByName(name, start, end, null);
	}

	/**
	 * Returns an ordered range of all the account entries where name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param name the name
	 * @param start the lower bound of the range of account entries
	 * @param end the upper bound of the range of account entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountEntry> findByName(String name, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAME;
			finderArgs = new Object[] { name };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_NAME;
			finderArgs = new Object[] { name, start, end, orderByComparator };
		}

		List<AccountEntry> list = (List<AccountEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AccountEntry accountEntry : list) {
				if (!Validator.equals(name, accountEntry.getName())) {
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

			query.append(_SQL_SELECT_ACCOUNTENTRY_WHERE);

			if (name == null) {
				query.append(_FINDER_COLUMN_NAME_NAME_1);
			}
			else {
				if (name.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_NAME_NAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_NAME_NAME_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(AccountEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (name != null) {
					qPos.add(name);
				}

				list = (List<AccountEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first account entry in the ordered set where name = &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account entry
	 * @throws com.liferay.osb.NoSuchAccountEntryException if a matching account entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEntry findByName_First(String name,
		OrderByComparator orderByComparator)
		throws NoSuchAccountEntryException, SystemException {
		AccountEntry accountEntry = fetchByName_First(name, orderByComparator);

		if (accountEntry != null) {
			return accountEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("name=");
		msg.append(name);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountEntryException(msg.toString());
	}

	/**
	 * Returns the first account entry in the ordered set where name = &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account entry, or <code>null</code> if a matching account entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEntry fetchByName_First(String name,
		OrderByComparator orderByComparator) throws SystemException {
		List<AccountEntry> list = findByName(name, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last account entry in the ordered set where name = &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account entry
	 * @throws com.liferay.osb.NoSuchAccountEntryException if a matching account entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEntry findByName_Last(String name,
		OrderByComparator orderByComparator)
		throws NoSuchAccountEntryException, SystemException {
		AccountEntry accountEntry = fetchByName_Last(name, orderByComparator);

		if (accountEntry != null) {
			return accountEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("name=");
		msg.append(name);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountEntryException(msg.toString());
	}

	/**
	 * Returns the last account entry in the ordered set where name = &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account entry, or <code>null</code> if a matching account entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEntry fetchByName_Last(String name,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByName(name);

		List<AccountEntry> list = findByName(name, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the account entries before and after the current account entry in the ordered set where name = &#63;.
	 *
	 * @param accountEntryId the primary key of the current account entry
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account entry
	 * @throws com.liferay.osb.NoSuchAccountEntryException if a account entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEntry[] findByName_PrevAndNext(long accountEntryId,
		String name, OrderByComparator orderByComparator)
		throws NoSuchAccountEntryException, SystemException {
		AccountEntry accountEntry = findByPrimaryKey(accountEntryId);

		Session session = null;

		try {
			session = openSession();

			AccountEntry[] array = new AccountEntryImpl[3];

			array[0] = getByName_PrevAndNext(session, accountEntry, name,
					orderByComparator, true);

			array[1] = accountEntry;

			array[2] = getByName_PrevAndNext(session, accountEntry, name,
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

	protected AccountEntry getByName_PrevAndNext(Session session,
		AccountEntry accountEntry, String name,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ACCOUNTENTRY_WHERE);

		if (name == null) {
			query.append(_FINDER_COLUMN_NAME_NAME_1);
		}
		else {
			if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_NAME_NAME_3);
			}
			else {
				query.append(_FINDER_COLUMN_NAME_NAME_2);
			}
		}

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
			query.append(AccountEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (name != null) {
			qPos.add(name);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(accountEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AccountEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the account entry where code = &#63; or throws a {@link com.liferay.osb.NoSuchAccountEntryException} if it could not be found.
	 *
	 * @param code the code
	 * @return the matching account entry
	 * @throws com.liferay.osb.NoSuchAccountEntryException if a matching account entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEntry findByCode(String code)
		throws NoSuchAccountEntryException, SystemException {
		AccountEntry accountEntry = fetchByCode(code);

		if (accountEntry == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("code=");
			msg.append(code);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchAccountEntryException(msg.toString());
		}

		return accountEntry;
	}

	/**
	 * Returns the account entry where code = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param code the code
	 * @return the matching account entry, or <code>null</code> if a matching account entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEntry fetchByCode(String code) throws SystemException {
		return fetchByCode(code, true);
	}

	/**
	 * Returns the account entry where code = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param code the code
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching account entry, or <code>null</code> if a matching account entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEntry fetchByCode(String code, boolean retrieveFromCache)
		throws SystemException {
		Object[] finderArgs = new Object[] { code };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_CODE,
					finderArgs, this);
		}

		if (result instanceof AccountEntry) {
			AccountEntry accountEntry = (AccountEntry)result;

			if (!Validator.equals(code, accountEntry.getCode())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_ACCOUNTENTRY_WHERE);

			if (code == null) {
				query.append(_FINDER_COLUMN_CODE_CODE_1);
			}
			else {
				if (code.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_CODE_CODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_CODE_CODE_2);
				}
			}

			query.append(AccountEntryModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (code != null) {
					qPos.add(code);
				}

				List<AccountEntry> list = q.list();

				result = list;

				AccountEntry accountEntry = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CODE,
						finderArgs, list);
				}
				else {
					accountEntry = list.get(0);

					cacheResult(accountEntry);

					if ((accountEntry.getCode() == null) ||
							!accountEntry.getCode().equals(code)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CODE,
							finderArgs, accountEntry);
					}
				}

				return accountEntry;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CODE,
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
				return (AccountEntry)result;
			}
		}
	}

	/**
	 * Returns all the account entries where redirectAccountEntryId = &#63;.
	 *
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @return the matching account entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountEntry> findByRedirectAccountEntryId(
		long redirectAccountEntryId) throws SystemException {
		return findByRedirectAccountEntryId(redirectAccountEntryId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account entries where redirectAccountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param start the lower bound of the range of account entries
	 * @param end the upper bound of the range of account entries (not inclusive)
	 * @return the range of matching account entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountEntry> findByRedirectAccountEntryId(
		long redirectAccountEntryId, int start, int end)
		throws SystemException {
		return findByRedirectAccountEntryId(redirectAccountEntryId, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the account entries where redirectAccountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param start the lower bound of the range of account entries
	 * @param end the upper bound of the range of account entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountEntry> findByRedirectAccountEntryId(
		long redirectAccountEntryId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REDIRECTACCOUNTENTRYID;
			finderArgs = new Object[] { redirectAccountEntryId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_REDIRECTACCOUNTENTRYID;
			finderArgs = new Object[] {
					redirectAccountEntryId,
					
					start, end, orderByComparator
				};
		}

		List<AccountEntry> list = (List<AccountEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AccountEntry accountEntry : list) {
				if ((redirectAccountEntryId != accountEntry.getRedirectAccountEntryId())) {
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

			query.append(_SQL_SELECT_ACCOUNTENTRY_WHERE);

			query.append(_FINDER_COLUMN_REDIRECTACCOUNTENTRYID_REDIRECTACCOUNTENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(AccountEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(redirectAccountEntryId);

				list = (List<AccountEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first account entry in the ordered set where redirectAccountEntryId = &#63;.
	 *
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account entry
	 * @throws com.liferay.osb.NoSuchAccountEntryException if a matching account entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEntry findByRedirectAccountEntryId_First(
		long redirectAccountEntryId, OrderByComparator orderByComparator)
		throws NoSuchAccountEntryException, SystemException {
		AccountEntry accountEntry = fetchByRedirectAccountEntryId_First(redirectAccountEntryId,
				orderByComparator);

		if (accountEntry != null) {
			return accountEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("redirectAccountEntryId=");
		msg.append(redirectAccountEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountEntryException(msg.toString());
	}

	/**
	 * Returns the first account entry in the ordered set where redirectAccountEntryId = &#63;.
	 *
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account entry, or <code>null</code> if a matching account entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEntry fetchByRedirectAccountEntryId_First(
		long redirectAccountEntryId, OrderByComparator orderByComparator)
		throws SystemException {
		List<AccountEntry> list = findByRedirectAccountEntryId(redirectAccountEntryId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last account entry in the ordered set where redirectAccountEntryId = &#63;.
	 *
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account entry
	 * @throws com.liferay.osb.NoSuchAccountEntryException if a matching account entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEntry findByRedirectAccountEntryId_Last(
		long redirectAccountEntryId, OrderByComparator orderByComparator)
		throws NoSuchAccountEntryException, SystemException {
		AccountEntry accountEntry = fetchByRedirectAccountEntryId_Last(redirectAccountEntryId,
				orderByComparator);

		if (accountEntry != null) {
			return accountEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("redirectAccountEntryId=");
		msg.append(redirectAccountEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountEntryException(msg.toString());
	}

	/**
	 * Returns the last account entry in the ordered set where redirectAccountEntryId = &#63;.
	 *
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account entry, or <code>null</code> if a matching account entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEntry fetchByRedirectAccountEntryId_Last(
		long redirectAccountEntryId, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByRedirectAccountEntryId(redirectAccountEntryId);

		List<AccountEntry> list = findByRedirectAccountEntryId(redirectAccountEntryId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the account entries before and after the current account entry in the ordered set where redirectAccountEntryId = &#63;.
	 *
	 * @param accountEntryId the primary key of the current account entry
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account entry
	 * @throws com.liferay.osb.NoSuchAccountEntryException if a account entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEntry[] findByRedirectAccountEntryId_PrevAndNext(
		long accountEntryId, long redirectAccountEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchAccountEntryException, SystemException {
		AccountEntry accountEntry = findByPrimaryKey(accountEntryId);

		Session session = null;

		try {
			session = openSession();

			AccountEntry[] array = new AccountEntryImpl[3];

			array[0] = getByRedirectAccountEntryId_PrevAndNext(session,
					accountEntry, redirectAccountEntryId, orderByComparator,
					true);

			array[1] = accountEntry;

			array[2] = getByRedirectAccountEntryId_PrevAndNext(session,
					accountEntry, redirectAccountEntryId, orderByComparator,
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

	protected AccountEntry getByRedirectAccountEntryId_PrevAndNext(
		Session session, AccountEntry accountEntry,
		long redirectAccountEntryId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ACCOUNTENTRY_WHERE);

		query.append(_FINDER_COLUMN_REDIRECTACCOUNTENTRYID_REDIRECTACCOUNTENTRYID_2);

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
			query.append(AccountEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(redirectAccountEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(accountEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AccountEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the account entries where partnerEntryId = &#63;.
	 *
	 * @param partnerEntryId the partner entry ID
	 * @return the matching account entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountEntry> findByPartnerEntryId(long partnerEntryId)
		throws SystemException {
		return findByPartnerEntryId(partnerEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account entries where partnerEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param partnerEntryId the partner entry ID
	 * @param start the lower bound of the range of account entries
	 * @param end the upper bound of the range of account entries (not inclusive)
	 * @return the range of matching account entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountEntry> findByPartnerEntryId(long partnerEntryId,
		int start, int end) throws SystemException {
		return findByPartnerEntryId(partnerEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the account entries where partnerEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param partnerEntryId the partner entry ID
	 * @param start the lower bound of the range of account entries
	 * @param end the upper bound of the range of account entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountEntry> findByPartnerEntryId(long partnerEntryId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARTNERENTRYID;
			finderArgs = new Object[] { partnerEntryId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PARTNERENTRYID;
			finderArgs = new Object[] {
					partnerEntryId,
					
					start, end, orderByComparator
				};
		}

		List<AccountEntry> list = (List<AccountEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AccountEntry accountEntry : list) {
				if ((partnerEntryId != accountEntry.getPartnerEntryId())) {
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

			query.append(_SQL_SELECT_ACCOUNTENTRY_WHERE);

			query.append(_FINDER_COLUMN_PARTNERENTRYID_PARTNERENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(AccountEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(partnerEntryId);

				list = (List<AccountEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first account entry in the ordered set where partnerEntryId = &#63;.
	 *
	 * @param partnerEntryId the partner entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account entry
	 * @throws com.liferay.osb.NoSuchAccountEntryException if a matching account entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEntry findByPartnerEntryId_First(long partnerEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchAccountEntryException, SystemException {
		AccountEntry accountEntry = fetchByPartnerEntryId_First(partnerEntryId,
				orderByComparator);

		if (accountEntry != null) {
			return accountEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("partnerEntryId=");
		msg.append(partnerEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountEntryException(msg.toString());
	}

	/**
	 * Returns the first account entry in the ordered set where partnerEntryId = &#63;.
	 *
	 * @param partnerEntryId the partner entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account entry, or <code>null</code> if a matching account entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEntry fetchByPartnerEntryId_First(long partnerEntryId,
		OrderByComparator orderByComparator) throws SystemException {
		List<AccountEntry> list = findByPartnerEntryId(partnerEntryId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last account entry in the ordered set where partnerEntryId = &#63;.
	 *
	 * @param partnerEntryId the partner entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account entry
	 * @throws com.liferay.osb.NoSuchAccountEntryException if a matching account entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEntry findByPartnerEntryId_Last(long partnerEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchAccountEntryException, SystemException {
		AccountEntry accountEntry = fetchByPartnerEntryId_Last(partnerEntryId,
				orderByComparator);

		if (accountEntry != null) {
			return accountEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("partnerEntryId=");
		msg.append(partnerEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountEntryException(msg.toString());
	}

	/**
	 * Returns the last account entry in the ordered set where partnerEntryId = &#63;.
	 *
	 * @param partnerEntryId the partner entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account entry, or <code>null</code> if a matching account entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEntry fetchByPartnerEntryId_Last(long partnerEntryId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByPartnerEntryId(partnerEntryId);

		List<AccountEntry> list = findByPartnerEntryId(partnerEntryId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the account entries before and after the current account entry in the ordered set where partnerEntryId = &#63;.
	 *
	 * @param accountEntryId the primary key of the current account entry
	 * @param partnerEntryId the partner entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account entry
	 * @throws com.liferay.osb.NoSuchAccountEntryException if a account entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEntry[] findByPartnerEntryId_PrevAndNext(
		long accountEntryId, long partnerEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchAccountEntryException, SystemException {
		AccountEntry accountEntry = findByPrimaryKey(accountEntryId);

		Session session = null;

		try {
			session = openSession();

			AccountEntry[] array = new AccountEntryImpl[3];

			array[0] = getByPartnerEntryId_PrevAndNext(session, accountEntry,
					partnerEntryId, orderByComparator, true);

			array[1] = accountEntry;

			array[2] = getByPartnerEntryId_PrevAndNext(session, accountEntry,
					partnerEntryId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AccountEntry getByPartnerEntryId_PrevAndNext(Session session,
		AccountEntry accountEntry, long partnerEntryId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ACCOUNTENTRY_WHERE);

		query.append(_FINDER_COLUMN_PARTNERENTRYID_PARTNERENTRYID_2);

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
			query.append(AccountEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(partnerEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(accountEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AccountEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the account entries where highestSupportResponseId = &#63;.
	 *
	 * @param highestSupportResponseId the highest support response ID
	 * @return the matching account entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountEntry> findByHighestSupportResponseId(
		long highestSupportResponseId) throws SystemException {
		return findByHighestSupportResponseId(highestSupportResponseId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account entries where highestSupportResponseId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param highestSupportResponseId the highest support response ID
	 * @param start the lower bound of the range of account entries
	 * @param end the upper bound of the range of account entries (not inclusive)
	 * @return the range of matching account entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountEntry> findByHighestSupportResponseId(
		long highestSupportResponseId, int start, int end)
		throws SystemException {
		return findByHighestSupportResponseId(highestSupportResponseId, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the account entries where highestSupportResponseId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param highestSupportResponseId the highest support response ID
	 * @param start the lower bound of the range of account entries
	 * @param end the upper bound of the range of account entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountEntry> findByHighestSupportResponseId(
		long highestSupportResponseId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_HIGHESTSUPPORTRESPONSEID;
			finderArgs = new Object[] { highestSupportResponseId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_HIGHESTSUPPORTRESPONSEID;
			finderArgs = new Object[] {
					highestSupportResponseId,
					
					start, end, orderByComparator
				};
		}

		List<AccountEntry> list = (List<AccountEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AccountEntry accountEntry : list) {
				if ((highestSupportResponseId != accountEntry.getHighestSupportResponseId())) {
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

			query.append(_SQL_SELECT_ACCOUNTENTRY_WHERE);

			query.append(_FINDER_COLUMN_HIGHESTSUPPORTRESPONSEID_HIGHESTSUPPORTRESPONSEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(AccountEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(highestSupportResponseId);

				list = (List<AccountEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first account entry in the ordered set where highestSupportResponseId = &#63;.
	 *
	 * @param highestSupportResponseId the highest support response ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account entry
	 * @throws com.liferay.osb.NoSuchAccountEntryException if a matching account entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEntry findByHighestSupportResponseId_First(
		long highestSupportResponseId, OrderByComparator orderByComparator)
		throws NoSuchAccountEntryException, SystemException {
		AccountEntry accountEntry = fetchByHighestSupportResponseId_First(highestSupportResponseId,
				orderByComparator);

		if (accountEntry != null) {
			return accountEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("highestSupportResponseId=");
		msg.append(highestSupportResponseId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountEntryException(msg.toString());
	}

	/**
	 * Returns the first account entry in the ordered set where highestSupportResponseId = &#63;.
	 *
	 * @param highestSupportResponseId the highest support response ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account entry, or <code>null</code> if a matching account entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEntry fetchByHighestSupportResponseId_First(
		long highestSupportResponseId, OrderByComparator orderByComparator)
		throws SystemException {
		List<AccountEntry> list = findByHighestSupportResponseId(highestSupportResponseId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last account entry in the ordered set where highestSupportResponseId = &#63;.
	 *
	 * @param highestSupportResponseId the highest support response ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account entry
	 * @throws com.liferay.osb.NoSuchAccountEntryException if a matching account entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEntry findByHighestSupportResponseId_Last(
		long highestSupportResponseId, OrderByComparator orderByComparator)
		throws NoSuchAccountEntryException, SystemException {
		AccountEntry accountEntry = fetchByHighestSupportResponseId_Last(highestSupportResponseId,
				orderByComparator);

		if (accountEntry != null) {
			return accountEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("highestSupportResponseId=");
		msg.append(highestSupportResponseId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountEntryException(msg.toString());
	}

	/**
	 * Returns the last account entry in the ordered set where highestSupportResponseId = &#63;.
	 *
	 * @param highestSupportResponseId the highest support response ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account entry, or <code>null</code> if a matching account entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEntry fetchByHighestSupportResponseId_Last(
		long highestSupportResponseId, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByHighestSupportResponseId(highestSupportResponseId);

		List<AccountEntry> list = findByHighestSupportResponseId(highestSupportResponseId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the account entries before and after the current account entry in the ordered set where highestSupportResponseId = &#63;.
	 *
	 * @param accountEntryId the primary key of the current account entry
	 * @param highestSupportResponseId the highest support response ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account entry
	 * @throws com.liferay.osb.NoSuchAccountEntryException if a account entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEntry[] findByHighestSupportResponseId_PrevAndNext(
		long accountEntryId, long highestSupportResponseId,
		OrderByComparator orderByComparator)
		throws NoSuchAccountEntryException, SystemException {
		AccountEntry accountEntry = findByPrimaryKey(accountEntryId);

		Session session = null;

		try {
			session = openSession();

			AccountEntry[] array = new AccountEntryImpl[3];

			array[0] = getByHighestSupportResponseId_PrevAndNext(session,
					accountEntry, highestSupportResponseId, orderByComparator,
					true);

			array[1] = accountEntry;

			array[2] = getByHighestSupportResponseId_PrevAndNext(session,
					accountEntry, highestSupportResponseId, orderByComparator,
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

	protected AccountEntry getByHighestSupportResponseId_PrevAndNext(
		Session session, AccountEntry accountEntry,
		long highestSupportResponseId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ACCOUNTENTRY_WHERE);

		query.append(_FINDER_COLUMN_HIGHESTSUPPORTRESPONSEID_HIGHESTSUPPORTRESPONSEID_2);

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
			query.append(AccountEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(highestSupportResponseId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(accountEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AccountEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the account entries where userId = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param type the type
	 * @return the matching account entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountEntry> findByU_T(long userId, int type)
		throws SystemException {
		return findByU_T(userId, type, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the account entries where userId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param type the type
	 * @param start the lower bound of the range of account entries
	 * @param end the upper bound of the range of account entries (not inclusive)
	 * @return the range of matching account entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountEntry> findByU_T(long userId, int type, int start,
		int end) throws SystemException {
		return findByU_T(userId, type, start, end, null);
	}

	/**
	 * Returns an ordered range of all the account entries where userId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param type the type
	 * @param start the lower bound of the range of account entries
	 * @param end the upper bound of the range of account entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountEntry> findByU_T(long userId, int type, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_T;
			finderArgs = new Object[] { userId, type };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_U_T;
			finderArgs = new Object[] {
					userId, type,
					
					start, end, orderByComparator
				};
		}

		List<AccountEntry> list = (List<AccountEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AccountEntry accountEntry : list) {
				if ((userId != accountEntry.getUserId()) ||
						(type != accountEntry.getType())) {
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
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_ACCOUNTENTRY_WHERE);

			query.append(_FINDER_COLUMN_U_T_USERID_2);

			query.append(_FINDER_COLUMN_U_T_TYPE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(AccountEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(type);

				list = (List<AccountEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first account entry in the ordered set where userId = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account entry
	 * @throws com.liferay.osb.NoSuchAccountEntryException if a matching account entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEntry findByU_T_First(long userId, int type,
		OrderByComparator orderByComparator)
		throws NoSuchAccountEntryException, SystemException {
		AccountEntry accountEntry = fetchByU_T_First(userId, type,
				orderByComparator);

		if (accountEntry != null) {
			return accountEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountEntryException(msg.toString());
	}

	/**
	 * Returns the first account entry in the ordered set where userId = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account entry, or <code>null</code> if a matching account entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEntry fetchByU_T_First(long userId, int type,
		OrderByComparator orderByComparator) throws SystemException {
		List<AccountEntry> list = findByU_T(userId, type, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last account entry in the ordered set where userId = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account entry
	 * @throws com.liferay.osb.NoSuchAccountEntryException if a matching account entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEntry findByU_T_Last(long userId, int type,
		OrderByComparator orderByComparator)
		throws NoSuchAccountEntryException, SystemException {
		AccountEntry accountEntry = fetchByU_T_Last(userId, type,
				orderByComparator);

		if (accountEntry != null) {
			return accountEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountEntryException(msg.toString());
	}

	/**
	 * Returns the last account entry in the ordered set where userId = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account entry, or <code>null</code> if a matching account entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEntry fetchByU_T_Last(long userId, int type,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByU_T(userId, type);

		List<AccountEntry> list = findByU_T(userId, type, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the account entries before and after the current account entry in the ordered set where userId = &#63; and type = &#63;.
	 *
	 * @param accountEntryId the primary key of the current account entry
	 * @param userId the user ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account entry
	 * @throws com.liferay.osb.NoSuchAccountEntryException if a account entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEntry[] findByU_T_PrevAndNext(long accountEntryId,
		long userId, int type, OrderByComparator orderByComparator)
		throws NoSuchAccountEntryException, SystemException {
		AccountEntry accountEntry = findByPrimaryKey(accountEntryId);

		Session session = null;

		try {
			session = openSession();

			AccountEntry[] array = new AccountEntryImpl[3];

			array[0] = getByU_T_PrevAndNext(session, accountEntry, userId,
					type, orderByComparator, true);

			array[1] = accountEntry;

			array[2] = getByU_T_PrevAndNext(session, accountEntry, userId,
					type, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AccountEntry getByU_T_PrevAndNext(Session session,
		AccountEntry accountEntry, long userId, int type,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ACCOUNTENTRY_WHERE);

		query.append(_FINDER_COLUMN_U_T_USERID_2);

		query.append(_FINDER_COLUMN_U_T_TYPE_2);

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
			query.append(AccountEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		qPos.add(type);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(accountEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AccountEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the account entries where redirectAccountEntryId = &#63; and status = &#63;.
	 *
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param status the status
	 * @return the matching account entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountEntry> findByRAEI_S(long redirectAccountEntryId,
		int status) throws SystemException {
		return findByRAEI_S(redirectAccountEntryId, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account entries where redirectAccountEntryId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param status the status
	 * @param start the lower bound of the range of account entries
	 * @param end the upper bound of the range of account entries (not inclusive)
	 * @return the range of matching account entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountEntry> findByRAEI_S(long redirectAccountEntryId,
		int status, int start, int end) throws SystemException {
		return findByRAEI_S(redirectAccountEntryId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the account entries where redirectAccountEntryId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param status the status
	 * @param start the lower bound of the range of account entries
	 * @param end the upper bound of the range of account entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountEntry> findByRAEI_S(long redirectAccountEntryId,
		int status, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RAEI_S;
			finderArgs = new Object[] { redirectAccountEntryId, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_RAEI_S;
			finderArgs = new Object[] {
					redirectAccountEntryId, status,
					
					start, end, orderByComparator
				};
		}

		List<AccountEntry> list = (List<AccountEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AccountEntry accountEntry : list) {
				if ((redirectAccountEntryId != accountEntry.getRedirectAccountEntryId()) ||
						(status != accountEntry.getStatus())) {
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
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_ACCOUNTENTRY_WHERE);

			query.append(_FINDER_COLUMN_RAEI_S_REDIRECTACCOUNTENTRYID_2);

			query.append(_FINDER_COLUMN_RAEI_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(AccountEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(redirectAccountEntryId);

				qPos.add(status);

				list = (List<AccountEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first account entry in the ordered set where redirectAccountEntryId = &#63; and status = &#63;.
	 *
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account entry
	 * @throws com.liferay.osb.NoSuchAccountEntryException if a matching account entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEntry findByRAEI_S_First(long redirectAccountEntryId,
		int status, OrderByComparator orderByComparator)
		throws NoSuchAccountEntryException, SystemException {
		AccountEntry accountEntry = fetchByRAEI_S_First(redirectAccountEntryId,
				status, orderByComparator);

		if (accountEntry != null) {
			return accountEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("redirectAccountEntryId=");
		msg.append(redirectAccountEntryId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountEntryException(msg.toString());
	}

	/**
	 * Returns the first account entry in the ordered set where redirectAccountEntryId = &#63; and status = &#63;.
	 *
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account entry, or <code>null</code> if a matching account entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEntry fetchByRAEI_S_First(long redirectAccountEntryId,
		int status, OrderByComparator orderByComparator)
		throws SystemException {
		List<AccountEntry> list = findByRAEI_S(redirectAccountEntryId, status,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last account entry in the ordered set where redirectAccountEntryId = &#63; and status = &#63;.
	 *
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account entry
	 * @throws com.liferay.osb.NoSuchAccountEntryException if a matching account entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEntry findByRAEI_S_Last(long redirectAccountEntryId,
		int status, OrderByComparator orderByComparator)
		throws NoSuchAccountEntryException, SystemException {
		AccountEntry accountEntry = fetchByRAEI_S_Last(redirectAccountEntryId,
				status, orderByComparator);

		if (accountEntry != null) {
			return accountEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("redirectAccountEntryId=");
		msg.append(redirectAccountEntryId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountEntryException(msg.toString());
	}

	/**
	 * Returns the last account entry in the ordered set where redirectAccountEntryId = &#63; and status = &#63;.
	 *
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account entry, or <code>null</code> if a matching account entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEntry fetchByRAEI_S_Last(long redirectAccountEntryId,
		int status, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByRAEI_S(redirectAccountEntryId, status);

		List<AccountEntry> list = findByRAEI_S(redirectAccountEntryId, status,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the account entries before and after the current account entry in the ordered set where redirectAccountEntryId = &#63; and status = &#63;.
	 *
	 * @param accountEntryId the primary key of the current account entry
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account entry
	 * @throws com.liferay.osb.NoSuchAccountEntryException if a account entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEntry[] findByRAEI_S_PrevAndNext(long accountEntryId,
		long redirectAccountEntryId, int status,
		OrderByComparator orderByComparator)
		throws NoSuchAccountEntryException, SystemException {
		AccountEntry accountEntry = findByPrimaryKey(accountEntryId);

		Session session = null;

		try {
			session = openSession();

			AccountEntry[] array = new AccountEntryImpl[3];

			array[0] = getByRAEI_S_PrevAndNext(session, accountEntry,
					redirectAccountEntryId, status, orderByComparator, true);

			array[1] = accountEntry;

			array[2] = getByRAEI_S_PrevAndNext(session, accountEntry,
					redirectAccountEntryId, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AccountEntry getByRAEI_S_PrevAndNext(Session session,
		AccountEntry accountEntry, long redirectAccountEntryId, int status,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ACCOUNTENTRY_WHERE);

		query.append(_FINDER_COLUMN_RAEI_S_REDIRECTACCOUNTENTRYID_2);

		query.append(_FINDER_COLUMN_RAEI_S_STATUS_2);

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
			query.append(AccountEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(redirectAccountEntryId);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(accountEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AccountEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the account entries where redirectAccountEntryId = &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param statuses the statuses
	 * @return the matching account entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountEntry> findByRAEI_S(long redirectAccountEntryId,
		int[] statuses) throws SystemException {
		return findByRAEI_S(redirectAccountEntryId, statuses,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account entries where redirectAccountEntryId = &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param statuses the statuses
	 * @param start the lower bound of the range of account entries
	 * @param end the upper bound of the range of account entries (not inclusive)
	 * @return the range of matching account entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountEntry> findByRAEI_S(long redirectAccountEntryId,
		int[] statuses, int start, int end) throws SystemException {
		return findByRAEI_S(redirectAccountEntryId, statuses, start, end, null);
	}

	/**
	 * Returns an ordered range of all the account entries where redirectAccountEntryId = &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param statuses the statuses
	 * @param start the lower bound of the range of account entries
	 * @param end the upper bound of the range of account entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountEntry> findByRAEI_S(long redirectAccountEntryId,
		int[] statuses, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_RAEI_S;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderArgs = new Object[] {
					redirectAccountEntryId, StringUtil.merge(statuses)
				};
		}
		else {
			finderArgs = new Object[] {
					redirectAccountEntryId, StringUtil.merge(statuses),
					
					start, end, orderByComparator
				};
		}

		List<AccountEntry> list = (List<AccountEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AccountEntry accountEntry : list) {
				if ((redirectAccountEntryId != accountEntry.getRedirectAccountEntryId()) ||
						!ArrayUtil.contains(statuses, accountEntry.getStatus())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_ACCOUNTENTRY_WHERE);

			boolean conjunctionable = false;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_RAEI_S_REDIRECTACCOUNTENTRYID_5);

			conjunctionable = true;

			if ((statuses == null) || (statuses.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < statuses.length; i++) {
					query.append(_FINDER_COLUMN_RAEI_S_STATUS_5);

					if ((i + 1) < statuses.length) {
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

			else {
				query.append(AccountEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(redirectAccountEntryId);

				if (statuses != null) {
					qPos.add(statuses);
				}

				list = (List<AccountEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns all the account entries where redirectAccountEntryId = &#63; and type &ne; &#63; and status = &#63;.
	 *
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param type the type
	 * @param status the status
	 * @return the matching account entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountEntry> findByRAEI_NotT_S(long redirectAccountEntryId,
		int type, int status) throws SystemException {
		return findByRAEI_NotT_S(redirectAccountEntryId, type, status,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account entries where redirectAccountEntryId = &#63; and type &ne; &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param type the type
	 * @param status the status
	 * @param start the lower bound of the range of account entries
	 * @param end the upper bound of the range of account entries (not inclusive)
	 * @return the range of matching account entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountEntry> findByRAEI_NotT_S(long redirectAccountEntryId,
		int type, int status, int start, int end) throws SystemException {
		return findByRAEI_NotT_S(redirectAccountEntryId, type, status, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the account entries where redirectAccountEntryId = &#63; and type &ne; &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param type the type
	 * @param status the status
	 * @param start the lower bound of the range of account entries
	 * @param end the upper bound of the range of account entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountEntry> findByRAEI_NotT_S(long redirectAccountEntryId,
		int type, int status, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_RAEI_NOTT_S;
		finderArgs = new Object[] {
				redirectAccountEntryId, type, status,
				
				start, end, orderByComparator
			};

		List<AccountEntry> list = (List<AccountEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AccountEntry accountEntry : list) {
				if ((redirectAccountEntryId != accountEntry.getRedirectAccountEntryId()) ||
						(type != accountEntry.getType()) ||
						(status != accountEntry.getStatus())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_ACCOUNTENTRY_WHERE);

			query.append(_FINDER_COLUMN_RAEI_NOTT_S_REDIRECTACCOUNTENTRYID_2);

			query.append(_FINDER_COLUMN_RAEI_NOTT_S_TYPE_2);

			query.append(_FINDER_COLUMN_RAEI_NOTT_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(AccountEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(redirectAccountEntryId);

				qPos.add(type);

				qPos.add(status);

				list = (List<AccountEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first account entry in the ordered set where redirectAccountEntryId = &#63; and type &ne; &#63; and status = &#63;.
	 *
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param type the type
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account entry
	 * @throws com.liferay.osb.NoSuchAccountEntryException if a matching account entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEntry findByRAEI_NotT_S_First(long redirectAccountEntryId,
		int type, int status, OrderByComparator orderByComparator)
		throws NoSuchAccountEntryException, SystemException {
		AccountEntry accountEntry = fetchByRAEI_NotT_S_First(redirectAccountEntryId,
				type, status, orderByComparator);

		if (accountEntry != null) {
			return accountEntry;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("redirectAccountEntryId=");
		msg.append(redirectAccountEntryId);

		msg.append(", type=");
		msg.append(type);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountEntryException(msg.toString());
	}

	/**
	 * Returns the first account entry in the ordered set where redirectAccountEntryId = &#63; and type &ne; &#63; and status = &#63;.
	 *
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param type the type
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account entry, or <code>null</code> if a matching account entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEntry fetchByRAEI_NotT_S_First(long redirectAccountEntryId,
		int type, int status, OrderByComparator orderByComparator)
		throws SystemException {
		List<AccountEntry> list = findByRAEI_NotT_S(redirectAccountEntryId,
				type, status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last account entry in the ordered set where redirectAccountEntryId = &#63; and type &ne; &#63; and status = &#63;.
	 *
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param type the type
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account entry
	 * @throws com.liferay.osb.NoSuchAccountEntryException if a matching account entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEntry findByRAEI_NotT_S_Last(long redirectAccountEntryId,
		int type, int status, OrderByComparator orderByComparator)
		throws NoSuchAccountEntryException, SystemException {
		AccountEntry accountEntry = fetchByRAEI_NotT_S_Last(redirectAccountEntryId,
				type, status, orderByComparator);

		if (accountEntry != null) {
			return accountEntry;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("redirectAccountEntryId=");
		msg.append(redirectAccountEntryId);

		msg.append(", type=");
		msg.append(type);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountEntryException(msg.toString());
	}

	/**
	 * Returns the last account entry in the ordered set where redirectAccountEntryId = &#63; and type &ne; &#63; and status = &#63;.
	 *
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param type the type
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account entry, or <code>null</code> if a matching account entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEntry fetchByRAEI_NotT_S_Last(long redirectAccountEntryId,
		int type, int status, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByRAEI_NotT_S(redirectAccountEntryId, type, status);

		List<AccountEntry> list = findByRAEI_NotT_S(redirectAccountEntryId,
				type, status, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the account entries before and after the current account entry in the ordered set where redirectAccountEntryId = &#63; and type &ne; &#63; and status = &#63;.
	 *
	 * @param accountEntryId the primary key of the current account entry
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param type the type
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account entry
	 * @throws com.liferay.osb.NoSuchAccountEntryException if a account entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEntry[] findByRAEI_NotT_S_PrevAndNext(long accountEntryId,
		long redirectAccountEntryId, int type, int status,
		OrderByComparator orderByComparator)
		throws NoSuchAccountEntryException, SystemException {
		AccountEntry accountEntry = findByPrimaryKey(accountEntryId);

		Session session = null;

		try {
			session = openSession();

			AccountEntry[] array = new AccountEntryImpl[3];

			array[0] = getByRAEI_NotT_S_PrevAndNext(session, accountEntry,
					redirectAccountEntryId, type, status, orderByComparator,
					true);

			array[1] = accountEntry;

			array[2] = getByRAEI_NotT_S_PrevAndNext(session, accountEntry,
					redirectAccountEntryId, type, status, orderByComparator,
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

	protected AccountEntry getByRAEI_NotT_S_PrevAndNext(Session session,
		AccountEntry accountEntry, long redirectAccountEntryId, int type,
		int status, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ACCOUNTENTRY_WHERE);

		query.append(_FINDER_COLUMN_RAEI_NOTT_S_REDIRECTACCOUNTENTRYID_2);

		query.append(_FINDER_COLUMN_RAEI_NOTT_S_TYPE_2);

		query.append(_FINDER_COLUMN_RAEI_NOTT_S_STATUS_2);

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
			query.append(AccountEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(redirectAccountEntryId);

		qPos.add(type);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(accountEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AccountEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the account entries where redirectAccountEntryId = &#63; and type &ne; any &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param types the types
	 * @param statuses the statuses
	 * @return the matching account entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountEntry> findByRAEI_NotT_S(long redirectAccountEntryId,
		int[] types, int[] statuses) throws SystemException {
		return findByRAEI_NotT_S(redirectAccountEntryId, types, statuses,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account entries where redirectAccountEntryId = &#63; and type &ne; any &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param types the types
	 * @param statuses the statuses
	 * @param start the lower bound of the range of account entries
	 * @param end the upper bound of the range of account entries (not inclusive)
	 * @return the range of matching account entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountEntry> findByRAEI_NotT_S(long redirectAccountEntryId,
		int[] types, int[] statuses, int start, int end)
		throws SystemException {
		return findByRAEI_NotT_S(redirectAccountEntryId, types, statuses,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the account entries where redirectAccountEntryId = &#63; and type &ne; any &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param types the types
	 * @param statuses the statuses
	 * @param start the lower bound of the range of account entries
	 * @param end the upper bound of the range of account entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountEntry> findByRAEI_NotT_S(long redirectAccountEntryId,
		int[] types, int[] statuses, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_RAEI_NOTT_S;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderArgs = new Object[] {
					redirectAccountEntryId, StringUtil.merge(types),
					StringUtil.merge(statuses)
				};
		}
		else {
			finderArgs = new Object[] {
					redirectAccountEntryId, StringUtil.merge(types),
					StringUtil.merge(statuses),
					
					start, end, orderByComparator
				};
		}

		List<AccountEntry> list = (List<AccountEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AccountEntry accountEntry : list) {
				if ((redirectAccountEntryId != accountEntry.getRedirectAccountEntryId()) ||
						!ArrayUtil.contains(types, accountEntry.getType()) ||
						!ArrayUtil.contains(statuses, accountEntry.getStatus())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_ACCOUNTENTRY_WHERE);

			boolean conjunctionable = false;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_RAEI_NOTT_S_REDIRECTACCOUNTENTRYID_5);

			conjunctionable = true;

			if ((types == null) || (types.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < types.length; i++) {
					query.append(_FINDER_COLUMN_RAEI_NOTT_S_TYPE_5);

					if ((i + 1) < types.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if ((statuses == null) || (statuses.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < statuses.length; i++) {
					query.append(_FINDER_COLUMN_RAEI_NOTT_S_STATUS_5);

					if ((i + 1) < statuses.length) {
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

			else {
				query.append(AccountEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(redirectAccountEntryId);

				if (types != null) {
					qPos.add(types);
				}

				if (statuses != null) {
					qPos.add(statuses);
				}

				list = (List<AccountEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns all the account entries where name LIKE &#63; and code LIKE &#63; and redirectAccountEntryId = &#63;.
	 *
	 * @param name the name
	 * @param code the code
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @return the matching account entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountEntry> findByN_C_RAEI(String name, String code,
		long redirectAccountEntryId) throws SystemException {
		return findByN_C_RAEI(name, code, redirectAccountEntryId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account entries where name LIKE &#63; and code LIKE &#63; and redirectAccountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param name the name
	 * @param code the code
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param start the lower bound of the range of account entries
	 * @param end the upper bound of the range of account entries (not inclusive)
	 * @return the range of matching account entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountEntry> findByN_C_RAEI(String name, String code,
		long redirectAccountEntryId, int start, int end)
		throws SystemException {
		return findByN_C_RAEI(name, code, redirectAccountEntryId, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the account entries where name LIKE &#63; and code LIKE &#63; and redirectAccountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param name the name
	 * @param code the code
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param start the lower bound of the range of account entries
	 * @param end the upper bound of the range of account entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountEntry> findByN_C_RAEI(String name, String code,
		long redirectAccountEntryId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_N_C_RAEI;
		finderArgs = new Object[] {
				name, code, redirectAccountEntryId,
				
				start, end, orderByComparator
			};

		List<AccountEntry> list = (List<AccountEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AccountEntry accountEntry : list) {
				if (!Validator.equals(name, accountEntry.getName()) ||
						!Validator.equals(code, accountEntry.getCode()) ||
						(redirectAccountEntryId != accountEntry.getRedirectAccountEntryId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_ACCOUNTENTRY_WHERE);

			if (name == null) {
				query.append(_FINDER_COLUMN_N_C_RAEI_NAME_1);
			}
			else {
				if (name.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_N_C_RAEI_NAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_N_C_RAEI_NAME_2);
				}
			}

			if (code == null) {
				query.append(_FINDER_COLUMN_N_C_RAEI_CODE_1);
			}
			else {
				if (code.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_N_C_RAEI_CODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_N_C_RAEI_CODE_2);
				}
			}

			query.append(_FINDER_COLUMN_N_C_RAEI_REDIRECTACCOUNTENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(AccountEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (name != null) {
					qPos.add(name);
				}

				if (code != null) {
					qPos.add(code);
				}

				qPos.add(redirectAccountEntryId);

				list = (List<AccountEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first account entry in the ordered set where name LIKE &#63; and code LIKE &#63; and redirectAccountEntryId = &#63;.
	 *
	 * @param name the name
	 * @param code the code
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account entry
	 * @throws com.liferay.osb.NoSuchAccountEntryException if a matching account entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEntry findByN_C_RAEI_First(String name, String code,
		long redirectAccountEntryId, OrderByComparator orderByComparator)
		throws NoSuchAccountEntryException, SystemException {
		AccountEntry accountEntry = fetchByN_C_RAEI_First(name, code,
				redirectAccountEntryId, orderByComparator);

		if (accountEntry != null) {
			return accountEntry;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("name=");
		msg.append(name);

		msg.append(", code=");
		msg.append(code);

		msg.append(", redirectAccountEntryId=");
		msg.append(redirectAccountEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountEntryException(msg.toString());
	}

	/**
	 * Returns the first account entry in the ordered set where name LIKE &#63; and code LIKE &#63; and redirectAccountEntryId = &#63;.
	 *
	 * @param name the name
	 * @param code the code
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account entry, or <code>null</code> if a matching account entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEntry fetchByN_C_RAEI_First(String name, String code,
		long redirectAccountEntryId, OrderByComparator orderByComparator)
		throws SystemException {
		List<AccountEntry> list = findByN_C_RAEI(name, code,
				redirectAccountEntryId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last account entry in the ordered set where name LIKE &#63; and code LIKE &#63; and redirectAccountEntryId = &#63;.
	 *
	 * @param name the name
	 * @param code the code
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account entry
	 * @throws com.liferay.osb.NoSuchAccountEntryException if a matching account entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEntry findByN_C_RAEI_Last(String name, String code,
		long redirectAccountEntryId, OrderByComparator orderByComparator)
		throws NoSuchAccountEntryException, SystemException {
		AccountEntry accountEntry = fetchByN_C_RAEI_Last(name, code,
				redirectAccountEntryId, orderByComparator);

		if (accountEntry != null) {
			return accountEntry;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("name=");
		msg.append(name);

		msg.append(", code=");
		msg.append(code);

		msg.append(", redirectAccountEntryId=");
		msg.append(redirectAccountEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountEntryException(msg.toString());
	}

	/**
	 * Returns the last account entry in the ordered set where name LIKE &#63; and code LIKE &#63; and redirectAccountEntryId = &#63;.
	 *
	 * @param name the name
	 * @param code the code
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account entry, or <code>null</code> if a matching account entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEntry fetchByN_C_RAEI_Last(String name, String code,
		long redirectAccountEntryId, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByN_C_RAEI(name, code, redirectAccountEntryId);

		List<AccountEntry> list = findByN_C_RAEI(name, code,
				redirectAccountEntryId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the account entries before and after the current account entry in the ordered set where name LIKE &#63; and code LIKE &#63; and redirectAccountEntryId = &#63;.
	 *
	 * @param accountEntryId the primary key of the current account entry
	 * @param name the name
	 * @param code the code
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account entry
	 * @throws com.liferay.osb.NoSuchAccountEntryException if a account entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEntry[] findByN_C_RAEI_PrevAndNext(long accountEntryId,
		String name, String code, long redirectAccountEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchAccountEntryException, SystemException {
		AccountEntry accountEntry = findByPrimaryKey(accountEntryId);

		Session session = null;

		try {
			session = openSession();

			AccountEntry[] array = new AccountEntryImpl[3];

			array[0] = getByN_C_RAEI_PrevAndNext(session, accountEntry, name,
					code, redirectAccountEntryId, orderByComparator, true);

			array[1] = accountEntry;

			array[2] = getByN_C_RAEI_PrevAndNext(session, accountEntry, name,
					code, redirectAccountEntryId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AccountEntry getByN_C_RAEI_PrevAndNext(Session session,
		AccountEntry accountEntry, String name, String code,
		long redirectAccountEntryId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ACCOUNTENTRY_WHERE);

		if (name == null) {
			query.append(_FINDER_COLUMN_N_C_RAEI_NAME_1);
		}
		else {
			if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_N_C_RAEI_NAME_3);
			}
			else {
				query.append(_FINDER_COLUMN_N_C_RAEI_NAME_2);
			}
		}

		if (code == null) {
			query.append(_FINDER_COLUMN_N_C_RAEI_CODE_1);
		}
		else {
			if (code.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_N_C_RAEI_CODE_3);
			}
			else {
				query.append(_FINDER_COLUMN_N_C_RAEI_CODE_2);
			}
		}

		query.append(_FINDER_COLUMN_N_C_RAEI_REDIRECTACCOUNTENTRYID_2);

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
			query.append(AccountEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (name != null) {
			qPos.add(name);
		}

		if (code != null) {
			qPos.add(code);
		}

		qPos.add(redirectAccountEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(accountEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AccountEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the account entries.
	 *
	 * @return the account entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountEntry> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of account entries
	 * @param end the upper bound of the range of account entries (not inclusive)
	 * @return the range of account entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountEntry> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the account entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of account entries
	 * @param end the upper bound of the range of account entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of account entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountEntry> findAll(int start, int end,
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

		List<AccountEntry> list = (List<AccountEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_ACCOUNTENTRY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ACCOUNTENTRY.concat(AccountEntryModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<AccountEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<AccountEntry>)QueryUtil.list(q, getDialect(),
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
	 * Removes the account entry where corpProjectId = &#63; from the database.
	 *
	 * @param corpProjectId the corp project ID
	 * @return the account entry that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEntry removeByCorpProjectId(long corpProjectId)
		throws NoSuchAccountEntryException, SystemException {
		AccountEntry accountEntry = findByCorpProjectId(corpProjectId);

		return remove(accountEntry);
	}

	/**
	 * Removes all the account entries where name = &#63; from the database.
	 *
	 * @param name the name
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByName(String name) throws SystemException {
		for (AccountEntry accountEntry : findByName(name)) {
			remove(accountEntry);
		}
	}

	/**
	 * Removes the account entry where code = &#63; from the database.
	 *
	 * @param code the code
	 * @return the account entry that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEntry removeByCode(String code)
		throws NoSuchAccountEntryException, SystemException {
		AccountEntry accountEntry = findByCode(code);

		return remove(accountEntry);
	}

	/**
	 * Removes all the account entries where redirectAccountEntryId = &#63; from the database.
	 *
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByRedirectAccountEntryId(long redirectAccountEntryId)
		throws SystemException {
		for (AccountEntry accountEntry : findByRedirectAccountEntryId(
				redirectAccountEntryId)) {
			remove(accountEntry);
		}
	}

	/**
	 * Removes all the account entries where partnerEntryId = &#63; from the database.
	 *
	 * @param partnerEntryId the partner entry ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByPartnerEntryId(long partnerEntryId)
		throws SystemException {
		for (AccountEntry accountEntry : findByPartnerEntryId(partnerEntryId)) {
			remove(accountEntry);
		}
	}

	/**
	 * Removes all the account entries where highestSupportResponseId = &#63; from the database.
	 *
	 * @param highestSupportResponseId the highest support response ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByHighestSupportResponseId(long highestSupportResponseId)
		throws SystemException {
		for (AccountEntry accountEntry : findByHighestSupportResponseId(
				highestSupportResponseId)) {
			remove(accountEntry);
		}
	}

	/**
	 * Removes all the account entries where userId = &#63; and type = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param type the type
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByU_T(long userId, int type) throws SystemException {
		for (AccountEntry accountEntry : findByU_T(userId, type)) {
			remove(accountEntry);
		}
	}

	/**
	 * Removes all the account entries where redirectAccountEntryId = &#63; and status = &#63; from the database.
	 *
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param status the status
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByRAEI_S(long redirectAccountEntryId, int status)
		throws SystemException {
		for (AccountEntry accountEntry : findByRAEI_S(redirectAccountEntryId,
				status)) {
			remove(accountEntry);
		}
	}

	/**
	 * Removes all the account entries where redirectAccountEntryId = &#63; and type &ne; &#63; and status = &#63; from the database.
	 *
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param type the type
	 * @param status the status
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByRAEI_NotT_S(long redirectAccountEntryId, int type,
		int status) throws SystemException {
		for (AccountEntry accountEntry : findByRAEI_NotT_S(
				redirectAccountEntryId, type, status)) {
			remove(accountEntry);
		}
	}

	/**
	 * Removes all the account entries where name LIKE &#63; and code LIKE &#63; and redirectAccountEntryId = &#63; from the database.
	 *
	 * @param name the name
	 * @param code the code
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByN_C_RAEI(String name, String code,
		long redirectAccountEntryId) throws SystemException {
		for (AccountEntry accountEntry : findByN_C_RAEI(name, code,
				redirectAccountEntryId)) {
			remove(accountEntry);
		}
	}

	/**
	 * Removes all the account entries from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (AccountEntry accountEntry : findAll()) {
			remove(accountEntry);
		}
	}

	/**
	 * Returns the number of account entries where corpProjectId = &#63;.
	 *
	 * @param corpProjectId the corp project ID
	 * @return the number of matching account entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByCorpProjectId(long corpProjectId)
		throws SystemException {
		Object[] finderArgs = new Object[] { corpProjectId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_CORPPROJECTID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ACCOUNTENTRY_WHERE);

			query.append(_FINDER_COLUMN_CORPPROJECTID_CORPPROJECTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(corpProjectId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_CORPPROJECTID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of account entries where name = &#63;.
	 *
	 * @param name the name
	 * @return the number of matching account entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByName(String name) throws SystemException {
		Object[] finderArgs = new Object[] { name };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_NAME,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ACCOUNTENTRY_WHERE);

			if (name == null) {
				query.append(_FINDER_COLUMN_NAME_NAME_1);
			}
			else {
				if (name.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_NAME_NAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_NAME_NAME_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_NAME,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of account entries where code = &#63;.
	 *
	 * @param code the code
	 * @return the number of matching account entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByCode(String code) throws SystemException {
		Object[] finderArgs = new Object[] { code };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_CODE,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ACCOUNTENTRY_WHERE);

			if (code == null) {
				query.append(_FINDER_COLUMN_CODE_CODE_1);
			}
			else {
				if (code.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_CODE_CODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_CODE_CODE_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (code != null) {
					qPos.add(code);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_CODE,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of account entries where redirectAccountEntryId = &#63;.
	 *
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @return the number of matching account entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByRedirectAccountEntryId(long redirectAccountEntryId)
		throws SystemException {
		Object[] finderArgs = new Object[] { redirectAccountEntryId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_REDIRECTACCOUNTENTRYID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ACCOUNTENTRY_WHERE);

			query.append(_FINDER_COLUMN_REDIRECTACCOUNTENTRYID_REDIRECTACCOUNTENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(redirectAccountEntryId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_REDIRECTACCOUNTENTRYID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of account entries where partnerEntryId = &#63;.
	 *
	 * @param partnerEntryId the partner entry ID
	 * @return the number of matching account entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByPartnerEntryId(long partnerEntryId)
		throws SystemException {
		Object[] finderArgs = new Object[] { partnerEntryId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_PARTNERENTRYID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ACCOUNTENTRY_WHERE);

			query.append(_FINDER_COLUMN_PARTNERENTRYID_PARTNERENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(partnerEntryId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_PARTNERENTRYID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of account entries where highestSupportResponseId = &#63;.
	 *
	 * @param highestSupportResponseId the highest support response ID
	 * @return the number of matching account entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByHighestSupportResponseId(long highestSupportResponseId)
		throws SystemException {
		Object[] finderArgs = new Object[] { highestSupportResponseId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_HIGHESTSUPPORTRESPONSEID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ACCOUNTENTRY_WHERE);

			query.append(_FINDER_COLUMN_HIGHESTSUPPORTRESPONSEID_HIGHESTSUPPORTRESPONSEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(highestSupportResponseId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_HIGHESTSUPPORTRESPONSEID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of account entries where userId = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param type the type
	 * @return the number of matching account entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByU_T(long userId, int type) throws SystemException {
		Object[] finderArgs = new Object[] { userId, type };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_U_T,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ACCOUNTENTRY_WHERE);

			query.append(_FINDER_COLUMN_U_T_USERID_2);

			query.append(_FINDER_COLUMN_U_T_TYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(type);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_U_T, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of account entries where redirectAccountEntryId = &#63; and status = &#63;.
	 *
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param status the status
	 * @return the number of matching account entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByRAEI_S(long redirectAccountEntryId, int status)
		throws SystemException {
		Object[] finderArgs = new Object[] { redirectAccountEntryId, status };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_RAEI_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ACCOUNTENTRY_WHERE);

			query.append(_FINDER_COLUMN_RAEI_S_REDIRECTACCOUNTENTRYID_2);

			query.append(_FINDER_COLUMN_RAEI_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(redirectAccountEntryId);

				qPos.add(status);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_RAEI_S,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of account entries where redirectAccountEntryId = &#63; and status = any &#63;.
	 *
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param statuses the statuses
	 * @return the number of matching account entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByRAEI_S(long redirectAccountEntryId, int[] statuses)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				redirectAccountEntryId, StringUtil.merge(statuses)
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_RAEI_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_ACCOUNTENTRY_WHERE);

			boolean conjunctionable = false;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_RAEI_S_REDIRECTACCOUNTENTRYID_5);

			conjunctionable = true;

			if ((statuses == null) || (statuses.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < statuses.length; i++) {
					query.append(_FINDER_COLUMN_RAEI_S_STATUS_5);

					if ((i + 1) < statuses.length) {
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

				qPos.add(redirectAccountEntryId);

				if (statuses != null) {
					qPos.add(statuses);
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

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_RAEI_S,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of account entries where redirectAccountEntryId = &#63; and type &ne; &#63; and status = &#63;.
	 *
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param type the type
	 * @param status the status
	 * @return the number of matching account entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByRAEI_NotT_S(long redirectAccountEntryId, int type,
		int status) throws SystemException {
		Object[] finderArgs = new Object[] { redirectAccountEntryId, type, status };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_RAEI_NOTT_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_ACCOUNTENTRY_WHERE);

			query.append(_FINDER_COLUMN_RAEI_NOTT_S_REDIRECTACCOUNTENTRYID_2);

			query.append(_FINDER_COLUMN_RAEI_NOTT_S_TYPE_2);

			query.append(_FINDER_COLUMN_RAEI_NOTT_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(redirectAccountEntryId);

				qPos.add(type);

				qPos.add(status);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_RAEI_NOTT_S,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of account entries where redirectAccountEntryId = &#63; and type &ne; any &#63; and status = any &#63;.
	 *
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param types the types
	 * @param statuses the statuses
	 * @return the number of matching account entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByRAEI_NotT_S(long redirectAccountEntryId, int[] types,
		int[] statuses) throws SystemException {
		Object[] finderArgs = new Object[] {
				redirectAccountEntryId, StringUtil.merge(types),
				StringUtil.merge(statuses)
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_RAEI_NOTT_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_ACCOUNTENTRY_WHERE);

			boolean conjunctionable = false;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_RAEI_NOTT_S_REDIRECTACCOUNTENTRYID_5);

			conjunctionable = true;

			if ((types == null) || (types.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < types.length; i++) {
					query.append(_FINDER_COLUMN_RAEI_NOTT_S_TYPE_5);

					if ((i + 1) < types.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if ((statuses == null) || (statuses.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < statuses.length; i++) {
					query.append(_FINDER_COLUMN_RAEI_NOTT_S_STATUS_5);

					if ((i + 1) < statuses.length) {
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

				qPos.add(redirectAccountEntryId);

				if (types != null) {
					qPos.add(types);
				}

				if (statuses != null) {
					qPos.add(statuses);
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

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_RAEI_NOTT_S,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of account entries where name LIKE &#63; and code LIKE &#63; and redirectAccountEntryId = &#63;.
	 *
	 * @param name the name
	 * @param code the code
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @return the number of matching account entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByN_C_RAEI(String name, String code,
		long redirectAccountEntryId) throws SystemException {
		Object[] finderArgs = new Object[] { name, code, redirectAccountEntryId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_N_C_RAEI,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_ACCOUNTENTRY_WHERE);

			if (name == null) {
				query.append(_FINDER_COLUMN_N_C_RAEI_NAME_1);
			}
			else {
				if (name.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_N_C_RAEI_NAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_N_C_RAEI_NAME_2);
				}
			}

			if (code == null) {
				query.append(_FINDER_COLUMN_N_C_RAEI_CODE_1);
			}
			else {
				if (code.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_N_C_RAEI_CODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_N_C_RAEI_CODE_2);
				}
			}

			query.append(_FINDER_COLUMN_N_C_RAEI_REDIRECTACCOUNTENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (name != null) {
					qPos.add(name);
				}

				if (code != null) {
					qPos.add(code);
				}

				qPos.add(redirectAccountEntryId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_N_C_RAEI,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of account entries.
	 *
	 * @return the number of account entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ACCOUNTENTRY);

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
	 * Returns all the support regions associated with the account entry.
	 *
	 * @param pk the primary key of the account entry
	 * @return the support regions associated with the account entry
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.liferay.osb.model.SupportRegion> getSupportRegions(long pk)
		throws SystemException {
		return getSupportRegions(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	/**
	 * Returns a range of all the support regions associated with the account entry.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param pk the primary key of the account entry
	 * @param start the lower bound of the range of account entries
	 * @param end the upper bound of the range of account entries (not inclusive)
	 * @return the range of support regions associated with the account entry
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.liferay.osb.model.SupportRegion> getSupportRegions(
		long pk, int start, int end) throws SystemException {
		return getSupportRegions(pk, start, end, null);
	}

	public static final FinderPath FINDER_PATH_GET_SUPPORTREGIONS = new FinderPath(com.liferay.osb.model.impl.SupportRegionModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryModelImpl.FINDER_CACHE_ENABLED_OSB_ACCOUNTENTRIES_SUPPORTREGIONS,
			com.liferay.osb.model.impl.SupportRegionImpl.class,
			AccountEntryModelImpl.MAPPING_TABLE_OSB_ACCOUNTENTRIES_SUPPORTREGIONS_NAME,
			"getSupportRegions",
			new String[] {
				Long.class.getName(), "java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});

	static {
		FINDER_PATH_GET_SUPPORTREGIONS.setCacheKeyGeneratorCacheName(null);
	}

	/**
	 * Returns an ordered range of all the support regions associated with the account entry.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param pk the primary key of the account entry
	 * @param start the lower bound of the range of account entries
	 * @param end the upper bound of the range of account entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of support regions associated with the account entry
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.liferay.osb.model.SupportRegion> getSupportRegions(
		long pk, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] { pk, start, end, orderByComparator };

		List<com.liferay.osb.model.SupportRegion> list = (List<com.liferay.osb.model.SupportRegion>)FinderCacheUtil.getResult(FINDER_PATH_GET_SUPPORTREGIONS,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				String sql = null;

				if (orderByComparator != null) {
					sql = _SQL_GETSUPPORTREGIONS.concat(ORDER_BY_CLAUSE)
												.concat(orderByComparator.getOrderBy());
				}
				else {
					sql = _SQL_GETSUPPORTREGIONS.concat(com.liferay.osb.model.impl.SupportRegionModelImpl.ORDER_BY_SQL);
				}

				SQLQuery q = session.createSQLQuery(sql);

				q.addEntity("OSB_SupportRegion",
					com.liferay.osb.model.impl.SupportRegionImpl.class);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(pk);

				list = (List<com.liferay.osb.model.SupportRegion>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_GET_SUPPORTREGIONS,
						finderArgs);
				}
				else {
					supportRegionPersistence.cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_GET_SUPPORTREGIONS,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	public static final FinderPath FINDER_PATH_GET_SUPPORTREGIONS_SIZE = new FinderPath(com.liferay.osb.model.impl.SupportRegionModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryModelImpl.FINDER_CACHE_ENABLED_OSB_ACCOUNTENTRIES_SUPPORTREGIONS,
			Long.class,
			AccountEntryModelImpl.MAPPING_TABLE_OSB_ACCOUNTENTRIES_SUPPORTREGIONS_NAME,
			"getSupportRegionsSize", new String[] { Long.class.getName() });

	static {
		FINDER_PATH_GET_SUPPORTREGIONS_SIZE.setCacheKeyGeneratorCacheName(null);
	}

	/**
	 * Returns the number of support regions associated with the account entry.
	 *
	 * @param pk the primary key of the account entry
	 * @return the number of support regions associated with the account entry
	 * @throws SystemException if a system exception occurred
	 */
	public int getSupportRegionsSize(long pk) throws SystemException {
		Object[] finderArgs = new Object[] { pk };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_GET_SUPPORTREGIONS_SIZE,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				SQLQuery q = session.createSQLQuery(_SQL_GETSUPPORTREGIONSSIZE);

				q.addScalar(COUNT_COLUMN_NAME,
					com.liferay.portal.kernel.dao.orm.Type.LONG);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(pk);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_GET_SUPPORTREGIONS_SIZE,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public static final FinderPath FINDER_PATH_CONTAINS_SUPPORTREGION = new FinderPath(com.liferay.osb.model.impl.SupportRegionModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryModelImpl.FINDER_CACHE_ENABLED_OSB_ACCOUNTENTRIES_SUPPORTREGIONS,
			Boolean.class,
			AccountEntryModelImpl.MAPPING_TABLE_OSB_ACCOUNTENTRIES_SUPPORTREGIONS_NAME,
			"containsSupportRegion",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns <code>true</code> if the support region is associated with the account entry.
	 *
	 * @param pk the primary key of the account entry
	 * @param supportRegionPK the primary key of the support region
	 * @return <code>true</code> if the support region is associated with the account entry; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	public boolean containsSupportRegion(long pk, long supportRegionPK)
		throws SystemException {
		Object[] finderArgs = new Object[] { pk, supportRegionPK };

		Boolean value = (Boolean)FinderCacheUtil.getResult(FINDER_PATH_CONTAINS_SUPPORTREGION,
				finderArgs, this);

		if (value == null) {
			try {
				value = Boolean.valueOf(containsSupportRegion.contains(pk,
							supportRegionPK));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (value == null) {
					value = Boolean.FALSE;
				}

				FinderCacheUtil.putResult(FINDER_PATH_CONTAINS_SUPPORTREGION,
					finderArgs, value);
			}
		}

		return value.booleanValue();
	}

	/**
	 * Returns <code>true</code> if the account entry has any support regions associated with it.
	 *
	 * @param pk the primary key of the account entry to check for associations with support regions
	 * @return <code>true</code> if the account entry has any support regions associated with it; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	public boolean containsSupportRegions(long pk) throws SystemException {
		if (getSupportRegionsSize(pk) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Adds an association between the account entry and the support region. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the account entry
	 * @param supportRegionPK the primary key of the support region
	 * @throws SystemException if a system exception occurred
	 */
	public void addSupportRegion(long pk, long supportRegionPK)
		throws SystemException {
		try {
			addSupportRegion.add(pk, supportRegionPK);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(AccountEntryModelImpl.MAPPING_TABLE_OSB_ACCOUNTENTRIES_SUPPORTREGIONS_NAME);
		}
	}

	/**
	 * Adds an association between the account entry and the support region. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the account entry
	 * @param supportRegion the support region
	 * @throws SystemException if a system exception occurred
	 */
	public void addSupportRegion(long pk,
		com.liferay.osb.model.SupportRegion supportRegion)
		throws SystemException {
		try {
			addSupportRegion.add(pk, supportRegion.getPrimaryKey());
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(AccountEntryModelImpl.MAPPING_TABLE_OSB_ACCOUNTENTRIES_SUPPORTREGIONS_NAME);
		}
	}

	/**
	 * Adds an association between the account entry and the support regions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the account entry
	 * @param supportRegionPKs the primary keys of the support regions
	 * @throws SystemException if a system exception occurred
	 */
	public void addSupportRegions(long pk, long[] supportRegionPKs)
		throws SystemException {
		try {
			for (long supportRegionPK : supportRegionPKs) {
				addSupportRegion.add(pk, supportRegionPK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(AccountEntryModelImpl.MAPPING_TABLE_OSB_ACCOUNTENTRIES_SUPPORTREGIONS_NAME);
		}
	}

	/**
	 * Adds an association between the account entry and the support regions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the account entry
	 * @param supportRegions the support regions
	 * @throws SystemException if a system exception occurred
	 */
	public void addSupportRegions(long pk,
		List<com.liferay.osb.model.SupportRegion> supportRegions)
		throws SystemException {
		try {
			for (com.liferay.osb.model.SupportRegion supportRegion : supportRegions) {
				addSupportRegion.add(pk, supportRegion.getPrimaryKey());
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(AccountEntryModelImpl.MAPPING_TABLE_OSB_ACCOUNTENTRIES_SUPPORTREGIONS_NAME);
		}
	}

	/**
	 * Clears all associations between the account entry and its support regions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the account entry to clear the associated support regions from
	 * @throws SystemException if a system exception occurred
	 */
	public void clearSupportRegions(long pk) throws SystemException {
		try {
			clearSupportRegions.clear(pk);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(AccountEntryModelImpl.MAPPING_TABLE_OSB_ACCOUNTENTRIES_SUPPORTREGIONS_NAME);
		}
	}

	/**
	 * Removes the association between the account entry and the support region. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the account entry
	 * @param supportRegionPK the primary key of the support region
	 * @throws SystemException if a system exception occurred
	 */
	public void removeSupportRegion(long pk, long supportRegionPK)
		throws SystemException {
		try {
			removeSupportRegion.remove(pk, supportRegionPK);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(AccountEntryModelImpl.MAPPING_TABLE_OSB_ACCOUNTENTRIES_SUPPORTREGIONS_NAME);
		}
	}

	/**
	 * Removes the association between the account entry and the support region. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the account entry
	 * @param supportRegion the support region
	 * @throws SystemException if a system exception occurred
	 */
	public void removeSupportRegion(long pk,
		com.liferay.osb.model.SupportRegion supportRegion)
		throws SystemException {
		try {
			removeSupportRegion.remove(pk, supportRegion.getPrimaryKey());
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(AccountEntryModelImpl.MAPPING_TABLE_OSB_ACCOUNTENTRIES_SUPPORTREGIONS_NAME);
		}
	}

	/**
	 * Removes the association between the account entry and the support regions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the account entry
	 * @param supportRegionPKs the primary keys of the support regions
	 * @throws SystemException if a system exception occurred
	 */
	public void removeSupportRegions(long pk, long[] supportRegionPKs)
		throws SystemException {
		try {
			for (long supportRegionPK : supportRegionPKs) {
				removeSupportRegion.remove(pk, supportRegionPK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(AccountEntryModelImpl.MAPPING_TABLE_OSB_ACCOUNTENTRIES_SUPPORTREGIONS_NAME);
		}
	}

	/**
	 * Removes the association between the account entry and the support regions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the account entry
	 * @param supportRegions the support regions
	 * @throws SystemException if a system exception occurred
	 */
	public void removeSupportRegions(long pk,
		List<com.liferay.osb.model.SupportRegion> supportRegions)
		throws SystemException {
		try {
			for (com.liferay.osb.model.SupportRegion supportRegion : supportRegions) {
				removeSupportRegion.remove(pk, supportRegion.getPrimaryKey());
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(AccountEntryModelImpl.MAPPING_TABLE_OSB_ACCOUNTENTRIES_SUPPORTREGIONS_NAME);
		}
	}

	/**
	 * Sets the support regions associated with the account entry, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the account entry
	 * @param supportRegionPKs the primary keys of the support regions to be associated with the account entry
	 * @throws SystemException if a system exception occurred
	 */
	public void setSupportRegions(long pk, long[] supportRegionPKs)
		throws SystemException {
		try {
			Set<Long> supportRegionPKSet = SetUtil.fromArray(supportRegionPKs);

			List<com.liferay.osb.model.SupportRegion> supportRegions = getSupportRegions(pk);

			for (com.liferay.osb.model.SupportRegion supportRegion : supportRegions) {
				if (!supportRegionPKSet.remove(supportRegion.getPrimaryKey())) {
					removeSupportRegion.remove(pk, supportRegion.getPrimaryKey());
				}
			}

			for (Long supportRegionPK : supportRegionPKSet) {
				addSupportRegion.add(pk, supportRegionPK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(AccountEntryModelImpl.MAPPING_TABLE_OSB_ACCOUNTENTRIES_SUPPORTREGIONS_NAME);
		}
	}

	/**
	 * Sets the support regions associated with the account entry, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the account entry
	 * @param supportRegions the support regions to be associated with the account entry
	 * @throws SystemException if a system exception occurred
	 */
	public void setSupportRegions(long pk,
		List<com.liferay.osb.model.SupportRegion> supportRegions)
		throws SystemException {
		try {
			long[] supportRegionPKs = new long[supportRegions.size()];

			for (int i = 0; i < supportRegions.size(); i++) {
				com.liferay.osb.model.SupportRegion supportRegion = supportRegions.get(i);

				supportRegionPKs[i] = supportRegion.getPrimaryKey();
			}

			setSupportRegions(pk, supportRegionPKs);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(AccountEntryModelImpl.MAPPING_TABLE_OSB_ACCOUNTENTRIES_SUPPORTREGIONS_NAME);
		}
	}

	/**
	 * Returns all the support teams associated with the account entry.
	 *
	 * @param pk the primary key of the account entry
	 * @return the support teams associated with the account entry
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.liferay.osb.model.SupportTeam> getSupportTeams(long pk)
		throws SystemException {
		return getSupportTeams(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	/**
	 * Returns a range of all the support teams associated with the account entry.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param pk the primary key of the account entry
	 * @param start the lower bound of the range of account entries
	 * @param end the upper bound of the range of account entries (not inclusive)
	 * @return the range of support teams associated with the account entry
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.liferay.osb.model.SupportTeam> getSupportTeams(long pk,
		int start, int end) throws SystemException {
		return getSupportTeams(pk, start, end, null);
	}

	public static final FinderPath FINDER_PATH_GET_SUPPORTTEAMS = new FinderPath(com.liferay.osb.model.impl.SupportTeamModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryModelImpl.FINDER_CACHE_ENABLED_OSB_ACCOUNTENTRIES_SUPPORTTEAMS,
			com.liferay.osb.model.impl.SupportTeamImpl.class,
			AccountEntryModelImpl.MAPPING_TABLE_OSB_ACCOUNTENTRIES_SUPPORTTEAMS_NAME,
			"getSupportTeams",
			new String[] {
				Long.class.getName(), "java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});

	static {
		FINDER_PATH_GET_SUPPORTTEAMS.setCacheKeyGeneratorCacheName(null);
	}

	/**
	 * Returns an ordered range of all the support teams associated with the account entry.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param pk the primary key of the account entry
	 * @param start the lower bound of the range of account entries
	 * @param end the upper bound of the range of account entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of support teams associated with the account entry
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.liferay.osb.model.SupportTeam> getSupportTeams(long pk,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] { pk, start, end, orderByComparator };

		List<com.liferay.osb.model.SupportTeam> list = (List<com.liferay.osb.model.SupportTeam>)FinderCacheUtil.getResult(FINDER_PATH_GET_SUPPORTTEAMS,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				String sql = null;

				if (orderByComparator != null) {
					sql = _SQL_GETSUPPORTTEAMS.concat(ORDER_BY_CLAUSE)
											  .concat(orderByComparator.getOrderBy());
				}
				else {
					sql = _SQL_GETSUPPORTTEAMS.concat(com.liferay.osb.model.impl.SupportTeamModelImpl.ORDER_BY_SQL);
				}

				SQLQuery q = session.createSQLQuery(sql);

				q.addEntity("OSB_SupportTeam",
					com.liferay.osb.model.impl.SupportTeamImpl.class);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(pk);

				list = (List<com.liferay.osb.model.SupportTeam>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_GET_SUPPORTTEAMS,
						finderArgs);
				}
				else {
					supportTeamPersistence.cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_GET_SUPPORTTEAMS,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	public static final FinderPath FINDER_PATH_GET_SUPPORTTEAMS_SIZE = new FinderPath(com.liferay.osb.model.impl.SupportTeamModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryModelImpl.FINDER_CACHE_ENABLED_OSB_ACCOUNTENTRIES_SUPPORTTEAMS,
			Long.class,
			AccountEntryModelImpl.MAPPING_TABLE_OSB_ACCOUNTENTRIES_SUPPORTTEAMS_NAME,
			"getSupportTeamsSize", new String[] { Long.class.getName() });

	static {
		FINDER_PATH_GET_SUPPORTTEAMS_SIZE.setCacheKeyGeneratorCacheName(null);
	}

	/**
	 * Returns the number of support teams associated with the account entry.
	 *
	 * @param pk the primary key of the account entry
	 * @return the number of support teams associated with the account entry
	 * @throws SystemException if a system exception occurred
	 */
	public int getSupportTeamsSize(long pk) throws SystemException {
		Object[] finderArgs = new Object[] { pk };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_GET_SUPPORTTEAMS_SIZE,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				SQLQuery q = session.createSQLQuery(_SQL_GETSUPPORTTEAMSSIZE);

				q.addScalar(COUNT_COLUMN_NAME,
					com.liferay.portal.kernel.dao.orm.Type.LONG);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(pk);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_GET_SUPPORTTEAMS_SIZE,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public static final FinderPath FINDER_PATH_CONTAINS_SUPPORTTEAM = new FinderPath(com.liferay.osb.model.impl.SupportTeamModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryModelImpl.FINDER_CACHE_ENABLED_OSB_ACCOUNTENTRIES_SUPPORTTEAMS,
			Boolean.class,
			AccountEntryModelImpl.MAPPING_TABLE_OSB_ACCOUNTENTRIES_SUPPORTTEAMS_NAME,
			"containsSupportTeam",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns <code>true</code> if the support team is associated with the account entry.
	 *
	 * @param pk the primary key of the account entry
	 * @param supportTeamPK the primary key of the support team
	 * @return <code>true</code> if the support team is associated with the account entry; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	public boolean containsSupportTeam(long pk, long supportTeamPK)
		throws SystemException {
		Object[] finderArgs = new Object[] { pk, supportTeamPK };

		Boolean value = (Boolean)FinderCacheUtil.getResult(FINDER_PATH_CONTAINS_SUPPORTTEAM,
				finderArgs, this);

		if (value == null) {
			try {
				value = Boolean.valueOf(containsSupportTeam.contains(pk,
							supportTeamPK));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (value == null) {
					value = Boolean.FALSE;
				}

				FinderCacheUtil.putResult(FINDER_PATH_CONTAINS_SUPPORTTEAM,
					finderArgs, value);
			}
		}

		return value.booleanValue();
	}

	/**
	 * Returns <code>true</code> if the account entry has any support teams associated with it.
	 *
	 * @param pk the primary key of the account entry to check for associations with support teams
	 * @return <code>true</code> if the account entry has any support teams associated with it; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	public boolean containsSupportTeams(long pk) throws SystemException {
		if (getSupportTeamsSize(pk) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Adds an association between the account entry and the support team. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the account entry
	 * @param supportTeamPK the primary key of the support team
	 * @throws SystemException if a system exception occurred
	 */
	public void addSupportTeam(long pk, long supportTeamPK)
		throws SystemException {
		try {
			addSupportTeam.add(pk, supportTeamPK);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(AccountEntryModelImpl.MAPPING_TABLE_OSB_ACCOUNTENTRIES_SUPPORTTEAMS_NAME);
		}
	}

	/**
	 * Adds an association between the account entry and the support team. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the account entry
	 * @param supportTeam the support team
	 * @throws SystemException if a system exception occurred
	 */
	public void addSupportTeam(long pk,
		com.liferay.osb.model.SupportTeam supportTeam)
		throws SystemException {
		try {
			addSupportTeam.add(pk, supportTeam.getPrimaryKey());
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(AccountEntryModelImpl.MAPPING_TABLE_OSB_ACCOUNTENTRIES_SUPPORTTEAMS_NAME);
		}
	}

	/**
	 * Adds an association between the account entry and the support teams. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the account entry
	 * @param supportTeamPKs the primary keys of the support teams
	 * @throws SystemException if a system exception occurred
	 */
	public void addSupportTeams(long pk, long[] supportTeamPKs)
		throws SystemException {
		try {
			for (long supportTeamPK : supportTeamPKs) {
				addSupportTeam.add(pk, supportTeamPK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(AccountEntryModelImpl.MAPPING_TABLE_OSB_ACCOUNTENTRIES_SUPPORTTEAMS_NAME);
		}
	}

	/**
	 * Adds an association between the account entry and the support teams. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the account entry
	 * @param supportTeams the support teams
	 * @throws SystemException if a system exception occurred
	 */
	public void addSupportTeams(long pk,
		List<com.liferay.osb.model.SupportTeam> supportTeams)
		throws SystemException {
		try {
			for (com.liferay.osb.model.SupportTeam supportTeam : supportTeams) {
				addSupportTeam.add(pk, supportTeam.getPrimaryKey());
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(AccountEntryModelImpl.MAPPING_TABLE_OSB_ACCOUNTENTRIES_SUPPORTTEAMS_NAME);
		}
	}

	/**
	 * Clears all associations between the account entry and its support teams. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the account entry to clear the associated support teams from
	 * @throws SystemException if a system exception occurred
	 */
	public void clearSupportTeams(long pk) throws SystemException {
		try {
			clearSupportTeams.clear(pk);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(AccountEntryModelImpl.MAPPING_TABLE_OSB_ACCOUNTENTRIES_SUPPORTTEAMS_NAME);
		}
	}

	/**
	 * Removes the association between the account entry and the support team. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the account entry
	 * @param supportTeamPK the primary key of the support team
	 * @throws SystemException if a system exception occurred
	 */
	public void removeSupportTeam(long pk, long supportTeamPK)
		throws SystemException {
		try {
			removeSupportTeam.remove(pk, supportTeamPK);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(AccountEntryModelImpl.MAPPING_TABLE_OSB_ACCOUNTENTRIES_SUPPORTTEAMS_NAME);
		}
	}

	/**
	 * Removes the association between the account entry and the support team. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the account entry
	 * @param supportTeam the support team
	 * @throws SystemException if a system exception occurred
	 */
	public void removeSupportTeam(long pk,
		com.liferay.osb.model.SupportTeam supportTeam)
		throws SystemException {
		try {
			removeSupportTeam.remove(pk, supportTeam.getPrimaryKey());
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(AccountEntryModelImpl.MAPPING_TABLE_OSB_ACCOUNTENTRIES_SUPPORTTEAMS_NAME);
		}
	}

	/**
	 * Removes the association between the account entry and the support teams. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the account entry
	 * @param supportTeamPKs the primary keys of the support teams
	 * @throws SystemException if a system exception occurred
	 */
	public void removeSupportTeams(long pk, long[] supportTeamPKs)
		throws SystemException {
		try {
			for (long supportTeamPK : supportTeamPKs) {
				removeSupportTeam.remove(pk, supportTeamPK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(AccountEntryModelImpl.MAPPING_TABLE_OSB_ACCOUNTENTRIES_SUPPORTTEAMS_NAME);
		}
	}

	/**
	 * Removes the association between the account entry and the support teams. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the account entry
	 * @param supportTeams the support teams
	 * @throws SystemException if a system exception occurred
	 */
	public void removeSupportTeams(long pk,
		List<com.liferay.osb.model.SupportTeam> supportTeams)
		throws SystemException {
		try {
			for (com.liferay.osb.model.SupportTeam supportTeam : supportTeams) {
				removeSupportTeam.remove(pk, supportTeam.getPrimaryKey());
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(AccountEntryModelImpl.MAPPING_TABLE_OSB_ACCOUNTENTRIES_SUPPORTTEAMS_NAME);
		}
	}

	/**
	 * Sets the support teams associated with the account entry, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the account entry
	 * @param supportTeamPKs the primary keys of the support teams to be associated with the account entry
	 * @throws SystemException if a system exception occurred
	 */
	public void setSupportTeams(long pk, long[] supportTeamPKs)
		throws SystemException {
		try {
			Set<Long> supportTeamPKSet = SetUtil.fromArray(supportTeamPKs);

			List<com.liferay.osb.model.SupportTeam> supportTeams = getSupportTeams(pk);

			for (com.liferay.osb.model.SupportTeam supportTeam : supportTeams) {
				if (!supportTeamPKSet.remove(supportTeam.getPrimaryKey())) {
					removeSupportTeam.remove(pk, supportTeam.getPrimaryKey());
				}
			}

			for (Long supportTeamPK : supportTeamPKSet) {
				addSupportTeam.add(pk, supportTeamPK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(AccountEntryModelImpl.MAPPING_TABLE_OSB_ACCOUNTENTRIES_SUPPORTTEAMS_NAME);
		}
	}

	/**
	 * Sets the support teams associated with the account entry, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the account entry
	 * @param supportTeams the support teams to be associated with the account entry
	 * @throws SystemException if a system exception occurred
	 */
	public void setSupportTeams(long pk,
		List<com.liferay.osb.model.SupportTeam> supportTeams)
		throws SystemException {
		try {
			long[] supportTeamPKs = new long[supportTeams.size()];

			for (int i = 0; i < supportTeams.size(); i++) {
				com.liferay.osb.model.SupportTeam supportTeam = supportTeams.get(i);

				supportTeamPKs[i] = supportTeam.getPrimaryKey();
			}

			setSupportTeams(pk, supportTeamPKs);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(AccountEntryModelImpl.MAPPING_TABLE_OSB_ACCOUNTENTRIES_SUPPORTTEAMS_NAME);
		}
	}

	/**
	 * Initializes the account entry persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.osb.model.AccountEntry")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<AccountEntry>> listenersList = new ArrayList<ModelListener<AccountEntry>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<AccountEntry>)InstanceFactory.newInstance(
							clazz.getClassLoader(), listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}

		containsSupportRegion = new ContainsSupportRegion();

		addSupportRegion = new AddSupportRegion();
		clearSupportRegions = new ClearSupportRegions();
		removeSupportRegion = new RemoveSupportRegion();

		containsSupportTeam = new ContainsSupportTeam();

		addSupportTeam = new AddSupportTeam();
		clearSupportTeams = new ClearSupportTeams();
		removeSupportTeam = new RemoveSupportTeam();
	}

	public void destroy() {
		EntityCacheUtil.removeCache(AccountEntryImpl.class.getName());
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
	@BeanReference(type = AddressPersistence.class)
	protected AddressPersistence addressPersistence;
	@BeanReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;
	@BeanReference(type = CountryPersistence.class)
	protected CountryPersistence countryPersistence;
	@BeanReference(type = ListTypePersistence.class)
	protected ListTypePersistence listTypePersistence;
	@BeanReference(type = OrganizationPersistence.class)
	protected OrganizationPersistence organizationPersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	@BeanReference(type = WorkflowInstanceLinkPersistence.class)
	protected WorkflowInstanceLinkPersistence workflowInstanceLinkPersistence;
	protected ContainsSupportRegion containsSupportRegion;
	protected AddSupportRegion addSupportRegion;
	protected ClearSupportRegions clearSupportRegions;
	protected RemoveSupportRegion removeSupportRegion;
	protected ContainsSupportTeam containsSupportTeam;
	protected AddSupportTeam addSupportTeam;
	protected ClearSupportTeams clearSupportTeams;
	protected RemoveSupportTeam removeSupportTeam;

	protected class ContainsSupportRegion {
		protected ContainsSupportRegion() {
			_mappingSqlQuery = MappingSqlQueryFactoryUtil.getMappingSqlQuery(getDataSource(),
					_SQL_CONTAINSSUPPORTREGION,
					new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT },
					RowMapper.COUNT);
		}

		protected boolean contains(long accountEntryId, long supportRegionId) {
			List<Integer> results = _mappingSqlQuery.execute(new Object[] {
						new Long(accountEntryId), new Long(supportRegionId)
					});

			if (results.size() > 0) {
				Integer count = results.get(0);

				if (count.intValue() > 0) {
					return true;
				}
			}

			return false;
		}

		private MappingSqlQuery<Integer> _mappingSqlQuery;
	}

	protected class AddSupportRegion {
		protected AddSupportRegion() {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"INSERT INTO OSB_AccountEntries_SupportRegions (accountEntryId, supportRegionId) VALUES (?, ?)",
					new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT });
		}

		protected void add(long accountEntryId, long supportRegionId)
			throws SystemException {
			if (!containsSupportRegion.contains(accountEntryId, supportRegionId)) {
				ModelListener<com.liferay.osb.model.SupportRegion>[] supportRegionListeners =
					supportRegionPersistence.getListeners();

				for (ModelListener<AccountEntry> listener : listeners) {
					listener.onBeforeAddAssociation(accountEntryId,
						com.liferay.osb.model.SupportRegion.class.getName(),
						supportRegionId);
				}

				for (ModelListener<com.liferay.osb.model.SupportRegion> listener : supportRegionListeners) {
					listener.onBeforeAddAssociation(supportRegionId,
						AccountEntry.class.getName(), accountEntryId);
				}

				_sqlUpdate.update(new Object[] {
						new Long(accountEntryId), new Long(supportRegionId)
					});

				for (ModelListener<AccountEntry> listener : listeners) {
					listener.onAfterAddAssociation(accountEntryId,
						com.liferay.osb.model.SupportRegion.class.getName(),
						supportRegionId);
				}

				for (ModelListener<com.liferay.osb.model.SupportRegion> listener : supportRegionListeners) {
					listener.onAfterAddAssociation(supportRegionId,
						AccountEntry.class.getName(), accountEntryId);
				}
			}
		}

		private SqlUpdate _sqlUpdate;
	}

	protected class ClearSupportRegions {
		protected ClearSupportRegions() {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"DELETE FROM OSB_AccountEntries_SupportRegions WHERE accountEntryId = ?",
					new int[] { java.sql.Types.BIGINT });
		}

		protected void clear(long accountEntryId) throws SystemException {
			ModelListener<com.liferay.osb.model.SupportRegion>[] supportRegionListeners =
				supportRegionPersistence.getListeners();

			List<com.liferay.osb.model.SupportRegion> supportRegions = null;

			if ((listeners.length > 0) || (supportRegionListeners.length > 0)) {
				supportRegions = getSupportRegions(accountEntryId);

				for (com.liferay.osb.model.SupportRegion supportRegion : supportRegions) {
					for (ModelListener<AccountEntry> listener : listeners) {
						listener.onBeforeRemoveAssociation(accountEntryId,
							com.liferay.osb.model.SupportRegion.class.getName(),
							supportRegion.getPrimaryKey());
					}

					for (ModelListener<com.liferay.osb.model.SupportRegion> listener : supportRegionListeners) {
						listener.onBeforeRemoveAssociation(supportRegion.getPrimaryKey(),
							AccountEntry.class.getName(), accountEntryId);
					}
				}
			}

			_sqlUpdate.update(new Object[] { new Long(accountEntryId) });

			if ((listeners.length > 0) || (supportRegionListeners.length > 0)) {
				for (com.liferay.osb.model.SupportRegion supportRegion : supportRegions) {
					for (ModelListener<AccountEntry> listener : listeners) {
						listener.onAfterRemoveAssociation(accountEntryId,
							com.liferay.osb.model.SupportRegion.class.getName(),
							supportRegion.getPrimaryKey());
					}

					for (ModelListener<com.liferay.osb.model.SupportRegion> listener : supportRegionListeners) {
						listener.onAfterRemoveAssociation(supportRegion.getPrimaryKey(),
							AccountEntry.class.getName(), accountEntryId);
					}
				}
			}
		}

		private SqlUpdate _sqlUpdate;
	}

	protected class RemoveSupportRegion {
		protected RemoveSupportRegion() {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"DELETE FROM OSB_AccountEntries_SupportRegions WHERE accountEntryId = ? AND supportRegionId = ?",
					new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT });
		}

		protected void remove(long accountEntryId, long supportRegionId)
			throws SystemException {
			if (containsSupportRegion.contains(accountEntryId, supportRegionId)) {
				ModelListener<com.liferay.osb.model.SupportRegion>[] supportRegionListeners =
					supportRegionPersistence.getListeners();

				for (ModelListener<AccountEntry> listener : listeners) {
					listener.onBeforeRemoveAssociation(accountEntryId,
						com.liferay.osb.model.SupportRegion.class.getName(),
						supportRegionId);
				}

				for (ModelListener<com.liferay.osb.model.SupportRegion> listener : supportRegionListeners) {
					listener.onBeforeRemoveAssociation(supportRegionId,
						AccountEntry.class.getName(), accountEntryId);
				}

				_sqlUpdate.update(new Object[] {
						new Long(accountEntryId), new Long(supportRegionId)
					});

				for (ModelListener<AccountEntry> listener : listeners) {
					listener.onAfterRemoveAssociation(accountEntryId,
						com.liferay.osb.model.SupportRegion.class.getName(),
						supportRegionId);
				}

				for (ModelListener<com.liferay.osb.model.SupportRegion> listener : supportRegionListeners) {
					listener.onAfterRemoveAssociation(supportRegionId,
						AccountEntry.class.getName(), accountEntryId);
				}
			}
		}

		private SqlUpdate _sqlUpdate;
	}

	protected class ContainsSupportTeam {
		protected ContainsSupportTeam() {
			_mappingSqlQuery = MappingSqlQueryFactoryUtil.getMappingSqlQuery(getDataSource(),
					_SQL_CONTAINSSUPPORTTEAM,
					new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT },
					RowMapper.COUNT);
		}

		protected boolean contains(long accountEntryId, long supportTeamId) {
			List<Integer> results = _mappingSqlQuery.execute(new Object[] {
						new Long(accountEntryId), new Long(supportTeamId)
					});

			if (results.size() > 0) {
				Integer count = results.get(0);

				if (count.intValue() > 0) {
					return true;
				}
			}

			return false;
		}

		private MappingSqlQuery<Integer> _mappingSqlQuery;
	}

	protected class AddSupportTeam {
		protected AddSupportTeam() {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"INSERT INTO OSB_AccountEntries_SupportTeams (accountEntryId, supportTeamId) VALUES (?, ?)",
					new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT });
		}

		protected void add(long accountEntryId, long supportTeamId)
			throws SystemException {
			if (!containsSupportTeam.contains(accountEntryId, supportTeamId)) {
				ModelListener<com.liferay.osb.model.SupportTeam>[] supportTeamListeners =
					supportTeamPersistence.getListeners();

				for (ModelListener<AccountEntry> listener : listeners) {
					listener.onBeforeAddAssociation(accountEntryId,
						com.liferay.osb.model.SupportTeam.class.getName(),
						supportTeamId);
				}

				for (ModelListener<com.liferay.osb.model.SupportTeam> listener : supportTeamListeners) {
					listener.onBeforeAddAssociation(supportTeamId,
						AccountEntry.class.getName(), accountEntryId);
				}

				_sqlUpdate.update(new Object[] {
						new Long(accountEntryId), new Long(supportTeamId)
					});

				for (ModelListener<AccountEntry> listener : listeners) {
					listener.onAfterAddAssociation(accountEntryId,
						com.liferay.osb.model.SupportTeam.class.getName(),
						supportTeamId);
				}

				for (ModelListener<com.liferay.osb.model.SupportTeam> listener : supportTeamListeners) {
					listener.onAfterAddAssociation(supportTeamId,
						AccountEntry.class.getName(), accountEntryId);
				}
			}
		}

		private SqlUpdate _sqlUpdate;
	}

	protected class ClearSupportTeams {
		protected ClearSupportTeams() {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"DELETE FROM OSB_AccountEntries_SupportTeams WHERE accountEntryId = ?",
					new int[] { java.sql.Types.BIGINT });
		}

		protected void clear(long accountEntryId) throws SystemException {
			ModelListener<com.liferay.osb.model.SupportTeam>[] supportTeamListeners =
				supportTeamPersistence.getListeners();

			List<com.liferay.osb.model.SupportTeam> supportTeams = null;

			if ((listeners.length > 0) || (supportTeamListeners.length > 0)) {
				supportTeams = getSupportTeams(accountEntryId);

				for (com.liferay.osb.model.SupportTeam supportTeam : supportTeams) {
					for (ModelListener<AccountEntry> listener : listeners) {
						listener.onBeforeRemoveAssociation(accountEntryId,
							com.liferay.osb.model.SupportTeam.class.getName(),
							supportTeam.getPrimaryKey());
					}

					for (ModelListener<com.liferay.osb.model.SupportTeam> listener : supportTeamListeners) {
						listener.onBeforeRemoveAssociation(supportTeam.getPrimaryKey(),
							AccountEntry.class.getName(), accountEntryId);
					}
				}
			}

			_sqlUpdate.update(new Object[] { new Long(accountEntryId) });

			if ((listeners.length > 0) || (supportTeamListeners.length > 0)) {
				for (com.liferay.osb.model.SupportTeam supportTeam : supportTeams) {
					for (ModelListener<AccountEntry> listener : listeners) {
						listener.onAfterRemoveAssociation(accountEntryId,
							com.liferay.osb.model.SupportTeam.class.getName(),
							supportTeam.getPrimaryKey());
					}

					for (ModelListener<com.liferay.osb.model.SupportTeam> listener : supportTeamListeners) {
						listener.onAfterRemoveAssociation(supportTeam.getPrimaryKey(),
							AccountEntry.class.getName(), accountEntryId);
					}
				}
			}
		}

		private SqlUpdate _sqlUpdate;
	}

	protected class RemoveSupportTeam {
		protected RemoveSupportTeam() {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"DELETE FROM OSB_AccountEntries_SupportTeams WHERE accountEntryId = ? AND supportTeamId = ?",
					new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT });
		}

		protected void remove(long accountEntryId, long supportTeamId)
			throws SystemException {
			if (containsSupportTeam.contains(accountEntryId, supportTeamId)) {
				ModelListener<com.liferay.osb.model.SupportTeam>[] supportTeamListeners =
					supportTeamPersistence.getListeners();

				for (ModelListener<AccountEntry> listener : listeners) {
					listener.onBeforeRemoveAssociation(accountEntryId,
						com.liferay.osb.model.SupportTeam.class.getName(),
						supportTeamId);
				}

				for (ModelListener<com.liferay.osb.model.SupportTeam> listener : supportTeamListeners) {
					listener.onBeforeRemoveAssociation(supportTeamId,
						AccountEntry.class.getName(), accountEntryId);
				}

				_sqlUpdate.update(new Object[] {
						new Long(accountEntryId), new Long(supportTeamId)
					});

				for (ModelListener<AccountEntry> listener : listeners) {
					listener.onAfterRemoveAssociation(accountEntryId,
						com.liferay.osb.model.SupportTeam.class.getName(),
						supportTeamId);
				}

				for (ModelListener<com.liferay.osb.model.SupportTeam> listener : supportTeamListeners) {
					listener.onAfterRemoveAssociation(supportTeamId,
						AccountEntry.class.getName(), accountEntryId);
				}
			}
		}

		private SqlUpdate _sqlUpdate;
	}

	private static final String _SQL_SELECT_ACCOUNTENTRY = "SELECT accountEntry FROM AccountEntry accountEntry";
	private static final String _SQL_SELECT_ACCOUNTENTRY_WHERE = "SELECT accountEntry FROM AccountEntry accountEntry WHERE ";
	private static final String _SQL_COUNT_ACCOUNTENTRY = "SELECT COUNT(accountEntry) FROM AccountEntry accountEntry";
	private static final String _SQL_COUNT_ACCOUNTENTRY_WHERE = "SELECT COUNT(accountEntry) FROM AccountEntry accountEntry WHERE ";
	private static final String _SQL_GETSUPPORTREGIONS = "SELECT {OSB_SupportRegion.*} FROM OSB_SupportRegion INNER JOIN OSB_AccountEntries_SupportRegions ON (OSB_AccountEntries_SupportRegions.supportRegionId = OSB_SupportRegion.supportRegionId) WHERE (OSB_AccountEntries_SupportRegions.accountEntryId = ?)";
	private static final String _SQL_GETSUPPORTREGIONSSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM OSB_AccountEntries_SupportRegions WHERE accountEntryId = ?";
	private static final String _SQL_CONTAINSSUPPORTREGION = "SELECT COUNT(*) AS COUNT_VALUE FROM OSB_AccountEntries_SupportRegions WHERE accountEntryId = ? AND supportRegionId = ?";
	private static final String _SQL_GETSUPPORTTEAMS = "SELECT {OSB_SupportTeam.*} FROM OSB_SupportTeam INNER JOIN OSB_AccountEntries_SupportTeams ON (OSB_AccountEntries_SupportTeams.supportTeamId = OSB_SupportTeam.supportTeamId) WHERE (OSB_AccountEntries_SupportTeams.accountEntryId = ?)";
	private static final String _SQL_GETSUPPORTTEAMSSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM OSB_AccountEntries_SupportTeams WHERE accountEntryId = ?";
	private static final String _SQL_CONTAINSSUPPORTTEAM = "SELECT COUNT(*) AS COUNT_VALUE FROM OSB_AccountEntries_SupportTeams WHERE accountEntryId = ? AND supportTeamId = ?";
	private static final String _FINDER_COLUMN_CORPPROJECTID_CORPPROJECTID_2 = "accountEntry.corpProjectId = ?";
	private static final String _FINDER_COLUMN_NAME_NAME_1 = "accountEntry.name IS NULL";
	private static final String _FINDER_COLUMN_NAME_NAME_2 = "accountEntry.name = ?";
	private static final String _FINDER_COLUMN_NAME_NAME_3 = "(accountEntry.name IS NULL OR accountEntry.name = ?)";
	private static final String _FINDER_COLUMN_CODE_CODE_1 = "accountEntry.code IS NULL";
	private static final String _FINDER_COLUMN_CODE_CODE_2 = "lower(accountEntry.code) = lower(CAST_TEXT(?))";
	private static final String _FINDER_COLUMN_CODE_CODE_3 = "(accountEntry.code IS NULL OR lower(accountEntry.code) = lower(CAST_TEXT(?)))";
	private static final String _FINDER_COLUMN_REDIRECTACCOUNTENTRYID_REDIRECTACCOUNTENTRYID_2 =
		"accountEntry.redirectAccountEntryId = ?";
	private static final String _FINDER_COLUMN_PARTNERENTRYID_PARTNERENTRYID_2 = "accountEntry.partnerEntryId = ?";
	private static final String _FINDER_COLUMN_HIGHESTSUPPORTRESPONSEID_HIGHESTSUPPORTRESPONSEID_2 =
		"accountEntry.highestSupportResponseId = ?";
	private static final String _FINDER_COLUMN_U_T_USERID_2 = "accountEntry.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_T_TYPE_2 = "accountEntry.type = ?";
	private static final String _FINDER_COLUMN_RAEI_S_REDIRECTACCOUNTENTRYID_2 = "accountEntry.redirectAccountEntryId = ? AND ";
	private static final String _FINDER_COLUMN_RAEI_S_REDIRECTACCOUNTENTRYID_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_RAEI_S_REDIRECTACCOUNTENTRYID_2) +
		")";
	private static final String _FINDER_COLUMN_RAEI_S_STATUS_2 = "accountEntry.status = ?";
	private static final String _FINDER_COLUMN_RAEI_S_STATUS_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_RAEI_S_STATUS_2) + ")";
	private static final String _FINDER_COLUMN_RAEI_NOTT_S_REDIRECTACCOUNTENTRYID_2 =
		"accountEntry.redirectAccountEntryId = ? AND ";
	private static final String _FINDER_COLUMN_RAEI_NOTT_S_REDIRECTACCOUNTENTRYID_5 =
		"(" +
		_removeConjunction(_FINDER_COLUMN_RAEI_NOTT_S_REDIRECTACCOUNTENTRYID_2) +
		")";
	private static final String _FINDER_COLUMN_RAEI_NOTT_S_TYPE_2 = "accountEntry.type != ? AND ";
	private static final String _FINDER_COLUMN_RAEI_NOTT_S_TYPE_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_RAEI_NOTT_S_TYPE_2) + ")";
	private static final String _FINDER_COLUMN_RAEI_NOTT_S_STATUS_2 = "accountEntry.status = ?";
	private static final String _FINDER_COLUMN_RAEI_NOTT_S_STATUS_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_RAEI_NOTT_S_STATUS_2) + ")";
	private static final String _FINDER_COLUMN_N_C_RAEI_NAME_1 = "accountEntry.name LIKE NULL AND ";
	private static final String _FINDER_COLUMN_N_C_RAEI_NAME_2 = "lower(accountEntry.name) LIKE lower(CAST_TEXT(?)) AND ";
	private static final String _FINDER_COLUMN_N_C_RAEI_NAME_3 = "(accountEntry.name IS NULL OR lower(accountEntry.name) LIKE lower(CAST_TEXT(?))) AND ";
	private static final String _FINDER_COLUMN_N_C_RAEI_CODE_1 = "accountEntry.code LIKE NULL AND ";
	private static final String _FINDER_COLUMN_N_C_RAEI_CODE_2 = "lower(accountEntry.code) LIKE lower(CAST_TEXT(?)) AND ";
	private static final String _FINDER_COLUMN_N_C_RAEI_CODE_3 = "(accountEntry.code IS NULL OR lower(accountEntry.code) LIKE lower(CAST_TEXT(?))) AND ";
	private static final String _FINDER_COLUMN_N_C_RAEI_REDIRECTACCOUNTENTRYID_2 =
		"accountEntry.redirectAccountEntryId = ?";

	private static String _removeConjunction(String sql) {
		int pos = sql.indexOf(" AND ");

		if (pos != -1) {
			sql = sql.substring(0, pos);
		}

		return sql;
	}

	private static final String _ORDER_BY_ENTITY_ALIAS = "accountEntry.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AccountEntry exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No AccountEntry exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(AccountEntryPersistenceImpl.class);
	private static AccountEntry _nullAccountEntry = new AccountEntryImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<AccountEntry> toCacheModel() {
				return _nullAccountEntryCacheModel;
			}
		};

	private static CacheModel<AccountEntry> _nullAccountEntryCacheModel = new CacheModel<AccountEntry>() {
			public AccountEntry toEntityModel() {
				return _nullAccountEntry;
			}
		};
}