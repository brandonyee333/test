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

package com.liferay.osb.customer.support.project.details.web.internal.portlet.action;

import com.liferay.osb.customer.support.project.details.web.internal.constants.SupportProjectDetailsConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.service.OrganizationLocalService;
import com.liferay.portal.kernel.servlet.SessionErrors;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
public abstract class BaseMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		try {
			return doRender(renderRequest, renderResponse);
		}
		catch (Exception e) {
			SessionErrors.add(renderRequest, e.getClass());

			return "/support_project_details/error.jsp";
		}
	}

	protected abstract String doRender(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws Exception;

	protected boolean isLiferayIncOrg(long userId) throws PortalException {
		if (organizationLocalService.hasUserOrganization(
				userId,
				SupportProjectDetailsConstants.ORGANIZATION_LIFERAY_INC_ID)) {

			return true;
		}
		else {
			return false;
		}
	}

	@Reference(unbind = "-")
	protected void setOrganizationLocalService(
		OrganizationLocalService organizationLocalService) {

		this.organizationLocalService = organizationLocalService;
	}

	protected OrganizationLocalService organizationLocalService;

}