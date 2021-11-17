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

package com.liferay.osb.asah.common.util;

import com.liferay.osb.asah.common.model.AnalyticsEventsMessage;

import java.util.Date;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author Marcos Martins
 */
public class AnalyticsEventUtil {

	public static String generateAnalyticsEventId(
		String dataSourceId, AnalyticsEventsMessage.Event event,
		String projectId, String userId) {

		Date eventDate = event.getEventDate();

		Map<String, String> eventProperties = event.getProperties();

		long eventDateTime = 0;

		if (eventDate != null) {
			eventDateTime = eventDate.getTime();
		}

		return DigestUtils.sha256Hex(
			String.join(
				"#", projectId, dataSourceId, userId, event.getApplicationId(),
				event.getEventId(), String.valueOf(eventProperties.hashCode()),
				String.valueOf(eventDateTime)));
	}

}