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

import com.liferay.osb.asah.backend.model.PageVisitorBehaviorMetric;
import com.liferay.osb.asah.backend.repository.PageRepository;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.model.TimeRange;

import java.math.BigDecimal;

import java.time.ZoneId;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.impl.DSL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

/**
 * @author Leslie Wong
 */
@Repository
public class PageRepositoryImpl implements PageRepository {

	@Override
	public PageVisitorBehaviorMetric getPageVisitorBehaviorMetric(
		String canonicalUrl, Long channelId, TimeRange timeRange, String title,
		ZoneId zoneId) {

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

		return dslContext.select(
			metricFields.toArray(new Field[0])
		).from(
			"BQPages"
		).where(
			_createWhereClause(
				canonicalUrl, channelId, timeRange, title, zoneId)
		).fetchOne(
			record -> new PageVisitorBehaviorMetric(record.intoMap())
		);
	}

	@Override
	public PageVisitorBehaviorMetric getPageVisitorBehaviorMetrics(
		Long channelId, Pageable pageable, TimeRange timeRange, ZoneId zoneId) {

		List<Field<BigDecimal>> metricFields = _getMetricFields(
			"bounces", "entrances", "exits", "sessions", "timeonpage", "views",
			"visitors");

		return dslContext.select(
			metricFields.toArray(new Field[0])
		).from(
			"BQPages"
		).where(
			_createWhereClause(null, channelId, timeRange, null, zoneId)
		).fetchOne(
			record -> new PageVisitorBehaviorMetric(record.intoMap())
		);
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
				DateUtil.toUTCLocalDateTime(
					timeRange.getStartLocalDateTime(), zoneId),
				DateUtil.toUTCLocalDateTime(
					timeRange.getEndLocalDateTime(), zoneId)
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

}