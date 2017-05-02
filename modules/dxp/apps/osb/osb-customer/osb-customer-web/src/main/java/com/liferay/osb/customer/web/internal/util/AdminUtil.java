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

package com.liferay.osb.customer.web.internal.util;

import com.liferay.knowledge.base.constants.KBFolderConstants;
import com.liferay.knowledge.base.model.KBArticle;
import com.liferay.knowledge.base.model.KBFolder;
import com.liferay.knowledge.base.service.KBArticleServiceUtil;
import com.liferay.knowledge.base.service.KBFolderServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.HashMap;
import java.util.Map;

import javax.portlet.PortletURL;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alan Zhang
 */
public class AdminUtil {

	public static void addPortletBreadcrumbEntries(
			long parentResourceClassNameId, long parentResourcePrimKey,
			String mvcPath, HttpServletRequest request,
			RenderResponse renderResponse)
		throws PortalException {

		Map<String, Object> parameters = new HashMap<>();

		parameters.put("mvcPath", mvcPath);
		parameters.put("parentResourceClassNameId", parentResourceClassNameId);
		parameters.put("parentResourcePrimKey", parentResourcePrimKey);

		addPortletBreadcrumbEntries(parameters, request, renderResponse);
	}

	protected static void addPortletBreadcrumbEntries(
			Map<String, Object> parameters, HttpServletRequest request,
			RenderResponse renderResponse)
		throws PortalException {

		PortletURL portletURL = renderResponse.createRenderURL();

		for (Map.Entry<String, Object> entry : parameters.entrySet()) {
			Object value = entry.getValue();

			portletURL.setParameter(entry.getKey(), value.toString());
		}

		long kbFolderClassNameId = PortalUtil.getClassNameId(
			KBFolderConstants.getClassName());

		long parentResourceClassNameId = (Long)parameters.get(
			"parentResourceClassNameId");
		long parentResourcePrimKey = (Long)parameters.get(
			"parentResourcePrimKey");

		String mvcPath = (String)parameters.get("mvcPath");

		if (parentResourcePrimKey ==
				KBFolderConstants.DEFAULT_PARENT_FOLDER_ID) {

			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
				WebKeys.THEME_DISPLAY);

			PortalUtil.addPortletBreadcrumbEntry(
				request, themeDisplay.translate("home"), portletURL.toString());
		}
		else if (parentResourceClassNameId == kbFolderClassNameId) {
			KBFolder kbFolder = KBFolderServiceUtil.getKBFolder(
				parentResourcePrimKey);

			addPortletBreadcrumbEntries(
				kbFolder.getClassNameId(), kbFolder.getParentKBFolderId(),
				mvcPath, request, renderResponse);

			PortalUtil.addPortletBreadcrumbEntry(
				request, kbFolder.getName(), portletURL.toString());
		}
		else {
			KBArticle kbArticle = KBArticleServiceUtil.getLatestKBArticle(
				parentResourcePrimKey, WorkflowConstants.STATUS_ANY);

			addPortletBreadcrumbEntries(
				kbArticle.getParentResourceClassNameId(),
				kbArticle.getParentResourcePrimKey(), mvcPath, request,
				renderResponse);

			PortalUtil.addPortletBreadcrumbEntry(
				request, kbArticle.getTitle(), portletURL.toString());
		}
	}

}