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

package com.liferay.osb.asah.publisher.messaging;

import com.liferay.osb.asah.common.messaging.Channel;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * @author Riccardo Ferrari
 */
@Component
public class DXPEntitiesChannels {

	public DXPEntitiesChannels() {
		_channels.put(
			"com.liferay.headless.commerce.machine.learning.dto.v1_0.Order",
			Channel.DXP_ENTITIES_ORDER);
		_channels.put(
			"com.liferay.headless.commerce.machine.learning.dto.v1_0.Product",
			Channel.DXP_ENTITIES_PRODUCT);
	}

	public Channel getChannel(String resourceName) {
		return _channels.getOrDefault(resourceName, _DEFAULT_CHANNEL);
	}

	private static final Channel _DEFAULT_CHANNEL =
		Channel.DXP_ENTITIES_DEFAULT;

	private static final Map<String, Channel> _channels = new HashMap<>();

}