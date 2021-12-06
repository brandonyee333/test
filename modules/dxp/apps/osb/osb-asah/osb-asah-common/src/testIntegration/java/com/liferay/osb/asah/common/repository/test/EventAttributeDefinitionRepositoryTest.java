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
import com.liferay.osb.asah.common.entity.EventAttributeDefinition;
import com.liferay.osb.asah.common.entity.EventDefinition;
import com.liferay.osb.asah.common.entity.EventDefinitionEventAttributeDefinition;
import com.liferay.osb.asah.common.repository.EventAttributeDefinitionRepository;
import com.liferay.osb.asah.common.repository.EventDefinitionRepository;
import com.liferay.osb.asah.test.util.configuration.JDBCTestConfiguration;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;

/**
 * @author Leilany Ulisses
 */
@Import(JDBCTestConfiguration.class)
public class EventAttributeDefinitionRepositoryTest
	implements OSBAsahCommonSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@Test
	public void testSearchEventAttributeDefinitions() {
		Optional<EventDefinition> eventDefinitionOptional =
			_eventDefinitionRepository.findByDisplayNameIgnoreCase(
				"assetClicked");

		EventDefinition eventDefinition = eventDefinitionOptional.get();

		List<EventAttributeDefinition> eventAttributeDefinitions =
			_eventAttributeDefinitionRepository.searchEventAttributeDefinitions(
				eventDefinition.getId(), null, PageRequest.of(1, 10), null);

		Assertions.assertNotNull(eventAttributeDefinitions.get(0));

		EventAttributeDefinition eventAttributeDefinition =
			eventAttributeDefinitions.get(0);

		Set<EventDefinitionEventAttributeDefinition>
			eventDefinitionEventAttributeDefinitions =
				eventAttributeDefinition.
					getEventDefinitionEventAttributeDefinitions();

		Stream<EventDefinitionEventAttributeDefinition> stream =
			eventDefinitionEventAttributeDefinitions.stream();

		Assertions.assertNotNull(
			stream.filter(
				eventDefinitionEventAttributeDefinition ->
					eventDefinitionEventAttributeDefinition.
						getEventDefinitionId(
						).equals(
							eventDefinition.getId()
						)
			).map(
				EventDefinitionEventAttributeDefinition::getEventDefinitionId
			).findFirst(
			).orElse(
				null
			));
	}

	@Autowired
	private EventAttributeDefinitionRepository
		_eventAttributeDefinitionRepository;

	@Autowired
	private EventDefinitionRepository _eventDefinitionRepository;

}