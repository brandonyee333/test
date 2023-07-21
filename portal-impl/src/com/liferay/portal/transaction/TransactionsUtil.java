/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.transaction;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.spring.aop.Skip;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.ReflectionUtil;
import com.liferay.portal.spring.aop.ServiceBeanAopCacheManager;
import com.liferay.portal.spring.aop.ServiceBeanAopCacheManagerUtil;
import com.liferay.portal.util.PropsUtil;
import com.liferay.portal.util.PropsValues;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import org.aopalliance.intercept.MethodInvocation;

/**
 * @author Miguel Pastor
 */
public class TransactionsUtil {

	public static void disableTransactions() {
		if (_log.isDebugEnabled()) {
			_log.debug("Disable transactions");
		}

		PropsValues.SPRING_HIBERNATE_SESSION_DELEGATED = false;

		try {
			Field field = ReflectionUtil.getDeclaredField(
				ServiceBeanAopCacheManager.class, "_annotations");

			field.set(
				null,
				new HashMap<MethodInvocation, Annotation[]>() {

					@Override
					public Annotation[] get(Object key) {
						return _annotations;
					}

					private final Annotation[] _annotations = {
						new Skip() {

							@Override
							public Class<? extends Annotation>
								annotationType() {

								return Skip.class;
							}

						}
					};

				});
		}
		catch (Exception e) {
			throw new RuntimeException(
				"Unexpected error disabling transactions", e);
		}
	}

	public static void enableTransactions() {
		if (_log.isDebugEnabled()) {
			_log.debug("Enable transactions");
		}

		PropsValues.SPRING_HIBERNATE_SESSION_DELEGATED = GetterUtil.getBoolean(
			PropsUtil.get(PropsKeys.SPRING_HIBERNATE_SESSION_DELEGATED));

		try {
			Field field = ReflectionUtil.getDeclaredField(
				ServiceBeanAopCacheManager.class, "_annotations");

			field.set(
				null, new ConcurrentHashMap<MethodInvocation, Annotation[]>());

			ServiceBeanAopCacheManagerUtil.reset();
		}
		catch (Exception e) {
			throw new RuntimeException(
				"Unexpected error disabling transactions", e);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		TransactionsUtil.class);

}