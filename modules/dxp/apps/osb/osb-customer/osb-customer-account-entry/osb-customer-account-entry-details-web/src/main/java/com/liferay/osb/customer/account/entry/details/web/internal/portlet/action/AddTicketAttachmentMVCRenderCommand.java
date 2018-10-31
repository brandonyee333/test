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
import com.liferay.osb.customer.ticket.constants.TicketActionKeys;
import com.liferay.osb.customer.ticket.service.permission.TicketEntryPermissionChecker;
import com.liferay.osb.customer.zendesk.exception.ZendeskTicketClosedException;
import com.liferay.osb.customer.zendesk.model.ZendeskTicket;
import com.liferay.osb.customer.zendesk.util.ZendeskMapperUtil;
import com.liferay.osb.customer.zendesk.web.service.ZendeskTicketWebService;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.service.AccountEntryLocalServiceUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
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
		"mvc.command.name=/add_ticket_attachment"
	},
	service = MVCRenderCommand.class
)
public class AddTicketAttachmentMVCRenderCommand extends BaseMVCRenderCommand {

	@Override
	protected String doRender(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long zendeskTicketId = ParamUtil.getLong(
			renderRequest, "zendeskTicketId");

		ZendeskTicket zendeskTicket = _zendeskTicketWebService.getZendeskTicket(
			zendeskTicketId);

		if (zendeskTicket.isClosed()) {
			throw new ZendeskTicketClosedException();
		}

		long accountEntryId = _zendeskMapperUtil.getAccountEntryId(
			zendeskTicket.getZendeskOrganizationId());

		TicketEntryPermissionChecker.check(
			themeDisplay.getPermissionChecker(), accountEntryId,
			TicketActionKeys.ADD_TICKET_ATTACHMENT);

		AccountEntry accountEntry =
			AccountEntryLocalServiceUtil.getAccountEntry(accountEntryId);

		renderRequest.setAttribute(
			AccountEntryDetailsWebKeys.ACCOUNT_ENTRY, accountEntry);

		return "/account_entry_details/add_ticket_attachment.jsp";
	}

	@Reference
	private ZendeskMapperUtil _zendeskMapperUtil;

	@Reference
	private ZendeskTicketWebService _zendeskTicketWebService;

}