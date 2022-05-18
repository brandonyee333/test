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

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.entity.BQMembershipChange;
import com.liferay.osb.asah.common.entity.Channel;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.repository.BQMembershipChangeRepository;
import com.liferay.osb.asah.common.repository.ChannelRepository;
import com.liferay.osb.asah.common.repository.SegmentRepository;
import com.liferay.osb.asah.common.repository.helper.FilterHelper;
import com.liferay.osb.asah.common.util.SetUtil;
import com.liferay.osb.asah.test.util.configuration.JDBCTestConfiguration;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.lang.time.DateUtils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Marcellus Tavares
 */
@Import(JDBCTestConfiguration.class)
public class BQMembershipChangeRepositoryTest
	extends BaseRepositoryTestCase<BQMembershipChange, Long> {

	@BeforeEach
	public void setUp() {
		_newDayDate = DateUtil.newDayDate();

		_segments = Arrays.asList(_addSegment(), _addSegment(), _addSegment());
		_tomorrow = DateUtils.addDays(_newDayDate, 1);
		_yesterday = DateUtils.addDays(_newDayDate, -1);

		List<BQMembershipChange> bqMembershipsChanges = new LinkedList<>();

		for (Segment segment : _segments) {
			bqMembershipsChanges.add(
				_createBQMembershipChange(_yesterday, 1, segment));
			bqMembershipsChanges.add(
				_createBQMembershipChange(
					DateUtil.newEndOfDayDate(_newDayDate), 3, segment));
			bqMembershipsChanges.add(
				_createBQMembershipChange(_tomorrow, 5, segment));
		}

		setUpRepository(
			bqMembershipsChanges.toArray(new BQMembershipChange[0]));

		_bqMembershipChange = entityModels.get(1);
	}

	@Test
	public void testCountMembershipChanges() {
		Segment segment = _segments.get(0);

		Assertions.assertEquals(
			3,
			_bqMembershipChangeRepository.countBQMembershipChanges(
				FilterHelper.EMPTY, segment.getId()));
	}

	@Test
	public void testDeleteByIndividualSegmentIdIn() {
		List<Segment> segments = _segments.subList(0, 2);

		Stream<Segment> segmentsStream = segments.stream();

		List<Long> segmentIds = segmentsStream.map(
			Segment::getId
		).collect(
			Collectors.toList()
		);

		_bqMembershipChangeRepository.deleteByIndividualSegmentIdIn(segmentIds);

		List<BQMembershipChange> bqMembershipChanges = IterableUtils.toList(
			_bqMembershipChangeRepository.findAll());

		Stream<BQMembershipChange> bqMembershipChangesStream =
			bqMembershipChanges.stream();

		Assertions.assertFalse(
			bqMembershipChangesStream.anyMatch(
				membershipChange -> segmentIds.contains(
					membershipChange.getIndividualSegmentId())));
	}

	@Test
	public void testSearchLastByDateChangedPeriodAndIndividualSegmentId() {
		List<Long> individualSegmentIds = Collections.singletonList(
			_bqMembershipChange.getIndividualSegmentId());

		List<BQMembershipChange> bqMembershipChanges =
			_bqMembershipChangeRepository.
				searchLastByCreateDateAndIndividualSegmentId(
					DateUtil.newBeginningOfDayDate(
						DateUtils.addDays(_yesterday, -3)),
					individualSegmentIds,
					DateUtil.newEndOfDayDate(
						DateUtils.addDays(_yesterday, -2)));

		Assertions.assertTrue(bqMembershipChanges.isEmpty());

		bqMembershipChanges =
			_bqMembershipChangeRepository.
				searchLastByCreateDateAndIndividualSegmentId(
					DateUtil.newBeginningOfDayDate(_yesterday),
					individualSegmentIds,
					DateUtil.newEndOfDayDate(_newDayDate));

		Assertions.assertEquals(
			1, bqMembershipChanges.size(), bqMembershipChanges.toString());
		Assertions.assertEquals(
			_bqMembershipChange, bqMembershipChanges.get(0));

		bqMembershipChanges =
			_bqMembershipChangeRepository.
				searchLastByCreateDateAndIndividualSegmentId(
					null, individualSegmentIds,
					DateUtil.newEndOfDayDate(_newDayDate));

		Assertions.assertEquals(
			1, bqMembershipChanges.size(), bqMembershipChanges.toString());
		Assertions.assertEquals(
			_bqMembershipChange, bqMembershipChanges.get(0));

		bqMembershipChanges =
			_bqMembershipChangeRepository.
				searchLastByCreateDateAndIndividualSegmentId(
					DateUtil.newBeginningOfDayDate(_yesterday),
					individualSegmentIds, DateUtil.newEndOfDayDate(_tomorrow));

		Assertions.assertEquals(
			1, bqMembershipChanges.size(), bqMembershipChanges.toString());
		Assertions.assertEquals(
			entityModels.get(2), bqMembershipChanges.get(0));

		List<Segment> segments = _segments.subList(0, 2);

		Stream<Segment> stream1 = segments.stream();

		List<Long> segmentIds1 = stream1.map(
			Segment::getId
		).collect(
			Collectors.toList()
		);

		bqMembershipChanges =
			_bqMembershipChangeRepository.
				searchLastByCreateDateAndIndividualSegmentId(
					DateUtil.newBeginningOfDayDate(_yesterday), segmentIds1,
					DateUtil.newEndOfDayDate(_tomorrow));

		Assertions.assertEquals(
			2, bqMembershipChanges.size(), bqMembershipChanges.toString());

		Stream<BQMembershipChange> stream2 = bqMembershipChanges.stream();

		Assertions.assertEquals(
			segmentIds1,
			stream2.map(
				BQMembershipChange::getIndividualSegmentId
			).collect(
				Collectors.toList()
			));

		for (BQMembershipChange bqMembershipChange : bqMembershipChanges) {
			Assertions.assertEquals(
				5L, bqMembershipChange.getIndividualsCount());
		}
	}

	@Test
	public void testSearchMembershipChanges() {
		Segment segment = _segments.get(0);

		List<BQMembershipChange> bqMembershipChanges =
			_bqMembershipChangeRepository.searchBQMembershipChanges(
				FilterHelper.EMPTY, segment.getId(), PageRequest.of(0, 1));

		Assertions.assertEquals(
			1, bqMembershipChanges.size(), bqMembershipChanges.toString());
		Assertions.assertEquals(
			entityModels.get(0), bqMembershipChanges.get(0));

		bqMembershipChanges =
			_bqMembershipChangeRepository.searchBQMembershipChanges(
				FilterHelper.EMPTY, segment.getId(), PageRequest.of(0, 10));

		Stream<BQMembershipChange> stream = bqMembershipChanges.stream();

		Map<Long, List<BQMembershipChange>> bqMembershipChangesMap =
			stream.collect(
				Collectors.groupingBy(
					BQMembershipChange::getIndividualSegmentId));

		Assertions.assertEquals(
			1, bqMembershipChangesMap.size(),
			bqMembershipChangesMap.toString());
	}

	@Override
	protected PagingAndSortingRepository<BQMembershipChange, Long>
		getPagingAndSortingRepository() {

		return _bqMembershipChangeRepository;
	}

	private Segment _addSegment() {
		Segment segment = new Segment();

		Channel channel = _channelRepository.save(new Channel("Channel"));

		Long channelId = channel.getId();

		segment.setChannelId(channelId);

		segment.setCreateDate(_newDayDate);
		segment.setFilter(String.format("(channelId eq '%d')", channelId));
		segment.setName(String.format("Segment %d", channelId));
		segment.setReferencedAssetDataSourceIds(SetUtil.of(5L, 6L));
		segment.setReferencedAssetIds(SetUtil.of(3L, 4L));
		segment.setReferencedFieldMappingIds(SetUtil.of(7L, 8L));
		segment.setState("READY");
		segment.setStatus("STARTED");
		segment.setType(Segment.Type.DYNAMIC);

		return _segmentRepository.save(segment);
	}

	private BQMembershipChange _createBQMembershipChange(
		Date createDate, long knownIndividualsCount, Segment segment) {

		BQMembershipChange bqMembershipChange = new BQMembershipChange();

		bqMembershipChange.setCreateDate(createDate);
		bqMembershipChange.setIndividualsCount(knownIndividualsCount);
		bqMembershipChange.setIndividualSegmentId(segment.getId());
		bqMembershipChange.setKnownIndividualsCount(knownIndividualsCount);

		return bqMembershipChange;
	}

	private BQMembershipChange _bqMembershipChange;

	@Autowired
	private BQMembershipChangeRepository _bqMembershipChangeRepository;

	@Autowired
	private ChannelRepository _channelRepository;

	private Date _newDayDate;

	@Autowired
	private SegmentRepository _segmentRepository;

	private List<Segment> _segments;
	private Date _tomorrow;
	private Date _yesterday;

}