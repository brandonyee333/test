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
import com.liferay.osb.customer.ticket.model.TicketAttachment;
import com.liferay.osb.customer.ticket.service.TicketAttachmentService;
import com.liferay.osb.customer.zendesk.model.ZendeskTicket;
import com.liferay.osb.customer.zendesk.util.ZendeskMapperUtil;
import com.liferay.osb.customer.zendesk.web.service.ZendeskTicketCommentWebService;
import com.liferay.osb.customer.zendesk.web.service.ZendeskTicketWebService;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

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
		"mvc.command.name=addTicketAttachment"
	},
	service = MVCActionCommand.class
)
public class AddTicketAttachmentMVCActionCommand extends BaseMVCActionCommand {

	protected String buildZendeskTicketCommentBody(
		ActionResponse actionResponse, TicketAttachment ticketAttachment,
		String comment) {

		StringBundler sb = new StringBundler(7);

		if (Validator.isNotNull(comment)) {
			sb.append(comment.replace(StringPool.NEW_LINE, "<br />"));
			sb.append("<br /><br />");
		}

		sb.append("<a href=\"");

		LiferayPortletResponse liferayPortletResponse =
			_portal.getLiferayPortletResponse(actionResponse);

		LiferayPortletURL resourceURL =
			(LiferayPortletURL)liferayPortletResponse.createResourceURL();

		resourceURL.setCopyCurrentRenderParameters(false);
		resourceURL.setParameter(
			"ticketAttachmentId",
			String.valueOf(ticketAttachment.getTicketAttachmentId()));
		resourceURL.setResourceID("/ticket_attachment");

		sb.append(resourceURL.toString());

		sb.append("\">");
		sb.append(ticketAttachment.getFileName());
		sb.append("</a>");

		return sb.toString();
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

		ZendeskTicket zendeskTicket = _zendeskTicketWebService.getZendeskTicket(
			zendeskTicketId);

		long accountEntryId = _zendeskMapperUtil.getAccountEntryId(
			zendeskTicket.getZendeskOrganizationId());

		TicketAttachment ticketAttachment =
			_ticketAttachmentService.addTicketAttachment(
				zendeskTicketId, accountEntryId, fileRepositoryId, fileName,
				fileSize, type);

		long zendeskUserId = _zendeskMapperUtil.getZendeskUserId(
			ticketAttachment.getUserId());

		String zendeskTicketCommentBody = buildZendeskTicketCommentBody(
			actionResponse, ticketAttachment, comment);

		_zendeskTicketCommentWebService.addZendeskTicketComment(
			zendeskTicketId, zendeskUserId, zendeskTicketCommentBody);
	}

	@Reference
	private Portal _portal;

	@Reference
	private TicketAttachmentService _ticketAttachmentService;

	@Reference
	private ZendeskMapperUtil _zendeskMapperUtil;

	@Reference
	private ZendeskTicketCommentWebService _zendeskTicketCommentWebService;

	@Reference
	private ZendeskTicketWebService _zendeskTicketWebService;

}