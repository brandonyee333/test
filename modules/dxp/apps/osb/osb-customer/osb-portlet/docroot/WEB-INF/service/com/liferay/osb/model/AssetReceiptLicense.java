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

package com.liferay.osb.model;

import com.liferay.portal.model.PersistedModel;

/**
 * The extended model interface for the AssetReceiptLicense service. Represents a row in the &quot;OSB_AssetReceiptLicense&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see AssetReceiptLicenseModel
 * @see com.liferay.osb.model.impl.AssetReceiptLicenseImpl
 * @see com.liferay.osb.model.impl.AssetReceiptLicenseModelImpl
 * @generated
 */
public interface AssetReceiptLicense extends AssetReceiptLicenseModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.osb.model.impl.AssetReceiptLicenseImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public com.liferay.portlet.asset.model.AssetEntry getAssetEntry()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.AssetLicense getAssetLicense()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.AssetReceipt getAssetReceipt()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public int getAvailableLicenseKeyCount()
		throws com.liferay.portal.kernel.exception.SystemException;

	public long getMaxUsers();

	public long getRemainingDays();

	public long getRemainingTime();

	public int getServers();

	public java.lang.String getUsageTypeLabel();

	public boolean hasAvailableLicenseKeys()
		throws com.liferay.portal.kernel.exception.SystemException;

	public boolean hasUnlimitedServers();

	public boolean isExpired();

	public boolean isExpiring();
}