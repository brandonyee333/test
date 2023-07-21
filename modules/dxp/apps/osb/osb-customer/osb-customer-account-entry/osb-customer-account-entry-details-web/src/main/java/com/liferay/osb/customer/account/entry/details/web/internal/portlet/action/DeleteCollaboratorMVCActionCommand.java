/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.account.entry.details.web.internal.portlet.action;

import com.liferay.osb.customer.account.entry.details.web.internal.constants.AccountEntryDetailsPortletKeys;
import com.liferay.osb.customer.github.service.CollaboratorLocalService;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.JSONPortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jenny Chen
 */
@Component(
	property = {
		"javax.portlet.name=" + AccountEntryDetailsPortletKeys.ACCOUNT_ENTRY_DETAILS,
		"mvc.command.name=deleteCollaborator"
	},
	service = MVCActionCommand.class
)
public class DeleteCollaboratorMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		JSONObject jsonObject = null;

		long collaboratorId = ParamUtil.getLong(
			actionRequest, "collaboratorId");

		try {
			_collaboratorLocalService.deleteCollaborator(collaboratorId);

			jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put("message", "success");
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
		DeleteCollaboratorMVCActionCommand.class);

	@Reference
	private CollaboratorLocalService _collaboratorLocalService;

	@Reference
	private Portal _portal;

}