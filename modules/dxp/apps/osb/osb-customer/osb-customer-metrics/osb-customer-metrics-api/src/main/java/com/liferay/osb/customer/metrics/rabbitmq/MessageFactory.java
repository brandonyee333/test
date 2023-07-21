/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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