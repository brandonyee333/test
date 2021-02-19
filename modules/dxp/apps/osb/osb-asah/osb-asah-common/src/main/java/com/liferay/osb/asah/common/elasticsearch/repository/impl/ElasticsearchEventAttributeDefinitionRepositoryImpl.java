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

import java.util.Optional;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
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
	public void delete(EventAttributeDefinition entity) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteAll() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteAll(
		Iterable<? extends EventAttributeDefinition> entities) {

		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteById(Long aLong) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean existsById(Long aLong) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Iterable<EventAttributeDefinition> findAll() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Iterable<EventAttributeDefinition> findAllById(
		Iterable<Long> longs) {

		throw new UnsupportedOperationException();
	}

	@Override
	public Optional<EventAttributeDefinition> findById(Long aLong) {
		throw new UnsupportedOperationException();
	}

	@Override
	public EventAttributeDefinition findByName(String name) {
		throw new UnsupportedOperationException();
	}

	@Override
	public <S extends EventAttributeDefinition> S save(S entity) {
		throw new UnsupportedOperationException();
	}

	@Override
	public <S extends EventAttributeDefinition> Iterable<S> saveAll(
		Iterable<S> entities) {

		throw new UnsupportedOperationException();
	}

}