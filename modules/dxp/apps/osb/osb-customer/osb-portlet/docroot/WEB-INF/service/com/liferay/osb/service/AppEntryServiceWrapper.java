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
 * This class is a wrapper for {@link AppEntryService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AppEntryService
 * @generated
 */
public class AppEntryServiceWrapper implements AppEntryService,
	ServiceWrapper<AppEntryService> {
	public AppEntryServiceWrapper(AppEntryService appEntryService) {
		_appEntryService = appEntryService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _appEntryService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_appEntryService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _appEntryService.invokeMethod(name, parameterTypes, arguments);
	}

	public com.liferay.osb.model.AppEntry addAppEntry(long developerEntryId,
		java.lang.String title,
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
		return _appEntryService.addAppEntry(developerEntryId, title,
			descriptionMap, website, demoWebsite, documentationWebsite,
			referenceWebsite, sourceCodeWebsite, supportWebsite, labs,
			productType, version, changeLogMap, iconFile, paclEnabled,
			eulaContentMap, licenseType, orderURL, hidden, portalRequired,
			serviceContext);
	}

	public com.liferay.osb.model.AssetAttachment addAppEntryMedia(
		long appEntryId, java.lang.String fileName, java.io.File file)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appEntryService.addAppEntryMedia(appEntryId, fileName, file);
	}

	public java.io.File buildLiferayPackage(
		com.liferay.osb.model.AppPackage appPackage, boolean licensed)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appEntryService.buildLiferayPackage(appPackage, licensed);
	}

	public com.liferay.osb.model.AppEntry deleteAppEntry(long appEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appEntryService.deleteAppEntry(appEntryId);
	}

	public void deleteAppEntryMedia(long appEntryId, long assetAttachmentId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_appEntryService.deleteAppEntryMedia(appEntryId, assetAttachmentId);
	}

	public com.liferay.osb.model.AppEntry getAppEntry(long appEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appEntryService.getAppEntry(appEntryId);
	}

	public com.liferay.osb.model.AppEntry updateAppEntry(long appEntryId,
		long licenseLifetime, boolean supported, long[] standardQuantities,
		boolean standardUnlimited, long[] developerQuantities,
		boolean developerUnlimited, boolean trial)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appEntryService.updateAppEntry(appEntryId, licenseLifetime,
			supported, standardQuantities, standardUnlimited,
			developerQuantities, developerUnlimited, trial);
	}

	public com.liferay.osb.model.AppEntry updateAppEntry(long appEntryId,
		java.lang.String version,
		java.util.Map<java.util.Locale, java.lang.String> changeLogMap,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appEntryService.updateAppEntry(appEntryId, version,
			changeLogMap, serviceContext);
	}

	public com.liferay.osb.model.AppEntry updateAppEntry(long appEntryId,
		java.lang.String title,
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
		return _appEntryService.updateAppEntry(appEntryId, title,
			descriptionMap, website, demoWebsite, documentationWebsite,
			referenceWebsite, sourceCodeWebsite, supportWebsite, labs,
			productType, version, versionOrder, changeLogMap, iconFile,
			paclEnabled, releaseType, eulaContentMap, licenseType, orderURL,
			hidden, portalRequired, status, serviceContext);
	}

	public com.liferay.osb.model.AppEntry updateStatus(long appEntryId,
		int status, java.lang.String statusMessage,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appEntryService.updateStatus(appEntryId, status, statusMessage,
			serviceContext);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public AppEntryService getWrappedAppEntryService() {
		return _appEntryService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedAppEntryService(AppEntryService appEntryService) {
		_appEntryService = appEntryService;
	}

	public AppEntryService getWrappedService() {
		return _appEntryService;
	}

	public void setWrappedService(AppEntryService appEntryService) {
		_appEntryService = appEntryService;
	}

	private AppEntryService _appEntryService;
}