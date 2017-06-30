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

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the ticket canned response service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TicketCannedResponsePersistenceImpl
 * @see TicketCannedResponseUtil
 * @generated
 */
public interface TicketCannedResponsePersistence extends BasePersistence<TicketCannedResponse> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TicketCannedResponseUtil} to access the ticket canned response persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the ticket canned response in the entity cache if it is enabled.
	*
	* @param ticketCannedResponse the ticket canned response
	*/
	public void cacheResult(
		com.liferay.osb.model.TicketCannedResponse ticketCannedResponse);

	/**
	* Caches the ticket canned responses in the entity cache if it is enabled.
	*
	* @param ticketCannedResponses the ticket canned responses
	*/
	public void cacheResult(
		java.util.List<com.liferay.osb.model.TicketCannedResponse> ticketCannedResponses);

	/**
	* Creates a new ticket canned response with the primary key. Does not add the ticket canned response to the database.
	*
	* @param ticketCannedResponseId the primary key for the new ticket canned response
	* @return the new ticket canned response
	*/
	public com.liferay.osb.model.TicketCannedResponse create(
		long ticketCannedResponseId);

	/**
	* Removes the ticket canned response with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketCannedResponseId the primary key of the ticket canned response
	* @return the ticket canned response that was removed
	* @throws com.liferay.osb.NoSuchTicketCannedResponseException if a ticket canned response with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketCannedResponse remove(
		long ticketCannedResponseId)
		throws com.liferay.osb.NoSuchTicketCannedResponseException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.TicketCannedResponse updateImpl(
		com.liferay.osb.model.TicketCannedResponse ticketCannedResponse,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the ticket canned response with the primary key or throws a {@link com.liferay.osb.NoSuchTicketCannedResponseException} if it could not be found.
	*
	* @param ticketCannedResponseId the primary key of the ticket canned response
	* @return the ticket canned response
	* @throws com.liferay.osb.NoSuchTicketCannedResponseException if a ticket canned response with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketCannedResponse findByPrimaryKey(
		long ticketCannedResponseId)
		throws com.liferay.osb.NoSuchTicketCannedResponseException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the ticket canned response with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ticketCannedResponseId the primary key of the ticket canned response
	* @return the ticket canned response, or <code>null</code> if a ticket canned response with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketCannedResponse fetchByPrimaryKey(
		long ticketCannedResponseId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the ticket canned responses.
	*
	* @return the ticket canned responses
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketCannedResponse> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.TicketCannedResponse> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.TicketCannedResponse> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the ticket canned responses from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of ticket canned responses.
	*
	* @return the number of ticket canned responses
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}