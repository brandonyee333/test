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
 * Provides a wrapper for {@link TicketFlagLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see TicketFlagLocalService
 * @generated
 */
@ProviderType
public class TicketFlagLocalServiceWrapper implements TicketFlagLocalService,
	ServiceWrapper<TicketFlagLocalService> {
	public TicketFlagLocalServiceWrapper(
		TicketFlagLocalService ticketFlagLocalService) {
		_ticketFlagLocalService = ticketFlagLocalService;
	}

	@Override
	public boolean hasTicketFlag(long ticketEntryId, int type, int flag) {
		return _ticketFlagLocalService.hasTicketFlag(ticketEntryId, type, flag);
	}

	@Override
	public boolean hasTicketFlag(long userId, long accountEntryId,
		long ticketEntryId, int type, int flag) {
		return _ticketFlagLocalService.hasTicketFlag(userId, accountEntryId,
			ticketEntryId, type, flag);
	}

	/**
	* Adds the ticket flag to the database. Also notifies the appropriate model listeners.
	*
	* @param ticketFlag the ticket flag
	* @return the ticket flag that was added
	*/
	@Override
	public com.liferay.osb.model.TicketFlag addTicketFlag(
		com.liferay.osb.model.TicketFlag ticketFlag) {
		return _ticketFlagLocalService.addTicketFlag(ticketFlag);
	}

	/**
	* Creates a new ticket flag with the primary key. Does not add the ticket flag to the database.
	*
	* @param ticketFlagId the primary key for the new ticket flag
	* @return the new ticket flag
	*/
	@Override
	public com.liferay.osb.model.TicketFlag createTicketFlag(long ticketFlagId) {
		return _ticketFlagLocalService.createTicketFlag(ticketFlagId);
	}

	/**
	* Deletes the ticket flag from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketFlag the ticket flag
	* @return the ticket flag that was removed
	*/
	@Override
	public com.liferay.osb.model.TicketFlag deleteTicketFlag(
		com.liferay.osb.model.TicketFlag ticketFlag) {
		return _ticketFlagLocalService.deleteTicketFlag(ticketFlag);
	}

	/**
	* Deletes the ticket flag with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketFlagId the primary key of the ticket flag
	* @return the ticket flag that was removed
	* @throws PortalException if a ticket flag with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.TicketFlag deleteTicketFlag(long ticketFlagId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketFlagLocalService.deleteTicketFlag(ticketFlagId);
	}

	@Override
	public com.liferay.osb.model.TicketFlag fetchTicketFlag(long ticketFlagId) {
		return _ticketFlagLocalService.fetchTicketFlag(ticketFlagId);
	}

	/**
	* Returns the ticket flag with the primary key.
	*
	* @param ticketFlagId the primary key of the ticket flag
	* @return the ticket flag
	* @throws PortalException if a ticket flag with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.TicketFlag getTicketFlag(long ticketFlagId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketFlagLocalService.getTicketFlag(ticketFlagId);
	}

	/**
	* Updates the ticket flag in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ticketFlag the ticket flag
	* @return the ticket flag that was updated
	*/
	@Override
	public com.liferay.osb.model.TicketFlag updateTicketFlag(
		com.liferay.osb.model.TicketFlag ticketFlag) {
		return _ticketFlagLocalService.updateTicketFlag(ticketFlag);
	}

	@Override
	public com.liferay.osb.model.TicketFlag updateTicketFlag(long userId,
		long accountEntryId, long ticketEntryId, int type, int flag)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketFlagLocalService.updateTicketFlag(userId, accountEntryId,
			ticketEntryId, type, flag);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _ticketFlagLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _ticketFlagLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _ticketFlagLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketFlagLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketFlagLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of ticket flags.
	*
	* @return the number of ticket flags
	*/
	@Override
	public int getTicketFlagsCount() {
		return _ticketFlagLocalService.getTicketFlagsCount();
	}

	@Override
	public int getTicketFlagsCount(long ticketEntryId, int type, int flag) {
		return _ticketFlagLocalService.getTicketFlagsCount(ticketEntryId, type,
			flag);
	}

	@Override
	public int[] getTicketFlagTypes(long ticketEntryId, int[] types, int flag) {
		return _ticketFlagLocalService.getTicketFlagTypes(ticketEntryId, types,
			flag);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _ticketFlagLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _ticketFlagLocalService.getOSGiServiceIdentifier();
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
		return _ticketFlagLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _ticketFlagLocalService.dynamicQuery(dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _ticketFlagLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
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
	@Override
	public java.util.List<com.liferay.osb.model.TicketFlag> getTicketFlags(
		int start, int end) {
		return _ticketFlagLocalService.getTicketFlags(start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.model.TicketFlag> getTicketFlags(
		long ticketEntryId, int type, int flag) {
		return _ticketFlagLocalService.getTicketFlags(ticketEntryId, type, flag);
	}

	@Override
	public java.util.List<com.liferay.osb.model.TicketFlag> getTicketFlags(
		long ticketEntryId, int[] types, int flag) {
		return _ticketFlagLocalService.getTicketFlags(ticketEntryId, types, flag);
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
		return _ticketFlagLocalService.dynamicQueryCount(dynamicQuery);
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
		return _ticketFlagLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public void deleteTicketFlag(long userId, long accountEntryId,
		long ticketEntryId, int type)
		throws com.liferay.portal.kernel.exception.PortalException {
		_ticketFlagLocalService.deleteTicketFlag(userId, accountEntryId,
			ticketEntryId, type);
	}

	@Override
	public void deleteTicketFlags(long ticketEntryId, int type, int flag) {
		_ticketFlagLocalService.deleteTicketFlags(ticketEntryId, type, flag);
	}

	@Override
	public TicketFlagLocalService getWrappedService() {
		return _ticketFlagLocalService;
	}

	@Override
	public void setWrappedService(TicketFlagLocalService ticketFlagLocalService) {
		_ticketFlagLocalService = ticketFlagLocalService;
	}

	private TicketFlagLocalService _ticketFlagLocalService;
}