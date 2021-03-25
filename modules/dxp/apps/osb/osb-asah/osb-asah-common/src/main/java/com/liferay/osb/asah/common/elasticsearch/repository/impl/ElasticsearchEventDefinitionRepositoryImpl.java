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

import com.liferay.osb.asah.common.model.EventDefinition;
import com.liferay.osb.asah.common.repository.EventDefinitionRepository;

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
public class ElasticsearchEventDefinitionRepositoryImpl
	implements EventDefinitionRepository {

	@Override
	public long count() {
		throw new UnsupportedOperationException();
	}

	@Override
	public long countEventDefinitions(
		Boolean blocked, String keyword, EventDefinition.Type type) {

		throw new UnsupportedOperationException();
	}

	@Override
	public void delete(EventDefinition eventDefinition) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteAll() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteAll(
		Iterable<? extends EventDefinition> eventDefinitions) {

		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteById(Long eventDefinitionId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean existsById(Long eventDefinitionId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Iterable<EventDefinition> findAll() {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<EventDefinition> findAll(Pageable pageable) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Iterable<EventDefinition> findAllById(
		Iterable<Long> eventDefinitionIds) {

		throw new UnsupportedOperationException();
	}

	@Override
	public Optional<EventDefinition> findByDisplayName(String displayName) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Optional<EventDefinition> findById(Long eventDefinitionId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Optional<EventDefinition> findByName(String name) {
		throw new UnsupportedOperationException();
	}

	@Override
	public <S extends EventDefinition> S save(S eventDefinition) {
		throw new UnsupportedOperationException();
	}

	@Override
	public <S extends EventDefinition> Iterable<S> saveAll(
		Iterable<S> eventDefinitions) {

		throw new UnsupportedOperationException();
	}

	@Override
	public List<EventDefinition> searchEventDefinitions(
		Boolean blocked, String keyword, Pageable pageable,
		EventDefinition.Type type) {

		throw new UnsupportedOperationException();
	}

}