/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.marketplace.util;

import com.liferay.compat.portal.kernel.util.ArrayUtil;
import com.liferay.compat.portal.kernel.util.LocaleUtil;
import com.liferay.compat.portal.kernel.util.StringUtil;
import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.osb.AssetReceiptOwnerClassNameException;
import com.liferay.osb.SalesforceAddressException;
import com.liferay.osb.model.AppEntry;
import com.liferay.osb.model.AppVersion;
import com.liferay.osb.model.AssetLicense;
import com.liferay.osb.model.AssetLicenseConstants;
import com.liferay.osb.model.AssetReceiptLicense;
import com.liferay.osb.model.CorpProject;
import com.liferay.osb.service.AppEntryLocalServiceUtil;
import com.liferay.osb.service.AssetLicenseLocalServiceUtil;
import com.liferay.osb.service.AssetReceiptLicenseLocalServiceUtil;
import com.liferay.osb.service.ContractAuditLocalServiceUtil;
import com.liferay.osb.service.ContractEntryLocalServiceUtil;
import com.liferay.osb.service.CorpProjectLocalServiceUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.PortletPropsValues;
import com.liferay.osb.util.WorkflowConstants;
import com.liferay.portal.kernel.bean.BeanLocator;
import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.TextFormatter;
import com.liferay.portal.model.ListType;
import com.liferay.portal.model.Phone;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.SubscriptionSender;
import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.salesforce.service.ClpSerializer;
import com.liferay.salesforce.service.SalesforceLocalServiceUtil;
import com.liferay.salesforce.util.SalesforceMessageBuilder;
import com.liferay.salesforce.util.SalesforceObjectNames;
import com.liferay.util.ContentUtil;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletRequest;

/**
 * @author Peter Shin
 * @author Ryan Park
 * @author Joan Kim
 */
public class MarketplaceSOEEUtil {

	public static Locale getOrderLocale(String country) {
		return _instance._getOrderLocale(country);
	}

	public static Map<String, Double> getPriceMap(String country) {
		return _instance._getPriceMap(country);
	}

	public static boolean hasSourceCodePermission(long userId)
		throws PortalException, SystemException {

		return _instance._hasSourceCodePermission(userId);
	}

	public static boolean hasSourceCodePermission(
			String ownerClassName, long ownerClassPK)
		throws PortalException, SystemException {

		return _instance._hasSourceCodePermission(ownerClassName, ownerClassPK);
	}

	public static void updateSalesforce(
			long userId, String ownerClassName, long ownerClassPK,
			String productClassName, long productClassId, long contractEntryId,
			PortletRequest request)
		throws PortalException, SystemException {

		_instance._updateSalesforce(
			userId, ownerClassName, ownerClassPK, productClassName,
			productClassId, contractEntryId, request);
	}

	private MarketplaceSOEEUtil() {
		Map<String, Double> priceMap = new LinkedHashMap<String, Double>();

		priceMap.put("20-users-updates-only", 2000.00);
		priceMap.put("50-users-support-included", 4500.00);
		priceMap.put("100-users-support-included", 8000.00);
		priceMap.put("500-users-support-included", 12000.00);
		priceMap.put("2000-users-support-included", 18000.00);
		priceMap.put("10000-users-support-included", 25000.00);
		priceMap.put("unlimited-users-support-included", 32000.00);

		_priceMaps.put("AUD", priceMap);
		_priceMaps.put("EUR", priceMap);
		_priceMaps.put("USD", priceMap);

		priceMap = new LinkedHashMap<String, Double>();

		priceMap.put("20-users-updates-only", 4000.00);
		priceMap.put("50-users-support-included", 9000.00);
		priceMap.put("100-users-support-included", 16000.00);
		priceMap.put("500-users-support-included", 24000.00);
		priceMap.put("2000-users-support-included", 36000.00);
		priceMap.put("10000-users-support-included", 50000.00);
		priceMap.put("unlimited-users-support-included", 64000.00);

		_priceMaps.put("BRL", priceMap);

		priceMap = new LinkedHashMap<String, Double>();

		priceMap.put("20-users-updates-only", 12500.00);
		priceMap.put("50-users-support-included", 28125.00);
		priceMap.put("100-users-support-included", 50000.00);
		priceMap.put("500-users-support-included", 75000.00);
		priceMap.put("2000-users-support-included", 112500.00);
		priceMap.put("10000-users-support-included", 156250.00);
		priceMap.put("unlimited-users-support-included", 200000.00);

		_priceMaps.put("CNY", priceMap);

		priceMap = new LinkedHashMap<String, Double>();

		priceMap.put("20-users-updates-only", 1700.00);
		priceMap.put("50-users-support-included", 3825.00);
		priceMap.put("100-users-support-included", 6800.00);
		priceMap.put("500-users-support-included", 10200.00);
		priceMap.put("2000-users-support-included", 15300.00);
		priceMap.put("10000-users-support-included", 21250.00);
		priceMap.put("unlimited-users-support-included", 27200.00);

		_priceMaps.put("GBP", priceMap);

		priceMap = new LinkedHashMap<String, Double>();

		priceMap.put("20-users-updates-only", 100000.00);
		priceMap.put("50-users-support-included", 225000.00);
		priceMap.put("100-users-support-included", 400000.00);
		priceMap.put("500-users-support-included", 600000.00);
		priceMap.put("2000-users-support-included", 900000.00);
		priceMap.put("10000-users-support-included", 1250000.00);
		priceMap.put("unlimited-users-support-included", 1600000.00);

		_priceMaps.put("INR", priceMap);

		priceMap = new LinkedHashMap<String, Double>();

		priceMap.put("20-users-updates-only", 200000.00);
		priceMap.put("50-users-support-included", 450000.00);
		priceMap.put("100-users-support-included", 800000.00);
		priceMap.put("500-users-support-included", 1200000.00);
		priceMap.put("2000-users-support-included", 1800000.00);
		priceMap.put("10000-users-support-included", 2500000.00);
		priceMap.put("unlimited-users-support-included", 3200000.00);

		_priceMaps.put("JPY", priceMap);

		priceMap = new LinkedHashMap<String, Double>();

		priceMap.put("20-users-updates-only", 2500.00);
		priceMap.put("50-users-support-included", 5625.00);
		priceMap.put("100-users-support-included", 10000.00);
		priceMap.put("500-users-support-included", 15000.00);
		priceMap.put("2000-users-support-included", 22500.00);
		priceMap.put("10000-users-support-included", 31250.00);
		priceMap.put("unlimited-users-support-included", 40000.00);

		_priceMaps.put("NZD", priceMap);
	}

	private String _getData(String attributeName, ExpandoBridge expandoBridge) {
		Serializable serializable = expandoBridge.getAttribute(attributeName);

		if (serializable instanceof String) {
			return GetterUtil.getString(serializable, _DEFAULT_DATA);
		}
		else if (serializable instanceof String[]) {
			String[] array = (String[])serializable;

			if (ArrayUtil.isNotEmpty(array)) {
				return StringUtil.merge(array, StringPool.NEW_LINE);
			}
		}

		return _DEFAULT_DATA;
	}

	private String _getNotes(String country, String licenseType) {
		StringBundler sb = new StringBundler(5);

		sb.append("Country: ");
		sb.append(country);
		sb.append(StringPool.NEW_LINE);
		sb.append("Desired License: ");
		sb.append(licenseType);

		return sb.toString();
	}

	private Locale _getOrderLocale(String country) {
		if (country.equals("Australia")) {
			return new Locale("en", "AU");
		}
		else if (country.equals("Brazil")) {
			return LocaleUtil.BRAZIL;
		}
		else if (country.equals("China")) {
			return LocaleUtil.CHINA;
		}
		else if (country.equals("India")) {
			return new Locale("hi", "IN");
		}
		else if (country.equals("Japan")) {
			return LocaleUtil.JAPAN;
		}
		else if (country.equals("New Zealand")) {
			return new Locale("en", "NZ");
		}
		else if (country.equals("United Kingdom")) {
			return LocaleUtil.UK;
		}
		else if (country.equals("United States") ||
				 country.equals("United States Minor Outlying Islands")) {

			return LocaleUtil.US;
		}
		else if (ArrayUtil.contains(_EURO_CURRENCY_COUNTRIES, country)) {
			return LocaleUtil.GERMANY;
		}

		return LocaleUtil.US;
	}

	private String _getPhoneNumbers(User user) {
		List<Phone> phones = null;

		try {
			phones = user.getPhones();
		}
		catch (Exception e) {
			return _DEFAULT_DATA;
		}

		List<String> phoneNumbers = new ArrayList<String>();

		for (Phone phone : phones) {
			ListType listType = null;

			try {
				listType = phone.getType();
			}
			catch (Exception e) {
				continue;
			}

			StringBundler sb = new StringBundler(5);

			sb.append(phone.getNumber());
			sb.append(StringPool.DASH);
			sb.append(phone.getExtension());
			sb.append(StringPool.SPACE);
			sb.append(listType.getName());

			phoneNumbers.add(sb.toString());
		}

		return StringUtil.merge(phoneNumbers, StringPool.NEW_LINE);
	}

	private Map<String, Double> _getPriceMap(String country) {
		Locale locale = _getOrderLocale(country);

		Currency currency = Currency.getInstance(locale);

		return _priceMaps.get(currency.getCurrencyCode());
	}

	private String _getStreet(String street1, String street2, String street3) {
		String[] array = {street1, street2, street3};

		for (String value : array) {
			if (Validator.isNull(value)) {
				array = ArrayUtil.remove(array, value);
			}
		}

		return StringUtil.merge(array, StringPool.NEW_LINE);
	}

	private String _getToName(String toAddress) {
		int pos = toAddress.indexOf(StringPool.AT);

		if (pos == -1) {
			return toAddress;
		}

		return TextFormatter.format(
			toAddress.substring(0, pos), TextFormatter.J);
	}

	private boolean _hasSourceCodePermission(long userId)
		throws PortalException, SystemException {

		if (_hasSourceCodePermission(User.class.getName(), userId)) {
			return true;
		}

		List<CorpProject> corpProjects =
			CorpProjectLocalServiceUtil.getUserCorpProjects(userId);

		for (CorpProject corpProject : corpProjects) {
			if (_hasSourceCodePermission(
					CorpProject.class.getName(),
					corpProject.getCorpProjectId())) {

				return true;
			}
		}

		return false;
	}

	private boolean _hasSourceCodePermission(
			String ownerClassName, long ownerClassPK)
		throws PortalException, SystemException {

		List<AssetReceiptLicense> assetReceiptLicenses =
			AssetReceiptLicenseLocalServiceUtil.getAssetReceiptLicenses(
				ownerClassName, ownerClassPK,
				PortletPropsValues.MARKETPLACE_SO_EE_APP_ENTRY_TITLE);

		AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(
			PortletPropsValues.MARKETPLACE_SO_EE_APP_ENTRY_TITLE);

		AppVersion appVersion = appEntry.getApprovedAppVersion();

		List<AssetLicense> assetLicenses =
			AssetLicenseLocalServiceUtil.getAssetLicenses(
				AppVersion.class.getName(), appVersion.getAppVersionId(),
				AssetLicenseConstants.USAGE_TYPE_TRIAL,
				AssetLicenseConstants.LICENSE_TYPE_PRODUCTION,
				WorkflowConstants.STATUS_APPROVED, 0, 1, null);

		AssetLicense assetLicense = assetLicenses.get(0);

		for (AssetReceiptLicense assetReceiptLicense : assetReceiptLicenses) {
			if (assetReceiptLicense.getAssetLicenseId() ==
					assetLicense.getAssetLicenseId()) {

				continue;
			}

			if (!assetReceiptLicense.isExpired()) {
				return true;
			}
		}

		return false;
	}

	private void _updateSalesforce(
			long userId, String ownerClassName, long ownerClassPK,
			String productClassName, long productClassId, long contractEntryId,
			PortletRequest request)
		throws PortalException, SystemException {

		String firstName = ParamUtil.getString(request, "firstName");
		String lastName = ParamUtil.getString(request, "lastName");
		String emailAddress = ParamUtil.getString(request, "emailAddress");
		String city = ParamUtil.getString(request, "city");
		String country = ParamUtil.getString(request, "country");
		String region = ParamUtil.getString(request, "region");
		String street1 = ParamUtil.getString(request, "street1");
		String street2 = ParamUtil.getString(request, "street2");
		String street3 = ParamUtil.getString(request, "street3");
		String zip = ParamUtil.getString(request, "zip");
		String licenseType = ParamUtil.getString(request, "licenseType");

		_validate(
			userId, ownerClassName, ownerClassPK, contractEntryId, firstName,
			lastName, emailAddress, country, region, zip, city, street1,
			street2, street3, licenseType);

		String notes = _getNotes(country, licenseType);

		ContractAuditLocalServiceUtil.addContractAudit(
			userId, contractEntryId, ownerClassName, ownerClassPK,
			productClassName, productClassId);

		User user = UserLocalServiceUtil.getUser(userId);

		ExpandoBridge expandoBridge = user.getExpandoBridge();

		String osbCompany = _getData("osbCompany", expandoBridge);
		String osbCompanyRoles = _getData("osbCompanyRole", expandoBridge);
		String osbIndustries = _getData("osbIndustry", expandoBridge);
		String leadSource = "Liferay Marketplace Purchase";

		String phoneNumbers = _getPhoneNumbers(user);
		String street = _getStreet(street1, street2, street3);

		SalesforceMessageBuilder messageBuilder = new SalesforceMessageBuilder(
			SalesforceObjectNames.LEAD);

		messageBuilder.addAttribute("City", city);
		messageBuilder.addAttribute("Company", osbCompany);
		messageBuilder.addAttribute("Country", country);
		messageBuilder.addAttribute("Email", emailAddress);
		messageBuilder.addAttribute("FirstName", firstName);
		messageBuilder.addAttribute("Job_Role__c", osbCompanyRoles);
		messageBuilder.addAttribute("LastName", lastName);
		messageBuilder.addAttribute("Lead_Country__c", country);
		messageBuilder.addAttribute("Lead_State_Province__c", region);
		messageBuilder.addAttribute("LeadSource", leadSource);
		messageBuilder.addAttribute("NOTES__c", notes);
		messageBuilder.addAttribute("Phone", phoneNumbers);
		messageBuilder.addAttribute("PostalCode", zip);
		messageBuilder.addAttribute("State", region);
		messageBuilder.addAttribute("Street", street);

		String leadOid = null;

		String servletContextName = ClpSerializer.getServletContextName();

		BeanLocator beanLocator = PortletBeanLocatorUtil.getBeanLocator(
			servletContextName);

		if (beanLocator != null) {
			try {
				leadOid = SalesforceLocalServiceUtil.executeAdd(
					user.getCompanyId(), messageBuilder.getMessage());
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		else {
			_log.error("Salesforce portlet is not deployed");
		}

		String leadURL = _DEFAULT_DATA;

		if (Validator.isNotNull(leadOid)) {
			leadURL = "https://login.salesforce.com/" + leadOid;
		}

		String toAddress = PortletPropsValues.MARKETPLACE_SO_EE_EMAIL_ADDRESS;

		if (Validator.isNull(toAddress)) {
			return;
		}

		SubscriptionSender subscriptionSender = new SubscriptionSender();

		subscriptionSender.setBody(_BODY);
		subscriptionSender.setCompanyId(OSBConstants.COMPANY_ID);
		subscriptionSender.setContextAttribute("[$CITY$]", city);
		subscriptionSender.setContextAttribute("[$COMPANY$]", osbCompany);
		subscriptionSender.setContextAttribute(
			"[$COMPANY_ROLES$]", osbCompanyRoles);
		subscriptionSender.setContextAttribute("[$COUNTRY$]", country);
		subscriptionSender.setContextAttribute(
			"[$EMAIL_ADDRESS$]", emailAddress);
		subscriptionSender.setContextAttribute("[$FIRST_NAME$]", firstName);
		subscriptionSender.setContextAttribute("[$INDUSTRIES$]", osbIndustries);
		subscriptionSender.setContextAttribute("[$LAST_NAME$]", lastName);
		subscriptionSender.setContextAttribute("[$LEAD_COUNTRY$]", country);
		subscriptionSender.setContextAttribute("[$LEAD_REGION$]", region);
		subscriptionSender.setContextAttribute("[$LEAD_SOURCE$]", leadSource);
		subscriptionSender.setContextAttribute("[$LEAD_URL$]", leadURL);
		subscriptionSender.setContextAttribute("[$NOTES$]", notes, false);
		subscriptionSender.setContextAttribute(
			"[$PHONE_NUMBERS$]", phoneNumbers);
		subscriptionSender.setContextAttribute("[$STREET$]", street);
		subscriptionSender.setContextAttribute("[$ZIP$]", zip);
		subscriptionSender.setFrom("webmaster@liferay.com", "webmaster");
		subscriptionSender.setHtmlFormat(true);
		subscriptionSender.setMailId("sales_lead", user.getUserId());
		subscriptionSender.setReplyToAddress("webmaster@liferay.com");
		subscriptionSender.setSubject(_SUBJECT);

		subscriptionSender.addRuntimeSubscribers(
			toAddress, _getToName(toAddress));

		subscriptionSender.flushNotificationsAsync();
	}

	private void _validate(
			long userId, String ownerClassName, long ownerClassPK,
			long contractEntryId, String firstName, String lastName,
			String emailAddress, String country, String region, String zip,
			String city, String street1, String street2, String street3,
			String licenseType)
		throws PortalException, SystemException {

		User user = UserLocalServiceUtil.getUser(userId);

		if (user.isDefaultUser()) {
			throw new PrincipalException();
		}

		if (Validator.isNull(licenseType)) {
			throw new SalesforceAddressException(
				SalesforceAddressException.LICENSE_TYPE);
		}

		if (Validator.isNull(firstName) || Validator.isNull(lastName)) {
			throw new SalesforceAddressException(
				SalesforceAddressException.USER_NAME);
		}

		if (Validator.isNull(emailAddress)) {
			throw new SalesforceAddressException(
				SalesforceAddressException.EMAIL_ADDRESS);
		}

		if (Validator.isNull(_getStreet(street1, street2, street3))) {
			throw new SalesforceAddressException(
				SalesforceAddressException.ADDRESS_STREET);
		}

		if (Validator.isNull(country)) {
			throw new SalesforceAddressException(
				SalesforceAddressException.ADDRESS_COUNTRY);
		}

		if (Validator.isNull(city)) {
			throw new SalesforceAddressException(
				SalesforceAddressException.ADDRESS_CITY);
		}

		if (Validator.isNull(zip)) {
			throw new SalesforceAddressException(
				SalesforceAddressException.ADDRESS_ZIP);
		}

		ContractEntryLocalServiceUtil.getContractEntry(contractEntryId);

		if (Validator.isNull(ownerClassName)) {
			throw new AssetReceiptOwnerClassNameException();
		}

		if (ownerClassName.equals(CorpProject.class.getName())) {
			CorpProjectLocalServiceUtil.validateMembership(
				userId, ownerClassPK, OSBConstants.ROLE_OSB_CORP_BUYER_ID);
		}
		else if (ownerClassName.equals(User.class.getName())) {
			UserLocalServiceUtil.getUser(ownerClassPK);
		}
	}

	private static final String _BODY = ContentUtil.get(
		PortletPropsValues.MARKETPLACE_SO_EE_EMAIL_BODY);

	private static final String _DEFAULT_DATA = "N/A";

	private static final String[] _EURO_CURRENCY_COUNTRIES = new String[] {
		"Albania", "Andorra", "Austria", "Azerbaijan", "Belarus", "Belgium",
		"Bosnia and Herzegovina", "Bulgaria", "Croatia", "Cyprus",
		"Czech Republic", "Denmark", "Estonia", "Finland", "France", "Georgia",
		"Germany", "Greece", "Hungary", "Iceland", "Ireland", "Italy",
		"Kazakhstan", "Latvia", "Liechtenstein", "Lithuania", "Luxembourg",
		"Macedonia", "Malta", "Moldova, Republic of", "Monaco", "Montenegro",
		"Netherlands", "Norway", "Poland", "Portugal", "Romania",
		"Russian Federation", "San Marino", "Serbia", "Slovakia", "Slovenia",
		"Spain", "Sweden", "Switzerland", "Turkey", "Ukraine",
		"Vatican City State (Holy See)"
	};

	private static final String _SUBJECT = ContentUtil.get(
		PortletPropsValues.MARKETPLACE_SO_EE_EMAIL_SUBJECT);

	private static Log _log = LogFactoryUtil.getLog(MarketplaceSOEEUtil.class);

	private static MarketplaceSOEEUtil _instance = new MarketplaceSOEEUtil();

	private Map<String, Map<String, Double>> _priceMaps =
		new HashMap<String, Map<String, Double>>();

}