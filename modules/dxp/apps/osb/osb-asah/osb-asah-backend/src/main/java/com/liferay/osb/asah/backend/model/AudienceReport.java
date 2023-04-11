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
 * @author Robson Pastor
 */
public class AudienceReport {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AudienceReport)) {
			return false;
		}

		AudienceReport audienceReport = (AudienceReport)obj;

		if (Objects.equals(
				_anonymousIndividualsCount,
				audienceReport._anonymousIndividualsCount) &&
			Objects.equals(
				_knownIndividualsCount,
				audienceReport._knownIndividualsCount) &&
			Objects.equals(
				_nonsegmentedIndividualsCount,
				audienceReport._nonsegmentedIndividualsCount) &&
			Objects.equals(
				_segmentedIndividualsCount,
				audienceReport._segmentedIndividualsCount)) {

			return true;
		}

		return false;
	}

	public Long getAnonymousIndividualsCount() {
		return _anonymousIndividualsCount;
	}

	public Long getKnownIndividualsCount() {
		return _knownIndividualsCount;
	}

	public Long getNonsegmentedIndividualsCount() {
		return _nonsegmentedIndividualsCount;
	}

	public Long getSegmentedIndividualsCount() {
		return _segmentedIndividualsCount;
	}

	@Override
	public int hashCode() {
		return Objects.hash(
			_anonymousIndividualsCount, _knownIndividualsCount,
			_nonsegmentedIndividualsCount, _segmentedIndividualsCount);
	}

	public void setAnonymousIndividualsCount(Long anonymousIndividualsCount) {
		_anonymousIndividualsCount = anonymousIndividualsCount;
	}

	public void setKnownIndividualsCount(Long knownIndividualsCount) {
		_knownIndividualsCount = knownIndividualsCount;
	}

	public void setNonsegmentedIndividualsCount(
		Long nonsegmentedIndividualsCount) {

		_nonsegmentedIndividualsCount = nonsegmentedIndividualsCount;
	}

	public void setSegmentedIndividualsCount(Long segmentedIndividualsCount) {
		_segmentedIndividualsCount = segmentedIndividualsCount;
	}

	private Long _anonymousIndividualsCount;
	private Long _knownIndividualsCount;
	private Long _nonsegmentedIndividualsCount;
	private Long _segmentedIndividualsCount;

}