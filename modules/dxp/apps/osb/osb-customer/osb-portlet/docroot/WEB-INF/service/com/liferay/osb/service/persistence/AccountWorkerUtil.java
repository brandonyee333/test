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

import com.liferay.osb.model.AccountWorker;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the account worker service. This utility wraps {@link AccountWorkerPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountWorkerPersistence
 * @see AccountWorkerPersistenceImpl
 * @generated
 */
public class AccountWorkerUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(AccountWorker accountWorker) {
		getPersistence().clearCache(accountWorker);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<AccountWorker> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AccountWorker> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AccountWorker> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static AccountWorker update(AccountWorker accountWorker,
		boolean merge) throws SystemException {
		return getPersistence().update(accountWorker, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static AccountWorker update(AccountWorker accountWorker,
		boolean merge, ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(accountWorker, merge, serviceContext);
	}

	/**
	* Caches the account worker in the entity cache if it is enabled.
	*
	* @param accountWorker the account worker
	*/
	public static void cacheResult(
		com.liferay.osb.model.AccountWorker accountWorker) {
		getPersistence().cacheResult(accountWorker);
	}

	/**
	* Caches the account workers in the entity cache if it is enabled.
	*
	* @param accountWorkers the account workers
	*/
	public static void cacheResult(
		java.util.List<com.liferay.osb.model.AccountWorker> accountWorkers) {
		getPersistence().cacheResult(accountWorkers);
	}

	/**
	* Creates a new account worker with the primary key. Does not add the account worker to the database.
	*
	* @param accountWorkerId the primary key for the new account worker
	* @return the new account worker
	*/
	public static com.liferay.osb.model.AccountWorker create(
		long accountWorkerId) {
		return getPersistence().create(accountWorkerId);
	}

	/**
	* Removes the account worker with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accountWorkerId the primary key of the account worker
	* @return the account worker that was removed
	* @throws com.liferay.osb.NoSuchAccountWorkerException if a account worker with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountWorker remove(
		long accountWorkerId)
		throws com.liferay.osb.NoSuchAccountWorkerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(accountWorkerId);
	}

	public static com.liferay.osb.model.AccountWorker updateImpl(
		com.liferay.osb.model.AccountWorker accountWorker, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(accountWorker, merge);
	}

	/**
	* Returns the account worker with the primary key or throws a {@link com.liferay.osb.NoSuchAccountWorkerException} if it could not be found.
	*
	* @param accountWorkerId the primary key of the account worker
	* @return the account worker
	* @throws com.liferay.osb.NoSuchAccountWorkerException if a account worker with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountWorker findByPrimaryKey(
		long accountWorkerId)
		throws com.liferay.osb.NoSuchAccountWorkerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(accountWorkerId);
	}

	/**
	* Returns the account worker with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param accountWorkerId the primary key of the account worker
	* @return the account worker, or <code>null</code> if a account worker with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountWorker fetchByPrimaryKey(
		long accountWorkerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(accountWorkerId);
	}

	/**
	* Returns all the account workers where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching account workers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountWorker> findByUserId(
		long userId) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId(userId);
	}

	/**
	* Returns a range of all the account workers where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of account workers
	* @param end the upper bound of the range of account workers (not inclusive)
	* @return the range of matching account workers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountWorker> findByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId(userId, start, end);
	}

	/**
	* Returns an ordered range of all the account workers where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of account workers
	* @param end the upper bound of the range of account workers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching account workers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountWorker> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserId(userId, start, end, orderByComparator);
	}

	/**
	* Returns the first account worker in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account worker
	* @throws com.liferay.osb.NoSuchAccountWorkerException if a matching account worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountWorker findByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountWorkerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the first account worker in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account worker, or <code>null</code> if a matching account worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountWorker fetchByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the last account worker in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account worker
	* @throws com.liferay.osb.NoSuchAccountWorkerException if a matching account worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountWorker findByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountWorkerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the last account worker in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account worker, or <code>null</code> if a matching account worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountWorker fetchByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the account workers before and after the current account worker in the ordered set where userId = &#63;.
	*
	* @param accountWorkerId the primary key of the current account worker
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next account worker
	* @throws com.liferay.osb.NoSuchAccountWorkerException if a account worker with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountWorker[] findByUserId_PrevAndNext(
		long accountWorkerId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountWorkerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserId_PrevAndNext(accountWorkerId, userId,
			orderByComparator);
	}

	/**
	* Returns all the account workers where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @return the matching account workers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountWorker> findByAccountEntryId(
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAccountEntryId(accountEntryId);
	}

	/**
	* Returns a range of all the account workers where accountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param start the lower bound of the range of account workers
	* @param end the upper bound of the range of account workers (not inclusive)
	* @return the range of matching account workers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountWorker> findByAccountEntryId(
		long accountEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAccountEntryId(accountEntryId, start, end);
	}

	/**
	* Returns an ordered range of all the account workers where accountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param start the lower bound of the range of account workers
	* @param end the upper bound of the range of account workers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching account workers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountWorker> findByAccountEntryId(
		long accountEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAccountEntryId(accountEntryId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first account worker in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account worker
	* @throws com.liferay.osb.NoSuchAccountWorkerException if a matching account worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountWorker findByAccountEntryId_First(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountWorkerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAccountEntryId_First(accountEntryId, orderByComparator);
	}

	/**
	* Returns the first account worker in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account worker, or <code>null</code> if a matching account worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountWorker fetchByAccountEntryId_First(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAccountEntryId_First(accountEntryId,
			orderByComparator);
	}

	/**
	* Returns the last account worker in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account worker
	* @throws com.liferay.osb.NoSuchAccountWorkerException if a matching account worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountWorker findByAccountEntryId_Last(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountWorkerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAccountEntryId_Last(accountEntryId, orderByComparator);
	}

	/**
	* Returns the last account worker in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account worker, or <code>null</code> if a matching account worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountWorker fetchByAccountEntryId_Last(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAccountEntryId_Last(accountEntryId, orderByComparator);
	}

	/**
	* Returns the account workers before and after the current account worker in the ordered set where accountEntryId = &#63;.
	*
	* @param accountWorkerId the primary key of the current account worker
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next account worker
	* @throws com.liferay.osb.NoSuchAccountWorkerException if a account worker with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountWorker[] findByAccountEntryId_PrevAndNext(
		long accountWorkerId, long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountWorkerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAccountEntryId_PrevAndNext(accountWorkerId,
			accountEntryId, orderByComparator);
	}

	/**
	* Returns the account worker where userId = &#63; and accountEntryId = &#63; or throws a {@link com.liferay.osb.NoSuchAccountWorkerException} if it could not be found.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @return the matching account worker
	* @throws com.liferay.osb.NoSuchAccountWorkerException if a matching account worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountWorker findByU_AEI(long userId,
		long accountEntryId)
		throws com.liferay.osb.NoSuchAccountWorkerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByU_AEI(userId, accountEntryId);
	}

	/**
	* Returns the account worker where userId = &#63; and accountEntryId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @return the matching account worker, or <code>null</code> if a matching account worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountWorker fetchByU_AEI(
		long userId, long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByU_AEI(userId, accountEntryId);
	}

	/**
	* Returns the account worker where userId = &#63; and accountEntryId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching account worker, or <code>null</code> if a matching account worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountWorker fetchByU_AEI(
		long userId, long accountEntryId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByU_AEI(userId, accountEntryId, retrieveFromCache);
	}

	/**
	* Returns all the account workers where userId = &#63; and role = &#63;.
	*
	* @param userId the user ID
	* @param role the role
	* @return the matching account workers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountWorker> findByU_R(
		long userId, int role)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByU_R(userId, role);
	}

	/**
	* Returns a range of all the account workers where userId = &#63; and role = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param role the role
	* @param start the lower bound of the range of account workers
	* @param end the upper bound of the range of account workers (not inclusive)
	* @return the range of matching account workers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountWorker> findByU_R(
		long userId, int role, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByU_R(userId, role, start, end);
	}

	/**
	* Returns an ordered range of all the account workers where userId = &#63; and role = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param role the role
	* @param start the lower bound of the range of account workers
	* @param end the upper bound of the range of account workers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching account workers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountWorker> findByU_R(
		long userId, int role, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_R(userId, role, start, end, orderByComparator);
	}

	/**
	* Returns the first account worker in the ordered set where userId = &#63; and role = &#63;.
	*
	* @param userId the user ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account worker
	* @throws com.liferay.osb.NoSuchAccountWorkerException if a matching account worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountWorker findByU_R_First(
		long userId, int role,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountWorkerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByU_R_First(userId, role, orderByComparator);
	}

	/**
	* Returns the first account worker in the ordered set where userId = &#63; and role = &#63;.
	*
	* @param userId the user ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account worker, or <code>null</code> if a matching account worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountWorker fetchByU_R_First(
		long userId, int role,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByU_R_First(userId, role, orderByComparator);
	}

	/**
	* Returns the last account worker in the ordered set where userId = &#63; and role = &#63;.
	*
	* @param userId the user ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account worker
	* @throws com.liferay.osb.NoSuchAccountWorkerException if a matching account worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountWorker findByU_R_Last(
		long userId, int role,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountWorkerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByU_R_Last(userId, role, orderByComparator);
	}

	/**
	* Returns the last account worker in the ordered set where userId = &#63; and role = &#63;.
	*
	* @param userId the user ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account worker, or <code>null</code> if a matching account worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountWorker fetchByU_R_Last(
		long userId, int role,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByU_R_Last(userId, role, orderByComparator);
	}

	/**
	* Returns the account workers before and after the current account worker in the ordered set where userId = &#63; and role = &#63;.
	*
	* @param accountWorkerId the primary key of the current account worker
	* @param userId the user ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next account worker
	* @throws com.liferay.osb.NoSuchAccountWorkerException if a account worker with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountWorker[] findByU_R_PrevAndNext(
		long accountWorkerId, long userId, int role,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountWorkerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_R_PrevAndNext(accountWorkerId, userId, role,
			orderByComparator);
	}

	/**
	* Returns all the account workers where accountEntryId = &#63; and role = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param role the role
	* @return the matching account workers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountWorker> findByAEI_R(
		long accountEntryId, int role)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAEI_R(accountEntryId, role);
	}

	/**
	* Returns a range of all the account workers where accountEntryId = &#63; and role = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param role the role
	* @param start the lower bound of the range of account workers
	* @param end the upper bound of the range of account workers (not inclusive)
	* @return the range of matching account workers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountWorker> findByAEI_R(
		long accountEntryId, int role, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAEI_R(accountEntryId, role, start, end);
	}

	/**
	* Returns an ordered range of all the account workers where accountEntryId = &#63; and role = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param role the role
	* @param start the lower bound of the range of account workers
	* @param end the upper bound of the range of account workers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching account workers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountWorker> findByAEI_R(
		long accountEntryId, int role, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAEI_R(accountEntryId, role, start, end,
			orderByComparator);
	}

	/**
	* Returns the first account worker in the ordered set where accountEntryId = &#63; and role = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account worker
	* @throws com.liferay.osb.NoSuchAccountWorkerException if a matching account worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountWorker findByAEI_R_First(
		long accountEntryId, int role,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountWorkerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAEI_R_First(accountEntryId, role, orderByComparator);
	}

	/**
	* Returns the first account worker in the ordered set where accountEntryId = &#63; and role = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account worker, or <code>null</code> if a matching account worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountWorker fetchByAEI_R_First(
		long accountEntryId, int role,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAEI_R_First(accountEntryId, role, orderByComparator);
	}

	/**
	* Returns the last account worker in the ordered set where accountEntryId = &#63; and role = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account worker
	* @throws com.liferay.osb.NoSuchAccountWorkerException if a matching account worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountWorker findByAEI_R_Last(
		long accountEntryId, int role,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountWorkerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAEI_R_Last(accountEntryId, role, orderByComparator);
	}

	/**
	* Returns the last account worker in the ordered set where accountEntryId = &#63; and role = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account worker, or <code>null</code> if a matching account worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountWorker fetchByAEI_R_Last(
		long accountEntryId, int role,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAEI_R_Last(accountEntryId, role, orderByComparator);
	}

	/**
	* Returns the account workers before and after the current account worker in the ordered set where accountEntryId = &#63; and role = &#63;.
	*
	* @param accountWorkerId the primary key of the current account worker
	* @param accountEntryId the account entry ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next account worker
	* @throws com.liferay.osb.NoSuchAccountWorkerException if a account worker with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountWorker[] findByAEI_R_PrevAndNext(
		long accountWorkerId, long accountEntryId, int role,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountWorkerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAEI_R_PrevAndNext(accountWorkerId, accountEntryId,
			role, orderByComparator);
	}

	/**
	* Returns all the account workers where accountEntryId = &#63; and notifications &ne; &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param notifications the notifications
	* @return the matching account workers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountWorker> findByAEI_NotN(
		long accountEntryId, int notifications)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAEI_NotN(accountEntryId, notifications);
	}

	/**
	* Returns a range of all the account workers where accountEntryId = &#63; and notifications &ne; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param notifications the notifications
	* @param start the lower bound of the range of account workers
	* @param end the upper bound of the range of account workers (not inclusive)
	* @return the range of matching account workers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountWorker> findByAEI_NotN(
		long accountEntryId, int notifications, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAEI_NotN(accountEntryId, notifications, start, end);
	}

	/**
	* Returns an ordered range of all the account workers where accountEntryId = &#63; and notifications &ne; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param notifications the notifications
	* @param start the lower bound of the range of account workers
	* @param end the upper bound of the range of account workers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching account workers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountWorker> findByAEI_NotN(
		long accountEntryId, int notifications, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAEI_NotN(accountEntryId, notifications, start, end,
			orderByComparator);
	}

	/**
	* Returns the first account worker in the ordered set where accountEntryId = &#63; and notifications &ne; &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param notifications the notifications
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account worker
	* @throws com.liferay.osb.NoSuchAccountWorkerException if a matching account worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountWorker findByAEI_NotN_First(
		long accountEntryId, int notifications,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountWorkerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAEI_NotN_First(accountEntryId, notifications,
			orderByComparator);
	}

	/**
	* Returns the first account worker in the ordered set where accountEntryId = &#63; and notifications &ne; &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param notifications the notifications
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account worker, or <code>null</code> if a matching account worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountWorker fetchByAEI_NotN_First(
		long accountEntryId, int notifications,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAEI_NotN_First(accountEntryId, notifications,
			orderByComparator);
	}

	/**
	* Returns the last account worker in the ordered set where accountEntryId = &#63; and notifications &ne; &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param notifications the notifications
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account worker
	* @throws com.liferay.osb.NoSuchAccountWorkerException if a matching account worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountWorker findByAEI_NotN_Last(
		long accountEntryId, int notifications,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountWorkerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAEI_NotN_Last(accountEntryId, notifications,
			orderByComparator);
	}

	/**
	* Returns the last account worker in the ordered set where accountEntryId = &#63; and notifications &ne; &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param notifications the notifications
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account worker, or <code>null</code> if a matching account worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountWorker fetchByAEI_NotN_Last(
		long accountEntryId, int notifications,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAEI_NotN_Last(accountEntryId, notifications,
			orderByComparator);
	}

	/**
	* Returns the account workers before and after the current account worker in the ordered set where accountEntryId = &#63; and notifications &ne; &#63;.
	*
	* @param accountWorkerId the primary key of the current account worker
	* @param accountEntryId the account entry ID
	* @param notifications the notifications
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next account worker
	* @throws com.liferay.osb.NoSuchAccountWorkerException if a account worker with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountWorker[] findByAEI_NotN_PrevAndNext(
		long accountWorkerId, long accountEntryId, int notifications,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountWorkerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAEI_NotN_PrevAndNext(accountWorkerId, accountEntryId,
			notifications, orderByComparator);
	}

	/**
	* Returns all the account workers.
	*
	* @return the account workers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountWorker> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the account workers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of account workers
	* @param end the upper bound of the range of account workers (not inclusive)
	* @return the range of account workers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountWorker> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the account workers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of account workers
	* @param end the upper bound of the range of account workers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of account workers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountWorker> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the account workers where userId = &#63; from the database.
	*
	* @param userId the user ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUserId(userId);
	}

	/**
	* Removes all the account workers where accountEntryId = &#63; from the database.
	*
	* @param accountEntryId the account entry ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByAccountEntryId(long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByAccountEntryId(accountEntryId);
	}

	/**
	* Removes the account worker where userId = &#63; and accountEntryId = &#63; from the database.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @return the account worker that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountWorker removeByU_AEI(
		long userId, long accountEntryId)
		throws com.liferay.osb.NoSuchAccountWorkerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByU_AEI(userId, accountEntryId);
	}

	/**
	* Removes all the account workers where userId = &#63; and role = &#63; from the database.
	*
	* @param userId the user ID
	* @param role the role
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByU_R(long userId, int role)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByU_R(userId, role);
	}

	/**
	* Removes all the account workers where accountEntryId = &#63; and role = &#63; from the database.
	*
	* @param accountEntryId the account entry ID
	* @param role the role
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByAEI_R(long accountEntryId, int role)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByAEI_R(accountEntryId, role);
	}

	/**
	* Removes all the account workers where accountEntryId = &#63; and notifications &ne; &#63; from the database.
	*
	* @param accountEntryId the account entry ID
	* @param notifications the notifications
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByAEI_NotN(long accountEntryId, int notifications)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByAEI_NotN(accountEntryId, notifications);
	}

	/**
	* Removes all the account workers from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of account workers where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching account workers
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUserId(userId);
	}

	/**
	* Returns the number of account workers where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @return the number of matching account workers
	* @throws SystemException if a system exception occurred
	*/
	public static int countByAccountEntryId(long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByAccountEntryId(accountEntryId);
	}

	/**
	* Returns the number of account workers where userId = &#63; and accountEntryId = &#63;.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @return the number of matching account workers
	* @throws SystemException if a system exception occurred
	*/
	public static int countByU_AEI(long userId, long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByU_AEI(userId, accountEntryId);
	}

	/**
	* Returns the number of account workers where userId = &#63; and role = &#63;.
	*
	* @param userId the user ID
	* @param role the role
	* @return the number of matching account workers
	* @throws SystemException if a system exception occurred
	*/
	public static int countByU_R(long userId, int role)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByU_R(userId, role);
	}

	/**
	* Returns the number of account workers where accountEntryId = &#63; and role = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param role the role
	* @return the number of matching account workers
	* @throws SystemException if a system exception occurred
	*/
	public static int countByAEI_R(long accountEntryId, int role)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByAEI_R(accountEntryId, role);
	}

	/**
	* Returns the number of account workers where accountEntryId = &#63; and notifications &ne; &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param notifications the notifications
	* @return the number of matching account workers
	* @throws SystemException if a system exception occurred
	*/
	public static int countByAEI_NotN(long accountEntryId, int notifications)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByAEI_NotN(accountEntryId, notifications);
	}

	/**
	* Returns the number of account workers.
	*
	* @return the number of account workers
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static AccountWorkerPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (AccountWorkerPersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					AccountWorkerPersistence.class.getName());

			ReferenceRegistry.registerReference(AccountWorkerUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(AccountWorkerPersistence persistence) {
	}

	private static AccountWorkerPersistence _persistence;
}