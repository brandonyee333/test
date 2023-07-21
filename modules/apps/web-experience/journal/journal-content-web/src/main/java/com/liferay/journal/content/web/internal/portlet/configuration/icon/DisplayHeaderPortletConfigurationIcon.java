/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.content.web.internal.portlet.configuration.icon;

import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.journal.constants.JournalContentPortletKeys;
import com.liferay.journal.content.web.configuration.JournalContentConfigurationUtil;
import com.liferay.portal.kernel.portlet.configuration.icon.PortletConfigurationIcon;
import com.liferay.portal.kernel.util.Portal;

import javax.servlet.ServletContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author     Daniel Couso
 * @deprecated As of Wilberforce (7.0.x), with no direct replacement
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + JournalContentPortletKeys.JOURNAL_CONTENT,
		"path=-"
	},
	service = PortletConfigurationIcon.class
)
@Deprecated
public class DisplayHeaderPortletConfigurationIcon
	extends BaseJournalArticlePortletConfigurationIcon {

	@Override
	public String getCssClass() {
		return "divider";
	}

	@Override
	public String getJspPath() {
		return null;
	}

	@Override
	public double getSeparateMenusWeight() {
		return 0.9;
	}

	@Override
	public double getSingleMenuApplicationWeight() {
		return 0.9;
	}

	@Override
	public double getSingleMenuContentWeight() {
		return 17;
	}

	@Override
	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.journal.content.web)",
		unbind = "-"
	)
	public void setServletContext(ServletContext servletContext) {
		super.setServletContext(servletContext);
	}

	@Reference(unbind = "-")
	protected void setJournalContentConfigurationUtil(
		JournalContentConfigurationUtil journalContentConfigurationUtil) {

		this.journalContentConfigurationUtil = journalContentConfigurationUtil;
	}

	@Reference(unbind = "-")
	protected void setPortal(Portal portal) {
		ddmStructureClassNameId = portal.getClassNameId(DDMStructure.class);
	}

}