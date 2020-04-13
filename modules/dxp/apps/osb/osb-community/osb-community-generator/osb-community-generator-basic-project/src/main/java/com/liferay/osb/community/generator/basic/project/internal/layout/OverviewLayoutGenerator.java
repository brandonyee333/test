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

package com.liferay.osb.community.generator.basic.project.internal.layout;

import com.liferay.osb.community.doc.project.model.DocProject;
import com.liferay.osb.community.doc.project.service.DocProjectLocalService;
import com.liferay.osb.community.generator.basic.project.internal.layout.constants.BasicProjectSitePortletConstants;
import com.liferay.osb.community.generator.basic.project.site.constants.BasicProjectSiteConstants;
import com.liferay.osb.community.generator.layout.BaseLayoutGenerator;
import com.liferay.osb.community.generator.layout.LayoutGenerator;
import com.liferay.osb.community.generator.layout.LayoutVersion;
import com.liferay.osb.community.generator.layout.helper.LayoutWebContentHelper;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.model.LayoutTypePortlet;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.StringPool;

import javax.portlet.PortletPreferences;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Yury Butrymovich
 * @author Ryan Park
 * @author Haote Chou
 */
@Component(
	immediate = true,
	property = {
		"osb.community.layout.description=",
		"osb.community.layout.friendly.url=/overview",
		"osb.community.layout.hidden:Boolean=true",
		"osb.community.layout.name=Overview",
		"osb.community.layout.title=Overview",
		"osb.community.layout.type=" + LayoutConstants.TYPE_PORTLET,
		"osb.community.site.generator.key=" + BasicProjectSiteConstants.BASIC_PROJECT_SITE_KEY
	},
	service = LayoutGenerator.class
)
public class OverviewLayoutGenerator extends BaseLayoutGenerator {

	public static final int LAYOUT_VERSION = 1;

	@Override
	public int getLayoutVersion() {
		return LAYOUT_VERSION;
	}

	@Override
	protected void doGenerate(long plid) throws Exception {
		Layout layout = _layoutLocalService.getLayout(plid);

		layout.setTypeSettings(StringPool.BLANK);

		LayoutTypePortlet layoutTypePortlet =
			(LayoutTypePortlet)layout.getLayoutType();

		User user = _userLocalService.getDefaultUser(layout.getCompanyId());

		layoutTypePortlet.setLayoutTemplateId(
			user.getUserId(), "1_column", false);

		if (!layoutTypePortlet.hasPortletId(
				BasicProjectSitePortletConstants.PROJECT_HEADER_PORTLET_ID)) {

			layoutTypePortlet.addPortletId(
				user.getUserId(),
				BasicProjectSitePortletConstants.PROJECT_HEADER_PORTLET_ID,
				"column-1", 1, false);
		}

		if (!layoutTypePortlet.hasPortletId(
				BasicProjectSitePortletConstants.
					OPEN_SOURCE_PROJECT_OVERVIEW_CONTENT_PORTLET_ID)) {

			layoutTypePortlet.addPortletId(
				user.getUserId(),
				BasicProjectSitePortletConstants.
					OPEN_SOURCE_PROJECT_OVERVIEW_CONTENT_PORTLET_ID,
				"column-1", 2, false);
		}

		PortletPreferences portletPreferences =
			PortletPreferencesFactoryUtil.getStrictPortletSetup(
				layout,
				BasicProjectSitePortletConstants.PROJECT_HEADER_PORTLET_ID);

		DocProject docProject = _docProjectLocalService.getDocProjectByGroupId(
			layout.getGroupId());

		portletPreferences.setValue(
			"docProjectId", String.valueOf(docProject.getDocProjectId()));

		portletPreferences.store();

		_layoutLocalService.updateLayout(
			layout.getGroupId(), layout.isPrivateLayout(), layout.getLayoutId(),
			layout.getTypeSettings());

		_layoutWebContentHelper.setWebContent(
			layout,
			BasicProjectSitePortletConstants.
				OPEN_SOURCE_PROJECT_OVERVIEW_CONTENT_PORTLET_ID,
			"OPEN_SOURCE_PROJECT_OVERVIEW", "Open Source Project Overview",
			"/web_content/open_source_project_overview",
			bundleContext.getBundle());
	}

	@Reference
	private void setLayoutVersion(LayoutVersion layoutVersion) {
		this.layoutVersion = layoutVersion;
	}

	@Reference
	private DocProjectLocalService _docProjectLocalService;

	@Reference
	private LayoutLocalService _layoutLocalService;

	@Reference
	private LayoutWebContentHelper _layoutWebContentHelper;

	@Reference
	private UserLocalService _userLocalService;

}