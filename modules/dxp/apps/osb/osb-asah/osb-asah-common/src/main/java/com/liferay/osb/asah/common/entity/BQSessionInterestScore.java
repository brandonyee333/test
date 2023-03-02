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

package com.liferay.osb.asah.common.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.util.BeanUtils;

import java.util.Date;
import java.util.Map;
import java.util.Objects;

/**
 * @author Robson Pastor
 */
public class BQSessionInterestScore {

	public BQSessionInterestScore() {
	}

	public BQSessionInterestScore(Map<String, Object> source) {
		BeanUtils.copyProperties(source, this);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof BQSessionInterestScore)) {
			return false;
		}

		BQSessionInterestScore bqIdentityInterestScore =
			(BQSessionInterestScore)obj;

		if (Objects.equals(_id, bqIdentityInterestScore._id)) {
			return true;
		}

		return false;
	}

	@JsonSerialize(using = ToStringSerializer.class)
	public Long getId() {
		return _id;
	}

	public String getIdentityId() {
		return _identityId;
	}

	public Boolean getInterested() {
		return _interested;
	}

	public Double getInterestScore() {
		return _interestScore;
	}

	public String getKeyword() {
		return _keyword;
	}

	@JsonAlias("recordedDate")
	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	@JsonProperty("dateRecorded")
	public Date getRecordedDate() {
		if (_recordedDate == null) {
			return null;
		}

		return new Date(_recordedDate.getTime());
	}

	public String getSessionId() {
		return _sessionId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(
			_id, _identityId, _interested, _interestScore, _keyword,
			_recordedDate, _sessionId);
	}

	public Boolean isInterested() {
		return _interested;
	}

	@JsonIgnore
	public boolean isNew() {
		if ((_id == null) || ((_isNew != null) && _isNew)) {
			return true;
		}

		return false;
	}

	public void setId(Long id) {
		_id = id;
	}

	public void setIdentityId(String identityId) {
		_identityId = identityId;
	}

	public void setInterested(Boolean interested) {
		_interested = interested;
	}

	public void setInterestScore(Double interestScore) {
		_interestScore = interestScore;
	}

	public void setIsNew(boolean isNew) {
		_isNew = isNew;
	}

	public void setKeyword(String keyword) {
		_keyword = keyword;
	}

	public void setRecordedDate(Date recordedDate) {
		if (recordedDate != null) {
			_recordedDate = new Date(recordedDate.getTime());
		}
	}

	public void setSessionId(String sessionId) {
		_sessionId = sessionId;
	}

	private Long _id;
	private String _identityId;
	private Boolean _interested;
	private Double _interestScore;
	private Boolean _isNew;
	private String _keyword;
	private Date _recordedDate;
	private String _sessionId;

}