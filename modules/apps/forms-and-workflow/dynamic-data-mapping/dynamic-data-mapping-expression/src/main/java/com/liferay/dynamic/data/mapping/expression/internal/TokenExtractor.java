/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.expression.internal;

import com.liferay.dynamic.data.mapping.expression.DDMExpressionException;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import com.udojava.evalex.Expression;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Marcellus Tavares
 * @author Leonardo Barros
 */
public class TokenExtractor {

	public TokenExtractor(String expressionString)
		throws DDMExpressionException {

		if (Validator.isNull(expressionString)) {
			throw new IllegalArgumentException("Expression is null");
		}

		_expression = expressionString;

		extract();
	}

	public String getExpression() {
		return _expression;
	}

	public Map<String, String> getRandomVariableMap() {
		return _randomVariableMap;
	}

	public Map<String, String> getVariableMap() {
		return _variableMap;
	}

	protected String createRandomVariableName() {
		return StringPool.UNDERLINE + StringUtil.randomId();
	}

	protected void createStringVariable(String token) {
		String randomVariableName = null;

		do {
			randomVariableName = createRandomVariableName();
		}
		while (_variableMap.containsKey(randomVariableName));

		_variableMap.put(randomVariableName, token);

		_expression = StringUtil.replace(
			_expression, "\"" + token + "\"", randomVariableName);

		_randomVariableMap.put(token, randomVariableName);
	}

	protected void createVariable(String token) {
		String randomVariableName = null;

		do {
			randomVariableName = createRandomVariableName();
		}
		while (_variableMap.containsKey(randomVariableName));

		_variableMap.put(randomVariableName, token);

		_expression = _expression.replaceAll(
			"\\b" + token + "\\b", randomVariableName);

		_randomVariableMap.put(token, randomVariableName);
	}

	protected void extract() throws DDMExpressionException {
		try {
			Matcher matcher = _stringPattern.matcher(_expression);

			while (matcher.find()) {
				createStringVariable(matcher.group(1));
			}

			Iterator<String> tokenIterator = getExpressionTokens();

			while (tokenIterator.hasNext()) {
				String token = tokenIterator.next();

				if (isFunction(token) && !isFunctionAllowed(token)) {
					throw new DDMExpressionException.FunctionNotAllowed(token);
				}

				if (!isOperator(token) && !isFunctionAllowed(token) &&
					!isBooleanConstant(token)) {

					Matcher variableMatcher = _variablePattern.matcher(token);

					if (variableMatcher.matches()) {
						if (!_variableMap.containsKey(token)) {
							_variableMap.put(token, token);
						}
					}
					else {
						createVariable(token);
					}
				}
			}
		}
		catch (Expression.ExpressionException eee) {
			throw new DDMExpressionException(eee);
		}
	}

	protected Iterator<String> getExpressionTokens() {
		Expression expression = new Expression(_expression);

		return expression.getExpressionTokenizer();
	}

	protected boolean isBooleanConstant(String token) {
		return _booleanConstants.contains(StringUtil.toLowerCase(token));
	}

	protected boolean isFunction(String token) {
		return _availableFunctions.contains(StringUtil.toLowerCase(token));
	}

	protected boolean isFunctionAllowed(String token) {
		return _allowedFunctions.contains(StringUtil.toLowerCase(token));
	}

	protected boolean isOperator(String token) {
		Matcher tokenMatcher = _operatorsPattern.matcher(token);

		return tokenMatcher.matches();
	}

	private static final Set<String> _allowedFunctions = SetUtil.fromArray(
		new String[] {
			"between", "concat", "contains", "equals", "if", "isemailaddress",
			"isurl", "max", "min", "not", "sum", "isemailaddresses"
		});
	private static final Set<String> _availableFunctions = SetUtil.fromArray(
		new String[] {
			"abs", "acos", "asin", "atan", "between", "ceiling", "concat",
			"contains", "cos", "cosh", "deg", "equals", "floor", "if",
			"isemailaddress", "isurl", "log", "log10", "max", "min", "not",
			"rad", "random", "round", "sin", "sinh", "sqrt", "sum", "tan",
			"tanh", "isemailaddresses"
		});
	private static final Set<String> _booleanConstants = SetUtil.fromArray(
		new String[] {"false", "true"});
	private static final Pattern _operatorsPattern = Pattern.compile(
		"[+-/\\*%\\^\\(\\)]|[<>=!]=?|&&|\\|\\|");
	private static final Pattern _stringPattern = Pattern.compile(
		"\"([^\"]*)\"");
	private static final Pattern _variablePattern = Pattern.compile(
		"\\b(_?)([^0-9_\\s]+[^\\s]*)(?!\\()\\b");

	private String _expression;
	private final Map<String, String> _randomVariableMap = new HashMap<>();
	private final Map<String, String> _variableMap = new HashMap<>();

}