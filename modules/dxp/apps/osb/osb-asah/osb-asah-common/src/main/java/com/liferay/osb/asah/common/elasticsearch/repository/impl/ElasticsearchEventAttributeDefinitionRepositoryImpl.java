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
		return 0;
	}

	@Override
	public void delete(EventAttributeDefinition entity) {
	}

	@Override
	public void deleteAll() {
	}

	@Override
	public void deleteAll(
		Iterable<? extends EventAttributeDefinition> entities) {
	}

	@Override
	public void deleteById(Long aLong) {
	}

	@Override
	public boolean existsById(Long aLong) {
		return false;
	}

	@Override
	public Iterable<EventAttributeDefinition> findAll() {
		return null;
	}

	@Override
	public Iterable<EventAttributeDefinition> findAllById(
		Iterable<Long> longs) {

		return null;
	}

	@Override
	public Optional<EventAttributeDefinition> findById(Long aLong) {
		return Optional.empty();
	}

	@Override
	public EventAttributeDefinition findByName(String name) {
		return null;
	}

	@Override
	public <S extends EventAttributeDefinition> S save(S entity) {
		return null;
	}

	@Override
	public <S extends EventAttributeDefinition> Iterable<S> saveAll(
		Iterable<S> entities) {

		return null;
	}

}