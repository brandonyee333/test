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

import com.liferay.osb.asah.batch.curator.bot.nanite.UpdateDynamicMembershipsNanite;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.AsahMarkerDog;
import com.liferay.osb.asah.common.dog.BQMembershipDog;
import com.liferay.osb.asah.common.dog.IndividualDog;
import com.liferay.osb.asah.common.dog.SegmentDog;
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
import com.liferay.osb.asah.common.repository.BQMembershipRepository;
import com.liferay.osb.asah.common.repository.ChannelRepository;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.common.repository.InterestRepository;
import com.liferay.osb.asah.common.repository.SegmentRepository;
import com.liferay.osb.asah.common.util.SetUtil;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Vishal Reddy
 * @author Rachael Koestartyo
 * @author Michael Bowerman
 */
public class UpdateDynamicMembershipsNaniteTest
	extends BaseNaniteTestCase implements OSBAsahTestExecutionListenersContext {

	@BeforeEach
	public void setUp() throws Exception {
		Channel channel = new Channel();

		channel.setId(1L);
		channel.setIsNew(Boolean.TRUE);
		channel.setName("Liferay");

		_channelRepository.save(channel);
	}

	@Test
	public void test() throws Exception {
		Segment segment = _segmentRepository.save(
			FaroInfoTestUtil.buildDynamicSegment(1L, "id gt '0'"));

		_updateDynamicMembershipsNanite.run(
			_objectMapper.convertValue(segment, JSONObject.class));

		segment = _segmentDog.getSegment(segment.getId());

		Assertions.assertEquals("READY", segment.getState());
	}

	@Disabled
	@Test
	public void testBehavioralCriteria() throws Exception {
		DataSource dataSource = _dataSourceRepository.save(
			FaroInfoTestUtil.buildLiferayDataSource());

		Individual individual1 = FaroInfoTestUtil.buildIndividual(
			1L, dataSource);

		individual1 = _individualDog.addIndividual(individual1, false);

		Individual individual2 = FaroInfoTestUtil.buildIndividual(
			1L, dataSource);

		individual2 = _individualDog.addIndividual(individual2, false);

		Long dataSourceId = dataSource.getId();

		JSONObject assetJSONObject = _objectMapper.convertValue(
			_assetRepository.save(
				_objectMapper.convertValue(
					FaroInfoTestUtil.buildPageAssetJSONObject(dataSourceId),
					Asset.class)),
			JSONObject.class);

		faroInfoElasticsearchInvoker.add(
			"activities",
			FaroInfoTestUtil.buildActivityJSONObject(
				assetJSONObject,
				Long.parseLong(RandomStringUtils.randomNumeric(4)),
				dataSourceId, DateUtil.newDateString(), "pageViewed",
				new String[0], individual1));
		faroInfoElasticsearchInvoker.add(
			"activities",
			FaroInfoTestUtil.buildActivityJSONObject(
				assetJSONObject,
				Long.parseLong(RandomStringUtils.randomNumeric(4)),
				dataSourceId, DateUtil.newDateString(), "pageUnloaded",
				new String[] {"viewDuration", "1000"}, individual2));

		Long segmentId = _updateDynamicMemberships(
			"(((activities/ever eq 'Page#pageViewed#" +
				assetJSONObject.getString("id") + "')))");

		Long individual1Id = individual1.getId();
		Long individual2Id = individual2.getId();

		Assertions.assertTrue(
			_bqMembershipDog.isMember(
				String.valueOf(individual1Id), segmentId));
		Assertions.assertFalse(
			_bqMembershipDog.isMember(
				String.valueOf(individual2Id), segmentId));

		segmentId = _updateDynamicMemberships(
			"(((activities/ever ne 'Page#pageViewed#" +
				assetJSONObject.getString("id") + "')))");

		Assertions.assertFalse(
			_bqMembershipDog.isMember(
				String.valueOf(individual1Id), segmentId));
		Assertions.assertTrue(
			_bqMembershipDog.isMember(
				String.valueOf(individual2Id), segmentId));
	}

	@Disabled
	@Test
	public void testInterestCriteria() throws Exception {
		DataSource dataSource = _dataSourceRepository.save(
			FaroInfoTestUtil.buildLiferayDataSource());

		Individual individual1 = FaroInfoTestUtil.buildIndividual(
			1L, dataSource);

		individual1 = _individualDog.addIndividual(individual1, false);

		Individual individual2 = FaroInfoTestUtil.buildIndividual(
			1L, dataSource);

		individual2 = _individualDog.addIndividual(individual2, false);

		Individual individual3 = FaroInfoTestUtil.buildIndividual(
			1L, dataSource);

		individual3 = _individualDog.addIndividual(individual3, false);

		Long dataSourceId = dataSource.getId();

		JSONObject assetJSONObject = _objectMapper.convertValue(
			_assetRepository.save(
				_objectMapper.convertValue(
					FaroInfoTestUtil.buildPageAssetJSONObject(dataSourceId),
					Asset.class)),
			JSONObject.class);

		Date dayDate = DateUtil.newDayDate();

		Long individual1Id = individual1.getId();
		Long individual2Id = individual2.getId();

		_addIndividualInterests(
			DateUtil.toString(dayDate),
			FaroInfoTestUtil.buildIndividualInterests(
				assetJSONObject, individual1Id, dayDate, 0.5, 20L),
			FaroInfoTestUtil.buildIndividualInterests(
				assetJSONObject, individual2Id, dayDate, 0.1, 4L));

		JSONArray keywordsJSONArray = assetJSONObject.getJSONArray("keywords");

		JSONObject keywordJSONObject = keywordsJSONArray.getJSONObject(
			RandomUtils.nextInt(0, keywordsJSONArray.length()));

		String keyword = keywordJSONObject.getString("keyword");

		_asahMarkerDog.addAsahMarker(
			new AsahMarker(
				"InterestThresholdScoreNanite",
				JSONUtil.put(
					"lastSuccessfulDay", DateUtil.toString(dayDate)
				).put(
					"score", 0.3
				).put(
					"type", "nanite"
				)));

		Long segmentId = _updateDynamicMemberships(
			"interests.filter(filter='(name eq ''" + keyword + "'') and " +
				"(score eq ''true'')')");

		Long individual3Id = individual3.getId();

		Assertions.assertTrue(
			_bqMembershipDog.isMember(
				String.valueOf(individual1Id), segmentId));
		Assertions.assertFalse(
			_bqMembershipDog.isMember(
				String.valueOf(individual2Id), segmentId));
		Assertions.assertFalse(
			_bqMembershipDog.isMember(
				String.valueOf(individual3Id), segmentId));

		segmentId = _updateDynamicMemberships(
			"interests.filter(filter='(name eq ''" + keyword + "'') and " +
				"(score eq ''false'')')");

		Assertions.assertFalse(
			_bqMembershipDog.isMember(
				String.valueOf(individual1Id), segmentId));
		Assertions.assertTrue(
			_bqMembershipDog.isMember(
				String.valueOf(individual2Id), segmentId));
		Assertions.assertTrue(
			_bqMembershipDog.isMember(
				String.valueOf(individual3Id), segmentId));
	}

	@Disabled
	@Test
	public void testKeywordInterestMembership() throws Exception {
		DataSource dataSource = _dataSourceRepository.save(
			FaroInfoTestUtil.buildLiferayDataSource());

		JSONObject assetJSONObject = _objectMapper.convertValue(
			_assetRepository.save(
				_objectMapper.convertValue(
					FaroInfoTestUtil.buildPageAssetJSONObject(
						dataSource.getId(),
						JSONUtil.put(
							JSONUtil.put(
								"keyword", "test"
							).put(
								"type", "keyword"
							))),
					Asset.class)),
			JSONObject.class);

		Date dayDate = DateUtil.newDayDate();

		Individual individual1 = FaroInfoTestUtil.buildIndividual(
			1L, dataSource);

		individual1 = _individualDog.addIndividual(individual1, false);

		Individual individual2 = FaroInfoTestUtil.buildIndividual(
			1L, dataSource);

		individual2 = _individualDog.addIndividual(individual2, false);

		Long individual1Id = individual1.getId();
		Long individual2Id = individual2.getId();

		_addIndividualInterests(
			DateUtil.toString(dayDate),
			FaroInfoTestUtil.buildIndividualInterests(
				assetJSONObject, individual1Id, dayDate, 0.5, 20L),
			FaroInfoTestUtil.buildIndividualInterests(
				assetJSONObject, individual2Id, dayDate, 0.1, 4L));

		_asahMarkerDog.addAsahMarker(
			new AsahMarker(
				"InterestThresholdScoreNanite",
				JSONUtil.put(
					"lastSuccessfulDay", DateUtil.toString(dayDate)
				).put(
					"score", 0.3
				).put(
					"type", "nanite"
				)));

		Long segmentId = _updateDynamicMemberships(
			"interests.filter(filter='(name eq ''test'') and (score eq " +
				"''false'')')");

		Assertions.assertFalse(
			_bqMembershipDog.isMember(
				String.valueOf(individual1Id), segmentId));
		Assertions.assertTrue(
			_bqMembershipDog.isMember(
				String.valueOf(individual2Id), segmentId));

		segmentId = _updateDynamicMemberships(
			"interests.filter(filter='(name eq ''test'') and (score eq " +
				"''true'')')");

		Assertions.assertTrue(
			_bqMembershipDog.isMember(
				String.valueOf(individual1Id), segmentId));
		Assertions.assertFalse(
			_bqMembershipDog.isMember(
				String.valueOf(individual2Id), segmentId));
	}

	@Disabled
	@Test
	public void testKeywordInterestMembershipNoThreshold() throws Exception {
		DataSource dataSource = _dataSourceRepository.save(
			FaroInfoTestUtil.buildLiferayDataSource());

		Individual individual = FaroInfoTestUtil.buildIndividual(
			1L, dataSource);

		individual = _individualDog.addIndividual(individual, false);

		JSONObject assetJSONObject = _objectMapper.convertValue(
			_assetRepository.save(
				_objectMapper.convertValue(
					FaroInfoTestUtil.buildPageAssetJSONObject(
						dataSource.getId(),
						JSONUtil.put(
							JSONUtil.put(
								"keyword", "test"
							).put(
								"type", "keyword"
							))),
					Asset.class)),
			JSONObject.class);

		Date dayDate = DateUtil.newDayDate();

		_addIndividualInterests(
			DateUtil.toString(dayDate),
			FaroInfoTestUtil.buildIndividualInterests(
				assetJSONObject, individual.getId(), dayDate, 0.5, 20L));

		Long segmentId = _updateDynamicMemberships(
			"interests.filter(filter='(name eq ''test'') and (score eq " +
				"''true'')')");

		Assertions.assertNull(
			faroInfoElasticsearchInvoker.fetch(
				"memberships",
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery(
						"individualSegmentId", String.valueOf(segmentId)))),
			"No memberships should be added if no interest threshold exists");
	}

	@Disabled
	@Test
	public void testRemoveMembershipsWithDifferentChannel() throws Exception {
		DataSource dataSource = _dataSourceRepository.save(
			FaroInfoTestUtil.buildLiferayDataSource());

		Segment segment = _segmentRepository.save(
			FaroInfoTestUtil.buildDynamicSegment(1L, "id gt '0'"));

		// TODO Add BQFieldMapping "email", "Text"

		Individual individual1 = FaroInfoTestUtil.buildIndividual(
			1L, dataSource);

		individual1 = _individualDog.addIndividual(individual1, false);

		Individual individual2 = FaroInfoTestUtil.buildIndividual(
			2L, dataSource);

		individual2 = _individualDog.addIndividual(individual2, false);

		_bqMembershipDog.addBQMembership(
			FaroInfoTestUtil.buildBQMembership(
				String.valueOf(individual1.getId()), segment.getId()));
		_bqMembershipDog.addBQMembership(
			FaroInfoTestUtil.buildBQMembership(
				String.valueOf(individual2.getId()), segment.getId()));

		_updateDynamicMembershipsNanite.run(
			_objectMapper.convertValue(segment, JSONObject.class));

		List<String> individualIds = _bqMembershipDog.getActiveIdentityIds(
			segment.getId());

		Assertions.assertEquals(
			1, individualIds.size(), individualIds.toString());
	}

	@Test
	public void testSegmentState() throws Exception {
		Segment segment = _segmentRepository.save(
			FaroInfoTestUtil.buildDynamicSegment(1L, ""));

		_updateDynamicMembershipsNanite.run(
			_getContextJSONObject(
				_objectMapper.convertValue(segment, JSONObject.class)));

		segment = _segmentDog.getSegment(segment.getId());

		Assertions.assertEquals("READY", segment.getState());
	}

	@Test
	public void testWithIndividuals() throws Exception {
		Segment segment = _segmentRepository.save(
			FaroInfoTestUtil.buildStaticSegment());

		List<Individual> individuals = new ArrayList<>();

		DataSource dataSource = _dataSourceRepository.save(
			FaroInfoTestUtil.buildLiferayDataSource());

		Individual individual1 = FaroInfoTestUtil.buildIndividual(
			1L, dataSource);

		individual1.setSegmentIds(SetUtil.of(segment.getId()));

		individuals.add(_individualDog.addIndividual(individual1, false));

		Individual individual2 = FaroInfoTestUtil.buildIndividual(
			1L, dataSource);

		individual2.setSegmentIds(SetUtil.of(segment.getId()));

		individuals.add(_individualDog.addIndividual(individual2, false));

		_updateDynamicMembershipsNanite.run(
			JSONUtil.put(
				"individualsJSONArray",
				JSONUtil.toJSONArray(individuals, Individual::getId)));

		individuals.forEach(
			individual -> Assertions.assertFalse(
				_bqMembershipDog.isMember(
					String.valueOf(individual.getId()), segment.getId())));
	}

	private void _addIndividualInterests(
		String dayDateString, List<Interest>... interestsLists) {

		for (List<Interest> interests : interestsLists) {
			_interestRepository.saveAll(interests);
		}

		_asahMarkerDog.addAsahMarker(
			new AsahMarker(
				"IndividualInterestScoresNanite",
				JSONUtil.put(
					"lastSuccessfulDay", dayDateString
				).put(
					"type", "nanite"
				)));
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

	private Long _updateDynamicMemberships(String filter) throws Exception {
		Segment segment = _segmentRepository.save(
			FaroInfoTestUtil.buildDynamicSegment(1L, filter));

		_updateDynamicMembershipsNanite.run(
			_getContextJSONObject(
				_objectMapper.convertValue(segment, JSONObject.class)));

		return segment.getId();
	}

	@Autowired
	private AsahMarkerDog _asahMarkerDog;

	@Autowired
	private AssetRepository _assetRepository;

	@Autowired
	private BQMembershipDog _bqMembershipDog;

	@Autowired
	private BQMembershipRepository _bqMembershipRepository;

	@Autowired
	private ChannelRepository _channelRepository;

	@Autowired
	private DataSourceRepository _dataSourceRepository;

	@Autowired
	private IndividualDog _individualDog;

	@Autowired
	private InterestRepository _interestRepository;

	@Autowired
	private ObjectMapper _objectMapper;

	@Autowired
	private SegmentDog _segmentDog;

	@Autowired
	private SegmentRepository _segmentRepository;

	@Autowired
	private UpdateDynamicMembershipsNanite _updateDynamicMembershipsNanite;

}