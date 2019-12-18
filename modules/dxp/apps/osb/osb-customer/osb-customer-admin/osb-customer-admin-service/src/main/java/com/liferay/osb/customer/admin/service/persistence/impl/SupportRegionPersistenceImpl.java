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

package com.liferay.osb.customer.admin.service.persistence.impl;

import com.liferay.osb.customer.admin.exception.NoSuchSupportRegionException;
import com.liferay.osb.customer.admin.model.SupportRegion;
import com.liferay.osb.customer.admin.model.impl.SupportRegionImpl;
import com.liferay.osb.customer.admin.model.impl.SupportRegionModelImpl;
import com.liferay.osb.customer.admin.service.persistence.AccountEntryPersistence;
import com.liferay.osb.customer.admin.service.persistence.SupportRegionPersistence;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.service.persistence.impl.TableMapper;
import com.liferay.portal.kernel.service.persistence.impl.TableMapperFactory;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
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
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the support region service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class SupportRegionPersistenceImpl
	extends BasePersistenceImpl<SupportRegion>
	implements SupportRegionPersistence {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>SupportRegionUtil</code> to access the support region persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		SupportRegionImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathFetchByName;
	private FinderPath _finderPathCountByName;

	/**
	 * Returns the support region where name = &#63; or throws a <code>NoSuchSupportRegionException</code> if it could not be found.
	 *
	 * @param name the name
	 * @return the matching support region
	 * @throws NoSuchSupportRegionException if a matching support region could not be found
	 */
	@Override
	public SupportRegion findByName(String name)
		throws NoSuchSupportRegionException {

		SupportRegion supportRegion = fetchByName(name);

		if (supportRegion == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("name=");
			msg.append(name);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchSupportRegionException(msg.toString());
		}

		return supportRegion;
	}

	/**
	 * Returns the support region where name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param name the name
	 * @return the matching support region, or <code>null</code> if a matching support region could not be found
	 */
	@Override
	public SupportRegion fetchByName(String name) {
		return fetchByName(name, true);
	}

	/**
	 * Returns the support region where name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param name the name
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching support region, or <code>null</code> if a matching support region could not be found
	 */
	@Override
	public SupportRegion fetchByName(String name, boolean useFinderCache) {
		name = Objects.toString(name, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {name};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByName, finderArgs, this);
		}

		if (result instanceof SupportRegion) {
			SupportRegion supportRegion = (SupportRegion)result;

			if (!Objects.equals(name, supportRegion.getName())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_SUPPORTREGION_WHERE);

			boolean bindName = false;

			if (name.isEmpty()) {
				query.append(_FINDER_COLUMN_NAME_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_NAME_NAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindName) {
					qPos.add(name);
				}

				List<SupportRegion> list = q.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByName, finderArgs, list);
					}
				}
				else {
					SupportRegion supportRegion = list.get(0);

					result = supportRegion;

					cacheResult(supportRegion);
				}
			}
			catch (Exception e) {
				if (useFinderCache) {
					finderCache.removeResult(
						_finderPathFetchByName, finderArgs);
				}

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (SupportRegion)result;
		}
	}

	/**
	 * Removes the support region where name = &#63; from the database.
	 *
	 * @param name the name
	 * @return the support region that was removed
	 */
	@Override
	public SupportRegion removeByName(String name)
		throws NoSuchSupportRegionException {

		SupportRegion supportRegion = findByName(name);

		return remove(supportRegion);
	}

	/**
	 * Returns the number of support regions where name = &#63;.
	 *
	 * @param name the name
	 * @return the number of matching support regions
	 */
	@Override
	public int countByName(String name) {
		name = Objects.toString(name, "");

		FinderPath finderPath = _finderPathCountByName;

		Object[] finderArgs = new Object[] {name};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SUPPORTREGION_WHERE);

			boolean bindName = false;

			if (name.isEmpty()) {
				query.append(_FINDER_COLUMN_NAME_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_NAME_NAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindName) {
					qPos.add(name);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_NAME_NAME_2 =
		"supportRegion.name = ?";

	private static final String _FINDER_COLUMN_NAME_NAME_3 =
		"(supportRegion.name IS NULL OR supportRegion.name = '')";

	public SupportRegionPersistenceImpl() {
		setModelClass(SupportRegion.class);
	}

	/**
	 * Caches the support region in the entity cache if it is enabled.
	 *
	 * @param supportRegion the support region
	 */
	@Override
	public void cacheResult(SupportRegion supportRegion) {
		entityCache.putResult(
			SupportRegionModelImpl.ENTITY_CACHE_ENABLED,
			SupportRegionImpl.class, supportRegion.getPrimaryKey(),
			supportRegion);

		finderCache.putResult(
			_finderPathFetchByName, new Object[] {supportRegion.getName()},
			supportRegion);

		supportRegion.resetOriginalValues();
	}

	/**
	 * Caches the support regions in the entity cache if it is enabled.
	 *
	 * @param supportRegions the support regions
	 */
	@Override
	public void cacheResult(List<SupportRegion> supportRegions) {
		for (SupportRegion supportRegion : supportRegions) {
			if (entityCache.getResult(
					SupportRegionModelImpl.ENTITY_CACHE_ENABLED,
					SupportRegionImpl.class, supportRegion.getPrimaryKey()) ==
						null) {

				cacheResult(supportRegion);
			}
			else {
				supportRegion.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all support regions.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(SupportRegionImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the support region.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SupportRegion supportRegion) {
		entityCache.removeResult(
			SupportRegionModelImpl.ENTITY_CACHE_ENABLED,
			SupportRegionImpl.class, supportRegion.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((SupportRegionModelImpl)supportRegion, true);
	}

	@Override
	public void clearCache(List<SupportRegion> supportRegions) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (SupportRegion supportRegion : supportRegions) {
			entityCache.removeResult(
				SupportRegionModelImpl.ENTITY_CACHE_ENABLED,
				SupportRegionImpl.class, supportRegion.getPrimaryKey());

			clearUniqueFindersCache(
				(SupportRegionModelImpl)supportRegion, true);
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				SupportRegionModelImpl.ENTITY_CACHE_ENABLED,
				SupportRegionImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		SupportRegionModelImpl supportRegionModelImpl) {

		Object[] args = new Object[] {supportRegionModelImpl.getName()};

		finderCache.putResult(
			_finderPathCountByName, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByName, args, supportRegionModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		SupportRegionModelImpl supportRegionModelImpl, boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {supportRegionModelImpl.getName()};

			finderCache.removeResult(_finderPathCountByName, args);
			finderCache.removeResult(_finderPathFetchByName, args);
		}

		if ((supportRegionModelImpl.getColumnBitmask() &
			 _finderPathFetchByName.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				supportRegionModelImpl.getOriginalName()
			};

			finderCache.removeResult(_finderPathCountByName, args);
			finderCache.removeResult(_finderPathFetchByName, args);
		}
	}

	/**
	 * Creates a new support region with the primary key. Does not add the support region to the database.
	 *
	 * @param supportRegionId the primary key for the new support region
	 * @return the new support region
	 */
	@Override
	public SupportRegion create(long supportRegionId) {
		SupportRegion supportRegion = new SupportRegionImpl();

		supportRegion.setNew(true);
		supportRegion.setPrimaryKey(supportRegionId);

		supportRegion.setCompanyId(CompanyThreadLocal.getCompanyId());

		return supportRegion;
	}

	/**
	 * Removes the support region with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param supportRegionId the primary key of the support region
	 * @return the support region that was removed
	 * @throws NoSuchSupportRegionException if a support region with the primary key could not be found
	 */
	@Override
	public SupportRegion remove(long supportRegionId)
		throws NoSuchSupportRegionException {

		return remove((Serializable)supportRegionId);
	}

	/**
	 * Removes the support region with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the support region
	 * @return the support region that was removed
	 * @throws NoSuchSupportRegionException if a support region with the primary key could not be found
	 */
	@Override
	public SupportRegion remove(Serializable primaryKey)
		throws NoSuchSupportRegionException {

		Session session = null;

		try {
			session = openSession();

			SupportRegion supportRegion = (SupportRegion)session.get(
				SupportRegionImpl.class, primaryKey);

			if (supportRegion == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSupportRegionException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(supportRegion);
		}
		catch (NoSuchSupportRegionException nsee) {
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
	protected SupportRegion removeImpl(SupportRegion supportRegion) {
		supportRegionToAccountEntryTableMapper.
			deleteLeftPrimaryKeyTableMappings(supportRegion.getPrimaryKey());

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(supportRegion)) {
				supportRegion = (SupportRegion)session.get(
					SupportRegionImpl.class, supportRegion.getPrimaryKeyObj());
			}

			if (supportRegion != null) {
				session.delete(supportRegion);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (supportRegion != null) {
			clearCache(supportRegion);
		}

		return supportRegion;
	}

	@Override
	public SupportRegion updateImpl(SupportRegion supportRegion) {
		boolean isNew = supportRegion.isNew();

		if (!(supportRegion instanceof SupportRegionModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(supportRegion.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					supportRegion);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in supportRegion proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom SupportRegion implementation " +
					supportRegion.getClass());
		}

		SupportRegionModelImpl supportRegionModelImpl =
			(SupportRegionModelImpl)supportRegion;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (supportRegion.getCreateDate() == null)) {
			if (serviceContext == null) {
				supportRegion.setCreateDate(now);
			}
			else {
				supportRegion.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!supportRegionModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				supportRegion.setModifiedDate(now);
			}
			else {
				supportRegion.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (supportRegion.isNew()) {
				session.save(supportRegion);

				supportRegion.setNew(false);
			}
			else {
				supportRegion = (SupportRegion)session.merge(supportRegion);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!SupportRegionModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}

		entityCache.putResult(
			SupportRegionModelImpl.ENTITY_CACHE_ENABLED,
			SupportRegionImpl.class, supportRegion.getPrimaryKey(),
			supportRegion, false);

		clearUniqueFindersCache(supportRegionModelImpl, false);
		cacheUniqueFindersCache(supportRegionModelImpl);

		supportRegion.resetOriginalValues();

		return supportRegion;
	}

	/**
	 * Returns the support region with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the support region
	 * @return the support region
	 * @throws NoSuchSupportRegionException if a support region with the primary key could not be found
	 */
	@Override
	public SupportRegion findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSupportRegionException {

		SupportRegion supportRegion = fetchByPrimaryKey(primaryKey);

		if (supportRegion == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSupportRegionException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return supportRegion;
	}

	/**
	 * Returns the support region with the primary key or throws a <code>NoSuchSupportRegionException</code> if it could not be found.
	 *
	 * @param supportRegionId the primary key of the support region
	 * @return the support region
	 * @throws NoSuchSupportRegionException if a support region with the primary key could not be found
	 */
	@Override
	public SupportRegion findByPrimaryKey(long supportRegionId)
		throws NoSuchSupportRegionException {

		return findByPrimaryKey((Serializable)supportRegionId);
	}

	/**
	 * Returns the support region with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the support region
	 * @return the support region, or <code>null</code> if a support region with the primary key could not be found
	 */
	@Override
	public SupportRegion fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			SupportRegionModelImpl.ENTITY_CACHE_ENABLED,
			SupportRegionImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		SupportRegion supportRegion = (SupportRegion)serializable;

		if (supportRegion == null) {
			Session session = null;

			try {
				session = openSession();

				supportRegion = (SupportRegion)session.get(
					SupportRegionImpl.class, primaryKey);

				if (supportRegion != null) {
					cacheResult(supportRegion);
				}
				else {
					entityCache.putResult(
						SupportRegionModelImpl.ENTITY_CACHE_ENABLED,
						SupportRegionImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(
					SupportRegionModelImpl.ENTITY_CACHE_ENABLED,
					SupportRegionImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return supportRegion;
	}

	/**
	 * Returns the support region with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param supportRegionId the primary key of the support region
	 * @return the support region, or <code>null</code> if a support region with the primary key could not be found
	 */
	@Override
	public SupportRegion fetchByPrimaryKey(long supportRegionId) {
		return fetchByPrimaryKey((Serializable)supportRegionId);
	}

	@Override
	public Map<Serializable, SupportRegion> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, SupportRegion> map =
			new HashMap<Serializable, SupportRegion>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			SupportRegion supportRegion = fetchByPrimaryKey(primaryKey);

			if (supportRegion != null) {
				map.put(primaryKey, supportRegion);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				SupportRegionModelImpl.ENTITY_CACHE_ENABLED,
				SupportRegionImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (SupportRegion)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		query.append(_SQL_SELECT_SUPPORTREGION_WHERE_PKS_IN);

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

			for (SupportRegion supportRegion : (List<SupportRegion>)q.list()) {
				map.put(supportRegion.getPrimaryKeyObj(), supportRegion);

				cacheResult(supportRegion);

				uncachedPrimaryKeys.remove(supportRegion.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					SupportRegionModelImpl.ENTITY_CACHE_ENABLED,
					SupportRegionImpl.class, primaryKey, nullModel);
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
	 * Returns all the support regions.
	 *
	 * @return the support regions
	 */
	@Override
	public List<SupportRegion> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the support regions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SupportRegionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of support regions
	 * @param end the upper bound of the range of support regions (not inclusive)
	 * @return the range of support regions
	 */
	@Override
	public List<SupportRegion> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the support regions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SupportRegionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of support regions
	 * @param end the upper bound of the range of support regions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of support regions
	 */
	@Override
	public List<SupportRegion> findAll(
		int start, int end,
		OrderByComparator<SupportRegion> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the support regions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SupportRegionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of support regions
	 * @param end the upper bound of the range of support regions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of support regions
	 */
	@Override
	public List<SupportRegion> findAll(
		int start, int end, OrderByComparator<SupportRegion> orderByComparator,
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

		List<SupportRegion> list = null;

		if (useFinderCache) {
			list = (List<SupportRegion>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_SUPPORTREGION);

				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SUPPORTREGION;

				sql = sql.concat(SupportRegionModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				list = (List<SupportRegion>)QueryUtil.list(
					q, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception e) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the support regions from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (SupportRegion supportRegion : findAll()) {
			remove(supportRegion);
		}
	}

	/**
	 * Returns the number of support regions.
	 *
	 * @return the number of support regions
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_SUPPORTREGION);

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

	/**
	 * Returns the primaryKeys of account entries associated with the support region.
	 *
	 * @param pk the primary key of the support region
	 * @return long[] of the primaryKeys of account entries associated with the support region
	 */
	@Override
	public long[] getAccountEntryPrimaryKeys(long pk) {
		long[] pks = supportRegionToAccountEntryTableMapper.getRightPrimaryKeys(
			pk);

		return pks.clone();
	}

	/**
	 * Returns all the account entries associated with the support region.
	 *
	 * @param pk the primary key of the support region
	 * @return the account entries associated with the support region
	 */
	@Override
	public List<com.liferay.osb.customer.admin.model.AccountEntry>
		getAccountEntries(long pk) {

		return getAccountEntries(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	/**
	 * Returns a range of all the account entries associated with the support region.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SupportRegionModelImpl</code>.
	 * </p>
	 *
	 * @param pk the primary key of the support region
	 * @param start the lower bound of the range of support regions
	 * @param end the upper bound of the range of support regions (not inclusive)
	 * @return the range of account entries associated with the support region
	 */
	@Override
	public List<com.liferay.osb.customer.admin.model.AccountEntry>
		getAccountEntries(long pk, int start, int end) {

		return getAccountEntries(pk, start, end, null);
	}

	/**
	 * Returns an ordered range of all the account entries associated with the support region.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SupportRegionModelImpl</code>.
	 * </p>
	 *
	 * @param pk the primary key of the support region
	 * @param start the lower bound of the range of support regions
	 * @param end the upper bound of the range of support regions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of account entries associated with the support region
	 */
	@Override
	public List<com.liferay.osb.customer.admin.model.AccountEntry>
		getAccountEntries(
			long pk, int start, int end,
			OrderByComparator<com.liferay.osb.customer.admin.model.AccountEntry>
				orderByComparator) {

		return supportRegionToAccountEntryTableMapper.getRightBaseModels(
			pk, start, end, orderByComparator);
	}

	/**
	 * Returns the number of account entries associated with the support region.
	 *
	 * @param pk the primary key of the support region
	 * @return the number of account entries associated with the support region
	 */
	@Override
	public int getAccountEntriesSize(long pk) {
		long[] pks = supportRegionToAccountEntryTableMapper.getRightPrimaryKeys(
			pk);

		return pks.length;
	}

	/**
	 * Returns <code>true</code> if the account entry is associated with the support region.
	 *
	 * @param pk the primary key of the support region
	 * @param accountEntryPK the primary key of the account entry
	 * @return <code>true</code> if the account entry is associated with the support region; <code>false</code> otherwise
	 */
	@Override
	public boolean containsAccountEntry(long pk, long accountEntryPK) {
		return supportRegionToAccountEntryTableMapper.containsTableMapping(
			pk, accountEntryPK);
	}

	/**
	 * Returns <code>true</code> if the support region has any account entries associated with it.
	 *
	 * @param pk the primary key of the support region to check for associations with account entries
	 * @return <code>true</code> if the support region has any account entries associated with it; <code>false</code> otherwise
	 */
	@Override
	public boolean containsAccountEntries(long pk) {
		if (getAccountEntriesSize(pk) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Adds an association between the support region and the account entry. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support region
	 * @param accountEntryPK the primary key of the account entry
	 */
	@Override
	public void addAccountEntry(long pk, long accountEntryPK) {
		SupportRegion supportRegion = fetchByPrimaryKey(pk);

		if (supportRegion == null) {
			supportRegionToAccountEntryTableMapper.addTableMapping(
				CompanyThreadLocal.getCompanyId(), pk, accountEntryPK);
		}
		else {
			supportRegionToAccountEntryTableMapper.addTableMapping(
				supportRegion.getCompanyId(), pk, accountEntryPK);
		}
	}

	/**
	 * Adds an association between the support region and the account entry. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support region
	 * @param accountEntry the account entry
	 */
	@Override
	public void addAccountEntry(
		long pk,
		com.liferay.osb.customer.admin.model.AccountEntry accountEntry) {

		SupportRegion supportRegion = fetchByPrimaryKey(pk);

		if (supportRegion == null) {
			supportRegionToAccountEntryTableMapper.addTableMapping(
				CompanyThreadLocal.getCompanyId(), pk,
				accountEntry.getPrimaryKey());
		}
		else {
			supportRegionToAccountEntryTableMapper.addTableMapping(
				supportRegion.getCompanyId(), pk, accountEntry.getPrimaryKey());
		}
	}

	/**
	 * Adds an association between the support region and the account entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support region
	 * @param accountEntryPKs the primary keys of the account entries
	 */
	@Override
	public void addAccountEntries(long pk, long[] accountEntryPKs) {
		long companyId = 0;

		SupportRegion supportRegion = fetchByPrimaryKey(pk);

		if (supportRegion == null) {
			companyId = CompanyThreadLocal.getCompanyId();
		}
		else {
			companyId = supportRegion.getCompanyId();
		}

		supportRegionToAccountEntryTableMapper.addTableMappings(
			companyId, pk, accountEntryPKs);
	}

	/**
	 * Adds an association between the support region and the account entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support region
	 * @param accountEntries the account entries
	 */
	@Override
	public void addAccountEntries(
		long pk,
		List<com.liferay.osb.customer.admin.model.AccountEntry>
			accountEntries) {

		addAccountEntries(
			pk,
			ListUtil.toLongArray(
				accountEntries,
				com.liferay.osb.customer.admin.model.AccountEntry.
					ACCOUNT_ENTRY_ID_ACCESSOR));
	}

	/**
	 * Clears all associations between the support region and its account entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support region to clear the associated account entries from
	 */
	@Override
	public void clearAccountEntries(long pk) {
		supportRegionToAccountEntryTableMapper.
			deleteLeftPrimaryKeyTableMappings(pk);
	}

	/**
	 * Removes the association between the support region and the account entry. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support region
	 * @param accountEntryPK the primary key of the account entry
	 */
	@Override
	public void removeAccountEntry(long pk, long accountEntryPK) {
		supportRegionToAccountEntryTableMapper.deleteTableMapping(
			pk, accountEntryPK);
	}

	/**
	 * Removes the association between the support region and the account entry. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support region
	 * @param accountEntry the account entry
	 */
	@Override
	public void removeAccountEntry(
		long pk,
		com.liferay.osb.customer.admin.model.AccountEntry accountEntry) {

		supportRegionToAccountEntryTableMapper.deleteTableMapping(
			pk, accountEntry.getPrimaryKey());
	}

	/**
	 * Removes the association between the support region and the account entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support region
	 * @param accountEntryPKs the primary keys of the account entries
	 */
	@Override
	public void removeAccountEntries(long pk, long[] accountEntryPKs) {
		supportRegionToAccountEntryTableMapper.deleteTableMappings(
			pk, accountEntryPKs);
	}

	/**
	 * Removes the association between the support region and the account entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support region
	 * @param accountEntries the account entries
	 */
	@Override
	public void removeAccountEntries(
		long pk,
		List<com.liferay.osb.customer.admin.model.AccountEntry>
			accountEntries) {

		removeAccountEntries(
			pk,
			ListUtil.toLongArray(
				accountEntries,
				com.liferay.osb.customer.admin.model.AccountEntry.
					ACCOUNT_ENTRY_ID_ACCESSOR));
	}

	/**
	 * Sets the account entries associated with the support region, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support region
	 * @param accountEntryPKs the primary keys of the account entries to be associated with the support region
	 */
	@Override
	public void setAccountEntries(long pk, long[] accountEntryPKs) {
		Set<Long> newAccountEntryPKsSet = SetUtil.fromArray(accountEntryPKs);
		Set<Long> oldAccountEntryPKsSet = SetUtil.fromArray(
			supportRegionToAccountEntryTableMapper.getRightPrimaryKeys(pk));

		Set<Long> removeAccountEntryPKsSet = new HashSet<Long>(
			oldAccountEntryPKsSet);

		removeAccountEntryPKsSet.removeAll(newAccountEntryPKsSet);

		supportRegionToAccountEntryTableMapper.deleteTableMappings(
			pk, ArrayUtil.toLongArray(removeAccountEntryPKsSet));

		newAccountEntryPKsSet.removeAll(oldAccountEntryPKsSet);

		long companyId = 0;

		SupportRegion supportRegion = fetchByPrimaryKey(pk);

		if (supportRegion == null) {
			companyId = CompanyThreadLocal.getCompanyId();
		}
		else {
			companyId = supportRegion.getCompanyId();
		}

		supportRegionToAccountEntryTableMapper.addTableMappings(
			companyId, pk, ArrayUtil.toLongArray(newAccountEntryPKsSet));
	}

	/**
	 * Sets the account entries associated with the support region, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support region
	 * @param accountEntries the account entries to be associated with the support region
	 */
	@Override
	public void setAccountEntries(
		long pk,
		List<com.liferay.osb.customer.admin.model.AccountEntry>
			accountEntries) {

		try {
			long[] accountEntryPKs = new long[accountEntries.size()];

			for (int i = 0; i < accountEntries.size(); i++) {
				com.liferay.osb.customer.admin.model.AccountEntry accountEntry =
					accountEntries.get(i);

				accountEntryPKs[i] = accountEntry.getPrimaryKey();
			}

			setAccountEntries(pk, accountEntryPKs);
		}
		catch (Exception e) {
			throw processException(e);
		}
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return SupportRegionModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the support region persistence.
	 */
	public void afterPropertiesSet() {
		supportRegionToAccountEntryTableMapper =
			TableMapperFactory.getTableMapper(
				"OSB_AccountEntries_SupportRegions", "companyId",
				"supportRegionId", "accountEntryId", this,
				accountEntryPersistence);

		_finderPathWithPaginationFindAll = new FinderPath(
			SupportRegionModelImpl.ENTITY_CACHE_ENABLED,
			SupportRegionModelImpl.FINDER_CACHE_ENABLED,
			SupportRegionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			SupportRegionModelImpl.ENTITY_CACHE_ENABLED,
			SupportRegionModelImpl.FINDER_CACHE_ENABLED,
			SupportRegionImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);

		_finderPathCountAll = new FinderPath(
			SupportRegionModelImpl.ENTITY_CACHE_ENABLED,
			SupportRegionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathFetchByName = new FinderPath(
			SupportRegionModelImpl.ENTITY_CACHE_ENABLED,
			SupportRegionModelImpl.FINDER_CACHE_ENABLED,
			SupportRegionImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByName",
			new String[] {String.class.getName()},
			SupportRegionModelImpl.NAME_COLUMN_BITMASK);

		_finderPathCountByName = new FinderPath(
			SupportRegionModelImpl.ENTITY_CACHE_ENABLED,
			SupportRegionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByName",
			new String[] {String.class.getName()});
	}

	public void destroy() {
		entityCache.removeCache(SupportRegionImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		TableMapperFactory.removeTableMapper(
			"OSB_AccountEntries_SupportRegions");
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	@BeanReference(type = AccountEntryPersistence.class)
	protected AccountEntryPersistence accountEntryPersistence;

	protected TableMapper
		<SupportRegion, com.liferay.osb.customer.admin.model.AccountEntry>
			supportRegionToAccountEntryTableMapper;

	private static final String _SQL_SELECT_SUPPORTREGION =
		"SELECT supportRegion FROM SupportRegion supportRegion";

	private static final String _SQL_SELECT_SUPPORTREGION_WHERE_PKS_IN =
		"SELECT supportRegion FROM SupportRegion supportRegion WHERE supportRegionId IN (";

	private static final String _SQL_SELECT_SUPPORTREGION_WHERE =
		"SELECT supportRegion FROM SupportRegion supportRegion WHERE ";

	private static final String _SQL_COUNT_SUPPORTREGION =
		"SELECT COUNT(supportRegion) FROM SupportRegion supportRegion";

	private static final String _SQL_COUNT_SUPPORTREGION_WHERE =
		"SELECT COUNT(supportRegion) FROM SupportRegion supportRegion WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "supportRegion.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No SupportRegion exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No SupportRegion exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		SupportRegionPersistenceImpl.class);

}