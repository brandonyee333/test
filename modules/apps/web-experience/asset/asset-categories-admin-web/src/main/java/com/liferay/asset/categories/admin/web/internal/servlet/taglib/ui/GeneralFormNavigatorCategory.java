/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.categories.admin.web.internal.servlet.taglib.ui;

import com.liferay.asset.categories.admin.web.constants.AssetCategoriesConstants;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.servlet.taglib.ui.FormNavigatorCategory;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;

/**
 * @author Eudaldo Alonso
 */
@Component(
	property = "form.navigator.category.order:Integer=10",
	service = FormNavigatorCategory.class
)
public class GeneralFormNavigatorCategory implements FormNavigatorCategory {

	@Override
	public String getFormNavigatorId() {
		return AssetCategoriesConstants.FORM_NAVIGATOR_ID_EDIT_CATEGORY;
	}

	@Override
	public String getKey() {
		return AssetCategoriesConstants.CATEGORY_KEY_GENERAL;
	}

	@Override
	public String getLabel(Locale locale) {
		return LanguageUtil.get(locale, "general");
	}

}