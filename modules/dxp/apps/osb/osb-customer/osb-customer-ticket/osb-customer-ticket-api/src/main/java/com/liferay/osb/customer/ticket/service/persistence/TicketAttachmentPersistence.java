/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.customer.ticket.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.customer.ticket.exception.NoSuchTicketAttachmentException;
import com.liferay.osb.customer.ticket.model.TicketAttachment;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the ticket attachment service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.customer.ticket.service.persistence.impl.TicketAttachmentPersistenceImpl
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
	* Returns all the ticket attachments where zendeskTicketId = &#63;.
	*
	* @param zendeskTicketId the zendesk ticket ID
	* @return the matching ticket attachments
	*/
	public java.util.List<TicketAttachment> findByZendeskTicketId(
		long zendeskTicketId);

	/**
	* Returns a range of all the ticket attachments where zendeskTicketId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param zendeskTicketId the zendesk ticket ID
	* @param start the lower bound of the range of ticket attachments
	* @param end the upper bound of the range of ticket attachments (not inclusive)
	* @return the range of matching ticket attachments
	*/
	public java.util.List<TicketAttachment> findByZendeskTicketId(
		long zendeskTicketId, int start, int end);

	/**
	* Returns an ordered range of all the ticket attachments where zendeskTicketId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param zendeskTicketId the zendesk ticket ID
	* @param start the lower bound of the range of ticket attachments
	* @param end the upper bound of the range of ticket attachments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket attachments
	*/
	public java.util.List<TicketAttachment> findByZendeskTicketId(
		long zendeskTicketId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TicketAttachment> orderByComparator);

	/**
	* Returns an ordered range of all the ticket attachments where zendeskTicketId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param zendeskTicketId the zendesk ticket ID
	* @param start the lower bound of the range of ticket attachments
	* @param end the upper bound of the range of ticket attachments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching ticket attachments
	*/
	public java.util.List<TicketAttachment> findByZendeskTicketId(
		long zendeskTicketId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TicketAttachment> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first ticket attachment in the ordered set where zendeskTicketId = &#63;.
	*
	* @param zendeskTicketId the zendesk ticket ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket attachment
	* @throws NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	*/
	public TicketAttachment findByZendeskTicketId_First(long zendeskTicketId,
		com.liferay.portal.kernel.util.OrderByComparator<TicketAttachment> orderByComparator)
		throws NoSuchTicketAttachmentException;

	/**
	* Returns the first ticket attachment in the ordered set where zendeskTicketId = &#63;.
	*
	* @param zendeskTicketId the zendesk ticket ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	*/
	public TicketAttachment fetchByZendeskTicketId_First(long zendeskTicketId,
		com.liferay.portal.kernel.util.OrderByComparator<TicketAttachment> orderByComparator);

	/**
	* Returns the last ticket attachment in the ordered set where zendeskTicketId = &#63;.
	*
	* @param zendeskTicketId the zendesk ticket ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket attachment
	* @throws NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	*/
	public TicketAttachment findByZendeskTicketId_Last(long zendeskTicketId,
		com.liferay.portal.kernel.util.OrderByComparator<TicketAttachment> orderByComparator)
		throws NoSuchTicketAttachmentException;

	/**
	* Returns the last ticket attachment in the ordered set where zendeskTicketId = &#63;.
	*
	* @param zendeskTicketId the zendesk ticket ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	*/
	public TicketAttachment fetchByZendeskTicketId_Last(long zendeskTicketId,
		com.liferay.portal.kernel.util.OrderByComparator<TicketAttachment> orderByComparator);

	/**
	* Returns the ticket attachments before and after the current ticket attachment in the ordered set where zendeskTicketId = &#63;.
	*
	* @param ticketAttachmentId the primary key of the current ticket attachment
	* @param zendeskTicketId the zendesk ticket ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ticket attachment
	* @throws NoSuchTicketAttachmentException if a ticket attachment with the primary key could not be found
	*/
	public TicketAttachment[] findByZendeskTicketId_PrevAndNext(
		long ticketAttachmentId, long zendeskTicketId,
		com.liferay.portal.kernel.util.OrderByComparator<TicketAttachment> orderByComparator)
		throws NoSuchTicketAttachmentException;

	/**
	* Removes all the ticket attachments where zendeskTicketId = &#63; from the database.
	*
	* @param zendeskTicketId the zendesk ticket ID
	*/
	public void removeByZendeskTicketId(long zendeskTicketId);

	/**
	* Returns the number of ticket attachments where zendeskTicketId = &#63;.
	*
	* @param zendeskTicketId the zendesk ticket ID
	* @return the number of matching ticket attachments
	*/
	public int countByZendeskTicketId(long zendeskTicketId);

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
	public java.util.Set<String> getBadColumnNames();
}