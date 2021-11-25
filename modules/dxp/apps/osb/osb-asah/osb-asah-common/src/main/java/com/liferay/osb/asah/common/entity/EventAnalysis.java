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

import com.fasterxml.jackson.annotation.JsonProperty;

import com.liferay.osb.asah.common.util.BeanUtils;

import java.util.Date;
import java.util.Map;
import java.util.Objects;

import org.json.JSONArray;

import org.springframework.data.annotation.AccessType;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author Rachael Koestartyo
 */
@Table
public class EventAnalysis implements Persistable<Long> {

	public EventAnalysis() {
	}

	public EventAnalysis(
		Long channelId, Boolean compareToPrevious, Date createDate,
		Long createdByUserId, String createdByUserName,
		JSONArray eventAnalysisBreakdownJSONArray,
		JSONArray eventAnalysisFilterJSONArray, String eventAnalysisType,
		Long eventDefinitionId, Long modifiedByUserId,
		String modifiedByUserName, Date modifiedDate, String name,
		Date rangeEnd, Integer rangeKey, Date rangeStart) {

		_channelId = channelId;
		_compareToPrevious = compareToPrevious;
		_createDate = new Date(createDate.getTime());
		_createdByUserId = createdByUserId;
		_createdByUserName = createdByUserName;
		_eventAnalysisBreakdownJSONArray = eventAnalysisBreakdownJSONArray;
		_eventAnalysisFilterJSONArray = eventAnalysisFilterJSONArray;
		_eventAnalysisType = eventAnalysisType;
		_eventDefinitionId = eventDefinitionId;
		_modifiedByUserId = modifiedByUserId;
		_modifiedByUserName = modifiedByUserName;
		_modifiedDate = new Date(modifiedDate.getTime());
		_name = name;
		_rangeEnd = new Date(rangeEnd.getTime());
		_rangeKey = rangeKey;
		_rangeStart = new Date(rangeStart.getTime());
	}

	public EventAnalysis(Map<String, Object> source) {
		BeanUtils.copyProperties(source, this);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof EventAnalysis)) {
			return false;
		}

		EventAnalysis eventAnalysis = (EventAnalysis)obj;

		if (Objects.equals(_channelId, eventAnalysis._channelId) &&
			Objects.equals(
				_compareToPrevious, eventAnalysis._compareToPrevious) &&
			Objects.equals(_createDate, eventAnalysis._createDate) &&
			Objects.equals(_createdByUserId, eventAnalysis._createdByUserId) &&
			Objects.equals(
				_createdByUserName, eventAnalysis._createdByUserName) &&
			Objects.equals(
				_eventAnalysisBreakdownJSONArray,
				eventAnalysis._eventAnalysisBreakdownJSONArray) &&
			Objects.equals(
				_eventAnalysisFilterJSONArray,
				eventAnalysis._eventAnalysisFilterJSONArray) &&
			Objects.equals(
				_eventAnalysisType, eventAnalysis._eventAnalysisType) &&
			Objects.equals(
				_eventDefinitionId, eventAnalysis._eventDefinitionId) &&
			Objects.equals(_id, eventAnalysis._id) &&
			Objects.equals(
				_modifiedByUserId, eventAnalysis._modifiedByUserId) &&
			Objects.equals(
				_modifiedByUserName, eventAnalysis._modifiedByUserName) &&
			Objects.equals(_modifiedDate, eventAnalysis._modifiedDate) &&
			Objects.equals(_name, eventAnalysis._name) &&
			Objects.equals(_rangeEnd, eventAnalysis._rangeEnd) &&
			Objects.equals(_rangeKey, eventAnalysis._rangeKey) &&
			Objects.equals(_rangeStart, eventAnalysis._rangeStart)) {

			return true;
		}

		return false;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Long getChannelId() {
		return _channelId;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Boolean getCompareToPrevious() {
		return _compareToPrevious;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Date getCreateDate() {
		if (_createDate == null) {
			return null;
		}

		return new Date(_createDate.getTime());
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Long getCreatedByUserId() {
		return _createdByUserId;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getCreatedByUserName() {
		return _createdByUserName;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@Column("breakdowns")
	@JsonProperty("breakdowns")
	public JSONArray getEventAnalysisBreakdownJSONArray() {
		return _eventAnalysisBreakdownJSONArray;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@Column("filters")
	@JsonProperty("filters")
	public JSONArray getEventAnalysisFilterJSONArray() {
		return _eventAnalysisFilterJSONArray;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getEventAnalysisType() {
		return _eventAnalysisType;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Long getEventDefinitionId() {
		return _eventDefinitionId;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@Id
	@Override
	public Long getId() {
		return _id;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Long getModifiedByUserId() {
		return _modifiedByUserId;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getModifiedByUserName() {
		return _modifiedByUserName;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Date getModifiedDate() {
		if (_modifiedDate == null) {
			return null;
		}

		return new Date(_modifiedDate.getTime());
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Date getRangeEnd() {
		if (_rangeEnd == null) {
			return null;
		}

		return new Date(_rangeEnd.getTime());
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Integer getRangeKey() {
		return _rangeKey;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Date getRangeStart() {
		if (_rangeStart == null) {
			return null;
		}

		return new Date(_rangeStart.getTime());
	}

	@Override
	public int hashCode() {
		return Objects.hash(
			_channelId, _compareToPrevious, _createDate, _createdByUserId,
			_createdByUserName, _eventAnalysisBreakdownJSONArray,
			_eventAnalysisFilterJSONArray, _eventAnalysisType,
			_eventDefinitionId, _id, _modifiedByUserId, _modifiedByUserName,
			_modifiedDate, _name, _rangeEnd, _rangeKey, _rangeStart);
	}

	@Override
	public boolean isNew() {
		if ((_id == null) || ((_isNew != null) && _isNew)) {
			return true;
		}

		return false;
	}

	public void setChannelId(Long channelId) {
		_channelId = channelId;
	}

	public void setCompareToPrevious(Boolean compareToPrevious) {
		_compareToPrevious = compareToPrevious;
	}

	public void setCreateDate(Date createDate) {
		if (createDate != null) {
			_createDate = new Date(createDate.getTime());
		}
	}

	public void setCreatedByUserId(Long createdByUserId) {
		_createdByUserId = createdByUserId;
	}

	public void setCreatedByUserName(String createdByUserName) {
		_createdByUserName = createdByUserName;
	}

	public void setEventAnalysisBreakdownJSONArray(
		JSONArray eventAnalysisBreakdownJSONArray) {

		_eventAnalysisBreakdownJSONArray = eventAnalysisBreakdownJSONArray;
	}

	public void setEventAnalysisFilterJSONArray(
		JSONArray eventAnalysisFilterJSONArray) {

		_eventAnalysisFilterJSONArray = eventAnalysisFilterJSONArray;
	}

	public void setEventAnalysisType(String eventAnalysisType) {
		_eventAnalysisType = eventAnalysisType;
	}

	public void setEventDefinitionId(Long eventDefinitionId) {
		_eventDefinitionId = eventDefinitionId;
	}

	public void setId(Long id) {
		_id = id;
	}

	public void setIsNew(boolean isNew) {
		_isNew = isNew;
	}

	public void setModifiedByUserId(Long modifiedByUserId) {
		_modifiedByUserId = modifiedByUserId;
	}

	public void setModifiedByUserName(String modifiedByUserName) {
		_modifiedByUserName = modifiedByUserName;
	}

	public void setModifiedDate(Date modifiedDate) {
		if (modifiedDate != null) {
			_modifiedDate = new Date(modifiedDate.getTime());
		}
	}

	public void setRangeEnd(Date rangeEnd) {
		if (rangeEnd != null) {
			_rangeEnd = new Date(rangeEnd.getTime());
		}
	}

	public void setRangeKey(Integer rangeKey) {
		_rangeKey = rangeKey;
	}

	public void setRangeStart(Date rangeStart) {
		if (rangeStart != null) {
			_rangeStart = new Date(rangeStart.getTime());
		}
	}

	@Transient
	private Long _channelId;

	@Transient
	private Boolean _compareToPrevious;

	@Transient
	private Date _createDate;

	@Transient
	private Long _createdByUserId;

	@Transient
	private String _createdByUserName;

	@Transient
	private JSONArray _eventAnalysisBreakdownJSONArray;

	@Transient
	private JSONArray _eventAnalysisFilterJSONArray;

	@Transient
	private String _eventAnalysisType;

	@Transient
	private Long _eventDefinitionId;

	@Transient
	private Long _id;

	@Transient
	private Boolean _isNew;

	@Transient
	private Long _modifiedByUserId;

	@Transient
	private String _modifiedByUserName;

	@Transient
	private Date _modifiedDate;

	@Transient
	private String _name;

	@Transient
	private Date _rangeEnd;

	@Transient
	private Integer _rangeKey;

	@Transient
	private Date _rangeStart;

}