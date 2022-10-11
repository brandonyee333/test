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

package com.liferay.osb.asah.dataflow.common;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Leslie Wong
 */
public class URLUtilTest {

	@Test
	public void testCreateURL() throws Exception {
		URI uri = URLUtil.createURI(
			"https://test.liferay.com/web/guest/home?searchString=" +
				"testing 1 2 3");

		Assert.assertEquals("https", uri.getScheme());
		Assert.assertEquals("test.liferay.com", uri.getAuthority());
		Assert.assertEquals("/web/guest/home", uri.getPath());
		Assert.assertEquals("searchString=testing 1 2 3", uri.getQuery());
		Assert.assertEquals(null, uri.getFragment());
	}

	@Test
	public void testCreateURLDoubleHashMark() throws Exception {
		URI uri = URLUtil.createURI(
			"https://test.liferay.com/web/guest/home#/#abc123");

		Assert.assertEquals("https", uri.getScheme());
		Assert.assertEquals("test.liferay.com", uri.getAuthority());
		Assert.assertEquals("/web/guest/home", uri.getPath());
		Assert.assertEquals(null, uri.getQuery());
		Assert.assertEquals("/#abc123", uri.getFragment());
	}

	@Test(expected = NullPointerException.class)
	public void testCreateUrlEmptyString() throws Exception {
		URLUtil.createURI("");
	}

	@Test
	public void testCreateURLNoAuthority() throws Exception {
		URI uri = URLUtil.createURI("https://?searchString=testing 1 2 3");

		Assert.assertEquals("https", uri.getScheme());
		Assert.assertEquals(null, uri.getAuthority());
		Assert.assertEquals("", uri.getPath());
		Assert.assertEquals("searchString=testing 1 2 3", uri.getQuery());
		Assert.assertEquals(null, uri.getFragment());
	}

	@Test
	public void testCreateURLNoFragment() throws Exception {
		URI uri = URLUtil.createURI("https://test.liferay.com/web/guest/home#");

		Assert.assertEquals("https", uri.getScheme());
		Assert.assertEquals("test.liferay.com", uri.getAuthority());
		Assert.assertEquals("/web/guest/home", uri.getPath());
		Assert.assertEquals(null, uri.getQuery());
		Assert.assertEquals(null, uri.getFragment());
	}

	@Test
	public void testCreateURLNoPath1() throws Exception {
		URI uri = URLUtil.createURI(
			"https://test.liferay.com?searchString=testing 1 2 3");

		Assert.assertEquals("https", uri.getScheme());
		Assert.assertEquals("test.liferay.com", uri.getAuthority());
		Assert.assertEquals("", uri.getPath());
		Assert.assertEquals("searchString=testing 1 2 3", uri.getQuery());
		Assert.assertEquals(null, uri.getFragment());
	}

	@Test
	public void testCreateURLNoPath2() throws Exception {
		URI uri = URLUtil.createURI(
			"https://test.liferay.com/?searchString=testing 1 2 3");

		Assert.assertEquals("https", uri.getScheme());
		Assert.assertEquals("test.liferay.com", uri.getAuthority());
		Assert.assertEquals("", uri.getPath());
		Assert.assertEquals("searchString=testing 1 2 3", uri.getQuery());
		Assert.assertEquals(null, uri.getFragment());
	}

	@Test
	public void testCreateURLNoQuery1() throws Exception {
		URI uri = URLUtil.createURI("https://test.liferay.com/web/guest/home");

		Assert.assertEquals("https", uri.getScheme());
		Assert.assertEquals("test.liferay.com", uri.getAuthority());
		Assert.assertEquals("/web/guest/home", uri.getPath());
		Assert.assertEquals(null, uri.getQuery());
		Assert.assertEquals(null, uri.getFragment());
	}

	@Test
	public void testCreateURLNoQuery2() throws Exception {
		URI uri = URLUtil.createURI("https://test.liferay.com/web/guest/home/");

		Assert.assertEquals("https", uri.getScheme());
		Assert.assertEquals("test.liferay.com", uri.getAuthority());
		Assert.assertEquals("/web/guest/home/", uri.getPath());
		Assert.assertEquals(null, uri.getQuery());
		Assert.assertEquals(null, uri.getFragment());
	}

	@Test(expected = URISyntaxException.class)
	public void testCreateURLNoScheme() throws Exception {
		URLUtil.createURI("://test.liferay.com/web/guest/home");
	}

	@Test(expected = NullPointerException.class)
	public void testCreateUrlNullString() throws Exception {
		URLUtil.createURI(null);
	}

}