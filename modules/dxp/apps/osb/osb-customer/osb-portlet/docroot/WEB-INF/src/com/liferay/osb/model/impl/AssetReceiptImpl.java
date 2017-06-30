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

import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.osb.model.AppEntry;
import com.liferay.osb.model.AppVersion;
import com.liferay.osb.model.AppVersionConstants;
import com.liferay.osb.model.AssetLicense;
import com.liferay.osb.model.AssetReceiptConstants;
import com.liferay.osb.model.AssetReceiptLicense;
import com.liferay.osb.model.AssetReceiptSupport;
import com.liferay.osb.model.CorpProject;
import com.liferay.osb.service.AppVersionLocalServiceUtil;
import com.liferay.osb.service.AssetLicenseLocalServiceUtil;
import com.liferay.osb.service.AssetReceiptLicenseLocalServiceUtil;
import com.liferay.osb.service.AssetReceiptSupportLocalServiceUtil;
import com.liferay.osb.util.WorkflowConstants;
import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;

import java.util.Date;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Ryan Park
 * @author Joan Kim
 */
public class AssetReceiptImpl extends AssetReceiptBaseImpl {

	public AssetReceiptImpl() {
	}

	public Date getActiveAssetReceiptLicensesEndDate(int usageType)
		throws SystemException {

		List<AssetReceiptLicense> assetReceiptLicenses =
			AssetReceiptLicenseLocalServiceUtil.getActiveAssetReceiptLicenses(
				getAssetReceiptId(), usageType, 0, 1);

		if (assetReceiptLicenses.isEmpty()) {
			return null;
		}

		AssetReceiptLicense assetReceiptLicense = assetReceiptLicenses.get(0);

		return assetReceiptLicense.getEndDate();
	}

	public Date getActiveAssetReceiptSupportsEndDate(int usageType)
		throws SystemException {

		List<AssetReceiptSupport> assetReceiptSupports =
			AssetReceiptSupportLocalServiceUtil.getActiveAssetReceiptSupports(
				getAssetReceiptId(), usageType, 0, 1);

		if (assetReceiptSupports.isEmpty()) {
			return null;
		}

		AssetReceiptSupport assetReceiptSupport = assetReceiptSupports.get(0);

		return assetReceiptSupport.getEndDate();
	}

	public AssetEntry getAssetEntry() throws PortalException, SystemException {
		return AssetEntryLocalServiceUtil.getAssetEntry(getAssetEntryId());
	}

	public List<AssetLicense> getAssetLicenses() throws SystemException {
		if (getProductClassNameId() ==
				PortalUtil.getClassNameId(AppEntry.class.getName())) {

			AppVersion appVersion = AppVersionLocalServiceUtil.fetchAppVersion(
				getProductClassPK(), AppVersionConstants.STATUSES_APPROVED,
				null);

			return AssetLicenseLocalServiceUtil.getAssetLicenses(
				AppVersion.class.getName(), appVersion.getAppVersionId(),
				WorkflowConstants.STATUS_ANY, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null);
		}

		return null;
	}

	public Date getAssetReceiptLicensesEndDate(int usageType)
		throws SystemException {

		if (hasRenewedAssetReceiptLicenses(usageType)) {
			return getRenewedAssetReceiptLicensesEndDate(usageType);
		}
		else if (hasActiveAssetReceiptLicenses(usageType)) {
			return getActiveAssetReceiptLicensesEndDate(usageType);
		}

		return null;
	}

	public Date getAssetReceiptSupportsEndDate(int usageType)
		throws SystemException {

		if (hasRenewedAssetReceiptSupports(usageType)) {
			return getRenewedAssetReceiptSupportsEndDate(usageType);
		}
		else if (hasActiveAssetReceiptSupports(usageType)) {
			return getActiveAssetReceiptSupportsEndDate(usageType);
		}

		return null;
	}

	public String getFormalLegalEntityName() {
		if (isOwnerUser() && Validator.isNotNull(getLegalEntityName())) {
			return getLegalEntityName();
		}
		else {
			return getOwnerName();
		}
	}

	public String getOwnerClassName() {
		return PortalUtil.getClassName(getOwnerClassNameId());
	}

	@AutoEscape
	public String getOwnerName() {
		return AssetReceiptConstants.getOwnerName(
			getOwnerClassName(), getOwnerClassPK(), getLegalEntityName());
	}

	public String getProductClassName() {
		return PortalUtil.getClassName(getProductClassNameId());
	}

	public Date getRenewedAssetReceiptLicensesEndDate(int usageType)
		throws SystemException {

		List<AssetReceiptLicense> assetReceiptLicenses =
			AssetReceiptLicenseLocalServiceUtil.getRenewedAssetReceiptLicenses(
				getAssetReceiptId(), usageType, 0, 1);

		if (assetReceiptLicenses.isEmpty()) {
			return null;
		}

		AssetReceiptLicense assetReceiptLicense = assetReceiptLicenses.get(0);

		return assetReceiptLicense.getEndDate();
	}

	public Date getRenewedAssetReceiptSupportsEndDate(int usageType)
		throws SystemException {

		List<AssetReceiptSupport> assetReceiptSupports =
			AssetReceiptSupportLocalServiceUtil.getRenewedAssetReceiptSupports(
				getAssetReceiptId(), usageType, 0, 1);

		if (assetReceiptSupports.isEmpty()) {
			return null;
		}

		AssetReceiptSupport assetReceiptSupport = assetReceiptSupports.get(0);

		return assetReceiptSupport.getEndDate();
	}

	public boolean hasActiveAssetReceiptLicenses() throws SystemException {
		int count =
			AssetReceiptLicenseLocalServiceUtil.
				getActiveAssetReceiptLicensesCount(getAssetReceiptId());

		if (count > 0) {
			return true;
		}

		return false;
	}

	public boolean hasActiveAssetReceiptLicenses(int usageType)
		throws SystemException {

		int count =
			AssetReceiptLicenseLocalServiceUtil.
				getActiveAssetReceiptLicensesCount(
					getAssetReceiptId(), usageType);

		if (count > 0) {
			return true;
		}

		return false;
	}

	public boolean hasActiveAssetReceiptSupports(int usageType)
		throws SystemException {

		int count =
			AssetReceiptSupportLocalServiceUtil.
				getActiveAssetReceiptSupportsCount(
					getAssetReceiptId(), usageType);

		if (count > 0) {
			return true;
		}

		return false;
	}

	public boolean hasRenewedAssetReceiptLicenses(int usageType)
		throws SystemException {

		int count =
			AssetReceiptLicenseLocalServiceUtil.
				getRenewedAssetReceiptLicensesCount(
					getAssetReceiptId(), usageType);

		if (count > 0) {
			return true;
		}

		return false;
	}

	public boolean hasRenewedAssetReceiptSupports(int usageType)
		throws SystemException {

		int count =
			AssetReceiptSupportLocalServiceUtil.
				getRenewedAssetReceiptSupportsCount(
					getAssetReceiptId(), usageType);

		if (count > 0) {
			return true;
		}

		return false;
	}

	public boolean isOwnerCorpProject() {
		long classNameId = getOwnerClassNameId();

		if (classNameId ==
				PortalUtil.getClassNameId(CorpProject.class.getName())) {

			return true;
		}

		return false;
	}

	public boolean isOwnerUser() {
		long classNameId = getOwnerClassNameId();

		if (classNameId == PortalUtil.getClassNameId(User.class.getName())) {
			return true;
		}

		return false;
	}

}