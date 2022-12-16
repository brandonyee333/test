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
import com.liferay.osb.asah.backend.model.FormMetric;
import com.liferay.osb.asah.backend.model.FormMetricType;
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
@Repository("FormAssetMetricRepository")
public class FormAssetMetricRepositoryImpl
	extends BaseAssetMetricRepository<FormMetric> {

	@Override
	public AssetType getAssetType() {
		return AssetType.FORM;
	}

	@Override
	protected FormMetric createAssetMetric() {
		return new FormMetric();
	}

	@Override
	protected Map<String, BiConsumer<FormMetric, Metric>>
		getAssetMetricSetters() {

		return new HashMap<String, BiConsumer<FormMetric, Metric>>() {
			{
				put(
					FormMetricType.ABANDONMENTS.getName(),
					FormMetric::setAbandonmentsMetric);
				put(
					FormMetricType.COMPLETION_TIME.getName(),
					FormMetric::setCompletionTimeMetric);
				put(
					FormMetricType.SUBMISSIONS.getName(),
					FormMetric::setSubmissionsMetric);
				put(FormMetricType.VIEWS.getName(), FormMetric::setViewsMetric);
			}
		};
	}

	@Override
	protected Field<BigDecimal> getMetricField(
		MetricType metricType, TimeRange timeRange) {

		if (metricType == FormMetricType.ABANDONMENTS) {
			return DSL.sum(
				DSL.field(
					FormMetricType.ABANDONMENTS.getFieldName(), Long.class)
			).div(
				DSL.greatest(
					DSL.sum(DSL.field("finalizedFormViews", Long.class)),
					DSL.one())
			);
		}

		Field<Long> longField = DSL.field(
			metricType.getFieldName(), Long.class);

		if (metricType == FormMetricType.COMPLETION_TIME) {
			return DSL.avg(longField);
		}

		return DSL.sum(longField);
	}

	@Override
	protected MetricType getMetricType(String metricTypeName) {
		return FormMetricType.of(metricTypeName);
	}

	@Override
	protected MetricType[] getMetricTypes() {
		return FormMetricType.values();
	}

	@Override
	protected String getTableName(TimeRange timeRange) {
		if (!dslHelper.isBigQueryDialect()) {
			return "BQForm";
		}

		if ((timeRange == TimeRange.LAST_24_HOURS) ||
			(timeRange == TimeRange.YESTERDAY)) {

			return "FormHourly";
		}

		return "FormDaily";
	}

}