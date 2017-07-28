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
 * Provides a wrapper for {@link SupportWorkerLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see SupportWorkerLocalService
 * @generated
 */
@ProviderType
public class SupportWorkerLocalServiceWrapper
	implements SupportWorkerLocalService,
		ServiceWrapper<SupportWorkerLocalService> {
	public SupportWorkerLocalServiceWrapper(
		SupportWorkerLocalService supportWorkerLocalService) {
		_supportWorkerLocalService = supportWorkerLocalService;
	}

	@Override
	public boolean hasSupportWorker(long userId, int notRole) {
		return _supportWorkerLocalService.hasSupportWorker(userId, notRole);
	}

	@Override
	public boolean hasSupportWorker(long userId, int role,
		long locationSupportRegionId, java.lang.Integer supportTeamType) {
		return _supportWorkerLocalService.hasSupportWorker(userId, role,
			locationSupportRegionId, supportTeamType);
	}

	@Override
	public boolean hasSupportWorker(long userId, long supportTeamId) {
		return _supportWorkerLocalService.hasSupportWorker(userId, supportTeamId);
	}

	@Override
	public boolean hasSupportWorkerRole(long userId, int role) {
		return _supportWorkerLocalService.hasSupportWorkerRole(userId, role);
	}

	@Override
	public boolean isClockedIn(long userId) {
		return _supportWorkerLocalService.isClockedIn(userId);
	}

	@Override
	public boolean isManagerOfWorker(long userId, long workerUserId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _supportWorkerLocalService.isManagerOfWorker(userId, workerUserId);
	}

	/**
	* Adds the support worker to the database. Also notifies the appropriate model listeners.
	*
	* @param supportWorker the support worker
	* @return the support worker that was added
	*/
	@Override
	public com.liferay.osb.model.SupportWorker addSupportWorker(
		com.liferay.osb.model.SupportWorker supportWorker) {
		return _supportWorkerLocalService.addSupportWorker(supportWorker);
	}

	/**
	* Creates a new support worker with the primary key. Does not add the support worker to the database.
	*
	* @param supportWorkerId the primary key for the new support worker
	* @return the new support worker
	*/
	@Override
	public com.liferay.osb.model.SupportWorker createSupportWorker(
		long supportWorkerId) {
		return _supportWorkerLocalService.createSupportWorker(supportWorkerId);
	}

	/**
	* Deletes the support worker from the database. Also notifies the appropriate model listeners.
	*
	* @param supportWorker the support worker
	* @return the support worker that was removed
	*/
	@Override
	public com.liferay.osb.model.SupportWorker deleteSupportWorker(
		com.liferay.osb.model.SupportWorker supportWorker) {
		return _supportWorkerLocalService.deleteSupportWorker(supportWorker);
	}

	/**
	* Deletes the support worker with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param supportWorkerId the primary key of the support worker
	* @return the support worker that was removed
	* @throws PortalException if a support worker with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.SupportWorker deleteSupportWorker(
		long supportWorkerId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _supportWorkerLocalService.deleteSupportWorker(supportWorkerId);
	}

	@Override
	public com.liferay.osb.model.SupportWorker fetchSupportWorker(
		long supportWorkerId) {
		return _supportWorkerLocalService.fetchSupportWorker(supportWorkerId);
	}

	@Override
	public com.liferay.osb.model.SupportWorker getAvailableSupportWorker(
		com.liferay.osb.model.TicketEntry ticketEntry)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _supportWorkerLocalService.getAvailableSupportWorker(ticketEntry);
	}

	@Override
	public com.liferay.osb.model.SupportWorker getLongestOpenSupportWorker(
		java.util.List<com.liferay.osb.model.SupportWorker> supportWorkers,
		com.liferay.osb.model.TicketEntry ticketEntry)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _supportWorkerLocalService.getLongestOpenSupportWorker(supportWorkers,
			ticketEntry);
	}

	@Override
	public com.liferay.osb.model.SupportWorker getMostAvailableSupportWorker(
		com.liferay.osb.model.TicketEntry ticketEntry,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _supportWorkerLocalService.getMostAvailableSupportWorker(ticketEntry,
			params);
	}

	@Override
	public com.liferay.osb.model.SupportWorker getNextOpenSupportWorker(
		java.util.List<com.liferay.osb.model.SupportWorker> supportWorkers,
		com.liferay.osb.model.TicketEntry ticketEntry)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _supportWorkerLocalService.getNextOpenSupportWorker(supportWorkers,
			ticketEntry);
	}

	/**
	* Returns the support worker with the primary key.
	*
	* @param supportWorkerId the primary key of the support worker
	* @return the support worker
	* @throws PortalException if a support worker with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.SupportWorker getSupportWorker(
		long supportWorkerId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _supportWorkerLocalService.getSupportWorker(supportWorkerId);
	}

	@Override
	public com.liferay.osb.model.SupportWorker getSupportWorker(long userId,
		long supportTeamId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _supportWorkerLocalService.getSupportWorker(userId, supportTeamId);
	}

	/**
	* Updates the support worker in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param supportWorker the support worker
	* @return the support worker that was updated
	*/
	@Override
	public com.liferay.osb.model.SupportWorker updateSupportWorker(
		com.liferay.osb.model.SupportWorker supportWorker) {
		return _supportWorkerLocalService.updateSupportWorker(supportWorker);
	}

	@Override
	public com.liferay.osb.model.SupportWorker updateSupportWorker(
		long supportWorkerId, long supportTeamId, boolean autoAssign,
		double maxWork, int escalationlevel, int escalationLevel2Role,
		int notifications)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _supportWorkerLocalService.updateSupportWorker(supportWorkerId,
			supportTeamId, autoAssign, maxWork, escalationlevel,
			escalationLevel2Role, notifications);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _supportWorkerLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _supportWorkerLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _supportWorkerLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _supportWorkerLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _supportWorkerLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public double getAssignedWork(long userId) {
		return _supportWorkerLocalService.getAssignedWork(userId);
	}

	/**
	* Returns the number of support workers.
	*
	* @return the number of support workers
	*/
	@Override
	public int getSupportWorkersCount() {
		return _supportWorkerLocalService.getSupportWorkersCount();
	}

	@Override
	public int getSupportWorkersCountBySupportLaborId(long supportLaborId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _supportWorkerLocalService.getSupportWorkersCountBySupportLaborId(supportLaborId);
	}

	@Override
	public int searchCount(long supportLaborId, java.lang.String firstName,
		java.lang.String middleName, java.lang.String lastName,
		java.lang.String screenName, java.lang.String emailAddress,
		java.lang.String supportTeamName, boolean andSearch) {
		return _supportWorkerLocalService.searchCount(supportLaborId,
			firstName, middleName, lastName, screenName, emailAddress,
			supportTeamName, andSearch);
	}

	@Override
	public int searchCount(long supportLaborId, java.lang.String keywords) {
		return _supportWorkerLocalService.searchCount(supportLaborId, keywords);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _supportWorkerLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _supportWorkerLocalService.getOSGiServiceIdentifier();
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
		return _supportWorkerLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.SupportWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _supportWorkerLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.SupportWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _supportWorkerLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Returns a range of all the support workers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.SupportWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of support workers
	* @param end the upper bound of the range of support workers (not inclusive)
	* @return the range of support workers
	*/
	@Override
	public java.util.List<com.liferay.osb.model.SupportWorker> getSupportWorkers(
		int start, int end) {
		return _supportWorkerLocalService.getSupportWorkers(start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.model.SupportWorker> getSupportWorkersBySupportLaborId(
		long supportLaborId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _supportWorkerLocalService.getSupportWorkersBySupportLaborId(supportLaborId);
	}

	@Override
	public java.util.List<com.liferay.osb.model.SupportWorker> getSupportWorkersBySupportRegionId(
		long supportRegionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _supportWorkerLocalService.getSupportWorkersBySupportRegionId(supportRegionId);
	}

	@Override
	public java.util.List<com.liferay.osb.model.SupportWorker> getTeamSupportWorkers(
		long supportTeamId) {
		return _supportWorkerLocalService.getTeamSupportWorkers(supportTeamId);
	}

	@Override
	public java.util.List<com.liferay.osb.model.SupportWorker> getUserSupportTeamManagers(
		long userId, java.lang.Integer supportTeamType)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _supportWorkerLocalService.getUserSupportTeamManagers(userId,
			supportTeamType);
	}

	@Override
	public java.util.List<com.liferay.osb.model.SupportWorker> getUserSupportWorkers(
		long userId) {
		return _supportWorkerLocalService.getUserSupportWorkers(userId);
	}

	@Override
	public java.util.List<com.liferay.osb.model.SupportWorker> search(
		java.lang.Boolean overUtilization, int escalationLevel,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params) {
		return _supportWorkerLocalService.search(overUtilization,
			escalationLevel, params);
	}

	@Override
	public java.util.List<com.liferay.osb.model.SupportWorker> search(
		long supportLaborId, java.lang.String firstName,
		java.lang.String middleName, java.lang.String lastName,
		java.lang.String screenName, java.lang.String emailAddress,
		java.lang.String supportTeamName, boolean andSearch, int start,
		int end, com.liferay.portal.kernel.util.OrderByComparator obc) {
		return _supportWorkerLocalService.search(supportLaborId, firstName,
			middleName, lastName, screenName, emailAddress, supportTeamName,
			andSearch, start, end, obc);
	}

	@Override
	public java.util.List<com.liferay.osb.model.SupportWorker> search(
		long supportLaborId, java.lang.String keywords, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc) {
		return _supportWorkerLocalService.search(supportLaborId, keywords,
			start, end, obc);
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
		return _supportWorkerLocalService.dynamicQueryCount(dynamicQuery);
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
		return _supportWorkerLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public void addSupportWorkers(long[] userIds, long supportTeamId,
		double[] maxWork, int[] escalationLevels, int[] roles,
		int[] notifications)
		throws com.liferay.portal.kernel.exception.PortalException {
		_supportWorkerLocalService.addSupportWorkers(userIds, supportTeamId,
			maxWork, escalationLevels, roles, notifications);
	}

	@Override
	public void clockInOut(long supportWorkerId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_supportWorkerLocalService.clockInOut(supportWorkerId);
	}

	@Override
	public void decreaseAssignedWork(long userId, double work) {
		_supportWorkerLocalService.decreaseAssignedWork(userId, work);
	}

	@Override
	public void decreaseTicketEntryAssignedWork(long ticketEntryId, double work) {
		_supportWorkerLocalService.decreaseTicketEntryAssignedWork(ticketEntryId,
			work);
	}

	@Override
	public void deleteSupportWorkers(long userId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_supportWorkerLocalService.deleteSupportWorkers(userId);
	}

	@Override
	public void deleteSupportWorkers(long[] userIds, long supportTeamId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_supportWorkerLocalService.deleteSupportWorkers(userIds, supportTeamId);
	}

	@Override
	public void increaseAssignedWork(long userId, double work) {
		_supportWorkerLocalService.increaseAssignedWork(userId, work);
	}

	@Override
	public void increaseTicketEntryAssignedWork(long ticketEntryId, double work) {
		_supportWorkerLocalService.increaseTicketEntryAssignedWork(ticketEntryId,
			work);
	}

	@Override
	public void recalculateUtilization() {
		_supportWorkerLocalService.recalculateUtilization();
	}

	@Override
	public SupportWorkerLocalService getWrappedService() {
		return _supportWorkerLocalService;
	}

	@Override
	public void setWrappedService(
		SupportWorkerLocalService supportWorkerLocalService) {
		_supportWorkerLocalService = supportWorkerLocalService;
	}

	private SupportWorkerLocalService _supportWorkerLocalService;
}