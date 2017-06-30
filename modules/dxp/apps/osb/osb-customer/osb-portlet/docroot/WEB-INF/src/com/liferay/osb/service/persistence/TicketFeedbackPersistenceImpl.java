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

import com.liferay.osb.NoSuchTicketFeedbackException;
import com.liferay.osb.model.TicketFeedback;
import com.liferay.osb.model.impl.TicketFeedbackImpl;
import com.liferay.osb.model.impl.TicketFeedbackModelImpl;

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
import com.liferay.portal.service.persistence.OrganizationPersistence;
import com.liferay.portal.service.persistence.PortletPreferencesPersistence;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the ticket feedback service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TicketFeedbackPersistence
 * @see TicketFeedbackUtil
 * @generated
 */
public class TicketFeedbackPersistenceImpl extends BasePersistenceImpl<TicketFeedback>
	implements TicketFeedbackPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link TicketFeedbackUtil} to access the ticket feedback persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = TicketFeedbackImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TICKETENTRYID =
		new FinderPath(TicketFeedbackModelImpl.ENTITY_CACHE_ENABLED,
			TicketFeedbackModelImpl.FINDER_CACHE_ENABLED,
			TicketFeedbackImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByTicketEntryId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TICKETENTRYID =
		new FinderPath(TicketFeedbackModelImpl.ENTITY_CACHE_ENABLED,
			TicketFeedbackModelImpl.FINDER_CACHE_ENABLED,
			TicketFeedbackImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByTicketEntryId",
			new String[] { Long.class.getName() },
			TicketFeedbackModelImpl.TICKETENTRYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TICKETENTRYID = new FinderPath(TicketFeedbackModelImpl.ENTITY_CACHE_ENABLED,
			TicketFeedbackModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByTicketEntryId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TEI_S = new FinderPath(TicketFeedbackModelImpl.ENTITY_CACHE_ENABLED,
			TicketFeedbackModelImpl.FINDER_CACHE_ENABLED,
			TicketFeedbackImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByTEI_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_S = new FinderPath(TicketFeedbackModelImpl.ENTITY_CACHE_ENABLED,
			TicketFeedbackModelImpl.FINDER_CACHE_ENABLED,
			TicketFeedbackImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByTEI_S",
			new String[] { Long.class.getName(), Integer.class.getName() },
			TicketFeedbackModelImpl.TICKETENTRYID_COLUMN_BITMASK |
			TicketFeedbackModelImpl.SUBJECT_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TEI_S = new FinderPath(TicketFeedbackModelImpl.ENTITY_CACHE_ENABLED,
			TicketFeedbackModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByTEI_S",
			new String[] { Long.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TEI_S_NOTS =
		new FinderPath(TicketFeedbackModelImpl.ENTITY_CACHE_ENABLED,
			TicketFeedbackModelImpl.FINDER_CACHE_ENABLED,
			TicketFeedbackImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByTEI_S_NotS",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_TEI_S_NOTS =
		new FinderPath(TicketFeedbackModelImpl.ENTITY_CACHE_ENABLED,
			TicketFeedbackModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByTEI_S_NotS",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TEI_S_S = new FinderPath(TicketFeedbackModelImpl.ENTITY_CACHE_ENABLED,
			TicketFeedbackModelImpl.FINDER_CACHE_ENABLED,
			TicketFeedbackImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByTEI_S_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_S_S =
		new FinderPath(TicketFeedbackModelImpl.ENTITY_CACHE_ENABLED,
			TicketFeedbackModelImpl.FINDER_CACHE_ENABLED,
			TicketFeedbackImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByTEI_S_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName()
			},
			TicketFeedbackModelImpl.TICKETENTRYID_COLUMN_BITMASK |
			TicketFeedbackModelImpl.SUBJECT_COLUMN_BITMASK |
			TicketFeedbackModelImpl.STATUS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TEI_S_S = new FinderPath(TicketFeedbackModelImpl.ENTITY_CACHE_ENABLED,
			TicketFeedbackModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByTEI_S_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_U_TEI_S_NOTS =
		new FinderPath(TicketFeedbackModelImpl.ENTITY_CACHE_ENABLED,
			TicketFeedbackModelImpl.FINDER_CACHE_ENABLED,
			TicketFeedbackImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByU_TEI_S_NotS",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_U_TEI_S_NOTS =
		new FinderPath(TicketFeedbackModelImpl.ENTITY_CACHE_ENABLED,
			TicketFeedbackModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByU_TEI_S_NotS",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(TicketFeedbackModelImpl.ENTITY_CACHE_ENABLED,
			TicketFeedbackModelImpl.FINDER_CACHE_ENABLED,
			TicketFeedbackImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(TicketFeedbackModelImpl.ENTITY_CACHE_ENABLED,
			TicketFeedbackModelImpl.FINDER_CACHE_ENABLED,
			TicketFeedbackImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(TicketFeedbackModelImpl.ENTITY_CACHE_ENABLED,
			TicketFeedbackModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the ticket feedback in the entity cache if it is enabled.
	 *
	 * @param ticketFeedback the ticket feedback
	 */
	public void cacheResult(TicketFeedback ticketFeedback) {
		EntityCacheUtil.putResult(TicketFeedbackModelImpl.ENTITY_CACHE_ENABLED,
			TicketFeedbackImpl.class, ticketFeedback.getPrimaryKey(),
			ticketFeedback);

		ticketFeedback.resetOriginalValues();
	}

	/**
	 * Caches the ticket feedbacks in the entity cache if it is enabled.
	 *
	 * @param ticketFeedbacks the ticket feedbacks
	 */
	public void cacheResult(List<TicketFeedback> ticketFeedbacks) {
		for (TicketFeedback ticketFeedback : ticketFeedbacks) {
			if (EntityCacheUtil.getResult(
						TicketFeedbackModelImpl.ENTITY_CACHE_ENABLED,
						TicketFeedbackImpl.class, ticketFeedback.getPrimaryKey()) == null) {
				cacheResult(ticketFeedback);
			}
			else {
				ticketFeedback.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all ticket feedbacks.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(TicketFeedbackImpl.class.getName());
		}

		EntityCacheUtil.clearCache(TicketFeedbackImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the ticket feedback.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(TicketFeedback ticketFeedback) {
		EntityCacheUtil.removeResult(TicketFeedbackModelImpl.ENTITY_CACHE_ENABLED,
			TicketFeedbackImpl.class, ticketFeedback.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<TicketFeedback> ticketFeedbacks) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (TicketFeedback ticketFeedback : ticketFeedbacks) {
			EntityCacheUtil.removeResult(TicketFeedbackModelImpl.ENTITY_CACHE_ENABLED,
				TicketFeedbackImpl.class, ticketFeedback.getPrimaryKey());
		}
	}

	/**
	 * Creates a new ticket feedback with the primary key. Does not add the ticket feedback to the database.
	 *
	 * @param ticketFeedbackId the primary key for the new ticket feedback
	 * @return the new ticket feedback
	 */
	public TicketFeedback create(long ticketFeedbackId) {
		TicketFeedback ticketFeedback = new TicketFeedbackImpl();

		ticketFeedback.setNew(true);
		ticketFeedback.setPrimaryKey(ticketFeedbackId);

		return ticketFeedback;
	}

	/**
	 * Removes the ticket feedback with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ticketFeedbackId the primary key of the ticket feedback
	 * @return the ticket feedback that was removed
	 * @throws com.liferay.osb.NoSuchTicketFeedbackException if a ticket feedback with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketFeedback remove(long ticketFeedbackId)
		throws NoSuchTicketFeedbackException, SystemException {
		return remove(Long.valueOf(ticketFeedbackId));
	}

	/**
	 * Removes the ticket feedback with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ticket feedback
	 * @return the ticket feedback that was removed
	 * @throws com.liferay.osb.NoSuchTicketFeedbackException if a ticket feedback with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TicketFeedback remove(Serializable primaryKey)
		throws NoSuchTicketFeedbackException, SystemException {
		Session session = null;

		try {
			session = openSession();

			TicketFeedback ticketFeedback = (TicketFeedback)session.get(TicketFeedbackImpl.class,
					primaryKey);

			if (ticketFeedback == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTicketFeedbackException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(ticketFeedback);
		}
		catch (NoSuchTicketFeedbackException nsee) {
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
	protected TicketFeedback removeImpl(TicketFeedback ticketFeedback)
		throws SystemException {
		ticketFeedback = toUnwrappedModel(ticketFeedback);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, ticketFeedback);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(ticketFeedback);

		return ticketFeedback;
	}

	@Override
	public TicketFeedback updateImpl(
		com.liferay.osb.model.TicketFeedback ticketFeedback, boolean merge)
		throws SystemException {
		ticketFeedback = toUnwrappedModel(ticketFeedback);

		boolean isNew = ticketFeedback.isNew();

		TicketFeedbackModelImpl ticketFeedbackModelImpl = (TicketFeedbackModelImpl)ticketFeedback;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, ticketFeedback, merge);

			ticketFeedback.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !TicketFeedbackModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((ticketFeedbackModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TICKETENTRYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(ticketFeedbackModelImpl.getOriginalTicketEntryId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TICKETENTRYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TICKETENTRYID,
					args);

				args = new Object[] {
						Long.valueOf(ticketFeedbackModelImpl.getTicketEntryId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TICKETENTRYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TICKETENTRYID,
					args);
			}

			if ((ticketFeedbackModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(ticketFeedbackModelImpl.getOriginalTicketEntryId()),
						Integer.valueOf(ticketFeedbackModelImpl.getOriginalSubject())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TEI_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_S,
					args);

				args = new Object[] {
						Long.valueOf(ticketFeedbackModelImpl.getTicketEntryId()),
						Integer.valueOf(ticketFeedbackModelImpl.getSubject())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TEI_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_S,
					args);
			}

			if ((ticketFeedbackModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_S_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(ticketFeedbackModelImpl.getOriginalTicketEntryId()),
						Integer.valueOf(ticketFeedbackModelImpl.getOriginalSubject()),
						Integer.valueOf(ticketFeedbackModelImpl.getOriginalStatus())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TEI_S_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_S_S,
					args);

				args = new Object[] {
						Long.valueOf(ticketFeedbackModelImpl.getTicketEntryId()),
						Integer.valueOf(ticketFeedbackModelImpl.getSubject()),
						Integer.valueOf(ticketFeedbackModelImpl.getStatus())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TEI_S_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_S_S,
					args);
			}
		}

		EntityCacheUtil.putResult(TicketFeedbackModelImpl.ENTITY_CACHE_ENABLED,
			TicketFeedbackImpl.class, ticketFeedback.getPrimaryKey(),
			ticketFeedback);

		return ticketFeedback;
	}

	protected TicketFeedback toUnwrappedModel(TicketFeedback ticketFeedback) {
		if (ticketFeedback instanceof TicketFeedbackImpl) {
			return ticketFeedback;
		}

		TicketFeedbackImpl ticketFeedbackImpl = new TicketFeedbackImpl();

		ticketFeedbackImpl.setNew(ticketFeedback.isNew());
		ticketFeedbackImpl.setPrimaryKey(ticketFeedback.getPrimaryKey());

		ticketFeedbackImpl.setTicketFeedbackId(ticketFeedback.getTicketFeedbackId());
		ticketFeedbackImpl.setUserId(ticketFeedback.getUserId());
		ticketFeedbackImpl.setUserName(ticketFeedback.getUserName());
		ticketFeedbackImpl.setCreateDate(ticketFeedback.getCreateDate());
		ticketFeedbackImpl.setModifiedDate(ticketFeedback.getModifiedDate());
		ticketFeedbackImpl.setAccountEntryId(ticketFeedback.getAccountEntryId());
		ticketFeedbackImpl.setTicketEntryId(ticketFeedback.getTicketEntryId());
		ticketFeedbackImpl.setSubject(ticketFeedback.getSubject());
		ticketFeedbackImpl.setSatisfied(ticketFeedback.getSatisfied());
		ticketFeedbackImpl.setAnswer1(ticketFeedback.getAnswer1());
		ticketFeedbackImpl.setAnswer2(ticketFeedback.getAnswer2());
		ticketFeedbackImpl.setAnswer3(ticketFeedback.getAnswer3());
		ticketFeedbackImpl.setRating1(ticketFeedback.getRating1());
		ticketFeedbackImpl.setRating2(ticketFeedback.getRating2());
		ticketFeedbackImpl.setRating3(ticketFeedback.getRating3());
		ticketFeedbackImpl.setRating4(ticketFeedback.getRating4());
		ticketFeedbackImpl.setComments(ticketFeedback.getComments());
		ticketFeedbackImpl.setStatus(ticketFeedback.getStatus());

		return ticketFeedbackImpl;
	}

	/**
	 * Returns the ticket feedback with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the ticket feedback
	 * @return the ticket feedback
	 * @throws com.liferay.portal.NoSuchModelException if a ticket feedback with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TicketFeedback findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the ticket feedback with the primary key or throws a {@link com.liferay.osb.NoSuchTicketFeedbackException} if it could not be found.
	 *
	 * @param ticketFeedbackId the primary key of the ticket feedback
	 * @return the ticket feedback
	 * @throws com.liferay.osb.NoSuchTicketFeedbackException if a ticket feedback with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketFeedback findByPrimaryKey(long ticketFeedbackId)
		throws NoSuchTicketFeedbackException, SystemException {
		TicketFeedback ticketFeedback = fetchByPrimaryKey(ticketFeedbackId);

		if (ticketFeedback == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + ticketFeedbackId);
			}

			throw new NoSuchTicketFeedbackException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				ticketFeedbackId);
		}

		return ticketFeedback;
	}

	/**
	 * Returns the ticket feedback with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ticket feedback
	 * @return the ticket feedback, or <code>null</code> if a ticket feedback with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TicketFeedback fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the ticket feedback with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param ticketFeedbackId the primary key of the ticket feedback
	 * @return the ticket feedback, or <code>null</code> if a ticket feedback with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketFeedback fetchByPrimaryKey(long ticketFeedbackId)
		throws SystemException {
		TicketFeedback ticketFeedback = (TicketFeedback)EntityCacheUtil.getResult(TicketFeedbackModelImpl.ENTITY_CACHE_ENABLED,
				TicketFeedbackImpl.class, ticketFeedbackId);

		if (ticketFeedback == _nullTicketFeedback) {
			return null;
		}

		if (ticketFeedback == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				ticketFeedback = (TicketFeedback)session.get(TicketFeedbackImpl.class,
						Long.valueOf(ticketFeedbackId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (ticketFeedback != null) {
					cacheResult(ticketFeedback);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(TicketFeedbackModelImpl.ENTITY_CACHE_ENABLED,
						TicketFeedbackImpl.class, ticketFeedbackId,
						_nullTicketFeedback);
				}

				closeSession(session);
			}
		}

		return ticketFeedback;
	}

	/**
	 * Returns all the ticket feedbacks where ticketEntryId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @return the matching ticket feedbacks
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketFeedback> findByTicketEntryId(long ticketEntryId)
		throws SystemException {
		return findByTicketEntryId(ticketEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket feedbacks where ticketEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param start the lower bound of the range of ticket feedbacks
	 * @param end the upper bound of the range of ticket feedbacks (not inclusive)
	 * @return the range of matching ticket feedbacks
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketFeedback> findByTicketEntryId(long ticketEntryId,
		int start, int end) throws SystemException {
		return findByTicketEntryId(ticketEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket feedbacks where ticketEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param start the lower bound of the range of ticket feedbacks
	 * @param end the upper bound of the range of ticket feedbacks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ticket feedbacks
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketFeedback> findByTicketEntryId(long ticketEntryId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
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

		List<TicketFeedback> list = (List<TicketFeedback>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (TicketFeedback ticketFeedback : list) {
				if ((ticketEntryId != ticketFeedback.getTicketEntryId())) {
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

			query.append(_SQL_SELECT_TICKETFEEDBACK_WHERE);

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

				list = (List<TicketFeedback>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first ticket feedback in the ordered set where ticketEntryId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket feedback
	 * @throws com.liferay.osb.NoSuchTicketFeedbackException if a matching ticket feedback could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketFeedback findByTicketEntryId_First(long ticketEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchTicketFeedbackException, SystemException {
		TicketFeedback ticketFeedback = fetchByTicketEntryId_First(ticketEntryId,
				orderByComparator);

		if (ticketFeedback != null) {
			return ticketFeedback;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ticketEntryId=");
		msg.append(ticketEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketFeedbackException(msg.toString());
	}

	/**
	 * Returns the first ticket feedback in the ordered set where ticketEntryId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket feedback, or <code>null</code> if a matching ticket feedback could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketFeedback fetchByTicketEntryId_First(long ticketEntryId,
		OrderByComparator orderByComparator) throws SystemException {
		List<TicketFeedback> list = findByTicketEntryId(ticketEntryId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ticket feedback in the ordered set where ticketEntryId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket feedback
	 * @throws com.liferay.osb.NoSuchTicketFeedbackException if a matching ticket feedback could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketFeedback findByTicketEntryId_Last(long ticketEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchTicketFeedbackException, SystemException {
		TicketFeedback ticketFeedback = fetchByTicketEntryId_Last(ticketEntryId,
				orderByComparator);

		if (ticketFeedback != null) {
			return ticketFeedback;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ticketEntryId=");
		msg.append(ticketEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketFeedbackException(msg.toString());
	}

	/**
	 * Returns the last ticket feedback in the ordered set where ticketEntryId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket feedback, or <code>null</code> if a matching ticket feedback could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketFeedback fetchByTicketEntryId_Last(long ticketEntryId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByTicketEntryId(ticketEntryId);

		List<TicketFeedback> list = findByTicketEntryId(ticketEntryId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ticket feedbacks before and after the current ticket feedback in the ordered set where ticketEntryId = &#63;.
	 *
	 * @param ticketFeedbackId the primary key of the current ticket feedback
	 * @param ticketEntryId the ticket entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ticket feedback
	 * @throws com.liferay.osb.NoSuchTicketFeedbackException if a ticket feedback with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketFeedback[] findByTicketEntryId_PrevAndNext(
		long ticketFeedbackId, long ticketEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchTicketFeedbackException, SystemException {
		TicketFeedback ticketFeedback = findByPrimaryKey(ticketFeedbackId);

		Session session = null;

		try {
			session = openSession();

			TicketFeedback[] array = new TicketFeedbackImpl[3];

			array[0] = getByTicketEntryId_PrevAndNext(session, ticketFeedback,
					ticketEntryId, orderByComparator, true);

			array[1] = ticketFeedback;

			array[2] = getByTicketEntryId_PrevAndNext(session, ticketFeedback,
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

	protected TicketFeedback getByTicketEntryId_PrevAndNext(Session session,
		TicketFeedback ticketFeedback, long ticketEntryId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TICKETFEEDBACK_WHERE);

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
			Object[] values = orderByComparator.getOrderByConditionValues(ticketFeedback);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TicketFeedback> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the ticket feedbacks where ticketEntryId = &#63; and subject = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param subject the subject
	 * @return the matching ticket feedbacks
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketFeedback> findByTEI_S(long ticketEntryId, int subject)
		throws SystemException {
		return findByTEI_S(ticketEntryId, subject, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket feedbacks where ticketEntryId = &#63; and subject = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param subject the subject
	 * @param start the lower bound of the range of ticket feedbacks
	 * @param end the upper bound of the range of ticket feedbacks (not inclusive)
	 * @return the range of matching ticket feedbacks
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketFeedback> findByTEI_S(long ticketEntryId, int subject,
		int start, int end) throws SystemException {
		return findByTEI_S(ticketEntryId, subject, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket feedbacks where ticketEntryId = &#63; and subject = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param subject the subject
	 * @param start the lower bound of the range of ticket feedbacks
	 * @param end the upper bound of the range of ticket feedbacks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ticket feedbacks
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketFeedback> findByTEI_S(long ticketEntryId, int subject,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_S;
			finderArgs = new Object[] { ticketEntryId, subject };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TEI_S;
			finderArgs = new Object[] {
					ticketEntryId, subject,
					
					start, end, orderByComparator
				};
		}

		List<TicketFeedback> list = (List<TicketFeedback>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (TicketFeedback ticketFeedback : list) {
				if ((ticketEntryId != ticketFeedback.getTicketEntryId()) ||
						(subject != ticketFeedback.getSubject())) {
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

			query.append(_SQL_SELECT_TICKETFEEDBACK_WHERE);

			query.append(_FINDER_COLUMN_TEI_S_TICKETENTRYID_2);

			query.append(_FINDER_COLUMN_TEI_S_SUBJECT_2);

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

				qPos.add(subject);

				list = (List<TicketFeedback>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first ticket feedback in the ordered set where ticketEntryId = &#63; and subject = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param subject the subject
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket feedback
	 * @throws com.liferay.osb.NoSuchTicketFeedbackException if a matching ticket feedback could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketFeedback findByTEI_S_First(long ticketEntryId, int subject,
		OrderByComparator orderByComparator)
		throws NoSuchTicketFeedbackException, SystemException {
		TicketFeedback ticketFeedback = fetchByTEI_S_First(ticketEntryId,
				subject, orderByComparator);

		if (ticketFeedback != null) {
			return ticketFeedback;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ticketEntryId=");
		msg.append(ticketEntryId);

		msg.append(", subject=");
		msg.append(subject);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketFeedbackException(msg.toString());
	}

	/**
	 * Returns the first ticket feedback in the ordered set where ticketEntryId = &#63; and subject = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param subject the subject
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket feedback, or <code>null</code> if a matching ticket feedback could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketFeedback fetchByTEI_S_First(long ticketEntryId, int subject,
		OrderByComparator orderByComparator) throws SystemException {
		List<TicketFeedback> list = findByTEI_S(ticketEntryId, subject, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ticket feedback in the ordered set where ticketEntryId = &#63; and subject = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param subject the subject
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket feedback
	 * @throws com.liferay.osb.NoSuchTicketFeedbackException if a matching ticket feedback could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketFeedback findByTEI_S_Last(long ticketEntryId, int subject,
		OrderByComparator orderByComparator)
		throws NoSuchTicketFeedbackException, SystemException {
		TicketFeedback ticketFeedback = fetchByTEI_S_Last(ticketEntryId,
				subject, orderByComparator);

		if (ticketFeedback != null) {
			return ticketFeedback;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ticketEntryId=");
		msg.append(ticketEntryId);

		msg.append(", subject=");
		msg.append(subject);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketFeedbackException(msg.toString());
	}

	/**
	 * Returns the last ticket feedback in the ordered set where ticketEntryId = &#63; and subject = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param subject the subject
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket feedback, or <code>null</code> if a matching ticket feedback could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketFeedback fetchByTEI_S_Last(long ticketEntryId, int subject,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByTEI_S(ticketEntryId, subject);

		List<TicketFeedback> list = findByTEI_S(ticketEntryId, subject,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ticket feedbacks before and after the current ticket feedback in the ordered set where ticketEntryId = &#63; and subject = &#63;.
	 *
	 * @param ticketFeedbackId the primary key of the current ticket feedback
	 * @param ticketEntryId the ticket entry ID
	 * @param subject the subject
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ticket feedback
	 * @throws com.liferay.osb.NoSuchTicketFeedbackException if a ticket feedback with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketFeedback[] findByTEI_S_PrevAndNext(long ticketFeedbackId,
		long ticketEntryId, int subject, OrderByComparator orderByComparator)
		throws NoSuchTicketFeedbackException, SystemException {
		TicketFeedback ticketFeedback = findByPrimaryKey(ticketFeedbackId);

		Session session = null;

		try {
			session = openSession();

			TicketFeedback[] array = new TicketFeedbackImpl[3];

			array[0] = getByTEI_S_PrevAndNext(session, ticketFeedback,
					ticketEntryId, subject, orderByComparator, true);

			array[1] = ticketFeedback;

			array[2] = getByTEI_S_PrevAndNext(session, ticketFeedback,
					ticketEntryId, subject, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TicketFeedback getByTEI_S_PrevAndNext(Session session,
		TicketFeedback ticketFeedback, long ticketEntryId, int subject,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TICKETFEEDBACK_WHERE);

		query.append(_FINDER_COLUMN_TEI_S_TICKETENTRYID_2);

		query.append(_FINDER_COLUMN_TEI_S_SUBJECT_2);

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

		qPos.add(subject);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ticketFeedback);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TicketFeedback> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the ticket feedbacks where ticketEntryId = &#63; and subject = &#63; and status &ne; &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param subject the subject
	 * @param status the status
	 * @return the matching ticket feedbacks
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketFeedback> findByTEI_S_NotS(long ticketEntryId,
		int subject, int status) throws SystemException {
		return findByTEI_S_NotS(ticketEntryId, subject, status,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket feedbacks where ticketEntryId = &#63; and subject = &#63; and status &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param subject the subject
	 * @param status the status
	 * @param start the lower bound of the range of ticket feedbacks
	 * @param end the upper bound of the range of ticket feedbacks (not inclusive)
	 * @return the range of matching ticket feedbacks
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketFeedback> findByTEI_S_NotS(long ticketEntryId,
		int subject, int status, int start, int end) throws SystemException {
		return findByTEI_S_NotS(ticketEntryId, subject, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket feedbacks where ticketEntryId = &#63; and subject = &#63; and status &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param subject the subject
	 * @param status the status
	 * @param start the lower bound of the range of ticket feedbacks
	 * @param end the upper bound of the range of ticket feedbacks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ticket feedbacks
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketFeedback> findByTEI_S_NotS(long ticketEntryId,
		int subject, int status, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TEI_S_NOTS;
		finderArgs = new Object[] {
				ticketEntryId, subject, status,
				
				start, end, orderByComparator
			};

		List<TicketFeedback> list = (List<TicketFeedback>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (TicketFeedback ticketFeedback : list) {
				if ((ticketEntryId != ticketFeedback.getTicketEntryId()) ||
						(subject != ticketFeedback.getSubject()) ||
						(status != ticketFeedback.getStatus())) {
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

			query.append(_SQL_SELECT_TICKETFEEDBACK_WHERE);

			query.append(_FINDER_COLUMN_TEI_S_NOTS_TICKETENTRYID_2);

			query.append(_FINDER_COLUMN_TEI_S_NOTS_SUBJECT_2);

			query.append(_FINDER_COLUMN_TEI_S_NOTS_STATUS_2);

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

				qPos.add(subject);

				qPos.add(status);

				list = (List<TicketFeedback>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first ticket feedback in the ordered set where ticketEntryId = &#63; and subject = &#63; and status &ne; &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param subject the subject
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket feedback
	 * @throws com.liferay.osb.NoSuchTicketFeedbackException if a matching ticket feedback could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketFeedback findByTEI_S_NotS_First(long ticketEntryId,
		int subject, int status, OrderByComparator orderByComparator)
		throws NoSuchTicketFeedbackException, SystemException {
		TicketFeedback ticketFeedback = fetchByTEI_S_NotS_First(ticketEntryId,
				subject, status, orderByComparator);

		if (ticketFeedback != null) {
			return ticketFeedback;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ticketEntryId=");
		msg.append(ticketEntryId);

		msg.append(", subject=");
		msg.append(subject);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketFeedbackException(msg.toString());
	}

	/**
	 * Returns the first ticket feedback in the ordered set where ticketEntryId = &#63; and subject = &#63; and status &ne; &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param subject the subject
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket feedback, or <code>null</code> if a matching ticket feedback could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketFeedback fetchByTEI_S_NotS_First(long ticketEntryId,
		int subject, int status, OrderByComparator orderByComparator)
		throws SystemException {
		List<TicketFeedback> list = findByTEI_S_NotS(ticketEntryId, subject,
				status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ticket feedback in the ordered set where ticketEntryId = &#63; and subject = &#63; and status &ne; &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param subject the subject
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket feedback
	 * @throws com.liferay.osb.NoSuchTicketFeedbackException if a matching ticket feedback could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketFeedback findByTEI_S_NotS_Last(long ticketEntryId,
		int subject, int status, OrderByComparator orderByComparator)
		throws NoSuchTicketFeedbackException, SystemException {
		TicketFeedback ticketFeedback = fetchByTEI_S_NotS_Last(ticketEntryId,
				subject, status, orderByComparator);

		if (ticketFeedback != null) {
			return ticketFeedback;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ticketEntryId=");
		msg.append(ticketEntryId);

		msg.append(", subject=");
		msg.append(subject);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketFeedbackException(msg.toString());
	}

	/**
	 * Returns the last ticket feedback in the ordered set where ticketEntryId = &#63; and subject = &#63; and status &ne; &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param subject the subject
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket feedback, or <code>null</code> if a matching ticket feedback could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketFeedback fetchByTEI_S_NotS_Last(long ticketEntryId,
		int subject, int status, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByTEI_S_NotS(ticketEntryId, subject, status);

		List<TicketFeedback> list = findByTEI_S_NotS(ticketEntryId, subject,
				status, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ticket feedbacks before and after the current ticket feedback in the ordered set where ticketEntryId = &#63; and subject = &#63; and status &ne; &#63;.
	 *
	 * @param ticketFeedbackId the primary key of the current ticket feedback
	 * @param ticketEntryId the ticket entry ID
	 * @param subject the subject
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ticket feedback
	 * @throws com.liferay.osb.NoSuchTicketFeedbackException if a ticket feedback with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketFeedback[] findByTEI_S_NotS_PrevAndNext(
		long ticketFeedbackId, long ticketEntryId, int subject, int status,
		OrderByComparator orderByComparator)
		throws NoSuchTicketFeedbackException, SystemException {
		TicketFeedback ticketFeedback = findByPrimaryKey(ticketFeedbackId);

		Session session = null;

		try {
			session = openSession();

			TicketFeedback[] array = new TicketFeedbackImpl[3];

			array[0] = getByTEI_S_NotS_PrevAndNext(session, ticketFeedback,
					ticketEntryId, subject, status, orderByComparator, true);

			array[1] = ticketFeedback;

			array[2] = getByTEI_S_NotS_PrevAndNext(session, ticketFeedback,
					ticketEntryId, subject, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TicketFeedback getByTEI_S_NotS_PrevAndNext(Session session,
		TicketFeedback ticketFeedback, long ticketEntryId, int subject,
		int status, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TICKETFEEDBACK_WHERE);

		query.append(_FINDER_COLUMN_TEI_S_NOTS_TICKETENTRYID_2);

		query.append(_FINDER_COLUMN_TEI_S_NOTS_SUBJECT_2);

		query.append(_FINDER_COLUMN_TEI_S_NOTS_STATUS_2);

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

		qPos.add(subject);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ticketFeedback);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TicketFeedback> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the ticket feedbacks where ticketEntryId = &#63; and subject = &#63; and status = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param subject the subject
	 * @param status the status
	 * @return the matching ticket feedbacks
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketFeedback> findByTEI_S_S(long ticketEntryId, int subject,
		int status) throws SystemException {
		return findByTEI_S_S(ticketEntryId, subject, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket feedbacks where ticketEntryId = &#63; and subject = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param subject the subject
	 * @param status the status
	 * @param start the lower bound of the range of ticket feedbacks
	 * @param end the upper bound of the range of ticket feedbacks (not inclusive)
	 * @return the range of matching ticket feedbacks
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketFeedback> findByTEI_S_S(long ticketEntryId, int subject,
		int status, int start, int end) throws SystemException {
		return findByTEI_S_S(ticketEntryId, subject, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket feedbacks where ticketEntryId = &#63; and subject = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param subject the subject
	 * @param status the status
	 * @param start the lower bound of the range of ticket feedbacks
	 * @param end the upper bound of the range of ticket feedbacks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ticket feedbacks
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketFeedback> findByTEI_S_S(long ticketEntryId, int subject,
		int status, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_S_S;
			finderArgs = new Object[] { ticketEntryId, subject, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TEI_S_S;
			finderArgs = new Object[] {
					ticketEntryId, subject, status,
					
					start, end, orderByComparator
				};
		}

		List<TicketFeedback> list = (List<TicketFeedback>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (TicketFeedback ticketFeedback : list) {
				if ((ticketEntryId != ticketFeedback.getTicketEntryId()) ||
						(subject != ticketFeedback.getSubject()) ||
						(status != ticketFeedback.getStatus())) {
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

			query.append(_SQL_SELECT_TICKETFEEDBACK_WHERE);

			query.append(_FINDER_COLUMN_TEI_S_S_TICKETENTRYID_2);

			query.append(_FINDER_COLUMN_TEI_S_S_SUBJECT_2);

			query.append(_FINDER_COLUMN_TEI_S_S_STATUS_2);

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

				qPos.add(subject);

				qPos.add(status);

				list = (List<TicketFeedback>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first ticket feedback in the ordered set where ticketEntryId = &#63; and subject = &#63; and status = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param subject the subject
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket feedback
	 * @throws com.liferay.osb.NoSuchTicketFeedbackException if a matching ticket feedback could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketFeedback findByTEI_S_S_First(long ticketEntryId, int subject,
		int status, OrderByComparator orderByComparator)
		throws NoSuchTicketFeedbackException, SystemException {
		TicketFeedback ticketFeedback = fetchByTEI_S_S_First(ticketEntryId,
				subject, status, orderByComparator);

		if (ticketFeedback != null) {
			return ticketFeedback;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ticketEntryId=");
		msg.append(ticketEntryId);

		msg.append(", subject=");
		msg.append(subject);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketFeedbackException(msg.toString());
	}

	/**
	 * Returns the first ticket feedback in the ordered set where ticketEntryId = &#63; and subject = &#63; and status = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param subject the subject
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket feedback, or <code>null</code> if a matching ticket feedback could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketFeedback fetchByTEI_S_S_First(long ticketEntryId, int subject,
		int status, OrderByComparator orderByComparator)
		throws SystemException {
		List<TicketFeedback> list = findByTEI_S_S(ticketEntryId, subject,
				status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ticket feedback in the ordered set where ticketEntryId = &#63; and subject = &#63; and status = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param subject the subject
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket feedback
	 * @throws com.liferay.osb.NoSuchTicketFeedbackException if a matching ticket feedback could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketFeedback findByTEI_S_S_Last(long ticketEntryId, int subject,
		int status, OrderByComparator orderByComparator)
		throws NoSuchTicketFeedbackException, SystemException {
		TicketFeedback ticketFeedback = fetchByTEI_S_S_Last(ticketEntryId,
				subject, status, orderByComparator);

		if (ticketFeedback != null) {
			return ticketFeedback;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ticketEntryId=");
		msg.append(ticketEntryId);

		msg.append(", subject=");
		msg.append(subject);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketFeedbackException(msg.toString());
	}

	/**
	 * Returns the last ticket feedback in the ordered set where ticketEntryId = &#63; and subject = &#63; and status = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param subject the subject
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket feedback, or <code>null</code> if a matching ticket feedback could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketFeedback fetchByTEI_S_S_Last(long ticketEntryId, int subject,
		int status, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByTEI_S_S(ticketEntryId, subject, status);

		List<TicketFeedback> list = findByTEI_S_S(ticketEntryId, subject,
				status, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ticket feedbacks before and after the current ticket feedback in the ordered set where ticketEntryId = &#63; and subject = &#63; and status = &#63;.
	 *
	 * @param ticketFeedbackId the primary key of the current ticket feedback
	 * @param ticketEntryId the ticket entry ID
	 * @param subject the subject
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ticket feedback
	 * @throws com.liferay.osb.NoSuchTicketFeedbackException if a ticket feedback with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketFeedback[] findByTEI_S_S_PrevAndNext(long ticketFeedbackId,
		long ticketEntryId, int subject, int status,
		OrderByComparator orderByComparator)
		throws NoSuchTicketFeedbackException, SystemException {
		TicketFeedback ticketFeedback = findByPrimaryKey(ticketFeedbackId);

		Session session = null;

		try {
			session = openSession();

			TicketFeedback[] array = new TicketFeedbackImpl[3];

			array[0] = getByTEI_S_S_PrevAndNext(session, ticketFeedback,
					ticketEntryId, subject, status, orderByComparator, true);

			array[1] = ticketFeedback;

			array[2] = getByTEI_S_S_PrevAndNext(session, ticketFeedback,
					ticketEntryId, subject, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TicketFeedback getByTEI_S_S_PrevAndNext(Session session,
		TicketFeedback ticketFeedback, long ticketEntryId, int subject,
		int status, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TICKETFEEDBACK_WHERE);

		query.append(_FINDER_COLUMN_TEI_S_S_TICKETENTRYID_2);

		query.append(_FINDER_COLUMN_TEI_S_S_SUBJECT_2);

		query.append(_FINDER_COLUMN_TEI_S_S_STATUS_2);

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

		qPos.add(subject);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ticketFeedback);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TicketFeedback> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the ticket feedbacks where userId = &#63; and ticketEntryId = &#63; and subject = &#63; and status &ne; &#63;.
	 *
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @param subject the subject
	 * @param status the status
	 * @return the matching ticket feedbacks
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketFeedback> findByU_TEI_S_NotS(long userId,
		long ticketEntryId, int subject, int status) throws SystemException {
		return findByU_TEI_S_NotS(userId, ticketEntryId, subject, status,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket feedbacks where userId = &#63; and ticketEntryId = &#63; and subject = &#63; and status &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @param subject the subject
	 * @param status the status
	 * @param start the lower bound of the range of ticket feedbacks
	 * @param end the upper bound of the range of ticket feedbacks (not inclusive)
	 * @return the range of matching ticket feedbacks
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketFeedback> findByU_TEI_S_NotS(long userId,
		long ticketEntryId, int subject, int status, int start, int end)
		throws SystemException {
		return findByU_TEI_S_NotS(userId, ticketEntryId, subject, status,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket feedbacks where userId = &#63; and ticketEntryId = &#63; and subject = &#63; and status &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @param subject the subject
	 * @param status the status
	 * @param start the lower bound of the range of ticket feedbacks
	 * @param end the upper bound of the range of ticket feedbacks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ticket feedbacks
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketFeedback> findByU_TEI_S_NotS(long userId,
		long ticketEntryId, int subject, int status, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_U_TEI_S_NOTS;
		finderArgs = new Object[] {
				userId, ticketEntryId, subject, status,
				
				start, end, orderByComparator
			};

		List<TicketFeedback> list = (List<TicketFeedback>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (TicketFeedback ticketFeedback : list) {
				if ((userId != ticketFeedback.getUserId()) ||
						(ticketEntryId != ticketFeedback.getTicketEntryId()) ||
						(subject != ticketFeedback.getSubject()) ||
						(status != ticketFeedback.getStatus())) {
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

			query.append(_SQL_SELECT_TICKETFEEDBACK_WHERE);

			query.append(_FINDER_COLUMN_U_TEI_S_NOTS_USERID_2);

			query.append(_FINDER_COLUMN_U_TEI_S_NOTS_TICKETENTRYID_2);

			query.append(_FINDER_COLUMN_U_TEI_S_NOTS_SUBJECT_2);

			query.append(_FINDER_COLUMN_U_TEI_S_NOTS_STATUS_2);

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

				qPos.add(ticketEntryId);

				qPos.add(subject);

				qPos.add(status);

				list = (List<TicketFeedback>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first ticket feedback in the ordered set where userId = &#63; and ticketEntryId = &#63; and subject = &#63; and status &ne; &#63;.
	 *
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @param subject the subject
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket feedback
	 * @throws com.liferay.osb.NoSuchTicketFeedbackException if a matching ticket feedback could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketFeedback findByU_TEI_S_NotS_First(long userId,
		long ticketEntryId, int subject, int status,
		OrderByComparator orderByComparator)
		throws NoSuchTicketFeedbackException, SystemException {
		TicketFeedback ticketFeedback = fetchByU_TEI_S_NotS_First(userId,
				ticketEntryId, subject, status, orderByComparator);

		if (ticketFeedback != null) {
			return ticketFeedback;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", ticketEntryId=");
		msg.append(ticketEntryId);

		msg.append(", subject=");
		msg.append(subject);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketFeedbackException(msg.toString());
	}

	/**
	 * Returns the first ticket feedback in the ordered set where userId = &#63; and ticketEntryId = &#63; and subject = &#63; and status &ne; &#63;.
	 *
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @param subject the subject
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket feedback, or <code>null</code> if a matching ticket feedback could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketFeedback fetchByU_TEI_S_NotS_First(long userId,
		long ticketEntryId, int subject, int status,
		OrderByComparator orderByComparator) throws SystemException {
		List<TicketFeedback> list = findByU_TEI_S_NotS(userId, ticketEntryId,
				subject, status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ticket feedback in the ordered set where userId = &#63; and ticketEntryId = &#63; and subject = &#63; and status &ne; &#63;.
	 *
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @param subject the subject
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket feedback
	 * @throws com.liferay.osb.NoSuchTicketFeedbackException if a matching ticket feedback could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketFeedback findByU_TEI_S_NotS_Last(long userId,
		long ticketEntryId, int subject, int status,
		OrderByComparator orderByComparator)
		throws NoSuchTicketFeedbackException, SystemException {
		TicketFeedback ticketFeedback = fetchByU_TEI_S_NotS_Last(userId,
				ticketEntryId, subject, status, orderByComparator);

		if (ticketFeedback != null) {
			return ticketFeedback;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", ticketEntryId=");
		msg.append(ticketEntryId);

		msg.append(", subject=");
		msg.append(subject);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketFeedbackException(msg.toString());
	}

	/**
	 * Returns the last ticket feedback in the ordered set where userId = &#63; and ticketEntryId = &#63; and subject = &#63; and status &ne; &#63;.
	 *
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @param subject the subject
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket feedback, or <code>null</code> if a matching ticket feedback could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketFeedback fetchByU_TEI_S_NotS_Last(long userId,
		long ticketEntryId, int subject, int status,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByU_TEI_S_NotS(userId, ticketEntryId, subject, status);

		List<TicketFeedback> list = findByU_TEI_S_NotS(userId, ticketEntryId,
				subject, status, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ticket feedbacks before and after the current ticket feedback in the ordered set where userId = &#63; and ticketEntryId = &#63; and subject = &#63; and status &ne; &#63;.
	 *
	 * @param ticketFeedbackId the primary key of the current ticket feedback
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @param subject the subject
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ticket feedback
	 * @throws com.liferay.osb.NoSuchTicketFeedbackException if a ticket feedback with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketFeedback[] findByU_TEI_S_NotS_PrevAndNext(
		long ticketFeedbackId, long userId, long ticketEntryId, int subject,
		int status, OrderByComparator orderByComparator)
		throws NoSuchTicketFeedbackException, SystemException {
		TicketFeedback ticketFeedback = findByPrimaryKey(ticketFeedbackId);

		Session session = null;

		try {
			session = openSession();

			TicketFeedback[] array = new TicketFeedbackImpl[3];

			array[0] = getByU_TEI_S_NotS_PrevAndNext(session, ticketFeedback,
					userId, ticketEntryId, subject, status, orderByComparator,
					true);

			array[1] = ticketFeedback;

			array[2] = getByU_TEI_S_NotS_PrevAndNext(session, ticketFeedback,
					userId, ticketEntryId, subject, status, orderByComparator,
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

	protected TicketFeedback getByU_TEI_S_NotS_PrevAndNext(Session session,
		TicketFeedback ticketFeedback, long userId, long ticketEntryId,
		int subject, int status, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TICKETFEEDBACK_WHERE);

		query.append(_FINDER_COLUMN_U_TEI_S_NOTS_USERID_2);

		query.append(_FINDER_COLUMN_U_TEI_S_NOTS_TICKETENTRYID_2);

		query.append(_FINDER_COLUMN_U_TEI_S_NOTS_SUBJECT_2);

		query.append(_FINDER_COLUMN_U_TEI_S_NOTS_STATUS_2);

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

		qPos.add(ticketEntryId);

		qPos.add(subject);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ticketFeedback);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TicketFeedback> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the ticket feedbacks.
	 *
	 * @return the ticket feedbacks
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketFeedback> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket feedbacks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of ticket feedbacks
	 * @param end the upper bound of the range of ticket feedbacks (not inclusive)
	 * @return the range of ticket feedbacks
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketFeedback> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket feedbacks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of ticket feedbacks
	 * @param end the upper bound of the range of ticket feedbacks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ticket feedbacks
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketFeedback> findAll(int start, int end,
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

		List<TicketFeedback> list = (List<TicketFeedback>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_TICKETFEEDBACK);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_TICKETFEEDBACK;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<TicketFeedback>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<TicketFeedback>)QueryUtil.list(q,
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
	 * Removes all the ticket feedbacks where ticketEntryId = &#63; from the database.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByTicketEntryId(long ticketEntryId)
		throws SystemException {
		for (TicketFeedback ticketFeedback : findByTicketEntryId(ticketEntryId)) {
			remove(ticketFeedback);
		}
	}

	/**
	 * Removes all the ticket feedbacks where ticketEntryId = &#63; and subject = &#63; from the database.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param subject the subject
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByTEI_S(long ticketEntryId, int subject)
		throws SystemException {
		for (TicketFeedback ticketFeedback : findByTEI_S(ticketEntryId, subject)) {
			remove(ticketFeedback);
		}
	}

	/**
	 * Removes all the ticket feedbacks where ticketEntryId = &#63; and subject = &#63; and status &ne; &#63; from the database.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param subject the subject
	 * @param status the status
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByTEI_S_NotS(long ticketEntryId, int subject, int status)
		throws SystemException {
		for (TicketFeedback ticketFeedback : findByTEI_S_NotS(ticketEntryId,
				subject, status)) {
			remove(ticketFeedback);
		}
	}

	/**
	 * Removes all the ticket feedbacks where ticketEntryId = &#63; and subject = &#63; and status = &#63; from the database.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param subject the subject
	 * @param status the status
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByTEI_S_S(long ticketEntryId, int subject, int status)
		throws SystemException {
		for (TicketFeedback ticketFeedback : findByTEI_S_S(ticketEntryId,
				subject, status)) {
			remove(ticketFeedback);
		}
	}

	/**
	 * Removes all the ticket feedbacks where userId = &#63; and ticketEntryId = &#63; and subject = &#63; and status &ne; &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @param subject the subject
	 * @param status the status
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByU_TEI_S_NotS(long userId, long ticketEntryId,
		int subject, int status) throws SystemException {
		for (TicketFeedback ticketFeedback : findByU_TEI_S_NotS(userId,
				ticketEntryId, subject, status)) {
			remove(ticketFeedback);
		}
	}

	/**
	 * Removes all the ticket feedbacks from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (TicketFeedback ticketFeedback : findAll()) {
			remove(ticketFeedback);
		}
	}

	/**
	 * Returns the number of ticket feedbacks where ticketEntryId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @return the number of matching ticket feedbacks
	 * @throws SystemException if a system exception occurred
	 */
	public int countByTicketEntryId(long ticketEntryId)
		throws SystemException {
		Object[] finderArgs = new Object[] { ticketEntryId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_TICKETENTRYID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TICKETFEEDBACK_WHERE);

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
	 * Returns the number of ticket feedbacks where ticketEntryId = &#63; and subject = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param subject the subject
	 * @return the number of matching ticket feedbacks
	 * @throws SystemException if a system exception occurred
	 */
	public int countByTEI_S(long ticketEntryId, int subject)
		throws SystemException {
		Object[] finderArgs = new Object[] { ticketEntryId, subject };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_TEI_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TICKETFEEDBACK_WHERE);

			query.append(_FINDER_COLUMN_TEI_S_TICKETENTRYID_2);

			query.append(_FINDER_COLUMN_TEI_S_SUBJECT_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ticketEntryId);

				qPos.add(subject);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_TEI_S,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of ticket feedbacks where ticketEntryId = &#63; and subject = &#63; and status &ne; &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param subject the subject
	 * @param status the status
	 * @return the number of matching ticket feedbacks
	 * @throws SystemException if a system exception occurred
	 */
	public int countByTEI_S_NotS(long ticketEntryId, int subject, int status)
		throws SystemException {
		Object[] finderArgs = new Object[] { ticketEntryId, subject, status };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_TEI_S_NOTS,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_TICKETFEEDBACK_WHERE);

			query.append(_FINDER_COLUMN_TEI_S_NOTS_TICKETENTRYID_2);

			query.append(_FINDER_COLUMN_TEI_S_NOTS_SUBJECT_2);

			query.append(_FINDER_COLUMN_TEI_S_NOTS_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ticketEntryId);

				qPos.add(subject);

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

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_TEI_S_NOTS,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of ticket feedbacks where ticketEntryId = &#63; and subject = &#63; and status = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param subject the subject
	 * @param status the status
	 * @return the number of matching ticket feedbacks
	 * @throws SystemException if a system exception occurred
	 */
	public int countByTEI_S_S(long ticketEntryId, int subject, int status)
		throws SystemException {
		Object[] finderArgs = new Object[] { ticketEntryId, subject, status };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_TEI_S_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_TICKETFEEDBACK_WHERE);

			query.append(_FINDER_COLUMN_TEI_S_S_TICKETENTRYID_2);

			query.append(_FINDER_COLUMN_TEI_S_S_SUBJECT_2);

			query.append(_FINDER_COLUMN_TEI_S_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ticketEntryId);

				qPos.add(subject);

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_TEI_S_S,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of ticket feedbacks where userId = &#63; and ticketEntryId = &#63; and subject = &#63; and status &ne; &#63;.
	 *
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @param subject the subject
	 * @param status the status
	 * @return the number of matching ticket feedbacks
	 * @throws SystemException if a system exception occurred
	 */
	public int countByU_TEI_S_NotS(long userId, long ticketEntryId,
		int subject, int status) throws SystemException {
		Object[] finderArgs = new Object[] {
				userId, ticketEntryId, subject, status
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_U_TEI_S_NOTS,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_TICKETFEEDBACK_WHERE);

			query.append(_FINDER_COLUMN_U_TEI_S_NOTS_USERID_2);

			query.append(_FINDER_COLUMN_U_TEI_S_NOTS_TICKETENTRYID_2);

			query.append(_FINDER_COLUMN_U_TEI_S_NOTS_SUBJECT_2);

			query.append(_FINDER_COLUMN_U_TEI_S_NOTS_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(ticketEntryId);

				qPos.add(subject);

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

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_U_TEI_S_NOTS,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of ticket feedbacks.
	 *
	 * @return the number of ticket feedbacks
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_TICKETFEEDBACK);

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
	 * Initializes the ticket feedback persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.osb.model.TicketFeedback")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<TicketFeedback>> listenersList = new ArrayList<ModelListener<TicketFeedback>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<TicketFeedback>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(TicketFeedbackImpl.class.getName());
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
	@BeanReference(type = PortletPreferencesPersistence.class)
	protected PortletPreferencesPersistence portletPreferencesPersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_TICKETFEEDBACK = "SELECT ticketFeedback FROM TicketFeedback ticketFeedback";
	private static final String _SQL_SELECT_TICKETFEEDBACK_WHERE = "SELECT ticketFeedback FROM TicketFeedback ticketFeedback WHERE ";
	private static final String _SQL_COUNT_TICKETFEEDBACK = "SELECT COUNT(ticketFeedback) FROM TicketFeedback ticketFeedback";
	private static final String _SQL_COUNT_TICKETFEEDBACK_WHERE = "SELECT COUNT(ticketFeedback) FROM TicketFeedback ticketFeedback WHERE ";
	private static final String _FINDER_COLUMN_TICKETENTRYID_TICKETENTRYID_2 = "ticketFeedback.ticketEntryId = ?";
	private static final String _FINDER_COLUMN_TEI_S_TICKETENTRYID_2 = "ticketFeedback.ticketEntryId = ? AND ";
	private static final String _FINDER_COLUMN_TEI_S_SUBJECT_2 = "ticketFeedback.subject = ?";
	private static final String _FINDER_COLUMN_TEI_S_NOTS_TICKETENTRYID_2 = "ticketFeedback.ticketEntryId = ? AND ";
	private static final String _FINDER_COLUMN_TEI_S_NOTS_SUBJECT_2 = "ticketFeedback.subject = ? AND ";
	private static final String _FINDER_COLUMN_TEI_S_NOTS_STATUS_2 = "ticketFeedback.status != ?";
	private static final String _FINDER_COLUMN_TEI_S_S_TICKETENTRYID_2 = "ticketFeedback.ticketEntryId = ? AND ";
	private static final String _FINDER_COLUMN_TEI_S_S_SUBJECT_2 = "ticketFeedback.subject = ? AND ";
	private static final String _FINDER_COLUMN_TEI_S_S_STATUS_2 = "ticketFeedback.status = ?";
	private static final String _FINDER_COLUMN_U_TEI_S_NOTS_USERID_2 = "ticketFeedback.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_TEI_S_NOTS_TICKETENTRYID_2 = "ticketFeedback.ticketEntryId = ? AND ";
	private static final String _FINDER_COLUMN_U_TEI_S_NOTS_SUBJECT_2 = "ticketFeedback.subject = ? AND ";
	private static final String _FINDER_COLUMN_U_TEI_S_NOTS_STATUS_2 = "ticketFeedback.status != ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "ticketFeedback.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No TicketFeedback exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No TicketFeedback exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(TicketFeedbackPersistenceImpl.class);
	private static TicketFeedback _nullTicketFeedback = new TicketFeedbackImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<TicketFeedback> toCacheModel() {
				return _nullTicketFeedbackCacheModel;
			}
		};

	private static CacheModel<TicketFeedback> _nullTicketFeedbackCacheModel = new CacheModel<TicketFeedback>() {
			public TicketFeedback toEntityModel() {
				return _nullTicketFeedback;
			}
		};
}