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

package com.liferay.osb.service.persistence;

import com.liferay.osb.model.AppPackagePlugin;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the app package plugin service. This utility wraps {@link AppPackagePluginPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AppPackagePluginPersistence
 * @see AppPackagePluginPersistenceImpl
 * @generated
 */
public class AppPackagePluginUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(AppPackagePlugin appPackagePlugin) {
		getPersistence().clearCache(appPackagePlugin);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<AppPackagePlugin> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AppPackagePlugin> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AppPackagePlugin> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static AppPackagePlugin update(AppPackagePlugin appPackagePlugin,
		boolean merge) throws SystemException {
		return getPersistence().update(appPackagePlugin, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static AppPackagePlugin update(AppPackagePlugin appPackagePlugin,
		boolean merge, ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(appPackagePlugin, merge, serviceContext);
	}

	/**
	* Caches the app package plugin in the entity cache if it is enabled.
	*
	* @param appPackagePlugin the app package plugin
	*/
	public static void cacheResult(
		com.liferay.osb.model.AppPackagePlugin appPackagePlugin) {
		getPersistence().cacheResult(appPackagePlugin);
	}

	/**
	* Caches the app package plugins in the entity cache if it is enabled.
	*
	* @param appPackagePlugins the app package plugins
	*/
	public static void cacheResult(
		java.util.List<com.liferay.osb.model.AppPackagePlugin> appPackagePlugins) {
		getPersistence().cacheResult(appPackagePlugins);
	}

	/**
	* Creates a new app package plugin with the primary key. Does not add the app package plugin to the database.
	*
	* @param appPackagePluginId the primary key for the new app package plugin
	* @return the new app package plugin
	*/
	public static com.liferay.osb.model.AppPackagePlugin create(
		long appPackagePluginId) {
		return getPersistence().create(appPackagePluginId);
	}

	/**
	* Removes the app package plugin with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param appPackagePluginId the primary key of the app package plugin
	* @return the app package plugin that was removed
	* @throws com.liferay.osb.NoSuchAppPackagePluginException if a app package plugin with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPackagePlugin remove(
		long appPackagePluginId)
		throws com.liferay.osb.NoSuchAppPackagePluginException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(appPackagePluginId);
	}

	public static com.liferay.osb.model.AppPackagePlugin updateImpl(
		com.liferay.osb.model.AppPackagePlugin appPackagePlugin, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(appPackagePlugin, merge);
	}

	/**
	* Returns the app package plugin with the primary key or throws a {@link com.liferay.osb.NoSuchAppPackagePluginException} if it could not be found.
	*
	* @param appPackagePluginId the primary key of the app package plugin
	* @return the app package plugin
	* @throws com.liferay.osb.NoSuchAppPackagePluginException if a app package plugin with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPackagePlugin findByPrimaryKey(
		long appPackagePluginId)
		throws com.liferay.osb.NoSuchAppPackagePluginException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(appPackagePluginId);
	}

	/**
	* Returns the app package plugin with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param appPackagePluginId the primary key of the app package plugin
	* @return the app package plugin, or <code>null</code> if a app package plugin with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPackagePlugin fetchByPrimaryKey(
		long appPackagePluginId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(appPackagePluginId);
	}

	/**
	* Returns all the app package plugins where appPackageId = &#63;.
	*
	* @param appPackageId the app package ID
	* @return the matching app package plugins
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AppPackagePlugin> findByAppPackageId(
		long appPackageId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAppPackageId(appPackageId);
	}

	/**
	* Returns a range of all the app package plugins where appPackageId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param appPackageId the app package ID
	* @param start the lower bound of the range of app package plugins
	* @param end the upper bound of the range of app package plugins (not inclusive)
	* @return the range of matching app package plugins
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AppPackagePlugin> findByAppPackageId(
		long appPackageId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAppPackageId(appPackageId, start, end);
	}

	/**
	* Returns an ordered range of all the app package plugins where appPackageId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param appPackageId the app package ID
	* @param start the lower bound of the range of app package plugins
	* @param end the upper bound of the range of app package plugins (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching app package plugins
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AppPackagePlugin> findByAppPackageId(
		long appPackageId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAppPackageId(appPackageId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first app package plugin in the ordered set where appPackageId = &#63;.
	*
	* @param appPackageId the app package ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app package plugin
	* @throws com.liferay.osb.NoSuchAppPackagePluginException if a matching app package plugin could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPackagePlugin findByAppPackageId_First(
		long appPackageId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppPackagePluginException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAppPackageId_First(appPackageId, orderByComparator);
	}

	/**
	* Returns the first app package plugin in the ordered set where appPackageId = &#63;.
	*
	* @param appPackageId the app package ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app package plugin, or <code>null</code> if a matching app package plugin could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPackagePlugin fetchByAppPackageId_First(
		long appPackageId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAppPackageId_First(appPackageId, orderByComparator);
	}

	/**
	* Returns the last app package plugin in the ordered set where appPackageId = &#63;.
	*
	* @param appPackageId the app package ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app package plugin
	* @throws com.liferay.osb.NoSuchAppPackagePluginException if a matching app package plugin could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPackagePlugin findByAppPackageId_Last(
		long appPackageId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppPackagePluginException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAppPackageId_Last(appPackageId, orderByComparator);
	}

	/**
	* Returns the last app package plugin in the ordered set where appPackageId = &#63;.
	*
	* @param appPackageId the app package ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app package plugin, or <code>null</code> if a matching app package plugin could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPackagePlugin fetchByAppPackageId_Last(
		long appPackageId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAppPackageId_Last(appPackageId, orderByComparator);
	}

	/**
	* Returns the app package plugins before and after the current app package plugin in the ordered set where appPackageId = &#63;.
	*
	* @param appPackagePluginId the primary key of the current app package plugin
	* @param appPackageId the app package ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next app package plugin
	* @throws com.liferay.osb.NoSuchAppPackagePluginException if a app package plugin with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPackagePlugin[] findByAppPackageId_PrevAndNext(
		long appPackagePluginId, long appPackageId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppPackagePluginException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAppPackageId_PrevAndNext(appPackagePluginId,
			appPackageId, orderByComparator);
	}

	/**
	* Returns the app package plugin where assetAttachmentId = &#63; or throws a {@link com.liferay.osb.NoSuchAppPackagePluginException} if it could not be found.
	*
	* @param assetAttachmentId the asset attachment ID
	* @return the matching app package plugin
	* @throws com.liferay.osb.NoSuchAppPackagePluginException if a matching app package plugin could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPackagePlugin findByAssetAttachmentId(
		long assetAttachmentId)
		throws com.liferay.osb.NoSuchAppPackagePluginException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAssetAttachmentId(assetAttachmentId);
	}

	/**
	* Returns the app package plugin where assetAttachmentId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param assetAttachmentId the asset attachment ID
	* @return the matching app package plugin, or <code>null</code> if a matching app package plugin could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPackagePlugin fetchByAssetAttachmentId(
		long assetAttachmentId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByAssetAttachmentId(assetAttachmentId);
	}

	/**
	* Returns the app package plugin where assetAttachmentId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param assetAttachmentId the asset attachment ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching app package plugin, or <code>null</code> if a matching app package plugin could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPackagePlugin fetchByAssetAttachmentId(
		long assetAttachmentId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAssetAttachmentId(assetAttachmentId,
			retrieveFromCache);
	}

	/**
	* Returns all the app package plugins where contextName = &#63;.
	*
	* @param contextName the context name
	* @return the matching app package plugins
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AppPackagePlugin> findByContextName(
		java.lang.String contextName)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByContextName(contextName);
	}

	/**
	* Returns a range of all the app package plugins where contextName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param contextName the context name
	* @param start the lower bound of the range of app package plugins
	* @param end the upper bound of the range of app package plugins (not inclusive)
	* @return the range of matching app package plugins
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AppPackagePlugin> findByContextName(
		java.lang.String contextName, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByContextName(contextName, start, end);
	}

	/**
	* Returns an ordered range of all the app package plugins where contextName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param contextName the context name
	* @param start the lower bound of the range of app package plugins
	* @param end the upper bound of the range of app package plugins (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching app package plugins
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AppPackagePlugin> findByContextName(
		java.lang.String contextName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByContextName(contextName, start, end, orderByComparator);
	}

	/**
	* Returns the first app package plugin in the ordered set where contextName = &#63;.
	*
	* @param contextName the context name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app package plugin
	* @throws com.liferay.osb.NoSuchAppPackagePluginException if a matching app package plugin could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPackagePlugin findByContextName_First(
		java.lang.String contextName,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppPackagePluginException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByContextName_First(contextName, orderByComparator);
	}

	/**
	* Returns the first app package plugin in the ordered set where contextName = &#63;.
	*
	* @param contextName the context name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app package plugin, or <code>null</code> if a matching app package plugin could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPackagePlugin fetchByContextName_First(
		java.lang.String contextName,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByContextName_First(contextName, orderByComparator);
	}

	/**
	* Returns the last app package plugin in the ordered set where contextName = &#63;.
	*
	* @param contextName the context name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app package plugin
	* @throws com.liferay.osb.NoSuchAppPackagePluginException if a matching app package plugin could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPackagePlugin findByContextName_Last(
		java.lang.String contextName,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppPackagePluginException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByContextName_Last(contextName, orderByComparator);
	}

	/**
	* Returns the last app package plugin in the ordered set where contextName = &#63;.
	*
	* @param contextName the context name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app package plugin, or <code>null</code> if a matching app package plugin could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPackagePlugin fetchByContextName_Last(
		java.lang.String contextName,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByContextName_Last(contextName, orderByComparator);
	}

	/**
	* Returns the app package plugins before and after the current app package plugin in the ordered set where contextName = &#63;.
	*
	* @param appPackagePluginId the primary key of the current app package plugin
	* @param contextName the context name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next app package plugin
	* @throws com.liferay.osb.NoSuchAppPackagePluginException if a app package plugin with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPackagePlugin[] findByContextName_PrevAndNext(
		long appPackagePluginId, java.lang.String contextName,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppPackagePluginException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByContextName_PrevAndNext(appPackagePluginId,
			contextName, orderByComparator);
	}

	/**
	* Returns all the app package plugins where appPackageId = &#63; and contextName = &#63;.
	*
	* @param appPackageId the app package ID
	* @param contextName the context name
	* @return the matching app package plugins
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AppPackagePlugin> findByAPI_CN(
		long appPackageId, java.lang.String contextName)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAPI_CN(appPackageId, contextName);
	}

	/**
	* Returns a range of all the app package plugins where appPackageId = &#63; and contextName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param appPackageId the app package ID
	* @param contextName the context name
	* @param start the lower bound of the range of app package plugins
	* @param end the upper bound of the range of app package plugins (not inclusive)
	* @return the range of matching app package plugins
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AppPackagePlugin> findByAPI_CN(
		long appPackageId, java.lang.String contextName, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAPI_CN(appPackageId, contextName, start, end);
	}

	/**
	* Returns an ordered range of all the app package plugins where appPackageId = &#63; and contextName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param appPackageId the app package ID
	* @param contextName the context name
	* @param start the lower bound of the range of app package plugins
	* @param end the upper bound of the range of app package plugins (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching app package plugins
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AppPackagePlugin> findByAPI_CN(
		long appPackageId, java.lang.String contextName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAPI_CN(appPackageId, contextName, start, end,
			orderByComparator);
	}

	/**
	* Returns the first app package plugin in the ordered set where appPackageId = &#63; and contextName = &#63;.
	*
	* @param appPackageId the app package ID
	* @param contextName the context name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app package plugin
	* @throws com.liferay.osb.NoSuchAppPackagePluginException if a matching app package plugin could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPackagePlugin findByAPI_CN_First(
		long appPackageId, java.lang.String contextName,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppPackagePluginException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAPI_CN_First(appPackageId, contextName,
			orderByComparator);
	}

	/**
	* Returns the first app package plugin in the ordered set where appPackageId = &#63; and contextName = &#63;.
	*
	* @param appPackageId the app package ID
	* @param contextName the context name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app package plugin, or <code>null</code> if a matching app package plugin could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPackagePlugin fetchByAPI_CN_First(
		long appPackageId, java.lang.String contextName,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAPI_CN_First(appPackageId, contextName,
			orderByComparator);
	}

	/**
	* Returns the last app package plugin in the ordered set where appPackageId = &#63; and contextName = &#63;.
	*
	* @param appPackageId the app package ID
	* @param contextName the context name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app package plugin
	* @throws com.liferay.osb.NoSuchAppPackagePluginException if a matching app package plugin could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPackagePlugin findByAPI_CN_Last(
		long appPackageId, java.lang.String contextName,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppPackagePluginException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAPI_CN_Last(appPackageId, contextName,
			orderByComparator);
	}

	/**
	* Returns the last app package plugin in the ordered set where appPackageId = &#63; and contextName = &#63;.
	*
	* @param appPackageId the app package ID
	* @param contextName the context name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app package plugin, or <code>null</code> if a matching app package plugin could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPackagePlugin fetchByAPI_CN_Last(
		long appPackageId, java.lang.String contextName,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAPI_CN_Last(appPackageId, contextName,
			orderByComparator);
	}

	/**
	* Returns the app package plugins before and after the current app package plugin in the ordered set where appPackageId = &#63; and contextName = &#63;.
	*
	* @param appPackagePluginId the primary key of the current app package plugin
	* @param appPackageId the app package ID
	* @param contextName the context name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next app package plugin
	* @throws com.liferay.osb.NoSuchAppPackagePluginException if a app package plugin with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPackagePlugin[] findByAPI_CN_PrevAndNext(
		long appPackagePluginId, long appPackageId,
		java.lang.String contextName,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppPackagePluginException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAPI_CN_PrevAndNext(appPackagePluginId, appPackageId,
			contextName, orderByComparator);
	}

	/**
	* Returns all the app package plugins where appPackageId = &#63; and fileName = &#63;.
	*
	* @param appPackageId the app package ID
	* @param fileName the file name
	* @return the matching app package plugins
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AppPackagePlugin> findByAPI_FN(
		long appPackageId, java.lang.String fileName)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAPI_FN(appPackageId, fileName);
	}

	/**
	* Returns a range of all the app package plugins where appPackageId = &#63; and fileName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param appPackageId the app package ID
	* @param fileName the file name
	* @param start the lower bound of the range of app package plugins
	* @param end the upper bound of the range of app package plugins (not inclusive)
	* @return the range of matching app package plugins
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AppPackagePlugin> findByAPI_FN(
		long appPackageId, java.lang.String fileName, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAPI_FN(appPackageId, fileName, start, end);
	}

	/**
	* Returns an ordered range of all the app package plugins where appPackageId = &#63; and fileName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param appPackageId the app package ID
	* @param fileName the file name
	* @param start the lower bound of the range of app package plugins
	* @param end the upper bound of the range of app package plugins (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching app package plugins
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AppPackagePlugin> findByAPI_FN(
		long appPackageId, java.lang.String fileName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAPI_FN(appPackageId, fileName, start, end,
			orderByComparator);
	}

	/**
	* Returns the first app package plugin in the ordered set where appPackageId = &#63; and fileName = &#63;.
	*
	* @param appPackageId the app package ID
	* @param fileName the file name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app package plugin
	* @throws com.liferay.osb.NoSuchAppPackagePluginException if a matching app package plugin could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPackagePlugin findByAPI_FN_First(
		long appPackageId, java.lang.String fileName,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppPackagePluginException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAPI_FN_First(appPackageId, fileName, orderByComparator);
	}

	/**
	* Returns the first app package plugin in the ordered set where appPackageId = &#63; and fileName = &#63;.
	*
	* @param appPackageId the app package ID
	* @param fileName the file name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app package plugin, or <code>null</code> if a matching app package plugin could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPackagePlugin fetchByAPI_FN_First(
		long appPackageId, java.lang.String fileName,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAPI_FN_First(appPackageId, fileName,
			orderByComparator);
	}

	/**
	* Returns the last app package plugin in the ordered set where appPackageId = &#63; and fileName = &#63;.
	*
	* @param appPackageId the app package ID
	* @param fileName the file name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app package plugin
	* @throws com.liferay.osb.NoSuchAppPackagePluginException if a matching app package plugin could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPackagePlugin findByAPI_FN_Last(
		long appPackageId, java.lang.String fileName,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppPackagePluginException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAPI_FN_Last(appPackageId, fileName, orderByComparator);
	}

	/**
	* Returns the last app package plugin in the ordered set where appPackageId = &#63; and fileName = &#63;.
	*
	* @param appPackageId the app package ID
	* @param fileName the file name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app package plugin, or <code>null</code> if a matching app package plugin could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPackagePlugin fetchByAPI_FN_Last(
		long appPackageId, java.lang.String fileName,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAPI_FN_Last(appPackageId, fileName, orderByComparator);
	}

	/**
	* Returns the app package plugins before and after the current app package plugin in the ordered set where appPackageId = &#63; and fileName = &#63;.
	*
	* @param appPackagePluginId the primary key of the current app package plugin
	* @param appPackageId the app package ID
	* @param fileName the file name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next app package plugin
	* @throws com.liferay.osb.NoSuchAppPackagePluginException if a app package plugin with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPackagePlugin[] findByAPI_FN_PrevAndNext(
		long appPackagePluginId, long appPackageId, java.lang.String fileName,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppPackagePluginException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAPI_FN_PrevAndNext(appPackagePluginId, appPackageId,
			fileName, orderByComparator);
	}

	/**
	* Returns all the app package plugins where appPackageId = &#63; and paclEnabled = &#63;.
	*
	* @param appPackageId the app package ID
	* @param paclEnabled the pacl enabled
	* @return the matching app package plugins
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AppPackagePlugin> findByAPI_PE(
		long appPackageId, boolean paclEnabled)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAPI_PE(appPackageId, paclEnabled);
	}

	/**
	* Returns a range of all the app package plugins where appPackageId = &#63; and paclEnabled = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param appPackageId the app package ID
	* @param paclEnabled the pacl enabled
	* @param start the lower bound of the range of app package plugins
	* @param end the upper bound of the range of app package plugins (not inclusive)
	* @return the range of matching app package plugins
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AppPackagePlugin> findByAPI_PE(
		long appPackageId, boolean paclEnabled, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAPI_PE(appPackageId, paclEnabled, start, end);
	}

	/**
	* Returns an ordered range of all the app package plugins where appPackageId = &#63; and paclEnabled = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param appPackageId the app package ID
	* @param paclEnabled the pacl enabled
	* @param start the lower bound of the range of app package plugins
	* @param end the upper bound of the range of app package plugins (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching app package plugins
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AppPackagePlugin> findByAPI_PE(
		long appPackageId, boolean paclEnabled, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAPI_PE(appPackageId, paclEnabled, start, end,
			orderByComparator);
	}

	/**
	* Returns the first app package plugin in the ordered set where appPackageId = &#63; and paclEnabled = &#63;.
	*
	* @param appPackageId the app package ID
	* @param paclEnabled the pacl enabled
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app package plugin
	* @throws com.liferay.osb.NoSuchAppPackagePluginException if a matching app package plugin could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPackagePlugin findByAPI_PE_First(
		long appPackageId, boolean paclEnabled,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppPackagePluginException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAPI_PE_First(appPackageId, paclEnabled,
			orderByComparator);
	}

	/**
	* Returns the first app package plugin in the ordered set where appPackageId = &#63; and paclEnabled = &#63;.
	*
	* @param appPackageId the app package ID
	* @param paclEnabled the pacl enabled
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app package plugin, or <code>null</code> if a matching app package plugin could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPackagePlugin fetchByAPI_PE_First(
		long appPackageId, boolean paclEnabled,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAPI_PE_First(appPackageId, paclEnabled,
			orderByComparator);
	}

	/**
	* Returns the last app package plugin in the ordered set where appPackageId = &#63; and paclEnabled = &#63;.
	*
	* @param appPackageId the app package ID
	* @param paclEnabled the pacl enabled
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app package plugin
	* @throws com.liferay.osb.NoSuchAppPackagePluginException if a matching app package plugin could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPackagePlugin findByAPI_PE_Last(
		long appPackageId, boolean paclEnabled,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppPackagePluginException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAPI_PE_Last(appPackageId, paclEnabled,
			orderByComparator);
	}

	/**
	* Returns the last app package plugin in the ordered set where appPackageId = &#63; and paclEnabled = &#63;.
	*
	* @param appPackageId the app package ID
	* @param paclEnabled the pacl enabled
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app package plugin, or <code>null</code> if a matching app package plugin could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPackagePlugin fetchByAPI_PE_Last(
		long appPackageId, boolean paclEnabled,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAPI_PE_Last(appPackageId, paclEnabled,
			orderByComparator);
	}

	/**
	* Returns the app package plugins before and after the current app package plugin in the ordered set where appPackageId = &#63; and paclEnabled = &#63;.
	*
	* @param appPackagePluginId the primary key of the current app package plugin
	* @param appPackageId the app package ID
	* @param paclEnabled the pacl enabled
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next app package plugin
	* @throws com.liferay.osb.NoSuchAppPackagePluginException if a app package plugin with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPackagePlugin[] findByAPI_PE_PrevAndNext(
		long appPackagePluginId, long appPackageId, boolean paclEnabled,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppPackagePluginException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAPI_PE_PrevAndNext(appPackagePluginId, appPackageId,
			paclEnabled, orderByComparator);
	}

	/**
	* Returns all the app package plugins where appPackageId = &#63; and portalRestartRequired = &#63;.
	*
	* @param appPackageId the app package ID
	* @param portalRestartRequired the portal restart required
	* @return the matching app package plugins
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AppPackagePlugin> findByAPI_PRR(
		long appPackageId, boolean portalRestartRequired)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAPI_PRR(appPackageId, portalRestartRequired);
	}

	/**
	* Returns a range of all the app package plugins where appPackageId = &#63; and portalRestartRequired = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param appPackageId the app package ID
	* @param portalRestartRequired the portal restart required
	* @param start the lower bound of the range of app package plugins
	* @param end the upper bound of the range of app package plugins (not inclusive)
	* @return the range of matching app package plugins
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AppPackagePlugin> findByAPI_PRR(
		long appPackageId, boolean portalRestartRequired, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAPI_PRR(appPackageId, portalRestartRequired, start,
			end);
	}

	/**
	* Returns an ordered range of all the app package plugins where appPackageId = &#63; and portalRestartRequired = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param appPackageId the app package ID
	* @param portalRestartRequired the portal restart required
	* @param start the lower bound of the range of app package plugins
	* @param end the upper bound of the range of app package plugins (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching app package plugins
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AppPackagePlugin> findByAPI_PRR(
		long appPackageId, boolean portalRestartRequired, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAPI_PRR(appPackageId, portalRestartRequired, start,
			end, orderByComparator);
	}

	/**
	* Returns the first app package plugin in the ordered set where appPackageId = &#63; and portalRestartRequired = &#63;.
	*
	* @param appPackageId the app package ID
	* @param portalRestartRequired the portal restart required
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app package plugin
	* @throws com.liferay.osb.NoSuchAppPackagePluginException if a matching app package plugin could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPackagePlugin findByAPI_PRR_First(
		long appPackageId, boolean portalRestartRequired,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppPackagePluginException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAPI_PRR_First(appPackageId, portalRestartRequired,
			orderByComparator);
	}

	/**
	* Returns the first app package plugin in the ordered set where appPackageId = &#63; and portalRestartRequired = &#63;.
	*
	* @param appPackageId the app package ID
	* @param portalRestartRequired the portal restart required
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app package plugin, or <code>null</code> if a matching app package plugin could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPackagePlugin fetchByAPI_PRR_First(
		long appPackageId, boolean portalRestartRequired,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAPI_PRR_First(appPackageId, portalRestartRequired,
			orderByComparator);
	}

	/**
	* Returns the last app package plugin in the ordered set where appPackageId = &#63; and portalRestartRequired = &#63;.
	*
	* @param appPackageId the app package ID
	* @param portalRestartRequired the portal restart required
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app package plugin
	* @throws com.liferay.osb.NoSuchAppPackagePluginException if a matching app package plugin could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPackagePlugin findByAPI_PRR_Last(
		long appPackageId, boolean portalRestartRequired,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppPackagePluginException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAPI_PRR_Last(appPackageId, portalRestartRequired,
			orderByComparator);
	}

	/**
	* Returns the last app package plugin in the ordered set where appPackageId = &#63; and portalRestartRequired = &#63;.
	*
	* @param appPackageId the app package ID
	* @param portalRestartRequired the portal restart required
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app package plugin, or <code>null</code> if a matching app package plugin could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPackagePlugin fetchByAPI_PRR_Last(
		long appPackageId, boolean portalRestartRequired,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAPI_PRR_Last(appPackageId, portalRestartRequired,
			orderByComparator);
	}

	/**
	* Returns the app package plugins before and after the current app package plugin in the ordered set where appPackageId = &#63; and portalRestartRequired = &#63;.
	*
	* @param appPackagePluginId the primary key of the current app package plugin
	* @param appPackageId the app package ID
	* @param portalRestartRequired the portal restart required
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next app package plugin
	* @throws com.liferay.osb.NoSuchAppPackagePluginException if a app package plugin with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPackagePlugin[] findByAPI_PRR_PrevAndNext(
		long appPackagePluginId, long appPackageId,
		boolean portalRestartRequired,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppPackagePluginException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAPI_PRR_PrevAndNext(appPackagePluginId, appPackageId,
			portalRestartRequired, orderByComparator);
	}

	/**
	* Returns all the app package plugins where appEntryId &ne; &#63; and contextName = &#63;.
	*
	* @param appEntryId the app entry ID
	* @param contextName the context name
	* @return the matching app package plugins
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AppPackagePlugin> findByNotAEI_CN(
		long appEntryId, java.lang.String contextName)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByNotAEI_CN(appEntryId, contextName);
	}

	/**
	* Returns a range of all the app package plugins where appEntryId &ne; &#63; and contextName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param appEntryId the app entry ID
	* @param contextName the context name
	* @param start the lower bound of the range of app package plugins
	* @param end the upper bound of the range of app package plugins (not inclusive)
	* @return the range of matching app package plugins
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AppPackagePlugin> findByNotAEI_CN(
		long appEntryId, java.lang.String contextName, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByNotAEI_CN(appEntryId, contextName, start, end);
	}

	/**
	* Returns an ordered range of all the app package plugins where appEntryId &ne; &#63; and contextName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param appEntryId the app entry ID
	* @param contextName the context name
	* @param start the lower bound of the range of app package plugins
	* @param end the upper bound of the range of app package plugins (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching app package plugins
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AppPackagePlugin> findByNotAEI_CN(
		long appEntryId, java.lang.String contextName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByNotAEI_CN(appEntryId, contextName, start, end,
			orderByComparator);
	}

	/**
	* Returns the first app package plugin in the ordered set where appEntryId &ne; &#63; and contextName = &#63;.
	*
	* @param appEntryId the app entry ID
	* @param contextName the context name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app package plugin
	* @throws com.liferay.osb.NoSuchAppPackagePluginException if a matching app package plugin could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPackagePlugin findByNotAEI_CN_First(
		long appEntryId, java.lang.String contextName,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppPackagePluginException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByNotAEI_CN_First(appEntryId, contextName,
			orderByComparator);
	}

	/**
	* Returns the first app package plugin in the ordered set where appEntryId &ne; &#63; and contextName = &#63;.
	*
	* @param appEntryId the app entry ID
	* @param contextName the context name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app package plugin, or <code>null</code> if a matching app package plugin could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPackagePlugin fetchByNotAEI_CN_First(
		long appEntryId, java.lang.String contextName,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByNotAEI_CN_First(appEntryId, contextName,
			orderByComparator);
	}

	/**
	* Returns the last app package plugin in the ordered set where appEntryId &ne; &#63; and contextName = &#63;.
	*
	* @param appEntryId the app entry ID
	* @param contextName the context name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app package plugin
	* @throws com.liferay.osb.NoSuchAppPackagePluginException if a matching app package plugin could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPackagePlugin findByNotAEI_CN_Last(
		long appEntryId, java.lang.String contextName,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppPackagePluginException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByNotAEI_CN_Last(appEntryId, contextName,
			orderByComparator);
	}

	/**
	* Returns the last app package plugin in the ordered set where appEntryId &ne; &#63; and contextName = &#63;.
	*
	* @param appEntryId the app entry ID
	* @param contextName the context name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app package plugin, or <code>null</code> if a matching app package plugin could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPackagePlugin fetchByNotAEI_CN_Last(
		long appEntryId, java.lang.String contextName,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByNotAEI_CN_Last(appEntryId, contextName,
			orderByComparator);
	}

	/**
	* Returns the app package plugins before and after the current app package plugin in the ordered set where appEntryId &ne; &#63; and contextName = &#63;.
	*
	* @param appPackagePluginId the primary key of the current app package plugin
	* @param appEntryId the app entry ID
	* @param contextName the context name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next app package plugin
	* @throws com.liferay.osb.NoSuchAppPackagePluginException if a app package plugin with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPackagePlugin[] findByNotAEI_CN_PrevAndNext(
		long appPackagePluginId, long appEntryId, java.lang.String contextName,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppPackagePluginException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByNotAEI_CN_PrevAndNext(appPackagePluginId, appEntryId,
			contextName, orderByComparator);
	}

	/**
	* Returns all the app package plugins where appPackageId = &#63; and bundleSymbolicName = &#63; and bundleVersion = &#63;.
	*
	* @param appPackageId the app package ID
	* @param bundleSymbolicName the bundle symbolic name
	* @param bundleVersion the bundle version
	* @return the matching app package plugins
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AppPackagePlugin> findByAPI_BSN_BV(
		long appPackageId, java.lang.String bundleSymbolicName,
		java.lang.String bundleVersion)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAPI_BSN_BV(appPackageId, bundleSymbolicName,
			bundleVersion);
	}

	/**
	* Returns a range of all the app package plugins where appPackageId = &#63; and bundleSymbolicName = &#63; and bundleVersion = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param appPackageId the app package ID
	* @param bundleSymbolicName the bundle symbolic name
	* @param bundleVersion the bundle version
	* @param start the lower bound of the range of app package plugins
	* @param end the upper bound of the range of app package plugins (not inclusive)
	* @return the range of matching app package plugins
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AppPackagePlugin> findByAPI_BSN_BV(
		long appPackageId, java.lang.String bundleSymbolicName,
		java.lang.String bundleVersion, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAPI_BSN_BV(appPackageId, bundleSymbolicName,
			bundleVersion, start, end);
	}

	/**
	* Returns an ordered range of all the app package plugins where appPackageId = &#63; and bundleSymbolicName = &#63; and bundleVersion = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param appPackageId the app package ID
	* @param bundleSymbolicName the bundle symbolic name
	* @param bundleVersion the bundle version
	* @param start the lower bound of the range of app package plugins
	* @param end the upper bound of the range of app package plugins (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching app package plugins
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AppPackagePlugin> findByAPI_BSN_BV(
		long appPackageId, java.lang.String bundleSymbolicName,
		java.lang.String bundleVersion, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAPI_BSN_BV(appPackageId, bundleSymbolicName,
			bundleVersion, start, end, orderByComparator);
	}

	/**
	* Returns the first app package plugin in the ordered set where appPackageId = &#63; and bundleSymbolicName = &#63; and bundleVersion = &#63;.
	*
	* @param appPackageId the app package ID
	* @param bundleSymbolicName the bundle symbolic name
	* @param bundleVersion the bundle version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app package plugin
	* @throws com.liferay.osb.NoSuchAppPackagePluginException if a matching app package plugin could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPackagePlugin findByAPI_BSN_BV_First(
		long appPackageId, java.lang.String bundleSymbolicName,
		java.lang.String bundleVersion,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppPackagePluginException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAPI_BSN_BV_First(appPackageId, bundleSymbolicName,
			bundleVersion, orderByComparator);
	}

	/**
	* Returns the first app package plugin in the ordered set where appPackageId = &#63; and bundleSymbolicName = &#63; and bundleVersion = &#63;.
	*
	* @param appPackageId the app package ID
	* @param bundleSymbolicName the bundle symbolic name
	* @param bundleVersion the bundle version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app package plugin, or <code>null</code> if a matching app package plugin could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPackagePlugin fetchByAPI_BSN_BV_First(
		long appPackageId, java.lang.String bundleSymbolicName,
		java.lang.String bundleVersion,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAPI_BSN_BV_First(appPackageId, bundleSymbolicName,
			bundleVersion, orderByComparator);
	}

	/**
	* Returns the last app package plugin in the ordered set where appPackageId = &#63; and bundleSymbolicName = &#63; and bundleVersion = &#63;.
	*
	* @param appPackageId the app package ID
	* @param bundleSymbolicName the bundle symbolic name
	* @param bundleVersion the bundle version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app package plugin
	* @throws com.liferay.osb.NoSuchAppPackagePluginException if a matching app package plugin could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPackagePlugin findByAPI_BSN_BV_Last(
		long appPackageId, java.lang.String bundleSymbolicName,
		java.lang.String bundleVersion,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppPackagePluginException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAPI_BSN_BV_Last(appPackageId, bundleSymbolicName,
			bundleVersion, orderByComparator);
	}

	/**
	* Returns the last app package plugin in the ordered set where appPackageId = &#63; and bundleSymbolicName = &#63; and bundleVersion = &#63;.
	*
	* @param appPackageId the app package ID
	* @param bundleSymbolicName the bundle symbolic name
	* @param bundleVersion the bundle version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app package plugin, or <code>null</code> if a matching app package plugin could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPackagePlugin fetchByAPI_BSN_BV_Last(
		long appPackageId, java.lang.String bundleSymbolicName,
		java.lang.String bundleVersion,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAPI_BSN_BV_Last(appPackageId, bundleSymbolicName,
			bundleVersion, orderByComparator);
	}

	/**
	* Returns the app package plugins before and after the current app package plugin in the ordered set where appPackageId = &#63; and bundleSymbolicName = &#63; and bundleVersion = &#63;.
	*
	* @param appPackagePluginId the primary key of the current app package plugin
	* @param appPackageId the app package ID
	* @param bundleSymbolicName the bundle symbolic name
	* @param bundleVersion the bundle version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next app package plugin
	* @throws com.liferay.osb.NoSuchAppPackagePluginException if a app package plugin with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPackagePlugin[] findByAPI_BSN_BV_PrevAndNext(
		long appPackagePluginId, long appPackageId,
		java.lang.String bundleSymbolicName, java.lang.String bundleVersion,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppPackagePluginException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAPI_BSN_BV_PrevAndNext(appPackagePluginId,
			appPackageId, bundleSymbolicName, bundleVersion, orderByComparator);
	}

	/**
	* Returns all the app package plugins.
	*
	* @return the app package plugins
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AppPackagePlugin> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
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
	public static java.util.List<com.liferay.osb.model.AppPackagePlugin> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the app package plugins.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of app package plugins
	* @param end the upper bound of the range of app package plugins (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of app package plugins
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AppPackagePlugin> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the app package plugins where appPackageId = &#63; from the database.
	*
	* @param appPackageId the app package ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByAppPackageId(long appPackageId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByAppPackageId(appPackageId);
	}

	/**
	* Removes the app package plugin where assetAttachmentId = &#63; from the database.
	*
	* @param assetAttachmentId the asset attachment ID
	* @return the app package plugin that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPackagePlugin removeByAssetAttachmentId(
		long assetAttachmentId)
		throws com.liferay.osb.NoSuchAppPackagePluginException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByAssetAttachmentId(assetAttachmentId);
	}

	/**
	* Removes all the app package plugins where contextName = &#63; from the database.
	*
	* @param contextName the context name
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByContextName(java.lang.String contextName)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByContextName(contextName);
	}

	/**
	* Removes all the app package plugins where appPackageId = &#63; and contextName = &#63; from the database.
	*
	* @param appPackageId the app package ID
	* @param contextName the context name
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByAPI_CN(long appPackageId,
		java.lang.String contextName)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByAPI_CN(appPackageId, contextName);
	}

	/**
	* Removes all the app package plugins where appPackageId = &#63; and fileName = &#63; from the database.
	*
	* @param appPackageId the app package ID
	* @param fileName the file name
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByAPI_FN(long appPackageId,
		java.lang.String fileName)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByAPI_FN(appPackageId, fileName);
	}

	/**
	* Removes all the app package plugins where appPackageId = &#63; and paclEnabled = &#63; from the database.
	*
	* @param appPackageId the app package ID
	* @param paclEnabled the pacl enabled
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByAPI_PE(long appPackageId, boolean paclEnabled)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByAPI_PE(appPackageId, paclEnabled);
	}

	/**
	* Removes all the app package plugins where appPackageId = &#63; and portalRestartRequired = &#63; from the database.
	*
	* @param appPackageId the app package ID
	* @param portalRestartRequired the portal restart required
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByAPI_PRR(long appPackageId,
		boolean portalRestartRequired)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByAPI_PRR(appPackageId, portalRestartRequired);
	}

	/**
	* Removes all the app package plugins where appEntryId &ne; &#63; and contextName = &#63; from the database.
	*
	* @param appEntryId the app entry ID
	* @param contextName the context name
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByNotAEI_CN(long appEntryId,
		java.lang.String contextName)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByNotAEI_CN(appEntryId, contextName);
	}

	/**
	* Removes all the app package plugins where appPackageId = &#63; and bundleSymbolicName = &#63; and bundleVersion = &#63; from the database.
	*
	* @param appPackageId the app package ID
	* @param bundleSymbolicName the bundle symbolic name
	* @param bundleVersion the bundle version
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByAPI_BSN_BV(long appPackageId,
		java.lang.String bundleSymbolicName, java.lang.String bundleVersion)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence()
			.removeByAPI_BSN_BV(appPackageId, bundleSymbolicName, bundleVersion);
	}

	/**
	* Removes all the app package plugins from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of app package plugins where appPackageId = &#63;.
	*
	* @param appPackageId the app package ID
	* @return the number of matching app package plugins
	* @throws SystemException if a system exception occurred
	*/
	public static int countByAppPackageId(long appPackageId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByAppPackageId(appPackageId);
	}

	/**
	* Returns the number of app package plugins where assetAttachmentId = &#63;.
	*
	* @param assetAttachmentId the asset attachment ID
	* @return the number of matching app package plugins
	* @throws SystemException if a system exception occurred
	*/
	public static int countByAssetAttachmentId(long assetAttachmentId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByAssetAttachmentId(assetAttachmentId);
	}

	/**
	* Returns the number of app package plugins where contextName = &#63;.
	*
	* @param contextName the context name
	* @return the number of matching app package plugins
	* @throws SystemException if a system exception occurred
	*/
	public static int countByContextName(java.lang.String contextName)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByContextName(contextName);
	}

	/**
	* Returns the number of app package plugins where appPackageId = &#63; and contextName = &#63;.
	*
	* @param appPackageId the app package ID
	* @param contextName the context name
	* @return the number of matching app package plugins
	* @throws SystemException if a system exception occurred
	*/
	public static int countByAPI_CN(long appPackageId,
		java.lang.String contextName)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByAPI_CN(appPackageId, contextName);
	}

	/**
	* Returns the number of app package plugins where appPackageId = &#63; and fileName = &#63;.
	*
	* @param appPackageId the app package ID
	* @param fileName the file name
	* @return the number of matching app package plugins
	* @throws SystemException if a system exception occurred
	*/
	public static int countByAPI_FN(long appPackageId, java.lang.String fileName)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByAPI_FN(appPackageId, fileName);
	}

	/**
	* Returns the number of app package plugins where appPackageId = &#63; and paclEnabled = &#63;.
	*
	* @param appPackageId the app package ID
	* @param paclEnabled the pacl enabled
	* @return the number of matching app package plugins
	* @throws SystemException if a system exception occurred
	*/
	public static int countByAPI_PE(long appPackageId, boolean paclEnabled)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByAPI_PE(appPackageId, paclEnabled);
	}

	/**
	* Returns the number of app package plugins where appPackageId = &#63; and portalRestartRequired = &#63;.
	*
	* @param appPackageId the app package ID
	* @param portalRestartRequired the portal restart required
	* @return the number of matching app package plugins
	* @throws SystemException if a system exception occurred
	*/
	public static int countByAPI_PRR(long appPackageId,
		boolean portalRestartRequired)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByAPI_PRR(appPackageId, portalRestartRequired);
	}

	/**
	* Returns the number of app package plugins where appEntryId &ne; &#63; and contextName = &#63;.
	*
	* @param appEntryId the app entry ID
	* @param contextName the context name
	* @return the number of matching app package plugins
	* @throws SystemException if a system exception occurred
	*/
	public static int countByNotAEI_CN(long appEntryId,
		java.lang.String contextName)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByNotAEI_CN(appEntryId, contextName);
	}

	/**
	* Returns the number of app package plugins where appPackageId = &#63; and bundleSymbolicName = &#63; and bundleVersion = &#63;.
	*
	* @param appPackageId the app package ID
	* @param bundleSymbolicName the bundle symbolic name
	* @param bundleVersion the bundle version
	* @return the number of matching app package plugins
	* @throws SystemException if a system exception occurred
	*/
	public static int countByAPI_BSN_BV(long appPackageId,
		java.lang.String bundleSymbolicName, java.lang.String bundleVersion)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByAPI_BSN_BV(appPackageId, bundleSymbolicName,
			bundleVersion);
	}

	/**
	* Returns the number of app package plugins.
	*
	* @return the number of app package plugins
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static AppPackagePluginPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (AppPackagePluginPersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					AppPackagePluginPersistence.class.getName());

			ReferenceRegistry.registerReference(AppPackagePluginUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(AppPackagePluginPersistence persistence) {
	}

	private static AppPackagePluginPersistence _persistence;
}