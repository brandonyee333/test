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
 * This class is a wrapper for {@link TicketCannedResponseLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       TicketCannedResponseLocalService
 * @generated
 */
public class TicketCannedResponseLocalServiceWrapper
	implements TicketCannedResponseLocalService,
		ServiceWrapper<TicketCannedResponseLocalService> {
	public TicketCannedResponseLocalServiceWrapper(
		TicketCannedResponseLocalService ticketCannedResponseLocalService) {
		_ticketCannedResponseLocalService = ticketCannedResponseLocalService;
	}

	/**
	* Adds the ticket canned response to the database. Also notifies the appropriate model listeners.
	*
	* @param ticketCannedResponse the ticket canned response
	* @return the ticket canned response that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketCannedResponse addTicketCannedResponse(
		com.liferay.osb.model.TicketCannedResponse ticketCannedResponse)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketCannedResponseLocalService.addTicketCannedResponse(ticketCannedResponse);
	}

	/**
	* Creates a new ticket canned response with the primary key. Does not add the ticket canned response to the database.
	*
	* @param ticketCannedResponseId the primary key for the new ticket canned response
	* @return the new ticket canned response
	*/
	public com.liferay.osb.model.TicketCannedResponse createTicketCannedResponse(
		long ticketCannedResponseId) {
		return _ticketCannedResponseLocalService.createTicketCannedResponse(ticketCannedResponseId);
	}

	/**
	* Deletes the ticket canned response with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketCannedResponseId the primary key of the ticket canned response
	* @return the ticket canned response that was removed
	* @throws PortalException if a ticket canned response with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketCannedResponse deleteTicketCannedResponse(
		long ticketCannedResponseId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketCannedResponseLocalService.deleteTicketCannedResponse(ticketCannedResponseId);
	}

	/**
	* Deletes the ticket canned response from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketCannedResponse the ticket canned response
	* @return the ticket canned response that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketCannedResponse deleteTicketCannedResponse(
		com.liferay.osb.model.TicketCannedResponse ticketCannedResponse)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketCannedResponseLocalService.deleteTicketCannedResponse(ticketCannedResponse);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _ticketCannedResponseLocalService.dynamicQuery();
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
		return _ticketCannedResponseLocalService.dynamicQuery(dynamicQuery);
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
		return _ticketCannedResponseLocalService.dynamicQuery(dynamicQuery,
			start, end);
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
		return _ticketCannedResponseLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
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
		return _ticketCannedResponseLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.osb.model.TicketCannedResponse fetchTicketCannedResponse(
		long ticketCannedResponseId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketCannedResponseLocalService.fetchTicketCannedResponse(ticketCannedResponseId);
	}

	/**
	* Returns the ticket canned response with the primary key.
	*
	* @param ticketCannedResponseId the primary key of the ticket canned response
	* @return the ticket canned response
	* @throws PortalException if a ticket canned response with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketCannedResponse getTicketCannedResponse(
		long ticketCannedResponseId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketCannedResponseLocalService.getTicketCannedResponse(ticketCannedResponseId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketCannedResponseLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the ticket canned responses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of ticket canned responses
	* @param end the upper bound of the range of ticket canned responses (not inclusive)
	* @return the range of ticket canned responses
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketCannedResponse> getTicketCannedResponses(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketCannedResponseLocalService.getTicketCannedResponses(start,
			end);
	}

	/**
	* Returns the number of ticket canned responses.
	*
	* @return the number of ticket canned responses
	* @throws SystemException if a system exception occurred
	*/
	public int getTicketCannedResponsesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketCannedResponseLocalService.getTicketCannedResponsesCount();
	}

	/**
	* Updates the ticket canned response in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ticketCannedResponse the ticket canned response
	* @return the ticket canned response that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketCannedResponse updateTicketCannedResponse(
		com.liferay.osb.model.TicketCannedResponse ticketCannedResponse)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketCannedResponseLocalService.updateTicketCannedResponse(ticketCannedResponse);
	}

	/**
	* Updates the ticket canned response in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ticketCannedResponse the ticket canned response
	* @param merge whether to merge the ticket canned response with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the ticket canned response that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketCannedResponse updateTicketCannedResponse(
		com.liferay.osb.model.TicketCannedResponse ticketCannedResponse,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketCannedResponseLocalService.updateTicketCannedResponse(ticketCannedResponse,
			merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _ticketCannedResponseLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_ticketCannedResponseLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _ticketCannedResponseLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	public com.liferay.osb.model.TicketCannedResponse addTicketCannedResponse(
		long userId, java.lang.String defaultLanguageId, java.lang.String name,
		java.lang.String content)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketCannedResponseLocalService.addTicketCannedResponse(userId,
			defaultLanguageId, name, content);
	}

	public void incrementUseCount(long ticketCannedResponseId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_ticketCannedResponseLocalService.incrementUseCount(ticketCannedResponseId);
	}

	public void removeCannedResponseLocale(long ticketCannedResponseId,
		java.lang.String languageId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_ticketCannedResponseLocalService.removeCannedResponseLocale(ticketCannedResponseId,
			languageId);
	}

	public java.util.List<com.liferay.osb.model.TicketCannedResponse> search(
		java.lang.String keywords, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketCannedResponseLocalService.search(keywords, start, end);
	}

	public java.util.List<com.liferay.osb.model.TicketCannedResponse> search(
		java.lang.String name, java.lang.String content, boolean andSearch,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketCannedResponseLocalService.search(name, content,
			andSearch, start, end);
	}

	public int searchCount(java.lang.String keywords)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketCannedResponseLocalService.searchCount(keywords);
	}

	public int searchCount(java.lang.String name, java.lang.String content,
		boolean andSearch)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketCannedResponseLocalService.searchCount(name, content,
			andSearch);
	}

	public com.liferay.osb.model.TicketCannedResponse updateTicketCannedResponse(
		long ticketCannedResponseId, java.lang.String defaultLanguageId,
		java.lang.String languageId, java.lang.String name,
		java.lang.String content)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketCannedResponseLocalService.updateTicketCannedResponse(ticketCannedResponseId,
			defaultLanguageId, languageId, name, content);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public TicketCannedResponseLocalService getWrappedTicketCannedResponseLocalService() {
		return _ticketCannedResponseLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedTicketCannedResponseLocalService(
		TicketCannedResponseLocalService ticketCannedResponseLocalService) {
		_ticketCannedResponseLocalService = ticketCannedResponseLocalService;
	}

	public TicketCannedResponseLocalService getWrappedService() {
		return _ticketCannedResponseLocalService;
	}

	public void setWrappedService(
		TicketCannedResponseLocalService ticketCannedResponseLocalService) {
		_ticketCannedResponseLocalService = ticketCannedResponseLocalService;
	}

	private TicketCannedResponseLocalService _ticketCannedResponseLocalService;
}