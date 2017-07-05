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

package com.liferay.osb.hook.upgrade.v3_6_2;

import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.TicketFeedback;
import com.liferay.osb.model.TicketFeedbackConstants;
import com.liferay.osb.service.TicketFeedbackLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.List;

/**
 * @author Kyle Bischof
 */
public class Upgrade_20161118140245391_TicketFeedback
	extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20161118140245391L;
	}

	protected void deleteTicketFeedbacks(long ticketEntryId)
		throws SystemException {

		List<TicketFeedback> ticketFeedbacks =
			ListUtil.copy(
				TicketFeedbackLocalServiceUtil.getTicketFeedbacks(
					ticketEntryId, TicketFeedbackConstants.SUBJECT_LIFERAY,
					TicketFeedbackConstants.STATUS_ANSWERED));

		if (!ticketFeedbacks.isEmpty()) {
			ticketFeedbacks.remove(0);

			for (TicketFeedback ticketFeedback : ticketFeedbacks) {
				TicketFeedbackLocalServiceUtil.deleteTicketFeedback(
					ticketFeedback);
			}
		}
	}

	@Override
	protected void doUpgrade() throws Exception {
		long vicses10TicketEntryId = 59310;
		long vicses15TicketEntryId = 59821;

		deleteTicketFeedbacks(vicses10TicketEntryId);
		deleteTicketFeedbacks(vicses15TicketEntryId);
	}

}