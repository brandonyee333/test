/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
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