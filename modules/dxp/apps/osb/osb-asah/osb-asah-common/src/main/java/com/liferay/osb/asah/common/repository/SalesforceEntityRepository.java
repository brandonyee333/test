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

import com.liferay.osb.asah.common.entity.SalesforceEntity;

import java.util.List;
import java.util.Optional;

import org.json.JSONObject;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * @author Marcellus Tavares
 */
public interface SalesforceEntityRepository
	extends CrudRepository<SalesforceEntity, String> {

	public long countByDataSourceIdAndType(
		Long dataSourceId, SalesforceEntity.Type type);

	@Modifying
	public void deleteByDataSourceId(@Param("dataSourceId") Long dataSourceId);

	public boolean existsByDataSourceIdAndIdAndType(
		Long dataSourceId, String id, SalesforceEntity.Type type);

	public List<SalesforceEntity> findByDataSourceIdAndFieldKeyEqualsAndType(
		Long dataSourceId, String fieldKey, String fieldValue,
		SalesforceEntity.Type type);

	public List<String>
		findByDataSourceIdAndFieldKeyEqualsAndTypeGroupByFieldKey(
			Long dataSourceId, String fieldKey, String fieldValue,
			SalesforceEntity.Type type, String groupByFieldKey);

	public Optional<SalesforceEntity> findByDataSourceIdAndIdAndType(
		Long dataSourceId, String id, SalesforceEntity.Type type);

	public List<SalesforceEntity> findByDataSourceIdAndType(
		Long dataSourceId, SalesforceEntity.Type type, Pageable pageable);

	@Modifying
	public void updateSalesforceEntityFields(
		@Param("dataSourceId") Long dataSourceId,
		@Param("fields") JSONObject fieldsJSONObject, @Param("id") String id,
		@Param("type") SalesforceEntity.Type type);

}