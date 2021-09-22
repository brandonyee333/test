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
import com.liferay.osb.asah.common.dog.MembershipDog;
import com.liferay.osb.asah.common.dog.SegmentDog;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.entity.ActivityGroup;
import com.liferay.osb.asah.common.entity.AsahTask;
import com.liferay.osb.asah.common.entity.Asset;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.entity.Individual;
import com.liferay.osb.asah.common.entity.Membership;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.repository.AssetRepository;
import com.liferay.osb.asah.common.repository.FieldRepository;
import com.liferay.osb.asah.common.repository.MembershipRepository;
import com.liferay.osb.asah.common.repository.SegmentRepository;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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
		List<Long> membershipIds = _addMemberships(
			"last30Days", "last7Days", "lastYear", "today");

		_staleDynamicIndividualSegmentsNanite.run(null);

		for (Long membershipId : membershipIds) {
			Optional<Membership> membershipOptional =
				_membershipRepository.findById(membershipId);

			Membership membership = membershipOptional.get();

			String individualSegmentActivityFilterDuration =
				_getIndividualSegmentActivityFilterDuration(membership);

			Assert.assertEquals(
				"Membership should be deactivated if no activities were " +
					"found for an individual within " +
						individualSegmentActivityFilterDuration,
				"INACTIVE", membership.getStatus());

			List<Long> individualIds = _individualDog.getIdsBySegmentId(
				membership.getIndividualSegmentId());

			Assert.assertFalse(
				"Individual segment ID should be removed from individual " +
					"when membership is deactivated",
				individualIds.contains(membership.getIndividualId()));
		}
	}

	@Test
	public void testMembershipsOfAllTimeDynamicIndividualSegmentsRemainActive()
		throws Exception {

		List<Long> membershipIds = _addMemberships("ever");

		_staleDynamicIndividualSegmentsNanite.run(null);

		for (Long membershipId : membershipIds) {
			Optional<Membership> membershipOptional =
				_membershipRepository.findById(membershipId);

			Membership membership = membershipOptional.get();

			Assert.assertTrue(
				"Membership should remain active if activities were found",
				Objects.equals(membership.getStatus(), "ACTIVE"));
		}
	}

	@Test
	public void testMembershipsOfStaleDynamicIndividualSegmentsAreDeactivated()
		throws Exception {

		_addActivity(DateUtil.addDays(DateUtil.newDayDateString(), -3));

		List<Long> membershipIds = _addMemberships("last7Days", "today");

		_staleDynamicIndividualSegmentsNanite.run(null);

		for (Long membershipId : membershipIds) {
			Optional<Membership> membershipOptional =
				_membershipRepository.findById(membershipId);

			Membership membership = membershipOptional.get();

			String individualSegmentActivityFilterDuration =
				_getIndividualSegmentActivityFilterDuration(membership);

			if (Objects.equals(membership.getStatus(), "ACTIVE")) {
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

		List<Long> membershipIds = _addMemberships(
			"last30Days", "last7Days", "lastYear", "today");

		_staleDynamicIndividualSegmentsNanite.run(null);

		for (Long membershipId : membershipIds) {
			Optional<Membership> membershipOptional =
				_membershipRepository.findById(membershipId);

			Membership membership = membershipOptional.get();

			String individualSegmentActivityFilterDuration =
				_getIndividualSegmentActivityFilterDuration(membership);

			Assert.assertTrue(
				"Membership should remain active if activities were found " +
					"within " + individualSegmentActivityFilterDuration,
				Objects.equals(membership.getStatus(), "ACTIVE"));
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

	private List<Long> _addMemberships(String... durations) {
		List<Long> membershipIds = new ArrayList<>();

		for (String duration : durations) {
			Segment segment = _segmentRepository.save(
				FaroInfoTestUtil.buildDynamicSegment(
					null,
					"(((activities/" + duration + " eq 'Page#pageViewed#" +
						_assetJSONObject.getString("id") + "')))"));

			_individualDog.addSegmentId(_individual.getId(), segment.getId());

			Membership membership = _membershipDog.addMembership(
				FaroInfoTestUtil.buildMembership(
					_individual.getId(), segment.getId()));

			membershipIds.add(membership.getId());
		}

		return membershipIds;
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

			_individualDog.addSegmentId(_individual.getId(), segment.getId());

			membershipsJSONArray.put(
				_objectMapper.convertValue(
					_membershipDog.addMembership(
						FaroInfoTestUtil.buildMembership(
							_individual.getId(), segment.getId())),
					JSONObject.class));
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
		Membership membership) {

		Segment segment = _segmentDog.getSegment(
			membership.getIndividualSegmentId());

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
	private MembershipDog _membershipDog;

	@Autowired
	private MembershipRepository _membershipRepository;

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