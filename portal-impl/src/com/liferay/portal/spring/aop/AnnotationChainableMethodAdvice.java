/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.spring.aop;

import com.liferay.petra.reflect.AnnotationLocator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.aopalliance.intercept.MethodInvocation;

/**
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 */
public abstract class AnnotationChainableMethodAdvice<T extends Annotation>
	extends ChainableMethodAdvice {

	public AnnotationChainableMethodAdvice() {
		_nullAnnotation = getNullAnnotation();

		_annotationClass = _nullAnnotation.annotationType();
	}

	public Class<? extends Annotation> getAnnotationClass() {
		return _annotationClass;
	}

	public abstract T getNullAnnotation();

	protected T findAnnotation(MethodInvocation methodInvocation) {
		Annotation annotation = ServiceBeanAopCacheManager.getAnnotation(
			methodInvocation, _annotationClass, _nullAnnotation);

		if (annotation != null) {
			return (T)annotation;
		}

		Method method = methodInvocation.getMethod();

		ServiceBeanMethodInvocation serviceBeanMethodInvocation =
			(ServiceBeanMethodInvocation)methodInvocation;

		Class<?> targetClass = serviceBeanMethodInvocation.getTargetClass();

		List<Annotation> annotations = AnnotationLocator.locate(
			method, targetClass);

		Iterator<Annotation> iterator = annotations.iterator();

		while (iterator.hasNext()) {
			Annotation curAnnotation = iterator.next();

			if (!serviceBeanAopCacheManager.isRegisteredAnnotationClass(
					curAnnotation.annotationType())) {

				iterator.remove();
			}
		}

		ServiceBeanAopCacheManager.putAnnotations(
			methodInvocation, annotations.toArray(new Annotation[0]));

		Set<Class<? extends Annotation>> annotationClasses = new HashSet<>();

		annotation = _nullAnnotation;

		for (Annotation curAnnotation : annotations) {
			Class<? extends Annotation> annotationClass =
				curAnnotation.annotationType();

			if (annotationClass == _annotationClass) {
				annotation = curAnnotation;
			}

			annotationClasses.add(annotationClass);
		}

		Map<Class<? extends Annotation>, AnnotationChainableMethodAdvice<?>[]>
			annotationChainableMethodAdvices =
				serviceBeanAopCacheManager.
					getRegisteredAnnotationChainableMethodAdvices();

		for (Map.Entry
				<Class<? extends Annotation>,
				 AnnotationChainableMethodAdvice<?>[]> entry :
					annotationChainableMethodAdvices.entrySet()) {

			Class<? extends Annotation> annotationClass = entry.getKey();
			AnnotationChainableMethodAdvice<?>[]
				annotationChainableMethodAdvicesArray = entry.getValue();

			if (annotationClasses.contains(annotationClass) ||
				(annotationChainableMethodAdvicesArray == null)) {

				continue;
			}

			for (AnnotationChainableMethodAdvice<?>
					annotationChainableMethodAdvice :
						annotationChainableMethodAdvicesArray) {

				serviceBeanAopCacheManager.removeMethodInterceptor(
					methodInvocation, annotationChainableMethodAdvice);
			}
		}

		return (T)annotation;
	}

	@Override
	protected void setServiceBeanAopCacheManager(
		ServiceBeanAopCacheManager serviceBeanAopCacheManager) {

		if (this.serviceBeanAopCacheManager != null) {
			return;
		}

		this.serviceBeanAopCacheManager = serviceBeanAopCacheManager;

		serviceBeanAopCacheManager.registerAnnotationChainableMethodAdvice(
			_annotationClass, this);
	}

	private final Class<? extends Annotation> _annotationClass;
	private final T _nullAnnotation;

}