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

package com.liferay.osb.asah.common.model.comparator;

import com.liferay.osb.asah.common.model.BreakdownItem;

import java.util.Comparator;

import org.apache.commons.lang3.math.NumberUtils;

/**
 * @author Marcos Martins
 */
public class BreakdownItemComparator implements Comparator<BreakdownItem> {

	public static BreakdownItemComparator getBreakdownItemComparator(
		String sortType) {

		if (sortType.equals("DESC")) {
			return _BREAKDOWN_ITEM_COMPARATOR_DESC;
		}

		return _BREAKDOWN_ITEM_COMPARATOR_ASC;
	}

	@Override
	public int compare(
		BreakdownItem breakdownItem1, BreakdownItem breakdownItem2) {

		Number value2 = breakdownItem2.getValue();

		Number value1 = breakdownItem1.getValue();

		int compare = Double.compare(
			value1.doubleValue(), value2.doubleValue());

		if (compare != 0) {
			if (_sortType.equals("DESC")) {
				return -compare;
			}

			return compare;
		}

		String internalName2 = breakdownItem2.getInternalName();

		String internalName1 = breakdownItem1.getInternalName();

		if (NumberUtils.isCreatable(internalName2) &&
			NumberUtils.isCreatable(internalName1)) {

			return Double.compare(
				NumberUtils.toDouble(internalName1),
				NumberUtils.toDouble(internalName2));
		}

		return internalName1.compareTo(internalName2);
	}

	private BreakdownItemComparator(String sortType) {
		_sortType = sortType;
	}

	private static final BreakdownItemComparator
		_BREAKDOWN_ITEM_COMPARATOR_ASC = new BreakdownItemComparator("ASC");

	private static final BreakdownItemComparator
		_BREAKDOWN_ITEM_COMPARATOR_DESC = new BreakdownItemComparator("DESC");

	private final String _sortType;

}