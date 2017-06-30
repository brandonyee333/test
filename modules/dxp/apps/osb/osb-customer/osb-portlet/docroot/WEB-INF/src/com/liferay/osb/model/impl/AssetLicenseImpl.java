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

package com.liferay.osb.model.impl;

import com.liferay.osb.model.AssetLicenseConstants;
import com.liferay.osb.service.AssetReceiptLicenseLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Amos Fong
 */
public class AssetLicenseImpl extends AssetLicenseBaseImpl {

	public AssetLicenseImpl() {
	}

	public String getLicenseKeyType() throws PortalException {
		return AssetLicenseConstants.getLicenseKeyType(getLicenseType());
	}

	public String getLicenseTypeLabel() {
		return AssetLicenseConstants.getLicenseTypeLabel(getLicenseType());
	}

	public String getLifetimeLabel() {
		return AssetLicenseConstants.getLifetimeLabel(getLifetime());
	}

	public String getUsageTypeLabel() {
		return AssetLicenseConstants.getUsageTypeLabel(getUsageType());
	}

	public boolean isPurchased() throws SystemException {
		if (AssetReceiptLicenseLocalServiceUtil.hasAssetReceiptLicense(
				getAssetLicenseId())) {

			return true;
		}
		else {
			return false;
		}
	}

}