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

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.repository.query.Param;

/**
 * @author Marcellus Tavares
 */
@Primary
public interface MembershipChangeRepository
	extends Repository<MembershipChange, Long> {

	@Cacheable
	public long countMembershipChanges(
		String filterString, Boolean includeAnonymousUsers, Long segmentId);

	@CacheEvict(allEntries = true)
	@Modifying
	public void deleteByIndividualSegmentId(
		@Param("individualSegmentId") Long individualSegmentId);

	@Cacheable
	public Optional<MembershipChange> findByIndividualId(Long individualId);

	public List<MembershipChange>
		searchLastByDateChangedPeriodAndIndividualSegmentId(
			Date dateChangedEnd, Date dateChangedStart,
			boolean includeAnonymousUsers, List<Long> individualSegmentIds);

	@Cacheable
	public List<MembershipChange> searchMembershipChanges(
		String filterString, Boolean includeAnonymousUsers, Long segmentId,
		Pageable pageable);

	@CacheEvict(allEntries = true)
	@Modifying
	public void updateIndividualDeletedByIndividualId(
		@Param("individualDeleted") Boolean individualDeleted,
		@Param("individualId") Long individualId);

	@CacheEvict(allEntries = true)
	@Modifying
	public void updateIndividualNameByIndividualId(
		@Param("individualId") Long individualId,
		@Param("individualName") String individualName);

}