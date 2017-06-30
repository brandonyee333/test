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

package com.liferay.osb.util;

import com.liferay.compat.portal.kernel.util.LocaleUtil;
import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.ecommerce.model.ECDocumentEntry;
import com.liferay.ecommerce.model.ECDocumentItem;
import com.liferay.osb.marketplace.util.ECDocumentEntryExtraSettings;
import com.liferay.osb.marketplace.util.ECDocumentItemExtraSettings;
import com.liferay.osb.marketplace.util.MarketplaceEcommerceUtil;
import com.liferay.osb.model.AppEntry;
import com.liferay.osb.model.AppVersion;
import com.liferay.osb.model.AssetReceipt;
import com.liferay.osb.model.DeveloperEntry;
import com.liferay.osb.model.TrainingCourse;
import com.liferay.osb.model.TrainingEvent;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.TimeZoneUtil;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.Country;
import com.liferay.portal.model.Region;
import com.liferay.portal.model.User;
import com.liferay.portal.service.CountryServiceUtil;
import com.liferay.portal.service.RegionServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.Portal;
import com.liferay.portal.util.SubscriptionSender;

import java.text.DateFormat;
import java.text.Format;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

/**
 * @author Ryan Park
 * @author Joan Kim
 */
public class OSBSubscriptionSender extends SubscriptionSender {

	public void setContextAttributes(
		AppEntry appEntry, Locale locale, TimeZone timeZone) {

		if (locale == null) {
			locale = LocaleUtil.getDefault();
		}

		if (timeZone == null) {
			timeZone = TimeZoneUtil.getDefault();
		}

		setContextAttributes(appEntry, "APP_ENTRY");

		setContextAttribute(
			"[$APP_ENTRY_CHANGE_LOG$]", appEntry.getChangeLog(locale));
		setContextAttribute(
			"[$APP_ENTRY_DESCRIPTION$]", appEntry.getDescription(locale));

		try {
			DeveloperEntry developerEntry = appEntry.getDeveloperEntry();

			String developerURL = PortalUtil.getLayoutFullURL(
				developerEntry.getGroupId(),
				OSBPortletKeys.OSB_MARKETPLACE_DEVELOPER_APPS);

			developerURL =
				developerURL + Portal.FRIENDLY_URL_SEPARATOR +
					"marketplace_developer_apps/application/" +
						appEntry.getAppEntryId();

			setContextAttribute("[$APP_ENTRY_DEVELOPER_URL$]", developerURL);
		}
		catch (Exception e) {
		}

		setContextAttribute(
			"[$APP_ENTRY_STATUS$]",
			LanguageUtil.get(locale, appEntry.getStatusLabel()));

		DateFormat dateFormat = DateFormatFactoryUtil.getDateTime(
			locale, timeZone);

		setContextAttribute(
			"[$APP_ENTRY_STATUS_DATE$]",
			dateFormat.format(appEntry.getStatusDate()));

		try {
			String layoutURL = PortalUtil.getLayoutFullURL(
				OSBConstants.GROUP_GUEST_ID, OSBPortletKeys.OSB_MARKETPLACE);

			String iconURL =
				layoutURL + Portal.FRIENDLY_URL_SEPARATOR + "mp/asset/icon/" +
					appEntry.getIconImageId();

			setContextAttribute("[$APP_ENTRY_ICON_URL$]", iconURL);

			String reviewURL =
				layoutURL + Portal.FRIENDLY_URL_SEPARATOR +
					"mp/application/review/" + appEntry.getAppEntryId();

			setContextAttribute("[$APP_ENTRY_REVIEW_URL$]", reviewURL);

			String storeURL =
				layoutURL + Portal.FRIENDLY_URL_SEPARATOR + "mp/application/" +
					appEntry.getAppEntryId();

			setContextAttribute("[$APP_ENTRY_STORE_URL$]", storeURL);
		}
		catch (Exception e) {
		}
	}

	public void setContextAttributes(
		AppVersion appVersion, Locale locale, TimeZone timeZone) {

		if (locale == null) {
			locale = LocaleUtil.getDefault();
		}

		if (timeZone == null) {
			timeZone = TimeZoneUtil.getDefault();
		}

		setContextAttributes(appVersion, "APP_VERSION");

		setContextAttribute(
			"[$APP_VERSION_CHANGE_LOG$]", appVersion.getChangeLog(locale));
		setContextAttribute(
			"[$APP_VERSION_DESCRIPTION$]", appVersion.getDescription(locale));
		setContextAttribute(
			"[$APP_VERSION_STATUS$]",
			LanguageUtil.get(locale, appVersion.getStatusLabel()));

		DateFormat dateFormat = DateFormatFactoryUtil.getDateTime(
			locale, timeZone);

		setContextAttribute(
			"[$APP_VERSION_STATUS_DATE$]",
			dateFormat.format(appVersion.getStatusDate()));
	}

	public void setContextAttributes(
		AssetReceipt assetReceipt, Locale locale, TimeZone timeZone) {

		if (locale == null) {
			locale = LocaleUtil.getDefault();
		}

		if (timeZone == null) {
			timeZone = TimeZoneUtil.getDefault();
		}

		setContextAttributes(assetReceipt, "ASSET_RECEIPT");

		setContextAttribute(
			"[$ASSET_RECEIPT_FORMAL_LEGAL_ENTITY_NAME$]",
			assetReceipt.getFormalLegalEntityName());

		try {
			User user = UserLocalServiceUtil.getUser(assetReceipt.getUserId());

			String layoutURL = PortalUtil.getLayoutFullURL(
				user.getGroupId(), OSBPortletKeys.OSB_MARKETPLACE_APPS);

			String manageAppURL =
				layoutURL + Portal.FRIENDLY_URL_SEPARATOR +
					"marketplace_apps/" + assetReceipt.getAssetReceiptId();

			setContextAttribute(
				"[$ASSET_RECEIPT_MANAGE_APP_URL$]", manageAppURL);
		}
		catch (Exception e) {
		}
	}

	public void setContextAttributes(BaseModel model, String namespace) {
		Map<String, Object> map = model.getModelAttributes();

		for (Map.Entry<String, Object> entry : map.entrySet()) {
			String key = getNamespacedKey(entry.getKey(), namespace);
			String value = String.valueOf(entry.getValue());

			setContextAttributes(key, value);
		}
	}

	public void setContextAttributes(
		DeveloperEntry developerEntry, Locale locale, TimeZone timeZone) {

		if (locale == null) {
			locale = LocaleUtil.getDefault();
		}

		if (timeZone == null) {
			timeZone = TimeZoneUtil.getDefault();
		}

		setContextAttributes(developerEntry, "DEVELOPER_ENTRY");

		String developerAddressHTML = getAddressHTML(
			developerEntry.getStreet1(), developerEntry.getStreet2(),
			developerEntry.getStreet3(), developerEntry.getCity(),
			developerEntry.getRegionId(), developerEntry.getZip(),
			developerEntry.getCountryId());

		setContextAttribute(
			"[$DEVELOPER_ENTRY_ADDRESS_HTML$]", developerAddressHTML, false);

		String developerEntryLegalEntityName =
			developerEntry.getLegalEntityName();

		if (developerEntry.isUser()) {
			developerEntryLegalEntityName =
				developerEntry.getFirstName() + StringPool.SPACE +
					developerEntry.getLastName();
		}

		setContextAttribute(
			"[$DEVELOPER_ENTRY_LEGAL_ENTITY_NAME$]",
			developerEntryLegalEntityName, false);
		setContextAttribute(
			"[$DEVELOPER_ENTRY_STATUS$]",
			LanguageUtil.get(locale, developerEntry.getStatusLabel()));
	}

	public void setContextAttributes(
		ECDocumentEntry ecDocumentEntry, Locale locale, TimeZone timeZone) {

		if (locale == null) {
			locale = LocaleUtil.getDefault();
		}

		if (timeZone == null) {
			timeZone = TimeZoneUtil.getDefault();
		}

		setContextAttributes(ecDocumentEntry, "EC_DOCUMENT_ENTRY");

		Map<String, String[]> params = new HashMap<String, String[]>();

		String namespace = PortalUtil.getPortletNamespace(
			OSBPortletKeys.OSB_MARKETPLACE_ADMIN);

		params.put(
			namespace + "mvcPath",
			new String[] {"/marketplace_admin/review_ec_document_entry.jsp"});
		params.put(
			namespace + "ecDocumentEntryId",
			new String[] {
				String.valueOf(ecDocumentEntry.getEcDocumentEntryId())
			});

		String ecDocumentEntryURL = StringPool.BLANK;

		try {
			ecDocumentEntryURL = PortalUtil.getControlPanelFullURL(
				OSBConstants.GROUP_GUEST_ID,
				OSBPortletKeys.OSB_MARKETPLACE_ADMIN, params);
		}
		catch (Exception e) {
		}

		setContextAttribute("[$EC_DOCUMENT_ENTRY_URL$]", ecDocumentEntryURL);

		String billingAddressHTML = getAddressHTML(
			ecDocumentEntry.getBillingAddressStreet1(),
			ecDocumentEntry.getBillingAddressStreet2(),
			ecDocumentEntry.getBillingAddressStreet3(),
			ecDocumentEntry.getBillingAddressCity(),
			ecDocumentEntry.getBillingAddressRegionId(),
			ecDocumentEntry.getBillingAddressZip(),
			ecDocumentEntry.getBillingAddressCountryId());

		setContextAttribute(
			"[$EC_DOCUMENT_ENTRY_BILLING_ADDRESS_HTML$]", billingAddressHTML,
			false);

		DateFormat dateFormat = DateFormatFactoryUtil.getDateTime(
			locale, timeZone);

		if (ecDocumentEntry.getFulfillmentDate() != null) {
			setContextAttribute(
				"[$EC_DOCUMENT_ENTRY_FULFILLMENT_DATE$]",
				dateFormat.format(ecDocumentEntry.getFulfillmentDate()));
		}

		String lineItemsHTML = getLineItemsHTML(
			ecDocumentEntry, locale, timeZone);

		setContextAttribute(
			"[$EC_DOCUMENT_ENTRY_LINE_ITEMS_HTML$]", lineItemsHTML, false);

		if (ecDocumentEntry.getPaidDate() != null) {
			setContextAttribute(
				"[$EC_DOCUMENT_ENTRY_PAID_DATE$]",
				dateFormat.format(ecDocumentEntry.getPaidDate()));
		}

		String paymentProcessor = StringPool.BLANK;

		String paymentProcessorClassName =
			ecDocumentEntry.getPaymentProcessor();

		if (paymentProcessorClassName.equals(
				MarketplaceEcommerceUtil.
					PAY_PAL_PAYMENT_PROCESSOR_CLASS_NAME)) {

			paymentProcessor = "PayPal";
		}
		else {
			paymentProcessor = "Invoice Me";
		}

		setContextAttribute(
			"[$EC_DOCUMENT_ENTRY_PAYMENT_PROCESSOR$]", paymentProcessor);

		String shippingAddressHTML = getAddressHTML(
			ecDocumentEntry.getShippingAddressStreet1(),
			ecDocumentEntry.getShippingAddressStreet2(),
			ecDocumentEntry.getShippingAddressStreet3(),
			ecDocumentEntry.getShippingAddressCity(),
			ecDocumentEntry.getShippingAddressRegionId(),
			ecDocumentEntry.getShippingAddressZip(),
			ecDocumentEntry.getShippingAddressCountryId());

		setContextAttribute(
			"[$EC_DOCUMENT_ENTRY_SHIPPING_ADDRESS_HTML$]", shippingAddressHTML,
			false);

		setContextAttribute(
			"[$EC_DOCUMENT_ENTRY_STATUS$]",
			LanguageUtil.get(locale, ecDocumentEntry.getStatusLabel()));
		setContextAttribute(
			"[$EC_DOCUMENT_ENTRY_TYPE$]",
			LanguageUtil.get(locale, ecDocumentEntry.getTypeLabel()));

		try {
			setContextAttribute(
				"[$EC_DOCUMENT_ENTRY_SUBTOTAL$]",
				ecDocumentEntry.getFormattedSubtotal());
			setContextAttribute(
				"[$EC_DOCUMENT_ENTRY_TAX_AMOUNT$]",
				ecDocumentEntry.getFormattedTaxAmount());
			setContextAttribute(
				"[$EC_DOCUMENT_ENTRY_TOTAL$]",
				ecDocumentEntry.getFormattedTotal());

			ECDocumentEntryExtraSettings ecDocumentEntryExtraSettings =
				new ECDocumentEntryExtraSettings(ecDocumentEntry);

			String vendorAmount = CurrencyUtil.format(
				ecDocumentEntry.getCurrencyCode(),
				ecDocumentEntryExtraSettings.getVendorAmount());

			setContextAttribute(
				"[$EC_DOCUMENT_ENTRY_VENDOR_AMOUNT$]", vendorAmount);

			String vendorAmountUSD = CurrencyUtil.format(
				ecDocumentEntry.getCurrencyCode(),
				ecDocumentEntryExtraSettings.getVendorAmountUSD());

			setContextAttribute(
				"[$EC_DOCUMENT_ENTRY_VENDOR_AMOUNT_USD$]", vendorAmountUSD);
		}
		catch (Exception e) {
		}
	}

	public void setContextAttributes(
		TrainingEvent trainingEvent, Locale locale, TimeZone timeZone) {

		if (locale == null) {
			locale = LocaleUtil.getDefault();
		}

		if (timeZone == null) {
			timeZone = TimeZoneUtil.getDefault();
		}

		setContextAttributes(trainingEvent, "TRAINING_EVENT");

		Format dateFormat = FastDateFormatFactoryUtil.getSimpleDateFormat(
			"MMMMM d", timeZone);

		StringBundler sb = new StringBundler(5);

		sb.append(dateFormat.format(trainingEvent.getStartDate()));

		if (!trainingEvent.isDurationSingleDay()) {
			sb.append(StringPool.SPACE);
			sb.append(StringPool.DASH);
			sb.append(StringPool.SPACE);
			sb.append(dateFormat.format(trainingEvent.getEndDate()));
		}

		setContextAttributes("[$CLASS_DATES$]", sb.toString());

		try {
			TrainingCourse trainingCourse = trainingEvent.getTrainingCourse();

			setContextAttributes("[$COURSE_NAME$]", trainingCourse.getName());

			String layoutURL = PortalUtil.getLayoutFullURL(
				OSBConstants.GROUP_GUEST_ID,
				OSBPortletKeys.OSB_TRAINING_SURVEY);

			String trainingSurveyURL =
				layoutURL + Portal.FRIENDLY_URL_SEPARATOR + "ddl/" +
					trainingEvent.getDDLRecordSetId();

			setContextAttribute("[$TRAINING_SURVEY_URL$]", trainingSurveyURL);
		}
		catch (Exception e) {
		}
	}

	protected static String getLineItemsHTML(
		ECDocumentEntry ecDocumentEntry, Locale locale, TimeZone timeZone) {

		List<ECDocumentItem> ecDocumentItems = Collections.emptyList();

		try {
			ecDocumentItems = ecDocumentEntry.getECDocumentItems();
		}
		catch (Exception e) {
		}

		if (ecDocumentItems.isEmpty()) {
			return StringPool.BLANK;
		}

		Format dateFormat = FastDateFormatFactoryUtil.getDate(
			DateFormat.MEDIUM, locale, timeZone);

		StringBundler sb = new StringBundler(23 * ecDocumentItems.size());

		for (ECDocumentItem ecDocumentItem : ecDocumentItems) {
			sb.append("<tr>");
			sb.append("<td style=\"padding: 3px 5px; text-align: center;\">");
			sb.append(ecDocumentItem.getQuantity());
			sb.append("</td><td style=\"padding: 3px 5px;\"><div>");
			sb.append(ecDocumentItem.getName());
			sb.append("</div>");

			ECDocumentItemExtraSettings ecDocumentItemExtraSettings =
				new ECDocumentItemExtraSettings(ecDocumentItem);

			if (ecDocumentItemExtraSettings.getStartDate() != null) {
				sb.append("<div>");
				sb.append(LanguageUtil.get(locale, "license-starts"));
				sb.append(StringPool.COLON);
				sb.append(StringPool.SPACE);
				sb.append(
					dateFormat.format(
						ecDocumentItemExtraSettings.getStartDate()));
				sb.append("</div>");
			}

			if (ecDocumentItemExtraSettings.getEndDate() != null) {
				sb.append("<div>");
				sb.append(LanguageUtil.get(locale, "license-ends"));
				sb.append(StringPool.COLON);
				sb.append(StringPool.SPACE);
				sb.append(
					dateFormat.format(
						ecDocumentItemExtraSettings.getEndDate()));
				sb.append("</div>");
			}

			sb.append("</td><td style=\"padding: 3px 5px;\">");
			sb.append(ecDocumentItem.getFormattedUnitPrice());
			sb.append("</td><td style=\"padding: 3px 5px;\">");
			sb.append(ecDocumentItem.getFormattedLinePrice());
			sb.append("</td></tr>");
		}

		return sb.toString();
	}

	protected String getAddressHTML(
		String street1, String street2, String street3, String city,
		long regionId, String zip, long countryId) {

		StringBundler sb = new StringBundler(16);

		sb.append("<div>");
		sb.append(street1);
		sb.append("</div><div>");

		if (Validator.isNotNull(street2)) {
			sb.append(street2);
			sb.append("</div><div>");
		}

		if (Validator.isNotNull(street3)) {
			sb.append(street3);
			sb.append("</div><div>");
		}

		sb.append(city);
		sb.append("</div><div>");

		if (regionId > 0) {
			try {
				Region region = RegionServiceUtil.getRegion(regionId);

				sb.append(region.getName());
				sb.append("</div><div>");
			}
			catch (Exception e) {
			}
		}

		sb.append(zip);
		sb.append("</div>");

		if (countryId > 0) {
			try {
				Country country = CountryServiceUtil.getCountry(countryId);

				sb.append("<div>");
				sb.append(country.getName());
				sb.append("</div>");
			}
			catch (Exception e) {
			}
		}

		return sb.toString();
	}

	protected String getNamespacedKey(String attribute, String namespace) {
		String key = attribute.replaceAll("([A-Z][^A-Z])", "_$1");

		key = key.replaceAll("([^A-Z_])([A-Z])", "$1_$2");

		return "[$" + namespace + StringPool.UNDERLINE + key.toUpperCase() +
			"$]";
	}

}