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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.service.BaseService;
import com.liferay.portal.service.InvokableService;

/**
 * The interface for the app entry remote service.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AppEntryServiceUtil
 * @see com.liferay.osb.service.base.AppEntryServiceBaseImpl
 * @see com.liferay.osb.service.impl.AppEntryServiceImpl
 * @generated
 */
@JSONWebService
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface AppEntryService extends BaseService, InvokableService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AppEntryServiceUtil} to access the app entry remote service. Add custom service methods to {@link com.liferay.osb.service.impl.AppEntryServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier();

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier);

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable;

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
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.AssetAttachment addAppEntryMedia(
		long appEntryId, java.lang.String fileName, java.io.File file)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public java.io.File buildLiferayPackage(
		com.liferay.osb.model.AppPackage appPackage, boolean licensed)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.AppEntry deleteAppEntry(long appEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public void deleteAppEntryMedia(long appEntryId, long assetAttachmentId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.osb.model.AppEntry getAppEntry(long appEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.AppEntry updateAppEntry(long appEntryId,
		long licenseLifetime, boolean supported, long[] standardQuantities,
		boolean standardUnlimited, long[] developerQuantities,
		boolean developerUnlimited, boolean trial)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.AppEntry updateAppEntry(long appEntryId,
		java.lang.String version,
		java.util.Map<java.util.Locale, java.lang.String> changeLogMap,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

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
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.AppEntry updateStatus(long appEntryId,
		int status, java.lang.String statusMessage,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;
}