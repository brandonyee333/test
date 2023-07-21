/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.servlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;

import java.io.NotSerializableException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Igor Spasic
 */
public class NonSerializableObjectRequestWrapper
	extends PersistentHttpServletRequestWrapper {

	public static boolean isWrapped(HttpServletRequest request) {
		if (!_WEBLOGIC_REQUEST_WRAP_NON_SERIALIZABLE) {
			return false;
		}

		Class<?> clazz = request.getClass();

		String className = clazz.getName();

		if (className.startsWith("weblogic.")) {
			request.removeAttribute(
				NonSerializableObjectRequestWrapper.class.getName());

			return false;
		}

		Boolean wrapped = (Boolean)request.getAttribute(
			NonSerializableObjectRequestWrapper.class.getName());

		if (wrapped == null) {
			return false;
		}

		return wrapped.booleanValue();
	}

	public NonSerializableObjectRequestWrapper(HttpServletRequest request) {
		super(request);

		request.setAttribute(
			NonSerializableObjectRequestWrapper.class.getName(), Boolean.TRUE);
	}

	@Override
	public Object getAttribute(String name) {
		Object object = null;

		try {
			object = super.getAttribute(name);
		}
		catch (Exception e) {
			if (e instanceof NotSerializableException) {

				// LPS-31885

				String message = e.getMessage();

				if ((message == null) || !message.contains("BEA-101362")) {
					_log.error(e, e);
				}
			}

			return null;
		}

		return NonSerializableObjectHandler.getValue(object);
	}

	@Override
	public void setAttribute(String name, Object object) {
		if (_WEBLOGIC_REQUEST_WRAP_NON_SERIALIZABLE) {
			object = new NonSerializableObjectHandler(object);
		}

		super.setAttribute(name, object);
	}

	private static final boolean _WEBLOGIC_REQUEST_WRAP_NON_SERIALIZABLE =
		GetterUtil.getBoolean(
			PropsUtil.get(PropsKeys.WEBLOGIC_REQUEST_WRAP_NON_SERIALIZABLE));

	private static final Log _log = LogFactoryUtil.getLog(
		NonSerializableObjectRequestWrapper.class);

}