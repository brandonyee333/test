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

import com.liferay.osb.asah.common.entity.CustomAssetDashboard;

import java.util.Set;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author André Miranda
 */
public interface CustomAssetDashboardRepository
	extends CustomCustomAssetDashboardRepository,
			Repository<CustomAssetDashboard, String> {

	@CacheEvict(allEntries = true)
	@Modifying
	@Query("DELETE FROM CustomAssetDashboard WHERE channelId IN (:channelIds)")
	public void deleteByChannelIdIn(@Param("channelIds") Set<Long> channelIds);

}