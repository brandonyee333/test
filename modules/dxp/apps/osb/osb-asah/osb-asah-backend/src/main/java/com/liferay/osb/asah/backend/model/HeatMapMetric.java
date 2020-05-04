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
 * @author Leslie Wong
 */
public class HeatMapMetric extends Metric {

	public HeatMapMetric(
		String colDimension, Metric metric, String rowDimension) {

		super(metric.getMetricType());

		_colDimension = colDimension;
		_rowDimension = rowDimension;

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

		if (!(obj instanceof HeatMapMetric)) {
			return false;
		}

		HeatMapMetric heatMapMetric = (HeatMapMetric)obj;

		if (super.equalsMetric(heatMapMetric) &&
			Objects.equals(_colDimension, heatMapMetric._colDimension) &&
			Objects.equals(_rowDimension, heatMapMetric._rowDimension)) {

			return true;
		}

		return false;
	}

	public String getColDimension() {
		return _colDimension;
	}

	public String getRowDimension() {
		return _rowDimension;
	}

	@Override
	public int hashCode() {
		return super.hashCode() ^ Objects.hash(_colDimension, _rowDimension);
	}

	private final String _colDimension;
	private final String _rowDimension;

}