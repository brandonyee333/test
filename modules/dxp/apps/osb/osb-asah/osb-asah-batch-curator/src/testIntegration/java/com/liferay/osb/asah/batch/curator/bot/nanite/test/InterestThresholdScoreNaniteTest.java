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

import com.liferay.osb.asah.batch.curator.bot.nanite.InterestThresholdScoreNanite;
import com.liferay.osb.asah.batch.curator.bot.nanite.arm.InterestScoreArm;
import com.liferay.osb.asah.batch.curator.spring.OSBAsahBatchCuratorSpringBootApplication;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.queue.http.CerebroQueueHttpTestConfiguration;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

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
 * @author Leslie Wong
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(
	classes = {
		CerebroQueueHttpTestConfiguration.class,
		OSBAsahBatchCuratorSpringBootApplication.class
	}
)
public class InterestThresholdScoreNaniteTest extends BaseNaniteTestCase {

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		_dataSourceJSONObject = faroInfoElasticsearchInvoker.add(
			"data-sources",
			FaroInfoTestUtil.buildLiferayDataSourceJSONObject());

		_individualJSONObject = faroInfoElasticsearchInvoker.add(
			"individuals",
			FaroInfoTestUtil.buildIndividualJSONObject(_dataSourceJSONObject));
	}

	@Test
	public void testNoThresholdWhenNoKeywords() throws Exception {
		_interestThresholdScoreNanite.run(null);

		Assert.assertFalse(
			faroInfoElasticsearchInvoker.exists(
				"OSBAsahMarkers",
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery(
						"id", "InterestThresholdScoreNanite")
				).filter(
					QueryBuilders.existsQuery("score")
				)));
	}

	@Test
	public void testThresholdComesFromKeywordWithMostViews() throws Exception {
		_addPageVisitActivities(1, 2, "bar");
		_addPageVisitActivities(2, 2, "foo");
		_addPageVisitActivities(3, 2, "test");

		_interestThresholdScoreNanite.run(null);

		Assert.assertEquals(
			_interestScoreArm.computeThresholdScore("test"),
			_getInterestThresholdScore(), _DELTA);
	}

	@Test
	public void testThresholdIsMinimumWhen0Activities() throws Exception {
		faroInfoElasticsearchInvoker.add(
			"assets",
			FaroInfoTestUtil.buildPageAssetJSONObject(
				_dataSourceJSONObject.getString("id")));

		_interestThresholdScoreNanite.run(null);

		Assert.assertEquals(
			InterestScoreArm.MINIMUM_THRESHOLD_SCORE,
			_getInterestThresholdScore(), _DELTA);
	}

	@Test
	public void testThresholdIsMinimumWhen0ActivitiesInPast30Days()
		throws Exception {

		_addPageVisitActivities(1, 30, "bar");
		_addPageVisitActivities(2, 30, "foo");

		_interestThresholdScoreNanite.run(null);

		Assert.assertEquals(
			InterestScoreArm.MINIMUM_THRESHOLD_SCORE,
			_getInterestThresholdScore(), _DELTA);
	}

	@Test
	public void testThresholdIsMinimumWith1Page() throws Exception {
		_addPageVisitActivities(5, 2, "bar", "foo", "test");

		_interestThresholdScoreNanite.run(null);

		Assert.assertEquals(
			InterestScoreArm.MINIMUM_THRESHOLD_SCORE,
			_getInterestThresholdScore(), _DELTA);
	}

	private void _addPageVisitActivities(
			int count, int days, String... keywords)
		throws Exception {

		JSONObject activityGroupJSONObject = faroInfoElasticsearchInvoker.add(
			"activity-groups",
			FaroInfoTestUtil.buildActivityGroupJSONObject(
				_dataSourceJSONObject.getString("id"),
				DateUtil.addDays(DateUtil.newDayDateString(), -days),
				_individualJSONObject));

		JSONObject pageAssetJSONObject = null;

		if (keywords.length > 0) {
			JSONArray keywordsJSONArray = new JSONArray();

			for (String keyword : keywords) {
				keywordsJSONArray.put(
					JSONUtil.put(
						"keyword", keyword
					).put(
						"type", "keyword"
					));
			}

			pageAssetJSONObject = faroInfoElasticsearchInvoker.add(
				"assets",
				FaroInfoTestUtil.buildPageAssetJSONObject(
					_dataSourceJSONObject.getString("id"), keywordsJSONArray));
		}
		else {
			pageAssetJSONObject = faroInfoElasticsearchInvoker.add(
				"assets",
				FaroInfoTestUtil.buildPageAssetJSONObject(
					_dataSourceJSONObject.getString("id")));
		}

		for (int i = 0; i < count; i++) {
			String pageViewActivityId = RandomTestUtil.randomUUID();

			faroInfoElasticsearchInvoker.add(
				"activities",
				FaroInfoTestUtil.buildActivityJSONObject(
					activityGroupJSONObject, pageAssetJSONObject,
					"pageUnloaded", new String[] {"viewDuration", "30000"},
					pageViewActivityId));
			faroInfoElasticsearchInvoker.add(
				"activities",
				FaroInfoTestUtil.buildActivityJSONObject(
					activityGroupJSONObject, pageAssetJSONObject, "pageViewed",
					new String[] {"pageLoadTime", "1000"}, pageViewActivityId));
		}
	}

	private double _getInterestThresholdScore() {
		JSONObject osbAsahMarkerJSONObject = faroInfoElasticsearchInvoker.fetch(
			"OSBAsahMarkers",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("id", "InterestThresholdScoreNanite")));

		return osbAsahMarkerJSONObject.getDouble("score");
	}

	private static final double _DELTA = 0.00001;

	private JSONObject _dataSourceJSONObject;
	private JSONObject _individualJSONObject;

	@Autowired
	private InterestScoreArm _interestScoreArm;

	@Autowired
	private InterestThresholdScoreNanite _interestThresholdScoreNanite;

}