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
import com.liferay.osb.asah.backend.model.Individual;
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

import javax.validation.constraints.Null;

import org.apache.commons.lang3.StringUtils;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.DatePart;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.SortField;
import org.jooq.Table;
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
	public Long getAnonymousIndividualsCount(
		String assetId, @Nullable String assetTitle, Long channelId,
		MetricType metricType, TimeRange timeRange) {

		Condition whereClauseCondition = _createWhereClause(
			assetId, assetTitle, channelId, timeRange);

		return _queryExecutor.queryForLong(
			dslContext.select(
				DSL.countDistinct(DSL.field("metric.userId"))
			).from(
				DSL.table(
					getTableName(timeRange)
				).as(
					"metric"
				)
			).join(
				DSL.table(
					"BQIdentity"
				).as(
					"identity"
				)
			).on(
				DSL.field(
					"identity.userid"
				).eq(
					DSL.field("metric.userId")
				)
			).where(
				DSL.and(
					whereClauseCondition,
					DSL.field(
						metricType.getFieldName()
					).gt(
						0
					),
					DSL.field(
						"identity.individualId"
					).isNull())
			));
	}

	@Override
	public List<T> getAppearsOnMetrics(
		String assetId, @Nullable String assetTitle, Long channelId,
		MetricType metricType, TimeRange timeRange) {

		Field<String> canonicalUrlField = DSL.field(
			"canonicalUrl", String.class);
		Field<BigDecimal> metricField = getMetricField(metricType);
		Field<String> pageTitleField = DSL.field("pageTitle", String.class);

		Map<String, BiConsumer<T, Metric>> assetMetricSetters =
			getAssetMetricSetters();

		return _queryExecutor.queryForList(
			recordMap -> {
				T assetMetric = createAssetMetric();

				assetMetric.setAssetId((String)recordMap.get("canonicalUrl"));
				assetMetric.setAssetTitle((String)recordMap.get("pageTitle"));

				Metric metric = new Metric(metricType);

				BigDecimal metricValueBigDecimal = (BigDecimal)recordMap.get(
					metricType.getFieldName());

				if (metricValueBigDecimal != null) {
					metric.setValue(metricValueBigDecimal.doubleValue());
				}

				BiConsumer<T, Metric> metricSetterBiConsumer =
					assetMetricSetters.get(metricType.getName());

				metricSetterBiConsumer.accept(assetMetric, metric);

				return assetMetric;
			},
			dslContext.select(
				canonicalUrlField, metricField, pageTitleField
			).from(
				getTableName(timeRange)
			).where(
				_createWhereClause(assetId, assetTitle, channelId, timeRange)
			).groupBy(
				canonicalUrlField, pageTitleField
			).orderBy(
				metricField.desc()
			));
	}

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

		Field<String> assetIdField = DSL.field(
			getAssetIdFieldName(), String.class);
		Field<String> assetTitleField = DSL.field(
			getAssetTitleFieldName(), String.class);

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
			dslContext.selectCount(
			).from(
				DSL.select(
					DSL.field("assetId"), DSL.field("assetTitle")
				).from(
					getTableName(timeRange)
				).where(
					_createWhereClause(
						null, null, channelId, keywords, timeRange)
				).groupBy(
					DSL.field("assetId"), DSL.field("assetTitle")
				)
			));
	}

	@Override
	public List<Metric> getBrowserMetrics(
		String assetId, @Nullable String assetTitle, Long channelId,
		MetricType metricType, TimeRange timeRange) {

		Field<String> browserNameField = DSL.field("browserName", String.class);
		Field<BigDecimal> metricField = getMetricField(metricType);

		return _queryExecutor.queryForList(
			recordMap -> {
				Metric metric = new Metric(metricType);

				BigDecimal metricValueBigDecimal = (BigDecimal)recordMap.get(
					metricType.getFieldName());

				metric.setValue(metricValueBigDecimal.doubleValue());

				metric.setValueKey((String)recordMap.get("browserName"));

				return metric;
			},
			dslContext.select(
				browserNameField, metricField
			).from(
				getTableName(timeRange)
			).where(
				_createWhereClause(assetId, assetTitle, channelId, timeRange)
			).groupBy(
				browserNameField
			).orderBy(
				metricField.desc()
			));
	}

	@Override
	public List<Metric> getDeviceMetrics(
		String assetId, @Null String assetTitle, Long channelId,
		MetricType metricType, TimeRange timeRange) {

		Field<String> deviceTypeField = DSL.field("deviceType", String.class);
		Field<BigDecimal> metricField = getMetricField(metricType);
		Field<String> platformNameField = DSL.field(
			"platformName", String.class);

		Map<String, Metric> metrics = new LinkedHashMap<>();

		_queryExecutor.queryForList(
			recordMap -> {
				Metric deviceTypeMetric = new Metric(metricType);

				String deviceType = (String)recordMap.get("deviceType");

				deviceTypeMetric.setValueKey(deviceType);

				metrics.putIfAbsent(deviceType, deviceTypeMetric);

				deviceTypeMetric = metrics.get(deviceType);

				Metric platformNameMetric = new Metric(metricType);

				String platformName = (String)recordMap.get("platformName");

				platformNameMetric.setValueKey(platformName);

				BigDecimal metricValueBigDecimal = (BigDecimal)recordMap.get(
					metricType.getFieldName());

				platformNameMetric.setValue(
					metricValueBigDecimal.doubleValue());

				deviceTypeMetric.addMetric(platformNameMetric);

				deviceTypeMetric.setValue(
					deviceTypeMetric.getValue() +
						metricValueBigDecimal.doubleValue());

				return null;
			},
			dslContext.select(
				deviceTypeField, metricField, platformNameField
			).from(
				getTableName(timeRange)
			).where(
				_createWhereClause(assetId, assetTitle, channelId, timeRange)
			).groupBy(
				deviceTypeField, platformNameField
			).orderBy(
				deviceTypeField, metricField.desc()
			));

		return new ArrayList<>(metrics.values());
	}

	@Override
	public List<Metric> getGeolocationMetrics(
		String assetId, @Nullable String assetTitle, Long channelId,
		MetricType metricType, TimeRange timeRange) {

		Field<String> countryField = DSL.field("country", String.class);
		Field<BigDecimal> metricField = getMetricField(metricType);

		return _queryExecutor.queryForList(
			recordMap -> {
				Metric metric = new Metric(metricType);

				BigDecimal metricValueBigDecimal = (BigDecimal)recordMap.get(
					metricType.getFieldName());

				metric.setValue(metricValueBigDecimal.doubleValue());

				metric.setValueKey((String)recordMap.get("country"));

				return metric;
			},
			dslContext.select(
				countryField, metricField
			).from(
				getTableName(timeRange)
			).where(
				_createWhereClause(assetId, assetTitle, channelId, timeRange)
			).groupBy(
				countryField
			).orderBy(
				metricField.desc()
			));
	}

	@Override
	public List<HistogramMetric> getHistogramMetrics(
		String assetId, @Nullable String assetTitle, Long channelId,
		boolean includePrevious, Interval interval, MetricType metricType,
		TimeRange timeRange) {

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

				if (bigDecimal == null) {
					bigDecimal = BigDecimal.ZERO;
				}

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
				_createWhereClause(
					assetId, assetTitle, channelId, includePrevious, timeRange)
			).groupBy(
				field
			));
	}

	@Override
	public List<Individual> getKnownIndividuals(
		String assetId, @Nullable String assetTitle, Long channelId,
		MetricType metricType, Pageable pageable, @Nullable String keywords,
		TimeRange timeRange) {

		Table<Record> individualTable = DSL.table(
			"BQIndividual"
		).as(
			"individual"
		);

		Condition whereClauseCondition = _createWhereClause(
			assetId, assetTitle, channelId, timeRange);

		if (StringUtils.isNotBlank(keywords)) {
			String wrappedKeywords = StringUtils.wrap(
				StringUtils.lowerCase(keywords), "%");

			whereClauseCondition = whereClauseCondition.and(
				DSL.or(
					DSL.lower(
						DSL.field("individual.firstname", String.class)
					).like(
						wrappedKeywords
					),
					DSL.lower(
						DSL.field("individual.lastname", String.class)
					).like(
						wrappedKeywords
					),
					DSL.lower(
						DSL.field("individual.emailaddress", String.class)
					).like(
						wrappedKeywords
					)));
		}

		return _queryExecutor.queryForList(
			recordMap -> new Individual(
				(String)recordMap.get("individual.emailAddress"),
				(String)recordMap.get("individual.id"),
				recordMap.get("individual.firstName") + " " +
					recordMap.get("individual.lastName")),
			dslContext.selectDistinct(
				DSL.field("individual.emailAddress"),
				DSL.field("individual.firstName"), DSL.field("individual.id"),
				DSL.field("individual.lastName")
			).from(
				DSL.table(
					getTableName(timeRange)
				).as(
					"metric"
				)
			).join(
				DSL.table(
					"BQIdentity"
				).as(
					"identity"
				)
			).on(
				DSL.field(
					"identity.userid"
				).eq(
					DSL.field("metric.userId")
				)
			).join(
				individualTable
			).on(
				DSL.field(
					"individual.id"
				).eq(
					DSL.field("identity.individualId")
				)
			).where(
				whereClauseCondition.and(
					DSL.field(
						metricType.getFieldName()
					).gt(
						0
					))
			));
	}

	@Override
	public Long getKnownIndividualsCount(
		String assetId, @Nullable String assetTitle, Long channelId,
		MetricType metricType, @Nullable String keywords, TimeRange timeRange) {

		Condition whereClauseCondition = _createWhereClause(
			assetId, assetTitle, channelId, timeRange);

		if (StringUtils.isNotBlank(keywords)) {
			whereClauseCondition = whereClauseCondition.and(
				DSL.or(
					DSL.field(
						"individual.firstname"
					).containsIgnoreCase(
						keywords
					),
					DSL.field(
						"individual.lastname"
					).containsIgnoreCase(
						keywords
					),
					DSL.field(
						"individual.emailaddress"
					).containsIgnoreCase(
						keywords
					)));
		}

		return _queryExecutor.queryForLong(
			dslContext.select(
				DSL.countDistinct(DSL.field("individual.id"))
			).from(
				DSL.table(
					getTableName(timeRange)
				).as(
					"metric"
				)
			).join(
				DSL.table(
					"BQIdentity"
				).as(
					"identity"
				)
			).on(
				DSL.field(
					"identity.userid"
				).eq(
					DSL.field("metric.userId")
				)
			).join(
				DSL.table(
					"BQIndividual"
				).as(
					"individual"
				)
			).on(
				DSL.field(
					"individual.id"
				).eq(
					DSL.field("identity.individualId")
				)
			).where(
				whereClauseCondition.and(
					DSL.field(
						metricType.getFieldName()
					).gt(
						0
					))
			));
	}

	@Override
	public Long getSegmentedIndividualsCount(
		String assetId, @Nullable String assetTitle, Long channelId,
		MetricType metricType, TimeRange timeRange) {

		Condition whereClauseCondition = _createWhereClause(
			assetId, assetTitle, channelId, timeRange);

		return _queryExecutor.queryForLong(
			dslContext.with(
				"segmentedIndividuals"
			).as(
				dslContext.selectDistinct(
					DSL.field(
						"membership.userId"
					).as(
						"userId"
					),
					DSL.field(
						"membership.individualId"
					).as(
						"individualId"
					)
				).from(
					DSL.table(
						getTableName(timeRange)
					).as(
						"metric"
					)
				).join(
					DSL.table(
						"BQMembership"
					).as(
						"membership"
					)
				).on(
					DSL.field(
						"metric.userId"
					).eq(
						DSL.field("membership.userId")
					)
				).where(
					whereClauseCondition.and(
						DSL.field(
							metricType.getFieldName()
						).gt(
							0
						))
				)
			).with(
				"segmentedAnonymousIndividualCount"
			).as(
				dslContext.select(
					DSL.countDistinct(
						DSL.field("segmentedIndividuals.userId")
					).as(
						"value"
					)
				).from(
					"segmentedIndividuals"
				).where(
					DSL.field(
						"segmentedIndividuals.individualId"
					).isNull()
				)
			).with(
				"segmentedKnownIndividualCount"
			).as(
				dslContext.select(
					DSL.countDistinct(
						DSL.field("segmentedIndividuals.individualId")
					).as(
						"value"
					)
				).from(
					"segmentedIndividuals"
				).where(
					DSL.field(
						"segmentedIndividuals.individualId"
					).isNotNull()
				)
			).select(
				DSL.field(
					"segmentedAnonymousIndividualCount.value", Integer.class
				).plus(
					DSL.field(
						"segmentedKnownIndividualCount.value", Integer.class)
				)
			).from(
				DSL.table("segmentedAnonymousIndividualCount"),
				DSL.table("segmentedKnownIndividualCount")
			));
	}

	@Override
	public List<Metric> getSegmentMetrics(
		String assetId, @Nullable String assetTitle, Long channelId,
		MetricType metricType, TimeRange timeRange) {

		Condition whereClauseCondition = _createWhereClause(
			assetId, assetTitle, channelId, timeRange);

		return _queryExecutor.queryForList(
			recordMap -> {
				Metric metric = new Metric(metricType);

				BigDecimal metricValueBigDecimal = (BigDecimal)recordMap.get(
					"individualsCount");

				metric.setValueKey(String.valueOf(recordMap.get("segmentId")));

				if (metricValueBigDecimal != null) {
					metric.setValue(metricValueBigDecimal.doubleValue());
				}

				return metric;
			},
			dslContext.with(
				"segmentedIndividuals"
			).as(
				dslContext.select(
					DSL.field("membership.individualId"),
					DSL.field("membership.segmentId"),
					DSL.field("membership.userId")
				).from(
					DSL.table(
						getTableName(timeRange)
					).as(
						"metric"
					)
				).join(
					DSL.table(
						"BQMembership"
					).as(
						"membership"
					)
				).on(
					DSL.field(
						"membership.userId"
					).eq(
						DSL.field("metric.userId")
					)
				).where(
					whereClauseCondition.and(
						DSL.field(
							metricType.getFieldName()
						).gt(
							0
						))
				)
			).with(
				"segmentedAnonymousIndividualCount"
			).as(
				dslContext.select(
					DSL.field(
						"segmentedIndividuals.segmentId"
					).as(
						"segmentId"
					),
					DSL.countDistinct(
						DSL.field("segmentedIndividuals.userId")
					).as(
						"value"
					)
				).from(
					"segmentedIndividuals"
				).where(
					DSL.field(
						"segmentedIndividuals.individualId"
					).isNull()
				).groupBy(
					DSL.field("segmentedIndividuals.segmentId")
				)
			).with(
				"segmentedKnownIndividualCount"
			).as(
				dslContext.select(
					DSL.field(
						"segmentedIndividuals.segmentId"
					).as(
						"segmentId"
					),
					DSL.countDistinct(
						DSL.field("segmentedIndividuals.individualId")
					).as(
						"value"
					)
				).from(
					"segmentedIndividuals"
				).where(
					DSL.field(
						"segmentedIndividuals.individualId"
					).isNotNull()
				).groupBy(
					DSL.field("segmentedIndividuals.segmentId")
				)
			).select(
				DSL.field("segmentId"),
				DSL.sum(
					DSL.field("value", Integer.class)
				).as(
					"individualsCount"
				)
			).from(
				dslContext.select(
				).from(
					"segmentedAnonymousIndividualCount"
				).unionAll(
					dslContext.select(
					).from(
						"segmentedKnownIndividualCount"
					)
				)
			).groupBy(
				DSL.field("segmentId")
			).orderBy(
				DSL.field(
					"individualsCount"
				).desc()
			).limit(
				5
			));
	}

	protected abstract T createAssetMetric();

	protected String getAssetIdFieldName() {
		return "assetId";
	}

	protected abstract Map<String, BiConsumer<T, Metric>>
		getAssetMetricSetters();

	protected String getAssetTitleFieldName() {
		return "assetTitle";
	}

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
		boolean includePrevious, TimeRange timeRange) {

		if (includePrevious) {
			timeRange = timeRange.getIncludePreviousTimeRange();
		}

		return _createWhereClause(
			assetId, assetTitle, channelId, null, timeRange);
	}

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
					getAssetTitleFieldName()
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
				DSL.lower(
					DSL.field("assetTitle", String.class)
				).like(
					StringUtils.wrap(StringUtils.lowerCase(keywords), "%")
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

		assetMetric.setAssetId((String)recordMap.get(getAssetIdFieldName()));
		assetMetric.setAssetTitle(
			(String)recordMap.get(getAssetTitleFieldName()));

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