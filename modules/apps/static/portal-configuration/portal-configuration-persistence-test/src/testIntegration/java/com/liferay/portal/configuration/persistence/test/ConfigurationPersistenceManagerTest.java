/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.configuration.persistence.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.osgi.util.ServiceTrackerFactory;
import com.liferay.portal.configuration.persistence.ConfigurationPersistenceManager;
import com.liferay.portal.kernel.util.StringPool;

import java.io.IOException;

import java.util.Dictionary;
import java.util.Hashtable;

import org.apache.felix.cm.PersistenceManager;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.util.tracker.ServiceTracker;

/**
 * @author Raymond Augé
 */
@RunWith(Arquillian.class)
public class ConfigurationPersistenceManagerTest {

	@Before
	public void setUp() throws Exception {
		Bundle bundle = FrameworkUtil.getBundle(
			ConfigurationPersistenceManagerTest.class);

		_configurationAdminServiceTracker = ServiceTrackerFactory.open(
			bundle, ConfigurationAdmin.class);

		_configurationAdmin = _configurationAdminServiceTracker.waitForService(
			5000);

		_persistenceManagerServiceTracker = ServiceTrackerFactory.open(
			bundle, PersistenceManager.class);

		_persistenceManager = _persistenceManagerServiceTracker.waitForService(
			5000);
	}

	@After
	public void tearDown() throws Exception {
		_configurationAdminServiceTracker.close();

		_persistenceManagerServiceTracker.close();
	}

	@Test
	public void testConfigurationPersistenceManager() throws Exception {
		Assert.assertEquals(
			ConfigurationPersistenceManager.class,
			_persistenceManager.getClass());
	}

	@Test
	public void testCreateFactoryConfiguration() throws Exception {
		Configuration configuration =
			_configurationAdmin.createFactoryConfiguration(
				"test.pid", StringPool.QUESTION);

		assertConfiguration(configuration);
	}

	@Test
	public void testExists() throws Exception {
		Configuration configuration = _configurationAdmin.getConfiguration(
			"test.pid", StringPool.QUESTION);

		Assert.assertTrue(_persistenceManager.exists("test.pid"));

		configuration.delete();

		Assert.assertFalse(_persistenceManager.exists("test.pid"));
	}

	@Test
	public void testGetConfiguration() throws Exception {
		Configuration configuration = _configurationAdmin.getConfiguration(
			"test.pid");

		assertConfiguration(configuration);
	}

	protected void assertConfiguration(Configuration configuration)
		throws IOException {

		String pid = configuration.getPid();

		Dictionary<String, Object> properties = new Hashtable<>();

		properties.put("foo", "bar");

		configuration.update(properties);

		Assert.assertTrue(_persistenceManager.exists(pid));

		properties = _persistenceManager.load(pid);

		Assert.assertEquals("bar", properties.get("foo"));

		properties.put("fee", "fum");

		configuration.update(properties);

		properties = _persistenceManager.load(pid);

		Assert.assertEquals("bar", properties.get("foo"));
		Assert.assertEquals("fum", properties.get("fee"));

		configuration.delete();

		Assert.assertFalse(_persistenceManager.exists(pid));
	}

	private ConfigurationAdmin _configurationAdmin;
	private ServiceTracker<ConfigurationAdmin, ConfigurationAdmin>
		_configurationAdminServiceTracker;
	private PersistenceManager _persistenceManager;
	private ServiceTracker<PersistenceManager, PersistenceManager>
		_persistenceManagerServiceTracker;

}