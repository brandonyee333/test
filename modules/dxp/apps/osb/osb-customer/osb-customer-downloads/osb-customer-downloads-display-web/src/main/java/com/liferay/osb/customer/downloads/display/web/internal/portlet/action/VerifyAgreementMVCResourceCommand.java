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

package com.liferay.osb.customer.downloads.display.web.internal.portlet.action;

import com.liferay.expando.kernel.model.ExpandoTableConstants;
import com.liferay.expando.kernel.service.ExpandoValueLocalService;
import com.liferay.osb.customer.downloads.display.web.internal.constants.DDMStructureConstants;
import com.liferay.osb.customer.downloads.display.web.internal.constants.DownloadsDisplayPortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.portlet.JSONPortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
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
		"javax.portlet.name=" + DownloadsDisplayPortletKeys.DOWNLOADS_DISPLAY,
		"mvc.command.name=/verify_agreement"
	},
	service = MVCResourceCommand.class
)
public class VerifyAgreementMVCResourceCommand extends BaseMVCResourceCommand {

	protected void doServeResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String agreement = ParamUtil.getString(resourceRequest, "agreement");
		double versionRequired = ParamUtil.getDouble(
			resourceRequest, "versionRequired");

		boolean acceptedAgreement = false;

		if (agreement.equals(DDMStructureConstants.REQUIRED_AGREEMENT_ESA)) {
			if (hasExpandoValue(
					themeDisplay.getCompanyId(), themeDisplay.getUserId(),
					"osbCustomerESA", versionRequired) ||
				hasExpandoValue(
					themeDisplay.getCompanyId(), themeDisplay.getUserId(),
					"osbESA", versionRequired)) {

				acceptedAgreement = true;
			}
		}
		else if (agreement.equals(
					DDMStructureConstants.REQUIRED_AGREEMENT_EVALUATION_EULA)) {

			if (hasExpandoValue(
					themeDisplay.getCompanyId(), themeDisplay.getUserId(),
					"osbCustomerEvaluationEULA", versionRequired) ||
				hasExpandoValue(
					themeDisplay.getCompanyId(), themeDisplay.getUserId(),
					"osbEvaluationEULA", versionRequired)) {

				acceptedAgreement = true;
			}
		}
		else if (agreement.equals(
					DDMStructureConstants.REQUIRED_AGREEMENT_STUDIO_EULA)) {

			if (hasExpandoValue(
					themeDisplay.getCompanyId(), themeDisplay.getUserId(),
					"osbCustomerStudioEULA", versionRequired) ||
				hasExpandoValue(
					themeDisplay.getCompanyId(), themeDisplay.getUserId(),
					"osbStudioEULA", versionRequired)) {

				acceptedAgreement = true;
			}
		}

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("verified", String.valueOf(acceptedAgreement));

		JSONPortletResponseUtil.writeJSON(
			resourceRequest, resourceResponse, jsonObject);
	}

	protected boolean hasExpandoValue(
			long companyId, long userId, String expandoColumnName,
			double versionRequired)
		throws PortalException {

		String[] agreementData = _expandoValueLocalService.getData(
			companyId, User.class.getName(),
			ExpandoTableConstants.DEFAULT_TABLE_NAME, expandoColumnName, userId,
			new String[0]);

		if ((agreementData != null) && (agreementData.length >= 4)) {
			if (versionRequired == 0) {
				return true;
			}

			double version = GetterUtil.getDouble(agreementData[3]);

			if (version >= versionRequired) {
				return true;
			}
		}

		return false;
	}

	@Reference(
		target = "(module.service.lifecycle=osb.portlet.initialized)",
		unbind = "-"
	)
	protected void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {
	}

	@Reference
	private ExpandoValueLocalService _expandoValueLocalService;

}