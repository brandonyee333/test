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

import com.liferay.osb.asah.batch.curator.bot.nanite.StaleDynamicIndividualSegmentsNanite;
import com.liferay.osb.asah.batch.curator.bot.nanite.UpdateDynamicMembershipsNanite;
import com.liferay.osb.asah.batch.curator.spring.OSBAsahBatchCuratorSpringBootApplication;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoIndividualDog;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoIndividualSegmentDog;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoOSBAsahTaskDog;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.queue.http.CerebroQueueHttpTestConfiguration;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

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

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Leslie Wong
 */
@ContextConfiguration(classes = OSBAsahBatchCuratorSpringBootApplication.class)
@Import(CerebroQueueHttpTestConfiguration.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest
public class StaleDynamicIndividualSegmentsNaniteTest
	extends BaseNaniteTestCase {

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		_mock();

		_dataSourceJSONObject =
			FaroInfoTestUtil.buildLiferayDataSourceJSONObject();

		_dataSourceJSONObject.put("id", RandomTestUtil.randomId());

		_assetJSONObject = faroInfoElasticsearchInvoker.add(
			"assets",
			FaroInfoTestUtil.buildPageAssetJSONObject(
				_dataSourceJSONObject.getString("id")));

		_individualJSONObject = _faroInfoIndividualDog.addIndividual(
			FaroInfoTestUtil.buildIndividualJSONObject(
				"1", _dataSourceJSONObject),
			false);
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

			Assert.assertEquals(
				"Membership should be deactivated if no activities were " +
					"found for an individual within " +
						_getIndividualSegmentActivityFilterDuration(
							membershipJSONObject),
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

			Assert.assertTrue(
				"Membership should remain active if activities were found " +
					"within " +
						_getIndividualSegmentActivityFilterDuration(
							membershipJSONObject),
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

	private void _addActivity(String dayDateString) throws Exception {
		JSONObject activityGroupJSONObject = faroInfoElasticsearchInvoker.add(
			"activity-groups",
			FaroInfoTestUtil.buildActivityGroupJSONObject(
				_dataSourceJSONObject.getString("id"), dayDateString,
				_individualJSONObject));

		faroInfoElasticsearchInvoker.add(
			"activities",
			FaroInfoTestUtil.buildActivityJSONObject(
				activityGroupJSONObject, _assetJSONObject, "pageViewed",
				new String[] {"pageLoadTime", "1000"}));
	}

	private JSONArray _addMemberships(String... durations) throws Exception {
		JSONArray membershipsJSONArray = new JSONArray();

		for (String duration : durations) {
			JSONObject individualSegmentJSONObject =
				_faroInfoIndividualSegmentDog.addIndividualSegment(
					FaroInfoTestUtil.buildDynamicIndividualSegmentJSONObject(
						"1",
						"(((activities/" + duration + " eq 'Page#pageViewed#" +
							_assetJSONObject.getString("id") + "')))"));

			_faroInfoIndividualDog.updateIndividual(
				_individualJSONObject.getString("id"),
				JSONUtil.put(
					"individualSegmentIds",
					JSONUtil.put(individualSegmentJSONObject.getString("id"))),
				false);

			membershipsJSONArray.put(
				faroInfoElasticsearchInvoker.add(
					"memberships",
					FaroInfoTestUtil.buildMembershipJSONObject(
						_individualJSONObject.getString("id"),
						individualSegmentJSONObject.getString("id"))));
		}

		return membershipsJSONArray;
	}

	private String _getIndividualSegmentActivityFilterDuration(
		JSONObject membershipJSONObject) {

		JSONObject individualSegmentJSONObject =
			faroInfoElasticsearchInvoker.fetch(
				"individual-segments",
				membershipJSONObject.getString("individualSegmentId"));

		Matcher matcher = _individualSegmentActivityFilterPattern.matcher(
			individualSegmentJSONObject.getString("filter"));

		if (matcher.matches()) {
			return matcher.group(1);
		}

		return null;
	}

	private void _mock() {
		Mockito.doAnswer(
			invocation -> {
				_updateDynamicMembershipsNanite.run(
					invocation.getArgumentAt(1, JSONObject.class));

				return null;
			}
		).when(
			_faroInfoOSBAsahTaskDog
		).addOSBAsahTask(
			Mockito.eq("UpdateDynamicMembershipsNanite"),
			Mockito.any(JSONObject.class)
		);
	}

	private static final Pattern _individualSegmentActivityFilterPattern =
		Pattern.compile(".*activities/([\\w]+) eq.*");

	private JSONObject _assetJSONObject;
	private JSONObject _dataSourceJSONObject;

	@Autowired
	private FaroInfoIndividualDog _faroInfoIndividualDog;

	@Autowired
	private FaroInfoIndividualSegmentDog _faroInfoIndividualSegmentDog;

	@Mock
	private FaroInfoOSBAsahTaskDog _faroInfoOSBAsahTaskDog;

	private JSONObject _individualJSONObject;

	@Autowired
	@InjectMocks
	private StaleDynamicIndividualSegmentsNanite
		_staleDynamicIndividualSegmentsNanite;

	@Autowired
	private UpdateDynamicMembershipsNanite _updateDynamicMembershipsNanite;

}