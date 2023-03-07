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

import com.liferay.osb.asah.common.dog.util.SortUtil;
import com.liferay.osb.asah.common.entity.BQMembership;
import com.liferay.osb.asah.common.entity.BQMembershipChange;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.repository.BQMembershipChangeRepository;
import com.liferay.osb.asah.common.repository.SegmentRepository;
import com.liferay.osb.asah.common.repository.helper.FilterHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Component;

/**
 * @author Inácio Nery
 */
@Component
public class BQMembershipChangeDog {

	public void addBQMembershipChange(BQMembershipChange bqMembershipChange) {
		_bqMembershipChangeRepository.insert(bqMembershipChange);
	}

	public void addBQMembershipChange(List<BQMembership> bqMemberships) {
		BQMembership bqMembership = bqMemberships.get(0);

		addMembershipChange(bqMembership.getSegmentId());
	}

	public void addMembershipChange(Long segmentId) {
		_bqMembershipChangeRepository.addBQMembershipChange(segmentId);
	}

	public void deleteBQMembershipChanges(List<Long> segmentIds) {
		_bqMembershipChangeRepository.deleteBySegmentIdIn(segmentIds);
	}

	public List<Long> findSegmentIdByFilterString(String filterString) {
		return _bqMembershipChangeRepository.findSegmentIdByFilterString(
			filterString);
	}

	public BQMembershipChange getLastBeforeTodayBySegmentId(Long segmentId) {
		List<BQMembershipChange> bqMembershipChanges =
			_bqMembershipChangeRepository.
				findLastBQMembershipChangeBySegmentIds(
					Collections.singletonList(segmentId));

		if (bqMembershipChanges.isEmpty()) {
			return null;
		}

		return bqMembershipChanges.get(0);
	}

	public Map<Long, BQMembershipChange> getLastBQMembershipChanges(
		List<Segment> segments) {

		Map<Long, BQMembershipChange> bqMembershipChanges = new HashMap<>();

		List<Long> segmentIds = new ArrayList<>();

		for (Segment segment : segments) {
			segmentIds.add(segment.getId());
		}

		for (BQMembershipChange bqMembershipChange :
				_bqMembershipChangeRepository.
					findLastBQMembershipChangeBySegmentIds(segmentIds)) {

			bqMembershipChanges.put(
				bqMembershipChange.getSegmentId(), bqMembershipChange);
		}

		return bqMembershipChanges;
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
	private SegmentRepository _segmentRepository;

}