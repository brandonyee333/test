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

package com.liferay.osb.asah.stream.curator.bot.nanite.blog.test;

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.messaging.Channel;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.stream.curator.bot.nanite.BaseNaniteTestCase;
import com.liferay.osb.asah.stream.curator.bot.nanite.blog.BlogNanite;
import com.liferay.osb.asah.stream.curator.spring.OSBAsahCuratorSpringBootApplication;
import com.liferay.osb.asah.test.util.annotation.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.annotation.MessageBusChannel;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Pedro Queiroz
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OSBAsahCuratorSpringBootApplication.class)
public class BlogNaniteTest extends BaseNaniteTestCase {

	@Before
	public void setUp() {
		setUp(_blogNanite);
	}

	@ElasticsearchIndex(
		name = "blogs", resourcePath = "blog_info_2.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@MessageBusChannel(
		channel = Channel.ANALYTICS_EVENTS_BLOG,
		resourcePath = "analytics_events_blog_channel_2.json"
	)
	@Test
	public void testBlogMetrics() throws Exception {
		_blogNanite.run();

		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONArray(
				"dependencies/expected_blog_info.json", this),
			_cerebroInfoElasticsearchInvoker.get("blogs"), false);
	}

	@ElasticsearchIndex(
		name = "blogs", resourcePath = "blog_info_1.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@MessageBusChannel(
		channel = Channel.ANALYTICS_EVENTS_BLOG,
		resourcePath = "analytics_events_blog_channel_1.json"
	)
	@Test
	public void testBlogRatingsMetric() {
		_blogNanite.run();

		JSONArray jsonArray = _cerebroInfoElasticsearchInvoker.get("blogs");

		Assert.assertEquals(1, jsonArray.length());

		JSONObject jsonObject = jsonArray.getJSONObject(0);

		Assert.assertEquals(1, jsonObject.getInt("ratings"));
		Assert.assertEquals(0.2, jsonObject.getDouble("ratingsScore"), 0);
	}

	@MessageBusChannel(
		channel = Channel.ANALYTICS_EVENTS_BLOG,
		resourcePath = "analytics_events_blog_channel_3.json"
	)
	@Test
	public void testBlogRatingsMetric2() {
		_blogNanite.run();

		JSONArray jsonArray = _cerebroInfoElasticsearchInvoker.get("blogs");

		Assert.assertEquals(1, jsonArray.length());

		JSONObject jsonObject = jsonArray.getJSONObject(0);

		Assert.assertEquals(0, jsonObject.getInt("ratings"));
		Assert.assertEquals(0.0, jsonObject.getDouble("ratingsScore"), 0);
	}

	@Autowired
	private BlogNanite _blogNanite;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_CEREBRO_INFO)
	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;

}