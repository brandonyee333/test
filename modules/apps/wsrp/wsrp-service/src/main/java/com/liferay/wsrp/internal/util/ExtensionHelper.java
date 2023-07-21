/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wsrp.internal.util;

import java.util.List;

import oasis.names.tc.wsrp.v2.types.Extension;

import org.apache.axis.message.MessageElement;

/**
 * @author Michael C. Han
 */
public interface ExtensionHelper {

	public void addMessageElement(
		List<MessageElement> messageElements, String name, String value);

	public Extension[] getExtensions(List<MessageElement> messageElements);

	public Extension[] getExtensions(String name, String value);

	public MessageElement[] getMessageElements(Extension[] extensions);

	public String getNameAttribute(MessageElement messageElement);

}