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
 * This class is a wrapper for {@link AssetAuditService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AssetAuditService
 * @generated
 */
public class AssetAuditServiceWrapper implements AssetAuditService,
	ServiceWrapper<AssetAuditService> {
	public AssetAuditServiceWrapper(AssetAuditService assetAuditService) {
		_assetAuditService = assetAuditService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _assetAuditService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_assetAuditService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _assetAuditService.invokeMethod(name, parameterTypes, arguments);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public AssetAuditService getWrappedAssetAuditService() {
		return _assetAuditService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedAssetAuditService(AssetAuditService assetAuditService) {
		_assetAuditService = assetAuditService;
	}

	public AssetAuditService getWrappedService() {
		return _assetAuditService;
	}

	public void setWrappedService(AssetAuditService assetAuditService) {
		_assetAuditService = assetAuditService;
	}

	private AssetAuditService _assetAuditService;
}