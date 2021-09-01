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

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.batch.curator.bot.nanite.IndividualInterestScoresNanite;
import com.liferay.osb.asah.batch.curator.bot.nanite.NaniteTestConfiguration;
import com.liferay.osb.asah.batch.curator.bot.nanite.UpdateDynamicMembershipsNanite;
import com.liferay.osb.asah.batch.curator.spring.OSBAsahBatchCuratorSpringBootApplication;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.ActivityGroupDog;
import com.liferay.osb.asah.common.dog.AsahMarkerDog;
import com.liferay.osb.asah.common.dog.IndividualDog;
import com.liferay.osb.asah.common.dog.MembershipDog;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.entity.ActivityGroup;
import com.liferay.osb.asah.common.entity.AsahMarker;
import com.liferay.osb.asah.common.entity.Asset;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.entity.Individual;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.repository.AssetRepository;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.common.repository.FieldRepository;
import com.liferay.osb.asah.common.repository.SegmentRepository;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
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

/**
 * @author Edward Kwok-Yu Wong
 * @author Michael Bowerman
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(
	classes = {
		OSBAsahBatchCuratorSpringBootApplication.class,
		NaniteTestConfiguration.class
	}
)
public class IndividualInterestScoresNaniteTest extends BaseNaniteTestCase {

	@Before
	public void setUp() throws Exception {
		DataSource dataSource = _dataSourceRepository.save(
			FaroInfoTestUtil.buildLiferayDataSource());

		Individual individual = FaroInfoTestUtil.buildIndividual(
			1L, dataSource);

		_fieldRepository.saveAll(individual.getFields());

		_individual = _individualDog.addIndividual(individual, false);

		_dataSourceId = dataSource.getId();

		_assetJSONObject1 = _objectMapper.convertValue(
			_assetRepository.save(
				_objectMapper.convertValue(
					FaroInfoTestUtil.buildPageAssetJSONObject(_dataSourceId),
					Asset.class)),
			JSONObject.class);

		_assetJSONObject2 = _objectMapper.convertValue(
			_assetRepository.save(
				_objectMapper.convertValue(
					FaroInfoTestUtil.buildPageAssetJSONObject(_dataSourceId),
					Asset.class)),
			JSONObject.class);

		_addActivities(DateUtil.addDays(DateUtil.newDayDateString(), -1));
	}

	@Test
	public void testDeleteInterestScores() throws Exception {
		String dayDateString = DateUtil.newDayDateString();

		faroInfoElasticsearchInvoker.add(
			"interests",
			FaroInfoTestUtil.buildIndividualInterestsJSONArray(
				_assetJSONObject1, DateUtil.addDays(dayDateString, -2),
				_individual.getId(), 1, 10));
		faroInfoElasticsearchInvoker.add(
			"interests",
			FaroInfoTestUtil.buildIndividualInterestsJSONArray(
				_assetJSONObject1, DateUtil.addDays(dayDateString, -3),
				_individual.getId(), 1, 10));
		faroInfoElasticsearchInvoker.add(
			"visited-pages",
			FaroInfoTestUtil.buildIndividualVisitedPagesJSONArray(
				_assetJSONObject1, DateUtil.addDays(dayDateString, -30),
				_individual.getId(), 1));
		faroInfoElasticsearchInvoker.add(
			"visited-pages",
			FaroInfoTestUtil.buildIndividualVisitedPagesJSONArray(
				_assetJSONObject1, DateUtil.addDays(dayDateString, -31),
				_individual.getId(), 1));

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

		Segment segment1 = _segmentRepository.save(
			FaroInfoTestUtil.buildDynamicSegment(
				1L,
				"interests.filter(filter='(name eq ''" + keyword + "'') and " +
					"(score eq ''false'')')"));
		Segment segment2 = _segmentRepository.save(
			FaroInfoTestUtil.buildDynamicSegment(
				1L,
				"interests.filter(filter='(name eq ''" + keyword + "'') and " +
					"(score eq ''true'')')"));

		_asahMarkerDog.addAsahMarker(
			new AsahMarker(
				"InterestThresholdScoreNanite", JSONUtil.put("score", 0.3)),
			WeDeployDataService.OSB_ASAH_FARO_INFO);

		_updateDynamicMembershipsNanite.run(
			_getContextJSONObject(
				_objectMapper.convertValue(segment1, JSONObject.class)));
		_updateDynamicMembershipsNanite.run(
			_getContextJSONObject(
				_objectMapper.convertValue(segment2, JSONObject.class)));

		Long segment1Id = segment1.getId();
		Long segment2Id = segment2.getId();

		Long individualId = _individual.getId();

		Assert.assertTrue(_membershipDog.isMember(individualId, segment1Id));
		Assert.assertFalse(_membershipDog.isMember(individualId, segment2Id));

		_individualInterestScoresNanite.run((JSONObject)null);

		Assert.assertFalse(_membershipDog.isMember(individualId, segment1Id));
		Assert.assertTrue(_membershipDog.isMember(individualId, segment2Id));
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

	private void _addActivities(String dateString) {
		ActivityGroup activityGroup = _activityGroupDog.addActivityGroup(
			FaroInfoTestUtil.buildActivityGroup(
				_dataSourceId, DateUtil.toUTCDate(dateString), _individual));

		faroInfoElasticsearchInvoker.add(
			"activities",
			FaroInfoTestUtil.buildActivityJSONObject(
				_objectMapper.convertValue(activityGroup, JSONObject.class),
				_assetJSONObject1, "pageViewed", new String[0]));

		faroInfoElasticsearchInvoker.add(
			"activities",
			FaroInfoTestUtil.buildActivityJSONObject(
				_objectMapper.convertValue(activityGroup, JSONObject.class),
				_assetJSONObject2, "pageViewed", new String[0]));
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

	@Autowired
	private ActivityGroupDog _activityGroupDog;

	@Autowired
	private AsahMarkerDog _asahMarkerDog;

	private JSONObject _assetJSONObject1;
	private JSONObject _assetJSONObject2;

	@Autowired
	private AssetRepository _assetRepository;

	private Long _dataSourceId;

	@Autowired
	private DataSourceRepository _dataSourceRepository;

	@Autowired
	private FieldRepository _fieldRepository;

	private Individual _individual;

	@Autowired
	private IndividualDog _individualDog;

	@Autowired
	private IndividualInterestScoresNanite _individualInterestScoresNanite;

	@Autowired
	private MembershipDog _membershipDog;

	@Autowired
	private ObjectMapper _objectMapper;

	@Autowired
	private SegmentRepository _segmentRepository;

	@Autowired
	private UpdateDynamicMembershipsNanite _updateDynamicMembershipsNanite;

}