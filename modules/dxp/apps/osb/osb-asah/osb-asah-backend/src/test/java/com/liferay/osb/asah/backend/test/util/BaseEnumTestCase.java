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

package com.liferay.osb.asah.backend.test.util;

import java.lang.reflect.Method;

import org.junit.Test;

/**
 * @author Inácio Nery
 */
public abstract class BaseEnumTestCase<T> {

	@Test
	public void testValueOf() throws Exception {
		Class<? extends Enum<?>> clazz = getClazz();

		Method valuesMethod = clazz.getMethod("values");

		for (Object o : (Object[])valuesMethod.invoke(null)) {
			Method valueOfMethod = clazz.getMethod("valueOf", String.class);

			valueOfMethod.invoke(null, o.toString());
		}
	}

	protected abstract Class<? extends Enum<?>> getClazz();

}