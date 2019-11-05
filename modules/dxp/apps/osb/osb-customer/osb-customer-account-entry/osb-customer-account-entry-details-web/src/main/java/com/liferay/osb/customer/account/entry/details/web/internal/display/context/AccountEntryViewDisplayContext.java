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
import com.liferay.osb.customer.constants.OSBCustomerConstants;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.AccountEnvironment;
import com.liferay.osb.model.AccountEnvironmentAttachment;
import com.liferay.osb.model.AccountEnvironmentAttachmentConstants;
import com.liferay.osb.model.AccountEnvironmentConstants;
import com.liferay.osb.model.OfferingEntry;
import com.liferay.osb.model.ProductEntry;
import com.liferay.osb.model.ProductEntryConstants;
import com.liferay.osb.service.AccountEntryServiceUtil;
import com.liferay.osb.service.AccountEnvironmentAttachmentLocalServiceUtil;
import com.liferay.osb.service.AccountEnvironmentLocalServiceUtil;
import com.liferay.osb.service.PartnerWorkerLocalServiceUtil;
import com.liferay.osb.service.ProductEntryLocalServiceUtil;
import com.liferay.osb.service.permission.OSBAccountEntryPermission;
import com.liferay.osb.service.permission.OSBAccountEnvironmentPermission;
import com.liferay.osb.util.OSBActionKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.ListType;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import javax.portlet.ActionRequest;
import javax.portlet.MimeResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.ResourceURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Amos Fong
 */
public class AccountEntryViewDisplayContext {

	public AccountEntryViewDisplayContext(
			PortletRequest portletRequest, MimeResponse mimeResponse)
		throws PortalException {

		_portletRequest = portletRequest;
		_mimeResponse = mimeResponse;

		AccountEntry accountEntry = (AccountEntry)_portletRequest.getAttribute(
			AccountEntryDetailsWebKeys.ACCOUNT_ENTRY);

		if (accountEntry == null) {
			long accountEntryId = ParamUtil.getLong(
				portletRequest, "accountEntryId");

			accountEntry = AccountEntryServiceUtil.getAccountEntry(
				accountEntryId);
		}

		_accountEntry = accountEntry;

		_accountEntryDetailsRequestHelper =
			new AccountEntryDetailsRequestHelper(portletRequest);

		_request = _accountEntryDetailsRequestHelper.getRequest();
		_themeDisplay = _accountEntryDetailsRequestHelper.getThemeDisplay();
	}

	public AccountEntry getAccountEntry() {
		return _accountEntry;
	}

	public String getAccountEntryInstructionsEditURL() throws PortalException {
		if (!OSBAccountEntryPermission.contains(
				_accountEntryDetailsRequestHelper.getPermissionChecker(),
				_accountEntry.getAccountEntryId(),
				OSBActionKeys.UPDATE_ACCOUNT_INFO) &&
			!OSBAccountEntryPermission.contains(
				_accountEntryDetailsRequestHelper.getPermissionChecker(),
				_accountEntry.getAccountEntryId(),
				OSBActionKeys.UPDATE_ACCOUNT_INSTRUCTIONS)) {

			return null;
		}

		PortletURL portletURL = _mimeResponse.createActionURL();

		portletURL.setParameter(
			ActionRequest.ACTION_NAME, "editAccountEntryInstructions");
		portletURL.setParameter(
			"redirect", _accountEntryDetailsRequestHelper.getCurrentURL());
		portletURL.setParameter(
			"accountEntryId",
			String.valueOf(_accountEntry.getAccountEntryId()));

		return portletURL.toString();
	}

	public String getAccountEnvironmentAddURL(AccountEntry accountEntry)
		throws PortletException {

		PortletURL portletURL = _mimeResponse.createActionURL();

		portletURL.setParameter(
			ActionRequest.ACTION_NAME, "editAccountEnvironment");
		portletURL.setParameter(
			"redirect", _accountEntryDetailsRequestHelper.getCurrentURL());
		portletURL.setParameter(
			"accountEntryId", String.valueOf(accountEntry.getAccountEntryId()));

		return portletURL.toString();
	}

	public JSONArray getAccountEnvironmentsJSONArray() throws Exception {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		List<AccountEnvironment> accountEnvironments =
			AccountEnvironmentLocalServiceUtil.getAccountEnvironments(
				_accountEntry.getAccountEntryId());

		for (AccountEnvironment accountEnvironment : accountEnvironments) {
			JSONObject jsonObject = getDisplayJSONObject(accountEnvironment);

			jsonArray.put(jsonObject);
		}

		return jsonArray;
	}

	public JSONObject getEnvCommerceJSONObject(
			Set<ListType> envCommerceVersions)
		throws Exception {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		Set<ListType> envLFRVersions = new HashSet<>();

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (ListType listType : envCommerceVersions) {
			List<ListType> envLFRListTypes =
				AccountEnvironmentConstants.getEnvListTypes(
					listType.getListTypeId(),
					ProductEntryConstants.
						LIST_TYPE_DIGITAL_ENTERPRISE_ALL_VERSIONS);

			envLFRVersions.addAll(envLFRListTypes);

			JSONObject envCommerceVersionJSONObject =
				JSONFactoryUtil.createJSONObject();

			JSONObject envCommerceVersionEnvironmentsJSONObject =
				JSONFactoryUtil.createJSONObject();

			envCommerceVersionEnvironmentsJSONObject.put(
				"envLFR", toJSONArray(envLFRListTypes));

			envCommerceVersionJSONObject.put(
				String.valueOf(listType.getListTypeId()),
				envCommerceVersionEnvironmentsJSONObject);

			jsonArray.put(envCommerceVersionJSONObject);
		}

		jsonObject.put("envCommerceVersions", jsonArray);

		jsonObject.put(
			"envLFRVersions",
			getEnvLFRVersionsJSONArray(envLFRVersions, "commerce"));

		return jsonObject;
	}

	public JSONObject getEnvironmentConfigurationJSONObject() throws Exception {
		List<String> productEntryDisplayNames = new ArrayList<>();

		JSONArray productsJSONArray = JSONFactoryUtil.createJSONArray();

		Set<ListType> envCommerceVersions = new HashSet<>();
		Set<ListType> envLFRVersions = new HashSet<>();

		for (OfferingEntry offeringEntry : _accountEntry.getOfferingEntries()) {
			ProductEntry productEntry = offeringEntry.getProductEntry();

			if (productEntryDisplayNames.contains(
					productEntry.getDisplayName())) {

				continue;
			}

			productEntryDisplayNames.add(productEntry.getDisplayName());

			productsJSONArray.put(getProductJSONObject(productEntry));

			if (productEntry.isCommerce()) {
				envCommerceVersions.addAll(
					getCurrentVersionsListTypes(productEntry));
			}
			else {
				envLFRVersions.addAll(
					getCurrentVersionsListTypes(productEntry));
			}
		}

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		if (!envCommerceVersions.isEmpty()) {
			jsonObject.put(
				"envCommerce", getEnvCommerceJSONObject(envCommerceVersions));
		}

		jsonObject.put(
			"envLFRVersions", getEnvLFRVersionsJSONArray(envLFRVersions));
		jsonObject.put("products", productsJSONArray);

		return jsonObject;
	}

	public boolean isLiferayContractorOrg() {
		if (OrganizationLocalServiceUtil.hasUserOrganization(
				_themeDisplay.getUserId(),
				OSBCustomerConstants.ORGANIZATION_LIFERAY_CONTRACTOR_ID)) {

			return true;
		}

		return false;
	}

	public boolean isLiferayIncOrg() {
		if (OrganizationLocalServiceUtil.hasUserOrganization(
				_themeDisplay.getUserId(),
				OSBCustomerConstants.ORGANIZATION_LIFERAY_INC_ID)) {

			return true;
		}

		return false;
	}

	public boolean isPartnerManagedSupportWorker() {
		if (!_accountEntry.isPartnerManagedSupport()) {
			return false;
		}

		if (PartnerWorkerLocalServiceUtil.hasPartnerWorker(
				_themeDisplay.getUserId(), _accountEntry.getPartnerEntryId())) {

			return true;
		}

		return false;
	}

	protected String getAccountEnvironmentAttachmentURL(
		AccountEnvironmentAttachment accountEnvironmentAttachment) {

		ResourceURL resourceURL = _mimeResponse.createResourceURL();

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

		PortletURL portletURL = _mimeResponse.createActionURL();

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

		PortletURL portletURL = _mimeResponse.createActionURL();

		portletURL.setParameter(
			ActionRequest.ACTION_NAME, "editAccountEnvironment");
		portletURL.setParameter(
			"redirect", _accountEntryDetailsRequestHelper.getCurrentURL());
		portletURL.setParameter(
			"accountEnvironmentId",
			String.valueOf(accountEnvironment.getAccountEnvironmentId()));

		return portletURL.toString();
	}

	protected List<ListType> getCurrentVersionsListTypes(
		ProductEntry productEntry) {

		List<ListType> productVersionListTypes = new ArrayList<>();

		IntStream intStream = Arrays.stream(
			ProductEntryConstants.LIST_TYPES_DEPRECATED);

		LongStream longStream = intStream.asLongStream();

		long[] deprecatedTypes = longStream.toArray();

		for (ListType listType : productEntry.getAllVersionsListTypes()) {
			if (ArrayUtil.contains(deprecatedTypes, listType.getListTypeId()) ||
				(listType.getListTypeId() ==
					(long)ProductEntryConstants.COMMERCE_VERSION_1_0) ||
				(listType.getListTypeId() ==
					(long)ProductEntryConstants.PORTAL_VERSION_OTHER)) {

				continue;
			}

			productVersionListTypes.add(listType);
		}

		return productVersionListTypes;
	}

	protected JSONObject getDisplayJSONObject(
			AccountEnvironment accountEnvironment)
		throws Exception {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put(
			"accountEnvironmentId",
			accountEnvironment.getAccountEnvironmentId());

		if (OSBAccountEnvironmentPermission.contains(
				_accountEntryDetailsRequestHelper.getPermissionChecker(),
				_accountEntry.getAccountEntryId(), OSBActionKeys.DELETE)) {

			jsonObject.put(
				"deleteAccountEnvironmentURL",
				getAccountEnvironmentDeleteURL(accountEnvironment));
		}

		if (OSBAccountEnvironmentPermission.contains(
				_accountEntryDetailsRequestHelper.getPermissionChecker(),
				_accountEntry.getAccountEntryId(), OSBActionKeys.UPDATE)) {

			jsonObject.put(
				"editAccountEnvironmentURL",
				getAccountEnvironmentEditURL(accountEnvironment));
		}

		jsonObject.put(
			"envASLabel",
			LanguageUtil.get(_request, accountEnvironment.getEnvASLabel()));
		jsonObject.put(
			"envBrowserLabel",
			LanguageUtil.get(
				_request, accountEnvironment.getEnvBrowserLabel()));
		jsonObject.put(
			"envCommerceLabel",
			LanguageUtil.get(
				_request, accountEnvironment.getEnvCommerceLabel()));
		jsonObject.put(
			"envCSLabel",
			LanguageUtil.get(_request, accountEnvironment.getEnvCSLabel()));
		jsonObject.put(
			"envDBLabel",
			LanguageUtil.get(_request, accountEnvironment.getEnvDBLabel()));
		jsonObject.put(
			"envJVMLabel",
			LanguageUtil.get(_request, accountEnvironment.getEnvJVMLabel()));
		jsonObject.put(
			"envLFRLabel",
			LanguageUtil.get(_request, accountEnvironment.getEnvLFRLabel()));
		jsonObject.put(
			"envSearchLabels",
			toEnvSearchJSONArray(accountEnvironment.getEnvSearchLabels()));

		String envOSLabel = LanguageUtil.get(
			_request, accountEnvironment.getEnvOSLabel());

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

	protected JSONObject getEnvLFRJSONObject(
			ListType listType, String... sublistType)
		throws Exception {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		long listTypeId = listType.getListTypeId();

		jsonObject.put("envLFR", toJSONArray(Arrays.asList(listType)));

		List<ListType> envASListTypes =
			AccountEnvironmentConstants.getEnvListTypes(
				listTypeId, AccountEnvironmentConstants.LIST_TYPE_ENV_AS,
				sublistType);

		jsonObject.put("envAS", toJSONArray(envASListTypes));

		List<ListType> envBrowserListTypes =
			AccountEnvironmentConstants.getEnvListTypes(
				listTypeId, AccountEnvironmentConstants.LIST_TYPE_ENV_BROWSER,
				sublistType);

		jsonObject.put("envBrowser", toJSONArray(envBrowserListTypes));

		if (ProductEntryConstants.isPortalVersion6_2(listTypeId) ||
			ProductEntryConstants.isDigitalEnterpriseVersion7_0(listTypeId) ||
			ProductEntryConstants.isDigitalEnterpriseVersion7_1(listTypeId) ||
			ProductEntryConstants.isDigitalEnterpriseVersion7_2(listTypeId)) {

			List<ListType> envCSListTypes =
				AccountEnvironmentConstants.getEnvListTypes(
					listTypeId, AccountEnvironmentConstants.LIST_TYPE_ENV_CS,
					sublistType);

			jsonObject.put("envCS", toJSONArray(envCSListTypes));
		}

		List<ListType> envDBListTypes =
			AccountEnvironmentConstants.getEnvListTypes(
				listTypeId, AccountEnvironmentConstants.LIST_TYPE_ENV_DB,
				sublistType);

		jsonObject.put("envDB", toJSONArray(envDBListTypes));

		List<ListType> envJVMListTypes =
			AccountEnvironmentConstants.getEnvListTypes(
				listTypeId, AccountEnvironmentConstants.LIST_TYPE_ENV_JVM,
				sublistType);

		jsonObject.put("envJVM", toJSONArray(envJVMListTypes));

		List<ListType> envOSListTypes =
			AccountEnvironmentConstants.getEnvListTypes(
				listTypeId, AccountEnvironmentConstants.LIST_TYPE_ENV_OS,
				sublistType);

		jsonObject.put("envOS", toJSONArray(envOSListTypes));

		if (ProductEntryConstants.isDigitalEnterpriseVersion7_0(listTypeId) ||
			ProductEntryConstants.isDigitalEnterpriseVersion7_1(listTypeId) ||
			ProductEntryConstants.isDigitalEnterpriseVersion7_2(listTypeId)) {

			JSONArray envSearchJSONArray = JSONFactoryUtil.createJSONArray();

			JSONObject envSearchEnterpriseJSONObject =
				JSONFactoryUtil.createJSONObject();

			List<ListType> envSearchEnterpriseListTypes =
				AccountEnvironmentConstants.getEnvListTypes(
					listTypeId,
					AccountEnvironmentConstants.LIST_TYPE_ENV_SEARCH,
					ArrayUtil.append(new String[] {"enterprise"}, sublistType));

			envSearchEnterpriseJSONObject.put(
				"enterprise", toJSONArray(envSearchEnterpriseListTypes));

			envSearchJSONArray.put(envSearchEnterpriseJSONObject);

			JSONObject envSearchStandardJSONObject =
				JSONFactoryUtil.createJSONObject();

			List<ListType> envSearchStandardListTypes =
				AccountEnvironmentConstants.getEnvListTypes(
					listTypeId,
					AccountEnvironmentConstants.LIST_TYPE_ENV_SEARCH,
					ArrayUtil.append(new String[] {"standard"}, sublistType));

			envSearchStandardJSONObject.put(
				"standard", toJSONArray(envSearchStandardListTypes));

			envSearchJSONArray.put(envSearchStandardJSONObject);

			jsonObject.put("envSearch", envSearchJSONArray);
		}

		return jsonObject;
	}

	protected JSONArray getEnvLFRVersionsJSONArray(
			Set<ListType> envLFRVersions, String... sublistType)
		throws Exception {

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (ListType listType : envLFRVersions) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put(
				String.valueOf(listType.getListTypeId()),
				getEnvLFRJSONObject(listType, sublistType));

			jsonArray.put(jsonObject);
		}

		return jsonArray;
	}

	protected JSONObject getProductJSONObject(ProductEntry productEntry)
		throws Exception {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("displayName", productEntry.getDisplayName());

		if (productEntry.isCommerce() || productEntry.isDigitalEnterprise()) {
			boolean enterpriseSearch =
				_accountEntry.hasEnterpriseSearchOffering(
					productEntry.getEnvironment());

			jsonObject.put("enterpriseSearch", enterpriseSearch);
		}

		jsonObject.put("productEntryId", productEntry.getProductEntryId());

		if (productEntry.isCommerce()) {
			jsonObject.put(
				"envCommerce",
				toJSONArray(getCurrentVersionsListTypes(productEntry)));
		}
		else {
			jsonObject.put(
				"envLFR",
				toJSONArray(getCurrentVersionsListTypes(productEntry)));
		}

		return jsonObject;
	}

	protected JSONArray toEnvSearchJSONArray(List<String> envSearches) {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (String envSearch : envSearches) {
			jsonArray.put(LanguageUtil.get(_request, envSearch));
		}

		return jsonArray;
	}

	protected JSONArray toJSONArray(List<ListType> listTypes) {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (ListType listType : listTypes) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put(
				"name", LanguageUtil.get(_request, listType.getName()));
			jsonObject.put("value", listType.getListTypeId());

			jsonArray.put(jsonObject);
		}

		return jsonArray;
	}

	private final AccountEntry _accountEntry;
	private final AccountEntryDetailsRequestHelper
		_accountEntryDetailsRequestHelper;
	private final MimeResponse _mimeResponse;
	private final PortletRequest _portletRequest;
	private final HttpServletRequest _request;
	private final ThemeDisplay _themeDisplay;

}