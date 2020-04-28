/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.loop.asset.entry.set.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AssetEntrySetService}.
 *
 * @author Brian Wing Shun Chan
 * @see AssetEntrySetService
 * @generated
 */
public class AssetEntrySetServiceWrapper
	implements AssetEntrySetService, ServiceWrapper<AssetEntrySetService> {

	public AssetEntrySetServiceWrapper(
		AssetEntrySetService assetEntrySetService) {

		_assetEntrySetService = assetEntrySetService;
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject addFileAttachment(
			java.io.File file)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _assetEntrySetService.addFileAttachment(file);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _assetEntrySetService.getOSGiServiceIdentifier();
	}

	@Override
	public AssetEntrySetService getWrappedService() {
		return _assetEntrySetService;
	}

	@Override
	public void setWrappedService(AssetEntrySetService assetEntrySetService) {
		_assetEntrySetService = assetEntrySetService;
	}

	private AssetEntrySetService _assetEntrySetService;

}