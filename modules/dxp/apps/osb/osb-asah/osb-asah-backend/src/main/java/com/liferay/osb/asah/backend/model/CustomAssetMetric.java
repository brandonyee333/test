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

package com.liferay.osb.asah.backend.model;

import java.util.List;
import java.util.Objects;

/**
 * @author Marcellus Tavares
 */
public class CustomAssetMetric implements AssetMetric {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CustomAssetMetric)) {
			return false;
		}

		CustomAssetMetric customAssetMetric = (CustomAssetMetric)obj;

		if (Objects.equals(
				_abandonmentsMetric, customAssetMetric._abandonmentsMetric) &&
			Objects.equals(_assetId, customAssetMetric._assetId) &&
			Objects.equals(_assetMetrics, customAssetMetric._assetMetrics) &&
			Objects.equals(_assetTitle, customAssetMetric._assetTitle) &&
			Objects.equals(_clicksMetric, customAssetMetric._clicksMetric) &&
			Objects.equals(
				_completionTimeMetric,
				customAssetMetric._completionTimeMetric) &&
			Objects.equals(_dataSourceId, customAssetMetric._dataSourceId) &&
			Objects.equals(
				_readingTimeMetric, customAssetMetric._readingTimeMetric) &&
			Objects.equals(
				_submissionsMetric, customAssetMetric._submissionsMetric) &&
			Objects.equals(_urls, customAssetMetric._urls) &&
			Objects.equals(_viewsMetric, customAssetMetric._viewsMetric)) {

			return true;
		}

		return false;
	}

	public Metric getAbandonmentsMetric() {
		return _abandonmentsMetric;
	}

	@Override
	public String getAssetId() {
		return _assetId;
	}

	@Override
	public List<AssetMetric> getAssetMetrics() {
		return _assetMetrics;
	}

	@Override
	public String getAssetTitle() {
		return _assetTitle;
	}

	@Override
	public String getAssetType() {
		return AssetType.CUSTOM.getValue();
	}

	public Metric getClicksMetric() {
		return _clicksMetric;
	}

	public Metric getCompletionTimeMetric() {
		return _completionTimeMetric;
	}

	@Override
	public String getDataSourceId() {
		return _dataSourceId;
	}

	@Override
	public Metric getDefaultMetric() {
		return _viewsMetric;
	}

	public Metric getDownloadsMetric() {
		return _downloadsMetric;
	}

	public Metric getReadingTimeMetric() {
		return _readingTimeMetric;
	}

	public Metric getSubmissionsMetric() {
		return _submissionsMetric;
	}

	@Override
	public List<String> getURLs() {
		return _urls;
	}

	public Metric getViewsMetric() {
		return _viewsMetric;
	}

	@Override
	public int hashCode() {
		return Objects.hash(
			_abandonmentsMetric, _assetId, _assetMetrics, _assetTitle,
			_clicksMetric, _completionTimeMetric, _dataSourceId,
			_readingTimeMetric, _submissionsMetric, _urls, _viewsMetric);
	}

	public void setAbandonmentsMetric(Metric abandonmentsMetric) {
		_abandonmentsMetric = abandonmentsMetric;
	}

	@Override
	public void setAssetId(String assetId) {
		_assetId = assetId;
	}

	@Override
	public void setAssetMetrics(List<AssetMetric> assetMetrics) {
		_assetMetrics = assetMetrics;
	}

	@Override
	public void setAssetTitle(String assetTitle) {
		_assetTitle = assetTitle;
	}

	public void setClicksMetric(Metric clicksMetric) {
		_clicksMetric = clicksMetric;
	}

	public void setCompletionTimeMetric(Metric completionTimeMetric) {
		_completionTimeMetric = completionTimeMetric;
	}

	@Override
	public void setDataSourceId(String dataSourceId) {
		_dataSourceId = dataSourceId;
	}

	public void setDownloadsMetric(Metric downloadsMetric) {
		_downloadsMetric = downloadsMetric;
	}

	public void setReadingTimeMetric(Metric readingTimeMetric) {
		_readingTimeMetric = readingTimeMetric;
	}

	public void setSubmissionsMetric(Metric submissionsMetric) {
		_submissionsMetric = submissionsMetric;
	}

	@Override
	public void setURLs(List<String> urls) {
		_urls = urls;
	}

	public void setViewsMetric(Metric viewsMetric) {
		_viewsMetric = viewsMetric;
	}

	private Metric _abandonmentsMetric = new Metric(
		CustomAssetMetricType.ABANDONMENTS);
	private String _assetId;
	private List<AssetMetric> _assetMetrics;
	private String _assetTitle;
	private Metric _clicksMetric = new Metric(CustomAssetMetricType.CLICKS);
	private Metric _completionTimeMetric = new Metric(
		CustomAssetMetricType.COMPLETION_TIME);
	private String _dataSourceId;
	private Metric _downloadsMetric = new Metric(
		CustomAssetMetricType.DOWNLOADS);
	private Metric _readingTimeMetric = new Metric(
		CustomAssetMetricType.READING_TIME);
	private Metric _submissionsMetric = new Metric(
		CustomAssetMetricType.SUBMISSIONS);
	private List<String> _urls;
	private Metric _viewsMetric = new Metric(CustomAssetMetricType.VIEWS);

}