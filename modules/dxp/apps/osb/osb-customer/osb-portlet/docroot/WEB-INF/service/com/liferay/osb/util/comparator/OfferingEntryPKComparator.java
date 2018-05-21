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
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * @author Lin Cui
 */
public class OfferingEntryPKComparator extends OrderByComparator {

	public static final String ORDER_BY_ASC = "offeringEntryId ASC";

	public static final String ORDER_BY_DESC = "offeringEntryId DESC";

	public static final String[] ORDER_BY_FIELDS = {"offeringEntryId"};

	public OfferingEntryPKComparator() {
		this(false);
	}

	public OfferingEntryPKComparator(boolean asc) {
		_asc = asc;
	}

	@Override
	public int compare(Object obj1, Object obj2) {
		OfferingEntry offeringEntry1 = (OfferingEntry)obj1;
		OfferingEntry offeringEntry2 = (OfferingEntry)obj2;

		int value = 0;

		if (offeringEntry1.getOfferingEntryId() <
				offeringEntry2.getOfferingEntryId()) {

			value = -1;
		}
		else {
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