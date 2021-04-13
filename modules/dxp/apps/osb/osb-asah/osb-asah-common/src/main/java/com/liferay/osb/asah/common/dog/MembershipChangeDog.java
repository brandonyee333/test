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

package com.liferay.osb.asah.common.dog;

import com.liferay.osb.asah.common.faro.info.dog.BaseFaroInfoDog;
import com.liferay.osb.asah.common.faro.info.util.FaroInfoIndividualUtil;
import com.liferay.osb.asah.common.model.Membership;
import com.liferay.osb.asah.common.model.MembershipChange;
import com.liferay.osb.asah.common.repository.MembershipChangeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Inácio Nery
 */
@Component
public class MembershipChangeDog extends BaseFaroInfoDog {

	public void addMembershipChange(
		JSONObject individualJSONObject, long individualsCount,
		long knownIndividualsCount, Membership membership, String operation) {

		MembershipChange membershipChange = new MembershipChange();

		membershipChange.setIndividualDeleted(Boolean.FALSE);

		JSONObject demographicsJSONObject = individualJSONObject.optJSONObject(
			"demographics");

		membershipChange.setIndividualEmail(
			FaroInfoIndividualUtil.getIndividualEmail(demographicsJSONObject));

		membershipChange.setIndividualId(membership.getIndividualId());
		membershipChange.setIndividualName(
			FaroInfoIndividualUtil.getIndividualName(demographicsJSONObject));
		membershipChange.setIndividualsCount(individualsCount);
		membershipChange.setIndividualSegmentId(
			membership.getIndividualSegmentId());
		membershipChange.setJoinedDate(membership.getCreateDate());
		membershipChange.setKnownIndividualsCount(knownIndividualsCount);
		membershipChange.setModifiedDate(membership.getModifiedDate());
		membershipChange.setOperation(operation);

		_membershipChangeRepository.save(membershipChange);
	}

	public void addMembershipChangeForDeletedIndividual(
		Long individualId, long individualsCount, long knownIndividualsCount,
		Membership membership) {

		MembershipChange membershipChange = new MembershipChange();

		membershipChange.setJoinedDate(membership.getCreateDate());
		membershipChange.setIndividualDeleted(Boolean.TRUE);

		Optional<MembershipChange> membershipChangeOptional =
			_membershipChangeRepository.findByIndividualId(individualId);

		membershipChangeOptional.map(
			MembershipChange::getIndividualEmail
		).ifPresent(
			individualEmail -> membershipChange.setIndividualEmail(
				individualEmail)
		);

		membershipChange.setIndividualId(membership.getIndividualId());

		membershipChangeOptional.map(
			MembershipChange::getIndividualName
		).ifPresent(
			individualName -> membershipChange.setIndividualName(individualName)
		);

		membershipChange.setIndividualsCount(individualsCount);
		membershipChange.setIndividualSegmentId(
			membership.getIndividualSegmentId());
		membershipChange.setKnownIndividualsCount(knownIndividualsCount);
		membershipChange.setModifiedDate(membership.getModifiedDate());
		membershipChange.setOperation("REMOVED");

		_membershipChangeRepository.save(membershipChange);
	}

	public void addMembershipChanges(
		boolean includeAnonymousUsers, long individualsCount,
		long knownIndividualsCount, List<Membership> memberships) {

		List<MembershipChange> membershipChanges = new ArrayList<>();

		for (Membership membership : memberships) {
			Long individualId = membership.getIndividualId();

			JSONObject individualJSONObject = elasticsearchInvoker.get(
				"individuals", String.valueOf(individualId));

			JSONObject demographicsJSONObject =
				individualJSONObject.optJSONObject("demographics");

			String individualEmail = FaroInfoIndividualUtil.getIndividualEmail(
				demographicsJSONObject);

			if (individualEmail != null) {
				knownIndividualsCount++;
			}

			if (includeAnonymousUsers ||
				(!includeAnonymousUsers && (individualEmail != null))) {

				individualsCount++;
			}

			MembershipChange membershipChange = new MembershipChange();

			membershipChange.setIndividualDeleted(Boolean.FALSE);
			membershipChange.setIndividualEmail(individualEmail);
			membershipChange.setIndividualId(individualId);
			membershipChange.setIndividualName(
				FaroInfoIndividualUtil.getIndividualName(
					demographicsJSONObject));
			membershipChange.setIndividualsCount(individualsCount);
			membershipChange.setIndividualSegmentId(
				membership.getIndividualSegmentId());
			membershipChange.setJoinedDate(membership.getCreateDate());
			membershipChange.setKnownIndividualsCount(knownIndividualsCount);
			membershipChange.setModifiedDate(membership.getModifiedDate());
			membershipChange.setOperation("ADDED");

			membershipChanges.add(membershipChange);
		}

		_membershipChangeRepository.saveAll(membershipChanges);
	}

	public void deleteMembershipChanges(Long individualSegmentId) {
		_membershipChangeRepository.deleteByIndividualSegmentId(
			individualSegmentId);
	}

	public void updateIndividualNameForIndividual(
		Long individualId, String individualName) {

		_membershipChangeRepository.updateIndividualNameByIndividualId(
			individualId, individualName);
	}

	public void updateMembershipChangeIndividualDeleted(
		Boolean individualDeleted, Long individualId) {

		_membershipChangeRepository.updateIndividualDeletedByIndividualId(
			individualDeleted, individualId);
	}

	@Autowired
	private MembershipChangeRepository _membershipChangeRepository;

}