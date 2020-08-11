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

import com.liferay.osb.asah.batch.curator.bot.nanite.IndividualActivityFieldsNanite;
import com.liferay.osb.asah.batch.curator.bot.nanite.IndividualInterestScoresNanite;
import com.liferay.osb.asah.batch.curator.bot.nanite.NaniteTestConfiguration;
import com.liferay.osb.asah.batch.curator.bot.nanite.UpdateDynamicMembershipsNanite;
import com.liferay.osb.asah.batch.curator.spring.OSBAsahBatchCuratorSpringBootApplication;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoMembershipDog;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.queue.http.CerebroQueueHttpTestConfiguration;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.util.List;

import org.apache.commons.lang3.RandomUtils;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Edward Kwok-Yu Wong
 * @author Michael Bowerman
 */
@ContextConfiguration(classes = OSBAsahBatchCuratorSpringBootApplication.class)
@Import(
	{CerebroQueueHttpTestConfiguration.class, NaniteTestConfiguration.class}
)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest
public class IndividualInterestScoresNaniteTest extends BaseNaniteTestCase {

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		JSONObject dataSourceJSONObject = faroInfoElasticsearchInvoker.add(
			"data-sources",
			FaroInfoTestUtil.buildLiferayDataSourceJSONObject());

		_individualJSONObject = faroInfoElasticsearchInvoker.add(
			"individuals",
			FaroInfoTestUtil.buildIndividualJSONObject(
				"1", dataSourceJSONObject));

		_dataSourceId = dataSourceJSONObject.getString("id");

		_assetJSONObject1 = faroInfoElasticsearchInvoker.add(
			"assets", FaroInfoTestUtil.buildPageAssetJSONObject(_dataSourceId));
		_assetJSONObject2 = faroInfoElasticsearchInvoker.add(
			"assets", FaroInfoTestUtil.buildPageAssetJSONObject(_dataSourceId));

		_addActivities(DateUtil.addDays(DateUtil.newDayDateString(), -1));

		_individualActivityFieldsNanite.run();
	}

	@Test
	public void testDeleteInterestScores() throws Exception {
		String dayDateString = DateUtil.newDayDateString();

		faroInfoElasticsearchInvoker.add(
			"interests",
			FaroInfoTestUtil.buildIndividualInterestsJSONArray(
				_assetJSONObject1, DateUtil.addDays(dayDateString, -2),
				_individualJSONObject.getString("id"), 1, 10));
		faroInfoElasticsearchInvoker.add(
			"interests",
			FaroInfoTestUtil.buildIndividualInterestsJSONArray(
				_assetJSONObject1, DateUtil.addDays(dayDateString, -3),
				_individualJSONObject.getString("id"), 1, 10));
		faroInfoElasticsearchInvoker.add(
			"visited-pages",
			FaroInfoTestUtil.buildIndividualVisitedPagesJSONArray(
				_assetJSONObject1, DateUtil.addDays(dayDateString, -30),
				_individualJSONObject.getString("id"), 1));
		faroInfoElasticsearchInvoker.add(
			"visited-pages",
			FaroInfoTestUtil.buildIndividualVisitedPagesJSONArray(
				_assetJSONObject1, DateUtil.addDays(dayDateString, -31),
				_individualJSONObject.getString("id"), 1));

		_individualInterestScoresNanite.run(dayDateString);

		JSONArray interestsJSONArray = faroInfoElasticsearchInvoker.get(
			"interests");

		for (int i = 0; i < interestsJSONArray.length(); i++) {
			JSONObject interestJSONObject = interestsJSONArray.getJSONObject(i);

			Assert.assertEquals(
				DateUtil.addDays(dayDateString, -2),
				interestJSONObject.getString("dateRecorded))"));
		}

		JSONArray visitedPagesJSONArray = faroInfoElasticsearchInvoker.get(
			"visited-pages");

		for (int i = 0; i < visitedPagesJSONArray.length(); i++) {
			JSONObject visitedPageJSONObject =
				visitedPagesJSONArray.getJSONObject(i);

			Assert.assertEquals(
				DateUtil.addDays(dayDateString, -30),
				visitedPageJSONObject.getString("day"));
		}
	}

	@Test
	public void testInterestScoresDecayOverTime() throws Exception {
		String dayDateString = DateUtil.newDayDateString();

		String previousDayDateString = DateUtil.addDays(dayDateString, -1);

		_individualInterestScoresNanite.run(previousDayDateString);

		for (int elapsedDays = 0;
			 elapsedDays < _MAX_DAYS_BEFORE_INTEREST_SCORE_BELOW_THRESHOLD;
			 elapsedDays++) {

			_individualInterestScoresNanite.run(dayDateString);

			JSONArray interestsJSONArray = faroInfoElasticsearchInvoker.get(
				"interests",
				QueryBuilders.termQuery("dateRecorded", dayDateString));

			if (interestsJSONArray.length() == 0) {
				Assert.assertTrue(
					"No positive interest scores were calculated for " +
						dayDateString,
					elapsedDays > 0);

				return;
			}

			_assertInterestScoreDirection(
				previousDayDateString, dayDateString, false);

			_assertInterestScoresAboveThreshold(interestsJSONArray);

			previousDayDateString = dayDateString;

			dayDateString = DateUtil.addDays(dayDateString, 1);
		}

		throw new Exception(
			"Exceeded maximum number of days allowed before reaching score " +
				"below threshold");
	}

	@Test
	public void testMembershipsUpdatedWhenNaniteRuns() throws Exception {
		JSONArray keywordsJSONArray = _assetJSONObject1.getJSONArray(
			"keywords");

		JSONObject keywordJSONObject = keywordsJSONArray.getJSONObject(
			RandomUtils.nextInt(0, keywordsJSONArray.length()));

		String keyword = keywordJSONObject.getString("keyword");

		JSONObject individualSegment1JSONObject =
			faroInfoElasticsearchInvoker.add(
				"individual-segments",
				FaroInfoTestUtil.buildDynamicIndividualSegmentJSONObject(
					"1",
					"interests.filter(filter='(name eq ''" + keyword + "'') " +
						"and (score eq ''false'')')"));
		JSONObject individualSegment2JSONObject =
			faroInfoElasticsearchInvoker.add(
				"individual-segments",
				FaroInfoTestUtil.buildDynamicIndividualSegmentJSONObject(
					"1",
					"interests.filter(filter='(name eq ''" + keyword + "'') " +
						"and (score eq ''true'')')"));

		faroInfoElasticsearchInvoker.add(
			"OSBAsahMarkers",
			JSONUtil.put(
				"id", "InterestThresholdScoreNanite"
			).put(
				"score", 0.3
			));

		_updateDynamicMembershipsNanite.run(
			_getContextJSONObject(individualSegment1JSONObject));
		_updateDynamicMembershipsNanite.run(
			_getContextJSONObject(individualSegment2JSONObject));

		String individualSegment1Id = individualSegment1JSONObject.getString(
			"id");
		String individualSegment2Id = individualSegment2JSONObject.getString(
			"id");

		String individualId = _individualJSONObject.getString("id");

		Assert.assertTrue(
			_faroInfoMembershipDog.isMember(
				individualId, individualSegment1Id));
		Assert.assertFalse(
			_faroInfoMembershipDog.isMember(
				individualId, individualSegment2Id));

		_individualInterestScoresNanite.run((JSONObject)null);

		Assert.assertFalse(
			_faroInfoMembershipDog.isMember(
				individualId, individualSegment1Id));
		Assert.assertTrue(
			_faroInfoMembershipDog.isMember(
				individualId, individualSegment2Id));
	}

	@Test
	public void testNoInterestScoresGeneratedBeforeActivitiesOccur()
		throws Exception {

		_individualInterestScoresNanite.run(
			DateUtil.addDays(DateUtil.newDayDateString(), -2));

		JSONArray interestsJSONArray = faroInfoElasticsearchInvoker.get(
			"interests");

		Assert.assertEquals(
			"Expected no interests to be generated, but interests were " +
				"generated: " + interestsJSONArray,
			0, interestsJSONArray.length());
	}

	@Test
	public void testScoresIncreaseWithActivities() throws Exception {
		String dayDateString = DateUtil.newDayDateString();

		String previousDayDateString = DateUtil.addDays(dayDateString, -1);

		_individualInterestScoresNanite.run(previousDayDateString);

		_addActivities(dayDateString);

		_individualInterestScoresNanite.run(dayDateString);

		_assertInterestScoreDirection(
			previousDayDateString, dayDateString, true);
	}

	@Test
	public void testVisitedPagesWithCanonicalUrl() throws Exception {
		String dayDateString = DateUtil.newDayDateString();

		_addActivities(dayDateString);

		_individualInterestScoresNanite.run(dayDateString);

		Assert.assertEquals(
			0,
			faroInfoElasticsearchInvoker.count(
				"visited-pages",
				BoolQueryBuilderUtil.mustNot(
					QueryBuilders.existsQuery("canonicalUrl"))));
	}

	private void _addActivities(String dateString) throws Exception {
		JSONObject activityGroupJSONObject = faroInfoElasticsearchInvoker.add(
			"activity-groups",
			FaroInfoTestUtil.buildActivityGroupJSONObject(
				_dataSourceId, dateString, _individualJSONObject));

		faroInfoElasticsearchInvoker.add(
			"activities",
			FaroInfoTestUtil.buildActivityJSONObject(
				activityGroupJSONObject, _assetJSONObject1, "pageViewed",
				new String[] {"pageLoadTime", "1000"}));

		faroInfoElasticsearchInvoker.add(
			"activities",
			FaroInfoTestUtil.buildActivityJSONObject(
				activityGroupJSONObject, _assetJSONObject2, "pageViewed",
				new String[] {"pageLoadTime", "1000"}));
	}

	private void _assertInterestScoreDirection(
			String previousDayDateString, String dayDateString,
			boolean increasing)
		throws Exception {

		for (String keyword : _getKeywords()) {
			JSONObject previousDayInterestJSONObject = _getInterestJSONObject(
				previousDayDateString, keyword);
			JSONObject currentDayInterestJSONObject = _getInterestJSONObject(
				dayDateString, keyword);

			double previousDayScore = previousDayInterestJSONObject.getDouble(
				"score");
			double currentDayScore = currentDayInterestJSONObject.getDouble(
				"score");

			if (increasing) {
				Assert.assertTrue(
					"Expected current day score " + currentDayScore + " to " +
						"be greater than previous day score " +
							previousDayScore,
					currentDayScore > previousDayScore);
			}
			else {
				Assert.assertTrue(
					"Expected current date score " + currentDayScore + " to " +
						"be less than previous date score " + previousDayScore,
					currentDayScore < previousDayScore);
			}
		}
	}

	private void _assertInterestScoresAboveThreshold(
		JSONArray interestsJSONArray) {

		for (int i = 0; i < interestsJSONArray.length(); i++) {
			JSONObject interestJSONObject = interestsJSONArray.getJSONObject(i);

			double score = interestJSONObject.getDouble("score");

			Assert.assertTrue(
				"Expected interest score to be greater than or equal to the " +
					"threshold but was " + score + ": " + interestJSONObject,
				score >=
					IndividualInterestScoresNanite.
						MINIMUM_INTEREST_SCORE_THRESHOLD);
		}
	}

	private JSONObject _getContextJSONObject(
		JSONObject individualSegmentJSONObject) {

		return JSONUtil.put(
			"dateModified",
			individualSegmentJSONObject.getString("dateModified")
		).put(
			"individualSegmentJSONObject", individualSegmentJSONObject
		);
	}

	private JSONObject _getInterestJSONObject(
		String dayDateString, String name) {

		JSONObject interestJSONObject = faroInfoElasticsearchInvoker.fetch(
			"interests",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("dateRecorded", dayDateString)
			).filter(
				QueryBuilders.termQuery("name", name)
			));

		Assert.assertNotNull(
			"Unable to get interest score for keyword \"" + name + "\" for " +
				"date " + dayDateString,
			interestJSONObject);

		return interestJSONObject;
	}

	private List<String> _getKeywords() throws Exception {
		List<String> keywords = JSONUtil.toList(
			_assetJSONObject1.getJSONArray("keywords"),
			jsonObject -> jsonObject.getString("keyword"));

		keywords.addAll(
			JSONUtil.toList(
				_assetJSONObject2.getJSONArray("keywords"),
				jsonObject -> jsonObject.getString("keyword")));

		return keywords;
	}

	private static final double
		_MAX_DAYS_BEFORE_INTEREST_SCORE_BELOW_THRESHOLD = 60;

	private JSONObject _assetJSONObject1;
	private JSONObject _assetJSONObject2;
	private String _dataSourceId;

	@Autowired
	private FaroInfoMembershipDog _faroInfoMembershipDog;

	@Autowired
	private IndividualActivityFieldsNanite _individualActivityFieldsNanite;

	@Autowired
	private IndividualInterestScoresNanite _individualInterestScoresNanite;

	private JSONObject _individualJSONObject;

	@Autowired
	private UpdateDynamicMembershipsNanite _updateDynamicMembershipsNanite;

}