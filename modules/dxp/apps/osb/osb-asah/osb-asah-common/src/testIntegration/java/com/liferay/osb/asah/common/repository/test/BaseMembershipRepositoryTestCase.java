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

package com.liferay.osb.asah.common.repository.test;

import com.liferay.osb.asah.common.entity.Channel;
import com.liferay.osb.asah.common.entity.Membership;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.repository.ChannelRepository;
import com.liferay.osb.asah.common.repository.MembershipRepository;
import com.liferay.osb.asah.common.repository.SegmentRepository;
import com.liferay.osb.asah.common.util.SetUtil;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.collections4.IterableUtils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Inácio Nery
 */
public abstract class BaseMembershipRepositoryTestCase
	extends BaseRepositoryTestCase<Membership, Long> {

	@BeforeEach
	public void setUp() {
		Membership membership1 = new Membership();

		membership1.setCreateDate(new Date());
		membership1.setIndividualId(12L);

		Segment segment1 = _addSegment(34);

		membership1.setIndividualSegmentId(segment1.getId());

		membership1.setModifiedDate(new Date());
		membership1.setRemovedDate(new Date());
		membership1.setStatus("ACTIVE");

		Membership membership2 = new Membership();

		membership2.setCreateDate(new Date());
		membership2.setIndividualId(12L);

		Segment segment2 = _addSegment(56);

		membership2.setIndividualSegmentId(segment2.getId());

		membership2.setModifiedDate(new Date());
		membership2.setRemovedDate(new Date());
		membership2.setStatus("INACTIVE");

		Membership membership3 = new Membership();

		membership3.setCreateDate(new Date());
		membership3.setIndividualId(78L);

		membership3.setIndividualSegmentId(segment1.getId());

		membership3.setModifiedDate(new Date());
		membership3.setRemovedDate(new Date());
		membership3.setStatus("ACTIVE");

		setUpRepository(membership1, membership2, membership3);
	}

	@Test
	public void testCountByIndividualIdInAndIndividualSegmentIdAndStatus() {
		Assertions.assertEquals(
			2,
			_membershipRepository.
				countByIndividualIdInAndIndividualSegmentIdAndStatus(
					Arrays.asList(12L, 78L), 34L, "ACTIVE"));
		Assertions.assertEquals(
			0,
			_membershipRepository.
				countByIndividualIdInAndIndividualSegmentIdAndStatus(
					Arrays.asList(12L, 78L), 34L, "INACTIVE"));
		Assertions.assertEquals(
			1,
			_membershipRepository.
				countByIndividualIdInAndIndividualSegmentIdAndStatus(
					Arrays.asList(12L, 78L), 56L, "INACTIVE"));
	}

	@Test
	public void testCountByIndividualSegmentIdAndStatus() {
		Assertions.assertEquals(
			2,
			_membershipRepository.countByIndividualSegmentIdAndStatus(
				34L, "ACTIVE"));
		Assertions.assertEquals(
			0,
			_membershipRepository.countByIndividualSegmentIdAndStatus(
				34L, "INACTIVE"));
		Assertions.assertEquals(
			1,
			_membershipRepository.countByIndividualSegmentIdAndStatus(
				56L, "INACTIVE"));
	}

	@Test
	public void testDeleteByIndividualSegmentIdIn() {
		List<Membership> memberships = IterableUtils.toList(
			_membershipRepository.findAll());

		Stream<Membership> stream = memberships.stream();

		Assertions.assertTrue(
			stream.anyMatch(
				membership -> membership.getIndividualSegmentId() == 34));

		_membershipRepository.deleteByIndividualSegmentIdIn(Arrays.asList(34L));

		memberships = IterableUtils.toList(_membershipRepository.findAll());

		stream = memberships.stream();

		Assertions.assertFalse(
			stream.anyMatch(
				membership -> membership.getIndividualSegmentId() == 34));
	}

	@Test
	public void testExistsByIndividualIdAndIndividualSegmentIdAndStatus() {
		Assertions.assertTrue(
			_membershipRepository.
				existsByIndividualIdAndIndividualSegmentIdAndStatus(
					12L, 34L, "ACTIVE"));
	}

	@Test
	public void testFindByIndividualIdAndIndividualSegmentIdAndStatus() {
		Membership membership =
			_membershipRepository.
				findByIndividualIdAndIndividualSegmentIdAndStatus(
					12L, 34L, "ACTIVE");

		Assertions.assertEquals(entityModels.get(0), membership);
	}

	@Test
	public void testFindByIndividualIdAndIndividualSegmentIdInAndStatus() {
		List<Membership> memberships =
			_membershipRepository.
				findByIndividualIdAndIndividualSegmentIdInAndStatus(
					12L, Arrays.asList(34L, 56L), "ACTIVE");

		Assertions.assertEquals(1, memberships.size(), memberships.toString());
		Assertions.assertEquals(entityModels.get(0), memberships.get(0));
	}

	@Test
	public void testFindByIndividualIdAndStatus() {
		List<Membership> memberships =
			_membershipRepository.findByIndividualIdAndStatus(12L, "ACTIVE");

		Assertions.assertEquals(1, memberships.size(), memberships.toString());
		Assertions.assertEquals(entityModels.get(0), memberships.get(0));
	}

	@Test
	public void testFindByIndividualIdInAndIndividualSegmentIdAndStatus() {
		List<Membership> memberships =
			_membershipRepository.
				findByIndividualIdInAndIndividualSegmentIdAndStatus(
					Arrays.asList(12L, 78L), 34L, "ACTIVE",
					PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assertions.assertEquals(2, memberships.size(), memberships.toString());
		Assertions.assertEquals(entityModels.get(0), memberships.get(0));
		Assertions.assertEquals(entityModels.get(2), memberships.get(1));

		memberships =
			_membershipRepository.
				findByIndividualIdInAndIndividualSegmentIdAndStatus(
					Arrays.asList(12L, 78L), 34L, "INACTIVE",
					PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assertions.assertEquals(0, memberships.size(), memberships.toString());

		memberships =
			_membershipRepository.
				findByIndividualIdInAndIndividualSegmentIdAndStatus(
					Arrays.asList(12L, 78L), 56L, "INACTIVE",
					PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assertions.assertEquals(1, memberships.size(), memberships.toString());
		Assertions.assertEquals(entityModels.get(1), memberships.get(0));
	}

	@Test
	public void testFindByIndividualSegmentIdAndStatus() {
		List<Membership> memberships =
			_membershipRepository.findByIndividualSegmentIdAndStatus(
				34L, "ACTIVE",
				PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assertions.assertEquals(2, memberships.size(), memberships.toString());
		Assertions.assertEquals(entityModels.get(0), memberships.get(0));
		Assertions.assertEquals(entityModels.get(2), memberships.get(1));

		memberships = _membershipRepository.findByIndividualSegmentIdAndStatus(
			34L, "INACTIVE",
			PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assertions.assertEquals(0, memberships.size(), memberships.toString());

		memberships = _membershipRepository.findByIndividualSegmentIdAndStatus(
			56L, "INACTIVE",
			PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assertions.assertEquals(1, memberships.size(), memberships.toString());
		Assertions.assertEquals(entityModels.get(1), memberships.get(0));
	}

	@Test
	public void testFindIndividualIdByIndividualSegmentIdAndStatus() {
		List<Long> individualIds =
			_membershipRepository.
				findIndividualIdByIndividualSegmentIdAndStatus(34L, "ACTIVE");

		Assertions.assertEquals(
			2, individualIds.size(), individualIds.toString());
		Assertions.assertEquals(12, individualIds.get(0));
		Assertions.assertEquals(78, individualIds.get(1));

		individualIds =
			_membershipRepository.
				findIndividualIdByIndividualSegmentIdAndStatus(34L, "INACTIVE");

		Assertions.assertEquals(
			0, individualIds.size(), individualIds.toString());

		individualIds =
			_membershipRepository.
				findIndividualIdByIndividualSegmentIdAndStatus(56L, "INACTIVE");

		Assertions.assertEquals(
			1, individualIds.size(), individualIds.toString());
		Assertions.assertEquals(12, individualIds.get(0));
	}

	@Test
	public void testFindIndividualIdByIndividualSegmentIdIn() {
		List<Long> individualIds =
			_membershipRepository.findIndividualIdByIndividualSegmentIdIn(
				Arrays.asList(34L, 56L), 10, 1, true);

		Assertions.assertEquals(Arrays.asList(78L, 12L), individualIds);

		individualIds =
			_membershipRepository.findIndividualIdByIndividualSegmentIdIn(
				Arrays.asList(34L, 56L), 10, 1, false);

		Assertions.assertEquals(Arrays.asList(12L, 78L), individualIds);

		individualIds =
			_membershipRepository.findIndividualIdByIndividualSegmentIdIn(
				Arrays.asList(34L, 56L), 10, 2, true);

		Assertions.assertEquals(Arrays.asList(12L), individualIds);

		individualIds =
			_membershipRepository.findIndividualIdByIndividualSegmentIdIn(
				Arrays.asList(34L, 56L), 1, 1, true);

		Assertions.assertEquals(Arrays.asList(78L), individualIds);
	}

	@Test
	public void testFindIndividualSegmentIdByIndividualIdAndStatus() {
		List<Long> individualSegmenIds =
			_membershipRepository.
				findIndividualSegmentIdByIndividualIdAndStatus(12L, "ACTIVE");

		Assertions.assertEquals(
			1, individualSegmenIds.size(), individualSegmenIds.toString());
		Assertions.assertEquals(34, individualSegmenIds.get(0));

		individualSegmenIds =
			_membershipRepository.
				findIndividualSegmentIdByIndividualIdAndStatus(78L, "INACTIVE");

		Assertions.assertEquals(
			0, individualSegmenIds.size(), individualSegmenIds.toString());

		individualSegmenIds =
			_membershipRepository.
				findIndividualSegmentIdByIndividualIdAndStatus(12L, "INACTIVE");

		Assertions.assertEquals(
			1, individualSegmenIds.size(), individualSegmenIds.toString());
		Assertions.assertEquals(56, individualSegmenIds.get(0));
	}

	@Test
	public void testFindIndividualSegmentIdByIndividualIdInAndStatus() {
		List<Long> individualSegmenIds =
			_membershipRepository.
				findIndividualSegmentIdByIndividualIdInAndStatus(
					Arrays.asList(12L, 78L), "ACTIVE");

		Assertions.assertEquals(
			1, individualSegmenIds.size(), individualSegmenIds.toString());
		Assertions.assertEquals(34, individualSegmenIds.get(0));

		individualSegmenIds =
			_membershipRepository.
				findIndividualSegmentIdByIndividualIdInAndStatus(
					Arrays.asList(12L, 78L), "INACTIVE");

		Assertions.assertEquals(
			1, individualSegmenIds.size(), individualSegmenIds.toString());
		Assertions.assertEquals(56, individualSegmenIds.get(0));
	}

	@Test
	public void testFindTop20IndividualSegmentIdByIndividualId() {
		List<Long> individualSegmenIds =
			_membershipRepository.findTop20IndividualSegmentIdByIndividualId(
				12L);

		Assertions.assertEquals(
			2, individualSegmenIds.size(), individualSegmenIds.toString());
		Assertions.assertEquals(34, individualSegmenIds.get(0));
		Assertions.assertEquals(56, individualSegmenIds.get(1));

		individualSegmenIds =
			_membershipRepository.findTop20IndividualSegmentIdByIndividualId(
				78L);

		Assertions.assertEquals(
			1, individualSegmenIds.size(), individualSegmenIds.toString());
		Assertions.assertEquals(34, individualSegmenIds.get(0));
	}

	@Test
	public void testSearchMemberships() {
		List<Membership> memberships = _membershipRepository.searchMemberships(
			null, 34L, 10, "ACTIVE");

		Assertions.assertEquals(2, memberships.size(), memberships.toString());
		Assertions.assertEquals(entityModels.get(0), memberships.get(0));
		Assertions.assertEquals(entityModels.get(2), memberships.get(1));

		memberships = _membershipRepository.searchMemberships(
			null, 34L, 10, "INACTIVE");

		Assertions.assertEquals(0, memberships.size(), memberships.toString());

		memberships = _membershipRepository.searchMemberships(
			null, 56L, 10, "INACTIVE");

		Assertions.assertEquals(1, memberships.size(), memberships.toString());
		Assertions.assertEquals(entityModels.get(1), memberships.get(0));
	}

	@Override
	protected PagingAndSortingRepository<Membership, Long>
		getPagingAndSortingRepository() {

		return _membershipRepository;
	}

	private Segment _addSegment(long segmentId) {
		Segment segment = new Segment();

		segment.setId(segmentId);
		segment.setIsNew(true);

		Channel channel = _channelRepository.save(new Channel("Channel"));

		segment.setChannelId(channel.getId());

		segment.setCreateDate(new Date());
		segment.setFilter("(channelId eq '1')");
		segment.setName("Segment 1");
		segment.setReferencedAssetDataSourceIds(SetUtil.of(5L, 6L));
		segment.setReferencedAssetIds(SetUtil.of(3L, 4L));
		segment.setReferencedFieldMappingIds(SetUtil.of(7L, 8L));
		segment.setState("READY");
		segment.setStatus("STARTED");
		segment.setType(Segment.Type.DYNAMIC);

		return _segmentRepository.save(segment);
	}

	@Autowired
	private ChannelRepository _channelRepository;

	@Autowired
	private MembershipRepository _membershipRepository;

	@Autowired
	private SegmentRepository _segmentRepository;

}