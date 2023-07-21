/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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