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

package com.liferay.osb.customer.web.internal.portlet.action;

import com.liferay.osb.customer.exception.EmailAddressDomainException;
import com.liferay.osb.customer.service.AuditFormLocalService;
import com.liferay.osb.customer.service.TrainingBaseWebService;
import com.liferay.osb.customer.web.internal.constants.PassportAccessPortletKeys;
import com.liferay.portal.kernel.exception.RequiredFieldException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.HashMap;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	property = {
		"javax.portlet.name=" + PassportAccessPortletKeys.PASSPORT_ACCESS,
		"mvc.command.name=/submit_passport_access"
	},
	service = MVCActionCommand.class
)
public class SubmitPassportAccessMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			String endUserFirstName = ParamUtil.getString(
				actionRequest, "endUserFirstName");
			String endUserLastName = ParamUtil.getString(
				actionRequest, "endUserLastName");
			String endUserEmailAddress = ParamUtil.getString(
				actionRequest, "endUserEmailAddress");
			boolean agreement = ParamUtil.getBoolean(
				actionRequest, "agreement");

			_auditFormLocalService.addAuditForm(
				themeDisplay.getUserId(),
				endUserFirstName + StringPool.SPACE + endUserLastName,
				endUserEmailAddress, agreement);

			Map<String, String> parameters = new HashMap<>();

			parameters.put("emailAddress", endUserEmailAddress);
			parameters.put("firstName", endUserFirstName);
			parameters.put("lastName", endUserLastName);

			_trainingBaseWebService.post(parameters);

			actionResponse.setRenderParameter(
				"mvcRenderCommandName", "/success");
		}
		catch (Exception e) {
			if (e instanceof EmailAddressDomainException ||
				e instanceof RequiredFieldException) {

				SessionErrors.add(actionRequest, e.getClass());

				actionResponse.setRenderParameter(
					"mvcRenderCommandName", "/view");
			}
			else {
				_log.error(e, e);

				throw e;
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SubmitPassportAccessMVCActionCommand.class);

	@Reference
	private AuditFormLocalService _auditFormLocalService;

	@Reference
	private TrainingBaseWebService _trainingBaseWebService;

}