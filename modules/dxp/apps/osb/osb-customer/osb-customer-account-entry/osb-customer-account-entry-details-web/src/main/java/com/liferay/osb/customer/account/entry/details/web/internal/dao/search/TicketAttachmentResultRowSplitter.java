/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.account.entry.details.web.internal.dao.search;

import com.liferay.osb.customer.ticket.model.TicketAttachment;
import com.liferay.portal.kernel.dao.search.ResultRow;
import com.liferay.portal.kernel.dao.search.ResultRowSplitter;
import com.liferay.portal.kernel.dao.search.ResultRowSplitterEntry;
import com.liferay.portal.kernel.util.StringPool;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Amos Fong
 */
public class TicketAttachmentResultRowSplitter implements ResultRowSplitter {

	@Override
	public List<ResultRowSplitterEntry> split(List<ResultRow> resultRows) {
		List<ResultRowSplitterEntry> resultRowSplitterEntries =
			new ArrayList<>();

		Map<Long, List<ResultRow>> zendeskTicketResultRowsMap = new TreeMap<>();

		for (ResultRow resultRow : resultRows) {
			TicketAttachment ticketAttachment =
				(TicketAttachment)resultRow.getObject();

			List<ResultRow> zendeskTicketResultRows =
				zendeskTicketResultRowsMap.get(
					ticketAttachment.getZendeskTicketId());

			if (zendeskTicketResultRows == null) {
				zendeskTicketResultRows = new ArrayList<>();

				zendeskTicketResultRowsMap.put(
					ticketAttachment.getZendeskTicketId(),
					zendeskTicketResultRows);
			}

			zendeskTicketResultRows.add(resultRow);
		}

		if (zendeskTicketResultRowsMap.size() <= 1) {
			resultRowSplitterEntries.add(
				new ResultRowSplitterEntry(StringPool.BLANK, resultRows));
		}
		else {
			for (Map.Entry<Long, List<ResultRow>> entry :
					zendeskTicketResultRowsMap.entrySet()) {

				resultRowSplitterEntries.add(
					new ResultRowSplitterEntry(
						String.valueOf(entry.getKey()), entry.getValue()));
			}
		}

		return resultRowSplitterEntries;
	}

}