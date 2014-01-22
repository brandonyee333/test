/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.util;

import com.liferay.portal.kernel.util.CharPool;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Neil Zhao Jin
 */
public class HtmlImplTest {

	@Test
	public void testToAuiCompatibleId() {
		String testString = null;
		Assert.assertEquals(null, _htmlImpl.toAuiCompatibleId(testString));

		testString = " a";
		Assert.assertEquals("lr_20a", _htmlImpl.toAuiCompatibleId(testString));

		testString = "a b";
		Assert.assertEquals("a20b", _htmlImpl.toAuiCompatibleId(testString));

		testString = "testString012";
		Assert.assertEquals(
			testString, _htmlImpl.toAuiCompatibleId(testString));

		testString = "[script";
		Assert.assertEquals(
			"lr_5bscript", _htmlImpl.toAuiCompatibleId(testString));

		testString = "_testString012";
		Assert.assertEquals(
			"lr__testString012", _htmlImpl.toAuiCompatibleId(testString));

		testString = CharPool.RAQUO + "testString";
		Assert.assertEquals(
			"lr_bbtestString", _htmlImpl.toAuiCompatibleId(testString));

		testString = "1testString";
		Assert.assertEquals(
			"lr_1testString", _htmlImpl.toAuiCompatibleId(testString));

		testString = '\u6c49' + "testString" + '\u5b57';
		Assert.assertEquals(
			"lr_6c49testString5b57", _htmlImpl.toAuiCompatibleId(testString));
	}

	private HtmlImpl _htmlImpl = new HtmlImpl();

}