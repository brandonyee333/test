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

import com.liferay.osb.asah.common.entity.Interest;
import com.liferay.osb.asah.common.model.Distribution;
import com.liferay.osb.asah.common.repository.helper.FilterHelper;

import java.util.Date;
import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;

/**
 * @author Robson Pastor
 */
@Primary
public interface InterestRepository extends Repository<Interest, Long> {

	@Cacheable
	public long countByFilterStringAndScoreGreaterThanEqual(
		FilterHelper filterHelper, Double score);

	@Cacheable
	public long countByOwnerIdAndOwnerType(Long ownerId, String ownerType);

	@CacheEvict(allEntries = true)
	@Modifying
	public void deleteByNameAndRecordedDateGreaterThanEqual(
		@Param("name") String name, @Param("recordedDate") Date recordedDate);

	@CacheEvict(allEntries = true)
	@Modifying
	public void deleteByOwnerIdAndOwnerType(
		@Param("ownerId") Long ownerId, @Param("ownerType") String ownerType);

	@CacheEvict(allEntries = true)
	@Modifying
	public void deleteByOwnerTypeAndRecordedDateLessThanEqual(
		@Param("ownerType") String ownerType,
		@Param("recordedDate") Date recordedDate);

	@Cacheable
	public List<Interest> findByFilterStringAndScoreGreaterThanEqual(
		FilterHelper filterHelper, Double score, Pageable pageable);

	@Cacheable
	public List<Interest>
		findByNameAndOwnerIdAndOwnerTypeAndRecordedDateBetween(
			String name, Long ownerId, String ownerType, Date fromRecordedDate,
			Date toRecordedDate);

	@Cacheable
	public List<Interest> findByOwnerIdAndOwnerType(
		Long ownerId, String ownerType, Pageable pageable);

	@Cacheable
	public List<Interest> findByOwnerTypeAndRecordedDate(
		@Nullable Long interestId, @Nullable String ownerType,
		@Nullable Date recordedDate, int size);

	@Cacheable
	public Interest getByNameAndOwnerIdAndOwnerTypeAndRecordedDate(
		String name, Long ownerId, String ownerType, Date recordedDate);

	@Cacheable
	public List<Distribution> getInterestDistributions(
		@Nullable String keyword, @Nullable List<Long> ownerIds,
		@Nullable String ownerType, @Nullable Date recordedDate,
		@Nullable Double score, Pageable pageable);

	@Cacheable
	public List<String> getTopNamesByOwnerIdAndOwnerType(
		Long ownerId, String ownerType, int size);

}