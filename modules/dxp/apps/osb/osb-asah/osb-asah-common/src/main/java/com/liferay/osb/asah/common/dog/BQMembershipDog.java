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
import com.liferay.osb.asah.common.faro.info.dog.BaseFaroInfoDog;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.Individual;
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
public class BQMembershipDog extends BaseFaroInfoDog {

	public BQMembership addBQMembership(BQMembership bqMembership) {
		bqMembership = _bqMembershipRepository.save(bqMembership);

		String status = bqMembership.getStatus();

		if (!status.equals("ACTIVE")) {
			return bqMembership;
		}

		Individual individual = _individualDog.addSegmentId(
			Long.parseLong(bqMembership.getIdentityId()),
			bqMembership.getSegmentId());

		if (individual == null) {
			return null;
		}

		return bqMembership;
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

		_individualDog.addSegmentId(individuals, segmentId);
	}

	public List<BQMembership> addBQMemberships(
		List<BQMembership> bqMemberships) {

		if (bqMemberships.isEmpty()) {
			return bqMemberships;
		}

		_bqMembershipRepository.saveAll(bqMemberships);

		BQMembership bqMembership = bqMemberships.get(0);

		_individualDog.addIndividualSegmentIds(
			ListUtil.map(
				ListUtil.map(bqMemberships, BQMembership::getIdentityId),
				Long::parseLong),
			bqMembership.getSegmentId());

		Segment segment = _segmentDog.getSegment(bqMembership.getSegmentId());

		long knownIndividualsCount = _individualDog.getKnownIndividualsCount(
			ListUtil.map(
				ListUtil.map(bqMemberships, BQMembership::getIdentityId),
				Long::parseLong));

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
			Long.parseLong(bqMembership.getIdentityId()),
			bqMembership.getSegmentId());
	}

	public void deactivateBQMembership(
		Date deletionDate, String identityId, Long segmentId) {

		deactivateBQMembership(
			deletionDate,
			_bqMembershipRepository.findByIdentityIdAndSegmentIdAndStatus(
				identityId, segmentId, "ACTIVE"));
	}

	public void deactivateBQMembershipByIndividuals(
		Date deletionDate, List<Individual> individuals) {

		List<BQMembership> bqMemberships =
			_bqMembershipRepository.findByIdentityIdInAndStatus(
				ListUtil.map(
					ListUtil.map(individuals, Individual::getId),
					String::valueOf),
				"ACTIVE");

		for (BQMembership bqMembership : bqMemberships) {
			bqMembership.setModifiedDate(deletionDate);
			bqMembership.setRemovedDate(deletionDate);
			bqMembership.setStatus("INACTIVE");
		}

		_bqMembershipRepository.saveAll(bqMemberships);

		Stream<BQMembership> bqMembershipsStream = bqMemberships.stream();

		Map<Long, List<BQMembership>> bqMembershipsMap =
			bqMembershipsStream.collect(
				Collectors.groupingBy(BQMembership::getSegmentId));

		for (Map.Entry<Long, List<BQMembership>> entry :
				bqMembershipsMap.entrySet()) {

			List<String> identityIdsByMembership = ListUtil.map(
				entry.getValue(), BQMembership::getIdentityId);

			Stream<Individual> individualsStream = individuals.stream();

			List<Individual> individualsByMembership = individualsStream.filter(
				individual -> identityIdsByMembership.contains(
					String.valueOf(individual.getId()))
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
			Collectors.groupingBy(BQMembership::getSegmentId));

		for (Map.Entry<Long, List<BQMembership>> entry :
				bqMembershipsMap.entrySet()) {

			_individualDog.removeSegmentId(
				SetUtil.map(
					SetUtil.map(entry.getValue(), BQMembership::getIdentityId),
					Long::parseLong),
				entry.getKey());
		}
	}

	public void deactivateBQMemberships(Date deletionDate, String identityId) {
		for (BQMembership bqMembership :
				_bqMembershipRepository.findByIdentityIdAndStatus(
					identityId, "ACTIVE")) {

			deactivateBQMembership(deletionDate, bqMembership);
		}
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

	public List<Long> getActiveSegmentIds(List<String> identityIds) {
		return _bqMembershipRepository.findSegmentIdByIdentityIdInAndStatus(
			identityIds, "ACTIVE");
	}

	public List<Long> getActiveSegmentIds(String identityId) {
		return _bqMembershipRepository.findSegmentIdByIdentityIdAndStatus(
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

	public long getIdentitiesCount(Long segmentId) {
		return _bqMembershipRepository.countBySegmentIdAndStatus(
			segmentId, "ACTIVE");
	}

	public List<String> getIdentityIds(
		List<Long> segmentIds, int max, int min, boolean ascending) {

		return _bqMembershipRepository.findIdentityIdBySegmentIdIn(
			segmentIds, max, min, ascending);
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
						"Unable to get active membership for individual " +
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

	public boolean isIdentityInSegments(
		String identityId, List<Long> segmentIds, int max, int min,
		boolean ascending) {

		List<String> identityIds =
			_bqMembershipRepository.findIdentityIdBySegmentIdIn(
				identityId, segmentIds, max, min, ascending);

		return !identityIds.isEmpty();
	}

	public List<String> isMember(List<String> identityIds, Long segmentId) {
		return _bqMembershipRepository.
			findIdentityIdByIdentityIdInAndSegmentIdAndStatus(
				identityIds, segmentId, "ACTIVE");
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
	private IndividualDog _individualDog;

	@Autowired
	private BQMembershipChangeDog _membershipChangeDog;

	@Autowired
	private ObjectMapper _objectMapper;

	@Autowired
	private SegmentDog _segmentDog;

}