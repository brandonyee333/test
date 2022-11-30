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

package com.liferay.osb.asah.common.filterexpression;

import com.liferay.osb.asah.common.filterexpression.parser.FilterExpressionLexer;
import com.liferay.osb.asah.common.filterexpression.parser.FilterExpressionParser;
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
 */
public class FilterExpression {

	public FilterExpression(String filterExpressionString) {
		Assert.notNull(filterExpressionString, "The filter expression is null");


		try {
			MyErrorListener myErrorListener = new MyErrorListener();

			FilterExpressionLexer filterExpressionLexer = new FilterExpressionLexer(
				new ANTLRInputStream(filterExpressionString));

			filterExpressionLexer.addErrorListener(myErrorListener);

			FilterExpressionParser filterExpressionParser =
				new FilterExpressionParser(
					new CommonTokenStream(filterExpressionLexer));

			//filterExpressionParser.addErrorListener(myErrorListener);
			filterExpressionParser.setErrorHandler(new BailErrorStrategy());

			_expressionContext = filterExpressionParser.expression();
		}
		catch (ParseCancellationException parseCancellationException) {
			// TODO Wrap into a proper parser exception

			throw new FilterExpressionParserException(
				"Unable to parse " + filterExpressionString, parseCancellationException);
		}

	}

	public Condition getCondition() {
		FilterExpressionVisitor filterExpressionVisitor =
			new FilterExpressionVisitor();

		return (Condition)_expressionContext.accept(filterExpressionVisitor);
	}

	public class MyErrorListener extends BaseErrorListener {
		@Override
		public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
			throw new ParseCancellationException();
		}
	}

	private FilterExpressionParser.ExpressionContext _expressionContext;
}
