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
import com.liferay.osb.asah.common.model.ChannelDataSource;
import com.liferay.osb.asah.common.repository.ChannelRepository;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.common.util.SetUtil;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Inácio Nery
 */
@ContextConfiguration(classes = OSBAsahSpringBootApplication.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class ChannelRepositoryTest {

	@Before
	public void setUp() {
		_channel = _channelRepository.save(new Channel("name"));
	}

	@After
	public void tearDown() {
		_channelRepository.deleteAll();
	}

	@Test
	public void testCount() {
		Assert.assertEquals(1, _channelRepository.count());
	}

	@Test
	public void testCountByNameContainingIgnoreCase() {
		Assert.assertEquals(
			1, _channelRepository.countByNameContainingIgnoreCase("NAME"));
	}

	@Test
	public void testDelete() {
		_channelRepository.delete(_channel);

		Assert.assertEquals(0, _channelRepository.count());
	}

	@Test
	public void testDeleteAll1() {
		_channelRepository.deleteAll();

		Assert.assertEquals(0, _channelRepository.count());
	}

	@Test
	public void testDeleteAll2() {
		Iterable<Channel> channels = new ArrayList<>(Arrays.asList(_channel));

		_channelRepository.deleteAll(channels);

		Assert.assertEquals(0, _channelRepository.count());
	}

	@Test
	public void testDeleteById() {
		_channelRepository.deleteById(_channel.getId());

		Assert.assertEquals(0, _channelRepository.count());
	}

	@Test
	public void testDeleteByIdIn() {
		_channelRepository.deleteByIdIn(
			Collections.singleton(_channel.getId()));

		Assert.assertEquals(0, _channelRepository.count());
	}

	@Test
	public void testExistsById() {
		Assert.assertTrue(_channelRepository.existsById(_channel.getId()));
	}

	@Test
	public void testExistsByIdNotAndName() {
		Channel channel = _channelRepository.save(new Channel("channel"));

		Assert.assertEquals(
			true,
			_channelRepository.existsByIdNotAndName(channel.getId(), "name"));

		Assert.assertEquals(
			false,
			_channelRepository.existsByIdNotAndName(_channel.getId(), "name"));
	}

	@Test
	public void testExistsByName() {
		Assert.assertTrue(_channelRepository.existsByName("name"));
	}

	@Test
	public void testFindAll1() {
		Iterable<Channel> channels = new ArrayList<>(Arrays.asList(_channel));

		Assertions.assertIterableEquals(channels, _channelRepository.findAll());
	}

	@Test
	public void testFindAll2() {
		Iterable<Channel> channels = new ArrayList<>(Arrays.asList(_channel));

		Assertions.assertIterableEquals(
			channels, _channelRepository.findAll(PageRequest.of(0, 1)));
	}

	@Test
	public void testFindAllById() {
		Iterable<Channel> channels = new ArrayList<>(Arrays.asList(_channel));

		Assertions.assertIterableEquals(
			channels,
			_channelRepository.findAllById(
				new ArrayList<>(Arrays.asList(_channel.getId()))));
	}

	@Test
	public void testFindByDataSourceId() {
		ChannelDataSource channelDataSource = new ChannelDataSource(
			123L, Collections.emptySet());

		_channel.addChannelDataSource(channelDataSource);

		_channelRepository.save(_channel);

		List<Channel> channels = _channelRepository.findByDataSourceId(123L);

		Assert.assertEquals(channels.toString(), 1, channels.size());

		Channel channel = channels.get(0);

		Set<ChannelDataSource> channelDataSources =
			channel.getChannelDataSources();

		Assert.assertEquals(
			channelDataSources.toString(), 1, channelDataSources.size());

		Assert.assertTrue(channelDataSources.contains(channelDataSource));
	}

	@Test
	public void testFindByDataSourceIdAndGroupIds() {
		ChannelDataSource channelDataSource = new ChannelDataSource(
			123L, SetUtil.of(456L, 789L));

		_channel.addChannelDataSource(channelDataSource);

		_channelRepository.save(_channel);

		List<Channel> channels =
			_channelRepository.findByDataSourceIdAndGroupIds(
				123L, SetUtil.of(456L, 789L));

		Assert.assertEquals(channels.toString(), 1, channels.size());

		Channel channel = channels.get(0);

		Set<ChannelDataSource> channelDataSources =
			channel.getChannelDataSources();

		Assert.assertEquals(
			channelDataSources.toString(), 1, channelDataSources.size());

		Assert.assertTrue(channelDataSources.contains(channelDataSource));
	}

	@Test
	public void testFindById() {
		Optional<Channel> channelOptional = _channelRepository.findById(
			_channel.getId());

		Assert.assertTrue(channelOptional.isPresent());

		_assertChannel(channelOptional.get());
	}

	@Test
	public void testFindByNameContainingIgnoreCase() {
		List<Channel> channels =
			_channelRepository.findByNameContainingIgnoreCase(
				"NAME", PageRequest.of(0, 1));

		Assert.assertEquals(channels.toString(), 1, channels.size());

		_assertChannel(channels.get(0));
	}

	@Test
	public void testSave() {
		_assertChannel(_channel);
	}

	@Test
	public void testSaveAll() {
		Iterable<Channel> channels = new ArrayList<>(Arrays.asList(_channel));

		Assertions.assertIterableEquals(
			channels, _channelRepository.saveAll(channels));
	}

	private void _assertChannel(Channel channel) {
		Assert.assertNotNull(channel);
		Assert.assertNotNull(channel.getId());
		Assert.assertEquals("name", channel.getName());
	}

	private Channel _channel;

	@Autowired
	private ChannelRepository _channelRepository;

}