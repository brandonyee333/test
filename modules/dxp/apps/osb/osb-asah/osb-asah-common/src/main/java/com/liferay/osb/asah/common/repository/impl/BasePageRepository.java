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
import com.liferay.osb.asah.common.model.TimeRange;
import com.liferay.osb.asah.common.repository.CustomPageRepository;
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
import org.jooq.SelectSelectStep;
import org.jooq.SortField;
import org.jooq.impl.DSL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * @author Leslie Wong
 */
public abstract class BasePageRepository implements CustomPageRepository {

	public long countPageVisitorBehaviorMetric(
		Long channelId, TimeRange timeRange, ZoneId zoneId) {

		SelectSelectStep<Record1<Integer>> selectSelectStep =
			dslContext.selectCount();

		return _queryExecutor.queryForLong(
			selectSelectStep.from(
				DSL.select(
					DSL.field("canonicalurl"), DSL.field("title")
				).from(
					getTableName()
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
				getTableName()
			).where(
				_createWhereClause(
					canonicalUrl, channelId, timeRange, title, zoneId)
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
				getTableName()
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

	protected abstract String getTableName();

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

	private List<Field<?>> _getDistinctMetricFields(
		Map<String, String> fieldNames) {

		List<Field<?>> metricFields = new ArrayList<>();

		for (Map.Entry<String, String> entry : fieldNames.entrySet()) {
			metricFields.add(
				DSL.countDistinct(
					DSL.field(entry.getKey())
				).cast(
					BigDecimal.class
				).as(
					entry.getValue()
				));
		}

		return metricFields;
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

		metricFields.addAll(
			_getDistinctMetricFields(
				new HashMap<String, String>() {
					{
						put("sessionid", "sessions");
						put("userid", "visitors");
					}
				}));

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

	@Autowired
	private DSLHelper _dslHelper;

	@Autowired
	private Environment _environment;

	@Autowired
	private QueryExecutor _queryExecutor;

}