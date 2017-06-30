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
 * The utility for the app package remote service. This utility wraps {@link com.liferay.osb.service.impl.AppPackageServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AppPackageService
 * @see com.liferay.osb.service.base.AppPackageServiceBaseImpl
 * @see com.liferay.osb.service.impl.AppPackageServiceImpl
 * @generated
 */
public class AppPackageServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.AppPackageServiceImpl} and rerun ServiceBuilder to regenerate this class.
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

	public static com.liferay.osb.model.AppPackage addAppPackage(
		long appEntryId, long appVersionId, int compatibility,
		boolean compatibilityPlus)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addAppPackage(appEntryId, appVersionId, compatibility,
			compatibilityPlus);
	}

	public static void addAppPackageSrc(long appPackageId,
		java.lang.String fileName, java.io.File file)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().addAppPackageSrc(appPackageId, fileName, file);
	}

	public static com.liferay.osb.model.AppPackage deleteAppPackage(
		long appPackageId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteAppPackage(appPackageId);
	}

	public static void deleteAppPackageSrc(long appPackageId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteAppPackageSrc(appPackageId);
	}

	public static com.liferay.osb.model.AppPackage fetchAppPackage(
		long appVersionId, int compatibility)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchAppPackage(appVersionId, compatibility);
	}

	public static com.liferay.osb.model.AppPackage getAppPackage(
		long appPackageId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getAppPackage(appPackageId);
	}

	public static com.liferay.osb.model.AppPackage updateAppPackage(
		long appPackageId, boolean compatibilityPlus)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().updateAppPackage(appPackageId, compatibilityPlus);
	}

	public static void clearService() {
		_service = null;
	}

	public static AppPackageService getService() {
		if (_service == null) {
			InvokableService invokableService = (InvokableService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					AppPackageService.class.getName());

			if (invokableService instanceof AppPackageService) {
				_service = (AppPackageService)invokableService;
			}
			else {
				_service = new AppPackageServiceClp(invokableService);
			}

			ReferenceRegistry.registerReference(AppPackageServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(AppPackageService service) {
	}

	private static AppPackageService _service;
}