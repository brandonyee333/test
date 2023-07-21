/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wsrp.internal.util;

import java.util.List;

import org.apache.axis.message.MessageElement;

/**
 * @author Michael C. Han
 */
public class ElementExtensionHelper extends BaseExtensionHelper {

	@Override
	public void addMessageElement(
		List<MessageElement> messageElements, String localPart, String value) {

		MessageElement messageElement = new MessageElement(
			"http://www.liferay.com/wsrp", localPart);

		messageElement.setValue(value);

		messageElements.add(messageElement);
	}

	@Override
	public String getNameAttribute(MessageElement messageElement) {
		return messageElement.getName();
	}

}