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

package com.liferay.osb.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.exception.NoSuchAccountEntryException;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.impl.AccountEntryImpl;
import com.liferay.osb.model.impl.AccountEntryModelImpl;
import com.liferay.osb.service.persistence.AccountEntryPersistence;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.CompanyProvider;
import com.liferay.portal.kernel.service.persistence.CompanyProviderWrapper;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the account entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountEntryPersistence
 * @see com.liferay.osb.service.persistence.AccountEntryUtil
 * @generated
 */
@ProviderType
public class AccountEntryPersistenceImpl extends BasePersistenceImpl<AccountEntry>
	implements AccountEntryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AccountEntryUtil} to access the account entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AccountEntryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryModelImpl.FINDER_CACHE_ENABLED, AccountEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryModelImpl.FINDER_CACHE_ENABLED, AccountEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public AccountEntryPersistenceImpl() {
		setModelClass(AccountEntry.class);
	}

	/**
	 * Caches the account entry in the entity cache if it is enabled.
	 *
	 * @param accountEntry the account entry
	 */
	@Override
	public void cacheResult(AccountEntry accountEntry) {
		entityCache.putResult(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryImpl.class, accountEntry.getPrimaryKey(), accountEntry);

		accountEntry.resetOriginalValues();
	}

	/**
	 * Caches the account entries in the entity cache if it is enabled.
	 *
	 * @param accountEntries the account entries
	 */
	@Override
	public void cacheResult(List<AccountEntry> accountEntries) {
		for (AccountEntry accountEntry : accountEntries) {
			if (entityCache.getResult(
						AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
						AccountEntryImpl.class, accountEntry.getPrimaryKey()) == null) {
				cacheResult(accountEntry);
			}
			else {
				accountEntry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all account entries.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(AccountEntryImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the account entry.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AccountEntry accountEntry) {
		entityCache.removeResult(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryImpl.class, accountEntry.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<AccountEntry> accountEntries) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AccountEntry accountEntry : accountEntries) {
			entityCache.removeResult(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
				AccountEntryImpl.class, accountEntry.getPrimaryKey());
		}
	}

	/**
	 * Creates a new account entry with the primary key. Does not add the account entry to the database.
	 *
	 * @param accountEntryId the primary key for the new account entry
	 * @return the new account entry
	 */
	@Override
	public AccountEntry create(long accountEntryId) {
		AccountEntry accountEntry = new AccountEntryImpl();

		accountEntry.setNew(true);
		accountEntry.setPrimaryKey(accountEntryId);

		accountEntry.setCompanyId(companyProvider.getCompanyId());

		return accountEntry;
	}

	/**
	 * Removes the account entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param accountEntryId the primary key of the account entry
	 * @return the account entry that was removed
	 * @throws NoSuchAccountEntryException if a account entry with the primary key could not be found
	 */
	@Override
	public AccountEntry remove(long accountEntryId)
		throws NoSuchAccountEntryException {
		return remove((Serializable)accountEntryId);
	}

	/**
	 * Removes the account entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the account entry
	 * @return the account entry that was removed
	 * @throws NoSuchAccountEntryException if a account entry with the primary key could not be found
	 */
	@Override
	public AccountEntry remove(Serializable primaryKey)
		throws NoSuchAccountEntryException {
		Session session = null;

		try {
			session = openSession();

			AccountEntry accountEntry = (AccountEntry)session.get(AccountEntryImpl.class,
					primaryKey);

			if (accountEntry == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAccountEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(accountEntry);
		}
		catch (NoSuchAccountEntryException nsee) {
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
	protected AccountEntry removeImpl(AccountEntry accountEntry) {
		accountEntry = toUnwrappedModel(accountEntry);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(accountEntry)) {
				accountEntry = (AccountEntry)session.get(AccountEntryImpl.class,
						accountEntry.getPrimaryKeyObj());
			}

			if (accountEntry != null) {
				session.delete(accountEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (accountEntry != null) {
			clearCache(accountEntry);
		}

		return accountEntry;
	}

	@Override
	public AccountEntry updateImpl(AccountEntry accountEntry) {
		accountEntry = toUnwrappedModel(accountEntry);

		boolean isNew = accountEntry.isNew();

		AccountEntryModelImpl accountEntryModelImpl = (AccountEntryModelImpl)accountEntry;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (accountEntry.getCreateDate() == null)) {
			if (serviceContext == null) {
				accountEntry.setCreateDate(now);
			}
			else {
				accountEntry.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!accountEntryModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				accountEntry.setModifiedDate(now);
			}
			else {
				accountEntry.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (accountEntry.isNew()) {
				session.save(accountEntry);

				accountEntry.setNew(false);
			}
			else {
				accountEntry = (AccountEntry)session.merge(accountEntry);
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

		entityCache.putResult(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryImpl.class, accountEntry.getPrimaryKey(), accountEntry,
			false);

		accountEntry.resetOriginalValues();

		return accountEntry;
	}

	protected AccountEntry toUnwrappedModel(AccountEntry accountEntry) {
		if (accountEntry instanceof AccountEntryImpl) {
			return accountEntry;
		}

		AccountEntryImpl accountEntryImpl = new AccountEntryImpl();

		accountEntryImpl.setNew(accountEntry.isNew());
		accountEntryImpl.setPrimaryKey(accountEntry.getPrimaryKey());

		accountEntryImpl.setAccountEntryId(accountEntry.getAccountEntryId());
		accountEntryImpl.setCompanyId(accountEntry.getCompanyId());
		accountEntryImpl.setUserId(accountEntry.getUserId());
		accountEntryImpl.setUserName(accountEntry.getUserName());
		accountEntryImpl.setCreateDate(accountEntry.getCreateDate());
		accountEntryImpl.setModifiedUserId(accountEntry.getModifiedUserId());
		accountEntryImpl.setModifiedUserName(accountEntry.getModifiedUserName());
		accountEntryImpl.setModifiedDate(accountEntry.getModifiedDate());
		accountEntryImpl.setKoroneikiAccountKey(accountEntry.getKoroneikiAccountKey());
		accountEntryImpl.setDossieraAccountKey(accountEntry.getDossieraAccountKey());
		accountEntryImpl.setName(accountEntry.getName());
		accountEntryImpl.setCode(accountEntry.getCode());
		accountEntryImpl.setInstructions(accountEntry.getInstructions());
		accountEntryImpl.setActiveSupport(accountEntry.isActiveSupport());
		accountEntryImpl.setActiveTicketSupport(accountEntry.isActiveTicketSupport());
		accountEntryImpl.setLastZendeskAuditDate(accountEntry.getLastZendeskAuditDate());
		accountEntryImpl.setStatus(accountEntry.getStatus());

		return accountEntryImpl;
	}

	/**
	 * Returns the account entry with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the account entry
	 * @return the account entry
	 * @throws NoSuchAccountEntryException if a account entry with the primary key could not be found
	 */
	@Override
	public AccountEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchAccountEntryException {
		AccountEntry accountEntry = fetchByPrimaryKey(primaryKey);

		if (accountEntry == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchAccountEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return accountEntry;
	}

	/**
	 * Returns the account entry with the primary key or throws a {@link NoSuchAccountEntryException} if it could not be found.
	 *
	 * @param accountEntryId the primary key of the account entry
	 * @return the account entry
	 * @throws NoSuchAccountEntryException if a account entry with the primary key could not be found
	 */
	@Override
	public AccountEntry findByPrimaryKey(long accountEntryId)
		throws NoSuchAccountEntryException {
		return findByPrimaryKey((Serializable)accountEntryId);
	}

	/**
	 * Returns the account entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the account entry
	 * @return the account entry, or <code>null</code> if a account entry with the primary key could not be found
	 */
	@Override
	public AccountEntry fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
				AccountEntryImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		AccountEntry accountEntry = (AccountEntry)serializable;

		if (accountEntry == null) {
			Session session = null;

			try {
				session = openSession();

				accountEntry = (AccountEntry)session.get(AccountEntryImpl.class,
						primaryKey);

				if (accountEntry != null) {
					cacheResult(accountEntry);
				}
				else {
					entityCache.putResult(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
						AccountEntryImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
					AccountEntryImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return accountEntry;
	}

	/**
	 * Returns the account entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param accountEntryId the primary key of the account entry
	 * @return the account entry, or <code>null</code> if a account entry with the primary key could not be found
	 */
	@Override
	public AccountEntry fetchByPrimaryKey(long accountEntryId) {
		return fetchByPrimaryKey((Serializable)accountEntryId);
	}

	@Override
	public Map<Serializable, AccountEntry> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, AccountEntry> map = new HashMap<Serializable, AccountEntry>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			AccountEntry accountEntry = fetchByPrimaryKey(primaryKey);

			if (accountEntry != null) {
				map.put(primaryKey, accountEntry);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
					AccountEntryImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (AccountEntry)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_ACCOUNTENTRY_WHERE_PKS_IN);

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

			for (AccountEntry accountEntry : (List<AccountEntry>)q.list()) {
				map.put(accountEntry.getPrimaryKeyObj(), accountEntry);

				cacheResult(accountEntry);

				uncachedPrimaryKeys.remove(accountEntry.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
					AccountEntryImpl.class, primaryKey, nullModel);
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
	 * Returns all the account entries.
	 *
	 * @return the account entries
	 */
	@Override
	public List<AccountEntry> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of account entries
	 * @param end the upper bound of the range of account entries (not inclusive)
	 * @return the range of account entries
	 */
	@Override
	public List<AccountEntry> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the account entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of account entries
	 * @param end the upper bound of the range of account entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of account entries
	 */
	@Override
	public List<AccountEntry> findAll(int start, int end,
		OrderByComparator<AccountEntry> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the account entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of account entries
	 * @param end the upper bound of the range of account entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of account entries
	 */
	@Override
	public List<AccountEntry> findAll(int start, int end,
		OrderByComparator<AccountEntry> orderByComparator,
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

		List<AccountEntry> list = null;

		if (retrieveFromCache) {
			list = (List<AccountEntry>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_ACCOUNTENTRY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ACCOUNTENTRY;

				if (pagination) {
					sql = sql.concat(AccountEntryModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<AccountEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AccountEntry>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the account entries from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (AccountEntry accountEntry : findAll()) {
			remove(accountEntry);
		}
	}

	/**
	 * Returns the number of account entries.
	 *
	 * @return the number of account entries
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ACCOUNTENTRY);

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
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return AccountEntryModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the account entry persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(AccountEntryImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@BeanReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;
	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_ACCOUNTENTRY = "SELECT accountEntry FROM AccountEntry accountEntry";
	private static final String _SQL_SELECT_ACCOUNTENTRY_WHERE_PKS_IN = "SELECT accountEntry FROM AccountEntry accountEntry WHERE accountEntryId IN (";
	private static final String _SQL_COUNT_ACCOUNTENTRY = "SELECT COUNT(accountEntry) FROM AccountEntry accountEntry";
	private static final String _ORDER_BY_ENTITY_ALIAS = "accountEntry.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AccountEntry exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(AccountEntryPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"code"
			});
}