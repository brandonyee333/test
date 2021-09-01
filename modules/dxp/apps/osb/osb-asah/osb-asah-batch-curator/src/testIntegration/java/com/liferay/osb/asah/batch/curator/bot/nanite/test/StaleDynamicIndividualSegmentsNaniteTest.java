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

import com.liferay.osb.asah.batch.curator.bot.nanite.StaleDynamicIndividualSegmentsNanite;
import com.liferay.osb.asah.batch.curator.bot.nanite.UpdateDynamicMembershipsNanite;
import com.liferay.osb.asah.batch.curator.spring.OSBAsahBatchCuratorSpringBootApplication;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.ActivityGroupDog;
import com.liferay.osb.asah.common.dog.AsahTaskDog;
import com.liferay.osb.asah.common.dog.IndividualDog;
import com.liferay.osb.asah.common.dog.SegmentDog;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.entity.ActivityGroup;
import com.liferay.osb.asah.common.entity.AsahTask;
import com.liferay.osb.asah.common.entity.Asset;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.entity.Individual;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.repository.AssetRepository;
import com.liferay.osb.asah.common.repository.FieldRepository;
import com.liferay.osb.asah.common.repository.SegmentRepository;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import java.util.Collections;
import java.util.HashMap;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Leslie Wong
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OSBAsahBatchCuratorSpringBootApplication.class)
public class StaleDynamicIndividualSegmentsNaniteTest
	extends BaseNaniteTestCase {

	@Before
	public void setUp() {
		_mock();

		_dataSource = FaroInfoTestUtil.buildLiferayDataSource();

		_dataSource.setId(Long.parseLong(RandomTestUtil.randomId()));

		_assetJSONObject = _objectMapper.convertValue(
			_assetRepository.save(
				_objectMapper.convertValue(
					FaroInfoTestUtil.buildPageAssetJSONObject(
						_dataSource.getId()),
					Asset.class)),
			JSONObject.class);

		Individual individual = FaroInfoTestUtil.buildIndividual(_dataSource);

		_fieldRepository.saveAll(individual.getFields());

		_individual = _individualDog.addIndividual(individual, false);
	}

	@After
	public void tearDown() {
		faroInfoElasticsearchInvoker.delete(
			"OSBAsahMarkers", "StaleDynamicIndividualSegmentsNanite");
	}

	@Test
	public void testMembershipsAreDeactivatedIfNoActivities() throws Exception {
		JSONArray membershipsJSONArray = _addMemberships(
			"last30Days", "last7Days", "lastYear", "today");

		_staleDynamicIndividualSegmentsNanite.run(null);

		for (int i = 0; i < membershipsJSONArray.length(); i++) {
			JSONObject membershipJSONObject =
				membershipsJSONArray.getJSONObject(i);

			membershipJSONObject = faroInfoElasticsearchInvoker.fetch(
				"memberships", membershipJSONObject.getString("id"));

			String individualSegmentActivityFilterDuration =
				_getIndividualSegmentActivityFilterDuration(
					membershipJSONObject);

			Assert.assertEquals(
				"Membership should be deactivated if no activities were " +
					"found for an individual within " +
						individualSegmentActivityFilterDuration,
				"INACTIVE", membershipJSONObject.getString("status"));

			Assert.assertFalse(
				"Individual segment ID should be removed from individual " +
					"when membership is deactivated",
				faroInfoElasticsearchInvoker.exists(
					"individuals",
					BoolQueryBuilderUtil.filter(
						QueryBuilders.termQuery(
							"id",
							membershipJSONObject.getString("individualId"))
					).filter(
						QueryBuilders.termQuery(
							"individualSegmentIds",
							membershipJSONObject.getString(
								"individualSegmentId"))
					)));
		}
	}

	@Test
	public void testMembershipsOfAllTimeDynamicIndividualSegmentsRemainActive()
		throws Exception {

		JSONArray membershipsJSONArray = _addMemberships("ever");

		_staleDynamicIndividualSegmentsNanite.run(null);

		for (int i = 0; i < membershipsJSONArray.length(); i++) {
			JSONObject membershipJSONObject =
				membershipsJSONArray.getJSONObject(i);

			Assert.assertTrue(
				"Membership should remain active if activities were found",
				faroInfoElasticsearchInvoker.exists(
					"memberships",
					BoolQueryBuilderUtil.filter(
						QueryBuilders.termQuery(
							"id", membershipJSONObject.getString("id"))
					).filter(
						QueryBuilders.termQuery("status", "ACTIVE")
					)));
		}
	}

	@Test
	public void testMembershipsOfStaleDynamicIndividualSegmentsAreDeactivated()
		throws Exception {

		_addActivity(DateUtil.addDays(DateUtil.newDayDateString(), -3));

		JSONArray membershipsJSONArray = _addMemberships("last7Days", "today");

		_staleDynamicIndividualSegmentsNanite.run(null);

		for (int i = 0; i < membershipsJSONArray.length(); i++) {
			JSONObject membershipJSONObject =
				membershipsJSONArray.getJSONObject(i);

			membershipJSONObject = faroInfoElasticsearchInvoker.get(
				"memberships", membershipJSONObject.getString("id"));

			String individualSegmentActivityFilterDuration =
				_getIndividualSegmentActivityFilterDuration(
					membershipJSONObject);

			String status = membershipJSONObject.getString("status");

			if (status.equals("ACTIVE")) {
				Assert.assertEquals(
					"Membership should remain active if activities were " +
						"found within the last 7 days",
					"last7Days", individualSegmentActivityFilterDuration);
			}
			else {
				Assert.assertEquals(
					"Membership should be deactivated if no activities were " +
						"found within today",
					"today", individualSegmentActivityFilterDuration);
			}
		}
	}

	@Test
	public void testMembershipsRemainActive() throws Exception {
		_addActivity(DateUtil.newDayDateString());

		JSONArray membershipsJSONArray = _addMemberships(
			"last30Days", "last7Days", "lastYear", "today");

		_staleDynamicIndividualSegmentsNanite.run(null);

		for (int i = 0; i < membershipsJSONArray.length(); i++) {
			JSONObject membershipJSONObject =
				membershipsJSONArray.getJSONObject(i);

			String individualSegmentActivityFilterDuration =
				_getIndividualSegmentActivityFilterDuration(
					membershipJSONObject);

			Assert.assertTrue(
				"Membership should remain active if activities were found " +
					"within " + individualSegmentActivityFilterDuration,
				faroInfoElasticsearchInvoker.exists(
					"memberships",
					BoolQueryBuilderUtil.filter(
						QueryBuilders.termQuery(
							"id", membershipJSONObject.getString("id"))
					).filter(
						QueryBuilders.termQuery("status", "ACTIVE")
					)));
		}
	}

	@Test
	public void testStaleSessionCriteriaMembershipsAreDeactivated()
		throws Exception {

		_addUserSession(DateUtil.addDays(DateUtil.newDayDateString(), -91));

		_addSessionMemberships(
			"last24Hours", "last28Days", "last30Days", "last7Days",
			"last90Days", "yesterday");

		JSONArray individualSegmentsJSONArray =
			_getSessionsIndividualSegments();

		for (int i = 0; i < individualSegmentsJSONArray.length(); i++) {
			JSONObject individualSegmentJSONObject =
				individualSegmentsJSONArray.getJSONObject(i);

			Assert.assertTrue(
				faroInfoElasticsearchInvoker.exists(
					"memberships",
					BoolQueryBuilderUtil.filter(
						QueryBuilders.termQuery(
							"individualSegmentId",
							individualSegmentJSONObject.getString("id"))
					).filter(
						QueryBuilders.termQuery("status", "ACTIVE")
					)));
		}

		_staleDynamicIndividualSegmentsNanite.run(null);

		for (int i = 0; i < individualSegmentsJSONArray.length(); i++) {
			JSONObject individualSegmentJSONObject =
				individualSegmentsJSONArray.getJSONObject(i);

			Assert.assertFalse(
				faroInfoElasticsearchInvoker.exists(
					"memberships",
					BoolQueryBuilderUtil.filter(
						QueryBuilders.termQuery(
							"individualSegmentId",
							individualSegmentJSONObject.getString("id"))
					).filter(
						QueryBuilders.termQuery("status", "ACTIVE")
					)));
		}
	}

	private void _addActivity(String dayDateString) {
		ActivityGroup activityGroup = _activityGroupDog.addActivityGroup(
			FaroInfoTestUtil.buildActivityGroup(
				_dataSource.getId(), DateUtil.toUTCDate(dayDateString),
				_individual));

		faroInfoElasticsearchInvoker.add(
			"activities",
			FaroInfoTestUtil.buildActivityJSONObject(
				_objectMapper.convertValue(activityGroup, JSONObject.class),
				_assetJSONObject, "pageViewed", new String[0]));
	}

	private JSONArray _addMemberships(String... durations) {
		JSONArray membershipsJSONArray = new JSONArray();

		for (String duration : durations) {
			Segment segment = _segmentRepository.save(
				FaroInfoTestUtil.buildDynamicSegment(
					null,
					"(((activities/" + duration + " eq 'Page#pageViewed#" +
						_assetJSONObject.getString("id") + "')))"));

			Individual partialIndividual = new Individual();

			Set<Long> segmentIds = _individual.getSegmentIds();

			segmentIds.add(segment.getId());

			partialIndividual.setSegmentIds(segmentIds);

			_individualDog.updateIndividual(
				_individual.getId(), partialIndividual, false);

			membershipsJSONArray.put(
				faroInfoElasticsearchInvoker.add(
					"memberships",
					_objectMapper.convertValue(
						FaroInfoTestUtil.buildMembership(
							_individual.getId(), segment.getId()),
						JSONObject.class)));
		}

		return membershipsJSONArray;
	}

	private JSONArray _addSessionMemberships(String... durations) {
		JSONArray membershipsJSONArray = new JSONArray();

		for (String duration : durations) {
			Segment segment = _segmentRepository.save(
				FaroInfoTestUtil.buildDynamicSegment(
					1L,
					"(sessions.filter(filter='(context/country eq ''" +
						"United States'' and completeDate gt ''" + duration +
							"'')'))"));

			Individual partialIndividual = new Individual();

			partialIndividual.setSegmentIds(
				Collections.singleton(segment.getId()));

			_individualDog.updateIndividual(
				_individual.getId(), partialIndividual, false);

			membershipsJSONArray.put(
				faroInfoElasticsearchInvoker.add(
					"memberships",
					_objectMapper.convertValue(
						FaroInfoTestUtil.buildMembership(
							_individual.getId(), segment.getId()),
						JSONObject.class)));
		}

		return membershipsJSONArray;
	}

	private void _addUserSession(String dayDateString) {
		cerebroInfoElasticsearchInvoker.add(
			"user-sessions",
			FaroInfoTestUtil.buildUserSessionJSONObject(
				new HashMap<String, String>() {
					{
						put("completeDate", dayDateString);
						put(
							"dataSourceId",
							String.valueOf(_dataSource.getId()));
						put("date", dayDateString);
						put(
							"individualId",
							String.valueOf(_individual.getId()));
					}
				}));
	}

	private String _getIndividualSegmentActivityFilterDuration(
		JSONObject membershipJSONObject) {

		Segment segment = _segmentDog.getSegment(
			Long.valueOf(
				membershipJSONObject.getString("individualSegmentId")));

		Matcher matcher = _individualSegmentActivityFilterPattern.matcher(
			segment.getFilter());

		if (matcher.matches()) {
			return matcher.group(1);
		}

		return null;
	}

	private JSONArray _getSessionsIndividualSegments() {
		return faroInfoElasticsearchInvoker.get(
			"individual-segments",
			QueryBuilders.regexpQuery("filter", ".*sessions.filter.*"));
	}

	private void _mock() {
		Mockito.doAnswer(
			invocation -> {
				_updateDynamicMembershipsNanite.run(
					invocation.getArgument(1, JSONObject.class));

				return null;
			}
		).when(
			_asahTaskDog
		).scheduleAsahTask(
			ArgumentMatchers.any(AsahTask.class)
		);
	}

	private static final Pattern _individualSegmentActivityFilterPattern =
		Pattern.compile(".*activities/([\\w]+) eq.*");

	@Autowired
	private ActivityGroupDog _activityGroupDog;

	@Mock
	private AsahTaskDog _asahTaskDog;

	private JSONObject _assetJSONObject;

	@Autowired
	private AssetRepository _assetRepository;

	private DataSource _dataSource;

	@Autowired
	private FieldRepository _fieldRepository;

	private Individual _individual;

	@Autowired
	private IndividualDog _individualDog;

	@Autowired
	private ObjectMapper _objectMapper;

	@Autowired
	private SegmentDog _segmentDog;

	@Autowired
	private SegmentRepository _segmentRepository;

	@Autowired
	@InjectMocks
	private StaleDynamicIndividualSegmentsNanite
		_staleDynamicIndividualSegmentsNanite;

	@Autowired
	private UpdateDynamicMembershipsNanite _updateDynamicMembershipsNanite;

}