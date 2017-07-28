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

import com.liferay.asset.kernel.exception.NoSuchVocabularyException;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.LocaleUtil;

import java.util.HashMap;
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
			Map<Locale, String> titleMap = new HashMap<>();

			titleMap.put(LocaleUtil.getDefault(), "Support-Regions");

			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setAddGroupPermissions(true);
			serviceContext.setScopeGroupId(OSBConstants.GROUP_GLOBAL_ID);

			// TODO need database to grab OSBConstants.USER_SUPPORT_PM_USER_ID

			/*AssetVocabulary assetVocabulary =
				AssetVocabularyLocalServiceUtil.addVocabulary(
					OSBConstants.USER_SUPPORT_PM_USER_ID, 0L, StringPool.BLANK,
					titleMap, null, StringPool.BLANK, serviceContext);

			addAssetCategory(assetVocabulary.getVocabularyId(), 0, "Brazil");
			addAssetCategory(assetVocabulary.getVocabularyId(), 0, "China");
			addAssetCategory(assetVocabulary.getVocabularyId(), 0, "Hungary");
			addAssetCategory(assetVocabulary.getVocabularyId(), 0, "India");
			addAssetCategory(assetVocabulary.getVocabularyId(), 0, "Spain");
			addAssetCategory(
				assetVocabulary.getVocabularyId(), 0, "United States");*/
		}
	}

	protected static AssetCategory addAssetCategory(
			long vocabularyId, long parentCategoryId, String title)
		throws PortalException {

		Map<Locale, String> titleMap = new HashMap<>();

		Locale defaultLocale = LocaleUtil.getDefault();

		titleMap.put(defaultLocale, title);

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);
		serviceContext.setScopeGroupId(OSBConstants.GROUP_GUEST_ID);

		return AssetCategoryLocalServiceUtil.addCategory(
			OSBConstants.USER_SUPPORT_PM_USER_ID, OSBConstants.GROUP_GUEST_ID,
			parentCategoryId, titleMap, null, vocabularyId, null,
			serviceContext);
	}

}