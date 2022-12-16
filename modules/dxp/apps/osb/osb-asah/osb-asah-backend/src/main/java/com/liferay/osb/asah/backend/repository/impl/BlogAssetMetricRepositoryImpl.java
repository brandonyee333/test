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
import com.liferay.osb.asah.backend.model.BlogMetric;
import com.liferay.osb.asah.backend.model.BlogMetricType;
import com.liferay.osb.asah.backend.model.Metric;
import com.liferay.osb.asah.common.model.MetricType;
import com.liferay.osb.asah.common.model.TimeRange;

import java.math.BigDecimal;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import org.jooq.Field;
import org.jooq.impl.DSL;

import org.springframework.stereotype.Repository;

/**
 * @author Alejo Ceballos
 * @author Marcos Martins
 */
@Repository("BlogAssetMetricRepository")
public class BlogAssetMetricRepositoryImpl
	extends BaseAssetMetricRepository<BlogMetric> {

	@Override
	public AssetType getAssetType() {
		return AssetType.BLOG;
	}

	@Override
	protected BlogMetric createAssetMetric() {
		return new BlogMetric();
	}

	@Override
	protected Map<String, BiConsumer<BlogMetric, Metric>>
		getAssetMetricSetters() {

		return new HashMap<String, BiConsumer<BlogMetric, Metric>>() {
			{
				put(
					BlogMetricType.CLICKS.getName(),
					BlogMetric::setClicksMetric);
				put(
					BlogMetricType.COMMENTS.getName(),
					BlogMetric::setCommentsMetric);
				put(
					BlogMetricType.RATINGS.getName(),
					BlogMetric::setRatingsMetric);
				put(
					BlogMetricType.READING_TIME.getName(),
					BlogMetric::setReadingTimeMetric);
				put(BlogMetricType.VIEWS.getName(), BlogMetric::setViewsMetric);
			}
		};
	}

	@Override
	protected Field<BigDecimal> getMetricField(
		MetricType metricType, TimeRange timeRange) {

		if (metricType == BlogMetricType.RATINGS) {
			return DSL.coalesce(
				DSL.sum(
					DSL.field("ratingsScore", Float.class)
				).div(
					DSL.sum(DSL.field("ratings", Long.class))
				),
				BigDecimal.ZERO);
		}

		if (metricType == BlogMetricType.READING_TIME) {
			return DSL.coalesce(
				DSL.sum(
					DSL.field(metricType.getFieldName(), Long.class)
				).div(
					DSL.sum(DSL.field("sessions", Long.class))
				),
				BigDecimal.ZERO);
		}

		Field<Long> longField = DSL.field(
			metricType.getFieldName(), Long.class);

		return DSL.sum(longField);
	}

	@Override
	protected MetricType getMetricType(String metricTypeName) {
		return BlogMetricType.of(metricTypeName);
	}

	@Override
	protected MetricType[] getMetricTypes() {
		return BlogMetricType.values();
	}

	@Override
	protected String getTableName(TimeRange timeRange) {
		if (!dslHelper.isBigQueryDialect()) {
			return "BQBlog";
		}

		if ((timeRange == TimeRange.LAST_24_HOURS) ||
			(timeRange == TimeRange.YESTERDAY)) {

			return "BlogHourly";
		}

		return "BlogDaily";
	}

}