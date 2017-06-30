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

import com.liferay.compat.portal.kernel.util.ArrayUtil;
import com.liferay.compat.portal.kernel.util.LocalizationUtil;
import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.osb.model.AppEntry;
import com.liferay.osb.model.AppVersion;
import com.liferay.osb.model.AppVersionConstants;
import com.liferay.osb.model.AssetLicense;
import com.liferay.osb.model.AssetLicenseConstants;
import com.liferay.osb.model.ContractEntry;
import com.liferay.osb.model.ContractEntryConstants;
import com.liferay.osb.model.DeveloperEntry;
import com.liferay.osb.service.AppEntryLocalServiceUtil;
import com.liferay.osb.service.AppVersionLocalServiceUtil;
import com.liferay.osb.service.AssetLicenseLocalServiceUtil;
import com.liferay.osb.service.ContractEntryLocalServiceUtil;
import com.liferay.osb.service.DeveloperEntryLocalServiceUtil;
import com.liferay.osb.util.PortletPropsValues;
import com.liferay.osb.util.WorkflowConstants;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portlet.asset.model.AssetCategory;
import com.liferay.portlet.asset.service.AssetCategoryLocalServiceUtil;

import java.util.List;

/**
 * @author Amos Fong
 * @author Douglas Wong
 * @author Ryan Park
 */
public class AppVersionImpl extends AppVersionBaseImpl {

	public AppVersionImpl() {
	}

	public AppEntry getAppEntry() throws PortalException, SystemException {
		return AppEntryLocalServiceUtil.getAppEntry(getAppEntryId());
	}

	public List<AssetLicense> getAssetLicenses()
		throws PortalException, SystemException {

		return AssetLicenseLocalServiceUtil.getAssetLicenses(
			AppVersion.class.getName(), getAppVersionId(),
			WorkflowConstants.STATUS_ANY, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	public ContractEntry getContractEntry()
		throws PortalException, SystemException {

		if (getContractEntryId() > 0) {
			return ContractEntryLocalServiceUtil.getContractEntry(
				getContractEntryId());
		}
		else {
			return ContractEntryLocalServiceUtil.getLatestContractEntry(
				ContractEntryConstants.DEFAULT_CLASS_NAME_ID,
				ContractEntryConstants.DEFAULT_CLASS_PK,
				ContractEntryConstants.TYPE_APP_EULA);
		}
	}

	public String getDefaultLanguageId() {
		String[] availableLocales = LocalizationUtil.getAvailableLocales(
			getDescription());

		if (availableLocales.length > 0) {
			return LocalizationUtil.getDefaultLocale(getDescription());
		}

		if (PortletPropsValues.CONTRACT_ENTRY_LOCALIZED_ENABLED &&
			(getContractEntryId() > 0)) {

			try {
				ContractEntry contractEntry =
					ContractEntryLocalServiceUtil.getContractEntry(
						getContractEntryId());

				return LocalizationUtil.getDefaultLocale(
					contractEntry.getContent());
			}
			catch (Exception e) {
			}
		}

		availableLocales = LocalizationUtil.getAvailableLocales(getChangeLog());

		if (availableLocales.length > 0) {
			return LocalizationUtil.getDefaultLocale(getChangeLog());
		}

		return null;
	}

	public DeveloperEntry getDeveloperEntry() throws SystemException {
		DeveloperEntry developerEntry =
			DeveloperEntryLocalServiceUtil.fetchDeveloperEntry(
				getDeveloperEntryId());

		if (developerEntry != null) {
			return developerEntry;
		}

		return new DeveloperEntryImpl();
	}

	public String getDeveloperEntryName()
		throws PortalException, SystemException {

		DeveloperEntry developerEntry = getDeveloperEntry();

		return developerEntry.getName();
	}

	public int getLicenseType() throws PortalException, SystemException {
		AppEntry appEntry = getAppEntry();

		return appEntry.getLicenseType();
	}

	public String getProductTypeLabel() {
		return AppVersionConstants.getProductTypeLabel(getProductType());
	}

	public String getStatusLabel() {
		int status = getStatus();

		if ((status == WorkflowConstants.STATUS_APPROVED) ||
			(status == WorkflowConstants.STATUS_APPROVED_HIDDEN)) {

			return WorkflowConstants.LABEL_APPROVED;
		}
		else if (status == WorkflowConstants.STATUS_DENIED) {
			return "version-denied";
		}
		else if (status == WorkflowConstants.STATUS_DRAFT) {
			return "version-unsubmitted";
		}
		else if (status == WorkflowConstants.STATUS_PENDING) {
			return "version-pending";
		}
		else if (status == WorkflowConstants.STATUS_PENDING_QA) {
			return "version-pending-qa";
		}
		else {
			return "N/A";
		}
	}

	public boolean hasCustomOrderWorkflow() {
		if (Validator.isNotNull(getOrderURL())) {
			return true;
		}

		return false;
	}

	public boolean hasTrialLicense() throws PortalException, SystemException {
		AppEntry appEntry = getAppEntry();

		int count = AssetLicenseLocalServiceUtil.getAssetLicensesCount(
			AppVersion.class.getName(), getAppVersionId(),
			AssetLicenseConstants.USAGE_TYPE_TRIAL, appEntry.getLicenseType(),
			WorkflowConstants.STATUS_APPROVED);

		if (count > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isAddResourcesImporter() throws SystemException {
		if (_addResourcesImporter != null) {
			return _addResourcesImporter.booleanValue();
		}

		List<AssetCategory> assetCategories =
			AssetCategoryLocalServiceUtil.getCategories(
				AppVersion.class.getName(), getAppVersionId());

		for (AssetCategory assetCategory : assetCategories) {
			if (ArrayUtil.contains(
					PortletPropsValues.
						MARKETPLACE_ADD_RESOURCES_IMPORTER_ASSET_CATEGORY_IDS,
					assetCategory.getCategoryId())) {

				_addResourcesImporter = Boolean.TRUE;

				return _addResourcesImporter.booleanValue();
			}
		}

		_addResourcesImporter = Boolean.FALSE;

		return _addResourcesImporter.booleanValue();
	}

	public boolean isApproved() {
		if (ArrayUtil.contains(
				AppVersionConstants.STATUSES_APPROVED, getStatus())) {

			return true;
		}
		else {
			return false;
		}
	}

	public boolean isDeveloperEntryCorpEntry() throws SystemException {
		DeveloperEntry developerEntry = getDeveloperEntry();

		return developerEntry.isCompany();
	}

	public boolean isDeveloperEntryLiferayInc() throws SystemException {
		DeveloperEntry developerEntry = getDeveloperEntry();

		return developerEntry.isLiferayInc();
	}

	public boolean isDeveloperEntryUser() throws SystemException {
		DeveloperEntry developerEntry = getDeveloperEntry();

		return developerEntry.isUser();
	}

	public boolean isFree() throws PortalException, SystemException {
		AppEntry appEntry = getAppEntry();

		return appEntry.isFree();
	}

	public boolean isLatestApproved() throws SystemException {
		if (!isApproved()) {
			return false;
		}

		AppVersion appVersion = AppVersionLocalServiceUtil.fetchAppVersion(
			getAppEntryId(), AppVersionConstants.STATUSES_APPROVED, null);

		if (appVersion.getAppVersionId() == getAppVersionId()) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isNewRelease() {
		if (getReleaseType() == AppVersionConstants.RELEASE_TYPE_NEW_VERSION) {
			return true;
		}

		return false;
	}

	public boolean isPending() {
		if (ArrayUtil.contains(
				AppVersionConstants.STATUSES_PENDING, getStatus())) {

			return true;
		}
		else {
			return false;
		}
	}

	public boolean isSOEEPlugin() {
		String title = getTitle();

		return title.equals(
			PortletPropsValues.MARKETPLACE_SO_EE_APP_ENTRY_TITLE);
	}

	private Boolean _addResourcesImporter;

}