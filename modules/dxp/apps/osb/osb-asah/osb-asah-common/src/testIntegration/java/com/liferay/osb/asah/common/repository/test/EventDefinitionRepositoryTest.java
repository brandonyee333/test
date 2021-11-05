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

package com.liferay.osb.asah.common.repository.test;

import com.liferay.osb.asah.common.OSBAsahCommonSpringTestContext;
import com.liferay.osb.asah.common.entity.EventDefinition;
import com.liferay.osb.asah.common.repository.EventDefinitionRepository;
import com.liferay.osb.asah.test.util.annotation.SQLResource;
import com.liferay.osb.asah.test.util.configuration.JDBCTestConfiguration;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;

/**
 * @author Alejo Ceballos
 * @author Marcos Martins
 */
@Import(JDBCTestConfiguration.class)
public class EventDefinitionRepositoryTest
	implements OSBAsahCommonSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@SQLResource(resourcePath = "test_event_definition.sql")
	@Test
	public void testSearchHiddenBlockedEventDefinitions() {
		List<EventDefinition> eventDefinitions =
			_eventDefinitionRepository.searchEventDefinitions(
				true, null, true, "CustomEventNotification",
				PageRequest.of(0, 3), EventDefinition.Type.CUSTOM);

		Assertions.assertEquals(
			3, eventDefinitions.size(), eventDefinitions.toString());
	}

	@Autowired
	private EventDefinitionRepository _eventDefinitionRepository;

}