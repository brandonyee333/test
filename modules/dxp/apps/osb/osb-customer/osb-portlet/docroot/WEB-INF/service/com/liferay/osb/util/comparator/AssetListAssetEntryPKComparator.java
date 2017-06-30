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

import com.liferay.osb.model.AssetListAssetEntry;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * @author Douglas Wong
 */
public class AssetListAssetEntryPKComparator extends OrderByComparator {

	public static final String ORDER_BY_ASC = "assetListAssetEntryId ASC";

	public static final String ORDER_BY_DESC = "assetListAssetEntryId DESC";

	public static final String[] ORDER_BY_FIELDS = {"assetListAssetEntryId"};

	public AssetListAssetEntryPKComparator() {
		this(false);
	}

	public AssetListAssetEntryPKComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(Object obj1, Object obj2) {
		AssetListAssetEntry assetListAssetEntry1 = (AssetListAssetEntry)obj1;
		AssetListAssetEntry assetListAssetEntry2 = (AssetListAssetEntry)obj2;

		int value = 0;

		long assetListAssetEntryId1 =
			assetListAssetEntry1.getAssetListAssetEntryId();
		long assetListAssetEntryId2 =
			assetListAssetEntry2.getAssetListAssetEntryId();

		if (assetListAssetEntryId1 < assetListAssetEntryId2) {
			value = -1;
		}
		else if (assetListAssetEntryId1 > assetListAssetEntryId2) {
			value = 1;
		}

		if (_ascending) {
			return value;
		}
		else {
			return -value;
		}
	}

	@Override
	public String getOrderBy() {
		if (_ascending) {
			return ORDER_BY_ASC;
		}
		else {
			return ORDER_BY_DESC;
		}
	}

	@Override
	public String[] getOrderByFields() {
		return ORDER_BY_FIELDS;
	}

	@Override
	public boolean isAscending() {
		return _ascending;
	}

	private boolean _ascending;

}