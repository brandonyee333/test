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

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Inácio Nery
 */
public class PageMetric implements AssetMetric {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PageMetric)) {
			return false;
		}

		PageMetric pageMetric = (PageMetric)obj;

		if (Objects.equals(_assetMetrics, pageMetric._assetMetrics) &&
			Objects.equals(_assetTitle, pageMetric._assetTitle) &&
			Objects.equals(
				_avgTimeOnPageMetric, pageMetric._avgTimeOnPageMetric) &&
			Objects.equals(_bounceMetric, pageMetric._bounceMetric) &&
			Objects.equals(_bounceRateMetric, pageMetric._bounceRateMetric) &&
			Objects.equals(_canonicalUrls, pageMetric._canonicalUrls) &&
			Objects.equals(_ctpMetric, pageMetric._ctpMetric) &&
			Objects.equals(_ctrMetric, pageMetric._ctrMetric) &&
			Objects.equals(_dataSourceId, pageMetric._dataSourceId) &&
			Objects.equals(
				_directAccessMetric, pageMetric._directAccessMetric) &&
			Objects.equals(_engagementMetric, pageMetric._engagementMetric) &&
			Objects.equals(_entrancesMetric, pageMetric._entrancesMetric) &&
			Objects.equals(_exitRateMetric, pageMetric._exitRateMetric) &&
			Objects.equals(
				_indirectAccessMetric, pageMetric._indirectAccessMetric) &&
			Objects.equals(
				_maxScrollDepthMetric, pageMetric._maxScrollDepthMetric) &&
			Objects.equals(_readsMetric, pageMetric._readsMetric) &&
			Objects.equals(_sessionsMetric, pageMetric._sessionsMetric) &&
			Objects.equals(_timeOnPageMetric, pageMetric._timeOnPageMetric) &&
			Objects.equals(_urls, pageMetric._urls) &&
			Objects.equals(_viewsMetric, pageMetric._viewsMetric) &&
			Objects.equals(_visitorsMetric, pageMetric._visitorsMetric)) {

			return true;
		}

		return false;
	}

	@Override
	public String getAssetId() {
		if ((_canonicalUrls == null) || _canonicalUrls.isEmpty()) {
			return null;
		}

		return _canonicalUrls.get(0);
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
		return AssetType.PAGE.getValue();
	}

	public Metric getAvgTimeOnPageMetric() {
		return _avgTimeOnPageMetric;
	}

	public Metric getBounceMetric() {
		return _bounceMetric;
	}

	public Metric getBounceRateMetric() {
		return _bounceRateMetric;
	}

	@Override
	public List<String> getCanonicalUrls() {
		return _canonicalUrls;
	}

	public Metric getCTPMetric() {
		return _ctpMetric;
	}

	public Metric getCTRMetric() {
		return _ctrMetric;
	}

	@Override
	public String getDataSourceId() {
		return _dataSourceId;
	}

	@Override
	public Metric getDefaultMetric() {
		return _viewsMetric;
	}

	public Metric getDirectAccessMetric() {
		return _directAccessMetric;
	}

	public Metric getEngagementMetric() {
		return _engagementMetric;
	}

	public Metric getEntrancesMetric() {
		return _entrancesMetric;
	}

	public Metric getExitRateMetric() {
		return _exitRateMetric;
	}

	public Metric getIndirectAccessMetric() {
		return _indirectAccessMetric;
	}

	public Metric getMaxScrollDepthMetric() {
		return _maxScrollDepthMetric;
	}

	public Metric getReadsMetric() {
		return _readsMetric;
	}

	public Metric getSessionsMetric() {
		return _sessionsMetric;
	}

	public Metric getTimeOnPageMetric() {
		return _timeOnPageMetric;
	}

	@Override
	public List<String> getURLs() {
		return _urls;
	}

	public Metric getViewsMetric() {
		return _viewsMetric;
	}

	public Metric getVisitorsMetric() {
		return _visitorsMetric;
	}

	@Override
	public int hashCode() {
		return Objects.hash(
			_assetMetrics, _assetTitle, _avgTimeOnPageMetric, _bounceMetric,
			_bounceRateMetric, _canonicalUrls, _ctpMetric, _ctrMetric,
			_dataSourceId, _directAccessMetric, _engagementMetric,
			_entrancesMetric, _exitRateMetric, _indirectAccessMetric,
			_maxScrollDepthMetric, _readsMetric, _sessionsMetric,
			_timeOnPageMetric, _urls, _viewsMetric, _visitorsMetric);
	}

	@Override
	public void setAssetId(String assetId) {
		_canonicalUrls = Collections.singletonList(assetId);
	}

	@Override
	public void setAssetMetrics(List<AssetMetric> assetMetrics) {
		_assetMetrics = assetMetrics;
	}

	@Override
	public void setAssetTitle(String assetTitle) {
		_assetTitle = assetTitle;
	}

	public void setAvgTimeOnPageMetric(Metric avgTimeOnPageMetric) {
		_avgTimeOnPageMetric = avgTimeOnPageMetric;
	}

	public void setBounceMetric(Metric bounceMetric) {
		_bounceMetric = bounceMetric;
	}

	public void setBounceRateMetric(Metric bounceRateMetric) {
		double value = bounceRateMetric.getValue();

		if (value > 1) {
			_log.error(
				_buildRateErrorMessage(
					PageMetricType.BOUNCE_RATE, false, value));

			bounceRateMetric.setValue(1.0);
		}

		Double previousValue = bounceRateMetric.getPreviousValue();

		if ((previousValue != null) && (previousValue > 1)) {
			_log.error(
				_buildRateErrorMessage(
					PageMetricType.BOUNCE_RATE, true, previousValue));

			bounceRateMetric.setPreviousValue(1.0);
		}

		_bounceRateMetric = bounceRateMetric;
	}

	@Override
	public void setCanonicalUrls(List<String> canonicalUrls) {
		_canonicalUrls = canonicalUrls;
	}

	public void setCTPMetric(Metric ctpMetric) {
		_ctpMetric = ctpMetric;
	}

	public void setCTRMetric(Metric ctrMetric) {
		_ctrMetric = ctrMetric;
	}

	@Override
	public void setDataSourceId(String dataSourceId) {
		_dataSourceId = dataSourceId;
	}

	public void setDirectAccessMetric(Metric directAccessMetric) {
		_directAccessMetric = directAccessMetric;
	}

	public void setEngagementMetric(Metric engagementMetric) {
		_engagementMetric = engagementMetric;
	}

	public void setEntrancesMetric(Metric entrancesMetric) {
		_entrancesMetric = entrancesMetric;
	}

	public void setExitRateMetric(Metric exitRateMetric) {
		double value = exitRateMetric.getValue();

		if (value > 1) {
			_log.error(
				_buildRateErrorMessage(PageMetricType.EXIT_RATE, false, value));

			exitRateMetric.setValue(1.0);
		}

		Double previousValue = exitRateMetric.getPreviousValue();

		if ((previousValue != null) && (previousValue > 1)) {
			_log.error(
				_buildRateErrorMessage(
					PageMetricType.EXIT_RATE, true, previousValue));

			exitRateMetric.setPreviousValue(1.0);
		}

		_exitRateMetric = exitRateMetric;
	}

	public void setIndirectAccessMetric(Metric indirectAccessMetric) {
		_indirectAccessMetric = indirectAccessMetric;
	}

	public void setMaxScrollDepthMetric(Metric maxScrollDepthMetric) {
		_maxScrollDepthMetric = maxScrollDepthMetric;
	}

	public void setReadsMetric(Metric readsMetric) {
		_readsMetric = readsMetric;
	}

	public void setSessionsMetric(Metric sessionsMetric) {
		_sessionsMetric = sessionsMetric;
	}

	public void setTimeOnPageMetric(Metric timeOnPageMetric) {
		_timeOnPageMetric = timeOnPageMetric;
	}

	@Override
	public void setURLs(List<String> urls) {
		_urls = urls;
	}

	public void setViewsMetric(Metric viewsMetric) {
		_viewsMetric = viewsMetric;
	}

	public void setVisitorsMetric(Metric visitorsMetric) {
		_visitorsMetric = visitorsMetric;
	}

	private String _buildRateErrorMessage(
		PageMetricType pageMetricType, boolean previous, double value) {

		StringBuilder sb = new StringBuilder();

		if (previous) {
			sb.append("Previous ");
		}

		sb.append(pageMetricType.getName());
		sb.append(" for page with title ");
		sb.append(_assetTitle);

		if (CollectionUtils.isNotEmpty(_canonicalUrls)) {
			sb.append(", canonical URL ");
			sb.append(_canonicalUrls.get(0));
		}

		if (CollectionUtils.isNotEmpty(_urls)) {
			sb.append(" and URL ");
			sb.append(_urls.get(0));
		}

		sb.append(" was ");
		sb.append(value);

		return sb.toString();
	}

	private static final Log _log = LogFactory.getLog(PageMetric.class);

	private List<AssetMetric> _assetMetrics;
	private String _assetTitle;
	private Metric _avgTimeOnPageMetric = new Metric(
		PageMetricType.AVG_TIME_ON_PAGE);
	private Metric _bounceMetric = new Metric(PageMetricType.BOUNCE);
	private Metric _bounceRateMetric = new Metric(PageMetricType.BOUNCE_RATE);
	private List<String> _canonicalUrls;
	private Metric _ctpMetric = new Metric(
		PageMetricType.CLICK_THROUGH_PROBABILITY);
	private Metric _ctrMetric = new Metric(PageMetricType.CLICK_THROUGH_RATE);
	private String _dataSourceId;
	private Metric _directAccessMetric = new Metric(
		PageMetricType.DIRECT_ACCESS);
	private Metric _engagementMetric = new Metric(PageMetricType.ENGAGEMENT);
	private Metric _entrancesMetric = new Metric(PageMetricType.ENTRANCES);
	private Metric _exitRateMetric = new Metric(PageMetricType.EXIT_RATE);
	private Metric _indirectAccessMetric = new Metric(
		PageMetricType.INDIRECT_ACCESS);
	private Metric _maxScrollDepthMetric = new Metric(
		PageMetricType.MAX_SCROLL_DEPTH);
	private Metric _readsMetric = new Metric(PageMetricType.READS);
	private Metric _sessionsMetric = new Metric(PageMetricType.SESSIONS);
	private Metric _timeOnPageMetric = new Metric(PageMetricType.TIME_ON_PAGE);
	private List<String> _urls;
	private Metric _viewsMetric = new Metric(PageMetricType.VIEWS);
	private Metric _visitorsMetric = new Metric(PageMetricType.VISITORS);

}