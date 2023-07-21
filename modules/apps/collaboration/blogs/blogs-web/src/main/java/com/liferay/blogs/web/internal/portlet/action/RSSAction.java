/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.blogs.web.internal.portlet.action;

import com.liferay.blogs.configuration.BlogsGroupServiceOverriddenConfiguration;
import com.liferay.blogs.kernel.service.BlogsEntryService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.permission.GroupPermissionUtil;
import com.liferay.portal.kernel.settings.GroupServiceSettingsLocator;
import com.liferay.portal.kernel.struts.StrutsAction;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.RSSUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.struts.BaseRSSStrutsAction;
import com.liferay.portlet.blogs.constants.BlogsConstants;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	immediate = true, property = "path=/blogs/rss", service = StrutsAction.class
)
public class RSSAction extends BaseRSSStrutsAction {

	@Override
	protected byte[] getRSS(HttpServletRequest request) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		Layout layout = themeDisplay.getLayout();

		long plid = ParamUtil.getLong(request, "plid");

		if (plid == LayoutConstants.DEFAULT_PLID) {
			plid = themeDisplay.getPlid();
		}

		long companyId = ParamUtil.getLong(request, "companyId");
		long groupId = ParamUtil.getLong(request, "groupId");
		long organizationId = ParamUtil.getLong(request, "organizationId");
		int status = WorkflowConstants.STATUS_APPROVED;
		int max = ParamUtil.getInteger(
			request, "max", SearchContainer.DEFAULT_DELTA);
		String type = ParamUtil.getString(
			request, "type", RSSUtil.FORMAT_DEFAULT);
		double version = ParamUtil.getDouble(
			request, "version", RSSUtil.VERSION_DEFAULT);
		String displayStyle = ParamUtil.getString(
			request, "displayStyle", RSSUtil.DISPLAY_STYLE_DEFAULT);

		String feedURL =
			themeDisplay.getPortalURL() + themeDisplay.getPathMain() +
				"/blogs/find_entry?";

		String entryURL = feedURL;

		String rss = StringPool.BLANK;

		if (companyId > 0) {
			feedURL = StringPool.BLANK;

			rss = _blogsEntryService.getCompanyEntriesRSS(
				companyId, new Date(), status, max, type, version, displayStyle,
				feedURL, entryURL, themeDisplay);
		}
		else if (groupId > 0) {
			feedURL += "p_l_id=" + plid;

			entryURL = feedURL;

			rss = _blogsEntryService.getGroupEntriesRSS(
				groupId, new Date(), status, max, type, version, displayStyle,
				feedURL, entryURL, themeDisplay);
		}
		else if (organizationId > 0) {
			feedURL = StringPool.BLANK;

			rss = _blogsEntryService.getOrganizationEntriesRSS(
				organizationId, new Date(), status, max, type, version,
				displayStyle, feedURL, entryURL, themeDisplay);
		}
		else if (layout != null) {
			groupId = themeDisplay.getScopeGroupId();

			feedURL = themeDisplay.getPathMain() + "/blogs/rss";

			entryURL = feedURL;

			rss = _blogsEntryService.getGroupEntriesRSS(
				groupId, new Date(), status, max, type, version, displayStyle,
				feedURL, entryURL, themeDisplay);
		}

		return rss.getBytes(StringPool.UTF8);
	}

	@Override
	protected boolean isRSSFeedsEnabled(HttpServletRequest request)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		BlogsGroupServiceOverriddenConfiguration
			blogsGroupServiceOverriddenConfiguration =
				_configurationProvider.getConfiguration(
					BlogsGroupServiceOverriddenConfiguration.class,
					new GroupServiceSettingsLocator(
						themeDisplay.getSiteGroupId(),
						BlogsConstants.SERVICE_NAME));

		if (!blogsGroupServiceOverriddenConfiguration.enableRss()) {
			return false;
		}

		long groupId = ParamUtil.getLong(request, "groupId");

		if ((groupId == 0) ||
			GroupPermissionUtil.contains(
				themeDisplay.getPermissionChecker(), groupId,
				ActionKeys.VIEW)) {

			return true;
		}

		return false;
	}

	@Reference(unbind = "-")
	protected void setBlogsEntryService(BlogsEntryService blogsEntryService) {
		_blogsEntryService = blogsEntryService;
	}

	@Reference(unbind = "-")
	protected void setConfigurationProvider(
		ConfigurationProvider configurationProvider) {

		_configurationProvider = configurationProvider;
	}

	private BlogsEntryService _blogsEntryService;
	private ConfigurationProvider _configurationProvider;

}