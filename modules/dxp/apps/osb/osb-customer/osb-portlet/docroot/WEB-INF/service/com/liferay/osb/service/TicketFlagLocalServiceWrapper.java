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
 * This class is a wrapper for {@link TicketFlagLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       TicketFlagLocalService
 * @generated
 */
public class TicketFlagLocalServiceWrapper implements TicketFlagLocalService,
	ServiceWrapper<TicketFlagLocalService> {
	public TicketFlagLocalServiceWrapper(
		TicketFlagLocalService ticketFlagLocalService) {
		_ticketFlagLocalService = ticketFlagLocalService;
	}

	/**
	* Adds the ticket flag to the database. Also notifies the appropriate model listeners.
	*
	* @param ticketFlag the ticket flag
	* @return the ticket flag that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketFlag addTicketFlag(
		com.liferay.osb.model.TicketFlag ticketFlag)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketFlagLocalService.addTicketFlag(ticketFlag);
	}

	/**
	* Creates a new ticket flag with the primary key. Does not add the ticket flag to the database.
	*
	* @param ticketFlagId the primary key for the new ticket flag
	* @return the new ticket flag
	*/
	public com.liferay.osb.model.TicketFlag createTicketFlag(long ticketFlagId) {
		return _ticketFlagLocalService.createTicketFlag(ticketFlagId);
	}

	/**
	* Deletes the ticket flag with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketFlagId the primary key of the ticket flag
	* @return the ticket flag that was removed
	* @throws PortalException if a ticket flag with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketFlag deleteTicketFlag(long ticketFlagId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketFlagLocalService.deleteTicketFlag(ticketFlagId);
	}

	/**
	* Deletes the ticket flag from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketFlag the ticket flag
	* @return the ticket flag that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketFlag deleteTicketFlag(
		com.liferay.osb.model.TicketFlag ticketFlag)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketFlagLocalService.deleteTicketFlag(ticketFlag);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _ticketFlagLocalService.dynamicQuery();
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
		return _ticketFlagLocalService.dynamicQuery(dynamicQuery);
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
		return _ticketFlagLocalService.dynamicQuery(dynamicQuery, start, end);
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
		return _ticketFlagLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _ticketFlagLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.osb.model.TicketFlag fetchTicketFlag(long ticketFlagId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketFlagLocalService.fetchTicketFlag(ticketFlagId);
	}

	/**
	* Returns the ticket flag with the primary key.
	*
	* @param ticketFlagId the primary key of the ticket flag
	* @return the ticket flag
	* @throws PortalException if a ticket flag with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketFlag getTicketFlag(long ticketFlagId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketFlagLocalService.getTicketFlag(ticketFlagId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketFlagLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the ticket flags.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of ticket flags
	* @param end the upper bound of the range of ticket flags (not inclusive)
	* @return the range of ticket flags
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketFlag> getTicketFlags(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketFlagLocalService.getTicketFlags(start, end);
	}

	/**
	* Returns the number of ticket flags.
	*
	* @return the number of ticket flags
	* @throws SystemException if a system exception occurred
	*/
	public int getTicketFlagsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketFlagLocalService.getTicketFlagsCount();
	}

	/**
	* Updates the ticket flag in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ticketFlag the ticket flag
	* @return the ticket flag that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketFlag updateTicketFlag(
		com.liferay.osb.model.TicketFlag ticketFlag)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketFlagLocalService.updateTicketFlag(ticketFlag);
	}

	/**
	* Updates the ticket flag in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ticketFlag the ticket flag
	* @param merge whether to merge the ticket flag with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the ticket flag that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketFlag updateTicketFlag(
		com.liferay.osb.model.TicketFlag ticketFlag, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketFlagLocalService.updateTicketFlag(ticketFlag, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _ticketFlagLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_ticketFlagLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _ticketFlagLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public void deleteTicketFlag(long userId, long accountEntryId,
		long ticketEntryId, int type)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_ticketFlagLocalService.deleteTicketFlag(userId, accountEntryId,
			ticketEntryId, type);
	}

	public void deleteTicketFlags(long ticketEntryId, int type, int flag)
		throws com.liferay.portal.kernel.exception.SystemException {
		_ticketFlagLocalService.deleteTicketFlags(ticketEntryId, type, flag);
	}

	public java.util.List<com.liferay.osb.model.TicketFlag> getTicketFlags(
		long ticketEntryId, int type, int flag)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketFlagLocalService.getTicketFlags(ticketEntryId, type, flag);
	}

	public java.util.List<com.liferay.osb.model.TicketFlag> getTicketFlags(
		long ticketEntryId, int[] types, int flag)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketFlagLocalService.getTicketFlags(ticketEntryId, types, flag);
	}

	public int getTicketFlagsCount(long ticketEntryId, int type, int flag)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketFlagLocalService.getTicketFlagsCount(ticketEntryId, type,
			flag);
	}

	public int[] getTicketFlagTypes(long ticketEntryId, int[] types, int flag)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketFlagLocalService.getTicketFlagTypes(ticketEntryId, types,
			flag);
	}

	public boolean hasTicketFlag(long ticketEntryId, int type, int flag)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketFlagLocalService.hasTicketFlag(ticketEntryId, type, flag);
	}

	public boolean hasTicketFlag(long userId, long accountEntryId,
		long ticketEntryId, int type, int flag)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketFlagLocalService.hasTicketFlag(userId, accountEntryId,
			ticketEntryId, type, flag);
	}

	public com.liferay.osb.model.TicketFlag updateTicketFlag(long userId,
		long accountEntryId, long ticketEntryId, int type, int flag)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketFlagLocalService.updateTicketFlag(userId, accountEntryId,
			ticketEntryId, type, flag);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public TicketFlagLocalService getWrappedTicketFlagLocalService() {
		return _ticketFlagLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedTicketFlagLocalService(
		TicketFlagLocalService ticketFlagLocalService) {
		_ticketFlagLocalService = ticketFlagLocalService;
	}

	public TicketFlagLocalService getWrappedService() {
		return _ticketFlagLocalService;
	}

	public void setWrappedService(TicketFlagLocalService ticketFlagLocalService) {
		_ticketFlagLocalService = ticketFlagLocalService;
	}

	private TicketFlagLocalService _ticketFlagLocalService;
}