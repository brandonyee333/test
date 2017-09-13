/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.rabbitmq.consumer;

/**
 * @author Amos Fong
 */
public interface RabbitMQProperties {

	public static final String APP_ID = "appId";

	public static final String BODY_SIZE = "bodySize";

	public static final String CLASS_ID = "classId";

	public static final String CLASS_NAME = "className";

	public static final String CLUSTER_ID = "clusterId";

	public static final String CONTENT_ENCODING = "contentEncoding";

	public static final String CONTENT_TYPE = "contentType";

	public static final String CORRELATION_ID = "correlationId";

	public static final String DELIVERY_MODE = "deliveryMode";

	public static final String EXPIRATION = "expiration";

	public static final String HEADERS = "headers";

	public static final String MESSAGE_ID = "messageId";

	public static final String PRIORITY = "priority";

	public static final String REPLY_TO = "replyTo";

	public static final String TIMESTAMP = "timestamp";

	public static final String TYPE = "type";

	public static final String USER_ID = "userId";

}