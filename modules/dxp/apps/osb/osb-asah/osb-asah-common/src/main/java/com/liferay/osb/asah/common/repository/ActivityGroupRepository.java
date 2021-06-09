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

package com.liferay.osb.asah.common.repository;

import com.liferay.osb.asah.common.entity.ActivityGroup;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * @author Inácio Nery
 */
public interface ActivityGroupRepository
	extends CrudRepository<ActivityGroup, Long> {

	public long countActivityGroups(String filterString);

	@Modifying
	public void deleteByChannelIdIn(@Param("channelIds") Set<Long> channelIds);

	public ActivityGroup
		findByActivityTypeAndChannelIdAndDataSourceIdAndDayDateAndUserId(
			String activityType, Long channelId, Long dataSourceId,
			Date dayDate, String userId);

	public List<ActivityGroup> searchActivityGroups(
		String filterString, Pageable pageable);

	@Modifying
	public boolean updateOwnerId(
		@Param("ownerId") Long ownerId, @Param("userId") String userId);

}