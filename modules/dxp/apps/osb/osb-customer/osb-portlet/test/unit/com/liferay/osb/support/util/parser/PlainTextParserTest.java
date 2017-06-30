/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.osb.support.util.parser;

import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.TicketEntry;
import com.liferay.osb.service.AccountEntryLocalServiceUtil;
import com.liferay.osb.service.TicketEntryLocalServiceUtil;

import org.apache.commons.lang.math.RandomUtils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Kyle Bischof
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(
	{
		AccountEntryLocalServiceUtil.class, TicketEntryLocalServiceUtil.class
	})
public class PlainTextParserTest extends PowerMockito {

	@Before
	public void setUp() throws Exception {
		when(
			accountEntry.getAccountEntryId()
		).thenReturn(
			RandomUtils.nextLong()
		);

		when(
			accountEntry.getCode()
		).thenReturn(
			"THESPACEPRO"
		);

		when(
			ticketEntry.getTicketId()
		).thenReturn(
			RandomUtils.nextLong()
		);

		setUpAccountEntryLocalServiceUtil();
		setUpTicketEntryLocalServiceUtil();
	}

	@Test
	public void testJiraTicketUrl() throws Exception {
		String parsedText = _plainTextParser.parse("LEP-123");

		Assert.assertEquals(
			"<a href=\"https://issues.liferay.com/browse/LEP-123\">LEP-123" +
				"</a>", parsedText);

		parsedText = _plainTextParser.parse("LPE-123");

		Assert.assertEquals(
			"<a href=\"https://issues.liferay.com/browse/LPE-123\">LPE-123" +
				"</a>", parsedText);

		parsedText = _plainTextParser.parse("LPS-123");

		Assert.assertEquals(
			"<a href=\"https://issues.liferay.com/browse/LPS-123\">LPS-123" +
				"</a>", parsedText);
	}

	@Test
	public void testLiferayTicketUrl() throws Exception {
		String parsedText = _plainTextParser.parse("THESPACEPRO-123");

		Assert.assertEquals(
			"<a href=\"http://www.liferay.com/group/customer/support/-/" +
				"support/ticket/THESPACEPRO-123\">THESPACEPRO-123</a>",
			parsedText);
	}

	@Test
	public void testSimpleUrl() throws Exception {
		String parsedText = _plainTextParser.parse("https://www.liferay.com");

		Assert.assertEquals(
			"<a href=\"https://www.liferay.com\" target=\"_blank\">" +
				"https://www.liferay.com</a>", parsedText);

		parsedText = _plainTextParser.parse(
			"Please visit https://www.liferay.com.");

		Assert.assertEquals(
			"Please visit <a href=\"https://www.liferay.com\" target=\"_blank" +
				"\">https://www.liferay.com</a>.", parsedText);

		parsedText = _plainTextParser.parse(
			"Please visit https://www.dev.liferay.com, or visit our forum.");

		Assert.assertEquals(
			"Please visit <a href=\"https://www.dev.liferay.com\" target=\"" +
				"_blank\">https://www.dev.liferay.com</a>, or visit our forum.",
			parsedText);
	}

	protected void setUpAccountEntryLocalServiceUtil() throws Exception {
		mockStatic(AccountEntryLocalServiceUtil.class);

		when(
			AccountEntryLocalServiceUtil.getAccountEntryByCode(
				accountEntry.getCode())
		).thenReturn(
			accountEntry
		);
	}

	protected void setUpTicketEntryLocalServiceUtil() throws Exception {
		mockStatic(TicketEntryLocalServiceUtil.class);

		when(
			TicketEntryLocalServiceUtil.getTicketEntry(
				accountEntry.getAccountEntryId(), ticketEntry.getTicketId())
		).thenReturn(
			ticketEntry
		);
	}

	protected AccountEntry accountEntry = mock(AccountEntry.class);
	protected TicketEntry ticketEntry = mock(TicketEntry.class);

	private PlainTextParser _plainTextParser = new PlainTextParser();

}