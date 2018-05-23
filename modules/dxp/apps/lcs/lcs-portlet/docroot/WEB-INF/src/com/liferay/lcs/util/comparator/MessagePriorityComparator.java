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

package com.liferay.lcs.util.comparator;

import com.liferay.lcs.messaging.CommandMessage;
import com.liferay.lcs.messaging.Message;

import java.util.Comparator;

/**
 * @author Igor Beslic
 */
public class MessagePriorityComparator implements Comparator<Message> {

	@Override
	public int compare(Message message1, Message message2) {
		if (message1 instanceof CommandMessage) {
			CommandMessage commandMessage = (CommandMessage)message1;

			String commandType = commandMessage.getCommandType();

			if (commandType.equals(CommandMessage.COMMAND_TYPE_SEND_PATCHES) ||
				commandType.equals(
					CommandMessage.COMMAND_TYPE_SEND_PORTAL_PROPERTIES)) {

				return 1;
			}
		}

		return -1;
	}

}