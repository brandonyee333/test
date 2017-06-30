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

package com.liferay.osb.marketplaceserver.search;

import com.liferay.portal.kernel.dao.search.DisplayTerms;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.PortletRequest;

/**
 * @author Joan Kim
 * @author Douglas Wong
 */
public class AssetReceiptDisplayTerms extends DisplayTerms {

	public static final String ASSET_CATEGORY_ID = "assetCategoryId";

	public static final String ASSET_ENTRY_TITLE = "assetEntryTitle";

	public static final String COMPATIBILITY = "compatibility";

	public static final String FILTER_BY_COMPATIBILITY =
		"filterByCompatibility";

	public AssetReceiptDisplayTerms(PortletRequest portletRequest) {
		super(portletRequest);

		assetCategoryId = ParamUtil.getLong(portletRequest, ASSET_CATEGORY_ID);
		assetEntryTitle = ParamUtil.getString(
			portletRequest, ASSET_ENTRY_TITLE);
		compatibility = ParamUtil.getInteger(portletRequest, COMPATIBILITY);
		filterByCompatibility = ParamUtil.getBoolean(
			portletRequest, "filterByCompatibility");
	}

	public long getAssetCategoryId() {
		return assetCategoryId;
	}

	public String getAssetEntryTitle() {
		return assetEntryTitle;
	}

	public int getCompatibility() {
		return compatibility;
	}

	public boolean getFilterByCompatibility() {
		return filterByCompatibility;
	}

	protected long assetCategoryId;
	protected String assetEntryTitle;
	protected int compatibility;
	protected boolean filterByCompatibility;

}