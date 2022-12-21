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

import com.liferay.osb.asah.backend.model.AssetType;
import com.liferay.osb.asah.backend.model.Metric;
import com.liferay.osb.asah.backend.model.PageMetric;
import com.liferay.osb.asah.common.model.MetricType;
import com.liferay.osb.asah.common.model.PageMetricType;
import com.liferay.osb.asah.common.model.TimeRange;

import java.math.BigDecimal;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import org.jooq.Field;
import org.jooq.Record;
import org.jooq.SelectJoinStep;
import org.jooq.SelectSelectStep;
import org.jooq.impl.DSL;

import org.springframework.stereotype.Repository;

/**
 * @author Marcellus Tavares
 */
@Repository("PageAssetMetricRepository")
public class PageAssetMetricRepositoryImpl
	extends BaseAssetMetricRepository<PageMetric> {

	@Override
	public AssetType getAssetType() {
		return AssetType.PAGE;
	}

	@Override
	protected PageMetric createAssetMetric() {
		return new PageMetric();
	}

	@Override
	protected String getAssetIdFieldName() {
		return "canonicalurl";
	}

	@Override
	protected SelectJoinStep<Record> getAssetMetricSelectJoinStep(
		SelectSelectStep<Record> selectSelectStep, TimeRange timeRange) {

		return selectSelectStep.from(
			getTableName(timeRange)
		).leftJoin(
			"BQIdentity"
		).on(
			DSL.field(
				"BQIdentity.id"
			).eq(
				DSL.field(getTableName(timeRange) + ".userId")
			)
		);
	}

	@Override
	protected Map<String, BiConsumer<PageMetric, Metric>>
		getAssetMetricSetters() {

		return new HashMap<String, BiConsumer<PageMetric, Metric>>() {
			{
				put(
					PageMetricType.AVG_TIME_ON_PAGE.getName(),
					PageMetric::setAvgTimeOnPageMetric);
				put(
					PageMetricType.BOUNCE.getName(),
					PageMetric::setBounceMetric);
				put(
					PageMetricType.BOUNCE_RATE.getName(),
					PageMetric::setBounceRateMetric);
				put(
					PageMetricType.DIRECT_ACCESS.getName(),
					PageMetric::setDirectAccessMetric);
				put(
					PageMetricType.ENTRANCES.getName(),
					PageMetric::setEntrancesMetric);
				put(
					PageMetricType.EXIT_RATE.getName(),
					PageMetric::setExitRateMetric);
				put(
					PageMetricType.INDIRECT_ACCESS.getName(),
					PageMetric::setIndirectAccessMetric);
				put(PageMetricType.READS.getName(), PageMetric::setReadsMetric);
				put(
					PageMetricType.SESSIONS.getName(),
					PageMetric::setSessionsMetric);
				put(PageMetricType.VIEWS.getName(), PageMetric::setViewsMetric);
				put(
					PageMetricType.VISITORS.getName(),
					PageMetric::setVisitorsMetric);
			}
		};
	}

	@Override
	protected String getAssetTitleFieldName() {
		return "title";
	}

	@Override
	protected Field<BigDecimal> getMetricField(
		MetricType metricType, TimeRange timeRange) {

		if ((metricType == PageMetricType.AVG_TIME_ON_PAGE) ||
			(metricType == PageMetricType.BOUNCE_RATE) ||
			(metricType == PageMetricType.EXIT_RATE)) {

			return DSL.coalesce(
				DSL.sum(
					DSL.field(metricType.getFieldName(), Float.class)
				).div(
					DSL.nullif(DSL.countDistinct(DSL.field("sessionId")), 0)
				),
				BigDecimal.ZERO);
		}

		if (metricType == PageMetricType.VISITORS) {
			Field<Integer> visitorsField = DSL.countDistinct(
				DSL.field("BQIdentity.individualId")
			).plus(
				DSL.countDistinct(
					DSL.when(
						DSL.field(
							"BQIdentity.individualId"
						).isNull(),
						DSL.field(getTableName(timeRange) + ".userId")))
			);

			return DSL.cast(visitorsField, BigDecimal.class);
		}

		Field<Long> longField = DSL.field(
			metricType.getFieldName(), Long.class);

		return DSL.sum(longField);
	}

	@Override
	protected MetricType getMetricType(String metricTypeName) {
		return PageMetricType.of(metricTypeName);
	}

	@Override
	protected MetricType[] getMetricTypes() {
		return PageMetricType.values();
	}

	@Override
	protected String getTableName(TimeRange timeRange) {
		if (!dslHelper.isBigQueryDialect()) {
			return "BQPage";
		}

		if ((timeRange == TimeRange.LAST_24_HOURS) ||
			(timeRange == TimeRange.YESTERDAY)) {

			return "PageHourly";
		}

		return "PageDaily";
	}

}