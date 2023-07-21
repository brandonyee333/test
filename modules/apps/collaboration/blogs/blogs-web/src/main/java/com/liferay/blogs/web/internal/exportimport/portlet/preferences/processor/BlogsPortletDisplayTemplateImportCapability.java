/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.blogs.web.internal.exportimport.portlet.preferences.processor;

import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.portlet.preferences.processor.Capability;
import com.liferay.portlet.display.template.exportimport.portlet.preferences.processor.PortletDisplayTemplateImportCapability;

import javax.portlet.PortletPreferences;

import org.osgi.service.component.annotations.Component;

/**
 * @author Máté Thurzó
 */
@Component(
	immediate = true,
	service = {
		Capability.class, BlogsPortletDisplayTemplateImportCapability.class
	}
)
public class BlogsPortletDisplayTemplateImportCapability
	extends PortletDisplayTemplateImportCapability {

	@Override
	protected String getDisplayStyle(
		PortletDataContext portletDataContext, String portletId,
		PortletPreferences portletPreferences) {

		return BlogsExportImportPortletPreferencesProcessorUtil.getDisplayStyle(
			portletPreferences);
	}

	@Override
	protected long getDisplayStyleGroupId(
		PortletDataContext portletDataContext, String portletId,
		PortletPreferences portletPreferences) {

		return BlogsExportImportPortletPreferencesProcessorUtil.
			getDisplayStyleGroupId(portletPreferences);
	}

}