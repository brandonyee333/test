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
 * The utility for the app package plugin local service. This utility wraps {@link com.liferay.osb.service.impl.AppPackagePluginLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AppPackagePluginLocalService
 * @see com.liferay.osb.service.base.AppPackagePluginLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.AppPackagePluginLocalServiceImpl
 * @generated
 */
public class AppPackagePluginLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.AppPackagePluginLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the app package plugin to the database. Also notifies the appropriate model listeners.
	*
	* @param appPackagePlugin the app package plugin
	* @return the app package plugin that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPackagePlugin addAppPackagePlugin(
		com.liferay.osb.model.AppPackagePlugin appPackagePlugin)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addAppPackagePlugin(appPackagePlugin);
	}

	/**
	* Creates a new app package plugin with the primary key. Does not add the app package plugin to the database.
	*
	* @param appPackagePluginId the primary key for the new app package plugin
	* @return the new app package plugin
	*/
	public static com.liferay.osb.model.AppPackagePlugin createAppPackagePlugin(
		long appPackagePluginId) {
		return getService().createAppPackagePlugin(appPackagePluginId);
	}

	/**
	* Deletes the app package plugin with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param appPackagePluginId the primary key of the app package plugin
	* @return the app package plugin that was removed
	* @throws PortalException if a app package plugin with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPackagePlugin deleteAppPackagePlugin(
		long appPackagePluginId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteAppPackagePlugin(appPackagePluginId);
	}

	/**
	* Deletes the app package plugin from the database. Also notifies the appropriate model listeners.
	*
	* @param appPackagePlugin the app package plugin
	* @return the app package plugin that was removed
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPackagePlugin deleteAppPackagePlugin(
		com.liferay.osb.model.AppPackagePlugin appPackagePlugin)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteAppPackagePlugin(appPackagePlugin);
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

	public static com.liferay.osb.model.AppPackagePlugin fetchAppPackagePlugin(
		long appPackagePluginId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchAppPackagePlugin(appPackagePluginId);
	}

	/**
	* Returns the app package plugin with the primary key.
	*
	* @param appPackagePluginId the primary key of the app package plugin
	* @return the app package plugin
	* @throws PortalException if a app package plugin with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPackagePlugin getAppPackagePlugin(
		long appPackagePluginId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getAppPackagePlugin(appPackagePluginId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
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
	public static java.util.List<com.liferay.osb.model.AppPackagePlugin> getAppPackagePlugins(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAppPackagePlugins(start, end);
	}

	/**
	* Returns the number of app package plugins.
	*
	* @return the number of app package plugins
	* @throws SystemException if a system exception occurred
	*/
	public static int getAppPackagePluginsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAppPackagePluginsCount();
	}

	/**
	* Updates the app package plugin in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param appPackagePlugin the app package plugin
	* @return the app package plugin that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPackagePlugin updateAppPackagePlugin(
		com.liferay.osb.model.AppPackagePlugin appPackagePlugin)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateAppPackagePlugin(appPackagePlugin);
	}

	/**
	* Updates the app package plugin in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param appPackagePlugin the app package plugin
	* @param merge whether to merge the app package plugin with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the app package plugin that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPackagePlugin updateAppPackagePlugin(
		com.liferay.osb.model.AppPackagePlugin appPackagePlugin, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateAppPackagePlugin(appPackagePlugin, merge);
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

	public static com.liferay.osb.model.AppPackagePlugin addAppPackagePlugin(
		long userId, long appVersionId, java.lang.String fileName,
		java.io.File file, int compatibility, boolean compatibilityPlus)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addAppPackagePlugin(userId, appVersionId, fileName, file,
			compatibility, compatibilityPlus);
	}

	public static void copyAppPackagePlugins(long sourceAppPackageId,
		long targetAppPackageId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.copyAppPackagePlugins(sourceAppPackageId, targetAppPackageId);
	}

	public static void deleteAppPackagePlugins(long appPackageId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteAppPackagePlugins(appPackageId);
	}

	public static void deleteInvalidAppPackagePlugins(long appVersionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteInvalidAppPackagePlugins(appVersionId);
	}

	public static java.util.List<com.liferay.osb.model.AppPackagePlugin> getAppPackagePlugins(
		long appPackageId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAppPackagePlugins(appPackageId);
	}

	public static java.util.List<com.liferay.osb.model.AppPackagePlugin> getAppPackagePlugins(
		long appPackageId, boolean paclEnabled)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAppPackagePlugins(appPackageId, paclEnabled);
	}

	public static int getAppPackagePluginsCount(long appPackageId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAppPackagePluginsCount(appPackageId);
	}

	public static int getAppPackagePluginsCount(long appPackageId,
		boolean paclEnabled)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAppPackagePluginsCount(appPackageId, paclEnabled);
	}

	public static java.util.List<com.liferay.osb.model.AppPackagePlugin> getOtherAppPackagePlugins(
		long appEntryId, java.lang.String contextName)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getOtherAppPackagePlugins(appEntryId, contextName);
	}

	public static int getOtherAppPackagePluginsCount(long appEntryId,
		java.lang.String contextName)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getOtherAppPackagePluginsCount(appEntryId, contextName);
	}

	public static void validateAppPackagePlugin(
		com.liferay.osb.model.AppVersion appVersion,
		com.liferay.osb.model.AppPackage appPackage,
		com.liferay.osb.model.AppPackagePlugin appPackagePlugin)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.validateAppPackagePlugin(appVersion, appPackage, appPackagePlugin);
	}

	public static void clearService() {
		_service = null;
	}

	public static AppPackagePluginLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					AppPackagePluginLocalService.class.getName());

			if (invokableLocalService instanceof AppPackagePluginLocalService) {
				_service = (AppPackagePluginLocalService)invokableLocalService;
			}
			else {
				_service = new AppPackagePluginLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(AppPackagePluginLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(AppPackagePluginLocalService service) {
	}

	private static AppPackagePluginLocalService _service;
}