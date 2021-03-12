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

package com.liferay.portal.util;

import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Subscription;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.SubscriptionLocalService;
import com.liferay.portal.kernel.service.SubscriptionLocalServiceUtil;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.util.PropsTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.SubscriptionSender;
import com.liferay.portal.kernel.uuid.PortalUUID;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Mika Koivisto
 */
@RunWith(PowerMockRunner.class)
public class SubscriptionSenderTest extends PowerMockito {

	@Before
	public void setUp() throws Exception {
		CompanyLocalService companyLocalService = getMockService(
			CompanyLocalServiceUtil.class, CompanyLocalService.class);

		Company company = mock(Company.class);

		when(
			companyLocalService.getCompany(Mockito.anyLong())
		).thenReturn(
			company
		);

		when(
			company.getPortalURL(Mockito.eq(0L))
		).thenReturn(
			"http://www.portal.com"
		);

		when(
			company.getPortalURL(Mockito.eq(100L))
		).thenReturn(
			"http://www.virtual.com"
		);

		GroupLocalService groupLocalService = getMockService(
			GroupLocalServiceUtil.class, GroupLocalService.class);

		Group group = mock(Group.class);

		when(
			groupLocalService.getGroup(Mockito.eq(100L))
		).thenReturn(
			group
		);

		when(
			group.isLayout()
		).thenReturn(
			false
		);

		PortalUUIDUtil portalUUIDUtil = new PortalUUIDUtil();

		PortalUUID portalUUID = mock(PortalUUID.class);

		portalUUIDUtil.setPortalUUID(portalUUID);

		PortalUtil portalUtil = new PortalUtil();

		Portal portal = mock(Portal.class);

		portalUtil.setPortal(portal);

		PropsTestUtil.setProps(Collections.emptyMap());
	}

	@After
	public void tearDown() {
		for (Class<?> serviceUtilClass : _serviceUtilClasses) {
			ReflectionTestUtil.setFieldValue(
				serviceUtilClass, "_service", null);
		}
	}

	@Test
	public void testGetPortalURLWithGroupId() throws Exception {
		SubscriptionSender subscriptionSender = new SubscriptionSender();

		subscriptionSender.setGroupId(100);
		subscriptionSender.setMailId("test-mail-id");

		subscriptionSender.initialize();

		String portalURL = String.valueOf(
			subscriptionSender.getContextAttribute("[$PORTAL_URL$]"));

		Assert.assertEquals("http://www.virtual.com", portalURL);
	}

	@Test
	public void testGetPortalURLWithoutGroupId() throws Exception {
		SubscriptionSender subscriptionSender = new SubscriptionSender();

		subscriptionSender.setMailId("test-mail-id");

		subscriptionSender.initialize();

		String portalURL = String.valueOf(
			subscriptionSender.getContextAttribute("[$PORTAL_URL$]"));

		Assert.assertEquals("http://www.portal.com", portalURL);
	}

	@Test
	public void testGetPortalURLWithServiceContext() throws Exception {
		SubscriptionSender subscriptionSender = new SubscriptionSender();

		subscriptionSender.setMailId("test-mail-id");

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setScopeGroupId(100L);

		subscriptionSender.setServiceContext(serviceContext);

		subscriptionSender.initialize();

		String portalURL = String.valueOf(
			subscriptionSender.getContextAttribute("[$PORTAL_URL$]"));

		Assert.assertEquals("http://www.virtual.com", portalURL);
	}

	@Test
	public void testHasSubscriptionsReturnsFalseWhenNoSubscribers() {
		SubscriptionSender subscriptionSender = new SubscriptionSender();

		Assert.assertFalse(subscriptionSender.hasSubscribers());
	}

	@Test
	public void testHasSubscriptionsReturnsFalseWhenNoSubscriptionsInDB() {
		SubscriptionLocalService subscriptionLocalService = Mockito.mock(
			SubscriptionLocalService.class);

		ReflectionTestUtil.setFieldValue(
			SubscriptionLocalServiceUtil.class, "_service",
			subscriptionLocalService);

		Mockito.when(
			subscriptionLocalService.getSubscriptions(
				Mockito.anyLong(), Mockito.anyString(), Mockito.anyLong())
		).thenReturn(
			Collections.emptyList()
		);

		SubscriptionSender subscriptionSender = new SubscriptionSender();

		subscriptionSender.addPersistedSubscribers(
			Group.class.getName(), RandomTestUtil.randomInt());

		Assert.assertFalse(subscriptionSender.hasSubscribers());
	}

	@Test
	public void testHasSubscriptionsReturnsTrueWhenSubscriptionsInDB() {
		SubscriptionLocalService subscriptionLocalService = Mockito.mock(
			SubscriptionLocalService.class);

		ReflectionTestUtil.setFieldValue(
			SubscriptionLocalServiceUtil.class, "_service",
			subscriptionLocalService);

		Mockito.when(
			subscriptionLocalService.getSubscriptions(
				Mockito.anyLong(), Mockito.anyString(), Mockito.anyLong())
		).thenReturn(
			Collections.singletonList(Mockito.mock(Subscription.class))
		);

		SubscriptionSender subscriptionSender = new SubscriptionSender();

		subscriptionSender.addPersistedSubscribers(
			Group.class.getName(), RandomTestUtil.randomInt());

		Assert.assertTrue(subscriptionSender.hasSubscribers());
	}

	protected <T> T getMockService(
		Class<?> serviceUtilClass, Class<T> serviceClass) {

		_serviceUtilClasses.add(serviceUtilClass);

		T serviceMock = mock(serviceClass);

		ReflectionTestUtil.setFieldValue(
			serviceUtilClass, "_service", serviceMock);

		return serviceMock;
	}

	private final List<Class<?>> _serviceUtilClasses = new ArrayList<>();

}