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
 * Provides the local service utility for TicketSolution. This utility wraps
 * {@link com.liferay.osb.service.impl.TicketSolutionLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see TicketSolutionLocalService
 * @see com.liferay.osb.service.base.TicketSolutionLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.TicketSolutionLocalServiceImpl
 * @generated
 */
@ProviderType
public class TicketSolutionLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.TicketSolutionLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the ticket solution to the database. Also notifies the appropriate model listeners.
	*
	* @param ticketSolution the ticket solution
	* @return the ticket solution that was added
	*/
	public static com.liferay.osb.model.TicketSolution addTicketSolution(
		com.liferay.osb.model.TicketSolution ticketSolution) {
		return getService().addTicketSolution(ticketSolution);
	}

	public static com.liferay.osb.model.TicketSolution addTicketSolution(
		long userId, long ticketEntryId, java.lang.String summary,
		boolean useCustomerSummary, int issueType, java.lang.String solution,
		int type, boolean customerSpecific, boolean environmentSpecific,
		boolean versionSpecific, boolean reviewForKB, int status,
		int ticketEntrySubcomponent,
		java.lang.String ticketEntrySubcomponentCustom,
		java.util.List<java.lang.String> ticketLinkURLs,
		java.util.List<java.lang.Integer> ticketLinkTypes,
		java.util.List<com.liferay.osb.model.TicketAttachment> ticketAttachments)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addTicketSolution(userId, ticketEntryId, summary,
			useCustomerSummary, issueType, solution, type, customerSpecific,
			environmentSpecific, versionSpecific, reviewForKB, status,
			ticketEntrySubcomponent, ticketEntrySubcomponentCustom,
			ticketLinkURLs, ticketLinkTypes, ticketAttachments);
	}

	/**
	* Creates a new ticket solution with the primary key. Does not add the ticket solution to the database.
	*
	* @param ticketSolutionId the primary key for the new ticket solution
	* @return the new ticket solution
	*/
	public static com.liferay.osb.model.TicketSolution createTicketSolution(
		long ticketSolutionId) {
		return getService().createTicketSolution(ticketSolutionId);
	}

	/**
	* Deletes the ticket solution from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketSolution the ticket solution
	* @return the ticket solution that was removed
	*/
	public static com.liferay.osb.model.TicketSolution deleteTicketSolution(
		com.liferay.osb.model.TicketSolution ticketSolution) {
		return getService().deleteTicketSolution(ticketSolution);
	}

	/**
	* Deletes the ticket solution with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketSolutionId the primary key of the ticket solution
	* @return the ticket solution that was removed
	* @throws PortalException if a ticket solution with the primary key could not be found
	*/
	public static com.liferay.osb.model.TicketSolution deleteTicketSolution(
		long ticketSolutionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteTicketSolution(ticketSolutionId);
	}

	public static com.liferay.osb.model.TicketSolution fetchTicketSolution(
		long ticketSolutionId) {
		return getService().fetchTicketSolution(ticketSolutionId);
	}

	public static com.liferay.osb.model.TicketSolution getActiveTicketSolution(
		long ticketEntryId) {
		return getService().getActiveTicketSolution(ticketEntryId);
	}

	/**
	* Returns the ticket solution with the primary key.
	*
	* @param ticketSolutionId the primary key of the ticket solution
	* @return the ticket solution
	* @throws PortalException if a ticket solution with the primary key could not be found
	*/
	public static com.liferay.osb.model.TicketSolution getTicketSolution(
		long ticketSolutionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getTicketSolution(ticketSolutionId);
	}

	/**
	* Updates the ticket solution in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ticketSolution the ticket solution
	* @return the ticket solution that was updated
	*/
	public static com.liferay.osb.model.TicketSolution updateTicketSolution(
		com.liferay.osb.model.TicketSolution ticketSolution) {
		return getService().updateTicketSolution(ticketSolution);
	}

	public static com.liferay.osb.model.TicketSolution updateTicketSolution(
		long ticketSolutionId, long ticketEntryId, int status,
		long statusByUserId, java.lang.String statusMessage, int statusReason)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateTicketSolution(ticketSolutionId, ticketEntryId,
			status, statusByUserId, statusMessage, statusReason);
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
	* Returns the number of ticket solutions.
	*
	* @return the number of ticket solutions
	*/
	public static int getTicketSolutionsCount() {
		return getService().getTicketSolutionsCount();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.TicketSolutionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.TicketSolutionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Returns a range of all the ticket solutions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.TicketSolutionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ticket solutions
	* @param end the upper bound of the range of ticket solutions (not inclusive)
	* @return the range of ticket solutions
	*/
	public static java.util.List<com.liferay.osb.model.TicketSolution> getTicketSolutions(
		int start, int end) {
		return getService().getTicketSolutions(start, end);
	}

	public static java.util.List<com.liferay.osb.model.TicketSolution> getTicketSolutions(
		long ticketEntryId) {
		return getService().getTicketSolutions(ticketEntryId);
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

	public static void updateStatus(long ticketEntryId, int ticketEntryStatus,
		int resolution) {
		getService().updateStatus(ticketEntryId, ticketEntryStatus, resolution);
	}

	public static void clearService() {
		_service = null;
	}

	public static TicketSolutionLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					TicketSolutionLocalService.class.getName());

			if (invokableLocalService instanceof TicketSolutionLocalService) {
				_service = (TicketSolutionLocalService)invokableLocalService;
			}
			else {
				_service = new TicketSolutionLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(TicketSolutionLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	private static TicketSolutionLocalService _service;
}