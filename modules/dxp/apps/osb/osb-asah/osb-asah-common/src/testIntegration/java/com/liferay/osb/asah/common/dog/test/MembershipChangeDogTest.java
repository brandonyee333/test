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
import com.liferay.osb.asah.common.entity.Channel;
import com.liferay.osb.asah.common.entity.MembershipChange;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.faro.info.dog.test.BaseFaroInfoDogTestCase;
import com.liferay.osb.asah.common.repository.ChannelRepository;
import com.liferay.osb.asah.common.repository.MembershipChangeRepository;
import com.liferay.osb.asah.common.repository.SegmentRepository;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.common.util.SetUtil;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateUtils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Alejo Ceballos
 */
@ContextConfiguration(classes = OSBAsahSpringBootApplication.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class MembershipChangeDogTest extends BaseFaroInfoDogTestCase {

	@Before
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
					MembershipChange membershipChange = _addMembershipChange(
						33 + daysAgo, DateUtils.addDays(date, daysAgo),
						segment);

					if (daysAgo == -1) {
						_membershipChangeByIndividualSegmentId.put(
							segment.getId(), membershipChange);
					}
				}
			});
	}

	@Test
	public void testGetLastFrom30DaysByIndividualSegmentId() {
		List<Long> segmentIds = new ArrayList(
			_membershipChangeByIndividualSegmentId.keySet());

		List<MembershipChange> membershipChanges =
			_membershipChangeDog.getLastFrom30DaysByIndividualSegmentsId(
				segmentIds);

		Assert.assertEquals(
			membershipChanges.toString(), 2, membershipChanges.size());
		Assert.assertNotEquals(
			membershipChanges.get(0), membershipChanges.get(1));

		for (MembershipChange membershipChange : membershipChanges) {
			Assert.assertEquals(
				_membershipChangeByIndividualSegmentId.get(
					membershipChange.getIndividualSegmentId()),
				membershipChange);
		}
	}

	private MembershipChange _addMembershipChange(
		int index, Date modifiedDate, Segment segment) {

		MembershipChange membershipChange = new MembershipChange();

		membershipChange.setIndividualDeleted(Boolean.FALSE);
		membershipChange.setIndividualEmail(
			String.format("individual.%d@liferay.com", index));
		membershipChange.setIndividualId((long)index);
		membershipChange.setIndividualName(
			String.format("Individual Name %d", index));

		membershipChange.setIndividualSegmentId(segment.getId());

		membershipChange.setJoinedDate(modifiedDate);
		membershipChange.setKnownIndividualsCount((long)index);
		membershipChange.setModifiedDate(modifiedDate);
		membershipChange.setOperation("ADDED");

		return _membershipChangeRepository.save(membershipChange);
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

	@Autowired
	private ChannelRepository _channelRepository;

	private final Map<Long, MembershipChange>
		_membershipChangeByIndividualSegmentId = new HashMap<>();

	@Autowired
	private MembershipChangeDog _membershipChangeDog;

	@Autowired
	private MembershipChangeRepository _membershipChangeRepository;

	@Autowired
	private SegmentRepository _segmentRepository;

}