/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wsrp.internal.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Iterator;
import java.util.List;

import org.apache.axis.message.MessageElement;

/**
 * @author Michael C. Han
 */
public class AttributeExtensionHelper extends BaseExtensionHelper {

	@Override
	public void addMessageElement(
		List<MessageElement> messageElements, String name, String value) {

		MessageElement messageElement = new MessageElement(
			"http://www.liferay.com/wsrp", "extension");

		try {
			messageElement.addAttribute(
				"http://www.liferay.com/wsrp", "name", name);
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		messageElement.setValue(value);

		messageElements.add(messageElement);
	}

	@Override
	public String getNameAttribute(MessageElement messageElement) {
		String name = null;

		Iterator<String> iterator = messageElement.getNamespacePrefixes();

		if (iterator.hasNext()) {
			String namespacePrefix = iterator.next();

			String namespaceURI = messageElement.getNamespaceURI(
				namespacePrefix);

			name = messageElement.getAttributeNS(namespaceURI, "name");
		}

		if (Validator.isNull(name)) {
			name = messageElement.getAttribute("name");
		}

		return name;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AttributeExtensionHelper.class);

}