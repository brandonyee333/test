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

package com.liferay.osb.asah.backend.rest.controller.util;

import com.liferay.osb.asah.common.util.ListUtil;

import java.time.Instant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author Inácio Nery
 */
public class FilterUtil {

	public static Boolean getBoolean(String filter, String key) {
		List<String> values = getStringValues(filter, key, false);

		if ((values == null) || values.isEmpty()) {
			return false;
		}

		return true;
	}

	public static Date getEndDate(String filter, String key) {
		List<String> values = _getValues(
			filter, Arrays.asList(" " + key + " lt ", "(" + key + " lt "));

		if ((values == null) || values.isEmpty()) {
			return null;
		}

		return Date.from(Instant.parse(values.get(0)));
	}

	public static Long getLong(String filter, String key) {
		List<String> values = getStringValues(filter, key, false);

		if ((values == null) || values.isEmpty()) {
			return null;
		}

		return Long.valueOf(values.get(0));
	}

	public static List<Long> getLongValues(
		String filter, String key, boolean search) {

		List<String> values;

		if (search) {
			values = _getValues(filter, Arrays.asList("contains(" + key + ","));
		}
		else {
			values = _getValues(
				filter, Arrays.asList(" " + key + " eq ", "(" + key + " eq "));
		}

		return ListUtil.map(values, Long::valueOf);
	}

	public static Date getStartDate(String filter, String key) {
		List<String> values = _getValues(
			filter, Arrays.asList(" " + key + " ge ", "(" + key + " ge "));

		if ((values == null) || values.isEmpty()) {
			return null;
		}

		return Date.from(Instant.parse(values.get(0)));
	}

	public static String getString(String filter, String key) {
		List<String> values = getStringValues(filter, key, false);

		if ((values == null) || values.isEmpty()) {
			return null;
		}

		return values.get(0);
	}

	public static List<String> getStringValues(
		String filter, String key, boolean search) {

		if (search) {
			return _getValues(filter, Arrays.asList("contains(" + key + ","));
		}

		return _getValues(
			filter, Arrays.asList(" " + key + " eq ", "(" + key + " eq "));
	}

	private static List<String> _getValues(String filter, List<String> keys) {
		if (filter == null) {
			return null;
		}

		List<String> values = new ArrayList<>();

		for (String key : keys) {
			while (filter.contains(key)) {
				int keyIndex = filter.indexOf(key);

				int valueIndex = keyIndex + key.length();

				String value = filter.substring(valueIndex);

				if (value.indexOf("'") == 0) {
					value = value.substring(value.indexOf("'") + 1);

					value = value.substring(0, value.indexOf("'"));

					values.add(value);

					valueIndex += value.length() + 2;
				}
				else if (value.indexOf("null") == 0) {
					values.add("null");

					valueIndex += 5;
				}
				else if (value.indexOf("[") == 0) {
					value = value.substring(value.indexOf("[") + 1);

					value = value.substring(0, value.indexOf("]"));

					Collections.addAll(values, value.split(","));

					valueIndex += value.length() + 2;
				}

				filter = filter.replace(
					filter.substring(keyIndex, valueIndex), "");
			}
		}

		if (values.isEmpty()) {
			return null;
		}

		return values;
	}

}