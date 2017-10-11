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

package com.liferay.osb.customer.release.notes.remote.util.comparator;

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Comparator;

/**
 * @author Samuel Kong
 */
public class ClassNameComparator implements Comparator<String> {

	@Override
	public int compare(String className1, String className2) {
		if (Validator.isNull(className1)) {
			return -1;
		}

		if (Validator.isNull(className2)) {
			return 1;
		}

		String[] packageParts1 = _getPackageParts(className1);
		String[] packageParts2 = _getPackageParts(className2);

		int packagePartsLength = Math.min(
			packageParts1.length, packageParts2.length);

		for (int i = 0; i < packagePartsLength; i++) {
			if (packageParts1[i].equals(packageParts2[i])) {
				continue;
			}

			return packageParts1[i].compareTo(packageParts2[i]);
		}

		if (packageParts1.length > packageParts2.length) {
			return 1;
		}
		else if (packageParts1.length < packageParts2.length) {
			return -1;
		}
		else {
			String name1 = _getName(className1);
			String name2 = _getName(className2);

			return name1.compareTo(name2);
		}
	}

	private String _getName(String className) {
		int x = className.lastIndexOf(StringPool.PERIOD);

		if (x == -1) {
			return className;
		}

		return className.substring(x + 1);
	}

	private String[] _getPackageParts(String className) {
		int x = className.lastIndexOf(StringPool.PERIOD);

		if (x == -1) {
			return new String[0];
		}

		String classPackage = className.substring(0, x);

		return StringUtil.split(classPackage, StringPool.PERIOD);
	}

}