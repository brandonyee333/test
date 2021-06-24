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

package com.liferay.osb.asah.backend.repository.impl;

import com.liferay.osb.asah.backend.model.HistogramMetric;
import com.liferay.osb.asah.backend.model.Metric;
import com.liferay.osb.asah.backend.repository.AssetMetricRepository;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.date.dog.TimeZoneDog;
import com.liferay.osb.asah.common.model.Interval;
import com.liferay.osb.asah.common.model.MetricType;
import com.liferay.osb.asah.common.model.TimeRange;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;

import java.math.BigDecimal;

import java.time.OffsetDateTime;

import java.util.List;

import org.jooq.DSLContext;
import org.jooq.DatePart;
import org.jooq.Field;
import org.jooq.impl.DSL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * @author Alejo Ceballos
 * @author Marcos Martins
 */
public abstract class BaseAssetMetricRepository
	implements AssetMetricRepository {

	@Override
	public List<HistogramMetric> getHistogramMetrics(
		String assetId, Long channelId, Interval interval,
		MetricType metricType, TimeRange timeRange) {

		Field<OffsetDateTime> eventDateField = DSL.field(
			"at_timezone({0}, {1})", OffsetDateTime.class,
			DSL.field("eventDate"), DSL.inline(_timeZoneDog.getTimeZoneId()));

		if (interval != Interval.HOUR) {
			eventDateField = DSL.trunc(eventDateField, DatePart.DAY);
		}

		return dslContext.select(
			eventDateField, getMetricField(metricType)
		).from(
			getTableName()
		).where(
			DSL.and(
				DSL.field(
					getAssetIdFieldName()
				).eq(
					assetId
				),
				DSL.field(
					"channelId"
				).eq(
					channelId
				),
				DSL.field(
					"eventDate"
				).between(
					DateUtil.toDate(
						timeRange.getStartLocalDateTime(),
						_timeZoneDog.getZoneId()),
					DateUtil.toDate(
						timeRange.getEndLocalDateTime(),
						_timeZoneDog.getZoneId())
				),
				DSL.field(
					"projectId"
				).eq(
					ProjectIdThreadLocal.getProjectId()
				))
		).groupBy(
			eventDateField
		).fetch(
		).map(
			record -> {
				OffsetDateTime offsetDateTime = record.value1();

				Metric metric = new Metric(metricType);

				BigDecimal bigDecimal = record.value2();

				metric.setValue(bigDecimal.doubleValue());

				return new HistogramMetric(
					String.valueOf(offsetDateTime.toLocalDateTime()), metric);
			}
		);
	}

	protected String getAssetIdFieldName() {
		return "assetId";
	}

	protected abstract Field<BigDecimal> getMetricField(MetricType metricType);

	protected abstract String getTableName();

	@Autowired
	@Qualifier("trinoDSLContext")
	protected DSLContext dslContext;

	@Autowired
	private TimeZoneDog _timeZoneDog;

}