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
 * Provides the local service utility for TicketFeedback. This utility wraps
 * {@link com.liferay.osb.service.impl.TicketFeedbackLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see TicketFeedbackLocalService
 * @see com.liferay.osb.service.base.TicketFeedbackLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.TicketFeedbackLocalServiceImpl
 * @generated
 */
@ProviderType
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
	*/
	public static com.liferay.osb.model.TicketFeedback addTicketFeedback(
		com.liferay.osb.model.TicketFeedback ticketFeedback) {
		return getService().addTicketFeedback(ticketFeedback);
	}

	public static com.liferay.osb.model.TicketFeedback addTicketFeedback(
		long userId, long ticketEntryId, int subject, int satisfied)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addTicketFeedback(userId, ticketEntryId, subject, satisfied);
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
	* Deletes the ticket feedback from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketFeedback the ticket feedback
	* @return the ticket feedback that was removed
	*/
	public static com.liferay.osb.model.TicketFeedback deleteTicketFeedback(
		com.liferay.osb.model.TicketFeedback ticketFeedback) {
		return getService().deleteTicketFeedback(ticketFeedback);
	}

	/**
	* Deletes the ticket feedback with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketFeedbackId the primary key of the ticket feedback
	* @return the ticket feedback that was removed
	* @throws PortalException if a ticket feedback with the primary key could not be found
	*/
	public static com.liferay.osb.model.TicketFeedback deleteTicketFeedback(
		long ticketFeedbackId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteTicketFeedback(ticketFeedbackId);
	}

	public static com.liferay.osb.model.TicketFeedback fetchFirstOpenTicketFeedback(
		long userId, long ticketEntryId, int subject) {
		return getService()
				   .fetchFirstOpenTicketFeedback(userId, ticketEntryId, subject);
	}

	public static com.liferay.osb.model.TicketFeedback fetchTicketFeedback(
		long ticketFeedbackId) {
		return getService().fetchTicketFeedback(ticketFeedbackId);
	}

	/**
	* Returns the ticket feedback with the primary key.
	*
	* @param ticketFeedbackId the primary key of the ticket feedback
	* @return the ticket feedback
	* @throws PortalException if a ticket feedback with the primary key could not be found
	*/
	public static com.liferay.osb.model.TicketFeedback getTicketFeedback(
		long ticketFeedbackId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getTicketFeedback(ticketFeedbackId);
	}

	/**
	* Updates the ticket feedback in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ticketFeedback the ticket feedback
	* @return the ticket feedback that was updated
	*/
	public static com.liferay.osb.model.TicketFeedback updateTicketFeedback(
		com.liferay.osb.model.TicketFeedback ticketFeedback) {
		return getService().updateTicketFeedback(ticketFeedback);
	}

	public static com.liferay.osb.model.TicketFeedback updateTicketFeedback(
		long userId, long ticketFeedbackId, int satisfied, int answer1,
		int answer2, int answer3, int rating1, int rating2, int rating3,
		int rating4, java.lang.String comments)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateTicketFeedback(userId, ticketFeedbackId, satisfied,
			answer1, answer2, answer3, rating1, rating2, rating3, rating4,
			comments);
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
	* Returns the number of ticket feedbacks.
	*
	* @return the number of ticket feedbacks
	*/
	public static int getTicketFeedbacksCount() {
		return getService().getTicketFeedbacksCount();
	}

	public static int searchCount(java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params) {
		return getService().searchCount(keywords, params);
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
		boolean andSearch) {
		return getService()
				   .searchCount(name, createdGTDay, createdGTMonth,
			createdGTYear, createdLTDay, createdLTMonth, createdLTYear,
			modifiedGTDay, modifiedGTMonth, modifiedGTYear, modifiedLTDay,
			modifiedLTMonth, modifiedLTYear, satisfied, comments, status,
			ratings1, ratings2, ratings3, ratings4, params, andSearch);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.TicketFeedbackModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.TicketFeedbackModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Returns a range of all the ticket feedbacks.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.TicketFeedbackModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ticket feedbacks
	* @param end the upper bound of the range of ticket feedbacks (not inclusive)
	* @return the range of ticket feedbacks
	*/
	public static java.util.List<com.liferay.osb.model.TicketFeedback> getTicketFeedbacks(
		int start, int end) {
		return getService().getTicketFeedbacks(start, end);
	}

	public static java.util.List<com.liferay.osb.model.TicketFeedback> getTicketFeedbacks(
		long ticketEntryId, int subject) {
		return getService().getTicketFeedbacks(ticketEntryId, subject);
	}

	public static java.util.List<com.liferay.osb.model.TicketFeedback> search(
		java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc) {
		return getService().search(keywords, params, start, end, obc);
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
		com.liferay.portal.kernel.util.OrderByComparator obc) {
		return getService()
				   .search(name, createdGTDay, createdGTMonth, createdGTYear,
			createdLTDay, createdLTMonth, createdLTYear, modifiedGTDay,
			modifiedGTMonth, modifiedGTYear, modifiedLTDay, modifiedLTMonth,
			modifiedLTYear, satisfied, comments, status, ratings1, ratings2,
			ratings3, ratings4, params, andSearch, start, end, obc);
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

	private static TicketFeedbackLocalService _service;
}