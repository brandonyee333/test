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

package com.liferay.osb.community.doc.project.random.nine.web.internal.portlet.action;

import com.liferay.osb.community.doc.project.constants.DocProjectConstants;
import com.liferay.osb.community.doc.project.model.DocProject;
import com.liferay.osb.community.doc.project.model.DocProjectURLTypeSettings;
import com.liferay.osb.community.doc.project.random.nine.web.internal.constants.DocProjectPortletKeys;
import com.liferay.osb.community.doc.project.service.DocProjectLocalService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.template.Template;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ResourceBundleLoader;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ryan Park
 * @author Haote Chou
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + DocProjectPortletKeys.DOC_PROJECT_RANDOM_NINE,
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

		String allProjectsURL = StringPool.BLANK;

		try {
			long plid = _layoutLocalService.getDefaultPlid(
				themeDisplay.getScopeGroupId(), false,
				DOC_PROJECT_INDEX_PORTLET_ID);

			Layout layout = _layoutLocalService.getLayout(plid);

			allProjectsURL = layout.getFriendlyURL(themeDisplay.getLocale());
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}

		template.put("allProjectsURL", allProjectsURL);

		List<Map<String, Object>> docProjects = null;

		try {
			docProjects = getRandomDocProjectList(renderRequest, themeDisplay);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}

			docProjects = new ArrayList<>(0);
		}

		template.put("docProjects", docProjects);

		Map<String, Object> strings = getStringsMap(
			themeDisplay.getLanguageId());

		template.put("strings", strings);

		return "view";
	}

	protected Map<String, Object> getDocProjectMap(
			DocProject docProject, PortletConfig portletConfig,
			RenderRequest renderRequest, ThemeDisplay themeDisplay)
		throws Exception {

		Map<String, Object> docProjectMap = new HashMap<>(3);

		LiferayPortletURL iconURL = PortletURLFactoryUtil.create(
			renderRequest, portletConfig.getPortletName(),
			themeDisplay.getPlid(), PortletRequest.RESOURCE_PHASE);

		iconURL.setCopyCurrentRenderParameters(false);
		iconURL.setParameter(
			"docProjectId", String.valueOf(docProject.getDocProjectId()));
		iconURL.setResourceID("/serve_doc_project_icon");

		docProjectMap.put("iconURL", iconURL.toString());

		docProjectMap.put("name", docProject.getName());

		String type = docProject.getType();

		docProjectMap.put("siteType", type);

		String url = StringPool.BLANK;

		if (type.equals(DocProjectConstants.TYPE_SITE)) {
			Group group = _groupLocalService.getGroup(docProject.getGroupId());

			url = group.getDisplayURL(themeDisplay, false);
		}
		else if (type.equals(DocProjectConstants.TYPE_URL)) {
			DocProjectURLTypeSettings docProjectURLTypeSettings =
				(DocProjectURLTypeSettings)
					docProject.getDocProjectTypeSettings();

			url = docProjectURLTypeSettings.getURL();
		}

		docProjectMap.put("siteURL", url);

		return docProjectMap;
	}

	protected Map<Integer, Long> getFixedDocProjectPositionMap(
			RenderRequest renderRequest)
		throws Exception {

		PortletPreferences portletPreferences = renderRequest.getPreferences();

		Map<Integer, Long> randomNinePositionMap = new HashMap<>(9);

		for (int i = 0; i < 9; i++) {
			long docProjectId = GetterUtil.getLong(
				portletPreferences.getValue("randomNinePosition" + i, null));

			if (docProjectId != 0) {
				randomNinePositionMap.put(i, docProjectId);
			}
		}

		return randomNinePositionMap;
	}

	protected List<Map<String, Object>> getRandomDocProjectList(
			RenderRequest renderRequest, ThemeDisplay themeDisplay)
		throws Exception {

		List<Map<String, Object>> docProjectList = new ArrayList<>(9);

		List<DocProject> docProjects = _docProjectLocalService.getDocProjects(
			false, DocProjectConstants.STATUS_LIVE, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);

		docProjects = ListUtil.copy(docProjects);

		Collections.shuffle(docProjects);

		PortletConfig portletConfig = (PortletConfig)renderRequest.getAttribute(
			JavaConstants.JAVAX_PORTLET_CONFIG);

		Map<Integer, Long> randomNinePositionMap =
			getFixedDocProjectPositionMap(renderRequest);

		int docProjectsSize = docProjects.size();

		for (int i = 0; (i < 9) && (i < docProjectsSize); i++) {
			DocProject docProject = null;

			if (randomNinePositionMap.get(i) == null) {
				for (DocProject docProjectHolder : docProjects) {
					if (!randomNinePositionMap.containsValue(
							docProjectHolder.getDocProjectId())) {

						docProject = docProjectHolder;

						break;
					}
				}
			}
			else {
				docProject = _docProjectLocalService.getDocProject(
					randomNinePositionMap.get(i));
			}

			docProjectList.add(
				getDocProjectMap(
					docProject, portletConfig, renderRequest, themeDisplay));

			docProjects.remove(docProject);
		}

		return docProjectList;
	}

	protected Map<String, Object> getStringsMap(String languageId) {
		Map<String, Object> strings = new HashMap<>();

		ResourceBundle resourceBundle =
			_resourceBundleLoader.loadResourceBundle(
				LanguageUtil.getLocale(languageId));

		for (String key : resourceBundle.keySet()) {
			strings.put(key, LanguageUtil.get(resourceBundle, key));
		}

		return strings;
	}

	protected static final String DOC_PROJECT_INDEX_PORTLET_ID =
		"com_liferay_osb_community_doc_project_index_web_" +
			"DocProjectIndexPortlet";

	private static final Log _log = LogFactoryUtil.getLog(
		ViewMVCRenderCommand.class);

	@Reference
	private DocProjectLocalService _docProjectLocalService;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private LayoutLocalService _layoutLocalService;

	@Reference(
		target = "(bundle.symbolic.name=com.liferay.osb.community.doc.project.random.nine.web)"
	)
	private ResourceBundleLoader _resourceBundleLoader;

}