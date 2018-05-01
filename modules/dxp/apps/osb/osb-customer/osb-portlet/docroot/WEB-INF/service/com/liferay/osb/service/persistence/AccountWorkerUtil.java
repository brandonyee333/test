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

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.model.AccountWorker;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;

import java.util.List;

/**
 * The persistence utility for the account worker service. This utility wraps {@link com.liferay.osb.service.persistence.impl.AccountWorkerPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountWorkerPersistence
 * @see com.liferay.osb.service.persistence.impl.AccountWorkerPersistenceImpl
 * @generated
 */
@ProviderType
public class AccountWorkerUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(AccountWorker accountWorker) {
		getPersistence().clearCache(accountWorker);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<AccountWorker> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AccountWorker> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AccountWorker> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<AccountWorker> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static AccountWorker update(AccountWorker accountWorker) {
		return getPersistence().update(accountWorker);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static AccountWorker update(AccountWorker accountWorker,
		ServiceContext serviceContext) {
		return getPersistence().update(accountWorker, serviceContext);
	}

	/**
	* Returns all the account workers where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching account workers
	*/
	public static List<AccountWorker> findByUserId(long userId) {
		return getPersistence().findByUserId(userId);
	}

	/**
	* Returns a range of all the account workers where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of account workers
	* @param end the upper bound of the range of account workers (not inclusive)
	* @return the range of matching account workers
	*/
	public static List<AccountWorker> findByUserId(long userId, int start,
		int end) {
		return getPersistence().findByUserId(userId, start, end);
	}

	/**
	* Returns an ordered range of all the account workers where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of account workers
	* @param end the upper bound of the range of account workers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching account workers
	*/
	public static List<AccountWorker> findByUserId(long userId, int start,
		int end, OrderByComparator<AccountWorker> orderByComparator) {
		return getPersistence()
				   .findByUserId(userId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the account workers where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of account workers
	* @param end the upper bound of the range of account workers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching account workers
	*/
	public static List<AccountWorker> findByUserId(long userId, int start,
		int end, OrderByComparator<AccountWorker> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUserId(userId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first account worker in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account worker
	* @throws NoSuchAccountWorkerException if a matching account worker could not be found
	*/
	public static AccountWorker findByUserId_First(long userId,
		OrderByComparator<AccountWorker> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAccountWorkerException {
		return getPersistence().findByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the first account worker in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account worker, or <code>null</code> if a matching account worker could not be found
	*/
	public static AccountWorker fetchByUserId_First(long userId,
		OrderByComparator<AccountWorker> orderByComparator) {
		return getPersistence().fetchByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the last account worker in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account worker
	* @throws NoSuchAccountWorkerException if a matching account worker could not be found
	*/
	public static AccountWorker findByUserId_Last(long userId,
		OrderByComparator<AccountWorker> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAccountWorkerException {
		return getPersistence().findByUserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the last account worker in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account worker, or <code>null</code> if a matching account worker could not be found
	*/
	public static AccountWorker fetchByUserId_Last(long userId,
		OrderByComparator<AccountWorker> orderByComparator) {
		return getPersistence().fetchByUserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the account workers before and after the current account worker in the ordered set where userId = &#63;.
	*
	* @param accountWorkerId the primary key of the current account worker
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next account worker
	* @throws NoSuchAccountWorkerException if a account worker with the primary key could not be found
	*/
	public static AccountWorker[] findByUserId_PrevAndNext(
		long accountWorkerId, long userId,
		OrderByComparator<AccountWorker> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAccountWorkerException {
		return getPersistence()
				   .findByUserId_PrevAndNext(accountWorkerId, userId,
			orderByComparator);
	}

	/**
	* Removes all the account workers where userId = &#63; from the database.
	*
	* @param userId the user ID
	*/
	public static void removeByUserId(long userId) {
		getPersistence().removeByUserId(userId);
	}

	/**
	* Returns the number of account workers where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching account workers
	*/
	public static int countByUserId(long userId) {
		return getPersistence().countByUserId(userId);
	}

	/**
	* Returns all the account workers where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @return the matching account workers
	*/
	public static List<AccountWorker> findByAccountEntryId(long accountEntryId) {
		return getPersistence().findByAccountEntryId(accountEntryId);
	}

	/**
	* Returns a range of all the account workers where accountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param start the lower bound of the range of account workers
	* @param end the upper bound of the range of account workers (not inclusive)
	* @return the range of matching account workers
	*/
	public static List<AccountWorker> findByAccountEntryId(
		long accountEntryId, int start, int end) {
		return getPersistence().findByAccountEntryId(accountEntryId, start, end);
	}

	/**
	* Returns an ordered range of all the account workers where accountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param start the lower bound of the range of account workers
	* @param end the upper bound of the range of account workers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching account workers
	*/
	public static List<AccountWorker> findByAccountEntryId(
		long accountEntryId, int start, int end,
		OrderByComparator<AccountWorker> orderByComparator) {
		return getPersistence()
				   .findByAccountEntryId(accountEntryId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the account workers where accountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param start the lower bound of the range of account workers
	* @param end the upper bound of the range of account workers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching account workers
	*/
	public static List<AccountWorker> findByAccountEntryId(
		long accountEntryId, int start, int end,
		OrderByComparator<AccountWorker> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByAccountEntryId(accountEntryId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first account worker in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account worker
	* @throws NoSuchAccountWorkerException if a matching account worker could not be found
	*/
	public static AccountWorker findByAccountEntryId_First(
		long accountEntryId, OrderByComparator<AccountWorker> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAccountWorkerException {
		return getPersistence()
				   .findByAccountEntryId_First(accountEntryId, orderByComparator);
	}

	/**
	* Returns the first account worker in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account worker, or <code>null</code> if a matching account worker could not be found
	*/
	public static AccountWorker fetchByAccountEntryId_First(
		long accountEntryId, OrderByComparator<AccountWorker> orderByComparator) {
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
	* @throws NoSuchAccountWorkerException if a matching account worker could not be found
	*/
	public static AccountWorker findByAccountEntryId_Last(long accountEntryId,
		OrderByComparator<AccountWorker> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAccountWorkerException {
		return getPersistence()
				   .findByAccountEntryId_Last(accountEntryId, orderByComparator);
	}

	/**
	* Returns the last account worker in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account worker, or <code>null</code> if a matching account worker could not be found
	*/
	public static AccountWorker fetchByAccountEntryId_Last(
		long accountEntryId, OrderByComparator<AccountWorker> orderByComparator) {
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
	* @throws NoSuchAccountWorkerException if a account worker with the primary key could not be found
	*/
	public static AccountWorker[] findByAccountEntryId_PrevAndNext(
		long accountWorkerId, long accountEntryId,
		OrderByComparator<AccountWorker> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAccountWorkerException {
		return getPersistence()
				   .findByAccountEntryId_PrevAndNext(accountWorkerId,
			accountEntryId, orderByComparator);
	}

	/**
	* Removes all the account workers where accountEntryId = &#63; from the database.
	*
	* @param accountEntryId the account entry ID
	*/
	public static void removeByAccountEntryId(long accountEntryId) {
		getPersistence().removeByAccountEntryId(accountEntryId);
	}

	/**
	* Returns the number of account workers where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @return the number of matching account workers
	*/
	public static int countByAccountEntryId(long accountEntryId) {
		return getPersistence().countByAccountEntryId(accountEntryId);
	}

	/**
	* Returns the account worker where userId = &#63; and accountEntryId = &#63; or throws a {@link NoSuchAccountWorkerException} if it could not be found.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @return the matching account worker
	* @throws NoSuchAccountWorkerException if a matching account worker could not be found
	*/
	public static AccountWorker findByU_AEI(long userId, long accountEntryId)
		throws com.liferay.osb.exception.NoSuchAccountWorkerException {
		return getPersistence().findByU_AEI(userId, accountEntryId);
	}

	/**
	* Returns the account worker where userId = &#63; and accountEntryId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @return the matching account worker, or <code>null</code> if a matching account worker could not be found
	*/
	public static AccountWorker fetchByU_AEI(long userId, long accountEntryId) {
		return getPersistence().fetchByU_AEI(userId, accountEntryId);
	}

	/**
	* Returns the account worker where userId = &#63; and accountEntryId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching account worker, or <code>null</code> if a matching account worker could not be found
	*/
	public static AccountWorker fetchByU_AEI(long userId, long accountEntryId,
		boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByU_AEI(userId, accountEntryId, retrieveFromCache);
	}

	/**
	* Removes the account worker where userId = &#63; and accountEntryId = &#63; from the database.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @return the account worker that was removed
	*/
	public static AccountWorker removeByU_AEI(long userId, long accountEntryId)
		throws com.liferay.osb.exception.NoSuchAccountWorkerException {
		return getPersistence().removeByU_AEI(userId, accountEntryId);
	}

	/**
	* Returns the number of account workers where userId = &#63; and accountEntryId = &#63;.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @return the number of matching account workers
	*/
	public static int countByU_AEI(long userId, long accountEntryId) {
		return getPersistence().countByU_AEI(userId, accountEntryId);
	}

	/**
	* Returns all the account workers where userId = &#63; and role = &#63;.
	*
	* @param userId the user ID
	* @param role the role
	* @return the matching account workers
	*/
	public static List<AccountWorker> findByU_R(long userId, int role) {
		return getPersistence().findByU_R(userId, role);
	}

	/**
	* Returns a range of all the account workers where userId = &#63; and role = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param role the role
	* @param start the lower bound of the range of account workers
	* @param end the upper bound of the range of account workers (not inclusive)
	* @return the range of matching account workers
	*/
	public static List<AccountWorker> findByU_R(long userId, int role,
		int start, int end) {
		return getPersistence().findByU_R(userId, role, start, end);
	}

	/**
	* Returns an ordered range of all the account workers where userId = &#63; and role = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param role the role
	* @param start the lower bound of the range of account workers
	* @param end the upper bound of the range of account workers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching account workers
	*/
	public static List<AccountWorker> findByU_R(long userId, int role,
		int start, int end, OrderByComparator<AccountWorker> orderByComparator) {
		return getPersistence()
				   .findByU_R(userId, role, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the account workers where userId = &#63; and role = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param role the role
	* @param start the lower bound of the range of account workers
	* @param end the upper bound of the range of account workers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching account workers
	*/
	public static List<AccountWorker> findByU_R(long userId, int role,
		int start, int end, OrderByComparator<AccountWorker> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByU_R(userId, role, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first account worker in the ordered set where userId = &#63; and role = &#63;.
	*
	* @param userId the user ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account worker
	* @throws NoSuchAccountWorkerException if a matching account worker could not be found
	*/
	public static AccountWorker findByU_R_First(long userId, int role,
		OrderByComparator<AccountWorker> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAccountWorkerException {
		return getPersistence().findByU_R_First(userId, role, orderByComparator);
	}

	/**
	* Returns the first account worker in the ordered set where userId = &#63; and role = &#63;.
	*
	* @param userId the user ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account worker, or <code>null</code> if a matching account worker could not be found
	*/
	public static AccountWorker fetchByU_R_First(long userId, int role,
		OrderByComparator<AccountWorker> orderByComparator) {
		return getPersistence().fetchByU_R_First(userId, role, orderByComparator);
	}

	/**
	* Returns the last account worker in the ordered set where userId = &#63; and role = &#63;.
	*
	* @param userId the user ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account worker
	* @throws NoSuchAccountWorkerException if a matching account worker could not be found
	*/
	public static AccountWorker findByU_R_Last(long userId, int role,
		OrderByComparator<AccountWorker> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAccountWorkerException {
		return getPersistence().findByU_R_Last(userId, role, orderByComparator);
	}

	/**
	* Returns the last account worker in the ordered set where userId = &#63; and role = &#63;.
	*
	* @param userId the user ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account worker, or <code>null</code> if a matching account worker could not be found
	*/
	public static AccountWorker fetchByU_R_Last(long userId, int role,
		OrderByComparator<AccountWorker> orderByComparator) {
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
	* @throws NoSuchAccountWorkerException if a account worker with the primary key could not be found
	*/
	public static AccountWorker[] findByU_R_PrevAndNext(long accountWorkerId,
		long userId, int role,
		OrderByComparator<AccountWorker> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAccountWorkerException {
		return getPersistence()
				   .findByU_R_PrevAndNext(accountWorkerId, userId, role,
			orderByComparator);
	}

	/**
	* Removes all the account workers where userId = &#63; and role = &#63; from the database.
	*
	* @param userId the user ID
	* @param role the role
	*/
	public static void removeByU_R(long userId, int role) {
		getPersistence().removeByU_R(userId, role);
	}

	/**
	* Returns the number of account workers where userId = &#63; and role = &#63;.
	*
	* @param userId the user ID
	* @param role the role
	* @return the number of matching account workers
	*/
	public static int countByU_R(long userId, int role) {
		return getPersistence().countByU_R(userId, role);
	}

	/**
	* Returns all the account workers where accountEntryId = &#63; and role = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param role the role
	* @return the matching account workers
	*/
	public static List<AccountWorker> findByAEI_R(long accountEntryId, int role) {
		return getPersistence().findByAEI_R(accountEntryId, role);
	}

	/**
	* Returns a range of all the account workers where accountEntryId = &#63; and role = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param role the role
	* @param start the lower bound of the range of account workers
	* @param end the upper bound of the range of account workers (not inclusive)
	* @return the range of matching account workers
	*/
	public static List<AccountWorker> findByAEI_R(long accountEntryId,
		int role, int start, int end) {
		return getPersistence().findByAEI_R(accountEntryId, role, start, end);
	}

	/**
	* Returns an ordered range of all the account workers where accountEntryId = &#63; and role = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param role the role
	* @param start the lower bound of the range of account workers
	* @param end the upper bound of the range of account workers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching account workers
	*/
	public static List<AccountWorker> findByAEI_R(long accountEntryId,
		int role, int start, int end,
		OrderByComparator<AccountWorker> orderByComparator) {
		return getPersistence()
				   .findByAEI_R(accountEntryId, role, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the account workers where accountEntryId = &#63; and role = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param role the role
	* @param start the lower bound of the range of account workers
	* @param end the upper bound of the range of account workers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching account workers
	*/
	public static List<AccountWorker> findByAEI_R(long accountEntryId,
		int role, int start, int end,
		OrderByComparator<AccountWorker> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByAEI_R(accountEntryId, role, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first account worker in the ordered set where accountEntryId = &#63; and role = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account worker
	* @throws NoSuchAccountWorkerException if a matching account worker could not be found
	*/
	public static AccountWorker findByAEI_R_First(long accountEntryId,
		int role, OrderByComparator<AccountWorker> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAccountWorkerException {
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
	*/
	public static AccountWorker fetchByAEI_R_First(long accountEntryId,
		int role, OrderByComparator<AccountWorker> orderByComparator) {
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
	* @throws NoSuchAccountWorkerException if a matching account worker could not be found
	*/
	public static AccountWorker findByAEI_R_Last(long accountEntryId, int role,
		OrderByComparator<AccountWorker> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAccountWorkerException {
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
	*/
	public static AccountWorker fetchByAEI_R_Last(long accountEntryId,
		int role, OrderByComparator<AccountWorker> orderByComparator) {
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
	* @throws NoSuchAccountWorkerException if a account worker with the primary key could not be found
	*/
	public static AccountWorker[] findByAEI_R_PrevAndNext(
		long accountWorkerId, long accountEntryId, int role,
		OrderByComparator<AccountWorker> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAccountWorkerException {
		return getPersistence()
				   .findByAEI_R_PrevAndNext(accountWorkerId, accountEntryId,
			role, orderByComparator);
	}

	/**
	* Removes all the account workers where accountEntryId = &#63; and role = &#63; from the database.
	*
	* @param accountEntryId the account entry ID
	* @param role the role
	*/
	public static void removeByAEI_R(long accountEntryId, int role) {
		getPersistence().removeByAEI_R(accountEntryId, role);
	}

	/**
	* Returns the number of account workers where accountEntryId = &#63; and role = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param role the role
	* @return the number of matching account workers
	*/
	public static int countByAEI_R(long accountEntryId, int role) {
		return getPersistence().countByAEI_R(accountEntryId, role);
	}

	/**
	* Caches the account worker in the entity cache if it is enabled.
	*
	* @param accountWorker the account worker
	*/
	public static void cacheResult(AccountWorker accountWorker) {
		getPersistence().cacheResult(accountWorker);
	}

	/**
	* Caches the account workers in the entity cache if it is enabled.
	*
	* @param accountWorkers the account workers
	*/
	public static void cacheResult(List<AccountWorker> accountWorkers) {
		getPersistence().cacheResult(accountWorkers);
	}

	/**
	* Creates a new account worker with the primary key. Does not add the account worker to the database.
	*
	* @param accountWorkerId the primary key for the new account worker
	* @return the new account worker
	*/
	public static AccountWorker create(long accountWorkerId) {
		return getPersistence().create(accountWorkerId);
	}

	/**
	* Removes the account worker with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accountWorkerId the primary key of the account worker
	* @return the account worker that was removed
	* @throws NoSuchAccountWorkerException if a account worker with the primary key could not be found
	*/
	public static AccountWorker remove(long accountWorkerId)
		throws com.liferay.osb.exception.NoSuchAccountWorkerException {
		return getPersistence().remove(accountWorkerId);
	}

	public static AccountWorker updateImpl(AccountWorker accountWorker) {
		return getPersistence().updateImpl(accountWorker);
	}

	/**
	* Returns the account worker with the primary key or throws a {@link NoSuchAccountWorkerException} if it could not be found.
	*
	* @param accountWorkerId the primary key of the account worker
	* @return the account worker
	* @throws NoSuchAccountWorkerException if a account worker with the primary key could not be found
	*/
	public static AccountWorker findByPrimaryKey(long accountWorkerId)
		throws com.liferay.osb.exception.NoSuchAccountWorkerException {
		return getPersistence().findByPrimaryKey(accountWorkerId);
	}

	/**
	* Returns the account worker with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param accountWorkerId the primary key of the account worker
	* @return the account worker, or <code>null</code> if a account worker with the primary key could not be found
	*/
	public static AccountWorker fetchByPrimaryKey(long accountWorkerId) {
		return getPersistence().fetchByPrimaryKey(accountWorkerId);
	}

	public static java.util.Map<java.io.Serializable, AccountWorker> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the account workers.
	*
	* @return the account workers
	*/
	public static List<AccountWorker> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the account workers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of account workers
	* @param end the upper bound of the range of account workers (not inclusive)
	* @return the range of account workers
	*/
	public static List<AccountWorker> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the account workers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of account workers
	* @param end the upper bound of the range of account workers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of account workers
	*/
	public static List<AccountWorker> findAll(int start, int end,
		OrderByComparator<AccountWorker> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the account workers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of account workers
	* @param end the upper bound of the range of account workers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of account workers
	*/
	public static List<AccountWorker> findAll(int start, int end,
		OrderByComparator<AccountWorker> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the account workers from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of account workers.
	*
	* @return the number of account workers
	*/
	public static int countAll() {
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

	private static AccountWorkerPersistence _persistence;
}