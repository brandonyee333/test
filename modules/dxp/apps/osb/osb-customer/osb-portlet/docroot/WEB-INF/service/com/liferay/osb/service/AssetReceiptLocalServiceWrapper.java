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
 * This class is a wrapper for {@link AssetReceiptLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AssetReceiptLocalService
 * @generated
 */
public class AssetReceiptLocalServiceWrapper implements AssetReceiptLocalService,
	ServiceWrapper<AssetReceiptLocalService> {
	public AssetReceiptLocalServiceWrapper(
		AssetReceiptLocalService assetReceiptLocalService) {
		_assetReceiptLocalService = assetReceiptLocalService;
	}

	/**
	* Adds the asset receipt to the database. Also notifies the appropriate model listeners.
	*
	* @param assetReceipt the asset receipt
	* @return the asset receipt that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceipt addAssetReceipt(
		com.liferay.osb.model.AssetReceipt assetReceipt)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptLocalService.addAssetReceipt(assetReceipt);
	}

	/**
	* Creates a new asset receipt with the primary key. Does not add the asset receipt to the database.
	*
	* @param assetReceiptId the primary key for the new asset receipt
	* @return the new asset receipt
	*/
	public com.liferay.osb.model.AssetReceipt createAssetReceipt(
		long assetReceiptId) {
		return _assetReceiptLocalService.createAssetReceipt(assetReceiptId);
	}

	/**
	* Deletes the asset receipt with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param assetReceiptId the primary key of the asset receipt
	* @return the asset receipt that was removed
	* @throws PortalException if a asset receipt with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceipt deleteAssetReceipt(
		long assetReceiptId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptLocalService.deleteAssetReceipt(assetReceiptId);
	}

	/**
	* Deletes the asset receipt from the database. Also notifies the appropriate model listeners.
	*
	* @param assetReceipt the asset receipt
	* @return the asset receipt that was removed
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceipt deleteAssetReceipt(
		com.liferay.osb.model.AssetReceipt assetReceipt)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptLocalService.deleteAssetReceipt(assetReceipt);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _assetReceiptLocalService.dynamicQuery();
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
		return _assetReceiptLocalService.dynamicQuery(dynamicQuery);
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
		return _assetReceiptLocalService.dynamicQuery(dynamicQuery, start, end);
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
		return _assetReceiptLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _assetReceiptLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.osb.model.AssetReceipt fetchAssetReceipt(
		long assetReceiptId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptLocalService.fetchAssetReceipt(assetReceiptId);
	}

	/**
	* Returns the asset receipt with the primary key.
	*
	* @param assetReceiptId the primary key of the asset receipt
	* @return the asset receipt
	* @throws PortalException if a asset receipt with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceipt getAssetReceipt(
		long assetReceiptId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptLocalService.getAssetReceipt(assetReceiptId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the asset receipts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of asset receipts
	* @param end the upper bound of the range of asset receipts (not inclusive)
	* @return the range of asset receipts
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetReceipt> getAssetReceipts(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptLocalService.getAssetReceipts(start, end);
	}

	/**
	* Returns the number of asset receipts.
	*
	* @return the number of asset receipts
	* @throws SystemException if a system exception occurred
	*/
	public int getAssetReceiptsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptLocalService.getAssetReceiptsCount();
	}

	/**
	* Updates the asset receipt in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param assetReceipt the asset receipt
	* @return the asset receipt that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceipt updateAssetReceipt(
		com.liferay.osb.model.AssetReceipt assetReceipt)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptLocalService.updateAssetReceipt(assetReceipt);
	}

	/**
	* Updates the asset receipt in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param assetReceipt the asset receipt
	* @param merge whether to merge the asset receipt with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the asset receipt that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceipt updateAssetReceipt(
		com.liferay.osb.model.AssetReceipt assetReceipt, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptLocalService.updateAssetReceipt(assetReceipt, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _assetReceiptLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_assetReceiptLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _assetReceiptLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public java.util.List<com.liferay.osb.model.AssetReceipt> deleteAssetReceipts(
		java.lang.String ownerClassName, long ownerClassPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptLocalService.deleteAssetReceipts(ownerClassName,
			ownerClassPK);
	}

	public com.liferay.osb.model.AssetReceipt fetchAssetReceipt(
		java.lang.String ownerClassName, long ownerClassPK,
		java.lang.String productClassName, long productClassPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptLocalService.fetchAssetReceipt(ownerClassName,
			ownerClassPK, productClassName, productClassPK);
	}

	public com.liferay.osb.model.AssetReceipt getAssetReceipt(long userId,
		java.lang.String productClassName, long productClassPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptLocalService.getAssetReceipt(userId,
			productClassName, productClassPK);
	}

	public com.liferay.osb.model.AssetReceipt getAssetReceipt(
		java.lang.String ownerClassName, long ownerClassPK,
		java.lang.String productClassName, long productClassPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptLocalService.getAssetReceipt(ownerClassName,
			ownerClassPK, productClassName, productClassPK);
	}

	public java.util.List<com.liferay.osb.model.AssetReceipt> getAssetReceipts(
		long productClassNameId,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptLocalService.getAssetReceipts(productClassNameId,
			params, start, end, obc);
	}

	public int getAssetReceiptsCount(long productClassNameId,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptLocalService.getAssetReceiptsCount(productClassNameId,
			params);
	}

	public boolean hasAssetReceipt(long userId,
		java.lang.String productClassName, long productClassPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptLocalService.hasAssetReceipt(userId,
			productClassName, productClassPK);
	}

	public boolean hasAssetReceipt(java.lang.String ownerClassName,
		long ownerClassPK, java.lang.String productClassName,
		long productClassPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptLocalService.hasAssetReceipt(ownerClassName,
			ownerClassPK, productClassName, productClassPK);
	}

	public com.liferay.osb.model.AssetReceipt purchaseAsset(long userId,
		java.lang.String ownerClassName, long ownerClassPK,
		java.lang.String legalEntityName, java.lang.String productClassName,
		long productClassPK, long type)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptLocalService.purchaseAsset(userId, ownerClassName,
			ownerClassPK, legalEntityName, productClassName, productClassPK,
			type);
	}

	public com.liferay.osb.model.AssetReceipt purchaseAssets(long userId,
		java.lang.String ownerClassName, long ownerClassPK,
		java.lang.String legalEntityName, java.lang.String productClassName,
		long productClassPK, long type, long ecDocumentEntryId, long countryId,
		int domain)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptLocalService.purchaseAssets(userId, ownerClassName,
			ownerClassPK, legalEntityName, productClassName, productClassPK,
			type, ecDocumentEntryId, countryId, domain);
	}

	public com.liferay.osb.model.AssetReceipt updateAssetReceipt(
		long assetReceiptId, java.lang.String ownerClassName, long ownerClassPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptLocalService.updateAssetReceipt(assetReceiptId,
			ownerClassName, ownerClassPK);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public AssetReceiptLocalService getWrappedAssetReceiptLocalService() {
		return _assetReceiptLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedAssetReceiptLocalService(
		AssetReceiptLocalService assetReceiptLocalService) {
		_assetReceiptLocalService = assetReceiptLocalService;
	}

	public AssetReceiptLocalService getWrappedService() {
		return _assetReceiptLocalService;
	}

	public void setWrappedService(
		AssetReceiptLocalService assetReceiptLocalService) {
		_assetReceiptLocalService = assetReceiptLocalService;
	}

	private AssetReceiptLocalService _assetReceiptLocalService;
}