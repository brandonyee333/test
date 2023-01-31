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
import java.util.Map;

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
	public void deleteByIndividualIdAndSegmentId(
		String individualId, Long segmentId);

	@CacheEvict(allEntries = true)
	@Modifying
	public void deleteBySegmentIdIn(List<Long> segmentIds);

	@Cacheable
	public List<String> findIdentityIdBySegmentIdAndStatus(
		Long segmentId, String status);

	@Cacheable
	public List<String> findIdentityIdBySegmentIdIn(
		List<Long> segmentIds, int max, int min, boolean ascending);

	@Cacheable
	public List<Long> findSegmentIdByIdentityIdAndStatus(
		String identityId, String status);

	@Cacheable
	public List<Long> findSegmentIdByIdentityIdInAndStatus(
		List<String> identityIds, String status);

	@Cacheable
	public List<Map<String, Long>>
		findSegmentIdIdentitiesCountByIdentityIdAndStatusAnd(
			String identityId, String status);

	@Cacheable
	public List<Long> findTop20SegmentIdByIdentityId(String identityId);

	@Cacheable
	public List<BQMembership> searchBQMemberships(
		@Nullable Long id, Long segmentId, int size, String status);

	@CacheEvict(allEntries = true)
	@Modifying
	public void updateBQMemberships(String filterString, Long segmentId);

}