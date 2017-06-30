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

package com.liferay.osb.service.impl;

import com.liferay.compat.portal.kernel.util.ListUtil;
import com.liferay.osb.model.AppEntry;
import com.liferay.osb.model.AssetRecommendationEntry;
import com.liferay.osb.service.base.AssetRecommendationEntryServiceBaseImpl;
import com.liferay.osb.service.permission.OSBAppEntryPermission;
import com.liferay.osb.util.OSBActionKeys;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portlet.asset.AssetRendererFactoryRegistryUtil;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.model.AssetRendererFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Peter Shin
 */
@JSONWebService(mode = JSONWebServiceMode.MANUAL)
public class AssetRecommendationEntryServiceImpl
	extends AssetRecommendationEntryServiceBaseImpl {

	public List<AssetRecommendationEntry> getAssetRecommendationEntries(
			long assetRecommendationSetId, int type, int start, int end)
		throws Exception {

		List<AssetRecommendationEntry> assetRecommendationEntries =
			new ArrayList<AssetRecommendationEntry>();

		int filteredListStart = 0;
		boolean filteredListExhausted = false;

		while ((assetRecommendationEntries.size() < end) &&
			   !filteredListExhausted) {

			List<AssetRecommendationEntry> filteredAssetRecommendationEntries =
				assetRecommendationEntryLocalService.
					getAssetRecommendationEntries(
						assetRecommendationSetId, type, filteredListStart,
						filteredListStart + _DELTA);

			filteredListStart += _DELTA;

			if (filteredAssetRecommendationEntries.size() != _DELTA) {
				filteredListExhausted = true;
			}

			for (AssetRecommendationEntry assetRecommendationEntry :
					filteredAssetRecommendationEntries) {

				if (assetRecommendationEntries.size() >= end) {
					break;
				}

				String className = assetRecommendationEntry.getClassName();
				long classPK = assetRecommendationEntry.getClassPK();

				AssetEntry assetEntry = assetEntryLocalService.fetchEntry(
					className, classPK);

				if (assetEntry == null) {
					continue;
				}

				if (!hasViewPermission(className, classPK)) {
					continue;
				}

				AppEntry appEntry =
					appEntryPersistence.fetchByPrimaryKey(
						assetEntry.getClassPK());

				if (appEntry == null) {
					continue;
				}

				if (appEntry.isApproved()) {
					assetRecommendationEntries.add(assetRecommendationEntry);
				}
			}
		}

		return ListUtil.subList(assetRecommendationEntries, start, end);
	}

	protected boolean hasViewPermission(String className, long classPK) {
		try {
			AssetRendererFactory assetRendererFactory =
				AssetRendererFactoryRegistryUtil.
					getAssetRendererFactoryByClassName(className);

			if (assetRendererFactory != null) {
				return assetRendererFactory.hasPermission(
					getPermissionChecker(), classPK, ActionKeys.VIEW);
			}
			else if (className.equals(AppEntry.class.getName())) {
				return OSBAppEntryPermission.contains(
					getPermissionChecker(), classPK, OSBActionKeys.VIEW);
			}

			return true;
		}
		catch (Exception e) {
			return false;
		}
	}

	private static final int _DELTA = 50;

}