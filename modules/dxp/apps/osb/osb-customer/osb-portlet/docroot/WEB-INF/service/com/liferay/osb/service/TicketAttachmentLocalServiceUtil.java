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

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.service.InvokableLocalService;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * Provides the local service utility for TicketAttachment. This utility wraps
 * {@link com.liferay.osb.service.impl.TicketAttachmentLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see TicketAttachmentLocalService
 * @see com.liferay.osb.service.base.TicketAttachmentLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.TicketAttachmentLocalServiceImpl
 * @generated
 */
@ProviderType
public class TicketAttachmentLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.TicketAttachmentLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static boolean checkAvailability(long ticketAttachmentId,
		java.lang.String fileRepositoryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .checkAvailability(ticketAttachmentId, fileRepositoryId);
	}

	/**
	* Adds the ticket attachment to the database. Also notifies the appropriate model listeners.
	*
	* @param ticketAttachment the ticket attachment
	* @return the ticket attachment that was added
	*/
	public static com.liferay.osb.model.TicketAttachment addTicketAttachment(
		com.liferay.osb.model.TicketAttachment ticketAttachment) {
		return getService().addTicketAttachment(ticketAttachment);
	}

	public static com.liferay.osb.model.TicketAttachment addTicketAttachment(
		long userId, long ticketEntryId, long ticketSolutionId,
		java.lang.String fileName, long fileSize, int type, int visibility,
		java.lang.String fileRepositoryId, int status)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addTicketAttachment(userId, ticketEntryId,
			ticketSolutionId, fileName, fileSize, type, visibility,
			fileRepositoryId, status);
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
	* Deletes the ticket attachment from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketAttachment the ticket attachment
	* @return the ticket attachment that was removed
	*/
	public static com.liferay.osb.model.TicketAttachment deleteTicketAttachment(
		com.liferay.osb.model.TicketAttachment ticketAttachment) {
		return getService().deleteTicketAttachment(ticketAttachment);
	}

	/**
	* Deletes the ticket attachment with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketAttachmentId the primary key of the ticket attachment
	* @return the ticket attachment that was removed
	* @throws PortalException if a ticket attachment with the primary key could not be found
	*/
	public static com.liferay.osb.model.TicketAttachment deleteTicketAttachment(
		long ticketAttachmentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteTicketAttachment(ticketAttachmentId);
	}

	public static com.liferay.osb.model.TicketAttachment deleteTicketAttachment(
		long userId, com.liferay.osb.model.TicketAttachment ticketAttachment)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteTicketAttachment(userId, ticketAttachment);
	}

	public static com.liferay.osb.model.TicketAttachment deleteTicketAttachment(
		long userId, long ticketAttachmentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteTicketAttachment(userId, ticketAttachmentId);
	}

	public static com.liferay.osb.model.TicketAttachment fetchTicketAttachment(
		long ticketAttachmentId) {
		return getService().fetchTicketAttachment(ticketAttachmentId);
	}

	public static com.liferay.osb.model.TicketAttachment fetchTicketAttachment(
		long ticketEntryId, int type) {
		return getService().fetchTicketAttachment(ticketEntryId, type);
	}

	public static com.liferay.osb.model.TicketAttachment fetchTicketAttachment(
		long ticketEntryId, java.lang.String fileName, int visibility,
		int status) {
		return getService()
				   .fetchTicketAttachment(ticketEntryId, fileName, visibility,
			status);
	}

	/**
	* Returns the ticket attachment with the primary key.
	*
	* @param ticketAttachmentId the primary key of the ticket attachment
	* @return the ticket attachment
	* @throws PortalException if a ticket attachment with the primary key could not be found
	*/
	public static com.liferay.osb.model.TicketAttachment getTicketAttachment(
		long ticketAttachmentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getTicketAttachment(ticketAttachmentId);
	}

	public static com.liferay.osb.model.TicketAttachment replicateTicketAttachment(
		long userId, long ticketAttachmentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().replicateTicketAttachment(userId, ticketAttachmentId);
	}

	public static com.liferay.osb.model.TicketAttachment updateDeleteDate(
		long userId, long ticketAttachmentId, java.util.Date deleteDate)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateDeleteDate(userId, ticketAttachmentId, deleteDate);
	}

	/**
	* Updates the ticket attachment in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ticketAttachment the ticket attachment
	* @return the ticket attachment that was updated
	*/
	public static com.liferay.osb.model.TicketAttachment updateTicketAttachment(
		com.liferay.osb.model.TicketAttachment ticketAttachment) {
		return getService().updateTicketAttachment(ticketAttachment);
	}

	public static com.liferay.osb.model.TicketAttachment updateTicketAttachment(
		long ticketAttachmentId, long ticketEntryId, int type, int visibility)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateTicketAttachment(ticketAttachmentId, ticketEntryId,
			type, visibility);
	}

	public static com.liferay.osb.model.TicketAttachment updateTicketAttachment(
		long ticketAttachmentId, long ticketEntryId, long ticketSolutionId,
		int type, int visibility)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateTicketAttachment(ticketAttachmentId, ticketEntryId,
			ticketSolutionId, type, visibility);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of ticket attachments.
	*
	* @return the number of ticket attachments
	*/
	public static int getTicketAttachmentsCount() {
		return getService().getTicketAttachmentsCount();
	}

	public static int getTicketAttachmentsCount(long ticketEntryId,
		int[] types, int[] visibilities) {
		return getService()
				   .getTicketAttachmentsCount(ticketEntryId, types, visibilities);
	}

	public static java.io.File getTicketAttachmentsZipFile(long ticketEntryId,
		int[] visibilities)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getTicketAttachmentsZipFile(ticketEntryId, visibilities);
	}

	public static java.io.InputStream getFileAsStream(
		com.liferay.osb.model.TicketAttachment ticketAttachment)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getFileAsStream(ticketAttachment);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static java.util.List<com.liferay.osb.model.TicketAttachment> addTicketAttachments(
		long userId, long ticketEntryId, long ticketSolutionId,
		java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.File>> files,
		java.util.List<java.lang.Integer> types, int visibility, int status,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addTicketAttachments(userId, ticketEntryId,
			ticketSolutionId, files, types, visibility, status, serviceContext);
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns a range of all the ticket attachments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ticket attachments
	* @param end the upper bound of the range of ticket attachments (not inclusive)
	* @return the range of ticket attachments
	*/
	public static java.util.List<com.liferay.osb.model.TicketAttachment> getTicketAttachments(
		int start, int end) {
		return getService().getTicketAttachments(start, end);
	}

	public static java.util.List<com.liferay.osb.model.TicketAttachment> getTicketAttachments(
		long ticketEntryId) {
		return getService().getTicketAttachments(ticketEntryId);
	}

	public static java.util.List<com.liferay.osb.model.TicketAttachment> getTicketAttachments(
		long ticketEntryId, int[] types, int[] visibilities, int status) {
		return getService()
				   .getTicketAttachments(ticketEntryId, types, visibilities,
			status);
	}

	public static java.util.List<com.liferay.osb.model.TicketAttachment> getTicketAttachments(
		long ticketEntryId, long ticketSolutionId) {
		return getService().getTicketAttachments(ticketEntryId, ticketSolutionId);
	}

	public static java.util.List<com.liferay.osb.model.TicketAttachment> getTicketAttachments(
		long userId, long ticketEntryId, int visibility, int status) {
		return getService()
				   .getTicketAttachments(userId, ticketEntryId, visibility,
			status);
	}

	public static java.util.List<com.liferay.osb.model.TicketAttachment> updateTicketAttachments(
		java.util.List<java.lang.Long> ticketAttachmentIds, long ticketEntryId,
		java.util.List<java.lang.Integer> types,
		java.util.List<java.lang.Integer> visibilities)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateTicketAttachments(ticketAttachmentIds, ticketEntryId,
			types, visibilities);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static void cleanTicketAttachments()
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().cleanTicketAttachments();
	}

	public static void deleteTicketAttachment(long userId, long ticketEntryId,
		int type) throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteTicketAttachment(userId, ticketEntryId, type);
	}

	public static void updateExtractedText(
		com.liferay.osb.model.TicketAttachment ticketAttachment) {
		getService().updateExtractedText(ticketAttachment);
	}

	public static void updateStatus(com.liferay.portal.kernel.model.User user,
		java.util.List<com.liferay.osb.model.TicketAttachment> ticketAttachments,
		long ticketEntryId, int status,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService()
			.updateStatus(user, ticketAttachments, ticketEntryId, status,
			serviceContext);
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

	private static TicketAttachmentLocalService _service;
}