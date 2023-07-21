/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.web.util;

import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalFolder;
import com.liferay.journal.model.JournalFolderConstants;
import com.liferay.journal.service.JournalFolderLocalServiceUtil;
import com.liferay.journal.util.comparator.ArticleCreateDateComparator;
import com.liferay.journal.util.comparator.ArticleDisplayDateComparator;
import com.liferay.journal.util.comparator.ArticleIDComparator;
import com.liferay.journal.util.comparator.ArticleModifiedDateComparator;
import com.liferay.journal.util.comparator.ArticleReviewDateComparator;
import com.liferay.journal.util.comparator.ArticleTitleComparator;
import com.liferay.journal.util.comparator.ArticleVersionComparator;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 * @author Wesley Gong
 * @author Angelo Jefferson
 * @author Hugo Huijser
 * @author Eduardo García
 */
public class JournalPortletUtil {

	public static void addPortletBreadcrumbEntries(
			JournalArticle article, HttpServletRequest request,
			PortletURL portletURL)
		throws Exception {

		JournalFolder folder = article.getFolder();

		if (folder.getFolderId() !=
				JournalFolderConstants.DEFAULT_PARENT_FOLDER_ID) {

			addPortletBreadcrumbEntries(folder, request, portletURL);
		}

		JournalArticle unescapedArticle = article.toUnescapedModel();

		portletURL.setParameter("mvcPath", "/edit_article.jsp");
		portletURL.setParameter(
			"groupId", String.valueOf(article.getGroupId()));
		portletURL.setParameter(
			"articleId", String.valueOf(article.getArticleId()));

		PortalUtil.addPortletBreadcrumbEntry(
			request, unescapedArticle.getTitle(), portletURL.toString());
	}

	public static void addPortletBreadcrumbEntries(
			JournalFolder folder, HttpServletRequest request,
			PortletURL portletURL)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		String mvcPath = ParamUtil.getString(request, "mvcPath");

		portletURL.setParameter(
			"folderId",
			String.valueOf(JournalFolderConstants.DEFAULT_PARENT_FOLDER_ID));

		if (mvcPath.equals("/select_folder.jsp")) {
			portletURL.setWindowState(LiferayWindowState.POP_UP);

			PortalUtil.addPortletBreadcrumbEntry(
				request, themeDisplay.translate("home"), portletURL.toString());
		}
		else {
			Map<String, Object> data = new HashMap<>();

			data.put("direction-right", Boolean.TRUE.toString());
			data.put(
				"folder-id", JournalFolderConstants.DEFAULT_PARENT_FOLDER_ID);

			PortalUtil.addPortletBreadcrumbEntry(
				request, themeDisplay.translate("home"), portletURL.toString(),
				data);
		}

		if (folder == null) {
			return;
		}

		List<JournalFolder> ancestorFolders = folder.getAncestors();

		Collections.reverse(ancestorFolders);

		for (JournalFolder ancestorFolder : ancestorFolders) {
			portletURL.setParameter(
				"folderId", String.valueOf(ancestorFolder.getFolderId()));

			Map<String, Object> data = new HashMap<>();

			data.put("direction-right", Boolean.TRUE.toString());
			data.put("folder-id", ancestorFolder.getFolderId());

			PortalUtil.addPortletBreadcrumbEntry(
				request, ancestorFolder.getName(), portletURL.toString(), data);
		}

		portletURL.setParameter(
			"folderId", String.valueOf(folder.getFolderId()));

		if (folder.getFolderId() !=
				JournalFolderConstants.DEFAULT_PARENT_FOLDER_ID) {

			JournalFolder unescapedFolder = folder.toUnescapedModel();

			Map<String, Object> data = new HashMap<>();

			data.put("direction-right", Boolean.TRUE.toString());
			data.put("folder-id", folder.getFolderId());

			PortalUtil.addPortletBreadcrumbEntry(
				request, unescapedFolder.getName(), portletURL.toString(),
				data);
		}
	}

	public static void addPortletBreadcrumbEntries(
			long folderId, HttpServletRequest request, PortletURL portletURL)
		throws Exception {

		if (folderId == JournalFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
			return;
		}

		JournalFolder folder = JournalFolderLocalServiceUtil.getFolder(
			folderId);

		addPortletBreadcrumbEntries(folder, request, portletURL);
	}

	public static OrderByComparator<JournalArticle> getArticleOrderByComparator(
		String orderByCol, String orderByType) {

		boolean orderByAsc = false;

		if (orderByType.equals("asc")) {
			orderByAsc = true;
		}

		OrderByComparator<JournalArticle> orderByComparator = null;

		if (orderByCol.equals("create-date")) {
			orderByComparator = new ArticleCreateDateComparator(orderByAsc);
		}
		else if (orderByCol.equals("display-date")) {
			orderByComparator = new ArticleDisplayDateComparator(orderByAsc);
		}
		else if (orderByCol.equals("id")) {
			orderByComparator = new ArticleIDComparator(orderByAsc);
		}
		else if (orderByCol.equals("modified-date")) {
			orderByComparator = new ArticleModifiedDateComparator(orderByAsc);
		}
		else if (orderByCol.equals("review-date")) {
			orderByComparator = new ArticleReviewDateComparator(orderByAsc);
		}
		else if (orderByCol.equals("title")) {
			orderByComparator = new ArticleTitleComparator(orderByAsc);
		}
		else if (orderByCol.equals("version")) {
			orderByComparator = new ArticleVersionComparator(orderByAsc);
		}

		return orderByComparator;
	}

}