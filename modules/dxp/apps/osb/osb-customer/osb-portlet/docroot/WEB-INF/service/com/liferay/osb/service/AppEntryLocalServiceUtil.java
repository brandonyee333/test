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
 * The utility for the app entry local service. This utility wraps {@link com.liferay.osb.service.impl.AppEntryLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AppEntryLocalService
 * @see com.liferay.osb.service.base.AppEntryLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.AppEntryLocalServiceImpl
 * @generated
 */
public class AppEntryLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.AppEntryLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the app entry to the database. Also notifies the appropriate model listeners.
	*
	* @param appEntry the app entry
	* @return the app entry that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppEntry addAppEntry(
		com.liferay.osb.model.AppEntry appEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addAppEntry(appEntry);
	}

	/**
	* Creates a new app entry with the primary key. Does not add the app entry to the database.
	*
	* @param appEntryId the primary key for the new app entry
	* @return the new app entry
	*/
	public static com.liferay.osb.model.AppEntry createAppEntry(long appEntryId) {
		return getService().createAppEntry(appEntryId);
	}

	/**
	* Deletes the app entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param appEntryId the primary key of the app entry
	* @return the app entry that was removed
	* @throws PortalException if a app entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppEntry deleteAppEntry(long appEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteAppEntry(appEntryId);
	}

	/**
	* Deletes the app entry from the database. Also notifies the appropriate model listeners.
	*
	* @param appEntry the app entry
	* @return the app entry that was removed
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppEntry deleteAppEntry(
		com.liferay.osb.model.AppEntry appEntry)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteAppEntry(appEntry);
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

	public static com.liferay.osb.model.AppEntry fetchAppEntry(long appEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchAppEntry(appEntryId);
	}

	/**
	* Returns the app entry with the primary key.
	*
	* @param appEntryId the primary key of the app entry
	* @return the app entry
	* @throws PortalException if a app entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppEntry getAppEntry(long appEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getAppEntry(appEntryId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the app entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of app entries
	* @param end the upper bound of the range of app entries (not inclusive)
	* @return the range of app entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AppEntry> getAppEntries(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAppEntries(start, end);
	}

	/**
	* Returns the number of app entries.
	*
	* @return the number of app entries
	* @throws SystemException if a system exception occurred
	*/
	public static int getAppEntriesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAppEntriesCount();
	}

	/**
	* Updates the app entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param appEntry the app entry
	* @return the app entry that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppEntry updateAppEntry(
		com.liferay.osb.model.AppEntry appEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateAppEntry(appEntry);
	}

	/**
	* Updates the app entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param appEntry the app entry
	* @param merge whether to merge the app entry with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the app entry that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppEntry updateAppEntry(
		com.liferay.osb.model.AppEntry appEntry, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateAppEntry(appEntry, merge);
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

	public static com.liferay.osb.model.AppEntry addAppEntry(long userId,
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
				   .addAppEntry(userId, developerEntryId, title,
			descriptionMap, website, demoWebsite, documentationWebsite,
			referenceWebsite, sourceCodeWebsite, supportWebsite, labs,
			productType, version, changeLogMap, iconFile, paclEnabled,
			eulaContentMap, licenseType, orderURL, hidden, portalRequired,
			serviceContext);
	}

	public static void addEntryResources(com.liferay.osb.model.AppEntry entry,
		boolean addCommunityPermissions, boolean addGuestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.addEntryResources(entry, addCommunityPermissions,
			addGuestPermissions);
	}

	public static void addEntryResources(com.liferay.osb.model.AppEntry entry,
		java.lang.String[] communityPermissions,
		java.lang.String[] guestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.addEntryResources(entry, communityPermissions, guestPermissions);
	}

	public static java.io.File buildLiferayPackage(
		com.liferay.osb.model.AppPackage appPackage, boolean licensed)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().buildLiferayPackage(appPackage, licensed);
	}

	public static void buildLiferayPackages(
		com.liferay.osb.model.AppVersion appVersion)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().buildLiferayPackages(appVersion);
	}

	public static void expireAppEntries(long developerEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().expireAppEntries(developerEntryId);
	}

	public static java.util.List<com.liferay.osb.model.AppEntry> getAppEntries(
		int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAppEntries(status, start, end);
	}

	public static java.util.List<com.liferay.osb.model.AppEntry> getAppEntries(
		long developerEntryId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getAppEntries(developerEntryId, status, start, end, obc);
	}

	public static java.util.List<com.liferay.osb.model.AppEntry> getAppEntries(
		long developerEntryId, java.lang.String title, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAppEntries(developerEntryId, title, start, end);
	}

	public static int getAppEntriesCount(int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAppEntriesCount(status);
	}

	public static int getAppEntriesCount(long developerEntryId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAppEntriesCount(developerEntryId, status);
	}

	public static int getAppEntriesCount(long developerEntryId,
		java.lang.String title)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAppEntriesCount(developerEntryId, title);
	}

	public static com.liferay.osb.model.AppEntry getAppEntry(
		java.lang.String title)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getAppEntry(title);
	}

	public static java.util.List<com.liferay.osb.model.AppEntry> getPrepackagedAppEntries(
		int compatibility)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPrepackagedAppEntries(compatibility);
	}

	public static int getPrepackagedAppEntriesCount(int compatibility)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPrepackagedAppEntriesCount(compatibility);
	}

	public static com.liferay.osb.model.AppEntry incrementDownloadCount(
		long userId, long appVersionId, int domain)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().incrementDownloadCount(userId, appVersionId, domain);
	}

	public static java.util.List<com.liferay.osb.model.AppEntry> search(
		java.lang.String title,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andSearch, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().search(title, params, andSearch, start, end, obc);
	}

	public static int searchCount(java.lang.String title,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andSearch)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().searchCount(title, params, andSearch);
	}

	public static com.liferay.osb.model.AppEntry updateAppEntry(long userId,
		long appEntryId, long licenseLifetime, boolean supported,
		long[] standardQuantities, boolean standardUnlimited,
		long[] developerQuantities, boolean developerUnlimited, boolean trial)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateAppEntry(userId, appEntryId, licenseLifetime,
			supported, standardQuantities, standardUnlimited,
			developerQuantities, developerUnlimited, trial);
	}

	public static com.liferay.osb.model.AppEntry updateAppEntry(long userId,
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
				   .updateAppEntry(userId, appEntryId, title, descriptionMap,
			website, demoWebsite, documentationWebsite, referenceWebsite,
			sourceCodeWebsite, supportWebsite, labs, productType, version,
			versionOrder, changeLogMap, iconFile, paclEnabled, releaseType,
			eulaContentMap, licenseType, orderURL, hidden, portalRequired,
			status, serviceContext);
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

	public static void updateAsset(long userId,
		com.liferay.osb.model.AppEntry appEntry,
		com.liferay.osb.model.AppVersion appVersion, long[] assetCategoryIds,
		java.lang.String[] assetTagNames)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.updateAsset(userId, appEntry, appVersion, assetCategoryIds,
			assetTagNames);
	}

	public static com.liferay.osb.model.AppEntry updateStatus(long userId,
		long appEntryId, int status, java.lang.String statusMessage,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateStatus(userId, appEntryId, status, statusMessage,
			serviceContext);
	}

	public static void validatePurchase(long appEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().validatePurchase(appEntryId);
	}

	public static void clearService() {
		_service = null;
	}

	public static AppEntryLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					AppEntryLocalService.class.getName());

			if (invokableLocalService instanceof AppEntryLocalService) {
				_service = (AppEntryLocalService)invokableLocalService;
			}
			else {
				_service = new AppEntryLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(AppEntryLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(AppEntryLocalService service) {
	}

	private static AppEntryLocalService _service;
}