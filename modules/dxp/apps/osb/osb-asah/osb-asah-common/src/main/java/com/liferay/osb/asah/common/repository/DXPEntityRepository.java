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
import com.liferay.osb.asah.common.model.Sort;

import java.util.List;
import java.util.Map;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Marcos Martins
 */
@ConditionalOnProperty(
	havingValue = "true", value = "osb.asah.postgresql.enabled"
)
@Repository
public interface DXPEntityRepository extends CrudRepository<DXPEntity, Long> {

	public void deleteAll(String collectionName);

	public void deleteByPropertyValue(
		DXPEntity.Type dxpEntityType, String fieldName, String fieldValue);

	public DXPEntity fetchByDataSourceIdAndEntityTypeIdFieldValue(
		String dataSourceId, DXPEntity.Type dxpEntityType,
		String dxpEntityTypeIdValue);

	public List<DXPEntity> findByProperties(
		Long after, String collectionName, Map<String, Object> properties,
		int size);

	public List<DXPEntity> findUsersByMembershipId(
		DXPEntity.Type dxpEntityType, String membershipId);

	public List<DXPEntity> searchByDataSourceIdsAndKeywordsAndCollectionName(
		String collectionName, List<Long> dataSourceIds, String keywords,
		int size, Sort sort, int start);

}