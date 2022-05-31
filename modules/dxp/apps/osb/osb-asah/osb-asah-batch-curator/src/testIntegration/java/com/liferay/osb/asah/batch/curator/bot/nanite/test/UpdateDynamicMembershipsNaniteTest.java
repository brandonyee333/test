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
import com.liferay.osb.asah.common.dog.ActivityGroupDog;
import com.liferay.osb.asah.common.dog.AsahMarkerDog;
import com.liferay.osb.asah.common.dog.DXPEntityDog;
import com.liferay.osb.asah.common.dog.IndividualDog;
import com.liferay.osb.asah.common.dog.MembershipDog;
import com.liferay.osb.asah.common.dog.OrganizationDog;
import com.liferay.osb.asah.common.dog.SegmentDog;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.entity.Account;
import com.liferay.osb.asah.common.entity.ActivityGroup;
import com.liferay.osb.asah.common.entity.AsahMarker;
import com.liferay.osb.asah.common.entity.Asset;
import com.liferay.osb.asah.common.entity.BQMembership;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.entity.DataSourceIndividual;
import com.liferay.osb.asah.common.entity.Field;
import com.liferay.osb.asah.common.entity.Individual;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.repository.AccountRepository;
import com.liferay.osb.asah.common.repository.AssetRepository;
import com.liferay.osb.asah.common.repository.BQMembershipRepository;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.common.repository.FieldMappingRepository;
import com.liferay.osb.asah.common.repository.FieldRepository;
import com.liferay.osb.asah.common.repository.SegmentRepository;
import com.liferay.osb.asah.common.util.SetUtil;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Vishal Reddy
 * @author Rachael Koestartyo
 * @author Michael Bowerman
 */
public class UpdateDynamicMembershipsNaniteTest
	extends BaseNaniteTestCase implements OSBAsahTestExecutionListenersContext {

	@Test
	public void test() throws Exception {
		Segment segment = _segmentRepository.save(
			FaroInfoTestUtil.buildDynamicSegment(1L, "id gt '0'"));

		_updateDynamicMembershipsNanite.run(
			_objectMapper.convertValue(segment, JSONObject.class));

		segment = _segmentDog.getSegment(segment.getId());

		Assertions.assertEquals("READY", segment.getState());
	}

	@Test
	public void testBehavioralCriteria() throws Exception {
		DataSource dataSource = _dataSourceRepository.save(
			FaroInfoTestUtil.buildLiferayDataSource());

		Individual individual1 = FaroInfoTestUtil.buildIndividual(
			1L, dataSource);

		_fieldRepository.saveAll(individual1.getFields());

		individual1 = _individualDog.addIndividual(individual1, false);

		Individual individual2 = FaroInfoTestUtil.buildIndividual(
			1L, dataSource);

		_fieldRepository.saveAll(individual2.getFields());

		individual2 = _individualDog.addIndividual(individual2, false);

		Long dataSourceId = dataSource.getId();

		JSONObject assetJSONObject = _objectMapper.convertValue(
			_assetRepository.save(
				_objectMapper.convertValue(
					FaroInfoTestUtil.buildPageAssetJSONObject(dataSourceId),
					Asset.class)),
			JSONObject.class);

		ActivityGroup activityGroup1 = _activityGroupDog.addActivityGroup(
			FaroInfoTestUtil.buildActivityGroup(dataSourceId, individual1));
		ActivityGroup activityGroup2 = _activityGroupDog.addActivityGroup(
			FaroInfoTestUtil.buildActivityGroup(dataSourceId, individual2));

		faroInfoElasticsearchInvoker.add(
			"activities",
			FaroInfoTestUtil.buildActivityJSONObject(
				_objectMapper.convertValue(activityGroup1, JSONObject.class),
				assetJSONObject, "pageViewed", new String[0]));
		faroInfoElasticsearchInvoker.add(
			"activities",
			FaroInfoTestUtil.buildActivityJSONObject(
				_objectMapper.convertValue(activityGroup2, JSONObject.class),
				assetJSONObject, "pageUnloaded",
				new String[] {"viewDuration", "1000"}));

		Long individualSegmentId = _updateDynamicMemberships(
			"(((activities/ever eq 'Page#pageViewed#" +
				assetJSONObject.getString("id") + "')))");

		Long individual1Id = individual1.getId();
		Long individual2Id = individual2.getId();

		Assertions.assertTrue(
			_membershipDog.isMember(individual1Id, individualSegmentId));
		Assertions.assertFalse(
			_membershipDog.isMember(individual2Id, individualSegmentId));

		individualSegmentId = _updateDynamicMemberships(
			"(((activities/ever ne 'Page#pageViewed#" +
				assetJSONObject.getString("id") + "')))");

		Assertions.assertFalse(
			_membershipDog.isMember(individual1Id, individualSegmentId));
		Assertions.assertTrue(
			_membershipDog.isMember(individual2Id, individualSegmentId));
	}

	@Test
	public void testIndividualSegmentState() throws Exception {
		Segment segment = _segmentRepository.save(
			FaroInfoTestUtil.buildDynamicSegment(1L, ""));

		_updateDynamicMembershipsNanite.run(
			_getContextJSONObject(
				_objectMapper.convertValue(segment, JSONObject.class)));

		segment = _segmentDog.getSegment(segment.getId());

		Assertions.assertEquals("READY", segment.getState());
	}

	@Test
	public void testInterestCriteria() throws Exception {
		DataSource dataSource = _dataSourceRepository.save(
			FaroInfoTestUtil.buildLiferayDataSource());

		Individual individual1 = FaroInfoTestUtil.buildIndividual(
			1L, dataSource);

		_fieldRepository.saveAll(individual1.getFields());

		individual1 = _individualDog.addIndividual(individual1, false);

		Individual individual2 = FaroInfoTestUtil.buildIndividual(
			1L, dataSource);

		_fieldRepository.saveAll(individual2.getFields());

		individual2 = _individualDog.addIndividual(individual2, false);

		Individual individual3 = FaroInfoTestUtil.buildIndividual(
			1L, dataSource);

		_fieldRepository.saveAll(individual3.getFields());

		individual3 = _individualDog.addIndividual(individual3, false);

		Long dataSourceId = dataSource.getId();

		JSONObject assetJSONObject = _objectMapper.convertValue(
			_assetRepository.save(
				_objectMapper.convertValue(
					FaroInfoTestUtil.buildPageAssetJSONObject(dataSourceId),
					Asset.class)),
			JSONObject.class);

		String dayDateString = DateUtil.newDayDateString();

		Long individual1Id = individual1.getId();
		Long individual2Id = individual2.getId();

		_addIndividualInterests(
			dayDateString,
			FaroInfoTestUtil.buildIndividualInterestsJSONArray(
				assetJSONObject, dayDateString, individual1Id, 0.5, 20),
			FaroInfoTestUtil.buildIndividualInterestsJSONArray(
				assetJSONObject, dayDateString, individual2Id, 0.1, 4));

		JSONArray keywordsJSONArray = assetJSONObject.getJSONArray("keywords");

		JSONObject keywordJSONObject = keywordsJSONArray.getJSONObject(
			RandomUtils.nextInt(0, keywordsJSONArray.length()));

		String keyword = keywordJSONObject.getString("keyword");

		_asahMarkerDog.addAsahMarker(
			new AsahMarker(
				"InterestThresholdScoreNanite",
				JSONUtil.put(
					"lastSuccessfulDay", dayDateString
				).put(
					"score", 0.3
				).put(
					"type", "nanite"
				)));

		Long individualSegmentId = _updateDynamicMemberships(
			"interests.filter(filter='(name eq ''" + keyword + "'') and " +
				"(score eq ''true'')')");

		Long individual3Id = individual3.getId();

		Assertions.assertTrue(
			_membershipDog.isMember(individual1Id, individualSegmentId));
		Assertions.assertFalse(
			_membershipDog.isMember(individual2Id, individualSegmentId));
		Assertions.assertFalse(
			_membershipDog.isMember(individual3Id, individualSegmentId));

		individualSegmentId = _updateDynamicMemberships(
			"interests.filter(filter='(name eq ''" + keyword + "'') and " +
				"(score eq ''false'')')");

		Assertions.assertFalse(
			_membershipDog.isMember(individual1Id, individualSegmentId));
		Assertions.assertTrue(
			_membershipDog.isMember(individual2Id, individualSegmentId));
		Assertions.assertTrue(
			_membershipDog.isMember(individual3Id, individualSegmentId));
	}

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

		String dayDateString = DateUtil.newDayDateString();

		Individual individual1 = FaroInfoTestUtil.buildIndividual(
			1L, dataSource);

		_fieldRepository.saveAll(individual1.getFields());

		individual1 = _individualDog.addIndividual(individual1, false);

		Individual individual2 = FaroInfoTestUtil.buildIndividual(
			1L, dataSource);

		_fieldRepository.saveAll(individual2.getFields());

		individual2 = _individualDog.addIndividual(individual2, false);

		Long individual1Id = individual1.getId();
		Long individual2Id = individual2.getId();

		_addIndividualInterests(
			dayDateString,
			FaroInfoTestUtil.buildIndividualInterestsJSONArray(
				assetJSONObject, dayDateString, individual1Id, 0.5, 20),
			FaroInfoTestUtil.buildIndividualInterestsJSONArray(
				assetJSONObject, dayDateString, individual2Id, 0.1, 4));

		_asahMarkerDog.addAsahMarker(
			new AsahMarker(
				"InterestThresholdScoreNanite",
				JSONUtil.put(
					"lastSuccessfulDay", dayDateString
				).put(
					"score", 0.3
				).put(
					"type", "nanite"
				)));

		Long individualSegmentId = _updateDynamicMemberships(
			"interests.filter(filter='(name eq ''test'') and (score eq " +
				"''false'')')");

		Assertions.assertFalse(
			_membershipDog.isMember(individual1Id, individualSegmentId));
		Assertions.assertTrue(
			_membershipDog.isMember(individual2Id, individualSegmentId));

		individualSegmentId = _updateDynamicMemberships(
			"interests.filter(filter='(name eq ''test'') and (score eq " +
				"''true'')')");

		Assertions.assertTrue(
			_membershipDog.isMember(individual1Id, individualSegmentId));
		Assertions.assertFalse(
			_membershipDog.isMember(individual2Id, individualSegmentId));
	}

	@Test
	public void testKeywordInterestMembershipNoThreshold() throws Exception {
		DataSource dataSource = _dataSourceRepository.save(
			FaroInfoTestUtil.buildLiferayDataSource());

		Individual individual = FaroInfoTestUtil.buildIndividual(
			1L, dataSource);

		_fieldRepository.saveAll(individual.getFields());

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

		String dayDateString = DateUtil.newDayDateString();

		_addIndividualInterests(
			dayDateString,
			FaroInfoTestUtil.buildIndividualInterestsJSONArray(
				assetJSONObject, dayDateString, individual.getId(), 0.5, 20));

		Long individualSegmentId = _updateDynamicMemberships(
			"interests.filter(filter='(name eq ''test'') and (score eq " +
				"''true'')')");

		Assertions.assertNull(
			faroInfoElasticsearchInvoker.fetch(
				"memberships",
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery(
						"individualSegmentId",
						String.valueOf(individualSegmentId)))),
			"No memberships should be added if no interest threshold exists");
	}

	@Test
	public void testRemoveMembershipsWithDifferentChannel() throws Exception {
		DataSource dataSource = _dataSourceRepository.save(
			FaroInfoTestUtil.buildLiferayDataSource());

		Segment segment = _segmentRepository.save(
			FaroInfoTestUtil.buildDynamicSegment(1L, "id gt '0'"));

		_fieldMappingRepository.save(
			FaroInfoTestUtil.buildIndividualFieldMapping(
				dataSource.getId(), "email", "email", "Text"));

		Individual individual1 = FaroInfoTestUtil.buildIndividual(
			1L, dataSource);

		_fieldRepository.saveAll(individual1.getFields());

		individual1 = _individualDog.addIndividual(individual1, false);

		Individual individual2 = FaroInfoTestUtil.buildIndividual(
			2L, dataSource);

		_fieldRepository.saveAll(individual2.getFields());

		individual2 = _individualDog.addIndividual(individual2, false);

		_membershipDog.addBQMembership(
			FaroInfoTestUtil.buildBQMembership(
				individual1.getId(), segment.getId()));
		_membershipDog.addBQMembership(
			FaroInfoTestUtil.buildBQMembership(
				individual2.getId(), segment.getId()));

		_updateDynamicMembershipsNanite.run(
			_objectMapper.convertValue(segment, JSONObject.class));

		List<Long> individualIds = _membershipDog.getActiveIndividualIds(
			segment.getId());

		Assertions.assertEquals(
			1, individualIds.size(), individualIds.toString());
	}

	@Test
	public void testSalesforceAccountMemberships() throws Exception {
		Account account = new Account();

		account.setAccountPK("345");
		account.setDataSourceId(123L);
		account.setId(234L);

		_accountRepository.save(account);

		Individual individual = new Individual();

		individual.setChannelIds(Collections.singleton(1L));

		DataSourceIndividual dataSourceIndividual = new DataSourceIndividual();

		dataSourceIndividual.setAccountPKs(Collections.singleton("345"));
		dataSourceIndividual.setDataSourceId(123L);

		individual.setDataSourceIndividuals(
			Collections.singleton(dataSourceIndividual));

		Field field = new Field();

		field.setContext("demographics");
		field.setDataSourceId(123L);
		field.setFieldType("Text");
		field.setName("email");
		field.setOwnerId(123L);
		field.setOwnerType("individual");
		field.setSourceName("email");
		field.setValue(RandomTestUtil.randomEmailAddress());

		individual.setFields(Collections.singleton(field));

		individual.setSegmentIds(Collections.emptySet());

		_individualDog.addIndividual(individual, false);

		_updateDynamicMemberships(
			"(((dataSourceAccountPKs/accountPKs eq '345')))");

		Assertions.assertEquals(1, _bqMembershipRepository.count());

		Iterable<BQMembership> iterable = _bqMembershipRepository.findAll();

		Iterator<BQMembership> iterator = iterable.iterator();

		BQMembership bqMembership = iterator.next();

		individual = _individualDog.fetchIndividual(
			bqMembership.getIndividualId());

		Assertions.assertEquals(
			Collections.singleton(
				new Individual.DataSourceAccountPK(dataSourceIndividual)),
			individual.getDataSourceAccountPKs());
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

		_fieldRepository.saveAll(individual1.getFields());

		individuals.add(_individualDog.addIndividual(individual1, false));

		Individual individual2 = FaroInfoTestUtil.buildIndividual(
			1L, dataSource);

		individual2.setSegmentIds(SetUtil.of(segment.getId()));

		_fieldRepository.saveAll(individual2.getFields());

		individuals.add(_individualDog.addIndividual(individual2, false));

		_updateDynamicMembershipsNanite.run(
			JSONUtil.put(
				"individualsJSONArray",
				JSONUtil.toJSONArray(individuals, Individual::getId)));

		individuals.forEach(
			individual -> Assertions.assertFalse(
				_membershipDog.isMember(individual.getId(), segment.getId())));
	}

	private void _addIndividualInterests(
		String dayDateString, JSONArray... individualInterestsJSONArrays) {

		JSONArray jsonArray = new JSONArray();

		for (JSONArray individualInterestsJSONArray :
				individualInterestsJSONArrays) {

			for (Object value : individualInterestsJSONArray) {
				jsonArray.put(value);
			}
		}

		faroInfoElasticsearchInvoker.add("interests", jsonArray);

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
	private AccountRepository _accountRepository;

	@Autowired
	private ActivityGroupDog _activityGroupDog;

	@Autowired
	private AsahMarkerDog _asahMarkerDog;

	@Autowired
	private AssetRepository _assetRepository;

	@Autowired
	private BQMembershipRepository _bqMembershipRepository;

	@Autowired
	private DataSourceRepository _dataSourceRepository;

	@Autowired
	private DXPEntityDog _dxpEntityDog;

	@Autowired
	private FieldMappingRepository _fieldMappingRepository;

	@Autowired
	private FieldRepository _fieldRepository;

	@Autowired
	private IndividualDog _individualDog;

	@Autowired
	private MembershipDog _membershipDog;

	@Autowired
	private ObjectMapper _objectMapper;

	@Autowired
	private OrganizationDog _organizationDog;

	@Autowired
	private SegmentDog _segmentDog;

	@Autowired
	private SegmentRepository _segmentRepository;

	@Autowired
	private UpdateDynamicMembershipsNanite _updateDynamicMembershipsNanite;

}