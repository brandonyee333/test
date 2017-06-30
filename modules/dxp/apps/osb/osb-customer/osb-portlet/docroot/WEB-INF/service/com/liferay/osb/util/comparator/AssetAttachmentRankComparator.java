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

import com.liferay.osb.model.AssetAttachment;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * @author Joan Kim
 */
public class AssetAttachmentRankComparator extends OrderByComparator {

	public static final String ORDER_BY_ASC = "rank ASC";

	public static final String ORDER_BY_DESC = "rank DESC";

	public static final String[] ORDER_BY_FIELDS = {"rank"};

	public AssetAttachmentRankComparator() {
		this(true);
	}

	public AssetAttachmentRankComparator(boolean asc) {
		_asc = asc;
	}

	@Override
	public int compare(Object obj1, Object obj2) {
		AssetAttachment assetAttachment1 = (AssetAttachment)obj1;
		AssetAttachment assetAttachment2 = (AssetAttachment)obj2;

		int value = 0;

		int rank1 = assetAttachment1.getRank();
		int rank2 = assetAttachment2.getRank();

		if (rank1 < rank2) {
			value = -1;
		}
		else if (rank1 > rank2) {
			value = 1;
		}

		if (_asc) {
			return value;
		}
		else {
			return -value;
		}
	}

	@Override
	public String getOrderBy() {
		if (_asc) {
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
		return _asc;
	}

	private boolean _asc;

}