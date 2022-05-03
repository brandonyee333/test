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
import com.liferay.osb.asah.common.entity.BQMembership;
import com.liferay.osb.asah.common.util.SetUtil;
import com.liferay.osb.asah.common.util.StringUtil;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author Inácio Nery
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonRootName("memberships")
public class BQMembershipDTO {

	public BQMembershipDTO() {
	}

	public BQMembershipDTO(BQMembership bqMembership) {
		_createDate = bqMembership.getCreateDate();
		_id = StringUtil.get(bqMembership.getId(), null);
		_individualId = StringUtil.get(bqMembership.getIndividualId(), null);
		_individualSegmentId = StringUtil.get(
			bqMembership.getIndividualSegmentId(), null);
		_modifiedDate = bqMembership.getModifiedDate();
		_removedDate = bqMembership.getRemovedDate();
		_status = bqMembership.getStatus();
	}

	public BQMembershipDTO(List<BQMembership> bqMemberships) {
		_bqMembershipDTOs = SetUtil.map(bqMemberships, BQMembershipDTO::new);
	}

	@JsonProperty("memberships")
	public Set<BQMembershipDTO> getBQMembershipDTOs() {
		return _bqMembershipDTOs;
	}

	@JsonAlias("createDate")
	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	@JsonProperty("dateCreated")
	public Date getCreateDate() {
		if (_createDate == null) {
			return null;
		}

		return new Date(_createDate.getTime());
	}

	@JsonProperty("id")
	public String getId() {
		return _id;
	}

	@JsonProperty("individualId")
	public String getIndividualId() {
		return _individualId;
	}

	@JsonProperty("individualSegmentId")
	public String getIndividualSegmentId() {
		return _individualSegmentId;
	}

	@JsonAlias("modifiedDate")
	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	@JsonProperty("dateModified")
	public Date getModifiedDate() {
		if (_modifiedDate == null) {
			return null;
		}

		return new Date(_modifiedDate.getTime());
	}

	@JsonAlias("removedDate")
	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	@JsonProperty("dateRemoved")
	public Date getRemovedDate() {
		if (_removedDate == null) {
			return null;
		}

		return new Date(_removedDate.getTime());
	}

	@JsonProperty("status")
	public String getStatus() {
		return _status;
	}

	public void setCreateDate(Date createDate) {
		if (createDate != null) {
			_createDate = new Date(createDate.getTime());
		}
	}

	public void setId(String id) {
		_id = id;
	}

	public void setIndividualId(String individualId) {
		_individualId = individualId;
	}

	public void setIndividualSegmentId(String individualSegmentId) {
		_individualSegmentId = individualSegmentId;
	}

	public void setModifiedDate(Date modifiedDate) {
		if (modifiedDate != null) {
			_modifiedDate = new Date(modifiedDate.getTime());
		}
	}

	public void setRemovedDate(Date removedDate) {
		if (removedDate != null) {
			_removedDate = new Date(removedDate.getTime());
		}
	}

	public void setStatus(String status) {
		_status = status;
	}

	private Set<BQMembershipDTO> _bqMembershipDTOs;
	private Date _createDate;
	private String _id;
	private String _individualId;
	private String _individualSegmentId;
	private Date _modifiedDate;
	private Date _removedDate;
	private String _status;

}