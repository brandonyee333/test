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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * The utility for the ticket worker local service. This utility wraps {@link com.liferay.osb.service.impl.TicketWorkerLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TicketWorkerLocalService
 * @see com.liferay.osb.service.base.TicketWorkerLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.TicketWorkerLocalServiceImpl
 * @generated
 */
public class TicketWorkerLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.TicketWorkerLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the ticket worker to the database. Also notifies the appropriate model listeners.
	*
	* @param ticketWorker the ticket worker
	* @return the ticket worker that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketWorker addTicketWorker(
		com.liferay.osb.model.TicketWorker ticketWorker)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addTicketWorker(ticketWorker);
	}

	/**
	* Creates a new ticket worker with the primary key. Does not add the ticket worker to the database.
	*
	* @param ticketWorkerId the primary key for the new ticket worker
	* @return the new ticket worker
	*/
	public static com.liferay.osb.model.TicketWorker createTicketWorker(
		long ticketWorkerId) {
		return getService().createTicketWorker(ticketWorkerId);
	}

	/**
	* Deletes the ticket worker with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketWorkerId the primary key of the ticket worker
	* @return the ticket worker that was removed
	* @throws PortalException if a ticket worker with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketWorker deleteTicketWorker(
		long ticketWorkerId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteTicketWorker(ticketWorkerId);
	}

	/**
	* Deletes the ticket worker from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketWorker the ticket worker
	* @return the ticket worker that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketWorker deleteTicketWorker(
		com.liferay.osb.model.TicketWorker ticketWorker)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteTicketWorker(ticketWorker);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery);
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
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
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
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	public static com.liferay.osb.model.TicketWorker fetchTicketWorker(
		long ticketWorkerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchTicketWorker(ticketWorkerId);
	}

	/**
	* Returns the ticket worker with the primary key.
	*
	* @param ticketWorkerId the primary key of the ticket worker
	* @return the ticket worker
	* @throws PortalException if a ticket worker with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketWorker getTicketWorker(
		long ticketWorkerId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getTicketWorker(ticketWorkerId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the ticket workers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of ticket workers
	* @param end the upper bound of the range of ticket workers (not inclusive)
	* @return the range of ticket workers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TicketWorker> getTicketWorkers(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getTicketWorkers(start, end);
	}

	/**
	* Returns the number of ticket workers.
	*
	* @return the number of ticket workers
	* @throws SystemException if a system exception occurred
	*/
	public static int getTicketWorkersCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getTicketWorkersCount();
	}

	/**
	* Updates the ticket worker in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ticketWorker the ticket worker
	* @return the ticket worker that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketWorker updateTicketWorker(
		com.liferay.osb.model.TicketWorker ticketWorker)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateTicketWorker(ticketWorker);
	}

	/**
	* Updates the ticket worker in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ticketWorker the ticket worker
	* @param merge whether to merge the ticket worker with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the ticket worker that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketWorker updateTicketWorker(
		com.liferay.osb.model.TicketWorker ticketWorker, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateTicketWorker(ticketWorker, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	public static java.util.List<com.liferay.osb.model.TicketWorker> addTicketWorkers(
		long userId, long[] userIds, long ticketEntryId,
		long[] sourceClassNameIds, long[] sourceClassPKs, int[] roles,
		long primaryUserId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addTicketWorkers(userId, userIds, ticketEntryId,
			sourceClassNameIds, sourceClassPKs, roles, primaryUserId);
	}

	public static void deleteTicketWorkers(long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteTicketWorkers(userId);
	}

	public static void deleteTicketWorkers(long userId, long[] userIds,
		long ticketEntryId, long primaryUserId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.deleteTicketWorkers(userId, userIds, ticketEntryId, primaryUserId);
	}

	public static com.liferay.osb.model.TicketWorker fetchLatestTicketWorker(
		long ticketEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchLatestTicketWorker(ticketEntryId);
	}

	public static com.liferay.osb.model.TicketWorker fetchPrimaryTicketWorker(
		long ticketEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchPrimaryTicketWorker(ticketEntryId);
	}

	public static com.liferay.osb.model.TicketWorker fetchTicketWorker(
		long userId, long ticketEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchTicketWorker(userId, ticketEntryId);
	}

	public static com.liferay.osb.model.TicketWorker getTicketWorker(
		long userId, long ticketEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getTicketWorker(userId, ticketEntryId);
	}

	public static java.util.List<com.liferay.osb.model.TicketWorker> getTicketWorkers(
		long ticketEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getTicketWorkers(ticketEntryId);
	}

	public static java.util.List<com.liferay.osb.model.TicketWorker> getTicketWorkers(
		long sourceClassNameId, long sourceClassPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getTicketWorkers(sourceClassNameId, sourceClassPK);
	}

	public static java.util.List<com.liferay.osb.model.TicketWorker> getUserTicketWorkers(
		long userId) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getUserTicketWorkers(userId);
	}

	public static int getUserTicketWorkersCount(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getUserTicketWorkersCount(userId);
	}

	public static boolean hasTicketWorker(long userId, long ticketEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().hasTicketWorker(userId, ticketEntryId);
	}

	public static void clearService() {
		_service = null;
	}

	public static TicketWorkerLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					TicketWorkerLocalService.class.getName());

			if (invokableLocalService instanceof TicketWorkerLocalService) {
				_service = (TicketWorkerLocalService)invokableLocalService;
			}
			else {
				_service = new TicketWorkerLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(TicketWorkerLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(TicketWorkerLocalService service) {
	}

	private static TicketWorkerLocalService _service;
}