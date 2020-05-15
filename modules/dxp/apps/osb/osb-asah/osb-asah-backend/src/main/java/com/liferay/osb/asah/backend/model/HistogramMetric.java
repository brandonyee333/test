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

import java.util.Objects;

/**
 * @author Inácio Nery
 */
public class HistogramMetric extends Metric {

	public HistogramMetric(String key, Metric metric) {
		super(metric.getMetricType());

		_key = key;

		setPreviousValue(metric.getPreviousValue());
		setPreviousValueKey(metric.getPreviousValueKey());
		setValue(metric.getValue());
		setValueKey(metric.getValueKey());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!super.equals(obj)) {
			return false;
		}

		if (!(obj instanceof HistogramMetric)) {
			return false;
		}

		HistogramMetric histogramMetric = (HistogramMetric)obj;

		if (super.equalsMetric(histogramMetric) &&
			Objects.equals(_key, histogramMetric._key)) {

			return true;
		}

		return false;
	}

	public String getKey() {
		return _key;
	}

	@Override
	public int hashCode() {
		return super.hashCode() ^ Objects.hash(_key);
	}

	private final String _key;

}