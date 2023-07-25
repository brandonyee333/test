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