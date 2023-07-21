/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.sanitizer;

import com.liferay.portal.kernel.sanitizer.Sanitizer;
import com.liferay.portal.kernel.sanitizer.SanitizerUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.sanitizer.bundle.sanitizerimpl.TestSanitizer;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.SyntheticBundleRule;
import com.liferay.portal.util.test.AtomicState;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import java.util.HashMap;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Peter Fellwock
 */
public class SanitizerUtilTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			new SyntheticBundleRule("bundle.sanitizerimpl"));

	@BeforeClass
	public static void setUpClass() {
		_atomicState = new AtomicState();
	}

	@AfterClass
	public static void tearDownClass() {
		_atomicState.close();
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testSanitize1() {
		_atomicState.reset();

		try {
			SanitizerUtil.sanitize(
				1, 1, 1, TestSanitizer.class.getName(), 1, "contentType",
				"bytes".getBytes());
		}
		catch (Exception e) {
			Assert.fail();
		}

		Assert.assertTrue(_atomicState.isSet());
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testSanitize2() {
		_atomicState.reset();

		ByteArrayOutputStream byteArrayOutputStream =
			new ByteArrayOutputStream();

		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
			byteArrayOutputStream.toByteArray());

		try {
			SanitizerUtil.sanitize(
				1, 1, 1, TestSanitizer.class.getName(), 1, "contentType",
				byteArrayInputStream, byteArrayOutputStream);
		}
		catch (Exception e) {
			Assert.fail();
		}

		Assert.assertTrue(_atomicState.isSet());
	}

	@Test
	public void testSanitize3() {
		try {
			String value = SanitizerUtil.sanitize(
				1, 1, 1, TestSanitizer.class.getName(), 1, "contentType", "s");

			Assert.assertEquals("1:1", value);
		}
		catch (Exception e) {
			Assert.fail();
		}
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testSanitize4() {
		_atomicState.reset();

		try {
			SanitizerUtil.sanitize(
				1, 1, 1, TestSanitizer.class.getName(), 1, "contentType",
				Sanitizer.MODE_ALL, "bytes".getBytes(),
				new HashMap<String, Object>());
		}
		catch (Exception e) {
			Assert.fail();
		}

		Assert.assertTrue(_atomicState.isSet());
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testSanitize5() {
		_atomicState.reset();

		ByteArrayOutputStream byteArrayOutputStream =
			new ByteArrayOutputStream();

		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
			byteArrayOutputStream.toByteArray());

		try {
			SanitizerUtil.sanitize(
				1, 1, 1, TestSanitizer.class.getName(), 1, "contentType",
				Sanitizer.MODE_ALL, byteArrayInputStream, byteArrayOutputStream,
				new HashMap<String, Object>());
		}
		catch (Exception e) {
			Assert.fail();
		}

		Assert.assertTrue(_atomicState.isSet());
	}

	@Test
	public void testSanitize6() {
		try {
			String value = SanitizerUtil.sanitize(
				1, 1, 1, TestSanitizer.class.getName(), 1, "contentType",
				Sanitizer.MODE_ALL, "s", new HashMap<String, Object>());

			Assert.assertEquals("1:1", value);
		}
		catch (Exception e) {
			Assert.fail();
		}
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testSanitize7() {
		_atomicState.reset();

		try {
			SanitizerUtil.sanitize(
				1, 1, 1, TestSanitizer.class.getName(), 1, "contentType",
				Sanitizer.MODE_ALL, "bytes".getBytes(),
				new HashMap<String, Object>());
		}
		catch (Exception e) {
			Assert.fail();
		}

		Assert.assertTrue(_atomicState.isSet());
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testSanitize8() {
		_atomicState.reset();

		ByteArrayOutputStream byteArrayOutputStream =
			new ByteArrayOutputStream();

		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
			byteArrayOutputStream.toByteArray());

		try {
			SanitizerUtil.sanitize(
				1, 1, 1, TestSanitizer.class.getName(), 1, "contentType",
				new String[] {
					Sanitizer.MODE_ALL, Sanitizer.MODE_BAD_WORDS,
					Sanitizer.MODE_XSS
				},
				byteArrayInputStream, byteArrayOutputStream,
				new HashMap<String, Object>());
		}
		catch (Exception e) {
			Assert.fail();
		}

		Assert.assertTrue(_atomicState.isSet());
	}

	@Test
	public void testSanitize9() {
		try {
			String value = SanitizerUtil.sanitize(
				1, 1, 1, TestSanitizer.class.getName(), 1, "contentType",
				new String[] {
					Sanitizer.MODE_ALL, Sanitizer.MODE_BAD_WORDS,
					Sanitizer.MODE_XSS
				},
				"s", new HashMap<String, Object>());

			Assert.assertEquals("1:1", value);
		}
		catch (Exception e) {
			Assert.fail();
		}
	}

	private static AtomicState _atomicState;

}