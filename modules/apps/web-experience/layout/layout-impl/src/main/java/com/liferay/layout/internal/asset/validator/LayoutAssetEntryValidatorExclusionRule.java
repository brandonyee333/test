/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.internal.asset.validator;

import com.liferay.asset.kernel.validator.AssetEntryValidatorExclusionRule;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.sites.kernel.util.Sites;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(
	immediate = true,
	property = "model.class.name=com.liferay.portal.kernel.model.Layout",
	service = AssetEntryValidatorExclusionRule.class
)
public class LayoutAssetEntryValidatorExclusionRule
	implements AssetEntryValidatorExclusionRule {

	@Override
	public boolean isValidationExcluded(
		long groupId, String className, long classPK, long classTypePK,
		long[] categoryIds, String[] tagNames) {

		Layout layout = _layoutLocalService.fetchLayout(classPK);

		UnicodeProperties typeSettingsProperties =
			layout.getTypeSettingsProperties();

		boolean layoutUpdateable = GetterUtil.getBoolean(
			typeSettingsProperties.get(Sites.LAYOUT_UPDATEABLE));

		if (!layoutUpdateable) {
			return true;
		}

		return false;
	}

	@Reference
	private LayoutLocalService _layoutLocalService;

}