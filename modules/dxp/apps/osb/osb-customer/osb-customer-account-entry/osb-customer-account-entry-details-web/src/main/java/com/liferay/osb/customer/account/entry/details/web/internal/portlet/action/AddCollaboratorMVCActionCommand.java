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
import com.liferay.osb.customer.github.model.Collaborator;
import com.liferay.osb.customer.github.service.CollaboratorLocalService;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jenny Chen
 */
@Component(
	property = {
		"javax.portlet.name=" + AccountEntryDetailsPortletKeys.ACCOUNT_ENTRY_DETAILS,
		"mvc.command.name=addCollaborator"
	},
	service = MVCActionCommand.class
)
public class AddCollaboratorMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long accountEntryId = ParamUtil.getLong(
			actionRequest, "accountEntryId");
		String emailAddress = ParamUtil.getString(
			actionRequest, "emailAddress");
		String fullName = ParamUtil.getString(actionRequest, "fullName");
		String gitHubUserName = ParamUtil.getString(
			actionRequest, "gitHubUserName");

		try {
			Collaborator collaborator =
				_collaboratorLocalService.addCollaborator(
					themeDisplay.getUserId(), accountEntryId, emailAddress,
					fullName, gitHubUserName);

			if (collaborator.getStatus() != WorkflowConstants.STATUS_APPROVED) {
				SessionErrors.add(actionRequest, "Pending Invitation Limit");
			}
		}
		catch (Exception e) {
			if (Validator.isNotNull(e.getMessage())) {
				SessionErrors.add(actionRequest, e.getMessage());
			}
			else {
				SessionErrors.add(actionRequest, e.getClass());
			}
		}
	}

	@Reference
	private CollaboratorLocalService _collaboratorLocalService;

}