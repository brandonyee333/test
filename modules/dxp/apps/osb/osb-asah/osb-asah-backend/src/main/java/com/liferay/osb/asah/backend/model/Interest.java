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
public class Interest {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (!(obj instanceof Interest)) {
			return false;
		}

		Interest interest = (Interest)obj;

		if (Objects.equals(_dateRecorded, interest._dateRecorded) &&
			Objects.equals(_id, interest._id) &&
			Objects.equals(_name, interest._name) &&
			Objects.equals(_ownerId, interest._ownerId) &&
			Objects.equals(_score, interest._score)) {

			return true;
		}

		return false;
	}

	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	public Date getDateRecorded() {
		if (_dateRecorded == null) {
			return null;
		}

		return new Date(_dateRecorded.getTime());
	}

	public String getId() {
		return _id;
	}

	public String getName() {
		return _name;
	}

	public String getOwnerId() {
		return _ownerId;
	}

	public double getScore() {
		return _score;
	}

	@Override
	public int hashCode() {
		return Objects.hash(_dateRecorded, _id, _name, _ownerId, _score);
	}

	public void setDateRecorded(Date dateRecorded) {
		if (dateRecorded != null) {
			_dateRecorded = new Date(dateRecorded.getTime());
		}
	}

	public void setId(String id) {
		_id = id;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setOwnerId(String ownerId) {
		_ownerId = ownerId;
	}

	public void setScore(double score) {
		_score = score;
	}

	private Date _dateRecorded;
	private String _id;
	private String _name;
	private String _ownerId;
	private double _score;

}