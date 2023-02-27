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

package com.liferay.osb.asah.backend.repository.test;

import com.liferay.osb.asah.backend.model.CustomAssetMetric;
import com.liferay.osb.asah.backend.repository.AssetMetricRepository;
import com.liferay.osb.asah.common.model.CustomAssetMetricType;
import com.liferay.osb.asah.common.model.Interval;
import com.liferay.osb.asah.common.model.TimeRange;
import com.liferay.osb.asah.common.util.SetUtil;
import com.liferay.osb.asah.test.util.annotation.BQSQLResource;

import org.apache.commons.codec.digest.DigestUtils;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * @author Marcellus Tavares
 */
public class CustomAssetMetricRepositoryTest
	extends BaseAssetMetricRepositoryTestCase {

	@BQSQLResource(
		resourcePath = "custom_asset_metric_views_histogram_last_24_hours.sql"
	)
	@Test
	public void testGetViewsHistogramMetricsLast24Hours() {
		assertHistogramMetrics(
			SetUtil.of(1D, 2D, 4D, 7D),
			_assetMetricRepository.getHistogramMetrics(
				DigestUtils.sha256Hex("Adefault1"), null, 1L, false,
				Interval.HOUR, CustomAssetMetricType.VIEWS,
				TimeRange.LAST_24_HOURS));
	}

	@BQSQLResource(
		resourcePath = "custom_asset_metric_views_histogram_last_24_hours.sql"
	)
	@Test
	public void testGetViewsHistogramMetricsLast24HoursDifferentTimezone() {
		assertHistogramMetricsDifferentTimezone(
			DigestUtils.sha256Hex("Adefault1"), 1L, CustomAssetMetricType.VIEWS,
			-3, "America/Fortaleza");
	}

	@Override
	protected AssetMetricRepository getAssetMetricRepository() {
		return _assetMetricRepository;
	}

	@Autowired
	@Qualifier("CustomAssetMetricRepository")
	private AssetMetricRepository<CustomAssetMetric> _assetMetricRepository;

}