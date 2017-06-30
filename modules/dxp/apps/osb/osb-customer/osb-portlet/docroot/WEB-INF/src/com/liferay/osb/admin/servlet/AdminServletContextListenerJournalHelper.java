/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.admin.servlet;

import com.liferay.compat.portal.kernel.util.LocaleUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.journal.model.JournalStructure;
import com.liferay.portlet.journal.model.JournalTemplate;
import com.liferay.portlet.journal.model.JournalTemplateConstants;
import com.liferay.portlet.journal.service.JournalStructureLocalServiceUtil;
import com.liferay.portlet.journal.service.JournalTemplateLocalServiceUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 * @author Amos Fong
 */
public class AdminServletContextListenerJournalHelper {

	public static void setup() throws Exception {
		JournalStructure journalStructure = null;

		Locale defaultLocale = LocaleUtil.getDefault();

		List<JournalStructure> journalStructures =
			JournalStructureLocalServiceUtil.search(
				OSBConstants.COMPANY_ID,
				new long[] {OSBConstants.GROUP_GUEST_ID}, null,
				"Marketplace Ad", "Marketplace Ad", true, 0, 1, null);

		if (!journalStructures.isEmpty()) {
			journalStructure = journalStructures.get(0);
		}
		else {
			StringBundler xsl = new StringBundler(8);

			xsl.append("<root><dynamic-element name=\"image\" type=\"image\" ");
			xsl.append("index-type=\"\" repeatable=\"false\"/>");
			xsl.append("<dynamic-element name=\"content\" type=\"text_area\" ");
			xsl.append("index-type=\"\" repeatable=\"false\"/>");
			xsl.append("<dynamic-element name=\"appid\" type=\"text\" ");
			xsl.append("index-type=\"\" repeatable=\"false\"/>");
			xsl.append("<dynamic-element name=\"external_url\" type=\"text\" ");
			xsl.append("index-type=\"\" repeatable=\"false\"/></root>");

			Map<Locale, String> titleMap = new HashMap<Locale, String>();

			titleMap.put(defaultLocale, "Marketplace Ad");

			journalStructure = JournalStructureLocalServiceUtil.addStructure(
				OSBConstants.USER_DEFAULT_USER_ID, OSBConstants.GROUP_GUEST_ID,
				StringPool.BLANK, true, null, titleMap, titleMap,
				xsl.toString(), new ServiceContext());
		}

		JournalTemplate journalTemplate = null;

		List<JournalTemplate> journalTemplates =
			JournalTemplateLocalServiceUtil.getStructureTemplates(
				OSBConstants.GROUP_GUEST_ID, journalStructure.getStructureId());

		for (int i = 0; i < journalTemplates.size(); i++) {
			JournalTemplate curJournalTemplate = journalTemplates.get(i);

			String journalTemplateName = curJournalTemplate.getName(
				defaultLocale);

			if (journalTemplateName.equals("Marketplace Banner")) {
				journalTemplate = curJournalTemplate;

				break;
			}
		}

		if (journalTemplate == null) {
			Map<Locale, String> nameMap = new HashMap<Locale, String>();

			nameMap.put(defaultLocale, "Marketplace Banner");

			StringBundler xsl = new StringBundler(11);

			xsl.append("<a href=\"[$APPLICATIONENTRY_ID=$app-id.data$]\" ");
			xsl.append("#if ($appid.getData() != \"\")\n<a ");
			xsl.append("href=\"[$APPLICATIONENTRY_ID=$appid.data$]\" ");
			xsl.append("class=\"banner\" style=\"");
			xsl.append("background:url('$image.data') no-repeat\">\n<div ");
			xsl.append("class=\"invisible\">\n$content.data\n</div>\n</a>\n");
			xsl.append("#else\n<a href=\"$external_url.getData()\" ");
			xsl.append("target=\"_blank\" class=\"banner\" ");
			xsl.append("style=\"background:url('$image.data') no-repeat\">\n");
			xsl.append("<div class=\"invisible\">\n$content.data\n</div>\n");
			xsl.append("</a>\n#end");

			journalTemplate = JournalTemplateLocalServiceUtil.addTemplate(
				OSBConstants.USER_DEFAULT_USER_ID, OSBConstants.GROUP_GUEST_ID,
				StringPool.BLANK, true, journalStructure.getStructureId(),
				nameMap, nameMap, xsl.toString(), true,
				JournalTemplateConstants.LANG_TYPE_VM, true, false, null, null,
				new ServiceContext());
		}

		OSBConstants.JOURNAL_TEMPLATE_MARKETPLACE_BANNER_ID =
			journalTemplate.getTemplateId();

		journalTemplate = null;

		for (int i = 0; i < journalTemplates.size(); i++) {
			JournalTemplate curJournalTemplate = journalTemplates.get(i);

			String journalTemplateName = curJournalTemplate.getName(
				defaultLocale);

			if (journalTemplateName.equals("Marketplace Medium Ad")) {
				journalTemplate = curJournalTemplate;

				break;
			}
		}

		if (journalTemplate == null) {
			Map<Locale, String> titleMap = new HashMap<Locale, String>();

			titleMap.put(defaultLocale, "Marketplace Medium Ad");

			StringBundler xsl = new StringBundler(5);

			xsl.append("<a href=\"[$APPLICATIONENTRY_ID=$app-id.data$]\" ");
			xsl.append("class=\"medium-ad\" ");
			xsl.append("style=\"background:url('$image.data') ");
			xsl.append("no-repeat\"><div class=\"invisible\">$content.data");
			xsl.append("</div></a>");

			journalTemplate = JournalTemplateLocalServiceUtil.addTemplate(
				OSBConstants.USER_DEFAULT_USER_ID, OSBConstants.GROUP_GUEST_ID,
				StringPool.BLANK, true, journalStructure.getStructureId(),
				titleMap, titleMap, xsl.toString(), true,
				JournalTemplateConstants.LANG_TYPE_VM, true, false, null, null,
				new ServiceContext());
		}

		OSBConstants.JOURNAL_TEMPLATE_MARKETPLACE_MEDIUM_AD_ID =
			journalTemplate.getTemplateId();
	}

}