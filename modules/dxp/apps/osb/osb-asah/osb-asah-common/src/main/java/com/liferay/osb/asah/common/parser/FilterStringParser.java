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

package com.liferay.osb.asah.common.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Michael Bowerman
 */
public class FilterStringParser {

	public static <T> T parse(
			String filterString, Supplier<T> getParseResultSupplier,
			Function<String, Exception> parseInnerFilterFunction,
			Function<String, Exception> processLogicalOperatorFunction,
			Function<String[], Exception> processLogicFunctionFunction,
			Function<Object[], Exception> processStringFunctionFunction)
		throws Exception {

		if (StringUtils.isEmpty(filterString)) {
			return null;
		}

		try {
			for (int index = 0; index < filterString.length(); index++) {
				char c = filterString.charAt(index);

				if (c == '(') {
					int closeParenthesisIndex =
						_getMatchingCloseParenthesisIndex(filterString, index);

					Exception e = parseInnerFilterFunction.apply(
						filterString.substring(
							index + 1, closeParenthesisIndex));

					if (e != null) {
						throw e;
					}

					index = _getNextLogicalOperatorEndIndex(
						filterString, processLogicalOperatorFunction,
						closeParenthesisIndex);
				}
				else if (!Character.isWhitespace(c)) {
					index = _getFunctionEndIndex(
						filterString, processLogicFunctionFunction,
						processStringFunctionFunction, index);

					index = _getNextLogicalOperatorEndIndex(
						filterString, processLogicalOperatorFunction, index);
				}
			}

			return getParseResultSupplier.get();
		}
		catch (Exception e) {
			throw new Exception("Unable to process filter " + filterString, e);
		}
	}

	private static int _getEndQuoteIndex(String s, int start) {
		for (int i = start + 1; i < s.length(); i++) {
			if (s.charAt(i) == '\'') {
				if (((i + 1) >= s.length()) || (s.charAt(i + 1) != '\'')) {
					return i;
				}

				i++;
			}
		}

		throw new IllegalArgumentException(
			"Unclosed string literal: " + s.substring(start));
	}

	private static int _getFunctionEndIndex(
			String filterString,
			Function<String[], Exception> processLogicFunctionFunction,
			Function<Object[], Exception> processStringFunctionFunction,
			int start)
		throws Exception {

		int firstTermBeginIndex = _getNextNonwhitespaceIndex(
			start, filterString, start);

		int firstTermEndIndex = _getNextWhitespaceOrOpenParenthesisIndex(
			start, filterString, firstTermBeginIndex);

		String firstTerm = filterString.substring(
			firstTermBeginIndex, firstTermEndIndex);

		if (filterString.charAt(firstTermEndIndex) == '(') {
			return _getStringFunctionEndIndex(
				filterString, firstTerm, false, firstTermEndIndex,
				processStringFunctionFunction);
		}

		int secondTermBeginIndex = _getNextNonwhitespaceIndex(
			start, filterString, firstTermEndIndex + 1);

		int secondTermEndIndex = _getNextWhitespaceOrOpenParenthesisIndex(
			start, filterString, secondTermBeginIndex);

		String secondTerm = filterString.substring(
			secondTermBeginIndex, secondTermEndIndex);

		if (firstTerm.equalsIgnoreCase("not") &&
			(filterString.charAt(secondTermEndIndex) == '(')) {

			return _getStringFunctionEndIndex(
				filterString, secondTerm, true, secondTermEndIndex,
				processStringFunctionFunction);
		}

		int thirdTermBeginIndex = _getNextNonwhitespaceIndex(
			start, filterString, secondTermEndIndex + 1);

		int thirdTermEndIndex = _getNextWhitespaceOrOpenParenthesisIndex(
			filterString, thirdTermBeginIndex);

		if (thirdTermEndIndex < 0) {
			thirdTermEndIndex = filterString.length();
		}

		String thirdTerm = filterString.substring(
			thirdTermBeginIndex, thirdTermEndIndex);

		Exception e = processLogicFunctionFunction.apply(
			new String[] {firstTerm, secondTerm, thirdTerm});

		if (e != null) {
			throw e;
		}

		return thirdTermEndIndex;
	}

	private static int _getMatchingCloseParenthesisIndex(String s, int start) {
		int openParentheses = 1;

		for (int i = start + 1; i < s.length(); i++) {
			char c = s.charAt(i);

			if (c == ')') {
				openParentheses--;

				if (openParentheses <= 0) {
					return i;
				}
			}
			else if (c == '(') {
				openParentheses++;
			}
			else if (c == '\'') {
				i = _getEndQuoteIndex(s, i);
			}
		}

		throw new IllegalArgumentException(
			"Unclosed parenthetical statement: " + s.substring(start));
	}

	private static int _getNextCommaIndex(String s, int start) {
		for (int i = start; i < s.length(); i++) {
			char c = s.charAt(i);

			if (c == ',') {
				return i;
			}
			else if (c == '\'') {
				i = _getEndQuoteIndex(s, i);
			}
		}

		return -1;
	}

	private static int _getNextLogicalOperatorEndIndex(
			String filterString,
			Function<String, Exception> processLogicalOperatorFunction,
			int start)
		throws Exception {

		int logicalOperatorStartIndex = _getNextNonwhitespaceIndex(
			filterString, start + 1);

		if (logicalOperatorStartIndex < 0) {
			return filterString.length();
		}

		int logicalOperatorEndIndex = _getNextWhitespaceIndex(
			filterString, logicalOperatorStartIndex);

		if (logicalOperatorEndIndex < 0) {
			String logicalOperator = filterString.substring(
				logicalOperatorStartIndex);

			throw new IllegalArgumentException(
				"Parsed '" + logicalOperator + "' as a logical operator, but " +
					"no operand was found after it");
		}

		Exception e = processLogicalOperatorFunction.apply(
			filterString.substring(
				logicalOperatorStartIndex, logicalOperatorEndIndex));

		if (e != null) {
			throw e;
		}

		return logicalOperatorEndIndex;
	}

	private static int _getNextNonwhitespaceIndex(
		int expressionStart, String s, int start) {

		int nextNonwhitespaceIndex = _getNextNonwhitespaceIndex(s, start);

		if (nextNonwhitespaceIndex < 0) {
			throw new IllegalArgumentException(
				"Expression terminated unexpectedly: " +
					s.substring(expressionStart));
		}

		return nextNonwhitespaceIndex;
	}

	private static int _getNextNonwhitespaceIndex(String s, int start) {
		for (int i = start; i < s.length(); i++) {
			if (!Character.isWhitespace(s.charAt(i))) {
				return i;
			}
		}

		return -1;
	}

	private static int _getNextWhitespaceIndex(String s, int start) {
		for (int i = start; i < s.length(); i++) {
			if (Character.isWhitespace(s.charAt(i))) {
				return i;
			}
		}

		return -1;
	}

	private static int _getNextWhitespaceOrOpenParenthesisIndex(
		int expressionStart, String s, int start) {

		int nextWhitespaceOrOpenParenthesisIndex =
			_getNextWhitespaceOrOpenParenthesisIndex(s, start);

		if (nextWhitespaceOrOpenParenthesisIndex < 0) {
			throw new IllegalArgumentException(
				"Expression terminated unexpectedly: " +
					s.substring(expressionStart));
		}

		return nextWhitespaceOrOpenParenthesisIndex;
	}

	private static int _getNextWhitespaceOrOpenParenthesisIndex(
		String s, int start) {

		for (int i = start; i < s.length(); i++) {
			char c = s.charAt(i);

			if (Character.isWhitespace(c) || (c == '(')) {
				return i;
			}
			else if (c == '\'') {
				i = _getEndQuoteIndex(s, i);
			}
		}

		return -1;
	}

	private static int _getStringFunctionEndIndex(
			String filterString, String stringFunction, boolean negated,
			int functionOpenParenthesisIndex,
			Function<Object[], Exception> processStringFunctionFunction)
		throws Exception {

		int functionCloseParenthesisIndex = _getMatchingCloseParenthesisIndex(
			filterString, functionOpenParenthesisIndex);

		List<String> arguments = _splitIntoArguments(
			filterString.substring(
				functionOpenParenthesisIndex + 1,
				functionCloseParenthesisIndex));

		Exception e = processStringFunctionFunction.apply(
			new Object[] {arguments, negated, stringFunction});

		if (e != null) {
			throw e;
		}

		return functionCloseParenthesisIndex;
	}

	private static List<String> _splitIntoArguments(String callBody) {
		List<String> arguments = new ArrayList<>();

		if (StringUtils.isBlank(callBody)) {
			return arguments;
		}

		int argumentBeginIndex = 0;
		int argumentEndIndex = 0;

		while (true) {
			argumentEndIndex = _getNextCommaIndex(callBody, argumentBeginIndex);

			if (argumentEndIndex < 0) {
				arguments.add(callBody.substring(argumentBeginIndex));

				return arguments;
			}

			arguments.add(
				callBody.substring(argumentBeginIndex, argumentEndIndex));

			argumentBeginIndex = _getNextNonwhitespaceIndex(
				0, callBody, argumentEndIndex + 1);
		}
	}

}