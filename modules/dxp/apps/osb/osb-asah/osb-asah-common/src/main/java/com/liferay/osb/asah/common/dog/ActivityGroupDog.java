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

package com.liferay.osb.asah.common.dog;

import com.liferay.osb.asah.common.entity.ActivityGroup;
import com.liferay.osb.asah.common.repository.ActivityGroupRepository;
import com.liferay.osb.asah.common.spring.http.exception.OSBAsahException;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * @author Inácio Nery
 */
@Component
public class ActivityGroupDog {

	public ActivityGroup addActivityGroup(
		String activityType, Long channelId, Long dataSourceId, Date dayDate,
		Date endDate, LocalDateTime endLocalDate, Long ownerId, Date startDate,
		LocalDateTime startLocalDate, String userId) {

		ActivityGroup activityGroup = new ActivityGroup();

		activityGroup.setActivityType(activityType);
		activityGroup.setChannelId(channelId);
		activityGroup.setDataSourceId(dataSourceId);
		activityGroup.setDayDate(dayDate);
		activityGroup.setEndDate(endDate);
		activityGroup.setEndLocalDateTime(endLocalDate);
		activityGroup.setOwnerId(ownerId);
		activityGroup.setStartDate(startDate);
		activityGroup.setStartLocalDateTime(startLocalDate);
		activityGroup.setUserId(userId);

		return _activityGroupRepository.save(activityGroup);
	}

	public void deleteActivityGroups(Set<Long> channelIds) {
		_activityGroupRepository.deleteByChannelIdIn(channelIds);
	}

	public ActivityGroup fetchActivityGroup(
		String activityType, Long channelId, Long dataSourceId, Date dayDate,
		String userId) {

		return _activityGroupRepository.
			findByActivityTypeAndChannelIdAndDataSourceIdAndDayDateAndUserId(
				activityType, channelId, dataSourceId, dayDate, userId);
	}

	public ActivityGroup getActivityGroup(Long activityGroupId) {
		Optional<ActivityGroup> activityGroupOptional =
			_activityGroupRepository.findById(activityGroupId);

		return activityGroupOptional.orElseThrow(
			() -> new OSBAsahException(
				HttpStatus.BAD_REQUEST,
				"There is no activity group with ID " + activityGroupId));
	}

	public Page<ActivityGroup> searchActivityGroupsPage(
		String filterString, int page, int size, String[] sorts) {

		PageRequest pageRequest = PageRequest.of(page, size, _getSort(sorts));

		return PageableExecutionUtils.getPage(
			_activityGroupRepository.searchActivityGroups(
				filterString, pageRequest),
			pageRequest,
			() -> _activityGroupRepository.countActivityGroups(filterString));
	}

	public boolean updateActivityGroup(Long ownerId, String userId) {
		return _activityGroupRepository.updateOwnerId(ownerId, userId);
	}

	public ActivityGroup updatedActivityGroup(
		Long activityGroupId, Date endDate, LocalDateTime endLocalDateTime) {

		ActivityGroup activityGroup = getActivityGroup(activityGroupId);

		activityGroup.setEndDate(endDate);
		activityGroup.setEndLocalDateTime(endLocalDateTime);

		return _activityGroupRepository.save(activityGroup);
	}

	private Sort _getSort(String[] sorts) {
		if (ArrayUtils.isEmpty(sorts)) {
			return Sort.by(Sort.Order.desc("id"));
		}

		List<Sort.Order> orders = new ArrayList<>();

		for (int i = 0; i < (sorts.length - 1); i = i + 2) {
			String sort = sorts[i];

			if (sort.contains("startTime")) {
				sort = "startDate";
			}

			if (Objects.equals(sorts[i + 1], "asc")) {
				orders.add(Sort.Order.asc(sort));
			}
			else {
				orders.add(Sort.Order.desc(sort));
			}
		}

		return Sort.by(orders);
	}

	@Autowired
	private ActivityGroupRepository _activityGroupRepository;

}