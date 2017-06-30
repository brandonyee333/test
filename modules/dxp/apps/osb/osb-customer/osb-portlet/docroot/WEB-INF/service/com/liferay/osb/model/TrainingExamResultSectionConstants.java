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
public class TrainingExamResultSectionConstants {

	public static final int GRADE_FAILED = 1;

	public static final int GRADE_INCOMPLETE = 2;

	public static final int GRADE_PASSED = 3;

	public static final int GRADE_TAKEN = 4;

	public static final int GRADE_UNFINISHED = 5;

	public static int getGrade(String label) {
		if (label.equals("f")) {
			return GRADE_FAILED;
		}
		else if (label.equals("i")) {
			return GRADE_INCOMPLETE;
		}
		else if (label.equals("p")) {
			return GRADE_PASSED;
		}
		else if (label.equals("t")) {
			return GRADE_TAKEN;
		}
		else if (label.equals("u")) {
			return GRADE_UNFINISHED;
		}

		return -1;
	}

	public static String getGradeLabel(int grade) {
		if (grade == GRADE_FAILED) {
			return "failed";
		}
		else if (grade == GRADE_INCOMPLETE) {
			return "incomplete";
		}
		else if (grade == GRADE_PASSED) {
			return "passed";
		}
		else if (grade == GRADE_TAKEN) {
			return "taken";
		}
		else if (grade == GRADE_UNFINISHED) {
			return "unfinished";
		}
		else {
			return StringPool.BLANK;
		}
	}

	public static boolean getScoreIndicator(String label) {
		if (label.equals("s")) {
			return true;
		}

		return false;
	}

}