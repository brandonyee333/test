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

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.entity.BQMembershipChange;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.util.StringUtil;

import java.util.Date;

/**
 * @author Marcellus Tavares
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonRootName("segments")
public class ReportSegmentDTO {

	public ReportSegmentDTO(
		BQMembershipChange bqMembershipChange, Segment segment) {

		_createDate = segment.getCreateDate();
		_id = StringUtil.get(segment.getId());
		_includeAnonymousUsers = segment.getIncludeAnonymousUsers();

		if (bqMembershipChange != null) {
			_individualsCount = bqMembershipChange.getIndividualsCount();
			_knownIndividualsCount =
				bqMembershipChange.getKnownIndividualsCount();
		}
		else {
			_individualsCount = 0L;
			_knownIndividualsCount = 0L;
		}

		_name = segment.getName();
		_segmentType = String.valueOf(segment.getType());
	}

	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	@JsonProperty("dateCreated")
	public Date getDateCreated() {
		if (_createDate == null) {
			return null;
		}

		return new Date(_createDate.getTime());
	}

	public String getId() {
		return _id;
	}

	@JsonAlias("individualsCount")
	@JsonProperty("individualCount")
	public Long getIndividualsCount() {
		return _individualsCount;
	}

	@JsonAlias("knownIndividualsCount")
	@JsonProperty("knownIndividualCount")
	public Long getKnownIndividualsCount() {
		return _knownIndividualsCount;
	}

	public String getName() {
		return _name;
	}

	public String getSegmentType() {
		return _segmentType;
	}

	public Boolean isIncludeAnonymousUsers() {
		return _includeAnonymousUsers;
	}

	private final Date _createDate;
	private final String _id;
	private final Boolean _includeAnonymousUsers;
	private final Long _individualsCount;
	private final Long _knownIndividualsCount;
	private final String _name;
	private final String _segmentType;

}