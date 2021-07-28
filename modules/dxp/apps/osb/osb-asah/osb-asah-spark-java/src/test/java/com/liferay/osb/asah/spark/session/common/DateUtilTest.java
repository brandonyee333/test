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

package com.liferay.osb.asah.spark.session.common;

import com.liferay.osb.asah.spark.common.DateUtil;

import java.sql.Date;
import java.sql.Timestamp;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Robson Pastor
 */
public class DateUtilTest {

	@Test
	public void testMax() {
		Timestamp before = DateUtil.toTimestamp("2018-09-15T09:00:01.000Z");
		Timestamp after = DateUtil.toTimestamp("2018-09-15T10:00:01.000Z");
		Timestamp expected = DateUtil.toTimestamp("2018-09-15T10:00:01.000Z");

		Timestamp max1 = DateUtil.max(before, after);
		Timestamp max2 = DateUtil.max(after, before);

		Assert.assertEquals(expected, max1);
		Assert.assertEquals(expected, max2);
	}

	@Test
	public void testMin() {
		Timestamp before = DateUtil.toTimestamp("2018-09-15T09:00:01.000Z");
		Timestamp after = DateUtil.toTimestamp("2018-09-15T10:00:01.000Z");
		Timestamp expected = DateUtil.toTimestamp("2018-09-15T09:00:01.000Z");

		Timestamp min1 = DateUtil.min(before, after);
		Timestamp min2 = DateUtil.min(after, before);

		Assert.assertEquals("It should return min", expected, min1);
		Assert.assertEquals("It should return min", expected, min2);
	}

	@Test
	public void testToDate() {
		Date date = DateUtil.toDate("2018-09-15");
		Date expectedDate = new Date(1536969600000L);

		Assert.assertEquals("It should return date 2018-09-15", expectedDate, date);
	}

	@Test
	public void testToTimestamp() {
		Timestamp timestamp = DateUtil.toTimestamp("2018-09-15T10:00:01.000Z");
		Timestamp expectedTimestamp = new Timestamp(1537005601000L);

		Assert.assertEquals("It should return timestamp 2018-09-15T10:00:01.000Z",expectedTimestamp, timestamp);
	}

}