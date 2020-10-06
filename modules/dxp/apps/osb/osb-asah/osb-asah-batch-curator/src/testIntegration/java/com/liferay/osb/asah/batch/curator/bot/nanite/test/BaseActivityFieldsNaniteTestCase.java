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

import com.liferay.osb.asah.batch.curator.bot.nanite.ActivitiesNanite;
import com.liferay.osb.asah.batch.curator.bot.nanite.IndividualActivityFieldsNanite;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoIndividualDog;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;

import org.json.JSONObject;

import org.junit.Before;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Michael Bowerman
 */
public abstract class BaseActivityFieldsNaniteTestCase
	extends BaseNaniteTestCase {

	@Before
	public void setUp() throws Exception {
		dataSourceJSONObject = faroInfoElasticsearchInvoker.add(
			"data-sources",
			FaroInfoTestUtil.buildLiferayDataSourceJSONObject());
	}

	protected void addActivities(
			int activitiesCount, String applicationId, String channelId,
			String dateString, String eventId, JSONObject individualJSONObject)
		throws Exception {

		String dataSourceId = dataSourceJSONObject.getString("id");

		JSONObject activityGroupJSONObject = faroInfoElasticsearchInvoker.add(
			"activity-groups",
			FaroInfoTestUtil.buildActivityGroupJSONObject(
				dataSourceId, dateString, individualJSONObject));

		JSONObject assetJSONObject = faroInfoElasticsearchInvoker.add(
			"assets",
			FaroInfoTestUtil.buildAssetJSONObject(applicationId, dataSourceId));

		for (int i = 0; i < activitiesCount; i++) {
			_activitiesNanite.addActivityJSONObject(
				FaroInfoTestUtil.buildActivityJSONObject(
					activityGroupJSONObject, assetJSONObject, channelId,
					eventId, new String[0]),
				applicationId, eventId, individualJSONObject.getString("id"));
		}
	}

	protected JSONObject addIndividualWithActivities(
			int activitiesCount, String applicationId, String channelId,
			String dateString, String eventId)
		throws Exception {

		JSONObject individualJSONObject = faroInfoIndividualDog.addIndividual(
			FaroInfoTestUtil.buildIndividualJSONObject(
				channelId, dataSourceJSONObject),
			false);

		addActivities(
			activitiesCount, applicationId, channelId, dateString, eventId,
			individualJSONObject);

		individualActivityFieldsNanite.run();

		return faroInfoElasticsearchInvoker.get(
			"individuals", individualJSONObject.getString("id"));
	}

	protected JSONObject dataSourceJSONObject;

	@Autowired
	protected FaroInfoIndividualDog faroInfoIndividualDog;

	@Autowired
	protected IndividualActivityFieldsNanite individualActivityFieldsNanite;

	@Autowired
	private ActivitiesNanite _activitiesNanite;

}