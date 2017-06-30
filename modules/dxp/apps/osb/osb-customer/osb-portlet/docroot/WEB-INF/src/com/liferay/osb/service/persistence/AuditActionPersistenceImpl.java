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

import com.liferay.osb.NoSuchAuditActionException;
import com.liferay.osb.model.AuditAction;
import com.liferay.osb.model.impl.AuditActionImpl;
import com.liferay.osb.model.impl.AuditActionModelImpl;

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
 * The persistence implementation for the audit action service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AuditActionPersistence
 * @see AuditActionUtil
 * @generated
 */
public class AuditActionPersistenceImpl extends BasePersistenceImpl<AuditAction>
	implements AuditActionPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AuditActionUtil} to access the audit action persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AuditActionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_LTMODIFIEDDATE =
		new FinderPath(AuditActionModelImpl.ENTITY_CACHE_ENABLED,
			AuditActionModelImpl.FINDER_CACHE_ENABLED, AuditActionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByLtModifiedDate",
			new String[] {
				Date.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_LTMODIFIEDDATE =
		new FinderPath(AuditActionModelImpl.ENTITY_CACHE_ENABLED,
			AuditActionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByLtModifiedDate",
			new String[] { Date.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_C_C_MC = new FinderPath(AuditActionModelImpl.ENTITY_CACHE_ENABLED,
			AuditActionModelImpl.FINDER_CACHE_ENABLED, AuditActionImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByC_C_MC",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			},
			AuditActionModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			AuditActionModelImpl.CLASSPK_COLUMN_BITMASK |
			AuditActionModelImpl.MAPPINGCLASSPK_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_C_MC = new FinderPath(AuditActionModelImpl.ENTITY_CACHE_ENABLED,
			AuditActionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_C_MC",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GTMD_C_GTMC_A =
		new FinderPath(AuditActionModelImpl.ENTITY_CACHE_ENABLED,
			AuditActionModelImpl.FINDER_CACHE_ENABLED, AuditActionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGtMD_C_GtMC_A",
			new String[] {
				Date.class.getName(), Long.class.getName(), Long.class.getName(),
				Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_GTMD_C_GTMC_A =
		new FinderPath(AuditActionModelImpl.ENTITY_CACHE_ENABLED,
			AuditActionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByGtMD_C_GtMC_A",
			new String[] {
				Date.class.getName(), Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GTMD_C_MC_A =
		new FinderPath(AuditActionModelImpl.ENTITY_CACHE_ENABLED,
			AuditActionModelImpl.FINDER_CACHE_ENABLED, AuditActionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGtMD_C_MC_A",
			new String[] {
				Date.class.getName(), Long.class.getName(), Long.class.getName(),
				Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_GTMD_C_MC_A =
		new FinderPath(AuditActionModelImpl.ENTITY_CACHE_ENABLED,
			AuditActionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByGtMD_C_MC_A",
			new String[] {
				Date.class.getName(), Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_MD_C_C_MC_A =
		new FinderPath(AuditActionModelImpl.ENTITY_CACHE_ENABLED,
			AuditActionModelImpl.FINDER_CACHE_ENABLED, AuditActionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByMD_C_C_MC_A",
			new String[] {
				Date.class.getName(), Long.class.getName(), Long.class.getName(),
				Long.class.getName(), Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MD_C_C_MC_A =
		new FinderPath(AuditActionModelImpl.ENTITY_CACHE_ENABLED,
			AuditActionModelImpl.FINDER_CACHE_ENABLED, AuditActionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByMD_C_C_MC_A",
			new String[] {
				Date.class.getName(), Long.class.getName(), Long.class.getName(),
				Long.class.getName(), Integer.class.getName()
			},
			AuditActionModelImpl.MODIFIEDDATE_COLUMN_BITMASK |
			AuditActionModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			AuditActionModelImpl.CLASSPK_COLUMN_BITMASK |
			AuditActionModelImpl.MAPPINGCLASSPK_COLUMN_BITMASK |
			AuditActionModelImpl.ACTION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_MD_C_C_MC_A = new FinderPath(AuditActionModelImpl.ENTITY_CACHE_ENABLED,
			AuditActionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByMD_C_C_MC_A",
			new String[] {
				Date.class.getName(), Long.class.getName(), Long.class.getName(),
				Long.class.getName(), Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AuditActionModelImpl.ENTITY_CACHE_ENABLED,
			AuditActionModelImpl.FINDER_CACHE_ENABLED, AuditActionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AuditActionModelImpl.ENTITY_CACHE_ENABLED,
			AuditActionModelImpl.FINDER_CACHE_ENABLED, AuditActionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AuditActionModelImpl.ENTITY_CACHE_ENABLED,
			AuditActionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the audit action in the entity cache if it is enabled.
	 *
	 * @param auditAction the audit action
	 */
	public void cacheResult(AuditAction auditAction) {
		EntityCacheUtil.putResult(AuditActionModelImpl.ENTITY_CACHE_ENABLED,
			AuditActionImpl.class, auditAction.getPrimaryKey(), auditAction);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_C_MC,
			new Object[] {
				Long.valueOf(auditAction.getClassNameId()),
				Long.valueOf(auditAction.getClassPK()),
				Long.valueOf(auditAction.getMappingClassPK())
			}, auditAction);

		auditAction.resetOriginalValues();
	}

	/**
	 * Caches the audit actions in the entity cache if it is enabled.
	 *
	 * @param auditActions the audit actions
	 */
	public void cacheResult(List<AuditAction> auditActions) {
		for (AuditAction auditAction : auditActions) {
			if (EntityCacheUtil.getResult(
						AuditActionModelImpl.ENTITY_CACHE_ENABLED,
						AuditActionImpl.class, auditAction.getPrimaryKey()) == null) {
				cacheResult(auditAction);
			}
			else {
				auditAction.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all audit actions.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(AuditActionImpl.class.getName());
		}

		EntityCacheUtil.clearCache(AuditActionImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the audit action.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AuditAction auditAction) {
		EntityCacheUtil.removeResult(AuditActionModelImpl.ENTITY_CACHE_ENABLED,
			AuditActionImpl.class, auditAction.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(auditAction);
	}

	@Override
	public void clearCache(List<AuditAction> auditActions) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AuditAction auditAction : auditActions) {
			EntityCacheUtil.removeResult(AuditActionModelImpl.ENTITY_CACHE_ENABLED,
				AuditActionImpl.class, auditAction.getPrimaryKey());

			clearUniqueFindersCache(auditAction);
		}
	}

	protected void cacheUniqueFindersCache(AuditAction auditAction) {
		if (auditAction.isNew()) {
			Object[] args = new Object[] {
					Long.valueOf(auditAction.getClassNameId()),
					Long.valueOf(auditAction.getClassPK()),
					Long.valueOf(auditAction.getMappingClassPK())
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_C_MC, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_C_MC, args,
				auditAction);
		}
		else {
			AuditActionModelImpl auditActionModelImpl = (AuditActionModelImpl)auditAction;

			if ((auditActionModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_C_C_MC.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(auditAction.getClassNameId()),
						Long.valueOf(auditAction.getClassPK()),
						Long.valueOf(auditAction.getMappingClassPK())
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_C_MC, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_C_MC, args,
					auditAction);
			}
		}
	}

	protected void clearUniqueFindersCache(AuditAction auditAction) {
		AuditActionModelImpl auditActionModelImpl = (AuditActionModelImpl)auditAction;

		Object[] args = new Object[] {
				Long.valueOf(auditAction.getClassNameId()),
				Long.valueOf(auditAction.getClassPK()),
				Long.valueOf(auditAction.getMappingClassPK())
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C_MC, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_C_MC, args);

		if ((auditActionModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_C_C_MC.getColumnBitmask()) != 0) {
			args = new Object[] {
					Long.valueOf(auditActionModelImpl.getOriginalClassNameId()),
					Long.valueOf(auditActionModelImpl.getOriginalClassPK()),
					Long.valueOf(auditActionModelImpl.getOriginalMappingClassPK())
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C_MC, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_C_MC, args);
		}
	}

	/**
	 * Creates a new audit action with the primary key. Does not add the audit action to the database.
	 *
	 * @param auditActionId the primary key for the new audit action
	 * @return the new audit action
	 */
	public AuditAction create(long auditActionId) {
		AuditAction auditAction = new AuditActionImpl();

		auditAction.setNew(true);
		auditAction.setPrimaryKey(auditActionId);

		return auditAction;
	}

	/**
	 * Removes the audit action with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param auditActionId the primary key of the audit action
	 * @return the audit action that was removed
	 * @throws com.liferay.osb.NoSuchAuditActionException if a audit action with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AuditAction remove(long auditActionId)
		throws NoSuchAuditActionException, SystemException {
		return remove(Long.valueOf(auditActionId));
	}

	/**
	 * Removes the audit action with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the audit action
	 * @return the audit action that was removed
	 * @throws com.liferay.osb.NoSuchAuditActionException if a audit action with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AuditAction remove(Serializable primaryKey)
		throws NoSuchAuditActionException, SystemException {
		Session session = null;

		try {
			session = openSession();

			AuditAction auditAction = (AuditAction)session.get(AuditActionImpl.class,
					primaryKey);

			if (auditAction == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAuditActionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(auditAction);
		}
		catch (NoSuchAuditActionException nsee) {
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
	protected AuditAction removeImpl(AuditAction auditAction)
		throws SystemException {
		auditAction = toUnwrappedModel(auditAction);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, auditAction);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(auditAction);

		return auditAction;
	}

	@Override
	public AuditAction updateImpl(
		com.liferay.osb.model.AuditAction auditAction, boolean merge)
		throws SystemException {
		auditAction = toUnwrappedModel(auditAction);

		boolean isNew = auditAction.isNew();

		AuditActionModelImpl auditActionModelImpl = (AuditActionModelImpl)auditAction;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, auditAction, merge);

			auditAction.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !AuditActionModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((auditActionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MD_C_C_MC_A.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						auditActionModelImpl.getOriginalModifiedDate(),
						Long.valueOf(auditActionModelImpl.getOriginalClassNameId()),
						Long.valueOf(auditActionModelImpl.getOriginalClassPK()),
						Long.valueOf(auditActionModelImpl.getOriginalMappingClassPK()),
						Integer.valueOf(auditActionModelImpl.getOriginalAction())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_MD_C_C_MC_A,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MD_C_C_MC_A,
					args);

				args = new Object[] {
						auditActionModelImpl.getModifiedDate(),
						Long.valueOf(auditActionModelImpl.getClassNameId()),
						Long.valueOf(auditActionModelImpl.getClassPK()),
						Long.valueOf(auditActionModelImpl.getMappingClassPK()),
						Integer.valueOf(auditActionModelImpl.getAction())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_MD_C_C_MC_A,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MD_C_C_MC_A,
					args);
			}
		}

		EntityCacheUtil.putResult(AuditActionModelImpl.ENTITY_CACHE_ENABLED,
			AuditActionImpl.class, auditAction.getPrimaryKey(), auditAction);

		clearUniqueFindersCache(auditAction);
		cacheUniqueFindersCache(auditAction);

		return auditAction;
	}

	protected AuditAction toUnwrappedModel(AuditAction auditAction) {
		if (auditAction instanceof AuditActionImpl) {
			return auditAction;
		}

		AuditActionImpl auditActionImpl = new AuditActionImpl();

		auditActionImpl.setNew(auditAction.isNew());
		auditActionImpl.setPrimaryKey(auditAction.getPrimaryKey());

		auditActionImpl.setAuditActionId(auditAction.getAuditActionId());
		auditActionImpl.setModifiedDate(auditAction.getModifiedDate());
		auditActionImpl.setClassNameId(auditAction.getClassNameId());
		auditActionImpl.setClassPK(auditAction.getClassPK());
		auditActionImpl.setMappingClassPK(auditAction.getMappingClassPK());
		auditActionImpl.setAction(auditAction.getAction());

		return auditActionImpl;
	}

	/**
	 * Returns the audit action with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the audit action
	 * @return the audit action
	 * @throws com.liferay.portal.NoSuchModelException if a audit action with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AuditAction findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the audit action with the primary key or throws a {@link com.liferay.osb.NoSuchAuditActionException} if it could not be found.
	 *
	 * @param auditActionId the primary key of the audit action
	 * @return the audit action
	 * @throws com.liferay.osb.NoSuchAuditActionException if a audit action with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AuditAction findByPrimaryKey(long auditActionId)
		throws NoSuchAuditActionException, SystemException {
		AuditAction auditAction = fetchByPrimaryKey(auditActionId);

		if (auditAction == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + auditActionId);
			}

			throw new NoSuchAuditActionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				auditActionId);
		}

		return auditAction;
	}

	/**
	 * Returns the audit action with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the audit action
	 * @return the audit action, or <code>null</code> if a audit action with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AuditAction fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the audit action with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param auditActionId the primary key of the audit action
	 * @return the audit action, or <code>null</code> if a audit action with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AuditAction fetchByPrimaryKey(long auditActionId)
		throws SystemException {
		AuditAction auditAction = (AuditAction)EntityCacheUtil.getResult(AuditActionModelImpl.ENTITY_CACHE_ENABLED,
				AuditActionImpl.class, auditActionId);

		if (auditAction == _nullAuditAction) {
			return null;
		}

		if (auditAction == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				auditAction = (AuditAction)session.get(AuditActionImpl.class,
						Long.valueOf(auditActionId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (auditAction != null) {
					cacheResult(auditAction);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(AuditActionModelImpl.ENTITY_CACHE_ENABLED,
						AuditActionImpl.class, auditActionId, _nullAuditAction);
				}

				closeSession(session);
			}
		}

		return auditAction;
	}

	/**
	 * Returns all the audit actions where modifiedDate &lt; &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @return the matching audit actions
	 * @throws SystemException if a system exception occurred
	 */
	public List<AuditAction> findByLtModifiedDate(Date modifiedDate)
		throws SystemException {
		return findByLtModifiedDate(modifiedDate, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the audit actions where modifiedDate &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param start the lower bound of the range of audit actions
	 * @param end the upper bound of the range of audit actions (not inclusive)
	 * @return the range of matching audit actions
	 * @throws SystemException if a system exception occurred
	 */
	public List<AuditAction> findByLtModifiedDate(Date modifiedDate, int start,
		int end) throws SystemException {
		return findByLtModifiedDate(modifiedDate, start, end, null);
	}

	/**
	 * Returns an ordered range of all the audit actions where modifiedDate &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param start the lower bound of the range of audit actions
	 * @param end the upper bound of the range of audit actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching audit actions
	 * @throws SystemException if a system exception occurred
	 */
	public List<AuditAction> findByLtModifiedDate(Date modifiedDate, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_LTMODIFIEDDATE;
		finderArgs = new Object[] { modifiedDate, start, end, orderByComparator };

		List<AuditAction> list = (List<AuditAction>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AuditAction auditAction : list) {
				if (!Validator.equals(modifiedDate,
							auditAction.getModifiedDate())) {
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

			query.append(_SQL_SELECT_AUDITACTION_WHERE);

			if (modifiedDate == null) {
				query.append(_FINDER_COLUMN_LTMODIFIEDDATE_MODIFIEDDATE_1);
			}
			else {
				query.append(_FINDER_COLUMN_LTMODIFIEDDATE_MODIFIEDDATE_2);
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

				if (modifiedDate != null) {
					qPos.add(CalendarUtil.getTimestamp(modifiedDate));
				}

				list = (List<AuditAction>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first audit action in the ordered set where modifiedDate &lt; &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching audit action
	 * @throws com.liferay.osb.NoSuchAuditActionException if a matching audit action could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AuditAction findByLtModifiedDate_First(Date modifiedDate,
		OrderByComparator orderByComparator)
		throws NoSuchAuditActionException, SystemException {
		AuditAction auditAction = fetchByLtModifiedDate_First(modifiedDate,
				orderByComparator);

		if (auditAction != null) {
			return auditAction;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("modifiedDate=");
		msg.append(modifiedDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAuditActionException(msg.toString());
	}

	/**
	 * Returns the first audit action in the ordered set where modifiedDate &lt; &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching audit action, or <code>null</code> if a matching audit action could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AuditAction fetchByLtModifiedDate_First(Date modifiedDate,
		OrderByComparator orderByComparator) throws SystemException {
		List<AuditAction> list = findByLtModifiedDate(modifiedDate, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last audit action in the ordered set where modifiedDate &lt; &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching audit action
	 * @throws com.liferay.osb.NoSuchAuditActionException if a matching audit action could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AuditAction findByLtModifiedDate_Last(Date modifiedDate,
		OrderByComparator orderByComparator)
		throws NoSuchAuditActionException, SystemException {
		AuditAction auditAction = fetchByLtModifiedDate_Last(modifiedDate,
				orderByComparator);

		if (auditAction != null) {
			return auditAction;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("modifiedDate=");
		msg.append(modifiedDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAuditActionException(msg.toString());
	}

	/**
	 * Returns the last audit action in the ordered set where modifiedDate &lt; &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching audit action, or <code>null</code> if a matching audit action could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AuditAction fetchByLtModifiedDate_Last(Date modifiedDate,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByLtModifiedDate(modifiedDate);

		List<AuditAction> list = findByLtModifiedDate(modifiedDate, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the audit actions before and after the current audit action in the ordered set where modifiedDate &lt; &#63;.
	 *
	 * @param auditActionId the primary key of the current audit action
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next audit action
	 * @throws com.liferay.osb.NoSuchAuditActionException if a audit action with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AuditAction[] findByLtModifiedDate_PrevAndNext(long auditActionId,
		Date modifiedDate, OrderByComparator orderByComparator)
		throws NoSuchAuditActionException, SystemException {
		AuditAction auditAction = findByPrimaryKey(auditActionId);

		Session session = null;

		try {
			session = openSession();

			AuditAction[] array = new AuditActionImpl[3];

			array[0] = getByLtModifiedDate_PrevAndNext(session, auditAction,
					modifiedDate, orderByComparator, true);

			array[1] = auditAction;

			array[2] = getByLtModifiedDate_PrevAndNext(session, auditAction,
					modifiedDate, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AuditAction getByLtModifiedDate_PrevAndNext(Session session,
		AuditAction auditAction, Date modifiedDate,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_AUDITACTION_WHERE);

		if (modifiedDate == null) {
			query.append(_FINDER_COLUMN_LTMODIFIEDDATE_MODIFIEDDATE_1);
		}
		else {
			query.append(_FINDER_COLUMN_LTMODIFIEDDATE_MODIFIEDDATE_2);
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

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (modifiedDate != null) {
			qPos.add(CalendarUtil.getTimestamp(modifiedDate));
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(auditAction);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AuditAction> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the audit action where classNameId = &#63; and classPK = &#63; and mappingClassPK = &#63; or throws a {@link com.liferay.osb.NoSuchAuditActionException} if it could not be found.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param mappingClassPK the mapping class p k
	 * @return the matching audit action
	 * @throws com.liferay.osb.NoSuchAuditActionException if a matching audit action could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AuditAction findByC_C_MC(long classNameId, long classPK,
		long mappingClassPK) throws NoSuchAuditActionException, SystemException {
		AuditAction auditAction = fetchByC_C_MC(classNameId, classPK,
				mappingClassPK);

		if (auditAction == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("classNameId=");
			msg.append(classNameId);

			msg.append(", classPK=");
			msg.append(classPK);

			msg.append(", mappingClassPK=");
			msg.append(mappingClassPK);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchAuditActionException(msg.toString());
		}

		return auditAction;
	}

	/**
	 * Returns the audit action where classNameId = &#63; and classPK = &#63; and mappingClassPK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param mappingClassPK the mapping class p k
	 * @return the matching audit action, or <code>null</code> if a matching audit action could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AuditAction fetchByC_C_MC(long classNameId, long classPK,
		long mappingClassPK) throws SystemException {
		return fetchByC_C_MC(classNameId, classPK, mappingClassPK, true);
	}

	/**
	 * Returns the audit action where classNameId = &#63; and classPK = &#63; and mappingClassPK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param mappingClassPK the mapping class p k
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching audit action, or <code>null</code> if a matching audit action could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AuditAction fetchByC_C_MC(long classNameId, long classPK,
		long mappingClassPK, boolean retrieveFromCache)
		throws SystemException {
		Object[] finderArgs = new Object[] { classNameId, classPK, mappingClassPK };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_C_C_MC,
					finderArgs, this);
		}

		if (result instanceof AuditAction) {
			AuditAction auditAction = (AuditAction)result;

			if ((classNameId != auditAction.getClassNameId()) ||
					(classPK != auditAction.getClassPK()) ||
					(mappingClassPK != auditAction.getMappingClassPK())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_AUDITACTION_WHERE);

			query.append(_FINDER_COLUMN_C_C_MC_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_MC_CLASSPK_2);

			query.append(_FINDER_COLUMN_C_C_MC_MAPPINGCLASSPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				qPos.add(mappingClassPK);

				List<AuditAction> list = q.list();

				result = list;

				AuditAction auditAction = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_C_MC,
						finderArgs, list);
				}
				else {
					auditAction = list.get(0);

					cacheResult(auditAction);

					if ((auditAction.getClassNameId() != classNameId) ||
							(auditAction.getClassPK() != classPK) ||
							(auditAction.getMappingClassPK() != mappingClassPK)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_C_MC,
							finderArgs, auditAction);
					}
				}

				return auditAction;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_C_MC,
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
				return (AuditAction)result;
			}
		}
	}

	/**
	 * Returns all the audit actions where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK &gt; &#63; and action = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param classNameId the class name ID
	 * @param mappingClassPK the mapping class p k
	 * @param action the action
	 * @return the matching audit actions
	 * @throws SystemException if a system exception occurred
	 */
	public List<AuditAction> findByGtMD_C_GtMC_A(Date modifiedDate,
		long classNameId, long mappingClassPK, int action)
		throws SystemException {
		return findByGtMD_C_GtMC_A(modifiedDate, classNameId, mappingClassPK,
			action, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the audit actions where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK &gt; &#63; and action = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param classNameId the class name ID
	 * @param mappingClassPK the mapping class p k
	 * @param action the action
	 * @param start the lower bound of the range of audit actions
	 * @param end the upper bound of the range of audit actions (not inclusive)
	 * @return the range of matching audit actions
	 * @throws SystemException if a system exception occurred
	 */
	public List<AuditAction> findByGtMD_C_GtMC_A(Date modifiedDate,
		long classNameId, long mappingClassPK, int action, int start, int end)
		throws SystemException {
		return findByGtMD_C_GtMC_A(modifiedDate, classNameId, mappingClassPK,
			action, start, end, null);
	}

	/**
	 * Returns an ordered range of all the audit actions where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK &gt; &#63; and action = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param classNameId the class name ID
	 * @param mappingClassPK the mapping class p k
	 * @param action the action
	 * @param start the lower bound of the range of audit actions
	 * @param end the upper bound of the range of audit actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching audit actions
	 * @throws SystemException if a system exception occurred
	 */
	public List<AuditAction> findByGtMD_C_GtMC_A(Date modifiedDate,
		long classNameId, long mappingClassPK, int action, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GTMD_C_GTMC_A;
		finderArgs = new Object[] {
				modifiedDate, classNameId, mappingClassPK, action,
				
				start, end, orderByComparator
			};

		List<AuditAction> list = (List<AuditAction>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AuditAction auditAction : list) {
				if (!Validator.equals(modifiedDate,
							auditAction.getModifiedDate()) ||
						(classNameId != auditAction.getClassNameId()) ||
						(mappingClassPK != auditAction.getMappingClassPK()) ||
						(action != auditAction.getAction())) {
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
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_AUDITACTION_WHERE);

			if (modifiedDate == null) {
				query.append(_FINDER_COLUMN_GTMD_C_GTMC_A_MODIFIEDDATE_1);
			}
			else {
				query.append(_FINDER_COLUMN_GTMD_C_GTMC_A_MODIFIEDDATE_2);
			}

			query.append(_FINDER_COLUMN_GTMD_C_GTMC_A_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_GTMD_C_GTMC_A_MAPPINGCLASSPK_2);

			query.append(_FINDER_COLUMN_GTMD_C_GTMC_A_ACTION_2);

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

				if (modifiedDate != null) {
					qPos.add(CalendarUtil.getTimestamp(modifiedDate));
				}

				qPos.add(classNameId);

				qPos.add(mappingClassPK);

				qPos.add(action);

				list = (List<AuditAction>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first audit action in the ordered set where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK &gt; &#63; and action = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param classNameId the class name ID
	 * @param mappingClassPK the mapping class p k
	 * @param action the action
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching audit action
	 * @throws com.liferay.osb.NoSuchAuditActionException if a matching audit action could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AuditAction findByGtMD_C_GtMC_A_First(Date modifiedDate,
		long classNameId, long mappingClassPK, int action,
		OrderByComparator orderByComparator)
		throws NoSuchAuditActionException, SystemException {
		AuditAction auditAction = fetchByGtMD_C_GtMC_A_First(modifiedDate,
				classNameId, mappingClassPK, action, orderByComparator);

		if (auditAction != null) {
			return auditAction;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("modifiedDate=");
		msg.append(modifiedDate);

		msg.append(", classNameId=");
		msg.append(classNameId);

		msg.append(", mappingClassPK=");
		msg.append(mappingClassPK);

		msg.append(", action=");
		msg.append(action);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAuditActionException(msg.toString());
	}

	/**
	 * Returns the first audit action in the ordered set where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK &gt; &#63; and action = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param classNameId the class name ID
	 * @param mappingClassPK the mapping class p k
	 * @param action the action
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching audit action, or <code>null</code> if a matching audit action could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AuditAction fetchByGtMD_C_GtMC_A_First(Date modifiedDate,
		long classNameId, long mappingClassPK, int action,
		OrderByComparator orderByComparator) throws SystemException {
		List<AuditAction> list = findByGtMD_C_GtMC_A(modifiedDate, classNameId,
				mappingClassPK, action, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last audit action in the ordered set where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK &gt; &#63; and action = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param classNameId the class name ID
	 * @param mappingClassPK the mapping class p k
	 * @param action the action
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching audit action
	 * @throws com.liferay.osb.NoSuchAuditActionException if a matching audit action could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AuditAction findByGtMD_C_GtMC_A_Last(Date modifiedDate,
		long classNameId, long mappingClassPK, int action,
		OrderByComparator orderByComparator)
		throws NoSuchAuditActionException, SystemException {
		AuditAction auditAction = fetchByGtMD_C_GtMC_A_Last(modifiedDate,
				classNameId, mappingClassPK, action, orderByComparator);

		if (auditAction != null) {
			return auditAction;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("modifiedDate=");
		msg.append(modifiedDate);

		msg.append(", classNameId=");
		msg.append(classNameId);

		msg.append(", mappingClassPK=");
		msg.append(mappingClassPK);

		msg.append(", action=");
		msg.append(action);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAuditActionException(msg.toString());
	}

	/**
	 * Returns the last audit action in the ordered set where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK &gt; &#63; and action = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param classNameId the class name ID
	 * @param mappingClassPK the mapping class p k
	 * @param action the action
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching audit action, or <code>null</code> if a matching audit action could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AuditAction fetchByGtMD_C_GtMC_A_Last(Date modifiedDate,
		long classNameId, long mappingClassPK, int action,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByGtMD_C_GtMC_A(modifiedDate, classNameId,
				mappingClassPK, action);

		List<AuditAction> list = findByGtMD_C_GtMC_A(modifiedDate, classNameId,
				mappingClassPK, action, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the audit actions before and after the current audit action in the ordered set where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK &gt; &#63; and action = &#63;.
	 *
	 * @param auditActionId the primary key of the current audit action
	 * @param modifiedDate the modified date
	 * @param classNameId the class name ID
	 * @param mappingClassPK the mapping class p k
	 * @param action the action
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next audit action
	 * @throws com.liferay.osb.NoSuchAuditActionException if a audit action with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AuditAction[] findByGtMD_C_GtMC_A_PrevAndNext(long auditActionId,
		Date modifiedDate, long classNameId, long mappingClassPK, int action,
		OrderByComparator orderByComparator)
		throws NoSuchAuditActionException, SystemException {
		AuditAction auditAction = findByPrimaryKey(auditActionId);

		Session session = null;

		try {
			session = openSession();

			AuditAction[] array = new AuditActionImpl[3];

			array[0] = getByGtMD_C_GtMC_A_PrevAndNext(session, auditAction,
					modifiedDate, classNameId, mappingClassPK, action,
					orderByComparator, true);

			array[1] = auditAction;

			array[2] = getByGtMD_C_GtMC_A_PrevAndNext(session, auditAction,
					modifiedDate, classNameId, mappingClassPK, action,
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

	protected AuditAction getByGtMD_C_GtMC_A_PrevAndNext(Session session,
		AuditAction auditAction, Date modifiedDate, long classNameId,
		long mappingClassPK, int action, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_AUDITACTION_WHERE);

		if (modifiedDate == null) {
			query.append(_FINDER_COLUMN_GTMD_C_GTMC_A_MODIFIEDDATE_1);
		}
		else {
			query.append(_FINDER_COLUMN_GTMD_C_GTMC_A_MODIFIEDDATE_2);
		}

		query.append(_FINDER_COLUMN_GTMD_C_GTMC_A_CLASSNAMEID_2);

		query.append(_FINDER_COLUMN_GTMD_C_GTMC_A_MAPPINGCLASSPK_2);

		query.append(_FINDER_COLUMN_GTMD_C_GTMC_A_ACTION_2);

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

		if (modifiedDate != null) {
			qPos.add(CalendarUtil.getTimestamp(modifiedDate));
		}

		qPos.add(classNameId);

		qPos.add(mappingClassPK);

		qPos.add(action);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(auditAction);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AuditAction> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the audit actions where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK = &#63; and action = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param classNameId the class name ID
	 * @param mappingClassPK the mapping class p k
	 * @param action the action
	 * @return the matching audit actions
	 * @throws SystemException if a system exception occurred
	 */
	public List<AuditAction> findByGtMD_C_MC_A(Date modifiedDate,
		long classNameId, long mappingClassPK, int action)
		throws SystemException {
		return findByGtMD_C_MC_A(modifiedDate, classNameId, mappingClassPK,
			action, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the audit actions where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK = &#63; and action = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param classNameId the class name ID
	 * @param mappingClassPK the mapping class p k
	 * @param action the action
	 * @param start the lower bound of the range of audit actions
	 * @param end the upper bound of the range of audit actions (not inclusive)
	 * @return the range of matching audit actions
	 * @throws SystemException if a system exception occurred
	 */
	public List<AuditAction> findByGtMD_C_MC_A(Date modifiedDate,
		long classNameId, long mappingClassPK, int action, int start, int end)
		throws SystemException {
		return findByGtMD_C_MC_A(modifiedDate, classNameId, mappingClassPK,
			action, start, end, null);
	}

	/**
	 * Returns an ordered range of all the audit actions where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK = &#63; and action = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param classNameId the class name ID
	 * @param mappingClassPK the mapping class p k
	 * @param action the action
	 * @param start the lower bound of the range of audit actions
	 * @param end the upper bound of the range of audit actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching audit actions
	 * @throws SystemException if a system exception occurred
	 */
	public List<AuditAction> findByGtMD_C_MC_A(Date modifiedDate,
		long classNameId, long mappingClassPK, int action, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GTMD_C_MC_A;
		finderArgs = new Object[] {
				modifiedDate, classNameId, mappingClassPK, action,
				
				start, end, orderByComparator
			};

		List<AuditAction> list = (List<AuditAction>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AuditAction auditAction : list) {
				if (!Validator.equals(modifiedDate,
							auditAction.getModifiedDate()) ||
						(classNameId != auditAction.getClassNameId()) ||
						(mappingClassPK != auditAction.getMappingClassPK()) ||
						(action != auditAction.getAction())) {
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
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_AUDITACTION_WHERE);

			if (modifiedDate == null) {
				query.append(_FINDER_COLUMN_GTMD_C_MC_A_MODIFIEDDATE_1);
			}
			else {
				query.append(_FINDER_COLUMN_GTMD_C_MC_A_MODIFIEDDATE_2);
			}

			query.append(_FINDER_COLUMN_GTMD_C_MC_A_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_GTMD_C_MC_A_MAPPINGCLASSPK_2);

			query.append(_FINDER_COLUMN_GTMD_C_MC_A_ACTION_2);

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

				if (modifiedDate != null) {
					qPos.add(CalendarUtil.getTimestamp(modifiedDate));
				}

				qPos.add(classNameId);

				qPos.add(mappingClassPK);

				qPos.add(action);

				list = (List<AuditAction>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first audit action in the ordered set where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK = &#63; and action = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param classNameId the class name ID
	 * @param mappingClassPK the mapping class p k
	 * @param action the action
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching audit action
	 * @throws com.liferay.osb.NoSuchAuditActionException if a matching audit action could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AuditAction findByGtMD_C_MC_A_First(Date modifiedDate,
		long classNameId, long mappingClassPK, int action,
		OrderByComparator orderByComparator)
		throws NoSuchAuditActionException, SystemException {
		AuditAction auditAction = fetchByGtMD_C_MC_A_First(modifiedDate,
				classNameId, mappingClassPK, action, orderByComparator);

		if (auditAction != null) {
			return auditAction;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("modifiedDate=");
		msg.append(modifiedDate);

		msg.append(", classNameId=");
		msg.append(classNameId);

		msg.append(", mappingClassPK=");
		msg.append(mappingClassPK);

		msg.append(", action=");
		msg.append(action);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAuditActionException(msg.toString());
	}

	/**
	 * Returns the first audit action in the ordered set where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK = &#63; and action = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param classNameId the class name ID
	 * @param mappingClassPK the mapping class p k
	 * @param action the action
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching audit action, or <code>null</code> if a matching audit action could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AuditAction fetchByGtMD_C_MC_A_First(Date modifiedDate,
		long classNameId, long mappingClassPK, int action,
		OrderByComparator orderByComparator) throws SystemException {
		List<AuditAction> list = findByGtMD_C_MC_A(modifiedDate, classNameId,
				mappingClassPK, action, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last audit action in the ordered set where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK = &#63; and action = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param classNameId the class name ID
	 * @param mappingClassPK the mapping class p k
	 * @param action the action
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching audit action
	 * @throws com.liferay.osb.NoSuchAuditActionException if a matching audit action could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AuditAction findByGtMD_C_MC_A_Last(Date modifiedDate,
		long classNameId, long mappingClassPK, int action,
		OrderByComparator orderByComparator)
		throws NoSuchAuditActionException, SystemException {
		AuditAction auditAction = fetchByGtMD_C_MC_A_Last(modifiedDate,
				classNameId, mappingClassPK, action, orderByComparator);

		if (auditAction != null) {
			return auditAction;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("modifiedDate=");
		msg.append(modifiedDate);

		msg.append(", classNameId=");
		msg.append(classNameId);

		msg.append(", mappingClassPK=");
		msg.append(mappingClassPK);

		msg.append(", action=");
		msg.append(action);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAuditActionException(msg.toString());
	}

	/**
	 * Returns the last audit action in the ordered set where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK = &#63; and action = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param classNameId the class name ID
	 * @param mappingClassPK the mapping class p k
	 * @param action the action
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching audit action, or <code>null</code> if a matching audit action could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AuditAction fetchByGtMD_C_MC_A_Last(Date modifiedDate,
		long classNameId, long mappingClassPK, int action,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByGtMD_C_MC_A(modifiedDate, classNameId,
				mappingClassPK, action);

		List<AuditAction> list = findByGtMD_C_MC_A(modifiedDate, classNameId,
				mappingClassPK, action, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the audit actions before and after the current audit action in the ordered set where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK = &#63; and action = &#63;.
	 *
	 * @param auditActionId the primary key of the current audit action
	 * @param modifiedDate the modified date
	 * @param classNameId the class name ID
	 * @param mappingClassPK the mapping class p k
	 * @param action the action
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next audit action
	 * @throws com.liferay.osb.NoSuchAuditActionException if a audit action with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AuditAction[] findByGtMD_C_MC_A_PrevAndNext(long auditActionId,
		Date modifiedDate, long classNameId, long mappingClassPK, int action,
		OrderByComparator orderByComparator)
		throws NoSuchAuditActionException, SystemException {
		AuditAction auditAction = findByPrimaryKey(auditActionId);

		Session session = null;

		try {
			session = openSession();

			AuditAction[] array = new AuditActionImpl[3];

			array[0] = getByGtMD_C_MC_A_PrevAndNext(session, auditAction,
					modifiedDate, classNameId, mappingClassPK, action,
					orderByComparator, true);

			array[1] = auditAction;

			array[2] = getByGtMD_C_MC_A_PrevAndNext(session, auditAction,
					modifiedDate, classNameId, mappingClassPK, action,
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

	protected AuditAction getByGtMD_C_MC_A_PrevAndNext(Session session,
		AuditAction auditAction, Date modifiedDate, long classNameId,
		long mappingClassPK, int action, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_AUDITACTION_WHERE);

		if (modifiedDate == null) {
			query.append(_FINDER_COLUMN_GTMD_C_MC_A_MODIFIEDDATE_1);
		}
		else {
			query.append(_FINDER_COLUMN_GTMD_C_MC_A_MODIFIEDDATE_2);
		}

		query.append(_FINDER_COLUMN_GTMD_C_MC_A_CLASSNAMEID_2);

		query.append(_FINDER_COLUMN_GTMD_C_MC_A_MAPPINGCLASSPK_2);

		query.append(_FINDER_COLUMN_GTMD_C_MC_A_ACTION_2);

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

		if (modifiedDate != null) {
			qPos.add(CalendarUtil.getTimestamp(modifiedDate));
		}

		qPos.add(classNameId);

		qPos.add(mappingClassPK);

		qPos.add(action);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(auditAction);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AuditAction> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the audit actions where modifiedDate = &#63; and classNameId = &#63; and classPK = &#63; and mappingClassPK = &#63; and action = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param mappingClassPK the mapping class p k
	 * @param action the action
	 * @return the matching audit actions
	 * @throws SystemException if a system exception occurred
	 */
	public List<AuditAction> findByMD_C_C_MC_A(Date modifiedDate,
		long classNameId, long classPK, long mappingClassPK, int action)
		throws SystemException {
		return findByMD_C_C_MC_A(modifiedDate, classNameId, classPK,
			mappingClassPK, action, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the audit actions where modifiedDate = &#63; and classNameId = &#63; and classPK = &#63; and mappingClassPK = &#63; and action = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param mappingClassPK the mapping class p k
	 * @param action the action
	 * @param start the lower bound of the range of audit actions
	 * @param end the upper bound of the range of audit actions (not inclusive)
	 * @return the range of matching audit actions
	 * @throws SystemException if a system exception occurred
	 */
	public List<AuditAction> findByMD_C_C_MC_A(Date modifiedDate,
		long classNameId, long classPK, long mappingClassPK, int action,
		int start, int end) throws SystemException {
		return findByMD_C_C_MC_A(modifiedDate, classNameId, classPK,
			mappingClassPK, action, start, end, null);
	}

	/**
	 * Returns an ordered range of all the audit actions where modifiedDate = &#63; and classNameId = &#63; and classPK = &#63; and mappingClassPK = &#63; and action = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param mappingClassPK the mapping class p k
	 * @param action the action
	 * @param start the lower bound of the range of audit actions
	 * @param end the upper bound of the range of audit actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching audit actions
	 * @throws SystemException if a system exception occurred
	 */
	public List<AuditAction> findByMD_C_C_MC_A(Date modifiedDate,
		long classNameId, long classPK, long mappingClassPK, int action,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MD_C_C_MC_A;
			finderArgs = new Object[] {
					modifiedDate, classNameId, classPK, mappingClassPK, action
				};
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_MD_C_C_MC_A;
			finderArgs = new Object[] {
					modifiedDate, classNameId, classPK, mappingClassPK, action,
					
					start, end, orderByComparator
				};
		}

		List<AuditAction> list = (List<AuditAction>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AuditAction auditAction : list) {
				if (!Validator.equals(modifiedDate,
							auditAction.getModifiedDate()) ||
						(classNameId != auditAction.getClassNameId()) ||
						(classPK != auditAction.getClassPK()) ||
						(mappingClassPK != auditAction.getMappingClassPK()) ||
						(action != auditAction.getAction())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(7 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(6);
			}

			query.append(_SQL_SELECT_AUDITACTION_WHERE);

			if (modifiedDate == null) {
				query.append(_FINDER_COLUMN_MD_C_C_MC_A_MODIFIEDDATE_1);
			}
			else {
				query.append(_FINDER_COLUMN_MD_C_C_MC_A_MODIFIEDDATE_2);
			}

			query.append(_FINDER_COLUMN_MD_C_C_MC_A_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_MD_C_C_MC_A_CLASSPK_2);

			query.append(_FINDER_COLUMN_MD_C_C_MC_A_MAPPINGCLASSPK_2);

			query.append(_FINDER_COLUMN_MD_C_C_MC_A_ACTION_2);

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

				if (modifiedDate != null) {
					qPos.add(CalendarUtil.getTimestamp(modifiedDate));
				}

				qPos.add(classNameId);

				qPos.add(classPK);

				qPos.add(mappingClassPK);

				qPos.add(action);

				list = (List<AuditAction>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first audit action in the ordered set where modifiedDate = &#63; and classNameId = &#63; and classPK = &#63; and mappingClassPK = &#63; and action = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param mappingClassPK the mapping class p k
	 * @param action the action
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching audit action
	 * @throws com.liferay.osb.NoSuchAuditActionException if a matching audit action could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AuditAction findByMD_C_C_MC_A_First(Date modifiedDate,
		long classNameId, long classPK, long mappingClassPK, int action,
		OrderByComparator orderByComparator)
		throws NoSuchAuditActionException, SystemException {
		AuditAction auditAction = fetchByMD_C_C_MC_A_First(modifiedDate,
				classNameId, classPK, mappingClassPK, action, orderByComparator);

		if (auditAction != null) {
			return auditAction;
		}

		StringBundler msg = new StringBundler(12);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("modifiedDate=");
		msg.append(modifiedDate);

		msg.append(", classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(", mappingClassPK=");
		msg.append(mappingClassPK);

		msg.append(", action=");
		msg.append(action);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAuditActionException(msg.toString());
	}

	/**
	 * Returns the first audit action in the ordered set where modifiedDate = &#63; and classNameId = &#63; and classPK = &#63; and mappingClassPK = &#63; and action = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param mappingClassPK the mapping class p k
	 * @param action the action
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching audit action, or <code>null</code> if a matching audit action could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AuditAction fetchByMD_C_C_MC_A_First(Date modifiedDate,
		long classNameId, long classPK, long mappingClassPK, int action,
		OrderByComparator orderByComparator) throws SystemException {
		List<AuditAction> list = findByMD_C_C_MC_A(modifiedDate, classNameId,
				classPK, mappingClassPK, action, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last audit action in the ordered set where modifiedDate = &#63; and classNameId = &#63; and classPK = &#63; and mappingClassPK = &#63; and action = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param mappingClassPK the mapping class p k
	 * @param action the action
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching audit action
	 * @throws com.liferay.osb.NoSuchAuditActionException if a matching audit action could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AuditAction findByMD_C_C_MC_A_Last(Date modifiedDate,
		long classNameId, long classPK, long mappingClassPK, int action,
		OrderByComparator orderByComparator)
		throws NoSuchAuditActionException, SystemException {
		AuditAction auditAction = fetchByMD_C_C_MC_A_Last(modifiedDate,
				classNameId, classPK, mappingClassPK, action, orderByComparator);

		if (auditAction != null) {
			return auditAction;
		}

		StringBundler msg = new StringBundler(12);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("modifiedDate=");
		msg.append(modifiedDate);

		msg.append(", classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(", mappingClassPK=");
		msg.append(mappingClassPK);

		msg.append(", action=");
		msg.append(action);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAuditActionException(msg.toString());
	}

	/**
	 * Returns the last audit action in the ordered set where modifiedDate = &#63; and classNameId = &#63; and classPK = &#63; and mappingClassPK = &#63; and action = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param mappingClassPK the mapping class p k
	 * @param action the action
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching audit action, or <code>null</code> if a matching audit action could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AuditAction fetchByMD_C_C_MC_A_Last(Date modifiedDate,
		long classNameId, long classPK, long mappingClassPK, int action,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByMD_C_C_MC_A(modifiedDate, classNameId, classPK,
				mappingClassPK, action);

		List<AuditAction> list = findByMD_C_C_MC_A(modifiedDate, classNameId,
				classPK, mappingClassPK, action, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the audit actions before and after the current audit action in the ordered set where modifiedDate = &#63; and classNameId = &#63; and classPK = &#63; and mappingClassPK = &#63; and action = &#63;.
	 *
	 * @param auditActionId the primary key of the current audit action
	 * @param modifiedDate the modified date
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param mappingClassPK the mapping class p k
	 * @param action the action
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next audit action
	 * @throws com.liferay.osb.NoSuchAuditActionException if a audit action with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AuditAction[] findByMD_C_C_MC_A_PrevAndNext(long auditActionId,
		Date modifiedDate, long classNameId, long classPK, long mappingClassPK,
		int action, OrderByComparator orderByComparator)
		throws NoSuchAuditActionException, SystemException {
		AuditAction auditAction = findByPrimaryKey(auditActionId);

		Session session = null;

		try {
			session = openSession();

			AuditAction[] array = new AuditActionImpl[3];

			array[0] = getByMD_C_C_MC_A_PrevAndNext(session, auditAction,
					modifiedDate, classNameId, classPK, mappingClassPK, action,
					orderByComparator, true);

			array[1] = auditAction;

			array[2] = getByMD_C_C_MC_A_PrevAndNext(session, auditAction,
					modifiedDate, classNameId, classPK, mappingClassPK, action,
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

	protected AuditAction getByMD_C_C_MC_A_PrevAndNext(Session session,
		AuditAction auditAction, Date modifiedDate, long classNameId,
		long classPK, long mappingClassPK, int action,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_AUDITACTION_WHERE);

		if (modifiedDate == null) {
			query.append(_FINDER_COLUMN_MD_C_C_MC_A_MODIFIEDDATE_1);
		}
		else {
			query.append(_FINDER_COLUMN_MD_C_C_MC_A_MODIFIEDDATE_2);
		}

		query.append(_FINDER_COLUMN_MD_C_C_MC_A_CLASSNAMEID_2);

		query.append(_FINDER_COLUMN_MD_C_C_MC_A_CLASSPK_2);

		query.append(_FINDER_COLUMN_MD_C_C_MC_A_MAPPINGCLASSPK_2);

		query.append(_FINDER_COLUMN_MD_C_C_MC_A_ACTION_2);

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

		if (modifiedDate != null) {
			qPos.add(CalendarUtil.getTimestamp(modifiedDate));
		}

		qPos.add(classNameId);

		qPos.add(classPK);

		qPos.add(mappingClassPK);

		qPos.add(action);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(auditAction);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AuditAction> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the audit actions.
	 *
	 * @return the audit actions
	 * @throws SystemException if a system exception occurred
	 */
	public List<AuditAction> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the audit actions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of audit actions
	 * @param end the upper bound of the range of audit actions (not inclusive)
	 * @return the range of audit actions
	 * @throws SystemException if a system exception occurred
	 */
	public List<AuditAction> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the audit actions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of audit actions
	 * @param end the upper bound of the range of audit actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of audit actions
	 * @throws SystemException if a system exception occurred
	 */
	public List<AuditAction> findAll(int start, int end,
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

		List<AuditAction> list = (List<AuditAction>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_AUDITACTION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_AUDITACTION;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<AuditAction>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<AuditAction>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the audit actions where modifiedDate &lt; &#63; from the database.
	 *
	 * @param modifiedDate the modified date
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByLtModifiedDate(Date modifiedDate)
		throws SystemException {
		for (AuditAction auditAction : findByLtModifiedDate(modifiedDate)) {
			remove(auditAction);
		}
	}

	/**
	 * Removes the audit action where classNameId = &#63; and classPK = &#63; and mappingClassPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param mappingClassPK the mapping class p k
	 * @return the audit action that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public AuditAction removeByC_C_MC(long classNameId, long classPK,
		long mappingClassPK) throws NoSuchAuditActionException, SystemException {
		AuditAction auditAction = findByC_C_MC(classNameId, classPK,
				mappingClassPK);

		return remove(auditAction);
	}

	/**
	 * Removes all the audit actions where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK &gt; &#63; and action = &#63; from the database.
	 *
	 * @param modifiedDate the modified date
	 * @param classNameId the class name ID
	 * @param mappingClassPK the mapping class p k
	 * @param action the action
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByGtMD_C_GtMC_A(Date modifiedDate, long classNameId,
		long mappingClassPK, int action) throws SystemException {
		for (AuditAction auditAction : findByGtMD_C_GtMC_A(modifiedDate,
				classNameId, mappingClassPK, action)) {
			remove(auditAction);
		}
	}

	/**
	 * Removes all the audit actions where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK = &#63; and action = &#63; from the database.
	 *
	 * @param modifiedDate the modified date
	 * @param classNameId the class name ID
	 * @param mappingClassPK the mapping class p k
	 * @param action the action
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByGtMD_C_MC_A(Date modifiedDate, long classNameId,
		long mappingClassPK, int action) throws SystemException {
		for (AuditAction auditAction : findByGtMD_C_MC_A(modifiedDate,
				classNameId, mappingClassPK, action)) {
			remove(auditAction);
		}
	}

	/**
	 * Removes all the audit actions where modifiedDate = &#63; and classNameId = &#63; and classPK = &#63; and mappingClassPK = &#63; and action = &#63; from the database.
	 *
	 * @param modifiedDate the modified date
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param mappingClassPK the mapping class p k
	 * @param action the action
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByMD_C_C_MC_A(Date modifiedDate, long classNameId,
		long classPK, long mappingClassPK, int action)
		throws SystemException {
		for (AuditAction auditAction : findByMD_C_C_MC_A(modifiedDate,
				classNameId, classPK, mappingClassPK, action)) {
			remove(auditAction);
		}
	}

	/**
	 * Removes all the audit actions from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (AuditAction auditAction : findAll()) {
			remove(auditAction);
		}
	}

	/**
	 * Returns the number of audit actions where modifiedDate &lt; &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @return the number of matching audit actions
	 * @throws SystemException if a system exception occurred
	 */
	public int countByLtModifiedDate(Date modifiedDate)
		throws SystemException {
		Object[] finderArgs = new Object[] { modifiedDate };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_LTMODIFIEDDATE,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_AUDITACTION_WHERE);

			if (modifiedDate == null) {
				query.append(_FINDER_COLUMN_LTMODIFIEDDATE_MODIFIEDDATE_1);
			}
			else {
				query.append(_FINDER_COLUMN_LTMODIFIEDDATE_MODIFIEDDATE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (modifiedDate != null) {
					qPos.add(CalendarUtil.getTimestamp(modifiedDate));
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

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_LTMODIFIEDDATE,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of audit actions where classNameId = &#63; and classPK = &#63; and mappingClassPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param mappingClassPK the mapping class p k
	 * @return the number of matching audit actions
	 * @throws SystemException if a system exception occurred
	 */
	public int countByC_C_MC(long classNameId, long classPK, long mappingClassPK)
		throws SystemException {
		Object[] finderArgs = new Object[] { classNameId, classPK, mappingClassPK };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_C_C_MC,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_AUDITACTION_WHERE);

			query.append(_FINDER_COLUMN_C_C_MC_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_MC_CLASSPK_2);

			query.append(_FINDER_COLUMN_C_C_MC_MAPPINGCLASSPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				qPos.add(mappingClassPK);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_C_MC,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of audit actions where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK &gt; &#63; and action = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param classNameId the class name ID
	 * @param mappingClassPK the mapping class p k
	 * @param action the action
	 * @return the number of matching audit actions
	 * @throws SystemException if a system exception occurred
	 */
	public int countByGtMD_C_GtMC_A(Date modifiedDate, long classNameId,
		long mappingClassPK, int action) throws SystemException {
		Object[] finderArgs = new Object[] {
				modifiedDate, classNameId, mappingClassPK, action
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_GTMD_C_GTMC_A,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_AUDITACTION_WHERE);

			if (modifiedDate == null) {
				query.append(_FINDER_COLUMN_GTMD_C_GTMC_A_MODIFIEDDATE_1);
			}
			else {
				query.append(_FINDER_COLUMN_GTMD_C_GTMC_A_MODIFIEDDATE_2);
			}

			query.append(_FINDER_COLUMN_GTMD_C_GTMC_A_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_GTMD_C_GTMC_A_MAPPINGCLASSPK_2);

			query.append(_FINDER_COLUMN_GTMD_C_GTMC_A_ACTION_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (modifiedDate != null) {
					qPos.add(CalendarUtil.getTimestamp(modifiedDate));
				}

				qPos.add(classNameId);

				qPos.add(mappingClassPK);

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

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_GTMD_C_GTMC_A,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of audit actions where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK = &#63; and action = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param classNameId the class name ID
	 * @param mappingClassPK the mapping class p k
	 * @param action the action
	 * @return the number of matching audit actions
	 * @throws SystemException if a system exception occurred
	 */
	public int countByGtMD_C_MC_A(Date modifiedDate, long classNameId,
		long mappingClassPK, int action) throws SystemException {
		Object[] finderArgs = new Object[] {
				modifiedDate, classNameId, mappingClassPK, action
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_GTMD_C_MC_A,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_AUDITACTION_WHERE);

			if (modifiedDate == null) {
				query.append(_FINDER_COLUMN_GTMD_C_MC_A_MODIFIEDDATE_1);
			}
			else {
				query.append(_FINDER_COLUMN_GTMD_C_MC_A_MODIFIEDDATE_2);
			}

			query.append(_FINDER_COLUMN_GTMD_C_MC_A_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_GTMD_C_MC_A_MAPPINGCLASSPK_2);

			query.append(_FINDER_COLUMN_GTMD_C_MC_A_ACTION_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (modifiedDate != null) {
					qPos.add(CalendarUtil.getTimestamp(modifiedDate));
				}

				qPos.add(classNameId);

				qPos.add(mappingClassPK);

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

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_GTMD_C_MC_A,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of audit actions where modifiedDate = &#63; and classNameId = &#63; and classPK = &#63; and mappingClassPK = &#63; and action = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param mappingClassPK the mapping class p k
	 * @param action the action
	 * @return the number of matching audit actions
	 * @throws SystemException if a system exception occurred
	 */
	public int countByMD_C_C_MC_A(Date modifiedDate, long classNameId,
		long classPK, long mappingClassPK, int action)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				modifiedDate, classNameId, classPK, mappingClassPK, action
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_MD_C_C_MC_A,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(6);

			query.append(_SQL_COUNT_AUDITACTION_WHERE);

			if (modifiedDate == null) {
				query.append(_FINDER_COLUMN_MD_C_C_MC_A_MODIFIEDDATE_1);
			}
			else {
				query.append(_FINDER_COLUMN_MD_C_C_MC_A_MODIFIEDDATE_2);
			}

			query.append(_FINDER_COLUMN_MD_C_C_MC_A_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_MD_C_C_MC_A_CLASSPK_2);

			query.append(_FINDER_COLUMN_MD_C_C_MC_A_MAPPINGCLASSPK_2);

			query.append(_FINDER_COLUMN_MD_C_C_MC_A_ACTION_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (modifiedDate != null) {
					qPos.add(CalendarUtil.getTimestamp(modifiedDate));
				}

				qPos.add(classNameId);

				qPos.add(classPK);

				qPos.add(mappingClassPK);

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_MD_C_C_MC_A,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of audit actions.
	 *
	 * @return the number of audit actions
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_AUDITACTION);

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
	 * Initializes the audit action persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.osb.model.AuditAction")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<AuditAction>> listenersList = new ArrayList<ModelListener<AuditAction>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<AuditAction>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(AuditActionImpl.class.getName());
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
	private static final String _SQL_SELECT_AUDITACTION = "SELECT auditAction FROM AuditAction auditAction";
	private static final String _SQL_SELECT_AUDITACTION_WHERE = "SELECT auditAction FROM AuditAction auditAction WHERE ";
	private static final String _SQL_COUNT_AUDITACTION = "SELECT COUNT(auditAction) FROM AuditAction auditAction";
	private static final String _SQL_COUNT_AUDITACTION_WHERE = "SELECT COUNT(auditAction) FROM AuditAction auditAction WHERE ";
	private static final String _FINDER_COLUMN_LTMODIFIEDDATE_MODIFIEDDATE_1 = "auditAction.modifiedDate < NULL";
	private static final String _FINDER_COLUMN_LTMODIFIEDDATE_MODIFIEDDATE_2 = "auditAction.modifiedDate < ?";
	private static final String _FINDER_COLUMN_C_C_MC_CLASSNAMEID_2 = "auditAction.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_C_C_MC_CLASSPK_2 = "auditAction.classPK = ? AND ";
	private static final String _FINDER_COLUMN_C_C_MC_MAPPINGCLASSPK_2 = "auditAction.mappingClassPK = ?";
	private static final String _FINDER_COLUMN_GTMD_C_GTMC_A_MODIFIEDDATE_1 = "auditAction.modifiedDate > NULL AND ";
	private static final String _FINDER_COLUMN_GTMD_C_GTMC_A_MODIFIEDDATE_2 = "auditAction.modifiedDate > ? AND ";
	private static final String _FINDER_COLUMN_GTMD_C_GTMC_A_CLASSNAMEID_2 = "auditAction.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_GTMD_C_GTMC_A_MAPPINGCLASSPK_2 = "auditAction.mappingClassPK > ? AND ";
	private static final String _FINDER_COLUMN_GTMD_C_GTMC_A_ACTION_2 = "auditAction.action = ?";
	private static final String _FINDER_COLUMN_GTMD_C_MC_A_MODIFIEDDATE_1 = "auditAction.modifiedDate > NULL AND ";
	private static final String _FINDER_COLUMN_GTMD_C_MC_A_MODIFIEDDATE_2 = "auditAction.modifiedDate > ? AND ";
	private static final String _FINDER_COLUMN_GTMD_C_MC_A_CLASSNAMEID_2 = "auditAction.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_GTMD_C_MC_A_MAPPINGCLASSPK_2 = "auditAction.mappingClassPK = ? AND ";
	private static final String _FINDER_COLUMN_GTMD_C_MC_A_ACTION_2 = "auditAction.action = ?";
	private static final String _FINDER_COLUMN_MD_C_C_MC_A_MODIFIEDDATE_1 = "auditAction.modifiedDate IS NULL AND ";
	private static final String _FINDER_COLUMN_MD_C_C_MC_A_MODIFIEDDATE_2 = "auditAction.modifiedDate = ? AND ";
	private static final String _FINDER_COLUMN_MD_C_C_MC_A_CLASSNAMEID_2 = "auditAction.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_MD_C_C_MC_A_CLASSPK_2 = "auditAction.classPK = ? AND ";
	private static final String _FINDER_COLUMN_MD_C_C_MC_A_MAPPINGCLASSPK_2 = "auditAction.mappingClassPK = ? AND ";
	private static final String _FINDER_COLUMN_MD_C_C_MC_A_ACTION_2 = "auditAction.action = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "auditAction.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AuditAction exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No AuditAction exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(AuditActionPersistenceImpl.class);
	private static AuditAction _nullAuditAction = new AuditActionImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<AuditAction> toCacheModel() {
				return _nullAuditActionCacheModel;
			}
		};

	private static CacheModel<AuditAction> _nullAuditActionCacheModel = new CacheModel<AuditAction>() {
			public AuditAction toEntityModel() {
				return _nullAuditAction;
			}
		};
}