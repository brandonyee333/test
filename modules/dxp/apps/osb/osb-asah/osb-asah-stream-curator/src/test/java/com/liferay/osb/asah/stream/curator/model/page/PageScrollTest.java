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

package com.liferay.osb.asah.stream.curator.model.page;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Marcellus Tavares
 */
public class PageScrollTest {

	@Test
	public void testEquals() {
		Date date = new Date();

		Assert.assertEquals(new PageScroll(25, date), new PageScroll(25, date));
	}

	@Test
	public void testHashCodeEquals() {
		Date date = new Date();

		PageScroll pageScroll1 = new PageScroll(25, date);
		PageScroll pageScroll2 = new PageScroll(25, date);

		Assert.assertEquals(pageScroll1.hashCode(), pageScroll2.hashCode());
	}

	@Test
	public void testHashCodeNotEquals1() {
		PageScroll pageScroll1 = new PageScroll(25, _toDate(2016, 7, 19));
		PageScroll pageScroll2 = new PageScroll(25, _toDate(2016, 7, 20));

		Assert.assertNotEquals(pageScroll1.hashCode(), pageScroll2.hashCode());
	}

	@Test
	public void testHashCodeNotEquals2() {
		Date date = new Date();

		PageScroll pageScroll1 = new PageScroll(25, date);
		PageScroll pageScroll2 = new PageScroll(30, date);

		Assert.assertNotEquals(pageScroll1.hashCode(), pageScroll2.hashCode());
	}

	@Test
	public void testNotEquals1() {
		Assert.assertNotEquals(
			new PageScroll(25, _toDate(2016, 7, 19)),
			new PageScroll(25, _toDate(2016, 7, 20)));
	}

	@Test
	public void testNotEquals2() {
		Date date = new Date();

		Assert.assertNotEquals(
			new PageScroll(25, date), new PageScroll(30, date));
	}

	private Date _toDate(int year, int month, int day) {
		Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));

		calendar.set(Calendar.DATE, day);
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.YEAR, year);

		return calendar.getTime();
	}

}