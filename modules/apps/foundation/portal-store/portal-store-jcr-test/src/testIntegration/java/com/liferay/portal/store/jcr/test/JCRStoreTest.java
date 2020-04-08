/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.store.jcr.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.document.library.kernel.store.Store;
import com.liferay.osgi.util.ServiceTrackerFactory;
import com.liferay.portal.configuration.test.util.ConfigurationTestUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portlet.documentlibrary.store.test.BaseStoreTestCase;

import java.util.Dictionary;
import java.util.Hashtable;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.runner.RunWith;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.util.tracker.ServiceTracker;

/**
 * @author Preston Crary
 * @author Manuel de la Peña
 */
@RunWith(Arquillian.class)
public class JCRStoreTest extends BaseStoreTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@BeforeClass
	public static void setUpClass() throws Exception {
		Bundle bundle = FrameworkUtil.getBundle(JCRStoreTest.class);

		BundleContext bundleContext = bundle.getBundleContext();

		_jcrConfiguration = _configurationAdmin.getConfiguration(
			"com.liferay.portal.store.jcr.configuration.JCRStoreConfiguration",
			null);

		Dictionary<String, Object> properties = new Hashtable<>();

		properties.put("initializeOnStartup", Boolean.TRUE);
		properties.put("jackrabbitConfigFilePath", "repository.xml");
		properties.put("jackrabbitCredentialsPassword", "none");
		properties.put("jackrabbitCredentialsUsername", "none");
		properties.put("jackrabbitRepositoryHome", "home");
		properties.put("jackrabbitRepositoryRoot", "data/jackrabbit");
		properties.put("moveVersionLabels", Boolean.FALSE);
		properties.put("nodeDocumentlibrary", "documentlibrary");
		properties.put("workspaceName", "liferay");
		properties.put("wrapSession", Boolean.TRUE);

		ConfigurationTestUtil.saveConfiguration(_jcrConfiguration, properties);

		ServiceTracker<?, ?> serviceTracker = ServiceTrackerFactory.open(
			bundleContext,
			"(&(objectClass=" + Store.class.getName() +
				")(store.type=com.liferay.portal.store.jcr.JCRStore))");

		Object jcrStore = serviceTracker.waitForService(10000);

		serviceTracker.close();

		if (jcrStore == null) {
			throw new IllegalStateException(
				"JCR store was not registered within 10 seconds");
		}
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
		if (_jcrConfiguration != null) {
			ConfigurationTestUtil.deleteConfiguration(_jcrConfiguration);
		}
	}

	@Override
	protected String getStoreType() {
		return "com.liferay.portal.store.jcr.JCRStore";
	}

	@Inject
	private static ConfigurationAdmin _configurationAdmin;

	private static Configuration _jcrConfiguration;

}