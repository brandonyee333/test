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
import com.liferay.osb.asah.common.entity.Channel;
import com.liferay.osb.asah.common.entity.MembershipChange;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.repository.ChannelRepository;
import com.liferay.osb.asah.common.repository.MembershipChangeRepository;
import com.liferay.osb.asah.common.repository.SegmentRepository;
import com.liferay.osb.asah.common.repository.helper.FilterHelper;
import com.liferay.osb.asah.common.util.SetUtil;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.lang.time.DateUtils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Marcellus Tavares
 */
public abstract class BaseMembershipChangeRepositoryTestCase
	extends BaseRepositoryTestCase<MembershipChange, Long> {

	@BeforeEach
	public void setUp() {
		_newDayDate = DateUtil.newDayDate();

		_segments = Arrays.asList(_addSegment(), _addSegment(), _addSegment());
		_tomorrow = DateUtils.addDays(_newDayDate, 1);
		_yesterday = DateUtils.addDays(_newDayDate, -1);

		List<MembershipChange> membershipsChanges = new LinkedList<>();

		for (Segment segment : _segments) {
			membershipsChanges.add(
				_createMembershipChange(
					RandomTestUtil.randomNumber(), 1, _yesterday, segment));
			membershipsChanges.add(
				_createMembershipChange(
					RandomTestUtil.randomNumber(), 3,
					DateUtil.newEndOfDayDate(_newDayDate), segment));
			membershipsChanges.add(
				_createMembershipChange(
					RandomTestUtil.randomNumber(), 4,
					DateUtil.newEndOfDayDate(_newDayDate), segment));
			membershipsChanges.add(
				_createMembershipChange(
					RandomTestUtil.randomNumber(), 2, _newDayDate, segment));
			membershipsChanges.add(
				_createMembershipChange(
					RandomTestUtil.randomNumber(), 5, _tomorrow, segment));
		}

		setUpRepository(membershipsChanges.toArray(new MembershipChange[0]));

		_membershipChange = entityModels.get(2);
	}

	@Test
	public void testCountMembershipChanges() {
		Segment segment = _segments.get(0);

		Assertions.assertEquals(
			5,
			_membershipChangeRepository.countMembershipChanges(
				FilterHelper.EMPTY, false, segment.getId()));
	}

	@Test
	public void testDeleteByIndividualSegmentIdIn() {
		List<Segment> subList = _segments.subList(0, 2);

		Stream<Segment> stream = subList.stream();

		List<Long> segmentIds = stream.map(
			Segment::getId
		).collect(
			Collectors.toList()
		);

		_membershipChangeRepository.deleteByIndividualSegmentIdIn(segmentIds);

		List<MembershipChange> membershipChanges = IterableUtils.toList(
			_membershipChangeRepository.findAll());

		Stream<MembershipChange> membershipChangesStream =
			membershipChanges.stream();

		Assertions.assertFalse(
			membershipChangesStream.anyMatch(
				membershipChange -> segmentIds.contains(
					membershipChange.getIndividualSegmentId())));
	}

	@Test
	public void testFindByIndividualId() {
		Assertions.assertEquals(
			Optional.of(_membershipChange),
			_membershipChangeRepository.findByIndividualId(
				_membershipChange.getIndividualId()));
	}

	@Test
	public void testSearchLastByDateChangedPeriodAndIndividualSegmentId() {
		List<Long> individualSegmentIds = Collections.singletonList(
			_membershipChange.getIndividualSegmentId());

		List<MembershipChange> membershipChanges =
			_membershipChangeRepository.
				searchLastByModifiedDateAndIndividualSegmentId(
					DateUtil.newBeginningOfDayDate(
						DateUtils.addDays(_yesterday, -3)),
					false, individualSegmentIds,
					DateUtil.newEndOfDayDate(
						DateUtils.addDays(_yesterday, -2)));

		Assertions.assertTrue(membershipChanges.isEmpty());

		membershipChanges =
			_membershipChangeRepository.
				searchLastByModifiedDateAndIndividualSegmentId(
					DateUtil.newBeginningOfDayDate(_yesterday), false,
					individualSegmentIds,
					DateUtil.newEndOfDayDate(_newDayDate));

		Assertions.assertEquals(
			1, membershipChanges.size(), membershipChanges.toString());
		Assertions.assertEquals(_membershipChange, membershipChanges.get(0));

		membershipChanges =
			_membershipChangeRepository.
				searchLastByModifiedDateAndIndividualSegmentId(
					null, false, individualSegmentIds,
					DateUtil.newEndOfDayDate(_newDayDate));

		Assertions.assertEquals(
			1, membershipChanges.size(), membershipChanges.toString());
		Assertions.assertEquals(_membershipChange, membershipChanges.get(0));

		membershipChanges =
			_membershipChangeRepository.
				searchLastByModifiedDateAndIndividualSegmentId(
					DateUtil.newBeginningOfDayDate(_yesterday), false,
					individualSegmentIds, DateUtil.newEndOfDayDate(_tomorrow));

		Assertions.assertEquals(
			1, membershipChanges.size(), membershipChanges.toString());
		Assertions.assertEquals(entityModels.get(4), membershipChanges.get(0));

		List<Segment> segments = _segments.subList(0, 2);

		Stream<Segment> stream1 = segments.stream();

		List<Long> segmentIds1 = stream1.map(
			Segment::getId
		).collect(
			Collectors.toList()
		);

		membershipChanges =
			_membershipChangeRepository.
				searchLastByModifiedDateAndIndividualSegmentId(
					DateUtil.newBeginningOfDayDate(_yesterday), false,
					segmentIds1, DateUtil.newEndOfDayDate(_tomorrow));

		Assertions.assertEquals(
			2, membershipChanges.size(), membershipChanges.toString());

		Stream<MembershipChange> stream2 = membershipChanges.stream();

		Assertions.assertEquals(
			segmentIds1,
			stream2.map(
				MembershipChange::getIndividualSegmentId
			).collect(
				Collectors.toList()
			));

		for (MembershipChange membershipChange : membershipChanges) {
			Assertions.assertEquals(5L, membershipChange.getIndividualsCount());
		}
	}

	@Test
	public void testSearchMembershipChanges() {
		Segment segment = _segments.get(0);

		List<MembershipChange> membershipChanges =
			_membershipChangeRepository.searchMembershipChanges(
				FilterHelper.EMPTY, false, segment.getId(),
				PageRequest.of(0, 1));

		Assertions.assertEquals(
			1, membershipChanges.size(), membershipChanges.toString());
		Assertions.assertEquals(entityModels.get(0), membershipChanges.get(0));
	}

	@Test
	public void testUpdateIndividualDeletedByIndividualId() {
		_membershipChangeRepository.updateIndividualDeletedByIndividualId(
			Boolean.TRUE, _membershipChange.getIndividualId());

		Long membershipChangeId = _membershipChange.getId();

		Assertions.assertNotNull(membershipChangeId);

		Optional<MembershipChange> membershipChangeOptional =
			_membershipChangeRepository.findById(membershipChangeId);

		Assertions.assertTrue(membershipChangeOptional.isPresent());

		MembershipChange actualMembershipChange =
			membershipChangeOptional.get();

		Assertions.assertTrue(actualMembershipChange.getIndividualDeleted());
	}

	@Test
	public void testUpdateIndividualDeletedByIndividualIdIn() {
		_membershipChangeRepository.updateIndividualDeletedByIndividualIdIn(
			Boolean.TRUE, Arrays.asList(_membershipChange.getIndividualId()));

		Long membershipChangeId = _membershipChange.getId();

		Assertions.assertNotNull(membershipChangeId);

		Optional<MembershipChange> membershipChangeOptional =
			_membershipChangeRepository.findById(membershipChangeId);

		Assertions.assertTrue(membershipChangeOptional.isPresent());

		MembershipChange actualMembershipChange =
			membershipChangeOptional.get();

		Assertions.assertTrue(actualMembershipChange.getIndividualDeleted());
	}

	@Test
	public void testUpdateIndividualNameByIndividualId() {
		_membershipChangeRepository.updateIndividualNameByIndividualId(
			_membershipChange.getIndividualId(), "Frank Sinatra");

		Long membershipChangeId = _membershipChange.getId();

		Assertions.assertNotNull(membershipChangeId);

		Optional<MembershipChange> membershipChangeOptional =
			_membershipChangeRepository.findById(membershipChangeId);

		Assertions.assertTrue(membershipChangeOptional.isPresent());

		MembershipChange actualMembershipChange =
			membershipChangeOptional.get();

		Assertions.assertEquals(
			"Frank Sinatra", actualMembershipChange.getIndividualName());
	}

	@Override
	protected PagingAndSortingRepository<MembershipChange, Long>
		getPagingAndSortingRepository() {

		return _membershipChangeRepository;
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

	private MembershipChange _createMembershipChange(
		long individualId, long knownIndividualsCount, Date modifiedDate,
		Segment segment) {

		MembershipChange membershipChange = new MembershipChange();

		membershipChange.setIndividualDeleted(Boolean.FALSE);
		membershipChange.setIndividualEmail(
			String.format("individual.%d@liferay.com", individualId));
		membershipChange.setIndividualId(individualId);
		membershipChange.setIndividualName(
			String.format("Individual Name %d", individualId));
		membershipChange.setIndividualsCount(knownIndividualsCount);
		membershipChange.setIndividualSegmentId(segment.getId());
		membershipChange.setJoinedDate(modifiedDate);
		membershipChange.setKnownIndividualsCount(knownIndividualsCount);
		membershipChange.setModifiedDate(modifiedDate);
		membershipChange.setOperation("ADDED");

		return membershipChange;
	}

	@Autowired
	private ChannelRepository _channelRepository;

	private MembershipChange _membershipChange;

	@Autowired
	private MembershipChangeRepository _membershipChangeRepository;

	private Date _newDayDate;

	@Autowired
	private SegmentRepository _segmentRepository;

	private List<Segment> _segments;
	private Date _tomorrow;
	private Date _yesterday;

}