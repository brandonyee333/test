/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.account.entry.details.web.internal.portlet.action;

import com.liferay.osb.customer.account.entry.details.constants.EventConstants;
import com.liferay.osb.customer.account.entry.details.service.EventLocalService;
import com.liferay.osb.customer.account.entry.details.web.internal.constants.AccountEntryDetailsPortletKeys;
import com.liferay.osb.customer.ticket.model.TicketAttachment;
import com.liferay.osb.customer.ticket.service.TicketAttachmentService;
import com.liferay.osb.customer.zendesk.model.ZendeskTicket;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Date;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	property = {
		"javax.portlet.name=" + AccountEntryDetailsPortletKeys.ACCOUNT_ENTRY_DETAILS,
		"mvc.command.name=deleteTicketAttachment"
	},
	service = MVCActionCommand.class
)
public class DeleteTicketAttachmentMVCActionCommand
	extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long ticketAttachmentId = ParamUtil.getLong(
			actionRequest, "ticketAttachmentId");

		try {
			TicketAttachment ticketAttachment =
				_ticketAttachmentService.deleteTicketAttachment(
					ticketAttachmentId);

			long classNameId = _classNameLocalService.getClassNameId(
				ZendeskTicket.class.getName());
			long typeClassNameId = _classNameLocalService.getClassNameId(
				TicketAttachment.class.getName());

			_eventLocalService.addEvent(
				themeDisplay.getUserId(), new Date(),
				ticketAttachment.getAccountEntryId(), classNameId,
				ticketAttachment.getZendeskTicketId(),
				EventConstants.TYPE_DELETE_ATTACHMENT, typeClassNameId,
				ticketAttachment.getTicketAttachmentId(),
				ticketAttachment.getFileName(), StringPool.BLANK,
				StringPool.BLANK);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}

			SessionErrors.add(actionRequest, e.getClass(), e);

			actionResponse.setRenderParameter(
				"mvcRenderCommandName", "/view_account_entry");

			hideDefaultSuccessMessage(actionRequest);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DeleteTicketAttachmentMVCActionCommand.class);

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private EventLocalService _eventLocalService;

	@Reference
	private TicketAttachmentService _ticketAttachmentService;

}