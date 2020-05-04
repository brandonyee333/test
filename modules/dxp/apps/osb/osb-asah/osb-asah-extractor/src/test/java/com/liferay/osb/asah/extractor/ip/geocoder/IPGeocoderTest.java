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

package com.liferay.osb.asah.extractor.ip.geocoder;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Inácio Nery
 */
@RunWith(SpringRunner.class)
public class IPGeocoderTest {

	@Test
	public void testGetCity() {
		IPInfo ipInfo = _ipGeocoder.getIPInfo(_IP);

		Assert.assertEquals("Los Angeles", ipInfo.getCity());
	}

	@Test
	public void testGetCountry() {
		IPInfo ipInfo = _ipGeocoder.getIPInfo(_IP);

		Assert.assertEquals("United States", ipInfo.getCountry());
	}

	@Test
	public void testGetRegion() {
		IPInfo ipInfo = _ipGeocoder.getIPInfo(_IP);

		Assert.assertEquals("California", ipInfo.getRegion());
	}

	@Configuration
	@Import(IPGeocoder.class)
	public static class TestConfiguration {
	}

	@SuppressWarnings("PMD.AvoidUsingHardCodedIP")
	private static final String _IP = "38.75.15.30";

	@Autowired
	private IPGeocoder _ipGeocoder;

}