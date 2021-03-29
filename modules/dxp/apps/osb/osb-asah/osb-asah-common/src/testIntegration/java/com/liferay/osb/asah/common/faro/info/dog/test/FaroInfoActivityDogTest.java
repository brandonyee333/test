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

package com.liferay.osb.asah.common.faro.info.dog.test;

import com.liferay.osb.asah.common.dog.AsahTaskDog;
import com.liferay.osb.asah.common.dog.SegmentDog;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoActivityDog;
import com.liferay.osb.asah.common.model.AsahTask;
import com.liferay.osb.asah.common.model.Segment;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.util.Collections;
import java.util.List;

import org.json.JSONObject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Michael Bowerman
 */
@ContextConfiguration(classes = OSBAsahSpringBootApplication.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class FaroInfoActivityDogTest extends BaseFaroInfoDogTestCase {

	@Test
	public void testAddActivityTriggersAddsAsahTask() throws Exception {
		JSONObject dataSourceJSONObject = faroInfoElasticsearchInvoker.add(
			"data-sources",
			FaroInfoTestUtil.buildLiferayDataSourceJSONObject());

		String dataSourceId = dataSourceJSONObject.getString("id");

		JSONObject individualJSONObject = faroInfoElasticsearchInvoker.add(
			"individuals",
			FaroInfoTestUtil.buildIndividualJSONObject(dataSourceJSONObject));

		JSONObject activityGroupJSONObject = faroInfoElasticsearchInvoker.add(
			"activity-groups",
			FaroInfoTestUtil.buildActivityGroupJSONObject(
				dataSourceId, individualJSONObject));

		JSONObject assetJSONObject = faroInfoElasticsearchInvoker.add(
			"assets", FaroInfoTestUtil.buildPageAssetJSONObject(dataSourceId));

		String assetId = assetJSONObject.getString("id");

		Segment segment = FaroInfoTestUtil.buildDynamicSegment(
			"(((activities/ever eq 'Page#pageViewed#" + assetId + "')))");

		segment.setReferencedAssetIds(
			Collections.singleton(Long.valueOf(assetId)));

		_segmentDog.addSegment(segment);

		_faroInfoActivityDog.addActivity(
			FaroInfoTestUtil.buildActivityJSONObject(
				activityGroupJSONObject, assetJSONObject, "pageViewed",
				new String[0]));

		List<AsahTask> asahTasks = _asahTaskDog.getAsahTasks(
			"UpdateDynamicMembershipsNanite");

		Assert.assertFalse(asahTasks.isEmpty());
	}

	@Autowired
	private AsahTaskDog _asahTaskDog;

	@Autowired
	private FaroInfoActivityDog _faroInfoActivityDog;

	@Autowired
	private SegmentDog _segmentDog;

}