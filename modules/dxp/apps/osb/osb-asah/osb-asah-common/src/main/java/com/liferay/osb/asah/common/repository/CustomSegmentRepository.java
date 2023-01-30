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
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.model.Transformation;
import com.liferay.osb.asah.common.repository.helper.FilterHelper;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;

/**
 * @author Ivica Cardic
 */
public interface CustomSegmentRepository {

	@Cacheable
	public long countPreviewDisabledSegments(
		List<Long> dataSourceFieldMappingIds, Long dataSourceId,
		FilterHelper filterHelper);

	@Cacheable
	public long countSegments(
		FilterHelper filterHelper,
		@Nullable List<Map<String, Long>> segmentIdIdentityCounts);

	@Cacheable
	public long countSegments(List<Long> channelIds, FilterHelper filterHelper);

	@Cacheable
	public List<Transformation> getSegmentTransformations(
		String apply, FilterHelper filterHelper, Pageable pageable,
		@Nullable List<Long> segmentIds);

	@Cacheable
	public List<Segment> searchDynamicSegments(
		FilterHelper filterHelper, @Nullable Boolean includeAnonymousUsers,
		Pageable pageable, Set<Long> segmentIds);

	@Cacheable
	public List<Segment> searchDynamicSegments(
		FilterHelper filterHelper, Pageable pageable);

	@Cacheable
	public List<Segment> searchPreviewDisabledSegments(
		List<Long> dataSourceFieldMappingIds, Long dataSourceId,
		FilterHelper filterHelper, Pageable pageable);

	@Cacheable
	public List<Segment> searchSegments(
		FilterHelper filterHelper,
		@Nullable List<Map<String, Long>> segmentIdIdentityCounts,
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
		String filterString, String state, String status, Pageable pageable);

}