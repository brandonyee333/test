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

import com.liferay.osb.asah.common.entity.DXPEntity;
import com.liferay.osb.asah.common.entity.Individual;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.model.Transformation;
import com.liferay.osb.asah.common.repository.helper.FilterHelper;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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
public interface SegmentRepository extends Repository<Segment, Long> {

	@Cacheable
	public long countByIdAfter(Long id);

	@Cacheable
	public long countPreviewDisabledSegments(
		List<Long> dataSourceFieldMappingIds, Long dataSourceId,
		FilterHelper filterHelper);

	@Cacheable
	public long countSegments(
		FilterHelper filterHelper, @Nullable List<Long> segmentIds);

	@Cacheable
	public long countSegments(List<Long> channelIds, FilterHelper filterHelper);

	@CacheEvict(allEntries = true)
	@Modifying
	public void deleteByChannelIdIn(@Param("channelIds") Set<Long> channelIds);

	@Cacheable
	public boolean existsByName(String name);

	@Cacheable
	public List<Segment> findByChannelIdIn(
		@Param("channelIds") Set<Long> channelIds, Pageable pageable);

	@Cacheable
	public List<Segment> findByChannelIdIsNotNullOrNameStartingWith(
		String name, Pageable pageable);

	@Cacheable
	public List<Segment> findByIdAfter(Long id, Pageable pageable);

	@Cacheable
	public Optional<Segment> findByNameAndStatus(String name, String status);

	@Cacheable
	public List<Segment> findByReferencedAssetDataSourceIdsAndStateNotAndType(
		@Param("referencedAssetDataSourceId") Long referencedAssetDataSourceId,
		@Param("state") String state, @Param("type") Segment.Type type);

	@Cacheable
	public List<Segment>
		findByReferencedAssetDataSourceIdsOrReferencedFieldMappingIdInAndStateNotAndType(
			@Param("referencedAssetDataSourceId") Long
				referencedAssetDataSourceId,
			@Param("referencedFieldMappingIds") List<Long>
				referencedFieldMappingIds,
			@Param("state") String state, @Param("type") Segment.Type type);

	@Cacheable
	public List<Segment> findByReferencedFieldMappingIdInAndStateNotAndType(
		@Param("referencedFieldMappingIds") List<Long>
			referencedFieldMappingIds,
		@Param("state") String state, @Param("type") Segment.Type type);

	@Cacheable
	public List<Segment> findByStateNotAndType(String state, Segment.Type type);

	@Cacheable
	public List<Long> findIdByNameInAndStatus(
		@Param("names") List<String> names, @Param("status") String status);

	@Cacheable
	public List<String> findNameByChannelIdAndIdInAndStatus(
		@Param("channelId") Long channelId, @Param("ids") List<Long> ids,
		@Param("status") String status);

	@Cacheable
	public List<String> findNameByIdInAndStatus(
		@Param("ids") List<Long> ids, @Param("status") String status);

	@Cacheable
	public List<Long> findReferencedAssetIds();

	@Cacheable
	public List<Transformation> getSegmentTransformations(
		String apply, FilterHelper filterHelper, Pageable pageable,
		@Nullable List<Long> segmentIds);

	@Cacheable
	public List<Segment> searchDynamicSegments(
		FilterHelper filterHelper, Pageable pageable);

	@Cacheable
	public List<Segment> searchDynamicSegments(
		Set<Individual.DataSourceAccountPK> dataSourceAccountPKs,
		FilterHelper filterHelper, @Nullable Boolean includeAnonymousUsers,
		Pageable pageable, Set<Long> segmentIds);

	@Cacheable
	public List<Segment> searchPreviewDisabledSegments(
		List<Long> dataSourceFieldMappingIds, Long dataSourceId,
		FilterHelper filterHelper, Pageable pageable);

	@Cacheable
	public List<Segment> searchSegments(
		FilterHelper filterHelper, @Nullable List<Long> segmentIds,
		Pageable pageable);

	@Cacheable
	public List<Segment> searchSegments(
		List<Long> channelIds, FilterHelper filterHelper, Pageable pageable);

	@Cacheable
	public List<Segment> searchSegments(
		Long dxpEntityId, DXPEntity.Type dxpEntityType, String state,
		Segment.Type type);

	@Cacheable
	public List<Segment> searchSegments(
		String filter, String state, String status, Pageable pageable);

	@CacheEvict(allEntries = true)
	@Modifying
	public void updateActivitiesCountAndRemoveLastActivityDate(
		@Param("activitiesCount") Long activitiesCount);

}