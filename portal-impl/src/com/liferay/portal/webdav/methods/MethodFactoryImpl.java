/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.webdav.methods;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.webdav.WebDAVException;
import com.liferay.portal.kernel.webdav.methods.Method;
import com.liferay.portal.kernel.webdav.methods.MethodFactory;
import com.liferay.portal.util.PropsUtil;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Brian Wing Shun Chan
 */
public class MethodFactoryImpl implements MethodFactory {

	public MethodFactoryImpl() throws Exception {
		for (String methodName : Method.SUPPORTED_METHOD_NAMES) {
			addMethod(methodName);
		}
	}

	@Override
	public Method create(HttpServletRequest request) throws WebDAVException {
		String method = request.getMethod();

		Method methodImpl = (Method)_methods.get(
			StringUtil.toUpperCase(method));

		if (methodImpl == null) {
			throw new WebDAVException(
				"Method " + method + " is not implemented");
		}

		return methodImpl;
	}

	protected void addMethod(String methodName) throws Exception {
		String defaultClassName = methodName.substring(1);

		defaultClassName = StringUtil.toLowerCase(defaultClassName);
		defaultClassName = methodName.substring(0, 1) + defaultClassName;
		defaultClassName =
			"com.liferay.portal.webdav.methods." + defaultClassName +
				"MethodImpl";

		String className = GetterUtil.getString(
			PropsUtil.get(MethodFactoryImpl.class.getName() + "." + methodName),
			defaultClassName);

		_methods.put(methodName, InstanceFactory.newInstance(className));
	}

	private final Map<String, Object> _methods = new HashMap<>();

}