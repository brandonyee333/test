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
import com.liferay.watson.exception.NoSuchDocumentException;
import com.liferay.watson.model.WatsonDocument;
import com.liferay.watson.model.impl.WatsonDocumentImpl;
import com.liferay.watson.model.impl.WatsonDocumentModelImpl;
import com.liferay.watson.service.persistence.WatsonDocumentPersistence;

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
 * The persistence implementation for the watson document service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @generated
 */
public class WatsonDocumentPersistenceImpl
	extends BasePersistenceImpl<WatsonDocument>
	implements WatsonDocumentPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>WatsonDocumentUtil</code> to access the watson document persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		WatsonDocumentImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public WatsonDocumentPersistenceImpl() {
		setModelClass(WatsonDocument.class);
	}

	/**
	 * Caches the watson document in the entity cache if it is enabled.
	 *
	 * @param watsonDocument the watson document
	 */
	@Override
	public void cacheResult(WatsonDocument watsonDocument) {
		entityCache.putResult(
			WatsonDocumentModelImpl.ENTITY_CACHE_ENABLED,
			WatsonDocumentImpl.class, watsonDocument.getPrimaryKey(),
			watsonDocument);

		watsonDocument.resetOriginalValues();
	}

	/**
	 * Caches the watson documents in the entity cache if it is enabled.
	 *
	 * @param watsonDocuments the watson documents
	 */
	@Override
	public void cacheResult(List<WatsonDocument> watsonDocuments) {
		for (WatsonDocument watsonDocument : watsonDocuments) {
			if (entityCache.getResult(
					WatsonDocumentModelImpl.ENTITY_CACHE_ENABLED,
					WatsonDocumentImpl.class, watsonDocument.getPrimaryKey()) ==
						null) {

				cacheResult(watsonDocument);
			}
			else {
				watsonDocument.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all watson documents.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(WatsonDocumentImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the watson document.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(WatsonDocument watsonDocument) {
		entityCache.removeResult(
			WatsonDocumentModelImpl.ENTITY_CACHE_ENABLED,
			WatsonDocumentImpl.class, watsonDocument.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<WatsonDocument> watsonDocuments) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (WatsonDocument watsonDocument : watsonDocuments) {
			entityCache.removeResult(
				WatsonDocumentModelImpl.ENTITY_CACHE_ENABLED,
				WatsonDocumentImpl.class, watsonDocument.getPrimaryKey());
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				WatsonDocumentModelImpl.ENTITY_CACHE_ENABLED,
				WatsonDocumentImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new watson document with the primary key. Does not add the watson document to the database.
	 *
	 * @param watsonDocumentId the primary key for the new watson document
	 * @return the new watson document
	 */
	@Override
	public WatsonDocument create(long watsonDocumentId) {
		WatsonDocument watsonDocument = new WatsonDocumentImpl();

		watsonDocument.setNew(true);
		watsonDocument.setPrimaryKey(watsonDocumentId);

		watsonDocument.setCompanyId(CompanyThreadLocal.getCompanyId());

		return watsonDocument;
	}

	/**
	 * Removes the watson document with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonDocumentId the primary key of the watson document
	 * @return the watson document that was removed
	 * @throws NoSuchDocumentException if a watson document with the primary key could not be found
	 */
	@Override
	public WatsonDocument remove(long watsonDocumentId)
		throws NoSuchDocumentException {

		return remove((Serializable)watsonDocumentId);
	}

	/**
	 * Removes the watson document with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the watson document
	 * @return the watson document that was removed
	 * @throws NoSuchDocumentException if a watson document with the primary key could not be found
	 */
	@Override
	public WatsonDocument remove(Serializable primaryKey)
		throws NoSuchDocumentException {

		Session session = null;

		try {
			session = openSession();

			WatsonDocument watsonDocument = (WatsonDocument)session.get(
				WatsonDocumentImpl.class, primaryKey);

			if (watsonDocument == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDocumentException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(watsonDocument);
		}
		catch (NoSuchDocumentException noSuchEntityException) {
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
	protected WatsonDocument removeImpl(WatsonDocument watsonDocument) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(watsonDocument)) {
				watsonDocument = (WatsonDocument)session.get(
					WatsonDocumentImpl.class,
					watsonDocument.getPrimaryKeyObj());
			}

			if (watsonDocument != null) {
				session.delete(watsonDocument);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (watsonDocument != null) {
			clearCache(watsonDocument);
		}

		return watsonDocument;
	}

	@Override
	public WatsonDocument updateImpl(WatsonDocument watsonDocument) {
		boolean isNew = watsonDocument.isNew();

		if (!(watsonDocument instanceof WatsonDocumentModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(watsonDocument.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					watsonDocument);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in watsonDocument proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom WatsonDocument implementation " +
					watsonDocument.getClass());
		}

		WatsonDocumentModelImpl watsonDocumentModelImpl =
			(WatsonDocumentModelImpl)watsonDocument;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (watsonDocument.getCreateDate() == null)) {
			if (serviceContext == null) {
				watsonDocument.setCreateDate(now);
			}
			else {
				watsonDocument.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!watsonDocumentModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				watsonDocument.setModifiedDate(now);
			}
			else {
				watsonDocument.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(watsonDocument);

				watsonDocument.setNew(false);
			}
			else {
				watsonDocument = (WatsonDocument)session.merge(watsonDocument);
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
			WatsonDocumentModelImpl.ENTITY_CACHE_ENABLED,
			WatsonDocumentImpl.class, watsonDocument.getPrimaryKey(),
			watsonDocument, false);

		watsonDocument.resetOriginalValues();

		return watsonDocument;
	}

	/**
	 * Returns the watson document with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the watson document
	 * @return the watson document
	 * @throws NoSuchDocumentException if a watson document with the primary key could not be found
	 */
	@Override
	public WatsonDocument findByPrimaryKey(Serializable primaryKey)
		throws NoSuchDocumentException {

		WatsonDocument watsonDocument = fetchByPrimaryKey(primaryKey);

		if (watsonDocument == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchDocumentException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return watsonDocument;
	}

	/**
	 * Returns the watson document with the primary key or throws a <code>NoSuchDocumentException</code> if it could not be found.
	 *
	 * @param watsonDocumentId the primary key of the watson document
	 * @return the watson document
	 * @throws NoSuchDocumentException if a watson document with the primary key could not be found
	 */
	@Override
	public WatsonDocument findByPrimaryKey(long watsonDocumentId)
		throws NoSuchDocumentException {

		return findByPrimaryKey((Serializable)watsonDocumentId);
	}

	/**
	 * Returns the watson document with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the watson document
	 * @return the watson document, or <code>null</code> if a watson document with the primary key could not be found
	 */
	@Override
	public WatsonDocument fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			WatsonDocumentModelImpl.ENTITY_CACHE_ENABLED,
			WatsonDocumentImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		WatsonDocument watsonDocument = (WatsonDocument)serializable;

		if (watsonDocument == null) {
			Session session = null;

			try {
				session = openSession();

				watsonDocument = (WatsonDocument)session.get(
					WatsonDocumentImpl.class, primaryKey);

				if (watsonDocument != null) {
					cacheResult(watsonDocument);
				}
				else {
					entityCache.putResult(
						WatsonDocumentModelImpl.ENTITY_CACHE_ENABLED,
						WatsonDocumentImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception exception) {
				entityCache.removeResult(
					WatsonDocumentModelImpl.ENTITY_CACHE_ENABLED,
					WatsonDocumentImpl.class, primaryKey);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return watsonDocument;
	}

	/**
	 * Returns the watson document with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param watsonDocumentId the primary key of the watson document
	 * @return the watson document, or <code>null</code> if a watson document with the primary key could not be found
	 */
	@Override
	public WatsonDocument fetchByPrimaryKey(long watsonDocumentId) {
		return fetchByPrimaryKey((Serializable)watsonDocumentId);
	}

	@Override
	public Map<Serializable, WatsonDocument> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, WatsonDocument> map =
			new HashMap<Serializable, WatsonDocument>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			WatsonDocument watsonDocument = fetchByPrimaryKey(primaryKey);

			if (watsonDocument != null) {
				map.put(primaryKey, watsonDocument);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				WatsonDocumentModelImpl.ENTITY_CACHE_ENABLED,
				WatsonDocumentImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (WatsonDocument)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler sb = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		sb.append(_SQL_SELECT_WATSONDOCUMENT_WHERE_PKS_IN);

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

			for (WatsonDocument watsonDocument :
					(List<WatsonDocument>)query.list()) {

				map.put(watsonDocument.getPrimaryKeyObj(), watsonDocument);

				cacheResult(watsonDocument);

				uncachedPrimaryKeys.remove(watsonDocument.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					WatsonDocumentModelImpl.ENTITY_CACHE_ENABLED,
					WatsonDocumentImpl.class, primaryKey, nullModel);
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
	 * Returns all the watson documents.
	 *
	 * @return the watson documents
	 */
	@Override
	public List<WatsonDocument> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the watson documents.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonDocumentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson documents
	 * @param end the upper bound of the range of watson documents (not inclusive)
	 * @return the range of watson documents
	 */
	@Override
	public List<WatsonDocument> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the watson documents.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonDocumentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson documents
	 * @param end the upper bound of the range of watson documents (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of watson documents
	 */
	@Override
	public List<WatsonDocument> findAll(
		int start, int end,
		OrderByComparator<WatsonDocument> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the watson documents.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonDocumentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson documents
	 * @param end the upper bound of the range of watson documents (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of watson documents
	 */
	@Override
	public List<WatsonDocument> findAll(
		int start, int end, OrderByComparator<WatsonDocument> orderByComparator,
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

		List<WatsonDocument> list = null;

		if (useFinderCache) {
			list = (List<WatsonDocument>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_WATSONDOCUMENT);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_WATSONDOCUMENT;

				sql = sql.concat(WatsonDocumentModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<WatsonDocument>)QueryUtil.list(
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
	 * Removes all the watson documents from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (WatsonDocument watsonDocument : findAll()) {
			remove(watsonDocument);
		}
	}

	/**
	 * Returns the number of watson documents.
	 *
	 * @return the number of watson documents
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_WATSONDOCUMENT);

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
		return WatsonDocumentModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the watson document persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			WatsonDocumentModelImpl.ENTITY_CACHE_ENABLED,
			WatsonDocumentModelImpl.FINDER_CACHE_ENABLED,
			WatsonDocumentImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			WatsonDocumentModelImpl.ENTITY_CACHE_ENABLED,
			WatsonDocumentModelImpl.FINDER_CACHE_ENABLED,
			WatsonDocumentImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);

		_finderPathCountAll = new FinderPath(
			WatsonDocumentModelImpl.ENTITY_CACHE_ENABLED,
			WatsonDocumentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);
	}

	public void destroy() {
		entityCache.removeCache(WatsonDocumentImpl.class.getName());

		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_WATSONDOCUMENT =
		"SELECT watsonDocument FROM WatsonDocument watsonDocument";

	private static final String _SQL_SELECT_WATSONDOCUMENT_WHERE_PKS_IN =
		"SELECT watsonDocument FROM WatsonDocument watsonDocument WHERE watsonDocumentId IN (";

	private static final String _SQL_COUNT_WATSONDOCUMENT =
		"SELECT COUNT(watsonDocument) FROM WatsonDocument watsonDocument";

	private static final String _ORDER_BY_ENTITY_ALIAS = "watsonDocument.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No WatsonDocument exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		WatsonDocumentPersistenceImpl.class);

}