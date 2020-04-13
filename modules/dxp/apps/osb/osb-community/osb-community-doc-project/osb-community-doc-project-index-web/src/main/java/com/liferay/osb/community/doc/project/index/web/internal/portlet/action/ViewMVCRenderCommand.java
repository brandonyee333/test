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

package com.liferay.osb.community.doc.project.index.web.internal.portlet.action;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.osb.community.doc.project.constants.DocProjectConstants;
import com.liferay.osb.community.doc.project.index.web.internal.constants.DocProjectPortletKeys;
import com.liferay.osb.community.doc.project.model.DocProject;
import com.liferay.osb.community.doc.project.model.DocProjectURLTypeSettings;
import com.liferay.osb.community.doc.project.service.DocProjectLocalService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.template.Template;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.ResourceBundleLoader;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Enoch Chu
 * @author Haote Chou
 * @author Yury Butrymovich
 * @author Ryan Park
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + DocProjectPortletKeys.DOC_PROJECT_INDEX,
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

		String predefinedFilterTag = ParamUtil.getString(
			renderRequest, "predefinedFilterTag");

		Template template = (Template)renderRequest.getAttribute(
			WebKeys.TEMPLATE);

		List<Map<String, Object>> docProjectsList = null;

		try {
			docProjectsList = getDocProjectList(
				renderRequest, themeDisplay, predefinedFilterTag);
		}
		catch (Exception e) {
			_log.error(e, e);

			docProjectsList = new ArrayList<>(0);
		}

		template.put("docProjects", docProjectsList);

		template.put(
			"predefinedFilterTags",
			getPredefinedFilterTagsList(renderRequest, renderResponse));

		template.put("predefinedFilterTag", predefinedFilterTag);

		PortletURL viewUrl = renderResponse.createRenderURL();

		template.put("viewURL", viewUrl.toString());

		Map<String, Object> strings = getStringsMap(
			renderRequest, themeDisplay.getLanguageId(),
			docProjectsList.size());

		template.put("strings", strings);

		return "view";
	}

	protected List<Map<String, Object>> getDocProjectList(
			RenderRequest renderRequest, ThemeDisplay themeDisplay,
			String predefinedFilterTag)
		throws Exception {

		List<Map<String, Object>> docProjectList = new LinkedList<>();

		List<DocProject> docProjects = _docProjectLocalService.getDocProjects(
			false, DocProjectConstants.STATUS_LIVE, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);

		PortletConfig portletConfig = (PortletConfig)renderRequest.getAttribute(
			JavaConstants.JAVAX_PORTLET_CONFIG);

		for (DocProject docProject : docProjects) {
			Map<String, Object> docProjectMap = new HashMap<>(5);

			AssetEntry assetEntry = _assetEntryLocalService.getEntry(
				DocProject.class.getName(), docProject.getDocProjectId());

			String[] tagNames = assetEntry.getTagNames();

			List<String> tagNamesList = Arrays.asList(tagNames);

			if (tagNamesList.size() > _TAG_NAMES_COUNT) {
				tagNamesList = tagNamesList.subList(0, _TAG_NAMES_COUNT);
			}

			if (Validator.isNotNull(predefinedFilterTag) &&
				!tagNamesList.contains(predefinedFilterTag)) {

				continue;
			}

			docProjectMap.put("assetTagNames", tagNamesList);

			docProjectMap.put("description", docProject.getDescription());

			LiferayPortletURL iconURL = PortletURLFactoryUtil.create(
				renderRequest, portletConfig.getPortletName(),
				themeDisplay.getPlid(), PortletRequest.RESOURCE_PHASE);

			iconURL.setCopyCurrentRenderParameters(false);
			iconURL.setParameter(
				"docProjectId", String.valueOf(docProject.getDocProjectId()));
			iconURL.setResourceID("/serve_doc_project_icon");

			docProjectMap.put("iconURL", iconURL.toString());

			docProjectMap.put("name", docProject.getName());

			String url = StringPool.BLANK;

			String type = docProject.getType();

			if (type.equals(DocProjectConstants.TYPE_SITE)) {
				Group group = _groupLocalService.getGroup(
					docProject.getGroupId());

				url = group.getDisplayURL(themeDisplay, false);
			}
			else if (type.equals(DocProjectConstants.TYPE_URL)) {
				DocProjectURLTypeSettings docProjectURLTypeSettings =
					(DocProjectURLTypeSettings)
						docProject.getDocProjectTypeSettings();

				url = docProjectURLTypeSettings.getURL();
			}

			docProjectMap.put("siteType", type);
			docProjectMap.put("siteURL", url);

			docProjectList.add(docProjectMap);
		}

		return docProjectList;
	}

	protected List<Map<String, Object>> getPredefinedFilterTagsList(
		RenderRequest renderRequest, RenderResponse renderResponse) {

		List<Map<String, Object>> predefinedFilterTagsList = new LinkedList<>();

		PortletPreferences portletPreferences = renderRequest.getPreferences();

		String predefinedFilterTagsString = portletPreferences.getValue(
			"predefinedFilterTags", StringPool.BLANK);

		String[] predefinedFilterTags = StringUtil.split(
			predefinedFilterTagsString);

		PortletURL portletURL = renderResponse.createRenderURL();

		for (String predefinedFilterTag : predefinedFilterTags) {
			Map<String, Object> predefinedFilterTagMap = new HashMap<>();

			predefinedFilterTagMap.put("name", predefinedFilterTag);

			portletURL.setParameter("predefinedFilterTag", predefinedFilterTag);

			predefinedFilterTagMap.put("url", portletURL.toString());

			predefinedFilterTagsList.add(predefinedFilterTagMap);
		}

		return predefinedFilterTagsList;
	}

	protected Map<String, Object> getStringsMap(
		RenderRequest renderRequest, String languageId, int docProjectCount) {

		Map<String, Object> strings = new HashMap<>();

		HttpServletRequest httpServletRequest = _portal.getHttpServletRequest(
			renderRequest);

		ResourceBundle resourceBundle =
			_resourceBundleLoader.loadResourceBundle(
				LanguageUtil.getLocale(languageId));

		strings.put("all", LanguageUtil.get(httpServletRequest, "all"));
		strings.put("filter", LanguageUtil.get(httpServletRequest, "filter"));
		strings.put(
			"projects", LanguageUtil.get(httpServletRequest, "projects"));

		if (docProjectCount == 1) {
			strings.put(
				"x-projects", LanguageUtil.get(resourceBundle, "1-project"));
		}
		else {
			strings.put(
				"x-projects",
				LanguageUtil.format(
					resourceBundle, "x-projects", docProjectCount));
		}

		return strings;
	}

	private static final int _TAG_NAMES_COUNT = 3;

	private static final Log _log = LogFactoryUtil.getLog(
		ViewMVCRenderCommand.class);

	@Reference
	private AssetEntryLocalService _assetEntryLocalService;

	@Reference
	private DocProjectLocalService _docProjectLocalService;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private Portal _portal;

	@Reference(
		target = "(bundle.symbolic.name=com.liferay.osb.community.doc.project.index.web)"
	)
	private ResourceBundleLoader _resourceBundleLoader;

}