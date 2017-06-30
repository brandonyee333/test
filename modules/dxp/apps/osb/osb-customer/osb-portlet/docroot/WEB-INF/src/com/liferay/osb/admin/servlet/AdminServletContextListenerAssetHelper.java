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

package com.liferay.osb.admin.servlet;

import com.liferay.compat.portal.kernel.util.LocaleUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.PortletPropsValues;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.asset.NoSuchVocabularyException;
import com.liferay.portlet.asset.model.AssetCategory;
import com.liferay.portlet.asset.model.AssetVocabulary;
import com.liferay.portlet.asset.service.AssetCategoryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetVocabularyLocalServiceUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 * @author Amos Fong
 */
public class AdminServletContextListenerAssetHelper {

	public static void setup() throws Exception {
		try {
			AssetVocabularyLocalServiceUtil.getGroupVocabulary(
				OSBConstants.GROUP_GLOBAL_ID, "Support-Regions");
		}
		catch (NoSuchVocabularyException nsve) {
			Map<Locale, String> titleMap = new HashMap<Locale, String>();

			titleMap.put(LocaleUtil.getDefault(), "Support-Regions");

			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setAddGroupPermissions(true);
			serviceContext.setScopeGroupId(OSBConstants.GROUP_GLOBAL_ID);

			AssetVocabulary assetVocabulary =
				AssetVocabularyLocalServiceUtil.addVocabulary(
					OSBConstants.USER_SUPPORT_PM_USER_ID, null, titleMap, null,
					StringPool.BLANK, serviceContext);

			addAssetCategory(assetVocabulary.getVocabularyId(), 0, "Brazil");
			addAssetCategory(assetVocabulary.getVocabularyId(), 0, "China");
			addAssetCategory(assetVocabulary.getVocabularyId(), 0, "Hungary");
			addAssetCategory(assetVocabulary.getVocabularyId(), 0, "India");
			addAssetCategory(assetVocabulary.getVocabularyId(), 0, "Spain");
			addAssetCategory(
				assetVocabulary.getVocabularyId(), 0, "United States");
		}

		try {
			AssetVocabulary assetVocabulary =
				AssetVocabularyLocalServiceUtil.getGroupVocabulary(
					OSBConstants.GROUP_GUEST_ID, "Marketplace");

			if (PortletPropsValues.DEVELOPER_MODE_ENABLED) {
				OSBConstants.ASSET_VOCABULARY_MARKETPLACE_ID =
					assetVocabulary.getVocabularyId();

				List<AssetCategory> assetCategories =
					AssetCategoryLocalServiceUtil.search(
						OSBConstants.GROUP_GUEST_ID,
						"Liferay Subscription Apps", new String[0], 0, 1);

				AssetCategory assetCategory = assetCategories.get(0);

				OSBConstants.ASSET_CATEGORY_EE_PLUGINS_ID =
					assetCategory.getCategoryId();
			}
		}
		catch (NoSuchVocabularyException nsve) {
			Map<Locale, String> titleMap = new HashMap<Locale, String>();

			titleMap.put(LocaleUtil.getDefault(), "Marketplace");

			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setAddGroupPermissions(true);
			serviceContext.setAddGuestPermissions(true);
			serviceContext.setScopeGroupId(OSBConstants.GROUP_GUEST_ID);

			AssetVocabulary assetVocabulary =
				AssetVocabularyLocalServiceUtil.addVocabulary(
					OSBConstants.USER_SUPPORT_PM_USER_ID, null, titleMap, null,
					StringPool.BLANK, serviceContext);

			if (PortletPropsValues.DEVELOPER_MODE_ENABLED) {
				OSBConstants.ASSET_VOCABULARY_MARKETPLACE_ID =
					assetVocabulary.getVocabularyId();
			}

			AssetCategory assetCategory = addAssetCategory(
				assetVocabulary.getVocabularyId(), 0, "Apps");

			addAssetCategory(
				assetVocabulary.getVocabularyId(),
				assetCategory.getCategoryId(), "Communication");
			addAssetCategory(
				assetVocabulary.getVocabularyId(),
				assetCategory.getCategoryId(), "Productivity");
			addAssetCategory(
				assetVocabulary.getVocabularyId(),
				assetCategory.getCategoryId(), "Security");
			addAssetCategory(
				assetVocabulary.getVocabularyId(),
				assetCategory.getCategoryId(), "Utility");

			assetCategory = addAssetCategory(
				assetVocabulary.getVocabularyId(), 0, "Templates");

			addAssetCategory(
				assetVocabulary.getVocabularyId(),
				assetCategory.getCategoryId(), "Page Layouts");
			addAssetCategory(
				assetVocabulary.getVocabularyId(),
				assetCategory.getCategoryId(), "Site Templates");

			assetCategory = addAssetCategory(
				assetVocabulary.getVocabularyId(), 0,
				"Liferay Subscription Apps");

			if (PortletPropsValues.DEVELOPER_MODE_ENABLED) {
				OSBConstants.ASSET_CATEGORY_EE_PLUGINS_ID =
					assetCategory.getCategoryId();
			}
		}
	}

	protected static AssetCategory addAssetCategory(
			long vocabularyId, long parentCategoryId, String title)
		throws PortalException, SystemException {

		Map<Locale, String> titleMap = new HashMap<Locale, String>();

		Locale defaultLocale = LocaleUtil.getDefault();

		titleMap.put(defaultLocale, title);

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);
		serviceContext.setScopeGroupId(OSBConstants.GROUP_GUEST_ID);

		return AssetCategoryLocalServiceUtil.addCategory(
			OSBConstants.USER_SUPPORT_PM_USER_ID, parentCategoryId, titleMap,
			null, vocabularyId, null, serviceContext);
	}

}