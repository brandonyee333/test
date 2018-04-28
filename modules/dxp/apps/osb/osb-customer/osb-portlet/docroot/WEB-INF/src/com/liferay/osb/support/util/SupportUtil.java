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

package com.liferay.osb.support.util;

import com.liferay.osb.model.AccountEnvironmentConstants;
import com.liferay.osb.model.FileRepository;
import com.liferay.osb.model.OfferingEntry;
import com.liferay.osb.model.OfferingEntryGroup;
import com.liferay.osb.model.OfferingEntryGroupFactoryUtil;
import com.liferay.osb.model.OrderEntry;
import com.liferay.osb.service.OfferingEntryLocalServiceUtil;
import com.liferay.osb.util.PortletPropsKeys;
import com.liferay.osb.util.PortletPropsValues;
import com.liferay.osb.util.comparator.OfferingEntryPKComparator;
import com.liferay.petra.content.ContentUtil;
import com.liferay.portal.kernel.configuration.Filter;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.ListType;
import com.liferay.portal.kernel.service.ListTypeServiceUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.util.portlet.PortletProps;

import java.util.ArrayList;
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
		int envLFR, String envListType) {

		List<ListType> listTypes = ListTypeServiceUtil.getListTypes(
			envListType);

		listTypes = ListUtil.copy(listTypes);

		int[] envListTypeIds = AccountEnvironmentConstants.getEnvListTypeIds(
			envLFR, envListType);

		Long[] listTypeIds = ArrayUtil.toLongArray(envListTypeIds);

		Iterator<ListType> itr = listTypes.iterator();

		while (itr.hasNext()) {
			ListType listType = itr.next();

			if (!ArrayUtil.contains(listTypeIds, listType.getListTypeId())) {
				itr.remove();
			}
		}

		return listTypes;
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