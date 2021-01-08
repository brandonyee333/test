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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Inácio Nery
 */
public class TimeRangeTest {

	@Test
	public void testGetEndLocalDateTime() {
		TimeRange timeRange = TimeRange.LAST_30_DAYS;

		LocalDateTime localDateTime = LocalDateTime.now(ZoneOffset.UTC);

		localDateTime = localDateTime.minusDays(1);
		localDateTime = localDateTime.withHour(23);
		localDateTime = localDateTime.withMinute(59);
		localDateTime = localDateTime.withSecond(59);
		localDateTime = localDateTime.withNano(999999999);

		Assert.assertEquals(localDateTime, timeRange.getEndLocalDateTime());
	}

	@Test
	public void testGetKey1() {
		TimeRange timeRange = TimeRange.LAST_7_DAYS;

		Assert.assertEquals("last-7-days", timeRange.getKey());
	}

	@Test
	public void testGetKey2() {
		TimeRange timeRange = TimeRange.LAST_24_HOURS;

		Assert.assertEquals("last-24-hours", timeRange.getKey());
	}

	@Test
	public void testGetKey3() {
		TimeRange timeRange = TimeRange.LAST_28_DAYS;

		Assert.assertEquals("last-28-days", timeRange.getKey());
	}

	@Test
	public void testGetKey4() {
		TimeRange timeRange = TimeRange.LAST_30_DAYS;

		Assert.assertEquals("last-30-days", timeRange.getKey());
	}

	@Test
	public void testGetKey5() {
		TimeRange timeRange = TimeRange.LAST_90_DAYS;

		Assert.assertEquals("last-90-days", timeRange.getKey());
	}

	@Test
	public void testGetKey6() {
		TimeRange timeRange = TimeRange.YESTERDAY;

		Assert.assertEquals("yesterday", timeRange.getKey());
	}

	@Test
	public void testGetRangeKey1() {
		TimeRange timeRange = TimeRange.LAST_7_DAYS;

		Assert.assertEquals(7, timeRange.getRangeKey());
	}

	@Test
	public void testGetRangeKey2() {
		TimeRange timeRange = TimeRange.LAST_24_HOURS;

		Assert.assertEquals(0, timeRange.getRangeKey());
	}

	@Test
	public void testGetRangeKey3() {
		TimeRange timeRange = TimeRange.LAST_28_DAYS;

		Assert.assertEquals(28, timeRange.getRangeKey());
	}

	@Test
	public void testGetRangeKey4() {
		TimeRange timeRange = TimeRange.LAST_30_DAYS;

		Assert.assertEquals(30, timeRange.getRangeKey());
	}

	@Test
	public void testGetRangeKey5() {
		TimeRange timeRange = TimeRange.LAST_90_DAYS;

		Assert.assertEquals(90, timeRange.getRangeKey());
	}

	@Test
	public void testGetRangeKey6() {
		TimeRange timeRange = TimeRange.YESTERDAY;

		Assert.assertEquals(1, timeRange.getRangeKey());
	}

	@Test
	public void testGetStartLocalDateTime() {
		TimeRange timeRange = TimeRange.LAST_30_DAYS;

		LocalDateTime localDateTime = LocalDateTime.of(
			LocalDate.now(ZoneOffset.UTC), LocalTime.MIDNIGHT);

		Assert.assertEquals(
			localDateTime.minusDays(30), timeRange.getStartLocalDateTime());
	}

	@Test
	public void testTimeRangeCustom() {
		TimeRange timeRange = TimeRange.of(1);

		Assert.assertEquals(TimeRange.YESTERDAY, timeRange);
	}

	@Test
	public void testTimeRangeOf1() {
		TimeRange timeRange = TimeRange.of(7);

		Assert.assertEquals(TimeRange.LAST_7_DAYS, timeRange);
	}

	@Test
	public void testTimeRangeOf2() {
		TimeRange timeRange = TimeRange.of(0);

		Assert.assertEquals(TimeRange.LAST_24_HOURS, timeRange);
	}

	@Test
	public void testTimeRangeOf3() {
		TimeRange timeRange = TimeRange.of(28);

		Assert.assertEquals(TimeRange.LAST_28_DAYS, timeRange);
	}

	@Test
	public void testTimeRangeOf4() {
		TimeRange timeRange = TimeRange.of(30);

		Assert.assertEquals(TimeRange.LAST_30_DAYS, timeRange);
	}

	@Test
	public void testTimeRangeOf5() {
		TimeRange timeRange = TimeRange.of(90);

		Assert.assertEquals(TimeRange.LAST_90_DAYS, timeRange);
	}

	@Test
	public void testTimeRangeOf6() {
		LocalDateTime endLocalDateTime = LocalDateTime.now();

		LocalDateTime startLocalDateTime = endLocalDateTime.minusDays(2);

		TimeRange timeRange = TimeRange.of(
			endLocalDateTime, startLocalDateTime);

		Assert.assertEquals("custom", timeRange.getKey());
		Assert.assertEquals(endLocalDateTime, timeRange.getEndLocalDateTime());
		Assert.assertEquals(3, timeRange.getRangeKey());
		Assert.assertEquals(
			startLocalDateTime, timeRange.getStartLocalDateTime());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testTimeRangeOf7() {
		TimeRange.of(14);
	}

}