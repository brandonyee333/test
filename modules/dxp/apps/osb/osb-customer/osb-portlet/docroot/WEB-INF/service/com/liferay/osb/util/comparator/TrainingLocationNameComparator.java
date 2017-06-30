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

import com.liferay.osb.model.TrainingLocation;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * @author Ryan Park
 */
public class TrainingLocationNameComparator extends OrderByComparator {

	public static final String ORDER_BY_ASC = "OSB_TrainingLocation.name ASC";

	public static final String ORDER_BY_DESC = "OSB_TrainingLocation.name DESC";

	public static final String[] ORDER_BY_FIELDS = {"name"};

	public TrainingLocationNameComparator() {
		this(false);
	}

	public TrainingLocationNameComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(Object obj1, Object obj2) {
		TrainingLocation trainingLocation1 = (TrainingLocation)obj1;
		TrainingLocation trainingLocation2 = (TrainingLocation)obj2;

		int value = trainingLocation1.getName().toLowerCase().compareTo(
			trainingLocation2.getName().toLowerCase());

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