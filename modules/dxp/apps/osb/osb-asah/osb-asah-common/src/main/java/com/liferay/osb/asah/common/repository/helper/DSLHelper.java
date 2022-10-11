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

package com.liferay.osb.asah.common.repository.helper;

import com.liferay.osb.asah.common.date.DateUtil;

import java.math.BigDecimal;
import java.math.BigInteger;

import java.sql.Timestamp;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;

import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.stream.Stream;

import org.jooq.Condition;
import org.jooq.DatePart;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Table;
import org.jooq.impl.DSL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
public class DSLHelper {

	public Field concat(Field... fields) {
		if (_isBigQueryDialect()) {
			StringBuffer stringBuffer = new StringBuffer("CONCAT(");

			Stream<Field> stream = Arrays.stream(fields);

			Iterator<Field> iterator = stream.iterator();

			while (iterator.hasNext()) {
				stringBuffer.append(String.valueOf(iterator.next()));

				if (iterator.hasNext()) {
					stringBuffer.append(",");
				}
			}

			stringBuffer.append(")");

			return DSL.field(stringBuffer.toString());
		}

		return DSL.concat(fields);
	}

	public Field<OffsetDateTime> dateTrunc(
		DatePart datePart, Field<OffsetDateTime> field) {

		if (_isBigQueryDialect()) {
			return DSL.field(
				"date_trunc({0}, {1})", field.getDataType(), field,
				datePart.toName());
		}

		if (datePart == DatePart.WEEK) {
			return DSL.field(
				"date_trunc({0}, {1} + INTERVAL '1 DAY') - INTERVAL '1 DAY'",
				field.getDataType(), datePart.toSQL(), field);
		}

		return DSL.field(
			"date_trunc({0}, {1})", field.getDataType(), datePart.toSQL(),
			field);
	}

	public Field getCastBooleanField(Field field) {
		if (_isBigQueryDialect()) {
			return DSL.field("SAFE_CAST({0} as BOOL)", field);
		}

		return DSL.function("try_cast_boolean", Boolean.class, field);
	}

	public Field getCastDurationField(Field field) {
		if (_isBigQueryDialect()) {
			return DSL.field("SAFE_CAST({0} as INT64)", field);
		}

		return DSL.round(
			DSL.abs(DSL.function("try_cast_bigint", BigInteger.class, field)),
			-3);
	}

	public Field getCastNumberField(Field field) {
		if (_isBigQueryDialect()) {
			return DSL.field("SAFE_CAST({0} as BIGNUMERIC)", field);
		}

		return DSL.round(
			DSL.function("try_cast_float", BigDecimal.class, field));
	}

	public Field getCastStringField(Field field) {
		if (_isBigQueryDialect()) {
			return DSL.field("SAFE_CAST({0} as STRING)", field);
		}

		return field.cast(String.class);
	}

	public Field getDateAtTimeZoneField(String fieldName, String timeZoneId) {
		if (_isBigQueryDialect()) {
			return DSL.field(
				String.format("DATETIME(%s, '%s')", fieldName, timeZoneId),
				OffsetDateTime.class);
		}

		if (timeZoneId.equals("UTC")) {
			return DSL.field(fieldName, OffsetDateTime.class);
		}

		return DSL.field(
			String.format("%s AT TIME ZONE '%s'", fieldName, timeZoneId),
			OffsetDateTime.class);
	}

	public Object getDateParam(Date date) {
		if (_isBigQueryDialect()) {
			return DateUtil.toUTCString(date);
		}

		return date;
	}

	public Object getDateParam(LocalDateTime localDateTime, String timeZoneId) {
		localDateTime = DateUtil.toUTCLocalDateTime(
			localDateTime, ZoneId.of(timeZoneId));

		if (_isBigQueryDialect()) {
			return DateUtil.toUTCString(localDateTime);
		}

		return localDateTime;
	}

	public Object getDateValue(Date date) {
		if (_isBigQueryDialect()) {
			return DSL.timestamp(date);
		}

		return DSL.date(date);
	}

	public Field<OffsetDateTime> getDateValueField(
		Field field, String timeZoneId) {

		if (_isBigQueryDialect()) {
			return DSL.field(
				"SAFE_CAST({0} as TIMESTAMP)", OffsetDateTime.class, field);
		}

		StringBuilder sb = new StringBuilder();

		sb.append(DSL.function("try_cast_timestamp", Object.class, field));
		sb.append(" AT TIME ZONE 'UTC'");

		if (!timeZoneId.equals("UTC")) {
			sb.append(" AT TIME ZONE '");
			sb.append(timeZoneId);
			sb.append("'");
		}

		return DSL.field(sb.toString(), OffsetDateTime.class);
	}

	public Field<String> getNestedField(
		String keyAttribute, String nestedField, String valueAttribute,
		String wrapperColumn) {

		if (_isBigQueryDialect()) {
			return DSL.field(
				String.format(
					"(SELECT %s FROM UNNEST(%s) WHERE %s = {0})",
					valueAttribute, wrapperColumn, keyAttribute),
				String.class, nestedField);
		}

		String sql =
			"(SELECT %s FROM JSON_TO_RECORDSET(%s) AS (%s TEXT, %s TEXT) " +
				"WHERE %s = {0})";

		return DSL.field(
			String.format(
				sql, valueAttribute, wrapperColumn, keyAttribute,
				valueAttribute, keyAttribute),
			String.class, nestedField);
	}

	public Table<Record> getTimeSeriesTable(
		DatePart datePart, Timestamp timestamp1, Timestamp timestamp2) {

		if (_isBigQueryDialect()) {
			return DSL.table(
				String.format(
					"UNNEST(GENERATE_DATE_ARRAY({1}, {2}, INTERVAL 1 %s)) AS " +
						"generatedDate",
					datePart.toSQL()),
				timestamp1, timestamp2);
		}

		return DSL.table(
			String.format(
				"generate_series({0}, {1}, '1 %s'::interval) AS generatedDate",
				datePart.toSQL()),
			timestamp1, timestamp2);
	}

	public Field<Object> jsonExtractScalar(String fieldName, String key) {
		if (_isBigQueryDialect()) {
			return DSL.field(
				String.format(
					"JSON_EXTRACT_SCALAR(%s, '$.%s')", fieldName, key));
		}

		return DSL.field(
			String.format(
				"JSON_EXTRACT_PATH_TEXT(%s::json, '%s')", fieldName, key));
	}

	public Condition likeRegex(String fieldName, String regexp) {
		if (_isBigQueryDialect()) {
			return DSL.condition(
				String.format("REGEXP_CONTAINS(%s, r'%s')", fieldName, regexp));
		}

		return DSL.condition(
			String.format("%s ~ '^.*%s.*$'", fieldName, regexp));
	}

	public Field<String> regexpExtract(Field<String> field, String regexp) {
		if (_isBigQueryDialect()) {
			return DSL.field(
				String.format("REGEXP_EXTRACT(%s, r'%s')", field, regexp),
				String.class);
		}

		return DSL.field(
			String.format("(REGEXP_MATCH(%s, '%s'))[1]", field, regexp),
			String.class);
	}

	public Field<String> regexpReplace(
		String fieldName, String regexp, String value) {

		if (_isBigQueryDialect()) {
			return DSL.field(
				String.format(
					"REGEXP_REPLACE(%s, r'%s', '%s')", fieldName, regexp,
					value),
				String.class);
		}

		return DSL.field(
			String.format(
				"REGEXP_REPLACE(%s, '%s', '%s')", fieldName, regexp, value),
			String.class);
	}

	private boolean _isBigQueryDialect() {
		String googleApplicationCredentials = _environment.getProperty(
			"GOOGLE_APPLICATION_CREDENTIALS");

		if (googleApplicationCredentials != null) {
			return true;
		}

		return false;
	}

	@Autowired
	private Environment _environment;

}