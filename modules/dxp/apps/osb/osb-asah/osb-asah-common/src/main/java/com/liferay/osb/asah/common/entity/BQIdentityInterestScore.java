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
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.util.BeanUtils;

import java.util.Date;
import java.util.Map;
import java.util.Objects;

import org.springframework.data.annotation.AccessType;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author Marcellus Tavares
 */
@Table
public class BQIdentityInterestScore {

	public BQIdentityInterestScore() {
	}

	public BQIdentityInterestScore(Map<String, Object> source) {
		BeanUtils.copyProperties(source, this);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof BQIdentityInterestScore)) {
			return false;
		}

		BQIdentityInterestScore bqIdentityInterestScore =
			(BQIdentityInterestScore)obj;

		if (Objects.equals(_id, bqIdentityInterestScore._id)) {
			return true;
		}

		return false;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@Id
	@JsonSerialize(using = ToStringSerializer.class)
	public Long getId() {
		return _id;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getIdentityId() {
		return _identityId;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Boolean getInterested() {
		return _interested;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Double getInterestScore() {
		return _interestScore;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getKeyword() {
		return _keyword;
	}

	@AccessType(AccessType.Type.PROPERTY)
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

	@Override
	public int hashCode() {
		return Objects.hash(
			_id, _identityId, _interested, _interestScore, _keyword,
			_recordedDate);
	}

	public Boolean isInterested() {
		return _interested;
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

	public void setKeyword(String keyword) {
		_keyword = keyword;
	}

	public void setRecordedDate(Date recordedDate) {
		if (recordedDate != null) {
			_recordedDate = new Date(recordedDate.getTime());
		}
	}

	@Transient
	private Long _id;

	@Transient
	private String _identityId;

	@Transient
	private Boolean _interested;

	@Transient
	private Double _interestScore;

	@Transient
	private String _keyword;

	@Transient
	private Date _recordedDate;

}