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

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;

/**
 * @author Rachael Koestartyo
 */
@Primary
public interface IndividualRepository
	extends PagingAndSortingRepository<Individual, Long> {

	public long
		countByChannelIdsAndLastActivityDatesAndPreviousActivityDatesAndSegmentIdsIn(
			Long channelId, @Nullable Date endLastActivityDate,
			@Nullable Date endPreviousActivityDate, List<Long> segmentIds,
			@Nullable Date startLastActivityDate,
			@Nullable Date startPreviousActivityDate);

	public long countByFieldNamesAndQueryAndSegmentId(
		List<String> fieldNames, @Nullable String query,
		@Nullable Long segmentId);

	public long countByIdAfter(Long id);

	public long countByIdInAndKeywords(
		List<Long> ids, @Nullable String keywords);

	public long countIndividuals(
		@Nullable Long channelId, FilterHelper filterHelper,
		Boolean includeAnonymousUsers, @Nullable Long segmentChannelId,
		@Nullable Long segmentId);

	public long countKnownIndividualsByAnySegmentIds(
		@Param("segmentId") Long segmentId);

	public long countKnownIndividualsByIdIn(@Param("ids") List<Long> ids);

	@Modifying
	public void deleteByIdIn(@Param("ids") List<Long> ids);

	public boolean existsByChannelIdAndFilterStringAndId(
		@Nullable Long channelId, FilterHelper filterHelper, @Nullable Long id);

	public boolean existsByFilterStringAndId(
		FilterHelper filterHelper, @Nullable Long id);

	public List<String> findAccountPKsByChannelIdAndSegmentId(
		@Nullable Long channelId, @Nullable Long segmentId);

	public List<Individual.ActivitiesCount> findActivitiesCounts(
		boolean includeAnonymousUsers, Long segmentId);

	public List<Individual> findAnonymousByCreateDateAndLastActivityDateAndId(
		Date date, @Nullable Long id, int size);

	public Individual findByAssociatedIdNotAndDataSourceIdAndIndividualPK(
		Long associatedId, Long dataSourceId, String fieldName,
		String individualPK);

	public List<Individual> findByChannelIdAndFilterStringAndIdIn(
		@Nullable Long channelId, FilterHelper filterHelper, List<Long> ids);

	public Individual findByDataSourceIdAndIndividualPK(
		Long dataSourceId, String individualPK);

	public Individual findByEmailAddress(String emailAddress);

	public Individual findByEmailAddressHashed(String emailAddressHashed);

	public List<Individual> findByFieldNamesAndQueryAndSegmentId(
		List<String> fieldNames, @Nullable String query,
		@Nullable Long segmentId, Pageable pageable);

	public List<Individual> findByIdAfter(Long id, Pageable pageable);

	public List<Individual> findByIdInAndKeywords(
		List<Long> ids, @Nullable String keywords, Pageable pageable);

	public List<Individual> findBySegmentIds(Long segmentId);

	public List<Long>
		findIdsByAnyChannelIdsAndLastActivityDateAfterAndAnySegmentIds(
			@Nullable Long channelId, @Nullable Date lastActivityDate,
			@Nullable Long segmentId);

	public List<Long> findIdsByAnySegmentIds(
		@Param("segmentId") Long segmentId);

	public Map<Long, Long> findIndividualCounts(
		boolean includeAnonymousUsers, Long segmentId);

	public List<Long> findKnownIndividualIds(
		FilterHelper filterHelper, Long segmentId);

	public List<Distribution> getIndividualDistributions(
		String fieldName, String fieldType, FilterHelper filterHelper,
		Pageable pageable);

	public List<Transformation> getIndividualTransformations(
		String apply, @Nullable Long channelId, FilterHelper filterHelper,
		Boolean includeAnonymousUsers, @Nullable Long segmentChannelId,
		@Nullable Long segmentId, Pageable pageable);

	@Modifying
	public void removeSegmentId(@Param("segmentId") Long segmentId);

	@Modifying
	public void removeSegmentIdByIdIn(
		@Param("ids") Set<Long> ids, @Param("segmentId") Long segmentId);

	@Modifying
	public void removeSegmentIds(@Param("segmentIds") List<Long> segmentIds);

	public List<Individual> searchIndividuals(
		FilterHelper filterHelper, Pageable pageable);

	public List<Individual> searchIndividuals(
		@Nullable Long channelId, FilterHelper filterHelper,
		Boolean includeAnonymousUsers, @Nullable Long segmentChannelId,
		@Nullable Long segmentId, Pageable pageable);

	public List<Individual> searchIndividuals(
		@Nullable Long channelId, FilterHelper filterHelper, List<Long> ids,
		Boolean includeAnonymousUsers);

	public List<Individual> searchIndividuals(
		@Nullable Long channelId, FilterHelper filterHelper, @Nullable Long id,
		Boolean includeAnonymousUsers, int size);

	public List<Individual> searchIndividuals(
		Long dataSourceId, @Nullable Long id, int size);

	@Modifying
	public void updateAssociatedIds(String fieldName, Set<Long> ids, Long id);

	@Modifying
	public void updateDataSourceNameByDataSourceId(
		Long dataSourceId, String dataSourceName);

}