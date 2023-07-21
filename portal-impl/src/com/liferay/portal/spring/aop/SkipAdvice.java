/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.spring.aop;

import com.liferay.portal.kernel.spring.aop.Skip;

import java.lang.annotation.Annotation;

import java.util.Collections;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author Shuyang Zhou
 */
public class SkipAdvice extends AnnotationChainableMethodAdvice<Skip> {

	@Override
	public Object before(MethodInvocation methodInvocation) throws Throwable {
		Skip skip = findAnnotation(methodInvocation);

		if (skip != _nullSkip) {
			MethodInterceptorsBag methodInterceptorsBag =
				serviceBeanAopCacheManager.getMethodInterceptorsBag(
					methodInvocation);

			if (methodInterceptorsBag == null) {
				return null;
			}

			MethodInterceptorsBag newMethodInterceptorsBag =
				new MethodInterceptorsBag(
					methodInterceptorsBag.getClassLevelMethodInterceptors(),
					Collections.<MethodInterceptor>emptyList());

			serviceBeanAopCacheManager.putMethodInterceptorsBag(
				methodInvocation, newMethodInterceptorsBag);

			ServiceBeanMethodInvocation serviceBeanMethodInvocation =
				(ServiceBeanMethodInvocation)methodInvocation;

			serviceBeanMethodInvocation.setMethodInterceptors(
				Collections.<MethodInterceptor>emptyList());
		}

		return null;
	}

	@Override
	public Skip getNullAnnotation() {
		return _nullSkip;
	}

	private static final Skip _nullSkip = new Skip() {

		@Override
		public Class<? extends Annotation> annotationType() {
			return Skip.class;
		}

	};

}