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

package com.liferay.osb.asah.backend.rest.controller.util;

import com.liferay.osb.asah.common.date.DateUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Inácio Nery
 */
public class FilterUtilTest {

	@Test
	public void testGetBoolean() {
		Assert.assertFalse(
			FilterUtil.getBoolean("(workspaceURL eq null)", "url"));
		Assert.assertTrue(
			FilterUtil.getBoolean("(channelId eq '123')", "channelId"));
		Assert.assertTrue(FilterUtil.getBoolean("(url eq '')", "url"));
		Assert.assertTrue(
			FilterUtil.getBoolean("(workspaceURL eq null)", "workspaceURL"));
	}

	@Test
	public void testGetEndDate() {
		Assert.assertEquals(
			DateUtil.toUTCDate(
				LocalDateTime.of(LocalDate.of(2021, 03, 25), LocalTime.MAX)),
			FilterUtil.getEndDate(
				"(day ge '2021-03-25T00:00:00Z' and day lt " +
					"'2021-03-25T23:59:59.999999999Z')",
				"day"));
	}

	@Test
	public void testGetLong() {
		Assert.assertEquals(
			(Long)123L,
			FilterUtil.getLong("(channelId eq '123')", "channelId"));
		Assert.assertNull(
			FilterUtil.getLong("(channelId eq null)", "channelId"));
	}

	@Test
	public void testGetLongValues() {
		Assert.assertEquals(
			Arrays.asList(123L, 456L),
			FilterUtil.getLongValues(
				"(channelId eq [123,456])", "channelId", false));
	}

	@Test
	public void testGetStartDate() {
		Assert.assertEquals(
			DateUtil.toUTCDate(LocalDateTime.of(2021, 03, 25, 0, 0, 0)),
			FilterUtil.getStartDate(
				"(day ge '2021-03-25T00:00:00Z' and day lt " +
					"'2021-03-25T23:59:59.999999999Z')",
				"day"));
	}

	@Test
	public void testGetString() {
		Assert.assertEquals(
			"Token Authentication",
			FilterUtil.getString(
				"(credentials/type eq 'Token Authentication')",
				"credentials/type"));
	}

	@Test
	public void testGetStringValues() {
		Assert.assertEquals(
			Arrays.asList("Liferay", "Brazil"),
			FilterUtil.getStringValues(
				"(contains(name,'Liferay') and contains(name,'Brazil') and " +
					"name eq 'Liferay Brazil')",
				"name", true));
		Assert.assertEquals(
			Arrays.asList("Liferay Brazil"),
			FilterUtil.getStringValues(
				"(contains(name,'Liferay') and contains(name,'Brazil') and " +
					"name eq 'Liferay Brazil')",
				"name", false));
	}

}