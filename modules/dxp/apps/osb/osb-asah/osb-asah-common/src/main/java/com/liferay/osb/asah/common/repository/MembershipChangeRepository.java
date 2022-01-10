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

import com.liferay.osb.asah.common.entity.MembershipChange;
import com.liferay.osb.asah.common.repository.helper.FilterHelper;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;

/**
 * @author Marcellus Tavares
 */
@Primary
public interface MembershipChangeRepository
	extends Repository<MembershipChange, Long> {

	@Cacheable
	public long countMembershipChanges(
		FilterHelper filterHelper, Boolean includeAnonymousUsers,
		Long segmentId);

	@CacheEvict(allEntries = true)
	@Modifying
	public void deleteByIndividualSegmentIdIn(
		@Param("individualSegmentIds") List<Long> individualSegmentIds);

	@Cacheable
	public Optional<MembershipChange> findByIndividualId(Long individualId);

	@Cacheable
	public List<MembershipChange>
		searchLastByModifiedDateAndIndividualSegmentId(
			@Nullable Date fromModifiedDate, boolean includeAnonymousUsers,
			List<Long> individualSegmentIds, Date toModifiedDate);

	@Cacheable
	public List<MembershipChange> searchMembershipChanges(
		FilterHelper filterHelper, Boolean includeAnonymousUsers,
		Long segmentId, Pageable pageable);

	@CacheEvict(allEntries = true)
	@Modifying
	public void updateIndividualDeletedByIndividualId(
		@Param("individualDeleted") Boolean individualDeleted,
		@Param("individualId") Long individualId);

	@CacheEvict(allEntries = true)
	@Modifying
	public void updateIndividualDeletedByIndividualIdIn(
		@Param("individualDeleted") Boolean individualDeleted,
		@Param("individualIds") List<Long> individualIds);

	@CacheEvict(allEntries = true)
	@Modifying
	public void updateIndividualNameByIndividualId(
		@Param("individualId") Long individualId,
		@Param("individualName") String individualName);

}