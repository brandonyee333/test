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
 * The utility for the app entry remote service. This utility wraps {@link com.liferay.osb.service.impl.AppEntryServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AppEntryService
 * @see com.liferay.osb.service.base.AppEntryServiceBaseImpl
 * @see com.liferay.osb.service.impl.AppEntryServiceImpl
 * @generated
 */
public class AppEntryServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.AppEntryServiceImpl} and rerun ServiceBuilder to regenerate this class.
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

	public static com.liferay.osb.model.AppEntry addAppEntry(
		long developerEntryId, java.lang.String title,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String website, java.lang.String demoWebsite,
		java.lang.String documentationWebsite,
		java.lang.String referenceWebsite, java.lang.String sourceCodeWebsite,
		java.lang.String supportWebsite, boolean labs, int productType,
		java.lang.String version,
		java.util.Map<java.util.Locale, java.lang.String> changeLogMap,
		java.io.File iconFile, boolean paclEnabled,
		java.util.Map<java.util.Locale, java.lang.String> eulaContentMap,
		int licenseType, java.lang.String orderURL, boolean hidden,
		boolean portalRequired,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addAppEntry(developerEntryId, title, descriptionMap,
			website, demoWebsite, documentationWebsite, referenceWebsite,
			sourceCodeWebsite, supportWebsite, labs, productType, version,
			changeLogMap, iconFile, paclEnabled, eulaContentMap, licenseType,
			orderURL, hidden, portalRequired, serviceContext);
	}

	public static com.liferay.osb.model.AssetAttachment addAppEntryMedia(
		long appEntryId, java.lang.String fileName, java.io.File file)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().addAppEntryMedia(appEntryId, fileName, file);
	}

	public static java.io.File buildLiferayPackage(
		com.liferay.osb.model.AppPackage appPackage, boolean licensed)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().buildLiferayPackage(appPackage, licensed);
	}

	public static com.liferay.osb.model.AppEntry deleteAppEntry(long appEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteAppEntry(appEntryId);
	}

	public static void deleteAppEntryMedia(long appEntryId,
		long assetAttachmentId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteAppEntryMedia(appEntryId, assetAttachmentId);
	}

	public static com.liferay.osb.model.AppEntry getAppEntry(long appEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getAppEntry(appEntryId);
	}

	public static com.liferay.osb.model.AppEntry updateAppEntry(
		long appEntryId, long licenseLifetime, boolean supported,
		long[] standardQuantities, boolean standardUnlimited,
		long[] developerQuantities, boolean developerUnlimited, boolean trial)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateAppEntry(appEntryId, licenseLifetime, supported,
			standardQuantities, standardUnlimited, developerQuantities,
			developerUnlimited, trial);
	}

	public static com.liferay.osb.model.AppEntry updateAppEntry(
		long appEntryId, java.lang.String version,
		java.util.Map<java.util.Locale, java.lang.String> changeLogMap,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateAppEntry(appEntryId, version, changeLogMap,
			serviceContext);
	}

	public static com.liferay.osb.model.AppEntry updateAppEntry(
		long appEntryId, java.lang.String title,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String website, java.lang.String demoWebsite,
		java.lang.String documentationWebsite,
		java.lang.String referenceWebsite, java.lang.String sourceCodeWebsite,
		java.lang.String supportWebsite, boolean labs, int productType,
		java.lang.String version, int versionOrder,
		java.util.Map<java.util.Locale, java.lang.String> changeLogMap,
		java.io.File iconFile, boolean paclEnabled, int releaseType,
		java.util.Map<java.util.Locale, java.lang.String> eulaContentMap,
		int licenseType, java.lang.String orderURL, boolean hidden,
		boolean portalRequired, int status,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateAppEntry(appEntryId, title, descriptionMap, website,
			demoWebsite, documentationWebsite, referenceWebsite,
			sourceCodeWebsite, supportWebsite, labs, productType, version,
			versionOrder, changeLogMap, iconFile, paclEnabled, releaseType,
			eulaContentMap, licenseType, orderURL, hidden, portalRequired,
			status, serviceContext);
	}

	public static com.liferay.osb.model.AppEntry updateStatus(long appEntryId,
		int status, java.lang.String statusMessage,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateStatus(appEntryId, status, statusMessage,
			serviceContext);
	}

	public static void clearService() {
		_service = null;
	}

	public static AppEntryService getService() {
		if (_service == null) {
			InvokableService invokableService = (InvokableService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					AppEntryService.class.getName());

			if (invokableService instanceof AppEntryService) {
				_service = (AppEntryService)invokableService;
			}
			else {
				_service = new AppEntryServiceClp(invokableService);
			}

			ReferenceRegistry.registerReference(AppEntryServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(AppEntryService service) {
	}

	private static AppEntryService _service;
}