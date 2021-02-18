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
import com.liferay.osb.customer.ticket.exception.NoSuchTicketAttachmentException;
import com.liferay.osb.customer.ticket.model.TicketAttachment;
import com.liferay.osb.customer.ticket.repository.FileRepositoryWebService;
import com.liferay.osb.customer.ticket.service.TicketAttachmentService;
import com.liferay.osb.customer.zendesk.model.ZendeskTicket;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.FileNotFoundException;

import java.util.Date;

import javax.portlet.PortletURL;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	property = {
		"javax.portlet.name=" + AccountEntryDetailsPortletKeys.ACCOUNT_ENTRY_DETAILS,
		"mvc.command.name=/ticket_attachment"
	},
	service = MVCResourceCommand.class
)
public class TicketAttachmentMVCResourceCommand extends BaseMVCResourceCommand {

	protected void addEvent(
			ResourceRequest resourceRequest, long userId,
			TicketAttachment ticketAttachment)
		throws PortalException {

		HttpServletRequest request = _portal.getHttpServletRequest(
			resourceRequest);

		long classNameId = _classNameLocalService.getClassNameId(
			ZendeskTicket.class.getName());
		long typeClassNameId = _classNameLocalService.getClassNameId(
			TicketAttachment.class.getName());

		_eventLocalService.addEvent(
			userId, new Date(), ticketAttachment.getAccountEntryId(),
			classNameId, ticketAttachment.getZendeskTicketId(),
			EventConstants.TYPE_DOWNLOAD_ATTACHMENT, typeClassNameId,
			ticketAttachment.getTicketAttachmentId(),
			ticketAttachment.getFileName(), StringPool.BLANK,
			request.getRemoteAddr());
	}

	protected void doServeResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		HttpServletResponse response = _portal.getHttpServletResponse(
			resourceResponse);

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		try {
			long ticketAttachmentId = ParamUtil.getLong(
				resourceRequest, "ticketAttachmentId");

			TicketAttachment ticketAttachment =
				_ticketAttachmentService.getTicketAttachment(
					ticketAttachmentId);

			String downloadURL = _fileRepositoryWebService.getDownloadURL(
				ticketAttachment.getFileRepositoryId(),
				ticketAttachment.getFilePath());

			if (downloadURL != null) {
				response.sendRedirect(downloadURL);

				addEvent(
					resourceRequest, themeDisplay.getUserId(),
					ticketAttachment);
			}
			else {
				throw new FileNotFoundException();
			}
		}
		catch (Exception e) {
			if (!(e instanceof FileNotFoundException) &&
				!(e instanceof NoSuchTicketAttachmentException)) {

				_log.error(e, e);
			}

			SessionErrors.add(resourceRequest, e.getClass());

			PortletURL portletURL = resourceResponse.createRenderURL();

			portletURL.setParameter("mvcRenderCommandName", "/error");

			response.sendRedirect(portletURL.toString());
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		TicketAttachmentMVCResourceCommand.class);

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private EventLocalService _eventLocalService;

	@Reference
	private FileRepositoryWebService _fileRepositoryWebService;

	@Reference
	private Portal _portal;

	@Reference
	private TicketAttachmentService _ticketAttachmentService;

}