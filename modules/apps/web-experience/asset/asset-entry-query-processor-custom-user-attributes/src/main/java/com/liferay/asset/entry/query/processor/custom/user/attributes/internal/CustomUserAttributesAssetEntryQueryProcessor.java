/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.entry.query.processor.custom.user.attributes.internal;

import com.liferay.asset.kernel.service.persistence.AssetEntryQuery;
import com.liferay.asset.kernel.util.AssetEntryQueryProcessor;
import com.liferay.asset.kernel.util.BaseJSPAssetEntryQueryProcessor;
import com.liferay.asset.publisher.web.constants.AssetPublisherPortletKeys;
import com.liferay.asset.publisher.web.util.AssetPublisherUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.portlet.PortletPreferences;

import javax.servlet.ServletContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jorge Ferrer
 */
@Component(
	immediate = true,
	property = "javax.portlet.name=" + AssetPublisherPortletKeys.ASSET_PUBLISHER,
	service = AssetEntryQueryProcessor.class
)
public class CustomUserAttributesAssetEntryQueryProcessor
	extends BaseJSPAssetEntryQueryProcessor {

	@Override
	public String getJspPath() {
		return "/custom_user_attributes.jsp";
	}

	@Override
	public String getTitle(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return LanguageUtil.get(resourceBundle, "custom-user-attributes");
	}

	@Override
	public void processAssetEntryQuery(
			User user, PortletPreferences preferences,
			AssetEntryQuery assetEntryQuery)
		throws Exception {

		String customUserAttributes = GetterUtil.getString(
			preferences.getValue("customUserAttributes", StringPool.BLANK));

		AssetPublisherUtil.addUserAttributes(
			user, StringUtil.split(customUserAttributes), assetEntryQuery);
	}

	@Override
	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.asset.entry.query.processor.custom.user.attributes)",
		unbind = "-"
	)
	public void setServletContext(ServletContext servletContext) {
		super.setServletContext(servletContext);
	}

}