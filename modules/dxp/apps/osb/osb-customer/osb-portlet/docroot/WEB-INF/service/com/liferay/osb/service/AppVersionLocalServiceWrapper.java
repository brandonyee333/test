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
 * This class is a wrapper for {@link AppVersionLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AppVersionLocalService
 * @generated
 */
public class AppVersionLocalServiceWrapper implements AppVersionLocalService,
	ServiceWrapper<AppVersionLocalService> {
	public AppVersionLocalServiceWrapper(
		AppVersionLocalService appVersionLocalService) {
		_appVersionLocalService = appVersionLocalService;
	}

	/**
	* Adds the app version to the database. Also notifies the appropriate model listeners.
	*
	* @param appVersion the app version
	* @return the app version that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppVersion addAppVersion(
		com.liferay.osb.model.AppVersion appVersion)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appVersionLocalService.addAppVersion(appVersion);
	}

	/**
	* Creates a new app version with the primary key. Does not add the app version to the database.
	*
	* @param appVersionId the primary key for the new app version
	* @return the new app version
	*/
	public com.liferay.osb.model.AppVersion createAppVersion(long appVersionId) {
		return _appVersionLocalService.createAppVersion(appVersionId);
	}

	/**
	* Deletes the app version with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param appVersionId the primary key of the app version
	* @return the app version that was removed
	* @throws PortalException if a app version with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppVersion deleteAppVersion(long appVersionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appVersionLocalService.deleteAppVersion(appVersionId);
	}

	/**
	* Deletes the app version from the database. Also notifies the appropriate model listeners.
	*
	* @param appVersion the app version
	* @return the app version that was removed
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppVersion deleteAppVersion(
		com.liferay.osb.model.AppVersion appVersion)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appVersionLocalService.deleteAppVersion(appVersion);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _appVersionLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appVersionLocalService.dynamicQuery(dynamicQuery);
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
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _appVersionLocalService.dynamicQuery(dynamicQuery, start, end);
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
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appVersionLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appVersionLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.osb.model.AppVersion fetchAppVersion(long appVersionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appVersionLocalService.fetchAppVersion(appVersionId);
	}

	/**
	* Returns the app version with the primary key.
	*
	* @param appVersionId the primary key of the app version
	* @return the app version
	* @throws PortalException if a app version with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppVersion getAppVersion(long appVersionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appVersionLocalService.getAppVersion(appVersionId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appVersionLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the app versions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of app versions
	* @param end the upper bound of the range of app versions (not inclusive)
	* @return the range of app versions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppVersion> getAppVersions(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appVersionLocalService.getAppVersions(start, end);
	}

	/**
	* Returns the number of app versions.
	*
	* @return the number of app versions
	* @throws SystemException if a system exception occurred
	*/
	public int getAppVersionsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appVersionLocalService.getAppVersionsCount();
	}

	/**
	* Updates the app version in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param appVersion the app version
	* @return the app version that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppVersion updateAppVersion(
		com.liferay.osb.model.AppVersion appVersion)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appVersionLocalService.updateAppVersion(appVersion);
	}

	/**
	* Updates the app version in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param appVersion the app version
	* @param merge whether to merge the app version with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the app version that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppVersion updateAppVersion(
		com.liferay.osb.model.AppVersion appVersion, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appVersionLocalService.updateAppVersion(appVersion, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _appVersionLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_appVersionLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _appVersionLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public com.liferay.osb.model.AppVersion fetchAppVersion(long appEntryId,
		int status, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appVersionLocalService.fetchAppVersion(appEntryId, status, obc);
	}

	public com.liferay.osb.model.AppVersion fetchAppVersion(long appEntryId,
		int[] statuses, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appVersionLocalService.fetchAppVersion(appEntryId, statuses, obc);
	}

	public com.liferay.osb.model.AppVersion fetchAppVersion(long appEntryId,
		java.lang.String version)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appVersionLocalService.fetchAppVersion(appEntryId, version);
	}

	public java.util.List<com.liferay.osb.model.AppVersion> getAppVersions(
		long appEntryId, int compatibility, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appVersionLocalService.getAppVersions(appEntryId,
			compatibility, status, start, end);
	}

	public java.util.List<com.liferay.osb.model.AppVersion> getAppVersions(
		long appEntryId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appVersionLocalService.getAppVersions(appEntryId, status,
			start, end, obc);
	}

	public java.util.List<com.liferay.osb.model.AppVersion> getAppVersions(
		long appEntryId, int[] statuses, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appVersionLocalService.getAppVersions(appEntryId, statuses,
			start, end, obc);
	}

	public int getAppVersionsByReleaseTypeCount(long appEntryId, int releaseType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appVersionLocalService.getAppVersionsByReleaseTypeCount(appEntryId,
			releaseType);
	}

	public int getAppVersionsCount(long appEntryId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appVersionLocalService.getAppVersionsCount(appEntryId, status);
	}

	public int getAppVersionsCount(long appEntryId, int[] statuses)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appVersionLocalService.getAppVersionsCount(appEntryId, statuses);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public AppVersionLocalService getWrappedAppVersionLocalService() {
		return _appVersionLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedAppVersionLocalService(
		AppVersionLocalService appVersionLocalService) {
		_appVersionLocalService = appVersionLocalService;
	}

	public AppVersionLocalService getWrappedService() {
		return _appVersionLocalService;
	}

	public void setWrappedService(AppVersionLocalService appVersionLocalService) {
		_appVersionLocalService = appVersionLocalService;
	}

	private AppVersionLocalService _appVersionLocalService;
}