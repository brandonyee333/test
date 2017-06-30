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