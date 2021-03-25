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
import com.liferay.osb.asah.common.model.Segment;
import com.liferay.osb.asah.common.repository.ChannelRepository;
import com.liferay.osb.asah.common.repository.SegmentRepository;
import com.liferay.osb.asah.common.util.SetUtil;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Inácio Nery
 */
public abstract class BaseSegmentRepositoryTestCase
	extends BaseRepositoryTestCase<Segment> {

	@Before
	public void setUp() {
		Segment segment1 = new Segment();

		Channel channel1 = _addChannel(1, "Liferay Brazil");

		segment1.setChannelId(channel1.getId());

		segment1.setCreateDate(new Date());
		segment1.setFilter("(channelId eq '1')");
		segment1.setName("Segment 1");
		segment1.setReferencedAssetDataSourceIds(SetUtil.of(5L, 6L));
		segment1.setReferencedAssetIds(SetUtil.of(3L, 4L));
		segment1.setReferencedFieldMappingIds(SetUtil.of(7L, 8L));
		segment1.setState("READY");
		segment1.setStatus("STARTED");
		segment1.setType(Segment.Type.DYNAMIC);

		Segment segment2 = new Segment();

		Channel channel2 = _addChannel(2, "Liferay USA");

		segment2.setChannelId(channel2.getId());

		segment2.setCreateDate(new Date());
		segment2.setFilter("(channelId eq '2')");
		segment2.setName("Segment 2");
		segment2.setReferencedAssetDataSourceIds(SetUtil.of(5L, 6L));
		segment2.setReferencedAssetIds(SetUtil.of(3L, 4L));
		segment2.setReferencedFieldMappingIds(SetUtil.of(7L, 8L));
		segment2.setState("READY");
		segment2.setStatus("STARTED");
		segment2.setType(Segment.Type.DYNAMIC);

		setUpRepository(segment1, segment2);
	}

	@Test
	public void testCountByIdAfter() {
		Assert.assertEquals(2, _segmentRepository.countByIdAfter(0L));

		Segment segment = entityModels.get(0);

		Assert.assertEquals(
			1, _segmentRepository.countByIdAfter(segment.getId()));
	}

	@Test
	public void testDeleteByChannelIdIn() {
		_segmentRepository.deleteByChannelIdIn(
			SetUtil.map(entityModels, Segment::getChannelId));

		Assert.assertEquals(0, _segmentRepository.count());
	}

	@Override
	@Test
	public void testFindAll() {
		super.testFindAll();

		Assert.assertEquals(
			Arrays.asList(entityModels.get(0)),
			_segmentRepository.findAll(PageRequest.of(0, 1)));
	}

	@Test
	public void testFindByNameAndStatus() {
		Optional<Segment> segmentOptional =
			_segmentRepository.findByNameAndStatus("Segment 1", "STARTED");

		Assert.assertTrue(segmentOptional.isPresent());

		segmentOptional = _segmentRepository.findByNameAndStatus(
			"Segment 1", "END");

		Assert.assertFalse(segmentOptional.isPresent());
	}

	@Test
	public void testFindByReferencedAssetDataSourceIdsAndStateNotAndType() {
		Assert.assertEquals(
			entityModels,
			_segmentRepository.
				findByReferencedAssetDataSourceIdsAndStateNotAndType(
					5L, "INACTIVE", Segment.Type.DYNAMIC));
	}

	@Override
	protected CrudRepository<Segment, Long> getCrudRepository() {
		return _segmentRepository;
	}

	private Channel _addChannel(long id, String name) {
		Channel channel = new Channel(name);

		channel.setId(id);
		channel.setIsNew(true);

		return _channelRepository.save(channel);
	}

	@Autowired
	private ChannelRepository _channelRepository;

	@Autowired
	private SegmentRepository _segmentRepository;

}