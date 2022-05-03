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
import com.liferay.osb.asah.common.util.SetUtil;
import com.liferay.osb.asah.common.util.StringUtil;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.BooleanUtils;

/**
 * @author Rachael Koestartyo
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonRootName("membership-changes")
public class BQMembershipChangeDTO {

	public BQMembershipChangeDTO() {
	}

	public BQMembershipChangeDTO(BQMembershipChange bqMembershipChange) {
		_id = StringUtil.get(bqMembershipChange.getId(), null);
		_individualDeleted = BooleanUtils.toBoolean(
			bqMembershipChange.getIndividualDeleted());
		_individualEmail = bqMembershipChange.getIndividualEmail();
		_individualId = StringUtil.get(
			bqMembershipChange.getIndividualId(), null);
		_individualName = bqMembershipChange.getIndividualName();
		_individualsCount = bqMembershipChange.getIndividualsCount();
		_joinedDate = bqMembershipChange.getJoinedDate();
		_knownIndividualsCount = bqMembershipChange.getKnownIndividualsCount();
		_modifiedDate = bqMembershipChange.getModifiedDate();
		_operation = bqMembershipChange.getOperation();
		_segmentId = StringUtil.get(
			bqMembershipChange.getIndividualSegmentId(), null);
	}

	public BQMembershipChangeDTO(List<BQMembershipChange> bqMembershipChanges) {
		_bqMembershipChangeDTOs = SetUtil.map(
			bqMembershipChanges, BQMembershipChangeDTO::new);
	}

	public BQMembershipChangeDTO(
		Set<BQMembershipChangeDTO> bqMembershipChangeDTOs) {

		_bqMembershipChangeDTOs = bqMembershipChangeDTOs;
	}

	@JsonProperty("_embedded")
	public Map<String, Object> getEmbedded() {
		return _embedded;
	}

	@JsonProperty("id")
	public String getId() {
		return _id;
	}

	@JsonProperty("individualDeleted")
	public Boolean getIndividualDeleted() {
		return _individualDeleted;
	}

	@JsonProperty("individualEmail")
	public String getIndividualEmail() {
		return _individualEmail;
	}

	@JsonProperty("individualId")
	public String getIndividualId() {
		return _individualId;
	}

	@JsonProperty("individualName")
	public String getIndividualName() {
		return _individualName;
	}

	@JsonProperty("individualsCount")
	public Long getIndividualsCount() {
		return _individualsCount;
	}

	@JsonAlias("joinedDate")
	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	@JsonProperty("dateFirst")
	public Date getJoinedDate() {
		if (_joinedDate == null) {
			return null;
		}

		return new Date(_joinedDate.getTime());
	}

	@JsonProperty("knownIndividualsCount")
	public Long getKnownIndividualsCount() {
		return _knownIndividualsCount;
	}

	@JsonProperty("membership-changes")
	public Set<BQMembershipChangeDTO> getMembershipChangeDTOs() {
		return _bqMembershipChangeDTOs;
	}

	@JsonAlias("modifiedDate")
	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	@JsonProperty("dateChanged")
	public Date getModifiedDate() {
		if (_modifiedDate == null) {
			return null;
		}

		return new Date(_modifiedDate.getTime());
	}

	@JsonProperty("operation")
	public String getOperation() {
		return _operation;
	}

	@JsonProperty("individualSegmentId")
	public String getSegmentId() {
		return _segmentId;
	}

	public void setEmbedded(Map<String, Object> embedded) {
		_embedded = embedded;
	}

	public void setId(String id) {
		_id = id;
	}

	public void setIndividualDeleted(Boolean individualDeleted) {
		_individualDeleted = individualDeleted;
	}

	public void setIndividualEmail(String individualEmail) {
		_individualEmail = individualEmail;
	}

	public void setIndividualId(String individualId) {
		_individualId = individualId;
	}

	public void setIndividualName(String individualName) {
		_individualName = individualName;
	}

	public void setIndividualsCount(Long individualsCount) {
		_individualsCount = individualsCount;
	}

	public void setJoinedDate(Date joinedDate) {
		if (joinedDate != null) {
			_joinedDate = new Date(joinedDate.getTime());
		}
	}

	public void setKnownIndividualsCount(Long knownIndividualsCount) {
		_knownIndividualsCount = knownIndividualsCount;
	}

	public void setMembershipChangeDTOs(
		Set<BQMembershipChangeDTO> bqMembershipChangeDTOs) {

		_bqMembershipChangeDTOs = bqMembershipChangeDTOs;
	}

	public void setModifiedDate(Date modifiedDate) {
		if (modifiedDate != null) {
			_modifiedDate = new Date(modifiedDate.getTime());
		}
	}

	public void setOperation(String operation) {
		_operation = operation;
	}

	public void setSegmentId(String segmentId) {
		_segmentId = segmentId;
	}

	private Set<BQMembershipChangeDTO> _bqMembershipChangeDTOs;
	private Map<String, Object> _embedded;
	private String _id;
	private Boolean _individualDeleted;
	private String _individualEmail;
	private String _individualId;
	private String _individualName;
	private Long _individualsCount;
	private Date _joinedDate;
	private Long _knownIndividualsCount;
	private Date _modifiedDate;
	private String _operation;
	private String _segmentId;

}