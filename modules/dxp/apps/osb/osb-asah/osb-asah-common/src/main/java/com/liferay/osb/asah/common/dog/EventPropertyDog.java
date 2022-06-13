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
import com.liferay.osb.asah.common.repository.BQEventPropertyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
public class EventPropertyDog {

	public Page<String> getBQEventPropertyValuePage(
		Long channelId, Long eventAttributeDefinitionId, Long eventDefinitionId,
		String keywords, Integer size, Integer start) {

		PageRequest pageRequest = PageRequest.of(start, size);

		String eventAttributeDefinitionName = _getEventAttributeDefinitionName(
			eventAttributeDefinitionId);

		String eventDefinitionName = _getEventDefinitionName(eventDefinitionId);

		return PageableExecutionUtils.getPage(
			_bqEventPropertyRepository.searchValues(
				channelId, eventAttributeDefinitionName, eventDefinitionName,
				keywords, pageRequest),
			pageRequest,
			() -> _bqEventPropertyRepository.countValues(
				channelId, eventAttributeDefinitionName, eventDefinitionName,
				keywords));
	}

	private String _getEventAttributeDefinitionName(
		Long eventAttributeDefinitionId) {

		EventAttributeDefinition eventAttributeDefinition =
			_eventAttributeDefinitionDog.getEventAttributeDefinition(
				eventAttributeDefinitionId);

		return eventAttributeDefinition.getName();
	}

	private String _getEventDefinitionName(Long eventDefinitionId) {
		EventDefinition eventDefinition =
			_eventDefinitionDog.getEventDefinition(eventDefinitionId);

		return eventDefinition.getName();
	}

	@Autowired
	private BQEventPropertyRepository _bqEventPropertyRepository;

	@Autowired
	private EventAttributeDefinitionDog _eventAttributeDefinitionDog;

	@Autowired
	private EventAttributeDefinitionDog _eventAttributionDefinitionDog;

	@Autowired
	private EventDefinitionDog _eventDefinitionDog;

}