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

import java.sql.Timestamp;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Robson Pastor
 */
public class DateUtilTest {

	@Test
	public void testMax() {
		Timestamp beforeTimestamp = DateUtil.toTimestamp(
			"2018-09-15T09:00:01.000Z");
		Timestamp afterTimestamp = DateUtil.toTimestamp(
			"2018-09-15T10:00:01.000Z");

		Assert.assertEquals(
			afterTimestamp, DateUtil.max(beforeTimestamp, afterTimestamp));
		Assert.assertEquals(
			afterTimestamp, DateUtil.max(afterTimestamp, beforeTimestamp));
	}

	@Test
	public void testMin() {
		Timestamp beforeTimestamp = DateUtil.toTimestamp(
			"2018-09-15T09:00:01.000Z");
		Timestamp afterTimestamp = DateUtil.toTimestamp(
			"2018-09-15T10:00:01.000Z");

		Assert.assertEquals(
			beforeTimestamp, DateUtil.min(beforeTimestamp, afterTimestamp));
		Assert.assertEquals(
			beforeTimestamp, DateUtil.min(afterTimestamp, beforeTimestamp));
	}

}