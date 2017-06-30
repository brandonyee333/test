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
 * This class is a wrapper for {@link SupportWorkerLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       SupportWorkerLocalService
 * @generated
 */
public class SupportWorkerLocalServiceWrapper
	implements SupportWorkerLocalService,
		ServiceWrapper<SupportWorkerLocalService> {
	public SupportWorkerLocalServiceWrapper(
		SupportWorkerLocalService supportWorkerLocalService) {
		_supportWorkerLocalService = supportWorkerLocalService;
	}

	/**
	* Adds the support worker to the database. Also notifies the appropriate model listeners.
	*
	* @param supportWorker the support worker
	* @return the support worker that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportWorker addSupportWorker(
		com.liferay.osb.model.SupportWorker supportWorker)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportWorkerLocalService.addSupportWorker(supportWorker);
	}

	/**
	* Creates a new support worker with the primary key. Does not add the support worker to the database.
	*
	* @param supportWorkerId the primary key for the new support worker
	* @return the new support worker
	*/
	public com.liferay.osb.model.SupportWorker createSupportWorker(
		long supportWorkerId) {
		return _supportWorkerLocalService.createSupportWorker(supportWorkerId);
	}

	/**
	* Deletes the support worker with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param supportWorkerId the primary key of the support worker
	* @return the support worker that was removed
	* @throws PortalException if a support worker with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportWorker deleteSupportWorker(
		long supportWorkerId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _supportWorkerLocalService.deleteSupportWorker(supportWorkerId);
	}

	/**
	* Deletes the support worker from the database. Also notifies the appropriate model listeners.
	*
	* @param supportWorker the support worker
	* @return the support worker that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportWorker deleteSupportWorker(
		com.liferay.osb.model.SupportWorker supportWorker)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportWorkerLocalService.deleteSupportWorker(supportWorker);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _supportWorkerLocalService.dynamicQuery();
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
		return _supportWorkerLocalService.dynamicQuery(dynamicQuery);
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
		return _supportWorkerLocalService.dynamicQuery(dynamicQuery, start, end);
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
		return _supportWorkerLocalService.dynamicQuery(dynamicQuery, start,
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
		return _supportWorkerLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.osb.model.SupportWorker fetchSupportWorker(
		long supportWorkerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportWorkerLocalService.fetchSupportWorker(supportWorkerId);
	}

	/**
	* Returns the support worker with the primary key.
	*
	* @param supportWorkerId the primary key of the support worker
	* @return the support worker
	* @throws PortalException if a support worker with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportWorker getSupportWorker(
		long supportWorkerId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _supportWorkerLocalService.getSupportWorker(supportWorkerId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _supportWorkerLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the support workers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of support workers
	* @param end the upper bound of the range of support workers (not inclusive)
	* @return the range of support workers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.SupportWorker> getSupportWorkers(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportWorkerLocalService.getSupportWorkers(start, end);
	}

	/**
	* Returns the number of support workers.
	*
	* @return the number of support workers
	* @throws SystemException if a system exception occurred
	*/
	public int getSupportWorkersCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportWorkerLocalService.getSupportWorkersCount();
	}

	/**
	* Updates the support worker in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param supportWorker the support worker
	* @return the support worker that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportWorker updateSupportWorker(
		com.liferay.osb.model.SupportWorker supportWorker)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportWorkerLocalService.updateSupportWorker(supportWorker);
	}

	/**
	* Updates the support worker in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param supportWorker the support worker
	* @param merge whether to merge the support worker with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the support worker that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportWorker updateSupportWorker(
		com.liferay.osb.model.SupportWorker supportWorker, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportWorkerLocalService.updateSupportWorker(supportWorker,
			merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _supportWorkerLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_supportWorkerLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _supportWorkerLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public void addSupportWorkers(long[] userIds, long supportTeamId,
		double[] maxWork, int[] escalationLevels, int[] roles,
		int[] notifications)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_supportWorkerLocalService.addSupportWorkers(userIds, supportTeamId,
			maxWork, escalationLevels, roles, notifications);
	}

	public void clockInOut(long supportWorkerId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_supportWorkerLocalService.clockInOut(supportWorkerId);
	}

	public void decreaseAssignedWork(long userId, double work)
		throws com.liferay.portal.kernel.exception.SystemException {
		_supportWorkerLocalService.decreaseAssignedWork(userId, work);
	}

	public void decreaseTicketEntryAssignedWork(long ticketEntryId, double work)
		throws com.liferay.portal.kernel.exception.SystemException {
		_supportWorkerLocalService.decreaseTicketEntryAssignedWork(ticketEntryId,
			work);
	}

	public void deleteSupportWorkers(long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_supportWorkerLocalService.deleteSupportWorkers(userId);
	}

	public void deleteSupportWorkers(long[] userIds, long supportTeamId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_supportWorkerLocalService.deleteSupportWorkers(userIds, supportTeamId);
	}

	public double getAssignedWork(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportWorkerLocalService.getAssignedWork(userId);
	}

	public com.liferay.osb.model.SupportWorker getAvailableSupportWorker(
		com.liferay.osb.model.TicketEntry ticketEntry)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _supportWorkerLocalService.getAvailableSupportWorker(ticketEntry);
	}

	public com.liferay.osb.model.SupportWorker getLongestOpenSupportWorker(
		java.util.List<com.liferay.osb.model.SupportWorker> supportWorkers,
		com.liferay.osb.model.TicketEntry ticketEntry)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _supportWorkerLocalService.getLongestOpenSupportWorker(supportWorkers,
			ticketEntry);
	}

	public com.liferay.osb.model.SupportWorker getMostAvailableSupportWorker(
		com.liferay.osb.model.TicketEntry ticketEntry,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _supportWorkerLocalService.getMostAvailableSupportWorker(ticketEntry,
			params);
	}

	public com.liferay.osb.model.SupportWorker getNextOpenSupportWorker(
		java.util.List<com.liferay.osb.model.SupportWorker> supportWorkers,
		com.liferay.osb.model.TicketEntry ticketEntry)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _supportWorkerLocalService.getNextOpenSupportWorker(supportWorkers,
			ticketEntry);
	}

	public com.liferay.osb.model.SupportWorker getSupportWorker(long userId,
		long supportTeamId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _supportWorkerLocalService.getSupportWorker(userId, supportTeamId);
	}

	public java.util.List<com.liferay.osb.model.SupportWorker> getSupportWorkersBySupportLaborId(
		long supportLaborId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _supportWorkerLocalService.getSupportWorkersBySupportLaborId(supportLaborId);
	}

	public java.util.List<com.liferay.osb.model.SupportWorker> getSupportWorkersBySupportRegionId(
		long supportRegionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _supportWorkerLocalService.getSupportWorkersBySupportRegionId(supportRegionId);
	}

	public int getSupportWorkersCountBySupportLaborId(long supportLaborId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _supportWorkerLocalService.getSupportWorkersCountBySupportLaborId(supportLaborId);
	}

	public java.util.List<com.liferay.osb.model.SupportWorker> getTeamSupportWorkers(
		long supportTeamId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportWorkerLocalService.getTeamSupportWorkers(supportTeamId);
	}

	public java.util.List<com.liferay.osb.model.SupportWorker> getUserSupportTeamManagers(
		long userId, java.lang.Integer supportTeamType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _supportWorkerLocalService.getUserSupportTeamManagers(userId,
			supportTeamType);
	}

	public java.util.List<com.liferay.osb.model.SupportWorker> getUserSupportWorkers(
		long userId) throws com.liferay.portal.kernel.exception.SystemException {
		return _supportWorkerLocalService.getUserSupportWorkers(userId);
	}

	public boolean hasSupportWorker(long userId, int notRole)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportWorkerLocalService.hasSupportWorker(userId, notRole);
	}

	public boolean hasSupportWorker(long userId, int role,
		long locationSupportRegionId, java.lang.Integer supportTeamType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportWorkerLocalService.hasSupportWorker(userId, role,
			locationSupportRegionId, supportTeamType);
	}

	public boolean hasSupportWorker(long userId, long supportTeamId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportWorkerLocalService.hasSupportWorker(userId, supportTeamId);
	}

	public boolean hasSupportWorkerRole(long userId, int role)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportWorkerLocalService.hasSupportWorkerRole(userId, role);
	}

	public void increaseAssignedWork(long userId, double work)
		throws com.liferay.portal.kernel.exception.SystemException {
		_supportWorkerLocalService.increaseAssignedWork(userId, work);
	}

	public void increaseTicketEntryAssignedWork(long ticketEntryId, double work)
		throws com.liferay.portal.kernel.exception.SystemException {
		_supportWorkerLocalService.increaseTicketEntryAssignedWork(ticketEntryId,
			work);
	}

	public boolean isClockedIn(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportWorkerLocalService.isClockedIn(userId);
	}

	public boolean isManagerOfWorker(long userId, long workerUserId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _supportWorkerLocalService.isManagerOfWorker(userId, workerUserId);
	}

	public void recalculateUtilization() {
		_supportWorkerLocalService.recalculateUtilization();
	}

	public java.util.List<com.liferay.osb.model.SupportWorker> search(
		java.lang.Boolean overUtilization, int escalationLevel,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportWorkerLocalService.search(overUtilization,
			escalationLevel, params);
	}

	public java.util.List<com.liferay.osb.model.SupportWorker> search(
		long supportLaborId, java.lang.String keywords, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportWorkerLocalService.search(supportLaborId, keywords,
			start, end, obc);
	}

	public java.util.List<com.liferay.osb.model.SupportWorker> search(
		long supportLaborId, java.lang.String firstName,
		java.lang.String middleName, java.lang.String lastName,
		java.lang.String screenName, java.lang.String emailAddress,
		java.lang.String supportTeamName, boolean andSearch, int start,
		int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportWorkerLocalService.search(supportLaborId, firstName,
			middleName, lastName, screenName, emailAddress, supportTeamName,
			andSearch, start, end, obc);
	}

	public int searchCount(long supportLaborId, java.lang.String keywords)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportWorkerLocalService.searchCount(supportLaborId, keywords);
	}

	public int searchCount(long supportLaborId, java.lang.String firstName,
		java.lang.String middleName, java.lang.String lastName,
		java.lang.String screenName, java.lang.String emailAddress,
		java.lang.String supportTeamName, boolean andSearch)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportWorkerLocalService.searchCount(supportLaborId,
			firstName, middleName, lastName, screenName, emailAddress,
			supportTeamName, andSearch);
	}

	public com.liferay.osb.model.SupportWorker updateSupportWorker(
		long supportWorkerId, long supportTeamId, boolean autoAssign,
		double maxWork, int escalationlevel, int escalationLevel2Role,
		int notifications)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _supportWorkerLocalService.updateSupportWorker(supportWorkerId,
			supportTeamId, autoAssign, maxWork, escalationlevel,
			escalationLevel2Role, notifications);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public SupportWorkerLocalService getWrappedSupportWorkerLocalService() {
		return _supportWorkerLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedSupportWorkerLocalService(
		SupportWorkerLocalService supportWorkerLocalService) {
		_supportWorkerLocalService = supportWorkerLocalService;
	}

	public SupportWorkerLocalService getWrappedService() {
		return _supportWorkerLocalService;
	}

	public void setWrappedService(
		SupportWorkerLocalService supportWorkerLocalService) {
		_supportWorkerLocalService = supportWorkerLocalService;
	}

	private SupportWorkerLocalService _supportWorkerLocalService;
}