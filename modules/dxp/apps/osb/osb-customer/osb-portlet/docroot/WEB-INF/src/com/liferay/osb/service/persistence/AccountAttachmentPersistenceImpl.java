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

import com.liferay.osb.NoSuchAccountAttachmentException;
import com.liferay.osb.model.AccountAttachment;
import com.liferay.osb.model.impl.AccountAttachmentImpl;
import com.liferay.osb.model.impl.AccountAttachmentModelImpl;

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
import com.liferay.portal.service.persistence.OrganizationPersistence;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the account attachment service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountAttachmentPersistence
 * @see AccountAttachmentUtil
 * @generated
 */
public class AccountAttachmentPersistenceImpl extends BasePersistenceImpl<AccountAttachment>
	implements AccountAttachmentPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AccountAttachmentUtil} to access the account attachment persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AccountAttachmentImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ACCOUNTENTRYID =
		new FinderPath(AccountAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountAttachmentModelImpl.FINDER_CACHE_ENABLED,
			AccountAttachmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAccountEntryId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID =
		new FinderPath(AccountAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountAttachmentModelImpl.FINDER_CACHE_ENABLED,
			AccountAttachmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAccountEntryId",
			new String[] { Long.class.getName() },
			AccountAttachmentModelImpl.ACCOUNTENTRYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ACCOUNTENTRYID = new FinderPath(AccountAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountAttachmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAccountEntryId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_AEI_API = new FinderPath(AccountAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountAttachmentModelImpl.FINDER_CACHE_ENABLED,
			AccountAttachmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAEI_API",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_API =
		new FinderPath(AccountAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountAttachmentModelImpl.FINDER_CACHE_ENABLED,
			AccountAttachmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAEI_API",
			new String[] { Long.class.getName(), Long.class.getName() },
			AccountAttachmentModelImpl.ACCOUNTENTRYID_COLUMN_BITMASK |
			AccountAttachmentModelImpl.ACCOUNTPROJECTID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_AEI_API = new FinderPath(AccountAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountAttachmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAEI_API",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_AEI_API_T =
		new FinderPath(AccountAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountAttachmentModelImpl.FINDER_CACHE_ENABLED,
			AccountAttachmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAEI_API_T",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_API_T =
		new FinderPath(AccountAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountAttachmentModelImpl.FINDER_CACHE_ENABLED,
			AccountAttachmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAEI_API_T",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			},
			AccountAttachmentModelImpl.ACCOUNTENTRYID_COLUMN_BITMASK |
			AccountAttachmentModelImpl.ACCOUNTPROJECTID_COLUMN_BITMASK |
			AccountAttachmentModelImpl.TYPE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_AEI_API_T = new FinderPath(AccountAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountAttachmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAEI_API_T",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_FETCH_BY_AEI_API_FN_T = new FinderPath(AccountAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountAttachmentModelImpl.FINDER_CACHE_ENABLED,
			AccountAttachmentImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByAEI_API_FN_T",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(), Integer.class.getName()
			},
			AccountAttachmentModelImpl.ACCOUNTENTRYID_COLUMN_BITMASK |
			AccountAttachmentModelImpl.ACCOUNTPROJECTID_COLUMN_BITMASK |
			AccountAttachmentModelImpl.FILENAME_COLUMN_BITMASK |
			AccountAttachmentModelImpl.TYPE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_AEI_API_FN_T = new FinderPath(AccountAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountAttachmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAEI_API_FN_T",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(), Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AccountAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountAttachmentModelImpl.FINDER_CACHE_ENABLED,
			AccountAttachmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AccountAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountAttachmentModelImpl.FINDER_CACHE_ENABLED,
			AccountAttachmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AccountAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountAttachmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the account attachment in the entity cache if it is enabled.
	 *
	 * @param accountAttachment the account attachment
	 */
	public void cacheResult(AccountAttachment accountAttachment) {
		EntityCacheUtil.putResult(AccountAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountAttachmentImpl.class, accountAttachment.getPrimaryKey(),
			accountAttachment);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_AEI_API_FN_T,
			new Object[] {
				Long.valueOf(accountAttachment.getAccountEntryId()),
				Long.valueOf(accountAttachment.getAccountProjectId()),
				
			accountAttachment.getFileName(),
				Integer.valueOf(accountAttachment.getType())
			}, accountAttachment);

		accountAttachment.resetOriginalValues();
	}

	/**
	 * Caches the account attachments in the entity cache if it is enabled.
	 *
	 * @param accountAttachments the account attachments
	 */
	public void cacheResult(List<AccountAttachment> accountAttachments) {
		for (AccountAttachment accountAttachment : accountAttachments) {
			if (EntityCacheUtil.getResult(
						AccountAttachmentModelImpl.ENTITY_CACHE_ENABLED,
						AccountAttachmentImpl.class,
						accountAttachment.getPrimaryKey()) == null) {
				cacheResult(accountAttachment);
			}
			else {
				accountAttachment.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all account attachments.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(AccountAttachmentImpl.class.getName());
		}

		EntityCacheUtil.clearCache(AccountAttachmentImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the account attachment.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AccountAttachment accountAttachment) {
		EntityCacheUtil.removeResult(AccountAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountAttachmentImpl.class, accountAttachment.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(accountAttachment);
	}

	@Override
	public void clearCache(List<AccountAttachment> accountAttachments) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AccountAttachment accountAttachment : accountAttachments) {
			EntityCacheUtil.removeResult(AccountAttachmentModelImpl.ENTITY_CACHE_ENABLED,
				AccountAttachmentImpl.class, accountAttachment.getPrimaryKey());

			clearUniqueFindersCache(accountAttachment);
		}
	}

	protected void cacheUniqueFindersCache(AccountAttachment accountAttachment) {
		if (accountAttachment.isNew()) {
			Object[] args = new Object[] {
					Long.valueOf(accountAttachment.getAccountEntryId()),
					Long.valueOf(accountAttachment.getAccountProjectId()),
					
					accountAttachment.getFileName(),
					Integer.valueOf(accountAttachment.getType())
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_AEI_API_FN_T, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_AEI_API_FN_T, args,
				accountAttachment);
		}
		else {
			AccountAttachmentModelImpl accountAttachmentModelImpl = (AccountAttachmentModelImpl)accountAttachment;

			if ((accountAttachmentModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_AEI_API_FN_T.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(accountAttachment.getAccountEntryId()),
						Long.valueOf(accountAttachment.getAccountProjectId()),
						
						accountAttachment.getFileName(),
						Integer.valueOf(accountAttachment.getType())
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_AEI_API_FN_T,
					args, Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_AEI_API_FN_T,
					args, accountAttachment);
			}
		}
	}

	protected void clearUniqueFindersCache(AccountAttachment accountAttachment) {
		AccountAttachmentModelImpl accountAttachmentModelImpl = (AccountAttachmentModelImpl)accountAttachment;

		Object[] args = new Object[] {
				Long.valueOf(accountAttachment.getAccountEntryId()),
				Long.valueOf(accountAttachment.getAccountProjectId()),
				
				accountAttachment.getFileName(),
				Integer.valueOf(accountAttachment.getType())
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_AEI_API_FN_T, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_AEI_API_FN_T, args);

		if ((accountAttachmentModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_AEI_API_FN_T.getColumnBitmask()) != 0) {
			args = new Object[] {
					Long.valueOf(accountAttachmentModelImpl.getOriginalAccountEntryId()),
					Long.valueOf(accountAttachmentModelImpl.getOriginalAccountProjectId()),
					
					accountAttachmentModelImpl.getOriginalFileName(),
					Integer.valueOf(accountAttachmentModelImpl.getOriginalType())
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_AEI_API_FN_T, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_AEI_API_FN_T, args);
		}
	}

	/**
	 * Creates a new account attachment with the primary key. Does not add the account attachment to the database.
	 *
	 * @param accountAttachmentId the primary key for the new account attachment
	 * @return the new account attachment
	 */
	public AccountAttachment create(long accountAttachmentId) {
		AccountAttachment accountAttachment = new AccountAttachmentImpl();

		accountAttachment.setNew(true);
		accountAttachment.setPrimaryKey(accountAttachmentId);

		return accountAttachment;
	}

	/**
	 * Removes the account attachment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param accountAttachmentId the primary key of the account attachment
	 * @return the account attachment that was removed
	 * @throws com.liferay.osb.NoSuchAccountAttachmentException if a account attachment with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountAttachment remove(long accountAttachmentId)
		throws NoSuchAccountAttachmentException, SystemException {
		return remove(Long.valueOf(accountAttachmentId));
	}

	/**
	 * Removes the account attachment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the account attachment
	 * @return the account attachment that was removed
	 * @throws com.liferay.osb.NoSuchAccountAttachmentException if a account attachment with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AccountAttachment remove(Serializable primaryKey)
		throws NoSuchAccountAttachmentException, SystemException {
		Session session = null;

		try {
			session = openSession();

			AccountAttachment accountAttachment = (AccountAttachment)session.get(AccountAttachmentImpl.class,
					primaryKey);

			if (accountAttachment == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAccountAttachmentException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(accountAttachment);
		}
		catch (NoSuchAccountAttachmentException nsee) {
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
	protected AccountAttachment removeImpl(AccountAttachment accountAttachment)
		throws SystemException {
		accountAttachment = toUnwrappedModel(accountAttachment);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, accountAttachment);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(accountAttachment);

		return accountAttachment;
	}

	@Override
	public AccountAttachment updateImpl(
		com.liferay.osb.model.AccountAttachment accountAttachment, boolean merge)
		throws SystemException {
		accountAttachment = toUnwrappedModel(accountAttachment);

		boolean isNew = accountAttachment.isNew();

		AccountAttachmentModelImpl accountAttachmentModelImpl = (AccountAttachmentModelImpl)accountAttachment;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, accountAttachment, merge);

			accountAttachment.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !AccountAttachmentModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((accountAttachmentModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(accountAttachmentModelImpl.getOriginalAccountEntryId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTENTRYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID,
					args);

				args = new Object[] {
						Long.valueOf(accountAttachmentModelImpl.getAccountEntryId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTENTRYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID,
					args);
			}

			if ((accountAttachmentModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_API.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(accountAttachmentModelImpl.getOriginalAccountEntryId()),
						Long.valueOf(accountAttachmentModelImpl.getOriginalAccountProjectId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_AEI_API, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_API,
					args);

				args = new Object[] {
						Long.valueOf(accountAttachmentModelImpl.getAccountEntryId()),
						Long.valueOf(accountAttachmentModelImpl.getAccountProjectId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_AEI_API, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_API,
					args);
			}

			if ((accountAttachmentModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_API_T.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(accountAttachmentModelImpl.getOriginalAccountEntryId()),
						Long.valueOf(accountAttachmentModelImpl.getOriginalAccountProjectId()),
						Integer.valueOf(accountAttachmentModelImpl.getOriginalType())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_AEI_API_T,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_API_T,
					args);

				args = new Object[] {
						Long.valueOf(accountAttachmentModelImpl.getAccountEntryId()),
						Long.valueOf(accountAttachmentModelImpl.getAccountProjectId()),
						Integer.valueOf(accountAttachmentModelImpl.getType())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_AEI_API_T,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_API_T,
					args);
			}
		}

		EntityCacheUtil.putResult(AccountAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountAttachmentImpl.class, accountAttachment.getPrimaryKey(),
			accountAttachment);

		clearUniqueFindersCache(accountAttachment);
		cacheUniqueFindersCache(accountAttachment);

		return accountAttachment;
	}

	protected AccountAttachment toUnwrappedModel(
		AccountAttachment accountAttachment) {
		if (accountAttachment instanceof AccountAttachmentImpl) {
			return accountAttachment;
		}

		AccountAttachmentImpl accountAttachmentImpl = new AccountAttachmentImpl();

		accountAttachmentImpl.setNew(accountAttachment.isNew());
		accountAttachmentImpl.setPrimaryKey(accountAttachment.getPrimaryKey());

		accountAttachmentImpl.setAccountAttachmentId(accountAttachment.getAccountAttachmentId());
		accountAttachmentImpl.setUserId(accountAttachment.getUserId());
		accountAttachmentImpl.setUserName(accountAttachment.getUserName());
		accountAttachmentImpl.setCreateDate(accountAttachment.getCreateDate());
		accountAttachmentImpl.setAccountEntryId(accountAttachment.getAccountEntryId());
		accountAttachmentImpl.setAccountProjectId(accountAttachment.getAccountProjectId());
		accountAttachmentImpl.setFileName(accountAttachment.getFileName());
		accountAttachmentImpl.setFileSize(accountAttachment.getFileSize());
		accountAttachmentImpl.setType(accountAttachment.getType());

		return accountAttachmentImpl;
	}

	/**
	 * Returns the account attachment with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the account attachment
	 * @return the account attachment
	 * @throws com.liferay.portal.NoSuchModelException if a account attachment with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AccountAttachment findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the account attachment with the primary key or throws a {@link com.liferay.osb.NoSuchAccountAttachmentException} if it could not be found.
	 *
	 * @param accountAttachmentId the primary key of the account attachment
	 * @return the account attachment
	 * @throws com.liferay.osb.NoSuchAccountAttachmentException if a account attachment with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountAttachment findByPrimaryKey(long accountAttachmentId)
		throws NoSuchAccountAttachmentException, SystemException {
		AccountAttachment accountAttachment = fetchByPrimaryKey(accountAttachmentId);

		if (accountAttachment == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					accountAttachmentId);
			}

			throw new NoSuchAccountAttachmentException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				accountAttachmentId);
		}

		return accountAttachment;
	}

	/**
	 * Returns the account attachment with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the account attachment
	 * @return the account attachment, or <code>null</code> if a account attachment with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AccountAttachment fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the account attachment with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param accountAttachmentId the primary key of the account attachment
	 * @return the account attachment, or <code>null</code> if a account attachment with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountAttachment fetchByPrimaryKey(long accountAttachmentId)
		throws SystemException {
		AccountAttachment accountAttachment = (AccountAttachment)EntityCacheUtil.getResult(AccountAttachmentModelImpl.ENTITY_CACHE_ENABLED,
				AccountAttachmentImpl.class, accountAttachmentId);

		if (accountAttachment == _nullAccountAttachment) {
			return null;
		}

		if (accountAttachment == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				accountAttachment = (AccountAttachment)session.get(AccountAttachmentImpl.class,
						Long.valueOf(accountAttachmentId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (accountAttachment != null) {
					cacheResult(accountAttachment);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(AccountAttachmentModelImpl.ENTITY_CACHE_ENABLED,
						AccountAttachmentImpl.class, accountAttachmentId,
						_nullAccountAttachment);
				}

				closeSession(session);
			}
		}

		return accountAttachment;
	}

	/**
	 * Returns all the account attachments where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @return the matching account attachments
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountAttachment> findByAccountEntryId(long accountEntryId)
		throws SystemException {
		return findByAccountEntryId(accountEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account attachments where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of account attachments
	 * @param end the upper bound of the range of account attachments (not inclusive)
	 * @return the range of matching account attachments
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountAttachment> findByAccountEntryId(long accountEntryId,
		int start, int end) throws SystemException {
		return findByAccountEntryId(accountEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the account attachments where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of account attachments
	 * @param end the upper bound of the range of account attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account attachments
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountAttachment> findByAccountEntryId(long accountEntryId,
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

		List<AccountAttachment> list = (List<AccountAttachment>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AccountAttachment accountAttachment : list) {
				if ((accountEntryId != accountAttachment.getAccountEntryId())) {
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

			query.append(_SQL_SELECT_ACCOUNTATTACHMENT_WHERE);

			query.append(_FINDER_COLUMN_ACCOUNTENTRYID_ACCOUNTENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(AccountAttachmentModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEntryId);

				list = (List<AccountAttachment>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first account attachment in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account attachment
	 * @throws com.liferay.osb.NoSuchAccountAttachmentException if a matching account attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountAttachment findByAccountEntryId_First(long accountEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchAccountAttachmentException, SystemException {
		AccountAttachment accountAttachment = fetchByAccountEntryId_First(accountEntryId,
				orderByComparator);

		if (accountAttachment != null) {
			return accountAttachment;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountAttachmentException(msg.toString());
	}

	/**
	 * Returns the first account attachment in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account attachment, or <code>null</code> if a matching account attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountAttachment fetchByAccountEntryId_First(long accountEntryId,
		OrderByComparator orderByComparator) throws SystemException {
		List<AccountAttachment> list = findByAccountEntryId(accountEntryId, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last account attachment in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account attachment
	 * @throws com.liferay.osb.NoSuchAccountAttachmentException if a matching account attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountAttachment findByAccountEntryId_Last(long accountEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchAccountAttachmentException, SystemException {
		AccountAttachment accountAttachment = fetchByAccountEntryId_Last(accountEntryId,
				orderByComparator);

		if (accountAttachment != null) {
			return accountAttachment;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountAttachmentException(msg.toString());
	}

	/**
	 * Returns the last account attachment in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account attachment, or <code>null</code> if a matching account attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountAttachment fetchByAccountEntryId_Last(long accountEntryId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByAccountEntryId(accountEntryId);

		List<AccountAttachment> list = findByAccountEntryId(accountEntryId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the account attachments before and after the current account attachment in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountAttachmentId the primary key of the current account attachment
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account attachment
	 * @throws com.liferay.osb.NoSuchAccountAttachmentException if a account attachment with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountAttachment[] findByAccountEntryId_PrevAndNext(
		long accountAttachmentId, long accountEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchAccountAttachmentException, SystemException {
		AccountAttachment accountAttachment = findByPrimaryKey(accountAttachmentId);

		Session session = null;

		try {
			session = openSession();

			AccountAttachment[] array = new AccountAttachmentImpl[3];

			array[0] = getByAccountEntryId_PrevAndNext(session,
					accountAttachment, accountEntryId, orderByComparator, true);

			array[1] = accountAttachment;

			array[2] = getByAccountEntryId_PrevAndNext(session,
					accountAttachment, accountEntryId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AccountAttachment getByAccountEntryId_PrevAndNext(
		Session session, AccountAttachment accountAttachment,
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

		query.append(_SQL_SELECT_ACCOUNTATTACHMENT_WHERE);

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
			query.append(AccountAttachmentModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(accountEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(accountAttachment);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AccountAttachment> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the account attachments where accountEntryId = &#63; and accountProjectId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @return the matching account attachments
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountAttachment> findByAEI_API(long accountEntryId,
		long accountProjectId) throws SystemException {
		return findByAEI_API(accountEntryId, accountProjectId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account attachments where accountEntryId = &#63; and accountProjectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param start the lower bound of the range of account attachments
	 * @param end the upper bound of the range of account attachments (not inclusive)
	 * @return the range of matching account attachments
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountAttachment> findByAEI_API(long accountEntryId,
		long accountProjectId, int start, int end) throws SystemException {
		return findByAEI_API(accountEntryId, accountProjectId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the account attachments where accountEntryId = &#63; and accountProjectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param start the lower bound of the range of account attachments
	 * @param end the upper bound of the range of account attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account attachments
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountAttachment> findByAEI_API(long accountEntryId,
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

		List<AccountAttachment> list = (List<AccountAttachment>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AccountAttachment accountAttachment : list) {
				if ((accountEntryId != accountAttachment.getAccountEntryId()) ||
						(accountProjectId != accountAttachment.getAccountProjectId())) {
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

			query.append(_SQL_SELECT_ACCOUNTATTACHMENT_WHERE);

			query.append(_FINDER_COLUMN_AEI_API_ACCOUNTENTRYID_2);

			query.append(_FINDER_COLUMN_AEI_API_ACCOUNTPROJECTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(AccountAttachmentModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEntryId);

				qPos.add(accountProjectId);

				list = (List<AccountAttachment>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first account attachment in the ordered set where accountEntryId = &#63; and accountProjectId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account attachment
	 * @throws com.liferay.osb.NoSuchAccountAttachmentException if a matching account attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountAttachment findByAEI_API_First(long accountEntryId,
		long accountProjectId, OrderByComparator orderByComparator)
		throws NoSuchAccountAttachmentException, SystemException {
		AccountAttachment accountAttachment = fetchByAEI_API_First(accountEntryId,
				accountProjectId, orderByComparator);

		if (accountAttachment != null) {
			return accountAttachment;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(", accountProjectId=");
		msg.append(accountProjectId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountAttachmentException(msg.toString());
	}

	/**
	 * Returns the first account attachment in the ordered set where accountEntryId = &#63; and accountProjectId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account attachment, or <code>null</code> if a matching account attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountAttachment fetchByAEI_API_First(long accountEntryId,
		long accountProjectId, OrderByComparator orderByComparator)
		throws SystemException {
		List<AccountAttachment> list = findByAEI_API(accountEntryId,
				accountProjectId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last account attachment in the ordered set where accountEntryId = &#63; and accountProjectId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account attachment
	 * @throws com.liferay.osb.NoSuchAccountAttachmentException if a matching account attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountAttachment findByAEI_API_Last(long accountEntryId,
		long accountProjectId, OrderByComparator orderByComparator)
		throws NoSuchAccountAttachmentException, SystemException {
		AccountAttachment accountAttachment = fetchByAEI_API_Last(accountEntryId,
				accountProjectId, orderByComparator);

		if (accountAttachment != null) {
			return accountAttachment;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(", accountProjectId=");
		msg.append(accountProjectId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountAttachmentException(msg.toString());
	}

	/**
	 * Returns the last account attachment in the ordered set where accountEntryId = &#63; and accountProjectId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account attachment, or <code>null</code> if a matching account attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountAttachment fetchByAEI_API_Last(long accountEntryId,
		long accountProjectId, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByAEI_API(accountEntryId, accountProjectId);

		List<AccountAttachment> list = findByAEI_API(accountEntryId,
				accountProjectId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the account attachments before and after the current account attachment in the ordered set where accountEntryId = &#63; and accountProjectId = &#63;.
	 *
	 * @param accountAttachmentId the primary key of the current account attachment
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account attachment
	 * @throws com.liferay.osb.NoSuchAccountAttachmentException if a account attachment with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountAttachment[] findByAEI_API_PrevAndNext(
		long accountAttachmentId, long accountEntryId, long accountProjectId,
		OrderByComparator orderByComparator)
		throws NoSuchAccountAttachmentException, SystemException {
		AccountAttachment accountAttachment = findByPrimaryKey(accountAttachmentId);

		Session session = null;

		try {
			session = openSession();

			AccountAttachment[] array = new AccountAttachmentImpl[3];

			array[0] = getByAEI_API_PrevAndNext(session, accountAttachment,
					accountEntryId, accountProjectId, orderByComparator, true);

			array[1] = accountAttachment;

			array[2] = getByAEI_API_PrevAndNext(session, accountAttachment,
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

	protected AccountAttachment getByAEI_API_PrevAndNext(Session session,
		AccountAttachment accountAttachment, long accountEntryId,
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

		query.append(_SQL_SELECT_ACCOUNTATTACHMENT_WHERE);

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

		else {
			query.append(AccountAttachmentModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(accountEntryId);

		qPos.add(accountProjectId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(accountAttachment);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AccountAttachment> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the account attachments where accountEntryId = &#63; and accountProjectId = &#63; and type = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param type the type
	 * @return the matching account attachments
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountAttachment> findByAEI_API_T(long accountEntryId,
		long accountProjectId, int type) throws SystemException {
		return findByAEI_API_T(accountEntryId, accountProjectId, type,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account attachments where accountEntryId = &#63; and accountProjectId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param type the type
	 * @param start the lower bound of the range of account attachments
	 * @param end the upper bound of the range of account attachments (not inclusive)
	 * @return the range of matching account attachments
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountAttachment> findByAEI_API_T(long accountEntryId,
		long accountProjectId, int type, int start, int end)
		throws SystemException {
		return findByAEI_API_T(accountEntryId, accountProjectId, type, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the account attachments where accountEntryId = &#63; and accountProjectId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param type the type
	 * @param start the lower bound of the range of account attachments
	 * @param end the upper bound of the range of account attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account attachments
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountAttachment> findByAEI_API_T(long accountEntryId,
		long accountProjectId, int type, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_API_T;
			finderArgs = new Object[] { accountEntryId, accountProjectId, type };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_AEI_API_T;
			finderArgs = new Object[] {
					accountEntryId, accountProjectId, type,
					
					start, end, orderByComparator
				};
		}

		List<AccountAttachment> list = (List<AccountAttachment>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AccountAttachment accountAttachment : list) {
				if ((accountEntryId != accountAttachment.getAccountEntryId()) ||
						(accountProjectId != accountAttachment.getAccountProjectId()) ||
						(type != accountAttachment.getType())) {
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

			query.append(_SQL_SELECT_ACCOUNTATTACHMENT_WHERE);

			query.append(_FINDER_COLUMN_AEI_API_T_ACCOUNTENTRYID_2);

			query.append(_FINDER_COLUMN_AEI_API_T_ACCOUNTPROJECTID_2);

			query.append(_FINDER_COLUMN_AEI_API_T_TYPE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(AccountAttachmentModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEntryId);

				qPos.add(accountProjectId);

				qPos.add(type);

				list = (List<AccountAttachment>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first account attachment in the ordered set where accountEntryId = &#63; and accountProjectId = &#63; and type = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account attachment
	 * @throws com.liferay.osb.NoSuchAccountAttachmentException if a matching account attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountAttachment findByAEI_API_T_First(long accountEntryId,
		long accountProjectId, int type, OrderByComparator orderByComparator)
		throws NoSuchAccountAttachmentException, SystemException {
		AccountAttachment accountAttachment = fetchByAEI_API_T_First(accountEntryId,
				accountProjectId, type, orderByComparator);

		if (accountAttachment != null) {
			return accountAttachment;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(", accountProjectId=");
		msg.append(accountProjectId);

		msg.append(", type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountAttachmentException(msg.toString());
	}

	/**
	 * Returns the first account attachment in the ordered set where accountEntryId = &#63; and accountProjectId = &#63; and type = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account attachment, or <code>null</code> if a matching account attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountAttachment fetchByAEI_API_T_First(long accountEntryId,
		long accountProjectId, int type, OrderByComparator orderByComparator)
		throws SystemException {
		List<AccountAttachment> list = findByAEI_API_T(accountEntryId,
				accountProjectId, type, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last account attachment in the ordered set where accountEntryId = &#63; and accountProjectId = &#63; and type = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account attachment
	 * @throws com.liferay.osb.NoSuchAccountAttachmentException if a matching account attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountAttachment findByAEI_API_T_Last(long accountEntryId,
		long accountProjectId, int type, OrderByComparator orderByComparator)
		throws NoSuchAccountAttachmentException, SystemException {
		AccountAttachment accountAttachment = fetchByAEI_API_T_Last(accountEntryId,
				accountProjectId, type, orderByComparator);

		if (accountAttachment != null) {
			return accountAttachment;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(", accountProjectId=");
		msg.append(accountProjectId);

		msg.append(", type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountAttachmentException(msg.toString());
	}

	/**
	 * Returns the last account attachment in the ordered set where accountEntryId = &#63; and accountProjectId = &#63; and type = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account attachment, or <code>null</code> if a matching account attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountAttachment fetchByAEI_API_T_Last(long accountEntryId,
		long accountProjectId, int type, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByAEI_API_T(accountEntryId, accountProjectId, type);

		List<AccountAttachment> list = findByAEI_API_T(accountEntryId,
				accountProjectId, type, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the account attachments before and after the current account attachment in the ordered set where accountEntryId = &#63; and accountProjectId = &#63; and type = &#63;.
	 *
	 * @param accountAttachmentId the primary key of the current account attachment
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account attachment
	 * @throws com.liferay.osb.NoSuchAccountAttachmentException if a account attachment with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountAttachment[] findByAEI_API_T_PrevAndNext(
		long accountAttachmentId, long accountEntryId, long accountProjectId,
		int type, OrderByComparator orderByComparator)
		throws NoSuchAccountAttachmentException, SystemException {
		AccountAttachment accountAttachment = findByPrimaryKey(accountAttachmentId);

		Session session = null;

		try {
			session = openSession();

			AccountAttachment[] array = new AccountAttachmentImpl[3];

			array[0] = getByAEI_API_T_PrevAndNext(session, accountAttachment,
					accountEntryId, accountProjectId, type, orderByComparator,
					true);

			array[1] = accountAttachment;

			array[2] = getByAEI_API_T_PrevAndNext(session, accountAttachment,
					accountEntryId, accountProjectId, type, orderByComparator,
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

	protected AccountAttachment getByAEI_API_T_PrevAndNext(Session session,
		AccountAttachment accountAttachment, long accountEntryId,
		long accountProjectId, int type, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ACCOUNTATTACHMENT_WHERE);

		query.append(_FINDER_COLUMN_AEI_API_T_ACCOUNTENTRYID_2);

		query.append(_FINDER_COLUMN_AEI_API_T_ACCOUNTPROJECTID_2);

		query.append(_FINDER_COLUMN_AEI_API_T_TYPE_2);

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
			query.append(AccountAttachmentModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(accountEntryId);

		qPos.add(accountProjectId);

		qPos.add(type);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(accountAttachment);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AccountAttachment> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the account attachment where accountEntryId = &#63; and accountProjectId = &#63; and fileName = &#63; and type = &#63; or throws a {@link com.liferay.osb.NoSuchAccountAttachmentException} if it could not be found.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param fileName the file name
	 * @param type the type
	 * @return the matching account attachment
	 * @throws com.liferay.osb.NoSuchAccountAttachmentException if a matching account attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountAttachment findByAEI_API_FN_T(long accountEntryId,
		long accountProjectId, String fileName, int type)
		throws NoSuchAccountAttachmentException, SystemException {
		AccountAttachment accountAttachment = fetchByAEI_API_FN_T(accountEntryId,
				accountProjectId, fileName, type);

		if (accountAttachment == null) {
			StringBundler msg = new StringBundler(10);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("accountEntryId=");
			msg.append(accountEntryId);

			msg.append(", accountProjectId=");
			msg.append(accountProjectId);

			msg.append(", fileName=");
			msg.append(fileName);

			msg.append(", type=");
			msg.append(type);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchAccountAttachmentException(msg.toString());
		}

		return accountAttachment;
	}

	/**
	 * Returns the account attachment where accountEntryId = &#63; and accountProjectId = &#63; and fileName = &#63; and type = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param fileName the file name
	 * @param type the type
	 * @return the matching account attachment, or <code>null</code> if a matching account attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountAttachment fetchByAEI_API_FN_T(long accountEntryId,
		long accountProjectId, String fileName, int type)
		throws SystemException {
		return fetchByAEI_API_FN_T(accountEntryId, accountProjectId, fileName,
			type, true);
	}

	/**
	 * Returns the account attachment where accountEntryId = &#63; and accountProjectId = &#63; and fileName = &#63; and type = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param fileName the file name
	 * @param type the type
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching account attachment, or <code>null</code> if a matching account attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountAttachment fetchByAEI_API_FN_T(long accountEntryId,
		long accountProjectId, String fileName, int type,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] {
				accountEntryId, accountProjectId, fileName, type
			};

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_AEI_API_FN_T,
					finderArgs, this);
		}

		if (result instanceof AccountAttachment) {
			AccountAttachment accountAttachment = (AccountAttachment)result;

			if ((accountEntryId != accountAttachment.getAccountEntryId()) ||
					(accountProjectId != accountAttachment.getAccountProjectId()) ||
					!Validator.equals(fileName, accountAttachment.getFileName()) ||
					(type != accountAttachment.getType())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(6);

			query.append(_SQL_SELECT_ACCOUNTATTACHMENT_WHERE);

			query.append(_FINDER_COLUMN_AEI_API_FN_T_ACCOUNTENTRYID_2);

			query.append(_FINDER_COLUMN_AEI_API_FN_T_ACCOUNTPROJECTID_2);

			if (fileName == null) {
				query.append(_FINDER_COLUMN_AEI_API_FN_T_FILENAME_1);
			}
			else {
				if (fileName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_AEI_API_FN_T_FILENAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_AEI_API_FN_T_FILENAME_2);
				}
			}

			query.append(_FINDER_COLUMN_AEI_API_FN_T_TYPE_2);

			query.append(AccountAttachmentModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEntryId);

				qPos.add(accountProjectId);

				if (fileName != null) {
					qPos.add(fileName);
				}

				qPos.add(type);

				List<AccountAttachment> list = q.list();

				result = list;

				AccountAttachment accountAttachment = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_AEI_API_FN_T,
						finderArgs, list);
				}
				else {
					accountAttachment = list.get(0);

					cacheResult(accountAttachment);

					if ((accountAttachment.getAccountEntryId() != accountEntryId) ||
							(accountAttachment.getAccountProjectId() != accountProjectId) ||
							(accountAttachment.getFileName() == null) ||
							!accountAttachment.getFileName().equals(fileName) ||
							(accountAttachment.getType() != type)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_AEI_API_FN_T,
							finderArgs, accountAttachment);
					}
				}

				return accountAttachment;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_AEI_API_FN_T,
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
				return (AccountAttachment)result;
			}
		}
	}

	/**
	 * Returns all the account attachments.
	 *
	 * @return the account attachments
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountAttachment> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account attachments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of account attachments
	 * @param end the upper bound of the range of account attachments (not inclusive)
	 * @return the range of account attachments
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountAttachment> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the account attachments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of account attachments
	 * @param end the upper bound of the range of account attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of account attachments
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountAttachment> findAll(int start, int end,
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

		List<AccountAttachment> list = (List<AccountAttachment>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_ACCOUNTATTACHMENT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ACCOUNTATTACHMENT.concat(AccountAttachmentModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<AccountAttachment>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<AccountAttachment>)QueryUtil.list(q,
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
	 * Removes all the account attachments where accountEntryId = &#63; from the database.
	 *
	 * @param accountEntryId the account entry ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByAccountEntryId(long accountEntryId)
		throws SystemException {
		for (AccountAttachment accountAttachment : findByAccountEntryId(
				accountEntryId)) {
			remove(accountAttachment);
		}
	}

	/**
	 * Removes all the account attachments where accountEntryId = &#63; and accountProjectId = &#63; from the database.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByAEI_API(long accountEntryId, long accountProjectId)
		throws SystemException {
		for (AccountAttachment accountAttachment : findByAEI_API(
				accountEntryId, accountProjectId)) {
			remove(accountAttachment);
		}
	}

	/**
	 * Removes all the account attachments where accountEntryId = &#63; and accountProjectId = &#63; and type = &#63; from the database.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param type the type
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByAEI_API_T(long accountEntryId, long accountProjectId,
		int type) throws SystemException {
		for (AccountAttachment accountAttachment : findByAEI_API_T(
				accountEntryId, accountProjectId, type)) {
			remove(accountAttachment);
		}
	}

	/**
	 * Removes the account attachment where accountEntryId = &#63; and accountProjectId = &#63; and fileName = &#63; and type = &#63; from the database.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param fileName the file name
	 * @param type the type
	 * @return the account attachment that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public AccountAttachment removeByAEI_API_FN_T(long accountEntryId,
		long accountProjectId, String fileName, int type)
		throws NoSuchAccountAttachmentException, SystemException {
		AccountAttachment accountAttachment = findByAEI_API_FN_T(accountEntryId,
				accountProjectId, fileName, type);

		return remove(accountAttachment);
	}

	/**
	 * Removes all the account attachments from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (AccountAttachment accountAttachment : findAll()) {
			remove(accountAttachment);
		}
	}

	/**
	 * Returns the number of account attachments where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @return the number of matching account attachments
	 * @throws SystemException if a system exception occurred
	 */
	public int countByAccountEntryId(long accountEntryId)
		throws SystemException {
		Object[] finderArgs = new Object[] { accountEntryId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_ACCOUNTENTRYID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ACCOUNTATTACHMENT_WHERE);

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
	 * Returns the number of account attachments where accountEntryId = &#63; and accountProjectId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @return the number of matching account attachments
	 * @throws SystemException if a system exception occurred
	 */
	public int countByAEI_API(long accountEntryId, long accountProjectId)
		throws SystemException {
		Object[] finderArgs = new Object[] { accountEntryId, accountProjectId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_AEI_API,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ACCOUNTATTACHMENT_WHERE);

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
	 * Returns the number of account attachments where accountEntryId = &#63; and accountProjectId = &#63; and type = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param type the type
	 * @return the number of matching account attachments
	 * @throws SystemException if a system exception occurred
	 */
	public int countByAEI_API_T(long accountEntryId, long accountProjectId,
		int type) throws SystemException {
		Object[] finderArgs = new Object[] {
				accountEntryId, accountProjectId, type
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_AEI_API_T,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_ACCOUNTATTACHMENT_WHERE);

			query.append(_FINDER_COLUMN_AEI_API_T_ACCOUNTENTRYID_2);

			query.append(_FINDER_COLUMN_AEI_API_T_ACCOUNTPROJECTID_2);

			query.append(_FINDER_COLUMN_AEI_API_T_TYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEntryId);

				qPos.add(accountProjectId);

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_AEI_API_T,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of account attachments where accountEntryId = &#63; and accountProjectId = &#63; and fileName = &#63; and type = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param fileName the file name
	 * @param type the type
	 * @return the number of matching account attachments
	 * @throws SystemException if a system exception occurred
	 */
	public int countByAEI_API_FN_T(long accountEntryId, long accountProjectId,
		String fileName, int type) throws SystemException {
		Object[] finderArgs = new Object[] {
				accountEntryId, accountProjectId, fileName, type
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_AEI_API_FN_T,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_ACCOUNTATTACHMENT_WHERE);

			query.append(_FINDER_COLUMN_AEI_API_FN_T_ACCOUNTENTRYID_2);

			query.append(_FINDER_COLUMN_AEI_API_FN_T_ACCOUNTPROJECTID_2);

			if (fileName == null) {
				query.append(_FINDER_COLUMN_AEI_API_FN_T_FILENAME_1);
			}
			else {
				if (fileName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_AEI_API_FN_T_FILENAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_AEI_API_FN_T_FILENAME_2);
				}
			}

			query.append(_FINDER_COLUMN_AEI_API_FN_T_TYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEntryId);

				qPos.add(accountProjectId);

				if (fileName != null) {
					qPos.add(fileName);
				}

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_AEI_API_FN_T,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of account attachments.
	 *
	 * @return the number of account attachments
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ACCOUNTATTACHMENT);

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
	 * Initializes the account attachment persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.osb.model.AccountAttachment")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<AccountAttachment>> listenersList = new ArrayList<ModelListener<AccountAttachment>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<AccountAttachment>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(AccountAttachmentImpl.class.getName());
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
	private static final String _SQL_SELECT_ACCOUNTATTACHMENT = "SELECT accountAttachment FROM AccountAttachment accountAttachment";
	private static final String _SQL_SELECT_ACCOUNTATTACHMENT_WHERE = "SELECT accountAttachment FROM AccountAttachment accountAttachment WHERE ";
	private static final String _SQL_COUNT_ACCOUNTATTACHMENT = "SELECT COUNT(accountAttachment) FROM AccountAttachment accountAttachment";
	private static final String _SQL_COUNT_ACCOUNTATTACHMENT_WHERE = "SELECT COUNT(accountAttachment) FROM AccountAttachment accountAttachment WHERE ";
	private static final String _FINDER_COLUMN_ACCOUNTENTRYID_ACCOUNTENTRYID_2 = "accountAttachment.accountEntryId = ?";
	private static final String _FINDER_COLUMN_AEI_API_ACCOUNTENTRYID_2 = "accountAttachment.accountEntryId = ? AND ";
	private static final String _FINDER_COLUMN_AEI_API_ACCOUNTPROJECTID_2 = "accountAttachment.accountProjectId = ?";
	private static final String _FINDER_COLUMN_AEI_API_T_ACCOUNTENTRYID_2 = "accountAttachment.accountEntryId = ? AND ";
	private static final String _FINDER_COLUMN_AEI_API_T_ACCOUNTPROJECTID_2 = "accountAttachment.accountProjectId = ? AND ";
	private static final String _FINDER_COLUMN_AEI_API_T_TYPE_2 = "accountAttachment.type = ?";
	private static final String _FINDER_COLUMN_AEI_API_FN_T_ACCOUNTENTRYID_2 = "accountAttachment.accountEntryId = ? AND ";
	private static final String _FINDER_COLUMN_AEI_API_FN_T_ACCOUNTPROJECTID_2 = "accountAttachment.accountProjectId = ? AND ";
	private static final String _FINDER_COLUMN_AEI_API_FN_T_FILENAME_1 = "accountAttachment.fileName IS NULL AND ";
	private static final String _FINDER_COLUMN_AEI_API_FN_T_FILENAME_2 = "accountAttachment.fileName = ? AND ";
	private static final String _FINDER_COLUMN_AEI_API_FN_T_FILENAME_3 = "(accountAttachment.fileName IS NULL OR accountAttachment.fileName = ?) AND ";
	private static final String _FINDER_COLUMN_AEI_API_FN_T_TYPE_2 = "accountAttachment.type = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "accountAttachment.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AccountAttachment exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No AccountAttachment exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(AccountAttachmentPersistenceImpl.class);
	private static AccountAttachment _nullAccountAttachment = new AccountAttachmentImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<AccountAttachment> toCacheModel() {
				return _nullAccountAttachmentCacheModel;
			}
		};

	private static CacheModel<AccountAttachment> _nullAccountAttachmentCacheModel =
		new CacheModel<AccountAttachment>() {
			public AccountAttachment toEntityModel() {
				return _nullAccountAttachment;
			}
		};
}