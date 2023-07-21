/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.announcements.web.internal.portlet.action;

import com.liferay.announcements.kernel.model.AnnouncementsEntry;
import com.liferay.announcements.kernel.service.AnnouncementsEntryServiceUtil;
import com.liferay.announcements.web.constants.AnnouncementsWebKeys;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import javax.portlet.PortletRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Raymond Augé
 */
public class ActionUtil {

	public static void getEntry(HttpServletRequest request) throws Exception {
		long entryId = ParamUtil.getLong(request, "entryId");

		AnnouncementsEntry entry = null;

		if (entryId > 0) {
			entry = AnnouncementsEntryServiceUtil.getEntry(entryId);
		}

		request.setAttribute(AnnouncementsWebKeys.ANNOUNCEMENTS_ENTRY, entry);
	}

	public static void getEntry(PortletRequest portletRequest)
		throws Exception {

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			portletRequest);

		getEntry(request);
	}

}