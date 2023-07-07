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

package com.liferay.osb.asah.batch.curator.bot.nanite.test;

import com.liferay.osb.asah.batch.curator.OSBAsahBatchCuratorSpringTestContext;
import com.liferay.osb.asah.batch.curator.bot.nanite.UpdateMembershipsNanite;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.entity.Channel;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.repository.ChannelRepository;
import com.liferay.osb.asah.common.repository.SegmentRepository;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.test.util.configuration.JDBCTestConfiguration;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

/**
 * @author Rachael Koestartyo
 */
@Import(JDBCTestConfiguration.class)
public class UpdateMembershipsNaniteTest
	implements OSBAsahBatchCuratorSpringTestContext {

	@AfterEach
	public void tearDown() {
		_segmentRepository.deleteAll();

		_channelRepository.deleteAll();
	}

	@Test
	public void testRun() {
		ProjectIdThreadLocal.setProjectId("test");

		Segment segment = new Segment();

		segment.setAuthorName("Test Test");

		Channel channel1 = _addChannel(10, "Liferay Brazil");

		segment.setChannelId(channel1.getId());

		segment.setCreateDate(DateUtil.addDays(new Date(), -5));
		segment.setFilter("(channelId eq '10')");
		segment.setIsNew(Boolean.TRUE);
		segment.setName("Segment 1");
		segment.setState("IN_PROGRESS");
		segment.setStatus("ACTIVE");
		segment.setType(Segment.Type.DYNAMIC);

		_segmentRepository.save(segment);

		_updateMembershipsNanite.run(null);

		Optional<Segment> segmentOptional =
			_segmentRepository.findByNameAndStatus("Segment 1", "ACTIVE");

		segment = segmentOptional.get();

		Assertions.assertEquals("READY", segment.getState());
	}

	private Channel _addChannel(long id, String name) {
		Channel channel = new Channel(name);

		channel.setId(id);
		channel.setIsNew(Boolean.TRUE);

		return _channelRepository.save(channel);
	}

	@Autowired
	private ChannelRepository _channelRepository;

	@Autowired
	private SegmentRepository _segmentRepository;

	@Autowired
	private UpdateMembershipsNanite _updateMembershipsNanite;

}