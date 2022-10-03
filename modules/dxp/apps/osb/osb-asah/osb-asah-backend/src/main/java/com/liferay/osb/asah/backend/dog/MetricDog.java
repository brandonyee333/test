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

package com.liferay.osb.asah.backend.dog;

import com.liferay.osb.asah.backend.dog.helper.SearchQueryContext;
import com.liferay.osb.asah.backend.model.AssetMetric;
import com.liferay.osb.asah.backend.model.AssetType;
import com.liferay.osb.asah.backend.model.Metric;
import com.liferay.osb.asah.backend.repository.AssetMetricRepository;
import com.liferay.osb.asah.common.model.MetricType;
import com.liferay.osb.asah.common.model.Sort;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 * @author André Miranda
 */
@Component
public class MetricDog {

	@Autowired
	public MetricDog(List<AssetMetricRepository> assetMetricRepositories) {
		assetMetricRepositories.forEach(
			assetMetricAssetMetricRepository -> _assetMetricRepositoryMap.put(
				assetMetricAssetMetricRepository.getAssetType(),
				assetMetricAssetMetricRepository));
	}

	public <T extends AssetMetric> List<T> getAppearsOnMetrics(
		MetricType metricType, SearchQueryContext searchQueryContext) {

		AssetMetricRepository assetMetricRepository =
			(AssetMetricRepository<T>)_assetMetricRepositoryMap.get(
				searchQueryContext.getAssetType());

		return assetMetricRepository.getAppearsOnMetrics(
			searchQueryContext.getAssetId(), searchQueryContext.getTitle(),
			Long.valueOf(searchQueryContext.getChannelId()), metricType,
			searchQueryContext.getTimeRange());
	}

	public <T extends AssetMetric> T getAssetMetric(
		SearchQueryContext searchQueryContext) {

		return getAssetMetric(searchQueryContext, Collections.emptySet());
	}

	public <T extends AssetMetric> T getAssetMetric(
		SearchQueryContext searchQueryContext, Set<String> selectedMetrics) {

		AssetMetricRepository<T> assetMetricRepository =
			(AssetMetricRepository<T>)_getAssetMetricRepository(
				searchQueryContext.getAssetType());

		String assetTitle = null;

		if (searchQueryContext.getAssetType() != AssetType.CUSTOM) {
			assetTitle = searchQueryContext.getTitle();
		}

		return assetMetricRepository.getAssetMetric(
			searchQueryContext.getAssetId(), assetTitle,
			Long.valueOf(searchQueryContext.getChannelId()), selectedMetrics,
			searchQueryContext.getTimeRange());
	}

	public <T extends AssetMetric> List<T> getAssetMetrics(
		int page, SearchQueryContext searchQueryContext,
		Set<String> selectedMetrics, int size, Sort sort) {

		return _getAssetMetrics(
			page, searchQueryContext, selectedMetrics, size, sort);
	}

	public long getAssetMetricsCount(SearchQueryContext searchQueryContext) {
		AssetMetricRepository assetMetricRepository = _getAssetMetricRepository(
			searchQueryContext.getAssetType());

		return assetMetricRepository.getAssetMetricsCount(
			Long.valueOf(searchQueryContext.getChannelId()),
			searchQueryContext.getKeywords(),
			searchQueryContext.getTimeRange());
	}

	public List<Metric> getBrowserMetrics(
		MetricType metricType, SearchQueryContext searchQueryContext) {

		AssetMetricRepository assetMetricRepository = _getAssetMetricRepository(
			searchQueryContext.getAssetType());

		return assetMetricRepository.getBrowserMetrics(
			searchQueryContext.getAssetId(), searchQueryContext.getTitle(),
			Long.valueOf(searchQueryContext.getChannelId()), metricType,
			searchQueryContext.getTimeRange());
	}

	public List<Metric> getDeviceMetrics(
		MetricType metricType, SearchQueryContext searchQueryContext) {

		AssetMetricRepository assetMetricRepository = _getAssetMetricRepository(
			searchQueryContext.getAssetType());

		return assetMetricRepository.getDeviceMetrics(
			searchQueryContext.getAssetId(), searchQueryContext.getTitle(),
			Long.valueOf(searchQueryContext.getChannelId()), metricType,
			searchQueryContext.getTimeRange());
	}

	public List<Metric> getGeolocationMetrics(
		MetricType metricType, SearchQueryContext searchQueryContext) {

		AssetMetricRepository assetMetricRepository = _getAssetMetricRepository(
			searchQueryContext.getAssetType());

		return assetMetricRepository.getGeolocationMetrics(
			searchQueryContext.getAssetId(), searchQueryContext.getTitle(),
			Long.valueOf(searchQueryContext.getChannelId()), metricType,
			searchQueryContext.getTimeRange());
	}

	private AssetMetricRepository _getAssetMetricRepository(
		AssetType assetType) {

		AssetMetricRepository assetMetricRepository =
			_assetMetricRepositoryMap.get(assetType);

		if (assetMetricRepository == null) {
			throw new IllegalArgumentException(
				"There is no asset metric repository for asset type " +
					assetType);
		}

		return assetMetricRepository;
	}

	private <T extends AssetMetric> List<T> _getAssetMetrics(
		int page, SearchQueryContext searchQueryContext,
		Set<String> selectedMetrics, int size, Sort sort) {

		AssetMetricRepository<T> assetMetricRepository =
			(AssetMetricRepository<T>)_getAssetMetricRepository(
				searchQueryContext.getAssetType());

		return assetMetricRepository.getAssetMetrics(
			Long.valueOf(searchQueryContext.getChannelId()),
			searchQueryContext.getKeywords(), PageRequest.of(page, size, sort),
			selectedMetrics, searchQueryContext.getTimeRange());
	}

	private final Map<AssetType, AssetMetricRepository>
		_assetMetricRepositoryMap = new HashMap<>();

}