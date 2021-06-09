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

package com.liferay.osb.asah.common.repository.impl;

import com.liferay.osb.asah.common.date.dog.TimeZoneDog;
import com.liferay.osb.asah.common.model.CustomAssetMetricType;
import com.liferay.osb.asah.common.model.Interval;
import com.liferay.osb.asah.common.model.TimeRange;
import com.liferay.osb.asah.common.model.Tuple2;
import com.liferay.osb.asah.common.repository.CustomAssetMetricRepository;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;

import java.math.BigDecimal;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

import java.util.List;

import org.jooq.DSLContext;
import org.jooq.DatePart;
import org.jooq.Field;
import org.jooq.impl.DSL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

/**
 * @author Marcellus Tavares
 */
@ConditionalOnProperty(havingValue = "true", value = "osb.asah.trino.enabled")
@Repository
public class CustomAssetMetricRepositoryImpl
	implements CustomAssetMetricRepository {

	@Override
	public List<Tuple2<LocalDateTime, BigDecimal>> getHistogramMetrics(
		Long channelId, CustomAssetMetricType customAssetMetricType,
		String customAssetPrimaryKey, Interval interval, TimeRange timeRange) {

		Field<OffsetDateTime> eventDateField = DSL.field(
			"at_timezone({0}, {1})", OffsetDateTime.class,
			DSL.field("eventDate"), DSL.inline(_timeZoneDog.getTimeZoneId()));

		if (interval != Interval.HOUR) {
			eventDateField = DSL.trunc(eventDateField, DatePart.DAY);
		}

		return _dslContext.select(
			eventDateField, _getMetricField(customAssetMetricType)
		).from(
			"hive.default.CustomAsset"
		).where(
			DSL.and(
				DSL.field(
					"assetPrimaryKey"
				).eq(
					customAssetPrimaryKey
				),
				DSL.field(
					"channelId"
				).eq(
					channelId
				),
				DSL.field(
					"projectId"
				).eq(
					ProjectIdThreadLocal.getProjectId()
				),
				DSL.field(
					"eventDate"
				).between(
					timeRange.getStartLocalDateTime(),
					timeRange.getEndLocalDateTime()
				))
		).groupBy(
			eventDateField
		).fetch(
		).map(
			record2 -> {
				OffsetDateTime offsetDateTime = record2.value1();

				return new Tuple2<>(
					offsetDateTime.toLocalDateTime(), record2.value2());
			}
		);
	}

	private Field<BigDecimal> _getMetricField(
		CustomAssetMetricType customAssetMetricType) {

		if (customAssetMetricType == CustomAssetMetricType.ABANDONMENTS) {
			return DSL.sum(
				DSL.field(
					CustomAssetMetricType.ABANDONMENTS.getFieldName(),
					Long.class)
			).div(
				DSL.sum(
					DSL.field(
						CustomAssetMetricType.VIEWS.getFieldName(), Long.class))
			).as(
				CustomAssetMetricType.ABANDONMENTS.getFieldName()
			);
		}

		Field<Long> longField = DSL.field(
			customAssetMetricType.getFieldName(), Long.class);

		if ((customAssetMetricType == CustomAssetMetricType.CLICKS) ||
			(customAssetMetricType == CustomAssetMetricType.DOWNLOADS) ||
			(customAssetMetricType == CustomAssetMetricType.SUBMISSIONS) ||
			(customAssetMetricType == CustomAssetMetricType.VIEWS)) {

			return DSL.sum(
				longField
			).as(
				longField.getName()
			);
		}

		return DSL.avg(
			longField
		).as(
			longField.getName()
		);
	}

	@Autowired
	@Qualifier("trinoDSLContext")
	private DSLContext _dslContext;

	@Autowired
	private TimeZoneDog _timeZoneDog;

}