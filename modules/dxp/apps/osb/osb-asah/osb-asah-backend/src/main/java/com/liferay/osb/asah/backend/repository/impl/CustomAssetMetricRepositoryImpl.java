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

import com.liferay.osb.asah.common.model.CustomAssetMetricType;
import com.liferay.osb.asah.common.model.MetricType;

import java.math.BigDecimal;

import org.jooq.Field;
import org.jooq.impl.DSL;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

/**
 * @author Marcellus Tavares
 */
@ConditionalOnProperty(havingValue = "true", value = "osb.asah.trino.enabled")
@Repository("CustomAssetMetricRepository")
public class CustomAssetMetricRepositoryImpl extends BaseAssetMetricRepository {

	@Override
	protected String getAssetIdFieldName() {
		return "assetPrimaryKey";
	}

	@Override
	protected Field<BigDecimal> getMetricField(MetricType metricType) {
		if (metricType == CustomAssetMetricType.ABANDONMENTS) {
			return DSL.sum(
				DSL.field(
					CustomAssetMetricType.ABANDONMENTS.getFieldName(),
					Long.class)
			).div(
				DSL.sum(
					DSL.field(
						CustomAssetMetricType.VIEWS.getFieldName(), Long.class))
			).as(
				CustomAssetMetricType.ABANDONMENTS.getFieldName()
			);
		}

		Field<Long> longField = DSL.field(
			metricType.getFieldName(), Long.class);

		if ((metricType == CustomAssetMetricType.CLICKS) ||
			(metricType == CustomAssetMetricType.DOWNLOADS) ||
			(metricType == CustomAssetMetricType.SUBMISSIONS) ||
			(metricType == CustomAssetMetricType.VIEWS)) {

			return DSL.sum(
				longField
			).as(
				longField.getName()
			);
		}

		return DSL.avg(
			longField
		).as(
			longField.getName()
		);
	}

	@Override
	protected String getTableName() {
		return "hive.default.CustomAsset";
	}

}