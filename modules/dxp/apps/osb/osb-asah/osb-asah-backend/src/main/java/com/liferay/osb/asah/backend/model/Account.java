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
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Marcellus Tavares
 */
public class Account {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Account)) {
			return false;
		}

		Account account = (Account)obj;

		if (Objects.equals(
				_activeIndividualsCount, account._activeIndividualsCount) &&
			Objects.equals(_dateCreated, account._dateCreated) &&
			Objects.equals(_dateModified, account._dateModified) &&
			Objects.equals(_engagementScore, account._engagementScore) &&
			Objects.equals(_id, account._id) &&
			Objects.equals(_individualsCount, account._individualsCount) &&
			Objects.equals(_properties, account._properties)) {

			return true;
		}

		return false;
	}

	public long getActiveIndividualsCount() {
		return _activeIndividualsCount;
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

	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	public Date getDateModified() {
		if (_dateModified == null) {
			return null;
		}

		return new Date(_dateModified.getTime());
	}

	public double getEngagementScore() {
		return _engagementScore;
	}

	public String getId() {
		return _id;
	}

	public long getIndividualsCount() {
		return _individualsCount;
	}

	public Map<String, Object> getProperties() {
		return _properties;
	}

	@Override
	public int hashCode() {
		return Objects.hash(
			_activeIndividualsCount, _dateCreated, _dateModified,
			_engagementScore, _id, _individualsCount, _properties);
	}

	public void setActiveIndividualsCount(long activeIndividualsCount) {
		_activeIndividualsCount = activeIndividualsCount;
	}

	public void setDateCreated(Date dateCreated) {
		if (dateCreated != null) {
			_dateCreated = new Date(dateCreated.getTime());
		}
	}

	public void setDateModified(Date dateModified) {
		if (dateModified != null) {
			_dateModified = new Date(dateModified.getTime());
		}
	}

	public void setEngagementScore(double engagementScore) {
		_engagementScore = engagementScore;
	}

	public void setId(String id) {
		_id = id;
	}

	public void setIndividualsCount(long individualsCount) {
		_individualsCount = individualsCount;
	}

	public void setProperties(Map<String, Object> properties) {
		_properties = properties;
	}

	private long _activeIndividualsCount;
	private Date _dateCreated;
	private Date _dateModified;
	private double _engagementScore;
	private String _id;
	private long _individualsCount;
	private Map<String, Object> _properties = new HashMap<>();

}