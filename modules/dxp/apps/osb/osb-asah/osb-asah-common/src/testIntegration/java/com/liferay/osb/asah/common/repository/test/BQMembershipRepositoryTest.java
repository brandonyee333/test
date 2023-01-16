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
import com.liferay.osb.asah.common.entity.BQMembershipChange;
import com.liferay.osb.asah.common.entity.Channel;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.repository.BQMembershipChangeRepository;
import com.liferay.osb.asah.common.repository.BQMembershipRepository;
import com.liferay.osb.asah.common.repository.ChannelRepository;
import com.liferay.osb.asah.common.repository.SegmentRepository;
import com.liferay.osb.asah.common.util.SetUtil;
import com.liferay.osb.asah.test.util.configuration.JDBCTestConfiguration;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
		bqMembership1.setIdentityId("12");
		bqMembership1.setModifiedDate(new Date());
		bqMembership1.setRemovedDate(new Date());

		Segment segment1 = _addSegment(34);

		bqMembership1.setSegmentId(segment1.getId());

		bqMembership1.setStatus("ACTIVE");

		_addBQMembershipChange(segment1.getId(), 37L);

		BQMembership bqMembership2 = new BQMembership();

		bqMembership2.setCreateDate(new Date());
		bqMembership2.setIdentityId("12");
		bqMembership2.setModifiedDate(new Date());
		bqMembership2.setRemovedDate(new Date());

		Segment segment2 = _addSegment(56);

		bqMembership2.setSegmentId(segment2.getId());

		bqMembership2.setStatus("INACTIVE");

		_addBQMembershipChange(segment2.getId(), 27L);

		BQMembership bqMembership3 = new BQMembership();

		bqMembership3.setCreateDate(new Date());
		bqMembership3.setIdentityId("78");
		bqMembership3.setModifiedDate(new Date());
		bqMembership3.setRemovedDate(new Date());
		bqMembership3.setSegmentId(segment1.getId());
		bqMembership3.setStatus("ACTIVE");

		setUpRepository(bqMembership1, bqMembership2, bqMembership3);
	}

	@Test
	public void testCountByIdentityIdInAndSegmentIdAndStatus() {
		Assertions.assertEquals(
			2,
			_bqMembershipRepository.countByIdentityIdInAndSegmentIdAndStatus(
				Arrays.asList("12", "78"), 34L, "ACTIVE"));
		Assertions.assertEquals(
			0,
			_bqMembershipRepository.countByIdentityIdInAndSegmentIdAndStatus(
				Arrays.asList("12", "78"), 34L, "INACTIVE"));
		Assertions.assertEquals(
			1,
			_bqMembershipRepository.countByIdentityIdInAndSegmentIdAndStatus(
				Arrays.asList("12", "78"), 56L, "INACTIVE"));
	}

	@Test
	public void testCountBySegmentIdAndStatus() {
		Assertions.assertEquals(
			2,
			_bqMembershipRepository.countBySegmentIdAndStatus(34L, "ACTIVE"));
		Assertions.assertEquals(
			0,
			_bqMembershipRepository.countBySegmentIdAndStatus(34L, "INACTIVE"));
		Assertions.assertEquals(
			1,
			_bqMembershipRepository.countBySegmentIdAndStatus(56L, "INACTIVE"));
	}

	@Test
	public void testDeleteBySegmentIdIn() {
		List<BQMembership> bqMemberships = IterableUtils.toList(
			_bqMembershipRepository.findAll());

		Stream<BQMembership> stream = bqMemberships.stream();

		Assertions.assertTrue(
			stream.anyMatch(bqMembership -> bqMembership.getSegmentId() == 34));

		_bqMembershipRepository.deleteBySegmentIdIn(Arrays.asList(34L));

		bqMemberships = IterableUtils.toList(_bqMembershipRepository.findAll());

		stream = bqMemberships.stream();

		Assertions.assertFalse(
			stream.anyMatch(bqMembership -> bqMembership.getSegmentId() == 34));
	}

	@Test
	public void testExistsByIdentityIdAndSegmentIdAndStatus() {
		Assertions.assertTrue(
			_bqMembershipRepository.existsByIdentityIdAndSegmentIdAndStatus(
				"12", 34L, "ACTIVE"));
	}

	@Test
	public void testFindByIdentityIdAndSegmentIdInAndStatus() {
		List<BQMembership> bqMemberships =
			_bqMembershipRepository.findByIdentityIdAndSegmentIdInAndStatus(
				"12", Arrays.asList(34L, 56L), "ACTIVE");

		Assertions.assertEquals(
			1, bqMemberships.size(), bqMemberships.toString());
		Assertions.assertEquals(entityModels.get(0), bqMemberships.get(0));
	}

	@Test
	public void testFindByIdentityIdAndStatus() {
		List<BQMembership> bqMemberships =
			_bqMembershipRepository.findByIdentityIdAndStatus("12", "ACTIVE");

		Assertions.assertEquals(
			1, bqMemberships.size(), bqMemberships.toString());
		Assertions.assertEquals(entityModels.get(0), bqMemberships.get(0));
	}

	@Test
	public void testFindByIdentityIdInAndSegmentIdAndStatus() {
		List<BQMembership> bqMemberships =
			_bqMembershipRepository.findByIdentityIdInAndSegmentIdAndStatus(
				Arrays.asList("12", "78"), 34L, "ACTIVE",
				PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assertions.assertEquals(
			2, bqMemberships.size(), bqMemberships.toString());
		Assertions.assertEquals(entityModels.get(0), bqMemberships.get(0));
		Assertions.assertEquals(entityModels.get(2), bqMemberships.get(1));

		bqMemberships =
			_bqMembershipRepository.findByIdentityIdInAndSegmentIdAndStatus(
				Arrays.asList("12", "78"), 34L, "INACTIVE",
				PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assertions.assertEquals(
			0, bqMemberships.size(), bqMemberships.toString());

		bqMemberships =
			_bqMembershipRepository.findByIdentityIdInAndSegmentIdAndStatus(
				Arrays.asList("12", "78"), 56L, "INACTIVE",
				PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assertions.assertEquals(
			1, bqMemberships.size(), bqMemberships.toString());
		Assertions.assertEquals(entityModels.get(1), bqMemberships.get(0));
	}

	@Test
	public void testFindBySegmentIdAndStatus() {
		List<BQMembership> bqMemberships =
			_bqMembershipRepository.findBySegmentIdAndStatus(
				34L, "ACTIVE",
				PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assertions.assertEquals(
			2, bqMemberships.size(), bqMemberships.toString());
		Assertions.assertEquals(entityModels.get(0), bqMemberships.get(0));
		Assertions.assertEquals(entityModels.get(2), bqMemberships.get(1));

		bqMemberships = _bqMembershipRepository.findBySegmentIdAndStatus(
			34L, "INACTIVE",
			PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assertions.assertEquals(
			0, bqMemberships.size(), bqMemberships.toString());

		bqMemberships = _bqMembershipRepository.findBySegmentIdAndStatus(
			56L, "INACTIVE",
			PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assertions.assertEquals(
			1, bqMemberships.size(), bqMemberships.toString());
		Assertions.assertEquals(entityModels.get(1), bqMemberships.get(0));
	}

	@Test
	public void testFindIdentityIdBySegmentIdAndStatus() {
		List<String> identityIds =
			_bqMembershipRepository.findIdentityIdBySegmentIdAndStatus(
				34L, "ACTIVE");

		Assertions.assertEquals(2, identityIds.size(), identityIds.toString());
		Assertions.assertEquals("12", identityIds.get(0));
		Assertions.assertEquals("78", identityIds.get(1));

		identityIds =
			_bqMembershipRepository.findIdentityIdBySegmentIdAndStatus(
				34L, "INACTIVE");

		Assertions.assertEquals(0, identityIds.size(), identityIds.toString());

		identityIds =
			_bqMembershipRepository.findIdentityIdBySegmentIdAndStatus(
				56L, "INACTIVE");

		Assertions.assertEquals(1, identityIds.size(), identityIds.toString());
		Assertions.assertEquals("12", identityIds.get(0));
	}

	@Test
	public void testFindIdentityIdBySegmentIdIn() {
		List<String> identityIds =
			_bqMembershipRepository.findIdentityIdBySegmentIdIn(
				Arrays.asList(34L, 56L), 10, 1, true);

		Assertions.assertEquals(Arrays.asList("78", "12"), identityIds);

		identityIds = _bqMembershipRepository.findIdentityIdBySegmentIdIn(
			Arrays.asList(34L, 56L), 10, 1, false);

		Assertions.assertEquals(Arrays.asList("12", "78"), identityIds);

		identityIds = _bqMembershipRepository.findIdentityIdBySegmentIdIn(
			Arrays.asList(34L, 56L), 10, 2, true);

		Assertions.assertEquals(Arrays.asList("12"), identityIds);

		identityIds = _bqMembershipRepository.findIdentityIdBySegmentIdIn(
			Arrays.asList(34L, 56L), 1, 1, true);

		Assertions.assertEquals(Arrays.asList("78"), identityIds);
	}

	@Test
	public void testFindSegmentIdByIdentityIdAndStatus() {
		List<Long> segmentIds =
			_bqMembershipRepository.findSegmentIdByIdentityIdAndStatus(
				"12", "ACTIVE");

		Assertions.assertEquals(1, segmentIds.size(), segmentIds.toString());
		Assertions.assertEquals(34, segmentIds.get(0));

		segmentIds = _bqMembershipRepository.findSegmentIdByIdentityIdAndStatus(
			"78", "INACTIVE");

		Assertions.assertEquals(0, segmentIds.size(), segmentIds.toString());

		segmentIds = _bqMembershipRepository.findSegmentIdByIdentityIdAndStatus(
			"12", "INACTIVE");

		Assertions.assertEquals(1, segmentIds.size(), segmentIds.toString());
		Assertions.assertEquals(56, segmentIds.get(0));
	}

	@Test
	public void testFindSegmentIdByIdentityIdInAndStatus() {
		List<Long> segmentIds =
			_bqMembershipRepository.findSegmentIdByIdentityIdInAndStatus(
				Arrays.asList("12", "78"), "ACTIVE");

		Assertions.assertEquals(1, segmentIds.size(), segmentIds.toString());
		Assertions.assertEquals(34, segmentIds.get(0));

		segmentIds =
			_bqMembershipRepository.findSegmentIdByIdentityIdInAndStatus(
				Arrays.asList("12", "78"), "INACTIVE");

		Assertions.assertEquals(1, segmentIds.size(), segmentIds.toString());
		Assertions.assertEquals(56, segmentIds.get(0));
	}

	@Test
	public void testFindSegmentIdIdentitiesCountByIdentityIdAndStatus() {
		List<Map<String, Long>> segmentIdIdentitiesCounts =
			_bqMembershipRepository.
				findSegmentIdIdentitiesCountByIdentityIdAndStatusAnd(
					"12", "ACTIVE");

		Assertions.assertEquals(
			1, segmentIdIdentitiesCounts.size(),
			segmentIdIdentitiesCounts.toString());

		Map<String, Long> segmentIdIdentitiesCount =
			segmentIdIdentitiesCounts.get(0);

		Assertions.assertEquals(34, segmentIdIdentitiesCount.get("segmentId"));
		Assertions.assertEquals(
			37, segmentIdIdentitiesCount.get("identitiesCount"));

		segmentIdIdentitiesCounts =
			_bqMembershipRepository.
				findSegmentIdIdentitiesCountByIdentityIdAndStatusAnd(
					"12", "INACTIVE");

		Assertions.assertEquals(
			1, segmentIdIdentitiesCounts.size(),
			segmentIdIdentitiesCounts.toString());

		segmentIdIdentitiesCount = segmentIdIdentitiesCounts.get(0);

		Assertions.assertEquals(56, segmentIdIdentitiesCount.get("segmentId"));
		Assertions.assertEquals(
			27, segmentIdIdentitiesCount.get("identitiesCount"));
	}

	@Test
	public void testFindTop20SegmentIdByIdentityId() {
		List<Long> segmentIds =
			_bqMembershipRepository.findTop20SegmentIdByIdentityId("12");

		Assertions.assertEquals(2, segmentIds.size(), segmentIds.toString());
		Assertions.assertEquals(34, segmentIds.get(0));
		Assertions.assertEquals(56, segmentIds.get(1));

		segmentIds = _bqMembershipRepository.findTop20SegmentIdByIdentityId(
			"78");

		Assertions.assertEquals(1, segmentIds.size(), segmentIds.toString());
		Assertions.assertEquals(34, segmentIds.get(0));
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

	private void _addBQMembershipChange(Long segmentId, long identitiesCount) {
		BQMembershipChange bqMembershipChange = new BQMembershipChange();

		bqMembershipChange.setCreateDate(new Date());
		bqMembershipChange.setIdentitiesCount(identitiesCount);
		bqMembershipChange.setKnownIdentitiesCount(19L);
		bqMembershipChange.setSegmentId(segmentId);

		_bqMembershipChangeRepository.save(bqMembershipChange);
	}

	private Segment _addSegment(long segmentId) {
		Segment segment = new Segment();

		segment.setId(segmentId);
		segment.setIsNew(Boolean.TRUE);

		Channel channel = _channelRepository.save(new Channel("Channel"));

		segment.setChannelId(channel.getId());

		segment.setCreateDate(new Date());
		segment.setFilter("(channelId eq '1')");
		segment.setName("Segment 1");
		segment.setReferencedAssetDataSourceIds(SetUtil.of(5L, 6L));
		segment.setReferencedFieldMappingIds(SetUtil.of("7", "8"));
		segment.setState("READY");
		segment.setStatus("STARTED");
		segment.setType(Segment.Type.DYNAMIC);

		return _segmentRepository.save(segment);
	}

	@Autowired
	private BQMembershipChangeRepository _bqMembershipChangeRepository;

	@Autowired
	private BQMembershipRepository _bqMembershipRepository;

	@Autowired
	private ChannelRepository _channelRepository;

	@Autowired
	private SegmentRepository _segmentRepository;

}