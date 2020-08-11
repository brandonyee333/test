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

import com.liferay.osb.asah.batch.curator.bot.nanite.IndividualEngagementScoresNanite;
import com.liferay.osb.asah.batch.curator.spring.OSBAsahBatchCuratorSpringBootApplication;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dxp.extractor.dog.DXPExtractorConfigurationDog;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoActivityDog;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoDataSourceDog;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoIndividualDog;
import com.liferay.osb.asah.common.http.ChannelHttp;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.queue.http.CerebroQueueHttpTestConfiguration;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;
import com.liferay.osb.asah.test.util.spring.cache.OSBAsahRedisDisabledTestConfiguration;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import java.util.Collections;
import java.util.Map;

import org.apache.commons.lang3.RandomUtils;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Michael Bowerman
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
@SpringBootTest
public class IndividualEngagementScoresNaniteTest extends BaseNaniteTestCase {

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		_dataSourceJSONObject = _faroInfoDataSourceDog.addDataSource(
			FaroInfoTestUtil.buildLiferayDataSourceJSONObject());

		_formAssetsJSONArray = new JSONArray();
		_pageAssetsJSONArray = new JSONArray();

		for (int i = 0; i < 20; i++) {
			_formAssetsJSONArray.put(
				faroInfoElasticsearchInvoker.add(
					"assets",
					FaroInfoTestUtil.buildAssetJSONObject(
						"Form", _dataSourceJSONObject.getString("id"))));
			_pageAssetsJSONArray.put(
				faroInfoElasticsearchInvoker.add(
					"assets",
					FaroInfoTestUtil.buildPageAssetJSONObject(
						_dataSourceJSONObject.getString("id"))));
		}

		_individualJSONObject = _faroInfoIndividualDog.addIndividual(
			FaroInfoTestUtil.buildIndividualJSONObject(_dataSourceJSONObject),
			false);
	}

	@Test
	public void testFilterActivitiesWithoutOwnerId() throws Exception {
		JSONObject activityGroupJSONObject =
			FaroInfoTestUtil.buildActivityGroupJSONObject(
				_dataSourceJSONObject.getString("id"),
				DateUtil.addDays(DateUtil.newDateString(), 0),
				_individualJSONObject);

		activityGroupJSONObject.remove("ownerId");

		_addPageVisitActivities(
			faroInfoElasticsearchInvoker.add(
				"activity-groups", activityGroupJSONObject),
			_pageAssetsJSONArray.getJSONObject(0), "100",
			RandomTestUtil.randomUUID());

		Assert.assertEquals(
			0, _computeInteractionScore(DateUtil.newDayDateString()), _DELTA);
		Assert.assertEquals(
			0, _computeLoyaltyScore(DateUtil.newDayDateString()), _DELTA);
		Assert.assertEquals(
			0, _computeVarietyScore(DateUtil.newDayDateString()), _DELTA);
	}

	@Test
	public void testFilterDuplicatePageViewActivities() throws Exception {
		JSONObject activityGroupJSONObject = _addActivityGroup(0);
		JSONObject assetJSONObject = _pageAssetsJSONArray.getJSONObject(0);
		String pageViewActivityId = RandomTestUtil.randomUUID();

		_addPageVisitActivities(
			activityGroupJSONObject, assetJSONObject, "100",
			pageViewActivityId);

		String dayDateString = DateUtil.newDayDateString();

		Map<String, Double> interactionScores =
			_individualEngagementScoresNanite.computeInteractionScores(
				dayDateString);

		_addPageVisitActivities(
			activityGroupJSONObject, assetJSONObject, "100",
			pageViewActivityId);

		Assert.assertEquals(
			"Only one set of identical page view activities should be used " +
				"for interaction score computation",
			interactionScores,
			_individualEngagementScoresNanite.computeInteractionScores(
				dayDateString));
	}

	@Test
	public void testFilterMaxActivities() throws Exception {
		JSONObject activityGroupJSONObject = _addActivityGroup(0);
		JSONObject assetJSONObject = _pageAssetsJSONArray.getJSONObject(0);
		String pageViewActivityId = RandomTestUtil.randomUUID();

		_addPageVisitActivities(
			activityGroupJSONObject, assetJSONObject, "100",
			pageViewActivityId);

		Map<String, Double> interactionScores =
			_individualEngagementScoresNanite.computeInteractionScores(
				DateUtil.newDayDateString());

		for (String depth : new String[] {"0", "25", "50", "75"}) {
			_faroInfoActivityDog.addActivity(
				FaroInfoTestUtil.buildActivityJSONObject(
					activityGroupJSONObject, assetJSONObject,
					"pageDepthReached", new String[] {"depth", depth},
					pageViewActivityId));
		}

		Assert.assertEquals(
			"Only the greatest depth value of identical page view activities " +
				"should be used for interaction score computation",
			interactionScores,
			_individualEngagementScoresNanite.computeInteractionScores(
				DateUtil.newDayDateString()));

		for (String viewDuration : new String[] {"0", "10000", "20000"}) {
			_faroInfoActivityDog.addActivity(
				FaroInfoTestUtil.buildActivityJSONObject(
					activityGroupJSONObject, assetJSONObject, "pageUnloaded",
					new String[] {"viewDuration", viewDuration},
					pageViewActivityId));
		}

		Assert.assertEquals(
			"Only the greatest page view duration of identical page view " +
				"activities should be used for interaction score computation",
			interactionScores,
			_individualEngagementScoresNanite.computeInteractionScores(
				DateUtil.newDayDateString()));
	}

	@Test
	public void testInteractionScoreIncreasesWithIncreasingFormSubmissions()
		throws Exception {

		String dayDateString = DateUtil.newDayDateString();

		double previousInteractionScore = 0;

		for (int i = 1; i <= 10; i++) {
			_addFormSubmittedActivities(_addActivityGroup(1));

			double interactionScore = _computeInteractionScore(dayDateString);

			StringBuilder sb = new StringBuilder();

			sb.append("Interaction score with ");
			sb.append(i - 1);
			sb.append(" form submissions was ");
			sb.append(previousInteractionScore);
			sb.append(", which is greater than or equal to the interaction ");
			sb.append("score with ");
			sb.append(i);
			sb.append(" form submissions, ");
			sb.append(interactionScore);

			Assert.assertTrue(
				sb.toString(), interactionScore > previousInteractionScore);

			previousInteractionScore = interactionScore;
		}
	}

	@Test
	public void testInteractionScoreIncreasesWithIncreasingPageVisits()
		throws Exception {

		String dayDateString = DateUtil.newDayDateString();

		double previousInteractionScore = _computeInteractionScore(
			dayDateString);

		for (int i = 1; i <= 10; i++) {
			_addPageVisitActivities(_addActivityGroup(1));

			double interactionScore = _computeInteractionScore(dayDateString);

			StringBuilder sb = new StringBuilder();

			sb.append("Interaction score with ");
			sb.append(i - 1);
			sb.append(" page views was ");
			sb.append(previousInteractionScore);
			sb.append(", which is greater than or equal to the interaction ");
			sb.append("score with ");
			sb.append(i);
			sb.append(" page views, ");
			sb.append(interactionScore);

			Assert.assertTrue(
				sb.toString(), interactionScore > previousInteractionScore);

			previousInteractionScore = interactionScore;
		}
	}

	@Test
	public void testInteractionScoreIncreasesWithIncreasingScrollDepth()
		throws Exception {

		JSONObject activityGroupJSONObject = _addActivityGroup(1);

		String[] depths = {"0", "25", "50", "75", "100"};

		_addPageVisitActivities(activityGroupJSONObject, depths[0]);

		String dayDateString = DateUtil.newDayDateString();

		double previousInteractionScore = _computeInteractionScore(
			dayDateString);

		for (int i = 1; i < depths.length; i++) {
			faroInfoElasticsearchInvoker.delete(
				"activities", QueryBuilders.matchAllQuery());
			faroInfoElasticsearchInvoker.delete(
				"activity-groups", QueryBuilders.matchAllQuery());

			String depth = depths[i];

			_addPageVisitActivities(activityGroupJSONObject, depth);

			double interactionScore = _computeInteractionScore(dayDateString);

			StringBuilder sb = new StringBuilder();

			sb.append("Interaction score of a single page view at ");
			sb.append(depths[i - 1]);
			sb.append("% page depth was ");
			sb.append(previousInteractionScore);
			sb.append(", which is greater than or equal to the interaction ");
			sb.append("score of a single page view at ");
			sb.append(depth);
			sb.append("% page depth, ");
			sb.append(interactionScore);

			Assert.assertTrue(
				sb.toString(), interactionScore > previousInteractionScore);

			previousInteractionScore = interactionScore;
		}
	}

	@Test
	public void testInteractionScoreIs0When0ActivityDaysInPast30Days()
		throws Exception {

		_addFormSubmittedActivities(_addActivityGroups(30, 31, 32));
		_addPageVisitActivities(_addActivityGroups(30, 31, 32));

		Assert.assertEquals(
			Collections.emptyMap(),
			_individualEngagementScoresNanite.computeInteractionScores(
				DateUtil.newDayDateString()));
	}

	@Test
	public void testInteractionScoreIs0WhenNoActivitiesExist()
		throws Exception {

		Assert.assertEquals(
			Collections.emptyMap(),
			_individualEngagementScoresNanite.computeInteractionScores(
				DateUtil.newDayDateString()));
	}

	@Test
	public void testInteractionScoreIsGreaterWithMoreRecentFormSubmission()
		throws Exception {

		String dayDateString = DateUtil.newDayDateString();

		double previousInteractionScore = 0;

		for (int i = 29; i >= 0; i--) {
			faroInfoElasticsearchInvoker.delete(
				"activities", QueryBuilders.matchAllQuery());
			faroInfoElasticsearchInvoker.delete(
				"activity-groups", QueryBuilders.matchAllQuery());

			_addFormSubmittedActivities(_addActivityGroup(i));

			double interactionScore = _computeInteractionScore(dayDateString);

			StringBuilder sb = new StringBuilder();

			sb.append("Interaction score with one form submission activity ");
			sb.append(i + 1);
			sb.append(" days ago was ");
			sb.append(previousInteractionScore);
			sb.append(", which is greater than or equal to the interaction ");
			sb.append("score with one form submission activity ");
			sb.append(i);
			sb.append(" days ago, ");
			sb.append(interactionScore);

			Assert.assertTrue(
				sb.toString(), interactionScore > previousInteractionScore);

			previousInteractionScore = interactionScore;
		}
	}

	@Test
	public void testInteractionScoreIsGreaterWithMoreRecentPageVisits()
		throws Exception {

		String dayDateString = DateUtil.newDayDateString();

		double previousInteractionScore = 0;

		for (int i = 29; i >= 0; i--) {
			faroInfoElasticsearchInvoker.delete(
				"activities", QueryBuilders.matchAllQuery());
			faroInfoElasticsearchInvoker.delete(
				"activity-groups", QueryBuilders.matchAllQuery());

			_addPageVisitActivities(_addActivityGroup(i));

			double interactionScore = _computeInteractionScore(dayDateString);

			StringBuilder sb = new StringBuilder();

			sb.append("Interaction score with one page view activity ");
			sb.append(i + 1);
			sb.append(" days ago was ");
			sb.append(previousInteractionScore);
			sb.append(", which is greater than or equal to the interaction ");
			sb.append("score with one page view activity ");
			sb.append(i);
			sb.append(" days ago, ");
			sb.append(interactionScore);

			Assert.assertTrue(
				sb.toString(), interactionScore > previousInteractionScore);

			previousInteractionScore = interactionScore;
		}
	}

	@Test
	public void testLoyaltyScoreForOneActivityDayIncreasesWithRecency()
		throws Exception {

		String dayDateString = DateUtil.newDayDateString();

		double previousLoyaltyScore = 0;

		for (int i = 29; i >= 0; i--) {
			faroInfoElasticsearchInvoker.delete(
				"activities", QueryBuilders.matchAllQuery());
			faroInfoElasticsearchInvoker.delete(
				"activity-groups", QueryBuilders.matchAllQuery());

			_addPageVisitActivities(_addActivityGroup(i));

			double loyaltyScore = _computeLoyaltyScore(dayDateString);

			StringBuilder sb = new StringBuilder();

			sb.append("Loyalty score with one activity day ");
			sb.append(i - 1);
			sb.append(" days ago was ");
			sb.append(previousLoyaltyScore);
			sb.append(", which is greater than or equal to the loyalty score ");
			sb.append("with one activity day ");
			sb.append(i);
			sb.append(" days ago, ");
			sb.append(loyaltyScore);

			Assert.assertTrue(
				sb.toString(), loyaltyScore > previousLoyaltyScore);

			previousLoyaltyScore = loyaltyScore;
		}
	}

	@Test
	public void testLoyaltyScoreIs0When0ActivityDaysInPast30Days1()
		throws Exception {

		Assert.assertEquals(
			Collections.emptyMap(),
			_individualEngagementScoresNanite.computeLoyaltyScores(
				DateUtil.newDayDateString()));
	}

	@Test
	public void testLoyaltyScoreIs0When0ActivityDaysInPast30Days2()
		throws Exception {

		_addPageVisitActivities(_addActivityGroups(30, 31, 32));

		Assert.assertEquals(
			Collections.emptyMap(),
			_individualEngagementScoresNanite.computeLoyaltyScores(
				DateUtil.newDayDateString()));
	}

	@Test
	public void testLoyaltyScoreIs1When10ActivityDaysInPast30Days1()
		throws Exception {

		_addPageVisitActivities(
			_addActivityGroups(
				0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17,
				18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29));

		Assert.assertEquals(
			1, _computeLoyaltyScore(DateUtil.newDayDateString()), _DELTA);
	}

	@Test
	public void testLoyaltyScoreIs1When10ActivityDaysInPast30Days2()
		throws Exception {

		_addPageVisitActivities(
			_addActivityGroups(
				0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17,
				18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32));

		Assert.assertEquals(
			1, _computeLoyaltyScore(DateUtil.newDayDateString()), _DELTA);
	}

	@Test
	public void testLoyaltyScoreIsStrictlyIncreasingWithDecreasingSlope()
		throws Exception {

		String dayDateString = DateUtil.newDayDateString();

		double previousLoyaltyScore = _computeLoyaltyScore(dayDateString);

		double previousDelta = Double.MAX_VALUE;

		for (int i = 0; i < 30; i++) {
			_addPageVisitActivities(_addActivityGroups(i));

			double loyaltyScore = _computeLoyaltyScore(dayDateString);

			StringBuilder sb = new StringBuilder();

			sb.append("Loyalty score with the most recent ");
			sb.append(i);
			sb.append(" days having activities was ");
			sb.append(previousLoyaltyScore);
			sb.append(", which is greater than or equal to the loyalty score ");
			sb.append("with the previous ");
			sb.append(i + 1);
			sb.append(" days having activities, ");
			sb.append(loyaltyScore);

			Assert.assertTrue(
				sb.toString(), loyaltyScore > previousLoyaltyScore);

			double delta = loyaltyScore - previousLoyaltyScore;

			if (i > 0) {
				sb = new StringBuilder();

				sb.append("The slope of loyalty score should be strictly ");
				sb.append("decreasing as an activity is added to the next ");
				sb.append("most recent day, but was found to be ");
				sb.append("nondecreasing: the difference between loyalty ");
				sb.append("score with the most recent ");
				sb.append(i - 1);
				sb.append(" days having activities and the most recent ");
				sb.append(i);
				sb.append(" days having activities was ");
				sb.append(previousDelta);
				sb.append(", but the difference between loyalty score with ");
				sb.append("the most recent ");
				sb.append(i);
				sb.append(" days having activities and the most recent ");
				sb.append(i + 1);
				sb.append(" days having activities was ");
				sb.append(delta);

				Assert.assertTrue(sb.toString(), delta < previousDelta);
			}

			previousDelta = delta;
			previousLoyaltyScore = loyaltyScore;
		}
	}

	@Test
	public void testLoyaltyScoreUnaffectedByMultipleActivitiesOn1Day()
		throws Exception {

		String dayString = DateUtil.newDayDateString();

		JSONObject activityGroupJSONObject = _addActivityGroup(
			RandomUtils.nextInt(0, 30));

		_addPageVisitActivities(activityGroupJSONObject);

		Map<String, Double> loyaltyScores =
			_individualEngagementScoresNanite.computeLoyaltyScores(dayString);

		for (int i = 0; i < 10; i++) {
			_addPageVisitActivities(activityGroupJSONObject);

			Assert.assertEquals(
				loyaltyScores,
				_individualEngagementScoresNanite.computeLoyaltyScores(
					dayString));
		}
	}

	@Test
	public void testVarietyScoreForOneDistinctPageVisitIncreasesWithRecency()
		throws Exception {

		String dayString = DateUtil.newDayDateString();

		JSONObject pageAssetJSONObject = _pageAssetsJSONArray.getJSONObject(
			RandomUtils.nextInt(0, _pageAssetsJSONArray.length()));

		double previousVarietyScore = 0;

		for (int i = 29; i >= 0; i--) {
			_addPageVisitActivities(_addActivityGroup(i), pageAssetJSONObject);

			Map<String, Double> varietyScores =
				_individualEngagementScoresNanite.computeVarietyScores(
					dayString);

			double varietyScore = varietyScores.get(
				_individualJSONObject.getString("id"));

			StringBuilder sb = new StringBuilder();

			sb.append("Variety score with one distinct page visit ");
			sb.append(i - 1);
			sb.append(" days ago was ");
			sb.append(previousVarietyScore);
			sb.append(", which is greater than or equal to the variety score ");
			sb.append("with one distinct page visit ");
			sb.append(i);
			sb.append(" days ago, ");
			sb.append(varietyScore);

			Assert.assertTrue(
				sb.toString(), varietyScore > previousVarietyScore);

			previousVarietyScore = varietyScore;
		}
	}

	@Test
	public void testVarietyScoreIs0When0DistinctPagesInPast30Days1()
		throws Exception {

		Assert.assertEquals(
			Collections.emptyMap(),
			_individualEngagementScoresNanite.computeVarietyScores(
				DateUtil.newDayDateString()));
	}

	@Test
	public void testVarietyScoreIs0When0DistinctPagesInPast30Days2()
		throws Exception {

		_addPageVisitActivities(_addActivityGroups(30, 31, 32));

		Assert.assertEquals(
			Collections.emptyMap(),
			_individualEngagementScoresNanite.computeVarietyScores(
				DateUtil.newDayDateString()));
	}

	@Test
	public void testVarietyScoreIsAlwaysLessThan1() throws Exception {
		JSONObject activityGroupJSONObject = _addActivityGroup(0);

		for (int i = 0; i < 20; i++) {
			_addPageVisitActivities(
				activityGroupJSONObject, _pageAssetsJSONArray.getJSONObject(i));
		}

		for (int i = 0; i < 100; i++) {
			JSONObject assetJSONObject = faroInfoElasticsearchInvoker.add(
				"assets",
				FaroInfoTestUtil.buildPageAssetJSONObject(
					_dataSourceJSONObject.getString("id")));

			_addPageVisitActivities(activityGroupJSONObject, assetJSONObject);
		}

		double varietyScore = _computeVarietyScore(DateUtil.newDayDateString());

		StringBuilder sb = new StringBuilder();

		sb.append("Variety score should asymptote at 1 as the number of ");
		sb.append("distinct page visits approaches infinity, but a variety ");
		sb.append("score of ");
		sb.append(varietyScore);
		sb.append(" was computed for 110 distinct page visits");

		Assert.assertTrue(sb.toString(), varietyScore < 1);
	}

	@Test
	public void testVarietyScoreIsHalfWhen9DistinctPageVisitsAveraging15DaysAgo1()
		throws Exception {

		JSONObject activityGroupJSONObject = _addActivityGroup(15);

		for (int i = 0; i < 9; i++) {
			_addPageVisitActivities(
				activityGroupJSONObject, _pageAssetsJSONArray.getJSONObject(i));
		}

		Assert.assertEquals(
			0.5, _computeVarietyScore(DateUtil.newDayDateString()), _DELTA);
	}

	@Test
	public void testVarietyScoreIsHalfWhen9DistinctPageVisitsAveraging15DaysAgo2()
		throws Exception {

		for (int i = 0; i < 9; i++) {
			_addPageVisitActivities(
				_addActivityGroup(19 - i),
				_pageAssetsJSONArray.getJSONObject(i));
		}

		Assert.assertEquals(
			0.5, _computeVarietyScore(DateUtil.newDayDateString()), _DELTA);
	}

	@Test
	public void testVarietyScoreIsHalfWhen9DistinctPageVisitsAveraging15DaysAgo3()
		throws Exception {

		JSONObject activityGroup1JSONObject = _addActivityGroup(15);
		JSONObject activityGroup2JSONObject = _addActivityGroup(
			RandomUtils.nextInt(30, 40));

		for (int i = 0; i < 9; i++) {
			_addPageVisitActivities(
				activityGroup1JSONObject,
				_pageAssetsJSONArray.getJSONObject(i));
		}

		for (int i = 9; i < 20; i++) {
			_addPageVisitActivities(
				activityGroup2JSONObject,
				_pageAssetsJSONArray.getJSONObject(i));
		}

		Assert.assertEquals(
			0.5, _computeVarietyScore(DateUtil.newDayDateString()), _DELTA);
	}

	@Test
	public void testVarietyScoreIsStrictlyIncreasingWithDecreasingSlope()
		throws Exception {

		String dayDateString = DateUtil.newDayDateString();

		double previousVarietyScore = _computeVarietyScore(dayDateString);

		double previousDelta = Double.MAX_VALUE;

		JSONObject activityGroupJSONObject = _addActivityGroup(
			RandomUtils.nextInt(0, 30));

		for (int i = 0; i < _pageAssetsJSONArray.length(); i++) {
			_addPageVisitActivities(
				activityGroupJSONObject, _pageAssetsJSONArray.getJSONObject(i));

			double varietyScore = _computeVarietyScore(dayDateString);

			StringBuilder sb = new StringBuilder();

			sb.append("Variety score with ");
			sb.append(i);
			sb.append(" distinct page visits on the same day was ");
			sb.append(previousVarietyScore);
			sb.append(", which is greater than or equal to the variety score ");
			sb.append("with ");
			sb.append(i + 1);
			sb.append(" distinct page visits on the same day, ");
			sb.append(varietyScore);

			Assert.assertTrue(
				sb.toString(), varietyScore > previousVarietyScore);

			double delta = varietyScore - previousVarietyScore;

			if (i > 0) {
				sb = new StringBuilder();

				sb.append("The slope of variety score should be strictly ");
				sb.append("decreasing as distinct page visits on the same ");
				sb.append("day increases, but was found to be nondecreasing: ");
				sb.append("the difference between variety score with ");
				sb.append(i - 1);
				sb.append(" and ");
				sb.append(i);
				sb.append(" distinct page visits on the same day was ");
				sb.append(previousDelta);
				sb.append(", but the difference between variety score with ");
				sb.append(i);
				sb.append(" and ");
				sb.append(i + 1);
				sb.append(" distinct page visits on the same day was ");
				sb.append(delta);

				Assert.assertTrue(sb.toString(), delta < previousDelta);
			}

			previousDelta = delta;
			previousVarietyScore = varietyScore;
		}
	}

	@Test
	public void testVarietyScoreUnaffectedByMultipleVisitsToTheSamePageOnTheSameOrEarlierDays()
		throws Exception {

		String dayString = DateUtil.newDayDateString();

		JSONObject activityGroupJSONObject = _addActivityGroup(0);

		JSONObject pageAssetJSONObject = _pageAssetsJSONArray.getJSONObject(
			RandomUtils.nextInt(0, _pageAssetsJSONArray.length()));

		_addPageVisitActivities(activityGroupJSONObject, pageAssetJSONObject);

		double varietyScore = _computeVarietyScore(dayString);

		for (int j = 0; j < RandomUtils.nextInt(3, 10); j++) {
			_addPageVisitActivities(
				activityGroupJSONObject, pageAssetJSONObject);
		}

		for (int i = 1; i < 30; i++) {
			activityGroupJSONObject = _addActivityGroup(i);

			for (int j = 0; j < RandomUtils.nextInt(3, 10); j++) {
				_addPageVisitActivities(
					activityGroupJSONObject, pageAssetJSONObject);
			}
		}

		Assert.assertEquals(
			varietyScore, _computeVarietyScore(dayString), _DELTA);
	}

	@TestConfiguration
	public static class IndividualEngagementScoresNaniteTestConfiguration {

		@Bean
		@Primary
		public DXPExtractorConfigurationDog dxpExtractorConfigurationDog() {
			return Mockito.mock(DXPExtractorConfigurationDog.class);
		}

	}

	private JSONObject _addActivityGroup(int days) throws Exception {
		return faroInfoElasticsearchInvoker.add(
			"activity-groups",
			FaroInfoTestUtil.buildActivityGroupJSONObject(
				_dataSourceJSONObject.getString("id"),
				DateUtil.addDays(DateUtil.newDateString(), -days),
				_individualJSONObject));
	}

	private JSONArray _addActivityGroups(int... daysArray) throws Exception {
		JSONArray activityGroupsJSONArray = new JSONArray();

		for (int days : daysArray) {
			activityGroupsJSONArray.put(_addActivityGroup(days));
		}

		return activityGroupsJSONArray;
	}

	private void _addFormSubmittedActivities(JSONArray activityGroupsJSONArray)
		throws Exception {

		for (int i = 0; i < activityGroupsJSONArray.length(); i++) {
			_addFormSubmittedActivities(
				activityGroupsJSONArray.getJSONObject(i));
		}
	}

	private void _addFormSubmittedActivities(JSONObject activityGroupJSONObject)
		throws Exception {

		_addFormSubmittedActivities(
			activityGroupJSONObject,
			_formAssetsJSONArray.getJSONObject(
				RandomUtils.nextInt(0, _formAssetsJSONArray.length())));
	}

	private void _addFormSubmittedActivities(
			JSONObject activityGroupJSONObject, JSONObject assetJSONObject)
		throws Exception {

		_faroInfoActivityDog.addActivity(
			FaroInfoTestUtil.buildActivityJSONObject(
				activityGroupJSONObject, assetJSONObject, "formSubmitted",
				new String[0]));
	}

	private void _addPageVisitActivities(JSONArray activityGroupsJSONArray)
		throws Exception {

		_addPageVisitActivities(activityGroupsJSONArray, "100");
	}

	private void _addPageVisitActivities(
			JSONArray activityGroupsJSONArray, String depth)
		throws Exception {

		for (int i = 0; i < activityGroupsJSONArray.length(); i++) {
			_addPageVisitActivities(
				activityGroupsJSONArray.getJSONObject(i), depth);
		}
	}

	private void _addPageVisitActivities(JSONObject activityGroupJSONObject)
		throws Exception {

		_addPageVisitActivities(
			activityGroupJSONObject,
			_pageAssetsJSONArray.getJSONObject(
				RandomUtils.nextInt(0, _pageAssetsJSONArray.length())));
	}

	private void _addPageVisitActivities(
			JSONObject activityGroupJSONObject, JSONObject assetJSONObject)
		throws Exception {

		_addPageVisitActivities(
			activityGroupJSONObject, assetJSONObject, "100",
			RandomTestUtil.randomUUID());
	}

	private void _addPageVisitActivities(
			JSONObject activityGroupJSONObject, JSONObject assetJSONObject,
			String depth, String pageViewActivityId)
		throws Exception {

		_faroInfoActivityDog.addActivity(
			FaroInfoTestUtil.buildActivityJSONObject(
				activityGroupJSONObject, assetJSONObject, "pageDepthReached",
				new String[] {"depth", depth}, pageViewActivityId));
		_faroInfoActivityDog.addActivity(
			FaroInfoTestUtil.buildActivityJSONObject(
				activityGroupJSONObject, assetJSONObject, "pageUnloaded",
				new String[] {"viewDuration", "30000"}, pageViewActivityId));
		_faroInfoActivityDog.addActivity(
			FaroInfoTestUtil.buildActivityJSONObject(
				activityGroupJSONObject, assetJSONObject, "pageViewed",
				new String[] {"pageLoadTime", "1000"}, pageViewActivityId));
	}

	private void _addPageVisitActivities(
			JSONObject activityGroupJSONObject, String depth)
		throws Exception {

		_addPageVisitActivities(
			activityGroupJSONObject,
			_pageAssetsJSONArray.getJSONObject(
				RandomUtils.nextInt(0, _pageAssetsJSONArray.length())),
			depth, RandomTestUtil.randomUUID());
	}

	private double _computeInteractionScore(String dayDateString)
		throws Exception {

		Map<String, Double> interactionScores =
			_individualEngagementScoresNanite.computeInteractionScores(
				dayDateString);

		return interactionScores.getOrDefault(
			_individualJSONObject.getString("id"), 0D);
	}

	private double _computeLoyaltyScore(String dayDateString) throws Exception {
		Map<String, Double> loyaltyScores =
			_individualEngagementScoresNanite.computeLoyaltyScores(
				dayDateString);

		return loyaltyScores.getOrDefault(
			_individualJSONObject.getString("id"), 0D);
	}

	private double _computeVarietyScore(String dayDateString) throws Exception {
		Map<String, Double> varietyScores =
			_individualEngagementScoresNanite.computeVarietyScores(
				dayDateString);

		return varietyScores.getOrDefault(
			_individualJSONObject.getString("id"), 0D);
	}

	private static final double _DELTA = 0.00001;

	@MockBean
	private ChannelHttp _channelHttp;

	private JSONObject _dataSourceJSONObject;

	@Autowired
	private FaroInfoActivityDog _faroInfoActivityDog;

	@Autowired
	private FaroInfoDataSourceDog _faroInfoDataSourceDog;

	@Autowired
	private FaroInfoIndividualDog _faroInfoIndividualDog;

	private JSONArray _formAssetsJSONArray;

	@Autowired
	private IndividualEngagementScoresNanite _individualEngagementScoresNanite;

	private JSONObject _individualJSONObject;
	private JSONArray _pageAssetsJSONArray;

}