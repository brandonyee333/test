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

package com.liferay.pulpo.connector.de.contacts.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.service.test.ServiceTestUtil;
import com.liferay.pulpo.connector.de.contacts.demo.service.ContactsDemoService;
import com.liferay.registry.Filter;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;
import com.liferay.registry.ServiceTracker;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Cristina González
 */
@RunWith(Arquillian.class)
public class ContactsDemoServiceTest {

	@BeforeClass
	public static void setUpClass() {
		Registry registry = RegistryUtil.getRegistry();

		StringBundler sb = new StringBundler(3);

		sb.append("(&(objectClass=");
		sb.append(ContactsDemoService.class.getName());
		sb.append("))");

		Filter filter = registry.getFilter(sb.toString());

		_serviceTracker = registry.trackServices(filter);

		_serviceTracker.open();
	}

	@AfterClass
	public static void tearDownClass() {
		_serviceTracker.close();
	}

	@Before
	public void setUp() throws Exception {
		ServiceTestUtil.initPermissions();
	}

	@Test
	public void testCreateUser() throws IOException, PortalException {
		ContactsDemoService contactsDemoService = _serviceTracker.getService();

		User user = contactsDemoService.createUser(
			TestPropsValues.getCompanyId());

		Assert.assertNotNull(user.getEmailAddress());
		Assert.assertTrue(user.getPortraitId() > 0);
		Assert.assertNotNull(user.getScreenName());
	}

	private static ServiceTracker<ContactsDemoService, ContactsDemoService>
		_serviceTracker;

}