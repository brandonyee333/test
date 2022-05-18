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

package com.liferay.osb.asah.common.dog.test;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.MembershipChangeDog;
import com.liferay.osb.asah.common.entity.BQMembershipChange;
import com.liferay.osb.asah.common.entity.Channel;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.faro.info.dog.test.BaseFaroInfoDogTestCase;
import com.liferay.osb.asah.common.repository.BQMembershipChangeRepository;
import com.liferay.osb.asah.common.repository.ChannelRepository;
import com.liferay.osb.asah.common.repository.SegmentRepository;
import com.liferay.osb.asah.common.util.SetUtil;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateUtils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Alejo Ceballos
 */
public class MembershipChangeDogTest
	extends BaseFaroInfoDogTestCase
	implements OSBAsahTestExecutionListenersContext {

	@BeforeEach
	public void setUp() {
		List<String> channelNames = Arrays.asList(
			RandomTestUtil.randomString(), RandomTestUtil.randomString());

		Date date = DateUtil.newDayDate();

		channelNames.forEach(
			channelName -> {
				int daysAgoCreation = -32;

				Segment segment = _addSegment(
					_channelRepository.save(new Channel(channelName)),
					DateUtils.addDays(date, daysAgoCreation));

				for (int daysAgo = daysAgoCreation; daysAgo <= 0; daysAgo++) {
					BQMembershipChange bqMembershipChange =
						_addMembershipChange(
							DateUtils.addDays(date, daysAgo),
							33 + daysAgo +
								(channelNames.indexOf(channelName) * 33),
							segment);

					if (daysAgo == -1) {
						_bqMembershipChangeByIndividualSegmentId.put(
							segment.getId(), bqMembershipChange);
						_segments.add(segment);
					}
				}
			});
	}

	@Test
	public void testGetBQMembershipChanges() {
		Assertions.assertEquals(
			_bqMembershipChangeByIndividualSegmentId,
			_membershipChangeDog.getBQMembershipChanges(_segments));
	}

	@Test
	public void testGetLastBeforeTodayByIndividualSegmentId() {
		List<Long> segmentIds = new ArrayList(
			_bqMembershipChangeByIndividualSegmentId.keySet());

		Long segmentId = segmentIds.get(0);

		BQMembershipChange bqMembershipChange =
			_membershipChangeDog.getLastBeforeTodayByIndividualSegmentId(
				segmentId);

		Assertions.assertEquals(
			_bqMembershipChangeByIndividualSegmentId.get(segmentId),
			bqMembershipChange);
	}

	@Test
	public void testGetLastBeforeTodayByIndividualSegmentsId1() {
		List<Long> segmentIds = new ArrayList(
			_bqMembershipChangeByIndividualSegmentId.keySet());

		List<BQMembershipChange> bqMembershipChanges =
			_membershipChangeDog.getLastBeforeTodayByIndividualSegmentsId(
				segmentIds);

		Assertions.assertEquals(
			2, bqMembershipChanges.size(), bqMembershipChanges.toString());
		Assertions.assertNotEquals(
			bqMembershipChanges.get(0), bqMembershipChanges.get(1));

		for (BQMembershipChange bqMembershipChange : bqMembershipChanges) {
			Assertions.assertEquals(
				_bqMembershipChangeByIndividualSegmentId.get(
					bqMembershipChange.getIndividualSegmentId()),
				bqMembershipChange);
		}
	}

	@Test
	public void testGetLastBeforeTodayByIndividualSegmentsId2() {
		Date date = DateUtil.newDayDate();

		Segment segment = _addSegment(
			_channelRepository.save(new Channel(RandomTestUtil.randomString())),
			DateUtils.addDays(date, -1));

		segment.setIncludeAnonymousUsers(true);

		segment = _segmentRepository.save(segment);

		BQMembershipChange bqMembershipChange = _addMembershipChange(
			DateUtils.addDays(date, -1), 1, segment);

		_bqMembershipChangeRepository.save(bqMembershipChange);

		List<BQMembershipChange> bqMembershipChanges =
			_membershipChangeDog.getLastBeforeTodayByIndividualSegmentsId(
				Arrays.asList(segment.getId()));

		Assertions.assertEquals(
			1, bqMembershipChanges.size(), bqMembershipChanges.toString());
	}

	private BQMembershipChange _addMembershipChange(
		Date createDate, int index, Segment segment) {

		BQMembershipChange bqMembershipChange = new BQMembershipChange();

		bqMembershipChange.setCreateDate(createDate);
		bqMembershipChange.setIndividualsCount((long)index);
		bqMembershipChange.setIndividualSegmentId(segment.getId());
		bqMembershipChange.setKnownIndividualsCount((long)index);

		return _bqMembershipChangeRepository.save(bqMembershipChange);
	}

	private Segment _addSegment(Channel channel, Date createDate) {
		Segment segment = new Segment();

		segment.setChannelId(channel.getId());

		segment.setCreateDate(createDate);
		segment.setFilter(
			String.format("(channelId eq '%d')", channel.getId()));
		segment.setName(
			String.format("Segment of channel %s", channel.getName()));
		segment.setReferencedAssetDataSourceIds(SetUtil.of(5L, 6L));
		segment.setReferencedAssetIds(SetUtil.of(3L, 4L));
		segment.setReferencedFieldMappingIds(SetUtil.of(7L, 8L));
		segment.setState("READY");
		segment.setStatus("STARTED");
		segment.setType(Segment.Type.DYNAMIC);

		return _segmentRepository.save(segment);
	}

	private final Map<Long, BQMembershipChange>
		_bqMembershipChangeByIndividualSegmentId = new HashMap<>();

	@Autowired
	private BQMembershipChangeRepository _bqMembershipChangeRepository;

	@Autowired
	private ChannelRepository _channelRepository;

	@Autowired
	private MembershipChangeDog _membershipChangeDog;

	@Autowired
	private SegmentRepository _segmentRepository;

	private final List<Segment> _segments = new ArrayList<>();

}