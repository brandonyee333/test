/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.zendesk.constants;

import com.liferay.petra.string.StringPool;

/**
 * @author Jenny Chen
 */
public interface ZendeskFeedbackConstants {

	public static final String CES1_1 = "ces1_1";

	public static final String CES1_2 = "ces1_2";

	public static final String CES1_3 = "ces1_3";

	public static final String CES1_4 = "ces1_4";

	public static final String CES1_5 = "ces1_5";

	public static final String CSAT1_1 = "csat1_1";

	public static final String CSAT1_2 = "csat1_2";

	public static final String SURVEY_COMMENT = "survey_comment";

	public static String getRatingLabel(String rating) {
		if (rating.equals(CES1_1)) {
			return "strongly-disagree";
		}
		else if (rating.equals(CES1_2)) {
			return "disagree";
		}
		else if (rating.equals(CES1_3)) {
			return "neutral";
		}
		else if (rating.equals(CES1_4)) {
			return "agree";
		}
		else if (rating.equals(CES1_5)) {
			return "strongly-agree";
		}
		else if (rating.equals(CSAT1_1)) {
			return "bad-im-unsatisfied";
		}
		else if (rating.equals(CSAT1_2)) {
			return "good-im-satisfied";
		}

		return StringPool.BLANK;
	}

}