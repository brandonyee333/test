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

import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.lang.Nullable;

/**
 * @author Marcos Martins
 */
@Primary
public interface DXPEntityRepository extends Repository<DXPEntity, Long> {

	@Cacheable
	public long countByDataSourceIdsAndKeywordsAndType(
		List<Long> dataSourceIds, @Nullable String keywords,
		DXPEntity.Type type);

	@CacheEvict(allEntries = true)
	@Modifying
	public void deleteByFieldNameAndFieldValueAndType(
		String fieldName, String fieldValue, DXPEntity.Type type);

	@CacheEvict(allEntries = true)
	@Modifying
	public void deleteByType(DXPEntity.Type type);

	@Cacheable
	public List<DXPEntity> findByAfterAndFieldsAndType(
		@Nullable Long after, Map<String, Object> fields, int size,
		DXPEntity.Type type);

	@Cacheable
	public List<DXPEntity> findByMembershipClassNameAndMembershipId(
		String membershipClassName, Long membershipId);

	@Cacheable
	public List<DXPEntity> searchByDataSourceIdsAndKeywordsAndType(
		List<Long> dataSourceIds, @Nullable String keywords,
		DXPEntity.Type type, Pageable pageable);

}