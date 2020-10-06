/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.asah.common.elasticsearch.impl;

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;

import java.lang.reflect.Field;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

/**
 * @author Shinn Lok
 */
@Component
public class ElasticsearchInvokerAutowiredAnnotationBeanPostProcessor
	implements BeanPostProcessor {

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName)
		throws BeansException {

		return bean;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)
		throws BeansException {

		ReflectionUtils.doWithFields(
			bean.getClass(),
			new MessageSubscriberAutowiredFieldCallback(
				bean, _elasticsearchInvokerManager));

		return bean;
	}

	@Autowired
	private ElasticsearchInvokerManager _elasticsearchInvokerManager;

	private static class MessageSubscriberAutowiredFieldCallback
		implements ReflectionUtils.FieldCallback {

		public MessageSubscriberAutowiredFieldCallback(
			Object bean,
			ElasticsearchInvokerManager elasticsearchInvokerManager) {

			_bean = bean;
			_elasticsearchInvokerManager = elasticsearchInvokerManager;
		}

		@Override
		public void doWith(Field field)
			throws IllegalAccessException, IllegalArgumentException {

			if (!field.isAnnotationPresent(
					ElasticsearchInvoker.Autowired.class)) {

				return;
			}

			Class<?> fieldTypeClass = field.getType();

			if (!fieldTypeClass.equals(ElasticsearchInvoker.class)) {
				throw new IllegalArgumentException(
					"Unable to autowire ElasticsearchInvoker due " +
						"inconsistent bean type");
			}

			ElasticsearchInvoker.Autowired elasticsearchInvokerAutowired =
				field.getAnnotation(ElasticsearchInvoker.Autowired.class);

			ReflectionUtils.makeAccessible(field);

			field.set(
				_bean,
				_elasticsearchInvokerManager.forWeDeployDataService(
					elasticsearchInvokerAutowired.value(),
					elasticsearchInvokerAutowired.cacheable()));
		}

		private final Object _bean;
		private final ElasticsearchInvokerManager _elasticsearchInvokerManager;

	}

}