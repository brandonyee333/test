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

package com.liferay.osb.community.doc.project.admin.web.internal.portlet.action;

import com.liferay.osb.community.doc.project.admin.web.internal.constants.DocProjectPortletKeys;
import com.liferay.osb.community.doc.project.constants.DocProjectConstants;
import com.liferay.osb.community.doc.project.model.DocProject;
import com.liferay.osb.community.doc.project.model.DocProjectSiteTypeSettings;
import com.liferay.osb.community.doc.project.model.DocProjectTypeSettings;
import com.liferay.osb.community.doc.project.model.DocProjectURLTypeSettings;
import com.liferay.osb.community.doc.project.service.DocProjectService;
import com.liferay.osb.community.doc.project.util.DocProjectTypeSettingsFactoryUtil;
import com.liferay.osb.community.generator.basic.project.site.constants.BasicProjectSiteConstants;
import com.liferay.osb.community.generator.site.SiteGenerator;
import com.liferay.osb.community.generator.site.SiteGeneratorRegistry;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;

import java.io.File;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ryan Park
 * @author Haote Chou
 */
@Component(
	property = {
		"javax.portlet.name=" + DocProjectPortletKeys.DOC_PROJECT_ADMIN,
		"mvc.command.name=/edit_doc_project"
	},
	service = MVCActionCommand.class
)
public class EditDocProjectMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		UploadPortletRequest uploadPortletRequest =
			_portal.getUploadPortletRequest(actionRequest);

		long docProjectId = ParamUtil.getLong(
			uploadPortletRequest, "docProjectId");

		String name = ParamUtil.getString(uploadPortletRequest, "name");
		String description = ParamUtil.getString(
			uploadPortletRequest, "description");
		String iconFileName = uploadPortletRequest.getFileName("icon");
		File iconFile = uploadPortletRequest.getFile("icon");
		boolean unlisted = ParamUtil.getBoolean(
			uploadPortletRequest, "unlisted");
		String type = ParamUtil.getString(uploadPortletRequest, "type");
		int status = ParamUtil.getInteger(uploadPortletRequest, "status");

		DocProjectTypeSettings docProjectTypeSettings =
			DocProjectTypeSettingsFactoryUtil.create(type);

		if (type.equals(DocProjectConstants.TYPE_SITE)) {
			String gitHubRepositoryName = ParamUtil.getString(
				uploadPortletRequest, "gitHubRepositoryName");
			String gitHubRepositoryOwner = ParamUtil.getString(
				uploadPortletRequest, "gitHubRepositoryOwner");
			String headerGradientEndColor = ParamUtil.getString(
				uploadPortletRequest, "headerGradientEndColor");
			String headerGradientStartColor = ParamUtil.getString(
				uploadPortletRequest, "headerGradientStartColor");

			DocProjectSiteTypeSettings docProjectSiteTypeSettings =
				(DocProjectSiteTypeSettings)docProjectTypeSettings;

			docProjectSiteTypeSettings.setGitHubRepositoryName(
				gitHubRepositoryName);
			docProjectSiteTypeSettings.setGitHubRepositoryOwner(
				gitHubRepositoryOwner);
			docProjectSiteTypeSettings.setHeaderGradientEndColor(
				headerGradientEndColor);
			docProjectSiteTypeSettings.setHeaderGradientStartColor(
				headerGradientStartColor);
		}
		else if (type.equals(DocProjectConstants.TYPE_URL)) {
			String url = ParamUtil.getString(uploadPortletRequest, "url");

			DocProjectURLTypeSettings docProjectURLTypeSettings =
				(DocProjectURLTypeSettings)docProjectTypeSettings;

			docProjectURLTypeSettings.setURL(url);
		}

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			actionRequest);

		DocProject docProject = null;

		if (docProjectId > 0) {
			docProject = _docProjectService.updateDocProject(
				docProjectId, name, description, iconFileName, iconFile,
				unlisted, type, docProjectTypeSettings.toString(), status,
				serviceContext);
		}
		else {
			docProject = _docProjectService.addDocProject(
				name, description, iconFileName, iconFile, unlisted, type,
				docProjectTypeSettings.toString(), status, serviceContext);

			SiteGenerator siteGenerator =
				_siteGeneratorRegistry.getSiteGenerator(
					BasicProjectSiteConstants.BASIC_PROJECT_SITE_KEY);

			siteGenerator.generate(docProject.getGroupId());
		}
	}

	@Reference
	private DocProjectService _docProjectService;

	@Reference
	private Portal _portal;

	@Reference
	private SiteGeneratorRegistry _siteGeneratorRegistry;

}