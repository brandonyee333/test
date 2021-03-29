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
import com.liferay.osb.asah.common.model.BlockedEventDefinition;
import com.liferay.osb.asah.common.model.EventAttribute;
import com.liferay.osb.asah.common.model.EventAttributeDefinition;
import com.liferay.osb.asah.common.model.EventDefinition;
import com.liferay.osb.asah.common.model.EventDefinitionEventAttributeDefinition;
import com.liferay.osb.asah.common.util.MapUtil;

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

			String canonicalUrl = MapUtil.getString(
				analyticsEvent.getContext(), "canonicalUrl");

			if (eventDefinition == null) {
				eventDefinition = _eventDefinitionDog.addEventDefinition(
					null, null, analyticsEvent.getEventDate(), eventId,
					EventDefinition.Type.CUSTOM, canonicalUrl);

				if (eventDefinition.isBlocked()) {
					return;
				}
			}
			else if (eventDefinition.isBlocked()) {
				_eventDefinitionDog.updateEventDefinition(
					new BlockedEventDefinition(
						analyticsEvent.getEventDate(), canonicalUrl),
					null, null, eventDefinition.getId());

				return;
			}

			Long eventDefinitionId = eventDefinition.getId();

			Set<EventAttribute> eventAttributes = new HashSet<>();

			Map<String, String> eventProperties =
				analyticsEvent.getEventProperties();

			for (Map.Entry<String, String> entry : eventProperties.entrySet()) {
				String propertyName = entry.getKey();
				String propertyValue = entry.getValue();

				EventAttributeDefinition eventAttributeDefinition =
					_eventAttributeDefinitionDog.
						fetchEventAttributeDefinitionByName(propertyName);

				if (eventAttributeDefinition == null) {
					eventAttributeDefinition =
						_eventAttributeDefinitionDog.
							addEventAttributeDefinition(
								null, null, eventDefinitionId, propertyName,
								propertyValue);
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
								eventDefinitionId, propertyValue));

						_eventAttributeDefinitionDog.
							updateEventAttributeDefinition(
								null, null, null, eventAttributeDefinitionId,
								eventDefinitionEventAttributeDefinitions, null);
					}
				}

				eventAttributes.add(
					new EventAttribute(
						propertyValue, eventAttributeDefinition.getId()));
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