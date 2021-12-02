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

package com.liferay.osb.asah.common.model.filter;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.entity.EventAttributeDefinition;

import java.time.LocalDate;
import java.time.ZoneOffset;

import java.util.ArrayList;
import java.util.Collections;

import org.jooq.impl.DSL;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Leslie Wong
 */
public class FilterOperatorTest {

	@Test
	public void testBetweenFilterOperatorDate() {
		FilterOperator filterOperator = FilterOperators.of(
			EventAttributeDefinition.DataType.DATE, "between",
			new ArrayList<String>() {
				{
					add("2021-06-01");
					add("2021-06-23");
				}
			});

		LocalDate startLocalDate = LocalDate.parse("2021-06-01");
		LocalDate endLocalDate = LocalDate.parse("2021-06-23");

		Assertions.assertEquals(
			DSL.and(
				DSL.field(
					"testField"
				).ge(
					DSL.date(
						DateUtil.toDate(
							startLocalDate.atStartOfDay(), ZoneOffset.UTC))
				),
				DSL.field(
					"testField"
				).le(
					DSL.date(
						DateUtil.toDate(
							endLocalDate.atStartOfDay(), ZoneOffset.UTC))
				)),
			filterOperator.getCondition(DSL.field("testField")));
	}

	@Test
	public void testBetweenFilterOperatorDuration() {
		FilterOperator filterOperator = FilterOperators.of(
			EventAttributeDefinition.DataType.DURATION, "between",
			new ArrayList<String>() {
				{
					add("3600");
					add("36000");
				}
			});

		Assertions.assertEquals(
			DSL.and(
				DSL.field(
					"testField"
				).ge(
					3600
				),
				DSL.field(
					"testField"
				).le(
					36000
				)),
			filterOperator.getCondition(DSL.field("testField")));
	}

	@Test
	public void testBetweenFilterOperatorNumber() {
		FilterOperator filterOperator = FilterOperators.of(
			EventAttributeDefinition.DataType.NUMBER, "between",
			new ArrayList<String>() {
				{
					add("20");
					add("27");
				}
			});

		Assertions.assertEquals(
			DSL.and(
				DSL.field(
					"testField"
				).ge(
					20.0
				),
				DSL.field(
					"testField"
				).le(
					27.0
				)),
			filterOperator.getCondition(DSL.field("testField")));
	}

	@Test
	public void testContainsFilterOperator() {
		FilterOperator filterOperator = FilterOperators.of(
			EventAttributeDefinition.DataType.STRING, "contains",
			Collections.singletonList("testValue"));

		Assertions.assertEquals(
			DSL.field(
				"testField"
			).containsIgnoreCase(
				"testValue"
			),
			filterOperator.getCondition(DSL.field("testField")));
	}

	@Test
	public void testEndsWithFilterOperator() {
		FilterOperator filterOperator = FilterOperators.of(
			EventAttributeDefinition.DataType.STRING, "endsWith",
			Collections.singletonList("testValue"));

		Assertions.assertEquals(
			DSL.field(
				"testField"
			).endsWithIgnoreCase(
				"testValue"
			),
			filterOperator.getCondition(DSL.field("testField")));
	}

	@Test
	public void testEqualsFilterOperator() {
		FilterOperator filterOperator = FilterOperators.of(
			EventAttributeDefinition.DataType.DURATION, "eq",
			Collections.singletonList("123"));

		Assertions.assertEquals(
			DSL.field(
				"testField"
			).eq(
				123
			),
			filterOperator.getCondition(DSL.field("testField")));
	}

	@Test
	public void testEqualsFilterOperatorNull() {
		FilterOperator filterOperator = FilterOperators.of(
			EventAttributeDefinition.DataType.STRING, "eq",
			Collections.singletonList(null));

		Assertions.assertEquals(
			DSL.field(
				"testField"
			).isNull(),
			filterOperator.getCondition(DSL.field("testField")));
	}

	@Test
	public void testEqualsFilterOperatorString() {
		FilterOperator filterOperator = FilterOperators.of(
			EventAttributeDefinition.DataType.STRING, "eq",
			Collections.singletonList("testValue"));

		Assertions.assertEquals(
			DSL.field(
				"testField"
			).equalIgnoreCase(
				"testValue"
			),
			filterOperator.getCondition(DSL.field("testField")));
	}

	@Test
	public void testFilterOperatorBadArgumentsCount() {
		Assertions.assertThrows(
			IllegalArgumentException.class,
			() -> FilterOperators.of(
				EventAttributeDefinition.DataType.STRING, "contains",
				new ArrayList<String>() {
					{
						add("test1");
						add("test2");
					}
				}));
	}

	@Test
	public void testFilterOperatorUnsupportedDataType() {
		Assertions.assertThrows(
			IllegalArgumentException.class,
			() -> FilterOperators.of(
				EventAttributeDefinition.DataType.STRING, "gt",
				new ArrayList<String>() {
					{
						add("test");
					}
				}));
	}

	@Test
	public void testGreaterThanEqualsFilterOperatorDate() {
		String dateString = "2021-06-01";

		FilterOperator filterOperator = FilterOperators.of(
			EventAttributeDefinition.DataType.DATE, "ge",
			Collections.singletonList(dateString));

		LocalDate localDate = LocalDate.parse(dateString);

		Assertions.assertEquals(
			DSL.field(
				"testField"
			).ge(
				DSL.date(
					DateUtil.toDate(localDate.atStartOfDay(), ZoneOffset.UTC))
			),
			filterOperator.getCondition(DSL.field("testField")));
	}

	@Test
	public void testGreaterThanEqualsFilterOperatorDuration() {
		FilterOperator filterOperator = FilterOperators.of(
			EventAttributeDefinition.DataType.DURATION, "ge",
			Collections.singletonList("123"));

		Assertions.assertEquals(
			DSL.field(
				"testField"
			).ge(
				123
			),
			filterOperator.getCondition(DSL.field("testField")));
	}

	@Test
	public void testGreaterThanEqualsFilterOperatorNumber() {
		FilterOperator filterOperator = FilterOperators.of(
			EventAttributeDefinition.DataType.NUMBER, "ge",
			Collections.singletonList("123"));

		Assertions.assertEquals(
			DSL.field(
				"testField"
			).ge(
				123.0
			),
			filterOperator.getCondition(DSL.field("testField")));
	}

	@Test
	public void testGreaterThanFilterOperatorDate() {
		String dateString = "2021-06-01";

		FilterOperator filterOperator = FilterOperators.of(
			EventAttributeDefinition.DataType.DATE, "gt",
			Collections.singletonList(dateString));

		LocalDate localDate = LocalDate.parse(dateString);

		Assertions.assertEquals(
			DSL.field(
				"testField"
			).gt(
				DSL.date(
					DateUtil.toDate(localDate.atStartOfDay(), ZoneOffset.UTC))
			),
			filterOperator.getCondition(DSL.field("testField")));
	}

	@Test
	public void testGreaterThanFilterOperatorDuration() {
		FilterOperator filterOperator = FilterOperators.of(
			EventAttributeDefinition.DataType.DURATION, "gt",
			Collections.singletonList("123"));

		Assertions.assertEquals(
			DSL.field(
				"testField"
			).gt(
				123
			),
			filterOperator.getCondition(DSL.field("testField")));
	}

	@Test
	public void testGreaterThanFilterOperatorNumber() {
		FilterOperator filterOperator = FilterOperators.of(
			EventAttributeDefinition.DataType.NUMBER, "gt",
			Collections.singletonList("123"));

		Assertions.assertEquals(
			DSL.field(
				"testField"
			).gt(
				123.0
			),
			filterOperator.getCondition(DSL.field("testField")));
	}

	@Test
	public void testLessThanEqualsFilterOperatorDate() {
		String dateString = "2020-06-01";

		FilterOperator filterOperator = FilterOperators.of(
			EventAttributeDefinition.DataType.DATE, "le",
			Collections.singletonList(dateString));

		LocalDate localDate = LocalDate.parse(dateString);

		Assertions.assertEquals(
			DSL.field(
				"testField"
			).le(
				DSL.date(
					DateUtil.toDate(localDate.atStartOfDay(), ZoneOffset.UTC))
			),
			filterOperator.getCondition(DSL.field("testField")));
	}

	@Test
	public void testLessThanEqualsFilterOperatorDuration() {
		FilterOperator filterOperator = FilterOperators.of(
			EventAttributeDefinition.DataType.DURATION, "le",
			Collections.singletonList("123"));

		Assertions.assertEquals(
			DSL.field(
				"testField"
			).le(
				123
			),
			filterOperator.getCondition(DSL.field("testField")));
	}

	@Test
	public void testLessThanEqualsFilterOperatorNumber() {
		FilterOperator filterOperator = FilterOperators.of(
			EventAttributeDefinition.DataType.NUMBER, "le",
			Collections.singletonList("123"));

		Assertions.assertEquals(
			DSL.field(
				"testField"
			).le(
				123.0
			),
			filterOperator.getCondition(DSL.field("testField")));
	}

	@Test
	public void testLessThanFilterOperatorDate() {
		String dateString = "2021-06-01";

		FilterOperator filterOperator = FilterOperators.of(
			EventAttributeDefinition.DataType.DATE, "lt",
			Collections.singletonList(dateString));

		LocalDate localDate = LocalDate.parse(dateString);

		Assertions.assertEquals(
			DSL.field(
				"testField"
			).lt(
				DSL.date(
					DateUtil.toDate(localDate.atStartOfDay(), ZoneOffset.UTC))
			),
			filterOperator.getCondition(DSL.field("testField")));
	}

	@Test
	public void testLessThanFilterOperatorDuration() {
		FilterOperator filterOperator = FilterOperators.of(
			EventAttributeDefinition.DataType.DURATION, "lt",
			Collections.singletonList("123"));

		Assertions.assertEquals(
			DSL.field(
				"testField"
			).lt(
				123
			),
			filterOperator.getCondition(DSL.field("testField")));
	}

	@Test
	public void testLessThanFilterOperatorNumber() {
		FilterOperator filterOperator = FilterOperators.of(
			EventAttributeDefinition.DataType.NUMBER, "lt",
			Collections.singletonList("123"));

		Assertions.assertEquals(
			DSL.field(
				"testField"
			).lt(
				123.0
			),
			filterOperator.getCondition(DSL.field("testField")));
	}

	@Test
	public void testNotContainsFilterOperator() {
		FilterOperator filterOperator = FilterOperators.of(
			EventAttributeDefinition.DataType.STRING, "notContains",
			Collections.singletonList("testValue"));

		Assertions.assertEquals(
			DSL.field(
				"testField"
			).notContainsIgnoreCase(
				"testValue"
			),
			filterOperator.getCondition(DSL.field("testField")));
	}

	@Test
	public void testNotEqualsFilterOperator() {
		FilterOperator filterOperator = FilterOperators.of(
			EventAttributeDefinition.DataType.DURATION, "ne",
			Collections.singletonList("123"));

		Assertions.assertEquals(
			DSL.field(
				"testField"
			).ne(
				123
			),
			filterOperator.getCondition(DSL.field("testField")));
	}

	@Test
	public void testNotEqualsFilterOperatorNull() {
		FilterOperator filterOperator = FilterOperators.of(
			EventAttributeDefinition.DataType.STRING, "ne",
			Collections.singletonList(null));

		Assertions.assertEquals(
			DSL.field(
				"testField"
			).isNotNull(),
			filterOperator.getCondition(DSL.field("testField")));
	}

	@Test
	public void testNotEqualsFilterOperatorString() {
		FilterOperator filterOperator = FilterOperators.of(
			EventAttributeDefinition.DataType.STRING, "ne",
			Collections.singletonList("testValue"));

		Assertions.assertEquals(
			DSL.field(
				"testField"
			).notEqualIgnoreCase(
				"testValue"
			),
			filterOperator.getCondition(DSL.field("testField")));
	}

	@Test
	public void testSimilarToFilterOperator() {
		FilterOperator filterOperator = FilterOperators.of(
			EventAttributeDefinition.DataType.STRING, "similarTo",
			Collections.singletonList("test.*Value"));

		Assertions.assertEquals(
			DSL.lower(
				DSL.field("testField", String.class)
			).similarTo(
				"test_%value"
			),
			filterOperator.getCondition(DSL.field("testField")));
	}

	@Test
	public void testStartsWithFilterOperator() {
		FilterOperator filterOperator = FilterOperators.of(
			EventAttributeDefinition.DataType.STRING, "startsWith",
			Collections.singletonList("testValue"));

		Assertions.assertEquals(
			DSL.field(
				"testField"
			).startsWithIgnoreCase(
				"testValue"
			),
			filterOperator.getCondition(DSL.field("testField")));
	}

}