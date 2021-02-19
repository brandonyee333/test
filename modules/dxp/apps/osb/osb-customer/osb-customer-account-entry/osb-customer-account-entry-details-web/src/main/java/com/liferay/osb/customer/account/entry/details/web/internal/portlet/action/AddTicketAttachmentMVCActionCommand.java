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

import com.liferay.osb.customer.account.entry.details.constants.EventConstants;
import com.liferay.osb.customer.account.entry.details.service.EventLocalService;
import com.liferay.osb.customer.account.entry.details.web.internal.constants.AccountEntryDetailsPortletKeys;
import com.liferay.osb.customer.ticket.model.TicketAttachment;
import com.liferay.osb.customer.ticket.service.TicketAttachmentService;
import com.liferay.osb.customer.zendesk.exception.ZendeskTicketClosedException;
import com.liferay.osb.customer.zendesk.model.ZendeskTicket;
import com.liferay.osb.customer.zendesk.util.ZendeskMapperUtil;
import com.liferay.osb.customer.zendesk.web.service.ZendeskTicketWebService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Date;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	property = {
		"javax.portlet.name=" + AccountEntryDetailsPortletKeys.ACCOUNT_ENTRY_DETAILS,
		"mvc.command.name=addTicketAttachment"
	},
	service = MVCActionCommand.class
)
public class AddTicketAttachmentMVCActionCommand extends BaseMVCActionCommand {

	protected void addEvent(
			ActionRequest actionRequest, TicketAttachment ticketAttachment)
		throws PortalException {

		HttpServletRequest request = _portal.getHttpServletRequest(
			actionRequest);

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long classNameId = _classNameLocalService.getClassNameId(
			ZendeskTicket.class.getName());
		long typeClassNameId = _classNameLocalService.getClassNameId(
			TicketAttachment.class.getName());

		_eventLocalService.addEvent(
			themeDisplay.getUserId(), new Date(),
			ticketAttachment.getAccountEntryId(), classNameId,
			ticketAttachment.getZendeskTicketId(),
			EventConstants.TYPE_UPLOAD_ATTACHMENT, typeClassNameId,
			ticketAttachment.getTicketAttachmentId(),
			ticketAttachment.getFileName(), StringPool.BLANK,
			request.getRemoteAddr());
	}

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long zendeskTicketId = ParamUtil.getLong(
			actionRequest, "zendeskTicketId");
		String fileRepositoryId = ParamUtil.getString(
			actionRequest, "fileRepositoryId");
		String fileName = ParamUtil.getString(actionRequest, "fileName");
		long fileSize = ParamUtil.getLong(actionRequest, "fileSize");
		int type = ParamUtil.getInteger(actionRequest, "type");
		String comment = ParamUtil.getString(actionRequest, "comment");
		boolean noPersonalData = ParamUtil.getBoolean(
			actionRequest, "noPersonalData");

		ZendeskTicket zendeskTicket = _zendeskTicketWebService.getZendeskTicket(
			zendeskTicketId);

		if (zendeskTicket.isClosed()) {
			throw new ZendeskTicketClosedException();
		}

		long accountEntryId = _zendeskMapperUtil.getAccountEntryId(
			zendeskTicket.getZendeskOrganizationId());

		boolean regionRestricted = true;

		if (noPersonalData) {
			regionRestricted = false;
		}

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			TicketAttachment.class.getName(), actionRequest);

		serviceContext.setAttribute("comment", comment);

		TicketAttachment ticketAttachment =
			_ticketAttachmentService.addTicketAttachment(
				accountEntryId, zendeskTicketId, fileRepositoryId, fileName,
				fileSize, type, regionRestricted, serviceContext);

		addEvent(actionRequest, ticketAttachment);
	}

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private EventLocalService _eventLocalService;

	@Reference
	private Portal _portal;

	@Reference
	private TicketAttachmentService _ticketAttachmentService;

	@Reference
	private ZendeskMapperUtil _zendeskMapperUtil;

	@Reference
	private ZendeskTicketWebService _zendeskTicketWebService;

}