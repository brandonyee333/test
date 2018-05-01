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

import com.liferay.osb.exception.NoSuchAccountWorkerException;
import com.liferay.osb.model.AccountWorker;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the account worker service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.service.persistence.impl.AccountWorkerPersistenceImpl
 * @see AccountWorkerUtil
 * @generated
 */
@ProviderType
public interface AccountWorkerPersistence extends BasePersistence<AccountWorker> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AccountWorkerUtil} to access the account worker persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the account workers where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching account workers
	*/
	public java.util.List<AccountWorker> findByUserId(long userId);

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
	public java.util.List<AccountWorker> findByUserId(long userId, int start,
		int end);

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
	public java.util.List<AccountWorker> findByUserId(long userId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountWorker> orderByComparator);

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
	public java.util.List<AccountWorker> findByUserId(long userId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountWorker> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first account worker in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account worker
	* @throws NoSuchAccountWorkerException if a matching account worker could not be found
	*/
	public AccountWorker findByUserId_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<AccountWorker> orderByComparator)
		throws NoSuchAccountWorkerException;

	/**
	* Returns the first account worker in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account worker, or <code>null</code> if a matching account worker could not be found
	*/
	public AccountWorker fetchByUserId_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<AccountWorker> orderByComparator);

	/**
	* Returns the last account worker in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account worker
	* @throws NoSuchAccountWorkerException if a matching account worker could not be found
	*/
	public AccountWorker findByUserId_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<AccountWorker> orderByComparator)
		throws NoSuchAccountWorkerException;

	/**
	* Returns the last account worker in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account worker, or <code>null</code> if a matching account worker could not be found
	*/
	public AccountWorker fetchByUserId_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<AccountWorker> orderByComparator);

	/**
	* Returns the account workers before and after the current account worker in the ordered set where userId = &#63;.
	*
	* @param accountWorkerId the primary key of the current account worker
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next account worker
	* @throws NoSuchAccountWorkerException if a account worker with the primary key could not be found
	*/
	public AccountWorker[] findByUserId_PrevAndNext(long accountWorkerId,
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<AccountWorker> orderByComparator)
		throws NoSuchAccountWorkerException;

	/**
	* Removes all the account workers where userId = &#63; from the database.
	*
	* @param userId the user ID
	*/
	public void removeByUserId(long userId);

	/**
	* Returns the number of account workers where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching account workers
	*/
	public int countByUserId(long userId);

	/**
	* Returns all the account workers where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @return the matching account workers
	*/
	public java.util.List<AccountWorker> findByAccountEntryId(
		long accountEntryId);

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
	public java.util.List<AccountWorker> findByAccountEntryId(
		long accountEntryId, int start, int end);

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
	public java.util.List<AccountWorker> findByAccountEntryId(
		long accountEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountWorker> orderByComparator);

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
	public java.util.List<AccountWorker> findByAccountEntryId(
		long accountEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountWorker> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first account worker in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account worker
	* @throws NoSuchAccountWorkerException if a matching account worker could not be found
	*/
	public AccountWorker findByAccountEntryId_First(long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<AccountWorker> orderByComparator)
		throws NoSuchAccountWorkerException;

	/**
	* Returns the first account worker in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account worker, or <code>null</code> if a matching account worker could not be found
	*/
	public AccountWorker fetchByAccountEntryId_First(long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<AccountWorker> orderByComparator);

	/**
	* Returns the last account worker in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account worker
	* @throws NoSuchAccountWorkerException if a matching account worker could not be found
	*/
	public AccountWorker findByAccountEntryId_Last(long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<AccountWorker> orderByComparator)
		throws NoSuchAccountWorkerException;

	/**
	* Returns the last account worker in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account worker, or <code>null</code> if a matching account worker could not be found
	*/
	public AccountWorker fetchByAccountEntryId_Last(long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<AccountWorker> orderByComparator);

	/**
	* Returns the account workers before and after the current account worker in the ordered set where accountEntryId = &#63;.
	*
	* @param accountWorkerId the primary key of the current account worker
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next account worker
	* @throws NoSuchAccountWorkerException if a account worker with the primary key could not be found
	*/
	public AccountWorker[] findByAccountEntryId_PrevAndNext(
		long accountWorkerId, long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<AccountWorker> orderByComparator)
		throws NoSuchAccountWorkerException;

	/**
	* Removes all the account workers where accountEntryId = &#63; from the database.
	*
	* @param accountEntryId the account entry ID
	*/
	public void removeByAccountEntryId(long accountEntryId);

	/**
	* Returns the number of account workers where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @return the number of matching account workers
	*/
	public int countByAccountEntryId(long accountEntryId);

	/**
	* Returns the account worker where userId = &#63; and accountEntryId = &#63; or throws a {@link NoSuchAccountWorkerException} if it could not be found.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @return the matching account worker
	* @throws NoSuchAccountWorkerException if a matching account worker could not be found
	*/
	public AccountWorker findByU_AEI(long userId, long accountEntryId)
		throws NoSuchAccountWorkerException;

	/**
	* Returns the account worker where userId = &#63; and accountEntryId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @return the matching account worker, or <code>null</code> if a matching account worker could not be found
	*/
	public AccountWorker fetchByU_AEI(long userId, long accountEntryId);

	/**
	* Returns the account worker where userId = &#63; and accountEntryId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching account worker, or <code>null</code> if a matching account worker could not be found
	*/
	public AccountWorker fetchByU_AEI(long userId, long accountEntryId,
		boolean retrieveFromCache);

	/**
	* Removes the account worker where userId = &#63; and accountEntryId = &#63; from the database.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @return the account worker that was removed
	*/
	public AccountWorker removeByU_AEI(long userId, long accountEntryId)
		throws NoSuchAccountWorkerException;

	/**
	* Returns the number of account workers where userId = &#63; and accountEntryId = &#63;.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @return the number of matching account workers
	*/
	public int countByU_AEI(long userId, long accountEntryId);

	/**
	* Returns all the account workers where userId = &#63; and role = &#63;.
	*
	* @param userId the user ID
	* @param role the role
	* @return the matching account workers
	*/
	public java.util.List<AccountWorker> findByU_R(long userId, int role);

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
	public java.util.List<AccountWorker> findByU_R(long userId, int role,
		int start, int end);

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
	public java.util.List<AccountWorker> findByU_R(long userId, int role,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountWorker> orderByComparator);

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
	public java.util.List<AccountWorker> findByU_R(long userId, int role,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountWorker> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first account worker in the ordered set where userId = &#63; and role = &#63;.
	*
	* @param userId the user ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account worker
	* @throws NoSuchAccountWorkerException if a matching account worker could not be found
	*/
	public AccountWorker findByU_R_First(long userId, int role,
		com.liferay.portal.kernel.util.OrderByComparator<AccountWorker> orderByComparator)
		throws NoSuchAccountWorkerException;

	/**
	* Returns the first account worker in the ordered set where userId = &#63; and role = &#63;.
	*
	* @param userId the user ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account worker, or <code>null</code> if a matching account worker could not be found
	*/
	public AccountWorker fetchByU_R_First(long userId, int role,
		com.liferay.portal.kernel.util.OrderByComparator<AccountWorker> orderByComparator);

	/**
	* Returns the last account worker in the ordered set where userId = &#63; and role = &#63;.
	*
	* @param userId the user ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account worker
	* @throws NoSuchAccountWorkerException if a matching account worker could not be found
	*/
	public AccountWorker findByU_R_Last(long userId, int role,
		com.liferay.portal.kernel.util.OrderByComparator<AccountWorker> orderByComparator)
		throws NoSuchAccountWorkerException;

	/**
	* Returns the last account worker in the ordered set where userId = &#63; and role = &#63;.
	*
	* @param userId the user ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account worker, or <code>null</code> if a matching account worker could not be found
	*/
	public AccountWorker fetchByU_R_Last(long userId, int role,
		com.liferay.portal.kernel.util.OrderByComparator<AccountWorker> orderByComparator);

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
	public AccountWorker[] findByU_R_PrevAndNext(long accountWorkerId,
		long userId, int role,
		com.liferay.portal.kernel.util.OrderByComparator<AccountWorker> orderByComparator)
		throws NoSuchAccountWorkerException;

	/**
	* Removes all the account workers where userId = &#63; and role = &#63; from the database.
	*
	* @param userId the user ID
	* @param role the role
	*/
	public void removeByU_R(long userId, int role);

	/**
	* Returns the number of account workers where userId = &#63; and role = &#63;.
	*
	* @param userId the user ID
	* @param role the role
	* @return the number of matching account workers
	*/
	public int countByU_R(long userId, int role);

	/**
	* Returns all the account workers where accountEntryId = &#63; and role = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param role the role
	* @return the matching account workers
	*/
	public java.util.List<AccountWorker> findByAEI_R(long accountEntryId,
		int role);

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
	public java.util.List<AccountWorker> findByAEI_R(long accountEntryId,
		int role, int start, int end);

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
	public java.util.List<AccountWorker> findByAEI_R(long accountEntryId,
		int role, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountWorker> orderByComparator);

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
	public java.util.List<AccountWorker> findByAEI_R(long accountEntryId,
		int role, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountWorker> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first account worker in the ordered set where accountEntryId = &#63; and role = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account worker
	* @throws NoSuchAccountWorkerException if a matching account worker could not be found
	*/
	public AccountWorker findByAEI_R_First(long accountEntryId, int role,
		com.liferay.portal.kernel.util.OrderByComparator<AccountWorker> orderByComparator)
		throws NoSuchAccountWorkerException;

	/**
	* Returns the first account worker in the ordered set where accountEntryId = &#63; and role = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account worker, or <code>null</code> if a matching account worker could not be found
	*/
	public AccountWorker fetchByAEI_R_First(long accountEntryId, int role,
		com.liferay.portal.kernel.util.OrderByComparator<AccountWorker> orderByComparator);

	/**
	* Returns the last account worker in the ordered set where accountEntryId = &#63; and role = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account worker
	* @throws NoSuchAccountWorkerException if a matching account worker could not be found
	*/
	public AccountWorker findByAEI_R_Last(long accountEntryId, int role,
		com.liferay.portal.kernel.util.OrderByComparator<AccountWorker> orderByComparator)
		throws NoSuchAccountWorkerException;

	/**
	* Returns the last account worker in the ordered set where accountEntryId = &#63; and role = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account worker, or <code>null</code> if a matching account worker could not be found
	*/
	public AccountWorker fetchByAEI_R_Last(long accountEntryId, int role,
		com.liferay.portal.kernel.util.OrderByComparator<AccountWorker> orderByComparator);

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
	public AccountWorker[] findByAEI_R_PrevAndNext(long accountWorkerId,
		long accountEntryId, int role,
		com.liferay.portal.kernel.util.OrderByComparator<AccountWorker> orderByComparator)
		throws NoSuchAccountWorkerException;

	/**
	* Removes all the account workers where accountEntryId = &#63; and role = &#63; from the database.
	*
	* @param accountEntryId the account entry ID
	* @param role the role
	*/
	public void removeByAEI_R(long accountEntryId, int role);

	/**
	* Returns the number of account workers where accountEntryId = &#63; and role = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param role the role
	* @return the number of matching account workers
	*/
	public int countByAEI_R(long accountEntryId, int role);

	/**
	* Caches the account worker in the entity cache if it is enabled.
	*
	* @param accountWorker the account worker
	*/
	public void cacheResult(AccountWorker accountWorker);

	/**
	* Caches the account workers in the entity cache if it is enabled.
	*
	* @param accountWorkers the account workers
	*/
	public void cacheResult(java.util.List<AccountWorker> accountWorkers);

	/**
	* Creates a new account worker with the primary key. Does not add the account worker to the database.
	*
	* @param accountWorkerId the primary key for the new account worker
	* @return the new account worker
	*/
	public AccountWorker create(long accountWorkerId);

	/**
	* Removes the account worker with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accountWorkerId the primary key of the account worker
	* @return the account worker that was removed
	* @throws NoSuchAccountWorkerException if a account worker with the primary key could not be found
	*/
	public AccountWorker remove(long accountWorkerId)
		throws NoSuchAccountWorkerException;

	public AccountWorker updateImpl(AccountWorker accountWorker);

	/**
	* Returns the account worker with the primary key or throws a {@link NoSuchAccountWorkerException} if it could not be found.
	*
	* @param accountWorkerId the primary key of the account worker
	* @return the account worker
	* @throws NoSuchAccountWorkerException if a account worker with the primary key could not be found
	*/
	public AccountWorker findByPrimaryKey(long accountWorkerId)
		throws NoSuchAccountWorkerException;

	/**
	* Returns the account worker with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param accountWorkerId the primary key of the account worker
	* @return the account worker, or <code>null</code> if a account worker with the primary key could not be found
	*/
	public AccountWorker fetchByPrimaryKey(long accountWorkerId);

	@Override
	public java.util.Map<java.io.Serializable, AccountWorker> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the account workers.
	*
	* @return the account workers
	*/
	public java.util.List<AccountWorker> findAll();

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
	public java.util.List<AccountWorker> findAll(int start, int end);

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
	public java.util.List<AccountWorker> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountWorker> orderByComparator);

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
	public java.util.List<AccountWorker> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountWorker> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the account workers from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of account workers.
	*
	* @return the number of account workers
	*/
	public int countAll();
}