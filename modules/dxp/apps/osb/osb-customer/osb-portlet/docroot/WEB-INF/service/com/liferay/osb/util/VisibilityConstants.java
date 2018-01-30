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

package com.liferay.osb.util;

import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.TicketEntry;
import com.liferay.osb.service.PartnerWorkerLocalServiceUtil;
import com.liferay.osb.service.TicketEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alan Zhang
 */
public class VisibilityConstants {

	public static final int ADMIN = 4;

	public static final int LIFERAY_INC = 3;

	public static final int PUBLIC = 1;

	public static final int WORKERS = 2;

	public static int[] getUserVisibilities(long userId, long ticketEntryId)
		throws PortalException {

		List<Integer> userVisibilities = new ArrayList<>();

		for (int visibility : _DISPLAY_VISIBILITIES) {
			if (!hasVisibility(userId, ticketEntryId, visibility)) {
				continue;
			}

			userVisibilities.add(visibility);
		}

		return ArrayUtil.toArray(userVisibilities.toArray(new Integer[0]));
	}

	public static boolean hasVisibility(
			long userId, long ticketEntryId, int visibility)
		throws PortalException {

		if (visibility == PUBLIC) {
			return true;
		}

		if (OrganizationLocalServiceUtil.hasUserOrganization(
				userId, OSBConstants.ORGANIZATION_LIFERAY_INC_ID)) {

			return true;
		}

		if (visibility == WORKERS) {
			TicketEntry ticketEntry =
				TicketEntryLocalServiceUtil.getTicketEntry(ticketEntryId);

			AccountEntry accountEntry = ticketEntry.getAccountEntry();

			if (PartnerWorkerLocalServiceUtil.hasPartnerWorker(
					userId, accountEntry.getPartnerEntryId())) {

				return true;
			}
		}

		return false;
	}

	public static String toLabel(int visibility) {
		if (visibility == ADMIN) {
			return "admin";
		}
		else if (visibility == LIFERAY_INC) {
			return "liferay";
		}
		else if (visibility == PUBLIC) {
			return "public";
		}
		else if (visibility == WORKERS) {
			return "workers";
		}
		else {
			return StringPool.BLANK;
		}
	}

	public static int toVisibility(String label) {
		if (label.equals("admin")) {
			return ADMIN;
		}
		else if (label.equals("liferay")) {
			return LIFERAY_INC;
		}
		else if (label.equals("public")) {
			return PUBLIC;
		}
		else if (label.equals("workers")) {
			return WORKERS;
		}
		else {
			return 0;
		}
	}

	private static final int[] _DISPLAY_VISIBILITIES =
		{LIFERAY_INC, PUBLIC, WORKERS};

}