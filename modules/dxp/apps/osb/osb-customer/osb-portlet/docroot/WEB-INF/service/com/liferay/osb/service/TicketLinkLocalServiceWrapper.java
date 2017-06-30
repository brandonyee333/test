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
 * This class is a wrapper for {@link TicketLinkLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       TicketLinkLocalService
 * @generated
 */
public class TicketLinkLocalServiceWrapper implements TicketLinkLocalService,
	ServiceWrapper<TicketLinkLocalService> {
	public TicketLinkLocalServiceWrapper(
		TicketLinkLocalService ticketLinkLocalService) {
		_ticketLinkLocalService = ticketLinkLocalService;
	}

	/**
	* Adds the ticket link to the database. Also notifies the appropriate model listeners.
	*
	* @param ticketLink the ticket link
	* @return the ticket link that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketLink addTicketLink(
		com.liferay.osb.model.TicketLink ticketLink)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketLinkLocalService.addTicketLink(ticketLink);
	}

	/**
	* Creates a new ticket link with the primary key. Does not add the ticket link to the database.
	*
	* @param ticketLinkId the primary key for the new ticket link
	* @return the new ticket link
	*/
	public com.liferay.osb.model.TicketLink createTicketLink(long ticketLinkId) {
		return _ticketLinkLocalService.createTicketLink(ticketLinkId);
	}

	/**
	* Deletes the ticket link with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketLinkId the primary key of the ticket link
	* @return the ticket link that was removed
	* @throws PortalException if a ticket link with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketLink deleteTicketLink(long ticketLinkId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketLinkLocalService.deleteTicketLink(ticketLinkId);
	}

	/**
	* Deletes the ticket link from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketLink the ticket link
	* @return the ticket link that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketLink deleteTicketLink(
		com.liferay.osb.model.TicketLink ticketLink)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketLinkLocalService.deleteTicketLink(ticketLink);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _ticketLinkLocalService.dynamicQuery();
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
		return _ticketLinkLocalService.dynamicQuery(dynamicQuery);
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
		return _ticketLinkLocalService.dynamicQuery(dynamicQuery, start, end);
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
		return _ticketLinkLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
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
		return _ticketLinkLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.osb.model.TicketLink fetchTicketLink(long ticketLinkId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketLinkLocalService.fetchTicketLink(ticketLinkId);
	}

	/**
	* Returns the ticket link with the primary key.
	*
	* @param ticketLinkId the primary key of the ticket link
	* @return the ticket link
	* @throws PortalException if a ticket link with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketLink getTicketLink(long ticketLinkId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketLinkLocalService.getTicketLink(ticketLinkId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketLinkLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the ticket links.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of ticket links
	* @param end the upper bound of the range of ticket links (not inclusive)
	* @return the range of ticket links
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketLink> getTicketLinks(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketLinkLocalService.getTicketLinks(start, end);
	}

	/**
	* Returns the number of ticket links.
	*
	* @return the number of ticket links
	* @throws SystemException if a system exception occurred
	*/
	public int getTicketLinksCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketLinkLocalService.getTicketLinksCount();
	}

	/**
	* Updates the ticket link in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ticketLink the ticket link
	* @return the ticket link that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketLink updateTicketLink(
		com.liferay.osb.model.TicketLink ticketLink)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketLinkLocalService.updateTicketLink(ticketLink);
	}

	/**
	* Updates the ticket link in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ticketLink the ticket link
	* @param merge whether to merge the ticket link with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the ticket link that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketLink updateTicketLink(
		com.liferay.osb.model.TicketLink ticketLink, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketLinkLocalService.updateTicketLink(ticketLink, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _ticketLinkLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_ticketLinkLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _ticketLinkLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public com.liferay.osb.model.TicketLink addTicketLink(long userId,
		long ticketEntryId, long ticketSolutionId, java.lang.String[] urls,
		java.lang.Integer[] types, int visibility,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketLinkLocalService.addTicketLink(userId, ticketEntryId,
			ticketSolutionId, urls, types, visibility, serviceContext);
	}

	public void deleteTicketLink(long userId, long ticketLinkId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_ticketLinkLocalService.deleteTicketLink(userId, ticketLinkId);
	}

	public void deleteTicketLink(long userId,
		com.liferay.osb.model.TicketLink ticketLink)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_ticketLinkLocalService.deleteTicketLink(userId, ticketLink);
	}

	public java.util.List<com.liferay.osb.model.TicketLink> getTicketLinks(
		long ticketEntryId, int visibility)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketLinkLocalService.getTicketLinks(ticketEntryId, visibility);
	}

	public java.util.List<com.liferay.osb.model.TicketLink> getTicketLinks(
		long ticketEntryId, int[] visibilities)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketLinkLocalService.getTicketLinks(ticketEntryId,
			visibilities);
	}

	public java.util.List<com.liferay.osb.model.TicketLink> getTicketLinks(
		long ticketEntryId, long ticketSolutionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketLinkLocalService.getTicketLinks(ticketEntryId,
			ticketSolutionId);
	}

	public int getTicketLinksCount(long ticketEntryId, int visibility)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketLinkLocalService.getTicketLinksCount(ticketEntryId,
			visibility);
	}

	public int getTicketLinksCount(long ticketEntryId, int[] visibilities)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketLinkLocalService.getTicketLinksCount(ticketEntryId,
			visibilities);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public TicketLinkLocalService getWrappedTicketLinkLocalService() {
		return _ticketLinkLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedTicketLinkLocalService(
		TicketLinkLocalService ticketLinkLocalService) {
		_ticketLinkLocalService = ticketLinkLocalService;
	}

	public TicketLinkLocalService getWrappedService() {
		return _ticketLinkLocalService;
	}

	public void setWrappedService(TicketLinkLocalService ticketLinkLocalService) {
		_ticketLinkLocalService = ticketLinkLocalService;
	}

	private TicketLinkLocalService _ticketLinkLocalService;
}