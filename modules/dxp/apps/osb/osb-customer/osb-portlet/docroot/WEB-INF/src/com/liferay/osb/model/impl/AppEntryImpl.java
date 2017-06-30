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

import com.liferay.compat.portal.kernel.util.ListUtil;
import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.osb.model.AppEntry;
import com.liferay.osb.model.AppPackage;
import com.liferay.osb.model.AppVersion;
import com.liferay.osb.model.AppVersionConstants;
import com.liferay.osb.model.AssetLicenseConstants;
import com.liferay.osb.model.AssetList;
import com.liferay.osb.model.CorpEntry;
import com.liferay.osb.model.CountryAppPricing;
import com.liferay.osb.model.DeveloperEntry;
import com.liferay.osb.service.AppPackageLocalServiceUtil;
import com.liferay.osb.service.AppVersionLocalServiceUtil;
import com.liferay.osb.service.AssetListLocalServiceUtil;
import com.liferay.osb.service.CorpEntryLocalServiceUtil;
import com.liferay.osb.service.CountryAppPricingLocalServiceUtil;
import com.liferay.osb.service.DeveloperEntryLocalServiceUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.PortalReleaseUtil;
import com.liferay.osb.util.PortletPropsValues;
import com.liferay.osb.util.WorkflowConstants;
import com.liferay.osb.util.comparator.AssetCategoryTreeComparator;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portlet.asset.model.AssetCategory;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.service.AssetCategoryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetTagLocalServiceUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Amos Fong
 * @author Ryan Park
 * @author Douglas Wong
 */
public class AppEntryImpl extends AppEntryBaseImpl {

	public AppEntryImpl() {
	}

	public AppVersion getActionableAppVersion() throws SystemException {
		AppVersion appVersion = getUnreleasedAppVersion();

		if (appVersion != null) {
			return appVersion;
		}

		return getLatestAppVersion();
	}

	public AppVersion getApprovedAppVersion() throws SystemException {
		return AppVersionLocalServiceUtil.fetchAppVersion(
			getAppEntryId(), AppVersionConstants.STATUSES_APPROVED, null);
	}

	public List<AssetCategory> getAssetCategories() throws SystemException {
		List<AssetCategory> assetCategories =
			AssetCategoryLocalServiceUtil.getCategories(
				AppEntry.class.getName(), getAppEntryId());

		return ListUtil.sort(
			assetCategories, new AssetCategoryTreeComparator(true));
	}

	public long[] getAssetCategoryIds() throws SystemException {
		List<AssetCategory> assetCategories = getAssetCategories();

		long[] assetCategoryIds = new long[assetCategories.size()];

		for (int i = 0; i < assetCategories.size(); i++) {
			AssetCategory assetCategory = assetCategories.get(i);

			assetCategoryIds[i] = assetCategory.getCategoryId();
		}

		return assetCategoryIds;
	}

	public AssetEntry getAssetEntry() throws PortalException, SystemException {
		return AssetEntryLocalServiceUtil.getEntry(
			AppEntry.class.getName(), getAppEntryId());
	}

	public long[] getAssetListIds() throws PortalException, SystemException {
		AssetEntry assetEntry = getAssetEntry();

		List<AssetList> assetLists = AssetListLocalServiceUtil.getAssetLists(
			assetEntry.getEntryId());

		long[] assetListIds = new long[assetLists.size()];

		for (int i = 0; i < assetLists.size(); i++) {
			AssetList assetList = assetLists.get(i);

			assetListIds[i] = assetList.getAssetListId();
		}

		return assetListIds;
	}

	public String[] getAssetTagNames() throws SystemException {
		return AssetTagLocalServiceUtil.getTagNames(
			AppEntry.class.getName(), getAppEntryId());
	}

	public long[] getAvailableCountryIds() throws SystemException {
		AppVersion appVersion = getApprovedAppVersion();

		if (appVersion == null) {
			appVersion = getLatestAppVersion();
		}

		List<CountryAppPricing> countryAppPricings =
			CountryAppPricingLocalServiceUtil.getAppVersionCountryAppPricings(
				appVersion.getAppVersionId());

		long[] availableCountryIds = new long[countryAppPricings.size()];

		for (int i = 0; i < countryAppPricings.size(); i++) {
			CountryAppPricing countryAppPricing = countryAppPricings.get(i);

			availableCountryIds[i] = countryAppPricing.getCountryId();
		}

		return availableCountryIds;
	}

	public String[] getCompatibilities() throws SystemException {
		AppVersion appVersion = getApprovedAppVersion();

		if (appVersion == null) {
			appVersion = getLatestAppVersion();
		}

		List<String> compatibility = new ArrayList<String>();

		List<AppPackage> appPackages =
			AppPackageLocalServiceUtil.getAppPackages(
				appVersion.getAppVersionId(), QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null);

		for (AppPackage appPackage : appPackages) {
			compatibility.add(
				PortalReleaseUtil.getVersion(
					appPackage.getCompatibility(),
					appPackage.getCompatibilityPlus()));
		}

		return compatibility.toArray(new String[0]);
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

	public AppVersion getLatestAppVersion() throws SystemException {
		return AppVersionLocalServiceUtil.fetchAppVersion(
			getAppEntryId(), WorkflowConstants.STATUS_ANY, null);
	}

	public String getLicenseLifetimeLabel() {
		if (isSOEEPlugin()) {
			return AssetLicenseConstants.getLifetimeLabel(
				AssetLicenseConstants.LIFETIME_SUBSCRIPTION);
		}

		return AssetLicenseConstants.getLifetimeLabel(getLicenseLifetime());
	}

	public String getLicenseTypeLabel() {
		return AssetLicenseConstants.getLicenseTypeLabel(getLicenseType());
	}

	public String getStatusLabel() {
		int status = getStatus();

		if ((status == WorkflowConstants.STATUS_APPROVED) ||
			(status == WorkflowConstants.STATUS_APPROVED_HIDDEN)) {

			return WorkflowConstants.LABEL_APPROVED;
		}
		else if (status == WorkflowConstants.STATUS_DENIED) {
			return WorkflowConstants.LABEL_DENIED;
		}
		else if (status == WorkflowConstants.STATUS_DRAFT) {
			return "unsubmitted";
		}
		else if (status == WorkflowConstants.STATUS_PENDING) {
			return WorkflowConstants.LABEL_PENDING;
		}
		else if (status == WorkflowConstants.STATUS_PENDING_AUTO_QA) {
			return WorkflowConstants.LABEL_PENDING_AUTO_QA;
		}
		else if (status == WorkflowConstants.STATUS_PENDING_QA) {
			return WorkflowConstants.LABEL_PENDING_QA;
		}
		else {
			return "not-available";
		}
	}

	public AppVersion getUnreleasedAppVersion() throws SystemException {
		return AppVersionLocalServiceUtil.fetchAppVersion(
			getAppEntryId(), AppVersionConstants.STATUSES_UNRELEASED, null);
	}

	public boolean hasAvailableCountry(long countryId)
		throws PortalException, SystemException {

		if (getLicenseType() == AssetLicenseConstants.LICENSE_TYPE_FREE) {
			return true;
		}

		AppVersion appVersion = getApprovedAppVersion();

		if (appVersion == null) {
			appVersion = getLatestAppVersion();
		}

		CountryAppPricing countryAppPricing =
			CountryAppPricingLocalServiceUtil.fetchCountryAppPricing(
				appVersion.getAppVersionId(), countryId);

		if (countryAppPricing == null) {
			return false;
		}
		else {
			return true;
		}
	}

	public boolean hasCustomOrderWorkflow() {
		if (Validator.isNotNull(getOrderURL())) {
			return true;
		}

		return false;
	}

	public boolean hasMultipleNewVersions()
		throws PortalException, SystemException {

		int count = AppVersionLocalServiceUtil.getAppVersionsByReleaseTypeCount(
			getAppEntryId(), AppVersionConstants.RELEASE_TYPE_NEW_VERSION);

		if (count > 1) {
			return true;
		}

		return false;
	}

	public boolean hasUnreleasedAppVersion() throws SystemException {
		int count = AppVersionLocalServiceUtil.getAppVersionsCount(
			getAppEntryId(), AppVersionConstants.STATUSES_UNRELEASED);

		if (count > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isApproved() {
		if ((getStatus() == WorkflowConstants.STATUS_APPROVED) ||
			(getStatus() == WorkflowConstants.STATUS_APPROVED_HIDDEN)) {

			return true;
		}
		else {
			return false;
		}
	}

	public boolean isCotermRequired() {
		if (isSupported()) {
			return true;
		}

		if (getLicenseLifetime() ==
				AssetLicenseConstants.LIFETIME_SUBSCRIPTION) {

			return true;
		}

		return false;
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

	public boolean isFirstSubmission() throws PortalException, SystemException {
		int count = AppVersionLocalServiceUtil.getAppVersionsCount(
			getAppEntryId(), WorkflowConstants.STATUS_ANY);

		if (count == 0) {
			return true;
		}
		else if (count > 1) {
			return false;
		}

		AppVersion appVersion = getLatestAppVersion();

		if (appVersion.isApproved()) {
			return false;
		}

		return true;
	}

	public boolean isFree() {
		if (getLicenseType() == AssetLicenseConstants.LICENSE_TYPE_FREE) {
			return true;
		}

		return false;
	}

	public boolean isLiferayEEPlugin() throws SystemException {
		List<AssetCategory> assetCategories = getAssetCategories();

		for (AssetCategory assetCategory : assetCategories) {
			if (assetCategory.getCategoryId() ==
					OSBConstants.ASSET_CATEGORY_EE_PLUGINS_ID) {

				return true;
			}
		}

		return false;
	}

	public boolean isSOEEPlugin() {
		String title = getTitle();

		return title.equals(
			PortletPropsValues.MARKETPLACE_SO_EE_APP_ENTRY_TITLE);
	}

	public boolean isStatusByDeveloper()
		throws PortalException, SystemException {

		DeveloperEntry developerEntry = getDeveloperEntry();

		if (developerEntry.isUser() &&
			(getStatusByUserId() == developerEntry.getUserId())) {

			return true;
		}
		else if (developerEntry.isCompany()) {
			CorpEntry corpEntry = CorpEntryLocalServiceUtil.getCorpEntry(
				developerEntry.getDossieraAccountKey());

			return CorpEntryLocalServiceUtil.hasUserCorpEntry(
				getStatusByUserId(), corpEntry.getCorpEntryId());
		}

		return false;
	}

}