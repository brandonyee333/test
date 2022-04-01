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

package com.liferay.osb.asah.common.dog;

import com.liferay.osb.asah.common.entity.BQEventProperty;
import com.liferay.osb.asah.common.entity.EventAttributeDefinition;
import com.liferay.osb.asah.common.repository.BQEventPropertyRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
public class EventPropertyDog {

	public BQEventProperty getEventProperty(
		String attributeName, Long eventId) {

		EventAttributeDefinition eventAttributeDefinition =
			_eventAttributionDefinitionDog.fetchEventAttributeDefinitionByName(
				attributeName);

		if (eventAttributeDefinition == null) {
			throw new IllegalArgumentException(
				"There is no event attribute definition with name " +
					attributeName);
		}

		Optional<BQEventProperty> eventAttributeOptional =
			_bqEventPropertyRepository.
				findByEventAttributeDefinitionIdAndEventId(
					eventAttributeDefinition.getId(), eventId);

		return eventAttributeOptional.orElseThrow(
			() -> new IllegalArgumentException(
				String.format(
					"There is no event attribute with name %s and event ID %s",
					attributeName, eventId)));
	}

	public Page<String> getEventPropertyValuePage(
		Long channelId, Long eventAttributeDefinitionId, Long eventDefinitionId,
		String keywords, Integer size, Integer start) {

		PageRequest pageRequest = PageRequest.of(start, size);

		return PageableExecutionUtils.getPage(
			_bqEventPropertyRepository.searchValues(
				channelId, eventAttributeDefinitionId, eventDefinitionId,
				keywords, pageRequest),
			pageRequest,
			() -> _bqEventPropertyRepository.countValues(
				channelId, eventAttributeDefinitionId, eventDefinitionId,
				keywords));
	}

	@Autowired
	private BQEventPropertyRepository _bqEventPropertyRepository;

	@Autowired
	private EventAttributeDefinitionDog _eventAttributionDefinitionDog;

}