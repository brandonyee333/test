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
 * This class is a wrapper for {@link AssetListAssetEntryLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AssetListAssetEntryLocalService
 * @generated
 */
public class AssetListAssetEntryLocalServiceWrapper
	implements AssetListAssetEntryLocalService,
		ServiceWrapper<AssetListAssetEntryLocalService> {
	public AssetListAssetEntryLocalServiceWrapper(
		AssetListAssetEntryLocalService assetListAssetEntryLocalService) {
		_assetListAssetEntryLocalService = assetListAssetEntryLocalService;
	}

	/**
	* Adds the asset list asset entry to the database. Also notifies the appropriate model listeners.
	*
	* @param assetListAssetEntry the asset list asset entry
	* @return the asset list asset entry that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetListAssetEntry addAssetListAssetEntry(
		com.liferay.osb.model.AssetListAssetEntry assetListAssetEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetListAssetEntryLocalService.addAssetListAssetEntry(assetListAssetEntry);
	}

	/**
	* Creates a new asset list asset entry with the primary key. Does not add the asset list asset entry to the database.
	*
	* @param assetListAssetEntryId the primary key for the new asset list asset entry
	* @return the new asset list asset entry
	*/
	public com.liferay.osb.model.AssetListAssetEntry createAssetListAssetEntry(
		long assetListAssetEntryId) {
		return _assetListAssetEntryLocalService.createAssetListAssetEntry(assetListAssetEntryId);
	}

	/**
	* Deletes the asset list asset entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param assetListAssetEntryId the primary key of the asset list asset entry
	* @return the asset list asset entry that was removed
	* @throws PortalException if a asset list asset entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetListAssetEntry deleteAssetListAssetEntry(
		long assetListAssetEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetListAssetEntryLocalService.deleteAssetListAssetEntry(assetListAssetEntryId);
	}

	/**
	* Deletes the asset list asset entry from the database. Also notifies the appropriate model listeners.
	*
	* @param assetListAssetEntry the asset list asset entry
	* @return the asset list asset entry that was removed
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetListAssetEntry deleteAssetListAssetEntry(
		com.liferay.osb.model.AssetListAssetEntry assetListAssetEntry)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetListAssetEntryLocalService.deleteAssetListAssetEntry(assetListAssetEntry);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _assetListAssetEntryLocalService.dynamicQuery();
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
		return _assetListAssetEntryLocalService.dynamicQuery(dynamicQuery);
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
		return _assetListAssetEntryLocalService.dynamicQuery(dynamicQuery,
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
		return _assetListAssetEntryLocalService.dynamicQuery(dynamicQuery,
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
		return _assetListAssetEntryLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.osb.model.AssetListAssetEntry fetchAssetListAssetEntry(
		long assetListAssetEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetListAssetEntryLocalService.fetchAssetListAssetEntry(assetListAssetEntryId);
	}

	/**
	* Returns the asset list asset entry with the primary key.
	*
	* @param assetListAssetEntryId the primary key of the asset list asset entry
	* @return the asset list asset entry
	* @throws PortalException if a asset list asset entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetListAssetEntry getAssetListAssetEntry(
		long assetListAssetEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetListAssetEntryLocalService.getAssetListAssetEntry(assetListAssetEntryId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetListAssetEntryLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the asset list asset entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of asset list asset entries
	* @param end the upper bound of the range of asset list asset entries (not inclusive)
	* @return the range of asset list asset entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetListAssetEntry> getAssetListAssetEntries(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetListAssetEntryLocalService.getAssetListAssetEntries(start,
			end);
	}

	/**
	* Returns the number of asset list asset entries.
	*
	* @return the number of asset list asset entries
	* @throws SystemException if a system exception occurred
	*/
	public int getAssetListAssetEntriesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetListAssetEntryLocalService.getAssetListAssetEntriesCount();
	}

	/**
	* Updates the asset list asset entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param assetListAssetEntry the asset list asset entry
	* @return the asset list asset entry that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetListAssetEntry updateAssetListAssetEntry(
		com.liferay.osb.model.AssetListAssetEntry assetListAssetEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetListAssetEntryLocalService.updateAssetListAssetEntry(assetListAssetEntry);
	}

	/**
	* Updates the asset list asset entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param assetListAssetEntry the asset list asset entry
	* @param merge whether to merge the asset list asset entry with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the asset list asset entry that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetListAssetEntry updateAssetListAssetEntry(
		com.liferay.osb.model.AssetListAssetEntry assetListAssetEntry,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetListAssetEntryLocalService.updateAssetListAssetEntry(assetListAssetEntry,
			merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _assetListAssetEntryLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_assetListAssetEntryLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _assetListAssetEntryLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	public com.liferay.osb.model.AssetListAssetEntry addAssetListAssetEntry(
		long assetListId, long assetEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetListAssetEntryLocalService.addAssetListAssetEntry(assetListId,
			assetEntryId);
	}

	public void deleteAssetListAssetListAssetEntry(long asestListId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_assetListAssetEntryLocalService.deleteAssetListAssetListAssetEntry(asestListId);
	}

	public java.util.List<com.liferay.portlet.asset.model.AssetEntry> getAssetEntries(
		long assetListId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetListAssetEntryLocalService.getAssetEntries(assetListId);
	}

	public java.util.List<com.liferay.osb.model.AssetListAssetEntry> getAssetListAssetEntries(
		long assetListId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetListAssetEntryLocalService.getAssetListAssetEntries(assetListId);
	}

	public void setAssetListAssetEntries(long assetListId,
		java.util.List<com.liferay.portlet.asset.model.AssetEntry> assetEntries)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_assetListAssetEntryLocalService.setAssetListAssetEntries(assetListId,
			assetEntries);
	}

	public void setAssetListAssetEntries(long assetListId, long[] assetEntryIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_assetListAssetEntryLocalService.setAssetListAssetEntries(assetListId,
			assetEntryIds);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public AssetListAssetEntryLocalService getWrappedAssetListAssetEntryLocalService() {
		return _assetListAssetEntryLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedAssetListAssetEntryLocalService(
		AssetListAssetEntryLocalService assetListAssetEntryLocalService) {
		_assetListAssetEntryLocalService = assetListAssetEntryLocalService;
	}

	public AssetListAssetEntryLocalService getWrappedService() {
		return _assetListAssetEntryLocalService;
	}

	public void setWrappedService(
		AssetListAssetEntryLocalService assetListAssetEntryLocalService) {
		_assetListAssetEntryLocalService = assetListAssetEntryLocalService;
	}

	private AssetListAssetEntryLocalService _assetListAssetEntryLocalService;
}