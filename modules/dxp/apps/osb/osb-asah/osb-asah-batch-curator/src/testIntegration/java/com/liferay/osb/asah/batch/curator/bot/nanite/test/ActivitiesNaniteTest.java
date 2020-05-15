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
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.queue.http.CerebroQueueHttpTestConfiguration;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;
import com.liferay.osb.asah.test.util.spring.cache.OSBAsahRedisDisabledTestConfiguration;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Vishal Reddy
 * @author Leslie Wong
 */
@ContextConfiguration(classes = OSBAsahBatchCuratorSpringBootApplication.class)
@Import(
	{
		CerebroQueueHttpTestConfiguration.class,
		OSBAsahRedisDisabledTestConfiguration.class
	}
)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class ActivitiesNaniteTest extends BaseNaniteTestCase {

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		_cerebroRawElasticsearchInvoker =
			_elasticsearchInvokerFactory.forCerebroRaw();

		_dataSourceJSONObject = faroInfoElasticsearchInvoker.add(
			"data-sources",
			FaroInfoTestUtil.buildLiferayDataSourceJSONObject());
	}

	@Test
	public void testCommentPostedActivityIsAdded() throws Exception {
		_cerebroRawElasticsearchInvoker.add(
			"analytics-events",
			_getAnalyticsEventJSONArray("analytics-events-1.json"), 50);

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

	@Test
	public void testFormSubmittedPropertiesAreAdded() throws Exception {
		_cerebroRawElasticsearchInvoker.add(
			"analytics-events",
			_getAnalyticsEventJSONArray("analytics-events-3.json"), 50);

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

	private JSONArray _getAnalyticsEventJSONArray(String resourceName)
		throws Exception {

		String analyticsEventsJSON = ResourceUtil.readResourceToString(
			"dependencies/osbasahcerebroraw/" + resourceName, this);

		return new JSONArray(
			analyticsEventsJSON.replace(
				"[$DATA_SOURCE_ID$]", _dataSourceJSONObject.getString("id")));
	}

	@Autowired
	private ActivitiesNanite _activitiesNanite;

	private ElasticsearchInvoker _cerebroRawElasticsearchInvoker;
	private JSONObject _dataSourceJSONObject;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

}