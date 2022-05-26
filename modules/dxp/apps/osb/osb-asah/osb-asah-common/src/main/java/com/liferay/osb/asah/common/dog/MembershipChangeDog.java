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
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.faro.info.dog.BaseFaroInfoDog;
import com.liferay.osb.asah.common.faro.info.util.FaroInfoIndividualUtil;
import com.liferay.osb.asah.common.repository.BQMembershipChangeRepository;
import com.liferay.osb.asah.common.repository.helper.FilterHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateUtils;

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

	public void addBQMembershipChanges(
		boolean includeAnonymousUsers, long individualsCount,
		long knownIndividualsCount, List<BQMembership> bqMemberships) {

		List<BQMembershipChange> bqMembershipChanges = new ArrayList<>();

		for (BQMembership bqMembership : bqMemberships) {
			String individualEmail = FaroInfoIndividualUtil.getIndividualEmail(
				_individualDog.getIndividual(bqMembership.getIndividualId()));

			if (individualEmail != null) {
				knownIndividualsCount++;
			}

			if (includeAnonymousUsers ||
				(!includeAnonymousUsers && (individualEmail != null))) {

				individualsCount++;
			}

			BQMembershipChange bqMembershipChange = new BQMembershipChange();

			bqMembershipChange.setCreateDate(bqMembership.getCreateDate());
			bqMembershipChange.setIndividualsCount(individualsCount);
			bqMembershipChange.setIndividualSegmentId(
				bqMembership.getIndividualSegmentId());
			bqMembershipChange.setKnownIndividualsCount(knownIndividualsCount);

			bqMembershipChanges.add(bqMembershipChange);
		}

		_bqMembershipChangeRepository.saveAll(bqMembershipChanges);
	}

	public void deleteBQMembershipChanges(List<Long> individualSegmentIds) {
		_bqMembershipChangeRepository.deleteByIndividualSegmentIdIn(
			individualSegmentIds);
	}

	public Map<Long, BQMembershipChange> getBQMembershipChanges(
		List<Segment> segments) {

		Map<Long, BQMembershipChange> bqMembershipChanges = new HashMap<>();

		List<Long> individualSegmentIds = new ArrayList<>();

		for (Segment segment : segments) {
			individualSegmentIds.add(segment.getId());
		}

		for (BQMembershipChange bqMembershipChange :
				getLastBeforeTodayByIndividualSegmentsId(
					individualSegmentIds)) {

			bqMembershipChanges.put(
				bqMembershipChange.getIndividualSegmentId(),
				bqMembershipChange);
		}

		return bqMembershipChanges;
	}

	public BQMembershipChange getLastBeforeTodayByIndividualSegmentId(
		Long individualSegmentId) {

		List<BQMembershipChange> bqMembershipChanges =
			getLastBeforeTodayByIndividualSegmentsId(
				Collections.singletonList(individualSegmentId));

		if (bqMembershipChanges.isEmpty()) {
			return null;
		}

		return bqMembershipChanges.get(0);
	}

	public List<BQMembershipChange> getLastBeforeTodayByIndividualSegmentsId(
		List<Long> individualSegmentIds) {

		return _bqMembershipChangeRepository.
			searchLastByCreateDateAndIndividualSegmentId(
				null, individualSegmentIds,
				DateUtil.newEndOfDayDate(
					DateUtils.addDays(DateUtil.newDayDate(), -1)));
	}

	public Page<BQMembershipChange> searchBQMembershipChangePages(
		String filterString, Long segmentId, int page, int size,
		String[] sorts) {

		FilterHelper filterHelper = new FilterHelper(filterString);

		PageRequest pageRequest = PageRequest.of(
			page, size, SortUtil.getSort(sorts));

		return PageableExecutionUtils.getPage(
			_bqMembershipChangeRepository.searchBQMembershipChanges(
				filterHelper, segmentId, pageRequest),
			pageRequest,
			() -> _bqMembershipChangeRepository.countBQMembershipChanges(
				filterHelper, segmentId));
	}

	@Autowired
	private BQMembershipChangeRepository _bqMembershipChangeRepository;

	@Autowired
	private IndividualDog _individualDog;

}