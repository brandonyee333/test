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

package com.liferay.osb.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.service.BaseLocalService;
import com.liferay.portal.service.InvokableLocalService;
import com.liferay.portal.service.PersistedModelLocalService;

/**
 * The interface for the ticket attachment local service.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TicketAttachmentLocalServiceUtil
 * @see com.liferay.osb.service.base.TicketAttachmentLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.TicketAttachmentLocalServiceImpl
 * @generated
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface TicketAttachmentLocalService extends BaseLocalService,
	InvokableLocalService, PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TicketAttachmentLocalServiceUtil} to access the ticket attachment local service. Add custom service methods to {@link com.liferay.osb.service.impl.TicketAttachmentLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the ticket attachment to the database. Also notifies the appropriate model listeners.
	*
	* @param ticketAttachment the ticket attachment
	* @return the ticket attachment that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketAttachment addTicketAttachment(
		com.liferay.osb.model.TicketAttachment ticketAttachment)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Creates a new ticket attachment with the primary key. Does not add the ticket attachment to the database.
	*
	* @param ticketAttachmentId the primary key for the new ticket attachment
	* @return the new ticket attachment
	*/
	public com.liferay.osb.model.TicketAttachment createTicketAttachment(
		long ticketAttachmentId);

	/**
	* Deletes the ticket attachment with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketAttachmentId the primary key of the ticket attachment
	* @return the ticket attachment that was removed
	* @throws PortalException if a ticket attachment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketAttachment deleteTicketAttachment(
		long ticketAttachmentId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Deletes the ticket attachment from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketAttachment the ticket attachment
	* @return the ticket attachment that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketAttachment deleteTicketAttachment(
		com.liferay.osb.model.TicketAttachment ticketAttachment)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.osb.model.TicketAttachment fetchTicketAttachment(
		long ticketAttachmentId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the ticket attachment with the primary key.
	*
	* @param ticketAttachmentId the primary key of the ticket attachment
	* @return the ticket attachment
	* @throws PortalException if a ticket attachment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.osb.model.TicketAttachment getTicketAttachment(
		long ticketAttachmentId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.osb.model.TicketAttachment> getTicketAttachments(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of ticket attachments.
	*
	* @return the number of ticket attachments
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getTicketAttachmentsCount()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Updates the ticket attachment in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ticketAttachment the ticket attachment
	* @return the ticket attachment that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketAttachment updateTicketAttachment(
		com.liferay.osb.model.TicketAttachment ticketAttachment)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Updates the ticket attachment in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ticketAttachment the ticket attachment
	* @param merge whether to merge the ticket attachment with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the ticket attachment that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketAttachment updateTicketAttachment(
		com.liferay.osb.model.TicketAttachment ticketAttachment, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier();

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier);

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable;

	public com.liferay.osb.model.TicketAttachment addTicketAttachment(
		long userId, long ticketEntryId, long ticketSolutionId,
		java.lang.String fileName, long fileSize, int type, int visibility,
		java.lang.String fileRepositoryId, int status)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.osb.model.TicketAttachment> addTicketAttachments(
		long userId, long ticketEntryId, long ticketSolutionId,
		java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.File>> files,
		java.util.List<java.lang.Integer> types, int visibility, int status,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public boolean checkAvailability(long ticketAttachmentId,
		java.lang.String fileRepositoryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public void cleanTicketAttachments()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.TicketAttachment deleteTicketAttachment(
		long userId, long ticketAttachmentId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public void deleteTicketAttachment(long userId, long ticketEntryId, int type)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.TicketAttachment deleteTicketAttachment(
		long userId, com.liferay.osb.model.TicketAttachment ticketAttachment)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.osb.model.TicketAttachment fetchTicketAttachment(
		long ticketEntryId, int type)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.osb.model.TicketAttachment fetchTicketAttachment(
		long ticketEntryId, java.lang.String fileName, int visibility,
		int status) throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.io.InputStream getFileAsStream(
		com.liferay.osb.model.TicketAttachment ticketAttachment)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.osb.model.TicketAttachment> getTicketAttachments(
		java.util.Date createDate, int type)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.osb.model.TicketAttachment> getTicketAttachments(
		int[] types) throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.osb.model.TicketAttachment> getTicketAttachments(
		long ticketEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.osb.model.TicketAttachment> getTicketAttachments(
		long ticketEntryId, int[] visibilities, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.osb.model.TicketAttachment> getTicketAttachments(
		long ticketEntryId, int[] types, int[] visibilities)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.osb.model.TicketAttachment> getTicketAttachments(
		long ticketEntryId, int[] types, int[] visibilities, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.osb.model.TicketAttachment> getTicketAttachments(
		long ticketEntryId, long ticketSolutionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.osb.model.TicketAttachment> getTicketAttachments(
		long userId, long ticketEntryId, int visibility, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getTicketAttachmentsCount(long ticketEntryId, int[] visibilities)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getTicketAttachmentsCount(long ticketEntryId, int[] types,
		int[] visibilities)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.io.File getTicketAttachmentsZipFile(long ticketEntryId,
		int[] visibilities)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.TicketAttachment replicateTicketAttachment(
		long userId, long ticketAttachmentId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.TicketAttachment updateDeleteDate(
		long userId, long ticketAttachmentId, java.util.Date deleteDate)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public void updateExtractedText(
		com.liferay.osb.model.TicketAttachment ticketAttachment)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void updateStatus(com.liferay.portal.model.User user,
		java.util.List<com.liferay.osb.model.TicketAttachment> ticketAttachments,
		long ticketEntryId, int status,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.TicketAttachment updateTicketAttachment(
		long ticketAttachmentId, long ticketEntryId, int type, int visibility)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.TicketAttachment updateTicketAttachment(
		long ticketAttachmentId, long ticketEntryId, long ticketSolutionId,
		int type, int visibility)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.osb.model.TicketAttachment> updateTicketAttachments(
		java.util.List<java.lang.Long> ticketAttachmentIds, long ticketEntryId,
		java.util.List<java.lang.Integer> types,
		java.util.List<java.lang.Integer> visibilities)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;
}