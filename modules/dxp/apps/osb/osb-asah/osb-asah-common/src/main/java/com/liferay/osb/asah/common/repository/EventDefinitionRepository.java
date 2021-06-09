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

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

/**
 * @author Leslie Wong
 */
@Repository
public interface EventDefinitionRepository
	extends CrudRepository<EventDefinition, Long> {

	public long countEventDefinitions(
		@Nullable Boolean blocked, @Nullable String keyword,
		@Nullable EventDefinition.Type type);

	public List<EventDefinition> findAll(Pageable pageable);

	public Optional<EventDefinition> findByDisplayNameIgnoreCase(
		String displayName);

	public Optional<EventDefinition> findByName(String name);

	public List<EventDefinition> searchEventDefinitions(
		@Nullable Boolean blocked, @Nullable String keyword, Pageable pageable,
		@Nullable EventDefinition.Type type);

}