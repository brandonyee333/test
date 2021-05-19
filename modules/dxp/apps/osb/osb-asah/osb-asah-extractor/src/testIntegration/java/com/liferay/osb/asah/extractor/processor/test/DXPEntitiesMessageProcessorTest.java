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

import com.liferay.osb.asah.common.messaging.Channel;
import com.liferay.osb.asah.common.messaging.MessageSubscriber;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.extractor.processor.DXPEntitiesMessageProcessor;
import com.liferay.osb.asah.extractor.spring.OSBAsahExtractorSpringBootApplication;
import com.liferay.osb.asah.test.util.annotation.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.annotation.MessageBusChannel;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Rachael Koestartyo
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OSBAsahExtractorSpringBootApplication.class)
public class DXPEntitiesMessageProcessorTest {

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

		List<String> messages = _messageSubscriber.pullMessages(50);

		Assert.assertNotEquals(0, messages.size());

		JSONArray jsonArray = new JSONArray();

		for (String message : messages) {
			JSONObject messageJSONObject = new JSONObject(message);

			jsonArray.put(messageJSONObject);
		}

		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONArray(
				"dependencies/dxp_entities_message_1.json", this),
			jsonArray, true);
	}

	@Autowired
	private DXPEntitiesMessageProcessor _dxpEntitiesMessageProcessor;

	@MessageSubscriber.Autowired(channel = Channel.DXP_ENTITIES_MESSAGE)
	private MessageSubscriber _messageSubscriber;

}