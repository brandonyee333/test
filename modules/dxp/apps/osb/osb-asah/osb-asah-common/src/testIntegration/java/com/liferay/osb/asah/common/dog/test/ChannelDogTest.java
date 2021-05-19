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

package com.liferay.osb.asah.common.dog.test;

import com.liferay.osb.asah.common.dog.ChannelDog;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.entity.Channel;
import com.liferay.osb.asah.common.entity.ChannelDataSource;
import com.liferay.osb.asah.common.faro.info.dog.test.BaseFaroInfoDogTestCase;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.common.util.SetUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.annotation.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.hamcrest.Matchers;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author André Miranda
 */
@ContextConfiguration(classes = OSBAsahSpringBootApplication.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class ChannelDogTest extends BaseFaroInfoDogTestCase {

	@ElasticsearchIndex(
		name = "channels", resourcePath = "channels.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testAddChannelWithDuplicateName() {
		Channel channel = _channelDog.addChannel("channel1");

		Assert.assertEquals("channel1 (1)", channel.getName());
	}

	@ElasticsearchIndex(
		name = "accounts", resourcePath = "accounts_delete_channels.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "assets", resourcePath = "assets_delete_channels.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "blogs", resourcePath = "blogs_delete_channels.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@ElasticsearchIndex(
		name = "channels", resourcePath = "channels.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "individual-segments",
		resourcePath = "individual_segments_delete_channels.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals_delete_channels.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testDeleteChannels() throws Exception {
		_channelDog.deleteChannels(Arrays.asList(1L, 3L), null, null);

		JSONArray assetsJSONArray = faroInfoElasticsearchInvoker.get("assets");

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

		JSONArray individualSegmentsJSONArray =
			faroInfoElasticsearchInvoker.get("individual-segments");

		Assert.assertEquals(1, individualSegmentsJSONArray.length());

		JSONObject individualSegmentJSONObject =
			individualSegmentsJSONArray.getJSONObject(0);

		Assert.assertEquals(
			"2", individualSegmentJSONObject.getString("channelId"));

		JSONObject individualJSONObject = faroInfoElasticsearchInvoker.get(
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

		JSONObject accountJSONObject = faroInfoElasticsearchInvoker.get(
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

		JSONArray channelsJSONArray = faroInfoElasticsearchInvoker.get(
			"channels");

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
		Map<Long, String> channelNames = _channelDog.getChannelNamesByGroupIds(
			405201047787757795L, SetUtil.of(123L, 456L, 789L));

		Assert.assertEquals(channelNames.toString(), 2, channelNames.size());
		Assert.assertEquals("channel1", channelNames.get(123L));
		Assert.assertEquals("channel2", channelNames.get(456L));
		Assert.assertNull(channelNames.get(789L));
	}

	@ElasticsearchIndex(
		name = "channels", resourcePath = "channels.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testPatchChannelAddGroups() {
		Long dataSourceId = RandomTestUtil.randomNumber();

		_channelDog.patchChannel(
			1L, dataSourceId, SetUtil.of(456L, 789L), null);

		Channel channel = _channelDog.getChannel(1L);

		Set<ChannelDataSource> channelChannelDataSources =
			channel.getChannelDataSources();

		Stream<ChannelDataSource> stream = channelChannelDataSources.stream();

		Map<Long, List<ChannelDataSource>> channelDataSourcesByDataSourceId =
			stream.collect(
				Collectors.groupingBy(ChannelDataSource::getDataSourceId));

		List<ChannelDataSource> channelDataSources =
			channelDataSourcesByDataSourceId.get(405201047787757795L);

		ChannelDataSource channelDataSource = channelDataSources.get(0);

		Assert.assertEquals(SetUtil.of(123L), channelDataSource.getGroupIds());

		channelDataSources = channelDataSourcesByDataSourceId.get(dataSourceId);

		channelDataSource = channelDataSources.get(0);

		Assert.assertEquals(
			SetUtil.of(456L, 789L), channelDataSource.getGroupIds());

		channelDataSources = channelDataSourcesByDataSourceId.get(
			402135416847684684L);

		channelDataSource = channelDataSources.get(0);

		Assert.assertEquals(SetUtil.of(321L), channelDataSource.getGroupIds());
	}

	@ElasticsearchIndex(
		name = "channels", resourcePath = "channels.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testPatchChannelName() {
		String name = RandomTestUtil.randomString();

		_channelDog.patchChannel(1L, null, null, name);

		Channel channel = _channelDog.getChannel(1L);

		Assert.assertEquals(name, channel.getName());
	}

	@ElasticsearchIndex(
		name = "channels", resourcePath = "channels.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testPatchChannelReplaceGroups() {
		_channelDog.patchChannel(
			1L, 405201047787757795L, SetUtil.of(456L, 789L), null);

		Channel channel = _channelDog.getChannel(1L);

		ChannelDataSource channelDataSource1 = _findFirstChannelDataSource(
			405201047787757795L, channel.getChannelDataSources());

		Assert.assertEquals(
			SetUtil.of(456L, 789L), channelDataSource1.getGroupIds());

		ChannelDataSource channelDataSource2 = _findFirstChannelDataSource(
			402135416847684684L, channel.getChannelDataSources());

		Assert.assertEquals(SetUtil.of(321L), channelDataSource2.getGroupIds());
	}

	@ElasticsearchIndex(
		name = "channels", resourcePath = "channels.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testPatchChannelWithDuplicateName() {
		Channel channel = _channelDog.patchChannel(2L, null, null, "channel1");

		Assert.assertEquals("channel1 (1)", channel.getName());
	}

	private ChannelDataSource _findFirstChannelDataSource(
		Long dataSourceId, Set<ChannelDataSource> channelDataSources) {

		for (ChannelDataSource channelDataSource : channelDataSources) {
			if (Objects.equals(
					channelDataSource.getDataSourceId(), dataSourceId)) {

				return channelDataSource;
			}
		}

		return null;
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_CEREBRO_INFO)
	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;

	@Autowired
	private ChannelDog _channelDog;

}