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

import com.liferay.osb.asah.common.entity.FieldMapping;
import com.liferay.osb.asah.common.model.Transformation;

import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;

/**
 * @author Rachael Koestartyo
 */
@Primary
public interface FieldMappingRepository extends Repository<FieldMapping, Long> {

	@Cacheable
	public long countByFieldNameAndOwnerType(
		String fieldName, String ownerType);

	@Cacheable
	public long countFieldMappings(@Nullable String filterString);

	@Cacheable
	public long countIndividualFieldMappings(@Nullable String name);

	@Cacheable
	public boolean existsByContextAndFieldNameAndOwnerType(
		String context, String fieldName, String ownerType);

	@Cacheable
	public List<FieldMapping>
		findByContextAndDataSourceIdAndFieldNameAndOwnerType(
			@Param("context") String context,
			@Param("dataSourceId") Long dataSourceId,
			@Param("fieldName") String fieldName,
			@Param("ownerType") String ownerType);

	@Cacheable
	public List<FieldMapping> findByContextAndDataSourceIdAndOwnerType(
		@Param("context") String context,
		@Param("dataSourceId") Long dataSourceId,
		@Param("ownerType") String ownerType);

	@Cacheable
	public Optional<FieldMapping>
		findByContextAndDisplayNameAndDisplayTypeAndFieldTypeAndOwnerType(
			String context, String displayName, String displayType,
			String fieldType, String ownerType);

	@Cacheable
	public Optional<FieldMapping> findByContextAndDisplayNameAndOwnerType(
		String context, String displayName, String ownerType);

	@Cacheable
	public Optional<FieldMapping> findByContextAndFieldNameAndOwnerType(
		String context, String fieldName, String ownerType);

	@Cacheable
	public List<FieldMapping> findByContextAndFieldTypeAndOwnerType(
		String context, String fieldType, String ownerType);

	@Cacheable
	public List<FieldMapping>
		findByDataSourceFieldNameAndDataSourceIdAndOwnerType(
			@Param("dataSourceFieldName") String dataSourceFieldName,
			@Param("dataSourceId") Long dataSourceId,
			@Param("ownerType") String ownerType);

	@Cacheable
	public List<Transformation> getFieldMappingTransformations(
		String apply, @Nullable String filterString, Pageable pageable);

	public List<FieldMapping> searchFieldMappings(
		@Nullable String filterString, Pageable pageable);

	public List<FieldMapping> searchIndividualFieldMappings(
		@Nullable String name, Pageable pageable);

}