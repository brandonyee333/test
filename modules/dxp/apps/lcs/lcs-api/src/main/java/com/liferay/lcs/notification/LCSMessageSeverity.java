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

package com.liferay.lcs.notification;

/**
 * @author     Matija Petanjek
 * @deprecated As of 10.0.0, Moved into osb-lcs-api module
 */
@Deprecated
public enum LCSMessageSeverity {

	DANGER("lcs-message-severity-danger", 3),
	INFO("lcs-message-severity-info", 1),
	WARNING("lcs-message-severity-warning", 2);

	public static LCSMessageSeverity valueOf(int level) {
		for (LCSMessageSeverity lcsMessageSeverity : values()) {
			if (level == lcsMessageSeverity.getLevel()) {
				return lcsMessageSeverity;
			}
		}

		return null;
	}

	public String getLabel() {
		return _label;
	}

	public int getLevel() {
		return _level;
	}

	private LCSMessageSeverity(String label, int level) {
		_label = label;
		_level = level;
	}

	private final String _label;
	private final int _level;

}