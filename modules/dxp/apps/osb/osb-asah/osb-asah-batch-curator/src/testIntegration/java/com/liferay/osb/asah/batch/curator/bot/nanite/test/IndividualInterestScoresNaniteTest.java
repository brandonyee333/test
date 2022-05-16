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
import com.liferay.osb.asah.common.entity.Interest;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.repository.AssetRepository;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.common.repository.FieldRepository;
import com.liferay.osb.asah.common.repository.InterestRepository;
import com.liferay.osb.asah.common.repository.SegmentRepository;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.util.ReflectionTestUtils;

/**
 * @author Edward Kwok-Yu Wong
 * @author Michael Bowerman
 */
@ContextConfiguration(
	classes = {
		OSBAsahBatchCuratorSpringBootApplication.class,
		NaniteTestConfiguration.class
	}
)
public class IndividualInterestScoresNaniteTest
	extends BaseNaniteTestCase implements OSBAsahTestExecutionListenersContext {

	@BeforeEach
	public void setUp() throws Exception {
		_dataSource = _dataSourceRepository.save(
			FaroInfoTestUtil.buildLiferayDataSource());

		_individual = _creteIndividual();

		_assetJSONObject1 = _createAssetJSONObject();
		_assetJSONObject2 = _createAssetJSONObject();

		_addActivities(DateUtil.addDays(DateUtil.newDayDateString(), -1));

		ReflectionTestUtils.setField(
			_individualInterestScoresNanite, "_staleInterestsQuerySize", 10000);
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

		_runIndividualInterestScoresNanite(dayDateString);

		JSONArray interestsJSONArray = faroInfoElasticsearchInvoker.get(
			"interests");

		for (int i = 0; i < interestsJSONArray.length(); i++) {
			JSONObject interestJSONObject = interestsJSONArray.getJSONObject(i);

			Assertions.assertEquals(
				DateUtil.addDays(dayDateString, -2),
				interestJSONObject.getString("dateRecorded))"));
		}

		JSONArray visitedPagesJSONArray = faroInfoElasticsearchInvoker.get(
			"visited-pages");

		for (int i = 0; i < visitedPagesJSONArray.length(); i++) {
			JSONObject visitedPageJSONObject =
				visitedPagesJSONArray.getJSONObject(i);

			Assertions.assertEquals(
				DateUtil.addDays(dayDateString, -30),
				visitedPageJSONObject.getString("day"));
		}
	}

	@Test
	public void testInterestAreInsertedAndRemovedOverTime() throws Exception {
		String dayDateString = DateUtil.addDays(
			DateUtil.newDayDateString(), -1);
		boolean decreasing = false;
		int interactions = 0;
		int previousInterestsSize = 0;

		do {
			_runIndividualInterestScoresNanite(dayDateString);

			List<Interest> interests =
				_interestRepository.findByFilterStringAndScoreGreaterThanEqual(
					null, null, PageRequest.of(0, 10000));

			dayDateString = DateUtil.addDays(dayDateString, 1);

			decreasing = previousInterestsSize >= interests.size();

			if (!decreasing) {
				_addActivities(
					dayDateString, _creteIndividual(),
					_createAssetJSONObject());
			}

			previousInterestsSize = interests.size();

			Assertions.assertFalse(
				++interactions > 150,
				String.format(
					"Too many interactions (%d). Were interests being " +
						"deleted? %b",
					interactions, decreasing));
		}
		while (previousInterestsSize > 0);

		Assertions.assertTrue(
			interactions > 1,
			"Too few interactions means no interest was created");
	}

	@Test
	public void testInterestScoresDecayMultipleLoops() throws Exception {
		String dayDateString = DateUtil.newDayDateString();

		String previousDayDateString = DateUtil.addDays(dayDateString, -1);

		_runIndividualInterestScoresNanite(previousDayDateString);

		JSONArray interestsJSONArray = faroInfoElasticsearchInvoker.get(
			"interests",
			QueryBuilders.termQuery("dateRecorded", previousDayDateString));

		Map<Pair<String, String>, Double> interestsMap = new HashMap<>();

		interestsJSONArray.forEach(
			object -> {
				JSONObject interestJSONObject = (JSONObject)object;

				interestsMap.put(
					Pair.of(
						interestJSONObject.getString("name"),
						interestJSONObject.getString("ownerId")),
					interestJSONObject.getDouble("score"));
			});

		ReflectionTestUtils.setField(
			_individualInterestScoresNanite, "_staleInterestsQuerySize",
			interestsJSONArray.length() / 3);

		_runIndividualInterestScoresNanite(dayDateString);

		for (Map.Entry<Pair<String, String>, Double> entry :
				interestsMap.entrySet()) {

			Pair<String, String> nameOwnerIdPair = entry.getKey();

			interestsJSONArray = faroInfoElasticsearchInvoker.get(
				"interests",
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery("dateRecorded", dayDateString)
				).filter(
					QueryBuilders.termQuery("name", nameOwnerIdPair.getKey())
				).filter(
					QueryBuilders.termQuery(
						"ownerId", nameOwnerIdPair.getValue())
				));

			Assertions.assertEquals(1, interestsJSONArray.length());

			JSONObject interestJSONObject = interestsJSONArray.getJSONObject(0);

			Assertions.assertTrue(
				entry.getValue() > interestJSONObject.getDouble("score"));

			faroInfoElasticsearchInvoker.delete(
				"interests", interestJSONObject.getString("id"));
		}

		Assertions.assertEquals(
			0,
			faroInfoElasticsearchInvoker.count(
				"interests",
				QueryBuilders.termQuery("dateRecorded", dayDateString)));
	}

	@Test
	public void testInterestScoresDecayOverTime() throws Exception {
		String dayDateString = DateUtil.newDayDateString();

		String previousDayDateString = DateUtil.addDays(dayDateString, -1);

		_runIndividualInterestScoresNanite(previousDayDateString);

		for (int elapsedDays = 0;
			 elapsedDays < _MAX_DAYS_BEFORE_INTEREST_SCORE_BELOW_THRESHOLD;
			 elapsedDays++) {

			_runIndividualInterestScoresNanite(dayDateString);

			JSONArray interestsJSONArray = faroInfoElasticsearchInvoker.get(
				"interests",
				QueryBuilders.termQuery("dateRecorded", dayDateString));

			if (interestsJSONArray.length() == 0) {
				Assertions.assertTrue(
					elapsedDays > 0,
					"No positive interest scores were calculated for " +
						dayDateString);

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
				"InterestThresholdScoreNanite", JSONUtil.put("score", 0.3)));

		_updateDynamicMembershipsNanite.run(
			_getContextJSONObject(
				_objectMapper.convertValue(segment1, JSONObject.class)));
		_updateDynamicMembershipsNanite.run(
			_getContextJSONObject(
				_objectMapper.convertValue(segment2, JSONObject.class)));

		Long segment1Id = segment1.getId();
		Long segment2Id = segment2.getId();

		Long individualId = _individual.getId();

		Assertions.assertTrue(
			_membershipDog.isMember(individualId, segment1Id));
		Assertions.assertFalse(
			_membershipDog.isMember(individualId, segment2Id));

		_runIndividualInterestScoresNanite((JSONObject)null);

		Assertions.assertFalse(
			_membershipDog.isMember(individualId, segment1Id));
		Assertions.assertTrue(
			_membershipDog.isMember(individualId, segment2Id));
	}

	@Test
	public void testNoInterestScoresGeneratedBeforeActivitiesOccur()
		throws Exception {

		_runIndividualInterestScoresNanite(
			DateUtil.addDays(DateUtil.newDayDateString(), -2));

		JSONArray interestsJSONArray = faroInfoElasticsearchInvoker.get(
			"interests");

		Assertions.assertEquals(
			0, interestsJSONArray.length(),
			"Expected no interests to be generated, but interests were " +
				"generated: " + interestsJSONArray);
	}

	@Test
	public void testRunTwice() {
		String dayDateString = DateUtil.newDayDateString();

		faroInfoElasticsearchInvoker.add(
			"interests",
			FaroInfoTestUtil.buildIndividualInterestsJSONArray(
				_assetJSONObject1, DateUtil.addDays(dayDateString, -1),
				_individual.getId(), 1, 10));

		IntStream.range(
			1, 3
		).forEach(
			i -> {
				try {
					_runIndividualInterestScoresNanite(
						JSONUtil.put("processDay", dayDateString));
				}
				catch (Exception exception) {
					_log.error(exception, exception);

					Assertions.fail();
				}
			}
		);

		JSONArray interestsJSONArray = faroInfoElasticsearchInvoker.get(
			"interests",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("dateRecorded", dayDateString)));

		Map<String, Boolean> map = new HashedMap<>();

		interestsJSONArray.forEach(
			object -> {
				JSONObject jsonObject = (JSONObject)object;

				String key =
					jsonObject.getString("ownerId") +
						jsonObject.getString("name");

				if (map.containsKey(key)) {
					Assertions.fail();
				}

				map.put(key, true);
			});

		AsahMarker asahMarker = _asahMarkerDog.getAsahMarker(
			"IndividualInterestScoresNanite");

		JSONObject asahMarkerContextJSONObject =
			asahMarker.getContextJSONObject();

		Assertions.assertEquals(
			dayDateString,
			asahMarkerContextJSONObject.getString("lastSuccessfulDay"));
	}

	@Test
	public void testScoresIncreaseWithActivities() throws Exception {
		String dayDateString = DateUtil.newDayDateString();

		String previousDayDateString = DateUtil.addDays(dayDateString, -1);

		_runIndividualInterestScoresNanite(previousDayDateString);

		_addActivities(dayDateString);

		_runIndividualInterestScoresNanite(dayDateString);

		_assertInterestScoreDirection(
			previousDayDateString, dayDateString, true);
	}

	@Test
	public void testVisitedPagesWithCanonicalUrl() throws Exception {
		String dayDateString = DateUtil.newDayDateString();

		_addActivities(dayDateString);

		_runIndividualInterestScoresNanite(dayDateString);

		Assertions.assertEquals(
			0,
			faroInfoElasticsearchInvoker.count(
				"visited-pages",
				BoolQueryBuilderUtil.mustNot(
					QueryBuilders.existsQuery("canonicalUrl"))));
	}

	private void _addActivities(String dateString) {
		_addActivities(
			dateString, _individual, _assetJSONObject1, _assetJSONObject2);
	}

	private void _addActivities(
		String dateString, Individual individual,
		JSONObject... assetJSONObjects) {

		ActivityGroup activityGroup = _activityGroupDog.addActivityGroup(
			FaroInfoTestUtil.buildActivityGroup(
				_dataSource.getId(), DateUtil.toUTCDate(dateString),
				individual));

		for (JSONObject jsonObject : assetJSONObjects) {
			faroInfoElasticsearchInvoker.add(
				"activities",
				FaroInfoTestUtil.buildActivityJSONObject(
					_objectMapper.convertValue(activityGroup, JSONObject.class),
					jsonObject, "pageViewed", new String[0]));
		}
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
				Assertions.assertTrue(
					currentDayScore > previousDayScore,
					"Expected current day score " + currentDayScore + " to " +
						"be greater than previous day score " +
							previousDayScore);
			}
			else {
				Assertions.assertTrue(
					currentDayScore < previousDayScore,
					"Expected current date score " + currentDayScore + " to " +
						"be less than previous date score " + previousDayScore);
			}
		}
	}

	private void _assertInterestScoresAboveThreshold(
		JSONArray interestsJSONArray) {

		for (int i = 0; i < interestsJSONArray.length(); i++) {
			JSONObject interestJSONObject = interestsJSONArray.getJSONObject(i);

			double score = interestJSONObject.getDouble("score");

			Assertions.assertTrue(
				score >=
					IndividualInterestScoresNanite.
						MINIMUM_INTEREST_SCORE_THRESHOLD,
				"Expected interest score to be greater than or equal to the " +
					"threshold but was " + score + ": " + interestJSONObject);
		}
	}

	private JSONObject _createAssetJSONObject() {
		return _objectMapper.convertValue(
			_assetRepository.save(
				_objectMapper.convertValue(
					FaroInfoTestUtil.buildPageAssetJSONObject(
						_dataSource.getId()),
					Asset.class)),
			JSONObject.class);
	}

	private Individual _creteIndividual() {
		Individual individual = FaroInfoTestUtil.buildIndividual(
			1L, _dataSource);

		_fieldRepository.saveAll(individual.getFields());

		return _individualDog.addIndividual(individual, false);
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

		Assertions.assertNotNull(
			interestJSONObject,
			"Unable to get interest score for keyword \"" + name + "\" for " +
				"date " + dayDateString);

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

	private void _runIndividualInterestScoresNanite(
			JSONObject contextJSONObject)
		throws Exception {

		_individualInterestScoresNanite.run(contextJSONObject);

		faroInfoElasticsearchInvoker.refresh();
	}

	private void _runIndividualInterestScoresNanite(String dateString)
		throws Exception {

		_individualInterestScoresNanite.run(dateString);

		faroInfoElasticsearchInvoker.refresh();
	}

	private static final int _MAX_DAYS_BEFORE_INTEREST_SCORE_BELOW_THRESHOLD =
		60;

	private static final Log _log = LogFactory.getLog(
		IndividualInterestScoresNaniteTest.class);

	@Autowired
	private ActivityGroupDog _activityGroupDog;

	@Autowired
	private AsahMarkerDog _asahMarkerDog;

	private JSONObject _assetJSONObject1;
	private JSONObject _assetJSONObject2;

	@Autowired
	private AssetRepository _assetRepository;

	private DataSource _dataSource;

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
	private InterestRepository _interestRepository;

	@Autowired
	private MembershipDog _membershipDog;

	@Autowired
	private ObjectMapper _objectMapper;

	@Autowired
	private SegmentRepository _segmentRepository;

	@Autowired
	private UpdateDynamicMembershipsNanite _updateDynamicMembershipsNanite;

}