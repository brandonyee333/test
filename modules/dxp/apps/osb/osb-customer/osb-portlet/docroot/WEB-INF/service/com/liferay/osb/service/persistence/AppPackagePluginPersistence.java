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

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the app package plugin service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AppPackagePluginPersistenceImpl
 * @see AppPackagePluginUtil
 * @generated
 */
public interface AppPackagePluginPersistence extends BasePersistence<AppPackagePlugin> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AppPackagePluginUtil} to access the app package plugin persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the app package plugin in the entity cache if it is enabled.
	*
	* @param appPackagePlugin the app package plugin
	*/
	public void cacheResult(
		com.liferay.osb.model.AppPackagePlugin appPackagePlugin);

	/**
	* Caches the app package plugins in the entity cache if it is enabled.
	*
	* @param appPackagePlugins the app package plugins
	*/
	public void cacheResult(
		java.util.List<com.liferay.osb.model.AppPackagePlugin> appPackagePlugins);

	/**
	* Creates a new app package plugin with the primary key. Does not add the app package plugin to the database.
	*
	* @param appPackagePluginId the primary key for the new app package plugin
	* @return the new app package plugin
	*/
	public com.liferay.osb.model.AppPackagePlugin create(
		long appPackagePluginId);

	/**
	* Removes the app package plugin with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param appPackagePluginId the primary key of the app package plugin
	* @return the app package plugin that was removed
	* @throws com.liferay.osb.NoSuchAppPackagePluginException if a app package plugin with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppPackagePlugin remove(
		long appPackagePluginId)
		throws com.liferay.osb.NoSuchAppPackagePluginException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.AppPackagePlugin updateImpl(
		com.liferay.osb.model.AppPackagePlugin appPackagePlugin, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the app package plugin with the primary key or throws a {@link com.liferay.osb.NoSuchAppPackagePluginException} if it could not be found.
	*
	* @param appPackagePluginId the primary key of the app package plugin
	* @return the app package plugin
	* @throws com.liferay.osb.NoSuchAppPackagePluginException if a app package plugin with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppPackagePlugin findByPrimaryKey(
		long appPackagePluginId)
		throws com.liferay.osb.NoSuchAppPackagePluginException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the app package plugin with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param appPackagePluginId the primary key of the app package plugin
	* @return the app package plugin, or <code>null</code> if a app package plugin with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppPackagePlugin fetchByPrimaryKey(
		long appPackagePluginId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the app package plugins where appPackageId = &#63;.
	*
	* @param appPackageId the app package ID
	* @return the matching app package plugins
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppPackagePlugin> findByAppPackageId(
		long appPackageId)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AppPackagePlugin> findByAppPackageId(
		long appPackageId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AppPackagePlugin> findByAppPackageId(
		long appPackageId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first app package plugin in the ordered set where appPackageId = &#63;.
	*
	* @param appPackageId the app package ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app package plugin
	* @throws com.liferay.osb.NoSuchAppPackagePluginException if a matching app package plugin could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppPackagePlugin findByAppPackageId_First(
		long appPackageId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppPackagePluginException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first app package plugin in the ordered set where appPackageId = &#63;.
	*
	* @param appPackageId the app package ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app package plugin, or <code>null</code> if a matching app package plugin could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppPackagePlugin fetchByAppPackageId_First(
		long appPackageId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last app package plugin in the ordered set where appPackageId = &#63;.
	*
	* @param appPackageId the app package ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app package plugin
	* @throws com.liferay.osb.NoSuchAppPackagePluginException if a matching app package plugin could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppPackagePlugin findByAppPackageId_Last(
		long appPackageId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppPackagePluginException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last app package plugin in the ordered set where appPackageId = &#63;.
	*
	* @param appPackageId the app package ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app package plugin, or <code>null</code> if a matching app package plugin could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppPackagePlugin fetchByAppPackageId_Last(
		long appPackageId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AppPackagePlugin[] findByAppPackageId_PrevAndNext(
		long appPackagePluginId, long appPackageId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppPackagePluginException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the app package plugin where assetAttachmentId = &#63; or throws a {@link com.liferay.osb.NoSuchAppPackagePluginException} if it could not be found.
	*
	* @param assetAttachmentId the asset attachment ID
	* @return the matching app package plugin
	* @throws com.liferay.osb.NoSuchAppPackagePluginException if a matching app package plugin could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppPackagePlugin findByAssetAttachmentId(
		long assetAttachmentId)
		throws com.liferay.osb.NoSuchAppPackagePluginException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the app package plugin where assetAttachmentId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param assetAttachmentId the asset attachment ID
	* @return the matching app package plugin, or <code>null</code> if a matching app package plugin could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppPackagePlugin fetchByAssetAttachmentId(
		long assetAttachmentId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the app package plugin where assetAttachmentId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param assetAttachmentId the asset attachment ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching app package plugin, or <code>null</code> if a matching app package plugin could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppPackagePlugin fetchByAssetAttachmentId(
		long assetAttachmentId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the app package plugins where contextName = &#63;.
	*
	* @param contextName the context name
	* @return the matching app package plugins
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppPackagePlugin> findByContextName(
		java.lang.String contextName)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AppPackagePlugin> findByContextName(
		java.lang.String contextName, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AppPackagePlugin> findByContextName(
		java.lang.String contextName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first app package plugin in the ordered set where contextName = &#63;.
	*
	* @param contextName the context name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app package plugin
	* @throws com.liferay.osb.NoSuchAppPackagePluginException if a matching app package plugin could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppPackagePlugin findByContextName_First(
		java.lang.String contextName,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppPackagePluginException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first app package plugin in the ordered set where contextName = &#63;.
	*
	* @param contextName the context name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app package plugin, or <code>null</code> if a matching app package plugin could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppPackagePlugin fetchByContextName_First(
		java.lang.String contextName,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last app package plugin in the ordered set where contextName = &#63;.
	*
	* @param contextName the context name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app package plugin
	* @throws com.liferay.osb.NoSuchAppPackagePluginException if a matching app package plugin could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppPackagePlugin findByContextName_Last(
		java.lang.String contextName,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppPackagePluginException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last app package plugin in the ordered set where contextName = &#63;.
	*
	* @param contextName the context name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app package plugin, or <code>null</code> if a matching app package plugin could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppPackagePlugin fetchByContextName_Last(
		java.lang.String contextName,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AppPackagePlugin[] findByContextName_PrevAndNext(
		long appPackagePluginId, java.lang.String contextName,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppPackagePluginException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the app package plugins where appPackageId = &#63; and contextName = &#63;.
	*
	* @param appPackageId the app package ID
	* @param contextName the context name
	* @return the matching app package plugins
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppPackagePlugin> findByAPI_CN(
		long appPackageId, java.lang.String contextName)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AppPackagePlugin> findByAPI_CN(
		long appPackageId, java.lang.String contextName, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AppPackagePlugin> findByAPI_CN(
		long appPackageId, java.lang.String contextName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AppPackagePlugin findByAPI_CN_First(
		long appPackageId, java.lang.String contextName,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppPackagePluginException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first app package plugin in the ordered set where appPackageId = &#63; and contextName = &#63;.
	*
	* @param appPackageId the app package ID
	* @param contextName the context name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app package plugin, or <code>null</code> if a matching app package plugin could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppPackagePlugin fetchByAPI_CN_First(
		long appPackageId, java.lang.String contextName,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AppPackagePlugin findByAPI_CN_Last(
		long appPackageId, java.lang.String contextName,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppPackagePluginException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last app package plugin in the ordered set where appPackageId = &#63; and contextName = &#63;.
	*
	* @param appPackageId the app package ID
	* @param contextName the context name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app package plugin, or <code>null</code> if a matching app package plugin could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppPackagePlugin fetchByAPI_CN_Last(
		long appPackageId, java.lang.String contextName,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AppPackagePlugin[] findByAPI_CN_PrevAndNext(
		long appPackagePluginId, long appPackageId,
		java.lang.String contextName,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppPackagePluginException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the app package plugins where appPackageId = &#63; and fileName = &#63;.
	*
	* @param appPackageId the app package ID
	* @param fileName the file name
	* @return the matching app package plugins
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppPackagePlugin> findByAPI_FN(
		long appPackageId, java.lang.String fileName)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AppPackagePlugin> findByAPI_FN(
		long appPackageId, java.lang.String fileName, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AppPackagePlugin> findByAPI_FN(
		long appPackageId, java.lang.String fileName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AppPackagePlugin findByAPI_FN_First(
		long appPackageId, java.lang.String fileName,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppPackagePluginException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first app package plugin in the ordered set where appPackageId = &#63; and fileName = &#63;.
	*
	* @param appPackageId the app package ID
	* @param fileName the file name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app package plugin, or <code>null</code> if a matching app package plugin could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppPackagePlugin fetchByAPI_FN_First(
		long appPackageId, java.lang.String fileName,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AppPackagePlugin findByAPI_FN_Last(
		long appPackageId, java.lang.String fileName,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppPackagePluginException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last app package plugin in the ordered set where appPackageId = &#63; and fileName = &#63;.
	*
	* @param appPackageId the app package ID
	* @param fileName the file name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app package plugin, or <code>null</code> if a matching app package plugin could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppPackagePlugin fetchByAPI_FN_Last(
		long appPackageId, java.lang.String fileName,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AppPackagePlugin[] findByAPI_FN_PrevAndNext(
		long appPackagePluginId, long appPackageId, java.lang.String fileName,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppPackagePluginException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the app package plugins where appPackageId = &#63; and paclEnabled = &#63;.
	*
	* @param appPackageId the app package ID
	* @param paclEnabled the pacl enabled
	* @return the matching app package plugins
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppPackagePlugin> findByAPI_PE(
		long appPackageId, boolean paclEnabled)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AppPackagePlugin> findByAPI_PE(
		long appPackageId, boolean paclEnabled, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AppPackagePlugin> findByAPI_PE(
		long appPackageId, boolean paclEnabled, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AppPackagePlugin findByAPI_PE_First(
		long appPackageId, boolean paclEnabled,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppPackagePluginException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first app package plugin in the ordered set where appPackageId = &#63; and paclEnabled = &#63;.
	*
	* @param appPackageId the app package ID
	* @param paclEnabled the pacl enabled
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app package plugin, or <code>null</code> if a matching app package plugin could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppPackagePlugin fetchByAPI_PE_First(
		long appPackageId, boolean paclEnabled,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AppPackagePlugin findByAPI_PE_Last(
		long appPackageId, boolean paclEnabled,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppPackagePluginException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last app package plugin in the ordered set where appPackageId = &#63; and paclEnabled = &#63;.
	*
	* @param appPackageId the app package ID
	* @param paclEnabled the pacl enabled
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app package plugin, or <code>null</code> if a matching app package plugin could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppPackagePlugin fetchByAPI_PE_Last(
		long appPackageId, boolean paclEnabled,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AppPackagePlugin[] findByAPI_PE_PrevAndNext(
		long appPackagePluginId, long appPackageId, boolean paclEnabled,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppPackagePluginException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the app package plugins where appPackageId = &#63; and portalRestartRequired = &#63;.
	*
	* @param appPackageId the app package ID
	* @param portalRestartRequired the portal restart required
	* @return the matching app package plugins
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppPackagePlugin> findByAPI_PRR(
		long appPackageId, boolean portalRestartRequired)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AppPackagePlugin> findByAPI_PRR(
		long appPackageId, boolean portalRestartRequired, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AppPackagePlugin> findByAPI_PRR(
		long appPackageId, boolean portalRestartRequired, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AppPackagePlugin findByAPI_PRR_First(
		long appPackageId, boolean portalRestartRequired,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppPackagePluginException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first app package plugin in the ordered set where appPackageId = &#63; and portalRestartRequired = &#63;.
	*
	* @param appPackageId the app package ID
	* @param portalRestartRequired the portal restart required
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app package plugin, or <code>null</code> if a matching app package plugin could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppPackagePlugin fetchByAPI_PRR_First(
		long appPackageId, boolean portalRestartRequired,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AppPackagePlugin findByAPI_PRR_Last(
		long appPackageId, boolean portalRestartRequired,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppPackagePluginException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last app package plugin in the ordered set where appPackageId = &#63; and portalRestartRequired = &#63;.
	*
	* @param appPackageId the app package ID
	* @param portalRestartRequired the portal restart required
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app package plugin, or <code>null</code> if a matching app package plugin could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppPackagePlugin fetchByAPI_PRR_Last(
		long appPackageId, boolean portalRestartRequired,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AppPackagePlugin[] findByAPI_PRR_PrevAndNext(
		long appPackagePluginId, long appPackageId,
		boolean portalRestartRequired,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppPackagePluginException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the app package plugins where appEntryId &ne; &#63; and contextName = &#63;.
	*
	* @param appEntryId the app entry ID
	* @param contextName the context name
	* @return the matching app package plugins
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppPackagePlugin> findByNotAEI_CN(
		long appEntryId, java.lang.String contextName)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AppPackagePlugin> findByNotAEI_CN(
		long appEntryId, java.lang.String contextName, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AppPackagePlugin> findByNotAEI_CN(
		long appEntryId, java.lang.String contextName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AppPackagePlugin findByNotAEI_CN_First(
		long appEntryId, java.lang.String contextName,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppPackagePluginException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first app package plugin in the ordered set where appEntryId &ne; &#63; and contextName = &#63;.
	*
	* @param appEntryId the app entry ID
	* @param contextName the context name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app package plugin, or <code>null</code> if a matching app package plugin could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppPackagePlugin fetchByNotAEI_CN_First(
		long appEntryId, java.lang.String contextName,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AppPackagePlugin findByNotAEI_CN_Last(
		long appEntryId, java.lang.String contextName,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppPackagePluginException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last app package plugin in the ordered set where appEntryId &ne; &#63; and contextName = &#63;.
	*
	* @param appEntryId the app entry ID
	* @param contextName the context name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app package plugin, or <code>null</code> if a matching app package plugin could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppPackagePlugin fetchByNotAEI_CN_Last(
		long appEntryId, java.lang.String contextName,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AppPackagePlugin[] findByNotAEI_CN_PrevAndNext(
		long appPackagePluginId, long appEntryId, java.lang.String contextName,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppPackagePluginException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the app package plugins where appPackageId = &#63; and bundleSymbolicName = &#63; and bundleVersion = &#63;.
	*
	* @param appPackageId the app package ID
	* @param bundleSymbolicName the bundle symbolic name
	* @param bundleVersion the bundle version
	* @return the matching app package plugins
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppPackagePlugin> findByAPI_BSN_BV(
		long appPackageId, java.lang.String bundleSymbolicName,
		java.lang.String bundleVersion)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AppPackagePlugin> findByAPI_BSN_BV(
		long appPackageId, java.lang.String bundleSymbolicName,
		java.lang.String bundleVersion, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AppPackagePlugin> findByAPI_BSN_BV(
		long appPackageId, java.lang.String bundleSymbolicName,
		java.lang.String bundleVersion, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AppPackagePlugin findByAPI_BSN_BV_First(
		long appPackageId, java.lang.String bundleSymbolicName,
		java.lang.String bundleVersion,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppPackagePluginException,
			com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AppPackagePlugin fetchByAPI_BSN_BV_First(
		long appPackageId, java.lang.String bundleSymbolicName,
		java.lang.String bundleVersion,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AppPackagePlugin findByAPI_BSN_BV_Last(
		long appPackageId, java.lang.String bundleSymbolicName,
		java.lang.String bundleVersion,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppPackagePluginException,
			com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AppPackagePlugin fetchByAPI_BSN_BV_Last(
		long appPackageId, java.lang.String bundleSymbolicName,
		java.lang.String bundleVersion,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AppPackagePlugin[] findByAPI_BSN_BV_PrevAndNext(
		long appPackagePluginId, long appPackageId,
		java.lang.String bundleSymbolicName, java.lang.String bundleVersion,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppPackagePluginException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the app package plugins.
	*
	* @return the app package plugins
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppPackagePlugin> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AppPackagePlugin> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AppPackagePlugin> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the app package plugins where appPackageId = &#63; from the database.
	*
	* @param appPackageId the app package ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByAppPackageId(long appPackageId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the app package plugin where assetAttachmentId = &#63; from the database.
	*
	* @param assetAttachmentId the asset attachment ID
	* @return the app package plugin that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppPackagePlugin removeByAssetAttachmentId(
		long assetAttachmentId)
		throws com.liferay.osb.NoSuchAppPackagePluginException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the app package plugins where contextName = &#63; from the database.
	*
	* @param contextName the context name
	* @throws SystemException if a system exception occurred
	*/
	public void removeByContextName(java.lang.String contextName)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the app package plugins where appPackageId = &#63; and contextName = &#63; from the database.
	*
	* @param appPackageId the app package ID
	* @param contextName the context name
	* @throws SystemException if a system exception occurred
	*/
	public void removeByAPI_CN(long appPackageId, java.lang.String contextName)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the app package plugins where appPackageId = &#63; and fileName = &#63; from the database.
	*
	* @param appPackageId the app package ID
	* @param fileName the file name
	* @throws SystemException if a system exception occurred
	*/
	public void removeByAPI_FN(long appPackageId, java.lang.String fileName)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the app package plugins where appPackageId = &#63; and paclEnabled = &#63; from the database.
	*
	* @param appPackageId the app package ID
	* @param paclEnabled the pacl enabled
	* @throws SystemException if a system exception occurred
	*/
	public void removeByAPI_PE(long appPackageId, boolean paclEnabled)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the app package plugins where appPackageId = &#63; and portalRestartRequired = &#63; from the database.
	*
	* @param appPackageId the app package ID
	* @param portalRestartRequired the portal restart required
	* @throws SystemException if a system exception occurred
	*/
	public void removeByAPI_PRR(long appPackageId, boolean portalRestartRequired)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the app package plugins where appEntryId &ne; &#63; and contextName = &#63; from the database.
	*
	* @param appEntryId the app entry ID
	* @param contextName the context name
	* @throws SystemException if a system exception occurred
	*/
	public void removeByNotAEI_CN(long appEntryId, java.lang.String contextName)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the app package plugins where appPackageId = &#63; and bundleSymbolicName = &#63; and bundleVersion = &#63; from the database.
	*
	* @param appPackageId the app package ID
	* @param bundleSymbolicName the bundle symbolic name
	* @param bundleVersion the bundle version
	* @throws SystemException if a system exception occurred
	*/
	public void removeByAPI_BSN_BV(long appPackageId,
		java.lang.String bundleSymbolicName, java.lang.String bundleVersion)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the app package plugins from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of app package plugins where appPackageId = &#63;.
	*
	* @param appPackageId the app package ID
	* @return the number of matching app package plugins
	* @throws SystemException if a system exception occurred
	*/
	public int countByAppPackageId(long appPackageId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of app package plugins where assetAttachmentId = &#63;.
	*
	* @param assetAttachmentId the asset attachment ID
	* @return the number of matching app package plugins
	* @throws SystemException if a system exception occurred
	*/
	public int countByAssetAttachmentId(long assetAttachmentId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of app package plugins where contextName = &#63;.
	*
	* @param contextName the context name
	* @return the number of matching app package plugins
	* @throws SystemException if a system exception occurred
	*/
	public int countByContextName(java.lang.String contextName)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of app package plugins where appPackageId = &#63; and contextName = &#63;.
	*
	* @param appPackageId the app package ID
	* @param contextName the context name
	* @return the number of matching app package plugins
	* @throws SystemException if a system exception occurred
	*/
	public int countByAPI_CN(long appPackageId, java.lang.String contextName)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of app package plugins where appPackageId = &#63; and fileName = &#63;.
	*
	* @param appPackageId the app package ID
	* @param fileName the file name
	* @return the number of matching app package plugins
	* @throws SystemException if a system exception occurred
	*/
	public int countByAPI_FN(long appPackageId, java.lang.String fileName)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of app package plugins where appPackageId = &#63; and paclEnabled = &#63;.
	*
	* @param appPackageId the app package ID
	* @param paclEnabled the pacl enabled
	* @return the number of matching app package plugins
	* @throws SystemException if a system exception occurred
	*/
	public int countByAPI_PE(long appPackageId, boolean paclEnabled)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of app package plugins where appPackageId = &#63; and portalRestartRequired = &#63;.
	*
	* @param appPackageId the app package ID
	* @param portalRestartRequired the portal restart required
	* @return the number of matching app package plugins
	* @throws SystemException if a system exception occurred
	*/
	public int countByAPI_PRR(long appPackageId, boolean portalRestartRequired)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of app package plugins where appEntryId &ne; &#63; and contextName = &#63;.
	*
	* @param appEntryId the app entry ID
	* @param contextName the context name
	* @return the number of matching app package plugins
	* @throws SystemException if a system exception occurred
	*/
	public int countByNotAEI_CN(long appEntryId, java.lang.String contextName)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of app package plugins where appPackageId = &#63; and bundleSymbolicName = &#63; and bundleVersion = &#63;.
	*
	* @param appPackageId the app package ID
	* @param bundleSymbolicName the bundle symbolic name
	* @param bundleVersion the bundle version
	* @return the number of matching app package plugins
	* @throws SystemException if a system exception occurred
	*/
	public int countByAPI_BSN_BV(long appPackageId,
		java.lang.String bundleSymbolicName, java.lang.String bundleVersion)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of app package plugins.
	*
	* @return the number of app package plugins
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}