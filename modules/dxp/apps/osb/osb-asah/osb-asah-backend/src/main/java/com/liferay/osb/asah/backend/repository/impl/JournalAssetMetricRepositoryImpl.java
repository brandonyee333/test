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
import com.liferay.osb.asah.backend.model.JournalMetric;
import com.liferay.osb.asah.backend.model.JournalMetricType;
import com.liferay.osb.asah.backend.model.Metric;
import com.liferay.osb.asah.common.model.MetricType;
import com.liferay.osb.asah.common.model.TimeRange;

import java.math.BigDecimal;

import java.util.Collections;
import java.util.Map;
import java.util.function.BiConsumer;

import org.jooq.Field;
import org.jooq.impl.DSL;

import org.springframework.stereotype.Repository;

/**
 * @author Alejo Ceballos
 * @author Marcos Martins
 */
@Repository("JournalAssetMetricRepository")
public class JournalAssetMetricRepositoryImpl
	extends BaseAssetMetricRepository<JournalMetric> {

	@Override
	public AssetType getAssetType() {
		return AssetType.JOURNAL;
	}

	@Override
	protected JournalMetric createAssetMetric() {
		return new JournalMetric();
	}

	@Override
	protected Map<String, BiConsumer<JournalMetric, Metric>>
		getAssetMetricSetters() {

		return Collections.singletonMap(
			JournalMetricType.VIEWS.getName(), JournalMetric::setViewsMetric);
	}

	@Override
	protected Field<BigDecimal> getMetricField(
		MetricType metricType, TimeRange timeRange) {

		Field<Long> longField = DSL.field(
			metricType.getFieldName(), Long.class);

		return DSL.sum(longField);
	}

	@Override
	protected MetricType getMetricType(String metricTypeName) {
		return JournalMetricType.of(metricTypeName);
	}

	@Override
	protected MetricType[] getMetricTypes() {
		return JournalMetricType.values();
	}

	@Override
	protected String getTableName(TimeRange timeRange) {
		if (!dslHelper.isBigQueryDialect()) {
			return "BQJournal";
		}

		if ((timeRange == TimeRange.LAST_24_HOURS) ||
			(timeRange == TimeRange.YESTERDAY)) {

			return "JournalHourly";
		}

		return "JournalDaily";
	}

}