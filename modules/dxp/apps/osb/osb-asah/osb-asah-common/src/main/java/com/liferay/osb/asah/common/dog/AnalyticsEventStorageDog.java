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
import com.liferay.osb.asah.common.model.Event;
import com.liferay.osb.asah.common.model.EventAttributeDefinition;
import com.liferay.osb.asah.common.model.EventDefinition;

import java.util.Map;

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
					null, null, eventId, "custom");
			}

			if (eventDefinition == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"Skipping store event as event definition is null");
				}

				return;
			}

			Long eventDefinitionId = eventDefinition.getId();

			Event event = _eventDog.addEvent(
				analyticsEvent.getId(), analyticsEvent.getApplicationId(),
				Long.valueOf(analyticsEvent.getChannelId()),
				analyticsEvent.getCreateDate(),
				analyticsEvent.getDataSourceId(), analyticsEvent.getEventDate(),
				eventDefinitionId, analyticsEvent.getUserId());

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
								"string", null, null, eventDefinitionId,
								propertyName);
				}
				else {
					Set<EventDefinitionEventAttributeDefinition>
						eventDefinitionEventAttributeDefinitions =
							eventAttributeDefinition.
								getEventDefinitionEventAttributeDefinitions();

					eventDefinitionEventAttributeDefinitions = new HashSet<>(
						eventDefinitionEventAttributeDefinitions);

					int initialSize =
						eventDefinitionEventAttributeDefinitions.size();

					eventDefinitionEventAttributeDefinitions.add(
						new EventDefinitionEventAttributeDefinition(
							eventDefinitionId));

					if (initialSize !=
							eventDefinitionEventAttributeDefinitions.size()) {

						_eventAttributeDefinitionDog.
							updateEventAttributeDefinition(
								null, null, null,
								eventAttributeDefinition.getId(),
								eventDefinitionEventAttributeDefinitions, null);
					}
				}

				_eventAttributeDog.addEventAttribute(
					eventAttributeDefinition.getId(), entry.getValue(),
					event.getId());
			}
		}
		catch (Exception e) {
			_log.error("Unable to store event", e);
		}
	}

	private static final Log _log = LogFactory.getLog(
		AnalyticsEventStorageDog.class);

	@Autowired
	private EventAttributeDefinitionDog _eventAttributeDefinitionDog;

	@Autowired
	private EventAttributeDog _eventAttributeDog;

	@Autowired
	private EventDefinitionDog _eventDefinitionDog;

	@Autowired
	private EventDog _eventDog;

}