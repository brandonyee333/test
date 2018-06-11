/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.customer.web.internal.search;

import com.liferay.journal.model.JournalArticle;
import com.liferay.osb.customer.constants.OSBCustomerPortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Node;
import com.liferay.portal.kernel.xml.SAXReaderUtil;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;

import javax.portlet.PortletPreferences;

/**
 * @author Kyle Bischof
 * @author Amos Fong
 */
public class ArticleSearchUtil {

	public static String cleanKeywords(String keywords) {
		return keywords.replaceAll("(?i)\\b(and|not)\\b", StringPool.BLANK);
	}

	public static String[] getSearchStructureIds(long groupId)
		throws PortalException {

		long plid = PortalUtil.getPlidFromPortletId(
			groupId, OSBCustomerPortletKeys.SEARCH);

		Layout layout = LayoutLocalServiceUtil.getLayout(plid);

		PortletPreferences portletPreferences =
			PortletPreferencesFactoryUtil.getStrictPortletSetup(
				layout, OSBCustomerPortletKeys.SEARCH);

		return portletPreferences.getValues(
			"searchStructureIds", new String[0]);
	}

	public static String getSummary(
		JournalArticle journalArticle, String languageId) {

		String summary = StringPool.BLANK;

		try {
			Document xmlDocument = SAXReaderUtil.read(
				journalArticle.getContentByLocale(languageId));

			Node node = xmlDocument.selectSingleNode(
				"/root/dynamic-element[@name='" + Field.DESCRIPTION +
					"']/dynamic-content");

			summary = HtmlUtil.extractText(node.getText());
		}
		catch (Exception e) {
		}

		return summary;
	}

	public static Hits search(
			SearchContext searchContext, String[] entryClassNames,
			long[] assetCategoryIds, String[] languageIds,
			long[] permissionTypes, String[] searchStructureIds,
			String userLanguageId, int start, int end)
		throws PortalException {

		searchContext.setAssetCategoryIds(assetCategoryIds);

		Map<String, Serializable> attributes = new HashMap<>();

		attributes.put("languageIds", languageIds);
		attributes.put("paginationType", "regular");
		attributes.put("permissionTypes", permissionTypes);
		attributes.put("searchStructureIds", searchStructureIds);
		attributes.put("userLanguageId", userLanguageId);

		searchContext.setAttributes(attributes);

		searchContext.setEnd(end);
		searchContext.setEntryClassNames(entryClassNames);
		searchContext.setKeywords(searchContext.getKeywords());
		searchContext.setStart(start);

		Indexer indexer = new ArticleSearcher();

		return indexer.search(searchContext);
	}

}