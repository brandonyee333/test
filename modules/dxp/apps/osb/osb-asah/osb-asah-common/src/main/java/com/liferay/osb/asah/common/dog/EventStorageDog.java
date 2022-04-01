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

import com.liferay.osb.asah.common.entity.EventDefinition;
import com.liferay.osb.asah.common.model.AnalyticsEvent;
import com.liferay.osb.asah.common.util.MapUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Leslie Wong
 */
@Component
public class EventStorageDog {

	public EventDefinition storeEventDefinition(AnalyticsEvent analyticsEvent) {
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

	@Autowired
	private EventAttributeDefinitionDog _eventAttributeDefinitionDog;

	@Autowired
	private EventDefinitionDog _eventDefinitionDog;

}