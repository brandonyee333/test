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
 * This class is a wrapper for {@link AssetReceiptService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AssetReceiptService
 * @generated
 */
public class AssetReceiptServiceWrapper implements AssetReceiptService,
	ServiceWrapper<AssetReceiptService> {
	public AssetReceiptServiceWrapper(AssetReceiptService assetReceiptService) {
		_assetReceiptService = assetReceiptService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _assetReceiptService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_assetReceiptService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _assetReceiptService.invokeMethod(name, parameterTypes, arguments);
	}

	public com.liferay.osb.model.AssetReceipt fetchAssetReceipt(
		long assetReceiptId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptService.fetchAssetReceipt(assetReceiptId);
	}

	public com.liferay.osb.model.AssetReceipt fetchAssetReceipt(
		java.lang.String ownerClassName, long ownerClassPK,
		java.lang.String productClassName, long productClassPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptService.fetchAssetReceipt(ownerClassName,
			ownerClassPK, productClassName, productClassPK);
	}

	public com.liferay.osb.model.AssetReceipt getAssetReceipt(
		long assetReceiptId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptService.getAssetReceipt(assetReceiptId);
	}

	public com.liferay.osb.model.AssetReceipt getAssetReceipt(
		java.lang.String ownerClassName, long ownerClassPK,
		java.lang.String productClassName, long productClassPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptService.getAssetReceipt(ownerClassName,
			ownerClassPK, productClassName, productClassPK);
	}

	public com.liferay.osb.model.AssetReceipt purchaseAssets(
		java.lang.String ownerClassName, long ownerClassPK,
		java.lang.String legalEntityName, java.lang.String productClassName,
		long productClassPK, long type, long ecDocumentEntryId, long countryId,
		int domain, boolean validateContractEntries, long eulaContractEntryId,
		long tosContractEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptService.purchaseAssets(ownerClassName,
			ownerClassPK, legalEntityName, productClassName, productClassPK,
			type, ecDocumentEntryId, countryId, domain,
			validateContractEntries, eulaContractEntryId, tosContractEntryId);
	}

	public com.liferay.osb.model.AssetReceipt updateAssetReceipt(
		long assetReceiptId, java.lang.String ownerClassName, long ownerClassPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptService.updateAssetReceipt(assetReceiptId,
			ownerClassName, ownerClassPK);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public AssetReceiptService getWrappedAssetReceiptService() {
		return _assetReceiptService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedAssetReceiptService(
		AssetReceiptService assetReceiptService) {
		_assetReceiptService = assetReceiptService;
	}

	public AssetReceiptService getWrappedService() {
		return _assetReceiptService;
	}

	public void setWrappedService(AssetReceiptService assetReceiptService) {
		_assetReceiptService = assetReceiptService;
	}

	private AssetReceiptService _assetReceiptService;
}