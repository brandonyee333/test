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

import com.liferay.osb.asah.common.entity.Individual;
import com.liferay.osb.asah.common.model.Distribution;
import com.liferay.osb.asah.common.model.Transformation;
import com.liferay.osb.asah.common.repository.helper.FilterHelper;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;

/**
 * @author Rachael Koestartyo
 */
@Primary
public interface IndividualRepository extends Repository<Individual, Long> {

	@Cacheable
	public long countByFieldNamesAndQueryAndSegmentId(
		List<String> fieldNames, @Nullable String query,
		@Nullable Long segmentId);

	@Cacheable
	public long countByIdAfter(Long individualId);

	@Cacheable
	public long countByIdsInAndKeywords(
		List<Long> individualIds, @Nullable String keywords);

	@Cacheable
	public long countIndividuals(
		@Nullable Long channelId, FilterHelper filterHelper,
		Boolean includeAnonymousUsers, @Nullable Long segmentChannelId,
		@Nullable Long segmentId);

	@Cacheable
	public long countKnownIndividualsByAnySegmentIds(
		@Param("segmentId") Long segmentId);

	@Cacheable
	public long countKnownIndividualsByIdIn(@Param("ids") List<Long> ids);

	@Cacheable
	public boolean existsByChannelIdAndFilterStringAndId(
		@Nullable Long channelId, FilterHelper filterHelper,
		@Nullable Long individualId);

	@Cacheable
	public boolean
		existsByChannelIdAndFilterStringAndIncludeAnonymousUsersAndId(
			@Nullable Long channelId, FilterHelper filterHelper,
			Boolean includeAnonymousUsers, @Nullable Long individualId);

	@Cacheable
	public boolean existsByFilterStringAndId(
		FilterHelper filterHelper, @Nullable Long individualId);

	@Cacheable
	public List<String> findAccountPKsByChannelIdAndSegmentId(
		@Nullable Long channelId, @Nullable Long segmentId);

	@Cacheable
	public List<Individual.ActivitiesCount> findActivitiesCounts(
		boolean includeAnonymousUsers, Long segmentId);

	@Cacheable
	public List<Individual> findAnonymousByCreateDateAndLastActivityDate(
		String dateString, Pageable pageable);

	@Cacheable
	public Individual findByAssociatedIdNotAndDataSourceIdAndIndividualPK(
		Long associatedId, Long dataSourceId, String fieldName,
		String individualPK);

	@Cacheable
	public List<Individual> findByDataSourceId(
		Long dataSourceId, Pageable pageable);

	@Cacheable
	public Individual findByDataSourceIdAndIndividualPK(
		Long dataSourceId, String individualPK);

	@Cacheable
	public Individual findByEmailAddress(String emailAddress);

	@Cacheable
	public Individual findByEmailAddressHashed(String emailAddressHashed);

	@Cacheable
	public List<Individual> findByFieldNamesAndQueryAndSegmentId(
		List<String> fieldNames, @Nullable String query,
		@Nullable Long segmentId, Pageable pageable);

	@Cacheable
	public List<Individual> findByIdAfter(Long individualId, Pageable pageable);

	@Cacheable
	public List<Individual> findByIdsInAndKeywords(
		List<Long> individualIds, @Nullable String keywords, Pageable pageable);

	@Cacheable
	public List<Individual> findBySegmentIds(Long segmentId);

	@Cacheable
	public List<Long> findIdsByAnySegmentIds(
		@Param("segmentId") Long segmentId);

	@Cacheable
	public Map<Long, Long> findIndividualCounts(
		boolean includeAnonymousUsers, Long segmentId);

	@Cacheable
	public List<Long> findKnownIndividualIds(
		FilterHelper filterHelper, Long segmentId);

	@Cacheable
	public List<Distribution> getIndividualDistributions(
		String fieldName, String fieldType, FilterHelper filterHelper,
		Pageable pageable);

	@Cacheable
	public List<Transformation> getIndividualTransformations(
		String apply, @Nullable Long channelId, FilterHelper filterHelper,
		Boolean includeAnonymousUsers, @Nullable Long segmentChannelId,
		@Nullable Long segmentId, Pageable pageable);

	@Cacheable
	public List<Individual> searchIndividuals(
		FilterHelper filterHelper, Pageable pageable);

	@Cacheable
	public List<Individual> searchIndividuals(
		@Nullable Long channelId, FilterHelper filterHelper,
		Boolean includeAnonymousUsers, @Nullable Long segmentChannelId,
		@Nullable Long segmentId, Pageable pageable);

	@CacheEvict(allEntries = true)
	@Modifying
	public void updateAssociatedIds(
		String fieldName, Set<Long> ids, Long individualId);

}