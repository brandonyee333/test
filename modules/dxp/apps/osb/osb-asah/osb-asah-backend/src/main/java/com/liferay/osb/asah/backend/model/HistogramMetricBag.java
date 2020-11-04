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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Leslie Wong
 */
public class HistogramMetricBag {

	public HistogramMetricBag() {
	}

	public HistogramMetricBag(
		boolean asymmetricComparison, List<HistogramMetric> metrics,
		long total) {

		_asymmetricComparison = asymmetricComparison;
		_metrics = metrics;
		_total = total;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof HistogramMetricBag)) {
			return false;
		}

		HistogramMetricBag histogramMetricBag = (HistogramMetricBag)obj;

		if (Objects.equals(_metrics, histogramMetricBag._metrics) &&
			Objects.equals(
				_asymmetricComparison,
				histogramMetricBag._asymmetricComparison) &&
			Objects.equals(_total, histogramMetricBag._total)) {

			return true;
		}

		return false;
	}

	public List<HistogramMetric> getMetrics() {
		return _metrics;
	}

	public long getTotal() {
		return _total;
	}

	@Override
	public int hashCode() {
		return Objects.hash(_metrics, _asymmetricComparison, _total);
	}

	public boolean isAsymmetricComparison() {
		return _asymmetricComparison;
	}

	public void setAsymmetricComparison(boolean asymmetricComparison) {
		_asymmetricComparison = asymmetricComparison;
	}

	public void setHistogramMetrics(List<HistogramMetric> histogramMetrics) {
		_metrics = histogramMetrics;
	}

	public void setTotal(long total) {
		_total = total;
	}

	private boolean _asymmetricComparison;
	private List<HistogramMetric> _metrics = new ArrayList<>();
	private long _total;

}