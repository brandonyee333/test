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

package com.liferay.osb.rabbitmq;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.osb.exception.NoSuchCorpProjectException;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.AccountEntryConstants;
import com.liferay.osb.model.AccountWorker;
import com.liferay.osb.model.AccountWorkerConstants;
import com.liferay.osb.model.CorpProject;
import com.liferay.osb.model.ExternalIdMapper;
import com.liferay.osb.model.ExternalIdMapperConstants;
import com.liferay.osb.model.OfferingDefinitionConstants;
import com.liferay.osb.model.OfferingEntry;
import com.liferay.osb.model.OfferingEntryConstants;
import com.liferay.osb.model.OrderEntry;
import com.liferay.osb.model.PartnerEntry;
import com.liferay.osb.model.ProductEntry;
import com.liferay.osb.model.ProductEntryConstants;
import com.liferay.osb.model.SupportRegion;
import com.liferay.osb.model.SupportResponse;
import com.liferay.osb.model.SupportResponseConstants;
import com.liferay.osb.model.impl.AccountWorkerImpl;
import com.liferay.osb.model.impl.CorpProjectImpl;
import com.liferay.osb.model.impl.OfferingEntryImpl;
import com.liferay.osb.model.impl.OrderEntryImpl;
import com.liferay.osb.service.AccountEntryLocalServiceUtil;
import com.liferay.osb.service.CorpProjectLocalServiceUtil;
import com.liferay.osb.service.ExternalIdMapperLocalServiceUtil;
import com.liferay.osb.service.PartnerEntryLocalServiceUtil;
import com.liferay.osb.service.ProductEntryLocalServiceUtil;
import com.liferay.osb.service.RemoteUserLocalServiceUtil;
import com.liferay.osb.service.SupportRegionLocalServiceUtil;
import com.liferay.osb.service.SupportResponseLocalServiceUtil;
import com.liferay.osb.support.util.SupportUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.PortletPropsValues;
import com.liferay.osb.util.SalesforceConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Address;
import com.liferay.portal.kernel.model.Country;
import com.liferay.portal.kernel.model.ListType;
import com.liferay.portal.kernel.model.ModelHintsUtil;
import com.liferay.portal.kernel.model.Region;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.AddressLocalServiceUtil;
import com.liferay.portal.kernel.service.CountryServiceUtil;
import com.liferay.portal.kernel.service.RegionServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.AutoResetThreadLocal;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StackTraceUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.SubscriptionSender;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.Validator;

import java.text.Format;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Amos Fong
 */
public abstract class ProvisioningRabbitMQConsumer implements RabbitMQConsumer {

	public void parse(
		String routingKey, String message, Map<String, Object> properties) {

		_warningMessagesThreadLocal.set(new ArrayList<String>());

		JSONObject jsonObject = null;

		try {
			jsonObject = JSONFactoryUtil.createJSONObject(message);

			doParse(jsonObject);
		}
		catch (Exception e) {
			sendErrorNotification(routingKey, message, jsonObject, e);
		}
	}

	protected ServiceContext createServiceContext(
			JSONObject jsonObject, List<OrderEntry> orderEntries)
		throws PortalException {

		ServiceContext serviceContext = new ServiceContext();

		if (hasAnalyticsCloud(orderEntries)) {
			ArrayList<User> analyticsCloudUsers = parseUsers(
				jsonObject, "Analytics Cloud Owner", false);

			serviceContext.setAttribute(
				"analyticsCloudUsers", analyticsCloudUsers);
		}

		serviceContext.setAttribute(
			"salesforceOpportunityStageName",
			jsonObject.getString("_salesforceOpportunityStageName"));

		int salesforceOpportunityType = getSalesforceOpportunityType(
			jsonObject.getString("_salesforceOpportunityType"));

		serviceContext.setAttribute(
			"salesforceOpportunityType", salesforceOpportunityType);

		ArrayList<String> warningMessages = _warningMessagesThreadLocal.get();

		serviceContext.setAttribute("warningMessages", warningMessages);

		return serviceContext;
	}

	protected abstract void doParse(JSONObject jsonObject)
		throws PortalException;

	protected User fetchRemoteUser(String emailAddress) throws PortalException {
		User remoteUser = RemoteUserLocalServiceUtil.fetchUserByEmailAddress(
			emailAddress);

		if (remoteUser == null) {
			return null;
		}

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setCreateDate(remoteUser.getCreateDate());
		serviceContext.setUuid(remoteUser.getUuid());

		User user = UserLocalServiceUtil.addUser(
			OSBConstants.USER_DEFAULT_USER_ID, OSBConstants.COMPANY_ID, true,
			StringPool.BLANK, StringPool.BLANK, false,
			remoteUser.getScreenName(), remoteUser.getEmailAddress(), 0,
			StringPool.BLANK, remoteUser.getLocale(), remoteUser.getFirstName(),
			remoteUser.getMiddleName(), remoteUser.getLastName(), 0, 0, false,
			0, 1, 1970, StringPool.BLANK, new long[0],
			remoteUser.getOrganizationIds(), remoteUser.getRoleIds(),
			new long[0], false, serviceContext);

		ExpandoBridge expandoBridge = user.getExpandoBridge();

		ExpandoBridge remoteExpandoBridge = remoteUser.getExpandoBridge();

		expandoBridge.setAttributes(remoteExpandoBridge.getAttributes(), false);

		return user;
	}

	protected SupportResponse fetchSupportResponse(
			JSONArray bundledProductsJSONArray)
		throws PortalException {

		boolean unlimitedEnterpriseWide = false;

		for (int i = 0; i < bundledProductsJSONArray.length(); i++) {
			JSONObject bundledProductJSONObject =
				bundledProductsJSONArray.getJSONObject(i);

			String name = bundledProductJSONObject.getString("_name");

			SupportResponse supportResponse = fetchSupportResponse(name);

			if (supportResponse != null) {
				return supportResponse;
			}

			ProductEntry productEntry = getProductEntry(name);

			if ((productEntry != null) &&
				productEntry.isUnlimitedEnterpriseWide()) {

				unlimitedEnterpriseWide = true;
			}
		}

		if (unlimitedEnterpriseWide) {
			return SupportResponseLocalServiceUtil.fetchSupportResponseByName(
				"Platinum");
		}
		else {
			return null;
		}
	}

	protected SupportResponse fetchSupportResponse(String name) {
		if (Validator.isNull(name)) {
			return null;
		}

		if (!name.contains("Support")) {
			return null;
		}

		String[] nameArray = StringUtil.split(name, StringPool.SPACE);

		return SupportResponseLocalServiceUtil.fetchSupportResponseByName(
			nameArray[0]);
	}

	protected Address getAddress(JSONObject jsonObject) {
		String city = jsonObject.getString("_city");
		String countryName = jsonObject.getString("_country");
		String postalCode = jsonObject.getString("_postalCode");
		String regionName = jsonObject.getString("_region");
		String street = jsonObject.getString("_street");

		Address address = AddressLocalServiceUtil.createAddress(0);

		city = ModelHintsUtil.trimString(Address.class.getName(), "city", city);

		address.setCity(city);

		if (Validator.isNotNull(countryName)) {
			try {
				Country country = CountryServiceUtil.getCountryByName(
					countryName);

				address.setCountryId(country.getCountryId());

				List<Region> regions = RegionServiceUtil.getRegions(
					country.getCountryId());

				for (Region region : regions) {
					if (regionName.equals(region.getName())) {
						address.setRegionId(region.getRegionId());

						break;
					}
				}
			}
			catch (Exception e) {
			}
		}

		String street1 = street;
		String street2 = StringPool.BLANK;
		String street3 = StringPool.BLANK;

		int maxLength = ModelHintsUtil.getMaxLength(
			Address.class.getName(), "street1");

		if (street1.length() > maxLength) {
			street1 = street1.substring(0, maxLength);

			street2 = street.substring(maxLength);

			if (street2.length() > maxLength) {
				street2 = street2.substring(0, maxLength);

				street3 = street.substring(maxLength * 2);

				if (street3.length() > maxLength) {
					street3 = street3.substring(0, maxLength);
				}
			}
		}

		address.setStreet1(street1);
		address.setStreet2(street2);
		address.setStreet3(street3);

		postalCode = ModelHintsUtil.trimString(
			Address.class.getName(), "zip", postalCode);

		address.setZip(postalCode);

		return address;
	}

	protected int getIndustry(JSONObject accountJSONObject) {
		String industry = accountJSONObject.getString("_industry");

		int industryListTypeId = SupportUtil.getListTypeIdFromName(
			AccountEntryConstants.LIST_TYPE_INDUSTRY, industry, true);

		if (industryListTypeId > 0) {
			if (StringUtil.equalsIgnoreCase(industry, "other")) {
				_logWarning("Industry is Other");
			}

			return industryListTypeId;
		}
		else {
			_logWarning("Unable to find industry: " + industry);

			return AccountEntryConstants.INDUSTRY_OTHER;
		}
	}

	protected String getLanguageId(JSONObject jsonObject, String countryName) {
		String soldBy = jsonObject.getString("_salesforceOpportunitySoldBy");

		if (Validator.isNull(soldBy)) {
			_logWarning(
				"Sold by field is empty. Defaulting support language to " +
					"English.");

			return AccountEntryConstants.LANGUAGE_ID_ENGLISH;
		}

		if (soldBy.equals("Liferay Australia") ||
			soldBy.equals("Liferay Canada") ||
			soldBy.equals("Liferay France") ||
			soldBy.equals("Liferay Germany") ||
			soldBy.equals("Liferay Hungary") ||
			soldBy.equals("Liferay India") ||
			soldBy.equals("Liferay International") ||
			soldBy.equals("Liferay Middle East") ||
			soldBy.equals("Liferay Morocco") ||
			soldBy.equals("Liferay Netherlands") ||
			soldBy.equals("Liferay Singapore") || soldBy.equals("Liferay UK") ||
			soldBy.equals("Liferay US")) {

			return AccountEntryConstants.LANGUAGE_ID_ENGLISH;
		}
		else if (soldBy.equals("Liferay Brazil")) {
			if (Validator.isNotNull(countryName) &&
				countryName.equals("Brazil")) {

				return AccountEntryConstants.LANGUAGE_ID_PORTUGUESE;
			}
			else {
				return AccountEntryConstants.LANGUAGE_ID_SPANISH;
			}
		}
		else if (soldBy.equals("Liferay China")) {
			if (Validator.isNotNull(countryName) &&
				countryName.equals("China")) {

				return AccountEntryConstants.LANGUAGE_ID_CHINESE;
			}
			else {
				return AccountEntryConstants.LANGUAGE_ID_ENGLISH;
			}
		}
		else if (soldBy.equals("Liferay Japan")) {
			return AccountEntryConstants.LANGUAGE_ID_JAPANESE;
		}
		else if (soldBy.equals("Liferay Spain")) {
			if (Validator.isNotNull(countryName) &&
				(countryName.equals("Cyprus") || countryName.equals("Greece") ||
				 countryName.equals("Italy") ||
				 countryName.equals("Portugal"))) {

				return AccountEntryConstants.LANGUAGE_ID_ENGLISH;
			}
			else {
				return AccountEntryConstants.LANGUAGE_ID_SPANISH;
			}
		}

		_logWarning(
			"Unable to find matching support language for " + soldBy + " and " +
				countryName + ". Defaulting support language to English.");

		return AccountEntryConstants.LANGUAGE_ID_ENGLISH;
	}

	protected boolean getLicenses(
		ProductEntry productEntry, int salesforceOpportunityType) {

		if (productEntry.isDigitalEnterprise() || productEntry.isPortal()) {
			if (productEntry.isPortal() &&
				(salesforceOpportunityType ==
					SalesforceConstants.OPPORTUNITY_TYPE_RENEWAL)) {

				return false;
			}
			else {
				return true;
			}
		}
		else {
			return false;
		}
	}

	protected String getNotes(
			JSONObject jsonObject, List<OrderEntry> orderEntries)
		throws PortalException {

		Map<String, Map<String, Integer>> subscriptionsMap = new TreeMap<>();

		for (OrderEntry orderEntry : orderEntries) {
			for (OfferingEntry offeringEntry :
					orderEntry.getOfferingEntries()) {

				String key = getNotesDateRange(orderEntry, offeringEntry);

				Map<String, Integer> productsMap = subscriptionsMap.get(key);

				if (productsMap == null) {
					productsMap = new TreeMap<>();

					subscriptionsMap.put(key, productsMap);
				}

				String productKey = getNotesProductName(offeringEntry);

				int quantity = GetterUtil.getInteger(
					productsMap.get(productKey));

				quantity += offeringEntry.getQuantity();

				productsMap.put(productKey, quantity);
			}
		}

		StringBundler sb = new StringBundler();

		for (Map.Entry<String, Map<String, Integer>> entry :
				subscriptionsMap.entrySet()) {

			String dateRange = entry.getKey();
			Map<String, Integer> productsMap = entry.getValue();

			sb.append("Subscriptions:");
			sb.append(StringPool.NEW_LINE);

			for (Map.Entry<String, Integer> productsEntry :
					productsMap.entrySet()) {

				sb.append(StringPool.TAB);
				sb.append(productsEntry.getKey());
				sb.append(" (");
				sb.append(productsEntry.getValue());
				sb.append(")");
				sb.append(StringPool.NEW_LINE);
			}

			sb.append("Dates: ");
			sb.append(dateRange);
			sb.append(StringPool.NEW_LINE);
			sb.append(StringPool.NEW_LINE);
		}

		JSONObject ownerJSONObject = jsonObject.getJSONObject("_owner");

		sb.append("Owner: ");
		sb.append(ownerJSONObject.getString("_firstName"));
		sb.append(StringPool.SPACE);
		sb.append(ownerJSONObject.getString("_lastName"));
		sb.append(StringPool.NEW_LINE);

		sb.append("SFDC: https://login.salesforce.com/");
		sb.append(jsonObject.getString("_salesforceOpportunityKey"));

		return sb.toString();
	}

	protected String getNotesDateRange(
		OrderEntry orderEntry, OfferingEntry offeringEntry) {

		StringBundler sb = new StringBundler(4);

		sb.append(_dateFormat.format(orderEntry.getStartDate()));
		sb.append(" - ");
		sb.append(_dateFormat.format(offeringEntry.getSupportEndDate()));
		sb.append(" (UTC)");

		return sb.toString();
	}

	protected String getNotesProductName(OfferingEntry offeringEntry)
		throws PortalException {

		StringBundler sb = new StringBundler(7);

		SupportResponse supportResponse = offeringEntry.getSupportResponse();

		sb.append(supportResponse.getName());

		sb.append(StringPool.SPACE);

		ProductEntry productEntry = offeringEntry.getProductEntry();

		sb.append(productEntry.getName());

		sb.append(StringPool.SPACE);

		if (Validator.isNotNull(offeringEntry.getSizingLabel())) {
			sb.append(
				LanguageUtil.get(Locale.US, offeringEntry.getSizingLabel()));
		}

		if (Validator.isNotNull(offeringEntry.getProductDescription())) {
			sb.append(" - ");
			sb.append(offeringEntry.getProductDescription());
		}

		return sb.toString();
	}

	protected String getProductDescription(String name) {
		name = StringUtil.toLowerCase(name);

		if (name.contains("additional jvm")) {
			return "Additional JVM";
		}
		else if (name.endsWith("partner")) {
			return "Partner";
		}
		else {
			return StringPool.BLANK;
		}
	}

	protected ProductEntry getProductEntry(String name) throws PortalException {
		long classNameId = PortalUtil.getClassNameId(ProductEntry.class);

		List<ExternalIdMapper> externalIdMappers =
			ExternalIdMapperLocalServiceUtil.getExternalIdMappers(
				classNameId, ExternalIdMapperConstants.TYPE_DOSSIERA, name);

		if (!externalIdMappers.isEmpty()) {
			ExternalIdMapper externalIdMapper = externalIdMappers.get(0);

			return ProductEntryLocalServiceUtil.getProductEntry(
				externalIdMapper.getClassPK());
		}

		return ProductEntryLocalServiceUtil.fetchProductEntryByName(name);
	}

	protected int getSalesforceOpportunityType(
		String salesforceOpportunityTypeName) {

		if (StringUtil.equalsIgnoreCase(
				salesforceOpportunityTypeName, "Existing Business")) {

			return SalesforceConstants.OPPORTUNITY_TYPE_EXISTING_BUSINESS;
		}
		else if (StringUtil.equalsIgnoreCase(
					salesforceOpportunityTypeName, "New Business")) {

			return SalesforceConstants.OPPORTUNITY_TYPE_NEW_BUSINESS;
		}
		else if (StringUtil.equalsIgnoreCase(
					salesforceOpportunityTypeName, "Renewal")) {

			return SalesforceConstants.OPPORTUNITY_TYPE_RENEWAL;
		}
		else if (StringUtil.equalsIgnoreCase(
					salesforceOpportunityTypeName,
					"New Project Existing Business")) {

			return SalesforceConstants.
				OPPORTUNITY_TYPE_NEW_PROJECT_EXISTING_BUSINESS;
		}
		else {
			return 0;
		}
	}

	protected int getSizing(ProductEntry productEntry, String sizing) {
		if (Validator.isNotNull(sizing)) {
			if (sizing.equals("Sizing 1")) {
				return OfferingEntryConstants.SIZING_1;
			}
			else if (sizing.equals("Sizing 2")) {
				return OfferingEntryConstants.SIZING_2;
			}
			else if (sizing.equals("Sizing 3")) {
				return OfferingEntryConstants.SIZING_3;
			}
			else if (sizing.equals("Sizing 4")) {
				return OfferingEntryConstants.SIZING_4;
			}
		}

		if (productEntry.isDigitalEnterprise() || productEntry.isPortal()) {
			_logWarning(
				"No valid sizing was found for " + productEntry.getName() +
					". Defaulting to sizing 1.");

			return OfferingEntryConstants.SIZING_1;
		}

		return 0;
	}

	protected long[] getSupportRegionIds(
		JSONObject jsonObject, String countryName) {

		String soldBy = jsonObject.getString("_salesforceOpportunitySoldBy");

		String supportRegionName = getSupportRegionName(soldBy, countryName);

		SupportRegion supportRegion =
			SupportRegionLocalServiceUtil.fetchSupportRegionByName(
				supportRegionName);

		if (supportRegion != null) {
			return new long[] {supportRegion.getSupportRegionId()};
		}

		return new long[0];
	}

	protected String getSupportRegionName(String soldBy, String countryName) {
		if (Validator.isNull(soldBy)) {
			_logWarning(
				"Sold by field is empty. Defaulting support region to global.");

			return "Global";
		}

		if (soldBy.equals("Liferay Australia")) {
			return "Australia";
		}
		else if (soldBy.equals("Liferay Brazil")) {
			return "Brazil";
		}
		else if (soldBy.equals("Liferay Canada") ||
				 soldBy.equals("Liferay US")) {

			return "US";
		}
		else if (soldBy.equals("Liferay China") ||
				 soldBy.equals("Liferay Singapore")) {

			return "China";
		}
		else if (soldBy.equals("Liferay France") ||
				 soldBy.equals("Liferay Germany") ||
				 soldBy.equals("Liferay Hungary") ||
				 soldBy.equals("Liferay International") ||
				 soldBy.equals("Liferay Middle East") ||
				 soldBy.equals("Liferay Morocco") ||
				 soldBy.equals("Liferay Netherlands") ||
				 soldBy.equals("Liferay UK")) {

			return "Hungary";
		}
		else if (soldBy.equals("Liferay India")) {
			return "India";
		}
		else if (soldBy.equals("Liferay Japan")) {
			return "Japan";
		}
		else if (soldBy.equals("Liferay Spain")) {
			if (Validator.isNotNull(countryName) &&
				(countryName.equals("Cyprus") || countryName.equals("Greece") ||
				 countryName.equals("Italy"))) {

				return "Hungary";
			}
			else {
				return "Spain";
			}
		}

		_logWarning(
			"Unable to find matching support region for " + soldBy + " and " +
				countryName + ". Defaulting support region to global.");

		return "Global";
	}

	protected boolean getSupportTickets(SupportResponse supportResponse) {
		if (supportResponse == null) {
			return false;
		}

		if ((supportResponse.getSupportLevel() ==
				SupportResponseConstants.SUPPORT_LEVEL_FLOATING) ||
			(supportResponse.getSupportLevel() ==
				SupportResponseConstants.SUPPORT_LEVEL_GOLD) ||
			(supportResponse.getSupportLevel() ==
				SupportResponseConstants.SUPPORT_LEVEL_PLATINUM)) {

			return true;
		}

		return false;
	}

	protected int getVersion(ProductEntry productEntry) {
		if (Validator.isNull(productEntry.getVersionsListType())) {
			return 0;
		}

		List<ListType> listTypes = productEntry.getVersionsListTypes();

		if (listTypes.isEmpty()) {
			return 0;
		}

		ListType listType = listTypes.get(listTypes.size() - 1);

		return (int)listType.getListTypeId();
	}

	protected boolean hasAnalyticsCloud(List<OrderEntry> orderEntries)
		throws PortalException {

		for (OrderEntry orderEntry : orderEntries) {
			List<OfferingEntry> offeringEntries =
				orderEntry.getOfferingEntries();

			for (OfferingEntry offeringEntry : offeringEntries) {
				ProductEntry productEntry = offeringEntry.getProductEntry();

				if (productEntry.isAnalyticsCloudBusiness() ||
					productEntry.isAnalyticsCloudEnterprise()) {

					return true;
				}
			}
		}

		return false;
	}

	protected boolean hasOpportunityProductFamily(JSONObject jsonObject) {
		String salesforceOpportunityProductFamily = jsonObject.getString(
			"_salesforceOpportunityProductFamily");

		if (Validator.isNull(salesforceOpportunityProductFamily)) {
			return false;
		}

		for (String productFamilyToken :
				PortletPropsValues.
					PROVISIONING_OPPORTUNITY_PRODUCT_FAMILY_TOKENS) {

			if (salesforceOpportunityProductFamily.contains(
					productFamilyToken)) {

				return true;
			}
		}

		return false;
	}

	protected boolean hasUnlimitedEnterpriseWide(List<OrderEntry> orderEntries)
		throws PortalException {

		for (OrderEntry orderEntry : orderEntries) {
			List<OfferingEntry> offeringEntries =
				orderEntry.getOfferingEntries();

			for (OfferingEntry offeringEntry : offeringEntries) {
				ProductEntry productEntry = offeringEntry.getProductEntry();

				if (productEntry.isUnlimitedEnterpriseWide()) {
					return true;
				}
			}
		}

		return false;
	}

	protected AccountEntry parseAccountEntry(
			JSONObject jsonObject, Address address, CorpProject corpProject,
			List<OrderEntry> orderEntries)
		throws PortalException {

		JSONObject accountJSONObject = jsonObject.getJSONObject("_account");

		int industry = getIndustry(accountJSONObject);

		boolean partnerManagedSupport = jsonObject.getBoolean(
			"_partnerFirstLineSupport");
		String notes = getNotes(jsonObject, orderEntries);

		Country country = address.getCountry();

		String languageId = getLanguageId(jsonObject, country.getName());
		long[] supportRegionIds = getSupportRegionIds(
			jsonObject, country.getName());

		AccountEntry accountEntry = null;

		if (Validator.isNotNull(corpProject.getUuid())) {
			accountEntry =
				AccountEntryLocalServiceUtil.fetchCorpProjectAccountEntry(
					corpProject.getUuid());
		}

		if (accountEntry == null) {
			accountEntry = AccountEntryLocalServiceUtil.createAccountEntry(0);

			accountEntry.setUserId(OSBConstants.USER_AMOS_FONG_USER_ID);
			accountEntry.setDossieraAccountKey(
				accountJSONObject.getString("_dossieraAccountKey"));
			accountEntry.setCorpEntryName(accountJSONObject.getString("_name"));
			accountEntry.setName(corpProject.getName());
			accountEntry.setType(AccountEntryConstants.TYPE_GROUP);
			accountEntry.setIndustry(industry);
			accountEntry.setPartnerManagedSupport(partnerManagedSupport);
			accountEntry.setTier(AccountEntryConstants.TIER_REGULAR);
			accountEntry.setMaxCustomers(10);
			accountEntry.setNotes(notes);
			accountEntry.setLanguageIds(new String[] {languageId});
			accountEntry.setSupportRegionIds(supportRegionIds);
		}
		else {
			accountEntry.setDossieraAccountKey(
				accountJSONObject.getString("_dossieraAccountKey"));
			accountEntry.setCorpEntryName(accountJSONObject.getString("_name"));
			accountEntry.setName(corpProject.getName());
			accountEntry.setIndustry(industry);
			accountEntry.setPartnerManagedSupport(partnerManagedSupport);
			accountEntry.setNotes(notes);
			accountEntry.setLanguageIds(new String[] {languageId});
			accountEntry.setSupportRegionIds(supportRegionIds);
		}

		return accountEntry;
	}

	protected AccountWorker parseAccountWorker(
		JSONObject jsonObject, AccountEntry accountEntry) {

		JSONObject ownerJSONObject = jsonObject.getJSONObject("_owner");

		User ownerUser = UserLocalServiceUtil.fetchUserByEmailAddress(
			OSBConstants.COMPANY_ID,
			ownerJSONObject.getString("_emailAddress"));

		if (ownerUser == null) {
			return null;
		}

		AccountWorker accountWorker = new AccountWorkerImpl();

		accountWorker.setUserId(ownerUser.getUserId());
		accountWorker.setRole(AccountWorkerConstants.ROLE_SALES);
		accountWorker.setNotifications(
			AccountWorkerConstants.NOTIFICATIONS_ALL);

		return accountWorker;
	}

	protected Address parseAddress(JSONObject jsonObject) {
		JSONObject billingAddressJSONObject = jsonObject.getJSONObject(
			"_billingAddress");
		JSONObject shippingAddressJSONObject = jsonObject.getJSONObject(
			"_shippingAddress");

		Address address = null;

		if (shippingAddressJSONObject != null) {
			address = getAddress(shippingAddressJSONObject);
		}
		else if (billingAddressJSONObject != null) {
			address = getAddress(billingAddressJSONObject);
		}
		else {
			address = AddressLocalServiceUtil.createAddress(0);
		}

		if (Validator.isNull(address.getCity())) {
			address.setCity("N/A");
		}

		if (Validator.isNull(address.getStreet1())) {
			address.setStreet1("N/A");
		}

		if (Validator.isNull(address.getZip())) {
			address.setZip("N/A");
		}

		return address;
	}

	protected CorpProject parseCorpProject(JSONObject jsonObject)
		throws PortalException {

		int salesforceOpportunityType = getSalesforceOpportunityType(
			jsonObject.getString("_salesforceOpportunityType"));

		JSONObject projectJSONObject = jsonObject.getJSONObject("_project");

		if (projectJSONObject == null) {
			throw new NoSuchCorpProjectException();
		}

		String dossieraProjectKey = projectJSONObject.getString(
			"_dossieraProjectKey");
		String name = projectJSONObject.getString("_name");

		name = StringUtil.shorten(name, 150);

		CorpProject corpProject = CorpProjectLocalServiceUtil.fetchCorpProject(
			dossieraProjectKey);

		if (corpProject == null) {
			if ((salesforceOpportunityType ==
					SalesforceConstants.OPPORTUNITY_TYPE_EXISTING_BUSINESS) ||
				(salesforceOpportunityType ==
					SalesforceConstants.OPPORTUNITY_TYPE_RENEWAL)) {

				String salesforceOpportunityTypeLabel = LanguageUtil.get(
					LocaleUtil.getDefault(),
					SalesforceConstants.getOpportunityTypeLabel(
						salesforceOpportunityType));

				_logWarning(
					"The opportunity type is " +
						salesforceOpportunityTypeLabel +
							" and the project does not exist");
			}

			String salesforceProjectKey = jsonObject.getString(
				"_salesforceProjectKey");

			corpProject = new CorpProjectImpl();

			corpProject.setUserId(OSBConstants.USER_AMOS_FONG_USER_ID);
			corpProject.setDossieraProjectKey(dossieraProjectKey);
			corpProject.setSalesforceProjectKey(salesforceProjectKey);
			corpProject.setName(name);
		}
		else {
			if ((salesforceOpportunityType ==
					SalesforceConstants.OPPORTUNITY_TYPE_NEW_BUSINESS) ||
				(salesforceOpportunityType ==
					SalesforceConstants.
						OPPORTUNITY_TYPE_NEW_PROJECT_EXISTING_BUSINESS)) {

				String salesforceOpportunityTypeLabel = LanguageUtil.get(
					LocaleUtil.getDefault(),
					SalesforceConstants.getOpportunityTypeLabel(
						salesforceOpportunityType));

				_logWarning(
					"The opportunity type is " +
						salesforceOpportunityTypeLabel +
							" and the project already exists");
			}

			corpProject.setName(name);
		}

		return corpProject;
	}

	protected List<OfferingEntry> parseOfferingEntries(
			int salesforceOpportunityType, Date startDate, Date endDate,
			JSONArray purchasedProductsJSONArray)
		throws PortalException {

		SupportResponse supportResponse = fetchSupportResponse(
			purchasedProductsJSONArray);

		List<OfferingEntry> offeringEntries = new ArrayList<>();

		for (int i = 0; i < purchasedProductsJSONArray.length(); i++) {
			JSONObject purchasedProductJSONObject =
				purchasedProductsJSONArray.getJSONObject(i);

			String name = purchasedProductJSONObject.getString("_name");
			int quantity = purchasedProductJSONObject.getInt("_quantity");
			String sizing = purchasedProductJSONObject.getString("_sizing");

			if (supportResponse != null) {
				SupportResponse curSupportResponse = fetchSupportResponse(name);

				if (curSupportResponse != null) {
					continue;
				}
			}

			ProductEntry productEntry = getProductEntry(name);

			if (productEntry == null) {
				_logWarning("Unable to find match for product: " + name);

				continue;
			}

			if ((productEntry.getType() !=
					ProductEntryConstants.TYPE_PRIMARY) &&
				(endDate == null)) {

				endDate = new Date(
					startDate.getTime() +
						OfferingDefinitionConstants.LIFETIME_INDEFINITE_VALUE);
			}

			String productDescription = getProductDescription(name);
			int version = getVersion(productEntry);
			boolean licenses = getLicenses(
				productEntry, salesforceOpportunityType);
			long supportLifetime = endDate.getTime() - startDate.getTime();
			int sizingValue = getSizing(productEntry, sizing);

			if (quantity <= 0) {
				quantity = 1;
			}

			OfferingEntry offeringEntry = new OfferingEntryImpl();

			offeringEntry.setProductEntryId(productEntry.getProductEntryId());

			SupportResponse curSupportResponse = null;

			if (supportResponse != null) {
				curSupportResponse = supportResponse;
			}
			else if (productEntry.getType() ==
						ProductEntryConstants.TYPE_ADD_ON) {

				curSupportResponse =
					SupportResponseLocalServiceUtil.fetchSupportResponseByName(
						"Floating");
			}
			else if (productEntry.getType() ==
						ProductEntryConstants.TYPE_PRIMARY) {

				curSupportResponse =
					SupportResponseLocalServiceUtil.fetchSupportResponseByName(
						"Limited");
			}
			else {
				curSupportResponse =
					SupportResponseLocalServiceUtil.fetchSupportResponseByName(
						"Not Applicable");
			}

			offeringEntry.setSupportResponseId(
				curSupportResponse.getSupportResponseId());
			offeringEntry.setProductDescription(productDescription);
			offeringEntry.setType(OfferingEntryConstants.TYPE_REGULAR);
			offeringEntry.setVersion(version);
			offeringEntry.setLicenses(licenses);

			if (productEntry.isUnlimitedEnterpriseWide()) {
				offeringEntry.setLicenseLifetime(supportLifetime);
			}
			else {
				offeringEntry.setLicenseLifetime(
					OfferingDefinitionConstants.LIFETIME_INDEFINITE_VALUE);
			}

			offeringEntry.setSupportTickets(
				getSupportTickets(curSupportResponse));
			offeringEntry.setSupportLifetime(supportLifetime);
			offeringEntry.setSupportEndDate(endDate);
			offeringEntry.setSizing(sizingValue);
			offeringEntry.setQuantity(quantity);
			offeringEntry.setStatus(OfferingEntryConstants.STATUS_ACTIVE);

			offeringEntries.add(offeringEntry);
		}

		return offeringEntries;
	}

	protected List<OrderEntry> parseOrderEntries(JSONObject jsonObject)
		throws PortalException {

		JSONArray bundledProductsJSONArray = jsonObject.getJSONArray(
			"_bundledProducts");

		if (bundledProductsJSONArray == null) {
			return Collections.emptyList();
		}

		int salesforceOpportunityType = getSalesforceOpportunityType(
			jsonObject.getString("_salesforceOpportunityType"));

		Map<Long, OrderEntry> orderEntries = new HashMap<>();

		for (int i = 0; i < bundledProductsJSONArray.length(); i++) {
			JSONObject bundledProductJSONObject =
				bundledProductsJSONArray.getJSONObject(i);

			int endDateDay = bundledProductJSONObject.getInt("_endDay");
			int endDateMonth = bundledProductJSONObject.getInt("_endMonth") - 1;
			int endDateYear = bundledProductJSONObject.getInt("_endYear");
			int startDateDay = bundledProductJSONObject.getInt("_startDay");
			int startDateMonth =
				bundledProductJSONObject.getInt("_startMonth") - 1;
			int startDateYear = bundledProductJSONObject.getInt("_startYear");
			JSONArray purchasedProducts = bundledProductJSONObject.getJSONArray(
				"_purchasedProducts");

			Date startDate = PortalUtil.getDate(
				startDateMonth, startDateDay, startDateYear);

			Date endDate = PortalUtil.getDate(
				endDateMonth, endDateDay, endDateYear);

			if (endDate != null) {
				endDate = new Date(endDate.getTime() + Time.DAY);
			}

			List<OfferingEntry> offeringEntries = parseOfferingEntries(
				salesforceOpportunityType, startDate, endDate,
				purchasedProducts);

			if (offeringEntries.isEmpty()) {
				continue;
			}

			OrderEntry orderEntry = orderEntries.get(startDate.getTime());

			if (orderEntry != null) {
				List<OfferingEntry> orderEntryOfferingEntries =
					orderEntry.getOfferingEntries();

				orderEntryOfferingEntries.addAll(offeringEntries);
			}
			else {
				orderEntry = new OrderEntryImpl();

				orderEntry.setUserId(OSBConstants.USER_DEFAULT_USER_ID);
				orderEntry.setStartDate(startDate);
				orderEntry.setOfferingEntries(offeringEntries);

				orderEntries.put(startDate.getTime(), orderEntry);
			}
		}

		return new ArrayList<>(orderEntries.values());
	}

	protected PartnerEntry parsePartnerEntry(JSONObject jsonObject) {
		JSONObject partnerAccountJSONObject = jsonObject.getJSONObject(
			"_partnerAccount");

		if (partnerAccountJSONObject == null) {
			return null;
		}

		String dossieraAccountKey = partnerAccountJSONObject.getString(
			"_dossieraAccountKey");
		String name = partnerAccountJSONObject.getString("_name");

		PartnerEntry partnerEntry =
			PartnerEntryLocalServiceUtil.fetchPartnerEntry(dossieraAccountKey);

		if (partnerEntry == null) {
			_logWarning(
				"Unable to find partner " + name + " with key " +
					dossieraAccountKey);
		}

		return partnerEntry;
	}

	protected ArrayList<User> parseUsers(JSONObject jsonObject)
		throws PortalException {

		return parseUsers(jsonObject, "Analytics Cloud Owner", true);
	}

	protected ArrayList<User> parseUsers(
			JSONObject jsonObject, String filterRoleName, boolean excludeFilter)
		throws PortalException {

		JSONArray contactsJSONArray = jsonObject.getJSONArray("_contacts");

		if (contactsJSONArray == null) {
			_logWarning("No contacts were found");

			return new ArrayList<>();
		}

		ArrayList<User> users = new ArrayList<>(contactsJSONArray.length());

		for (int i = 0; i < contactsJSONArray.length(); i++) {
			JSONObject contactJSONObject = contactsJSONArray.getJSONObject(i);

			String role = contactJSONObject.getString("_role");

			if (Validator.isNotNull(filterRoleName) &&
				((role.equals(filterRoleName) && excludeFilter) ||
				 (!role.equals(filterRoleName) && !excludeFilter))) {

				continue;
			}

			String firstName = contactJSONObject.getString("_firstName");
			String lastName = contactJSONObject.getString("_lastName");
			String emailAddress = contactJSONObject.getString("_emailAddress");

			User user = UserLocalServiceUtil.fetchUserByEmailAddress(
				OSBConstants.COMPANY_ID, emailAddress);

			if (user == null) {
				user = fetchRemoteUser(emailAddress);
			}

			if (user == null) {
				user = UserLocalServiceUtil.createUser(0);

				user.setScreenName("N/A");
				user.setEmailAddress(emailAddress);
				user.setFirstName(firstName);
				user.setLastName(lastName);
			}

			users.add(user);
		}

		return users;
	}

	protected void sendErrorNotification(
		String routingKey, String message, JSONObject jsonObject, Exception e) {

		StringBundler sb = new StringBundler(8);

		if (e instanceof NoSuchCorpProjectException) {
			sb.append("The corp project information is missing.");
		}
		else {
			sb.append("An unexpected error occurred.");
		}

		sb.append("<br />Routing Key: ");
		sb.append(routingKey);
		sb.append("<br />Message:<br /><pre>");
		sb.append(message);
		sb.append("</pre><br />Error:<br /><pre>");
		sb.append(StackTraceUtil.getStackTrace(e));
		sb.append("</pre>");

		_log.error("Sending error notification: " + sb.toString());

		SubscriptionSender subscriptionSender = new SubscriptionSender();

		subscriptionSender.setBody(sb.toString());
		subscriptionSender.setCompanyId(OSBConstants.COMPANY_ID);
		subscriptionSender.setFrom(
			PortletPropsValues.AUTOMATIC_PROVISIONING_ERROR_EMAIL_ADDRESS,
			"provisioning");
		subscriptionSender.setHtmlFormat(true);
		subscriptionSender.setMailId("provisioning");
		subscriptionSender.setReplyToAddress(
			PortletPropsValues.AUTOMATIC_PROVISIONING_ERROR_EMAIL_ADDRESS);
		subscriptionSender.setSubject("Auto-Provisioning Error");

		subscriptionSender.addRuntimeSubscribers(
			PortletPropsValues.AUTOMATIC_PROVISIONING_ERROR_EMAIL_ADDRESS,
			"provisioning");

		subscriptionSender.flushNotificationsAsync();
	}

	private void _logWarning(String s) {
		List<String> warningMessages = _warningMessagesThreadLocal.get();

		warningMessages.add(s);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ProvisioningRabbitMQConsumer.class);

	private static final ThreadLocal<ArrayList<String>>
		_warningMessagesThreadLocal = new AutoResetThreadLocal<>(
			ProvisioningRabbitMQConsumer.class + "._warningMessagesThreadLocal",
			new ArrayList<String>());

	private final Format _dateFormat =
		FastDateFormatFactoryUtil.getSimpleDateFormat("yyyy/MM/dd");

}