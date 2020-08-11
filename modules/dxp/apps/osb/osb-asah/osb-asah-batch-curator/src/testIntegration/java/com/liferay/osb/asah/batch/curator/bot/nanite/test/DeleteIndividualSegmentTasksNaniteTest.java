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

import com.liferay.osb.asah.batch.curator.bot.nanite.DeleteIndividualSegmentTasksNanite;
import com.liferay.osb.asah.batch.curator.spring.OSBAsahBatchCuratorSpringBootApplication;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.queue.http.CerebroQueueHttpTestConfiguration;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import org.apache.commons.lang3.RandomUtils;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

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
public class DeleteIndividualSegmentTasksNaniteTest extends BaseNaniteTestCase {

	@Test
	public void testDeleteIndividualSegmentTasks() throws Exception {
		JSONObject dataSourceJSONObject = faroInfoElasticsearchInvoker.add(
			"data-sources",
			FaroInfoTestUtil.buildLiferayDataSourceJSONObject());

		String dayDateString = DateUtil.newDayDateString();
		String individualSegmentId = RandomTestUtil.randomId();

		faroInfoElasticsearchInvoker.add(
			"engagements",
			FaroInfoTestUtil.buildIndividualSegmentEngagementJSONObject(
				dayDateString, individualSegmentId,
				RandomUtils.nextDouble(0, 1)));

		JSONObject assetJSONObject = FaroInfoTestUtil.buildPageAssetJSONObject(
			dataSourceJSONObject.getString("id"));

		JSONObject individualJSONObject =
			FaroInfoTestUtil.buildIndividualJSONObject(dataSourceJSONObject);

		individualJSONObject.put(
			"individualSegmentIds", JSONUtil.put(individualSegmentId));

		individualJSONObject = faroInfoElasticsearchInvoker.add(
			"individuals", individualJSONObject);

		faroInfoElasticsearchInvoker.add(
			"membership-changes",
			FaroInfoTestUtil.buildMembershipChangeJSONObject(
				false, individualJSONObject, individualSegmentId, "ADDED"));

		faroInfoElasticsearchInvoker.add(
			"memberships",
			FaroInfoTestUtil.buildMembershipJSONObject(
				individualJSONObject.getString("id"), individualSegmentId));

		faroInfoElasticsearchInvoker.add(
			"visited-pages",
			FaroInfoTestUtil.buildIndividualSegmentVisitedPagesJSONArray(
				assetJSONObject, dayDateString, individualSegmentId,
				RandomUtils.nextInt()));

		_deleteIndividualSegmentTasksNanite.run(
			JSONUtil.put("individualSegmentId", individualSegmentId));

		for (String collectionName :
				new String[] {"engagements", "visited-pages"}) {

			Assert.assertFalse(
				"Entries within " + collectionName + " related to the " +
					"deleted individual segment should be deleted",
				faroInfoElasticsearchInvoker.exists(
					collectionName,
					BoolQueryBuilderUtil.filter(
						QueryBuilders.termQuery("ownerId", individualSegmentId)
					).filter(
						QueryBuilders.termQuery(
							"ownerType", "individual-segment")
					)));
		}

		for (String collectionName :
				new String[] {"memberships", "membership-changes"}) {

			Assert.assertFalse(
				"Entries within " + collectionName + " related to the " +
					"deleted individual segment should be deleted",
				faroInfoElasticsearchInvoker.exists(
					collectionName,
					QueryBuilders.termQuery(
						"individualSegmentId", individualSegmentId)));
		}

		individualJSONObject = faroInfoElasticsearchInvoker.fetch(
			"individuals", individualJSONObject.getString("id"));

		JSONArray individualSegmentIdsJSONArray =
			individualJSONObject.getJSONArray("individualSegmentIds");

		for (int i = 0; i < individualSegmentIdsJSONArray.length(); i++) {
			Assert.assertNotEquals(
				"Individual segment ID should be removed from individual on " +
					"individual segment deletion",
				individualSegmentId,
				individualSegmentIdsJSONArray.getString(i));
		}
	}

	@Autowired
	private DeleteIndividualSegmentTasksNanite
		_deleteIndividualSegmentTasksNanite;

}