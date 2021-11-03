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

package com.liferay.osb.asah.backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.entity.Interest;
import com.liferay.osb.asah.common.util.StringUtil;

import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * @author Robson Pastor
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonRootName("interests")
public class InterestDTO {

	public InterestDTO() {
	}

	public InterestDTO(Interest interest) {
		_id = StringUtil.get(interest.getId(), null);
		_name = interest.getName();
		_ownerId = StringUtil.get(interest.getOwnerId(), null);
		_ownerType = interest.getOwnerType();
		_recordedDate = interest.getRecordedDate();
		_score = interest.getScore();
		_views = interest.getViews();
	}

	public InterestDTO(Set<InterestDTO> interestDTOS) {
		_interestDTOs = interestDTOS;
	}

	@JsonProperty("_embedded")
	public Map<String, Object> getEmbedded() {
		return _embedded;
	}

	@JsonProperty("id")
	public String getId() {
		return _id;
	}

	@JsonProperty("interests")
	public Set<InterestDTO> getInterestDTOs() {
		return _interestDTOs;
	}

	@JsonProperty("name")
	public String getName() {
		return _name;
	}

	@JsonProperty("ownerId")
	public String getOwnerId() {
		return _ownerId;
	}

	@JsonProperty("ownerType")
	public String getOwnerType() {
		return _ownerType;
	}

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

	@JsonProperty("score")
	public Double getScore() {
		return _score;
	}

	@JsonProperty("views")
	public Long getViews() {
		return _views;
	}

	public void setEmbedded(Map<String, Object> embedded) {
		_embedded = embedded;
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

	public void setOwnerType(String ownerType) {
		_ownerType = ownerType;
	}

	public void setRecordedDate(Date recordedDate) {
		if (recordedDate != null) {
			_recordedDate = new Date(recordedDate.getTime());
		}
		else {
			_recordedDate = null;
		}
	}

	public void setScore(Double score) {
		_score = score;
	}

	public void setViews(Long views) {
		_views = views;
	}

	private Map<String, Object> _embedded;
	private String _id;
	private Set<InterestDTO> _interestDTOs;
	private String _name;
	private String _ownerId;
	private String _ownerType;
	private Date _recordedDate;
	private Double _score;
	private Long _views;

}