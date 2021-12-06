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

package com.liferay.osb.asah.backend.dto;

import com.liferay.osb.asah.common.entity.EventAttributeDefinition;
import com.liferay.osb.asah.common.entity.EventDefinitionEventAttributeDefinition;
import com.liferay.osb.asah.common.util.SetUtil;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Leilany Ulisses
 */
public class EventAttributeDefinitionDTOTest extends BaseDTOTestCase {

	@Override
	public String getClassName() {
		return EventAttributeDefinitionDTO.class.getName();
	}

	@Test
	public void testEventAttributeDefinitionDTO() {
		EventAttributeDefinition eventAttributeDefinition =
			new EventAttributeDefinition();

		eventAttributeDefinition.setDataType(
			EventAttributeDefinition.DataType.STRING);
		eventAttributeDefinition.setDescription(null);
		eventAttributeDefinition.setDisplayName("canonicalUrl");
		eventAttributeDefinition.setEventDefinitionEventAttributeDefinitions(
			SetUtil.of(
				new EventDefinitionEventAttributeDefinition(17L, null),
				new EventDefinitionEventAttributeDefinition(17L, null),
				new EventDefinitionEventAttributeDefinition(
					17L, "http://192.168.111.140:8089"),
				new EventDefinitionEventAttributeDefinition(17L, null),
				new EventDefinitionEventAttributeDefinition(17L, null)));

		eventAttributeDefinition.setId(10L);
		eventAttributeDefinition.setName("canonicalUrl");
		eventAttributeDefinition.setType(EventAttributeDefinition.Type.GLOBAL);

		EventAttributeDefinitionDTO eventAttributeDefinitionDTO =
			new EventAttributeDefinitionDTO(eventAttributeDefinition);

		Assertions.assertEquals(
			EventAttributeDefinition.DataType.STRING,
			eventAttributeDefinitionDTO.getDataType());

		Assertions.assertEquals(
			null, eventAttributeDefinitionDTO.getDescription());

		Assertions.assertEquals(
			"canonicalUrl", eventAttributeDefinitionDTO.getDisplayName());

		Assertions.assertEquals("10", eventAttributeDefinitionDTO.getId());

		Assertions.assertEquals(
			"canonicalUrl", eventAttributeDefinitionDTO.getName());

		Assertions.assertEquals(
			"http://192.168.111.140:8089",
			eventAttributeDefinitionDTO.getSampleValue());

		Assertions.assertEquals(
			EventAttributeDefinition.Type.GLOBAL,
			eventAttributeDefinitionDTO.getType());
	}

}