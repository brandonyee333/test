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

package com.liferay.osb.customer.account.entry.details.web.internal.display.context;

import com.liferay.osb.customer.account.entry.details.web.internal.constants.AccountEntryDetailsWebKeys;
import com.liferay.osb.customer.account.entry.details.web.internal.display.context.util.AccountEntryDetailsRequestHelper;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.AccountEnvironment;
import com.liferay.osb.model.AccountEnvironmentAttachment;
import com.liferay.osb.model.AccountEnvironmentAttachmentConstants;
import com.liferay.osb.model.ProductEntry;
import com.liferay.osb.service.AccountEnvironmentAttachmentLocalServiceUtil;
import com.liferay.osb.service.AccountEnvironmentLocalServiceUtil;
import com.liferay.osb.service.ProductEntryLocalServiceUtil;
import com.liferay.osb.service.permission.OSBAccountEnvironmentPermission;
import com.liferay.osb.util.OSBActionKeys;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceURL;

/**
 * @author Amos Fong
 */
public class AccountEntryViewDisplayContext {

	public AccountEntryViewDisplayContext(
		RenderRequest renderRequest, RenderResponse renderResponse) {

		_renderRequest = renderRequest;
		_renderResponse = renderResponse;

		_accountEntryDetailsRequestHelper =
			new AccountEntryDetailsRequestHelper(renderRequest);
	}

	public AccountEntry getAccountEntry() {
		if (_accountEntry != null) {
			return _accountEntry;
		}

		_accountEntry = (AccountEntry)_renderRequest.getAttribute(
			AccountEntryDetailsWebKeys.ACCOUNT_ENTRY);

		return _accountEntry;
	}

	public JSONArray getAccountEnvironmentsJSONArray() throws Exception {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		AccountEntry accountEntry = getAccountEntry();

		List<AccountEnvironment> accountEnvironments =
			AccountEnvironmentLocalServiceUtil.getAccountEnvironments(
				accountEntry.getAccountEntryId());

		for (AccountEnvironment accountEnvironment : accountEnvironments) {
			JSONObject jsonObject = getDisplayJSONObject(
				accountEntry, accountEnvironment);

			jsonArray.put(jsonObject);
		}

		return jsonArray;
	}

	protected String getAccountEnvironmentAttachmentURL(
		AccountEnvironmentAttachment accountEnvironmentAttachment) {

		ResourceURL resourceURL = _renderResponse.createResourceURL();

		resourceURL.setParameter(
			"accountEnvironmentAttachmentId",
			String.valueOf(
				accountEnvironmentAttachment.
					getAccountEnvironmentAttachmentId()));
		resourceURL.setResourceID("/account_environment_attachment");

		return resourceURL.toString();
	}

	protected String getAccountEnvironmentDeleteURL(
		AccountEnvironment accountEnvironment) {

		PortletURL portletURL = _renderResponse.createActionURL();

		portletURL.setParameter(
			ActionRequest.ACTION_NAME, "deleteAccountEnvironment");
		portletURL.setParameter(
			"redirect", _accountEntryDetailsRequestHelper.getCurrentURL());
		portletURL.setParameter(
			"accountEnvironmentId",
			String.valueOf(accountEnvironment.getAccountEnvironmentId()));

		return portletURL.toString();
	}

	protected String getAccountEnvironmentEditURL(
			AccountEnvironment accountEnvironment)
		throws PortletException {

		PortletURL portletURL = _renderResponse.createRenderURL();

		portletURL.setParameter(
			"mvcRenderCommandName", "/edit_account_environment");
		portletURL.setParameter(
			"accountEnvironmentId",
			String.valueOf(accountEnvironment.getAccountEnvironmentId()));
		portletURL.setWindowState(LiferayWindowState.POP_UP);

		return portletURL.toString();
	}

	protected JSONObject getDisplayJSONObject(
			AccountEntry accountEntry, AccountEnvironment accountEnvironment)
		throws Exception {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put(
			"accountEnvironmentId",
			String.valueOf(accountEnvironment.getAccountEnvironmentId()));

		if (OSBAccountEnvironmentPermission.contains(
				_accountEntryDetailsRequestHelper.getPermissionChecker(),
				accountEntry.getAccountEntryId(), OSBActionKeys.DELETE)) {

			jsonObject.put(
				"deleteAccountEnvironmentURL",
				getAccountEnvironmentDeleteURL(accountEnvironment));
		}

		if (OSBAccountEnvironmentPermission.contains(
				_accountEntryDetailsRequestHelper.getPermissionChecker(),
				accountEntry.getAccountEntryId(), OSBActionKeys.UPDATE)) {

			jsonObject.put(
				"editAccountEnvironmentURL",
				getAccountEnvironmentEditURL(accountEnvironment));
		}

		jsonObject.put(
			"envASLabel",
			LanguageUtil.get(
				_accountEntryDetailsRequestHelper.getRequest(),
				accountEnvironment.getEnvASLabel()));
		jsonObject.put(
			"envDBLabel",
			LanguageUtil.get(
				_accountEntryDetailsRequestHelper.getRequest(),
				accountEnvironment.getEnvDBLabel()));
		jsonObject.put(
			"envJVMLabel",
			LanguageUtil.get(
				_accountEntryDetailsRequestHelper.getRequest(),
				accountEnvironment.getEnvJVMLabel()));
		jsonObject.put(
			"envLFRLabel",
			LanguageUtil.get(
				_accountEntryDetailsRequestHelper.getRequest(),
				accountEnvironment.getEnvLFRLabel()));

		String envOSLabel = LanguageUtil.get(
			_accountEntryDetailsRequestHelper.getRequest(),
			accountEnvironment.getEnvOSLabel());

		if (Validator.isNotNull(accountEnvironment.getEnvOSCustom())) {
			envOSLabel += " - " + accountEnvironment.getEnvOSCustom();
		}

		jsonObject.put("envOSLabel", envOSLabel);

		jsonObject.put("name", accountEnvironment.getName());

		AccountEnvironmentAttachment patchLevelAccountEnvironmentAttachment =
			AccountEnvironmentAttachmentLocalServiceUtil.
				fetchAccountEnvironmentAttachment(
					accountEnvironment.getAccountEnvironmentId(),
					AccountEnvironmentAttachmentConstants.TYPE_PATCH_LEVEL);

		if (patchLevelAccountEnvironmentAttachment != null) {
			jsonObject.put(
				"patchLevelAccountEnvironmentAttachmentFileName",
				patchLevelAccountEnvironmentAttachment.getFileName());
			jsonObject.put(
				"patchLevelAccountEnvironmentAttachmentURL",
				getAccountEnvironmentAttachmentURL(
					patchLevelAccountEnvironmentAttachment));
		}

		AccountEnvironmentAttachment portalExtAccountEnvironmentAttachment =
			AccountEnvironmentAttachmentLocalServiceUtil.
				fetchAccountEnvironmentAttachment(
					accountEnvironment.getAccountEnvironmentId(),
					AccountEnvironmentAttachmentConstants.TYPE_PORTAL_EXT);

		if (portalExtAccountEnvironmentAttachment != null) {
			jsonObject.put(
				"portalExtAccountEnvironmentAttachmentFileName",
				portalExtAccountEnvironmentAttachment.getFileName());
			jsonObject.put(
				"portalExtAccountEnvironmentAttachmentURL",
				getAccountEnvironmentAttachmentURL(
					portalExtAccountEnvironmentAttachment));
		}

		ProductEntry productEntry =
			ProductEntryLocalServiceUtil.getProductEntry(
				accountEnvironment.getProductEntryId());

		jsonObject.put(
			"productEntryDisplayName", productEntry.getDisplayName());

		return jsonObject;
	}

	private AccountEntry _accountEntry;
	private final AccountEntryDetailsRequestHelper
		_accountEntryDetailsRequestHelper;
	private final RenderRequest _renderRequest;
	private final RenderResponse _renderResponse;

}