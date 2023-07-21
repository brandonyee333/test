/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.categories.admin.web.internal.servlet.taglib.ui;

import com.liferay.asset.categories.admin.web.constants.AssetCategoriesConstants;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.servlet.taglib.ui.BaseJSPFormNavigatorEntry;

import java.util.Locale;

/**
 * @author Eudaldo Alonso
 */
public abstract class BaseCategoryFormNavigatorEntry
	extends BaseJSPFormNavigatorEntry<AssetCategory> {

	@Override
	public String getCategoryKey() {
		return AssetCategoriesConstants.CATEGORY_KEY_GENERAL;
	}

	@Override
	public String getFormNavigatorId() {
		return AssetCategoriesConstants.FORM_NAVIGATOR_ID_EDIT_CATEGORY;
	}

	@Override
	public String getLabel(Locale locale) {
		return LanguageUtil.get(locale, getKey());
	}

}