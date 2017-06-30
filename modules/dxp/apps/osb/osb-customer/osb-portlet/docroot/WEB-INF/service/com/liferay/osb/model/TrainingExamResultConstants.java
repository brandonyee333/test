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

/**
 * @author Kyle Bischof
 * @author Haote Chou
 */
public class TrainingExamResultConstants {

	public static final int GRADE_FAILED = 1;

	public static final int GRADE_INCOMPLETE = 2;

	public static final int GRADE_PASSED = 3;

	public static final int GRADE_TAKEN = 4;

	public static final int GRADE_UNDEFINED = -1;

	public static final int GRADE_UNFINISHED = 5;

	public static final String PROMETRIC_DATE_FORMAT = "MM/dd/yyyy";

	public static final String PROMETRIC_FILE_SIGNATURE = "CNMExport";

	public static final int PROMETRIC_SUPPORTED_VERSION = 11;

	public static final String PROMETRIC_TIME_FORMAT = "HH:mm:ss";

	public static final int RECORD_TYPE_CANCEL = 1;

	public static final int RECORD_TYPE_EXPIRE = 2;

	public static final int RECORD_TYPE_NO_SHOW = 3;

	public static final int RECORD_TYPE_TEST = 4;

	public static final int RECORD_TYPE_UNDEFINED = -1;

	public static int getGrade(String label) {
		if (label.equals("failed")) {
			return GRADE_FAILED;
		}
		else if (label.equals("incomplete")) {
			return GRADE_INCOMPLETE;
		}
		else if (label.equals("passed")) {
			return GRADE_PASSED;
		}
		else if (label.equals("taken")) {
			return GRADE_TAKEN;
		}
		else if (label.equals("unfinished")) {
			return GRADE_UNFINISHED;
		}

		return GRADE_UNDEFINED;
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

		return "undefined";
	}

	public static int getRecordType(String label) {
		if (label.equals("cancel")) {
			return RECORD_TYPE_CANCEL;
		}
		else if (label.equals("expire")) {
			return RECORD_TYPE_EXPIRE;
		}
		else if (label.equals("no-show")) {
			return RECORD_TYPE_NO_SHOW;
		}
		else if (label.equals("test")) {
			return RECORD_TYPE_TEST;
		}

		return RECORD_TYPE_UNDEFINED;
	}

	public static String getRecordTypeLabel(int recordType) {
		if (recordType == RECORD_TYPE_CANCEL) {
			return "cancel";
		}
		else if (recordType == RECORD_TYPE_EXPIRE) {
			return "expire";
		}
		else if (recordType == RECORD_TYPE_NO_SHOW) {
			return "no-show";
		}
		else if (recordType == RECORD_TYPE_TEST) {
			return "test";
		}

		return "undefined";
	}

}