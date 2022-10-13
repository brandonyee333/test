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

		CohortMetric siteMetric = (CohortMetric)obj;

		if (Objects.equals(
				_anonymousVisitorsMetric,
				siteMetric._anonymousVisitorsMetric) &&
			Objects.equals(
				_knownVisitorsMetric, siteMetric._knownVisitorsMetric) &&
			Objects.equals(_visitorsMetric, siteMetric._visitorsMetric)) {

			return true;
		}

		return false;
	}

	public List<CohortHeatMapMetric> getAnonymousVisitorsMetric() {
		return _anonymousVisitorsMetric;
	}

	public List<CohortHeatMapMetric> getKnownVisitorsMetric() {
		return _knownVisitorsMetric;
	}

	public List<CohortHeatMapMetric> getVisitorsMetric() {
		return _visitorsMetric;
	}

	@Override
	public int hashCode() {
		return Objects.hash(
			_anonymousVisitorsMetric, _knownVisitorsMetric, _visitorsMetric);
	}

	public void setAnonymousVisitorsMetric(
		List<CohortHeatMapMetric> anonymousVisitorsMetric) {

		_anonymousVisitorsMetric = anonymousVisitorsMetric;
	}

	public void setKnownVisitorsMetric(
		List<CohortHeatMapMetric> knownVisitorsMetric) {

		_knownVisitorsMetric = knownVisitorsMetric;
	}

	public void setVisitorsMetric(List<CohortHeatMapMetric> visitorsMetric) {
		_visitorsMetric = visitorsMetric;
	}

	private List<CohortHeatMapMetric> _anonymousVisitorsMetric =
		new ArrayList<>();
	private List<CohortHeatMapMetric> _knownVisitorsMetric = new ArrayList<>();
	private List<CohortHeatMapMetric> _visitorsMetric = new ArrayList<>();

}