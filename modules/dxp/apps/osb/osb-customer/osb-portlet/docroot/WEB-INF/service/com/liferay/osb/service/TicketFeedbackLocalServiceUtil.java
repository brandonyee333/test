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
 * The utility for the ticket feedback local service. This utility wraps {@link com.liferay.osb.service.impl.TicketFeedbackLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TicketFeedbackLocalService
 * @see com.liferay.osb.service.base.TicketFeedbackLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.TicketFeedbackLocalServiceImpl
 * @generated
 */
public class TicketFeedbackLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.TicketFeedbackLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the ticket feedback to the database. Also notifies the appropriate model listeners.
	*
	* @param ticketFeedback the ticket feedback
	* @return the ticket feedback that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketFeedback addTicketFeedback(
		com.liferay.osb.model.TicketFeedback ticketFeedback)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addTicketFeedback(ticketFeedback);
	}

	/**
	* Creates a new ticket feedback with the primary key. Does not add the ticket feedback to the database.
	*
	* @param ticketFeedbackId the primary key for the new ticket feedback
	* @return the new ticket feedback
	*/
	public static com.liferay.osb.model.TicketFeedback createTicketFeedback(
		long ticketFeedbackId) {
		return getService().createTicketFeedback(ticketFeedbackId);
	}

	/**
	* Deletes the ticket feedback with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketFeedbackId the primary key of the ticket feedback
	* @return the ticket feedback that was removed
	* @throws PortalException if a ticket feedback with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketFeedback deleteTicketFeedback(
		long ticketFeedbackId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteTicketFeedback(ticketFeedbackId);
	}

	/**
	* Deletes the ticket feedback from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketFeedback the ticket feedback
	* @return the ticket feedback that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketFeedback deleteTicketFeedback(
		com.liferay.osb.model.TicketFeedback ticketFeedback)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteTicketFeedback(ticketFeedback);
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

	public static com.liferay.osb.model.TicketFeedback fetchTicketFeedback(
		long ticketFeedbackId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchTicketFeedback(ticketFeedbackId);
	}

	/**
	* Returns the ticket feedback with the primary key.
	*
	* @param ticketFeedbackId the primary key of the ticket feedback
	* @return the ticket feedback
	* @throws PortalException if a ticket feedback with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketFeedback getTicketFeedback(
		long ticketFeedbackId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getTicketFeedback(ticketFeedbackId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the ticket feedbacks.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of ticket feedbacks
	* @param end the upper bound of the range of ticket feedbacks (not inclusive)
	* @return the range of ticket feedbacks
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TicketFeedback> getTicketFeedbacks(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getTicketFeedbacks(start, end);
	}

	/**
	* Returns the number of ticket feedbacks.
	*
	* @return the number of ticket feedbacks
	* @throws SystemException if a system exception occurred
	*/
	public static int getTicketFeedbacksCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getTicketFeedbacksCount();
	}

	/**
	* Updates the ticket feedback in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ticketFeedback the ticket feedback
	* @return the ticket feedback that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketFeedback updateTicketFeedback(
		com.liferay.osb.model.TicketFeedback ticketFeedback)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateTicketFeedback(ticketFeedback);
	}

	/**
	* Updates the ticket feedback in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ticketFeedback the ticket feedback
	* @param merge whether to merge the ticket feedback with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the ticket feedback that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketFeedback updateTicketFeedback(
		com.liferay.osb.model.TicketFeedback ticketFeedback, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateTicketFeedback(ticketFeedback, merge);
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

	public static com.liferay.osb.model.TicketFeedback addTicketFeedback(
		long userId, long ticketEntryId, int subject, int satisfied)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addTicketFeedback(userId, ticketEntryId, subject, satisfied);
	}

	public static com.liferay.osb.model.TicketFeedback fetchFirstOpenTicketFeedback(
		long userId, long ticketEntryId, int subject)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .fetchFirstOpenTicketFeedback(userId, ticketEntryId, subject);
	}

	public static java.util.List<com.liferay.osb.model.TicketFeedback> getTicketFeedbacks(
		long ticketEntryId, int subject)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getTicketFeedbacks(ticketEntryId, subject);
	}

	public static java.util.List<com.liferay.osb.model.TicketFeedback> getTicketFeedbacks(
		long ticketEntryId, int subject, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getTicketFeedbacks(ticketEntryId, subject, status);
	}

	public static java.util.List<com.liferay.osb.model.TicketFeedback> search(
		java.lang.String name, int createdGTDay, int createdGTMonth,
		int createdGTYear, int createdLTDay, int createdLTMonth,
		int createdLTYear, int modifiedGTDay, int modifiedGTMonth,
		int modifiedGTYear, int modifiedLTDay, int modifiedLTMonth,
		int modifiedLTYear, java.lang.Integer satisfied,
		java.lang.String comments, java.lang.Integer status,
		java.lang.Integer[] ratings1, java.lang.Integer[] ratings2,
		java.lang.Integer[] ratings3, java.lang.Integer[] ratings4,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andSearch, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .search(name, createdGTDay, createdGTMonth, createdGTYear,
			createdLTDay, createdLTMonth, createdLTYear, modifiedGTDay,
			modifiedGTMonth, modifiedGTYear, modifiedLTDay, modifiedLTMonth,
			modifiedLTYear, satisfied, comments, status, ratings1, ratings2,
			ratings3, ratings4, params, andSearch, start, end, obc);
	}

	public static java.util.List<com.liferay.osb.model.TicketFeedback> search(
		java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().search(keywords, params, start, end, obc);
	}

	public static int searchCount(java.lang.String name, int createdGTDay,
		int createdGTMonth, int createdGTYear, int createdLTDay,
		int createdLTMonth, int createdLTYear, int modifiedGTDay,
		int modifiedGTMonth, int modifiedGTYear, int modifiedLTDay,
		int modifiedLTMonth, int modifiedLTYear, java.lang.Integer satisfied,
		java.lang.String comments, java.lang.Integer status,
		java.lang.Integer[] ratings1, java.lang.Integer[] ratings2,
		java.lang.Integer[] ratings3, java.lang.Integer[] ratings4,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andSearch)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .searchCount(name, createdGTDay, createdGTMonth,
			createdGTYear, createdLTDay, createdLTMonth, createdLTYear,
			modifiedGTDay, modifiedGTMonth, modifiedGTYear, modifiedLTDay,
			modifiedLTMonth, modifiedLTYear, satisfied, comments, status,
			ratings1, ratings2, ratings3, ratings4, params, andSearch);
	}

	public static int searchCount(java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().searchCount(keywords, params);
	}

	public static void sendCustomerNotifications() throws java.lang.Exception {
		getService().sendCustomerNotifications();
	}

	public static void sendLiferayWorkerNotifications()
		throws java.lang.Exception {
		getService().sendLiferayWorkerNotifications();
	}

	public static void sendSupportTeamNotifications()
		throws java.lang.Exception {
		getService().sendSupportTeamNotifications();
	}

	public static com.liferay.osb.model.TicketFeedback updateTicketFeedback(
		long userId, long ticketFeedbackId, int satisfied, int answer1,
		int answer2, int answer3, int rating1, int rating2, int rating3,
		int rating4, java.lang.String comments)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateTicketFeedback(userId, ticketFeedbackId, satisfied,
			answer1, answer2, answer3, rating1, rating2, rating3, rating4,
			comments);
	}

	public static void clearService() {
		_service = null;
	}

	public static TicketFeedbackLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					TicketFeedbackLocalService.class.getName());

			if (invokableLocalService instanceof TicketFeedbackLocalService) {
				_service = (TicketFeedbackLocalService)invokableLocalService;
			}
			else {
				_service = new TicketFeedbackLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(TicketFeedbackLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(TicketFeedbackLocalService service) {
	}

	private static TicketFeedbackLocalService _service;
}