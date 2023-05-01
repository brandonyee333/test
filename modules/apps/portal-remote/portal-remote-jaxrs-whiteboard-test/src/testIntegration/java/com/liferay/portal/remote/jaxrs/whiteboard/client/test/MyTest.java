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

package com.liferay.portal.remote.jaxrs.whiteboard.client.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.util.HashMapDictionaryBuilder;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.net.URL;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Dictionary;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseBroadcaster;
import javax.ws.rs.sse.SseEventSink;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceRegistration;

/**
 * @author Kevin Valencia
 */
@RunWith(Arquillian.class)
public class MyTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@BeforeClass
	public static void setUpClass() {
		Bundle bundle = FrameworkUtil.getBundle(MyTest.class);

		BundleContext bundleContext = bundle.getBundleContext();

		Dictionary<String, Object> properties =
			HashMapDictionaryBuilder.<String, Object>put(
				"auth.verifier.guest.allowed", true
			).put(
				"liferay.access.control.disable", true
			).put(
				"osgi.jaxrs.application.base", "/rest-test/greeter1"
			).build();

		_serviceRegistrations.add(
			bundleContext.registerService(
				Application.class, new Greeter(), properties));
	}

	@AfterClass
	public static void tearDownClass() {
		for (ServiceRegistration<?> serviceRegistration :
				_serviceRegistrations) {

			serviceRegistration.unregister();
		}
	}

	@Test
	public void testIsRegistered() throws Exception {
		URL url = new URL(
			"http://localhost:8080/o/rest-test/greeter1/register");

		Assert.assertEquals("Hello.", StringUtil.read(url.openStream()));
	}

	@Test(expected = Exception.class)
	public void testServiceListIsUnavailable() throws Exception {
		URL url = new URL("http://localhost:8080/o/soap-test/services");

		StringUtil.read(url.openStream());
	}

	public static class Greeter extends Application {

		@Override
		public Set<Object> getSingletons() {
			return Collections.singleton(this);
		}

		@GET
		@Path("/register")
		@Produces({"text/event-stream"})
		public String register(@Context SseEventSink eventSink) {
			
				this.getBroadcaster(
				).register(
					eventSink
				);

				return "Hello.";
			
		
		}

		@Context
		public void setSse(Sse sse) {
			if (sse != null) {
				MyTest.sse = sse;
			}
		}

		private SseBroadcaster getBroadcaster() {
			SseBroadcaster sseBroadcaster = MyTest.BROADCASTER.get();

			if (sseBroadcaster == null) {
				MyTest.BROADCASTER.compareAndSet(
					null, MyTest.sse.newBroadcaster());
			}

			return MyTest.BROADCASTER.get();
		}

	}

	private static final AtomicReference<SseBroadcaster> BROADCASTER;

	private static final List<ServiceRegistration<?>> _serviceRegistrations =
		new ArrayList<>();
	private static Sse sse;

	static {
		BROADCASTER = new AtomicReference<>(null);
	}

}