/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wsrp.internal.util;

import java.util.ArrayList;
import java.util.List;

import oasis.names.tc.wsrp.v2.types.Extension;

import org.apache.axis.message.MessageElement;

/**
 * @author Michael C. Han
 */
public abstract class BaseExtensionHelper implements ExtensionHelper {

	@Override
	public Extension[] getExtensions(List<MessageElement> messageElements) {
		Extension[] extensions = new Extension[messageElements.size()];

		// Wrap the extension in an extension to be compatible with Oracle's
		// producer

		for (int i = 0; i < messageElements.size(); i++) {
			MessageElement messageElement = messageElements.get(i);

			extensions[i] = new Extension(
				new MessageElement[] {messageElement});
		}

		return extensions;
	}

	@Override
	public Extension[] getExtensions(String localPart, String value) {
		List<MessageElement> messageElements = new ArrayList<>();

		addMessageElement(messageElements, localPart, value);

		return getExtensions(messageElements);
	}

	@Override
	public MessageElement[] getMessageElements(Extension[] extensions) {
		if ((extensions == null) || (extensions.length <= 0)) {
			return null;
		}

		MessageElement[] messageElements =
			new MessageElement[extensions.length];

		for (int i = 0; i < extensions.length; i++) {
			MessageElement[] messageElementsWrapper = extensions[i].get_any();

			messageElements[i] = messageElementsWrapper[0];
		}

		return messageElements;
	}

}