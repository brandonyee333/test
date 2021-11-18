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

package com.liferay.osb.asah.upgrade.v3_0_1.test;

import com.liferay.osb.asah.common.entity.EventDefinition;
import com.liferay.osb.asah.common.repository.EventDefinitionRepository;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;
import com.liferay.osb.asah.upgrade.OSBAsahUpgradeSpringTestContext;
import com.liferay.osb.asah.upgrade.v3_0_1.EventDefinitionUpgradeStep;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Inácio Nery
 */
public class EventDefinitionUpgradeStepTest
	implements OSBAsahTestExecutionListenersContext,
			   OSBAsahUpgradeSpringTestContext {

	@Test
	public void testUpgrade() throws Exception {
		EventDefinition eventDefinition1 = _getEventDefinition("pageRead");

		eventDefinition1.setType(EventDefinition.Type.CUSTOM);

		eventDefinition1 = _eventDefinitionRepository.save(eventDefinition1);

		Assertions.assertEquals(
			EventDefinition.Type.CUSTOM, eventDefinition1.getType());

		EventDefinition eventDefinition2 = new EventDefinition();

		eventDefinition2.setDisplayName("test");
		eventDefinition2.setHidden(false);
		eventDefinition2.setName("test");
		eventDefinition2.setType(EventDefinition.Type.CUSTOM);

		eventDefinition2 = _eventDefinitionRepository.save(eventDefinition2);

		Assertions.assertEquals(
			EventDefinition.Type.CUSTOM, eventDefinition2.getType());

		_eventDefinitionUpgradeStep.upgrade("");

		eventDefinition1 = _getEventDefinition("pageRead");

		Assertions.assertEquals(
			EventDefinition.Type.DEFAULT, eventDefinition1.getType());

		eventDefinition2 = _getEventDefinition("test");

		Assertions.assertEquals(
			EventDefinition.Type.CUSTOM, eventDefinition2.getType());
	}

	private EventDefinition _getEventDefinition(String name) {
		Optional<EventDefinition> eventDefinitionOptional =
			_eventDefinitionRepository.findByName(name);

		Assertions.assertTrue(eventDefinitionOptional.isPresent());

		return eventDefinitionOptional.get();
	}

	@Autowired
	private EventDefinitionRepository _eventDefinitionRepository;

	@Autowired
	private EventDefinitionUpgradeStep _eventDefinitionUpgradeStep;

}