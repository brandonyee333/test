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

package com.liferay.osb.asah.common.elasticsearch;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Geyson Silva
 * @author André Miranda
 */
public class QueryUtil {

	public static String escapeKeywords(String keywords) {
		IntStream intStream = keywords.codePoints();

		return intStream.mapToObj(
			c -> (char)c
		).map(
			c -> {
				if (_CHARACTERS_TO_BE_ESCAPED_IN_QUERY_STRING.indexOf(c) >= 0) {
					return "\\" + c;
				}

				return String.valueOf(c);
			}
		).collect(
			Collectors.joining()
		);
	}

	private static final String _CHARACTERS_TO_BE_ESCAPED_IN_QUERY_STRING =
		"_+-=&&||><!(){}[]^\"~*?:\\/";

}