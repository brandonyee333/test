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

import java.math.BigDecimal;

import java.time.OffsetDateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jooq.DSLContext;
import org.jooq.DatePart;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Record2;
import org.jooq.WindowOverStep;
import org.jooq.impl.DSL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;

/**
 * @author Alejo Ceballos
 * @author Marcos Martins
 */
public abstract class BaseAssetMetricRepository<T extends AssetMetric>
	implements AssetMetricRepository<T> {

	@Override
	public T getAssetMetric(
		String assetId, Long channelId, Set<String> selectedMetrics,
		TimeRange timeRange) {

		Record record = dslContext.select(
			_getMetricFields(selectedMetrics)
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
					DateUtil.toUTCLocalDateTime(
						timeRange.getStartLocalDateTime(),
						_timeZoneDog.getZoneId()),
					DateUtil.toUTCLocalDateTime(
						timeRange.getEndLocalDateTime(),
						_timeZoneDog.getZoneId())
				),
				DSL.field(
					"projectId"
				).eq(
					ProjectIdThreadLocal.getProjectId()
				))
		).fetchOne();

		return _toMetric(record, selectedMetrics);
	}

	@Override
	public List<T> getAssetMetrics(
		Set<String> assetIds, Long channelId, Set<String> selectedMetrics,
		Pageable pageable, TimeRange timeRange) {

		List<Field<? extends Object>> fields = new ArrayList<>(
			_getMetricFields(selectedMetrics));

		Field<String> assetIdField = DSL.field("assetId", String.class);

		fields.add(assetIdField);

		WindowOverStep<Integer> rowNumber = DSL.rowNumber();

		Field<Integer> rowNumberField = rowNumber.over();

		fields.add(rowNumberField.as("rowNumber"));

		Long offset = Long.valueOf(pageable.getOffset());

		return dslContext.select(
		).from(
			dslContext.select(
				fields
			).from(
				getTableName()
			).where(
				DSL.and(
					assetIdField.in(assetIds),
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
						DateUtil.toUTCLocalDateTime(
							timeRange.getStartLocalDateTime(),
							_timeZoneDog.getZoneId()),
						DateUtil.toUTCLocalDateTime(
							timeRange.getEndLocalDateTime(),
							_timeZoneDog.getZoneId())
					))
			).groupBy(
				assetIdField
			)
		).where(
			DSL.field(
				"rowNumber"
			).ge(
				offset.intValue()
			)
		).limit(
			pageable.getPageSize()
		).fetch(
		).map(
			record -> _toMetric(record, selectedMetrics)
		);
	}

	@Override
	public List<Metric> getGeolocationMetrics(
		String assetId, Long channelId, MetricType metricType,
		TimeRange timeRange) {

		Field<String> countryField = DSL.field("country", String.class);
		Field<BigDecimal> metricField = getMetricField(metricType);

		return dslContext.select(
			countryField, metricField
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
					DateUtil.toUTCLocalDateTime(
						timeRange.getStartLocalDateTime(),
						_timeZoneDog.getZoneId()),
					DateUtil.toUTCLocalDateTime(
						timeRange.getEndLocalDateTime(),
						_timeZoneDog.getZoneId())
				))
		).groupBy(
			countryField
		).orderBy(
			metricField.desc()
		).fetch(
		).map(
			record -> _getMetric(metricType, record)
		);
	}

	@Override
	public List<HistogramMetric> getHistogramMetrics(
		String assetId, Long channelId, Interval interval,
		MetricType metricType, TimeRange timeRange) {

		Field<OffsetDateTime> eventDateField = DSL.field(
			"eventDate", OffsetDateTime.class);

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
					DateUtil.toUTCLocalDateTime(
						timeRange.getStartLocalDateTime(),
						_timeZoneDog.getZoneId()),
					DateUtil.toUTCLocalDateTime(
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
					String.valueOf(
						DateUtil.toUTCLocalDateTime(
							offsetDateTime.toLocalDateTime(),
							_timeZoneDog.getZoneId())),
					metric);
			}
		);
	}

	protected abstract T createAssetMetric();

	protected String getAssetIdFieldName() {
		return "assetId";
	}

	protected abstract Map<String, BiConsumer<T, Metric>>
		getAssetMetricSetters();

	protected abstract Field<BigDecimal> getMetricField(MetricType metricType);

	protected abstract MetricType getMetricType(String metricTypeName);

	protected abstract MetricType[] getMetricTypes();

	protected abstract String getTableName();

	@Autowired
	@Qualifier("trinoDSLContext")
	protected DSLContext dslContext;

	private Metric _getMetric(
		MetricType metricType, Record2<String, BigDecimal> record) {

		Metric metric = new Metric(metricType);

		BigDecimal bigDecimal = record.value2();

		metric.setValue(bigDecimal.doubleValue());

		metric.setValueKey(record.value1());

		return metric;
	}

	private List<Field<BigDecimal>> _getMetricFields(Set<String> metricNames) {
		return Stream.of(
			getMetricTypes()
		).filter(
			assetMetricType -> metricNames.contains(assetMetricType.getName())
		).map(
			this::getMetricField
		).collect(
			Collectors.toList()
		);
	}

	private T _toMetric(Record record, Set<String> selectedMetrics) {
		T assetMetric = createAssetMetric();

		Map<String, BiConsumer<T, Metric>> assetMetricSetters =
			getAssetMetricSetters();

		for (String selectedMetric : selectedMetrics) {
			MetricType metricType = getMetricType(selectedMetric);

			Metric metric = new Metric(metricType);

			BigDecimal metricValueBigDecimal = (BigDecimal)record.get(
				metricType.getFieldName());

			if (metricValueBigDecimal != null) {
				metric.setValue(metricValueBigDecimal.doubleValue());
			}

			BiConsumer<T, Metric> metricSetterBiConsumer =
				assetMetricSetters.get(selectedMetric);

			metricSetterBiConsumer.accept(assetMetric, metric);
		}

		return assetMetric;
	}

	@Autowired
	private TimeZoneDog _timeZoneDog;

}