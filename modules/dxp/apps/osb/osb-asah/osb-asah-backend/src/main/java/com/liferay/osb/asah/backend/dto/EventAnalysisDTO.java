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

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.entity.EventAnalysis;
import com.liferay.osb.asah.common.graphql.GraphQLProperty;
import com.liferay.osb.asah.common.graphql.GraphQLType;

import java.text.SimpleDateFormat;

import org.json.JSONArray;

/**
 * @author Rachael Koestartyo
 */
@GraphQLType("EventAnalysis")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EventAnalysisDTO {

	public EventAnalysisDTO(EventAnalysis eventAnalysis) {
		_eventAnalysis = eventAnalysis;
	}

	public String getAnalysisType() {
		return _eventAnalysis.getEventAnalysisType();
	}

	public String getChannelId() {
		return String.valueOf(_eventAnalysis.getChannelId());
	}

	public Boolean getCompareToPrevious() {
		return _eventAnalysis.getCompareToPrevious();
	}

	@GraphQLProperty("createDate")
	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	@JsonProperty("createDate")
	public String getCreateDate() {
		if (_eventAnalysis.getCreateDate() != null) {
			return DateUtil.toUTCString(_eventAnalysis.getCreateDate());
		}

		return null;
	}

	public String getCreatedByUserId() {
		return String.valueOf(_eventAnalysis.getCreatedByUserId());
	}

	public String getCreatedByUserName() {
		return _eventAnalysis.getCreatedByUserName();
	}

	@GraphQLProperty("eventAnalysisBreakdowns")
	public JSONArray getEventAnalysisBreakdownJSONArray() {
		return _eventAnalysis.getEventAnalysisBreakdownJSONArray();
	}

	@GraphQLProperty("eventAnalysisFilters")
	public JSONArray getEventAnalysisFilterJSONArray() {
		return _eventAnalysis.getEventAnalysisFilterJSONArray();
	}

	public String getEventDefinitionId() {
		return String.valueOf(_eventAnalysis.getEventDefinitionId());
	}

	public String getId() {
		return String.valueOf(_eventAnalysis.getId());
	}

	public String getModifiedByUserId() {
		return String.valueOf(_eventAnalysis.getModifiedByUserId());
	}

	public String getModifiedByUserName() {
		return _eventAnalysis.getModifiedByUserName();
	}

	@GraphQLProperty("modifiedDate")
	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	@JsonProperty("modifiedDate")
	public String getModifiedDate() {
		if (_eventAnalysis.getModifiedDate() != null) {
			return DateUtil.toUTCString(_eventAnalysis.getModifiedDate());
		}

		return null;
	}

	public String getName() {
		return _eventAnalysis.getName();
	}

	@GraphQLProperty("rangeEnd")
	@JsonFormat(
		pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	@JsonProperty("rangeEnd")
	public String getRangeEnd() {
		if (_eventAnalysis.getRangeEnd() != null) {
			return DateUtil.toUTCString(
				_eventAnalysis.getRangeEnd(),
				new SimpleDateFormat("yyyy-MM-dd"));
		}

		return null;
	}

	public Integer getRangeKey() {
		return _eventAnalysis.getRangeKey();
	}

	@GraphQLProperty("rangeStart")
	@JsonFormat(
		pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	@JsonProperty("rangeStart")
	public String getRangeStart() {
		if (_eventAnalysis.getRangeStart() != null) {
			return DateUtil.toUTCString(
				_eventAnalysis.getRangeStart(),
				new SimpleDateFormat("yyyy-MM-dd"));
		}

		return null;
	}

	private final EventAnalysis _eventAnalysis;

}