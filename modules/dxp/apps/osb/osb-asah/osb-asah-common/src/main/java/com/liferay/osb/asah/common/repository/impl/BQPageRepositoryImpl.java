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

import com.liferay.osb.asah.common.model.PageVisitorBehaviorMetric;
import com.liferay.osb.asah.common.model.SiteVisitorBehaviorMetric;
import com.liferay.osb.asah.common.model.TimeRange;
import com.liferay.osb.asah.common.repository.BQPageRepository;
import com.liferay.osb.asah.common.repository.executor.QueryExecutor;
import com.liferay.osb.asah.common.repository.helper.DSLHelper;

import java.math.BigDecimal;

import java.time.ZoneId;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.SelectFinalStep;
import org.jooq.SelectJoinStep;
import org.jooq.SelectOnConditionStep;
import org.jooq.SelectSelectStep;
import org.jooq.SortField;
import org.jooq.impl.DSL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

/**
 * @author Leslie Wong
 */
@Repository
public class BQPageRepositoryImpl implements BQPageRepository {

	public long countPageVisitorBehaviorMetric(
		Long channelId, TimeRange timeRange, ZoneId zoneId) {

		SelectSelectStep<Record1<Integer>> selectSelectStep =
			dslContext.selectCount();

		return _queryExecutor.queryForLong(
			selectSelectStep.from(
				DSL.select(
					DSL.field("canonicalurl"), DSL.field("title")
				).from(
					_getTableName(timeRange)
				).where(
					_createWhereClause(null, channelId, timeRange, null, zoneId)
				).groupBy(
					DSL.field("canonicalurl"), DSL.field("title")
				)));
	}

	public Optional<PageVisitorBehaviorMetric> getPageVisitorBehaviorMetric(
		String canonicalUrl, Long channelId, TimeRange timeRange, String title,
		ZoneId zoneId) {

		return _queryExecutor.queryForObject(
			PageVisitorBehaviorMetric.class,
			dslContext.select(
				_getMetricFields()
			).from(
				_getTableName(timeRange)
			).where(
				_createWhereClause(
					canonicalUrl, channelId, timeRange, title, zoneId)
			));
	}

	@Override
	public List<SiteVisitorBehaviorMetric> getSiteVisitorBehaviorMetrics(
		Long channelId, boolean includePrevious, TimeRange timeRange,
		ZoneId zoneId) {

		String tableName = _getTableName(timeRange);

		Field<Integer> field = DSL.when(
			DSL.field(
				_getFieldName("eventDate", tableName)
			).gt(
				_dslHelper.getDateParam(
					timeRange.getStartLocalDateTime(), zoneId.toString())
			),
			1
		).otherwise(
			0
		).as(
			"groupByField"
		);

		return _queryExecutor.queryForList(
			SiteVisitorBehaviorMetric.class,
			(SelectFinalStep)_joinWithIdentityTable(
				dslContext.select(
					field, _getUniqueVisitorsField(tableName)
				).from(
					tableName
				),
				tableName
			).where(
				_createWhereClause(
					null, channelId, includePrevious, timeRange, null, zoneId)
			).groupBy(
				field
			).orderBy(
				field.desc()
			));
	}

	public List<PageVisitorBehaviorMetric> searchPageVisitorBehaviorMetrics(
		Long channelId, Pageable pageable, TimeRange timeRange, ZoneId zoneId) {

		List<Field<?>> metricFields = _getMetricFields();

		metricFields.add(DSL.field("canonicalurl", String.class));
		metricFields.add(DSL.field("title", String.class));

		return _queryExecutor.queryForList(
			PageVisitorBehaviorMetric.class,
			dslContext.select(
				metricFields.toArray(new Field[0])
			).from(
				_getTableName(timeRange)
			).where(
				_createWhereClause(null, channelId, timeRange, null, zoneId)
			).groupBy(
				DSL.field("canonicalurl"), DSL.field("title")
			).orderBy(
				_getSortFields(pageable.getSort())
			).limit(
				pageable.getPageSize()
			).offset(
				pageable.getOffset()
			));
	}

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
		String canonicalUrl, Long channelId, boolean includePrevious,
		TimeRange timeRange, String title, ZoneId zoneId) {

		TimeRange whereClauseTimeRange = timeRange;

		if (includePrevious) {
			whereClauseTimeRange = timeRange.getIncludePreviousTimeRange();
		}

		return _createWhereClause(
			canonicalUrl, channelId, whereClauseTimeRange, title, zoneId);
	}

	private Condition _createWhereClause(
		String canonicalUrl, Long channelId, TimeRange timeRange, String title,
		ZoneId zoneId) {

		Condition condition = DSL.and(
			DSL.field(
				"channelId"
			).eq(
				channelId
			),
			DSL.field(
				"eventDate"
			).between(
				_dslHelper.getDateParam(
					timeRange.getStartLocalDateTime(), zoneId.toString()),
				_dslHelper.getDateParam(
					timeRange.getEndLocalDateTime(), zoneId.toString())
			));

		if (canonicalUrl != null) {
			condition = condition.and(
				DSL.field(
					"canonicalUrl"
				).eq(
					canonicalUrl
				));
		}

		if (title != null) {
			condition = condition.and(
				DSL.field(
					"title"
				).eq(
					title
				));
		}

		return condition;
	}

	private List<Field<?>> _getAvgMetricFields(Map<String, String> fieldNames) {
		List<Field<?>> metricFields = new ArrayList<>();

		for (Map.Entry<String, String> entry : fieldNames.entrySet()) {
			metricFields.add(
				DSL.sum(
					DSL.field(
						entry.getKey()
					).cast(
						BigDecimal.class
					)
				).div(
					DSL.countDistinct(DSL.field("sessionid"))
				).as(
					entry.getValue()
				));
		}

		return metricFields;
	}

	private String _getFieldName(String fieldName, String tableName) {
		return String.format("%s.%s", tableName, fieldName);
	}

	private List<Field<?>> _getMetricFields() {
		List<Field<?>> metricFields = _getSumMetricFields(
			new HashMap<String, String>() {
				{
					put("bounce", "bounces");
					put("entrance", "entrances");
					put("exit", "exits");
					put("timeonpage", "timeonpage");
					put("views", "views");
				}
			});

		metricFields.add(
			DSL.countDistinct(
				DSL.field(_getFieldName("sessionid", tableName))
			).cast(
				BigDecimal.class
			).as(
				"sessions"
			));

		metricFields.add(_getUniqueVisitorsField(tableName));

		metricFields.addAll(
			_getAvgMetricFields(
				new HashMap<String, String>() {
					{
						put("bounce", "bouncerate");
						put("exit", "exitrate");
						put("timeonpage", "avgtimeonpage");
					}
				}));

		return metricFields;
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

	private List<Field<?>> _getSumMetricFields(Map<String, String> fieldNames) {
		List<Field<?>> metricFields = new ArrayList<>();

		for (Map.Entry<String, String> entry : fieldNames.entrySet()) {
			metricFields.add(
				DSL.sum(
					DSL.field(entry.getKey(), Long.class)
				).as(
					entry.getValue()
				));
		}

		return metricFields;
	}

	private String _getTableName(TimeRange timeRange) {
		if (!isBigQueryDialect()) {
			return "BQPage";
		}

		if (timeRange == TimeRange.LAST_24_HOURS) {
			return "PageHourly";
		}

		return "PageDaily";
	}

	private Field<Integer> _getUniqueVisitorsField(String tableName) {
		return DSL.countDistinct(
			DSL.field("BQIdentity.emailaddresshashed")
		).plus(
			DSL.countDistinct(
				DSL.when(
					DSL.field(
						"BQIdentity.emailaddresshashed"
					).isNull(),
					DSL.field(_getFieldName("userid", tableName))))
		).as(
			"visitors"
		);
	}

	private SelectOnConditionStep _joinWithIdentityTable(
		SelectJoinStep selectJoinStep, String tableName) {

		return selectJoinStep.leftJoin(
			"BQIdentity"
		).on(
			DSL.field(
				"BQIdentity.userid"
			).eq(
				DSL.field(_getFieldName("userid", tableName))
			)
		);
	}

	@Autowired
	private DSLHelper _dslHelper;

	@Autowired
	private Environment _environment;

	@Autowired
	private QueryExecutor _queryExecutor;

}