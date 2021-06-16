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

package com.liferay.osb.asah.stream.curator.bot.nanite.activity.test;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoActivityDog;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoIndividualDog;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.messaging.Channel;
import com.liferay.osb.asah.common.messaging.MessageBus;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.stream.curator.bot.nanite.activity.IndividualActivityFieldsNanite;
import com.liferay.osb.asah.stream.curator.spring.OSBAsahCuratorSpringBootApplication;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import org.apache.commons.lang3.RandomUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Michael Bowerman
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OSBAsahCuratorSpringBootApplication.class)
public class IndividualActivityFieldsNaniteTest {

	@Before
	public void setUp() throws Exception {
		_dataSourceJSONObject = _faroInfoElasticsearchInvoker.add(
			"data-sources",
			FaroInfoTestUtil.buildLiferayDataSourceJSONObject());
	}

	@Test
	public void testBlogClickedActivitiesExcluded() throws Exception {
		_testSingleTypeOfActivityExcluded("Blog", "blogClicked");
	}

	@Test
	public void testBlogDepthReachedActivitiesExcluded() throws Exception {
		_testSingleTypeOfActivityExcluded("Blog", "blogDepthReached");
	}

	@Test
	public void testBlogViewedActivitiesExcluded() throws Exception {
		_testSingleTypeOfActivityExcluded("Blog", "blogViewed");
	}

	@Test
	public void testDocumentDownloadedActivitiesIncluded() throws Exception {
		_testSingleTypeOfActivityIncluded("Document", "documentDownloaded");
	}

	@Test
	public void testDocumentPreviewedActivitiesIncluded() throws Exception {
		_testSingleTypeOfActivityIncluded("Document", "documentPreviewed");
	}

	@Test
	public void testFieldBlurredActivitiesExcluded() throws Exception {
		_testSingleTypeOfActivityExcluded("Form", "fieldBlurred");
	}

	@Test
	public void testFieldFocusedActivitiesExcluded() throws Exception {
		_testSingleTypeOfActivityExcluded("Form", "fieldFocused");
	}

	@Test
	public void testFormSubmittedActivitiesIncluded() throws Exception {
		_testSingleTypeOfActivityIncluded("Form", "formSubmitted");
	}

	@Test
	public void testFormViewedActivitiesIncluded() throws Exception {
		_testSingleTypeOfActivityIncluded("Form", "formViewed");
	}

	@Test
	public void testMixtureOfVariousActivities() throws Exception {
		String channelId = RandomTestUtil.randomId();
		String dateString = DateUtil.addDays(DateUtil.newDateString(), -1);
		JSONObject individualJSONObject = _faroInfoIndividualDog.addIndividual(
			FaroInfoTestUtil.buildIndividualJSONObject(
				channelId, _dataSourceJSONObject),
			false);

		_addActivities(
			1, "Form", channelId, dateString, "fieldBlurred",
			individualJSONObject);
		_addActivities(
			2, "Form", channelId, DateUtil.addDays(dateString, -8),
			"formSubmitted", individualJSONObject);
		_addActivities(
			4, "Page", channelId, DateUtil.addDays(dateString, -16),
			"pageLoaded", individualJSONObject);
		_addActivities(
			8, "Page", channelId, DateUtil.addDays(dateString, -24),
			"pageViewed", individualJSONObject);
		_addActivities(
			16, "Blog", channelId, DateUtil.addDays(dateString, -32),
			"blogViewed", individualJSONObject);
		_addActivities(
			32, "Document", channelId, DateUtil.addDays(dateString, -40),
			"documentPreviewed", individualJSONObject);

		_individualActivityFieldsNanite.run();

		individualJSONObject = _faroInfoElasticsearchInvoker.get(
			"individuals", individualJSONObject.getString("id"));

		JSONObject activitiesCountJSONObject = JSONUtil.find(
			individualJSONObject.getJSONArray("activitiesCounts"), "channelId",
			channelId);

		Assert.assertNotNull(activitiesCountJSONObject);
		Assert.assertEquals(
			42, activitiesCountJSONObject.getInt("activitiesCount"));

		JSONObject lastActivityDateJSONObject = JSONUtil.find(
			individualJSONObject.getJSONArray("lastActivityDates"), "channelId",
			channelId);

		Assert.assertNotNull(lastActivityDateJSONObject);
		Assert.assertEquals(
			DateUtil.addDays(dateString, -8),
			lastActivityDateJSONObject.getString("lastActivityDate"));
	}

	@Test
	public void testPageDepthReachedActivitiesExcluded() throws Exception {
		_testSingleTypeOfActivityExcluded("Page", "pageDepthReached");
	}

	@Test
	public void testPageLoadedActivitiesExcluded() throws Exception {
		_testSingleTypeOfActivityExcluded("Page", "pageLoaded");
	}

	@Test
	public void testPageUnloadedActivitiesExcluded() throws Exception {
		_testSingleTypeOfActivityExcluded("Page", "pageUnloaded");
	}

	@Test
	public void testPageViewedActivitiesIncluded() throws Exception {
		_testSingleTypeOfActivityIncluded("Page", "pageViewed");
	}

	@Test
	public void testWebContentClickedActivitiesExcluded() throws Exception {
		_testSingleTypeOfActivityExcluded("WebContent", "webContentClicked");
	}

	@Test
	public void testWebContentViewedActivitiesExcluded() throws Exception {
		_testSingleTypeOfActivityExcluded("WebContent", "webContentViewed");
	}

	private void _addActivities(
			int activitiesCount, String applicationId, String channelId,
			String dateString, String eventId, JSONObject individualJSONObject)
		throws Exception {

		String dataSourceId = _dataSourceJSONObject.getString("id");

		JSONObject activityGroupJSONObject = _faroInfoElasticsearchInvoker.add(
			"activity-groups",
			FaroInfoTestUtil.buildActivityGroupJSONObject(
				dataSourceId, dateString, individualJSONObject));

		JSONObject assetJSONObject = _faroInfoElasticsearchInvoker.add(
			"assets",
			FaroInfoTestUtil.buildAssetJSONObject(applicationId, dataSourceId));

		for (int i = 0; i < activitiesCount; i++) {
			_faroInfoActivityDog.addActivity(
				FaroInfoTestUtil.buildActivityJSONObject(
					activityGroupJSONObject, assetJSONObject, channelId,
					eventId, new String[0]));

			if (_faroInfoActivityDog.isActivity(applicationId, eventId)) {
				_messageBus.sendMessage(
					Channel.ACTIVE_INDIVIDUAL_IDS,
					JSONUtil.put(
						"channelId", channelId
					).put(
						"ownerId", individualJSONObject.get("id")
					).put(
						"projectId", ProjectIdThreadLocal.getProjectId()
					).toString());
			}
		}
	}

	private JSONObject _addIndividualWithActivities(
			int activitiesCount, String applicationId, String channelId,
			String dateString, String eventId)
		throws Exception {

		JSONObject individualJSONObject = _faroInfoIndividualDog.addIndividual(
			FaroInfoTestUtil.buildIndividualJSONObject(
				channelId, _dataSourceJSONObject),
			false);

		_addActivities(
			activitiesCount, applicationId, channelId, dateString, eventId,
			individualJSONObject);

		_individualActivityFieldsNanite.run();

		return _faroInfoElasticsearchInvoker.get(
			"individuals", individualJSONObject.getString("id"));
	}

	private void _testSingleTypeOfActivityExcluded(
			String applicationId, String eventId)
		throws Exception {

		JSONObject individualJSONObject = _addIndividualWithActivities(
			RandomUtils.nextInt(5, 25), applicationId,
			RandomTestUtil.randomId(), DateUtil.newDateString(), eventId);

		JSONArray activitiesCountsJSONArray = individualJSONObject.getJSONArray(
			"activitiesCounts");

		Assert.assertNotNull(activitiesCountsJSONArray);
		Assert.assertEquals(0, activitiesCountsJSONArray.length());
	}

	private void _testSingleTypeOfActivityIncluded(
			String applicationId, String eventId)
		throws Exception {

		int activitiesCount = RandomUtils.nextInt(5, 25);
		String channelId = RandomTestUtil.randomId();
		String dateString = DateUtil.newDateString();

		JSONObject individualJSONObject = _addIndividualWithActivities(
			activitiesCount, applicationId, channelId, dateString, eventId);

		JSONObject activitiesCountJSONObject = JSONUtil.find(
			individualJSONObject.getJSONArray("activitiesCounts"), "channelId",
			channelId);

		Assert.assertNotNull(activitiesCountJSONObject);
		Assert.assertEquals(
			activitiesCount,
			activitiesCountJSONObject.getInt("activitiesCount"));

		JSONObject lastActivityDateJSONObject = JSONUtil.find(
			individualJSONObject.getJSONArray("lastActivityDates"), "channelId",
			channelId);

		Assert.assertNotNull(lastActivityDateJSONObject);
		Assert.assertEquals(
			dateString,
			lastActivityDateJSONObject.getString("lastActivityDate"));
	}

	private JSONObject _dataSourceJSONObject;

	@Autowired
	private FaroInfoActivityDog _faroInfoActivityDog;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@Autowired
	private FaroInfoIndividualDog _faroInfoIndividualDog;

	@Autowired
	private IndividualActivityFieldsNanite _individualActivityFieldsNanite;

	@Autowired
	private MessageBus _messageBus;

}