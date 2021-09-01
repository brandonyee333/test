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

import com.liferay.osb.asah.common.entity.Event;
import com.liferay.osb.asah.common.entity.EventAttribute;
import com.liferay.osb.asah.common.entity.EventAttributeDefinition;
import com.liferay.osb.asah.common.entity.EventDefinition;
import com.liferay.osb.asah.common.entity.EventDefinitionEventAttributeDefinition;
import com.liferay.osb.asah.common.model.AnalyticsEvent;
import com.liferay.osb.asah.common.util.Assert;
import com.liferay.osb.asah.common.util.MapUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Leslie Wong
 */
@Component
public class EventStorageDog {

	public Set<EventAttribute> getEventAttributes(
		AnalyticsEvent analyticsEvent, Long eventDefinitionId) {

		return new HashSet<EventAttribute>() {
			{
				addAll(
					_resolveGlobalEventAttributes(
						analyticsEvent.getContext(),
						analyticsEvent.getEventDate(), eventDefinitionId));
				addAll(
					_resolveLocalEventAttributes(
						analyticsEvent.getEventDate(), eventDefinitionId,
						analyticsEvent.getEventProperties()));
			}
		};
	}

	public EventDefinition getEventDefinition(AnalyticsEvent analyticsEvent) {
		EventDefinition eventDefinition =
			_eventDefinitionDog.fetchEventDefinitionByName(
				analyticsEvent.getEventId());

		if (eventDefinition == null) {
			eventDefinition = _eventDefinitionDog.addEventDefinition(
				null, null, analyticsEvent.getEventDate(),
				analyticsEvent.getEventId(), EventDefinition.Type.CUSTOM,
				MapUtil.getString(analyticsEvent.getContext(), "canonicalUrl"));
		}
		else if (eventDefinition.isBlocked()) {
			eventDefinition = _eventDefinitionDog.updateEventDefinition(
				analyticsEvent.getEventDate(),
				MapUtil.getString(analyticsEvent.getContext(), "canonicalUrl"),
				null, null, eventDefinition.getId());
		}

		return eventDefinition;
	}

	@Transactional
	public Event store(AnalyticsEvent analyticsEvent, String sessionId) {
		Assert.notBlank(sessionId, "Session ID is blank");

		EventDefinition eventDefinition = getEventDefinition(analyticsEvent);

		if (eventDefinition.isBlocked()) {
			return null;
		}

		Set<EventAttribute> eventAttributes = getEventAttributes(
			analyticsEvent, eventDefinition.getId());

		return _eventDog.addEvent(
			analyticsEvent.getId(), analyticsEvent.getApplicationId(),
			Long.valueOf(analyticsEvent.getChannelId()),
			analyticsEvent.getCreateDate(),
			Long.valueOf(analyticsEvent.getDataSourceId()), eventAttributes,
			analyticsEvent.getEventDate(), eventDefinition.getId(),
			Optional.ofNullable(
				analyticsEvent.getIndividualId()
			).map(
				Long::valueOf
			).orElse(
				null
			),
			sessionId, analyticsEvent.getUserId());
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

	private EventAttribute _resolveEventAttribute(
		String eventAttributeName, String eventAttributeValue, Date eventDate,
		Long eventDefinitionId) {

		EventAttributeDefinition eventAttributeDefinition =
			_eventAttributeDefinitionDog.fetchEventAttributeDefinitionByName(
				eventAttributeName);

		if (eventAttributeDefinition == null) {
			eventAttributeDefinition =
				_eventAttributeDefinitionDog.addEventAttributeDefinition(
					null, null, eventDefinitionId, eventAttributeName,
					eventAttributeValue);
		}
		else {
			Long eventAttributeDefinitionId = eventAttributeDefinition.getId();

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
						eventDefinitionId, eventAttributeValue));

				_eventAttributeDefinitionDog.updateEventAttributeDefinition(
					null, null, null, eventAttributeDefinitionId,
					eventDefinitionEventAttributeDefinitions, null);
			}
		}

		return new EventAttribute(
			eventDate, eventAttributeDefinition.getId(), eventAttributeValue);
	}

	private Set<EventAttribute> _resolveGlobalEventAttributes(
		Map<String, String> eventContext, Date eventDate,
		Long eventDefinitionId) {

		Set<EventAttribute> eventAttributes = new HashSet<>();

		for (Map.Entry<String, String> entry :
				_globalEventAttributeDefinitionNames.entrySet()) {

			eventAttributes.add(
				_resolveEventAttribute(
					entry.getKey(), eventContext.get(entry.getValue()),
					eventDate, eventDefinitionId));
		}

		return eventAttributes;
	}

	private Set<EventAttribute> _resolveLocalEventAttributes(
		Date eventDate, Long eventDefinitionId,
		Map<String, String> eventProperties) {

		Set<EventAttribute> eventAttributes = new HashSet<>();

		for (Map.Entry<String, String> entry : eventProperties.entrySet()) {
			eventAttributes.add(
				_resolveEventAttribute(
					entry.getKey(), entry.getValue(), eventDate,
					eventDefinitionId));
		}

		return eventAttributes;
	}

	private static final Map<String, String>
		_globalEventAttributeDefinitionNames = new HashMap<String, String>() {
			{
				put("canonicalUrl", "canonicalUrl");
				put("pageDescription", "description");
				put("pageKeywords", "keywords");
				put("pageTitle", "title");
				put("referrer", "referrer");
				put("url", "url");
			}
		};

	@Autowired
	private EventAttributeDefinitionDog _eventAttributeDefinitionDog;

	@Autowired
	private EventDefinitionDog _eventDefinitionDog;

	@Autowired
	private EventDog _eventDog;

}