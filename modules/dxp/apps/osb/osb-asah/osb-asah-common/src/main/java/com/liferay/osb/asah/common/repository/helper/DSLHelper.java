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

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;

import java.util.Date;

import org.jooq.DatePart;
import org.jooq.Field;
import org.jooq.impl.DSL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
public class DSLHelper {

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

	public Field<OffsetDateTime> getDateValueField(
		Field field, String timeZoneId) {

		if (_isBigQueryDialect()) {
			return DSL.field(
				"SAFE_CAST({0} as DATE)", OffsetDateTime.class, field);
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