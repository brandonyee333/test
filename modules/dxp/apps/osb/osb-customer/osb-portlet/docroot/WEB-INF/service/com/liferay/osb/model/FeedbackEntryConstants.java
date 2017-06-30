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