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