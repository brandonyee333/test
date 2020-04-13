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

package com.liferay.osb.community.github.top.contributors.web.internal.portlet.action;

import com.liferay.osb.community.doc.project.constants.DocProjectConstants;
import com.liferay.osb.community.doc.project.model.DocProject;
import com.liferay.osb.community.doc.project.model.DocProjectSiteTypeSettings;
import com.liferay.osb.community.doc.project.model.DocProjectTypeSettings;
import com.liferay.osb.community.doc.project.service.DocProjectLocalService;
import com.liferay.osb.community.doc.project.util.DocProjectTypeSettingsFactoryUtil;
import com.liferay.osb.community.github.model.GitHubContributor;
import com.liferay.osb.community.github.service.GitHubContributorLocalService;
import com.liferay.osb.community.github.top.contributors.web.internal.constants.GitHubPortletKeys;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.template.Template;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ResourceBundleLoader;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Haote Chou
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + GitHubPortletKeys.GITHUB_TOP_CONTRIBUTORS,
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

		List<Map<String, Object>> gitHubContributors = null;

		try {
			gitHubContributors = getGitHubContributors(
				renderRequest, themeDisplay);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}

			gitHubContributors = new ArrayList<>(0);
		}

		template.put("gitHubContributors", gitHubContributors);

		Map<String, Object> strings = getStringsMap(
			themeDisplay.getLanguageId());

		template.put("strings", strings);

		return "view";
	}

	protected List<Map<String, Object>> getGitHubContributors(
			RenderRequest renderRequest, ThemeDisplay themeDisplay)
		throws Exception {

		List<Map<String, Object>> gitHubContributors = new ArrayList<>();

		PortletPreferences portletPreferences = renderRequest.getPreferences();

		long docProjectId = GetterUtil.getLong(
			portletPreferences.getValue("docProjectId", null));

		DocProject docProject = _docProjectLocalService.getDocProject(
			docProjectId);

		DocProjectTypeSettings docProjectTypeSettings =
			DocProjectTypeSettingsFactoryUtil.create(docProject);

		String gitHubRepositoryName = StringPool.BLANK;
		String gitHubRepositoryOwner = StringPool.BLANK;

		String type = docProject.getType();

		if (type.equals(DocProjectConstants.TYPE_SITE)) {
			DocProjectSiteTypeSettings docProjectSiteTypeSettings =
				(DocProjectSiteTypeSettings)docProjectTypeSettings;

			gitHubRepositoryName =
				docProjectSiteTypeSettings.getGitHubRepositoryName();
			gitHubRepositoryOwner =
				docProjectSiteTypeSettings.getGitHubRepositoryOwner();
		}

		int contributorsCount = GetterUtil.getInteger(
			portletPreferences.getValue("contributorsCount", null), 5);

		List<GitHubContributor> topGitHubContributors =
			_gitHubContributorLocalService.getTopGitHubContributors(
				themeDisplay.getUserId(), gitHubRepositoryOwner,
				gitHubRepositoryName, contributorsCount);

		for (GitHubContributor gitHubContributor : topGitHubContributors) {
			Map<String, Object> gitHubContributorMap = new HashMap<>();

			gitHubContributorMap.put(
				"avatarURL", gitHubContributor.getAvatarURL());
			gitHubContributorMap.put("name", gitHubContributor.getName());
			gitHubContributorMap.put(
				"profileURL", gitHubContributor.getProfileURL());

			gitHubContributors.add(gitHubContributorMap);
		}

		return gitHubContributors;
	}

	protected Map<String, Object> getStringsMap(String languageId) {
		Map<String, Object> stringsMap = new HashMap<>();

		ResourceBundle resourceBundle =
			_resourceBundleLoader.loadResourceBundle(
				LanguageUtil.getLocale(languageId));

		for (String key : resourceBundle.keySet()) {
			stringsMap.put(key, LanguageUtil.get(resourceBundle, key));
		}

		return stringsMap;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ViewMVCRenderCommand.class);

	@Reference
	private DocProjectLocalService _docProjectLocalService;

	@Reference
	private GitHubContributorLocalService _gitHubContributorLocalService;

	@Reference(
		target = "(bundle.symbolic.name=com.liferay.osb.community.github.top.contributors.web)"
	)
	private ResourceBundleLoader _resourceBundleLoader;

}