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
 * Provides the local service utility for TicketFlag. This utility wraps
 * {@link com.liferay.osb.service.impl.TicketFlagLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see TicketFlagLocalService
 * @see com.liferay.osb.service.base.TicketFlagLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.TicketFlagLocalServiceImpl
 * @generated
 */
@ProviderType
public class TicketFlagLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.TicketFlagLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static boolean hasTicketFlag(long ticketEntryId, int type, int flag) {
		return getService().hasTicketFlag(ticketEntryId, type, flag);
	}

	public static boolean hasTicketFlag(long userId, long accountEntryId,
		long ticketEntryId, int type, int flag) {
		return getService()
				   .hasTicketFlag(userId, accountEntryId, ticketEntryId, type,
			flag);
	}

	/**
	* Adds the ticket flag to the database. Also notifies the appropriate model listeners.
	*
	* @param ticketFlag the ticket flag
	* @return the ticket flag that was added
	*/
	public static com.liferay.osb.model.TicketFlag addTicketFlag(
		com.liferay.osb.model.TicketFlag ticketFlag) {
		return getService().addTicketFlag(ticketFlag);
	}

	/**
	* Creates a new ticket flag with the primary key. Does not add the ticket flag to the database.
	*
	* @param ticketFlagId the primary key for the new ticket flag
	* @return the new ticket flag
	*/
	public static com.liferay.osb.model.TicketFlag createTicketFlag(
		long ticketFlagId) {
		return getService().createTicketFlag(ticketFlagId);
	}

	/**
	* Deletes the ticket flag from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketFlag the ticket flag
	* @return the ticket flag that was removed
	*/
	public static com.liferay.osb.model.TicketFlag deleteTicketFlag(
		com.liferay.osb.model.TicketFlag ticketFlag) {
		return getService().deleteTicketFlag(ticketFlag);
	}

	/**
	* Deletes the ticket flag with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketFlagId the primary key of the ticket flag
	* @return the ticket flag that was removed
	* @throws PortalException if a ticket flag with the primary key could not be found
	*/
	public static com.liferay.osb.model.TicketFlag deleteTicketFlag(
		long ticketFlagId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteTicketFlag(ticketFlagId);
	}

	public static com.liferay.osb.model.TicketFlag fetchTicketFlag(
		long ticketFlagId) {
		return getService().fetchTicketFlag(ticketFlagId);
	}

	/**
	* Returns the ticket flag with the primary key.
	*
	* @param ticketFlagId the primary key of the ticket flag
	* @return the ticket flag
	* @throws PortalException if a ticket flag with the primary key could not be found
	*/
	public static com.liferay.osb.model.TicketFlag getTicketFlag(
		long ticketFlagId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getTicketFlag(ticketFlagId);
	}

	/**
	* Updates the ticket flag in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ticketFlag the ticket flag
	* @return the ticket flag that was updated
	*/
	public static com.liferay.osb.model.TicketFlag updateTicketFlag(
		com.liferay.osb.model.TicketFlag ticketFlag) {
		return getService().updateTicketFlag(ticketFlag);
	}

	public static com.liferay.osb.model.TicketFlag updateTicketFlag(
		long userId, long accountEntryId, long ticketEntryId, int type, int flag)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateTicketFlag(userId, accountEntryId, ticketEntryId,
			type, flag);
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
	* Returns the number of ticket flags.
	*
	* @return the number of ticket flags
	*/
	public static int getTicketFlagsCount() {
		return getService().getTicketFlagsCount();
	}

	public static int getTicketFlagsCount(long ticketEntryId, int type, int flag) {
		return getService().getTicketFlagsCount(ticketEntryId, type, flag);
	}

	public static int[] getTicketFlagTypes(long ticketEntryId, int[] types,
		int flag) {
		return getService().getTicketFlagTypes(ticketEntryId, types, flag);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.TicketFlagModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.TicketFlagModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Returns a range of all the ticket flags.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.TicketFlagModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ticket flags
	* @param end the upper bound of the range of ticket flags (not inclusive)
	* @return the range of ticket flags
	*/
	public static java.util.List<com.liferay.osb.model.TicketFlag> getTicketFlags(
		int start, int end) {
		return getService().getTicketFlags(start, end);
	}

	public static java.util.List<com.liferay.osb.model.TicketFlag> getTicketFlags(
		long ticketEntryId, int type, int flag) {
		return getService().getTicketFlags(ticketEntryId, type, flag);
	}

	public static java.util.List<com.liferay.osb.model.TicketFlag> getTicketFlags(
		long ticketEntryId, int[] types, int flag) {
		return getService().getTicketFlags(ticketEntryId, types, flag);
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

	public static void deleteTicketFlag(long userId, long accountEntryId,
		long ticketEntryId, int type)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService()
			.deleteTicketFlag(userId, accountEntryId, ticketEntryId, type);
	}

	public static void deleteTicketFlags(long ticketEntryId, int type, int flag) {
		getService().deleteTicketFlags(ticketEntryId, type, flag);
	}

	public static void clearService() {
		_service = null;
	}

	public static TicketFlagLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					TicketFlagLocalService.class.getName());

			if (invokableLocalService instanceof TicketFlagLocalService) {
				_service = (TicketFlagLocalService)invokableLocalService;
			}
			else {
				_service = new TicketFlagLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(TicketFlagLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	private static TicketFlagLocalService _service;
}