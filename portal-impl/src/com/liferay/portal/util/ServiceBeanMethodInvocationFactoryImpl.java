/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.util;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.security.pacl.DoPrivileged;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ServiceBeanMethodInvocationFactory;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.spring.aop.ServiceBeanMethodInvocation;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aopalliance.intercept.MethodInterceptor;

/**
 * @author Brian Wing Shun Chan
 * @author Wesley Gong
 */
@DoPrivileged
public class ServiceBeanMethodInvocationFactoryImpl
	implements ServiceBeanMethodInvocationFactory {

	@Override
	public Object proceed(
			Object target, Class<?> targetClass, Method method,
			Object[] arguments, String[] methodInterceptorBeanIds)
		throws Exception {

		if (ArrayUtil.isEmpty(methodInterceptorBeanIds)) {
			throw new IllegalArgumentException(
				"Method interceptor bean IDs array is empty");
		}

		ServiceBeanMethodInvocation serviceBeanMethodInvocation =
			new ServiceBeanMethodInvocation(target, method, arguments);

		List<MethodInterceptor> methodInterceptors = getMethodInterceptors(
			methodInterceptorBeanIds);

		serviceBeanMethodInvocation.setMethodInterceptors(methodInterceptors);

		try {
			return serviceBeanMethodInvocation.proceed();
		}
		catch (Throwable t) {
			if (t instanceof Exception) {
				throw (Exception)t;
			}

			throw new Exception(t);
		}
	}

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	protected ServiceBeanMethodInvocation create(
		Object target, Class<?> targetClass, Method method,
		Object[] arguments) {

		return new ServiceBeanMethodInvocation(target, method, arguments);
	}

	protected List<MethodInterceptor> getMethodInterceptors(
		String... methodInterceptorBeanIds) {

		String methodInterceptorsKey = StringUtil.merge(
			methodInterceptorBeanIds);

		List<MethodInterceptor> methodInterceptors = _methodInterceptors.get(
			methodInterceptorsKey);

		if (methodInterceptors != null) {
			return methodInterceptors;
		}

		methodInterceptors = new ArrayList<>();

		for (String methodInterceptorBeanId : methodInterceptorBeanIds) {
			MethodInterceptor methodInterceptor =
				(MethodInterceptor)PortalBeanLocatorUtil.locate(
					methodInterceptorBeanId);

			methodInterceptors.add(methodInterceptor);
		}

		_methodInterceptors.put(methodInterceptorsKey, methodInterceptors);

		return methodInterceptors;
	}

	private final Map<String, List<MethodInterceptor>> _methodInterceptors =
		new HashMap<>();

}