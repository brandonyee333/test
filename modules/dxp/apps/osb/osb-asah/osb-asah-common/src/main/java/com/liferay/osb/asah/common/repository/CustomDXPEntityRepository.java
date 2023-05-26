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

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.lang.Nullable;

/**
 * @author Ivica Cardic
 */
public interface CustomDXPEntityRepository {

	public long count();

	@Cacheable
	public long countByDataSourceIdsAndKeywordsAndType(
		List<Long> dataSourceIds, @Nullable String keywords,
		DXPEntity.Type type);

	public long countByModifiedDateBetweenAndType(
		@Nullable Date modifiedDate1, Date modifiedDate2, DXPEntity.Type type);

	public void delete(DXPEntity dxpEntity);

	public void deleteAll(Iterable<? extends DXPEntity> dxpEntities);

	@CacheEvict(allEntries = true)
	@Modifying
	public void deleteByFieldNameAndFieldValueAndType(
		String fieldName, Object fieldValue, DXPEntity.Type type);

	public void deleteById(Long id);

	@CacheEvict(allEntries = true)
	@Modifying
	public void deleteByType(DXPEntity.Type type);

	public boolean existsById(Long id);

	public Iterable<DXPEntity> findAll();

	public Page<DXPEntity> findAll(Pageable pageable);

	public Iterable<DXPEntity> findAll(Sort sort);

	public Iterable<DXPEntity> findAllById(Iterable<Long> ids);

	@Cacheable
	public List<DXPEntity> findByAfterAndFieldsAndType(
		@Nullable Long after, Map<String, Object> fields, int size,
		DXPEntity.Type type);

	@Cacheable
	public List<DXPEntity> findByFieldsAndType(
		Map<String, Object> fields, DXPEntity.Type type);

	@Cacheable
	public List<DXPEntity> findByMembershipClassNameAndMembershipId(
		String memebershipClassName, Long membershipId);

	public List<DXPEntity> findByModifiedDateBetweenAndType(
		@Nullable Date modifiedDate1, Date modifiedDate2, DXPEntity.Type type,
		Pageable pageable);

	@Cacheable
	public List<DXPEntity> searchByDataSourceIdsAndKeywordsAndType(
		List<Long> dataSourceIds, @Nullable String keywords,
		DXPEntity.Type type, Pageable pageable);

}