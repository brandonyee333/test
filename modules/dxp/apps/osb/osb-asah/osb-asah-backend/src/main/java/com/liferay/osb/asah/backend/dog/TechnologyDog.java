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
import com.liferay.osb.asah.backend.model.AssetType;
import com.liferay.osb.asah.backend.model.Metric;
import com.liferay.osb.asah.backend.repository.AssetMetricRepository;
import com.liferay.osb.asah.common.model.MetricType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Inácio Nery
 */
@Component
public class TechnologyDog {

	@Autowired
	public TechnologyDog(List<AssetMetricRepository> assetMetricRepositories) {
		assetMetricRepositories.forEach(
			assetMetricAssetMetricRepository -> _assetMetricRepositoryMap.put(
				assetMetricAssetMetricRepository.getAssetType(),
				assetMetricAssetMetricRepository));
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

	private final Map<AssetType, AssetMetricRepository>
		_assetMetricRepositoryMap = new HashMap<>();

}