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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * The utility for the ticket attachment local service. This utility wraps {@link com.liferay.osb.service.impl.TicketAttachmentLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TicketAttachmentLocalService
 * @see com.liferay.osb.service.base.TicketAttachmentLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.TicketAttachmentLocalServiceImpl
 * @generated
 */
public class TicketAttachmentLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.TicketAttachmentLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the ticket attachment to the database. Also notifies the appropriate model listeners.
	*
	* @param ticketAttachment the ticket attachment
	* @return the ticket attachment that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketAttachment addTicketAttachment(
		com.liferay.osb.model.TicketAttachment ticketAttachment)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addTicketAttachment(ticketAttachment);
	}

	/**
	* Creates a new ticket attachment with the primary key. Does not add the ticket attachment to the database.
	*
	* @param ticketAttachmentId the primary key for the new ticket attachment
	* @return the new ticket attachment
	*/
	public static com.liferay.osb.model.TicketAttachment createTicketAttachment(
		long ticketAttachmentId) {
		return getService().createTicketAttachment(ticketAttachmentId);
	}

	/**
	* Deletes the ticket attachment with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketAttachmentId the primary key of the ticket attachment
	* @return the ticket attachment that was removed
	* @throws PortalException if a ticket attachment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketAttachment deleteTicketAttachment(
		long ticketAttachmentId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteTicketAttachment(ticketAttachmentId);
	}

	/**
	* Deletes the ticket attachment from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketAttachment the ticket attachment
	* @return the ticket attachment that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketAttachment deleteTicketAttachment(
		com.liferay.osb.model.TicketAttachment ticketAttachment)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteTicketAttachment(ticketAttachment);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery);
	}

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
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

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
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	public static com.liferay.osb.model.TicketAttachment fetchTicketAttachment(
		long ticketAttachmentId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchTicketAttachment(ticketAttachmentId);
	}

	/**
	* Returns the ticket attachment with the primary key.
	*
	* @param ticketAttachmentId the primary key of the ticket attachment
	* @return the ticket attachment
	* @throws PortalException if a ticket attachment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketAttachment getTicketAttachment(
		long ticketAttachmentId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getTicketAttachment(ticketAttachmentId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
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
	public static java.util.List<com.liferay.osb.model.TicketAttachment> getTicketAttachments(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getTicketAttachments(start, end);
	}

	/**
	* Returns the number of ticket attachments.
	*
	* @return the number of ticket attachments
	* @throws SystemException if a system exception occurred
	*/
	public static int getTicketAttachmentsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getTicketAttachmentsCount();
	}

	/**
	* Updates the ticket attachment in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ticketAttachment the ticket attachment
	* @return the ticket attachment that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketAttachment updateTicketAttachment(
		com.liferay.osb.model.TicketAttachment ticketAttachment)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateTicketAttachment(ticketAttachment);
	}

	/**
	* Updates the ticket attachment in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ticketAttachment the ticket attachment
	* @param merge whether to merge the ticket attachment with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the ticket attachment that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketAttachment updateTicketAttachment(
		com.liferay.osb.model.TicketAttachment ticketAttachment, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateTicketAttachment(ticketAttachment, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	public static com.liferay.osb.model.TicketAttachment addTicketAttachment(
		long userId, long ticketEntryId, long ticketSolutionId,
		java.lang.String fileName, long fileSize, int type, int visibility,
		java.lang.String fileRepositoryId, int status)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addTicketAttachment(userId, ticketEntryId,
			ticketSolutionId, fileName, fileSize, type, visibility,
			fileRepositoryId, status);
	}

	public static java.util.List<com.liferay.osb.model.TicketAttachment> addTicketAttachments(
		long userId, long ticketEntryId, long ticketSolutionId,
		java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.File>> files,
		java.util.List<java.lang.Integer> types, int visibility, int status,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addTicketAttachments(userId, ticketEntryId,
			ticketSolutionId, files, types, visibility, status, serviceContext);
	}

	public static boolean checkAvailability(long ticketAttachmentId,
		java.lang.String fileRepositoryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .checkAvailability(ticketAttachmentId, fileRepositoryId);
	}

	public static void cleanTicketAttachments()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().cleanTicketAttachments();
	}

	public static com.liferay.osb.model.TicketAttachment deleteTicketAttachment(
		long userId, long ticketAttachmentId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteTicketAttachment(userId, ticketAttachmentId);
	}

	public static void deleteTicketAttachment(long userId, long ticketEntryId,
		int type)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteTicketAttachment(userId, ticketEntryId, type);
	}

	public static com.liferay.osb.model.TicketAttachment deleteTicketAttachment(
		long userId, com.liferay.osb.model.TicketAttachment ticketAttachment)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteTicketAttachment(userId, ticketAttachment);
	}

	public static com.liferay.osb.model.TicketAttachment fetchTicketAttachment(
		long ticketEntryId, int type)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchTicketAttachment(ticketEntryId, type);
	}

	public static com.liferay.osb.model.TicketAttachment fetchTicketAttachment(
		long ticketEntryId, java.lang.String fileName, int visibility,
		int status) throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .fetchTicketAttachment(ticketEntryId, fileName, visibility,
			status);
	}

	public static java.io.InputStream getFileAsStream(
		com.liferay.osb.model.TicketAttachment ticketAttachment)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getFileAsStream(ticketAttachment);
	}

	public static java.util.List<com.liferay.osb.model.TicketAttachment> getTicketAttachments(
		java.util.Date createDate, int type)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getTicketAttachments(createDate, type);
	}

	public static java.util.List<com.liferay.osb.model.TicketAttachment> getTicketAttachments(
		int[] types) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getTicketAttachments(types);
	}

	public static java.util.List<com.liferay.osb.model.TicketAttachment> getTicketAttachments(
		long ticketEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getTicketAttachments(ticketEntryId);
	}

	public static java.util.List<com.liferay.osb.model.TicketAttachment> getTicketAttachments(
		long ticketEntryId, int[] visibilities, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getTicketAttachments(ticketEntryId, visibilities, status);
	}

	public static java.util.List<com.liferay.osb.model.TicketAttachment> getTicketAttachments(
		long ticketEntryId, int[] types, int[] visibilities)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getTicketAttachments(ticketEntryId, types, visibilities);
	}

	public static java.util.List<com.liferay.osb.model.TicketAttachment> getTicketAttachments(
		long ticketEntryId, int[] types, int[] visibilities, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getTicketAttachments(ticketEntryId, types, visibilities,
			status);
	}

	public static java.util.List<com.liferay.osb.model.TicketAttachment> getTicketAttachments(
		long ticketEntryId, long ticketSolutionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getTicketAttachments(ticketEntryId, ticketSolutionId);
	}

	public static java.util.List<com.liferay.osb.model.TicketAttachment> getTicketAttachments(
		long userId, long ticketEntryId, int visibility, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getTicketAttachments(userId, ticketEntryId, visibility,
			status);
	}

	public static int getTicketAttachmentsCount(long ticketEntryId,
		int[] visibilities)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getTicketAttachmentsCount(ticketEntryId, visibilities);
	}

	public static int getTicketAttachmentsCount(long ticketEntryId,
		int[] types, int[] visibilities)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getTicketAttachmentsCount(ticketEntryId, types, visibilities);
	}

	public static java.io.File getTicketAttachmentsZipFile(long ticketEntryId,
		int[] visibilities)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getTicketAttachmentsZipFile(ticketEntryId, visibilities);
	}

	public static com.liferay.osb.model.TicketAttachment replicateTicketAttachment(
		long userId, long ticketAttachmentId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().replicateTicketAttachment(userId, ticketAttachmentId);
	}

	public static com.liferay.osb.model.TicketAttachment updateDeleteDate(
		long userId, long ticketAttachmentId, java.util.Date deleteDate)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateDeleteDate(userId, ticketAttachmentId, deleteDate);
	}

	public static void updateExtractedText(
		com.liferay.osb.model.TicketAttachment ticketAttachment)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().updateExtractedText(ticketAttachment);
	}

	public static void updateStatus(com.liferay.portal.model.User user,
		java.util.List<com.liferay.osb.model.TicketAttachment> ticketAttachments,
		long ticketEntryId, int status,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.updateStatus(user, ticketAttachments, ticketEntryId, status,
			serviceContext);
	}

	public static com.liferay.osb.model.TicketAttachment updateTicketAttachment(
		long ticketAttachmentId, long ticketEntryId, int type, int visibility)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateTicketAttachment(ticketAttachmentId, ticketEntryId,
			type, visibility);
	}

	public static com.liferay.osb.model.TicketAttachment updateTicketAttachment(
		long ticketAttachmentId, long ticketEntryId, long ticketSolutionId,
		int type, int visibility)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateTicketAttachment(ticketAttachmentId, ticketEntryId,
			ticketSolutionId, type, visibility);
	}

	public static java.util.List<com.liferay.osb.model.TicketAttachment> updateTicketAttachments(
		java.util.List<java.lang.Long> ticketAttachmentIds, long ticketEntryId,
		java.util.List<java.lang.Integer> types,
		java.util.List<java.lang.Integer> visibilities)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateTicketAttachments(ticketAttachmentIds, ticketEntryId,
			types, visibilities);
	}

	public static void clearService() {
		_service = null;
	}

	public static TicketAttachmentLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					TicketAttachmentLocalService.class.getName());

			if (invokableLocalService instanceof TicketAttachmentLocalService) {
				_service = (TicketAttachmentLocalService)invokableLocalService;
			}
			else {
				_service = new TicketAttachmentLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(TicketAttachmentLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(TicketAttachmentLocalService service) {
	}

	private static TicketAttachmentLocalService _service;
}