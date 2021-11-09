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

import com.liferay.osb.asah.batch.curator.bot.nanite.InterestThresholdScoreNanite;
import com.liferay.osb.asah.batch.curator.bot.nanite.arm.InterestScoreArm;
import com.liferay.osb.asah.batch.curator.spring.OSBAsahBatchCuratorSpringBootApplication;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.ActivityGroupDog;
import com.liferay.osb.asah.common.dog.AsahMarkerDog;
import com.liferay.osb.asah.common.dog.IndividualDog;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.entity.ActivityGroup;
import com.liferay.osb.asah.common.entity.AsahMarker;
import com.liferay.osb.asah.common.entity.Asset;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.entity.Individual;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.repository.AssetRepository;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.common.repository.FieldRepository;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
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

/**
 * @author Leslie Wong
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OSBAsahBatchCuratorSpringBootApplication.class)
public class InterestThresholdScoreNaniteTest extends BaseNaniteTestCase {

	@Before
	public void setUp() {
		_dataSource = _dataSourceRepository.save(
			FaroInfoTestUtil.buildLiferayDataSource());

		Individual individual = FaroInfoTestUtil.buildIndividual(_dataSource);

		_fieldRepository.saveAll(individual.getFields());

		_individual = _individualDog.addIndividual(individual, false);
	}

	@Test
	public void testNoThresholdWhenNoKeywords() throws Exception {
		_interestThresholdScoreNanite.run(null);

		Assert.assertFalse(
			faroInfoElasticsearchInvoker.exists(
				"OSBAsahMarkers",
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery(
						"id", "InterestThresholdScoreNanite")
				).filter(
					QueryBuilders.existsQuery("score")
				)));
	}

	@Test
	public void testThresholdComesFromKeywordWithMostViews() throws Exception {
		_addPageVisitActivities(1, 2, "bar");
		_addPageVisitActivities(2, 2, "foo");
		_addPageVisitActivities(3, 2, "test");

		_interestThresholdScoreNanite.run(null);

		Assert.assertEquals(
			_interestScoreArm.computeThresholdScore("test"),
			_getInterestThresholdScore(), _DELTA);
	}

	@Test
	public void testThresholdIsMinimumWhen0Activities() throws Exception {
		_assetRepository.save(
			_objectMapper.convertValue(
				FaroInfoTestUtil.buildPageAssetJSONObject(_dataSource.getId()),
				Asset.class));

		_interestThresholdScoreNanite.run(null);

		Assert.assertEquals(
			InterestScoreArm.MINIMUM_THRESHOLD_SCORE,
			_getInterestThresholdScore(), _DELTA);
	}

	@Test
	public void testThresholdIsMinimumWhen0ActivitiesInPast30Days()
		throws Exception {

		_addPageVisitActivities(1, 30, "bar");
		_addPageVisitActivities(2, 30, "foo");

		_interestThresholdScoreNanite.run(null);

		Assert.assertEquals(
			InterestScoreArm.MINIMUM_THRESHOLD_SCORE,
			_getInterestThresholdScore(), _DELTA);
	}

	@Test
	public void testThresholdIsMinimumWith1Page() throws Exception {
		_addPageVisitActivities(5, 2, "bar", "foo", "test");

		_interestThresholdScoreNanite.run(null);

		Assert.assertEquals(
			InterestScoreArm.MINIMUM_THRESHOLD_SCORE,
			_getInterestThresholdScore(), _DELTA);
	}

	private void _addPageVisitActivities(
		int count, int days, String... keywords) {

		ActivityGroup activityGroup = _activityGroupDog.addActivityGroup(
			FaroInfoTestUtil.buildActivityGroup(
				_dataSource.getId(),
				DateUtil.toUTCDate(
					DateUtil.addDays(DateUtil.newDayDateString(), -days)),
				_individual));

		Asset pageAsset = null;

		if (keywords.length > 0) {
			JSONArray keywordsJSONArray = new JSONArray();

			for (String keyword : keywords) {
				keywordsJSONArray.put(
					JSONUtil.put(
						"keyword", keyword
					).put(
						"type", "keyword"
					));
			}

			pageAsset = _assetRepository.save(
				_objectMapper.convertValue(
					FaroInfoTestUtil.buildPageAssetJSONObject(
						_dataSource.getId(), keywordsJSONArray),
					Asset.class));
		}
		else {
			pageAsset = _assetRepository.save(
				_objectMapper.convertValue(
					FaroInfoTestUtil.buildPageAssetJSONObject(
						_dataSource.getId()),
					Asset.class));
		}

		JSONObject pageAssetJSONObject = _objectMapper.convertValue(
			pageAsset, JSONObject.class);

		for (int i = 0; i < count; i++) {
			faroInfoElasticsearchInvoker.add(
				"activities",
				FaroInfoTestUtil.buildActivityJSONObject(
					_objectMapper.convertValue(activityGroup, JSONObject.class),
					pageAssetJSONObject, "pageUnloaded",
					new String[] {"viewDuration", "30000"}));
			faroInfoElasticsearchInvoker.add(
				"activities",
				FaroInfoTestUtil.buildActivityJSONObject(
					_objectMapper.convertValue(activityGroup, JSONObject.class),
					pageAssetJSONObject, "pageViewed", new String[0]));
		}
	}

	private double _getInterestThresholdScore() {
		AsahMarker asahMarker = _asahMarkerDog.fetchAsahMarker(
			"InterestThresholdScoreNanite",
			WeDeployDataService.OSB_ASAH_FARO_INFO);

		if (asahMarker == null) {
			return 0;
		}

		JSONObject asahMarkerContextJSONObject =
			asahMarker.getContextJSONObject();

		return asahMarkerContextJSONObject.getDouble("score");
	}

	private static final double _DELTA = 0.00001;

	@Autowired
	private ActivityGroupDog _activityGroupDog;

	@Autowired
	private AsahMarkerDog _asahMarkerDog;

	@Autowired
	private AssetRepository _assetRepository;

	private DataSource _dataSource;

	@Autowired
	private DataSourceRepository _dataSourceRepository;

	@Autowired
	private FieldRepository _fieldRepository;

	private Individual _individual;

	@Autowired
	private IndividualDog _individualDog;

	@Autowired
	private InterestScoreArm _interestScoreArm;

	@Autowired
	private InterestThresholdScoreNanite _interestThresholdScoreNanite;

	@Autowired
	private ObjectMapper _objectMapper;

}