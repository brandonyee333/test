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

package com.liferay.osb.marketplaceadmin.search;

import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.portal.kernel.dao.search.DisplayTerms;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.PortletRequest;

/**
 * @author Douglas Wong
 * @author Ryan Park
 */
public class AppEntryDisplayTerms extends DisplayTerms {

	public static final String ASSET_CATEGORY_ID = "assetCategoryId";

	public static final String HIDE_LIFERAY_APP_ENTRIES =
		"hideLiferayAppEntries";

	public static final String TITLE = "title";

	public AppEntryDisplayTerms(PortletRequest portletRequest) {
		super(portletRequest);

		assetCategoryId = ParamUtil.getLong(portletRequest, ASSET_CATEGORY_ID);

		String hideLiferayApps = ParamUtil.getString(
			portletRequest, HIDE_LIFERAY_APP_ENTRIES);

		if (Validator.isNull(hideLiferayApps)) {
			String tabs2 = ParamUtil.getString(portletRequest, "tabs2");

			if (tabs2.equals("pending-qa")) {
				hideLiferayAppEntries = true;
			}
			else {
				hideLiferayAppEntries = false;
			}
		}
		else {
			hideLiferayAppEntries = GetterUtil.getBoolean(hideLiferayApps);
		}

		title = ParamUtil.getString(portletRequest, TITLE);
	}

	public long getAssetCategoryId() {
		return assetCategoryId;
	}

	public String getTitle() {
		return title;
	}

	public boolean isHideLiferayAppEntries() {
		return hideLiferayAppEntries;
	}

	protected long assetCategoryId;
	protected boolean hideLiferayAppEntries;
	protected String title;

}