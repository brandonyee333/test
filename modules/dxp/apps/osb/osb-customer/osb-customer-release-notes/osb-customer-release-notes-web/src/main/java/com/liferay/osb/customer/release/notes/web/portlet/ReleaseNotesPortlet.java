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

package com.liferay.osb.customer.release.notes.web.portlet;

import com.liferay.osb.customer.release.notes.exception.NoSuchReleaseNotesException;
import com.liferay.osb.customer.release.notes.jira.exception.DuplicateJIRAIssueKeysException;
import com.liferay.osb.customer.release.notes.jira.exception.RequiredJIRAIssueKeysException;
import com.liferay.osb.customer.release.notes.jira.exception.RequiredNameException;
import com.liferay.osb.customer.release.notes.jira.model.JIRAComponent;
import com.liferay.osb.customer.release.notes.jira.model.JIRAIssue;
import com.liferay.osb.customer.release.notes.jira.model.JIRAProject;
import com.liferay.osb.customer.release.notes.jira.model.JIRAProjectVersion;
import com.liferay.osb.customer.release.notes.jira.service.JIRAIssueLocalService;
import com.liferay.osb.customer.release.notes.jira.service.JIRAProjectLocalService;
import com.liferay.osb.customer.release.notes.jira.service.JIRAProjectVersionLocalService;
import com.liferay.osb.customer.release.notes.model.ReleaseNotes;
import com.liferay.osb.customer.release.notes.service.ReleaseNotesLocalService;
import com.liferay.osb.customer.release.notes.util.ReleaseNotesCacheUtil;
import com.liferay.osb.customer.release.notes.web.internal.configuration.ReleaseNotesConfigurationValues;
import com.liferay.osb.customer.release.notes.web.internal.constants.ReleaseNotesPortletKeys;
import com.liferay.osb.customer.release.notes.web.internal.util.DataURIUtil;
import com.liferay.osb.customer.release.notes.web.internal.util.JIRAConstants;
import com.liferay.osb.customer.release.notes.web.internal.util.ReleaseNotesUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.permission.PortletPermissionUtil;
import com.liferay.portal.kernel.template.StringTemplateResource;
import com.liferay.portal.kernel.template.Template;
import com.liferay.portal.kernel.template.TemplateConstants;
import com.liferay.portal.kernel.template.TemplateManagerUtil;
import com.liferay.portal.kernel.template.TemplateResource;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.StringUtil_IW;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.Writer;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletContext;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Samuel Kong
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.add-default-resource=true",
		"com.liferay.portlet.css-class-wrapper=release-notes-portlet",
		"com.liferay.portlet.display-category=category.osb",
		"com.liferay.portlet.icon=/icon.png",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=OSB Release Notes",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.init-param.copy-request-parameters=true",
		"javax.portlet.init-param.template-path=/META-INF/resources/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + ReleaseNotesPortletKeys.RELEASE_NOTES,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=administrator,guest,power-user,user",
		"javax.portlet.supports.mime-type=text/html"
	},
	service = Portlet.class
)
public class ReleaseNotesPortlet extends MVCPortlet {

	public void deleteReleaseNotes(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long releaseNotesId = ParamUtil.getLong(
			actionRequest, "releaseNotesId");

		_releaseNotesLocalService.deleteReleaseNotes(releaseNotesId);
	}

	@Override
	public void serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException {

		String jiraLabel = ParamUtil.getString(resourceRequest, "jiraLabel");
		Long jiraProjectVersionId = ParamUtil.getLong(
			resourceRequest, "jiraProjectVersionId");
		String uuid = ParamUtil.getString(resourceRequest, "uuid");

		String responseString = StringPool.BLANK;

		try {
			if (Validator.isNotNull(jiraLabel)) {
				responseString = getReleaseNotesByJIRALabel(
					resourceRequest, jiraLabel);
			}
			else if (Validator.isNotNull(jiraProjectVersionId)) {
				responseString = getReleaseNotesByJIRAProjectVersion(
					resourceRequest, jiraProjectVersionId);
			}
			else if (Validator.isNotNull(uuid)) {
				responseString = getReleaseNotesByUuid(resourceRequest, uuid);
			}
		}
		catch (Exception e) {
			responseString = e.getMessage();
		}

		resourceResponse.setContentType(ContentTypes.TEXT_HTML);

		OutputStream outputStream = resourceResponse.getPortletOutputStream();

		outputStream.write(responseString.getBytes());
	}

	public void updateReleaseNotes(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long releaseNotesId = ParamUtil.getLong(
			actionRequest, "releaseNotesId");

		String name = ParamUtil.getString(actionRequest, "name");
		String jiraIssueKeys = ParamUtil.getString(
			actionRequest, "jiraIssueKeys");

		if (releaseNotesId > 0) {
			_releaseNotesLocalService.updateReleaseNotes(
				releaseNotesId, name, jiraIssueKeys);
		}
		else {
			ReleaseNotes releaseNotes =
				_releaseNotesLocalService.addReleaseNotes(
					themeDisplay.getUserId(), name, jiraIssueKeys);

			releaseNotesId = releaseNotes.getReleaseNotesId();
		}

		String redirect = getRedirect(actionRequest, actionResponse);

		PortletURL redirectURL = PortletURLFactoryUtil.create(
			actionRequest, ReleaseNotesPortletKeys.RELEASE_NOTES,
			themeDisplay.getPlid(), PortletRequest.RENDER_PHASE);

		redirectURL.setParameter("mvcPath", "/edit_release_notes.jsp");
		redirectURL.setParameter(
			"releaseNotesId", String.valueOf(releaseNotesId));
		redirectURL.setParameter("redirect", redirect);

		actionRequest.setAttribute(WebKeys.REDIRECT, redirectURL.toString());

		sendRedirect(actionRequest, actionResponse);
	}

	protected String encodeToDataURI(String fileName) {
		return DataURIUtil.encode(
			getInputStream(fileName), FileUtil.getShortFileName(fileName));
	}

	protected String getCachedReleaseNotes(
		String cacheFileName, boolean clearCache) {

		File cacheFile = new File(cacheFileName);

		long cacheFileExpirationDate = cacheFile.lastModified() + Time.DAY;

		if (!clearCache &&
			(cacheFileExpirationDate > System.currentTimeMillis())) {

			try {
				return FileUtil.read(cacheFileName);
			}
			catch (IOException ioe) {
			}
		}

		return null;
	}

	protected InputStream getInputStream(String path) {
		PortletContext portletContext = getPortletContext();

		return portletContext.getResourceAsStream(path);
	}

	protected String getReleaseNotesByJIRALabel(
			ResourceRequest resourceRequest, String jiraLabel)
		throws Exception {

		boolean clearCache = ParamUtil.getBoolean(
			resourceRequest, "clearCache");

		String cacheFileName = ReleaseNotesCacheUtil.getCacheFilePath(
			jiraLabel, ReleaseNotesCacheUtil.CACHE_DIR_LABEL);

		String cachedReleaseNotes = getCachedReleaseNotes(
			cacheFileName, clearCache);

		if (Validator.isNotNull(cachedReleaseNotes)) {
			return cachedReleaseNotes;
		}

		String jiraProjectKey = ParamUtil.getString(
			resourceRequest, "jiraProjectKey", "LPE");

		if (!ArrayUtil.contains(
				ReleaseNotesConfigurationValues.JIRA_PROJECT_KEYS_ALLOWED,
				jiraProjectKey)) {

			return "The project is restricted.";
		}

		List<JIRAIssue> jiraIssues =
			_jiraIssueLocalService.getJIRALabelJIRAIssues(
				jiraLabel, jiraProjectKey);

		if (jiraIssues.isEmpty()) {
			return "Invalid JIRA label selected.";
		}

		Map<JIRAComponent, Set<JIRAIssue>> jiraComponentMap =
			ReleaseNotesUtil.sortJIRAIssues(jiraIssues);

		String releaseNotesString = mergeTemplate(
			jiraIssues, jiraComponentMap, jiraLabel);

		FileUtil.write(cacheFileName, releaseNotesString);

		return releaseNotesString;
	}

	protected String getReleaseNotesByJIRAProjectVersion(
			ResourceRequest resourceRequest, long jiraProjectVersionId)
		throws Exception {

		boolean clearCache = ParamUtil.getBoolean(
			resourceRequest, "clearCache");

		String cacheFileName = ReleaseNotesCacheUtil.getCacheFilePath(
			String.valueOf(jiraProjectVersionId),
			ReleaseNotesCacheUtil.CACHE_DIR_PROJECT_VERSION);

		String cachedReleaseNotes = getCachedReleaseNotes(
			cacheFileName, clearCache);

		if (Validator.isNotNull(cachedReleaseNotes)) {
			return cachedReleaseNotes;
		}

		JIRAProjectVersion jiraProjectVersion =
			_jiraProjectVersionLocalService.getJIRAProjectVersion(
				jiraProjectVersionId);

		JIRAProject jiraProject = _jiraProjectLocalService.getJIRAProject(
			jiraProjectVersion.getJiraProjectId());

		if (!ArrayUtil.contains(
				ReleaseNotesConfigurationValues.JIRA_PROJECT_KEYS_ALLOWED,
				jiraProject.getKey())) {

			return "The project is restricted.";
		}

		List<JIRAIssue> jiraIssues =
			_jiraIssueLocalService.getJIRAProjectVersionJIRAIssues(
				jiraProjectVersion.getJiraProjectVersionId());

		if (jiraIssues.isEmpty()) {
			return "Invalid JIRA project version selected.";
		}

		Map<JIRAComponent, Set<JIRAIssue>> jiraComponentMap =
			ReleaseNotesUtil.sortJIRAIssues(jiraIssues);

		String releaseNotesString = mergeTemplate(
			jiraIssues, jiraComponentMap, jiraProjectVersion.getName());

		FileUtil.write(cacheFileName, releaseNotesString);

		return releaseNotesString;
	}

	protected String getReleaseNotesByUuid(
			ResourceRequest resourceRequest, String uuid)
		throws Exception {

		ReleaseNotes releaseNotes = null;

		try {
			releaseNotes = _releaseNotesLocalService.getReleaseNotesByUuid(
				uuid);
		}
		catch (NoSuchReleaseNotesException nsrne) {
			return "Invalid release notes selected.";
		}

		boolean clearCache = ParamUtil.getBoolean(
			resourceRequest, "clearCache");

		String cacheFileName = ReleaseNotesCacheUtil.getCacheFilePath(
			releaseNotes.getUuid(), ReleaseNotesCacheUtil.CACHE_DIR_ISSUE);

		String cachedReleaseNotes = getCachedReleaseNotes(
			cacheFileName, clearCache);

		if (Validator.isNotNull(cachedReleaseNotes)) {
			return cachedReleaseNotes;
		}

		String[] jiraIssueKeys = ReleaseNotesUtil.filterJIRAIssueKeys(
			releaseNotes.getJiraIssueKeysArray(), true);

		List<JIRAIssue> jiraIssues = _jiraIssueLocalService.getJIRAIssues(
			jiraIssueKeys);

		if (jiraIssues.isEmpty()) {
			return "Invalid release notes selected.";
		}

		Map<JIRAComponent, Set<JIRAIssue>> jiraComponentMap =
			ReleaseNotesUtil.sortJIRAIssues(jiraIssues);

		String releaseNotesString = mergeTemplate(
			jiraIssues, jiraComponentMap, releaseNotes.getName());

		FileUtil.write(cacheFileName, releaseNotesString);

		return releaseNotesString;
	}

	protected TemplateResource getTemplateResource(String templateId)
		throws Exception {

		InputStream inputStream = getInputStream(templateId);

		try {
			String content = StringUtil.read(inputStream);

			return new StringTemplateResource(templateId, content);
		}
		finally {
			StreamUtil.cleanUp(inputStream);
		}
	}

	protected boolean hasPermission(PortletRequest portletRequest) {
		try {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)portletRequest.getAttribute(
					WebKeys.THEME_DISPLAY);

			String portletId = _portal.getPortletId(portletRequest);

			return PortletPermissionUtil.contains(
				themeDisplay.getPermissionChecker(), themeDisplay.getPlid(),
				portletId, ActionKeys.CONFIGURATION);
		}
		catch (Exception e) {
			return false;
		}
	}

	@Override
	protected boolean isProcessActionRequest(ActionRequest actionRequest) {
		return hasPermission(actionRequest);
	}

	@Override
	protected boolean isProcessRenderRequest(RenderRequest renderRequest) {
		return hasPermission(renderRequest);
	}

	@Override
	protected boolean isSessionErrorException(Throwable cause) {
		if (cause instanceof DuplicateJIRAIssueKeysException ||
			cause instanceof RequiredJIRAIssueKeysException ||
			cause instanceof RequiredNameException) {

			return true;
		}
		else {
			return false;
		}
	}

	protected String mergeFiles(String[] fileNames) {
		if (fileNames.length == 0) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(fileNames.length);

		for (String fileName : fileNames) {
			InputStream inputStream = getInputStream(fileName);

			try {
				String content = StringUtil.read(inputStream);

				if (Validator.isNotNull(content)) {
					sb.append(content);
				}
			}
			catch (IOException ioe) {
				_log.error(ioe.getMessage());
			}
			finally {
				StreamUtil.cleanUp(inputStream);
			}
		}

		return sb.toString();
	}

	protected String mergeTemplate(
			List<JIRAIssue> jiraIssues,
			Map<JIRAComponent, Set<JIRAIssue>> jiraComponentMap, String version)
		throws Exception {

		TemplateResource templateResource = getTemplateResource(
			ReleaseNotesConfigurationValues.TEMPLATE_VELOCITY);

		Template template = TemplateManagerUtil.getTemplate(
			TemplateConstants.LANG_TYPE_VM, templateResource, false);

		prepareTemplate(template, jiraIssues, jiraComponentMap, version);

		Writer writer = new StringWriter();

		template.processTemplate(writer);

		return writer.toString();
	}

	protected void prepareTemplate(
			Template template, List<JIRAIssue> jiraIssues,
			Map<JIRAComponent, Set<JIRAIssue>> jiraComponentMap, String version)
		throws Exception {

		template.put(
			"apiChangeClasses", ReleaseNotesUtil.getAPIChanges(jiraIssues));
		template.put(
			"css", mergeFiles(ReleaseNotesConfigurationValues.TEMPLATE_CSS));
		template.put(
			"bugIcon",
			encodeToDataURI(ReleaseNotesConfigurationValues.IMAGE_ICON_BUG));
		template.put("htmlUtil", HtmlUtil.getHtml());
		template.put(
			"improvementIcon",
			encodeToDataURI(
				ReleaseNotesConfigurationValues.IMAGE_ICON_IMPROVEMENT));
		template.put("jiraComponentMap", jiraComponentMap);
		template.put("jiraIssues", jiraIssues);
		template.put(
			"jiraIssuesWithUpgradeNote",
			ReleaseNotesUtil.getJIRAIssuesWithUpgradeNote(jiraIssues));
		template.put("jiraIssueTypeBug", JIRAConstants.ISSUE_TYPE_BUG);
		template.put(
			"jiraIssueTypeImprovement", JIRAConstants.ISSUE_TYPE_IMPROVEMENT);
		template.put(
			"jiraIssueTypeNewFeature", JIRAConstants.ISSUE_TYPE_NEW_FEATURE);
		template.put(
			"jsBottom",
			mergeFiles(ReleaseNotesConfigurationValues.TEMPLATE_JS_BOTTOM));
		template.put(
			"jsTop",
			mergeFiles(ReleaseNotesConfigurationValues.TEMPLATE_JS_TOP));
		template.put(
			"logo",
			encodeToDataURI(ReleaseNotesConfigurationValues.IMAGE_LOGO));
		template.put(
			"newFeatureIcon",
			encodeToDataURI(
				ReleaseNotesConfigurationValues.IMAGE_ICON_NEW_FEATURE));
		template.put("newline", StringPool.NEW_LINE);
		template.put(
			"otherIcon",
			encodeToDataURI(ReleaseNotesConfigurationValues.IMAGE_ICON_OTHER));

		String[] versionArray = StringUtil.split(version, StringPool.DASH);

		if (versionArray.length > 1) {
			int versionNumber = GetterUtil.getInteger(
				versionArray[versionArray.length - 2]);

			if ((versionNumber - 1) > 0) {
				versionArray[versionArray.length - 2] = String.valueOf(
					versionNumber - 1);

				template.put(
					"previousVersion",
					StringUtil.merge(versionArray, StringPool.DASH));
			}
		}

		template.put("return", StringPool.RETURN);
		template.put("stringUtil", StringUtil_IW.getInstance());
		template.put("version", version);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ReleaseNotesPortlet.class);

	@Reference
	private JIRAIssueLocalService _jiraIssueLocalService;

	@Reference
	private JIRAProjectLocalService _jiraProjectLocalService;

	@Reference
	private JIRAProjectVersionLocalService _jiraProjectVersionLocalService;

	@Reference
	private Portal _portal;

	@Reference
	private ReleaseNotesLocalService _releaseNotesLocalService;

}