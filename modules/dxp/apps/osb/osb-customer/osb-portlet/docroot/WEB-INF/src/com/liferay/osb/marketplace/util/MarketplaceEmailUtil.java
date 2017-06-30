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
import com.liferay.compat.portal.kernel.util.ListUtil;
import com.liferay.compat.portal.kernel.util.LocaleUtil;
import com.liferay.compat.portal.kernel.util.LocalizationUtil;
import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.ecommerce.model.ECDocumentEntry;
import com.liferay.osb.corpmembers.util.CorpMembersUtil;
import com.liferay.osb.model.AppEntry;
import com.liferay.osb.model.AppVersion;
import com.liferay.osb.model.AssetReceipt;
import com.liferay.osb.model.AssetReceiptLicense;
import com.liferay.osb.model.CorpEntry;
import com.liferay.osb.model.DeveloperEntry;
import com.liferay.osb.service.AppEntryLocalServiceUtil;
import com.liferay.osb.service.AppVersionLocalServiceUtil;
import com.liferay.osb.service.AssetReceiptLicenseLocalServiceUtil;
import com.liferay.osb.service.CorpEntryLocalServiceUtil;
import com.liferay.osb.service.DeveloperEntryLocalServiceUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.OSBPortletKeys;
import com.liferay.osb.util.OSBSubscriptionSender;
import com.liferay.osb.util.PortletPropsValues;
import com.liferay.osb.util.WorkflowConstants;
import com.liferay.osb.util.comparator.AssetReceiptLicenseEndDateComparator;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Country;
import com.liferay.portal.model.User;
import com.liferay.portal.service.PortletPreferencesLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.util.ContentUtil;

import java.text.Format;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletPreferences;

/**
 * @author Amos Fong
 * @author Ryan Park
 * @author Douglas Wong
 * @author Joan Kim
 * @author Haote Chou
 */
public class MarketplaceEmailUtil {

	public static Map<Locale, String> getLocalizationMap(String name)
		throws SystemException {

		PortletPreferences preferences = getPortletPreferences();

		Map<Locale, String> map = LocalizationUtil.getLocalizationMap(
			preferences, name);

		Locale defaultLocale = LocaleUtil.getDefault();

		String defaultValue = map.get(defaultLocale);

		if (Validator.isNotNull(defaultValue)) {
			return map;
		}

		map.put(defaultLocale, ContentUtil.get(getFileName(name)));

		return map;
	}

	public static PortletPreferences getPortletPreferences()
		throws SystemException {

		return PortletPreferencesLocalServiceUtil.getPreferences(
			OSBConstants.COMPANY_ID, OSBConstants.COMPANY_ID,
			PortletKeys.PREFS_OWNER_TYPE_COMPANY, PortletKeys.PREFS_PLID_SHARED,
			OSBPortletKeys.OSB_MARKETPLACE, null);
	}

	public static void sendAppEntryReviewMail(AssetReceipt assetReceipt)
		throws PortalException, SystemException {

		Map<Locale, String> subjectMap = getLocalizationMap(
			"emailAppEntryReviewSubject");
		Map<Locale, String> bodyMap = getLocalizationMap(
			"emailAppEntryReviewBody");

		User user = UserLocalServiceUtil.getUser(assetReceipt.getUserId());

		OSBSubscriptionSender subscriptionSender = new OSBSubscriptionSender();

		subscriptionSender.setCompanyId(user.getCompanyId());

		AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(
			assetReceipt.getProductClassPK());

		subscriptionSender.setContextAttributes(
			appEntry, user.getLocale(), user.getTimeZone());
		subscriptionSender.setFrom(FROM_ADDRESS, FROM_NAME);
		subscriptionSender.setHtmlFormat(true);
		subscriptionSender.setLocalizedBodyMap(bodyMap);
		subscriptionSender.setLocalizedSubjectMap(subjectMap);
		subscriptionSender.setMailId("app_entry", appEntry.getAppEntryId());
		subscriptionSender.setUserId(user.getUserId());

		subscriptionSender.addRuntimeSubscribers(
			user.getEmailAddress(), user.getFullName());

		subscriptionSender.flushNotificationsAsync();
	}

	public static void sendAppEntryStatusMail(AppEntry appEntry)
		throws PortalException, SystemException {

		Map<Locale, String> subjectMap = getLocalizationMap(
			"emailAppEntryStatusSubject");
		Map<Locale, String> bodyMap = getLocalizationMap(
			"emailAppEntryStatusBody");

		DeveloperEntry developerEntry = appEntry.getDeveloperEntry();

		List<User> users = Collections.emptyList();

		if (developerEntry.isCompany()) {
			CorpEntry corpEntry = CorpEntryLocalServiceUtil.getCorpEntry(
				developerEntry.getDossieraAccountKey());

			List<User> adminUsers = CorpMembersUtil.getCorpEntryUsers(
				corpEntry.getCorpEntryId(), StringPool.BLANK,
				OSBConstants.ROLE_OSB_CORP_ADMIN_ID, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null);

			users = ListUtil.copy(adminUsers);
		}
		else {
			users = new ArrayList<User>(1);
		}

		AppVersion appVersion = appEntry.getActionableAppVersion();

		User appVersionUser = UserLocalServiceUtil.getUser(
			appVersion.getUserId());

		if (!users.contains(appVersionUser)) {
			users.add(appVersionUser);
		}

		for (User user : users) {
			OSBSubscriptionSender subscriptionSender =
				new OSBSubscriptionSender();

			subscriptionSender.setCompanyId(user.getCompanyId());
			subscriptionSender.setContextAttributes(
				appEntry, user.getLocale(), user.getTimeZone());
			subscriptionSender.setContextAttributes(
				appVersion, user.getLocale(), user.getTimeZone());
			subscriptionSender.setFrom(FROM_ADDRESS, FROM_NAME);
			subscriptionSender.setHtmlFormat(true);
			subscriptionSender.setLocalizedBodyMap(bodyMap);
			subscriptionSender.setLocalizedSubjectMap(subjectMap);
			subscriptionSender.setMailId("app_entry", appEntry.getAppEntryId());
			subscriptionSender.setUserId(user.getUserId());

			subscriptionSender.addRuntimeSubscribers(
				user.getEmailAddress(), user.getFullName());

			subscriptionSender.flushNotificationsAsync();
		}

		String toAddress =
			PortletPropsValues.MARKETPLACE_NOTIFICATION_EMAIL_ADDRESS;

		if (Validator.isNotNull(toAddress) &&
			(appVersion.getStatus() != WorkflowConstants.STATUS_DRAFT)) {

			OSBSubscriptionSender subscriptionSender =
				new OSBSubscriptionSender();

			subscriptionSender.setCompanyId(PortalUtil.getDefaultCompanyId());
			subscriptionSender.setContextAttributes(appEntry, null, null);
			subscriptionSender.setContextAttributes(appVersion, null, null);
			subscriptionSender.setFrom(FROM_ADDRESS, FROM_NAME);
			subscriptionSender.setHtmlFormat(true);
			subscriptionSender.setLocalizedBodyMap(bodyMap);
			subscriptionSender.setLocalizedSubjectMap(subjectMap);
			subscriptionSender.setMailId("app_entry", appEntry.getAppEntryId());

			subscriptionSender.addRuntimeSubscribers(
				toAddress, "Marketplace Dev");

			subscriptionSender.flushNotificationsAsync();
		}
	}

	public static void sendAssetReceiptLicenseTrialUpdateEmail(
			AssetReceiptLicense assetReceiptLicense)
		throws PortalException, SystemException {

		Map<Locale, String> subjectMap = getLocalizationMap(
			"emailAssetReceiptLicenseTrialUpdateSubject");
		Map<Locale, String> bodyMap = getLocalizationMap(
			"emailAssetReceiptLicenseTrialUpdateBody");

		User user = UserLocalServiceUtil.getUser(
			assetReceiptLicense.getUserId());

		OSBSubscriptionSender subscriptionSender = new OSBSubscriptionSender();

		subscriptionSender.setCompanyId(user.getCompanyId());

		AppVersion appVersion = AppVersionLocalServiceUtil.getAppVersion(
			assetReceiptLicense.getProductClassPK());

		subscriptionSender.setContextAttributes(
			appVersion, user.getLocale(), user.getTimeZone());
		subscriptionSender.setFrom(FROM_ADDRESS, FROM_NAME);
		subscriptionSender.setHtmlFormat(true);
		subscriptionSender.setLocalizedBodyMap(bodyMap);
		subscriptionSender.setLocalizedSubjectMap(subjectMap);
		subscriptionSender.setMailId(
			"asset_receipt_license",
			assetReceiptLicense.getAssetReceiptLicenseId());
		subscriptionSender.setUserId(user.getUserId());

		subscriptionSender.addRuntimeSubscribers(
			user.getEmailAddress(), user.getFullName());

		subscriptionSender.flushNotificationsAsync();
	}

	public static void sendAssetReceiptMail(
			AssetReceipt assetReceipt, ECDocumentEntry ecDocumentEntry)
		throws PortalException, SystemException {

		Map<Locale, String> subjectMap = getLocalizationMap(
			"emailAssetReceiptSubject");
		Map<Locale, String> bodyMap = getLocalizationMap(
			"emailAssetReceiptBody");

		User user = UserLocalServiceUtil.getUser(assetReceipt.getUserId());

		AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(
			assetReceipt.getProductClassPK());

		OSBSubscriptionSender subscriptionSender = new OSBSubscriptionSender();

		subscriptionSender.setCompanyId(user.getCompanyId());
		subscriptionSender.setContextAttributes(
			appEntry, user.getLocale(), user.getTimeZone());
		subscriptionSender.setContextAttributes(
			assetReceipt, user.getLocale(), user.getTimeZone());
		subscriptionSender.setContextAttributes(
			ecDocumentEntry, user.getLocale(), user.getTimeZone());
		subscriptionSender.setContextAttribute(
			"[$LIFERAY_VAT_NUMBER$]", StringPool.BLANK);

		Country country = ecDocumentEntry.getShippingAddressCountry();

		if (ArrayUtil.contains(
				PortletPropsValues.VAT_EU_COUNTRIES, country.getA2())) {

			subscriptionSender.setContextAttribute(
				"[$LIFERAY_VAT_NUMBER$]", "(VAT #EU372002466)");
		}

		subscriptionSender.setFrom(FROM_ADDRESS, FROM_NAME);
		subscriptionSender.setHtmlFormat(true);
		subscriptionSender.setLocalizedBodyMap(bodyMap);
		subscriptionSender.setLocalizedSubjectMap(subjectMap);
		subscriptionSender.setMailId(
			"asset_receipt", assetReceipt.getAssetReceiptId());
		subscriptionSender.setUserId(user.getUserId());

		subscriptionSender.addRuntimeSubscribers(
			user.getEmailAddress(), user.getFullName());

		ECDocumentEntryExtraSettings ecDocumentEntryExtraSettings =
			new ECDocumentEntryExtraSettings(ecDocumentEntry);

		if (ecDocumentEntryExtraSettings.isEndUserEmailReceipt()) {
			String endUserFullName = PortalUtil.getFullName(
				ecDocumentEntryExtraSettings.getEndUserFirstName(),
				StringPool.BLANK,
				ecDocumentEntryExtraSettings.getEndUserLastName());

			subscriptionSender.addRuntimeSubscribers(
				ecDocumentEntryExtraSettings.getEndUserEmailAddress(),
				endUserFullName);
		}

		subscriptionSender.flushNotificationsAsync();
	}

	public static void sendAssetReceiptReminderMail(
			AssetReceipt assetReceipt, int daysRemaining)
		throws Exception {

		Map<Locale, String> bodyMap = null;
		Map<Locale, String> subjectMap = null;

		AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(
			assetReceipt.getProductClassPK());

		if (daysRemaining > 0) {
			subjectMap = getLocalizationMap("emailAssetReceiptReminderSubject");

			if (appEntry.isHidden()) {
				bodyMap = getLocalizationMap(
					"emailAssetReceiptHiddenReminderBody");
			}
			else {
				bodyMap = getLocalizationMap("emailAssetReceiptReminderBody");
			}
		}
		else {
			subjectMap = getLocalizationMap("emailAssetReceiptExpiredSubject");

			if (appEntry.isHidden()) {
				bodyMap = getLocalizationMap(
					"emailAssetReceiptExpiredHiddenBody");
			}
			else {
				bodyMap = getLocalizationMap("emailAssetReceiptExpiredBody");
			}
		}

		List<User> users = Collections.emptyList();

		if (assetReceipt.isOwnerCorpProject()) {
			List<User> adminUsers = CorpMembersUtil.getCorpProjectUsers(
				assetReceipt.getOwnerClassPK(), StringPool.BLANK,
				OSBConstants.ROLE_OSB_CORP_ADMIN_ID, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null);

			users = ListUtil.copy(adminUsers);
		}
		else {
			users = new ArrayList<User>(1);

			User assetReceiptOwnerUser = UserLocalServiceUtil.getUser(
				assetReceipt.getOwnerClassPK());

			users.add(assetReceiptOwnerUser);
		}

		String supportMessage = StringPool.BLANK;

		if (!appEntry.isDeveloperEntryLiferayInc()) {
			supportMessage = ContentUtil.get(
				"com/liferay/osb/marketplace/dependencies/" +
					"email_asset_receipt_support.tmpl");
		}

		for (User user : users) {
			OSBSubscriptionSender subscriptionSender =
				new OSBSubscriptionSender();

			subscriptionSender.setCompanyId(user.getCompanyId());
			subscriptionSender.setContextAttributes(
				appEntry, user.getLocale(), user.getTimeZone());
			subscriptionSender.setContextAttributes(
				"[$DAYS_REMAINING$]", String.valueOf(daysRemaining));
			subscriptionSender.setContextAttributes(
				"[$SUPPORT_MESSAGE$]", supportMessage);

			List<AssetReceiptLicense> assetReceiptLicenses =
				AssetReceiptLicenseLocalServiceUtil.getAssetReceiptLicenses(
					assetReceipt.getAssetReceiptId(), 0, 1,
					new AssetReceiptLicenseEndDateComparator(false));

			AssetReceiptLicense assetReceiptLicense = assetReceiptLicenses.get(
				0);

			Format dateFormat = FastDateFormatFactoryUtil.getDate(
				user.getLocale(), user.getTimeZone());

			subscriptionSender.setContextAttributes(
				"[$EXPIRATION_DATE$]",
				dateFormat.format(assetReceiptLicense.getEndDate()));

			subscriptionSender.setFrom(FROM_ADDRESS, FROM_NAME);
			subscriptionSender.setHtmlFormat(true);
			subscriptionSender.setLocalizedBodyMap(bodyMap);
			subscriptionSender.setLocalizedSubjectMap(subjectMap);
			subscriptionSender.setMailId(
				"asset_receipt", assetReceipt.getAssetReceiptId());
			subscriptionSender.setUserId(user.getUserId());

			subscriptionSender.addRuntimeSubscribers(
				user.getEmailAddress(), user.getFullName());

			subscriptionSender.flushNotificationsAsync();
		}
	}

	public static void sendPayoutNotificationEmail(
			ECDocumentEntry ecDocumentEntry)
		throws PortalException, SystemException {

		Map<Locale, String> subjectMap = getLocalizationMap(
			"emailECDocumentEntryPayoutSubject");
		Map<Locale, String> bodyMap = getLocalizationMap(
			"emailECDocumentEntryPayoutBody");

		User user = UserLocalServiceUtil.getUser(ecDocumentEntry.getUserId());

		OSBSubscriptionSender subscriptionSender = new OSBSubscriptionSender();

		subscriptionSender.setCompanyId(user.getCompanyId());

		DeveloperEntry developerEntry =
			DeveloperEntryLocalServiceUtil.getDeveloperEntry(
				ecDocumentEntry.getVendorClassPK());

		subscriptionSender.setContextAttributes(
			developerEntry, user.getLocale(), user.getTimeZone());

		subscriptionSender.setContextAttributes(
			ecDocumentEntry, user.getLocale(), user.getTimeZone());
		subscriptionSender.setFrom(FROM_ADDRESS, FROM_NAME);
		subscriptionSender.setHtmlFormat(true);
		subscriptionSender.setLocalizedBodyMap(bodyMap);
		subscriptionSender.setLocalizedSubjectMap(subjectMap);
		subscriptionSender.setMailId(
			"ec_document_entry", ecDocumentEntry.getEcDocumentEntryId());
		subscriptionSender.setUserId(user.getUserId());

		for (String marketplacePayoutNotificationEmailAddress :
				PortletPropsValues.
					MARKETPLACE_PAYOUT_NOTIFICATION_EMAIL_ADDRESSES) {

			subscriptionSender.addRuntimeSubscribers(
				marketplacePayoutNotificationEmailAddress,
				"Marketplace Payout Notification");
		}

		subscriptionSender.flushNotificationsAsync();
	}

	public static void sendPurchaseNotificationEmail(
			ECDocumentEntry ecDocumentEntry)
		throws PortalException, SystemException {

		Map<Locale, String> subjectMap = getLocalizationMap(
			"emailECDocumentEntrySubject");
		Map<Locale, String> bodyMap = getLocalizationMap(
			"emailECDocumentEntryBody");

		User user = UserLocalServiceUtil.getUser(ecDocumentEntry.getUserId());

		OSBSubscriptionSender subscriptionSender = new OSBSubscriptionSender();

		subscriptionSender.setCompanyId(user.getCompanyId());

		DeveloperEntry developerEntry =
			DeveloperEntryLocalServiceUtil.getDeveloperEntry(
				ecDocumentEntry.getVendorClassPK());

		subscriptionSender.setContextAttributes(
			developerEntry, user.getLocale(), user.getTimeZone());

		subscriptionSender.setContextAttributes(
			ecDocumentEntry, user.getLocale(), user.getTimeZone());
		subscriptionSender.setFrom(FROM_ADDRESS, FROM_NAME);
		subscriptionSender.setHtmlFormat(true);
		subscriptionSender.setLocalizedBodyMap(bodyMap);
		subscriptionSender.setLocalizedSubjectMap(subjectMap);
		subscriptionSender.setMailId(
			"ec_document_entry", ecDocumentEntry.getEcDocumentEntryId());
		subscriptionSender.setUserId(user.getUserId());

		for (String marketplacePurchaseNotificationEmailAddress :
				PortletPropsValues.
					MARKETPLACE_PURCHASE_NOTIFICATION_EMAIL_ADDRESSES) {

			subscriptionSender.addRuntimeSubscribers(
				marketplacePurchaseNotificationEmailAddress, StringPool.BLANK);
		}

		subscriptionSender.flushNotificationsAsync();
	}

	protected static String getFileName(String name) {
		String fileName = name.replaceAll("([A-Z][^A-Z])", "_$1");

		fileName = fileName.replaceAll("([^A-Z_])([A-Z])", "$1_$2");

		return "com/liferay/osb/marketplace/dependencies/" +
			fileName.toLowerCase() + ".tmpl";
	}

	protected static String getFreeLineItemsHTML(Locale locale) {
		StringBundler sb = new StringBundler(9);

		sb.append("<tr>");
		sb.append("<td style=\"padding: 3px 5px; text-align: center;\">-");
		sb.append("</td><td style=\"padding: 3px 5px;\">");
		sb.append(LanguageUtil.get(locale, "unlimited-instances"));
		sb.append("</td><td style=\"padding: 3px 5px;\">");
		sb.append(LanguageUtil.get(locale, "free"));
		sb.append("</td><td style=\"padding: 3px 5px;\">");
		sb.append(LanguageUtil.get(locale, "free"));
		sb.append("</td></tr>");

		return sb.toString();
	}

	protected static final String FROM_ADDRESS = "noreply@liferay.com";

	protected static final String FROM_NAME = "Liferay Marketplace";

}