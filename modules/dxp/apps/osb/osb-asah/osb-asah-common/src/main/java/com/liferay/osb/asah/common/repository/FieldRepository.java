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

import com.liferay.osb.asah.common.entity.Field;
import com.liferay.osb.asah.common.model.Transformation;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;

/**
 * @author Rachael Koestartyo
 */
@Primary
public interface FieldRepository extends CrudRepository<Field, Long> {

	public long countFields(@Nullable String filterString);

	@Modifying
	public void deleteByDataSourceId(@Param("dataSourceId") Long dataSourceId);

	public boolean existsByDataSourceId(Long dataSourceId);

	public List<Field>
		findByContextAndDataSourceIdAndNameAndOwnerIdAndOwnerType(
			String context, @Nullable Long dataSourceId, @Nullable String name,
			@Nullable Long ownerId, String ownerType);

	public List<Field>
		findByContextAndDataSourceIdNotAndNameNotInAndOwnerIdAndOwnerType(
			String context, Long dataSourceId, List<String> names, Long ownerId,
			String ownerType);

	public List<Field> findByContextAndNameAndOwnerIdAndOwnerType(
		String context, String name, Long ownerId, String ownerType);

	public List<Field> findByContextAndOwnerIdAndOwnerType(
		String context, Long ownerId, String ownerType);

	public List<Field> findByContextAndOwnerIdGroupByMaxModifiedDateAndName(
		@Param("context") String context, @Param("ownerId") Long ownerId);

	public List<Field> findByFieldTypeAndOwnerTypeAndValueIn(
		String fieldType, String ownerType, List<String> values);

	public List<Transformation> getFieldTransformations(
		String apply, @Nullable String filterString, Pageable pageable);

	public List<Field> searchFields(
		@Nullable String filterString, Pageable pageable);

}