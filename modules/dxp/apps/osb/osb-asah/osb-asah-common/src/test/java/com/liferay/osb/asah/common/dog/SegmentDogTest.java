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

package com.liferay.osb.asah.common.dog;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Marcos Martins
 */
public class SegmentDogTest {

	@Test
	public void testProcessFilter() {
		Assert.assertEquals(
			"(accounts.filter(filter='(organization/rating/value eq '4.6')'))",
			_segmentDog.processFilter(
				"(accounts.filter(filter='(organization/rating/value eq " +
					"'4.6')'))"));

		Assert.assertEquals(
			"demographics/additionalName/value eq 'Miles'",
			_segmentDog.processFilter(
				"demographics/additionalName/value eq 'Miles'"));

		Assert.assertEquals(
			"demographics/age/value ge " + Integer.MAX_VALUE,
			_segmentDog.processFilter(
				"demographics/age/value ge 1.2345678901234568e+21"));

		Assert.assertEquals(
			"demographics/age/value ge " + Integer.MAX_VALUE,
			_segmentDog.processFilter(
				"demographics/age/value ge " + Integer.MAX_VALUE));

		Assert.assertEquals(
			"organizations.filter(filter='(dateModified gt 1580256740750)')",
			_segmentDog.processFilter(
				"organizations.filter(filter='(dateModified gt " +
					"1580256740750)')"));
	}

	private final SegmentDog _segmentDog = new SegmentDog();

}