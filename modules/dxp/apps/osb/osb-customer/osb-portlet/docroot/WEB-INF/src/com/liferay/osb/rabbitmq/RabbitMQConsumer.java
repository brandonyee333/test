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

package com.liferay.osb.rabbitmq;

import java.util.Map;

/**
 * @author Kyle Bischof
 */
public interface RabbitMQConsumer {

	public static final int RESPONSE_ACK = 1;

	public static final int RESPONSE_REJECT = 2;

	public static final int RESPONSE_REPUBLISH = 3;

	public static final int[] RESPONSES =
		{RESPONSE_ACK, RESPONSE_REJECT, RESPONSE_REPUBLISH};

	public int parse(
		String routingKey, String message, Map<String, Object> properties);

}