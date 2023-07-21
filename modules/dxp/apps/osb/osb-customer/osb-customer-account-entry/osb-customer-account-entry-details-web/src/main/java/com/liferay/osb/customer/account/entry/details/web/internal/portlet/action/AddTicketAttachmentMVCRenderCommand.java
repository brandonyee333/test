/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.account.entry.details.web.internal.portlet.action;

import com.liferay.osb.customer.account.entry.details.web.internal.constants.AccountEntryDetailsPortletKeys;
import com.liferay.osb.customer.account.entry.details.web.internal.constants.AccountEntryDetailsWebKeys;
import com.liferay.osb.customer.admin.model.AccountEntry;
import com.liferay.osb.customer.admin.service.AccountEntryLocalService;
import com.liferay.osb.customer.koroneiki.web.service.AccountWebService;
import com.liferay.osb.customer.ticket.constants.TicketActionKeys;
import com.liferay.osb.customer.ticket.service.permission.TicketEntryPermissionChecker;
import com.liferay.osb.customer.zendesk.exception.ZendeskTicketClosedException;
import com.liferay.osb.customer.zendesk.model.ZendeskTicket;
import com.liferay.osb.customer.zendesk.util.ZendeskMapperUtil;
import com.liferay.osb.customer.zendesk.web.service.ZendeskTicketWebService;
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

		AccountEntry accountEntry = _accountEntryLocalService.getAccountEntry(
			accountEntryId);

		renderRequest.setAttribute(
			AccountEntryDetailsWebKeys.ACCOUNT,
			_accountWebService.getAccount(
				accountEntry.getKoroneikiAccountKey()));

		return "/account_entry_details/add_ticket_attachment.jsp";
	}

	@Reference
	private AccountEntryLocalService _accountEntryLocalService;

	@Reference
	private AccountWebService _accountWebService;

	@Reference
	private ZendeskMapperUtil _zendeskMapperUtil;

	@Reference
	private ZendeskTicketWebService _zendeskTicketWebService;

}