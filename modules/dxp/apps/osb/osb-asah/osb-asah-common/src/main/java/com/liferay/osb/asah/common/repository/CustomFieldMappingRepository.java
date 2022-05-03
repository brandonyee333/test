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
import com.liferay.osb.asah.common.repository.helper.FilterHelper;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;

/**
 * @author Ivica Cardic
 */
public interface CustomFieldMappingRepository {

	@Cacheable
	public long countFieldMappings(FilterHelper filterHelper);

	@Cacheable
	public long countIndividualFieldMappings(@Nullable String name);

	@Cacheable
	public List<FieldMapping>
		findByContextAndDataSourceIdAndFieldNameAndOwnerType(
			String context, Long dataSourceId, String fieldName,
			String ownerType);

	@Cacheable
	public List<FieldMapping> findByContextAndDataSourceIdAndOwnerType(
		String context, @Nullable Long dataSourceId, String ownerType);

	@Cacheable
	public List<FieldMapping>
		findByDataSourceFieldNameAndDataSourceIdAndOwnerType(
			String dataSourceFieldName, Long dataSourceId, String ownerType);

	@Cacheable
	public List<FieldMapping> findByDataSourceId(Long dataSourceId);

	@Cacheable
	public List<Transformation> getFieldMappingTransformations(
		String apply, FilterHelper filterHelper, Pageable pageable);

	@Cacheable
	public List<FieldMapping> searchFieldMappings(
		FilterHelper filterHelper, Pageable pageable);

	@Cacheable
	public List<FieldMapping> searchIndividualFieldMappings(
		@Nullable String name, Pageable pageable);

}