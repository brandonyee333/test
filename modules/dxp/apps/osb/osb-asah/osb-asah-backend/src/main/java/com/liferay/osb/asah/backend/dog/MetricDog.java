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
import com.liferay.osb.asah.common.model.Sort;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 * @author André Miranda
 */
@Component
public class MetricDog {

	@Autowired
	public MetricDog() {
	}

	public <T extends AssetMetric> T getAssetMetric(
		SearchQueryContext searchQueryContext) {

		return getAssetMetric(searchQueryContext, Collections.emptySet());
	}

	public <T extends AssetMetric> T getAssetMetric(
		SearchQueryContext searchQueryContext, Set<String> selectedMetrics) {

		return null;
	}

	public <T extends AssetMetric> List<T> getAssetMetrics(
		int count, SearchQueryContext searchQueryContext,
		Set<String> selectedMetrics, int size, Sort sort, int start) {

		return _getAssetMetrics(
			Collections.emptySet(), count, searchQueryContext, selectedMetrics,
			size, sort, start);
	}

	public <T extends AssetMetric> List<T> getAssetMetrics(
		SearchQueryContext searchQueryContext, Set<String> selectedMetrics,
		int size, Sort sort, int start) {

		return _getAssetMetrics(
			Collections.emptySet(), getAssetMetricsCount(searchQueryContext),
			searchQueryContext, selectedMetrics, size, sort, start);
	}

	public <T extends AssetMetric> List<T> getAssetMetrics(
		Set<String> assetIds, SearchQueryContext searchQueryContext,
		Set<String> selectedMetrics, int size, Sort sort, int start) {

		return _getAssetMetrics(
			assetIds, assetIds.size(), searchQueryContext, selectedMetrics,
			size, sort, start);
	}

	public int getAssetMetricsCount(SearchQueryContext searchQueryContext) {
		return 0;
	}

	private <T extends AssetMetric> List<T> _getAssetMetrics(
		Set<String> assetIds, int count, SearchQueryContext searchQueryContext,
		Set<String> selectedMetrics, int size, Sort sort, int start) {

		return Collections.emptyList();
	}

}