/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.publisher.web.internal.exportimport.portlet.preferences.processor;

import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.portlet.preferences.processor.Capability;
import com.liferay.portlet.display.template.exportimport.portlet.preferences.processor.PortletDisplayTemplateExportCapability;

import javax.portlet.PortletPreferences;

import org.osgi.service.component.annotations.Component;

/**
 * Provides the implementation of the export capability {@link
 * AssetPublisherPortletDisplayTemplateExportCapability} for the Asset Publisher
 * portlet. This allows the display style and display style group ID to be
 * provided based on the existence of the template handler.
 *
 * @author Máté Thurzó
 */
@Component(
	immediate = true,
	service = {
		AssetPublisherPortletDisplayTemplateExportCapability.class,
		Capability.class
	}
)
public class AssetPublisherPortletDisplayTemplateExportCapability
	extends PortletDisplayTemplateExportCapability {

	@Override
	protected String getDisplayStyle(
		PortletDataContext portletDataContext, String portletId,
		PortletPreferences portletPreferences) {

		return AssetPublisherExportImportPortletPreferencesProcessorUtil.
			getDisplayStyle(portletPreferences);
	}

	@Override
	protected long getDisplayStyleGroupId(
		PortletDataContext portletDataContext, String portletId,
		PortletPreferences portletPreferences) {

		return AssetPublisherExportImportPortletPreferencesProcessorUtil.
			getDisplayStyleGroupId(portletPreferences);
	}

}