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

import com.liferay.osb.asah.common.entity.BQMembership;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.lang.Nullable;

/**
 * @author Ivica Cardic
 */
public interface CustomBQMembershipRepository {

	@CacheEvict(allEntries = true)
	@Modifying
	public void deleteBySegmentIdIn(List<Long> segmentIds);

	@Cacheable
	public List<Long> findSegmentIdByStatusAndUserId(
		String status, String userId);

	@Cacheable
	public List<Long> findSegmentIdByStatusAndUserIdIn(
		String status, List<String> userIds);

	@Cacheable
	public List<Long> findTop20SegmentIdByUserId(String userId);

	@Cacheable
	public List<String> findUserIdBySegmentIdAndStatus(
		Long segmentId, String status);

	@Cacheable
	public List<String> findUserIdBySegmentIdIn(
		List<Long> segmentIds, int max, int min, boolean ascending);

	@Cacheable
	public List<BQMembership> searchBQMemberships(
		@Nullable Long id, Long segmentId, int size, String status);

}