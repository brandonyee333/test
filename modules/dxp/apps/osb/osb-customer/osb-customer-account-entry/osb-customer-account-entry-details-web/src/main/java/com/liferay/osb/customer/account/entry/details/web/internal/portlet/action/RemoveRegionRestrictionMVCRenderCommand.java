/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.account.entry.details.web.internal.portlet.action;

import com.liferay.osb.customer.account.entry.details.web.internal.constants.AccountEntryDetailsPortletKeys;
import com.liferay.osb.customer.account.entry.details.web.internal.constants.AccountEntryDetailsWebKeys;
import com.liferay.osb.customer.ticket.model.TicketAttachment;
import com.liferay.osb.customer.ticket.service.TicketAttachmentLocalService;
import com.liferay.osb.customer.ticket.service.permission.TicketAttachmentPermissionChecker;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	property = {
		"javax.portlet.name=" + AccountEntryDetailsPortletKeys.ACCOUNT_ENTRY_DETAILS,
		"mvc.command.name=/remove_region_restriction"
	},
	service = MVCRenderCommand.class
)
public class RemoveRegionRestrictionMVCRenderCommand
	extends BaseMVCRenderCommand {

	@Override
	protected String doRender(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long ticketAttachmentId = ParamUtil.getLong(
			renderRequest, "ticketAttachmentId");

		TicketAttachment ticketAttachment =
			_ticketAttachmentLocalService.getTicketAttachment(
				ticketAttachmentId);

		if (TicketAttachmentPermissionChecker.contains(
				themeDisplay.getPermissionChecker(), ticketAttachment,
				ActionKeys.UPDATE)) {

			renderRequest.setAttribute(
				AccountEntryDetailsWebKeys.TICKET_ATTACHMENT, ticketAttachment);
		}

		return "/account_entry_details/remove_region_restriction.jsp";
	}

	@Reference
	private TicketAttachmentLocalService _ticketAttachmentLocalService;

}