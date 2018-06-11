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

package com.liferay.osb.customer.release.notes.web.internal.util.comparator;

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