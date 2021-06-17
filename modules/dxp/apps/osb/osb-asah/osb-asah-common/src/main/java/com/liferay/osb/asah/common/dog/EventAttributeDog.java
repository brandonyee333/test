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

import com.liferay.osb.asah.common.entity.EventAttribute;
import com.liferay.osb.asah.common.entity.EventAttributeDefinition;
import com.liferay.osb.asah.common.repository.EventAttributeRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
public class EventAttributeDog {

	public EventAttribute getEventAttribute(
		String attributeName, Long eventId) {

		EventAttributeDefinition eventAttributeDefinition =
			_eventAttributionDefinitionDog.fetchEventAttributeDefinitionByName(
				attributeName);

		if (eventAttributeDefinition == null) {
			throw new IllegalArgumentException(
				"There is no event attribute definition with name " +
					attributeName);
		}

		Optional<EventAttribute> eventAttributeOptional =
			_eventAttributeRepository.
				findByEventAttributeDefinitionIdAndEventId(
					eventAttributeDefinition.getId(), eventId);

		return eventAttributeOptional.orElseThrow(
			() -> new IllegalArgumentException(
				String.format(
					"There is no event attribute with name %s and event ID %s",
					attributeName, eventId)));
	}

	@Autowired
	private EventAttributeRepository _eventAttributeRepository;

	@Autowired
	private EventAttributeDefinitionDog _eventAttributionDefinitionDog;

}