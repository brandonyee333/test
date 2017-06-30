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

import com.liferay.osb.NoSuchContractEntryException;
import com.liferay.osb.model.ContractEntry;
import com.liferay.osb.model.impl.ContractEntryImpl;
import com.liferay.osb.model.impl.ContractEntryModelImpl;

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
 * The persistence implementation for the contract entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContractEntryPersistence
 * @see ContractEntryUtil
 * @generated
 */
public class ContractEntryPersistenceImpl extends BasePersistenceImpl<ContractEntry>
	implements ContractEntryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ContractEntryUtil} to access the contract entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ContractEntryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CN_CP_T = new FinderPath(ContractEntryModelImpl.ENTITY_CACHE_ENABLED,
			ContractEntryModelImpl.FINDER_CACHE_ENABLED,
			ContractEntryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCN_CP_T",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CN_CP_T =
		new FinderPath(ContractEntryModelImpl.ENTITY_CACHE_ENABLED,
			ContractEntryModelImpl.FINDER_CACHE_ENABLED,
			ContractEntryImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByCN_CP_T",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			},
			ContractEntryModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			ContractEntryModelImpl.CLASSPK_COLUMN_BITMASK |
			ContractEntryModelImpl.TYPE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CN_CP_T = new FinderPath(ContractEntryModelImpl.ENTITY_CACHE_ENABLED,
			ContractEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCN_CP_T",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ContractEntryModelImpl.ENTITY_CACHE_ENABLED,
			ContractEntryModelImpl.FINDER_CACHE_ENABLED,
			ContractEntryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ContractEntryModelImpl.ENTITY_CACHE_ENABLED,
			ContractEntryModelImpl.FINDER_CACHE_ENABLED,
			ContractEntryImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ContractEntryModelImpl.ENTITY_CACHE_ENABLED,
			ContractEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the contract entry in the entity cache if it is enabled.
	 *
	 * @param contractEntry the contract entry
	 */
	public void cacheResult(ContractEntry contractEntry) {
		EntityCacheUtil.putResult(ContractEntryModelImpl.ENTITY_CACHE_ENABLED,
			ContractEntryImpl.class, contractEntry.getPrimaryKey(),
			contractEntry);

		contractEntry.resetOriginalValues();
	}

	/**
	 * Caches the contract entries in the entity cache if it is enabled.
	 *
	 * @param contractEntries the contract entries
	 */
	public void cacheResult(List<ContractEntry> contractEntries) {
		for (ContractEntry contractEntry : contractEntries) {
			if (EntityCacheUtil.getResult(
						ContractEntryModelImpl.ENTITY_CACHE_ENABLED,
						ContractEntryImpl.class, contractEntry.getPrimaryKey()) == null) {
				cacheResult(contractEntry);
			}
			else {
				contractEntry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all contract entries.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(ContractEntryImpl.class.getName());
		}

		EntityCacheUtil.clearCache(ContractEntryImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the contract entry.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ContractEntry contractEntry) {
		EntityCacheUtil.removeResult(ContractEntryModelImpl.ENTITY_CACHE_ENABLED,
			ContractEntryImpl.class, contractEntry.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<ContractEntry> contractEntries) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ContractEntry contractEntry : contractEntries) {
			EntityCacheUtil.removeResult(ContractEntryModelImpl.ENTITY_CACHE_ENABLED,
				ContractEntryImpl.class, contractEntry.getPrimaryKey());
		}
	}

	/**
	 * Creates a new contract entry with the primary key. Does not add the contract entry to the database.
	 *
	 * @param contractEntryId the primary key for the new contract entry
	 * @return the new contract entry
	 */
	public ContractEntry create(long contractEntryId) {
		ContractEntry contractEntry = new ContractEntryImpl();

		contractEntry.setNew(true);
		contractEntry.setPrimaryKey(contractEntryId);

		return contractEntry;
	}

	/**
	 * Removes the contract entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param contractEntryId the primary key of the contract entry
	 * @return the contract entry that was removed
	 * @throws com.liferay.osb.NoSuchContractEntryException if a contract entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ContractEntry remove(long contractEntryId)
		throws NoSuchContractEntryException, SystemException {
		return remove(Long.valueOf(contractEntryId));
	}

	/**
	 * Removes the contract entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the contract entry
	 * @return the contract entry that was removed
	 * @throws com.liferay.osb.NoSuchContractEntryException if a contract entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ContractEntry remove(Serializable primaryKey)
		throws NoSuchContractEntryException, SystemException {
		Session session = null;

		try {
			session = openSession();

			ContractEntry contractEntry = (ContractEntry)session.get(ContractEntryImpl.class,
					primaryKey);

			if (contractEntry == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchContractEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(contractEntry);
		}
		catch (NoSuchContractEntryException nsee) {
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
	protected ContractEntry removeImpl(ContractEntry contractEntry)
		throws SystemException {
		contractEntry = toUnwrappedModel(contractEntry);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, contractEntry);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(contractEntry);

		return contractEntry;
	}

	@Override
	public ContractEntry updateImpl(
		com.liferay.osb.model.ContractEntry contractEntry, boolean merge)
		throws SystemException {
		contractEntry = toUnwrappedModel(contractEntry);

		boolean isNew = contractEntry.isNew();

		ContractEntryModelImpl contractEntryModelImpl = (ContractEntryModelImpl)contractEntry;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, contractEntry, merge);

			contractEntry.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !ContractEntryModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((contractEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CN_CP_T.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(contractEntryModelImpl.getOriginalClassNameId()),
						Long.valueOf(contractEntryModelImpl.getOriginalClassPK()),
						Integer.valueOf(contractEntryModelImpl.getOriginalType())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CN_CP_T, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CN_CP_T,
					args);

				args = new Object[] {
						Long.valueOf(contractEntryModelImpl.getClassNameId()),
						Long.valueOf(contractEntryModelImpl.getClassPK()),
						Integer.valueOf(contractEntryModelImpl.getType())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CN_CP_T, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CN_CP_T,
					args);
			}
		}

		EntityCacheUtil.putResult(ContractEntryModelImpl.ENTITY_CACHE_ENABLED,
			ContractEntryImpl.class, contractEntry.getPrimaryKey(),
			contractEntry);

		return contractEntry;
	}

	protected ContractEntry toUnwrappedModel(ContractEntry contractEntry) {
		if (contractEntry instanceof ContractEntryImpl) {
			return contractEntry;
		}

		ContractEntryImpl contractEntryImpl = new ContractEntryImpl();

		contractEntryImpl.setNew(contractEntry.isNew());
		contractEntryImpl.setPrimaryKey(contractEntry.getPrimaryKey());

		contractEntryImpl.setContractEntryId(contractEntry.getContractEntryId());
		contractEntryImpl.setUserId(contractEntry.getUserId());
		contractEntryImpl.setUserName(contractEntry.getUserName());
		contractEntryImpl.setCreateDate(contractEntry.getCreateDate());
		contractEntryImpl.setClassNameId(contractEntry.getClassNameId());
		contractEntryImpl.setClassPK(contractEntry.getClassPK());
		contractEntryImpl.setType(contractEntry.getType());
		contractEntryImpl.setVersion(contractEntry.getVersion());
		contractEntryImpl.setContent(contractEntry.getContent());

		return contractEntryImpl;
	}

	/**
	 * Returns the contract entry with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the contract entry
	 * @return the contract entry
	 * @throws com.liferay.portal.NoSuchModelException if a contract entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ContractEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the contract entry with the primary key or throws a {@link com.liferay.osb.NoSuchContractEntryException} if it could not be found.
	 *
	 * @param contractEntryId the primary key of the contract entry
	 * @return the contract entry
	 * @throws com.liferay.osb.NoSuchContractEntryException if a contract entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ContractEntry findByPrimaryKey(long contractEntryId)
		throws NoSuchContractEntryException, SystemException {
		ContractEntry contractEntry = fetchByPrimaryKey(contractEntryId);

		if (contractEntry == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + contractEntryId);
			}

			throw new NoSuchContractEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				contractEntryId);
		}

		return contractEntry;
	}

	/**
	 * Returns the contract entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the contract entry
	 * @return the contract entry, or <code>null</code> if a contract entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ContractEntry fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the contract entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param contractEntryId the primary key of the contract entry
	 * @return the contract entry, or <code>null</code> if a contract entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ContractEntry fetchByPrimaryKey(long contractEntryId)
		throws SystemException {
		ContractEntry contractEntry = (ContractEntry)EntityCacheUtil.getResult(ContractEntryModelImpl.ENTITY_CACHE_ENABLED,
				ContractEntryImpl.class, contractEntryId);

		if (contractEntry == _nullContractEntry) {
			return null;
		}

		if (contractEntry == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				contractEntry = (ContractEntry)session.get(ContractEntryImpl.class,
						Long.valueOf(contractEntryId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (contractEntry != null) {
					cacheResult(contractEntry);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(ContractEntryModelImpl.ENTITY_CACHE_ENABLED,
						ContractEntryImpl.class, contractEntryId,
						_nullContractEntry);
				}

				closeSession(session);
			}
		}

		return contractEntry;
	}

	/**
	 * Returns all the contract entries where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @return the matching contract entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<ContractEntry> findByCN_CP_T(long classNameId, long classPK,
		int type) throws SystemException {
		return findByCN_CP_T(classNameId, classPK, type, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the contract entries where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param start the lower bound of the range of contract entries
	 * @param end the upper bound of the range of contract entries (not inclusive)
	 * @return the range of matching contract entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<ContractEntry> findByCN_CP_T(long classNameId, long classPK,
		int type, int start, int end) throws SystemException {
		return findByCN_CP_T(classNameId, classPK, type, start, end, null);
	}

	/**
	 * Returns an ordered range of all the contract entries where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param start the lower bound of the range of contract entries
	 * @param end the upper bound of the range of contract entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching contract entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<ContractEntry> findByCN_CP_T(long classNameId, long classPK,
		int type, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CN_CP_T;
			finderArgs = new Object[] { classNameId, classPK, type };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CN_CP_T;
			finderArgs = new Object[] {
					classNameId, classPK, type,
					
					start, end, orderByComparator
				};
		}

		List<ContractEntry> list = (List<ContractEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (ContractEntry contractEntry : list) {
				if ((classNameId != contractEntry.getClassNameId()) ||
						(classPK != contractEntry.getClassPK()) ||
						(type != contractEntry.getType())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_CONTRACTENTRY_WHERE);

			query.append(_FINDER_COLUMN_CN_CP_T_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_CN_CP_T_CLASSPK_2);

			query.append(_FINDER_COLUMN_CN_CP_T_TYPE_2);

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

				qPos.add(classNameId);

				qPos.add(classPK);

				qPos.add(type);

				list = (List<ContractEntry>)QueryUtil.list(q, getDialect(),
						start, end);
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
	 * Returns the first contract entry in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contract entry
	 * @throws com.liferay.osb.NoSuchContractEntryException if a matching contract entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ContractEntry findByCN_CP_T_First(long classNameId, long classPK,
		int type, OrderByComparator orderByComparator)
		throws NoSuchContractEntryException, SystemException {
		ContractEntry contractEntry = fetchByCN_CP_T_First(classNameId,
				classPK, type, orderByComparator);

		if (contractEntry != null) {
			return contractEntry;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(", type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchContractEntryException(msg.toString());
	}

	/**
	 * Returns the first contract entry in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contract entry, or <code>null</code> if a matching contract entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ContractEntry fetchByCN_CP_T_First(long classNameId, long classPK,
		int type, OrderByComparator orderByComparator)
		throws SystemException {
		List<ContractEntry> list = findByCN_CP_T(classNameId, classPK, type, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last contract entry in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contract entry
	 * @throws com.liferay.osb.NoSuchContractEntryException if a matching contract entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ContractEntry findByCN_CP_T_Last(long classNameId, long classPK,
		int type, OrderByComparator orderByComparator)
		throws NoSuchContractEntryException, SystemException {
		ContractEntry contractEntry = fetchByCN_CP_T_Last(classNameId, classPK,
				type, orderByComparator);

		if (contractEntry != null) {
			return contractEntry;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(", type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchContractEntryException(msg.toString());
	}

	/**
	 * Returns the last contract entry in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contract entry, or <code>null</code> if a matching contract entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ContractEntry fetchByCN_CP_T_Last(long classNameId, long classPK,
		int type, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByCN_CP_T(classNameId, classPK, type);

		List<ContractEntry> list = findByCN_CP_T(classNameId, classPK, type,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the contract entries before and after the current contract entry in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param contractEntryId the primary key of the current contract entry
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next contract entry
	 * @throws com.liferay.osb.NoSuchContractEntryException if a contract entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ContractEntry[] findByCN_CP_T_PrevAndNext(long contractEntryId,
		long classNameId, long classPK, int type,
		OrderByComparator orderByComparator)
		throws NoSuchContractEntryException, SystemException {
		ContractEntry contractEntry = findByPrimaryKey(contractEntryId);

		Session session = null;

		try {
			session = openSession();

			ContractEntry[] array = new ContractEntryImpl[3];

			array[0] = getByCN_CP_T_PrevAndNext(session, contractEntry,
					classNameId, classPK, type, orderByComparator, true);

			array[1] = contractEntry;

			array[2] = getByCN_CP_T_PrevAndNext(session, contractEntry,
					classNameId, classPK, type, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ContractEntry getByCN_CP_T_PrevAndNext(Session session,
		ContractEntry contractEntry, long classNameId, long classPK, int type,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CONTRACTENTRY_WHERE);

		query.append(_FINDER_COLUMN_CN_CP_T_CLASSNAMEID_2);

		query.append(_FINDER_COLUMN_CN_CP_T_CLASSPK_2);

		query.append(_FINDER_COLUMN_CN_CP_T_TYPE_2);

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

		qPos.add(classNameId);

		qPos.add(classPK);

		qPos.add(type);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(contractEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ContractEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the contract entries.
	 *
	 * @return the contract entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<ContractEntry> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the contract entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of contract entries
	 * @param end the upper bound of the range of contract entries (not inclusive)
	 * @return the range of contract entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<ContractEntry> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the contract entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of contract entries
	 * @param end the upper bound of the range of contract entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of contract entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<ContractEntry> findAll(int start, int end,
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

		List<ContractEntry> list = (List<ContractEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_CONTRACTENTRY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CONTRACTENTRY;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<ContractEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<ContractEntry>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the contract entries where classNameId = &#63; and classPK = &#63; and type = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByCN_CP_T(long classNameId, long classPK, int type)
		throws SystemException {
		for (ContractEntry contractEntry : findByCN_CP_T(classNameId, classPK,
				type)) {
			remove(contractEntry);
		}
	}

	/**
	 * Removes all the contract entries from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (ContractEntry contractEntry : findAll()) {
			remove(contractEntry);
		}
	}

	/**
	 * Returns the number of contract entries where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @return the number of matching contract entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByCN_CP_T(long classNameId, long classPK, int type)
		throws SystemException {
		Object[] finderArgs = new Object[] { classNameId, classPK, type };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_CN_CP_T,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_CONTRACTENTRY_WHERE);

			query.append(_FINDER_COLUMN_CN_CP_T_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_CN_CP_T_CLASSPK_2);

			query.append(_FINDER_COLUMN_CN_CP_T_TYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_CN_CP_T,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of contract entries.
	 *
	 * @return the number of contract entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CONTRACTENTRY);

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
	 * Initializes the contract entry persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.osb.model.ContractEntry")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<ContractEntry>> listenersList = new ArrayList<ModelListener<ContractEntry>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<ContractEntry>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(ContractEntryImpl.class.getName());
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
	private static final String _SQL_SELECT_CONTRACTENTRY = "SELECT contractEntry FROM ContractEntry contractEntry";
	private static final String _SQL_SELECT_CONTRACTENTRY_WHERE = "SELECT contractEntry FROM ContractEntry contractEntry WHERE ";
	private static final String _SQL_COUNT_CONTRACTENTRY = "SELECT COUNT(contractEntry) FROM ContractEntry contractEntry";
	private static final String _SQL_COUNT_CONTRACTENTRY_WHERE = "SELECT COUNT(contractEntry) FROM ContractEntry contractEntry WHERE ";
	private static final String _FINDER_COLUMN_CN_CP_T_CLASSNAMEID_2 = "contractEntry.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_CN_CP_T_CLASSPK_2 = "contractEntry.classPK = ? AND ";
	private static final String _FINDER_COLUMN_CN_CP_T_TYPE_2 = "contractEntry.type = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "contractEntry.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ContractEntry exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ContractEntry exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(ContractEntryPersistenceImpl.class);
	private static ContractEntry _nullContractEntry = new ContractEntryImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<ContractEntry> toCacheModel() {
				return _nullContractEntryCacheModel;
			}
		};

	private static CacheModel<ContractEntry> _nullContractEntryCacheModel = new CacheModel<ContractEntry>() {
			public ContractEntry toEntityModel() {
				return _nullContractEntry;
			}
		};
}