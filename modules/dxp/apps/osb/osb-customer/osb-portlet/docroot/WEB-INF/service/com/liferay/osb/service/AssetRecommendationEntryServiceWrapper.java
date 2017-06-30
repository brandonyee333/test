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
 * This class is a wrapper for {@link AssetRecommendationEntryService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AssetRecommendationEntryService
 * @generated
 */
public class AssetRecommendationEntryServiceWrapper
	implements AssetRecommendationEntryService,
		ServiceWrapper<AssetRecommendationEntryService> {
	public AssetRecommendationEntryServiceWrapper(
		AssetRecommendationEntryService assetRecommendationEntryService) {
		_assetRecommendationEntryService = assetRecommendationEntryService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _assetRecommendationEntryService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_assetRecommendationEntryService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _assetRecommendationEntryService.invokeMethod(name,
			parameterTypes, arguments);
	}

	public java.util.List<com.liferay.osb.model.AssetRecommendationEntry> getAssetRecommendationEntries(
		long assetRecommendationSetId, int type, int start, int end)
		throws java.lang.Exception {
		return _assetRecommendationEntryService.getAssetRecommendationEntries(assetRecommendationSetId,
			type, start, end);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public AssetRecommendationEntryService getWrappedAssetRecommendationEntryService() {
		return _assetRecommendationEntryService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedAssetRecommendationEntryService(
		AssetRecommendationEntryService assetRecommendationEntryService) {
		_assetRecommendationEntryService = assetRecommendationEntryService;
	}

	public AssetRecommendationEntryService getWrappedService() {
		return _assetRecommendationEntryService;
	}

	public void setWrappedService(
		AssetRecommendationEntryService assetRecommendationEntryService) {
		_assetRecommendationEntryService = assetRecommendationEntryService;
	}

	private AssetRecommendationEntryService _assetRecommendationEntryService;
}