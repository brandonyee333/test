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

import com.liferay.osb.asah.common.entity.Event;
import com.liferay.osb.asah.common.model.EventAttributeValue;
import com.liferay.osb.asah.common.repository.EventRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

/**
 * @author Leslie Wong
 */
@ConditionalOnProperty(
	havingValue = "false", matchIfMissing = true,
	value = "osb.asah.postgresql.enabled"
)
@Repository
public class ElasticsearchEventRepositoryImpl implements EventRepository {

	@Override
	public long count() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Long countByChannelIdAndEventDefinitionIdAndEventDateBetween(
		Long channelId, long eventDefinitionId, Date rangeStartDate,
		Date rangeEndDate) {

		throw new UnsupportedOperationException();
	}

	@Override
	public long countByEventDefinitionId(long eventDefinitionId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public long countUniqueIndividuals(
		@Nullable Long channelId, @Nullable Long eventDefinitionId,
		@Nullable Date rangeEndDate, @Nullable Date rangeStartDate) {

		throw new UnsupportedOperationException();
	}

	@Override
	public void delete(Event event) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteAll() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteAll(Iterable<? extends Event> events) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteById(Long eventId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean existsById(Long eventId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Iterable<Event> findAll() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Iterable<Event> findAllById(Iterable<Long> eventIds) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Optional<Event> findById(Long eventId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<EventAttributeValue> findDistinctAttributeValues(
		Long eventAttributeDefinitionId, int size) {

		throw new UnsupportedOperationException();
	}

	@Override
	public Optional<Event> findLastSeenEvent(@Nullable Long eventDefinitionId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public long getAverageEventCountPerIndividual(
		@Nullable Long channelId, @Nullable Long eventDefinitionId,
		@Nullable Date rangeEndDate, @Nullable Date rangeStartDate) {

		throw new UnsupportedOperationException();
	}

	@Override
	public <S extends Event> S save(S event) {
		throw new UnsupportedOperationException();
	}

	@Override
	public <S extends Event> Iterable<S> saveAll(Iterable<S> events) {
		throw new UnsupportedOperationException();
	}

}