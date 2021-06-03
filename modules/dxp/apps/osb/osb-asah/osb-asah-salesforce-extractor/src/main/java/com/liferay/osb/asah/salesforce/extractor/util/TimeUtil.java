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

package com.liferay.osb.asah.salesforce.extractor.util;

import com.liferay.osb.asah.common.date.DateUtil;

/**
 * @author Brian Wing Shun Chan
 * @author Rachael Koestartyo
 */
public class TimeUtil {

	public static String format(long time) {
		long milliseconds = System.currentTimeMillis() - time;

		long hours = milliseconds / DateUtil.HOUR;
		long minutes = milliseconds / DateUtil.MINUTE % 60;
		long seconds = milliseconds / DateUtil.SECOND % 60;

		return String.format("%03d:%02d:%02d", hours, minutes, seconds);
	}

}