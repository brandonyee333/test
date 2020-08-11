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

import com.liferay.osb.asah.batch.curator.bot.nanite.IndividualSegmentEngagementScoresNanite;
import com.liferay.osb.asah.batch.curator.spring.OSBAsahBatchCuratorSpringBootApplication;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoMembershipDog;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.queue.http.CerebroQueueHttpTestConfiguration;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Edward Kwok-Yu Wong
 * @author Michael Bowerman
 */
@ContextConfiguration(classes = OSBAsahBatchCuratorSpringBootApplication.class)
@Import(CerebroQueueHttpTestConfiguration.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest
public class IndividualSegmentEngagementScoresNaniteTest
	extends BaseNaniteTestCase {

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		JSONObject dataSourceJSONObject = faroInfoElasticsearchInvoker.add(
			"data-sources",
			FaroInfoTestUtil.buildLiferayDataSourceJSONObject());

		JSONObject individualSegmentJSONObject =
			faroInfoElasticsearchInvoker.add(
				"individual-segments",
				FaroInfoTestUtil.buildStaticIndividualSegmentJSONObject());

		_individualSegmentId = individualSegmentJSONObject.getString("id");

		JSONObject individual1JSONObject =
			FaroInfoTestUtil.buildIndividualJSONObject(dataSourceJSONObject);

		individual1JSONObject = faroInfoElasticsearchInvoker.add(
			"individuals", individual1JSONObject.put("engagementScore", 2.5));

		JSONObject individual2JSONObject =
			FaroInfoTestUtil.buildIndividualJSONObject(dataSourceJSONObject);

		individual2JSONObject = faroInfoElasticsearchInvoker.add(
			"individuals", individual2JSONObject.put("engagementScore", 1.5));

		String dayDateString = DateUtil.newDayDateString();

		_faroInfoMembershipDog.addMembership(
			FaroInfoTestUtil.buildMembershipJSONObject(
				individual1JSONObject.getString("id"), _individualSegmentId));
		_faroInfoMembershipDog.addMembership(
			FaroInfoTestUtil.buildMembershipJSONObject(
				individual2JSONObject.getString("id"), _individualSegmentId));

		individual1JSONObject = faroInfoElasticsearchInvoker.get(
			"individuals", individual1JSONObject.getString("id"));
		individual2JSONObject = faroInfoElasticsearchInvoker.get(
			"individuals", individual2JSONObject.getString("id"));

		faroInfoElasticsearchInvoker.add(
			"OSBAsahMarkers",
			JSONUtil.put(
				"id", "IndividualSegmentEngagementScoresNanite"
			).put(
				"lastSuccessfulDay", dayDateString
			));
		faroInfoElasticsearchInvoker.add(
			"engagements",
			FaroInfoTestUtil.buildIndividualEngagementJSONObject(
				dayDateString, individual1JSONObject, 2.5));
		faroInfoElasticsearchInvoker.add(
			"engagements",
			FaroInfoTestUtil.buildIndividualEngagementJSONObject(
				dayDateString, individual2JSONObject, 1.5));
	}

	@Test
	public void test() throws Exception {
		String dayDateString = DateUtil.newDayDateString();

		_individualSegmentEngagementScoresNanite.run(dayDateString);

		_assertIndividualSegmentEngagement(dayDateString, 1.5, 2.5);

		JSONObject individualSegmentsJSONObject =
			faroInfoElasticsearchInvoker.get(
				"individual-segments", _individualSegmentId);

		Assert.assertEquals(
			2, individualSegmentsJSONObject.getLong("activeIndividualCount"));
	}

	private void _assertIndividualSegmentEngagement(
		String dayDateString, double lowerBound, double upperBound) {

		JSONArray engagementsJSONArray = faroInfoElasticsearchInvoker.get(
			"engagements",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("dateRecorded", dayDateString)
			).filter(
				QueryBuilders.termQuery("ownerId", _individualSegmentId)
			).filter(
				QueryBuilders.termQuery("ownerType", "individual-segment")
			));

		Assert.assertEquals(
			"Unexpected number of engagement score entries for individual " +
				"segment " + _individualSegmentId + " and date " +
					dayDateString,
			1, engagementsJSONArray.length());

		JSONObject engagementJSONObject = engagementsJSONArray.getJSONObject(0);

		double score = engagementJSONObject.getDouble("score");

		Assert.assertTrue(
			"Engagement score " + score + " is out of range (" + lowerBound +
				", " + upperBound + ")",
			(score > lowerBound) && (score < upperBound));
	}

	@Autowired
	private FaroInfoMembershipDog _faroInfoMembershipDog;

	@Autowired
	private IndividualSegmentEngagementScoresNanite
		_individualSegmentEngagementScoresNanite;

	private String _individualSegmentId;

}