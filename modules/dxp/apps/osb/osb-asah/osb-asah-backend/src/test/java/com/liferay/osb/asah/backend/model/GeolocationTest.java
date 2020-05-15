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

package com.liferay.osb.asah.backend.model;

import com.liferay.osb.asah.backend.constants.DataConstants;
import com.liferay.osb.asah.backend.test.util.BaseBeanTestCase;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Inácio Nery
 */
public class GeolocationTest extends BaseBeanTestCase<Geolocation> {

	@Test
	public void testCountry() {
		Geolocation geolocation = Geolocation.country(DataConstants.ANY);

		Assert.assertEquals(Geolocation.any(), geolocation);
	}

	@Test
	public void testRegion1() {
		Geolocation geolocation = Geolocation.region(
			DataConstants.ANY, DataConstants.ANY);

		Assert.assertEquals(Geolocation.any(), geolocation);
	}

	@Test
	public void testRegion2() {
		Geolocation geolocation = Geolocation.region(null, null);

		Assert.assertEquals(Geolocation.any(), geolocation);
	}

	@Override
	protected Geolocation newInstance() {
		return Geolocation.any();
	}

}