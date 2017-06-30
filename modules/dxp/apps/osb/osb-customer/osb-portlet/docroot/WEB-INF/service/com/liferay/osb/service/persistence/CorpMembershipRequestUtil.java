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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the corp membership request service. This utility wraps {@link CorpMembershipRequestPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CorpMembershipRequestPersistence
 * @see CorpMembershipRequestPersistenceImpl
 * @generated
 */
public class CorpMembershipRequestUtil {
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
	public static void clearCache(CorpMembershipRequest corpMembershipRequest) {
		getPersistence().clearCache(corpMembershipRequest);
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
	public static List<CorpMembershipRequest> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CorpMembershipRequest> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CorpMembershipRequest> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static CorpMembershipRequest update(
		CorpMembershipRequest corpMembershipRequest, boolean merge)
		throws SystemException {
		return getPersistence().update(corpMembershipRequest, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static CorpMembershipRequest update(
		CorpMembershipRequest corpMembershipRequest, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence()
				   .update(corpMembershipRequest, merge, serviceContext);
	}

	/**
	* Caches the corp membership request in the entity cache if it is enabled.
	*
	* @param corpMembershipRequest the corp membership request
	*/
	public static void cacheResult(
		com.liferay.osb.model.CorpMembershipRequest corpMembershipRequest) {
		getPersistence().cacheResult(corpMembershipRequest);
	}

	/**
	* Caches the corp membership requests in the entity cache if it is enabled.
	*
	* @param corpMembershipRequests the corp membership requests
	*/
	public static void cacheResult(
		java.util.List<com.liferay.osb.model.CorpMembershipRequest> corpMembershipRequests) {
		getPersistence().cacheResult(corpMembershipRequests);
	}

	/**
	* Creates a new corp membership request with the primary key. Does not add the corp membership request to the database.
	*
	* @param corpMembershipRequestId the primary key for the new corp membership request
	* @return the new corp membership request
	*/
	public static com.liferay.osb.model.CorpMembershipRequest create(
		long corpMembershipRequestId) {
		return getPersistence().create(corpMembershipRequestId);
	}

	/**
	* Removes the corp membership request with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param corpMembershipRequestId the primary key of the corp membership request
	* @return the corp membership request that was removed
	* @throws com.liferay.osb.NoSuchCorpMembershipRequestException if a corp membership request with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpMembershipRequest remove(
		long corpMembershipRequestId)
		throws com.liferay.osb.NoSuchCorpMembershipRequestException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(corpMembershipRequestId);
	}

	public static com.liferay.osb.model.CorpMembershipRequest updateImpl(
		com.liferay.osb.model.CorpMembershipRequest corpMembershipRequest,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(corpMembershipRequest, merge);
	}

	/**
	* Returns the corp membership request with the primary key or throws a {@link com.liferay.osb.NoSuchCorpMembershipRequestException} if it could not be found.
	*
	* @param corpMembershipRequestId the primary key of the corp membership request
	* @return the corp membership request
	* @throws com.liferay.osb.NoSuchCorpMembershipRequestException if a corp membership request with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpMembershipRequest findByPrimaryKey(
		long corpMembershipRequestId)
		throws com.liferay.osb.NoSuchCorpMembershipRequestException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(corpMembershipRequestId);
	}

	/**
	* Returns the corp membership request with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param corpMembershipRequestId the primary key of the corp membership request
	* @return the corp membership request, or <code>null</code> if a corp membership request with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpMembershipRequest fetchByPrimaryKey(
		long corpMembershipRequestId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(corpMembershipRequestId);
	}

	/**
	* Returns the corp membership request where key = &#63; or throws a {@link com.liferay.osb.NoSuchCorpMembershipRequestException} if it could not be found.
	*
	* @param key the key
	* @return the matching corp membership request
	* @throws com.liferay.osb.NoSuchCorpMembershipRequestException if a matching corp membership request could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpMembershipRequest findByKey(
		java.lang.String key)
		throws com.liferay.osb.NoSuchCorpMembershipRequestException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByKey(key);
	}

	/**
	* Returns the corp membership request where key = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param key the key
	* @return the matching corp membership request, or <code>null</code> if a matching corp membership request could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpMembershipRequest fetchByKey(
		java.lang.String key)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByKey(key);
	}

	/**
	* Returns the corp membership request where key = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param key the key
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching corp membership request, or <code>null</code> if a matching corp membership request could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpMembershipRequest fetchByKey(
		java.lang.String key, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByKey(key, retrieveFromCache);
	}

	/**
	* Returns all the corp membership requests where corpEntryId = &#63; and status = &#63;.
	*
	* @param corpEntryId the corp entry ID
	* @param status the status
	* @return the matching corp membership requests
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.CorpMembershipRequest> findByCEI_S(
		long corpEntryId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCEI_S(corpEntryId, status);
	}

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
	public static java.util.List<com.liferay.osb.model.CorpMembershipRequest> findByCEI_S(
		long corpEntryId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCEI_S(corpEntryId, status, start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.CorpMembershipRequest> findByCEI_S(
		long corpEntryId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCEI_S(corpEntryId, status, start, end,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.CorpMembershipRequest findByCEI_S_First(
		long corpEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchCorpMembershipRequestException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCEI_S_First(corpEntryId, status, orderByComparator);
	}

	/**
	* Returns the first corp membership request in the ordered set where corpEntryId = &#63; and status = &#63;.
	*
	* @param corpEntryId the corp entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching corp membership request, or <code>null</code> if a matching corp membership request could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpMembershipRequest fetchByCEI_S_First(
		long corpEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByCEI_S_First(corpEntryId, status, orderByComparator);
	}

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
	public static com.liferay.osb.model.CorpMembershipRequest findByCEI_S_Last(
		long corpEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchCorpMembershipRequestException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCEI_S_Last(corpEntryId, status, orderByComparator);
	}

	/**
	* Returns the last corp membership request in the ordered set where corpEntryId = &#63; and status = &#63;.
	*
	* @param corpEntryId the corp entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching corp membership request, or <code>null</code> if a matching corp membership request could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpMembershipRequest fetchByCEI_S_Last(
		long corpEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByCEI_S_Last(corpEntryId, status, orderByComparator);
	}

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
	public static com.liferay.osb.model.CorpMembershipRequest[] findByCEI_S_PrevAndNext(
		long corpMembershipRequestId, long corpEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchCorpMembershipRequestException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCEI_S_PrevAndNext(corpMembershipRequestId,
			corpEntryId, status, orderByComparator);
	}

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
	public static java.util.List<com.liferay.osb.model.CorpMembershipRequest> findByCEI_S(
		long corpEntryId, int[] statuses)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCEI_S(corpEntryId, statuses);
	}

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
	public static java.util.List<com.liferay.osb.model.CorpMembershipRequest> findByCEI_S(
		long corpEntryId, int[] statuses, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCEI_S(corpEntryId, statuses, start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.CorpMembershipRequest> findByCEI_S(
		long corpEntryId, int[] statuses, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCEI_S(corpEntryId, statuses, start, end,
			orderByComparator);
	}

	/**
	* Returns all the corp membership requests where userId = &#63; and corpEntryId = &#63; and status = &#63;.
	*
	* @param userId the user ID
	* @param corpEntryId the corp entry ID
	* @param status the status
	* @return the matching corp membership requests
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.CorpMembershipRequest> findByU_CEI_S(
		long userId, long corpEntryId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByU_CEI_S(userId, corpEntryId, status);
	}

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
	public static java.util.List<com.liferay.osb.model.CorpMembershipRequest> findByU_CEI_S(
		long userId, long corpEntryId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_CEI_S(userId, corpEntryId, status, start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.CorpMembershipRequest> findByU_CEI_S(
		long userId, long corpEntryId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_CEI_S(userId, corpEntryId, status, start, end,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.CorpMembershipRequest findByU_CEI_S_First(
		long userId, long corpEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchCorpMembershipRequestException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_CEI_S_First(userId, corpEntryId, status,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.CorpMembershipRequest fetchByU_CEI_S_First(
		long userId, long corpEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByU_CEI_S_First(userId, corpEntryId, status,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.CorpMembershipRequest findByU_CEI_S_Last(
		long userId, long corpEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchCorpMembershipRequestException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_CEI_S_Last(userId, corpEntryId, status,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.CorpMembershipRequest fetchByU_CEI_S_Last(
		long userId, long corpEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByU_CEI_S_Last(userId, corpEntryId, status,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.CorpMembershipRequest[] findByU_CEI_S_PrevAndNext(
		long corpMembershipRequestId, long userId, long corpEntryId,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchCorpMembershipRequestException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_CEI_S_PrevAndNext(corpMembershipRequestId, userId,
			corpEntryId, status, orderByComparator);
	}

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
	public static java.util.List<com.liferay.osb.model.CorpMembershipRequest> findByU_CEI_S(
		long userId, long corpEntryId, int[] statuses)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByU_CEI_S(userId, corpEntryId, statuses);
	}

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
	public static java.util.List<com.liferay.osb.model.CorpMembershipRequest> findByU_CEI_S(
		long userId, long corpEntryId, int[] statuses, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_CEI_S(userId, corpEntryId, statuses, start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.CorpMembershipRequest> findByU_CEI_S(
		long userId, long corpEntryId, int[] statuses, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_CEI_S(userId, corpEntryId, statuses, start, end,
			orderByComparator);
	}

	/**
	* Returns all the corp membership requests.
	*
	* @return the corp membership requests
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.CorpMembershipRequest> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

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
	public static java.util.List<com.liferay.osb.model.CorpMembershipRequest> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.CorpMembershipRequest> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes the corp membership request where key = &#63; from the database.
	*
	* @param key the key
	* @return the corp membership request that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpMembershipRequest removeByKey(
		java.lang.String key)
		throws com.liferay.osb.NoSuchCorpMembershipRequestException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByKey(key);
	}

	/**
	* Removes all the corp membership requests where corpEntryId = &#63; and status = &#63; from the database.
	*
	* @param corpEntryId the corp entry ID
	* @param status the status
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByCEI_S(long corpEntryId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByCEI_S(corpEntryId, status);
	}

	/**
	* Removes all the corp membership requests where userId = &#63; and corpEntryId = &#63; and status = &#63; from the database.
	*
	* @param userId the user ID
	* @param corpEntryId the corp entry ID
	* @param status the status
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByU_CEI_S(long userId, long corpEntryId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByU_CEI_S(userId, corpEntryId, status);
	}

	/**
	* Removes all the corp membership requests from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of corp membership requests where key = &#63;.
	*
	* @param key the key
	* @return the number of matching corp membership requests
	* @throws SystemException if a system exception occurred
	*/
	public static int countByKey(java.lang.String key)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByKey(key);
	}

	/**
	* Returns the number of corp membership requests where corpEntryId = &#63; and status = &#63;.
	*
	* @param corpEntryId the corp entry ID
	* @param status the status
	* @return the number of matching corp membership requests
	* @throws SystemException if a system exception occurred
	*/
	public static int countByCEI_S(long corpEntryId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByCEI_S(corpEntryId, status);
	}

	/**
	* Returns the number of corp membership requests where corpEntryId = &#63; and status = any &#63;.
	*
	* @param corpEntryId the corp entry ID
	* @param statuses the statuses
	* @return the number of matching corp membership requests
	* @throws SystemException if a system exception occurred
	*/
	public static int countByCEI_S(long corpEntryId, int[] statuses)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByCEI_S(corpEntryId, statuses);
	}

	/**
	* Returns the number of corp membership requests where userId = &#63; and corpEntryId = &#63; and status = &#63;.
	*
	* @param userId the user ID
	* @param corpEntryId the corp entry ID
	* @param status the status
	* @return the number of matching corp membership requests
	* @throws SystemException if a system exception occurred
	*/
	public static int countByU_CEI_S(long userId, long corpEntryId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByU_CEI_S(userId, corpEntryId, status);
	}

	/**
	* Returns the number of corp membership requests where userId = &#63; and corpEntryId = &#63; and status = any &#63;.
	*
	* @param userId the user ID
	* @param corpEntryId the corp entry ID
	* @param statuses the statuses
	* @return the number of matching corp membership requests
	* @throws SystemException if a system exception occurred
	*/
	public static int countByU_CEI_S(long userId, long corpEntryId,
		int[] statuses)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByU_CEI_S(userId, corpEntryId, statuses);
	}

	/**
	* Returns the number of corp membership requests.
	*
	* @return the number of corp membership requests
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static CorpMembershipRequestPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (CorpMembershipRequestPersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					CorpMembershipRequestPersistence.class.getName());

			ReferenceRegistry.registerReference(CorpMembershipRequestUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(CorpMembershipRequestPersistence persistence) {
	}

	private static CorpMembershipRequestPersistence _persistence;
}