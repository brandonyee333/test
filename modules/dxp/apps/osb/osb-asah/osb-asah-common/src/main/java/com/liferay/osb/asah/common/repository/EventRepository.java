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

import com.liferay.osb.asah.common.entity.Event;
import com.liferay.osb.asah.common.model.EventAttributeValue;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

/**
 * @author Leslie Wong
 */
@ConditionalOnProperty(
	havingValue = "true", value = "osb.asah.postgresql.enabled"
)
@Repository
public interface EventRepository extends CrudRepository<Event, Long> {

	public Long countByChannelIdAndEventDefinitionIdAndEventDateBetween(
		Long channelId, long eventDefinitionId, Date rangeStartDate,
		Date rangeEndDate);

	public long countByEventDefinitionId(long eventDefinitionId);

	public List<EventAttributeValue> findDistinctAttributeValues(
		@Param("eventAttributeDefinitionId") Long eventAttributeDefinitionId,
		@Param("size") int size);

	public Optional<Event> findLastSeenEvent(@Nullable Long eventDefinitionId);

}