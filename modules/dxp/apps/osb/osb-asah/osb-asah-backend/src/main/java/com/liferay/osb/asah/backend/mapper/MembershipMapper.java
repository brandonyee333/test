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

package com.liferay.osb.asah.common.mapper;

import com.liferay.osb.asah.common.dto.MembershipDTO;
import com.liferay.osb.asah.common.model.Membership;

import org.json.JSONObject;

import org.springframework.stereotype.Component;

/**
 * @author Inácio Nery
 */
@Component
public class MembershipMapper extends Mapper<Membership, MembershipDTO> {

	@Override
	protected MembershipDTO toDTO(Membership membership) {
		return new MembershipDTO(membership);
	}

	@Override
	protected Membership toModel(JSONObject jsonObject) {
		Membership membership = new Membership();

		if (jsonObject.has("createDate") && !jsonObject.isNull("createDate")) {
			membership.setCreateDate(toUTCDate(jsonObject.get("createDate")));
		}
		else if (jsonObject.has("dateCreated") &&
				 !jsonObject.isNull("dateCreated")) {

			membership.setCreateDate(toUTCDate(jsonObject.get("dateCreated")));
		}

		if (jsonObject.has("id") && !jsonObject.isNull("id")) {
			membership.setId(jsonObject.getLong("id"));
		}

		if (jsonObject.has("individualId") &&
			!jsonObject.isNull("individualId")) {

			membership.setIndividualId(jsonObject.getLong("individualId"));
		}

		if (jsonObject.has("individualSegmentId") &&
			!jsonObject.isNull("individualSegmentId")) {

			membership.setIndividualSegmentId(
				jsonObject.getLong("individualSegmentId"));
		}

		if (jsonObject.has("modifiedDate") &&
			!jsonObject.isNull("modifiedDate")) {

			membership.setModifiedDate(
				toUTCDate(jsonObject.get("modifiedDate")));
		}
		else if (jsonObject.has("dateModified") &&
				 !jsonObject.isNull("dateModified")) {

			membership.setModifiedDate(
				toUTCDate(jsonObject.get("dateModified")));
		}

		if (jsonObject.has("removedDate") &&
			!jsonObject.isNull("removedDate")) {

			membership.setRemovedDate(toUTCDate(jsonObject.get("removedDate")));
		}
		else if (jsonObject.has("dateRemoved") &&
				 !jsonObject.isNull("dateRemoved")) {

			membership.setRemovedDate(toUTCDate(jsonObject.get("dateRemoved")));
		}

		if (jsonObject.has("status") && !jsonObject.isNull("status")) {
			membership.setStatus(jsonObject.getString("status"));
		}

		return membership;
	}

}