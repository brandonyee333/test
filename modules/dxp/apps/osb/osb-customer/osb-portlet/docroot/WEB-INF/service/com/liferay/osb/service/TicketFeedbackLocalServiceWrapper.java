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

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link TicketFeedbackLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       TicketFeedbackLocalService
 * @generated
 */
public class TicketFeedbackLocalServiceWrapper
	implements TicketFeedbackLocalService,
		ServiceWrapper<TicketFeedbackLocalService> {
	public TicketFeedbackLocalServiceWrapper(
		TicketFeedbackLocalService ticketFeedbackLocalService) {
		_ticketFeedbackLocalService = ticketFeedbackLocalService;
	}

	/**
	* Adds the ticket feedback to the database. Also notifies the appropriate model listeners.
	*
	* @param ticketFeedback the ticket feedback
	* @return the ticket feedback that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketFeedback addTicketFeedback(
		com.liferay.osb.model.TicketFeedback ticketFeedback)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketFeedbackLocalService.addTicketFeedback(ticketFeedback);
	}

	/**
	* Creates a new ticket feedback with the primary key. Does not add the ticket feedback to the database.
	*
	* @param ticketFeedbackId the primary key for the new ticket feedback
	* @return the new ticket feedback
	*/
	public com.liferay.osb.model.TicketFeedback createTicketFeedback(
		long ticketFeedbackId) {
		return _ticketFeedbackLocalService.createTicketFeedback(ticketFeedbackId);
	}

	/**
	* Deletes the ticket feedback with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketFeedbackId the primary key of the ticket feedback
	* @return the ticket feedback that was removed
	* @throws PortalException if a ticket feedback with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketFeedback deleteTicketFeedback(
		long ticketFeedbackId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketFeedbackLocalService.deleteTicketFeedback(ticketFeedbackId);
	}

	/**
	* Deletes the ticket feedback from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketFeedback the ticket feedback
	* @return the ticket feedback that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketFeedback deleteTicketFeedback(
		com.liferay.osb.model.TicketFeedback ticketFeedback)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketFeedbackLocalService.deleteTicketFeedback(ticketFeedback);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _ticketFeedbackLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketFeedbackLocalService.dynamicQuery(dynamicQuery);
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
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketFeedbackLocalService.dynamicQuery(dynamicQuery, start, end);
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
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketFeedbackLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketFeedbackLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.osb.model.TicketFeedback fetchTicketFeedback(
		long ticketFeedbackId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketFeedbackLocalService.fetchTicketFeedback(ticketFeedbackId);
	}

	/**
	* Returns the ticket feedback with the primary key.
	*
	* @param ticketFeedbackId the primary key of the ticket feedback
	* @return the ticket feedback
	* @throws PortalException if a ticket feedback with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketFeedback getTicketFeedback(
		long ticketFeedbackId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketFeedbackLocalService.getTicketFeedback(ticketFeedbackId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketFeedbackLocalService.getPersistedModel(primaryKeyObj);
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
	public java.util.List<com.liferay.osb.model.TicketFeedback> getTicketFeedbacks(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketFeedbackLocalService.getTicketFeedbacks(start, end);
	}

	/**
	* Returns the number of ticket feedbacks.
	*
	* @return the number of ticket feedbacks
	* @throws SystemException if a system exception occurred
	*/
	public int getTicketFeedbacksCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketFeedbackLocalService.getTicketFeedbacksCount();
	}

	/**
	* Updates the ticket feedback in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ticketFeedback the ticket feedback
	* @return the ticket feedback that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketFeedback updateTicketFeedback(
		com.liferay.osb.model.TicketFeedback ticketFeedback)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketFeedbackLocalService.updateTicketFeedback(ticketFeedback);
	}

	/**
	* Updates the ticket feedback in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ticketFeedback the ticket feedback
	* @param merge whether to merge the ticket feedback with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the ticket feedback that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketFeedback updateTicketFeedback(
		com.liferay.osb.model.TicketFeedback ticketFeedback, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketFeedbackLocalService.updateTicketFeedback(ticketFeedback,
			merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _ticketFeedbackLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_ticketFeedbackLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _ticketFeedbackLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public com.liferay.osb.model.TicketFeedback addTicketFeedback(long userId,
		long ticketEntryId, int subject, int satisfied)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketFeedbackLocalService.addTicketFeedback(userId,
			ticketEntryId, subject, satisfied);
	}

	public com.liferay.osb.model.TicketFeedback fetchFirstOpenTicketFeedback(
		long userId, long ticketEntryId, int subject)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketFeedbackLocalService.fetchFirstOpenTicketFeedback(userId,
			ticketEntryId, subject);
	}

	public java.util.List<com.liferay.osb.model.TicketFeedback> getTicketFeedbacks(
		long ticketEntryId, int subject)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketFeedbackLocalService.getTicketFeedbacks(ticketEntryId,
			subject);
	}

	public java.util.List<com.liferay.osb.model.TicketFeedback> getTicketFeedbacks(
		long ticketEntryId, int subject, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketFeedbackLocalService.getTicketFeedbacks(ticketEntryId,
			subject, status);
	}

	public java.util.List<com.liferay.osb.model.TicketFeedback> search(
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
		return _ticketFeedbackLocalService.search(name, createdGTDay,
			createdGTMonth, createdGTYear, createdLTDay, createdLTMonth,
			createdLTYear, modifiedGTDay, modifiedGTMonth, modifiedGTYear,
			modifiedLTDay, modifiedLTMonth, modifiedLTYear, satisfied,
			comments, status, ratings1, ratings2, ratings3, ratings4, params,
			andSearch, start, end, obc);
	}

	public java.util.List<com.liferay.osb.model.TicketFeedback> search(
		java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketFeedbackLocalService.search(keywords, params, start, end,
			obc);
	}

	public int searchCount(java.lang.String name, int createdGTDay,
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
		return _ticketFeedbackLocalService.searchCount(name, createdGTDay,
			createdGTMonth, createdGTYear, createdLTDay, createdLTMonth,
			createdLTYear, modifiedGTDay, modifiedGTMonth, modifiedGTYear,
			modifiedLTDay, modifiedLTMonth, modifiedLTYear, satisfied,
			comments, status, ratings1, ratings2, ratings3, ratings4, params,
			andSearch);
	}

	public int searchCount(java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketFeedbackLocalService.searchCount(keywords, params);
	}

	public void sendCustomerNotifications() throws java.lang.Exception {
		_ticketFeedbackLocalService.sendCustomerNotifications();
	}

	public void sendLiferayWorkerNotifications() throws java.lang.Exception {
		_ticketFeedbackLocalService.sendLiferayWorkerNotifications();
	}

	public void sendSupportTeamNotifications() throws java.lang.Exception {
		_ticketFeedbackLocalService.sendSupportTeamNotifications();
	}

	public com.liferay.osb.model.TicketFeedback updateTicketFeedback(
		long userId, long ticketFeedbackId, int satisfied, int answer1,
		int answer2, int answer3, int rating1, int rating2, int rating3,
		int rating4, java.lang.String comments)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketFeedbackLocalService.updateTicketFeedback(userId,
			ticketFeedbackId, satisfied, answer1, answer2, answer3, rating1,
			rating2, rating3, rating4, comments);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public TicketFeedbackLocalService getWrappedTicketFeedbackLocalService() {
		return _ticketFeedbackLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedTicketFeedbackLocalService(
		TicketFeedbackLocalService ticketFeedbackLocalService) {
		_ticketFeedbackLocalService = ticketFeedbackLocalService;
	}

	public TicketFeedbackLocalService getWrappedService() {
		return _ticketFeedbackLocalService;
	}

	public void setWrappedService(
		TicketFeedbackLocalService ticketFeedbackLocalService) {
		_ticketFeedbackLocalService = ticketFeedbackLocalService;
	}

	private TicketFeedbackLocalService _ticketFeedbackLocalService;
}