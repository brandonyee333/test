/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.metrics.processor.distributed.messaging.rabbitmq.connector;

import com.liferay.osb.distributed.messaging.rabbitmq.connector.BaseConnection;

import org.osgi.service.component.annotations.Component;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true,
	property = {"host=", "password=", "port=", "username=", "useSSL="},
	service = LegacyConnection.class
)
public class LegacyConnection extends BaseConnection {
}