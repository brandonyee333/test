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