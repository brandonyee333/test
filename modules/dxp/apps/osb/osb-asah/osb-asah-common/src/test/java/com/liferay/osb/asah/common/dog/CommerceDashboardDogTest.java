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

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.test.util.ReflectionTestUtils;

/**
 * @author Riccardo Ferrari
 */
public class CommerceDashboardDogTest {

	@Test
	public void testGetPercentageVariation() {
		Assertions.assertEquals(
			0.0,
			ReflectionTestUtils.invokeMethod(
				_commerceDashboardDog, "_getPercentageVariation",
				new BigDecimal("737"), BigDecimal.ZERO));
		Assertions.assertEquals(
			659.8,
			ReflectionTestUtils.invokeMethod(
				_commerceDashboardDog, "_getPercentageVariation",
				new BigDecimal("737"), new BigDecimal("97")));
	}

	private static final CommerceDashboardDog _commerceDashboardDog =
		new CommerceDashboardDog();

}