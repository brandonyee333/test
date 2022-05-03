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
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.repository.query.Param;

/**
 * @author Inácio Nery
 */
public interface BQMembershipRepository
	extends CustomBQMembershipRepository, Repository<BQMembership, Long> {

	@Cacheable
	public long countByIndividualIdAndIndividualSegmentId(
		Long individualId, Long individualSegmentId);

	@Cacheable
	public long countByIndividualIdInAndIndividualSegmentIdAndStatus(
		List<Long> individualIds, Long individualSegmentId, String status);

	@Cacheable
	public long countByIndividualSegmentId(Long individualSegmentId);

	@Cacheable
	public long countByIndividualSegmentIdAndStatus(
		Long individualSegmentId, String status);

	@CacheEvict(allEntries = true)
	@Modifying
	public void deleteByIndividualSegmentIdIn(
		@Param("individualSegmentIds") List<Long> individualSegmentIds);

	@Cacheable
	public boolean existsByIndividualIdAndIndividualSegmentIdAndStatus(
		Long individualId, Long individualSegmentId, String status);

	@Cacheable
	public BQMembership findByIndividualIdAndIndividualSegmentIdAndStatus(
		Long individualId, Long individualSegmentId, String status);

	@Cacheable
	public List<BQMembership>
		findByIndividualIdAndIndividualSegmentIdInAndStatus(
			Long individualId, List<Long> individualSegmentIds, String status);

	@Cacheable
	public List<BQMembership> findByIndividualIdAndStatus(
		Long individualId, String status);

	@Cacheable
	public List<BQMembership>
		findByIndividualIdInAndIndividualSegmentIdAndStatus(
			List<Long> individualIds, Long individualSegmentId, String status,
			Pageable pageable);

	@Cacheable
	public List<BQMembership> findByIndividualIdInAndStatus(
		List<Long> individualIds, String status);

	@Cacheable
	public List<BQMembership> findByIndividualSegmentIdAndStatus(
		Long individualSegmentId, String status, Pageable pageable);

	@Cacheable
	public List<Long>
		findIndividualIdByIndividualInAndIndividualSegmentIdAndStatus(
			@Param("individualIds") List<Long> individualIds,
			@Param("individualSegmentId") Long individualSegmentId,
			@Param("status") String status);

	@Cacheable
	public List<Long> findIndividualIdByIndividualSegmentIdAndStatus(
		@Param("individualSegmentId") Long individualSegmentId,
		@Param("status") String status);

	@Cacheable
	public List<Long> findIndividualSegmentIdByIndividualIdAndStatus(
		@Param("individualId") Long individualId,
		@Param("status") String status);

	@Cacheable
	public List<Long> findIndividualSegmentIdByIndividualIdInAndStatus(
		@Param("individualIds") List<Long> individualIds,
		@Param("status") String status);

	@Cacheable
	public List<Long> findTop20IndividualSegmentIdByIndividualId(
		@Param("individualId") Long individualId);

}