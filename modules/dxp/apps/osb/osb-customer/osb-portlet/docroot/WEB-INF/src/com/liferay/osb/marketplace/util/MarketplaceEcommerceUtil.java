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

import com.liferay.compat.portal.kernel.util.BigDecimalUtil;
import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.ecommerce.access.messaging.ECAccessUtil;
import com.liferay.ecommerce.model.ECDocumentEntry;
import com.liferay.ecommerce.model.ECDocumentEntryConstants;
import com.liferay.ecommerce.model.ECDocumentItem;
import com.liferay.ecommerce.service.ECDocumentEntryLocalServiceUtil;
import com.liferay.ecommerce.service.ECDocumentEntryServiceUtil;
import com.liferay.ecommerce.service.ECDocumentItemLocalServiceUtil;
import com.liferay.ecommerce.service.ECDocumentItemServiceUtil;
import com.liferay.osb.AddressCompanyNameException;
import com.liferay.osb.AssetReceiptEmailAddressException;
import com.liferay.osb.AssetReceiptLegalEntityNameException;
import com.liferay.osb.AssetReceiptOwnerClassNameException;
import com.liferay.osb.ContractAuditAcceptanceException;
import com.liferay.osb.ExpiredAppPricingItemException;
import com.liferay.osb.NoSuchAssetLicenseException;
import com.liferay.osb.RequiredCotermException;
import com.liferay.osb.RequiredEndUserEmailAddressException;
import com.liferay.osb.RequiredEndUserFirstNameException;
import com.liferay.osb.RequiredEndUserLastNameException;
import com.liferay.osb.RequiredEndUserPhoneNumberException;
import com.liferay.osb.RequiredQuantityException;
import com.liferay.osb.RequiredRenewalException;
import com.liferay.osb.RequiredTaxException;
import com.liferay.osb.TrialLicenseException;
import com.liferay.osb.model.AppEntry;
import com.liferay.osb.model.AppPricing;
import com.liferay.osb.model.AppPricingItem;
import com.liferay.osb.model.AppVersion;
import com.liferay.osb.model.AssetLicense;
import com.liferay.osb.model.AssetLicenseConstants;
import com.liferay.osb.model.AssetReceipt;
import com.liferay.osb.model.AssetReceiptLicense;
import com.liferay.osb.model.AssetReceiptSupport;
import com.liferay.osb.model.ContractEntry;
import com.liferay.osb.model.ContractEntryConstants;
import com.liferay.osb.model.CorpProject;
import com.liferay.osb.model.CountryAppPricing;
import com.liferay.osb.model.DeveloperEntry;
import com.liferay.osb.service.AppEntryLocalServiceUtil;
import com.liferay.osb.service.AppPricingItemLocalServiceUtil;
import com.liferay.osb.service.AppPricingLocalServiceUtil;
import com.liferay.osb.service.AppVersionLocalServiceUtil;
import com.liferay.osb.service.AssetLicenseLocalServiceUtil;
import com.liferay.osb.service.AssetReceiptLicenseLocalServiceUtil;
import com.liferay.osb.service.AssetReceiptLocalServiceUtil;
import com.liferay.osb.service.AssetReceiptServiceUtil;
import com.liferay.osb.service.AssetReceiptSupportLocalServiceUtil;
import com.liferay.osb.service.ContractEntryLocalServiceUtil;
import com.liferay.osb.service.CountryAppPricingLocalServiceUtil;
import com.liferay.osb.service.DeveloperEntryLocalServiceUtil;
import com.liferay.osb.service.permission.OSBCorpProjectPermission;
import com.liferay.osb.util.CurrencyUtil;
import com.liferay.osb.util.OSBActionKeys;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.PortletPropsValues;
import com.liferay.osb.util.WorkflowConstants;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Address;
import com.liferay.portal.model.Contact;
import com.liferay.portal.model.Country;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.security.permission.PermissionThreadLocal;
import com.liferay.portal.service.AddressLocalServiceUtil;
import com.liferay.portal.service.AddressServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.expando.model.ExpandoBridge;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Ryan Park
 * @author Douglas Wong
 * @author Joan Kim
 * @author Haote Chou
 */
public class MarketplaceEcommerceUtil {

	public static final String MANUAL_TAX_PROCESSOR_CLASS_NAME =
		"com.liferay.ecommerce.tax.processor.ManualTaxProcessor";

	public static final String PAY_PAL_PAYMENT_PROCESSOR_CLASS_NAME =
		"com.liferay.ecommerce.payment.processor.PayPalPaymentProcessor";

	public static ECDocumentItem deleteECDocumentItem(long ecDocumentItemId)
		throws PortalException, SystemException {

		ECDocumentItem ecDocumentItem =
			ECDocumentItemLocalServiceUtil.getECDocumentItem(ecDocumentItemId);

		if (ecDocumentItem.getClassPK() > 0) {
			return ECDocumentItemServiceUtil.deleteECDocumentItem(
				ecDocumentItemId);
		}

		ECDocumentItemExtraSettings ecDocumentItemExtraSettings =
			new ECDocumentItemExtraSettings(ecDocumentItem);

		ECDocumentEntry ecDocumentEntry =
			ECDocumentEntryLocalServiceUtil.getECDocumentEntry(
				ecDocumentItem.getEcDocumentEntryId());

		ECDocumentEntryExtraSettings ecDocumentEntryExtraSettings =
			new ECDocumentEntryExtraSettings(ecDocumentEntry);

		AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(
			ecDocumentEntryExtraSettings.getAppEntryId());

		AppVersion appVersion = appEntry.getApprovedAppVersion();

		if (hasECDocumentEntryUsageType(
				ecDocumentItem.getEcDocumentEntryId(),
				appVersion.getAppVersionId(),
				ecDocumentItemExtraSettings.getUsageType())) {

			throw new UnsupportedOperationException();
		}

		return ECDocumentItemServiceUtil.deleteECDocumentItem(ecDocumentItemId);
	}

	public static boolean hasECDocumentEntryUsageType(
			long ecDocumentEntryId, long appVersionId, int usageType)
		throws PortalException, SystemException {

		List<AssetLicense> assetLicenses =
			AssetLicenseLocalServiceUtil.getAssetLicenses(
				AppVersion.class.getName(), appVersionId, usageType,
				AssetLicenseConstants.LICENSE_TYPE_PRODUCTION,
				WorkflowConstants.STATUS_APPROVED, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null);

		for (AssetLicense assetLicense : assetLicenses) {
			int count = ECDocumentItemLocalServiceUtil.getECDocumentItemsCount(
				ecDocumentEntryId, AssetLicense.class.getName(),
				assetLicense.getAssetLicenseId());

			if (count > 0) {
				return true;
			}
		}

		return false;
	}

	public static boolean isFATCAReportable(
			AppEntry appEntry, Country shippingAddressCountry)
		throws SystemException {

		String shippingAddressCountryA2 = shippingAddressCountry.getA2();

		DeveloperEntry developerEntry = appEntry.getDeveloperEntry();

		String developerEntryCountryA2 = developerEntry.getCountryCode();

		if ((appEntry.getLicenseLifetime() ==
				AssetLicenseConstants.LIFETIME_SUBSCRIPTION) &&
			shippingAddressCountryA2.equals("US") &&
			!developerEntryCountryA2.equals("US")) {

			return true;
		}
		else {
			return false;
		}
	}

	public static boolean isIndependentDeveloper(DeveloperEntry developerEntry)
		throws SystemException {

		String countryA2 = developerEntry.getCountryCode();

		if (countryA2.equals("US") && developerEntry.isUser()) {
			return true;
		}
		else {
			return false;
		}
	}

	public static ECDocumentEntry purchaseAppEntryFree(
			long userId, long appEntryId, String ownerClassName,
			long ownerClassPK, long countryId, int domain,
			boolean acceptContract, String legalEntityName)
		throws PortalException, SystemException {

		User user = UserLocalServiceUtil.getUserById(userId);

		AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(appEntryId);

		AppVersion appVersion = appEntry.getApprovedAppVersion();

		validatePayment(
			null, appVersion.getAppVersionId(), ownerClassName, acceptContract,
			legalEntityName);

		AssetLicense assetLicense = null;

		if (!appEntry.isFree() || appEntry.isSOEEPlugin()) {
			validateTrial(appEntry, ownerClassName, ownerClassPK);

			List<AssetLicense> assetLicenses =
				AssetLicenseLocalServiceUtil.getAssetLicenses(
					AppVersion.class.getName(), appVersion.getAppVersionId(),
					AssetLicenseConstants.USAGE_TYPE_TRIAL,
					AssetLicenseConstants.LICENSE_TYPE_PRODUCTION,
					WorkflowConstants.STATUS_APPROVED, 0, 1, null);

			if (assetLicenses.isEmpty()) {
				throw new NoSuchAssetLicenseException();
			}

			assetLicense = assetLicenses.get(0);
		}

		long eulaContractEntryId = 0;
		long tosContractEntryId = 0;

		if (acceptContract) {
			ContractEntry eulaContractEntry = appVersion.getContractEntry();

			eulaContractEntryId = eulaContractEntry.getContractEntryId();

			ContractEntry tosContractEntry =
				ContractEntryLocalServiceUtil.getLatestContractEntry(
					ContractEntryConstants.DEFAULT_CLASS_NAME_ID,
					ContractEntryConstants.DEFAULT_CLASS_PK,
					ContractEntryConstants.TYPE_TERMS_OF_SERVICE);

			tosContractEntryId = tosContractEntry.getContractEntryId();
		}

		ECDocumentEntryExtraSettings ecDocumentEntryExtraSettings =
			new ECDocumentEntryExtraSettings();

		ecDocumentEntryExtraSettings.setAppEntryId(appEntryId);
		ecDocumentEntryExtraSettings.setCountryId(countryId);
		ecDocumentEntryExtraSettings.setDeveloperEntryId(
			appEntry.getDeveloperEntryId());
		ecDocumentEntryExtraSettings.setDomain(domain);
		ecDocumentEntryExtraSettings.setEulaContractEntryId(
			eulaContractEntryId);
		ecDocumentEntryExtraSettings.setLegalEntityName(legalEntityName);
		ecDocumentEntryExtraSettings.setOwnerClassPK(ownerClassPK);
		ecDocumentEntryExtraSettings.setOwnerClassName(ownerClassName);
		ecDocumentEntryExtraSettings.setPurchaseType("new");
		ecDocumentEntryExtraSettings.setProductType("app-entry");
		ecDocumentEntryExtraSettings.setResale(false);
		ecDocumentEntryExtraSettings.setTosContractEntryId(tosContractEntryId);

		DeveloperEntry developerEntry = appEntry.getDeveloperEntry();

		ECDocumentEntry ecDocumentEntry =
			ECDocumentEntryServiceUtil.addECDocumentEntry(
				OSBConstants.GROUP_GUEST_ID,
				ECommerceConstants.STORE_NAME_MARKETPLACE,
				DeveloperEntry.class.getName(),
				developerEntry.getDeveloperEntryId(), userId,
				user.getEmailAddress(), null, null, null, null, null, 0, 0,
				null, null, null, null, null, 0, countryId, StringPool.BLANK,
				null, StringPool.BLANK, MANUAL_TAX_PROCESSOR_CLASS_NAME, 0,
				ecDocumentEntryExtraSettings.toString(), null);

		if (assetLicense != null) {
			ECDocumentItemExtraSettings ecDocumentItemExtraSettings =
				new ECDocumentItemExtraSettings();

			Date startDate = new Date();

			if (assetLicense.getLifetime() <
					AssetLicenseConstants.LIFETIME_PERPETUAL) {

				Date endDate = new Date(
					startDate.getTime() + assetLicense.getLifetime());

				ecDocumentItemExtraSettings.setEndDate(endDate);
			}

			ecDocumentItemExtraSettings.setItemType("license");
			ecDocumentItemExtraSettings.setPurchaseType("new");
			ecDocumentItemExtraSettings.setStartDate(startDate);
			ecDocumentItemExtraSettings.setUsageType(
				AssetLicenseConstants.USAGE_TYPE_TRIAL);

			String ecDocumentItemName = getECDocumentItemName(
				appVersion, assetLicense);

			ECDocumentItemLocalServiceUtil.addECDocumentItem(
				ecDocumentEntry.getEcDocumentEntryId(),
				ECommerceConstants.EC_PRODUCT_TYPE_ID_MARKETPLACE_APP,
				AssetLicense.class.getName(), assetLicense.getAssetLicenseId(),
				ecDocumentItemName, "USD", 0, 1,
				ecDocumentItemExtraSettings.toString());
		}
		else {
			ECDocumentItemLocalServiceUtil.addECDocumentItem(
				ecDocumentEntry.getEcDocumentEntryId(),
				ECommerceConstants.EC_PRODUCT_TYPE_ID_MARKETPLACE_APP,
				AppEntry.class.getName(), appEntry.getAppEntryId(),
				appEntry.getTitle(), "USD", 0, 1, null);
		}

		ecDocumentEntry =
			ECDocumentEntryLocalServiceUtil.updateECDocumentEntryTax(
				ecDocumentEntry.getEcDocumentEntryId());

		return ECDocumentEntryLocalServiceUtil.updateECDocumentEntry(
			ecDocumentEntry.getEcDocumentEntryId(),
			ECDocumentEntryConstants.TYPE_INVOICE,
			ECDocumentEntryConstants.STATUS_PAID);
	}

	public static ECDocumentEntry purchaseDeveloperSubscription(
			long userId, long developerEntryId)
		throws PortalException, SystemException {

		DeveloperEntry developerEntry =
			DeveloperEntryLocalServiceUtil.getDeveloperEntry(developerEntryId);

		ECDocumentEntryExtraSettings ecDocumentEntryExtraSettings =
			new ECDocumentEntryExtraSettings();

		ecDocumentEntryExtraSettings.setCompanyName(
			developerEntry.getScreenName());
		ecDocumentEntryExtraSettings.setDeveloperEntryId(developerEntryId);
		ecDocumentEntryExtraSettings.setProductType("developer-subscription");

		ECDocumentEntry ecDocumentEntry =
			ECDocumentEntryServiceUtil.addECDocumentEntry(
				OSBConstants.GROUP_GUEST_ID,
				ECommerceConstants.STORE_NAME_MARKETPLACE,
				DeveloperEntry.class.getName(), developerEntryId, userId,
				developerEntry.getEmailAddress(), developerEntry.getStreet1(),
				developerEntry.getStreet2(), developerEntry.getStreet3(),
				developerEntry.getCity(), developerEntry.getZip(),
				developerEntry.getRegionId(), developerEntry.getCountryId(),
				developerEntry.getStreet1(), developerEntry.getStreet2(),
				developerEntry.getStreet3(), developerEntry.getCity(),
				developerEntry.getZip(), developerEntry.getRegionId(),
				developerEntry.getCountryId(), StringPool.BLANK, null, null,
				null, 0, ecDocumentEntryExtraSettings.toString(), null);

		ECDocumentItemLocalServiceUtil.addECDocumentItem(
			ecDocumentEntry.getEcDocumentEntryId(),
			ECommerceConstants.EC_PRODUCT_ENTRY_ID_MARKETPLACE_SUBSCRIPTION, 1);

		return ecDocumentEntry;
	}

	public static ECDocumentEntry updateECDocumentEntryAssetLicenseCoterm(
			long ecDocumentEntryId, boolean cotermStandardAssetLicense,
			boolean cotermDeveloperAssetLicense)
		throws PortalException, SystemException {

		ECDocumentEntry ecDocumentEntry =
			ECDocumentEntryServiceUtil.getECDocumentEntry(ecDocumentEntryId);

		ECDocumentEntryExtraSettings ecDocumentEntryExtraSettings =
			new ECDocumentEntryExtraSettings(ecDocumentEntry);

		AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(
			ecDocumentEntryExtraSettings.getAppEntryId());

		AssetReceipt assetReceipt =
			AssetReceiptLocalServiceUtil.getAssetReceipt(
				ecDocumentEntryExtraSettings.getOwnerClassName(),
				ecDocumentEntryExtraSettings.getOwnerClassPK(),
				AppEntry.class.getName(), appEntry.getAppEntryId());

		validateLicenseCoterm(
			ecDocumentEntry, assetReceipt, appEntry, cotermStandardAssetLicense,
			cotermDeveloperAssetLicense);

		if (assetReceipt.hasRenewedAssetReceiptLicenses(
				AssetLicenseConstants.USAGE_TYPE_STANDARD)) {

			addECDocumentItemLicenseCoterm(
				ecDocumentEntry, assetReceipt,
				ecDocumentEntryExtraSettings.getCountryId(),
				appEntry.getApprovedAppVersion(),
				AssetLicenseConstants.USAGE_TYPE_STANDARD);
		}
		else {
			addECDocumentItemLicenseExtensionCoterm(
				ecDocumentEntry, assetReceipt,
				ecDocumentEntryExtraSettings.getCountryId(),
				ecDocumentEntryExtraSettings.getAppEntryId(),
				AssetLicenseConstants.USAGE_TYPE_STANDARD,
				cotermStandardAssetLicense);
		}

		if (assetReceipt.hasRenewedAssetReceiptLicenses(
				AssetLicenseConstants.USAGE_TYPE_DEVELOPER)) {

			addECDocumentItemLicenseCoterm(
				ecDocumentEntry, assetReceipt,
				ecDocumentEntryExtraSettings.getCountryId(),
				appEntry.getApprovedAppVersion(),
				AssetLicenseConstants.USAGE_TYPE_DEVELOPER);
		}
		else {
			addECDocumentItemLicenseExtensionCoterm(
				ecDocumentEntry, assetReceipt,
				ecDocumentEntryExtraSettings.getCountryId(),
				ecDocumentEntryExtraSettings.getAppEntryId(),
				AssetLicenseConstants.USAGE_TYPE_DEVELOPER,
				cotermDeveloperAssetLicense);
		}

		return ecDocumentEntry;
	}

	public static ECDocumentEntry updateECDocumentEntryDestination(
			long userId, long ecDocumentEntryId, long addressId)
		throws PortalException, SystemException {

		ECDocumentEntry ecDocumentEntry =
			ECDocumentEntryLocalServiceUtil.getECDocumentEntry(
				ecDocumentEntryId);

		ECDocumentEntryExtraSettings ecDocumentEntryExtraSettings =
			new ECDocumentEntryExtraSettings(ecDocumentEntry);

		Address address = AddressLocalServiceUtil.getAddress(addressId);

		ExpandoBridge expandoBridge = address.getExpandoBridge();

		String companyName = GetterUtil.getString(
			expandoBridge.getAttribute("osbCompanyName", false));
		String vatNumber = GetterUtil.getString(
			expandoBridge.getAttribute("vatNumber", false));

		validateDestination(
			ecDocumentEntryExtraSettings.getOwnerClassName(), companyName);

		ecDocumentEntryExtraSettings.setAddressId(address.getAddressId());
		ecDocumentEntryExtraSettings.setCompanyName(companyName);

		return ECDocumentEntryServiceUtil.updateECDocumentEntry(
			ecDocumentEntry.getEcDocumentEntryId(), ecDocumentEntry.getType(),
			userId, ecDocumentEntry.getBillingEmailAddress(),
			ecDocumentEntry.getBillingAddressStreet1(),
			ecDocumentEntry.getBillingAddressStreet2(),
			ecDocumentEntry.getBillingAddressStreet3(),
			ecDocumentEntry.getBillingAddressCity(),
			ecDocumentEntry.getBillingAddressZip(),
			ecDocumentEntry.getBillingAddressRegionId(),
			ecDocumentEntry.getBillingAddressCountryId(), address.getStreet1(),
			address.getStreet2(), address.getStreet3(), address.getCity(),
			address.getZip(), address.getRegionId(), address.getCountryId(),
			vatNumber, ecDocumentEntry.getComment(),
			ecDocumentEntry.getPaymentProcessor(),
			ecDocumentEntry.getTaxProcessor(),
			ecDocumentEntry.getEcAccessKeyId(),
			ecDocumentEntryExtraSettings.toString());
	}

	public static ECDocumentEntry updateECDocumentEntryDestination(
			long userId, long ecDocumentEntryId, long addressId, long contactId,
			String street1, String street2, String street3, String city,
			String zip, long regionId, long countryId, String companyName,
			String vatNumber)
		throws PortalException, SystemException {

		Address address = null;

		if (addressId > 0) {
			address = AddressLocalServiceUtil.getAddress(addressId);

			address = AddressServiceUtil.updateAddress(
				addressId, street1, street2, street3, city, zip, regionId,
				countryId, address.getTypeId(), address.getMailing(),
				address.getPrimary());
		}
		else {
			address = AddressServiceUtil.addAddress(
				Contact.class.getName(), contactId, street1, street2, street3,
				city, zip, regionId, countryId,
				OSBConstants.BUSINESS_LIST_TYPE_ID, true, false);
		}

		ExpandoBridge expandoBridge = address.getExpandoBridge();

		expandoBridge.setAttribute("osbCompanyName", companyName, false);
		expandoBridge.setAttribute("vatNumber", vatNumber, false);

		return updateECDocumentEntryDestination(
			userId, ecDocumentEntryId, address.getAddressId());
	}

	public static ECDocumentEntry updateECDocumentEntryEndUser(
			long ecDocumentEntryId, String emailAddress, String firstName,
			String lastName, String phoneNumber, boolean emailReceipt)
		throws PortalException, SystemException {

		validateEndUser(emailAddress, firstName, lastName, phoneNumber);

		ECDocumentEntry ecDocumentEntry =
			ECDocumentEntryLocalServiceUtil.getECDocumentEntry(
				ecDocumentEntryId);

		ECDocumentEntryExtraSettings ecDocumentEntryExtraSettings =
			new ECDocumentEntryExtraSettings(ecDocumentEntry);

		ecDocumentEntryExtraSettings.setEndUserEmailAddress(emailAddress);
		ecDocumentEntryExtraSettings.setEndUserFirstName(firstName);
		ecDocumentEntryExtraSettings.setEndUserLastName(lastName);
		ecDocumentEntryExtraSettings.setEndUserPhone(phoneNumber);
		ecDocumentEntryExtraSettings.setEndUserEmailReceipt(emailReceipt);

		User endUser = UserLocalServiceUtil.fetchUserByEmailAddress(
			OSBConstants.COMPANY_ID, emailAddress);

		if (endUser != null) {
			ecDocumentEntryExtraSettings.setEndUserId(endUser.getUserId());
		}

		return ECDocumentEntryServiceUtil.updateECDocumentEntry(
			ecDocumentEntryId, ecDocumentEntryExtraSettings.toString());
	}

	public static ECDocumentEntry updateECDocumentEntryPayment(
			long ecDocumentEntryId, String paymentMethod,
			boolean acceptContract, int domain)
		throws PortalException, SystemException {

		ECDocumentEntry ecDocumentEntry =
			ECDocumentEntryServiceUtil.getECDocumentEntry(ecDocumentEntryId);

		ECDocumentEntryExtraSettings ecDocumentEntryExtraSettings =
			new ECDocumentEntryExtraSettings(ecDocumentEntry);

		AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(
			ecDocumentEntryExtraSettings.getAppEntryId());

		AppVersion appVersion = appEntry.getApprovedAppVersion();

		String ownerClassName =
			ecDocumentEntryExtraSettings.getOwnerClassName();

		String legalEntityName = StringPool.BLANK;

		if (ownerClassName.equals(CorpProject.class.getName())) {
			Address address = AddressLocalServiceUtil.getAddress(
				ecDocumentEntryExtraSettings.getAddressId());

			ExpandoBridge expandoBridge = address.getExpandoBridge();

			legalEntityName = GetterUtil.getString(
				expandoBridge.getAttribute("osbCompanyName", false));
		}

		validatePayment(
			ecDocumentEntry, appVersion.getAppVersionId(), ownerClassName,
			acceptContract, legalEntityName);

		ecDocumentEntryExtraSettings.setDomain(domain);
		ecDocumentEntryExtraSettings.setLegalEntityName(legalEntityName);

		ContractEntry eulaContractEntry = appVersion.getContractEntry();

		ecDocumentEntryExtraSettings.setEulaContractEntryId(
			eulaContractEntry.getContractEntryId());

		ContractEntry tosContractEntry =
			ContractEntryLocalServiceUtil.getLatestContractEntry(
				ContractEntryConstants.DEFAULT_CLASS_NAME_ID,
				ContractEntryConstants.DEFAULT_CLASS_PK,
				ContractEntryConstants.TYPE_TERMS_OF_SERVICE);

		ecDocumentEntryExtraSettings.setTosContractEntryId(
			tosContractEntry.getContractEntryId());

		if (ecDocumentEntryExtraSettings.isResale()) {
			ContractEntry rlaContractEntry =
				ContractEntryLocalServiceUtil.getLatestContractEntry(
					ContractEntryConstants.DEFAULT_CLASS_NAME_ID,
					ContractEntryConstants.DEFAULT_CLASS_PK,
					ContractEntryConstants.TYPE_RESELLER_LICENSE_AGREEMENT);

			ecDocumentEntryExtraSettings.setRlaContractEntryId(
				rlaContractEntry.getContractEntryId());
		}

		DeveloperEntry developerEntry = appEntry.getDeveloperEntry();

		if (paymentMethod.equals("paypal") && !developerEntry.isLiferayInc()) {
			updateExtraSettingsVendorAmount(
				ecDocumentEntryExtraSettings, ecDocumentEntry);

			ecDocumentEntryExtraSettings.setPayPalVendorEmailAddress(
				developerEntry.getPaymentEmailAddress());
		}

		return ECDocumentEntryServiceUtil.updateECDocumentEntry(
			ecDocumentEntry.getEcDocumentEntryId(),
			ecDocumentEntry.getStoreName(),
			ecDocumentEntry.getVendorClassName(),
			ecDocumentEntry.getVendorClassPK(), ecDocumentEntry.getType(),
			ecDocumentEntry.getBillingEmailAddress(),
			ecDocumentEntry.getFulfillmentDate(),
			ecDocumentEntry.getPaymentProcessor(),
			ecDocumentEntry.getTaxProcessor(),
			ecDocumentEntry.getEcAccessKeyId(),
			ecDocumentEntryExtraSettings.toString(),
			ecDocumentEntry.getStatus());
	}

	public static ECDocumentEntry updateECDocumentEntryProject(
			long userId, long ecDocumentEntryId, String ownerClassName,
			long ownerClassPK, long appEntryId, long countryId, boolean resale)
		throws PortalException, SystemException {

		AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(appEntryId);

		validateProject(userId, ownerClassName, ownerClassPK, resale);

		ECDocumentEntry ecDocumentEntry = null;

		ECDocumentEntryExtraSettings ecDocumentEntryExtraSettings =
			new ECDocumentEntryExtraSettings();

		ecDocumentEntryExtraSettings.setAppEntryId(appEntryId);
		ecDocumentEntryExtraSettings.setDeveloperEntryId(
			appEntry.getDeveloperEntryId());
		ecDocumentEntryExtraSettings.setOwnerClassPK(ownerClassPK);
		ecDocumentEntryExtraSettings.setOwnerClassName(ownerClassName);
		ecDocumentEntryExtraSettings.setProductType("app-entry");
		ecDocumentEntryExtraSettings.setResale(resale);

		if (ecDocumentEntryId > 0) {
			ecDocumentEntry =
				ECDocumentEntryLocalServiceUtil.getECDocumentEntry(
					ecDocumentEntryId);

			ECDocumentEntryExtraSettings oldECDocumentEntryExtraSettings =
				new ECDocumentEntryExtraSettings(ecDocumentEntry);

			if ((ownerClassPK !=
					oldECDocumentEntryExtraSettings.getOwnerClassPK()) ||
				!ownerClassName.equals(
					oldECDocumentEntryExtraSettings.getOwnerClassName())) {

				ecDocumentEntry =
					ECDocumentEntryServiceUtil.updateECDocumentEntry(
						ecDocumentEntryId,
						ecDocumentEntryExtraSettings.toString());

				ECDocumentItemLocalServiceUtil.deleteECDocumentItems(
					ecDocumentEntryId);
			}
		}
		else {
			DeveloperEntry developerEntry = appEntry.getDeveloperEntry();

			ecDocumentEntry = ECDocumentEntryServiceUtil.addECDocumentEntry(
				OSBConstants.GROUP_GUEST_ID,
				ECommerceConstants.STORE_NAME_MARKETPLACE,
				DeveloperEntry.class.getName(),
				developerEntry.getDeveloperEntryId(), userId, null, null, null,
				null, null, null, 0, 0, null, null, null, null, null, 0,
				countryId, StringPool.BLANK, null, null, null, 0,
				ecDocumentEntryExtraSettings.toString(), null);
		}

		return ecDocumentEntry;
	}

	public static ECDocumentEntry updateECDocumentEntryRequestInvoice(
			long ecDocumentEntryId, String billingEmailAddress)
		throws PortalException, SystemException {

		ECDocumentEntry ecDocumentEntry =
			ECDocumentEntryLocalServiceUtil.getECDocumentEntry(
				ecDocumentEntryId);

		validateRequestInvoice(ecDocumentEntry, billingEmailAddress);

		ECDocumentEntryExtraSettings ecDocumentEntryExtraSettings =
			new ECDocumentEntryExtraSettings(ecDocumentEntry);

		updateExtraSettingsVendorAmount(
			ecDocumentEntryExtraSettings, ecDocumentEntry);

		ecDocumentEntry = ECDocumentEntryServiceUtil.updateECDocumentEntry(
			ecDocumentEntry.getEcDocumentEntryId(),
			ecDocumentEntry.getStoreName(),
			ecDocumentEntry.getVendorClassName(),
			ecDocumentEntry.getVendorClassPK(),
			ECDocumentEntryConstants.TYPE_PURCHASE_ORDER, billingEmailAddress,
			ecDocumentEntry.getFulfillmentDate(), StringPool.BLANK,
			ecDocumentEntry.getTaxProcessor(),
			ecDocumentEntry.getEcAccessKeyId(),
			ecDocumentEntryExtraSettings.toString(),
			ecDocumentEntry.getStatus());

		MarketplaceEmailUtil.sendPurchaseNotificationEmail(ecDocumentEntry);

		return ecDocumentEntry;
	}

	public static ECDocumentEntry updateECDocumentEntrySupportCoterm(
			long ecDocumentEntryId, boolean cotermStandardSupport,
			boolean cotermDeveloperSupport)
		throws PortalException, SystemException {

		ECDocumentEntry ecDocumentEntry =
			ECDocumentEntryServiceUtil.getECDocumentEntry(ecDocumentEntryId);

		ECDocumentEntryExtraSettings ecDocumentEntryExtraSettings =
			new ECDocumentEntryExtraSettings(ecDocumentEntry);

		AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(
			ecDocumentEntryExtraSettings.getAppEntryId());

		AssetReceipt assetReceipt =
			AssetReceiptLocalServiceUtil.getAssetReceipt(
				ecDocumentEntryExtraSettings.getOwnerClassName(),
				ecDocumentEntryExtraSettings.getOwnerClassPK(),
				AppEntry.class.getName(), appEntry.getAppEntryId());

		updateECDocumentItemSupportCoterm(
			ecDocumentEntry, assetReceipt,
			ecDocumentEntryExtraSettings.getCountryId(),
			ecDocumentEntryExtraSettings.getAppEntryId(),
			AssetLicenseConstants.USAGE_TYPE_STANDARD, cotermStandardSupport);
		updateECDocumentItemSupportCoterm(
			ecDocumentEntry, assetReceipt,
			ecDocumentEntryExtraSettings.getCountryId(),
			ecDocumentEntryExtraSettings.getAppEntryId(),
			AssetLicenseConstants.USAGE_TYPE_DEVELOPER, cotermDeveloperSupport);

		return ecDocumentEntry;
	}

	public static ECDocumentEntry updateECDocumentEntryVendorAmount(
			long ecDocumentEntryId)
		throws PortalException, SystemException {

		ECDocumentEntry ecDocumentEntry =
			ECDocumentEntryLocalServiceUtil.getECDocumentEntry(
				ecDocumentEntryId);

		ECDocumentEntryExtraSettings ecDocumentEntryExtraSettings =
			new ECDocumentEntryExtraSettings(ecDocumentEntry);

		updateExtraSettingsVendorAmount(
			ecDocumentEntryExtraSettings, ecDocumentEntry);

		return ECDocumentEntryServiceUtil.updateECDocumentEntry(
			ecDocumentEntryId, ecDocumentEntryExtraSettings.toString());
	}

	public static ECDocumentEntry updateECDocumentItemAssetLicenses(
			long ecDocumentEntryId, Map<AssetLicense, Integer> assetLicensesMap,
			String purchaseType, long countryId)
		throws PortalException, SystemException {

		ECDocumentEntry ecDocumentEntry =
			ECDocumentEntryLocalServiceUtil.getECDocumentEntry(
				ecDocumentEntryId);

		validateLicenses(assetLicensesMap);

		ECDocumentEntryExtraSettings ecDocumentEntryExtraSettings =
			new ECDocumentEntryExtraSettings(ecDocumentEntry);

		long assetReceiptId = 0;

		AssetReceipt assetReceipt = AssetReceiptServiceUtil.fetchAssetReceipt(
			ecDocumentEntryExtraSettings.getOwnerClassName(),
			ecDocumentEntryExtraSettings.getOwnerClassPK(),
			AppEntry.class.getName(),
			ecDocumentEntryExtraSettings.getAppEntryId());

		if (assetReceipt != null) {
			assetReceiptId = assetReceipt.getAssetReceiptId();
		}

		boolean renew = false;

		if (purchaseType.equals("renew")) {
			renew = true;
		}

		for (Map.Entry<AssetLicense, Integer> entry :
				assetLicensesMap.entrySet()) {

			addECDocumentItemsAssetLicense(
				ecDocumentEntryId, assetReceiptId, countryId, entry.getKey(),
				entry.getValue(), renew);
		}

		ecDocumentEntryExtraSettings.setPurchaseType(purchaseType);
		ecDocumentEntryExtraSettings.setCountryId(countryId);

		return ECDocumentEntryServiceUtil.updateECDocumentEntry(
			ecDocumentEntryId, ecDocumentEntryExtraSettings.toString());
	}

	public static ECDocumentEntry updateECDocumentItemSupport(
			long ecDocumentEntryId, boolean renewStandardSupport,
			boolean renewDeveloperSupport, String purchaseType, long countryId)
		throws PortalException, SystemException {

		ECDocumentEntry ecDocumentEntry =
			ECDocumentEntryLocalServiceUtil.getECDocumentEntry(
				ecDocumentEntryId);

		validateSupportRenewal(renewStandardSupport, renewDeveloperSupport);

		ECDocumentEntryExtraSettings ecDocumentEntryExtraSettings =
			new ECDocumentEntryExtraSettings(ecDocumentEntry);

		AssetReceipt assetReceipt =
			AssetReceiptLocalServiceUtil.getAssetReceipt(
				ecDocumentEntryExtraSettings.getOwnerClassName(),
				ecDocumentEntryExtraSettings.getOwnerClassPK(),
				AppEntry.class.getName(),
				ecDocumentEntryExtraSettings.getAppEntryId());

		AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(
			ecDocumentEntryExtraSettings.getAppEntryId());

		AppVersion appVersion = appEntry.getApprovedAppVersion();

		if (renewStandardSupport) {
			addECDocumentItemsAssetReceiptSupport(
				ecDocumentEntryId, assetReceipt.getAssetReceiptId(), countryId,
				appVersion.getAppVersionId(),
				AssetLicenseConstants.USAGE_TYPE_STANDARD);
		}

		if (renewDeveloperSupport) {
			addECDocumentItemsAssetReceiptSupport(
				ecDocumentEntryId, assetReceipt.getAssetReceiptId(), countryId,
				appVersion.getAppVersionId(),
				AssetLicenseConstants.USAGE_TYPE_DEVELOPER);
		}

		ecDocumentEntryExtraSettings.setPurchaseType(purchaseType);
		ecDocumentEntryExtraSettings.setCountryId(countryId);

		return ECDocumentEntryServiceUtil.updateECDocumentEntry(
			ecDocumentEntryId, ecDocumentEntryExtraSettings.toString());
	}

	protected static void addECDocumentItemLicenseCoterm(
			ECDocumentEntry ecDocumentEntry, AssetReceipt assetReceipt,
			long countryId, AppVersion appVersion, int usageType)
		throws PortalException, SystemException {

		List<ECDocumentItem> ecDocumentItems =
			ecDocumentEntry.getECDocumentItems();

		Date startDate = new Date();
		Date endDate = assetReceipt.getRenewedAssetReceiptLicensesEndDate(
			usageType);

		long classNameId = PortalUtil.getClassNameId(AssetLicense.class);

		long licenseLifetime = endDate.getTime() - startDate.getTime();

		if (licenseLifetime <= AssetLicenseConstants.LIFETIME_SUBSCRIPTION) {
			return;
		}

		double prorate =
			(double)licenseLifetime /
				AssetLicenseConstants.LIFETIME_SUBSCRIPTION;

		for (ECDocumentItem ecDocumentItem : ecDocumentItems) {
			if ((ecDocumentItem.getClassNameId() != classNameId) ||
				(ecDocumentItem.getClassPK() <= 0)) {

				continue;
			}

			AppPricingItem appPricingItem =
				AppPricingItemLocalServiceUtil.fetchAppPricingItem(
					appVersion.getAppVersionId(), countryId,
					ecDocumentItem.getClassPK());

			double unitPrice = appPricingItem.getPrice() * prorate;

			ECDocumentItemExtraSettings ecDocumentItemExtraSettings =
				new ECDocumentItemExtraSettings(ecDocumentItem);

			ecDocumentItemExtraSettings.setEndDate(endDate);
			ecDocumentItemExtraSettings.setItemType("license");
			ecDocumentItemExtraSettings.setPurchaseType("renew");
			ecDocumentItemExtraSettings.setStartDate(startDate);
			ecDocumentItemExtraSettings.setUsageType(usageType);

			ECDocumentItemLocalServiceUtil.updateECDocumentItem(
				ecDocumentItem.getEcDocumentItemId(), unitPrice,
				ecDocumentItem.getQuantity(), ecDocumentItem.getComment(),
				ecDocumentItemExtraSettings.toString());
		}
	}

	protected static void addECDocumentItemLicenseExtensionCoterm(
			ECDocumentEntry ecDocumentEntry, AssetReceipt assetReceipt,
			long countryId, long appEntryId, int usageType, boolean purchase)
		throws PortalException, SystemException {

		long classPK = ECommerceConstants.getClassPK(usageType);

		ECDocumentItem ecDocumentItem =
			ECDocumentItemLocalServiceUtil.fetchECDocumentItem(
				ecDocumentEntry.getEcDocumentEntryId(),
				AssetLicense.class.getName(), classPK, null);

		if (!purchase) {
			if (ecDocumentItem != null) {
				ECDocumentItemServiceUtil.deleteECDocumentItem(
					ecDocumentItem.getEcDocumentItemId());
			}

			return;
		}

		List<AssetReceiptLicense> assetReceiptLicenses =
			AssetReceiptLicenseLocalServiceUtil.getActiveAssetReceiptLicenses(
				assetReceipt.getAssetReceiptId(), usageType, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS);

		double price = MarketplaceCotermUtil.getCotermLicensePrice(
			assetReceiptLicenses, countryId, appEntryId, usageType);

		ECDocumentItemExtraSettings ecDocumentItemExtraSettings =
			new ECDocumentItemExtraSettings(ecDocumentItem);

		ecDocumentItemExtraSettings.setItemType("license");

		long[] assetReceiptLicenseIds = getAssetReceiptLicenseIds(
			assetReceiptLicenses);

		ecDocumentItemExtraSettings.setProratedAssetReceiptLicenseIds(
			assetReceiptLicenseIds);

		long proratedDays = MarketplaceCotermUtil.getCotermProratedDays(
			assetReceipt.getAssetReceiptId(), usageType);

		ecDocumentItemExtraSettings.setProratedDays(proratedDays);

		ecDocumentItemExtraSettings.setPurchaseType("coterm");
		ecDocumentItemExtraSettings.setUsageType(usageType);

		if (ecDocumentItem == null) {
			ECDocumentItemServiceUtil.addECDocumentItem(
				ecDocumentEntry.getEcDocumentEntryId(),
				ECommerceConstants.EC_PRODUCT_TYPE_ID_MARKETPLACE_APP,
				AssetLicense.class.getName(), classPK,
				"Prorated License Extension", ecDocumentEntry.getCurrencyCode(),
				price, 1, ecDocumentItemExtraSettings.toString());
		}
		else {
			ECDocumentItemServiceUtil.updateECDocumentItem(
				ecDocumentItem.getEcDocumentItemId(), price,
				ecDocumentItem.getQuantity(), ecDocumentItem.getComment(),
				ecDocumentItem.getExtraSettings());
		}
	}

	protected static void addECDocumentItemsAssetLicense(
			long ecDocumentEntryId, long assetReceiptId, long countryId,
			AssetLicense assetLicense, int quantity, boolean renew)
		throws PortalException, SystemException {

		ECDocumentItemExtraSettings ecDocumentItemExtraSettings =
			new ECDocumentItemExtraSettings();

		ecDocumentItemExtraSettings.setItemType("license");
		ecDocumentItemExtraSettings.setPurchaseType("new");
		ecDocumentItemExtraSettings.setUsageType(assetLicense.getUsageType());

		Date startDate = new Date();

		if (renew) {
			ecDocumentItemExtraSettings.setPurchaseType("renew");

			List<AssetReceiptLicense> assetReceiptLicenses =
				AssetReceiptLicenseLocalServiceUtil.
					getActiveAssetReceiptLicenses(
						assetReceiptId, assetLicense.getUsageType(), 0, 1);

			if (assetReceiptLicenses.isEmpty()) {
				return;
			}

			AssetReceiptLicense assetReceiptLicense = assetReceiptLicenses.get(
				0);

			startDate = assetReceiptLicense.getEndDate();
		}

		ecDocumentItemExtraSettings.setStartDate(startDate);

		if (assetLicense.getLifetime() <
				AssetLicenseConstants.LIFETIME_PERPETUAL) {

			Date endDate = new Date(
				startDate.getTime() + assetLicense.getLifetime());

			ecDocumentItemExtraSettings.setEndDate(endDate);
		}

		ECDocumentItem ecDocumentItem =
			ECDocumentItemLocalServiceUtil.fetchECDocumentItem(
				ecDocumentEntryId, AssetLicense.class.getName(),
				assetLicense.getAssetLicenseId(), null);

		if ((ecDocumentItem == null) && (quantity > 0)) {
			AppVersion appVersion = AppVersionLocalServiceUtil.getAppVersion(
				assetLicense.getClassPK());

			AppPricingItem appPricingItem =
				AppPricingItemLocalServiceUtil.getAppPricingItem(
					appVersion.getAppVersionId(), countryId,
					assetLicense.getAssetLicenseId());

			String ecDocumentItemName = getECDocumentItemName(
				appVersion, assetLicense);

			ECDocumentItemServiceUtil.addECDocumentItem(
				ecDocumentEntryId,
				ECommerceConstants.EC_PRODUCT_TYPE_ID_MARKETPLACE_APP,
				AssetLicense.class.getName(), assetLicense.getAssetLicenseId(),
				ecDocumentItemName, appPricingItem.getCurrencyCode(),
				appPricingItem.getPrice(), quantity,
				ecDocumentItemExtraSettings.toString());
		}
		else if ((ecDocumentItem != null) && (quantity > 0)) {
			ECDocumentItemServiceUtil.updateECDocumentItem(
				ecDocumentItem.getEcDocumentItemId(),
				ecDocumentItem.getUnitPrice(), quantity,
				ecDocumentItem.getComment(), ecDocumentItem.getExtraSettings());
		}
		else if (ecDocumentItem != null) {
			ECDocumentItemServiceUtil.deleteECDocumentItem(
				ecDocumentItem.getEcDocumentItemId());
		}
	}

	protected static void addECDocumentItemsAssetReceiptSupport(
			long ecDocumentEntryId, long assetReceiptId, long countryId,
			long appVersionId, int usageType)
		throws PortalException, SystemException {

		long totalLicenseTypeAllotment =
			AssetReceiptLicenseLocalServiceUtil.
				getActiveAssetReceiptLicenseTypeAllotment(
					assetReceiptId, usageType);

		long totalRenewedLicenseTypeAllotment =
			AssetReceiptLicenseLocalServiceUtil.
				getRenewedAssetReceiptLicenseTypeAllotment(
					assetReceiptId, usageType);

		totalLicenseTypeAllotment -= totalRenewedLicenseTypeAllotment;

		if (totalLicenseTypeAllotment <= 0) {
			return;
		}

		ECDocumentItemExtraSettings ecDocumentItemExtraSettings =
			new ECDocumentItemExtraSettings();

		ecDocumentItemExtraSettings.setItemType("support");
		ecDocumentItemExtraSettings.setUsageType(usageType);

		List<AssetReceiptSupport> assetReceiptSupports =
			AssetReceiptSupportLocalServiceUtil.getActiveAssetReceiptSupports(
				assetReceiptId, usageType, 0, 1);

		AssetReceiptSupport assetReceiptSupport = assetReceiptSupports.get(0);

		Date startDate = assetReceiptSupport.getEndDate();

		ecDocumentItemExtraSettings.setStartDate(startDate);

		Date endDate = new Date(
			startDate.getTime() + AssetLicenseConstants.LIFETIME_SUBSCRIPTION);

		ecDocumentItemExtraSettings.setEndDate(endDate);

		CountryAppPricing countryAppPricing =
			CountryAppPricingLocalServiceUtil.fetchCountryAppPricing(
				appVersionId, countryId);

		AppPricing appPricing = AppPricingLocalServiceUtil.getAppPricing(
			countryAppPricing.getAppPricingId());

		long classPK = ECommerceConstants.getClassPK(usageType);

		ECDocumentItem ecDocumentItem =
			ECDocumentItemLocalServiceUtil.fetchECDocumentItem(
				ecDocumentEntryId, AssetReceiptSupport.class.getName(), classPK,
				null);

		if (ecDocumentItem == null) {
			ECDocumentItemServiceUtil.addECDocumentItem(
				ecDocumentEntryId,
				ECommerceConstants.EC_PRODUCT_TYPE_ID_MARKETPLACE_APP_SUPPORT,
				AssetReceiptSupport.class.getName(), classPK,
				ECommerceConstants.EC_PRODUCT_TYPE_NAME_MARKETPLACE_APP_SUPPORT,
				appPricing.getCurrencyCode(),
				appPricing.getSupportPrice(usageType),
				(int)totalLicenseTypeAllotment,
				ecDocumentItemExtraSettings.toString());
		}
		else {
			ECDocumentItemServiceUtil.updateECDocumentItem(
				ecDocumentItem.getEcDocumentItemId(),
				appPricing.getSupportPrice(usageType),
				(int)totalLicenseTypeAllotment, StringPool.BLANK,
				ecDocumentItemExtraSettings.toString());
		}
	}

	protected static long[] getAssetReceiptLicenseIds(
		List<AssetReceiptLicense> assetReceiptLicenses) {

		long[] assetReceiptLicenseIds = new long[assetReceiptLicenses.size()];

		for (int i = 0; i < assetReceiptLicenses.size(); i++) {
			AssetReceiptLicense assetReceiptLicense = assetReceiptLicenses.get(
				i);

			assetReceiptLicenseIds[i] =
				assetReceiptLicense.getAssetReceiptLicenseId();
		}

		return assetReceiptLicenseIds;
	}

	protected static String getECDocumentItemName(
			AppVersion appVersion, AssetLicense assetLicense)
		throws PortalException, SystemException {

		if (assetLicense.getClassNameId() !=
				PortalUtil.getClassNameId(AppVersion.class)) {

			return assetLicense.getName();
		}

		return appVersion.getTitle() + " - " + assetLicense.getName();
	}

	protected static void updateECDocumentItemSupportCoterm(
			ECDocumentEntry ecDocumentEntry, AssetReceipt assetReceipt,
			long countryId, long appEntryId, int usageType, boolean purchase)
		throws PortalException, SystemException {

		long classPK = ECommerceConstants.getClassPK(usageType);

		ECDocumentItem ecDocumentItem =
			ECDocumentItemLocalServiceUtil.fetchECDocumentItem(
				ecDocumentEntry.getEcDocumentEntryId(),
				AssetReceiptSupport.class.getName(), classPK, null);

		if (!purchase) {
			if (ecDocumentItem != null) {
				ECDocumentItemServiceUtil.deleteECDocumentItem(
					ecDocumentItem.getEcDocumentItemId());
			}

			return;
		}

		double price = MarketplaceCotermUtil.getCotermSupportPrice(
			assetReceipt.getAssetReceiptId(), countryId, appEntryId, usageType);

		ECDocumentItemExtraSettings ecDocumentItemExtraSettings =
			new ECDocumentItemExtraSettings(ecDocumentItem);

		ecDocumentItemExtraSettings.setItemType("support");

		List<AssetReceiptLicense> assetReceiptLicenses =
			AssetReceiptLicenseLocalServiceUtil.getActiveAssetReceiptLicenses(
				assetReceipt.getAssetReceiptId(), usageType, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS);

		long[] assetReceiptLicenseIds = getAssetReceiptLicenseIds(
			assetReceiptLicenses);

		ecDocumentItemExtraSettings.setProratedAssetReceiptLicenseIds(
			assetReceiptLicenseIds);

		long proratedDays = MarketplaceCotermUtil.getCotermProratedDays(
			assetReceipt.getAssetReceiptId(), usageType);

		ecDocumentItemExtraSettings.setProratedDays(proratedDays);

		ecDocumentItemExtraSettings.setPurchaseType("coterm");
		ecDocumentItemExtraSettings.setUsageType(usageType);

		if (ecDocumentItem == null) {
			ECDocumentItemServiceUtil.addECDocumentItem(
				ecDocumentEntry.getEcDocumentEntryId(),
				ECommerceConstants.EC_PRODUCT_TYPE_ID_MARKETPLACE_APP_SUPPORT,
				AssetReceiptSupport.class.getName(), classPK,
				"Prorated Support Extension", ecDocumentEntry.getCurrencyCode(),
				price, 1, ecDocumentItemExtraSettings.toString());
		}
		else {
			ECDocumentItemServiceUtil.updateECDocumentItem(
				ecDocumentItem.getEcDocumentItemId(), price,
				ecDocumentItem.getQuantity(), ecDocumentItem.getComment(),
				ecDocumentItem.getExtraSettings());
		}
	}

	protected static void updateExtraSettingsVendorAmount(
			ECDocumentEntryExtraSettings ecDocumentEntryExtraSettings,
			ECDocumentEntry ecDocumentEntry)
		throws PortalException, SystemException {

		double vendorAmount = BigDecimalUtil.multiply(
			ecDocumentEntry.getSubtotal(),
			PortletPropsValues.MARKETPLACE_DEVELOPER_PAYMENT_PERCENTAGE);

		vendorAmount = BigDecimalUtil.multiply(vendorAmount, 0.01);

		String currencyCode = ecDocumentEntry.getCurrencyCode();

		AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(
			ecDocumentEntryExtraSettings.getAppEntryId());

		if (isFATCAReportable(
				appEntry, ecDocumentEntry.getShippingAddressCountry())) {

			DeveloperEntry developerEntry =
				DeveloperEntryLocalServiceUtil.getDeveloperEntry(
					ecDocumentEntryExtraSettings.getDeveloperEntryId());

			double fatcaWithholdingAmount = BigDecimalUtil.multiply(
				vendorAmount,
				developerEntry.getFatcaWithholdingPercentageValue());

			fatcaWithholdingAmount = BigDecimalUtil.multiply(
				fatcaWithholdingAmount, 0.01);

			fatcaWithholdingAmount = CurrencyUtil.scale(
				currencyCode, fatcaWithholdingAmount);

			ecDocumentEntryExtraSettings.setFatcaWithholdingAmount(
				fatcaWithholdingAmount);

			double fatcaWithholdingAmountUSD = ECAccessUtil.convertCurrency(
				ecDocumentEntry.getGroupId(), fatcaWithholdingAmount,
				currencyCode, "USD");

			ecDocumentEntryExtraSettings.setFatcaWithholdingAmountUSD(
				fatcaWithholdingAmountUSD);

			vendorAmount = BigDecimalUtil.subtract(
				vendorAmount, fatcaWithholdingAmount);
		}

		vendorAmount = CurrencyUtil.scale(currencyCode, vendorAmount);

		ecDocumentEntryExtraSettings.setVendorAmount(vendorAmount);

		if (!currencyCode.equals("USD")) {
			double vendorAmountUSD = ECAccessUtil.convertCurrency(
				ecDocumentEntry.getGroupId(), vendorAmount, currencyCode,
				"USD");

			ecDocumentEntryExtraSettings.setVendorAmountUSD(vendorAmountUSD);
		}
		else {
			ecDocumentEntryExtraSettings.setVendorAmountUSD(vendorAmount);
		}
	}

	protected static void validateDestination(
			String ownerClassName, String companyName)
		throws PortalException {

		if (ownerClassName.equals(CorpProject.class.getName()) &&
			Validator.isNull(companyName)) {

			throw new AddressCompanyNameException();
		}
	}

	protected static void validateEndUser(
			String emailAddress, String firstName, String lastName,
			String phoneNumber)
		throws PortalException, SystemException {

		if (!Validator.isEmailAddress(emailAddress)) {
			throw new RequiredEndUserEmailAddressException();
		}

		if (Validator.isNull(firstName)) {
			throw new RequiredEndUserFirstNameException();
		}

		if (Validator.isNull(lastName)) {
			throw new RequiredEndUserLastNameException();
		}

		if (!Validator.isPhoneNumber(phoneNumber)) {
			throw new RequiredEndUserPhoneNumberException();
		}
	}

	protected static void validateLicenseCoterm(
			ECDocumentEntry ecDocumentEntry, AssetReceipt assetReceipt,
			AppEntry appEntry, boolean cotermStandardAssetLicense,
			boolean cotermDeveloperAssetLicense)
		throws PortalException, SystemException {

		if (appEntry.getLicenseLifetime() !=
				AssetLicenseConstants.LIFETIME_SUBSCRIPTION) {

			throw new UnsupportedOperationException();
		}

		boolean activeStandardAssetReceiptLicenses =
			assetReceipt.hasActiveAssetReceiptLicenses(
				AssetLicenseConstants.USAGE_TYPE_STANDARD);
		boolean activeDeveloperAssetReceiptLicenses =
			assetReceipt.hasActiveAssetReceiptLicenses(
				AssetLicenseConstants.USAGE_TYPE_DEVELOPER);

		List<ECDocumentItem> ecDocumentItems =
			ECDocumentItemLocalServiceUtil.getECDocumentItems(
				ecDocumentEntry.getEcDocumentEntryId(), QueryUtil.ALL_POS,
				QueryUtil.ALL_POS);

		for (ECDocumentItem ecDocumentItem : ecDocumentItems) {
			if (ecDocumentItem.getClassPK() <= 0) {
				continue;
			}

			AssetLicense assetLicense =
				AssetLicenseLocalServiceUtil.getAssetLicense(
					ecDocumentItem.getClassPK());

			if ((assetLicense.getUsageType() ==
					AssetLicenseConstants.USAGE_TYPE_STANDARD) &&
				activeStandardAssetReceiptLicenses &&
				!cotermStandardAssetLicense) {

				throw new RequiredCotermException();
			}
			else if ((assetLicense.getUsageType() ==
						AssetLicenseConstants.USAGE_TYPE_DEVELOPER) &&
					 activeDeveloperAssetReceiptLicenses &&
					 !cotermDeveloperAssetLicense) {

				throw new RequiredCotermException();
			}
		}
	}

	protected static void validateLicenses(
			Map<AssetLicense, Integer> assetLicensesMap)
		throws PortalException {

		int count = 0;

		for (Map.Entry<AssetLicense, Integer> entry :
				assetLicensesMap.entrySet()) {

			count += entry.getValue();
		}

		if (count <= 0) {
			throw new RequiredQuantityException();
		}
	}

	protected static void validatePayment(
			ECDocumentEntry ecDocumentEntry, long appVersionId,
			String ownerClassName, boolean acceptContract,
			String legalEntityName)
		throws PortalException, SystemException {

		if (!acceptContract) {
			throw new ContractAuditAcceptanceException();
		}

		if (ownerClassName.equals(CorpProject.class.getName()) &&
			Validator.isNull(legalEntityName)) {

			throw new AssetReceiptLegalEntityNameException();
		}

		if (ecDocumentEntry == null) {
			return;
		}

		if (!ecDocumentEntry.isTaxProcessed()) {
			throw new RequiredTaxException();
		}

		List<ECDocumentItem> ecDocumentItems =
			ecDocumentEntry.getECDocumentItems();

		if (ecDocumentItems.isEmpty()) {
			throw new RequiredQuantityException();
		}

		for (ECDocumentItem ecDocumentItem : ecDocumentItems) {
			String className = ecDocumentItem.getClassName();

			if (!className.equals(AssetLicense.class.getName()) ||
				(ecDocumentItem.getClassPK() <= 0)) {

				continue;
			}

			AssetLicense assetLicense =
				AssetLicenseLocalServiceUtil.getAssetLicense(
					ecDocumentItem.getClassPK());

			AppVersion appVersion = AppVersionLocalServiceUtil.getAppVersion(
				assetLicense.getClassPK());

			if (appVersionId != appVersion.getAppVersionId()) {
				throw new ExpiredAppPricingItemException();
			}
		}
	}

	protected static void validateProject(
			long userId, String ownerClassName, long ownerClassPK,
			boolean resale)
		throws PortalException, SystemException {

		if (Validator.isNull(ownerClassName) || (ownerClassPK <= 0)) {
			throw new AssetReceiptOwnerClassNameException();
		}

		if (resale) {
			if (ownerClassName.equals(User.class.getName())) {
				throw new AssetReceiptOwnerClassNameException();
			}

			PermissionChecker permissionChecker =
				PermissionThreadLocal.getPermissionChecker();

			if ((permissionChecker != null) &&
				ownerClassName.equals(CorpProject.class.getName())) {

				OSBCorpProjectPermission.check(
					permissionChecker, ownerClassPK,
					OSBActionKeys.PURCHASE_ASSET);
			}
		}
	}

	protected static void validateRequestInvoice(
			ECDocumentEntry ecDocumentEntry, String billingEmailAddress)
		throws PortalException {

		if (!ecDocumentEntry.isTaxProcessed()) {
			throw new RequiredTaxException();
		}

		if (!Validator.isEmailAddress(billingEmailAddress)) {
			throw new AssetReceiptEmailAddressException();
		}
	}

	protected static void validateSupportRenewal(
			boolean renewStandardSupport, boolean renewDeveloperSupport)
		throws PortalException {

		if (!renewStandardSupport && !renewDeveloperSupport) {
			throw new RequiredRenewalException();
		}
	}

	protected static void validateTrial(
			AppEntry appEntry, String ownerClassName, long ownerClassPK)
		throws PortalException, SystemException {

		int count =
			AssetReceiptLicenseLocalServiceUtil.getAssetReceiptLicensesCount(
				PortalUtil.getClassNameId(ownerClassName), ownerClassPK,
				appEntry.getUuid());

		if (count > 0) {
			throw new TrialLicenseException();
		}
	}

}