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

package com.liferay.osb.asah.common.elasticsearch.repository.impl;

import com.liferay.osb.asah.common.model.EventAttributeDefinition;
import com.liferay.osb.asah.common.repository.EventAttributeDefinitionRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

/**
 * @author Leslie Wong
 */
@ConditionalOnProperty(
	havingValue = "false", matchIfMissing = true,
	value = "osb.asah.postgresql.enabled"
)
@Repository
public class ElasticsearchEventAttributeDefinitionRepositoryImpl
	implements EventAttributeDefinitionRepository {

	@Override
	public long count() {
		throw new UnsupportedOperationException();
	}

	@Override
	public long countByNameContainingIgnoreCase(String name) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void delete(EventAttributeDefinition eventAttributeDefinition) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteAll() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteAll(
		Iterable<? extends EventAttributeDefinition>
			eventAttributeDefinitions) {

		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteById(Long eventAttributeDefinitionId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean existsById(Long eventAttributeDefinitionId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Iterable<EventAttributeDefinition> findAll() {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<EventAttributeDefinition> findAll(Pageable pageable) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Iterable<EventAttributeDefinition> findAllById(
		Iterable<Long> eventAttributeDefinitionIds) {

		throw new UnsupportedOperationException();
	}

	@Override
	public Optional<EventAttributeDefinition> findByDisplayName(
		String displayName) {

		throw new UnsupportedOperationException();
	}

	@Override
	public List<EventAttributeDefinition> findByEventDefinitionId(
		Long eventDefinitionId) {

		return null;
	}

	@Override
	public Optional<EventAttributeDefinition> findById(
		Long eventAttributeDefinitionId) {

		throw new UnsupportedOperationException();
	}

	@Override
	public EventAttributeDefinition findByName(String name) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<EventAttributeDefinition> findByNameContainingIgnoreCase(
		String name, Pageable pageable) {

		throw new UnsupportedOperationException();
	}

	@Override
	public <S extends EventAttributeDefinition> S save(
		S eventAttributeDefinition) {

		throw new UnsupportedOperationException();
	}

	@Override
	public <S extends EventAttributeDefinition> Iterable<S> saveAll(
		Iterable<S> eventAttributeDefinitions) {

		throw new UnsupportedOperationException();
	}

}