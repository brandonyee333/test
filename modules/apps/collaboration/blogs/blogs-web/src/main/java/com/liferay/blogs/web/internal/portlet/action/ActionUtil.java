/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.blogs.web.internal.portlet.action;

import com.liferay.blogs.kernel.exception.NoSuchEntryException;
import com.liferay.blogs.kernel.model.BlogsEntry;
import com.liferay.blogs.kernel.service.BlogsEntryServiceUtil;
import com.liferay.petra.string.CharPool;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.PortletRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Brian Wing Shun Chan
 */
public class ActionUtil {

	public static void getEntry(HttpServletRequest request) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		long entryId = ParamUtil.getLong(request, "entryId");

		String urlTitle = ParamUtil.getString(request, "urlTitle");

		BlogsEntry entry = null;

		if (entryId > 0) {
			entry = BlogsEntryServiceUtil.getEntry(entryId);
		}
		else if (Validator.isNotNull(urlTitle)) {
			try {
				entry = BlogsEntryServiceUtil.getEntry(
					themeDisplay.getScopeGroupId(), urlTitle);
			}
			catch (NoSuchEntryException nsee) {
				if (urlTitle.indexOf(CharPool.UNDERLINE) != -1) {

					// Check another URL title for backwards compatibility. See
					// LEP-5733.

					urlTitle = StringUtil.replace(
						urlTitle, CharPool.UNDERLINE, CharPool.DASH);

					entry = BlogsEntryServiceUtil.getEntry(
						themeDisplay.getScopeGroupId(), urlTitle);
				}
				else {
					throw nsee;
				}
			}
		}

		if ((entry != null) && entry.isInTrash()) {
			throw new NoSuchEntryException("{entryId=" + entryId + "}");
		}

		request.setAttribute(WebKeys.BLOGS_ENTRY, entry);
	}

	public static void getEntry(PortletRequest portletRequest)
		throws Exception {

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			portletRequest);

		getEntry(request);
	}

}