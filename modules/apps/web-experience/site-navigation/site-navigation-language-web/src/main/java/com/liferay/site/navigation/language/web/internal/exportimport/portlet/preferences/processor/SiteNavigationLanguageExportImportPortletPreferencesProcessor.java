/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.site.navigation.language.web.internal.exportimport.portlet.preferences.processor;

import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.PortletDataException;
import com.liferay.exportimport.portlet.preferences.processor.Capability;
import com.liferay.exportimport.portlet.preferences.processor.ExportImportPortletPreferencesProcessor;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portlet.display.template.exportimport.portlet.preferences.processor.PortletDisplayTemplateExportCapability;
import com.liferay.portlet.display.template.exportimport.portlet.preferences.processor.PortletDisplayTemplateImportCapability;
import com.liferay.site.navigation.language.constants.SiteNavigationLanguagePortletKeys;

import java.util.List;

import javax.portlet.PortletPreferences;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jürgen Kappler
 */
@Component(
	immediate = true,
	property = "javax.portlet.name=" + SiteNavigationLanguagePortletKeys.SITE_NAVIGATION_LANGUAGE,
	service = ExportImportPortletPreferencesProcessor.class
)
public class SiteNavigationLanguageExportImportPortletPreferencesProcessor
	implements ExportImportPortletPreferencesProcessor {

	@Override
	public List<Capability> getExportCapabilities() {
		return ListUtil.toList(
			new Capability[] {_portletDisplayTemplateExportCapability});
	}

	@Override
	public List<Capability> getImportCapabilities() {
		return ListUtil.toList(
			new Capability[] {_portletDisplayTemplateImportCapability});
	}

	@Override
	public PortletPreferences processExportPortletPreferences(
			PortletDataContext portletDataContext,
			PortletPreferences portletPreferences)
		throws PortletDataException {

		return null;
	}

	@Override
	public PortletPreferences processImportPortletPreferences(
			PortletDataContext portletDataContext,
			PortletPreferences portletPreferences)
		throws PortletDataException {

		return null;
	}

	@Reference(unbind = "-")
	protected void setPortletDisplayTemplateExportCapability(
		PortletDisplayTemplateExportCapability
			portletDisplayTemplateExportCapability) {

		_portletDisplayTemplateExportCapability =
			portletDisplayTemplateExportCapability;
	}

	@Reference(unbind = "-")
	protected void setPortletDisplayTemplateImportCapability(
		PortletDisplayTemplateImportCapability
			portletDisplayTemplateImportCapability) {

		_portletDisplayTemplateImportCapability =
			portletDisplayTemplateImportCapability;
	}

	private PortletDisplayTemplateExportCapability
		_portletDisplayTemplateExportCapability;
	private PortletDisplayTemplateImportCapability
		_portletDisplayTemplateImportCapability;

}