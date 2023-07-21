/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.loop.web.internal.constants;

/**
 * @author Sherry Yang
 */
public class LoopParticipantAssignmentConstants {

	public static final String LABEL_INHERITED = "inherited";

	public static final String LABEL_LEAD = "lead";

	public static final String LABEL_MEMBER = "member";

	public static final String LABEL_SPONSOR = "sponsor";

	public static final int TYPE_INHERITED = 4;

	public static final int TYPE_LEAD = 1;

	public static final int TYPE_MEMBER = 2;

	public static final int TYPE_SPONSOR = 3;

	public static String getTypeLabel(int type) {
		if (type == TYPE_INHERITED) {
			return LABEL_INHERITED;
		}
		else if (type == TYPE_LEAD) {
			return LABEL_LEAD;
		}
		else if (type == TYPE_MEMBER) {
			return LABEL_MEMBER;
		}

		return LABEL_SPONSOR;
	}

}