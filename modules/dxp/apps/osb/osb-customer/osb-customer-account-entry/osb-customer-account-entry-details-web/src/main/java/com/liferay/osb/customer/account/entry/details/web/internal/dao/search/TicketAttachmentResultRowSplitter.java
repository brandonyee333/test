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