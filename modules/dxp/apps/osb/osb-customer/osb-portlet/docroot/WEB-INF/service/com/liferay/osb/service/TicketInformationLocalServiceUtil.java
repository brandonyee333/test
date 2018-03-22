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
 * Provides the local service utility for TicketInformation. This utility wraps
 * {@link com.liferay.osb.service.impl.TicketInformationLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see TicketInformationLocalService
 * @see com.liferay.osb.service.base.TicketInformationLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.TicketInformationLocalServiceImpl
 * @generated
 */
@ProviderType
public class TicketInformationLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.TicketInformationLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the ticket information to the database. Also notifies the appropriate model listeners.
	*
	* @param ticketInformation the ticket information
	* @return the ticket information that was added
	*/
	public static com.liferay.osb.model.TicketInformation addTicketInformation(
		com.liferay.osb.model.TicketInformation ticketInformation) {
		return getService().addTicketInformation(ticketInformation);
	}

	public static com.liferay.osb.model.TicketInformation addTicketInformation(
		long ticketEntryId, long fieldId, java.lang.String data)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().addTicketInformation(ticketEntryId, fieldId, data);
	}

	/**
	* Creates a new ticket information with the primary key. Does not add the ticket information to the database.
	*
	* @param ticketInformationId the primary key for the new ticket information
	* @return the new ticket information
	*/
	public static com.liferay.osb.model.TicketInformation createTicketInformation(
		long ticketInformationId) {
		return getService().createTicketInformation(ticketInformationId);
	}

	/**
	* Deletes the ticket information from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketInformation the ticket information
	* @return the ticket information that was removed
	*/
	public static com.liferay.osb.model.TicketInformation deleteTicketInformation(
		com.liferay.osb.model.TicketInformation ticketInformation) {
		return getService().deleteTicketInformation(ticketInformation);
	}

	/**
	* Deletes the ticket information with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketInformationId the primary key of the ticket information
	* @return the ticket information that was removed
	* @throws PortalException if a ticket information with the primary key could not be found
	*/
	public static com.liferay.osb.model.TicketInformation deleteTicketInformation(
		long ticketInformationId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteTicketInformation(ticketInformationId);
	}

	public static com.liferay.osb.model.TicketInformation fetchTicketInformation(
		long ticketInformationId) {
		return getService().fetchTicketInformation(ticketInformationId);
	}

	/**
	* Returns the ticket information with the primary key.
	*
	* @param ticketInformationId the primary key of the ticket information
	* @return the ticket information
	* @throws PortalException if a ticket information with the primary key could not be found
	*/
	public static com.liferay.osb.model.TicketInformation getTicketInformation(
		long ticketInformationId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getTicketInformation(ticketInformationId);
	}

	/**
	* Updates the ticket information in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ticketInformation the ticket information
	* @return the ticket information that was updated
	*/
	public static com.liferay.osb.model.TicketInformation updateTicketInformation(
		com.liferay.osb.model.TicketInformation ticketInformation) {
		return getService().updateTicketInformation(ticketInformation);
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
	* Returns the number of ticket informations.
	*
	* @return the number of ticket informations
	*/
	public static int getTicketInformationsCount() {
		return getService().getTicketInformationsCount();
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	public static java.lang.String getData(long ticketEntryId, long fieldId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getData(ticketEntryId, fieldId);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.TicketInformationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.TicketInformationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static java.util.List<com.liferay.osb.model.TicketInformation> getTicketInformationList(
		long ticketEntryId) {
		return getService().getTicketInformationList(ticketEntryId);
	}

	/**
	* Returns a range of all the ticket informations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.TicketInformationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ticket informations
	* @param end the upper bound of the range of ticket informations (not inclusive)
	* @return the range of ticket informations
	*/
	public static java.util.List<com.liferay.osb.model.TicketInformation> getTicketInformations(
		int start, int end) {
		return getService().getTicketInformations(start, end);
	}

	public static java.util.List<com.liferay.osb.model.TicketInformation> updateTicketInformation(
		long ticketEntryId,
		java.util.Map<java.lang.Long, java.lang.String> fieldsMap)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().updateTicketInformation(ticketEntryId, fieldsMap);
	}

	public static java.util.List<com.liferay.osb.model.TicketInformation> updateTicketInformation(
		long userId, java.lang.String userName, long ticketEntryId,
		java.util.Map<java.lang.Long, java.lang.String> fieldsMap,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateTicketInformation(userId, userName, ticketEntryId,
			fieldsMap, serviceContext);
	}

	public static java.util.Map<java.lang.Long, java.lang.String> getFieldsMap(
		long ticketEntryId) {
		return getService().getFieldsMap(ticketEntryId);
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

	public static void clearService() {
		_service = null;
	}

	public static TicketInformationLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					TicketInformationLocalService.class.getName());

			if (invokableLocalService instanceof TicketInformationLocalService) {
				_service = (TicketInformationLocalService)invokableLocalService;
			}
			else {
				_service = new TicketInformationLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(TicketInformationLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	private static TicketInformationLocalService _service;
}