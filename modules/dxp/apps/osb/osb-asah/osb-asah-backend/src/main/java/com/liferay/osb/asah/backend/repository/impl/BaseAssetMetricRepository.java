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

import com.liferay.osb.asah.backend.model.AssetMetric;
import com.liferay.osb.asah.backend.model.HistogramMetric;
import com.liferay.osb.asah.backend.model.Metric;
import com.liferay.osb.asah.backend.repository.AssetMetricRepository;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.date.dog.TimeZoneDog;
import com.liferay.osb.asah.common.model.Interval;
import com.liferay.osb.asah.common.model.MetricType;
import com.liferay.osb.asah.common.model.TimeRange;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;

import java.lang.reflect.Method;

import java.math.BigDecimal;

import java.time.OffsetDateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jooq.DSLContext;
import org.jooq.DatePart;
import org.jooq.Field;
import org.jooq.Record;
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
	public AssetMetric getAssetMetric(
		String assetId, Long channelId, Set<String> selectedMetrics,
		TimeRange timeRange) {

		List<Field<? extends Object>> fields = new ArrayList<>();

		fields.addAll(
			Stream.of(
				getMetricTypes()
			).filter(
				assetMetricType -> selectedMetrics.contains(
					assetMetricType.getName())
			).map(
				this::getMetricField
			).collect(
				Collectors.toList()
			));

		List<AssetMetric> assetMetrics = dslContext.select(
			fields
		).from(
			getTableName()
		).where(
			DSL.and(
				DSL.field(
					"assetId"
				).eq(
					assetId
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
					DateUtil.toDate(
						timeRange.getStartLocalDateTime(),
						_timeZoneDog.getZoneId()),
					DateUtil.toDate(
						timeRange.getEndLocalDateTime(),
						_timeZoneDog.getZoneId())
				))
		).fetch(
		).map(
			record -> _toMetric(record, selectedMetrics)
		);

		return assetMetrics.get(0);
	}

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

	protected abstract AssetMetric createAssetMetric();

	protected String getAssetIdFieldName() {
		return "assetId";
	}

	protected abstract Field<BigDecimal> getMetricField(MetricType metricType);

	protected abstract Map<String, String> getMetricSetterMethodNames();

	protected abstract MetricType getMetricType(String metricTypeName);

	protected abstract MetricType[] getMetricTypes();

	protected abstract String getTableName();

	@Autowired
	@Qualifier("trinoDSLContext")
	protected DSLContext dslContext;

	private AssetMetric _toMetric(Record record, Set<String> selectedMetrics) {
		AssetMetric assetMetric = createAssetMetric();

		Map<String, String> metricSetterMethodNames =
			getMetricSetterMethodNames();

		selectedMetrics.forEach(
			selectedMetric -> {
				String methodName = metricSetterMethodNames.get(selectedMetric);

				try {
					Class<? extends AssetMetric> assetMetricClass =
						assetMetric.getClass();

					Method method = assetMetricClass.getDeclaredMethod(
						methodName, Metric.class);

					MetricType metricType = getMetricType(selectedMetric);

					Metric metric = new Metric(metricType);

					BigDecimal metricValueBigDecimal = (BigDecimal)record.get(
						metricType.getFieldName());

					metric.setValue(metricValueBigDecimal.doubleValue());

					method.invoke(assetMetric, metric);
				}
				catch (Exception exception) {
					throw new RuntimeException(exception);
				}
			});

		return assetMetric;
	}

	@Autowired
	private TimeZoneDog _timeZoneDog;

}