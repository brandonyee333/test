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
 * @author Inácio Nery
 */
public class DocumentLibraryMetric implements AssetMetric {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DocumentLibraryMetric)) {
			return false;
		}

		DocumentLibraryMetric documentLibraryMetric =
			(DocumentLibraryMetric)obj;

		if (Objects.equals(_assetId, documentLibraryMetric._assetId) &&
			Objects.equals(
				_assetMetrics, documentLibraryMetric._assetMetrics) &&
			Objects.equals(_assetTitle, documentLibraryMetric._assetTitle) &&
			Objects.equals(
				_commentsMetric, documentLibraryMetric._commentsMetric) &&
			Objects.equals(
				_dataSourceId, documentLibraryMetric._dataSourceId) &&
			Objects.equals(
				_downloadsMetric, documentLibraryMetric._downloadsMetric) &&
			Objects.equals(
				_previewsMetric, documentLibraryMetric._previewsMetric) &&
			Objects.equals(
				_ratingsMetric, documentLibraryMetric._ratingsMetric) &&
			Objects.equals(_urls, documentLibraryMetric._urls)) {

			return true;
		}

		return false;
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
		return AssetType.DOCUMENT.getValue();
	}

	public Metric getCommentsMetric() {
		return _commentsMetric;
	}

	@Override
	public String getDataSourceId() {
		return _dataSourceId;
	}

	@Override
	public Metric getDefaultMetric() {
		return _downloadsMetric;
	}

	public Metric getDownloadsMetric() {
		return _downloadsMetric;
	}

	public Metric getPreviewsMetric() {
		return _previewsMetric;
	}

	public Metric getRatingsMetric() {
		return _ratingsMetric;
	}

	@Override
	public List<String> getURLs() {
		return _urls;
	}

	@Override
	public int hashCode() {
		return Objects.hash(
			_assetId, _assetMetrics, _assetTitle, _commentsMetric,
			_dataSourceId, _downloadsMetric, _previewsMetric, _ratingsMetric,
			_urls);
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

	public void setCommentsMetric(Metric commentsMetric) {
		_commentsMetric = commentsMetric;
	}

	@Override
	public void setDataSourceId(String dataSourceId) {
		_dataSourceId = dataSourceId;
	}

	public void setDownloadsMetric(Metric downloadsMetric) {
		_downloadsMetric = downloadsMetric;
	}

	public void setPreviewsMetric(Metric previewsMetric) {
		_previewsMetric = previewsMetric;
	}

	public void setRatingsMetric(Metric ratingsMetric) {
		_ratingsMetric = ratingsMetric;
	}

	@Override
	public void setURLs(List<String> urls) {
		_urls = urls;
	}

	private String _assetId;
	private List<AssetMetric> _assetMetrics;
	private String _assetTitle;
	private Metric _commentsMetric = new Metric(
		DocumentLibraryMetricType.COMMENTS);
	private String _dataSourceId;
	private Metric _downloadsMetric = new Metric(
		DocumentLibraryMetricType.DOWNLOADS);
	private Metric _previewsMetric = new Metric(
		DocumentLibraryMetricType.PREVIEWS);
	private Metric _ratingsMetric = new Metric(
		DocumentLibraryMetricType.RATINGS);
	private List<String> _urls;

}