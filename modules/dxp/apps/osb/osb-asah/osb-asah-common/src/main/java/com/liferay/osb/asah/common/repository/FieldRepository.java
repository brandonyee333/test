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

import java.util.List;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Rachael Koestartyo
 */
@ConditionalOnProperty(
	havingValue = "true", value = "osb.asah.postgresql.enabled"
)
@Repository
public interface FieldRepository extends CrudRepository<Field, Long> {

	public boolean existsByDataSourceId(Long dataSourceId);

	public List<Field>
		findByContextAndDataSourceIdAndNameAndOwnerIdAndOwnerType(
			String context, Long dataSourceId, String name, Long ownerId,
			String ownerType);

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

}