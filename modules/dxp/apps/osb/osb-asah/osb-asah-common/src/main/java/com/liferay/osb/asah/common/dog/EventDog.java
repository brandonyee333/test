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
import com.liferay.osb.asah.common.repository.EventRepository;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Leslie Wong
 */
@Component
public class EventDog {

	public Event addEvent(
		AnalyticsEvent analyticsEvent, Long eventDefinitionId) {

		Event event = new Event(analyticsEvent);

		event.setEventDefinitionId(eventDefinitionId);

		return _eventRepository.save(event);
	}

	public Event addEvent(
		String applicationId, Long channelId, Date createDate,
		String dataSourceId, Date eventDate, Long eventDefinitionId,
		String projectId, String userId) {

		Event event = new Event();

		event.setApplicationId(applicationId);
		event.setChannelId(channelId);
		event.setCreateDate(createDate);
		event.setDataSourceId(dataSourceId);
		event.setEventDate(eventDate);
		event.setEventDefinitionId(eventDefinitionId);
		event.setUserId(userId);

		return _eventRepository.save(event);
	}

	@Autowired
	private EventRepository _eventRepository;

}