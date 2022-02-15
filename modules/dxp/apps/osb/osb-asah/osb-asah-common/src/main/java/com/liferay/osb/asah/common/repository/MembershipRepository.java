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

import com.liferay.osb.asah.common.entity.Membership;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;

/**
 * @author Inácio Nery
 */
@Primary
public interface MembershipRepository extends Repository<Membership, Long> {

	@Cacheable
	public long countByIndividualIdInAndIndividualSegmentIdAndStatus(
		List<Long> individualIds, Long individualSegmentId, String status);

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
	public Membership findByIndividualIdAndIndividualSegmentIdAndStatus(
		Long individualId, Long individualSegmentId, String status);

	@Cacheable
	public List<Membership> findByIndividualIdAndIndividualSegmentIdInAndStatus(
		Long individualId, List<Long> individualSegmentIds, String status);

	@Cacheable
	public List<Membership> findByIndividualIdAndStatus(
		Long individualId, String status);

	@Cacheable
	public List<Membership> findByIndividualIdInAndIndividualSegmentIdAndStatus(
		List<Long> individualIds, Long individualSegmentId, String status,
		Pageable pageable);

	@Cacheable
	public List<Membership> findByIndividualIdInAndStatus(
		List<Long> individualIds, String status);

	@Cacheable
	public List<Membership> findByIndividualSegmentIdAndStatus(
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
	public List<Long> findIndividualIdByIndividualSegmentIdIn(
		List<Long> individualSegmentIds, int max, int min, boolean ascending);

	@Cacheable
	public List<Long> findIndividualIdByIndividualSegmentIdIn(
		Long individualId, List<Long> individualSegmentIds, int max, int min,
		boolean ascending);

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

	@Cacheable
	public List<Membership> searchMemberships(
		@Nullable Long id, Long individualSegmentId, int size, String status);

}