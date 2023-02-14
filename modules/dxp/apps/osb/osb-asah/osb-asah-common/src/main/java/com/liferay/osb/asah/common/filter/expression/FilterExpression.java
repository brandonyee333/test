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

import com.liferay.osb.asah.common.filter.expression.parser.FilterExpressionLexer;
import com.liferay.osb.asah.common.filter.expression.parser.FilterExpressionParser;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
 * @author Marcellus Tavares
 * @author Ivica Cardic
 */
public class FilterExpression {

	public FilterExpression(String filterExpressionString) {
		this(filterExpressionString, null);
	}

	public FilterExpression(String filterExpressionString, String filterType) {
		Assert.notNull(
			filterExpressionString, "Filter expression string is null");

		_filterType = filterType;

		try {
			ErrorListener errorListener = new ErrorListener();

			FilterExpressionLexer filterExpressionLexer =
				new FilterExpressionLexer(
					new ANTLRInputStream(
						_rewriteInterestFilterExpression(
							filterExpressionString)));

			filterExpressionLexer.addErrorListener(errorListener);

			FilterExpressionParser filterExpressionParser =
				new FilterExpressionParser(
					new CommonTokenStream(filterExpressionLexer));

			filterExpressionParser.setErrorHandler(new BailErrorStrategy());

			FilterExpressionParser.ExpressionContext expressionContext =
				filterExpressionParser.expression();

			FilterExpressionVisitor filterExpressionVisitor =
				new FilterExpressionVisitor(_filterType);

			_condition = (Condition)expressionContext.accept(
				filterExpressionVisitor);
			_referencedTableNames =
				filterExpressionVisitor.getReferencedTableNames();
		}
		catch (ParseCancellationException parseCancellationException) {
			throw new FilterExpressionParserException(
				"Unable to parse " + filterExpressionString,
				parseCancellationException);
		}
	}

	public Condition getCondition() {
		return _condition;
	}

	public Set<String> getReferencedTableNames() {
		return _referencedTableNames;
	}

	private String _rewriteInterestFilterExpression(
		String filterExpressionString) {

		Matcher matcher = _interestPattern.matcher(filterExpressionString);

		while (matcher.find()) {
			String expression =
				"interests.filter(filter='(isInterested(keyword, \"" +
					matcher.group("keyword") + "\"))')";

			if (!Boolean.parseBoolean(matcher.group("interested"))) {
				expression = "not(" + expression + ")";
			}

			filterExpressionString = matcher.replaceFirst(expression);

			matcher = _interestPattern.matcher(filterExpressionString);
		}

		return filterExpressionString;
	}

	private static final Pattern _interestPattern = Pattern.compile(
		"interests.filter\\(filter='\\(name eq ''(?<keyword>[^']+)'' and " +
			"score eq ''(?<interested>true|false)''\\)'\\)");

	private Condition _condition;
	private final String _filterType;
	private final Set<String> _referencedTableNames;

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