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
import com.liferay.osb.customer.downloads.display.constants.DownloadsDDMStructureConstants;
import com.liferay.osb.customer.downloads.display.constants.DownloadsDisplayPortletKeys;
import com.liferay.osb.model.AccountCustomer;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.PartnerEntry;
import com.liferay.osb.model.PartnerWorker;
import com.liferay.osb.service.AccountCustomerLocalServiceUtil;
import com.liferay.osb.service.PartnerWorkerLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	property = {
		"javax.portlet.name=" + DownloadsDisplayPortletKeys.DOWNLOADS_DISPLAY,
		"mvc.command.name=acceptAgreement"
	},
	service = MVCActionCommand.class
)
public class AcceptAgreementMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String agreement = ParamUtil.getString(actionRequest, "agreement");
		double version = ParamUtil.getDouble(actionRequest, "version");

		if (agreement.equals(
				DownloadsDDMStructureConstants.REQUIRED_AGREEMENT_ESA)) {

			setExpandoValue(
				themeDisplay.getCompanyId(), themeDisplay.getUserId(),
				"osbCustomerESA", version);
		}
		else if (agreement.equals(
					DownloadsDDMStructureConstants.
						REQUIRED_AGREEMENT_EVALUATION_EULA)) {

			setExpandoValue(
				themeDisplay.getCompanyId(), themeDisplay.getUserId(),
				"osbCustomerEvaluationEULA", version);
		}
		else if (agreement.equals(
					DownloadsDDMStructureConstants.
						REQUIRED_AGREEMENT_STUDIO_EULA)) {

			setExpandoValue(
				themeDisplay.getCompanyId(), themeDisplay.getUserId(),
				"osbCustomerStudioEULA", version);
		}
	}

	protected String getCompanies(long userId) throws PortalException {
		List<String> companies = new ArrayList<>();

		List<AccountCustomer> accountCustomers =
			AccountCustomerLocalServiceUtil.getUserAccountCustomers(userId);

		for (AccountCustomer accountCustomer : accountCustomers) {
			AccountEntry accountEntry = accountCustomer.getAccountEntry();

			companies.add(accountEntry.getName());
		}

		if (companies.isEmpty()) {
			List<PartnerWorker> partnerWorkers =
				PartnerWorkerLocalServiceUtil.getUserPartnerWorkers(userId);

			for (PartnerWorker partnerWorker : partnerWorkers) {
				PartnerEntry partnerEntry = partnerWorker.getPartnerEntry();

				companies.add(partnerEntry.getCode());
			}
		}

		return StringUtil.merge(companies, StringPool.SLASH);
	}

	protected void setExpandoValue(
			long companyId, long userId, String expandoColumnName,
			double version)
		throws PortalException {

		String[] agreementData = new String[4];

		agreementData[0] = String.valueOf(System.currentTimeMillis());
		agreementData[1] = getCompanies(userId);
		agreementData[2] = LocaleUtil.toLanguageId(LocaleUtil.US);
		agreementData[3] = String.valueOf(version);

		_expandoValueLocalService.addValue(
			companyId, User.class.getName(),
			ExpandoTableConstants.DEFAULT_TABLE_NAME, expandoColumnName, userId,
			agreementData);
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