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

package com.liferay.osb.util.comparator;

import com.liferay.osb.model.OfferingEntryConstants;
import com.liferay.osb.model.ProductEntry;
import com.liferay.osb.service.ProductEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.Comparator;
import java.util.Map;

/**
 * @author Amos Fong
 */
public class OfferingEntryGroupComparator implements Comparator<String> {

	@Override
	public int compare(String key, String key2) {
		String[] keyArray = StringUtil.split(key);
		String[] key2Array = StringUtil.split(key2);

		Map<String, String> keyMap = MapUtil.toLinkedHashMap(
			keyArray, StringPool.EQUAL);
		Map<String, String> key2Map = MapUtil.toLinkedHashMap(
			key2Array, StringPool.EQUAL);

		int status = GetterUtil.getInteger(keyMap.get("status"));
		int status2 = GetterUtil.getInteger(key2Map.get("status"));

		int value = compareStatus(status, status2);

		if (value != 0) {
			return value;
		}

		int type = GetterUtil.getInteger(keyMap.get("type"));
		int type2 = GetterUtil.getInteger(key2Map.get("type"));

		value = compareType(type, type2);

		if (value != 0) {
			return value;
		}

		long supportEndDate = GetterUtil.getLong(keyMap.get("supportEndDate"));
		long supportEndDate2 = GetterUtil.getLong(
			key2Map.get("supportEndDate"));
		long supportLifetime = GetterUtil.getLong(
			keyMap.get("supportLifetime"));
		long supportLifetime2 = GetterUtil.getLong(
			key2Map.get("supportLifetime"));

		value = compareStartDate(
			supportEndDate - supportLifetime,
			supportEndDate2 - supportLifetime2);

		if (value != 0) {
			return value;
		}

		long productEntryId = GetterUtil.getLong(keyMap.get("productEntryId"));
		long productEntryId2 = GetterUtil.getLong(
			key2Map.get("productEntryId"));

		try {
			return compareProductEntry(productEntryId, productEntryId2);
		}
		catch (Exception e) {
		}

		return key.compareTo(key2);
	}

	public int compareProductEntry(long productEntryId, long productEntryId2)
		throws PortalException {

		ProductEntry productEntry =
			ProductEntryLocalServiceUtil.getProductEntry(productEntryId);
		ProductEntry productEntry2 =
			ProductEntryLocalServiceUtil.getProductEntry(productEntryId2);

		String name = productEntry.getName();
		String name2 = productEntry2.getName();

		return name.compareTo(name2);
	}

	public int compareStartDate(long startDate, long startDate2) {
		if (startDate > startDate2) {
			return -1;
		}

		if (startDate2 > startDate) {
			return 1;
		}

		return 0;
	}

	public int compareStatus(int status, int status2) {
		if (status != status2) {
			for (int curStatus : _ORDERED_STATUSES) {
				if (curStatus == status) {
					return -1;
				}

				if (curStatus == status2) {
					return 1;
				}
			}
		}

		return 0;
	}

	public int compareType(int type, int type2) {
		if (type != type2) {
			for (int curType : _ORDERED_TYPES) {
				if (curType == type) {
					return -1;
				}

				if (curType == type2) {
					return 1;
				}
			}
		}

		return 0;
	}

	private static final int[] _ORDERED_STATUSES = {
		OfferingEntryConstants.STATUS_ACTIVE,
		OfferingEntryConstants.STATUS_PENDING,
		OfferingEntryConstants.STATUS_ON_HOLD,
		OfferingEntryConstants.STATUS_CLOSED
	};

	private static final int[] _ORDERED_TYPES = {
		OfferingEntryConstants.TYPE_REGULAR, OfferingEntryConstants.TYPE_TRIAL
	};

}