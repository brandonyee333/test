/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.community.meetup.service.persistence.impl;

import com.liferay.osb.community.meetup.exception.NoSuchMeetupGroupException;
import com.liferay.osb.community.meetup.model.MeetupGroup;
import com.liferay.osb.community.meetup.model.impl.MeetupGroupImpl;
import com.liferay.osb.community.meetup.model.impl.MeetupGroupModelImpl;
import com.liferay.osb.community.meetup.service.persistence.MeetupGroupPersistence;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the meetup group service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Jamie Sammons
 * @generated
 */
public class MeetupGroupPersistenceImpl
	extends BasePersistenceImpl<MeetupGroup> implements MeetupGroupPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>MeetupGroupUtil</code> to access the meetup group persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		MeetupGroupImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public MeetupGroupPersistenceImpl() {
		setModelClass(MeetupGroup.class);
	}

	/**
	 * Caches the meetup group in the entity cache if it is enabled.
	 *
	 * @param meetupGroup the meetup group
	 */
	@Override
	public void cacheResult(MeetupGroup meetupGroup) {
		entityCache.putResult(
			MeetupGroupModelImpl.ENTITY_CACHE_ENABLED, MeetupGroupImpl.class,
			meetupGroup.getPrimaryKey(), meetupGroup);

		meetupGroup.resetOriginalValues();
	}

	/**
	 * Caches the meetup groups in the entity cache if it is enabled.
	 *
	 * @param meetupGroups the meetup groups
	 */
	@Override
	public void cacheResult(List<MeetupGroup> meetupGroups) {
		for (MeetupGroup meetupGroup : meetupGroups) {
			if (entityCache.getResult(
					MeetupGroupModelImpl.ENTITY_CACHE_ENABLED,
					MeetupGroupImpl.class, meetupGroup.getPrimaryKey()) ==
						null) {

				cacheResult(meetupGroup);
			}
			else {
				meetupGroup.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all meetup groups.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(MeetupGroupImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the meetup group.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(MeetupGroup meetupGroup) {
		entityCache.removeResult(
			MeetupGroupModelImpl.ENTITY_CACHE_ENABLED, MeetupGroupImpl.class,
			meetupGroup.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<MeetupGroup> meetupGroups) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (MeetupGroup meetupGroup : meetupGroups) {
			entityCache.removeResult(
				MeetupGroupModelImpl.ENTITY_CACHE_ENABLED,
				MeetupGroupImpl.class, meetupGroup.getPrimaryKey());
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				MeetupGroupModelImpl.ENTITY_CACHE_ENABLED,
				MeetupGroupImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new meetup group with the primary key. Does not add the meetup group to the database.
	 *
	 * @param meetupGroupId the primary key for the new meetup group
	 * @return the new meetup group
	 */
	@Override
	public MeetupGroup create(long meetupGroupId) {
		MeetupGroup meetupGroup = new MeetupGroupImpl();

		meetupGroup.setNew(true);
		meetupGroup.setPrimaryKey(meetupGroupId);

		meetupGroup.setCompanyId(CompanyThreadLocal.getCompanyId());

		return meetupGroup;
	}

	/**
	 * Removes the meetup group with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param meetupGroupId the primary key of the meetup group
	 * @return the meetup group that was removed
	 * @throws NoSuchMeetupGroupException if a meetup group with the primary key could not be found
	 */
	@Override
	public MeetupGroup remove(long meetupGroupId)
		throws NoSuchMeetupGroupException {

		return remove((Serializable)meetupGroupId);
	}

	/**
	 * Removes the meetup group with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the meetup group
	 * @return the meetup group that was removed
	 * @throws NoSuchMeetupGroupException if a meetup group with the primary key could not be found
	 */
	@Override
	public MeetupGroup remove(Serializable primaryKey)
		throws NoSuchMeetupGroupException {

		Session session = null;

		try {
			session = openSession();

			MeetupGroup meetupGroup = (MeetupGroup)session.get(
				MeetupGroupImpl.class, primaryKey);

			if (meetupGroup == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchMeetupGroupException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(meetupGroup);
		}
		catch (NoSuchMeetupGroupException noSuchEntityException) {
			throw noSuchEntityException;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected MeetupGroup removeImpl(MeetupGroup meetupGroup) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(meetupGroup)) {
				meetupGroup = (MeetupGroup)session.get(
					MeetupGroupImpl.class, meetupGroup.getPrimaryKeyObj());
			}

			if (meetupGroup != null) {
				session.delete(meetupGroup);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (meetupGroup != null) {
			clearCache(meetupGroup);
		}

		return meetupGroup;
	}

	@Override
	public MeetupGroup updateImpl(MeetupGroup meetupGroup) {
		boolean isNew = meetupGroup.isNew();

		if (!(meetupGroup instanceof MeetupGroupModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(meetupGroup.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(meetupGroup);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in meetupGroup proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom MeetupGroup implementation " +
					meetupGroup.getClass());
		}

		MeetupGroupModelImpl meetupGroupModelImpl =
			(MeetupGroupModelImpl)meetupGroup;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (meetupGroup.getCreateDate() == null)) {
			if (serviceContext == null) {
				meetupGroup.setCreateDate(now);
			}
			else {
				meetupGroup.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!meetupGroupModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				meetupGroup.setModifiedDate(now);
			}
			else {
				meetupGroup.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (meetupGroup.isNew()) {
				session.save(meetupGroup);

				meetupGroup.setNew(false);
			}
			else {
				meetupGroup = (MeetupGroup)session.merge(meetupGroup);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew) {
			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}

		entityCache.putResult(
			MeetupGroupModelImpl.ENTITY_CACHE_ENABLED, MeetupGroupImpl.class,
			meetupGroup.getPrimaryKey(), meetupGroup, false);

		meetupGroup.resetOriginalValues();

		return meetupGroup;
	}

	/**
	 * Returns the meetup group with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the meetup group
	 * @return the meetup group
	 * @throws NoSuchMeetupGroupException if a meetup group with the primary key could not be found
	 */
	@Override
	public MeetupGroup findByPrimaryKey(Serializable primaryKey)
		throws NoSuchMeetupGroupException {

		MeetupGroup meetupGroup = fetchByPrimaryKey(primaryKey);

		if (meetupGroup == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchMeetupGroupException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return meetupGroup;
	}

	/**
	 * Returns the meetup group with the primary key or throws a <code>NoSuchMeetupGroupException</code> if it could not be found.
	 *
	 * @param meetupGroupId the primary key of the meetup group
	 * @return the meetup group
	 * @throws NoSuchMeetupGroupException if a meetup group with the primary key could not be found
	 */
	@Override
	public MeetupGroup findByPrimaryKey(long meetupGroupId)
		throws NoSuchMeetupGroupException {

		return findByPrimaryKey((Serializable)meetupGroupId);
	}

	/**
	 * Returns the meetup group with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the meetup group
	 * @return the meetup group, or <code>null</code> if a meetup group with the primary key could not be found
	 */
	@Override
	public MeetupGroup fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			MeetupGroupModelImpl.ENTITY_CACHE_ENABLED, MeetupGroupImpl.class,
			primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		MeetupGroup meetupGroup = (MeetupGroup)serializable;

		if (meetupGroup == null) {
			Session session = null;

			try {
				session = openSession();

				meetupGroup = (MeetupGroup)session.get(
					MeetupGroupImpl.class, primaryKey);

				if (meetupGroup != null) {
					cacheResult(meetupGroup);
				}
				else {
					entityCache.putResult(
						MeetupGroupModelImpl.ENTITY_CACHE_ENABLED,
						MeetupGroupImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception exception) {
				entityCache.removeResult(
					MeetupGroupModelImpl.ENTITY_CACHE_ENABLED,
					MeetupGroupImpl.class, primaryKey);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return meetupGroup;
	}

	/**
	 * Returns the meetup group with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param meetupGroupId the primary key of the meetup group
	 * @return the meetup group, or <code>null</code> if a meetup group with the primary key could not be found
	 */
	@Override
	public MeetupGroup fetchByPrimaryKey(long meetupGroupId) {
		return fetchByPrimaryKey((Serializable)meetupGroupId);
	}

	@Override
	public Map<Serializable, MeetupGroup> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, MeetupGroup> map =
			new HashMap<Serializable, MeetupGroup>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			MeetupGroup meetupGroup = fetchByPrimaryKey(primaryKey);

			if (meetupGroup != null) {
				map.put(primaryKey, meetupGroup);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				MeetupGroupModelImpl.ENTITY_CACHE_ENABLED,
				MeetupGroupImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (MeetupGroup)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler sb = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		sb.append(_SQL_SELECT_MEETUPGROUP_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			sb.append((long)primaryKey);

			sb.append(",");
		}

		sb.setIndex(sb.index() - 1);

		sb.append(")");

		String sql = sb.toString();

		Session session = null;

		try {
			session = openSession();

			Query query = session.createQuery(sql);

			for (MeetupGroup meetupGroup : (List<MeetupGroup>)query.list()) {
				map.put(meetupGroup.getPrimaryKeyObj(), meetupGroup);

				cacheResult(meetupGroup);

				uncachedPrimaryKeys.remove(meetupGroup.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					MeetupGroupModelImpl.ENTITY_CACHE_ENABLED,
					MeetupGroupImpl.class, primaryKey, nullModel);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the meetup groups.
	 *
	 * @return the meetup groups
	 */
	@Override
	public List<MeetupGroup> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the meetup groups.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MeetupGroupModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of meetup groups
	 * @param end the upper bound of the range of meetup groups (not inclusive)
	 * @return the range of meetup groups
	 */
	@Override
	public List<MeetupGroup> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the meetup groups.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MeetupGroupModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of meetup groups
	 * @param end the upper bound of the range of meetup groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of meetup groups
	 */
	@Override
	public List<MeetupGroup> findAll(
		int start, int end, OrderByComparator<MeetupGroup> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the meetup groups.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MeetupGroupModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of meetup groups
	 * @param end the upper bound of the range of meetup groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of meetup groups
	 */
	@Override
	public List<MeetupGroup> findAll(
		int start, int end, OrderByComparator<MeetupGroup> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindAll;
				finderArgs = FINDER_ARGS_EMPTY;
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<MeetupGroup> list = null;

		if (useFinderCache) {
			list = (List<MeetupGroup>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_MEETUPGROUP);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_MEETUPGROUP;

				sql = sql.concat(MeetupGroupModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<MeetupGroup>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the meetup groups from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (MeetupGroup meetupGroup : findAll()) {
			remove(meetupGroup);
		}
	}

	/**
	 * Returns the number of meetup groups.
	 *
	 * @return the number of meetup groups
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_MEETUPGROUP);

				count = (Long)query.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return MeetupGroupModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the meetup group persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			MeetupGroupModelImpl.ENTITY_CACHE_ENABLED,
			MeetupGroupModelImpl.FINDER_CACHE_ENABLED, MeetupGroupImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			MeetupGroupModelImpl.ENTITY_CACHE_ENABLED,
			MeetupGroupModelImpl.FINDER_CACHE_ENABLED, MeetupGroupImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			MeetupGroupModelImpl.ENTITY_CACHE_ENABLED,
			MeetupGroupModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);
	}

	public void destroy() {
		entityCache.removeCache(MeetupGroupImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_MEETUPGROUP =
		"SELECT meetupGroup FROM MeetupGroup meetupGroup";

	private static final String _SQL_SELECT_MEETUPGROUP_WHERE_PKS_IN =
		"SELECT meetupGroup FROM MeetupGroup meetupGroup WHERE meetupGroupId IN (";

	private static final String _SQL_COUNT_MEETUPGROUP =
		"SELECT COUNT(meetupGroup) FROM MeetupGroup meetupGroup";

	private static final String _ORDER_BY_ENTITY_ALIAS = "meetupGroup.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No MeetupGroup exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		MeetupGroupPersistenceImpl.class);

}