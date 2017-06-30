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
public class TrainingExamResultItemConstants {

	public static String getScoreLabel(String score) {
		if (score.equals("1")) {
			return "correct";
		}
		else {
			return "incorrect";
		}
	}

	public static String getStatusLabel(String status) {
		if (status.equals("0")) {
			return "absent";
		}
		else if (status.equals("1")) {
			return "skipped";
		}
		else if (status.equals("2")) {
			return "answered";
		}
		else if (status.equals("3")) {
			return "incomplete";
		}
		else if (status.equals("4")) {
			return "inactive";
		}
		else {
			return StringPool.BLANK;
		}
	}

}