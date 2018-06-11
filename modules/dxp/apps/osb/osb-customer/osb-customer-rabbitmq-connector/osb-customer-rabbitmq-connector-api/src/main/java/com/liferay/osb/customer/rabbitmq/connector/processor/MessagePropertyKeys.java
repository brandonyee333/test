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

package com.liferay.osb.customer.rabbitmq.connector.processor;

/**
 * @author Amos Fong
 */
public interface MessagePropertyKeys {

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