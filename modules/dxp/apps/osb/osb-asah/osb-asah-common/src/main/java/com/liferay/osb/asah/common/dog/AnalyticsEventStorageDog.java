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

import com.liferay.osb.asah.common.model.AnalyticsEvent;
import com.liferay.osb.asah.common.model.EventAttribute;
import com.liferay.osb.asah.common.model.EventAttributeDefinition;
import com.liferay.osb.asah.common.model.EventAttributeDefinitionDataType;
import com.liferay.osb.asah.common.model.EventDefinition;
import com.liferay.osb.asah.common.model.EventDefinitionEventAttributeDefinition;
import com.liferay.osb.asah.common.model.EventDefinitionType;
import com.liferay.osb.asah.common.util.Validator;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Leslie Wong
 */
@Component
public class AnalyticsEventStorageDog {

	public void store(AnalyticsEvent analyticsEvent) {
		try {
			String eventId = analyticsEvent.getEventId();

			EventDefinition eventDefinition =
				_eventDefinitionDog.fetchEventDefinitionByName(eventId);

			if (eventDefinition == null) {
				eventDefinition = _eventDefinitionDog.addEventDefinition(
					null, null, EventDefinitionType.CUSTOM, eventId);
			}

			Long eventDefinitionId = eventDefinition.getId();

			Set<EventAttribute> eventAttributes = new HashSet<>();

			Map<String, String> eventProperties =
				analyticsEvent.getEventProperties();

			for (Map.Entry<String, String> entry : eventProperties.entrySet()) {
				String propertyName = entry.getKey();

				EventAttributeDefinition eventAttributeDefinition =
					_eventAttributeDefinitionDog.
						fetchEventAttributeDefinitionByName(propertyName);

				if (eventAttributeDefinition == null) {
					eventAttributeDefinition =
						_eventAttributeDefinitionDog.
							addEventAttributeDefinition(
								_getEventAttributeDefinitionDataType(
									propertyName, entry.getValue()),
								null, null, eventDefinitionId, propertyName,
								entry.getValue());
				}
				else {
					Long eventAttributeDefinitionId =
						eventAttributeDefinition.getId();

					Set<EventDefinitionEventAttributeDefinition>
						eventDefinitionEventAttributeDefinitions =
							eventAttributeDefinition.
								getEventDefinitionEventAttributeDefinitions();

					Set<Long> eventDefinitionIds = _getEventDefinitionIds(
						eventDefinitionEventAttributeDefinitions);

					if ((eventAttributeDefinitionId != null) &&
						!eventDefinitionIds.contains(eventDefinitionId)) {

						eventDefinitionEventAttributeDefinitions.add(
							new EventDefinitionEventAttributeDefinition(
								eventDefinitionId, entry.getValue()));

						_eventAttributeDefinitionDog.
							updateEventAttributeDefinition(
								null, null, null, eventAttributeDefinitionId,
								eventDefinitionEventAttributeDefinitions, null);
					}
				}

				eventAttributes.add(
					new EventAttribute(
						entry.getValue(), eventAttributeDefinition.getId()));
			}

			_eventDog.addEvent(
				analyticsEvent.getId(), analyticsEvent.getApplicationId(),
				Long.valueOf(analyticsEvent.getChannelId()),
				analyticsEvent.getCreateDate(),
				analyticsEvent.getDataSourceId(), eventAttributes,
				analyticsEvent.getEventDate(), eventDefinitionId,
				analyticsEvent.getUserId());
		}
		catch (Exception e) {
			_log.error("Unable to store event", e);
		}
	}

	private EventAttributeDefinitionDataType
		_getEventAttributeDefinitionDataType(
			String propertyName, String value) {

		if (Validator.isBoolean(value)) {
			return EventAttributeDefinitionDataType.BOOLEAN;
		}

		if (Validator.isDate(value)) {
			return EventAttributeDefinitionDataType.DATE;
		}

		String lowerCasePropertyName = propertyName.toLowerCase();

		if ((lowerCasePropertyName.contains("duration") ||
			 lowerCasePropertyName.contains("interval") ||
			 lowerCasePropertyName.contains("time")) &&
			Validator.isNumber(value)) {

			return EventAttributeDefinitionDataType.DURATION;
		}

		if (Validator.isDouble(value) || Validator.isNumber(value)) {
			return EventAttributeDefinitionDataType.NUMBER;
		}

		return EventAttributeDefinitionDataType.STRING;
	}

	private Set<Long> _getEventDefinitionIds(
		Set<EventDefinitionEventAttributeDefinition>
			eventDefinitionEventAttributeDefinitions) {

		Stream<EventDefinitionEventAttributeDefinition> stream =
			eventDefinitionEventAttributeDefinitions.stream();

		return stream.map(
			EventDefinitionEventAttributeDefinition::getEventDefinitionId
		).collect(
			Collectors.toSet()
		);
	}

	private static final Log _log = LogFactory.getLog(
		AnalyticsEventStorageDog.class);

	@Autowired
	private EventAttributeDefinitionDog _eventAttributeDefinitionDog;

	@Autowired
	private EventDefinitionDog _eventDefinitionDog;

	@Autowired
	private EventDog _eventDog;

}