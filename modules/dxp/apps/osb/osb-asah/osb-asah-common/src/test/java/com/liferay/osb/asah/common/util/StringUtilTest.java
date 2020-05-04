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

import com.liferay.osb.asah.common.json.JSONUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.Assert;
import org.junit.Test;

import org.skyscreamer.jsonassert.JSONAssert;

/**
 * @author Vishal Reddy
 */
public class StringUtilTest {

	@Test
	public void testIsQuoted() {
		Assert.assertFalse(StringUtil.isQuoted("'Hello World"));
		Assert.assertTrue(StringUtil.isQuoted("'Hello World'"));
		Assert.assertTrue(StringUtil.isQuoted("\"Hello World\""));
		Assert.assertFalse(StringUtil.isQuoted("\"Hello World"));
		Assert.assertFalse(StringUtil.isQuoted("Hello World"));

		// String with single character

		Assert.assertFalse(StringUtil.isQuoted("'"));
		Assert.assertFalse(StringUtil.isQuoted("\""));

		// String with two characters

		Assert.assertTrue(StringUtil.isQuoted("''"));
		Assert.assertTrue(StringUtil.isQuoted("\"\""));

		// Blank string

		Assert.assertFalse(StringUtil.isQuoted(""));
	}

	@Test
	public void testToObject() {
		Assert.assertEquals(
			"'Hello World'", StringUtil.toObject("'''Hello World'''"));
		Assert.assertEquals(0.5, StringUtil.toObject("0.5"));
		Assert.assertEquals(5L, StringUtil.toObject("5"));
		Assert.assertEquals(false, StringUtil.toObject("FALSE"));
		Assert.assertNull(StringUtil.toObject("NULL"));
		Assert.assertEquals(true, StringUtil.toObject("TRUE"));
		Assert.assertEquals(false, StringUtil.toObject("false"));
		Assert.assertNull(StringUtil.toObject("null"));
		Assert.assertEquals(true, StringUtil.toObject("true"));

		JSONAssert.assertEquals(
			JSONUtil.put(JSONUtil.put("foo", "bar")),
			(JSONArray)StringUtil.toObject("[{\"foo\": \"bar\"}]"), true);
		JSONAssert.assertEquals(
			JSONUtil.put("foo", "bar"),
			(JSONObject)StringUtil.toObject("{\"foo\": \"bar\"}"), true);
	}

	@Test
	public void testUnquote() {
		Assert.assertEquals("", StringUtil.unquote(""));
		Assert.assertEquals("Hello World", StringUtil.unquote("'Hello World'"));
		Assert.assertEquals("'Hello World", StringUtil.unquote("'Hello World"));
		Assert.assertEquals(
			"Hello World", StringUtil.unquote("\"Hello World\""));
		Assert.assertEquals("Hello World", StringUtil.unquote("Hello World"));
	}

	@Test
	public void testUnquoteAndDecodeInnerQuotes() {
		Assert.assertEquals("", StringUtil.unquoteAndDecodeInnerQuotes(""));
		Assert.assertEquals(
			"'Hello World'",
			StringUtil.unquoteAndDecodeInnerQuotes("'''Hello World'''"));
	}

}