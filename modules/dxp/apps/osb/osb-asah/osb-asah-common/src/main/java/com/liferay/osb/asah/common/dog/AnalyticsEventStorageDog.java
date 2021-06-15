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

import com.liferay.osb.asah.common.entity.BlockedEventDefinition;
import com.liferay.osb.asah.common.entity.Event;
import com.liferay.osb.asah.common.entity.EventAttribute;
import com.liferay.osb.asah.common.entity.EventAttributeDefinition;
import com.liferay.osb.asah.common.entity.EventDefinition;
import com.liferay.osb.asah.common.entity.EventDefinitionEventAttributeDefinition;
import com.liferay.osb.asah.common.model.AnalyticsEvent;
import com.liferay.osb.asah.common.util.MapUtil;

import java.util.HashMap;
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

	public Event store(AnalyticsEvent analyticsEvent) {
		try {
			EventDefinition eventDefinition = _resolveEventDefinition(
				analyticsEvent);

			if (eventDefinition.isBlocked()) {
				return null;
			}

			Set<EventAttribute> eventAttributes = _resolveEventAttributes(
				analyticsEvent, eventDefinition.getId());

			Map<String, String> eventContext = analyticsEvent.getContext();

			return _eventDog.addEvent(
				analyticsEvent.getId(), analyticsEvent.getApplicationId(),
				MapUtil.getString(eventContext, "canonicalUrl"),
				Long.valueOf(analyticsEvent.getChannelId()),
				analyticsEvent.getCreateDate(),
				analyticsEvent.getDataSourceId(), eventAttributes,
				analyticsEvent.getEventDate(), eventDefinition.getId(),
				Long.valueOf(analyticsEvent.getIndividualId()),
				MapUtil.getString(eventContext, "url"),
				analyticsEvent.getUserId());
		}
		catch (Exception exception) {
			_log.error("Unable to store event", exception);

			return null;
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

	private Set<EventAttribute> _resolveEventAttributes(
		AnalyticsEvent analyticsEvent, Long eventDefinitionId) {

		return new HashSet<EventAttribute>() {
			{
				addAll(
					_resolveGlobalEventAttributes(
						analyticsEvent.getContext(), eventDefinitionId));
				addAll(
					_resolveLocalEventAttributes(
						eventDefinitionId,
						analyticsEvent.getEventProperties()));
			}
		};
	}

	private EventDefinition _resolveEventDefinition(
		AnalyticsEvent analyticsEvent) {

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
				new BlockedEventDefinition(
					analyticsEvent.getEventDate(),
					MapUtil.getString(
						analyticsEvent.getContext(), "canonicalUrl")),
				null, null, eventDefinition.getId());
		}

		return eventDefinition;
	}

	private Set<EventAttribute> _resolveGlobalEventAttributes(
		Map<String, String> eventContext, Long eventDefinitionId) {

		Set<EventAttribute> eventAttributes = new HashSet<>();

		for (Map.Entry<String, String> entry :
				_globalEventAttributeDefinitionNames.entrySet()) {

			String propertyName = entry.getKey();
			String propertyValue = eventContext.get(entry.getValue());

			EventAttributeDefinition eventAttributeDefinition =
				_eventAttributeDefinitionDog.
					fetchEventAttributeDefinitionByName(propertyName);

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
						eventDefinitionId, propertyValue));

				_eventAttributeDefinitionDog.updateEventAttributeDefinition(
					null, null, null, eventAttributeDefinitionId,
					eventDefinitionEventAttributeDefinitions, null);
			}

			eventAttributes.add(
				new EventAttribute(
					propertyValue, eventAttributeDefinition.getId()));
		}

		return eventAttributes;
	}

	private Set<EventAttribute> _resolveLocalEventAttributes(
		Long eventDefinitionId, Map<String, String> eventProperties) {

		Set<EventAttribute> eventAttributes = new HashSet<>();

		for (Map.Entry<String, String> entry : eventProperties.entrySet()) {
			String propertyName = entry.getKey();
			String propertyValue = entry.getValue();

			EventAttributeDefinition eventAttributeDefinition =
				_eventAttributeDefinitionDog.
					fetchEventAttributeDefinitionByName(propertyName);

			if (eventAttributeDefinition == null) {
				eventAttributeDefinition =
					_eventAttributeDefinitionDog.addEventAttributeDefinition(
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

					_eventAttributeDefinitionDog.updateEventAttributeDefinition(
						null, null, null, eventAttributeDefinitionId,
						eventDefinitionEventAttributeDefinitions, null);
				}
			}

			eventAttributes.add(
				new EventAttribute(
					propertyValue, eventAttributeDefinition.getId()));
		}

		return eventAttributes;
	}

	private static final Log _log = LogFactory.getLog(
		AnalyticsEventStorageDog.class);

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