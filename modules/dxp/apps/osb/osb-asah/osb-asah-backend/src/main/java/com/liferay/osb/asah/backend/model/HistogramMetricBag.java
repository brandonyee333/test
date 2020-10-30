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

		this.asymmetricComparison = asymmetricComparison;
		this.metrics = metrics;
		this.total = total;
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

		if (Objects.equals(metrics, histogramMetricBag.metrics) &&
			Objects.equals(
				asymmetricComparison,
				histogramMetricBag.asymmetricComparison) &&
			Objects.equals(total, histogramMetricBag.total)) {

			return true;
		}

		return false;
	}

	public List<HistogramMetric> getMetrics() {
		return metrics;
	}

	public long getTotal() {
		return total;
	}

	@Override
	public int hashCode() {
		return Objects.hash(metrics, asymmetricComparison, total);
	}

	public boolean isAsymmetricComparison() {
		return asymmetricComparison;
	}

	public void setAsymmetricComparison(boolean asymmetricComparison) {
		this.asymmetricComparison = asymmetricComparison;
	}

	public void setHistogramMetrics(List<HistogramMetric> histogramMetrics) {
		this.metrics = histogramMetrics;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	protected boolean asymmetricComparison;
	protected List<HistogramMetric> metrics = new ArrayList<>();
	protected long total;

}