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

import com.liferay.osb.model.TicketAttachment;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the ticket attachment service. This utility wraps {@link TicketAttachmentPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TicketAttachmentPersistence
 * @see TicketAttachmentPersistenceImpl
 * @generated
 */
public class TicketAttachmentUtil {
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
	public static void clearCache(TicketAttachment ticketAttachment) {
		getPersistence().clearCache(ticketAttachment);
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
	public static List<TicketAttachment> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<TicketAttachment> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<TicketAttachment> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static TicketAttachment update(TicketAttachment ticketAttachment,
		boolean merge) throws SystemException {
		return getPersistence().update(ticketAttachment, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static TicketAttachment update(TicketAttachment ticketAttachment,
		boolean merge, ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(ticketAttachment, merge, serviceContext);
	}

	/**
	* Caches the ticket attachment in the entity cache if it is enabled.
	*
	* @param ticketAttachment the ticket attachment
	*/
	public static void cacheResult(
		com.liferay.osb.model.TicketAttachment ticketAttachment) {
		getPersistence().cacheResult(ticketAttachment);
	}

	/**
	* Caches the ticket attachments in the entity cache if it is enabled.
	*
	* @param ticketAttachments the ticket attachments
	*/
	public static void cacheResult(
		java.util.List<com.liferay.osb.model.TicketAttachment> ticketAttachments) {
		getPersistence().cacheResult(ticketAttachments);
	}

	/**
	* Creates a new ticket attachment with the primary key. Does not add the ticket attachment to the database.
	*
	* @param ticketAttachmentId the primary key for the new ticket attachment
	* @return the new ticket attachment
	*/
	public static com.liferay.osb.model.TicketAttachment create(
		long ticketAttachmentId) {
		return getPersistence().create(ticketAttachmentId);
	}

	/**
	* Removes the ticket attachment with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketAttachmentId the primary key of the ticket attachment
	* @return the ticket attachment that was removed
	* @throws com.liferay.osb.NoSuchTicketAttachmentException if a ticket attachment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketAttachment remove(
		long ticketAttachmentId)
		throws com.liferay.osb.NoSuchTicketAttachmentException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(ticketAttachmentId);
	}

	public static com.liferay.osb.model.TicketAttachment updateImpl(
		com.liferay.osb.model.TicketAttachment ticketAttachment, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(ticketAttachment, merge);
	}

	/**
	* Returns the ticket attachment with the primary key or throws a {@link com.liferay.osb.NoSuchTicketAttachmentException} if it could not be found.
	*
	* @param ticketAttachmentId the primary key of the ticket attachment
	* @return the ticket attachment
	* @throws com.liferay.osb.NoSuchTicketAttachmentException if a ticket attachment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketAttachment findByPrimaryKey(
		long ticketAttachmentId)
		throws com.liferay.osb.NoSuchTicketAttachmentException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(ticketAttachmentId);
	}

	/**
	* Returns the ticket attachment with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ticketAttachmentId the primary key of the ticket attachment
	* @return the ticket attachment, or <code>null</code> if a ticket attachment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketAttachment fetchByPrimaryKey(
		long ticketAttachmentId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(ticketAttachmentId);
	}

	/**
	* Returns all the ticket attachments where type = &#63;.
	*
	* @param type the type
	* @return the matching ticket attachments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TicketAttachment> findByType(
		int type) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByType(type);
	}

	/**
	* Returns a range of all the ticket attachments where type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param type the type
	* @param start the lower bound of the range of ticket attachments
	* @param end the upper bound of the range of ticket attachments (not inclusive)
	* @return the range of matching ticket attachments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TicketAttachment> findByType(
		int type, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByType(type, start, end);
	}

	/**
	* Returns an ordered range of all the ticket attachments where type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param type the type
	* @param start the lower bound of the range of ticket attachments
	* @param end the upper bound of the range of ticket attachments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket attachments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TicketAttachment> findByType(
		int type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByType(type, start, end, orderByComparator);
	}

	/**
	* Returns the first ticket attachment in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket attachment
	* @throws com.liferay.osb.NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketAttachment findByType_First(
		int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketAttachmentException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByType_First(type, orderByComparator);
	}

	/**
	* Returns the first ticket attachment in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketAttachment fetchByType_First(
		int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByType_First(type, orderByComparator);
	}

	/**
	* Returns the last ticket attachment in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket attachment
	* @throws com.liferay.osb.NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketAttachment findByType_Last(
		int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketAttachmentException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByType_Last(type, orderByComparator);
	}

	/**
	* Returns the last ticket attachment in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketAttachment fetchByType_Last(
		int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByType_Last(type, orderByComparator);
	}

	/**
	* Returns the ticket attachments before and after the current ticket attachment in the ordered set where type = &#63;.
	*
	* @param ticketAttachmentId the primary key of the current ticket attachment
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ticket attachment
	* @throws com.liferay.osb.NoSuchTicketAttachmentException if a ticket attachment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketAttachment[] findByType_PrevAndNext(
		long ticketAttachmentId, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketAttachmentException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByType_PrevAndNext(ticketAttachmentId, type,
			orderByComparator);
	}

	/**
	* Returns all the ticket attachments where type = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param types the types
	* @return the matching ticket attachments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TicketAttachment> findByType(
		int[] types) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByType(types);
	}

	/**
	* Returns a range of all the ticket attachments where type = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param types the types
	* @param start the lower bound of the range of ticket attachments
	* @param end the upper bound of the range of ticket attachments (not inclusive)
	* @return the range of matching ticket attachments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TicketAttachment> findByType(
		int[] types, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByType(types, start, end);
	}

	/**
	* Returns an ordered range of all the ticket attachments where type = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param types the types
	* @param start the lower bound of the range of ticket attachments
	* @param end the upper bound of the range of ticket attachments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket attachments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TicketAttachment> findByType(
		int[] types, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByType(types, start, end, orderByComparator);
	}

	/**
	* Returns all the ticket attachments where createDate &lt; &#63; and ticketEntryId = &#63;.
	*
	* @param createDate the create date
	* @param ticketEntryId the ticket entry ID
	* @return the matching ticket attachments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TicketAttachment> findByCD_TEI(
		java.util.Date createDate, long ticketEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCD_TEI(createDate, ticketEntryId);
	}

	/**
	* Returns a range of all the ticket attachments where createDate &lt; &#63; and ticketEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param createDate the create date
	* @param ticketEntryId the ticket entry ID
	* @param start the lower bound of the range of ticket attachments
	* @param end the upper bound of the range of ticket attachments (not inclusive)
	* @return the range of matching ticket attachments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TicketAttachment> findByCD_TEI(
		java.util.Date createDate, long ticketEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCD_TEI(createDate, ticketEntryId, start, end);
	}

	/**
	* Returns an ordered range of all the ticket attachments where createDate &lt; &#63; and ticketEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param createDate the create date
	* @param ticketEntryId the ticket entry ID
	* @param start the lower bound of the range of ticket attachments
	* @param end the upper bound of the range of ticket attachments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket attachments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TicketAttachment> findByCD_TEI(
		java.util.Date createDate, long ticketEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCD_TEI(createDate, ticketEntryId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first ticket attachment in the ordered set where createDate &lt; &#63; and ticketEntryId = &#63;.
	*
	* @param createDate the create date
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket attachment
	* @throws com.liferay.osb.NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketAttachment findByCD_TEI_First(
		java.util.Date createDate, long ticketEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketAttachmentException,
			com.liferay.portal.kernel.exception.SystemException {
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
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketAttachment fetchByCD_TEI_First(
		java.util.Date createDate, long ticketEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
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
	* @throws com.liferay.osb.NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketAttachment findByCD_TEI_Last(
		java.util.Date createDate, long ticketEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketAttachmentException,
			com.liferay.portal.kernel.exception.SystemException {
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
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketAttachment fetchByCD_TEI_Last(
		java.util.Date createDate, long ticketEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
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
	* @throws com.liferay.osb.NoSuchTicketAttachmentException if a ticket attachment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketAttachment[] findByCD_TEI_PrevAndNext(
		long ticketAttachmentId, java.util.Date createDate, long ticketEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketAttachmentException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCD_TEI_PrevAndNext(ticketAttachmentId, createDate,
			ticketEntryId, orderByComparator);
	}

	/**
	* Returns all the ticket attachments where createDate &lt; &#63; and type = &#63;.
	*
	* @param createDate the create date
	* @param type the type
	* @return the matching ticket attachments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TicketAttachment> findByCD_T(
		java.util.Date createDate, int type)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCD_T(createDate, type);
	}

	/**
	* Returns a range of all the ticket attachments where createDate &lt; &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param createDate the create date
	* @param type the type
	* @param start the lower bound of the range of ticket attachments
	* @param end the upper bound of the range of ticket attachments (not inclusive)
	* @return the range of matching ticket attachments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TicketAttachment> findByCD_T(
		java.util.Date createDate, int type, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCD_T(createDate, type, start, end);
	}

	/**
	* Returns an ordered range of all the ticket attachments where createDate &lt; &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param createDate the create date
	* @param type the type
	* @param start the lower bound of the range of ticket attachments
	* @param end the upper bound of the range of ticket attachments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket attachments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TicketAttachment> findByCD_T(
		java.util.Date createDate, int type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCD_T(createDate, type, start, end, orderByComparator);
	}

	/**
	* Returns the first ticket attachment in the ordered set where createDate &lt; &#63; and type = &#63;.
	*
	* @param createDate the create date
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket attachment
	* @throws com.liferay.osb.NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketAttachment findByCD_T_First(
		java.util.Date createDate, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketAttachmentException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCD_T_First(createDate, type, orderByComparator);
	}

	/**
	* Returns the first ticket attachment in the ordered set where createDate &lt; &#63; and type = &#63;.
	*
	* @param createDate the create date
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketAttachment fetchByCD_T_First(
		java.util.Date createDate, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByCD_T_First(createDate, type, orderByComparator);
	}

	/**
	* Returns the last ticket attachment in the ordered set where createDate &lt; &#63; and type = &#63;.
	*
	* @param createDate the create date
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket attachment
	* @throws com.liferay.osb.NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketAttachment findByCD_T_Last(
		java.util.Date createDate, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketAttachmentException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCD_T_Last(createDate, type, orderByComparator);
	}

	/**
	* Returns the last ticket attachment in the ordered set where createDate &lt; &#63; and type = &#63;.
	*
	* @param createDate the create date
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketAttachment fetchByCD_T_Last(
		java.util.Date createDate, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByCD_T_Last(createDate, type, orderByComparator);
	}

	/**
	* Returns the ticket attachments before and after the current ticket attachment in the ordered set where createDate &lt; &#63; and type = &#63;.
	*
	* @param ticketAttachmentId the primary key of the current ticket attachment
	* @param createDate the create date
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ticket attachment
	* @throws com.liferay.osb.NoSuchTicketAttachmentException if a ticket attachment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketAttachment[] findByCD_T_PrevAndNext(
		long ticketAttachmentId, java.util.Date createDate, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketAttachmentException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCD_T_PrevAndNext(ticketAttachmentId, createDate,
			type, orderByComparator);
	}

	/**
	* Returns all the ticket attachments where ticketEntryId = &#63; and ticketSolutionId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param ticketSolutionId the ticket solution ID
	* @return the matching ticket attachments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TicketAttachment> findByTEI_TSI(
		long ticketEntryId, long ticketSolutionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByTEI_TSI(ticketEntryId, ticketSolutionId);
	}

	/**
	* Returns a range of all the ticket attachments where ticketEntryId = &#63; and ticketSolutionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param ticketSolutionId the ticket solution ID
	* @param start the lower bound of the range of ticket attachments
	* @param end the upper bound of the range of ticket attachments (not inclusive)
	* @return the range of matching ticket attachments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TicketAttachment> findByTEI_TSI(
		long ticketEntryId, long ticketSolutionId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTEI_TSI(ticketEntryId, ticketSolutionId, start, end);
	}

	/**
	* Returns an ordered range of all the ticket attachments where ticketEntryId = &#63; and ticketSolutionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param ticketSolutionId the ticket solution ID
	* @param start the lower bound of the range of ticket attachments
	* @param end the upper bound of the range of ticket attachments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket attachments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TicketAttachment> findByTEI_TSI(
		long ticketEntryId, long ticketSolutionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTEI_TSI(ticketEntryId, ticketSolutionId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first ticket attachment in the ordered set where ticketEntryId = &#63; and ticketSolutionId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param ticketSolutionId the ticket solution ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket attachment
	* @throws com.liferay.osb.NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketAttachment findByTEI_TSI_First(
		long ticketEntryId, long ticketSolutionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketAttachmentException,
			com.liferay.portal.kernel.exception.SystemException {
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
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketAttachment fetchByTEI_TSI_First(
		long ticketEntryId, long ticketSolutionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
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
	* @throws com.liferay.osb.NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketAttachment findByTEI_TSI_Last(
		long ticketEntryId, long ticketSolutionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketAttachmentException,
			com.liferay.portal.kernel.exception.SystemException {
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
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketAttachment fetchByTEI_TSI_Last(
		long ticketEntryId, long ticketSolutionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
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
	* @throws com.liferay.osb.NoSuchTicketAttachmentException if a ticket attachment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketAttachment[] findByTEI_TSI_PrevAndNext(
		long ticketAttachmentId, long ticketEntryId, long ticketSolutionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketAttachmentException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTEI_TSI_PrevAndNext(ticketAttachmentId,
			ticketEntryId, ticketSolutionId, orderByComparator);
	}

	/**
	* Returns all the ticket attachments where ticketEntryId = &#63; and status = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param status the status
	* @return the matching ticket attachments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TicketAttachment> findByTEI_S(
		long ticketEntryId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByTEI_S(ticketEntryId, status);
	}

	/**
	* Returns a range of all the ticket attachments where ticketEntryId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param status the status
	* @param start the lower bound of the range of ticket attachments
	* @param end the upper bound of the range of ticket attachments (not inclusive)
	* @return the range of matching ticket attachments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TicketAttachment> findByTEI_S(
		long ticketEntryId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByTEI_S(ticketEntryId, status, start, end);
	}

	/**
	* Returns an ordered range of all the ticket attachments where ticketEntryId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param status the status
	* @param start the lower bound of the range of ticket attachments
	* @param end the upper bound of the range of ticket attachments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket attachments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TicketAttachment> findByTEI_S(
		long ticketEntryId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTEI_S(ticketEntryId, status, start, end,
			orderByComparator);
	}

	/**
	* Returns the first ticket attachment in the ordered set where ticketEntryId = &#63; and status = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket attachment
	* @throws com.liferay.osb.NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketAttachment findByTEI_S_First(
		long ticketEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketAttachmentException,
			com.liferay.portal.kernel.exception.SystemException {
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
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketAttachment fetchByTEI_S_First(
		long ticketEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
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
	* @throws com.liferay.osb.NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketAttachment findByTEI_S_Last(
		long ticketEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketAttachmentException,
			com.liferay.portal.kernel.exception.SystemException {
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
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketAttachment fetchByTEI_S_Last(
		long ticketEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
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
	* @throws com.liferay.osb.NoSuchTicketAttachmentException if a ticket attachment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketAttachment[] findByTEI_S_PrevAndNext(
		long ticketAttachmentId, long ticketEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketAttachmentException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTEI_S_PrevAndNext(ticketAttachmentId, ticketEntryId,
			status, orderByComparator);
	}

	/**
	* Returns all the ticket attachments where type = &#63; and deleteDate &lt; &#63;.
	*
	* @param type the type
	* @param deleteDate the delete date
	* @return the matching ticket attachments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TicketAttachment> findByT_DD(
		int type, java.util.Date deleteDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByT_DD(type, deleteDate);
	}

	/**
	* Returns a range of all the ticket attachments where type = &#63; and deleteDate &lt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param type the type
	* @param deleteDate the delete date
	* @param start the lower bound of the range of ticket attachments
	* @param end the upper bound of the range of ticket attachments (not inclusive)
	* @return the range of matching ticket attachments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TicketAttachment> findByT_DD(
		int type, java.util.Date deleteDate, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByT_DD(type, deleteDate, start, end);
	}

	/**
	* Returns an ordered range of all the ticket attachments where type = &#63; and deleteDate &lt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param type the type
	* @param deleteDate the delete date
	* @param start the lower bound of the range of ticket attachments
	* @param end the upper bound of the range of ticket attachments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket attachments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TicketAttachment> findByT_DD(
		int type, java.util.Date deleteDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByT_DD(type, deleteDate, start, end, orderByComparator);
	}

	/**
	* Returns the first ticket attachment in the ordered set where type = &#63; and deleteDate &lt; &#63;.
	*
	* @param type the type
	* @param deleteDate the delete date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket attachment
	* @throws com.liferay.osb.NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketAttachment findByT_DD_First(
		int type, java.util.Date deleteDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketAttachmentException,
			com.liferay.portal.kernel.exception.SystemException {
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
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketAttachment fetchByT_DD_First(
		int type, java.util.Date deleteDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
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
	* @throws com.liferay.osb.NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketAttachment findByT_DD_Last(
		int type, java.util.Date deleteDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketAttachmentException,
			com.liferay.portal.kernel.exception.SystemException {
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
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketAttachment fetchByT_DD_Last(
		int type, java.util.Date deleteDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
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
	* @throws com.liferay.osb.NoSuchTicketAttachmentException if a ticket attachment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketAttachment[] findByT_DD_PrevAndNext(
		long ticketAttachmentId, int type, java.util.Date deleteDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketAttachmentException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByT_DD_PrevAndNext(ticketAttachmentId, type,
			deleteDate, orderByComparator);
	}

	/**
	* Returns all the ticket attachments where type = any &#63; and deleteDate &lt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param types the types
	* @param deleteDate the delete date
	* @return the matching ticket attachments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TicketAttachment> findByT_DD(
		int[] types, java.util.Date deleteDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByT_DD(types, deleteDate);
	}

	/**
	* Returns a range of all the ticket attachments where type = any &#63; and deleteDate &lt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param types the types
	* @param deleteDate the delete date
	* @param start the lower bound of the range of ticket attachments
	* @param end the upper bound of the range of ticket attachments (not inclusive)
	* @return the range of matching ticket attachments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TicketAttachment> findByT_DD(
		int[] types, java.util.Date deleteDate, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByT_DD(types, deleteDate, start, end);
	}

	/**
	* Returns an ordered range of all the ticket attachments where type = any &#63; and deleteDate &lt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param types the types
	* @param deleteDate the delete date
	* @param start the lower bound of the range of ticket attachments
	* @param end the upper bound of the range of ticket attachments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket attachments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TicketAttachment> findByT_DD(
		int[] types, java.util.Date deleteDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByT_DD(types, deleteDate, start, end, orderByComparator);
	}

	/**
	* Returns all the ticket attachments where ticketEntryId = &#63; and type = &#63; and status = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param status the status
	* @return the matching ticket attachments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TicketAttachment> findByTEI_T_S(
		long ticketEntryId, int type, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByTEI_T_S(ticketEntryId, type, status);
	}

	/**
	* Returns a range of all the ticket attachments where ticketEntryId = &#63; and type = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param status the status
	* @param start the lower bound of the range of ticket attachments
	* @param end the upper bound of the range of ticket attachments (not inclusive)
	* @return the range of matching ticket attachments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TicketAttachment> findByTEI_T_S(
		long ticketEntryId, int type, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTEI_T_S(ticketEntryId, type, status, start, end);
	}

	/**
	* Returns an ordered range of all the ticket attachments where ticketEntryId = &#63; and type = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param status the status
	* @param start the lower bound of the range of ticket attachments
	* @param end the upper bound of the range of ticket attachments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket attachments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TicketAttachment> findByTEI_T_S(
		long ticketEntryId, int type, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTEI_T_S(ticketEntryId, type, status, start, end,
			orderByComparator);
	}

	/**
	* Returns the first ticket attachment in the ordered set where ticketEntryId = &#63; and type = &#63; and status = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket attachment
	* @throws com.liferay.osb.NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketAttachment findByTEI_T_S_First(
		long ticketEntryId, int type, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketAttachmentException,
			com.liferay.portal.kernel.exception.SystemException {
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
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketAttachment fetchByTEI_T_S_First(
		long ticketEntryId, int type, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
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
	* @throws com.liferay.osb.NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketAttachment findByTEI_T_S_Last(
		long ticketEntryId, int type, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketAttachmentException,
			com.liferay.portal.kernel.exception.SystemException {
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
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketAttachment fetchByTEI_T_S_Last(
		long ticketEntryId, int type, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
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
	* @throws com.liferay.osb.NoSuchTicketAttachmentException if a ticket attachment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketAttachment[] findByTEI_T_S_PrevAndNext(
		long ticketAttachmentId, long ticketEntryId, int type, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketAttachmentException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTEI_T_S_PrevAndNext(ticketAttachmentId,
			ticketEntryId, type, status, orderByComparator);
	}

	/**
	* Returns all the ticket attachments where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	*
	* @param userId the user ID
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param status the status
	* @return the matching ticket attachments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TicketAttachment> findByU_TEI_V_S(
		long userId, long ticketEntryId, int visibility, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_TEI_V_S(userId, ticketEntryId, visibility, status);
	}

	/**
	* Returns a range of all the ticket attachments where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param status the status
	* @param start the lower bound of the range of ticket attachments
	* @param end the upper bound of the range of ticket attachments (not inclusive)
	* @return the range of matching ticket attachments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TicketAttachment> findByU_TEI_V_S(
		long userId, long ticketEntryId, int visibility, int status, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_TEI_V_S(userId, ticketEntryId, visibility, status,
			start, end);
	}

	/**
	* Returns an ordered range of all the ticket attachments where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
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
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TicketAttachment> findByU_TEI_V_S(
		long userId, long ticketEntryId, int visibility, int status, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_TEI_V_S(userId, ticketEntryId, visibility, status,
			start, end, orderByComparator);
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
	* @throws com.liferay.osb.NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketAttachment findByU_TEI_V_S_First(
		long userId, long ticketEntryId, int visibility, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketAttachmentException,
			com.liferay.portal.kernel.exception.SystemException {
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
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketAttachment fetchByU_TEI_V_S_First(
		long userId, long ticketEntryId, int visibility, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
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
	* @throws com.liferay.osb.NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketAttachment findByU_TEI_V_S_Last(
		long userId, long ticketEntryId, int visibility, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketAttachmentException,
			com.liferay.portal.kernel.exception.SystemException {
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
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketAttachment fetchByU_TEI_V_S_Last(
		long userId, long ticketEntryId, int visibility, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
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
	* @throws com.liferay.osb.NoSuchTicketAttachmentException if a ticket attachment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketAttachment[] findByU_TEI_V_S_PrevAndNext(
		long ticketAttachmentId, long userId, long ticketEntryId,
		int visibility, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketAttachmentException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_TEI_V_S_PrevAndNext(ticketAttachmentId, userId,
			ticketEntryId, visibility, status, orderByComparator);
	}

	/**
	* Returns the ticket attachment where ticketEntryId = &#63; and fileName = &#63; and visibility = &#63; and status = &#63; or throws a {@link com.liferay.osb.NoSuchTicketAttachmentException} if it could not be found.
	*
	* @param ticketEntryId the ticket entry ID
	* @param fileName the file name
	* @param visibility the visibility
	* @param status the status
	* @return the matching ticket attachment
	* @throws com.liferay.osb.NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketAttachment findByTEI_FN_V_S(
		long ticketEntryId, java.lang.String fileName, int visibility,
		int status)
		throws com.liferay.osb.NoSuchTicketAttachmentException,
			com.liferay.portal.kernel.exception.SystemException {
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
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketAttachment fetchByTEI_FN_V_S(
		long ticketEntryId, java.lang.String fileName, int visibility,
		int status) throws com.liferay.portal.kernel.exception.SystemException {
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
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketAttachment fetchByTEI_FN_V_S(
		long ticketEntryId, java.lang.String fileName, int visibility,
		int status, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByTEI_FN_V_S(ticketEntryId, fileName, visibility,
			status, retrieveFromCache);
	}

	/**
	* Returns all the ticket attachments where ticketEntryId = &#63; and type = &#63; and visibility = &#63; and status = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param visibility the visibility
	* @param status the status
	* @return the matching ticket attachments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TicketAttachment> findByTEI_T_V_S(
		long ticketEntryId, int type, int visibility, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTEI_T_V_S(ticketEntryId, type, visibility, status);
	}

	/**
	* Returns a range of all the ticket attachments where ticketEntryId = &#63; and type = &#63; and visibility = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param visibility the visibility
	* @param status the status
	* @param start the lower bound of the range of ticket attachments
	* @param end the upper bound of the range of ticket attachments (not inclusive)
	* @return the range of matching ticket attachments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TicketAttachment> findByTEI_T_V_S(
		long ticketEntryId, int type, int visibility, int status, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTEI_T_V_S(ticketEntryId, type, visibility, status,
			start, end);
	}

	/**
	* Returns an ordered range of all the ticket attachments where ticketEntryId = &#63; and type = &#63; and visibility = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
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
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TicketAttachment> findByTEI_T_V_S(
		long ticketEntryId, int type, int visibility, int status, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTEI_T_V_S(ticketEntryId, type, visibility, status,
			start, end, orderByComparator);
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
	* @throws com.liferay.osb.NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketAttachment findByTEI_T_V_S_First(
		long ticketEntryId, int type, int visibility, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketAttachmentException,
			com.liferay.portal.kernel.exception.SystemException {
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
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketAttachment fetchByTEI_T_V_S_First(
		long ticketEntryId, int type, int visibility, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
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
	* @throws com.liferay.osb.NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketAttachment findByTEI_T_V_S_Last(
		long ticketEntryId, int type, int visibility, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketAttachmentException,
			com.liferay.portal.kernel.exception.SystemException {
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
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketAttachment fetchByTEI_T_V_S_Last(
		long ticketEntryId, int type, int visibility, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
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
	* @throws com.liferay.osb.NoSuchTicketAttachmentException if a ticket attachment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketAttachment[] findByTEI_T_V_S_PrevAndNext(
		long ticketAttachmentId, long ticketEntryId, int type, int visibility,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketAttachmentException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTEI_T_V_S_PrevAndNext(ticketAttachmentId,
			ticketEntryId, type, visibility, status, orderByComparator);
	}

	/**
	* Returns all the ticket attachments where ticketEntryId = &#63; and type = any &#63; and visibility = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param types the types
	* @param visibilities the visibilities
	* @param status the status
	* @return the matching ticket attachments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TicketAttachment> findByTEI_T_V_S(
		long ticketEntryId, int[] types, int[] visibilities, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTEI_T_V_S(ticketEntryId, types, visibilities, status);
	}

	/**
	* Returns a range of all the ticket attachments where ticketEntryId = &#63; and type = any &#63; and visibility = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param types the types
	* @param visibilities the visibilities
	* @param status the status
	* @param start the lower bound of the range of ticket attachments
	* @param end the upper bound of the range of ticket attachments (not inclusive)
	* @return the range of matching ticket attachments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TicketAttachment> findByTEI_T_V_S(
		long ticketEntryId, int[] types, int[] visibilities, int status,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTEI_T_V_S(ticketEntryId, types, visibilities, status,
			start, end);
	}

	/**
	* Returns an ordered range of all the ticket attachments where ticketEntryId = &#63; and type = any &#63; and visibility = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
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
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TicketAttachment> findByTEI_T_V_S(
		long ticketEntryId, int[] types, int[] visibilities, int status,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTEI_T_V_S(ticketEntryId, types, visibilities, status,
			start, end, orderByComparator);
	}

	/**
	* Returns all the ticket attachments.
	*
	* @return the ticket attachments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TicketAttachment> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the ticket attachments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of ticket attachments
	* @param end the upper bound of the range of ticket attachments (not inclusive)
	* @return the range of ticket attachments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TicketAttachment> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the ticket attachments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of ticket attachments
	* @param end the upper bound of the range of ticket attachments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ticket attachments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TicketAttachment> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the ticket attachments where type = &#63; from the database.
	*
	* @param type the type
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByType(int type)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByType(type);
	}

	/**
	* Removes all the ticket attachments where createDate &lt; &#63; and ticketEntryId = &#63; from the database.
	*
	* @param createDate the create date
	* @param ticketEntryId the ticket entry ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByCD_TEI(java.util.Date createDate,
		long ticketEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByCD_TEI(createDate, ticketEntryId);
	}

	/**
	* Removes all the ticket attachments where createDate &lt; &#63; and type = &#63; from the database.
	*
	* @param createDate the create date
	* @param type the type
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByCD_T(java.util.Date createDate, int type)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByCD_T(createDate, type);
	}

	/**
	* Removes all the ticket attachments where ticketEntryId = &#63; and ticketSolutionId = &#63; from the database.
	*
	* @param ticketEntryId the ticket entry ID
	* @param ticketSolutionId the ticket solution ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByTEI_TSI(long ticketEntryId, long ticketSolutionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByTEI_TSI(ticketEntryId, ticketSolutionId);
	}

	/**
	* Removes all the ticket attachments where ticketEntryId = &#63; and status = &#63; from the database.
	*
	* @param ticketEntryId the ticket entry ID
	* @param status the status
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByTEI_S(long ticketEntryId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByTEI_S(ticketEntryId, status);
	}

	/**
	* Removes all the ticket attachments where type = &#63; and deleteDate &lt; &#63; from the database.
	*
	* @param type the type
	* @param deleteDate the delete date
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByT_DD(int type, java.util.Date deleteDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByT_DD(type, deleteDate);
	}

	/**
	* Removes all the ticket attachments where ticketEntryId = &#63; and type = &#63; and status = &#63; from the database.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param status the status
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByTEI_T_S(long ticketEntryId, int type, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByTEI_T_S(ticketEntryId, type, status);
	}

	/**
	* Removes all the ticket attachments where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63; from the database.
	*
	* @param userId the user ID
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param status the status
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByU_TEI_V_S(long userId, long ticketEntryId,
		int visibility, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence()
			.removeByU_TEI_V_S(userId, ticketEntryId, visibility, status);
	}

	/**
	* Removes the ticket attachment where ticketEntryId = &#63; and fileName = &#63; and visibility = &#63; and status = &#63; from the database.
	*
	* @param ticketEntryId the ticket entry ID
	* @param fileName the file name
	* @param visibility the visibility
	* @param status the status
	* @return the ticket attachment that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketAttachment removeByTEI_FN_V_S(
		long ticketEntryId, java.lang.String fileName, int visibility,
		int status)
		throws com.liferay.osb.NoSuchTicketAttachmentException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .removeByTEI_FN_V_S(ticketEntryId, fileName, visibility,
			status);
	}

	/**
	* Removes all the ticket attachments where ticketEntryId = &#63; and type = &#63; and visibility = &#63; and status = &#63; from the database.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param visibility the visibility
	* @param status the status
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByTEI_T_V_S(long ticketEntryId, int type,
		int visibility, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence()
			.removeByTEI_T_V_S(ticketEntryId, type, visibility, status);
	}

	/**
	* Removes all the ticket attachments from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of ticket attachments where type = &#63;.
	*
	* @param type the type
	* @return the number of matching ticket attachments
	* @throws SystemException if a system exception occurred
	*/
	public static int countByType(int type)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByType(type);
	}

	/**
	* Returns the number of ticket attachments where type = any &#63;.
	*
	* @param types the types
	* @return the number of matching ticket attachments
	* @throws SystemException if a system exception occurred
	*/
	public static int countByType(int[] types)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByType(types);
	}

	/**
	* Returns the number of ticket attachments where createDate &lt; &#63; and ticketEntryId = &#63;.
	*
	* @param createDate the create date
	* @param ticketEntryId the ticket entry ID
	* @return the number of matching ticket attachments
	* @throws SystemException if a system exception occurred
	*/
	public static int countByCD_TEI(java.util.Date createDate,
		long ticketEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByCD_TEI(createDate, ticketEntryId);
	}

	/**
	* Returns the number of ticket attachments where createDate &lt; &#63; and type = &#63;.
	*
	* @param createDate the create date
	* @param type the type
	* @return the number of matching ticket attachments
	* @throws SystemException if a system exception occurred
	*/
	public static int countByCD_T(java.util.Date createDate, int type)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByCD_T(createDate, type);
	}

	/**
	* Returns the number of ticket attachments where ticketEntryId = &#63; and ticketSolutionId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param ticketSolutionId the ticket solution ID
	* @return the number of matching ticket attachments
	* @throws SystemException if a system exception occurred
	*/
	public static int countByTEI_TSI(long ticketEntryId, long ticketSolutionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByTEI_TSI(ticketEntryId, ticketSolutionId);
	}

	/**
	* Returns the number of ticket attachments where ticketEntryId = &#63; and status = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param status the status
	* @return the number of matching ticket attachments
	* @throws SystemException if a system exception occurred
	*/
	public static int countByTEI_S(long ticketEntryId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByTEI_S(ticketEntryId, status);
	}

	/**
	* Returns the number of ticket attachments where type = &#63; and deleteDate &lt; &#63;.
	*
	* @param type the type
	* @param deleteDate the delete date
	* @return the number of matching ticket attachments
	* @throws SystemException if a system exception occurred
	*/
	public static int countByT_DD(int type, java.util.Date deleteDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByT_DD(type, deleteDate);
	}

	/**
	* Returns the number of ticket attachments where type = any &#63; and deleteDate &lt; &#63;.
	*
	* @param types the types
	* @param deleteDate the delete date
	* @return the number of matching ticket attachments
	* @throws SystemException if a system exception occurred
	*/
	public static int countByT_DD(int[] types, java.util.Date deleteDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByT_DD(types, deleteDate);
	}

	/**
	* Returns the number of ticket attachments where ticketEntryId = &#63; and type = &#63; and status = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param status the status
	* @return the number of matching ticket attachments
	* @throws SystemException if a system exception occurred
	*/
	public static int countByTEI_T_S(long ticketEntryId, int type, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByTEI_T_S(ticketEntryId, type, status);
	}

	/**
	* Returns the number of ticket attachments where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	*
	* @param userId the user ID
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param status the status
	* @return the number of matching ticket attachments
	* @throws SystemException if a system exception occurred
	*/
	public static int countByU_TEI_V_S(long userId, long ticketEntryId,
		int visibility, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByU_TEI_V_S(userId, ticketEntryId, visibility, status);
	}

	/**
	* Returns the number of ticket attachments where ticketEntryId = &#63; and fileName = &#63; and visibility = &#63; and status = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param fileName the file name
	* @param visibility the visibility
	* @param status the status
	* @return the number of matching ticket attachments
	* @throws SystemException if a system exception occurred
	*/
	public static int countByTEI_FN_V_S(long ticketEntryId,
		java.lang.String fileName, int visibility, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByTEI_FN_V_S(ticketEntryId, fileName, visibility,
			status);
	}

	/**
	* Returns the number of ticket attachments where ticketEntryId = &#63; and type = &#63; and visibility = &#63; and status = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param visibility the visibility
	* @param status the status
	* @return the number of matching ticket attachments
	* @throws SystemException if a system exception occurred
	*/
	public static int countByTEI_T_V_S(long ticketEntryId, int type,
		int visibility, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
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
	* @throws SystemException if a system exception occurred
	*/
	public static int countByTEI_T_V_S(long ticketEntryId, int[] types,
		int[] visibilities, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByTEI_T_V_S(ticketEntryId, types, visibilities, status);
	}

	/**
	* Returns the number of ticket attachments.
	*
	* @return the number of ticket attachments
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
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

	/**
	 * @deprecated
	 */
	public void setPersistence(TicketAttachmentPersistence persistence) {
	}

	private static TicketAttachmentPersistence _persistence;
}