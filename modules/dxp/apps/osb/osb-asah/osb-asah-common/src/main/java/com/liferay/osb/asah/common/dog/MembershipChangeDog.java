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
import com.liferay.osb.asah.common.entity.BQMembership;
import com.liferay.osb.asah.common.entity.BQMembershipChange;
import com.liferay.osb.asah.common.entity.Individual;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.faro.info.dog.BaseFaroInfoDog;
import com.liferay.osb.asah.common.faro.info.util.FaroInfoIndividualUtil;
import com.liferay.osb.asah.common.repository.BQMembershipChangeRepository;
import com.liferay.osb.asah.common.repository.helper.FilterHelper;

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

	public void addBQMembershipChange(BQMembershipChange bqMembershipChange) {
		_bqMembershipChangeRepository.save(bqMembershipChange);
	}

	public void addBQMembershipChange(
		Individual individual, long individualsCount,
		long knownIndividualsCount, BQMembership bqMembership,
		String operation) {

		BQMembershipChange bqMembershipChange = new BQMembershipChange();

		bqMembershipChange.setIndividualDeleted(Boolean.FALSE);
		bqMembershipChange.setIndividualEmail(
			FaroInfoIndividualUtil.getIndividualEmail(individual));
		bqMembershipChange.setIndividualId(bqMembership.getIndividualId());
		bqMembershipChange.setIndividualName(
			FaroInfoIndividualUtil.getIndividualName(individual));
		bqMembershipChange.setIndividualsCount(individualsCount);
		bqMembershipChange.setIndividualSegmentId(
			bqMembership.getIndividualSegmentId());
		bqMembershipChange.setJoinedDate(bqMembership.getCreateDate());
		bqMembershipChange.setKnownIndividualsCount(knownIndividualsCount);
		bqMembershipChange.setModifiedDate(bqMembership.getModifiedDate());
		bqMembershipChange.setOperation(operation);

		_bqMembershipChangeRepository.save(bqMembershipChange);
	}

	public void addBQMembershipChangeForDeletedIndividual(
		Date createDate, Long individualId, long individualsCount,
		long knownIndividualsCount, Date modifiedDate, Long segmentId) {

		BQMembershipChange bqMembershipChange = new BQMembershipChange();

		bqMembershipChange.setJoinedDate(createDate);
		bqMembershipChange.setIndividualDeleted(Boolean.TRUE);

		Optional<BQMembershipChange> bqMembershipChangeOptional =
			_bqMembershipChangeRepository.findByIndividualId(individualId);

		bqMembershipChangeOptional.map(
			BQMembershipChange::getIndividualEmail
		).ifPresent(
			individualEmail -> bqMembershipChange.setIndividualEmail(
				individualEmail)
		);

		bqMembershipChange.setIndividualId(individualId);

		bqMembershipChangeOptional.map(
			BQMembershipChange::getIndividualName
		).ifPresent(
			individualName -> bqMembershipChange.setIndividualName(
				individualName)
		);

		bqMembershipChange.setIndividualsCount(individualsCount);
		bqMembershipChange.setIndividualSegmentId(segmentId);
		bqMembershipChange.setKnownIndividualsCount(knownIndividualsCount);
		bqMembershipChange.setModifiedDate(modifiedDate);
		bqMembershipChange.setOperation("REMOVED");

		_bqMembershipChangeRepository.save(bqMembershipChange);
	}

	public void addBQMembershipChanges(
		boolean includeAnonymousUsers, long individualsCount,
		long knownIndividualsCount, List<BQMembership> bqMemberships) {

		List<BQMembershipChange> bqMembershipChanges = new ArrayList<>();

		for (BQMembership bqMembership : bqMemberships) {
			Long individualId = bqMembership.getIndividualId();

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

			BQMembershipChange bqMembershipChange = new BQMembershipChange();

			bqMembershipChange.setIndividualDeleted(Boolean.FALSE);
			bqMembershipChange.setIndividualEmail(individualEmail);
			bqMembershipChange.setIndividualId(individualId);
			bqMembershipChange.setIndividualName(
				FaroInfoIndividualUtil.getIndividualName(individual));
			bqMembershipChange.setIndividualsCount(individualsCount);
			bqMembershipChange.setIndividualSegmentId(
				bqMembership.getIndividualSegmentId());
			bqMembershipChange.setJoinedDate(bqMembership.getCreateDate());
			bqMembershipChange.setKnownIndividualsCount(knownIndividualsCount);
			bqMembershipChange.setModifiedDate(bqMembership.getModifiedDate());
			bqMembershipChange.setOperation("ADDED");

			bqMembershipChanges.add(bqMembershipChange);
		}

		_bqMembershipChangeRepository.saveAll(bqMembershipChanges);
	}

	public void addBQMembershipChanges(
		Date createDate, List<Individual> individuals, long individualsCount,
		Long individualSegmentId, long knownIndividualsCount,
		String operation) {

		List<BQMembershipChange> bqMembershipChanges = new ArrayList<>();

		for (Individual individual : individuals) {
			BQMembershipChange bqMembershipChange = new BQMembershipChange();

			bqMembershipChange.setIndividualDeleted(Boolean.FALSE);
			bqMembershipChange.setIndividualEmail(
				FaroInfoIndividualUtil.getIndividualEmail(individual));
			bqMembershipChange.setIndividualId(individual.getId());
			bqMembershipChange.setIndividualName(
				FaroInfoIndividualUtil.getIndividualName(individual));
			bqMembershipChange.setIndividualsCount(individualsCount);
			bqMembershipChange.setIndividualSegmentId(individualSegmentId);
			bqMembershipChange.setJoinedDate(createDate);
			bqMembershipChange.setKnownIndividualsCount(knownIndividualsCount);
			bqMembershipChange.setModifiedDate(createDate);
			bqMembershipChange.setOperation(operation);

			bqMembershipChanges.add(bqMembershipChange);
		}

		_bqMembershipChangeRepository.saveAll(bqMembershipChanges);
	}

	public void deleteBQMembershipChanges(List<Long> individualSegmentIds) {
		_bqMembershipChangeRepository.deleteByIndividualSegmentIdIn(
			individualSegmentIds);
	}

	public Map<Long, JSONObject> getAccountNamesJSONObjects(
		List<BQMembershipChange> bqMembershipChanges) {

		Map<Long, JSONObject> accountNamesJSONObjects = new HashMap<>();

		List<Individual> individuals = new ArrayList<>();

		for (BQMembershipChange bqMembershipChange : bqMembershipChanges) {
			Individual individual = _individualDog.fetchIndividual(
				bqMembershipChange.getIndividualId());

			if (individual != null) {
				individuals.add(individual);
			}
		}

		Map<Long, JSONObject> individualAccountNamesJSONObjects =
			_accountDog.getAccountNamesJSONObjects(individuals);

		for (BQMembershipChange bqMembershipChange : bqMembershipChanges) {
			accountNamesJSONObjects.put(
				bqMembershipChange.getId(),
				individualAccountNamesJSONObjects.get(
					bqMembershipChange.getIndividualId()));
		}

		return accountNamesJSONObjects;
	}

	public List<BQMembershipChange> getLastBeforeTodayByIndividualSegmentsId(
		boolean includeAnonymous, List<Long> individualSegmentIds) {

		Date date = DateUtil.newDayDate();

		return _bqMembershipChangeRepository.
			searchLastByModifiedDateAndIndividualSegmentId(
				null, includeAnonymous, individualSegmentIds,
				DateUtil.newEndOfDayDate(DateUtils.addDays(date, -1)));
	}

	public List<BQMembershipChange> getLastBeforeTodayByIndividualSegmentsId(
		List<Long> individualSegmentIds) {

		return getLastBeforeTodayByIndividualSegmentsId(
			false, individualSegmentIds);
	}

	public Page<BQMembershipChange> searchBQMembershipChangePages(
		String filterString, Long segmentId, int page, int size,
		String[] sorts) {

		FilterHelper filterHelper = new FilterHelper(filterString);

		PageRequest pageRequest = PageRequest.of(
			page, size, SortUtil.getSort(sorts));

		Segment segment = _segmentDog.getSegment(segmentId);

		return PageableExecutionUtils.getPage(
			_bqMembershipChangeRepository.searchBQMembershipChanges(
				filterHelper, segment.getIncludeAnonymousUsers(), segmentId,
				pageRequest),
			pageRequest,
			() -> _bqMembershipChangeRepository.countBQMembershipChanges(
				filterHelper, segment.getIncludeAnonymousUsers(), segmentId));
	}

	public void updateBQMembershipChangeIndividualDeleted(
		Boolean individualDeleted, List<Long> individualIds) {

		_bqMembershipChangeRepository.updateIndividualDeletedByIndividualIdIn(
			individualDeleted, individualIds);
	}

	public void updateBQMembershipChangeIndividualDeleted(
		Boolean individualDeleted, Long individualId) {

		_bqMembershipChangeRepository.updateIndividualDeletedByIndividualId(
			individualDeleted, individualId);
	}

	public void updateIndividualNameForIndividual(
		Long individualId, String individualName) {

		_bqMembershipChangeRepository.updateIndividualNameByIndividualId(
			individualId, individualName);
	}

	@Autowired
	private AccountDog _accountDog;

	@Autowired
	private BQMembershipChangeRepository _bqMembershipChangeRepository;

	@Autowired
	private IndividualDog _individualDog;

	@Autowired
	private SegmentDog _segmentDog;

}