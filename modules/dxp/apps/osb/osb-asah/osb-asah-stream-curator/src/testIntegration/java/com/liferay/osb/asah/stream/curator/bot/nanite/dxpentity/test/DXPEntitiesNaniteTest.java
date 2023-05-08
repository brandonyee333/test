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

package com.liferay.osb.asah.stream.curator.bot.nanite.dxpentity.test;

import com.liferay.osb.asah.common.dog.DXPEntityDog;
import com.liferay.osb.asah.common.entity.DXPEntity;
import com.liferay.osb.asah.common.messaging.Channel;
import com.liferay.osb.asah.common.messaging.MessageSubscriber;
import com.liferay.osb.asah.common.messaging.model.Message;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.util.ListUtil;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.stream.curator.OSBAsahStreamCuratorSpringTestContext;
import com.liferay.osb.asah.stream.curator.bot.nanite.dxpentity.DXPEntitiesNanite;
import com.liferay.osb.asah.test.util.annotation.MessageBusChannel;
import com.liferay.osb.asah.test.util.annotation.RepositoryResource;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Rachael Koestartyo
 */
public class DXPEntitiesNaniteTest
	implements OSBAsahStreamCuratorSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@Disabled
	@MessageBusChannel(
		channel = Channel.DXP_ENTITIES_MESSAGE,
		resourcePath = "dxp_entities_message_2.json"
	)
	@RepositoryResource(
		repositoryClass = DataSourceRepository.class,
		resourcePath = "data_sources.json"
	)
	@Test
	public void testProcessQueuedMessagesAddEntities() throws Exception {
		_processQueuedMessages();

		ProjectIdThreadLocal.setProjectId("test");

		DXPEntity dxpEntity = _dxpEntityDog.fetchByFieldsAndType(
			Collections.singletonMap("fields.roleId", 39521),
			DXPEntity.Type.ROLE);

		Assertions.assertNotNull(dxpEntity);
	}

	@MessageBusChannel(
		channel = Channel.DXP_ENTITIES_MESSAGE,
		resourcePath = "dxp_entities_message_1.json"
	)
	@RepositoryResource(
		repositoryClass = DataSourceRepository.class,
		resourcePath = "data_sources.json"
	)
	@Test
	public void testProcessQueuedMessagesRetainsOrder() throws Exception {
		_processQueuedMessages();

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

	private void _processQueuedMessages() throws Exception {
		ExecutorService executorService = Executors.newSingleThreadExecutor();

		Future<?> future = executorService.submit(_dxpEntitiesNanite::run);

		try {
			future.get(8, TimeUnit.SECONDS);
		}
		catch (TimeoutException timeoutException) {
			future.cancel(true);
		}
		finally {
			executorService.shutdownNow();
		}
	}

	@Autowired
	private DXPEntitiesNanite _dxpEntitiesNanite;

	@Autowired
	private DXPEntityDog _dxpEntityDog;

	@MessageSubscriber.Autowired(channel = Channel.DXP_ENTITIES_MESSAGE)
	private MessageSubscriber _messageSubscriber;

}