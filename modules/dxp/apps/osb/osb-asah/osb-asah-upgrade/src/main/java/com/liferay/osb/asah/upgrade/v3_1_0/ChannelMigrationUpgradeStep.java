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

package com.liferay.osb.asah.upgrade.v3_1_0;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.entity.Channel;
import com.liferay.osb.asah.common.repository.ChannelRepository;

import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Matthew Kong
 */
@Component
public class ChannelMigrationUpgradeStep extends BaseMigrationUpgradeStep {

	@Override
	protected Consumer<Object> getConsumer() {
		return object -> {
			Channel channel = _objectMapper.convertValue(object, Channel.class);

			channel.setIsNew(Boolean.TRUE);

			_channelRepository.save(channel);
		};
	}

	@Override
	protected String getElasticsearchCollectionName() {
		return "channels";
	}

	@Override
	protected String getSelectLatestIdSQL() {
		return "SELECT id FROM channel ORDER BY id DESC LIMIT 1";
	}

	@Autowired
	private ChannelRepository _channelRepository;

	@Autowired
	private ObjectMapper _objectMapper;

}