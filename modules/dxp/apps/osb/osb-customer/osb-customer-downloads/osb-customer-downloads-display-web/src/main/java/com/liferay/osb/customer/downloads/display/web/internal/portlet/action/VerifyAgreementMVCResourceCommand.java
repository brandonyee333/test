/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.downloads.display.web.internal.portlet.action;

import com.liferay.expando.kernel.model.ExpandoTableConstants;
import com.liferay.expando.kernel.service.ExpandoValueLocalService;
import com.liferay.osb.customer.downloads.display.constants.DownloadsDDMStructureConstants;
import com.liferay.osb.customer.downloads.display.constants.DownloadsDisplayPortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.User;
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

		if (agreement.equals(
				DownloadsDDMStructureConstants.REQUIRED_AGREEMENT_ESA)) {

			if (hasExpandoValue(
					themeDisplay.getCompanyId(), themeDisplay.getUserId(),
					"osbCustomerESA", versionRequired)) {

				acceptedAgreement = true;
			}
		}
		else if (agreement.equals(
					DownloadsDDMStructureConstants.
						REQUIRED_AGREEMENT_EVALUATION_EULA)) {

			if (hasExpandoValue(
					themeDisplay.getCompanyId(), themeDisplay.getUserId(),
					"osbCustomerEvaluationEULA", versionRequired)) {

				acceptedAgreement = true;
			}
		}
		else if (agreement.equals(
					DownloadsDDMStructureConstants.
						REQUIRED_AGREEMENT_STUDIO_EULA)) {

			if (hasExpandoValue(
					themeDisplay.getCompanyId(), themeDisplay.getUserId(),
					"osbCustomerStudioEULA", versionRequired)) {

				acceptedAgreement = true;
			}
		}

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("verified", acceptedAgreement);

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

	@Reference
	private ExpandoValueLocalService _expandoValueLocalService;

}