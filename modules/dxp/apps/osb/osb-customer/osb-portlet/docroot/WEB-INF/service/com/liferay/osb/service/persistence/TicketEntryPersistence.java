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

import com.liferay.osb.exception.NoSuchTicketEntryException;
import com.liferay.osb.model.TicketEntry;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.util.Date;

/**
 * The persistence interface for the ticket entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.service.persistence.impl.TicketEntryPersistenceImpl
 * @see TicketEntryUtil
 * @generated
 */
@ProviderType
public interface TicketEntryPersistence extends BasePersistence<TicketEntry> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TicketEntryUtil} to access the ticket entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the ticket entries where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching ticket entries
	*/
	public java.util.List<TicketEntry> findByCompanyId(long companyId);

	/**
	* Returns a range of all the ticket entries where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of ticket entries
	* @param end the upper bound of the range of ticket entries (not inclusive)
	* @return the range of matching ticket entries
	*/
	public java.util.List<TicketEntry> findByCompanyId(long companyId,
		int start, int end);

	/**
	* Returns an ordered range of all the ticket entries where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of ticket entries
	* @param end the upper bound of the range of ticket entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket entries
	*/
	public java.util.List<TicketEntry> findByCompanyId(long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TicketEntry> orderByComparator);

	/**
	* Returns an ordered range of all the ticket entries where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of ticket entries
	* @param end the upper bound of the range of ticket entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching ticket entries
	*/
	public java.util.List<TicketEntry> findByCompanyId(long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TicketEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first ticket entry in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket entry
	* @throws NoSuchTicketEntryException if a matching ticket entry could not be found
	*/
	public TicketEntry findByCompanyId_First(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<TicketEntry> orderByComparator)
		throws NoSuchTicketEntryException;

	/**
	* Returns the first ticket entry in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket entry, or <code>null</code> if a matching ticket entry could not be found
	*/
	public TicketEntry fetchByCompanyId_First(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<TicketEntry> orderByComparator);

	/**
	* Returns the last ticket entry in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket entry
	* @throws NoSuchTicketEntryException if a matching ticket entry could not be found
	*/
	public TicketEntry findByCompanyId_Last(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<TicketEntry> orderByComparator)
		throws NoSuchTicketEntryException;

	/**
	* Returns the last ticket entry in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket entry, or <code>null</code> if a matching ticket entry could not be found
	*/
	public TicketEntry fetchByCompanyId_Last(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<TicketEntry> orderByComparator);

	/**
	* Returns the ticket entries before and after the current ticket entry in the ordered set where companyId = &#63;.
	*
	* @param ticketEntryId the primary key of the current ticket entry
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ticket entry
	* @throws NoSuchTicketEntryException if a ticket entry with the primary key could not be found
	*/
	public TicketEntry[] findByCompanyId_PrevAndNext(long ticketEntryId,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<TicketEntry> orderByComparator)
		throws NoSuchTicketEntryException;

	/**
	* Removes all the ticket entries where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	*/
	public void removeByCompanyId(long companyId);

	/**
	* Returns the number of ticket entries where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching ticket entries
	*/
	public int countByCompanyId(long companyId);

	/**
	* Returns all the ticket entries where modifiedDate &ge; &#63;.
	*
	* @param modifiedDate the modified date
	* @return the matching ticket entries
	*/
	public java.util.List<TicketEntry> findByGtModifiedDate(Date modifiedDate);

	/**
	* Returns a range of all the ticket entries where modifiedDate &ge; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of ticket entries
	* @param end the upper bound of the range of ticket entries (not inclusive)
	* @return the range of matching ticket entries
	*/
	public java.util.List<TicketEntry> findByGtModifiedDate(Date modifiedDate,
		int start, int end);

	/**
	* Returns an ordered range of all the ticket entries where modifiedDate &ge; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of ticket entries
	* @param end the upper bound of the range of ticket entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket entries
	*/
	public java.util.List<TicketEntry> findByGtModifiedDate(Date modifiedDate,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TicketEntry> orderByComparator);

	/**
	* Returns an ordered range of all the ticket entries where modifiedDate &ge; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of ticket entries
	* @param end the upper bound of the range of ticket entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching ticket entries
	*/
	public java.util.List<TicketEntry> findByGtModifiedDate(Date modifiedDate,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TicketEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first ticket entry in the ordered set where modifiedDate &ge; &#63;.
	*
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket entry
	* @throws NoSuchTicketEntryException if a matching ticket entry could not be found
	*/
	public TicketEntry findByGtModifiedDate_First(Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator<TicketEntry> orderByComparator)
		throws NoSuchTicketEntryException;

	/**
	* Returns the first ticket entry in the ordered set where modifiedDate &ge; &#63;.
	*
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket entry, or <code>null</code> if a matching ticket entry could not be found
	*/
	public TicketEntry fetchByGtModifiedDate_First(Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator<TicketEntry> orderByComparator);

	/**
	* Returns the last ticket entry in the ordered set where modifiedDate &ge; &#63;.
	*
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket entry
	* @throws NoSuchTicketEntryException if a matching ticket entry could not be found
	*/
	public TicketEntry findByGtModifiedDate_Last(Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator<TicketEntry> orderByComparator)
		throws NoSuchTicketEntryException;

	/**
	* Returns the last ticket entry in the ordered set where modifiedDate &ge; &#63;.
	*
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket entry, or <code>null</code> if a matching ticket entry could not be found
	*/
	public TicketEntry fetchByGtModifiedDate_Last(Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator<TicketEntry> orderByComparator);

	/**
	* Returns the ticket entries before and after the current ticket entry in the ordered set where modifiedDate &ge; &#63;.
	*
	* @param ticketEntryId the primary key of the current ticket entry
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ticket entry
	* @throws NoSuchTicketEntryException if a ticket entry with the primary key could not be found
	*/
	public TicketEntry[] findByGtModifiedDate_PrevAndNext(long ticketEntryId,
		Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator<TicketEntry> orderByComparator)
		throws NoSuchTicketEntryException;

	/**
	* Removes all the ticket entries where modifiedDate &ge; &#63; from the database.
	*
	* @param modifiedDate the modified date
	*/
	public void removeByGtModifiedDate(Date modifiedDate);

	/**
	* Returns the number of ticket entries where modifiedDate &ge; &#63;.
	*
	* @param modifiedDate the modified date
	* @return the number of matching ticket entries
	*/
	public int countByGtModifiedDate(Date modifiedDate);

	/**
	* Returns all the ticket entries where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @return the matching ticket entries
	*/
	public java.util.List<TicketEntry> findByAccountEntryId(long accountEntryId);

	/**
	* Returns a range of all the ticket entries where accountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param start the lower bound of the range of ticket entries
	* @param end the upper bound of the range of ticket entries (not inclusive)
	* @return the range of matching ticket entries
	*/
	public java.util.List<TicketEntry> findByAccountEntryId(
		long accountEntryId, int start, int end);

	/**
	* Returns an ordered range of all the ticket entries where accountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param start the lower bound of the range of ticket entries
	* @param end the upper bound of the range of ticket entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket entries
	*/
	public java.util.List<TicketEntry> findByAccountEntryId(
		long accountEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TicketEntry> orderByComparator);

	/**
	* Returns an ordered range of all the ticket entries where accountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param start the lower bound of the range of ticket entries
	* @param end the upper bound of the range of ticket entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching ticket entries
	*/
	public java.util.List<TicketEntry> findByAccountEntryId(
		long accountEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TicketEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first ticket entry in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket entry
	* @throws NoSuchTicketEntryException if a matching ticket entry could not be found
	*/
	public TicketEntry findByAccountEntryId_First(long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<TicketEntry> orderByComparator)
		throws NoSuchTicketEntryException;

	/**
	* Returns the first ticket entry in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket entry, or <code>null</code> if a matching ticket entry could not be found
	*/
	public TicketEntry fetchByAccountEntryId_First(long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<TicketEntry> orderByComparator);

	/**
	* Returns the last ticket entry in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket entry
	* @throws NoSuchTicketEntryException if a matching ticket entry could not be found
	*/
	public TicketEntry findByAccountEntryId_Last(long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<TicketEntry> orderByComparator)
		throws NoSuchTicketEntryException;

	/**
	* Returns the last ticket entry in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket entry, or <code>null</code> if a matching ticket entry could not be found
	*/
	public TicketEntry fetchByAccountEntryId_Last(long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<TicketEntry> orderByComparator);

	/**
	* Returns the ticket entries before and after the current ticket entry in the ordered set where accountEntryId = &#63;.
	*
	* @param ticketEntryId the primary key of the current ticket entry
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ticket entry
	* @throws NoSuchTicketEntryException if a ticket entry with the primary key could not be found
	*/
	public TicketEntry[] findByAccountEntryId_PrevAndNext(long ticketEntryId,
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<TicketEntry> orderByComparator)
		throws NoSuchTicketEntryException;

	/**
	* Removes all the ticket entries where accountEntryId = &#63; from the database.
	*
	* @param accountEntryId the account entry ID
	*/
	public void removeByAccountEntryId(long accountEntryId);

	/**
	* Returns the number of ticket entries where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @return the number of matching ticket entries
	*/
	public int countByAccountEntryId(long accountEntryId);

	/**
	* Returns all the ticket entries where offeringEntryId = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @return the matching ticket entries
	*/
	public java.util.List<TicketEntry> findByOfferingEntryId(
		long offeringEntryId);

	/**
	* Returns a range of all the ticket entries where offeringEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param offeringEntryId the offering entry ID
	* @param start the lower bound of the range of ticket entries
	* @param end the upper bound of the range of ticket entries (not inclusive)
	* @return the range of matching ticket entries
	*/
	public java.util.List<TicketEntry> findByOfferingEntryId(
		long offeringEntryId, int start, int end);

	/**
	* Returns an ordered range of all the ticket entries where offeringEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param offeringEntryId the offering entry ID
	* @param start the lower bound of the range of ticket entries
	* @param end the upper bound of the range of ticket entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket entries
	*/
	public java.util.List<TicketEntry> findByOfferingEntryId(
		long offeringEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TicketEntry> orderByComparator);

	/**
	* Returns an ordered range of all the ticket entries where offeringEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param offeringEntryId the offering entry ID
	* @param start the lower bound of the range of ticket entries
	* @param end the upper bound of the range of ticket entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching ticket entries
	*/
	public java.util.List<TicketEntry> findByOfferingEntryId(
		long offeringEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TicketEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first ticket entry in the ordered set where offeringEntryId = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket entry
	* @throws NoSuchTicketEntryException if a matching ticket entry could not be found
	*/
	public TicketEntry findByOfferingEntryId_First(long offeringEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<TicketEntry> orderByComparator)
		throws NoSuchTicketEntryException;

	/**
	* Returns the first ticket entry in the ordered set where offeringEntryId = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket entry, or <code>null</code> if a matching ticket entry could not be found
	*/
	public TicketEntry fetchByOfferingEntryId_First(long offeringEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<TicketEntry> orderByComparator);

	/**
	* Returns the last ticket entry in the ordered set where offeringEntryId = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket entry
	* @throws NoSuchTicketEntryException if a matching ticket entry could not be found
	*/
	public TicketEntry findByOfferingEntryId_Last(long offeringEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<TicketEntry> orderByComparator)
		throws NoSuchTicketEntryException;

	/**
	* Returns the last ticket entry in the ordered set where offeringEntryId = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket entry, or <code>null</code> if a matching ticket entry could not be found
	*/
	public TicketEntry fetchByOfferingEntryId_Last(long offeringEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<TicketEntry> orderByComparator);

	/**
	* Returns the ticket entries before and after the current ticket entry in the ordered set where offeringEntryId = &#63;.
	*
	* @param ticketEntryId the primary key of the current ticket entry
	* @param offeringEntryId the offering entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ticket entry
	* @throws NoSuchTicketEntryException if a ticket entry with the primary key could not be found
	*/
	public TicketEntry[] findByOfferingEntryId_PrevAndNext(long ticketEntryId,
		long offeringEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<TicketEntry> orderByComparator)
		throws NoSuchTicketEntryException;

	/**
	* Removes all the ticket entries where offeringEntryId = &#63; from the database.
	*
	* @param offeringEntryId the offering entry ID
	*/
	public void removeByOfferingEntryId(long offeringEntryId);

	/**
	* Returns the number of ticket entries where offeringEntryId = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @return the number of matching ticket entries
	*/
	public int countByOfferingEntryId(long offeringEntryId);

	/**
	* Returns the ticket entry where accountEntryId = &#63; and ticketId = &#63; or throws a {@link NoSuchTicketEntryException} if it could not be found.
	*
	* @param accountEntryId the account entry ID
	* @param ticketId the ticket ID
	* @return the matching ticket entry
	* @throws NoSuchTicketEntryException if a matching ticket entry could not be found
	*/
	public TicketEntry findByAEI_TI(long accountEntryId, long ticketId)
		throws NoSuchTicketEntryException;

	/**
	* Returns the ticket entry where accountEntryId = &#63; and ticketId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param accountEntryId the account entry ID
	* @param ticketId the ticket ID
	* @return the matching ticket entry, or <code>null</code> if a matching ticket entry could not be found
	*/
	public TicketEntry fetchByAEI_TI(long accountEntryId, long ticketId);

	/**
	* Returns the ticket entry where accountEntryId = &#63; and ticketId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param accountEntryId the account entry ID
	* @param ticketId the ticket ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching ticket entry, or <code>null</code> if a matching ticket entry could not be found
	*/
	public TicketEntry fetchByAEI_TI(long accountEntryId, long ticketId,
		boolean retrieveFromCache);

	/**
	* Removes the ticket entry where accountEntryId = &#63; and ticketId = &#63; from the database.
	*
	* @param accountEntryId the account entry ID
	* @param ticketId the ticket ID
	* @return the ticket entry that was removed
	*/
	public TicketEntry removeByAEI_TI(long accountEntryId, long ticketId)
		throws NoSuchTicketEntryException;

	/**
	* Returns the number of ticket entries where accountEntryId = &#63; and ticketId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param ticketId the ticket ID
	* @return the number of matching ticket entries
	*/
	public int countByAEI_TI(long accountEntryId, long ticketId);

	/**
	* Returns all the ticket entries where offeringEntryId = &#63; and resolution &ne; &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param resolution the resolution
	* @return the matching ticket entries
	*/
	public java.util.List<TicketEntry> findByOEI_NotR(long offeringEntryId,
		int resolution);

	/**
	* Returns a range of all the ticket entries where offeringEntryId = &#63; and resolution &ne; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param offeringEntryId the offering entry ID
	* @param resolution the resolution
	* @param start the lower bound of the range of ticket entries
	* @param end the upper bound of the range of ticket entries (not inclusive)
	* @return the range of matching ticket entries
	*/
	public java.util.List<TicketEntry> findByOEI_NotR(long offeringEntryId,
		int resolution, int start, int end);

	/**
	* Returns an ordered range of all the ticket entries where offeringEntryId = &#63; and resolution &ne; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param offeringEntryId the offering entry ID
	* @param resolution the resolution
	* @param start the lower bound of the range of ticket entries
	* @param end the upper bound of the range of ticket entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket entries
	*/
	public java.util.List<TicketEntry> findByOEI_NotR(long offeringEntryId,
		int resolution, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TicketEntry> orderByComparator);

	/**
	* Returns an ordered range of all the ticket entries where offeringEntryId = &#63; and resolution &ne; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param offeringEntryId the offering entry ID
	* @param resolution the resolution
	* @param start the lower bound of the range of ticket entries
	* @param end the upper bound of the range of ticket entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching ticket entries
	*/
	public java.util.List<TicketEntry> findByOEI_NotR(long offeringEntryId,
		int resolution, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TicketEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first ticket entry in the ordered set where offeringEntryId = &#63; and resolution &ne; &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param resolution the resolution
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket entry
	* @throws NoSuchTicketEntryException if a matching ticket entry could not be found
	*/
	public TicketEntry findByOEI_NotR_First(long offeringEntryId,
		int resolution,
		com.liferay.portal.kernel.util.OrderByComparator<TicketEntry> orderByComparator)
		throws NoSuchTicketEntryException;

	/**
	* Returns the first ticket entry in the ordered set where offeringEntryId = &#63; and resolution &ne; &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param resolution the resolution
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket entry, or <code>null</code> if a matching ticket entry could not be found
	*/
	public TicketEntry fetchByOEI_NotR_First(long offeringEntryId,
		int resolution,
		com.liferay.portal.kernel.util.OrderByComparator<TicketEntry> orderByComparator);

	/**
	* Returns the last ticket entry in the ordered set where offeringEntryId = &#63; and resolution &ne; &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param resolution the resolution
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket entry
	* @throws NoSuchTicketEntryException if a matching ticket entry could not be found
	*/
	public TicketEntry findByOEI_NotR_Last(long offeringEntryId,
		int resolution,
		com.liferay.portal.kernel.util.OrderByComparator<TicketEntry> orderByComparator)
		throws NoSuchTicketEntryException;

	/**
	* Returns the last ticket entry in the ordered set where offeringEntryId = &#63; and resolution &ne; &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param resolution the resolution
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket entry, or <code>null</code> if a matching ticket entry could not be found
	*/
	public TicketEntry fetchByOEI_NotR_Last(long offeringEntryId,
		int resolution,
		com.liferay.portal.kernel.util.OrderByComparator<TicketEntry> orderByComparator);

	/**
	* Returns the ticket entries before and after the current ticket entry in the ordered set where offeringEntryId = &#63; and resolution &ne; &#63;.
	*
	* @param ticketEntryId the primary key of the current ticket entry
	* @param offeringEntryId the offering entry ID
	* @param resolution the resolution
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ticket entry
	* @throws NoSuchTicketEntryException if a ticket entry with the primary key could not be found
	*/
	public TicketEntry[] findByOEI_NotR_PrevAndNext(long ticketEntryId,
		long offeringEntryId, int resolution,
		com.liferay.portal.kernel.util.OrderByComparator<TicketEntry> orderByComparator)
		throws NoSuchTicketEntryException;

	/**
	* Removes all the ticket entries where offeringEntryId = &#63; and resolution &ne; &#63; from the database.
	*
	* @param offeringEntryId the offering entry ID
	* @param resolution the resolution
	*/
	public void removeByOEI_NotR(long offeringEntryId, int resolution);

	/**
	* Returns the number of ticket entries where offeringEntryId = &#63; and resolution &ne; &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param resolution the resolution
	* @return the number of matching ticket entries
	*/
	public int countByOEI_NotR(long offeringEntryId, int resolution);

	/**
	* Caches the ticket entry in the entity cache if it is enabled.
	*
	* @param ticketEntry the ticket entry
	*/
	public void cacheResult(TicketEntry ticketEntry);

	/**
	* Caches the ticket entries in the entity cache if it is enabled.
	*
	* @param ticketEntries the ticket entries
	*/
	public void cacheResult(java.util.List<TicketEntry> ticketEntries);

	/**
	* Creates a new ticket entry with the primary key. Does not add the ticket entry to the database.
	*
	* @param ticketEntryId the primary key for the new ticket entry
	* @return the new ticket entry
	*/
	public TicketEntry create(long ticketEntryId);

	/**
	* Removes the ticket entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketEntryId the primary key of the ticket entry
	* @return the ticket entry that was removed
	* @throws NoSuchTicketEntryException if a ticket entry with the primary key could not be found
	*/
	public TicketEntry remove(long ticketEntryId)
		throws NoSuchTicketEntryException;

	public TicketEntry updateImpl(TicketEntry ticketEntry);

	/**
	* Returns the ticket entry with the primary key or throws a {@link NoSuchTicketEntryException} if it could not be found.
	*
	* @param ticketEntryId the primary key of the ticket entry
	* @return the ticket entry
	* @throws NoSuchTicketEntryException if a ticket entry with the primary key could not be found
	*/
	public TicketEntry findByPrimaryKey(long ticketEntryId)
		throws NoSuchTicketEntryException;

	/**
	* Returns the ticket entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ticketEntryId the primary key of the ticket entry
	* @return the ticket entry, or <code>null</code> if a ticket entry with the primary key could not be found
	*/
	public TicketEntry fetchByPrimaryKey(long ticketEntryId);

	@Override
	public java.util.Map<java.io.Serializable, TicketEntry> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the ticket entries.
	*
	* @return the ticket entries
	*/
	public java.util.List<TicketEntry> findAll();

	/**
	* Returns a range of all the ticket entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ticket entries
	* @param end the upper bound of the range of ticket entries (not inclusive)
	* @return the range of ticket entries
	*/
	public java.util.List<TicketEntry> findAll(int start, int end);

	/**
	* Returns an ordered range of all the ticket entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ticket entries
	* @param end the upper bound of the range of ticket entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ticket entries
	*/
	public java.util.List<TicketEntry> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TicketEntry> orderByComparator);

	/**
	* Returns an ordered range of all the ticket entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ticket entries
	* @param end the upper bound of the range of ticket entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ticket entries
	*/
	public java.util.List<TicketEntry> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TicketEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the ticket entries from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of ticket entries.
	*
	* @return the number of ticket entries
	*/
	public int countAll();
}