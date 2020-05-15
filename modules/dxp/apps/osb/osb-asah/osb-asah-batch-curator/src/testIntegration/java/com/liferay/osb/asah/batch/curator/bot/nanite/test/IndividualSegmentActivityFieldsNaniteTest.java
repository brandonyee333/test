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

import com.liferay.osb.asah.batch.curator.bot.nanite.IndividualSegmentActivityFieldsNanite;
import com.liferay.osb.asah.batch.curator.spring.OSBAsahBatchCuratorSpringBootApplication;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoIndividualSegmentDog;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoMembershipDog;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.queue.http.CerebroQueueHttpTestConfiguration;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import org.json.JSONObject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Michael Bowerman
 */
@ContextConfiguration(classes = OSBAsahBatchCuratorSpringBootApplication.class)
@Import(CerebroQueueHttpTestConfiguration.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class IndividualSegmentActivityFieldsNaniteTest
	extends BaseActivityFieldsNaniteTestCase {

	@Test
	public void test() throws Exception {
		JSONObject individualSegmentJSONObject =
			FaroInfoTestUtil.buildStaticIndividualSegmentJSONObject();

		individualSegmentJSONObject =
			_faroInfoIndividualSegmentDog.addIndividualSegment(
				individualSegmentJSONObject.put("channelId", "1"));

		String individualSegmentId = individualSegmentJSONObject.getString(
			"id");

		String dateString = DateUtil.addDays(DateUtil.newDateString(), -15);

		JSONObject individual1JSONObject = addIndividualWithActivities(
			2, "Page", "1", DateUtil.addDays(dateString, -5), "pageViewed");
		JSONObject individual2JSONObject = addIndividualWithActivities(
			4, "Form", "1", dateString, "formSubmitted");
		JSONObject individual3JSONObject = addIndividualWithActivities(
			8, "Form", "1", DateUtil.addDays(dateString, 5), "fieldBlurred");
		addIndividualWithActivities(
			16, "Page", "1", DateUtil.addDays(dateString, 10), "pageViewed");

		_faroInfoMembershipDog.addMembership(
			FaroInfoTestUtil.buildMembershipJSONObject(
				individual1JSONObject.getString("id"), individualSegmentId));
		_faroInfoMembershipDog.addMembership(
			FaroInfoTestUtil.buildMembershipJSONObject(
				individual2JSONObject.getString("id"), individualSegmentId));
		_faroInfoMembershipDog.addMembership(
			FaroInfoTestUtil.buildMembershipJSONObject(
				individual3JSONObject.getString("id"), individualSegmentId));

		addIndividualWithActivities(
			27, "Page", "1", DateUtil.addDays(dateString, 5), "pageViewed");

		individualActivityFieldsNanite.run();

		_individualSegmentActivityFieldsNanite.run();

		individualSegmentJSONObject = faroInfoElasticsearchInvoker.get(
			"individual-segments", individualSegmentId);

		Assert.assertEquals(
			6, individualSegmentJSONObject.getInt("activitiesCount"));
		Assert.assertEquals(
			dateString,
			individualSegmentJSONObject.getString("lastActivityDate"));

		_removeMembershipAndAssertActivityFields(
			6, dateString, individual3JSONObject, individualSegmentId);

		_removeMembershipAndAssertActivityFields(
			2, DateUtil.addDays(dateString, -5), individual2JSONObject,
			individualSegmentId);

		_removeMembershipAndAssertActivityFields(
			0, null, individual1JSONObject, individualSegmentId);
	}

	private void _removeMembershipAndAssertActivityFields(
			int expectedActivitiesCount, String expectedLastActivityDate,
			JSONObject individualJSONObject, String individualSegmentId)
		throws Exception {

		_faroInfoMembershipDog.deactivateMembership(
			DateUtil.newDateString(), individualJSONObject.getString("id"),
			individualSegmentId);

		_individualSegmentActivityFieldsNanite.run();

		JSONObject individualSegmentJSONObject =
			faroInfoElasticsearchInvoker.get(
				"individual-segments", individualSegmentId);

		Assert.assertEquals(
			expectedActivitiesCount,
			individualSegmentJSONObject.getInt("activitiesCount"));
		Assert.assertEquals(
			expectedLastActivityDate,
			individualSegmentJSONObject.optString("lastActivityDate", null));
	}

	@Autowired
	private FaroInfoIndividualSegmentDog _faroInfoIndividualSegmentDog;

	@Autowired
	private FaroInfoMembershipDog _faroInfoMembershipDog;

	@Autowired
	private IndividualSegmentActivityFieldsNanite
		_individualSegmentActivityFieldsNanite;

}