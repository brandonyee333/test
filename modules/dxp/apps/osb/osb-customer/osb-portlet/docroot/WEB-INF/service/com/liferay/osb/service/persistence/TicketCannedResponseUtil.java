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

import com.liferay.osb.model.TicketCannedResponse;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the ticket canned response service. This utility wraps {@link TicketCannedResponsePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TicketCannedResponsePersistence
 * @see TicketCannedResponsePersistenceImpl
 * @generated
 */
public class TicketCannedResponseUtil {
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
	public static void clearCache(TicketCannedResponse ticketCannedResponse) {
		getPersistence().clearCache(ticketCannedResponse);
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
	public static List<TicketCannedResponse> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<TicketCannedResponse> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<TicketCannedResponse> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static TicketCannedResponse update(
		TicketCannedResponse ticketCannedResponse, boolean merge)
		throws SystemException {
		return getPersistence().update(ticketCannedResponse, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static TicketCannedResponse update(
		TicketCannedResponse ticketCannedResponse, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence()
				   .update(ticketCannedResponse, merge, serviceContext);
	}

	/**
	* Caches the ticket canned response in the entity cache if it is enabled.
	*
	* @param ticketCannedResponse the ticket canned response
	*/
	public static void cacheResult(
		com.liferay.osb.model.TicketCannedResponse ticketCannedResponse) {
		getPersistence().cacheResult(ticketCannedResponse);
	}

	/**
	* Caches the ticket canned responses in the entity cache if it is enabled.
	*
	* @param ticketCannedResponses the ticket canned responses
	*/
	public static void cacheResult(
		java.util.List<com.liferay.osb.model.TicketCannedResponse> ticketCannedResponses) {
		getPersistence().cacheResult(ticketCannedResponses);
	}

	/**
	* Creates a new ticket canned response with the primary key. Does not add the ticket canned response to the database.
	*
	* @param ticketCannedResponseId the primary key for the new ticket canned response
	* @return the new ticket canned response
	*/
	public static com.liferay.osb.model.TicketCannedResponse create(
		long ticketCannedResponseId) {
		return getPersistence().create(ticketCannedResponseId);
	}

	/**
	* Removes the ticket canned response with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketCannedResponseId the primary key of the ticket canned response
	* @return the ticket canned response that was removed
	* @throws com.liferay.osb.NoSuchTicketCannedResponseException if a ticket canned response with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketCannedResponse remove(
		long ticketCannedResponseId)
		throws com.liferay.osb.NoSuchTicketCannedResponseException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(ticketCannedResponseId);
	}

	public static com.liferay.osb.model.TicketCannedResponse updateImpl(
		com.liferay.osb.model.TicketCannedResponse ticketCannedResponse,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(ticketCannedResponse, merge);
	}

	/**
	* Returns the ticket canned response with the primary key or throws a {@link com.liferay.osb.NoSuchTicketCannedResponseException} if it could not be found.
	*
	* @param ticketCannedResponseId the primary key of the ticket canned response
	* @return the ticket canned response
	* @throws com.liferay.osb.NoSuchTicketCannedResponseException if a ticket canned response with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketCannedResponse findByPrimaryKey(
		long ticketCannedResponseId)
		throws com.liferay.osb.NoSuchTicketCannedResponseException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(ticketCannedResponseId);
	}

	/**
	* Returns the ticket canned response with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ticketCannedResponseId the primary key of the ticket canned response
	* @return the ticket canned response, or <code>null</code> if a ticket canned response with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketCannedResponse fetchByPrimaryKey(
		long ticketCannedResponseId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(ticketCannedResponseId);
	}

	/**
	* Returns all the ticket canned responses.
	*
	* @return the ticket canned responses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TicketCannedResponse> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the ticket canned responses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of ticket canned responses
	* @param end the upper bound of the range of ticket canned responses (not inclusive)
	* @return the range of ticket canned responses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TicketCannedResponse> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the ticket canned responses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of ticket canned responses
	* @param end the upper bound of the range of ticket canned responses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ticket canned responses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TicketCannedResponse> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the ticket canned responses from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of ticket canned responses.
	*
	* @return the number of ticket canned responses
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static TicketCannedResponsePersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (TicketCannedResponsePersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					TicketCannedResponsePersistence.class.getName());

			ReferenceRegistry.registerReference(TicketCannedResponseUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(TicketCannedResponsePersistence persistence) {
	}

	private static TicketCannedResponsePersistence _persistence;
}