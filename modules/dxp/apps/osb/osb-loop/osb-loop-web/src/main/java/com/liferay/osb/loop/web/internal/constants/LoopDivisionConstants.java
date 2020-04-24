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

import com.liferay.osb.loop.constants.LoopConstants;
import com.liferay.portal.kernel.util.StringPool;

/**
 * @author Calvin Keum
 */
public class LoopDivisionConstants {

	public static final String CONTROLLER_DESTINATION_NAME =
		"liferay/sync_loop_division";

	public static final String LABEL_DEPARTMENT =
		LoopConstants.LABEL_DEPARTMENT;

	public static final String LABEL_LOCATION = LoopConstants.LABEL_LOCATION;

	public static final String LABEL_ROOT = LoopConstants.LABEL_ROOT;

	public static final String LABEL_TEAM = LoopConstants.LABEL_TEAM;

	public static final String ROOT_ORGANIZATION_NAME_DEFAULT = "Loop Division";

	public static final int SUBTYPE_DEPARTMENT_FUNCTIONAL = 1;

	public static final int SUBTYPE_DEPARTMENT_REGIONAL = 2;

	public static final int SUBTYPE_LOCATION_OFFICE = 1;

	public static final int SUBTYPE_LOCATION_REGION = 2;

	public static final int SUBTYPE_LOCATION_REMOTE = 3;

	public static final int SUBTYPE_NONE = 0;

	public static final int[] SUBTYPES_DEPARTMENT = {
		SUBTYPE_DEPARTMENT_FUNCTIONAL, SUBTYPE_DEPARTMENT_REGIONAL
	};

	public static final int[] SUBTYPES_LOCATION = {
		SUBTYPE_LOCATION_OFFICE, SUBTYPE_LOCATION_REGION,
		SUBTYPE_LOCATION_REMOTE
	};

	public static final int[] SUBTYPES_TEAM = {SUBTYPE_NONE};

	public static final String TOOLBAR_LABEL_DEPARTMENT = "departments";

	public static final String TOOLBAR_LABEL_LOCATION = "locations";

	public static final String TOOLBAR_LABEL_TEAM = "teams";

	public static final int TYPE_DEPARTMENT =
		LoopConstants.TYPE_LOOP_DIVISION_DEPARTMENT;

	public static final int TYPE_LOCATION =
		LoopConstants.TYPE_LOOP_DIVISION_LOCATION;

	public static final int TYPE_ROOT = LoopConstants.TYPE_LOOP_DIVISION_ROOT;

	public static final int TYPE_TEAM = LoopConstants.TYPE_LOOP_DIVISION_TEAM;

	public static final int[] TYPES = {
		TYPE_DEPARTMENT, TYPE_LOCATION, TYPE_TEAM
	};

	public static String getTypeLabel(int type) {
		if (type == TYPE_DEPARTMENT) {
			return LABEL_DEPARTMENT;
		}
		else if (type == TYPE_LOCATION) {
			return LABEL_LOCATION;
		}
		else if (type == TYPE_ROOT) {
			return LABEL_ROOT;
		}
		else if (type == TYPE_TEAM) {
			return LABEL_TEAM;
		}

		return StringPool.BLANK;
	}

	public static String getTypeToolbarLabel(int type) {
		if (type == TYPE_DEPARTMENT) {
			return TOOLBAR_LABEL_DEPARTMENT;
		}
		else if (type == TYPE_LOCATION) {
			return TOOLBAR_LABEL_LOCATION;
		}
		else if (type == TYPE_TEAM) {
			return TOOLBAR_LABEL_TEAM;
		}

		return StringPool.BLANK;
	}

	public static String getTypeURL(int type) {
		if (type == LoopConstants.TYPE_LOOP_DIVISION_DEPARTMENT) {
			return LoopConstants.URL_LOOP_DIVISION_DEPARTMENT;
		}
		else if (type == LoopConstants.TYPE_LOOP_DIVISION_LOCATION) {
			return LoopConstants.URL_LOOP_DIVISION_LOCATION;
		}
		else if (type == LoopConstants.TYPE_LOOP_DIVISION_ROOT) {
			return LoopConstants.URL_LOOP_DIVISION_ROOT;
		}
		else if (type == LoopConstants.TYPE_LOOP_DIVISION_TEAM) {
			return LoopConstants.URL_LOOP_DIVISION_TEAM;
		}

		return StringPool.BLANK;
	}

}