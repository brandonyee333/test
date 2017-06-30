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
 * This class is a wrapper for {@link AssetListLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AssetListLocalService
 * @generated
 */
public class AssetListLocalServiceWrapper implements AssetListLocalService,
	ServiceWrapper<AssetListLocalService> {
	public AssetListLocalServiceWrapper(
		AssetListLocalService assetListLocalService) {
		_assetListLocalService = assetListLocalService;
	}

	/**
	* Adds the asset list to the database. Also notifies the appropriate model listeners.
	*
	* @param assetList the asset list
	* @return the asset list that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetList addAssetList(
		com.liferay.osb.model.AssetList assetList)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetListLocalService.addAssetList(assetList);
	}

	/**
	* Creates a new asset list with the primary key. Does not add the asset list to the database.
	*
	* @param assetListId the primary key for the new asset list
	* @return the new asset list
	*/
	public com.liferay.osb.model.AssetList createAssetList(long assetListId) {
		return _assetListLocalService.createAssetList(assetListId);
	}

	/**
	* Deletes the asset list with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param assetListId the primary key of the asset list
	* @return the asset list that was removed
	* @throws PortalException if a asset list with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetList deleteAssetList(long assetListId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetListLocalService.deleteAssetList(assetListId);
	}

	/**
	* Deletes the asset list from the database. Also notifies the appropriate model listeners.
	*
	* @param assetList the asset list
	* @return the asset list that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetList deleteAssetList(
		com.liferay.osb.model.AssetList assetList)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetListLocalService.deleteAssetList(assetList);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _assetListLocalService.dynamicQuery();
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
		return _assetListLocalService.dynamicQuery(dynamicQuery);
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
		return _assetListLocalService.dynamicQuery(dynamicQuery, start, end);
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
		return _assetListLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _assetListLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.osb.model.AssetList fetchAssetList(long assetListId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetListLocalService.fetchAssetList(assetListId);
	}

	/**
	* Returns the asset list with the primary key.
	*
	* @param assetListId the primary key of the asset list
	* @return the asset list
	* @throws PortalException if a asset list with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetList getAssetList(long assetListId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetListLocalService.getAssetList(assetListId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetListLocalService.getPersistedModel(primaryKeyObj);
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
	public java.util.List<com.liferay.osb.model.AssetList> getAssetLists(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetListLocalService.getAssetLists(start, end);
	}

	/**
	* Returns the number of asset lists.
	*
	* @return the number of asset lists
	* @throws SystemException if a system exception occurred
	*/
	public int getAssetListsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetListLocalService.getAssetListsCount();
	}

	/**
	* Updates the asset list in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param assetList the asset list
	* @return the asset list that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetList updateAssetList(
		com.liferay.osb.model.AssetList assetList)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetListLocalService.updateAssetList(assetList);
	}

	/**
	* Updates the asset list in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param assetList the asset list
	* @param merge whether to merge the asset list with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the asset list that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetList updateAssetList(
		com.liferay.osb.model.AssetList assetList, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetListLocalService.updateAssetList(assetList, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _assetListLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_assetListLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _assetListLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public com.liferay.osb.model.AssetList addAssetList(long assetCategoryId,
		int type) throws com.liferay.portal.kernel.exception.SystemException {
		return _assetListLocalService.addAssetList(assetCategoryId, type);
	}

	public com.liferay.osb.model.AssetList fetchAssetList(
		long assetCategoryId, int type)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetListLocalService.fetchAssetList(assetCategoryId, type);
	}

	public com.liferay.osb.model.AssetList getAssetList(long assetCategoryId,
		int type)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetListLocalService.getAssetList(assetCategoryId, type);
	}

	public java.util.List<com.liferay.osb.model.AssetList> getAssetLists(
		long assetEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetListLocalService.getAssetLists(assetEntryId);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public AssetListLocalService getWrappedAssetListLocalService() {
		return _assetListLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedAssetListLocalService(
		AssetListLocalService assetListLocalService) {
		_assetListLocalService = assetListLocalService;
	}

	public AssetListLocalService getWrappedService() {
		return _assetListLocalService;
	}

	public void setWrappedService(AssetListLocalService assetListLocalService) {
		_assetListLocalService = assetListLocalService;
	}

	private AssetListLocalService _assetListLocalService;
}