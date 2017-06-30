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

import com.liferay.osb.model.DeveloperEntry;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Comparator;

/**
 * @author Ryan Park
 */
public class DeveloperEntryNameComparator implements Comparator {

	public DeveloperEntryNameComparator() {
		this(true);
	}

	public DeveloperEntryNameComparator(boolean asc) {
		_asc = asc;
	}

	@Override
	public int compare(Object obj1, Object obj2) {
		DeveloperEntry developerEntry1 = (DeveloperEntry)obj1;
		DeveloperEntry developerEntry2 = (DeveloperEntry)obj2;

		String developerEntryName1 = StringPool.BLANK;
		String developerEntryName2 = StringPool.BLANK;

		try {
			developerEntryName1 = developerEntry1.getName();
			developerEntryName1 = developerEntryName1.toLowerCase();

			developerEntryName2 = developerEntry2.getName();
			developerEntryName2 = developerEntryName2.toLowerCase();
		}
		catch (Exception e) {
		}

		int value = developerEntryName1.compareTo(developerEntryName2);

		if (_asc) {
			return value;
		}
		else {
			return -value;
		}
	}

	private boolean _asc;

}