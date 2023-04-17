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

import java.time.ZoneId;

import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.lang.Nullable;

/**
 * @author Ivica Cardic
 */
public interface CustomBQMembershipRepository {

	@Cacheable
	public long countActiveMembersBySegmentId(
		@Nullable Boolean includeAnonymousUsers, Long segmentId, ZoneId zoneId);

	@Cacheable
	public long countByIdentityIdAndSegmentId(
		String identityId, Long segmentId);

	@Cacheable
	public long countByIdentityIdInAndSegmentIdAndStatus(
		List<String> identityIds, Long segmentId, String status);

	@Cacheable
	public long countBySegmentId(Long segmentId);

	@Cacheable
	public long countBySegmentIdAndStatus(Long segmentId, String status);

	@CacheEvict(allEntries = true)
	@Modifying
	public void deleteByIndividualIdAndSegmentId(
		String individualId, Long segmentId);

	@CacheEvict(allEntries = true)
	@Modifying
	public void deleteBySegmentIdIn(List<Long> segmentIds);

	@Cacheable
	public boolean existsByIdentityIdAndSegmentIdAndStatus(
		String identityId, Long segmentId, String status);

	public List<BQMembership> findAll();

	@Cacheable
	public List<BQMembership> findByIdentityIdAndStatus(
		String identityId, String status);

	@Cacheable
	public List<BQMembership> findByIdentityIdInAndSegmentIdAndStatus(
		List<String> identityIds, Long segmentId, String status,
		Pageable pageable);

	@Cacheable
	public List<BQMembership> findByIndividualIdAndSegmentIdInAndStatus(
		String individualId, List<Long> segmentIds, String status);

	@Cacheable
	public List<BQMembership> findBySegmentIdAndStatus(
		Long segmentId, String status, Pageable pageable);

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
	public List<Long> findSegmentIdByIndividualId(String individualId);

	@Cacheable
	public List<Map<String, Long>>
		findSegmentIdIdentitiesCountByIndividualIdAndStatus(
			String individualId, String status);

	@Cacheable
	public List<Long> findTop20SegmentIdByIndividualId(String individualId);

	@CacheEvict(allEntries = true)
	@Modifying
	public BQMembership insert(BQMembership bqMembership);

	@CacheEvict(allEntries = true)
	@Modifying
	public void insertAll(List<BQMembership> bqMemberships);

	@CacheEvict(allEntries = true)
	@Modifying
	public void updateBQMemberships(
		String filterString, Boolean includeAnonymousUsers, Long segmentId);

}