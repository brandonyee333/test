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
 * Provides the local service utility for SupportWorker. This utility wraps
 * {@link com.liferay.osb.service.impl.SupportWorkerLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see SupportWorkerLocalService
 * @see com.liferay.osb.service.base.SupportWorkerLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.SupportWorkerLocalServiceImpl
 * @generated
 */
@ProviderType
public class SupportWorkerLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.SupportWorkerLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static boolean hasSupportWorker(long userId, int notRole) {
		return getService().hasSupportWorker(userId, notRole);
	}

	public static boolean hasSupportWorker(long userId, int role,
		long locationSupportRegionId, java.lang.Integer supportTeamType) {
		return getService()
				   .hasSupportWorker(userId, role, locationSupportRegionId,
			supportTeamType);
	}

	public static boolean hasSupportWorker(long userId, long supportTeamId) {
		return getService().hasSupportWorker(userId, supportTeamId);
	}

	public static boolean hasSupportWorkerRole(long userId, int role) {
		return getService().hasSupportWorkerRole(userId, role);
	}

	public static boolean isClockedIn(long userId) {
		return getService().isClockedIn(userId);
	}

	public static boolean isManagerOfWorker(long userId, long workerUserId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().isManagerOfWorker(userId, workerUserId);
	}

	/**
	* Adds the support worker to the database. Also notifies the appropriate model listeners.
	*
	* @param supportWorker the support worker
	* @return the support worker that was added
	*/
	public static com.liferay.osb.model.SupportWorker addSupportWorker(
		com.liferay.osb.model.SupportWorker supportWorker) {
		return getService().addSupportWorker(supportWorker);
	}

	/**
	* Creates a new support worker with the primary key. Does not add the support worker to the database.
	*
	* @param supportWorkerId the primary key for the new support worker
	* @return the new support worker
	*/
	public static com.liferay.osb.model.SupportWorker createSupportWorker(
		long supportWorkerId) {
		return getService().createSupportWorker(supportWorkerId);
	}

	/**
	* Deletes the support worker from the database. Also notifies the appropriate model listeners.
	*
	* @param supportWorker the support worker
	* @return the support worker that was removed
	*/
	public static com.liferay.osb.model.SupportWorker deleteSupportWorker(
		com.liferay.osb.model.SupportWorker supportWorker) {
		return getService().deleteSupportWorker(supportWorker);
	}

	/**
	* Deletes the support worker with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param supportWorkerId the primary key of the support worker
	* @return the support worker that was removed
	* @throws PortalException if a support worker with the primary key could not be found
	*/
	public static com.liferay.osb.model.SupportWorker deleteSupportWorker(
		long supportWorkerId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteSupportWorker(supportWorkerId);
	}

	public static com.liferay.osb.model.SupportWorker fetchSupportWorker(
		long supportWorkerId) {
		return getService().fetchSupportWorker(supportWorkerId);
	}

	public static com.liferay.osb.model.SupportWorker getAvailableSupportWorker(
		com.liferay.osb.model.TicketEntry ticketEntry)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getAvailableSupportWorker(ticketEntry);
	}

	public static com.liferay.osb.model.SupportWorker getLongestOpenSupportWorker(
		java.util.List<com.liferay.osb.model.SupportWorker> supportWorkers,
		com.liferay.osb.model.TicketEntry ticketEntry)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getLongestOpenSupportWorker(supportWorkers, ticketEntry);
	}

	public static com.liferay.osb.model.SupportWorker getMostAvailableSupportWorker(
		com.liferay.osb.model.TicketEntry ticketEntry,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getMostAvailableSupportWorker(ticketEntry, params);
	}

	public static com.liferay.osb.model.SupportWorker getNextOpenSupportWorker(
		java.util.List<com.liferay.osb.model.SupportWorker> supportWorkers,
		com.liferay.osb.model.TicketEntry ticketEntry)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getNextOpenSupportWorker(supportWorkers, ticketEntry);
	}

	/**
	* Returns the support worker with the primary key.
	*
	* @param supportWorkerId the primary key of the support worker
	* @return the support worker
	* @throws PortalException if a support worker with the primary key could not be found
	*/
	public static com.liferay.osb.model.SupportWorker getSupportWorker(
		long supportWorkerId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getSupportWorker(supportWorkerId);
	}

	public static com.liferay.osb.model.SupportWorker getSupportWorker(
		long userId, long supportTeamId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getSupportWorker(userId, supportTeamId);
	}

	/**
	* Updates the support worker in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param supportWorker the support worker
	* @return the support worker that was updated
	*/
	public static com.liferay.osb.model.SupportWorker updateSupportWorker(
		com.liferay.osb.model.SupportWorker supportWorker) {
		return getService().updateSupportWorker(supportWorker);
	}

	public static com.liferay.osb.model.SupportWorker updateSupportWorker(
		long supportWorkerId, long supportTeamId, boolean autoAssign,
		double maxWork, int escalationlevel, int escalationLevel2Role,
		int notifications)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateSupportWorker(supportWorkerId, supportTeamId,
			autoAssign, maxWork, escalationlevel, escalationLevel2Role,
			notifications);
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

	public static double getAssignedWork(long userId) {
		return getService().getAssignedWork(userId);
	}

	/**
	* Returns the number of support workers.
	*
	* @return the number of support workers
	*/
	public static int getSupportWorkersCount() {
		return getService().getSupportWorkersCount();
	}

	public static int getSupportWorkersCountBySupportLaborId(
		long supportLaborId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getSupportWorkersCountBySupportLaborId(supportLaborId);
	}

	public static int searchCount(long supportLaborId,
		java.lang.String firstName, java.lang.String middleName,
		java.lang.String lastName, java.lang.String screenName,
		java.lang.String emailAddress, java.lang.String supportTeamName,
		boolean andSearch) {
		return getService()
				   .searchCount(supportLaborId, firstName, middleName,
			lastName, screenName, emailAddress, supportTeamName, andSearch);
	}

	public static int searchCount(long supportLaborId, java.lang.String keywords) {
		return getService().searchCount(supportLaborId, keywords);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.SupportWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.SupportWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public static java.util.List<com.liferay.osb.model.SupportWorker> getSupportWorkers(
		int start, int end) {
		return getService().getSupportWorkers(start, end);
	}

	public static java.util.List<com.liferay.osb.model.SupportWorker> getSupportWorkersBySupportLaborId(
		long supportLaborId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getSupportWorkersBySupportLaborId(supportLaborId);
	}

	public static java.util.List<com.liferay.osb.model.SupportWorker> getSupportWorkersBySupportRegionId(
		long supportRegionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getSupportWorkersBySupportRegionId(supportRegionId);
	}

	public static java.util.List<com.liferay.osb.model.SupportWorker> getTeamSupportWorkers(
		long supportTeamId) {
		return getService().getTeamSupportWorkers(supportTeamId);
	}

	public static java.util.List<com.liferay.osb.model.SupportWorker> getUserSupportTeamManagers(
		long userId, java.lang.Integer supportTeamType)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getUserSupportTeamManagers(userId, supportTeamType);
	}

	public static java.util.List<com.liferay.osb.model.SupportWorker> getUserSupportWorkers(
		long userId) {
		return getService().getUserSupportWorkers(userId);
	}

	public static java.util.List<com.liferay.osb.model.SupportWorker> search(
		java.lang.Boolean overUtilization, int escalationLevel,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params) {
		return getService().search(overUtilization, escalationLevel, params);
	}

	public static java.util.List<com.liferay.osb.model.SupportWorker> search(
		long supportLaborId, java.lang.String firstName,
		java.lang.String middleName, java.lang.String lastName,
		java.lang.String screenName, java.lang.String emailAddress,
		java.lang.String supportTeamName, boolean andSearch, int start,
		int end, com.liferay.portal.kernel.util.OrderByComparator obc) {
		return getService()
				   .search(supportLaborId, firstName, middleName, lastName,
			screenName, emailAddress, supportTeamName, andSearch, start, end,
			obc);
	}

	public static java.util.List<com.liferay.osb.model.SupportWorker> search(
		long supportLaborId, java.lang.String keywords, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc) {
		return getService().search(supportLaborId, keywords, start, end, obc);
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

	public static void addSupportWorkers(long[] userIds, long supportTeamId,
		double[] maxWork, int[] escalationLevels, int[] roles,
		int[] notifications)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService()
			.addSupportWorkers(userIds, supportTeamId, maxWork,
			escalationLevels, roles, notifications);
	}

	public static void clockInOut(long supportWorkerId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().clockInOut(supportWorkerId);
	}

	public static void decreaseAssignedWork(long userId, double work) {
		getService().decreaseAssignedWork(userId, work);
	}

	public static void decreaseTicketEntryAssignedWork(long ticketEntryId,
		double work) {
		getService().decreaseTicketEntryAssignedWork(ticketEntryId, work);
	}

	public static void deleteSupportWorkers(long userId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteSupportWorkers(userId);
	}

	public static void deleteSupportWorkers(long[] userIds, long supportTeamId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteSupportWorkers(userIds, supportTeamId);
	}

	public static void increaseAssignedWork(long userId, double work) {
		getService().increaseAssignedWork(userId, work);
	}

	public static void increaseTicketEntryAssignedWork(long ticketEntryId,
		double work) {
		getService().increaseTicketEntryAssignedWork(ticketEntryId, work);
	}

	public static void recalculateUtilization() {
		getService().recalculateUtilization();
	}

	public static void clearService() {
		_service = null;
	}

	public static SupportWorkerLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					SupportWorkerLocalService.class.getName());

			if (invokableLocalService instanceof SupportWorkerLocalService) {
				_service = (SupportWorkerLocalService)invokableLocalService;
			}
			else {
				_service = new SupportWorkerLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(SupportWorkerLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	private static SupportWorkerLocalService _service;
}