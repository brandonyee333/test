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
 * This class is a wrapper for {@link AssetAttachmentService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AssetAttachmentService
 * @generated
 */
public class AssetAttachmentServiceWrapper implements AssetAttachmentService,
	ServiceWrapper<AssetAttachmentService> {
	public AssetAttachmentServiceWrapper(
		AssetAttachmentService assetAttachmentService) {
		_assetAttachmentService = assetAttachmentService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _assetAttachmentService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_assetAttachmentService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _assetAttachmentService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public com.liferay.osb.model.AssetAttachment fetchAssetAttachment(
		long assetAttachmentId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetAttachmentService.fetchAssetAttachment(assetAttachmentId);
	}

	public java.util.List<com.liferay.osb.model.AssetAttachment> getAssetAttachments(
		java.lang.String className, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetAttachmentService.getAssetAttachments(className, classPK);
	}

	public java.util.List<com.liferay.osb.model.AssetAttachment> getAssetAttachments(
		java.lang.String className, long classPK, int type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetAttachmentService.getAssetAttachments(className, classPK,
			type, start, end, obc);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public AssetAttachmentService getWrappedAssetAttachmentService() {
		return _assetAttachmentService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedAssetAttachmentService(
		AssetAttachmentService assetAttachmentService) {
		_assetAttachmentService = assetAttachmentService;
	}

	public AssetAttachmentService getWrappedService() {
		return _assetAttachmentService;
	}

	public void setWrappedService(AssetAttachmentService assetAttachmentService) {
		_assetAttachmentService = assetAttachmentService;
	}

	private AssetAttachmentService _assetAttachmentService;
}