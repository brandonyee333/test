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

import org.springframework.data.annotation.AccessType;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author Marcellus Tavares
 */
@Table
public class Interest implements Persistable<Long> {

	public Interest() {
	}

	public Interest(Map<String, Object> source) {
		BeanUtils.copyProperties(source, this);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if ((obj == null) || !(obj instanceof Interest)) {
			return false;
		}

		Interest interest = (Interest)obj;

		if (Objects.equals(_id, interest._id) &&
			Objects.equals(_name, interest._name) &&
			Objects.equals(_ownerId, interest._ownerId) &&
			Objects.equals(_ownerType, interest._ownerType) &&
			Objects.equals(_recordedDate, interest._recordedDate) &&
			Objects.equals(_score, interest._score) &&
			Objects.equals(_views, interest._views)) {

			return true;
		}

		return false;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@Id
	@JsonSerialize(using = ToStringSerializer.class)
	@Override
	public Long getId() {
		return _id;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getName() {
		return _name;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@JsonSerialize(using = ToStringSerializer.class)
	public String getOwnerId() {
		return _ownerId;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getOwnerType() {
		return _ownerType;
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

	@AccessType(AccessType.Type.PROPERTY)
	public Double getScore() {
		return _score;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Long getViews() {
		return _views;
	}

	@Override
	public int hashCode() {
		return Objects.hash(
			_id, _name, _ownerId, _ownerType, _recordedDate, _score, _views);
	}

	@JsonIgnore
	@Override
	public boolean isNew() {
		if ((_id == null) || ((_isNew != null) && _isNew)) {
			return true;
		}

		return false;
	}

	public void setId(Long id) {
		_id = id;
	}

	public void setIsNew(boolean isNew) {
		_isNew = isNew;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setOwnerId(String ownerId) {
		_ownerId = ownerId;
	}

	public void setOwnerType(String ownerType) {
		_ownerType = ownerType;
	}

	public void setRecordedDate(Date recordedDate) {
		if (recordedDate != null) {
			_recordedDate = new Date(recordedDate.getTime());
		}
	}

	public void setScore(Double score) {
		_score = score;
	}

	public void setViews(Long views) {
		_views = views;
	}

	@Transient
	private Long _id;

	@Transient
	private Boolean _isNew;

	@Transient
	private String _name;

	@Transient
	private String _ownerId;

	@Transient
	private String _ownerType;

	@Transient
	private Date _recordedDate;

	@Transient
	private Double _score;

	@Transient
	private Long _views;

}