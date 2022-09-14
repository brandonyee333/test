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

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

/**
 * @author Inácio Nery
 */
public interface BQMembershipRepository
	extends CustomBQMembershipRepository, Repository<BQMembership, Long> {

	@Cacheable
	public long countBySegmentId(Long segmentId);

	@Cacheable
	public long countBySegmentIdAndStatus(Long segmentId, String status);

	@Cacheable
	public long countBySegmentIdAndStatusAndUserIdIn(
		Long segmentId, String status, List<String> userIds);

	@Cacheable
	public long countBySegmentIdAndUserId(Long segmentId, String userId);

	@Cacheable
	public boolean existsBySegmentIdAndStatusAndUserId(
		Long segmentId, String status, String userId);

	@Cacheable
	public List<BQMembership> findBySegmentIdAndStatus(
		Long segmentId, String status, Pageable pageable);

	@Cacheable
	public BQMembership findBySegmentIdAndStatusAndUserId(
		Long segmentId, String status, String userId);

	@Cacheable
	public List<BQMembership> findBySegmentIdAndStatusAndUserIdIn(
		Long segmentId, String status, List<String> userIds, Pageable pageable);

	@Cacheable
	public List<BQMembership> findBySegmentIdInAndStatusAndUserId(
		List<Long> segmentIds, String status, String userId);

	@Cacheable
	public List<BQMembership> findByStatusAndUserId(
		String status, String userId);

}