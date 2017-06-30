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

import com.liferay.osb.NoSuchAuditEntryException;
import com.liferay.osb.model.AuditEntry;
import com.liferay.osb.model.impl.AuditEntryImpl;
import com.liferay.osb.model.impl.AuditEntryModelImpl;

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
import com.liferay.portal.kernel.util.CalendarUtil;
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
import java.util.Date;
import java.util.List;

/**
 * The persistence implementation for the audit entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AuditEntryPersistence
 * @see AuditEntryUtil
 * @generated
 */
public class AuditEntryPersistenceImpl extends BasePersistenceImpl<AuditEntry>
	implements AuditEntryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AuditEntryUtil} to access the audit entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AuditEntryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GTCD_C = new FinderPath(AuditEntryModelImpl.ENTITY_CACHE_ENABLED,
			AuditEntryModelImpl.FINDER_CACHE_ENABLED, AuditEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGtCD_C",
			new String[] {
				Date.class.getName(), Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_GTCD_C = new FinderPath(AuditEntryModelImpl.ENTITY_CACHE_ENABLED,
			AuditEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByGtCD_C",
			new String[] { Date.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C_V = new FinderPath(AuditEntryModelImpl.ENTITY_CACHE_ENABLED,
			AuditEntryModelImpl.FINDER_CACHE_ENABLED, AuditEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_C_V",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_V = new FinderPath(AuditEntryModelImpl.ENTITY_CACHE_ENABLED,
			AuditEntryModelImpl.FINDER_CACHE_ENABLED, AuditEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_C_V",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			},
			AuditEntryModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			AuditEntryModelImpl.CLASSPK_COLUMN_BITMASK |
			AuditEntryModelImpl.VISIBILITY_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_C_V = new FinderPath(AuditEntryModelImpl.ENTITY_CACHE_ENABLED,
			AuditEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_C_V",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_C_C_V = new FinderPath(AuditEntryModelImpl.ENTITY_CACHE_ENABLED,
			AuditEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByC_C_V",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_FC_FC_F = new FinderPath(AuditEntryModelImpl.ENTITY_CACHE_ENABLED,
			AuditEntryModelImpl.FINDER_CACHE_ENABLED, AuditEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByFC_FC_F",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FC_FC_F =
		new FinderPath(AuditEntryModelImpl.ENTITY_CACHE_ENABLED,
			AuditEntryModelImpl.FINDER_CACHE_ENABLED, AuditEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByFC_FC_F",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			},
			AuditEntryModelImpl.FIELDCLASSNAMEID_COLUMN_BITMASK |
			AuditEntryModelImpl.FIELDCLASSPK_COLUMN_BITMASK |
			AuditEntryModelImpl.FIELD_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_FC_FC_F = new FinderPath(AuditEntryModelImpl.ENTITY_CACHE_ENABLED,
			AuditEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByFC_FC_F",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C_F_A = new FinderPath(AuditEntryModelImpl.ENTITY_CACHE_ENABLED,
			AuditEntryModelImpl.FINDER_CACHE_ENABLED, AuditEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_C_F_A",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_F_A =
		new FinderPath(AuditEntryModelImpl.ENTITY_CACHE_ENABLED,
			AuditEntryModelImpl.FINDER_CACHE_ENABLED, AuditEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_C_F_A",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName()
			},
			AuditEntryModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			AuditEntryModelImpl.CLASSPK_COLUMN_BITMASK |
			AuditEntryModelImpl.FIELD_COLUMN_BITMASK |
			AuditEntryModelImpl.ACTION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_C_F_A = new FinderPath(AuditEntryModelImpl.ENTITY_CACHE_ENABLED,
			AuditEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_C_F_A",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AuditEntryModelImpl.ENTITY_CACHE_ENABLED,
			AuditEntryModelImpl.FINDER_CACHE_ENABLED, AuditEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AuditEntryModelImpl.ENTITY_CACHE_ENABLED,
			AuditEntryModelImpl.FINDER_CACHE_ENABLED, AuditEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AuditEntryModelImpl.ENTITY_CACHE_ENABLED,
			AuditEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the audit entry in the entity cache if it is enabled.
	 *
	 * @param auditEntry the audit entry
	 */
	public void cacheResult(AuditEntry auditEntry) {
		EntityCacheUtil.putResult(AuditEntryModelImpl.ENTITY_CACHE_ENABLED,
			AuditEntryImpl.class, auditEntry.getPrimaryKey(), auditEntry);

		auditEntry.resetOriginalValues();
	}

	/**
	 * Caches the audit entries in the entity cache if it is enabled.
	 *
	 * @param auditEntries the audit entries
	 */
	public void cacheResult(List<AuditEntry> auditEntries) {
		for (AuditEntry auditEntry : auditEntries) {
			if (EntityCacheUtil.getResult(
						AuditEntryModelImpl.ENTITY_CACHE_ENABLED,
						AuditEntryImpl.class, auditEntry.getPrimaryKey()) == null) {
				cacheResult(auditEntry);
			}
			else {
				auditEntry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all audit entries.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(AuditEntryImpl.class.getName());
		}

		EntityCacheUtil.clearCache(AuditEntryImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the audit entry.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AuditEntry auditEntry) {
		EntityCacheUtil.removeResult(AuditEntryModelImpl.ENTITY_CACHE_ENABLED,
			AuditEntryImpl.class, auditEntry.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<AuditEntry> auditEntries) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AuditEntry auditEntry : auditEntries) {
			EntityCacheUtil.removeResult(AuditEntryModelImpl.ENTITY_CACHE_ENABLED,
				AuditEntryImpl.class, auditEntry.getPrimaryKey());
		}
	}

	/**
	 * Creates a new audit entry with the primary key. Does not add the audit entry to the database.
	 *
	 * @param auditEntryId the primary key for the new audit entry
	 * @return the new audit entry
	 */
	public AuditEntry create(long auditEntryId) {
		AuditEntry auditEntry = new AuditEntryImpl();

		auditEntry.setNew(true);
		auditEntry.setPrimaryKey(auditEntryId);

		return auditEntry;
	}

	/**
	 * Removes the audit entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param auditEntryId the primary key of the audit entry
	 * @return the audit entry that was removed
	 * @throws com.liferay.osb.NoSuchAuditEntryException if a audit entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AuditEntry remove(long auditEntryId)
		throws NoSuchAuditEntryException, SystemException {
		return remove(Long.valueOf(auditEntryId));
	}

	/**
	 * Removes the audit entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the audit entry
	 * @return the audit entry that was removed
	 * @throws com.liferay.osb.NoSuchAuditEntryException if a audit entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AuditEntry remove(Serializable primaryKey)
		throws NoSuchAuditEntryException, SystemException {
		Session session = null;

		try {
			session = openSession();

			AuditEntry auditEntry = (AuditEntry)session.get(AuditEntryImpl.class,
					primaryKey);

			if (auditEntry == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAuditEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(auditEntry);
		}
		catch (NoSuchAuditEntryException nsee) {
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
	protected AuditEntry removeImpl(AuditEntry auditEntry)
		throws SystemException {
		auditEntry = toUnwrappedModel(auditEntry);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, auditEntry);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(auditEntry);

		return auditEntry;
	}

	@Override
	public AuditEntry updateImpl(com.liferay.osb.model.AuditEntry auditEntry,
		boolean merge) throws SystemException {
		auditEntry = toUnwrappedModel(auditEntry);

		boolean isNew = auditEntry.isNew();

		AuditEntryModelImpl auditEntryModelImpl = (AuditEntryModelImpl)auditEntry;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, auditEntry, merge);

			auditEntry.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !AuditEntryModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((auditEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_V.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(auditEntryModelImpl.getOriginalClassNameId()),
						Long.valueOf(auditEntryModelImpl.getOriginalClassPK()),
						Integer.valueOf(auditEntryModelImpl.getOriginalVisibility())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C_V, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_V,
					args);

				args = new Object[] {
						Long.valueOf(auditEntryModelImpl.getClassNameId()),
						Long.valueOf(auditEntryModelImpl.getClassPK()),
						Integer.valueOf(auditEntryModelImpl.getVisibility())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C_V, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_V,
					args);
			}

			if ((auditEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FC_FC_F.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(auditEntryModelImpl.getOriginalFieldClassNameId()),
						Long.valueOf(auditEntryModelImpl.getOriginalFieldClassPK()),
						Integer.valueOf(auditEntryModelImpl.getOriginalField())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_FC_FC_F, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FC_FC_F,
					args);

				args = new Object[] {
						Long.valueOf(auditEntryModelImpl.getFieldClassNameId()),
						Long.valueOf(auditEntryModelImpl.getFieldClassPK()),
						Integer.valueOf(auditEntryModelImpl.getField())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_FC_FC_F, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FC_FC_F,
					args);
			}

			if ((auditEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_F_A.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(auditEntryModelImpl.getOriginalClassNameId()),
						Long.valueOf(auditEntryModelImpl.getOriginalClassPK()),
						Integer.valueOf(auditEntryModelImpl.getOriginalField()),
						Integer.valueOf(auditEntryModelImpl.getOriginalAction())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C_F_A, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_F_A,
					args);

				args = new Object[] {
						Long.valueOf(auditEntryModelImpl.getClassNameId()),
						Long.valueOf(auditEntryModelImpl.getClassPK()),
						Integer.valueOf(auditEntryModelImpl.getField()),
						Integer.valueOf(auditEntryModelImpl.getAction())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C_F_A, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_F_A,
					args);
			}
		}

		EntityCacheUtil.putResult(AuditEntryModelImpl.ENTITY_CACHE_ENABLED,
			AuditEntryImpl.class, auditEntry.getPrimaryKey(), auditEntry);

		return auditEntry;
	}

	protected AuditEntry toUnwrappedModel(AuditEntry auditEntry) {
		if (auditEntry instanceof AuditEntryImpl) {
			return auditEntry;
		}

		AuditEntryImpl auditEntryImpl = new AuditEntryImpl();

		auditEntryImpl.setNew(auditEntry.isNew());
		auditEntryImpl.setPrimaryKey(auditEntry.getPrimaryKey());

		auditEntryImpl.setAuditEntryId(auditEntry.getAuditEntryId());
		auditEntryImpl.setUserId(auditEntry.getUserId());
		auditEntryImpl.setUserName(auditEntry.getUserName());
		auditEntryImpl.setCreateDate(auditEntry.getCreateDate());
		auditEntryImpl.setClassNameId(auditEntry.getClassNameId());
		auditEntryImpl.setClassPK(auditEntry.getClassPK());
		auditEntryImpl.setPreviousAuditEntryId(auditEntry.getPreviousAuditEntryId());
		auditEntryImpl.setAuditSetId(auditEntry.getAuditSetId());
		auditEntryImpl.setFieldClassNameId(auditEntry.getFieldClassNameId());
		auditEntryImpl.setFieldClassPK(auditEntry.getFieldClassPK());
		auditEntryImpl.setAction(auditEntry.getAction());
		auditEntryImpl.setField(auditEntry.getField());
		auditEntryImpl.setVisibility(auditEntry.getVisibility());
		auditEntryImpl.setOldLabel(auditEntry.getOldLabel());
		auditEntryImpl.setOldValue(auditEntry.getOldValue());
		auditEntryImpl.setNewLabel(auditEntry.getNewLabel());
		auditEntryImpl.setNewValue(auditEntry.getNewValue());
		auditEntryImpl.setI18n(auditEntry.isI18n());

		return auditEntryImpl;
	}

	/**
	 * Returns the audit entry with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the audit entry
	 * @return the audit entry
	 * @throws com.liferay.portal.NoSuchModelException if a audit entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AuditEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the audit entry with the primary key or throws a {@link com.liferay.osb.NoSuchAuditEntryException} if it could not be found.
	 *
	 * @param auditEntryId the primary key of the audit entry
	 * @return the audit entry
	 * @throws com.liferay.osb.NoSuchAuditEntryException if a audit entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AuditEntry findByPrimaryKey(long auditEntryId)
		throws NoSuchAuditEntryException, SystemException {
		AuditEntry auditEntry = fetchByPrimaryKey(auditEntryId);

		if (auditEntry == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + auditEntryId);
			}

			throw new NoSuchAuditEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				auditEntryId);
		}

		return auditEntry;
	}

	/**
	 * Returns the audit entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the audit entry
	 * @return the audit entry, or <code>null</code> if a audit entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AuditEntry fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the audit entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param auditEntryId the primary key of the audit entry
	 * @return the audit entry, or <code>null</code> if a audit entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AuditEntry fetchByPrimaryKey(long auditEntryId)
		throws SystemException {
		AuditEntry auditEntry = (AuditEntry)EntityCacheUtil.getResult(AuditEntryModelImpl.ENTITY_CACHE_ENABLED,
				AuditEntryImpl.class, auditEntryId);

		if (auditEntry == _nullAuditEntry) {
			return null;
		}

		if (auditEntry == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				auditEntry = (AuditEntry)session.get(AuditEntryImpl.class,
						Long.valueOf(auditEntryId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (auditEntry != null) {
					cacheResult(auditEntry);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(AuditEntryModelImpl.ENTITY_CACHE_ENABLED,
						AuditEntryImpl.class, auditEntryId, _nullAuditEntry);
				}

				closeSession(session);
			}
		}

		return auditEntry;
	}

	/**
	 * Returns all the audit entries where createDate &gt; &#63; and classNameId = &#63;.
	 *
	 * @param createDate the create date
	 * @param classNameId the class name ID
	 * @return the matching audit entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<AuditEntry> findByGtCD_C(Date createDate, long classNameId)
		throws SystemException {
		return findByGtCD_C(createDate, classNameId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the audit entries where createDate &gt; &#63; and classNameId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param createDate the create date
	 * @param classNameId the class name ID
	 * @param start the lower bound of the range of audit entries
	 * @param end the upper bound of the range of audit entries (not inclusive)
	 * @return the range of matching audit entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<AuditEntry> findByGtCD_C(Date createDate, long classNameId,
		int start, int end) throws SystemException {
		return findByGtCD_C(createDate, classNameId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the audit entries where createDate &gt; &#63; and classNameId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param createDate the create date
	 * @param classNameId the class name ID
	 * @param start the lower bound of the range of audit entries
	 * @param end the upper bound of the range of audit entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching audit entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<AuditEntry> findByGtCD_C(Date createDate, long classNameId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GTCD_C;
		finderArgs = new Object[] {
				createDate, classNameId,
				
				start, end, orderByComparator
			};

		List<AuditEntry> list = (List<AuditEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AuditEntry auditEntry : list) {
				if (!Validator.equals(createDate, auditEntry.getCreateDate()) ||
						(classNameId != auditEntry.getClassNameId())) {
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

			query.append(_SQL_SELECT_AUDITENTRY_WHERE);

			if (createDate == null) {
				query.append(_FINDER_COLUMN_GTCD_C_CREATEDATE_1);
			}
			else {
				query.append(_FINDER_COLUMN_GTCD_C_CREATEDATE_2);
			}

			query.append(_FINDER_COLUMN_GTCD_C_CLASSNAMEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(AuditEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (createDate != null) {
					qPos.add(CalendarUtil.getTimestamp(createDate));
				}

				qPos.add(classNameId);

				list = (List<AuditEntry>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns the first audit entry in the ordered set where createDate &gt; &#63; and classNameId = &#63;.
	 *
	 * @param createDate the create date
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching audit entry
	 * @throws com.liferay.osb.NoSuchAuditEntryException if a matching audit entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AuditEntry findByGtCD_C_First(Date createDate, long classNameId,
		OrderByComparator orderByComparator)
		throws NoSuchAuditEntryException, SystemException {
		AuditEntry auditEntry = fetchByGtCD_C_First(createDate, classNameId,
				orderByComparator);

		if (auditEntry != null) {
			return auditEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("createDate=");
		msg.append(createDate);

		msg.append(", classNameId=");
		msg.append(classNameId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAuditEntryException(msg.toString());
	}

	/**
	 * Returns the first audit entry in the ordered set where createDate &gt; &#63; and classNameId = &#63;.
	 *
	 * @param createDate the create date
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching audit entry, or <code>null</code> if a matching audit entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AuditEntry fetchByGtCD_C_First(Date createDate, long classNameId,
		OrderByComparator orderByComparator) throws SystemException {
		List<AuditEntry> list = findByGtCD_C(createDate, classNameId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last audit entry in the ordered set where createDate &gt; &#63; and classNameId = &#63;.
	 *
	 * @param createDate the create date
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching audit entry
	 * @throws com.liferay.osb.NoSuchAuditEntryException if a matching audit entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AuditEntry findByGtCD_C_Last(Date createDate, long classNameId,
		OrderByComparator orderByComparator)
		throws NoSuchAuditEntryException, SystemException {
		AuditEntry auditEntry = fetchByGtCD_C_Last(createDate, classNameId,
				orderByComparator);

		if (auditEntry != null) {
			return auditEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("createDate=");
		msg.append(createDate);

		msg.append(", classNameId=");
		msg.append(classNameId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAuditEntryException(msg.toString());
	}

	/**
	 * Returns the last audit entry in the ordered set where createDate &gt; &#63; and classNameId = &#63;.
	 *
	 * @param createDate the create date
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching audit entry, or <code>null</code> if a matching audit entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AuditEntry fetchByGtCD_C_Last(Date createDate, long classNameId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByGtCD_C(createDate, classNameId);

		List<AuditEntry> list = findByGtCD_C(createDate, classNameId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the audit entries before and after the current audit entry in the ordered set where createDate &gt; &#63; and classNameId = &#63;.
	 *
	 * @param auditEntryId the primary key of the current audit entry
	 * @param createDate the create date
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next audit entry
	 * @throws com.liferay.osb.NoSuchAuditEntryException if a audit entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AuditEntry[] findByGtCD_C_PrevAndNext(long auditEntryId,
		Date createDate, long classNameId, OrderByComparator orderByComparator)
		throws NoSuchAuditEntryException, SystemException {
		AuditEntry auditEntry = findByPrimaryKey(auditEntryId);

		Session session = null;

		try {
			session = openSession();

			AuditEntry[] array = new AuditEntryImpl[3];

			array[0] = getByGtCD_C_PrevAndNext(session, auditEntry, createDate,
					classNameId, orderByComparator, true);

			array[1] = auditEntry;

			array[2] = getByGtCD_C_PrevAndNext(session, auditEntry, createDate,
					classNameId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AuditEntry getByGtCD_C_PrevAndNext(Session session,
		AuditEntry auditEntry, Date createDate, long classNameId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_AUDITENTRY_WHERE);

		if (createDate == null) {
			query.append(_FINDER_COLUMN_GTCD_C_CREATEDATE_1);
		}
		else {
			query.append(_FINDER_COLUMN_GTCD_C_CREATEDATE_2);
		}

		query.append(_FINDER_COLUMN_GTCD_C_CLASSNAMEID_2);

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
			query.append(AuditEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (createDate != null) {
			qPos.add(CalendarUtil.getTimestamp(createDate));
		}

		qPos.add(classNameId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(auditEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AuditEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the audit entries where classNameId = &#63; and classPK = &#63; and visibility = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param visibility the visibility
	 * @return the matching audit entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<AuditEntry> findByC_C_V(long classNameId, long classPK,
		int visibility) throws SystemException {
		return findByC_C_V(classNameId, classPK, visibility, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the audit entries where classNameId = &#63; and classPK = &#63; and visibility = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param visibility the visibility
	 * @param start the lower bound of the range of audit entries
	 * @param end the upper bound of the range of audit entries (not inclusive)
	 * @return the range of matching audit entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<AuditEntry> findByC_C_V(long classNameId, long classPK,
		int visibility, int start, int end) throws SystemException {
		return findByC_C_V(classNameId, classPK, visibility, start, end, null);
	}

	/**
	 * Returns an ordered range of all the audit entries where classNameId = &#63; and classPK = &#63; and visibility = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param visibility the visibility
	 * @param start the lower bound of the range of audit entries
	 * @param end the upper bound of the range of audit entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching audit entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<AuditEntry> findByC_C_V(long classNameId, long classPK,
		int visibility, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_V;
			finderArgs = new Object[] { classNameId, classPK, visibility };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C_V;
			finderArgs = new Object[] {
					classNameId, classPK, visibility,
					
					start, end, orderByComparator
				};
		}

		List<AuditEntry> list = (List<AuditEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AuditEntry auditEntry : list) {
				if ((classNameId != auditEntry.getClassNameId()) ||
						(classPK != auditEntry.getClassPK()) ||
						(visibility != auditEntry.getVisibility())) {
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

			query.append(_SQL_SELECT_AUDITENTRY_WHERE);

			query.append(_FINDER_COLUMN_C_C_V_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_V_CLASSPK_2);

			query.append(_FINDER_COLUMN_C_C_V_VISIBILITY_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(AuditEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				qPos.add(visibility);

				list = (List<AuditEntry>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns the first audit entry in the ordered set where classNameId = &#63; and classPK = &#63; and visibility = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param visibility the visibility
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching audit entry
	 * @throws com.liferay.osb.NoSuchAuditEntryException if a matching audit entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AuditEntry findByC_C_V_First(long classNameId, long classPK,
		int visibility, OrderByComparator orderByComparator)
		throws NoSuchAuditEntryException, SystemException {
		AuditEntry auditEntry = fetchByC_C_V_First(classNameId, classPK,
				visibility, orderByComparator);

		if (auditEntry != null) {
			return auditEntry;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(", visibility=");
		msg.append(visibility);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAuditEntryException(msg.toString());
	}

	/**
	 * Returns the first audit entry in the ordered set where classNameId = &#63; and classPK = &#63; and visibility = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param visibility the visibility
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching audit entry, or <code>null</code> if a matching audit entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AuditEntry fetchByC_C_V_First(long classNameId, long classPK,
		int visibility, OrderByComparator orderByComparator)
		throws SystemException {
		List<AuditEntry> list = findByC_C_V(classNameId, classPK, visibility,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last audit entry in the ordered set where classNameId = &#63; and classPK = &#63; and visibility = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param visibility the visibility
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching audit entry
	 * @throws com.liferay.osb.NoSuchAuditEntryException if a matching audit entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AuditEntry findByC_C_V_Last(long classNameId, long classPK,
		int visibility, OrderByComparator orderByComparator)
		throws NoSuchAuditEntryException, SystemException {
		AuditEntry auditEntry = fetchByC_C_V_Last(classNameId, classPK,
				visibility, orderByComparator);

		if (auditEntry != null) {
			return auditEntry;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(", visibility=");
		msg.append(visibility);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAuditEntryException(msg.toString());
	}

	/**
	 * Returns the last audit entry in the ordered set where classNameId = &#63; and classPK = &#63; and visibility = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param visibility the visibility
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching audit entry, or <code>null</code> if a matching audit entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AuditEntry fetchByC_C_V_Last(long classNameId, long classPK,
		int visibility, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByC_C_V(classNameId, classPK, visibility);

		List<AuditEntry> list = findByC_C_V(classNameId, classPK, visibility,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the audit entries before and after the current audit entry in the ordered set where classNameId = &#63; and classPK = &#63; and visibility = &#63;.
	 *
	 * @param auditEntryId the primary key of the current audit entry
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param visibility the visibility
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next audit entry
	 * @throws com.liferay.osb.NoSuchAuditEntryException if a audit entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AuditEntry[] findByC_C_V_PrevAndNext(long auditEntryId,
		long classNameId, long classPK, int visibility,
		OrderByComparator orderByComparator)
		throws NoSuchAuditEntryException, SystemException {
		AuditEntry auditEntry = findByPrimaryKey(auditEntryId);

		Session session = null;

		try {
			session = openSession();

			AuditEntry[] array = new AuditEntryImpl[3];

			array[0] = getByC_C_V_PrevAndNext(session, auditEntry, classNameId,
					classPK, visibility, orderByComparator, true);

			array[1] = auditEntry;

			array[2] = getByC_C_V_PrevAndNext(session, auditEntry, classNameId,
					classPK, visibility, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AuditEntry getByC_C_V_PrevAndNext(Session session,
		AuditEntry auditEntry, long classNameId, long classPK, int visibility,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_AUDITENTRY_WHERE);

		query.append(_FINDER_COLUMN_C_C_V_CLASSNAMEID_2);

		query.append(_FINDER_COLUMN_C_C_V_CLASSPK_2);

		query.append(_FINDER_COLUMN_C_C_V_VISIBILITY_2);

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
			query.append(AuditEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(classNameId);

		qPos.add(classPK);

		qPos.add(visibility);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(auditEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AuditEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the audit entries where classNameId = &#63; and classPK = &#63; and visibility = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param visibilities the visibilities
	 * @return the matching audit entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<AuditEntry> findByC_C_V(long classNameId, long classPK,
		int[] visibilities) throws SystemException {
		return findByC_C_V(classNameId, classPK, visibilities,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the audit entries where classNameId = &#63; and classPK = &#63; and visibility = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param visibilities the visibilities
	 * @param start the lower bound of the range of audit entries
	 * @param end the upper bound of the range of audit entries (not inclusive)
	 * @return the range of matching audit entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<AuditEntry> findByC_C_V(long classNameId, long classPK,
		int[] visibilities, int start, int end) throws SystemException {
		return findByC_C_V(classNameId, classPK, visibilities, start, end, null);
	}

	/**
	 * Returns an ordered range of all the audit entries where classNameId = &#63; and classPK = &#63; and visibility = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param visibilities the visibilities
	 * @param start the lower bound of the range of audit entries
	 * @param end the upper bound of the range of audit entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching audit entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<AuditEntry> findByC_C_V(long classNameId, long classPK,
		int[] visibilities, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C_V;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderArgs = new Object[] {
					classNameId, classPK, StringUtil.merge(visibilities)
				};
		}
		else {
			finderArgs = new Object[] {
					classNameId, classPK, StringUtil.merge(visibilities),
					
					start, end, orderByComparator
				};
		}

		List<AuditEntry> list = (List<AuditEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AuditEntry auditEntry : list) {
				if ((classNameId != auditEntry.getClassNameId()) ||
						(classPK != auditEntry.getClassPK()) ||
						!ArrayUtil.contains(visibilities,
							auditEntry.getVisibility())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_AUDITENTRY_WHERE);

			boolean conjunctionable = false;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_C_C_V_CLASSNAMEID_5);

			conjunctionable = true;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_C_C_V_CLASSPK_5);

			conjunctionable = true;

			if ((visibilities == null) || (visibilities.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < visibilities.length; i++) {
					query.append(_FINDER_COLUMN_C_C_V_VISIBILITY_5);

					if ((i + 1) < visibilities.length) {
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
				query.append(AuditEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				if (visibilities != null) {
					qPos.add(visibilities);
				}

				list = (List<AuditEntry>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns all the audit entries where fieldClassNameId = &#63; and fieldClassPK = &#63; and field = &#63;.
	 *
	 * @param fieldClassNameId the field class name ID
	 * @param fieldClassPK the field class p k
	 * @param field the field
	 * @return the matching audit entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<AuditEntry> findByFC_FC_F(long fieldClassNameId,
		long fieldClassPK, int field) throws SystemException {
		return findByFC_FC_F(fieldClassNameId, fieldClassPK, field,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the audit entries where fieldClassNameId = &#63; and fieldClassPK = &#63; and field = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param fieldClassNameId the field class name ID
	 * @param fieldClassPK the field class p k
	 * @param field the field
	 * @param start the lower bound of the range of audit entries
	 * @param end the upper bound of the range of audit entries (not inclusive)
	 * @return the range of matching audit entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<AuditEntry> findByFC_FC_F(long fieldClassNameId,
		long fieldClassPK, int field, int start, int end)
		throws SystemException {
		return findByFC_FC_F(fieldClassNameId, fieldClassPK, field, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the audit entries where fieldClassNameId = &#63; and fieldClassPK = &#63; and field = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param fieldClassNameId the field class name ID
	 * @param fieldClassPK the field class p k
	 * @param field the field
	 * @param start the lower bound of the range of audit entries
	 * @param end the upper bound of the range of audit entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching audit entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<AuditEntry> findByFC_FC_F(long fieldClassNameId,
		long fieldClassPK, int field, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FC_FC_F;
			finderArgs = new Object[] { fieldClassNameId, fieldClassPK, field };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_FC_FC_F;
			finderArgs = new Object[] {
					fieldClassNameId, fieldClassPK, field,
					
					start, end, orderByComparator
				};
		}

		List<AuditEntry> list = (List<AuditEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AuditEntry auditEntry : list) {
				if ((fieldClassNameId != auditEntry.getFieldClassNameId()) ||
						(fieldClassPK != auditEntry.getFieldClassPK()) ||
						(field != auditEntry.getField())) {
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

			query.append(_SQL_SELECT_AUDITENTRY_WHERE);

			query.append(_FINDER_COLUMN_FC_FC_F_FIELDCLASSNAMEID_2);

			query.append(_FINDER_COLUMN_FC_FC_F_FIELDCLASSPK_2);

			query.append(_FINDER_COLUMN_FC_FC_F_FIELD_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(AuditEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(fieldClassNameId);

				qPos.add(fieldClassPK);

				qPos.add(field);

				list = (List<AuditEntry>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns the first audit entry in the ordered set where fieldClassNameId = &#63; and fieldClassPK = &#63; and field = &#63;.
	 *
	 * @param fieldClassNameId the field class name ID
	 * @param fieldClassPK the field class p k
	 * @param field the field
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching audit entry
	 * @throws com.liferay.osb.NoSuchAuditEntryException if a matching audit entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AuditEntry findByFC_FC_F_First(long fieldClassNameId,
		long fieldClassPK, int field, OrderByComparator orderByComparator)
		throws NoSuchAuditEntryException, SystemException {
		AuditEntry auditEntry = fetchByFC_FC_F_First(fieldClassNameId,
				fieldClassPK, field, orderByComparator);

		if (auditEntry != null) {
			return auditEntry;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("fieldClassNameId=");
		msg.append(fieldClassNameId);

		msg.append(", fieldClassPK=");
		msg.append(fieldClassPK);

		msg.append(", field=");
		msg.append(field);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAuditEntryException(msg.toString());
	}

	/**
	 * Returns the first audit entry in the ordered set where fieldClassNameId = &#63; and fieldClassPK = &#63; and field = &#63;.
	 *
	 * @param fieldClassNameId the field class name ID
	 * @param fieldClassPK the field class p k
	 * @param field the field
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching audit entry, or <code>null</code> if a matching audit entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AuditEntry fetchByFC_FC_F_First(long fieldClassNameId,
		long fieldClassPK, int field, OrderByComparator orderByComparator)
		throws SystemException {
		List<AuditEntry> list = findByFC_FC_F(fieldClassNameId, fieldClassPK,
				field, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last audit entry in the ordered set where fieldClassNameId = &#63; and fieldClassPK = &#63; and field = &#63;.
	 *
	 * @param fieldClassNameId the field class name ID
	 * @param fieldClassPK the field class p k
	 * @param field the field
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching audit entry
	 * @throws com.liferay.osb.NoSuchAuditEntryException if a matching audit entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AuditEntry findByFC_FC_F_Last(long fieldClassNameId,
		long fieldClassPK, int field, OrderByComparator orderByComparator)
		throws NoSuchAuditEntryException, SystemException {
		AuditEntry auditEntry = fetchByFC_FC_F_Last(fieldClassNameId,
				fieldClassPK, field, orderByComparator);

		if (auditEntry != null) {
			return auditEntry;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("fieldClassNameId=");
		msg.append(fieldClassNameId);

		msg.append(", fieldClassPK=");
		msg.append(fieldClassPK);

		msg.append(", field=");
		msg.append(field);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAuditEntryException(msg.toString());
	}

	/**
	 * Returns the last audit entry in the ordered set where fieldClassNameId = &#63; and fieldClassPK = &#63; and field = &#63;.
	 *
	 * @param fieldClassNameId the field class name ID
	 * @param fieldClassPK the field class p k
	 * @param field the field
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching audit entry, or <code>null</code> if a matching audit entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AuditEntry fetchByFC_FC_F_Last(long fieldClassNameId,
		long fieldClassPK, int field, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByFC_FC_F(fieldClassNameId, fieldClassPK, field);

		List<AuditEntry> list = findByFC_FC_F(fieldClassNameId, fieldClassPK,
				field, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the audit entries before and after the current audit entry in the ordered set where fieldClassNameId = &#63; and fieldClassPK = &#63; and field = &#63;.
	 *
	 * @param auditEntryId the primary key of the current audit entry
	 * @param fieldClassNameId the field class name ID
	 * @param fieldClassPK the field class p k
	 * @param field the field
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next audit entry
	 * @throws com.liferay.osb.NoSuchAuditEntryException if a audit entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AuditEntry[] findByFC_FC_F_PrevAndNext(long auditEntryId,
		long fieldClassNameId, long fieldClassPK, int field,
		OrderByComparator orderByComparator)
		throws NoSuchAuditEntryException, SystemException {
		AuditEntry auditEntry = findByPrimaryKey(auditEntryId);

		Session session = null;

		try {
			session = openSession();

			AuditEntry[] array = new AuditEntryImpl[3];

			array[0] = getByFC_FC_F_PrevAndNext(session, auditEntry,
					fieldClassNameId, fieldClassPK, field, orderByComparator,
					true);

			array[1] = auditEntry;

			array[2] = getByFC_FC_F_PrevAndNext(session, auditEntry,
					fieldClassNameId, fieldClassPK, field, orderByComparator,
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

	protected AuditEntry getByFC_FC_F_PrevAndNext(Session session,
		AuditEntry auditEntry, long fieldClassNameId, long fieldClassPK,
		int field, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_AUDITENTRY_WHERE);

		query.append(_FINDER_COLUMN_FC_FC_F_FIELDCLASSNAMEID_2);

		query.append(_FINDER_COLUMN_FC_FC_F_FIELDCLASSPK_2);

		query.append(_FINDER_COLUMN_FC_FC_F_FIELD_2);

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
			query.append(AuditEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(fieldClassNameId);

		qPos.add(fieldClassPK);

		qPos.add(field);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(auditEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AuditEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the audit entries where classNameId = &#63; and classPK = &#63; and field = &#63; and action = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param field the field
	 * @param action the action
	 * @return the matching audit entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<AuditEntry> findByC_C_F_A(long classNameId, long classPK,
		int field, int action) throws SystemException {
		return findByC_C_F_A(classNameId, classPK, field, action,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the audit entries where classNameId = &#63; and classPK = &#63; and field = &#63; and action = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param field the field
	 * @param action the action
	 * @param start the lower bound of the range of audit entries
	 * @param end the upper bound of the range of audit entries (not inclusive)
	 * @return the range of matching audit entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<AuditEntry> findByC_C_F_A(long classNameId, long classPK,
		int field, int action, int start, int end) throws SystemException {
		return findByC_C_F_A(classNameId, classPK, field, action, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the audit entries where classNameId = &#63; and classPK = &#63; and field = &#63; and action = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param field the field
	 * @param action the action
	 * @param start the lower bound of the range of audit entries
	 * @param end the upper bound of the range of audit entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching audit entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<AuditEntry> findByC_C_F_A(long classNameId, long classPK,
		int field, int action, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_F_A;
			finderArgs = new Object[] { classNameId, classPK, field, action };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C_F_A;
			finderArgs = new Object[] {
					classNameId, classPK, field, action,
					
					start, end, orderByComparator
				};
		}

		List<AuditEntry> list = (List<AuditEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AuditEntry auditEntry : list) {
				if ((classNameId != auditEntry.getClassNameId()) ||
						(classPK != auditEntry.getClassPK()) ||
						(field != auditEntry.getField()) ||
						(action != auditEntry.getAction())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(6 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(6);
			}

			query.append(_SQL_SELECT_AUDITENTRY_WHERE);

			query.append(_FINDER_COLUMN_C_C_F_A_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_F_A_CLASSPK_2);

			query.append(_FINDER_COLUMN_C_C_F_A_FIELD_2);

			query.append(_FINDER_COLUMN_C_C_F_A_ACTION_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(AuditEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				qPos.add(field);

				qPos.add(action);

				list = (List<AuditEntry>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns the first audit entry in the ordered set where classNameId = &#63; and classPK = &#63; and field = &#63; and action = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param field the field
	 * @param action the action
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching audit entry
	 * @throws com.liferay.osb.NoSuchAuditEntryException if a matching audit entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AuditEntry findByC_C_F_A_First(long classNameId, long classPK,
		int field, int action, OrderByComparator orderByComparator)
		throws NoSuchAuditEntryException, SystemException {
		AuditEntry auditEntry = fetchByC_C_F_A_First(classNameId, classPK,
				field, action, orderByComparator);

		if (auditEntry != null) {
			return auditEntry;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(", field=");
		msg.append(field);

		msg.append(", action=");
		msg.append(action);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAuditEntryException(msg.toString());
	}

	/**
	 * Returns the first audit entry in the ordered set where classNameId = &#63; and classPK = &#63; and field = &#63; and action = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param field the field
	 * @param action the action
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching audit entry, or <code>null</code> if a matching audit entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AuditEntry fetchByC_C_F_A_First(long classNameId, long classPK,
		int field, int action, OrderByComparator orderByComparator)
		throws SystemException {
		List<AuditEntry> list = findByC_C_F_A(classNameId, classPK, field,
				action, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last audit entry in the ordered set where classNameId = &#63; and classPK = &#63; and field = &#63; and action = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param field the field
	 * @param action the action
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching audit entry
	 * @throws com.liferay.osb.NoSuchAuditEntryException if a matching audit entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AuditEntry findByC_C_F_A_Last(long classNameId, long classPK,
		int field, int action, OrderByComparator orderByComparator)
		throws NoSuchAuditEntryException, SystemException {
		AuditEntry auditEntry = fetchByC_C_F_A_Last(classNameId, classPK,
				field, action, orderByComparator);

		if (auditEntry != null) {
			return auditEntry;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(", field=");
		msg.append(field);

		msg.append(", action=");
		msg.append(action);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAuditEntryException(msg.toString());
	}

	/**
	 * Returns the last audit entry in the ordered set where classNameId = &#63; and classPK = &#63; and field = &#63; and action = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param field the field
	 * @param action the action
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching audit entry, or <code>null</code> if a matching audit entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AuditEntry fetchByC_C_F_A_Last(long classNameId, long classPK,
		int field, int action, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByC_C_F_A(classNameId, classPK, field, action);

		List<AuditEntry> list = findByC_C_F_A(classNameId, classPK, field,
				action, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the audit entries before and after the current audit entry in the ordered set where classNameId = &#63; and classPK = &#63; and field = &#63; and action = &#63;.
	 *
	 * @param auditEntryId the primary key of the current audit entry
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param field the field
	 * @param action the action
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next audit entry
	 * @throws com.liferay.osb.NoSuchAuditEntryException if a audit entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AuditEntry[] findByC_C_F_A_PrevAndNext(long auditEntryId,
		long classNameId, long classPK, int field, int action,
		OrderByComparator orderByComparator)
		throws NoSuchAuditEntryException, SystemException {
		AuditEntry auditEntry = findByPrimaryKey(auditEntryId);

		Session session = null;

		try {
			session = openSession();

			AuditEntry[] array = new AuditEntryImpl[3];

			array[0] = getByC_C_F_A_PrevAndNext(session, auditEntry,
					classNameId, classPK, field, action, orderByComparator, true);

			array[1] = auditEntry;

			array[2] = getByC_C_F_A_PrevAndNext(session, auditEntry,
					classNameId, classPK, field, action, orderByComparator,
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

	protected AuditEntry getByC_C_F_A_PrevAndNext(Session session,
		AuditEntry auditEntry, long classNameId, long classPK, int field,
		int action, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_AUDITENTRY_WHERE);

		query.append(_FINDER_COLUMN_C_C_F_A_CLASSNAMEID_2);

		query.append(_FINDER_COLUMN_C_C_F_A_CLASSPK_2);

		query.append(_FINDER_COLUMN_C_C_F_A_FIELD_2);

		query.append(_FINDER_COLUMN_C_C_F_A_ACTION_2);

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
			query.append(AuditEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(classNameId);

		qPos.add(classPK);

		qPos.add(field);

		qPos.add(action);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(auditEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AuditEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the audit entries.
	 *
	 * @return the audit entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<AuditEntry> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the audit entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of audit entries
	 * @param end the upper bound of the range of audit entries (not inclusive)
	 * @return the range of audit entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<AuditEntry> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the audit entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of audit entries
	 * @param end the upper bound of the range of audit entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of audit entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<AuditEntry> findAll(int start, int end,
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

		List<AuditEntry> list = (List<AuditEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_AUDITENTRY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_AUDITENTRY.concat(AuditEntryModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<AuditEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<AuditEntry>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the audit entries where createDate &gt; &#63; and classNameId = &#63; from the database.
	 *
	 * @param createDate the create date
	 * @param classNameId the class name ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByGtCD_C(Date createDate, long classNameId)
		throws SystemException {
		for (AuditEntry auditEntry : findByGtCD_C(createDate, classNameId)) {
			remove(auditEntry);
		}
	}

	/**
	 * Removes all the audit entries where classNameId = &#63; and classPK = &#63; and visibility = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param visibility the visibility
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByC_C_V(long classNameId, long classPK, int visibility)
		throws SystemException {
		for (AuditEntry auditEntry : findByC_C_V(classNameId, classPK,
				visibility)) {
			remove(auditEntry);
		}
	}

	/**
	 * Removes all the audit entries where fieldClassNameId = &#63; and fieldClassPK = &#63; and field = &#63; from the database.
	 *
	 * @param fieldClassNameId the field class name ID
	 * @param fieldClassPK the field class p k
	 * @param field the field
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByFC_FC_F(long fieldClassNameId, long fieldClassPK,
		int field) throws SystemException {
		for (AuditEntry auditEntry : findByFC_FC_F(fieldClassNameId,
				fieldClassPK, field)) {
			remove(auditEntry);
		}
	}

	/**
	 * Removes all the audit entries where classNameId = &#63; and classPK = &#63; and field = &#63; and action = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param field the field
	 * @param action the action
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByC_C_F_A(long classNameId, long classPK, int field,
		int action) throws SystemException {
		for (AuditEntry auditEntry : findByC_C_F_A(classNameId, classPK, field,
				action)) {
			remove(auditEntry);
		}
	}

	/**
	 * Removes all the audit entries from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (AuditEntry auditEntry : findAll()) {
			remove(auditEntry);
		}
	}

	/**
	 * Returns the number of audit entries where createDate &gt; &#63; and classNameId = &#63;.
	 *
	 * @param createDate the create date
	 * @param classNameId the class name ID
	 * @return the number of matching audit entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByGtCD_C(Date createDate, long classNameId)
		throws SystemException {
		Object[] finderArgs = new Object[] { createDate, classNameId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_GTCD_C,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_AUDITENTRY_WHERE);

			if (createDate == null) {
				query.append(_FINDER_COLUMN_GTCD_C_CREATEDATE_1);
			}
			else {
				query.append(_FINDER_COLUMN_GTCD_C_CREATEDATE_2);
			}

			query.append(_FINDER_COLUMN_GTCD_C_CLASSNAMEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (createDate != null) {
					qPos.add(CalendarUtil.getTimestamp(createDate));
				}

				qPos.add(classNameId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_GTCD_C,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of audit entries where classNameId = &#63; and classPK = &#63; and visibility = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param visibility the visibility
	 * @return the number of matching audit entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByC_C_V(long classNameId, long classPK, int visibility)
		throws SystemException {
		Object[] finderArgs = new Object[] { classNameId, classPK, visibility };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_C_C_V,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_AUDITENTRY_WHERE);

			query.append(_FINDER_COLUMN_C_C_V_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_V_CLASSPK_2);

			query.append(_FINDER_COLUMN_C_C_V_VISIBILITY_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				qPos.add(visibility);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_C_V,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of audit entries where classNameId = &#63; and classPK = &#63; and visibility = any &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param visibilities the visibilities
	 * @return the number of matching audit entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByC_C_V(long classNameId, long classPK, int[] visibilities)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				classNameId, classPK, StringUtil.merge(visibilities)
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_C_C_V,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_AUDITENTRY_WHERE);

			boolean conjunctionable = false;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_C_C_V_CLASSNAMEID_5);

			conjunctionable = true;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_C_C_V_CLASSPK_5);

			conjunctionable = true;

			if ((visibilities == null) || (visibilities.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < visibilities.length; i++) {
					query.append(_FINDER_COLUMN_C_C_V_VISIBILITY_5);

					if ((i + 1) < visibilities.length) {
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

				qPos.add(classNameId);

				qPos.add(classPK);

				if (visibilities != null) {
					qPos.add(visibilities);
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

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_C_C_V,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of audit entries where fieldClassNameId = &#63; and fieldClassPK = &#63; and field = &#63;.
	 *
	 * @param fieldClassNameId the field class name ID
	 * @param fieldClassPK the field class p k
	 * @param field the field
	 * @return the number of matching audit entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByFC_FC_F(long fieldClassNameId, long fieldClassPK,
		int field) throws SystemException {
		Object[] finderArgs = new Object[] { fieldClassNameId, fieldClassPK, field };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_FC_FC_F,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_AUDITENTRY_WHERE);

			query.append(_FINDER_COLUMN_FC_FC_F_FIELDCLASSNAMEID_2);

			query.append(_FINDER_COLUMN_FC_FC_F_FIELDCLASSPK_2);

			query.append(_FINDER_COLUMN_FC_FC_F_FIELD_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(fieldClassNameId);

				qPos.add(fieldClassPK);

				qPos.add(field);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_FC_FC_F,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of audit entries where classNameId = &#63; and classPK = &#63; and field = &#63; and action = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param field the field
	 * @param action the action
	 * @return the number of matching audit entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByC_C_F_A(long classNameId, long classPK, int field,
		int action) throws SystemException {
		Object[] finderArgs = new Object[] { classNameId, classPK, field, action };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_C_C_F_A,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_AUDITENTRY_WHERE);

			query.append(_FINDER_COLUMN_C_C_F_A_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_F_A_CLASSPK_2);

			query.append(_FINDER_COLUMN_C_C_F_A_FIELD_2);

			query.append(_FINDER_COLUMN_C_C_F_A_ACTION_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				qPos.add(field);

				qPos.add(action);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_C_F_A,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of audit entries.
	 *
	 * @return the number of audit entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_AUDITENTRY);

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
	 * Initializes the audit entry persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.osb.model.AuditEntry")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<AuditEntry>> listenersList = new ArrayList<ModelListener<AuditEntry>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<AuditEntry>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(AuditEntryImpl.class.getName());
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
	private static final String _SQL_SELECT_AUDITENTRY = "SELECT auditEntry FROM AuditEntry auditEntry";
	private static final String _SQL_SELECT_AUDITENTRY_WHERE = "SELECT auditEntry FROM AuditEntry auditEntry WHERE ";
	private static final String _SQL_COUNT_AUDITENTRY = "SELECT COUNT(auditEntry) FROM AuditEntry auditEntry";
	private static final String _SQL_COUNT_AUDITENTRY_WHERE = "SELECT COUNT(auditEntry) FROM AuditEntry auditEntry WHERE ";
	private static final String _FINDER_COLUMN_GTCD_C_CREATEDATE_1 = "auditEntry.createDate > NULL AND ";
	private static final String _FINDER_COLUMN_GTCD_C_CREATEDATE_2 = "auditEntry.createDate > ? AND ";
	private static final String _FINDER_COLUMN_GTCD_C_CLASSNAMEID_2 = "auditEntry.classNameId = ?";
	private static final String _FINDER_COLUMN_C_C_V_CLASSNAMEID_2 = "auditEntry.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_C_C_V_CLASSNAMEID_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_C_C_V_CLASSNAMEID_2) + ")";
	private static final String _FINDER_COLUMN_C_C_V_CLASSPK_2 = "auditEntry.classPK = ? AND ";
	private static final String _FINDER_COLUMN_C_C_V_CLASSPK_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_C_C_V_CLASSPK_2) + ")";
	private static final String _FINDER_COLUMN_C_C_V_VISIBILITY_2 = "auditEntry.visibility = ?";
	private static final String _FINDER_COLUMN_C_C_V_VISIBILITY_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_C_C_V_VISIBILITY_2) + ")";
	private static final String _FINDER_COLUMN_FC_FC_F_FIELDCLASSNAMEID_2 = "auditEntry.fieldClassNameId = ? AND ";
	private static final String _FINDER_COLUMN_FC_FC_F_FIELDCLASSPK_2 = "auditEntry.fieldClassPK = ? AND ";
	private static final String _FINDER_COLUMN_FC_FC_F_FIELD_2 = "auditEntry.field = ?";
	private static final String _FINDER_COLUMN_C_C_F_A_CLASSNAMEID_2 = "auditEntry.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_C_C_F_A_CLASSPK_2 = "auditEntry.classPK = ? AND ";
	private static final String _FINDER_COLUMN_C_C_F_A_FIELD_2 = "auditEntry.field = ? AND ";
	private static final String _FINDER_COLUMN_C_C_F_A_ACTION_2 = "auditEntry.action = ?";

	private static String _removeConjunction(String sql) {
		int pos = sql.indexOf(" AND ");

		if (pos != -1) {
			sql = sql.substring(0, pos);
		}

		return sql;
	}

	private static final String _ORDER_BY_ENTITY_ALIAS = "auditEntry.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AuditEntry exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No AuditEntry exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(AuditEntryPersistenceImpl.class);
	private static AuditEntry _nullAuditEntry = new AuditEntryImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<AuditEntry> toCacheModel() {
				return _nullAuditEntryCacheModel;
			}
		};

	private static CacheModel<AuditEntry> _nullAuditEntryCacheModel = new CacheModel<AuditEntry>() {
			public AuditEntry toEntityModel() {
				return _nullAuditEntry;
			}
		};
}