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
import com.liferay.osb.asah.common.dog.AsahMarkerDog;
import com.liferay.osb.asah.common.dog.BQMembershipDog;
import com.liferay.osb.asah.common.dog.IndividualDog;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.entity.AsahMarker;
import com.liferay.osb.asah.common.entity.Asset;
import com.liferay.osb.asah.common.entity.Channel;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.entity.Individual;
import com.liferay.osb.asah.common.entity.Interest;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.repository.AssetRepository;
import com.liferay.osb.asah.common.repository.ChannelRepository;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.common.repository.FieldRepository;
import com.liferay.osb.asah.common.repository.InterestRepository;
import com.liferay.osb.asah.common.repository.SegmentRepository;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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
		Channel channel = new Channel();

		channel.setId(1L);
		channel.setIsNew(Boolean.TRUE);
		channel.setName("Liferay");

		_channelRepository.save(channel);

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
		Date dayDate = DateUtil.newDayDate();

		_interestRepository.saveAll(
			FaroInfoTestUtil.buildIndividualInterests(
				_assetJSONObject1, _individual.getId(),
				DateUtil.addDays(dayDate, -2), 1, 10L));

		_interestRepository.saveAll(
			FaroInfoTestUtil.buildIndividualInterests(
				_assetJSONObject1, _individual.getId(),
				DateUtil.addDays(dayDate, -3), 1, 10L));
		faroInfoElasticsearchInvoker.add(
			"visited-pages",
			FaroInfoTestUtil.buildIndividualVisitedPagesJSONArray(
				_assetJSONObject1,
				DateUtil.toString(DateUtil.addDays(dayDate, -30)),
				_individual.getId(), 1));
		faroInfoElasticsearchInvoker.add(
			"visited-pages",
			FaroInfoTestUtil.buildIndividualVisitedPagesJSONArray(
				_assetJSONObject1,
				DateUtil.toString(DateUtil.addDays(dayDate, -31)),
				_individual.getId(), 1));

		_runIndividualInterestScoresNanite(DateUtil.toString(dayDate));

		for (Interest interest : _interestRepository.findAll()) {
			Assertions.assertEquals(
				DateUtil.addDays(dayDate, -2), interest.getRecordedDate());
		}

		JSONArray visitedPagesJSONArray = faroInfoElasticsearchInvoker.get(
			"visited-pages");

		for (int i = 0; i < visitedPagesJSONArray.length(); i++) {
			JSONObject visitedPageJSONObject =
				visitedPagesJSONArray.getJSONObject(i);

			Assertions.assertEquals(
				DateUtil.toString(DateUtil.addDays(dayDate, -30)),
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
		Date dayDate = DateUtil.newDayDate();

		Date previousDayDate = DateUtil.addDays(dayDate, -1);

		_runIndividualInterestScoresNanite(DateUtil.toString(previousDayDate));

		Map<Pair<String, Long>, Double> interestsMap = new HashMap<>();

		List<Interest> interests =
			_interestRepository.findByNameAndOwnerIdAndRecordedDate(
				null, null, previousDayDate);

		for (Interest interest : interests) {
			interestsMap.put(
				Pair.of(interest.getName(), interest.getOwnerId()),
				interest.getScore());
		}

		ReflectionTestUtils.setField(
			_individualInterestScoresNanite, "_staleInterestsQuerySize",
			interests.size() / 3);

		_runIndividualInterestScoresNanite(DateUtil.toString(dayDate));

		for (Map.Entry<Pair<String, Long>, Double> entry :
				interestsMap.entrySet()) {

			Pair<String, Long> nameOwnerIdPair = entry.getKey();

			interests = _interestRepository.findByNameAndOwnerIdAndRecordedDate(
				nameOwnerIdPair.getKey(), nameOwnerIdPair.getValue(), dayDate);

			Assertions.assertEquals(1, interests.size());

			Interest interest = interests.get(0);

			Assertions.assertTrue(entry.getValue() > interest.getScore());

			_interestRepository.delete(interest);
		}

		_interestRepository.findByNameAndOwnerIdAndRecordedDate(
			null, null, dayDate);

		interests = _interestRepository.findByNameAndOwnerIdAndRecordedDate(
			null, null, dayDate);

		Assertions.assertEquals(0, interests.size());
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

			List<Interest> interests =
				_interestRepository.findByNameAndOwnerIdAndRecordedDate(
					null, null, DateUtil.toUTCDate(dayDateString));

			if (interests.size() == 0) {
				Assertions.assertTrue(
					elapsedDays > 0,
					"No positive interest scores were calculated for " +
						dayDateString);

				return;
			}

			_assertInterestScoreDirection(
				DateUtil.toUTCDate(previousDayDateString),
				DateUtil.toUTCDate(dayDateString), false);

			_assertInterestScoresAboveThreshold(interests);

			previousDayDateString = dayDateString;

			dayDateString = DateUtil.addDays(dayDateString, 1);
		}

		throw new Exception(
			"Exceeded maximum number of days allowed before reaching score " +
				"below threshold");
	}

	@Disabled
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
			_bqMembershipDog.isMember(
				String.valueOf(individualId), segment1Id));
		Assertions.assertFalse(
			_bqMembershipDog.isMember(
				String.valueOf(individualId), segment2Id));

		_runIndividualInterestScoresNanite((JSONObject)null);

		Assertions.assertFalse(
			_bqMembershipDog.isMember(
				String.valueOf(individualId), segment1Id));
		Assertions.assertTrue(
			_bqMembershipDog.isMember(
				String.valueOf(individualId), segment2Id));
	}

	@Test
	public void testNoInterestScoresGeneratedBeforeActivitiesOccur()
		throws Exception {

		_runIndividualInterestScoresNanite(
			DateUtil.addDays(DateUtil.newDayDateString(), -2));

		long count = _interestRepository.count();

		Assertions.assertEquals(
			0, count,
			"Expected no interests to be generated, but interests were " +
				"generated: " + count);
	}

	@Test
	public void testRunTwice() {
		Date dayDate = DateUtil.newDayDate();

		_interestRepository.saveAll(
			FaroInfoTestUtil.buildIndividualInterests(
				_assetJSONObject1, _individual.getId(),
				DateUtil.addDays(dayDate, -1), 1, 10L));

		IntStream.range(
			1, 3
		).forEach(
			i -> {
				try {
					_runIndividualInterestScoresNanite(
						JSONUtil.put("processDay", DateUtil.toString(dayDate)));
				}
				catch (Exception exception) {
					_log.error(exception, exception);

					Assertions.fail();
				}
			}
		);

		Map<String, Boolean> map = new HashedMap<>();

		for (Interest interest : _interestRepository.findAll()) {
			String key = String.format(
				"%s#%s", interest.getId(), interest.getName());

			if (map.containsKey(key)) {
				Assertions.fail();
			}

			map.put(key, true);
		}

		AsahMarker asahMarker = _asahMarkerDog.getAsahMarker(
			"IndividualInterestScoresNanite");

		JSONObject asahMarkerContextJSONObject =
			asahMarker.getContextJSONObject();

		Assertions.assertEquals(
			DateUtil.toString(dayDate),
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
			DateUtil.toUTCDate(previousDayDateString),
			DateUtil.toUTCDate(dayDateString), true);
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

		Long channelId = Long.parseLong(RandomStringUtils.randomNumeric(4));

		for (JSONObject jsonObject : assetJSONObjects) {
			faroInfoElasticsearchInvoker.add(
				"activities",
				FaroInfoTestUtil.buildActivityJSONObject(
					jsonObject, channelId, _dataSource.getId(), dateString,
					"pageViewed", new String[0], individual));
		}
	}

	private void _assertInterestScoreDirection(
			Date previousDayDate, Date dayDate, boolean increasing)
		throws Exception {

		for (String keyword : _getKeywords()) {
			Interest previousDayInterest = _getInterest(
				previousDayDate, keyword);
			Interest currentDayInterest = _getInterest(dayDate, keyword);

			double previousDayScore = previousDayInterest.getScore();
			double currentDayScore = currentDayInterest.getScore();

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

	private void _assertInterestScoresAboveThreshold(List<Interest> interests) {
		for (Interest interest : interests) {
			double score = interest.getScore();

			Assertions.assertTrue(
				score >=
					IndividualInterestScoresNanite.
						MINIMUM_INTEREST_SCORE_THRESHOLD,
				"Expected interest score to be greater than or equal to the " +
					"threshold but was " + score + ": " + interest);
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

	private Interest _getInterest(Date dayDate, String name) {
		List<Interest> interests =
			_interestRepository.findByNameAndOwnerIdAndRecordedDate(
				name, null, dayDate);

		Interest interest = interests.get(0);

		Assertions.assertNotNull(
			interest,
			"Unable to get interest score for keyword \"" + name + "\" for " +
				"date " + DateUtil.toString(dayDate));

		return interest;
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
	private AsahMarkerDog _asahMarkerDog;

	private JSONObject _assetJSONObject1;
	private JSONObject _assetJSONObject2;

	@Autowired
	private AssetRepository _assetRepository;

	@Autowired
	private BQMembershipDog _bqMembershipDog;

	@Autowired
	private ChannelRepository _channelRepository;

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
	private ObjectMapper _objectMapper;

	@Autowired
	private SegmentRepository _segmentRepository;

	@Autowired
	private UpdateDynamicMembershipsNanite _updateDynamicMembershipsNanite;

}