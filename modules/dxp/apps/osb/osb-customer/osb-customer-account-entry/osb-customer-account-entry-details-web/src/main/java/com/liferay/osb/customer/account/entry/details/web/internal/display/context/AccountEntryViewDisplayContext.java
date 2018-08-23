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
import com.liferay.osb.model.AccountEnvironmentConstants;
import com.liferay.osb.model.OfferingEntry;
import com.liferay.osb.model.ProductEntry;
import com.liferay.osb.model.ProductEntryConstants;
import com.liferay.osb.service.AccountEnvironmentAttachmentLocalServiceUtil;
import com.liferay.osb.service.AccountEnvironmentLocalServiceUtil;
import com.liferay.osb.service.OfferingEntryLocalServiceUtil;
import com.liferay.osb.service.ProductEntryLocalServiceUtil;
import com.liferay.osb.service.permission.OSBAccountEnvironmentPermission;
import com.liferay.osb.util.OSBActionKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.ListType;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.service.ListTypeServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.stream.IntStream;

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

	public static List<ListType> getPortalEnvListTypes(
		long envLFR, String envListType) {

		return getPortalEnvListTypes(envLFR, envListType, StringPool.BLANK);
	}

	public static List<ListType> getPortalEnvListTypes(
		long envLFR, String envListType, String sublistType) {

		List<ListType> listTypes = ListTypeServiceUtil.getListTypes(
			envListType);

		listTypes = ListUtil.copy(listTypes);

		if (Validator.isNotNull(sublistType)) {
			sublistType = StringPool.PERIOD + sublistType;
		}

		long[] listTypeIds = AccountEnvironmentConstants.getEnvListTypeIds(
			envLFR, envListType + sublistType);

		Iterator<ListType> itr = listTypes.iterator();

		while (itr.hasNext()) {
			ListType listType = itr.next();

			if (!ArrayUtil.contains(listTypeIds, listType.getListTypeId())) {
				itr.remove();
			}
		}

		return listTypes;
	}

	public static boolean hasEnterpriseSearchOffering(
			long accountEntryId, int productEntryEnvironment)
		throws PortalException {

		List<OfferingEntry> offeringEntries =
			OfferingEntryLocalServiceUtil.getAccountEntryOfferingEntries(
				accountEntryId);

		for (OfferingEntry offeringEntry : offeringEntries) {
			ProductEntry productEntry = offeringEntry.getProductEntry();

			if (productEntry.isEnterpriseSearch() &&
				(productEntry.getEnvironment() == productEntryEnvironment)) {

				return true;
			}
		}

		return false;
	}

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

	public JSONArray getAddAccountEnvironmentsJSONArray() throws Exception {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		AccountEntry accountEntry = getAccountEntry();

		List<OfferingEntry> offeringEntries =
			OfferingEntryLocalServiceUtil.getAccountEntryOfferingEntries(
				accountEntry.getAccountEntryId());

		List<String> productEntryDisplayNames = new ArrayList<>();

		for (OfferingEntry offeringEntry : offeringEntries) {
			ProductEntry productEntry = offeringEntry.getProductEntry();

			if (!productEntryDisplayNames.contains(
					productEntry.getDisplayName())) {

				productEntryDisplayNames.add(productEntry.getDisplayName());

				JSONObject jsonObject = getDisplayJSONObject(
					accountEntry, productEntry);

				jsonArray.put(jsonObject);
			}
		}

		return jsonArray;
	}

	public JSONArray getEditAccountEnvironmentsJSONArray() throws Exception {
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

	public JSONArray getProductVersions(
			long accountEntryId, ProductEntry productEntry)
		throws Exception {

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		List<ListType> envLFRTypes = ListUtil.copy(
			productEntry.getAllVersionsListTypes());

		Iterator<ListType> itr = envLFRTypes.iterator();

		IntStream intStream = Arrays.stream(
			ProductEntryConstants.LIST_TYPES_DEPRECATED);

		long[] deprecatedTypes = intStream.asLongStream().toArray();

		while (itr.hasNext()) {
			ListType envLFRType = itr.next();

			if (ArrayUtil.contains(
					deprecatedTypes, envLFRType.getListTypeId()) ||
				(envLFRType.getListTypeId() ==
					(long)ProductEntryConstants.PORTAL_VERSION_OTHER)) {

				itr.remove();
			}
		}

		ThemeDisplay themeDisplay = getThemeDisplay();

		for (ListType listType : envLFRTypes) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put(
				"environmentDetails",
				getEnvironmentDetails(
					accountEntryId, productEntry, listType,
					themeDisplay.getLocale()));

			jsonArray.put(jsonObject);
		}

		return jsonArray;
	}

	public ThemeDisplay getThemeDisplay() {
		if (_themeDisplay != null) {
			return _themeDisplay;
		}

		_themeDisplay = (ThemeDisplay)_renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		return _themeDisplay;
	}

	protected String getAccountEnvironmentAddURL(AccountEntry accountEntry)
		throws PortletException {

		PortletURL portletURL = _renderResponse.createRenderURL();

		portletURL.setParameter(
			"mvcRenderCommandName", "/edit_account_environment");
		portletURL.setParameter(
			"accountEntryId", String.valueOf(accountEntry.getAccountEntryId()));
		portletURL.setWindowState(LiferayWindowState.POP_UP);

		return portletURL.toString();
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
		jsonObject.put("productEntryId", productEntry.getProductEntryId());

		return jsonObject;
	}

	protected JSONObject getDisplayJSONObject(
			AccountEntry accountEntry, ProductEntry productEntry)
		throws Exception {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		if (OSBAccountEnvironmentPermission.contains(
				_accountEntryDetailsRequestHelper.getPermissionChecker(),
				accountEntry.getAccountEntryId(),
				OSBActionKeys.ADD_ACCOUNT_ENVIRONMENT)) {

			jsonObject.put(
				"addAccountEnvironmentURL",
				getAccountEnvironmentAddURL(accountEntry));
		}

		jsonObject.put(
			"productEntryId", String.valueOf(productEntry.getProductEntryId()));

		jsonObject.put("productName", productEntry.getDisplayName());

		jsonObject.put(
			"accountEntryId", String.valueOf(accountEntry.getAccountEntryId()));

		jsonObject.put(
			"productVersions",
			getProductVersions(accountEntry.getAccountEntryId(), productEntry));

		return jsonObject;
	}

	protected JSONArray getEnvironmentArray(
		List<ListType> listTypes, Locale locale) {

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (ListType listType : listTypes) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put(
				"name", LanguageUtil.get(locale, listType.getName()));
			jsonObject.put("value", String.valueOf(listType.getListTypeId()));

			jsonArray.put(jsonObject);
		}

		return jsonArray;
	}

	protected JSONArray getEnvironmentDetails(
			long accountEntryId, ProductEntry productEntry, ListType listType,
			Locale locale)
		throws Exception {

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("envLFR", LanguageUtil.get(locale, listType.getName()));

		long listTypeId = listType.getListTypeId();

		List<ListType> envASListTypes = getPortalEnvListTypes(
			listTypeId, AccountEnvironmentConstants.LIST_TYPE_ENV_AS);

		jsonObject.put("envAS", getEnvironmentArray(envASListTypes, locale));

		List<ListType> envBrowserListTypes = getPortalEnvListTypes(
			listTypeId, AccountEnvironmentConstants.LIST_TYPE_ENV_BROWSER);

		jsonObject.put(
			"envBR", getEnvironmentArray(envBrowserListTypes, locale));

		if (ProductEntryConstants.isPortalVersion6_2(listTypeId) ||
			ProductEntryConstants.isDigitalEnterpriseVersion7_0(listTypeId) ||
			ProductEntryConstants.isDigitalEnterpriseVersion7_1(listTypeId)) {

			List<ListType> envCSListTypes = getPortalEnvListTypes(
				listTypeId, AccountEnvironmentConstants.LIST_TYPE_ENV_CS);

			jsonObject.put(
				"envCS", getEnvironmentArray(envCSListTypes, locale));
		}

		List<ListType> envDBListTypes = getPortalEnvListTypes(
			listTypeId, AccountEnvironmentConstants.LIST_TYPE_ENV_DB);

		jsonObject.put("envDB", getEnvironmentArray(envDBListTypes, locale));

		List<ListType> envJVMListTypes = getPortalEnvListTypes(
			listTypeId, AccountEnvironmentConstants.LIST_TYPE_ENV_JVM);

		jsonObject.put("envJVM", getEnvironmentArray(envJVMListTypes, locale));

		List<ListType> envOSListTypes = getPortalEnvListTypes(
			listTypeId, AccountEnvironmentConstants.LIST_TYPE_ENV_OS);

		jsonObject.put("envOS", getEnvironmentArray(envOSListTypes, locale));

		if (ProductEntryConstants.isDigitalEnterpriseVersion7_0(listTypeId) ||
			ProductEntryConstants.isDigitalEnterpriseVersion7_1(listTypeId)) {

			List<ListType> envSearchListTypes = new ArrayList<>();

			if (hasEnterpriseSearchOffering(
					accountEntryId, productEntry.getEnvironment())) {

				envSearchListTypes = getPortalEnvListTypes(
					listTypeId,
					AccountEnvironmentConstants.LIST_TYPE_ENV_SEARCH,
					"enterprise");
			}
			else {
				envSearchListTypes = getPortalEnvListTypes(
					listTypeId,
					AccountEnvironmentConstants.LIST_TYPE_ENV_SEARCH,
					"standard");
			}

			jsonObject.put(
				"envSearch", getEnvironmentArray(envSearchListTypes, locale));
		}

		jsonArray.put(jsonObject);

		return jsonArray;
	}

	private AccountEntry _accountEntry;
	private final AccountEntryDetailsRequestHelper
		_accountEntryDetailsRequestHelper;
	private final RenderRequest _renderRequest;
	private final RenderResponse _renderResponse;
	private ThemeDisplay _themeDisplay;

}