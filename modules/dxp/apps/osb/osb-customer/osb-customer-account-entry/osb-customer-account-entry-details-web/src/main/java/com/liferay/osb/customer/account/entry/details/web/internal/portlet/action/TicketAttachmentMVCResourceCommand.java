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
import com.liferay.osb.customer.ticket.repository.FileRepositoryWebService;
import com.liferay.osb.customer.ticket.service.TicketAttachmentService;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;

import java.io.FileNotFoundException;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

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

	protected void doServeResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		long ticketAttachmentId = ParamUtil.getLong(
			resourceRequest, "ticketAttachmentId");

		TicketAttachment ticketAttachment =
			_ticketAttachmentService.getTicketAttachment(ticketAttachmentId);

		String downloadURL = _fileRepositoryWebService.getDownloadURL(
			ticketAttachment.getFileRepositoryId(),
			ticketAttachment.getFilePath());

		if (downloadURL != null) {
			HttpServletResponse response = _portal.getHttpServletResponse(
				resourceResponse);

			response.sendRedirect(downloadURL);
		}
		else {
			throw new FileNotFoundException();
		}
	}

	@Reference(
		target = "(module.service.lifecycle=osb.portlet.initialized)",
		unbind = "-"
	)
	protected void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {
	}

	@Reference
	private FileRepositoryWebService _fileRepositoryWebService;

	@Reference
	private Portal _portal;

	@Reference
	private TicketAttachmentService _ticketAttachmentService;

}