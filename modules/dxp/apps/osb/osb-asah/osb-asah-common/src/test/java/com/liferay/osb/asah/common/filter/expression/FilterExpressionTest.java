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

import com.liferay.osb.asah.common.date.dog.util.TimeZoneDogUtil;
import com.liferay.osb.asah.common.findbugs.SuppressFBWarnings;

import java.io.File;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import java.util.Arrays;
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
import org.junit.jupiter.api.Disabled;
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
	public void testCastOperatorBoolean() {
		_assertEquals(
			DSL.field(
				"SAFE_CAST(Individual.defaultUser AS BOOLEAN)"
			).eq(
				true
			),
			"individuals.filter(filter='(cast(demographics/defaultUser/value" +
				", ''BOOLEAN'') eq true)')");

		_assertEquals(
			DSL.field(
				"SAFE_CAST(Individual.defaultUser AS BOOLEAN)"
			).eq(
				false
			),
			"individuals.filter(filter='(cast(demographics/defaultUser/value" +
				", ''BOOLEAN'') eq false)')");
	}

	@Test
	public void testCastOperatorDate() {
		_assertEquals(
			DSL.field(
				"SAFE_CAST(Individual.birthday AS DATE)"
			).eq(
				"2023-02-16"
			),
			"individuals.filter(filter='(cast(demographics/birthDate/value, " +
				"''DATE'') eq ''2023-02-16'')')");

		_assertEquals(
			DSL.field(
				"SAFE_CAST(Individual.birthday AS DATE)"
			).gt(
				"2023-02-16"
			),
			"individuals.filter(filter='(cast(demographics/birthDate/value, " +
				"''DATE'') gt ''2023-02-16'')')");

		_assertEquals(
			DSL.field(
				"SAFE_CAST(Individual.birthday AS DATE)"
			).lt(
				"2023-02-16"
			),
			"individuals.filter(filter='(cast(demographics/birthDate/value, " +
				"''DATE'') lt ''2023-02-16'')')");
	}

	@Test
	public void testCastOperatorNumeric() {
		_assertEquals(
			DSL.cast(
				DSL.field("SAFE_CAST(Individual.contactId AS NUMERIC)"),
				Long.class
			).eq(
				0L
			),
			"individuals.filter(filter='(cast(demographics/contactId/value, " +
				"''NUMBER'') eq 0)')");

		_assertEquals(
			DSL.cast(
				DSL.field("SAFE_CAST(Individual.contactId AS NUMERIC)"),
				Long.class
			).ne(
				0L
			),
			"individuals.filter(filter='cast(demographics/contactId/value, " +
				"''NUMBER'') ne 0')");

		_assertEquals(
			DSL.cast(
				DSL.field("SAFE_CAST(Individual.contactId AS NUMERIC)"),
				Long.class
			).gt(
				0L
			),
			"individuals.filter(filter='cast(demographics/contactId/value, " +
				"''NUMBER'') gt 0')");

		_assertEquals(
			DSL.cast(
				DSL.field("SAFE_CAST(Individual.contactId AS NUMERIC)"),
				Long.class
			).lt(
				0L
			),
			"individuals.filter(filter='cast(demographics/contactId/value, " +
				"''NUMBER'') lt 0')");

		_assertEquals(
			DSL.field(
				"Individual.contactId"
			).isNull(),
			"individuals.filter(filter='demographics/contactId/value eq " +
				"null')");

		_assertEquals(
			DSL.field(
				"Individual.contactId"
			).isNotNull(),
			"individuals.filter(filter='demographics/contactId/value ne " +
				"null')");
	}

	@Test
	public void testContainsOperator() {
		_assertEquals(
			DSL.lower(
				DSL.field("column1", String.class)
			).like(
				"%value1%"
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
						DSL.lower(
							DSL.field("column2", String.class)
						).like(
							"%escaped'quote)%"
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
	public void testFreestyle5() {
		_assertEquals(
			DSL.lower(
				DSL.field("Individual.jobTitle", String.class)
			).like(
				"%manager%"
			),
			"individuals.filter(filter='contains(demographics/jobTitle/value" +
				", ''manager'')')");
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
			new HashSet<>(Arrays.asList("Session")));
	}

	@Test
	public void testFreestyle8() {
		_assertEquals(
			DSL.and(
				DSL.field(
					"Individual.id", String.class
				).in(
					DSL.selectDistinct(
						DSL.field("Individual.id", String.class)
					).from(
						DSL.table(
							"BQIndividual"
						).as(
							"Individual"
						)
					).crossJoin(
						DSL.unnest(
							DSL.field("Individual.memberships", String[].class)
						).as(
							"IndividualMemberships"
						)
					).join(
						DSL.table(
							"BQOrganization"
						).as(
							"Organization"
						)
					).on(
						DSL.field(
							"Organization.id"
						).in(
							DSL.function(
								"unnest", String[].class,
								DSL.field("IndividualMemberships.ids"))
						)
					).where(
						DSL.field(
							"Organization.name"
						).eq(
							"name1"
						)
					)
				),
				DSL.field(
					"Individual.id", String.class
				).in(
					DSL.selectDistinct(
						DSL.field("Individual.id", String.class)
					).from(
						DSL.table(
							"BQIndividual"
						).as(
							"Individual"
						)
					).crossJoin(
						DSL.unnest(
							DSL.field("Individual.memberships", String[].class)
						).as(
							"IndividualMemberships"
						)
					).join(
						DSL.table(
							"BQOrganization"
						).as(
							"Organization"
						)
					).on(
						DSL.field(
							"Organization.id"
						).in(
							DSL.function(
								"unnest", String[].class,
								DSL.field("IndividualMemberships.ids"))
						)
					).where(
						DSL.field(
							"Organization.type"
						).eq(
							"type1"
						)
					)
				)),
			_testFilters.get("testFreestyle8"),
			new HashSet<>(Arrays.asList("Individual", "Organization")));
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
					"Individual.id", String.class
				).in(
					DSL.selectDistinct(
						DSL.field("Individual.id", String.class)
					).from(
						DSL.table(
							"BQIndividual"
						).as(
							"Individual"
						)
					).crossJoin(
						DSL.unnest(
							DSL.field("Individual.memberships", String[].class)
						).as(
							"IndividualMemberships"
						)
					).join(
						DSL.table(
							"BQOrganization"
						).as(
							"Organization"
						)
					).on(
						DSL.field(
							"Organization.id"
						).in(
							DSL.function(
								"unnest", String[].class,
								DSL.field("IndividualMemberships.ids"))
						)
					).where(
						DSL.field(
							"Organization.name"
						).eq(
							"name1"
						)
					)
				)),
			_testFilters.get("testFreestyle9"),
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
					"Individual.id", String.class
				).in(
					DSL.selectDistinct(
						DSL.field("Individual.id", String.class)
					).from(
						DSL.table(
							"BQIndividual"
						).as(
							"Individual"
						)
					).crossJoin(
						DSL.unnest(
							DSL.field("Individual.memberships", String[].class)
						).as(
							"IndividualMemberships"
						)
					).join(
						DSL.table(
							"BQOrganization"
						).as(
							"Organization"
						)
					).on(
						DSL.field(
							"Organization.id"
						).in(
							DSL.function(
								"unnest", String[].class,
								DSL.field("IndividualMemberships.ids"))
						)
					).where(
						DSL.field(
							"Organization.name"
						).eq(
							"name1"
						)
					)
				)),
			_testFilters.get("testFreestyle10"),
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
							"Event.eventDate"
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
			new HashSet<>(Arrays.asList("Event")));
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
							"Event.eventDate"
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
								"Event.eventDate"
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
			new HashSet<>(Arrays.asList("Event")));
	}

	@Test
	public void testFreestyle14() {
		Field userIdField = DSL.field("Event.userId");
		LocalDateTime localDateTime = LocalDateTime.of(
			LocalDate.now(TimeZoneDogUtil.getZoneId()), LocalTime.MIDNIGHT);

		_assertEquals(
			DSL.field(
				"Individual.addresses"
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
									"Event.eventDate"
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
			new HashSet<>(Arrays.asList("Event", "Individual")));
	}

	@Test
	public void testFreestyle15() {
		Field userIdField = DSL.field("Event.userId");
		LocalDateTime localDateTime = LocalDateTime.of(
			LocalDate.now(TimeZoneDogUtil.getZoneId()), LocalTime.MIDNIGHT);

		_assertEquals(
			DSL.field(
				"Individual.addresses"
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
										"Event.eventDate"
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
			new HashSet<>(Arrays.asList("Event", "Individual")));
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
	public void testIdentifierDemographicsDate() {
		_assertEquals(
			DSL.field(
				"Individual.birthday"
			).eq(
				"2023-01-01"
			),
			"individuals.filter(filter='(demographics/birthDate/value eq " +
				"''2023-01-01'')')");

		_assertEquals(
			DSL.field(
				"Individual.birthday"
			).gt(
				"2023-01-01"
			),
			"individuals.filter(filter='(demographics/birthDate/value gt " +
				"''2023-01-01'')')");

		_assertEquals(
			DSL.field(
				"Individual.birthday"
			).lt(
				"2023-01-01"
			),
			"individuals.filter(filter='(demographics/birthDate/value lt " +
				"''2023-01-01'')')");
	}

	@Test
	public void testIdentifierDemographicsFieldMappings() {
		_assertEquals(
			DSL.field(
				"Individual.addresses"
			).eq(
				"Test"
			),
			"individuals.filter(filter='(demographics/address/value eq " +
				"''Test'')')");

		_assertEquals(
			DSL.field(
				"Individual.emailAddress"
			).ne(
				"Test"
			),
			"individuals.filter(filter='(demographics/email/value ne " +
				"''Test'')')");

		_assertEquals(
			DSL.field(
				"Individual.lastName"
			).ne(
				"Test"
			),
			"individuals.filter(filter='(demographics/familyName/value ne " +
				"''Test'')')");

		_assertEquals(
			DSL.field(
				"Individual.firstName"
			).ne(
				"Test"
			),
			"individuals.filter(filter='(demographics/givenName/value ne " +
				"''Test'')')");
	}

	@Test
	public void testIdentifierDemographicsText() {
		_assertEquals(
			DSL.field(
				"Individual.firstName"
			).eq(
				"Test"
			),
			"individuals.filter(filter='(demographics/givenName/value eq " +
				"''Test'')')");

		_assertEquals(
			DSL.field(
				"Individual.firstName"
			).ne(
				"Test"
			),
			"individuals.filter(filter='(demographics/givenName/value ne " +
				"''Test'')')");

		_assertEquals(
			DSL.lower(
				DSL.field("Individual.firstName", String.class)
			).like(
				"%liferay.com%"
			),
			"individuals.filter(filter='contains(demographics/givenName" +
				"/value, ''liferay.com'')')");

		_assertEquals(
			DSL.not(
				DSL.lower(
					DSL.field("Individual.firstName", String.class)
				).like(
					"%liferay.com%"
				)),
			"not individuals.filter(filter='contains(demographics/givenName" +
				"/value, ''liferay.com'')')");

		_assertEquals(
			DSL.field(
				"Individual.firstName"
			).isNull(),
			"individuals.filter(filter='demographics/givenName/value eq " +
				"null')");

		_assertEquals(
			DSL.field(
				"Individual.firstName"
			).isNotNull(),
			"individuals.filter(filter='demographics/givenName/value ne " +
				"null')");
	}

	@Test
	public void testIncompleteExpressionThrowsException() {
		_assertThrowsException(
			"column1 eq ", "Expression terminated unexpectedly: column1 eq ");
	}

	@Disabled
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

		FilterExpression filterExpression = new FilterExpression(
			"(channelIds eq '506297979389450553' and " +
				"(demographics/email/value ne null))");

		Condition actualCondition = filterExpression.getCondition();

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
	public void testInterestFilterExpressionFalse() {
		_assertEquals(
			DSL.not(
				DSL.field(
					"Identity.id", String.class
				).in(
					DSL.selectDistinct(
						DSL.field("Identity.id", String.class)
					).from(
						DSL.table(
							"BQIdentity"
						).as(
							"Identity"
						)
					).join(
						DSL.table(
							"BQIdentityInterestScore"
						).as(
							"Interest"
						)
					).on(
						DSL.field(
							"Identity.id", String.class
						).eq(
							DSL.field("Interest.identityId", String.class)
						)
					).where(
						DSL.and(
							DSL.field(
								"Interest.keyword", String.class
							).eq(
								"analytics"
							),
							DSL.field(
								"Interest.interested", Boolean.class
							).eq(
								true
							))
					)
				)),
			"(interests.filter(filter='(name eq ''analytics'' and score eq " +
				"''false'')'))");
	}

	@Test
	public void testInterestFilterExpressionTrue() {
		_assertEquals(
			DSL.field(
				"Identity.id", String.class
			).in(
				DSL.selectDistinct(
					DSL.field("Identity.id", String.class)
				).from(
					DSL.table(
						"BQIdentity"
					).as(
						"Identity"
					)
				).join(
					DSL.table(
						"BQIdentityInterestScore"
					).as(
						"Interest"
					)
				).on(
					DSL.field(
						"Identity.id", String.class
					).eq(
						DSL.field("Interest.identityId", String.class)
					)
				).where(
					DSL.and(
						DSL.field(
							"Interest.keyword", String.class
						).eq(
							"analytics"
						),
						DSL.field(
							"Interest.interested", Boolean.class
						).eq(
							true
						))
				)
			),
			"(interests.filter(filter='(name eq ''analytics'' and score eq " +
				"''true'')'))");
	}

	@Test
	public void testInvalidLogicalOperatorThrowsException() {
		_assertThrowsException(
			"column1 eq 'value1' but column2 eq 'value2'",
			"Expected logical operator \"and\" or \"or\", but got \"but\" " +
				"instead");
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
	public void testIsMemberFilterExpressionEq() {
		_assertEquals(
			DSL.field(
				"Individual.id", String.class
			).in(
				DSL.selectDistinct(
					DSL.field("Individual.id", String.class)
				).from(
					DSL.table(
						"BQIndividual"
					).as(
						"Individual"
					)
				).crossJoin(
					DSL.unnest(
						DSL.field("Individual.memberships", String[].class)
					).as(
						"IndividualMemberships"
					)
				).where(
					DSL.and(
						DSL.field(
							"IndividualMemberships.name"
						).eq(
							"userGroupIds"
						),
						DSL.val(
							"1234"
						).in(
							DSL.function(
								"unnest", String[].class,
								DSL.field("IndividualMemberships.ids"))
						))
				)
			),
			"userGroupIds eq '1234'");
	}

	@Test
	public void testIsMemberFilterExpressionNe() {
		_assertEquals(
			DSL.not(
				DSL.field(
					"Individual.id", String.class
				).in(
					DSL.selectDistinct(
						DSL.field("Individual.id", String.class)
					).from(
						DSL.table(
							"BQIndividual"
						).as(
							"Individual"
						)
					).crossJoin(
						DSL.unnest(
							DSL.field("Individual.memberships", String[].class)
						).as(
							"IndividualMemberships"
						)
					).where(
						DSL.and(
							DSL.field(
								"IndividualMemberships.name"
							).eq(
								"userGroupIds"
							),
							DSL.val(
								"1234"
							).in(
								DSL.function(
									"unnest", String[].class,
									DSL.field("IndividualMemberships.ids"))
							))
					)
				)),
			"userGroupIds ne '1234'");
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
				DSL.lower(
					DSL.field("column1", String.class)
				).like(
					"%value1%"
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
	public void testOrganizationFilterExpression() {
		_assertEquals(
			DSL.field(
				"Individual.id", String.class
			).in(
				DSL.selectDistinct(
					DSL.field("Individual.id", String.class)
				).from(
					DSL.table(
						"BQIndividual"
					).as(
						"Individual"
					)
				).crossJoin(
					DSL.unnest(
						DSL.field("Individual.memberships", String[].class)
					).as(
						"IndividualMemberships"
					)
				).where(
					DSL.field(
						"IndividualMemberships.name"
					).eq(
						"organizationIds"
					),
					DSL.val(
						"1234"
					).in(
						DSL.function(
							"unnest", String[].class,
							DSL.field("IndividualMemberships.ids"))
					)
				)
			),
			"organizations.filter(filter='(id eq ''1234'')')");

		_assertEquals(
			DSL.field(
				"Individual.id", String.class
			).in(
				DSL.selectDistinct(
					DSL.field("Individual.id", String.class)
				).from(
					DSL.table(
						"BQIndividual"
					).as(
						"Individual"
					)
				).crossJoin(
					DSL.unnest(
						DSL.field("Individual.memberships", String[].class)
					).as(
						"IndividualMemberships"
					)
				).join(
					DSL.table(
						"BQOrganization"
					).as(
						"Organization"
					)
				).on(
					DSL.field(
						"Organization.id"
					).in(
						DSL.function(
							"unnest", String[].class,
							DSL.field("IndividualMemberships.ids"))
					)
				).join(
					DSL.table(
						"BQOrganization"
					).as(
						"ParentOrganization"
					)
				).on(
					DSL.and(
						DSL.field(
							"Organization.parentOrganizationId"
						).eq(
							DSL.field("ParentOrganization.organizationId")
						),
						DSL.field(
							"Organization.dataSourceId"
						).eq(
							DSL.field("ParentOrganization.dataSourceId")
						))
				).where(
					DSL.field(
						"ParentOrganization.id"
					).eq(
						"1234"
					)
				)
			),
			"organizations.filter(filter='(parentId eq ''1234'')')");

		_assertEquals(
			DSL.field(
				"Individual.id", String.class
			).in(
				DSL.selectDistinct(
					DSL.field("Individual.id", String.class)
				).from(
					DSL.table(
						"BQIndividual"
					).as(
						"Individual"
					)
				).crossJoin(
					DSL.unnest(
						DSL.field("Individual.memberships", String[].class)
					).as(
						"IndividualMemberships"
					)
				).join(
					DSL.table(
						"BQOrganization"
					).as(
						"Organization"
					)
				).on(
					DSL.field(
						"Organization.id"
					).in(
						DSL.function(
							"unnest", String[].class,
							DSL.field("IndividualMemberships.ids"))
					)
				).where(
					DSL.field(
						"Organization.type"
					).eq(
						"org"
					)
				)
			),
			"organizations.filter(filter='(type eq ''org'')')");

		_assertEquals(
			DSL.field(
				"Individual.id", String.class
			).in(
				DSL.selectDistinct(
					DSL.field("Individual.id", String.class)
				).from(
					DSL.table(
						"BQIndividual"
					).as(
						"Individual"
					)
				).crossJoin(
					DSL.unnest(
						DSL.field("Individual.memberships", String[].class)
					).as(
						"IndividualMemberships"
					)
				).join(
					DSL.table(
						"BQOrganization"
					).as(
						"Organization"
					)
				).on(
					DSL.field(
						"Organization.id"
					).in(
						DSL.function(
							"unnest", String[].class,
							DSL.field("IndividualMemberships.ids"))
					)
				).where(
					DSL.field(
						"Organization.type"
					).isNull()
				)
			),
			"organizations.filter(filter='(type eq null)')");

		_assertEquals(
			DSL.field(
				"Individual.id", String.class
			).in(
				DSL.selectDistinct(
					DSL.field("Individual.id", String.class)
				).from(
					DSL.table(
						"BQIndividual"
					).as(
						"Individual"
					)
				).crossJoin(
					DSL.unnest(
						DSL.field("Individual.memberships", String[].class)
					).as(
						"IndividualMemberships"
					)
				).join(
					DSL.table(
						"BQOrganization"
					).as(
						"Organization"
					)
				).on(
					DSL.field(
						"Organization.id"
					).in(
						DSL.function(
							"unnest", String[].class,
							DSL.field("IndividualMemberships.ids"))
					)
				).where(
					DSL.field(
						"Organization.type"
					).ne(
						"org"
					)
				)
			),
			"organizations.filter(filter='(type ne ''org'')')");

		_assertEquals(
			DSL.field(
				"Individual.id", String.class
			).in(
				DSL.selectDistinct(
					DSL.field("Individual.id", String.class)
				).from(
					DSL.table(
						"BQIndividual"
					).as(
						"Individual"
					)
				).crossJoin(
					DSL.unnest(
						DSL.field("Individual.memberships", String[].class)
					).as(
						"IndividualMemberships"
					)
				).join(
					DSL.table(
						"BQOrganization"
					).as(
						"Organization"
					)
				).on(
					DSL.field(
						"Organization.id"
					).in(
						DSL.function(
							"unnest", String[].class,
							DSL.field("IndividualMemberships.ids"))
					)
				).where(
					DSL.field(
						"Organization.type"
					).isNotNull()
				)
			),
			"organizations.filter(filter='(type ne null)')");

		_assertEquals(
			DSL.field(
				"Individual.id", String.class
			).in(
				DSL.selectDistinct(
					DSL.field("Individual.id", String.class)
				).from(
					DSL.table(
						"BQIndividual"
					).as(
						"Individual"
					)
				).crossJoin(
					DSL.unnest(
						DSL.field("Individual.memberships", String[].class)
					).as(
						"IndividualMemberships"
					)
				).join(
					DSL.table(
						"BQOrganization"
					).as(
						"Organization"
					)
				).on(
					DSL.field(
						"Organization.id"
					).in(
						DSL.function(
							"unnest", String[].class,
							DSL.field("IndividualMemberships.ids"))
					)
				).where(
					DSL.lower(
						DSL.field("Organization.hierarchyPath", String.class)
					).like(
						"%test%"
					)
				)
			),
			"organizations.filter(filter='(contains(hierarchyPath, ''test''))" +
				"')");
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

	@Disabled
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

		FilterExpression filterExpression = new FilterExpression(
			actualFilterExpressionString);

		Assertions.assertEquals(
			expectedCondition, filterExpression.getCondition());
	}

	private void _assertEquals(
		Condition expectedCondition, String actualFilterExpressionString,
		Set<String> includedTableNames) {

		FilterExpression filterExpression = new FilterExpression(
			actualFilterExpressionString);

		Assertions.assertEquals(
			expectedCondition, filterExpression.getCondition());

		Assertions.assertEquals(
			includedTableNames, filterExpression.getReferencedTableNames());
	}

	private void _assertThrowsException(
		String filterExpressionString, String message) {

		Assertions.assertThrows(
			FilterExpressionParserException.class,
			() -> new FilterExpression(filterExpressionString), message);
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

	private final Map<String, String> _testFilters = _getTestFilters();

}