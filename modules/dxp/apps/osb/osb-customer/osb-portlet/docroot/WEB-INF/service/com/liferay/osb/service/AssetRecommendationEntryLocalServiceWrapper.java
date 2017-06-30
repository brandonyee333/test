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
 * This class is a wrapper for {@link AssetRecommendationEntryLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AssetRecommendationEntryLocalService
 * @generated
 */
public class AssetRecommendationEntryLocalServiceWrapper
	implements AssetRecommendationEntryLocalService,
		ServiceWrapper<AssetRecommendationEntryLocalService> {
	public AssetRecommendationEntryLocalServiceWrapper(
		AssetRecommendationEntryLocalService assetRecommendationEntryLocalService) {
		_assetRecommendationEntryLocalService = assetRecommendationEntryLocalService;
	}

	/**
	* Adds the asset recommendation entry to the database. Also notifies the appropriate model listeners.
	*
	* @param assetRecommendationEntry the asset recommendation entry
	* @return the asset recommendation entry that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetRecommendationEntry addAssetRecommendationEntry(
		com.liferay.osb.model.AssetRecommendationEntry assetRecommendationEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetRecommendationEntryLocalService.addAssetRecommendationEntry(assetRecommendationEntry);
	}

	/**
	* Creates a new asset recommendation entry with the primary key. Does not add the asset recommendation entry to the database.
	*
	* @param assetRecommendationEntryId the primary key for the new asset recommendation entry
	* @return the new asset recommendation entry
	*/
	public com.liferay.osb.model.AssetRecommendationEntry createAssetRecommendationEntry(
		long assetRecommendationEntryId) {
		return _assetRecommendationEntryLocalService.createAssetRecommendationEntry(assetRecommendationEntryId);
	}

	/**
	* Deletes the asset recommendation entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param assetRecommendationEntryId the primary key of the asset recommendation entry
	* @return the asset recommendation entry that was removed
	* @throws PortalException if a asset recommendation entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetRecommendationEntry deleteAssetRecommendationEntry(
		long assetRecommendationEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetRecommendationEntryLocalService.deleteAssetRecommendationEntry(assetRecommendationEntryId);
	}

	/**
	* Deletes the asset recommendation entry from the database. Also notifies the appropriate model listeners.
	*
	* @param assetRecommendationEntry the asset recommendation entry
	* @return the asset recommendation entry that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetRecommendationEntry deleteAssetRecommendationEntry(
		com.liferay.osb.model.AssetRecommendationEntry assetRecommendationEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetRecommendationEntryLocalService.deleteAssetRecommendationEntry(assetRecommendationEntry);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _assetRecommendationEntryLocalService.dynamicQuery();
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
		return _assetRecommendationEntryLocalService.dynamicQuery(dynamicQuery);
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
		return _assetRecommendationEntryLocalService.dynamicQuery(dynamicQuery,
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
		return _assetRecommendationEntryLocalService.dynamicQuery(dynamicQuery,
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
		return _assetRecommendationEntryLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.osb.model.AssetRecommendationEntry fetchAssetRecommendationEntry(
		long assetRecommendationEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetRecommendationEntryLocalService.fetchAssetRecommendationEntry(assetRecommendationEntryId);
	}

	/**
	* Returns the asset recommendation entry with the primary key.
	*
	* @param assetRecommendationEntryId the primary key of the asset recommendation entry
	* @return the asset recommendation entry
	* @throws PortalException if a asset recommendation entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetRecommendationEntry getAssetRecommendationEntry(
		long assetRecommendationEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetRecommendationEntryLocalService.getAssetRecommendationEntry(assetRecommendationEntryId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetRecommendationEntryLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the asset recommendation entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of asset recommendation entries
	* @param end the upper bound of the range of asset recommendation entries (not inclusive)
	* @return the range of asset recommendation entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetRecommendationEntry> getAssetRecommendationEntries(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetRecommendationEntryLocalService.getAssetRecommendationEntries(start,
			end);
	}

	/**
	* Returns the number of asset recommendation entries.
	*
	* @return the number of asset recommendation entries
	* @throws SystemException if a system exception occurred
	*/
	public int getAssetRecommendationEntriesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetRecommendationEntryLocalService.getAssetRecommendationEntriesCount();
	}

	/**
	* Updates the asset recommendation entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param assetRecommendationEntry the asset recommendation entry
	* @return the asset recommendation entry that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetRecommendationEntry updateAssetRecommendationEntry(
		com.liferay.osb.model.AssetRecommendationEntry assetRecommendationEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetRecommendationEntryLocalService.updateAssetRecommendationEntry(assetRecommendationEntry);
	}

	/**
	* Updates the asset recommendation entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param assetRecommendationEntry the asset recommendation entry
	* @param merge whether to merge the asset recommendation entry with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the asset recommendation entry that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetRecommendationEntry updateAssetRecommendationEntry(
		com.liferay.osb.model.AssetRecommendationEntry assetRecommendationEntry,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetRecommendationEntryLocalService.updateAssetRecommendationEntry(assetRecommendationEntry,
			merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _assetRecommendationEntryLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_assetRecommendationEntryLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _assetRecommendationEntryLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	public void deleteAssetRecommendationEntries(java.lang.String className,
		long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		_assetRecommendationEntryLocalService.deleteAssetRecommendationEntries(className,
			classPK);
	}

	public java.util.List<com.liferay.osb.model.AssetRecommendationEntry> getAssetRecommendationEntries(
		long assetRecommendationSetId, int type, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetRecommendationEntryLocalService.getAssetRecommendationEntries(assetRecommendationSetId,
			type, start, end);
	}

	public com.liferay.osb.model.AssetRecommendationEntry updateAssetRecommendationEntry(
		long assetRecommendationSetId, java.lang.String className,
		long classPK, int type)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetRecommendationEntryLocalService.updateAssetRecommendationEntry(assetRecommendationSetId,
			className, classPK, type);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public AssetRecommendationEntryLocalService getWrappedAssetRecommendationEntryLocalService() {
		return _assetRecommendationEntryLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedAssetRecommendationEntryLocalService(
		AssetRecommendationEntryLocalService assetRecommendationEntryLocalService) {
		_assetRecommendationEntryLocalService = assetRecommendationEntryLocalService;
	}

	public AssetRecommendationEntryLocalService getWrappedService() {
		return _assetRecommendationEntryLocalService;
	}

	public void setWrappedService(
		AssetRecommendationEntryLocalService assetRecommendationEntryLocalService) {
		_assetRecommendationEntryLocalService = assetRecommendationEntryLocalService;
	}

	private AssetRecommendationEntryLocalService _assetRecommendationEntryLocalService;
}