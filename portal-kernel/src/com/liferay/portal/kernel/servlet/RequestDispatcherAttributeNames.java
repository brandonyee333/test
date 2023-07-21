/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.servlet;

import com.liferay.portal.kernel.util.JavaConstants;

import java.util.HashSet;
import java.util.Set;

import javax.portlet.MimeResponse;

/**
 * @author Shuyang Zhou
 */
public class RequestDispatcherAttributeNames {

	public static boolean contains(String name) {
		return _attributeNames.contains(name);
	}

	private static final Set<String> _attributeNames = new HashSet<>();

	static {
		_attributeNames.add(JavaConstants.JAVAX_SERVLET_FORWARD_CONTEXT_PATH);
		_attributeNames.add(JavaConstants.JAVAX_SERVLET_FORWARD_PATH_INFO);
		_attributeNames.add(JavaConstants.JAVAX_SERVLET_FORWARD_QUERY_STRING);
		_attributeNames.add(JavaConstants.JAVAX_SERVLET_FORWARD_REQUEST_URI);
		_attributeNames.add(JavaConstants.JAVAX_SERVLET_FORWARD_SERVLET_PATH);
		_attributeNames.add(JavaConstants.JAVAX_SERVLET_INCLUDE_CONTEXT_PATH);
		_attributeNames.add(JavaConstants.JAVAX_SERVLET_INCLUDE_PATH_INFO);
		_attributeNames.add(JavaConstants.JAVAX_SERVLET_INCLUDE_QUERY_STRING);
		_attributeNames.add(JavaConstants.JAVAX_SERVLET_INCLUDE_REQUEST_URI);
		_attributeNames.add(JavaConstants.JAVAX_SERVLET_INCLUDE_SERVLET_PATH);
		_attributeNames.add(MimeResponse.MARKUP_HEAD_ELEMENT);
	}

}