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
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.AccountEnvironment;
import com.liferay.osb.service.AccountEntryServiceUtil;
import com.liferay.osb.service.AccountEnvironmentServiceUtil;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;

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
		"mvc.command.name=/edit_account_environment"
	},
	service = MVCRenderCommand.class
)
public class EditAccountEnvironmentMVCRenderCommand
	extends BaseMVCRenderCommand {

	@Override
	protected String doRender(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws Exception {

		long accountEnvironmentId = ParamUtil.getLong(
			renderRequest, "accountEnvironmentId");
		long accountEntryId = ParamUtil.getLong(
			renderRequest, "accountEntryId");

		if (accountEnvironmentId > 0) {
			AccountEnvironment accountEnvironment =
				AccountEnvironmentServiceUtil.getAccountEnvironment(
					accountEnvironmentId);

			accountEntryId = accountEnvironment.getAccountEntryId();

			renderRequest.setAttribute(
				AccountEntryDetailsWebKeys.ACCOUNT_ENVIRONMENT,
				accountEnvironment);
		}

		if (accountEntryId > 0) {
			AccountEntry accountEntry = AccountEntryServiceUtil.getAccountEntry(
				accountEntryId);

			renderRequest.setAttribute(
				AccountEntryDetailsWebKeys.ACCOUNT_ENTRY, accountEntry);
		}

		return "/account_entry_details/edit_account_environment.jsp";
	}

	@Reference(
		target = "(module.service.lifecycle=osb.portlet.initialized)",
		unbind = "-"
	)
	protected void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {
	}

}