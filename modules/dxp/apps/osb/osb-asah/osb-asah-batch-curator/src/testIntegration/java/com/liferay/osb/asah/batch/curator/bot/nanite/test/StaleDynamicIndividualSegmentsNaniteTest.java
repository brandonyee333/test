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
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.AsahMarkerDog;
import com.liferay.osb.asah.common.dog.AsahTaskDog;
import com.liferay.osb.asah.common.dog.BQMembershipDog;
import com.liferay.osb.asah.common.dog.ChannelDog;
import com.liferay.osb.asah.common.dog.IndividualDog;
import com.liferay.osb.asah.common.dog.SegmentDog;
import com.liferay.osb.asah.common.entity.AsahTask;
import com.liferay.osb.asah.common.entity.Asset;
import com.liferay.osb.asah.common.entity.BQMembership;
import com.liferay.osb.asah.common.entity.Channel;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.entity.Individual;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.repository.AssetRepository;
import com.liferay.osb.asah.common.repository.BQMembershipRepository;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.common.repository.SegmentRepository;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.spring.OSBAsahElasticsearchTestExecutionListener;
import com.liferay.osb.asah.test.util.spring.OSBAsahRepositoryTestExecutionListener;
import com.liferay.osb.asah.test.util.spring.OSBAsahSQLTestExecutionListener;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.RandomStringUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestExecutionListeners;

/**
 * @author Leslie Wong
 */
@Disabled
@TestExecutionListeners(
	mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS,
	value = {
		OSBAsahElasticsearchTestExecutionListener.class,
		OSBAsahRepositoryTestExecutionListener.class,
		OSBAsahSQLTestExecutionListener.class
	}
)
public class StaleDynamicIndividualSegmentsNaniteTest
	extends BaseNaniteTestCase {

	@BeforeEach
	public void setUp() {
		_mock();

		_dataSource = FaroInfoTestUtil.buildLiferayDataSource();

		_dataSource.setId(Long.parseLong(RandomTestUtil.randomId()));

		_dataSource = _dataSourceRepository.save(_dataSource);

		_assetJSONObject = _objectMapper.convertValue(
			_assetRepository.save(
				_objectMapper.convertValue(
					FaroInfoTestUtil.buildPageAssetJSONObject(
						_dataSource.getId()),
					Asset.class)),
			JSONObject.class);

		// TODO Add BQFieldMapping "email", "Text"

		Individual individual = FaroInfoTestUtil.buildIndividual(_dataSource);

		_individual = _individualDog.addIndividual(individual, false);
	}

	@AfterEach
	public void tearDown() {
		_asahMarkerDog.deleteAsahMarker("StaleDynamicIndividualSegmentsNanite");
	}

	@Test
	public void testMembershipsAreDeactivatedIfNoActivities() throws Exception {
		List<Long> bqMembershipIds = _addBQMemberships(
			"last30Days", "last7Days", "lastYear", "today");

		_staleDynamicIndividualSegmentsNanite.run(null);

		for (Long bqMembershipId : bqMembershipIds) {
			Optional<BQMembership> bqMembershipOptional =
				_bqMembershipRepository.findById(bqMembershipId);

			BQMembership bqMembership = bqMembershipOptional.get();

			String individualSegmentActivityFilterDuration =
				_getIndividualSegmentActivityFilterDuration(bqMembership);

			Assertions.assertEquals(
				"INACTIVE", bqMembership.getStatus(),
				"Membership should be deactivated if no activities were " +
					"found for an individual within " +
						individualSegmentActivityFilterDuration);

			List<Long> identityIds = _individualDog.getIdsBySegmentId(
				bqMembership.getSegmentId());

			Assertions.assertFalse(
				identityIds.contains(
					Long.parseLong(bqMembership.getIdentityId())),
				"Individual segment ID should be removed from individual " +
					"when membership is deactivated");
		}
	}

	@Test
	public void testMembershipsOfAllTimeDynamicIndividualSegmentsRemainActive()
		throws Exception {

		List<Long> bqMembershipIds = _addBQMemberships("ever");

		_staleDynamicIndividualSegmentsNanite.run(null);

		for (Long bqMembershipId : bqMembershipIds) {
			Optional<BQMembership> bqMembershipOptional =
				_bqMembershipRepository.findById(bqMembershipId);

			BQMembership bqMembership = bqMembershipOptional.get();

			Assertions.assertTrue(
				Objects.equals(bqMembership.getStatus(), "ACTIVE"),
				"Membership should remain active if activities were found");
		}
	}

	@Test
	public void testMembershipsOfStaleDynamicIndividualSegmentsAreDeactivated()
		throws Exception {

		_addActivity(DateUtil.addDays(DateUtil.newDayDateString(), -3));

		List<Long> bqMembershipIds = _addBQMemberships("last7Days", "today");

		_staleDynamicIndividualSegmentsNanite.run(null);

		for (Long bqMembershipId : bqMembershipIds) {
			Optional<BQMembership> bqMembershipOptional =
				_bqMembershipRepository.findById(bqMembershipId);

			BQMembership bqMembership = bqMembershipOptional.get();

			String individualSegmentActivityFilterDuration =
				_getIndividualSegmentActivityFilterDuration(bqMembership);

			if (Objects.equals(bqMembership.getStatus(), "ACTIVE")) {
				Assertions.assertEquals(
					"last7Days", individualSegmentActivityFilterDuration,
					"Membership should remain active if activities were " +
						"found within the last 7 days");
			}
			else {
				Assertions.assertEquals(
					"today", individualSegmentActivityFilterDuration,
					"Membership should be deactivated if no activities were " +
						"found within today");
			}
		}
	}

	@Test
	public void testMembershipsRemainActive() throws Exception {
		_addActivity(DateUtil.newDayDateString());

		List<Long> bqMembershipIds = _addBQMemberships(
			"last30Days", "last7Days", "lastYear", "today");

		_staleDynamicIndividualSegmentsNanite.run(null);

		for (Long bqMembershipId : bqMembershipIds) {
			Optional<BQMembership> bqMembershipOptional =
				_bqMembershipRepository.findById(bqMembershipId);

			BQMembership bqMembership = bqMembershipOptional.get();

			String individualSegmentActivityFilterDuration =
				_getIndividualSegmentActivityFilterDuration(bqMembership);

			Assertions.assertTrue(
				Objects.equals(bqMembership.getStatus(), "ACTIVE"),
				"Membership should remain active if activities were found " +
					"within " + individualSegmentActivityFilterDuration);
		}
	}

	@Test
	public void testStaleSessionCriteriaMembershipsAreDeactivated()
		throws Exception {

		// TODO Provide date day

		_addUserSession();

		_addSessionMemberships(
			"last24Hours", "last28Days", "last30Days", "last7Days",
			"last90Days", "yesterday");

		List<Long> segmentIds = _segmentRepository.findIdByFilterLike(
			"%sessions.filter%");

		Assertions.assertEquals(6, segmentIds.size());

		for (Long segmentId : segmentIds) {
			Assertions.assertEquals(
				1,
				_bqMembershipRepository.countBySegmentIdAndStatus(
					segmentId, "ACTIVE"));
		}

		_staleDynamicIndividualSegmentsNanite.run(null);

		for (Long segmentId : segmentIds) {
			Assertions.assertEquals(
				0,
				_bqMembershipRepository.countBySegmentIdAndStatus(
					segmentId, "ACTIVE"));
		}
	}

	private void _addActivity(String dayDateString) {
		faroInfoElasticsearchInvoker.add(
			"activities",
			FaroInfoTestUtil.buildActivityJSONObject(
				_assetJSONObject,
				Long.parseLong(RandomStringUtils.randomNumeric(4)),
				_dataSource.getId(), dayDateString, "pageViewed", new String[0],
				_individual));
	}

	private List<Long> _addBQMemberships(String... durations) {
		List<Long> bqMembershipIds = new ArrayList<>();

		for (String duration : durations) {
			Segment segment = _segmentRepository.save(
				FaroInfoTestUtil.buildDynamicSegment(
					null,
					"(((activities/" + duration + " eq 'Page#pageViewed#" +
						_assetJSONObject.getString("id") + "')))"));

			_individualDog.addSegmentId(_individual.getId(), segment.getId());

			BQMembership bqMembership = _bqMembershipDog.addBQMembership(
				FaroInfoTestUtil.buildBQMembership(
					String.valueOf(_individual.getId()), segment.getId()));

			bqMembershipIds.add(bqMembership.getId());
		}

		return bqMembershipIds;
	}

	private JSONArray _addSessionMemberships(String... durations) {
		JSONArray membershipsJSONArray = new JSONArray();
		Channel channel = _channelDog.addChannel("Liferay");

		for (String duration : durations) {
			Segment segment = _segmentRepository.save(
				FaroInfoTestUtil.buildDynamicSegment(
					channel.getId(),
					"(sessions.filter(filter='(context/country eq ''" +
						"United States'' and completeDate gt ''" + duration +
							"'')'))"));

			_individualDog.addSegmentId(_individual.getId(), segment.getId());

			membershipsJSONArray.put(
				_objectMapper.convertValue(
					_bqMembershipDog.addBQMembership(
						FaroInfoTestUtil.buildBQMembership(
							String.valueOf(_individual.getId()),
							segment.getId())),
					JSONObject.class));
		}

		return membershipsJSONArray;
	}

	private void _addUserSession() {

		// TODD Add user session;

	}

	private String _getIndividualSegmentActivityFilterDuration(
		BQMembership bqMembership) {

		Segment segment = _segmentDog.getSegment(bqMembership.getSegmentId());

		Matcher matcher = _individualSegmentActivityFilterPattern.matcher(
			segment.getFilter());

		if (matcher.matches()) {
			return matcher.group(1);
		}

		return null;
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
	private AsahMarkerDog _asahMarkerDog;

	@Mock
	private AsahTaskDog _asahTaskDog;

	private JSONObject _assetJSONObject;

	@Autowired
	private AssetRepository _assetRepository;

	@Autowired
	private BQMembershipDog _bqMembershipDog;

	@Autowired
	private BQMembershipRepository _bqMembershipRepository;

	@Autowired
	private ChannelDog _channelDog;

	private DataSource _dataSource;

	@Autowired
	private DataSourceRepository _dataSourceRepository;

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