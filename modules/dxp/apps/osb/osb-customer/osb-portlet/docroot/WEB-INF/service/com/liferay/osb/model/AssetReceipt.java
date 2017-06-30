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
 * The extended model interface for the AssetReceipt service. Represents a row in the &quot;OSB_AssetReceipt&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see AssetReceiptModel
 * @see com.liferay.osb.model.impl.AssetReceiptImpl
 * @see com.liferay.osb.model.impl.AssetReceiptModelImpl
 * @generated
 */
public interface AssetReceipt extends AssetReceiptModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.osb.model.impl.AssetReceiptImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public java.util.Date getActiveAssetReceiptLicensesEndDate(int usageType)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.Date getActiveAssetReceiptSupportsEndDate(int usageType)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.portlet.asset.model.AssetEntry getAssetEntry()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.osb.model.AssetLicense> getAssetLicenses()
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.Date getAssetReceiptLicensesEndDate(int usageType)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.Date getAssetReceiptSupportsEndDate(int usageType)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.lang.String getFormalLegalEntityName();

	public java.lang.String getOwnerClassName();

	@com.liferay.portal.kernel.bean.AutoEscape()
	public java.lang.String getOwnerName();

	public java.lang.String getProductClassName();

	public java.util.Date getRenewedAssetReceiptLicensesEndDate(int usageType)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.Date getRenewedAssetReceiptSupportsEndDate(int usageType)
		throws com.liferay.portal.kernel.exception.SystemException;

	public boolean hasActiveAssetReceiptLicenses()
		throws com.liferay.portal.kernel.exception.SystemException;

	public boolean hasActiveAssetReceiptLicenses(int usageType)
		throws com.liferay.portal.kernel.exception.SystemException;

	public boolean hasActiveAssetReceiptSupports(int usageType)
		throws com.liferay.portal.kernel.exception.SystemException;

	public boolean hasRenewedAssetReceiptLicenses(int usageType)
		throws com.liferay.portal.kernel.exception.SystemException;

	public boolean hasRenewedAssetReceiptSupports(int usageType)
		throws com.liferay.portal.kernel.exception.SystemException;

	public boolean isOwnerCorpProject();

	public boolean isOwnerUser();
}