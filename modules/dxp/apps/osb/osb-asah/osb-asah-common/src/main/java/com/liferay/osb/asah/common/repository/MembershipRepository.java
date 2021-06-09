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

import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * @author Inácio Nery
 */
public interface MembershipRepository extends CrudRepository<Membership, Long> {

	public long countByIndividualIdInAndIndividualSegmentIdAndStatus(
		List<Long> individualIds, Long individualSegmentId, String status);

	public long countByIndividualSegmentIdAndStatus(
		Long individualSegmentId, String status);

	@Modifying
	public void deleteByIndividualSegmentId(
		@Param("individualSegmentId") Long individualSegmentId);

	public boolean existsByIndividualIdAndIndividualSegmentIdAndStatus(
		Long individualId, Long individualSegmentId, String status);

	public Membership findByIndividualIdAndIndividualSegmentIdAndStatus(
		Long individualId, Long individualSegmentId, String status);

	public List<Membership> findByIndividualIdAndIndividualSegmentIdInAndStatus(
		Long individualId, List<Long> individualSegmentIds, String status);

	public List<Membership> findByIndividualIdAndStatus(
		Long individualId, String status);

	public List<Membership> findByIndividualIdInAndIndividualSegmentIdAndStatus(
		List<Long> individualIds, Long individualSegmentId, String status,
		Pageable pageable);

	public List<Membership> findByIndividualSegmentIdAndStatus(
		Long individualSegmentId, String status);

	public List<Membership> findByIndividualSegmentIdAndStatus(
		Long individualSegmentId, String status, Pageable pageable);

	public List<Long> findIndividualIdByIndividualSegmentIdAndStatus(
		@Param("individualSegmentId") Long individualSegmentId,
		@Param("status") String status);

	public List<Long> findIndividualIdByIndividualSegmentIdIn(
		List<Long> individualSegmentIds, int max, int min, boolean ascending);

	public List<Long> findIndividualSegmentIdByIndividualIdAndStatus(
		@Param("individualId") Long individualId,
		@Param("status") String status);

	public List<Long> findIndividualSegmentIdByIndividualIdInAndStatus(
		@Param("individualIds") List<Long> individualIds,
		@Param("status") String status);

	public List<Long> findTop20IndividualSegmentIdByIndividualId(
		@Param("individualId") Long individualId);

}