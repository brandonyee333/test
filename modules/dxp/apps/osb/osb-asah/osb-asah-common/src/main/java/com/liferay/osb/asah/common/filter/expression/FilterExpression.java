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

package com.liferay.osb.asah.common.filter.expression;

import com.liferay.osb.asah.common.converter.helper.FilterStringConverterHelper;
import com.liferay.osb.asah.common.filter.expression.parser.FilterExpressionLexer;
import com.liferay.osb.asah.common.filter.expression.parser.FilterExpressionParser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.misc.ParseCancellationException;

import org.jooq.Condition;

import org.springframework.util.Assert;

/**
 * @author Marcelllus Tavares
 * @author Ivica Cardic
 */
public class FilterExpression {

	public static Condition convert(String filterExpressionString) {
		FilterExpression filterExpression = new FilterExpression(
			filterExpressionString);

		return filterExpression.getCondition();
	}

	public static Condition convert(
		String filterExpressionString,
		FilterStringConverterHelper filterStringConverterHelper) {

		FilterExpression filterExpression = new FilterExpression(
			filterExpressionString,
			new ArrayList<FilterStringConverterHelper>() {
				{
					add(filterStringConverterHelper);
				}
			});

		return filterExpression.getCondition();
	}

	public static JoinCondition joinConvert(
		String filterExpressionString,
		List<FilterStringConverterHelper> filterTypeStringConverterHelpers) {

		FilterExpression filterExpression = new FilterExpression(
			filterExpressionString, filterTypeStringConverterHelpers);

		return filterExpression._getJoinCondition();
	}

	protected FilterExpression(
		String filterExpressionString, String filterType,
		List<FilterStringConverterHelper> filterTypeStringConverterHelpers,
		Set<String> includedTableNames) {

		Assert.notNull(filterExpressionString, "The filter expression is null");

		_filterType = filterType;
		_filterTypeStringConverterHelpers = filterTypeStringConverterHelpers;
		_includedTableNames = includedTableNames;

		try {
			ErrorListener errorListener = new ErrorListener();

			FilterExpressionLexer filterExpressionLexer =
				new FilterExpressionLexer(
					new ANTLRInputStream(filterExpressionString));

			filterExpressionLexer.addErrorListener(errorListener);

			FilterExpressionParser filterExpressionParser =
				new FilterExpressionParser(
					new CommonTokenStream(filterExpressionLexer));

			filterExpressionParser.setErrorHandler(new BailErrorStrategy());

			_expressionContext = filterExpressionParser.expression();
		}
		catch (ParseCancellationException parseCancellationException) {
			throw new FilterExpressionParserException(
				"Unable to parse " + filterExpressionString,
				parseCancellationException);
		}
	}

	protected Condition getCondition() {
		return (Condition)_expressionContext.accept(
			new FilterExpressionVisitor(
				_filterType, _filterTypeStringConverterHelpers,
				_includedTableNames));
	}

	private FilterExpression(String filterExpressionString) {
		this(filterExpressionString, null, Collections.emptyList(), null);
	}

	private FilterExpression(
		String filterExpressionString,
		List<FilterStringConverterHelper> filterTypeStringConverterHelpers) {

		this(
			filterExpressionString, null, filterTypeStringConverterHelpers,
			null);
	}

	private JoinCondition _getJoinCondition() {
		Set<String> includedTableNames = new HashSet<>();

		Condition condition = (Condition)_expressionContext.accept(
			new FilterExpressionVisitor(
				_filterType, _filterTypeStringConverterHelpers,
				includedTableNames));

		return new JoinCondition(condition, includedTableNames);
	}

	private final FilterExpressionParser.ExpressionContext _expressionContext;
	private final String _filterType;
	private final List<FilterStringConverterHelper>
		_filterTypeStringConverterHelpers;
	private final Set<String> _includedTableNames;

	private static class ErrorListener extends BaseErrorListener {

		@Override
		public void syntaxError(
			Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
			int charPositionInLine, String message,
			RecognitionException recognitionException) {

			throw new ParseCancellationException(message, recognitionException);
		}

	}

}