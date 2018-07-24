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
import com.liferay.osb.model.AccountEnvironmentAttachment;
import com.liferay.osb.service.AccountEnvironmentAttachmentLocalServiceUtil;
import com.liferay.osb.service.AccountEnvironmentAttachmentServiceUtil;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.ParamUtil;

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
		"mvc.command.name=/account_environment_attachment"
	},
	service = MVCResourceCommand.class
)
public class AccountEnvironmentAttachmentMVCResourceCommand
	extends BaseMVCResourceCommand {

	protected void doServeResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		long accountEnvironmentAttachmentId = ParamUtil.getLong(
			resourceRequest, "accountEnvironmentAttachmentId");

		AccountEnvironmentAttachment accountEnvironmentAttachment =
			AccountEnvironmentAttachmentServiceUtil.
				getAccountEnvironmentAttachment(accountEnvironmentAttachmentId);

		PortletResponseUtil.sendFile(
			resourceRequest, resourceResponse,
			accountEnvironmentAttachment.getFileName(),
			AccountEnvironmentAttachmentLocalServiceUtil.getFileAsStream(
				accountEnvironmentAttachment),
			accountEnvironmentAttachment.getContentLength(),
			MimeTypesUtil.getContentType(
				accountEnvironmentAttachment.getFileName()),
			HttpHeaders.CONTENT_DISPOSITION_ATTACHMENT);
	}

	@Reference(
		target = "(module.service.lifecycle=osb.portlet.initialized)",
		unbind = "-"
	)
	protected void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {
	}

}