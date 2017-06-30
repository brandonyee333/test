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
 * The utility for the asset list local service. This utility wraps {@link com.liferay.osb.service.impl.AssetListLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetListLocalService
 * @see com.liferay.osb.service.base.AssetListLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.AssetListLocalServiceImpl
 * @generated
 */
public class AssetListLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.AssetListLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the asset list to the database. Also notifies the appropriate model listeners.
	*
	* @param assetList the asset list
	* @return the asset list that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetList addAssetList(
		com.liferay.osb.model.AssetList assetList)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addAssetList(assetList);
	}

	/**
	* Creates a new asset list with the primary key. Does not add the asset list to the database.
	*
	* @param assetListId the primary key for the new asset list
	* @return the new asset list
	*/
	public static com.liferay.osb.model.AssetList createAssetList(
		long assetListId) {
		return getService().createAssetList(assetListId);
	}

	/**
	* Deletes the asset list with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param assetListId the primary key of the asset list
	* @return the asset list that was removed
	* @throws PortalException if a asset list with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetList deleteAssetList(
		long assetListId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteAssetList(assetListId);
	}

	/**
	* Deletes the asset list from the database. Also notifies the appropriate model listeners.
	*
	* @param assetList the asset list
	* @return the asset list that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetList deleteAssetList(
		com.liferay.osb.model.AssetList assetList)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteAssetList(assetList);
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

	public static com.liferay.osb.model.AssetList fetchAssetList(
		long assetListId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchAssetList(assetListId);
	}

	/**
	* Returns the asset list with the primary key.
	*
	* @param assetListId the primary key of the asset list
	* @return the asset list
	* @throws PortalException if a asset list with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetList getAssetList(long assetListId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getAssetList(assetListId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the asset lists.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of asset lists
	* @param end the upper bound of the range of asset lists (not inclusive)
	* @return the range of asset lists
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetList> getAssetLists(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAssetLists(start, end);
	}

	/**
	* Returns the number of asset lists.
	*
	* @return the number of asset lists
	* @throws SystemException if a system exception occurred
	*/
	public static int getAssetListsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAssetListsCount();
	}

	/**
	* Updates the asset list in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param assetList the asset list
	* @return the asset list that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetList updateAssetList(
		com.liferay.osb.model.AssetList assetList)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateAssetList(assetList);
	}

	/**
	* Updates the asset list in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param assetList the asset list
	* @param merge whether to merge the asset list with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the asset list that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetList updateAssetList(
		com.liferay.osb.model.AssetList assetList, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateAssetList(assetList, merge);
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

	public static com.liferay.osb.model.AssetList addAssetList(
		long assetCategoryId, int type)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addAssetList(assetCategoryId, type);
	}

	public static com.liferay.osb.model.AssetList fetchAssetList(
		long assetCategoryId, int type)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchAssetList(assetCategoryId, type);
	}

	public static com.liferay.osb.model.AssetList getAssetList(
		long assetCategoryId, int type)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getAssetList(assetCategoryId, type);
	}

	public static java.util.List<com.liferay.osb.model.AssetList> getAssetLists(
		long assetEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAssetLists(assetEntryId);
	}

	public static void clearService() {
		_service = null;
	}

	public static AssetListLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					AssetListLocalService.class.getName());

			if (invokableLocalService instanceof AssetListLocalService) {
				_service = (AssetListLocalService)invokableLocalService;
			}
			else {
				_service = new AssetListLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(AssetListLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(AssetListLocalService service) {
	}

	private static AssetListLocalService _service;
}