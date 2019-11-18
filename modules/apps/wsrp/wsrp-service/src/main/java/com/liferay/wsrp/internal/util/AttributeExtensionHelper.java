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