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

package com.liferay.osb.model.impl;

import com.liferay.osb.model.SupportResponseConstants;

/**
 * @author Brian Wing Shun Chan
 * @author Amos Fong
 */
public class SupportResponseImpl extends SupportResponseBaseImpl {

	public SupportResponseImpl() {
	}

	public int getSeverityResolution(int severityLevel) {
		if (severityLevel == SupportResponseConstants.SEVERITY_CRITICAL) {
			return getSeverity1Resolution();
		}
		else if (severityLevel == SupportResponseConstants.SEVERITY_MAJOR) {
			return getSeverity2Resolution();
		}
		else if (severityLevel == SupportResponseConstants.SEVERITY_MINOR) {
			return getSeverity3Resolution();
		}
		else {
			return 0;
		}
	}

	public int getSeverityResponse(int severityLevel) {
		if (severityLevel == SupportResponseConstants.SEVERITY_CRITICAL) {
			return getSeverity1Response();
		}
		else if (severityLevel == SupportResponseConstants.SEVERITY_MAJOR) {
			return getSeverity2Response();
		}
		else if (severityLevel == SupportResponseConstants.SEVERITY_MINOR) {
			return getSeverity3Response();
		}
		else {
			return 0;
		}
	}

	public String getSupportLevelLabel() {
		return SupportResponseConstants.getSupportLevelLabel(getSupportLevel());
	}

	public boolean isPlatinumLevel() {
		if (getSupportLevel() ==
				SupportResponseConstants.SUPPORT_LEVEL_PLATINUM) {

			return true;
		}

		String name = getName();

		if (name.indexOf("Platinum") > -1) {
			return true;
		}

		return false;
	}

}