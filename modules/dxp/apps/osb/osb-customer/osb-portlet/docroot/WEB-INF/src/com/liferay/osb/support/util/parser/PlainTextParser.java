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
import com.liferay.osb.service.AccountEntryLocalServiceUtil;
import com.liferay.osb.service.TicketEntryLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Amos Fong
 */
public class PlainTextParser {

	public String parse(String text) {
		Matcher matcher = _urlPattern.matcher(text);

		while (matcher.find()) {
			text = matcher.replaceAll(
				"<a href=\"$1\" target=\"_blank\">$1</a>");
		}

		matcher = _ticketPattern.matcher(text);

		StringBundler sb = new StringBundler();

		int pos = 0;

		while (matcher.find()) {
			if (Validator.isNotNull(matcher.group(1))) {
				continue;
			}

			String displayId = matcher.group(2);
			String code = matcher.group(3);
			long ticketId = GetterUtil.getLong(matcher.group(4));

			boolean jiraTicket = false;

			if (code.equals("LEP") || code.equals("LPE") ||
				code.equals("LPS")) {

				jiraTicket = true;
			}

			if (!jiraTicket) {
				try {
					AccountEntry accountEntry =
						AccountEntryLocalServiceUtil.getAccountEntryByCode(
							code);

					TicketEntryLocalServiceUtil.getTicketEntry(
						accountEntry.getAccountEntryId(), ticketId);
				}
				catch (Exception e) {
					continue;
				}
			}

			sb.append(text.substring(pos, matcher.start(1)));
			sb.append("<a href=\"");

			if (jiraTicket) {
				sb.append(_JIRA_TICKET_URL);
			}
			else {
				sb.append(_LIFERAY_TICKET_URL);
			}

			sb.append(displayId);
			sb.append("\">");
			sb.append(displayId);
			sb.append("</a>");

			pos = matcher.end();
		}

		if (pos != 0) {
			sb.append(text.substring(pos, text.length()));

			text = sb.toString();
		}

		text = text.replace("\n", "<br />");

		return text;
	}

	private static final String _JIRA_TICKET_URL =
		"https://issues.liferay.com/browse/";

	private static final String _LIFERAY_TICKET_URL =
		"http://www.liferay.com/group/customer/support/-/support/ticket/";

	private static final Pattern _ticketPattern = Pattern.compile(
		"([/=]?)(([A-Z0-9]+)-([0-9]+))");
	private static final Pattern _urlPattern = Pattern.compile(
		"((?<!href=\")https?://[-\\p{L}0-9~!@#%&()_+=|:;,.?/]+(?<![,.]))",
		Pattern.CASE_INSENSITIVE);

}