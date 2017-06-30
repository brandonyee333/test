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
 * The utility for the support worker component local service. This utility wraps {@link com.liferay.osb.service.impl.SupportWorkerComponentLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SupportWorkerComponentLocalService
 * @see com.liferay.osb.service.base.SupportWorkerComponentLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.SupportWorkerComponentLocalServiceImpl
 * @generated
 */
public class SupportWorkerComponentLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.SupportWorkerComponentLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the support worker component to the database. Also notifies the appropriate model listeners.
	*
	* @param supportWorkerComponent the support worker component
	* @return the support worker component that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportWorkerComponent addSupportWorkerComponent(
		com.liferay.osb.model.SupportWorkerComponent supportWorkerComponent)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addSupportWorkerComponent(supportWorkerComponent);
	}

	/**
	* Creates a new support worker component with the primary key. Does not add the support worker component to the database.
	*
	* @param supportWorkerComponentId the primary key for the new support worker component
	* @return the new support worker component
	*/
	public static com.liferay.osb.model.SupportWorkerComponent createSupportWorkerComponent(
		long supportWorkerComponentId) {
		return getService()
				   .createSupportWorkerComponent(supportWorkerComponentId);
	}

	/**
	* Deletes the support worker component with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param supportWorkerComponentId the primary key of the support worker component
	* @return the support worker component that was removed
	* @throws PortalException if a support worker component with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportWorkerComponent deleteSupportWorkerComponent(
		long supportWorkerComponentId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .deleteSupportWorkerComponent(supportWorkerComponentId);
	}

	/**
	* Deletes the support worker component from the database. Also notifies the appropriate model listeners.
	*
	* @param supportWorkerComponent the support worker component
	* @return the support worker component that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportWorkerComponent deleteSupportWorkerComponent(
		com.liferay.osb.model.SupportWorkerComponent supportWorkerComponent)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteSupportWorkerComponent(supportWorkerComponent);
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

	public static com.liferay.osb.model.SupportWorkerComponent fetchSupportWorkerComponent(
		long supportWorkerComponentId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchSupportWorkerComponent(supportWorkerComponentId);
	}

	/**
	* Returns the support worker component with the primary key.
	*
	* @param supportWorkerComponentId the primary key of the support worker component
	* @return the support worker component
	* @throws PortalException if a support worker component with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportWorkerComponent getSupportWorkerComponent(
		long supportWorkerComponentId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getSupportWorkerComponent(supportWorkerComponentId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the support worker components.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of support worker components
	* @param end the upper bound of the range of support worker components (not inclusive)
	* @return the range of support worker components
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SupportWorkerComponent> getSupportWorkerComponents(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSupportWorkerComponents(start, end);
	}

	/**
	* Returns the number of support worker components.
	*
	* @return the number of support worker components
	* @throws SystemException if a system exception occurred
	*/
	public static int getSupportWorkerComponentsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSupportWorkerComponentsCount();
	}

	/**
	* Updates the support worker component in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param supportWorkerComponent the support worker component
	* @return the support worker component that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportWorkerComponent updateSupportWorkerComponent(
		com.liferay.osb.model.SupportWorkerComponent supportWorkerComponent)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateSupportWorkerComponent(supportWorkerComponent);
	}

	/**
	* Updates the support worker component in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param supportWorkerComponent the support worker component
	* @param merge whether to merge the support worker component with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the support worker component that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportWorkerComponent updateSupportWorkerComponent(
		com.liferay.osb.model.SupportWorkerComponent supportWorkerComponent,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateSupportWorkerComponent(supportWorkerComponent, merge);
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

	public static java.util.List<com.liferay.osb.model.SupportWorkerComponent> getSupportWorkerComponents(
		long supportWorkerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSupportWorkerComponents(supportWorkerId);
	}

	public static void setSupportWorkerComponents(long supportWorkerId,
		int[] components)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().setSupportWorkerComponents(supportWorkerId, components);
	}

	public static void clearService() {
		_service = null;
	}

	public static SupportWorkerComponentLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					SupportWorkerComponentLocalService.class.getName());

			if (invokableLocalService instanceof SupportWorkerComponentLocalService) {
				_service = (SupportWorkerComponentLocalService)invokableLocalService;
			}
			else {
				_service = new SupportWorkerComponentLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(SupportWorkerComponentLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(SupportWorkerComponentLocalService service) {
	}

	private static SupportWorkerComponentLocalService _service;
}