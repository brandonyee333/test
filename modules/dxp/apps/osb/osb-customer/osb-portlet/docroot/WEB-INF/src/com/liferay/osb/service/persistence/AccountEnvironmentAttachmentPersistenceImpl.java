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

import com.liferay.osb.NoSuchAccountEnvironmentAttachmentException;
import com.liferay.osb.model.AccountEnvironmentAttachment;
import com.liferay.osb.model.impl.AccountEnvironmentAttachmentImpl;
import com.liferay.osb.model.impl.AccountEnvironmentAttachmentModelImpl;

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
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the account environment attachment service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountEnvironmentAttachmentPersistence
 * @see AccountEnvironmentAttachmentUtil
 * @generated
 */
public class AccountEnvironmentAttachmentPersistenceImpl
	extends BasePersistenceImpl<AccountEnvironmentAttachment>
	implements AccountEnvironmentAttachmentPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AccountEnvironmentAttachmentUtil} to access the account environment attachment persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AccountEnvironmentAttachmentImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ACCOUNTENVIRONMENTID =
		new FinderPath(AccountEnvironmentAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountEnvironmentAttachmentModelImpl.FINDER_CACHE_ENABLED,
			AccountEnvironmentAttachmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByAccountEnvironmentId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENVIRONMENTID =
		new FinderPath(AccountEnvironmentAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountEnvironmentAttachmentModelImpl.FINDER_CACHE_ENABLED,
			AccountEnvironmentAttachmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByAccountEnvironmentId",
			new String[] { Long.class.getName() },
			AccountEnvironmentAttachmentModelImpl.ACCOUNTENVIRONMENTID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ACCOUNTENVIRONMENTID = new FinderPath(AccountEnvironmentAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountEnvironmentAttachmentModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByAccountEnvironmentId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_AEI_FN = new FinderPath(AccountEnvironmentAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountEnvironmentAttachmentModelImpl.FINDER_CACHE_ENABLED,
			AccountEnvironmentAttachmentImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByAEI_FN",
			new String[] { Long.class.getName(), String.class.getName() },
			AccountEnvironmentAttachmentModelImpl.ACCOUNTENVIRONMENTID_COLUMN_BITMASK |
			AccountEnvironmentAttachmentModelImpl.FILENAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_AEI_FN = new FinderPath(AccountEnvironmentAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountEnvironmentAttachmentModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByAEI_FN",
			new String[] { Long.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_AEI_T = new FinderPath(AccountEnvironmentAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountEnvironmentAttachmentModelImpl.FINDER_CACHE_ENABLED,
			AccountEnvironmentAttachmentImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByAEI_T",
			new String[] { Long.class.getName(), Integer.class.getName() },
			AccountEnvironmentAttachmentModelImpl.ACCOUNTENVIRONMENTID_COLUMN_BITMASK |
			AccountEnvironmentAttachmentModelImpl.TYPE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_AEI_T = new FinderPath(AccountEnvironmentAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountEnvironmentAttachmentModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByAEI_T",
			new String[] { Long.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AccountEnvironmentAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountEnvironmentAttachmentModelImpl.FINDER_CACHE_ENABLED,
			AccountEnvironmentAttachmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AccountEnvironmentAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountEnvironmentAttachmentModelImpl.FINDER_CACHE_ENABLED,
			AccountEnvironmentAttachmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AccountEnvironmentAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountEnvironmentAttachmentModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

	/**
	 * Caches the account environment attachment in the entity cache if it is enabled.
	 *
	 * @param accountEnvironmentAttachment the account environment attachment
	 */
	public void cacheResult(
		AccountEnvironmentAttachment accountEnvironmentAttachment) {
		EntityCacheUtil.putResult(AccountEnvironmentAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountEnvironmentAttachmentImpl.class,
			accountEnvironmentAttachment.getPrimaryKey(),
			accountEnvironmentAttachment);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_AEI_FN,
			new Object[] {
				Long.valueOf(
					accountEnvironmentAttachment.getAccountEnvironmentId()),
				
			accountEnvironmentAttachment.getFileName()
			}, accountEnvironmentAttachment);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_AEI_T,
			new Object[] {
				Long.valueOf(
					accountEnvironmentAttachment.getAccountEnvironmentId()),
				Integer.valueOf(accountEnvironmentAttachment.getType())
			}, accountEnvironmentAttachment);

		accountEnvironmentAttachment.resetOriginalValues();
	}

	/**
	 * Caches the account environment attachments in the entity cache if it is enabled.
	 *
	 * @param accountEnvironmentAttachments the account environment attachments
	 */
	public void cacheResult(
		List<AccountEnvironmentAttachment> accountEnvironmentAttachments) {
		for (AccountEnvironmentAttachment accountEnvironmentAttachment : accountEnvironmentAttachments) {
			if (EntityCacheUtil.getResult(
						AccountEnvironmentAttachmentModelImpl.ENTITY_CACHE_ENABLED,
						AccountEnvironmentAttachmentImpl.class,
						accountEnvironmentAttachment.getPrimaryKey()) == null) {
				cacheResult(accountEnvironmentAttachment);
			}
			else {
				accountEnvironmentAttachment.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all account environment attachments.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(AccountEnvironmentAttachmentImpl.class.getName());
		}

		EntityCacheUtil.clearCache(AccountEnvironmentAttachmentImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the account environment attachment.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(
		AccountEnvironmentAttachment accountEnvironmentAttachment) {
		EntityCacheUtil.removeResult(AccountEnvironmentAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountEnvironmentAttachmentImpl.class,
			accountEnvironmentAttachment.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(accountEnvironmentAttachment);
	}

	@Override
	public void clearCache(
		List<AccountEnvironmentAttachment> accountEnvironmentAttachments) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AccountEnvironmentAttachment accountEnvironmentAttachment : accountEnvironmentAttachments) {
			EntityCacheUtil.removeResult(AccountEnvironmentAttachmentModelImpl.ENTITY_CACHE_ENABLED,
				AccountEnvironmentAttachmentImpl.class,
				accountEnvironmentAttachment.getPrimaryKey());

			clearUniqueFindersCache(accountEnvironmentAttachment);
		}
	}

	protected void cacheUniqueFindersCache(
		AccountEnvironmentAttachment accountEnvironmentAttachment) {
		if (accountEnvironmentAttachment.isNew()) {
			Object[] args = new Object[] {
					Long.valueOf(accountEnvironmentAttachment.getAccountEnvironmentId()),
					
					accountEnvironmentAttachment.getFileName()
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_AEI_FN, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_AEI_FN, args,
				accountEnvironmentAttachment);

			args = new Object[] {
					Long.valueOf(accountEnvironmentAttachment.getAccountEnvironmentId()),
					Integer.valueOf(accountEnvironmentAttachment.getType())
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_AEI_T, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_AEI_T, args,
				accountEnvironmentAttachment);
		}
		else {
			AccountEnvironmentAttachmentModelImpl accountEnvironmentAttachmentModelImpl =
				(AccountEnvironmentAttachmentModelImpl)accountEnvironmentAttachment;

			if ((accountEnvironmentAttachmentModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_AEI_FN.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(accountEnvironmentAttachment.getAccountEnvironmentId()),
						
						accountEnvironmentAttachment.getFileName()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_AEI_FN, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_AEI_FN, args,
					accountEnvironmentAttachment);
			}

			if ((accountEnvironmentAttachmentModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_AEI_T.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(accountEnvironmentAttachment.getAccountEnvironmentId()),
						Integer.valueOf(accountEnvironmentAttachment.getType())
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_AEI_T, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_AEI_T, args,
					accountEnvironmentAttachment);
			}
		}
	}

	protected void clearUniqueFindersCache(
		AccountEnvironmentAttachment accountEnvironmentAttachment) {
		AccountEnvironmentAttachmentModelImpl accountEnvironmentAttachmentModelImpl =
			(AccountEnvironmentAttachmentModelImpl)accountEnvironmentAttachment;

		Object[] args = new Object[] {
				Long.valueOf(accountEnvironmentAttachment.getAccountEnvironmentId()),
				
				accountEnvironmentAttachment.getFileName()
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_AEI_FN, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_AEI_FN, args);

		if ((accountEnvironmentAttachmentModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_AEI_FN.getColumnBitmask()) != 0) {
			args = new Object[] {
					Long.valueOf(accountEnvironmentAttachmentModelImpl.getOriginalAccountEnvironmentId()),
					
					accountEnvironmentAttachmentModelImpl.getOriginalFileName()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_AEI_FN, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_AEI_FN, args);
		}

		args = new Object[] {
				Long.valueOf(accountEnvironmentAttachment.getAccountEnvironmentId()),
				Integer.valueOf(accountEnvironmentAttachment.getType())
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_AEI_T, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_AEI_T, args);

		if ((accountEnvironmentAttachmentModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_AEI_T.getColumnBitmask()) != 0) {
			args = new Object[] {
					Long.valueOf(accountEnvironmentAttachmentModelImpl.getOriginalAccountEnvironmentId()),
					Integer.valueOf(accountEnvironmentAttachmentModelImpl.getOriginalType())
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_AEI_T, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_AEI_T, args);
		}
	}

	/**
	 * Creates a new account environment attachment with the primary key. Does not add the account environment attachment to the database.
	 *
	 * @param accountEnvironmentAttachmentId the primary key for the new account environment attachment
	 * @return the new account environment attachment
	 */
	public AccountEnvironmentAttachment create(
		long accountEnvironmentAttachmentId) {
		AccountEnvironmentAttachment accountEnvironmentAttachment = new AccountEnvironmentAttachmentImpl();

		accountEnvironmentAttachment.setNew(true);
		accountEnvironmentAttachment.setPrimaryKey(accountEnvironmentAttachmentId);

		return accountEnvironmentAttachment;
	}

	/**
	 * Removes the account environment attachment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param accountEnvironmentAttachmentId the primary key of the account environment attachment
	 * @return the account environment attachment that was removed
	 * @throws com.liferay.osb.NoSuchAccountEnvironmentAttachmentException if a account environment attachment with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEnvironmentAttachment remove(
		long accountEnvironmentAttachmentId)
		throws NoSuchAccountEnvironmentAttachmentException, SystemException {
		return remove(Long.valueOf(accountEnvironmentAttachmentId));
	}

	/**
	 * Removes the account environment attachment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the account environment attachment
	 * @return the account environment attachment that was removed
	 * @throws com.liferay.osb.NoSuchAccountEnvironmentAttachmentException if a account environment attachment with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AccountEnvironmentAttachment remove(Serializable primaryKey)
		throws NoSuchAccountEnvironmentAttachmentException, SystemException {
		Session session = null;

		try {
			session = openSession();

			AccountEnvironmentAttachment accountEnvironmentAttachment = (AccountEnvironmentAttachment)session.get(AccountEnvironmentAttachmentImpl.class,
					primaryKey);

			if (accountEnvironmentAttachment == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAccountEnvironmentAttachmentException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(accountEnvironmentAttachment);
		}
		catch (NoSuchAccountEnvironmentAttachmentException nsee) {
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
	protected AccountEnvironmentAttachment removeImpl(
		AccountEnvironmentAttachment accountEnvironmentAttachment)
		throws SystemException {
		accountEnvironmentAttachment = toUnwrappedModel(accountEnvironmentAttachment);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, accountEnvironmentAttachment);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(accountEnvironmentAttachment);

		return accountEnvironmentAttachment;
	}

	@Override
	public AccountEnvironmentAttachment updateImpl(
		com.liferay.osb.model.AccountEnvironmentAttachment accountEnvironmentAttachment,
		boolean merge) throws SystemException {
		accountEnvironmentAttachment = toUnwrappedModel(accountEnvironmentAttachment);

		boolean isNew = accountEnvironmentAttachment.isNew();

		AccountEnvironmentAttachmentModelImpl accountEnvironmentAttachmentModelImpl =
			(AccountEnvironmentAttachmentModelImpl)accountEnvironmentAttachment;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, accountEnvironmentAttachment, merge);

			accountEnvironmentAttachment.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew ||
				!AccountEnvironmentAttachmentModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((accountEnvironmentAttachmentModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENVIRONMENTID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(accountEnvironmentAttachmentModelImpl.getOriginalAccountEnvironmentId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTENVIRONMENTID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENVIRONMENTID,
					args);

				args = new Object[] {
						Long.valueOf(accountEnvironmentAttachmentModelImpl.getAccountEnvironmentId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTENVIRONMENTID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENVIRONMENTID,
					args);
			}
		}

		EntityCacheUtil.putResult(AccountEnvironmentAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountEnvironmentAttachmentImpl.class,
			accountEnvironmentAttachment.getPrimaryKey(),
			accountEnvironmentAttachment);

		clearUniqueFindersCache(accountEnvironmentAttachment);
		cacheUniqueFindersCache(accountEnvironmentAttachment);

		return accountEnvironmentAttachment;
	}

	protected AccountEnvironmentAttachment toUnwrappedModel(
		AccountEnvironmentAttachment accountEnvironmentAttachment) {
		if (accountEnvironmentAttachment instanceof AccountEnvironmentAttachmentImpl) {
			return accountEnvironmentAttachment;
		}

		AccountEnvironmentAttachmentImpl accountEnvironmentAttachmentImpl = new AccountEnvironmentAttachmentImpl();

		accountEnvironmentAttachmentImpl.setNew(accountEnvironmentAttachment.isNew());
		accountEnvironmentAttachmentImpl.setPrimaryKey(accountEnvironmentAttachment.getPrimaryKey());

		accountEnvironmentAttachmentImpl.setAccountEnvironmentAttachmentId(accountEnvironmentAttachment.getAccountEnvironmentAttachmentId());
		accountEnvironmentAttachmentImpl.setUserId(accountEnvironmentAttachment.getUserId());
		accountEnvironmentAttachmentImpl.setUserName(accountEnvironmentAttachment.getUserName());
		accountEnvironmentAttachmentImpl.setCreateDate(accountEnvironmentAttachment.getCreateDate());
		accountEnvironmentAttachmentImpl.setModifiedDate(accountEnvironmentAttachment.getModifiedDate());
		accountEnvironmentAttachmentImpl.setAccountEnvironmentId(accountEnvironmentAttachment.getAccountEnvironmentId());
		accountEnvironmentAttachmentImpl.setFileName(accountEnvironmentAttachment.getFileName());
		accountEnvironmentAttachmentImpl.setFileSize(accountEnvironmentAttachment.getFileSize());
		accountEnvironmentAttachmentImpl.setType(accountEnvironmentAttachment.getType());

		return accountEnvironmentAttachmentImpl;
	}

	/**
	 * Returns the account environment attachment with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the account environment attachment
	 * @return the account environment attachment
	 * @throws com.liferay.portal.NoSuchModelException if a account environment attachment with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AccountEnvironmentAttachment findByPrimaryKey(
		Serializable primaryKey) throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the account environment attachment with the primary key or throws a {@link com.liferay.osb.NoSuchAccountEnvironmentAttachmentException} if it could not be found.
	 *
	 * @param accountEnvironmentAttachmentId the primary key of the account environment attachment
	 * @return the account environment attachment
	 * @throws com.liferay.osb.NoSuchAccountEnvironmentAttachmentException if a account environment attachment with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEnvironmentAttachment findByPrimaryKey(
		long accountEnvironmentAttachmentId)
		throws NoSuchAccountEnvironmentAttachmentException, SystemException {
		AccountEnvironmentAttachment accountEnvironmentAttachment = fetchByPrimaryKey(accountEnvironmentAttachmentId);

		if (accountEnvironmentAttachment == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					accountEnvironmentAttachmentId);
			}

			throw new NoSuchAccountEnvironmentAttachmentException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				accountEnvironmentAttachmentId);
		}

		return accountEnvironmentAttachment;
	}

	/**
	 * Returns the account environment attachment with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the account environment attachment
	 * @return the account environment attachment, or <code>null</code> if a account environment attachment with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AccountEnvironmentAttachment fetchByPrimaryKey(
		Serializable primaryKey) throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the account environment attachment with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param accountEnvironmentAttachmentId the primary key of the account environment attachment
	 * @return the account environment attachment, or <code>null</code> if a account environment attachment with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEnvironmentAttachment fetchByPrimaryKey(
		long accountEnvironmentAttachmentId) throws SystemException {
		AccountEnvironmentAttachment accountEnvironmentAttachment = (AccountEnvironmentAttachment)EntityCacheUtil.getResult(AccountEnvironmentAttachmentModelImpl.ENTITY_CACHE_ENABLED,
				AccountEnvironmentAttachmentImpl.class,
				accountEnvironmentAttachmentId);

		if (accountEnvironmentAttachment == _nullAccountEnvironmentAttachment) {
			return null;
		}

		if (accountEnvironmentAttachment == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				accountEnvironmentAttachment = (AccountEnvironmentAttachment)session.get(AccountEnvironmentAttachmentImpl.class,
						Long.valueOf(accountEnvironmentAttachmentId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (accountEnvironmentAttachment != null) {
					cacheResult(accountEnvironmentAttachment);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(AccountEnvironmentAttachmentModelImpl.ENTITY_CACHE_ENABLED,
						AccountEnvironmentAttachmentImpl.class,
						accountEnvironmentAttachmentId,
						_nullAccountEnvironmentAttachment);
				}

				closeSession(session);
			}
		}

		return accountEnvironmentAttachment;
	}

	/**
	 * Returns all the account environment attachments where accountEnvironmentId = &#63;.
	 *
	 * @param accountEnvironmentId the account environment ID
	 * @return the matching account environment attachments
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountEnvironmentAttachment> findByAccountEnvironmentId(
		long accountEnvironmentId) throws SystemException {
		return findByAccountEnvironmentId(accountEnvironmentId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account environment attachments where accountEnvironmentId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param accountEnvironmentId the account environment ID
	 * @param start the lower bound of the range of account environment attachments
	 * @param end the upper bound of the range of account environment attachments (not inclusive)
	 * @return the range of matching account environment attachments
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountEnvironmentAttachment> findByAccountEnvironmentId(
		long accountEnvironmentId, int start, int end)
		throws SystemException {
		return findByAccountEnvironmentId(accountEnvironmentId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the account environment attachments where accountEnvironmentId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param accountEnvironmentId the account environment ID
	 * @param start the lower bound of the range of account environment attachments
	 * @param end the upper bound of the range of account environment attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account environment attachments
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountEnvironmentAttachment> findByAccountEnvironmentId(
		long accountEnvironmentId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENVIRONMENTID;
			finderArgs = new Object[] { accountEnvironmentId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ACCOUNTENVIRONMENTID;
			finderArgs = new Object[] {
					accountEnvironmentId,
					
					start, end, orderByComparator
				};
		}

		List<AccountEnvironmentAttachment> list = (List<AccountEnvironmentAttachment>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AccountEnvironmentAttachment accountEnvironmentAttachment : list) {
				if ((accountEnvironmentId != accountEnvironmentAttachment.getAccountEnvironmentId())) {
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

			query.append(_SQL_SELECT_ACCOUNTENVIRONMENTATTACHMENT_WHERE);

			query.append(_FINDER_COLUMN_ACCOUNTENVIRONMENTID_ACCOUNTENVIRONMENTID_2);

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

				qPos.add(accountEnvironmentId);

				list = (List<AccountEnvironmentAttachment>)QueryUtil.list(q,
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
	 * Returns the first account environment attachment in the ordered set where accountEnvironmentId = &#63;.
	 *
	 * @param accountEnvironmentId the account environment ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account environment attachment
	 * @throws com.liferay.osb.NoSuchAccountEnvironmentAttachmentException if a matching account environment attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEnvironmentAttachment findByAccountEnvironmentId_First(
		long accountEnvironmentId, OrderByComparator orderByComparator)
		throws NoSuchAccountEnvironmentAttachmentException, SystemException {
		AccountEnvironmentAttachment accountEnvironmentAttachment = fetchByAccountEnvironmentId_First(accountEnvironmentId,
				orderByComparator);

		if (accountEnvironmentAttachment != null) {
			return accountEnvironmentAttachment;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEnvironmentId=");
		msg.append(accountEnvironmentId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountEnvironmentAttachmentException(msg.toString());
	}

	/**
	 * Returns the first account environment attachment in the ordered set where accountEnvironmentId = &#63;.
	 *
	 * @param accountEnvironmentId the account environment ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account environment attachment, or <code>null</code> if a matching account environment attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEnvironmentAttachment fetchByAccountEnvironmentId_First(
		long accountEnvironmentId, OrderByComparator orderByComparator)
		throws SystemException {
		List<AccountEnvironmentAttachment> list = findByAccountEnvironmentId(accountEnvironmentId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last account environment attachment in the ordered set where accountEnvironmentId = &#63;.
	 *
	 * @param accountEnvironmentId the account environment ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account environment attachment
	 * @throws com.liferay.osb.NoSuchAccountEnvironmentAttachmentException if a matching account environment attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEnvironmentAttachment findByAccountEnvironmentId_Last(
		long accountEnvironmentId, OrderByComparator orderByComparator)
		throws NoSuchAccountEnvironmentAttachmentException, SystemException {
		AccountEnvironmentAttachment accountEnvironmentAttachment = fetchByAccountEnvironmentId_Last(accountEnvironmentId,
				orderByComparator);

		if (accountEnvironmentAttachment != null) {
			return accountEnvironmentAttachment;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEnvironmentId=");
		msg.append(accountEnvironmentId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountEnvironmentAttachmentException(msg.toString());
	}

	/**
	 * Returns the last account environment attachment in the ordered set where accountEnvironmentId = &#63;.
	 *
	 * @param accountEnvironmentId the account environment ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account environment attachment, or <code>null</code> if a matching account environment attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEnvironmentAttachment fetchByAccountEnvironmentId_Last(
		long accountEnvironmentId, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByAccountEnvironmentId(accountEnvironmentId);

		List<AccountEnvironmentAttachment> list = findByAccountEnvironmentId(accountEnvironmentId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the account environment attachments before and after the current account environment attachment in the ordered set where accountEnvironmentId = &#63;.
	 *
	 * @param accountEnvironmentAttachmentId the primary key of the current account environment attachment
	 * @param accountEnvironmentId the account environment ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account environment attachment
	 * @throws com.liferay.osb.NoSuchAccountEnvironmentAttachmentException if a account environment attachment with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEnvironmentAttachment[] findByAccountEnvironmentId_PrevAndNext(
		long accountEnvironmentAttachmentId, long accountEnvironmentId,
		OrderByComparator orderByComparator)
		throws NoSuchAccountEnvironmentAttachmentException, SystemException {
		AccountEnvironmentAttachment accountEnvironmentAttachment = findByPrimaryKey(accountEnvironmentAttachmentId);

		Session session = null;

		try {
			session = openSession();

			AccountEnvironmentAttachment[] array = new AccountEnvironmentAttachmentImpl[3];

			array[0] = getByAccountEnvironmentId_PrevAndNext(session,
					accountEnvironmentAttachment, accountEnvironmentId,
					orderByComparator, true);

			array[1] = accountEnvironmentAttachment;

			array[2] = getByAccountEnvironmentId_PrevAndNext(session,
					accountEnvironmentAttachment, accountEnvironmentId,
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

	protected AccountEnvironmentAttachment getByAccountEnvironmentId_PrevAndNext(
		Session session,
		AccountEnvironmentAttachment accountEnvironmentAttachment,
		long accountEnvironmentId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ACCOUNTENVIRONMENTATTACHMENT_WHERE);

		query.append(_FINDER_COLUMN_ACCOUNTENVIRONMENTID_ACCOUNTENVIRONMENTID_2);

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

		qPos.add(accountEnvironmentId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(accountEnvironmentAttachment);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AccountEnvironmentAttachment> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the account environment attachment where accountEnvironmentId = &#63; and fileName = &#63; or throws a {@link com.liferay.osb.NoSuchAccountEnvironmentAttachmentException} if it could not be found.
	 *
	 * @param accountEnvironmentId the account environment ID
	 * @param fileName the file name
	 * @return the matching account environment attachment
	 * @throws com.liferay.osb.NoSuchAccountEnvironmentAttachmentException if a matching account environment attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEnvironmentAttachment findByAEI_FN(
		long accountEnvironmentId, String fileName)
		throws NoSuchAccountEnvironmentAttachmentException, SystemException {
		AccountEnvironmentAttachment accountEnvironmentAttachment = fetchByAEI_FN(accountEnvironmentId,
				fileName);

		if (accountEnvironmentAttachment == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("accountEnvironmentId=");
			msg.append(accountEnvironmentId);

			msg.append(", fileName=");
			msg.append(fileName);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchAccountEnvironmentAttachmentException(msg.toString());
		}

		return accountEnvironmentAttachment;
	}

	/**
	 * Returns the account environment attachment where accountEnvironmentId = &#63; and fileName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param accountEnvironmentId the account environment ID
	 * @param fileName the file name
	 * @return the matching account environment attachment, or <code>null</code> if a matching account environment attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEnvironmentAttachment fetchByAEI_FN(
		long accountEnvironmentId, String fileName) throws SystemException {
		return fetchByAEI_FN(accountEnvironmentId, fileName, true);
	}

	/**
	 * Returns the account environment attachment where accountEnvironmentId = &#63; and fileName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param accountEnvironmentId the account environment ID
	 * @param fileName the file name
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching account environment attachment, or <code>null</code> if a matching account environment attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEnvironmentAttachment fetchByAEI_FN(
		long accountEnvironmentId, String fileName, boolean retrieveFromCache)
		throws SystemException {
		Object[] finderArgs = new Object[] { accountEnvironmentId, fileName };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_AEI_FN,
					finderArgs, this);
		}

		if (result instanceof AccountEnvironmentAttachment) {
			AccountEnvironmentAttachment accountEnvironmentAttachment = (AccountEnvironmentAttachment)result;

			if ((accountEnvironmentId != accountEnvironmentAttachment.getAccountEnvironmentId()) ||
					!Validator.equals(fileName,
						accountEnvironmentAttachment.getFileName())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_ACCOUNTENVIRONMENTATTACHMENT_WHERE);

			query.append(_FINDER_COLUMN_AEI_FN_ACCOUNTENVIRONMENTID_2);

			if (fileName == null) {
				query.append(_FINDER_COLUMN_AEI_FN_FILENAME_1);
			}
			else {
				if (fileName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_AEI_FN_FILENAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_AEI_FN_FILENAME_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEnvironmentId);

				if (fileName != null) {
					qPos.add(fileName);
				}

				List<AccountEnvironmentAttachment> list = q.list();

				result = list;

				AccountEnvironmentAttachment accountEnvironmentAttachment = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_AEI_FN,
						finderArgs, list);
				}
				else {
					accountEnvironmentAttachment = list.get(0);

					cacheResult(accountEnvironmentAttachment);

					if ((accountEnvironmentAttachment.getAccountEnvironmentId() != accountEnvironmentId) ||
							(accountEnvironmentAttachment.getFileName() == null) ||
							!accountEnvironmentAttachment.getFileName()
															 .equals(fileName)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_AEI_FN,
							finderArgs, accountEnvironmentAttachment);
					}
				}

				return accountEnvironmentAttachment;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_AEI_FN,
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
				return (AccountEnvironmentAttachment)result;
			}
		}
	}

	/**
	 * Returns the account environment attachment where accountEnvironmentId = &#63; and type = &#63; or throws a {@link com.liferay.osb.NoSuchAccountEnvironmentAttachmentException} if it could not be found.
	 *
	 * @param accountEnvironmentId the account environment ID
	 * @param type the type
	 * @return the matching account environment attachment
	 * @throws com.liferay.osb.NoSuchAccountEnvironmentAttachmentException if a matching account environment attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEnvironmentAttachment findByAEI_T(long accountEnvironmentId,
		int type)
		throws NoSuchAccountEnvironmentAttachmentException, SystemException {
		AccountEnvironmentAttachment accountEnvironmentAttachment = fetchByAEI_T(accountEnvironmentId,
				type);

		if (accountEnvironmentAttachment == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("accountEnvironmentId=");
			msg.append(accountEnvironmentId);

			msg.append(", type=");
			msg.append(type);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchAccountEnvironmentAttachmentException(msg.toString());
		}

		return accountEnvironmentAttachment;
	}

	/**
	 * Returns the account environment attachment where accountEnvironmentId = &#63; and type = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param accountEnvironmentId the account environment ID
	 * @param type the type
	 * @return the matching account environment attachment, or <code>null</code> if a matching account environment attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEnvironmentAttachment fetchByAEI_T(
		long accountEnvironmentId, int type) throws SystemException {
		return fetchByAEI_T(accountEnvironmentId, type, true);
	}

	/**
	 * Returns the account environment attachment where accountEnvironmentId = &#63; and type = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param accountEnvironmentId the account environment ID
	 * @param type the type
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching account environment attachment, or <code>null</code> if a matching account environment attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEnvironmentAttachment fetchByAEI_T(
		long accountEnvironmentId, int type, boolean retrieveFromCache)
		throws SystemException {
		Object[] finderArgs = new Object[] { accountEnvironmentId, type };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_AEI_T,
					finderArgs, this);
		}

		if (result instanceof AccountEnvironmentAttachment) {
			AccountEnvironmentAttachment accountEnvironmentAttachment = (AccountEnvironmentAttachment)result;

			if ((accountEnvironmentId != accountEnvironmentAttachment.getAccountEnvironmentId()) ||
					(type != accountEnvironmentAttachment.getType())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_ACCOUNTENVIRONMENTATTACHMENT_WHERE);

			query.append(_FINDER_COLUMN_AEI_T_ACCOUNTENVIRONMENTID_2);

			query.append(_FINDER_COLUMN_AEI_T_TYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEnvironmentId);

				qPos.add(type);

				List<AccountEnvironmentAttachment> list = q.list();

				result = list;

				AccountEnvironmentAttachment accountEnvironmentAttachment = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_AEI_T,
						finderArgs, list);
				}
				else {
					accountEnvironmentAttachment = list.get(0);

					cacheResult(accountEnvironmentAttachment);

					if ((accountEnvironmentAttachment.getAccountEnvironmentId() != accountEnvironmentId) ||
							(accountEnvironmentAttachment.getType() != type)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_AEI_T,
							finderArgs, accountEnvironmentAttachment);
					}
				}

				return accountEnvironmentAttachment;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_AEI_T,
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
				return (AccountEnvironmentAttachment)result;
			}
		}
	}

	/**
	 * Returns all the account environment attachments.
	 *
	 * @return the account environment attachments
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountEnvironmentAttachment> findAll()
		throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account environment attachments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of account environment attachments
	 * @param end the upper bound of the range of account environment attachments (not inclusive)
	 * @return the range of account environment attachments
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountEnvironmentAttachment> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the account environment attachments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of account environment attachments
	 * @param end the upper bound of the range of account environment attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of account environment attachments
	 * @throws SystemException if a system exception occurred
	 */
	public List<AccountEnvironmentAttachment> findAll(int start, int end,
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

		List<AccountEnvironmentAttachment> list = (List<AccountEnvironmentAttachment>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_ACCOUNTENVIRONMENTATTACHMENT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ACCOUNTENVIRONMENTATTACHMENT;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<AccountEnvironmentAttachment>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<AccountEnvironmentAttachment>)QueryUtil.list(q,
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
	 * Removes all the account environment attachments where accountEnvironmentId = &#63; from the database.
	 *
	 * @param accountEnvironmentId the account environment ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByAccountEnvironmentId(long accountEnvironmentId)
		throws SystemException {
		for (AccountEnvironmentAttachment accountEnvironmentAttachment : findByAccountEnvironmentId(
				accountEnvironmentId)) {
			remove(accountEnvironmentAttachment);
		}
	}

	/**
	 * Removes the account environment attachment where accountEnvironmentId = &#63; and fileName = &#63; from the database.
	 *
	 * @param accountEnvironmentId the account environment ID
	 * @param fileName the file name
	 * @return the account environment attachment that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEnvironmentAttachment removeByAEI_FN(
		long accountEnvironmentId, String fileName)
		throws NoSuchAccountEnvironmentAttachmentException, SystemException {
		AccountEnvironmentAttachment accountEnvironmentAttachment = findByAEI_FN(accountEnvironmentId,
				fileName);

		return remove(accountEnvironmentAttachment);
	}

	/**
	 * Removes the account environment attachment where accountEnvironmentId = &#63; and type = &#63; from the database.
	 *
	 * @param accountEnvironmentId the account environment ID
	 * @param type the type
	 * @return the account environment attachment that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public AccountEnvironmentAttachment removeByAEI_T(
		long accountEnvironmentId, int type)
		throws NoSuchAccountEnvironmentAttachmentException, SystemException {
		AccountEnvironmentAttachment accountEnvironmentAttachment = findByAEI_T(accountEnvironmentId,
				type);

		return remove(accountEnvironmentAttachment);
	}

	/**
	 * Removes all the account environment attachments from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (AccountEnvironmentAttachment accountEnvironmentAttachment : findAll()) {
			remove(accountEnvironmentAttachment);
		}
	}

	/**
	 * Returns the number of account environment attachments where accountEnvironmentId = &#63;.
	 *
	 * @param accountEnvironmentId the account environment ID
	 * @return the number of matching account environment attachments
	 * @throws SystemException if a system exception occurred
	 */
	public int countByAccountEnvironmentId(long accountEnvironmentId)
		throws SystemException {
		Object[] finderArgs = new Object[] { accountEnvironmentId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_ACCOUNTENVIRONMENTID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ACCOUNTENVIRONMENTATTACHMENT_WHERE);

			query.append(_FINDER_COLUMN_ACCOUNTENVIRONMENTID_ACCOUNTENVIRONMENTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEnvironmentId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_ACCOUNTENVIRONMENTID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of account environment attachments where accountEnvironmentId = &#63; and fileName = &#63;.
	 *
	 * @param accountEnvironmentId the account environment ID
	 * @param fileName the file name
	 * @return the number of matching account environment attachments
	 * @throws SystemException if a system exception occurred
	 */
	public int countByAEI_FN(long accountEnvironmentId, String fileName)
		throws SystemException {
		Object[] finderArgs = new Object[] { accountEnvironmentId, fileName };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_AEI_FN,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ACCOUNTENVIRONMENTATTACHMENT_WHERE);

			query.append(_FINDER_COLUMN_AEI_FN_ACCOUNTENVIRONMENTID_2);

			if (fileName == null) {
				query.append(_FINDER_COLUMN_AEI_FN_FILENAME_1);
			}
			else {
				if (fileName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_AEI_FN_FILENAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_AEI_FN_FILENAME_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEnvironmentId);

				if (fileName != null) {
					qPos.add(fileName);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_AEI_FN,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of account environment attachments where accountEnvironmentId = &#63; and type = &#63;.
	 *
	 * @param accountEnvironmentId the account environment ID
	 * @param type the type
	 * @return the number of matching account environment attachments
	 * @throws SystemException if a system exception occurred
	 */
	public int countByAEI_T(long accountEnvironmentId, int type)
		throws SystemException {
		Object[] finderArgs = new Object[] { accountEnvironmentId, type };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_AEI_T,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ACCOUNTENVIRONMENTATTACHMENT_WHERE);

			query.append(_FINDER_COLUMN_AEI_T_ACCOUNTENVIRONMENTID_2);

			query.append(_FINDER_COLUMN_AEI_T_TYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEnvironmentId);

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_AEI_T,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of account environment attachments.
	 *
	 * @return the number of account environment attachments
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ACCOUNTENVIRONMENTATTACHMENT);

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
	 * Initializes the account environment attachment persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.osb.model.AccountEnvironmentAttachment")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<AccountEnvironmentAttachment>> listenersList = new ArrayList<ModelListener<AccountEnvironmentAttachment>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<AccountEnvironmentAttachment>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(AccountEnvironmentAttachmentImpl.class.getName());
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
	private static final String _SQL_SELECT_ACCOUNTENVIRONMENTATTACHMENT = "SELECT accountEnvironmentAttachment FROM AccountEnvironmentAttachment accountEnvironmentAttachment";
	private static final String _SQL_SELECT_ACCOUNTENVIRONMENTATTACHMENT_WHERE = "SELECT accountEnvironmentAttachment FROM AccountEnvironmentAttachment accountEnvironmentAttachment WHERE ";
	private static final String _SQL_COUNT_ACCOUNTENVIRONMENTATTACHMENT = "SELECT COUNT(accountEnvironmentAttachment) FROM AccountEnvironmentAttachment accountEnvironmentAttachment";
	private static final String _SQL_COUNT_ACCOUNTENVIRONMENTATTACHMENT_WHERE = "SELECT COUNT(accountEnvironmentAttachment) FROM AccountEnvironmentAttachment accountEnvironmentAttachment WHERE ";
	private static final String _FINDER_COLUMN_ACCOUNTENVIRONMENTID_ACCOUNTENVIRONMENTID_2 =
		"accountEnvironmentAttachment.accountEnvironmentId = ?";
	private static final String _FINDER_COLUMN_AEI_FN_ACCOUNTENVIRONMENTID_2 = "accountEnvironmentAttachment.accountEnvironmentId = ? AND ";
	private static final String _FINDER_COLUMN_AEI_FN_FILENAME_1 = "accountEnvironmentAttachment.fileName IS NULL";
	private static final String _FINDER_COLUMN_AEI_FN_FILENAME_2 = "accountEnvironmentAttachment.fileName = ?";
	private static final String _FINDER_COLUMN_AEI_FN_FILENAME_3 = "(accountEnvironmentAttachment.fileName IS NULL OR accountEnvironmentAttachment.fileName = ?)";
	private static final String _FINDER_COLUMN_AEI_T_ACCOUNTENVIRONMENTID_2 = "accountEnvironmentAttachment.accountEnvironmentId = ? AND ";
	private static final String _FINDER_COLUMN_AEI_T_TYPE_2 = "accountEnvironmentAttachment.type = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "accountEnvironmentAttachment.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AccountEnvironmentAttachment exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No AccountEnvironmentAttachment exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(AccountEnvironmentAttachmentPersistenceImpl.class);
	private static AccountEnvironmentAttachment _nullAccountEnvironmentAttachment =
		new AccountEnvironmentAttachmentImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<AccountEnvironmentAttachment> toCacheModel() {
				return _nullAccountEnvironmentAttachmentCacheModel;
			}
		};

	private static CacheModel<AccountEnvironmentAttachment> _nullAccountEnvironmentAttachmentCacheModel =
		new CacheModel<AccountEnvironmentAttachment>() {
			public AccountEnvironmentAttachment toEntityModel() {
				return _nullAccountEnvironmentAttachment;
			}
		};
}