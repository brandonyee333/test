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

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.entity.BQMembership;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.Individual;
import com.liferay.osb.asah.common.repository.BQMembershipRepository;
import com.liferay.osb.asah.common.util.ListUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Component;

/**
 * @author Michael Bowerman
 * @author Rachael Koestartyo
 */
@Component
public class BQMembershipDog {

	public BQMembership addBQMembership(BQMembership bqMembership) {
		return _bqMembershipRepository.save(bqMembership);
	}

	public void addBQMemberships(
		Date createDate, List<Individual> individuals, Long segmentId) {

		List<BQMembership> bqMemberships = new ArrayList<>();

		for (Individual individual : individuals) {
			BQMembership bqMembership = new BQMembership();

			bqMembership.setCreateDate(createDate);
			bqMembership.setIdentityId(String.valueOf(individual.getId()));
			bqMembership.setModifiedDate(createDate);
			bqMembership.setSegmentId(segmentId);
			bqMembership.setStatus("ACTIVE");

			bqMemberships.add(bqMembership);
		}

		_bqMembershipRepository.saveAll(bqMemberships);
	}

	public List<BQMembership> addBQMemberships(
		List<BQMembership> bqMemberships) {

		if (bqMemberships.isEmpty()) {
			return bqMemberships;
		}

		_bqMembershipRepository.saveAll(bqMemberships);

		_membershipChangeDog.addBQMembershipChange(bqMemberships);

		return bqMemberships;
	}

	public void deleteBQMembership(String individualId, Long segmentId) {
		_bqMembershipRepository.deleteByIndividualIdAndSegmentId(
			individualId, segmentId);
	}

	public void deleteBQMemberships(List<Long> segmentIds) {
		_bqMembershipRepository.deleteBySegmentIdIn(segmentIds);
	}

	public List<BQMembership> getActiveBQMemberships(
		String identityId, List<Long> segmentIds) {

		return _bqMembershipRepository.findByIdentityIdAndSegmentIdInAndStatus(
			identityId, segmentIds, "ACTIVE");
	}

	public List<String> getActiveIdentityIds(Long segmentId) {
		return _bqMembershipRepository.findIdentityIdBySegmentIdAndStatus(
			segmentId, "ACTIVE");
	}

	public List<Map<String, Long>> getActiveSegmentIds(String identityId) {
		return _bqMembershipRepository.
			findSegmentIdIdentitiesCountByIdentityIdAndStatusAnd(
				identityId, "ACTIVE");
	}

	public Page<BQMembership> getBQMembershipPage(
		List<String> identityIds, Long segmentId, String status, int page,
		int size, String[] sorts) {

		PageRequest pageRequest = PageRequest.of(page, size, _getSort(sorts));

		return PageableExecutionUtils.getPage(
			_bqMembershipRepository.findByIdentityIdInAndSegmentIdAndStatus(
				identityIds, segmentId, status, pageRequest),
			pageRequest,
			() ->
				_bqMembershipRepository.
					countByIdentityIdInAndSegmentIdAndStatus(
						identityIds, segmentId, status));
	}

	public Page<BQMembership> getBQMembershipPage(
		Long segmentId, String status, int page, int size, String[] sorts) {

		PageRequest pageRequest = PageRequest.of(page, size, _getSort(sorts));

		return PageableExecutionUtils.getPage(
			_bqMembershipRepository.findBySegmentIdAndStatus(
				segmentId, status, pageRequest),
			pageRequest,
			() -> _bqMembershipRepository.countBySegmentIdAndStatus(
				segmentId, status));
	}

	public long getBQMembershipsCount(Long segmentId) {
		return _bqMembershipRepository.countBySegmentId(segmentId);
	}

	public Map<Long, JSONObject> getMembershipsJSONObjects(
		String identityId, List<Segment> segments) {

		List<BQMembership> bqMemberships = getActiveBQMemberships(
			identityId, ListUtil.map(segments, Segment::getId));

		Map<Long, JSONObject> segmentJSONObjects = new HashMap<>();

		for (BQMembership bqMembership : bqMemberships) {
			segmentJSONObjects.put(
				bqMembership.getSegmentId(),
				_objectMapper.convertValue(bqMembership, JSONObject.class));
		}

		Map<Long, JSONObject> membershipsJSONObjects = new HashMap<>();

		for (Segment segment : segments) {
			Long segmentId = segment.getId();

			if (!segmentJSONObjects.containsKey(segmentId)) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"Unable to get active membership for user " +
							identityId + "and individual segment " + segmentId);
				}

				continue;
			}

			JSONObject membershipJSONObject = segmentJSONObjects.get(segmentId);

			membershipJSONObject.remove("dateModified");
			membershipJSONObject.remove("dateRemoved");
			membershipJSONObject.remove("id");

			membershipsJSONObjects.put(
				segmentId,
				JSONUtil.put("active-membership", membershipJSONObject));
		}

		return membershipsJSONObjects;
	}

	public List<Long> getSegmentIds(String identityId) {
		return _bqMembershipRepository.findTop20SegmentIdByIdentityId(
			identityId);
	}

	public boolean isMember(String identityId, Long segmentId) {
		return _bqMembershipRepository.existsByIdentityIdAndSegmentIdAndStatus(
			identityId, segmentId, "ACTIVE");
	}

	public List<BQMembership> searchBQMemberships(
		Long segmentId, Long bqMembershipId, int size, String status) {

		return _bqMembershipRepository.searchBQMemberships(
			bqMembershipId, segmentId, size, status);
	}

	public void updateBQMemberships(String filterString, Long segmentId) {
		_bqMembershipRepository.updateBQMemberships(filterString, segmentId);
	}

	private Sort _getSort(String[] sorts) {
		if (ArrayUtils.isEmpty(sorts)) {
			return Sort.by(Sort.Order.desc("id"));
		}

		List<Sort.Order> orders = new ArrayList<>();

		for (int i = 0; i < (sorts.length - 1); i = i + 2) {
			if (Objects.equals(sorts[i + 1], "asc")) {
				orders.add(Sort.Order.asc(sorts[i]));
			}
			else {
				orders.add(Sort.Order.desc(sorts[i]));
			}
		}

		return Sort.by(orders);
	}

	private static final Log _log = LogFactory.getLog(BQMembershipDog.class);

	@Autowired
	private BQMembershipRepository _bqMembershipRepository;

	@Autowired
	private BQMembershipChangeDog _membershipChangeDog;

	@Autowired
	private ObjectMapper _objectMapper;

}