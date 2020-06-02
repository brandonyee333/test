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
import com.liferay.osb.customer.admin.constants.WorkflowConstants;
import com.liferay.osb.customer.github.model.Collaborator;
import com.liferay.osb.customer.github.service.CollaboratorLocalService;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;

import javax.servlet.http.HttpServletResponse;

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

		JSONObject jsonObject = null;

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

			jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put("collaboratorId", collaborator.getCollaboratorId());
			jsonObject.put("createDate", collaborator.getCreateDate());

			String portletId =
				AccountEntryDetailsPortletKeys.ACCOUNT_ENTRY_DETAILS;

			long plid = _portal.getPlidFromPortletId(
				OSBConstants.GROUP_CUSTOMER_ID, portletId);

			LiferayPortletURL portletURL = PortletURLFactoryUtil.create(
				actionRequest, portletId, plid, PortletRequest.ACTION_PHASE);

			portletURL.setParameter(
				ActionRequest.ACTION_NAME, "deleteCollaborator");
			portletURL.setParameter(
				"collaboratorId",
				String.valueOf(collaborator.getCollaboratorId()));

			jsonObject.put("deleteCollaboratorURL", portletURL.toString());

			if (collaborator.getStatus() == WorkflowConstants.STATUS_CLOSED) {
				jsonObject.put("message", "pending-project-status");
			}
			else if (collaborator.getStatus() ==
						WorkflowConstants.STATUS_PENDING) {

				jsonObject.put("message", "pending-invitation-limit");
			}
			else {
				jsonObject.put("message", "success");
			}
		}
		catch (Exception e) {
			_log.error(e, e);

			HttpServletResponse httpServletResponse =
				_portal.getHttpServletResponse(actionResponse);

			httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);

			jsonObject = JSONFactoryUtil.createJSONObject();

			if (Validator.isNotNull(e.getMessage())) {
				jsonObject.put("errorMessage", e.getMessage());
			}
			else {
				jsonObject.put("errorMessage", e.getClass());
			}
		}

		JSONPortletResponseUtil.writeJSON(
			actionRequest, actionResponse, jsonObject);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AddCollaboratorMVCActionCommand.class);

	@Reference
	private CollaboratorLocalService _collaboratorLocalService;

	@Reference
	private Portal _portal;

}