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

import com.liferay.osb.asah.batch.curator.bot.nanite.ActivitiesNanite;
import com.liferay.osb.asah.batch.curator.spring.OSBAsahBatchCuratorSpringBootApplication;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.messaging.Channel;
import com.liferay.osb.asah.common.messaging.MessageBus;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.messaging.MessageBusTestHelper;
import com.liferay.osb.asah.test.util.queue.http.CerebroQueueHttpTestConfiguration;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.util.Arrays;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Vishal Reddy
 * @author Leslie Wong
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(
	classes = {
		CerebroQueueHttpTestConfiguration.class,
		OSBAsahBatchCuratorSpringBootApplication.class
	}
)
public class ActivitiesNaniteTest extends BaseNaniteTestCase {

	@Before
	public void setUp() throws Exception {
		JSONObject dataSourceJSONObject =
			FaroInfoTestUtil.buildLiferayDataSourceJSONObject();

		faroInfoElasticsearchInvoker.add(
			"data-sources", dataSourceJSONObject.put("id", "1"));
	}

	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals_2.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testCanonicalUrlSet() throws Exception {
		MessageBusTestHelper messageBusTestHelper = new MessageBusTestHelper(
			_messageBus);

		messageBusTestHelper.prepareMessageBusChannel(
			Channel.ANALYTICS_EVENTS_ACTIVITY,
			ResourceUtil.readResourceToJSONArray(
				"dependencies/analytics_events_1.json", this));

		_activitiesNanite.run();

		Assert.assertEquals(
			1,
			faroInfoElasticsearchInvoker.count(
				"activities",
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery("eventId", "pageViewed")
				).filter(
					QueryBuilders.termQuery(
						"object.canonicalUrl",
						"https://customer.liferay.com/documentation/search")
				).filter(
					QueryBuilders.termQuery(
						"userId", "34209dc0-a1b7-11e8-bf1b-f987e25a7caa")
				)));
	}

	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals_2.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testCommentPostedActivityIsAdded() throws Exception {
		MessageBusTestHelper messageBusTestHelper = new MessageBusTestHelper(
			_messageBus);

		messageBusTestHelper.prepareMessageBusChannel(
			Channel.ANALYTICS_EVENTS_ACTIVITY,
			ResourceUtil.readResourceToJSONArray(
				"dependencies/analytics_events_1.json", this));

		_activitiesNanite.run();

		JSONArray assetsJSONArray = faroInfoElasticsearchInvoker.get("assets");

		Assert.assertEquals(28, assetsJSONArray.length());

		JSONObject assetJSONObject = faroInfoElasticsearchInvoker.fetch(
			"assets",
			QueryBuilders.termQuery(
				"name",
				"8 Best Practices for Your Omnichannel Strategy | Digital " +
					"Strategy | Liferay Blogs"));

		Assert.assertEquals(
			"https://www.liferay.com/blog?regionCategoryName=en-us&" +
				"blogCategoryName=digital-strategy&" +
					"title=8-best-practices-for-your-omnichannel-strategy",
			assetJSONObject.getString("dataSourceAssetPK"));

		Assert.assertTrue(
			faroInfoElasticsearchInvoker.exists(
				"activities",
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery("applicationId", "Blog")
				).filter(
					QueryBuilders.termQuery("eventId", "commentPosted")
				)));
	}

	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals_2.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testFormSubmittedPropertiesAreAdded() throws Exception {
		MessageBusTestHelper messageBusTestHelper = new MessageBusTestHelper(
			_messageBus);

		messageBusTestHelper.prepareMessageBusChannel(
			Channel.ANALYTICS_EVENTS_ACTIVITY,
			ResourceUtil.readResourceToJSONArray(
				"dependencies/analytics_events_3.json", this));

		_activitiesNanite.run();

		JSONObject activityJSONObject = faroInfoElasticsearchInvoker.fetch(
			"activities",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("eventId", "formSubmitted")));

		JSONObject eventPropertiesJSONObject = activityJSONObject.getJSONObject(
			"eventProperties");

		Assert.assertEquals(
			"24000", eventPropertiesJSONObject.getString("duration"));
		Assert.assertEquals(
			"GL Contact: Sales - MKTG FRM KK",
			eventPropertiesJSONObject.getString("title"));
	}

	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals_2.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testKeywordsLowercase() throws Exception {
		MessageBusTestHelper messageBusTestHelper = new MessageBusTestHelper(
			_messageBus);

		messageBusTestHelper.prepareMessageBusChannel(
			Channel.ANALYTICS_EVENTS_ACTIVITY,
			ResourceUtil.readResourceToJSONArray(
				"dependencies/analytics_events_1.json", this));

		_activitiesNanite.run();

		JSONObject assetJSONObject = faroInfoElasticsearchInvoker.fetch(
			"assets",
			QueryBuilders.termQuery(
				"name",
				"Felix Gogo Shell - reference - Knowledge | \"Liferay"));

		Assert.assertTrue(assetJSONObject.has("keywords"));

		String[] keywords = JSONUtil.toStringArray(
			assetJSONObject.getJSONArray("keywords"), "keyword");

		Arrays.sort(keywords);

		Assert.assertArrayEquals(
			new String[] {
				"felix gogo shell", "knowledge", "portal", "reference"
			},
			keywords);
	}

	@Autowired
	private ActivitiesNanite _activitiesNanite;

	@Autowired
	private MessageBus _messageBus;

}