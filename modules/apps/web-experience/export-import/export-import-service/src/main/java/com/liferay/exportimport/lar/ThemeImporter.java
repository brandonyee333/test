/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.exportimport.lar;

import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.PortletDataHandlerKeys;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.LayoutSet;
import com.liferay.portal.kernel.service.LayoutSetLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.xml.Attribute;
import com.liferay.portal.kernel.xml.Element;

/**
 * @author Máté Thurzó
 */
public class ThemeImporter {

	public static ThemeImporter getInstance() {
		return _instance;
	}

	public void importTheme(
			PortletDataContext portletDataContext, LayoutSet layoutSet)
		throws Exception {

		boolean importThemeSettings = MapUtil.getBoolean(
			portletDataContext.getParameterMap(),
			PortletDataHandlerKeys.THEME_REFERENCE);

		if (_log.isDebugEnabled()) {
			_log.debug("Import theme settings " + importThemeSettings);
		}

		if (!importThemeSettings) {
			return;
		}

		Element importDataRootElement =
			portletDataContext.getImportDataRootElement();

		Element headerElement = importDataRootElement.element("header");

		String themeId = layoutSet.getThemeId();
		String colorSchemeId = layoutSet.getColorSchemeId();

		Attribute themeIdAttribute = headerElement.attribute("theme-id");

		if (themeIdAttribute != null) {
			themeId = themeIdAttribute.getValue();
		}

		Attribute colorSchemeIdAttribute = headerElement.attribute(
			"color-scheme-id");

		if (colorSchemeIdAttribute != null) {
			colorSchemeId = colorSchemeIdAttribute.getValue();
		}

		String css = GetterUtil.getString(headerElement.elementText("css"));

		LayoutSetLocalServiceUtil.updateLookAndFeel(
			layoutSet.getGroupId(), layoutSet.isPrivateLayout(), themeId,
			colorSchemeId, css);
	}

	private ThemeImporter() {
	}

	private static final Log _log = LogFactoryUtil.getLog(ThemeImporter.class);

	private static final ThemeImporter _instance = new ThemeImporter();

}