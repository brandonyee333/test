/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.account.entry.details.web.internal.portlet.action;

import com.liferay.osb.customer.account.entry.details.web.internal.constants.AccountEntryDetailsPortletKeys;
import com.liferay.osb.customer.admin.service.AccountEnvironmentService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	property = {
		"javax.portlet.name=" + AccountEntryDetailsPortletKeys.ACCOUNT_ENTRY_DETAILS,
		"mvc.command.name=editAccountEnvironment"
	},
	service = MVCActionCommand.class
)
public class EditAccountEnvironmentMVCActionCommand
	extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		UploadPortletRequest uploadPortletRequest =
			_portal.getUploadPortletRequest(actionRequest);

		long accountEnvironmentId = ParamUtil.getLong(
			actionRequest, "accountEnvironmentId");

		long accountEntryId = ParamUtil.getLong(
			actionRequest, "accountEntryId");
		long productEntryId = ParamUtil.getLong(
			actionRequest, "productEntryId");
		String name = ParamUtil.getString(actionRequest, "name");
		int envOS = ParamUtil.getInteger(actionRequest, "envOS");
		String envOSCustom = ParamUtil.getString(actionRequest, "envOSCustom");
		int envDB = ParamUtil.getInteger(actionRequest, "envDB");
		int envJVM = ParamUtil.getInteger(actionRequest, "envJVM");
		int envAS = ParamUtil.getInteger(actionRequest, "envAS");
		int envLFR = ParamUtil.getInteger(actionRequest, "envLFR");
		int envCommerce = ParamUtil.getInteger(actionRequest, "envCommerce");
		int envBrowser = ParamUtil.getInteger(actionRequest, "envBrowser");
		int envCS = ParamUtil.getInteger(actionRequest, "envCS");
		String envSearch = StringUtil.merge(
			ParamUtil.getParameterValues(actionRequest, "envSearch"),
			StringPool.NEW_LINE);

		try {
			if (accountEnvironmentId > 0) {
				_accountEnvironmentService.updateAccountEnvironment(
					accountEnvironmentId, productEntryId, name, envOS,
					envOSCustom, envDB, envJVM, envAS, envLFR, envCommerce,
					envBrowser, envCS, envSearch);
			}
			else {
				_accountEnvironmentService.addAccountEnvironment(
					accountEntryId, productEntryId, name, envOS, envOSCustom,
					envDB, envJVM, envAS, envLFR, envCommerce, envBrowser,
					envCS, envSearch);
			}
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}

			SessionErrors.add(actionRequest, e.getClass());

			actionResponse.setRenderParameter(
				"mvcRenderCommandName", "/view_account_entry");

			hideDefaultSuccessMessage(actionRequest);
		}
		finally {
			uploadPortletRequest.cleanUp();
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		EditAccountEnvironmentMVCActionCommand.class);

	@Reference
	private AccountEnvironmentService _accountEnvironmentService;

	@Reference
	private Portal _portal;

}