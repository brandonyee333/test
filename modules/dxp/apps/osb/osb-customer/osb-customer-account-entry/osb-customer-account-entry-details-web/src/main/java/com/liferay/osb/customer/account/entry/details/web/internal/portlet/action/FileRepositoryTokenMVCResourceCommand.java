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
import com.liferay.osb.customer.ticket.constants.TicketActionKeys;
import com.liferay.osb.customer.ticket.repository.FileRepositoryWebService;
import com.liferay.osb.customer.ticket.service.permission.TicketEntryPermissionChecker;
import com.liferay.osb.customer.zendesk.model.ZendeskTicket;
import com.liferay.osb.customer.zendesk.util.ZendeskMapperUtil;
import com.liferay.osb.customer.zendesk.web.service.ZendeskTicketWebService;
import com.liferay.osb.model.AccountEntry;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.portlet.JSONPortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	property = {
		"javax.portlet.name=" + AccountEntryDetailsPortletKeys.ACCOUNT_ENTRY_DETAILS,
		"mvc.command.name=/file_repository_token"
	},
	service = MVCResourceCommand.class
)
public class FileRepositoryTokenMVCResourceCommand
	extends BaseMVCResourceCommand {

	protected void doServeResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String fileRepositoryId = ParamUtil.getString(
			resourceRequest, "fileRepositoryId");

		long zendeskTicketId = ParamUtil.getLong(
			resourceRequest, "zendeskTicketId");

		ZendeskTicket zendeskTicket = _zendeskTicketWebService.getZendeskTicket(
			zendeskTicketId);

		AccountEntry accountEntry = _zendeskMapperUtil.getAccountEntry(
			zendeskTicket.getZendeskOrganizationId());

		TicketEntryPermissionChecker.check(
			themeDisplay.getPermissionChecker(),
			accountEntry.getAccountEntryId(),
			TicketActionKeys.ADD_TICKET_ATTACHMENT);

		String token = _fileRepositoryWebService.getToken(
			fileRepositoryId, zendeskTicketId);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		if (token != null) {
			jsonObject.put("message", "success");

			jsonObject.put("token", token);
		}
		else {
			jsonObject.put("message", "invalid-session");
		}

		JSONPortletResponseUtil.writeJSON(
			resourceRequest, resourceResponse, jsonObject);
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
	private ZendeskMapperUtil _zendeskMapperUtil;

	@Reference
	private ZendeskTicketWebService _zendeskTicketWebService;

}