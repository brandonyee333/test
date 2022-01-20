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

package com.liferay.osb.asah.common.messaging.impl;

import com.liferay.osb.asah.common.messaging.MessageBus;
import com.liferay.osb.asah.common.messaging.MessageStreamingSubscriber;

import java.lang.reflect.Field;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

/**
 * @author Robson Pastor
 */
@Component
public class MessageStreamingSubscriberAutowiredAnnotationBeanPostProcessor
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
			new MessageStreamingSubscriberAutowiredFieldCallback(
				bean, _messageBus));

		return bean;
	}

	@Autowired
	private MessageBus _messageBus;

	private static class MessageStreamingSubscriberAutowiredFieldCallback
		implements ReflectionUtils.FieldCallback {

		public MessageStreamingSubscriberAutowiredFieldCallback(
			Object bean, MessageBus messageBus) {

			_bean = bean;
			_messageBus = messageBus;
		}

		@Override
		public void doWith(Field field)
			throws IllegalAccessException, IllegalArgumentException {

			if (!field.isAnnotationPresent(
					MessageStreamingSubscriber.Autowired.class)) {

				return;
			}

			Class<?> fieldTypeClass = field.getType();

			if (!fieldTypeClass.equals(MessageStreamingSubscriber.class)) {
				throw new IllegalArgumentException(
					"Unable to autowire MessageStreamingSubscriber due to " +
						"inconsistent bean type");
			}

			MessageStreamingSubscriber.Autowired
				messageStreamingSubscriberAutowired = field.getAnnotation(
					MessageStreamingSubscriber.Autowired.class);

			Class<?> beanClass = _bean.getClass();

			MessageStreamingSubscriber messageStreamingSubscriber =
				_messageBus.registerMessageStreamingSubscriber(
					messageStreamingSubscriberAutowired.channel(),
					beanClass.getName());

			ReflectionUtils.makeAccessible(field);

			field.set(_bean, messageStreamingSubscriber);
		}

		private final Object _bean;
		private final MessageBus _messageBus;

	}

}