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

package com.liferay.osb.community.doc.project.heading.section.web.portlet.action;

import com.liferay.osb.community.doc.project.constants.DocProjectConstants;
import com.liferay.osb.community.doc.project.heading.section.web.constants.DocProjectHeadingPortletKeys;
import com.liferay.osb.community.doc.project.model.DocProject;
import com.liferay.osb.community.doc.project.model.DocProjectSiteTypeSettings;
import com.liferay.osb.community.doc.project.model.DocProjectTypeSettings;
import com.liferay.osb.community.doc.project.service.DocProjectLocalService;
import com.liferay.osb.community.doc.project.util.DocProjectTypeSettingsFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.template.Template;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Yury Butrymovich
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + DocProjectHeadingPortletKeys.DOC_PROJECT_HEADING,
		"mvc.command.name=/", "mvc.command.name=/view"
	},
	service = MVCRenderCommand.class
)
public class ViewMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(
		RenderRequest renderRequest, RenderResponse renderResponse) {

		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		Template template = (Template)renderRequest.getAttribute(
			WebKeys.TEMPLATE);

		PortletPreferences portletPreferences = renderRequest.getPreferences();

		long docProjectId = GetterUtil.getLong(
			portletPreferences.getValue("docProjectId", null));

		DocProject docProject = _docProjectLocalService.fetchDocProject(
			docProjectId);

		String headerGradientEndColor = "#FFFFFF";
		String headerGradientStartColor = "#FFFFFF";

		DocProjectTypeSettings docProjectTypeSettings =
			DocProjectTypeSettingsFactoryUtil.create(docProject);

		String type = docProject.getType();

		if (type.equals(DocProjectConstants.TYPE_SITE)) {
			DocProjectSiteTypeSettings docProjectSiteTypeSettings =
				(DocProjectSiteTypeSettings)docProjectTypeSettings;

			headerGradientEndColor =
				docProjectSiteTypeSettings.getHeaderGradientEndColor();
			headerGradientStartColor =
				docProjectSiteTypeSettings.getHeaderGradientStartColor();
		}

		template.put("headerGradientEndColor", headerGradientEndColor);
		template.put("headerGradientStartColor", headerGradientStartColor);

		PortletConfig portletConfig = (PortletConfig)renderRequest.getAttribute(
			JavaConstants.JAVAX_PORTLET_CONFIG);

		LiferayPortletURL iconURL = PortletURLFactoryUtil.create(
			renderRequest, portletConfig.getPortletName(),
			themeDisplay.getPlid(), PortletRequest.RESOURCE_PHASE);

		iconURL.setCopyCurrentRenderParameters(false);
		iconURL.setParameter("docProjectId", String.valueOf(docProjectId));
		iconURL.setResourceID("/serve_doc_project_page_icon");

		template.put("iconURL", iconURL.toString());

		template.put("layouts", getLayoutsList(themeDisplay));
		template.put("projectName", docProject.getName());

		return "view";
	}

	protected List<Map<String, Object>> getLayoutsList(
		ThemeDisplay themeDisplay) {

		Group siteGroup = themeDisplay.getSiteGroup();

		List<Layout> layouts = _layoutLocalService.getLayouts(
			siteGroup.getGroupId(), false, LayoutConstants.TYPE_PORTLET);

		List<Map<String, Object>> layoutsList = new LinkedList<>();

		String displayURL = siteGroup.getDisplayURL(themeDisplay);

		Locale locale = themeDisplay.getLocale();

		for (Layout layout : layouts) {
			Map<String, Object> layoutMap = new HashMap<>();

			layoutMap.put("name", layout.getHTMLTitle(locale));

			if (themeDisplay.getPlid() == layout.getPlid()) {
				layoutMap.put("selected", true);
			}
			else {
				layoutMap.put("selected", false);
			}

			layoutMap.put("url", displayURL + layout.getFriendlyURL());

			layoutsList.add(layoutMap);
		}

		return layoutsList;
	}

	@Reference
	private DocProjectLocalService _docProjectLocalService;

	@Reference
	private LayoutLocalService _layoutLocalService;

}