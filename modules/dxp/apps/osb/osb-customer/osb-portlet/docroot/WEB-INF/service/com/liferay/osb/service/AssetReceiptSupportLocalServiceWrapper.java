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
 * This class is a wrapper for {@link AssetReceiptSupportLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AssetReceiptSupportLocalService
 * @generated
 */
public class AssetReceiptSupportLocalServiceWrapper
	implements AssetReceiptSupportLocalService,
		ServiceWrapper<AssetReceiptSupportLocalService> {
	public AssetReceiptSupportLocalServiceWrapper(
		AssetReceiptSupportLocalService assetReceiptSupportLocalService) {
		_assetReceiptSupportLocalService = assetReceiptSupportLocalService;
	}

	/**
	* Adds the asset receipt support to the database. Also notifies the appropriate model listeners.
	*
	* @param assetReceiptSupport the asset receipt support
	* @return the asset receipt support that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceiptSupport addAssetReceiptSupport(
		com.liferay.osb.model.AssetReceiptSupport assetReceiptSupport)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptSupportLocalService.addAssetReceiptSupport(assetReceiptSupport);
	}

	/**
	* Creates a new asset receipt support with the primary key. Does not add the asset receipt support to the database.
	*
	* @param assetReceiptSupportId the primary key for the new asset receipt support
	* @return the new asset receipt support
	*/
	public com.liferay.osb.model.AssetReceiptSupport createAssetReceiptSupport(
		long assetReceiptSupportId) {
		return _assetReceiptSupportLocalService.createAssetReceiptSupport(assetReceiptSupportId);
	}

	/**
	* Deletes the asset receipt support with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param assetReceiptSupportId the primary key of the asset receipt support
	* @return the asset receipt support that was removed
	* @throws PortalException if a asset receipt support with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceiptSupport deleteAssetReceiptSupport(
		long assetReceiptSupportId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptSupportLocalService.deleteAssetReceiptSupport(assetReceiptSupportId);
	}

	/**
	* Deletes the asset receipt support from the database. Also notifies the appropriate model listeners.
	*
	* @param assetReceiptSupport the asset receipt support
	* @return the asset receipt support that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceiptSupport deleteAssetReceiptSupport(
		com.liferay.osb.model.AssetReceiptSupport assetReceiptSupport)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptSupportLocalService.deleteAssetReceiptSupport(assetReceiptSupport);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _assetReceiptSupportLocalService.dynamicQuery();
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
		return _assetReceiptSupportLocalService.dynamicQuery(dynamicQuery);
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
		return _assetReceiptSupportLocalService.dynamicQuery(dynamicQuery,
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
		return _assetReceiptSupportLocalService.dynamicQuery(dynamicQuery,
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
		return _assetReceiptSupportLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.osb.model.AssetReceiptSupport fetchAssetReceiptSupport(
		long assetReceiptSupportId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptSupportLocalService.fetchAssetReceiptSupport(assetReceiptSupportId);
	}

	/**
	* Returns the asset receipt support with the primary key.
	*
	* @param assetReceiptSupportId the primary key of the asset receipt support
	* @return the asset receipt support
	* @throws PortalException if a asset receipt support with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceiptSupport getAssetReceiptSupport(
		long assetReceiptSupportId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptSupportLocalService.getAssetReceiptSupport(assetReceiptSupportId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptSupportLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the asset receipt supports.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of asset receipt supports
	* @param end the upper bound of the range of asset receipt supports (not inclusive)
	* @return the range of asset receipt supports
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetReceiptSupport> getAssetReceiptSupports(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptSupportLocalService.getAssetReceiptSupports(start,
			end);
	}

	/**
	* Returns the number of asset receipt supports.
	*
	* @return the number of asset receipt supports
	* @throws SystemException if a system exception occurred
	*/
	public int getAssetReceiptSupportsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptSupportLocalService.getAssetReceiptSupportsCount();
	}

	/**
	* Updates the asset receipt support in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param assetReceiptSupport the asset receipt support
	* @return the asset receipt support that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceiptSupport updateAssetReceiptSupport(
		com.liferay.osb.model.AssetReceiptSupport assetReceiptSupport)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptSupportLocalService.updateAssetReceiptSupport(assetReceiptSupport);
	}

	/**
	* Updates the asset receipt support in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param assetReceiptSupport the asset receipt support
	* @param merge whether to merge the asset receipt support with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the asset receipt support that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceiptSupport updateAssetReceiptSupport(
		com.liferay.osb.model.AssetReceiptSupport assetReceiptSupport,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptSupportLocalService.updateAssetReceiptSupport(assetReceiptSupport,
			merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _assetReceiptSupportLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_assetReceiptSupportLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _assetReceiptSupportLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	public com.liferay.osb.model.AssetReceiptSupport addAssetReceiptSupport(
		long userId, long assetReceiptId, long assetLicenseId,
		long assetEntryId, long ownerClassNameId, long ownerClassPK,
		long productClassNameId, long productClassPK,
		java.lang.String productId, java.util.Date startDate,
		java.util.Date endDate, int usageType, long supportLifetime)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptSupportLocalService.addAssetReceiptSupport(userId,
			assetReceiptId, assetLicenseId, assetEntryId, ownerClassNameId,
			ownerClassPK, productClassNameId, productClassPK, productId,
			startDate, endDate, usageType, supportLifetime);
	}

	public java.util.List<com.liferay.osb.model.AssetReceiptSupport> getActiveAssetReceiptSupports(
		long assetReceiptId, int usageType, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptSupportLocalService.getActiveAssetReceiptSupports(assetReceiptId,
			usageType, start, end);
	}

	public int getActiveAssetReceiptSupportsCount(long assetReceiptId,
		int usageType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptSupportLocalService.getActiveAssetReceiptSupportsCount(assetReceiptId,
			usageType);
	}

	public java.util.List<com.liferay.osb.model.AssetReceiptSupport> getAssetReceiptSupports(
		long assetReceiptId, java.lang.String productClassName,
		long productClassPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptSupportLocalService.getAssetReceiptSupports(assetReceiptId,
			productClassName, productClassPK, start, end, obc);
	}

	public java.util.List<com.liferay.osb.model.AssetReceiptSupport> getRenewedAssetReceiptSupports(
		long assetReceiptId, int usageType, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptSupportLocalService.getRenewedAssetReceiptSupports(assetReceiptId,
			usageType, start, end);
	}

	public int getRenewedAssetReceiptSupportsCount(long assetReceiptId,
		int usageType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptSupportLocalService.getRenewedAssetReceiptSupportsCount(assetReceiptId,
			usageType);
	}

	public com.liferay.osb.model.AssetReceiptSupport updateAssetReceiptSupport(
		long assetReceiptSupportId, java.util.Date endDate)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptSupportLocalService.updateAssetReceiptSupport(assetReceiptSupportId,
			endDate);
	}

	public java.util.List<com.liferay.osb.model.AssetReceiptSupport> updateAssetReceiptSupports(
		long assetReceiptId, int usageType, java.util.Date startDate,
		java.util.Date endDate)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptSupportLocalService.updateAssetReceiptSupports(assetReceiptId,
			usageType, startDate, endDate);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public AssetReceiptSupportLocalService getWrappedAssetReceiptSupportLocalService() {
		return _assetReceiptSupportLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedAssetReceiptSupportLocalService(
		AssetReceiptSupportLocalService assetReceiptSupportLocalService) {
		_assetReceiptSupportLocalService = assetReceiptSupportLocalService;
	}

	public AssetReceiptSupportLocalService getWrappedService() {
		return _assetReceiptSupportLocalService;
	}

	public void setWrappedService(
		AssetReceiptSupportLocalService assetReceiptSupportLocalService) {
		_assetReceiptSupportLocalService = assetReceiptSupportLocalService;
	}

	private AssetReceiptSupportLocalService _assetReceiptSupportLocalService;
}