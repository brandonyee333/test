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
import com.liferay.osb.asah.common.util.SetUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.BooleanUtils;
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

		Segment segment = _segmentDog.updateSegment(
			membership.getIndividualSegmentId());

		_membershipChangeDog.addMembershipChange(
			individual, segment.getIndividualsCount(),
			segment.getKnownIndividualsCount(), membership, "ADDED");

		return membership;
	}

	public void addMemberships(
		Date createDate, List<Individual> individuals,
		Long individualSegmentId) {

		List<Membership> memberships = new ArrayList<>();

		for (Individual individual : individuals) {
			Membership membership = new Membership();

			membership.setCreateDate(createDate);
			membership.setIndividualId(individual.getId());
			membership.setIndividualSegmentId(individualSegmentId);
			membership.setModifiedDate(createDate);
			membership.setStatus("ACTIVE");

			memberships.add(membership);
		}

		_membershipRepository.saveAll(memberships);

		_individualDog.addSegmentId(individuals, individualSegmentId);

		Segment segment = _segmentDog.updateSegment(individualSegmentId);

		_membershipChangeDog.addMembershipChanges(
			createDate, individuals, segment.getIndividualsCount(),
			individualSegmentId, segment.getKnownIndividualsCount(), "ADDED");
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

		Segment segment = _segmentDog.updateSegment(
			membership.getIndividualSegmentId());

		long knownIndividualsCount = _individualDog.getKnownIndividualsCount(
			ListUtil.map(memberships, Membership::getIndividualId));

		_membershipChangeDog.addMembershipChanges(
			BooleanUtils.toBoolean(segment.getIncludeAnonymousUsers()),
			segment.getIndividualsCount() - memberships.size(),
			segment.getKnownIndividualsCount() - knownIndividualsCount,
			memberships);

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

		_individualDog.removeSegmentId(
			membership.getIndividualId(), membership.getIndividualSegmentId());

		Individual individual = _individualDog.fetchIndividual(
			membership.getIndividualId());

		Segment segment = _segmentDog.updateSegment(
			membership.getIndividualSegmentId());

		if (individual == null) {
			_membershipChangeDog.addMembershipChangeForDeletedIndividual(
				membership.getCreateDate(), membership.getIndividualId(),
				segment.getIndividualsCount(),
				segment.getKnownIndividualsCount(), deletionDate,
				membership.getIndividualSegmentId());
		}
		else {
			_membershipChangeDog.addMembershipChange(
				individual, segment.getIndividualsCount(),
				segment.getKnownIndividualsCount(), membership, "REMOVED");
		}
	}

	public void deactivateMembershipByIndividuals(
		Date deletionDate, List<Individual> individuals) {

		List<Membership> memberships =
			_membershipRepository.findByIndividualIdInAndStatus(
				ListUtil.map(individuals, Individual::getId), "ACTIVE");

		for (Membership membership : memberships) {
			membership.setModifiedDate(deletionDate);
			membership.setRemovedDate(deletionDate);
			membership.setStatus("INACTIVE");
		}

		_membershipRepository.saveAll(memberships);

		Stream<Membership> membershipsStream = memberships.stream();

		Map<Long, List<Membership>> membershipsMap = membershipsStream.collect(
			Collectors.groupingBy(Membership::getIndividualSegmentId));

		for (Map.Entry<Long, List<Membership>> entry :
				membershipsMap.entrySet()) {

			List<Long> individualIdsByMembership = ListUtil.map(
				entry.getValue(), Membership::getIndividualId);

			Stream<Individual> individualsStream = individuals.stream();

			List<Individual> individualsByMembership = individualsStream.filter(
				individual -> individualIdsByMembership.contains(
					individual.getId())
			).collect(
				Collectors.toList()
			);

			_individualDog.removeSegmentId(
				SetUtil.map(individualsByMembership, Individual::getId),
				entry.getKey());

			Segment segment = _segmentDog.updateSegment(entry.getKey());

			_membershipChangeDog.addMembershipChanges(
				deletionDate, individualsByMembership,
				segment.getIndividualsCount(), entry.getKey(),
				segment.getKnownIndividualsCount(), "REMOVED");
		}
	}

	public void deactivateMemberships(
		Date deletionDate, List<Membership> memberships) {

		for (Membership membership : memberships) {
			membership.setModifiedDate(deletionDate);
			membership.setRemovedDate(deletionDate);
			membership.setStatus("INACTIVE");
		}

		_membershipRepository.saveAll(memberships);

		Stream<Membership> stream = memberships.stream();

		Map<Long, List<Membership>> membershipsMap = stream.collect(
			Collectors.groupingBy(Membership::getIndividualSegmentId));

		for (Map.Entry<Long, List<Membership>> entry :
				membershipsMap.entrySet()) {

			_individualDog.removeSegmentId(
				SetUtil.map(entry.getValue(), Membership::getIndividualId),
				entry.getKey());

			Segment segment = _segmentDog.updateSegment(entry.getKey());

			for (Membership membership : entry.getValue()) {
				_membershipChangeDog.addMembershipChangeForDeletedIndividual(
					membership.getCreateDate(), membership.getIndividualId(),
					segment.getIndividualsCount(),
					segment.getKnownIndividualsCount(), deletionDate,
					membership.getIndividualSegmentId());
			}
		}
	}

	public void deactivateMemberships(Date deletionDate, Long individualId) {
		for (Membership membership :
				_membershipRepository.findByIndividualIdAndStatus(
					individualId, "ACTIVE")) {

			deactivateMembership(deletionDate, membership);
		}
	}

	public void deleteMemberships(List<Long> individualSegmentIds) {
		_membershipRepository.deleteByIndividualSegmentIdIn(
			individualSegmentIds);
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

	public long getIndividualsCount(Long individualSegmentId) {
		return _membershipRepository.countByIndividualSegmentIdAndStatus(
			individualSegmentId, "ACTIVE");
	}

	public List<Long> getIndividualSegmentIds(Long individualId) {
		return _membershipRepository.findTop20IndividualSegmentIdByIndividualId(
			individualId);
	}

	public Page<Membership> getMembershipPage(
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

	public Page<Membership> getMembershipPage(
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

	public boolean isIndividualInSegments(
		Long individualId, List<Long> individualSegmentIds, int max, int min,
		boolean ascending) {

		List<Long> individualIds =
			_membershipRepository.findIndividualIdByIndividualSegmentIdIn(
				individualId, individualSegmentIds, max, min, ascending);

		return !individualIds.isEmpty();
	}

	public List<Long> isMember(
		List<Long> individualIds, Long individualSegmentId) {

		return _membershipRepository.
			findIndividualIdByIndividualInAndIndividualSegmentIdAndStatus(
				individualIds, individualSegmentId, "ACTIVE");
	}

	public boolean isMember(Long individualId, Long individualSegmentId) {
		return _membershipRepository.
			existsByIndividualIdAndIndividualSegmentIdAndStatus(
				individualId, individualSegmentId, "ACTIVE");
	}

	public List<Membership> searchMemberships(
		Long individualSegmentId, Long membershipId, int size, String status) {

		return _membershipRepository.searchMemberships(
			membershipId, individualSegmentId, size, status);
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