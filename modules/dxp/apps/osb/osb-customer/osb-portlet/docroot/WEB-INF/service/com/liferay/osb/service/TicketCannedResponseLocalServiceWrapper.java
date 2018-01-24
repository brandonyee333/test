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
 * Provides a wrapper for {@link TicketCannedResponseLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see TicketCannedResponseLocalService
 * @generated
 */
@ProviderType
public class TicketCannedResponseLocalServiceWrapper
	implements TicketCannedResponseLocalService,
		ServiceWrapper<TicketCannedResponseLocalService> {
	public TicketCannedResponseLocalServiceWrapper(
		TicketCannedResponseLocalService ticketCannedResponseLocalService) {
		_ticketCannedResponseLocalService = ticketCannedResponseLocalService;
	}

	@Override
	public com.liferay.osb.model.TicketCannedResponse addTicketCannedResponse(
		long userId, java.lang.String defaultLanguageId, java.lang.String name,
		java.lang.String content)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketCannedResponseLocalService.addTicketCannedResponse(userId,
			defaultLanguageId, name, content);
	}

	/**
	* Adds the ticket canned response to the database. Also notifies the appropriate model listeners.
	*
	* @param ticketCannedResponse the ticket canned response
	* @return the ticket canned response that was added
	*/
	@Override
	public com.liferay.osb.model.TicketCannedResponse addTicketCannedResponse(
		com.liferay.osb.model.TicketCannedResponse ticketCannedResponse) {
		return _ticketCannedResponseLocalService.addTicketCannedResponse(ticketCannedResponse);
	}

	/**
	* Creates a new ticket canned response with the primary key. Does not add the ticket canned response to the database.
	*
	* @param ticketCannedResponseId the primary key for the new ticket canned response
	* @return the new ticket canned response
	*/
	@Override
	public com.liferay.osb.model.TicketCannedResponse createTicketCannedResponse(
		long ticketCannedResponseId) {
		return _ticketCannedResponseLocalService.createTicketCannedResponse(ticketCannedResponseId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketCannedResponseLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the ticket canned response with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketCannedResponseId the primary key of the ticket canned response
	* @return the ticket canned response that was removed
	* @throws PortalException if a ticket canned response with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.TicketCannedResponse deleteTicketCannedResponse(
		long ticketCannedResponseId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketCannedResponseLocalService.deleteTicketCannedResponse(ticketCannedResponseId);
	}

	/**
	* Deletes the ticket canned response from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketCannedResponse the ticket canned response
	* @return the ticket canned response that was removed
	*/
	@Override
	public com.liferay.osb.model.TicketCannedResponse deleteTicketCannedResponse(
		com.liferay.osb.model.TicketCannedResponse ticketCannedResponse) {
		return _ticketCannedResponseLocalService.deleteTicketCannedResponse(ticketCannedResponse);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _ticketCannedResponseLocalService.dynamicQuery();
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
		return _ticketCannedResponseLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.TicketCannedResponseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _ticketCannedResponseLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.TicketCannedResponseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _ticketCannedResponseLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
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
		return _ticketCannedResponseLocalService.dynamicQueryCount(dynamicQuery);
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
		return _ticketCannedResponseLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.osb.model.TicketCannedResponse fetchTicketCannedResponse(
		long ticketCannedResponseId) {
		return _ticketCannedResponseLocalService.fetchTicketCannedResponse(ticketCannedResponseId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _ticketCannedResponseLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _ticketCannedResponseLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _ticketCannedResponseLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketCannedResponseLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the ticket canned response with the primary key.
	*
	* @param ticketCannedResponseId the primary key of the ticket canned response
	* @return the ticket canned response
	* @throws PortalException if a ticket canned response with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.TicketCannedResponse getTicketCannedResponse(
		long ticketCannedResponseId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketCannedResponseLocalService.getTicketCannedResponse(ticketCannedResponseId);
	}

	/**
	* Returns a range of all the ticket canned responses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.TicketCannedResponseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ticket canned responses
	* @param end the upper bound of the range of ticket canned responses (not inclusive)
	* @return the range of ticket canned responses
	*/
	@Override
	public java.util.List<com.liferay.osb.model.TicketCannedResponse> getTicketCannedResponses(
		int start, int end) {
		return _ticketCannedResponseLocalService.getTicketCannedResponses(start,
			end);
	}

	/**
	* Returns the number of ticket canned responses.
	*
	* @return the number of ticket canned responses
	*/
	@Override
	public int getTicketCannedResponsesCount() {
		return _ticketCannedResponseLocalService.getTicketCannedResponsesCount();
	}

	@Override
	public void incrementUseCount(long ticketCannedResponseId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_ticketCannedResponseLocalService.incrementUseCount(ticketCannedResponseId);
	}

	@Override
	public void removeCannedResponseLocale(long ticketCannedResponseId,
		java.lang.String languageId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_ticketCannedResponseLocalService.removeCannedResponseLocale(ticketCannedResponseId,
			languageId);
	}

	@Override
	public java.util.List<com.liferay.osb.model.TicketCannedResponse> search(
		java.lang.String keywords, int start, int end) {
		return _ticketCannedResponseLocalService.search(keywords, start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.model.TicketCannedResponse> search(
		java.lang.String name, java.lang.String content, boolean andSearch,
		int start, int end) {
		return _ticketCannedResponseLocalService.search(name, content,
			andSearch, start, end);
	}

	@Override
	public int searchCount(java.lang.String keywords) {
		return _ticketCannedResponseLocalService.searchCount(keywords);
	}

	@Override
	public int searchCount(java.lang.String name, java.lang.String content,
		boolean andSearch) {
		return _ticketCannedResponseLocalService.searchCount(name, content,
			andSearch);
	}

	@Override
	public com.liferay.osb.model.TicketCannedResponse updateTicketCannedResponse(
		long ticketCannedResponseId, java.lang.String defaultLanguageId,
		java.lang.String languageId, java.lang.String name,
		java.lang.String content)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketCannedResponseLocalService.updateTicketCannedResponse(ticketCannedResponseId,
			defaultLanguageId, languageId, name, content);
	}

	/**
	* Updates the ticket canned response in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ticketCannedResponse the ticket canned response
	* @return the ticket canned response that was updated
	*/
	@Override
	public com.liferay.osb.model.TicketCannedResponse updateTicketCannedResponse(
		com.liferay.osb.model.TicketCannedResponse ticketCannedResponse) {
		return _ticketCannedResponseLocalService.updateTicketCannedResponse(ticketCannedResponse);
	}

	@Override
	public TicketCannedResponseLocalService getWrappedService() {
		return _ticketCannedResponseLocalService;
	}

	@Override
	public void setWrappedService(
		TicketCannedResponseLocalService ticketCannedResponseLocalService) {
		_ticketCannedResponseLocalService = ticketCannedResponseLocalService;
	}

	private TicketCannedResponseLocalService _ticketCannedResponseLocalService;
}