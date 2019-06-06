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

package com.liferay.osb.util.comparator;

import com.liferay.osb.model.OfferingEntry;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * @author Amos Fong
 */
public class OfferingEntrySupportEndDateComparator extends OrderByComparator {

	public static final String ORDER_BY_ASC = "supportEndDate ASC";

	public static final String ORDER_BY_DESC = "supportEndDate DESC";

	public static final String[] ORDER_BY_FIELDS = {"supportEndDate"};

	public OfferingEntrySupportEndDateComparator() {
		this(false);
	}

	public OfferingEntrySupportEndDateComparator(boolean asc) {
		_asc = asc;
	}

	@Override
	public int compare(Object obj1, Object obj2) {
		OfferingEntry offeringEntry1 = (OfferingEntry)obj1;
		OfferingEntry offeringEntry2 = (OfferingEntry)obj2;

		int value = DateUtil.compareTo(
			offeringEntry1.getSupportEndDate(),
			offeringEntry2.getSupportEndDate());

		if (_asc) {
			return value;
		}

		return -value;
	}

	@Override
	public String getOrderBy() {
		if (_asc) {
			return ORDER_BY_ASC;
		}

		return ORDER_BY_DESC;
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