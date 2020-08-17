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

package com.liferay.osb.asah.backend.rest.controller.test;

import com.liferay.osb.asah.backend.rest.controller.DataSourcesRestController;
import com.liferay.osb.asah.backend.rest.controller.api.data.source.v1.ChannelsRestController;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.http.ChannelHttp;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.spring.http.exception.OSBAsahException;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.IntStream;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.HttpClientErrorException;

/**
 * @author Geyson Silva
 */
@ContextConfiguration(classes = OSBAsahBackendSpringBootApplication.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class ChannelsRestControllerTest {

	@Before
	public void setUp() {
		_elasticsearchInvoker = _elasticsearchInvokerFactory.forFaroInfo();
	}

	@Test
	public void testDuplicateChannelName() throws Exception {
		JSONObject dataSourceJSONObject = new JSONObject(
			_dataSourcesRestController.postDataSource(
				String.valueOf(
					FaroInfoTestUtil.buildLiferayDataSourceJSONObject(
						"Liferay", RandomTestUtil.randomURL()))));

		JSONObject channelJSONObject = FaroInfoTestUtil.buildChannelJSONObject(
			dataSourceJSONObject.getString("id"), "combined");

		for (int i = 0; i < 4; i++) {
			_channelsRestController.postChannels(
				String.valueOf(channelJSONObject));
		}

		JSONObject channelsJSONObject = new JSONObject(
			_channelsRestController.getChannels(null, 0, 20, null));

		JSONArray channelsJSONArray = (JSONArray)channelsJSONObject.query(
			"/_embedded/channels");

		Assert.assertEquals(5, channelsJSONArray.length());

		Set<String> channelNames = new HashSet<>();

		for (int i = 0; i < channelsJSONArray.length(); i++) {
			channelJSONObject = channelsJSONArray.getJSONObject(i);

			channelNames.add(channelJSONObject.getString("name"));
		}

		Assert.assertTrue(channelNames.contains("Liferay Combined Property"));
		Assert.assertTrue(
			channelNames.contains("Liferay Combined Property (1)"));
		Assert.assertTrue(
			channelNames.contains("Liferay Combined Property (2)"));
		Assert.assertTrue(
			channelNames.contains("Liferay Combined Property (3)"));
	}

	@ElasticsearchIndex(
		name = "channels", resourcePath = "channels_1.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetChannels() throws Exception {
		JSONObject channelsJSONObject = new JSONObject(
			_channelsRestController.getChannels(null, 0, 20, null));

		JSONArray channelsJSONArray = (JSONArray)channelsJSONObject.query(
			"/_embedded/channels");

		Assert.assertEquals(3, channelsJSONArray.length());
	}

	@Test
	public void testHandleFaroFailure() throws Exception {
		Mockito.doThrow(
			new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR)
		).when(
			_channelHttp
		).addChannel(
			Mockito.any(JSONObject.class)
		);

		try {
			JSONObject dataSourceJSONObject = new JSONObject(
				_dataSourcesRestController.postDataSource(
					String.valueOf(
						FaroInfoTestUtil.buildLiferayDataSourceJSONObject())));

			new JSONArray(
				_channelsRestController.postChannels(
					String.valueOf(
						FaroInfoTestUtil.buildChannelJSONObject(
							dataSourceJSONObject.getString("id"),
							"combined"))));
		}
		catch (OSBAsahException osbae) {
			Assert.assertEquals("Unable to create channel", osbae.getMessage());

			Assert.assertEquals(
				0,
				_elasticsearchInvoker.count(
					"channels", QueryBuilders.matchAllQuery()));

			return;
		}

		Assert.fail("postChannels did not throw an exception");
	}

	@ElasticsearchIndex(
		name = "data-sources", resourcePath = "data_sources.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testPatchChannelExistingDataSource() {
		String dataSourceId = "351238757269547424";

		JSONArray channelsJSONArray = new JSONArray(
			_channelsRestController.postChannels(
				String.valueOf(
					FaroInfoTestUtil.buildChannelJSONObject(
						dataSourceId, "combined"))));

		JSONObject channelJSONObject = channelsJSONArray.getJSONObject(0);

		String randomGroupId = RandomTestUtil.randomId();

		JSONObject inputChannelJSONObject = JSONUtil.put(
			"dataSourceId", dataSourceId
		).put(
			"groups",
			Collections.singleton(
				JSONUtil.put(
					"id", randomGroupId
				).put(
					"name", RandomTestUtil.randomString()
				))
		);

		JSONObject responseJSONObject = new JSONObject(
			_channelsRestController.patchChannel(
				channelJSONObject.getString("id"),
				inputChannelJSONObject.toString()));

		JSONObject actualChannelJSONObject = responseJSONObject.getJSONObject(
			"channel");

		JSONArray dataSourcesJSONArray = actualChannelJSONObject.getJSONArray(
			"dataSources");

		JSONObject dataSourceJSONObject = dataSourcesJSONArray.getJSONObject(0);

		JSONArray groupIdsJSONArray = dataSourceJSONObject.getJSONArray(
			"groupIds");

		Assert.assertEquals(randomGroupId, groupIdsJSONArray.getString(0));
	}

	@ElasticsearchIndex(
		name = "channels", resourcePath = "channels_1.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testPatchChannelNewDataSource() {
		String dataSourceId = RandomTestUtil.randomId();
		String randomGroupId = RandomTestUtil.randomId();

		JSONObject inputChannelJSONObject = JSONUtil.put(
			"dataSourceId", dataSourceId
		).put(
			"groups",
			Collections.singleton(
				JSONUtil.put(
					"id", randomGroupId
				).put(
					"name", RandomTestUtil.randomString()
				))
		);

		JSONObject responseJSONObject = new JSONObject(
			_channelsRestController.patchChannel(
				"123456789", inputChannelJSONObject.toString()));

		JSONObject actualChannelJSONObject = responseJSONObject.getJSONObject(
			"channel");

		JSONArray dataSourcesJSONArray = actualChannelJSONObject.getJSONArray(
			"dataSources");

		Assert.assertEquals(2, dataSourcesJSONArray.length());

		Optional<JSONObject> dataSourceOptional = IntStream.range(
			0, dataSourcesJSONArray.length()
		).mapToObj(
			dataSourcesJSONArray::getJSONObject
		).filter(
			dataSourceJSONObject -> dataSourceId.equals(
				dataSourceJSONObject.getString("id"))
		).findFirst();

		Assert.assertTrue(dataSourceOptional.isPresent());

		JSONObject dataSourceJSONObject = dataSourceOptional.get();

		JSONArray groupIdsJSONArray = dataSourceJSONObject.getJSONArray(
			"groupIds");

		Assert.assertEquals(randomGroupId, groupIdsJSONArray.getString(0));
	}

	@ElasticsearchIndex(
		name = "channels", resourcePath = "channels_1.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testPatchChannelRename() {
		String randomName = RandomTestUtil.randomString();

		JSONObject inputChannelJSONObject = JSONUtil.put("name", randomName);

		JSONObject responseJSONObject = new JSONObject(
			_channelsRestController.patchChannel(
				"123456789", inputChannelJSONObject.toString()));

		JSONObject actualChannelJSONObject = responseJSONObject.getJSONObject(
			"channel");

		Assert.assertEquals(
			randomName, actualChannelJSONObject.getString("name"));
	}

	@Test
	public void testPostCombinedChannels() throws Exception {
		JSONObject dataSourceJSONObject = new JSONObject(
			_dataSourcesRestController.postDataSource(
				String.valueOf(
					FaroInfoTestUtil.buildLiferayDataSourceJSONObject())));

		JSONArray channelsJSONArray = new JSONArray(
			_channelsRestController.postChannels(
				String.valueOf(
					FaroInfoTestUtil.buildChannelJSONObject(
						dataSourceJSONObject.getString("id"), "combined"))));

		Assert.assertEquals(1, channelsJSONArray.length());

		Mockito.verify(
			_channelHttp, Mockito.times(2)
		).addChannel(
			Mockito.any(JSONObject.class)
		);
	}

	@Test
	public void testPostMultipleChannels() throws Exception {
		JSONObject dataSourceJSONObject = new JSONObject(
			_dataSourcesRestController.postDataSource(
				String.valueOf(
					FaroInfoTestUtil.buildLiferayDataSourceJSONObject())));

		JSONArray channelsJSONArray = new JSONArray(
			_channelsRestController.postChannels(
				String.valueOf(
					FaroInfoTestUtil.buildChannelJSONObject(
						dataSourceJSONObject.getString("id"), "multiple"))));

		Assert.assertEquals(2, channelsJSONArray.length());

		Mockito.verify(
			_channelHttp, Mockito.times(3)
		).addChannel(
			Mockito.any(JSONObject.class)
		);
	}

	@ElasticsearchIndex(
		name = "channels", resourcePath = "channels_1.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testRemovedGroupIds() {
		JSONObject inputChannelJSONObject = JSONUtil.put(
			"dataSourceId", "123456789"
		).put(
			"groups",
			Collections.singleton(
				JSONUtil.put(
					"id", RandomTestUtil.randomId()
				).put(
					"name", RandomTestUtil.randomString()
				))
		);

		JSONObject responseJSONObject = new JSONObject(
			_channelsRestController.patchChannel(
				"4324324324", inputChannelJSONObject.toString()));

		JSONArray removedGroupIdsJSONArray = responseJSONObject.optJSONArray(
			"removedGroupIds");

		Assert.assertEquals(2, removedGroupIdsJSONArray.length());

		Set<String> removedGroupIds = JSONUtil.toStringSet(
			removedGroupIdsJSONArray);

		Assert.assertTrue(removedGroupIds.contains("123456"));
		Assert.assertTrue(removedGroupIds.contains("654321"));
	}

	@MockBean
	private ChannelHttp _channelHttp;

	@Autowired
	private ChannelsRestController _channelsRestController;

	@Autowired
	private DataSourcesRestController _dataSourcesRestController;

	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

}