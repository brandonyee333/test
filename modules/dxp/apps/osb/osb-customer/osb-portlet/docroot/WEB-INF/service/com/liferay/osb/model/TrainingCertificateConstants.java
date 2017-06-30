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
 * @author Calvin Keum
 */
public class TrainingCertificateConstants {

	public static final int SURVEY_STATUS_NOT_APPLICABLE = 3;

	public static final int SURVEY_STATUS_OPT_IN = 2;

	public static final int SURVEY_STATUS_OPT_OUT = 1;

	public static String getSurveyStatusLabel(int surveyStatus) {
		if (surveyStatus == SURVEY_STATUS_NOT_APPLICABLE) {
			return "not-applicable";
		}
		else if (surveyStatus == SURVEY_STATUS_OPT_IN) {
			return "opt-in";
		}
		else if (surveyStatus == SURVEY_STATUS_OPT_OUT) {
			return "opt-out";
		}
		else {
			return StringPool.BLANK;
		}
	}

}