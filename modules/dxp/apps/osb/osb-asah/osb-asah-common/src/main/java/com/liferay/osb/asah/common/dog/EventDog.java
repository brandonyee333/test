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
import com.liferay.osb.asah.common.entity.EventDefinition;
import com.liferay.osb.asah.common.model.EventAttributeValue;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.model.TimeRange;
import com.liferay.osb.asah.common.model.Tuple2;
import com.liferay.osb.asah.common.model.UserSession;
import com.liferay.osb.asah.common.repository.EventAttributeRepository;
import com.liferay.osb.asah.common.repository.EventRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.collections4.IterableUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

/**
 * @author Leslie Wong
 */
@Component
public class EventDog {

	public Event addEvent(
		String analyticsEventId, String applicationId, Long channelId,
		Date createDate, Long dataSourceId, Set<EventAttribute> eventAttributes,
		Date eventDate, Long eventDefinitionId, Long individualId,
		String sessionId, String userId) {

		return _eventRepository.save(
			new Event(
				analyticsEventId, applicationId, channelId, createDate,
				dataSourceId, eventAttributes, eventDate, eventDefinitionId,
				individualId, sessionId, userId));
	}

	public List<Event> addEvents(List<Event> events) {
		return IterableUtils.toList(_eventRepository.saveAll(events));
	}

	public long countEvents(Long eventDefinitionId) {
		if (eventDefinitionId != null) {
			return _eventRepository.countByEventDefinitionId(eventDefinitionId);
		}

		return _eventRepository.count();
	}

	public Integer countEvents(
		Long channelId, Long individualId, String keywords,
		TimeRange timeRange) {

		return _eventRepository.countEvents(
			channelId, individualId, keywords, timeRange);
	}

	public List<EventAttributeValue> getRecentEventAttributeValues(
		Long eventAttributeDefinitionId, int size) {

		return _eventRepository.findDistinctAttributeValues(
			eventAttributeDefinitionId, size);
	}

	public List<Event> searchEvents(
		Long channelId, Long individualId, String keywords, int page, int size,
		TimeRange timeRange) {

		List<Event> events = _eventRepository.searchEvents(
			channelId, individualId, keywords,
			PageRequest.of(page, size, Sort.desc("eventDate")), timeRange);

		Stream<Event> eventsStream = events.stream();

		List<EventAttribute> eventAttributes =
			_eventAttributeRepository.findByEventIdIn(
				eventsStream.map(
					Event::getId
				).collect(
					Collectors.toList()
				));

		Stream<EventAttribute> eventAttributesStream = eventAttributes.stream();

		Map<Long, List<EventAttribute>> eventAttributeMap =
			eventAttributesStream.collect(
				Collectors.groupingBy(EventAttribute::getEventId));

		events.forEach(
			event -> event.setEventAttributes(
				new HashSet<>(
					eventAttributeMap.getOrDefault(
						event.getId(), new ArrayList<>()))));

		return events;
	}

	public Map<UserSession, List<Tuple2<Event, EventDefinition>>>
		searchEventsGroupByUserSessionId(
			Long channelId, Long individualId, String keywords, int page,
			int size, TimeRange timeRange) {

		Set<Long> eventDefinitionIds = new HashSet<>();

		Set<String> userSessionIds = new HashSet<>();

		List<Event> events = searchEvents(
			channelId, individualId, keywords, page, size, timeRange);

		events.forEach(
			event -> {
				eventDefinitionIds.add(event.getEventDefinitionId());

				userSessionIds.add(event.getSessionId());
			});

		List<UserSession> userSessions = _userSessionDog.findByIds(
			userSessionIds);

		Stream<UserSession> userSessionsStream = userSessions.stream();

		Map<String, UserSession> userSessionMap = userSessionsStream.collect(
			Collectors.toMap(UserSession::getId, userSession -> userSession));

		Map<Long, EventDefinition> eventDefinitions =
			_eventDefinitionDog.getEventDefinitions(eventDefinitionIds);

		Stream<Event> eventsStream = events.stream();

		return eventsStream.map(
			event -> new Tuple2<>(
				event, eventDefinitions.get(event.getEventDefinitionId()))
		).collect(
			Collectors.groupingBy(
				tuple -> userSessionMap.get(
					tuple.getT1(
					).getSessionId()))
		);
	}

	@Autowired
	private EventAttributeRepository _eventAttributeRepository;

	@Autowired
	private EventDefinitionDog _eventDefinitionDog;

	@Autowired
	private EventRepository _eventRepository;

	@Autowired
	private UserSessionDog _userSessionDog;

}