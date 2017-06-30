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

import com.liferay.osb.model.TicketCall;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the ticket call service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TicketCallPersistenceImpl
 * @see TicketCallUtil
 * @generated
 */
public interface TicketCallPersistence extends BasePersistence<TicketCall> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TicketCallUtil} to access the ticket call persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the ticket call in the entity cache if it is enabled.
	*
	* @param ticketCall the ticket call
	*/
	public void cacheResult(com.liferay.osb.model.TicketCall ticketCall);

	/**
	* Caches the ticket calls in the entity cache if it is enabled.
	*
	* @param ticketCalls the ticket calls
	*/
	public void cacheResult(
		java.util.List<com.liferay.osb.model.TicketCall> ticketCalls);

	/**
	* Creates a new ticket call with the primary key. Does not add the ticket call to the database.
	*
	* @param ticketCallId the primary key for the new ticket call
	* @return the new ticket call
	*/
	public com.liferay.osb.model.TicketCall create(long ticketCallId);

	/**
	* Removes the ticket call with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketCallId the primary key of the ticket call
	* @return the ticket call that was removed
	* @throws com.liferay.osb.NoSuchTicketCallException if a ticket call with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketCall remove(long ticketCallId)
		throws com.liferay.osb.NoSuchTicketCallException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.TicketCall updateImpl(
		com.liferay.osb.model.TicketCall ticketCall, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the ticket call with the primary key or throws a {@link com.liferay.osb.NoSuchTicketCallException} if it could not be found.
	*
	* @param ticketCallId the primary key of the ticket call
	* @return the ticket call
	* @throws com.liferay.osb.NoSuchTicketCallException if a ticket call with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketCall findByPrimaryKey(long ticketCallId)
		throws com.liferay.osb.NoSuchTicketCallException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the ticket call with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ticketCallId the primary key of the ticket call
	* @return the ticket call, or <code>null</code> if a ticket call with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketCall fetchByPrimaryKey(long ticketCallId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the ticket calls.
	*
	* @return the ticket calls
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketCall> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the ticket calls.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of ticket calls
	* @param end the upper bound of the range of ticket calls (not inclusive)
	* @return the range of ticket calls
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketCall> findAll(int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the ticket calls.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of ticket calls
	* @param end the upper bound of the range of ticket calls (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ticket calls
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketCall> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the ticket calls from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of ticket calls.
	*
	* @return the number of ticket calls
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}