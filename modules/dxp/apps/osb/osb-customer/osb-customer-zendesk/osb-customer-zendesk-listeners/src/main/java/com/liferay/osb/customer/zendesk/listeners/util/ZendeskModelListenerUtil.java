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

package com.liferay.osb.customer.zendesk.listeners.util;

import com.liferay.osb.customer.zendesk.connector.constants.ZendeskLocales;
import com.liferay.osb.customer.zendesk.connector.constants.ZendeskTagConstants;
import com.liferay.osb.model.ProductEntry;
import com.liferay.portal.kernel.model.Address;
import com.liferay.portal.kernel.model.Country;
import com.liferay.portal.kernel.model.Region;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import org.osgi.service.component.annotations.Component;

/**
 * @author Kyle Bischof
 */
@Component(immediate = true)
public class ZendeskModelListenerUtil {

	public static String convertAddressToString(Address address) {
		if (address == null) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(7);

		if (Validator.isNotNull(address.getStreet1())) {
			sb.append(address.getStreet1());
			sb.append(StringPool.NEW_LINE);
		}

		if (Validator.isNotNull(address.getStreet2())) {
			sb.append(address.getStreet2());
			sb.append(StringPool.NEW_LINE);
		}

		if (Validator.isNotNull(address.getStreet3())) {
			sb.append(address.getStreet3());
			sb.append(StringPool.NEW_LINE);
		}

		StringBundler sb2 = new StringBundler(7);

		if (Validator.isNotNull(address.getCity())) {
			sb2.append(address.getCity());
		}

		Region region = address.getRegion();

		if (Validator.isNotNull(region.getName())) {
			if (sb2.index() != 0) {
				sb2.append(StringPool.SPACE);
			}

			sb2.append(region.getName());
		}

		if (Validator.isNotNull(address.getZip())) {
			if (sb2.index() != 0) {
				sb2.append(StringPool.SPACE);
			}

			sb2.append(address.getZip());
		}

		Country country = address.getCountry();

		if (Validator.isNotNull(country.getName())) {
			if (sb2.index() != 0) {
				sb2.append(StringPool.NEW_LINE);
			}

			sb2.append(country.getName());
		}

		sb.append(sb2);

		return sb.toString();
	}

	public static String convertToTag(ProductEntry productEntry) {
		if (productEntry.isAnalyticsCloud()) {
			return ZendeskTagConstants.LIFERAY_ANALYTICS_CLOUD;
		}
		else if (productEntry.isCommerce()) {
			return ZendeskTagConstants.LIFERAY_COMMERCE;
		}
		else if (productEntry.isDeveloperTools()) {
			return ZendeskTagConstants.DEVELOPER_TOOLS;
		}
		else if (productEntry.isDeviceDetection()) {
			return ZendeskTagConstants.MOBILE_DEVICE_DETECTION;
		}
		else if (productEntry.isDigitalEnterprise()) {
			return ZendeskTagConstants.LIFERAY_DXP;
		}
		else if (productEntry.isEnterpriseSearch()) {
			return ZendeskTagConstants.ENTERPRISE_SEARCH;
		}
		else if (productEntry.isManagementTools()) {
			return ZendeskTagConstants.MANAGEMENT_TOOLS_LCS;
		}
		else if (productEntry.isMobileExperience()) {
			return ZendeskTagConstants.MOBILE_EXPERIENCE;
		}
		else if (productEntry.isPortal()) {
			return ZendeskTagConstants.LIFERAY_PORTAL;
		}
		else if (productEntry.isProductivityTools()) {
			return ZendeskTagConstants.PRODUCTIVITY_TOOLS_SYNC;
		}
		else if (productEntry.isSocialOffice()) {
			return ZendeskTagConstants.SOCIAL_OFFICE;
		}
		else {
			return StringPool.BLANK;
		}
	}

	public static String convertToZendeskLocale(String locale) {
		if (locale.equals("zh_CN")) {
			return ZendeskLocales.CHINA;
		}
		else if (locale.equals("en_US")) {
			return ZendeskLocales.US;
		}
		else if (locale.equals("es_ES")) {
			return ZendeskLocales.SPAIN;
		}
		else if (locale.equals("ja_JP")) {
			return ZendeskLocales.JAPAN;
		}
		else if (locale.equals("pt_BR")) {
			return ZendeskLocales.PORTUGAL;
		}
		else {
			return StringPool.BLANK;
		}
	}

}