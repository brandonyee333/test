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

package com.liferay.osb.asah.backend.ext.seo.rest.controller.api.data.source.v1.test;

import com.liferay.osb.asah.backend.ext.seo.model.TrafficSource;
import com.liferay.osb.asah.backend.ext.seo.rest.controller.api.data.source.v1.RootRestController;
import com.liferay.osb.asah.backend.ext.seo.spring.OSBAsahBackendExtSEOSpringBootApplication;
import com.liferay.osb.asah.common.spring.http.Http;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

/**
 * @author David Arques
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(
	classes = OSBAsahBackendExtSEOSpringBootApplication.class,
	webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class RootRestControllerTest {

	@Before
	public void setUp() {
		Mockito.when(
			_environment.getProperty("SEMRUSH_API_KEY")
		).thenReturn(
			RandomTestUtil.randomString()
		);
	}

	@Test
	public void testGetTrafficSources() {
		Mockito.doAnswer(
			invocationOnMock -> ResponseEntity.ok(
				ResourceUtil.readResourceToString(
					"dependencies/semrush_url_organic.csv", this))
		).when(
			_http
		).exchangeResponseEntity(
			Mockito.contains("url_organic"), Mockito.anyString(),
			Mockito.any(HttpMethod.class), Mockito.anyObject()
		);

		Mockito.doAnswer(
			invocationOnMock -> ResponseEntity.ok(
				ResourceUtil.readResourceToString(
					"dependencies/semrush_url_adwords.csv", this))
		).when(
			_http
		).exchangeResponseEntity(
			Mockito.contains("url_adwords"), Mockito.anyString(),
			Mockito.any(HttpMethod.class), Mockito.anyObject()
		);

		List<TrafficSource> trafficSources =
			_rootRestController.getTrafficSources(RandomTestUtil.randomURL());

		Assert.assertEquals(
			trafficSources.toString(), 2, trafficSources.size());

		Assert.assertEquals(
			new TrafficSource("organic", 3849, 94.25D), trafficSources.get(0));
		Assert.assertEquals(
			new TrafficSource("paid", 235, 5.75D), trafficSources.get(1));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetTrafficSourcesWithEmptyURL() {
		_rootRestController.getTrafficSources("");
	}

	@Test
	public void testGetTrafficSourcesWithNothingFound() {
		Mockito.doAnswer(
			invocationOnMock -> ResponseEntity.ok("ERROR 50 :: NOTHING FOUND")
		).when(
			_http
		).exchangeResponseEntity(
			Mockito.anyString(), Mockito.anyString(),
			Mockito.any(HttpMethod.class), Mockito.anyObject()
		);

		List<TrafficSource> trafficSources =
			_rootRestController.getTrafficSources(RandomTestUtil.randomURL());

		Assert.assertEquals(
			trafficSources.toString(), 2, trafficSources.size());

		Assert.assertEquals(
			new TrafficSource("organic", 0, 0D), trafficSources.get(0));
		Assert.assertEquals(
			new TrafficSource("paid", 0, 0D), trafficSources.get(1));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetTrafficSourcesWithNullURL() {
		_rootRestController.getTrafficSources(null);
	}

	@Test(expected = HttpClientErrorException.class)
	public void testGetTrafficSourcesWithSEMrushAPIError() {
		Mockito.doAnswer(
			invocationOnMock -> ResponseEntity.status(
				HttpStatus.INTERNAL_SERVER_ERROR
			).build()
		).when(
			_http
		).exchangeResponseEntity(
			Mockito.anyString(), Mockito.anyString(),
			Mockito.any(HttpMethod.class), Mockito.anyObject()
		);

		_rootRestController.getTrafficSources(RandomTestUtil.randomURL());
	}

	@Test(expected = HttpClientErrorException.class)
	public void testGetTrafficSourcesWithURLAdwordsSEMrushAPIError() {
		Mockito.doAnswer(
			invocationOnMock -> ResponseEntity.ok(
				ResourceUtil.readResourceToString(
					"dependencies/semrush_url_organic.csv", this))
		).when(
			_http
		).exchangeResponseEntity(
			Mockito.contains("url_organic"), Mockito.anyString(),
			Mockito.any(HttpMethod.class), Mockito.anyObject()
		);

		Mockito.doAnswer(
			invocationOnMock -> ResponseEntity.status(
				HttpStatus.INTERNAL_SERVER_ERROR
			).build()
		).when(
			_http
		).exchangeResponseEntity(
			Mockito.contains("url_adwords"), Mockito.anyString(),
			Mockito.any(HttpMethod.class), Mockito.anyObject()
		);

		_rootRestController.getTrafficSources(RandomTestUtil.randomURL());
	}

	@Test(expected = HttpClientErrorException.class)
	public void testGetTrafficSourcesWithURLOrganicSEMrushAPIError() {
		Mockito.doAnswer(
			invocationOnMock -> ResponseEntity.status(
				HttpStatus.INTERNAL_SERVER_ERROR
			).build()
		).when(
			_http
		).exchangeResponseEntity(
			Mockito.contains("url_organic"), Mockito.anyString(),
			Mockito.any(HttpMethod.class), Mockito.anyObject()
		);

		Mockito.doAnswer(
			invocationOnMock -> ResponseEntity.ok(
				ResourceUtil.readResourceToString(
					"dependencies/semrush_url_adwords.csv", this))
		).when(
			_http
		).exchangeResponseEntity(
			Mockito.contains("url_adwords"), Mockito.anyString(),
			Mockito.any(HttpMethod.class), Mockito.anyObject()
		);

		_rootRestController.getTrafficSources(RandomTestUtil.randomURL());
	}

	@Mock
	private Environment _environment;

	@Mock
	private Http _http;

	@Autowired
	@InjectMocks
	private RootRestController _rootRestController;

}