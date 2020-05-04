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

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Matthew Kong
 */
public class FilterUtil {

	public static String[] getArgumentValues(
		List<String> arguments, String[] argumentNames) {

		String[] argumentValues = new String[argumentNames.length];

		argumentLoop:
		for (String argument : arguments) {
			String[] argumentData = _getArgumentData(argument);

			String argumentName = argumentData[0];
			String argumentValue = argumentData[1];

			for (int i = 0; i < argumentNames.length; i++) {
				if (argumentName.equals(argumentNames[i])) {
					if (argumentValues[i] != null) {
						throw new IllegalArgumentException(
							"Multiple values passed in for " + argumentName);
					}

					argumentValues[i] = argumentValue;

					continue argumentLoop;
				}
			}

			if (_log.isWarnEnabled()) {
				_log.warn("Unknown argument: " + argumentName);
			}
		}

		return argumentValues;
	}

	private static String[] _getArgumentData(String argument) {
		int index = argument.indexOf('=');

		if (index < 0) {
			throw new IllegalArgumentException("Invalid argument: " + argument);
		}

		int argumentNameBeginIndex = _getFirstNonwhitespaceIndex(
			argument, 0, index - 1);
		int argumentNameEndIndex = _getLastNonwhitespaceIndex(
			argument, index - 1, 0);
		int argumentValueBeginIndex = _getFirstNonwhitespaceIndex(
			argument, index + 1, argument.length() - 1);
		int argumentValueEndIndex = _getLastNonwhitespaceIndex(
			argument, argument.length() - 1, index + 1);

		return new String[] {
			argument.substring(
				argumentNameBeginIndex, argumentNameEndIndex + 1),
			argument.substring(
				argumentValueBeginIndex, argumentValueEndIndex + 1)
		};
	}

	private static int _getFirstNonwhitespaceIndex(
		String s, int startPos, int endPos) {

		for (int i = startPos; i <= endPos; i++) {
			if (!Character.isWhitespace(s.charAt(i))) {
				return i;
			}
		}

		throw new IllegalArgumentException(
			"Nothing was found between positions" + startPos + " and " +
				endPos + " in " + s);
	}

	private static int _getLastNonwhitespaceIndex(
		String s, int startPos, int endPos) {

		for (int i = startPos; i >= endPos; i--) {
			if (!Character.isWhitespace(s.charAt(i))) {
				return i;
			}
		}

		throw new IllegalArgumentException(
			"Nothing was found between positions" + endPos + " and " +
				startPos + " in " + s);
	}

	private static final Log _log = LogFactory.getLog(FilterUtil.class);

}