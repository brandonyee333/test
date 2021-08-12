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

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Pageable;

/**
 * @author André Miranda
 */
@Primary
public interface CustomAssetDashboardRepository
	extends OSBAsahRepository<CustomAssetDashboard, String> {

	@Cacheable
	public long countCustomAssetDashboards(Long channelId, String keywords);

	@Cacheable
	public List<CustomAssetDashboard> searchCustomAssetDashboards(
		Long channelId, String keywords, Pageable pageable);

}