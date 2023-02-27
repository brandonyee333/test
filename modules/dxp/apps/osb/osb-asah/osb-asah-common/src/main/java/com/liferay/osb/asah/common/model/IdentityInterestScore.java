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

package com.liferay.osb.asah.common.model;

import com.liferay.osb.asah.common.entity.BQIdentityInterestScore;

import java.util.Date;
import java.util.Objects;

/**
 * @author Ivica Cardic
 */
public class IdentityInterestScore {

	public IdentityInterestScore(
		BQIdentityInterestScore bqIdentityInterestScore, String individualId) {

		_individualId = individualId;

		_id = bqIdentityInterestScore.getId();
		_identityId = bqIdentityInterestScore.getIdentityId();
		_interestScore = bqIdentityInterestScore.getInterestScore();
		_keyword = bqIdentityInterestScore.getKeyword();
		_recordedDate = bqIdentityInterestScore.getRecordedDate();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof IdentityInterestScore)) {
			return false;
		}

		IdentityInterestScore identityInterestScore =
			(IdentityInterestScore)obj;

		if (Objects.equals(_id, identityInterestScore._id)) {
			return true;
		}

		return false;
	}

	public String getId() {
		return _id;
	}

	public String getIdentityId() {
		return _identityId;
	}

	public String getIndividualId() {
		return _individualId;
	}

	public Double getInterestScore() {
		return _interestScore;
	}

	public String getKeyword() {
		return _keyword;
	}

	public Date getRecordedDate() {
		if (_recordedDate == null) {
			return null;
		}

		return new Date(_recordedDate.getTime());
	}

	@Override
	public int hashCode() {
		return Objects.hash(_id);
	}

	private final String _id;
	private final String _identityId;
	private final String _individualId;
	private final Double _interestScore;
	private final String _keyword;
	private final Date _recordedDate;

}