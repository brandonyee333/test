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
 * @author Amos Fong
 */
public class TicketFeedbackConstants {

	public static final int ANSWER_NO = 2;

	public static final int ANSWER_NOT_APPLICABLE = 3;

	public static final int ANSWER_YES = 1;

	public static final int SATISFIED_NO = 2;

	public static final int SATISFIED_NOT_APPLICABLE = 3;

	public static final int SATISFIED_YES = 1;

	public static final int STATUS_ANSWERED = 2;

	public static final int STATUS_CLOSED = 3;

	public static final int STATUS_UNANSWERED = 1;

	public static final int SUBJECT_LIFERAY = 1;

	public static final int SUBJECT_PARTNER = 2;

	public static String getAnswerLabel(int answer) {
		if (answer == ANSWER_NO) {
			return "no";
		}
		else if (answer == ANSWER_NOT_APPLICABLE) {
			return "not-applicable";
		}
		else if (answer == ANSWER_YES) {
			return "yes";
		}
		else {
			return StringPool.BLANK;
		}
	}

	public static String getRatingLabel(int rating) {
		if (rating == 1) {
			return "very-dissatisfied";
		}
		else if (rating == 2) {
			return "dissatisfied";
		}
		else if (rating == 3) {
			return "neutral";
		}
		else if (rating == 4) {
			return "satisfied";
		}
		else if (rating == 5) {
			return "very-satisfied";
		}
		else {
			return StringPool.BLANK;
		}
	}

	public static String getSatisfiedLabel(int satisfied) {
		if (satisfied == SATISFIED_NO) {
			return "no";
		}
		else if (satisfied == SATISFIED_NOT_APPLICABLE) {
			return "n-a";
		}
		else if (satisfied == SATISFIED_YES) {
			return "yes";
		}
		else {
			return StringPool.BLANK;
		}
	}

	public static String getStatusLabel(int status) {
		if (status == STATUS_ANSWERED) {
			return "answered";
		}
		else if (status == STATUS_CLOSED) {
			return "closed";
		}
		else if (status == STATUS_UNANSWERED) {
			return "unanswered";
		}
		else {
			return StringPool.BLANK;
		}
	}

}