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
 * Provides the local service utility for PartnerWorker. This utility wraps
 * {@link com.liferay.osb.service.impl.PartnerWorkerLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see PartnerWorkerLocalService
 * @see com.liferay.osb.service.base.PartnerWorkerLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.PartnerWorkerLocalServiceImpl
 * @generated
 */
@ProviderType
public class PartnerWorkerLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.PartnerWorkerLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static boolean hasPartnerWorker(long userId) {
		return getService().hasPartnerWorker(userId);
	}

	public static boolean hasPartnerWorker(long userId, long partnerEntryId) {
		return getService().hasPartnerWorker(userId, partnerEntryId);
	}

	public static boolean hasPartnerWorkerRole(long userId, int role) {
		return getService().hasPartnerWorkerRole(userId, role);
	}

	/**
	* Adds the partner worker to the database. Also notifies the appropriate model listeners.
	*
	* @param partnerWorker the partner worker
	* @return the partner worker that was added
	*/
	public static com.liferay.osb.model.PartnerWorker addPartnerWorker(
		com.liferay.osb.model.PartnerWorker partnerWorker) {
		return getService().addPartnerWorker(partnerWorker);
	}

	public static com.liferay.osb.model.PartnerWorker addPartnerWorker(
		long partnerEntryId, java.lang.String emailAddress, int role)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().addPartnerWorker(partnerEntryId, emailAddress, role);
	}

	public static com.liferay.osb.model.PartnerWorker addPartnerWorker(
		long partnerEntryId, long userId, int role)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().addPartnerWorker(partnerEntryId, userId, role);
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
	* Deletes the partner worker from the database. Also notifies the appropriate model listeners.
	*
	* @param partnerWorker the partner worker
	* @return the partner worker that was removed
	*/
	public static com.liferay.osb.model.PartnerWorker deletePartnerWorker(
		com.liferay.osb.model.PartnerWorker partnerWorker) {
		return getService().deletePartnerWorker(partnerWorker);
	}

	/**
	* Deletes the partner worker with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param partnerWorkerId the primary key of the partner worker
	* @return the partner worker that was removed
	* @throws PortalException if a partner worker with the primary key could not be found
	*/
	public static com.liferay.osb.model.PartnerWorker deletePartnerWorker(
		long partnerWorkerId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePartnerWorker(partnerWorkerId);
	}

	public static com.liferay.osb.model.PartnerWorker fetchPartnerWorker(
		long partnerWorkerId) {
		return getService().fetchPartnerWorker(partnerWorkerId);
	}

	public static com.liferay.osb.model.PartnerWorker fetchPartnerWorker(
		long userId, long partnerEntryId) {
		return getService().fetchPartnerWorker(userId, partnerEntryId);
	}

	/**
	* Returns the partner worker with the primary key.
	*
	* @param partnerWorkerId the primary key of the partner worker
	* @return the partner worker
	* @throws PortalException if a partner worker with the primary key could not be found
	*/
	public static com.liferay.osb.model.PartnerWorker getPartnerWorker(
		long partnerWorkerId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPartnerWorker(partnerWorkerId);
	}

	public static com.liferay.osb.model.PartnerWorker getPartnerWorker(
		long userId, long partnerEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPartnerWorker(userId, partnerEntryId);
	}

	/**
	* Updates the partner worker in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param partnerWorker the partner worker
	* @return the partner worker that was updated
	*/
	public static com.liferay.osb.model.PartnerWorker updatePartnerWorker(
		com.liferay.osb.model.PartnerWorker partnerWorker) {
		return getService().updatePartnerWorker(partnerWorker);
	}

	public static com.liferay.osb.model.PartnerWorker updatePartnerWorker(
		long partnerWorkerId, int role)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().updatePartnerWorker(partnerWorkerId, role);
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

	/**
	* Returns the number of partner workers.
	*
	* @return the number of partner workers
	*/
	public static int getPartnerWorkersCount() {
		return getService().getPartnerWorkersCount();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.PartnerWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.PartnerWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Returns a range of all the partner workers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.PartnerWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of partner workers
	* @param end the upper bound of the range of partner workers (not inclusive)
	* @return the range of partner workers
	*/
	public static java.util.List<com.liferay.osb.model.PartnerWorker> getPartnerWorkers(
		int start, int end) {
		return getService().getPartnerWorkers(start, end);
	}

	public static java.util.List<com.liferay.osb.model.PartnerWorker> getPartnerWorkers(
		long partnerEntryId) {
		return getService().getPartnerWorkers(partnerEntryId);
	}

	public static java.util.List<com.liferay.osb.model.PartnerWorker> getPartnerWorkers(
		long partnerEntryId, int role) {
		return getService().getPartnerWorkers(partnerEntryId, role);
	}

	public static java.util.List<com.liferay.osb.model.PartnerWorker> getUserPartnerWorkers(
		long userId) {
		return getService().getUserPartnerWorkers(userId);
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

	public static void deletePartnerWorkers(long userId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deletePartnerWorkers(userId);
	}

	public static void syncPartnerWorkers(long partnerEntryId,
		java.lang.String oldDossieraAccountKey,
		java.lang.String newDossieraAccountKey)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService()
			.syncPartnerWorkers(partnerEntryId, oldDossieraAccountKey,
			newDossieraAccountKey);
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

	private static PartnerWorkerLocalService _service;
}