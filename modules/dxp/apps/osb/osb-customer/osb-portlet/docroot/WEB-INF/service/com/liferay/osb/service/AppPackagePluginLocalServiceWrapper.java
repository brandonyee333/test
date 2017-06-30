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
 * This class is a wrapper for {@link AppPackagePluginLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AppPackagePluginLocalService
 * @generated
 */
public class AppPackagePluginLocalServiceWrapper
	implements AppPackagePluginLocalService,
		ServiceWrapper<AppPackagePluginLocalService> {
	public AppPackagePluginLocalServiceWrapper(
		AppPackagePluginLocalService appPackagePluginLocalService) {
		_appPackagePluginLocalService = appPackagePluginLocalService;
	}

	/**
	* Adds the app package plugin to the database. Also notifies the appropriate model listeners.
	*
	* @param appPackagePlugin the app package plugin
	* @return the app package plugin that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppPackagePlugin addAppPackagePlugin(
		com.liferay.osb.model.AppPackagePlugin appPackagePlugin)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appPackagePluginLocalService.addAppPackagePlugin(appPackagePlugin);
	}

	/**
	* Creates a new app package plugin with the primary key. Does not add the app package plugin to the database.
	*
	* @param appPackagePluginId the primary key for the new app package plugin
	* @return the new app package plugin
	*/
	public com.liferay.osb.model.AppPackagePlugin createAppPackagePlugin(
		long appPackagePluginId) {
		return _appPackagePluginLocalService.createAppPackagePlugin(appPackagePluginId);
	}

	/**
	* Deletes the app package plugin with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param appPackagePluginId the primary key of the app package plugin
	* @return the app package plugin that was removed
	* @throws PortalException if a app package plugin with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppPackagePlugin deleteAppPackagePlugin(
		long appPackagePluginId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appPackagePluginLocalService.deleteAppPackagePlugin(appPackagePluginId);
	}

	/**
	* Deletes the app package plugin from the database. Also notifies the appropriate model listeners.
	*
	* @param appPackagePlugin the app package plugin
	* @return the app package plugin that was removed
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppPackagePlugin deleteAppPackagePlugin(
		com.liferay.osb.model.AppPackagePlugin appPackagePlugin)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appPackagePluginLocalService.deleteAppPackagePlugin(appPackagePlugin);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _appPackagePluginLocalService.dynamicQuery();
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
		return _appPackagePluginLocalService.dynamicQuery(dynamicQuery);
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
		return _appPackagePluginLocalService.dynamicQuery(dynamicQuery, start,
			end);
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
		return _appPackagePluginLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
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
		return _appPackagePluginLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.osb.model.AppPackagePlugin fetchAppPackagePlugin(
		long appPackagePluginId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appPackagePluginLocalService.fetchAppPackagePlugin(appPackagePluginId);
	}

	/**
	* Returns the app package plugin with the primary key.
	*
	* @param appPackagePluginId the primary key of the app package plugin
	* @return the app package plugin
	* @throws PortalException if a app package plugin with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppPackagePlugin getAppPackagePlugin(
		long appPackagePluginId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appPackagePluginLocalService.getAppPackagePlugin(appPackagePluginId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appPackagePluginLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the app package plugins.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of app package plugins
	* @param end the upper bound of the range of app package plugins (not inclusive)
	* @return the range of app package plugins
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppPackagePlugin> getAppPackagePlugins(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appPackagePluginLocalService.getAppPackagePlugins(start, end);
	}

	/**
	* Returns the number of app package plugins.
	*
	* @return the number of app package plugins
	* @throws SystemException if a system exception occurred
	*/
	public int getAppPackagePluginsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appPackagePluginLocalService.getAppPackagePluginsCount();
	}

	/**
	* Updates the app package plugin in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param appPackagePlugin the app package plugin
	* @return the app package plugin that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppPackagePlugin updateAppPackagePlugin(
		com.liferay.osb.model.AppPackagePlugin appPackagePlugin)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appPackagePluginLocalService.updateAppPackagePlugin(appPackagePlugin);
	}

	/**
	* Updates the app package plugin in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param appPackagePlugin the app package plugin
	* @param merge whether to merge the app package plugin with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the app package plugin that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppPackagePlugin updateAppPackagePlugin(
		com.liferay.osb.model.AppPackagePlugin appPackagePlugin, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appPackagePluginLocalService.updateAppPackagePlugin(appPackagePlugin,
			merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _appPackagePluginLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_appPackagePluginLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _appPackagePluginLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public com.liferay.osb.model.AppPackagePlugin addAppPackagePlugin(
		long userId, long appVersionId, java.lang.String fileName,
		java.io.File file, int compatibility, boolean compatibilityPlus)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appPackagePluginLocalService.addAppPackagePlugin(userId,
			appVersionId, fileName, file, compatibility, compatibilityPlus);
	}

	public void copyAppPackagePlugins(long sourceAppPackageId,
		long targetAppPackageId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_appPackagePluginLocalService.copyAppPackagePlugins(sourceAppPackageId,
			targetAppPackageId);
	}

	public void deleteAppPackagePlugins(long appPackageId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_appPackagePluginLocalService.deleteAppPackagePlugins(appPackageId);
	}

	public void deleteInvalidAppPackagePlugins(long appVersionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_appPackagePluginLocalService.deleteInvalidAppPackagePlugins(appVersionId);
	}

	public java.util.List<com.liferay.osb.model.AppPackagePlugin> getAppPackagePlugins(
		long appPackageId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appPackagePluginLocalService.getAppPackagePlugins(appPackageId);
	}

	public java.util.List<com.liferay.osb.model.AppPackagePlugin> getAppPackagePlugins(
		long appPackageId, boolean paclEnabled)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appPackagePluginLocalService.getAppPackagePlugins(appPackageId,
			paclEnabled);
	}

	public int getAppPackagePluginsCount(long appPackageId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appPackagePluginLocalService.getAppPackagePluginsCount(appPackageId);
	}

	public int getAppPackagePluginsCount(long appPackageId, boolean paclEnabled)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appPackagePluginLocalService.getAppPackagePluginsCount(appPackageId,
			paclEnabled);
	}

	public java.util.List<com.liferay.osb.model.AppPackagePlugin> getOtherAppPackagePlugins(
		long appEntryId, java.lang.String contextName)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appPackagePluginLocalService.getOtherAppPackagePlugins(appEntryId,
			contextName);
	}

	public int getOtherAppPackagePluginsCount(long appEntryId,
		java.lang.String contextName)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appPackagePluginLocalService.getOtherAppPackagePluginsCount(appEntryId,
			contextName);
	}

	public void validateAppPackagePlugin(
		com.liferay.osb.model.AppVersion appVersion,
		com.liferay.osb.model.AppPackage appPackage,
		com.liferay.osb.model.AppPackagePlugin appPackagePlugin)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_appPackagePluginLocalService.validateAppPackagePlugin(appVersion,
			appPackage, appPackagePlugin);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public AppPackagePluginLocalService getWrappedAppPackagePluginLocalService() {
		return _appPackagePluginLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedAppPackagePluginLocalService(
		AppPackagePluginLocalService appPackagePluginLocalService) {
		_appPackagePluginLocalService = appPackagePluginLocalService;
	}

	public AppPackagePluginLocalService getWrappedService() {
		return _appPackagePluginLocalService;
	}

	public void setWrappedService(
		AppPackagePluginLocalService appPackagePluginLocalService) {
		_appPackagePluginLocalService = appPackagePluginLocalService;
	}

	private AppPackagePluginLocalService _appPackagePluginLocalService;
}