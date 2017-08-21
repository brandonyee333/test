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
 * Provides the local service utility for TicketLink. This utility wraps
 * {@link com.liferay.osb.service.impl.TicketLinkLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see TicketLinkLocalService
 * @see com.liferay.osb.service.base.TicketLinkLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.TicketLinkLocalServiceImpl
 * @generated
 */
@ProviderType
public class TicketLinkLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.TicketLinkLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the ticket link to the database. Also notifies the appropriate model listeners.
	*
	* @param ticketLink the ticket link
	* @return the ticket link that was added
	*/
	public static com.liferay.osb.model.TicketLink addTicketLink(
		com.liferay.osb.model.TicketLink ticketLink) {
		return getService().addTicketLink(ticketLink);
	}

	public static com.liferay.osb.model.TicketLink addTicketLink(long userId,
		long ticketEntryId, long ticketSolutionId, java.lang.String[] urls,
		java.lang.Integer[] types, int visibility,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addTicketLink(userId, ticketEntryId, ticketSolutionId,
			urls, types, visibility, serviceContext);
	}

	/**
	* Creates a new ticket link with the primary key. Does not add the ticket link to the database.
	*
	* @param ticketLinkId the primary key for the new ticket link
	* @return the new ticket link
	*/
	public static com.liferay.osb.model.TicketLink createTicketLink(
		long ticketLinkId) {
		return getService().createTicketLink(ticketLinkId);
	}

	/**
	* Deletes the ticket link from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketLink the ticket link
	* @return the ticket link that was removed
	*/
	public static com.liferay.osb.model.TicketLink deleteTicketLink(
		com.liferay.osb.model.TicketLink ticketLink) {
		return getService().deleteTicketLink(ticketLink);
	}

	/**
	* Deletes the ticket link with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketLinkId the primary key of the ticket link
	* @return the ticket link that was removed
	* @throws PortalException if a ticket link with the primary key could not be found
	*/
	public static com.liferay.osb.model.TicketLink deleteTicketLink(
		long ticketLinkId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteTicketLink(ticketLinkId);
	}

	public static com.liferay.osb.model.TicketLink fetchTicketLink(
		long ticketLinkId) {
		return getService().fetchTicketLink(ticketLinkId);
	}

	/**
	* Returns the ticket link with the primary key.
	*
	* @param ticketLinkId the primary key of the ticket link
	* @return the ticket link
	* @throws PortalException if a ticket link with the primary key could not be found
	*/
	public static com.liferay.osb.model.TicketLink getTicketLink(
		long ticketLinkId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getTicketLink(ticketLinkId);
	}

	/**
	* Updates the ticket link in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ticketLink the ticket link
	* @return the ticket link that was updated
	*/
	public static com.liferay.osb.model.TicketLink updateTicketLink(
		com.liferay.osb.model.TicketLink ticketLink) {
		return getService().updateTicketLink(ticketLink);
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
	* Returns the number of ticket links.
	*
	* @return the number of ticket links
	*/
	public static int getTicketLinksCount() {
		return getService().getTicketLinksCount();
	}

	public static int getTicketLinksCount(long ticketEntryId, int[] visibilities) {
		return getService().getTicketLinksCount(ticketEntryId, visibilities);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.TicketLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.TicketLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Returns a range of all the ticket links.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.TicketLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ticket links
	* @param end the upper bound of the range of ticket links (not inclusive)
	* @return the range of ticket links
	*/
	public static java.util.List<com.liferay.osb.model.TicketLink> getTicketLinks(
		int start, int end) {
		return getService().getTicketLinks(start, end);
	}

	public static java.util.List<com.liferay.osb.model.TicketLink> getTicketLinks(
		long ticketEntryId, int[] visibilities) {
		return getService().getTicketLinks(ticketEntryId, visibilities);
	}

	public static java.util.List<com.liferay.osb.model.TicketLink> getTicketLinks(
		long ticketEntryId, long ticketSolutionId) {
		return getService().getTicketLinks(ticketEntryId, ticketSolutionId);
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

	public static void deleteTicketLink(long userId,
		com.liferay.osb.model.TicketLink ticketLink)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteTicketLink(userId, ticketLink);
	}

	public static void deleteTicketLink(long userId, long ticketLinkId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteTicketLink(userId, ticketLinkId);
	}

	public static void clearService() {
		_service = null;
	}

	public static TicketLinkLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					TicketLinkLocalService.class.getName());

			if (invokableLocalService instanceof TicketLinkLocalService) {
				_service = (TicketLinkLocalService)invokableLocalService;
			}
			else {
				_service = new TicketLinkLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(TicketLinkLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	private static TicketLinkLocalService _service;
}