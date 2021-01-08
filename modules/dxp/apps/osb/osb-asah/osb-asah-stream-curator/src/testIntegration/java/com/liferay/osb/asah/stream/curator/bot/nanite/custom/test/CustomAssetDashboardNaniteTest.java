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

package com.liferay.osb.asah.stream.curator.bot.nanite.custom.test;

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.messaging.Channel;
import com.liferay.osb.asah.common.messaging.MessageBus;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.stream.curator.bot.nanite.custom.CustomAssetDashboardNanite;
import com.liferay.osb.asah.stream.curator.spring.OSBAsahCuratorSpringBootApplication;
import com.liferay.osb.asah.test.util.elasticsearch.MessageBusChannel;
import com.liferay.osb.asah.test.util.messaging.MessageBusTestHelper;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Marcellus Tavares
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OSBAsahCuratorSpringBootApplication.class)
public class CustomAssetDashboardNaniteTest {

	@MessageBusChannel(
		channel = Channel.ANALYTICS_EVENTS_CUSTOM_ASSET,
		resourcePath = "analytics_events_custom_asset_channel.json"
	)
	@Test
	public void testAddDashboard() throws Exception {
		_customAssetDashboardNanite.run();

		ElasticsearchInvoker elasticsearchInvoker =
			_elasticsearchInvokerFactory.forCerebroInfo();

		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONArray(
				"dependencies/expected_custom_asset_dashboards_info.json",
				this),
			elasticsearchInvoker.get("custom-asset-dashboards"), false);
	}

	@MessageBusChannel(
		channel = Channel.ANALYTICS_EVENTS_CUSTOM_ASSET,
		resourcePath = "analytics_events_custom_asset_channel.json"
	)
	@Test
	public void testSkipTitleUpdate() throws Exception {
		_customAssetDashboardNanite.run();

		MessageBusTestHelper messageBusTestHelper = new MessageBusTestHelper(
			_messageBus);

		messageBusTestHelper.prepareMessageBusChannel(
			Channel.ANALYTICS_EVENTS_CUSTOM_ASSET,
			ResourceUtil.readResourceToJSONArray(
				"dependencies" +
					"/analytics_events_custom_asset_channel_empty_title.json",
				this));

		_customAssetDashboardNanite.run();

		ElasticsearchInvoker elasticsearchInvoker =
			_elasticsearchInvokerFactory.forCerebroInfo();

		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONArray(
				"dependencies/expected_custom_asset_dashboards_info.json",
				this),
			elasticsearchInvoker.get("custom-asset-dashboards"), false);
	}

	@Test
	public void testUpdateTitle() throws Exception {
		_customAssetDashboardNanite.run();

		MessageBusTestHelper messageBusTestHelper = new MessageBusTestHelper(
			_messageBus);

		messageBusTestHelper.prepareMessageBusChannel(
			Channel.ANALYTICS_EVENTS_CUSTOM_ASSET,
			ResourceUtil.readResourceToJSONArray(
				"dependencies" +
					"/analytics_events_custom_asset_channel_update_1.json",
				this));

		_customAssetDashboardNanite.run();

		messageBusTestHelper.prepareMessageBusChannel(
			Channel.ANALYTICS_EVENTS_CUSTOM_ASSET,
			ResourceUtil.readResourceToJSONArray(
				"dependencies" +
					"/analytics_events_custom_asset_channel_update_2.json",
				this));

		_customAssetDashboardNanite.run();

		ElasticsearchInvoker elasticsearchInvoker =
			_elasticsearchInvokerFactory.forCerebroInfo();

		JSONArray jsonArray = elasticsearchInvoker.get(
			"custom-asset-dashboards");

		Assert.assertEquals(1, jsonArray.length());

		JSONObject jsonObject = jsonArray.getJSONObject(0);

		Assert.assertEquals(
			"Asset's Title Update 2", jsonObject.getString("assetTitle"));
	}

	@Autowired
	private CustomAssetDashboardNanite _customAssetDashboardNanite;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

	@Autowired
	private MessageBus _messageBus;

}