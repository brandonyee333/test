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

import com.liferay.osb.NoSuchTicketFlagException;
import com.liferay.osb.model.TicketFlag;
import com.liferay.osb.model.impl.TicketFlagImpl;
import com.liferay.osb.model.impl.TicketFlagModelImpl;

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
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the ticket flag service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TicketFlagPersistence
 * @see TicketFlagUtil
 * @generated
 */
public class TicketFlagPersistenceImpl extends BasePersistenceImpl<TicketFlag>
	implements TicketFlagPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link TicketFlagUtil} to access the ticket flag persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = TicketFlagImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TICKETENTRYID =
		new FinderPath(TicketFlagModelImpl.ENTITY_CACHE_ENABLED,
			TicketFlagModelImpl.FINDER_CACHE_ENABLED, TicketFlagImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByTicketEntryId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TICKETENTRYID =
		new FinderPath(TicketFlagModelImpl.ENTITY_CACHE_ENABLED,
			TicketFlagModelImpl.FINDER_CACHE_ENABLED, TicketFlagImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByTicketEntryId",
			new String[] { Long.class.getName() },
			TicketFlagModelImpl.TICKETENTRYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TICKETENTRYID = new FinderPath(TicketFlagModelImpl.ENTITY_CACHE_ENABLED,
			TicketFlagModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByTicketEntryId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_AEI_T = new FinderPath(TicketFlagModelImpl.ENTITY_CACHE_ENABLED,
			TicketFlagModelImpl.FINDER_CACHE_ENABLED, TicketFlagImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAEI_T",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_T = new FinderPath(TicketFlagModelImpl.ENTITY_CACHE_ENABLED,
			TicketFlagModelImpl.FINDER_CACHE_ENABLED, TicketFlagImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAEI_T",
			new String[] { Long.class.getName(), Integer.class.getName() },
			TicketFlagModelImpl.ACCOUNTENTRYID_COLUMN_BITMASK |
			TicketFlagModelImpl.TYPE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_AEI_T = new FinderPath(TicketFlagModelImpl.ENTITY_CACHE_ENABLED,
			TicketFlagModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAEI_T",
			new String[] { Long.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TEI_T = new FinderPath(TicketFlagModelImpl.ENTITY_CACHE_ENABLED,
			TicketFlagModelImpl.FINDER_CACHE_ENABLED, TicketFlagImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByTEI_T",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_T = new FinderPath(TicketFlagModelImpl.ENTITY_CACHE_ENABLED,
			TicketFlagModelImpl.FINDER_CACHE_ENABLED, TicketFlagImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByTEI_T",
			new String[] { Long.class.getName(), Integer.class.getName() },
			TicketFlagModelImpl.TICKETENTRYID_COLUMN_BITMASK |
			TicketFlagModelImpl.TYPE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TEI_T = new FinderPath(TicketFlagModelImpl.ENTITY_CACHE_ENABLED,
			TicketFlagModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByTEI_T",
			new String[] { Long.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TEI_T_F = new FinderPath(TicketFlagModelImpl.ENTITY_CACHE_ENABLED,
			TicketFlagModelImpl.FINDER_CACHE_ENABLED, TicketFlagImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByTEI_T_F",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_T_F =
		new FinderPath(TicketFlagModelImpl.ENTITY_CACHE_ENABLED,
			TicketFlagModelImpl.FINDER_CACHE_ENABLED, TicketFlagImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByTEI_T_F",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName()
			},
			TicketFlagModelImpl.TICKETENTRYID_COLUMN_BITMASK |
			TicketFlagModelImpl.TYPE_COLUMN_BITMASK |
			TicketFlagModelImpl.FLAG_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TEI_T_F = new FinderPath(TicketFlagModelImpl.ENTITY_CACHE_ENABLED,
			TicketFlagModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByTEI_T_F",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_TEI_T_F = new FinderPath(TicketFlagModelImpl.ENTITY_CACHE_ENABLED,
			TicketFlagModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByTEI_T_F",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_FETCH_BY_U_AEI_TEI_T = new FinderPath(TicketFlagModelImpl.ENTITY_CACHE_ENABLED,
			TicketFlagModelImpl.FINDER_CACHE_ENABLED, TicketFlagImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByU_AEI_TEI_T",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			},
			TicketFlagModelImpl.USERID_COLUMN_BITMASK |
			TicketFlagModelImpl.ACCOUNTENTRYID_COLUMN_BITMASK |
			TicketFlagModelImpl.TICKETENTRYID_COLUMN_BITMASK |
			TicketFlagModelImpl.TYPE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_U_AEI_TEI_T = new FinderPath(TicketFlagModelImpl.ENTITY_CACHE_ENABLED,
			TicketFlagModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_AEI_TEI_T",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(TicketFlagModelImpl.ENTITY_CACHE_ENABLED,
			TicketFlagModelImpl.FINDER_CACHE_ENABLED, TicketFlagImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(TicketFlagModelImpl.ENTITY_CACHE_ENABLED,
			TicketFlagModelImpl.FINDER_CACHE_ENABLED, TicketFlagImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(TicketFlagModelImpl.ENTITY_CACHE_ENABLED,
			TicketFlagModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the ticket flag in the entity cache if it is enabled.
	 *
	 * @param ticketFlag the ticket flag
	 */
	public void cacheResult(TicketFlag ticketFlag) {
		EntityCacheUtil.putResult(TicketFlagModelImpl.ENTITY_CACHE_ENABLED,
			TicketFlagImpl.class, ticketFlag.getPrimaryKey(), ticketFlag);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_AEI_TEI_T,
			new Object[] {
				Long.valueOf(ticketFlag.getUserId()),
				Long.valueOf(ticketFlag.getAccountEntryId()),
				Long.valueOf(ticketFlag.getTicketEntryId()),
				Integer.valueOf(ticketFlag.getType())
			}, ticketFlag);

		ticketFlag.resetOriginalValues();
	}

	/**
	 * Caches the ticket flags in the entity cache if it is enabled.
	 *
	 * @param ticketFlags the ticket flags
	 */
	public void cacheResult(List<TicketFlag> ticketFlags) {
		for (TicketFlag ticketFlag : ticketFlags) {
			if (EntityCacheUtil.getResult(
						TicketFlagModelImpl.ENTITY_CACHE_ENABLED,
						TicketFlagImpl.class, ticketFlag.getPrimaryKey()) == null) {
				cacheResult(ticketFlag);
			}
			else {
				ticketFlag.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all ticket flags.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(TicketFlagImpl.class.getName());
		}

		EntityCacheUtil.clearCache(TicketFlagImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the ticket flag.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(TicketFlag ticketFlag) {
		EntityCacheUtil.removeResult(TicketFlagModelImpl.ENTITY_CACHE_ENABLED,
			TicketFlagImpl.class, ticketFlag.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(ticketFlag);
	}

	@Override
	public void clearCache(List<TicketFlag> ticketFlags) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (TicketFlag ticketFlag : ticketFlags) {
			EntityCacheUtil.removeResult(TicketFlagModelImpl.ENTITY_CACHE_ENABLED,
				TicketFlagImpl.class, ticketFlag.getPrimaryKey());

			clearUniqueFindersCache(ticketFlag);
		}
	}

	protected void cacheUniqueFindersCache(TicketFlag ticketFlag) {
		if (ticketFlag.isNew()) {
			Object[] args = new Object[] {
					Long.valueOf(ticketFlag.getUserId()),
					Long.valueOf(ticketFlag.getAccountEntryId()),
					Long.valueOf(ticketFlag.getTicketEntryId()),
					Integer.valueOf(ticketFlag.getType())
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_U_AEI_TEI_T, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_AEI_TEI_T, args,
				ticketFlag);
		}
		else {
			TicketFlagModelImpl ticketFlagModelImpl = (TicketFlagModelImpl)ticketFlag;

			if ((ticketFlagModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_U_AEI_TEI_T.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(ticketFlag.getUserId()),
						Long.valueOf(ticketFlag.getAccountEntryId()),
						Long.valueOf(ticketFlag.getTicketEntryId()),
						Integer.valueOf(ticketFlag.getType())
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_U_AEI_TEI_T,
					args, Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_AEI_TEI_T,
					args, ticketFlag);
			}
		}
	}

	protected void clearUniqueFindersCache(TicketFlag ticketFlag) {
		TicketFlagModelImpl ticketFlagModelImpl = (TicketFlagModelImpl)ticketFlag;

		Object[] args = new Object[] {
				Long.valueOf(ticketFlag.getUserId()),
				Long.valueOf(ticketFlag.getAccountEntryId()),
				Long.valueOf(ticketFlag.getTicketEntryId()),
				Integer.valueOf(ticketFlag.getType())
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_U_AEI_TEI_T, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_U_AEI_TEI_T, args);

		if ((ticketFlagModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_U_AEI_TEI_T.getColumnBitmask()) != 0) {
			args = new Object[] {
					Long.valueOf(ticketFlagModelImpl.getOriginalUserId()),
					Long.valueOf(ticketFlagModelImpl.getOriginalAccountEntryId()),
					Long.valueOf(ticketFlagModelImpl.getOriginalTicketEntryId()),
					Integer.valueOf(ticketFlagModelImpl.getOriginalType())
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_U_AEI_TEI_T, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_U_AEI_TEI_T, args);
		}
	}

	/**
	 * Creates a new ticket flag with the primary key. Does not add the ticket flag to the database.
	 *
	 * @param ticketFlagId the primary key for the new ticket flag
	 * @return the new ticket flag
	 */
	public TicketFlag create(long ticketFlagId) {
		TicketFlag ticketFlag = new TicketFlagImpl();

		ticketFlag.setNew(true);
		ticketFlag.setPrimaryKey(ticketFlagId);

		return ticketFlag;
	}

	/**
	 * Removes the ticket flag with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ticketFlagId the primary key of the ticket flag
	 * @return the ticket flag that was removed
	 * @throws com.liferay.osb.NoSuchTicketFlagException if a ticket flag with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketFlag remove(long ticketFlagId)
		throws NoSuchTicketFlagException, SystemException {
		return remove(Long.valueOf(ticketFlagId));
	}

	/**
	 * Removes the ticket flag with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ticket flag
	 * @return the ticket flag that was removed
	 * @throws com.liferay.osb.NoSuchTicketFlagException if a ticket flag with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TicketFlag remove(Serializable primaryKey)
		throws NoSuchTicketFlagException, SystemException {
		Session session = null;

		try {
			session = openSession();

			TicketFlag ticketFlag = (TicketFlag)session.get(TicketFlagImpl.class,
					primaryKey);

			if (ticketFlag == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTicketFlagException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(ticketFlag);
		}
		catch (NoSuchTicketFlagException nsee) {
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
	protected TicketFlag removeImpl(TicketFlag ticketFlag)
		throws SystemException {
		ticketFlag = toUnwrappedModel(ticketFlag);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, ticketFlag);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(ticketFlag);

		return ticketFlag;
	}

	@Override
	public TicketFlag updateImpl(com.liferay.osb.model.TicketFlag ticketFlag,
		boolean merge) throws SystemException {
		ticketFlag = toUnwrappedModel(ticketFlag);

		boolean isNew = ticketFlag.isNew();

		TicketFlagModelImpl ticketFlagModelImpl = (TicketFlagModelImpl)ticketFlag;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, ticketFlag, merge);

			ticketFlag.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !TicketFlagModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((ticketFlagModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TICKETENTRYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(ticketFlagModelImpl.getOriginalTicketEntryId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TICKETENTRYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TICKETENTRYID,
					args);

				args = new Object[] {
						Long.valueOf(ticketFlagModelImpl.getTicketEntryId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TICKETENTRYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TICKETENTRYID,
					args);
			}

			if ((ticketFlagModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_T.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(ticketFlagModelImpl.getOriginalAccountEntryId()),
						Integer.valueOf(ticketFlagModelImpl.getOriginalType())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_AEI_T, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_T,
					args);

				args = new Object[] {
						Long.valueOf(ticketFlagModelImpl.getAccountEntryId()),
						Integer.valueOf(ticketFlagModelImpl.getType())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_AEI_T, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_T,
					args);
			}

			if ((ticketFlagModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_T.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(ticketFlagModelImpl.getOriginalTicketEntryId()),
						Integer.valueOf(ticketFlagModelImpl.getOriginalType())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TEI_T, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_T,
					args);

				args = new Object[] {
						Long.valueOf(ticketFlagModelImpl.getTicketEntryId()),
						Integer.valueOf(ticketFlagModelImpl.getType())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TEI_T, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_T,
					args);
			}

			if ((ticketFlagModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_T_F.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(ticketFlagModelImpl.getOriginalTicketEntryId()),
						Integer.valueOf(ticketFlagModelImpl.getOriginalType()),
						Integer.valueOf(ticketFlagModelImpl.getOriginalFlag())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TEI_T_F, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_T_F,
					args);

				args = new Object[] {
						Long.valueOf(ticketFlagModelImpl.getTicketEntryId()),
						Integer.valueOf(ticketFlagModelImpl.getType()),
						Integer.valueOf(ticketFlagModelImpl.getFlag())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TEI_T_F, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_T_F,
					args);
			}
		}

		EntityCacheUtil.putResult(TicketFlagModelImpl.ENTITY_CACHE_ENABLED,
			TicketFlagImpl.class, ticketFlag.getPrimaryKey(), ticketFlag);

		clearUniqueFindersCache(ticketFlag);
		cacheUniqueFindersCache(ticketFlag);

		return ticketFlag;
	}

	protected TicketFlag toUnwrappedModel(TicketFlag ticketFlag) {
		if (ticketFlag instanceof TicketFlagImpl) {
			return ticketFlag;
		}

		TicketFlagImpl ticketFlagImpl = new TicketFlagImpl();

		ticketFlagImpl.setNew(ticketFlag.isNew());
		ticketFlagImpl.setPrimaryKey(ticketFlag.getPrimaryKey());

		ticketFlagImpl.setTicketFlagId(ticketFlag.getTicketFlagId());
		ticketFlagImpl.setUserId(ticketFlag.getUserId());
		ticketFlagImpl.setModifiedDate(ticketFlag.getModifiedDate());
		ticketFlagImpl.setAccountEntryId(ticketFlag.getAccountEntryId());
		ticketFlagImpl.setTicketEntryId(ticketFlag.getTicketEntryId());
		ticketFlagImpl.setType(ticketFlag.getType());
		ticketFlagImpl.setFlag(ticketFlag.getFlag());

		return ticketFlagImpl;
	}

	/**
	 * Returns the ticket flag with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the ticket flag
	 * @return the ticket flag
	 * @throws com.liferay.portal.NoSuchModelException if a ticket flag with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TicketFlag findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the ticket flag with the primary key or throws a {@link com.liferay.osb.NoSuchTicketFlagException} if it could not be found.
	 *
	 * @param ticketFlagId the primary key of the ticket flag
	 * @return the ticket flag
	 * @throws com.liferay.osb.NoSuchTicketFlagException if a ticket flag with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketFlag findByPrimaryKey(long ticketFlagId)
		throws NoSuchTicketFlagException, SystemException {
		TicketFlag ticketFlag = fetchByPrimaryKey(ticketFlagId);

		if (ticketFlag == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + ticketFlagId);
			}

			throw new NoSuchTicketFlagException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				ticketFlagId);
		}

		return ticketFlag;
	}

	/**
	 * Returns the ticket flag with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ticket flag
	 * @return the ticket flag, or <code>null</code> if a ticket flag with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TicketFlag fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the ticket flag with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param ticketFlagId the primary key of the ticket flag
	 * @return the ticket flag, or <code>null</code> if a ticket flag with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketFlag fetchByPrimaryKey(long ticketFlagId)
		throws SystemException {
		TicketFlag ticketFlag = (TicketFlag)EntityCacheUtil.getResult(TicketFlagModelImpl.ENTITY_CACHE_ENABLED,
				TicketFlagImpl.class, ticketFlagId);

		if (ticketFlag == _nullTicketFlag) {
			return null;
		}

		if (ticketFlag == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				ticketFlag = (TicketFlag)session.get(TicketFlagImpl.class,
						Long.valueOf(ticketFlagId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (ticketFlag != null) {
					cacheResult(ticketFlag);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(TicketFlagModelImpl.ENTITY_CACHE_ENABLED,
						TicketFlagImpl.class, ticketFlagId, _nullTicketFlag);
				}

				closeSession(session);
			}
		}

		return ticketFlag;
	}

	/**
	 * Returns all the ticket flags where ticketEntryId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @return the matching ticket flags
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketFlag> findByTicketEntryId(long ticketEntryId)
		throws SystemException {
		return findByTicketEntryId(ticketEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket flags where ticketEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param start the lower bound of the range of ticket flags
	 * @param end the upper bound of the range of ticket flags (not inclusive)
	 * @return the range of matching ticket flags
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketFlag> findByTicketEntryId(long ticketEntryId, int start,
		int end) throws SystemException {
		return findByTicketEntryId(ticketEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket flags where ticketEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param start the lower bound of the range of ticket flags
	 * @param end the upper bound of the range of ticket flags (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ticket flags
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketFlag> findByTicketEntryId(long ticketEntryId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TICKETENTRYID;
			finderArgs = new Object[] { ticketEntryId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TICKETENTRYID;
			finderArgs = new Object[] {
					ticketEntryId,
					
					start, end, orderByComparator
				};
		}

		List<TicketFlag> list = (List<TicketFlag>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (TicketFlag ticketFlag : list) {
				if ((ticketEntryId != ticketFlag.getTicketEntryId())) {
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

			query.append(_SQL_SELECT_TICKETFLAG_WHERE);

			query.append(_FINDER_COLUMN_TICKETENTRYID_TICKETENTRYID_2);

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

				qPos.add(ticketEntryId);

				list = (List<TicketFlag>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the first ticket flag in the ordered set where ticketEntryId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket flag
	 * @throws com.liferay.osb.NoSuchTicketFlagException if a matching ticket flag could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketFlag findByTicketEntryId_First(long ticketEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchTicketFlagException, SystemException {
		TicketFlag ticketFlag = fetchByTicketEntryId_First(ticketEntryId,
				orderByComparator);

		if (ticketFlag != null) {
			return ticketFlag;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ticketEntryId=");
		msg.append(ticketEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketFlagException(msg.toString());
	}

	/**
	 * Returns the first ticket flag in the ordered set where ticketEntryId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket flag, or <code>null</code> if a matching ticket flag could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketFlag fetchByTicketEntryId_First(long ticketEntryId,
		OrderByComparator orderByComparator) throws SystemException {
		List<TicketFlag> list = findByTicketEntryId(ticketEntryId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ticket flag in the ordered set where ticketEntryId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket flag
	 * @throws com.liferay.osb.NoSuchTicketFlagException if a matching ticket flag could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketFlag findByTicketEntryId_Last(long ticketEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchTicketFlagException, SystemException {
		TicketFlag ticketFlag = fetchByTicketEntryId_Last(ticketEntryId,
				orderByComparator);

		if (ticketFlag != null) {
			return ticketFlag;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ticketEntryId=");
		msg.append(ticketEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketFlagException(msg.toString());
	}

	/**
	 * Returns the last ticket flag in the ordered set where ticketEntryId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket flag, or <code>null</code> if a matching ticket flag could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketFlag fetchByTicketEntryId_Last(long ticketEntryId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByTicketEntryId(ticketEntryId);

		List<TicketFlag> list = findByTicketEntryId(ticketEntryId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ticket flags before and after the current ticket flag in the ordered set where ticketEntryId = &#63;.
	 *
	 * @param ticketFlagId the primary key of the current ticket flag
	 * @param ticketEntryId the ticket entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ticket flag
	 * @throws com.liferay.osb.NoSuchTicketFlagException if a ticket flag with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketFlag[] findByTicketEntryId_PrevAndNext(long ticketFlagId,
		long ticketEntryId, OrderByComparator orderByComparator)
		throws NoSuchTicketFlagException, SystemException {
		TicketFlag ticketFlag = findByPrimaryKey(ticketFlagId);

		Session session = null;

		try {
			session = openSession();

			TicketFlag[] array = new TicketFlagImpl[3];

			array[0] = getByTicketEntryId_PrevAndNext(session, ticketFlag,
					ticketEntryId, orderByComparator, true);

			array[1] = ticketFlag;

			array[2] = getByTicketEntryId_PrevAndNext(session, ticketFlag,
					ticketEntryId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TicketFlag getByTicketEntryId_PrevAndNext(Session session,
		TicketFlag ticketFlag, long ticketEntryId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TICKETFLAG_WHERE);

		query.append(_FINDER_COLUMN_TICKETENTRYID_TICKETENTRYID_2);

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

		qPos.add(ticketEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ticketFlag);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TicketFlag> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the ticket flags where accountEntryId = &#63; and type = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param type the type
	 * @return the matching ticket flags
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketFlag> findByAEI_T(long accountEntryId, int type)
		throws SystemException {
		return findByAEI_T(accountEntryId, type, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket flags where accountEntryId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param type the type
	 * @param start the lower bound of the range of ticket flags
	 * @param end the upper bound of the range of ticket flags (not inclusive)
	 * @return the range of matching ticket flags
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketFlag> findByAEI_T(long accountEntryId, int type,
		int start, int end) throws SystemException {
		return findByAEI_T(accountEntryId, type, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket flags where accountEntryId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param type the type
	 * @param start the lower bound of the range of ticket flags
	 * @param end the upper bound of the range of ticket flags (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ticket flags
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketFlag> findByAEI_T(long accountEntryId, int type,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_T;
			finderArgs = new Object[] { accountEntryId, type };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_AEI_T;
			finderArgs = new Object[] {
					accountEntryId, type,
					
					start, end, orderByComparator
				};
		}

		List<TicketFlag> list = (List<TicketFlag>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (TicketFlag ticketFlag : list) {
				if ((accountEntryId != ticketFlag.getAccountEntryId()) ||
						(type != ticketFlag.getType())) {
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

			query.append(_SQL_SELECT_TICKETFLAG_WHERE);

			query.append(_FINDER_COLUMN_AEI_T_ACCOUNTENTRYID_2);

			query.append(_FINDER_COLUMN_AEI_T_TYPE_2);

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

				qPos.add(type);

				list = (List<TicketFlag>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the first ticket flag in the ordered set where accountEntryId = &#63; and type = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket flag
	 * @throws com.liferay.osb.NoSuchTicketFlagException if a matching ticket flag could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketFlag findByAEI_T_First(long accountEntryId, int type,
		OrderByComparator orderByComparator)
		throws NoSuchTicketFlagException, SystemException {
		TicketFlag ticketFlag = fetchByAEI_T_First(accountEntryId, type,
				orderByComparator);

		if (ticketFlag != null) {
			return ticketFlag;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(", type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketFlagException(msg.toString());
	}

	/**
	 * Returns the first ticket flag in the ordered set where accountEntryId = &#63; and type = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket flag, or <code>null</code> if a matching ticket flag could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketFlag fetchByAEI_T_First(long accountEntryId, int type,
		OrderByComparator orderByComparator) throws SystemException {
		List<TicketFlag> list = findByAEI_T(accountEntryId, type, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ticket flag in the ordered set where accountEntryId = &#63; and type = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket flag
	 * @throws com.liferay.osb.NoSuchTicketFlagException if a matching ticket flag could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketFlag findByAEI_T_Last(long accountEntryId, int type,
		OrderByComparator orderByComparator)
		throws NoSuchTicketFlagException, SystemException {
		TicketFlag ticketFlag = fetchByAEI_T_Last(accountEntryId, type,
				orderByComparator);

		if (ticketFlag != null) {
			return ticketFlag;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(", type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketFlagException(msg.toString());
	}

	/**
	 * Returns the last ticket flag in the ordered set where accountEntryId = &#63; and type = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket flag, or <code>null</code> if a matching ticket flag could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketFlag fetchByAEI_T_Last(long accountEntryId, int type,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByAEI_T(accountEntryId, type);

		List<TicketFlag> list = findByAEI_T(accountEntryId, type, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ticket flags before and after the current ticket flag in the ordered set where accountEntryId = &#63; and type = &#63;.
	 *
	 * @param ticketFlagId the primary key of the current ticket flag
	 * @param accountEntryId the account entry ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ticket flag
	 * @throws com.liferay.osb.NoSuchTicketFlagException if a ticket flag with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketFlag[] findByAEI_T_PrevAndNext(long ticketFlagId,
		long accountEntryId, int type, OrderByComparator orderByComparator)
		throws NoSuchTicketFlagException, SystemException {
		TicketFlag ticketFlag = findByPrimaryKey(ticketFlagId);

		Session session = null;

		try {
			session = openSession();

			TicketFlag[] array = new TicketFlagImpl[3];

			array[0] = getByAEI_T_PrevAndNext(session, ticketFlag,
					accountEntryId, type, orderByComparator, true);

			array[1] = ticketFlag;

			array[2] = getByAEI_T_PrevAndNext(session, ticketFlag,
					accountEntryId, type, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TicketFlag getByAEI_T_PrevAndNext(Session session,
		TicketFlag ticketFlag, long accountEntryId, int type,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TICKETFLAG_WHERE);

		query.append(_FINDER_COLUMN_AEI_T_ACCOUNTENTRYID_2);

		query.append(_FINDER_COLUMN_AEI_T_TYPE_2);

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

		qPos.add(type);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ticketFlag);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TicketFlag> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the ticket flags where ticketEntryId = &#63; and type = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @return the matching ticket flags
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketFlag> findByTEI_T(long ticketEntryId, int type)
		throws SystemException {
		return findByTEI_T(ticketEntryId, type, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket flags where ticketEntryId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param start the lower bound of the range of ticket flags
	 * @param end the upper bound of the range of ticket flags (not inclusive)
	 * @return the range of matching ticket flags
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketFlag> findByTEI_T(long ticketEntryId, int type,
		int start, int end) throws SystemException {
		return findByTEI_T(ticketEntryId, type, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket flags where ticketEntryId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param start the lower bound of the range of ticket flags
	 * @param end the upper bound of the range of ticket flags (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ticket flags
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketFlag> findByTEI_T(long ticketEntryId, int type,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_T;
			finderArgs = new Object[] { ticketEntryId, type };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TEI_T;
			finderArgs = new Object[] {
					ticketEntryId, type,
					
					start, end, orderByComparator
				};
		}

		List<TicketFlag> list = (List<TicketFlag>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (TicketFlag ticketFlag : list) {
				if ((ticketEntryId != ticketFlag.getTicketEntryId()) ||
						(type != ticketFlag.getType())) {
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

			query.append(_SQL_SELECT_TICKETFLAG_WHERE);

			query.append(_FINDER_COLUMN_TEI_T_TICKETENTRYID_2);

			query.append(_FINDER_COLUMN_TEI_T_TYPE_2);

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

				qPos.add(ticketEntryId);

				qPos.add(type);

				list = (List<TicketFlag>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the first ticket flag in the ordered set where ticketEntryId = &#63; and type = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket flag
	 * @throws com.liferay.osb.NoSuchTicketFlagException if a matching ticket flag could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketFlag findByTEI_T_First(long ticketEntryId, int type,
		OrderByComparator orderByComparator)
		throws NoSuchTicketFlagException, SystemException {
		TicketFlag ticketFlag = fetchByTEI_T_First(ticketEntryId, type,
				orderByComparator);

		if (ticketFlag != null) {
			return ticketFlag;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ticketEntryId=");
		msg.append(ticketEntryId);

		msg.append(", type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketFlagException(msg.toString());
	}

	/**
	 * Returns the first ticket flag in the ordered set where ticketEntryId = &#63; and type = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket flag, or <code>null</code> if a matching ticket flag could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketFlag fetchByTEI_T_First(long ticketEntryId, int type,
		OrderByComparator orderByComparator) throws SystemException {
		List<TicketFlag> list = findByTEI_T(ticketEntryId, type, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ticket flag in the ordered set where ticketEntryId = &#63; and type = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket flag
	 * @throws com.liferay.osb.NoSuchTicketFlagException if a matching ticket flag could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketFlag findByTEI_T_Last(long ticketEntryId, int type,
		OrderByComparator orderByComparator)
		throws NoSuchTicketFlagException, SystemException {
		TicketFlag ticketFlag = fetchByTEI_T_Last(ticketEntryId, type,
				orderByComparator);

		if (ticketFlag != null) {
			return ticketFlag;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ticketEntryId=");
		msg.append(ticketEntryId);

		msg.append(", type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketFlagException(msg.toString());
	}

	/**
	 * Returns the last ticket flag in the ordered set where ticketEntryId = &#63; and type = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket flag, or <code>null</code> if a matching ticket flag could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketFlag fetchByTEI_T_Last(long ticketEntryId, int type,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByTEI_T(ticketEntryId, type);

		List<TicketFlag> list = findByTEI_T(ticketEntryId, type, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ticket flags before and after the current ticket flag in the ordered set where ticketEntryId = &#63; and type = &#63;.
	 *
	 * @param ticketFlagId the primary key of the current ticket flag
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ticket flag
	 * @throws com.liferay.osb.NoSuchTicketFlagException if a ticket flag with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketFlag[] findByTEI_T_PrevAndNext(long ticketFlagId,
		long ticketEntryId, int type, OrderByComparator orderByComparator)
		throws NoSuchTicketFlagException, SystemException {
		TicketFlag ticketFlag = findByPrimaryKey(ticketFlagId);

		Session session = null;

		try {
			session = openSession();

			TicketFlag[] array = new TicketFlagImpl[3];

			array[0] = getByTEI_T_PrevAndNext(session, ticketFlag,
					ticketEntryId, type, orderByComparator, true);

			array[1] = ticketFlag;

			array[2] = getByTEI_T_PrevAndNext(session, ticketFlag,
					ticketEntryId, type, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TicketFlag getByTEI_T_PrevAndNext(Session session,
		TicketFlag ticketFlag, long ticketEntryId, int type,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TICKETFLAG_WHERE);

		query.append(_FINDER_COLUMN_TEI_T_TICKETENTRYID_2);

		query.append(_FINDER_COLUMN_TEI_T_TYPE_2);

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

		qPos.add(ticketEntryId);

		qPos.add(type);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ticketFlag);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TicketFlag> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the ticket flags where ticketEntryId = &#63; and type = &#63; and flag = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param flag the flag
	 * @return the matching ticket flags
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketFlag> findByTEI_T_F(long ticketEntryId, int type, int flag)
		throws SystemException {
		return findByTEI_T_F(ticketEntryId, type, flag, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket flags where ticketEntryId = &#63; and type = &#63; and flag = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param flag the flag
	 * @param start the lower bound of the range of ticket flags
	 * @param end the upper bound of the range of ticket flags (not inclusive)
	 * @return the range of matching ticket flags
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketFlag> findByTEI_T_F(long ticketEntryId, int type,
		int flag, int start, int end) throws SystemException {
		return findByTEI_T_F(ticketEntryId, type, flag, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket flags where ticketEntryId = &#63; and type = &#63; and flag = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param flag the flag
	 * @param start the lower bound of the range of ticket flags
	 * @param end the upper bound of the range of ticket flags (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ticket flags
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketFlag> findByTEI_T_F(long ticketEntryId, int type,
		int flag, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_T_F;
			finderArgs = new Object[] { ticketEntryId, type, flag };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TEI_T_F;
			finderArgs = new Object[] {
					ticketEntryId, type, flag,
					
					start, end, orderByComparator
				};
		}

		List<TicketFlag> list = (List<TicketFlag>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (TicketFlag ticketFlag : list) {
				if ((ticketEntryId != ticketFlag.getTicketEntryId()) ||
						(type != ticketFlag.getType()) ||
						(flag != ticketFlag.getFlag())) {
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
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_TICKETFLAG_WHERE);

			query.append(_FINDER_COLUMN_TEI_T_F_TICKETENTRYID_2);

			query.append(_FINDER_COLUMN_TEI_T_F_TYPE_2);

			query.append(_FINDER_COLUMN_TEI_T_F_FLAG_2);

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

				qPos.add(ticketEntryId);

				qPos.add(type);

				qPos.add(flag);

				list = (List<TicketFlag>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the first ticket flag in the ordered set where ticketEntryId = &#63; and type = &#63; and flag = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param flag the flag
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket flag
	 * @throws com.liferay.osb.NoSuchTicketFlagException if a matching ticket flag could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketFlag findByTEI_T_F_First(long ticketEntryId, int type,
		int flag, OrderByComparator orderByComparator)
		throws NoSuchTicketFlagException, SystemException {
		TicketFlag ticketFlag = fetchByTEI_T_F_First(ticketEntryId, type, flag,
				orderByComparator);

		if (ticketFlag != null) {
			return ticketFlag;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ticketEntryId=");
		msg.append(ticketEntryId);

		msg.append(", type=");
		msg.append(type);

		msg.append(", flag=");
		msg.append(flag);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketFlagException(msg.toString());
	}

	/**
	 * Returns the first ticket flag in the ordered set where ticketEntryId = &#63; and type = &#63; and flag = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param flag the flag
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket flag, or <code>null</code> if a matching ticket flag could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketFlag fetchByTEI_T_F_First(long ticketEntryId, int type,
		int flag, OrderByComparator orderByComparator)
		throws SystemException {
		List<TicketFlag> list = findByTEI_T_F(ticketEntryId, type, flag, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ticket flag in the ordered set where ticketEntryId = &#63; and type = &#63; and flag = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param flag the flag
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket flag
	 * @throws com.liferay.osb.NoSuchTicketFlagException if a matching ticket flag could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketFlag findByTEI_T_F_Last(long ticketEntryId, int type,
		int flag, OrderByComparator orderByComparator)
		throws NoSuchTicketFlagException, SystemException {
		TicketFlag ticketFlag = fetchByTEI_T_F_Last(ticketEntryId, type, flag,
				orderByComparator);

		if (ticketFlag != null) {
			return ticketFlag;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ticketEntryId=");
		msg.append(ticketEntryId);

		msg.append(", type=");
		msg.append(type);

		msg.append(", flag=");
		msg.append(flag);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketFlagException(msg.toString());
	}

	/**
	 * Returns the last ticket flag in the ordered set where ticketEntryId = &#63; and type = &#63; and flag = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param flag the flag
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket flag, or <code>null</code> if a matching ticket flag could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketFlag fetchByTEI_T_F_Last(long ticketEntryId, int type,
		int flag, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByTEI_T_F(ticketEntryId, type, flag);

		List<TicketFlag> list = findByTEI_T_F(ticketEntryId, type, flag,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ticket flags before and after the current ticket flag in the ordered set where ticketEntryId = &#63; and type = &#63; and flag = &#63;.
	 *
	 * @param ticketFlagId the primary key of the current ticket flag
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param flag the flag
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ticket flag
	 * @throws com.liferay.osb.NoSuchTicketFlagException if a ticket flag with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketFlag[] findByTEI_T_F_PrevAndNext(long ticketFlagId,
		long ticketEntryId, int type, int flag,
		OrderByComparator orderByComparator)
		throws NoSuchTicketFlagException, SystemException {
		TicketFlag ticketFlag = findByPrimaryKey(ticketFlagId);

		Session session = null;

		try {
			session = openSession();

			TicketFlag[] array = new TicketFlagImpl[3];

			array[0] = getByTEI_T_F_PrevAndNext(session, ticketFlag,
					ticketEntryId, type, flag, orderByComparator, true);

			array[1] = ticketFlag;

			array[2] = getByTEI_T_F_PrevAndNext(session, ticketFlag,
					ticketEntryId, type, flag, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TicketFlag getByTEI_T_F_PrevAndNext(Session session,
		TicketFlag ticketFlag, long ticketEntryId, int type, int flag,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TICKETFLAG_WHERE);

		query.append(_FINDER_COLUMN_TEI_T_F_TICKETENTRYID_2);

		query.append(_FINDER_COLUMN_TEI_T_F_TYPE_2);

		query.append(_FINDER_COLUMN_TEI_T_F_FLAG_2);

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

		qPos.add(ticketEntryId);

		qPos.add(type);

		qPos.add(flag);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ticketFlag);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TicketFlag> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the ticket flags where ticketEntryId = &#63; and type = any &#63; and flag = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param types the types
	 * @param flag the flag
	 * @return the matching ticket flags
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketFlag> findByTEI_T_F(long ticketEntryId, int[] types,
		int flag) throws SystemException {
		return findByTEI_T_F(ticketEntryId, types, flag, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket flags where ticketEntryId = &#63; and type = any &#63; and flag = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param types the types
	 * @param flag the flag
	 * @param start the lower bound of the range of ticket flags
	 * @param end the upper bound of the range of ticket flags (not inclusive)
	 * @return the range of matching ticket flags
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketFlag> findByTEI_T_F(long ticketEntryId, int[] types,
		int flag, int start, int end) throws SystemException {
		return findByTEI_T_F(ticketEntryId, types, flag, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket flags where ticketEntryId = &#63; and type = any &#63; and flag = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param types the types
	 * @param flag the flag
	 * @param start the lower bound of the range of ticket flags
	 * @param end the upper bound of the range of ticket flags (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ticket flags
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketFlag> findByTEI_T_F(long ticketEntryId, int[] types,
		int flag, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TEI_T_F;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderArgs = new Object[] {
					ticketEntryId, StringUtil.merge(types), flag
				};
		}
		else {
			finderArgs = new Object[] {
					ticketEntryId, StringUtil.merge(types), flag,
					
					start, end, orderByComparator
				};
		}

		List<TicketFlag> list = (List<TicketFlag>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (TicketFlag ticketFlag : list) {
				if ((ticketEntryId != ticketFlag.getTicketEntryId()) ||
						!ArrayUtil.contains(types, ticketFlag.getType()) ||
						(flag != ticketFlag.getFlag())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_TICKETFLAG_WHERE);

			boolean conjunctionable = false;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_TEI_T_F_TICKETENTRYID_5);

			conjunctionable = true;

			if ((types == null) || (types.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < types.length; i++) {
					query.append(_FINDER_COLUMN_TEI_T_F_TYPE_5);

					if ((i + 1) < types.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_TEI_T_F_FLAG_5);

			conjunctionable = true;

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

				qPos.add(ticketEntryId);

				if (types != null) {
					qPos.add(types);
				}

				qPos.add(flag);

				list = (List<TicketFlag>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the ticket flag where userId = &#63; and accountEntryId = &#63; and ticketEntryId = &#63; and type = &#63; or throws a {@link com.liferay.osb.NoSuchTicketFlagException} if it could not be found.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @return the matching ticket flag
	 * @throws com.liferay.osb.NoSuchTicketFlagException if a matching ticket flag could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketFlag findByU_AEI_TEI_T(long userId, long accountEntryId,
		long ticketEntryId, int type)
		throws NoSuchTicketFlagException, SystemException {
		TicketFlag ticketFlag = fetchByU_AEI_TEI_T(userId, accountEntryId,
				ticketEntryId, type);

		if (ticketFlag == null) {
			StringBundler msg = new StringBundler(10);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("userId=");
			msg.append(userId);

			msg.append(", accountEntryId=");
			msg.append(accountEntryId);

			msg.append(", ticketEntryId=");
			msg.append(ticketEntryId);

			msg.append(", type=");
			msg.append(type);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchTicketFlagException(msg.toString());
		}

		return ticketFlag;
	}

	/**
	 * Returns the ticket flag where userId = &#63; and accountEntryId = &#63; and ticketEntryId = &#63; and type = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @return the matching ticket flag, or <code>null</code> if a matching ticket flag could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketFlag fetchByU_AEI_TEI_T(long userId, long accountEntryId,
		long ticketEntryId, int type) throws SystemException {
		return fetchByU_AEI_TEI_T(userId, accountEntryId, ticketEntryId, type,
			true);
	}

	/**
	 * Returns the ticket flag where userId = &#63; and accountEntryId = &#63; and ticketEntryId = &#63; and type = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching ticket flag, or <code>null</code> if a matching ticket flag could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketFlag fetchByU_AEI_TEI_T(long userId, long accountEntryId,
		long ticketEntryId, int type, boolean retrieveFromCache)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				userId, accountEntryId, ticketEntryId, type
			};

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_U_AEI_TEI_T,
					finderArgs, this);
		}

		if (result instanceof TicketFlag) {
			TicketFlag ticketFlag = (TicketFlag)result;

			if ((userId != ticketFlag.getUserId()) ||
					(accountEntryId != ticketFlag.getAccountEntryId()) ||
					(ticketEntryId != ticketFlag.getTicketEntryId()) ||
					(type != ticketFlag.getType())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_TICKETFLAG_WHERE);

			query.append(_FINDER_COLUMN_U_AEI_TEI_T_USERID_2);

			query.append(_FINDER_COLUMN_U_AEI_TEI_T_ACCOUNTENTRYID_2);

			query.append(_FINDER_COLUMN_U_AEI_TEI_T_TICKETENTRYID_2);

			query.append(_FINDER_COLUMN_U_AEI_TEI_T_TYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(accountEntryId);

				qPos.add(ticketEntryId);

				qPos.add(type);

				List<TicketFlag> list = q.list();

				result = list;

				TicketFlag ticketFlag = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_AEI_TEI_T,
						finderArgs, list);
				}
				else {
					ticketFlag = list.get(0);

					cacheResult(ticketFlag);

					if ((ticketFlag.getUserId() != userId) ||
							(ticketFlag.getAccountEntryId() != accountEntryId) ||
							(ticketFlag.getTicketEntryId() != ticketEntryId) ||
							(ticketFlag.getType() != type)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_AEI_TEI_T,
							finderArgs, ticketFlag);
					}
				}

				return ticketFlag;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_U_AEI_TEI_T,
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
				return (TicketFlag)result;
			}
		}
	}

	/**
	 * Returns all the ticket flags.
	 *
	 * @return the ticket flags
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketFlag> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket flags.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of ticket flags
	 * @param end the upper bound of the range of ticket flags (not inclusive)
	 * @return the range of ticket flags
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketFlag> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket flags.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of ticket flags
	 * @param end the upper bound of the range of ticket flags (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ticket flags
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketFlag> findAll(int start, int end,
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

		List<TicketFlag> list = (List<TicketFlag>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_TICKETFLAG);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_TICKETFLAG;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<TicketFlag>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<TicketFlag>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the ticket flags where ticketEntryId = &#63; from the database.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByTicketEntryId(long ticketEntryId)
		throws SystemException {
		for (TicketFlag ticketFlag : findByTicketEntryId(ticketEntryId)) {
			remove(ticketFlag);
		}
	}

	/**
	 * Removes all the ticket flags where accountEntryId = &#63; and type = &#63; from the database.
	 *
	 * @param accountEntryId the account entry ID
	 * @param type the type
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByAEI_T(long accountEntryId, int type)
		throws SystemException {
		for (TicketFlag ticketFlag : findByAEI_T(accountEntryId, type)) {
			remove(ticketFlag);
		}
	}

	/**
	 * Removes all the ticket flags where ticketEntryId = &#63; and type = &#63; from the database.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByTEI_T(long ticketEntryId, int type)
		throws SystemException {
		for (TicketFlag ticketFlag : findByTEI_T(ticketEntryId, type)) {
			remove(ticketFlag);
		}
	}

	/**
	 * Removes all the ticket flags where ticketEntryId = &#63; and type = &#63; and flag = &#63; from the database.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param flag the flag
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByTEI_T_F(long ticketEntryId, int type, int flag)
		throws SystemException {
		for (TicketFlag ticketFlag : findByTEI_T_F(ticketEntryId, type, flag)) {
			remove(ticketFlag);
		}
	}

	/**
	 * Removes the ticket flag where userId = &#63; and accountEntryId = &#63; and ticketEntryId = &#63; and type = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @return the ticket flag that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public TicketFlag removeByU_AEI_TEI_T(long userId, long accountEntryId,
		long ticketEntryId, int type)
		throws NoSuchTicketFlagException, SystemException {
		TicketFlag ticketFlag = findByU_AEI_TEI_T(userId, accountEntryId,
				ticketEntryId, type);

		return remove(ticketFlag);
	}

	/**
	 * Removes all the ticket flags from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (TicketFlag ticketFlag : findAll()) {
			remove(ticketFlag);
		}
	}

	/**
	 * Returns the number of ticket flags where ticketEntryId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @return the number of matching ticket flags
	 * @throws SystemException if a system exception occurred
	 */
	public int countByTicketEntryId(long ticketEntryId)
		throws SystemException {
		Object[] finderArgs = new Object[] { ticketEntryId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_TICKETENTRYID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TICKETFLAG_WHERE);

			query.append(_FINDER_COLUMN_TICKETENTRYID_TICKETENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ticketEntryId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_TICKETENTRYID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of ticket flags where accountEntryId = &#63; and type = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param type the type
	 * @return the number of matching ticket flags
	 * @throws SystemException if a system exception occurred
	 */
	public int countByAEI_T(long accountEntryId, int type)
		throws SystemException {
		Object[] finderArgs = new Object[] { accountEntryId, type };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_AEI_T,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TICKETFLAG_WHERE);

			query.append(_FINDER_COLUMN_AEI_T_ACCOUNTENTRYID_2);

			query.append(_FINDER_COLUMN_AEI_T_TYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEntryId);

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
	 * Returns the number of ticket flags where ticketEntryId = &#63; and type = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @return the number of matching ticket flags
	 * @throws SystemException if a system exception occurred
	 */
	public int countByTEI_T(long ticketEntryId, int type)
		throws SystemException {
		Object[] finderArgs = new Object[] { ticketEntryId, type };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_TEI_T,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TICKETFLAG_WHERE);

			query.append(_FINDER_COLUMN_TEI_T_TICKETENTRYID_2);

			query.append(_FINDER_COLUMN_TEI_T_TYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ticketEntryId);

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_TEI_T,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of ticket flags where ticketEntryId = &#63; and type = &#63; and flag = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param flag the flag
	 * @return the number of matching ticket flags
	 * @throws SystemException if a system exception occurred
	 */
	public int countByTEI_T_F(long ticketEntryId, int type, int flag)
		throws SystemException {
		Object[] finderArgs = new Object[] { ticketEntryId, type, flag };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_TEI_T_F,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_TICKETFLAG_WHERE);

			query.append(_FINDER_COLUMN_TEI_T_F_TICKETENTRYID_2);

			query.append(_FINDER_COLUMN_TEI_T_F_TYPE_2);

			query.append(_FINDER_COLUMN_TEI_T_F_FLAG_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ticketEntryId);

				qPos.add(type);

				qPos.add(flag);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_TEI_T_F,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of ticket flags where ticketEntryId = &#63; and type = any &#63; and flag = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param types the types
	 * @param flag the flag
	 * @return the number of matching ticket flags
	 * @throws SystemException if a system exception occurred
	 */
	public int countByTEI_T_F(long ticketEntryId, int[] types, int flag)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				ticketEntryId, StringUtil.merge(types), flag
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_TEI_T_F,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_TICKETFLAG_WHERE);

			boolean conjunctionable = false;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_TEI_T_F_TICKETENTRYID_5);

			conjunctionable = true;

			if ((types == null) || (types.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < types.length; i++) {
					query.append(_FINDER_COLUMN_TEI_T_F_TYPE_5);

					if ((i + 1) < types.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_TEI_T_F_FLAG_5);

			conjunctionable = true;

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ticketEntryId);

				if (types != null) {
					qPos.add(types);
				}

				qPos.add(flag);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_TEI_T_F,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of ticket flags where userId = &#63; and accountEntryId = &#63; and ticketEntryId = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @return the number of matching ticket flags
	 * @throws SystemException if a system exception occurred
	 */
	public int countByU_AEI_TEI_T(long userId, long accountEntryId,
		long ticketEntryId, int type) throws SystemException {
		Object[] finderArgs = new Object[] {
				userId, accountEntryId, ticketEntryId, type
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_U_AEI_TEI_T,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_TICKETFLAG_WHERE);

			query.append(_FINDER_COLUMN_U_AEI_TEI_T_USERID_2);

			query.append(_FINDER_COLUMN_U_AEI_TEI_T_ACCOUNTENTRYID_2);

			query.append(_FINDER_COLUMN_U_AEI_TEI_T_TICKETENTRYID_2);

			query.append(_FINDER_COLUMN_U_AEI_TEI_T_TYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(accountEntryId);

				qPos.add(ticketEntryId);

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_U_AEI_TEI_T,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of ticket flags.
	 *
	 * @return the number of ticket flags
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_TICKETFLAG);

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
	 * Initializes the ticket flag persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.osb.model.TicketFlag")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<TicketFlag>> listenersList = new ArrayList<ModelListener<TicketFlag>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<TicketFlag>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(TicketFlagImpl.class.getName());
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
	private static final String _SQL_SELECT_TICKETFLAG = "SELECT ticketFlag FROM TicketFlag ticketFlag";
	private static final String _SQL_SELECT_TICKETFLAG_WHERE = "SELECT ticketFlag FROM TicketFlag ticketFlag WHERE ";
	private static final String _SQL_COUNT_TICKETFLAG = "SELECT COUNT(ticketFlag) FROM TicketFlag ticketFlag";
	private static final String _SQL_COUNT_TICKETFLAG_WHERE = "SELECT COUNT(ticketFlag) FROM TicketFlag ticketFlag WHERE ";
	private static final String _FINDER_COLUMN_TICKETENTRYID_TICKETENTRYID_2 = "ticketFlag.ticketEntryId = ?";
	private static final String _FINDER_COLUMN_AEI_T_ACCOUNTENTRYID_2 = "ticketFlag.accountEntryId = ? AND ";
	private static final String _FINDER_COLUMN_AEI_T_TYPE_2 = "ticketFlag.type = ?";
	private static final String _FINDER_COLUMN_TEI_T_TICKETENTRYID_2 = "ticketFlag.ticketEntryId = ? AND ";
	private static final String _FINDER_COLUMN_TEI_T_TYPE_2 = "ticketFlag.type = ?";
	private static final String _FINDER_COLUMN_TEI_T_F_TICKETENTRYID_2 = "ticketFlag.ticketEntryId = ? AND ";
	private static final String _FINDER_COLUMN_TEI_T_F_TICKETENTRYID_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_TEI_T_F_TICKETENTRYID_2) + ")";
	private static final String _FINDER_COLUMN_TEI_T_F_TYPE_2 = "ticketFlag.type = ? AND ";
	private static final String _FINDER_COLUMN_TEI_T_F_TYPE_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_TEI_T_F_TYPE_2) + ")";
	private static final String _FINDER_COLUMN_TEI_T_F_FLAG_2 = "ticketFlag.flag = ?";
	private static final String _FINDER_COLUMN_TEI_T_F_FLAG_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_TEI_T_F_FLAG_2) + ")";
	private static final String _FINDER_COLUMN_U_AEI_TEI_T_USERID_2 = "ticketFlag.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_AEI_TEI_T_ACCOUNTENTRYID_2 = "ticketFlag.accountEntryId = ? AND ";
	private static final String _FINDER_COLUMN_U_AEI_TEI_T_TICKETENTRYID_2 = "ticketFlag.ticketEntryId = ? AND ";
	private static final String _FINDER_COLUMN_U_AEI_TEI_T_TYPE_2 = "ticketFlag.type = ?";

	private static String _removeConjunction(String sql) {
		int pos = sql.indexOf(" AND ");

		if (pos != -1) {
			sql = sql.substring(0, pos);
		}

		return sql;
	}

	private static final String _ORDER_BY_ENTITY_ALIAS = "ticketFlag.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No TicketFlag exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No TicketFlag exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(TicketFlagPersistenceImpl.class);
	private static TicketFlag _nullTicketFlag = new TicketFlagImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<TicketFlag> toCacheModel() {
				return _nullTicketFlagCacheModel;
			}
		};

	private static CacheModel<TicketFlag> _nullTicketFlagCacheModel = new CacheModel<TicketFlag>() {
			public TicketFlag toEntityModel() {
				return _nullTicketFlag;
			}
		};
}