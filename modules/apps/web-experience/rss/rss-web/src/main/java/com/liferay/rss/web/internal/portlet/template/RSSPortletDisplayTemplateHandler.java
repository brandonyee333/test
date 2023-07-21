/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.rss.web.internal.portlet.template;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portletdisplaytemplate.BasePortletDisplayTemplateHandler;
import com.liferay.portal.kernel.template.TemplateHandler;
import com.liferay.portal.kernel.template.TemplateVariableGroup;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portlet.display.template.PortletDisplayTemplateConstants;
import com.liferay.rss.constants.RSSPortletKeys;
import com.liferay.rss.web.internal.configuration.RSSWebConfigurationValues;
import com.liferay.rss.web.internal.display.context.RSSDisplayContext;
import com.liferay.rss.web.internal.util.RSSFeed;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(
	immediate = true, property = "javax.portlet.name=" + RSSPortletKeys.RSS,
	service = TemplateHandler.class
)
public class RSSPortletDisplayTemplateHandler
	extends BasePortletDisplayTemplateHandler {

	@Override
	public String getClassName() {
		return RSSFeed.class.getName();
	}

	@Override
	public String getName(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", getClass());

		String portletTitle = _portal.getPortletTitle(
			RSSPortletKeys.RSS, resourceBundle);

		return portletTitle.concat(
			StringPool.SPACE
		).concat(
			LanguageUtil.get(locale, "template")
		);
	}

	@Override
	public String getResourceName() {
		return RSSPortletKeys.RSS;
	}

	@Override
	public Map<String, TemplateVariableGroup> getTemplateVariableGroups(
			long classPK, String language, Locale locale)
		throws Exception {

		Map<String, TemplateVariableGroup> templateVariableGroups =
			super.getTemplateVariableGroups(classPK, language, locale);

		TemplateVariableGroup templateVariableGroup =
			templateVariableGroups.get("fields");

		templateVariableGroup.empty();

		templateVariableGroup.addVariable(
			"rss-display-context", RSSDisplayContext.class,
			"rssDisplayContext");
		templateVariableGroup.addCollectionVariable(
			"rss-feeds", List.class, PortletDisplayTemplateConstants.ENTRIES,
			"rss-feed", RSSFeed.class, "curEntry", "getSyndFeed().getTitle()");

		return templateVariableGroups;
	}

	@Override
	protected String getTemplatesConfigPath() {
		return RSSWebConfigurationValues.DISPLAY_TEMPLATES_CONFIG;
	}

	@Reference
	private Portal _portal;

}