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

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.date.dog.TimeZoneDog;
import com.liferay.osb.asah.common.entity.BQEvent;
import com.liferay.osb.asah.common.entity.BQEventProperty;
import com.liferay.osb.asah.common.entity.EventDefinition;
import com.liferay.osb.asah.common.model.BQEventPropertyValue;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.model.TimeRange;
import com.liferay.osb.asah.common.model.Tuple2;
import com.liferay.osb.asah.common.model.UserSession;
import com.liferay.osb.asah.common.repository.BQEventPropertyRepository;
import com.liferay.osb.asah.common.repository.BQEventRepository;
import com.liferay.osb.asah.common.spring.annotation.VisibleForTestingOnly;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

/**
 * @author Leslie Wong
 */
@Component
public class EventDog {

	@VisibleForTestingOnly
	public BQEvent addBQEvent(
			String applicationId, Set<BQEventProperty> bqEventProperties,
			Long channelId, Date createDate, Long dataSourceId, Date eventDate,
			String eventId, String id, String sessionId, String userId)
		throws Exception {

		return addBQEvent(
			applicationId, bqEventProperties, null, null, channelId, null, null,
			null, null, createDate, dataSourceId, null, null, eventDate,
			eventId, null, id, null, null, null, null, null, null, sessionId,
			null, null, null, userId, null);
	}

	@VisibleForTestingOnly
	public BQEvent addBQEvent(
			String applicationId, Set<BQEventProperty> bqEventProperties,
			String browserName, String canonicalUrl, Long channelId,
			String city, String contentLanguageId, String context,
			String country, Date createDate, Long dataSourceId,
			String description, String deviceType, Date eventDate,
			String eventId, String experienceId, String id, String keywords,
			String languageId, String platformName, String projectTimeZoneId,
			String referrer, String region, String sessionId,
			String timezoneOffset, String title, String url, String userId,
			String variantId)
		throws Exception {

		BQEvent bqEvent = _bqEventRepository.save(
			new BQEvent(
				applicationId, browserName, canonicalUrl, channelId, city,
				contentLanguageId, context, country, createDate, dataSourceId,
				description, deviceType, eventDate, eventId,
				_objectMapper.writeValueAsString(bqEventProperties),
				experienceId, id, keywords, languageId, platformName,
				projectTimeZoneId, referrer, region, sessionId, timezoneOffset,
				title, url, userId, variantId));

		for (BQEventProperty bqEventProperty : bqEventProperties) {
			bqEventProperty.setId(bqEvent.getId());

			_bqEventPropertyRepository.save(bqEventProperty);
		}

		return bqEvent;
	}

	public long countEvents(Long eventDefinitionId) {
		if (eventDefinitionId != null) {
			return _bqEventRepository.countByEventDefinitionId(
				eventDefinitionId);
		}

		return _bqEventRepository.count();
	}

	public Integer countEvents(
		Long channelId, Long individualId, String keywords,
		TimeRange timeRange) {

		return _bqEventRepository.countEvents(
			channelId, individualId, keywords, timeRange.getEndLocalDateTime(),
			timeRange.getStartLocalDateTime(), _timeZoneDog.getTimeZoneId());
	}

	public BQEvent fetchEvent(Long id) {
		Optional<BQEvent> eventOptional = _bqEventRepository.findById(id);

		return eventOptional.orElse(null);
	}

	public List<BQEventPropertyValue> getRecentEventPropertyValues(
		Long eventAttributeDefinitionId, int size) {

		return _bqEventPropertyRepository.
			findBQEventPropertyValuesByEventAttributeDefinitionId(
				eventAttributeDefinitionId, size);
	}

	public List<BQEvent> searchEvents(
		Long channelId, Long individualId, String keywords, int page, int size,
		TimeRange timeRange) {

		return _bqEventRepository.searchEvents(
			channelId, individualId, keywords,
			PageRequest.of(page, size, Sort.desc("eventDate")),
			timeRange.getEndLocalDateTime(), timeRange.getStartLocalDateTime(),
			_timeZoneDog.getTimeZoneId());
	}

	public Map<UserSession, List<Tuple2<BQEvent, EventDefinition>>>
		searchEventsGroupByUserSessionId(
			Long channelId, Long individualId, String keywords, int page,
			int size, TimeRange timeRange) {

		Set<String> eventDefinitionNames = new HashSet<>();

		Set<String> userSessionIds = new HashSet<>();

		List<BQEvent> bqEvents = searchEvents(
			channelId, individualId, keywords, page, size, timeRange);

		bqEvents.forEach(
			bqEvent -> {
				eventDefinitionNames.add(bqEvent.getEventId());

				userSessionIds.add(bqEvent.getSessionId());
			});

		List<UserSession> userSessions = _userSessionDog.findByIds(
			userSessionIds);

		Stream<UserSession> userSessionsStream = userSessions.stream();

		Map<String, UserSession> userSessionMap = userSessionsStream.collect(
			Collectors.toMap(UserSession::getId, userSession -> userSession));

		Map<String, EventDefinition> eventDefinitions =
			_eventDefinitionDog.getEventDefinitions(eventDefinitionNames);

		Stream<BQEvent> bqEventsStream = bqEvents.stream();

		return bqEventsStream.map(
			bqEvent -> new Tuple2<>(
				bqEvent, eventDefinitions.get(bqEvent.getEventId()))
		).collect(
			Collectors.groupingBy(
				tuple -> userSessionMap.get(
					tuple.getT1(
					).getSessionId()))
		);
	}

	@Autowired
	private BQEventPropertyRepository _bqEventPropertyRepository;

	@Autowired
	private BQEventRepository _bqEventRepository;

	@Autowired
	private EventDefinitionDog _eventDefinitionDog;

	@Autowired
	private ObjectMapper _objectMapper;

	@Autowired
	private TimeZoneDog _timeZoneDog;

	@Autowired
	private UserSessionDog _userSessionDog;

}