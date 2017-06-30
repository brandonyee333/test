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

import com.liferay.osb.NoSuchCorpProjectMessageException;
import com.liferay.osb.model.CorpProjectMessage;
import com.liferay.osb.model.impl.CorpProjectMessageImpl;
import com.liferay.osb.model.impl.CorpProjectMessageModelImpl;

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
 * The persistence implementation for the corp project message service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CorpProjectMessagePersistence
 * @see CorpProjectMessageUtil
 * @generated
 */
public class CorpProjectMessagePersistenceImpl extends BasePersistenceImpl<CorpProjectMessage>
	implements CorpProjectMessagePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CorpProjectMessageUtil} to access the corp project message persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CorpProjectMessageImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CORPPROJECTID =
		new FinderPath(CorpProjectMessageModelImpl.ENTITY_CACHE_ENABLED,
			CorpProjectMessageModelImpl.FINDER_CACHE_ENABLED,
			CorpProjectMessageImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCorpProjectId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CORPPROJECTID =
		new FinderPath(CorpProjectMessageModelImpl.ENTITY_CACHE_ENABLED,
			CorpProjectMessageModelImpl.FINDER_CACHE_ENABLED,
			CorpProjectMessageImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCorpProjectId",
			new String[] { Long.class.getName() },
			CorpProjectMessageModelImpl.CORPPROJECTID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CORPPROJECTID = new FinderPath(CorpProjectMessageModelImpl.ENTITY_CACHE_ENABLED,
			CorpProjectMessageModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCorpProjectId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TYPE = new FinderPath(CorpProjectMessageModelImpl.ENTITY_CACHE_ENABLED,
			CorpProjectMessageModelImpl.FINDER_CACHE_ENABLED,
			CorpProjectMessageImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByType",
			new String[] {
				Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPE = new FinderPath(CorpProjectMessageModelImpl.ENTITY_CACHE_ENABLED,
			CorpProjectMessageModelImpl.FINDER_CACHE_ENABLED,
			CorpProjectMessageImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByType",
			new String[] { Integer.class.getName() },
			CorpProjectMessageModelImpl.TYPE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TYPE = new FinderPath(CorpProjectMessageModelImpl.ENTITY_CACHE_ENABLED,
			CorpProjectMessageModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByType",
			new String[] { Integer.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_C_T = new FinderPath(CorpProjectMessageModelImpl.ENTITY_CACHE_ENABLED,
			CorpProjectMessageModelImpl.FINDER_CACHE_ENABLED,
			CorpProjectMessageImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByC_T",
			new String[] { Long.class.getName(), Integer.class.getName() },
			CorpProjectMessageModelImpl.CORPPROJECTID_COLUMN_BITMASK |
			CorpProjectMessageModelImpl.TYPE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_T = new FinderPath(CorpProjectMessageModelImpl.ENTITY_CACHE_ENABLED,
			CorpProjectMessageModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_T",
			new String[] { Long.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CorpProjectMessageModelImpl.ENTITY_CACHE_ENABLED,
			CorpProjectMessageModelImpl.FINDER_CACHE_ENABLED,
			CorpProjectMessageImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CorpProjectMessageModelImpl.ENTITY_CACHE_ENABLED,
			CorpProjectMessageModelImpl.FINDER_CACHE_ENABLED,
			CorpProjectMessageImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CorpProjectMessageModelImpl.ENTITY_CACHE_ENABLED,
			CorpProjectMessageModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the corp project message in the entity cache if it is enabled.
	 *
	 * @param corpProjectMessage the corp project message
	 */
	public void cacheResult(CorpProjectMessage corpProjectMessage) {
		EntityCacheUtil.putResult(CorpProjectMessageModelImpl.ENTITY_CACHE_ENABLED,
			CorpProjectMessageImpl.class, corpProjectMessage.getPrimaryKey(),
			corpProjectMessage);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_T,
			new Object[] {
				Long.valueOf(corpProjectMessage.getCorpProjectId()),
				Integer.valueOf(corpProjectMessage.getType())
			}, corpProjectMessage);

		corpProjectMessage.resetOriginalValues();
	}

	/**
	 * Caches the corp project messages in the entity cache if it is enabled.
	 *
	 * @param corpProjectMessages the corp project messages
	 */
	public void cacheResult(List<CorpProjectMessage> corpProjectMessages) {
		for (CorpProjectMessage corpProjectMessage : corpProjectMessages) {
			if (EntityCacheUtil.getResult(
						CorpProjectMessageModelImpl.ENTITY_CACHE_ENABLED,
						CorpProjectMessageImpl.class,
						corpProjectMessage.getPrimaryKey()) == null) {
				cacheResult(corpProjectMessage);
			}
			else {
				corpProjectMessage.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all corp project messages.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(CorpProjectMessageImpl.class.getName());
		}

		EntityCacheUtil.clearCache(CorpProjectMessageImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the corp project message.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CorpProjectMessage corpProjectMessage) {
		EntityCacheUtil.removeResult(CorpProjectMessageModelImpl.ENTITY_CACHE_ENABLED,
			CorpProjectMessageImpl.class, corpProjectMessage.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(corpProjectMessage);
	}

	@Override
	public void clearCache(List<CorpProjectMessage> corpProjectMessages) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CorpProjectMessage corpProjectMessage : corpProjectMessages) {
			EntityCacheUtil.removeResult(CorpProjectMessageModelImpl.ENTITY_CACHE_ENABLED,
				CorpProjectMessageImpl.class, corpProjectMessage.getPrimaryKey());

			clearUniqueFindersCache(corpProjectMessage);
		}
	}

	protected void cacheUniqueFindersCache(
		CorpProjectMessage corpProjectMessage) {
		if (corpProjectMessage.isNew()) {
			Object[] args = new Object[] {
					Long.valueOf(corpProjectMessage.getCorpProjectId()),
					Integer.valueOf(corpProjectMessage.getType())
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_T, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_T, args,
				corpProjectMessage);
		}
		else {
			CorpProjectMessageModelImpl corpProjectMessageModelImpl = (CorpProjectMessageModelImpl)corpProjectMessage;

			if ((corpProjectMessageModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_C_T.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(corpProjectMessage.getCorpProjectId()),
						Integer.valueOf(corpProjectMessage.getType())
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_T, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_T, args,
					corpProjectMessage);
			}
		}
	}

	protected void clearUniqueFindersCache(
		CorpProjectMessage corpProjectMessage) {
		CorpProjectMessageModelImpl corpProjectMessageModelImpl = (CorpProjectMessageModelImpl)corpProjectMessage;

		Object[] args = new Object[] {
				Long.valueOf(corpProjectMessage.getCorpProjectId()),
				Integer.valueOf(corpProjectMessage.getType())
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_T, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_T, args);

		if ((corpProjectMessageModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_C_T.getColumnBitmask()) != 0) {
			args = new Object[] {
					Long.valueOf(corpProjectMessageModelImpl.getOriginalCorpProjectId()),
					Integer.valueOf(corpProjectMessageModelImpl.getOriginalType())
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_T, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_T, args);
		}
	}

	/**
	 * Creates a new corp project message with the primary key. Does not add the corp project message to the database.
	 *
	 * @param corpProjectMessageId the primary key for the new corp project message
	 * @return the new corp project message
	 */
	public CorpProjectMessage create(long corpProjectMessageId) {
		CorpProjectMessage corpProjectMessage = new CorpProjectMessageImpl();

		corpProjectMessage.setNew(true);
		corpProjectMessage.setPrimaryKey(corpProjectMessageId);

		return corpProjectMessage;
	}

	/**
	 * Removes the corp project message with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param corpProjectMessageId the primary key of the corp project message
	 * @return the corp project message that was removed
	 * @throws com.liferay.osb.NoSuchCorpProjectMessageException if a corp project message with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CorpProjectMessage remove(long corpProjectMessageId)
		throws NoSuchCorpProjectMessageException, SystemException {
		return remove(Long.valueOf(corpProjectMessageId));
	}

	/**
	 * Removes the corp project message with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the corp project message
	 * @return the corp project message that was removed
	 * @throws com.liferay.osb.NoSuchCorpProjectMessageException if a corp project message with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CorpProjectMessage remove(Serializable primaryKey)
		throws NoSuchCorpProjectMessageException, SystemException {
		Session session = null;

		try {
			session = openSession();

			CorpProjectMessage corpProjectMessage = (CorpProjectMessage)session.get(CorpProjectMessageImpl.class,
					primaryKey);

			if (corpProjectMessage == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCorpProjectMessageException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(corpProjectMessage);
		}
		catch (NoSuchCorpProjectMessageException nsee) {
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
	protected CorpProjectMessage removeImpl(
		CorpProjectMessage corpProjectMessage) throws SystemException {
		corpProjectMessage = toUnwrappedModel(corpProjectMessage);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, corpProjectMessage);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(corpProjectMessage);

		return corpProjectMessage;
	}

	@Override
	public CorpProjectMessage updateImpl(
		com.liferay.osb.model.CorpProjectMessage corpProjectMessage,
		boolean merge) throws SystemException {
		corpProjectMessage = toUnwrappedModel(corpProjectMessage);

		boolean isNew = corpProjectMessage.isNew();

		CorpProjectMessageModelImpl corpProjectMessageModelImpl = (CorpProjectMessageModelImpl)corpProjectMessage;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, corpProjectMessage, merge);

			corpProjectMessage.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !CorpProjectMessageModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((corpProjectMessageModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CORPPROJECTID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(corpProjectMessageModelImpl.getOriginalCorpProjectId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CORPPROJECTID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CORPPROJECTID,
					args);

				args = new Object[] {
						Long.valueOf(corpProjectMessageModelImpl.getCorpProjectId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CORPPROJECTID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CORPPROJECTID,
					args);
			}

			if ((corpProjectMessageModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Integer.valueOf(corpProjectMessageModelImpl.getOriginalType())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TYPE, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPE,
					args);

				args = new Object[] {
						Integer.valueOf(corpProjectMessageModelImpl.getType())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TYPE, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPE,
					args);
			}
		}

		EntityCacheUtil.putResult(CorpProjectMessageModelImpl.ENTITY_CACHE_ENABLED,
			CorpProjectMessageImpl.class, corpProjectMessage.getPrimaryKey(),
			corpProjectMessage);

		clearUniqueFindersCache(corpProjectMessage);
		cacheUniqueFindersCache(corpProjectMessage);

		return corpProjectMessage;
	}

	protected CorpProjectMessage toUnwrappedModel(
		CorpProjectMessage corpProjectMessage) {
		if (corpProjectMessage instanceof CorpProjectMessageImpl) {
			return corpProjectMessage;
		}

		CorpProjectMessageImpl corpProjectMessageImpl = new CorpProjectMessageImpl();

		corpProjectMessageImpl.setNew(corpProjectMessage.isNew());
		corpProjectMessageImpl.setPrimaryKey(corpProjectMessage.getPrimaryKey());

		corpProjectMessageImpl.setCorpProjectMessageId(corpProjectMessage.getCorpProjectMessageId());
		corpProjectMessageImpl.setUserId(corpProjectMessage.getUserId());
		corpProjectMessageImpl.setUserName(corpProjectMessage.getUserName());
		corpProjectMessageImpl.setCreateDate(corpProjectMessage.getCreateDate());
		corpProjectMessageImpl.setModifiedDate(corpProjectMessage.getModifiedDate());
		corpProjectMessageImpl.setCorpProjectId(corpProjectMessage.getCorpProjectId());
		corpProjectMessageImpl.setType(corpProjectMessage.getType());
		corpProjectMessageImpl.setSeverityLevel(corpProjectMessage.getSeverityLevel());
		corpProjectMessageImpl.setTitle(corpProjectMessage.getTitle());
		corpProjectMessageImpl.setContent(corpProjectMessage.getContent());
		corpProjectMessageImpl.setDisplayCP(corpProjectMessage.isDisplayCP());
		corpProjectMessageImpl.setDisplayLCS(corpProjectMessage.isDisplayLCS());
		corpProjectMessageImpl.setDisplayLESA(corpProjectMessage.isDisplayLESA());

		return corpProjectMessageImpl;
	}

	/**
	 * Returns the corp project message with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the corp project message
	 * @return the corp project message
	 * @throws com.liferay.portal.NoSuchModelException if a corp project message with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CorpProjectMessage findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the corp project message with the primary key or throws a {@link com.liferay.osb.NoSuchCorpProjectMessageException} if it could not be found.
	 *
	 * @param corpProjectMessageId the primary key of the corp project message
	 * @return the corp project message
	 * @throws com.liferay.osb.NoSuchCorpProjectMessageException if a corp project message with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CorpProjectMessage findByPrimaryKey(long corpProjectMessageId)
		throws NoSuchCorpProjectMessageException, SystemException {
		CorpProjectMessage corpProjectMessage = fetchByPrimaryKey(corpProjectMessageId);

		if (corpProjectMessage == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					corpProjectMessageId);
			}

			throw new NoSuchCorpProjectMessageException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				corpProjectMessageId);
		}

		return corpProjectMessage;
	}

	/**
	 * Returns the corp project message with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the corp project message
	 * @return the corp project message, or <code>null</code> if a corp project message with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CorpProjectMessage fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the corp project message with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param corpProjectMessageId the primary key of the corp project message
	 * @return the corp project message, or <code>null</code> if a corp project message with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CorpProjectMessage fetchByPrimaryKey(long corpProjectMessageId)
		throws SystemException {
		CorpProjectMessage corpProjectMessage = (CorpProjectMessage)EntityCacheUtil.getResult(CorpProjectMessageModelImpl.ENTITY_CACHE_ENABLED,
				CorpProjectMessageImpl.class, corpProjectMessageId);

		if (corpProjectMessage == _nullCorpProjectMessage) {
			return null;
		}

		if (corpProjectMessage == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				corpProjectMessage = (CorpProjectMessage)session.get(CorpProjectMessageImpl.class,
						Long.valueOf(corpProjectMessageId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (corpProjectMessage != null) {
					cacheResult(corpProjectMessage);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(CorpProjectMessageModelImpl.ENTITY_CACHE_ENABLED,
						CorpProjectMessageImpl.class, corpProjectMessageId,
						_nullCorpProjectMessage);
				}

				closeSession(session);
			}
		}

		return corpProjectMessage;
	}

	/**
	 * Returns all the corp project messages where corpProjectId = &#63;.
	 *
	 * @param corpProjectId the corp project ID
	 * @return the matching corp project messages
	 * @throws SystemException if a system exception occurred
	 */
	public List<CorpProjectMessage> findByCorpProjectId(long corpProjectId)
		throws SystemException {
		return findByCorpProjectId(corpProjectId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the corp project messages where corpProjectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param corpProjectId the corp project ID
	 * @param start the lower bound of the range of corp project messages
	 * @param end the upper bound of the range of corp project messages (not inclusive)
	 * @return the range of matching corp project messages
	 * @throws SystemException if a system exception occurred
	 */
	public List<CorpProjectMessage> findByCorpProjectId(long corpProjectId,
		int start, int end) throws SystemException {
		return findByCorpProjectId(corpProjectId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the corp project messages where corpProjectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param corpProjectId the corp project ID
	 * @param start the lower bound of the range of corp project messages
	 * @param end the upper bound of the range of corp project messages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching corp project messages
	 * @throws SystemException if a system exception occurred
	 */
	public List<CorpProjectMessage> findByCorpProjectId(long corpProjectId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CORPPROJECTID;
			finderArgs = new Object[] { corpProjectId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CORPPROJECTID;
			finderArgs = new Object[] {
					corpProjectId,
					
					start, end, orderByComparator
				};
		}

		List<CorpProjectMessage> list = (List<CorpProjectMessage>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (CorpProjectMessage corpProjectMessage : list) {
				if ((corpProjectId != corpProjectMessage.getCorpProjectId())) {
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

			query.append(_SQL_SELECT_CORPPROJECTMESSAGE_WHERE);

			query.append(_FINDER_COLUMN_CORPPROJECTID_CORPPROJECTID_2);

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

				qPos.add(corpProjectId);

				list = (List<CorpProjectMessage>)QueryUtil.list(q,
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
	 * Returns the first corp project message in the ordered set where corpProjectId = &#63;.
	 *
	 * @param corpProjectId the corp project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching corp project message
	 * @throws com.liferay.osb.NoSuchCorpProjectMessageException if a matching corp project message could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CorpProjectMessage findByCorpProjectId_First(long corpProjectId,
		OrderByComparator orderByComparator)
		throws NoSuchCorpProjectMessageException, SystemException {
		CorpProjectMessage corpProjectMessage = fetchByCorpProjectId_First(corpProjectId,
				orderByComparator);

		if (corpProjectMessage != null) {
			return corpProjectMessage;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("corpProjectId=");
		msg.append(corpProjectId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCorpProjectMessageException(msg.toString());
	}

	/**
	 * Returns the first corp project message in the ordered set where corpProjectId = &#63;.
	 *
	 * @param corpProjectId the corp project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching corp project message, or <code>null</code> if a matching corp project message could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CorpProjectMessage fetchByCorpProjectId_First(long corpProjectId,
		OrderByComparator orderByComparator) throws SystemException {
		List<CorpProjectMessage> list = findByCorpProjectId(corpProjectId, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last corp project message in the ordered set where corpProjectId = &#63;.
	 *
	 * @param corpProjectId the corp project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching corp project message
	 * @throws com.liferay.osb.NoSuchCorpProjectMessageException if a matching corp project message could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CorpProjectMessage findByCorpProjectId_Last(long corpProjectId,
		OrderByComparator orderByComparator)
		throws NoSuchCorpProjectMessageException, SystemException {
		CorpProjectMessage corpProjectMessage = fetchByCorpProjectId_Last(corpProjectId,
				orderByComparator);

		if (corpProjectMessage != null) {
			return corpProjectMessage;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("corpProjectId=");
		msg.append(corpProjectId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCorpProjectMessageException(msg.toString());
	}

	/**
	 * Returns the last corp project message in the ordered set where corpProjectId = &#63;.
	 *
	 * @param corpProjectId the corp project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching corp project message, or <code>null</code> if a matching corp project message could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CorpProjectMessage fetchByCorpProjectId_Last(long corpProjectId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByCorpProjectId(corpProjectId);

		List<CorpProjectMessage> list = findByCorpProjectId(corpProjectId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the corp project messages before and after the current corp project message in the ordered set where corpProjectId = &#63;.
	 *
	 * @param corpProjectMessageId the primary key of the current corp project message
	 * @param corpProjectId the corp project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next corp project message
	 * @throws com.liferay.osb.NoSuchCorpProjectMessageException if a corp project message with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CorpProjectMessage[] findByCorpProjectId_PrevAndNext(
		long corpProjectMessageId, long corpProjectId,
		OrderByComparator orderByComparator)
		throws NoSuchCorpProjectMessageException, SystemException {
		CorpProjectMessage corpProjectMessage = findByPrimaryKey(corpProjectMessageId);

		Session session = null;

		try {
			session = openSession();

			CorpProjectMessage[] array = new CorpProjectMessageImpl[3];

			array[0] = getByCorpProjectId_PrevAndNext(session,
					corpProjectMessage, corpProjectId, orderByComparator, true);

			array[1] = corpProjectMessage;

			array[2] = getByCorpProjectId_PrevAndNext(session,
					corpProjectMessage, corpProjectId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CorpProjectMessage getByCorpProjectId_PrevAndNext(
		Session session, CorpProjectMessage corpProjectMessage,
		long corpProjectId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CORPPROJECTMESSAGE_WHERE);

		query.append(_FINDER_COLUMN_CORPPROJECTID_CORPPROJECTID_2);

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

		qPos.add(corpProjectId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(corpProjectMessage);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CorpProjectMessage> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the corp project messages where type = &#63;.
	 *
	 * @param type the type
	 * @return the matching corp project messages
	 * @throws SystemException if a system exception occurred
	 */
	public List<CorpProjectMessage> findByType(int type)
		throws SystemException {
		return findByType(type, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the corp project messages where type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param type the type
	 * @param start the lower bound of the range of corp project messages
	 * @param end the upper bound of the range of corp project messages (not inclusive)
	 * @return the range of matching corp project messages
	 * @throws SystemException if a system exception occurred
	 */
	public List<CorpProjectMessage> findByType(int type, int start, int end)
		throws SystemException {
		return findByType(type, start, end, null);
	}

	/**
	 * Returns an ordered range of all the corp project messages where type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param type the type
	 * @param start the lower bound of the range of corp project messages
	 * @param end the upper bound of the range of corp project messages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching corp project messages
	 * @throws SystemException if a system exception occurred
	 */
	public List<CorpProjectMessage> findByType(int type, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPE;
			finderArgs = new Object[] { type };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TYPE;
			finderArgs = new Object[] { type, start, end, orderByComparator };
		}

		List<CorpProjectMessage> list = (List<CorpProjectMessage>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (CorpProjectMessage corpProjectMessage : list) {
				if ((type != corpProjectMessage.getType())) {
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

			query.append(_SQL_SELECT_CORPPROJECTMESSAGE_WHERE);

			query.append(_FINDER_COLUMN_TYPE_TYPE_2);

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

				qPos.add(type);

				list = (List<CorpProjectMessage>)QueryUtil.list(q,
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
	 * Returns the first corp project message in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching corp project message
	 * @throws com.liferay.osb.NoSuchCorpProjectMessageException if a matching corp project message could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CorpProjectMessage findByType_First(int type,
		OrderByComparator orderByComparator)
		throws NoSuchCorpProjectMessageException, SystemException {
		CorpProjectMessage corpProjectMessage = fetchByType_First(type,
				orderByComparator);

		if (corpProjectMessage != null) {
			return corpProjectMessage;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCorpProjectMessageException(msg.toString());
	}

	/**
	 * Returns the first corp project message in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching corp project message, or <code>null</code> if a matching corp project message could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CorpProjectMessage fetchByType_First(int type,
		OrderByComparator orderByComparator) throws SystemException {
		List<CorpProjectMessage> list = findByType(type, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last corp project message in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching corp project message
	 * @throws com.liferay.osb.NoSuchCorpProjectMessageException if a matching corp project message could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CorpProjectMessage findByType_Last(int type,
		OrderByComparator orderByComparator)
		throws NoSuchCorpProjectMessageException, SystemException {
		CorpProjectMessage corpProjectMessage = fetchByType_Last(type,
				orderByComparator);

		if (corpProjectMessage != null) {
			return corpProjectMessage;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCorpProjectMessageException(msg.toString());
	}

	/**
	 * Returns the last corp project message in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching corp project message, or <code>null</code> if a matching corp project message could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CorpProjectMessage fetchByType_Last(int type,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByType(type);

		List<CorpProjectMessage> list = findByType(type, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the corp project messages before and after the current corp project message in the ordered set where type = &#63;.
	 *
	 * @param corpProjectMessageId the primary key of the current corp project message
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next corp project message
	 * @throws com.liferay.osb.NoSuchCorpProjectMessageException if a corp project message with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CorpProjectMessage[] findByType_PrevAndNext(
		long corpProjectMessageId, int type, OrderByComparator orderByComparator)
		throws NoSuchCorpProjectMessageException, SystemException {
		CorpProjectMessage corpProjectMessage = findByPrimaryKey(corpProjectMessageId);

		Session session = null;

		try {
			session = openSession();

			CorpProjectMessage[] array = new CorpProjectMessageImpl[3];

			array[0] = getByType_PrevAndNext(session, corpProjectMessage, type,
					orderByComparator, true);

			array[1] = corpProjectMessage;

			array[2] = getByType_PrevAndNext(session, corpProjectMessage, type,
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

	protected CorpProjectMessage getByType_PrevAndNext(Session session,
		CorpProjectMessage corpProjectMessage, int type,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CORPPROJECTMESSAGE_WHERE);

		query.append(_FINDER_COLUMN_TYPE_TYPE_2);

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

		qPos.add(type);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(corpProjectMessage);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CorpProjectMessage> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the corp project message where corpProjectId = &#63; and type = &#63; or throws a {@link com.liferay.osb.NoSuchCorpProjectMessageException} if it could not be found.
	 *
	 * @param corpProjectId the corp project ID
	 * @param type the type
	 * @return the matching corp project message
	 * @throws com.liferay.osb.NoSuchCorpProjectMessageException if a matching corp project message could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CorpProjectMessage findByC_T(long corpProjectId, int type)
		throws NoSuchCorpProjectMessageException, SystemException {
		CorpProjectMessage corpProjectMessage = fetchByC_T(corpProjectId, type);

		if (corpProjectMessage == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("corpProjectId=");
			msg.append(corpProjectId);

			msg.append(", type=");
			msg.append(type);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchCorpProjectMessageException(msg.toString());
		}

		return corpProjectMessage;
	}

	/**
	 * Returns the corp project message where corpProjectId = &#63; and type = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param corpProjectId the corp project ID
	 * @param type the type
	 * @return the matching corp project message, or <code>null</code> if a matching corp project message could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CorpProjectMessage fetchByC_T(long corpProjectId, int type)
		throws SystemException {
		return fetchByC_T(corpProjectId, type, true);
	}

	/**
	 * Returns the corp project message where corpProjectId = &#63; and type = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param corpProjectId the corp project ID
	 * @param type the type
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching corp project message, or <code>null</code> if a matching corp project message could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CorpProjectMessage fetchByC_T(long corpProjectId, int type,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { corpProjectId, type };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_C_T,
					finderArgs, this);
		}

		if (result instanceof CorpProjectMessage) {
			CorpProjectMessage corpProjectMessage = (CorpProjectMessage)result;

			if ((corpProjectId != corpProjectMessage.getCorpProjectId()) ||
					(type != corpProjectMessage.getType())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_CORPPROJECTMESSAGE_WHERE);

			query.append(_FINDER_COLUMN_C_T_CORPPROJECTID_2);

			query.append(_FINDER_COLUMN_C_T_TYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(corpProjectId);

				qPos.add(type);

				List<CorpProjectMessage> list = q.list();

				result = list;

				CorpProjectMessage corpProjectMessage = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_T,
						finderArgs, list);
				}
				else {
					corpProjectMessage = list.get(0);

					cacheResult(corpProjectMessage);

					if ((corpProjectMessage.getCorpProjectId() != corpProjectId) ||
							(corpProjectMessage.getType() != type)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_T,
							finderArgs, corpProjectMessage);
					}
				}

				return corpProjectMessage;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_T,
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
				return (CorpProjectMessage)result;
			}
		}
	}

	/**
	 * Returns all the corp project messages.
	 *
	 * @return the corp project messages
	 * @throws SystemException if a system exception occurred
	 */
	public List<CorpProjectMessage> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the corp project messages.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of corp project messages
	 * @param end the upper bound of the range of corp project messages (not inclusive)
	 * @return the range of corp project messages
	 * @throws SystemException if a system exception occurred
	 */
	public List<CorpProjectMessage> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the corp project messages.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of corp project messages
	 * @param end the upper bound of the range of corp project messages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of corp project messages
	 * @throws SystemException if a system exception occurred
	 */
	public List<CorpProjectMessage> findAll(int start, int end,
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

		List<CorpProjectMessage> list = (List<CorpProjectMessage>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_CORPPROJECTMESSAGE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CORPPROJECTMESSAGE;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<CorpProjectMessage>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<CorpProjectMessage>)QueryUtil.list(q,
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
	 * Removes all the corp project messages where corpProjectId = &#63; from the database.
	 *
	 * @param corpProjectId the corp project ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByCorpProjectId(long corpProjectId)
		throws SystemException {
		for (CorpProjectMessage corpProjectMessage : findByCorpProjectId(
				corpProjectId)) {
			remove(corpProjectMessage);
		}
	}

	/**
	 * Removes all the corp project messages where type = &#63; from the database.
	 *
	 * @param type the type
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByType(int type) throws SystemException {
		for (CorpProjectMessage corpProjectMessage : findByType(type)) {
			remove(corpProjectMessage);
		}
	}

	/**
	 * Removes the corp project message where corpProjectId = &#63; and type = &#63; from the database.
	 *
	 * @param corpProjectId the corp project ID
	 * @param type the type
	 * @return the corp project message that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public CorpProjectMessage removeByC_T(long corpProjectId, int type)
		throws NoSuchCorpProjectMessageException, SystemException {
		CorpProjectMessage corpProjectMessage = findByC_T(corpProjectId, type);

		return remove(corpProjectMessage);
	}

	/**
	 * Removes all the corp project messages from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (CorpProjectMessage corpProjectMessage : findAll()) {
			remove(corpProjectMessage);
		}
	}

	/**
	 * Returns the number of corp project messages where corpProjectId = &#63;.
	 *
	 * @param corpProjectId the corp project ID
	 * @return the number of matching corp project messages
	 * @throws SystemException if a system exception occurred
	 */
	public int countByCorpProjectId(long corpProjectId)
		throws SystemException {
		Object[] finderArgs = new Object[] { corpProjectId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_CORPPROJECTID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CORPPROJECTMESSAGE_WHERE);

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
	 * Returns the number of corp project messages where type = &#63;.
	 *
	 * @param type the type
	 * @return the number of matching corp project messages
	 * @throws SystemException if a system exception occurred
	 */
	public int countByType(int type) throws SystemException {
		Object[] finderArgs = new Object[] { type };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_TYPE,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CORPPROJECTMESSAGE_WHERE);

			query.append(_FINDER_COLUMN_TYPE_TYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_TYPE,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of corp project messages where corpProjectId = &#63; and type = &#63;.
	 *
	 * @param corpProjectId the corp project ID
	 * @param type the type
	 * @return the number of matching corp project messages
	 * @throws SystemException if a system exception occurred
	 */
	public int countByC_T(long corpProjectId, int type)
		throws SystemException {
		Object[] finderArgs = new Object[] { corpProjectId, type };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_C_T,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CORPPROJECTMESSAGE_WHERE);

			query.append(_FINDER_COLUMN_C_T_CORPPROJECTID_2);

			query.append(_FINDER_COLUMN_C_T_TYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(corpProjectId);

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_T, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of corp project messages.
	 *
	 * @return the number of corp project messages
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CORPPROJECTMESSAGE);

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
	 * Initializes the corp project message persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.osb.model.CorpProjectMessage")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<CorpProjectMessage>> listenersList = new ArrayList<ModelListener<CorpProjectMessage>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<CorpProjectMessage>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(CorpProjectMessageImpl.class.getName());
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
	private static final String _SQL_SELECT_CORPPROJECTMESSAGE = "SELECT corpProjectMessage FROM CorpProjectMessage corpProjectMessage";
	private static final String _SQL_SELECT_CORPPROJECTMESSAGE_WHERE = "SELECT corpProjectMessage FROM CorpProjectMessage corpProjectMessage WHERE ";
	private static final String _SQL_COUNT_CORPPROJECTMESSAGE = "SELECT COUNT(corpProjectMessage) FROM CorpProjectMessage corpProjectMessage";
	private static final String _SQL_COUNT_CORPPROJECTMESSAGE_WHERE = "SELECT COUNT(corpProjectMessage) FROM CorpProjectMessage corpProjectMessage WHERE ";
	private static final String _FINDER_COLUMN_CORPPROJECTID_CORPPROJECTID_2 = "corpProjectMessage.corpProjectId = ?";
	private static final String _FINDER_COLUMN_TYPE_TYPE_2 = "corpProjectMessage.type = ?";
	private static final String _FINDER_COLUMN_C_T_CORPPROJECTID_2 = "corpProjectMessage.corpProjectId = ? AND ";
	private static final String _FINDER_COLUMN_C_T_TYPE_2 = "corpProjectMessage.type = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "corpProjectMessage.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CorpProjectMessage exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No CorpProjectMessage exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(CorpProjectMessagePersistenceImpl.class);
	private static CorpProjectMessage _nullCorpProjectMessage = new CorpProjectMessageImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<CorpProjectMessage> toCacheModel() {
				return _nullCorpProjectMessageCacheModel;
			}
		};

	private static CacheModel<CorpProjectMessage> _nullCorpProjectMessageCacheModel =
		new CacheModel<CorpProjectMessage>() {
			public CorpProjectMessage toEntityModel() {
				return _nullCorpProjectMessage;
			}
		};
}