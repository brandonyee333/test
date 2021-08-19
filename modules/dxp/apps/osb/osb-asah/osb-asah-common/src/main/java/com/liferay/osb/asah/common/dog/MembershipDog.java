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

import com.liferay.osb.asah.common.entity.Individual;
import com.liferay.osb.asah.common.entity.Membership;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.faro.info.dog.BaseFaroInfoDog;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.repository.MembershipRepository;
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
public class MembershipDog extends BaseFaroInfoDog {

	public Membership addMembership(
		Date createDate, Long individualId, Long individualSegmentId,
		Date modifiedDate, String status) {

		Membership membership = new Membership();

		membership.setCreateDate(createDate);
		membership.setIndividualId(individualId);
		membership.setIndividualSegmentId(individualSegmentId);
		membership.setModifiedDate(createDate);
		membership.setStatus(status);

		return addMembership(membership);
	}

	public Membership addMembership(Membership membership) {
		membership = _membershipRepository.save(membership);

		String status = membership.getStatus();

		if (!status.equals("ACTIVE")) {
			return membership;
		}

		Individual individual = _individualDog.addSegmentId(
			membership.getIndividualId(), membership.getIndividualSegmentId());

		if (individual == null) {
			return null;
		}

		long knownIndividualCount = _individualDog.getKnownIndividualCount(
			membership.getIndividualSegmentId());

		long individualCount = 0;

		if (_segmentDog.isIncludeAnonymousUsers(
				membership.getIndividualSegmentId())) {

			individualCount = _getIndividualCount(
				membership.getIndividualSegmentId());
		}
		else {
			individualCount = knownIndividualCount;
		}

		_segmentDog.updateSegment(
			individualCount, knownIndividualCount,
			membership.getIndividualSegmentId());

		_membershipChangeDog.addMembershipChange(
			individual, individualCount, knownIndividualCount, membership,
			"ADDED");

		return membership;
	}

	public List<Membership> addMemberships(List<Membership> memberships) {
		if (memberships.isEmpty()) {
			return memberships;
		}

		_membershipRepository.saveAll(memberships);

		Membership membership = memberships.get(0);

		_individualDog.addIndividualSegmentIds(
			ListUtil.map(memberships, Membership::getIndividualId),
			membership.getIndividualSegmentId());

		boolean includeAnonymousUsers = _segmentDog.isIncludeAnonymousUsers(
			membership.getIndividualSegmentId());

		long knownIndividualSegmentCount =
			_individualDog.getKnownIndividualCount(
				membership.getIndividualSegmentId());

		long individualCount = 0;

		if (includeAnonymousUsers) {
			individualCount = _getIndividualCount(
				membership.getIndividualSegmentId());
		}
		else {
			individualCount = knownIndividualSegmentCount;
		}

		_segmentDog.updateSegment(
			individualCount, knownIndividualSegmentCount,
			membership.getIndividualSegmentId());

		long knownIndividualCount = _individualDog.getKnownIndividualCount(
			ListUtil.map(memberships, Membership::getIndividualId));

		_membershipChangeDog.addMembershipChanges(
			includeAnonymousUsers, individualCount - memberships.size(),
			knownIndividualSegmentCount - knownIndividualCount, memberships);

		return memberships;
	}

	public void deactivateMembership(
		Date deletionDate, Long individualId, Long individualSegmentId) {

		deactivateMembership(
			deletionDate,
			_membershipRepository.
				findByIndividualIdAndIndividualSegmentIdAndStatus(
					individualId, individualSegmentId, "ACTIVE"));
	}

	public void deactivateMembership(Date deletionDate, Membership membership) {
		if (membership == null) {
			return;
		}

		membership.setModifiedDate(deletionDate);
		membership.setRemovedDate(deletionDate);
		membership.setStatus("INACTIVE");

		membership = _membershipRepository.save(membership);

		Individual individual = _individualDog.removeSegmentId(
			membership.getIndividualId(), membership.getIndividualSegmentId());

		boolean includeAnonymousUsers = _segmentDog.isIncludeAnonymousUsers(
			membership.getIndividualSegmentId());

		long knownIndividualCount = _individualDog.getKnownIndividualCount(
			membership.getIndividualSegmentId());

		long individualCount = 0;

		if (includeAnonymousUsers) {
			individualCount = _getIndividualCount(
				membership.getIndividualSegmentId());
		}
		else {
			individualCount = knownIndividualCount;
		}

		_segmentDog.updateSegment(
			individualCount, knownIndividualCount,
			membership.getIndividualSegmentId());

		if (individual == null) {
			_membershipChangeDog.addMembershipChangeForDeletedIndividual(
				membership.getIndividualId(), individualCount,
				knownIndividualCount, membership);
		}
		else {
			_membershipChangeDog.addMembershipChange(
				individual, individualCount, knownIndividualCount, membership,
				"REMOVED");
		}
	}

	public void deactivateMemberships(Date deletionDate, Long individualId) {
		for (Membership membership :
				_membershipRepository.findByIndividualIdAndStatus(
					individualId, "ACTIVE")) {

			deactivateMembership(deletionDate, membership);
		}
	}

	public void deleteMembership(Long individualSegmentId) {
		_membershipRepository.deleteByIndividualSegmentId(individualSegmentId);
	}

	public List<Long> getActiveIndividualIds(Long individualSegmentId) {
		return _membershipRepository.
			findIndividualIdByIndividualSegmentIdAndStatus(
				individualSegmentId, "ACTIVE");
	}

	public List<Long> getActiveIndividualSegmentIds(List<Long> individualIds) {
		return _membershipRepository.
			findIndividualSegmentIdByIndividualIdInAndStatus(
				individualIds, "ACTIVE");
	}

	public List<Long> getActiveIndividualSegmentIds(Long individualId) {
		return _membershipRepository.
			findIndividualSegmentIdByIndividualIdAndStatus(
				individualId, "ACTIVE");
	}

	public List<Membership> getActiveMemberships(
		Long individualId, List<Long> individualSegmentIds) {

		return _membershipRepository.
			findByIndividualIdAndIndividualSegmentIdInAndStatus(
				individualId, individualSegmentIds, "ACTIVE");
	}

	public List<Long> getIndividualIds(
		List<Long> individualSegmentIds, int max, int min, boolean ascending) {

		return _membershipRepository.findIndividualIdByIndividualSegmentIdIn(
			individualSegmentIds, max, min, ascending);
	}

	public List<Long> getIndividualSegmentIds(Long individualId) {
		return _membershipRepository.findTop20IndividualSegmentIdByIndividualId(
			individualId);
	}

	public List<Membership> getMemberships(
		Long individualSegmentId, String status) {

		return _membershipRepository.findByIndividualSegmentIdAndStatus(
			individualSegmentId, status);
	}

	public Map<Long, JSONObject> getMembershipsJSONObjects(
		Long individualId, List<Segment> segments) {

		List<Membership> memberships = getActiveMemberships(
			individualId, ListUtil.map(segments, Segment::getId));

		Map<Long, JSONObject> individualSegmentJSONObjects = new HashMap<>();

		for (Membership membership : memberships) {
			individualSegmentJSONObjects.put(
				membership.getIndividualSegmentId(),
				_objectMapper.convertValue(membership, JSONObject.class));
		}

		Map<Long, JSONObject> membershipsJSONObjects = new HashMap<>();

		for (Segment segment : segments) {
			Long segmentId = segment.getId();

			if (!individualSegmentJSONObjects.containsKey(segmentId)) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"Unable to get active membership for individual " +
							individualId + "and individual segment " +
								segmentId);
				}

				continue;
			}

			JSONObject membershipJSONObject = individualSegmentJSONObjects.get(
				segmentId);

			membershipJSONObject.remove("dateModified");
			membershipJSONObject.remove("dateRemoved");
			membershipJSONObject.remove("id");

			membershipsJSONObjects.put(
				segmentId,
				JSONUtil.put("active-membership", membershipJSONObject));
		}

		return membershipsJSONObjects;
	}

	public Page<Membership> getMembershipsPage(
		List<Long> individualIds, Long individualSegmentId, String status,
		int page, int size, String[] sorts) {

		PageRequest pageRequest = PageRequest.of(page, size, _getSort(sorts));

		return PageableExecutionUtils.getPage(
			_membershipRepository.
				findByIndividualIdInAndIndividualSegmentIdAndStatus(
					individualIds, individualSegmentId, status, pageRequest),
			pageRequest,
			() ->
				_membershipRepository.
					countByIndividualIdInAndIndividualSegmentIdAndStatus(
						individualIds, individualSegmentId, status));
	}

	public Page<Membership> getMembershipsPage(
		Long individualSegmentId, String status, int page, int size,
		String[] sorts) {

		PageRequest pageRequest = PageRequest.of(page, size, _getSort(sorts));

		return PageableExecutionUtils.getPage(
			_membershipRepository.findByIndividualSegmentIdAndStatus(
				individualSegmentId, status, pageRequest),
			pageRequest,
			() -> _membershipRepository.countByIndividualSegmentIdAndStatus(
				individualSegmentId, status));
	}

	public boolean isMember(Long individualId, Long individualSegmentId) {
		return _membershipRepository.
			existsByIndividualIdAndIndividualSegmentIdAndStatus(
				individualId, individualSegmentId, "ACTIVE");
	}

	private long _getIndividualCount(Long individualSegmentId) {
		return _membershipRepository.countByIndividualSegmentIdAndStatus(
			individualSegmentId, "ACTIVE");
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

	private static final Log _log = LogFactory.getLog(MembershipDog.class);

	@Autowired
	private IndividualDog _individualDog;

	@Autowired
	private MembershipChangeDog _membershipChangeDog;

	@Autowired
	private MembershipRepository _membershipRepository;

	@Autowired
	private ObjectMapper _objectMapper;

	@Autowired
	private SegmentDog _segmentDog;

}