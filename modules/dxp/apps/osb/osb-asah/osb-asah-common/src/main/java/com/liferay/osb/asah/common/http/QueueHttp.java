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

package com.liferay.osb.asah.common.http;

/**
 * @author André Miranda
 */
public interface QueueHttp {

	public static final String QUEUE_NAME_ACTIVE_INDIVIDUAL_IDS = "104";

	public static final String QUEUE_NAME_DXP_ENTITIES = "103";

	public static final String QUEUE_NAME_IDENTITY = "102";

	public String getMessages(String queueName);

	public String getMessages(String queueName, long total);

	public int getMessagesCount(String queueName);

	public void initializeQueue();

	public void pushMessage(String message, String queueName);

}