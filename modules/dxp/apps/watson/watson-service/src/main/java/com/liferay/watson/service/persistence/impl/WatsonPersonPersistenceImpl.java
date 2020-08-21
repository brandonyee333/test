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

package com.liferay.watson.service.persistence.impl;

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
import com.liferay.watson.exception.NoSuchPersonException;
import com.liferay.watson.model.WatsonPerson;
import com.liferay.watson.model.impl.WatsonPersonImpl;
import com.liferay.watson.model.impl.WatsonPersonModelImpl;
import com.liferay.watson.service.persistence.WatsonPersonPersistence;

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
 * The persistence implementation for the watson person service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @generated
 */
public class WatsonPersonPersistenceImpl
	extends BasePersistenceImpl<WatsonPerson>
	implements WatsonPersonPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>WatsonPersonUtil</code> to access the watson person persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		WatsonPersonImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public WatsonPersonPersistenceImpl() {
		setModelClass(WatsonPerson.class);
	}

	/**
	 * Caches the watson person in the entity cache if it is enabled.
	 *
	 * @param watsonPerson the watson person
	 */
	@Override
	public void cacheResult(WatsonPerson watsonPerson) {
		entityCache.putResult(
			WatsonPersonModelImpl.ENTITY_CACHE_ENABLED, WatsonPersonImpl.class,
			watsonPerson.getPrimaryKey(), watsonPerson);

		watsonPerson.resetOriginalValues();
	}

	/**
	 * Caches the watson persons in the entity cache if it is enabled.
	 *
	 * @param watsonPersons the watson persons
	 */
	@Override
	public void cacheResult(List<WatsonPerson> watsonPersons) {
		for (WatsonPerson watsonPerson : watsonPersons) {
			if (entityCache.getResult(
					WatsonPersonModelImpl.ENTITY_CACHE_ENABLED,
					WatsonPersonImpl.class, watsonPerson.getPrimaryKey()) ==
						null) {

				cacheResult(watsonPerson);
			}
			else {
				watsonPerson.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all watson persons.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(WatsonPersonImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the watson person.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(WatsonPerson watsonPerson) {
		entityCache.removeResult(
			WatsonPersonModelImpl.ENTITY_CACHE_ENABLED, WatsonPersonImpl.class,
			watsonPerson.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<WatsonPerson> watsonPersons) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (WatsonPerson watsonPerson : watsonPersons) {
			entityCache.removeResult(
				WatsonPersonModelImpl.ENTITY_CACHE_ENABLED,
				WatsonPersonImpl.class, watsonPerson.getPrimaryKey());
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				WatsonPersonModelImpl.ENTITY_CACHE_ENABLED,
				WatsonPersonImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new watson person with the primary key. Does not add the watson person to the database.
	 *
	 * @param watsonPersonId the primary key for the new watson person
	 * @return the new watson person
	 */
	@Override
	public WatsonPerson create(long watsonPersonId) {
		WatsonPerson watsonPerson = new WatsonPersonImpl();

		watsonPerson.setNew(true);
		watsonPerson.setPrimaryKey(watsonPersonId);

		watsonPerson.setCompanyId(CompanyThreadLocal.getCompanyId());

		return watsonPerson;
	}

	/**
	 * Removes the watson person with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonPersonId the primary key of the watson person
	 * @return the watson person that was removed
	 * @throws NoSuchPersonException if a watson person with the primary key could not be found
	 */
	@Override
	public WatsonPerson remove(long watsonPersonId)
		throws NoSuchPersonException {

		return remove((Serializable)watsonPersonId);
	}

	/**
	 * Removes the watson person with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the watson person
	 * @return the watson person that was removed
	 * @throws NoSuchPersonException if a watson person with the primary key could not be found
	 */
	@Override
	public WatsonPerson remove(Serializable primaryKey)
		throws NoSuchPersonException {

		Session session = null;

		try {
			session = openSession();

			WatsonPerson watsonPerson = (WatsonPerson)session.get(
				WatsonPersonImpl.class, primaryKey);

			if (watsonPerson == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchPersonException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(watsonPerson);
		}
		catch (NoSuchPersonException noSuchEntityException) {
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
	protected WatsonPerson removeImpl(WatsonPerson watsonPerson) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(watsonPerson)) {
				watsonPerson = (WatsonPerson)session.get(
					WatsonPersonImpl.class, watsonPerson.getPrimaryKeyObj());
			}

			if (watsonPerson != null) {
				session.delete(watsonPerson);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (watsonPerson != null) {
			clearCache(watsonPerson);
		}

		return watsonPerson;
	}

	@Override
	public WatsonPerson updateImpl(WatsonPerson watsonPerson) {
		boolean isNew = watsonPerson.isNew();

		if (!(watsonPerson instanceof WatsonPersonModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(watsonPerson.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					watsonPerson);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in watsonPerson proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom WatsonPerson implementation " +
					watsonPerson.getClass());
		}

		WatsonPersonModelImpl watsonPersonModelImpl =
			(WatsonPersonModelImpl)watsonPerson;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (watsonPerson.getCreateDate() == null)) {
			if (serviceContext == null) {
				watsonPerson.setCreateDate(now);
			}
			else {
				watsonPerson.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!watsonPersonModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				watsonPerson.setModifiedDate(now);
			}
			else {
				watsonPerson.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(watsonPerson);

				watsonPerson.setNew(false);
			}
			else {
				watsonPerson = (WatsonPerson)session.merge(watsonPerson);
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
			WatsonPersonModelImpl.ENTITY_CACHE_ENABLED, WatsonPersonImpl.class,
			watsonPerson.getPrimaryKey(), watsonPerson, false);

		watsonPerson.resetOriginalValues();

		return watsonPerson;
	}

	/**
	 * Returns the watson person with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the watson person
	 * @return the watson person
	 * @throws NoSuchPersonException if a watson person with the primary key could not be found
	 */
	@Override
	public WatsonPerson findByPrimaryKey(Serializable primaryKey)
		throws NoSuchPersonException {

		WatsonPerson watsonPerson = fetchByPrimaryKey(primaryKey);

		if (watsonPerson == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchPersonException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return watsonPerson;
	}

	/**
	 * Returns the watson person with the primary key or throws a <code>NoSuchPersonException</code> if it could not be found.
	 *
	 * @param watsonPersonId the primary key of the watson person
	 * @return the watson person
	 * @throws NoSuchPersonException if a watson person with the primary key could not be found
	 */
	@Override
	public WatsonPerson findByPrimaryKey(long watsonPersonId)
		throws NoSuchPersonException {

		return findByPrimaryKey((Serializable)watsonPersonId);
	}

	/**
	 * Returns the watson person with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the watson person
	 * @return the watson person, or <code>null</code> if a watson person with the primary key could not be found
	 */
	@Override
	public WatsonPerson fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			WatsonPersonModelImpl.ENTITY_CACHE_ENABLED, WatsonPersonImpl.class,
			primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		WatsonPerson watsonPerson = (WatsonPerson)serializable;

		if (watsonPerson == null) {
			Session session = null;

			try {
				session = openSession();

				watsonPerson = (WatsonPerson)session.get(
					WatsonPersonImpl.class, primaryKey);

				if (watsonPerson != null) {
					cacheResult(watsonPerson);
				}
				else {
					entityCache.putResult(
						WatsonPersonModelImpl.ENTITY_CACHE_ENABLED,
						WatsonPersonImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception exception) {
				entityCache.removeResult(
					WatsonPersonModelImpl.ENTITY_CACHE_ENABLED,
					WatsonPersonImpl.class, primaryKey);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return watsonPerson;
	}

	/**
	 * Returns the watson person with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param watsonPersonId the primary key of the watson person
	 * @return the watson person, or <code>null</code> if a watson person with the primary key could not be found
	 */
	@Override
	public WatsonPerson fetchByPrimaryKey(long watsonPersonId) {
		return fetchByPrimaryKey((Serializable)watsonPersonId);
	}

	@Override
	public Map<Serializable, WatsonPerson> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, WatsonPerson> map =
			new HashMap<Serializable, WatsonPerson>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			WatsonPerson watsonPerson = fetchByPrimaryKey(primaryKey);

			if (watsonPerson != null) {
				map.put(primaryKey, watsonPerson);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				WatsonPersonModelImpl.ENTITY_CACHE_ENABLED,
				WatsonPersonImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (WatsonPerson)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler sb = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		sb.append(_SQL_SELECT_WATSONPERSON_WHERE_PKS_IN);

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

			for (WatsonPerson watsonPerson : (List<WatsonPerson>)query.list()) {
				map.put(watsonPerson.getPrimaryKeyObj(), watsonPerson);

				cacheResult(watsonPerson);

				uncachedPrimaryKeys.remove(watsonPerson.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					WatsonPersonModelImpl.ENTITY_CACHE_ENABLED,
					WatsonPersonImpl.class, primaryKey, nullModel);
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
	 * Returns all the watson persons.
	 *
	 * @return the watson persons
	 */
	@Override
	public List<WatsonPerson> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the watson persons.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonPersonModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson persons
	 * @param end the upper bound of the range of watson persons (not inclusive)
	 * @return the range of watson persons
	 */
	@Override
	public List<WatsonPerson> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the watson persons.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonPersonModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson persons
	 * @param end the upper bound of the range of watson persons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of watson persons
	 */
	@Override
	public List<WatsonPerson> findAll(
		int start, int end, OrderByComparator<WatsonPerson> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the watson persons.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonPersonModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson persons
	 * @param end the upper bound of the range of watson persons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of watson persons
	 */
	@Override
	public List<WatsonPerson> findAll(
		int start, int end, OrderByComparator<WatsonPerson> orderByComparator,
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

		List<WatsonPerson> list = null;

		if (useFinderCache) {
			list = (List<WatsonPerson>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_WATSONPERSON);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_WATSONPERSON;

				sql = sql.concat(WatsonPersonModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<WatsonPerson>)QueryUtil.list(
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
	 * Removes all the watson persons from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (WatsonPerson watsonPerson : findAll()) {
			remove(watsonPerson);
		}
	}

	/**
	 * Returns the number of watson persons.
	 *
	 * @return the number of watson persons
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_WATSONPERSON);

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
		return WatsonPersonModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the watson person persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			WatsonPersonModelImpl.ENTITY_CACHE_ENABLED,
			WatsonPersonModelImpl.FINDER_CACHE_ENABLED, WatsonPersonImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			WatsonPersonModelImpl.ENTITY_CACHE_ENABLED,
			WatsonPersonModelImpl.FINDER_CACHE_ENABLED, WatsonPersonImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			WatsonPersonModelImpl.ENTITY_CACHE_ENABLED,
			WatsonPersonModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);
	}

	public void destroy() {
		entityCache.removeCache(WatsonPersonImpl.class.getName());

		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_WATSONPERSON =
		"SELECT watsonPerson FROM WatsonPerson watsonPerson";

	private static final String _SQL_SELECT_WATSONPERSON_WHERE_PKS_IN =
		"SELECT watsonPerson FROM WatsonPerson watsonPerson WHERE watsonPersonId IN (";

	private static final String _SQL_COUNT_WATSONPERSON =
		"SELECT COUNT(watsonPerson) FROM WatsonPerson watsonPerson";

	private static final String _ORDER_BY_ENTITY_ALIAS = "watsonPerson.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No WatsonPerson exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		WatsonPersonPersistenceImpl.class);

}