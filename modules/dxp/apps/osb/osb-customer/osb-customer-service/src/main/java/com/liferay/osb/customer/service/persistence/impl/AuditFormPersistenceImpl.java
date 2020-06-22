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

package com.liferay.osb.customer.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.customer.exception.NoSuchAuditFormException;
import com.liferay.osb.customer.model.AuditForm;
import com.liferay.osb.customer.model.impl.AuditFormImpl;
import com.liferay.osb.customer.model.impl.AuditFormModelImpl;
import com.liferay.osb.customer.service.persistence.AuditFormPersistence;

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
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the audit form service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AuditFormPersistence
 * @see com.liferay.osb.customer.service.persistence.AuditFormUtil
 * @generated
 */
@ProviderType
public class AuditFormPersistenceImpl extends BasePersistenceImpl<AuditForm>
	implements AuditFormPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AuditFormUtil} to access the audit form persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AuditFormImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AuditFormModelImpl.ENTITY_CACHE_ENABLED,
			AuditFormModelImpl.FINDER_CACHE_ENABLED, AuditFormImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AuditFormModelImpl.ENTITY_CACHE_ENABLED,
			AuditFormModelImpl.FINDER_CACHE_ENABLED, AuditFormImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AuditFormModelImpl.ENTITY_CACHE_ENABLED,
			AuditFormModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public AuditFormPersistenceImpl() {
		setModelClass(AuditForm.class);
	}

	/**
	 * Caches the audit form in the entity cache if it is enabled.
	 *
	 * @param auditForm the audit form
	 */
	@Override
	public void cacheResult(AuditForm auditForm) {
		entityCache.putResult(AuditFormModelImpl.ENTITY_CACHE_ENABLED,
			AuditFormImpl.class, auditForm.getPrimaryKey(), auditForm);

		auditForm.resetOriginalValues();
	}

	/**
	 * Caches the audit forms in the entity cache if it is enabled.
	 *
	 * @param auditForms the audit forms
	 */
	@Override
	public void cacheResult(List<AuditForm> auditForms) {
		for (AuditForm auditForm : auditForms) {
			if (entityCache.getResult(AuditFormModelImpl.ENTITY_CACHE_ENABLED,
						AuditFormImpl.class, auditForm.getPrimaryKey()) == null) {
				cacheResult(auditForm);
			}
			else {
				auditForm.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all audit forms.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(AuditFormImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the audit form.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AuditForm auditForm) {
		entityCache.removeResult(AuditFormModelImpl.ENTITY_CACHE_ENABLED,
			AuditFormImpl.class, auditForm.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<AuditForm> auditForms) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AuditForm auditForm : auditForms) {
			entityCache.removeResult(AuditFormModelImpl.ENTITY_CACHE_ENABLED,
				AuditFormImpl.class, auditForm.getPrimaryKey());
		}
	}

	/**
	 * Creates a new audit form with the primary key. Does not add the audit form to the database.
	 *
	 * @param auditFormId the primary key for the new audit form
	 * @return the new audit form
	 */
	@Override
	public AuditForm create(long auditFormId) {
		AuditForm auditForm = new AuditFormImpl();

		auditForm.setNew(true);
		auditForm.setPrimaryKey(auditFormId);

		return auditForm;
	}

	/**
	 * Removes the audit form with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param auditFormId the primary key of the audit form
	 * @return the audit form that was removed
	 * @throws NoSuchAuditFormException if a audit form with the primary key could not be found
	 */
	@Override
	public AuditForm remove(long auditFormId) throws NoSuchAuditFormException {
		return remove((Serializable)auditFormId);
	}

	/**
	 * Removes the audit form with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the audit form
	 * @return the audit form that was removed
	 * @throws NoSuchAuditFormException if a audit form with the primary key could not be found
	 */
	@Override
	public AuditForm remove(Serializable primaryKey)
		throws NoSuchAuditFormException {
		Session session = null;

		try {
			session = openSession();

			AuditForm auditForm = (AuditForm)session.get(AuditFormImpl.class,
					primaryKey);

			if (auditForm == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAuditFormException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(auditForm);
		}
		catch (NoSuchAuditFormException nsee) {
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
	protected AuditForm removeImpl(AuditForm auditForm) {
		auditForm = toUnwrappedModel(auditForm);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(auditForm)) {
				auditForm = (AuditForm)session.get(AuditFormImpl.class,
						auditForm.getPrimaryKeyObj());
			}

			if (auditForm != null) {
				session.delete(auditForm);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (auditForm != null) {
			clearCache(auditForm);
		}

		return auditForm;
	}

	@Override
	public AuditForm updateImpl(AuditForm auditForm) {
		auditForm = toUnwrappedModel(auditForm);

		boolean isNew = auditForm.isNew();

		Session session = null;

		try {
			session = openSession();

			if (auditForm.isNew()) {
				session.save(auditForm);

				auditForm.setNew(false);
			}
			else {
				auditForm = (AuditForm)session.merge(auditForm);
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
			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		entityCache.putResult(AuditFormModelImpl.ENTITY_CACHE_ENABLED,
			AuditFormImpl.class, auditForm.getPrimaryKey(), auditForm, false);

		auditForm.resetOriginalValues();

		return auditForm;
	}

	protected AuditForm toUnwrappedModel(AuditForm auditForm) {
		if (auditForm instanceof AuditFormImpl) {
			return auditForm;
		}

		AuditFormImpl auditFormImpl = new AuditFormImpl();

		auditFormImpl.setNew(auditForm.isNew());
		auditFormImpl.setPrimaryKey(auditForm.getPrimaryKey());

		auditFormImpl.setAuditFormId(auditForm.getAuditFormId());
		auditFormImpl.setUserId(auditForm.getUserId());
		auditFormImpl.setUserName(auditForm.getUserName());
		auditFormImpl.setCreateDate(auditForm.getCreateDate());
		auditFormImpl.setEndUserName(auditForm.getEndUserName());
		auditFormImpl.setEndUserEmailAddress(auditForm.getEndUserEmailAddress());
		auditFormImpl.setCompanyName(auditForm.getCompanyName());
		auditFormImpl.setAgreement(auditForm.isAgreement());

		return auditFormImpl;
	}

	/**
	 * Returns the audit form with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the audit form
	 * @return the audit form
	 * @throws NoSuchAuditFormException if a audit form with the primary key could not be found
	 */
	@Override
	public AuditForm findByPrimaryKey(Serializable primaryKey)
		throws NoSuchAuditFormException {
		AuditForm auditForm = fetchByPrimaryKey(primaryKey);

		if (auditForm == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchAuditFormException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return auditForm;
	}

	/**
	 * Returns the audit form with the primary key or throws a {@link NoSuchAuditFormException} if it could not be found.
	 *
	 * @param auditFormId the primary key of the audit form
	 * @return the audit form
	 * @throws NoSuchAuditFormException if a audit form with the primary key could not be found
	 */
	@Override
	public AuditForm findByPrimaryKey(long auditFormId)
		throws NoSuchAuditFormException {
		return findByPrimaryKey((Serializable)auditFormId);
	}

	/**
	 * Returns the audit form with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the audit form
	 * @return the audit form, or <code>null</code> if a audit form with the primary key could not be found
	 */
	@Override
	public AuditForm fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(AuditFormModelImpl.ENTITY_CACHE_ENABLED,
				AuditFormImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		AuditForm auditForm = (AuditForm)serializable;

		if (auditForm == null) {
			Session session = null;

			try {
				session = openSession();

				auditForm = (AuditForm)session.get(AuditFormImpl.class,
						primaryKey);

				if (auditForm != null) {
					cacheResult(auditForm);
				}
				else {
					entityCache.putResult(AuditFormModelImpl.ENTITY_CACHE_ENABLED,
						AuditFormImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(AuditFormModelImpl.ENTITY_CACHE_ENABLED,
					AuditFormImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return auditForm;
	}

	/**
	 * Returns the audit form with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param auditFormId the primary key of the audit form
	 * @return the audit form, or <code>null</code> if a audit form with the primary key could not be found
	 */
	@Override
	public AuditForm fetchByPrimaryKey(long auditFormId) {
		return fetchByPrimaryKey((Serializable)auditFormId);
	}

	@Override
	public Map<Serializable, AuditForm> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, AuditForm> map = new HashMap<Serializable, AuditForm>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			AuditForm auditForm = fetchByPrimaryKey(primaryKey);

			if (auditForm != null) {
				map.put(primaryKey, auditForm);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(AuditFormModelImpl.ENTITY_CACHE_ENABLED,
					AuditFormImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (AuditForm)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_AUDITFORM_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append((long)primaryKey);

			query.append(StringPool.COMMA);
		}

		query.setIndex(query.index() - 1);

		query.append(StringPool.CLOSE_PARENTHESIS);

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (AuditForm auditForm : (List<AuditForm>)q.list()) {
				map.put(auditForm.getPrimaryKeyObj(), auditForm);

				cacheResult(auditForm);

				uncachedPrimaryKeys.remove(auditForm.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(AuditFormModelImpl.ENTITY_CACHE_ENABLED,
					AuditFormImpl.class, primaryKey, nullModel);
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
	 * Returns all the audit forms.
	 *
	 * @return the audit forms
	 */
	@Override
	public List<AuditForm> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the audit forms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of audit forms
	 * @param end the upper bound of the range of audit forms (not inclusive)
	 * @return the range of audit forms
	 */
	@Override
	public List<AuditForm> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the audit forms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of audit forms
	 * @param end the upper bound of the range of audit forms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of audit forms
	 */
	@Override
	public List<AuditForm> findAll(int start, int end,
		OrderByComparator<AuditForm> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the audit forms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of audit forms
	 * @param end the upper bound of the range of audit forms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of audit forms
	 */
	@Override
	public List<AuditForm> findAll(int start, int end,
		OrderByComparator<AuditForm> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<AuditForm> list = null;

		if (retrieveFromCache) {
			list = (List<AuditForm>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_AUDITFORM);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_AUDITFORM;

				if (pagination) {
					sql = sql.concat(AuditFormModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<AuditForm>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AuditForm>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Removes all the audit forms from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (AuditForm auditForm : findAll()) {
			remove(auditForm);
		}
	}

	/**
	 * Returns the number of audit forms.
	 *
	 * @return the number of audit forms
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_AUDITFORM);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY,
					count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return AuditFormModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the audit form persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(AuditFormImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_AUDITFORM = "SELECT auditForm FROM AuditForm auditForm";
	private static final String _SQL_SELECT_AUDITFORM_WHERE_PKS_IN = "SELECT auditForm FROM AuditForm auditForm WHERE auditFormId IN (";
	private static final String _SQL_COUNT_AUDITFORM = "SELECT COUNT(auditForm) FROM AuditForm auditForm";
	private static final String _ORDER_BY_ENTITY_ALIAS = "auditForm.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AuditForm exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(AuditFormPersistenceImpl.class);
}