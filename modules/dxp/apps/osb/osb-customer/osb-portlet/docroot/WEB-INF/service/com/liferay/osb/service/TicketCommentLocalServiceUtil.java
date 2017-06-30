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
 * The utility for the ticket comment local service. This utility wraps {@link com.liferay.osb.service.impl.TicketCommentLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TicketCommentLocalService
 * @see com.liferay.osb.service.base.TicketCommentLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.TicketCommentLocalServiceImpl
 * @generated
 */
public class TicketCommentLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.TicketCommentLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the ticket comment to the database. Also notifies the appropriate model listeners.
	*
	* @param ticketComment the ticket comment
	* @return the ticket comment that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketComment addTicketComment(
		com.liferay.osb.model.TicketComment ticketComment)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addTicketComment(ticketComment);
	}

	/**
	* Creates a new ticket comment with the primary key. Does not add the ticket comment to the database.
	*
	* @param ticketCommentId the primary key for the new ticket comment
	* @return the new ticket comment
	*/
	public static com.liferay.osb.model.TicketComment createTicketComment(
		long ticketCommentId) {
		return getService().createTicketComment(ticketCommentId);
	}

	/**
	* Deletes the ticket comment with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketCommentId the primary key of the ticket comment
	* @return the ticket comment that was removed
	* @throws PortalException if a ticket comment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketComment deleteTicketComment(
		long ticketCommentId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteTicketComment(ticketCommentId);
	}

	/**
	* Deletes the ticket comment from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketComment the ticket comment
	* @return the ticket comment that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketComment deleteTicketComment(
		com.liferay.osb.model.TicketComment ticketComment)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteTicketComment(ticketComment);
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

	public static com.liferay.osb.model.TicketComment fetchTicketComment(
		long ticketCommentId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchTicketComment(ticketCommentId);
	}

	/**
	* Returns the ticket comment with the primary key.
	*
	* @param ticketCommentId the primary key of the ticket comment
	* @return the ticket comment
	* @throws PortalException if a ticket comment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketComment getTicketComment(
		long ticketCommentId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getTicketComment(ticketCommentId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the ticket comments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of ticket comments
	* @param end the upper bound of the range of ticket comments (not inclusive)
	* @return the range of ticket comments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TicketComment> getTicketComments(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getTicketComments(start, end);
	}

	/**
	* Returns the number of ticket comments.
	*
	* @return the number of ticket comments
	* @throws SystemException if a system exception occurred
	*/
	public static int getTicketCommentsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getTicketCommentsCount();
	}

	/**
	* Updates the ticket comment in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ticketComment the ticket comment
	* @return the ticket comment that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketComment updateTicketComment(
		com.liferay.osb.model.TicketComment ticketComment)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateTicketComment(ticketComment);
	}

	/**
	* Updates the ticket comment in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ticketComment the ticket comment
	* @param merge whether to merge the ticket comment with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the ticket comment that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketComment updateTicketComment(
		com.liferay.osb.model.TicketComment ticketComment, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateTicketComment(ticketComment, merge);
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

	public static com.liferay.osb.model.TicketComment addAwayMessageTicketComment(
		long userId, long ticketEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().addAwayMessageTicketComment(userId, ticketEntryId);
	}

	public static com.liferay.osb.model.TicketComment addTicketComment(
		long userId, long ticketEntryId, java.lang.String body, int type,
		int visibility, int status, long ticketCannedResponseId,
		int[] pendingTypes,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addTicketComment(userId, ticketEntryId, body, type,
			visibility, status, ticketCannedResponseId, pendingTypes,
			serviceContext);
	}

	public static com.liferay.osb.model.TicketComment deleteTicketComment(
		long userId, long ticketCommentId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteTicketComment(userId, ticketCommentId);
	}

	public static com.liferay.osb.model.TicketComment deleteTicketComment(
		long userId, com.liferay.osb.model.TicketComment ticketComment)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteTicketComment(userId, ticketComment);
	}

	public static com.liferay.osb.model.TicketComment fetchLastTicketComment(
		long userId, long ticketEntryId, int visibility, int status, int type,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .fetchLastTicketComment(userId, ticketEntryId, visibility,
			status, type, obc);
	}

	public static com.liferay.osb.model.TicketComment fetchLastTicketComment(
		long userId, long ticketEntryId, int visibility, int status,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .fetchLastTicketComment(userId, ticketEntryId, visibility,
			status, obc);
	}

	public static com.liferay.osb.model.TicketComment getLastTicketComment(
		long ticketEntryId, int visibility,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getLastTicketComment(ticketEntryId, visibility, obc);
	}

	public static int getOrganizationTicketCommentsCount(
		long[] organizationIds, long ticketEntryId, int visibility)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getOrganizationTicketCommentsCount(organizationIds,
			ticketEntryId, visibility);
	}

	public static java.util.List<com.liferay.osb.model.TicketComment> getTicketComments(
		long ticketEntryId, int[] visibilities, int[] statuses)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getTicketComments(ticketEntryId, visibilities, statuses);
	}

	public static java.util.List<com.liferay.osb.model.TicketComment> getTicketComments(
		long userId, long ticketEntryId, int[] visibilities, int[] statuses)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getTicketComments(userId, ticketEntryId, visibilities,
			statuses);
	}

	public static int getTicketCommentsCount(long ticketEntryId,
		int[] visibilities, int[] statuses)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getTicketCommentsCount(ticketEntryId, visibilities, statuses);
	}

	public static int getTicketCommentsCount(long userId, long ticketEntryId,
		int[] visibilities, int[] statuses)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getTicketCommentsCount(userId, ticketEntryId, visibilities,
			statuses);
	}

	public static int[] getUserVisibilities(long userId, long ticketEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getUserVisibilities(userId, ticketEntryId);
	}

	public static boolean hasVisibility(long userId, long ticketEntryId,
		int visibility)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().hasVisibility(userId, ticketEntryId, visibility);
	}

	public static void resetSolutionTicketComment(long ticketEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().resetSolutionTicketComment(ticketEntryId);
	}

	public static com.liferay.osb.model.TicketComment updateTicketComment(
		long userId, long ticketCommentId, long ticketEntryId,
		java.lang.String body, int visibility, int status,
		long ticketCannedResponseId, int[] pendingTypes,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateTicketComment(userId, ticketCommentId, ticketEntryId,
			body, visibility, status, ticketCannedResponseId, pendingTypes,
			serviceContext);
	}

	public static com.liferay.osb.model.TicketComment updateTicketCommentType(
		long ticketCommentId, int type)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().updateTicketCommentType(ticketCommentId, type);
	}

	public static void clearService() {
		_service = null;
	}

	public static TicketCommentLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					TicketCommentLocalService.class.getName());

			if (invokableLocalService instanceof TicketCommentLocalService) {
				_service = (TicketCommentLocalService)invokableLocalService;
			}
			else {
				_service = new TicketCommentLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(TicketCommentLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(TicketCommentLocalService service) {
	}

	private static TicketCommentLocalService _service;
}