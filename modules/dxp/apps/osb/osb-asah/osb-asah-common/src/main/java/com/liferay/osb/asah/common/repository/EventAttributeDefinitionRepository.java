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

import com.liferay.osb.asah.common.entity.EventAttributeDefinition;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.query.Param;

/**
 * @author Leslie Wong
 */
public interface EventAttributeDefinitionRepository
	extends EventAttributeDefinitionRepositoryCustom,
			Repository<EventAttributeDefinition, Long> {

	@Cacheable
	public Optional<EventAttributeDefinition> findByDisplayNameIgnoreCase(
		String displayName);

	@Cacheable
	public List<EventAttributeDefinition> findByEventDefinitionId(
		@Param("eventDefinitionId") Long eventDefinitionId);

	@Cacheable
	public Optional<EventAttributeDefinition> findByName(String name);

	@Cacheable
	public List<EventAttributeDefinition> findByType(
		EventAttributeDefinition.Type type);

}