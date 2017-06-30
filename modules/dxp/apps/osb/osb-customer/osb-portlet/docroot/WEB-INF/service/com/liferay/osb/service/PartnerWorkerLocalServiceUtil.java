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
 * The utility for the partner worker local service. This utility wraps {@link com.liferay.osb.service.impl.PartnerWorkerLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PartnerWorkerLocalService
 * @see com.liferay.osb.service.base.PartnerWorkerLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.PartnerWorkerLocalServiceImpl
 * @generated
 */
public class PartnerWorkerLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.PartnerWorkerLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the partner worker to the database. Also notifies the appropriate model listeners.
	*
	* @param partnerWorker the partner worker
	* @return the partner worker that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.PartnerWorker addPartnerWorker(
		com.liferay.osb.model.PartnerWorker partnerWorker)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addPartnerWorker(partnerWorker);
	}

	/**
	* Creates a new partner worker with the primary key. Does not add the partner worker to the database.
	*
	* @param partnerWorkerId the primary key for the new partner worker
	* @return the new partner worker
	*/
	public static com.liferay.osb.model.PartnerWorker createPartnerWorker(
		long partnerWorkerId) {
		return getService().createPartnerWorker(partnerWorkerId);
	}

	/**
	* Deletes the partner worker with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param partnerWorkerId the primary key of the partner worker
	* @return the partner worker that was removed
	* @throws PortalException if a partner worker with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.PartnerWorker deletePartnerWorker(
		long partnerWorkerId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deletePartnerWorker(partnerWorkerId);
	}

	/**
	* Deletes the partner worker from the database. Also notifies the appropriate model listeners.
	*
	* @param partnerWorker the partner worker
	* @return the partner worker that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.PartnerWorker deletePartnerWorker(
		com.liferay.osb.model.PartnerWorker partnerWorker)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deletePartnerWorker(partnerWorker);
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

	public static com.liferay.osb.model.PartnerWorker fetchPartnerWorker(
		long partnerWorkerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchPartnerWorker(partnerWorkerId);
	}

	/**
	* Returns the partner worker with the primary key.
	*
	* @param partnerWorkerId the primary key of the partner worker
	* @return the partner worker
	* @throws PortalException if a partner worker with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.PartnerWorker getPartnerWorker(
		long partnerWorkerId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPartnerWorker(partnerWorkerId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the partner workers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of partner workers
	* @param end the upper bound of the range of partner workers (not inclusive)
	* @return the range of partner workers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.PartnerWorker> getPartnerWorkers(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getPartnerWorkers(start, end);
	}

	/**
	* Returns the number of partner workers.
	*
	* @return the number of partner workers
	* @throws SystemException if a system exception occurred
	*/
	public static int getPartnerWorkersCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getPartnerWorkersCount();
	}

	/**
	* Updates the partner worker in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param partnerWorker the partner worker
	* @return the partner worker that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.PartnerWorker updatePartnerWorker(
		com.liferay.osb.model.PartnerWorker partnerWorker)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updatePartnerWorker(partnerWorker);
	}

	/**
	* Updates the partner worker in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param partnerWorker the partner worker
	* @param merge whether to merge the partner worker with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the partner worker that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.PartnerWorker updatePartnerWorker(
		com.liferay.osb.model.PartnerWorker partnerWorker, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updatePartnerWorker(partnerWorker, merge);
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

	public static void addPartnerWorkers(long[] userIds, long partnerEntryId,
		int[] roles, int[] notifications)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.addPartnerWorkers(userIds, partnerEntryId, roles, notifications);
	}

	public static void deletePartnerWorkers(long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deletePartnerWorkers(userId);
	}

	public static void deletePartnerWorkers(long[] userIds, long partnerEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deletePartnerWorkers(userIds, partnerEntryId);
	}

	public static com.liferay.osb.model.PartnerWorker fetchPartnerWorker(
		long userId, long partnerEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchPartnerWorker(userId, partnerEntryId);
	}

	public static com.liferay.osb.model.PartnerWorker getPartnerWorker(
		long userId, long partnerEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPartnerWorker(userId, partnerEntryId);
	}

	public static java.util.List<com.liferay.osb.model.PartnerWorker> getPartnerWorkers(
		long partnerEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getPartnerWorkers(partnerEntryId);
	}

	public static java.util.List<com.liferay.osb.model.PartnerWorker> getPartnerWorkers(
		long partnerEntryId, int role)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getPartnerWorkers(partnerEntryId, role);
	}

	public static java.util.List<com.liferay.osb.model.PartnerWorker> getUserPartnerWorkers(
		long userId) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getUserPartnerWorkers(userId);
	}

	public static boolean hasPartnerWorker(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().hasPartnerWorker(userId);
	}

	public static boolean hasPartnerWorker(long userId, long partnerEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().hasPartnerWorker(userId, partnerEntryId);
	}

	public static boolean hasPartnerWorkerRole(long userId, int role)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().hasPartnerWorkerRole(userId, role);
	}

	public static void clearService() {
		_service = null;
	}

	public static PartnerWorkerLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					PartnerWorkerLocalService.class.getName());

			if (invokableLocalService instanceof PartnerWorkerLocalService) {
				_service = (PartnerWorkerLocalService)invokableLocalService;
			}
			else {
				_service = new PartnerWorkerLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(PartnerWorkerLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(PartnerWorkerLocalService service) {
	}

	private static PartnerWorkerLocalService _service;
}