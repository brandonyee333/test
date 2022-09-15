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
import com.liferay.osb.asah.backend.repository.AssetMetricRepository;
import com.liferay.osb.asah.common.model.MetricType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component("com.liferay.osb.asah.backend.dog.UserDog")
public class UserDog {

	@Autowired
	public UserDog(List<AssetMetricRepository> assetMetricRepositories) {
		assetMetricRepositories.forEach(
			assetMetricAssetMetricRepository -> _assetMetricRepositoryMap.put(
				assetMetricAssetMetricRepository.getAssetType(),
				assetMetricAssetMetricRepository));
	}

	public Long getAnonymousUsersCount(
		MetricType metricType, SearchQueryContext searchQueryContext) {

		AssetMetricRepository assetMetricRepository = _getAssetMetricRepository(
			searchQueryContext.getAssetType());

		return assetMetricRepository.getAnonymousIndividualsCount(
			searchQueryContext.getAssetId(), searchQueryContext.getTitle(),
			Long.valueOf(searchQueryContext.getChannelId()), metricType,
			searchQueryContext.getTimeRange());
	}

	public Long getKnownUsersCount(
		MetricType metricType, SearchQueryContext searchQueryContext) {

		AssetMetricRepository assetMetricRepository = _getAssetMetricRepository(
			searchQueryContext.getAssetType());

		return assetMetricRepository.getKnownIndividualsCount(
			searchQueryContext.getAssetId(), searchQueryContext.getTitle(),
			Long.valueOf(searchQueryContext.getChannelId()), metricType, null,
			searchQueryContext.getTimeRange());
	}

	public Long getSegmentedIndividualsCount(
		MetricType metricType, SearchQueryContext searchQueryContext) {

		AssetMetricRepository assetMetricRepository = _getAssetMetricRepository(
			searchQueryContext.getAssetType());

		return assetMetricRepository.getSegmentedIndividualsCount(
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