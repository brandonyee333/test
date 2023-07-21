/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.pacl;

import com.liferay.portal.kernel.security.pacl.permission.PortalServicePermission;
import com.liferay.portal.spring.aop.AdvisedSupportProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;

import org.springframework.aop.framework.AdvisedSupport;

/**
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 */
public class PACLInvocationHandler
	implements AdvisedSupportProxy, InvocationHandler {

	public PACLInvocationHandler(InvocationHandler invocationHandler) {
		this(invocationHandler, null);
	}

	public PACLInvocationHandler(
		InvocationHandler invocationHandler, AdvisedSupport advisedSupport) {

		_invocationHandler = invocationHandler;
		_advisedSupport = advisedSupport;
	}

	@Override
	public AdvisedSupport getAdvisedSupport() {
		return _advisedSupport;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] arguments)
		throws Throwable {

		try {
			return doInvoke(proxy, method, arguments);
		}
		catch (InvocationTargetException ite) {
			throw ite.getTargetException();
		}
	}

	protected Object doInvoke(Object proxy, Method method, Object[] arguments)
		throws Throwable {

		if (method.getDeclaringClass() == Object.class) {
			String methodName = method.getName();

			if (methodName.equals("equals")) {
				if (proxy == arguments[0]) {
					return true;
				}

				return false;
			}
			else if (methodName.equals("toString")) {
				return _invocationHandler.invoke(proxy, method, arguments);
			}
		}

		PortalServicePermission.checkService(proxy, method, arguments);

		try {
			return AccessController.doPrivileged(
				new InvokePrivilegedExceptionAction(
					_invocationHandler, proxy, method, arguments));
		}
		catch (PrivilegedActionException pae) {
			Exception e = pae.getException();

			throw e.getCause();
		}
	}

	private final AdvisedSupport _advisedSupport;
	private InvocationHandler _invocationHandler;

	private static class InvokePrivilegedExceptionAction
		implements PrivilegedExceptionAction<Object> {

		public InvokePrivilegedExceptionAction(
			InvocationHandler invocationHandler, Object proxy, Method method,
			Object[] arguments) {

			_invocationHandler = invocationHandler;
			_proxy = proxy;
			_method = method;
			_arguments = arguments;
		}

		@Override
		public Object run() throws Exception {
			try {
				return _invocationHandler.invoke(_proxy, _method, _arguments);
			}
			catch (Throwable t) {
				throw new Exception(t);
			}
		}

		private final Object[] _arguments;
		private InvocationHandler _invocationHandler;
		private final Method _method;
		private final Object _proxy;

	}

}