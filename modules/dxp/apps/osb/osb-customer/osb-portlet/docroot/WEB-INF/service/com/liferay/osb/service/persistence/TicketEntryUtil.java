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

import com.liferay.osb.model.TicketEntry;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;

import java.util.Date;
import java.util.List;

/**
 * The persistence utility for the ticket entry service. This utility wraps {@link com.liferay.osb.service.persistence.impl.TicketEntryPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TicketEntryPersistence
 * @see com.liferay.osb.service.persistence.impl.TicketEntryPersistenceImpl
 * @generated
 */
@ProviderType
public class TicketEntryUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(TicketEntry ticketEntry) {
		getPersistence().clearCache(ticketEntry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<TicketEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<TicketEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<TicketEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<TicketEntry> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static TicketEntry update(TicketEntry ticketEntry) {
		return getPersistence().update(ticketEntry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static TicketEntry update(TicketEntry ticketEntry,
		ServiceContext serviceContext) {
		return getPersistence().update(ticketEntry, serviceContext);
	}

	/**
	* Returns all the ticket entries where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching ticket entries
	*/
	public static List<TicketEntry> findByCompanyId(long companyId) {
		return getPersistence().findByCompanyId(companyId);
	}

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
	public static List<TicketEntry> findByCompanyId(long companyId, int start,
		int end) {
		return getPersistence().findByCompanyId(companyId, start, end);
	}

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
	public static List<TicketEntry> findByCompanyId(long companyId, int start,
		int end, OrderByComparator<TicketEntry> orderByComparator) {
		return getPersistence()
				   .findByCompanyId(companyId, start, end, orderByComparator);
	}

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
	public static List<TicketEntry> findByCompanyId(long companyId, int start,
		int end, OrderByComparator<TicketEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCompanyId(companyId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first ticket entry in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket entry
	* @throws NoSuchTicketEntryException if a matching ticket entry could not be found
	*/
	public static TicketEntry findByCompanyId_First(long companyId,
		OrderByComparator<TicketEntry> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketEntryException {
		return getPersistence()
				   .findByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the first ticket entry in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket entry, or <code>null</code> if a matching ticket entry could not be found
	*/
	public static TicketEntry fetchByCompanyId_First(long companyId,
		OrderByComparator<TicketEntry> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the last ticket entry in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket entry
	* @throws NoSuchTicketEntryException if a matching ticket entry could not be found
	*/
	public static TicketEntry findByCompanyId_Last(long companyId,
		OrderByComparator<TicketEntry> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketEntryException {
		return getPersistence()
				   .findByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the last ticket entry in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket entry, or <code>null</code> if a matching ticket entry could not be found
	*/
	public static TicketEntry fetchByCompanyId_Last(long companyId,
		OrderByComparator<TicketEntry> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the ticket entries before and after the current ticket entry in the ordered set where companyId = &#63;.
	*
	* @param ticketEntryId the primary key of the current ticket entry
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ticket entry
	* @throws NoSuchTicketEntryException if a ticket entry with the primary key could not be found
	*/
	public static TicketEntry[] findByCompanyId_PrevAndNext(
		long ticketEntryId, long companyId,
		OrderByComparator<TicketEntry> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketEntryException {
		return getPersistence()
				   .findByCompanyId_PrevAndNext(ticketEntryId, companyId,
			orderByComparator);
	}

	/**
	* Removes all the ticket entries where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	*/
	public static void removeByCompanyId(long companyId) {
		getPersistence().removeByCompanyId(companyId);
	}

	/**
	* Returns the number of ticket entries where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching ticket entries
	*/
	public static int countByCompanyId(long companyId) {
		return getPersistence().countByCompanyId(companyId);
	}

	/**
	* Returns all the ticket entries where modifiedDate &ge; &#63;.
	*
	* @param modifiedDate the modified date
	* @return the matching ticket entries
	*/
	public static List<TicketEntry> findByGtModifiedDate(Date modifiedDate) {
		return getPersistence().findByGtModifiedDate(modifiedDate);
	}

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
	public static List<TicketEntry> findByGtModifiedDate(Date modifiedDate,
		int start, int end) {
		return getPersistence().findByGtModifiedDate(modifiedDate, start, end);
	}

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
	public static List<TicketEntry> findByGtModifiedDate(Date modifiedDate,
		int start, int end, OrderByComparator<TicketEntry> orderByComparator) {
		return getPersistence()
				   .findByGtModifiedDate(modifiedDate, start, end,
			orderByComparator);
	}

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
	public static List<TicketEntry> findByGtModifiedDate(Date modifiedDate,
		int start, int end, OrderByComparator<TicketEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByGtModifiedDate(modifiedDate, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first ticket entry in the ordered set where modifiedDate &ge; &#63;.
	*
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket entry
	* @throws NoSuchTicketEntryException if a matching ticket entry could not be found
	*/
	public static TicketEntry findByGtModifiedDate_First(Date modifiedDate,
		OrderByComparator<TicketEntry> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketEntryException {
		return getPersistence()
				   .findByGtModifiedDate_First(modifiedDate, orderByComparator);
	}

	/**
	* Returns the first ticket entry in the ordered set where modifiedDate &ge; &#63;.
	*
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket entry, or <code>null</code> if a matching ticket entry could not be found
	*/
	public static TicketEntry fetchByGtModifiedDate_First(Date modifiedDate,
		OrderByComparator<TicketEntry> orderByComparator) {
		return getPersistence()
				   .fetchByGtModifiedDate_First(modifiedDate, orderByComparator);
	}

	/**
	* Returns the last ticket entry in the ordered set where modifiedDate &ge; &#63;.
	*
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket entry
	* @throws NoSuchTicketEntryException if a matching ticket entry could not be found
	*/
	public static TicketEntry findByGtModifiedDate_Last(Date modifiedDate,
		OrderByComparator<TicketEntry> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketEntryException {
		return getPersistence()
				   .findByGtModifiedDate_Last(modifiedDate, orderByComparator);
	}

	/**
	* Returns the last ticket entry in the ordered set where modifiedDate &ge; &#63;.
	*
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket entry, or <code>null</code> if a matching ticket entry could not be found
	*/
	public static TicketEntry fetchByGtModifiedDate_Last(Date modifiedDate,
		OrderByComparator<TicketEntry> orderByComparator) {
		return getPersistence()
				   .fetchByGtModifiedDate_Last(modifiedDate, orderByComparator);
	}

	/**
	* Returns the ticket entries before and after the current ticket entry in the ordered set where modifiedDate &ge; &#63;.
	*
	* @param ticketEntryId the primary key of the current ticket entry
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ticket entry
	* @throws NoSuchTicketEntryException if a ticket entry with the primary key could not be found
	*/
	public static TicketEntry[] findByGtModifiedDate_PrevAndNext(
		long ticketEntryId, Date modifiedDate,
		OrderByComparator<TicketEntry> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketEntryException {
		return getPersistence()
				   .findByGtModifiedDate_PrevAndNext(ticketEntryId,
			modifiedDate, orderByComparator);
	}

	/**
	* Removes all the ticket entries where modifiedDate &ge; &#63; from the database.
	*
	* @param modifiedDate the modified date
	*/
	public static void removeByGtModifiedDate(Date modifiedDate) {
		getPersistence().removeByGtModifiedDate(modifiedDate);
	}

	/**
	* Returns the number of ticket entries where modifiedDate &ge; &#63;.
	*
	* @param modifiedDate the modified date
	* @return the number of matching ticket entries
	*/
	public static int countByGtModifiedDate(Date modifiedDate) {
		return getPersistence().countByGtModifiedDate(modifiedDate);
	}

	/**
	* Returns all the ticket entries where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @return the matching ticket entries
	*/
	public static List<TicketEntry> findByAccountEntryId(long accountEntryId) {
		return getPersistence().findByAccountEntryId(accountEntryId);
	}

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
	public static List<TicketEntry> findByAccountEntryId(long accountEntryId,
		int start, int end) {
		return getPersistence().findByAccountEntryId(accountEntryId, start, end);
	}

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
	public static List<TicketEntry> findByAccountEntryId(long accountEntryId,
		int start, int end, OrderByComparator<TicketEntry> orderByComparator) {
		return getPersistence()
				   .findByAccountEntryId(accountEntryId, start, end,
			orderByComparator);
	}

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
	public static List<TicketEntry> findByAccountEntryId(long accountEntryId,
		int start, int end, OrderByComparator<TicketEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByAccountEntryId(accountEntryId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first ticket entry in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket entry
	* @throws NoSuchTicketEntryException if a matching ticket entry could not be found
	*/
	public static TicketEntry findByAccountEntryId_First(long accountEntryId,
		OrderByComparator<TicketEntry> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketEntryException {
		return getPersistence()
				   .findByAccountEntryId_First(accountEntryId, orderByComparator);
	}

	/**
	* Returns the first ticket entry in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket entry, or <code>null</code> if a matching ticket entry could not be found
	*/
	public static TicketEntry fetchByAccountEntryId_First(long accountEntryId,
		OrderByComparator<TicketEntry> orderByComparator) {
		return getPersistence()
				   .fetchByAccountEntryId_First(accountEntryId,
			orderByComparator);
	}

	/**
	* Returns the last ticket entry in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket entry
	* @throws NoSuchTicketEntryException if a matching ticket entry could not be found
	*/
	public static TicketEntry findByAccountEntryId_Last(long accountEntryId,
		OrderByComparator<TicketEntry> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketEntryException {
		return getPersistence()
				   .findByAccountEntryId_Last(accountEntryId, orderByComparator);
	}

	/**
	* Returns the last ticket entry in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket entry, or <code>null</code> if a matching ticket entry could not be found
	*/
	public static TicketEntry fetchByAccountEntryId_Last(long accountEntryId,
		OrderByComparator<TicketEntry> orderByComparator) {
		return getPersistence()
				   .fetchByAccountEntryId_Last(accountEntryId, orderByComparator);
	}

	/**
	* Returns the ticket entries before and after the current ticket entry in the ordered set where accountEntryId = &#63;.
	*
	* @param ticketEntryId the primary key of the current ticket entry
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ticket entry
	* @throws NoSuchTicketEntryException if a ticket entry with the primary key could not be found
	*/
	public static TicketEntry[] findByAccountEntryId_PrevAndNext(
		long ticketEntryId, long accountEntryId,
		OrderByComparator<TicketEntry> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketEntryException {
		return getPersistence()
				   .findByAccountEntryId_PrevAndNext(ticketEntryId,
			accountEntryId, orderByComparator);
	}

	/**
	* Removes all the ticket entries where accountEntryId = &#63; from the database.
	*
	* @param accountEntryId the account entry ID
	*/
	public static void removeByAccountEntryId(long accountEntryId) {
		getPersistence().removeByAccountEntryId(accountEntryId);
	}

	/**
	* Returns the number of ticket entries where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @return the number of matching ticket entries
	*/
	public static int countByAccountEntryId(long accountEntryId) {
		return getPersistence().countByAccountEntryId(accountEntryId);
	}

	/**
	* Returns all the ticket entries where offeringEntryId = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @return the matching ticket entries
	*/
	public static List<TicketEntry> findByOfferingEntryId(long offeringEntryId) {
		return getPersistence().findByOfferingEntryId(offeringEntryId);
	}

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
	public static List<TicketEntry> findByOfferingEntryId(
		long offeringEntryId, int start, int end) {
		return getPersistence()
				   .findByOfferingEntryId(offeringEntryId, start, end);
	}

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
	public static List<TicketEntry> findByOfferingEntryId(
		long offeringEntryId, int start, int end,
		OrderByComparator<TicketEntry> orderByComparator) {
		return getPersistence()
				   .findByOfferingEntryId(offeringEntryId, start, end,
			orderByComparator);
	}

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
	public static List<TicketEntry> findByOfferingEntryId(
		long offeringEntryId, int start, int end,
		OrderByComparator<TicketEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByOfferingEntryId(offeringEntryId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first ticket entry in the ordered set where offeringEntryId = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket entry
	* @throws NoSuchTicketEntryException if a matching ticket entry could not be found
	*/
	public static TicketEntry findByOfferingEntryId_First(
		long offeringEntryId, OrderByComparator<TicketEntry> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketEntryException {
		return getPersistence()
				   .findByOfferingEntryId_First(offeringEntryId,
			orderByComparator);
	}

	/**
	* Returns the first ticket entry in the ordered set where offeringEntryId = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket entry, or <code>null</code> if a matching ticket entry could not be found
	*/
	public static TicketEntry fetchByOfferingEntryId_First(
		long offeringEntryId, OrderByComparator<TicketEntry> orderByComparator) {
		return getPersistence()
				   .fetchByOfferingEntryId_First(offeringEntryId,
			orderByComparator);
	}

	/**
	* Returns the last ticket entry in the ordered set where offeringEntryId = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket entry
	* @throws NoSuchTicketEntryException if a matching ticket entry could not be found
	*/
	public static TicketEntry findByOfferingEntryId_Last(long offeringEntryId,
		OrderByComparator<TicketEntry> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketEntryException {
		return getPersistence()
				   .findByOfferingEntryId_Last(offeringEntryId,
			orderByComparator);
	}

	/**
	* Returns the last ticket entry in the ordered set where offeringEntryId = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket entry, or <code>null</code> if a matching ticket entry could not be found
	*/
	public static TicketEntry fetchByOfferingEntryId_Last(
		long offeringEntryId, OrderByComparator<TicketEntry> orderByComparator) {
		return getPersistence()
				   .fetchByOfferingEntryId_Last(offeringEntryId,
			orderByComparator);
	}

	/**
	* Returns the ticket entries before and after the current ticket entry in the ordered set where offeringEntryId = &#63;.
	*
	* @param ticketEntryId the primary key of the current ticket entry
	* @param offeringEntryId the offering entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ticket entry
	* @throws NoSuchTicketEntryException if a ticket entry with the primary key could not be found
	*/
	public static TicketEntry[] findByOfferingEntryId_PrevAndNext(
		long ticketEntryId, long offeringEntryId,
		OrderByComparator<TicketEntry> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketEntryException {
		return getPersistence()
				   .findByOfferingEntryId_PrevAndNext(ticketEntryId,
			offeringEntryId, orderByComparator);
	}

	/**
	* Removes all the ticket entries where offeringEntryId = &#63; from the database.
	*
	* @param offeringEntryId the offering entry ID
	*/
	public static void removeByOfferingEntryId(long offeringEntryId) {
		getPersistence().removeByOfferingEntryId(offeringEntryId);
	}

	/**
	* Returns the number of ticket entries where offeringEntryId = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @return the number of matching ticket entries
	*/
	public static int countByOfferingEntryId(long offeringEntryId) {
		return getPersistence().countByOfferingEntryId(offeringEntryId);
	}

	/**
	* Returns the ticket entry where accountEntryId = &#63; and ticketId = &#63; or throws a {@link NoSuchTicketEntryException} if it could not be found.
	*
	* @param accountEntryId the account entry ID
	* @param ticketId the ticket ID
	* @return the matching ticket entry
	* @throws NoSuchTicketEntryException if a matching ticket entry could not be found
	*/
	public static TicketEntry findByAEI_TI(long accountEntryId, long ticketId)
		throws com.liferay.osb.exception.NoSuchTicketEntryException {
		return getPersistence().findByAEI_TI(accountEntryId, ticketId);
	}

	/**
	* Returns the ticket entry where accountEntryId = &#63; and ticketId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param accountEntryId the account entry ID
	* @param ticketId the ticket ID
	* @return the matching ticket entry, or <code>null</code> if a matching ticket entry could not be found
	*/
	public static TicketEntry fetchByAEI_TI(long accountEntryId, long ticketId) {
		return getPersistence().fetchByAEI_TI(accountEntryId, ticketId);
	}

	/**
	* Returns the ticket entry where accountEntryId = &#63; and ticketId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param accountEntryId the account entry ID
	* @param ticketId the ticket ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching ticket entry, or <code>null</code> if a matching ticket entry could not be found
	*/
	public static TicketEntry fetchByAEI_TI(long accountEntryId, long ticketId,
		boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByAEI_TI(accountEntryId, ticketId, retrieveFromCache);
	}

	/**
	* Removes the ticket entry where accountEntryId = &#63; and ticketId = &#63; from the database.
	*
	* @param accountEntryId the account entry ID
	* @param ticketId the ticket ID
	* @return the ticket entry that was removed
	*/
	public static TicketEntry removeByAEI_TI(long accountEntryId, long ticketId)
		throws com.liferay.osb.exception.NoSuchTicketEntryException {
		return getPersistence().removeByAEI_TI(accountEntryId, ticketId);
	}

	/**
	* Returns the number of ticket entries where accountEntryId = &#63; and ticketId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param ticketId the ticket ID
	* @return the number of matching ticket entries
	*/
	public static int countByAEI_TI(long accountEntryId, long ticketId) {
		return getPersistence().countByAEI_TI(accountEntryId, ticketId);
	}

	/**
	* Returns all the ticket entries where offeringEntryId = &#63; and resolution &ne; &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param resolution the resolution
	* @return the matching ticket entries
	*/
	public static List<TicketEntry> findByOEI_NotR(long offeringEntryId,
		int resolution) {
		return getPersistence().findByOEI_NotR(offeringEntryId, resolution);
	}

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
	public static List<TicketEntry> findByOEI_NotR(long offeringEntryId,
		int resolution, int start, int end) {
		return getPersistence()
				   .findByOEI_NotR(offeringEntryId, resolution, start, end);
	}

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
	public static List<TicketEntry> findByOEI_NotR(long offeringEntryId,
		int resolution, int start, int end,
		OrderByComparator<TicketEntry> orderByComparator) {
		return getPersistence()
				   .findByOEI_NotR(offeringEntryId, resolution, start, end,
			orderByComparator);
	}

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
	public static List<TicketEntry> findByOEI_NotR(long offeringEntryId,
		int resolution, int start, int end,
		OrderByComparator<TicketEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByOEI_NotR(offeringEntryId, resolution, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first ticket entry in the ordered set where offeringEntryId = &#63; and resolution &ne; &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param resolution the resolution
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket entry
	* @throws NoSuchTicketEntryException if a matching ticket entry could not be found
	*/
	public static TicketEntry findByOEI_NotR_First(long offeringEntryId,
		int resolution, OrderByComparator<TicketEntry> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketEntryException {
		return getPersistence()
				   .findByOEI_NotR_First(offeringEntryId, resolution,
			orderByComparator);
	}

	/**
	* Returns the first ticket entry in the ordered set where offeringEntryId = &#63; and resolution &ne; &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param resolution the resolution
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket entry, or <code>null</code> if a matching ticket entry could not be found
	*/
	public static TicketEntry fetchByOEI_NotR_First(long offeringEntryId,
		int resolution, OrderByComparator<TicketEntry> orderByComparator) {
		return getPersistence()
				   .fetchByOEI_NotR_First(offeringEntryId, resolution,
			orderByComparator);
	}

	/**
	* Returns the last ticket entry in the ordered set where offeringEntryId = &#63; and resolution &ne; &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param resolution the resolution
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket entry
	* @throws NoSuchTicketEntryException if a matching ticket entry could not be found
	*/
	public static TicketEntry findByOEI_NotR_Last(long offeringEntryId,
		int resolution, OrderByComparator<TicketEntry> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketEntryException {
		return getPersistence()
				   .findByOEI_NotR_Last(offeringEntryId, resolution,
			orderByComparator);
	}

	/**
	* Returns the last ticket entry in the ordered set where offeringEntryId = &#63; and resolution &ne; &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param resolution the resolution
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket entry, or <code>null</code> if a matching ticket entry could not be found
	*/
	public static TicketEntry fetchByOEI_NotR_Last(long offeringEntryId,
		int resolution, OrderByComparator<TicketEntry> orderByComparator) {
		return getPersistence()
				   .fetchByOEI_NotR_Last(offeringEntryId, resolution,
			orderByComparator);
	}

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
	public static TicketEntry[] findByOEI_NotR_PrevAndNext(long ticketEntryId,
		long offeringEntryId, int resolution,
		OrderByComparator<TicketEntry> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketEntryException {
		return getPersistence()
				   .findByOEI_NotR_PrevAndNext(ticketEntryId, offeringEntryId,
			resolution, orderByComparator);
	}

	/**
	* Removes all the ticket entries where offeringEntryId = &#63; and resolution &ne; &#63; from the database.
	*
	* @param offeringEntryId the offering entry ID
	* @param resolution the resolution
	*/
	public static void removeByOEI_NotR(long offeringEntryId, int resolution) {
		getPersistence().removeByOEI_NotR(offeringEntryId, resolution);
	}

	/**
	* Returns the number of ticket entries where offeringEntryId = &#63; and resolution &ne; &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param resolution the resolution
	* @return the number of matching ticket entries
	*/
	public static int countByOEI_NotR(long offeringEntryId, int resolution) {
		return getPersistence().countByOEI_NotR(offeringEntryId, resolution);
	}

	/**
	* Caches the ticket entry in the entity cache if it is enabled.
	*
	* @param ticketEntry the ticket entry
	*/
	public static void cacheResult(TicketEntry ticketEntry) {
		getPersistence().cacheResult(ticketEntry);
	}

	/**
	* Caches the ticket entries in the entity cache if it is enabled.
	*
	* @param ticketEntries the ticket entries
	*/
	public static void cacheResult(List<TicketEntry> ticketEntries) {
		getPersistence().cacheResult(ticketEntries);
	}

	/**
	* Creates a new ticket entry with the primary key. Does not add the ticket entry to the database.
	*
	* @param ticketEntryId the primary key for the new ticket entry
	* @return the new ticket entry
	*/
	public static TicketEntry create(long ticketEntryId) {
		return getPersistence().create(ticketEntryId);
	}

	/**
	* Removes the ticket entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketEntryId the primary key of the ticket entry
	* @return the ticket entry that was removed
	* @throws NoSuchTicketEntryException if a ticket entry with the primary key could not be found
	*/
	public static TicketEntry remove(long ticketEntryId)
		throws com.liferay.osb.exception.NoSuchTicketEntryException {
		return getPersistence().remove(ticketEntryId);
	}

	public static TicketEntry updateImpl(TicketEntry ticketEntry) {
		return getPersistence().updateImpl(ticketEntry);
	}

	/**
	* Returns the ticket entry with the primary key or throws a {@link NoSuchTicketEntryException} if it could not be found.
	*
	* @param ticketEntryId the primary key of the ticket entry
	* @return the ticket entry
	* @throws NoSuchTicketEntryException if a ticket entry with the primary key could not be found
	*/
	public static TicketEntry findByPrimaryKey(long ticketEntryId)
		throws com.liferay.osb.exception.NoSuchTicketEntryException {
		return getPersistence().findByPrimaryKey(ticketEntryId);
	}

	/**
	* Returns the ticket entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ticketEntryId the primary key of the ticket entry
	* @return the ticket entry, or <code>null</code> if a ticket entry with the primary key could not be found
	*/
	public static TicketEntry fetchByPrimaryKey(long ticketEntryId) {
		return getPersistence().fetchByPrimaryKey(ticketEntryId);
	}

	public static java.util.Map<java.io.Serializable, TicketEntry> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the ticket entries.
	*
	* @return the ticket entries
	*/
	public static List<TicketEntry> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<TicketEntry> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<TicketEntry> findAll(int start, int end,
		OrderByComparator<TicketEntry> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<TicketEntry> findAll(int start, int end,
		OrderByComparator<TicketEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the ticket entries from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of ticket entries.
	*
	* @return the number of ticket entries
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static TicketEntryPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (TicketEntryPersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					TicketEntryPersistence.class.getName());

			ReferenceRegistry.registerReference(TicketEntryUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	private static TicketEntryPersistence _persistence;
}