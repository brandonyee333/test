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

import com.liferay.osb.asah.batch.curator.spring.OSBAsahBatchCuratorSpringBootApplication;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.queue.http.CerebroQueueHttpTestConfiguration;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import org.apache.commons.lang3.RandomUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Michael Bowerman
 */
@ContextConfiguration(classes = OSBAsahBatchCuratorSpringBootApplication.class)
@Import(CerebroQueueHttpTestConfiguration.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class IndividualActivityFieldsNaniteTest
	extends BaseActivityFieldsNaniteTestCase {

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
		JSONObject individualJSONObject = faroInfoIndividualDog.addIndividual(
			FaroInfoTestUtil.buildIndividualJSONObject(
				channelId, dataSourceJSONObject),
			false);

		addActivities(
			1, "Form", channelId, dateString, "fieldBlurred",
			individualJSONObject);
		addActivities(
			2, "Form", channelId, DateUtil.addDays(dateString, -8),
			"formSubmitted", individualJSONObject);
		addActivities(
			4, "Page", channelId, DateUtil.addDays(dateString, -16),
			"pageLoaded", individualJSONObject);
		addActivities(
			8, "Page", channelId, DateUtil.addDays(dateString, -24),
			"pageViewed", individualJSONObject);
		addActivities(
			16, "Blog", channelId, DateUtil.addDays(dateString, -32),
			"blogViewed", individualJSONObject);
		addActivities(
			32, "Document", channelId, DateUtil.addDays(dateString, -40),
			"documentPreviewed", individualJSONObject);

		individualActivityFieldsNanite.run();

		individualJSONObject = faroInfoElasticsearchInvoker.get(
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

	private void _testSingleTypeOfActivityExcluded(
			String applicationId, String eventId)
		throws Exception {

		JSONObject individualJSONObject = addIndividualWithActivities(
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

		JSONObject individualJSONObject = addIndividualWithActivities(
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

}