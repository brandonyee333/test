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

package com.liferay.osb.license.util;

import com.liferay.osb.admin.util.KeyGenerator;
import com.liferay.osb.model.LicenseEntryConstants;
import com.liferay.osb.model.LicenseKey;
import com.liferay.osb.model.LicenseKeySet;
import com.liferay.osb.model.OfferingEntry;
import com.liferay.osb.service.LicenseKeyLocalServiceUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.OSBFileUtil;
import com.liferay.osb.util.OSBPortletKeys;
import com.liferay.osb.util.comparator.LicenseKeyExpirationDateComparator;
import com.liferay.osb.util.comparator.LicenseKeyStartDateComparator;
import com.liferay.petra.content.ContentUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.PortletPreferencesLocalServiceUtil;
import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortletKeys;
import com.liferay.portal.kernel.util.PropertiesUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.util.xml.DocUtil;

import java.io.File;

import java.text.DateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.TimeZone;

import javax.portlet.PortletPreferences;

/**
 * @author Amos Fong
 */
public class LicenseUtil {

	public static String exportToEncodedLicenseFile(LicenseKey licenseKey)
		throws Exception {

		Properties licenseProperties = new Properties();

		licenseProperties.setProperty("serverId", licenseKey.getServerId());
		licenseProperties.setProperty("licenseKey", licenseKey.getKey());

		String licenseFileDecoded = PropertiesUtil.toString(licenseProperties);

		String licenseFileEncoded = Base64.objectToString(licenseFileDecoded);

		return licenseFileEncoded;
	}

	public static File exportToFile(LicenseKey licenseKey) throws Exception {
		File file = OSBFileUtil.createTempFile(
			getLicenseKeyFileName(licenseKey));

		FileUtil.write(file, exportToXML(licenseKey));

		return file;
	}

	public static String exportToXML(LicenseKey licenseKey) throws Exception {
		Document document = null;

		Map<String, String> properties = KeyGenerator.getProperties(licenseKey);

		String key = licenseKey.getKey();

		if (licenseKey.getLicenseVersion() >= 3) {
			document = exportToXMLVersion3_4(properties, key, false);
		}
		else {
			document = exportToXMLVersion2(properties, key);
		}

		return document.formattedString();
	}

	public static String exportToXML(LicenseKeySet licenseKeySet)
		throws Exception {

		if (!isAggregate(licenseKeySet.getLicenseKeySetId())) {
			return StringPool.BLANK;
		}

		List<LicenseKey> licenseKeys =
			LicenseKeyLocalServiceUtil.getLicenseKeySetLicenseKeys(
				licenseKeySet.getLicenseKeySetId());

		licenseKeys = ListUtil.copy(licenseKeys);

		Iterator<LicenseKey> itr = licenseKeys.iterator();

		while (itr.hasNext()) {
			LicenseKey licenseKey = itr.next();

			if (!licenseKey.isActive()) {
				itr.remove();
			}
		}

		LicenseKey firstLicenseKey = licenseKeys.get(0);

		Map<String, String> properties = KeyGenerator.getProperties(
			firstLicenseKey);

		if (firstLicenseKey.getLicenseVersion() >= 4) {
			String licenseEntryType = firstLicenseKey.getLicenseEntryType();

			if (licenseEntryType.equals(LicenseEntryConstants.TYPE_LIMITED) ||
				licenseEntryType.equals(
					LicenseEntryConstants.TYPE_PRODUCTION)) {

				properties.put(
					"maxServers", String.valueOf(licenseKeys.size()));
			}
		}

		Document document = exportToXMLVersion3_4(
			properties, StringPool.BLANK, true);

		Element rootElement = document.getRootElement();

		List<String> hostNames = new ArrayList<>();
		List<String> ipAddresses = new ArrayList<>();
		List<String> macAddresses = new ArrayList<>();

		Element serversElement = rootElement.addElement("servers");

		for (LicenseKey licenseKey : licenseKeys) {
			Map<String, String> curProperties = KeyGenerator.getProperties(
				licenseKey);

			Element serverElement = serversElement.addElement("server");

			exportServerToXML(serverElement, curProperties);

			String curHostName = curProperties.get("hostNames");

			if (Validator.isNotNull(curHostName)) {
				hostNames.add(curHostName);
			}

			List<String> curIpAddresses = ListUtil.fromArray(
				StringUtil.split(curProperties.get("ipAddresses")));

			ipAddresses.addAll(curIpAddresses);

			List<String> curMacAddresses = ListUtil.fromArray(
				StringUtil.split(curProperties.get("macAddresses")));

			macAddresses.addAll(curMacAddresses);
		}

		properties.put("hostNames", StringUtil.merge(hostNames));
		properties.put("ipAddresses", StringUtil.merge(ipAddresses));
		properties.put("macAddresses", StringUtil.merge(macAddresses));

		String key = KeyGenerator.generate(properties);

		DocUtil.add(rootElement, "key", key);

		return document.formattedString();
	}

	public static Map<Locale, String>
		getEmailNotificationTrialLicenseBodyMap() {

		Map<Locale, String> map = LocalizationUtil.getLocalizationMap(
			getPortletPreferences(), "emailNotificationTrialLicenseBody");

		Locale defaultLocale = LocaleUtil.getDefault();

		String defaultValue = map.get(defaultLocale);

		if (Validator.isNotNull(defaultValue)) {
			return map;
		}

		map.put(
			defaultLocale,
			ContentUtil.get(
				LicenseUtil.class.getClassLoader(),
				"com/liferay/osb/license/dependencies" +
					"/email_notification_trial_license_body.tmpl"));

		return map;
	}

	public static Map<Locale, String>
		getEmailNotificationTrialLicenseSubjectMap() {

		Map<Locale, String> map = LocalizationUtil.getLocalizationMap(
			getPortletPreferences(), "emailNotificationTrialLicenseSubject");

		Locale defaultLocale = LocaleUtil.getDefault();

		String defaultValue = map.get(defaultLocale);

		if (Validator.isNotNull(defaultValue)) {
			return map;
		}

		map.put(
			defaultLocale,
			ContentUtil.get(
				LicenseUtil.class.getClassLoader(),
				"com/liferay/osb/license/dependencies" +
					"/email_notification_trial_license_subject.tmpl"));

		return map;
	}

	public static Map<Locale, String> getEmailRegisteredTrialLicenseBodyMap() {
		Map<Locale, String> map = LocalizationUtil.getLocalizationMap(
			getPortletPreferences(), "emailRegisteredTrialLicenseBody");

		Locale defaultLocale = LocaleUtil.getDefault();

		String defaultValue = map.get(defaultLocale);

		if (Validator.isNotNull(defaultValue)) {
			return map;
		}

		map.put(
			defaultLocale,
			ContentUtil.get(
				LicenseUtil.class.getClassLoader(),
				"com/liferay/osb/license/dependencies" +
					"/email_registered_trial_license_body.tmpl"));

		return map;
	}

	public static Map<Locale, String>
		getEmailRegisteredTrialLicenseSubjectMap() {

		Map<Locale, String> map = LocalizationUtil.getLocalizationMap(
			getPortletPreferences(), "emailRegisteredTrialLicenseSubject");

		Locale defaultLocale = LocaleUtil.getDefault();

		String defaultValue = map.get(defaultLocale);

		if (Validator.isNotNull(defaultValue)) {
			return map;
		}

		map.put(
			defaultLocale,
			ContentUtil.get(
				LicenseUtil.class.getClassLoader(),
				"com/liferay/osb/license/dependencies" +
					"/email_registered_trial_license_subject.tmpl"));

		return map;
	}

	public static String getLicenseKeyFileName(LicenseKey licenseKey) {
		StringBundler sb = new StringBundler(7);

		String productEntryName = StringUtil.extractChars(
			licenseKey.getProductEntryName());

		if (productEntryName.startsWith("Portal")) {
			productEntryName = productEntryName.substring(6);
		}

		sb.append("activation-key-");
		sb.append(productEntryName);
		sb.append(StringPool.DASH);
		sb.append(licenseKey.getProductVersionLabel());

		try {
			LicenseKeySet licenseKeySet = licenseKey.getLicenseKeySet();

			sb.append(StringPool.DASH);
			sb.append(StringUtil.extractChars(licenseKeySet.getName()));
		}
		catch (Exception e) {
		}

		sb.append(".xml");

		String fileName = StringUtil.replace(
			sb.toString(), CharPool.SPACE, StringPool.BLANK);

		return StringUtil.toLowerCase(fileName);
	}

	public static OrderByComparator getLicenseKeyOrderByComparator(
		String orderByCol, String orderByType) {

		boolean orderByAsc = false;

		if (orderByType.equals("asc")) {
			orderByAsc = true;
		}

		OrderByComparator orderByComparator = null;

		if (orderByCol.equals("start-date")) {
			orderByComparator = new LicenseKeyStartDateComparator(orderByAsc);
		}
		else if (orderByCol.equals("expiration-date")) {
			orderByComparator = new LicenseKeyExpirationDateComparator(
				orderByAsc);
		}
		else {
			orderByComparator = new LicenseKeyStartDateComparator(orderByAsc);
		}

		return orderByComparator;
	}

	public static PortletPreferences getPortletPreferences() {
		long ownerId = OSBConstants.COMPANY_ID;
		int ownerType = PortletKeys.PREFS_OWNER_TYPE_COMPANY;
		long plid = PortletKeys.PREFS_PLID_SHARED;
		String portletId = OSBPortletKeys.OSB_ADMIN;
		String defaultPreferences = null;

		return PortletPreferencesLocalServiceUtil.getPreferences(
			OSBConstants.COMPANY_ID, ownerId, ownerType, plid, portletId,
			defaultPreferences);
	}

	public static boolean isAggregate(long licenseKeySetId)
		throws PortalException {

		List<LicenseKey> licenseKeys =
			LicenseKeyLocalServiceUtil.getLicenseKeySetLicenseKeys(
				licenseKeySetId);

		licenseKeys = ListUtil.copy(licenseKeys);

		Iterator<LicenseKey> itr = licenseKeys.iterator();

		while (itr.hasNext()) {
			LicenseKey licenseKey = itr.next();

			if (!licenseKey.isActive()) {
				itr.remove();
			}
		}

		if (licenseKeys.isEmpty() || (licenseKeys.size() <= 1)) {
			return false;
		}

		LicenseKey firstLicenseKey = licenseKeys.get(0);

		OfferingEntry offeringEntry = firstLicenseKey.getOfferingEntry();
		String licenseEntryType = firstLicenseKey.getLicenseEntryType();
		Date startDate = firstLicenseKey.getStartDate();
		Date expirationDate = firstLicenseKey.getExpirationDate();

		for (LicenseKey licenseKey : licenseKeys) {
			if (licenseKey.getLicenseVersion() < 3) {
				return false;
			}

			String curLicenseEntryType = licenseKey.getLicenseEntryType();

			if (!curLicenseEntryType.equals(
					LicenseEntryConstants.TYPE_PER_USER) &&
				!curLicenseEntryType.equals(
					LicenseEntryConstants.TYPE_PRODUCTION)) {

				return false;
			}

			if (curLicenseEntryType.equals(
					LicenseEntryConstants.TYPE_PER_USER)) {

				OfferingEntry curOfferingEntry = licenseKey.getOfferingEntry();

				if (offeringEntry.getMaxConcurrentUsers() !=
						curOfferingEntry.getMaxConcurrentUsers()) {

					return false;
				}

				if (offeringEntry.getMaxUsers() !=
						curOfferingEntry.getMaxUsers()) {

					return false;
				}
			}

			if (!licenseEntryType.equals(curLicenseEntryType)) {
				return false;
			}

			if (!DateUtil.equals(startDate, licenseKey.getStartDate())) {
				return false;
			}

			if (!DateUtil.equals(
					expirationDate, licenseKey.getExpirationDate())) {

				return false;
			}
		}

		return true;
	}

	public static boolean isRenewAggregate(long licenseKeySetId)
		throws PortalException {

		if (!isAggregate(licenseKeySetId)) {
			return false;
		}

		List<LicenseKey> licenseKeys =
			LicenseKeyLocalServiceUtil.getLicenseKeySetLicenseKeys(
				licenseKeySetId);

		licenseKeys = ListUtil.copy(licenseKeys);

		Iterator<LicenseKey> itr = licenseKeys.iterator();

		while (itr.hasNext()) {
			LicenseKey licenseKey = itr.next();

			if (!licenseKey.isActive()) {
				itr.remove();
			}
		}

		if (licenseKeys.isEmpty()) {
			return false;
		}

		LicenseKey firstLicenseKey = licenseKeys.get(0);

		if (!firstLicenseKey.canRenew()) {
			return false;
		}

		return true;
	}

	public static String trimText(String text) {

		// Copied from org.dom4j.tree.AbstractBranch.getTextTrim()

		StringBuffer textContent = new StringBuffer();

		StringTokenizer tokenizer = new StringTokenizer(text);

		while (tokenizer.hasMoreTokens()) {
			String str = tokenizer.nextToken();

			textContent.append(str);

			if (tokenizer.hasMoreTokens()) {
				textContent.append(" ");
			}
		}

		return textContent.toString();
	}

	protected static void exportServerToXML(
		Element element, Map<String, String> properties) {

		Element hostNamesElement = element.addElement("host-names");

		String[] hostNames = StringUtil.split(properties.get("hostNames"));

		for (String hostName : hostNames) {
			DocUtil.add(hostNamesElement, "host-name", hostName);
		}

		Element ipAddressesElement = element.addElement("ip-addresses");

		String[] ipAddresses = StringUtil.split(properties.get("ipAddresses"));

		for (String ipAddress : ipAddresses) {
			DocUtil.add(ipAddressesElement, "ip-address", ipAddress);
		}

		Element macAddressesElement = element.addElement("mac-addresses");

		String[] macAddresses = StringUtil.split(
			properties.get("macAddresses"));

		for (String macAddress : macAddresses) {
			DocUtil.add(macAddressesElement, "mac-address", macAddress);
		}

		String[] serverIds = StringUtil.split(properties.get("serverIds"));

		if (serverIds.length > 0) {
			Element serverIdElement = element.addElement("server-ids");

			for (String serverId : serverIds) {
				DocUtil.add(serverIdElement, "server-id", serverId);
			}
		}
	}

	protected static Document exportToXMLVersion2(
			Map<String, String> properties, String key)
		throws Exception {

		Document document = SAXReaderUtil.createDocument();

		Element rootElement = document.addElement("license");

		String licenseEntryType = properties.get("type");
		String licenseVersion = properties.get("version");

		DocUtil.add(
			rootElement, "account-name", properties.get("accountEntryName"));
		DocUtil.add(rootElement, "owner", properties.get("owner"));
		DocUtil.add(rootElement, "description", properties.get("description"));
		DocUtil.add(
			rootElement, "product-name", properties.get("productEntryName"));
		DocUtil.add(
			rootElement, "product-version", properties.get("productVersion"));
		DocUtil.add(
			rootElement, "license-name", properties.get("licenseEntryName"));
		DocUtil.add(rootElement, "license-type", licenseEntryType);
		DocUtil.add(rootElement, "license-version", licenseVersion);

		DateFormat longDateFormatDateTime = DateFormat.getDateTimeInstance(
			DateFormat.FULL, DateFormat.FULL, LocaleUtil.US);

		TimeZone timeZone = TimeZone.getTimeZone("GMT");

		longDateFormatDateTime.setTimeZone(timeZone);

		if (licenseEntryType.equals(LicenseEntryConstants.TYPE_TRIAL)) {
			DocUtil.add(rootElement, "start-date", "Registration");

			long lifetime = GetterUtil.getLong(properties.get("lifetime"));

			DocUtil.add(rootElement, "lifetime", String.valueOf(lifetime));
		}
		else {
			Date startDate = new Date(
				GetterUtil.getLong(properties.get("startDate")));

			DocUtil.add(
				rootElement, "start-date",
				longDateFormatDateTime.format(startDate));

			Date expirationDate = new Date(
				GetterUtil.getLong(properties.get("expirationDate")));

			DocUtil.add(
				rootElement, "expiration-date",
				longDateFormatDateTime.format(expirationDate));
		}

		if (licenseEntryType.equals(LicenseEntryConstants.TYPE_CLUSTER) ||
			licenseEntryType.equals(
				LicenseEntryConstants.TYPE_DEVELOPER_CLUSTER)) {

			DocUtil.add(
				rootElement, "max-servers", properties.get("maxServers"));
		}

		if (licenseEntryType.equals(LicenseEntryConstants.TYPE_DEVELOPER) ||
			licenseEntryType.equals(
				LicenseEntryConstants.TYPE_DEVELOPER_CLUSTER) ||
			licenseEntryType.equals(LicenseEntryConstants.TYPE_TRIAL)) {

			DocUtil.add(
				rootElement, "max-http-sessions",
				properties.get("maxHttpSessions"));
		}

		if (licenseEntryType.equals(LicenseEntryConstants.TYPE_PRODUCTION)) {
			Element serverIdElement = rootElement.addElement("server-ids");

			String[] serverIds = StringUtil.split(properties.get("serverIds"));

			for (String serverId : serverIds) {
				DocUtil.add(serverIdElement, "server-id", serverId);
			}
		}

		DocUtil.add(rootElement, "key", key);

		return document;
	}

	protected static Document exportToXMLVersion3_4(
			Map<String, String> properties, String key, boolean aggregate)
		throws Exception {

		Document document = SAXReaderUtil.createDocument();

		Element rootElement = document.addElement("license");

		String productId = properties.get("productId");
		String licenseEntryType = properties.get("type");
		long licenseVersion = GetterUtil.getLong(properties.get("version"));

		if (Validator.isNull(productId)) {
			DocUtil.add(
				rootElement, "account-name",
				properties.get("accountEntryName"));
		}

		DocUtil.add(rootElement, "owner", properties.get("owner"));
		DocUtil.add(rootElement, "description", properties.get("description"));
		DocUtil.add(
			rootElement, "product-name", properties.get("productEntryName"));

		if (Validator.isNotNull(productId)) {
			DocUtil.add(rootElement, "product-id", productId);
		}

		DocUtil.add(
			rootElement, "product-version", properties.get("productVersion"));

		if (Validator.isNull(productId)) {
			DocUtil.add(
				rootElement, "license-name",
				properties.get("licenseEntryName"));
		}

		DocUtil.add(rootElement, "license-type", licenseEntryType);
		DocUtil.add(
			rootElement, "license-version", String.valueOf(licenseVersion));

		DateFormat longDateFormatDateTime = DateFormat.getDateTimeInstance(
			DateFormat.FULL, DateFormat.FULL, LocaleUtil.US);

		TimeZone timeZone = TimeZone.getTimeZone("GMT");

		longDateFormatDateTime.setTimeZone(timeZone);

		Date startDate = new Date(
			GetterUtil.getLong(properties.get("startDate")));

		DocUtil.add(
			rootElement, "start-date",
			longDateFormatDateTime.format(startDate));

		Date expirationDate = new Date(
			GetterUtil.getLong(properties.get("expirationDate")));

		DocUtil.add(
			rootElement, "expiration-date",
			longDateFormatDateTime.format(expirationDate));

		if (licenseEntryType.equals(LicenseEntryConstants.TYPE_CLUSTER) ||
			((licenseVersion >= 4) &&
			 (licenseEntryType.equals(LicenseEntryConstants.TYPE_LIMITED) ||
			  licenseEntryType.equals(
				  LicenseEntryConstants.TYPE_PRODUCTION)))) {

			DocUtil.add(
				rootElement, "max-servers", properties.get("maxServers"));
		}

		if (licenseEntryType.equals(LicenseEntryConstants.TYPE_DEVELOPER) ||
			licenseEntryType.equals(
				LicenseEntryConstants.TYPE_DEVELOPER_CLUSTER)) {

			DocUtil.add(
				rootElement, "max-http-sessions",
				properties.get("maxHttpSessions"));
		}

		if (licenseEntryType.equals(LicenseEntryConstants.TYPE_PER_USER)) {
			String maxConcurrentUsers = properties.get("maxConcurrentUsers");

			if (Validator.isNotNull(maxConcurrentUsers)) {
				DocUtil.add(
					rootElement, "max-concurrent-users",
					properties.get("maxConcurrentUsers"));
			}

			String maxUsers = properties.get("maxUsers");

			if (Validator.isNotNull(maxUsers)) {
				DocUtil.add(
					rootElement, "max-users", properties.get("maxUsers"));
			}
		}

		if (!aggregate) {
			if (licenseEntryType.equals(LicenseEntryConstants.TYPE_CLUSTER) ||
				licenseEntryType.equals(LicenseEntryConstants.TYPE_LIMITED) ||
				licenseEntryType.equals(LicenseEntryConstants.TYPE_PER_USER) ||
				licenseEntryType.equals(
					LicenseEntryConstants.TYPE_PRODUCTION)) {

				exportServerToXML(rootElement, properties);
			}

			DocUtil.add(rootElement, "key", key);
		}

		return document;
	}

}