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

package com.liferay.osb.asah.backend.repository;

import com.liferay.osb.asah.backend.model.HistogramMetric;
import com.liferay.osb.asah.backend.model.PageMetric;
import com.liferay.osb.asah.common.model.PageMetricType;
import com.liferay.osb.asah.common.model.TimeRange;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.lang.Nullable;

/**
 * @author Marcellus Tavares
 */
public interface PageAssetMetricRepository
	extends AssetMetricRepository<PageMetric> {

	public List<HistogramMetric> getExperimentHistogramMetrics(
		Long experimentId, PageMetricType pageMetricType, TimeRange timeRange,
		@Nullable String variantId);

	public Optional<PageMetric> getExperimentPageMetric(
		Long experimentId, Set<PageMetricType> pageMetricTypes,
		TimeRange timeRange, String variantId);

	public Optional<PageMetric> getExperimentPageMetric(
		String canonicalUrl, Set<PageMetricType> pageMetricTypes,
		TimeRange timeRange);

	public Long getUniqueSessionsCount(Long experimentId, TimeRange timeRange);

	public Long getVariantUniqueVisitors(
		Long experimentId, TimeRange timeRange, String variantId);

}