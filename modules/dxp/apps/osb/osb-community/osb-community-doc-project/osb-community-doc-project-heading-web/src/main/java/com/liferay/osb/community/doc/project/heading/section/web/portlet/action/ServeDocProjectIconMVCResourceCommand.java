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

import com.liferay.osb.community.doc.project.heading.section.web.constants.DocProjectHeadingPortletKeys;
import com.liferay.osb.community.doc.project.model.DocProject;
import com.liferay.osb.community.doc.project.service.DocProjectLocalService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Yury Butrymovich
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + DocProjectHeadingPortletKeys.DOC_PROJECT_HEADING,
		"mvc.command.name=/serve_doc_project_page_icon"
	},
	service = MVCResourceCommand.class
)
public class ServeDocProjectIconMVCResourceCommand
	implements MVCResourceCommand {

	@Override
	public boolean serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws PortletException {

		long docProjectId = ParamUtil.getLong(resourceRequest, "docProjectId");

		try {
			DocProject docProject = _docProjectLocalService.getDocProject(
				docProjectId);

			String contentType = MimeTypesUtil.getContentType(
				docProject.getIconFileName());

			PortletResponseUtil.sendFile(
				resourceRequest, resourceResponse, docProject.getIconFileName(),
				docProject.getIconInputStream(), contentType);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Unable to serve icon for doc project " + docProjectId);
			}

			return true;
		}

		return false;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ServeDocProjectIconMVCResourceCommand.class);

	@Reference
	private DocProjectLocalService _docProjectLocalService;

}