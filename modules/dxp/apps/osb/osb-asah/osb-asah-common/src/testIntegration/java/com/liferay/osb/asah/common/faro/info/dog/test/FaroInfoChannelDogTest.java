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

package com.liferay.osb.asah.common.faro.info.dog.test;

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoChannelDog;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.common.util.SetUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import org.hamcrest.Matchers;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author André Miranda
 */
@ContextConfiguration(classes = OSBAsahSpringBootApplication.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class FaroInfoChannelDogTest extends BaseFaroInfoDogTestCase {

	@Before
	public void setUp() throws Exception {
		super.setUp();

		_cerebroInfoElasticsearchInvoker =
			_elasticsearchInvokerFactory.forCerebroInfo();
	}

	@ElasticsearchIndex(
		name = "channels", resourcePath = "channels.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testAddChannelWithDuplicateName() {
		JSONObject channelJSONObject = _faroInfoChannelTest.addChannel(
			Collections.emptyList(), "channel1");

		Assert.assertEquals("channel1 (1)", channelJSONObject.get("name"));
	}

	@ElasticsearchIndex(
		name = "accounts", resourcePath = "accounts-delete-channels.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "assets", resourcePath = "assets-delete-channels.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "blogs", resourcePath = "blogs-delete-channels.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@ElasticsearchIndex(
		name = "channels", resourcePath = "channels.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "individual-segments",
		resourcePath = "individual-segments-delete-channels.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals-delete-channels.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testDeleteChannels() throws Exception {
		_faroInfoChannelTest.deleteChannels(
			Arrays.asList("1", "3"), null, null);

		JSONArray assetsJSONArray = elasticsearchInvoker.get("assets");

		Assert.assertEquals(2, assetsJSONArray.length());

		JSONObject assetJSONObject1 = JSONUtil.find(
			assetsJSONArray, "id", "386700631786606770");

		Assert.assertThat(
			new String[] {"2"},
			Matchers.arrayContainingInAnyOrder(
				JSONUtil.toStringArray(
					assetJSONObject1.getJSONArray("channelIds"))));

		JSONObject assetJSONObject2 = JSONUtil.find(
			assetsJSONArray, "id", "386700631786606772");

		Assert.assertThat(
			new String[] {"2", "4"},
			Matchers.arrayContainingInAnyOrder(
				JSONUtil.toStringArray(
					assetJSONObject2.getJSONArray("channelIds"))));

		JSONArray blogsJSONArray = _cerebroInfoElasticsearchInvoker.get(
			"blogs");

		Assert.assertEquals(1, blogsJSONArray.length());

		JSONObject blogsJSONObject = blogsJSONArray.getJSONObject(0);

		Assert.assertEquals("2", blogsJSONObject.getString("channelId"));

		JSONArray individualSegmentsJSONArray = elasticsearchInvoker.get(
			"individual-segments");

		Assert.assertEquals(1, individualSegmentsJSONArray.length());

		JSONObject individualSegmentJSONObject =
			individualSegmentsJSONArray.getJSONObject(0);

		Assert.assertEquals(
			"2", individualSegmentJSONObject.getString("channelId"));

		JSONObject individualJSONObject = elasticsearchInvoker.get(
			"individuals", "338486037253283140");

		JSONArray individualActivitiesCountsJSONArray =
			individualJSONObject.getJSONArray("activitiesCounts");

		Assert.assertEquals(0, individualActivitiesCountsJSONArray.length());

		JSONArray channelIdsJSONArray = individualJSONObject.getJSONArray(
			"channelIds");

		Assert.assertEquals(0, channelIdsJSONArray.length());

		JSONArray individualLastActivityDatesJSONArray =
			individualJSONObject.getJSONArray("lastActivityDates");

		Assert.assertEquals(0, individualLastActivityDatesJSONArray.length());

		JSONObject accountJSONObject = elasticsearchInvoker.get(
			"accounts", "342313458385210529");

		JSONArray accountActivitiesCountsJSONArray =
			accountJSONObject.getJSONArray("activitiesCounts");

		Assert.assertEquals(0, accountActivitiesCountsJSONArray.length());

		JSONArray accountIndividualCountsJSONArray =
			accountJSONObject.getJSONArray("individualCounts");

		Assert.assertEquals(1, accountIndividualCountsJSONArray.length());

		JSONObject accountIndividualCountsJSONObject =
			accountIndividualCountsJSONArray.getJSONObject(0);

		Assert.assertEquals(
			"2", accountIndividualCountsJSONObject.getString("channelId"));

		JSONArray channelsJSONArray = elasticsearchInvoker.get("channels");

		Assert.assertEquals(1, channelsJSONArray.length());

		JSONObject channelJSONObject = channelsJSONArray.getJSONObject(0);

		Assert.assertEquals("2", channelJSONObject.getString("id"));
	}

	@ElasticsearchIndex(
		name = "channels", resourcePath = "channels.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetChannelNamesByGroupIds() {
		Map<String, String> channelNames =
			_faroInfoChannelTest.getChannelNamesByGroupIds(
				"405201047787757795", SetUtil.of("123", "456", "789"));

		Assert.assertEquals(channelNames.toString(), 2, channelNames.size());
		Assert.assertEquals("channel1", channelNames.get("123"));
		Assert.assertEquals("channel2", channelNames.get("456"));
		Assert.assertNull(channelNames.get("789"));
	}

	@ElasticsearchIndex(
		name = "channels", resourcePath = "channels.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testPatchChannelAddGroups() {
		String dataSourceId = RandomTestUtil.randomId();

		_faroInfoChannelTest.patchChannel(
			"1",
			JSONUtil.put(
				"dataSourceId", dataSourceId
			).put(
				"groups",
				JSONUtil.putAll(
					JSONUtil.put("id", "456"), JSONUtil.put("id", "789"))
			));

		JSONObject channelJSONObject = elasticsearchInvoker.get(
			"channels", "1");

		JSONArray dataSourcesJSONArray = channelJSONObject.getJSONArray(
			"dataSources");

		JSONAssert.assertEquals(
			JSONUtil.putAll(
				JSONUtil.put(
					"groupIds", JSONUtil.put("123")
				).put(
					"id", "405201047787757795"
				),
				JSONUtil.put(
					"groupIds", JSONUtil.putAll("456", "789")
				).put(
					"id", dataSourceId
				),
				JSONUtil.put(
					"groupIds", JSONUtil.put("000")
				).put(
					"id", "402135416847684684"
				)),
			dataSourcesJSONArray, false);
	}

	@ElasticsearchIndex(
		name = "channels", resourcePath = "channels.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testPatchChannelName() {
		String name = RandomTestUtil.randomString();

		_faroInfoChannelTest.patchChannel("1", JSONUtil.put("name", name));

		JSONObject channelJSONObject = elasticsearchInvoker.get(
			"channels", "1");

		Assert.assertEquals(name, channelJSONObject.getString("name"));
	}

	@ElasticsearchIndex(
		name = "channels", resourcePath = "channels.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testPatchChannelReplaceGroups() {
		_faroInfoChannelTest.patchChannel(
			"1",
			JSONUtil.put(
				"dataSourceId", "405201047787757795"
			).put(
				"groups",
				JSONUtil.putAll(
					JSONUtil.put("id", "456"), JSONUtil.put("id", "789"))
			));

		JSONObject channelJSONObject = elasticsearchInvoker.get(
			"channels", "1");

		JSONAssert.assertEquals(
			JSONUtil.put(
				"dataSources",
				JSONUtil.putAll(
					JSONUtil.put(
						"groupIds", JSONUtil.putAll("456", "789")
					).put(
						"id", "405201047787757795"
					),
					JSONUtil.put(
						"groupIds", JSONUtil.put("000")
					).put(
						"id", "402135416847684684"
					))
			).put(
				"name", "channel1"
			),
			channelJSONObject, false);
	}

	@ElasticsearchIndex(
		name = "channels", resourcePath = "channels.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testPatchChannelWithDuplicateName() {
		JSONObject patchJSONObject = _faroInfoChannelTest.patchChannel(
			"2", JSONUtil.put("name", "channel1"));

		JSONObject channelJSONObject = patchJSONObject.getJSONObject("channel");

		Assert.assertEquals("channel1 (1)", channelJSONObject.get("name"));
	}

	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

	@Autowired
	private FaroInfoChannelDog _faroInfoChannelTest;

}