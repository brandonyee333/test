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
 * This class is a wrapper for {@link AssetRecommendationSetLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AssetRecommendationSetLocalService
 * @generated
 */
public class AssetRecommendationSetLocalServiceWrapper
	implements AssetRecommendationSetLocalService,
		ServiceWrapper<AssetRecommendationSetLocalService> {
	public AssetRecommendationSetLocalServiceWrapper(
		AssetRecommendationSetLocalService assetRecommendationSetLocalService) {
		_assetRecommendationSetLocalService = assetRecommendationSetLocalService;
	}

	/**
	* Adds the asset recommendation set to the database. Also notifies the appropriate model listeners.
	*
	* @param assetRecommendationSet the asset recommendation set
	* @return the asset recommendation set that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetRecommendationSet addAssetRecommendationSet(
		com.liferay.osb.model.AssetRecommendationSet assetRecommendationSet)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetRecommendationSetLocalService.addAssetRecommendationSet(assetRecommendationSet);
	}

	/**
	* Creates a new asset recommendation set with the primary key. Does not add the asset recommendation set to the database.
	*
	* @param assetRecommendationSetId the primary key for the new asset recommendation set
	* @return the new asset recommendation set
	*/
	public com.liferay.osb.model.AssetRecommendationSet createAssetRecommendationSet(
		long assetRecommendationSetId) {
		return _assetRecommendationSetLocalService.createAssetRecommendationSet(assetRecommendationSetId);
	}

	/**
	* Deletes the asset recommendation set with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param assetRecommendationSetId the primary key of the asset recommendation set
	* @return the asset recommendation set that was removed
	* @throws PortalException if a asset recommendation set with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetRecommendationSet deleteAssetRecommendationSet(
		long assetRecommendationSetId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetRecommendationSetLocalService.deleteAssetRecommendationSet(assetRecommendationSetId);
	}

	/**
	* Deletes the asset recommendation set from the database. Also notifies the appropriate model listeners.
	*
	* @param assetRecommendationSet the asset recommendation set
	* @return the asset recommendation set that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetRecommendationSet deleteAssetRecommendationSet(
		com.liferay.osb.model.AssetRecommendationSet assetRecommendationSet)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetRecommendationSetLocalService.deleteAssetRecommendationSet(assetRecommendationSet);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _assetRecommendationSetLocalService.dynamicQuery();
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
		return _assetRecommendationSetLocalService.dynamicQuery(dynamicQuery);
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
		return _assetRecommendationSetLocalService.dynamicQuery(dynamicQuery,
			start, end);
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
		return _assetRecommendationSetLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
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
		return _assetRecommendationSetLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.osb.model.AssetRecommendationSet fetchAssetRecommendationSet(
		long assetRecommendationSetId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetRecommendationSetLocalService.fetchAssetRecommendationSet(assetRecommendationSetId);
	}

	/**
	* Returns the asset recommendation set with the primary key.
	*
	* @param assetRecommendationSetId the primary key of the asset recommendation set
	* @return the asset recommendation set
	* @throws PortalException if a asset recommendation set with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetRecommendationSet getAssetRecommendationSet(
		long assetRecommendationSetId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetRecommendationSetLocalService.getAssetRecommendationSet(assetRecommendationSetId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetRecommendationSetLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the asset recommendation sets.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of asset recommendation sets
	* @param end the upper bound of the range of asset recommendation sets (not inclusive)
	* @return the range of asset recommendation sets
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetRecommendationSet> getAssetRecommendationSets(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetRecommendationSetLocalService.getAssetRecommendationSets(start,
			end);
	}

	/**
	* Returns the number of asset recommendation sets.
	*
	* @return the number of asset recommendation sets
	* @throws SystemException if a system exception occurred
	*/
	public int getAssetRecommendationSetsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetRecommendationSetLocalService.getAssetRecommendationSetsCount();
	}

	/**
	* Updates the asset recommendation set in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param assetRecommendationSet the asset recommendation set
	* @return the asset recommendation set that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetRecommendationSet updateAssetRecommendationSet(
		com.liferay.osb.model.AssetRecommendationSet assetRecommendationSet)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetRecommendationSetLocalService.updateAssetRecommendationSet(assetRecommendationSet);
	}

	/**
	* Updates the asset recommendation set in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param assetRecommendationSet the asset recommendation set
	* @param merge whether to merge the asset recommendation set with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the asset recommendation set that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetRecommendationSet updateAssetRecommendationSet(
		com.liferay.osb.model.AssetRecommendationSet assetRecommendationSet,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetRecommendationSetLocalService.updateAssetRecommendationSet(assetRecommendationSet,
			merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _assetRecommendationSetLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_assetRecommendationSetLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _assetRecommendationSetLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	public com.liferay.osb.model.AssetRecommendationSet addAssetRecommendationSet(
		java.lang.String className, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetRecommendationSetLocalService.addAssetRecommendationSet(className,
			classPK);
	}

	public void deleteAssetRecommendationSet(java.lang.String className,
		long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		_assetRecommendationSetLocalService.deleteAssetRecommendationSet(className,
			classPK);
	}

	public com.liferay.osb.model.AssetRecommendationSet fetchAssetRecommendationSet(
		java.lang.String className, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetRecommendationSetLocalService.fetchAssetRecommendationSet(className,
			classPK);
	}

	public void updateAssetRecommendationSets(long userId,
		java.lang.String className, long classPK, int type)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_assetRecommendationSetLocalService.updateAssetRecommendationSets(userId,
			className, classPK, type);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public AssetRecommendationSetLocalService getWrappedAssetRecommendationSetLocalService() {
		return _assetRecommendationSetLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedAssetRecommendationSetLocalService(
		AssetRecommendationSetLocalService assetRecommendationSetLocalService) {
		_assetRecommendationSetLocalService = assetRecommendationSetLocalService;
	}

	public AssetRecommendationSetLocalService getWrappedService() {
		return _assetRecommendationSetLocalService;
	}

	public void setWrappedService(
		AssetRecommendationSetLocalService assetRecommendationSetLocalService) {
		_assetRecommendationSetLocalService = assetRecommendationSetLocalService;
	}

	private AssetRecommendationSetLocalService _assetRecommendationSetLocalService;
}