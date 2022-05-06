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

import com.liferay.osb.asah.common.entity.BQMembership;
import com.liferay.osb.asah.common.entity.Channel;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.repository.BQMembershipRepository;
import com.liferay.osb.asah.common.repository.ChannelRepository;
import com.liferay.osb.asah.common.repository.SegmentRepository;
import com.liferay.osb.asah.common.util.SetUtil;
import com.liferay.osb.asah.test.util.configuration.JDBCTestConfiguration;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.collections4.IterableUtils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Inácio Nery
 */
@Import(JDBCTestConfiguration.class)
public class BQMembershipRepositoryTest
	extends BaseRepositoryTestCase<BQMembership, Long> {

	@BeforeEach
	public void setUp() {
		BQMembership bqMembership1 = new BQMembership();

		bqMembership1.setCreateDate(new Date());
		bqMembership1.setIndividualId(12L);

		Segment segment1 = _addSegment(34);

		bqMembership1.setIndividualSegmentId(segment1.getId());

		bqMembership1.setModifiedDate(new Date());
		bqMembership1.setRemovedDate(new Date());
		bqMembership1.setStatus("ACTIVE");

		BQMembership bqMembership2 = new BQMembership();

		bqMembership2.setCreateDate(new Date());
		bqMembership2.setIndividualId(12L);

		Segment segment2 = _addSegment(56);

		bqMembership2.setIndividualSegmentId(segment2.getId());

		bqMembership2.setModifiedDate(new Date());
		bqMembership2.setRemovedDate(new Date());
		bqMembership2.setStatus("INACTIVE");

		BQMembership bqMembership3 = new BQMembership();

		bqMembership3.setCreateDate(new Date());
		bqMembership3.setIndividualId(78L);

		bqMembership3.setIndividualSegmentId(segment1.getId());

		bqMembership3.setModifiedDate(new Date());
		bqMembership3.setRemovedDate(new Date());
		bqMembership3.setStatus("ACTIVE");

		setUpRepository(bqMembership1, bqMembership2, bqMembership3);
	}

	@Test
	public void testCountByIndividualIdInAndIndividualSegmentIdAndStatus() {
		Assertions.assertEquals(
			2,
			_bqMembershipRepository.
				countByIndividualIdInAndIndividualSegmentIdAndStatus(
					Arrays.asList(12L, 78L), 34L, "ACTIVE"));
		Assertions.assertEquals(
			0,
			_bqMembershipRepository.
				countByIndividualIdInAndIndividualSegmentIdAndStatus(
					Arrays.asList(12L, 78L), 34L, "INACTIVE"));
		Assertions.assertEquals(
			1,
			_bqMembershipRepository.
				countByIndividualIdInAndIndividualSegmentIdAndStatus(
					Arrays.asList(12L, 78L), 56L, "INACTIVE"));
	}

	@Test
	public void testCountByIndividualSegmentIdAndStatus() {
		Assertions.assertEquals(
			2,
			_bqMembershipRepository.countByIndividualSegmentIdAndStatus(
				34L, "ACTIVE"));
		Assertions.assertEquals(
			0,
			_bqMembershipRepository.countByIndividualSegmentIdAndStatus(
				34L, "INACTIVE"));
		Assertions.assertEquals(
			1,
			_bqMembershipRepository.countByIndividualSegmentIdAndStatus(
				56L, "INACTIVE"));
	}

	@Test
	public void testDeleteByIndividualSegmentIdIn() {
		List<BQMembership> bqMemberships = IterableUtils.toList(
			_bqMembershipRepository.findAll());

		Stream<BQMembership> stream = bqMemberships.stream();

		Assertions.assertTrue(
			stream.anyMatch(
				bqMembership -> bqMembership.getIndividualSegmentId() == 34));

		_bqMembershipRepository.deleteByIndividualSegmentIdIn(
			Arrays.asList(34L));

		bqMemberships = IterableUtils.toList(_bqMembershipRepository.findAll());

		stream = bqMemberships.stream();

		Assertions.assertFalse(
			stream.anyMatch(
				bqMembership -> bqMembership.getIndividualSegmentId() == 34));
	}

	@Test
	public void testExistsByIndividualIdAndIndividualSegmentIdAndStatus() {
		Assertions.assertTrue(
			_bqMembershipRepository.
				existsByIndividualIdAndIndividualSegmentIdAndStatus(
					12L, 34L, "ACTIVE"));
	}

	@Test
	public void testFindByIndividualIdAndIndividualSegmentIdAndStatus() {
		BQMembership bqMembership =
			_bqMembershipRepository.
				findByIndividualIdAndIndividualSegmentIdAndStatus(
					12L, 34L, "ACTIVE");

		Assertions.assertEquals(entityModels.get(0), bqMembership);
	}

	@Test
	public void testFindByIndividualIdAndIndividualSegmentIdInAndStatus() {
		List<BQMembership> bqMemberships =
			_bqMembershipRepository.
				findByIndividualIdAndIndividualSegmentIdInAndStatus(
					12L, Arrays.asList(34L, 56L), "ACTIVE");

		Assertions.assertEquals(
			1, bqMemberships.size(), bqMemberships.toString());
		Assertions.assertEquals(entityModels.get(0), bqMemberships.get(0));
	}

	@Test
	public void testFindByIndividualIdAndStatus() {
		List<BQMembership> bqMemberships =
			_bqMembershipRepository.findByIndividualIdAndStatus(12L, "ACTIVE");

		Assertions.assertEquals(
			1, bqMemberships.size(), bqMemberships.toString());
		Assertions.assertEquals(entityModels.get(0), bqMemberships.get(0));
	}

	@Test
	public void testFindByIndividualIdInAndIndividualSegmentIdAndStatus() {
		List<BQMembership> bqMemberships =
			_bqMembershipRepository.
				findByIndividualIdInAndIndividualSegmentIdAndStatus(
					Arrays.asList(12L, 78L), 34L, "ACTIVE",
					PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assertions.assertEquals(
			2, bqMemberships.size(), bqMemberships.toString());
		Assertions.assertEquals(entityModels.get(0), bqMemberships.get(0));
		Assertions.assertEquals(entityModels.get(2), bqMemberships.get(1));

		bqMemberships =
			_bqMembershipRepository.
				findByIndividualIdInAndIndividualSegmentIdAndStatus(
					Arrays.asList(12L, 78L), 34L, "INACTIVE",
					PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assertions.assertEquals(
			0, bqMemberships.size(), bqMemberships.toString());

		bqMemberships =
			_bqMembershipRepository.
				findByIndividualIdInAndIndividualSegmentIdAndStatus(
					Arrays.asList(12L, 78L), 56L, "INACTIVE",
					PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assertions.assertEquals(
			1, bqMemberships.size(), bqMemberships.toString());
		Assertions.assertEquals(entityModels.get(1), bqMemberships.get(0));
	}

	@Test
	public void testFindByIndividualSegmentIdAndStatus() {
		List<BQMembership> bqMemberships =
			_bqMembershipRepository.findByIndividualSegmentIdAndStatus(
				34L, "ACTIVE",
				PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assertions.assertEquals(
			2, bqMemberships.size(), bqMemberships.toString());
		Assertions.assertEquals(entityModels.get(0), bqMemberships.get(0));
		Assertions.assertEquals(entityModels.get(2), bqMemberships.get(1));

		bqMemberships =
			_bqMembershipRepository.findByIndividualSegmentIdAndStatus(
				34L, "INACTIVE",
				PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assertions.assertEquals(
			0, bqMemberships.size(), bqMemberships.toString());

		bqMemberships =
			_bqMembershipRepository.findByIndividualSegmentIdAndStatus(
				56L, "INACTIVE",
				PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assertions.assertEquals(
			1, bqMemberships.size(), bqMemberships.toString());
		Assertions.assertEquals(entityModels.get(1), bqMemberships.get(0));
	}

	@Test
	public void testFindIndividualIdByIndividualSegmentIdAndStatus() {
		List<Long> individualIds =
			_bqMembershipRepository.
				findIndividualIdByIndividualSegmentIdAndStatus(34L, "ACTIVE");

		Assertions.assertEquals(
			2, individualIds.size(), individualIds.toString());
		Assertions.assertEquals(12, individualIds.get(0));
		Assertions.assertEquals(78, individualIds.get(1));

		individualIds =
			_bqMembershipRepository.
				findIndividualIdByIndividualSegmentIdAndStatus(34L, "INACTIVE");

		Assertions.assertEquals(
			0, individualIds.size(), individualIds.toString());

		individualIds =
			_bqMembershipRepository.
				findIndividualIdByIndividualSegmentIdAndStatus(56L, "INACTIVE");

		Assertions.assertEquals(
			1, individualIds.size(), individualIds.toString());
		Assertions.assertEquals(12, individualIds.get(0));
	}

	@Test
	public void testFindIndividualIdByIndividualSegmentIdIn() {
		List<Long> individualIds =
			_bqMembershipRepository.findIndividualIdByIndividualSegmentIdIn(
				Arrays.asList(34L, 56L), 10, 1, true);

		Assertions.assertEquals(Arrays.asList(78L, 12L), individualIds);

		individualIds =
			_bqMembershipRepository.findIndividualIdByIndividualSegmentIdIn(
				Arrays.asList(34L, 56L), 10, 1, false);

		Assertions.assertEquals(Arrays.asList(12L, 78L), individualIds);

		individualIds =
			_bqMembershipRepository.findIndividualIdByIndividualSegmentIdIn(
				Arrays.asList(34L, 56L), 10, 2, true);

		Assertions.assertEquals(Arrays.asList(12L), individualIds);

		individualIds =
			_bqMembershipRepository.findIndividualIdByIndividualSegmentIdIn(
				Arrays.asList(34L, 56L), 1, 1, true);

		Assertions.assertEquals(Arrays.asList(78L), individualIds);
	}

	@Test
	public void testFindIndividualSegmentIdByIndividualIdAndStatus() {
		List<Long> individualSegmenIds =
			_bqMembershipRepository.
				findIndividualSegmentIdByIndividualIdAndStatus(12L, "ACTIVE");

		Assertions.assertEquals(
			1, individualSegmenIds.size(), individualSegmenIds.toString());
		Assertions.assertEquals(34, individualSegmenIds.get(0));

		individualSegmenIds =
			_bqMembershipRepository.
				findIndividualSegmentIdByIndividualIdAndStatus(78L, "INACTIVE");

		Assertions.assertEquals(
			0, individualSegmenIds.size(), individualSegmenIds.toString());

		individualSegmenIds =
			_bqMembershipRepository.
				findIndividualSegmentIdByIndividualIdAndStatus(12L, "INACTIVE");

		Assertions.assertEquals(
			1, individualSegmenIds.size(), individualSegmenIds.toString());
		Assertions.assertEquals(56, individualSegmenIds.get(0));
	}

	@Test
	public void testFindIndividualSegmentIdByIndividualIdInAndStatus() {
		List<Long> individualSegmenIds =
			_bqMembershipRepository.
				findIndividualSegmentIdByIndividualIdInAndStatus(
					Arrays.asList(12L, 78L), "ACTIVE");

		Assertions.assertEquals(
			1, individualSegmenIds.size(), individualSegmenIds.toString());
		Assertions.assertEquals(34, individualSegmenIds.get(0));

		individualSegmenIds =
			_bqMembershipRepository.
				findIndividualSegmentIdByIndividualIdInAndStatus(
					Arrays.asList(12L, 78L), "INACTIVE");

		Assertions.assertEquals(
			1, individualSegmenIds.size(), individualSegmenIds.toString());
		Assertions.assertEquals(56, individualSegmenIds.get(0));
	}

	@Test
	public void testFindTop20IndividualSegmentIdByIndividualId() {
		List<Long> individualSegmenIds =
			_bqMembershipRepository.findTop20IndividualSegmentIdByIndividualId(
				12L);

		Assertions.assertEquals(
			2, individualSegmenIds.size(), individualSegmenIds.toString());
		Assertions.assertEquals(34, individualSegmenIds.get(0));
		Assertions.assertEquals(56, individualSegmenIds.get(1));

		individualSegmenIds =
			_bqMembershipRepository.findTop20IndividualSegmentIdByIndividualId(
				78L);

		Assertions.assertEquals(
			1, individualSegmenIds.size(), individualSegmenIds.toString());
		Assertions.assertEquals(34, individualSegmenIds.get(0));
	}

	@Test
	public void testSearchMemberships() {
		List<BQMembership> bqMemberships =
			_bqMembershipRepository.searchBQMemberships(
				null, 34L, 10, "ACTIVE");

		Assertions.assertEquals(
			2, bqMemberships.size(), bqMemberships.toString());
		Assertions.assertEquals(entityModels.get(0), bqMemberships.get(0));
		Assertions.assertEquals(entityModels.get(2), bqMemberships.get(1));

		bqMemberships = _bqMembershipRepository.searchBQMemberships(
			null, 34L, 10, "INACTIVE");

		Assertions.assertEquals(
			0, bqMemberships.size(), bqMemberships.toString());

		bqMemberships = _bqMembershipRepository.searchBQMemberships(
			null, 56L, 10, "INACTIVE");

		Assertions.assertEquals(
			1, bqMemberships.size(), bqMemberships.toString());
		Assertions.assertEquals(entityModels.get(1), bqMemberships.get(0));
	}

	@Override
	protected PagingAndSortingRepository<BQMembership, Long>
		getPagingAndSortingRepository() {

		return _bqMembershipRepository;
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
	private BQMembershipRepository _bqMembershipRepository;

	@Autowired
	private ChannelRepository _channelRepository;

	@Autowired
	private SegmentRepository _segmentRepository;

}