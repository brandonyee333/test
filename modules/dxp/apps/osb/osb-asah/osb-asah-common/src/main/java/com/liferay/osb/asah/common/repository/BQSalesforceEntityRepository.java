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

import com.liferay.osb.asah.common.entity.BQSalesforceEntity;

import java.util.List;
import java.util.Optional;

import org.json.JSONObject;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.repository.query.Param;

/**
 * @author Marcellus Tavares
 */
public interface BQSalesforceEntityRepository
	extends CustomBQSalesforceEntityRepository,
			Repository<BQSalesforceEntity, String> {

	@Cacheable
	public long countByDataSourceIdAndType(
		Long dataSourceId, BQSalesforceEntity.Type type);

	@CacheEvict(allEntries = true)
	@Modifying
	public void deleteByDataSourceId(@Param("dataSourceId") Long dataSourceId);

	@CacheEvict(allEntries = true)
	@Modifying
	public void deleteByFieldKeyAndFieldValueAndType(
		@Param("fieldKey") String fieldKey,
		@Param("fieldValue") String fieldValue,
		@Param("type") BQSalesforceEntity.Type type);

	@Cacheable
	public boolean existsByDataSourceIdAndIdAndType(
		Long dataSourceId, String id, BQSalesforceEntity.Type type);

	@Cacheable
	public Optional<BQSalesforceEntity> findByDataSourceIdAndIdAndType(
		Long dataSourceId, String id, BQSalesforceEntity.Type type);

	@Cacheable
	public List<BQSalesforceEntity> findByDataSourceIdAndType(
		Long dataSourceId, BQSalesforceEntity.Type type, Pageable pageable);

	@CacheEvict(allEntries = true)
	@Modifying
	public void updateBQSalesforceEntityFields(
		@Param("dataSourceId") Long dataSourceId,
		@Param("fields") JSONObject fieldsJSONObject, @Param("id") String id,
		@Param("type") BQSalesforceEntity.Type type);

}