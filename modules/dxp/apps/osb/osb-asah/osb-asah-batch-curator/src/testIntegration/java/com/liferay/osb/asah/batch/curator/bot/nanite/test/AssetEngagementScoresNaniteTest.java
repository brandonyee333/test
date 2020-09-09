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
import com.liferay.osb.asah.batch.curator.bot.nanite.AssetEngagementScoresNanite;
import com.liferay.osb.asah.batch.curator.spring.OSBAsahBatchCuratorSpringBootApplication;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dxp.extractor.dog.DXPExtractorConfigurationDog;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoDataSourceDog;
import com.liferay.osb.asah.common.http.ChannelHttp;
import com.liferay.osb.asah.common.messaging.Channel;
import com.liferay.osb.asah.common.messaging.MessageBus;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.messaging.MessageBusTestHelper;
import com.liferay.osb.asah.test.util.queue.http.CerebroQueueHttpTestConfiguration;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * @author Vishal Reddy
 * @author Edward Kwok-Yu Wong
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(
	classes = {
		CerebroQueueHttpTestConfiguration.class,
		OSBAsahBatchCuratorSpringBootApplication.class
	}
)
public class AssetEngagementScoresNaniteTest extends BaseNaniteTestCase {

	@Test
	public void test() throws Exception {
		JSONObject dataSourceJSONObject =
			FaroInfoTestUtil.buildLiferayDataSourceJSONObject();

		_faroInfoDataSourceDog.addDataSource(
			dataSourceJSONObject.put("id", "1"));

		MessageBusTestHelper messageBusTestHelper = new MessageBusTestHelper(
			_messageBus);

		messageBusTestHelper.prepareMessageBusChannel(
			Channel.ANALYTICS_EVENTS_ACTIVITY,
			ResourceUtil.readResourceToJSONArray(
				"dependencies/analytics_events_1.json", this));

		_activitiesNanite.run();

		String activityStartDayDateString = "2018-09-10T00:00:00.000Z";

		String dayBeforeDayDateString = DateUtil.addDays(
			activityStartDayDateString, -1);

		_assetEngagementScoresNanite.run(dayBeforeDayDateString);

		JSONObject assetJSONObject = faroInfoElasticsearchInvoker.fetch(
			"assets",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("assetType", "Page")
			).filter(
				QueryBuilders.termQuery(
					"dataSourceAssetPK",
					"https://customer.liferay.com/documentation/7.1/deploy")
			));

		String assetId = assetJSONObject.getString("id");

		_assertAssetEngagementScore(assetId, dayBeforeDayDateString, 0, 0);

		for (int i = 0; i < AssetEngagementScoresNanite.DAYS; i++) {
			String currentDate = DateUtil.addDays(
				activityStartDayDateString, i);

			_assetEngagementScoresNanite.run(currentDate);

			_assertAssetEngagementScore(assetId, currentDate, 0, 1);
		}

		String dayAfterDayDateString = DateUtil.addDays(
			activityStartDayDateString, AssetEngagementScoresNanite.DAYS + 1);

		_assetEngagementScoresNanite.run(dayAfterDayDateString);

		_assertAssetEngagementScore(assetId, dayAfterDayDateString, 0, 0);
	}

	@TestConfiguration
	public static class AssetEngagementScoresNaniteTestConfiguration {

		@Bean
		@Primary
		public DXPExtractorConfigurationDog dxpExtractorConfigurationDog() {
			return Mockito.mock(DXPExtractorConfigurationDog.class);
		}

	}

	private void _assertAssetEngagementScore(
		String assetId, String dayDateString, double lowerBound,
		double upperBound) {

		JSONArray engagementsJSONArray = faroInfoElasticsearchInvoker.get(
			"engagements",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("dateRecorded", dayDateString)
			).filter(
				QueryBuilders.termQuery("ownerId", assetId)
			).filter(
				QueryBuilders.termQuery("ownerType", "asset")
			));

		Assert.assertTrue(
			"More than one engagements were found for asset " + assetId +
				" on date " + dayDateString,
			engagementsJSONArray.length() <= 1);

		double score = 0;

		if (engagementsJSONArray.length() > 0) {
			JSONObject engagementJSONObject =
				engagementsJSONArray.getJSONObject(0);

			score = engagementJSONObject.getDouble("score");

			Assert.assertNotEquals(
				"Engagements with a score of 0 should not be stored in the " +
					"data service",
				0, score);
		}

		Assert.assertTrue(
			"Expected engagement score for asset " + assetId + " on date " +
				dayDateString + " to be in the range [" + lowerBound + ", " +
					upperBound + "], but was " + score,
			(score >= lowerBound) && (score <= upperBound));
	}

	@Autowired
	private ActivitiesNanite _activitiesNanite;

	@Autowired
	private AssetEngagementScoresNanite _assetEngagementScoresNanite;

	@MockBean
	private ChannelHttp _channelHttp;

	@Autowired
	private FaroInfoDataSourceDog _faroInfoDataSourceDog;

	@Autowired
	private MessageBus _messageBus;

}