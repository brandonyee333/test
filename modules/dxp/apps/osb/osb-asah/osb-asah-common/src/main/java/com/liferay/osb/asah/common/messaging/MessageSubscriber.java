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

package com.liferay.osb.asah.common.messaging;

import com.liferay.osb.asah.common.function.UnsafeFunction;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Marcellus Tavares
 */
public interface MessageSubscriber {

	public List<String> pullMessages(int maxMessages);

	public default <T> List<T> pullMessages(
			int maxMessages,
			UnsafeFunction<String, T, Exception> modelMapperFunction)
		throws Exception {

		List<T> messages = new ArrayList<>();

		for (String messageString : pullMessages(maxMessages)) {
			messages.add(modelMapperFunction.apply(messageString));
		}

		return messages;
	}

	@Documented
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	public @interface Autowired {

		public Channel channel();

	}

}