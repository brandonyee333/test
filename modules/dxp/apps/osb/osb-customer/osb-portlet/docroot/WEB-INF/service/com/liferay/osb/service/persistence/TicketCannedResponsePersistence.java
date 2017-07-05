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

import com.liferay.osb.exception.NoSuchTicketCannedResponseException;
import com.liferay.osb.model.TicketCannedResponse;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the ticket canned response service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.service.persistence.impl.TicketCannedResponsePersistenceImpl
 * @see TicketCannedResponseUtil
 * @generated
 */
@ProviderType
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
	public void cacheResult(TicketCannedResponse ticketCannedResponse);

	/**
	* Caches the ticket canned responses in the entity cache if it is enabled.
	*
	* @param ticketCannedResponses the ticket canned responses
	*/
	public void cacheResult(
		java.util.List<TicketCannedResponse> ticketCannedResponses);

	/**
	* Creates a new ticket canned response with the primary key. Does not add the ticket canned response to the database.
	*
	* @param ticketCannedResponseId the primary key for the new ticket canned response
	* @return the new ticket canned response
	*/
	public TicketCannedResponse create(long ticketCannedResponseId);

	/**
	* Removes the ticket canned response with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketCannedResponseId the primary key of the ticket canned response
	* @return the ticket canned response that was removed
	* @throws NoSuchTicketCannedResponseException if a ticket canned response with the primary key could not be found
	*/
	public TicketCannedResponse remove(long ticketCannedResponseId)
		throws NoSuchTicketCannedResponseException;

	public TicketCannedResponse updateImpl(
		TicketCannedResponse ticketCannedResponse);

	/**
	* Returns the ticket canned response with the primary key or throws a {@link NoSuchTicketCannedResponseException} if it could not be found.
	*
	* @param ticketCannedResponseId the primary key of the ticket canned response
	* @return the ticket canned response
	* @throws NoSuchTicketCannedResponseException if a ticket canned response with the primary key could not be found
	*/
	public TicketCannedResponse findByPrimaryKey(long ticketCannedResponseId)
		throws NoSuchTicketCannedResponseException;

	/**
	* Returns the ticket canned response with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ticketCannedResponseId the primary key of the ticket canned response
	* @return the ticket canned response, or <code>null</code> if a ticket canned response with the primary key could not be found
	*/
	public TicketCannedResponse fetchByPrimaryKey(long ticketCannedResponseId);

	@Override
	public java.util.Map<java.io.Serializable, TicketCannedResponse> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the ticket canned responses.
	*
	* @return the ticket canned responses
	*/
	public java.util.List<TicketCannedResponse> findAll();

	/**
	* Returns a range of all the ticket canned responses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketCannedResponseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ticket canned responses
	* @param end the upper bound of the range of ticket canned responses (not inclusive)
	* @return the range of ticket canned responses
	*/
	public java.util.List<TicketCannedResponse> findAll(int start, int end);

	/**
	* Returns an ordered range of all the ticket canned responses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketCannedResponseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ticket canned responses
	* @param end the upper bound of the range of ticket canned responses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ticket canned responses
	*/
	public java.util.List<TicketCannedResponse> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TicketCannedResponse> orderByComparator);

	/**
	* Returns an ordered range of all the ticket canned responses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketCannedResponseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ticket canned responses
	* @param end the upper bound of the range of ticket canned responses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ticket canned responses
	*/
	public java.util.List<TicketCannedResponse> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TicketCannedResponse> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the ticket canned responses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of ticket canned responses.
	*
	* @return the number of ticket canned responses
	*/
	public int countAll();
}