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
 * Provides the local service utility for SupportWorkerSeverity. This utility wraps
 * {@link com.liferay.osb.service.impl.SupportWorkerSeverityLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see SupportWorkerSeverityLocalService
 * @see com.liferay.osb.service.base.SupportWorkerSeverityLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.SupportWorkerSeverityLocalServiceImpl
 * @generated
 */
@ProviderType
public class SupportWorkerSeverityLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.SupportWorkerSeverityLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the support worker severity to the database. Also notifies the appropriate model listeners.
	*
	* @param supportWorkerSeverity the support worker severity
	* @return the support worker severity that was added
	*/
	public static com.liferay.osb.model.SupportWorkerSeverity addSupportWorkerSeverity(
		com.liferay.osb.model.SupportWorkerSeverity supportWorkerSeverity) {
		return getService().addSupportWorkerSeverity(supportWorkerSeverity);
	}

	/**
	* Creates a new support worker severity with the primary key. Does not add the support worker severity to the database.
	*
	* @param supportWorkerSeverityId the primary key for the new support worker severity
	* @return the new support worker severity
	*/
	public static com.liferay.osb.model.SupportWorkerSeverity createSupportWorkerSeverity(
		long supportWorkerSeverityId) {
		return getService().createSupportWorkerSeverity(supportWorkerSeverityId);
	}

	/**
	* Deletes the support worker severity from the database. Also notifies the appropriate model listeners.
	*
	* @param supportWorkerSeverity the support worker severity
	* @return the support worker severity that was removed
	*/
	public static com.liferay.osb.model.SupportWorkerSeverity deleteSupportWorkerSeverity(
		com.liferay.osb.model.SupportWorkerSeverity supportWorkerSeverity) {
		return getService().deleteSupportWorkerSeverity(supportWorkerSeverity);
	}

	/**
	* Deletes the support worker severity with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param supportWorkerSeverityId the primary key of the support worker severity
	* @return the support worker severity that was removed
	* @throws PortalException if a support worker severity with the primary key could not be found
	*/
	public static com.liferay.osb.model.SupportWorkerSeverity deleteSupportWorkerSeverity(
		long supportWorkerSeverityId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteSupportWorkerSeverity(supportWorkerSeverityId);
	}

	public static com.liferay.osb.model.SupportWorkerSeverity fetchSupportWorkerSeverity(
		long supportWorkerSeverityId) {
		return getService().fetchSupportWorkerSeverity(supportWorkerSeverityId);
	}

	/**
	* Returns the support worker severity with the primary key.
	*
	* @param supportWorkerSeverityId the primary key of the support worker severity
	* @return the support worker severity
	* @throws PortalException if a support worker severity with the primary key could not be found
	*/
	public static com.liferay.osb.model.SupportWorkerSeverity getSupportWorkerSeverity(
		long supportWorkerSeverityId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getSupportWorkerSeverity(supportWorkerSeverityId);
	}

	/**
	* Updates the support worker severity in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param supportWorkerSeverity the support worker severity
	* @return the support worker severity that was updated
	*/
	public static com.liferay.osb.model.SupportWorkerSeverity updateSupportWorkerSeverity(
		com.liferay.osb.model.SupportWorkerSeverity supportWorkerSeverity) {
		return getService().updateSupportWorkerSeverity(supportWorkerSeverity);
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
	* Returns the number of support worker severities.
	*
	* @return the number of support worker severities
	*/
	public static int getSupportWorkerSeveritiesCount() {
		return getService().getSupportWorkerSeveritiesCount();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.SupportWorkerSeverityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.SupportWorkerSeverityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Returns a range of all the support worker severities.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.SupportWorkerSeverityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of support worker severities
	* @param end the upper bound of the range of support worker severities (not inclusive)
	* @return the range of support worker severities
	*/
	public static java.util.List<com.liferay.osb.model.SupportWorkerSeverity> getSupportWorkerSeverities(
		int start, int end) {
		return getService().getSupportWorkerSeverities(start, end);
	}

	public static java.util.List<com.liferay.osb.model.SupportWorkerSeverity> getSupportWorkerSeverities(
		long supportWorkerId) {
		return getService().getSupportWorkerSeverities(supportWorkerId);
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

	public static void setSupportWorkerSeverities(long supportWorkerId,
		int[] severities) {
		getService().setSupportWorkerSeverities(supportWorkerId, severities);
	}

	public static void clearService() {
		_service = null;
	}

	public static SupportWorkerSeverityLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					SupportWorkerSeverityLocalService.class.getName());

			if (invokableLocalService instanceof SupportWorkerSeverityLocalService) {
				_service = (SupportWorkerSeverityLocalService)invokableLocalService;
			}
			else {
				_service = new SupportWorkerSeverityLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(SupportWorkerSeverityLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	private static SupportWorkerSeverityLocalService _service;
}