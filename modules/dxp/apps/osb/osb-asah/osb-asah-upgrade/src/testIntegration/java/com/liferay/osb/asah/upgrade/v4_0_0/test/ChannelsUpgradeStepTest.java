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

package com.liferay.osb.asah.upgrade.v4_0_0.test;

import com.liferay.osb.asah.common.entity.Channel;
import com.liferay.osb.asah.common.repository.ChannelRepository;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.upgrade.OSBAsahUpgradeSpringTestContext;
import com.liferay.osb.asah.upgrade.v4_0_0.ChannelsUpgradeStep;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.util.List;

import javax.sql.DataSource;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

/**
 * @author Leilany Ulisses
 */
public class ChannelsUpgradeStepTest
	implements OSBAsahUpgradeSpringTestContext {

	@AfterEach
	public void tearDown() {
		_channelRepository.deleteAll();
	}

	@Test
	public void testUpgrade() throws Exception {
		ProjectIdThreadLocal.setProjectId("test");

		_channelRepository.save(new Channel("name"));

		try (Connection connection = _dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(
				"ALTER TABLE Channel DROP COLUMN state")) {

			preparedStatement.execute();
		}

		_channelsUpgradeStep.upgrade("");

		List<Channel> channelsByName =
			_channelRepository.findByNameContainingIgnoreCase(
				"name", PageRequest.of(0, 1));

		Channel channel = channelsByName.get(0);

		Assertions.assertEquals("READY", channel.getState());
	}

	@Autowired
	private ChannelRepository _channelRepository;

	@Autowired
	private ChannelsUpgradeStep _channelsUpgradeStep;

	@Autowired
	private DataSource _dataSource;

}