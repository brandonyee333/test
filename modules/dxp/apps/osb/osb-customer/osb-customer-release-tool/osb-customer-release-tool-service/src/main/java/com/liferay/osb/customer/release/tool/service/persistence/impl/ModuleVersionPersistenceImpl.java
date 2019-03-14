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

package com.liferay.osb.customer.release.tool.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.customer.release.tool.exception.NoSuchModuleVersionException;
import com.liferay.osb.customer.release.tool.model.ModuleVersion;
import com.liferay.osb.customer.release.tool.model.impl.ModuleVersionImpl;
import com.liferay.osb.customer.release.tool.model.impl.ModuleVersionModelImpl;
import com.liferay.osb.customer.release.tool.service.persistence.ModuleVersionPersistence;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the module version service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class ModuleVersionPersistenceImpl
	extends BasePersistenceImpl<ModuleVersion>
	implements ModuleVersionPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>ModuleVersionUtil</code> to access the module version persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		ModuleVersionImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public ModuleVersionPersistenceImpl() {
		setModelClass(ModuleVersion.class);

		try {
			Field field = BasePersistenceImpl.class.getDeclaredField(
				"_dbColumnNames");

			field.setAccessible(true);

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("group", "group_");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the module version in the entity cache if it is enabled.
	 *
	 * @param moduleVersion the module version
	 */
	@Override
	public void cacheResult(ModuleVersion moduleVersion) {
		entityCache.putResult(
			ModuleVersionModelImpl.ENTITY_CACHE_ENABLED,
			ModuleVersionImpl.class, moduleVersion.getPrimaryKey(),
			moduleVersion);

		moduleVersion.resetOriginalValues();
	}

	/**
	 * Caches the module versions in the entity cache if it is enabled.
	 *
	 * @param moduleVersions the module versions
	 */
	@Override
	public void cacheResult(List<ModuleVersion> moduleVersions) {
		for (ModuleVersion moduleVersion : moduleVersions) {
			if (entityCache.getResult(
					ModuleVersionModelImpl.ENTITY_CACHE_ENABLED,
					ModuleVersionImpl.class, moduleVersion.getPrimaryKey()) ==
						null) {

				cacheResult(moduleVersion);
			}
			else {
				moduleVersion.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all module versions.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ModuleVersionImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the module version.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ModuleVersion moduleVersion) {
		entityCache.removeResult(
			ModuleVersionModelImpl.ENTITY_CACHE_ENABLED,
			ModuleVersionImpl.class, moduleVersion.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<ModuleVersion> moduleVersions) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ModuleVersion moduleVersion : moduleVersions) {
			entityCache.removeResult(
				ModuleVersionModelImpl.ENTITY_CACHE_ENABLED,
				ModuleVersionImpl.class, moduleVersion.getPrimaryKey());
		}
	}

	/**
	 * Creates a new module version with the primary key. Does not add the module version to the database.
	 *
	 * @param moduleVersionId the primary key for the new module version
	 * @return the new module version
	 */
	@Override
	public ModuleVersion create(long moduleVersionId) {
		ModuleVersion moduleVersion = new ModuleVersionImpl();

		moduleVersion.setNew(true);
		moduleVersion.setPrimaryKey(moduleVersionId);

		return moduleVersion;
	}

	/**
	 * Removes the module version with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param moduleVersionId the primary key of the module version
	 * @return the module version that was removed
	 * @throws NoSuchModuleVersionException if a module version with the primary key could not be found
	 */
	@Override
	public ModuleVersion remove(long moduleVersionId)
		throws NoSuchModuleVersionException {

		return remove((Serializable)moduleVersionId);
	}

	/**
	 * Removes the module version with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the module version
	 * @return the module version that was removed
	 * @throws NoSuchModuleVersionException if a module version with the primary key could not be found
	 */
	@Override
	public ModuleVersion remove(Serializable primaryKey)
		throws NoSuchModuleVersionException {

		Session session = null;

		try {
			session = openSession();

			ModuleVersion moduleVersion = (ModuleVersion)session.get(
				ModuleVersionImpl.class, primaryKey);

			if (moduleVersion == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchModuleVersionException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(moduleVersion);
		}
		catch (NoSuchModuleVersionException nsee) {
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
	protected ModuleVersion removeImpl(ModuleVersion moduleVersion) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(moduleVersion)) {
				moduleVersion = (ModuleVersion)session.get(
					ModuleVersionImpl.class, moduleVersion.getPrimaryKeyObj());
			}

			if (moduleVersion != null) {
				session.delete(moduleVersion);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (moduleVersion != null) {
			clearCache(moduleVersion);
		}

		return moduleVersion;
	}

	@Override
	public ModuleVersion updateImpl(ModuleVersion moduleVersion) {
		boolean isNew = moduleVersion.isNew();

		Session session = null;

		try {
			session = openSession();

			if (moduleVersion.isNew()) {
				session.save(moduleVersion);

				moduleVersion.setNew(false);
			}
			else {
				moduleVersion = (ModuleVersion)session.merge(moduleVersion);
			}
		}
		catch (Exception e) {
			throw processException(e);
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
			ModuleVersionModelImpl.ENTITY_CACHE_ENABLED,
			ModuleVersionImpl.class, moduleVersion.getPrimaryKey(),
			moduleVersion, false);

		moduleVersion.resetOriginalValues();

		return moduleVersion;
	}

	/**
	 * Returns the module version with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the module version
	 * @return the module version
	 * @throws NoSuchModuleVersionException if a module version with the primary key could not be found
	 */
	@Override
	public ModuleVersion findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModuleVersionException {

		ModuleVersion moduleVersion = fetchByPrimaryKey(primaryKey);

		if (moduleVersion == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchModuleVersionException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return moduleVersion;
	}

	/**
	 * Returns the module version with the primary key or throws a <code>NoSuchModuleVersionException</code> if it could not be found.
	 *
	 * @param moduleVersionId the primary key of the module version
	 * @return the module version
	 * @throws NoSuchModuleVersionException if a module version with the primary key could not be found
	 */
	@Override
	public ModuleVersion findByPrimaryKey(long moduleVersionId)
		throws NoSuchModuleVersionException {

		return findByPrimaryKey((Serializable)moduleVersionId);
	}

	/**
	 * Returns the module version with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the module version
	 * @return the module version, or <code>null</code> if a module version with the primary key could not be found
	 */
	@Override
	public ModuleVersion fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			ModuleVersionModelImpl.ENTITY_CACHE_ENABLED,
			ModuleVersionImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ModuleVersion moduleVersion = (ModuleVersion)serializable;

		if (moduleVersion == null) {
			Session session = null;

			try {
				session = openSession();

				moduleVersion = (ModuleVersion)session.get(
					ModuleVersionImpl.class, primaryKey);

				if (moduleVersion != null) {
					cacheResult(moduleVersion);
				}
				else {
					entityCache.putResult(
						ModuleVersionModelImpl.ENTITY_CACHE_ENABLED,
						ModuleVersionImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(
					ModuleVersionModelImpl.ENTITY_CACHE_ENABLED,
					ModuleVersionImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return moduleVersion;
	}

	/**
	 * Returns the module version with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param moduleVersionId the primary key of the module version
	 * @return the module version, or <code>null</code> if a module version with the primary key could not be found
	 */
	@Override
	public ModuleVersion fetchByPrimaryKey(long moduleVersionId) {
		return fetchByPrimaryKey((Serializable)moduleVersionId);
	}

	@Override
	public Map<Serializable, ModuleVersion> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ModuleVersion> map =
			new HashMap<Serializable, ModuleVersion>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ModuleVersion moduleVersion = fetchByPrimaryKey(primaryKey);

			if (moduleVersion != null) {
				map.put(primaryKey, moduleVersion);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				ModuleVersionModelImpl.ENTITY_CACHE_ENABLED,
				ModuleVersionImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ModuleVersion)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		query.append(_SQL_SELECT_MODULEVERSION_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append((long)primaryKey);

			query.append(",");
		}

		query.setIndex(query.index() - 1);

		query.append(")");

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (ModuleVersion moduleVersion : (List<ModuleVersion>)q.list()) {
				map.put(moduleVersion.getPrimaryKeyObj(), moduleVersion);

				cacheResult(moduleVersion);

				uncachedPrimaryKeys.remove(moduleVersion.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					ModuleVersionModelImpl.ENTITY_CACHE_ENABLED,
					ModuleVersionImpl.class, primaryKey, nullModel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the module versions.
	 *
	 * @return the module versions
	 */
	@Override
	public List<ModuleVersion> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the module versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ModuleVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of module versions
	 * @param end the upper bound of the range of module versions (not inclusive)
	 * @return the range of module versions
	 */
	@Override
	public List<ModuleVersion> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the module versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ModuleVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of module versions
	 * @param end the upper bound of the range of module versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of module versions
	 */
	@Override
	public List<ModuleVersion> findAll(
		int start, int end,
		OrderByComparator<ModuleVersion> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the module versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ModuleVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of module versions
	 * @param end the upper bound of the range of module versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of module versions
	 */
	@Override
	public List<ModuleVersion> findAll(
		int start, int end, OrderByComparator<ModuleVersion> orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindAll;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<ModuleVersion> list = null;

		if (retrieveFromCache) {
			list = (List<ModuleVersion>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_MODULEVERSION);

				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_MODULEVERSION;

				if (pagination) {
					sql = sql.concat(ModuleVersionModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ModuleVersion>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ModuleVersion>)QueryUtil.list(
						q, getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the module versions from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ModuleVersion moduleVersion : findAll()) {
			remove(moduleVersion);
		}
	}

	/**
	 * Returns the number of module versions.
	 *
	 * @return the number of module versions
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_MODULEVERSION);

				count = (Long)q.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception e) {
				finderCache.removeResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return ModuleVersionModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the module version persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			ModuleVersionModelImpl.ENTITY_CACHE_ENABLED,
			ModuleVersionModelImpl.FINDER_CACHE_ENABLED,
			ModuleVersionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			ModuleVersionModelImpl.ENTITY_CACHE_ENABLED,
			ModuleVersionModelImpl.FINDER_CACHE_ENABLED,
			ModuleVersionImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);

		_finderPathCountAll = new FinderPath(
			ModuleVersionModelImpl.ENTITY_CACHE_ENABLED,
			ModuleVersionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);
	}

	public void destroy() {
		entityCache.removeCache(ModuleVersionImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_MODULEVERSION =
		"SELECT moduleVersion FROM ModuleVersion moduleVersion";

	private static final String _SQL_SELECT_MODULEVERSION_WHERE_PKS_IN =
		"SELECT moduleVersion FROM ModuleVersion moduleVersion WHERE moduleVersionId IN (";

	private static final String _SQL_COUNT_MODULEVERSION =
		"SELECT COUNT(moduleVersion) FROM ModuleVersion moduleVersion";

	private static final String _ORDER_BY_ENTITY_ALIAS = "moduleVersion.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No ModuleVersion exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		ModuleVersionPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"group"});

}