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

import com.liferay.osb.model.AccountEnvironmentConstants;
import com.liferay.osb.model.FileRepository;
import com.liferay.osb.model.OfferingEntry;
import com.liferay.osb.model.OfferingEntryGroup;
import com.liferay.osb.model.OfferingEntryGroupFactoryUtil;
import com.liferay.osb.model.OrderEntry;
import com.liferay.osb.service.OfferingEntryLocalServiceUtil;
import com.liferay.osb.service.OrderEntryLocalServiceUtil;
import com.liferay.osb.util.PortletPropsKeys;
import com.liferay.osb.util.PortletPropsValues;
import com.liferay.osb.util.comparator.OfferingEntryPKComparator;
import com.liferay.petra.content.ContentUtil;
import com.liferay.portal.kernel.configuration.Filter;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.ListType;
import com.liferay.portal.kernel.service.ListTypeServiceUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.util.portlet.PortletProps;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletPreferences;

/**
 * @author Amos Fong
 * @author Mate Thurzo
 */
public class SupportUtil {

	public static List<OfferingEntryGroup> getAvailableOfferingEntryGroups(
			long userId, long accountEntryId, int[] types, int[] statuses,
			int supportEndDateGTDay, int supportEndDateGTMonth,
			int supportEndDateGTYear, int supportEndDateLTDay,
			int supportEndDateLTMonth, int supportEndDateLTYear,
			LinkedHashMap<String, Object> params, boolean andSearch)
		throws PortalException {

		List<OfferingEntryGroup> availableOfferingEntryGroups =
			new ArrayList<>();

		List<OfferingEntryGroup> offeringEntryGroups = getOfferingEntryGroups(
			userId, accountEntryId, types, statuses, supportEndDateGTDay,
			supportEndDateGTMonth, supportEndDateGTYear, supportEndDateLTDay,
			supportEndDateLTMonth, supportEndDateLTYear, params, andSearch);

		for (OfferingEntryGroup offeringEntryGroup : offeringEntryGroups) {
			if (offeringEntryGroup.hasAvailableSupportTickets()) {
				availableOfferingEntryGroups.add(offeringEntryGroup);
			}
		}

		return availableOfferingEntryGroups;
	}

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
				"com/liferay/osb/support/dependencies" +
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
				"com/liferay/osb/support/dependencies" +
					"/email_account_entry_tier_subject.tmpl"));

		return map;
	}

	/* Refactor for zendesk
	 *
	public static List<FileRepository> getFileRepositories() {
		List<FileRepository> fileRepositoryList = new ArrayList<>();

		PortletPreferences portletPreferences = getPortletPreferences();

		String fileRepositories = portletPreferences.getValue(
			"fileRepositories", null);

		UnicodeProperties fileRepositoriesProperties = new UnicodeProperties(
			true);

		fileRepositoriesProperties.fastLoad(fileRepositories);

		if (fileRepositoriesProperties.isEmpty()) {
			return getDefaultFileRepositories();
		}

		for (String fileRepositoryProperties :
				fileRepositoriesProperties.values()) {

			FileRepository fileRepository = new FileRepository(
				fileRepositoryProperties);

			if (fileRepository.getStatus() ==
					WorkflowConstants.STATUS_INACTIVE) {

				continue;
			}

			fileRepositoryList.add(fileRepository);
		}

		return fileRepositoryList;
	}

	public static FileRepository getFileRepository(String fileRepositoryId) {
		if (Validator.isNull(fileRepositoryId)) {
			return null;
		}

		PortletPreferences portletPreferences = getPortletPreferences();

		String fileRepositories = portletPreferences.getValue(
			"fileRepositories", null);

		if (fileRepositories != null) {
			UnicodeProperties fileRepositoriesProperties =
				new UnicodeProperties(true);

			fileRepositoriesProperties.fastLoad(fileRepositories);

			String fileRepositoryProperties =
				fileRepositoriesProperties.getProperty(fileRepositoryId);

			return new FileRepository(fileRepositoryProperties);
		}
		else {
			return getDefaultFileRepository(fileRepositoryId);
		}
	}

	public static FileRepository getFileRepository(TicketEntry ticketEntry) {
		FileRepository defaultFileRepository = null;

		for (FileRepository fileRepository : getFileRepositories()) {
			long[] supportRegionIds = fileRepository.getSupportRegionIds();

			if (ArrayUtil.contains(
					supportRegionIds, ticketEntry.getSupportRegionId())) {

				return fileRepository;
			}

			if (defaultFileRepository == null) {
				defaultFileRepository = fileRepository;
			}
		}

		return defaultFileRepository;
	}

	public static FileRepository getFirstActiveFileRepository(
		Set<String> fileRepositoryIdsSet) {

		if (fileRepositoryIdsSet.isEmpty()) {
			return null;
		}

		for (FileRepository fileRepository : getFileRepositories()) {
			String fileRepositoryId = fileRepository.getFileRepositoryId();

			if (fileRepositoryIdsSet.contains(fileRepositoryId)) {
				return fileRepository;
			}
		}

		return null;
	}*/

	public static int getListTypeIdFromName(
		String type, String name, boolean translate) {

		List<ListType> listTypes = ListTypeServiceUtil.getListTypes(type);

		for (ListType listType : listTypes) {
			String listTypeName = listType.getName();

			if (translate) {
				listTypeName = LanguageUtil.get(Locale.US, listTypeName);
			}

			if (listTypeName.equals(name)) {
				return (int)listType.getListTypeId();
			}
		}

		return 0;
	}

	public static Map<String, Integer> getOfferingEntriesMap(
		List<OrderEntry> orderEntries) {

		Map<String, Integer> offeringEntriesMap = new HashMap<>();

		for (OrderEntry orderEntry : orderEntries) {
			List<OfferingEntry> offeringEntries =
				orderEntry.getOfferingEntries();

			for (OfferingEntry offeringEntry : offeringEntries) {
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

		return getOfferingEntriesMap(orderEntries);
	}

	public static List<OfferingEntryGroup> getOfferingEntryGroups(
			long userId, long accountEntryId, int[] types, int[] statuses,
			int supportEndDateGTDay, int supportEndDateGTMonth,
			int supportEndDateGTYear, int supportEndDateLTDay,
			int supportEndDateLTMonth, int supportEndDateLTYear,
			LinkedHashMap<String, Object> params, boolean andSearch)
		throws PortalException {

		return getOfferingEntryGroups(
			userId, accountEntryId, types, statuses, supportEndDateGTDay,
			supportEndDateGTMonth, supportEndDateGTYear, supportEndDateLTDay,
			supportEndDateLTMonth, supportEndDateLTYear, params, andSearch,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	public static List<OfferingEntryGroup> getOfferingEntryGroups(
			long userId, long accountEntryId, int[] types, int[] statuses,
			int supportEndDateGTDay, int supportEndDateGTMonth,
			int supportEndDateGTYear, int supportEndDateLTDay,
			int supportEndDateLTMonth, int supportEndDateLTYear,
			LinkedHashMap<String, Object> params, boolean andSearch, int start,
			int end)
		throws PortalException {

		List<OfferingEntry> offeringEntries =
			OfferingEntryLocalServiceUtil.search(
				userId, accountEntryId, types, statuses, supportEndDateGTDay,
				supportEndDateGTMonth, supportEndDateGTYear,
				supportEndDateLTDay, supportEndDateLTMonth,
				supportEndDateLTYear, params, andSearch, start, end,
				new OfferingEntryPKComparator(true));

		Map<String, OfferingEntryGroup> offeringEntryGroupMap =
			OfferingEntryGroupFactoryUtil.createOfferingEntryGroupMap(
				offeringEntries);

		return new ArrayList<>(offeringEntryGroupMap.values());
	}

	public static List<ListType> getPortalEnvListTypes(
			int envLFR, String envListType)
		throws SystemException {

		return getPortalEnvListTypes(envLFR, envListType, StringPool.BLANK);
	}

	public static List<ListType> getPortalEnvListTypes(
			int envLFR, String envListType, String sublistType)
		throws SystemException {

		List<ListType> listTypes = ListTypeServiceUtil.getListTypes(
			envListType);

		listTypes = ListUtil.copy(listTypes);

		int[] listTypeIds = TicketEntryConstants.getEnvListTypeIds(
			envLFR, envListType + StringPool.PERIOD + sublistType);

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
		long accountEntryId, int productEntryEnvironment) {

		try {
			List<OfferingEntry> offeringEntries =
				OfferingEntryLocalServiceUtil.getAccountEntryOfferingEntries(
					accountEntryId);

			for (OfferingEntry offeringEntry : offeringEntries) {
				ProductEntry productEntry = offeringEntry.getProductEntry();

				if (productEntry.isEnterpriseSearch() &&
					(productEntry.getEnvironment() ==
						productEntryEnvironment)) {

					return true;
				}
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return false;
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

	protected static List<FileRepository> getDefaultFileRepositories() {
		List<FileRepository> fileRepositoryList = new ArrayList<>();

		for (String fileRepositoryId : PortletPropsValues.FILE_REPOSITORY_IDS) {
			fileRepositoryList.add(getDefaultFileRepository(fileRepositoryId));
		}

		return fileRepositoryList;
	}

	protected static FileRepository getDefaultFileRepository(
		String fileRepositoryId) {

		if (Validator.isNull(fileRepositoryId)) {
			return null;
		}

		String host = PortletProps.get(
			PortletPropsKeys.FILE_REPOSITORY_HOST,
			new Filter(fileRepositoryId));
		String name = PortletProps.get(
			PortletPropsKeys.FILE_REPOSITORY_NAME,
			new Filter(fileRepositoryId));
		long[] supportRegionIds = GetterUtil.getLongValues(
			PortletProps.getArray(
				PortletPropsKeys.FILE_REPOSITORY_SUPPORT_REGION_IDS,
				new Filter(fileRepositoryId)));

		return new FileRepository(
			fileRepositoryId, name, host, supportRegionIds);
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

	private static Log _log = LogFactoryUtil.getLog(SupportUtil.class);

}