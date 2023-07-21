/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.client.internal.util.comparator;

import com.liferay.lcs.messaging.CommandMessage;
import com.liferay.lcs.messaging.Message;
import com.liferay.lcs.messaging.SendPatchesCommandMessage;
import com.liferay.lcs.messaging.SendPortalPropertiesCommandMessage;

import java.util.Comparator;

/**
 * @author Igor Beslic
 */
public class MessagePriorityComparator implements Comparator<Message> {

	@Override
	public int compare(Message message1, Message message2) {
		if (message1 instanceof CommandMessage) {
			CommandMessage commandMessage = (CommandMessage)message1;

			if (commandMessage instanceof SendPatchesCommandMessage ||
				commandMessage instanceof SendPortalPropertiesCommandMessage) {

				return 1;
			}
		}

		return -1;
	}

}