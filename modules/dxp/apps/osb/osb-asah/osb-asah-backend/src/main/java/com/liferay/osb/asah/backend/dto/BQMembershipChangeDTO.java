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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import com.liferay.osb.asah.common.entity.BQMembershipChange;
import com.liferay.osb.asah.common.util.SetUtil;
import com.liferay.osb.asah.common.util.StringUtil;

import java.util.List;
import java.util.Map;
import java.util.Set;

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
		_individualsCount = bqMembershipChange.getIndividualsCount();
		_knownIndividualsCount = bqMembershipChange.getKnownIndividualsCount();
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

	@JsonProperty("membership-changes")
	public Set<BQMembershipChangeDTO> getBQMembershipChangeDTOs() {
		return _bqMembershipChangeDTOs;
	}

	@JsonProperty("_embedded")
	public Map<String, Object> getEmbedded() {
		return _embedded;
	}

	@JsonProperty("id")
	public String getId() {
		return _id;
	}

	@JsonProperty("individualsCount")
	public Long getIndividualsCount() {
		return _individualsCount;
	}

	@JsonProperty("knownIndividualsCount")
	public Long getKnownIndividualsCount() {
		return _knownIndividualsCount;
	}

	@JsonProperty("individualSegmentId")
	public String getSegmentId() {
		return _segmentId;
	}

	public void setBQMembershipChangeDTOs(
		Set<BQMembershipChangeDTO> bqMembershipChangeDTOs) {

		_bqMembershipChangeDTOs = bqMembershipChangeDTOs;
	}

	public void setEmbedded(Map<String, Object> embedded) {
		_embedded = embedded;
	}

	public void setId(String id) {
		_id = id;
	}

	public void setIndividualsCount(Long individualsCount) {
		_individualsCount = individualsCount;
	}

	public void setKnownIndividualsCount(Long knownIndividualsCount) {
		_knownIndividualsCount = knownIndividualsCount;
	}

	public void setSegmentId(String segmentId) {
		_segmentId = segmentId;
	}

	private Set<BQMembershipChangeDTO> _bqMembershipChangeDTOs;
	private Map<String, Object> _embedded;
	private String _id;
	private Long _individualsCount;
	private Long _knownIndividualsCount;
	private String _segmentId;

}