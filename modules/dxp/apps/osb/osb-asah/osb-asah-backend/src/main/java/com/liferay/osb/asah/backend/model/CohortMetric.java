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
 * @author Thiago Buarque
 */
public class CohortMetric {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CohortMetric)) {
			return false;
		}

		CohortMetric cohortMetric = (CohortMetric)obj;

		if (Objects.equals(
				_anonymousCohortHeatMapMetrics,
				cohortMetric._anonymousCohortHeatMapMetrics) &&
			Objects.equals(
				_knownCohortHeatMapMetrics,
				cohortMetric._knownCohortHeatMapMetrics) &&
			Objects.equals(
				_visitorsCohortHeatMapMetrics,
				cohortMetric._visitorsCohortHeatMapMetrics)) {

			return true;
		}

		return false;
	}

	public List<CohortHeatMapMetric> getAnonymousCohortHeatMapMetrics() {
		return _anonymousCohortHeatMapMetrics;
	}

	public List<CohortHeatMapMetric> getKnownCohortHeatMapMetrics() {
		return _knownCohortHeatMapMetrics;
	}

	public List<CohortHeatMapMetric> getVisitorsCohortHeatMapMetrics() {
		return _visitorsCohortHeatMapMetrics;
	}

	@Override
	public int hashCode() {
		return Objects.hash(
			_anonymousCohortHeatMapMetrics, _knownCohortHeatMapMetrics,
			_visitorsCohortHeatMapMetrics);
	}

	public void setAnonymousCohortHeatMapMetrics(
		List<CohortHeatMapMetric> anonymousCohortHeatMapMetrics) {

		_anonymousCohortHeatMapMetrics = anonymousCohortHeatMapMetrics;
	}

	public void setKnownCohortHeatMapMetrics(
		List<CohortHeatMapMetric> knownCohortHeatMapMetrics) {

		_knownCohortHeatMapMetrics = knownCohortHeatMapMetrics;
	}

	public void setVisitorsCohortHeatMapMetrics(
		List<CohortHeatMapMetric> visitorsCohortHeatMapMetrics) {

		_visitorsCohortHeatMapMetrics = visitorsCohortHeatMapMetrics;
	}

	private List<CohortHeatMapMetric> _anonymousCohortHeatMapMetrics =
		new ArrayList<>();
	private List<CohortHeatMapMetric> _knownCohortHeatMapMetrics =
		new ArrayList<>();
	private List<CohortHeatMapMetric> _visitorsCohortHeatMapMetrics =
		new ArrayList<>();

}