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
 * This class is a wrapper for {@link AssetReceiptRedeemTokenService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AssetReceiptRedeemTokenService
 * @generated
 */
public class AssetReceiptRedeemTokenServiceWrapper
	implements AssetReceiptRedeemTokenService,
		ServiceWrapper<AssetReceiptRedeemTokenService> {
	public AssetReceiptRedeemTokenServiceWrapper(
		AssetReceiptRedeemTokenService assetReceiptRedeemTokenService) {
		_assetReceiptRedeemTokenService = assetReceiptRedeemTokenService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _assetReceiptRedeemTokenService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_assetReceiptRedeemTokenService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _assetReceiptRedeemTokenService.invokeMethod(name,
			parameterTypes, arguments);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public AssetReceiptRedeemTokenService getWrappedAssetReceiptRedeemTokenService() {
		return _assetReceiptRedeemTokenService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedAssetReceiptRedeemTokenService(
		AssetReceiptRedeemTokenService assetReceiptRedeemTokenService) {
		_assetReceiptRedeemTokenService = assetReceiptRedeemTokenService;
	}

	public AssetReceiptRedeemTokenService getWrappedService() {
		return _assetReceiptRedeemTokenService;
	}

	public void setWrappedService(
		AssetReceiptRedeemTokenService assetReceiptRedeemTokenService) {
		_assetReceiptRedeemTokenService = assetReceiptRedeemTokenService;
	}

	private AssetReceiptRedeemTokenService _assetReceiptRedeemTokenService;
}