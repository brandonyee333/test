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

package com.liferay.osb.asah.common.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Rachael Koestartyo
 */
public class ValidatorTest {

	@Test
	public void testIsBoolean() {
		Assert.assertFalse(Validator.isBoolean("maybe"));
		Assert.assertFalse(Validator.isBoolean("no"));
		Assert.assertFalse(Validator.isBoolean("yes"));
		Assert.assertTrue(Validator.isBoolean("FALSE"));
		Assert.assertTrue(Validator.isBoolean("False"));
		Assert.assertTrue(Validator.isBoolean("false"));
		Assert.assertTrue(Validator.isBoolean("TRUE"));
		Assert.assertTrue(Validator.isBoolean("True"));
		Assert.assertTrue(Validator.isBoolean("true"));
	}

	@Test
	public void testIsDate() {
		Assert.assertFalse(Validator.isDate("2020-12-12T09:20:00.Z"));
		Assert.assertFalse(Validator.isDate("2020-33-39T10:00:00Z"));
		Assert.assertFalse(Validator.isDate("2020.12.12T09:20:00.000Z"));
		Assert.assertFalse(Validator.isDate("23429348239483"));
		Assert.assertFalse(Validator.isDate("01-01-2020"));
		Assert.assertFalse(Validator.isDate("12-31-2020"));
		Assert.assertFalse(Validator.isDate("12-31-2020T09:30:00Z"));
		Assert.assertFalse(Validator.isDate("12/31/2020"));
		Assert.assertFalse(Validator.isDate("12/31/2020T09:30:00Z"));
		Assert.assertFalse(Validator.isDate("13-12-2020"));
		Assert.assertFalse(Validator.isDate("2020 12 31 09:30:10+0130"));
		Assert.assertFalse(Validator.isDate("2020 12 31T09:30:00.100Z"));
		Assert.assertFalse(Validator.isDate("2020-01-01"));
		Assert.assertFalse(Validator.isDate("20200101"));
		Assert.assertFalse(Validator.isDate("20201231T09:30:00.100Z"));
		Assert.assertFalse(Validator.isDate("31-12-2020T09:30:00Z"));
		Assert.assertTrue(Validator.isDate("2020-12-31T09:30:00.000Z"));
		Assert.assertTrue(Validator.isDate("2020-12-31T09:30:00.100Z"));
	}

	@Test
	public void testIsNumber() {
		Assert.assertFalse(Validator.isNumber("123a"));
		Assert.assertFalse(Validator.isNumber("abc"));
		Assert.assertTrue(Validator.isNumber("00000000000"));
		Assert.assertTrue(Validator.isNumber("30293094040"));
		Assert.assertTrue(Validator.isNumber("30293094040"));
		Assert.assertTrue(Validator.isNumber("-1234"));
		Assert.assertTrue(Validator.isNumber("1234.95"));
	}

}