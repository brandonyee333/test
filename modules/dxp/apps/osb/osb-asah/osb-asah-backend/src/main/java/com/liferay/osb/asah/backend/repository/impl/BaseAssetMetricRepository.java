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
import com.liferay.osb.asah.common.repository.executor.QueryExecutor;
import com.liferay.osb.asah.common.repository.helper.DSLHelper;

import java.math.BigDecimal;

import java.time.ZoneId;
import java.time.ZoneOffset;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.DatePart;
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record2;
import org.jooq.SortField;
import org.jooq.WindowOverStep;
import org.jooq.impl.DSL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;

/**
 * @author Alejo Ceballos
 * @author Marcos Martins
 */
public abstract class BaseAssetMetricRepository<T extends AssetMetric>
	implements AssetMetricRepository<T> {

	@Override
	public T getAssetMetric(
		String assetId, @Nullable String assetTitle, Long channelId,
		Set<String> selectedMetrics, TimeRange timeRange) {

		Field<Boolean> previousField = DSL.when(
			DSL.field(
				"eventDate"
			).gt(
				_dslHelper.getDateParam(
					timeRange.getStartLocalDateTime(),
					String.valueOf(_timeZoneDog.getZoneId()))
			),
			false
		).otherwise(
			true
		).as(
			"previous"
		);

		List<Map<String, Object>> recordMaps = _queryExecutor.queryForList(
			Function.identity(),
			dslContext.select(
				_getMetricFields(selectedMetrics)
			).select(
				previousField
			).from(
				getTableName(timeRange)
			).where(
				_createWhereClause(
					assetId, assetTitle, channelId,
					timeRange.getIncludePreviousTimeRange())
			).groupBy(
				previousField
			));

		return _toMetric(assetId, assetTitle, recordMaps, selectedMetrics);
	}

	@Override
	public List<T> getAssetMetrics(
		Long channelId, @Nullable String keywords, Pageable pageable,
		Set<String> selectedMetrics, TimeRange timeRange) {

		List<Field<? extends Object>> fields = new ArrayList<>(
			_getMetricFields(selectedMetrics));

		Field<String> assetIdField = DSL.field("assetId", String.class);
		Field<String> assetTitleField = DSL.field("assetTitle", String.class);

		fields.add(assetIdField);

		fields.add(assetTitleField);

		return _queryExecutor.queryForList(
			rowMap -> _toMetric(rowMap, selectedMetrics),
			dslContext.select(
				fields
			).from(
				getTableName(timeRange)
			).where(
				_createWhereClause(null, null, channelId, keywords, timeRange)
			).groupBy(
				assetIdField, assetTitleField
			).orderBy(
				_getSortFields(pageable.getSort())
			).limit(
				pageable.getPageSize()
			).offset(
				pageable.getOffset()
			));
	}

	@Override
	public Long getAssetMetricsCount(
		Long channelId, @Nullable String keywords, TimeRange timeRange) {

		return _queryExecutor.queryForLong(
			dslContext.select(
				DSL.countDistinct(DSL.field("assetId"), DSL.field("assetTitle"))
			).from(
				getTableName(timeRange)
			).where(
				_createWhereClause(null, null, channelId, keywords, timeRange)
			));
	}

	@Override
	public List<Metric> getBrowserMetrics(
		String assetId, Long channelId, MetricType metricType,
		TimeRange timeRange) {

		Field<String> browserNameField = DSL.field("browserName", String.class);
		Field<BigDecimal> metricField = getMetricField(metricType);

		return dslContext.select(
			browserNameField, metricField
		).from(
			getTableName(timeRange)
		).where(
			_createWhereClause(assetId, null, channelId, timeRange)
		).groupBy(
			browserNameField
		).orderBy(
			metricField.desc()
		).fetch(
		).map(
			record -> _getMetric(metricType, record)
		);
	}

	@Override
	public List<String> getCanonicalUrls(
		String assetId, Long channelId, Pageable pageable,
		TimeRange timeRange) {

		List<Field<? extends Object>> fields = new ArrayList<>();

		Field<String> canonicalUrlField = DSL.field(
			"canonicalUrl", String.class);

		fields.add(canonicalUrlField);

		WindowOverStep<Integer> rowNumber = DSL.rowNumber();

		Field<Integer> rowNumberField = rowNumber.over();

		fields.add(rowNumberField.as("rowNumber"));

		Long offset = pageable.getOffset();

		return dslContext.select(
			canonicalUrlField
		).from(
			dslContext.select(
				fields
			).from(
				getTableName(timeRange)
			).where(
				_createWhereClause(assetId, null, channelId, timeRange)
			).groupBy(
				canonicalUrlField
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
			Record1::value1
		);
	}

	@Override
	public List<Metric> getDeviceMetrics(
		String assetId, Long channelId, MetricType metricType,
		TimeRange timeRange) {

		Field<String> deviceTypeField = DSL.field("deviceType", String.class);
		Field<BigDecimal> metricField = getMetricField(metricType);
		Field<String> platformNameField = DSL.field(
			"platformName", String.class);

		Map<String, Metric> metrics = new LinkedHashMap<>();

		dslContext.select(
			deviceTypeField, metricField, platformNameField
		).from(
			getTableName(timeRange)
		).where(
			_createWhereClause(assetId, null, channelId, timeRange)
		).groupBy(
			deviceTypeField, platformNameField
		).orderBy(
			deviceTypeField, metricField.desc()
		).fetch(
		).forEach(
			record -> {
				Metric deviceTypeMetric = new Metric(metricType);

				String deviceType = record.value1();

				deviceTypeMetric.setValueKey(deviceType);

				metrics.putIfAbsent(deviceType, deviceTypeMetric);

				deviceTypeMetric = metrics.get(deviceType);

				Metric platformNameMetric = new Metric(metricType);

				platformNameMetric.setValueKey(record.value3());

				BigDecimal valueBigDecimal = record.value2();

				platformNameMetric.setValue(valueBigDecimal.doubleValue());

				deviceTypeMetric.addMetric(platformNameMetric);

				deviceTypeMetric.setValue(
					deviceTypeMetric.getValue() +
						valueBigDecimal.doubleValue());
			}
		);

		return new ArrayList<>(metrics.values());
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
			getTableName(timeRange)
		).where(
			_createWhereClause(assetId, null, channelId, timeRange)
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
		String assetId, @Nullable String assetTitle, Long channelId,
		Interval interval, MetricType metricType, TimeRange timeRange) {

		Field field = DSL.timestamp(
			_dslHelper.dateTrunc(
				DatePart.valueOf(interval.name()),
				_dslHelper.getDateAtTimeZoneField(
					"eventdate", String.valueOf(_timeZoneDog.getZoneId()))));

		field = field.as("key");

		return _queryExecutor.queryForList(
			rowMap -> {
				Metric metric = new Metric(metricType);

				BigDecimal bigDecimal = (BigDecimal)rowMap.get(
					metricType.getFieldName());

				metric.setValue(bigDecimal.doubleValue());

				return new HistogramMetric(
					String.valueOf(
						DateUtil.toLocalDateTime(
							(Date)rowMap.get("key"), ZoneOffset.UTC)),
					metric);
			},
			dslContext.select(
				field, getMetricField(metricType)
			).from(
				getTableName(timeRange)
			).where(
				_createWhereClause(assetId, assetTitle, channelId, timeRange)
			).groupBy(
				field
			));
	}

	@Override
	public Long getIndividualsCount(
		String assetId, Long channelId, Boolean knownIndividual,
		MetricType metricType, TimeRange timeRange) {

		Condition whereClauseCondition = _createWhereClause(
			assetId, null, channelId, timeRange);

		if (knownIndividual != null) {
			whereClauseCondition = whereClauseCondition.and(
				DSL.field(
					"knownIndividual"
				).eq(
					knownIndividual
				));
		}

		return _getIndividualsCount(
			metricType, timeRange, whereClauseCondition);
	}

	@Override
	public Long getNonsegmentedIndividualsCount(
		String assetId, Long channelId, MetricType metricType,
		TimeRange timeRange) {

		Condition whereClauseCondition = _createWhereClause(
			assetId, null, channelId, timeRange);

		Field<String[]> segmentNamesField = DSL.field(
			"segmentNames", String[].class);

		whereClauseCondition = whereClauseCondition.and(
			DSL.or(
				DSL.cardinality(
					segmentNamesField
				).eq(
					0
				),
				segmentNamesField.isNull()));

		return _getIndividualsCount(
			metricType, timeRange, whereClauseCondition);
	}

	@Override
	public Long getSegmentedIndividualsCount(
		String assetId, Long channelId, MetricType metricType,
		TimeRange timeRange) {

		Condition whereClauseCondition = _createWhereClause(
			assetId, null, channelId, timeRange);

		Field<String[]> segmentNamesField = DSL.field(
			"segmentNames", String[].class);

		whereClauseCondition = whereClauseCondition.and(
			DSL.cardinality(
				segmentNamesField
			).gt(
				0
			));

		return _getIndividualsCount(
			metricType, timeRange, whereClauseCondition);
	}

	@Override
	public List<Metric> getSegmentMetrics(
		String assetId, Long channelId, MetricType metricType,
		TimeRange timeRange) {

		Field<String[]> segmentNamesField = DSL.field(
			"segmentNames", String[].class);
		Field<String> segmentNameField = DSL.field("segmentName", String.class);

		return dslContext.select(
			segmentNameField, DSL.count()
		).from(
			dslContext.select(
				segmentNameField
			).from(
				getTableName(timeRange)
			).crossJoin(
				DSL.unnest(
					segmentNamesField
				).as(
					"t", "segmentName"
				)
			).where(
				_createWhereClause(assetId, null, channelId, timeRange)
			)
		).groupBy(
			segmentNameField
		).orderBy(
			DSL.count(
			).desc(),
			segmentNameField.asc()
		).fetch(
		).map(
			record -> {
				Metric metric = new Metric(metricType);

				metric.setValueKey(record.value1());
				metric.setValue((double)record.value2());

				return metric;
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

	protected abstract String getTableName(TimeRange timeRange);

	protected boolean isBigQueryDialect() {
		String googleApplicationCredentials = _environment.getProperty(
			"GOOGLE_APPLICATION_CREDENTIALS");

		if (googleApplicationCredentials != null) {
			return true;
		}

		return false;
	}

	@Autowired
	protected DSLContext dslContext;

	private Condition _createWhereClause(
		@Nullable String assetId, @Nullable String assetTitle, Long channelId,
		@Nullable String keywords, TimeRange timeRange) {

		ZoneId zoneId = _timeZoneDog.getZoneId();

		List<Condition> conditions = new ArrayList<>();

		if (assetId != null) {
			conditions.add(
				DSL.field(
					getAssetIdFieldName()
				).eq(
					assetId
				));
		}

		if (assetTitle != null) {
			conditions.add(
				DSL.field(
					"assetTitle"
				).eq(
					assetTitle
				));
		}

		conditions.add(
			DSL.field(
				"channelId"
			).eq(
				channelId
			));

		conditions.add(
			DSL.field(
				"eventDate"
			).between(
				_dslHelper.getDateParam(
					timeRange.getStartLocalDateTime(), zoneId.toString()),
				_dslHelper.getDateParam(
					timeRange.getEndLocalDateTime(), zoneId.toString())
			));

		if (StringUtils.isNotBlank(keywords)) {
			conditions.add(
				DSL.field(
					"assetTitle", String.class
				).containsIgnoreCase(
					keywords
				));
		}

		return DSL.and(conditions);
	}

	private Condition _createWhereClause(
		@Nullable String assetId, @Nullable String assetTitle, Long channelId,
		TimeRange timeRange) {

		return _createWhereClause(
			assetId, assetTitle, channelId, null, timeRange);
	}

	private long _getIndividualsCount(
		MetricType metricType, TimeRange timeRange,
		Condition whereClauseCondition) {

		return dslContext.select(
			DSL.countDistinct(DSL.field("individualId"))
		).from(
			getTableName(timeRange)
		).where(
			whereClauseCondition.and(
				DSL.field(
					metricType.getFieldName()
				).gt(
					0
				))
		).fetch(
		).get(
			0
		).value1(
		).longValue();
	}

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

	private Collection<SortField<?>> _getSortFields(Sort sort) {
		Collection<SortField<?>> sortFields = new ArrayList<>();

		for (Sort.Order order : sort.toList()) {
			String fieldName = order.getProperty();

			Field<?> field = DSL.field(fieldName);

			if (order.getDirection() == Sort.Direction.ASC) {
				SortField<?> sortField = field.asc();

				sortFields.add(sortField.nullsFirst());
			}
			else {
				SortField<?> sortField = field.desc();

				sortFields.add(sortField.nullsLast());
			}
		}

		return sortFields;
	}

	private T _toMetric(
		Map<String, Object> recordMap, Set<String> selectedMetrics) {

		T assetMetric = createAssetMetric();

		assetMetric.setAssetId((String)recordMap.get("assetId"));
		assetMetric.setAssetTitle((String)recordMap.get("assetTitle"));

		Map<String, BiConsumer<T, Metric>> assetMetricSetters =
			getAssetMetricSetters();

		for (String selectedMetric : selectedMetrics) {
			MetricType metricType = getMetricType(selectedMetric);

			Metric metric = new Metric(metricType);

			BigDecimal metricValueBigDecimal = (BigDecimal)recordMap.get(
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

	private T _toMetric(
		String assetId, String assetTitle, List<Map<String, Object>> recordMaps,
		Set<String> selectedMetrics) {

		T assetMetric = createAssetMetric();

		assetMetric.setAssetId(assetId);
		assetMetric.setAssetTitle(assetTitle);

		Map<String, BiConsumer<T, Metric>> assetMetricSetters =
			getAssetMetricSetters();

		Map<String, Metric> metrics = new HashMap<>();

		for (String selectedMetric : selectedMetrics) {
			for (Map<String, Object> recordMap : recordMaps) {
				Metric metric = metrics.computeIfAbsent(
					selectedMetric,
					metricTypeName -> {
						MetricType metricType = getMetricType(metricTypeName);

						return new Metric(metricType);
					});

				MetricType metricType = metric.getMetricType();

				BigDecimal metricValueBigDecimal = (BigDecimal)recordMap.get(
					metricType.getFieldName());

				if (metricValueBigDecimal != null) {
					boolean previous = (boolean)recordMap.get("previous");

					if (previous) {
						metric.setPreviousValue(
							metricValueBigDecimal.doubleValue());
					}
					else {
						metric.setValue(metricValueBigDecimal.doubleValue());
					}
				}
			}
		}

		for (Map.Entry<String, Metric> entry : metrics.entrySet()) {
			BiConsumer<T, Metric> metricSetterBiConsumer =
				assetMetricSetters.get(entry.getKey());

			metricSetterBiConsumer.accept(assetMetric, entry.getValue());
		}

		return assetMetric;
	}

	@Autowired
	private DSLHelper _dslHelper;

	@Autowired
	private Environment _environment;

	@Autowired
	private QueryExecutor _queryExecutor;

	@Autowired
	private TimeZoneDog _timeZoneDog;

}