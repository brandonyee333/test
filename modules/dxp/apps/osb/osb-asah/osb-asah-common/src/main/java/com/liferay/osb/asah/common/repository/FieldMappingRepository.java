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

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.query.Param;

/**
 * @author Rachael Koestartyo
 */
public interface FieldMappingRepository
	extends CustomFieldMappingRepository, Repository<FieldMapping, Long> {

	@Cacheable
	public long countByFieldNameAndOwnerType(
		String fieldName, String ownerType);

	@Cacheable
	public boolean existsByContextAndFieldNameAndOwnerType(
		String context, String fieldName, String ownerType);

	@Cacheable
	public List<FieldMapping> findByContextAndDisplayNameAndOwnerType(
		String context, String displayName, String ownerType);

	@Cacheable
	public Optional<FieldMapping> findByContextAndFieldNameAndOwnerType(
		String context, String fieldName, String ownerType);

	@Cacheable
	public List<String> findFieldNameByContextAndFieldTypeAndOwnerType(
		@Param("context") String context, @Param("fieldType") String fieldType,
		@Param("ownerType") String ownerType);

}