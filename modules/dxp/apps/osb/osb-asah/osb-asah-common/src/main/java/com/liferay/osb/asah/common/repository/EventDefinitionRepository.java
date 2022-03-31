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

import com.liferay.osb.asah.common.entity.EventDefinition;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;

/**
 * @author Leslie Wong
 */
public interface EventDefinitionRepository
	extends Repository<EventDefinition, Long> {

	@Cacheable
	public long countEventDefinitions(
		@Nullable Boolean blocked,
		@Nullable EventDefinition.BlockedReasonType blockedReasonType,
		@Nullable Boolean hidden, @Nullable String keyword,
		@Nullable EventDefinition.Type type);

	@Cacheable
	public Optional<EventDefinition> findByDisplayNameIgnoreCase(
		String displayName);

	@Cacheable
	public Optional<EventDefinition> findByName(String name);

	@Cacheable
	public List<EventDefinition> findByNameIn(Collection<String> names);

	@Cacheable
	public List<EventDefinition> searchEventDefinitions(
		@Nullable Boolean blocked,
		@Nullable EventDefinition.BlockedReasonType blockedReasonType,
		@Nullable Boolean hidden, @Nullable String keyword, Pageable pageable,
		@Nullable EventDefinition.Type type);

	@CacheEvict(allEntries = true)
	@Modifying
	public void updateEventDefinitions(
		@Param("ids") List<Long> eventDefinitionIds,
		@Param("hidden") Boolean hidden);

}