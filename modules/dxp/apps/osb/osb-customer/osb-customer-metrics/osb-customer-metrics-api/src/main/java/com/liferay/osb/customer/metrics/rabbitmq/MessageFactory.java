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

package com.liferay.osb.customer.metrics.rabbitmq;

import com.liferay.osb.distributed.messaging.Message;

/**
 * @author Jenny Chen
 */
public interface MessageFactory {

	public Message createDropMessage(String modelClassName) throws Exception;

	public Message createRemoveMessage(String modelClassName, Object model)
		throws Exception;

	public Message createUpdateMessage(String modelClassName, Object model)
		throws Exception;

}