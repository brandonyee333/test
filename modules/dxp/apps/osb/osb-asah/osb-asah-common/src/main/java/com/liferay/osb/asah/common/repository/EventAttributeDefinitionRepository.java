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

import com.liferay.osb.asah.common.model.EventAttributeDefinition;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Leslie Wong
 */
@ConditionalOnProperty(
	havingValue = "true", value = "osb.asah.postgresql.enabled"
)
@Repository
public interface EventAttributeDefinitionRepository
	extends CrudRepository<EventAttributeDefinition, Long> {

	public long countEventAttributeDefinitions(String name);

	public List<EventAttributeDefinition> findAll(Pageable pageable);

	public Optional<EventAttributeDefinition> findByDisplayNameIgnoreCase(
		String displayName);

	public List<EventAttributeDefinition> findByEventDefinitionId(
		@Param("eventDefinitionId") Long eventDefinitionId);

	public EventAttributeDefinition findByName(String name);

	public List<EventAttributeDefinition> searchEventAttributeDefinitions(
		String name, Pageable pageable);

}