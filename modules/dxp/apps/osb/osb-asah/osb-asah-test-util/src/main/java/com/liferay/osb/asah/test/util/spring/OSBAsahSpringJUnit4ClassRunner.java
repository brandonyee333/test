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

package com.liferay.osb.asah.test.util.spring;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestContextManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Marcellus Tavares
 * @author André Miranda
 */
public class OSBAsahSpringJUnit4ClassRunner extends SpringJUnit4ClassRunner {

	public OSBAsahSpringJUnit4ClassRunner(Class<?> clazz) throws Exception {
		super(clazz);

		System.setProperty("spring.profiles.active", "test");

		_registerOSBAsahTestExecutionListener();
	}

	private OSBAsahTestExecutionListener _createOSBAsahTestExecutionListener()
		throws Exception {

		OSBAsahTestExecutionListener osbAsahTestExecutionListener =
			new OSBAsahTestExecutionListener();

		_setAutowireCapability(osbAsahTestExecutionListener);

		return osbAsahTestExecutionListener;
	}

	private void _registerOSBAsahTestExecutionListener() throws Exception {
		TestContextManager testContextManager = getTestContextManager();

		testContextManager.registerTestExecutionListeners(
			_createOSBAsahTestExecutionListener());
	}

	private void _setAutowireCapability(Object object) {
		TestContextManager testContextManager = getTestContextManager();

		TestContext testContext = testContextManager.getTestContext();

		ApplicationContext applicationContext =
			testContext.getApplicationContext();

		AutowireCapableBeanFactory autowireCapableBeanFactory =
			applicationContext.getAutowireCapableBeanFactory();

		autowireCapableBeanFactory.autowireBean(object);
	}

}