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

package com.liferay.osb.customer.web.internal.search;

import com.liferay.portal.kernel.dao.search.DisplayTerms;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.PortletRequest;

/**
 * @author Alan Zhang
 */
public class ArticleDisplayTerms extends DisplayTerms {

	public static final String ASSET_CATEGORY_IDS = "assetCategoryIds";

	public static final String LANGUAGE_IDS = "languageIds";

	public static final String PERMISSION_TYPES = "permissionTypes";

	public ArticleDisplayTerms(PortletRequest portletRequest) {
		super(portletRequest);

		assetCategoryIds = ParamUtil.getLongValues(
			portletRequest, ASSET_CATEGORY_IDS);
		languageIds = ParamUtil.getParameterValues(
			portletRequest, LANGUAGE_IDS);
		permissionTypes = ParamUtil.getLongValues(
			portletRequest, PERMISSION_TYPES);
	}

	public long[] getAssetCategoryIds() {
		return assetCategoryIds;
	}

	public String[] getLanguageIds() {
		return languageIds;
	}

	public long[] getPermissionTypes() {
		return permissionTypes;
	}

	protected long[] assetCategoryIds;
	protected String[] languageIds;
	protected long[] permissionTypes;

}