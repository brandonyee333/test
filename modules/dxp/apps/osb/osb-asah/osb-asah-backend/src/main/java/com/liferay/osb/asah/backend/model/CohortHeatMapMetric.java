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
 * @author Rachael Koestartyo
 */
public class CohortHeatMapMetric extends HeatMapMetric {

	public CohortHeatMapMetric(
		String colDimension, Metric metric, Double retention,
		String rowDimension, String rowKey) {

		super(colDimension, metric, rowDimension);

		_retention = retention;
		_rowKey = rowKey;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!super.equals(obj)) {
			return false;
		}

		if (!(obj instanceof CohortHeatMapMetric)) {
			return false;
		}

		CohortHeatMapMetric cohortHeatMapMetric = (CohortHeatMapMetric)obj;

		if (super.equalsMetric(cohortHeatMapMetric) &&
			Objects.equals(_retention, cohortHeatMapMetric._retention) &&
			Objects.equals(_rowKey, cohortHeatMapMetric._rowKey)) {

			return true;
		}

		return false;
	}

	public Double getRetention() {
		return _retention;
	}

	public String getRowKey() {
		return _rowKey;
	}

	@Override
	public int hashCode() {
		return super.hashCode() ^ Objects.hash(_retention, _rowKey);
	}

	public void setRetention(Double retention) {
		_retention = retention;
	}

	public void setRowKey(String rowKey) {
		_rowKey = rowKey;
	}

	private Double _retention;
	private String _rowKey;

}