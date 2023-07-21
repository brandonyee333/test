/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.account.entry.details.web.internal.portlet.action;

import com.liferay.osb.customer.constants.OSBCustomerConstants;
import com.liferay.osb.customer.zendesk.exception.ZendeskTicketClosedException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.service.OrganizationLocalService;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.Portal;

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
			if (!(e instanceof ZendeskTicketClosedException)) {
				_log.error(e, e);
			}

			SessionErrors.add(renderRequest, e.getClass());

			SessionMessages.add(
				renderRequest,
				portal.getPortletId(renderRequest) +
					SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);

			return "/account_entry_details/error.jsp";
		}
	}

	protected abstract String doRender(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws Exception;

	protected boolean isLiferayContractorOrg(long userId)
		throws PortalException {

		if (organizationLocalService.hasUserOrganization(
				userId,
				OSBCustomerConstants.ORGANIZATION_LIFERAY_CONTRACTOR_ID)) {

			return true;
		}

		return false;
	}

	protected boolean isLiferayIncOrg(long userId) throws PortalException {
		if (organizationLocalService.hasUserOrganization(
				userId, OSBCustomerConstants.ORGANIZATION_LIFERAY_INC_ID)) {

			return true;
		}

		return false;
	}

	@Reference(unbind = "-")
	protected void setOrganizationLocalService(
		OrganizationLocalService organizationLocalService) {

		this.organizationLocalService = organizationLocalService;
	}

	protected OrganizationLocalService organizationLocalService;

	@Reference
	protected Portal portal;

	private static final Log _log = LogFactoryUtil.getLog(
		BaseMVCRenderCommand.class);

}