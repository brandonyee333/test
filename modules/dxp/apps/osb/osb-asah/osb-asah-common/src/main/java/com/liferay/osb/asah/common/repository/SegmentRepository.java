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

import com.liferay.osb.asah.common.model.DXPEntityType;
import com.liferay.osb.asah.common.model.Segment;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Inácio Nery
 */
@ConditionalOnProperty(
	havingValue = "true", value = "osb.asah.postgresql.enabled"
)
@Repository
public interface SegmentRepository extends CrudRepository<Segment, Long> {

	public long countByIdAfter(Long id);

	public long countPreviewDisabledSegments(
		Long dataSourceId, String filterString);

	public long countSegments(Long dataSourceId, String filterString);

	@Modifying
	public void deleteByChannelIdIn(@Param("channelIds") Set<Long> channelIds);

	public List<Segment> findAll(Pageable pageable);

	public List<Segment> findByIdAfter(Long id, Pageable pageable);

	public Optional<Segment> findByNameAndStatus(String name, String status);

	public List<Segment> findByReferencedAssetDataSourceIdsAndStateNotAndType(
		@Param("referencedAssetDataSourceId") Long referencedAssetDataSourceId,
		@Param("state") String state, @Param("type") Segment.Type type);

	public List<Segment>
		findByReferencedAssetDataSourceIdsOrReferencedFieldMappingIdsInAndStateNotAndType(
			@Param("referencedAssetDataSourceId")
				Long referencedAssetDataSourceId,
			@Param("referencedFieldMappingIds")
				List<Long> referencedFieldMappingIds,
			@Param("state") String state, @Param("type") Segment.Type type);

	public List<Segment> findByReferencedFieldMappingIdsInAndStateNotAndType(
		@Param("referencedFieldMappingIds")
			List<Long> referencedFieldMappingIds,
		@Param("state") String state, @Param("type") Segment.Type type);

	public List<Segment> findByStateNotAndType(String state, Segment.Type type);

	public List<Long> findIdByNameInAndStatus(
		@Param("names") List<String> names, @Param("status") String status);

	public List<String> findNameByChannelIdAndIdInAndStatus(
		@Param("channelId") Long channelId, @Param("ids") List<Long> ids,
		@Param("status") String status);

	public List<String> findNameByIdInAndStatus(
		@Param("ids") List<Long> ids, @Param("status") String status);

	public List<Long> findReferencedAssetIds();

	public List<Segment> searchPreviewDisabledSegments(
		Long dataSourceId, String filterString, Pageable pageable);

	public List<Segment> searchSegments(
		DXPEntityType dxpEntityType, Long id, String state, Segment.Type type);

	public List<Segment> searchSegments(
		Long dataSourceId, String filterString, Pageable pageable);

	public List<Segment> searchSegments(
		String filter, String state, String status, Pageable pageable);

	@Modifying
	public void updateActivitiesCountAndRemoveLastActivityDate(
		@Param("activitiesCount") Long activitiesCount);

}