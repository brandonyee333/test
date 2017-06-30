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

import com.liferay.osb.NoSuchCorpGroupException;
import com.liferay.osb.model.CorpGroup;
import com.liferay.osb.model.impl.CorpGroupImpl;
import com.liferay.osb.model.impl.CorpGroupModelImpl;

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
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ClassNamePersistence;
import com.liferay.portal.service.persistence.GroupPersistence;
import com.liferay.portal.service.persistence.OrganizationPersistence;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserGroupRolePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the corp group service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CorpGroupPersistence
 * @see CorpGroupUtil
 * @generated
 */
public class CorpGroupPersistenceImpl extends BasePersistenceImpl<CorpGroup>
	implements CorpGroupPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CorpGroupUtil} to access the corp group persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CorpGroupImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_NAME = new FinderPath(CorpGroupModelImpl.ENTITY_CACHE_ENABLED,
			CorpGroupModelImpl.FINDER_CACHE_ENABLED, CorpGroupImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByName",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAME = new FinderPath(CorpGroupModelImpl.ENTITY_CACHE_ENABLED,
			CorpGroupModelImpl.FINDER_CACHE_ENABLED, CorpGroupImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByName",
			new String[] { String.class.getName() },
			CorpGroupModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_NAME = new FinderPath(CorpGroupModelImpl.ENTITY_CACHE_ENABLED,
			CorpGroupModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByName",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_ORGANIZATIONID = new FinderPath(CorpGroupModelImpl.ENTITY_CACHE_ENABLED,
			CorpGroupModelImpl.FINDER_CACHE_ENABLED, CorpGroupImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByOrganizationId",
			new String[] { Long.class.getName() },
			CorpGroupModelImpl.ORGANIZATIONID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ORGANIZATIONID = new FinderPath(CorpGroupModelImpl.ENTITY_CACHE_ENABLED,
			CorpGroupModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByOrganizationId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CorpGroupModelImpl.ENTITY_CACHE_ENABLED,
			CorpGroupModelImpl.FINDER_CACHE_ENABLED, CorpGroupImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CorpGroupModelImpl.ENTITY_CACHE_ENABLED,
			CorpGroupModelImpl.FINDER_CACHE_ENABLED, CorpGroupImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CorpGroupModelImpl.ENTITY_CACHE_ENABLED,
			CorpGroupModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the corp group in the entity cache if it is enabled.
	 *
	 * @param corpGroup the corp group
	 */
	public void cacheResult(CorpGroup corpGroup) {
		EntityCacheUtil.putResult(CorpGroupModelImpl.ENTITY_CACHE_ENABLED,
			CorpGroupImpl.class, corpGroup.getPrimaryKey(), corpGroup);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_ORGANIZATIONID,
			new Object[] { Long.valueOf(corpGroup.getOrganizationId()) },
			corpGroup);

		corpGroup.resetOriginalValues();
	}

	/**
	 * Caches the corp groups in the entity cache if it is enabled.
	 *
	 * @param corpGroups the corp groups
	 */
	public void cacheResult(List<CorpGroup> corpGroups) {
		for (CorpGroup corpGroup : corpGroups) {
			if (EntityCacheUtil.getResult(
						CorpGroupModelImpl.ENTITY_CACHE_ENABLED,
						CorpGroupImpl.class, corpGroup.getPrimaryKey()) == null) {
				cacheResult(corpGroup);
			}
			else {
				corpGroup.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all corp groups.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(CorpGroupImpl.class.getName());
		}

		EntityCacheUtil.clearCache(CorpGroupImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the corp group.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CorpGroup corpGroup) {
		EntityCacheUtil.removeResult(CorpGroupModelImpl.ENTITY_CACHE_ENABLED,
			CorpGroupImpl.class, corpGroup.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(corpGroup);
	}

	@Override
	public void clearCache(List<CorpGroup> corpGroups) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CorpGroup corpGroup : corpGroups) {
			EntityCacheUtil.removeResult(CorpGroupModelImpl.ENTITY_CACHE_ENABLED,
				CorpGroupImpl.class, corpGroup.getPrimaryKey());

			clearUniqueFindersCache(corpGroup);
		}
	}

	protected void cacheUniqueFindersCache(CorpGroup corpGroup) {
		if (corpGroup.isNew()) {
			Object[] args = new Object[] {
					Long.valueOf(corpGroup.getOrganizationId())
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_ORGANIZATIONID,
				args, Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_ORGANIZATIONID,
				args, corpGroup);
		}
		else {
			CorpGroupModelImpl corpGroupModelImpl = (CorpGroupModelImpl)corpGroup;

			if ((corpGroupModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_ORGANIZATIONID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(corpGroup.getOrganizationId())
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_ORGANIZATIONID,
					args, Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_ORGANIZATIONID,
					args, corpGroup);
			}
		}
	}

	protected void clearUniqueFindersCache(CorpGroup corpGroup) {
		CorpGroupModelImpl corpGroupModelImpl = (CorpGroupModelImpl)corpGroup;

		Object[] args = new Object[] { Long.valueOf(corpGroup.getOrganizationId()) };

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ORGANIZATIONID, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_ORGANIZATIONID, args);

		if ((corpGroupModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_ORGANIZATIONID.getColumnBitmask()) != 0) {
			args = new Object[] {
					Long.valueOf(corpGroupModelImpl.getOriginalOrganizationId())
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ORGANIZATIONID,
				args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_ORGANIZATIONID,
				args);
		}
	}

	/**
	 * Creates a new corp group with the primary key. Does not add the corp group to the database.
	 *
	 * @param corpGroupId the primary key for the new corp group
	 * @return the new corp group
	 */
	public CorpGroup create(long corpGroupId) {
		CorpGroup corpGroup = new CorpGroupImpl();

		corpGroup.setNew(true);
		corpGroup.setPrimaryKey(corpGroupId);

		return corpGroup;
	}

	/**
	 * Removes the corp group with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param corpGroupId the primary key of the corp group
	 * @return the corp group that was removed
	 * @throws com.liferay.osb.NoSuchCorpGroupException if a corp group with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CorpGroup remove(long corpGroupId)
		throws NoSuchCorpGroupException, SystemException {
		return remove(Long.valueOf(corpGroupId));
	}

	/**
	 * Removes the corp group with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the corp group
	 * @return the corp group that was removed
	 * @throws com.liferay.osb.NoSuchCorpGroupException if a corp group with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CorpGroup remove(Serializable primaryKey)
		throws NoSuchCorpGroupException, SystemException {
		Session session = null;

		try {
			session = openSession();

			CorpGroup corpGroup = (CorpGroup)session.get(CorpGroupImpl.class,
					primaryKey);

			if (corpGroup == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCorpGroupException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(corpGroup);
		}
		catch (NoSuchCorpGroupException nsee) {
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
	protected CorpGroup removeImpl(CorpGroup corpGroup)
		throws SystemException {
		corpGroup = toUnwrappedModel(corpGroup);

		try {
			clearCorpEntries.clear(corpGroup.getPrimaryKey());
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(CorpGroupModelImpl.MAPPING_TABLE_OSB_CORPENTRY_CORPGROUP_NAME);
		}

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, corpGroup);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(corpGroup);

		return corpGroup;
	}

	@Override
	public CorpGroup updateImpl(com.liferay.osb.model.CorpGroup corpGroup,
		boolean merge) throws SystemException {
		corpGroup = toUnwrappedModel(corpGroup);

		boolean isNew = corpGroup.isNew();

		CorpGroupModelImpl corpGroupModelImpl = (CorpGroupModelImpl)corpGroup;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, corpGroup, merge);

			corpGroup.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !CorpGroupModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((corpGroupModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAME.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						corpGroupModelImpl.getOriginalName()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_NAME, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAME,
					args);

				args = new Object[] { corpGroupModelImpl.getName() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_NAME, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAME,
					args);
			}
		}

		EntityCacheUtil.putResult(CorpGroupModelImpl.ENTITY_CACHE_ENABLED,
			CorpGroupImpl.class, corpGroup.getPrimaryKey(), corpGroup);

		clearUniqueFindersCache(corpGroup);
		cacheUniqueFindersCache(corpGroup);

		return corpGroup;
	}

	protected CorpGroup toUnwrappedModel(CorpGroup corpGroup) {
		if (corpGroup instanceof CorpGroupImpl) {
			return corpGroup;
		}

		CorpGroupImpl corpGroupImpl = new CorpGroupImpl();

		corpGroupImpl.setNew(corpGroup.isNew());
		corpGroupImpl.setPrimaryKey(corpGroup.getPrimaryKey());

		corpGroupImpl.setCorpGroupId(corpGroup.getCorpGroupId());
		corpGroupImpl.setUserId(corpGroup.getUserId());
		corpGroupImpl.setUserName(corpGroup.getUserName());
		corpGroupImpl.setCreateDate(corpGroup.getCreateDate());
		corpGroupImpl.setModifiedDate(corpGroup.getModifiedDate());
		corpGroupImpl.setName(corpGroup.getName());
		corpGroupImpl.setDescription(corpGroup.getDescription());
		corpGroupImpl.setOrganizationId(corpGroup.getOrganizationId());
		corpGroupImpl.setLogoId(corpGroup.getLogoId());
		corpGroupImpl.setEmailAddress(corpGroup.getEmailAddress());
		corpGroupImpl.setWebsite(corpGroup.getWebsite());

		return corpGroupImpl;
	}

	/**
	 * Returns the corp group with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the corp group
	 * @return the corp group
	 * @throws com.liferay.portal.NoSuchModelException if a corp group with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CorpGroup findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the corp group with the primary key or throws a {@link com.liferay.osb.NoSuchCorpGroupException} if it could not be found.
	 *
	 * @param corpGroupId the primary key of the corp group
	 * @return the corp group
	 * @throws com.liferay.osb.NoSuchCorpGroupException if a corp group with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CorpGroup findByPrimaryKey(long corpGroupId)
		throws NoSuchCorpGroupException, SystemException {
		CorpGroup corpGroup = fetchByPrimaryKey(corpGroupId);

		if (corpGroup == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + corpGroupId);
			}

			throw new NoSuchCorpGroupException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				corpGroupId);
		}

		return corpGroup;
	}

	/**
	 * Returns the corp group with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the corp group
	 * @return the corp group, or <code>null</code> if a corp group with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CorpGroup fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the corp group with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param corpGroupId the primary key of the corp group
	 * @return the corp group, or <code>null</code> if a corp group with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CorpGroup fetchByPrimaryKey(long corpGroupId)
		throws SystemException {
		CorpGroup corpGroup = (CorpGroup)EntityCacheUtil.getResult(CorpGroupModelImpl.ENTITY_CACHE_ENABLED,
				CorpGroupImpl.class, corpGroupId);

		if (corpGroup == _nullCorpGroup) {
			return null;
		}

		if (corpGroup == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				corpGroup = (CorpGroup)session.get(CorpGroupImpl.class,
						Long.valueOf(corpGroupId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (corpGroup != null) {
					cacheResult(corpGroup);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(CorpGroupModelImpl.ENTITY_CACHE_ENABLED,
						CorpGroupImpl.class, corpGroupId, _nullCorpGroup);
				}

				closeSession(session);
			}
		}

		return corpGroup;
	}

	/**
	 * Returns all the corp groups where name = &#63;.
	 *
	 * @param name the name
	 * @return the matching corp groups
	 * @throws SystemException if a system exception occurred
	 */
	public List<CorpGroup> findByName(String name) throws SystemException {
		return findByName(name, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the corp groups where name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param name the name
	 * @param start the lower bound of the range of corp groups
	 * @param end the upper bound of the range of corp groups (not inclusive)
	 * @return the range of matching corp groups
	 * @throws SystemException if a system exception occurred
	 */
	public List<CorpGroup> findByName(String name, int start, int end)
		throws SystemException {
		return findByName(name, start, end, null);
	}

	/**
	 * Returns an ordered range of all the corp groups where name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param name the name
	 * @param start the lower bound of the range of corp groups
	 * @param end the upper bound of the range of corp groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching corp groups
	 * @throws SystemException if a system exception occurred
	 */
	public List<CorpGroup> findByName(String name, int start, int end,
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

		List<CorpGroup> list = (List<CorpGroup>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (CorpGroup corpGroup : list) {
				if (!Validator.equals(name, corpGroup.getName())) {
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

			query.append(_SQL_SELECT_CORPGROUP_WHERE);

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

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (name != null) {
					qPos.add(name);
				}

				list = (List<CorpGroup>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the first corp group in the ordered set where name = &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching corp group
	 * @throws com.liferay.osb.NoSuchCorpGroupException if a matching corp group could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CorpGroup findByName_First(String name,
		OrderByComparator orderByComparator)
		throws NoSuchCorpGroupException, SystemException {
		CorpGroup corpGroup = fetchByName_First(name, orderByComparator);

		if (corpGroup != null) {
			return corpGroup;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("name=");
		msg.append(name);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCorpGroupException(msg.toString());
	}

	/**
	 * Returns the first corp group in the ordered set where name = &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching corp group, or <code>null</code> if a matching corp group could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CorpGroup fetchByName_First(String name,
		OrderByComparator orderByComparator) throws SystemException {
		List<CorpGroup> list = findByName(name, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last corp group in the ordered set where name = &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching corp group
	 * @throws com.liferay.osb.NoSuchCorpGroupException if a matching corp group could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CorpGroup findByName_Last(String name,
		OrderByComparator orderByComparator)
		throws NoSuchCorpGroupException, SystemException {
		CorpGroup corpGroup = fetchByName_Last(name, orderByComparator);

		if (corpGroup != null) {
			return corpGroup;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("name=");
		msg.append(name);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCorpGroupException(msg.toString());
	}

	/**
	 * Returns the last corp group in the ordered set where name = &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching corp group, or <code>null</code> if a matching corp group could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CorpGroup fetchByName_Last(String name,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByName(name);

		List<CorpGroup> list = findByName(name, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the corp groups before and after the current corp group in the ordered set where name = &#63;.
	 *
	 * @param corpGroupId the primary key of the current corp group
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next corp group
	 * @throws com.liferay.osb.NoSuchCorpGroupException if a corp group with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CorpGroup[] findByName_PrevAndNext(long corpGroupId, String name,
		OrderByComparator orderByComparator)
		throws NoSuchCorpGroupException, SystemException {
		CorpGroup corpGroup = findByPrimaryKey(corpGroupId);

		Session session = null;

		try {
			session = openSession();

			CorpGroup[] array = new CorpGroupImpl[3];

			array[0] = getByName_PrevAndNext(session, corpGroup, name,
					orderByComparator, true);

			array[1] = corpGroup;

			array[2] = getByName_PrevAndNext(session, corpGroup, name,
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

	protected CorpGroup getByName_PrevAndNext(Session session,
		CorpGroup corpGroup, String name, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CORPGROUP_WHERE);

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

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (name != null) {
			qPos.add(name);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(corpGroup);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CorpGroup> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the corp group where organizationId = &#63; or throws a {@link com.liferay.osb.NoSuchCorpGroupException} if it could not be found.
	 *
	 * @param organizationId the organization ID
	 * @return the matching corp group
	 * @throws com.liferay.osb.NoSuchCorpGroupException if a matching corp group could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CorpGroup findByOrganizationId(long organizationId)
		throws NoSuchCorpGroupException, SystemException {
		CorpGroup corpGroup = fetchByOrganizationId(organizationId);

		if (corpGroup == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("organizationId=");
			msg.append(organizationId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchCorpGroupException(msg.toString());
		}

		return corpGroup;
	}

	/**
	 * Returns the corp group where organizationId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param organizationId the organization ID
	 * @return the matching corp group, or <code>null</code> if a matching corp group could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CorpGroup fetchByOrganizationId(long organizationId)
		throws SystemException {
		return fetchByOrganizationId(organizationId, true);
	}

	/**
	 * Returns the corp group where organizationId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param organizationId the organization ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching corp group, or <code>null</code> if a matching corp group could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CorpGroup fetchByOrganizationId(long organizationId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { organizationId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_ORGANIZATIONID,
					finderArgs, this);
		}

		if (result instanceof CorpGroup) {
			CorpGroup corpGroup = (CorpGroup)result;

			if ((organizationId != corpGroup.getOrganizationId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_SELECT_CORPGROUP_WHERE);

			query.append(_FINDER_COLUMN_ORGANIZATIONID_ORGANIZATIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(organizationId);

				List<CorpGroup> list = q.list();

				result = list;

				CorpGroup corpGroup = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_ORGANIZATIONID,
						finderArgs, list);
				}
				else {
					corpGroup = list.get(0);

					cacheResult(corpGroup);

					if ((corpGroup.getOrganizationId() != organizationId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_ORGANIZATIONID,
							finderArgs, corpGroup);
					}
				}

				return corpGroup;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_ORGANIZATIONID,
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
				return (CorpGroup)result;
			}
		}
	}

	/**
	 * Returns all the corp groups.
	 *
	 * @return the corp groups
	 * @throws SystemException if a system exception occurred
	 */
	public List<CorpGroup> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the corp groups.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of corp groups
	 * @param end the upper bound of the range of corp groups (not inclusive)
	 * @return the range of corp groups
	 * @throws SystemException if a system exception occurred
	 */
	public List<CorpGroup> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the corp groups.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of corp groups
	 * @param end the upper bound of the range of corp groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of corp groups
	 * @throws SystemException if a system exception occurred
	 */
	public List<CorpGroup> findAll(int start, int end,
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

		List<CorpGroup> list = (List<CorpGroup>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_CORPGROUP);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CORPGROUP;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<CorpGroup>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<CorpGroup>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the corp groups where name = &#63; from the database.
	 *
	 * @param name the name
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByName(String name) throws SystemException {
		for (CorpGroup corpGroup : findByName(name)) {
			remove(corpGroup);
		}
	}

	/**
	 * Removes the corp group where organizationId = &#63; from the database.
	 *
	 * @param organizationId the organization ID
	 * @return the corp group that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public CorpGroup removeByOrganizationId(long organizationId)
		throws NoSuchCorpGroupException, SystemException {
		CorpGroup corpGroup = findByOrganizationId(organizationId);

		return remove(corpGroup);
	}

	/**
	 * Removes all the corp groups from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (CorpGroup corpGroup : findAll()) {
			remove(corpGroup);
		}
	}

	/**
	 * Returns the number of corp groups where name = &#63;.
	 *
	 * @param name the name
	 * @return the number of matching corp groups
	 * @throws SystemException if a system exception occurred
	 */
	public int countByName(String name) throws SystemException {
		Object[] finderArgs = new Object[] { name };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_NAME,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CORPGROUP_WHERE);

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
	 * Returns the number of corp groups where organizationId = &#63;.
	 *
	 * @param organizationId the organization ID
	 * @return the number of matching corp groups
	 * @throws SystemException if a system exception occurred
	 */
	public int countByOrganizationId(long organizationId)
		throws SystemException {
		Object[] finderArgs = new Object[] { organizationId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_ORGANIZATIONID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CORPGROUP_WHERE);

			query.append(_FINDER_COLUMN_ORGANIZATIONID_ORGANIZATIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(organizationId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_ORGANIZATIONID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of corp groups.
	 *
	 * @return the number of corp groups
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CORPGROUP);

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
	 * Returns all the corp entries associated with the corp group.
	 *
	 * @param pk the primary key of the corp group
	 * @return the corp entries associated with the corp group
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.liferay.osb.model.CorpEntry> getCorpEntries(long pk)
		throws SystemException {
		return getCorpEntries(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	/**
	 * Returns a range of all the corp entries associated with the corp group.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param pk the primary key of the corp group
	 * @param start the lower bound of the range of corp groups
	 * @param end the upper bound of the range of corp groups (not inclusive)
	 * @return the range of corp entries associated with the corp group
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.liferay.osb.model.CorpEntry> getCorpEntries(long pk,
		int start, int end) throws SystemException {
		return getCorpEntries(pk, start, end, null);
	}

	public static final FinderPath FINDER_PATH_GET_CORPENTRIES = new FinderPath(com.liferay.osb.model.impl.CorpEntryModelImpl.ENTITY_CACHE_ENABLED,
			CorpGroupModelImpl.FINDER_CACHE_ENABLED_OSB_CORPENTRY_CORPGROUP,
			com.liferay.osb.model.impl.CorpEntryImpl.class,
			CorpGroupModelImpl.MAPPING_TABLE_OSB_CORPENTRY_CORPGROUP_NAME,
			"getCorpEntries",
			new String[] {
				Long.class.getName(), "java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});

	static {
		FINDER_PATH_GET_CORPENTRIES.setCacheKeyGeneratorCacheName(null);
	}

	/**
	 * Returns an ordered range of all the corp entries associated with the corp group.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param pk the primary key of the corp group
	 * @param start the lower bound of the range of corp groups
	 * @param end the upper bound of the range of corp groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of corp entries associated with the corp group
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.liferay.osb.model.CorpEntry> getCorpEntries(long pk,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] { pk, start, end, orderByComparator };

		List<com.liferay.osb.model.CorpEntry> list = (List<com.liferay.osb.model.CorpEntry>)FinderCacheUtil.getResult(FINDER_PATH_GET_CORPENTRIES,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				String sql = null;

				if (orderByComparator != null) {
					sql = _SQL_GETCORPENTRIES.concat(ORDER_BY_CLAUSE)
											 .concat(orderByComparator.getOrderBy());
				}
				else {
					sql = _SQL_GETCORPENTRIES;
				}

				SQLQuery q = session.createSQLQuery(sql);

				q.addEntity("OSB_CorpEntry",
					com.liferay.osb.model.impl.CorpEntryImpl.class);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(pk);

				list = (List<com.liferay.osb.model.CorpEntry>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_GET_CORPENTRIES,
						finderArgs);
				}
				else {
					corpEntryPersistence.cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_GET_CORPENTRIES,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	public static final FinderPath FINDER_PATH_GET_CORPENTRIES_SIZE = new FinderPath(com.liferay.osb.model.impl.CorpEntryModelImpl.ENTITY_CACHE_ENABLED,
			CorpGroupModelImpl.FINDER_CACHE_ENABLED_OSB_CORPENTRY_CORPGROUP,
			Long.class,
			CorpGroupModelImpl.MAPPING_TABLE_OSB_CORPENTRY_CORPGROUP_NAME,
			"getCorpEntriesSize", new String[] { Long.class.getName() });

	static {
		FINDER_PATH_GET_CORPENTRIES_SIZE.setCacheKeyGeneratorCacheName(null);
	}

	/**
	 * Returns the number of corp entries associated with the corp group.
	 *
	 * @param pk the primary key of the corp group
	 * @return the number of corp entries associated with the corp group
	 * @throws SystemException if a system exception occurred
	 */
	public int getCorpEntriesSize(long pk) throws SystemException {
		Object[] finderArgs = new Object[] { pk };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_GET_CORPENTRIES_SIZE,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				SQLQuery q = session.createSQLQuery(_SQL_GETCORPENTRIESSIZE);

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

				FinderCacheUtil.putResult(FINDER_PATH_GET_CORPENTRIES_SIZE,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public static final FinderPath FINDER_PATH_CONTAINS_CORPENTRY = new FinderPath(com.liferay.osb.model.impl.CorpEntryModelImpl.ENTITY_CACHE_ENABLED,
			CorpGroupModelImpl.FINDER_CACHE_ENABLED_OSB_CORPENTRY_CORPGROUP,
			Boolean.class,
			CorpGroupModelImpl.MAPPING_TABLE_OSB_CORPENTRY_CORPGROUP_NAME,
			"containsCorpEntry",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns <code>true</code> if the corp entry is associated with the corp group.
	 *
	 * @param pk the primary key of the corp group
	 * @param corpEntryPK the primary key of the corp entry
	 * @return <code>true</code> if the corp entry is associated with the corp group; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	public boolean containsCorpEntry(long pk, long corpEntryPK)
		throws SystemException {
		Object[] finderArgs = new Object[] { pk, corpEntryPK };

		Boolean value = (Boolean)FinderCacheUtil.getResult(FINDER_PATH_CONTAINS_CORPENTRY,
				finderArgs, this);

		if (value == null) {
			try {
				value = Boolean.valueOf(containsCorpEntry.contains(pk,
							corpEntryPK));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (value == null) {
					value = Boolean.FALSE;
				}

				FinderCacheUtil.putResult(FINDER_PATH_CONTAINS_CORPENTRY,
					finderArgs, value);
			}
		}

		return value.booleanValue();
	}

	/**
	 * Returns <code>true</code> if the corp group has any corp entries associated with it.
	 *
	 * @param pk the primary key of the corp group to check for associations with corp entries
	 * @return <code>true</code> if the corp group has any corp entries associated with it; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	public boolean containsCorpEntries(long pk) throws SystemException {
		if (getCorpEntriesSize(pk) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Adds an association between the corp group and the corp entry. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the corp group
	 * @param corpEntryPK the primary key of the corp entry
	 * @throws SystemException if a system exception occurred
	 */
	public void addCorpEntry(long pk, long corpEntryPK)
		throws SystemException {
		try {
			addCorpEntry.add(pk, corpEntryPK);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(CorpGroupModelImpl.MAPPING_TABLE_OSB_CORPENTRY_CORPGROUP_NAME);
		}
	}

	/**
	 * Adds an association between the corp group and the corp entry. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the corp group
	 * @param corpEntry the corp entry
	 * @throws SystemException if a system exception occurred
	 */
	public void addCorpEntry(long pk, com.liferay.osb.model.CorpEntry corpEntry)
		throws SystemException {
		try {
			addCorpEntry.add(pk, corpEntry.getPrimaryKey());
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(CorpGroupModelImpl.MAPPING_TABLE_OSB_CORPENTRY_CORPGROUP_NAME);
		}
	}

	/**
	 * Adds an association between the corp group and the corp entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the corp group
	 * @param corpEntryPKs the primary keys of the corp entries
	 * @throws SystemException if a system exception occurred
	 */
	public void addCorpEntries(long pk, long[] corpEntryPKs)
		throws SystemException {
		try {
			for (long corpEntryPK : corpEntryPKs) {
				addCorpEntry.add(pk, corpEntryPK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(CorpGroupModelImpl.MAPPING_TABLE_OSB_CORPENTRY_CORPGROUP_NAME);
		}
	}

	/**
	 * Adds an association between the corp group and the corp entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the corp group
	 * @param corpEntries the corp entries
	 * @throws SystemException if a system exception occurred
	 */
	public void addCorpEntries(long pk,
		List<com.liferay.osb.model.CorpEntry> corpEntries)
		throws SystemException {
		try {
			for (com.liferay.osb.model.CorpEntry corpEntry : corpEntries) {
				addCorpEntry.add(pk, corpEntry.getPrimaryKey());
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(CorpGroupModelImpl.MAPPING_TABLE_OSB_CORPENTRY_CORPGROUP_NAME);
		}
	}

	/**
	 * Clears all associations between the corp group and its corp entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the corp group to clear the associated corp entries from
	 * @throws SystemException if a system exception occurred
	 */
	public void clearCorpEntries(long pk) throws SystemException {
		try {
			clearCorpEntries.clear(pk);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(CorpGroupModelImpl.MAPPING_TABLE_OSB_CORPENTRY_CORPGROUP_NAME);
		}
	}

	/**
	 * Removes the association between the corp group and the corp entry. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the corp group
	 * @param corpEntryPK the primary key of the corp entry
	 * @throws SystemException if a system exception occurred
	 */
	public void removeCorpEntry(long pk, long corpEntryPK)
		throws SystemException {
		try {
			removeCorpEntry.remove(pk, corpEntryPK);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(CorpGroupModelImpl.MAPPING_TABLE_OSB_CORPENTRY_CORPGROUP_NAME);
		}
	}

	/**
	 * Removes the association between the corp group and the corp entry. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the corp group
	 * @param corpEntry the corp entry
	 * @throws SystemException if a system exception occurred
	 */
	public void removeCorpEntry(long pk,
		com.liferay.osb.model.CorpEntry corpEntry) throws SystemException {
		try {
			removeCorpEntry.remove(pk, corpEntry.getPrimaryKey());
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(CorpGroupModelImpl.MAPPING_TABLE_OSB_CORPENTRY_CORPGROUP_NAME);
		}
	}

	/**
	 * Removes the association between the corp group and the corp entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the corp group
	 * @param corpEntryPKs the primary keys of the corp entries
	 * @throws SystemException if a system exception occurred
	 */
	public void removeCorpEntries(long pk, long[] corpEntryPKs)
		throws SystemException {
		try {
			for (long corpEntryPK : corpEntryPKs) {
				removeCorpEntry.remove(pk, corpEntryPK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(CorpGroupModelImpl.MAPPING_TABLE_OSB_CORPENTRY_CORPGROUP_NAME);
		}
	}

	/**
	 * Removes the association between the corp group and the corp entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the corp group
	 * @param corpEntries the corp entries
	 * @throws SystemException if a system exception occurred
	 */
	public void removeCorpEntries(long pk,
		List<com.liferay.osb.model.CorpEntry> corpEntries)
		throws SystemException {
		try {
			for (com.liferay.osb.model.CorpEntry corpEntry : corpEntries) {
				removeCorpEntry.remove(pk, corpEntry.getPrimaryKey());
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(CorpGroupModelImpl.MAPPING_TABLE_OSB_CORPENTRY_CORPGROUP_NAME);
		}
	}

	/**
	 * Sets the corp entries associated with the corp group, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the corp group
	 * @param corpEntryPKs the primary keys of the corp entries to be associated with the corp group
	 * @throws SystemException if a system exception occurred
	 */
	public void setCorpEntries(long pk, long[] corpEntryPKs)
		throws SystemException {
		try {
			Set<Long> corpEntryPKSet = SetUtil.fromArray(corpEntryPKs);

			List<com.liferay.osb.model.CorpEntry> corpEntries = getCorpEntries(pk);

			for (com.liferay.osb.model.CorpEntry corpEntry : corpEntries) {
				if (!corpEntryPKSet.remove(corpEntry.getPrimaryKey())) {
					removeCorpEntry.remove(pk, corpEntry.getPrimaryKey());
				}
			}

			for (Long corpEntryPK : corpEntryPKSet) {
				addCorpEntry.add(pk, corpEntryPK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(CorpGroupModelImpl.MAPPING_TABLE_OSB_CORPENTRY_CORPGROUP_NAME);
		}
	}

	/**
	 * Sets the corp entries associated with the corp group, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the corp group
	 * @param corpEntries the corp entries to be associated with the corp group
	 * @throws SystemException if a system exception occurred
	 */
	public void setCorpEntries(long pk,
		List<com.liferay.osb.model.CorpEntry> corpEntries)
		throws SystemException {
		try {
			long[] corpEntryPKs = new long[corpEntries.size()];

			for (int i = 0; i < corpEntries.size(); i++) {
				com.liferay.osb.model.CorpEntry corpEntry = corpEntries.get(i);

				corpEntryPKs[i] = corpEntry.getPrimaryKey();
			}

			setCorpEntries(pk, corpEntryPKs);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(CorpGroupModelImpl.MAPPING_TABLE_OSB_CORPENTRY_CORPGROUP_NAME);
		}
	}

	/**
	 * Initializes the corp group persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.osb.model.CorpGroup")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<CorpGroup>> listenersList = new ArrayList<ModelListener<CorpGroup>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<CorpGroup>)InstanceFactory.newInstance(
							clazz.getClassLoader(), listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}

		containsCorpEntry = new ContainsCorpEntry();

		addCorpEntry = new AddCorpEntry();
		clearCorpEntries = new ClearCorpEntries();
		removeCorpEntry = new RemoveCorpEntry();
	}

	public void destroy() {
		EntityCacheUtil.removeCache(CorpGroupImpl.class.getName());
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
	@BeanReference(type = GroupPersistence.class)
	protected GroupPersistence groupPersistence;
	@BeanReference(type = OrganizationPersistence.class)
	protected OrganizationPersistence organizationPersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	@BeanReference(type = UserGroupRolePersistence.class)
	protected UserGroupRolePersistence userGroupRolePersistence;
	protected ContainsCorpEntry containsCorpEntry;
	protected AddCorpEntry addCorpEntry;
	protected ClearCorpEntries clearCorpEntries;
	protected RemoveCorpEntry removeCorpEntry;

	protected class ContainsCorpEntry {
		protected ContainsCorpEntry() {
			_mappingSqlQuery = MappingSqlQueryFactoryUtil.getMappingSqlQuery(getDataSource(),
					_SQL_CONTAINSCORPENTRY,
					new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT },
					RowMapper.COUNT);
		}

		protected boolean contains(long corpGroupId, long corpEntryId) {
			List<Integer> results = _mappingSqlQuery.execute(new Object[] {
						new Long(corpGroupId), new Long(corpEntryId)
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

	protected class AddCorpEntry {
		protected AddCorpEntry() {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"INSERT INTO OSB_CorpEntry_CorpGroup (corpGroupId, corpEntryId) VALUES (?, ?)",
					new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT });
		}

		protected void add(long corpGroupId, long corpEntryId)
			throws SystemException {
			if (!containsCorpEntry.contains(corpGroupId, corpEntryId)) {
				ModelListener<com.liferay.osb.model.CorpEntry>[] corpEntryListeners =
					corpEntryPersistence.getListeners();

				for (ModelListener<CorpGroup> listener : listeners) {
					listener.onBeforeAddAssociation(corpGroupId,
						com.liferay.osb.model.CorpEntry.class.getName(),
						corpEntryId);
				}

				for (ModelListener<com.liferay.osb.model.CorpEntry> listener : corpEntryListeners) {
					listener.onBeforeAddAssociation(corpEntryId,
						CorpGroup.class.getName(), corpGroupId);
				}

				_sqlUpdate.update(new Object[] {
						new Long(corpGroupId), new Long(corpEntryId)
					});

				for (ModelListener<CorpGroup> listener : listeners) {
					listener.onAfterAddAssociation(corpGroupId,
						com.liferay.osb.model.CorpEntry.class.getName(),
						corpEntryId);
				}

				for (ModelListener<com.liferay.osb.model.CorpEntry> listener : corpEntryListeners) {
					listener.onAfterAddAssociation(corpEntryId,
						CorpGroup.class.getName(), corpGroupId);
				}
			}
		}

		private SqlUpdate _sqlUpdate;
	}

	protected class ClearCorpEntries {
		protected ClearCorpEntries() {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"DELETE FROM OSB_CorpEntry_CorpGroup WHERE corpGroupId = ?",
					new int[] { java.sql.Types.BIGINT });
		}

		protected void clear(long corpGroupId) throws SystemException {
			ModelListener<com.liferay.osb.model.CorpEntry>[] corpEntryListeners = corpEntryPersistence.getListeners();

			List<com.liferay.osb.model.CorpEntry> corpEntries = null;

			if ((listeners.length > 0) || (corpEntryListeners.length > 0)) {
				corpEntries = getCorpEntries(corpGroupId);

				for (com.liferay.osb.model.CorpEntry corpEntry : corpEntries) {
					for (ModelListener<CorpGroup> listener : listeners) {
						listener.onBeforeRemoveAssociation(corpGroupId,
							com.liferay.osb.model.CorpEntry.class.getName(),
							corpEntry.getPrimaryKey());
					}

					for (ModelListener<com.liferay.osb.model.CorpEntry> listener : corpEntryListeners) {
						listener.onBeforeRemoveAssociation(corpEntry.getPrimaryKey(),
							CorpGroup.class.getName(), corpGroupId);
					}
				}
			}

			_sqlUpdate.update(new Object[] { new Long(corpGroupId) });

			if ((listeners.length > 0) || (corpEntryListeners.length > 0)) {
				for (com.liferay.osb.model.CorpEntry corpEntry : corpEntries) {
					for (ModelListener<CorpGroup> listener : listeners) {
						listener.onAfterRemoveAssociation(corpGroupId,
							com.liferay.osb.model.CorpEntry.class.getName(),
							corpEntry.getPrimaryKey());
					}

					for (ModelListener<com.liferay.osb.model.CorpEntry> listener : corpEntryListeners) {
						listener.onAfterRemoveAssociation(corpEntry.getPrimaryKey(),
							CorpGroup.class.getName(), corpGroupId);
					}
				}
			}
		}

		private SqlUpdate _sqlUpdate;
	}

	protected class RemoveCorpEntry {
		protected RemoveCorpEntry() {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"DELETE FROM OSB_CorpEntry_CorpGroup WHERE corpGroupId = ? AND corpEntryId = ?",
					new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT });
		}

		protected void remove(long corpGroupId, long corpEntryId)
			throws SystemException {
			if (containsCorpEntry.contains(corpGroupId, corpEntryId)) {
				ModelListener<com.liferay.osb.model.CorpEntry>[] corpEntryListeners =
					corpEntryPersistence.getListeners();

				for (ModelListener<CorpGroup> listener : listeners) {
					listener.onBeforeRemoveAssociation(corpGroupId,
						com.liferay.osb.model.CorpEntry.class.getName(),
						corpEntryId);
				}

				for (ModelListener<com.liferay.osb.model.CorpEntry> listener : corpEntryListeners) {
					listener.onBeforeRemoveAssociation(corpEntryId,
						CorpGroup.class.getName(), corpGroupId);
				}

				_sqlUpdate.update(new Object[] {
						new Long(corpGroupId), new Long(corpEntryId)
					});

				for (ModelListener<CorpGroup> listener : listeners) {
					listener.onAfterRemoveAssociation(corpGroupId,
						com.liferay.osb.model.CorpEntry.class.getName(),
						corpEntryId);
				}

				for (ModelListener<com.liferay.osb.model.CorpEntry> listener : corpEntryListeners) {
					listener.onAfterRemoveAssociation(corpEntryId,
						CorpGroup.class.getName(), corpGroupId);
				}
			}
		}

		private SqlUpdate _sqlUpdate;
	}

	private static final String _SQL_SELECT_CORPGROUP = "SELECT corpGroup FROM CorpGroup corpGroup";
	private static final String _SQL_SELECT_CORPGROUP_WHERE = "SELECT corpGroup FROM CorpGroup corpGroup WHERE ";
	private static final String _SQL_COUNT_CORPGROUP = "SELECT COUNT(corpGroup) FROM CorpGroup corpGroup";
	private static final String _SQL_COUNT_CORPGROUP_WHERE = "SELECT COUNT(corpGroup) FROM CorpGroup corpGroup WHERE ";
	private static final String _SQL_GETCORPENTRIES = "SELECT {OSB_CorpEntry.*} FROM OSB_CorpEntry INNER JOIN OSB_CorpEntry_CorpGroup ON (OSB_CorpEntry_CorpGroup.corpEntryId = OSB_CorpEntry.corpEntryId) WHERE (OSB_CorpEntry_CorpGroup.corpGroupId = ?)";
	private static final String _SQL_GETCORPENTRIESSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM OSB_CorpEntry_CorpGroup WHERE corpGroupId = ?";
	private static final String _SQL_CONTAINSCORPENTRY = "SELECT COUNT(*) AS COUNT_VALUE FROM OSB_CorpEntry_CorpGroup WHERE corpGroupId = ? AND corpEntryId = ?";
	private static final String _FINDER_COLUMN_NAME_NAME_1 = "corpGroup.name IS NULL";
	private static final String _FINDER_COLUMN_NAME_NAME_2 = "corpGroup.name = ?";
	private static final String _FINDER_COLUMN_NAME_NAME_3 = "(corpGroup.name IS NULL OR corpGroup.name = ?)";
	private static final String _FINDER_COLUMN_ORGANIZATIONID_ORGANIZATIONID_2 = "corpGroup.organizationId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "corpGroup.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CorpGroup exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No CorpGroup exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(CorpGroupPersistenceImpl.class);
	private static CorpGroup _nullCorpGroup = new CorpGroupImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<CorpGroup> toCacheModel() {
				return _nullCorpGroupCacheModel;
			}
		};

	private static CacheModel<CorpGroup> _nullCorpGroupCacheModel = new CacheModel<CorpGroup>() {
			public CorpGroup toEntityModel() {
				return _nullCorpGroup;
			}
		};
}