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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link TicketFeedbackLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see TicketFeedbackLocalService
 * @generated
 */
@ProviderType
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
	*/
	@Override
	public com.liferay.osb.model.TicketFeedback addTicketFeedback(
		com.liferay.osb.model.TicketFeedback ticketFeedback) {
		return _ticketFeedbackLocalService.addTicketFeedback(ticketFeedback);
	}

	@Override
	public com.liferay.osb.model.TicketFeedback addTicketFeedback(long userId,
		long ticketEntryId, int subject, int satisfied)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketFeedbackLocalService.addTicketFeedback(userId,
			ticketEntryId, subject, satisfied);
	}

	/**
	* Creates a new ticket feedback with the primary key. Does not add the ticket feedback to the database.
	*
	* @param ticketFeedbackId the primary key for the new ticket feedback
	* @return the new ticket feedback
	*/
	@Override
	public com.liferay.osb.model.TicketFeedback createTicketFeedback(
		long ticketFeedbackId) {
		return _ticketFeedbackLocalService.createTicketFeedback(ticketFeedbackId);
	}

	/**
	* Deletes the ticket feedback from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketFeedback the ticket feedback
	* @return the ticket feedback that was removed
	*/
	@Override
	public com.liferay.osb.model.TicketFeedback deleteTicketFeedback(
		com.liferay.osb.model.TicketFeedback ticketFeedback) {
		return _ticketFeedbackLocalService.deleteTicketFeedback(ticketFeedback);
	}

	/**
	* Deletes the ticket feedback with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketFeedbackId the primary key of the ticket feedback
	* @return the ticket feedback that was removed
	* @throws PortalException if a ticket feedback with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.TicketFeedback deleteTicketFeedback(
		long ticketFeedbackId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketFeedbackLocalService.deleteTicketFeedback(ticketFeedbackId);
	}

	@Override
	public com.liferay.osb.model.TicketFeedback fetchFirstOpenTicketFeedback(
		long userId, long ticketEntryId, int subject) {
		return _ticketFeedbackLocalService.fetchFirstOpenTicketFeedback(userId,
			ticketEntryId, subject);
	}

	@Override
	public com.liferay.osb.model.TicketFeedback fetchTicketFeedback(
		long ticketFeedbackId) {
		return _ticketFeedbackLocalService.fetchTicketFeedback(ticketFeedbackId);
	}

	/**
	* Returns the ticket feedback with the primary key.
	*
	* @param ticketFeedbackId the primary key of the ticket feedback
	* @return the ticket feedback
	* @throws PortalException if a ticket feedback with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.TicketFeedback getTicketFeedback(
		long ticketFeedbackId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketFeedbackLocalService.getTicketFeedback(ticketFeedbackId);
	}

	/**
	* Updates the ticket feedback in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ticketFeedback the ticket feedback
	* @return the ticket feedback that was updated
	*/
	@Override
	public com.liferay.osb.model.TicketFeedback updateTicketFeedback(
		com.liferay.osb.model.TicketFeedback ticketFeedback) {
		return _ticketFeedbackLocalService.updateTicketFeedback(ticketFeedback);
	}

	@Override
	public com.liferay.osb.model.TicketFeedback updateTicketFeedback(
		long userId, long ticketFeedbackId, int satisfied, int answer1,
		int answer2, int answer3, int rating1, int rating2, int rating3,
		int rating4, java.lang.String comments)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketFeedbackLocalService.updateTicketFeedback(userId,
			ticketFeedbackId, satisfied, answer1, answer2, answer3, rating1,
			rating2, rating3, rating4, comments);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _ticketFeedbackLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _ticketFeedbackLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _ticketFeedbackLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketFeedbackLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketFeedbackLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of ticket feedbacks.
	*
	* @return the number of ticket feedbacks
	*/
	@Override
	public int getTicketFeedbacksCount() {
		return _ticketFeedbackLocalService.getTicketFeedbacksCount();
	}

	@Override
	public int searchCount(java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params) {
		return _ticketFeedbackLocalService.searchCount(keywords, params);
	}

	@Override
	public int searchCount(java.lang.String name, int createdGTDay,
		int createdGTMonth, int createdGTYear, int createdLTDay,
		int createdLTMonth, int createdLTYear, int modifiedGTDay,
		int modifiedGTMonth, int modifiedGTYear, int modifiedLTDay,
		int modifiedLTMonth, int modifiedLTYear, java.lang.Integer satisfied,
		java.lang.String comments, java.lang.Integer status,
		java.lang.Integer[] ratings1, java.lang.Integer[] ratings2,
		java.lang.Integer[] ratings3, java.lang.Integer[] ratings4,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andSearch) {
		return _ticketFeedbackLocalService.searchCount(name, createdGTDay,
			createdGTMonth, createdGTYear, createdLTDay, createdLTMonth,
			createdLTYear, modifiedGTDay, modifiedGTMonth, modifiedGTYear,
			modifiedLTDay, modifiedLTMonth, modifiedLTYear, satisfied,
			comments, status, ratings1, ratings2, ratings3, ratings4, params,
			andSearch);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _ticketFeedbackLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _ticketFeedbackLocalService.getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _ticketFeedbackLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _ticketFeedbackLocalService.dynamicQuery(dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _ticketFeedbackLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
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
	@Override
	public java.util.List<com.liferay.osb.model.TicketFeedback> getTicketFeedbacks(
		int start, int end) {
		return _ticketFeedbackLocalService.getTicketFeedbacks(start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.model.TicketFeedback> getTicketFeedbacks(
		long ticketEntryId, int subject) {
		return _ticketFeedbackLocalService.getTicketFeedbacks(ticketEntryId,
			subject);
	}

	@Override
	public java.util.List<com.liferay.osb.model.TicketFeedback> search(
		java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc) {
		return _ticketFeedbackLocalService.search(keywords, params, start, end,
			obc);
	}

	@Override
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
		com.liferay.portal.kernel.util.OrderByComparator obc) {
		return _ticketFeedbackLocalService.search(name, createdGTDay,
			createdGTMonth, createdGTYear, createdLTDay, createdLTMonth,
			createdLTYear, modifiedGTDay, modifiedGTMonth, modifiedGTYear,
			modifiedLTDay, modifiedLTMonth, modifiedLTYear, satisfied,
			comments, status, ratings1, ratings2, ratings3, ratings4, params,
			andSearch, start, end, obc);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _ticketFeedbackLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _ticketFeedbackLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public void sendCustomerNotifications() throws java.lang.Exception {
		_ticketFeedbackLocalService.sendCustomerNotifications();
	}

	@Override
	public void sendLiferayWorkerNotifications() throws java.lang.Exception {
		_ticketFeedbackLocalService.sendLiferayWorkerNotifications();
	}

	@Override
	public void sendSupportTeamNotifications() throws java.lang.Exception {
		_ticketFeedbackLocalService.sendSupportTeamNotifications();
	}

	@Override
	public TicketFeedbackLocalService getWrappedService() {
		return _ticketFeedbackLocalService;
	}

	@Override
	public void setWrappedService(
		TicketFeedbackLocalService ticketFeedbackLocalService) {
		_ticketFeedbackLocalService = ticketFeedbackLocalService;
	}

	private TicketFeedbackLocalService _ticketFeedbackLocalService;
}