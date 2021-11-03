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
import org.springframework.test.context.support.AbstractTestExecutionListener;

/**
 * @author Alejo Ceballos
 */
public abstract class BaseOSBAsahTestExecutionListener
	extends AbstractTestExecutionListener {

	@Override
	public void beforeTestClass(TestContext testContext) throws Exception {
		ApplicationContext applicationContext =
			testContext.getApplicationContext();

		AutowireCapableBeanFactory autowireCapableBeanFactory =
			applicationContext.getAutowireCapableBeanFactory();

		autowireCapableBeanFactory.autowireBean(this);
	}

}