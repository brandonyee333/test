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

package com.liferay.osb.asah.common.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.model.ActivityGroup;
import com.liferay.osb.asah.common.util.SetUtil;
import com.liferay.osb.asah.common.util.StringUtil;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Inácio Nery
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonRootName("activity-groups")
public class ActivityGroupDTO {

	public ActivityGroupDTO() {
	}

	public ActivityGroupDTO(ActivityGroup activityGroup) {
		_activityType = activityGroup.getActivityType();
		_channelId = StringUtil.get(activityGroup.getChannelId(), null);
		_dataSourceId = StringUtil.get(activityGroup.getDataSourceId(), null);
		_dayDate = activityGroup.getDayDate();
		_endDate = activityGroup.getEndDate();
		_endLocalDate = activityGroup.getEndLocalDate();
		_id = StringUtil.get(activityGroup.getId(), null);
		_ownerId = StringUtil.get(activityGroup.getOwnerId(), null);
		_startDate = activityGroup.getStartDate();
		_startLocalDate = activityGroup.getStartLocalDate();
		_userId = activityGroup.getUserId();
	}

	public ActivityGroupDTO(Collection<ActivityGroupDTO> activityGroupDTOs) {
		_activityGroupDTOs = new LinkedHashSet<>(activityGroupDTOs);
	}

	public ActivityGroupDTO(List<ActivityGroup> activityGroups) {
		_activityGroupDTOs = SetUtil.map(activityGroups, ActivityGroupDTO::new);
	}

	@JsonProperty("activity-groups")
	public Set<ActivityGroupDTO> getActivityGroupDTOs() {
		return _activityGroupDTOs;
	}

	@JsonProperty("activityType")
	public String getActivityType() {
		return _activityType;
	}

	@JsonProperty("channelId")
	public String getChannelId() {
		return _channelId;
	}

	@JsonProperty("dataSourceId")
	public String getDataSourceId() {
		return _dataSourceId;
	}

	@JsonAlias("dayDate")
	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	@JsonProperty("day")
	public Date getDayDate() {
		if (_dayDate == null) {
			return null;
		}

		return new Date(_dayDate.getTime());
	}

	@JsonProperty("_embedded")
	public Map<String, Object> getEmbedded() {
		return _embedded;
	}

	@JsonAlias("endDate")
	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	@JsonProperty("endTime")
	public Date getEndDate() {
		if (_endDate == null) {
			return null;
		}

		return new Date(_endDate.getTime());
	}

	@JsonAlias("endLocalDate")
	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	@JsonProperty("endTimeLocal")
	public Date getEndLocalDate() {
		if (_endLocalDate == null) {
			return null;
		}

		return new Date(_endLocalDate.getTime());
	}

	@JsonProperty("id")
	public String getId() {
		return _id;
	}

	@JsonProperty("ownerId")
	public String getOwnerId() {
		return _ownerId;
	}

	@JsonAlias("startDate")
	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	@JsonProperty("startTime")
	public Date getStartDate() {
		if (_startDate == null) {
			return null;
		}

		return new Date(_startDate.getTime());
	}

	@JsonAlias("startLocalDate")
	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	@JsonProperty("startTimeLocal")
	public Date getStartLocalDate() {
		if (_startLocalDate == null) {
			return null;
		}

		return new Date(_startLocalDate.getTime());
	}

	@JsonProperty("userId")
	public String getUserId() {
		return _userId;
	}

	public void setActivityType(String activityType) {
		_activityType = activityType;
	}

	public void setChannelId(String channelId) {
		_channelId = channelId;
	}

	public void setDataSourceId(String dataSourceId) {
		_dataSourceId = dataSourceId;
	}

	public void setDayDate(Date dayDate) {
		if (dayDate != null) {
			_dayDate = new Date(dayDate.getTime());
		}
	}

	public void setEmbedded(Map<String, Object> embedded) {
		_embedded = embedded;
	}

	public void setEndDate(Date endDate) {
		if (endDate != null) {
			_endDate = new Date(endDate.getTime());
		}
	}

	public void setEndLocalDate(Date endLocalDate) {
		if (endLocalDate != null) {
			_endLocalDate = new Date(endLocalDate.getTime());
		}
	}

	public void setId(String id) {
		_id = id;
	}

	public void setOwnerId(String ownerId) {
		_ownerId = ownerId;
	}

	public void setStartDate(Date startDate) {
		if (startDate != null) {
			_startDate = new Date(startDate.getTime());
		}
	}

	public void setStartLocalDate(Date startLocalDate) {
		if (startLocalDate != null) {
			_startLocalDate = new Date(startLocalDate.getTime());
		}
	}

	public void setUserId(String userId) {
		_userId = userId;
	}

	private Set<ActivityGroupDTO> _activityGroupDTOs;
	private String _activityType;
	private String _channelId;
	private String _dataSourceId;
	private Date _dayDate;
	private Map<String, Object> _embedded;
	private Date _endDate;
	private Date _endLocalDate;
	private String _id;
	private String _ownerId;
	private Date _startDate;
	private Date _startLocalDate;
	private String _userId;

}