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

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.IndividualDog;
import com.liferay.osb.asah.common.entity.ActivityGroup;
import com.liferay.osb.asah.common.entity.Asset;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.entity.Individual;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoActivityDog;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.messaging.Channel;
import com.liferay.osb.asah.common.messaging.MessageBus;
import com.liferay.osb.asah.common.repository.ActivityGroupRepository;
import com.liferay.osb.asah.common.repository.AssetRepository;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.common.repository.FieldRepository;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.stream.curator.bot.nanite.Nanite;
import com.liferay.osb.asah.stream.curator.bot.nanite.activity.IndividualActivityFieldsNanite;
import com.liferay.osb.asah.stream.curator.bot.nanite.test.BaseNaniteTestCase;
import com.liferay.osb.asah.stream.curator.spring.OSBAsahCuratorSpringBootApplication;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

import org.apache.commons.lang3.RandomUtils;

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
public class IndividualActivityFieldsNaniteTest extends BaseNaniteTestCase {

	@Before
	public void setUp() {
		DataSource dataSource = FaroInfoTestUtil.buildLiferayDataSource();

		_dataSource = _dataSourceRepository.save(dataSource);
	}

	@Test
	public void testBlogClickedActivitiesExcluded() {
		_testSingleTypeOfActivityExcluded("Blog", "blogClicked");
	}

	@Test
	public void testBlogDepthReachedActivitiesExcluded() {
		_testSingleTypeOfActivityExcluded("Blog", "blogDepthReached");
	}

	@Test
	public void testBlogViewedActivitiesExcluded() {
		_testSingleTypeOfActivityExcluded("Blog", "blogViewed");
	}

	@Test
	public void testDocumentDownloadedActivitiesIncluded() {
		_testSingleTypeOfActivityIncluded("Document", "documentDownloaded");
	}

	@Test
	public void testDocumentPreviewedActivitiesIncluded() {
		_testSingleTypeOfActivityIncluded("Document", "documentPreviewed");
	}

	@Test
	public void testFieldBlurredActivitiesExcluded() {
		_testSingleTypeOfActivityExcluded("Form", "fieldBlurred");
	}

	@Test
	public void testFieldFocusedActivitiesExcluded() {
		_testSingleTypeOfActivityExcluded("Form", "fieldFocused");
	}

	@Test
	public void testFormSubmittedActivitiesIncluded() {
		_testSingleTypeOfActivityIncluded("Form", "formSubmitted");
	}

	@Test
	public void testFormViewedActivitiesIncluded() {
		_testSingleTypeOfActivityIncluded("Form", "formViewed");
	}

	@Test
	public void testMixtureOfVariousActivities() {
		Long channelId = RandomTestUtil.randomNumber();

		String dateString = DateUtil.newDateString();

		String yesterdayString = DateUtil.addDays(dateString, -1);

		Individual individual = FaroInfoTestUtil.buildIndividual(
			channelId, _dataSource);

		_fieldRepository.saveAll(individual.getFields());

		individual = _individualDog.addIndividual(individual, false);

		_addActivities(
			1, "Form", channelId, yesterdayString, "fieldBlurred", individual);
		_addActivities(
			2, "Form", channelId, DateUtil.addDays(yesterdayString, -8),
			"formSubmitted", individual);
		_addActivities(
			4, "Page", channelId, DateUtil.addDays(yesterdayString, -16),
			"pageLoaded", individual);
		_addActivities(
			8, "Page", channelId, DateUtil.addDays(yesterdayString, -24),
			"pageViewed", individual);
		_addActivities(
			16, "Blog", channelId, DateUtil.addDays(yesterdayString, -32),
			"blogViewed", individual);
		_addActivities(
			32, "Document", channelId, DateUtil.addDays(yesterdayString, -40),
			"documentPreviewed", individual);

		runNanite();

		individual = _individualDog.fetchIndividual(individual.getId());

		Set<Individual.ActivitiesCount> activitiesCounts =
			individual.getActivitiesCounts();

		Stream<Individual.ActivitiesCount> activitiesCountsStream =
			activitiesCounts.stream();

		Individual.ActivitiesCount individualActivitiesCount =
			activitiesCountsStream.filter(
				activitiesCount -> Objects.equals(
					channelId, activitiesCount.getChannelId())
			).findFirst(
			).orElse(
				null
			);

		Assert.assertNotNull(individualActivitiesCount);
		Assert.assertEquals(
			42L, (long)individualActivitiesCount.getActivitiesCount());

		_assertLastActivitiesDates(
			channelId, 1, DateUtil.addDays(yesterdayString, -8),
			individual.getLastActivityDates());
		_assertLastActivitiesDates(
			channelId, 0, null, individual.getPreviousActivityDates());

		_addActivities(
			1, "Page", channelId, yesterdayString, "pageViewed", individual);

		runNanite();

		individual = _individualDog.fetchIndividual(individual.getId());

		_assertLastActivitiesDates(
			channelId, 1, yesterdayString, individual.getLastActivityDates());
		_assertLastActivitiesDates(
			channelId, 1, DateUtil.addDays(yesterdayString, -8),
			individual.getPreviousActivityDates());

		_addActivities(
			1, "Page", channelId, dateString, "pageViewed", individual);

		runNanite();

		individual = _individualDog.fetchIndividual(individual.getId());

		_assertLastActivitiesDates(
			channelId, 1, dateString, individual.getLastActivityDates());
		_assertLastActivitiesDates(
			channelId, 1, yesterdayString,
			individual.getPreviousActivityDates());

		_addActivities(
			1, "Page", channelId, DateUtil.addDays(dateString, 1), "pageViewed",
			individual);

		runNanite();

		individual = _individualDog.fetchIndividual(individual.getId());

		_assertLastActivitiesDates(
			channelId, 1, DateUtil.addDays(dateString, 1),
			individual.getLastActivityDates());
		_assertLastActivitiesDates(
			channelId, 1, yesterdayString,
			individual.getPreviousActivityDates());
	}

	@Test
	public void testPageDepthReachedActivitiesExcluded() {
		_testSingleTypeOfActivityExcluded("Page", "pageDepthReached");
	}

	@Test
	public void testPageLoadedActivitiesExcluded() {
		_testSingleTypeOfActivityExcluded("Page", "pageLoaded");
	}

	@Test
	public void testPageUnloadedActivitiesExcluded() {
		_testSingleTypeOfActivityExcluded("Page", "pageUnloaded");
	}

	@Test
	public void testPageViewedActivitiesIncluded() {
		_testSingleTypeOfActivityIncluded("Page", "pageViewed");
	}

	@Test
	public void testWebContentClickedActivitiesExcluded() {
		_testSingleTypeOfActivityExcluded("WebContent", "webContentClicked");
	}

	@Test
	public void testWebContentViewedActivitiesExcluded() {
		_testSingleTypeOfActivityExcluded("WebContent", "webContentViewed");
	}

	@Override
	protected Nanite getNanite() {
		return _individualActivityFieldsNanite;
	}

	private void _addActivities(
		int activitiesCount, String applicationId, Long channelId,
		String dateString, String eventId, Individual individual) {

		Long dataSourceId = _dataSource.getId();

		ActivityGroup activityGroup = _activityGroupRepository.save(
			FaroInfoTestUtil.buildActivityGroup(
				dataSourceId, DateUtil.toUTCDate(dateString), individual));

		Asset asset = _assetRepository.save(
			_objectMapper.convertValue(
				FaroInfoTestUtil.buildAssetJSONObject(
					applicationId, dataSourceId),
				Asset.class));

		for (int i = 0; i < activitiesCount; i++) {
			_faroInfoActivityDog.addActivity(
				FaroInfoTestUtil.buildActivityJSONObject(
					_objectMapper.convertValue(activityGroup, JSONObject.class),
					_objectMapper.convertValue(asset, JSONObject.class),
					channelId, eventId, new String[0]));

			if (_faroInfoActivityDog.isActivity(applicationId, eventId)) {
				_messageBus.sendMessage(
					Channel.ACTIVE_INDIVIDUAL_IDS,
					JSONUtil.put(
						"channelId", channelId
					).put(
						"ownerId", individual.getId()
					).put(
						"projectId", ProjectIdThreadLocal.getProjectId()
					).toString());
			}
		}
	}

	private Individual _addIndividualWithActivities(
		int activitiesCount, String applicationId, Long channelId,
		String dateString, String eventId) {

		Individual individual = FaroInfoTestUtil.buildIndividual(
			channelId, _dataSource);

		_fieldRepository.saveAll(individual.getFields());

		individual = _individualDog.addIndividual(individual, false);

		_addActivities(
			activitiesCount, applicationId, channelId, dateString, eventId,
			individual);

		runNanite();

		return _individualDog.fetchIndividual(individual.getId());
	}

	private void _assertLastActivitiesDates(
		Long channelId, long expectedLastActivityDateCount,
		String expectedLastActivityDateString,
		Set<Individual.ActivityDate> activityDates) {

		Assert.assertEquals(
			activityDates.toString(), expectedLastActivityDateCount,
			activityDates.size());

		if (activityDates.isEmpty()) {
			return;
		}

		Stream<Individual.ActivityDate> stream = activityDates.stream();

		Individual.ActivityDate individualActivityDate = stream.filter(
			lastActivityDate -> Objects.equals(
				channelId, lastActivityDate.getChannelId())
		).findFirst(
		).orElse(
			null
		);

		Assert.assertNotNull(individualActivityDate);
		Assert.assertEquals(
			expectedLastActivityDateString,
			DateUtil.toUTCString(individualActivityDate.getActivityDate()));
	}

	private void _testSingleTypeOfActivityExcluded(
		String applicationId, String eventId) {

		Individual individual = _addIndividualWithActivities(
			RandomUtils.nextInt(5, 25), applicationId,
			RandomTestUtil.randomNumber(), DateUtil.newDateString(), eventId);

		Set<Individual.ActivitiesCount> activitiesCounts =
			individual.getActivitiesCounts();

		Assert.assertEquals(
			activitiesCounts.toString(), 0, activitiesCounts.size());
	}

	private void _testSingleTypeOfActivityIncluded(
		String applicationId, String eventId) {

		int activitiesCount = RandomUtils.nextInt(5, 25);
		Long channelId = RandomTestUtil.randomNumber();
		String dateString = DateUtil.newDateString();

		Individual individual = _addIndividualWithActivities(
			activitiesCount, applicationId, channelId, dateString, eventId);

		Set<Individual.ActivitiesCount> activitiesCounts =
			individual.getActivitiesCounts();

		Stream<Individual.ActivitiesCount> activitiesCountsStream =
			activitiesCounts.stream();

		Individual.ActivitiesCount individualActivitiesCount =
			activitiesCountsStream.filter(
				curActivitiesCount -> Objects.equals(
					channelId, curActivitiesCount.getChannelId())
			).findFirst(
			).orElse(
				null
			);

		Assert.assertNotNull(individualActivitiesCount);
		Assert.assertEquals(
			activitiesCount,
			(long)individualActivitiesCount.getActivitiesCount());

		Set<Individual.ActivityDate> lastActivityDates =
			individual.getLastActivityDates();

		Stream<Individual.ActivityDate> lastActivityDatesStream =
			lastActivityDates.stream();

		Individual.ActivityDate individualLastActivityDate =
			lastActivityDatesStream.filter(
				lastActivityDate -> Objects.equals(
					channelId, lastActivityDate.getChannelId())
			).findFirst(
			).orElse(
				null
			);

		Assert.assertNotNull(individualLastActivityDate);
		Assert.assertEquals(
			dateString,
			DateUtil.toUTCString(individualLastActivityDate.getActivityDate()));
	}

	@Autowired
	private ActivityGroupRepository _activityGroupRepository;

	@Autowired
	private AssetRepository _assetRepository;

	private DataSource _dataSource;

	@Autowired
	private DataSourceRepository _dataSourceRepository;

	@Autowired
	private FaroInfoActivityDog _faroInfoActivityDog;

	@Autowired
	private FieldRepository _fieldRepository;

	@Autowired
	private IndividualActivityFieldsNanite _individualActivityFieldsNanite;

	@Autowired
	private IndividualDog _individualDog;

	@Autowired
	private MessageBus _messageBus;

	@Autowired
	private ObjectMapper _objectMapper;

}