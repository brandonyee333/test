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

import java.util.Objects;

/**
 * @author Marcellus Tavares
 */
public class MembershipCountSnapshot {

	public MembershipCountSnapshot(
		long identitiesCount, long individualsCount, long segmentId) {

		_identitiesCount = identitiesCount;
		_individualsCount = individualsCount;
		_segmentId = segmentId;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if ((obj == null) || !(obj instanceof MembershipCountSnapshot)) {
			return false;
		}

		MembershipCountSnapshot membershipCountSnapshot =
			(MembershipCountSnapshot)obj;

		if (Objects.equals(
				_identitiesCount, membershipCountSnapshot._identitiesCount) &&
			Objects.equals(
				_individualsCount, membershipCountSnapshot._individualsCount) &&
			Objects.equals(_segmentId, membershipCountSnapshot._segmentId)) {

			return true;
		}

		return false;
	}

	public long getIdentitiesCount() {
		return _identitiesCount;
	}

	public long getIndividualsCount() {
		return _individualsCount;
	}

	public long getSegmentId() {
		return _segmentId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(_identitiesCount, _individualsCount, _segmentId);
	}

	private final long _identitiesCount;
	private final long _individualsCount;
	private final long _segmentId;

}