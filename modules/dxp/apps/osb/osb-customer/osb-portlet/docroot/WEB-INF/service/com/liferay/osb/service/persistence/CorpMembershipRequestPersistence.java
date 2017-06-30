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

import com.liferay.osb.model.CorpMembershipRequest;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the corp membership request service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CorpMembershipRequestPersistenceImpl
 * @see CorpMembershipRequestUtil
 * @generated
 */
public interface CorpMembershipRequestPersistence extends BasePersistence<CorpMembershipRequest> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CorpMembershipRequestUtil} to access the corp membership request persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the corp membership request in the entity cache if it is enabled.
	*
	* @param corpMembershipRequest the corp membership request
	*/
	public void cacheResult(
		com.liferay.osb.model.CorpMembershipRequest corpMembershipRequest);

	/**
	* Caches the corp membership requests in the entity cache if it is enabled.
	*
	* @param corpMembershipRequests the corp membership requests
	*/
	public void cacheResult(
		java.util.List<com.liferay.osb.model.CorpMembershipRequest> corpMembershipRequests);

	/**
	* Creates a new corp membership request with the primary key. Does not add the corp membership request to the database.
	*
	* @param corpMembershipRequestId the primary key for the new corp membership request
	* @return the new corp membership request
	*/
	public com.liferay.osb.model.CorpMembershipRequest create(
		long corpMembershipRequestId);

	/**
	* Removes the corp membership request with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param corpMembershipRequestId the primary key of the corp membership request
	* @return the corp membership request that was removed
	* @throws com.liferay.osb.NoSuchCorpMembershipRequestException if a corp membership request with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpMembershipRequest remove(
		long corpMembershipRequestId)
		throws com.liferay.osb.NoSuchCorpMembershipRequestException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.CorpMembershipRequest updateImpl(
		com.liferay.osb.model.CorpMembershipRequest corpMembershipRequest,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the corp membership request with the primary key or throws a {@link com.liferay.osb.NoSuchCorpMembershipRequestException} if it could not be found.
	*
	* @param corpMembershipRequestId the primary key of the corp membership request
	* @return the corp membership request
	* @throws com.liferay.osb.NoSuchCorpMembershipRequestException if a corp membership request with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpMembershipRequest findByPrimaryKey(
		long corpMembershipRequestId)
		throws com.liferay.osb.NoSuchCorpMembershipRequestException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the corp membership request with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param corpMembershipRequestId the primary key of the corp membership request
	* @return the corp membership request, or <code>null</code> if a corp membership request with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpMembershipRequest fetchByPrimaryKey(
		long corpMembershipRequestId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the corp membership request where key = &#63; or throws a {@link com.liferay.osb.NoSuchCorpMembershipRequestException} if it could not be found.
	*
	* @param key the key
	* @return the matching corp membership request
	* @throws com.liferay.osb.NoSuchCorpMembershipRequestException if a matching corp membership request could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpMembershipRequest findByKey(
		java.lang.String key)
		throws com.liferay.osb.NoSuchCorpMembershipRequestException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the corp membership request where key = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param key the key
	* @return the matching corp membership request, or <code>null</code> if a matching corp membership request could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpMembershipRequest fetchByKey(
		java.lang.String key)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the corp membership request where key = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param key the key
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching corp membership request, or <code>null</code> if a matching corp membership request could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpMembershipRequest fetchByKey(
		java.lang.String key, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the corp membership requests where corpEntryId = &#63; and status = &#63;.
	*
	* @param corpEntryId the corp entry ID
	* @param status the status
	* @return the matching corp membership requests
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.CorpMembershipRequest> findByCEI_S(
		long corpEntryId, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the corp membership requests where corpEntryId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param corpEntryId the corp entry ID
	* @param status the status
	* @param start the lower bound of the range of corp membership requests
	* @param end the upper bound of the range of corp membership requests (not inclusive)
	* @return the range of matching corp membership requests
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.CorpMembershipRequest> findByCEI_S(
		long corpEntryId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the corp membership requests where corpEntryId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param corpEntryId the corp entry ID
	* @param status the status
	* @param start the lower bound of the range of corp membership requests
	* @param end the upper bound of the range of corp membership requests (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching corp membership requests
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.CorpMembershipRequest> findByCEI_S(
		long corpEntryId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first corp membership request in the ordered set where corpEntryId = &#63; and status = &#63;.
	*
	* @param corpEntryId the corp entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching corp membership request
	* @throws com.liferay.osb.NoSuchCorpMembershipRequestException if a matching corp membership request could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpMembershipRequest findByCEI_S_First(
		long corpEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchCorpMembershipRequestException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first corp membership request in the ordered set where corpEntryId = &#63; and status = &#63;.
	*
	* @param corpEntryId the corp entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching corp membership request, or <code>null</code> if a matching corp membership request could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpMembershipRequest fetchByCEI_S_First(
		long corpEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last corp membership request in the ordered set where corpEntryId = &#63; and status = &#63;.
	*
	* @param corpEntryId the corp entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching corp membership request
	* @throws com.liferay.osb.NoSuchCorpMembershipRequestException if a matching corp membership request could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpMembershipRequest findByCEI_S_Last(
		long corpEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchCorpMembershipRequestException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last corp membership request in the ordered set where corpEntryId = &#63; and status = &#63;.
	*
	* @param corpEntryId the corp entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching corp membership request, or <code>null</code> if a matching corp membership request could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpMembershipRequest fetchByCEI_S_Last(
		long corpEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the corp membership requests before and after the current corp membership request in the ordered set where corpEntryId = &#63; and status = &#63;.
	*
	* @param corpMembershipRequestId the primary key of the current corp membership request
	* @param corpEntryId the corp entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next corp membership request
	* @throws com.liferay.osb.NoSuchCorpMembershipRequestException if a corp membership request with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpMembershipRequest[] findByCEI_S_PrevAndNext(
		long corpMembershipRequestId, long corpEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchCorpMembershipRequestException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the corp membership requests where corpEntryId = &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param corpEntryId the corp entry ID
	* @param statuses the statuses
	* @return the matching corp membership requests
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.CorpMembershipRequest> findByCEI_S(
		long corpEntryId, int[] statuses)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the corp membership requests where corpEntryId = &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param corpEntryId the corp entry ID
	* @param statuses the statuses
	* @param start the lower bound of the range of corp membership requests
	* @param end the upper bound of the range of corp membership requests (not inclusive)
	* @return the range of matching corp membership requests
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.CorpMembershipRequest> findByCEI_S(
		long corpEntryId, int[] statuses, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the corp membership requests where corpEntryId = &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param corpEntryId the corp entry ID
	* @param statuses the statuses
	* @param start the lower bound of the range of corp membership requests
	* @param end the upper bound of the range of corp membership requests (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching corp membership requests
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.CorpMembershipRequest> findByCEI_S(
		long corpEntryId, int[] statuses, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the corp membership requests where userId = &#63; and corpEntryId = &#63; and status = &#63;.
	*
	* @param userId the user ID
	* @param corpEntryId the corp entry ID
	* @param status the status
	* @return the matching corp membership requests
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.CorpMembershipRequest> findByU_CEI_S(
		long userId, long corpEntryId, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the corp membership requests where userId = &#63; and corpEntryId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param corpEntryId the corp entry ID
	* @param status the status
	* @param start the lower bound of the range of corp membership requests
	* @param end the upper bound of the range of corp membership requests (not inclusive)
	* @return the range of matching corp membership requests
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.CorpMembershipRequest> findByU_CEI_S(
		long userId, long corpEntryId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the corp membership requests where userId = &#63; and corpEntryId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param corpEntryId the corp entry ID
	* @param status the status
	* @param start the lower bound of the range of corp membership requests
	* @param end the upper bound of the range of corp membership requests (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching corp membership requests
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.CorpMembershipRequest> findByU_CEI_S(
		long userId, long corpEntryId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first corp membership request in the ordered set where userId = &#63; and corpEntryId = &#63; and status = &#63;.
	*
	* @param userId the user ID
	* @param corpEntryId the corp entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching corp membership request
	* @throws com.liferay.osb.NoSuchCorpMembershipRequestException if a matching corp membership request could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpMembershipRequest findByU_CEI_S_First(
		long userId, long corpEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchCorpMembershipRequestException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first corp membership request in the ordered set where userId = &#63; and corpEntryId = &#63; and status = &#63;.
	*
	* @param userId the user ID
	* @param corpEntryId the corp entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching corp membership request, or <code>null</code> if a matching corp membership request could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpMembershipRequest fetchByU_CEI_S_First(
		long userId, long corpEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last corp membership request in the ordered set where userId = &#63; and corpEntryId = &#63; and status = &#63;.
	*
	* @param userId the user ID
	* @param corpEntryId the corp entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching corp membership request
	* @throws com.liferay.osb.NoSuchCorpMembershipRequestException if a matching corp membership request could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpMembershipRequest findByU_CEI_S_Last(
		long userId, long corpEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchCorpMembershipRequestException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last corp membership request in the ordered set where userId = &#63; and corpEntryId = &#63; and status = &#63;.
	*
	* @param userId the user ID
	* @param corpEntryId the corp entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching corp membership request, or <code>null</code> if a matching corp membership request could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpMembershipRequest fetchByU_CEI_S_Last(
		long userId, long corpEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the corp membership requests before and after the current corp membership request in the ordered set where userId = &#63; and corpEntryId = &#63; and status = &#63;.
	*
	* @param corpMembershipRequestId the primary key of the current corp membership request
	* @param userId the user ID
	* @param corpEntryId the corp entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next corp membership request
	* @throws com.liferay.osb.NoSuchCorpMembershipRequestException if a corp membership request with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpMembershipRequest[] findByU_CEI_S_PrevAndNext(
		long corpMembershipRequestId, long userId, long corpEntryId,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchCorpMembershipRequestException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the corp membership requests where userId = &#63; and corpEntryId = &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param corpEntryId the corp entry ID
	* @param statuses the statuses
	* @return the matching corp membership requests
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.CorpMembershipRequest> findByU_CEI_S(
		long userId, long corpEntryId, int[] statuses)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the corp membership requests where userId = &#63; and corpEntryId = &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param corpEntryId the corp entry ID
	* @param statuses the statuses
	* @param start the lower bound of the range of corp membership requests
	* @param end the upper bound of the range of corp membership requests (not inclusive)
	* @return the range of matching corp membership requests
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.CorpMembershipRequest> findByU_CEI_S(
		long userId, long corpEntryId, int[] statuses, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the corp membership requests where userId = &#63; and corpEntryId = &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param corpEntryId the corp entry ID
	* @param statuses the statuses
	* @param start the lower bound of the range of corp membership requests
	* @param end the upper bound of the range of corp membership requests (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching corp membership requests
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.CorpMembershipRequest> findByU_CEI_S(
		long userId, long corpEntryId, int[] statuses, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the corp membership requests.
	*
	* @return the corp membership requests
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.CorpMembershipRequest> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the corp membership requests.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of corp membership requests
	* @param end the upper bound of the range of corp membership requests (not inclusive)
	* @return the range of corp membership requests
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.CorpMembershipRequest> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the corp membership requests.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of corp membership requests
	* @param end the upper bound of the range of corp membership requests (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of corp membership requests
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.CorpMembershipRequest> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the corp membership request where key = &#63; from the database.
	*
	* @param key the key
	* @return the corp membership request that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpMembershipRequest removeByKey(
		java.lang.String key)
		throws com.liferay.osb.NoSuchCorpMembershipRequestException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the corp membership requests where corpEntryId = &#63; and status = &#63; from the database.
	*
	* @param corpEntryId the corp entry ID
	* @param status the status
	* @throws SystemException if a system exception occurred
	*/
	public void removeByCEI_S(long corpEntryId, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the corp membership requests where userId = &#63; and corpEntryId = &#63; and status = &#63; from the database.
	*
	* @param userId the user ID
	* @param corpEntryId the corp entry ID
	* @param status the status
	* @throws SystemException if a system exception occurred
	*/
	public void removeByU_CEI_S(long userId, long corpEntryId, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the corp membership requests from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of corp membership requests where key = &#63;.
	*
	* @param key the key
	* @return the number of matching corp membership requests
	* @throws SystemException if a system exception occurred
	*/
	public int countByKey(java.lang.String key)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of corp membership requests where corpEntryId = &#63; and status = &#63;.
	*
	* @param corpEntryId the corp entry ID
	* @param status the status
	* @return the number of matching corp membership requests
	* @throws SystemException if a system exception occurred
	*/
	public int countByCEI_S(long corpEntryId, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of corp membership requests where corpEntryId = &#63; and status = any &#63;.
	*
	* @param corpEntryId the corp entry ID
	* @param statuses the statuses
	* @return the number of matching corp membership requests
	* @throws SystemException if a system exception occurred
	*/
	public int countByCEI_S(long corpEntryId, int[] statuses)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of corp membership requests where userId = &#63; and corpEntryId = &#63; and status = &#63;.
	*
	* @param userId the user ID
	* @param corpEntryId the corp entry ID
	* @param status the status
	* @return the number of matching corp membership requests
	* @throws SystemException if a system exception occurred
	*/
	public int countByU_CEI_S(long userId, long corpEntryId, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of corp membership requests where userId = &#63; and corpEntryId = &#63; and status = any &#63;.
	*
	* @param userId the user ID
	* @param corpEntryId the corp entry ID
	* @param statuses the statuses
	* @return the number of matching corp membership requests
	* @throws SystemException if a system exception occurred
	*/
	public int countByU_CEI_S(long userId, long corpEntryId, int[] statuses)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of corp membership requests.
	*
	* @return the number of corp membership requests
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}