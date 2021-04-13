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

import com.liferay.osb.asah.common.model.Channel;
import com.liferay.osb.asah.common.model.MembershipChange;
import com.liferay.osb.asah.common.model.Segment;
import com.liferay.osb.asah.common.repository.ChannelRepository;
import com.liferay.osb.asah.common.repository.MembershipChangeRepository;
import com.liferay.osb.asah.common.repository.SegmentRepository;
import com.liferay.osb.asah.common.util.SetUtil;

import java.util.Date;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Marcellus Tavares
 */
public abstract class BaseMembershipChangeRepositoryTestCase
	extends BaseRepositoryTestCase<MembershipChange> {

	@Before
	public void setUp() {
		MembershipChange membershipChange = new MembershipChange();

		membershipChange.setCreateDate(new Date());
		membershipChange.setIndividualDeleted(Boolean.FALSE);
		membershipChange.setIndividualId(123L);
		membershipChange.setIndividualName("Nina Simone");

		Segment segment = _addSegment();

		membershipChange.setIndividualSegmentId(segment.getId());

		membershipChange.setKnownIndividualsCount(1L);
		membershipChange.setModifiedDate(new Date());
		membershipChange.setOperation("ADDED");

		setUpRepository(membershipChange);

		_membershipChange = entityModels.get(0);
	}

	@Test
	public void testDeleteByIndividualSegmentId() {
		_membershipChangeRepository.deleteByIndividualSegmentId(
			_membershipChange.getIndividualSegmentId());

		Long membershipChangeId = _membershipChange.getId();

		Assert.assertNotNull(membershipChangeId);

		Optional<MembershipChange> membershipChangeOptional =
			_membershipChangeRepository.findById(membershipChangeId);

		Assert.assertFalse(membershipChangeOptional.isPresent());
	}

	@Test
	public void testFindByIndividualId() {
		Assert.assertEquals(
			Optional.of(_membershipChange),
			_membershipChangeRepository.findByIndividualId(
				_membershipChange.getIndividualId()));
	}

	@Test
	public void testUpdateIndividualDeletedByIndividualId() {
		_membershipChangeRepository.updateIndividualDeletedByIndividualId(
			Boolean.TRUE, _membershipChange.getIndividualId());

		Long membershipChangeId = _membershipChange.getId();

		Assert.assertNotNull(membershipChangeId);

		Optional<MembershipChange> membershipChangeOptional =
			_membershipChangeRepository.findById(membershipChangeId);

		Assert.assertTrue(membershipChangeOptional.isPresent());

		MembershipChange actualMembershipChange =
			membershipChangeOptional.get();

		Assert.assertTrue(actualMembershipChange.getIndividualDeleted());
	}

	@Test
	public void testUpdateIndividualNameByIndividualId() {
		_membershipChangeRepository.updateIndividualNameByIndividualId(
			_membershipChange.getIndividualId(), "Frank Sinatra");

		Long membershipChangeId = _membershipChange.getId();

		Assert.assertNotNull(membershipChangeId);

		Optional<MembershipChange> membershipChangeOptional =
			_membershipChangeRepository.findById(membershipChangeId);

		Assert.assertTrue(membershipChangeOptional.isPresent());

		MembershipChange actualMembershipChange =
			membershipChangeOptional.get();

		Assert.assertEquals(
			"Frank Sinatra", actualMembershipChange.getIndividualName());
	}

	@Override
	protected CrudRepository<MembershipChange, Long> getCrudRepository() {
		return _membershipChangeRepository;
	}

	private Segment _addSegment() {
		Segment segment = new Segment();

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

	private MembershipChange _membershipChange;

	@Autowired
	private MembershipChangeRepository _membershipChangeRepository;

	@Autowired
	private SegmentRepository _segmentRepository;

}