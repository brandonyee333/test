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

package com.liferay.osb.loop.web.internal.constants;

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Jonathan Lee
 */
public class LoopPersonConstants {

	public static final String CONTROLLER_DESTINATION_NAME =
		"liferay/sync_loop_person";

	public static final String EMPLOYMENT_LABEL_CONTRACTOR = "contractor";

	public static final String EMPLOYMENT_LABEL_FULL_TIME = "full-time";

	public static final String EMPLOYMENT_LABEL_INTERN = "intern";

	public static final String EMPLOYMENT_LABEL_PART_TIME = "part-time";

	public static final String EMPLOYMENT_LABEL_TEMP_TO_HIRE = "temp-to-hire";

	public static final int EMPLOYMENT_TYPE_CONTRACTOR = 5;

	public static final int EMPLOYMENT_TYPE_FULL_TIME = 1;

	public static final int EMPLOYMENT_TYPE_INTERN = 3;

	public static final int EMPLOYMENT_TYPE_PART_TIME = 4;

	public static final int EMPLOYMENT_TYPE_TEMP_TO_HIRE = 2;

	public static final int TYPE_ADMINISTRATOR = 0;

	public static final int TYPE_ALL = -1;

	public static final int TYPE_NON_ADMINISTRATOR = 1;

	public static int getEmploymentLabelType(String label) {
		if (StringUtil.equalsIgnoreCase(label, EMPLOYMENT_LABEL_CONTRACTOR)) {
			return EMPLOYMENT_TYPE_CONTRACTOR;
		}
		else if (StringUtil.equalsIgnoreCase(
					label, EMPLOYMENT_LABEL_FULL_TIME)) {

			return EMPLOYMENT_TYPE_FULL_TIME;
		}
		else if (StringUtil.equalsIgnoreCase(label, EMPLOYMENT_LABEL_INTERN)) {
			return EMPLOYMENT_TYPE_INTERN;
		}
		else if (StringUtil.equalsIgnoreCase(
					label, EMPLOYMENT_LABEL_PART_TIME)) {

			return EMPLOYMENT_TYPE_PART_TIME;
		}
		else if (StringUtil.equalsIgnoreCase(
					label, EMPLOYMENT_LABEL_TEMP_TO_HIRE)) {

			return EMPLOYMENT_TYPE_TEMP_TO_HIRE;
		}

		return 0;
	}

	public static String getEmploymentTypeLabel(int type) {
		if (type == EMPLOYMENT_TYPE_CONTRACTOR) {
			return EMPLOYMENT_LABEL_CONTRACTOR;
		}
		else if (type == EMPLOYMENT_TYPE_FULL_TIME) {
			return EMPLOYMENT_LABEL_FULL_TIME;
		}
		else if (type == EMPLOYMENT_TYPE_INTERN) {
			return EMPLOYMENT_LABEL_INTERN;
		}
		else if (type == EMPLOYMENT_TYPE_PART_TIME) {
			return EMPLOYMENT_LABEL_PART_TIME;
		}
		else if (type == EMPLOYMENT_TYPE_TEMP_TO_HIRE) {
			return EMPLOYMENT_LABEL_TEMP_TO_HIRE;
		}

		return StringPool.BLANK;
	}

}