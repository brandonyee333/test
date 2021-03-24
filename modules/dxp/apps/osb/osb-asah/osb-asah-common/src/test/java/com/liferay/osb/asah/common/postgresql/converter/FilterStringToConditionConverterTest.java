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

package com.liferay.osb.asah.common.postgresql.converter;

import org.jooq.Condition;
import org.jooq.impl.DSL;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Rachael Koestartyo
 */
public class FilterStringToConditionConverterTest {

	@Test
	public void testAndOperator() throws Exception {
		_assertEquals(
			DSL.and(
				DSL.field(
					"column1"
				).eq(
					"value1"
				)
			).and(
				DSL.field(
					"column2"
				).ne(
					"value2"
				)
			),
			"column1 eq 'value1' and column2 ne 'value2'");
	}

	@Test
	public void testBooleanFalseValue() throws Exception {
		_assertEquals(
			DSL.field(
				"column1"
			).eq(
				false
			),
			"column1 eq false");
	}

	@Test
	public void testBooleanTrueValue() throws Exception {
		_assertEquals(
			DSL.field(
				"column1"
			).eq(
				true
			),
			"column1 eq true");
	}

	@Test
	public void testContainsOperator() throws Exception {
		_assertEquals(
			DSL.field(
				"column1"
			).containsIgnoreCase(
				"value1"
			),
			"contains(column1, 'value1')");
	}

	@Test
	public void testDanglingLogicalOperatorThrowsException() {
		_assertThrowsException(
			"column1 eq 'value1' and",
			"Parsed 'and' as a logical operator, but no operand was found " +
				"after it");
	}

	@Test
	public void testDoubleValue() throws Exception {
		_assertEquals(
			DSL.field(
				"column1"
			).eq(
				12.34
			),
			"column1 eq 12.34");
	}

	@Test
	public void testEndsWithOperator() throws Exception {
		_assertEquals(
			DSL.field(
				"column1"
			).similarTo(
				"%value1"
			),
			"endsWith(column1, 'value1')");
	}

	@Test
	public void testEqOperator() throws Exception {
		_assertEquals(
			DSL.field(
				"column1"
			).eq(
				"value1"
			),
			"column1 eq 'value1'");
	}

	@Test
	public void testEscapeOperator() throws Exception {
		_assertEquals(
			DSL.field(
				"column1"
			).eq(
				"value'1"
			),
			"column1 eq 'value''1'");
	}

	@Test
	public void testFreestyle1() throws Exception {
		_assertEquals(
			DSL.and(
				DSL.or(
					DSL.field(
						"column1"
					).gt(
						42
					)
				).or(
					DSL.or(
						DSL.field(
							"column2"
						).containsIgnoreCase(
							"escaped'quote)"
						)
					).or(
						DSL.and(
							DSL.field(
								"column3"
							).ne(
								true
							)
						).and(
							DSL.field(
								"column4"
							).le(
								97531.8642
							)
						)
					)
				)
			).and(
				DSL.field(
					"column5"
				).isNotNull()
			),
			"((column1 gt 42 or ((contains(column2, 'escaped''quote)')) or " +
				"(column3 ne true and column4 le 97531.8642)) and column5 ne " +
					"null))");
	}

	@Test
	public void testFreestyle2() throws Exception {
		_assertEquals(
			DSL.or(
				DSL.and(
					DSL.field(
						"column1"
					).ne(
						"null"
					)
				).and(
					DSL.field(
						"column2"
					).isNotNull()
				)
			).or(
				DSL.or(
					DSL.and(
						DSL.field(
							"column3"
						).eq(
							"true"
						)
					).and(
						DSL.field(
							"column4"
						).eq(
							true
						)
					)
				).or(
					DSL.and(
						DSL.field(
							"column5"
						).gt(
							-53.21
						)
					).and(
						DSL.field(
							"column6"
						).le(
							-8192
						)
					)
				)
			),
			"((column1 ne 'null' and column2 ne null) or ((column3 eq 'true' " +
				"and column4 eq true) or (column5 gt -53.21 and column6 le " +
					"-8192)))");
	}

	@Test
	public void testFreestyle3() throws Exception {
		_assertEquals(
			DSL.and(
				DSL.or(
					DSL.field(
						"column1"
					).eq(
						"value1"
					)
				).or(
					DSL.field(
						"column2"
					).eq(
						"value2"
					)
				)
			).and(
				DSL.field(
					"column3"
				).eq(
					"value3"
				)
			),
			"((column1 eq 'value1' or column2 eq 'value2') and column3 eq " +
				"'value3')");
	}

	@Test
	public void testGeOperator() throws Exception {
		_assertEquals(
			DSL.field(
				"column1"
			).ge(
				"value1"
			),
			"column1 ge 'value1'");
	}

	@Test
	public void testGtOperator() throws Exception {
		_assertEquals(
			DSL.field(
				"column1"
			).gt(
				"value1"
			),
			"column1 gt 'value1'");
	}

	@Test
	public void testIncompleteExpressionThrowsException() {
		_assertThrowsException(
			"column1 eq ", "Expression terminated unexpectedly: column1 eq ");
	}

	@Test
	public void testIntegerValue() throws Exception {
		_assertEquals(
			DSL.field(
				"column1"
			).eq(
				123
			),
			"column1 eq 123");
	}

	@Test
	public void testInvalidLogicalOperatorThrowsException() {
		_assertThrowsException(
			"column1 eq 'value1' but column2 eq 'value2'",
			"Expected logical operator 'and' or 'or', but got 'but' instead");
	}

	@Test
	public void testInvalidObjectThrowsException() {
		_assertThrowsException(
			"column1 eq value1", "Unknown object value1 used in filter");
	}

	@Test
	public void testInvalidOperatorThrowsException() {
		_assertThrowsException("column1 is 'value1'", "Invalid operator: is");
	}

	@Test
	public void testInvalidStringFunctionArgumentThrowsException() {
		_assertThrowsException(
			"matches(column1, 'value1')", "Invalid string function: matches");
	}

	@Test
	public void testLeOperator() throws Exception {
		_assertEquals(
			DSL.field(
				"column1"
			).le(
				"value1"
			),
			"column1 le 'value1'");
	}

	@Test
	public void testLtOperator() throws Exception {
		_assertEquals(
			DSL.field(
				"column1"
			).lt(
				"value1"
			),
			"column1 lt 'value1'");
	}

	@Test
	public void testNeNull() throws Exception {
		_assertEquals(
			DSL.field(
				"column1"
			).isNotNull(),
			"column1 ne null");
	}

	@Test
	public void testNeOperator() throws Exception {
		_assertEquals(
			DSL.field(
				"column1"
			).ne(
				"value1"
			),
			"column1 ne 'value1'");
	}

	@Test
	public void testNotContainsOperator() throws Exception {
		_assertEquals(
			DSL.not(
				DSL.field(
					"column1"
				).containsIgnoreCase(
					"value1"
				)),
			"not contains(column1, 'value1')");
	}

	@Test
	public void testNotEndsWithOperator() throws Exception {
		_assertEquals(
			DSL.not(
				DSL.field(
					"column1"
				).similarTo(
					"%value1"
				)),
			"not endsWith(column1, 'value1')");
	}

	@Test
	public void testNotStartsWithOperator() throws Exception {
		_assertEquals(
			DSL.not(
				DSL.field(
					"column1"
				).similarTo(
					"value1%"
				)),
			"not startsWith(column1, 'value1')");
	}

	@Test
	public void testNullValue() throws Exception {
		_assertEquals(
			DSL.field(
				"column1"
			).isNull(),
			"column1 eq null");
	}

	@Test
	public void testOrderOfOperations1() throws Exception {
		_assertEquals(
			DSL.or(
				DSL.field(
					"column1"
				).eq(
					"value1"
				)
			).or(
				DSL.and(
					DSL.field(
						"column2"
					).le(
						"value2"
					)
				).and(
					DSL.field(
						"column3"
					).ge(
						"value3"
					)
				)
			),
			"column1 eq 'value1' or (column2 le 'value2' and column3 ge " +
				"'value3')");
	}

	@Test
	public void testOrderOfOperations2() throws Exception {
		_assertEquals(
			DSL.and(
				DSL.or(
					DSL.field(
						"column1"
					).lt(
						"value1"
					)
				).or(
					DSL.field(
						"column2"
					).gt(
						"value2"
					)
				)
			).and(
				DSL.field(
					"column3"
				).ne(
					"value3"
				)
			),
			"(column1 lt 'value1' or column2 gt 'value2') and column3 ne " +
				"'value3'");
	}

	@Test
	public void testOrOperator() throws Exception {
		_assertEquals(
			DSL.or(
				DSL.field(
					"column1"
				).gt(
					"value1"
				)
			).or(
				DSL.field(
					"column2"
				).lt(
					"value2"
				)
			),
			"column1 gt 'value1' or column2 lt 'value2'");
	}

	@Test
	public void testParentheses() throws Exception {
		_assertEquals(
			DSL.field(
				"column1"
			).eq(
				"value)1"
			),
			"((column1 eq 'value)1'))");
	}

	@Test
	public void testStartsWithOperator() throws Exception {
		_assertEquals(
			DSL.field(
				"column1"
			).similarTo(
				"value1%"
			),
			"startsWith(column1, 'value1')");
	}

	@Test
	public void testTooManyStringFunctionArgumentsThrowsException() {
		_assertThrowsException(
			"contains(column1, 'value1', 'value2')",
			"Expected 2 arguments for contains function, got 3 instead: " +
				"[column1, 'value1', 'value2']");
	}

	@Test
	public void testUnclosedParenthesisThrowsException() {
		_assertThrowsException(
			"(column1 eq ')'",
			"Unclosed parenthetical statement: (column1 eq ')'");
	}

	@Test
	public void testUnclosedStringLiteralThrowsException() {
		_assertThrowsException(
			"column1 eq 'escaped quote: ''",
			"Unclosed string literal: 'escaped quote: ''");
	}

	private void _assertEquals(
			Condition expectedCondition, String actualFilterString)
		throws Exception {

		Condition actualCondition = FilterStringToConditionConverter.convert(
			actualFilterString);

		Assert.assertEquals(
			expectedCondition.toString(), actualCondition.toString());
	}

	private void _assertThrowsException(String filterString, String message) {
		try {
			FilterStringToConditionConverter.convert(filterString);

			Assert.fail(
				filterString +
					" did not throw an instance of IllegalArgumentException");
		}
		catch (Exception e) {
			Throwable cause = e.getCause();

			Assert.assertTrue(cause instanceof IllegalArgumentException);

			Assert.assertEquals(cause.getMessage(), message);
		}
	}

}