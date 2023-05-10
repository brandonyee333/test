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
import com.liferay.osb.asah.common.dog.SegmentDog;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.model.MetricType;
import com.liferay.osb.asah.common.model.ResultBag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
public class SegmentMetricDog {

	@Autowired
	public SegmentMetricDog(
		List<AssetMetricRepository> assetMetricRepositories) {

		assetMetricRepositories.forEach(
			assetMetricAssetMetricRepository -> _assetMetricRepositoryMap.put(
				assetMetricAssetMetricRepository.getAssetType(),
				assetMetricAssetMetricRepository));
	}

	public ResultBag<Metric> getSegmentMetricResultBag(
		MetricType metricType, SearchQueryContext searchQueryContext) {

		AssetMetricRepository assetMetricRepository =
			_assetMetricRepositoryMap.get(searchQueryContext.getAssetType());

		if (assetMetricRepository == null) {
			throw new IllegalArgumentException(
				"There is no asset metric repository for asset type " +
					searchQueryContext.getAssetType());
		}

		List<Metric> segmentMetrics = assetMetricRepository.getSegmentMetrics(
			searchQueryContext.getAssetId(), searchQueryContext.getTitle(),
			searchQueryContext.getChannelIdAsLong(), metricType,
			searchQueryContext.getTimeRange());

		Double othersMetricValue = 0D;

		for (int i = 0; i < segmentMetrics.size(); i++) {
			Metric metric = segmentMetrics.get(i);

			if (i >= 14) {
				othersMetricValue += metric.getValue();

				continue;
			}

			Segment segment = _segmentDog.fetchSegment(
				Long.valueOf(metric.getValueKey()));

			if (segment != null) {
				metric.setValueKey(segment.getName());
			}
		}

		if (segmentMetrics.size() > 14) {
			segmentMetrics = new ArrayList<>(segmentMetrics.subList(0, 14));

			segmentMetrics.add(
				Metric.of(metricType, othersMetricValue, "others"));
		}

		return new ResultBag<>(segmentMetrics, segmentMetrics.size());
	}

	private final Map<AssetType, AssetMetricRepository>
		_assetMetricRepositoryMap = new HashMap<>();

	@Autowired
	private SegmentDog _segmentDog;

}