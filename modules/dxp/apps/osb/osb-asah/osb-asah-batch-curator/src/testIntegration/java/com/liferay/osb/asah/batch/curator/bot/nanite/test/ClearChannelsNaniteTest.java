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

package com.liferay.osb.asah.batch.curator.bot.nanite.test;

import com.liferay.osb.asah.batch.curator.bot.nanite.ClearChannelsNanite;
import com.liferay.osb.asah.batch.curator.spring.OSBAsahBatchCuratorSpringBootApplication;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoDataSourceDog;
import com.liferay.osb.asah.common.http.ChannelHttp;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.queue.http.CerebroQueueHttpTestConfiguration;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONObject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

/**
 * @author André Miranda
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(
	classes = {
		CerebroQueueHttpTestConfiguration.class,
		OSBAsahBatchCuratorSpringBootApplication.class
	}
)
public class ClearChannelsNaniteTest extends BaseNaniteTestCase {

	@Test
	public void test() throws Exception {
		JSONObject dataSourceJSONObject = _faroInfoDataSourceDog.addDataSource(
			FaroInfoTestUtil.buildLiferayDataSourceJSONObject());

		String channelId = dataSourceJSONObject.getString("channelId");

		JSONObject accountJSONObject = faroInfoElasticsearchInvoker.add(
			"accounts",
			FaroInfoTestUtil.buildAccountJSONObject(dataSourceJSONObject));

		faroInfoElasticsearchInvoker.add(
			"individual-segments",
			FaroInfoTestUtil.buildAccountIndividualSegmentJSONObject(
				accountJSONObject, channelId));

		JSONObject individualJSONObject = faroInfoElasticsearchInvoker.add(
			"individuals",
			FaroInfoTestUtil.buildIndividualJSONObject(
				channelId, dataSourceJSONObject));

		JSONObject activityGroupJSONObject = faroInfoElasticsearchInvoker.add(
			"activity-groups",
			FaroInfoTestUtil.buildActivityGroupJSONObject(
				channelId, dataSourceJSONObject.getString("id"),
				DateUtil.newDateString(), individualJSONObject));

		JSONObject assetJSONObject = faroInfoElasticsearchInvoker.add(
			"assets",
			FaroInfoTestUtil.buildAssetJSONObject(
				"Page", channelId, dataSourceJSONObject.getString("id")));

		JSONObject activityJSONObject = faroInfoElasticsearchInvoker.add(
			"activities",
			FaroInfoTestUtil.buildActivityJSONObject(
				activityGroupJSONObject, assetJSONObject, channelId,
				"pageViewed", new String[] {"pageLoadTime", "1000"}));

		_clearChannelsNanite.run(
			JSONUtil.put("channelIds", JSONUtil.put(channelId)));

		Assert.assertTrue(
			faroInfoElasticsearchInvoker.exists(
				"accounts", accountJSONObject.getString("id")));
		Assert.assertFalse(
			faroInfoElasticsearchInvoker.exists(
				"activities", activityJSONObject.getString("id")));
		Assert.assertFalse(
			faroInfoElasticsearchInvoker.exists(
				"activity-groups", activityGroupJSONObject.getString("id")));
		Assert.assertFalse(
			faroInfoElasticsearchInvoker.exists(
				"assets", assetJSONObject.getString("id")));
		Assert.assertTrue(
			faroInfoElasticsearchInvoker.exists("channels", channelId));
		Assert.assertTrue(
			faroInfoElasticsearchInvoker.exists(
				"data-sources", dataSourceJSONObject.getString("id")));
		Assert.assertFalse(
			faroInfoElasticsearchInvoker.exists(
				"individual-segments",
				QueryBuilders.termQuery(
					"name", "Account: " + accountJSONObject.getString("id"))));
		Assert.assertFalse(
			faroInfoElasticsearchInvoker.exists(
				"individuals",
				QueryBuilders.termQuery("channelIds", channelId)));
		Assert.assertEquals(
			1,
			faroInfoElasticsearchInvoker.count(
				"individuals", QueryBuilders.matchAllQuery()));
	}

	@MockBean
	private ChannelHttp _channelHttp;

	@Autowired
	private ClearChannelsNanite _clearChannelsNanite;

	@Autowired
	private FaroInfoDataSourceDog _faroInfoDataSourceDog;

}