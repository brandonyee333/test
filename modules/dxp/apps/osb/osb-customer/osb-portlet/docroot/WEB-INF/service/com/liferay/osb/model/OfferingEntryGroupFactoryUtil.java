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

package com.liferay.osb.model;

import com.liferay.osb.service.OfferingEntryLocalServiceUtil;
import com.liferay.osb.util.comparator.OfferingEntryGroupComparator;
import com.liferay.osb.util.comparator.OfferingEntryPKComparator;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Amos Fong
 */
public class OfferingEntryGroupFactoryUtil {

	public static OfferingEntryGroup createOfferingEntryGroup(
			List<OfferingEntry> offeringEntries)
		throws PortalException {

		OfferingEntryGroup offeringEntryGroup = new OfferingEntryGroup();

		for (OfferingEntry offeringEntry : offeringEntries) {
			offeringEntryGroup.addOfferingEntry(offeringEntry);
		}

		return offeringEntryGroup;
	}

	public static Map<String, OfferingEntryGroup> createOfferingEntryGroupMap(
			List<OfferingEntry> offeringEntries)
		throws PortalException {

		Map<String, OfferingEntryGroup> offeringEntryGroupsMap = new TreeMap<>(
			new OfferingEntryGroupComparator());

		processOfferingEntries(offeringEntryGroupsMap, offeringEntries);

		return offeringEntryGroupsMap;
	}

	public static List<OfferingEntryGroup> createOfferingEntryGroups(
			List<OrderEntry> orderEntries)
		throws PortalException {

		Map<String, OfferingEntryGroup> offeringEntryGroupsMap =
			new HashMap<>();

		for (OrderEntry orderEntry : orderEntries) {
			processOfferingEntries(
				offeringEntryGroupsMap, orderEntry.getOfferingEntries());
		}

		return new ArrayList<>(offeringEntryGroupsMap.values());
	}

	public static List<OfferingEntryGroup> createOfferingEntryGroups(
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
			createOfferingEntryGroupMap(offeringEntries);

		return new ArrayList<>(offeringEntryGroupMap.values());
	}

	protected static void processOfferingEntries(
			Map<String, OfferingEntryGroup> offeringEntryGroupsMap,
			List<OfferingEntry> offeringEntries)
		throws PortalException {

		for (OfferingEntry offeringEntry : offeringEntries) {
			String key = offeringEntry.getKey();

			OfferingEntryGroup offeringEntryGroup = offeringEntryGroupsMap.get(
				key);

			if (offeringEntryGroup == null) {
				offeringEntryGroup = new OfferingEntryGroup();

				offeringEntryGroupsMap.put(key, offeringEntryGroup);
			}

			offeringEntryGroup.addOfferingEntry(offeringEntry);
		}
	}

}