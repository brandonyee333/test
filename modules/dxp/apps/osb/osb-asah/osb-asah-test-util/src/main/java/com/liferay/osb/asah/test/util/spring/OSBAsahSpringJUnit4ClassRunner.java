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

import com.liferay.osb.asah.common.constants.ServiceConstants;

import java.io.IOException;

import java.net.InetSocketAddress;
import java.net.Socket;

import java.util.TimeZone;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.jooq.JooqAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
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

		if (!_isElasticsearchUp() || !_isPostgreSQLUp()) {
			throw new IllegalStateException(
				"Integration test infrastructure is not up. Please run " +
					"\"docker-compose -f docker-compose.integration-test.yml " +
						"up -d\" from the root project directory.");
		}

		System.setProperty(
			"spring.autoconfigure.exclude",
			String.join(
				",", JooqAutoConfiguration.class.getName(),
				ManagementWebSecurityAutoConfiguration.class.getName(),
				SecurityAutoConfiguration.class.getName()));
		System.setProperty(
			"spring.main.allow-bean-definition-overriding", "true");
		System.setProperty("spring.profiles.active", "test");

		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

		_registerOSBAsahTestExecutionListener();
	}

	private boolean _isElasticsearchUp() {
		return _pingHost(
			ServiceConstants.LCP_ENGINE_ELASTICSEARCH_SERVER_IP, 9200, 3000);
	}

	private boolean _isPostgreSQLUp() {
		return _pingHost(ServiceConstants.POSTGRESQL_SERVER_IP, 5432, 3000);
	}

	private boolean _pingHost(String hostname, int port, int timeout) {
		try (Socket socket = new Socket()) {
			socket.connect(new InetSocketAddress(hostname, port), timeout);

			return true;
		}
		catch (IOException ioException) {
			_log.error(ioException, ioException);

			return false;
		}
	}

	private void _registerOSBAsahTestExecutionListener() {
		TestContextManager testContextManager = getTestContextManager();

		TestContext testContext = testContextManager.getTestContext();

		ApplicationContext applicationContext =
			testContext.getApplicationContext();

		AutowireCapableBeanFactory autowireCapableBeanFactory =
			applicationContext.getAutowireCapableBeanFactory();

		testContextManager.registerTestExecutionListeners(
			autowireCapableBeanFactory.createBean(
				OSBAsahElasticsearchTestExecutionListener.class),
			autowireCapableBeanFactory.createBean(
				OSBAsahSQLTestExecutionListener.class));
	}

	private static final Log _log = LogFactory.getLog(
		OSBAsahSpringJUnit4ClassRunner.class);

}