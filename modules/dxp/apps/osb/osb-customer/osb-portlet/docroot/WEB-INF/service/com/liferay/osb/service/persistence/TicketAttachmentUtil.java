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

import com.liferay.osb.model.TicketAttachment;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;

import java.util.Date;
import java.util.List;

/**
 * The persistence utility for the ticket attachment service. This utility wraps {@link com.liferay.osb.service.persistence.impl.TicketAttachmentPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TicketAttachmentPersistence
 * @see com.liferay.osb.service.persistence.impl.TicketAttachmentPersistenceImpl
 * @generated
 */
@ProviderType
public class TicketAttachmentUtil {
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
	public static void clearCache(TicketAttachment ticketAttachment) {
		getPersistence().clearCache(ticketAttachment);
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
	public static List<TicketAttachment> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<TicketAttachment> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<TicketAttachment> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<TicketAttachment> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static TicketAttachment update(TicketAttachment ticketAttachment) {
		return getPersistence().update(ticketAttachment);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static TicketAttachment update(TicketAttachment ticketAttachment,
		ServiceContext serviceContext) {
		return getPersistence().update(ticketAttachment, serviceContext);
	}

	/**
	* Returns all the ticket attachments where createDate &lt; &#63; and ticketEntryId = &#63;.
	*
	* @param createDate the create date
	* @param ticketEntryId the ticket entry ID
	* @return the matching ticket attachments
	*/
	public static List<TicketAttachment> findByCD_TEI(Date createDate,
		long ticketEntryId) {
		return getPersistence().findByCD_TEI(createDate, ticketEntryId);
	}

	/**
	* Returns a range of all the ticket attachments where createDate &lt; &#63; and ticketEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param createDate the create date
	* @param ticketEntryId the ticket entry ID
	* @param start the lower bound of the range of ticket attachments
	* @param end the upper bound of the range of ticket attachments (not inclusive)
	* @return the range of matching ticket attachments
	*/
	public static List<TicketAttachment> findByCD_TEI(Date createDate,
		long ticketEntryId, int start, int end) {
		return getPersistence()
				   .findByCD_TEI(createDate, ticketEntryId, start, end);
	}

	/**
	* Returns an ordered range of all the ticket attachments where createDate &lt; &#63; and ticketEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param createDate the create date
	* @param ticketEntryId the ticket entry ID
	* @param start the lower bound of the range of ticket attachments
	* @param end the upper bound of the range of ticket attachments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket attachments
	*/
	public static List<TicketAttachment> findByCD_TEI(Date createDate,
		long ticketEntryId, int start, int end,
		OrderByComparator<TicketAttachment> orderByComparator) {
		return getPersistence()
				   .findByCD_TEI(createDate, ticketEntryId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the ticket attachments where createDate &lt; &#63; and ticketEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param createDate the create date
	* @param ticketEntryId the ticket entry ID
	* @param start the lower bound of the range of ticket attachments
	* @param end the upper bound of the range of ticket attachments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching ticket attachments
	*/
	public static List<TicketAttachment> findByCD_TEI(Date createDate,
		long ticketEntryId, int start, int end,
		OrderByComparator<TicketAttachment> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCD_TEI(createDate, ticketEntryId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first ticket attachment in the ordered set where createDate &lt; &#63; and ticketEntryId = &#63;.
	*
	* @param createDate the create date
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket attachment
	* @throws NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	*/
	public static TicketAttachment findByCD_TEI_First(Date createDate,
		long ticketEntryId,
		OrderByComparator<TicketAttachment> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketAttachmentException {
		return getPersistence()
				   .findByCD_TEI_First(createDate, ticketEntryId,
			orderByComparator);
	}

	/**
	* Returns the first ticket attachment in the ordered set where createDate &lt; &#63; and ticketEntryId = &#63;.
	*
	* @param createDate the create date
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	*/
	public static TicketAttachment fetchByCD_TEI_First(Date createDate,
		long ticketEntryId,
		OrderByComparator<TicketAttachment> orderByComparator) {
		return getPersistence()
				   .fetchByCD_TEI_First(createDate, ticketEntryId,
			orderByComparator);
	}

	/**
	* Returns the last ticket attachment in the ordered set where createDate &lt; &#63; and ticketEntryId = &#63;.
	*
	* @param createDate the create date
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket attachment
	* @throws NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	*/
	public static TicketAttachment findByCD_TEI_Last(Date createDate,
		long ticketEntryId,
		OrderByComparator<TicketAttachment> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketAttachmentException {
		return getPersistence()
				   .findByCD_TEI_Last(createDate, ticketEntryId,
			orderByComparator);
	}

	/**
	* Returns the last ticket attachment in the ordered set where createDate &lt; &#63; and ticketEntryId = &#63;.
	*
	* @param createDate the create date
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	*/
	public static TicketAttachment fetchByCD_TEI_Last(Date createDate,
		long ticketEntryId,
		OrderByComparator<TicketAttachment> orderByComparator) {
		return getPersistence()
				   .fetchByCD_TEI_Last(createDate, ticketEntryId,
			orderByComparator);
	}

	/**
	* Returns the ticket attachments before and after the current ticket attachment in the ordered set where createDate &lt; &#63; and ticketEntryId = &#63;.
	*
	* @param ticketAttachmentId the primary key of the current ticket attachment
	* @param createDate the create date
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ticket attachment
	* @throws NoSuchTicketAttachmentException if a ticket attachment with the primary key could not be found
	*/
	public static TicketAttachment[] findByCD_TEI_PrevAndNext(
		long ticketAttachmentId, Date createDate, long ticketEntryId,
		OrderByComparator<TicketAttachment> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketAttachmentException {
		return getPersistence()
				   .findByCD_TEI_PrevAndNext(ticketAttachmentId, createDate,
			ticketEntryId, orderByComparator);
	}

	/**
	* Removes all the ticket attachments where createDate &lt; &#63; and ticketEntryId = &#63; from the database.
	*
	* @param createDate the create date
	* @param ticketEntryId the ticket entry ID
	*/
	public static void removeByCD_TEI(Date createDate, long ticketEntryId) {
		getPersistence().removeByCD_TEI(createDate, ticketEntryId);
	}

	/**
	* Returns the number of ticket attachments where createDate &lt; &#63; and ticketEntryId = &#63;.
	*
	* @param createDate the create date
	* @param ticketEntryId the ticket entry ID
	* @return the number of matching ticket attachments
	*/
	public static int countByCD_TEI(Date createDate, long ticketEntryId) {
		return getPersistence().countByCD_TEI(createDate, ticketEntryId);
	}

	/**
	* Returns all the ticket attachments where ticketEntryId = &#63; and ticketSolutionId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param ticketSolutionId the ticket solution ID
	* @return the matching ticket attachments
	*/
	public static List<TicketAttachment> findByTEI_TSI(long ticketEntryId,
		long ticketSolutionId) {
		return getPersistence().findByTEI_TSI(ticketEntryId, ticketSolutionId);
	}

	/**
	* Returns a range of all the ticket attachments where ticketEntryId = &#63; and ticketSolutionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param ticketSolutionId the ticket solution ID
	* @param start the lower bound of the range of ticket attachments
	* @param end the upper bound of the range of ticket attachments (not inclusive)
	* @return the range of matching ticket attachments
	*/
	public static List<TicketAttachment> findByTEI_TSI(long ticketEntryId,
		long ticketSolutionId, int start, int end) {
		return getPersistence()
				   .findByTEI_TSI(ticketEntryId, ticketSolutionId, start, end);
	}

	/**
	* Returns an ordered range of all the ticket attachments where ticketEntryId = &#63; and ticketSolutionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param ticketSolutionId the ticket solution ID
	* @param start the lower bound of the range of ticket attachments
	* @param end the upper bound of the range of ticket attachments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket attachments
	*/
	public static List<TicketAttachment> findByTEI_TSI(long ticketEntryId,
		long ticketSolutionId, int start, int end,
		OrderByComparator<TicketAttachment> orderByComparator) {
		return getPersistence()
				   .findByTEI_TSI(ticketEntryId, ticketSolutionId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the ticket attachments where ticketEntryId = &#63; and ticketSolutionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param ticketSolutionId the ticket solution ID
	* @param start the lower bound of the range of ticket attachments
	* @param end the upper bound of the range of ticket attachments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching ticket attachments
	*/
	public static List<TicketAttachment> findByTEI_TSI(long ticketEntryId,
		long ticketSolutionId, int start, int end,
		OrderByComparator<TicketAttachment> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByTEI_TSI(ticketEntryId, ticketSolutionId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first ticket attachment in the ordered set where ticketEntryId = &#63; and ticketSolutionId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param ticketSolutionId the ticket solution ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket attachment
	* @throws NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	*/
	public static TicketAttachment findByTEI_TSI_First(long ticketEntryId,
		long ticketSolutionId,
		OrderByComparator<TicketAttachment> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketAttachmentException {
		return getPersistence()
				   .findByTEI_TSI_First(ticketEntryId, ticketSolutionId,
			orderByComparator);
	}

	/**
	* Returns the first ticket attachment in the ordered set where ticketEntryId = &#63; and ticketSolutionId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param ticketSolutionId the ticket solution ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	*/
	public static TicketAttachment fetchByTEI_TSI_First(long ticketEntryId,
		long ticketSolutionId,
		OrderByComparator<TicketAttachment> orderByComparator) {
		return getPersistence()
				   .fetchByTEI_TSI_First(ticketEntryId, ticketSolutionId,
			orderByComparator);
	}

	/**
	* Returns the last ticket attachment in the ordered set where ticketEntryId = &#63; and ticketSolutionId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param ticketSolutionId the ticket solution ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket attachment
	* @throws NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	*/
	public static TicketAttachment findByTEI_TSI_Last(long ticketEntryId,
		long ticketSolutionId,
		OrderByComparator<TicketAttachment> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketAttachmentException {
		return getPersistence()
				   .findByTEI_TSI_Last(ticketEntryId, ticketSolutionId,
			orderByComparator);
	}

	/**
	* Returns the last ticket attachment in the ordered set where ticketEntryId = &#63; and ticketSolutionId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param ticketSolutionId the ticket solution ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	*/
	public static TicketAttachment fetchByTEI_TSI_Last(long ticketEntryId,
		long ticketSolutionId,
		OrderByComparator<TicketAttachment> orderByComparator) {
		return getPersistence()
				   .fetchByTEI_TSI_Last(ticketEntryId, ticketSolutionId,
			orderByComparator);
	}

	/**
	* Returns the ticket attachments before and after the current ticket attachment in the ordered set where ticketEntryId = &#63; and ticketSolutionId = &#63;.
	*
	* @param ticketAttachmentId the primary key of the current ticket attachment
	* @param ticketEntryId the ticket entry ID
	* @param ticketSolutionId the ticket solution ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ticket attachment
	* @throws NoSuchTicketAttachmentException if a ticket attachment with the primary key could not be found
	*/
	public static TicketAttachment[] findByTEI_TSI_PrevAndNext(
		long ticketAttachmentId, long ticketEntryId, long ticketSolutionId,
		OrderByComparator<TicketAttachment> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketAttachmentException {
		return getPersistence()
				   .findByTEI_TSI_PrevAndNext(ticketAttachmentId,
			ticketEntryId, ticketSolutionId, orderByComparator);
	}

	/**
	* Removes all the ticket attachments where ticketEntryId = &#63; and ticketSolutionId = &#63; from the database.
	*
	* @param ticketEntryId the ticket entry ID
	* @param ticketSolutionId the ticket solution ID
	*/
	public static void removeByTEI_TSI(long ticketEntryId, long ticketSolutionId) {
		getPersistence().removeByTEI_TSI(ticketEntryId, ticketSolutionId);
	}

	/**
	* Returns the number of ticket attachments where ticketEntryId = &#63; and ticketSolutionId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param ticketSolutionId the ticket solution ID
	* @return the number of matching ticket attachments
	*/
	public static int countByTEI_TSI(long ticketEntryId, long ticketSolutionId) {
		return getPersistence().countByTEI_TSI(ticketEntryId, ticketSolutionId);
	}

	/**
	* Returns all the ticket attachments where ticketEntryId = &#63; and status = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param status the status
	* @return the matching ticket attachments
	*/
	public static List<TicketAttachment> findByTEI_S(long ticketEntryId,
		int status) {
		return getPersistence().findByTEI_S(ticketEntryId, status);
	}

	/**
	* Returns a range of all the ticket attachments where ticketEntryId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param status the status
	* @param start the lower bound of the range of ticket attachments
	* @param end the upper bound of the range of ticket attachments (not inclusive)
	* @return the range of matching ticket attachments
	*/
	public static List<TicketAttachment> findByTEI_S(long ticketEntryId,
		int status, int start, int end) {
		return getPersistence().findByTEI_S(ticketEntryId, status, start, end);
	}

	/**
	* Returns an ordered range of all the ticket attachments where ticketEntryId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param status the status
	* @param start the lower bound of the range of ticket attachments
	* @param end the upper bound of the range of ticket attachments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket attachments
	*/
	public static List<TicketAttachment> findByTEI_S(long ticketEntryId,
		int status, int start, int end,
		OrderByComparator<TicketAttachment> orderByComparator) {
		return getPersistence()
				   .findByTEI_S(ticketEntryId, status, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the ticket attachments where ticketEntryId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param status the status
	* @param start the lower bound of the range of ticket attachments
	* @param end the upper bound of the range of ticket attachments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching ticket attachments
	*/
	public static List<TicketAttachment> findByTEI_S(long ticketEntryId,
		int status, int start, int end,
		OrderByComparator<TicketAttachment> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByTEI_S(ticketEntryId, status, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first ticket attachment in the ordered set where ticketEntryId = &#63; and status = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket attachment
	* @throws NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	*/
	public static TicketAttachment findByTEI_S_First(long ticketEntryId,
		int status, OrderByComparator<TicketAttachment> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketAttachmentException {
		return getPersistence()
				   .findByTEI_S_First(ticketEntryId, status, orderByComparator);
	}

	/**
	* Returns the first ticket attachment in the ordered set where ticketEntryId = &#63; and status = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	*/
	public static TicketAttachment fetchByTEI_S_First(long ticketEntryId,
		int status, OrderByComparator<TicketAttachment> orderByComparator) {
		return getPersistence()
				   .fetchByTEI_S_First(ticketEntryId, status, orderByComparator);
	}

	/**
	* Returns the last ticket attachment in the ordered set where ticketEntryId = &#63; and status = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket attachment
	* @throws NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	*/
	public static TicketAttachment findByTEI_S_Last(long ticketEntryId,
		int status, OrderByComparator<TicketAttachment> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketAttachmentException {
		return getPersistence()
				   .findByTEI_S_Last(ticketEntryId, status, orderByComparator);
	}

	/**
	* Returns the last ticket attachment in the ordered set where ticketEntryId = &#63; and status = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	*/
	public static TicketAttachment fetchByTEI_S_Last(long ticketEntryId,
		int status, OrderByComparator<TicketAttachment> orderByComparator) {
		return getPersistence()
				   .fetchByTEI_S_Last(ticketEntryId, status, orderByComparator);
	}

	/**
	* Returns the ticket attachments before and after the current ticket attachment in the ordered set where ticketEntryId = &#63; and status = &#63;.
	*
	* @param ticketAttachmentId the primary key of the current ticket attachment
	* @param ticketEntryId the ticket entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ticket attachment
	* @throws NoSuchTicketAttachmentException if a ticket attachment with the primary key could not be found
	*/
	public static TicketAttachment[] findByTEI_S_PrevAndNext(
		long ticketAttachmentId, long ticketEntryId, int status,
		OrderByComparator<TicketAttachment> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketAttachmentException {
		return getPersistence()
				   .findByTEI_S_PrevAndNext(ticketAttachmentId, ticketEntryId,
			status, orderByComparator);
	}

	/**
	* Removes all the ticket attachments where ticketEntryId = &#63; and status = &#63; from the database.
	*
	* @param ticketEntryId the ticket entry ID
	* @param status the status
	*/
	public static void removeByTEI_S(long ticketEntryId, int status) {
		getPersistence().removeByTEI_S(ticketEntryId, status);
	}

	/**
	* Returns the number of ticket attachments where ticketEntryId = &#63; and status = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param status the status
	* @return the number of matching ticket attachments
	*/
	public static int countByTEI_S(long ticketEntryId, int status) {
		return getPersistence().countByTEI_S(ticketEntryId, status);
	}

	/**
	* Returns all the ticket attachments where type = &#63; and deleteDate &lt; &#63;.
	*
	* @param type the type
	* @param deleteDate the delete date
	* @return the matching ticket attachments
	*/
	public static List<TicketAttachment> findByT_DD(int type, Date deleteDate) {
		return getPersistence().findByT_DD(type, deleteDate);
	}

	/**
	* Returns a range of all the ticket attachments where type = &#63; and deleteDate &lt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param type the type
	* @param deleteDate the delete date
	* @param start the lower bound of the range of ticket attachments
	* @param end the upper bound of the range of ticket attachments (not inclusive)
	* @return the range of matching ticket attachments
	*/
	public static List<TicketAttachment> findByT_DD(int type, Date deleteDate,
		int start, int end) {
		return getPersistence().findByT_DD(type, deleteDate, start, end);
	}

	/**
	* Returns an ordered range of all the ticket attachments where type = &#63; and deleteDate &lt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param type the type
	* @param deleteDate the delete date
	* @param start the lower bound of the range of ticket attachments
	* @param end the upper bound of the range of ticket attachments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket attachments
	*/
	public static List<TicketAttachment> findByT_DD(int type, Date deleteDate,
		int start, int end,
		OrderByComparator<TicketAttachment> orderByComparator) {
		return getPersistence()
				   .findByT_DD(type, deleteDate, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the ticket attachments where type = &#63; and deleteDate &lt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param type the type
	* @param deleteDate the delete date
	* @param start the lower bound of the range of ticket attachments
	* @param end the upper bound of the range of ticket attachments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching ticket attachments
	*/
	public static List<TicketAttachment> findByT_DD(int type, Date deleteDate,
		int start, int end,
		OrderByComparator<TicketAttachment> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByT_DD(type, deleteDate, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first ticket attachment in the ordered set where type = &#63; and deleteDate &lt; &#63;.
	*
	* @param type the type
	* @param deleteDate the delete date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket attachment
	* @throws NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	*/
	public static TicketAttachment findByT_DD_First(int type, Date deleteDate,
		OrderByComparator<TicketAttachment> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketAttachmentException {
		return getPersistence()
				   .findByT_DD_First(type, deleteDate, orderByComparator);
	}

	/**
	* Returns the first ticket attachment in the ordered set where type = &#63; and deleteDate &lt; &#63;.
	*
	* @param type the type
	* @param deleteDate the delete date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	*/
	public static TicketAttachment fetchByT_DD_First(int type, Date deleteDate,
		OrderByComparator<TicketAttachment> orderByComparator) {
		return getPersistence()
				   .fetchByT_DD_First(type, deleteDate, orderByComparator);
	}

	/**
	* Returns the last ticket attachment in the ordered set where type = &#63; and deleteDate &lt; &#63;.
	*
	* @param type the type
	* @param deleteDate the delete date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket attachment
	* @throws NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	*/
	public static TicketAttachment findByT_DD_Last(int type, Date deleteDate,
		OrderByComparator<TicketAttachment> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketAttachmentException {
		return getPersistence()
				   .findByT_DD_Last(type, deleteDate, orderByComparator);
	}

	/**
	* Returns the last ticket attachment in the ordered set where type = &#63; and deleteDate &lt; &#63;.
	*
	* @param type the type
	* @param deleteDate the delete date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	*/
	public static TicketAttachment fetchByT_DD_Last(int type, Date deleteDate,
		OrderByComparator<TicketAttachment> orderByComparator) {
		return getPersistence()
				   .fetchByT_DD_Last(type, deleteDate, orderByComparator);
	}

	/**
	* Returns the ticket attachments before and after the current ticket attachment in the ordered set where type = &#63; and deleteDate &lt; &#63;.
	*
	* @param ticketAttachmentId the primary key of the current ticket attachment
	* @param type the type
	* @param deleteDate the delete date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ticket attachment
	* @throws NoSuchTicketAttachmentException if a ticket attachment with the primary key could not be found
	*/
	public static TicketAttachment[] findByT_DD_PrevAndNext(
		long ticketAttachmentId, int type, Date deleteDate,
		OrderByComparator<TicketAttachment> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketAttachmentException {
		return getPersistence()
				   .findByT_DD_PrevAndNext(ticketAttachmentId, type,
			deleteDate, orderByComparator);
	}

	/**
	* Returns all the ticket attachments where type = any &#63; and deleteDate &lt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param types the types
	* @param deleteDate the delete date
	* @return the matching ticket attachments
	*/
	public static List<TicketAttachment> findByT_DD(int[] types, Date deleteDate) {
		return getPersistence().findByT_DD(types, deleteDate);
	}

	/**
	* Returns a range of all the ticket attachments where type = any &#63; and deleteDate &lt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param types the types
	* @param deleteDate the delete date
	* @param start the lower bound of the range of ticket attachments
	* @param end the upper bound of the range of ticket attachments (not inclusive)
	* @return the range of matching ticket attachments
	*/
	public static List<TicketAttachment> findByT_DD(int[] types,
		Date deleteDate, int start, int end) {
		return getPersistence().findByT_DD(types, deleteDate, start, end);
	}

	/**
	* Returns an ordered range of all the ticket attachments where type = any &#63; and deleteDate &lt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param types the types
	* @param deleteDate the delete date
	* @param start the lower bound of the range of ticket attachments
	* @param end the upper bound of the range of ticket attachments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket attachments
	*/
	public static List<TicketAttachment> findByT_DD(int[] types,
		Date deleteDate, int start, int end,
		OrderByComparator<TicketAttachment> orderByComparator) {
		return getPersistence()
				   .findByT_DD(types, deleteDate, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the ticket attachments where type = &#63; and deleteDate &lt; &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param type the type
	* @param deleteDate the delete date
	* @param start the lower bound of the range of ticket attachments
	* @param end the upper bound of the range of ticket attachments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching ticket attachments
	*/
	public static List<TicketAttachment> findByT_DD(int[] types,
		Date deleteDate, int start, int end,
		OrderByComparator<TicketAttachment> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByT_DD(types, deleteDate, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the ticket attachments where type = &#63; and deleteDate &lt; &#63; from the database.
	*
	* @param type the type
	* @param deleteDate the delete date
	*/
	public static void removeByT_DD(int type, Date deleteDate) {
		getPersistence().removeByT_DD(type, deleteDate);
	}

	/**
	* Returns the number of ticket attachments where type = &#63; and deleteDate &lt; &#63;.
	*
	* @param type the type
	* @param deleteDate the delete date
	* @return the number of matching ticket attachments
	*/
	public static int countByT_DD(int type, Date deleteDate) {
		return getPersistence().countByT_DD(type, deleteDate);
	}

	/**
	* Returns the number of ticket attachments where type = any &#63; and deleteDate &lt; &#63;.
	*
	* @param types the types
	* @param deleteDate the delete date
	* @return the number of matching ticket attachments
	*/
	public static int countByT_DD(int[] types, Date deleteDate) {
		return getPersistence().countByT_DD(types, deleteDate);
	}

	/**
	* Returns all the ticket attachments where ticketEntryId = &#63; and type = &#63; and status = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param status the status
	* @return the matching ticket attachments
	*/
	public static List<TicketAttachment> findByTEI_T_S(long ticketEntryId,
		int type, int status) {
		return getPersistence().findByTEI_T_S(ticketEntryId, type, status);
	}

	/**
	* Returns a range of all the ticket attachments where ticketEntryId = &#63; and type = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param status the status
	* @param start the lower bound of the range of ticket attachments
	* @param end the upper bound of the range of ticket attachments (not inclusive)
	* @return the range of matching ticket attachments
	*/
	public static List<TicketAttachment> findByTEI_T_S(long ticketEntryId,
		int type, int status, int start, int end) {
		return getPersistence()
				   .findByTEI_T_S(ticketEntryId, type, status, start, end);
	}

	/**
	* Returns an ordered range of all the ticket attachments where ticketEntryId = &#63; and type = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param status the status
	* @param start the lower bound of the range of ticket attachments
	* @param end the upper bound of the range of ticket attachments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket attachments
	*/
	public static List<TicketAttachment> findByTEI_T_S(long ticketEntryId,
		int type, int status, int start, int end,
		OrderByComparator<TicketAttachment> orderByComparator) {
		return getPersistence()
				   .findByTEI_T_S(ticketEntryId, type, status, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the ticket attachments where ticketEntryId = &#63; and type = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param status the status
	* @param start the lower bound of the range of ticket attachments
	* @param end the upper bound of the range of ticket attachments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching ticket attachments
	*/
	public static List<TicketAttachment> findByTEI_T_S(long ticketEntryId,
		int type, int status, int start, int end,
		OrderByComparator<TicketAttachment> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByTEI_T_S(ticketEntryId, type, status, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first ticket attachment in the ordered set where ticketEntryId = &#63; and type = &#63; and status = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket attachment
	* @throws NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	*/
	public static TicketAttachment findByTEI_T_S_First(long ticketEntryId,
		int type, int status,
		OrderByComparator<TicketAttachment> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketAttachmentException {
		return getPersistence()
				   .findByTEI_T_S_First(ticketEntryId, type, status,
			orderByComparator);
	}

	/**
	* Returns the first ticket attachment in the ordered set where ticketEntryId = &#63; and type = &#63; and status = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	*/
	public static TicketAttachment fetchByTEI_T_S_First(long ticketEntryId,
		int type, int status,
		OrderByComparator<TicketAttachment> orderByComparator) {
		return getPersistence()
				   .fetchByTEI_T_S_First(ticketEntryId, type, status,
			orderByComparator);
	}

	/**
	* Returns the last ticket attachment in the ordered set where ticketEntryId = &#63; and type = &#63; and status = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket attachment
	* @throws NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	*/
	public static TicketAttachment findByTEI_T_S_Last(long ticketEntryId,
		int type, int status,
		OrderByComparator<TicketAttachment> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketAttachmentException {
		return getPersistence()
				   .findByTEI_T_S_Last(ticketEntryId, type, status,
			orderByComparator);
	}

	/**
	* Returns the last ticket attachment in the ordered set where ticketEntryId = &#63; and type = &#63; and status = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	*/
	public static TicketAttachment fetchByTEI_T_S_Last(long ticketEntryId,
		int type, int status,
		OrderByComparator<TicketAttachment> orderByComparator) {
		return getPersistence()
				   .fetchByTEI_T_S_Last(ticketEntryId, type, status,
			orderByComparator);
	}

	/**
	* Returns the ticket attachments before and after the current ticket attachment in the ordered set where ticketEntryId = &#63; and type = &#63; and status = &#63;.
	*
	* @param ticketAttachmentId the primary key of the current ticket attachment
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ticket attachment
	* @throws NoSuchTicketAttachmentException if a ticket attachment with the primary key could not be found
	*/
	public static TicketAttachment[] findByTEI_T_S_PrevAndNext(
		long ticketAttachmentId, long ticketEntryId, int type, int status,
		OrderByComparator<TicketAttachment> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketAttachmentException {
		return getPersistence()
				   .findByTEI_T_S_PrevAndNext(ticketAttachmentId,
			ticketEntryId, type, status, orderByComparator);
	}

	/**
	* Removes all the ticket attachments where ticketEntryId = &#63; and type = &#63; and status = &#63; from the database.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param status the status
	*/
	public static void removeByTEI_T_S(long ticketEntryId, int type, int status) {
		getPersistence().removeByTEI_T_S(ticketEntryId, type, status);
	}

	/**
	* Returns the number of ticket attachments where ticketEntryId = &#63; and type = &#63; and status = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param status the status
	* @return the number of matching ticket attachments
	*/
	public static int countByTEI_T_S(long ticketEntryId, int type, int status) {
		return getPersistence().countByTEI_T_S(ticketEntryId, type, status);
	}

	/**
	* Returns all the ticket attachments where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	*
	* @param userId the user ID
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param status the status
	* @return the matching ticket attachments
	*/
	public static List<TicketAttachment> findByU_TEI_V_S(long userId,
		long ticketEntryId, int visibility, int status) {
		return getPersistence()
				   .findByU_TEI_V_S(userId, ticketEntryId, visibility, status);
	}

	/**
	* Returns a range of all the ticket attachments where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param status the status
	* @param start the lower bound of the range of ticket attachments
	* @param end the upper bound of the range of ticket attachments (not inclusive)
	* @return the range of matching ticket attachments
	*/
	public static List<TicketAttachment> findByU_TEI_V_S(long userId,
		long ticketEntryId, int visibility, int status, int start, int end) {
		return getPersistence()
				   .findByU_TEI_V_S(userId, ticketEntryId, visibility, status,
			start, end);
	}

	/**
	* Returns an ordered range of all the ticket attachments where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param status the status
	* @param start the lower bound of the range of ticket attachments
	* @param end the upper bound of the range of ticket attachments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket attachments
	*/
	public static List<TicketAttachment> findByU_TEI_V_S(long userId,
		long ticketEntryId, int visibility, int status, int start, int end,
		OrderByComparator<TicketAttachment> orderByComparator) {
		return getPersistence()
				   .findByU_TEI_V_S(userId, ticketEntryId, visibility, status,
			start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the ticket attachments where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param status the status
	* @param start the lower bound of the range of ticket attachments
	* @param end the upper bound of the range of ticket attachments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching ticket attachments
	*/
	public static List<TicketAttachment> findByU_TEI_V_S(long userId,
		long ticketEntryId, int visibility, int status, int start, int end,
		OrderByComparator<TicketAttachment> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByU_TEI_V_S(userId, ticketEntryId, visibility, status,
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first ticket attachment in the ordered set where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	*
	* @param userId the user ID
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket attachment
	* @throws NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	*/
	public static TicketAttachment findByU_TEI_V_S_First(long userId,
		long ticketEntryId, int visibility, int status,
		OrderByComparator<TicketAttachment> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketAttachmentException {
		return getPersistence()
				   .findByU_TEI_V_S_First(userId, ticketEntryId, visibility,
			status, orderByComparator);
	}

	/**
	* Returns the first ticket attachment in the ordered set where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	*
	* @param userId the user ID
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	*/
	public static TicketAttachment fetchByU_TEI_V_S_First(long userId,
		long ticketEntryId, int visibility, int status,
		OrderByComparator<TicketAttachment> orderByComparator) {
		return getPersistence()
				   .fetchByU_TEI_V_S_First(userId, ticketEntryId, visibility,
			status, orderByComparator);
	}

	/**
	* Returns the last ticket attachment in the ordered set where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	*
	* @param userId the user ID
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket attachment
	* @throws NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	*/
	public static TicketAttachment findByU_TEI_V_S_Last(long userId,
		long ticketEntryId, int visibility, int status,
		OrderByComparator<TicketAttachment> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketAttachmentException {
		return getPersistence()
				   .findByU_TEI_V_S_Last(userId, ticketEntryId, visibility,
			status, orderByComparator);
	}

	/**
	* Returns the last ticket attachment in the ordered set where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	*
	* @param userId the user ID
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	*/
	public static TicketAttachment fetchByU_TEI_V_S_Last(long userId,
		long ticketEntryId, int visibility, int status,
		OrderByComparator<TicketAttachment> orderByComparator) {
		return getPersistence()
				   .fetchByU_TEI_V_S_Last(userId, ticketEntryId, visibility,
			status, orderByComparator);
	}

	/**
	* Returns the ticket attachments before and after the current ticket attachment in the ordered set where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	*
	* @param ticketAttachmentId the primary key of the current ticket attachment
	* @param userId the user ID
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ticket attachment
	* @throws NoSuchTicketAttachmentException if a ticket attachment with the primary key could not be found
	*/
	public static TicketAttachment[] findByU_TEI_V_S_PrevAndNext(
		long ticketAttachmentId, long userId, long ticketEntryId,
		int visibility, int status,
		OrderByComparator<TicketAttachment> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketAttachmentException {
		return getPersistence()
				   .findByU_TEI_V_S_PrevAndNext(ticketAttachmentId, userId,
			ticketEntryId, visibility, status, orderByComparator);
	}

	/**
	* Removes all the ticket attachments where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63; from the database.
	*
	* @param userId the user ID
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param status the status
	*/
	public static void removeByU_TEI_V_S(long userId, long ticketEntryId,
		int visibility, int status) {
		getPersistence()
			.removeByU_TEI_V_S(userId, ticketEntryId, visibility, status);
	}

	/**
	* Returns the number of ticket attachments where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	*
	* @param userId the user ID
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param status the status
	* @return the number of matching ticket attachments
	*/
	public static int countByU_TEI_V_S(long userId, long ticketEntryId,
		int visibility, int status) {
		return getPersistence()
				   .countByU_TEI_V_S(userId, ticketEntryId, visibility, status);
	}

	/**
	* Returns the ticket attachment where ticketEntryId = &#63; and fileName = &#63; and visibility = &#63; and status = &#63; or throws a {@link NoSuchTicketAttachmentException} if it could not be found.
	*
	* @param ticketEntryId the ticket entry ID
	* @param fileName the file name
	* @param visibility the visibility
	* @param status the status
	* @return the matching ticket attachment
	* @throws NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	*/
	public static TicketAttachment findByTEI_FN_V_S(long ticketEntryId,
		java.lang.String fileName, int visibility, int status)
		throws com.liferay.osb.exception.NoSuchTicketAttachmentException {
		return getPersistence()
				   .findByTEI_FN_V_S(ticketEntryId, fileName, visibility, status);
	}

	/**
	* Returns the ticket attachment where ticketEntryId = &#63; and fileName = &#63; and visibility = &#63; and status = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param ticketEntryId the ticket entry ID
	* @param fileName the file name
	* @param visibility the visibility
	* @param status the status
	* @return the matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	*/
	public static TicketAttachment fetchByTEI_FN_V_S(long ticketEntryId,
		java.lang.String fileName, int visibility, int status) {
		return getPersistence()
				   .fetchByTEI_FN_V_S(ticketEntryId, fileName, visibility,
			status);
	}

	/**
	* Returns the ticket attachment where ticketEntryId = &#63; and fileName = &#63; and visibility = &#63; and status = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param ticketEntryId the ticket entry ID
	* @param fileName the file name
	* @param visibility the visibility
	* @param status the status
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	*/
	public static TicketAttachment fetchByTEI_FN_V_S(long ticketEntryId,
		java.lang.String fileName, int visibility, int status,
		boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByTEI_FN_V_S(ticketEntryId, fileName, visibility,
			status, retrieveFromCache);
	}

	/**
	* Removes the ticket attachment where ticketEntryId = &#63; and fileName = &#63; and visibility = &#63; and status = &#63; from the database.
	*
	* @param ticketEntryId the ticket entry ID
	* @param fileName the file name
	* @param visibility the visibility
	* @param status the status
	* @return the ticket attachment that was removed
	*/
	public static TicketAttachment removeByTEI_FN_V_S(long ticketEntryId,
		java.lang.String fileName, int visibility, int status)
		throws com.liferay.osb.exception.NoSuchTicketAttachmentException {
		return getPersistence()
				   .removeByTEI_FN_V_S(ticketEntryId, fileName, visibility,
			status);
	}

	/**
	* Returns the number of ticket attachments where ticketEntryId = &#63; and fileName = &#63; and visibility = &#63; and status = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param fileName the file name
	* @param visibility the visibility
	* @param status the status
	* @return the number of matching ticket attachments
	*/
	public static int countByTEI_FN_V_S(long ticketEntryId,
		java.lang.String fileName, int visibility, int status) {
		return getPersistence()
				   .countByTEI_FN_V_S(ticketEntryId, fileName, visibility,
			status);
	}

	/**
	* Returns all the ticket attachments where ticketEntryId = &#63; and type = &#63; and visibility = &#63; and status = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param visibility the visibility
	* @param status the status
	* @return the matching ticket attachments
	*/
	public static List<TicketAttachment> findByTEI_T_V_S(long ticketEntryId,
		int type, int visibility, int status) {
		return getPersistence()
				   .findByTEI_T_V_S(ticketEntryId, type, visibility, status);
	}

	/**
	* Returns a range of all the ticket attachments where ticketEntryId = &#63; and type = &#63; and visibility = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param visibility the visibility
	* @param status the status
	* @param start the lower bound of the range of ticket attachments
	* @param end the upper bound of the range of ticket attachments (not inclusive)
	* @return the range of matching ticket attachments
	*/
	public static List<TicketAttachment> findByTEI_T_V_S(long ticketEntryId,
		int type, int visibility, int status, int start, int end) {
		return getPersistence()
				   .findByTEI_T_V_S(ticketEntryId, type, visibility, status,
			start, end);
	}

	/**
	* Returns an ordered range of all the ticket attachments where ticketEntryId = &#63; and type = &#63; and visibility = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param visibility the visibility
	* @param status the status
	* @param start the lower bound of the range of ticket attachments
	* @param end the upper bound of the range of ticket attachments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket attachments
	*/
	public static List<TicketAttachment> findByTEI_T_V_S(long ticketEntryId,
		int type, int visibility, int status, int start, int end,
		OrderByComparator<TicketAttachment> orderByComparator) {
		return getPersistence()
				   .findByTEI_T_V_S(ticketEntryId, type, visibility, status,
			start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the ticket attachments where ticketEntryId = &#63; and type = &#63; and visibility = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param visibility the visibility
	* @param status the status
	* @param start the lower bound of the range of ticket attachments
	* @param end the upper bound of the range of ticket attachments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching ticket attachments
	*/
	public static List<TicketAttachment> findByTEI_T_V_S(long ticketEntryId,
		int type, int visibility, int status, int start, int end,
		OrderByComparator<TicketAttachment> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByTEI_T_V_S(ticketEntryId, type, visibility, status,
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first ticket attachment in the ordered set where ticketEntryId = &#63; and type = &#63; and visibility = &#63; and status = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param visibility the visibility
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket attachment
	* @throws NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	*/
	public static TicketAttachment findByTEI_T_V_S_First(long ticketEntryId,
		int type, int visibility, int status,
		OrderByComparator<TicketAttachment> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketAttachmentException {
		return getPersistence()
				   .findByTEI_T_V_S_First(ticketEntryId, type, visibility,
			status, orderByComparator);
	}

	/**
	* Returns the first ticket attachment in the ordered set where ticketEntryId = &#63; and type = &#63; and visibility = &#63; and status = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param visibility the visibility
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	*/
	public static TicketAttachment fetchByTEI_T_V_S_First(long ticketEntryId,
		int type, int visibility, int status,
		OrderByComparator<TicketAttachment> orderByComparator) {
		return getPersistence()
				   .fetchByTEI_T_V_S_First(ticketEntryId, type, visibility,
			status, orderByComparator);
	}

	/**
	* Returns the last ticket attachment in the ordered set where ticketEntryId = &#63; and type = &#63; and visibility = &#63; and status = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param visibility the visibility
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket attachment
	* @throws NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	*/
	public static TicketAttachment findByTEI_T_V_S_Last(long ticketEntryId,
		int type, int visibility, int status,
		OrderByComparator<TicketAttachment> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketAttachmentException {
		return getPersistence()
				   .findByTEI_T_V_S_Last(ticketEntryId, type, visibility,
			status, orderByComparator);
	}

	/**
	* Returns the last ticket attachment in the ordered set where ticketEntryId = &#63; and type = &#63; and visibility = &#63; and status = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param visibility the visibility
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	*/
	public static TicketAttachment fetchByTEI_T_V_S_Last(long ticketEntryId,
		int type, int visibility, int status,
		OrderByComparator<TicketAttachment> orderByComparator) {
		return getPersistence()
				   .fetchByTEI_T_V_S_Last(ticketEntryId, type, visibility,
			status, orderByComparator);
	}

	/**
	* Returns the ticket attachments before and after the current ticket attachment in the ordered set where ticketEntryId = &#63; and type = &#63; and visibility = &#63; and status = &#63;.
	*
	* @param ticketAttachmentId the primary key of the current ticket attachment
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param visibility the visibility
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ticket attachment
	* @throws NoSuchTicketAttachmentException if a ticket attachment with the primary key could not be found
	*/
	public static TicketAttachment[] findByTEI_T_V_S_PrevAndNext(
		long ticketAttachmentId, long ticketEntryId, int type, int visibility,
		int status, OrderByComparator<TicketAttachment> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketAttachmentException {
		return getPersistence()
				   .findByTEI_T_V_S_PrevAndNext(ticketAttachmentId,
			ticketEntryId, type, visibility, status, orderByComparator);
	}

	/**
	* Returns all the ticket attachments where ticketEntryId = &#63; and type = any &#63; and visibility = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param types the types
	* @param visibilities the visibilities
	* @param status the status
	* @return the matching ticket attachments
	*/
	public static List<TicketAttachment> findByTEI_T_V_S(long ticketEntryId,
		int[] types, int[] visibilities, int status) {
		return getPersistence()
				   .findByTEI_T_V_S(ticketEntryId, types, visibilities, status);
	}

	/**
	* Returns a range of all the ticket attachments where ticketEntryId = &#63; and type = any &#63; and visibility = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param types the types
	* @param visibilities the visibilities
	* @param status the status
	* @param start the lower bound of the range of ticket attachments
	* @param end the upper bound of the range of ticket attachments (not inclusive)
	* @return the range of matching ticket attachments
	*/
	public static List<TicketAttachment> findByTEI_T_V_S(long ticketEntryId,
		int[] types, int[] visibilities, int status, int start, int end) {
		return getPersistence()
				   .findByTEI_T_V_S(ticketEntryId, types, visibilities, status,
			start, end);
	}

	/**
	* Returns an ordered range of all the ticket attachments where ticketEntryId = &#63; and type = any &#63; and visibility = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param types the types
	* @param visibilities the visibilities
	* @param status the status
	* @param start the lower bound of the range of ticket attachments
	* @param end the upper bound of the range of ticket attachments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket attachments
	*/
	public static List<TicketAttachment> findByTEI_T_V_S(long ticketEntryId,
		int[] types, int[] visibilities, int status, int start, int end,
		OrderByComparator<TicketAttachment> orderByComparator) {
		return getPersistence()
				   .findByTEI_T_V_S(ticketEntryId, types, visibilities, status,
			start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the ticket attachments where ticketEntryId = &#63; and type = &#63; and visibility = &#63; and status = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param visibility the visibility
	* @param status the status
	* @param start the lower bound of the range of ticket attachments
	* @param end the upper bound of the range of ticket attachments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching ticket attachments
	*/
	public static List<TicketAttachment> findByTEI_T_V_S(long ticketEntryId,
		int[] types, int[] visibilities, int status, int start, int end,
		OrderByComparator<TicketAttachment> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByTEI_T_V_S(ticketEntryId, types, visibilities, status,
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the ticket attachments where ticketEntryId = &#63; and type = &#63; and visibility = &#63; and status = &#63; from the database.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param visibility the visibility
	* @param status the status
	*/
	public static void removeByTEI_T_V_S(long ticketEntryId, int type,
		int visibility, int status) {
		getPersistence()
			.removeByTEI_T_V_S(ticketEntryId, type, visibility, status);
	}

	/**
	* Returns the number of ticket attachments where ticketEntryId = &#63; and type = &#63; and visibility = &#63; and status = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param visibility the visibility
	* @param status the status
	* @return the number of matching ticket attachments
	*/
	public static int countByTEI_T_V_S(long ticketEntryId, int type,
		int visibility, int status) {
		return getPersistence()
				   .countByTEI_T_V_S(ticketEntryId, type, visibility, status);
	}

	/**
	* Returns the number of ticket attachments where ticketEntryId = &#63; and type = any &#63; and visibility = any &#63; and status = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param types the types
	* @param visibilities the visibilities
	* @param status the status
	* @return the number of matching ticket attachments
	*/
	public static int countByTEI_T_V_S(long ticketEntryId, int[] types,
		int[] visibilities, int status) {
		return getPersistence()
				   .countByTEI_T_V_S(ticketEntryId, types, visibilities, status);
	}

	/**
	* Caches the ticket attachment in the entity cache if it is enabled.
	*
	* @param ticketAttachment the ticket attachment
	*/
	public static void cacheResult(TicketAttachment ticketAttachment) {
		getPersistence().cacheResult(ticketAttachment);
	}

	/**
	* Caches the ticket attachments in the entity cache if it is enabled.
	*
	* @param ticketAttachments the ticket attachments
	*/
	public static void cacheResult(List<TicketAttachment> ticketAttachments) {
		getPersistence().cacheResult(ticketAttachments);
	}

	/**
	* Creates a new ticket attachment with the primary key. Does not add the ticket attachment to the database.
	*
	* @param ticketAttachmentId the primary key for the new ticket attachment
	* @return the new ticket attachment
	*/
	public static TicketAttachment create(long ticketAttachmentId) {
		return getPersistence().create(ticketAttachmentId);
	}

	/**
	* Removes the ticket attachment with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketAttachmentId the primary key of the ticket attachment
	* @return the ticket attachment that was removed
	* @throws NoSuchTicketAttachmentException if a ticket attachment with the primary key could not be found
	*/
	public static TicketAttachment remove(long ticketAttachmentId)
		throws com.liferay.osb.exception.NoSuchTicketAttachmentException {
		return getPersistence().remove(ticketAttachmentId);
	}

	public static TicketAttachment updateImpl(TicketAttachment ticketAttachment) {
		return getPersistence().updateImpl(ticketAttachment);
	}

	/**
	* Returns the ticket attachment with the primary key or throws a {@link NoSuchTicketAttachmentException} if it could not be found.
	*
	* @param ticketAttachmentId the primary key of the ticket attachment
	* @return the ticket attachment
	* @throws NoSuchTicketAttachmentException if a ticket attachment with the primary key could not be found
	*/
	public static TicketAttachment findByPrimaryKey(long ticketAttachmentId)
		throws com.liferay.osb.exception.NoSuchTicketAttachmentException {
		return getPersistence().findByPrimaryKey(ticketAttachmentId);
	}

	/**
	* Returns the ticket attachment with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ticketAttachmentId the primary key of the ticket attachment
	* @return the ticket attachment, or <code>null</code> if a ticket attachment with the primary key could not be found
	*/
	public static TicketAttachment fetchByPrimaryKey(long ticketAttachmentId) {
		return getPersistence().fetchByPrimaryKey(ticketAttachmentId);
	}

	public static java.util.Map<java.io.Serializable, TicketAttachment> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the ticket attachments.
	*
	* @return the ticket attachments
	*/
	public static List<TicketAttachment> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the ticket attachments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ticket attachments
	* @param end the upper bound of the range of ticket attachments (not inclusive)
	* @return the range of ticket attachments
	*/
	public static List<TicketAttachment> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the ticket attachments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ticket attachments
	* @param end the upper bound of the range of ticket attachments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ticket attachments
	*/
	public static List<TicketAttachment> findAll(int start, int end,
		OrderByComparator<TicketAttachment> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the ticket attachments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ticket attachments
	* @param end the upper bound of the range of ticket attachments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ticket attachments
	*/
	public static List<TicketAttachment> findAll(int start, int end,
		OrderByComparator<TicketAttachment> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the ticket attachments from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of ticket attachments.
	*
	* @return the number of ticket attachments
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static TicketAttachmentPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (TicketAttachmentPersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					TicketAttachmentPersistence.class.getName());

			ReferenceRegistry.registerReference(TicketAttachmentUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	private static TicketAttachmentPersistence _persistence;
}