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

import com.liferay.osb.exception.NoSuchTicketCallException;
import com.liferay.osb.model.TicketCall;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the ticket call service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.service.persistence.impl.TicketCallPersistenceImpl
 * @see TicketCallUtil
 * @generated
 */
@ProviderType
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
	public void cacheResult(TicketCall ticketCall);

	/**
	* Caches the ticket calls in the entity cache if it is enabled.
	*
	* @param ticketCalls the ticket calls
	*/
	public void cacheResult(java.util.List<TicketCall> ticketCalls);

	/**
	* Creates a new ticket call with the primary key. Does not add the ticket call to the database.
	*
	* @param ticketCallId the primary key for the new ticket call
	* @return the new ticket call
	*/
	public TicketCall create(long ticketCallId);

	/**
	* Removes the ticket call with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketCallId the primary key of the ticket call
	* @return the ticket call that was removed
	* @throws NoSuchTicketCallException if a ticket call with the primary key could not be found
	*/
	public TicketCall remove(long ticketCallId)
		throws NoSuchTicketCallException;

	public TicketCall updateImpl(TicketCall ticketCall);

	/**
	* Returns the ticket call with the primary key or throws a {@link NoSuchTicketCallException} if it could not be found.
	*
	* @param ticketCallId the primary key of the ticket call
	* @return the ticket call
	* @throws NoSuchTicketCallException if a ticket call with the primary key could not be found
	*/
	public TicketCall findByPrimaryKey(long ticketCallId)
		throws NoSuchTicketCallException;

	/**
	* Returns the ticket call with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ticketCallId the primary key of the ticket call
	* @return the ticket call, or <code>null</code> if a ticket call with the primary key could not be found
	*/
	public TicketCall fetchByPrimaryKey(long ticketCallId);

	@Override
	public java.util.Map<java.io.Serializable, TicketCall> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the ticket calls.
	*
	* @return the ticket calls
	*/
	public java.util.List<TicketCall> findAll();

	/**
	* Returns a range of all the ticket calls.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketCallModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ticket calls
	* @param end the upper bound of the range of ticket calls (not inclusive)
	* @return the range of ticket calls
	*/
	public java.util.List<TicketCall> findAll(int start, int end);

	/**
	* Returns an ordered range of all the ticket calls.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketCallModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ticket calls
	* @param end the upper bound of the range of ticket calls (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ticket calls
	*/
	public java.util.List<TicketCall> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TicketCall> orderByComparator);

	/**
	* Returns an ordered range of all the ticket calls.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketCallModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ticket calls
	* @param end the upper bound of the range of ticket calls (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ticket calls
	*/
	public java.util.List<TicketCall> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TicketCall> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the ticket calls from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of ticket calls.
	*
	* @return the number of ticket calls
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}