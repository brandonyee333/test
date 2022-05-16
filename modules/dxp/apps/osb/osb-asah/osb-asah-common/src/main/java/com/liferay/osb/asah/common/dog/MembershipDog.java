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
import com.liferay.osb.asah.common.entity.Individual;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.faro.info.dog.BaseFaroInfoDog;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.repository.BQMembershipRepository;
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

	public BQMembership addBQMembership(BQMembership bqMembership) {
		bqMembership = _bqMembershipRepository.save(bqMembership);

		String status = bqMembership.getStatus();

		if (!status.equals("ACTIVE")) {
			return bqMembership;
		}

		Individual individual = _individualDog.addSegmentId(
			bqMembership.getIndividualId(),
			bqMembership.getIndividualSegmentId());

		if (individual == null) {
			return null;
		}

		return bqMembership;
	}

	public void addBQMemberships(
		Date createDate, List<Individual> individuals,
		Long individualSegmentId) {

		List<BQMembership> bqMemberships = new ArrayList<>();

		for (Individual individual : individuals) {
			BQMembership bqMembership = new BQMembership();

			bqMembership.setCreateDate(createDate);
			bqMembership.setIndividualId(individual.getId());
			bqMembership.setIndividualSegmentId(individualSegmentId);
			bqMembership.setModifiedDate(createDate);
			bqMembership.setStatus("ACTIVE");

			bqMemberships.add(bqMembership);
		}

		_bqMembershipRepository.saveAll(bqMemberships);

		_individualDog.addSegmentId(individuals, individualSegmentId);
	}

	public List<BQMembership> addBQMemberships(
		List<BQMembership> bqMemberships) {

		if (bqMemberships.isEmpty()) {
			return bqMemberships;
		}

		_bqMembershipRepository.saveAll(bqMemberships);

		BQMembership bqMembership = bqMemberships.get(0);

		_individualDog.addIndividualSegmentIds(
			ListUtil.map(bqMemberships, BQMembership::getIndividualId),
			bqMembership.getIndividualSegmentId());

		Segment segment = _segmentDog.getSegment(
			bqMembership.getIndividualSegmentId());

		long knownIndividualsCount = _individualDog.getKnownIndividualsCount(
			ListUtil.map(bqMemberships, BQMembership::getIndividualId));

		_membershipChangeDog.addBQMembershipChanges(
			BooleanUtils.toBoolean(segment.getIncludeAnonymousUsers()),
			bqMemberships.size(), knownIndividualsCount, bqMemberships);

		return bqMemberships;
	}

	public void deactivateBQMembership(
		Date deletionDate, BQMembership bqMembership) {

		if (bqMembership == null) {
			return;
		}

		bqMembership.setModifiedDate(deletionDate);
		bqMembership.setRemovedDate(deletionDate);
		bqMembership.setStatus("INACTIVE");

		bqMembership = _bqMembershipRepository.save(bqMembership);

		_individualDog.removeSegmentId(
			bqMembership.getIndividualId(),
			bqMembership.getIndividualSegmentId());
	}

	public void deactivateBQMembership(
		Date deletionDate, Long individualId, Long individualSegmentId) {

		deactivateBQMembership(
			deletionDate,
			_bqMembershipRepository.
				findByIndividualIdAndIndividualSegmentIdAndStatus(
					individualId, individualSegmentId, "ACTIVE"));
	}

	public void deactivateBQMembershipByIndividuals(
		Date deletionDate, List<Individual> individuals) {

		List<BQMembership> bqMemberships =
			_bqMembershipRepository.findByIndividualIdInAndStatus(
				ListUtil.map(individuals, Individual::getId), "ACTIVE");

		for (BQMembership bqMembership : bqMemberships) {
			bqMembership.setModifiedDate(deletionDate);
			bqMembership.setRemovedDate(deletionDate);
			bqMembership.setStatus("INACTIVE");
		}

		_bqMembershipRepository.saveAll(bqMemberships);

		Stream<BQMembership> bqMembershipsStream = bqMemberships.stream();

		Map<Long, List<BQMembership>> bqMembershipsMap =
			bqMembershipsStream.collect(
				Collectors.groupingBy(BQMembership::getIndividualSegmentId));

		for (Map.Entry<Long, List<BQMembership>> entry :
				bqMembershipsMap.entrySet()) {

			List<Long> individualIdsByMembership = ListUtil.map(
				entry.getValue(), BQMembership::getIndividualId);

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
		}
	}

	public void deactivateBQMemberships(
		Date deletionDate, List<BQMembership> bqMemberships) {

		for (BQMembership bqMembership : bqMemberships) {
			bqMembership.setModifiedDate(deletionDate);
			bqMembership.setRemovedDate(deletionDate);
			bqMembership.setStatus("INACTIVE");
		}

		_bqMembershipRepository.saveAll(bqMemberships);

		Stream<BQMembership> stream = bqMemberships.stream();

		Map<Long, List<BQMembership>> bqMembershipsMap = stream.collect(
			Collectors.groupingBy(BQMembership::getIndividualSegmentId));

		for (Map.Entry<Long, List<BQMembership>> entry :
				bqMembershipsMap.entrySet()) {

			_individualDog.removeSegmentId(
				SetUtil.map(entry.getValue(), BQMembership::getIndividualId),
				entry.getKey());
		}
	}

	public void deactivateBQMemberships(Date deletionDate, Long individualId) {
		for (BQMembership bqMembership :
				_bqMembershipRepository.findByIndividualIdAndStatus(
					individualId, "ACTIVE")) {

			deactivateBQMembership(deletionDate, bqMembership);
		}
	}

	public void deleteBQMemberships(List<Long> individualSegmentIds) {
		_bqMembershipRepository.deleteByIndividualSegmentIdIn(
			individualSegmentIds);
	}

	public List<BQMembership> getActiveBQMemberships(
		Long individualId, List<Long> individualSegmentIds) {

		return _bqMembershipRepository.
			findByIndividualIdAndIndividualSegmentIdInAndStatus(
				individualId, individualSegmentIds, "ACTIVE");
	}

	public List<Long> getActiveIndividualIds(Long individualSegmentId) {
		return _bqMembershipRepository.
			findIndividualIdByIndividualSegmentIdAndStatus(
				individualSegmentId, "ACTIVE");
	}

	public List<Long> getActiveIndividualSegmentIds(List<Long> individualIds) {
		return _bqMembershipRepository.
			findIndividualSegmentIdByIndividualIdInAndStatus(
				individualIds, "ACTIVE");
	}

	public List<Long> getActiveIndividualSegmentIds(Long individualId) {
		return _bqMembershipRepository.
			findIndividualSegmentIdByIndividualIdAndStatus(
				individualId, "ACTIVE");
	}

	public Page<BQMembership> getBQMembershipPage(
		List<Long> individualIds, Long individualSegmentId, String status,
		int page, int size, String[] sorts) {

		PageRequest pageRequest = PageRequest.of(page, size, _getSort(sorts));

		return PageableExecutionUtils.getPage(
			_bqMembershipRepository.
				findByIndividualIdInAndIndividualSegmentIdAndStatus(
					individualIds, individualSegmentId, status, pageRequest),
			pageRequest,
			() ->
				_bqMembershipRepository.
					countByIndividualIdInAndIndividualSegmentIdAndStatus(
						individualIds, individualSegmentId, status));
	}

	public Page<BQMembership> getBQMembershipPage(
		Long individualSegmentId, String status, int page, int size,
		String[] sorts) {

		PageRequest pageRequest = PageRequest.of(page, size, _getSort(sorts));

		return PageableExecutionUtils.getPage(
			_bqMembershipRepository.findByIndividualSegmentIdAndStatus(
				individualSegmentId, status, pageRequest),
			pageRequest,
			() -> _bqMembershipRepository.countByIndividualSegmentIdAndStatus(
				individualSegmentId, status));
	}

	public List<Long> getIndividualIds(
		List<Long> individualSegmentIds, int max, int min, boolean ascending) {

		return _bqMembershipRepository.findIndividualIdByIndividualSegmentIdIn(
			individualSegmentIds, max, min, ascending);
	}

	public long getIndividualsCount(Long individualSegmentId) {
		return _bqMembershipRepository.countByIndividualSegmentIdAndStatus(
			individualSegmentId, "ACTIVE");
	}

	public List<Long> getIndividualSegmentIds(Long individualId) {
		return _bqMembershipRepository.
			findTop20IndividualSegmentIdByIndividualId(individualId);
	}

	public Map<Long, JSONObject> getMembershipsJSONObjects(
		Long individualId, List<Segment> segments) {

		List<BQMembership> bqMemberships = getActiveBQMemberships(
			individualId, ListUtil.map(segments, Segment::getId));

		Map<Long, JSONObject> individualSegmentJSONObjects = new HashMap<>();

		for (BQMembership bqMembership : bqMemberships) {
			individualSegmentJSONObjects.put(
				bqMembership.getIndividualSegmentId(),
				_objectMapper.convertValue(bqMembership, JSONObject.class));
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
			_bqMembershipRepository.findIndividualIdByIndividualSegmentIdIn(
				individualId, individualSegmentIds, max, min, ascending);

		return !individualIds.isEmpty();
	}

	public List<Long> isMember(
		List<Long> individualIds, Long individualSegmentId) {

		return _bqMembershipRepository.
			findIndividualIdByIndividualInAndIndividualSegmentIdAndStatus(
				individualIds, individualSegmentId, "ACTIVE");
	}

	public boolean isMember(Long individualId, Long individualSegmentId) {
		return _bqMembershipRepository.
			existsByIndividualIdAndIndividualSegmentIdAndStatus(
				individualId, individualSegmentId, "ACTIVE");
	}

	public List<BQMembership> searchBQMemberships(
		Long individualSegmentId, Long bqMembershipId, int size,
		String status) {

		return _bqMembershipRepository.searchBQMemberships(
			bqMembershipId, individualSegmentId, size, status);
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
	private BQMembershipRepository _bqMembershipRepository;

	@Autowired
	private IndividualDog _individualDog;

	@Autowired
	private MembershipChangeDog _membershipChangeDog;

	@Autowired
	private ObjectMapper _objectMapper;

	@Autowired
	private SegmentDog _segmentDog;

}