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

/**
 * @author Sherry Yang
 */
public class LoopParticipantAssignmentConstants {

	public static final String LABEL_INHERITED = "inherited";

	public static final String LABEL_LEAD = "lead";

	public static final String LABEL_MEMBER = "member";

	public static final String LABEL_SPONSOR = "sponsor";

	public static final int TYPE_INHERITED = 4;

	public static final int TYPE_LEAD = 1;

	public static final int TYPE_MEMBER = 2;

	public static final int TYPE_SPONSOR = 3;

	public static String getTypeLabel(int type) {
		if (type == TYPE_INHERITED) {
			return LABEL_INHERITED;
		}
		else if (type == TYPE_LEAD) {
			return LABEL_LEAD;
		}
		else if (type == TYPE_MEMBER) {
			return LABEL_MEMBER;
		}
		else {
			return LABEL_SPONSOR;
		}
	}

}