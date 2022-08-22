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

package com.liferay.osb.asah.common.elasticsearch.converter;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author Michael Bowerman
 */
public class FilterStringToQueryBuilderConverter {

	public static String buildIgnoreCaseRegExp(boolean contains, String value) {
		IntStream intStream = value.codePoints();

		Stream<String> stream = intStream.mapToObj(
			c -> (char)c
		).map(
			c -> {
				if (_CHARACTERS_TO_BE_ESCAPED_IN_REGEX.indexOf(c) >= 0) {
					return "\\" + c;
				}

				if (Character.isLowerCase(c)) {
					return "(" + c + "|" + Character.toUpperCase(c) + ")";
				}

				if (Character.isUpperCase(c)) {
					return "(" + Character.toLowerCase(c) + "|" + c + ")";
				}

				return String.valueOf(c);
			}
		);

		if (contains) {
			return stream.collect(Collectors.joining("", ".*?", ".*?"));
		}

		return stream.collect(Collectors.joining("", "", ""));
	}

	public static String buildIgnoreCaseRegExp(String value) {
		return buildIgnoreCaseRegExp(true, value);
	}

	private static final String _CHARACTERS_TO_BE_ESCAPED_IN_REGEX =
		"([{\\^-=$!|]})?*+.<>\"#";

}