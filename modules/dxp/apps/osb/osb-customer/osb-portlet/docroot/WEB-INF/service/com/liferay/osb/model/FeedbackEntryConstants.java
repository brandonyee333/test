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

package com.liferay.osb.model;

import com.liferay.portal.kernel.util.StringPool;

/**
 * @author Kyle Bischof
 */
public class FeedbackEntryConstants {

	public static final int ANSWER_NO = 2;

	public static final int ANSWER_YES = 1;

	public static String getAnswerLabel(int answer) {
		if (answer == ANSWER_NO) {
			return "no";
		}
		else if (answer == ANSWER_YES) {
			return "yes";
		}
		else {
			return StringPool.BLANK;
		}
	}

}