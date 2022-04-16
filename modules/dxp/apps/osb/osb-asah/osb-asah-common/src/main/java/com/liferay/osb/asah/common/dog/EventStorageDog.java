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

import com.liferay.osb.asah.common.entity.EventAttributeDefinition;
import com.liferay.osb.asah.common.entity.EventDefinition;
import com.liferay.osb.asah.common.entity.EventDefinitionEventAttributeDefinition;
import com.liferay.osb.asah.common.model.AnalyticsEvent;
import com.liferay.osb.asah.common.util.MapUtil;

import java.util.HashMap;
import java.util.Map;
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

	@Transactional
	public void storeEventDefinition(AnalyticsEvent analyticsEvent) {
		EventDefinition eventDefinition = _storeEventDefinition(analyticsEvent);

		if (eventDefinition.isBlocked()) {
			return;
		}

		_storeEventAttributeDefinitions(
			analyticsEvent, eventDefinition.getId());
	}

	private Set<Long> _getEventDefinitionIds(
		Set<EventDefinitionEventAttributeDefinition>
			eventDefinitionEventAttributeDefinitions) {

		Stream<EventDefinitionEventAttributeDefinition> stream =
			eventDefinitionEventAttributeDefinitions.stream();

		return stream.filter(
			eventDefinitionEventAttributeDefinition ->
				eventDefinitionEventAttributeDefinition.getSampleValue() != null
		).map(
			EventDefinitionEventAttributeDefinition::getEventDefinitionId
		).collect(
			Collectors.toSet()
		);
	}

	private void _resolveEventAttributeDefinition(
		String eventAttributeName, String eventAttributeValue,
		Long eventDefinitionId) {

		EventAttributeDefinition eventAttributeDefinition =
			_eventAttributeDefinitionDog.fetchEventAttributeDefinitionByName(
				eventAttributeName);

		if (eventAttributeDefinition == null) {
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

				eventDefinitionEventAttributeDefinitions.removeIf(
					eventDefinitionEventAttributeDefinition ->
						eventDefinitionId.equals(
							eventDefinitionEventAttributeDefinition.
								getEventDefinitionId()));

				eventDefinitionEventAttributeDefinitions.add(
					new EventDefinitionEventAttributeDefinition(
						eventDefinitionId, eventAttributeValue));

				_eventAttributeDefinitionDog.updateEventAttributeDefinition(
					null, null, null, eventAttributeDefinitionId,
					eventDefinitionEventAttributeDefinitions, null);
			}
		}
	}

	private void _resolveGlobalEventAttributeDefinitions(
		Map<String, String> eventContext, Long eventDefinitionId) {

		for (Map.Entry<String, String> entry :
				_globalEventAttributeDefinitionNames.entrySet()) {

			_resolveEventAttributeDefinition(
				entry.getKey(), eventContext.get(entry.getValue()),
				eventDefinitionId);
		}
	}

	private void _resolveLocalEventAttributeDefinitions(
		Long eventDefinitionId, Map<String, String> eventProperties) {

		for (Map.Entry<String, String> entry : eventProperties.entrySet()) {
			_resolveEventAttributeDefinition(
				entry.getKey(), entry.getValue(), eventDefinitionId);
		}
	}

	private void _storeEventAttributeDefinitions(
		AnalyticsEvent analyticsEvent, Long eventDefinitionId) {

		_resolveGlobalEventAttributeDefinitions(
			analyticsEvent.getContext(), eventDefinitionId);
		_resolveLocalEventAttributeDefinitions(
			eventDefinitionId, analyticsEvent.getEventProperties());
	}

	private EventDefinition _storeEventDefinition(
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
				analyticsEvent.getEventDate(),
				MapUtil.getString(analyticsEvent.getContext(), "canonicalUrl"),
				null, null, eventDefinition.getId());
		}

		return eventDefinition;
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

}