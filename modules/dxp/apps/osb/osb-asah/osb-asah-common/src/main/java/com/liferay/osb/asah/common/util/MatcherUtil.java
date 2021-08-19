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

package com.liferay.osb.asah.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringEscapeUtils;

/**
 * @author Rachael Koestartyo
 */
public class MatcherUtil {

	public static String getGroupByPattern() {
		return StringEscapeUtils.unescapeJava(_groupByPattern.toString());
	}

	public static Matcher getMatcher(String apply) {
		return _groupByPattern.matcher(apply);
	}

	private static final Pattern _groupByPattern = Pattern.compile(
		"groupby\\(\\((?<groupByField>[^)]+)\\)\\)" +
			"(/contains\\(\\((?<containsField>[^)]+)\\)\\))?");

}