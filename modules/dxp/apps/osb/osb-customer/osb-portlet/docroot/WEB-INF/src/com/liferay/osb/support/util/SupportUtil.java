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

package com.liferay.osb.support.util;

import com.liferay.osb.model.OfferingEntry;
import com.liferay.osb.model.OfferingEntryConstants;
import com.liferay.osb.model.OrderEntry;
import com.liferay.osb.model.ProductEntry;
import com.liferay.osb.model.ProductEntryConstants;
import com.liferay.osb.service.OfferingEntryLocalServiceUtil;
import com.liferay.osb.service.OrderEntryLocalServiceUtil;
import com.liferay.petra.content.ContentUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.ListType;
import com.liferay.portal.kernel.service.ListTypeServiceUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.portlet.PortletPreferences;

/**
 * @author Amos Fong
 * @author Máté Thurzó
 */
public class SupportUtil {

	public static Map<Locale, String> getEmailAccountEntryTierBodyMap(
		PortletPreferences portletPreferences) {

		Map<Locale, String> map = LocalizationUtil.getLocalizationMap(
			portletPreferences, "emailAccountEntryTierBody");

		Locale defaultLocale = LocaleUtil.getDefault();

		String defaultValue = map.get(defaultLocale);

		if (Validator.isNotNull(defaultValue)) {
			return map;
		}

		map.put(
			defaultLocale,
			ContentUtil.get(
				SupportUtil.class.getClassLoader(),
				"com/liferay/osb/admin/dependencies" +
					"/email_account_entry_tier_body.tmpl"));

		return map;
	}

	public static Map<Locale, String> getEmailAccountEntryTierSubjectMap(
		PortletPreferences portletPreferences) {

		Map<Locale, String> map = LocalizationUtil.getLocalizationMap(
			portletPreferences, "emailAccountEntryTierSubject");

		Locale defaultLocale = LocaleUtil.getDefault();

		String defaultValue = map.get(defaultLocale);

		if (Validator.isNotNull(defaultValue)) {
			return map;
		}

		map.put(
			defaultLocale,
			ContentUtil.get(
				SupportUtil.class.getClassLoader(),
				"com/liferay/osb/admin/dependencies" +
					"/email_account_entry_tier_subject.tmpl"));

		return map;
	}

	public static int getListTypeIdFromName(
		String type, String name, boolean translate) {

		List<ListType> listTypes = ListTypeServiceUtil.getListTypes(type);

		for (ListType listType : listTypes) {
			String listTypeName = listType.getName();

			if (translate) {
				listTypeName = LanguageUtil.get(LocaleUtil.US, listTypeName);
			}

			if (listTypeName.equals(name)) {
				return (int)listType.getListTypeId();
			}
		}

		return 0;
	}

	public static Map<String, Integer> getOfferingEntriesMap(
		List<OrderEntry> orderEntries, boolean ignorePastEndDate) {

		Map<String, Integer> offeringEntriesMap = new HashMap<>();

		for (OrderEntry orderEntry : orderEntries) {
			List<OfferingEntry> offeringEntries =
				orderEntry.getOfferingEntries();

			for (OfferingEntry offeringEntry : offeringEntries) {
				if (ignorePastEndDate) {
					Date now = new Date();

					if (now.after(offeringEntry.getSupportEndDate())) {
						continue;
					}
				}

				String key = getKey(offeringEntry);

				Integer quantity = offeringEntriesMap.get(key);

				if (quantity == null) {
					quantity = offeringEntry.getQuantity();
				}
				else {
					quantity += offeringEntry.getQuantity();
				}

				offeringEntriesMap.put(key, quantity);
			}
		}

		return offeringEntriesMap;
	}

	public static Map<String, Integer> getOfferingEntriesMap(
		long accountEntryId) {

		List<OrderEntry> orderEntries =
			OrderEntryLocalServiceUtil.getAccountEntryOrderEntries(
				accountEntryId);

		return getOfferingEntriesMap(orderEntries, false);
	}

	public static Set<String> getSelfProvisioningProducts(long accountEntryId)
		throws PortalException {

		Set<String> selfProvisioningProducts = new TreeSet<>();

		LinkedHashMap params = new LinkedHashMap();

		params.put("license", StringPool.BLANK);
		params.put("productEntry", ProductEntryConstants.TYPE_PRIMARY);

		List<OfferingEntry> offeringEntries =
			OfferingEntryLocalServiceUtil.search(
				0, accountEntryId,
				new int[] {OfferingEntryConstants.TYPE_REGULAR},
				new int[] {OfferingEntryConstants.STATUS_ACTIVE}, 0, 0, 0, 0, 0,
				0, params, true, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		for (OfferingEntry offeringEntry : offeringEntries) {
			ProductEntry productEntry = offeringEntry.getProductEntry();

			if (productEntry.getEnvironment() ==
					ProductEntryConstants.ENVIRONMENT_DEVELOPMENT) {

				continue;
			}

			if (productEntry.isDigitalEnterprise()) {
				selfProvisioningProducts.add(
					ProductEntryConstants.ROOT_NAME_DIGITAL_ENTERPRISE);
			}

			if (productEntry.isPortal()) {
				selfProvisioningProducts.add(
					ProductEntryConstants.ROOT_NAME_PORTAL);
			}
		}

		return selfProvisioningProducts;
	}

	public static String serialize(List<OrderEntry> orderEntries) {
		List<Map<String, Object>> orderEntriesList = new ArrayList<>();

		for (OrderEntry orderEntry : orderEntries) {
			Map<String, Object> orderEntryAttributes = getOrderEntryAttributes(
				orderEntry);

			orderEntriesList.add(orderEntryAttributes);
		}

		return JSONFactoryUtil.looseSerializeDeep(orderEntriesList);
	}

	public static String serialize(OrderEntry orderEntry) {
		Map<String, Object> orderEntryAttributes = getOrderEntryAttributes(
			orderEntry);

		return JSONFactoryUtil.looseSerializeDeep(orderEntryAttributes);
	}

	protected static String getKey(OfferingEntry offeringEntry) {
		StringBundler sb = new StringBundler(20);

		sb.append(offeringEntry.getProductEntryId());
		sb.append(StringPool.POUND);
		sb.append(offeringEntry.getSupportResponseId());
		sb.append(StringPool.POUND);
		sb.append(offeringEntry.getProductDescription());
		sb.append(StringPool.POUND);
		sb.append(offeringEntry.getType());
		sb.append(StringPool.POUND);
		sb.append(offeringEntry.getVersion());
		sb.append(StringPool.POUND);
		sb.append(offeringEntry.getLicenses());
		sb.append(StringPool.POUND);
		sb.append(offeringEntry.getLicenseLifetime());
		sb.append(StringPool.POUND);
		sb.append(offeringEntry.getSupportTickets());
		sb.append(StringPool.POUND);
		sb.append(offeringEntry.getSupportLifetime());
		sb.append(StringPool.POUND);
		sb.append(offeringEntry.getSizing());

		Date supportEndDate = offeringEntry.getSupportEndDate();

		sb.append(supportEndDate.getTime());

		return sb.toString();
	}

	protected static Map<String, Object> getOrderEntryAttributes(
		OrderEntry orderEntry) {

		Map<String, Object> orderEntryAttributes =
			orderEntry.getModelAttributes();

		List<Map<String, Object>> offeringEntriesList = new ArrayList<>();

		List<OfferingEntry> offeringEntries = orderEntry.getOfferingEntries();

		for (OfferingEntry offeringEntry : offeringEntries) {
			offeringEntriesList.add(offeringEntry.getModelAttributes());
		}

		orderEntryAttributes.put("offeringEntries", offeringEntriesList);

		return orderEntryAttributes;
	}

}