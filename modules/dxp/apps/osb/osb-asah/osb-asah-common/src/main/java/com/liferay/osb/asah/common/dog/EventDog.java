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
import com.liferay.osb.asah.common.model.EventAttributeValue;
import com.liferay.osb.asah.common.repository.EventRepository;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Leslie Wong
 */
@Component
public class EventDog {

	public Event addEvent(
		String analyticsEventId, String applicationId, String canonicalURL,
		Long channelId, Date createDate, Long dataSourceId,
		Set<EventAttribute> eventAttributes, Date eventDate,
		Long eventDefinitionId, Long individualId, String url, String userId) {

		Event event = new Event();

		event.setAnalyticsEventId(analyticsEventId);
		event.setApplicationId(applicationId);
		event.setCanonicalURL(canonicalURL);
		event.setChannelId(channelId);
		event.setCreateDate(createDate);
		event.setDataSourceId(dataSourceId);
		event.setEventAttributes(eventAttributes);
		event.setEventDate(eventDate);
		event.setEventDefinitionId(eventDefinitionId);
		event.setIndividualId(individualId);
		event.setURL(url);
		event.setUserId(userId);

		return _eventRepository.save(event);
	}

	public long countEvents(Long eventDefinitionId) {
		if (eventDefinitionId != null) {
			return _eventRepository.countByEventDefinitionId(eventDefinitionId);
		}

		return _eventRepository.count();
	}

	public List<EventAttributeValue> getRecentEventAttributeValues(
		Long eventAttributeDefinitionId, int size) {

		return _eventRepository.findDistinctAttributeValues(
			eventAttributeDefinitionId, size);
	}

	@Autowired
	private EventRepository _eventRepository;

}