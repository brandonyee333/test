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
import com.liferay.portal.service.InvokableService;

/**
 * The utility for the app package plugin remote service. This utility wraps {@link com.liferay.osb.service.impl.AppPackagePluginServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AppPackagePluginService
 * @see com.liferay.osb.service.base.AppPackagePluginServiceBaseImpl
 * @see com.liferay.osb.service.impl.AppPackagePluginServiceImpl
 * @generated
 */
public class AppPackagePluginServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.AppPackagePluginServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

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

	public static com.liferay.osb.model.AppPackagePlugin addAppPackagePlugin(
		long appVersionId, java.lang.String fileName, java.io.File file,
		int compatibility, boolean compatibilityPlus)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addAppPackagePlugin(appVersionId, fileName, file,
			compatibility, compatibilityPlus);
	}

	public static com.liferay.osb.model.AppPackagePlugin deleteAppPackagePlugin(
		long appPackagePluginId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteAppPackagePlugin(appPackagePluginId);
	}

	public static void deleteInvalidAppPackagePlugins(long appVersionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteInvalidAppPackagePlugins(appVersionId);
	}

	public static void clearService() {
		_service = null;
	}

	public static AppPackagePluginService getService() {
		if (_service == null) {
			InvokableService invokableService = (InvokableService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					AppPackagePluginService.class.getName());

			if (invokableService instanceof AppPackagePluginService) {
				_service = (AppPackagePluginService)invokableService;
			}
			else {
				_service = new AppPackagePluginServiceClp(invokableService);
			}

			ReferenceRegistry.registerReference(AppPackagePluginServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(AppPackagePluginService service) {
	}

	private static AppPackagePluginService _service;
}