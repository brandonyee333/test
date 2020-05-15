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

import com.fasterxml.jackson.annotation.JsonFormat;

import com.liferay.osb.asah.common.date.DateUtil;

import java.util.Date;
import java.util.Objects;

/**
 * @author Marcellus Tavares
 */
public class Segment {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Segment)) {
			return false;
		}

		Segment segment = (Segment)obj;

		if (Objects.equals(_dateCreated, segment._dateCreated) &&
			Objects.equals(_id, segment._id) &&
			Objects.equals(
				_includeAnonymousUsers, segment._includeAnonymousUsers) &&
			Objects.equals(_individualCount, segment._individualCount) &&
			Objects.equals(
				_knownIndividualCount, segment._knownIndividualCount) &&
			Objects.equals(_name, segment._name) &&
			Objects.equals(_segmentType, segment._segmentType)) {

			return true;
		}

		return false;
	}

	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	public Date getDateCreated() {
		if (_dateCreated == null) {
			return null;
		}

		return new Date(_dateCreated.getTime());
	}

	public String getId() {
		return _id;
	}

	public long getIndividualCount() {
		return _individualCount;
	}

	public long getKnownIndividualCount() {
		return _knownIndividualCount;
	}

	public String getName() {
		return _name;
	}

	public SegmentType getSegmentType() {
		return _segmentType;
	}

	@Override
	public int hashCode() {
		return Objects.hash(
			_dateCreated, _id, _includeAnonymousUsers, _individualCount,
			_knownIndividualCount, _name, _segmentType);
	}

	public boolean isIncludeAnonymousUsers() {
		return _includeAnonymousUsers;
	}

	public void setDateCreated(Date dateCreated) {
		if (dateCreated != null) {
			_dateCreated = new Date(dateCreated.getTime());
		}
	}

	public void setId(String id) {
		_id = id;
	}

	public void setIncludeAnonymousUsers(boolean includeAnonymousUsers) {
		_includeAnonymousUsers = includeAnonymousUsers;
	}

	public void setIndividualCount(long individualCount) {
		_individualCount = individualCount;
	}

	public void setKnownIndividualCount(long knownIndividualCount) {
		_knownIndividualCount = knownIndividualCount;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setSegmentType(SegmentType segmentType) {
		_segmentType = segmentType;
	}

	private Date _dateCreated;
	private String _id;
	private boolean _includeAnonymousUsers;
	private long _individualCount;
	private long _knownIndividualCount;
	private String _name;
	private SegmentType _segmentType;

}