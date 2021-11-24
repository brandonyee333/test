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

package com.liferay.osb.asah.extractor.processor.test;

import com.liferay.osb.asah.common.dog.DXPEntityDog;
import com.liferay.osb.asah.common.dog.IndividualDog;
import com.liferay.osb.asah.common.entity.DXPEntity;
import com.liferay.osb.asah.common.entity.Individual;
import com.liferay.osb.asah.common.messaging.Channel;
import com.liferay.osb.asah.common.messaging.MessageSubscriber;
import com.liferay.osb.asah.common.messaging.model.Message;
import com.liferay.osb.asah.common.repository.FieldMappingRepository;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.util.ListUtil;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.common.util.SetUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.extractor.OSBAsahExtractorSpringTestContext;
import com.liferay.osb.asah.extractor.processor.DXPEntitiesMessageProcessor;
import com.liferay.osb.asah.test.util.annotation.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.annotation.MessageBusChannel;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.util.Collections;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Rachael Koestartyo
 */
public class DXPEntitiesMessageProcessorTest
	implements OSBAsahExtractorSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@ElasticsearchIndex(
		name = "data-sources", resourcePath = "data_sources.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@MessageBusChannel(
		channel = Channel.DXP_ENTITIES_MESSAGE,
		resourcePath = "dxp_entities_message_2.json"
	)
	@Test
	public void testProcessQueuedMessagesAddEntities() throws Exception {
		_fieldMappingRepository.save(
			FaroInfoTestUtil.buildIndividualFieldMapping(
				349978106408647035L, "emailAddress", "email", "Text"));

		_dxpEntitiesMessageProcessor.processQueuedMessages();

		ProjectIdThreadLocal.setProjectId("test");

		DXPEntity dxpEntity = _dxpEntityDog.fetchByFieldsAndType(
			Collections.singletonMap("fields.roleId", 39521),
			DXPEntity.Type.ROLE);

		Assertions.assertNotNull(dxpEntity);

		Individual individual = _individualDog.fetchIndividualByEmailAddress(
			"scott.lang@test.com");

		Assertions.assertNotNull(individual);
		Assertions.assertEquals(
			SetUtil.of(dxpEntity.getId()), individual.getRoleIds());
	}

	@ElasticsearchIndex(
		name = "data-sources", resourcePath = "data_sources.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@MessageBusChannel(
		channel = Channel.DXP_ENTITIES_MESSAGE,
		resourcePath = "dxp_entities_message_1.json"
	)
	@Test
	public void testProcessQueuedMessagesRetainsOrder() throws Exception {
		_dxpEntitiesMessageProcessor.processQueuedMessages();

		List<Message<String>> messages = _messageSubscriber.pullMessages(
			50, String::valueOf);

		_messageSubscriber.sendAckIds(
			ListUtil.map(messages, Message::getAckId));

		Assertions.assertNotEquals(0, messages.size());

		JSONArray jsonArray = new JSONArray();

		for (Message<String> message : messages) {
			JSONObject messageJSONObject = new JSONObject(message.getObject());

			jsonArray.put(messageJSONObject);
		}

		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONArray(
				"dependencies/dxp_entities_message_1.json", this),
			jsonArray, true);
	}

	@Autowired
	private DXPEntitiesMessageProcessor _dxpEntitiesMessageProcessor;

	@Autowired
	private DXPEntityDog _dxpEntityDog;

	@Autowired
	private FieldMappingRepository _fieldMappingRepository;

	@Autowired
	private IndividualDog _individualDog;

	@MessageSubscriber.Autowired(channel = Channel.DXP_ENTITIES_MESSAGE)
	private MessageSubscriber _messageSubscriber;

}