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

package com.liferay.osb.asah.common.array;

import java.lang.reflect.Array;

/**
 * @author Brian Wing Shun Chan
 */
public class ArrayUtil {

	public static char[] append(char[]... arrays) {
		int length = 0;

		for (char[] array : arrays) {
			length += array.length;
		}

		char[] newArray = new char[length];

		int previousLength = 0;

		for (char[] array : arrays) {
			System.arraycopy(array, 0, newArray, previousLength, array.length);

			previousLength += array.length;
		}

		return newArray;
	}

	public static boolean isEmpty(Object[] array) {
		if ((array == null) || (array.length == 0)) {
			return true;
		}

		return false;
	}

	public static boolean isNotEmpty(Object[] array) {
		return !isEmpty(array);
	}

	public static char[] subset(char[] array, int start, int end) {
		if ((start < 0) || (end < 0) || ((end - start) < 0)) {
			return array;
		}

		char[] newArray = new char[end - start];

		System.arraycopy(array, start, newArray, 0, end - start);

		return newArray;
	}

	public static <T> T[] subset(T[] array, int start, int end) {
		if ((start < 0) || (end < 0) || ((end - start) < 0)) {
			return array;
		}

		Class<?> arrayClass = array.getClass();

		T[] newArray = (T[])Array.newInstance(
			arrayClass.getComponentType(), end - start);

		System.arraycopy(array, start, newArray, 0, end - start);

		return newArray;
	}

}