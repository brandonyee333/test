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
import com.liferay.osb.asah.common.date.dog.util.TimeZoneDogUtil;
import com.liferay.osb.asah.common.findbugs.SuppressFBWarnings;
import com.liferay.osb.asah.common.postgresql.converter.helper.ActivitiesFilterStringConverterHelper;
import com.liferay.osb.asah.common.postgresql.converter.helper.DataSourceFilterStringConverterHelper;
import com.liferay.osb.asah.common.postgresql.converter.helper.IndividualsFilterStringConverterHelper;
import com.liferay.osb.asah.common.postgresql.converter.helper.OrganizationsFilterStringConverterHelper;
import com.liferay.osb.asah.common.postgresql.converter.helper.SessionsFilterStringConverter;

import java.io.File;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.impl.DSL;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.core.io.ClassPathResource;

/**
 * @author Marcellus Tavares
 */
public class FilterExpressionTest {

	@Test
	public void testAndOperator() {
		_assertEquals(
			DSL.and(
				DSL.field(
					"column1"
				).eq(
					"value1"
				),
				DSL.field(
					"column2"
				).ne(
					"value2"
				)),
			"column1 eq 'value1' and column2 ne 'value2'");
	}

	@Test
	public void testBooleanFalseValue() {
		_assertEquals(
			DSL.field(
				"column1"
			).eq(
				false
			),
			"column1 eq false");
	}

	@Test
	public void testBooleanTrueValue() {
		_assertEquals(
			DSL.field(
				"column1"
			).eq(
				true
			),
			"column1 eq true");
	}

	@Test
	public void testContainsOperator() {
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
	public void testDoubleValue() {
		_assertEquals(
			DSL.field(
				"column1"
			).eq(
				12.34
			),
			"column1 eq 12.34");
	}

	@Test
	public void testEndsWithOperator() {
		_assertEquals(
			DSL.field(
				"column1"
			).similarTo(
				"%value1"
			),
			"endsWith(column1, 'value1')");
	}

	@Test
	public void testEqOperator() {
		_assertEquals(
			DSL.field(
				"column1"
			).eq(
				"value1"
			),
			"column1 eq 'value1'");
	}

	@Test
	public void testEscapeOperator() {
		_assertEquals(
			DSL.field(
				"column1"
			).eq(
				"value'1"
			),
			"column1 eq 'value''1'");
	}

	@Test
	public void testFreestyle1() {
		_assertEquals(
			DSL.or(
				DSL.field(
					DSL.cast(DSL.field("column1"), Long.class)
				).gt(
					42L
				),
				DSL.and(
					DSL.or(
						DSL.field(
							"column2"
						).containsIgnoreCase(
							"escaped'quote)"
						),
						DSL.and(
							DSL.field(
								"column3"
							).ne(
								true
							),
							DSL.field(
								"column4"
							).le(
								97531.8642
							))),
					DSL.field(
						"column5"
					).isNotNull())),
			_testFilters.get("testFreestyle1"));
	}

	@Test
	public void testFreestyle2() {
		_assertEquals(
			DSL.or(
				DSL.and(
					DSL.field(
						"column1"
					).ne(
						"null"
					),
					DSL.field(
						"column2"
					).isNotNull()),
				DSL.or(
					DSL.and(
						DSL.field(
							"column3"
						).eq(
							"true"
						),
						DSL.field(
							"column4"
						).eq(
							true
						)),
					DSL.and(
						DSL.field(
							"column5"
						).gt(
							-53.21
						),
						DSL.field(
							DSL.cast(DSL.field("column6"), Long.class)
						).le(
							-8192L
						)))),
			_testFilters.get("testFreestyle2"));
	}

	@Test
	public void testFreestyle3() {
		_assertEquals(
			DSL.and(
				DSL.or(
					DSL.field(
						"column1"
					).eq(
						"value1"
					),
					DSL.field(
						"column2"
					).eq(
						"value2"
					)),
				DSL.field(
					"column3"
				).eq(
					"value3"
				)),
			_testFilters.get("testFreestyle3"));
	}

	@Test
	public void testFreestyle4() {
		_assertEquals(
			DSL.cast(
				DSL.field("annualRevenue"), Long.class
			).gt(
				500000L
			),
			_testFilters.get("testFreestyle4"),
			_filterTypeFilterStringConverterHelpers, Collections.emptySet());
	}

	@Test
	public void testFreestyle5() {
		_assertEquals(
			DSL.field(
				"jobTitle"
			).containsIgnoreCase(
				"manager"
			),
			"contains(demographics/jobTitle/value, 'manager')");
	}

	@Test
	public void testFreestyle6() {
		LocalDateTime localDateTime = LocalDateTime.of(
			LocalDate.now(TimeZoneDogUtil.getZoneId()), LocalTime.MIDNIGHT);

		_assertEquals(
			DSL.and(
				DSL.field(
					"Individual.addresses"
				).eq(
					"address1"
				),
				DSL.field(
					"Session.browserName"
				).eq(
					"browser1"
				),
				DSL.field(
					"Session.completeDate"
				).gt(
					localDateTime.minusHours(24)
				),
				DSL.field(
					"Individual.addresses"
				).eq(
					"address2"
				)),
			_testFilters.get("testFreestyle6"),
			_filterTypeFilterStringConverterHelpers,
			new HashSet<>(Arrays.asList("Individual", "Session")));
	}

	@Test
	public void testFreestyle7() {
		LocalDateTime localDateTime = LocalDateTime.of(
			LocalDate.now(TimeZoneDogUtil.getZoneId()), LocalTime.MIDNIGHT);

		_assertEquals(
			DSL.or(
				DSL.and(
					DSL.field(
						"Session.country"
					).eq(
						"country1"
					),
					DSL.field(
						"Session.completeDate"
					).gt(
						localDateTime.minusHours(24)
					)),
				DSL.and(
					DSL.field(
						"Session.url"
					).eq(
						"url1"
					),
					DSL.field(
						"Session.completeDate"
					).gt(
						localDateTime.minusHours(24)
					))),
			_testFilters.get("testFreestyle7"),
			_filterTypeFilterStringConverterHelpers,
			new HashSet<>(Arrays.asList("Session")));
	}

	@Test
	public void testFreestyle8() {
		_assertEquals(
			DSL.and(
				DSL.field(
					"Organization.name"
				).eq(
					"name1"
				),
				DSL.field(
					"Organization.type"
				).eq(
					"type1"
				)),
			_testFilters.get("testFreestyle8"),
			_filterTypeFilterStringConverterHelpers,
			new HashSet<>(Arrays.asList("Organization")));
	}

	@Test
	public void testFreestyle9() {
		LocalDateTime localDateTime = LocalDateTime.of(
			LocalDate.now(TimeZoneDogUtil.getZoneId()), LocalTime.MIDNIGHT);

		_assertEquals(
			DSL.and(
				DSL.field(
					"Individual.addresses"
				).eq(
					"address1"
				),
				DSL.field(
					"Session.country"
				).eq(
					"country1"
				),
				DSL.field(
					"Session.completeDate"
				).gt(
					localDateTime.minusHours(24)
				),
				DSL.field(
					"Individual.emailAddress"
				).eq(
					"emailAddress1"
				),
				DSL.field(
					"Session.url"
				).eq(
					"url1"
				),
				DSL.field(
					"Session.completeDate"
				).gt(
					localDateTime.minusHours(24)
				),
				DSL.field(
					"Session.completeDate"
				).eq(
					"2022-12-20T16:56:05.761Z"
				),
				DSL.field(
					"Session.deviceType"
				).eq(
					"deviceType1"
				),
				DSL.field(
					"Session.completeDate"
				).gt(
					localDateTime.minusHours(24)
				),
				DSL.field(
					"Session.browserName"
				).eq(
					"browserName1"
				),
				DSL.field(
					"Session.completeDate"
				).gt(
					localDateTime.minusHours(24)
				),
				DSL.field(
					"Organization.name"
				).eq(
					"name1"
				)),
			_testFilters.get("testFreestyle9"),
			_filterTypeFilterStringConverterHelpers,
			new HashSet<>(
				Arrays.asList("Individual", "Organization", "Session")));
	}

	@Test
	public void testFreestyle10() {
		LocalDateTime localDateTime = LocalDateTime.of(
			LocalDate.now(TimeZoneDogUtil.getZoneId()), LocalTime.MIDNIGHT);

		_assertEquals(
			DSL.and(
				DSL.or(
					DSL.field(
						"Individual.addresses"
					).eq(
						"address1"
					),
					DSL.and(
						DSL.field(
							"Session.country"
						).eq(
							"country1"
						),
						DSL.field(
							"Session.completeDate"
						).gt(
							localDateTime.minusHours(24)
						),
						DSL.field(
							"Individual.emailAddress"
						).eq(
							"emailAddress1"
						),
						DSL.field(
							"Session.url"
						).eq(
							"url1"
						),
						DSL.field(
							"Session.completeDate"
						).gt(
							localDateTime.minusHours(24)
						),
						DSL.field(
							"Session.completeDate"
						).eq(
							"2022-12-20T16:56:05.761Z"
						))),
				DSL.field(
					"Session.deviceType"
				).eq(
					"deviceType1"
				),
				DSL.field(
					"Session.completeDate"
				).gt(
					localDateTime.minusHours(24)
				),
				DSL.field(
					"Session.browserName"
				).eq(
					"browserName1"
				),
				DSL.field(
					"Session.completeDate"
				).gt(
					localDateTime.minusHours(24)
				),
				DSL.field(
					"Organization.name"
				).eq(
					"name1"
				)),
			_testFilters.get("testFreestyle10"),
			_filterTypeFilterStringConverterHelpers,
			new HashSet<>(
				Arrays.asList("Individual", "Organization", "Session")));
	}

	@Test
	public void testFreestyle11() {
		_assertEquals(
			DSL.field(
				"accountPKs"
			).eq(
				"ae549a89-a2f4-4167-858c-e6f23f51beee"
			),
			_testFilters.get("testFreestyle11"));
	}

	@Test
	public void testFreestyle12() {
		Field userIdField = DSL.field("Event.userId");
		LocalDateTime localDateTime = LocalDateTime.of(
			LocalDate.now(TimeZoneDogUtil.getZoneId()), LocalTime.MIDNIGHT);

		_assertEquals(
			DSL.exists(
				DSL.select(
					userIdField
				).from(
					DSL.table(
						"BQEvent"
					).as(
						"Event"
					)
				).where(
					DSL.field(
						"Event.applicationId"
					).eq(
						"Blog"
					).and(
						DSL.field(
							"Event.eventId"
						).eq(
							"blogViewed"
						)
					).and(
						DSL.field(
							"Event.id"
						).eq(
							"370994124094927024"
						)
					).and(
						DSL.field(
							"Event.dayDate"
						).gt(
							localDateTime.minus(1, ChronoUnit.DAYS)
						)
					).and(
						userIdField.eq(DSL.field("Identity.id"))
					)
				).groupBy(
					userIdField
				).having(
					DSL.count(
						userIdField
					).ge(
						1
					)
				)),
			_testFilters.get("testFreestyle12"),
			_filterTypeFilterStringConverterHelpers,
			new HashSet<>(Arrays.asList("Event", "Identity")));
	}

	@Test
	public void testFreestyle13() {
		Field userIdField = DSL.field("Event.userId");
		LocalDateTime localDateTime = LocalDateTime.of(
			LocalDate.now(TimeZoneDogUtil.getZoneId()), LocalTime.MIDNIGHT);

		_assertEquals(
			DSL.exists(
				DSL.select(
					userIdField
				).from(
					DSL.table(
						"BQEvent"
					).as(
						"Event"
					)
				).where(
					DSL.field(
						"Event.applicationId"
					).eq(
						"Form"
					).and(
						DSL.field(
							"Event.eventId"
						).eq(
							"formSubmitted"
						)
					).and(
						DSL.field(
							"Event.id"
						).eq(
							"519356017509996745"
						)
					).and(
						DSL.field(
							"Event.dayDate"
						).gt(
							localDateTime.minus(1, ChronoUnit.DAYS)
						)
					).and(
						userIdField.eq(DSL.field("Identity.id"))
					)
				).groupBy(
					userIdField
				).having(
					DSL.count(
						userIdField
					).ge(
						1
					)
				)
			).and(
				DSL.exists(
					DSL.select(
						userIdField
					).from(
						DSL.table(
							"BQEvent"
						).as(
							"Event"
						)
					).where(
						DSL.field(
							"Event.applicationId"
						).eq(
							"Form"
						).and(
							DSL.field(
								"Event.eventId"
							).eq(
								"formSubmitted"
							)
						).and(
							DSL.field(
								"Event.id"
							).eq(
								"499704442662154327"
							)
						).and(
							DSL.field(
								"Event.dayDate"
							).gt(
								localDateTime.minus(1, ChronoUnit.DAYS)
							)
						).and(
							userIdField.eq(DSL.field("Identity.id"))
						)
					).groupBy(
						userIdField
					).having(
						DSL.count(
							userIdField
						).ge(
							1
						)
					))
			),
			_testFilters.get("testFreestyle13"),
			_filterTypeFilterStringConverterHelpers,
			new HashSet<>(Arrays.asList("Event", "Identity")));
	}

	@Test
	public void testFreestyle14() {
		Field userIdField = DSL.field("Event.userId");
		LocalDateTime localDateTime = LocalDateTime.of(
			LocalDate.now(TimeZoneDogUtil.getZoneId()), LocalTime.MIDNIGHT);

		_assertEquals(
			DSL.field(
				"Individual.address"
			).eq(
				"address"
			).and(
				DSL.or(
					DSL.field(
						"Individual.jobTitle"
					).eq(
						"jobtitle"
					),
					DSL.exists(
						DSL.select(
							userIdField
						).from(
							DSL.table(
								"BQEvent"
							).as(
								"Event"
							)
						).where(
							DSL.field(
								"Event.applicationId"
							).eq(
								"Page"
							).and(
								DSL.field(
									"Event.eventId"
								).eq(
									"pageViewed"
								)
							).and(
								DSL.field(
									"Event.id"
								).eq(
									"370983685501627145"
								)
							).and(
								DSL.field(
									"Event.dayDate"
								).gt(
									localDateTime.minus(1, ChronoUnit.DAYS)
								)
							).and(
								userIdField.eq(DSL.field("Identity.id"))
							)
						).groupBy(
							userIdField
						).having(
							DSL.count(
								userIdField
							).ge(
								1
							)
						)))
			).and(
				DSL.field(
					"Individual.emailAddress"
				).eq(
					"email@test.com"
				)
			),
			_testFilters.get("testFreestyle14"),
			_filterTypeFilterStringConverterHelpers,
			new HashSet<>(Arrays.asList("Event", "Identity", "Individual")));
	}

	@Test
	public void testFreestyle15() {
		Field userIdField = DSL.field("Event.userId");
		LocalDateTime localDateTime = LocalDateTime.of(
			LocalDate.now(TimeZoneDogUtil.getZoneId()), LocalTime.MIDNIGHT);

		_assertEquals(
			DSL.field(
				"Individual.address"
			).eq(
				"address"
			).and(
				DSL.or(
					DSL.field(
						"Individual.jobTitle"
					).eq(
						"jobtitle"
					),
					DSL.not(
						DSL.exists(
							DSL.select(
								userIdField
							).from(
								DSL.table(
									"BQEvent"
								).as(
									"Event"
								)
							).where(
								DSL.field(
									"Event.applicationId"
								).eq(
									"Page"
								).and(
									DSL.field(
										"Event.eventId"
									).eq(
										"pageViewed"
									)
								).and(
									DSL.field(
										"Event.id"
									).eq(
										"370983685501627145"
									)
								).and(
									DSL.field(
										"Event.dayDate"
									).gt(
										localDateTime.minus(1, ChronoUnit.DAYS)
									)
								).and(
									userIdField.eq(DSL.field("Identity.id"))
								)
							).groupBy(
								userIdField
							).having(
								DSL.count(
									userIdField
								).ge(
									1
								)
							))))
			).and(
				DSL.field(
					"Individual.emailAddress"
				).eq(
					"email@test.com"
				)
			),
			_testFilters.get("testFreestyle15"),
			_filterTypeFilterStringConverterHelpers,
			new HashSet<>(Arrays.asList("Event", "Identity", "Individual")));
	}

	@Test
	public void testGeOperator() {
		_assertEquals(
			DSL.field(
				"column1"
			).ge(
				"value1"
			),
			"column1 ge 'value1'");
	}

	@Test
	public void testGtOperator() {
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
	public void testIndividualsEqAndNull() {
		Condition expectedCondition = DSL.and(
			DSL.field(
				"IdentityActivity.channelId"
			).eq(
				506297979389450553L
			),
			DSL.field(
				"Individual.emailAddress"
			).isNotNull());

		Condition actualCondition = FilterExpression.convert(
			"(channelIds eq '506297979389450553' and " +
				"(demographics/email/value ne null))",
			new IndividualsFilterStringConverterHelper());

		Assertions.assertEquals(
			expectedCondition.toString(), actualCondition.toString());
	}

	@Test
	public void testIntegerValue() {
		_assertEquals(
			DSL.field(
				DSL.cast(DSL.field("column1"), Long.class)
			).eq(
				123L
			),
			"column1 eq 123");
	}

	@Test
	public void testInvalidLogicalOperatorThrowsException() {
		_assertThrowsException(
			"column1 eq 'value1' but column2 eq 'value2'",
			"Expected logical operator \"and\" or \"or\", but got \"but\" " +
				"instead");
	}

	@Test
	public void testInvalidObjectThrowsException() {
		_assertThrowsException(
			"column1 eq value1", "no viable alternative at input 'value1'");
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
	public void testLeOperator() {
		_assertEquals(
			DSL.field(
				"column1"
			).le(
				"value1"
			),
			"column1 le 'value1'");
	}

	@Test
	public void testLtOperator() {
		_assertEquals(
			DSL.field(
				"column1"
			).lt(
				"value1"
			),
			"column1 lt 'value1'");
	}

	@Test
	public void testNeNull() {
		_assertEquals(
			DSL.field(
				"column1"
			).isNotNull(),
			"column1 ne null");
	}

	@Test
	public void testNeOperator() {
		_assertEquals(
			DSL.field(
				"column1"
			).ne(
				"value1"
			),
			"column1 ne 'value1'");
	}

	@Test
	public void testNotContainsOperator() {
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
	public void testNotEndsWithOperator() {
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
	public void testNotStartsWithOperator() {
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
	public void testNullValue() {
		_assertEquals(
			DSL.field(
				"column1"
			).isNull(),
			"column1 eq null");
	}

	@Test
	public void testOrderOfOperations1() {
		_assertEquals(
			DSL.or(
				DSL.field(
					"column1"
				).eq(
					"value1"
				),
				DSL.and(
					DSL.field(
						"column2"
					).le(
						"value2"
					),
					DSL.field(
						"column3"
					).ge(
						"value3"
					))),
			"column1 eq 'value1' or (column2 le 'value2' and column3 ge " +
				"'value3')");
	}

	@Test
	public void testOrderOfOperations2() {
		_assertEquals(
			DSL.and(
				DSL.or(
					DSL.field(
						"column1"
					).lt(
						"value1"
					),
					DSL.field(
						"column2"
					).gt(
						"value2"
					)),
				DSL.field(
					"column3"
				).ne(
					"value3"
				)),
			"(column1 lt 'value1' or column2 gt 'value2') and column3 ne " +
				"'value3'");
	}

	@Test
	public void testOrOperator() {
		_assertEquals(
			DSL.or(
				DSL.field(
					"column1"
				).gt(
					"value1"
				),
				DSL.field(
					"column2"
				).lt(
					"value2"
				)),
			"column1 gt 'value1' or column2 lt 'value2'");
	}

	@Test
	public void testParentheses() {
		_assertEquals(
			DSL.field(
				"column1"
			).eq(
				"value)1"
			),
			"((column1 eq 'value)1'))");
	}

	@Test
	public void testStartsWithOperator() {
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
		Condition expectedCondition, String actualFilterExpressionString) {

		Assertions.assertEquals(
			expectedCondition,
			FilterExpression.convert(actualFilterExpressionString));
	}

	private void _assertEquals(
		Condition expectedCondition, String actualFilterExpressionString,
		List<FilterStringConverterHelper> filterTypeStringConverterHelpers,
		Set<String> includedTableNames) {

		JoinCondition joinCondition = FilterExpression.convert(
			actualFilterExpressionString, filterTypeStringConverterHelpers);

		Assertions.assertEquals(
			expectedCondition, joinCondition.getCondition());

		Assertions.assertEquals(
			includedTableNames, joinCondition.getIncludedTableNames());
	}

	private void _assertThrowsException(
		String filterExpressionString, String message) {

		Assertions.assertThrows(
			FilterExpressionParserException.class,
			() -> FilterExpression.convert(filterExpressionString), message);
	}

	@SuppressFBWarnings
	private Map<String, String> _getTestFilters() {
		ClassPathResource classPathResource = new ClassPathResource(
			"filters.txt", getClass());

		try {
			File file = classPathResource.getFile();

			List<String> filters = Files.readAllLines(
				Paths.get(file.getAbsolutePath()));

			Stream<String> stream = filters.stream();

			return stream.map(
				filter -> filter.split("=", 2)
			).collect(
				Collectors.toMap(filter -> filter[0], filter -> filter[1])
			);
		}
		catch (IOException ioException) {
			throw new RuntimeException(ioException);
		}
	}

	private static final List<FilterStringConverterHelper>
		_filterTypeFilterStringConverterHelpers =
			new ArrayList<FilterStringConverterHelper>() {
				{
					add(new IndividualsFilterStringConverterHelper());
					add(new ActivitiesFilterStringConverterHelper());
					add(new DataSourceFilterStringConverterHelper());
					add(new OrganizationsFilterStringConverterHelper());
					add(new SessionsFilterStringConverter());
				}
			};

	private final Map<String, String> _testFilters = _getTestFilters();

}