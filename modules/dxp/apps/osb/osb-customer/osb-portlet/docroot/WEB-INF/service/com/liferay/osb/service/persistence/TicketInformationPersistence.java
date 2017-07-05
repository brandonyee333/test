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

import com.liferay.osb.exception.NoSuchTicketInformationException;
import com.liferay.osb.model.TicketInformation;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the ticket information service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.service.persistence.impl.TicketInformationPersistenceImpl
 * @see TicketInformationUtil
 * @generated
 */
@ProviderType
public interface TicketInformationPersistence extends BasePersistence<TicketInformation> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TicketInformationUtil} to access the ticket information persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the ticket informations where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @return the matching ticket informations
	*/
	public java.util.List<TicketInformation> findByTicketEntryId(
		long ticketEntryId);

	/**
	* Returns a range of all the ticket informations where ticketEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketInformationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param start the lower bound of the range of ticket informations
	* @param end the upper bound of the range of ticket informations (not inclusive)
	* @return the range of matching ticket informations
	*/
	public java.util.List<TicketInformation> findByTicketEntryId(
		long ticketEntryId, int start, int end);

	/**
	* Returns an ordered range of all the ticket informations where ticketEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketInformationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param start the lower bound of the range of ticket informations
	* @param end the upper bound of the range of ticket informations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket informations
	*/
	public java.util.List<TicketInformation> findByTicketEntryId(
		long ticketEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TicketInformation> orderByComparator);

	/**
	* Returns an ordered range of all the ticket informations where ticketEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketInformationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param start the lower bound of the range of ticket informations
	* @param end the upper bound of the range of ticket informations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching ticket informations
	*/
	public java.util.List<TicketInformation> findByTicketEntryId(
		long ticketEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TicketInformation> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first ticket information in the ordered set where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket information
	* @throws NoSuchTicketInformationException if a matching ticket information could not be found
	*/
	public TicketInformation findByTicketEntryId_First(long ticketEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<TicketInformation> orderByComparator)
		throws NoSuchTicketInformationException;

	/**
	* Returns the first ticket information in the ordered set where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket information, or <code>null</code> if a matching ticket information could not be found
	*/
	public TicketInformation fetchByTicketEntryId_First(long ticketEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<TicketInformation> orderByComparator);

	/**
	* Returns the last ticket information in the ordered set where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket information
	* @throws NoSuchTicketInformationException if a matching ticket information could not be found
	*/
	public TicketInformation findByTicketEntryId_Last(long ticketEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<TicketInformation> orderByComparator)
		throws NoSuchTicketInformationException;

	/**
	* Returns the last ticket information in the ordered set where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket information, or <code>null</code> if a matching ticket information could not be found
	*/
	public TicketInformation fetchByTicketEntryId_Last(long ticketEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<TicketInformation> orderByComparator);

	/**
	* Returns the ticket informations before and after the current ticket information in the ordered set where ticketEntryId = &#63;.
	*
	* @param ticketInformationId the primary key of the current ticket information
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ticket information
	* @throws NoSuchTicketInformationException if a ticket information with the primary key could not be found
	*/
	public TicketInformation[] findByTicketEntryId_PrevAndNext(
		long ticketInformationId, long ticketEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<TicketInformation> orderByComparator)
		throws NoSuchTicketInformationException;

	/**
	* Removes all the ticket informations where ticketEntryId = &#63; from the database.
	*
	* @param ticketEntryId the ticket entry ID
	*/
	public void removeByTicketEntryId(long ticketEntryId);

	/**
	* Returns the number of ticket informations where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @return the number of matching ticket informations
	*/
	public int countByTicketEntryId(long ticketEntryId);

	/**
	* Returns the ticket information where ticketEntryId = &#63; and fieldId = &#63; or throws a {@link NoSuchTicketInformationException} if it could not be found.
	*
	* @param ticketEntryId the ticket entry ID
	* @param fieldId the field ID
	* @return the matching ticket information
	* @throws NoSuchTicketInformationException if a matching ticket information could not be found
	*/
	public TicketInformation findByTEI_FI(long ticketEntryId, long fieldId)
		throws NoSuchTicketInformationException;

	/**
	* Returns the ticket information where ticketEntryId = &#63; and fieldId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param ticketEntryId the ticket entry ID
	* @param fieldId the field ID
	* @return the matching ticket information, or <code>null</code> if a matching ticket information could not be found
	*/
	public TicketInformation fetchByTEI_FI(long ticketEntryId, long fieldId);

	/**
	* Returns the ticket information where ticketEntryId = &#63; and fieldId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param ticketEntryId the ticket entry ID
	* @param fieldId the field ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching ticket information, or <code>null</code> if a matching ticket information could not be found
	*/
	public TicketInformation fetchByTEI_FI(long ticketEntryId, long fieldId,
		boolean retrieveFromCache);

	/**
	* Removes the ticket information where ticketEntryId = &#63; and fieldId = &#63; from the database.
	*
	* @param ticketEntryId the ticket entry ID
	* @param fieldId the field ID
	* @return the ticket information that was removed
	*/
	public TicketInformation removeByTEI_FI(long ticketEntryId, long fieldId)
		throws NoSuchTicketInformationException;

	/**
	* Returns the number of ticket informations where ticketEntryId = &#63; and fieldId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param fieldId the field ID
	* @return the number of matching ticket informations
	*/
	public int countByTEI_FI(long ticketEntryId, long fieldId);

	/**
	* Caches the ticket information in the entity cache if it is enabled.
	*
	* @param ticketInformation the ticket information
	*/
	public void cacheResult(TicketInformation ticketInformation);

	/**
	* Caches the ticket informations in the entity cache if it is enabled.
	*
	* @param ticketInformations the ticket informations
	*/
	public void cacheResult(
		java.util.List<TicketInformation> ticketInformations);

	/**
	* Creates a new ticket information with the primary key. Does not add the ticket information to the database.
	*
	* @param ticketInformationId the primary key for the new ticket information
	* @return the new ticket information
	*/
	public TicketInformation create(long ticketInformationId);

	/**
	* Removes the ticket information with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketInformationId the primary key of the ticket information
	* @return the ticket information that was removed
	* @throws NoSuchTicketInformationException if a ticket information with the primary key could not be found
	*/
	public TicketInformation remove(long ticketInformationId)
		throws NoSuchTicketInformationException;

	public TicketInformation updateImpl(TicketInformation ticketInformation);

	/**
	* Returns the ticket information with the primary key or throws a {@link NoSuchTicketInformationException} if it could not be found.
	*
	* @param ticketInformationId the primary key of the ticket information
	* @return the ticket information
	* @throws NoSuchTicketInformationException if a ticket information with the primary key could not be found
	*/
	public TicketInformation findByPrimaryKey(long ticketInformationId)
		throws NoSuchTicketInformationException;

	/**
	* Returns the ticket information with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ticketInformationId the primary key of the ticket information
	* @return the ticket information, or <code>null</code> if a ticket information with the primary key could not be found
	*/
	public TicketInformation fetchByPrimaryKey(long ticketInformationId);

	@Override
	public java.util.Map<java.io.Serializable, TicketInformation> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the ticket informations.
	*
	* @return the ticket informations
	*/
	public java.util.List<TicketInformation> findAll();

	/**
	* Returns a range of all the ticket informations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketInformationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ticket informations
	* @param end the upper bound of the range of ticket informations (not inclusive)
	* @return the range of ticket informations
	*/
	public java.util.List<TicketInformation> findAll(int start, int end);

	/**
	* Returns an ordered range of all the ticket informations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketInformationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ticket informations
	* @param end the upper bound of the range of ticket informations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ticket informations
	*/
	public java.util.List<TicketInformation> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TicketInformation> orderByComparator);

	/**
	* Returns an ordered range of all the ticket informations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketInformationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ticket informations
	* @param end the upper bound of the range of ticket informations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ticket informations
	*/
	public java.util.List<TicketInformation> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TicketInformation> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the ticket informations from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of ticket informations.
	*
	* @return the number of ticket informations
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}