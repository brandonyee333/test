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
 * This class is a wrapper for {@link AppPackageLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AppPackageLocalService
 * @generated
 */
public class AppPackageLocalServiceWrapper implements AppPackageLocalService,
	ServiceWrapper<AppPackageLocalService> {
	public AppPackageLocalServiceWrapper(
		AppPackageLocalService appPackageLocalService) {
		_appPackageLocalService = appPackageLocalService;
	}

	/**
	* Adds the app package to the database. Also notifies the appropriate model listeners.
	*
	* @param appPackage the app package
	* @return the app package that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppPackage addAppPackage(
		com.liferay.osb.model.AppPackage appPackage)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appPackageLocalService.addAppPackage(appPackage);
	}

	/**
	* Creates a new app package with the primary key. Does not add the app package to the database.
	*
	* @param appPackageId the primary key for the new app package
	* @return the new app package
	*/
	public com.liferay.osb.model.AppPackage createAppPackage(long appPackageId) {
		return _appPackageLocalService.createAppPackage(appPackageId);
	}

	/**
	* Deletes the app package with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param appPackageId the primary key of the app package
	* @return the app package that was removed
	* @throws PortalException if a app package with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppPackage deleteAppPackage(long appPackageId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appPackageLocalService.deleteAppPackage(appPackageId);
	}

	/**
	* Deletes the app package from the database. Also notifies the appropriate model listeners.
	*
	* @param appPackage the app package
	* @return the app package that was removed
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppPackage deleteAppPackage(
		com.liferay.osb.model.AppPackage appPackage)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appPackageLocalService.deleteAppPackage(appPackage);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _appPackageLocalService.dynamicQuery();
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
		return _appPackageLocalService.dynamicQuery(dynamicQuery);
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
		return _appPackageLocalService.dynamicQuery(dynamicQuery, start, end);
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
		return _appPackageLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _appPackageLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.osb.model.AppPackage fetchAppPackage(long appPackageId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appPackageLocalService.fetchAppPackage(appPackageId);
	}

	/**
	* Returns the app package with the primary key.
	*
	* @param appPackageId the primary key of the app package
	* @return the app package
	* @throws PortalException if a app package with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppPackage getAppPackage(long appPackageId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appPackageLocalService.getAppPackage(appPackageId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appPackageLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the app packages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of app packages
	* @param end the upper bound of the range of app packages (not inclusive)
	* @return the range of app packages
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppPackage> getAppPackages(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appPackageLocalService.getAppPackages(start, end);
	}

	/**
	* Returns the number of app packages.
	*
	* @return the number of app packages
	* @throws SystemException if a system exception occurred
	*/
	public int getAppPackagesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appPackageLocalService.getAppPackagesCount();
	}

	/**
	* Updates the app package in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param appPackage the app package
	* @return the app package that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppPackage updateAppPackage(
		com.liferay.osb.model.AppPackage appPackage)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appPackageLocalService.updateAppPackage(appPackage);
	}

	/**
	* Updates the app package in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param appPackage the app package
	* @param merge whether to merge the app package with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the app package that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppPackage updateAppPackage(
		com.liferay.osb.model.AppPackage appPackage, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appPackageLocalService.updateAppPackage(appPackage, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _appPackageLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_appPackageLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _appPackageLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public com.liferay.osb.model.AppPackage addAppPackage(long appEntryId,
		long appVersionId, int compatibility, boolean compatibilityPlus)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appPackageLocalService.addAppPackage(appEntryId, appVersionId,
			compatibility, compatibilityPlus);
	}

	public void addAppPackageSrc(long userId, long appPackageId,
		java.lang.String fileName, java.io.File file)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_appPackageLocalService.addAppPackageSrc(userId, appPackageId,
			fileName, file);
	}

	public void copyAppPackages(long sourceAppVersionId, long targetAppVersionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_appPackageLocalService.copyAppPackages(sourceAppVersionId,
			targetAppVersionId);
	}

	public com.liferay.osb.model.AppPackage fetchAppPackage(long appVersionId,
		int compatibility)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appPackageLocalService.fetchAppPackage(appVersionId,
			compatibility);
	}

	public com.liferay.osb.model.AppPackage fetchCompatibleAppPackage(
		long appEntryId, int compatibility, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appPackageLocalService.fetchCompatibleAppPackage(appEntryId,
			compatibility, status);
	}

	public java.util.List<com.liferay.osb.model.AppPackage> getAppPackages(
		long appVersionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appPackageLocalService.getAppPackages(appVersionId, start, end,
			obc);
	}

	public int getAppPackagesCount(long appVersionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appPackageLocalService.getAppPackagesCount(appVersionId);
	}

	public java.util.List<com.liferay.osb.model.AppPackage> getPrepackagedAppPackages(
		int portalBuildNumber, java.lang.String identifyingName,
		java.lang.String identifyingVersion)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appPackageLocalService.getPrepackagedAppPackages(portalBuildNumber,
			identifyingName, identifyingVersion);
	}

	public boolean hasContextNames(long appPackageId,
		java.lang.String[] contextNames)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appPackageLocalService.hasContextNames(appPackageId,
			contextNames);
	}

	public boolean isAppPackagePortalRestartRequired(long appPackageId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appPackageLocalService.isAppPackagePortalRestartRequired(appPackageId);
	}

	public com.liferay.osb.model.AppPackage updateAppPackage(
		long appPackageId, boolean compatibilityPlus)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appPackageLocalService.updateAppPackage(appPackageId,
			compatibilityPlus);
	}

	public com.liferay.osb.model.AppPackage updateAppPackagePrepackaged(
		long appPackageId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appPackageLocalService.updateAppPackagePrepackaged(appPackageId);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public AppPackageLocalService getWrappedAppPackageLocalService() {
		return _appPackageLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedAppPackageLocalService(
		AppPackageLocalService appPackageLocalService) {
		_appPackageLocalService = appPackageLocalService;
	}

	public AppPackageLocalService getWrappedService() {
		return _appPackageLocalService;
	}

	public void setWrappedService(AppPackageLocalService appPackageLocalService) {
		_appPackageLocalService = appPackageLocalService;
	}

	private AppPackageLocalService _appPackageLocalService;
}