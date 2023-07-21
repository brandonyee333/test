/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.announcements.web.internal.portlet.action;

import com.liferay.announcements.constants.AnnouncementsPortletKeys;
import com.liferay.announcements.kernel.model.AnnouncementsEntry;
import com.liferay.announcements.kernel.model.AnnouncementsFlagConstants;
import com.liferay.announcements.web.constants.AnnouncementsWebKeys;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.sanitizer.Sanitizer;
import com.liferay.portal.kernel.sanitizer.SanitizerException;
import com.liferay.portal.kernel.sanitizer.SanitizerUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portlet.announcements.model.impl.AnnouncementsEntryImpl;

import java.util.Date;

import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author Adolfo Pérez
 */
@Component(
	property = {
		"javax.portlet.name=" + AnnouncementsPortletKeys.ALERTS,
		"javax.portlet.name=" + AnnouncementsPortletKeys.ANNOUNCEMENTS,
		"mvc.command.name=/announcements/preview_entry"
	}
)
public class PreviewEntryMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		renderRequest.setAttribute(
			AnnouncementsWebKeys.ANNOUNCEMENTS_ENTRY,
			_getAnnouncementsEntry(renderRequest));
		renderRequest.setAttribute(
			AnnouncementsWebKeys.VIEW_ENTRY_FLAG_VALUE,
			AnnouncementsFlagConstants.NOT_HIDDEN);

		return "/view_entry.jsp";
	}

	private AnnouncementsEntry _getAnnouncementsEntry(
			PortletRequest portletRequest)
		throws PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		User user = themeDisplay.getUser();

		Date now = new Date();

		String[] distributionScopeParts = StringUtil.split(
			ParamUtil.getString(portletRequest, "distributionScope"));

		long classNameId = 0;
		long classPK = 0;

		if (distributionScopeParts.length == 2) {
			classNameId = GetterUtil.getLong(distributionScopeParts[0]);

			if (classNameId > 0) {
				classPK = GetterUtil.getLong(distributionScopeParts[1]);
			}
		}

		String title = ParamUtil.getString(portletRequest, "title");
		String content = ParamUtil.getString(portletRequest, "content");
		String url = ParamUtil.getString(portletRequest, "url");
		String type = ParamUtil.getString(portletRequest, "type");
		int priority = ParamUtil.getInteger(portletRequest, "priority");
		boolean alert = ParamUtil.getBoolean(portletRequest, "alert");

		AnnouncementsEntry entry = new AnnouncementsEntryImpl();

		try {
			content = SanitizerUtil.sanitize(
				themeDisplay.getCompanyId(), themeDisplay.getSiteGroupId(),
				user.getUserId(), AnnouncementsEntry.class.getName(), 0,
				ContentTypes.TEXT_HTML, Sanitizer.MODE_ALL, content, null);
		}
		catch (SanitizerException se) {
			throw new PortletException(se);
		}

		entry.setCompanyId(user.getCompanyId());
		entry.setUserId(user.getUserId());
		entry.setUserName(user.getFullName());
		entry.setCreateDate(now);
		entry.setModifiedDate(now);
		entry.setClassNameId(classNameId);
		entry.setClassPK(classPK);
		entry.setTitle(title);
		entry.setContent(content);
		entry.setUrl(url);
		entry.setType(type);
		entry.setDisplayDate(now);
		entry.setExpirationDate(now);
		entry.setPriority(priority);
		entry.setAlert(alert);

		return entry;
	}

}