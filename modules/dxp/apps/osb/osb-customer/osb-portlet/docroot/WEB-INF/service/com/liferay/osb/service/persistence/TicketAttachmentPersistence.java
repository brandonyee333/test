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

import com.liferay.osb.exception.NoSuchTicketAttachmentException;
import com.liferay.osb.model.TicketAttachment;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.util.Date;

/**
 * The persistence interface for the ticket attachment service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.service.persistence.impl.TicketAttachmentPersistenceImpl
 * @see TicketAttachmentUtil
 * @generated
 */
@ProviderType
public interface TicketAttachmentPersistence extends BasePersistence<TicketAttachment> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TicketAttachmentUtil} to access the ticket attachment persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the ticket attachments where type = &#63;.
	*
	* @param type the type
	* @return the matching ticket attachments
	*/
	public java.util.List<TicketAttachment> findByType(int type);

	/**
	* Returns a range of all the ticket attachments where type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param type the type
	* @param start the lower bound of the range of ticket attachments
	* @param end the upper bound of the range of ticket attachments (not inclusive)
	* @return the range of matching ticket attachments
	*/
	public java.util.List<TicketAttachment> findByType(int type, int start,
		int end);

	/**
	* Returns an ordered range of all the ticket attachments where type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param type the type
	* @param start the lower bound of the range of ticket attachments
	* @param end the upper bound of the range of ticket attachments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket attachments
	*/
	public java.util.List<TicketAttachment> findByType(int type, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<TicketAttachment> orderByComparator);

	/**
	* Returns an ordered range of all the ticket attachments where type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param type the type
	* @param start the lower bound of the range of ticket attachments
	* @param end the upper bound of the range of ticket attachments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching ticket attachments
	*/
	public java.util.List<TicketAttachment> findByType(int type, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<TicketAttachment> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first ticket attachment in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket attachment
	* @throws NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	*/
	public TicketAttachment findByType_First(int type,
		com.liferay.portal.kernel.util.OrderByComparator<TicketAttachment> orderByComparator)
		throws NoSuchTicketAttachmentException;

	/**
	* Returns the first ticket attachment in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	*/
	public TicketAttachment fetchByType_First(int type,
		com.liferay.portal.kernel.util.OrderByComparator<TicketAttachment> orderByComparator);

	/**
	* Returns the last ticket attachment in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket attachment
	* @throws NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	*/
	public TicketAttachment findByType_Last(int type,
		com.liferay.portal.kernel.util.OrderByComparator<TicketAttachment> orderByComparator)
		throws NoSuchTicketAttachmentException;

	/**
	* Returns the last ticket attachment in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	*/
	public TicketAttachment fetchByType_Last(int type,
		com.liferay.portal.kernel.util.OrderByComparator<TicketAttachment> orderByComparator);

	/**
	* Returns the ticket attachments before and after the current ticket attachment in the ordered set where type = &#63;.
	*
	* @param ticketAttachmentId the primary key of the current ticket attachment
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ticket attachment
	* @throws NoSuchTicketAttachmentException if a ticket attachment with the primary key could not be found
	*/
	public TicketAttachment[] findByType_PrevAndNext(long ticketAttachmentId,
		int type,
		com.liferay.portal.kernel.util.OrderByComparator<TicketAttachment> orderByComparator)
		throws NoSuchTicketAttachmentException;

	/**
	* Returns all the ticket attachments where type = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param types the types
	* @return the matching ticket attachments
	*/
	public java.util.List<TicketAttachment> findByType(int[] types);

	/**
	* Returns a range of all the ticket attachments where type = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param types the types
	* @param start the lower bound of the range of ticket attachments
	* @param end the upper bound of the range of ticket attachments (not inclusive)
	* @return the range of matching ticket attachments
	*/
	public java.util.List<TicketAttachment> findByType(int[] types, int start,
		int end);

	/**
	* Returns an ordered range of all the ticket attachments where type = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param types the types
	* @param start the lower bound of the range of ticket attachments
	* @param end the upper bound of the range of ticket attachments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket attachments
	*/
	public java.util.List<TicketAttachment> findByType(int[] types, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<TicketAttachment> orderByComparator);

	/**
	* Returns an ordered range of all the ticket attachments where type = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param type the type
	* @param start the lower bound of the range of ticket attachments
	* @param end the upper bound of the range of ticket attachments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching ticket attachments
	*/
	public java.util.List<TicketAttachment> findByType(int[] types, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<TicketAttachment> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the ticket attachments where type = &#63; from the database.
	*
	* @param type the type
	*/
	public void removeByType(int type);

	/**
	* Returns the number of ticket attachments where type = &#63;.
	*
	* @param type the type
	* @return the number of matching ticket attachments
	*/
	public int countByType(int type);

	/**
	* Returns the number of ticket attachments where type = any &#63;.
	*
	* @param types the types
	* @return the number of matching ticket attachments
	*/
	public int countByType(int[] types);

	/**
	* Returns all the ticket attachments where createDate &lt; &#63; and ticketEntryId = &#63;.
	*
	* @param createDate the create date
	* @param ticketEntryId the ticket entry ID
	* @return the matching ticket attachments
	*/
	public java.util.List<TicketAttachment> findByCD_TEI(Date createDate,
		long ticketEntryId);

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
	public java.util.List<TicketAttachment> findByCD_TEI(Date createDate,
		long ticketEntryId, int start, int end);

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
	public java.util.List<TicketAttachment> findByCD_TEI(Date createDate,
		long ticketEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TicketAttachment> orderByComparator);

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
	public java.util.List<TicketAttachment> findByCD_TEI(Date createDate,
		long ticketEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TicketAttachment> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first ticket attachment in the ordered set where createDate &lt; &#63; and ticketEntryId = &#63;.
	*
	* @param createDate the create date
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket attachment
	* @throws NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	*/
	public TicketAttachment findByCD_TEI_First(Date createDate,
		long ticketEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<TicketAttachment> orderByComparator)
		throws NoSuchTicketAttachmentException;

	/**
	* Returns the first ticket attachment in the ordered set where createDate &lt; &#63; and ticketEntryId = &#63;.
	*
	* @param createDate the create date
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	*/
	public TicketAttachment fetchByCD_TEI_First(Date createDate,
		long ticketEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<TicketAttachment> orderByComparator);

	/**
	* Returns the last ticket attachment in the ordered set where createDate &lt; &#63; and ticketEntryId = &#63;.
	*
	* @param createDate the create date
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket attachment
	* @throws NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	*/
	public TicketAttachment findByCD_TEI_Last(Date createDate,
		long ticketEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<TicketAttachment> orderByComparator)
		throws NoSuchTicketAttachmentException;

	/**
	* Returns the last ticket attachment in the ordered set where createDate &lt; &#63; and ticketEntryId = &#63;.
	*
	* @param createDate the create date
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	*/
	public TicketAttachment fetchByCD_TEI_Last(Date createDate,
		long ticketEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<TicketAttachment> orderByComparator);

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
	public TicketAttachment[] findByCD_TEI_PrevAndNext(
		long ticketAttachmentId, Date createDate, long ticketEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<TicketAttachment> orderByComparator)
		throws NoSuchTicketAttachmentException;

	/**
	* Removes all the ticket attachments where createDate &lt; &#63; and ticketEntryId = &#63; from the database.
	*
	* @param createDate the create date
	* @param ticketEntryId the ticket entry ID
	*/
	public void removeByCD_TEI(Date createDate, long ticketEntryId);

	/**
	* Returns the number of ticket attachments where createDate &lt; &#63; and ticketEntryId = &#63;.
	*
	* @param createDate the create date
	* @param ticketEntryId the ticket entry ID
	* @return the number of matching ticket attachments
	*/
	public int countByCD_TEI(Date createDate, long ticketEntryId);

	/**
	* Returns all the ticket attachments where createDate &lt; &#63; and type = &#63;.
	*
	* @param createDate the create date
	* @param type the type
	* @return the matching ticket attachments
	*/
	public java.util.List<TicketAttachment> findByCD_T(Date createDate, int type);

	/**
	* Returns a range of all the ticket attachments where createDate &lt; &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param createDate the create date
	* @param type the type
	* @param start the lower bound of the range of ticket attachments
	* @param end the upper bound of the range of ticket attachments (not inclusive)
	* @return the range of matching ticket attachments
	*/
	public java.util.List<TicketAttachment> findByCD_T(Date createDate,
		int type, int start, int end);

	/**
	* Returns an ordered range of all the ticket attachments where createDate &lt; &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param createDate the create date
	* @param type the type
	* @param start the lower bound of the range of ticket attachments
	* @param end the upper bound of the range of ticket attachments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket attachments
	*/
	public java.util.List<TicketAttachment> findByCD_T(Date createDate,
		int type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TicketAttachment> orderByComparator);

	/**
	* Returns an ordered range of all the ticket attachments where createDate &lt; &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param createDate the create date
	* @param type the type
	* @param start the lower bound of the range of ticket attachments
	* @param end the upper bound of the range of ticket attachments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching ticket attachments
	*/
	public java.util.List<TicketAttachment> findByCD_T(Date createDate,
		int type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TicketAttachment> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first ticket attachment in the ordered set where createDate &lt; &#63; and type = &#63;.
	*
	* @param createDate the create date
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket attachment
	* @throws NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	*/
	public TicketAttachment findByCD_T_First(Date createDate, int type,
		com.liferay.portal.kernel.util.OrderByComparator<TicketAttachment> orderByComparator)
		throws NoSuchTicketAttachmentException;

	/**
	* Returns the first ticket attachment in the ordered set where createDate &lt; &#63; and type = &#63;.
	*
	* @param createDate the create date
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	*/
	public TicketAttachment fetchByCD_T_First(Date createDate, int type,
		com.liferay.portal.kernel.util.OrderByComparator<TicketAttachment> orderByComparator);

	/**
	* Returns the last ticket attachment in the ordered set where createDate &lt; &#63; and type = &#63;.
	*
	* @param createDate the create date
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket attachment
	* @throws NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	*/
	public TicketAttachment findByCD_T_Last(Date createDate, int type,
		com.liferay.portal.kernel.util.OrderByComparator<TicketAttachment> orderByComparator)
		throws NoSuchTicketAttachmentException;

	/**
	* Returns the last ticket attachment in the ordered set where createDate &lt; &#63; and type = &#63;.
	*
	* @param createDate the create date
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	*/
	public TicketAttachment fetchByCD_T_Last(Date createDate, int type,
		com.liferay.portal.kernel.util.OrderByComparator<TicketAttachment> orderByComparator);

	/**
	* Returns the ticket attachments before and after the current ticket attachment in the ordered set where createDate &lt; &#63; and type = &#63;.
	*
	* @param ticketAttachmentId the primary key of the current ticket attachment
	* @param createDate the create date
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ticket attachment
	* @throws NoSuchTicketAttachmentException if a ticket attachment with the primary key could not be found
	*/
	public TicketAttachment[] findByCD_T_PrevAndNext(long ticketAttachmentId,
		Date createDate, int type,
		com.liferay.portal.kernel.util.OrderByComparator<TicketAttachment> orderByComparator)
		throws NoSuchTicketAttachmentException;

	/**
	* Removes all the ticket attachments where createDate &lt; &#63; and type = &#63; from the database.
	*
	* @param createDate the create date
	* @param type the type
	*/
	public void removeByCD_T(Date createDate, int type);

	/**
	* Returns the number of ticket attachments where createDate &lt; &#63; and type = &#63;.
	*
	* @param createDate the create date
	* @param type the type
	* @return the number of matching ticket attachments
	*/
	public int countByCD_T(Date createDate, int type);

	/**
	* Returns all the ticket attachments where ticketEntryId = &#63; and ticketSolutionId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param ticketSolutionId the ticket solution ID
	* @return the matching ticket attachments
	*/
	public java.util.List<TicketAttachment> findByTEI_TSI(long ticketEntryId,
		long ticketSolutionId);

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
	public java.util.List<TicketAttachment> findByTEI_TSI(long ticketEntryId,
		long ticketSolutionId, int start, int end);

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
	public java.util.List<TicketAttachment> findByTEI_TSI(long ticketEntryId,
		long ticketSolutionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TicketAttachment> orderByComparator);

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
	public java.util.List<TicketAttachment> findByTEI_TSI(long ticketEntryId,
		long ticketSolutionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TicketAttachment> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first ticket attachment in the ordered set where ticketEntryId = &#63; and ticketSolutionId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param ticketSolutionId the ticket solution ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket attachment
	* @throws NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	*/
	public TicketAttachment findByTEI_TSI_First(long ticketEntryId,
		long ticketSolutionId,
		com.liferay.portal.kernel.util.OrderByComparator<TicketAttachment> orderByComparator)
		throws NoSuchTicketAttachmentException;

	/**
	* Returns the first ticket attachment in the ordered set where ticketEntryId = &#63; and ticketSolutionId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param ticketSolutionId the ticket solution ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	*/
	public TicketAttachment fetchByTEI_TSI_First(long ticketEntryId,
		long ticketSolutionId,
		com.liferay.portal.kernel.util.OrderByComparator<TicketAttachment> orderByComparator);

	/**
	* Returns the last ticket attachment in the ordered set where ticketEntryId = &#63; and ticketSolutionId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param ticketSolutionId the ticket solution ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket attachment
	* @throws NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	*/
	public TicketAttachment findByTEI_TSI_Last(long ticketEntryId,
		long ticketSolutionId,
		com.liferay.portal.kernel.util.OrderByComparator<TicketAttachment> orderByComparator)
		throws NoSuchTicketAttachmentException;

	/**
	* Returns the last ticket attachment in the ordered set where ticketEntryId = &#63; and ticketSolutionId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param ticketSolutionId the ticket solution ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	*/
	public TicketAttachment fetchByTEI_TSI_Last(long ticketEntryId,
		long ticketSolutionId,
		com.liferay.portal.kernel.util.OrderByComparator<TicketAttachment> orderByComparator);

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
	public TicketAttachment[] findByTEI_TSI_PrevAndNext(
		long ticketAttachmentId, long ticketEntryId, long ticketSolutionId,
		com.liferay.portal.kernel.util.OrderByComparator<TicketAttachment> orderByComparator)
		throws NoSuchTicketAttachmentException;

	/**
	* Removes all the ticket attachments where ticketEntryId = &#63; and ticketSolutionId = &#63; from the database.
	*
	* @param ticketEntryId the ticket entry ID
	* @param ticketSolutionId the ticket solution ID
	*/
	public void removeByTEI_TSI(long ticketEntryId, long ticketSolutionId);

	/**
	* Returns the number of ticket attachments where ticketEntryId = &#63; and ticketSolutionId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param ticketSolutionId the ticket solution ID
	* @return the number of matching ticket attachments
	*/
	public int countByTEI_TSI(long ticketEntryId, long ticketSolutionId);

	/**
	* Returns all the ticket attachments where ticketEntryId = &#63; and status = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param status the status
	* @return the matching ticket attachments
	*/
	public java.util.List<TicketAttachment> findByTEI_S(long ticketEntryId,
		int status);

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
	public java.util.List<TicketAttachment> findByTEI_S(long ticketEntryId,
		int status, int start, int end);

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
	public java.util.List<TicketAttachment> findByTEI_S(long ticketEntryId,
		int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TicketAttachment> orderByComparator);

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
	public java.util.List<TicketAttachment> findByTEI_S(long ticketEntryId,
		int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TicketAttachment> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first ticket attachment in the ordered set where ticketEntryId = &#63; and status = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket attachment
	* @throws NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	*/
	public TicketAttachment findByTEI_S_First(long ticketEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<TicketAttachment> orderByComparator)
		throws NoSuchTicketAttachmentException;

	/**
	* Returns the first ticket attachment in the ordered set where ticketEntryId = &#63; and status = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	*/
	public TicketAttachment fetchByTEI_S_First(long ticketEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<TicketAttachment> orderByComparator);

	/**
	* Returns the last ticket attachment in the ordered set where ticketEntryId = &#63; and status = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket attachment
	* @throws NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	*/
	public TicketAttachment findByTEI_S_Last(long ticketEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<TicketAttachment> orderByComparator)
		throws NoSuchTicketAttachmentException;

	/**
	* Returns the last ticket attachment in the ordered set where ticketEntryId = &#63; and status = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	*/
	public TicketAttachment fetchByTEI_S_Last(long ticketEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<TicketAttachment> orderByComparator);

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
	public TicketAttachment[] findByTEI_S_PrevAndNext(long ticketAttachmentId,
		long ticketEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<TicketAttachment> orderByComparator)
		throws NoSuchTicketAttachmentException;

	/**
	* Removes all the ticket attachments where ticketEntryId = &#63; and status = &#63; from the database.
	*
	* @param ticketEntryId the ticket entry ID
	* @param status the status
	*/
	public void removeByTEI_S(long ticketEntryId, int status);

	/**
	* Returns the number of ticket attachments where ticketEntryId = &#63; and status = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param status the status
	* @return the number of matching ticket attachments
	*/
	public int countByTEI_S(long ticketEntryId, int status);

	/**
	* Returns all the ticket attachments where type = &#63; and deleteDate &lt; &#63;.
	*
	* @param type the type
	* @param deleteDate the delete date
	* @return the matching ticket attachments
	*/
	public java.util.List<TicketAttachment> findByT_DD(int type, Date deleteDate);

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
	public java.util.List<TicketAttachment> findByT_DD(int type,
		Date deleteDate, int start, int end);

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
	public java.util.List<TicketAttachment> findByT_DD(int type,
		Date deleteDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TicketAttachment> orderByComparator);

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
	public java.util.List<TicketAttachment> findByT_DD(int type,
		Date deleteDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TicketAttachment> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first ticket attachment in the ordered set where type = &#63; and deleteDate &lt; &#63;.
	*
	* @param type the type
	* @param deleteDate the delete date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket attachment
	* @throws NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	*/
	public TicketAttachment findByT_DD_First(int type, Date deleteDate,
		com.liferay.portal.kernel.util.OrderByComparator<TicketAttachment> orderByComparator)
		throws NoSuchTicketAttachmentException;

	/**
	* Returns the first ticket attachment in the ordered set where type = &#63; and deleteDate &lt; &#63;.
	*
	* @param type the type
	* @param deleteDate the delete date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	*/
	public TicketAttachment fetchByT_DD_First(int type, Date deleteDate,
		com.liferay.portal.kernel.util.OrderByComparator<TicketAttachment> orderByComparator);

	/**
	* Returns the last ticket attachment in the ordered set where type = &#63; and deleteDate &lt; &#63;.
	*
	* @param type the type
	* @param deleteDate the delete date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket attachment
	* @throws NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	*/
	public TicketAttachment findByT_DD_Last(int type, Date deleteDate,
		com.liferay.portal.kernel.util.OrderByComparator<TicketAttachment> orderByComparator)
		throws NoSuchTicketAttachmentException;

	/**
	* Returns the last ticket attachment in the ordered set where type = &#63; and deleteDate &lt; &#63;.
	*
	* @param type the type
	* @param deleteDate the delete date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	*/
	public TicketAttachment fetchByT_DD_Last(int type, Date deleteDate,
		com.liferay.portal.kernel.util.OrderByComparator<TicketAttachment> orderByComparator);

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
	public TicketAttachment[] findByT_DD_PrevAndNext(long ticketAttachmentId,
		int type, Date deleteDate,
		com.liferay.portal.kernel.util.OrderByComparator<TicketAttachment> orderByComparator)
		throws NoSuchTicketAttachmentException;

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
	public java.util.List<TicketAttachment> findByT_DD(int[] types,
		Date deleteDate);

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
	public java.util.List<TicketAttachment> findByT_DD(int[] types,
		Date deleteDate, int start, int end);

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
	public java.util.List<TicketAttachment> findByT_DD(int[] types,
		Date deleteDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TicketAttachment> orderByComparator);

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
	public java.util.List<TicketAttachment> findByT_DD(int[] types,
		Date deleteDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TicketAttachment> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the ticket attachments where type = &#63; and deleteDate &lt; &#63; from the database.
	*
	* @param type the type
	* @param deleteDate the delete date
	*/
	public void removeByT_DD(int type, Date deleteDate);

	/**
	* Returns the number of ticket attachments where type = &#63; and deleteDate &lt; &#63;.
	*
	* @param type the type
	* @param deleteDate the delete date
	* @return the number of matching ticket attachments
	*/
	public int countByT_DD(int type, Date deleteDate);

	/**
	* Returns the number of ticket attachments where type = any &#63; and deleteDate &lt; &#63;.
	*
	* @param types the types
	* @param deleteDate the delete date
	* @return the number of matching ticket attachments
	*/
	public int countByT_DD(int[] types, Date deleteDate);

	/**
	* Returns all the ticket attachments where ticketEntryId = &#63; and type = &#63; and status = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param status the status
	* @return the matching ticket attachments
	*/
	public java.util.List<TicketAttachment> findByTEI_T_S(long ticketEntryId,
		int type, int status);

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
	public java.util.List<TicketAttachment> findByTEI_T_S(long ticketEntryId,
		int type, int status, int start, int end);

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
	public java.util.List<TicketAttachment> findByTEI_T_S(long ticketEntryId,
		int type, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TicketAttachment> orderByComparator);

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
	public java.util.List<TicketAttachment> findByTEI_T_S(long ticketEntryId,
		int type, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TicketAttachment> orderByComparator,
		boolean retrieveFromCache);

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
	public TicketAttachment findByTEI_T_S_First(long ticketEntryId, int type,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<TicketAttachment> orderByComparator)
		throws NoSuchTicketAttachmentException;

	/**
	* Returns the first ticket attachment in the ordered set where ticketEntryId = &#63; and type = &#63; and status = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	*/
	public TicketAttachment fetchByTEI_T_S_First(long ticketEntryId, int type,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<TicketAttachment> orderByComparator);

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
	public TicketAttachment findByTEI_T_S_Last(long ticketEntryId, int type,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<TicketAttachment> orderByComparator)
		throws NoSuchTicketAttachmentException;

	/**
	* Returns the last ticket attachment in the ordered set where ticketEntryId = &#63; and type = &#63; and status = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	*/
	public TicketAttachment fetchByTEI_T_S_Last(long ticketEntryId, int type,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<TicketAttachment> orderByComparator);

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
	public TicketAttachment[] findByTEI_T_S_PrevAndNext(
		long ticketAttachmentId, long ticketEntryId, int type, int status,
		com.liferay.portal.kernel.util.OrderByComparator<TicketAttachment> orderByComparator)
		throws NoSuchTicketAttachmentException;

	/**
	* Removes all the ticket attachments where ticketEntryId = &#63; and type = &#63; and status = &#63; from the database.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param status the status
	*/
	public void removeByTEI_T_S(long ticketEntryId, int type, int status);

	/**
	* Returns the number of ticket attachments where ticketEntryId = &#63; and type = &#63; and status = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param status the status
	* @return the number of matching ticket attachments
	*/
	public int countByTEI_T_S(long ticketEntryId, int type, int status);

	/**
	* Returns all the ticket attachments where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	*
	* @param userId the user ID
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param status the status
	* @return the matching ticket attachments
	*/
	public java.util.List<TicketAttachment> findByU_TEI_V_S(long userId,
		long ticketEntryId, int visibility, int status);

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
	public java.util.List<TicketAttachment> findByU_TEI_V_S(long userId,
		long ticketEntryId, int visibility, int status, int start, int end);

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
	public java.util.List<TicketAttachment> findByU_TEI_V_S(long userId,
		long ticketEntryId, int visibility, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TicketAttachment> orderByComparator);

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
	public java.util.List<TicketAttachment> findByU_TEI_V_S(long userId,
		long ticketEntryId, int visibility, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TicketAttachment> orderByComparator,
		boolean retrieveFromCache);

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
	public TicketAttachment findByU_TEI_V_S_First(long userId,
		long ticketEntryId, int visibility, int status,
		com.liferay.portal.kernel.util.OrderByComparator<TicketAttachment> orderByComparator)
		throws NoSuchTicketAttachmentException;

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
	public TicketAttachment fetchByU_TEI_V_S_First(long userId,
		long ticketEntryId, int visibility, int status,
		com.liferay.portal.kernel.util.OrderByComparator<TicketAttachment> orderByComparator);

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
	public TicketAttachment findByU_TEI_V_S_Last(long userId,
		long ticketEntryId, int visibility, int status,
		com.liferay.portal.kernel.util.OrderByComparator<TicketAttachment> orderByComparator)
		throws NoSuchTicketAttachmentException;

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
	public TicketAttachment fetchByU_TEI_V_S_Last(long userId,
		long ticketEntryId, int visibility, int status,
		com.liferay.portal.kernel.util.OrderByComparator<TicketAttachment> orderByComparator);

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
	public TicketAttachment[] findByU_TEI_V_S_PrevAndNext(
		long ticketAttachmentId, long userId, long ticketEntryId,
		int visibility, int status,
		com.liferay.portal.kernel.util.OrderByComparator<TicketAttachment> orderByComparator)
		throws NoSuchTicketAttachmentException;

	/**
	* Removes all the ticket attachments where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63; from the database.
	*
	* @param userId the user ID
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param status the status
	*/
	public void removeByU_TEI_V_S(long userId, long ticketEntryId,
		int visibility, int status);

	/**
	* Returns the number of ticket attachments where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	*
	* @param userId the user ID
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param status the status
	* @return the number of matching ticket attachments
	*/
	public int countByU_TEI_V_S(long userId, long ticketEntryId,
		int visibility, int status);

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
	public TicketAttachment findByTEI_FN_V_S(long ticketEntryId,
		java.lang.String fileName, int visibility, int status)
		throws NoSuchTicketAttachmentException;

	/**
	* Returns the ticket attachment where ticketEntryId = &#63; and fileName = &#63; and visibility = &#63; and status = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param ticketEntryId the ticket entry ID
	* @param fileName the file name
	* @param visibility the visibility
	* @param status the status
	* @return the matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	*/
	public TicketAttachment fetchByTEI_FN_V_S(long ticketEntryId,
		java.lang.String fileName, int visibility, int status);

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
	public TicketAttachment fetchByTEI_FN_V_S(long ticketEntryId,
		java.lang.String fileName, int visibility, int status,
		boolean retrieveFromCache);

	/**
	* Removes the ticket attachment where ticketEntryId = &#63; and fileName = &#63; and visibility = &#63; and status = &#63; from the database.
	*
	* @param ticketEntryId the ticket entry ID
	* @param fileName the file name
	* @param visibility the visibility
	* @param status the status
	* @return the ticket attachment that was removed
	*/
	public TicketAttachment removeByTEI_FN_V_S(long ticketEntryId,
		java.lang.String fileName, int visibility, int status)
		throws NoSuchTicketAttachmentException;

	/**
	* Returns the number of ticket attachments where ticketEntryId = &#63; and fileName = &#63; and visibility = &#63; and status = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param fileName the file name
	* @param visibility the visibility
	* @param status the status
	* @return the number of matching ticket attachments
	*/
	public int countByTEI_FN_V_S(long ticketEntryId, java.lang.String fileName,
		int visibility, int status);

	/**
	* Returns all the ticket attachments where ticketEntryId = &#63; and type = &#63; and visibility = &#63; and status = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param visibility the visibility
	* @param status the status
	* @return the matching ticket attachments
	*/
	public java.util.List<TicketAttachment> findByTEI_T_V_S(
		long ticketEntryId, int type, int visibility, int status);

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
	public java.util.List<TicketAttachment> findByTEI_T_V_S(
		long ticketEntryId, int type, int visibility, int status, int start,
		int end);

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
	public java.util.List<TicketAttachment> findByTEI_T_V_S(
		long ticketEntryId, int type, int visibility, int status, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<TicketAttachment> orderByComparator);

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
	public java.util.List<TicketAttachment> findByTEI_T_V_S(
		long ticketEntryId, int type, int visibility, int status, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<TicketAttachment> orderByComparator,
		boolean retrieveFromCache);

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
	public TicketAttachment findByTEI_T_V_S_First(long ticketEntryId, int type,
		int visibility, int status,
		com.liferay.portal.kernel.util.OrderByComparator<TicketAttachment> orderByComparator)
		throws NoSuchTicketAttachmentException;

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
	public TicketAttachment fetchByTEI_T_V_S_First(long ticketEntryId,
		int type, int visibility, int status,
		com.liferay.portal.kernel.util.OrderByComparator<TicketAttachment> orderByComparator);

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
	public TicketAttachment findByTEI_T_V_S_Last(long ticketEntryId, int type,
		int visibility, int status,
		com.liferay.portal.kernel.util.OrderByComparator<TicketAttachment> orderByComparator)
		throws NoSuchTicketAttachmentException;

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
	public TicketAttachment fetchByTEI_T_V_S_Last(long ticketEntryId, int type,
		int visibility, int status,
		com.liferay.portal.kernel.util.OrderByComparator<TicketAttachment> orderByComparator);

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
	public TicketAttachment[] findByTEI_T_V_S_PrevAndNext(
		long ticketAttachmentId, long ticketEntryId, int type, int visibility,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<TicketAttachment> orderByComparator)
		throws NoSuchTicketAttachmentException;

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
	public java.util.List<TicketAttachment> findByTEI_T_V_S(
		long ticketEntryId, int[] types, int[] visibilities, int status);

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
	public java.util.List<TicketAttachment> findByTEI_T_V_S(
		long ticketEntryId, int[] types, int[] visibilities, int status,
		int start, int end);

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
	public java.util.List<TicketAttachment> findByTEI_T_V_S(
		long ticketEntryId, int[] types, int[] visibilities, int status,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TicketAttachment> orderByComparator);

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
	public java.util.List<TicketAttachment> findByTEI_T_V_S(
		long ticketEntryId, int[] types, int[] visibilities, int status,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TicketAttachment> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the ticket attachments where ticketEntryId = &#63; and type = &#63; and visibility = &#63; and status = &#63; from the database.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param visibility the visibility
	* @param status the status
	*/
	public void removeByTEI_T_V_S(long ticketEntryId, int type, int visibility,
		int status);

	/**
	* Returns the number of ticket attachments where ticketEntryId = &#63; and type = &#63; and visibility = &#63; and status = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param visibility the visibility
	* @param status the status
	* @return the number of matching ticket attachments
	*/
	public int countByTEI_T_V_S(long ticketEntryId, int type, int visibility,
		int status);

	/**
	* Returns the number of ticket attachments where ticketEntryId = &#63; and type = any &#63; and visibility = any &#63; and status = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param types the types
	* @param visibilities the visibilities
	* @param status the status
	* @return the number of matching ticket attachments
	*/
	public int countByTEI_T_V_S(long ticketEntryId, int[] types,
		int[] visibilities, int status);

	/**
	* Caches the ticket attachment in the entity cache if it is enabled.
	*
	* @param ticketAttachment the ticket attachment
	*/
	public void cacheResult(TicketAttachment ticketAttachment);

	/**
	* Caches the ticket attachments in the entity cache if it is enabled.
	*
	* @param ticketAttachments the ticket attachments
	*/
	public void cacheResult(java.util.List<TicketAttachment> ticketAttachments);

	/**
	* Creates a new ticket attachment with the primary key. Does not add the ticket attachment to the database.
	*
	* @param ticketAttachmentId the primary key for the new ticket attachment
	* @return the new ticket attachment
	*/
	public TicketAttachment create(long ticketAttachmentId);

	/**
	* Removes the ticket attachment with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketAttachmentId the primary key of the ticket attachment
	* @return the ticket attachment that was removed
	* @throws NoSuchTicketAttachmentException if a ticket attachment with the primary key could not be found
	*/
	public TicketAttachment remove(long ticketAttachmentId)
		throws NoSuchTicketAttachmentException;

	public TicketAttachment updateImpl(TicketAttachment ticketAttachment);

	/**
	* Returns the ticket attachment with the primary key or throws a {@link NoSuchTicketAttachmentException} if it could not be found.
	*
	* @param ticketAttachmentId the primary key of the ticket attachment
	* @return the ticket attachment
	* @throws NoSuchTicketAttachmentException if a ticket attachment with the primary key could not be found
	*/
	public TicketAttachment findByPrimaryKey(long ticketAttachmentId)
		throws NoSuchTicketAttachmentException;

	/**
	* Returns the ticket attachment with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ticketAttachmentId the primary key of the ticket attachment
	* @return the ticket attachment, or <code>null</code> if a ticket attachment with the primary key could not be found
	*/
	public TicketAttachment fetchByPrimaryKey(long ticketAttachmentId);

	@Override
	public java.util.Map<java.io.Serializable, TicketAttachment> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the ticket attachments.
	*
	* @return the ticket attachments
	*/
	public java.util.List<TicketAttachment> findAll();

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
	public java.util.List<TicketAttachment> findAll(int start, int end);

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
	public java.util.List<TicketAttachment> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TicketAttachment> orderByComparator);

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
	public java.util.List<TicketAttachment> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TicketAttachment> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the ticket attachments from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of ticket attachments.
	*
	* @return the number of ticket attachments
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}