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

import com.liferay.osb.NoSuchContractAuditException;
import com.liferay.osb.model.ContractAudit;
import com.liferay.osb.model.impl.ContractAuditImpl;
import com.liferay.osb.model.impl.ContractAuditModelImpl;

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
 * The persistence implementation for the contract audit service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContractAuditPersistence
 * @see ContractAuditUtil
 * @generated
 */
public class ContractAuditPersistenceImpl extends BasePersistenceImpl<ContractAudit>
	implements ContractAuditPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ContractAuditUtil} to access the contract audit persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ContractAuditImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_U_CEI = new FinderPath(ContractAuditModelImpl.ENTITY_CACHE_ENABLED,
			ContractAuditModelImpl.FINDER_CACHE_ENABLED,
			ContractAuditImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByU_CEI",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_CEI = new FinderPath(ContractAuditModelImpl.ENTITY_CACHE_ENABLED,
			ContractAuditModelImpl.FINDER_CACHE_ENABLED,
			ContractAuditImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByU_CEI",
			new String[] { Long.class.getName(), Long.class.getName() },
			ContractAuditModelImpl.USERID_COLUMN_BITMASK |
			ContractAuditModelImpl.CONTRACTENTRYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_U_CEI = new FinderPath(ContractAuditModelImpl.ENTITY_CACHE_ENABLED,
			ContractAuditModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_CEI",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CEI_SCN_SCP =
		new FinderPath(ContractAuditModelImpl.ENTITY_CACHE_ENABLED,
			ContractAuditModelImpl.FINDER_CACHE_ENABLED,
			ContractAuditImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCEI_SCN_SCP",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CEI_SCN_SCP =
		new FinderPath(ContractAuditModelImpl.ENTITY_CACHE_ENABLED,
			ContractAuditModelImpl.FINDER_CACHE_ENABLED,
			ContractAuditImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByCEI_SCN_SCP",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			},
			ContractAuditModelImpl.CONTRACTENTRYID_COLUMN_BITMASK |
			ContractAuditModelImpl.SIGNATORYCLASSNAMEID_COLUMN_BITMASK |
			ContractAuditModelImpl.SIGNATORYCLASSPK_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CEI_SCN_SCP = new FinderPath(ContractAuditModelImpl.ENTITY_CACHE_ENABLED,
			ContractAuditModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCEI_SCN_SCP",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ContractAuditModelImpl.ENTITY_CACHE_ENABLED,
			ContractAuditModelImpl.FINDER_CACHE_ENABLED,
			ContractAuditImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ContractAuditModelImpl.ENTITY_CACHE_ENABLED,
			ContractAuditModelImpl.FINDER_CACHE_ENABLED,
			ContractAuditImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ContractAuditModelImpl.ENTITY_CACHE_ENABLED,
			ContractAuditModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the contract audit in the entity cache if it is enabled.
	 *
	 * @param contractAudit the contract audit
	 */
	public void cacheResult(ContractAudit contractAudit) {
		EntityCacheUtil.putResult(ContractAuditModelImpl.ENTITY_CACHE_ENABLED,
			ContractAuditImpl.class, contractAudit.getPrimaryKey(),
			contractAudit);

		contractAudit.resetOriginalValues();
	}

	/**
	 * Caches the contract audits in the entity cache if it is enabled.
	 *
	 * @param contractAudits the contract audits
	 */
	public void cacheResult(List<ContractAudit> contractAudits) {
		for (ContractAudit contractAudit : contractAudits) {
			if (EntityCacheUtil.getResult(
						ContractAuditModelImpl.ENTITY_CACHE_ENABLED,
						ContractAuditImpl.class, contractAudit.getPrimaryKey()) == null) {
				cacheResult(contractAudit);
			}
			else {
				contractAudit.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all contract audits.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(ContractAuditImpl.class.getName());
		}

		EntityCacheUtil.clearCache(ContractAuditImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the contract audit.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ContractAudit contractAudit) {
		EntityCacheUtil.removeResult(ContractAuditModelImpl.ENTITY_CACHE_ENABLED,
			ContractAuditImpl.class, contractAudit.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<ContractAudit> contractAudits) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ContractAudit contractAudit : contractAudits) {
			EntityCacheUtil.removeResult(ContractAuditModelImpl.ENTITY_CACHE_ENABLED,
				ContractAuditImpl.class, contractAudit.getPrimaryKey());
		}
	}

	/**
	 * Creates a new contract audit with the primary key. Does not add the contract audit to the database.
	 *
	 * @param contractAuditId the primary key for the new contract audit
	 * @return the new contract audit
	 */
	public ContractAudit create(long contractAuditId) {
		ContractAudit contractAudit = new ContractAuditImpl();

		contractAudit.setNew(true);
		contractAudit.setPrimaryKey(contractAuditId);

		return contractAudit;
	}

	/**
	 * Removes the contract audit with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param contractAuditId the primary key of the contract audit
	 * @return the contract audit that was removed
	 * @throws com.liferay.osb.NoSuchContractAuditException if a contract audit with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ContractAudit remove(long contractAuditId)
		throws NoSuchContractAuditException, SystemException {
		return remove(Long.valueOf(contractAuditId));
	}

	/**
	 * Removes the contract audit with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the contract audit
	 * @return the contract audit that was removed
	 * @throws com.liferay.osb.NoSuchContractAuditException if a contract audit with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ContractAudit remove(Serializable primaryKey)
		throws NoSuchContractAuditException, SystemException {
		Session session = null;

		try {
			session = openSession();

			ContractAudit contractAudit = (ContractAudit)session.get(ContractAuditImpl.class,
					primaryKey);

			if (contractAudit == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchContractAuditException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(contractAudit);
		}
		catch (NoSuchContractAuditException nsee) {
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
	protected ContractAudit removeImpl(ContractAudit contractAudit)
		throws SystemException {
		contractAudit = toUnwrappedModel(contractAudit);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, contractAudit);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(contractAudit);

		return contractAudit;
	}

	@Override
	public ContractAudit updateImpl(
		com.liferay.osb.model.ContractAudit contractAudit, boolean merge)
		throws SystemException {
		contractAudit = toUnwrappedModel(contractAudit);

		boolean isNew = contractAudit.isNew();

		ContractAuditModelImpl contractAuditModelImpl = (ContractAuditModelImpl)contractAudit;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, contractAudit, merge);

			contractAudit.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !ContractAuditModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((contractAuditModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_CEI.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(contractAuditModelImpl.getOriginalUserId()),
						Long.valueOf(contractAuditModelImpl.getOriginalContractEntryId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_U_CEI, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_CEI,
					args);

				args = new Object[] {
						Long.valueOf(contractAuditModelImpl.getUserId()),
						Long.valueOf(contractAuditModelImpl.getContractEntryId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_U_CEI, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_CEI,
					args);
			}

			if ((contractAuditModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CEI_SCN_SCP.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(contractAuditModelImpl.getOriginalContractEntryId()),
						Long.valueOf(contractAuditModelImpl.getOriginalSignatoryClassNameId()),
						Long.valueOf(contractAuditModelImpl.getOriginalSignatoryClassPK())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CEI_SCN_SCP,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CEI_SCN_SCP,
					args);

				args = new Object[] {
						Long.valueOf(contractAuditModelImpl.getContractEntryId()),
						Long.valueOf(contractAuditModelImpl.getSignatoryClassNameId()),
						Long.valueOf(contractAuditModelImpl.getSignatoryClassPK())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CEI_SCN_SCP,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CEI_SCN_SCP,
					args);
			}
		}

		EntityCacheUtil.putResult(ContractAuditModelImpl.ENTITY_CACHE_ENABLED,
			ContractAuditImpl.class, contractAudit.getPrimaryKey(),
			contractAudit);

		return contractAudit;
	}

	protected ContractAudit toUnwrappedModel(ContractAudit contractAudit) {
		if (contractAudit instanceof ContractAuditImpl) {
			return contractAudit;
		}

		ContractAuditImpl contractAuditImpl = new ContractAuditImpl();

		contractAuditImpl.setNew(contractAudit.isNew());
		contractAuditImpl.setPrimaryKey(contractAudit.getPrimaryKey());

		contractAuditImpl.setContractAuditId(contractAudit.getContractAuditId());
		contractAuditImpl.setUserId(contractAudit.getUserId());
		contractAuditImpl.setUserName(contractAudit.getUserName());
		contractAuditImpl.setUserEmailAddress(contractAudit.getUserEmailAddress());
		contractAuditImpl.setCreateDate(contractAudit.getCreateDate());
		contractAuditImpl.setModifiedDate(contractAudit.getModifiedDate());
		contractAuditImpl.setContractEntryId(contractAudit.getContractEntryId());
		contractAuditImpl.setSignatoryClassNameId(contractAudit.getSignatoryClassNameId());
		contractAuditImpl.setSignatoryClassPK(contractAudit.getSignatoryClassPK());
		contractAuditImpl.setProductClassNameId(contractAudit.getProductClassNameId());
		contractAuditImpl.setProductClassPK(contractAudit.getProductClassPK());
		contractAuditImpl.setType(contractAudit.getType());
		contractAuditImpl.setLanguageId(contractAudit.getLanguageId());
		contractAuditImpl.setVersion(contractAudit.getVersion());

		return contractAuditImpl;
	}

	/**
	 * Returns the contract audit with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the contract audit
	 * @return the contract audit
	 * @throws com.liferay.portal.NoSuchModelException if a contract audit with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ContractAudit findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the contract audit with the primary key or throws a {@link com.liferay.osb.NoSuchContractAuditException} if it could not be found.
	 *
	 * @param contractAuditId the primary key of the contract audit
	 * @return the contract audit
	 * @throws com.liferay.osb.NoSuchContractAuditException if a contract audit with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ContractAudit findByPrimaryKey(long contractAuditId)
		throws NoSuchContractAuditException, SystemException {
		ContractAudit contractAudit = fetchByPrimaryKey(contractAuditId);

		if (contractAudit == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + contractAuditId);
			}

			throw new NoSuchContractAuditException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				contractAuditId);
		}

		return contractAudit;
	}

	/**
	 * Returns the contract audit with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the contract audit
	 * @return the contract audit, or <code>null</code> if a contract audit with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ContractAudit fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the contract audit with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param contractAuditId the primary key of the contract audit
	 * @return the contract audit, or <code>null</code> if a contract audit with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ContractAudit fetchByPrimaryKey(long contractAuditId)
		throws SystemException {
		ContractAudit contractAudit = (ContractAudit)EntityCacheUtil.getResult(ContractAuditModelImpl.ENTITY_CACHE_ENABLED,
				ContractAuditImpl.class, contractAuditId);

		if (contractAudit == _nullContractAudit) {
			return null;
		}

		if (contractAudit == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				contractAudit = (ContractAudit)session.get(ContractAuditImpl.class,
						Long.valueOf(contractAuditId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (contractAudit != null) {
					cacheResult(contractAudit);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(ContractAuditModelImpl.ENTITY_CACHE_ENABLED,
						ContractAuditImpl.class, contractAuditId,
						_nullContractAudit);
				}

				closeSession(session);
			}
		}

		return contractAudit;
	}

	/**
	 * Returns all the contract audits where userId = &#63; and contractEntryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param contractEntryId the contract entry ID
	 * @return the matching contract audits
	 * @throws SystemException if a system exception occurred
	 */
	public List<ContractAudit> findByU_CEI(long userId, long contractEntryId)
		throws SystemException {
		return findByU_CEI(userId, contractEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the contract audits where userId = &#63; and contractEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param contractEntryId the contract entry ID
	 * @param start the lower bound of the range of contract audits
	 * @param end the upper bound of the range of contract audits (not inclusive)
	 * @return the range of matching contract audits
	 * @throws SystemException if a system exception occurred
	 */
	public List<ContractAudit> findByU_CEI(long userId, long contractEntryId,
		int start, int end) throws SystemException {
		return findByU_CEI(userId, contractEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the contract audits where userId = &#63; and contractEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param contractEntryId the contract entry ID
	 * @param start the lower bound of the range of contract audits
	 * @param end the upper bound of the range of contract audits (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching contract audits
	 * @throws SystemException if a system exception occurred
	 */
	public List<ContractAudit> findByU_CEI(long userId, long contractEntryId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_CEI;
			finderArgs = new Object[] { userId, contractEntryId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_U_CEI;
			finderArgs = new Object[] {
					userId, contractEntryId,
					
					start, end, orderByComparator
				};
		}

		List<ContractAudit> list = (List<ContractAudit>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (ContractAudit contractAudit : list) {
				if ((userId != contractAudit.getUserId()) ||
						(contractEntryId != contractAudit.getContractEntryId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_CONTRACTAUDIT_WHERE);

			query.append(_FINDER_COLUMN_U_CEI_USERID_2);

			query.append(_FINDER_COLUMN_U_CEI_CONTRACTENTRYID_2);

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

				qPos.add(userId);

				qPos.add(contractEntryId);

				list = (List<ContractAudit>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first contract audit in the ordered set where userId = &#63; and contractEntryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param contractEntryId the contract entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contract audit
	 * @throws com.liferay.osb.NoSuchContractAuditException if a matching contract audit could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ContractAudit findByU_CEI_First(long userId, long contractEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchContractAuditException, SystemException {
		ContractAudit contractAudit = fetchByU_CEI_First(userId,
				contractEntryId, orderByComparator);

		if (contractAudit != null) {
			return contractAudit;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", contractEntryId=");
		msg.append(contractEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchContractAuditException(msg.toString());
	}

	/**
	 * Returns the first contract audit in the ordered set where userId = &#63; and contractEntryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param contractEntryId the contract entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contract audit, or <code>null</code> if a matching contract audit could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ContractAudit fetchByU_CEI_First(long userId, long contractEntryId,
		OrderByComparator orderByComparator) throws SystemException {
		List<ContractAudit> list = findByU_CEI(userId, contractEntryId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last contract audit in the ordered set where userId = &#63; and contractEntryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param contractEntryId the contract entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contract audit
	 * @throws com.liferay.osb.NoSuchContractAuditException if a matching contract audit could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ContractAudit findByU_CEI_Last(long userId, long contractEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchContractAuditException, SystemException {
		ContractAudit contractAudit = fetchByU_CEI_Last(userId,
				contractEntryId, orderByComparator);

		if (contractAudit != null) {
			return contractAudit;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", contractEntryId=");
		msg.append(contractEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchContractAuditException(msg.toString());
	}

	/**
	 * Returns the last contract audit in the ordered set where userId = &#63; and contractEntryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param contractEntryId the contract entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contract audit, or <code>null</code> if a matching contract audit could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ContractAudit fetchByU_CEI_Last(long userId, long contractEntryId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByU_CEI(userId, contractEntryId);

		List<ContractAudit> list = findByU_CEI(userId, contractEntryId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the contract audits before and after the current contract audit in the ordered set where userId = &#63; and contractEntryId = &#63;.
	 *
	 * @param contractAuditId the primary key of the current contract audit
	 * @param userId the user ID
	 * @param contractEntryId the contract entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next contract audit
	 * @throws com.liferay.osb.NoSuchContractAuditException if a contract audit with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ContractAudit[] findByU_CEI_PrevAndNext(long contractAuditId,
		long userId, long contractEntryId, OrderByComparator orderByComparator)
		throws NoSuchContractAuditException, SystemException {
		ContractAudit contractAudit = findByPrimaryKey(contractAuditId);

		Session session = null;

		try {
			session = openSession();

			ContractAudit[] array = new ContractAuditImpl[3];

			array[0] = getByU_CEI_PrevAndNext(session, contractAudit, userId,
					contractEntryId, orderByComparator, true);

			array[1] = contractAudit;

			array[2] = getByU_CEI_PrevAndNext(session, contractAudit, userId,
					contractEntryId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ContractAudit getByU_CEI_PrevAndNext(Session session,
		ContractAudit contractAudit, long userId, long contractEntryId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CONTRACTAUDIT_WHERE);

		query.append(_FINDER_COLUMN_U_CEI_USERID_2);

		query.append(_FINDER_COLUMN_U_CEI_CONTRACTENTRYID_2);

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

		qPos.add(userId);

		qPos.add(contractEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(contractAudit);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ContractAudit> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the contract audits where contractEntryId = &#63; and signatoryClassNameId = &#63; and signatoryClassPK = &#63;.
	 *
	 * @param contractEntryId the contract entry ID
	 * @param signatoryClassNameId the signatory class name ID
	 * @param signatoryClassPK the signatory class p k
	 * @return the matching contract audits
	 * @throws SystemException if a system exception occurred
	 */
	public List<ContractAudit> findByCEI_SCN_SCP(long contractEntryId,
		long signatoryClassNameId, long signatoryClassPK)
		throws SystemException {
		return findByCEI_SCN_SCP(contractEntryId, signatoryClassNameId,
			signatoryClassPK, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the contract audits where contractEntryId = &#63; and signatoryClassNameId = &#63; and signatoryClassPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param contractEntryId the contract entry ID
	 * @param signatoryClassNameId the signatory class name ID
	 * @param signatoryClassPK the signatory class p k
	 * @param start the lower bound of the range of contract audits
	 * @param end the upper bound of the range of contract audits (not inclusive)
	 * @return the range of matching contract audits
	 * @throws SystemException if a system exception occurred
	 */
	public List<ContractAudit> findByCEI_SCN_SCP(long contractEntryId,
		long signatoryClassNameId, long signatoryClassPK, int start, int end)
		throws SystemException {
		return findByCEI_SCN_SCP(contractEntryId, signatoryClassNameId,
			signatoryClassPK, start, end, null);
	}

	/**
	 * Returns an ordered range of all the contract audits where contractEntryId = &#63; and signatoryClassNameId = &#63; and signatoryClassPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param contractEntryId the contract entry ID
	 * @param signatoryClassNameId the signatory class name ID
	 * @param signatoryClassPK the signatory class p k
	 * @param start the lower bound of the range of contract audits
	 * @param end the upper bound of the range of contract audits (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching contract audits
	 * @throws SystemException if a system exception occurred
	 */
	public List<ContractAudit> findByCEI_SCN_SCP(long contractEntryId,
		long signatoryClassNameId, long signatoryClassPK, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CEI_SCN_SCP;
			finderArgs = new Object[] {
					contractEntryId, signatoryClassNameId, signatoryClassPK
				};
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CEI_SCN_SCP;
			finderArgs = new Object[] {
					contractEntryId, signatoryClassNameId, signatoryClassPK,
					
					start, end, orderByComparator
				};
		}

		List<ContractAudit> list = (List<ContractAudit>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (ContractAudit contractAudit : list) {
				if ((contractEntryId != contractAudit.getContractEntryId()) ||
						(signatoryClassNameId != contractAudit.getSignatoryClassNameId()) ||
						(signatoryClassPK != contractAudit.getSignatoryClassPK())) {
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

			query.append(_SQL_SELECT_CONTRACTAUDIT_WHERE);

			query.append(_FINDER_COLUMN_CEI_SCN_SCP_CONTRACTENTRYID_2);

			query.append(_FINDER_COLUMN_CEI_SCN_SCP_SIGNATORYCLASSNAMEID_2);

			query.append(_FINDER_COLUMN_CEI_SCN_SCP_SIGNATORYCLASSPK_2);

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

				qPos.add(contractEntryId);

				qPos.add(signatoryClassNameId);

				qPos.add(signatoryClassPK);

				list = (List<ContractAudit>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first contract audit in the ordered set where contractEntryId = &#63; and signatoryClassNameId = &#63; and signatoryClassPK = &#63;.
	 *
	 * @param contractEntryId the contract entry ID
	 * @param signatoryClassNameId the signatory class name ID
	 * @param signatoryClassPK the signatory class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contract audit
	 * @throws com.liferay.osb.NoSuchContractAuditException if a matching contract audit could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ContractAudit findByCEI_SCN_SCP_First(long contractEntryId,
		long signatoryClassNameId, long signatoryClassPK,
		OrderByComparator orderByComparator)
		throws NoSuchContractAuditException, SystemException {
		ContractAudit contractAudit = fetchByCEI_SCN_SCP_First(contractEntryId,
				signatoryClassNameId, signatoryClassPK, orderByComparator);

		if (contractAudit != null) {
			return contractAudit;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("contractEntryId=");
		msg.append(contractEntryId);

		msg.append(", signatoryClassNameId=");
		msg.append(signatoryClassNameId);

		msg.append(", signatoryClassPK=");
		msg.append(signatoryClassPK);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchContractAuditException(msg.toString());
	}

	/**
	 * Returns the first contract audit in the ordered set where contractEntryId = &#63; and signatoryClassNameId = &#63; and signatoryClassPK = &#63;.
	 *
	 * @param contractEntryId the contract entry ID
	 * @param signatoryClassNameId the signatory class name ID
	 * @param signatoryClassPK the signatory class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contract audit, or <code>null</code> if a matching contract audit could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ContractAudit fetchByCEI_SCN_SCP_First(long contractEntryId,
		long signatoryClassNameId, long signatoryClassPK,
		OrderByComparator orderByComparator) throws SystemException {
		List<ContractAudit> list = findByCEI_SCN_SCP(contractEntryId,
				signatoryClassNameId, signatoryClassPK, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last contract audit in the ordered set where contractEntryId = &#63; and signatoryClassNameId = &#63; and signatoryClassPK = &#63;.
	 *
	 * @param contractEntryId the contract entry ID
	 * @param signatoryClassNameId the signatory class name ID
	 * @param signatoryClassPK the signatory class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contract audit
	 * @throws com.liferay.osb.NoSuchContractAuditException if a matching contract audit could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ContractAudit findByCEI_SCN_SCP_Last(long contractEntryId,
		long signatoryClassNameId, long signatoryClassPK,
		OrderByComparator orderByComparator)
		throws NoSuchContractAuditException, SystemException {
		ContractAudit contractAudit = fetchByCEI_SCN_SCP_Last(contractEntryId,
				signatoryClassNameId, signatoryClassPK, orderByComparator);

		if (contractAudit != null) {
			return contractAudit;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("contractEntryId=");
		msg.append(contractEntryId);

		msg.append(", signatoryClassNameId=");
		msg.append(signatoryClassNameId);

		msg.append(", signatoryClassPK=");
		msg.append(signatoryClassPK);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchContractAuditException(msg.toString());
	}

	/**
	 * Returns the last contract audit in the ordered set where contractEntryId = &#63; and signatoryClassNameId = &#63; and signatoryClassPK = &#63;.
	 *
	 * @param contractEntryId the contract entry ID
	 * @param signatoryClassNameId the signatory class name ID
	 * @param signatoryClassPK the signatory class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contract audit, or <code>null</code> if a matching contract audit could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ContractAudit fetchByCEI_SCN_SCP_Last(long contractEntryId,
		long signatoryClassNameId, long signatoryClassPK,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByCEI_SCN_SCP(contractEntryId, signatoryClassNameId,
				signatoryClassPK);

		List<ContractAudit> list = findByCEI_SCN_SCP(contractEntryId,
				signatoryClassNameId, signatoryClassPK, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the contract audits before and after the current contract audit in the ordered set where contractEntryId = &#63; and signatoryClassNameId = &#63; and signatoryClassPK = &#63;.
	 *
	 * @param contractAuditId the primary key of the current contract audit
	 * @param contractEntryId the contract entry ID
	 * @param signatoryClassNameId the signatory class name ID
	 * @param signatoryClassPK the signatory class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next contract audit
	 * @throws com.liferay.osb.NoSuchContractAuditException if a contract audit with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ContractAudit[] findByCEI_SCN_SCP_PrevAndNext(long contractAuditId,
		long contractEntryId, long signatoryClassNameId, long signatoryClassPK,
		OrderByComparator orderByComparator)
		throws NoSuchContractAuditException, SystemException {
		ContractAudit contractAudit = findByPrimaryKey(contractAuditId);

		Session session = null;

		try {
			session = openSession();

			ContractAudit[] array = new ContractAuditImpl[3];

			array[0] = getByCEI_SCN_SCP_PrevAndNext(session, contractAudit,
					contractEntryId, signatoryClassNameId, signatoryClassPK,
					orderByComparator, true);

			array[1] = contractAudit;

			array[2] = getByCEI_SCN_SCP_PrevAndNext(session, contractAudit,
					contractEntryId, signatoryClassNameId, signatoryClassPK,
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

	protected ContractAudit getByCEI_SCN_SCP_PrevAndNext(Session session,
		ContractAudit contractAudit, long contractEntryId,
		long signatoryClassNameId, long signatoryClassPK,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CONTRACTAUDIT_WHERE);

		query.append(_FINDER_COLUMN_CEI_SCN_SCP_CONTRACTENTRYID_2);

		query.append(_FINDER_COLUMN_CEI_SCN_SCP_SIGNATORYCLASSNAMEID_2);

		query.append(_FINDER_COLUMN_CEI_SCN_SCP_SIGNATORYCLASSPK_2);

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

		qPos.add(contractEntryId);

		qPos.add(signatoryClassNameId);

		qPos.add(signatoryClassPK);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(contractAudit);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ContractAudit> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the contract audits.
	 *
	 * @return the contract audits
	 * @throws SystemException if a system exception occurred
	 */
	public List<ContractAudit> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the contract audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of contract audits
	 * @param end the upper bound of the range of contract audits (not inclusive)
	 * @return the range of contract audits
	 * @throws SystemException if a system exception occurred
	 */
	public List<ContractAudit> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the contract audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of contract audits
	 * @param end the upper bound of the range of contract audits (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of contract audits
	 * @throws SystemException if a system exception occurred
	 */
	public List<ContractAudit> findAll(int start, int end,
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

		List<ContractAudit> list = (List<ContractAudit>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_CONTRACTAUDIT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CONTRACTAUDIT;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<ContractAudit>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<ContractAudit>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the contract audits where userId = &#63; and contractEntryId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param contractEntryId the contract entry ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByU_CEI(long userId, long contractEntryId)
		throws SystemException {
		for (ContractAudit contractAudit : findByU_CEI(userId, contractEntryId)) {
			remove(contractAudit);
		}
	}

	/**
	 * Removes all the contract audits where contractEntryId = &#63; and signatoryClassNameId = &#63; and signatoryClassPK = &#63; from the database.
	 *
	 * @param contractEntryId the contract entry ID
	 * @param signatoryClassNameId the signatory class name ID
	 * @param signatoryClassPK the signatory class p k
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByCEI_SCN_SCP(long contractEntryId,
		long signatoryClassNameId, long signatoryClassPK)
		throws SystemException {
		for (ContractAudit contractAudit : findByCEI_SCN_SCP(contractEntryId,
				signatoryClassNameId, signatoryClassPK)) {
			remove(contractAudit);
		}
	}

	/**
	 * Removes all the contract audits from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (ContractAudit contractAudit : findAll()) {
			remove(contractAudit);
		}
	}

	/**
	 * Returns the number of contract audits where userId = &#63; and contractEntryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param contractEntryId the contract entry ID
	 * @return the number of matching contract audits
	 * @throws SystemException if a system exception occurred
	 */
	public int countByU_CEI(long userId, long contractEntryId)
		throws SystemException {
		Object[] finderArgs = new Object[] { userId, contractEntryId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_U_CEI,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CONTRACTAUDIT_WHERE);

			query.append(_FINDER_COLUMN_U_CEI_USERID_2);

			query.append(_FINDER_COLUMN_U_CEI_CONTRACTENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(contractEntryId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_U_CEI,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of contract audits where contractEntryId = &#63; and signatoryClassNameId = &#63; and signatoryClassPK = &#63;.
	 *
	 * @param contractEntryId the contract entry ID
	 * @param signatoryClassNameId the signatory class name ID
	 * @param signatoryClassPK the signatory class p k
	 * @return the number of matching contract audits
	 * @throws SystemException if a system exception occurred
	 */
	public int countByCEI_SCN_SCP(long contractEntryId,
		long signatoryClassNameId, long signatoryClassPK)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				contractEntryId, signatoryClassNameId, signatoryClassPK
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_CEI_SCN_SCP,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_CONTRACTAUDIT_WHERE);

			query.append(_FINDER_COLUMN_CEI_SCN_SCP_CONTRACTENTRYID_2);

			query.append(_FINDER_COLUMN_CEI_SCN_SCP_SIGNATORYCLASSNAMEID_2);

			query.append(_FINDER_COLUMN_CEI_SCN_SCP_SIGNATORYCLASSPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(contractEntryId);

				qPos.add(signatoryClassNameId);

				qPos.add(signatoryClassPK);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_CEI_SCN_SCP,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of contract audits.
	 *
	 * @return the number of contract audits
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CONTRACTAUDIT);

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
	 * Initializes the contract audit persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.osb.model.ContractAudit")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<ContractAudit>> listenersList = new ArrayList<ModelListener<ContractAudit>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<ContractAudit>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(ContractAuditImpl.class.getName());
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
	private static final String _SQL_SELECT_CONTRACTAUDIT = "SELECT contractAudit FROM ContractAudit contractAudit";
	private static final String _SQL_SELECT_CONTRACTAUDIT_WHERE = "SELECT contractAudit FROM ContractAudit contractAudit WHERE ";
	private static final String _SQL_COUNT_CONTRACTAUDIT = "SELECT COUNT(contractAudit) FROM ContractAudit contractAudit";
	private static final String _SQL_COUNT_CONTRACTAUDIT_WHERE = "SELECT COUNT(contractAudit) FROM ContractAudit contractAudit WHERE ";
	private static final String _FINDER_COLUMN_U_CEI_USERID_2 = "contractAudit.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_CEI_CONTRACTENTRYID_2 = "contractAudit.contractEntryId = ?";
	private static final String _FINDER_COLUMN_CEI_SCN_SCP_CONTRACTENTRYID_2 = "contractAudit.contractEntryId = ? AND ";
	private static final String _FINDER_COLUMN_CEI_SCN_SCP_SIGNATORYCLASSNAMEID_2 =
		"contractAudit.signatoryClassNameId = ? AND ";
	private static final String _FINDER_COLUMN_CEI_SCN_SCP_SIGNATORYCLASSPK_2 = "contractAudit.signatoryClassPK = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "contractAudit.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ContractAudit exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ContractAudit exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(ContractAuditPersistenceImpl.class);
	private static ContractAudit _nullContractAudit = new ContractAuditImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<ContractAudit> toCacheModel() {
				return _nullContractAuditCacheModel;
			}
		};

	private static CacheModel<ContractAudit> _nullContractAuditCacheModel = new CacheModel<ContractAudit>() {
			public ContractAudit toEntityModel() {
				return _nullContractAudit;
			}
		};
}