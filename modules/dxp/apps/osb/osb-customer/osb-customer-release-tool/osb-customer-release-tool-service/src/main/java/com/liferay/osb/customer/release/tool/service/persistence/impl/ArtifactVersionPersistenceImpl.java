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

import com.liferay.osb.customer.release.tool.exception.NoSuchArtifactVersionException;
import com.liferay.osb.customer.release.tool.model.ArtifactVersion;
import com.liferay.osb.customer.release.tool.model.impl.ArtifactVersionImpl;
import com.liferay.osb.customer.release.tool.model.impl.ArtifactVersionModelImpl;
import com.liferay.osb.customer.release.tool.service.persistence.ArtifactVersionPersistence;
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
 * The persistence implementation for the artifact version service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class ArtifactVersionPersistenceImpl
	extends BasePersistenceImpl<ArtifactVersion>
	implements ArtifactVersionPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>ArtifactVersionUtil</code> to access the artifact version persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		ArtifactVersionImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public ArtifactVersionPersistenceImpl() {
		setModelClass(ArtifactVersion.class);

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
	 * Caches the artifact version in the entity cache if it is enabled.
	 *
	 * @param artifactVersion the artifact version
	 */
	@Override
	public void cacheResult(ArtifactVersion artifactVersion) {
		entityCache.putResult(
			ArtifactVersionModelImpl.ENTITY_CACHE_ENABLED,
			ArtifactVersionImpl.class, artifactVersion.getPrimaryKey(),
			artifactVersion);

		artifactVersion.resetOriginalValues();
	}

	/**
	 * Caches the artifact versions in the entity cache if it is enabled.
	 *
	 * @param artifactVersions the artifact versions
	 */
	@Override
	public void cacheResult(List<ArtifactVersion> artifactVersions) {
		for (ArtifactVersion artifactVersion : artifactVersions) {
			if (entityCache.getResult(
					ArtifactVersionModelImpl.ENTITY_CACHE_ENABLED,
					ArtifactVersionImpl.class,
					artifactVersion.getPrimaryKey()) == null) {

				cacheResult(artifactVersion);
			}
			else {
				artifactVersion.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all artifact versions.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ArtifactVersionImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the artifact version.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ArtifactVersion artifactVersion) {
		entityCache.removeResult(
			ArtifactVersionModelImpl.ENTITY_CACHE_ENABLED,
			ArtifactVersionImpl.class, artifactVersion.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<ArtifactVersion> artifactVersions) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ArtifactVersion artifactVersion : artifactVersions) {
			entityCache.removeResult(
				ArtifactVersionModelImpl.ENTITY_CACHE_ENABLED,
				ArtifactVersionImpl.class, artifactVersion.getPrimaryKey());
		}
	}

	/**
	 * Creates a new artifact version with the primary key. Does not add the artifact version to the database.
	 *
	 * @param artifactVersionId the primary key for the new artifact version
	 * @return the new artifact version
	 */
	@Override
	public ArtifactVersion create(long artifactVersionId) {
		ArtifactVersion artifactVersion = new ArtifactVersionImpl();

		artifactVersion.setNew(true);
		artifactVersion.setPrimaryKey(artifactVersionId);

		return artifactVersion;
	}

	/**
	 * Removes the artifact version with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param artifactVersionId the primary key of the artifact version
	 * @return the artifact version that was removed
	 * @throws NoSuchArtifactVersionException if a artifact version with the primary key could not be found
	 */
	@Override
	public ArtifactVersion remove(long artifactVersionId)
		throws NoSuchArtifactVersionException {

		return remove((Serializable)artifactVersionId);
	}

	/**
	 * Removes the artifact version with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the artifact version
	 * @return the artifact version that was removed
	 * @throws NoSuchArtifactVersionException if a artifact version with the primary key could not be found
	 */
	@Override
	public ArtifactVersion remove(Serializable primaryKey)
		throws NoSuchArtifactVersionException {

		Session session = null;

		try {
			session = openSession();

			ArtifactVersion artifactVersion = (ArtifactVersion)session.get(
				ArtifactVersionImpl.class, primaryKey);

			if (artifactVersion == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchArtifactVersionException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(artifactVersion);
		}
		catch (NoSuchArtifactVersionException nsee) {
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
	protected ArtifactVersion removeImpl(ArtifactVersion artifactVersion) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(artifactVersion)) {
				artifactVersion = (ArtifactVersion)session.get(
					ArtifactVersionImpl.class,
					artifactVersion.getPrimaryKeyObj());
			}

			if (artifactVersion != null) {
				session.delete(artifactVersion);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (artifactVersion != null) {
			clearCache(artifactVersion);
		}

		return artifactVersion;
	}

	@Override
	public ArtifactVersion updateImpl(ArtifactVersion artifactVersion) {
		boolean isNew = artifactVersion.isNew();

		Session session = null;

		try {
			session = openSession();

			if (artifactVersion.isNew()) {
				session.save(artifactVersion);

				artifactVersion.setNew(false);
			}
			else {
				artifactVersion = (ArtifactVersion)session.merge(
					artifactVersion);
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
			ArtifactVersionModelImpl.ENTITY_CACHE_ENABLED,
			ArtifactVersionImpl.class, artifactVersion.getPrimaryKey(),
			artifactVersion, false);

		artifactVersion.resetOriginalValues();

		return artifactVersion;
	}

	/**
	 * Returns the artifact version with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the artifact version
	 * @return the artifact version
	 * @throws NoSuchArtifactVersionException if a artifact version with the primary key could not be found
	 */
	@Override
	public ArtifactVersion findByPrimaryKey(Serializable primaryKey)
		throws NoSuchArtifactVersionException {

		ArtifactVersion artifactVersion = fetchByPrimaryKey(primaryKey);

		if (artifactVersion == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchArtifactVersionException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return artifactVersion;
	}

	/**
	 * Returns the artifact version with the primary key or throws a <code>NoSuchArtifactVersionException</code> if it could not be found.
	 *
	 * @param artifactVersionId the primary key of the artifact version
	 * @return the artifact version
	 * @throws NoSuchArtifactVersionException if a artifact version with the primary key could not be found
	 */
	@Override
	public ArtifactVersion findByPrimaryKey(long artifactVersionId)
		throws NoSuchArtifactVersionException {

		return findByPrimaryKey((Serializable)artifactVersionId);
	}

	/**
	 * Returns the artifact version with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the artifact version
	 * @return the artifact version, or <code>null</code> if a artifact version with the primary key could not be found
	 */
	@Override
	public ArtifactVersion fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			ArtifactVersionModelImpl.ENTITY_CACHE_ENABLED,
			ArtifactVersionImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ArtifactVersion artifactVersion = (ArtifactVersion)serializable;

		if (artifactVersion == null) {
			Session session = null;

			try {
				session = openSession();

				artifactVersion = (ArtifactVersion)session.get(
					ArtifactVersionImpl.class, primaryKey);

				if (artifactVersion != null) {
					cacheResult(artifactVersion);
				}
				else {
					entityCache.putResult(
						ArtifactVersionModelImpl.ENTITY_CACHE_ENABLED,
						ArtifactVersionImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(
					ArtifactVersionModelImpl.ENTITY_CACHE_ENABLED,
					ArtifactVersionImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return artifactVersion;
	}

	/**
	 * Returns the artifact version with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param artifactVersionId the primary key of the artifact version
	 * @return the artifact version, or <code>null</code> if a artifact version with the primary key could not be found
	 */
	@Override
	public ArtifactVersion fetchByPrimaryKey(long artifactVersionId) {
		return fetchByPrimaryKey((Serializable)artifactVersionId);
	}

	@Override
	public Map<Serializable, ArtifactVersion> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ArtifactVersion> map =
			new HashMap<Serializable, ArtifactVersion>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ArtifactVersion artifactVersion = fetchByPrimaryKey(primaryKey);

			if (artifactVersion != null) {
				map.put(primaryKey, artifactVersion);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				ArtifactVersionModelImpl.ENTITY_CACHE_ENABLED,
				ArtifactVersionImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ArtifactVersion)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		query.append(_SQL_SELECT_ARTIFACTVERSION_WHERE_PKS_IN);

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

			for (ArtifactVersion artifactVersion :
					(List<ArtifactVersion>)q.list()) {

				map.put(artifactVersion.getPrimaryKeyObj(), artifactVersion);

				cacheResult(artifactVersion);

				uncachedPrimaryKeys.remove(artifactVersion.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					ArtifactVersionModelImpl.ENTITY_CACHE_ENABLED,
					ArtifactVersionImpl.class, primaryKey, nullModel);
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
	 * Returns all the artifact versions.
	 *
	 * @return the artifact versions
	 */
	@Override
	public List<ArtifactVersion> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the artifact versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ArtifactVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of artifact versions
	 * @param end the upper bound of the range of artifact versions (not inclusive)
	 * @return the range of artifact versions
	 */
	@Override
	public List<ArtifactVersion> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the artifact versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ArtifactVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of artifact versions
	 * @param end the upper bound of the range of artifact versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of artifact versions
	 */
	@Override
	public List<ArtifactVersion> findAll(
		int start, int end,
		OrderByComparator<ArtifactVersion> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the artifact versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ArtifactVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of artifact versions
	 * @param end the upper bound of the range of artifact versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of artifact versions
	 */
	@Override
	public List<ArtifactVersion> findAll(
		int start, int end,
		OrderByComparator<ArtifactVersion> orderByComparator,
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

		List<ArtifactVersion> list = null;

		if (retrieveFromCache) {
			list = (List<ArtifactVersion>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_ARTIFACTVERSION);

				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ARTIFACTVERSION;

				if (pagination) {
					sql = sql.concat(ArtifactVersionModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ArtifactVersion>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ArtifactVersion>)QueryUtil.list(
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
	 * Removes all the artifact versions from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ArtifactVersion artifactVersion : findAll()) {
			remove(artifactVersion);
		}
	}

	/**
	 * Returns the number of artifact versions.
	 *
	 * @return the number of artifact versions
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ARTIFACTVERSION);

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
		return ArtifactVersionModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the artifact version persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			ArtifactVersionModelImpl.ENTITY_CACHE_ENABLED,
			ArtifactVersionModelImpl.FINDER_CACHE_ENABLED,
			ArtifactVersionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			ArtifactVersionModelImpl.ENTITY_CACHE_ENABLED,
			ArtifactVersionModelImpl.FINDER_CACHE_ENABLED,
			ArtifactVersionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			ArtifactVersionModelImpl.ENTITY_CACHE_ENABLED,
			ArtifactVersionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);
	}

	public void destroy() {
		entityCache.removeCache(ArtifactVersionImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_ARTIFACTVERSION =
		"SELECT artifactVersion FROM ArtifactVersion artifactVersion";

	private static final String _SQL_SELECT_ARTIFACTVERSION_WHERE_PKS_IN =
		"SELECT artifactVersion FROM ArtifactVersion artifactVersion WHERE artifactVersionId IN (";

	private static final String _SQL_COUNT_ARTIFACTVERSION =
		"SELECT COUNT(artifactVersion) FROM ArtifactVersion artifactVersion";

	private static final String _ORDER_BY_ENTITY_ALIAS = "artifactVersion.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No ArtifactVersion exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		ArtifactVersionPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"group"});

}