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

package com.liferay.osb.model;

import com.liferay.osb.util.comparator.OfferingEntryGroupComparator;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.ArrayList;
import java.util.HashMap;
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