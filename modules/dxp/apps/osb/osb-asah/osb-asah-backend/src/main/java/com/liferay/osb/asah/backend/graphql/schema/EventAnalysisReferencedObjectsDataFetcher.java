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

package com.liferay.osb.asah.backend.graphql.schema;

import com.liferay.osb.asah.backend.dto.EventAnalysisDTO;
import com.liferay.osb.asah.backend.dto.EventAnalysisReferencedObjectDTO;
import com.liferay.osb.asah.common.dog.EventAttributeDefinitionDog;
import com.liferay.osb.asah.common.dog.EventDefinitionDog;
import com.liferay.osb.asah.common.entity.EventAttributeDefinition;
import com.liferay.osb.asah.common.entity.EventDefinition;
import com.liferay.osb.asah.common.graphql.GraphQLTypeWiring;
import com.liferay.osb.asah.common.util.ListUtil;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Rachael Koestartyo
 */
@Component
@GraphQLTypeWiring(fieldName = "referencedObjects", typeName = "EventAnalysis")
public class EventAnalysisReferencedObjectsDataFetcher
	implements DataFetcher<EventAnalysisReferencedObjectDTO> {

	@Override
	public EventAnalysisReferencedObjectDTO get(
		DataFetchingEnvironment environment) {

		EventAnalysisDTO eventAnalysisDTO = environment.getSource();

		EventDefinition eventDefinition =
			_eventDefinitionDog.getEventDefinition(
				Long.valueOf(eventAnalysisDTO.getEventDefinitionId()));

		List<EventAttributeDefinition> eventAttributeDefinitions =
			new ArrayList<>(
				_eventAttributeDefinitionDog.getEventAttributeDefinitions(
					ListUtil.map(
						eventAnalysisDTO.getEventAnalysisBreakdownDTOs(),
						eventAnalysisBreakdownDTO -> Long.valueOf(
							eventAnalysisBreakdownDTO.getAttributeId()))));

		eventAttributeDefinitions.addAll(
			_eventAttributeDefinitionDog.getEventAttributeDefinitions(
				ListUtil.map(
					eventAnalysisDTO.getEventAnalysisFilterDTOs(),
					eventAnalysisFilterDTO -> Long.valueOf(
						eventAnalysisFilterDTO.getAttributeId()))));

		return new EventAnalysisReferencedObjectDTO(
			eventDefinition, eventAttributeDefinitions);
	}

	@Autowired
	private EventAttributeDefinitionDog _eventAttributeDefinitionDog;

	@Autowired
	private EventDefinitionDog _eventDefinitionDog;

}