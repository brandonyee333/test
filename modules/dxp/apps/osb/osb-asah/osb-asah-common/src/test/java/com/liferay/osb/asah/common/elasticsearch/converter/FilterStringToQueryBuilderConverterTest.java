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

import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Michael Bowerman
 */
public class FilterStringToQueryBuilderConverterTest {

	@Test
	public void testAndOperator() throws Exception {
		_assertEquals(
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("column1", "value1")
			).filter(
				BoolQueryBuilderUtil.mustNot(
					QueryBuilders.termQuery("column2", "value2"))
			),
			"column1 eq 'value1' and column2 ne 'value2'");
	}

	@Test
	public void testBooleanFalseValue() throws Exception {
		_assertEquals(
			QueryBuilders.termQuery("column1", false), "column1 eq false");
	}

	@Test
	public void testBooleanTrueValue() throws Exception {
		_assertEquals(
			QueryBuilders.termQuery("column1", true), "column1 eq true");
	}

	@Test
	public void testContainsOperator() throws Exception {
		_assertEquals(
			QueryBuilders.regexpQuery(
				"column1",
				FilterStringToQueryBuilderConverter.buildIgnoreCaseRegExp(
					"value1")),
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
			QueryBuilders.termQuery("column1", 12.34), "column1 eq 12.34");
	}

	@Test
	public void testEndsWithOperator() throws Exception {
		_assertEquals(
			QueryBuilders.wildcardQuery("column1", "*value1"),
			"endsWith(column1, 'value1')");
	}

	@Test
	public void testEqOperator() throws Exception {
		_assertEquals(
			QueryBuilders.termQuery("column1", "value1"),
			"column1 eq 'value1'");
	}

	@Test
	public void testEscapeOperator() throws Exception {
		_assertEquals(
			QueryBuilders.termQuery("column1", "value'1"),
			"column1 eq 'value''1'");
	}

	@Test
	public void testFreestyle1() throws Exception {
		_assertEquals(
			BoolQueryBuilderUtil.filter(
				BoolQueryBuilderUtil.should(
					QueryBuilders.rangeQuery(
						"column1"
					).gt(
						42
					)
				).should(
					BoolQueryBuilderUtil.should(
						QueryBuilders.regexpQuery(
							"column2",
							FilterStringToQueryBuilderConverter.
								buildIgnoreCaseRegExp("escaped'quote)"))
					).should(
						BoolQueryBuilderUtil.filter(
							BoolQueryBuilderUtil.mustNot(
								QueryBuilders.termQuery("column3", true))
						).filter(
							QueryBuilders.rangeQuery(
								"column4"
							).lte(
								97531.8642
							)
						)
					)
				)
			).filter(
				QueryBuilders.existsQuery("column5")
			),
			"((column1 gt 42 or ((contains(column2, 'escaped''quote)')) or " +
				"(column3 ne true and column4 le 97531.8642)) and column5 ne " +
					"null))");
	}

	@Test
	public void testFreestyle2() throws Exception {
		_assertEquals(
			BoolQueryBuilderUtil.should(
				BoolQueryBuilderUtil.filter(
					BoolQueryBuilderUtil.mustNot(
						QueryBuilders.termQuery("column1", "null"))
				).filter(
					QueryBuilders.existsQuery("column2")
				)
			).should(
				BoolQueryBuilderUtil.should(
					BoolQueryBuilderUtil.filter(
						QueryBuilders.termQuery("column3", "true")
					).filter(
						QueryBuilders.termQuery("column4", true)
					)
				).should(
					BoolQueryBuilderUtil.filter(
						QueryBuilders.rangeQuery(
							"column5"
						).gt(
							-53.21
						)
					).filter(
						QueryBuilders.rangeQuery(
							"column6"
						).lte(
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
			BoolQueryBuilderUtil.filter(
				BoolQueryBuilderUtil.should(
					QueryBuilders.termQuery("column1", "value1")
				).should(
					QueryBuilders.termQuery("column2", "value2")
				)
			).filter(
				QueryBuilders.termQuery("column3", "value3")
			),
			"((column1 eq 'value1' or column2 eq 'value2') and column3 eq " +
				"'value3')");
	}

	@Test
	public void testGeOperator() throws Exception {
		_assertEquals(
			QueryBuilders.rangeQuery(
				"column1"
			).gte(
				"value1"
			),
			"column1 ge 'value1'");
	}

	@Test
	public void testGtOperator() throws Exception {
		_assertEquals(
			QueryBuilders.rangeQuery(
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
			QueryBuilders.termQuery("column1", 123), "column1 eq 123");
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
			QueryBuilders.rangeQuery(
				"column1"
			).lte(
				"value1"
			),
			"column1 le 'value1'");
	}

	@Test
	public void testLtOperator() throws Exception {
		_assertEquals(
			QueryBuilders.rangeQuery(
				"column1"
			).lt(
				"value1"
			),
			"column1 lt 'value1'");
	}

	@Test
	public void testNeNull() throws Exception {
		_assertEquals(QueryBuilders.existsQuery("column1"), "column1 ne null");
	}

	@Test
	public void testNeOperator() throws Exception {
		_assertEquals(
			BoolQueryBuilderUtil.mustNot(
				QueryBuilders.termQuery("column1", "value1")),
			"column1 ne 'value1'");
	}

	@Test
	public void testNotContainsOperator() throws Exception {
		_assertEquals(
			BoolQueryBuilderUtil.mustNot(
				QueryBuilders.regexpQuery(
					"column1",
					FilterStringToQueryBuilderConverter.buildIgnoreCaseRegExp(
						"value1"))),
			"not contains(column1, 'value1')");
	}

	@Test
	public void testNotEndsWithOperator() throws Exception {
		_assertEquals(
			BoolQueryBuilderUtil.mustNot(
				QueryBuilders.wildcardQuery("column1", "*value1")),
			"not endsWith(column1, 'value1')");
	}

	@Test
	public void testNotStartsWithOperator() throws Exception {
		_assertEquals(
			BoolQueryBuilderUtil.mustNot(
				QueryBuilders.wildcardQuery("column1", "value1*")),
			"not startsWith(column1, 'value1')");
	}

	@Test
	public void testNullValue() throws Exception {
		_assertEquals(
			BoolQueryBuilderUtil.mustNot(QueryBuilders.existsQuery("column1")),
			"column1 eq null");
	}

	@Test
	public void testOrderOfOperations1() throws Exception {
		_assertEquals(
			BoolQueryBuilderUtil.should(
				QueryBuilders.termQuery("column1", "value1")
			).should(
				BoolQueryBuilderUtil.filter(
					QueryBuilders.rangeQuery(
						"column2"
					).lte(
						"value2"
					)
				).filter(
					QueryBuilders.rangeQuery(
						"column3"
					).gte(
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
			BoolQueryBuilderUtil.filter(
				BoolQueryBuilderUtil.should(
					QueryBuilders.rangeQuery(
						"column1"
					).lt(
						"value1"
					)
				).should(
					QueryBuilders.rangeQuery(
						"column2"
					).gt(
						"value2"
					)
				)
			).filter(
				BoolQueryBuilderUtil.mustNot(
					QueryBuilders.termQuery("column3", "value3"))
			),
			"(column1 lt 'value1' or column2 gt 'value2') and column3 ne " +
				"'value3'");
	}

	@Test
	public void testOrOperator() throws Exception {
		_assertEquals(
			BoolQueryBuilderUtil.should(
				QueryBuilders.rangeQuery(
					"column1"
				).gt(
					"value1"
				)
			).should(
				QueryBuilders.rangeQuery(
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
			QueryBuilders.termQuery("column1", "value)1"),
			"((column1 eq 'value)1'))");
	}

	@Test
	public void testStartsWithOperator() throws Exception {
		_assertEquals(
			QueryBuilders.wildcardQuery("column1", "value1*"),
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
			QueryBuilder expectedQueryBuilder, String actualFilterString)
		throws Exception {

		QueryBuilder actualQueryBuilder =
			FilterStringToQueryBuilderConverter.convert(actualFilterString);

		Assert.assertEquals(
			expectedQueryBuilder.toString(), actualQueryBuilder.toString());
	}

	private void _assertThrowsException(String filterString, String message) {
		try {
			FilterStringToQueryBuilderConverter.convert(filterString);

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