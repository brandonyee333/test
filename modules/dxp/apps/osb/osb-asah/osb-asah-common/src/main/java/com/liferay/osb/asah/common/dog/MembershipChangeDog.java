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

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.util.SortUtil;
import com.liferay.osb.asah.common.entity.Individual;
import com.liferay.osb.asah.common.entity.Membership;
import com.liferay.osb.asah.common.entity.MembershipChange;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.faro.info.dog.BaseFaroInfoDog;
import com.liferay.osb.asah.common.faro.info.util.FaroInfoIndividualUtil;
import com.liferay.osb.asah.common.repository.MembershipChangeRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.time.DateUtils;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Component;

/**
 * @author Inácio Nery
 */
@Component
public class MembershipChangeDog extends BaseFaroInfoDog {

	public void addMembershipChange(
		Individual individual, long individualsCount,
		long knownIndividualsCount, Membership membership, String operation) {

		MembershipChange membershipChange = new MembershipChange();

		membershipChange.setIndividualDeleted(Boolean.FALSE);
		membershipChange.setIndividualEmail(
			FaroInfoIndividualUtil.getIndividualEmail(individual));
		membershipChange.setIndividualId(membership.getIndividualId());
		membershipChange.setIndividualName(
			FaroInfoIndividualUtil.getIndividualName(individual));
		membershipChange.setIndividualsCount(individualsCount);
		membershipChange.setIndividualSegmentId(
			membership.getIndividualSegmentId());
		membershipChange.setJoinedDate(membership.getCreateDate());
		membershipChange.setKnownIndividualsCount(knownIndividualsCount);
		membershipChange.setModifiedDate(membership.getModifiedDate());
		membershipChange.setOperation(operation);

		_membershipChangeRepository.save(membershipChange);
	}

	public void addMembershipChange(MembershipChange membershipChange) {
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

			Individual individual = _individualDog.getIndividual(individualId);

			String individualEmail = FaroInfoIndividualUtil.getIndividualEmail(
				individual);

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
				FaroInfoIndividualUtil.getIndividualName(individual));
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

	public Map<Long, JSONObject> getAccountNamesJSONObjects(
		List<MembershipChange> membershipChanges) {

		Map<Long, JSONObject> accountNamesJSONObjects = new HashMap<>();

		List<Individual> individuals = new ArrayList<>();

		for (MembershipChange membershipChange : membershipChanges) {
			individuals.add(
				_individualDog.fetchIndividual(
					membershipChange.getIndividualId()));
		}

		Map<Long, JSONObject> individualAccountNamesJSONObjects =
			_accountDog.getAccountNamesJSONObjects(individuals);

		for (MembershipChange membershipChange : membershipChanges) {
			accountNamesJSONObjects.put(
				membershipChange.getId(),
				individualAccountNamesJSONObjects.get(
					membershipChange.getIndividualId()));
		}

		return accountNamesJSONObjects;
	}

	public List<MembershipChange> getLastFrom30DaysByIndividualSegmentsId(
		List<Long> individualSegmentIds) {

		Date date = DateUtil.newDayDate();

		return _membershipChangeRepository.
			searchLastByDateChangedPeriodAndIndividualSegmentId(
				DateUtil.newEndOfDayDate(DateUtils.addDays(date, -1)),
				DateUtil.newBeginningOfDayDate(DateUtils.addDays(date, -31)),
				false, individualSegmentIds);
	}

	public Page<MembershipChange> searchMembershipChangesPages(
		String filterString, Long segmentId, int page, int size,
		String[] sorts) {

		PageRequest pageRequest = PageRequest.of(
			page, size, SortUtil.getSort(sorts));

		Segment segment = _segmentDog.getSegment(segmentId);

		return PageableExecutionUtils.getPage(
			_membershipChangeRepository.searchMembershipChanges(
				filterString, segment.getIncludeAnonymousUsers(), segmentId,
				pageRequest),
			pageRequest,
			() -> _membershipChangeRepository.countMembershipChanges(
				filterString, segment.getIncludeAnonymousUsers(), segmentId));
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
	private AccountDog _accountDog;

	@Autowired
	private IndividualDog _individualDog;

	@Autowired
	private MembershipChangeRepository _membershipChangeRepository;

	@Autowired
	private SegmentDog _segmentDog;

}