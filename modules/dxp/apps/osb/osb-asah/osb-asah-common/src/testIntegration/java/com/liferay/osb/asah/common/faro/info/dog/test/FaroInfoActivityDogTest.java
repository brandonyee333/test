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

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.dog.ActivityGroupDog;
import com.liferay.osb.asah.common.dog.AsahTaskDog;
import com.liferay.osb.asah.common.dog.IndividualDog;
import com.liferay.osb.asah.common.dog.SegmentDog;
import com.liferay.osb.asah.common.entity.ActivityGroup;
import com.liferay.osb.asah.common.entity.AsahTask;
import com.liferay.osb.asah.common.entity.Asset;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.entity.Individual;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoActivityDog;
import com.liferay.osb.asah.common.repository.AssetRepository;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.spring.OSBAsahElasticsearchTestExecutionListener;
import com.liferay.osb.asah.test.util.spring.OSBAsahRepositoryTestExecutionListener;
import com.liferay.osb.asah.test.util.spring.OSBAsahSQLTestExecutionListener;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit5ClassRunner;

import java.util.Collections;
import java.util.List;

import org.json.JSONObject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;

/**
 * @author Michael Bowerman
 */
@ContextConfiguration(classes = OSBAsahSpringBootApplication.class)
@ExtendWith(OSBAsahSpringJUnit5ClassRunner.class)
@TestExecutionListeners(
	mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS,
	value = {
		OSBAsahElasticsearchTestExecutionListener.class,
		OSBAsahRepositoryTestExecutionListener.class,
		OSBAsahSQLTestExecutionListener.class
	}
)
public class FaroInfoActivityDogTest extends BaseFaroInfoDogTestCase {

	@Test
	public void testAddActivityTriggersAddsAsahTask() {
		DataSource dataSource = _dataSourceRepository.save(
			FaroInfoTestUtil.buildLiferayDataSource());

		Long dataSourceId = dataSource.getId();

		Individual individual = _individualDog.addIndividual(
			FaroInfoTestUtil.buildIndividual(dataSource), false);

		ActivityGroup activityGroup = _activityGroupDog.addActivityGroup(
			FaroInfoTestUtil.buildActivityGroup(dataSourceId, individual));

		Asset asset = _assetRepository.save(
			_objectMapper.convertValue(
				FaroInfoTestUtil.buildPageAssetJSONObject(dataSourceId),
				Asset.class));

		Segment segment = FaroInfoTestUtil.buildDynamicSegment(
			"(((activities/ever eq 'Page#pageViewed#" + asset.getId() + "')))");

		segment.setReferencedAssetIds(Collections.singleton(asset.getId()));

		_segmentDog.addSegment(segment);

		_faroInfoActivityDog.addActivity(
			FaroInfoTestUtil.buildActivityJSONObject(
				_objectMapper.convertValue(activityGroup, JSONObject.class),
				_objectMapper.convertValue(asset, JSONObject.class),
				"pageViewed", new String[0]));

		List<AsahTask> asahTasks = _asahTaskDog.getAsahTasks(
			"UpdateDynamicMembershipsNanite");

		Assertions.assertFalse(asahTasks.isEmpty());
	}

	@Autowired
	private ActivityGroupDog _activityGroupDog;

	@Autowired
	private AsahTaskDog _asahTaskDog;

	@Autowired
	private AssetRepository _assetRepository;

	@Autowired
	private DataSourceRepository _dataSourceRepository;

	@Autowired
	private FaroInfoActivityDog _faroInfoActivityDog;

	@Autowired
	private IndividualDog _individualDog;

	@Autowired
	private ObjectMapper _objectMapper;

	@Autowired
	private SegmentDog _segmentDog;

}