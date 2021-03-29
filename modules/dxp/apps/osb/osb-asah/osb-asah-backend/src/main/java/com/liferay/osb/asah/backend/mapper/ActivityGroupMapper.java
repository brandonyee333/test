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

package com.liferay.osb.asah.common.mapper;

import com.liferay.osb.asah.common.dto.ActivityGroupDTO;
import com.liferay.osb.asah.common.model.ActivityGroup;

import org.json.JSONObject;

import org.springframework.stereotype.Component;

/**
 * @author Inácio Nery
 */
@Component
public class ActivityGroupMapper
	extends Mapper<ActivityGroup, ActivityGroupDTO> {

	@Override
	protected ActivityGroupDTO toDTO(ActivityGroup channel) {
		return new ActivityGroupDTO(channel);
	}

	@Override
	protected ActivityGroup toModel(JSONObject jsonObject) {
		ActivityGroup activityGroup = new ActivityGroup();

		if (jsonObject.has("activityType") &&
			!jsonObject.isNull("activityType")) {

			activityGroup.setActivityType(jsonObject.getString("activityType"));
		}

		if (jsonObject.has("channelId") && !jsonObject.isNull("channelId")) {
			activityGroup.setChannelId(jsonObject.getLong("channelId"));
		}

		if (jsonObject.has("dataSourceId") &&
			!jsonObject.isNull("dataSourceId")) {

			activityGroup.setDataSourceId(jsonObject.getLong("dataSourceId"));
		}

		if (jsonObject.has("day") && !jsonObject.isNull("day")) {
			activityGroup.setDayDate(toUTCDate(jsonObject.get("day")));
		}
		else if (jsonObject.has("dayDate") && !jsonObject.isNull("dayDate")) {
			activityGroup.setDayDate(toUTCDate(jsonObject.get("dayDate")));
		}

		if (jsonObject.has("endDate") && !jsonObject.isNull("endDate")) {
			activityGroup.setEndDate(toUTCDate(jsonObject.get("endDate")));
		}
		else if (jsonObject.has("endTime") && !jsonObject.isNull("endTime")) {
			activityGroup.setEndDate(toUTCDate(jsonObject.get("endTime")));
		}

		if (jsonObject.has("endLocalDate") &&
			!jsonObject.isNull("endLocalDate")) {

			activityGroup.setEndLocalDate(
				toUTCDate(jsonObject.get("endLocalDate")));
		}
		else if (jsonObject.has("endTimeLocal") &&
				 !jsonObject.isNull("endTimeLocal")) {

			activityGroup.setEndLocalDate(
				toUTCDate(jsonObject.get("endTimeLocal")));
		}

		if (jsonObject.has("id") && !jsonObject.isNull("id")) {
			activityGroup.setId(jsonObject.getLong("id"));
		}

		if (jsonObject.has("ownerId") && !jsonObject.isNull("ownerId")) {
			activityGroup.setOwnerId(jsonObject.getLong("ownerId"));
		}

		if (jsonObject.has("startDate") && !jsonObject.isNull("startDate")) {
			activityGroup.setStartDate(toUTCDate(jsonObject.get("startDate")));
		}
		else if (jsonObject.has("startTime") &&
				 !jsonObject.isNull("startTime")) {

			activityGroup.setStartDate(toUTCDate(jsonObject.get("startTime")));
		}

		if (jsonObject.has("startLocalDate") &&
			!jsonObject.isNull("startLocalDate")) {

			activityGroup.setStartLocalDate(
				toUTCDate(jsonObject.get("startLocalDate")));
		}
		else if (jsonObject.has("startTimeLocal") &&
				 !jsonObject.isNull("startTimeLocal")) {

			activityGroup.setStartLocalDate(
				toUTCDate(jsonObject.get("startTimeLocal")));
		}

		if (jsonObject.has("userId") && !jsonObject.isNull("userId")) {
			activityGroup.setUserId(jsonObject.getString("userId"));
		}

		return activityGroup;
	}

}