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

import com.liferay.osb.asah.backend.model.AssetMetric;
import com.liferay.osb.asah.backend.model.AssetType;
import com.liferay.osb.asah.backend.model.AudienceReport;
import com.liferay.osb.asah.backend.model.HistogramMetric;
import com.liferay.osb.asah.backend.model.Individual;
import com.liferay.osb.asah.backend.model.Metric;
import com.liferay.osb.asah.common.model.Interval;
import com.liferay.osb.asah.common.model.MetricType;
import com.liferay.osb.asah.common.model.TimeRange;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;

/**
 * @author Alejo Ceballos
 * @author Marcos Martins
 */
public interface AssetMetricRepository<T extends AssetMetric> {

	public List<T> getAppearsOnMetrics(
		String assetId, @Nullable String assetTitle, @Nullable Long channelId,
		MetricType metricType, TimeRange timeRange);

	public T getAssetMetric(
		String assetId, @Nullable String assetTitle, @Nullable Long channelId,
		Set<String> selectedMetrics, TimeRange timeRange);

	public List<T> getAssetMetrics(
		@Nullable Long channelId, @Nullable String keywords, Pageable pageable,
		Set<String> selectedMetrics, TimeRange timeRange);

	public Long getAssetMetricsCount(
		@Nullable Long channelId, @Nullable String keywords,
		TimeRange timeRange);

	public AssetType getAssetType();

	public AudienceReport getAudienceReport(
		String assetId, @Nullable String assetTitle, @Nullable Long channelId,
		MetricType metricType, TimeRange timeRange);

	public List<Metric> getBrowserMetrics(
		String assetId, @Nullable String assetTitle, @Nullable Long channelId,
		MetricType metricType, TimeRange timeRange);

	public List<Metric> getDeviceMetrics(
		String assetId, @Nullable String assetTitle, @Nullable Long channelId,
		MetricType metricType, TimeRange timeRange);

	public List<Metric> getGeolocationMetrics(
		String assetId, @Nullable String assetTitle, @Nullable Long channelId,
		MetricType metricType, TimeRange timeRange);

	public List<HistogramMetric> getHistogramMetrics(
		String assetId, @Nullable String assetTitle, @Nullable Long channelId,
		boolean includePrevious, Interval interval, MetricType metricType,
		TimeRange timeRange);

	public List<Individual> getKnownIndividuals(
		String assetId, @Nullable String assetTitle, @Nullable Long channelId,
		MetricType metricType, Pageable pageable, @Nullable String keywords,
		TimeRange timeRange);

	public List<Metric> getSegmentMetrics(
		String assetId, @Nullable String assetTitle, @Nullable Long channelId,
		MetricType metricType, TimeRange timeRange);

}