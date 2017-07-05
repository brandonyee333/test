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

import com.liferay.osb.model.TicketLink;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;

import java.util.List;

/**
 * The persistence utility for the ticket link service. This utility wraps {@link com.liferay.osb.service.persistence.impl.TicketLinkPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TicketLinkPersistence
 * @see com.liferay.osb.service.persistence.impl.TicketLinkPersistenceImpl
 * @generated
 */
@ProviderType
public class TicketLinkUtil {
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
	public static void clearCache(TicketLink ticketLink) {
		getPersistence().clearCache(ticketLink);
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
	public static List<TicketLink> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<TicketLink> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<TicketLink> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<TicketLink> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static TicketLink update(TicketLink ticketLink) {
		return getPersistence().update(ticketLink);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static TicketLink update(TicketLink ticketLink,
		ServiceContext serviceContext) {
		return getPersistence().update(ticketLink, serviceContext);
	}

	/**
	* Returns all the ticket links where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @return the matching ticket links
	*/
	public static List<TicketLink> findByTicketEntryId(long ticketEntryId) {
		return getPersistence().findByTicketEntryId(ticketEntryId);
	}

	/**
	* Returns a range of all the ticket links where ticketEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param start the lower bound of the range of ticket links
	* @param end the upper bound of the range of ticket links (not inclusive)
	* @return the range of matching ticket links
	*/
	public static List<TicketLink> findByTicketEntryId(long ticketEntryId,
		int start, int end) {
		return getPersistence().findByTicketEntryId(ticketEntryId, start, end);
	}

	/**
	* Returns an ordered range of all the ticket links where ticketEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param start the lower bound of the range of ticket links
	* @param end the upper bound of the range of ticket links (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket links
	*/
	public static List<TicketLink> findByTicketEntryId(long ticketEntryId,
		int start, int end, OrderByComparator<TicketLink> orderByComparator) {
		return getPersistence()
				   .findByTicketEntryId(ticketEntryId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the ticket links where ticketEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param start the lower bound of the range of ticket links
	* @param end the upper bound of the range of ticket links (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching ticket links
	*/
	public static List<TicketLink> findByTicketEntryId(long ticketEntryId,
		int start, int end, OrderByComparator<TicketLink> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByTicketEntryId(ticketEntryId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first ticket link in the ordered set where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket link
	* @throws NoSuchTicketLinkException if a matching ticket link could not be found
	*/
	public static TicketLink findByTicketEntryId_First(long ticketEntryId,
		OrderByComparator<TicketLink> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketLinkException {
		return getPersistence()
				   .findByTicketEntryId_First(ticketEntryId, orderByComparator);
	}

	/**
	* Returns the first ticket link in the ordered set where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket link, or <code>null</code> if a matching ticket link could not be found
	*/
	public static TicketLink fetchByTicketEntryId_First(long ticketEntryId,
		OrderByComparator<TicketLink> orderByComparator) {
		return getPersistence()
				   .fetchByTicketEntryId_First(ticketEntryId, orderByComparator);
	}

	/**
	* Returns the last ticket link in the ordered set where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket link
	* @throws NoSuchTicketLinkException if a matching ticket link could not be found
	*/
	public static TicketLink findByTicketEntryId_Last(long ticketEntryId,
		OrderByComparator<TicketLink> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketLinkException {
		return getPersistence()
				   .findByTicketEntryId_Last(ticketEntryId, orderByComparator);
	}

	/**
	* Returns the last ticket link in the ordered set where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket link, or <code>null</code> if a matching ticket link could not be found
	*/
	public static TicketLink fetchByTicketEntryId_Last(long ticketEntryId,
		OrderByComparator<TicketLink> orderByComparator) {
		return getPersistence()
				   .fetchByTicketEntryId_Last(ticketEntryId, orderByComparator);
	}

	/**
	* Returns the ticket links before and after the current ticket link in the ordered set where ticketEntryId = &#63;.
	*
	* @param ticketLinkId the primary key of the current ticket link
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ticket link
	* @throws NoSuchTicketLinkException if a ticket link with the primary key could not be found
	*/
	public static TicketLink[] findByTicketEntryId_PrevAndNext(
		long ticketLinkId, long ticketEntryId,
		OrderByComparator<TicketLink> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketLinkException {
		return getPersistence()
				   .findByTicketEntryId_PrevAndNext(ticketLinkId,
			ticketEntryId, orderByComparator);
	}

	/**
	* Removes all the ticket links where ticketEntryId = &#63; from the database.
	*
	* @param ticketEntryId the ticket entry ID
	*/
	public static void removeByTicketEntryId(long ticketEntryId) {
		getPersistence().removeByTicketEntryId(ticketEntryId);
	}

	/**
	* Returns the number of ticket links where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @return the number of matching ticket links
	*/
	public static int countByTicketEntryId(long ticketEntryId) {
		return getPersistence().countByTicketEntryId(ticketEntryId);
	}

	/**
	* Returns all the ticket links where ticketEntryId = &#63; and ticketSolutionId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param ticketSolutionId the ticket solution ID
	* @return the matching ticket links
	*/
	public static List<TicketLink> findByTEI_TSI(long ticketEntryId,
		long ticketSolutionId) {
		return getPersistence().findByTEI_TSI(ticketEntryId, ticketSolutionId);
	}

	/**
	* Returns a range of all the ticket links where ticketEntryId = &#63; and ticketSolutionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param ticketSolutionId the ticket solution ID
	* @param start the lower bound of the range of ticket links
	* @param end the upper bound of the range of ticket links (not inclusive)
	* @return the range of matching ticket links
	*/
	public static List<TicketLink> findByTEI_TSI(long ticketEntryId,
		long ticketSolutionId, int start, int end) {
		return getPersistence()
				   .findByTEI_TSI(ticketEntryId, ticketSolutionId, start, end);
	}

	/**
	* Returns an ordered range of all the ticket links where ticketEntryId = &#63; and ticketSolutionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param ticketSolutionId the ticket solution ID
	* @param start the lower bound of the range of ticket links
	* @param end the upper bound of the range of ticket links (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket links
	*/
	public static List<TicketLink> findByTEI_TSI(long ticketEntryId,
		long ticketSolutionId, int start, int end,
		OrderByComparator<TicketLink> orderByComparator) {
		return getPersistence()
				   .findByTEI_TSI(ticketEntryId, ticketSolutionId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the ticket links where ticketEntryId = &#63; and ticketSolutionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param ticketSolutionId the ticket solution ID
	* @param start the lower bound of the range of ticket links
	* @param end the upper bound of the range of ticket links (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching ticket links
	*/
	public static List<TicketLink> findByTEI_TSI(long ticketEntryId,
		long ticketSolutionId, int start, int end,
		OrderByComparator<TicketLink> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByTEI_TSI(ticketEntryId, ticketSolutionId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first ticket link in the ordered set where ticketEntryId = &#63; and ticketSolutionId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param ticketSolutionId the ticket solution ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket link
	* @throws NoSuchTicketLinkException if a matching ticket link could not be found
	*/
	public static TicketLink findByTEI_TSI_First(long ticketEntryId,
		long ticketSolutionId, OrderByComparator<TicketLink> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketLinkException {
		return getPersistence()
				   .findByTEI_TSI_First(ticketEntryId, ticketSolutionId,
			orderByComparator);
	}

	/**
	* Returns the first ticket link in the ordered set where ticketEntryId = &#63; and ticketSolutionId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param ticketSolutionId the ticket solution ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket link, or <code>null</code> if a matching ticket link could not be found
	*/
	public static TicketLink fetchByTEI_TSI_First(long ticketEntryId,
		long ticketSolutionId, OrderByComparator<TicketLink> orderByComparator) {
		return getPersistence()
				   .fetchByTEI_TSI_First(ticketEntryId, ticketSolutionId,
			orderByComparator);
	}

	/**
	* Returns the last ticket link in the ordered set where ticketEntryId = &#63; and ticketSolutionId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param ticketSolutionId the ticket solution ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket link
	* @throws NoSuchTicketLinkException if a matching ticket link could not be found
	*/
	public static TicketLink findByTEI_TSI_Last(long ticketEntryId,
		long ticketSolutionId, OrderByComparator<TicketLink> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketLinkException {
		return getPersistence()
				   .findByTEI_TSI_Last(ticketEntryId, ticketSolutionId,
			orderByComparator);
	}

	/**
	* Returns the last ticket link in the ordered set where ticketEntryId = &#63; and ticketSolutionId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param ticketSolutionId the ticket solution ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket link, or <code>null</code> if a matching ticket link could not be found
	*/
	public static TicketLink fetchByTEI_TSI_Last(long ticketEntryId,
		long ticketSolutionId, OrderByComparator<TicketLink> orderByComparator) {
		return getPersistence()
				   .fetchByTEI_TSI_Last(ticketEntryId, ticketSolutionId,
			orderByComparator);
	}

	/**
	* Returns the ticket links before and after the current ticket link in the ordered set where ticketEntryId = &#63; and ticketSolutionId = &#63;.
	*
	* @param ticketLinkId the primary key of the current ticket link
	* @param ticketEntryId the ticket entry ID
	* @param ticketSolutionId the ticket solution ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ticket link
	* @throws NoSuchTicketLinkException if a ticket link with the primary key could not be found
	*/
	public static TicketLink[] findByTEI_TSI_PrevAndNext(long ticketLinkId,
		long ticketEntryId, long ticketSolutionId,
		OrderByComparator<TicketLink> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketLinkException {
		return getPersistence()
				   .findByTEI_TSI_PrevAndNext(ticketLinkId, ticketEntryId,
			ticketSolutionId, orderByComparator);
	}

	/**
	* Removes all the ticket links where ticketEntryId = &#63; and ticketSolutionId = &#63; from the database.
	*
	* @param ticketEntryId the ticket entry ID
	* @param ticketSolutionId the ticket solution ID
	*/
	public static void removeByTEI_TSI(long ticketEntryId, long ticketSolutionId) {
		getPersistence().removeByTEI_TSI(ticketEntryId, ticketSolutionId);
	}

	/**
	* Returns the number of ticket links where ticketEntryId = &#63; and ticketSolutionId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param ticketSolutionId the ticket solution ID
	* @return the number of matching ticket links
	*/
	public static int countByTEI_TSI(long ticketEntryId, long ticketSolutionId) {
		return getPersistence().countByTEI_TSI(ticketEntryId, ticketSolutionId);
	}

	/**
	* Returns all the ticket links where ticketEntryId = &#63; and visibility = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @return the matching ticket links
	*/
	public static List<TicketLink> findByTEI_V(long ticketEntryId,
		int visibility) {
		return getPersistence().findByTEI_V(ticketEntryId, visibility);
	}

	/**
	* Returns a range of all the ticket links where ticketEntryId = &#63; and visibility = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param start the lower bound of the range of ticket links
	* @param end the upper bound of the range of ticket links (not inclusive)
	* @return the range of matching ticket links
	*/
	public static List<TicketLink> findByTEI_V(long ticketEntryId,
		int visibility, int start, int end) {
		return getPersistence()
				   .findByTEI_V(ticketEntryId, visibility, start, end);
	}

	/**
	* Returns an ordered range of all the ticket links where ticketEntryId = &#63; and visibility = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param start the lower bound of the range of ticket links
	* @param end the upper bound of the range of ticket links (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket links
	*/
	public static List<TicketLink> findByTEI_V(long ticketEntryId,
		int visibility, int start, int end,
		OrderByComparator<TicketLink> orderByComparator) {
		return getPersistence()
				   .findByTEI_V(ticketEntryId, visibility, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the ticket links where ticketEntryId = &#63; and visibility = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param start the lower bound of the range of ticket links
	* @param end the upper bound of the range of ticket links (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching ticket links
	*/
	public static List<TicketLink> findByTEI_V(long ticketEntryId,
		int visibility, int start, int end,
		OrderByComparator<TicketLink> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByTEI_V(ticketEntryId, visibility, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first ticket link in the ordered set where ticketEntryId = &#63; and visibility = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket link
	* @throws NoSuchTicketLinkException if a matching ticket link could not be found
	*/
	public static TicketLink findByTEI_V_First(long ticketEntryId,
		int visibility, OrderByComparator<TicketLink> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketLinkException {
		return getPersistence()
				   .findByTEI_V_First(ticketEntryId, visibility,
			orderByComparator);
	}

	/**
	* Returns the first ticket link in the ordered set where ticketEntryId = &#63; and visibility = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket link, or <code>null</code> if a matching ticket link could not be found
	*/
	public static TicketLink fetchByTEI_V_First(long ticketEntryId,
		int visibility, OrderByComparator<TicketLink> orderByComparator) {
		return getPersistence()
				   .fetchByTEI_V_First(ticketEntryId, visibility,
			orderByComparator);
	}

	/**
	* Returns the last ticket link in the ordered set where ticketEntryId = &#63; and visibility = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket link
	* @throws NoSuchTicketLinkException if a matching ticket link could not be found
	*/
	public static TicketLink findByTEI_V_Last(long ticketEntryId,
		int visibility, OrderByComparator<TicketLink> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketLinkException {
		return getPersistence()
				   .findByTEI_V_Last(ticketEntryId, visibility,
			orderByComparator);
	}

	/**
	* Returns the last ticket link in the ordered set where ticketEntryId = &#63; and visibility = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket link, or <code>null</code> if a matching ticket link could not be found
	*/
	public static TicketLink fetchByTEI_V_Last(long ticketEntryId,
		int visibility, OrderByComparator<TicketLink> orderByComparator) {
		return getPersistence()
				   .fetchByTEI_V_Last(ticketEntryId, visibility,
			orderByComparator);
	}

	/**
	* Returns the ticket links before and after the current ticket link in the ordered set where ticketEntryId = &#63; and visibility = &#63;.
	*
	* @param ticketLinkId the primary key of the current ticket link
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ticket link
	* @throws NoSuchTicketLinkException if a ticket link with the primary key could not be found
	*/
	public static TicketLink[] findByTEI_V_PrevAndNext(long ticketLinkId,
		long ticketEntryId, int visibility,
		OrderByComparator<TicketLink> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketLinkException {
		return getPersistence()
				   .findByTEI_V_PrevAndNext(ticketLinkId, ticketEntryId,
			visibility, orderByComparator);
	}

	/**
	* Returns all the ticket links where ticketEntryId = &#63; and visibility = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param visibilities the visibilities
	* @return the matching ticket links
	*/
	public static List<TicketLink> findByTEI_V(long ticketEntryId,
		int[] visibilities) {
		return getPersistence().findByTEI_V(ticketEntryId, visibilities);
	}

	/**
	* Returns a range of all the ticket links where ticketEntryId = &#63; and visibility = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param visibilities the visibilities
	* @param start the lower bound of the range of ticket links
	* @param end the upper bound of the range of ticket links (not inclusive)
	* @return the range of matching ticket links
	*/
	public static List<TicketLink> findByTEI_V(long ticketEntryId,
		int[] visibilities, int start, int end) {
		return getPersistence()
				   .findByTEI_V(ticketEntryId, visibilities, start, end);
	}

	/**
	* Returns an ordered range of all the ticket links where ticketEntryId = &#63; and visibility = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param visibilities the visibilities
	* @param start the lower bound of the range of ticket links
	* @param end the upper bound of the range of ticket links (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket links
	*/
	public static List<TicketLink> findByTEI_V(long ticketEntryId,
		int[] visibilities, int start, int end,
		OrderByComparator<TicketLink> orderByComparator) {
		return getPersistence()
				   .findByTEI_V(ticketEntryId, visibilities, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the ticket links where ticketEntryId = &#63; and visibility = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param start the lower bound of the range of ticket links
	* @param end the upper bound of the range of ticket links (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching ticket links
	*/
	public static List<TicketLink> findByTEI_V(long ticketEntryId,
		int[] visibilities, int start, int end,
		OrderByComparator<TicketLink> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByTEI_V(ticketEntryId, visibilities, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the ticket links where ticketEntryId = &#63; and visibility = &#63; from the database.
	*
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	*/
	public static void removeByTEI_V(long ticketEntryId, int visibility) {
		getPersistence().removeByTEI_V(ticketEntryId, visibility);
	}

	/**
	* Returns the number of ticket links where ticketEntryId = &#63; and visibility = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @return the number of matching ticket links
	*/
	public static int countByTEI_V(long ticketEntryId, int visibility) {
		return getPersistence().countByTEI_V(ticketEntryId, visibility);
	}

	/**
	* Returns the number of ticket links where ticketEntryId = &#63; and visibility = any &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param visibilities the visibilities
	* @return the number of matching ticket links
	*/
	public static int countByTEI_V(long ticketEntryId, int[] visibilities) {
		return getPersistence().countByTEI_V(ticketEntryId, visibilities);
	}

	/**
	* Caches the ticket link in the entity cache if it is enabled.
	*
	* @param ticketLink the ticket link
	*/
	public static void cacheResult(TicketLink ticketLink) {
		getPersistence().cacheResult(ticketLink);
	}

	/**
	* Caches the ticket links in the entity cache if it is enabled.
	*
	* @param ticketLinks the ticket links
	*/
	public static void cacheResult(List<TicketLink> ticketLinks) {
		getPersistence().cacheResult(ticketLinks);
	}

	/**
	* Creates a new ticket link with the primary key. Does not add the ticket link to the database.
	*
	* @param ticketLinkId the primary key for the new ticket link
	* @return the new ticket link
	*/
	public static TicketLink create(long ticketLinkId) {
		return getPersistence().create(ticketLinkId);
	}

	/**
	* Removes the ticket link with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketLinkId the primary key of the ticket link
	* @return the ticket link that was removed
	* @throws NoSuchTicketLinkException if a ticket link with the primary key could not be found
	*/
	public static TicketLink remove(long ticketLinkId)
		throws com.liferay.osb.exception.NoSuchTicketLinkException {
		return getPersistence().remove(ticketLinkId);
	}

	public static TicketLink updateImpl(TicketLink ticketLink) {
		return getPersistence().updateImpl(ticketLink);
	}

	/**
	* Returns the ticket link with the primary key or throws a {@link NoSuchTicketLinkException} if it could not be found.
	*
	* @param ticketLinkId the primary key of the ticket link
	* @return the ticket link
	* @throws NoSuchTicketLinkException if a ticket link with the primary key could not be found
	*/
	public static TicketLink findByPrimaryKey(long ticketLinkId)
		throws com.liferay.osb.exception.NoSuchTicketLinkException {
		return getPersistence().findByPrimaryKey(ticketLinkId);
	}

	/**
	* Returns the ticket link with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ticketLinkId the primary key of the ticket link
	* @return the ticket link, or <code>null</code> if a ticket link with the primary key could not be found
	*/
	public static TicketLink fetchByPrimaryKey(long ticketLinkId) {
		return getPersistence().fetchByPrimaryKey(ticketLinkId);
	}

	public static java.util.Map<java.io.Serializable, TicketLink> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the ticket links.
	*
	* @return the ticket links
	*/
	public static List<TicketLink> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the ticket links.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ticket links
	* @param end the upper bound of the range of ticket links (not inclusive)
	* @return the range of ticket links
	*/
	public static List<TicketLink> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the ticket links.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ticket links
	* @param end the upper bound of the range of ticket links (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ticket links
	*/
	public static List<TicketLink> findAll(int start, int end,
		OrderByComparator<TicketLink> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the ticket links.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ticket links
	* @param end the upper bound of the range of ticket links (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ticket links
	*/
	public static List<TicketLink> findAll(int start, int end,
		OrderByComparator<TicketLink> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the ticket links from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of ticket links.
	*
	* @return the number of ticket links
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static TicketLinkPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (TicketLinkPersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					TicketLinkPersistence.class.getName());

			ReferenceRegistry.registerReference(TicketLinkUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	private static TicketLinkPersistence _persistence;
}