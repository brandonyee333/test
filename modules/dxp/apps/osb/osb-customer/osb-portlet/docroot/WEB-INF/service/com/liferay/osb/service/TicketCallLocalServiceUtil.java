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
 * The utility for the ticket call local service. This utility wraps {@link com.liferay.osb.service.impl.TicketCallLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TicketCallLocalService
 * @see com.liferay.osb.service.base.TicketCallLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.TicketCallLocalServiceImpl
 * @generated
 */
public class TicketCallLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.TicketCallLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the ticket call to the database. Also notifies the appropriate model listeners.
	*
	* @param ticketCall the ticket call
	* @return the ticket call that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketCall addTicketCall(
		com.liferay.osb.model.TicketCall ticketCall)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addTicketCall(ticketCall);
	}

	/**
	* Creates a new ticket call with the primary key. Does not add the ticket call to the database.
	*
	* @param ticketCallId the primary key for the new ticket call
	* @return the new ticket call
	*/
	public static com.liferay.osb.model.TicketCall createTicketCall(
		long ticketCallId) {
		return getService().createTicketCall(ticketCallId);
	}

	/**
	* Deletes the ticket call with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketCallId the primary key of the ticket call
	* @return the ticket call that was removed
	* @throws PortalException if a ticket call with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketCall deleteTicketCall(
		long ticketCallId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteTicketCall(ticketCallId);
	}

	/**
	* Deletes the ticket call from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketCall the ticket call
	* @return the ticket call that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketCall deleteTicketCall(
		com.liferay.osb.model.TicketCall ticketCall)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteTicketCall(ticketCall);
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

	public static com.liferay.osb.model.TicketCall fetchTicketCall(
		long ticketCallId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchTicketCall(ticketCallId);
	}

	/**
	* Returns the ticket call with the primary key.
	*
	* @param ticketCallId the primary key of the ticket call
	* @return the ticket call
	* @throws PortalException if a ticket call with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketCall getTicketCall(
		long ticketCallId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getTicketCall(ticketCallId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the ticket calls.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of ticket calls
	* @param end the upper bound of the range of ticket calls (not inclusive)
	* @return the range of ticket calls
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TicketCall> getTicketCalls(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getTicketCalls(start, end);
	}

	/**
	* Returns the number of ticket calls.
	*
	* @return the number of ticket calls
	* @throws SystemException if a system exception occurred
	*/
	public static int getTicketCallsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getTicketCallsCount();
	}

	/**
	* Updates the ticket call in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ticketCall the ticket call
	* @return the ticket call that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketCall updateTicketCall(
		com.liferay.osb.model.TicketCall ticketCall)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateTicketCall(ticketCall);
	}

	/**
	* Updates the ticket call in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ticketCall the ticket call
	* @param merge whether to merge the ticket call with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the ticket call that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketCall updateTicketCall(
		com.liferay.osb.model.TicketCall ticketCall, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateTicketCall(ticketCall, merge);
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

	public static com.liferay.osb.model.TicketCall addTicketCall(long userId,
		long ticketEntryId, int type, int callDateMonth, int callDateDay,
		int callDateYear, int callDateHour, int callDateMinute,
		long callLength, java.lang.String customerName,
		java.lang.String customerContact, java.lang.String confirmation,
		java.lang.String instructions)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addTicketCall(userId, ticketEntryId, type, callDateMonth,
			callDateDay, callDateYear, callDateHour, callDateMinute,
			callLength, customerName, customerContact, confirmation,
			instructions);
	}

	public static void clearService() {
		_service = null;
	}

	public static TicketCallLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					TicketCallLocalService.class.getName());

			if (invokableLocalService instanceof TicketCallLocalService) {
				_service = (TicketCallLocalService)invokableLocalService;
			}
			else {
				_service = new TicketCallLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(TicketCallLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(TicketCallLocalService service) {
	}

	private static TicketCallLocalService _service;
}