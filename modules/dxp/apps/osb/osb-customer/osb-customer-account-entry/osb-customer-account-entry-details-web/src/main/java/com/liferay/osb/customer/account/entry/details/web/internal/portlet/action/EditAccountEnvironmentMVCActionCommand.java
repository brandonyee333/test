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
import com.liferay.osb.exception.AccountEnvironmentAttachmentSizeException;
import com.liferay.osb.model.AccountEnvironment;
import com.liferay.osb.model.AccountEnvironmentAttachmentConstants;
import com.liferay.osb.service.AccountEnvironmentServiceUtil;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.File;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

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
		int envBrowser = ParamUtil.getInteger(actionRequest, "envBrowser");
		int envCS = ParamUtil.getInteger(actionRequest, "envCS");
		String envSearch = StringUtil.merge(
			ParamUtil.getParameterValues(actionRequest, "envSearch"),
			StringPool.NEW_LINE);

		List<ObjectValuePair<String, File>> files = new ArrayList<>();

		List<Integer> types = new ArrayList<>();

		String[] uploadFileNames = {"patch-level", "portal-ext"};

		try {
			for (String uploadFileName : uploadFileNames) {
				String fileName = uploadPortletRequest.getFileName(
					uploadFileName);

				if (Validator.isNull(fileName)) {
					continue;
				}

				File file = uploadPortletRequest.getFile(uploadFileName);

				if (file == null) {
					continue;
				}

				if (file.length() <= 0) {
					throw new AccountEnvironmentAttachmentSizeException(
						AccountEnvironmentAttachmentSizeException.EMPTY_FILE,
						fileName);
				}

				ObjectValuePair<String, File> ovp = new ObjectValuePair<>(
					fileName, file);

				files.add(ovp);

				if (uploadFileName.equals("portal-ext")) {
					types.add(
						AccountEnvironmentAttachmentConstants.TYPE_PORTAL_EXT);
				}
				else {
					types.add(
						AccountEnvironmentAttachmentConstants.TYPE_PATCH_LEVEL);
				}
			}

			AccountEnvironment accountEnvironment = null;

			if (accountEnvironmentId > 0) {
				accountEnvironment =
					AccountEnvironmentServiceUtil.updateAccountEnvironment(
						accountEnvironmentId, productEntryId, name, envOS,
						envOSCustom, envDB, envJVM, envAS, envLFR, envBrowser,
						envCS, envSearch, files, types);
			}
			else {
				accountEnvironment =
					AccountEnvironmentServiceUtil.addAccountEnvironment(
						accountEntryId, productEntryId, name, envOS,
						envOSCustom, envDB, envJVM, envAS, envLFR, envBrowser,
						envCS, envSearch, files, types);
			}

			PortletConfig portletConfig = getPortletConfig(actionRequest);

			PortletURL portletURL = PortletURLFactoryUtil.create(
				actionRequest, portletConfig.getPortletName(),
				PortletRequest.RENDER_PHASE);

			portletURL.setParameter(
				"mvcRenderCommandName", "/edit_account_environment");
			portletURL.setParameter(
				"accountEnvironmentId",
				String.valueOf(accountEnvironment.getAccountEnvironmentId()));
			portletURL.setWindowState(LiferayWindowState.POP_UP);

			actionResponse.sendRedirect(portletURL.toString());
		}
		catch (Exception e) {
			SessionErrors.add(actionRequest, e.getClass());

			actionResponse.setRenderParameter(
				"mvcRenderCommandName", "/edit_account_environment");

			hideDefaultSuccessMessage(actionRequest);
		}
		finally {
			uploadPortletRequest.cleanUp();
		}
	}

	@Reference(
		target = "(module.service.lifecycle=osb.portlet.initialized)",
		unbind = "-"
	)
	protected void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {
	}

	@Reference
	private Portal _portal;

}